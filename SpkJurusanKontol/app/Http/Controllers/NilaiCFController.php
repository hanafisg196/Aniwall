<?php

namespace App\Http\Controllers;

use App\Models\NilaiCF;
use Illuminate\Http\Request;

class NilaiCFController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index()
    {
        $data = NilaiCF::all();
        return view('nilaicf.index', compact('data'));
    }

    /**
     * Show the form for creating a new resource.
     */
    public function store(Request $request)
    {
        $validatedData = $request -> validate([
            "term" => "required",
            "cf" => "required",
        ]);

        NilaiCF::create($validatedData);
        return redirect("/nilaicf")->with("success","Data Berhasil Ditambahkan!");
    }

    /**
     * Store a newly created resource in storage.
     */
    public function tambahjawaban(Request $request, $id)
    {
        // // Validasi input nilaicf
        // $request->validate([
        //     'jawaban' => 'required',
        //     'nilai' => 'required',
        // ]);

        // // Simpan nilaicf ke dalam database
        // $soal = Soal::findOrFail($id);
        // $soal->nilaicf()->create([
        //     'jawaban' => $request->jawaban,
        //     'nilai' => $request->nilai,
        // ]);

        // Redirect atau tampilkan pesan sukses
        // return redirect('/nilaicf')->with('success', 'Jawaban berhasil ditambahkan.');
    }

    /**
     * Display the specified resource.
     */
    public function show(string $id)
    {
        //
    }

    /**
     * Show the form for editing the specified resource.
     */
    public function edit(string $id)
    {
        //
    }

    public function editnilaicf(Request $request, $id)
    {
        $nilaicf = NilaiCF::find($id);

        // Simpan data yang telah diedit
        $nilaicf->term = $request->term;
        $nilaicf->cf = $request->cf;
        $nilaicf->save();
        return redirect()->back();
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(Request $request, string $id)
    {
        //
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy(string $id)
    {
        // Temukan data kegiatan berdasarkan ID
        $nilaicf = NilaiCF::find($id);

        // Jika data soal ditemukan, lakukan penghapusan
        if ($nilaicf) {
            $nilaicf->delete();
            return redirect('/nilaicf')->with('success', 'Data berhasil dihapus.'); // Redirect dengan pesan sukses
        } else {
            return redirect('/nilaicf')->with('error', 'Data tidak ditemukan.'); // Redirect dengan pesan error jika data tidak ditemukan
        }
   }
}
