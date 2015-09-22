package com.xseillier.psnapi.utils;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.xseillier.psnapi.enumeration.DataEnum;
import com.xseillier.psnapi.model.AccessToken;

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
	
}