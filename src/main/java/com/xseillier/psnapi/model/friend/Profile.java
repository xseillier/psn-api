package com.xseillier.psnapi.model.friend;

import java.util.List;

import com.xseillier.psnapi.model.enumeration.RelationEnum;
import com.xseillier.psnapi.model.trophy.TrophySummary;

/**
*
* @author xseillier
* @version 1.0 16 sept. 2015
*/
public class Profile {
	
	private String mAboutMe;	
	private List<Avatar> mAvatarUrls;
	private List<String> mLanguagesUsed;
	private String mNpId;
	private String mOnlineId;
	private int mPlus = -1;
	private String mRegion;
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
	
	
	public List<Avatar> getAvatarUrl() {
		return mAvatarUrls;
	}
		
	public List<String> getLanguagesUsed() {
		return mLanguagesUsed;
	}
	
	
	public String getNpId() {
		return mNpId;
	}
	
	
	public String getOnlineId() {
		return mOnlineId;
	}
	
	
	public int getPlus() {
		return mPlus;
	}

	
	public String getRegion() {
		return mRegion;
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
	
	public void setAvatarUrl(List<Avatar> aAvatarUrl) {
		mAvatarUrls = aAvatarUrl;
	}
	
	public void setLanguagesUsed(List<String> aLanguagesUsed) {
		mLanguagesUsed = aLanguagesUsed;
	}
	
	public void setNpId(String aNpId) {
		mNpId = aNpId;
	}
	
	public void setOnlineId(String aOnlineId) {
		mOnlineId = aOnlineId;
	}
	
	public void setPlus(int aPlus) {
		mPlus = aPlus;
	}
	
	public void setRegion(String aRegion) {
		mRegion = aRegion;
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
		return "Profile [mAboutMe=" + mAboutMe + ", mAvatarUrl=" + mAvatarUrls
				+ ", mLanguagesUsed=" + mLanguagesUsed + ", mNpId=" + mNpId
				+ ", mOnlineId=" + mOnlineId + ", mPlus=" + mPlus
				+ ", mRegion=" + mRegion + ", mRelation=" + mRelation
				+ ", mPresence=" + mPresence + ", mTrophySummary="
				+ mTrophySummary + ", mPanelBgc=" + mPanelBgc + ", mPanelUrl="
				+ mPanelUrl + "]";
	}
		
}
