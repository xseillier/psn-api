package com.xseillier.psnapi.model.friend;

import java.util.Date;

import com.xseillier.psnapi.model.enumeration.RelationEnum;
import com.xseillier.psnapi.model.profile.BasicProfile;
import com.xseillier.psnapi.model.trophy.TrophySummary;

/**
*
* @author xseillier
* @version 1.0 16 sept. 2015
*/
public class FriendRequest extends BasicProfile{
	
	private RelationEnum  mRelation;
	private Presence      mPresence;
	private TrophySummary mTrophySummary;
	private boolean       mIsOfficiallyVerified;
	private Date          mRequestedDate;
	
//=============================================================================
// Accessor
//=============================================================================
	
			
	public RelationEnum getRelation() {
		return mRelation;
	}
	
	public Presence getPresence() {
		return mPresence;
	}
	
	
	public TrophySummary getTrophySummary() {
		return mTrophySummary;
	}
	
	public void setRelation(RelationEnum aRelation) {
		mRelation = aRelation;
	}
	
	public void setPresence(Presence aPresence) {
		mPresence = aPresence;
	}

	
	public void setTrophySummary(TrophySummary aTrophySummary) {
		mTrophySummary = aTrophySummary;
	}

	public boolean isOfficiallyVerified() {
		return mIsOfficiallyVerified;
	}

	public void setOfficiallyVerified(boolean aIsOfficiallyVerified) {
		mIsOfficiallyVerified = aIsOfficiallyVerified;
	}

	public Date getRequestedDate() {
		return mRequestedDate;
	}

	public void setRequestedDate(Date aRequestedDate) {
		mRequestedDate = aRequestedDate;
	}

		
}
