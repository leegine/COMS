head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.17.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminFeqRcvdQueueReferenceServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.feq.service.delegate.stdimpls;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.feq.message.WEB3AdminFeqRcvdQueueReferenceInputRequest;
import webbroker3.feq.message.WEB3AdminFeqRcvdQueueReferenceInputResponse;
import webbroker3.feq.message.WEB3AdminFeqRcvdQueueReferenceRequest;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminFeqRcvdQueueReferenceServiceImplTest extends
        TestBaseForMock
{
    /**
     * ログ出力ユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFeqRcvdQueueReferenceServiceImplTest.class);
    WEB3AdminFeqRcvdQueueReferenceServiceImpl l_impl;

    public WEB3AdminFeqRcvdQueueReferenceServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        l_impl = new WEB3AdminFeqRcvdQueueReferenceServiceImpl();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testGetInputScreen_Case0001()
    {
        final String STR_METHOD_NAME = "testGetInputScreen_Case0001()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminFeqRcvdQueueReferenceInputRequest l_request=
            new WEB3AdminFeqRcvdQueueReferenceInputRequest();
        
        try
        {
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            WEB3Administrator l_admin = new WEB3Administrator(l_administratorParams); 
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_admin);
            WEB3AdministratorForMock.mockValidateAuthority(
                l_admin,
                WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE,
                false,
                true
                );
            
            WEB3AdminFeqRcvdQueueReferenceInputResponse l_response = l_impl.getInputScreen(l_request);
            assertEquals(5,l_response.transactionDivList.length);
            assertEquals("7",l_response.transactionDivList[2]);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testGetListScreen_Case0001()
    {
        final String STR_METHOD_NAME = "testGetListScreen_Case0001()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminFeqRcvdQueueReferenceRequest l_request=
            new WEB3AdminFeqRcvdQueueReferenceRequestTest();
        
        try
        {
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            WEB3Administrator l_admin = new WEB3Administrator(l_administratorParams); 
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_admin);
            WEB3AdministratorForMock.mockValidateAuthority(
                l_admin,
                WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE,
                false,
                true
                );
            
            l_impl.getListScreen(l_request);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.SYSTEM_ERROR_80017);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public class WEB3AdminFeqRcvdQueueReferenceRequestTest extends WEB3AdminFeqRcvdQueueReferenceRequest
    {
        public void validate() throws WEB3BaseException
        {

        }
    }
}
@
