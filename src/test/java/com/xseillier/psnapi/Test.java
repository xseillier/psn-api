package com.xseillier.psnapi;

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
import com.xseillier.psnapi.model.friend.FriendProfile;
import com.xseillier.psnapi.model.param.FriendPaginationParam;
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
			FriendPaginationParam oNext = new FriendPaginationParam( 0 );
			
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
