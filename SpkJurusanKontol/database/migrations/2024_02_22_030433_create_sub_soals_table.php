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
        Schema::create('sub_soals', function (Blueprint $table) {
            $table->id();
            $table->unsignedBigInteger('nilaicf_id')->nullable();
            $table->unsignedBigInteger('soal_id');
            $table->timestamps();

            $table->foreign("soal_id")->on("soals")->references("id");
            $table->foreign("nilaicf_id")->on("nilai_c_f_s")->references("id");

        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('sub_soals');
    }
};
