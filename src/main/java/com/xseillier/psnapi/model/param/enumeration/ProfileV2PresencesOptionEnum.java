package com.xseillier.psnapi.model.param.enumeration;

import com.xseillier.psnapi.enumeration.ProfileV2DataEnum;

/**
 *
 * @author xseillier
 * @version 1.0 16 sept. 2015
 */
public enum ProfileV2PresencesOptionEnum implements ProfileV2DataEnum<String>{


	TITLE_INFO("@titleInfo"),
	HAS_BROADCAST_DATA("hasBroadcastData");
		
	private String mData;
	
	private ProfileV2PresencesOptionEnum( String aData ) {
		mData = aData;
	}
	
	public String getData() {
		return mData;
	}

	@Override
	public String getParamName() {
		return "presences";
	}
}
