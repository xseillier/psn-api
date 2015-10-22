package com.xseillier.psnapi.model.param.enumeration;

import com.xseillier.psnapi.enumeration.DataEnum;

/**
 *
 * @author xseillier
 * @version 1.0 16 sept. 2015
 */
public enum ProfileOptionEnum implements DataEnum<String>{

	ONLINE_ID("onlineId"),	        
	ABOUT_ME("aboutMe"),	  
	LANGUAGES_USED("languagesUsed"),	  
	PLUS("plus"),
	PERSONAL_DETAIL("@personalDetail"),   
	AVATAR_URLS("avatarUrls"),
	PRESENCE("presence"),
	RELATION("relation"),    
	IS_OFFICIALLY_VERIFIED("isOfficiallyVerified"),    
	TROPHY_SUMMARY("trophySummary"),  
	REQUEST_MESSAGE_FLAG("requestMessageFlag"),
	NP_TITLE_ICON_URL("npTitleIconUrl"),
	DEFAULT("@default"),    
	MUTUAL_FRIENDS_COUNT("mutualFriendsCount"),
	PROFILE("profile"),
	PERSONAL_DETAIL_DISPLAY_NAME("personalDetail.displayName"),
	REQUESTED_DATE("requestedDate");
	
	private String mData;
	
	private ProfileOptionEnum( String aData ) {
		mData = aData;
	}
	
	public String getData() {
		return mData;
	}
}
