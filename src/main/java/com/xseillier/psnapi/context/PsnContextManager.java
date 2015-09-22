package com.xseillier.psnapi.context;

import com.xseillier.psnapi.model.PsnContext;

/**
 *
 * @author xseillier
 * @version 1.0 16 sept. 2015
 */
public interface PsnContextManager {

	
	public PsnContext load( String aName );
	public void save( String aName, PsnContext aPsnContext );
	public void delete( String aName );
	
}
