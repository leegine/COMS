head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.54.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityPTSExecEndNotifyHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (PTS)株式出来終了通知ハンドラTest(WEB3EquityPTSExecEndNotifyHandlerTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/02/02 張騰宇(中訊)
*/
package webbroker3.equity.handler;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.equity.message.WEB3EquityPTSExecEndNotifyRequest;
import webbroker3.equity.message.WEB3EquityPTSExecEndNotifyResponse;
import webbroker3.equity.service.delegate.WEB3EquityPTSExecEndNotifyService;
import webbroker3.gentrade.data.OnlineRunStatusRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3EquityPTSExecEndNotifyHandlerTest extends TestBaseForMock
{
    /**
     *　@ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityPTSExecEndNotifyHandlerTest.class);

    public WEB3EquityPTSExecEndNotifyHandlerTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.gentrade.WEB3GentradeAccountManager", "lockAccount",
            new Class[] {String.class,String.class,String.class},
            null);
        Date l_datBizDate = WEB3DateUtility.getDate("20080130", "yyyyMMdd");
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
            "getSystemTimestamp",
            new Class[] {},
            new Timestamp(l_datBizDate.getTime()));
        ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                new Timestamp(l_datBizDate.getTime()));
        ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.attributes.basetimestampfororderbizdate",
                new Timestamp(l_datBizDate.getTime()));
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    WEB3EquityPTSExecEndNotifyService l_service = null;

    /*
     * Test method for 'webbroker3.equity.handler.WEB3EquityPTSExecEndNotifyHandler.completeNotify(WEB3EquityPTSExecEndNotifyRequest)'
     */
    public void testCompleteNotify_0001()
    {
        final String STR_METHOD_NAME = "testCompleteNotify_0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_service =
                (WEB3EquityPTSExecEndNotifyService)Services.getService(
                    WEB3EquityPTSExecEndNotifyService.class);
            WEB3EquityPTSExecEndNotifyHandler l_handler = new WEB3EquityPTSExecEndNotifyHandler();
            WEB3EquityPTSExecEndNotifyRequest l_request = new WEB3EquityPTSExecEndNotifyRequest();
            l_request.marketCode = "11";
            Services.unregisterService(WEB3EquityPTSExecEndNotifyService.class);
            WEB3EquityPTSExecEndNotifyResponse l_response = l_handler.completeNotify(l_request);
            assertEquals(l_response.errorInfo, WEB3ErrorCatalog.SYSTEM_ERROR_80002);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            Services.registerService(WEB3EquityPTSExecEndNotifyService.class, l_service);
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCompleteNotify_0002()
    {
        final String STR_METHOD_NAME = "testCompleteNotify_0002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3EquityPTSExecEndNotifyHandler l_handler = new WEB3EquityPTSExecEndNotifyHandler();
            WEB3EquityPTSExecEndNotifyRequest l_request = new WEB3EquityPTSExecEndNotifyRequest();
            l_request.marketCode = "11";
            WEB3EquityPTSExecEndNotifyResponse l_response = l_handler.completeNotify(l_request);
            assertEquals(l_response.errorInfo, WEB3ErrorCatalog.BUSINESS_ERROR_00827);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCompleteNotify_0003()
    {
        final String STR_METHOD_NAME = "testCompleteNotify_0003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.deleteAll(OnlineRunStatusRow.TYPE);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("11");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("11");
            l_marketParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_orderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            TestDBUtility.insertWithDel(l_orderUnitParams);
            
            
            WEB3EquityPTSExecEndNotifyHandler l_handler = new WEB3EquityPTSExecEndNotifyHandler();
            WEB3EquityPTSExecEndNotifyRequest l_request = new WEB3EquityPTSExecEndNotifyRequest();
            l_request.institutionCode = "0D";
            l_request.marketCode = "11";
            WEB3EquityPTSExecEndNotifyResponse l_response = l_handler.completeNotify(l_request);
            //正常結束 沒有例外
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(OnlineRunStatusRow.TYPE);
            }
            catch(Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                fail();
            }
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
