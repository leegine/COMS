head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.50.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityChangeCancelAcceptRequestTest.java;


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
import webbroker3.equity.message.WEB3EquityChangeCancelAcceptRequest;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3EquityChangeCancelAcceptRequestTest extends TestBaseForMock {

	private static WEB3LogUtility log = WEB3LogUtility
			.getInstance(WEB3EquityChangeCancelAcceptRequestTest.class);

	private WEB3EquityChangeCancelAcceptRequest equityChangeCancelAcceptRequest = null;

	public WEB3EquityChangeCancelAcceptRequestTest(String arg0) {
		super(arg0);
	}

	protected void setUp() throws Exception {
		super.setUp();
		this.equityChangeCancelAcceptRequest = new WEB3EquityChangeCancelAcceptRequest();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	//識別コードプレフィクス一覧=null
	public void testValidate_C0001() {
		final String STR_METHOD_NAME = "testValidate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
        	equityChangeCancelAcceptRequest.orderRequestNumberPrefixGroup = null;
        	equityChangeCancelAcceptRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01291, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
	}

	//識別コードプレフィクス一覧.length=0
	public void testValidate_C0002() {
		final String STR_METHOD_NAME = "testValidate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
        	equityChangeCancelAcceptRequest.orderRequestNumberPrefixGroup = new String[]{};
        	equityChangeCancelAcceptRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01291, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
	}

	//スレッドNo=null
	public void testValidate_C0003() {
		final String STR_METHOD_NAME = "testValidate_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
        	equityChangeCancelAcceptRequest.orderRequestNumberPrefixGroup = new String[]{"aaa","bbb"};
        	equityChangeCancelAcceptRequest.threadNo = null;
        	equityChangeCancelAcceptRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01974, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
	}

	//correct case
	public void testValidate_C0004() {
		final String STR_METHOD_NAME = "testValidate_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
        	equityChangeCancelAcceptRequest.orderRequestNumberPrefixGroup = new String[]{"aaa","bbb"};
        	equityChangeCancelAcceptRequest.threadNo = new Long(1000);
        	equityChangeCancelAcceptRequest.validate();
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
	}

}
@
