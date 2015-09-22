package com.xseillier.psnapi.model.friend;



public class Presence {

	
	private PrimaryInfo mPrimaryInfo = new PrimaryInfo();

	
	//private PresenceEnum mOnlineStatus = PresenceEnum.OFFLINE;
   // private PlatformEnum mPlatform;

//=============================================================================
// Accessor
//=============================================================================


	public PrimaryInfo getPrimaryInfo() {
		return mPrimaryInfo;
	}

	
//	public PresenceEnum getOnlineStatus() {
//		return mOnlineStatus;
//	}
//
//	
//	public PlatformEnum getPlatform() {
//		return mPlatform;
//	}

	
	public void setPrimaryInfo(PrimaryInfo aPrimaryInfo) {
		mPrimaryInfo = aPrimaryInfo;
	}

	
//	public void setOnlineStatus(PresenceEnum aOnlineStatus) {
//		mOnlineStatus = aOnlineStatus;
//	}
//
//	
//	public void setPlatform(PlatformEnum aPlatform) {
//		mPlatform = aPlatform;
//	}


	@Override
	public String toString() {
		return "Presence [mPrimaryInfo=" + mPrimaryInfo + "]";
	}
	
}
