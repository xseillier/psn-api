package com.xseillier.psnapi.model.param;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.xseillier.psnapi.model.param.enumeration.DiscussionOptionEnum;

/**
 * This object is used to pass param to discussion request
 * @author xseillier
 * @version 0.3 06 oct. 2015
 */
public class DiscussionParam {

	private List<DiscussionOptionEnum> mDiscussionParams = new ArrayList<>();
	private Locale mLocale;
	private Long mSinceMessageUid = null;
	
	
	public DiscussionParam(  List<DiscussionOptionEnum> aDiscussionParams, Locale aLocale, Long aSinceMessageUid ){
		mDiscussionParams.addAll( aDiscussionParams );
		mLocale          = aLocale;
		mSinceMessageUid = aSinceMessageUid;
	}
	
	public DiscussionParam(  List<DiscussionOptionEnum> aDiscussionParams, Locale aLocale){
		this( aDiscussionParams, aLocale, null);
	}
	
	public List<DiscussionOptionEnum> getDiscussionParams() {
		return mDiscussionParams;
	}
	
	public void setDiscussionParams(List<DiscussionOptionEnum> aDiscussionParams) {
		mDiscussionParams = aDiscussionParams;
	}
	
	
	public Locale getLocale() {
		return mLocale;
	}

	public void setLocale(Locale aLocale) {
		mLocale = aLocale;
	}



	public Long getSinceMessageUid() {
		return mSinceMessageUid;
	}

	public void setSinceMessageUid(Long aSinceMessageUid) {
		mSinceMessageUid = aSinceMessageUid;
	}



	/**
	 * DiscussionParam builder 
	 * @author xseillier
	 *
	 */
	public static class DiscussionParamBuilder
	{ 
		private List<DiscussionOptionEnum> mDiscussionParams;
		private Locale mLocale;
		private Long mSinceMessageUid = null;
		
		public DiscussionParamBuilder() {
			mDiscussionParams = new ArrayList<>();
		//	mDiscussionParams.add( DiscussionOptionEnum.DEFAULT );
			mLocale = Locale.getDefault();
		}
		
		public DiscussionParamBuilder addDiscussionOption( DiscussionOptionEnum aDiscussionOption ) {
			mDiscussionParams.add( aDiscussionOption );
			return this;
		}
		
		
		public DiscussionParamBuilder setLocale( Locale aLocale) {
			mLocale = aLocale;
			return this;
		}
		
		public DiscussionParamBuilder setSinceMessageUid( long aSinceMessageUid) {
			mSinceMessageUid = aSinceMessageUid;
			return this;
		}
		
		public DiscussionParam build() {
			return new DiscussionParam(  mDiscussionParams, mLocale );
		}
	}

}
