<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;

class LoginController extends Controller
{
    public function index()
    {
        return view('login.index');
    }

    public function doLogin(Request $request)
    {
        $user = $request->all();
        $this->validate($request,[
            "username"    => "required",
            "password" => "required"

        ]);

        if (auth()->attempt(['username' => $user['username'],
         'password' => $user['password']])) {

            $userRole = auth()->user()->role;
          
            if ( $userRole == 'admin' || $userRole == 'guru' ) {

                return redirect('/dashboard');
                
            } else {
                return redirect()->route('home');
            }
        } else {
           
            return redirect()->route('login')->with('error', 'Password atau Email Salah');
        }
    }

    public function doLogout()
    {
        Auth::logout();
        return redirect()->route('login');
    }

    

    
}
