package com.xseillier.psnapi.model.friend;

import java.util.Date;

import com.xseillier.psnapi.model.enumeration.PresenceEnum;
import com.xseillier.psnapi.model.game.GameTitleInfo;
import com.xseillier.psnapi.model.param.enumeration.PlatformEnum;

/**
*
* @author xseillier
* @version 1.0 16 sept. 2015
*/
public class PrimaryInfo {

	
	private Date         mLastOnlineDate;
	private PresenceEnum mOnlineStatus;
	private PlatformEnum mPlatform;
	private String 		 mGameStatus;
	private GameTitleInfo mGameTitleInfo = new GameTitleInfo();
	
	
//=============================================================================
// Accessor
//=============================================================================

	
	public Date getLastOnlineDate() {
		return mLastOnlineDate;
	}
	
	public PresenceEnum getOnlineStatus() {
		return mOnlineStatus;
	}
	
	
	public GameTitleInfo getGameTitleInfo() {
		return mGameTitleInfo;
	}
	
	public void setLastOnlineDate(Date aLastOnlineDate) {
		mLastOnlineDate = aLastOnlineDate;
	}
	
	public void setOnlineStatus(PresenceEnum aOnlineStatus) {
		mOnlineStatus = aOnlineStatus;
	}
	
	public void setGameTitleInfo(GameTitleInfo aGameTitleInfo) {
		mGameTitleInfo = aGameTitleInfo;
	}

	public PlatformEnum getPlatform() {
		return mPlatform;
	}

	public void setPlatform(PlatformEnum aPlatform) {
		mPlatform = aPlatform;
	}
	
	public String getGameStatus() {
		return mGameStatus;
	}

	public void setGameStatus(String aGameStatus) {
		mGameStatus = aGameStatus;
	}
	
	@Override
	public String toString() {
		return "PrimaryInfo [mLastOnlineDate=" + mLastOnlineDate
				+ ", mOnlineStatus=" + mOnlineStatus + ", mGameTitleInfo="
				+ mGameTitleInfo + "]";
	}

}
