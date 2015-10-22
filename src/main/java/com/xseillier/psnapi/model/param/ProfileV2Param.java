package com.xseillier.psnapi.model.param;

import java.util.ArrayList;
import java.util.List;

import com.xseillier.psnapi.model.enumeration.ImageSizeEnum;
import com.xseillier.psnapi.model.param.enumeration.ProfileV2OptionEnum;
import com.xseillier.psnapi.model.param.enumeration.ProfileV2PersonalDetailOptionEnum;
import com.xseillier.psnapi.model.param.enumeration.ProfileV2PresencesOptionEnum;
import com.xseillier.psnapi.model.param.enumeration.ProfileV2TrophySummaryOptionEnum;

/**
 * This object is used to pass param to profile request
 * @author xseillier
 * @version 1.0 20 sept. 2015
 */
public class ProfileV2Param {

	private List<ImageSizeEnum>       mAvatarSize                         = new ArrayList<>();
	private List<ProfileV2OptionEnum> mProfileParams                      = new ArrayList<>();
	private List<ProfileV2TrophySummaryOptionEnum> mTrophySummaryParams   = new ArrayList<>();
	private List<ProfileV2PresencesOptionEnum> mPresencesParams           = new ArrayList<>();
	private List<ProfileV2PersonalDetailOptionEnum> mPersonalDetailParams = new ArrayList<>();	
	private ImageSizeEnum mTitleIconSize; 
	
	public ProfileV2Param( List<ProfileV2OptionEnum> aProfileParams,
			List<ProfileV2TrophySummaryOptionEnum> aTrophySummaryParams,
			List<ProfileV2PresencesOptionEnum> aPresencesParams,
			List<ProfileV2PersonalDetailOptionEnum> aPersonalDetailParams,
			List<ImageSizeEnum> aAvatarSize,
			ImageSizeEnum aTitleIconSize ){
			
		mAvatarSize.addAll( aAvatarSize );
		mProfileParams.addAll( aProfileParams );
		mTrophySummaryParams.addAll( aTrophySummaryParams );
		mPresencesParams.addAll( aPresencesParams );
		mPersonalDetailParams.addAll( aPersonalDetailParams );
		setTitleIconSize(aTitleIconSize);
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
	
	public List<ProfileV2OptionEnum> getProfileParams() {
		return mProfileParams;
	}

	public void setProfileParams(List<ProfileV2OptionEnum> aProfileParams) {
		mProfileParams.addAll( aProfileParams );
	}
	
	public void addProfileParams(ProfileV2OptionEnum aProfileParams) {
		mProfileParams.add( aProfileParams );
	}
	
	public List<ProfileV2TrophySummaryOptionEnum> getTrophySummaryParams() {
		return mTrophySummaryParams;
	}

	public void setTrophySummaryParams(
			List<ProfileV2TrophySummaryOptionEnum> aTrophySummaryParams) {
		mTrophySummaryParams = aTrophySummaryParams;
	}

	public List<ProfileV2PresencesOptionEnum> getPresencesParams() {
		return mPresencesParams;
	}

	public void setPresencesParams(
			List<ProfileV2PresencesOptionEnum> aPresencesParams) {
		mPresencesParams = aPresencesParams;
	}

	public List<ProfileV2PersonalDetailOptionEnum> getPersonalDetailParams() {
		return mPersonalDetailParams;
	}

	public void setPersonalDetailParams(
			List<ProfileV2PersonalDetailOptionEnum> aPersonalDetailParams) {
		mPersonalDetailParams = aPersonalDetailParams;	
	}


	public ImageSizeEnum getTitleIconSize() {
		return mTitleIconSize;
	}


	public void setTitleIconSize(ImageSizeEnum aTitleIconSize) {
		mTitleIconSize = aTitleIconSize;
	}

	/**
	 * ProfileRequestParam builder 
	 * default ProfileOption : ProfileOptionEnum.ONLINE_ID
	 * @author xseillier
	 *
	 */
	public static class ProfileV2ParamBuilder
	{ 
		private List<ImageSizeEnum>     mAvatarSize                           = new ArrayList<>();
		private List<ProfileV2OptionEnum> mProfileOption                      = new ArrayList<>();
		private List<ProfileV2TrophySummaryOptionEnum> mTrophySummaryParams   = new ArrayList<>();
		private List<ProfileV2PresencesOptionEnum> mPresencesParams           = new ArrayList<>();
		private List<ProfileV2PersonalDetailOptionEnum> mPersonalDetailParams = new ArrayList<>();
		
		private ImageSizeEnum mTitleIconSize = ImageSizeEnum.SMALL;
		
		public ProfileV2ParamBuilder() {
			mProfileOption.add( ProfileV2OptionEnum.ONLINE_ID );
		}
		
		public ProfileV2ParamBuilder addProfileOption( ProfileV2OptionEnum aProfileOption ) {
			mProfileOption.add( aProfileOption );
			return this;
		}
		
		public ProfileV2ParamBuilder addTrophySummaryOption( ProfileV2TrophySummaryOptionEnum aProfileTrophySummaryOption ) {
			mTrophySummaryParams.add( aProfileTrophySummaryOption );
			return this;
		}
		
		public ProfileV2ParamBuilder addPresencesOption( ProfileV2PresencesOptionEnum aProfileV2PresencesOptionEnum ) {
			mPresencesParams.add( aProfileV2PresencesOptionEnum );
			return this;
		}
		
		public ProfileV2ParamBuilder addPersonalDetailOption( ProfileV2PersonalDetailOptionEnum aProfileV2PersonalDetailOptionEnum ) {
			mPersonalDetailParams.add( aProfileV2PersonalDetailOptionEnum );
			return this;
		}
		
		public ProfileV2ParamBuilder addImageSize( ImageSizeEnum aImageSize ) {
			mAvatarSize.add( aImageSize );
			return this;
		}
		
		public ProfileV2ParamBuilder setTitleIconSize(ImageSizeEnum aTitleIconSize) {
			mTitleIconSize = aTitleIconSize;
			return this;
		}
		
		public ProfileV2Param build() {
			
			if( mAvatarSize.size() > 0 ) {
				addProfileOption( ProfileV2OptionEnum.AVATAR_URLS );
				if( mPersonalDetailParams.size() > 0 ) {
					addPersonalDetailOption( ProfileV2PersonalDetailOptionEnum.PROFILE_PICTURE_URLS );
				}
			}
			
			return new ProfileV2Param( mProfileOption, mTrophySummaryParams, mPresencesParams, mPersonalDetailParams, mAvatarSize, mTitleIconSize );
		}
	}
}
