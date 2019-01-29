head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.49.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MstkBookPriceRegistRequestTest.java;


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

public class WEB3MstkBookPriceRegistRequestTest extends TestBaseForMock {

	private static WEB3LogUtility log = WEB3LogUtility
	.getInstance(WEB3MstkBookPriceRegistRequestTest.class);
	
	private WEB3MstkBookPriceRegistRequest bookPriceRegistRequest = null;

	public WEB3MstkBookPriceRegistRequestTest(String arg0) {
		super(arg0);
	}

	protected void setUp() throws Exception {
		super.setUp();
		MOCK_MANAGER.setIsMockUsed(true);
		bookPriceRegistRequest = new WEB3MstkBookPriceRegistRequest();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	//保有資産IDのチェック
	public void testValidate_C0001()
	{
		final String STR_METHOD_NAME = "testValidate_C0001()";
		log.entering(TEST_START + STR_METHOD_NAME);

		try
		{
			bookPriceRegistRequest.assetId= null;
			bookPriceRegistRequest.validate();
			fail();
		} 
		catch (WEB3BaseException l_ex)
		{
			assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01919, l_ex
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

	//変更後簿価単価チェック
	public void testValidate_C0002()
	{
		final String STR_METHOD_NAME = "testValidate_C0002()";
		log.entering(TEST_START + STR_METHOD_NAME);

		try
		{
			bookPriceRegistRequest.assetId= "001";
			bookPriceRegistRequest.aftBookPrice=null;
			bookPriceRegistRequest.validate();
			fail();
		} 
		catch (WEB3BaseException l_ex)
		{
			assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01920, l_ex
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

	//数値チェック
	//桁数チェック
	public void testValidate_C0003()
	{
		final String STR_METHOD_NAME = "testValidate_C0003()";
		log.entering(TEST_START + STR_METHOD_NAME);

		try
		{
			bookPriceRegistRequest.assetId= "001";
			bookPriceRegistRequest.aftBookPrice="111111111";
			bookPriceRegistRequest.validate();
			fail();
		} 
		catch (WEB3BaseException l_ex)
		{
			assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01920, l_ex
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

	//数値チェック
	//0以下チェック
	public void testValidate_C0004()
	{
		final String STR_METHOD_NAME = "testValidate_C0004()";
		log.entering(TEST_START + STR_METHOD_NAME);

		try
		{
			bookPriceRegistRequest.assetId= "001";
			bookPriceRegistRequest.aftBookPrice="-1111";
			bookPriceRegistRequest.validate();
			fail();
		} 
		catch (WEB3BaseException l_ex)
		{
			assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01920, l_ex
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

	//数値チェック
	//変更後簿価単価が数値以外の値
	public void testValidate_C0005()
	{
		final String STR_METHOD_NAME = "testValidate_C0005()";
		log.entering(TEST_START + STR_METHOD_NAME);

		try
		{
			bookPriceRegistRequest.assetId= "001";
			bookPriceRegistRequest.aftBookPrice="aaaa";
			bookPriceRegistRequest.validate();
			fail();
		} 
		catch (WEB3BaseException l_ex)
		{
			assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01920, l_ex
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
			bookPriceRegistRequest.assetId= "001";
			bookPriceRegistRequest.aftBookPrice="1111";
			bookPriceRegistRequest.validate();
		} 
		catch (Exception l_ex)
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
