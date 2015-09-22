package com.xseillier.psnapi.duid;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import com.xseillier.psnapi.duid.model.AndroidDevice;
import com.xseillier.psnapi.duid.model.impl.FakeAndroidDevice;

public class AndroidDeviceGenerator extends Properties {

	
//=============================================================================
// Attributes
//=============================================================================	
	
	final private static String PREFIX_FILE = "com/xseillier/psnapi/duid/android_device.txt";
	
	private Random mRandom = new Random();	
	
	
//=============================================================================
// Constructor
//=============================================================================	
	
	public AndroidDeviceGenerator()
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
		load(AndroidDeviceGenerator.class.getClassLoader().getResourceAsStream( PREFIX_FILE ) );
	}
	
	
	/**
	 * return a random int
	 * @return
	 */
	private int getRandomInt()
	{
		return Math.abs( mRandom.nextInt() );
	}

	
	public AndroidDevice getRandomAndroidDevice()
	{
		int oRandomeInt = getRandomInt();		
		String oKey = (String) keySet().toArray()[ oRandomeInt % size() ];
		
		return  (AndroidDevice) get( oKey );
	}
	
	
	@Override
	public synchronized Object put(Object aKey, Object aCodeName) {
		
		return super.put(aKey, new FakeAndroidDevice((String) aKey, (String) aCodeName ) );
	}
	

	
}
