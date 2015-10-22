package com.xseillier.psnapi.model.friend;

import com.xseillier.psnapi.model.AbstractPagination;

/**
 *
 * @author xseillier
 * @version 1.0 20 sept. 2015
 */
public class FriendPagination extends AbstractPagination {

	public static int LIMIT = 36;
	
	
	public FriendPagination( int aOffset ){
		super( aOffset, LIMIT );
	}

	public FriendPagination( int aOffset, int aLinit ){
		super( aOffset, aLinit );
	}
}
