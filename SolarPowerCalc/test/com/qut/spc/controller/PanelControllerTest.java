package com.qut.spc.controller;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
/**
 * Tests for PanelController
 * Note: not sure if XML I/O testing should be done here
 * @author Simen
 *
 */
public class PanelControllerTest {

	private PanelController controller;
	private HttpServletRequest request;

	private HttpServletResponse response;
	private String result;

	@Before
	public void setup() throws IOException, ServletException{
		
		controller =spy( new PanelController());
		request = mock(HttpServletRequest.class);

		response = new StubHttpServletResponse(this);		
		
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
	
	@Test
	public void testRedirect_noParameters_responseIsRedirectedToView() throws ServletException, IOException{
		
		HttpServletResponse mockResponse=mock(HttpServletResponse.class);
		when(mockResponse.getWriter()).thenReturn(new PrintWriter(new StringWriter()));
		controller.doGet(request, mockResponse);
		
		verify(mockResponse).sendRedirect("/view/PanelView.jsp");
		
	}
	
//	@Test
//	public void testParseParameter_existingName_correctValueIsReturned(){
//		request.setAttribute("whaaaat", "taaaw");
//		String value=controller.parseParameter(request,"whaaaat");
//		
//		assertEquals("taaaw", value);
//	
//	}
}
