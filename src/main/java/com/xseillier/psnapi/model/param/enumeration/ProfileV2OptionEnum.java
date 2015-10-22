package com.xseillier.psnapi.model.param.enumeration;

import com.xseillier.psnapi.enumeration.DataEnum;

/**
 *
 * @author xseillier
 * @version 1.0 16 sept. 2015
 */
public enum ProfileV2OptionEnum implements DataEnum<String>{

	
	ONLINE_ID("onlineId"),
	AVATAR_URLS("avatarUrls"),
	PLUS("plus"),
	ABOUT_ME("aboutMe"),
	LANGUAGES_USED("languagesUsed"),
	IS_OFFICIALLY_VERIFIED("isOfficiallyVerified"),
	PERSONAL_DETAIL_SHARING("personalDetailSharing"),
	PERSONAL_DETAIL_SHARING_REQUEST_MESSAGE_FLAG("personalDetailSharingRequestMessageFlag"),
	PRIMARY_ONLINE_STATUS("primaryOnlineStatus"),
	FRIEND_RELATION("friendRelation"),
	REQUEST_MESSAG_EFLAG("requestMessageFlag"),
	BLOCKING("blocking"),
	MUTUAL_FRIENDS_COUNT("mutualFriendsCount");


	
//	trophySummary(@default,progress,earnedTrophies),
//	personalDetail(@default,profilePictureUrls),
//	presences(@titleInfo,hasBroadcastData),

	
	

	
	private String mData;
	
	private ProfileV2OptionEnum( String aData ) {
		mData = aData;
	}
	
	public String getData() {
		return mData;
	}
}
