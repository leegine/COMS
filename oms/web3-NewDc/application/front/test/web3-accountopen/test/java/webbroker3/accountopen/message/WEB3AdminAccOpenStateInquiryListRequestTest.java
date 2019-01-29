head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.15.37;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminAccOpenStateInquiryListRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountopen.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminAccOpenStateInquiryListRequestTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccOpenStateInquiryListRequestTest.class);

    public WEB3AdminAccOpenStateInquiryListRequestTest(String arg0)
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
     * Test method for 'webbroker3.accountopen.message.WEB3AdminAccOpenStateInquiryListRequest.validate()'
     */
    public void testValidateCase1()
    {
        final String STR_METHOD_NAME = "testValidateCase1()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminAccOpenStateInquiryListRequest l_request =
                new WEB3AdminAccOpenStateInquiryListRequest();
            l_request.pageSize = "1";
            WEB3AccOpenSortKey[] sortKeys = new WEB3AccOpenSortKey[2];
            sortKeys[0] = new WEB3AccOpenSortKey();
            sortKeys[0] .keyItem = "branchCode";
            sortKeys[0] .ascDesc = "A";
            sortKeys[1] = new WEB3AccOpenSortKey();
            sortKeys[1] .keyItem = "accountCode";
            sortKeys[1] .ascDesc = "A";
            l_request.sortKeys = sortKeys;
            
            l_request.deleteFlag = "2";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03173, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateCase2()
    {
        final String STR_METHOD_NAME = "testValidateCase2()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminAccOpenStateInquiryListRequest l_request =
                new WEB3AdminAccOpenStateInquiryListRequest();
            l_request.pageSize = "1";
            WEB3AccOpenSortKey[] sortKeys = new WEB3AccOpenSortKey[2];
            sortKeys[0] = new WEB3AccOpenSortKey();
            sortKeys[0] .keyItem = "branchCode";
            sortKeys[0] .ascDesc = "A";
            sortKeys[1] = new WEB3AccOpenSortKey();
            sortKeys[1] .keyItem = "accountCode";
            sortKeys[1] .ascDesc = "A";
            l_request.sortKeys = sortKeys;
            l_request.deleteFlag = "1";
            l_request.printFlag = "4";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03174, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateCase3()
    {
        final String STR_METHOD_NAME = "testValidateCase3()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminAccOpenStateInquiryListRequest l_request =
                new WEB3AdminAccOpenStateInquiryListRequest();
            l_request.pageSize = "1";
            WEB3AccOpenSortKey[] sortKeys = new WEB3AccOpenSortKey[2];
            sortKeys[0] = new WEB3AccOpenSortKey();
            sortKeys[0] .keyItem = "branchCode";
            sortKeys[0] .ascDesc = "A";
            sortKeys[1] = new WEB3AccOpenSortKey();
            sortKeys[1] .keyItem = "accountCode";
            sortKeys[1] .ascDesc = "A";
            l_request.sortKeys = sortKeys;
            l_request.deleteFlag = "1";
            l_request.printFlag = "1";
            l_request.receiveFlag = "2";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03175, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateCase4()
    {
        final String STR_METHOD_NAME = "testValidateCase4()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminAccOpenStateInquiryListRequest l_request =
                new WEB3AdminAccOpenStateInquiryListRequest();
            l_request.pageSize = "1";
            WEB3AccOpenSortKey[] sortKeys = new WEB3AccOpenSortKey[2];
            sortKeys[0] = new WEB3AccOpenSortKey();
            sortKeys[0] .keyItem = "branchCode";
            sortKeys[0] .ascDesc = "A";
            sortKeys[1] = new WEB3AccOpenSortKey();
            sortKeys[1] .keyItem = "accountCode";
            sortKeys[1] .ascDesc = "A";
            l_request.sortKeys = sortKeys;
            l_request.deleteFlag = "1";
            l_request.printFlag = "1";
            l_request.receiveFlag = "1";
            l_request.taxTypeDiv = "2";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02114, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateCase5()
    {
        final String STR_METHOD_NAME = "testValidateCase5()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminAccOpenStateInquiryListRequest l_request =
                new WEB3AdminAccOpenStateInquiryListRequest();
            l_request.pageSize = "1";
            WEB3AccOpenSortKey[] sortKeys = new WEB3AccOpenSortKey[2];
            sortKeys[0] = new WEB3AccOpenSortKey();
            sortKeys[0] .keyItem = "branchCode";
            sortKeys[0] .ascDesc = "A";
            sortKeys[1] = new WEB3AccOpenSortKey();
            sortKeys[1] .keyItem = "accountCode";
            sortKeys[1] .ascDesc = "A";
            l_request.sortKeys = sortKeys;
            l_request.deleteFlag = "1";
            l_request.printFlag = "1";
            l_request.receiveFlag = "1";
            l_request.taxTypeDiv = "1";
            l_request.foreignerFlag = "2";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03176, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateCase6()
    {
        final String STR_METHOD_NAME = "testValidateCase6()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminAccOpenStateInquiryListRequest l_request =
                new WEB3AdminAccOpenStateInquiryListRequest();
            l_request.pageSize = "1";
            WEB3AccOpenSortKey[] sortKeys = new WEB3AccOpenSortKey[2];
            sortKeys[0] = new WEB3AccOpenSortKey();
            sortKeys[0] .keyItem = "branchCode";
            sortKeys[0] .ascDesc = "A";
            sortKeys[1] = new WEB3AccOpenSortKey();
            sortKeys[1] .keyItem = "accountCode";
            sortKeys[1] .ascDesc = "A";
            l_request.sortKeys = sortKeys;
            l_request.deleteFlag = "1";
            l_request.printFlag = "1";
            l_request.receiveFlag = "1";
            l_request.taxTypeDiv = "1";
            l_request.foreignerFlag = "1";
            l_request.validate();
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateCase7()
    {
        final String STR_METHOD_NAME = "testValidateCase7()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminAccOpenStateInquiryListRequest l_request =
                new WEB3AdminAccOpenStateInquiryListRequest();
            l_request.pageSize = "1";
            WEB3AccOpenSortKey[] sortKeys = new WEB3AccOpenSortKey[2];
            sortKeys[0] = new WEB3AccOpenSortKey();
            sortKeys[0] .keyItem = "branchCode";
            sortKeys[0] .ascDesc = "A";
            sortKeys[1] = new WEB3AccOpenSortKey();
            sortKeys[1] .keyItem = "accountCode";
            sortKeys[1] .ascDesc = "A";
            l_request.sortKeys = sortKeys;
//            l_request.deleteFlag = "1";
//            l_request.printFlag = "1";
//            l_request.receiveFlag = "1";
//            l_request.accountType = "1";
//            l_request.foreignerFlag = "1";
            l_request.validate();
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateCase8()
    {
        final String STR_METHOD_NAME = "testValidateCase8()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminAccOpenStateInquiryListRequest l_request =
                new WEB3AdminAccOpenStateInquiryListRequest();
            l_request.pageSize = "1";
            WEB3AccOpenSortKey[] sortKeys = new WEB3AccOpenSortKey[2];
            sortKeys[0] = new WEB3AccOpenSortKey();
            sortKeys[0] .keyItem = "branchCode";
            sortKeys[0] .ascDesc = "A";
            sortKeys[1] = new WEB3AccOpenSortKey();
            sortKeys[1] .keyItem = "accountCode";
            sortKeys[1] .ascDesc = "A";
            l_request.sortKeys = sortKeys;
//            l_request.deleteFlag = "1";
//            l_request.printFlag = "1";
//            l_request.receiveFlag = "1";
//            l_request.accountType = "1";
//            l_request.foreignerFlag = "1";
            l_request.insiderDiv = "3";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03186, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateCase9()
    {
        final String STR_METHOD_NAME = "testValidateCase9()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminAccOpenStateInquiryListRequest l_request =
                new WEB3AdminAccOpenStateInquiryListRequest();
            l_request.pageSize = "1";
            WEB3AccOpenSortKey[] sortKeys = new WEB3AccOpenSortKey[2];
            sortKeys[0] = new WEB3AccOpenSortKey();
            sortKeys[0] .keyItem = "branchCode";
            sortKeys[0] .ascDesc = "A";
            sortKeys[1] = new WEB3AccOpenSortKey();
            sortKeys[1] .keyItem = "accountCode";
            sortKeys[1] .ascDesc = "A";
            l_request.sortKeys = sortKeys;
//            l_request.deleteFlag = "1";
//            l_request.printFlag = "1";
//            l_request.receiveFlag = "1";
//            l_request.accountType = "1";
//            l_request.foreignerFlag = "1";
            l_request.insiderDiv = null;
            l_request.validate();
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
    }
        
        public void testValidateCase10()
        {
            final String STR_METHOD_NAME = "testValidateCase10()";
            log.entering(STR_METHOD_NAME);

            try
            {
                WEB3AdminAccOpenStateInquiryListRequest l_request =
                    new WEB3AdminAccOpenStateInquiryListRequest();
                l_request.pageSize = "1";
                WEB3AccOpenSortKey[] sortKeys = new WEB3AccOpenSortKey[2];
                sortKeys[0] = new WEB3AccOpenSortKey();
                sortKeys[0] .keyItem = "branchCode";
                sortKeys[0] .ascDesc = "A";
                sortKeys[1] = new WEB3AccOpenSortKey();
                sortKeys[1] .keyItem = "accountCode";
                sortKeys[1] .ascDesc = "A";
                l_request.sortKeys = sortKeys;
//                l_request.deleteFlag = "1";
//                l_request.printFlag = "1";
//                l_request.receiveFlag = "1";
//                l_request.accountType = "1";
//                l_request.foreignerFlag = "1";
                l_request.insiderDiv = "1";
                l_request.validate();
            }
            catch (Exception l_ex)
            {
                log.error(l_ex.getMessage());
                fail();
            }
        log.exiting(STR_METHOD_NAME);
    }
        
        public void testValidateCase11()
        {
            final String STR_METHOD_NAME = "testValidateCase11()";
            log.entering(STR_METHOD_NAME);

            try
            {
                WEB3AdminAccOpenStateInquiryListRequest l_request =
                    new WEB3AdminAccOpenStateInquiryListRequest();
                l_request.pageSize = "1";
                WEB3AccOpenSortKey[] sortKeys = new WEB3AccOpenSortKey[2];
                sortKeys[0] = new WEB3AccOpenSortKey();
                sortKeys[0] .keyItem = "branchCode";
                sortKeys[0] .ascDesc = "A";
                sortKeys[1] = new WEB3AccOpenSortKey();
                sortKeys[1] .keyItem = "accountCode";
                sortKeys[1] .ascDesc = "A";
                l_request.sortKeys = sortKeys;
//                l_request.deleteFlag = "1";
//                l_request.printFlag = "1";
//                l_request.receiveFlag = "1";
//                l_request.accountType = "1";
//                l_request.foreignerFlag = "1";
                l_request.insiderDiv = "0";
                l_request.validate();
            }
            catch (Exception l_ex)
            {
                log.error(l_ex.getMessage());
                fail();
            }
        log.exiting(STR_METHOD_NAME);
    }
}
@
