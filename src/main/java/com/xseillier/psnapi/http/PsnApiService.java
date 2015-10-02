package com.xseillier.psnapi.http;

import static com.xseillier.psnapi.http.cst.HttpHeaderCst.HEADER_AUTHORIZATION;
import static com.xseillier.psnapi.http.cst.HttpHeaderCst.HEADER_CONTENT_TYPE;
import static com.xseillier.psnapi.http.cst.HttpHeaderCst.HEADER_REQUESTED_WITH;
import static com.xseillier.psnapi.http.cst.HttpHeaderCst.HEADER_X_NP_ACCESS_TOKEN;
import static com.xseillier.psnapi.http.cst.HttpHeaderCst.TYPE_MINE_JSON;
import static com.xseillier.psnapi.http.cst.UrlParamCst.PARAM_REQUESTED_WITH;
import static com.xseillier.psnapi.http.cst.UrlParamCst.URL_PARAM_AUTH_TOKEN_REDIRECT_URI;
import static com.xseillier.psnapi.http.cst.UrlParamCst.URL_PARAM_AVATARSIZES;
import static com.xseillier.psnapi.http.cst.UrlParamCst.URL_PARAM_CLIENT_ID;
import static com.xseillier.psnapi.http.cst.UrlParamCst.URL_PARAM_CLIENT_SECRET;
import static com.xseillier.psnapi.http.cst.UrlParamCst.URL_PARAM_CODE;
import static com.xseillier.psnapi.http.cst.UrlParamCst.URL_PARAM_DUID;
import static com.xseillier.psnapi.http.cst.UrlParamCst.URL_PARAM_FIELDS;
import static com.xseillier.psnapi.http.cst.UrlParamCst.URL_PARAM_FRIENDSTATUS;
import static com.xseillier.psnapi.http.cst.UrlParamCst.URL_PARAM_GRANT_TYPE;
import static com.xseillier.psnapi.http.cst.UrlParamCst.URL_PARAM_IMAGESIZE;
import static com.xseillier.psnapi.http.cst.UrlParamCst.URL_PARAM_LIMIT;
import static com.xseillier.psnapi.http.cst.UrlParamCst.URL_PARAM_NP_LANGUAGE;
import static com.xseillier.psnapi.http.cst.UrlParamCst.URL_PARAM_OFFSET;
import static com.xseillier.psnapi.http.cst.UrlParamCst.URL_PARAM_ONLINE_ID;
import static com.xseillier.psnapi.http.cst.UrlParamCst.URL_PARAM_PLATFORM;
import static com.xseillier.psnapi.http.cst.UrlParamCst.URL_PARAM_PRESENCETYPE;
import static com.xseillier.psnapi.http.cst.UrlParamCst.URL_PARAM_REFRESH_TOKEN;
import static com.xseillier.psnapi.http.cst.UrlParamCst.URL_PARAM_SCOPE;
import static com.xseillier.psnapi.http.cst.UrlParamCst.URL_PARAM_SORT;
import static com.xseillier.psnapi.http.cst.UrlParamCst.URL_PARAM_STATE;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Query;
import retrofit.http.Url;

import com.xseillier.psnapi.model.AccessToken;
import com.xseillier.psnapi.model.ServiceUrl;
import com.xseillier.psnapi.model.block.BlockList;
import com.xseillier.psnapi.model.friend.FriendList;
import com.xseillier.psnapi.model.friend.FriendProfile;
import com.xseillier.psnapi.model.friend.ProfileList;
import com.xseillier.psnapi.model.param.RequestMessage;
import com.xseillier.psnapi.model.trophy.TrophyGroupsDetailsResponse;
import com.xseillier.psnapi.model.trophy.TrophyGroupsResponse;
import com.xseillier.psnapi.model.trophy.TrophyTitleList;
import com.xseillier.psnapi.model.user.User;
/**
 *
 * @author xseillier
 * @version 1.0 15 sept. 2015
 */
public interface PsnApiService {

	/**
	 * get auth token
	 * @param aGrantType
	 * @param aClientId
	 * @param aClientSecret
	 * @param aLoginCode
	 * @param aRedirectUrl
	 * @param aState
	 * @param aScope
	 * @param aDuid
	 * @return
	 */
	@FormUrlEncoded 
	@POST("/2.0/oauth/token")
	Call<AccessToken> getAuthToken( @Field( URL_PARAM_GRANT_TYPE ) String aGrantType,
			@Field( URL_PARAM_CLIENT_ID ) String aClientId,
			@Field( URL_PARAM_CLIENT_SECRET ) String aClientSecret,
			@Field( URL_PARAM_CODE ) String aLoginCode,
			@Field( URL_PARAM_AUTH_TOKEN_REDIRECT_URI ) String aRedirectUrl,
			@Field( URL_PARAM_STATE ) String aState,
			@Field( URL_PARAM_SCOPE ) String aScope,
			@Field( URL_PARAM_DUID ) String  aDuid );
	
	
	/**
	 *  refresh auth token
	 * @param aGrantType
	 * @param aClientId
	 * @param aClientSecret
	 * @param aScope
	 * @param aDuid
	 * @param aRefreshToken
	 * @return
	 */
	@FormUrlEncoded 
	@POST("/2.0/oauth/token")
	Call<AccessToken> refreshAuthToken( @Field( URL_PARAM_GRANT_TYPE ) String aGrantType,
			@Field( URL_PARAM_CLIENT_ID ) String aClientId,
			@Field( URL_PARAM_CLIENT_SECRET ) String aClientSecret,
			@Field( URL_PARAM_SCOPE ) String aScope,
			@Field( URL_PARAM_DUID ) String  aDuid,
			@Field( URL_PARAM_REFRESH_TOKEN ) String  aRefreshToken );
	
	

	/**
	 * return base url for rest service
	 * @param aUrl
	 * @param aAuthorisation
	 * @return
	 */
	@Headers({ HEADER_REQUESTED_WITH +": "+ PARAM_REQUESTED_WITH } )
	@GET
	Call<ServiceUrl> getUrl(@Url String aUrl, @Header( HEADER_AUTHORIZATION ) String aAuthorisation );
	
	
	/**
	 * return the profile of connected account
	 * @param aUrl
	 * @param aAuthToken
	 * @return
	 */
	@GET
	Call<User> getMyProfile(@Url String aUrl, @Header( HEADER_X_NP_ACCESS_TOKEN ) String aAuthToken  );
	
	
	/**
	 * return the friend list
	 * @param aUrl
	 * @param aAuthorisation
	 * @param aProfileParam
	 * @param aSort
	 * @param aAvatarSize
	 * @param aPresenceType
	 * @param aFriendStatus
	 * @param aOffset
	 * @param aLimit
	 * @return
	 */
	@Headers({ HEADER_REQUESTED_WITH +": "+ PARAM_REQUESTED_WITH } )
	@GET
	Call<FriendList> getFriendList(@Url String aUrl,
			@Header( HEADER_AUTHORIZATION ) String aAuthorisation,
			@Query( URL_PARAM_FIELDS ) String aProfileParam, 
			@Query( URL_PARAM_SORT ) String aSort, 
			@Query( URL_PARAM_AVATARSIZES ) String aAvatarSize,
			@Query( URL_PARAM_PRESENCETYPE ) String aPresenceType,
			@Query( URL_PARAM_FRIENDSTATUS) String aFriendStatus,
			@Query( URL_PARAM_OFFSET ) int aOffset,
			@Query( URL_PARAM_LIMIT ) int aLimit );
	
	
	/**
	 * 
	 * @param aUrl
	 * @param aAuthorisation
	 * @param aProfileParam
	 * @return
	 */
	@Headers({ HEADER_REQUESTED_WITH +": "+ PARAM_REQUESTED_WITH } )
	@GET
	Call<FriendProfile> getFriendDetail(@Url String aUrl,
			@Header( HEADER_AUTHORIZATION ) String aAuthorisation,
			@Query( URL_PARAM_FIELDS ) String aProfileParam,
			@Query( URL_PARAM_AVATARSIZES ) String aAvatarSize );
	
	
	
	/**
	 * 
	 * @param aUrl
	 * @param aAuthorisation
	 * @param aOnlineIds
	 * @param aProfileParam
	 * @return
	 */
	@Headers({ HEADER_REQUESTED_WITH +": "+ PARAM_REQUESTED_WITH } )
	@GET
	Call<ProfileList> getMultiFriendDetail(@Url String aUrl,
			@Header( HEADER_AUTHORIZATION ) String aAuthorisation,
			@Query( URL_PARAM_ONLINE_ID ) String aOnlineIds,
			@Query( URL_PARAM_FIELDS ) String aProfileParam,
			@Query( URL_PARAM_AVATARSIZES ) String aAvatarSize );
	
	
	
	/**
	 * del friend from friend list
	 * @param aUrl
	 * @param aAuthorisation
	 * @return
	 */
	@Headers({ HEADER_REQUESTED_WITH +": "+ PARAM_REQUESTED_WITH } )
	@DELETE
	Call<Void> delFriend(@Url String aUrl,
			@Header( HEADER_AUTHORIZATION ) String aAuthorisation );
	
	
	
	/**
	 * add friend from friend list ( new Friend must accept)
	 * @param aUrl
	 * @param aAuthorisation
	 * @return
	 */
	@Headers({ HEADER_REQUESTED_WITH +": "+ PARAM_REQUESTED_WITH, HEADER_CONTENT_TYPE +": "+ TYPE_MINE_JSON } )
	@POST
	Call<Void> addFriend(@Url String aUrl,
			@Header( HEADER_AUTHORIZATION ) String aAuthorisation,
			@Body RequestMessage aRequestMessage);
	
	
	/**
	 * 
	 * @param aUrl
	 * @param aAuthorisation
	 * @param aTrophyParam
	 * @param aNpLanguage
	 * @param aImageSize
	 * @param aPlatform
	 * @param aOffset
	 * @param aLimit
	 * @return
	 */
	@Headers({ HEADER_REQUESTED_WITH +": "+ PARAM_REQUESTED_WITH } )
	@GET
	Call<TrophyTitleList> getTrophyList(@Url String aUrl,
			@Header( HEADER_AUTHORIZATION ) String aAuthorisation,
			@Query( URL_PARAM_FIELDS ) String aTrophyParam,
			@Query( URL_PARAM_NP_LANGUAGE ) String aNpLanguage,
			@Query( URL_PARAM_IMAGESIZE ) String aImageSize,
			@Query( URL_PARAM_PLATFORM ) String aPlatform,
			@Query( URL_PARAM_OFFSET ) int aOffset,
			@Query( URL_PARAM_LIMIT ) int aLimit );
	
	
	
	/**
	 * 
	 * @param aUrl
	 * @param aAuthorisation
	 * @param aTrophyParam
	 * @param aNpLanguage
	 * @param aImageSize
	 * @return
	 */
	@Headers({ HEADER_REQUESTED_WITH +": "+ PARAM_REQUESTED_WITH } )
	@GET
	Call<TrophyGroupsResponse> getTrophyGroups(@Url String aUrl,
			@Header( HEADER_AUTHORIZATION ) String aAuthorisation,
			@Query( URL_PARAM_FIELDS ) String aTrophyParam,
			@Query( URL_PARAM_NP_LANGUAGE ) String aNpLanguage,
			@Query( URL_PARAM_IMAGESIZE ) String aImageSize );
	

	/**
	 * 
	 * @param aUrl
	 * @param aAuthorisation
	 * @param aTrophyParam
	 * @param aNpLanguage
	 * @param aImageSize
	 * @return
	 */
	@Headers({ HEADER_REQUESTED_WITH +": "+ PARAM_REQUESTED_WITH } )
	@GET
	Call<TrophyGroupsDetailsResponse> getTrophyGroupsDetail(@Url String aUrl,
			@Header( HEADER_AUTHORIZATION ) String aAuthorisation,
			@Query( URL_PARAM_FIELDS ) String aTrophyParam,
			@Query( URL_PARAM_NP_LANGUAGE ) String aNpLanguage,
			@Query( URL_PARAM_IMAGESIZE ) String aImageSize );
	
	
	/**
	 * 
	 * @param aUrl
	 * @param aAuthorisation
	 * @return
	 */
	@Headers({ HEADER_REQUESTED_WITH +": "+ PARAM_REQUESTED_WITH } )
	@GET
	Call<Void> blockProfile(@Url String aUrl,
			@Header( HEADER_AUTHORIZATION ) String aAuthorisation );
	
	/**
	 * 
	 * @param aUrl
	 * @param aAuthorisation
	 * @return
	 */
	@Headers({ HEADER_REQUESTED_WITH +": "+ PARAM_REQUESTED_WITH } )
	@DELETE
	Call<Void> unblockProfile(@Url String aUrl,
			@Header( HEADER_AUTHORIZATION ) String aAuthorisation );

	
	/**
	 * 
	 * @param aUrl
	 * @param aAuthorisation
	 * @param aProfileParam
	 * @param aSort
	 * @param aAvatarSize
	 * @param aOffset
	 * @param aLimit
	 * @return
	 */
	@Headers({ HEADER_REQUESTED_WITH +": "+ PARAM_REQUESTED_WITH } )
	@GET
	Call<BlockList> getBlockProfileList(@Url String aUrl,
			@Header( HEADER_AUTHORIZATION ) String aAuthorisation,
			@Query( URL_PARAM_FIELDS ) String aProfileParam, 
			@Query( URL_PARAM_SORT ) String aSort, 
			@Query( URL_PARAM_AVATARSIZES ) String aAvatarSize,
			@Query( URL_PARAM_OFFSET ) int aOffset,
			@Query( URL_PARAM_LIMIT ) int aLimit );
}
