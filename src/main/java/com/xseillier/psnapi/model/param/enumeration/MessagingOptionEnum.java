package com.xseillier.psnapi.model.param.enumeration;

import com.xseillier.psnapi.enumeration.DataEnum;

/**
 *
 * @author xseillier
 * @version 0.3 beta 2 oct. 2015
 */
public enum MessagingOptionEnum implements DataEnum<String>{

	
	DEFAULT("@default"),
	MESSAGE_GROUP_ID("messageGroupId"),
	MESSAGE_GROUP_DETAIL("messageGroupDetail"),
	TOTAL_UNSEEN_MESSAGES("totalUnseenMessages"),
	TOTAL_MESSAGES("totalMessages"),
	LATEST_MESSAGE("latestMessage"),
	LAST_CHECK_DATE("lastCheckDate");
	
	
	private String mData;
	
	private MessagingOptionEnum( String aData ) {
		mData = aData;
	}
	
	public String getData() {
		return mData;
	}
}
