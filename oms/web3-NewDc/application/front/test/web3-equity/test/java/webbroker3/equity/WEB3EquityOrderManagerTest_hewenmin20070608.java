head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.13.50;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityOrderManagerTest_hewenmin20070608.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 拡張株式注文マネージャ(WEB3EquityOrderManagerTest_hewenmin20070608.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/06/08 何文敏
*/
package webbroker3.equity;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;

import test.util.TestDBUtility;

import webbroker3.equity.message.WEB3MarginCloseMarginContractUnit;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * WEB3EquityOrderManagerTest_hewenmin20070608
 * @@author 何文敏
 *
 */
public class WEB3EquityOrderManagerTest_hewenmin20070608 extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityOrderManagerTest_hewenmin20070608.class);
    WEB3EquityOrderManager l_orderMgr = new WEB3EquityOrderManager();
    public WEB3EquityOrderManagerTest_hewenmin20070608(String arg0)
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

    public void testCreateClosingContractEntry()
    {
        final String STR_METHOD_NAME = "testCreateClosingContractEntry()";
        log.entering(STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setProductCode("N8080");
            l_context.setBizDateType("1");
            l_context.setMarketCode("1");
            l_context.setTradingTimeType("01");
            WEB3GentradeTradingTimeManagementForMock.setClendarContext(l_context);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            
            l_tradingTimeParams.setInstitutionCode(l_context.getInstitutionCode());
            l_tradingTimeParams.setBranchCode(l_context.getBranchCode());
            l_tradingTimeParams.setBizDateType(l_context.getBizDateType());
            l_tradingTimeParams.setMarketCode(l_context.getMarketCode());
            l_tradingTimeParams.setProductCode(l_context.getProductCode());
            l_tradingTimeParams.setTradingTimeType(l_context.getTradingTimeType());
            l_tradingTimeParams.setSessionType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            Date l_datExpect = WEB3DateUtility.getDate("20040710","yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TestDBUtility.deleteAll(EqtypeContractRow.TYPE);
            EqtypeContractParams l_eqtypeContractParams = TestDBUtility.getEqtypeContractRow();
            TestDBUtility.insertWithDel(l_eqtypeContractParams);

            long l_lngOrderUnitId = 123456789L;
            WEB3MarginCloseMarginContractUnit l_unit = new WEB3MarginCloseMarginContractUnit();
            l_unit.id = "2134566345";
            l_unit.orderQuantity = "100";
            l_unit.settlePriority = "123";
            
            WEB3MarginCloseMarginContractUnit[]  l_closeMarginContractUnits =
                new WEB3MarginCloseMarginContractUnit[]{l_unit};
            boolean l_isSkipCloseDateCheck = false;
            l_orderMgr.createClosingContractEntry(l_lngOrderUnitId, l_closeMarginContractUnits, l_isSkipCloseDateCheck);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
