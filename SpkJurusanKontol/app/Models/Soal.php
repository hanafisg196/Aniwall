<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Soal extends Model
{
    use HasFactory;

    protected $primaryKey = 'id';
    protected $guarded = ['id'];

    public function subsoal()
    {
        return $this->hasMany(SubSoal::class);
    }

    public function batch()
    {
        return $this->belongsTo(BatchSoal::class, 'batch_id','id');
    }

    public function nilaicf()
    {
        return $this->belongsTo(NilaiCF::class, 'nilaicf_id','id');
    }
    

}
