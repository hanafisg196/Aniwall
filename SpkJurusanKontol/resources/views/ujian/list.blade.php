@extends('tampilan2.main')
@section('content')
<div class="container">
    <br>
    <h1 class="mb-5 text-center">Ujian Penentuan Jurusan SMKN 2 Padang Panjang</h1>

    <div class="container">
        <div class="row">
            @foreach ($batch as $item)
            <div class="col-md-4 mb-3">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">{{ $item->title }}</h5>
                        <p class="card-text">Ujian Penetuan Jurusan</p>
                        <a href="/kerjakan" class="btn btn-primary">Kerjakan</a>
                      </div>
                  </div>
            </div>
            @endforeach


        </div>


    </div>


</div>


</div>

@endsection
