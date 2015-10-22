package com.xseillier.psnapi.model.param;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.xseillier.psnapi.model.enumeration.ImageSizeEnum;
import com.xseillier.psnapi.model.param.enumeration.PlatformEnum;
import com.xseillier.psnapi.model.param.enumeration.TrophySummaryOptionEnum;

/**
 * This object is used to pass param to trophy request
 * @author xseillier
 * @version 1.0 20 sept. 2015
 */
public class TrophyParam {

	
	private Locale			    mLocale;
	private List<ImageSizeEnum> mImageSizes                    = new ArrayList<>();
	private List<PlatformEnum>  mPlatfromEnums                 = new ArrayList<>();
	private List<TrophySummaryOptionEnum> mTrophySummaryOption = new ArrayList<>();
	
	public TrophyParam(  List<TrophySummaryOptionEnum> aTrophySummaryOption, List<ImageSizeEnum> aImageSizes, List<PlatformEnum> aPlatformEnums, Locale aLocale ){
		mLocale = aLocale;
		mTrophySummaryOption.addAll( aTrophySummaryOption );
		mImageSizes.addAll( aImageSizes );
		mPlatfromEnums.addAll( aPlatformEnums );
	}

	public Locale getLocale() {
		return mLocale;
	}

	public void setLocale(Locale aLocale) {
		mLocale = aLocale;
	}

	public List<ImageSizeEnum> getImageSize() {
		return mImageSizes;
	}

	public void setImageSize(List<ImageSizeEnum> aImageSizes) {
		mImageSizes = aImageSizes;
	}

	public void addImageSize( ImageSizeEnum aImageSize) {
		mImageSizes.add( aImageSize );
	}
	
	public List<PlatformEnum> getPlatfromEnums() {
		return mPlatfromEnums;
	}

	public void setPlatfromEnums(List<PlatformEnum> aPlatfromEnums) {
		mPlatfromEnums = aPlatfromEnums;
	}

	public void addPlatfromEnums(PlatformEnum aPlatfromEnum) {
		mPlatfromEnums.add( aPlatfromEnum );
	}
	
	public List<TrophySummaryOptionEnum> getTrophySummaryOption() {
		return mTrophySummaryOption;
	}

	public void setTrophySummaryOption(List<TrophySummaryOptionEnum> aTrophySummaryOption) {
		mTrophySummaryOption = aTrophySummaryOption;
	}
	
	

	/**
	 * ProfileRequestParam builder 
	 * default ProfileOption : ProfileOptionEnum.ONLINE_ID
	 * @author xseillier
	 *
	 */
	public static class TrophyParamBuilder
	{ 
		
		private Locale			    mLocale        = Locale.getDefault();
		private List<ImageSizeEnum> mImageSizes    = new ArrayList<>();
		private List<PlatformEnum>  mPlatfromEnums = new ArrayList<>();
		private List<TrophySummaryOptionEnum> mTrophySummaryOption = new ArrayList<>();
		
		public TrophyParamBuilder() {		
		}

		public TrophyParamBuilder setLocale(Locale aLocale) {
			mLocale = aLocale;
			return this;
		}
		
		public TrophyParamBuilder addImageSize( ImageSizeEnum aImageSize) {
			mImageSizes.add( aImageSize );
			return this;
		}
		
		public TrophyParamBuilder addPlatfrom(PlatformEnum aPlatfromEnum) {
			mPlatfromEnums.add( aPlatfromEnum );
			return this;
		}
		
		public TrophyParamBuilder addTrophySummaryOption(TrophySummaryOptionEnum aTrophySummaryOptionEnum) {
			mTrophySummaryOption.add( aTrophySummaryOptionEnum );
			return this;
		}

		public TrophyParam build() {
			return new TrophyParam( mTrophySummaryOption, mImageSizes, mPlatfromEnums,mLocale );
		}
	}


}
