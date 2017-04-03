package com.xseillier.psnapi;

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
import com.xseillier.psnapi.model.enumeration.ImageSizeEnum;
import com.xseillier.psnapi.model.enumeration.PresenceEnum;
import com.xseillier.psnapi.model.friend.FriendList;
import com.xseillier.psnapi.model.friend.FriendPagination;
import com.xseillier.psnapi.model.friend.FriendProfile;
import com.xseillier.psnapi.model.messaging.*;
import com.xseillier.psnapi.model.param.*;
import com.xseillier.psnapi.model.param.enumeration.PlatformEnum;
import com.xseillier.psnapi.model.param.enumeration.TrophySummaryOptionEnum;
import com.xseillier.psnapi.model.trophy.TrophyPagination;
import com.xseillier.psnapi.model.trophy.TrophyTitle;
import com.xseillier.psnapi.model.trophy.TrophyTitleList;
import com.xseillier.psnapi.model.user.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author xseillier
 * @version 1.0 15 sept. 2015
 *
 */
public class Test {

    private static final String oPsnLogin    = "Your Login";
    private static final String oPsnPassword = "Your Password";
    private PsnApi oPSNApi;
    private User oUser;
	
	public static void main(String[] args) {
	    new Test();
	}

	private Test() {
        //create duid id
        MacAddressGenerator oMacAddressGenerator  = new  MacAddressGenerator();
        AndroidDevice oAndroidDevice = new AndroidDeviceGenerator().getRandomAndroidDevice();
        AndroidDuidGenerator oDuidGen = new AndroidDuidGenerator();
        String oDuid =oDuidGen.generateDuid( oAndroidDevice, oMacAddressGenerator.generateMacAddressWithRealPrefix() );

        //create PsnContext
        PsnContext oPsnContext = new PsnContext( oDuid );

        //instantiation of Psn Api
        //  PsnApiImpl.NO_RATE_LIMIT_CONTROL for simple test else used PsnApiImpl.DEFAULT_RATE_LIMIT
        oPSNApi = new PsnApiImpl( oPsnContext, PsnApiImpl.NO_RATE_LIMIT_CONTROL );

        try {
            //login on PlayStation Network
            oPSNApi.login(oPsnLogin, oPsnPassword);

            //get account information
            oUser = oPSNApi.getAccountInformation();
            System.out.println("Your online id is : " + oUser.getOnlineId() );

            messageFriendsTest();
            trophyListTest();
        }
        catch (LoginException e) {
            System.out.println("Login Error " + e.getMessage() );
        }
        catch (PsnErrorException e) {
            System.out.println( e.getPsnError().getCode() + " - " + e.getPsnError().getMessage() );
            e.printStackTrace();
        }
        catch (IOException e) {
            System.out.println("Unknown Error " + e.getMessage() );
        }
    }

	private void messageFriendsTest() {
        try {
            ProfileParam oProfileParam = ProfileParamFactory.create( ProfileParamFactory.SIMPLE_PROFILE );

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
            DiscussionList oDiscussionList;
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
                            oPSNApi.addMessageToDiscussion(oMessageGroup.getMessageGroupId(), "Hello from PSN API (https://github.com/xseillier)");
                        }
                    }
                    System.out.println();

                    //send message to discussion with image
                    //oPSNApi.addMessageToDiscussion(oMessageGroup.getMessageGroupId(), "Hello from PSN API (https://github.com/xseillier)", new File("<IMAGE PATH>")  );
                    oPSNApi.addMessageToDiscussion(oMessageGroup.getMessageGroupId(), "Hello from PSN API (https://github.com/xseillier)");

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
            System.out.println( e.getPsnError().toString() );
            e.printStackTrace();
        }
    }

    private void trophyListTest() {
		try {
			List<TrophySummaryOptionEnum> trophySummaryOptionEnumList = new ArrayList<>();
			trophySummaryOptionEnumList.add(TrophySummaryOptionEnum.DEFAULT);

			List<ImageSizeEnum> imageSizeEnumList = new ArrayList<>();
			imageSizeEnumList.add(ImageSizeEnum.MEDIUM);

			List<PlatformEnum> platformEnumList = new ArrayList<>();
			platformEnumList.add(PlatformEnum.PS3);
			platformEnumList.add(PlatformEnum.PS4);
			platformEnumList.add(PlatformEnum.PSVITA);

			TrophyParam trophyParam = new TrophyParam(
					trophySummaryOptionEnumList,
					imageSizeEnumList,
					platformEnumList,
                    Locale.getDefault()
			);
			TrophyTitleList trophyList = oPSNApi.getTrophyList(trophyParam, new TrophyPagination(0));

            System.out.println( "===============================================================================" );
			for (TrophyTitle title : trophyList.getTrophyTitles()) {
			    System.out.println("Trophy: " + title.getTrophyTitleName());
                System.out.println("\tDetail: " + title.getTrophyTitleDetail());
                System.out.println("\tImage: " + title.getTrophyTitleIconUrl());
            }

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
			e.printStackTrace();
		}
	}
}