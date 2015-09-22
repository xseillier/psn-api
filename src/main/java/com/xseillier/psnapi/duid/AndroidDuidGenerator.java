package com.xseillier.psnapi.duid;

import com.xseillier.psnapi.duid.model.AndroidDevice;

public class AndroidDuidGenerator extends DuidGenerator {

	@Override
	public int getIndexOne() {
		return 7;
	}

	@Override
	public int getIndexTwo() {
		return 2;
	}

	
	 
	 // oManufacturer, oDevice, oDeviceID,
	 public byte[] generateDevicePartId(AndroidDevice aAndroidDevice, String aMacAddress )
	  {
	    String str = createPayload(aAndroidDevice.getDeviceId(), aMacAddress, 15);
	    if (str == null)
	        throw new IllegalStateException("payload cannot be created");
	     
	    
	      String oManufacturer = aAndroidDevice.getManufacturer();
	      String oCodeName     = aAndroidDevice.getCodeName();
	      
	      if ((oManufacturer == null) || (oManufacturer.isEmpty()))
	      {
	    	  oManufacturer = "Unknown";
	      }
	      
	      if ((oCodeName == null) || (oCodeName.isEmpty()))
	      {
	    	  oCodeName = "Unknown";
	      }
	            
	      StringBuffer localStringBuffer = new StringBuffer(str);
	      localStringBuffer.append(':');
	      localStringBuffer.append( fixeStringLengthSize(oManufacturer, ' ', 10, true) );
	      localStringBuffer.append(':');
	      localStringBuffer.append( fixeStringLengthSize(oCodeName, ' ', 10, true) );
	      return stringToByte(localStringBuffer.toString());
	  }
}
