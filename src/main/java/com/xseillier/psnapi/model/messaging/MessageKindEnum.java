package com.xseillier.psnapi.model.messaging;

import com.google.gson.annotations.SerializedName;
import com.xseillier.psnapi.enumeration.DataEnum;

/**
 *
 * @author xseillier
 * @version 1.0 2 oct. 2015
 */
public enum MessageKindEnum implements DataEnum<Integer> {

	@SerializedName("1")
	TYPE_STRING(1),
	@SerializedName("3")
	TYPE_IMAGE(3),
	@SerializedName("2001")
	ADD_SOMEBODY_IN_TALK(2001),
	@SerializedName("2002")
	REMOVE_SOMEBODY_IN_TALK(2002);
	

	private Integer mDate;
	
	private MessageKindEnum ( Integer aData ) {
		mDate = aData;
	}
	
	public Integer getData(){
		return mDate;
	}
}
