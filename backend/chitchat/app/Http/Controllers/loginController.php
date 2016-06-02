<?php
namespace App\Http\Controllers;

use Illuminate\Foundation\Bus\DispatchesJobs;
use Illuminate\Routing\Controller as BaseController;
use Illuminate\Foundation\Validation\ValidatesRequests;
use Illuminate\Foundation\Auth\Access\AuthorizesRequests;
use Illuminate\Foundation\Auth\Access\AuthorizesResources;
use Illuminate\Http\Request;
use Log;
use DB;
class loginController extends Controller{

	public function loginfun(Request $request)
	{

		$email=$request->Input('email');
		$password=$request->Input('password');
		Log::info("user with email ".$email." trying to login");

		$var=DB::table('tbl_users')->where('email',$email)->where('password',$password)->value('phone_no');

		$result['response']=false;

		if(isset($var))
		{
			$result['response']=true;
			$result['phone_no']=$var;
			Log::info("Authentication passed");
		}
		else
		{
			Log::info("Authentication Failed");
		}
		return response()->json($result);
	}
}
?>