head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.50.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityChangeCompleteRequestTest.java;


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

public class WEB3EquityChangeCompleteRequestTest extends TestBaseForMock {

	private static WEB3LogUtility log = WEB3LogUtility
			.getInstance(WEB3EquityChangeCompleteRequestTest.class);

	private WEB3EquityChangeCompleteRequest changeCompleteRequest = null;

	public WEB3EquityChangeCompleteRequestTest(String arg0) {
		super(arg0);
	}

	protected void setUp() throws Exception {
		super.setUp();
		this.changeCompleteRequest = new WEB3EquityChangeCompleteRequest();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	//super.validate()
	public void testValidate_C0001() {
		final String STR_METHOD_NAME = "testValidate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
        	changeCompleteRequest.orderQuantity = null;
        	changeCompleteRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00126, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
	}

	//this.IDnull‚Ìê‡
	public void testValidate_C0002() {
		final String STR_METHOD_NAME = "testValidate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
        	//super.validate()
        	changeCompleteRequest.orderQuantity = "1111";
        	changeCompleteRequest.orderPriceDiv = "0";
        	changeCompleteRequest.priceCondType = "0";
        	changeCompleteRequest.execCondType = "1";
        	changeCompleteRequest.expirationDateType ="1";
        	changeCompleteRequest.orderCondType = "0";
        	
        	changeCompleteRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00600, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
	}

	//correctcase
	public void testValidate_C0003() {
		final String STR_METHOD_NAME = "testValidate_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
        	//super.validate()
        	changeCompleteRequest.orderQuantity = "1111";
        	changeCompleteRequest.orderPriceDiv = "0";
        	changeCompleteRequest.priceCondType = "0";
        	changeCompleteRequest.execCondType = "1";
        	changeCompleteRequest.expirationDateType ="1";
        	changeCompleteRequest.orderCondType = "0";
        	
        	changeCompleteRequest.id = "0001";
        	changeCompleteRequest.validate();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
	}

}
@
