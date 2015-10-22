package com.xseillier.psnapi.model.trophy;

import java.util.List;

import com.xseillier.psnapi.model.ObjectPaginable;

/**
*
* @author xseillier
* @version 1.0 16 sept. 2015
*/
public class TrophyTitleList extends ObjectPaginable<TrophyPagination> {


    private List<TrophyTitle> mTrophyTitles;    
    
//=============================================================================
// Accessor
//=============================================================================
   
	
	public List<TrophyTitle> getTrophyTitles() {
		return mTrophyTitles;
	}
    
}
