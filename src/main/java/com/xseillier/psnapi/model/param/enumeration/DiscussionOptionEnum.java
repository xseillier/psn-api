package com.xseillier.psnapi.model.param.enumeration;

import com.xseillier.psnapi.enumeration.DataEnum;

/**
 *
 * @author xseillier
 * @version 1.0 16 sept. 2015
 */
public enum DiscussionOptionEnum implements DataEnum<String>{

		
	
	DEFAULT("@default"),
	MESSAGE_GROUP_ID("messageGroupId"),
	MESSAGE_GROUP_DETAIL("messageGroupDetail"),
	TOTAL_UNSEEN_MESSAGES("totalUnseenMessages"),
	TOTAL_MESSAGES("totalMessages"),
	LATEST_MESSAGE("latestMessage"),
	LAST_CHECK_DATE("lastCheckDate"),
	BODY("body"),
	MESSAGEGROUP("messageGroup");
	
	private String mData;
	
	private DiscussionOptionEnum( String aData ) {
		mData = aData;
	}
	
	public String getData() {
		return mData;
	}
}
