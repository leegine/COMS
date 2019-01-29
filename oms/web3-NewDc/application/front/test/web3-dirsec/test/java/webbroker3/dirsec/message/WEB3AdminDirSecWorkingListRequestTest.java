head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.00.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminDirSecWorkingListRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.dirsec.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminDirSecWorkingListRequestTest extends TestBaseForMock
{

    /**
     * ログ出力ユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminDirSecWorkingListRequestTest.class);

    public WEB3AdminDirSecWorkingListRequestTest(String arg0)
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

    public void testValidate_Case001()
    {
        final String STR_METHOD_NAME = "testValidate_Case001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminDirSecWorkingListRequest l_request =
                new WEB3AdminDirSecWorkingListRequest();
            l_request.branchCode = null;
            l_request.validate();
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01429, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidate_Case002()
    {
        final String STR_METHOD_NAME = "testValidate_Case002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminDirSecWorkingListRequest l_request =
                new WEB3AdminDirSecWorkingListRequest();
            l_request.branchCode = new String[0];
            l_request.validate();
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02175, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_Case003()
    {
        final String STR_METHOD_NAME = "testValidate_Case003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminDirSecWorkingListRequest l_request =
                new WEB3AdminDirSecWorkingListRequest();
            String[] l_strBranchCode = new String[1];
            l_strBranchCode[0] = null;
            l_request.branchCode = l_strBranchCode;
            l_request.validate();
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00833, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_Case004()
    {
        final String STR_METHOD_NAME = "testValidate_Case004()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminDirSecWorkingListRequest l_request =
                new WEB3AdminDirSecWorkingListRequest();
            String[] l_strBranchCode = new String[1];
            l_strBranchCode[0] = "1df";
            l_request.branchCode = l_strBranchCode;
            l_request.validate();
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01729, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_Case005()
    {
        final String STR_METHOD_NAME = "testValidate_Case005()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminDirSecWorkingListRequest l_request =
                new WEB3AdminDirSecWorkingListRequest();
            String[] l_strBranchCode = new String[1];
            l_strBranchCode[0] = "１２３";
            l_request.branchCode = l_strBranchCode;
            l_request.validate();
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01729, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_Case006()
    {
        final String STR_METHOD_NAME = "testValidate_Case006()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminDirSecWorkingListRequest l_request =
                new WEB3AdminDirSecWorkingListRequest();
            String[] l_strBranchCode = new String[3];
            l_strBranchCode[0] = "123";
            l_strBranchCode[1] = "456";
            l_strBranchCode[2] = "1234";
            l_request.branchCode = l_strBranchCode;
            l_request.validate();
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00834, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_Case007()
    {
        final String STR_METHOD_NAME = "testValidate_Case007()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminDirSecWorkingListRequest l_request =
                new WEB3AdminDirSecWorkingListRequest();
            String[] l_strBranchCode = new String[3];
            l_strBranchCode[0] = "123";
            l_strBranchCode[1] = "456";
            l_strBranchCode[2] = "789";
            l_request.branchCode = l_strBranchCode;
            l_request.validate();
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
