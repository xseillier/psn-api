### PSN API
This API allows you to query the servers Sony Playstation Network to retrieve data on their PSN account, these trophies, those friends.
This API is based on a reverse engineer work on the Android application.

### Requirements
* Valid PSN account

### Features
* User account data
* Friend list
* trophy list
* player profile(depends on users privacy)
* add/del friend
* block/unblock profile
### Limitation
The api is limited at 100 requests on 15 minutes. This limitation is imposed by the PSN servers
### Duid
Duid is an unique id use by Sony to distinguish the devices.  He is based on device manufacturer name and mac address.
You can find classes in package package com.xseillier.psnapi.duid, all you need to create an duid id fake

### Change log

*0.2 beta
	* add/del friend
	* block / unblock profile
	
	
*0.1 beta initial version

### Version
0.2 Beta
### Next
* add more doc
* add/delete friend
* retrieved/send message 


### Sample
```java
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
		
			
			ProfileParam oProfileParam = new ProfileParam.ProfileParamBuilder().addProfileOption(ProfileOptionEnum.ONLINE_ID).build();
						
			FriendList oFriendListResponse;
			List<Profile> mFriendList = new ArrayList<>();
			
			//retriveid friend list from offset 0
			FriendPaginationParam oNext = new FriendPaginationParam( 0 );
			
			while( oNext != null ) {		
					
				oFriendListResponse = oPSNApi.getFriendList( oUser.getOnlineId(), oProfileParam, oNext );
				oNext = oFriendListResponse.getNextPagination();
				mFriendList.addAll( oFriendListResponse.getFriendList() );
			}
			
			System.out.println("List of your Friends : " );
			for( Profile oProfile: mFriendList ) {
				System.out.print( oProfile.getOnlineId()  );
				if( oProfile.getPresence().getPrimaryInfo().getOnlineStatus() == PresenceEnum.ONLINE ) {
					System.out.print(" is online on " + oProfile.getPresence().getPrimaryInfo().getPlatform().getData() );
					if( oProfile.getPresence().getPrimaryInfo().getGameTitleInfo() != null ) {
						System.out.print(" and play to " + oProfile.getPresence().getPrimaryInfo().getGameTitleInfo().getTitleName()  + "\n\tgame status : " + oProfile.getPresence().getPrimaryInfo().getGameStatus() );
					}
					System.out.println(".");
				}
				else {
					System.out.println(" is offline.");
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
```