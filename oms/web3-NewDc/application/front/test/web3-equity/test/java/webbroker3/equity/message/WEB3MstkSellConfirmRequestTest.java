head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.50.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MstkSellConfirmRequestTest.java;


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

public class WEB3MstkSellConfirmRequestTest extends TestBaseForMock {

	private static WEB3LogUtility log = WEB3LogUtility
	.getInstance(WEB3MstkSellConfirmRequestTest.class);
	
	private WEB3MstkSellConfirmRequest confirmRequest = null;

	public WEB3MstkSellConfirmRequestTest(String arg0) {
		super(arg0);
	}

	protected void setUp() throws Exception {
		super.setUp();
		MOCK_MANAGER.setIsMockUsed(true);
		confirmRequest = new WEB3MstkSellConfirmRequest();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	//銘柄コードチェック
	public void testValidate_C0001()
	{
		final String STR_METHOD_NAME = "testValidate_C0001()";
		log.entering(TEST_START + STR_METHOD_NAME);

		try
		{
			confirmRequest.productCode= null;
			confirmRequest.validate();
			fail();
		} 
		catch (WEB3BaseException l_ex)
		{
			assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00079, l_ex
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

	//注文株数チェック
	//注文株数=null
	public void testValidate_C0002()
	{
		final String STR_METHOD_NAME = "testValidate_C0002()";
		log.entering(TEST_START + STR_METHOD_NAME);

		try
		{
			confirmRequest.productCode= "0001";
			confirmRequest.orderQuantity = null;
			confirmRequest.validate();
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

	//注文株数チェック
	//this.注文株数≠数字
	public void testValidate_C0003()
	{
		final String STR_METHOD_NAME = "testValidate_C0003()";
		log.entering(TEST_START + STR_METHOD_NAME);

		try
		{
			confirmRequest.productCode= "0001";
			confirmRequest.orderQuantity = "aaaa";
			confirmRequest.validate();
			fail();
		} 
		catch (WEB3BaseException l_ex)
		{
			assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00901, l_ex
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

	//注文株数チェック
	//this.注文株数≦０
	public void testValidate_C0004()
	{
		final String STR_METHOD_NAME = "testValidate_C0004()";
		log.entering(TEST_START + STR_METHOD_NAME);

		try
		{
			confirmRequest.productCode= "0001";
			confirmRequest.orderQuantity = "-111";
			confirmRequest.validate();
			fail();
		} 
		catch (WEB3BaseException l_ex)
		{
			assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00902, l_ex
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

	//注文株数チェック
	//this.注文株数＞8byte
	public void testValidate_C0005()
	{
		final String STR_METHOD_NAME = "testValidate_C0005()";
		log.entering(TEST_START + STR_METHOD_NAME);

		try
		{
			confirmRequest.productCode= "0001";
			confirmRequest.orderQuantity = "111111111";
			confirmRequest.validate();
			fail();
		} 
		catch (WEB3BaseException l_ex)
		{
			assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00903, l_ex
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

	//correctcase
	public void testValidate_C0006()
	{
		final String STR_METHOD_NAME = "testValidate_C0006()";
		log.entering(TEST_START + STR_METHOD_NAME);

		try
		{
			confirmRequest.productCode= "0001";
			confirmRequest.orderQuantity = "1111";
			confirmRequest.validate();
		} catch (Exception l_ex)
		{
			log.error(STR_METHOD_NAME, l_ex);
			fail();
		}
		log.info(STR_METHOD_NAME
				+ ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
		log.exiting(TEST_END + STR_METHOD_NAME);
	}

}
@
