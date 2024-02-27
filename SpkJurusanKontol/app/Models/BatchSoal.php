<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class BatchSoal extends Model
{
    use HasFactory;
    protected $primaryKey = 'id';
    protected $guarded = ['id'];


    public function soal()
    {
        return $this->hasMany(Soal::class, 'batch_id','id');
    }
}
