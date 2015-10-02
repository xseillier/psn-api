package com.xseillier.psnapi.impl;

import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

import com.google.gson.JsonSyntaxException;
import com.squareup.okhttp.OkHttpClient;
import com.xseillier.psnapi.PsnApi;
import com.xseillier.psnapi.http.Login;
import com.xseillier.psnapi.http.PsnApiService;
import com.xseillier.psnapi.http.cst.PsnUrlCst;
import com.xseillier.psnapi.http.cst.UrlParamCst;
import com.xseillier.psnapi.http.exception.AccessDeniedByPrivacyLevelException;
import com.xseillier.psnapi.http.exception.LoginException;
import com.xseillier.psnapi.http.exception.PsnErrorException;
import com.xseillier.psnapi.http.exception.PsnExceptionFactory;
import com.xseillier.psnapi.http.impl.LoginImpl;
import com.xseillier.psnapi.http.interceptor.CountRequestInterceptor;
import com.xseillier.psnapi.http.interceptor.RateLimiterInterceptor;
import com.xseillier.psnapi.jsonparser.GsonParser;
import com.xseillier.psnapi.model.AccessToken;
import com.xseillier.psnapi.model.PsnContext;
import com.xseillier.psnapi.model.PsnError;
import com.xseillier.psnapi.model.ServiceUrl;
import com.xseillier.psnapi.model.block.BlockList;
import com.xseillier.psnapi.model.friend.FriendList;
import com.xseillier.psnapi.model.friend.FriendProfile;
import com.xseillier.psnapi.model.friend.ProfileList;
import com.xseillier.psnapi.model.param.BlockPaginationParam;
import com.xseillier.psnapi.model.param.FriendPaginationParam;
import com.xseillier.psnapi.model.param.ProfileParam;
import com.xseillier.psnapi.model.param.RequestMessage;
import com.xseillier.psnapi.model.param.TrophyPaginationParam;
import com.xseillier.psnapi.model.param.TrophyParam;
import com.xseillier.psnapi.model.trophy.TrophyGroupsDetailsResponse;
import com.xseillier.psnapi.model.trophy.TrophyGroupsResponse;
import com.xseillier.psnapi.model.trophy.TrophyTitleList;
import com.xseillier.psnapi.model.user.User;
import com.xseillier.psnapi.properties.PsnApiProperties;
import com.xseillier.psnapi.utils.UrlUtils;

/**
 *
 * @author xseillier
 * @version 1.0 15 sept. 2015
 */
public class PsnApiImpl implements PsnApi {
	
	public static double NO_RATE_LIMIT_CONTROL           = -1; 
	public static double DEFAULT_RATE_LIMIT              = 0.1111111111111111; //Request by second	
	private static final String AUTH_TOKEN_GRANT         = "authorization_code";
	private static final String REFRESH_AUTH_TOKEN_GRANT = "refresh_token";
	private static final String STATE                    = "x";
	
	private OkHttpClient  mOkHttpClient;
	private PsnApiService mPSNApiService;	
	private PsnContext    mPsnContext;
	
	private boolean DEBUG = false;
	
	/**
	 * 
	 * @param aDuid PSN unique id
	 */
	public PsnApiImpl(  String aDuid ) {
		this( new PsnContext( aDuid ) );
	}
	
	/**
	 * 
	 * @param aPsnContext
	 */
	public PsnApiImpl( PsnContext aPsnContext ) {
		this( aPsnContext, DEFAULT_RATE_LIMIT );
	}
	
	/**
	 * 
	 * @param aDuid PSN unique id
	 * @param aLimitRate if is less as 0 -> no limit rate control by api 
	 */
	public PsnApiImpl(  String aDuid, double aLimitRate  ) {
		this( new PsnContext( aDuid ), aLimitRate );
	}
	
	/**
	 * 
	 * @param aPsnContext
	 * @param aLimitRate if is less as 0 -> no limit rate control by api 
	 */
	public PsnApiImpl( PsnContext aPsnContext, double aLimitRate ) {
		init( aLimitRate  );
		setPsnContext( aPsnContext );
	}
	
	/**
	 * 
	 */
	private void init( double aLimitRate  ){
		initOkHttpClient( aLimitRate );
		initPSNApiService();
	}
	
	/**
	 * init ok http client
	 */
	private void initOkHttpClient( double aLimitRate ){
		mOkHttpClient = new OkHttpClient();
		CookieManager cookieManager = new CookieManager();
		cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
		mOkHttpClient.setCookieHandler(cookieManager);
		if( aLimitRate > 0 ) {
			mOkHttpClient.interceptors().add( new RateLimiterInterceptor( aLimitRate ) );
		}
		if( DEBUG ) {
			mOkHttpClient.interceptors().add( new CountRequestInterceptor() );
		}
	}
	
	/**
	 * init retrofit service
	 */
	private void initPSNApiService(){
		
		Retrofit oRetrofit = new Retrofit.Builder()
		.client( mOkHttpClient )
		.addConverterFactory( GsonConverterFactory.create( GsonParser.getGsonParserInstance() ) )
	    .baseUrl( PsnUrlCst.URL_BASE )
	    .build();	
		mPSNApiService = oRetrofit.create( PsnApiService.class );
	}
	
	
	
	/**
	 * 
	 */
	@Override
	public void setPsnContext(PsnContext aPsnContext) {
		mPsnContext = aPsnContext;
	}

	/**
	 * 
	 */
	@Override
	public PsnContext getPsnContext() {
		return mPsnContext;
	}

//=============================================================================
//
// Auth METHODS
//
//=============================================================================

	/**
	 * 
	 * @param aPSNLogin
	 * @param aPSNPassword
	 * @throws LoginException
	 * @throws IOException
	 * @throws PsnErrorException
	 */
	@Override
	public void login(String aPSNLogin, String aPSNPassword) throws LoginException, IOException, PsnErrorException {
		
		Login oLogin = new LoginImpl( mOkHttpClient );
		
		String oBrandingParam = oLogin.getBrandingParamStep1( PsnUrlCst.URL_LOGIN_STEP_1, 
				System.currentTimeMillis(), 
				PsnApiProperties.getInstance().getLoginRedirectUrl(),
				PsnApiProperties.getInstance().getClientId(),
				PsnApiProperties.getInstance().getScope() );
		
		String oNextUrl       = oLogin.validLoginStep2( PsnUrlCst.URL_LOGIN_STEP_2, oBrandingParam, aPSNLogin, aPSNPassword);
		String oLoginCode     = oLogin.getLoginCodeStep3( oNextUrl );
		
		Call<AccessToken> oAccessTokenCall = mPSNApiService.getAuthToken( AUTH_TOKEN_GRANT, 
																		  PsnApiProperties.getInstance().getClientId(),
																		  PsnApiProperties.getInstance().getSecretKey(),
																		  oLoginCode, 
																		  PsnApiProperties.getInstance().getLoginRedirectUrl(),
																		  STATE,
																		  PsnApiProperties.getInstance().getScopeAuthToken(),
																		  mPsnContext.getDuid() );
		Response<AccessToken> oAccessTokenResponse = oAccessTokenCall.execute();
		
		
		if(  oAccessTokenResponse.isSuccess() )
		{
			mPsnContext.setAccessToken( oAccessTokenResponse.body() );
		} else {
			throw new LoginException("Error to retrieved access token");
		}
		
	}

	/**
	 * 
	 * @throws IOException
	 * @throws LoginException
	 * @throws PsnErrorException
	 */
	@Override
	public void refreshAuthToken() throws IOException, LoginException, PsnErrorException {
	
		Call<AccessToken> oAccessTokenCall = mPSNApiService.refreshAuthToken(REFRESH_AUTH_TOKEN_GRANT,
				PsnApiProperties.getInstance().getClientId(),
				PsnApiProperties.getInstance().getSecretKey(),
				PsnApiProperties.getInstance().getScopeAuthToken(), 
				mPsnContext.getDuid(),
				mPsnContext.getAccessToken().getRefreshToken() );
		
		Response<AccessToken> oAccessTokenResponse = oAccessTokenCall.execute();

		if (oAccessTokenResponse.isSuccess()) {
			mPsnContext.setAccessToken(oAccessTokenResponse.body());
			mPsnContext.setLastTokenUpdate( System.currentTimeMillis() );
		} else {
			throw new LoginException("Error to retrieved refresh access token");
		}
	}
	
	
//=============================================================================
//
// Profile METHODS
//
//=============================================================================
	
	/**
	 * 
	 * @return
	 * @throws IOException
	 * @throws PsnErrorException
	 */
	@Override
	public User getAccountInformation() throws IOException, PsnErrorException {

		Call<User> oUserCall  = mPSNApiService.getMyProfile( PsnUrlCst.BASE_URL_ME_PROFILE, mPsnContext.getAccessToken().getAccessToken() );
		Response<User> oUserResponse = oUserCall.execute();			
		return processResponse( oUserResponse );
	}

	
	
	/**
	 * 
	 * @param aOnlineId
	 * @param aOffset
	 * @param aLimit
	 * @param aAvatarSize
	 * @param aProfileParams
	 * @return
	 * @throws IOException
	 * @throws PsnErrorException
	 */
	@Override
	public FriendList getFriendList( String aOnlineId, ProfileParam aProfileParam, FriendPaginationParam aPagination ) throws IOException,AccessDeniedByPrivacyLevelException, PsnErrorException {
		
		String oBaseUrl = getFriendProfileBaseUrl().getUrl() + PsnUrlCst.URI_FRIEND_LIST;
				
		/* TODO supprimer les valeur en dur*/
		Call<FriendList> oFriendListCall  = mPSNApiService.getFriendList( UrlUtils.injectDataInUrl(oBaseUrl, 
				UrlParamCst.URL_PARAM_ONLINE_ID , aOnlineId ),
				UrlUtils.createAuthHeader( mPsnContext.getAccessToken() ) ,
				UrlUtils.joinDataEnum( aProfileParam.getProfileParams() ),
				"onlineId",
				( aProfileParam.getAvatarSize() != null && aProfileParam.getAvatarSize().size() > 0 )? UrlUtils.joinDataEnum( aProfileParam.getAvatarSize() ): null,			
				aProfileParam.getPresenceType(),
				aProfileParam.getFriendStatus().getData(),
				aPagination.getOffset(),  Math.min( aPagination.getLimit(), FriendPaginationParam.LIMIT ) );
		
		Response<FriendList> oFriendListResponse = oFriendListCall.execute();
	
		FriendList oFriendList =  processResponse( oFriendListResponse );
		
		if( aPagination.getOffset() + aPagination.getLimit() <= oFriendList.getTotalResults() ) {
			FriendPaginationParam oNextPaginationParam = new FriendPaginationParam( aPagination.getOffset() + aPagination.getLimit(), aPagination.getLimit() );
			oFriendList.setNextPagination( oNextPaginationParam );
		}
				
		return oFriendList;
	}

	/**
	 * 
	 * @param aOnlineId
	 * @param aProfileParams
	 * @return
	 * @throws IOException
	 * @throws PsnErrorException
	 */
	@Override
	public FriendProfile getFriendDetail(String aOnlineId, ProfileParam aProfileParam ) throws IOException, PsnErrorException {
		
		String oBaseUrl = getFriendProfileBaseUrl().getUrl() + PsnUrlCst.URI_FRIEND_DETAIL;
		 
		Call<FriendProfile> oFriendCall  = mPSNApiService.getFriendDetail( UrlUtils.injectDataInUrl(oBaseUrl,
				UrlParamCst.URL_PARAM_ONLINE_ID, aOnlineId ),
				UrlUtils.createAuthHeader( mPsnContext.getAccessToken() ), 
				UrlUtils.joinDataEnum( aProfileParam.getProfileParams() ),
				( aProfileParam.getAvatarSize() != null && aProfileParam.getAvatarSize().size() > 0 )? UrlUtils.joinDataEnum( aProfileParam.getAvatarSize() ): null );
		
		Response<FriendProfile> oFriendResponse = oFriendCall.execute();
	
		return processResponse( oFriendResponse  );
		
	}
	
	/**
	 * 
	 * @param aOnlineId
	 * @param aProfileParams
	 * @return
	 * @throws IOException
	 * @throws PsnErrorException
	 */
	@Override
	public ProfileList getMultiFriendDetail(List<String> aOnlineId, ProfileParam aProfileParam ) throws IOException, PsnErrorException {
		
		String oBaseUrl = getFriendProfileBaseUrl().getUrl() + PsnUrlCst.URI_MULTI_FRIEND_DETAIL;
		 
		Call<ProfileList> oProfileListCall  = mPSNApiService.getMultiFriendDetail( oBaseUrl,
				UrlUtils.createAuthHeader( mPsnContext.getAccessToken() ),
				UrlUtils.joinList( aOnlineId ), 
				UrlUtils.joinDataEnum( aProfileParam.getProfileParams() ),
				( aProfileParam.getAvatarSize() != null && aProfileParam.getAvatarSize().size() > 0 )? UrlUtils.joinDataEnum( aProfileParam.getAvatarSize() ): null );
		
		return processResponse( oProfileListCall.execute() );		
	}
	
	
	
	
	/**
	 * 
	 * @param aOnlineId
	 * @param aFriendOnlineId
	 * @throws IOException
	 * @throws PsnErrorException
	 */
	@Override
	public void delFriend(String aOnlineId, String aFriendOnlineId) throws IOException, PsnErrorException {
	
		 
		String oBaseUrl = getFriendProfileBaseUrl().getUrl() + PsnUrlCst.URI_DEL_FRIEND;
		
		Map<String, String> oMapParam = new HashMap<>();
		oMapParam.put(UrlParamCst.URL_PARAM_ONLINE_ID, aOnlineId);
		oMapParam.put(UrlParamCst.URL_PARAM_ONLINE_FRIEND_ID, aFriendOnlineId);
		
		Call<Void> oCall  = mPSNApiService.delFriend( UrlUtils.injectDataInUrl(oBaseUrl, oMapParam ),
				UrlUtils.createAuthHeader( mPsnContext.getAccessToken() ) );
	
		processResponse( oCall.execute() );	
	}

	/**
	 * 
	 * @param aOnlineId
	 * @param aFriendOnlineId
	 * @throws IOException
	 * @throws PsnErrorException
	 */
	
	@Override
	public void addFriend(String aOnlineId, String aFriendOnlineId) throws IOException, PsnErrorException {
		 addFriend( aOnlineId,  aFriendOnlineId,  "" );
	}
	
	
	/**
	 * 
	 * @param aOnlineId
	 * @param aFriendOnlineId
	 * @param aRequestMessage resquest message
	 * @throws IOException
	 * @throws PsnErrorException
	 */
	@Override
	public void addFriend(String aOnlineId, String aFriendOnlineId, String aRequestMessage ) throws IOException, PsnErrorException {
	
		 
		String oBaseUrl = getFriendProfileBaseUrl().getUrl() + PsnUrlCst.URI_ADD_FRIEND;
		
		Map<String, String> oMapParam = new HashMap<>();
		oMapParam.put(UrlParamCst.URL_PARAM_ONLINE_ID, aOnlineId);
		oMapParam.put(UrlParamCst.URL_PARAM_ONLINE_FRIEND_ID, aFriendOnlineId);
		
		Call<Void> oCall  = mPSNApiService.addFriend( UrlUtils.injectDataInUrl(oBaseUrl, oMapParam ),
				UrlUtils.createAuthHeader( mPsnContext.getAccessToken() ),
				new RequestMessage( aRequestMessage) );
	
		processResponse( oCall.execute() );	
	}
	
	/**
	 * return trophy base friend profile
	 * @return
	 * @throws IOException
	 * @throws PsnErrorException
	 */
	private ServiceUrl getFriendProfileBaseUrl() throws IOException, PsnErrorException {
		
		Call<ServiceUrl> oServiceUrlCall  = mPSNApiService.getUrl( PsnUrlCst.BASE_URL_USER_PROFILE, UrlUtils.createAuthHeader( mPsnContext.getAccessToken() ) );
		Response<ServiceUrl> oServiceUrlResponse = oServiceUrlCall.execute();
		
		return processResponse( oServiceUrlResponse );
	}
	

	
//=============================================================================
//
// "block" METHODS
//
//=============================================================================
	@Override
	public void blockProfile(String oYourOnlineId, String aProfileOnlineId) throws IOException,
			PsnErrorException {

		String oBaseUrl = getFriendProfileBaseUrl().getUrl() + PsnUrlCst.URI_BLOCK_PROFILE;

		Map<String, String> oMapParam = new HashMap<>();
		oMapParam.put(UrlParamCst.URL_PARAM_ONLINE_ID, oYourOnlineId);
		oMapParam.put(UrlParamCst.URL_PARAM_PROFILE_ONLINE_ID, aProfileOnlineId);
		
		
		Call<Void> oCall  = mPSNApiService.blockProfile( UrlUtils.injectDataInUrl(oBaseUrl, oMapParam ),
				UrlUtils.createAuthHeader( mPsnContext.getAccessToken() ) );
	
		processResponse( oCall.execute() );	
		
	}

	@Override
	public void unblockProfile(String oYourOnlineId, String aProfileOnlineId) throws IOException,
			PsnErrorException {
		String oBaseUrl = getFriendProfileBaseUrl().getUrl() + PsnUrlCst.URI_BLOCK_PROFILE;

		Map<String, String> oMapParam = new HashMap<>();
		oMapParam.put(UrlParamCst.URL_PARAM_ONLINE_ID, oYourOnlineId);
		oMapParam.put(UrlParamCst.URL_PARAM_PROFILE_ONLINE_ID, aProfileOnlineId);
		
		
		Call<Void> oCall  = mPSNApiService.unblockProfile( UrlUtils.injectDataInUrl(oBaseUrl, oMapParam ),
				UrlUtils.createAuthHeader( mPsnContext.getAccessToken() ) );
	
		processResponse( oCall.execute() );	
	}


	/**
	 * @param aYourOnlineId
	 * @param aProfileParam
	 * @param aPagination
	 * @return list of blocked profile
	 * 
	 */
	@Override
	public BlockList getBlockProfileList(String aYourOnlineId, ProfileParam aProfileParam, BlockPaginationParam aPagination)
			throws IOException, AccessDeniedByPrivacyLevelException, PsnErrorException {
		
		String oBaseUrl = getFriendProfileBaseUrl().getUrl() + PsnUrlCst.URI_BLOCK_LIST_PROFILE;
		
		/* TODO supprimer les valeur en dur*/
		Call<BlockList> oBlockListCall  = mPSNApiService.getBlockProfileList( UrlUtils.injectDataInUrl(oBaseUrl, 
				UrlParamCst.URL_PARAM_ONLINE_ID , aYourOnlineId ),
				UrlUtils.createAuthHeader( mPsnContext.getAccessToken() ) ,
				UrlUtils.joinDataEnum( aProfileParam.getProfileParams() ),
				"onlineId",
				( aProfileParam.getAvatarSize() != null && aProfileParam.getAvatarSize().size() > 0 )? UrlUtils.joinDataEnum( aProfileParam.getAvatarSize() ): null,			
				aPagination.getOffset(),  Math.min( aPagination.getLimit(), FriendPaginationParam.LIMIT ) );
		
		Response<BlockList> oBlockListResponse = oBlockListCall.execute();
	
		BlockList oBlockList =  processResponse( oBlockListResponse );
		
		if( aPagination.getOffset() + aPagination.getLimit() <= oBlockList.getTotalResults() ) {
			BlockPaginationParam oNextPaginationParam = new BlockPaginationParam( aPagination.getOffset() + aPagination.getLimit(), aPagination.getLimit() );
			oBlockList.setNextPagination( oNextPaginationParam );
		}
				
		return oBlockList;
	}

//=============================================================================
//
// Trohpy METHODS
//
//=============================================================================

	/**
	 * 
	 */
	@Override
	public TrophyTitleList getTrophyList(TrophyParam aTrophyParam, TrophyPaginationParam aPagination ) throws IOException,
			PsnErrorException {
		
		String oBaseUrl = getTrophyBaseUrl().getUrl() + PsnUrlCst.URI_TROPHY_TITLES;
		/* TODO supprimer les valeur en dure*/
		Call<TrophyTitleList> oTrophyTitleListCall  = mPSNApiService.getTrophyList(oBaseUrl,
				UrlUtils.createAuthHeader( mPsnContext.getAccessToken() ),
				"@default",
				aTrophyParam.getLocale().getCountry().toLowerCase(),
				UrlUtils.joinDataEnum( aTrophyParam.getImageSize() ),
				UrlUtils.joinDataEnum( aTrophyParam.getPlatfromEnums() ),
				aPagination.getOffset(),
				Math.min( aPagination.getLimit(), TrophyPaginationParam.LIMIT ) );
		
		Response<TrophyTitleList> oTrophyTitleListResponse = oTrophyTitleListCall.execute();
	
		TrophyTitleList oTrophyTitleList =  processResponse( oTrophyTitleListResponse );
		
		if( aPagination.getOffset() + aPagination.getLimit() <= oTrophyTitleList.getTotalResults() ) {
			TrophyPaginationParam oNextPaginationParam = new TrophyPaginationParam( aPagination.getOffset() + aPagination.getLimit(), aPagination.getLimit() );
			oTrophyTitleList.setNextPagination( oNextPaginationParam );
		}
		
		return oTrophyTitleList;
	}
	
	/**
	 * 
	 */
	@Override
	public TrophyGroupsResponse getTrophyGroups(String aNameId, TrophyParam aTrophyParam) throws IOException, PsnErrorException {
	
		String oBaseUrl = getTrophyBaseUrl().getUrl() + PsnUrlCst.URI_TROPHY_GROUPS;
		
		/* TODO supprimer les valeur en dure*/
		Call<TrophyGroupsResponse> oTrophyGroupsResponseCall  = mPSNApiService.getTrophyGroups( UrlUtils.injectDataInUrl(oBaseUrl, "gameId", aNameId),
				UrlUtils.createAuthHeader( mPsnContext.getAccessToken() ),
				"@default",
				aTrophyParam.getLocale().getCountry().toLowerCase(),
				UrlUtils.joinDataEnum( aTrophyParam.getImageSize() ) );
		
		Response<TrophyGroupsResponse> oTrophyGroupsResponseResponse = oTrophyGroupsResponseCall.execute();
		return processResponse( oTrophyGroupsResponseResponse );

	}
	
	/**
	 * 
	 */
	@Override
	public TrophyGroupsDetailsResponse getTrophyGroupsDetail(String aGameId, String aTrophyGroupId, TrophyParam aTrophyParam ) throws IOException, PsnErrorException {
		String oBaseUrl = getTrophyBaseUrl().getUrl() + PsnUrlCst.URI_TROPHY_GROUPS_DETAILS;
			
		/* TODO supprimer les valeur en dure*/
		Map<String,String> aDataUrl = new HashMap<>();
		aDataUrl.put("gameId", aGameId);
		aDataUrl.put("trophyGroupId", aTrophyGroupId);
		
		Call<TrophyGroupsDetailsResponse> oTrophyGroupsDetailsResponseCall  = mPSNApiService.getTrophyGroupsDetail( UrlUtils.injectDataInUrl(oBaseUrl, aDataUrl ),
				UrlUtils.createAuthHeader( mPsnContext.getAccessToken() ),
				"@default,trophyRare,trophyEarnedRate",
				aTrophyParam.getLocale().getCountry().toLowerCase(),
				UrlUtils.joinDataEnum( aTrophyParam.getImageSize() ) );
		
		Response<TrophyGroupsDetailsResponse> oTrophyGroupsDetailsResponseResponse = oTrophyGroupsDetailsResponseCall.execute();
		return processResponse( oTrophyGroupsDetailsResponseResponse );
	}
	
	
	
	
	/**
	 * return trophy base url
	 * @return
	 * @throws IOException
	 * @throws PsnErrorException
	 */
	private ServiceUrl getTrophyBaseUrl() throws IOException, PsnErrorException {
		
		Call<ServiceUrl> oServiceUrlCall  = mPSNApiService.getUrl( PsnUrlCst.BASE_URL_TROPHY, UrlUtils.createAuthHeader( mPsnContext.getAccessToken() ) );
		Response<ServiceUrl> oServiceUrlResponse = oServiceUrlCall.execute();
		
		return processResponse( oServiceUrlResponse );
	}
	
	/**
	 * 
	 * @param aResponse
	 * @return
	 * @throws PsnErrorException
	 */
	private <T> T processResponse( Response<T> aResponse ) throws PsnErrorException {
		
		if( aResponse.isSuccess() ) {
			return  aResponse.body();
		}
		else {
			try {
				PsnError oPsnError = null;
				String oError = aResponse.errorBody().string();
				if(!"".equals( oError ) && aResponse.errorBody().contentType().toString().contains("json") ) {
					oPsnError = GsonParser.getGsonParserInstance().fromJson( oError , PsnError.class );
				}
				else {
					oPsnError = new PsnError();
					oPsnError.setCode( aResponse.code() );
					oPsnError.setMessage( aResponse.message() );
				}
						
				throw PsnExceptionFactory.createException( oPsnError );	
				
			} catch (JsonSyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

}