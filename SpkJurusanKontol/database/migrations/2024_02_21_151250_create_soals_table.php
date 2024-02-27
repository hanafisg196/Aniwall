<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    /**
     * Run the migrations.
     */
    public function up(): void
    {
        Schema::create('soals', function (Blueprint $table) {
            $table->id();
            $table->string('kode');
            $table->string('soal');
            $table->string('jenis');
            $table->unsignedBigInteger('batch_id')->nullable(false);
            $table->unsignedBigInteger('nilaicf_id')->nullable();
            $table->timestamps();

            $table->foreign("batch_id")->on("batch_soals")->references("id");
            $table->foreign("nilaicf_id")->on("nilai_c_f_s")->references("id");
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('soals');
    }
};
