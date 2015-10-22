package com.xseillier.psnapi.model.messaging;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author xseillier
 * @version 1.0 6 oct. 2015
 */
public class MemberList {
	
	private List<Member> mMembers;
    
	public MemberList() {
		mMembers = new ArrayList<>();
	}
	
	public List<Member> getMembers() {
		return mMembers;
	}
	
	public void addMember(String aOnlineId ) {
		addMember( new Member( aOnlineId) );
	}
	
	public void addMember(Member aMember) {
		mMembers.add( aMember );
	}
	
	public void setMembers(List<Member> aMembers) {
		mMembers = aMembers;
	}
}
