package com.xseillier.psnapi.model.param;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.xseillier.psnapi.model.param.enumeration.MessagingOptionEnum;

/**
 * This object is used to pass param to profile request
 * @author xseillier
 * @version 0.3 beta 2 oct. 2015
 */
public class MessagingParam {

	private List<MessagingOptionEnum> mMessagingParams = new ArrayList<>();
	private Locale mLocale;
	private boolean mIgnoreCache = false;
	
	public MessagingParam( List<MessagingOptionEnum> aMessagingParams, Locale aLocal, boolean aIgnoreCache ){
		
		mMessagingParams.addAll( aMessagingParams );
		setLocale(aLocal);
		setIgnoreCache(aIgnoreCache);
	}


	public Locale getLocale() {
		return mLocale;
	}


	public void setLocale(Locale aLocale) {
		mLocale = aLocale;
	}


	public boolean isIgnoreCache() {
		return mIgnoreCache;
	}


	public void setIgnoreCache(boolean aIgnoreCache) {
		mIgnoreCache = aIgnoreCache;
	}

	public List<MessagingOptionEnum> getMessagingParams() {
		return mMessagingParams;
	}


	public void setMessagingParams(List<MessagingOptionEnum> aMessagingParams) {
		mMessagingParams = aMessagingParams;
	}
	
	/**
	 * MessagingParam builder 
	 
	 * @author xseillier
	 *
	 */
	
	public static class ProfileParamBuilder
	{ 
		private List<MessagingOptionEnum> mMessagingParams = new ArrayList<>();
		private Locale   mLocale;
		private boolean mIgnoreCache = false;
		
		
		public ProfileParamBuilder() {
			mMessagingParams = new ArrayList<>();
			mLocale = Locale.getDefault();
		}
		
		public ProfileParamBuilder addMessagingOption( MessagingOptionEnum aMessagingOption ) {
			mMessagingParams.add( aMessagingOption );
			return this;
		}
		
		public ProfileParamBuilder setLocale(Locale aLocale ) {
			mLocale = aLocale;
			return this;
		}
		
		public ProfileParamBuilder setIgnoreCache(Boolean aIgnoreCache ) {
			mIgnoreCache = aIgnoreCache;
			return this;
		}
		
		public MessagingParam build() {
			return new MessagingParam(  mMessagingParams, mLocale, mIgnoreCache );
		}
	}
}
