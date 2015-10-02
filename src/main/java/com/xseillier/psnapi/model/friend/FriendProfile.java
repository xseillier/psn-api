package com.xseillier.psnapi.model.friend;

import java.util.List;

import com.xseillier.psnapi.model.enumeration.RelationEnum;
import com.xseillier.psnapi.model.profile.BasicProfile;
import com.xseillier.psnapi.model.trophy.TrophySummary;

/**
*
* @author xseillier
* @version 1.0 16 sept. 2015
*/
public class FriendProfile extends BasicProfile{
	
	private String mAboutMe;	
	private List<String> mLanguagesUsed;
	private RelationEnum mRelation;
	private Presence mPresence;
	private TrophySummary mTrophySummary;
	private String mPanelBgc;
	private String mPanelUrl;
	
//=============================================================================
// Accessor
//=============================================================================
	
	
	public String getAboutMe() {
		return mAboutMe;
	}
	
			
	public List<String> getLanguagesUsed() {
		return mLanguagesUsed;
	}
		
	
	public RelationEnum getRelation() {
		return mRelation;
	}
	
	public Presence getPresence() {
		return mPresence;
	}
	
	
	public TrophySummary getTrophySummary() {
		return mTrophySummary;
	}
	
	
	public String getPanelBgc() {
		return mPanelBgc;
	}
	
	public String getPanelUrl() {
		return mPanelUrl;
	}
	
	public void setAboutMe(String aAboutMe) {
		mAboutMe = aAboutMe;
	}
	
	
	public void setLanguagesUsed(List<String> aLanguagesUsed) {
		mLanguagesUsed = aLanguagesUsed;
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
	
	public void setPanelBgc(String aPanelBgc) {
		mPanelBgc = aPanelBgc;
	}
	
	public void setPanelUrl(String aPanelUrl) {
		mPanelUrl = aPanelUrl;
	}


	@Override
	public String toString() {
		return "FriendProfile [mAboutMe=" + mAboutMe + ", mLanguagesUsed="
				+ mLanguagesUsed + ", mRelation=" + mRelation + ", mPresence="
				+ mPresence + ", mTrophySummary=" + mTrophySummary
				+ ", mPanelBgc=" + mPanelBgc + ", mPanelUrl=" + mPanelUrl
				+ ", toString()=" + super.toString() + "]";
	}
		
}
