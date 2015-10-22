package com.xseillier.psnapi;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.xseillier.psnapi.duid.AndroidDeviceGenerator;
import com.xseillier.psnapi.duid.AndroidDuidGenerator;
import com.xseillier.psnapi.duid.MacAddressGenerator;
import com.xseillier.psnapi.duid.model.AndroidDevice;
import com.xseillier.psnapi.http.exception.AccessDeniedByPrivacyLevelException;
import com.xseillier.psnapi.http.exception.LoginException;
import com.xseillier.psnapi.http.exception.PsnErrorException;
import com.xseillier.psnapi.http.exception.RateLimitExceededException;
import com.xseillier.psnapi.impl.PsnApiImpl;
import com.xseillier.psnapi.model.PsnContext;
import com.xseillier.psnapi.model.enumeration.PresenceEnum;
import com.xseillier.psnapi.model.friend.FriendList;
import com.xseillier.psnapi.model.friend.FriendPagination;
import com.xseillier.psnapi.model.friend.FriendProfile;
import com.xseillier.psnapi.model.messaging.Discussion;
import com.xseillier.psnapi.model.messaging.DiscussionList;
import com.xseillier.psnapi.model.messaging.DiscussionPagination;
import com.xseillier.psnapi.model.messaging.Member;
import com.xseillier.psnapi.model.messaging.Message;
import com.xseillier.psnapi.model.messaging.MessageGroup;
import com.xseillier.psnapi.model.param.DiscussionParam;
import com.xseillier.psnapi.model.param.DiscussionParamFactory;
import com.xseillier.psnapi.model.param.ProfileParam;
import com.xseillier.psnapi.model.param.ProfileParamFactory;
import com.xseillier.psnapi.model.param.enumeration.ProfileOptionEnum;
import com.xseillier.psnapi.model.user.User;

/**
 *
 * @author xseillier
 * @version 1.0 15 sept. 2015
 */
public class Test {

	
	
	
	public static void main(String[] args) {
		
		String oPsnLogin    = "your login";
		String oPsnPassword = "your password";
		
		//create duid id
		MacAddressGenerator oMacAddressGenerator  = new  MacAddressGenerator();
    	AndroidDevice oAndroidDevice = new AndroidDeviceGenerator().getRandomAndroidDevice();   
    	AndroidDuidGenerator oDuidGen = new AndroidDuidGenerator();   
        String oDuid =oDuidGen.generateDuid( oAndroidDevice, oMacAddressGenerator.generateMacAddressWithRealPrefix() );
              
        //create PsnContext
		PsnContext oPsnContext = new PsnContext( oDuid );
		
		//instantiation of Psn Api
		//  PsnApiImpl.NO_RATE_LIMIT_CONTROL for simple test else used PsnApiImpl.DEFAULT_RATE_LIMIT
		PsnApi oPSNApi = new PsnApiImpl( oPsnContext, PsnApiImpl.NO_RATE_LIMIT_CONTROL );
			
		
		try {
			
			
			//login on PlayStation Network
			oPSNApi.login(oPsnLogin, oPsnPassword);			
			
		    //get account information
			User oUser = oPSNApi.getAccountInformation();		
			System.out.println("Your online id is : " + oUser.getOnlineId() );
		
					
			ProfileParam oProfileParam = ProfileParamFactory.create( ProfileParamFactory.FULL_PROFILE );
			
			FriendList oFriendListResponse;
			List<FriendProfile> oFriendList = new ArrayList<>();
			
			//retriveid friend list from offset 0
			FriendPagination oNext = new FriendPagination( 0 );
			
			while( oNext != null ) {		
					
				oFriendListResponse = oPSNApi.getFriendList( oUser.getOnlineId(), oProfileParam, oNext );
				oNext = oFriendListResponse.getNextPagination();
				oFriendList.addAll( oFriendListResponse.getFriendList() );
			}
			
			System.out.println("List of your Friends : " );
			for( FriendProfile oProfile: oFriendList ) {
				System.out.print( oProfile.getOnlineId()  );
				if( oProfile.getPresence() != null ) {
					if( oProfile.getPresence().getPrimaryInfo().getOnlineStatus() == PresenceEnum.ONLINE ) {
						System.out.print(" is online on " + oProfile.getPresence().getPrimaryInfo().getPlatform().getData() );
						if( oProfile.getPresence().getPrimaryInfo().getGameTitleInfo() != null ) {
							System.out.print(" and play to " + oProfile.getPresence().getPrimaryInfo().getGameTitleInfo().getTitleName()  + "\n\tgame status : " + oProfile.getPresence().getPrimaryInfo().getGameStatus() );
						}
						System.out.println(".");
					} else if( oProfile.getPresence().getPrimaryInfo().getOnlineStatus() == PresenceEnum.STANDBY ) {
						System.out.println(" is standby." );
					}
					else {
						System.out.println(" is offline.");
					}
				}
			}
			
			DiscussionPagination oDiscussionPagination = new DiscussionPagination( 0 );
			DiscussionList oDiscussionList = oPSNApi.getDiscussionList(oUser.getOnlineId(), DiscussionParamFactory.create( DiscussionParamFactory.GET_DISCUSSION_LIST ), oDiscussionPagination );	
			DiscussionParam oDiscussionParam = DiscussionParamFactory.create( DiscussionParamFactory.GET_DISCUSSION );
			
			
			while( oDiscussionPagination != null ) {
				
				oDiscussionList = oPSNApi.getDiscussionList(oUser.getOnlineId(), DiscussionParamFactory.create( DiscussionParamFactory.GET_DISCUSSION_LIST ), oDiscussionPagination );		
				oDiscussionPagination = oDiscussionList.getNextPagination();
				
				for( MessageGroup oMessageGroup:  oDiscussionList.getMessageGroups() ) {
					System.out.println( "===============================================================================" );
					List<Long> oMessageUidList = new ArrayList<>();
					
					System.out.print( "Member in discussion : ");
					for( Member oMember : oMessageGroup.getMessageGroupDetail().getMembers() ){
						System.out.print(  oMember.getOnlineId() + " " );
						if( oMember.getOnlineId().equals("conan_hk" ) ){
							oPSNApi.addMessageToDiscussion(oMessageGroup.getMessageGroupId(), "Hello from PSN API (https://github.com/xseillier)", new File("c:/temp/github.jpg") );
						}
					}
					System.out.println();
					
					
					//send message to discussion
					oPSNApi.addMessageToDiscussion(oMessageGroup.getMessageGroupId(), "Hello from PSN API (https://github.com/xseillier)" );
					
					//send message to discussion with image
					//oPSNApi.addMessageToDiscussion(oMessageGroup.getMessageGroupId(), "Hello from PSN API (https://github.com/xseillier)", new File("<IMAGE PATH>")  );
					
					Discussion oDiscussion = oPSNApi.getDiscussion( oDiscussionParam, oMessageGroup.getMessageGroupId() );
								
					for(Message oMessage : oDiscussion.getMessages() ) {
						System.out.println( "\tMessage from " + oMessage.getSenderOnlineId() );
						System.out.println( "\t\tDate : " + oMessage.getReceivedDate() );
						System.out.println( "\t\tRead : " + ( ( oMessage.isSeenFlag() )?"Yes":"No" ) );
						System.out.println( "\t\tUid  : " + oMessage.getMessageUid() );
						System.out.println( "\t\tBody : " + oMessage.getBody() );	
						if( !oMessage.isSeenFlag() ) {
							oMessageUidList.add( oMessage.getMessageUid() );
						}
					}
					
					if( oMessageUidList.size() > 0 ) {
						//mark message(s) as read
						oPSNApi.markMessageAsSeen(oMessageUidList, oMessageGroup.getMessageGroupId() );
					}
				}
		}
		}
		catch (LoginException e) {
			System.out.println("Login Error " + e.getMessage() );
		}
		
		catch (IOException e) {
			System.out.println("Unknown Error " + e.getMessage() );
		}
		
		catch(AccessDeniedByPrivacyLevelException adbple ) {
			System.out.println( "Access denied by privacy level exception" );
		}
		
		catch( RateLimitExceededException rle ){
			System.out.println("Rate Limit error"  );		
		}
		
		catch (PsnErrorException e) {
			System.out.println( e.getPsnError().getCode() + " - " + e.getPsnError().getMessage() );
			e.printStackTrace();
		}
		
	}


}
