package com.xseillier.psnapi.duid.model.impl;

import com.xseillier.psnapi.duid.model.AndroidDevice;



public class AndroidDeviceImpl implements AndroidDevice {

	
	private String mManufacturer;
	private String mCodeName;
	private String mDeviceId;

//=============================================================================
// Constructor
//=============================================================================
	
	public AndroidDeviceImpl( String aManufacurer, String aCodename, String aDeviceId )
	{
		mManufacturer = aManufacurer;
		mCodeName = aCodename;
		mDeviceId = aDeviceId;
	}
	
	
//=============================================================================
// ACCESSOR
//=============================================================================
	
	public String getManufacturer() {
		return mManufacturer;
	}

	public String getCodeName() {
		return mCodeName;
	}

	public String getDeviceId() {
		return mDeviceId;
	}

}
