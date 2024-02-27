@extends('tampilan.main')
@section('content')
<div class="col-lg-12">
<!-- Contextual classes table starts -->
    <div class="card">
        <div class="card-header">
            <h5>Tabel Data Pihak Sekolah</h5>
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
        </div>
        <div class="card-block table-border-style">
            <div class="table-responsive">
                <table class="table">
                    <caption>Data Pihak Sekolah</caption>
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>Nama</th>
                            <th>NIP</th>
                            <th>Jabatan</th>
                            <th>Aksi</th>
                        </tr>
                    </thead>

                    <!-- Kemudian bagian HTML templatenya -->
                    <style>
                        .custom-td {
                            white-space: pre-wrap;
                        }
                    </style>
                    @php $no = 1; @endphp
                    <tbody>
                        @foreach ($data as $datas)
                            @if ($datas['role'] == "pihaksekolah")
                                <tr>
                                    <th scope="row">{{ $no++}}</th>
                                    <td>{{ $datas['name'] }}</td>
                                    <td>{{ $datas['username'] }}</td>
                                    <td>{{ $datas['jabatan'] }}</td>
                                    <td>
                                        <button type="button" class="btn btn-primary exampleModaledit" data-toggle="modal" data-target="#exampleModaledit{{ $datas->id }}">
                                            <i class="ti-pencil"></i>
                                        </button>
                                        <form action="/siswa/{{ $datas->id }}" method="POST" class="d-inline">
                                            @method('delete')
                                            @csrf
                                            <button class="ti-trash btn btn-danger" onclick="return confirm('Yakin Menghapus Data?')"></button>
                                        </form>
                                    </td>
                                </tr>
                            @endif
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
                <h5 class="modal-title" id="exampleModalLabel">Tambah Data Rekening</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form method="POST" action="/pihaksekolah" enctype="multipart/form-data">
                    @csrf
                    <div class="card-block">

                        <div class="form-group row">
                            <label for="name" class="col-sm-3 col-form-label">Nama</label>
                            <div class="col-sm-9">
                                <input type="text" id="name" name="name" class="form-control @error('name') is-invalid @enderror" value="{{ old('name') }}" required>
                                @error('name')
                                    <div class="invalid-feedback">
                                        {{ $message }}
                                    </div>
                                @enderror
                            </div>
                        </div>

                        <div class="form-group row">
                            <label class="col-sm-3 col-form-label">NIS</label>
                            <div class="col-sm-9">
                                <input type="number" id="username" name="username" class="form-control @error('username') is-invalid @enderror"
                                value="{{ old('name') }}" required>

                                @error('username')
                                    <div class="invalid-feedback">
                                        {{ $message }}
                                    </div>
                                @enderror

                            </div>
                        </div>

                        <div class="form-group row">
                            <label class="col-sm-3 col-form-label">Jabatan</label>
                            <div class="col-sm-9">
                                <input type="text" id="jabatan" name="jabatan" class="form-control @error('jabatan') is-invalid @enderror"
                                value="{{ old('jabatan') }}" required>

                                @error('jabatan')
                                    <div class="invalid-feedback">
                                        {{ $message }}
                                    </div>
                                @enderror

                            </div>
                        </div>
                        <input type="hidden" id="role" name="role" class="form-control @error('role') is-invalid @enderror"
                        value="pihaksekolah" readonly>

                        <div class="form-group row">
                            <label class="col-sm-3 col-form-label">Password</label>
                            <div class="col-sm-9">
                                <input type="password" id="password" name="password" class="form-control @error('password') is-invalid @enderror" required>

                                @error('password')
                                    <div class="invalid-feedback">
                                        {{ $message }}
                                    </div>
                                @enderror

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
@foreach ($data as $data)
    <div class="modal fade" id="exampleModaledit{{ $data->id }}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Edit Pihak Sekolah</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form method="POST" action="{{ url('/editpihaksekolah', $data->id) }}" enctype="multipart/form-data">
                        @csrf
                        <div class="card-block">
                            <div class="form-group row">
                                <label for="name" class="col-sm-3 col-form-label">Nama</label>
                                <div class="col-sm-9">
                                    <input type="text" id="name" name="name" class="form-control @error('name') is-invalid @enderror" value="{{ $data->name }}" required>
                                    @error('name')
                                        <div class="invalid-feedback">
                                            {{ $message }}
                                        </div>
                                    @enderror
                                </div>
                            </div>

                            <div class="form-group row">
                                <label class="col-sm-3 col-form-label">NIP</label>
                                <div class="col-sm-9">
                                    <input type="number" id="username" name="username" class="form-control @error('username') is-invalid @enderror"
                                    value="{{ $data->username }}" required>

                                    @error('username')
                                        <div class="invalid-feedback">
                                            {{ $message }}
                                        </div>
                                    @enderror

                                </div>
                            </div>

                            <div class="form-group row">
                                <label class="col-sm-3 col-form-label">Jabatan</label>
                                <div class="col-sm-9">
                                    <input type="text" id="jabatan" name="jabatan" class="form-control @error('jabatan') is-invalid @enderror"
                                    value="{{ $data->jabatan }}" required>

                                    @error('jabatan')
                                        <div class="invalid-feedback">
                                            {{ $message }}
                                        </div>
                                    @enderror

                                </div>
                            </div>

                            <input type="hidden" id="role" name="role" class="form-control @error('role') is-invalid @enderror"
                            value="pihaksekolah" readonly>


                            <div class="form-group row">
                                <label class="col-sm-3 col-form-label">Password</label>
                                <div class="col-sm-9">
                                    <input type="password" id="password" name="password" class="form-control @error('password') is-invalid @enderror" required>

                                    @error('password')
                                        <div class="invalid-feedback">
                                            {{ $message }}
                                        </div>
                                    @enderror

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
