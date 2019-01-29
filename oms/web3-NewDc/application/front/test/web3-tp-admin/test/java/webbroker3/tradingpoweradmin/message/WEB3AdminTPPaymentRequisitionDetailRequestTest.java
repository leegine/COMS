head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.35.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminTPPaymentRequisitionDetailRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入金請求顧客検索ダウンロードリクエストTest(WEB3AdminTPPaymentRequisitionDownLoadRequestTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/17 姜丹 (中訊) 新規作成 
*/
package webbroker3.tradingpoweradmin.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;


public class WEB3AdminTPPaymentRequisitionDetailRequestTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTPPaymentRequisitionDownLoadRequestTest.class);

    public WEB3AdminTPPaymentRequisitionDetailRequestTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /*
     * Test method for 'webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionDetailRequest.validate()'
     */

    //１）部店コードのチェック
    //部店コードがnullの場合
    //例外をスローする。
    public void testValidate_0001()
    {
        final String STR_METHOD_NAME = "validate";
        log.entering(STR_METHOD_NAME);

        WEB3AdminTPPaymentRequisitionDetailRequest l_request = new WEB3AdminTPPaymentRequisitionDetailRequest();

        try
        {
            //部店コード = null
            l_request.validate();
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00779);
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    //１）部店コードのチェック
    //部店コード.lengthが3以外の場合
    //例外をスローする。
    public void testValidate_0002()
    {
        final String STR_METHOD_NAME = "validate";
        log.entering(STR_METHOD_NAME);

        WEB3AdminTPPaymentRequisitionDetailRequest l_request = new WEB3AdminTPPaymentRequisitionDetailRequest();

        try
        {
            //部店コード = "1234"
            l_request.branchCode = "1234";
            l_request.validate();
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00779);
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    //１）部店コードのチェック
    //部店コードが半角数字以外の場合
    //例外をスローする。
    public void testValidate_0003()
    {
        final String STR_METHOD_NAME = "validate";
        log.entering(STR_METHOD_NAME);

        WEB3AdminTPPaymentRequisitionDetailRequest l_request = new WEB3AdminTPPaymentRequisitionDetailRequest();

        try
        {
            //部店コード = "a12"
            l_request.branchCode = "a12";
            l_request.validate();
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00779);
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    //２）顧客コードのチェック 
    //顧客コードがnullの場合
    //例外をスローする。
    public void testValidate_0004()
    {
        final String STR_METHOD_NAME = "validate";
        log.entering(STR_METHOD_NAME);

        WEB3AdminTPPaymentRequisitionDetailRequest l_request = new WEB3AdminTPPaymentRequisitionDetailRequest();

        try
        {
            //部店コード = "123"
            //顧客コード = null
            l_request.branchCode = "123";
            l_request.validate();
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00780);
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    //２）顧客コードのチェック 
    //顧客コード.lengthが6以外の場合
    //例外をスローする。
    public void testValidate_0005()
    {
        final String STR_METHOD_NAME = "validate";
        log.entering(STR_METHOD_NAME);

        WEB3AdminTPPaymentRequisitionDetailRequest l_request = new WEB3AdminTPPaymentRequisitionDetailRequest();

        try
        {
            //部店コード = "123"
            //顧客コード = "1234567"
            l_request.branchCode = "123";
            l_request.accountCode = "1234567";
            l_request.validate();
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00780);
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    //２）顧客コードのチェック 
    //顧客コードが半角数字以外の場合
    //例外をスローする。
    public void testValidate_0006()
    {
        final String STR_METHOD_NAME = "validate";
        log.entering(STR_METHOD_NAME);

        WEB3AdminTPPaymentRequisitionDetailRequest l_request = new WEB3AdminTPPaymentRequisitionDetailRequest();

        try
        {
            //部店コード = "123"
            //顧客コード = "１２３４５６"
            l_request.branchCode = "123";
            l_request.accountCode = "１２３４５６";
            l_request.validate();
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00780);
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidate_0007()
    {
        final String STR_METHOD_NAME = "validate";
        log.entering(STR_METHOD_NAME);

        WEB3AdminTPPaymentRequisitionDetailRequest l_request = new WEB3AdminTPPaymentRequisitionDetailRequest();

        try
        {
            //部店コード = "123"
            //顧客コード = "123456"
            l_request.branchCode = "123";
            l_request.accountCode = "123456";
            l_request.validate();
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

}
@
