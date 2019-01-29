head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.20.30;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AsynAdminToManualExpireServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3AsynAdminToManualExpireServiceImplTest.java
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/07 関博 新規作成
*/
package webbroker3.triggerorder.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;

import test.util.TestDBUtility;

import webbroker3.admintriggerorder.service.delegate.stdimpls.WEB3AsynAdminToManualExpireServiceImpl;
import webbroker3.admintriggerorder.service.delegate.stdimpls.WEB3AsynAdminToManualExpireServiceImpl.WEB3AdminToManualExpireTransactionCallback;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.CalendarParams;
import webbroker3.gentrade.data.OrderAcceptStatusParams;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.ifo.WEB3FuturesOrderManagerImpl;
import webbroker3.ifo.data.HostFotypeOrderAllParams;
import webbroker3.ifo.data.HostFotypeOrderAllRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AsynAdminToManualExpireServiceImplTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AsynAdminToManualExpireServiceImplTest.class);
    


    public WEB3AsynAdminToManualExpireServiceImplTest(String arg0)
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

    public void testInvalidateOrder_C0001()
    {
        final String STR_METHOD_NAME = " testInvalidateOrder_C0001";
        log.entering(STR_METHOD_NAME);
        try
        {
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(l_ifoOrderUnitParams.getRowType());

//            l_ifoOrderUnitParams.setUnderlyingProductCode("0");
            l_ifoOrderUnitParams.setExecutedQuantity(200);
            l_ifoOrderUnitParams.setExecutedAmount(201);
            l_ifoOrderUnitParams.setAccountId(10100L);
            l_ifoOrderUnitParams.setOrderConditionType("1");
            l_ifoOrderUnitParams.setRequestType("1");
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(10100L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            HostFotypeOrderAllParams l_hostFotypeOrderParams1 = TestDBUtility.getHostFotypeOrderAllRow();
            l_processor.doDeleteAllQuery(l_hostFotypeOrderParams1.getRowType());
            l_hostFotypeOrderParams1.setAccountId(10100L);
            l_hostFotypeOrderParams1.setInstitutionCode("0D");
            l_hostFotypeOrderParams1.setBranchCode("381");
            l_hostFotypeOrderParams1.setAccountCode("2512246");
            l_hostFotypeOrderParams1.setOrderRequestNumber("000003006");
            l_hostFotypeOrderParams1.setOrderActionSerialNo(0);
            l_hostFotypeOrderParams1.setStatus("0");
            l_hostFotypeOrderParams1.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_hostFotypeOrderParams1);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_marketParams);

            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_productParams);

            IfoProductParams l_ifoProductParams1 = TestDBUtility.getIfoProductRow();
            l_ifoProductParams1.setProductId(1006169090018L);
            l_ifoProductParams1.setUnderlyingProductCode("1");
            l_ifoProductParams1.setProductType(ProductTypeEnum.IFO);
            l_ifoProductParams1.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_ifoProductParams1.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_ifoProductParams1);

            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            l_traderParams.setTraderId(6610L);
            TestDBUtility.insertWithDel(l_traderParams);

            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(Calendar.YEAR,2007);
            l_calendar.set(Calendar.MONDAY,01);
            l_calendar.set(Calendar.DAY_OF_YEAR,01);
            l_calendar.set(Calendar.HOUR_OF_DAY,0);
            l_calendar.set(Calendar.MINUTE,0);
            l_calendar.set(Calendar.SECOND,0);
            l_calendar.set(Calendar.MILLISECOND,0);
            CalendarParams l_calendarParams = new CalendarParams();
            l_calendarParams.setHoliday(new Timestamp(l_calendar.getTimeInMillis()));
            l_calendarParams.setBizDateType("0");
            TestDBUtility.insertWithDel(l_calendarParams);

            List l_list = Processors.getDefaultProcessor().doFindAllQuery(
                    HostFotypeOrderAllRow.TYPE,
                    "account_id = ?",
                    new Object[]{new Long(10100L)});
            l_hostFotypeOrderParams1 = (HostFotypeOrderAllParams)l_list.get(0);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
                    "getHostFotypeOrderAll",
                    new Class[]{IfoOrderUnit.class},
                    l_hostFotypeOrderParams1);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoCloseNotifyUnitServiceImpl",
                    "notifyClose",
                    new Class[] 
                    {OrderUnit.class,
                     double.class,
                     String.class,
                     String.class,
                     },
                    "notifyClose");

            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
            WEB3AsynAdminToManualExpireServiceImpl l_asynAdminToManualExpireServiceImpl = 
                new WEB3AsynAdminToManualExpireServiceImpl();
            WEB3AdminToManualExpireTransactionCallback l_impl =
                l_asynAdminToManualExpireServiceImpl.new WEB3AdminToManualExpireTransactionCallback();

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3FuturesOrderManagerImpl l_orderMgr =
                (WEB3FuturesOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
            long l_lngOrderUnitId = 1001;
            IfoOrderUnit l_orderUnit = (IfoOrderUnit) l_orderMgr.getOrderUnit(l_lngOrderUnitId);
            l_impl.orderUnit = l_orderUnit;
            l_impl.productType = ProductTypeEnum.IFO;

            WEB3GentradeTradingClendarContext l_context =
                new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setProductCode("1");
            l_context.setBizDateType("1");
            l_context.setMarketCode("0");
            l_context.setTradingTimeType("11");
            WEB3GentradeTradingTimeManagementForMock.setClendarContext(l_context);

//            l_context.setInstitutionCode("");
//            l_context.setBranchCode("");
//            String l_strMarketCodeForSet = l_context.getMarketCode();
//            String l_strProductCodeForSet = l_context.getProductCode();
//            String l_strTradetimeDivForSet = l_context.getTradingTimeType();
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context);
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(Calendar.getInstance().getTime());
            
            

            
            l_impl.invalidateOrder();
            
            l_list = Processors.getDefaultProcessor().doFindAllQuery(
                    HostFotypeOrderAllRow.TYPE,
                    "account_id = ?",
                    new Object[]{new Long(10100L)});
            assertEquals(1,((HostFotypeOrderAllParams)l_list.get(0)).getOrderActionSerialNo());
            assertEquals("4",((HostFotypeOrderAllParams)l_list.get(0)).getStatus());
            assertNotNull(((HostFotypeOrderAllParams)l_list.get(0)).getLastUpdatedTimestamp());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
