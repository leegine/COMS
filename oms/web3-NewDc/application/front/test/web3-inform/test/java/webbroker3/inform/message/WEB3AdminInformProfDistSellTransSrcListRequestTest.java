head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.29.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminInformProfDistSellTransSrcListRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 利金・分配金・売却代金振込先一覧リクエストテストクラス(WEB3AdminInformProfDistSellTransSrcListRequestTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/05/31 李木子 (中訊) 新規作成
*/

package webbroker3.inform.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * (利金・分配金・売却代金振込先一覧リクエストテストクラス)<BR>
 * 利金・分配金・売却代金振込先一覧リクエストテストクラス<BR>
 * @@author 謝旋
 * @@version 1.0
 */
public class WEB3AdminInformProfDistSellTransSrcListRequestTest extends
		TestBaseForMock {
	
    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
        		WEB3AdminInformProfDistSellTransSrcListRequestTest.class);
    
    public static StringBuffer expectMethodParam = new StringBuffer(); 

    public WEB3AdminInformProfDistSellTransSrcListRequestTest(String arg0)
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
    	WEB3AdminInformProfDistSellTransSrcListRequest l_WEB3AdminInformProfDistSellTransSrcListRequest
    	    = new WEB3AdminInformProfDistSellTransSrcListRequest();
    	
    	l_WEB3AdminInformProfDistSellTransSrcListRequest.searchCondition = null;
    	
    	try 
    	{
    		l_WEB3AdminInformProfDistSellTransSrcListRequest.validate();
    	}
    	catch (WEB3BusinessLayerException l_ex)
    	{
    		assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00945, l_ex.getErrorInfo());
    	}
    	catch (Exception e)
    	{
    		fail();
    	}
    }

    public void test_validate_0002()
    {
    	WEB3AdminInformProfDistSellTransSrcListRequest l_WEB3AdminInformProfDistSellTransSrcListRequest =
    	    new WEB3AdminInformProfDistSellTransSrcListRequest();
    	
    	WEB3AdminInformProfDistSellTransSrcCondition searchConditionForTest =
    		new WEB3AdminInformProfDistSellTransSrcCondition();
    	
    	searchConditionForTest.accountCode = "1";
    	
    	l_WEB3AdminInformProfDistSellTransSrcListRequest.searchCondition = searchConditionForTest;
    	l_WEB3AdminInformProfDistSellTransSrcListRequest.sortKeys = null;
    	
    	try 
    	{
    		l_WEB3AdminInformProfDistSellTransSrcListRequest.validate();
    		fail();
    	}
    	catch (WEB3BusinessLayerException l_ex)
    	{
    		assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00231, l_ex.getErrorInfo());
    	}
    	catch (Exception e)
    	{
    		fail();
    	}
    }

    public void test_validate_0003()
    {
    	WEB3AdminInformProfDistSellTransSrcListRequest l_WEB3AdminInformProfDistSellTransSrcListRequest =
    	    new WEB3AdminInformProfDistSellTransSrcListRequest();
    	
    	WEB3AdminInformProfDistSellTransSrcCondition searchConditionForTest =
    		new WEB3AdminInformProfDistSellTransSrcCondition();
    	WEB3AdminInformProfDistSellTransSrcSortKey sortKeysForTest =
    		new WEB3AdminInformProfDistSellTransSrcSortKey();
    	
    	sortKeysForTest.ascDesc = "A";
    	sortKeysForTest.keyItem = "traderCode";
    	WEB3AdminInformProfDistSellTransSrcSortKey[] sortKeysForTests = {sortKeysForTest}; 
    	
    	searchConditionForTest.accountCode = "1";
    	
    	l_WEB3AdminInformProfDistSellTransSrcListRequest.searchCondition = searchConditionForTest;
    	l_WEB3AdminInformProfDistSellTransSrcListRequest.sortKeys = sortKeysForTests;
    	l_WEB3AdminInformProfDistSellTransSrcListRequest.pageIndex = null;
    	
    	try 
    	{
    		l_WEB3AdminInformProfDistSellTransSrcListRequest.validate();
    		fail();
    	}
    	catch (WEB3BusinessLayerException l_ex)
    	{
    		assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00089, l_ex.getErrorInfo());
    	}
    	catch (Exception e)
    	{
    		fail();
    	}
    }

    public void test_validate_0004()
    {
    	WEB3AdminInformProfDistSellTransSrcListRequest l_WEB3AdminInformProfDistSellTransSrcListRequest =
    	    new WEB3AdminInformProfDistSellTransSrcListRequest();
    	
    	WEB3AdminInformProfDistSellTransSrcCondition searchConditionForTest =
    		new WEB3AdminInformProfDistSellTransSrcCondition();
    	WEB3AdminInformProfDistSellTransSrcSortKey sortKeysForTest =
    		new WEB3AdminInformProfDistSellTransSrcSortKey();
    	
    	sortKeysForTest.ascDesc = "A";
    	sortKeysForTest.keyItem = "traderCode";
    	WEB3AdminInformProfDistSellTransSrcSortKey[] sortKeysForTests = {sortKeysForTest}; 
    	
    	searchConditionForTest.accountCode = "1";
    	
    	l_WEB3AdminInformProfDistSellTransSrcListRequest.searchCondition = searchConditionForTest;
    	l_WEB3AdminInformProfDistSellTransSrcListRequest.sortKeys = sortKeysForTests;
    	l_WEB3AdminInformProfDistSellTransSrcListRequest.pageIndex = "-1";
    	
    	try 
    	{
    		l_WEB3AdminInformProfDistSellTransSrcListRequest.validate();
    		fail();
    	}
    	catch (WEB3BusinessLayerException l_ex)
    	{
    		assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00616, l_ex.getErrorInfo());
    	}
    	catch (Exception e)
    	{
    		fail();
    	}
    }

    public void test_validate_0005()
    {
    	WEB3AdminInformProfDistSellTransSrcListRequest l_WEB3AdminInformProfDistSellTransSrcListRequest =
    	    new WEB3AdminInformProfDistSellTransSrcListRequest();
    	
    	WEB3AdminInformProfDistSellTransSrcCondition searchConditionForTest =
    		new WEB3AdminInformProfDistSellTransSrcCondition();
    	WEB3AdminInformProfDistSellTransSrcSortKey sortKeysForTest =
    		new WEB3AdminInformProfDistSellTransSrcSortKey();
    	
    	sortKeysForTest.ascDesc = "A";
    	sortKeysForTest.keyItem = "traderCode";
    	WEB3AdminInformProfDistSellTransSrcSortKey[] sortKeysForTests = {sortKeysForTest}; 
    	
    	searchConditionForTest.accountCode = "1";
    	
    	l_WEB3AdminInformProfDistSellTransSrcListRequest.searchCondition = searchConditionForTest;
    	l_WEB3AdminInformProfDistSellTransSrcListRequest.sortKeys = sortKeysForTests;
    	l_WEB3AdminInformProfDistSellTransSrcListRequest.pageIndex = "aaa";
    	
    	try 
    	{
    		l_WEB3AdminInformProfDistSellTransSrcListRequest.validate();
    		fail();
    	}
    	catch (WEB3BusinessLayerException l_ex)
    	{
    		assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00090, l_ex.getErrorInfo());
    	}
    	catch (Exception e)
    	{
    		fail();
    	}
    }

    public void test_validate_0006()
    {
    	WEB3AdminInformProfDistSellTransSrcListRequest l_WEB3AdminInformProfDistSellTransSrcListRequest =
    	    new WEB3AdminInformProfDistSellTransSrcListRequest();
    	
    	WEB3AdminInformProfDistSellTransSrcCondition searchConditionForTest =
    		new WEB3AdminInformProfDistSellTransSrcCondition();
    	WEB3AdminInformProfDistSellTransSrcSortKey sortKeysForTest =
    		new WEB3AdminInformProfDistSellTransSrcSortKey();
    	
    	sortKeysForTest.ascDesc = "A";
    	sortKeysForTest.keyItem = "traderCode";
    	WEB3AdminInformProfDistSellTransSrcSortKey[] sortKeysForTests = {sortKeysForTest}; 
    	
    	searchConditionForTest.accountCode = "1";
    	
    	l_WEB3AdminInformProfDistSellTransSrcListRequest.searchCondition = searchConditionForTest;
    	l_WEB3AdminInformProfDistSellTransSrcListRequest.sortKeys = sortKeysForTests;
    	l_WEB3AdminInformProfDistSellTransSrcListRequest.pageIndex = "2";
    	l_WEB3AdminInformProfDistSellTransSrcListRequest.pageSize = null;
    	
    	try 
    	{
    		l_WEB3AdminInformProfDistSellTransSrcListRequest.validate();
    		fail();
    	}
    	catch (WEB3BusinessLayerException l_ex)
    	{
    		assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02224, l_ex.getErrorInfo());
    	}
    	catch (Exception e)
    	{
    		fail();
    	}
    }

    public void test_validate_0007()
    {
    	WEB3AdminInformProfDistSellTransSrcListRequest l_WEB3AdminInformProfDistSellTransSrcListRequest =
    	    new WEB3AdminInformProfDistSellTransSrcListRequest();
    	
    	WEB3AdminInformProfDistSellTransSrcCondition searchConditionForTest =
    		new WEB3AdminInformProfDistSellTransSrcCondition();
    	WEB3AdminInformProfDistSellTransSrcSortKey sortKeysForTest =
    		new WEB3AdminInformProfDistSellTransSrcSortKey();
    	
    	sortKeysForTest.ascDesc = "A";
    	sortKeysForTest.keyItem = "traderCode";
    	WEB3AdminInformProfDistSellTransSrcSortKey[] sortKeysForTests = {sortKeysForTest}; 
    	
    	searchConditionForTest.accountCode = "1";
    	
    	l_WEB3AdminInformProfDistSellTransSrcListRequest.searchCondition = searchConditionForTest;
    	l_WEB3AdminInformProfDistSellTransSrcListRequest.sortKeys = sortKeysForTests;
    	l_WEB3AdminInformProfDistSellTransSrcListRequest.pageIndex = "2";
    	l_WEB3AdminInformProfDistSellTransSrcListRequest.pageSize = "-1";
    	
    	try 
    	{
    		l_WEB3AdminInformProfDistSellTransSrcListRequest.validate();
    		fail();
    	}
    	catch (WEB3BusinessLayerException l_ex)
    	{
    		assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00091, l_ex.getErrorInfo());
    	}
    	catch (Exception e)
    	{
    		fail();
    	}
    }

    public void test_validate_0008()
    {
    	WEB3AdminInformProfDistSellTransSrcListRequest l_WEB3AdminInformProfDistSellTransSrcListRequest =
    	    new WEB3AdminInformProfDistSellTransSrcListRequest();
    	
    	WEB3AdminInformProfDistSellTransSrcCondition searchConditionForTest =
    		new WEB3AdminInformProfDistSellTransSrcCondition();
    	WEB3AdminInformProfDistSellTransSrcSortKey sortKeysForTest =
    		new WEB3AdminInformProfDistSellTransSrcSortKey();
    	
    	sortKeysForTest.ascDesc = "A";
    	sortKeysForTest.keyItem = "traderCode";
    	WEB3AdminInformProfDistSellTransSrcSortKey[] sortKeysForTests = {sortKeysForTest}; 
    	
    	searchConditionForTest.accountCode = "1";
    	
    	l_WEB3AdminInformProfDistSellTransSrcListRequest.searchCondition = searchConditionForTest;
    	l_WEB3AdminInformProfDistSellTransSrcListRequest.sortKeys = sortKeysForTests;
    	l_WEB3AdminInformProfDistSellTransSrcListRequest.pageIndex = "2";
    	l_WEB3AdminInformProfDistSellTransSrcListRequest.pageSize = "aaa";
    	
    	try 
    	{
    		l_WEB3AdminInformProfDistSellTransSrcListRequest.validate();
    		fail();
    	}
    	catch (WEB3BusinessLayerException l_ex)
    	{
    		assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00092, l_ex.getErrorInfo());
    	}
    	catch (Exception e)
    	{
    		fail();
    	}
    }

    public void test_validate_0009()
    {
    	WEB3AdminInformProfDistSellTransSrcListRequest l_WEB3AdminInformProfDistSellTransSrcListRequest =
    	    new WEB3AdminInformProfDistSellTransSrcListRequest();
    	
    	WEB3AdminInformProfDistSellTransSrcCondition searchConditionForTest =
    		new WEB3AdminInformProfDistSellTransSrcCondition();
    	WEB3AdminInformProfDistSellTransSrcSortKey sortKeysForTest =
    		new WEB3AdminInformProfDistSellTransSrcSortKey();
    	
    	sortKeysForTest.ascDesc = "A";
    	sortKeysForTest.keyItem = "traderCode";
    	WEB3AdminInformProfDistSellTransSrcSortKey[] sortKeysForTests = {sortKeysForTest}; 
    	
    	searchConditionForTest.accountCode = "1";
    	
    	l_WEB3AdminInformProfDistSellTransSrcListRequest.searchCondition = searchConditionForTest;
    	l_WEB3AdminInformProfDistSellTransSrcListRequest.sortKeys = sortKeysForTests;
    	l_WEB3AdminInformProfDistSellTransSrcListRequest.pageIndex = "2";
    	l_WEB3AdminInformProfDistSellTransSrcListRequest.pageSize = "3";
    	
    	try 
    	{
    		l_WEB3AdminInformProfDistSellTransSrcListRequest.validate();
    	}
    	catch (Exception e)
    	{
    		fail();
    	}
    }

}
@
