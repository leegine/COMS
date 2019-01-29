head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.22.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminSLRestraintMoneyListRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3AdminSLRestraintMoneyListRequestTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/20 金傑（中訊）新規作成
*/
package webbroker3.aio.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminSLRestraintMoneyListRequestTest extends TestBaseForMock
{

    private WEB3AdminSLRestraintMoneyListRequest l_request = null;

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(
             WEB3AdminSLRestraintMoneyListRequestTest.class);

    public WEB3AdminSLRestraintMoneyListRequestTest(String name)
    {
        super(name);
    }
    
    protected void setUp() throws Exception
    {
        super.setUp();
        this.l_request = new WEB3AdminSLRestraintMoneyListRequest();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
        this.l_request = null;
    }
    
    /**
     * 部店コードのサイズが不正です。
     * 
     * 抛出異常: BUSINESS_ERROR_00834
     *
     */
    public void testValidate_C0001()
    {
        String STR_METHOD_NAME = "testValidate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.l_request.branchCode = "25";
            this.l_request.validate();
            fail(); 
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00834,l_ex.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
            
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail(); 
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 部店コードが数値以外の値です。
     * 
     * 抛出異常: BUSINESS_ERROR_01729
     *
     */
    public void testValidate_C0002()
    {
        String STR_METHOD_NAME = "testValidate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.l_request.branchCode = "25F";
            this.l_request.validate();
            fail(); 
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01729,l_ex.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
            
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail(); 
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 顧客コードのサイズが不正です。
     * 
     * 抛出異常: BUSINESS_ERROR_00836
     *
     */
    public void testValidate_C0003()
    {
        String STR_METHOD_NAME = "testValidate_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.l_request.accountCode = "12305478";
            this.l_request.validate();
            fail(); 
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00836,l_ex.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
            
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail(); 
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 顧客コードの値が数字以外の値です。
     * 
     * 抛出異常: BUSINESS_ERROR_01043
     *
     */
    public void testValidate_C0004()
    {
        String STR_METHOD_NAME = "testValidate_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.l_request.branchCode = "381";
            this.l_request.accountCode = "12345J";
            this.l_request.validate();
            fail(); 
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01043,l_ex.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
            
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail(); 
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 出金停止区分のサイズが不正です。
     * 
     * 抛出異常: BUSINESS_ERROR_02918
     *
     */
    public void testValidate_C0005()
    {
        String STR_METHOD_NAME = "testValidate_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.l_request.branchCode = "381";
            this.l_request.cashOutStopDiv = "02";
            this.l_request.validate();
            fail(); 
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02918,l_ex.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
            
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail(); 
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 出金停止区分が半角数字以外の値です。
     * 
     * 抛出異常: BUSINESS_ERROR_02918
     *
     */
    public void testValidate_C0006()
    {
        String STR_METHOD_NAME = "testValidate_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.l_request.branchCode = "381";
            this.l_request.accountCode = "123456";
            this.l_request.cashOutStopDiv = "F";
            this.l_request.validate();
            fail(); 
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02919,l_ex.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
            
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail(); 
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 要求ページ番号の入力が不正です。
     * 
     * 抛出異常: BUSINESS_ERROR_00089
     *
     */
    public void testValidate_C0007()
    {
        String STR_METHOD_NAME = "testValidate_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.l_request.branchCode = "381";
            this.l_request.accountCode = "123456";
            this.l_request.validate();
            fail(); 
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00089,l_ex.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
            
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail(); 
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 要求ページ番号が数字以外の値です。
     * 
     * 抛出異常: BUSINESS_ERROR_00090
     *
     */
    public void testValidate_C0008()
    {
        String STR_METHOD_NAME = "testValidate_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.l_request.pageIndex = "AF";
            this.l_request.validate();
            fail(); 
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00090,l_ex.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
            
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail(); 
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 要求ページ番号<=0。
     * 
     * 抛出異常: BUSINESS_ERROR_00616
     *
     */
    public void testValidate_C0009()
    {
        String STR_METHOD_NAME = "testValidate_C0009()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.l_request.pageIndex = "0";
            this.l_request.validate();
            fail(); 
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00616,l_ex.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
            
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail(); 
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * ページ内表示行数の入力が不正です。
     * 
     * 抛出異常: BUSINESS_ERROR_00091
     *
     */
    public void testValidate_C0010()
    {
        String STR_METHOD_NAME = "testValidate_C0010()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.l_request.branchCode = "381";
            this.l_request.accountCode = "123456";
            this.l_request.cashOutStopDiv = "0";
            this.l_request.pageIndex = "2";
            this.l_request.validate();
            fail(); 
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00091,l_ex.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
            
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail(); 
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * ページ内表示行数が数字以外の値です。
     * 
     * 抛出異常: BUSINESS_ERROR_00092
     *
     */
    public void testValidate_C0011()
    {
        String STR_METHOD_NAME = "testValidate_C0011()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.l_request.branchCode = "381";
            this.l_request.accountCode = "123456";
            this.l_request.cashOutStopDiv = "0";
            this.l_request.pageIndex = "2";
            this.l_request.pageSize = "C";
            this.l_request.validate();
            fail(); 
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00092,l_ex.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
            
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail(); 
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * ページ内表示行数の値が0以下です。
     * 
     * 抛出異常: BUSINESS_ERROR_00092
     *
     */
    public void testValidate_C0012()
    {
        String STR_METHOD_NAME = "testValidate_C0012()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.l_request.branchCode = "381";
            this.l_request.accountCode = "123456";
            this.l_request.cashOutStopDiv = "0";
            this.l_request.pageIndex = "2";
            this.l_request.pageSize = "-2";
            this.l_request.validate();
            fail(); 
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00092,l_ex.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
            
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail(); 
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * ソートキーが未指定です。
     * 
     * 抛出異常: BUSINESS_ERROR_00231
     *
     */
    public void testValidate_C0013()
    {
        String STR_METHOD_NAME = "testValidate_C0013()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.l_request.branchCode = "381";
            this.l_request.accountCode = "123456";
            this.l_request.cashOutStopDiv = "0";
            this.l_request.pageIndex = "2";
            this.l_request.pageSize = "10";
            this.l_request.validate();
            fail(); 
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00231,l_ex.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
            
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail(); 
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * ソートキーの要素数が０です。
     * 
     * 抛出異常: BUSINESS_ERROR_00232
     *
     */
    public void testValidate_C0014()
    {
        String STR_METHOD_NAME = "testValidate_C0014()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.l_request.branchCode = "381";
            this.l_request.accountCode = "123456";
            this.l_request.cashOutStopDiv = "0";
            this.l_request.pageIndex = "2";
            this.l_request.pageSize = "10";
            this.l_request.sortKeys = new WEB3SLSortKey[0];
            this.l_request.validate();
            fail(); 
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00232,l_ex.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
            
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail(); 
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * ソートキーの要素数が1です。
     * ソートキーのキー項目が未指定です。
     * 
     * 抛出異常: BUSINESS_ERROR_00085
     *
     */
    public void testValidate_C0015()
    {
        String STR_METHOD_NAME = "testValidate_C0015()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.l_request.branchCode = "381";
            this.l_request.accountCode = "123456";
            this.l_request.cashOutStopDiv = "0";
            this.l_request.pageIndex = "2";
            this.l_request.pageSize = "10";
            this.l_request.sortKeys = new WEB3SLSortKey[1];
            this.l_request.sortKeys[0] = new WEB3SLSortKey();
            this.l_request.validate();
            fail(); 
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00085,l_ex.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
            
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail(); 
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * ソートキーの要素数が1です。
     * 昇順／降順が未指定です。
     * 
     * 抛出異常: BUSINESS_ERROR_00087
     *
     */
    public void testValidate_C0016()
    {
        String STR_METHOD_NAME = "testValidate_C0016()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.l_request.branchCode = "381";
            this.l_request.accountCode = "123456";
            this.l_request.cashOutStopDiv = "0";
            this.l_request.pageIndex = "2";
            this.l_request.pageSize = "10";
            this.l_request.sortKeys = new WEB3SLSortKey[1];
            this.l_request.sortKeys[0] = new WEB3SLSortKey();
            this.l_request.sortKeys[0].keyItem = "branch_code";
            this.l_request.validate();
            fail(); 
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00087,l_ex.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
            
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail(); 
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 昇順／降順が”A：昇順”、”D：降順”以外の値です。
     * 
     * 抛出異常: BUSINESS_ERROR_00088
     *
     */
    public void testValidate_C0017()
    {
        String STR_METHOD_NAME = "testValidate_C0017()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.l_request.branchCode = "381";
            this.l_request.accountCode = "123456";
            this.l_request.cashOutStopDiv = "0";
            this.l_request.pageIndex = "2";
            this.l_request.pageSize = "10";
            this.l_request.sortKeys = new WEB3SLSortKey[1];
            this.l_request.sortKeys[0] = new WEB3SLSortKey();
            this.l_request.sortKeys[0].keyItem = "branch_code";
            this.l_request.sortKeys[0].ascDesc = "F";
            this.l_request.validate();
            fail(); 
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00088,l_ex.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
            
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail(); 
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * ソートキーの要素数が2です。
     * 正常結束
     *
     */
    public void testValidate_C0018()
    {
        String STR_METHOD_NAME = "testValidate_C0018()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.l_request.branchCode = "381";
            this.l_request.accountCode = "123456";
            this.l_request.cashOutStopDiv = "0";
            this.l_request.pageIndex = "2";
            this.l_request.pageSize = "10";
            this.l_request.sortKeys = new WEB3SLSortKey[2];
            this.l_request.sortKeys[0] = new WEB3SLSortKey();
            this.l_request.sortKeys[0].keyItem = "branch_code";
            this.l_request.sortKeys[0].ascDesc = "A";
            
            this.l_request.sortKeys[1] = new WEB3SLSortKey();
            this.l_request.sortKeys[1].keyItem = "account_code";
            this.l_request.sortKeys[1].ascDesc = "A";
            this.l_request.validate();

        }
        catch(WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
            
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail(); 
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * ページ内表示行数の値が0です。
     * 
     * 抛出異常: BUSINESS_ERROR_00617
     *
     */
    public void testValidate_C0019()
    {
        String STR_METHOD_NAME = "testValidate_C0019()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.l_request.branchCode = "381";
            this.l_request.accountCode = "123456";
            this.l_request.cashOutStopDiv = "0";
            this.l_request.pageIndex = "2";
            this.l_request.pageSize = "0";
            this.l_request.validate();
            fail(); 
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00617,l_ex.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
            
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail(); 
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

}
@
