head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.48.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3SuccEquitySellInputRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (WEB3SuccEquitySellInputRequestTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/07/07 安陽(中訊) 新規作成
*/
package webbroker3.triggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3SuccEquitySellInputRequestTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SuccEquitySellInputRequestTest.class);

    private WEB3SuccEquitySellInputRequest l_request = null;

    public WEB3SuccEquitySellInputRequestTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        l_request = new WEB3SuccEquitySellInputRequest();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
        l_request = null;
    }

    /*
     * Test method for 'webbroker3.triggerorder.message.WEB3SuccEquitySellInputRequest.validate()'
     */
    
    //１）　@連続注文共通情報チェック
    //　@１−１）　@this.連続注文共通情報==nullの場合、
    //　@　@　@　@　@　@「連続注文共通情報が未指定」の例外をスローする。
    public void testValidate_C0001()
    {
        final String STR_METHOD_NAME = "testValidate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.succCommonInfo = null;
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02251, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //１−２）　@this.連続注文共通情報.validate()をコールする。
    public void testValidate_C0002()
    {
        final String STR_METHOD_NAME = "testValidate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.succCommonInfo = new WEB3SuccCommonInfo();
            l_request.succCommonInfo.parentOrderId = null;
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02258, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
   
    //　@１−３）　@this.連続注文共通情報.連続注文取引区分が以下の値以外の場合、
    //　@　@　@　@　@　@「連続注文取引区分の値が処理対象外」の例外をthrowする。
    public void testValidate_C0003()
    {
        final String STR_METHOD_NAME = "testValidate_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.succCommonInfo = new WEB3SuccCommonInfo();
            l_request.succCommonInfo.parentOrderId = "995";
            l_request.succCommonInfo.succTradingType = "02";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02252, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
 
    //２）　@this.連続注文共通情報.連続注文取引区分=="売付（前提注文）"の場合、
    //　@　@　@以下のチェックを行う。
    //　@２−１） super.市場コード==nullの場合は、
    //　@　@　@　@　@　@「反対取引時は市場コード指定は必須」の例外をthrowする。
    public void testValidate_C0004()
    {
        final String STR_METHOD_NAME = "testValidate_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.succCommonInfo = new WEB3SuccCommonInfo();
            l_request.succCommonInfo.parentOrderId = "995";
            l_request.succCommonInfo.succTradingType = "03";
            l_request.marketCode = null;
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02257, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //３）　@this.連続注文共通情報.連続注文取引区分=="売付（既存残）"の場合のみ、
    //　@　@　@super.validate()をコールする。
    public void testValidate_C0005()
    {
        final String STR_METHOD_NAME = "testValidate_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.succCommonInfo = new WEB3SuccCommonInfo();
            l_request.succCommonInfo.parentOrderId = "995";
            l_request.succCommonInfo.succTradingType = "04";
            l_request.id = null;
            l_request.validate();
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
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //normal case
    public void testValidate_C0006()
    {
        final String STR_METHOD_NAME = "testValidate_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.succCommonInfo = new WEB3SuccCommonInfo();
            l_request.succCommonInfo.parentOrderId = "995";
            l_request.succCommonInfo.succTradingType = "03";
            l_request.marketCode = "1";
            l_request.validate();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //normal case
    public void testValidate_C0007()
    {
        final String STR_METHOD_NAME = "testValidate_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.succCommonInfo = new WEB3SuccCommonInfo();
            l_request.succCommonInfo.parentOrderId = "995";
            l_request.succCommonInfo.succTradingType = "04";
            l_request.id = "101";
            l_request.validate();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
