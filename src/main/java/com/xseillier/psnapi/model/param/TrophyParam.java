package com.xseillier.psnapi.model.param;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.xseillier.psnapi.model.enumeration.ImageSizeEnum;
import com.xseillier.psnapi.model.param.enumeration.PlatformEnum;

/**
 * This object is used to pass param to trophy request
 * @author xseillier
 * @version 1.0 20 sept. 2015
 */
public class TrophyParam {

	
	private Locale			    mLocale;
	private List<ImageSizeEnum> mImageSizes    = new ArrayList<>();
	private List<PlatformEnum>  mPlatfromEnums = new ArrayList<>();
	
	
	public TrophyParam( Locale aLocale, List<ImageSizeEnum> aImageSizes, List<PlatformEnum> aPlatformEnums ){
		mLocale = aLocale;
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
		
		public TrophyParamBuilder addPlatfromEnums(PlatformEnum aPlatfromEnum) {
			mPlatfromEnums.add( aPlatfromEnum );
			return this;
		}
		
		public TrophyParam build() {
			return new TrophyParam( mLocale, mImageSizes, mPlatfromEnums );
		}
	}


}
