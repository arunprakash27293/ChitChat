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

class Contacts extends Controller
{
    public function resolvecontacts(Request $request)
	{
	
		$data = $request->getContent();
		$phone_no = json_decode($data)->numbers;
		
			//$c=count($phone_no);
			$contact=DB::table('user_table')
				->select('name','status','lastseen')
				->whereIn('phone_no',$phone_no)
				->get();
				$result =[];
		
		if(count($contact) != 0)
		{	
			$result['contact']=$contact;
			Log::info("Contact saved");
		}
		else
		{
			Log::info("Contactss Not Found");
		}
		return response()->json($result);
		
	}
}
