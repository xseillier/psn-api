package com.xseillier.psnapi.model.profile;

import com.xseillier.psnapi.model.enumeration.ImageSizeEnum;

/**
 *
 * @author xseillier
 * @version 1.0 22 sept. 2015
 */
public class Avatar {

	private ImageSizeEnum mSize;
	private String		  mAvatarUrl;
	
//=============================================================================
// ACCESSORS
//=============================================================================
	
	public ImageSizeEnum getImageSize() {
		return mSize;
	}
	
	public void setImageSize(ImageSizeEnum aImageSize) {
		mSize = aImageSize;
	}
	
	public String getAvatarUrl() {
		return mAvatarUrl;
	}
	
	public void setAvatarUrl(String aAvatarUrl) {
		mAvatarUrl = aAvatarUrl;
	}
	
}
