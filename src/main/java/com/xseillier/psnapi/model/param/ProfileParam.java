package com.xseillier.psnapi.model.param;

import java.util.ArrayList;
import java.util.List;

import com.xseillier.psnapi.model.enumeration.ImageSizeEnum;
import com.xseillier.psnapi.model.param.enumeration.FriendStatusEnum;
import com.xseillier.psnapi.model.param.enumeration.ProfileOptionEnum;

/**
 * This object is used to pass param to profile request
 * @author xseillier
 * @version 1.0 20 sept. 2015
 */
public class ProfileParam {

	private static final String PRIMARY            = "primary"; 
	private List<ImageSizeEnum>     mAvatarSize    = new ArrayList<>();
	private List<ProfileOptionEnum> mProfileParams = new ArrayList<>();
	private FriendStatusEnum 		mFriendStatus  = FriendStatusEnum.FRIEND;
	private String 					mPresenceType  = PRIMARY;
	
	public ProfileParam( List<ProfileOptionEnum> aProfileParams, List<ImageSizeEnum> aAvatarSize ){
		mAvatarSize.addAll( aAvatarSize );
		mProfileParams.addAll( aProfileParams );
	}

	public ProfileParam( List<ProfileOptionEnum> aProfileParams, List<ImageSizeEnum> aAvatarSize, FriendStatusEnum aFriendStatus  ){
		this( aProfileParams, aAvatarSize );
		setFriendStatus( aFriendStatus );
	}
		
	public List<ImageSizeEnum> getAvatarSize() {
		return mAvatarSize;
	}

	public void setAvatarSize(List<ImageSizeEnum> aAvatarSize) {
		mAvatarSize.addAll( aAvatarSize );
	}

	public void addAvatarSize( ImageSizeEnum aAvatarSize) {
		mAvatarSize.add( aAvatarSize );
	}
	
	public List<ProfileOptionEnum> getProfileParams() {
		return mProfileParams;
	}

	public void setProfileParams(List<ProfileOptionEnum> aProfileParams) {
		mProfileParams.addAll( aProfileParams );
	}
	
	public void addProfileParams(ProfileOptionEnum aProfileParams) {
		mProfileParams.add( aProfileParams );
	}
	
	
	public FriendStatusEnum getFriendStatus() {
		return mFriendStatus;
	}

	public void setFriendStatus(FriendStatusEnum aFriendStatus) {
		mFriendStatus = aFriendStatus;
		if( mFriendStatus == FriendStatusEnum.REQUESTED ) {
			mPresenceType = null;
		} else {
			mPresenceType = PRIMARY;
		}
	}


	public String getPresenceType() {
		return mPresenceType;
	}

	public void setPresenceType(String aPresenceType) {
		mPresenceType = aPresenceType;
	}


	/**
	 * ProfileRequestParam builder 
	 * default ProfileOption : ProfileOptionEnum.ONLINE_ID
	 * @author xseillier
	 *
	 */
	public static class ProfileParamBuilder
	{ 
		private List<ImageSizeEnum>     mAvatarSize;
		private List<ProfileOptionEnum> mProfileOption;
		private FriendStatusEnum 		mFriendStatus  = FriendStatusEnum.FRIEND;
		
		
		public ProfileParamBuilder() {
			mAvatarSize    = new ArrayList<>();
			mProfileOption = new ArrayList<>();
			mProfileOption.add( ProfileOptionEnum.ONLINE_ID );
		}
		
		public ProfileParamBuilder addProfileOption( ProfileOptionEnum aProfileOption ) {
			mProfileOption.add( aProfileOption );
			return this;
		}
		
		public ProfileParamBuilder addImageSize( ImageSizeEnum aImageSize ) {
			mAvatarSize.add( aImageSize );
			addProfileOption( ProfileOptionEnum.AVATAR_URLS );
			return this;
		}
		
		public ProfileParamBuilder setFriendStatus( FriendStatusEnum aFriendStatus ) {
			mFriendStatus = aFriendStatus;
			return this;
		}
		
		public ProfileParam build() {
			return new ProfileParam(  mProfileOption, mAvatarSize, mFriendStatus );
		}
	}
}
