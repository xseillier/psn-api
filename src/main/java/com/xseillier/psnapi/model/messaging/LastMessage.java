package com.xseillier.psnapi.model.messaging;

import java.util.Date;

/**
 *
 * @author xseillier
 * @version 1.0 6 oct. 2015
 */
public class LastMessage {

	
    private String mBody;
    private MessageKindEnum mMessageKind;
    private long mMessageUid;
    private Date mReceivedDate;
    private boolean mSeenFlag;
    private String mSenderOnlineId;
    
    
	public String getBody() {
		return mBody;
	}
	public void setBody(String aBody) {
		mBody = aBody;
	}
	public MessageKindEnum getMessageKind() {
		return mMessageKind;
	}
	public void setMessageKind(MessageKindEnum aMessageKind) {
		mMessageKind = aMessageKind;
	}
	public long getMessageUid() {
		return mMessageUid;
	}
	public void setMessageUid(long aMessageUid) {
		mMessageUid = aMessageUid;
	}
	public Date getReceivedDate() {
		return mReceivedDate;
	}
	public void setReceivedDate(Date aReceivedDate) {
		mReceivedDate = aReceivedDate;
	}
	public boolean isSeenFlag() {
		return mSeenFlag;
	}
	public void setSeenFlag(boolean aSeenFlag) {
		mSeenFlag = aSeenFlag;
	}
	public String getSenderOnlineId() {
		return mSenderOnlineId;
	}
	public void setSenderOnlineId(String aSenderOnlineId) {
		mSenderOnlineId = aSenderOnlineId;
	}
}
