head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.11.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminMutualDisplayOrderServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.mf.service.delegate.stdimpls;

import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImplForMock;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketCalendarParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketCalendarRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.CalendarUtils;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mf.WEB3MutualFundTradingTimeManagementForMock;
import webbroker3.mf.message.WEB3AdminMutualDisplayOrderInputRequest;
import webbroker3.mf.message.WEB3AdminMutualDisplayOrderInputResponse;
import webbroker3.mf.message.WEB3MutualSortKey;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * 
 * @@author tang-xingfeng
 *
 */
public class WEB3AdminMutualDisplayOrderServiceImplTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminMutualDisplayOrderServiceImplTest.class);
    WEB3AdminMutualDisplayOrderServiceImpl impl =
        new WEB3AdminMutualDisplayOrderServiceImpl();

    public WEB3AdminMutualDisplayOrderServiceImplTest(String arg0)
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

    public class WEB3AdminMutualDisplayOrderInputRequestForMock
        extends WEB3AdminMutualDisplayOrderInputRequest
    {
        public void validate()
        {
            final String STR_METHOD_NAME = "validate()";
            log.entering(STR_METHOD_NAME);
            
            log.exiting(STR_METHOD_NAME);
        }
    }

    /*
     * Test method for 'webbroker3.mf.service.delegate.stdimpls.WEB3AdminMutualDisplayOrderServiceImpl.createMutualDisplayOrderInput(WEB3AdminMutualDisplayOrderInputRequest)'
     */

    public void testCreateMutualDisplayOrderInput_case1()
    {
        final String STR_METHOD_NAME = "testCreateMutualDisplayOrderInput_case1()";
        log.entering(STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getLoginInfo",
            new Class[] {},
            l_loginInfoImplForMock);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
            "getLoginId",
            new Class[] {},
            new Long(123456l));

        try
        {
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setAdministratorCode("123456789");
            l_administratorParams.setAdministratorId(123456l);
            l_administratorParams.setBranchCode("624");
            l_administratorParams.setInstitutionCode("60");
            l_administratorParams.setInstitutionId(60L);
            l_administratorParams.setLoginId(123456l);
            l_administratorParams.setPermissionLevel("01");

            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setSystemHandlingDiv("2");
            l_mutualFundProductParams.setFundType(MutualFundTypeEnum.DOMESTIC);
            l_mutualFundProductParams.setInstitutionCode("60");

            TradingTimeParams l_tradingTimeParams =
                TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0001000");
            l_tradingTimeParams.setBizDateType("1");
            
            TestDBUtility.deleteAllAndCommit(AdministratorRow.TYPE);
            TestDBUtility.deleteAllAndCommit(MutualFundProductRow.TYPE);
            TestDBUtility.deleteAllAndCommit(TradingTimeRow.TYPE);

            TestDBUtility.insertWithDelAndCommit(l_administratorParams);
            TestDBUtility.insertWithDelAndCommit(l_mutualFundProductParams);
            TestDBUtility.insertWithDelAndCommit(l_tradingTimeParams);

            MarketCalendarParams l_marketCalendarParams = new MarketCalendarParams();
            l_marketCalendarParams.setMarketId(3303L);

            l_marketCalendarParams.setTradeDate(CalendarUtils.clearTimeFields(GtlUtils.getSystemTimestamp()));
                l_marketCalendarParams.setHolidayFlag(BooleanEnum.TRUE);
                l_marketCalendarParams.setTradeOpenTime("090000");
                l_marketCalendarParams.setTradeCloseTime("150000");
    
            l_marketCalendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            
            l_marketCalendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            
            TestDBUtility.deleteAll(MarketCalendarRow.TYPE);
            TestDBUtility.insertWithDel(l_marketCalendarParams);
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
            WEB3MutualFundTradingTimeManagementForMock.mockGetOrderCloseTime("101010");
            
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorParams);
            
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet, "C0201", false, true);

            WEB3MutualSortKey l_mutualSortKeysTemps = new WEB3MutualSortKey();
            l_mutualSortKeysTemps.keyItem = "displayOrder";
            l_mutualSortKeysTemps.ascDesc = "A";
            
            WEB3MutualSortKey[] l_mutualSortKeys = {l_mutualSortKeysTemps};

            WEB3AdminMutualDisplayOrderInputRequestForMock l_request =
                new WEB3AdminMutualDisplayOrderInputRequestForMock();
            l_request.mutualFrgnMmfDisplayDiv = "0";
            l_request.sortKeys = l_mutualSortKeys;

            WEB3AdminMutualDisplayOrderInputResponse l_response =
                impl.createMutualDisplayOrderInput(l_request);
            assertEquals(l_response.displayOrderGroups[0].categoryCode1, null);
            assertEquals(l_response.displayOrderGroups[0].categoryName1, null);
            assertEquals(l_response.displayOrderGroups[0].categoryCode2, null);
            assertEquals(l_response.displayOrderGroups[0].categoryName2, null);
            assertEquals(l_response.displayOrderGroups[0].categoryCode3, null);
            assertEquals(l_response.displayOrderGroups[0].categoryName3, null);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testCreateMutualDisplayOrderInput_case2()
    {
        final String STR_METHOD_NAME = "testCreateMutualDisplayOrderInput_case2()";
        log.entering(STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getLoginInfo",
            new Class[] {},
            l_loginInfoImplForMock);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
            "getLoginId",
            new Class[] {},
            new Long(123456l));

        try
        {
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setAdministratorCode("123456789");
            l_administratorParams.setAdministratorId(123456l);
            l_administratorParams.setBranchCode("624");
            l_administratorParams.setInstitutionCode("60");
            l_administratorParams.setInstitutionId(60L);
            l_administratorParams.setLoginId(123456l);
            l_administratorParams.setPermissionLevel("01");

            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setSystemHandlingDiv("2");
            l_mutualFundProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
            l_mutualFundProductParams.setInstitutionCode("60");

            TradingTimeParams l_tradingTimeParams =
                TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0001000");
            l_tradingTimeParams.setBizDateType("1");
            
            TestDBUtility.deleteAllAndCommit(AdministratorRow.TYPE);
            TestDBUtility.deleteAllAndCommit(MutualFundProductRow.TYPE);
            TestDBUtility.deleteAllAndCommit(TradingTimeRow.TYPE);

            TestDBUtility.insertWithDelAndCommit(l_administratorParams);
            TestDBUtility.insertWithDelAndCommit(l_mutualFundProductParams);
            TestDBUtility.insertWithDelAndCommit(l_tradingTimeParams);

            MarketCalendarParams l_marketCalendarParams = new MarketCalendarParams();
            l_marketCalendarParams.setMarketId(3303L);

            l_marketCalendarParams.setTradeDate(CalendarUtils.clearTimeFields(GtlUtils.getSystemTimestamp()));
                l_marketCalendarParams.setHolidayFlag(BooleanEnum.TRUE);
                l_marketCalendarParams.setTradeOpenTime("090000");
                l_marketCalendarParams.setTradeCloseTime("150000");
    
            l_marketCalendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            
            l_marketCalendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
            WEB3MutualFundTradingTimeManagementForMock.mockGetOrderCloseTime("101010");

            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorParams);
            
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet, "C0201", false, true);

            WEB3MutualSortKey l_mutualSortKeysTemps = new WEB3MutualSortKey();
            l_mutualSortKeysTemps.keyItem = "displayOrder";
            l_mutualSortKeysTemps.ascDesc = "A";
            
            WEB3MutualSortKey[] l_mutualSortKeys = {l_mutualSortKeysTemps};

            WEB3AdminMutualDisplayOrderInputRequestForMock l_request =
                new WEB3AdminMutualDisplayOrderInputRequestForMock();
            l_request.mutualFrgnMmfDisplayDiv = "1";
            l_request.sortKeys = l_mutualSortKeys;

            WEB3AdminMutualDisplayOrderInputResponse l_response =
                impl.createMutualDisplayOrderInput(l_request);
            assertEquals(l_response.displayOrderGroups[0].categoryCode1, null);
            assertEquals(l_response.displayOrderGroups[0].categoryName1, null);
            assertEquals(l_response.displayOrderGroups[0].categoryCode2, null);
            assertEquals(l_response.displayOrderGroups[0].categoryName2, null);
            assertEquals(l_response.displayOrderGroups[0].categoryCode3, null);
            assertEquals(l_response.displayOrderGroups[0].categoryName3, null);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testCreateMutualDisplayOrderInput_case3()
    {
        final String STR_METHOD_NAME = "testCreateMutualDisplayOrderInput_case3()";
        log.entering(STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getLoginInfo",
            new Class[] {},
            l_loginInfoImplForMock);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
            "getLoginId",
            new Class[] {},
            new Long(123456l));

        try
        {
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setAdministratorCode("123456789");
            l_administratorParams.setAdministratorId(123456l);
            l_administratorParams.setBranchCode("624");
            l_administratorParams.setInstitutionCode("60");
            l_administratorParams.setInstitutionId(60L);
            l_administratorParams.setLoginId(123456l);
            l_administratorParams.setPermissionLevel("01");

            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setSystemHandlingDiv("2");
            l_mutualFundProductParams.setFundType(MutualFundTypeEnum.DOMESTIC);
            l_mutualFundProductParams.setInstitutionCode("60");

            TradingTimeParams l_tradingTimeParams =
                TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0001000");
            l_tradingTimeParams.setBizDateType("1");
            
            TestDBUtility.deleteAllAndCommit(AdministratorRow.TYPE);
            TestDBUtility.deleteAllAndCommit(MutualFundProductRow.TYPE);
            TestDBUtility.deleteAllAndCommit(TradingTimeRow.TYPE);

            TestDBUtility.insertWithDelAndCommit(l_administratorParams);
            TestDBUtility.insertWithDelAndCommit(l_mutualFundProductParams);
            TestDBUtility.insertWithDelAndCommit(l_tradingTimeParams);

            MarketCalendarParams l_marketCalendarParams = new MarketCalendarParams();
            l_marketCalendarParams.setMarketId(3303L);

            l_marketCalendarParams.setTradeDate(CalendarUtils.clearTimeFields(GtlUtils.getSystemTimestamp()));
                l_marketCalendarParams.setHolidayFlag(BooleanEnum.TRUE);
                l_marketCalendarParams.setTradeOpenTime("090000");
                l_marketCalendarParams.setTradeCloseTime("150000");
    
            l_marketCalendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            
            l_marketCalendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
            WEB3MutualFundTradingTimeManagementForMock.mockGetOrderCloseTime("101010");
            
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorParams);
            
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet, "C0201", false, true);

            WEB3MutualSortKey l_mutualSortKeysTemps = new WEB3MutualSortKey();
            l_mutualSortKeysTemps.keyItem = "displayOrder";
            l_mutualSortKeysTemps.ascDesc = "A";
            
            WEB3MutualSortKey[] l_mutualSortKeys = {l_mutualSortKeysTemps};

            WEB3AdminMutualDisplayOrderInputRequestForMock l_request =
                new WEB3AdminMutualDisplayOrderInputRequestForMock();
            l_request.mutualFrgnMmfDisplayDiv = "2";
            l_request.sortKeys = l_mutualSortKeys;

            WEB3AdminMutualDisplayOrderInputResponse l_response =
                impl.createMutualDisplayOrderInput(l_request);
            assertEquals(l_response.displayOrderGroups[0].categoryCode1, null);
            assertEquals(l_response.displayOrderGroups[0].categoryName1, null);
            assertEquals(l_response.displayOrderGroups[0].categoryCode2, null);
            assertEquals(l_response.displayOrderGroups[0].categoryName2, null);
            assertEquals(l_response.displayOrderGroups[0].categoryCode3, null);
            assertEquals(l_response.displayOrderGroups[0].categoryName3, null);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testCreateMutualDisplayOrderInput_case4()
    {
        final String STR_METHOD_NAME = "testCreateMutualDisplayOrderInput_case4()";
        log.entering(STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getLoginInfo",
            new Class[] {},
            l_loginInfoImplForMock);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
            "getLoginId",
            new Class[] {},
            new Long(123456l));

        try
        {
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setAdministratorCode("123456789");
            l_administratorParams.setAdministratorId(123456l);
            l_administratorParams.setBranchCode("624");
            l_administratorParams.setInstitutionCode("60");
            l_administratorParams.setInstitutionId(60L);
            l_administratorParams.setLoginId(123456l);
            l_administratorParams.setPermissionLevel("01");

            TestDBUtility.deleteAllAndCommit(AdministratorRow.TYPE);
            TestDBUtility.deleteAllAndCommit(MutualFundProductRow.TYPE);
            TestDBUtility.deleteAllAndCommit(TradingTimeRow.TYPE);

            TestDBUtility.insertWithDelAndCommit(l_administratorParams);

            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorParams);
            
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet, "C0201", false, true);

            WEB3AdminMutualDisplayOrderInputRequestForMock l_request =
                new WEB3AdminMutualDisplayOrderInputRequestForMock();

            impl.createMutualDisplayOrderInput(l_request);
            fail();
        }
        catch (WEB3BusinessLayerException l_blex)
        {
            assertEquals(l_blex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_01279);
            log.error(l_blex.getMessage(), l_blex);
            log.info("test pass----------------------------ok!");
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
