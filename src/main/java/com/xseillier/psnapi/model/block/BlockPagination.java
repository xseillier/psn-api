package com.xseillier.psnapi.model.block;

import com.xseillier.psnapi.model.AbstractPagination;

/**
 *
 * @author xseillier
 * @version 1.0 20 sept. 2015
 */
public class BlockPagination extends AbstractPagination {

	public static int LIMIT = 36;
	
	
	public BlockPagination( int aOffset ){
		super( aOffset, LIMIT );
	}

	public BlockPagination( int aOffset, int aLinit ){
		super( aOffset, aLinit );
	}
}
