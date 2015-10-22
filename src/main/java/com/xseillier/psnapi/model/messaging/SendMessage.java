package com.xseillier.psnapi.model.messaging;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.xseillier.psnapi.utils.FileUtils;

/**
 *
 * @author xseillier
 * @version 1.0 2 oct. 2015
 */
public class SendMessage {

	private List<String>    mTo;
	private Content         mMessage;	
	private transient File  mFile = null;
	
	public SendMessage( String aMessage ) {		
		initMessage( aMessage );
	}
	
	public SendMessage(List<String> aTo, String aMessage ) {		
		mTo = aTo;
		mMessage = new Content();		
		mMessage.setBody( aMessage );
		mMessage.setFakeMessageUid( System.currentTimeMillis() );
		initMessage( aMessage );
	}
	
	
	public SendMessage(String aTo, String aMessage ) {		
		mTo = new ArrayList<>();
		addTo( aTo );	
		initMessage( aMessage );
	}

	private void initMessage( String aMessage ) {
		mMessage = new Content();		
		mMessage.setBody( aMessage );
		mMessage.setFakeMessageUid( System.currentTimeMillis() );
	}
	
	public List<String> getTo() {
		return mTo;
	}

	public void setTo(List<String> aTo) {
		mTo = aTo;
	}

	public void addTo(String aTo) {
		if( mTo == null )
			mTo = new ArrayList<>();
		mTo.add( aTo );
	}
	
	public long getFakeMessageUid() {
		return mMessage.getFakeMessageUid();
	}

	public void setFakeMessageUid(long aFakeMessageUid) {
		mMessage.setFakeMessageUid(aFakeMessageUid);
	}

	public String getBody() {
		return mMessage.getBody();
	}

	public void setBody(String aBody) {
		mMessage.setBody(aBody);
	}

	public File getImage() {
		return mFile;
	}

	public void setImage(File aImageFile ) throws IOException {
		
		if( !FileUtils.isImage( aImageFile ) ) {
			throw new IllegalArgumentException("Only image is allow.");
		}
		mFile = aImageFile;
		mMessage.setMessageKind( MessageKindEnum.TYPE_IMAGE );
	}
	
	
	
	public class Content {
		
		private MessageKindEnum mMessageKind = MessageKindEnum.TYPE_STRING;
		private long            mFakeMessageUid;
		private String          mBody;
			
		public long getFakeMessageUid() {
			return mFakeMessageUid;
		}
		
		public void setFakeMessageUid(long aFakeMessageUid) {
			mFakeMessageUid = aFakeMessageUid;
		}

		public String getBody() {
			return mBody;
		}

		public void setBody(String aBody) {
			mBody = aBody;
		}

		public MessageKindEnum getMessageKind() {
			return mMessageKind;
		}
		
		public void setMessageKind(MessageKindEnum aMessageKind) {
			mMessageKind = aMessageKind;
		}
		
	}
}

