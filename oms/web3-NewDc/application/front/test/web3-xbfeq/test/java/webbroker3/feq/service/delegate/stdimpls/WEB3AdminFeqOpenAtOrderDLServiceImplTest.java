head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.17.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminFeqOpenAtOrderDLServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式寄付注文DLサービスImplテストクラス(WEB3AdminFeqOpenAtOrderDLServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/25  齊珂 (中訊) 新規作成
*/

package webbroker3.feq.service.delegate.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketPreferencesParams;

import test.util.TestDBUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.feq.message.WEB3AdminFeqOpenAtOrderDownloadInputRequest;
import webbroker3.feq.message.WEB3AdminFeqOpenAtOrderDownloadInputResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.FeqBranchMarketDealtCondParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * (外国株式寄付注文DLサービスImplテストクラス)<BR>
 * 
 * @@author 齊珂(中訊)
 * @@version 1.0
 */
public class WEB3AdminFeqOpenAtOrderDLServiceImplTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3AdminFeqOpenAtOrderDLServiceImplTest.class);
    
    public WEB3AdminFeqOpenAtOrderDLServiceImplTest(String arg0)
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
    
    public void testGetInputScreen1()
    {
        final String STR_METHOD_NAME = " testGetInputScreen1()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        
        WEB3AdminFeqOpenAtOrderDownloadInputResponse l_response = null;
        WEB3AdminFeqOpenAtOrderDLServiceImpl l_impl = new WEB3AdminFeqOpenAtOrderDLServiceImpl();
        WEB3AdminFeqOpenAtOrderDownloadInputRequest l_request = 
            new WEB3AdminFeqOpenAtOrderDownloadInputRequest();
        try
        {
            TestDBUtility.deleteAll(MarketParams.TYPE);
            TestDBUtility.deleteAll(FeqBranchMarketDealtCondParams.TYPE);
            AdministratorParams l_adminRow = TestDBUtility.getAdministratorRow();
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_adminRow);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3Administrator",
                "validateAuthority",
                new Class[] {String.class, boolean.class},
                Boolean.TRUE);
            
            FeqBranchMarketDealtCondParams l_params1 = TestDBUtility.getFeqBranchMarketDealtCondRow();
            TestDBUtility.insertWithDel(l_params1);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);
            
            l_response = l_impl.getInputScreen(l_request);
            assertEquals(l_response.marketCodeList[0], "SP");
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getErrorMessage(), l_ex);
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    public void testGetInputScreen2()
    {
        final String STR_METHOD_NAME = " testGetInputScreen2()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        
        WEB3AdminFeqOpenAtOrderDownloadInputResponse l_response = null;
        WEB3AdminFeqOpenAtOrderDLServiceImpl l_impl = new WEB3AdminFeqOpenAtOrderDLServiceImpl();
        WEB3AdminFeqOpenAtOrderDownloadInputRequest l_request = 
            new WEB3AdminFeqOpenAtOrderDownloadInputRequest();
        try
        {
            TestDBUtility.deleteAll(MarketParams.TYPE);
            TestDBUtility.deleteAll(FeqBranchMarketDealtCondParams.TYPE);
            
            AdministratorParams l_adminRow = TestDBUtility.getAdministratorRow();
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_adminRow);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3Administrator",
                "validateAuthority",
                new Class[] {String.class, boolean.class},
                Boolean.TRUE);
            
            FeqBranchMarketDealtCondParams l_params1 = TestDBUtility.getFeqBranchMarketDealtCondRow();
            TestDBUtility.insertWithDel(l_params1);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);
            
            MarketPreferencesParams l_MPParams = TestDBUtility.getMarketPreferencesRow();
            TestDBUtility.insertWithDel(l_MPParams);
            
            l_response = l_impl.getInputScreen(l_request);
            assertEquals(l_response.marketCodeList[0], "SP");
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getErrorMessage(), l_ex);
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    public void testGetInputScreen3()
    {
        final String STR_METHOD_NAME = " testGetInputScreen3()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        
        WEB3AdminFeqOpenAtOrderDownloadInputResponse l_response = null;
        WEB3AdminFeqOpenAtOrderDLServiceImpl l_impl = new WEB3AdminFeqOpenAtOrderDLServiceImpl();
        WEB3AdminFeqOpenAtOrderDownloadInputRequest l_request = 
            new WEB3AdminFeqOpenAtOrderDownloadInputRequest();
        try
        {
            TestDBUtility.deleteAll(MarketParams.TYPE);
            TestDBUtility.deleteAll(FeqBranchMarketDealtCondParams.TYPE);
            
            //admin
            AdministratorParams l_adminRow = TestDBUtility.getAdministratorRow();
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_adminRow);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3Administrator",
                "validateAuthority",
                new Class[] {String.class, boolean.class},
                Boolean.TRUE);
            
            //FeqBranchMarketDealtCond 1
            FeqBranchMarketDealtCondParams l_params1 = TestDBUtility.getFeqBranchMarketDealtCondRow();
            TestDBUtility.insertWithDel(l_params1);
            
            //market 1
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);

            //FeqBranchMarketDealtCond 2
            FeqBranchMarketDealtCondParams l_params2 = TestDBUtility.getFeqBranchMarketDealtCondRow();
            l_params2.setBranchCode("382");
            l_params2.setMarketCode("PS");           
            TestDBUtility.insertWithDel(l_params2);
            
            //market 2
            MarketParams l_marketParams2 = TestDBUtility.getMarketRow();
            l_marketParams2.setMarketId(3304L);
            l_marketParams2.setMarketCode("PS");
            TestDBUtility.insertWithDel(l_marketParams2);
            
            //FeqBranchMarketDealtCond 3
            FeqBranchMarketDealtCondParams l_params3 = TestDBUtility.getFeqBranchMarketDealtCondRow();
            l_params3.setBranchCode("383");
            l_params3.setMarketCode("PP");           
            TestDBUtility.insertWithDel(l_params3);
            
            //MarketPreferences
            MarketPreferencesParams l_MPParams = TestDBUtility.getMarketPreferencesRow();
            TestDBUtility.insertWithDel(l_MPParams);
            
            l_response = l_impl.getInputScreen(l_request);
            assertEquals(l_response.marketCodeList[0], "PS");
            assertEquals(l_response.marketCodeList[1], "SP");
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getErrorMessage(), l_ex);
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
}
@
