<?php

namespace App\Http\Controllers;

use App\Models\BatchSoal;
use App\Models\Soal;
use Illuminate\Bus\Batch;
use Illuminate\Http\Request;

class HomeController extends Controller
{
   public function index()
   {
     return redirect('/login');
   }


   public function home()
   {
     return view('ujian.index');
   }

   public function list()
   {
      $batch = BatchSoal::all();
       return view('ujian.list')->with('batch', $batch);
   }

   public function mulai()
   {



        // return json_encode($soal);

        return view('ujian.mulai');

       //$batchsoal = BatchSoal::find($id);
   }
}
