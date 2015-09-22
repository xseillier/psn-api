package com.xseillier.psnapi.model;
/**
 *
 * @author xseillier
 * @version 1.0 15 sept. 2015
 */
public class PsnContext {

	private static final int SECONDS = 1000;
	private AccessToken mAccessToken;
	private String   	mDuid;
	private String      mOnlineId;
	
	private long        mLastTokenUpdate = -1;
	
	public PsnContext( String aDuid ) {
		setDuid(aDuid);
	}
	
	public AccessToken getAccessToken() {
		return mAccessToken;
	}
	
	public void setAccessToken(AccessToken aAccessToken) {
		mAccessToken = aAccessToken;
	}
	
	public String getDuid() {
		return mDuid;
	}
	
	public void setDuid(String aDuid) {
		mDuid = aDuid;
	}
	
	
	public boolean haveAccessToken()
	{
		return mAccessToken != null;
	}

	public String getOnlineId() {
		return mOnlineId;
	}

	public void setOnlineId(String aOnlineId) {
		mOnlineId = aOnlineId;
	}

	public long getLastTokenUpdate() {
		return mLastTokenUpdate;
	}

	public void setLastTokenUpdate(long aLastTokenUpdate) {
		mLastTokenUpdate = aLastTokenUpdate;
	}
	
	public boolean isValidToken() {
		
		return mAccessToken != null && ( mLastTokenUpdate + ( mAccessToken.getExpiresIn() * SECONDS ) ) > System.currentTimeMillis();
	}
}
