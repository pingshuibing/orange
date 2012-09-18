package com.qut.spc.task;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.os.AsyncTask;
import android.util.Xml;

/**
 * Implement class should override onPostExecute to know when it's done
 * 
 * @author QuocViet
 * @see http://developer.android.com/training/basics/network-ops/xml.html
 */
public abstract class XmlRequestTask extends AsyncTask<String, Void, XmlPullParser> {
	private final int READ_TIMEOUT = 30000; /* milliseconds */
	private final int CONNECT_TIMEOUT = 60000; /* milliseconds */

	private String method = "GET";

	public XmlRequestTask() {
		
	}
	
	@Override
	protected XmlPullParser doInBackground(String... params) {
		InputStream stream;
		try {
			stream = getDownloadStream(params[0]);
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
		try {
			return parseXml(stream);
		} catch (XmlPullParserException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	protected abstract boolean onXmlTag(XmlPullParser parser, int eventType)
			throws IOException, XmlPullParserException;

	private XmlPullParser parseXml(InputStream stream)
			throws XmlPullParserException, IOException {
		XmlPullParser parser = Xml.newPullParser();

		parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
		parser.setInput(stream, null);
		
		int type = parser.getEventType();
		while (type != XmlPullParser.END_DOCUMENT) {
			// Stop parsing if the callback returns false
			if (!onXmlTag(parser, type)) {
				break;
			}
			type = parser.next();
		}
		return parser;
	}
	
	// Given a string representation of a URL, sets up a connection and gets
	// an input stream.
	private InputStream getDownloadStream(String url) throws IOException {
		URL u = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) u.openConnection();

		conn.setReadTimeout(READ_TIMEOUT);
		conn.setConnectTimeout(CONNECT_TIMEOUT);
		conn.setRequestMethod(method);
		conn.setDoInput(true);

		// Starts the query
		conn.connect();
		return conn.getInputStream();
	}
	
	protected static boolean findTag(XmlPullParser parser, String tagName)
			throws XmlPullParserException, IOException {
		int type = parser.getEventType();
		
		while (type != XmlPullParser.END_DOCUMENT) {
			if (type == XmlPullParser.START_TAG) {
				if (parser.getName().equals(tagName)) {
					return true;
				}
			}
			type = parser.next();
		}
		return false;
	}
	
	protected static String findText(XmlPullParser parser, String tagName)
			throws IOException, XmlPullParserException {
		String txt = "";
		if (findTag(parser, tagName)) {
			if (parser.next() == XmlPullParser.TEXT) {
				txt = parser.getText();
			}
		}
		return txt;
	}
}
