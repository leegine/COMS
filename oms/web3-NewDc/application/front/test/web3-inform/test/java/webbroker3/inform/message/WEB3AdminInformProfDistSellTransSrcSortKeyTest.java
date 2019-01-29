head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.31.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminInformProfDistSellTransSrcSortKeyTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 利金・分配金・売却代金振込先一覧ソートキーテストクラス(WEB3AdminInformProfDistSellTransSrcSortKeyTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/05/31 李木子 (中訊) 新規作成
*/

package webbroker3.inform.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * (利金・分配金・売却代金振込先一覧ソートキーテストクラス)<BR>
 * 利金・分配金・売却代金振込先一覧ソートキーテストクラス<BR>
 * @@author 李木子
 * @@version 1.0
 */
public class WEB3AdminInformProfDistSellTransSrcSortKeyTest extends TestBaseForMock {
	
    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
        		WEB3AdminInformProfDistSellTransSrcListRequestTest.class);
    
    public static StringBuffer expectMethodParam = new StringBuffer(); 

    public WEB3AdminInformProfDistSellTransSrcSortKeyTest(String arg0)
    {
        super(arg0);
    }

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

    public void test_validate_0001()
    {
    	WEB3AdminInformProfDistSellTransSrcSortKey l_WEB3AdminInformProfDistSellTransSrcSortKey
    	    = new WEB3AdminInformProfDistSellTransSrcSortKey();
    	
    	l_WEB3AdminInformProfDistSellTransSrcSortKey.keyItem = null;
    	
    	try 
    	{
    		l_WEB3AdminInformProfDistSellTransSrcSortKey.validate();
    		fail();
    	}
    	catch (WEB3BusinessLayerException l_ex)
    	{
    		assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00085, l_ex.getErrorInfo());
    	}
    	catch (Exception e)
    	{
    		fail();
    	}
    }

    public void test_validate_0002()
    {
    	WEB3AdminInformProfDistSellTransSrcSortKey l_WEB3AdminInformProfDistSellTransSrcSortKey
    	    = new WEB3AdminInformProfDistSellTransSrcSortKey();
    	
    	l_WEB3AdminInformProfDistSellTransSrcSortKey.keyItem = "requestNumber";
    	
    	try 
    	{
    		l_WEB3AdminInformProfDistSellTransSrcSortKey.validate();
    		fail();
    	}
    	catch (WEB3BusinessLayerException l_ex)
    	{
    		assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00086, l_ex.getErrorInfo());
    	}
    	catch (Exception e)
    	{
    		fail();
    	}
    }

    public void test_validate_0003()
    {
    	WEB3AdminInformProfDistSellTransSrcSortKey l_WEB3AdminInformProfDistSellTransSrcSortKey
    	    = new WEB3AdminInformProfDistSellTransSrcSortKey();
    	
    	l_WEB3AdminInformProfDistSellTransSrcSortKey.keyItem = "branchCode";
    	l_WEB3AdminInformProfDistSellTransSrcSortKey.ascDesc = null;
    	
    	try 
    	{
    		l_WEB3AdminInformProfDistSellTransSrcSortKey.validate();
    		fail();
    	}
    	catch (WEB3BusinessLayerException l_ex)
    	{
    		assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00087, l_ex.getErrorInfo());
    	}
    	catch (Exception e)
    	{
    		fail();
    	}
    }

    public void test_validate_0004()
    {
    	WEB3AdminInformProfDistSellTransSrcSortKey l_WEB3AdminInformProfDistSellTransSrcSortKey
    	    = new WEB3AdminInformProfDistSellTransSrcSortKey();
    	
    	l_WEB3AdminInformProfDistSellTransSrcSortKey.keyItem = "traderCode";
    	l_WEB3AdminInformProfDistSellTransSrcSortKey.ascDesc = "S";
    	
    	try 
    	{
    		l_WEB3AdminInformProfDistSellTransSrcSortKey.validate();
    		fail();
    	}
    	catch (WEB3BusinessLayerException l_ex)
    	{
    		assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00088, l_ex.getErrorInfo());
    	}
    	catch (Exception e)
    	{
    		fail();
    	}
    }

    public void test_validate_0005()
    {
    	WEB3AdminInformProfDistSellTransSrcSortKey l_WEB3AdminInformProfDistSellTransSrcSortKey
    	    = new WEB3AdminInformProfDistSellTransSrcSortKey();
    	
    	l_WEB3AdminInformProfDistSellTransSrcSortKey.keyItem = "accountCode";
    	l_WEB3AdminInformProfDistSellTransSrcSortKey.ascDesc = "A";
    	
    	try 
    	{
    		l_WEB3AdminInformProfDistSellTransSrcSortKey.validate();
    	}
    	catch (Exception e)
    	{
    		fail();
    	}
    }

    public void test_validate_0006()
    {
    	WEB3AdminInformProfDistSellTransSrcSortKey l_WEB3AdminInformProfDistSellTransSrcSortKey
    	    = new WEB3AdminInformProfDistSellTransSrcSortKey();
    	
    	l_WEB3AdminInformProfDistSellTransSrcSortKey.keyItem = "productCode";
    	l_WEB3AdminInformProfDistSellTransSrcSortKey.ascDesc = "D";
    	
    	try 
    	{
    		l_WEB3AdminInformProfDistSellTransSrcSortKey.validate();
    	}
    	catch (Exception e)
    	{
    		fail();
    	}
    }

    public void test_validate_0007()
    {
    	WEB3AdminInformProfDistSellTransSrcSortKey l_WEB3AdminInformProfDistSellTransSrcSortKey
    	    = new WEB3AdminInformProfDistSellTransSrcSortKey();
    	
    	l_WEB3AdminInformProfDistSellTransSrcSortKey.keyItem = "registDate";
    	l_WEB3AdminInformProfDistSellTransSrcSortKey.ascDesc = "D";
    	
    	try 
    	{
    		l_WEB3AdminInformProfDistSellTransSrcSortKey.validate();
    	}
    	catch (Exception e)
    	{
    		fail();
    	}
    }
}
@
