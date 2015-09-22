package com.xseillier.psnapi.properties;

import java.io.IOException;
import java.util.Properties;



public class PsnApiProperties extends Properties{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8514226613859931360L;
	
	
	private static String CONF_FILE = "com/xseillier/psnapi/properties/psn_api.properties";
	private static PsnApiProperties mPSNMessengerConfig = new PsnApiProperties();
	
	
	public static String PROPERTY_CLIENT_ID          = "psn.api.client.id";
	public static String PROPERTY_SCOPE              = "psn.api.scope";
	public static String PROPERTY_SCOPE_AUTH_TOKEN   = "psn.api.scope.authtoken";
	public static String PROPERTY_LOGIN_REDIRECT_URL = "psn.api.login.redirect.url";
	public static String PROPERTY_SERECT_KEY         = "psn.api.secret.key";
	
	
//=============================================================================
// Constructor
//=============================================================================
	
	private PsnApiProperties()
	{
		loadFile();
	}
	
//=============================================================================
// Accessor	
//=============================================================================

	public static PsnApiProperties getInstance()
	{
		return mPSNMessengerConfig;
	}
	
//=============================================================================
// Method	
//=============================================================================

	/**
	 * 
	 */
	private void loadFile()
	{
		try {
			load( PsnApiProperties.class.getClassLoader().getResourceAsStream( CONF_FILE ) );
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	/**
	 * 
	 * @param aPropertyKey
	 * @return
	 */
	public String getPropertyAsString( String aPropertyKey )
	{
		return this.getProperty( aPropertyKey );
	}
	
	/**
	 * 
	 * @return
	 */
	public String getClientId() {
		return getPropertyAsString( PROPERTY_CLIENT_ID );
	}
	
	/**
	 * 
	 * @return
	 */
	public String getScope() {
		return getPropertyAsString( PROPERTY_SCOPE );
	}
	
	/**
	 * 
	 * @return
	 */
	public String getScopeAuthToken() {
		return getPropertyAsString( PROPERTY_SCOPE_AUTH_TOKEN );
	}
	
	/**
	 * 
	 * @return
	 */
	public String getLoginRedirectUrl() {
		return getPropertyAsString( PROPERTY_LOGIN_REDIRECT_URL );
	}
	
	/**
	 * 
	 * @return
	 */
	public String getSecretKey() {
		return getPropertyAsString( PROPERTY_SERECT_KEY );
	}
}
