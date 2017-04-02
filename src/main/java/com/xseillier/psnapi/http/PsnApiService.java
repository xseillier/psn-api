package com.xseillier.psnapi.http;

import com.xseillier.psnapi.model.AccessToken;
import com.xseillier.psnapi.model.ServiceUrl;
import com.xseillier.psnapi.model.block.BlockList;
import com.xseillier.psnapi.model.friend.*;
import com.xseillier.psnapi.model.messaging.*;
import com.xseillier.psnapi.model.param.MessageSeen;
import com.xseillier.psnapi.model.param.RequestMessage;
import com.xseillier.psnapi.model.trophy.TrophyGroupsDetailsResponse;
import com.xseillier.psnapi.model.trophy.TrophyGroupsResponse;
import com.xseillier.psnapi.model.trophy.TrophyTitleList;
import com.xseillier.psnapi.model.user.User;
import retrofit2.Call;
import retrofit2.http.*;

import static com.xseillier.psnapi.http.cst.HttpHeaderCst.*;
import static com.xseillier.psnapi.http.cst.UrlParamCst.*;
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
	 * @return
	 */
	@Headers({ HEADER_REQUESTED_WITH +": "+ PARAM_REQUESTED_WITH, HEADER_ADD_AUTHORIZATION +": "+ "add"  } )
	@GET
	Call<ServiceUrl> getUrl(@Url String aUrl );
	
	
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
	 * @param aProfileParams
	 * @param aSort
	 * @param aAvatarSize
	 * @param aPresenceType
	 * @param aFriendStatus
	 * @param aOffset
	 * @param aLimit
	 * @return
	 */
	@Headers({ HEADER_REQUESTED_WITH +": "+ PARAM_REQUESTED_WITH, HEADER_ADD_AUTHORIZATION +": "+ "add"  } )
	@GET
	Call<FriendList> getFriendList(@Url String aUrl,
			@Query( URL_PARAM_FIELDS ) String aProfileParams,
			@Query( URL_PARAM_SORT ) String aSort, 
			@Query( URL_PARAM_AVATARSIZES ) String aAvatarSize,
			@Query( URL_PARAM_PRESENCETYPE ) String aPresenceType,
			@Query( URL_PARAM_FRIENDSTATUS) String aFriendStatus,
			@Query( URL_PARAM_OFFSET ) int aOffset,
			@Query( URL_PARAM_LIMIT ) int aLimit );
	
	
	/**
	 * 
	 * @param aUrl
	 * @param aProfileParams
	 * @return
	 */
	@Headers({ HEADER_REQUESTED_WITH +": "+ PARAM_REQUESTED_WITH, HEADER_ADD_AUTHORIZATION +": "+ "add"  } )
	@GET
	Call<FriendProfile> getFriendDetail(@Url String aUrl,
			@Query( URL_PARAM_FIELDS ) String aProfileParams,
			@Query( URL_PARAM_AVATARSIZES ) String aAvatarSize );
	
	
	
	/**
	 * 
	 * @param aUrl
	 * @param aOnlineIds
	 * @param aProfileParams
	 * @return
	 */
	@Headers({ HEADER_REQUESTED_WITH +": "+ PARAM_REQUESTED_WITH, HEADER_ADD_AUTHORIZATION +": "+ "add"  } )
	@GET
	Call<ProfileList> getMultiFriendDetail(@Url String aUrl,
			@Query( URL_PARAM_ONLINE_ID ) String aOnlineIds,
			@Query( URL_PARAM_FIELDS ) String aProfileParams,
			@Query( URL_PARAM_AVATARSIZES ) String aAvatarSize );
	
	
	
	/**
	 * del friend from friend list
	 * @param aUrl
	 * @return
	 */
	@Headers({ HEADER_REQUESTED_WITH +": "+ PARAM_REQUESTED_WITH, HEADER_ADD_AUTHORIZATION +": "+ "add"  } )
	@DELETE
	Call<Void> delFriend(@Url String aUrl );
	
	
	
	/**
	 * add friend from friend list ( new Friend must accept)
	 * @param aUrl
	 * @return
	 */
	@Headers({ HEADER_REQUESTED_WITH +": "+ PARAM_REQUESTED_WITH, HEADER_CONTENT_TYPE +": "+ TYPE_MINE_JSON, HEADER_ADD_AUTHORIZATION +": "+ "add"  } )
	@POST
	Call<Void> addFriend(@Url String aUrl,
			@Body RequestMessage aRequestMessage);
	
	
	
	/**
	 * 
	 * @param aUrl
	 * @param aProfileParams
	 * @param aAvatarSize
	 * @param aPresenceType
	 * @param aDirection
	 * @param aOffset
	 * @param aLimit
	 * @return
	 */
	@Headers({ HEADER_REQUESTED_WITH +": "+ PARAM_REQUESTED_WITH, HEADER_CONTENT_TYPE +": "+ TYPE_MINE_JSON, HEADER_ADD_AUTHORIZATION +": "+ "add"  } )
	@GET
	Call<FriendSendRequestList> getFriendSendRequest(@Url String aUrl,
			@Query( URL_PARAM_FIELDS ) String aProfileParams,
			@Query( URL_PARAM_SORT ) String aSort, 
			@Query( URL_PARAM_AVATARSIZES ) String aAvatarSize,
			@Query( URL_PARAM_PRESENCETYPE ) String aPresenceType,
			@Query( URL_PARAM_DIRECTION ) String aDirection,
			@Query( URL_PARAM_OFFSET ) int aOffset,
			@Query( URL_PARAM_LIMIT ) int aLimit );
	
	
	/**
	 * 
	 * @param aUrl
	 * @param aProfileParams
	 * @param aAvatarSize
	 * @param aPresenceType
	 * @param aDirection
	 * @param aOffset
	 * @param aLimit
	 * @return
	 */
	@Headers({ HEADER_REQUESTED_WITH +": "+ PARAM_REQUESTED_WITH, HEADER_CONTENT_TYPE +": "+ TYPE_MINE_JSON , HEADER_ADD_AUTHORIZATION +": "+ "add" } )
	@GET
	Call<FriendReceiveRequestList> getFriendReceiveRequest(@Url String aUrl,
			@Query( URL_PARAM_FIELDS ) String aProfileParams,
			@Query( URL_PARAM_SORT ) String aSort, 
			@Query( URL_PARAM_AVATARSIZES ) String aAvatarSize,
			@Query( URL_PARAM_PRESENCETYPE ) String aPresenceType,
			@Query( URL_PARAM_DIRECTION ) String aDirection,
			@Query( URL_PARAM_OFFSET ) int aOffset,
			@Query( URL_PARAM_LIMIT ) int aLimit );
	
	
	/**
	 * 
	 * @param aUrl
	 * @param aTrophyParam
	 * @param aNpLanguage
	 * @param aImageSize
	 * @param aPlatform
	 * @param aOffset
	 * @param aLimit
	 * @return
	 */
	@Headers({ HEADER_REQUESTED_WITH +": "+ PARAM_REQUESTED_WITH, HEADER_ADD_AUTHORIZATION +": "+ "add"  } )
	@GET
	Call<TrophyTitleList> getTrophyList(@Url String aUrl,
			@Query( URL_PARAM_FIELDS ) String aTrophyParam,
			@Query( URL_PARAM_NP_LANGUAGE ) String aNpLanguage,
			@Query( URL_PARAM_IMAGESIZE ) String aImageSize,
			@Query( URL_PARAM_PLATFORM ) String aPlatform,
			@Query( URL_PARAM_OFFSET ) int aOffset,
			@Query( URL_PARAM_LIMIT ) int aLimit );
	
	
	
	/**
	 * 
	 * @param aUrl
	 * @param aTrophyParam
	 * @param aNpLanguage
	 * @param aImageSize
	 * @return
	 */
	@Headers({ HEADER_REQUESTED_WITH +": "+ PARAM_REQUESTED_WITH, HEADER_ADD_AUTHORIZATION +": "+ "add"  } )
	@GET
	Call<TrophyGroupsResponse> getTrophyGroups(@Url String aUrl,
			@Query( URL_PARAM_FIELDS ) String aTrophyParam,
			@Query( URL_PARAM_NP_LANGUAGE ) String aNpLanguage,
			@Query( URL_PARAM_IMAGESIZE ) String aImageSize );
	

	/**
	 * 
	 * @param aUrl
	 * @param aTrophyParam
	 * @param aNpLanguage
	 * @param aImageSize
	 * @return
	 */
	@Headers({ HEADER_REQUESTED_WITH +": "+ PARAM_REQUESTED_WITH, HEADER_ADD_AUTHORIZATION +": "+ "add"  } )
	@GET
	Call<TrophyGroupsDetailsResponse> getTrophyGroupsDetail(@Url String aUrl,
			@Query( URL_PARAM_FIELDS ) String aTrophyParam,
			@Query( URL_PARAM_NP_LANGUAGE ) String aNpLanguage,
			@Query( URL_PARAM_IMAGESIZE ) String aImageSize );
	
	
	/**
	 * 
	 * @param aUrl
	 * @return
	 */
	@Headers({ HEADER_REQUESTED_WITH +": "+ PARAM_REQUESTED_WITH, HEADER_ADD_AUTHORIZATION +": "+ "add"  } )
	@GET
	Call<Void> blockProfile(@Url String aUrl );
	
	/**
	 * 
	 * @param aUrl
	 * @return
	 */
	@Headers({ HEADER_REQUESTED_WITH +": "+ PARAM_REQUESTED_WITH, HEADER_ADD_AUTHORIZATION +": "+ "add"  } )
	@DELETE
	Call<Void> unblockProfile(@Url String aUrl );

	
	/**
	 * 
	 * @param aUrl
	 * @param aProfileParams
	 * @param aSort
	 * @param aAvatarSize
	 * @param aOffset
	 * @param aLimit
	 * @return
	 */
	@Headers({ HEADER_REQUESTED_WITH +": "+ PARAM_REQUESTED_WITH, HEADER_ADD_AUTHORIZATION +": "+ "add"  } )
	@GET
	Call<BlockList> getBlockProfileList(@Url String aUrl,
			@Query( URL_PARAM_FIELDS ) String aProfileParams,
			@Query( URL_PARAM_SORT ) String aSort, 
			@Query( URL_PARAM_AVATARSIZES ) String aAvatarSize,
			@Query( URL_PARAM_OFFSET ) int aOffset,
			@Query( URL_PARAM_LIMIT ) int aLimit );
	
	
	
	/**
	 *  send message
	 * @param aUrl
	 * @param aMessage
	 * @return
	 */
	@Headers({ HEADER_REQUESTED_WITH +": "+ PARAM_REQUESTED_WITH, HEADER_ADD_AUTHORIZATION +": "+ "add"  } )
	@POST
	Call<SendMessageResponse> createDiscussion(@Url String aUrl,
			@Body SendMessage aMessage );

	
	/**
	 * 
	 * @param aUrl
	 * @param aMessageId
	 * @param aMessageSeen
	 * @return
	 */
	@Headers({ HEADER_REQUESTED_WITH +": "+ PARAM_REQUESTED_WITH, HEADER_ADD_AUTHORIZATION +": "+ "add"  } )
	@PUT
	Call<Void> markMessageAsSeen(@Url String aUrl,
			@Query( URL_MESSAGE_UID ) String aMessageId,
			@Body MessageSeen aMessageSeen );

	
	/**
	 * 
	 * @param aUrl
	 * @param aMessage
	 * @return
	 */
	@Headers({ HEADER_REQUESTED_WITH +": "+ PARAM_REQUESTED_WITH, HEADER_ADD_AUTHORIZATION +": "+ "add"  } )
	@POST
	Call<SendMessageResponse> addMessageToDiscussion(@Url String aUrl,
			@Body SendMessage aMessage );
	
	/**
	 * 
	 * @param aUrl
	 * @param aDiscussionParam
	 * @param aNpLanguage
	 * @return
	 */
	@Headers({ HEADER_REQUESTED_WITH +": "+ PARAM_REQUESTED_WITH, HEADER_ADD_AUTHORIZATION +": "+ "add"  } )
	@GET
	Call<DiscussionList> getListDiscussion(@Url String aUrl,
			@Query( URL_PARAM_FIELDS ) String aDiscussionParam,
			@Query( URL_PARAM_NP_LANGUAGE ) String aNpLanguage,
			@Query( URL_PARAM_OFFSET ) int aOffset,
			@Query( URL_PARAM_LIMIT ) int aLimit );
	
	
	
	@Headers({ HEADER_REQUESTED_WITH +": "+ PARAM_REQUESTED_WITH, HEADER_ADD_AUTHORIZATION +": "+ "add"  } )
	@GET
	Call<Discussion> getDiscussion(@Url String aUrl,
			@Query( URL_PARAM_FIELDS ) String aDiscussionParam,
			@Query( URL_PARAM_NP_LANGUAGE ) String aNpLanguage,
			@Query( URL_PARAM_SINCE_MESSAGE_UID ) Long aSinceMessageUid);
	
	
	
	/**
	 * 
	 * @param aUrl
	 * @return
	 */
	@Headers({ HEADER_REQUESTED_WITH +": "+ PARAM_REQUESTED_WITH, HEADER_ADD_AUTHORIZATION +": "+ "add"  } )
	@DELETE
	Call<Void> leaveFromDiscussion(@Url String aUrl );
	
	/**
	 * 
	 * @param aUrl
	 * @param aMemberList
	 * @return
	 */
	@Headers({ HEADER_REQUESTED_WITH +": "+ PARAM_REQUESTED_WITH, HEADER_ADD_AUTHORIZATION +": "+ "add"  } )
	@POST
	Call<SendMessageResponse> addMembersToDiscussion(@Url String aUrl,
			@Body MemberList aMemberList);

}
