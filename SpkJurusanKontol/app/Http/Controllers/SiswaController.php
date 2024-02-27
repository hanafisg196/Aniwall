<?php

namespace App\Http\Controllers;

use App\Models\User;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Hash;

class SiswaController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index()
    {
        return view('siswa.index',[
            "data" => User::latest()->get()
        ]);
    }

    /**
     * Show the form for creating a new resource.
     */
    public function editsiswa(Request $request, $id)
    {
        $siswa = User::find($id);

        // Simpan data yang telah diedit
        $siswa->name = $request->name;
        $siswa->username = $request->username;
        $siswa->password = Hash::make($request['password']);
        $siswa->tempat_lahir = $request->tempat_lahir;
        $siswa->tanggal_lahir = $request->tanggal_lahir;
        $siswa->jenis_kelamin = $request->jenis_kelamin;
        $siswa->role = $request->role;
        $siswa->save();
        return redirect()->back();
    }

    /**
     * Store a newly created resource in storage.
     */
    public function store(Request $request)
    {
        $validatedData = $request->validate([
            'name' => 'required|max:255',
            'username' => 'required',
            'password' => 'required',
            'tempat_lahir' => 'required',
            'tanggal_lahir' => 'required',
            'jenis_kelamin' => 'required',
            'role' => 'required'
        ]);

        //$validatedData['password'] = bcrypt($validatedData['password']);
        $validatedData['password'] = Hash::make($validatedData['password']);

        $validatedData['role'] = 'siswa';

        //dd($validatedData);

        User::create($validatedData);

        $request->session()->flash('success','tambah data berhasil');

        return redirect('/siswa');
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy(string $id)
    {
        // Temukan data kegiatan berdasarkan ID
        $siswa = User::find($id);

        // Jika data siswa ditemukan, lakukan penghapusan
        if ($siswa) {
            $siswa->delete();
            return redirect('/siswa')->with('success', 'Data berhasil dihapus.'); // Redirect dengan pesan sukses
        } else {
            return redirect('/siswa')->with('error', 'Data tidak ditemukan.'); // Redirect dengan pesan error jika data tidak ditemukan
        }
   }
}
