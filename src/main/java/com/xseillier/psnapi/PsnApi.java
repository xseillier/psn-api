package com.xseillier.psnapi;

import com.xseillier.psnapi.http.exception.AccessDeniedByPrivacyLevelException;
import com.xseillier.psnapi.http.exception.LoginException;
import com.xseillier.psnapi.http.exception.PsnErrorException;
import com.xseillier.psnapi.model.PsnContext;
import com.xseillier.psnapi.model.block.BlockList;
import com.xseillier.psnapi.model.block.BlockPagination;
import com.xseillier.psnapi.model.friend.*;
import com.xseillier.psnapi.model.messaging.Discussion;
import com.xseillier.psnapi.model.messaging.DiscussionList;
import com.xseillier.psnapi.model.messaging.DiscussionPagination;
import com.xseillier.psnapi.model.messaging.SendMessageResponse;
import com.xseillier.psnapi.model.param.DiscussionParam;
import com.xseillier.psnapi.model.param.ProfileParam;
import com.xseillier.psnapi.model.param.ProfileV2Param;
import com.xseillier.psnapi.model.param.TrophyParam;
import com.xseillier.psnapi.model.trophy.TrophyGroupsDetailsResponse;
import com.xseillier.psnapi.model.trophy.TrophyGroupsResponse;
import com.xseillier.psnapi.model.trophy.TrophyPagination;
import com.xseillier.psnapi.model.trophy.TrophyTitleList;
import com.xseillier.psnapi.model.user.User;

import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

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
	 * 
	 * @param aHost
	 * @param aPort
	 */
	public void addProxy( String aHost, int aPort ) throws UnknownHostException;
	
	
	
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
	 * @param aProfileParams
	 * @param aPagination
	 * @return
	 * @throws IOException
	 * @throws PsnErrorException
	 */
	public FriendList getFriendList(  String aOnlineId, ProfileParam aProfileParams, FriendPagination aPagination ) throws IOException, AccessDeniedByPrivacyLevelException, PsnErrorException;
	
	
	
	/**
	 * return detail of aOnlineId friend
	 * @param aOnlineId
	 * @param aProfileParams
	 * @return
	 * @throws IOException
	 */
	public FriendProfile getFriendDetail( String aOnlineId, ProfileParam aProfileParams ) throws IOException, PsnErrorException;
	
	
	/**
	 * 
	 * @param aOnlineId
	 * @param aProfileParams
	 * @return
	 * @throws IOException
	 * @throws PsnErrorException
	 */
	public FriendProfile getFriendDetailV2( String aOnlineId, ProfileV2Param aProfileParams ) throws IOException, PsnErrorException;
	
	
	/**
	 * 
	 * @param aOnlineId
	 * @param aProfileParams
	 * @return
	 * @throws IOException
	 */
	public ProfileList getMultiFriendDetail( List<String> aOnlineId, ProfileParam aProfileParams ) throws IOException, PsnErrorException;
	
	
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
	 * @param aProfileParams
	 * @param aPagination
	 * @return
	 * @throws IOException
	 * @throws PsnErrorException
	 */
	public FriendSendRequestList getFriendSendRequest( ProfileParam aProfileParams, FriendPagination aPagination )  throws IOException, PsnErrorException;
	
	
	/**
	 * 
	 * @param aProfileParams
	 * @param aPagination
	 * @return
	 * @throws IOException
	 * @throws PsnErrorException
	 */
	public FriendReceiveRequestList getFriendReceiveRequest( ProfileParam aProfileParams, FriendPagination aPagination )  throws IOException, PsnErrorException;
	
	
	/**
	 * 
	 * @param aTrophyParam
	 * @param aPagination offset of start
	 * @return
	 * @throws IOException
	 * @throws PsnErrorException
	 */
	public TrophyTitleList getTrophyList( TrophyParam aTrophyParam, TrophyPagination aPagination ) throws IOException, PsnErrorException;
	
	
	/**
	 * 
	 * @param aNameId
	 * @param aTrophyParam
	 * @return
	 * @throws IOException
	 * @throws PsnErrorException
	 */
	public TrophyGroupsResponse getTrophyGroups( String aNameId, TrophyParam aTrophyParam ) throws IOException, PsnErrorException;
	
	
	/**
	 * 
	 * @param aGameId
	 * @param aTrophyGroupId
	 * @param aTrophyParam
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
	 * @param aProfileParams
	 * @param aPagination
	 * @return
	 * @throws IOException
	 * @throws AccessDeniedByPrivacyLevelException
	 * @throws PsnErrorException
	 */
	public BlockList getBlockProfileList(  String aOnlineId, ProfileParam aProfileParams, BlockPagination aPagination ) throws IOException, AccessDeniedByPrivacyLevelException, PsnErrorException;
	
	
	/**
	 * 
	 * @param aMessage
	 * @return
	 * @throws IOException
	 * @throws PsnErrorException
	 */
	public SendMessageResponse createDiscussion( List<String> aOnlineIdList, String aMessage) throws IOException, PsnErrorException;
	
	/**
	 * 
	 * @param aOnlineIdList
	 * @param aMessage
	 * @param aImage
	 * @return
	 * @throws IOException
	 * @throws PsnErrorException
	 */
	public SendMessageResponse createDiscussion( List<String> aOnlineIdList, String aMessage, File aImage ) throws IOException, PsnErrorException;
	
	
	/**
	 * 
	 * @param aMessage
	 * @return
	 * @throws IOException
	 * @throws PsnErrorException
	 */
	public SendMessageResponse addMessageToDiscussion( String aDiscussionId, String aMessage ) throws IOException, PsnErrorException;
	
	/**
	 * 
	 * @param aDiscussionId
	 * @param aMessage
	 * @param aImage
	 * @return
	 * @throws IOException
	 * @throws PsnErrorException
	 */
	public SendMessageResponse addMessageToDiscussion( String aDiscussionId, String aMessage, File aImage  ) throws IOException, PsnErrorException;
	
	
	/**
	 * 
	 * @param aMessageUid
	 * @param aDiscussionId
	 * @throws IOException
	 * @throws PsnErrorException
	 */
	public void markMessageAsSeen(List<Long> aMessageUid, String aDiscussionId )throws IOException, PsnErrorException;
	
	/**
	 * 
	 * @param aYourOnlineId
	 * @param aDiscussionParam
	 * @return
	 * @throws IOException
	 * @throws PsnErrorException
	 */
	public DiscussionList getDiscussionList( String aYourOnlineId, DiscussionParam aDiscussionParam, DiscussionPagination aDiscussionPagination ) throws IOException, PsnErrorException;
	
	
	/**
	 * 
	 * @param aDiscussionParam
	 * @return
	 */
	public Discussion getDiscussion( DiscussionParam aDiscussionParam, String aDiscussionId ) throws IOException, PsnErrorException;
	
	
	/**
	 * 
	 * @param aYourOnlineId
	 * @param aDiscussionId
	 * @return
	 * @throws IOException
	 * @throws PsnErrorException
	 */
	public void leaveFromDiscussion( String aYourOnlineId, String aDiscussionId) throws IOException, PsnErrorException;
	
	
	/**
	 * 
	 * @param aOnlineIdList
	 * @param aDiscussionId
	 * @retourn SimpleDiscussion
	 * @throws IOException
	 * @throws PsnErrorException
	 */
	public SendMessageResponse addMemberToDiscussion( List<String> aOnlineIdList, String aDiscussionId) throws IOException, PsnErrorException;
	
	
	
}
