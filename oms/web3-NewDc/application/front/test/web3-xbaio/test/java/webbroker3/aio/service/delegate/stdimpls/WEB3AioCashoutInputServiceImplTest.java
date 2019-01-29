head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.36.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AioCashoutInputServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (äî)ëÂòaëçå§ èÿåîÉ\ÉäÉÖÅ[ÉVÉáÉìÉVÉXÉeÉÄëÊìÒïî
File Name        : //TODO(WEB3AioCashoutInputServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/03/19 âΩï∂ïq (íÜêu) êVãKçÏê¨
*/
package webbroker3.aio.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;

import test.util.TestDBUtility;

import webbroker3.aio.message.WEB3AioCashoutInputRequest;
import webbroker3.aio.message.WEB3AioCashoutInputResponse;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.TransferedFinInstitutionParams;
import webbroker3.gentrade.data.TransferedFinInstitutionRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.util.WEB3LogUtility;

/**
 * XXXXXXÉNÉâÉX//TODO
 *
 * @@author âΩï∂ïq(íÜêu)
 * @@version 1.0
 */
public class WEB3AioCashoutInputServiceImplTest extends TestBaseForMock
{

    private WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashoutInputServiceImplTest.class);
    
    WEB3AioCashoutInputServiceImpl l_impl = new WEB3AioCashoutInputServiceImpl();
    public WEB3AioCashoutInputServiceImplTest(String arg0)
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
    
    public void testExecute1()
    {
        final String STR_METHOD_NAME = "testExecute1()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AioCashoutInputRequest l_request = new WEB3AioCashoutInputRequest();
        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(GtlUtils.getSystemTimestamp());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.WEB3AioOrderManager", 
                    "validateOrder",
                    new Class[] {SubAccount.class},null);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[]{},
                new Long(333812512246L));
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            l_institutionParams.setPaymentReserve("0");
            l_institutionParams.setTheDayTransfer("1");
            TestDBUtility.insertWithDel(l_institutionParams);
            WEB3GentradeTradingTimeManagementForMock l_mock =
                new WEB3GentradeTradingTimeManagementForMock();
            TransferedFinInstitutionParams l_transferedFinInstitutionParams = TestDBUtility.getTransferedFinInstitutionRow();
            l_transferedFinInstitutionParams.setInstitutionCode("0D");
            l_transferedFinInstitutionParams.setBranchCode("381");
            l_transferedFinInstitutionParams.setAccountCode("2512246");
            l_transferedFinInstitutionParams.setDesignateDiv("A");
            l_transferedFinInstitutionParams.setTransferDiv("5");
            TestDBUtility.insertWithDel(l_transferedFinInstitutionParams);            
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "getPaymentTradingPower",
                    new Class[] {WEB3GentradeSubAccount.class, Date.class},
                    new Double(100));          
            
            l_impl.execute(l_request);
            
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExecute2()
    {
        final String STR_METHOD_NAME = "testExecute2()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AioCashoutInputRequest l_request = new WEB3AioCashoutInputRequest();
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.WEB3AioOrderManager", 
                    "validateOrder",
                    new Class[] {SubAccount.class},null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[]{},
                new Long(333812512246L));
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(TransferedFinInstitutionRow.TYPE);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setBankAccountRegi("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            l_institutionParams.setPaymentReserve("0");
            l_institutionParams.setTheDayTransfer("1");
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TransferedFinInstitutionParams l_transferedFinInstitutionParams = TestDBUtility.getTransferedFinInstitutionRow();
            l_transferedFinInstitutionParams.setInstitutionCode("0D");
            l_transferedFinInstitutionParams.setBranchCode("381");
            l_transferedFinInstitutionParams.setAccountCode("2512246");
            l_transferedFinInstitutionParams.setDesignateDiv("A");
            l_transferedFinInstitutionParams.setTransferDiv("5");
            TestDBUtility.insertWithDel(l_transferedFinInstitutionParams);
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(GtlUtils.getSystemTimestamp());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "getPaymentTradingPower",
                    new Class[] {WEB3GentradeSubAccount.class, Date.class},
                    new Double(100));
            
            WEB3TPTradingPowerResult l_result = new WEB3TPTradingPowerResult();
            l_result.setResultFlg(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "validateTradingPower",
                    new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class,boolean.class},
                    l_result);
            WEB3GentradeTradingTimeManagementForMock l_mock2 =
                new WEB3GentradeTradingTimeManagementForMock(); 
            WEB3AioCashoutInputResponse l_response = (WEB3AioCashoutInputResponse) l_impl.execute(l_request);
            log.debug("*****l_response.transRegistDiv = " + l_response.transRegistDiv);
            assertEquals("1", l_response.transRegistDiv);
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
