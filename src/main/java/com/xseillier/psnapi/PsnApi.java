package com.xseillier.psnapi;

import java.io.IOException;
import java.util.List;

import com.xseillier.psnapi.http.exception.AccessDeniedByPrivacyLevelException;
import com.xseillier.psnapi.http.exception.LoginException;
import com.xseillier.psnapi.http.exception.PsnErrorException;
import com.xseillier.psnapi.model.PsnContext;
import com.xseillier.psnapi.model.block.BlockList;
import com.xseillier.psnapi.model.friend.FriendList;
import com.xseillier.psnapi.model.friend.FriendProfile;
import com.xseillier.psnapi.model.friend.ProfileList;
import com.xseillier.psnapi.model.param.BlockPaginationParam;
import com.xseillier.psnapi.model.param.FriendPaginationParam;
import com.xseillier.psnapi.model.param.ProfileParam;
import com.xseillier.psnapi.model.param.TrophyPaginationParam;
import com.xseillier.psnapi.model.param.TrophyParam;
import com.xseillier.psnapi.model.trophy.TrophyGroupsDetailsResponse;
import com.xseillier.psnapi.model.trophy.TrophyGroupsResponse;
import com.xseillier.psnapi.model.trophy.TrophyTitleList;
import com.xseillier.psnapi.model.user.User;

/**
 *
 * @author xseillier
 * @version 1.0 15 sept. 2015
 */
public interface PsnApi {

	
	/**
	 * set Psn Context. It used to set previous context, and permite to by pass login phase (if context is ok ;) )
	 * @param aPsnContext
	 */
	public void setPsnContext( PsnContext aPsnContext );
	
	
	/**
	 * get current PsnContext
	 * @return
	 */
	public PsnContext getPsnContext();
	
	
	
	/**
	 * refresh Auth Token
	 */
	public void refreshAuthToken() throws IOException, LoginException, PsnErrorException;
	
	/**
	 * Login to PSN Network
	 * @param aPSNLogin
	 * @param aPSNPassword
	 */
	public void login( String aPSNLogin, String aPSNPassword ) throws LoginException, IOException, PsnErrorException;
	
	/**
	 * return information of connected account
	 * @return
	 * @throws IOException
	 */
	public User getAccountInformation() throws IOException, PsnErrorException;
	
	
		
	/**
	 * to get friend list 
	 * @param aOnlineId your onlineId or others ( friend, unknown ) 
	 * @param aOffset
	 * @param aLimit
	 * @param aAvatarSize
	 * @param aProfileParams
	 * @return
	 * @throws IOException
	 * @throws PsnErrorException
	 */
	public FriendList getFriendList(  String aOnlineId, ProfileParam aProfileParam, FriendPaginationParam aPagination ) throws IOException, AccessDeniedByPrivacyLevelException, PsnErrorException;
	
	
	
	/**
	 * return detail of aOnlineId friend
	 * @param aOnlineId
	 * @param aProfileParams
	 * @return
	 * @throws IOException
	 */
	public FriendProfile getFriendDetail( String aOnlineId, ProfileParam aProfileParam ) throws IOException, PsnErrorException;
	
	
	/**
	 * 
	 * @param aOnlineId
	 * @param aProfileParams
	 * @return
	 * @throws IOException
	 */
	public ProfileList getMultiFriendDetail( List<String> aOnlineId, ProfileParam aProfileParam ) throws IOException, PsnErrorException;
	
	
	/**
	 * remove Friend from your friend list
	 * @since 0.2 beta
	 * @param aYourOnlineId
	 * @param FriendOnlineId
	 */
	public void delFriend( String aYourOnlineId, String FriendOnlineId) throws IOException, PsnErrorException;
	
	
	/**
	 * add Friend from your friend list / use olso to accept request add friend
	 * @since 0.2 beta
	 * @param aYourOnlineId
	 * @param FriendOnlineId
	 * @throws IOException
	 * @throws PsnErrorException
	 */
	public void addFriend( String aYourOnlineId, String FriendOnlineId) throws IOException, PsnErrorException;
	
	/**
	 * 
	 * @param aYourOnlineId
	 * @param FriendOnlineId
	 * @param aRequestMessage
	 * @throws IOException
	 * @throws PsnErrorException
	 */
	public void addFriend( String aYourOnlineId, String FriendOnlineId, String aRequestMessage ) throws IOException, PsnErrorException;
	
	/**
	 * 
	 * @param aLocale
	 * @param aOffset offset of start
	 * @param aLimit size of trophy (max 64)
	 * @param aAvatarSize
	 * @return
	 * @throws IOException
	 * @throws PsnErrorException
	 */
	public TrophyTitleList getTrophyList( TrophyParam aTrophyParam, TrophyPaginationParam aPagination ) throws IOException, PsnErrorException;
	
	
	/**
	 * 
	 * @param aNameId
	 * @param aLocale
	 * @param aImageSize
	 * @return
	 * @throws IOException
	 * @throws PsnErrorException
	 */
	public TrophyGroupsResponse getTrophyGroups( String aNameId, TrophyParam aTrophyParam ) throws IOException, PsnErrorException;
	
	
	/**
	 * 
	 * @param aGameId
	 * @param aTrophyGroupId
	 * @param aLocale
	 * @param aImageSize
	 * @return
	 * @throws IOException
	 * @throws PsnErrorException
	 */
	public TrophyGroupsDetailsResponse getTrophyGroupsDetail( String aGameId, String aTrophyGroupId, TrophyParam aTrophyParam ) throws IOException, PsnErrorException;
	
	
	/**
	 * 
	 * @param aProfileOnlineId
	 * @throws IOException
	 * @throws PsnErrorException
	 */
	public void blockProfile( String oYourOnlineId, String aProfileOnlineId )  throws IOException, PsnErrorException;
	
	/**
	 * 
	 * @param aProfileOnlineId
	 * @throws IOException
	 * @throws PsnErrorException
	 */
	public void unblockProfile( String oYourOnlineId, String aProfileOnlineId )  throws IOException, PsnErrorException;
	
	/**
	 * 
	 * @param aOnlineId
	 * @param aProfileParam
	 * @param aPagination
	 * @return
	 * @throws IOException
	 * @throws AccessDeniedByPrivacyLevelException
	 * @throws PsnErrorException
	 */
	public BlockList getBlockProfileList(  String aOnlineId, ProfileParam aProfileParam, BlockPaginationParam aPagination ) throws IOException, AccessDeniedByPrivacyLevelException, PsnErrorException;
	
}
