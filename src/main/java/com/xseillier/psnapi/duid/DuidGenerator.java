package com.xseillier.psnapi.duid;

import java.io.UnsupportedEncodingException;

import com.xseillier.psnapi.duid.model.AndroidDevice;



/**
 * Reverse engineering
 * @author xseillier
 *
 */
public abstract class DuidGenerator {

	
	

	
//=============================================================================
// constructor
//=============================================================================
	

	
//=============================================================================
// Method
//=============================================================================

	public abstract int getIndexOne();
	public abstract int getIndexTwo();
	
	
	
	public String generateDuid( AndroidDevice aAndroidDevice, String aMacAddress )
	{
		
		byte[] arrayOfByte1 = encode(0, 2);
	    byte[] arrayOfByte2 = encode(getIndexOne(), 2);
	    byte[] arrayOfByte3 = encode(getIndexTwo(), 2);
	    byte[] arrayOfByte4 = generateDevicePartId( aAndroidDevice, aMacAddress );
	    byte[] arrayOfByte5 = encode(arrayOfByte4.length << 3, 2);	
	    byte[] arrayOfByte6 = new byte[arrayOfByte1.length + arrayOfByte2.length + arrayOfByte3.length + arrayOfByte5.length + arrayOfByte4.length];
	    
	    int i = 0 + copyArray(arrayOfByte6, arrayOfByte1, 0, arrayOfByte1.length);
	    int j = i + copyArray(arrayOfByte6, arrayOfByte2, i, arrayOfByte2.length);
	    int k = j + copyArray(arrayOfByte6, arrayOfByte3, j, arrayOfByte3.length);
	    
	    copyArray(arrayOfByte6, arrayOfByte4, k + copyArray(arrayOfByte6, arrayOfByte5, k, arrayOfByte5.length), arrayOfByte4.length);
	   
		return byteArrayToHex( arrayOfByte6 );
	}
	
	protected String fixeStringLengthSize( String paramString, char paramChar, int paramInt, boolean aboolean )
	{
		if( paramString.length() > paramInt )
		{
			if( aboolean )
			{
				paramString = paramString.substring(0, paramInt);
			}
		}
		
	    StringBuffer localStringBuffer = new StringBuffer(paramString);
	    for (int i = paramString.length(); i < paramInt ; i++)
	    {      
	      localStringBuffer.append( paramChar );
	    }
	    
	    return localStringBuffer.toString();
	}
	
	 protected String createPayload(String paramString1, String paramString2, int paramInt)
	  {
	    if ((paramString2 == null) || (paramString2.length() == 0));
	    for (String str = paramString1; ; str = paramString2)
	    {
	      if (str != null)
	        str = fixeStringLengthSize(str, 'X', paramInt, false);
	      return str;
	    }
	  }
		 
	 // oManufacturer, oDevice, oDeviceID,
	 public abstract byte[] generateDevicePartId(AndroidDevice aAndroidDevice, String aMacAddress );
	 
	
//=============================================================================
// Tools
//=============================================================================
	

	public static byte[] hexStringToByteArray(String s) {
	    int len = s.length();
	    byte[] data = new byte[len / 2];
	    for (int i = 0; i < len; i += 2) {
	        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
	                             + Character.digit(s.charAt(i+1), 16));
	    }
	    return data;
	}
	
	
	
	private byte[] encode( int aIndex, int aLength )
	{
		byte[] oResponse = new byte[ aLength ];
		
		for( int i = 0 ; i <= aLength ; i++ )
		{
			oResponse[ i ] = (byte) ( aIndex >> 8 * ( aLength - 1));
			aLength--;
		}
		return oResponse;
	}
	
	
	public void displayByte( byte[] aArray )
	{
		for( int i = 0; i < aArray.length; i++ )
		{
			System.out.printf("0x%02X ", aArray[ i ]);
		}
	}
	
	public void displayByteSmall( byte[] aArray )
	{
		for( int i = 0; i < aArray.length; i++ )
		{
			System.out.printf("%02X", aArray[ i ]);
		}
	}
	
	private String byteArrayToHex(byte[] aByteArray )
	{
		   StringBuilder sb = new StringBuilder();
		   for(byte oByte: aByteArray)
		      sb.append(String.format("%02x", oByte & 0xff ) );
		   return sb.toString();
	}
	

	 public byte[] stringToByte( String aString )
	 {
		 try {
			return aString.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	 }
	 
	 
	 protected  int copyArray(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int paramInt1, int paramInt2)
	  {
	    System.arraycopy(paramArrayOfByte2, 0, paramArrayOfByte1, paramInt1, paramInt2);
	    return paramInt2;
	  }
	
}
