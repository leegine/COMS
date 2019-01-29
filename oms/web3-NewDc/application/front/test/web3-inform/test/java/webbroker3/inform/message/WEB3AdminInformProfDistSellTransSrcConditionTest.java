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
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����E���z���E���p����U���挟�������e�X�g�N���X(WEB3AdminInformProfDistSellTransSrcConditionTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/05/31 ���؎q (���u) �V�K�쐬
*/

package webbroker3.inform.message;


import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (�����E���z���E���p����U���挟�������e�X�g�N���X)<BR>
 * �����E���z���E���p����U���挟�������e�X�g�N���X<BR>
 * @@author ���؎q
 * @@version 1.0
 */
public class WEB3AdminInformProfDistSellTransSrcConditionTest extends TestBaseForMock {
	
    /**
     * (���O�o�̓��[�e�B���e�B)
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
    	
    	l_WEB3AdminInformProfDistSellTransSrcCondition.branchCode = "�Q�Q�Q";
    	
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
    	l_WEB3AdminInformProfDistSellTransSrcCondition.traderCode = "�Q�Q�Qaaa";
    	
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
    	l_WEB3AdminInformProfDistSellTransSrcCondition.accountCode = "�Q�Q�Q";
    	
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
    	l_WEB3AdminInformProfDistSellTransSrcCondition.productCode = "�Q�Q�Q";
    	
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
