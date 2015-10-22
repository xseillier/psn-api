package com.xseillier.psnapi.model.param.enumeration;

import com.xseillier.psnapi.enumeration.ProfileV2DataEnum;

/**
 *
 * @author xseillier
 * @version 1.0 16 sept. 2015
 */
public enum ProfileV2TrophySummaryOptionEnum implements ProfileV2DataEnum<String>{

	DEFAULT("@default"),
	PROGRESS("progress"),
	EARNED_TROPHIES("earnedTrophies");
	
	private String mData;
	
	private ProfileV2TrophySummaryOptionEnum( String aData ) {
		mData = aData;
	}
	
	public String getData() {
		return mData;
	}

	@Override
	public String getParamName() {
		return "trophySummary";
	}
}
