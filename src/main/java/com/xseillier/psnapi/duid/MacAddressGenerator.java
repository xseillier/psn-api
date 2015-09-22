package com.xseillier.psnapi.duid;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;

public class MacAddressGenerator {

	
//=============================================================================
// Attributes
//=============================================================================	
	
	final private static String PREFIX_FILE = "com/xseillier/psnapi/duid/prefix_mac.txt";
	final private static int    MAC_ADDRESS_NB_BYTE = 6;
	
	private char[] mHexValue = new char[]{'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
	private Random mRandom = new Random();	
	private Properties mPrefixList;
	
	
//=============================================================================
// Constructor
//=============================================================================	
	
	public MacAddressGenerator()
	{
		try
		{
			initPrefixList();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
//=============================================================================
// Methods
//=============================================================================

	
	private void initPrefixList() throws IOException
	{
		mPrefixList = new Properties();
		mPrefixList.load(MacAddressGenerator.class.getClassLoader().getResourceAsStream( PREFIX_FILE ) );
	}
	
	
	/**
	 * return a random int
	 * @return
	 */
	private int getRandomInt()
	{
		return Math.abs( mRandom.nextInt() );
	}
	
	
	/**
	 * generate random char between 0 to F 
	 * @return
	 */
	private char getRandomHexValue()
	{		
		return mHexValue[ getRandomInt() % ( mHexValue.length - 1 )];
	}
	
	
	
	public String getRandomPrefix()
	{
		int oRandomeInt = getRandomInt();		
		return (String) mPrefixList.keySet().toArray()[ oRandomeInt % mPrefixList.size() ];
	}
	
	
	/**
	 *  generate a random mac address
	 * @return
	 */
	public String generateMacAddress()
	{
		return generateMacAddress('\n');
	}
	
	public String generateMacAddress( char aSeparator )
	{
		return generateMacAddress("", aSeparator );
	}
	
	public String generateMacAddressWithRealPrefix( char aSeparator )
	{
		return generateMacAddress( aSeparator );
	}
	
	public String generateMacAddressWithRealPrefix()
	{
		return generateMacAddress( getRandomPrefix(),'\n' );
	}
	
	
	private String generateMacAddress( String aPrefix, char aSeparator )
	{
		StringBuilder oResponse = new StringBuilder();
		oResponse.append( aPrefix );
		for( int i = 0; i < ( MAC_ADDRESS_NB_BYTE * 2 - aPrefix.length() ); i++ )
		{
			oResponse.append( getRandomHexValue() );
		}
		
		if( aSeparator != '\n' )
		{
			return insertPeriodically( oResponse.toString(), ":", 2 );
		}
		return oResponse.toString();
	}
	
	
	
	/**
	 * http://stackoverflow.com/questions/537174/putting-char-into-a-java-string-for-each-n-characters
	 * @param text
	 * @param insert
	 * @param period
	 * @return
	 */
	public static String insertPeriodically(
		    String text, String insert, int period)
		{
		    StringBuilder builder = new StringBuilder(
		         text.length() + insert.length() * (text.length()/period)+1);

		    int index = 0;
		    String prefix = "";
		    while (index < text.length())
		    {
		        // Don't put the insert in the very first iteration.
		        // This is easier than appending it *after* each substring
		        builder.append(prefix);
		        prefix = insert;
		        builder.append(text.substring(index, 
		            Math.min(index + period, text.length())));
		        index += period;
		    }
		    return builder.toString();
		}
	
}
