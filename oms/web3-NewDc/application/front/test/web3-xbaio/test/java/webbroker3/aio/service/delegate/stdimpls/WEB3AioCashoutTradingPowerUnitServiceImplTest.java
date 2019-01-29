head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.40.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AioCashoutTradingPowerUnitServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （WEB3AioCashoutTradingPowerUnitServiceImplTest.java）
Author Name      : Daiwa Institute of Research
Revision History : 2009/12/18 武波 (中訊) 新規作成
*/
package webbroker3.aio.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitDao;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;

import test.util.TestDBUtility;

import webbroker3.aio.data.HostPaymentOrderParams;
import webbroker3.aio.data.HostPaymentOrderRow;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.define.WEB3AioHostStatusDef;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3MarginAccountOpenDivDef;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.data.BranchPreferencesParams;
import webbroker3.gentrade.data.TradingpowerCalcConditionParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AioCashoutTradingPowerUnitServiceImplTest extends TestBaseForMock
{
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3AioCashoutTradingPowerUnitServiceImplTest.class);
    public WEB3AioCashoutTradingPowerUnitServiceImplTest(String arg0)
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

    WEB3AioCashoutTradingPowerUnitServiceImpl l_impl =
        new WEB3AioCashoutTradingPowerUnitServiceImpl();
    public void testUpdatePayMentData_Case001()
    {
        final String STR_METHOD_NAME = "testUpdatePayMentData_Case001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(AioOrderUnitParams.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setOrderUnitId(1);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            l_impl.updatePayMentData("0D", "381", "123456", "1", 1, 1);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }

    public void testUpdatePayMentData_Case002()
    {
        final String STR_METHOD_NAME = "testUpdatePayMentData_Case002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(AioOrderUnitParams.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setOrderUnitId(1);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            
            TestDBUtility.deleteAll(HostPaymentOrderParams.TYPE);
            HostPaymentOrderParams l_hostPaymentOrderParams = new HostPaymentOrderParams();
            l_hostPaymentOrderParams.setRequestCode("GI801");
            l_hostPaymentOrderParams.setInstitutionCode("0D");
            l_hostPaymentOrderParams.setBranchCode("381");
            l_hostPaymentOrderParams.setAccountCode("123456");
            l_hostPaymentOrderParams.setOrderRequestNumber("1");
            l_hostPaymentOrderParams.setStatus("3");
            TestDBUtility.insertWithDel(l_hostPaymentOrderParams);
            
            l_impl.updatePayMentData("0D", "381", "123456", "1", 1, 1);
            
            AioOrderUnitRow l_orderUnitRow = 
                AioOrderUnitDao.findRowByPk(1);
            assertEquals("1.0", "" + l_orderUnitRow.getQuantity());
            String l_strQueryString =
                " request_code = ? and institution_code = ? and branch_code = ? " +
                " and account_code = ? and order_request_number = ? and status = ? ";
            Object[] l_objQueryDataContainer =  new Object[] { 
                WEB3HostRequestCodeDef.AIO_CASH_OUT_REQUEST_ORDER,
                "0D",
                "381", 
                "123456",
                "1", 
                WEB3AioHostStatusDef.NOT_DEAL};
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisHostPaymentOrderRows = l_queryProcessor.doFindAllQuery(
                HostPaymentOrderRow.TYPE,
                l_strQueryString,
                l_objQueryDataContainer);
            HostPaymentOrderRow l_hostPaymentOrderRow = (HostPaymentOrderRow)l_lisHostPaymentOrderRows.get(0);
            assertEquals("1", "" + l_hostPaymentOrderRow.getPaymentAmount());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }

    public void testIsPaymentTodayDepositTransferEnforce_Case001()
    {
        final String STR_METHOD_NAME = "testIsPaymentTodayDepositTransferEnforce_Case001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            assertFalse(l_impl.isPaymentTodayDepositTransferEnforce(111));
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }

    public void testIsPaymentTodayDepositTransferEnforce_Case002()
    {
        final String STR_METHOD_NAME = "testIsPaymentTodayDepositTransferEnforce_Case002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            BranchPreferencesParams l_branchPreferencesParams =
                TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setBranchId(111);
            l_branchPreferencesParams.setName(WEB3BranchPreferencesNameDef.CASHOUT_TODAY_DEPOSIT_TRANSFER_DIV);
            l_branchPreferencesParams.setNameSerialNo(1);
            l_branchPreferencesParams.setValue("2");
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            assertFalse(l_impl.isPaymentTodayDepositTransferEnforce(111));
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }

    public void testIsPaymentTodayDepositTransferEnforce_Case003()
    {
        final String STR_METHOD_NAME = "testIsPaymentTodayDepositTransferEnforce_Case003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            BranchPreferencesParams l_branchPreferencesParams =
                TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setBranchId(111);
            l_branchPreferencesParams.setName(WEB3BranchPreferencesNameDef.CASHOUT_TODAY_DEPOSIT_TRANSFER_DIV);
            l_branchPreferencesParams.setNameSerialNo(1);
            l_branchPreferencesParams.setValue("0");
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            assertTrue(!l_impl.isPaymentTodayDepositTransferEnforce(111));
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }

    public void testIsPaymentTodayDepositTransferEnforce_Case004()
    {
        final String STR_METHOD_NAME = "testIsPaymentTodayDepositTransferEnforce_Case004()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            BranchPreferencesParams l_branchPreferencesParams =
                TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setBranchId(111);
            l_branchPreferencesParams.setName(WEB3BranchPreferencesNameDef.CASHOUT_TODAY_DEPOSIT_TRANSFER_DIV);
            l_branchPreferencesParams.setNameSerialNo(1);
            l_branchPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            assertTrue(l_impl.isPaymentTodayDepositTransferEnforce(111));
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }

    public void testExecute_Case001()
    {
        final String STR_METHOD_NAME = "testExecute_Case001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(HostPaymentOrderParams.TYPE);
            HostPaymentOrderParams l_hostPaymentOrderParams = new HostPaymentOrderParams();
            l_hostPaymentOrderParams.setRequestCode("GI801");
            l_hostPaymentOrderParams.setInstitutionCode("0D");
            l_hostPaymentOrderParams.setBranchCode("381");
            l_hostPaymentOrderParams.setAccountCode("123456");
            l_hostPaymentOrderParams.setOrderRequestNumber("1");
            l_hostPaymentOrderParams.setStatus("3");
            TestDBUtility.insertWithDel(l_hostPaymentOrderParams);

            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionCode("0D");
            l_branchParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionId(l_institutionParams.getInstitutionId());
            l_mainAccountParams.setBranchId(l_branchParams.getBranchId());
            l_mainAccountParams.setAccountCode("123456");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(AioOrderUnitParams.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setOrderUnitId(1);
            l_aioOrderUnitParams.setAccountId(l_mainAccountParams.getAccountId());
            l_aioOrderUnitParams.setSubAccountId(l_subAccountParams.getSubAccountId());
            l_aioOrderUnitParams.setBranchId(l_branchParams.getBranchId());
            l_aioOrderUnitParams.setProductType(ProductTypeEnum.CASH);
            l_aioOrderUnitParams.setOrderRequestNumber("1");
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);

            l_impl = new WEB3AioCashoutTradingPowerUnitServiceImplTestInner();
            l_impl.execute(l_hostPaymentOrderParams, "1", true, "1");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }

    public void testExecute_Case002()
    {
        final String STR_METHOD_NAME = "testExecute_Case002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(HostPaymentOrderParams.TYPE);
            HostPaymentOrderParams l_hostPaymentOrderParams = new HostPaymentOrderParams();
            l_hostPaymentOrderParams.setRequestCode("GI801");
            l_hostPaymentOrderParams.setInstitutionCode("0D");
            l_hostPaymentOrderParams.setBranchCode("381");
            l_hostPaymentOrderParams.setAccountCode("123456");
            l_hostPaymentOrderParams.setOrderRequestNumber("1");
            l_hostPaymentOrderParams.setStatus("3");
            TestDBUtility.insertWithDel(l_hostPaymentOrderParams);

            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionCode("0D");
            l_branchParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionId(l_institutionParams.getInstitutionId());
            l_mainAccountParams.setBranchId(l_branchParams.getBranchId());
            l_mainAccountParams.setAccountCode("123456");
            l_mainAccountParams.setMarginGenAccOpenDiv(WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
            l_subAccountParams.setSubAccountId(2);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
            l_subAccountParams.setSubAccountId(1);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(AioOrderUnitParams.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setOrderUnitId(1);
            l_aioOrderUnitParams.setAccountId(l_mainAccountParams.getAccountId());
            l_aioOrderUnitParams.setSubAccountId(1);
            l_aioOrderUnitParams.setBranchId(l_branchParams.getBranchId());
            l_aioOrderUnitParams.setProductType(ProductTypeEnum.CASH);
            l_aioOrderUnitParams.setOrderRequestNumber("1");
            
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);

            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            BranchPreferencesParams l_branchPreferencesParams =
                TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setBranchId(111);
            l_branchPreferencesParams.setName(WEB3BranchPreferencesNameDef.CASHOUT_TODAY_DEPOSIT_TRANSFER_DIV);
            l_branchPreferencesParams.setNameSerialNo(1);
            l_branchPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            TestDBUtility.deleteAll(TradingpowerCalcConditionParams.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams = TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(l_mainAccountParams.getAccountId());
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);

            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "getNextBizDateShortfall",
                new Class[]
                    { WEB3GentradeSubAccount.class},
                new Double(1.1));
            l_impl = new WEB3AioCashoutTradingPowerUnitServiceImplTestInner();
            l_impl.execute(l_hostPaymentOrderParams, "2", true, "1");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }

    public void testExecute_Case003()
    {
        final String STR_METHOD_NAME = "testExecute_Case003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(HostPaymentOrderParams.TYPE);
            HostPaymentOrderParams l_hostPaymentOrderParams = new HostPaymentOrderParams();
            l_hostPaymentOrderParams.setRequestCode("GI801");
            l_hostPaymentOrderParams.setInstitutionCode("0D");
            l_hostPaymentOrderParams.setBranchCode("381");
            l_hostPaymentOrderParams.setAccountCode("123456");
            l_hostPaymentOrderParams.setOrderRequestNumber("1");
            l_hostPaymentOrderParams.setStatus("3");
            TestDBUtility.insertWithDel(l_hostPaymentOrderParams);

            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionCode("0D");
            l_branchParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionId(l_institutionParams.getInstitutionId());
            l_mainAccountParams.setBranchId(l_branchParams.getBranchId());
            l_mainAccountParams.setAccountCode("123456");
            l_mainAccountParams.setMarginGenAccOpenDiv(WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
            l_subAccountParams.setSubAccountId(1);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
            l_subAccountParams.setSubAccountId(2);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            


            TestDBUtility.deleteAll(AioOrderUnitParams.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setOrderUnitId(1);
            l_aioOrderUnitParams.setAccountId(l_mainAccountParams.getAccountId());
            l_aioOrderUnitParams.setSubAccountId(1);
            l_aioOrderUnitParams.setBranchId(l_branchParams.getBranchId());
            l_aioOrderUnitParams.setProductType(ProductTypeEnum.CASH);
            l_aioOrderUnitParams.setOrderRequestNumber("1");
            l_aioOrderUnitParams.setQuantity(1);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);

            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            BranchPreferencesParams l_branchPreferencesParams =
                TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setBranchId(111);
            l_branchPreferencesParams.setName(WEB3BranchPreferencesNameDef.CASHOUT_TODAY_DEPOSIT_TRANSFER_DIV);
            l_branchPreferencesParams.setNameSerialNo(1);
            l_branchPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            TestDBUtility.deleteAll(TradingpowerCalcConditionParams.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams = TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(l_mainAccountParams.getAccountId());
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);

            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "getNextBizDateShortfall",
                new Class[]
                    { WEB3GentradeSubAccount.class},
                new Double(101));
            l_impl = new WEB3AioCashoutTradingPowerUnitServiceImplTestInner();
            l_impl.execute(l_hostPaymentOrderParams, "2", true, "1");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }

    class WEB3AioCashoutTradingPowerUnitServiceImplTestInner extends WEB3AioCashoutTradingPowerUnitServiceImpl
    {
        protected boolean validateCashoutTradingPower(
            AioOrderUnit l_orderUnit,
            String l_strDbCurrentPriceCheckDiv) 
            throws WEB3BaseException
        {
            return false;
        }

        protected void orderCancelProcess(AioOrderUnit l_orderUnit) 
            throws WEB3BaseException
        {
                
        }
        protected boolean isPaymentTodayDepositTransferEnforce(long l_lngBranchId)
            throws WEB3BaseException
        {
            return true;
        }
    }
}
@
