package com.xseillier.psnapi.impl;

import com.google.gson.JsonSyntaxException;
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
import com.xseillier.psnapi.http.interceptor.AuthorisationInterceptor;
import com.xseillier.psnapi.http.interceptor.CountRequestInterceptor;
import com.xseillier.psnapi.http.interceptor.LoggingInterceptor;
import com.xseillier.psnapi.http.interceptor.RateLimiterInterceptor;
import com.xseillier.psnapi.http.retrofit.converter.PsnMessagingFactory;
import com.xseillier.psnapi.jsonparser.GsonParser;
import com.xseillier.psnapi.model.AccessToken;
import com.xseillier.psnapi.model.PsnContext;
import com.xseillier.psnapi.model.PsnError;
import com.xseillier.psnapi.model.ServiceUrl;
import com.xseillier.psnapi.model.block.BlockList;
import com.xseillier.psnapi.model.block.BlockPagination;
import com.xseillier.psnapi.model.friend.*;
import com.xseillier.psnapi.model.messaging.*;
import com.xseillier.psnapi.model.param.*;
import com.xseillier.psnapi.model.trophy.TrophyGroupsDetailsResponse;
import com.xseillier.psnapi.model.trophy.TrophyGroupsResponse;
import com.xseillier.psnapi.model.trophy.TrophyPagination;
import com.xseillier.psnapi.model.trophy.TrophyTitleList;
import com.xseillier.psnapi.model.user.User;
import com.xseillier.psnapi.properties.PsnApiProperties;
import com.xseillier.psnapi.utils.UrlUtils;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.File;
import java.io.IOException;
import java.net.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author xseillier
 * @version 1.0 15 sept. 2015
 *
 */
public class PsnApiImpl implements PsnApi {
	
	public static double NO_RATE_LIMIT_CONTROL           = -1; 
	public static double DEFAULT_RATE_LIMIT              = 0.1111111111111111; //Request by second	
	private static final String AUTH_TOKEN_GRANT         = "authorization_code";
	private static final String REFRESH_AUTH_TOKEN_GRANT = "refresh_token";
	private static final String STATE                    = "x";
	
	private OkHttpClient.Builder mOkHttpClientBuilder;
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
		mOkHttpClientBuilder = new OkHttpClient().newBuilder();
		CookieManager cookieManager = new CookieManager();
		cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
		mOkHttpClientBuilder.cookieJar(new JavaNetCookieJar(cookieManager));
		
		mOkHttpClientBuilder.interceptors().add( new AuthorisationInterceptor( this ) );
		
		if( aLimitRate > 0 ) {
			mOkHttpClientBuilder.interceptors().add( new RateLimiterInterceptor( aLimitRate ) );
		}
		if( DEBUG ) {
			mOkHttpClientBuilder.interceptors().add( new CountRequestInterceptor() );
			mOkHttpClientBuilder.interceptors().add( new LoggingInterceptor() );
		}
			
	}
	
	
	
	/**
	 * 
	 * @param aHost
	 * @param aPort
	 * @throws UnknownHostException
	 */
	public void addProxy( String aHost, int aPort ) throws UnknownHostException{
		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(InetAddress.getByName( aHost ) , aPort ));
		mOkHttpClientBuilder.proxy(proxy);
	}
	
	
	/**
	 * init retrofit service
	 */
	private void initPSNApiService(){
		
		Retrofit oRetrofit = new Retrofit.Builder()
		.client( mOkHttpClientBuilder.build() )
		.addConverterFactory( new PsnMessagingFactory( GsonParser.getGsonParserInstance() ) )
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
		
		Login oLogin = new LoginImpl(mOkHttpClientBuilder);
		
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
		
		
		if(  oAccessTokenResponse.isSuccessful() )
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

		if (oAccessTokenResponse.isSuccessful()) {
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
	 * @param aProfileParams
	 * @param aPagination
	 * @return
	 * @throws IOException
	 * @throws PsnErrorException
	 */
	@Override
	public FriendList getFriendList( String aOnlineId, ProfileParam aProfileParams, FriendPagination aPagination ) throws IOException,AccessDeniedByPrivacyLevelException, PsnErrorException {
		
		String oBaseUrl = getFriendProfileBaseUrl().getUrl() + PsnUrlCst.URI_FRIEND_LIST;
				
		/* TODO supprimer les valeur en dur*/
		Call<FriendList> oFriendListCall  = mPSNApiService.getFriendList(
				UrlUtils.injectDataInUrl(oBaseUrl, UrlParamCst.URL_PARAM_ONLINE_ID , aOnlineId ),
				UrlUtils.joinDataEnum( aProfileParams.getProfileParams() ),
				"onlineId",
				( aProfileParams.getAvatarSize() != null && aProfileParams.getAvatarSize().size() > 0 )? UrlUtils.joinDataEnum( aProfileParams.getAvatarSize() ): null,
				aProfileParams.getPresenceType(),
				aProfileParams.getFriendStatus().getData(),
				aPagination.getOffset(),  Math.min( aPagination.getLimit(), FriendPagination.LIMIT ) );
		
		Response<FriendList> oFriendListResponse = oFriendListCall.execute();
	
		FriendList oFriendList =  processResponse( oFriendListResponse );
		
		if( aPagination.getOffset() + aPagination.getLimit() <= oFriendList.getTotalResults() ) {
			FriendPagination oNextPaginationParam = new FriendPagination( aPagination.getOffset() + aPagination.getLimit(), aPagination.getLimit() );
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
	public FriendProfile getFriendDetail(String aOnlineId, ProfileParam aProfileParams ) throws IOException, PsnErrorException {
		
		String oBaseUrl = getFriendProfileBaseUrl().getUrl() + PsnUrlCst.URI_FRIEND_DETAIL;
		 
		Call<FriendProfile> oFriendCall  = mPSNApiService.getFriendDetail( UrlUtils.injectDataInUrl(oBaseUrl,
				UrlParamCst.URL_PARAM_ONLINE_ID, aOnlineId ),
				UrlUtils.joinDataEnum( aProfileParams.getProfileParams() ),
				( aProfileParams.getAvatarSize() != null && aProfileParams.getAvatarSize().size() > 0 )? UrlUtils.joinDataEnum( aProfileParams.getAvatarSize() ): null );
		
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
	public FriendProfile getFriendDetailV2(String aOnlineId, ProfileV2Param aProfileParams) throws IOException, PsnErrorException {
		// TODO a completer
		throw new UnsupportedOperationException("Not Implemented");
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
	public ProfileList getMultiFriendDetail(List<String> aOnlineId, ProfileParam aProfileParams ) throws IOException, PsnErrorException {
		
		String oBaseUrl = getFriendProfileBaseUrl().getUrl() + PsnUrlCst.URI_MULTI_FRIEND_DETAIL;
		 
		Call<ProfileList> oProfileListCall  = mPSNApiService.getMultiFriendDetail( oBaseUrl,
				UrlUtils.joinList( aOnlineId ), 
				UrlUtils.joinDataEnum( aProfileParams.getProfileParams() ),
				( aProfileParams.getAvatarSize() != null && aProfileParams.getAvatarSize().size() > 0 )? UrlUtils.joinDataEnum( aProfileParams.getAvatarSize() ): null );
		
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
		
		Call<Void> oCall  = mPSNApiService.delFriend( UrlUtils.injectDataInUrl(oBaseUrl, oMapParam ) );
	
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
				new RequestMessage( aRequestMessage) );
	
		processResponse( oCall.execute() );	
	}

	
	/**
	 * @param aProfileParams
	 * @param aPagination
	 */
	@Override
	public FriendSendRequestList getFriendSendRequest( ProfileParam aProfileParams, FriendPagination aPagination) throws IOException, PsnErrorException {
		
		String oBaseUrl = getFriendProfileBaseUrl().getUrl() + PsnUrlCst.URI_GET_FRIEND_SENT_REQUEST;
		
		Call<FriendSendRequestList> oFriendSendRequestListCall = mPSNApiService.getFriendSendRequest(
				oBaseUrl,
				UrlUtils.joinDataEnum( aProfileParams.getProfileParams() ),
				"requestedDate",
				( aProfileParams.getAvatarSize() != null && aProfileParams.getAvatarSize().size() > 0 )? UrlUtils.joinDataEnum( aProfileParams.getAvatarSize() ): null,
				aProfileParams.getPresenceType(),
				"desc", 
				aPagination.getOffset(),
				Math.min( aPagination.getLimit(), aPagination.LIMIT ) );
		
		Response<FriendSendRequestList> oFriendSendRequestListResponse = oFriendSendRequestListCall.execute();
	
		FriendSendRequestList oFriendSendRequestList =  processResponse( oFriendSendRequestListResponse );
		
		if( aPagination.getOffset() + aPagination.getLimit() <= oFriendSendRequestList.getTotalResults() ) {
			FriendPagination oNextPaginationParam = new FriendPagination( aPagination.getOffset() + aPagination.getLimit(), aPagination.getLimit() );
			oFriendSendRequestList.setNextPagination( oNextPaginationParam );
		}
				
		return oFriendSendRequestList;
	}

	
	/**
	 * @param aProfileParams
	 * @param aPagination
	 */
	@Override
	public FriendReceiveRequestList getFriendReceiveRequest( ProfileParam aProfileParams, FriendPagination aPagination) throws IOException, PsnErrorException {
		
		
		String oBaseUrl = getFriendProfileBaseUrl().getUrl() + PsnUrlCst.URI_GET_FRIEND_RECEIVED_REQUEST;
		
		Call<FriendReceiveRequestList> oFriendReceiveRequestListCall = mPSNApiService.getFriendReceiveRequest(
				oBaseUrl,
				UrlUtils.joinDataEnum( aProfileParams.getProfileParams() ),
				"requestedDate",
				( aProfileParams.getAvatarSize() != null && aProfileParams.getAvatarSize().size() > 0 )? UrlUtils.joinDataEnum( aProfileParams.getAvatarSize() ): null,
				aProfileParams.getPresenceType(),
				"desc", 
				aPagination.getOffset(),
				Math.min( aPagination.getLimit(), aPagination.LIMIT ) );
		
		Response<FriendReceiveRequestList> oFriendReceiveRequestListResponse = oFriendReceiveRequestListCall.execute();
	
		FriendReceiveRequestList oFriendReceiveRequestList =  processResponse( oFriendReceiveRequestListResponse );
		
		if( aPagination.getOffset() + aPagination.getLimit() <= oFriendReceiveRequestList.getTotalResults() ) {
			FriendPagination oNextPaginationParam = new FriendPagination( aPagination.getOffset() + aPagination.getLimit(), aPagination.getLimit() );
			oFriendReceiveRequestList.setNextPagination( oNextPaginationParam );
		}
				
		return oFriendReceiveRequestList;
	}
	
	/**
	 * return trophy base friend profile
	 * @return
	 * @throws IOException
	 * @throws PsnErrorException
	 */
	private ServiceUrl getFriendProfileBaseUrl() throws IOException, PsnErrorException {
		
		Call<ServiceUrl> oServiceUrlCall  = mPSNApiService.getUrl( PsnUrlCst.BASE_URL_USER_PROFILE );
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
		
		
		Call<Void> oCall  = mPSNApiService.blockProfile( UrlUtils.injectDataInUrl(oBaseUrl, oMapParam ) );
	
		processResponse( oCall.execute() );	
		
	}

	@Override
	public void unblockProfile(String oYourOnlineId, String aProfileOnlineId) throws IOException,
			PsnErrorException {
		String oBaseUrl = getFriendProfileBaseUrl().getUrl() + PsnUrlCst.URI_BLOCK_PROFILE;

		Map<String, String> oMapParam = new HashMap<>();
		oMapParam.put(UrlParamCst.URL_PARAM_ONLINE_ID, oYourOnlineId);
		oMapParam.put(UrlParamCst.URL_PARAM_PROFILE_ONLINE_ID, aProfileOnlineId);
		
		
		Call<Void> oCall  = mPSNApiService.unblockProfile( UrlUtils.injectDataInUrl(oBaseUrl, oMapParam ) );
	
		processResponse( oCall.execute() );	
	}


	/**
	 * @param aYourOnlineId
	 * @param aProfileParams
	 * @param aPagination
	 * @return list of blocked profile
	 * 
	 */
	@Override
	public BlockList getBlockProfileList(String aYourOnlineId, ProfileParam aProfileParams, BlockPagination aPagination)
			throws IOException, AccessDeniedByPrivacyLevelException, PsnErrorException {
		
		String oBaseUrl = getFriendProfileBaseUrl().getUrl() + PsnUrlCst.URI_BLOCK_LIST_PROFILE;
		
		/* TODO supprimer les valeur en dur*/
		Call<BlockList> oBlockListCall  = mPSNApiService.getBlockProfileList( UrlUtils.injectDataInUrl(oBaseUrl, 
				UrlParamCst.URL_PARAM_ONLINE_ID , aYourOnlineId ),
				UrlUtils.joinDataEnum( aProfileParams.getProfileParams() ),
				"onlineId",
				( aProfileParams.getAvatarSize() != null && aProfileParams.getAvatarSize().size() > 0 )? UrlUtils.joinDataEnum( aProfileParams.getAvatarSize() ): null,
				aPagination.getOffset(),  Math.min( aPagination.getLimit(), FriendPagination.LIMIT ) );
		
		Response<BlockList> oBlockListResponse = oBlockListCall.execute();
	
		BlockList oBlockList =  processResponse( oBlockListResponse );
		
		if( aPagination.getOffset() + aPagination.getLimit() <= oBlockList.getTotalResults() ) {
			BlockPagination oNextPaginationParam = new BlockPagination( aPagination.getOffset() + aPagination.getLimit(), aPagination.getLimit() );
			oBlockList.setNextPagination( oNextPaginationParam );
		}
				
		return oBlockList;
	}

//=============================================================================
//
// Trophy METHODS
//
//=============================================================================

	/**
	 * 
	 */
	@Override
	public TrophyTitleList getTrophyList(TrophyParam aTrophyParam, TrophyPagination aPagination ) throws IOException,
			PsnErrorException {
		
		String oBaseUrl = getTrophyBaseUrl().getUrl() + PsnUrlCst.URI_TROPHY_TITLES;
		/* TODO supprimer les valeur en dure*/
		Call<TrophyTitleList> oTrophyTitleListCall  = mPSNApiService.getTrophyList(oBaseUrl,
				UrlUtils.joinDataEnum( aTrophyParam.getTrophySummaryOption()),
				aTrophyParam.getLocale().getLanguage(),
				UrlUtils.joinDataEnum( aTrophyParam.getImageSize() ),
				UrlUtils.joinDataEnum( aTrophyParam.getPlatfromEnums() ),
				aPagination.getOffset(),
				Math.min( aPagination.getLimit(), TrophyPagination.LIMIT ) );
		
		Response<TrophyTitleList> oTrophyTitleListResponse = oTrophyTitleListCall.execute();
	
		TrophyTitleList oTrophyTitleList =  processResponse( oTrophyTitleListResponse );
		
		if( aPagination.getOffset() + aPagination.getLimit() <= oTrophyTitleList.getTotalResults() ) {
			TrophyPagination oNextPaginationParam = new TrophyPagination( aPagination.getOffset() + aPagination.getLimit(), aPagination.getLimit() );
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
		Call<TrophyGroupsResponse> oTrophyGroupsResponseCall  = mPSNApiService.getTrophyGroups(
		        UrlUtils.injectDataInUrl(oBaseUrl, "gameId", aNameId),
				"@default",
				aTrophyParam.getLocale().getLanguage(),
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
		
		Call<TrophyGroupsDetailsResponse> oTrophyGroupsDetailsResponseCall  = mPSNApiService.getTrophyGroupsDetail(
		        UrlUtils.injectDataInUrl(oBaseUrl, aDataUrl ),
				UrlUtils.joinDataEnum( aTrophyParam.getTrophySummaryOption() ),
				aTrophyParam.getLocale().getLanguage(),
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
		
		Call<ServiceUrl> oServiceUrlCall  = mPSNApiService.getUrl( PsnUrlCst.BASE_URL_TROPHY );
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
		
		if( aResponse.isSuccessful() ) {
			return  aResponse.body();
		}
		else {
			try {
				PsnError oPsnError = null;
                ResponseBody body = aResponse.errorBody();
				String oError = body.string();
				if(!"".equals( oError ) && aResponse.errorBody().contentType().toString().contains("json") ) {
					oPsnError = GsonParser.getGsonParserInstance().fromJson( oError , PsnError.class );
				}
				else {
					oPsnError = new PsnError();
					oPsnError.setMessage( aResponse.message() );
				}
				oPsnError.setHttpCode( aResponse.code() );
                body.close();
						
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
	
//=============================================================================
//
// MESSAGING METHODS
//	
//=============================================================================


    /**
     * create a discussion
     * @param aOnlineIdList
     * @param aMessage
     * @return SendMessageResponse
     */
	@Override
	public SendMessageResponse createDiscussion(List<String> aOnlineIdList, String aMessage) throws IOException,
			PsnErrorException {
	
		return createDiscussion( aOnlineIdList, aMessage, null );
	}

	/**
	 * create a discussion
     * @param aOnlineIdList
     * @param aMessage
     * @param aImage
     * @return SendMessageResponse
	 */
	@Override
	public SendMessageResponse createDiscussion(List<String> aOnlineIdList, String aMessage, File aImage ) throws IOException,
			PsnErrorException {
		
		SendMessage oStringMessage = new SendMessage( aOnlineIdList, aMessage );
		if( aImage != null ) {
			oStringMessage.setImage( aImage  );
		}
		String oBaseUrl = getMessagingBaseUrl().getUrl() + PsnUrlCst.URI_CREATE_DISCUSSION;
		Call<SendMessageResponse> oSendMessageCall  = mPSNApiService.createDiscussion(oBaseUrl,
																					  oStringMessage );
				
		return processResponse( oSendMessageCall.execute() );
	}


	/**
	 * mark message as seen
	 * @param aMessageUidList
     * @param aDiscussionId
	 */
	@Override
	public void markMessageAsSeen(List<Long> aMessageUidList, String aDiscussionId) throws IOException, PsnErrorException {
		String oBaseUrl = getMessagingBaseUrl().getUrl() + PsnUrlCst.URI_MARK_MESSAGE_AS_SEEN;		
		
		Call<Void> oVoidCall = mPSNApiService.markMessageAsSeen(
				UrlUtils.injectDataInUrl(oBaseUrl, UrlParamCst.PART_DISCUSSION_ID, aDiscussionId), 
				UrlUtils.joinList( aMessageUidList ),
				new MessageSeen() );
		
		processResponse( oVoidCall.execute() );
	}


	/**
	 * @param aMessage
	 * @param aDiscussionId
	 * @return SimpleDiscussion
	 */
	@Override
	public SendMessageResponse addMessageToDiscussion(String aDiscussionId , String aMessage ) throws IOException, PsnErrorException {
		return addMessageToDiscussion( aDiscussionId, aMessage, null );
	}
	
	/**
	 * @param aMessage
	 * @param aDiscussionId
	 * @param aImage
	 * @return SimpleDiscussion
	 */
	@Override
	public SendMessageResponse addMessageToDiscussion(String aDiscussionId , String aMessage, File aImage) throws IOException, PsnErrorException {
	
		SendMessage oStringMessage = new SendMessage( aMessage );
		if( aImage != null ){
			oStringMessage.setImage( aImage );
		}
		
		String oBaseUrl = getMessagingBaseUrl().getUrl() + PsnUrlCst.URI_ADD_MSG_TO_DISCUSSION;
		Call<SendMessageResponse> oSendMessageCall  = mPSNApiService.addMessageToDiscussion(
				UrlUtils.injectDataInUrl(oBaseUrl, UrlParamCst.PART_DISCUSSION_ID, aDiscussionId), 
				oStringMessage );

		return processResponse( oSendMessageCall.execute() );
	}
	
	/**
	 * @param aYourOnlineId
	 * @param aDiscussionParam
     * @param aPagination
	 * @return
	 * @throws IOException
	 * @throws PsnErrorException
	 */
	@Override
	public DiscussionList getDiscussionList(String aYourOnlineId, DiscussionParam aDiscussionParam, DiscussionPagination aPagination ) throws IOException,
			PsnErrorException {
		
		String oBaseUrl = getMessagingBaseUrl().getUrl() + PsnUrlCst.URI_GET_DISCUSSION_LIST;		
						
		Call<DiscussionList> oDiscussionCall = mPSNApiService.getListDiscussion(
		        UrlUtils.injectDataInUrl(oBaseUrl, UrlParamCst.URL_PARAM_ONLINE_ID , aYourOnlineId ),
				UrlUtils.joinDataEnum( aDiscussionParam.getDiscussionParams() ),
				aDiscussionParam.getLocale().getLanguage(),
				aPagination.getOffset(),
				Math.min( aPagination.getLimit(), TrophyPagination.LIMIT ));
				
		Response<DiscussionList> oDiscussionListResponse = oDiscussionCall.execute();
		
		DiscussionList oDiscussionList =  processResponse( oDiscussionListResponse );
		
		if( aPagination.getOffset() + aPagination.getLimit() <= oDiscussionList.getTotalResults() ) {
			DiscussionPagination oNextPaginationParam = new DiscussionPagination( aPagination.getOffset() + aPagination.getLimit(), aPagination.getLimit() );
			oDiscussionList.setNextPagination( oNextPaginationParam );
		}
		
		return oDiscussionList;
		
	}

	
	/**
	 * @param aDiscussionParam
	 * @param aDiscussionId
	 */
	@Override
	public Discussion getDiscussion(DiscussionParam aDiscussionParam,
			String aDiscussionId) throws IOException, PsnErrorException {
		String oBaseUrl = getMessagingBaseUrl().getUrl() + PsnUrlCst.URI_GET_DISCUSSION;		
		
		Call<Discussion> oDiscussionCall = mPSNApiService.getDiscussion(UrlUtils.injectDataInUrl(oBaseUrl, UrlParamCst.PART_DISCUSSION_ID ,  aDiscussionId ), 
				UrlUtils.joinDataEnum( aDiscussionParam.getDiscussionParams() ),
				aDiscussionParam.getLocale().getLanguage(),
				aDiscussionParam.getSinceMessageUid() );
		
		return processResponse( oDiscussionCall.execute());
	}
	
	/**
	 * @param aYourOnlineId
	 * @param aDiscussionId
	 */
	@Override
	public void leaveFromDiscussion(String aYourOnlineId, String aDiscussionId) throws IOException, PsnErrorException {
		

		String oBaseUrl = getMessagingBaseUrl().getUrl() + PsnUrlCst.URI_LEAVE_FROM_DISCUSSION;		
		
		Map<String,String> aDataUrl = new HashMap<>();
		aDataUrl.put( UrlParamCst.URL_PARAM_ONLINE_ID, aYourOnlineId);
		aDataUrl.put( UrlParamCst.PART_DISCUSSION_ID, aDiscussionId);
				 
		Call<Void> oVoidCall = mPSNApiService.leaveFromDiscussion(
				UrlUtils.injectDataInUrl(oBaseUrl, aDataUrl) );
		
		 processResponse( oVoidCall.execute());
	}

	/**
	 * @param aOnlineIdList
	 * @param aDiscussionId
	 * @return SimpleDiscussion
	 */
	@Override
	public SendMessageResponse addMemberToDiscussion(List<String> aOnlineIdList,
			String aDiscussionId) throws IOException, PsnErrorException {
	
		String oBaseUrl = getMessagingBaseUrl().getUrl() + PsnUrlCst.URI_ADD_MEMBERS_TO_DISCUSSION;		
			
		MemberList oMemberList = new MemberList();
		for( String oOnlineId : aOnlineIdList ) {
			oMemberList.addMember( oOnlineId );
		}
		
		Call<SendMessageResponse> oSimpleDiscussionCall = mPSNApiService.addMembersToDiscussion(
				UrlUtils.injectDataInUrl(oBaseUrl, UrlParamCst.PART_DISCUSSION_ID, aDiscussionId), 
				oMemberList);
		
		return  processResponse( oSimpleDiscussionCall.execute());
	}
	
	/**
	 * return trophy base friend profile
	 * @return ServiceUrl
	 * @throws IOException
	 * @throws PsnErrorException
	 */
	private ServiceUrl getMessagingBaseUrl() throws IOException, PsnErrorException {
		
		Call<ServiceUrl> oServiceUrlCall  = mPSNApiService.getUrl( PsnUrlCst.BASE_URL_GROUP_MESSAGING );
		Response<ServiceUrl> oServiceUrlResponse = oServiceUrlCall.execute();	
		return processResponse( oServiceUrlResponse );
	}


}