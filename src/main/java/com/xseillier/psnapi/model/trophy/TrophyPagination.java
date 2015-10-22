package com.xseillier.psnapi.model.trophy;

import com.xseillier.psnapi.model.AbstractPagination;


/**
 *
 * @author xseillier
 * @version 1.0 20 sept. 2015
 */
public class TrophyPagination extends AbstractPagination {

	public static int LIMIT = 64;
	
	
	public TrophyPagination( int aOffset ){
		super( aOffset, LIMIT );
	}
	
	public TrophyPagination( int aOffset, int aLinit ){
		super( aOffset, aLinit );
	}
}
