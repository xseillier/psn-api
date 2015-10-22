package com.xseillier.psnapi.model.param;

import java.util.Locale;

import com.xseillier.psnapi.model.param.enumeration.DiscussionOptionEnum;

/**
 *
 * @author xseillier
 * @version 1.0 1 oct. 2015
 */
public class DiscussionParamFactory {

	public static final int SIMPLE              = 1;
	public static final int GET_DISCUSSION_LIST = 2;
	public static final int GET_DISCUSSION      = 3;
	
	public static DiscussionParam create( int aType ) {
		
		switch( aType ) {
			case SIMPLE             : return createSimple();
			case GET_DISCUSSION_LIST: return createGetDiscussionList();	
			case GET_DISCUSSION     : return createGetDiscussion();	
			default:
				throw new IllegalArgumentException("Type unknown");
		}
	}
	
	
	/**
	 * create simple discussion param
	 * @return
	 */
	private static DiscussionParam createSimple() {
		return new DiscussionParam.DiscussionParamBuilder()
		.addDiscussionOption(DiscussionOptionEnum.DEFAULT)
		.setLocale(Locale.getDefault()).build();
	}
	
	
	/**
	 * create  get discussion list  param
	 * @return
	 */
	private static DiscussionParam createGetDiscussionList() {
		
		return new DiscussionParam.DiscussionParamBuilder()
		.addDiscussionOption(DiscussionOptionEnum.DEFAULT)
		.addDiscussionOption(DiscussionOptionEnum.MESSAGE_GROUP_ID)
		.addDiscussionOption(DiscussionOptionEnum.MESSAGE_GROUP_DETAIL)
		.addDiscussionOption(DiscussionOptionEnum.TOTAL_UNSEEN_MESSAGES)
		.addDiscussionOption(DiscussionOptionEnum.TOTAL_MESSAGES)
		.addDiscussionOption(DiscussionOptionEnum.LATEST_MESSAGE)
		.addDiscussionOption(DiscussionOptionEnum.LAST_CHECK_DATE)	
		.setLocale(Locale.getDefault()).build();
		
	}
	
	
	/**
	 *  create  get discussion param
	 * @return
	 */
	private static DiscussionParam createGetDiscussion() {
		
		return new DiscussionParam.DiscussionParamBuilder()
		.addDiscussionOption(DiscussionOptionEnum.DEFAULT)
		.addDiscussionOption(DiscussionOptionEnum.MESSAGEGROUP)
		.addDiscussionOption(DiscussionOptionEnum.BODY)
		.setLocale(Locale.getDefault()).build();	
	}
}
