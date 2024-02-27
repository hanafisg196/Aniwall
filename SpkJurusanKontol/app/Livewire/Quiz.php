<?php

namespace App\Livewire;

use App\Models\Soal;
use App\Models\Ujian;
use Illuminate\Support\Facades\Auth;
use Livewire\Component;

class Quiz extends Component
{
    public $soals;
    public $selectedSoal;
    public $ujian;
    public $currentIndex;
    public $selectedAnswer;

 
    protected $listeners = ['refreshQuiz' => 'render'];


    public function render()
    {
        $this->soals = Soal::with('subsoal')->get();

        //take first id for soals
        if (!$this->selectedSoal && count($this->soals) > 0) {
            $this->selectedSoal = $this->soals->first();
        }

         // Retrieve the user's previous answer for the current question
        $this->ujian = Ujian::where('soal_id', $this->selectedSoal->id)
        ->where('user_id', auth()->user()->id)
        ->first();

        // Set $this->selectedAnswer to the previous answer if available, otherwise set it to null
        $this->selectedAnswer = $this->ujian ?  $this->ujian->selected_answer : null;

        return view('livewire.quiz');
    }

    public function selectSoal($soalId)
    {
         $this->selectedSoal = $this->soals->find($soalId);

         
        // Retrieve the user's previous answer for the current question
         $this->ujian;
        // Set $this->selectedAnswer to the previous answer if available, otherwise set it to null
         $this->selectedAnswer = $this->ujian ?  $this->ujian->selected_answer : null;

    }

    public function selectAnswer($subSoalId, $selectedAnswer)
    {
        // Check if the user has answered this question before
        $this->ujian;

        if ($this->ujian) {
            // If the user has answered, update the answer
            $this->ujian->update(['subsoal_id' => $subSoalId, 'selected_answer' => $selectedAnswer]);
            $this->selectedAnswer = $selectedAnswer;
        } else {
            // If not, create a new answer record
            Ujian::create([
                'user_id' => auth()->user()->id,
                'soal_id' => $this->selectedSoal->id,
                'subsoal_id' => $subSoalId,
                'selected_answer' => $selectedAnswer,
            ]);
            $this->selectedAnswer = $selectedAnswer;
        }

        // Emit an event to refresh the quiz for other components or if the page is refreshed
        $this->dispatch('refreshQuiz');
    }

    public function selectNextQuestion()
    {
        //search id from soals
        $this->currentIndex = $this->soals->search(function ($soal){
            return $soal->id == $this->selectedSoal->id;
        
        });
        //plus one id (id + 1)
        if ($this->currentIndex !== false && $this->currentIndex + 1 < count($this->soals)) {
            $this->selectedSoal = $this->soals[$this->currentIndex + 1];
            
        }
        
    }

    public function selectPrevQuestion()
    {
        //search id from soals
        $this->currentIndex = $this->soals->search(function ($soal){
            return $soal->id == $this->selectedSoal->id;
        
        });
        //less one id (id - 1)
        if ($this->currentIndex !== false &&  $this->currentIndex - 1 >= 0
        && $this->currentIndex -1 < count($this->soals)) {
            $this->selectedSoal = $this->soals[ $this->currentIndex - 1];
        }
        
    }


}

  

