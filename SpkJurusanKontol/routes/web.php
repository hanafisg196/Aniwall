<?php

use Illuminate\Support\Facades\Route;
use App\Http\Controllers\HomeController;
use App\Http\Controllers\SoalController;
use App\Http\Controllers\LoginController;
use App\Http\Controllers\SiswaController;
use App\Http\Controllers\UjianController;
use App\Http\Controllers\NilaiCFController;
use App\Http\Controllers\SubSoalController;
use App\Http\Controllers\BatchSoalController;
use App\Http\Controllers\PihakSekolahController;


/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider and all of them will
| be assigned to the "web" middleware group. Make something great!
|
*/



Route::middleware('guest')->group(function () {
    Route::get('/', [HomeController::class, 'index']);
    Route::get('/login', [LoginController::class, 'index'])->name('login');
    Route::post('/login', [LoginController::class, 'doLogin']);

});


Route::group(['middleware' => ['auth', 'checkrole:admin']], function () {
    Route::get('/dashboard', function () {
        return view('dashboard.index');
    });
    Route::post('/logout', [LoginController::class, 'doLogout']);
    Route::resource('/siswa',SiswaController::class);
    Route::post('/editsiswa/{id}', [SiswaController::class,'editsiswa']);
    Route::resource('/pihaksekolah',PihakSekolahController::class);
    Route::post('/editpihaksekolah/{id}', [PihakSekolahController::class,'editpihaksekolah']);
    Route::resource('/soal',SoalController::class);
    Route::post('/editsoal/{id}', [SoalController::class,'editsoal']);
    Route::resource('/subsoal',SubSoalController::class);
    Route::post('/tambahjawaban/{id}', [SubSoalController::class,'tambahjawaban']);
    Route::resource('/nilaicf',NilaiCFController::class);
    Route::post('/editnilaicf/{id}', [NilaiCFController::class,'editnilaicf']);
    Route::resource('/batch',BatchSoalController::class);
    Route::post('/batch/{id}', [BatchSoalController::class,'edit']);

});


Route::group(['middleware' => ['auth', 'checkrole:guru']], function () {
    Route::get('/dashboard', function () {
        return view('dashboard.index');
    });
    Route::post('/logout', [LoginController::class, 'doLogout']);
    Route::resource('/ujian',UjianController::class);


});

Route::middleware(['auth'])->group(function () {
    Route::get('/home', [HomeController::class, 'home'])->name('home');
    Route::get('/ujian', [HomeController::class, 'list']);
    Route::get('/kerjakan', [HomeController::class, 'mulai'])->name('kerjakan.edit');

    Route::get('/kerjakan/{id}/edit?page={page}', [HomeController::class, 'mulai'])
        ->name('kerjakan.edit.page')
        ->middleware('bindings');

});





