head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.35.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminTPPaymentRequisitionListRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.tradingpoweradmin.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminTPPaymentRequisitionListRequestTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTPPaymentRequisitionListRequest.class);

    public WEB3AdminTPPaymentRequisitionListRequestTest(String arg0)
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
     * Test method for 'webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionListRequest.validate()'
     */

    //１）顧客属性のチェック 
    //顧客属性がnullの場合 
    //例外をスローする。
    public void testValidate_0001()
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminTPPaymentRequisitionListRequest l_request = new WEB3AdminTPPaymentRequisitionListRequest();
        
        try
        {
            //顧客属性 = null
            l_request.validate();
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_03140);
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //２）請求事由のチェック 　@
    // 請求事由がnullの場合 
    // 例外をスローする。
    public void testValidate_0002()
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminTPPaymentRequisitionListRequest l_request = new WEB3AdminTPPaymentRequisitionListRequest();
        
        try
        {
            //顧客属性 = "0"
            //請求事由 = null
            l_request.customerAttribute = "0";
            l_request.validate();
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_03136);
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    //２）請求事由のチェック
    //立替金/特別立替金が選択されている場合
    //日数が 0 でなければ、例外をスローする。
    public void testValidate_0003()
    {
        final String STR_METHOD_NAME = "validate";
        log.entering(STR_METHOD_NAME);

        WEB3AdminTPPaymentRequisitionListRequest l_request = new WEB3AdminTPPaymentRequisitionListRequest();

        try
        {
            //顧客属性 = "0"
            //請求事由 = "1"
            //日数 = "1"
            l_request.customerAttribute = "0";
            l_request.claimReason = "1";
            l_request.days = "1";
            l_request.validate();
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_03138);
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    //２）請求事由のチェック
    //不足金（当日）が選択されている場合
    //日数が 0 でなければ、例外をスローする。
    public void testValidate_0004()
    {
        final String STR_METHOD_NAME = "validate";
        log.entering(STR_METHOD_NAME);

        WEB3AdminTPPaymentRequisitionListRequest l_request = new WEB3AdminTPPaymentRequisitionListRequest();

        try
        {
            //顧客属性 = "0"
            //請求事由 = "2"
            //日数 = "1"
            l_request.customerAttribute = "0";
            l_request.claimReason = "2";
            l_request.days = "1";
            l_request.validate();
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_03138);
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    //２）請求事由のチェック
    //指定なしが選択されている場合
    //日数が 0 でなければ、例外をスローする。
    public void testValidate_0005()
    {
        final String STR_METHOD_NAME = "validate";
        log.entering(STR_METHOD_NAME);

        WEB3AdminTPPaymentRequisitionListRequest l_request = new WEB3AdminTPPaymentRequisitionListRequest();

        try
        {
            //顧客属性 = "0"
            //請求事由 = "5"
            //日数 = "1"
            l_request.customerAttribute = "0";
            l_request.claimReason = "5";
            l_request.days = "1";
            l_request.validate();
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_03138);
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    //３）部店コードのチェック
    //部店コードがnullの場合
    //例外をスローする。
    public void testValidate_0006()
    {
        final String STR_METHOD_NAME = "validate";
        log.entering(STR_METHOD_NAME);

        WEB3AdminTPPaymentRequisitionListRequest l_request = new WEB3AdminTPPaymentRequisitionListRequest();

        try
        {
            //顧客属性 = "0"
            //請求事由 = "3"
            //日数 = "1"
            //部店コード = null
            l_request.customerAttribute = "0";
            l_request.claimReason = "3";
            l_request.days = "1";
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

    //３）部店コードのチェック
    //部店コード.lengthが3以外の場合
    //例外をスローする。
    public void testValidate_0007()
    {
        final String STR_METHOD_NAME = "validate";
        log.entering(STR_METHOD_NAME);

        WEB3AdminTPPaymentRequisitionListRequest l_request = new WEB3AdminTPPaymentRequisitionListRequest();

        try
        {
            //顧客属性 = "0"
            //請求事由 = "3"
            //日数 = "1"
            //部店コード = "1234"
            l_request.customerAttribute = "0";
            l_request.claimReason = "3";
            l_request.days = "1";
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

    //３）部店コードのチェック
    //部店コードが半角数字以外の場合
    //例外をスローする。
    public void testValidate_0008()
    {
        final String STR_METHOD_NAME = "validate";
        log.entering(STR_METHOD_NAME);

        WEB3AdminTPPaymentRequisitionListRequest l_request = new WEB3AdminTPPaymentRequisitionListRequest();

        try
        {
            //顧客属性 = "0"
            //請求事由 = "3"
            //日数 = "1"
            //部店コード = "１２３"
            l_request.customerAttribute = "0";
            l_request.claimReason = "3";
            l_request.days = "1";
            l_request.branchCode = "１２３";
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

    //４）顧客コードのチェック 
    //顧客コードがnull以外　@and  顧客コード.lengthが6以外 の場合
    //例外をスローする。
    public void testValidate_0009()
    {
        final String STR_METHOD_NAME = "validate";
        log.entering(STR_METHOD_NAME);

        WEB3AdminTPPaymentRequisitionListRequest l_request = new WEB3AdminTPPaymentRequisitionListRequest();

        try
        {
            //顧客属性 = "0"
            //請求事由 = "3"
            //日数 = "1"
            //部店コード = "123"
            //顧客コード = "1234567"
            l_request.customerAttribute = "0";
            l_request.claimReason = "3";
            l_request.days = "1";
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

    //４）顧客コードのチェック 
    //顧客コードがnull以外　@and  顧客コードが半角数字以外 の場合
    //例外をスローする。
    public void testValidate_0010()
    {
        final String STR_METHOD_NAME = "validate";
        log.entering(STR_METHOD_NAME);

        WEB3AdminTPPaymentRequisitionListRequest l_request = new WEB3AdminTPPaymentRequisitionListRequest();

        try
        {
            //顧客属性 = "0"
            //請求事由 = "3"
            //日数 = "1"
            //部店コード = "123"
            //顧客コード = "１２３４５６"
            l_request.customerAttribute = "0";
            l_request.claimReason = "3";
            l_request.days = "1";
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

    //５）扱者コードのチェック 
    //扱者コードがnull以外　@and  扱者コード.lengthが5以外の場合
    //例外をスローする。
    public void testValidate_0011()
    {
        final String STR_METHOD_NAME = "validate";
        log.entering(STR_METHOD_NAME);

        WEB3AdminTPPaymentRequisitionListRequest l_request = new WEB3AdminTPPaymentRequisitionListRequest();

        try
        {
            //顧客属性 = "0"
            //請求事由 = "3"
            //日数 = "1"
            //部店コード = "123"
            //顧客コード = "123456"
            //扱者コード = "123456"
            l_request.customerAttribute = "0";
            l_request.claimReason = "3";
            l_request.days = "1";
            l_request.branchCode = "123";
            l_request.accountCode = "123456";
            l_request.traderCode = "123456";
            l_request.validate();
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_01912);
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    //６）要求ページ番号のチェック
    //要求ページ番号がnullの場合
    //例外をスローする。
    public void testValidate_0012()
    {
        final String STR_METHOD_NAME = "validate";
        log.entering(STR_METHOD_NAME);

        WEB3AdminTPPaymentRequisitionListRequest l_request = new WEB3AdminTPPaymentRequisitionListRequest();

        try
        {
            //顧客属性 = "0"
            //請求事由 = "3"
            //日数 = "1"
            //部店コード = "123"
            //顧客コード = "123456"
            //扱者コード = "12345"
            //要求ページ番号 = null
            l_request.customerAttribute = "0";
            l_request.claimReason = "3";
            l_request.days = "1";
            l_request.branchCode = "123";
            l_request.accountCode = "123456";
            l_request.traderCode = "12345";
            l_request.validate();
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00786);
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    //６）要求ページ番号のチェック
    //要求ページ番号に半角数字以外の文字がある場合 
    //例外をスローする。
    public void testValidate_0013()
    {
        final String STR_METHOD_NAME = "validate";
        log.entering(STR_METHOD_NAME);

        WEB3AdminTPPaymentRequisitionListRequest l_request = new WEB3AdminTPPaymentRequisitionListRequest();

        try
        {
            //顧客属性 = "0"
            //請求事由 = "3"
            //日数 = "1"
            //部店コード = "123"
            //顧客コード = "123456"
            //扱者コード = "12345"
            //要求ページ番号 = "a"
            l_request.customerAttribute = "0";
            l_request.claimReason = "3";
            l_request.days = "1";
            l_request.branchCode = "123";
            l_request.accountCode = "123456";
            l_request.traderCode = "12345";
            l_request.pageIndex = "a";
            l_request.validate();
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00786);
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    //７）ページ内表示行数のチェック
    //ページ内表示行数がnullの場合
    //例外をスローする。
    public void testValidate_0014()
    {
        final String STR_METHOD_NAME = "validate";
        log.entering(STR_METHOD_NAME);

        WEB3AdminTPPaymentRequisitionListRequest l_request = new WEB3AdminTPPaymentRequisitionListRequest();

        try
        {
            //顧客属性 = "0"
            //請求事由 = "3"
            //日数 = "1"
            //部店コード = "123"
            //顧客コード = "123456"
            //扱者コード = "12345"
            //要求ページ番号 =  "1"
            //ページ内表示行数 = null
            l_request.customerAttribute = "0";
            l_request.claimReason = "3";
            l_request.days = "1";
            l_request.branchCode = "123";
            l_request.accountCode = "123456";
            l_request.traderCode = "12345";
            l_request.pageIndex = "1";
            l_request.validate();
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00091);
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    //７）ページ内表示行数のチェック
    //ページ内表示行数に半角数字以外の文字がある場合
    //例外をスローする。
    public void testValidate_0015()
    {
        final String STR_METHOD_NAME = "validate";
        log.entering(STR_METHOD_NAME);

        WEB3AdminTPPaymentRequisitionListRequest l_request = new WEB3AdminTPPaymentRequisitionListRequest();

        try
        {
            //顧客属性 = "0"
            //請求事由 = "3"
            //日数 = "1"
            //部店コード = "123"
            //顧客コード = "123456"
            //扱者コード = "12345"
            //要求ページ番号 =  "1"
            //ページ内表示行数 = "３"
            l_request.customerAttribute = "0";
            l_request.claimReason = "3";
            l_request.days = "1";
            l_request.branchCode = "123";
            l_request.accountCode = "123456";
            l_request.traderCode = "12345";
            l_request.pageIndex = "1";
            l_request.pageSize = "３";
            l_request.validate();
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00091);
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidate_0016()
    {
        final String STR_METHOD_NAME = "validate";
        log.entering(STR_METHOD_NAME);

        WEB3AdminTPPaymentRequisitionListRequest l_request = new WEB3AdminTPPaymentRequisitionListRequest();

        try
        {
            //顧客属性 = "0"
            //請求事由 = "3"
            //日数 = "1"
            //部店コード = "123"
            //顧客コード = null
            //扱者コード = null
            //要求ページ番号 =  "1"
            //ページ内表示行数 = "1"
            l_request.customerAttribute = "0";
            l_request.claimReason = "3";
            l_request.days = "1";
            l_request.branchCode = "123";
            l_request.pageIndex = "1";
            l_request.pageSize = "1";
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
