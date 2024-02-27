<?php

namespace App\Livewire;

use App\Models\Soal;
use App\Models\Ujian;
use Livewire\Livewire;
use Livewire\Component;
use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\Session;

class Kerjakanujian extends Component
{
    public $user_id;
    public $subsoal_id;


    public function mount()
    {
        $this->user_id = auth()->user()->id;
    }

    public function render()
    {
        return view('livewire.kerjakanujian',[
            'soal' => Soal::with('subsoal')->paginate(15)
        ]);

    }


    public function selectSoal($soalId)
    {
        $this->selectedSoal = $this->soals->find($soalId);
        $this->selectedAnswer = null;

    }
    public function selectAnswer($subSoalId, $selectedAnswer)
    {
        $user = Auth::user();
        // check answr exist or not
        $ujian = Ujian::where('soal_id', $this->selectedSoal->id)->first();

        if ($ujian) {
            // if answer is exist update properties
            $ujian->update(['subsoal_id' => $subSoalId, 'selected_answer' => $selectedAnswer]);
        } else {
            // if doesnt exist creta new properties
            Ujian::create([
                'user_id' => $user->id,
                'soal_id' => $this->selectedSoal->id,
                'subsoal_id' => $subSoalId,
                'selected_answer' => $selectedAnswer,
            ]);
        }

        $this->selectedAnswer = $selectedAnswer;
    }



}
