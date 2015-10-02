package com.xseillier.psnapi.model.profile;

import java.util.List;

/**
*
* @author xseillier
* @version 1.0 30 sept. 2015
*/
public class BasicProfile {
	
	protected List<Avatar> mAvatarUrls;
	protected String mNpId;
	protected String mOnlineId;
	protected int mPlus = -1;
	protected String mRegion;
	
//=============================================================================
// Accessor
//=============================================================================
	
	
	public List<Avatar> getAvatarUrl() {
		return mAvatarUrls;
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
	
	public void setAvatarUrl(List<Avatar> aAvatarUrl) {
		mAvatarUrls = aAvatarUrl;
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

	@Override
	public String toString() {
		return "BasicProfile [mAvatarUrls=" + mAvatarUrls + ", mNpId=" + mNpId
				+ ", mOnlineId=" + mOnlineId + ", mPlus=" + mPlus
				+ ", mRegion=" + mRegion + "]";
	}
		
}
