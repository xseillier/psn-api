package com.xseillier.psnapi.model.param;

import java.util.Locale;

import com.xseillier.psnapi.model.enumeration.ImageSizeEnum;
import com.xseillier.psnapi.model.param.enumeration.PlatformEnum;
import com.xseillier.psnapi.model.param.enumeration.TrophySummaryOptionEnum;

/**
 *
 * @author xseillier
 * @version 1.0 07 oct. 2015
 */
public class TrophyParamFactory {

	public static final int SIMPLE = 1;
	
	public static TrophyParam create( int aType ) {
		
		switch( aType ) {
			case SIMPLE             : return createSimple();
			default:
				throw new IllegalArgumentException("Type unknown");
		}
	}
	
	
	/**
	 * create simple discussion param
	 * @return
	 */
	private static TrophyParam createSimple() {
		return new TrophyParam.TrophyParamBuilder()
		.addImageSize(ImageSizeEnum.LARGE)
		.addPlatfrom(PlatformEnum.PS3)
		.addPlatfrom(PlatformEnum.PS4)
		.addTrophySummaryOption(TrophySummaryOptionEnum.TROPHY_EARNED_RATE)
		.addTrophySummaryOption(TrophySummaryOptionEnum.DEFAULT)
		.addTrophySummaryOption(TrophySummaryOptionEnum.TROPHY_RARE)
		.setLocale(Locale.getDefault()).build();
	}
	
}
