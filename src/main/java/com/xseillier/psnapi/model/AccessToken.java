package com.xseillier.psnapi.model;

/**
 *
 * @author xseillier
 * @version 1.0 15 sept. 2015
 */

public class AccessToken {

	private String access_token;
	private int    expires_in;
	private String refresh_token;
	private String scope;
	private String token_type;
	
//=============================================================================
// Accessor
//=============================================================================
	
	public String getAccessToken() {
		return access_token;
	}
	
	public void setAccessToken(String aAccessToken) {
		access_token = aAccessToken;
	}
	
	public int getExpiresIn() {
		return expires_in;
	}
	
	public void setExpiresIn(int aExpiresIn) {
		expires_in = aExpiresIn;
	}
	
	public String getRefreshToken() {
		return refresh_token;
	}
	
	public void setRefreshToken(String aRefreshToken) {
		refresh_token = aRefreshToken;
	}
	
	public String getScope() {
		return scope;
	}
	
	public void setScope(String aScope) {
		scope = aScope;
	}
	
	public String getTokenType() {
		return token_type;
	}
	
	public void setTokenType(String aTokenType) {
		token_type = aTokenType;
	}

}
