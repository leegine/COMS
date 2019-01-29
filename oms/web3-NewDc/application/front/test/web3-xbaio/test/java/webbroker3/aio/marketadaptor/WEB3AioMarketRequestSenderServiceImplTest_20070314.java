head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.32.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AioMarketRequestSenderServiceImplTest_20070314.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (äî)ëÂòaëçå§ èÿåîÉ\ÉäÉÖÅ[ÉVÉáÉìÉVÉXÉeÉÄëÊìÒïî
File Name        : //TODO(WEB3AioMarketRequestSenderServiceImplTest_20070314.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/03/15 èôçGàÃ (íÜêu) êVãKçÏê¨
*/
package webbroker3.aio.marketadaptor;

import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbaio.stdimpls.AioOrderManagerImpl;

import test.util.TestDBUtility;

import webbroker3.aio.data.HostForeignCashTransOrderDao;
import webbroker3.aio.data.HostForeignCashTransOrderParams;
import webbroker3.aio.data.HostForeignCashTransOrderRow;
import webbroker3.aio.data.HostPaymentOrderParams;
import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.FinInstitutionParams;
import webbroker3.gentrade.data.FinInstitutionRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.util.WEB3LogUtility;

import junit.framework.TestCase;

/**
 * XXXXXXÉNÉâÉX//TODO
 *
 * @@author èôçGàÃ(íÜêu)
 * @@version 1.0
 */
public class WEB3AioMarketRequestSenderServiceImplTest_20070314 extends TestCase
{

    /**
     * ÉçÉOÉÜÅ[ÉeÉBÉäÉeÉB
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioMarketRequestSenderServiceImplTest.class);

    WEB3AioMarketRequestSenderServiceImplForTest l_impl = new WEB3AioMarketRequestSenderServiceImplForTest();
    public WEB3AioMarketRequestSenderServiceImplTest_20070314(String arg0)
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

    public void testOpenCashinOrderSend_case001()
    {
        final String STR_METHOD_NAME = "testOpenCashinOrderSend_case001";
        log.entering(STR_METHOD_NAME);
        try
        {
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountCode("321");
            l_mainAccountParams.setBranchId(222);
            l_mainAccountParams.setInstitutionId(123);
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setInstitutionCode("0D");
            //å˚ç¿ÇhÇc]333812512203, SubAccount Id : 11110111101001
            l_subAccountParams.setAccountId(333812512203L);
            //ï‚èïå˚ç¿ÇhÇc
            l_subAccountParams.setSubAccountId(11110111101001L);
            //ï‚èïå˚ç¿É^ÉCÉv
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setBranchId(222);
            l_subAccountParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("624");
            l_branchParams.setBranchId(222);
            TestDBUtility.insertWithDel(l_branchParams);
            WEB3GentradeTradingTimeManagementForMock.setSystemAttributes();
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setSubmitMarketTrigger("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);
            AioOrderUnitParams l_params = TestDBUtility.getAioOrderUnitRow();
            l_params.setOrderUnitId(10);
            l_params.setAccountId(333812512203L);
            l_params.setOrderRequestNumber("24");
            l_params.setCurrencyCode(null);
            TestDBUtility.insertWithDel(l_params);
            TestDBUtility.deleteAll(FinInstitutionRow.TYPE);

            AioOrderManagerImpl l_aioOrderManagerImpl = new AioOrderManagerImpl();
            AioOrderUnit l_aioOrderUnit =
                (AioOrderUnit)l_aioOrderManagerImpl.getOrderUnit(10);
            this.l_impl.openCashinOrderSend(l_aioOrderUnit);
        }
        catch (Exception l_ex)
        {
            log.error("Error In Exception...", l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testOpenCashinOrderSend_case002()
    {
        final String STR_METHOD_NAME = "testOpenCashinOrderSend_case002";
        log.entering(STR_METHOD_NAME);
        try
        {
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountCode("321");
            l_mainAccountParams.setBranchId(222);
            l_mainAccountParams.setInstitutionId(123);
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setInstitutionCode("0D");
            //å˚ç¿ÇhÇc]333812512203, SubAccount Id : 11110111101001
            l_subAccountParams.setAccountId(333812512203L);
            //ï‚èïå˚ç¿ÇhÇc
            l_subAccountParams.setSubAccountId(11110111101001L);
            //ï‚èïå˚ç¿É^ÉCÉv
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setBranchId(222);
            l_subAccountParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("624");
            l_branchParams.setBranchId(222);
            TestDBUtility.insertWithDel(l_branchParams);
            WEB3GentradeTradingTimeManagementForMock.setSystemAttributes();
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setSubmitMarketTrigger("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);
            AioOrderUnitParams l_params = TestDBUtility.getAioOrderUnitRow();
            l_params.setOrderUnitId(10);
            l_params.setAccountId(333812512203L);
            l_params.setOrderRequestNumber("24");
            l_params.setCurrencyCode("10");
            TestDBUtility.insertWithDel(l_params);

            AioOrderManagerImpl l_aioOrderManagerImpl = new AioOrderManagerImpl();
            AioOrderUnit l_aioOrderUnit =
                (AioOrderUnit)l_aioOrderManagerImpl.getOrderUnit(10);
            this.l_impl.openCashinOrderSend(l_aioOrderUnit);
        }
        catch (Exception l_ex)
        {
            log.error("Error In Exception...", l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testOpenCashOutOrderSend_case001()
    {
        final String STR_METHOD_NAME = "testOpenCashOutOrderSend_case001";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(HostPaymentOrderParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("1D");
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountCode("321");
            l_mainAccountParams.setBranchId(222);
            l_mainAccountParams.setInstitutionId(123);
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setInstitutionCode("1D");
            //å˚ç¿ÇhÇc]333812512203, SubAccount Id : 11110111101001
            l_subAccountParams.setAccountId(333812512203L);
            //ï‚èïå˚ç¿ÇhÇc
            l_subAccountParams.setSubAccountId(11110111101001L);
            //ï‚èïå˚ç¿É^ÉCÉv
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setBranchId(222);
            l_subAccountParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("624");
            l_branchParams.setBranchId(222);
            TestDBUtility.insertWithDel(l_branchParams);
            WEB3GentradeTradingTimeManagementForMock.setSystemAttributes();
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            
            l_tradingTimeParams.setInstitutionCode("1D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setSubmitMarketTrigger("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            l_institutionParams.setInstitutionCode("1D");
            TestDBUtility.insertWithDel(l_institutionParams);
            AioOrderUnitParams l_params = TestDBUtility.getAioOrderUnitRow();
            l_params.setOrderUnitId(10);
            l_params.setAccountId(333812512203L);
            l_params.setOrderRequestNumber("24");
            l_params.setCurrencyCode(null);
            TestDBUtility.insertWithDel(l_params);

            AioOrderManagerImpl l_aioOrderManagerImpl = new AioOrderManagerImpl();
            AioOrderUnit l_aioOrderUnit =
                (AioOrderUnit)l_aioOrderManagerImpl.getOrderUnit(10);
            this.l_impl.openCashoutOrderSend(l_aioOrderUnit);
        }
        catch (Exception l_ex)
        {
            log.error("Error In Exception...", l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testOpenCashOutOrderSend_case002()
    {
        final String STR_METHOD_NAME = "testOpenCashOutOrderSend_case002";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(HostForeignCashTransOrderParams.TYPE);
            TestDBUtility.deleteAll(HostPaymentOrderParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("1D");
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountCode("321");
            l_mainAccountParams.setBranchId(222);
            l_mainAccountParams.setInstitutionId(123);
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setInstitutionCode("1D");
            //å˚ç¿ÇhÇc]333812512203, SubAccount Id : 11110111101001
            l_subAccountParams.setAccountId(333812512203L);
            //ï‚èïå˚ç¿ÇhÇc
            l_subAccountParams.setSubAccountId(11110111101001L);
            //ï‚èïå˚ç¿É^ÉCÉv
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setBranchId(222);
            l_subAccountParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("624");
            l_branchParams.setBranchId(222);
            TestDBUtility.insertWithDel(l_branchParams);
            WEB3GentradeTradingTimeManagementForMock.setSystemAttributes();
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            
            l_tradingTimeParams.setInstitutionCode("1D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setSubmitMarketTrigger("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            l_institutionParams.setInstitutionCode("1D");
            TestDBUtility.insertWithDel(l_institutionParams);
            AioOrderUnitParams l_params = TestDBUtility.getAioOrderUnitRow();
            l_params.setOrderUnitId(10);
            l_params.setAccountId(333812512203L);
            l_params.setOrderRequestNumber("24");
            l_params.setCurrencyCode("10");
            TestDBUtility.insertWithDel(l_params);
            TestDBUtility.deleteAll(FinInstitutionRow.TYPE);

            FinInstitutionParams l_finInstitutionparams = new FinInstitutionParams();
            l_finInstitutionparams.setFinInstitutionCode("000000000000000");
            l_finInstitutionparams.setInstitutionCode("1D");
            l_finInstitutionparams.setCashTransferType("1");
            l_finInstitutionparams.setFinInstitutionNameKana("1");
            l_finInstitutionparams.setFinInstitutionNameKanji("2");
            TestDBUtility.insertWithDel(l_finInstitutionparams);
            AioOrderManagerImpl l_aioOrderManagerImpl = new AioOrderManagerImpl();
            AioOrderUnit l_aioOrderUnit =
                (AioOrderUnit)l_aioOrderManagerImpl.getOrderUnit(10);
            this.l_impl.openCashoutOrderSend(l_aioOrderUnit);
        }
        catch (Exception l_ex)
        {
            log.error("Error In Exception...", l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }


    public void testCreateHostForeignCashinOrderData()
    {
        final String STR_METHOD_NAME = "createHostForeignCashinOrderData";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(HostForeignCashTransOrderRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountCode("321");
            l_mainAccountParams.setBranchId(222);
            l_mainAccountParams.setInstitutionId(123);
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setInstitutionCode("0D");
            //å˚ç¿ÇhÇc]333812512203, SubAccount Id : 11110111101001
            l_subAccountParams.setAccountId(333812512203L);
            //ï‚èïå˚ç¿ÇhÇc
            l_subAccountParams.setSubAccountId(11110111101001L);
            //ï‚èïå˚ç¿É^ÉCÉv
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setBranchId(222);
            l_subAccountParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("624");
            l_branchParams.setBranchId(222);
            TestDBUtility.insertWithDel(l_branchParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);
            AioOrderUnitParams l_params = TestDBUtility.getAioOrderUnitRow();
            l_params.setOrderUnitId(10);
            l_params.setAccountId(333812512203L);
            l_params.setOrderRequestNumber("24");
            l_params.setCurrencyCode("10");
            l_params.setConvertAmount(123456.123);
            l_params.setOrderRootDiv("D");
            l_params.setDescription("123");
            TestDBUtility.insertWithDel(l_params);
            TestDBUtility.deleteAll(FinInstitutionRow.TYPE);

            AioOrderManagerImpl l_aioOrderManagerImpl = new AioOrderManagerImpl();
            AioOrderUnit l_aioOrderUnit =
                (AioOrderUnit)l_aioOrderManagerImpl.getOrderUnit(10);
            TestDBUtility.deleteAll(HostForeignCashTransOrderParams.TYPE);
            FinInstitutionParams l_FinInstitutionParams = this.getFinInstitutionRow();
            l_FinInstitutionParams.setInstitutionCode("0D");
            l_FinInstitutionParams.setFinInstitutionCode("123");
            TestDBUtility.insertWithDel(l_FinInstitutionParams);
            this.l_impl.createHostForeignCashinOrderData(l_aioOrderUnit);
            String p_requestCode = "GI804";
            String p_institutionCode = "0D";
            String p_branchCode = "624";
            String p_accountCode = "321";
            String p_orderRequestNumber = "24";
            HostForeignCashTransOrderRow l_orderRow =
                HostForeignCashTransOrderDao.findRowByPk(
                    p_requestCode, p_institutionCode, p_branchCode, p_accountCode, p_orderRequestNumber);
            assertEquals(123456, l_orderRow.getCreditConvertAmount());
            assertEquals("2", l_orderRow.getOrderDiv());
            
        }
        catch (Exception l_ex)
        {
            log.error("Error In Exception...", l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateHostForeignCashoutOrderData_001()
    {
        final String STR_METHOD_NAME = "testCreateHostForeignCashoutOrderData_001";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(HostForeignCashTransOrderRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountCode("321");
            l_mainAccountParams.setBranchId(222);
            l_mainAccountParams.setInstitutionId(123);
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setInstitutionCode("0D");
            //å˚ç¿ÇhÇc]333812512203, SubAccount Id : 11110111101001
            l_subAccountParams.setAccountId(333812512203L);
            //ï‚èïå˚ç¿ÇhÇc
            l_subAccountParams.setSubAccountId(11110111101001L);
            //ï‚èïå˚ç¿É^ÉCÉv
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setBranchId(222);
            l_subAccountParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("624");
            l_branchParams.setBranchId(222);
            TestDBUtility.insertWithDel(l_branchParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);
            AioOrderUnitParams l_params = TestDBUtility.getAioOrderUnitRow();
            l_params.setOrderUnitId(10);
            l_params.setAccountId(333812512203L);
            l_params.setOrderRequestNumber("24");
            l_params.setCurrencyCode("10");
            l_params.setConvertAmount(123456.123);
            TestDBUtility.insertWithDel(l_params);
            TestDBUtility.deleteAll(FinInstitutionRow.TYPE);

            AioOrderManagerImpl l_aioOrderManagerImpl = new AioOrderManagerImpl();
            AioOrderUnit l_aioOrderUnit =
                (AioOrderUnit)l_aioOrderManagerImpl.getOrderUnit(10);
            TestDBUtility.deleteAll(HostForeignCashTransOrderParams.TYPE);
            FinInstitutionParams l_FinInstitutionParams = this.getFinInstitutionRow();
            l_FinInstitutionParams.setInstitutionCode("0D");
            l_FinInstitutionParams.setFinInstitutionCode("000000000000000");
            TestDBUtility.insertWithDel(l_FinInstitutionParams);
            this.l_impl.createHostForeignCashoutOrderData(l_aioOrderUnit);
            String p_requestCode = "GI804";
            String p_institutionCode = "0D";
            String p_branchCode = "624";
            String p_accountCode = "321";
            String p_orderRequestNumber = "24";

            HostForeignCashTransOrderRow l_orderRow =
                HostForeignCashTransOrderDao.findRowByPk(
                    p_requestCode, p_institutionCode, p_branchCode, p_accountCode, p_orderRequestNumber);
            assertEquals(123456, l_orderRow.getDebitConvertAmount());
            assertEquals("1", l_orderRow.getOrderDiv());
            
        }
        catch (Exception l_ex)
        {
            log.error("Error In Exception...", l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testCreateHostForeignCashoutOrderData_002()
    {
        final String STR_METHOD_NAME = "testCreateHostForeignCashoutOrderData_002";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(HostForeignCashTransOrderRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountCode("321");
            l_mainAccountParams.setBranchId(222);
            l_mainAccountParams.setInstitutionId(123);
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setInstitutionCode("0D");
            //å˚ç¿ÇhÇc]333812512203, SubAccount Id : 11110111101001
            l_subAccountParams.setAccountId(333812512203L);
            //ï‚èïå˚ç¿ÇhÇc
            l_subAccountParams.setSubAccountId(11110111101001L);
            //ï‚èïå˚ç¿É^ÉCÉv
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setBranchId(222);
            l_subAccountParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("624");
            l_branchParams.setBranchId(222);
            TestDBUtility.insertWithDel(l_branchParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);
            AioOrderUnitParams l_params = TestDBUtility.getAioOrderUnitRow();
            l_params.setOrderUnitId(10);
            l_params.setAccountId(333812512203L);
            l_params.setOrderRequestNumber("24");
            l_params.setCurrencyCode("10");
            l_params.setConvertAmount(0);
            TestDBUtility.insertWithDel(l_params);
            TestDBUtility.deleteAll(FinInstitutionRow.TYPE);
            FinInstitutionParams l_FinInstitutionParams = this.getFinInstitutionRow();
            l_FinInstitutionParams.setInstitutionCode("0D");
            l_FinInstitutionParams.setFinInstitutionCode("000000000000000");
            TestDBUtility.insertWithDel(l_FinInstitutionParams);
            AioOrderManagerImpl l_aioOrderManagerImpl = new AioOrderManagerImpl();
            AioOrderUnit l_aioOrderUnit =
                (AioOrderUnit)l_aioOrderManagerImpl.getOrderUnit(10);
            TestDBUtility.deleteAll(HostForeignCashTransOrderParams.TYPE);
            this.l_impl.createHostForeignCashoutOrderData(l_aioOrderUnit);
            String p_requestCode = "GI804";
            String p_institutionCode = "0D";
            String p_branchCode = "624";
            String p_accountCode = "321";
            String p_orderRequestNumber = "24";
            HostForeignCashTransOrderRow l_orderRow =
                HostForeignCashTransOrderDao.findRowByPk(
                    p_requestCode, p_institutionCode, p_branchCode, p_accountCode, p_orderRequestNumber);
            assertEquals(0, l_orderRow.getDebitConvertAmount());
            assertEquals("1", l_orderRow.getOrderDiv());
            
        }
        catch (Exception l_ex)
        {
            log.error("Error In Exception...", l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * ã‡óZã@@ä÷ÉeÅ[ÉuÉã(fin_institution)
     */

    private class WEB3AioMarketRequestSenderServiceImplForTest extends WEB3AioMarketRequestSenderServiceImpl
    {
        public void submitTrigger(String l_strInstitutionCode, 
            String l_strRequestCode) 
        {
            log.debug(l_strInstitutionCode + "== l_strInstitutionCode");
            log.debug(l_strRequestCode + "== l_strRequestCode");

            if ("1D".equals(l_strInstitutionCode))
            {
                assertEquals("1D" ,l_strInstitutionCode);
                assertEquals("GI801T" ,l_strRequestCode);
            }
            else
            {
                assertEquals("0D" ,l_strInstitutionCode);
                assertEquals("GI803T" ,l_strRequestCode);
            }
        }
        
        protected void createCashinOrderQueueData(AioOrderUnit l_orderUnit)
        throws WEB3BaseException
        {
        }
        
        public void updateStatus(
            String l_strInstitutionCode, 
            String l_strBranchCode, 
            String l_strAccountCode, 
            String l_strOrderRequestNumber, 
            long l_lngOrderUnitId)
            throws WEB3BaseException
        {
            final String l_strMethodName = "updateStatus(String l_strInstitutionCode,"
                + "String l_strBranchCode, String l_strAccountCode, "
                + "String l_strOrderRequestNumber, long l_lngOrderUnitId)";
            log.entering(l_strMethodName);
        }

        public void updateMQStatus(long l_lngOrderUnitId)
        throws WEB3BaseException
        {
                
        }

        public void submitTrigger(
            String l_strInstitutionCode, String l_strRequestCode, String l_strUser)
        {
            log.debug(l_strInstitutionCode + "== l_strInstitutionCode");
            log.debug(l_strRequestCode + "== l_strRequestCode");
            log.debug(l_strUser + "== l_strUser");
            
            if ("1D".equals(l_strInstitutionCode))
            {
                assertEquals("1D" ,l_strInstitutionCode);
                assertEquals("GI804T" ,l_strRequestCode);
                assertEquals("1" ,l_strUser);
            }
            else
            {
                assertEquals("0D" ,l_strInstitutionCode);
                assertEquals("GI804T" ,l_strRequestCode);
                assertEquals("2" ,l_strUser);
            }


        }
        protected boolean isSubmitRealTimeTrigger(long l_lngBranchId)
        throws WEB3BaseException
        {
                return true;
        }
    }
    private static FinInstitutionParams getFinInstitutionRow()
    {
        FinInstitutionParams l_finInstitutionParams = new FinInstitutionParams();
       // 1    èÿåîâÔé–ÉRÅ[Éh   institution_code   VARCHAR2   3   NotNull  
        l_finInstitutionParams.setInstitutionCode("0D");
       // 2    ã‡óZã@@ä÷ÉRÅ[Éh   fin_institution_code   VARCHAR2   15   NotNull   "ã‡óZã@@ä÷ÉRÅ[ÉhÅ@@AAAABBBCDDDDDDDÅiA:ã‡óZã@@ä÷,B:éxìXÉRÅ[Éh,C:óaã‡â»ñ⁄,D:å˚ç¿î‘çÜ)"            
        l_finInstitutionParams.setFinInstitutionCode("000000000000000");
        // 3    ã‡óZã@@ä÷ñºÅiäøéöÅj   fin_institution_name_kanji   VARCHAR2   50   NotNull   ì¸ã‡òAóçâÊñ ï\é¶óp            
        l_finInstitutionParams.setFinInstitutionNameKanji("xxxxxxxxxxxx");
        // 4    ã‡óZã@@ä÷ñºÅiÉJÉiÅj   fin_institution_name_kana   VARCHAR2   50   NotNull   ämîFóp            
        l_finInstitutionParams.setFinInstitutionNameKana("cccccccccccccccc");
        // 5    ì¸èoã‡ì`ï[ÉLÉÖÅ[ópì¸èoã‡ï˚ñ@@   cash_transfer_type   VARCHAR2   1         ÇUÅFóXï÷êUçû ÇVÅFã‚çsêUçûÅiNETÅj ÇXÅFã‚çsêUçûÅiïÅí Åj            
        l_finInstitutionParams.setCashTransferType("6");
        // 6    ì¸èoã‡ì`ï[ÉLÉÖÅ[ópëäéËÉRÅ[Éh   cash_transfer_sonar_code   VARCHAR2   2         èÿåîâÔé–Ç…ämîFíÜ 
        l_finInstitutionParams.setCashTransferSonarCode("61");
        return l_finInstitutionParams;
    }

}
@
