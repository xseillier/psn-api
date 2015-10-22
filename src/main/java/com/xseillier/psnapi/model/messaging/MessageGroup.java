package com.xseillier.psnapi.model.messaging;

import java.util.Date;


/**
 *
 * @author xseillier
 * @version 1.0 6 oct. 2015
 */
public class MessageGroup {
	
	private LastMessage        mLatestMessage;
	private MessageGroupDetail mMessageGroupDetail;
	private String             mMessageGroupId;
	private Date               mMessageGroupModifiedDate;
	private int 		       mTotalUnseenMessages;
	private int                mTotalMessages;
	
	
	public void setMlatestMessage(Message aMlatestMessage) {
		mLatestMessage = aMlatestMessage;
	}

	public MessageGroupDetail getMessageGroupDetail() {
		return mMessageGroupDetail;
	}

	public void setMessageGroupDetail(MessageGroupDetail aMessageGroupDetail) {
		mMessageGroupDetail = aMessageGroupDetail;
	}

	public LastMessage getLatestMessage() {
		return mLatestMessage;
	}

	public void setLatestMessage(LastMessage aLatestMessage) {
		mLatestMessage = aLatestMessage;
	}

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

	public int getTotalUnseenMessages() {
		return mTotalUnseenMessages;
	}

	public void setTotalUnseenMessages(int aTotalUnseenMessages) {
		mTotalUnseenMessages = aTotalUnseenMessages;
	}

	public int getTotalMessages() {
		return mTotalMessages;
	}

	public void setTotalMessages(int aMtotalMessages) {
		mTotalMessages = aMtotalMessages;
	}   
}
