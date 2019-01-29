head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.24.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3OptionsCloseMarginChangeConfirmRequestTest_OT.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数オプション訂正返済確認リクエスト(WEB3OptionsCloseMarginChangeConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2008/06/05 侯翠ナ (中訊) 新規作成
*/

package webbroker3.ifo.message;

import test.util.JunitTestBase;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * @@author 侯翠ナ
 * @@version 1.0 
 */
public class WEB3OptionsCloseMarginChangeConfirmRequestTest_OT extends JunitTestBase{
    private static WEB3LogUtility log =
	        WEB3LogUtility.getInstance(WEB3OptionsCloseMarginChangeConfirmRequest.class);
	   
	WEB3OptionsCloseMarginChangeConfirmRequest l_request = 
		new WEB3OptionsCloseMarginChangeConfirmRequest();
	
	public WEB3OptionsCloseMarginChangeConfirmRequestTest_OT(String arg0) {
		super(arg0);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/*
	 * this.注文単価区分がnullの値であれば例外をスローする。
	 */
	public void testValidate_case001() {

        final String STR_METHOD_NAME = "testValidate_case001()";
        log.entering(TEST_START + STR_METHOD_NAME);
		try 
		{
			l_request.validate();
			fail();
		}
		catch(WEB3BaseException l_ex)
		{
			log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00184);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
		}
		catch(Exception l_ex)
		{
			fail();
			log.error(ERROR + l_ex.getMessage(), l_ex);
		}
	}

	/**
	 * this.ＩＤ=null?の場合、例外をスローする。
	 */
	public void testValidate_case002() {

        final String STR_METHOD_NAME = "testValidate_case002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        // 注文単価区分
        l_request.orderPriceDiv = "0";
        // 執行条件
        l_request.execCondType = "1";
        //注文期限区分
        l_request.expirationDateType = "1";
        // 発注条件区分
        l_request.orderCondType = "0";
		try 
		{
			l_request.validate();
			fail();
		}
		catch(WEB3BaseException l_ex)
		{
			log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00080);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
		}
		catch(Exception l_ex)
		{
			fail();
			log.error(ERROR + l_ex.getMessage(), l_ex);
		}
	}
	
	/**
	 * this.返済建玉=null の場合、例外をスローする。
	 */
	public void testValidate_case003() {

        final String STR_METHOD_NAME = "testValidate_case003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        // 注文単価区分
        l_request.orderPriceDiv = "0";
        // 執行条件
        l_request.execCondType = "1";
        //注文期限区分
        l_request.expirationDateType = "1";
        // 発注条件区分
        l_request.orderCondType = "0";
        l_request.id = "1001";
        //返済建玉
        l_request.closeMarginContractUnits = null;
		try 
		{
			l_request.validate();
			fail();
		}
		catch(WEB3BaseException l_ex)
		{
			log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00178);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
		}
		catch(Exception l_ex)
		{
			fail();
			log.error(ERROR + l_ex.getMessage(), l_ex);
		}
	}
	
	/**
	 * this.返済建玉の要素数=0?の場合、例外をスローする。
	 */
	public void testValidate_case004() {

        final String STR_METHOD_NAME = "testValidate_case004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        // 注文単価区分
        l_request.orderPriceDiv = "0";
        // 執行条件
        l_request.execCondType = "1";
        //注文期限区分
        l_request.expirationDateType = "1";
        // 発注条件区分
        l_request.orderCondType = "0";
        l_request.id = "1001";
        //返済建玉
        l_request.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[0];
		try 
		{
			l_request.validate();
			fail();
		}
		catch(WEB3BaseException l_ex)
		{
			log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00178);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
		}
		catch(Exception l_ex)
		{
			fail();
			log.error(ERROR + l_ex.getMessage(), l_ex);
		}
	}
	
	/**
	 * this.注文数量≠null and this.注文数量≠数字 の場合、 例外をスローする。 
	 */
	public void testValidate_case005() {

        final String STR_METHOD_NAME = "testValidate_case005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        // 注文単価区分
        l_request.orderPriceDiv = "0";
        // 執行条件
        l_request.execCondType = "1";
        //注文期限区分
        l_request.expirationDateType = "1";
        // 発注条件区分
        l_request.orderCondType = "0";
        l_request.id = "1001";
        //返済建玉
        l_request.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[2];
        WEB3FuturesOptionsCloseMarginContractUnit l_unit = new WEB3FuturesOptionsCloseMarginContractUnit();
        l_request.closeMarginContractUnits[0] = l_unit;
        l_request.opOrderQuantity = "a";
		try 
		{
			l_request.validate();
			fail();
		}
		catch(WEB3BaseException l_ex)
		{
			log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00075);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
		}
		catch(Exception l_ex)
		{
			fail();
			log.error(ERROR + l_ex.getMessage(), l_ex);
		}
	}
	
	/**
	 * this.注文数量≠null and this.注文数量 = 0 の場合、 例外をスローする。 
	 */
	public void testValidate_case006() {

        final String STR_METHOD_NAME = "testValidate_case006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        // 注文単価区分
        l_request.orderPriceDiv = "0";
        // 執行条件
        l_request.execCondType = "1";
        //注文期限区分
        l_request.expirationDateType = "1";
        // 発注条件区分
        l_request.orderCondType = "0";
        l_request.id = "1001";
        //返済建玉
        l_request.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[2];
        WEB3FuturesOptionsCloseMarginContractUnit l_unit = new WEB3FuturesOptionsCloseMarginContractUnit();
        l_request.closeMarginContractUnits[0] = l_unit;
        l_request.opOrderQuantity = "0";
		try 
		{
			l_request.validate();
			fail();
		}
		catch(WEB3BaseException l_ex)
		{
			log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00076);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
		}
		catch(Exception l_ex)
		{
			fail();
			log.error(ERROR + l_ex.getMessage(), l_ex);
		}
	}
	
	/**
	 * this.注文数量≠null and this.注文数量 < 0 の場合、 例外をスローする。 
	 */
	public void testValidate_case007() {

        final String STR_METHOD_NAME = "testValidate_case007()";
        log.entering(TEST_START + STR_METHOD_NAME);
        // 注文単価区分
        l_request.orderPriceDiv = "0";
        // 執行条件
        l_request.execCondType = "1";
        //注文期限区分
        l_request.expirationDateType = "1";
        // 発注条件区分
        l_request.orderCondType = "0";
        l_request.id = "1001";
        //返済建玉
        l_request.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[2];
        WEB3FuturesOptionsCloseMarginContractUnit l_unit = new WEB3FuturesOptionsCloseMarginContractUnit();
        l_request.closeMarginContractUnits[0] = l_unit;
        l_request.opOrderQuantity = "-6";
		try 
		{
			l_request.validate();
			fail();
		}
		catch(WEB3BaseException l_ex)
		{
			log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00076);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
		}
		catch(Exception l_ex)
		{
			fail();
			log.error(ERROR + l_ex.getMessage(), l_ex);
		}
	}
	
	/**
	 * 返済建玉.決済順位≠null and 返済建玉.数量=null の場合、例外をスローする。
	 */
	public void testValidate_case008() {

        final String STR_METHOD_NAME = "testValidate_case007()";
        log.entering(TEST_START + STR_METHOD_NAME);
        // 注文単価区分
        l_request.orderPriceDiv = "0";
        // 執行条件
        l_request.execCondType = "1";
        //注文期限区分
        l_request.expirationDateType = "1";
        // 発注条件区分
        l_request.orderCondType = "0";
        l_request.id = "1001";
        //返済建玉
        l_request.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[1];
        WEB3FuturesOptionsCloseMarginContractUnit l_unit = new WEB3FuturesOptionsCloseMarginContractUnit();
        l_unit.id = "1001";
        l_unit.settlePriority = "1";
        l_request.closeMarginContractUnits[0] = l_unit;
        
		try 
		{
			l_request.validate();
			fail();
		}
		catch(WEB3BaseException l_ex)
		{
			log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00180);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
		}
		catch(Exception l_ex)
		{
			fail();
			log.error(ERROR + l_ex.getMessage(), l_ex);
		}
	}
	
	/**
	 * this.注文数量 = null and （すべての返済建玉の数量=0 ） の場合、例外をスローする。
	 */
	public void testValidate_case009() {

        final String STR_METHOD_NAME = "testValidate_case009()";
        log.entering(TEST_START + STR_METHOD_NAME);
        // 注文単価区分
        l_request.orderPriceDiv = "0";
        // 執行条件
        l_request.execCondType = "1";
        //注文期限区分
        l_request.expirationDateType = "1";
        // 発注条件区分
        l_request.orderCondType = "0";
        l_request.id = "1001";
        //返済建玉
        l_request.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[1];
        WEB3FuturesOptionsCloseMarginContractUnit l_unit = new WEB3FuturesOptionsCloseMarginContractUnit();
        l_unit.id = "1001";
        l_unit.settlePriority = "1";
        l_unit.contractOrderQuantity = "0";
        l_request.closeMarginContractUnits[0] = l_unit;
        
		try 
		{
			l_request.validate();
			fail();
		}
		catch(WEB3BaseException l_ex)
		{
			log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00285);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
		}
		catch(Exception l_ex)
		{
			fail();
			log.error(ERROR + l_ex.getMessage(), l_ex);
		}
	}
	
	/**
	 * this.注文数量 = null and （すべての返済建玉の数量=null ） の場合、例外をスローする。
	 */
	public void testValidate_case010() {

        final String STR_METHOD_NAME = "testValidate_case010()";
        log.entering(TEST_START + STR_METHOD_NAME);
        // 注文単価区分
        l_request.orderPriceDiv = "0";
        // 執行条件
        l_request.execCondType = "1";
        //注文期限区分
        l_request.expirationDateType = "1";
        // 発注条件区分
        l_request.orderCondType = "0";
        l_request.id = "1001";
        
        //返済建玉
        l_request.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnitForTest[1];
        WEB3FuturesOptionsCloseMarginContractUnitForTest l_unit = new WEB3FuturesOptionsCloseMarginContractUnitForTest();
        l_unit.id = "1001";
        l_unit.settlePriority = "1";
        l_request.closeMarginContractUnits[0] = l_unit;
        
		try 
		{
			l_request.validate();
			fail();
		}
		catch(WEB3BaseException l_ex)
		{
			log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00285);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
		}
		catch(Exception l_ex)
		{
			fail();
			log.error(ERROR + l_ex.getMessage(), l_ex);
		}
	}
	
	/**
	 * 正常數據
	 */
	public void testValidate_case011() {

        final String STR_METHOD_NAME = "testValidate_case011()";
        log.entering(TEST_START + STR_METHOD_NAME);
        // 注文単価区分
        l_request.orderPriceDiv = "0";
        // 執行条件
        l_request.execCondType = "1";
        //注文期限区分
        l_request.expirationDateType = "1";
        // 発注条件区分
        l_request.orderCondType = "0";
        l_request.id = "1001";
        
        //返済建玉
        l_request.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnitForTest[1];
        WEB3FuturesOptionsCloseMarginContractUnitForTest l_unit = new WEB3FuturesOptionsCloseMarginContractUnitForTest();
        l_unit.id = "1001";
        l_unit.settlePriority = "1";
        l_unit.contractOrderQuantity = "2";
        l_request.closeMarginContractUnits[0] = l_unit;
        
		try 
		{
			l_request.validate();
		}
		catch(Exception l_ex)
		{
			fail();
			log.error(ERROR + l_ex.getMessage(), l_ex);
		}
	}
	
	/**
	 * this.注文単価区分がnullの値であれば例外をスローする。
	 */
	public void testValidateATReserveOrder_case001() {
        final String STR_METHOD_NAME = "testValidateATReserveOrder_case001()";
        log.entering(TEST_START + STR_METHOD_NAME);
		try 
		{
			l_request.validateATReserveOrder();
			fail();
		}
		catch(WEB3BaseException l_ex)
		{
			log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00184);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
		}
		catch(Exception l_ex)
		{
			fail();
			log.error(ERROR + l_ex.getMessage(), l_ex);
		}

	}
	
	/**
	 * this.ＩＤ=null?の場合、例外をスローする。
	 */
	public void testValidateATReserveOrder_case002() {
        final String STR_METHOD_NAME = "testValidateATReserveOrder_case002()";
        log.entering(TEST_START + STR_METHOD_NAME);
		try 
		{
			// 注文単価区分
	        l_request.orderPriceDiv = "0";
	        // 執行条件
	        l_request.execCondType = "1";
	        //注文期限区分
	        l_request.expirationDateType = "1";
	        // 発注条件区分
	        l_request.orderCondType = "0";
	        
			l_request.validateATReserveOrder();
			fail();
		}
		catch(WEB3BaseException l_ex)
		{
			log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00600);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
		}
		catch(Exception l_ex)
		{
			fail();
			log.error(ERROR + l_ex.getMessage(), l_ex);
		}

	}
	
	/**
	 * this.返済建玉=null の場合、例外をスローする。
	 */
	public void testValidateATReserveOrder_case003() {
        final String STR_METHOD_NAME = "testValidateATReserveOrder_case003()";
        log.entering(TEST_START + STR_METHOD_NAME);
		try 
		{
            // 注文単価区分
	        l_request.orderPriceDiv = "0";
	        // 執行条件
	        l_request.execCondType = "1";
	        //注文期限区分
	        l_request.expirationDateType = "1";
	        // 発注条件区分
	        l_request.orderCondType = "0";
	        
	        l_request.id = "2001";
	        
			l_request.validateATReserveOrder();
			fail();
		}
		catch(WEB3BaseException l_ex)
		{
			log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00178);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
		}
		catch(Exception l_ex)
		{
			fail();
			log.error(ERROR + l_ex.getMessage(), l_ex);
		}

	}
	
	/**
	 * this.返済建玉の要素数=0?の場合、例外をスローする。
	 */
	public void testValidateATReserveOrder_case004() {
        final String STR_METHOD_NAME = "testValidateATReserveOrder_case004()";
        log.entering(TEST_START + STR_METHOD_NAME);
		try 
		{
            // 注文単価区分
	        l_request.orderPriceDiv = "0";
	        // 執行条件
	        l_request.execCondType = "1";
	        //注文期限区分
	        l_request.expirationDateType = "1";
	        // 発注条件区分
	        l_request.orderCondType = "0";
	        
	        l_request.id = "2001";
            // 返済建玉
	        l_request.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[0];
			l_request.validateATReserveOrder();
			fail();
		}
		catch(WEB3BaseException l_ex)
		{
			log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00178);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
		}
		catch(Exception l_ex)
		{
			fail();
			log.error(ERROR + l_ex.getMessage(), l_ex);
		}

	}
	
	/**
	 * 　@執行条件≠"無条件"の場合は、「連続注文は執行条件指定不可」の例外をthrowする。
	 */
	public void testValidateATReserveOrder_case005() {
        final String STR_METHOD_NAME = "testValidateATReserveOrder_case005()";
        log.entering(TEST_START + STR_METHOD_NAME);
		try 
		{
            // 注文単価区分
	        l_request.orderPriceDiv = "0";
	        // 執行条件
	        l_request.execCondType = "1";
	        //注文期限区分
	        l_request.expirationDateType = "1";
	        // 発注条件区分
	        l_request.orderCondType = "0";
	        
	        l_request.id = "2001";
	        
	        l_request.execCondType = "3";
            // 返済建玉
	        l_request.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[1];
	        WEB3FuturesOptionsCloseMarginContractUnit l_unit = new WEB3FuturesOptionsCloseMarginContractUnit();
	        l_request.closeMarginContractUnits[0] = l_unit;
			l_request.validateATReserveOrder();
			fail();
		}
		catch(WEB3BaseException l_ex)
		{
			log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_02235);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
		}
		catch(Exception l_ex)
		{
			fail();
			log.error(ERROR + l_ex.getMessage(), l_ex);
		}

	}
	
	/**
	 * 正常數據,正常終了。
	 */
	public void testValidateATReserveOrder_case006() {
        final String STR_METHOD_NAME = "testValidateATReserveOrder_case006()";
        log.entering(TEST_START + STR_METHOD_NAME);
		try 
		{
            // 注文単価区分
	        l_request.orderPriceDiv = "0";
	        // 執行条件
	        l_request.execCondType = "1";
	        //注文期限区分
	        l_request.expirationDateType = "1";
	        // 発注条件区分
	        l_request.orderCondType = "0";
	        
	        l_request.id = "2001";
	        
	        l_request.execCondType = "1";
            // 返済建玉
	        l_request.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[1];
	        WEB3FuturesOptionsCloseMarginContractUnit l_unit = new WEB3FuturesOptionsCloseMarginContractUnit();
	        l_request.closeMarginContractUnits[0] = l_unit;
			l_request.validateATReserveOrder();
		}
		catch(Exception l_ex)
		{
			l_ex.printStackTrace();
			fail();
			log.error(ERROR + l_ex.getMessage(), l_ex);
		}

	}

	private class WEB3FuturesOptionsCloseMarginContractUnitForTest extends WEB3FuturesOptionsCloseMarginContractUnit
	{
	    public void validate() throws WEB3BaseException 
	    {
	    	
	    }
	}
}
@
