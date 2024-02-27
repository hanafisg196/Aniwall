
 
 <!-- Livewire-->
<div>
 
   
     <!-- Navbar Start -->
     <nav class="navbar navbar-expand-lg bg-white navbar-light shadow sticky-top p-0">
         <a href="index.html" class="navbar-brand d-flex align-items-center px-4 px-lg-5">
             <h5 class="ml-3">{{ auth()->user()->name }}/ {{ auth()->user()->username }}</h5>
         </a>
         <button type="button" class="navbar-toggler me-4" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
             <span class="navbar-toggler-icon"></span>
         </button>
         <div class="collapse navbar-collapse" id="navbarCollapse">
             <div class="navbar-nav p-4 p-lg-0" style="margin-left: 30%">
                 <h5>0/100</h5>
             </div>
         </div>
     </nav>
     <!-- Navbar End -->
             
     <!-- Ujian Start -->
     
     <div class="container-xxl py-5 category">
        <div class="container">
            <div class="row g-3">
                <div class="card-body">
                    <div class="col-lg-12">
                        <div class="row">
                            <div class="col-lg-12">
                                @foreach($soals as $soal)
                                <button  wire:click="selectSoal({{ $soal->id }})"
                                    class="btn btn-primary">
                                     {{ $soal->id }}</button>
                                @endforeach
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row g-3">
                <div class="card" style="border: 0ch">
                    <div class="card-header" style="background-color:rgb(210, 210, 210)">
                        <div class="card-body">
                            <div class="col-lg-11 mx-auto">
                                <div class="row">
                                    <div class="col-lg-8">
                                        @if ($selectedSoal)
                                        <p class="card-text">{{ $selectedSoal->soal }}</p>
                                        <div class="col-lg-4">
                                            @foreach ($selectedSoal->subsoal as $sub)
                                            <label for="option{{ $sub->id }}">
                                                <input type="radio" id="option{{ $sub->id }}"
                                                wire:click="selectAnswer({{ $sub->id }},
                                                '{{ $sub->nilaicf_id }}')" wire:model="selectedAnswer"
                                                value="{{ $sub->nilaicf_id }}">
                                                {{ $sub->nilaicf->term }}
                                            </label>
                                            <br>
                                        @endforeach
                                        </div>
                                        @endif
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <br>
            <div class="col-lg-12">
                <div class="row">
                    <div class="col-lg-12">
                        <button wire:click="selectPrevQuestion" class="btn btn-primary">Prev</button>
                        <button wire:click="selectNextQuestion" class="btn btn-primary">Next</button>
                        
                    </div>
                </div>
            </div>
            
    </div>
    </div>
    
     <!-- Ujian End -->



</div>
 <!-- Livewire-->





