package com.xseillier.psnapi.model.messaging;

import java.util.List;


/**
 *
 * @author xseillier
 * @version 1.0 6 oct. 2015
 */
public class Discussion {
	
	private MessageGroup  mMessageGroup;
	private List<Message> mMessages;
	private int mStart;
	private int mSize;
	private int mTotalResults;
	
	public MessageGroup getMessageGroup() {
		return mMessageGroup;
	}
	public void setMessageGroup(MessageGroup aMessageGroups) {
		mMessageGroup = aMessageGroups;
	}
	public List<Message> getMessages() {
		return mMessages;
	}
	public void setMessages(List<Message> aMessages) {
		mMessages = aMessages;
	}
	public int getStart() {
		return mStart;
	}
	public void setStart(int aStart) {
		mStart = aStart;
	}
	public int getSize() {
		return mSize;
	}
	public void setSize(int aSize) {
		mSize = aSize;
	}
	public int getTotalResults() {
		return mTotalResults;
	}
	public void setTotalResults(int aTotalResults) {
		mTotalResults = aTotalResults;
	}
  
   
}
