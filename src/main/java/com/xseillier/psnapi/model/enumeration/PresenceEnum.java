package com.xseillier.psnapi.model.enumeration;

import com.google.gson.annotations.SerializedName;
import com.xseillier.psnapi.enumeration.DataEnum;


/**
*
* @author xseillier
* @version 1.0 16 sept. 2015
*/
public enum PresenceEnum implements DataEnum<String>{

	@SerializedName("online")
	ONLINE("online"),
	@SerializedName("offline")
	OFFLINE("offline"),
	@SerializedName("standby")
	STANDBY("standby");
	
	
	private String mDate;
	
	private PresenceEnum ( String aData ) {
		mDate = aData;
	}
	
	public String getData(){
		return mDate;
	}
}
