package com.xseillier.psnapi.model.param;

import java.util.ArrayList;
import java.util.List;

import com.xseillier.psnapi.model.enumeration.ImageSizeEnum;
import com.xseillier.psnapi.model.param.enumeration.ProfileOptionEnum;

/**
 * This object is used to pass param to profile request
 * @author xseillier
 * @version 1.0 20 sept. 2015
 */
public class ProfileParam {

	
	private List<ImageSizeEnum>     mAvatarSize    = new ArrayList<>();
	private List<ProfileOptionEnum> mProfileParams = new ArrayList<>();
	
	public ProfileParam( List<ProfileOptionEnum> aProfileParams, List<ImageSizeEnum> aAvatarSize ){
		mAvatarSize.addAll( aAvatarSize );
		mProfileParams.addAll( aProfileParams );
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
		
		
		public ProfileParamBuilder() {
			mAvatarSize    = new ArrayList<>();
			mProfileOption = new ArrayList<>();
			mProfileOption.add( ProfileOptionEnum.ONLINE_ID );
		}
		
		public ProfileParamBuilder addProfileOption( ProfileOptionEnum aProfileOptionEnum ) {
			mProfileOption.add( aProfileOptionEnum );
			return this;
		}
		
		public ProfileParamBuilder addImageSize( ImageSizeEnum aImageSizeEnum ) {
			mAvatarSize.add( aImageSizeEnum );
			addProfileOption( ProfileOptionEnum.AVATAR_URLS );
			return this;
		}
		
		public ProfileParam build() {
			return new ProfileParam(  mProfileOption, mAvatarSize );
		}
	}
}
