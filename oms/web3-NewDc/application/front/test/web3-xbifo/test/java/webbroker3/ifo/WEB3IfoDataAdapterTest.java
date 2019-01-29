head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.58.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoDataAdapterTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OPデータアダプタTest(WEB3IfoDataAdapterTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/06/14 張騰宇 (中訊) 新規作成
*/
package webbroker3.ifo;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3RequestTypeDef;
import webbroker3.common.define.WEB3SubmitMarketTriggerDef;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.BranchPreferencesParams;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3IfoDataAdapterTest extends TestBaseForMock
{

    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3IfoDataAdapterTest.class);

    public WEB3IfoDataAdapterTest(String arg0)
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
    public static String BASE_TIMESTAMP_FOR_ORDER_BIZ_DATE = "web3.attributes.basetimestampfororderbizdate";
    /*
     * Test method for 'webbroker3.ifo.WEB3IfoDataAdapter.getFirstOrderUnitId(String)'
     */
    public void testGetFirstOrderUnitIdCase1()
    {
        final String STR_METHOD_NAME = "testGetFirstOrderUnitIdCase1()";
        log.entering(STR_METHOD_NAME);

        try
        {
            Long l_firstOrderUnitId =
                WEB3IfoDataAdapter.getFirstOrderUnitId(WEB3OrderExpirationDateTypeDef.DAY_LIMIT);
            assertNull(l_firstOrderUnitId);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetFirstOrderUnitIdCase2()
    {
        final String STR_METHOD_NAME = "testGetFirstOrderUnitIdCase2()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Long l_firstOrderUnitId =
                WEB3IfoDataAdapter.getFirstOrderUnitId(WEB3OrderExpirationDateTypeDef.CARRIED_ORDER);
            assertEquals("0", l_firstOrderUnitId.toString());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetFirstOrderUnitIdCase3()
    {
        final String STR_METHOD_NAME = "testGetFirstOrderUnitIdCase3()";
        log.entering(STR_METHOD_NAME);

        try
        {
            Long l_firstOrderUnitId =
                WEB3IfoDataAdapter.getFirstOrderUnitId(WEB3OrderExpirationDateTypeDef.EVENING_SESSION_ORDER);
            assertNull(l_firstOrderUnitId);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /*
     * Test method for 'webbroker3.ifo.WEB3IfoDataAdapter.getEveningSessionCarryOverFlag(String, long)'
     */
    public void testGetEveningSessionCarryOverFlagCase1()
    {
        final String STR_METHOD_NAME = "testGetEveningSessionCarryOverFlagCase1()";
        log.entering(STR_METHOD_NAME);

        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        
        try
        {
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            boolean l_blnEveningSessionCarryOverFlag =
                WEB3IfoDataAdapter.getEveningSessionCarryOverFlag(
                    WEB3OrderExpirationDateTypeDef.DAY_LIMIT,
                    33381L);
            assertFalse(l_blnEveningSessionCarryOverFlag);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testGetEveningSessionCarryOverFlagCase2()
    {
        final String STR_METHOD_NAME = "testGetEveningSessionCarryOverFlagCase2()";
        log.entering(STR_METHOD_NAME);

        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        BranchPreferencesParams l_branchPreferencesParams =
            TestDBUtility.getBranchPreferencesRow();
        l_branchPreferencesParams.setBranchId(33381);
        l_branchPreferencesParams.setName("evening.session.div");
        l_branchPreferencesParams.setNameSerialNo(2);
        try
        {
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            boolean l_blnEveningSessionCarryOverFlag =
                WEB3IfoDataAdapter.getEveningSessionCarryOverFlag(
                    WEB3OrderExpirationDateTypeDef.CARRIED_ORDER,
                    33381L);
            assertTrue(l_blnEveningSessionCarryOverFlag);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetEveningSessionCarryOverFlagCase3()
    {
        final String STR_METHOD_NAME = "testGetEveningSessionCarryOverFlagCase3()";
        log.entering(STR_METHOD_NAME);

        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        BranchPreferencesParams l_branchPreferencesParams =
            TestDBUtility.getBranchPreferencesRow();
        
        
        try
        {
            TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            boolean l_blnEveningSessionCarryOverFlag =
                WEB3IfoDataAdapter.getEveningSessionCarryOverFlag(
                    WEB3OrderExpirationDateTypeDef.CARRIED_ORDER,
                    33381L);
            assertFalse(l_blnEveningSessionCarryOverFlag);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetEveningSessionCarryOverFlagCase4()
    {
        final String STR_METHOD_NAME = "testGetEveningSessionCarryOverFlagCase4()";
        log.entering(STR_METHOD_NAME);

        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        
        try
        {
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            boolean l_blnEveningSessionCarryOverFlag =
                WEB3IfoDataAdapter.getEveningSessionCarryOverFlag(
                    WEB3OrderExpirationDateTypeDef.EVENING_SESSION_ORDER,
                    33381L);
            assertTrue(l_blnEveningSessionCarryOverFlag);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetEveningSessionCarryOverFlagCase5()
    {
        final String STR_METHOD_NAME = "testGetEveningSessionCarryOverFlagCase5()";
        log.entering(STR_METHOD_NAME);

        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        
        try
        {
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            boolean l_blnEveningSessionCarryOverFlag =
                WEB3IfoDataAdapter.getEveningSessionCarryOverFlag(
                    "4",
                    33381L);
            assertFalse(l_blnEveningSessionCarryOverFlag);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetEveningSessionCarryOverFlagCase6()
    {
        final String STR_METHOD_NAME = "testGetEveningSessionCarryOverFlagCase6()";
        log.entering(STR_METHOD_NAME);

        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        
        try
        {
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            WEB3IfoDataAdapter.getEveningSessionCarryOverFlag(
                WEB3OrderExpirationDateTypeDef.DAY_LIMIT,
                33380L);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.SYSTEM_ERROR_80005);
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    /*
     * Test method for 'webbroker3.ifo.WEB3IfoDataAdapter.getExpirationDateType(IfoOrderUnit)'
     */
    public void testGetExpirationDateTypeCase1()
    {
        final String STR_METHOD_NAME = "testGetExpirationDateTypeCase1()";
        log.entering(STR_METHOD_NAME);
        
        IfoOrderUnitParams l_orderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        l_orderUnitParams.setFirstOrderUnitId(null);
        l_orderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.TRUE);
        try
        {
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.insertWithDel(l_orderUnitParams);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderManager =
                (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();
            IfoOrderUnit l_ifoOrderUnit = (IfoOrderUnit)l_orderManager.getOrderUnit(1001L);
            String l_strExpirationDateType =
            WEB3IfoDataAdapter.getExpirationDateType(l_ifoOrderUnit);
            assertEquals("3", l_strExpirationDateType);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetExpirationDateTypeCase2()
    {
        final String STR_METHOD_NAME = "testGetExpirationDateTypeCase2()";
        log.entering(STR_METHOD_NAME);
        
        IfoOrderUnitParams l_orderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        l_orderUnitParams.setFirstOrderUnitId(1001);
        l_orderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.TRUE);
        try
        {
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.insertWithDel(l_orderUnitParams);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderManager =
                (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();
            IfoOrderUnit l_ifoOrderUnit = (IfoOrderUnit)l_orderManager.getOrderUnit(1001L);
            String l_strExpirationDateType =
            WEB3IfoDataAdapter.getExpirationDateType(l_ifoOrderUnit);
            assertEquals("2", l_strExpirationDateType);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetExpirationDateTypeCase3()
    {
        final String STR_METHOD_NAME = "testGetExpirationDateTypeCase3()";
        log.entering(STR_METHOD_NAME);
        
        IfoOrderUnitParams l_orderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        l_orderUnitParams.setFirstOrderUnitId(null);
        l_orderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.FALSE);
        try
        {
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.insertWithDel(l_orderUnitParams);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderManager =
                (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();
            IfoOrderUnit l_ifoOrderUnit = (IfoOrderUnit)l_orderManager.getOrderUnit(1001L);
            String l_strExpirationDateType =
            WEB3IfoDataAdapter.getExpirationDateType(l_ifoOrderUnit);
            assertEquals("1", l_strExpirationDateType);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetExpirationDateTypeCase4()
    {
        final String STR_METHOD_NAME = "testGetExpirationDateTypeCase4()";
        log.entering(STR_METHOD_NAME);

        try
        {
            IfoOrderUnit l_ifoOrderUnit = null;
            WEB3IfoDataAdapter.getExpirationDateType(l_ifoOrderUnit);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.SYSTEM_ERROR_80017);
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /*
     * Test method for 'webbroker3.ifo.WEB3IfoDataAdapter.getDayTradeType(String)'
     */
    public void testGetDayTradeTypeCase1()
    {
        final String STR_METHOD_NAME = "testGetDayTradeTypeCase1()";
        log.entering(STR_METHOD_NAME);

        try
        {
            String l_strDayTradeType =
                WEB3IfoDataAdapter.getDayTradeType(null);
            assertEquals("1", l_strDayTradeType);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetDayTradeTypeCase2()
    {
        final String STR_METHOD_NAME = "testGetDayTradeTypeCase2()";
        log.entering(STR_METHOD_NAME);

        try
        {
            String l_strDayTradeType =
                WEB3IfoDataAdapter.getDayTradeType("5");
            assertEquals("5", l_strDayTradeType);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    
    public void testGetOrderStatusTypeCase1()
    {
        final String STR_METHOD_NAME = "testGetOrderStatusTypeCase1()";
        log.entering(STR_METHOD_NAME);
        IfoOrderUnitParams l_orderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        l_orderUnitParams.setFirstOrderUnitId(null);
        l_orderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.TRUE);
        l_orderUnitParams.setExpirationDate(WEB3DateUtility.getDate("21100615","yyyyMMdd"));
        l_orderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
        l_orderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.EXPIRED);
        l_orderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.ACCEPT_ERROR);
        try
        {
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.insertWithDel(l_orderUnitParams);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderManager =
                (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();
            IfoOrderUnit l_ifoOrderUnit = (IfoOrderUnit)l_orderManager.getOrderUnit(1001L);

            String l_strOrderStatusType =
                WEB3IfoDataAdapter.getOrderStatusType(l_ifoOrderUnit);
            // 51 : 繰越失敗
            assertEquals("51", l_strOrderStatusType);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetOrderStatusTypeCase2()
    {
        final String STR_METHOD_NAME = "testGetOrderStatusTypeCase2()";
        log.entering(STR_METHOD_NAME);
        IfoOrderUnitParams l_orderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        l_orderUnitParams.setFirstOrderUnitId(null);
        l_orderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.FALSE);
        l_orderUnitParams.setExpirationDate(WEB3DateUtility.getDate("20100615","yyyyMMdd"));
        l_orderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
        l_orderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.EXPIRED);
        l_orderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.ACCEPT_ERROR);
        try
        {
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.insertWithDel(l_orderUnitParams);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderManager =
                (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();
            IfoOrderUnit l_ifoOrderUnit = (IfoOrderUnit)l_orderManager.getOrderUnit(1001L);

            String l_strOrderStatusType =
                WEB3IfoDataAdapter.getOrderStatusType(l_ifoOrderUnit);
            
            assertEquals("22", l_strOrderStatusType);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetOrderStatusTypeCase3()
    {
        final String STR_METHOD_NAME = "testGetOrderStatusTypeCase3()";
        log.entering(STR_METHOD_NAME);
        IfoOrderUnitParams l_orderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        l_orderUnitParams.setFirstOrderUnitId(null);
        l_orderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.TRUE);
        l_orderUnitParams.setExpirationDate(WEB3DateUtility.getDate("20050615","yyyyMMdd"));
        l_orderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
        l_orderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.EXPIRED);
        l_orderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.ACCEPT_ERROR);
        try
        {
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.insertWithDel(l_orderUnitParams);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderManager =
                (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();
            IfoOrderUnit l_ifoOrderUnit = (IfoOrderUnit)l_orderManager.getOrderUnit(1001L);

            String l_strOrderStatusType =
                WEB3IfoDataAdapter.getOrderStatusType(l_ifoOrderUnit);
            assertEquals("22", l_strOrderStatusType);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetAcceptTypeCase1()
    {
        final String STR_METHOD_NAME = "testGetAcceptTypeCase1()";
        log.entering(STR_METHOD_NAME);
        IfoOrderUnitParams l_orderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        l_orderUnitParams.setFirstOrderUnitId(null);
        l_orderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.TRUE);
        l_orderUnitParams.setExpirationDate(WEB3DateUtility.getDate("20050615","yyyyMMdd"));
        l_orderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
        l_orderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.EXPIRED);
        l_orderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.ACCEPT_ERROR);
        l_orderUnitParams.setSessionType("1");
        l_orderUnitParams.setOrderConditionType(WEB3OrderingConditionDef.STOP_LIMIT_PRICE);
        l_orderUnitParams.setRequestType(WEB3RequestTypeDef.DEFAULT);
        l_orderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setInstitutionCode("0D");
        l_tradingTimeParams.setBranchCode("123");
        l_tradingTimeParams.setMarketCode("N1");
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setProductCode("0");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setSessionType("1");
        l_tradingTimeParams.setSubmitMarketTrigger(WEB3SubmitMarketTriggerDef.TRIGGER);
        try
        {
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.insertWithDel(l_orderUnitParams);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            Calendar ca =  Calendar.getInstance();
            ca.set(2007,6-1,19);
            
            Date date = ca.getTime();
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(date);
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderManager =
                (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();
            IfoOrderUnit l_ifoOrderUnit = (IfoOrderUnit)l_orderManager.getOrderUnit(1001L);

            String l_strAcceptType =
                WEB3IfoDataAdapter.getAcceptType(l_ifoOrderUnit);
            assertEquals("4", l_strAcceptType);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    //注文単位.立会区分 !＝ 取引時間管理.立会区分の場合
    public void testGetAcceptTypeCase2()
    {
        final String STR_METHOD_NAME = "testGetAcceptTypeCase2()";
        log.entering(STR_METHOD_NAME);
        IfoOrderUnitParams l_orderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        l_orderUnitParams.setFirstOrderUnitId(null);
        l_orderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.TRUE);
        l_orderUnitParams.setExpirationDate(WEB3DateUtility.getDate("20050615","yyyyMMdd"));
        l_orderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
        l_orderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.EXPIRED);
        l_orderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.ACCEPT_ERROR);
        l_orderUnitParams.setSessionType("1");
        l_orderUnitParams.setOrderConditionType(WEB3OrderingConditionDef.STOP_LIMIT_PRICE);
        l_orderUnitParams.setRequestType(WEB3RequestTypeDef.DEFAULT);
        l_orderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setInstitutionCode("0D");
        l_tradingTimeParams.setBranchCode("123");
        l_tradingTimeParams.setMarketCode("N1");
        l_tradingTimeParams.setTradingTimeType("03");
        l_tradingTimeParams.setProductCode("0");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setSessionType("1");
        l_tradingTimeParams.setSubmitMarketTrigger(WEB3SubmitMarketTriggerDef.TRIGGER);
        try
        {
            WEB3GentradeTradingTimeManagementForMock.resetTradingTimeType("03");
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.insertWithDel(l_orderUnitParams);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            Calendar ca =  Calendar.getInstance();
            ca.set(2007,6-1,19);
            
            Date date = ca.getTime();
            this.setExpectedDate(date, "2");
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderManager =
                (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();
            IfoOrderUnit l_ifoOrderUnit = (IfoOrderUnit)l_orderManager.getOrderUnit(1001L);

            String l_strAcceptType =
                WEB3IfoDataAdapter.getAcceptType(l_ifoOrderUnit);
            assertEquals("0", l_strAcceptType);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    //注文単位.立会区分 ＝ 取引時間管理.立会区分の場合
    public void testGetAcceptTypeCase3()
    {
        final String STR_METHOD_NAME = "testGetAcceptTypeCase3()";
        log.entering(STR_METHOD_NAME);
        IfoOrderUnitParams l_orderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        l_orderUnitParams.setFirstOrderUnitId(null);
        l_orderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.TRUE);
        l_orderUnitParams.setExpirationDate(WEB3DateUtility.getDate("20050615","yyyyMMdd"));
        l_orderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
        l_orderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.EXPIRED);
        l_orderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.ACCEPT_ERROR);
        l_orderUnitParams.setSessionType("1");
        l_orderUnitParams.setOrderConditionType(WEB3OrderingConditionDef.STOP_LIMIT_PRICE);
        l_orderUnitParams.setRequestType(WEB3RequestTypeDef.DEFAULT);
        l_orderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
        l_orderUnitParams.setBizDate("20070619");
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setInstitutionCode("0D");
        l_tradingTimeParams.setBranchCode("123");
        l_tradingTimeParams.setMarketCode("N1");
        l_tradingTimeParams.setTradingTimeType("03");
        l_tradingTimeParams.setProductCode("0");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setSessionType("1");
        l_tradingTimeParams.setSubmitMarketTrigger(WEB3SubmitMarketTriggerDef.TRIGGER);
        try
        {
            WEB3GentradeTradingTimeManagementForMock.resetTradingTimeType("03");
            TestDBUtility.deleteAllAndCommit(TradingTimeRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_tradingTimeParams);
            TestDBUtility.deleteAllAndCommit(IfoOrderUnitRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_orderUnitParams);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            Calendar ca =  Calendar.getInstance();
            ca.set(2007,6-1,19);
            
            Date date = ca.getTime();
            this.setExpectedDate(date, "1");
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderManager =
                (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();
            IfoOrderUnit l_ifoOrderUnit = (IfoOrderUnit)l_orderManager.getOrderUnit(1001L);

            String l_strAcceptType =
                WEB3IfoDataAdapter.getAcceptType(l_ifoOrderUnit);
            assertEquals("7", l_strAcceptType);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    ///////////////////////////////////////////////////////////////////////////////
    //二次
    //get夕場前繰越対象フラグ（PR層）
    public void testGetEveningSessionCarryOverFlagPrCase1()
    {
        final String STR_METHOD_NAME = "testGetEveningSessionCarryOverFlagPrCase1()";
        log.entering(STR_METHOD_NAME);
        IfoOrderUnitParams l_orderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        l_orderUnitParams.setFirstOrderUnitId(null);
        l_orderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.FALSE);
        try
        {
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.insertWithDel(l_orderUnitParams);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderManager =
                (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();
            IfoOrderUnit l_ifoOrderUnit = (IfoOrderUnit)l_orderManager.getOrderUnit(1001L);
            boolean l_blnEveningSessionCarryOverFlag =
                WEB3IfoDataAdapter.getEveningSessionCarryOverFlagPr(l_ifoOrderUnit);
            assertFalse(l_blnEveningSessionCarryOverFlag);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetEveningSessionCarryOverFlagPrCase2()
    {
        final String STR_METHOD_NAME = "testGetEveningSessionCarryOverFlagPrCase2()";
        log.entering(STR_METHOD_NAME);
        IfoOrderUnitParams l_orderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        l_orderUnitParams.setFirstOrderUnitId(null);
        l_orderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.TRUE);
        try
        {
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.insertWithDel(l_orderUnitParams);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderManager =
                (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();
            IfoOrderUnit l_ifoOrderUnit = (IfoOrderUnit)l_orderManager.getOrderUnit(1001L);
            boolean l_blnEveningSessionCarryOverFlag =
                WEB3IfoDataAdapter.getEveningSessionCarryOverFlagPr(l_ifoOrderUnit);
            assertTrue(l_blnEveningSessionCarryOverFlag);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //モデル759
    public void testGetWLimitExecutionConditionTypeListCase1()
    {
        final String STR_METHOD_NAME = "testGetWLimitExecutionConditionTypeListCase1()";
        log.entering(STR_METHOD_NAME);
        IfoOrderUnitParams l_orderUnitParams = TestDBUtility.getIfoOrderUnitRow();
//        l_orderUnitParams.setFirstOrderUnitId(null);
        l_orderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.TRUE);
        l_orderUnitParams.setOrderConditionType("2");
        l_orderUnitParams.setFirstOrderUnitId(1);
        try
        {
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.insertWithDel(l_orderUnitParams);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "isCarriedOrderUnit", new Class[]
                    { IfoOrderUnit.class },
                    new Boolean(true));
                    
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderManager =
                (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();
            IfoOrderUnit l_ifoOrderUnit = (IfoOrderUnit)l_orderManager.getOrderUnit(1001L);
            String[] l_strExecutionConditionTypeList = new String[1];
            String l_str1 = "1";
            l_strExecutionConditionTypeList[0] = l_str1;
            String[] l_str =
                WEB3IfoDataAdapter.getWLimitExecutionConditionTypeList(
                    l_strExecutionConditionTypeList,
                    l_ifoOrderUnit);
            assertEquals(l_str[0],"1");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetWLimitExecutionConditionTypeListCase2()
    {
        final String STR_METHOD_NAME = "testGetWLimitExecutionConditionTypeListCase2()";
        log.entering(STR_METHOD_NAME);
        IfoOrderUnitParams l_orderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        l_orderUnitParams.setFirstOrderUnitId(null);
        l_orderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.TRUE);
        l_orderUnitParams.setOrderConditionType("2");
//        l_orderUnitParams.setFirstOrderUnitId(1);
        try
        {
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.insertWithDel(l_orderUnitParams);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderManager =
                (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();
            IfoOrderUnit l_ifoOrderUnit = (IfoOrderUnit)l_orderManager.getOrderUnit(1001L);
            String[] l_strExecutionConditionTypeList = new String[1];
            String l_str1 = "1";
            l_strExecutionConditionTypeList[0] = l_str1;
            String[] l_str =
                WEB3IfoDataAdapter.getWLimitExecutionConditionTypeList(
                    l_strExecutionConditionTypeList,
                    l_ifoOrderUnit);
            assertEquals(l_str[0],"1");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetWLimitExecutionConditionTypeListCase3()
    {
        final String STR_METHOD_NAME = "testGetWLimitExecutionConditionTypeListCase3()";
        log.entering(STR_METHOD_NAME);
        IfoOrderUnitParams l_orderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        l_orderUnitParams.setFirstOrderUnitId(null);
        l_orderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.FALSE);
        l_orderUnitParams.setOrderConditionType("2");
//        l_orderUnitParams.setFirstOrderUnitId(1);
        try
        {
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.insertWithDel(l_orderUnitParams);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderManager =
                (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();
            IfoOrderUnit l_ifoOrderUnit = (IfoOrderUnit)l_orderManager.getOrderUnit(1001L);
            String[] l_strExecutionConditionTypeList = new String[1];
            String l_str1 = "7";
            l_strExecutionConditionTypeList[0] = l_str1;
            String[] l_str =
                WEB3IfoDataAdapter.getWLimitExecutionConditionTypeList(
                    l_strExecutionConditionTypeList,
                    l_ifoOrderUnit);
            assertEquals(l_str[0],"1");
            assertEquals(l_str[1],"7");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    private void setExpectedDate(Date l_expectDate,String l_sessionType)
    {
        final String STR_METHOD_NAME = "setExpectedDate()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(new Timestamp(l_expectDate.getTime()), "1");
            
            TradingTimeParams l_tradingTimeParams = new TradingTimeParams();

            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("03");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setStartTime("000000");
            l_tradingTimeParams.setEndTime("235959");        
            l_tradingTimeParams.setSubmitMarketTrigger("0");
            l_tradingTimeParams.setEnableOrder("0");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            l_tradingTimeParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_tradingTimeParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_tradingTimeParams.setSessionType(l_sessionType);
            
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                BASE_TIMESTAMP_FOR_ORDER_BIZ_DATE, new Timestamp(l_expectDate.getTime()));   
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
