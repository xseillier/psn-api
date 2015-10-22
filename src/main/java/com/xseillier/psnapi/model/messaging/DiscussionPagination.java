package com.xseillier.psnapi.model.messaging;

import com.xseillier.psnapi.model.AbstractPagination;

/**
 *
 * @author xseillier
 * @version 1.0 20 sept. 2015
 */
public class DiscussionPagination extends AbstractPagination {

	public static int LIMIT = 36;
	
	
	public DiscussionPagination( int aOffset ){
		super( aOffset, LIMIT );
	}

	public DiscussionPagination( int aOffset, int aLinit ){
		super( aOffset, aLinit );
	}
}
