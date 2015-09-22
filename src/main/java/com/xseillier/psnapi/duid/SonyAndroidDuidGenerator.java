package com.xseillier.psnapi.duid;

import com.xseillier.psnapi.duid.model.AndroidDevice;

public class SonyAndroidDuidGenerator extends DuidGenerator {

	@Override
	public int getIndexOne() {
		return 5;
	}

	@Override
	public int getIndexTwo() {
		return 100;
	}

	
	 
	 // oManufacturer, oDevice, oDeviceID,
	 public  byte[] generateDevicePartId(AndroidDevice aAndroidDevice, String aMacAddress )
	 {
		    String str = createPayload(aAndroidDevice.getDeviceId(), aMacAddress, 15);
		    if (str == null)
		      throw new IllegalStateException("payload cannot be created");
		     	    
		    String oCodeName     = aAndroidDevice.getCodeName();
		     
		    if ((oCodeName == null) || (oCodeName.isEmpty()))
		      {
		    	  oCodeName = "Unknown";
		      }
		   	    
		    StringBuffer localStringBuffer = new StringBuffer(str);
		    localStringBuffer.append(':');
		    localStringBuffer.append(fixeStringLengthSize(oCodeName, ' ', 21, true));
		    return stringToByte(localStringBuffer.toString());
		  }
}
