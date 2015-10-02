package com.xseillier.psnapi.model.param;
/**
 *
 * @author xseillier
 * @version 1.0 20 sept. 2015
 */
public class BlockPaginationParam extends AbstractPaginationParam {

	public static int LIMIT = 36;
	
	
	public BlockPaginationParam( int aOffset ){
		super( aOffset, LIMIT );
	}

	public BlockPaginationParam( int aOffset, int aLinit ){
		super( aOffset, aLinit );
	}
}
