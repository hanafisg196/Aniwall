<?php

namespace App\Http\Controllers;

use App\Models\BatchSoal;
use Illuminate\Bus\Batch;
use Illuminate\Http\Request;

class BatchSoalController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index(BatchSoal $batch)
    {
        $data = $batch->paginate(10);
        
        return view('batch.index')->with('data', $data);
    }

    /**
     * Show the form for creating a new resource.
     */
    public function create(Request $request)
    {


    }

    /**
     * Store a newly created resource in storage.
     */
    public function store(Request $request)
    {
        $validate = $request->validate([
            'title' =>'required',
            'kode' =>'required',
        ]);
      
        BatchSoal::create($validate);
        return redirect("/batch")->with("success","Data Berhasil Ditambahkan!");
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
    public function edit(Request $request, $id)
    {
        $bacth = BatchSoal::find($id);
        $validate = $request->validate([
            'title' =>'required',
            'kode' =>'required',
        ]);

        $bacth->update($validate);

       return redirect("/batch")->with("success","Data Berhasil Update!");
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
        $bacth = BatchSoal::find($id);
        $bacth->delete();
        return redirect("/batch")->with("success","Data Berhasil Dihapus!");
    }
}
