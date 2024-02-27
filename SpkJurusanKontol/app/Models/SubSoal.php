<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class SubSoal extends Model
{
    use HasFactory;
    protected $primaryKey = 'id' ;
    protected $guarded = ['id'];

    public function soal()
    {
        return $this->belongsTo(Soal::class);
    }

    public function nilaicf()
    {
        return $this->belongsTo(NilaiCF::class, 'nilaicf_id','id');
    }
}
