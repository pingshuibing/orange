package com.qut.spc.postcode;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
public class PostcodeValidatorTest {
	
	@Test (expected=IllegalArgumentException.class)
	public void testSetPostcode_postcodeWithLetters_IllegalArgumentExceptionIsThrown(){
		PostcodeUtil.validatePostcode("1234Q");
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testSetPostcode_postcodeLongerThen4Letters_IllegalArgumentExceptionIsThrown(){
		PostcodeUtil.validatePostcode("12345");
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testSetPostcode_postcodeShorterThen4Letters_IllegalArgumentExceptionIsThrown(){
		PostcodeUtil.validatePostcode("123");
	}
	
	@Test
	public void testSetPostcode_validPostCode_trueIsReturned(){
		boolean result=PostcodeUtil.validatePostcode("3231");
		assertEquals(true, result);
	}
	
	@Test
	public void testTransformToValidPostcode_validUnrecognizedPostcode_transformedPostcodeIsReturned(){
		String postcode=PostcodeUtil.transformPostcode("1234");
		
		assertEquals("2000", postcode);
	}
	@Test (expected=IllegalArgumentException.class)
	public void testTransformToValidPostcode_invalidUnrecognizedPostcode_IllegalArgumentExceptionIsThrown(){
		PostcodeUtil.transformPostcode("0001");
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testTransformToValidPostcode_postcodeLoverBoundary_IllegalArgumentExceptionIsThrown(){
		PostcodeUtil.transformPostcode("0199");
	}
	
	@Test
	public void testTransformToValidPostcode_ValidpostcodeLoverBoundary_IllegalArgumentExceptionIsThrown(){
		String res=PostcodeUtil.transformPostcode("0200");
		assertEquals("2900", res);
	}
	
}
