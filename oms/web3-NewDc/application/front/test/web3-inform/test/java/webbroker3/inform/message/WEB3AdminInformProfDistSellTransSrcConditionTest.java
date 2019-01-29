head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.30.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminInformProfDistSellTransSrcConditionTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 利金・分配金・売却代金振込先検索条件テストクラス(WEB3AdminInformProfDistSellTransSrcConditionTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/05/31 李木子 (中訊) 新規作成
*/

package webbroker3.inform.message;


import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (利金・分配金・売却代金振込先検索条件テストクラス)<BR>
 * 利金・分配金・売却代金振込先検索条件テストクラス<BR>
 * @@author 李木子
 * @@version 1.0
 */
public class WEB3AdminInformProfDistSellTransSrcConditionTest extends TestBaseForMock {
	
    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
        		WEB3AdminInformProfDistSellTransSrcConditionTest.class);
    
    public static StringBuffer expectMethodParam = new StringBuffer(); 

    public WEB3AdminInformProfDistSellTransSrcConditionTest(String arg0)
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

    public void test_validate_0001()
    {
    	WEB3AdminInformProfDistSellTransSrcCondition l_WEB3AdminInformProfDistSellTransSrcCondition
    	    = new WEB3AdminInformProfDistSellTransSrcCondition();
    	
    	l_WEB3AdminInformProfDistSellTransSrcCondition.branchCode = "aaaa";
    	
    	try 
    	{
    		l_WEB3AdminInformProfDistSellTransSrcCondition.validate();
    		fail();
    	}
    	catch (WEB3BusinessLayerException l_ex)
    	{
    		assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00779, l_ex.getErrorInfo());
    	}
    	catch (Exception e)
    	{
    		fail();
    	}
    }

    public void test_validate_0002()
    {
    	WEB3AdminInformProfDistSellTransSrcCondition l_WEB3AdminInformProfDistSellTransSrcCondition
    	    = new WEB3AdminInformProfDistSellTransSrcCondition();
    	
    	l_WEB3AdminInformProfDistSellTransSrcCondition.branchCode = "２２２";
    	
    	try 
    	{
    		l_WEB3AdminInformProfDistSellTransSrcCondition.validate();
    		fail();
    	}
    	catch (WEB3BusinessLayerException l_ex)
    	{
    		assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00779, l_ex.getErrorInfo());
    	}
    	catch (Exception e)
    	{
    		fail();
    	}
    }

    public void test_validate_0003()
    {
    	WEB3AdminInformProfDistSellTransSrcCondition l_WEB3AdminInformProfDistSellTransSrcCondition
    	    = new WEB3AdminInformProfDistSellTransSrcCondition();
    	
    	l_WEB3AdminInformProfDistSellTransSrcCondition.branchCode = null;
    	l_WEB3AdminInformProfDistSellTransSrcCondition.traderCode = "aaa";
    	
    	try 
    	{
    		l_WEB3AdminInformProfDistSellTransSrcCondition.validate();
    		fail();
    	}
    	catch (WEB3BusinessLayerException l_ex)
    	{
    		assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02782, l_ex.getErrorInfo());
    	}
    	catch (Exception e)
    	{
    		fail();
    	}
    }

    public void test_validate_0004()
    {
    	WEB3AdminInformProfDistSellTransSrcCondition l_WEB3AdminInformProfDistSellTransSrcCondition
    	    = new WEB3AdminInformProfDistSellTransSrcCondition();
    	
    	l_WEB3AdminInformProfDistSellTransSrcCondition.branchCode = "1111";
    	l_WEB3AdminInformProfDistSellTransSrcCondition.traderCode = "２２２aaa";
    	
    	try 
    	{
    		l_WEB3AdminInformProfDistSellTransSrcCondition.validate();
    		fail();
    	}
    	catch (WEB3BusinessLayerException l_ex)
    	{
    		assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02782, l_ex.getErrorInfo());
    	}
    	catch (Exception e)
    	{
    		fail();
    	}
    }

    public void test_validate_0005()
    {
    	WEB3AdminInformProfDistSellTransSrcCondition l_WEB3AdminInformProfDistSellTransSrcCondition
    	    = new WEB3AdminInformProfDistSellTransSrcCondition();
    	
    	l_WEB3AdminInformProfDistSellTransSrcCondition.branchCode = "1111";
    	l_WEB3AdminInformProfDistSellTransSrcCondition.traderCode = null;
    	l_WEB3AdminInformProfDistSellTransSrcCondition.accountCode = "aaa";
    	
    	try 
    	{
    		l_WEB3AdminInformProfDistSellTransSrcCondition.validate();
    		fail();
    	}
    	catch (WEB3BusinessLayerException l_ex)
    	{
    		assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00780, l_ex.getErrorInfo());
    	}
    	catch (Exception e)
    	{
    		fail();
    	}
    }

    public void test_validate_0006()
    {
    	WEB3AdminInformProfDistSellTransSrcCondition l_WEB3AdminInformProfDistSellTransSrcCondition
    	    = new WEB3AdminInformProfDistSellTransSrcCondition();
    	
    	l_WEB3AdminInformProfDistSellTransSrcCondition.branchCode = "1111";
    	l_WEB3AdminInformProfDistSellTransSrcCondition.traderCode = "1111";
    	l_WEB3AdminInformProfDistSellTransSrcCondition.accountCode = "２２２";
    	
    	try 
    	{
    		l_WEB3AdminInformProfDistSellTransSrcCondition.validate();
    		fail();
    	}
    	catch (WEB3BusinessLayerException l_ex)
    	{
    		assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00780, l_ex.getErrorInfo());
    	}
    	catch (Exception e)
    	{
    		fail();
    	}
    }

    public void test_validate_0007()
    {
    	WEB3AdminInformProfDistSellTransSrcCondition l_WEB3AdminInformProfDistSellTransSrcCondition
    	    = new WEB3AdminInformProfDistSellTransSrcCondition();
    	
    	l_WEB3AdminInformProfDistSellTransSrcCondition.branchCode = "1111";
    	l_WEB3AdminInformProfDistSellTransSrcCondition.traderCode = "1111";
    	l_WEB3AdminInformProfDistSellTransSrcCondition.accountCode = null;
    	l_WEB3AdminInformProfDistSellTransSrcCondition.productCode = "aaaa";
    	
    	try 
    	{
    		l_WEB3AdminInformProfDistSellTransSrcCondition.validate();
    		fail();
    	}
    	catch (WEB3BusinessLayerException l_ex)
    	{
    		assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01067, l_ex.getErrorInfo());
    	}
    	catch (Exception e)
    	{
    		fail();
    	}
    }

    public void test_validate_0008()
    {
    	WEB3AdminInformProfDistSellTransSrcCondition l_WEB3AdminInformProfDistSellTransSrcCondition
    	    = new WEB3AdminInformProfDistSellTransSrcCondition();
    	
    	l_WEB3AdminInformProfDistSellTransSrcCondition.branchCode = "1111";
    	l_WEB3AdminInformProfDistSellTransSrcCondition.traderCode = "1111";
    	l_WEB3AdminInformProfDistSellTransSrcCondition.accountCode = "1111";
    	l_WEB3AdminInformProfDistSellTransSrcCondition.productCode = "２２２";
    	
    	try 
    	{
    		l_WEB3AdminInformProfDistSellTransSrcCondition.validate();
    		fail();
    	}
    	catch (WEB3BusinessLayerException l_ex)
    	{
    		assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01067, l_ex.getErrorInfo());
    	}
    	catch (Exception e)
    	{
    		fail();
    	}
    }

    public void test_validate_0009()
    {
    	WEB3AdminInformProfDistSellTransSrcCondition l_WEB3AdminInformProfDistSellTransSrcCondition
    	    = new WEB3AdminInformProfDistSellTransSrcCondition();
    	
    	l_WEB3AdminInformProfDistSellTransSrcCondition.branchCode = "1111";
    	l_WEB3AdminInformProfDistSellTransSrcCondition.traderCode = "1111";
    	l_WEB3AdminInformProfDistSellTransSrcCondition.accountCode = "1111";
    	l_WEB3AdminInformProfDistSellTransSrcCondition.productCode = null;
    	l_WEB3AdminInformProfDistSellTransSrcCondition.registDateFrom = WEB3DateUtility.getDate("20070530", "yyyyMMdd");
    	l_WEB3AdminInformProfDistSellTransSrcCondition.registDateTo = WEB3DateUtility.getDate("20070520", "yyyyMMdd");
    	
    	try 
    	{
    		l_WEB3AdminInformProfDistSellTransSrcCondition.validate();
    		fail();
    	}
    	catch (WEB3BusinessLayerException l_ex)
    	{
    		assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02222, l_ex.getErrorInfo());
    	}
    	catch (Exception e)
    	{
    		fail();
    	}
    }

    public void test_validate_0010()
    {
    	WEB3AdminInformProfDistSellTransSrcCondition l_WEB3AdminInformProfDistSellTransSrcCondition
    	    = new WEB3AdminInformProfDistSellTransSrcCondition();
    	
    	l_WEB3AdminInformProfDistSellTransSrcCondition.branchCode = "1111";
    	l_WEB3AdminInformProfDistSellTransSrcCondition.traderCode = "1111";
    	l_WEB3AdminInformProfDistSellTransSrcCondition.accountCode = "1111";
    	l_WEB3AdminInformProfDistSellTransSrcCondition.productCode = "1111";
    	l_WEB3AdminInformProfDistSellTransSrcCondition.registDateFrom = null;
    	l_WEB3AdminInformProfDistSellTransSrcCondition.registDateTo = WEB3DateUtility.getDate("20070520", "yyyyMMdd");
    	
    	try 
    	{
    		l_WEB3AdminInformProfDistSellTransSrcCondition.validate();
    	}
    	catch (Exception e)
    	{
    		fail();
    	}
    }

    public void test_validate_0011()
    {
    	WEB3AdminInformProfDistSellTransSrcCondition l_WEB3AdminInformProfDistSellTransSrcCondition
    	    = new WEB3AdminInformProfDistSellTransSrcCondition();
    	
    	l_WEB3AdminInformProfDistSellTransSrcCondition.branchCode = "1111";
    	l_WEB3AdminInformProfDistSellTransSrcCondition.traderCode = "1111";
    	l_WEB3AdminInformProfDistSellTransSrcCondition.accountCode = "1111";
    	l_WEB3AdminInformProfDistSellTransSrcCondition.productCode = "1111";
    	l_WEB3AdminInformProfDistSellTransSrcCondition.registDateFrom = WEB3DateUtility.getDate("20070520", "yyyyMMdd");
    	l_WEB3AdminInformProfDistSellTransSrcCondition.registDateTo = null;
    	
    	try 
    	{
    		l_WEB3AdminInformProfDistSellTransSrcCondition.validate();
    	}
    	catch (Exception e)
    	{
    		fail();
    	}
    }

    public void test_validate_0012()
    {
    	WEB3AdminInformProfDistSellTransSrcCondition l_WEB3AdminInformProfDistSellTransSrcCondition
    	    = new WEB3AdminInformProfDistSellTransSrcCondition();
    	
    	l_WEB3AdminInformProfDistSellTransSrcCondition.branchCode = "1111";
    	l_WEB3AdminInformProfDistSellTransSrcCondition.traderCode = "1111";
    	l_WEB3AdminInformProfDistSellTransSrcCondition.accountCode = "1111";
    	l_WEB3AdminInformProfDistSellTransSrcCondition.productCode = "1111";
    	l_WEB3AdminInformProfDistSellTransSrcCondition.registDateFrom = null;
    	l_WEB3AdminInformProfDistSellTransSrcCondition.registDateTo = null;
    	
    	try 
    	{
    		l_WEB3AdminInformProfDistSellTransSrcCondition.validate();
    	}
    	catch (Exception e)
    	{
    		fail();
    	}
    }
}
@
