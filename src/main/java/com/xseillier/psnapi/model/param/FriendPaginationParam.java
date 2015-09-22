package com.xseillier.psnapi.model.param;
/**
 *
 * @author xseillier
 * @version 1.0 20 sept. 2015
 */
public class FriendPaginationParam extends AbstractPaginationParam {

	public static int LIMIT = 36;
	
	
	public FriendPaginationParam( int aOffset ){
		super( aOffset, LIMIT );
	}

	public FriendPaginationParam( int aOffset, int aLinit ){
		super( aOffset, aLinit );
	}
}
