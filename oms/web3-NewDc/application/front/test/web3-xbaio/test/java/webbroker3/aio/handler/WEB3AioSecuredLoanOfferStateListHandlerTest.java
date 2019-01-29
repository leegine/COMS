head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.25.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AioSecuredLoanOfferStateListHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 担保ローン申込状況一覧ハンドラのテストクラス(WEB3AioSecuredLoanOfferStateListHandlerTest.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/09/23 何文敏 (中訊) 新規作成
*/
package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;

import test.util.TestDBUtility;

import webbroker3.aio.message.WEB3AdminSLAccountOpenApplyListRequest;
import webbroker3.aio.message.WEB3AdminSLAccountOpenApplyListResponse;
import webbroker3.aio.service.delegate.WEB3AioSecuredLoanOfferStateListService;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdminPermissionRow;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * 
 * @@author 何文敏
 *
 */
public class WEB3AioSecuredLoanOfferStateListHandlerTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSecuredLoanOfferStateListHandlerTest.class);
    WEB3AioSecuredLoanOfferStateListHandler handler = new WEB3AioSecuredLoanOfferStateListHandler();
    WEB3AioSecuredLoanOfferStateListService service = null;
    public WEB3AioSecuredLoanOfferStateListHandlerTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testGetListScreen_case0001()
    {
        final String STR_METHOD_NAME = "testGetListScreen_case0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            service =
                (WEB3AioSecuredLoanOfferStateListService)Services.getService(
                    WEB3AioSecuredLoanOfferStateListService.class);
            Services.unregisterService(WEB3AioSecuredLoanOfferStateListService.class);
            WEB3AdminSLAccountOpenApplyListRequest l_request =
                new WEB3AdminSLAccountOpenApplyListRequest();
            WEB3AdminSLAccountOpenApplyListResponse l_response = handler.getListScreen(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch(Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {

            Services.registerService(WEB3AioSecuredLoanOfferStateListService.class, service);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetListScreen_case0002()
    {
        final String STR_METHOD_NAME = "testGetListScreen_case0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdministratorParams l_adminStratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_adminStratorParams);
            
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_adminStratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            WEB3AdminSLAccountOpenApplyListRequest l_request = new WEB3AdminSLAccountOpenApplyListRequest();
            WEB3AdminSLAccountOpenApplyListResponse l_response = handler.getListScreen(l_request);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, l_response.errorInfo);
        }
        catch(Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetListScreen_case0003()
    {
        final String STR_METHOD_NAME = "testGetListScreen_case0003()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_adminStratorParams = TestDBUtility.getAdministratorRow();
            l_adminStratorParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_adminStratorParams);
            
            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("B0601");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_adminStratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            WEB3AdminSLAccountOpenApplyListRequest l_request = new WEB3AdminSLAccountOpenApplyListRequest();
            l_request.branchCode = "381";
            handler.getListScreen(l_request);
        }
        catch(Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
