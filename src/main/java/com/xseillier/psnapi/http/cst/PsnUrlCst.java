package com.xseillier.psnapi.http.cst;
/**
 *
 * @author xseillier
 * @version 1.0 16 sept. 2015
 */
public class PsnUrlCst {

	
	
	public static final String URL_BASE	                   = "https://auth.api.sonyentertainmentnetwork.com"; 
	public static final String URL_LOGIN_STEP_1            = "https://reg.api.km.playstation.net/regcam/mobile/sign-in.html";
	public static final String URL_LOGIN_STEP_2            = "https://auth.api.sonyentertainmentnetwork.com/login.do";	
	
	public static final String BASE_URL_ME_PROFILE         = "https://vl.api.np.km.playstation.net/vl/api/v1/mobile/users/me/info";	
	public static final String BASE_URL_TROPHY             = "https://asm.np.community.playstation.net/asm/v1/apps/me/baseUrls/trophy";
	public static final String BASE_URL_ACTIVITY_FEED      = "https://asm.np.community.playstation.net/asm/v1/apps/me/baseUrls/activityFeed";
	public static final String BASE_URL_USER_PROFILE       = "https://asm.np.community.playstation.net/asm/v1/apps/me/baseUrls/userProfile";
	public static final String BASE_URL_GAME_CUSTOMER_DATA = "https://asm.np.community.playstation.net/asm/v1/apps/me/baseUrls/gameCustomData";	
	public static final String BASE_URL_GROUP_MESSAGING    = "https://asm.np.community.playstation.net/asm/v1/apps/me/baseUrls/groupMessaging";
	public static final String BASE_URL_NOTIFICATION_LIST  = "https://asm.np.community.playstation.net/asm/v1/apps/me/baseUrls/notificationList";
	public static final String BASE_URL_SESSION_INVITATION = "https://asm.np.community.playstation.net/asm/v1/apps/me/baseUrls/sessionInvitation";	

	public static final String URI_FRIEND_LIST             = "/v1/users/{onlineId}/friendList";
	public static final String URI_DEL_FRIEND              = URI_FRIEND_LIST + "/{onlineFriendId}"; 
	public static final String URI_ADD_FRIEND              = URI_DEL_FRIEND; 
	public static final String URI_FRIEND_DETAIL           = "/v1/users/{onlineId}/profile"; 
	public static final String URI_MULTI_FRIEND_DETAIL     = "/v1/profiles"; 
	public static final String URI_TROPHY_TITLES           = "/v1/trophyTitles";
	public static final String URI_TROPHY_GROUPS           = "/v1/trophyTitles/{gameId}/trophyGroups";
	public static final String URI_TROPHY_GROUPS_DETAILS   = "/v1/trophyTitles/{gameId}/trophyGroups/{trophyGroupId}/trophies";
	
	public static final String URI_BLOCK_LIST_PROFILE      = "/v1/users/{onlineId}/blockList"; 
	public static final String URI_BLOCK_PROFILE           = URI_BLOCK_LIST_PROFILE + "/{profileOnlineId}"; 
	public static final String URI_UNBLOCK_PROFILE         = URI_BLOCK_PROFILE; 
	
}
