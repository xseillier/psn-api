package com.xseillier.psnapi.utils;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.xseillier.psnapi.enumeration.DataEnum;
import com.xseillier.psnapi.enumeration.ProfileV2DataEnum;
import com.xseillier.psnapi.model.AccessToken;
import com.xseillier.psnapi.model.param.ProfileV2Param;

/**
 *
 * @author xseillier
 * @version 1.0 16 sept. 2015
 */
public class UrlUtils {

	
	public static  String createAuthHeader( AccessToken aAccessToken ) {
		return  aAccessToken.getTokenType() + " " +aAccessToken.getAccessToken();
	}
	
	
	public static String injectDataInUrl( String aUrl, Map<String,String> aUrlData ) {
		
		String oUrl = aUrl;
		for( String oKey: aUrlData.keySet() ) {
			oUrl= injectDataInUrl( oUrl, oKey, aUrlData.get( oKey ) );
		}
		return oUrl;
	}
	
	public static String injectDataInUrl( String aUrl, String aName, String aValue) {
		return aUrl.replaceFirst("\\{"+ aName + "\\}", aValue );
	}


	/**
	 * return string of  DataEnum<String> value join by default separator ","
	 * @param alist
	 * @return
	 */
	
	public static String  joinDataEnum( List< ? extends DataEnum<String> > alist ) {
		return joinDataEnum( alist, "," );
	}


	/**
	 * return string of  DataEnum<String> value join by separator aSeparator
	 * @param alist
	 * @param aSeparator
	 * @return
	 */
	public static String  joinDataEnum( List< ? extends DataEnum<String> > alist, String aSeparator  ) {
		StringBuffer oResponse = new StringBuffer();
			
		Iterator< ? extends DataEnum<String> > oIt =  alist.iterator();
		while( oIt.hasNext() ) {
			
			DataEnum< String > oData = oIt.next();
			oResponse.append( oData.getData() );
			
			if( oIt.hasNext() ) {
				oResponse.append( aSeparator );
			}
		}
		
		return oResponse.toString();
	}


	public static String joinList( List<?> aList  ) {
			StringBuffer oResponse = new StringBuffer();
			
			if( aList != null && aList.size() > 0 ) {
				for( int i = 0; i < aList.size(); i++ ){
					oResponse.append( aList.get( i ).toString() );
					if( i != aList.size() -1 ) {
						oResponse.append( "," );
					}
				}
			}
			return oResponse.toString();
	}
	
	/**
	 * 
	 * @param aProfileV2Param
	 * @return
	 */
	public static String createProfileV2field( ProfileV2Param aProfileV2Param ) {
		StringBuffer oResponse = new StringBuffer();
		
		oResponse.append( joinDataEnum( aProfileV2Param.getProfileParams()) );
		
		if( aProfileV2Param.getPresencesParams().size() > 0 )
		{
			oResponse.append(",");
			oResponse.append( joinProfileV2DataEnum( aProfileV2Param.getPresencesParams() ) );
		}
		
		if( aProfileV2Param.getTrophySummaryParams().size() > 0 )
		{
			oResponse.append(",");
			oResponse.append( joinProfileV2DataEnum( aProfileV2Param.getTrophySummaryParams() ) );
		}
		
		if( aProfileV2Param.getPersonalDetailParams().size() > 0 )
		{
			oResponse.append(",");
			oResponse.append( joinProfileV2DataEnum( aProfileV2Param.getPersonalDetailParams() ) );
		}
		
		return oResponse.toString();
	}
	
	/**
	 * 
	 * @param alist
	 * @return
	 */
	private static String joinProfileV2DataEnum(  List< ? extends ProfileV2DataEnum<String> > alist ) {
		if( alist == null || alist.size() == 0 )
			return null;
		
		StringBuffer oResponse = new StringBuffer();
		oResponse.append( alist.get(0).getParamName() );
		oResponse.append( "(");	
		oResponse.append( joinDataEnum( alist, "," ) );
		oResponse.append( ")");
		return oResponse.toString();		
	}
}