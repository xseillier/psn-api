package com.xseillier.psnapi.model.messaging;

import java.util.Date;

/**
 *
 * @author xseillier
 * @version 1.0 5 oct. 2015
 */
public class SendMessageResponse {

	
	 private String mMessageGroupId;
	 private Date   mMessageGroupModifiedDate;
	 private Long   mMessageUid;
	 private String mSentMessageId;
	 
	 
	 
	public String getMessageGroupId() {
		return mMessageGroupId;
	}
	public void setMessageGroupId(String aMessageGroupId) {
		mMessageGroupId = aMessageGroupId;
	}
	public Date getMessageGroupModifiedDate() {
		return mMessageGroupModifiedDate;
	}
	public void setMessageGroupModifiedDate(Date aMessageGroupModifiedDate) {
		mMessageGroupModifiedDate = aMessageGroupModifiedDate;
	}
	public Long getMessageUid() {
		return mMessageUid;
	}
	public void setMessageUid(Long aMessageUid) {
		mMessageUid = aMessageUid;
	}
	public String getSentMessageId() {
		return mSentMessageId;
	}
	public void setSentMessageId(String aSentMessageId) {
		mSentMessageId = aSentMessageId;
	}

}
