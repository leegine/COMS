head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.51.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MarginCloseMarginChangeCompleteRequestTest.java;


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
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3MarginCloseMarginChangeCompleteRequestTest extends
		TestBaseForMock {
	
	private static WEB3LogUtility log = WEB3LogUtility
	.getInstance(WEB3MarginCloseMarginChangeCompleteRequestTest.class);
	
	private WEB3MarginCloseMarginChangeCompleteRequest changeCompleteRequest = null;

	public WEB3MarginCloseMarginChangeCompleteRequestTest(String arg0) {
		super(arg0);
	}

	protected void setUp() throws Exception {
		super.setUp();
		MOCK_MANAGER.setIsMockUsed(true);
		changeCompleteRequest = new WEB3MarginCloseMarginChangeCompleteRequest();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	//スーパークラスのvalidateメソッドを呼び出す
	public void testValidate_C0001()
	{
		final String STR_METHOD_NAME = "testValidate_C0001()";
		log.entering(TEST_START + STR_METHOD_NAME);

		try
		{
			changeCompleteRequest.orderPriceDiv= null;
			changeCompleteRequest.validate();
			fail();
		} 
		catch (WEB3BaseException l_ex)
		{
			assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00184, l_ex
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

	//this.ID＝nullの場合
	public void testValidate_C0002()
	{
		final String STR_METHOD_NAME = "testValidate_C0002()";
		log.entering(TEST_START + STR_METHOD_NAME);

		try
		{
			//super.validate()
			changeCompleteRequest.orderPriceDiv = "0";
			changeCompleteRequest.limitPrice=null;
			changeCompleteRequest.priceCondType = "0";
			changeCompleteRequest.execCondType = "1";
			changeCompleteRequest.expirationDateType = "1";
			changeCompleteRequest.expirationDate = null;
			changeCompleteRequest.orderCondType = "0";
			changeCompleteRequest.wlimitExecCondType = null;
			changeCompleteRequest.orderQuantity = "111";
			changeCompleteRequest.id= null;
			changeCompleteRequest.validate();
			fail();
		} 
		catch (WEB3BaseException l_ex)
		{
			assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00080, l_ex
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

	//this.決済建株一覧＝nullの場合
	public void testValidate_C0003()
	{
		final String STR_METHOD_NAME = "testValidate_C0003()";
		log.entering(TEST_START + STR_METHOD_NAME);

		try
		{
			//super.validate()
			changeCompleteRequest.orderPriceDiv = "0";
			changeCompleteRequest.limitPrice=null;
			changeCompleteRequest.priceCondType = "0";
			changeCompleteRequest.execCondType = "1";
			changeCompleteRequest.expirationDateType = "1";
			changeCompleteRequest.expirationDate = null;
			changeCompleteRequest.orderCondType = "0";
			changeCompleteRequest.wlimitExecCondType = null;
			changeCompleteRequest.orderQuantity = "111";
			changeCompleteRequest.id= "0001";
			changeCompleteRequest.closeMarginContractUnits = null; 
			changeCompleteRequest.validate();
			fail();
		} 
		catch (WEB3BaseException l_ex)
		{
			assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00610, l_ex
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

	//this.決済建株一覧が要素数＝０の場合
	public void testValidate_C0004()
	{
		final String STR_METHOD_NAME = "testValidate_C0004()";
		log.entering(TEST_START + STR_METHOD_NAME);

		try
		{
			//super.validate()
			changeCompleteRequest.orderPriceDiv = "0";
			changeCompleteRequest.limitPrice=null;
			changeCompleteRequest.priceCondType = "0";
			changeCompleteRequest.execCondType = "1";
			changeCompleteRequest.expirationDateType = "1";
			changeCompleteRequest.expirationDate = null;
			changeCompleteRequest.orderCondType = "0";
			changeCompleteRequest.wlimitExecCondType = null;
			changeCompleteRequest.orderQuantity = "111";
			changeCompleteRequest.id= "0001";
			changeCompleteRequest.closeMarginContractUnits = new WEB3MarginCloseMarginContractUnit[]{}; 
			changeCompleteRequest.validate();
			fail();
		} 
		catch (WEB3BaseException l_ex)
		{
			assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00611, l_ex
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

	//決済建株一覧要素内チェック
	public void testValidate_C0005()
	{
		final String STR_METHOD_NAME = "testValidate_C0005()";
		log.entering(TEST_START + STR_METHOD_NAME);

		try
		{
			//super.validate()
			changeCompleteRequest.orderPriceDiv = "0";
			changeCompleteRequest.limitPrice=null;
			changeCompleteRequest.priceCondType = "0";
			changeCompleteRequest.execCondType = "1";
			changeCompleteRequest.expirationDateType = "1";
			changeCompleteRequest.expirationDate = null;
			changeCompleteRequest.orderCondType = "0";
			changeCompleteRequest.wlimitExecCondType = null;
			changeCompleteRequest.orderQuantity = "111";
			changeCompleteRequest.id= "0001";
			changeCompleteRequest.closeMarginContractUnits = new WEB3MarginCloseMarginContractUnit[]
	            {new WEB3MarginCloseMarginContractUnit()}; 
			changeCompleteRequest.closeMarginContractUnits[0].id=null;
			changeCompleteRequest.validate();
			fail();
		} 
		catch (WEB3BaseException l_ex)
		{
			assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00080, l_ex
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

	//（super.注文株数 == null）、かつ
    // this.決済建株一覧の要素数内の全ての注文株数がnullまたは0の場合
	public void testValidate_C0006()
	{
		final String STR_METHOD_NAME = "testValidate_C0006()";
		log.entering(TEST_START + STR_METHOD_NAME);

		try
		{
			//super.validate()
			changeCompleteRequest.orderPriceDiv = "0";
			changeCompleteRequest.limitPrice=null;
			changeCompleteRequest.priceCondType = "0";
			changeCompleteRequest.execCondType = "1";
			changeCompleteRequest.expirationDateType = "1";
			changeCompleteRequest.expirationDate = null;
			changeCompleteRequest.orderCondType = "0";
			changeCompleteRequest.wlimitExecCondType = null;
			changeCompleteRequest.orderQuantity = null;
			changeCompleteRequest.id= "0001";
			changeCompleteRequest.closeMarginContractUnits = new WEB3MarginCloseMarginContractUnit[]
	            {new WEB3MarginCloseMarginContractUnit()}; 
			changeCompleteRequest.closeMarginContractUnits[0].id="0001";
			changeCompleteRequest.closeMarginContractUnits[0].settlePriority=null;
			changeCompleteRequest.closeMarginContractUnits[0].orderQuantity=null;
			changeCompleteRequest.validate();
			fail();
		} 
		catch (WEB3BaseException l_ex)
		{
			assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00612, l_ex
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
	public void testValidate_C0007()
	{
		final String STR_METHOD_NAME = "testValidate_C0007()";
		log.entering(TEST_START + STR_METHOD_NAME);

		try
		{
			//super.validate()
			changeCompleteRequest.orderPriceDiv = "0";
			changeCompleteRequest.limitPrice=null;
			changeCompleteRequest.priceCondType = "0";
			changeCompleteRequest.execCondType = "1";
			changeCompleteRequest.expirationDateType = "1";
			changeCompleteRequest.expirationDate = null;
			changeCompleteRequest.orderCondType = "0";
			changeCompleteRequest.wlimitExecCondType = null;
			changeCompleteRequest.orderQuantity = null;
			changeCompleteRequest.id= "0001";
			changeCompleteRequest.closeMarginContractUnits = new WEB3MarginCloseMarginContractUnit[]
	            {new WEB3MarginCloseMarginContractUnit()}; 
			changeCompleteRequest.closeMarginContractUnits[0].id="0001";
			changeCompleteRequest.closeMarginContractUnits[0].settlePriority=null;
			changeCompleteRequest.closeMarginContractUnits[0].orderQuantity="1000";
			changeCompleteRequest.validate();
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

	//super.validate()
	public void testValidateAtReverseOrder_C0001()
	{
		final String STR_METHOD_NAME = "testValidateAtReverseOrder_C0001()";
		log.entering(TEST_START + STR_METHOD_NAME);

		try
		{
			changeCompleteRequest.orderPriceDiv= null;
			changeCompleteRequest.validateAtReverseOrder();
			fail();
		} 
		catch (WEB3BaseException l_ex)
		{
			assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00184, l_ex
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

	//this.ID＝nullの場合
	public void testValidateAtReverseOrder_C0002()
	{
		final String STR_METHOD_NAME = "testValidateAtReverseOrder_C0002()";
		log.entering(TEST_START + STR_METHOD_NAME);

		try
		{
			//super.validate()
			changeCompleteRequest.orderPriceDiv = "0";
			changeCompleteRequest.limitPrice=null;
			changeCompleteRequest.priceCondType = "0";
			changeCompleteRequest.execCondType = "1";
			changeCompleteRequest.expirationDateType = "1";
			changeCompleteRequest.expirationDate = null;
			changeCompleteRequest.orderCondType = "0";
			changeCompleteRequest.wlimitExecCondType = null;
			changeCompleteRequest.orderQuantity = "111";
			changeCompleteRequest.id= null;
			changeCompleteRequest.validateAtReverseOrder();
			fail();
		} 
		catch (WEB3BaseException l_ex)
		{
			assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00080, l_ex
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
	
	//this.決済建株一覧＝nullの場合
	public void testValidateAtReverseOrder_C0003()
	{
		final String STR_METHOD_NAME = "testValidateAtReverseOrder_C0003()";
		log.entering(TEST_START + STR_METHOD_NAME);

		try
		{
			//super.validate()
			changeCompleteRequest.orderPriceDiv = "0";
			changeCompleteRequest.limitPrice=null;
			changeCompleteRequest.priceCondType = "0";
			changeCompleteRequest.execCondType = "1";
			changeCompleteRequest.expirationDateType = "1";
			changeCompleteRequest.expirationDate = null;
			changeCompleteRequest.orderCondType = "0";
			changeCompleteRequest.wlimitExecCondType = null;
			changeCompleteRequest.orderQuantity = "111";
			changeCompleteRequest.id= "0001";
			changeCompleteRequest.closeMarginContractUnits = null; 
			changeCompleteRequest.validateAtReverseOrder();
			fail();
		} 
		catch (WEB3BaseException l_ex)
		{
			assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00610, l_ex
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

	//this.決済建株一覧が要素数＝０の場合
	public void testValidateAtReverseOrder_C0004()
	{
		final String STR_METHOD_NAME = "testValidateAtReverseOrder_C0004()";
		log.entering(TEST_START + STR_METHOD_NAME);

		try
		{
			//super.validate()
			changeCompleteRequest.orderPriceDiv = "0";
			changeCompleteRequest.limitPrice=null;
			changeCompleteRequest.priceCondType = "0";
			changeCompleteRequest.execCondType = "1";
			changeCompleteRequest.expirationDateType = "1";
			changeCompleteRequest.expirationDate = null;
			changeCompleteRequest.orderCondType = "0";
			changeCompleteRequest.wlimitExecCondType = null;
			changeCompleteRequest.orderQuantity = "111";
			changeCompleteRequest.id= "0001";
			changeCompleteRequest.closeMarginContractUnits = new WEB3MarginCloseMarginContractUnit[]{}; 
			changeCompleteRequest.validateAtReverseOrder();
			fail();
		} 
		catch (WEB3BaseException l_ex)
		{
			assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00611, l_ex
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

	//this.確認時単価＝nullの場合
	public void testValidateAtReverseOrder_C0005()
	{
		final String STR_METHOD_NAME = "testValidateAtReverseOrder_C0005()";
		log.entering(TEST_START + STR_METHOD_NAME);

		try
		{
			//super.validate()
			changeCompleteRequest.orderPriceDiv = "0";
			changeCompleteRequest.limitPrice=null;
			changeCompleteRequest.priceCondType = "0";
			changeCompleteRequest.execCondType = "1";
			changeCompleteRequest.expirationDateType = "1";
			changeCompleteRequest.expirationDate = null;
			changeCompleteRequest.orderCondType = "0";
			changeCompleteRequest.wlimitExecCondType = null;
			changeCompleteRequest.orderQuantity = "111";
			changeCompleteRequest.id= "0001";
			changeCompleteRequest.checkPrice = null;
			changeCompleteRequest.closeMarginContractUnits = new WEB3MarginCloseMarginContractUnit[]{new WEB3MarginCloseMarginContractUnit()}; 
			changeCompleteRequest.validateAtReverseOrder();
			fail();
		} 
		catch (WEB3BaseException l_ex)
		{
			assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00206, l_ex
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

	//this.確認時発注日＝nullの場合
	public void testValidateAtReverseOrder_C0006()
	{
		final String STR_METHOD_NAME = "testValidateAtReverseOrder_C0006()";
		log.entering(TEST_START + STR_METHOD_NAME);

		try
		{
			//super.validate()
			changeCompleteRequest.orderPriceDiv = "0";
			changeCompleteRequest.limitPrice=null;
			changeCompleteRequest.priceCondType = "0";
			changeCompleteRequest.execCondType = "1";
			changeCompleteRequest.expirationDateType = "1";
			changeCompleteRequest.expirationDate = null;
			changeCompleteRequest.orderCondType = "0";
			changeCompleteRequest.wlimitExecCondType = null;
			changeCompleteRequest.orderQuantity = "111";
			changeCompleteRequest.id= "0001";
			changeCompleteRequest.checkPrice = "1000";
			changeCompleteRequest.checkDate = null;
			changeCompleteRequest.closeMarginContractUnits = new WEB3MarginCloseMarginContractUnit[]{new WEB3MarginCloseMarginContractUnit()}; 
			changeCompleteRequest.validateAtReverseOrder();
			fail();
		} 
		catch (WEB3BaseException l_ex)
		{
			assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00078, l_ex
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

	//super.validateSuccOrder();
	public void testValidateAtReverseOrder_C0007()
	{
		final String STR_METHOD_NAME = "testValidateAtReverseOrder_C0007()";
		log.entering(TEST_START + STR_METHOD_NAME);

		try
		{
			//super.validate()
			changeCompleteRequest.orderPriceDiv = "0";
			changeCompleteRequest.limitPrice=null;
			changeCompleteRequest.priceCondType = "7";
			changeCompleteRequest.execCondType = "1";
			changeCompleteRequest.expirationDateType = "1";
			changeCompleteRequest.expirationDate = null;
			changeCompleteRequest.orderCondType = "0";
			changeCompleteRequest.wlimitExecCondType = null;
			changeCompleteRequest.orderQuantity = "111";
			changeCompleteRequest.id= "0001";
			changeCompleteRequest.checkPrice = "1000";
			changeCompleteRequest.checkDate = WEB3DateUtility.getDate("20080704","yyyyMMdd");
			changeCompleteRequest.closeMarginContractUnits = new WEB3MarginCloseMarginContractUnit[]{new WEB3MarginCloseMarginContractUnit()}; 
			changeCompleteRequest.validateAtReverseOrder();
			fail();
		} 
		catch (WEB3BaseException l_ex)
		{
			assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02234, l_ex
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
	public void testValidateAtReverseOrder_C0008()
	{
		final String STR_METHOD_NAME = "testValidateAtReverseOrder_C0008()";
		log.entering(TEST_START + STR_METHOD_NAME);

		try
		{
			//super.validate()
			changeCompleteRequest.orderPriceDiv = "0";
			changeCompleteRequest.limitPrice=null;
			changeCompleteRequest.priceCondType = "0";
			changeCompleteRequest.execCondType = "1";
			changeCompleteRequest.expirationDateType = "1";
			changeCompleteRequest.expirationDate = null;
			changeCompleteRequest.orderCondType = "0";
			changeCompleteRequest.wlimitExecCondType = null;
			changeCompleteRequest.orderQuantity = "111";
			changeCompleteRequest.id= "0001";
			changeCompleteRequest.checkPrice = "1000";
			changeCompleteRequest.checkDate = WEB3DateUtility.getDate("20080704","yyyyMMdd");
			changeCompleteRequest.closeMarginContractUnits = new WEB3MarginCloseMarginContractUnit[]{new WEB3MarginCloseMarginContractUnit()}; 
			changeCompleteRequest.validateAtReverseOrder();
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
