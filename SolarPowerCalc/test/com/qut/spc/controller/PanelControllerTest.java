package com.qut.spc.controller;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;

public class PanelControllerTest {

	private PanelController controller;
	private HttpServletRequest request;

	private HttpServletResponse response;
	private String result;

	@Before
	public void setup() throws IOException, ServletException{
		controller = new PanelController();
		request = mock(HttpServletRequest.class);

		response = new StubResponse();		
		
		controller.doGet(request, response);
		
		result = response.getWriter().toString();


	}
	
	@Test
	public void testDoGet_noParameters_responseIsNotEmpty(){		
		assertTrue(!result.trim().isEmpty());
		
	}
	
	@Test
	public void testDoGet_noParameters_responseIsXML() throws Exception{		
		String type=response.getContentType();
		
		assertEquals("application/xml", type);
	}
	
	private class StubResponse implements HttpServletResponse
	{

		private String contentType;
		private StringWriter sw;
		private PrintWriter pw;
		public StubResponse(){
			sw = new StringWriter();
			pw = new PrintWriter(sw);
		}
		@Override
		public void flushBuffer() throws IOException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public int getBufferSize() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public String getCharacterEncoding() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getContentType() {
			return contentType;
		}

		@Override
		public Locale getLocale() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public ServletOutputStream getOutputStream() throws IOException {
			return null;
		}

		@Override
		public PrintWriter getWriter() throws IOException {
			return pw;
		}

		@Override
		public boolean isCommitted() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void reset() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void resetBuffer() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setBufferSize(int arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setCharacterEncoding(String arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setContentLength(int arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setContentType(String arg0) {
			contentType=arg0;
		}

		@Override
		public void setLocale(Locale arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void addCookie(Cookie arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void addDateHeader(String arg0, long arg1) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void addHeader(String arg0, String arg1) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void addIntHeader(String arg0, int arg1) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean containsHeader(String arg0) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public String encodeRedirectURL(String arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String encodeRedirectUrl(String arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String encodeURL(String arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String encodeUrl(String arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void sendError(int arg0) throws IOException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void sendError(int arg0, String arg1) throws IOException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void sendRedirect(String arg0) throws IOException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setDateHeader(String arg0, long arg1) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setHeader(String arg0, String arg1) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setIntHeader(String arg0, int arg1) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setStatus(int arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setStatus(int arg0, String arg1) {
			// TODO Auto-generated method stub
			
		}
	}
}
