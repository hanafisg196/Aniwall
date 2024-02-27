@extends('tampilan.main')
@section('content')
<div class="col-lg-12">
<!-- Contextual classes table starts -->
    <div class="card">
        <div class="card-header">
            <h5>Tabel Data Siswa</h5>
            <div class="card-header-right">
                <ul class="list-unstyled card-option">
                    <li><i class="fa fa-chevron-left"></i></li>
                    <li><i class="fa fa-window-maximize full-card"></i></li>
                    <li><i class="fa fa-minus minimize-card"></i></li>
                    <li><i class="fa fa-refresh reload-card"></i></li>
                    <li><i class="fa fa-times close-card"></i></li>
                </ul>
            </div>
        </div>

        @if(session()->has('success'))
        <div class="m-2 row">
            <div class="alert alert-success col-sm-3 ml-3 mb-2" role="alert">
                {{ session('success') }}
            </div>
        </div>
        @endif

        <div class="text-right mr-4">
            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
                <i class="ti-plus"></i>Tambah
            </button>
            {{-- <a href="/kegiatan/create" class="btn waves-effect waves-light btn-primary"><i class="ti-plus"></i>Tambah</a> --}}
        </div>
        <div class="card-block table-border-style">
            <div class="table-responsive">
                <table class="table">
                    <caption>Data Siswa</caption>
                    <thead>
                        <tr>
                            <th>Kode</th>
                            <th>Batch</th>
                            <th>Soal</th>
                            <th>Jenis</th>
                            <th>Nilai CF(Rule)</th>
                            <th>Aksi</th>
                        </tr>
                    </thead>

                    <!-- Kemudian bagian HTML templatenya -->
                    <style>
                        .custom-td {
                            white-space: pre-wrap;
                        }
                    </style>
                    <tbody>
                        @foreach ($data as $datas)
                        <tr>
                            <td>{{ $datas->kode }}</td>
                            <td>{{ $datas->batch->kode }}</td>
                            <td>{{ $datas->soal }}</td>
                            <td>{{ $datas->jenis }}</td>
                            <td>{{ $datas->nilaicf->term }}</td>
                            <td>
                                <button type="button" class="btn btn-primary exampleModaledit"
                                 data-toggle="modal" data-target="#exampleModaledit{{ $datas->id }}">
                                    <i class="ti-pencil"></i>
                                </button>
                                <form action="/soal/{{ $datas->id }}" method="POST" class="d-inline">
                                    @method('delete')
                                    @csrf
                                    <button class="ti-trash btn btn-danger"
                                    onclick="return confirm('Yakin Menghapus Data?')"></button>
                                </form>
                            </td>
                        </tr>
                        @endforeach
                    </tbody>


                </table>
            </div>
        </div>
    </div>
</div>

{{-- Modal Tambah Data --}}
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Tambah Soal</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form method="POST" action="/soal" enctype="multipart/form-data">
                    @csrf
                    <div class="card-block">


                        <div class="form-group row">
                            <label class="col-sm-3 col-form-label">Batch</label>
                            <div class="col-md-9">
                                <select class="form-control" name="batch_id" >
                                    @foreach ($batch as $items )
                                    <option value="{{ $items->id }}">{{ $items->kode }}</option>
                                @endforeach
                                </select>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label class="col-sm-3 col-form-label">Kode</label>
                            <div class="col-sm-9">
                                <input type="text" id="kode" name="kode" class="form-control @error('kode') is-invalid @enderror"
                                value="{{ old('kode') }}" required>

                                @error('kode')
                                    <div class="invalid-feedback">
                                        {{ $message }}
                                    </div>
                                @enderror

                            </div>
                        </div>

                        <div class="form-group row">
                            <label class="col-sm-3 col-form-label">Soal</label>
                            <div class="col-sm-9">
                                <textarea name="soal" id="soal" cols="30" rows="5"
                                class="form-control" required placeholder="Tulis soal..."></textarea>

                                @error('soal')
                                    <div class="invalid-feedback">
                                        {{ $message }}
                                    </div>
                                @enderror

                            </div>
                        </div>

                        <div class="form-group row">
                            <label class="col-sm-3 col-form-label">Jenis</label>
                            <div class="col-sm-9">
                                <input type="text" id="jenis" name="jenis" class="form-control @error('jenis') is-invalid @enderror"
                                value="{{ old('jenis') }}" required>

                                @error('jenis')
                                    <div class="invalid-feedback">
                                        {{ $message }}
                                    </div>
                                @enderror
                            </div>
                        </div>

                        <div class="form-group row">
                            <label class="col-sm-3 col-form-label">Nilai CF(Rule)</label>
                            <div class="col-md-9">
                                <select class="form-control" name="nilaicf_id" >
                                    @foreach ($nilaicf as $items )
                                    <option value="{{ $items->id }}">{{ $items->term }}</option>
                                @endforeach
                                </select>
                            </div>
                        </div>

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary">Tambah</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- Modal Tambah Data ends -->

{{-- Modal Edit Data --}}
@foreach ($data as $datas)
    <div class="modal fade" id="exampleModaledit{{ $datas->id }}"
        tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Edit Soal</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form method="POST" action="{{ url('/editsoal', $datas->id) }}" enctype="multipart/form-data">
                        @csrf
                        <div class="card-block">

                            <div class="form-group row">
                                <label class="col-sm-3 col-form-label">Nilai CF(Rule)</label>
                                <div class="col-md-9">
                                    <select class="form-control" name="batch_id">
                                        @foreach ($batch as $item)
                                        @if(old('batch_id', $datas->batch_id) == $item->id)
                                            <option value="{{ $item->id }}" selected>{{ $item->kode }}</option>
                                        @else
                                            <option value="{{ $item->id }}">{{ $item->kode }}</option>
                                        @endif

                                    @endforeach
                                    </select>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="kode" class="col-sm-3 col-form-label">Kode</label>
                                <div class="col-sm-9">
                                    <input type="text" id="kode" name="kode" class="form-control
                                     @error('kode') is-invalid @enderror" value="{{ $datas->kode }}" required>
                                    @error('kode')
                                        <div class="invalid-feedback">
                                            {{ $message }}
                                        </div>
                                    @enderror
                                </div>
                            </div>

                            <div class="form-group row">
                                <label class="col-sm-3 col-form-label">Soal</label>
                                <div class="col-sm-9">
                                    <textarea name="soal" id="soal" cols="30" rows="5"
                                    class="form-control" required placeholder="Tulis soal...">{{ $datas->soal }}</textarea>

                                    @error('soal')
                                        <div class="invalid-feedback">
                                            {{ $message }}
                                        </div>
                                    @enderror

                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="jenis" class="col-sm-3 col-form-label">Jenis</label>
                                <div class="col-sm-9">
                                    <input type="text" id="jenis" name="jenis" class="form-control
                                     @error('jenis') is-invalid @enderror" value="{{ $datas->jenis }}" required>
                                    @error('jenis')
                                        <div class="invalid-feedback">
                                            {{ $message }}
                                        </div>
                                    @enderror
                                </div>
                            </div>

                            <div class="form-group row">
                                <label class="col-sm-3 col-form-label">Nilai CF(Rule)</label>
                                <div class="col-md-9">
                                    <select class="form-control" name="nilaicf_id">
                                        @foreach ($nilaicf as $item)
                                        @if(old('nilaicf_id', $datas->nilaicf_id) == $item->id)
                                            <option value="{{ $item->id }}" selected>{{ $item->term }}</option>
                                        @else
                                            <option value="{{ $item->id }}">{{ $item->term }}</option>
                                        @endif

                                    @endforeach
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            <button type="submit" class="btn btn-primary">Simpan</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
@endforeach

{{-- Modal Edit Data End--}}

  <script>
    document.addEventListener('trix-file-accept', function(e){
        e.preventDefault();
    });

    $(document).ready(function() {
        $('.custom-td').each(function() {
            var text = $(this).text();
            var words = text.split(' ');
            var result = '';
            var count = 0;

            for (var i = 0; i < words.length; i++) {
                result += words[i] + ' ';
                count++;

                if (count >= 5) {
                    result += '\n';
                    count = 0;
                }
            }

            $(this).text(result.trim());
        });
    });
</script>

@endsection
