package com.xseillier.psnapi.http.exception;

import com.xseillier.psnapi.model.PsnError;

/**
 *
 * @author xseillier
 * @version 1.0 18 sept. 2015
 */
public class PsnExceptionFactory {

	
	public static PsnErrorException createException( PsnError aPsnError ) {
		
		if( AccessDeniedByPrivacyLevelException.CODE == aPsnError.getCode() ) {
			return new AccessDeniedByPrivacyLevelException( aPsnError );
		}
		else if (RateLimitExceededException.CODE == aPsnError.getCode() ) {
			return new RateLimitExceededException( aPsnError );
		}
		else if (UserNotFoundException.CODE == aPsnError.getCode() ) {
			return new UserNotFoundException( aPsnError );
		}
		else if (AleadyFriendRequestedException.CODE == aPsnError.getCode() ) {
			return new AleadyFriendRequestedException( aPsnError );
		}
		else if (AccessDeniedByResourceOwnershipException.CODE == aPsnError.getCode() ) {
			return new AccessDeniedByResourceOwnershipException( aPsnError );
		}
		else if (BadRequestException.CODE == aPsnError.getCode() ) {
			return new BadRequestException( aPsnError );
		}
		return new PsnErrorException( aPsnError );
	}
}
