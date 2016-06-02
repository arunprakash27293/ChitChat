<?php

use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class TblUsers extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('tbl_users', function (Blueprint $table) {
                        $table->increments('id');
                        $table->string('name');
                        $table->string('email');
                        $table->string('password');
                        
                        $table->string('status');
                        $table->string('phone_no');
});
    }

    
    public function down()
    {
        
    }
}
