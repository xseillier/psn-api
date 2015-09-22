package com.xseillier.psnapi.utils;

/**
*
* @author xseillier
* @version 1.0 15 sept. 2015
*/
public class StringUtils {

	/**
	 * Extract String in aText between word aStart to word aEnd
	 * @param aText
	 * @param aStart
	 * @param aEnd
	 * @return
	 */
	public static String getTextBetween(String aText, String aStart, String aEnd)
	{
		int index1 = aText.indexOf(aStart);
		if( index1 > -1 )
		{
			int index2 = aText.indexOf(aEnd, index1  + aStart.length()  );
			if (index2 > index1 )
			{
				return aText.substring( index1+ aStart.length(), index2 ).trim();
			}
		}
		return null;
	}
	
	/**
	 * Extract String in aText from beginning to word aEnd
	 * @param aText
	 * @param aEnd
	 * @return
	 */
	public static String getTextBetween(String aText, String aEnd)
	{
		int index1 = aText.indexOf( aEnd );
		if( index1 > -1 )
		{
				return aText.substring( 0, index1 ).trim();			
		}
		return null;
	}
	

}
