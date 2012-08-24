package com.qut.spc;
import java.io.IOException;
import javax.servlet.http.*;


@SuppressWarnings("serial")
public class SolarPowerCalcServlet extends HttpServlet {
	String text="<panels>"+"<panel>"+"<id>1</id>"+"<name>A Panel</name>"+"<model>A model</model>"+"<manufacturer>ACompany</manufacturer>"+"<price>1233.12</price>"+"<companyWebsite>www.somewebsite.com</companyWebsite>"+"<capacity>someCapcity</capacity>"+"</panel>"+"<panel>"+"<id>1</id>"+"<name>A Panel</name>"+"<model>A model</model>"+"<manufacturer>ACompany</manufacturer>"+"<price>1233.12</price>"+"<companyWebsite>www.somewebsite.com</companyWebsite>"+"<capacity>someCapacity</capacity>"+"</panel>"+"</panels>";
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("application/xml");
		
		resp.getWriter().println(text);
	}
}
