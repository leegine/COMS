head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.51.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityChangeConfirmRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.equity.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3EquityChangeConfirmRequestTest extends TestBaseForMock {

	private static WEB3LogUtility log = WEB3LogUtility
			.getInstance(WEB3EquityChangeConfirmRequestTest.class);

	private WEB3EquityChangeConfirmRequest changeConfirmRequest = null;

	public WEB3EquityChangeConfirmRequestTest(String arg0) {
		super(arg0);
	}

	protected void setUp() throws Exception {
		super.setUp();
		MOCK_MANAGER.setIsMockUsed(true);
		changeConfirmRequest = new WEB3EquityChangeConfirmRequest();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	// super.validate()
	public void testValidate_C0001() {
		final String STR_METHOD_NAME = "testValidate_C0001()";
		log.entering(TEST_START + STR_METHOD_NAME);

		try
		{
			changeConfirmRequest.orderQuantity = null;
			changeConfirmRequest.validate();
			fail();
		} 
		catch (WEB3BaseException l_ex)
		{
			assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00126, l_ex
					.getErrorInfo());
		} catch (Exception l_ex)
		{
			log.error(STR_METHOD_NAME, l_ex);
			fail();
		}
		log.info(STR_METHOD_NAME
				+ ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
		log.exiting(TEST_END + STR_METHOD_NAME);
	}

	// this.IDnull‚Ìê‡
	public void testValidate_C0002() {
		final String STR_METHOD_NAME = "testValidate_C0002()";
		log.entering(TEST_START + STR_METHOD_NAME);

		try {
			//super.validate()
        	changeConfirmRequest.orderQuantity = "1111";
        	changeConfirmRequest.orderPriceDiv = "0";
        	changeConfirmRequest.priceCondType = "0";
        	changeConfirmRequest.execCondType = "1";
        	changeConfirmRequest.expirationDateType ="1";
        	changeConfirmRequest.orderCondType = "0";
			
			changeConfirmRequest.id = null;
			changeConfirmRequest.validate();
			fail();
		} catch (WEB3BaseException l_ex)

		{
			assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00080, l_ex
					.getErrorInfo());
		} catch (Exception l_ex) {
			log.error(STR_METHOD_NAME, l_ex);
			fail();
		}
		log.info(STR_METHOD_NAME
				+ ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
		log.exiting(TEST_END + STR_METHOD_NAME);
	}

	//correctcase
	public void testValidate_C0003() {
		final String STR_METHOD_NAME = "testValidate_C0003()";
		log.entering(TEST_START + STR_METHOD_NAME);

		try 
		{
			//super.validate()
        	changeConfirmRequest.orderQuantity = "1111";
        	changeConfirmRequest.orderPriceDiv = "0";
        	changeConfirmRequest.priceCondType = "0";
        	changeConfirmRequest.execCondType = "1";
        	changeConfirmRequest.expirationDateType ="1";
        	changeConfirmRequest.orderCondType = "0";
        	
			changeConfirmRequest.id = "0001";
			changeConfirmRequest.validate();
		} 
		catch (Exception l_ex) {
			log.error(STR_METHOD_NAME, l_ex);
			fail();
		}
		log.info(STR_METHOD_NAME
				+ ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
		log.exiting(TEST_END + STR_METHOD_NAME);
	}

}
@
