package com.xseillier.psnapi.model.block;

import java.util.ArrayList;
import java.util.List;

import com.xseillier.psnapi.model.ObjectPaginable;
import com.xseillier.psnapi.model.profile.BasicProfile;
/**
*
* @author xseillier
* @version 1.0 16 sept. 2015
*/
public class BlockList extends ObjectPaginable<BlockPagination>{
	
	
	
	private List<BasicProfile> mBlockList = new ArrayList<BasicProfile>();
	
//=============================================================================
// Accessor
//=============================================================================

    public List<BasicProfile> getBlockList() {
		return mBlockList;
	}   
	
	public void setBlockProfileList(List<BasicProfile> aBlockProfileList) {
		mBlockList = aBlockProfileList;
	}
	
}
