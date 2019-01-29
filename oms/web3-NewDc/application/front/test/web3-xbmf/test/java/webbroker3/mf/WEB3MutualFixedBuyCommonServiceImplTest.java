head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.15.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MutualFixedBuyCommonServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （WEB3MutualFixedBuyCommonServiceImplTest.java）
Author Name      : Daiwa Institute of Research
Revision History : 2008/07/17 武波 (中訊) 新規作成
*/
package webbroker3.mf;

import java.util.List;

import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3BizDateCalcParameterDef;
import webbroker3.common.define.WEB3BizDateTypeDef;
import webbroker3.common.define.WEB3EnableOrderDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3SystemPreferencesNameDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.mf.data.MfFixedBuyingChangeParams;
import webbroker3.mf.data.MfFixedBuyingCondParams;
import webbroker3.mf.data.MfFixedBuyingDrawAccountParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3MutualFixedBuyCommonServiceImplTest extends TestBaseForMock
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualFixedBuyCommonServiceImplTest.class);
    public WEB3MutualFixedBuyCommonServiceImplTest(String arg0)
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

    WEB3MutualFixedBuyCommonServiceImpl l_impl =
        new WEB3MutualFixedBuyCommonServiceImpl();
    public void testGetFixedBuyConditionChangeList_0001()
    {
        final String STR_METHOD_NAME = "testGetFixedBuyConditionChangeList_0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(MfFixedBuyingChangeParams.TYPE);
            List l_lisFixedBuyConditionChangeLists =
                l_impl.getFixedBuyConditionChangeList(
                    "0D", "381", "111111", null, null);
            
            assertEquals(l_lisFixedBuyConditionChangeLists.size(), 0);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testGetFixedBuyConditionChangeList_0002()
    {
        final String STR_METHOD_NAME = "testGetFixedBuyConditionChangeList_0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            String l_strMfFixedBuyingChangeQuery =
                " product_code = ? ";
            TestDBUtility.deleteAll(MfFixedBuyingChangeParams.TYPE);
            MfFixedBuyingChangeParams l_mfFixedBuyingChangeParams =
                TestDBUtility.getMfFixedBuyingChangeRow();
            l_mfFixedBuyingChangeParams.setDeleteFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_mfFixedBuyingChangeParams);
            List l_lisFixedBuyConditionChangeLists =
                l_impl.getFixedBuyConditionChangeList(
                    "0D", "381", "111111",
                    l_strMfFixedBuyingChangeQuery,
                    new Object[]{"1"});

            assertEquals(l_lisFixedBuyConditionChangeLists.size(), 1);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testCalcValidStartDateOrderBizdate_0001()
    {
        final String STR_METHOD_NAME = "testCalcValidStartDateOrderBizdate_0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD);
            l_systemPreferencesParams.setValue("11");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);

            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT);
            l_systemPreferencesParams.setValue("11");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);

            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD_BONUS);
            l_systemPreferencesParams.setValue("11");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);

            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT_BONUS);
            l_systemPreferencesParams.setValue("11");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams =
                TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_tradingTimeParams.setTradingTimeType(WEB3TradingTimeTypeDef.MF_FIXED_BUYING);
            l_tradingTimeParams.setProductCode(WEB3ProductCodeDef.DEFAULT);
            l_tradingTimeParams.setBizDateType(WEB3BizDateTypeDef.BIZ_DATE);
            l_tradingTimeParams.setEnableOrder(WEB3EnableOrderDef.ENABLE);
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            l_impl.calcValidStartDateOrderBizdate("0D", "381");
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testCalcValidStartDateCurrentDate_0001()
    {
        final String STR_METHOD_NAME = "testCalcValidStartDateOrderBizdate_0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD);
            l_systemPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);

            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT);
            l_systemPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);

            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD_BONUS);
            l_systemPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);

            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT_BONUS);
            l_systemPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams =
                TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_tradingTimeParams.setTradingTimeType(WEB3TradingTimeTypeDef.MF_FIXED_BUYING);
            l_tradingTimeParams.setProductCode(WEB3ProductCodeDef.DEFAULT);
            l_tradingTimeParams.setBizDateType(WEB3BizDateTypeDef.BIZ_DATE);
            l_tradingTimeParams.setEnableOrder(WEB3EnableOrderDef.ENABLE);
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            l_impl.calcValidStartDateCurrentDate("0D", "381");
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateDrawAccountRegist_0001()
    {
        final String STR_METHOD_NAME = "testValidateDrawAccountRegist_0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(MfFixedBuyingDrawAccountParams.TYPE);
            l_impl.validateDrawAccountRegist("0D", "381", "111111");
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03099, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateDrawAccountRegist_0002()
    {
        final String STR_METHOD_NAME = "testValidateDrawAccountRegist_0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(MfFixedBuyingDrawAccountParams.TYPE);
            MfFixedBuyingDrawAccountParams l_mfFixedBuyingDrawAccountParams =
                TestDBUtility.getMfFixedBuyingDrawAccountRow();
            l_mfFixedBuyingDrawAccountParams.setInstitutionCode("0D");
            l_mfFixedBuyingDrawAccountParams.setBranchCode("381");
            l_mfFixedBuyingDrawAccountParams.setAccountCode("111111");
            TestDBUtility.insertWithDel(l_mfFixedBuyingDrawAccountParams);
            l_impl.validateDrawAccountRegist("0D", "381", "111111");
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testCalcPrizeAndDecisioDrawAmount_0001()
    {
        final String STR_METHOD_NAME = "testValidateDrawAccountRegist_0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(MfFixedBuyingCondParams.TYPE);
            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            MfFixedBuyingCondParams l_mfFixedBuyingCondParams =
                TestDBUtility.getMfFixedBuyingCondRow();
            assertNull(l_impl.calcPrizeAndDecisioDrawAmount(l_mfFixedBuyingCondParams));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }


    public void testCalcPrizeAndDecisioDrawAmount_0002()
    {
        final String STR_METHOD_NAME = "testValidateDrawAccountRegist_0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(MfFixedBuyingCondParams.TYPE);
            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_BONUS_MONTH);
            l_systemPreferencesParams.setValue("11");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            MfFixedBuyingCondParams l_mfFixedBuyingCondParams =
                TestDBUtility.getMfFixedBuyingCondRow();
            l_mfFixedBuyingCondParams.setDrawDate(WEB3DateUtility.getDate("20081101", "yyyyMMdd"));
            TestDBUtility.deleteAll(MfFixedBuyingChangeParams.TYPE);
            assertNull(l_impl.calcPrizeAndDecisioDrawAmount(l_mfFixedBuyingCondParams));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testCalcPrizeAndDecisioDrawAmount_0003()
    {
        final String STR_METHOD_NAME = "testValidateDrawAccountRegist_0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(MfFixedBuyingCondParams.TYPE);
            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_BONUS_MONTH);
            l_systemPreferencesParams.setValue("11");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            MfFixedBuyingCondParams l_mfFixedBuyingCondParams =
                TestDBUtility.getMfFixedBuyingCondRow();

            l_mfFixedBuyingCondParams.setDrawDate(WEB3DateUtility.getDate("20081101", "yyyyMMdd"));

            TestDBUtility.deleteAll(MfFixedBuyingChangeParams.TYPE);
            MfFixedBuyingChangeParams l_mfFixedBuyingChangeParams =
                TestDBUtility.getMfFixedBuyingChangeRow();
            l_mfFixedBuyingChangeParams.setInstitutionCode(l_mfFixedBuyingCondParams.getInstitutionCode());
            l_mfFixedBuyingChangeParams.setBranchCode(l_mfFixedBuyingCondParams.getBranchCode());
            l_mfFixedBuyingChangeParams.setAccountCode(l_mfFixedBuyingCondParams.getAccountCode());
            l_mfFixedBuyingChangeParams.setProductCode(l_mfFixedBuyingCondParams.getProductCode());
            l_mfFixedBuyingChangeParams.setValidStartDate(l_mfFixedBuyingCondParams.getDrawDate());
            l_mfFixedBuyingChangeParams.setFinDrawIncreaseBuyAmount(1.1);
            l_mfFixedBuyingChangeParams.setDeleteFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_mfFixedBuyingChangeParams);
            assertNotNull(l_impl.calcPrizeAndDecisioDrawAmount(l_mfFixedBuyingCondParams));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testCalcPrizeAndDecisioDrawAmount_0004()
    {
        final String STR_METHOD_NAME = "testValidateDrawAccountRegist_0004()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(MfFixedBuyingCondParams.TYPE);
            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_BONUS_MONTH);
            l_systemPreferencesParams.setValue("10");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            MfFixedBuyingCondParams l_mfFixedBuyingCondParams =
                TestDBUtility.getMfFixedBuyingCondRow();
            l_mfFixedBuyingCondParams.setDrawDate(WEB3DateUtility.getDate("20081101", "yyyyMMdd"));
            TestDBUtility.deleteAll(MfFixedBuyingChangeParams.TYPE);
            assertNull(l_impl.calcPrizeAndDecisioDrawAmount(l_mfFixedBuyingCondParams));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
