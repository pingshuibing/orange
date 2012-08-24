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


	@Before
	public void setup() throws IOException, ServletException{
		
		controller =spy( new PanelController());



	}
}
