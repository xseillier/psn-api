package com.xseillier.psnapi.http;

import com.xseillier.psnapi.http.exception.LoginException;

/**
 *
 * @author xseillier
 * @version 1.0 15 sept. 2015
 */
public interface Login {

		/**
		 * Return the Branding Param extract from page at aUrl
		 * @param aUrl
		 * @param aCurrentTimestamp
		 * @param aRedirectUrl
		 * @param aClientId secret client id
		 * @param aScope
		 * @throws LoginException
		 */
		public String getBrandingParamStep1( String aUrl, long aCurrentTimestamp, String aRedirectUrl, String aClientId, String aScope ) throws LoginException;
		
		
		/**
		 * Valid Login/password if ok, return a url to get an login code (used for get an auth token)
		 * @param aUrl
		 * @param aBrandingParam
		 * @param aPSNLogin
		 * @param aPSNPassword
		 * @return
		 * @throws LoginException
		 */
		public String validLoginStep2( String aUrl, String aBrandingParam, String aPSNLogin, String aPSNPassword ) throws LoginException;
		
		/**
		 * Return the login code, used to get an auth token
		 * @param aUrl
		 * @return
		 * @throws LoginException
		 */
		public String getLoginCodeStep3( String aUrl ) throws LoginException;
}
