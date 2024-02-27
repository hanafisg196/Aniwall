<?php

namespace Database\Seeders;

// use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;

class DatabaseSeeder extends Seeder
{
    /**
     * Seed the application's database.
     */
    public function run(): void
    {
    
        \App\Models\User::factory()->create([
            'name' => 'Admin',
            'username' => '12345671',
            'tempat_lahir' => 'padang',
            'tanggal_lahir' => '1999-02-23',
            'jenis_kelamin' => 'laki-laki',
            'jabatan' => 'Tata Usaha',
            'password' => bcrypt('rahasia'),
            'role' => 'admin'
        ]);

        \App\Models\User::factory()->create([
            'name' => 'Guru',
            'username' => '12345672',
            'tempat_lahir' => 'padang',
            'tanggal_lahir' => '1999-02-23',
            'jenis_kelamin' => 'laki-laki',
            'jabatan' => 'Guru',
            'password' => bcrypt('rahasia'),
            'role' => 'guru'
        ]);

        

        \App\Models\User::factory()->create([
            'name' => 'Siswa',
            'username' => '12345673',
            'tempat_lahir' => 'padang',
            'tanggal_lahir' => '1999-02-23',
            'jenis_kelamin' => 'laki-laki',
            'jabatan' => 'Siswa',
            'password' => bcrypt('rahasia'),
        ]);

       
    }
}
