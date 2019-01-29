head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.38.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AioCashoutTradingPowerServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (äî)ëÂòaëçå§ èÿåîÉ\ÉäÉÖÅ[ÉVÉáÉìÉVÉXÉeÉÄëÊìÒïî
File Name        : //TODO(WEB3AioCashoutTradingPowerServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/03/21 âΩï∂ïq (íÜêu) êVãKçÏê¨
*/
package webbroker3.aio.service.delegate.stdimpls;

import java.util.Calendar;

import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesRow;

import test.util.TestDBUtility;

import webbroker3.aio.data.HostPaymentOrderParams;
import webbroker3.aio.data.HostPaymentOrderRow;
import webbroker3.aio.message.WEB3AioCashoutTradingPowerCheckRequest;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * XXXXXXÉNÉâÉX//TODO
 *
 * @@author âΩï∂ïq(íÜêu)
 * @@version 1.0
 */
public class WEB3AioCashoutTradingPowerServiceImplTest extends TestBaseForMock
{

    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashoutTradingPowerServiceImplTest.class);
    
    WEB3AioCashoutTradingPowerServiceImpl l_mpl =
        new WEB3AioCashoutTradingPowerServiceImpl();
    
    public WEB3AioCashoutTradingPowerServiceImplTest(String arg0)
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
    
    public void testExecute()
    {
        final String STR_METHOD_NAME = "testGetTriggerIssueNumber()";
        log.entering(STR_METHOD_NAME);
        WEB3AioCashoutTradingPowerCheckRequest l_request =
            new WEB3AioCashoutTradingPowerCheckRequest();
        l_request.processFlag = "1";
        l_request.institutionCode ="0D";
        
        try
        {
            TestDBUtility.deleteAll(SystemPreferencesRow.TYPE);
            SystemPreferencesParams l_systemPerferencesParams1 =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPerferencesParams1.setName("PAY_TRIGGER_ORDER_MAX_COUNT");
            TestDBUtility.insertWithDel(l_systemPerferencesParams1);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.marketadaptor.WEB3AioMarketRequestSenderServiceImpl",
                    "submitTrigger",
                    new Class[]{String.class, String.class},
                    null);
            
            l_mpl.execute(l_request);
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExecute1()
    {
        final String STR_METHOD_NAME = "testExecute1()";
        log.entering(STR_METHOD_NAME);
        WEB3AioCashoutTradingPowerCheckRequest l_request =
            new WEB3AioCashoutTradingPowerCheckRequest();
        l_request.processFlag = "1";
        l_request.institutionCode ="0D";
        
        try
        {
            TestDBUtility.deleteAll(SystemPreferencesRow.TYPE);
            SystemPreferencesParams l_systemPerferencesParams1 =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPerferencesParams1.setName("PAY_TRIGGER_ORDER_MAX_COUNT");
            TestDBUtility.insertWithDel(l_systemPerferencesParams1);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(HostPaymentOrderRow.TYPE);
            HostPaymentOrderParams l_hostPaymentOrderParams =
                this.getHostPaymentOrderRows();
            TestDBUtility.insertWithDel(l_hostPaymentOrderParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.marketadaptor.WEB3AioMarketRequestSenderServiceImpl",
                    "submitTrigger",
                    new Class[]{String.class, String.class},
                    null);
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.service.delegate.stdimpls.WEB3AioCashoutTradingPowerUnitServiceImpl",
                    "execute",
                    new Class[]{HostPaymentOrderParams.class,
                        String.class,
                        boolean.class,
                        String.class},
                        null);
            
            l_mpl.execute(l_request);
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    
    public static HostPaymentOrderParams getHostPaymentOrderRows()
    {
        HostPaymentOrderParams l_hostPaymentOrderParams = new HostPaymentOrderParams();
        l_hostPaymentOrderParams.setRequestCode("GI801");
        l_hostPaymentOrderParams.setInstitutionCode("0D");
        l_hostPaymentOrderParams.setBranchCode("624");
        l_hostPaymentOrderParams.setAccountCode("001");
        l_hostPaymentOrderParams.setOrderRequestNumber("002");
        l_hostPaymentOrderParams.setEstTransferDate(Calendar.getInstance().getTime());
        l_hostPaymentOrderParams.setStatus("3");
        return l_hostPaymentOrderParams;
    }
    
}
@
