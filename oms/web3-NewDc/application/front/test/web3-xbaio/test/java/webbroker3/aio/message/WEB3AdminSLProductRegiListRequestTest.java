head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.22.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminSLProductRegiListRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 担保銘柄登録一覧リクエストTest(WEB3AdminSLProductRegiListRequestTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/14 張騰宇 (中訊) 新規作成 モデル 760
*/

package webbroker3.aio.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminSLProductRegiListRequestTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(
                 WEB3AdminSLProductRegiListRequestTest.class);

    public WEB3AdminSLProductRegiListRequestTest(String arg0)
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
     * Test method for 'webbroker3.aio.message.WEB3AdminSLProductRegiListRequest.validate()'
     */
    //リクエスト.銘柄タイプ != null and リクエスト.銘柄タイプが半角数字以外
    public void testValidate1()
    {
        final String STR_METHOD_NAME = "testValidate1()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminSLProductRegiListRequest l_request = new WEB3AdminSLProductRegiListRequest();
        
        try
        {
            l_request.productType = "asd";
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02916, l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //リクエスト.銘柄コード != null and リクエスト.銘柄コードが半角数字以外
    public void testValidate2()
    {
        final String STR_METHOD_NAME = "testValidate2()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminSLProductRegiListRequest l_request = new WEB3AdminSLProductRegiListRequest();
        
        try
        {
            l_request.productType = "1";
            l_request.productCode = "asd";
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00815, l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //リクエスト.適格区分 != null and リクエスト.適格区分が半角数字以外
    public void testValidate3()
    {
        final String STR_METHOD_NAME = "testValidate3()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminSLProductRegiListRequest l_request = new WEB3AdminSLProductRegiListRequest();
        
        try
        {
            l_request.productType = "1";
            l_request.productCode = "123";
            l_request.qualifiedDiv = "a";
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02925, l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //リクエスト.適格区分 != null andリクエスト.適格区分.length() != 1
    public void testValidate4()
    {
        final String STR_METHOD_NAME = "testValidate4()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminSLProductRegiListRequest l_request = new WEB3AdminSLProductRegiListRequest();
        
        try
        {
            l_request.productType = "1";
            l_request.productCode = "123";
            l_request.qualifiedDiv = "12";
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02931, l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //４）適用期間区分チェック  リクエスト.適用期間区分 != null and  
    //(リクエスト.適用期間区分が半角数字以外) 
    public void testValidate14()
    {
        final String STR_METHOD_NAME = "testValidate14()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminSLProductRegiListRequest l_request = new WEB3AdminSLProductRegiListRequest();
        
        try
        {
            l_request.productType = "1";
            l_request.productCode = "123";
            l_request.qualifiedDiv = "1";
            l_request.targetPeriodDiv = "a";
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02932, l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //４）適用期間区分チェック  リクエスト.適用期間区分 != null and  
    //(リクエスト.適用期間区分.length() != 1)
    public void testValidate15()
    {
        final String STR_METHOD_NAME = "testValidate15()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminSLProductRegiListRequest l_request = new WEB3AdminSLProductRegiListRequest();
        
        try
        {
            l_request.productType = "1";
            l_request.productCode = "123";
            l_request.qualifiedDiv = "1";
            l_request.targetPeriodDiv = "12";
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02933, l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //５） 要求ページ番号チェック    リクエスト.要求ページ番号 = null
    public void testValidate5()
    {
        final String STR_METHOD_NAME = "testValidate5()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminSLProductRegiListRequest l_request = new WEB3AdminSLProductRegiListRequest();
        
        try
        {
            l_request.productType = "1";
            l_request.productCode = "123";
            l_request.qualifiedDiv = "1";
            l_request.targetPeriodDiv = "1";
            l_request.pageIndex = "";
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00089, l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //リクエスト.要求ページ番号が数字以外
    public void testValidate6()
    {
        final String STR_METHOD_NAME = "testValidate6()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminSLProductRegiListRequest l_request = new WEB3AdminSLProductRegiListRequest();
        
        try
        {
            l_request.productType = "1";
            l_request.productCode = "123";
            l_request.qualifiedDiv = "1";
            l_request.targetPeriodDiv = "1";
            l_request.pageIndex = "aa";
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00090, l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //リクエスト.要求ページ番号 <= 0 
    public void testValidate77()
    {
        final String STR_METHOD_NAME = "testValidate77()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminSLProductRegiListRequest l_request = new WEB3AdminSLProductRegiListRequest();
        
        try
        {
            l_request.productType = "1";
            l_request.productCode = "123";
            l_request.qualifiedDiv = "1";
            l_request.targetPeriodDiv = "1";
            l_request.pageIndex = "0";
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00616, l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
//    //リクエスト.要求ページ番号 <= 0 
//    public void testValidate7()
//    {
//        final String STR_METHOD_NAME = "testValidate7()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        
//        WEB3AdminSLProductRegiListRequest l_request = new WEB3AdminSLProductRegiListRequest();
//        
//        try
//        {
//            l_request.productType = "1";
//            l_request.productCode = "123";
//            l_request.qualifiedDiv = "1";
//            l_request.targetPeriodDiv = "1";
//            l_request.pageIndex = "-1";
//            l_request.validate();
//            fail();
//        }
//        catch(WEB3BaseException l_ex)
//        {
//            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00616, l_ex.getErrorInfo());
//            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>test pass !");
//        }
//        catch(Exception l_ex)
//        {
//            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
//            fail();
//        }
//
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
    
    //６）ページ内表示行数チェック     リクエスト.ページ内表示行数 = null
    public void testValidate8()
    {
        final String STR_METHOD_NAME = "testValidate8()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminSLProductRegiListRequest l_request = new WEB3AdminSLProductRegiListRequest();
        
        try
        {
            l_request.productType = "1";
            l_request.productCode = "123";
            l_request.qualifiedDiv = "1";
            l_request.targetPeriodDiv = "1";
            l_request.pageIndex = "1";
            l_request.pageSize = "";
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02224, l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //リクエスト.ページ内表示行数が数字以外
    public void testValidate9()
    {
        final String STR_METHOD_NAME = "testValidate9()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminSLProductRegiListRequest l_request = new WEB3AdminSLProductRegiListRequest();
        
        try
        {
            l_request.productType = "1";
            l_request.productCode = "123";
            l_request.qualifiedDiv = "1";
            l_request.targetPeriodDiv = "1";
            l_request.pageIndex = "1";
            l_request.pageSize = "a";
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00092, l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //リクエスト.ページ内表示行数 <= 0
    public void testValidate111()
    {
        final String STR_METHOD_NAME = "testValidate111()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminSLProductRegiListRequest l_request = new WEB3AdminSLProductRegiListRequest();
        
        try
        {
            l_request.productType = "1";
            l_request.productCode = "123";
            l_request.qualifiedDiv = "1";
            l_request.targetPeriodDiv = "1";
            l_request.pageIndex = "1";
            l_request.pageSize = "0";
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00617, l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
//    //リクエスト.ページ内表示行数 <= 0
//    public void testValidate10()
//    {
//        final String STR_METHOD_NAME = "testValidate10()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        
//        WEB3AdminSLProductRegiListRequest l_request = new WEB3AdminSLProductRegiListRequest();
//        
//        try
//        {
//            l_request.productType = "1";
//            l_request.productCode = "123";
//            l_request.qualifiedDiv = "1";
//            l_request.targetPeriodDiv = "1";
//            l_request.pageIndex = "1";
//            l_request.pageSize = "-1";
//            l_request.validate();
//            fail();
//        }
//        catch(WEB3BaseException l_ex)
//        {
//            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00617, l_ex.getErrorInfo());
//            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>test pass !");
//        }
//        catch(Exception l_ex)
//        {
//            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
//            fail();
//        }
//
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//    
    //７）ソートキーチェック  リクエストデータ.ソートキー = null 
    public void testValidate11()
    {
        final String STR_METHOD_NAME = "testValidate11()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminSLProductRegiListRequest l_request = new WEB3AdminSLProductRegiListRequest();
        
        try
        {
            l_request.productType = "1";
            l_request.productCode = "123";
            l_request.qualifiedDiv = "1";
            l_request.targetPeriodDiv = "1";
            l_request.pageIndex = "1";
            l_request.pageSize = "1";
            WEB3SLSortKey[] l_sortKeys = null;
            l_request.sortKeys = l_sortKeys;
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00231, l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //リクエストデータ.ソートキー.length() = 0 
    public void testValidate12()
    {
        final String STR_METHOD_NAME = "testValidate12()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminSLProductRegiListRequest l_request = new WEB3AdminSLProductRegiListRequest();
        
        try
        {
            l_request.productType = "1";
            l_request.productCode = "123";
            l_request.qualifiedDiv = "1";
            l_request.targetPeriodDiv = "1";
            l_request.pageIndex = "1";
            l_request.pageSize = "1";
            WEB3SLSortKey[] l_sortKeys = new WEB3SLSortKey[0];
            l_request.sortKeys = l_sortKeys;
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00232, l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //正常結束
    public void testValidate13()
    {
        final String STR_METHOD_NAME = "testValidate13()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminSLProductRegiListRequest l_request = new WEB3AdminSLProductRegiListRequest();
        
        try
        {
            l_request.productType = "1";
            l_request.productCode = "123";
            l_request.qualifiedDiv = "1";
            l_request.targetPeriodDiv = "1";
            l_request.pageIndex = "1";
            l_request.pageSize = "1";
            WEB3SLSortKey[] l_sortKeys = new WEB3SLSortKey[1];
            l_sortKeys[0] = new WEB3SLSortKey();
            l_request.sortKeys = l_sortKeys;
            l_request.validate();
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
