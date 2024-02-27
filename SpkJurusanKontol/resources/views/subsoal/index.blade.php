@extends('tampilan.main')
@section('content')

<div class="col-lg-12">
<!-- Contextual classes table starts -->
    <div class="card">
        <div class="card-header">
            <h5>Tabel Data Soal</h5>
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
        <div class="card-block">
            @foreach ($datas as $data)
                    <h2>{{ $data->kode .' '. $data->soal }}</h2>
                        {{-- <a href="{{ route('subsoal.create', ['id' => $datas->id]) }}" class="btn btn-primary mb-3 ">Tambah Data</a> --}}
                            <div class="text-right mr-4">
                                <button type="button" class="btn btn-primary exampleModaledit m-2" data-toggle="modal" data-target="#exampleModaltambah{{ $data->id }}">
                                    <i class="ti-plus"></i>Tambah Jawaban
                                </button>
                            </div>
                            <table class="table table-striped mb-5">
                                <tr>
                                    <th>Opsi</th>
                                    <th>Jawaban</th>
                                    <th>Aksi</th>
                                </tr>
                                @foreach ($data->subsoal as $isi)
                                    <tr>
                                        <td>{{ chr(96 + $loop->iteration) }}</td>
                                        <td>{{ $isi->nilaicf->term }}</td>
                                        <td>
                                            <form action="/subsoal/{{ $isi->id }}" method="POST">
                                                @csrf
                                                @method('DELETE')
                                                <button type="submit" onclick="return confirm('Apakah anda yakin ingin menghapus jawaban ini?')" class="btn btn-danger rounded-circle">Hapus</button>
                                            </form>
                                        </td>
                                    </tr>
                                @endforeach
                            </table>
                @endforeach
        </div>
    </div>
</div>

{{-- Modal Edit Data --}}
@foreach ($datas as $data)
    <div class="modal fade" id="exampleModaltambah{{ $data->id }}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Tambah Soal</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form method="POST" action="{{ url('/tambahjawaban', $data->id) }}" enctype="multipart/form-data">
                        @csrf
                        <div class="card-block">
                            <div class="form-group row">
                                <label class="col-sm-3 col-form-label">Jawaban</label>
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
                            <button type="submit" class="btn btn-primary">Simpan</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
@endforeach

{{-- Modal Edit Data End--}}

@endsection

