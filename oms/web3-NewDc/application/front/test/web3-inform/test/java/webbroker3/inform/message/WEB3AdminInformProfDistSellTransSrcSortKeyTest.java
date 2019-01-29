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
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����E���z���E���p����U����ꗗ�\�[�g�L�[�e�X�g�N���X(WEB3AdminInformProfDistSellTransSrcSortKeyTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/05/31 ���؎q (���u) �V�K�쐬
*/

package webbroker3.inform.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * (�����E���z���E���p����U����ꗗ�\�[�g�L�[�e�X�g�N���X)<BR>
 * �����E���z���E���p����U����ꗗ�\�[�g�L�[�e�X�g�N���X<BR>
 * @@author ���؎q
 * @@version 1.0
 */
public class WEB3AdminInformProfDistSellTransSrcSortKeyTest extends TestBaseForMock {
	
    /**
     * (���O�o�̓��[�e�B���e�B)
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
