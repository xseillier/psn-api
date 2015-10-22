package com.xseillier.psnapi.model.messaging;

import java.util.Date;
import java.util.List;

import com.xseillier.psnapi.model.ObjectPaginable;

/**
 *
 * @author xseillier
 * @version 1.0 6 oct. 2015
 */
public class DiscussionList extends ObjectPaginable<DiscussionPagination> {

	
	private Date mLastCheckDate;  	
	private List<MessageGroup> mMessageGroups;
	
	public Date getLastCheckDate() {
		return mLastCheckDate;
	}
	
	public void setLastCheckDate(Date aLastCheckDate) {
		mLastCheckDate = aLastCheckDate;
	}
	
	public List<MessageGroup> getMessageGroups() {
		return mMessageGroups;
	}

	public void setMessageGroups(List<MessageGroup> aMessageGroups) {
		mMessageGroups = aMessageGroups;
	}
}
