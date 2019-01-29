head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.09.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminEquityForcedSettleBelowMarginRateIntermissionTempOrderCreateUnitServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 強制決済保証金維持率割れ（場間）仮注文作成一件サービスImplTest(WEB3AdminEquityForcedSettleBelowMarginRateIntermissionTempOrderCreateUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/02/18 新規作成 
*/
package webbroker3.eqtypeadmin.service.delegate.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;

import test.util.TestDBUtility;

import webbroker3.gentrade.data.TradingpowerCalcConditionParams;
import webbroker3.gentrade.data.TradingpowerCalcConditionRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.tradingpower.WEB3TPContractForcedSettleResult;
import webbroker3.tradingpower.data.TpCashBalanceParams;
import webbroker3.tradingpower.data.TpCashBalanceRow;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminEquityForcedSettleBelowMarginRateIntermissionTempOrderCreateUnitServiceImplTest extends
        TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityForcedSettleBelowMarginRateIntermissionTempOrderCreateUnitServiceImplTest.class);

    public WEB3AdminEquityForcedSettleBelowMarginRateIntermissionTempOrderCreateUnitServiceImplTest(String arg0)
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

    /*
     * Test method for 'webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityForcedSettleBelowMarginRateIntermissionTempOrderCreateUnitServiceImpl.validateContractForcedSettle(EqtypeContractRow)'
     */
    public void testValidateContractForcedSettle()
    {
        final String STR_METHOD_NAME = "testValidateContractForcedSettle()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512203L);
            l_subAccountParams.setSubAccountId(33381251220302L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            SubAccountParams l_subAccountParams1 = TestDBUtility.getSubAccountRow();
            l_subAccountParams1.setAccountId(333812512203L);
            l_subAccountParams1.setSubAccountId(33381251220301L);
            l_subAccountParams1.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams1);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33L);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchRow = TestDBUtility.getBranchRow();
            l_branchRow.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchRow);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            l_mainAccountParams.setMarginSysAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(TpCashBalanceRow.TYPE);
            TpCashBalanceParams l_tpCashBalanceParams = TestDBUtility.getTpCashBalanceRow();
            l_tpCashBalanceParams.setSubAccountId(33381251220302L);
            TestDBUtility.insertWithDel(l_tpCashBalanceParams);
            
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams =
                TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);
            
            EqtypeContractParams l_eqtypeContractParams =
                TestDBUtility.getEqtypeContractRow();
            l_eqtypeContractParams.setTaxType(TaxTypeEnum.NORMAL);
            l_eqtypeContractParams.setContractType(ContractTypeEnum.LONG);
            l_eqtypeContractParams.setAccountId(333812512203L);
            l_eqtypeContractParams.setSubAccountId(33381251220301L);
            
            WEB3AdminEquityForcedSettleBelowMarginRateIntermissionTempOrderCreateUnitServiceImpl l_impl =
                new WEB3AdminEquityForcedSettleBelowMarginRateIntermissionTempOrderCreateUnitServiceImpl();
            WEB3TPContractForcedSettleResult l_contractForcedSettleResult =
                l_impl.validateContractForcedSettle(l_eqtypeContractParams);

            assertFalse(l_contractForcedSettleResult.resultFlg);
            assertNull(l_contractForcedSettleResult.forcedSettleReason);
            assertNull(l_contractForcedSettleResult.additionalMarginDate);
            assertNull(l_contractForcedSettleResult.additionalMarginAccruedDays);
            assertNull(l_contractForcedSettleResult.marginMaintenanceRate);
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
