<div>
    <nav class="navbar navbar-expand-lg bg-white navbar-light shadow sticky-top p-0">
        <a href="index.html" class="navbar-brand d-flex align-items-center px-4 px-lg-5">
            <h5 class="ml-3">{{ auth()->user()->name }}/ {{ auth()->user()->username }}</h5>
        </a>
        <button type="button" class="navbar-toggler me-4" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
            <span class="navbar-toggler-icon"></span>
        </button>
    </nav>
    <!-- Ujian Start -->

    <div class="container-xxl py-5 category">

            <div class="container">
                <div class="row g-3">
                    <div class="card" style="border: 0ch">
                        <div class="card-body">
                            <div class="col-lg-12">
                                <div class="row">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row g-3">
                    <div class="card" style="border: 0ch">
                        @foreach ($soal as $key => $question)
                        <form wire:submit.prevent="store">
                            <div class="card-body">
                                <div class="col-lg-11 mx-auto">
                                    <div class="row">
                                        <div class="col-lg-8">
                                            <p class="card-text">{{ $soal->firstitem() + $key }}.&nbsp;{{ $question->soal }}</p>
                                        </div>
                                        <div class="col-lg-4">
                                            @foreach ($question->subsoal as $key => $sub)
                                            <label for="option{{ $sub->id }}">
                                                <input type="radio" id="option{{ $key }}"  value="{{ $sub->nilaicf_id }}">
                                                {{ $sub->nilaicf->term}}
                                            </label>
                                            <br>
                                            @endforeach
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form>
                        <hr>
                        @endforeach

                        </div>
                </div>
            </div>
        <div class="card-footer text-center" style="border: 0ch">
            <a href="{{ $soal->nextPageUrl() }}" class="btn btn-primary btn-lg btn-block">Lanjutkan</a>
        </div>
    </div>
    <!-- Ujian End -->
</div>

