package com.xseillier.psnapi.model.param;
/**
 *
 * @author xseillier
 * @version 1.0 20 sept. 2015
 */
public class TrophyPaginationParam extends AbstractPaginationParam {

	public static int LIMIT = 64;
	
	
	public TrophyPaginationParam( int aOffset ){
		super( aOffset, LIMIT );
	}
	
	public TrophyPaginationParam( int aOffset, int aLinit ){
		super( aOffset, aLinit );
	}
}
