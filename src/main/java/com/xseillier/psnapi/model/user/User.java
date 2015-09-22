package com.xseillier.psnapi.model.user;

/**
*
* @author xseillier
* @version 1.0 15 sept. 2015
*/
public class User {

	
	 private String mAccountId;
	 private int    mAge;
	 private String mCommunityDomain;
	 private String mDateOfBirth; 
	 private String mLanguage;
	 private String mMAccountId; 
	 private ParentalControl mParentalControl;
	 private boolean mPs4Available;
	 private String  mRegion;
	 private boolean mSubaccount;
	 private String  mOnlineId;

//=============================================================================
// Accessor Method
//=============================================================================

	public String getAccountId() {
		return mAccountId;
	}
	public int getAge() {
		return mAge;
	}
	public String getCommunityDomain() {
		return mCommunityDomain;
	}
	public String getDateOfBirth() {
		return mDateOfBirth;
	}
	public String getLanguage() {
		return mLanguage;
	}
	public String getMAccountId() {
		return mMAccountId;
	}
	public ParentalControl getParentalControl() {
		return mParentalControl;
	}
	public boolean isPs4Available() {
		return mPs4Available;
	}
	public String  setRegion() {
		return mRegion;
	}
	public boolean isSubaccount() {
		return mSubaccount;
	}
	public String getOnlineId() {
		return mOnlineId;
	}
	
	@Override
	public String toString() {
		return "User [mAccountId=" + mAccountId + ", mAge=" + mAge
				+ ", mCommunityDomain=" + mCommunityDomain + ", mDateOfBirth="
				+ mDateOfBirth + ", mLanguage=" + mLanguage + ", mMAccountId="
				+ mMAccountId + ", mParentalControl=" + mParentalControl
				+ ", mPs4Available=" + mPs4Available + ", mRegion=" + mRegion
				+ ", mSubaccount=" + mSubaccount + ", mOnlineId=" + mOnlineId
				+ "]";
	}
}
