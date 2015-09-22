package com.xseillier.psnapi.duid.model.impl;

import java.util.Random;

import com.xseillier.psnapi.duid.model.AndroidDevice;

public class FakeAndroidDevice implements AndroidDevice{

	
	private String mManufacture;
	private String mCodeName;
	private String mFakeDeviceId;
	
	private final static int ID_LENGTH = 15; 
//=============================================================================
// Constructor
//=============================================================================
	
	public FakeAndroidDevice(  String aCodeName, String aManifacture )
	{
		mManufacture = aManifacture;
		mCodeName    = aCodeName;
		mFakeDeviceId = generateFakeId();
	}
//=============================================================================
// Methods
//=============================================================================

	
	private String generateFakeId()
	{
		Random oRandom = new Random( System.currentTimeMillis() );
		StringBuilder oResponse = new StringBuilder();
		
		for( int i = 0; i < ID_LENGTH; i++)
		{
			oResponse.append( Math.abs( oRandom.nextInt() ) % 10 );
		}
		return oResponse.toString();
		
	}
	
	public String getManufacturer() {
		return mManufacture;
	}

	
	public String getCodeName() {
		return mCodeName;
	}


	public String getDeviceId() {
		return mFakeDeviceId;
	}

	
	public String toString()
	{
		StringBuilder oResponse = new StringBuilder();	
		oResponse.append( "Manufacture = " );	
		oResponse.append( mManufacture );
		oResponse.append( " Device = " );
		oResponse.append( mCodeName );				
		return oResponse.toString();
	}

}
