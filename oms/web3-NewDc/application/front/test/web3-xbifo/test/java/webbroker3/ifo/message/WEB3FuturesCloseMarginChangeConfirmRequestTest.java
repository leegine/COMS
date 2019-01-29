head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.24.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FuturesCloseMarginChangeConfirmRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部 
File Name        : WEB3FuturesCloseMarginChangeConfirmRequestTest.java
Author Name      : Daiwa Institute of Research  
Revesion History : 2008/03/31 楊夫志 (中訊) 新規作成  
*/
package webbroker3.ifo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3FuturesCloseMarginChangeConfirmRequestTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3FuturesCloseMarginChangeConfirmRequestTest.class);

    private WEB3FuturesCloseMarginChangeConfirmRequest futuresCloseMarginChangeConfirmRequest = null;

    public WEB3FuturesCloseMarginChangeConfirmRequestTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.futuresCloseMarginChangeConfirmRequest = new WEB3FuturesCloseMarginChangeConfirmRequest();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    //１）　@スーパークラスのvalidate チェック
    public void testValidateAtReverseOrder_C0001()
    {
        final String STR_METHOD_NAME = "testValidateAtReverseOrder_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            futuresCloseMarginChangeConfirmRequest.orderPriceDiv = null;
            futuresCloseMarginChangeConfirmRequest.validateAtReverseOrder();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00184, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //２）　@ＩＤチェック
    //this.ＩＤ=null の場合、例外をスローする。
    public void testValidateAtReverseOrder_C0002()
    {
        final String STR_METHOD_NAME = "testValidateAtReverseOrder_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate()
            futuresCloseMarginChangeConfirmRequest.orderPriceDiv = "1";
            futuresCloseMarginChangeConfirmRequest.limitPrice = "1000";
            futuresCloseMarginChangeConfirmRequest.execCondType = "1";
            futuresCloseMarginChangeConfirmRequest.expirationDateType = "1";
            futuresCloseMarginChangeConfirmRequest.orderCondType = "0";
            //this.ID＝null
            futuresCloseMarginChangeConfirmRequest.id = null;
            futuresCloseMarginChangeConfirmRequest.validateAtReverseOrder();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00080, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //３）　@返済建玉チェック
    //３－１）this.返済建玉=null の場合、例外をスローする。
    public void testValidateAtReverseOrder_C0003()
    {
        final String STR_METHOD_NAME = "testValidateAtReverseOrder_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate()
            futuresCloseMarginChangeConfirmRequest.orderPriceDiv = "1";
            futuresCloseMarginChangeConfirmRequest.limitPrice = "1000";
            futuresCloseMarginChangeConfirmRequest.execCondType = "1";
            futuresCloseMarginChangeConfirmRequest.expirationDateType = "1";
            futuresCloseMarginChangeConfirmRequest.orderCondType = "0";
            //this.ID!＝null
            futuresCloseMarginChangeConfirmRequest.id = "0001";
            //this.返済建玉=null
            futuresCloseMarginChangeConfirmRequest.closeMarginContractUnits = null;
            futuresCloseMarginChangeConfirmRequest.validateAtReverseOrder();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00178, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //３－２）this.返済建玉の要素数=0 の場合、例外をスローする
    public void testValidateAtReverseOrder_C0004()
    {
        final String STR_METHOD_NAME = "testValidateAtReverseOrder_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate()
            futuresCloseMarginChangeConfirmRequest.orderPriceDiv = "1";
            futuresCloseMarginChangeConfirmRequest.limitPrice = "1000";
            futuresCloseMarginChangeConfirmRequest.execCondType = "1";
            futuresCloseMarginChangeConfirmRequest.expirationDateType = "1";
            futuresCloseMarginChangeConfirmRequest.orderCondType = "0";
            //this.ID!＝null
            futuresCloseMarginChangeConfirmRequest.id = "0001";
            //this.返済建玉!=null && this.返済建玉の要素数=0
            futuresCloseMarginChangeConfirmRequest.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[]{};
            futuresCloseMarginChangeConfirmRequest.validateAtReverseOrder();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00178, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //４）　@連続注文・注文条件チェック
    //スーパークラスのvalidate連続注文メソッドをコールする
    public void testValidateAtReverseOrder_C0005()
    {
        final String STR_METHOD_NAME = "testValidateAtReverseOrder_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate()
            futuresCloseMarginChangeConfirmRequest.orderPriceDiv = "1";
            futuresCloseMarginChangeConfirmRequest.limitPrice = "1000";
            futuresCloseMarginChangeConfirmRequest.execCondType = "3";
            futuresCloseMarginChangeConfirmRequest.expirationDateType = "1";
            futuresCloseMarginChangeConfirmRequest.orderCondType = "0";
            //this.ID!＝null
            futuresCloseMarginChangeConfirmRequest.id = "0001";
            //this.返済建玉!=null && this.返済建玉の要素数!=0 
            futuresCloseMarginChangeConfirmRequest.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit(),
                new WEB3FuturesOptionsCloseMarginContractUnit()};
            futuresCloseMarginChangeConfirmRequest.validateAtReverseOrder();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02235, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //normal case
    public void testValidateAtReverseOrder_C0006()
    {
        final String STR_METHOD_NAME = "testValidateAtReverseOrder_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate()
            futuresCloseMarginChangeConfirmRequest.orderPriceDiv = "1";
            futuresCloseMarginChangeConfirmRequest.limitPrice = "1000";
            futuresCloseMarginChangeConfirmRequest.execCondType = "1";
            futuresCloseMarginChangeConfirmRequest.expirationDateType = "1";
            futuresCloseMarginChangeConfirmRequest.orderCondType = "0";
            //this.ID!＝null
            futuresCloseMarginChangeConfirmRequest.id = "0001";
            //this.返済建玉!=null && this.返済建玉の要素数!=0 
            futuresCloseMarginChangeConfirmRequest.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit(),
                new WEB3FuturesOptionsCloseMarginContractUnit()};
            futuresCloseMarginChangeConfirmRequest.validateAtReverseOrder();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
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
