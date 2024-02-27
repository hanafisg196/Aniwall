<?php

namespace App\Http\Controllers;

use App\Models\User;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Hash;

class PihakSekolahController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index()
    {
        return view('pihaksekolah.index',[
            'data' => User::latest()->get()
        ]);
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
            'jabatan' => 'required',
            'role' => 'required'
        ]);

        //$validatedData['password'] = bcrypt($validatedData['password']);
        $validatedData['password'] = Hash::make($validatedData['password']);

        //dd($validatedData);

        User::create($validatedData);

        $request->session()->flash('success','tambah data berhasil');

        return redirect('/pihaksekolah');
    }

    /**
     * Show the form for editing the specified resource.
     */
    public function editpihaksekolah(Request $request, $id)
    {
        $siswa = User::find($id);

        // Simpan data yang telah diedit
        $siswa->name = $request->name;
        $siswa->username = $request->username;
        $siswa->jabatan = $request->jabatan;
        $siswa->password = Hash::make($request['password']);
        $siswa->role = $request->role;
        $siswa->save();
        return redirect()->back();
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy(string $id)
    {
        //
    }
}
