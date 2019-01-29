head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.08.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminEquityForcedSettleTempOrderCreateUnitServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (Š”)‘å˜a‘Œ¤ ØŒ”ƒ\ƒŠƒ…[ƒVƒ‡ƒ“ƒVƒXƒeƒ€‘æ“ñ•”
File Name        : (WEB3AdminEquityForcedSettleTempOrderCreateUnitServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/04/24 ‰½•¶•q(’†u) V‹Kì¬
*/
package webbroker3.eqtypeadmin.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeClosingContractSpecParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeClosingContractSpecRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractDao;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeLockedContractDetailsParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeLockedContractDetailsRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeNewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderEntry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.eqtypeadmin.data.ForcedSettleOrderInqDao;
import webbroker3.eqtypeadmin.data.ForcedSettleOrderInqParams;
import webbroker3.eqtypeadmin.data.ForcedSettleOrderInqRow;
import webbroker3.equity.WEB3EquityContract;
import webbroker3.equity.WEB3EquityRealizedProfitAndLossPrice;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.data.EqtypeOrderExecSendMailRow;
import webbroker3.equity.data.EquityLimitPriceRangeMstParams;
import webbroker3.equity.data.EquityLimitPriceRangeMstRow;
import webbroker3.equity.data.EquityTickValuesMstParams;
import webbroker3.equity.data.EquityTickValuesMstRow;
import webbroker3.equity.data.HostEqtypeCloseOrderNotifyParams;
import webbroker3.equity.data.HostEqtypeOrderAllDao;
import webbroker3.equity.data.HostEqtypeOrderAllParams;
import webbroker3.equity.data.HostEqtypeOrderAllRow;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.BranchMarketRepayDealtCondParams;
import webbroker3.gentrade.data.BranchMarketRepayDealtCondRow;
import webbroker3.gentrade.data.InstBranchProductParams;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectParamsValue;
import webbroker3.tradingpower.WEB3TPContractForcedSettleResult;
import webbroker3.tradingpower.data.TpCalcResultExecNotifyRow;
import webbroker3.triggerorder.base.data.RsvEqClosingContractSpecParams;
import webbroker3.triggerorder.base.data.RsvEqClosingContractSpecRow;
import webbroker3.triggerorder.base.data.RsvEqOrderActionRow;
import webbroker3.triggerorder.base.data.RsvEqOrderUnitDao;
import webbroker3.triggerorder.base.data.RsvEqOrderUnitParams;
import webbroker3.triggerorder.base.data.RsvEqOrderUnitRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * @@author ‰½•¶•q
 * @@version 1.0
 */
public class WEB3AdminEquityForcedSettleTempOrderCreateUnitServiceImplTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityForcedSettleTempOrderCreateUnitServiceImplTest.class);

    WEB3AdminEquityForcedSettleCloseDateTempOrderCreateUnitServiceImpl l_mpl =
        new WEB3AdminEquityForcedSettleCloseDateTempOrderCreateUnitServiceImpl();

    public WEB3AdminEquityForcedSettleTempOrderCreateUnitServiceImplTest(String arg0)
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
    
    public void testExecTempOrderCreation2()
    {
        final String STR_METHOD_NAME = "testExecTempOrderCreation2()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "web3.attributes.basetimestampfororderbizdate", null);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                new Timestamp(WEB3DateUtility.getDate("20080108", "yyyyMMdd").getTime()));
            
            TestDBUtility.deleteAll(EqtypeContractRow.TYPE);
            EqtypeContractParams l_eqtypeContractParams =
                TestDBUtility.getEqtypeContractRow();
            l_eqtypeContractParams.setTaxType(TaxTypeEnum.NORMAL);
            l_eqtypeContractParams.setContractType(ContractTypeEnum.LONG);
            l_eqtypeContractParams.setProductId(3304148080001L);
            l_eqtypeContractParams.setMarketId(3303);
            TestDBUtility.insertWithDel(l_eqtypeContractParams);
            
            TestDBUtility.deleteAll(EqtypeClosingContractSpecRow.TYPE);
            EqtypeClosingContractSpecParams l_eqtypeClosingContractSpecParams =
                TestDBUtility.getEqtypeClosingContractSpecRow();
            l_eqtypeClosingContractSpecParams.setContractId(2134566345L);
            TestDBUtility.insertWithDel(l_eqtypeClosingContractSpecParams);
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams =
                TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderId(33381L);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.SWAP_MARGIN);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_eqProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqProductParams.setProductId(3304148080001L);
            l_eqProductParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_eqProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_eqProductTradedParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqProductTradedParams.setProductId(3304148080001L);
            l_eqProductTradedParams.setMarketId(3303);
            l_eqProductTradedParams.setTradedProductId(1006160060005L);
            l_eqProductTradedParams.setValidUntilBizDate(null);
            TestDBUtility.insertWithDel(l_eqProductTradedParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080001L);
            l_tradedProductParams.setMarketId(3303);
            l_tradedProductParams.setTradedProductId(1006160060005L);
            l_tradedProductParams.setValidUntilBizDate(null);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);

            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams =
                TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080001L);
            l_productParams.setInstitutionCode("0D");
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams =
                TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("N1");
            l_marketParams.setMarketId(3303L);
            l_marketParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_marketParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams =
                TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionCode("0D");
            l_branchParams.setInstitutionId(63);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccoutParams = TestDBUtility.getMainAccountRow();
            l_mainAccoutParams.setAccountId(333812512203L);
            l_mainAccoutParams.setAccountCode("123456");
            TestDBUtility.insertWithDel(l_mainAccoutParams);
            
            
            String l_strForcedSettleReasonType = "1";
            WEB3TPContractForcedSettleResult l_contractForceSettleResult = new WEB3TPContractForcedSettleResult();
            l_contractForceSettleResult.additionalMarginAccruedDays = Integer.getInteger("20070508");
            l_contractForceSettleResult.additionalMarginDate = WEB3DateUtility.getDate("20070508", "yyyyMMdd");
            l_contractForceSettleResult.forcedSettleReason = "0001";
            l_contractForceSettleResult.marginMaintenanceRate = new Double(12.0033);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "isForcedSettleOrder",
                    new Class[] {EqTypeOrderUnit.class},
                    new Boolean(true));
            
            
            forTestWEB3AdminEquityForcedSettleTempOrderCreateUnitServiceImpl l_impl =
                new forTestWEB3AdminEquityForcedSettleTempOrderCreateUnitServiceImpl();
            l_impl.execTempOrderCreation(l_eqtypeContractParams, l_strForcedSettleReasonType, l_contractForceSettleResult, true);
            Long l_lngAccountIdCtx = (Long)ThreadLocalSystemAttributesRegistry.getAttribute("MPDS_KEY_VALUE");
            assertEquals(new Long(333812512203L), l_lngAccountIdCtx);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testInsertForceSettleErrorOrder()
    {
        final String STR_METHOD_NAME = "testInsertForceSettleErrorOrder()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(EqtypeContractRow.TYPE);
            EqtypeContractParams l_eqtypeContractParams =
                TestDBUtility.getEqtypeContractRow();
            l_eqtypeContractParams.setTaxType(TaxTypeEnum.NORMAL);
            l_eqtypeContractParams.setContractType(ContractTypeEnum.LONG);
            TestDBUtility.insertWithDel(l_eqtypeContractParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            l_mainAccountParams.setAccountCode("1234567");/////ˆÊÉ
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(12342);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_tradedProductParams.setMarketId(33381L);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(12342);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(12342);
            l_eqtypeProductParams.setProductCode("N8080");
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(12342);
            l_eqtypeTradedProductParams.setMarketId(33381L);
            l_eqtypeTradedProductParams.setTradedProductId(1006160060005L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(33381L);
            TestDBUtility.insertWithDel(l_marketParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(WEB3DateUtility.getDate("20040202","yyyyMMdd"));
            
            EqtypeContractRow l_eqtypeContractRow = EqtypeContractDao.findRowByContractId(2134566345L);
            String l_strForcedSettleResonDiv = "1";
            WEB3TPContractForcedSettleResult l_contractForceSettleResult =
                new WEB3TPContractForcedSettleResult();
            l_contractForceSettleResult.additionalMarginAccruedDays = new Integer(4);
            l_contractForceSettleResult.additionalMarginDate = WEB3DateUtility.getDate("20040202","yyyyMMdd");
            l_contractForceSettleResult.marginMaintenanceRate = new Double(0.24);
            double l_dblOrderNumber = 20;
            Double l_limitPrice = new Double(20);
            String l_strOrderErrorReasonCode = "2";
            
            TestDBUtility.deleteAll(ForcedSettleOrderInqRow.TYPE);
            l_mpl.insertForceSettleErrorOrder(l_eqtypeContractRow,
                l_strForcedSettleResonDiv,
                l_contractForceSettleResult,
                new Long(123),
                l_dblOrderNumber,
                l_limitPrice,
                l_strOrderErrorReasonCode);
            List l_row =
                ForcedSettleOrderInqDao.findRowsByBranchId(33381);
            ForcedSettleOrderInqParams l_forcedSettleErrorOrderParams =
                new ForcedSettleOrderInqParams((ForcedSettleOrderInqRow)l_row.get(0));
            assertEquals(l_forcedSettleErrorOrderParams.getApproveStatusType(), "9");
            assertEquals("", l_forcedSettleErrorOrderParams.getAccountId(), 333812512203L);
            assertEquals("", l_forcedSettleErrorOrderParams.getSubAccountId(), 33381251220301L);
            assertEquals("123456", l_forcedSettleErrorOrderParams.getAccountCode());
            assertEquals("N8080", l_forcedSettleErrorOrderParams.getProductCode());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testGetLimitPrice()
    {
        final String STR_METHOD_NAME = "testGetLimitPrice()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(12342);
            l_tradedProductParams.setMarketId(33381L);
            l_tradedProductParams.setTradedProductId(330304148080000L);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(12342);
            l_eqtypeTradedProductParams.setMarketId(33381L);
            l_eqtypeTradedProductParams.setTradedProductId(330304148080000L);
            l_eqtypeTradedProductParams.setLastClosingPrice(80);
            l_eqtypeTradedProductParams.setOpenOtcDiv("3");
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(12342);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(12342);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(33381L);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(EquityLimitPriceRangeMstRow.TYPE);
            EquityLimitPriceRangeMstParams l_equityLimitPriceRangeMstParams =
                this.getEquityLimitPriceRangeMstRow();
            TestDBUtility.insertWithDel(l_equityLimitPriceRangeMstParams);
            
            TestDBUtility.deleteAll(EqtypeContractParams.TYPE);
            EqtypeContractParams l_eqtypeContractParams = TestDBUtility.getEqtypeContractRow();
            TestDBUtility.insertWithDel(l_eqtypeContractParams);
            
            TestDBUtility.deleteAll(EquityTickValuesMstRow.TYPE);
            EquityTickValuesMstParams l_equityTickValuesMstParams = this.getEquityTickValuesMstRow();
            TestDBUtility.insertWithDel(l_equityTickValuesMstParams);
            
            TradedProductRow l_row = TradedProductDao.findRowByPk(330304148080000L);
            WEB3EquityTradedProduct l_tradedProduct = new WEB3EquityTradedProduct(l_row);
            
            WEB3EquityContract l_eqtypeContract = new WEB3EquityContract(l_eqtypeContractParams);

            double l_dblPrice = l_mpl.getLimitPrice(l_tradedProduct, true, l_eqtypeContract);
            assertEquals(l_dblPrice, new Double(100).doubleValue(), new Double(10).doubleValue());
            
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetLimitPrice2()
    {
        final String STR_METHOD_NAME = "testGetLimitPrice2()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(12342);
            l_tradedProductParams.setMarketId(33381L);
            l_tradedProductParams.setTradedProductId(330304148080000L);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(12342);
            l_eqtypeTradedProductParams.setMarketId(33381L);
            l_eqtypeTradedProductParams.setTradedProductId(330304148080000L);
            l_eqtypeTradedProductParams.setLastClosingPrice(80);
            l_eqtypeTradedProductParams.setOpenOtcDiv("3");
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(12342);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(33381L);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(12342);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(EquityLimitPriceRangeMstRow.TYPE);
            EquityLimitPriceRangeMstParams l_equityLimitPriceRangeMstParams =
                this.getEquityLimitPriceRangeMstRow();
            TestDBUtility.insertWithDel(l_equityLimitPriceRangeMstParams);
            
            TestDBUtility.deleteAll(EquityTickValuesMstRow.TYPE);
            EquityTickValuesMstParams l_equityTickValuesMstParams = this.getEquityTickValuesMstRow();
            TestDBUtility.insertWithDel(l_equityTickValuesMstParams);
            
            TestDBUtility.deleteAll(EqtypeContractParams.TYPE);
            EqtypeContractParams l_eqtypeContractParams = TestDBUtility.getEqtypeContractRow();
            TestDBUtility.insertWithDel(l_eqtypeContractParams);
            
            TradedProductRow l_row = TradedProductDao.findRowByPk(330304148080000L);
            WEB3EquityTradedProduct l_tradedProduct = new WEB3EquityTradedProduct(l_row);

            WEB3EquityContract l_eqtypeContract = new WEB3EquityContract(l_eqtypeContractParams);
            
            double l_dblPrice = l_mpl.getLimitPrice(l_tradedProduct, false, l_eqtypeContract);
            assertEquals(l_dblPrice, new Double(70).doubleValue(), new Double(10).doubleValue());
            
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetLimitPrice3()
    {
        final String STR_METHOD_NAME = "testGetLimitPrice3()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(12342);
            l_tradedProductParams.setMarketId(33381L);
            l_tradedProductParams.setTradedProductId(330304148080000L);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(12342);
            l_eqtypeTradedProductParams.setMarketId(33381L);
            l_eqtypeTradedProductParams.setTradedProductId(330304148080000L);
            l_eqtypeTradedProductParams.setLastClosingPrice(80);
            l_eqtypeTradedProductParams.setOpenOtcDiv("2");
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(12342);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(EqtypeContractParams.TYPE);
            EqtypeContractParams l_eqtypeContractParams = TestDBUtility.getEqtypeContractRow();
            TestDBUtility.insertWithDel(l_eqtypeContractParams);
            
            TradedProductRow l_row = TradedProductDao.findRowByPk(330304148080000L);
            WEB3EquityTradedProduct l_tradedProduct = new WEB3EquityTradedProduct(l_row);
            
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(12342);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(33381L);
            TestDBUtility.insertWithDel(l_marketParams);

            WEB3EquityContract l_eqtypeContract = new WEB3EquityContract(l_eqtypeContractParams);
            
            double l_dblPrice = l_mpl.getLimitPrice(l_tradedProduct, false, l_eqtypeContract);
            assertEquals(l_dblPrice, new Double(0).doubleValue(), new Double(10).doubleValue());
            
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetLimitPrice4()
    {
        final String STR_METHOD_NAME = "testGetLimitPrice4()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(12342);
            l_tradedProductParams.setMarketId(33381L);
            l_tradedProductParams.setTradedProductId(330304148080000L);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(12342);
            l_eqtypeTradedProductParams.setMarketId(33381L);
            l_eqtypeTradedProductParams.setTradedProductId(330304148080000L);
            l_eqtypeTradedProductParams.setLastClosingPrice(0);
            l_eqtypeTradedProductParams.setOpenOtcDiv("3");
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(12342);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(12342);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(33381L);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(EquityLimitPriceRangeMstRow.TYPE);
            EquityLimitPriceRangeMstParams l_equityLimitPriceRangeMstParams =
                this.getEquityLimitPriceRangeMstRow();
            TestDBUtility.insertWithDel(l_equityLimitPriceRangeMstParams);
            
            TestDBUtility.deleteAll(EqtypeContractParams.TYPE);
            EqtypeContractParams l_eqtypeContractParams = TestDBUtility.getEqtypeContractRow();
            l_eqtypeContractParams.setContractPrice(100);
            TestDBUtility.insertWithDel(l_eqtypeContractParams);
            
            TestDBUtility.deleteAll(EquityTickValuesMstRow.TYPE);
            EquityTickValuesMstParams l_equityTickValuesMstParams = this.getEquityTickValuesMstRow();
            TestDBUtility.insertWithDel(l_equityTickValuesMstParams);
            
            TradedProductRow l_row = TradedProductDao.findRowByPk(330304148080000L);
            WEB3EquityTradedProduct l_tradedProduct = new WEB3EquityTradedProduct(l_row);
            
            WEB3EquityContract l_eqtypeContract = new WEB3EquityContract(l_eqtypeContractParams);

            double l_dblPrice = l_mpl.getLimitPrice(l_tradedProduct, true, l_eqtypeContract);
            assertEquals(l_dblPrice, new Double(100).doubleValue(), new Double(10).doubleValue());
            
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExecCloseNotice()
    {
        final String STR_METHOD_NAME = "testExecCloseNotice()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(EqtypeOrderExecSendMailRow.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setAccountId(333812512203L);
            l_eqtypeOrderUnitParams.setOrderRequestNumber("000003006");
            l_eqtypeOrderUnitParams.setSubAccountId(33381251220301L);
            l_eqtypeOrderUnitParams.setMarketId(3303L);
            l_eqtypeOrderUnitParams.setProductId(12342);
            l_eqtypeOrderUnitParams.setOrderId(123456789L);
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.CANCELLED);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TestDBUtility.deleteAll(EqtypeOrderRow.TYPE);
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            l_eqtypeOrderParams.setOrderId(123456789L);
            l_eqtypeOrderParams.setAccountId(333812512203L);
            l_eqtypeOrderParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_marketParams);
            
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512203L);
            l_subAccountParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.service.delegate.stdimpls.WEB3EquityReceiveCloseOrderUnitServiceImpl",
                    "execCloseOrder",
                    new Class[] {HostEqtypeCloseOrderNotifyParams.class, EqTypeOrderUnit.class},
                    null);
            
            l_mpl.execCloseNotice(l_eqtypeOrderUnitParams);
            
            WEB3MockObjectParamsValue l_value =
                TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                        "webbroker3.equity.service.delegate.stdimpls.WEB3EquityReceiveCloseOrderUnitServiceImpl",
                        "execCloseOrder",
                        new Class[] {HostEqtypeCloseOrderNotifyParams.class, EqTypeOrderUnit.class});
            HostEqtypeCloseOrderNotifyParams l_params =
                (HostEqtypeCloseOrderNotifyParams)l_value.getCalled(0)[0];
            assertEquals("AI813", l_params.getRequestCode());
            assertEquals("0D", l_params.getInstitutionCode());
            assertEquals("381", l_params.getBranchCode());
            assertEquals("2512246", l_params.getAccountCode());
            assertEquals(null, l_params.getTraderCode());
            assertEquals("000003006", l_params.getOrderRequestNumber());
            assertEquals(500, l_params.getExecutedQuantity(), new Double(10).doubleValue());
            assertEquals("9", l_params.getReasonCode());
            assertEquals("1", l_params.getCloseNotifyType());
            assertEquals("0000", l_params.getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExecCloseNotice2()
    {
        final String STR_METHOD_NAME = "testExecCloseNotice2()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderRequestNumber("000003006");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
                
            l_mpl.execCloseNotice(l_eqtypeOrderUnitParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
            
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExpireRsvSettleOrder()
    {
        final String STR_METHOD_NAME = "testExpireRsvSettleOrder()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAllAndCommit(RsvEqOrderActionRow.TYPE);
            TestDBUtility.deleteAll(RsvEqOrderUnitRow.TYPE);
            RsvEqOrderUnitParams l_rsvEqOrderUnitParams = TestDBUtility.getRsvEqOrderUnitRow();
            l_rsvEqOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            TestDBUtility.insertWithDel(l_rsvEqOrderUnitParams);
            
            RsvEqOrderUnitRow l_rsvEqOrderUnitRow = RsvEqOrderUnitDao.findRowByOrderId(1001);
            
            l_mpl.expireRsvSettleOrder(l_rsvEqOrderUnitRow);
            RsvEqOrderUnitRow l_rsvEqOrderUnitRow1 = RsvEqOrderUnitDao.findRowByOrderId(1001);
            RsvEqOrderUnitParams l_rsvEqOrderUnitParams1 = new RsvEqOrderUnitParams(l_rsvEqOrderUnitRow1);
            assertEquals("1", l_rsvEqOrderUnitParams1.getForcedExpireType());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExpireRsvSettleOrder2()
    {
        final String STR_METHOD_NAME = "testExpireRsvSettleOrder2()";
        log.entering(STR_METHOD_NAME);
        
        try
        {    
            TestDBUtility.deleteAll(RsvEqOrderUnitRow.TYPE);
            RsvEqOrderUnitParams l_rsvEqOrderUnitParams = TestDBUtility.getRsvEqOrderUnitRow();
            l_rsvEqOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            TestDBUtility.insertWithDel(l_rsvEqOrderUnitParams);
            
            RsvEqOrderUnitRow l_rsvEqOrderUnitRow = RsvEqOrderUnitDao.findRowByOrderId(1001);
            
            l_mpl.expireRsvSettleOrder(l_rsvEqOrderUnitRow);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExpireSettleOrder()
    {
        final String STR_METHOD_NAME = "testExpireSettleOrder()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            l_mainAccountParams.setAccountCode("123456");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderRequestNumber("000003006");
            l_eqtypeOrderUnitParams.setOrderConditionType("0");
            l_eqtypeOrderUnitParams.setRequestType("1");
            l_eqtypeOrderUnitParams.setOrderRev("11");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(HostEqtypeOrderAllRow.TYPE);
            HostEqtypeOrderAllParams l_hostEqtypeOrderAllParams =
                TestDBUtility.getHostEqtypeOrderAllRow();
            l_hostEqtypeOrderAllParams.setRequestCode("AI801");
            l_hostEqtypeOrderAllParams.setBranchCode("381");
            l_hostEqtypeOrderAllParams.setInstitutionCode("0D");
            l_hostEqtypeOrderAllParams.setOrderRequestNumber("000003006");
            l_hostEqtypeOrderAllParams.setCorpCode("123456789012345111");
            l_hostEqtypeOrderAllParams.setStatus("0");
            TestDBUtility.insertWithDel(l_hostEqtypeOrderAllParams);
            
            forTestWEB3AdminEquityForcedSettleTempOrderCreateUnitServiceImpl1 l_impl =
                new forTestWEB3AdminEquityForcedSettleTempOrderCreateUnitServiceImpl1();
            
            l_impl.expireSettleOrder(l_eqtypeOrderUnitParams);
            
            List l_lisHostEqtypeOrderAllRow1 = HostEqtypeOrderAllDao.findRowsByCorpCode("123456789012345111");
            HostEqtypeOrderAllRow l_hostEqtypeOrderAllRow = (HostEqtypeOrderAllRow)l_lisHostEqtypeOrderAllRow1.get(0);
            HostEqtypeOrderAllParams l_hostEqtypeOrderAllParams1 =
                new HostEqtypeOrderAllParams(l_hostEqtypeOrderAllRow);
            int l_intSerialNo = l_hostEqtypeOrderAllParams1.getOrderActionSerialNo();
            assertEquals(1, l_intSerialNo);
            assertEquals("4", l_hostEqtypeOrderAllParams1.getStatus());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    public void testExpireSettleOrder2()
    {
        final String STR_METHOD_NAME = "testExpireSettleOrder2()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderRequestNumber("000003006");
            l_eqtypeOrderUnitParams.setOrderConditionType("0");
            l_eqtypeOrderUnitParams.setRequestType("1");
            l_eqtypeOrderUnitParams.setOrderRev("1");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33382L);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(HostEqtypeOrderAllRow.TYPE);
            HostEqtypeOrderAllParams l_hostEqtypeOrderAllParams =
                TestDBUtility.getHostEqtypeOrderAllRow();
            l_hostEqtypeOrderAllParams.setRequestCode("AI801");
            l_hostEqtypeOrderAllParams.setBranchCode("381");
            l_hostEqtypeOrderAllParams.setInstitutionCode("0D");
            l_hostEqtypeOrderAllParams.setOrderRequestNumber("000003006");
            l_hostEqtypeOrderAllParams.setCorpCode("1");
            TestDBUtility.insertWithDel(l_hostEqtypeOrderAllParams);
            
            forTestWEB3AdminEquityForcedSettleTempOrderCreateUnitServiceImpl1 l_impl =
                new forTestWEB3AdminEquityForcedSettleTempOrderCreateUnitServiceImpl1();
            
            l_impl.expireSettleOrder(l_eqtypeOrderUnitParams);
            
            List l_lisHostEqtypeOrderAllRow1 = HostEqtypeOrderAllDao.findRowsByCorpCode("1");
            HostEqtypeOrderAllRow l_hostEqtypeOrderAllRow = (HostEqtypeOrderAllRow)l_lisHostEqtypeOrderAllRow1.get(0);
            HostEqtypeOrderAllParams l_hostEqtypeOrderAllParams1 =
                new HostEqtypeOrderAllParams(l_hostEqtypeOrderAllRow);
            int l_intSerialNo = l_hostEqtypeOrderAllParams1.getOrderActionSerialNo();
            assertEquals("4", l_hostEqtypeOrderAllParams1.getStatus());
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
//    public void testExecTempOrderCreation2()
//    {
//        final String STR_METHOD_NAME = "testExecTempOrderCreation2()";
//        log.entering(STR_METHOD_NAME);
//        
//        try
//        {
//            TestDBUtility.deleteAll(EqtypeContractRow.TYPE);
//            EqtypeContractParams l_eqtypeContractParams =
//                TestDBUtility.getEqtypeContractRow();
//            l_eqtypeContractParams.setTaxType(TaxTypeEnum.NORMAL);
//            l_eqtypeContractParams.setContractType(ContractTypeEnum.LONG);
//            TestDBUtility.insertWithDel(l_eqtypeContractParams);
//            
//            TestDBUtility.deleteAll(EqtypeClosingContractSpecRow.TYPE);
//            EqtypeClosingContractSpecParams l_eqtypeClosingContractSpecParams =
//                TestDBUtility.getEqtypeClosingContractSpecRow();
//            l_eqtypeClosingContractSpecParams.setContractId(2134566345L);
//            TestDBUtility.insertWithDel(l_eqtypeClosingContractSpecParams);
//            
//            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
//            EqtypeOrderUnitParams l_eqtypeOrderUnitParams =
//                TestDBUtility.getEqtypeOrderUnitRow();
//            l_eqtypeOrderUnitParams.setOrderId(33381L);
//            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
//            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.SWAP_MARGIN);
//            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
//            
//            String l_strForcedSettleReasonType = "1";
//            WEB3TPContractForcedSettleResult l_contractForceSettleResult = new WEB3TPContractForcedSettleResult();
//            l_contractForceSettleResult.additionalMarginAccruedDays = Integer.getInteger("20070508");
//            l_contractForceSettleResult.additionalMarginDate = WEB3DateUtility.getDate("20070508", "yyyyMMdd");
//            l_contractForceSettleResult.forcedSettleReason = "0001";
//            l_contractForceSettleResult.marginMaintenanceRate = new Double(12.0033);
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.equity.WEB3EquityOrderManager",
//                    "isForcedSettleOrder",
//                    new Class[] {EqTypeOrderUnit.class},
//                    new Boolean(true));
//            
//            
//            forTestWEB3AdminEquityForcedSettleTempOrderCreateUnitServiceImpl l_impl =
//                new forTestWEB3AdminEquityForcedSettleTempOrderCreateUnitServiceImpl();
//            l_impl.execTempOrderCreation(l_eqtypeContractParams, l_strForcedSettleReasonType, l_contractForceSettleResult, true);
//            
//        }
//        catch (Exception l_ex)
//        {
//            log.debug(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        
//        log.exiting(STR_METHOD_NAME);
//    }
    
    public void testExecTempOrderCreation3()
    {
        final String STR_METHOD_NAME = "testExecTempOrderCreation3()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(EqtypeContractRow.TYPE);
            EqtypeContractParams l_eqtypeContractParams =
                TestDBUtility.getEqtypeContractRow();
            l_eqtypeContractParams.setTaxType(TaxTypeEnum.NORMAL);
            l_eqtypeContractParams.setContractType(ContractTypeEnum.LONG);
            TestDBUtility.insertWithDel(l_eqtypeContractParams);
            
            TestDBUtility.deleteAll(RsvEqClosingContractSpecRow.TYPE);
            RsvEqClosingContractSpecParams l_rsvEqClosingContractSpecParams = this.getRsvEqClosingContractSpecRow();
            l_rsvEqClosingContractSpecParams.setContractId(2134566345L);
            TestDBUtility.insertWithDel(l_rsvEqClosingContractSpecParams);
            
            TestDBUtility.deleteAll(RsvEqOrderUnitRow.TYPE);
            RsvEqOrderUnitParams l_rsvEqOrderUnitParams =
                TestDBUtility.getRsvEqOrderUnitRow();
            l_rsvEqOrderUnitParams.setOrderId(33381L);
            l_rsvEqOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_rsvEqOrderUnitParams.setOrderCateg(OrderCategEnum.SWAP_MARGIN);
            TestDBUtility.insertWithDel(l_rsvEqOrderUnitParams);
            
            TestDBUtility.deleteAll(HostEqtypeOrderAllRow.TYPE);
            HostEqtypeOrderAllParams l_hostEqtypeOrderAllParams =
                TestDBUtility.getHostEqtypeOrderAllRow();
            TestDBUtility.insertWithDel(l_hostEqtypeOrderAllParams);
            
            String l_strForcedSettleReasonType = "1";
            WEB3TPContractForcedSettleResult l_contractForceSettleResult = new WEB3TPContractForcedSettleResult();
            l_contractForceSettleResult.additionalMarginAccruedDays = Integer.getInteger("20070508");
            l_contractForceSettleResult.additionalMarginDate = WEB3DateUtility.getDate("20070508", "yyyyMMdd");
            l_contractForceSettleResult.forcedSettleReason = "0001";
            l_contractForceSettleResult.marginMaintenanceRate = new Double(12.0033);
            
//            HostEqtypeOrderAllRow l_hostEqtypeOrderAllRow = HostEqtypeOrderAllDao.findDaoByPk();
//            HostEqtypeOrderAllParams l_hostEqtypeOrderAllParams1 = new HostEqtypeOrderAllParams(l_hostEqtypeOrderAllRow);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "isForcedSettleOrder",
                    new Class[] {EqTypeOrderUnit.class},
                    new Boolean(true));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.service.delegate.stdimpls.WEB3EquityFrontOrderServiceImpl",
                    "lockHostEqtypeOrderAll",
                    new Class[] {EqTypeOrderUnit.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.service.delegate.stdimpls.WEB3EquityFrontOrderServiceImpl",
                    "getHostEqtypeOrderAll",
                    new Class[] {EqTypeOrderUnit.class},
                    l_hostEqtypeOrderAllParams);
            forTestWEB3AdminEquityForcedSettleTempOrderCreateUnitServiceImpl l_impl =
                new forTestWEB3AdminEquityForcedSettleTempOrderCreateUnitServiceImpl();
            l_impl.execTempOrderCreation(l_eqtypeContractParams, l_strForcedSettleReasonType, l_contractForceSettleResult, true);
            
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExecTempOrderCreation()
    {
        final String STR_METHOD_NAME = "testExecTempOrderCreation()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(EqtypeContractRow.TYPE);
            EqtypeContractParams l_eqtypeContractParams =
                TestDBUtility.getEqtypeContractRow();
            l_eqtypeContractParams.setTaxType(TaxTypeEnum.NORMAL);
            l_eqtypeContractParams.setContractType(ContractTypeEnum.LONG);
            l_eqtypeContractParams.setProductId(3304148080001L);
            l_eqtypeContractParams.setMarketId(3303);
            TestDBUtility.insertWithDel(l_eqtypeContractParams);
            
            TestDBUtility.deleteAll(EqtypeClosingContractSpecRow.TYPE);
            EqtypeClosingContractSpecParams l_eqtypeClosingContractSpecParams =
                TestDBUtility.getEqtypeClosingContractSpecRow();
            l_eqtypeClosingContractSpecParams.setContractId(2134566345L);
            TestDBUtility.insertWithDel(l_eqtypeClosingContractSpecParams);
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams =
                TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderId(33381L);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.SWAP_MARGIN);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TestDBUtility.deleteAll(HostEqtypeOrderAllRow.TYPE);
            HostEqtypeOrderAllParams l_hostEqtypeOrderAllParams =
                TestDBUtility.getHostEqtypeOrderAllRow();
            TestDBUtility.insertWithDel(l_hostEqtypeOrderAllParams);
            
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_eqProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqProductParams.setProductId(3304148080001L);
            l_eqProductParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_eqProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_eqProductTradedParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqProductTradedParams.setProductId(3304148080001L);
            l_eqProductTradedParams.setMarketId(3303);
            l_eqProductTradedParams.setTradedProductId(1006160060005L);
            l_eqProductTradedParams.setValidUntilBizDate(null);
            TestDBUtility.insertWithDel(l_eqProductTradedParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080001L);
            l_tradedProductParams.setMarketId(3303);
            l_tradedProductParams.setTradedProductId(1006160060005L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_tradedProductParams.setValidUntilBizDate(null);
            TestDBUtility.insertWithDel(l_tradedProductParams);

            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams =
                TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080001L);
            l_productParams.setInstitutionCode("0D");
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams =
                TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("N1");
            l_marketParams.setMarketId(3303L);
            l_marketParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_marketParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams =
                TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionCode("0D");
            l_branchParams.setInstitutionId(63);
            TestDBUtility.insertWithDel(l_branchParams);
            
            String l_strForcedSettleReasonType = "1";
            WEB3TPContractForcedSettleResult l_contractForceSettleResult = new WEB3TPContractForcedSettleResult();
            l_contractForceSettleResult.additionalMarginAccruedDays = Integer.getInteger("20070508");
            l_contractForceSettleResult.additionalMarginDate = WEB3DateUtility.getDate("20070508", "yyyyMMdd");
            l_contractForceSettleResult.forcedSettleReason = "0001";
            l_contractForceSettleResult.marginMaintenanceRate = new Double(12.0033);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "isForcedSettleOrder",
                    new Class[] {EqTypeOrderUnit.class},
                    new Boolean(false));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.service.delegate.stdimpls.WEB3EquityFrontOrderServiceImpl",
                    "lockHostEqtypeOrderAll",
                    new Class[] {EqTypeOrderUnit.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.service.delegate.stdimpls.WEB3EquityFrontOrderServiceImpl",
                    "getHostEqtypeOrderAll",
                    new Class[] {EqTypeOrderUnit.class},
                    l_hostEqtypeOrderAllParams);
            
            forTestWEB3AdminEquityForcedSettleTempOrderCreateUnitServiceImpl l_impl =
                new forTestWEB3AdminEquityForcedSettleTempOrderCreateUnitServiceImpl();
            l_impl.execTempOrderCreation(l_eqtypeContractParams, l_strForcedSettleReasonType, l_contractForceSettleResult, true);
            
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
//    public void testExecTempOrderCreation2()
//    {
//        final String STR_METHOD_NAME = "testExecTempOrderCreation2()";
//        log.entering(STR_METHOD_NAME);
//        
//        try
//        {
//            TestDBUtility.deleteAll(EqtypeContractRow.TYPE);
//            EqtypeContractParams l_eqtypeContractParams =
//                TestDBUtility.getEqtypeContractRow();
//            l_eqtypeContractParams.setTaxType(TaxTypeEnum.NORMAL);
//            l_eqtypeContractParams.setContractType(ContractTypeEnum.LONG);
//            TestDBUtility.insertWithDel(l_eqtypeContractParams);
//            
//            TestDBUtility.deleteAll(EqtypeClosingContractSpecRow.TYPE);
//            EqtypeClosingContractSpecParams l_eqtypeClosingContractSpecParams =
//                TestDBUtility.getEqtypeClosingContractSpecRow();
//            l_eqtypeClosingContractSpecParams.setContractId(2134566345L);
//            TestDBUtility.insertWithDel(l_eqtypeClosingContractSpecParams);
//            
//            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
//            EqtypeOrderUnitParams l_eqtypeOrderUnitParams =
//                TestDBUtility.getEqtypeOrderUnitRow();
//            l_eqtypeOrderUnitParams.setOrderId(33381L);
//            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
//            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.SWAP_MARGIN);
//            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
//            
//            String l_strForcedSettleReasonType = "1";
//            WEB3TPContractForcedSettleResult l_contractForceSettleResult = new WEB3TPContractForcedSettleResult();
//            l_contractForceSettleResult.additionalMarginAccruedDays = Integer.getInteger("20070508");
//            l_contractForceSettleResult.additionalMarginDate = WEB3DateUtility.getDate("20070508", "yyyyMMdd");
//            l_contractForceSettleResult.forcedSettleReason = "0001";
//            l_contractForceSettleResult.marginMaintenanceRate = new Double(12.0033);
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.equity.WEB3EquityOrderManager",
//                    "isForcedSettleOrder",
//                    new Class[] {EqTypeOrderUnit.class},
//                    new Boolean(true));
//            
//            
//            forTestWEB3AdminEquityForcedSettleTempOrderCreateUnitServiceImpl l_impl =
//                new forTestWEB3AdminEquityForcedSettleTempOrderCreateUnitServiceImpl();
//            l_impl.execTempOrderCreation(l_eqtypeContractParams, l_strForcedSettleReasonType, l_contractForceSettleResult, true);
//            
//        }
//        catch (Exception l_ex)
//        {
//            log.debug(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        
//        log.exiting(STR_METHOD_NAME);
//    }
    

    
    public void testExecTempOrderCreation4()
    {
        final String STR_METHOD_NAME = "testExecTempOrderCreation4()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(EqtypeContractRow.TYPE);
            EqtypeContractParams l_eqtypeContractParams =
                TestDBUtility.getEqtypeContractRow();
            l_eqtypeContractParams.setTaxType(TaxTypeEnum.NORMAL);
            l_eqtypeContractParams.setProductId(3304148080001L);
            l_eqtypeContractParams.setContractType(ContractTypeEnum.LONG);
            TestDBUtility.insertWithDel(l_eqtypeContractParams);
            
            TestDBUtility.deleteAll(EqtypeClosingContractSpecRow.TYPE);
            EqtypeClosingContractSpecParams l_eqtypeClosingContractSpecParams =
                TestDBUtility.getEqtypeClosingContractSpecRow();
            l_eqtypeClosingContractSpecParams.setContractId(2134566345L);
            TestDBUtility.insertWithDel(l_eqtypeClosingContractSpecParams);
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams =
                TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderId(33381L);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.CLOSE_MARGIN);
            l_eqtypeOrderUnitParams.setOrderConditionType("2");
            l_eqtypeOrderUnitParams.setRequestType("1");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_eqProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqProductParams.setProductId(3304148080001L);
            l_eqProductParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_eqProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_eqProductTradedParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqProductTradedParams.setProductId(3304148080001L);
            l_eqProductTradedParams.setMarketId(3303);
            l_eqProductTradedParams.setTradedProductId(1006160060005L);
            l_eqProductTradedParams.setValidUntilBizDate(null);
            TestDBUtility.insertWithDel(l_eqProductTradedParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080001L);
            l_tradedProductParams.setMarketId(3303);
            l_tradedProductParams.setTradedProductId(1006160060005L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_tradedProductParams.setValidUntilBizDate(null);
            TestDBUtility.insertWithDel(l_tradedProductParams);

            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams =
                TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080001L);
            l_productParams.setInstitutionCode("0D");
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(HostEqtypeOrderAllRow.TYPE);
            HostEqtypeOrderAllParams l_hostEqtypeOrderAllParams =
                TestDBUtility.getHostEqtypeOrderAllRow();
            TestDBUtility.insertWithDel(l_hostEqtypeOrderAllParams);
            
            String l_strForcedSettleReasonType = "1";
            WEB3TPContractForcedSettleResult l_contractForceSettleResult = new WEB3TPContractForcedSettleResult();
            l_contractForceSettleResult.additionalMarginAccruedDays = Integer.getInteger("20070508");
            l_contractForceSettleResult.additionalMarginDate = WEB3DateUtility.getDate("20070508", "yyyyMMdd");
            l_contractForceSettleResult.forcedSettleReason = "0001";
            l_contractForceSettleResult.marginMaintenanceRate = new Double(12.0033);
            

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "isForcedSettleOrder",
                    new Class[] {EqTypeOrderUnit.class},
                    new Boolean(false));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.service.delegate.stdimpls.WEB3EquityFrontOrderServiceImpl",
                    "lockHostEqtypeOrderAll",
                    new Class[] {EqTypeOrderUnit.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.service.delegate.stdimpls.WEB3EquityFrontOrderServiceImpl",
                    "getHostEqtypeOrderAll",
                    new Class[] {EqTypeOrderUnit.class},
                    null);
            
            forTestWEB3AdminEquityForcedSettleTempOrderCreateUnitServiceImpl l_impl =
                new forTestWEB3AdminEquityForcedSettleTempOrderCreateUnitServiceImpl();
            l_impl.execTempOrderCreation(l_eqtypeContractParams, l_strForcedSettleReasonType, l_contractForceSettleResult, true);
            
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    
    public void testSubmitRepayTempOrderT_01()
    {
        final String STR_METHOD_NAME = "testSubmitRepayTempOrderT_01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //EqtypeContractParams
            EqtypeContractParams l_eqtypeContractParams =
                TestDBUtility.getEqtypeContractRow();
            l_eqtypeContractParams.setQuantity(123d);
            TestDBUtility.deleteAll(EqtypeContractRow.TYPE);
            TestDBUtility.insertWithDel(l_eqtypeContractParams);
            
            //EqtypeLockedContractDetailsParams
            EqtypeLockedContractDetailsParams l_detailsParams =
                this.getEqtypeLockedContractDetailsParams(
                    l_eqtypeContractParams, 123d);
            TestDBUtility.deleteAll(EqtypeLockedContractDetailsRow.TYPE);
            TestDBUtility.insertWithDel(l_detailsParams);

            //WEB3TPContractForcedSettleResult(Œš‹Ê‹­§ŒˆÏŒ‹‰Ê)
            WEB3TPContractForcedSettleResult l_contractForceSettleResult =
                new WEB3TPContractForcedSettleResult();
            l_contractForceSettleResult.additionalMarginAccruedDays = new Integer(4);
            l_contractForceSettleResult.additionalMarginDate = WEB3DateUtility.getDate("20040202","yyyyMMdd");
            l_contractForceSettleResult.marginMaintenanceRate = new Double(0.24);
            l_mpl.submitRepayTempOrder(
                l_eqtypeContractParams, 
                "123",
                l_contractForceSettleResult);
            log.info(STR_METHOD_NAME + "-------------------------->ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            l_exc.printStackTrace();
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    
    public void testSubmitRepayTempOrderT_02()
    {
        final String STR_METHOD_NAME = "testSubmitRepayTempOrderT_02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(BranchMarketRepayDealtCondRow.TYPE);
            BranchMarketRepayDealtCondParams l_BranchMarketRepayDealtCond =
                TestDBUtility.getBranchMarketRepayDealCondRow();
            l_BranchMarketRepayDealtCond.setBranchCode("381");
            l_BranchMarketRepayDealtCond.setInstitutionCode("0D");
            l_BranchMarketRepayDealtCond.setMarketCode("SP");
            l_BranchMarketRepayDealtCond.setRepaymentDiv("1");
            l_BranchMarketRepayDealtCond.setRepaymentNum(0);
            TestDBUtility.insertWithDel(l_BranchMarketRepayDealtCond);
            
            //EqtypeContractParams
            EqtypeContractParams l_eqtypeContractParams =
                TestDBUtility.getEqtypeContractRow();
            l_eqtypeContractParams.setQuantity(223d);
            l_eqtypeContractParams.setContractType(ContractTypeEnum.SHORT);
            l_eqtypeContractParams.setRepaymentType("1");
            l_eqtypeContractParams.setProductId(330304148080000L);
            TestDBUtility.insertWithDel(l_eqtypeContractParams);

            //EqtypeLockedContractDetailsParams
            EqtypeLockedContractDetailsParams l_detailsParams =
                this.getEqtypeLockedContractDetailsParams(
                    l_eqtypeContractParams, 123d);
            TestDBUtility.insertWithDel(l_detailsParams);

            //WEB3TPContractForcedSettleResult(Œš‹Ê‹­§ŒˆÏŒ‹‰Ê)
            WEB3TPContractForcedSettleResult l_contractForceSettleResult =
                new WEB3TPContractForcedSettleResult();
            l_contractForceSettleResult.additionalMarginAccruedDays = new Integer(4);
            l_contractForceSettleResult.additionalMarginDate = WEB3DateUtility.getDate("20040202","yyyyMMdd");
            l_contractForceSettleResult.marginMaintenanceRate = new Double(0.24);

            //TradedProduct
            TradedProductParams l_tradedProductParams =
                TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(l_eqtypeContractParams.getProductId());
            l_tradedProductParams.setMarketId(l_eqtypeContractParams.getMarketId());
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_tradedProductParams);

            //EquityTradedProduct
            EqtypeTradedProductParams l_eqtypeTradedProductParams =
                TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setTradedProductId(l_tradedProductParams.getTradedProductId());
            l_eqtypeTradedProductParams.setProductId(l_tradedProductParams.product_id);
            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);

            //EqtypeProductParams
            EqtypeProductParams l_eqtypeProductParams =
                TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(l_tradedProductParams.product_id);
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            //ProductParams
            ProductParams l_productPrarams =
                TestDBUtility.getProductRow();
            l_productPrarams.setProductId(l_tradedProductParams.product_id);
            l_productPrarams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.insertWithDel(l_productPrarams);

            //MarketParams
            MarketParams l_marketParams =
                TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(l_tradedProductParams.getMarketId());
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.insertWithDel(l_marketParams);
            
//            //
//            TestDBUtility.deleteAll(MainAccountRow.TYPE);
//            TestDBUtility.deleteAll(SubAccountRow.TYPE);
//            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.commit();
            l_mpl.submitRepayTempOrder(
                l_eqtypeContractParams, 
                "123",
                l_contractForceSettleResult);
            fail();

        }
        catch(WEB3SystemLayerException l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_exc.getErrorInfo());
            log.info(STR_METHOD_NAME + "-------------------------->ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            l_exc.printStackTrace();
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    /*
    public void testSubmitRepayTempOrderT_03()
    {
        final String STR_METHOD_NAME = "testSubmitRepayTempOrderT_03()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(BranchMarketRepayDealtCondRow.TYPE);
            BranchMarketRepayDealtCondParams l_BranchMarketRepayDealtCond =
                TestDBUtility.getBranchMarketRepayDealCondRow();
            l_BranchMarketRepayDealtCond.setBranchCode("381");
            l_BranchMarketRepayDealtCond.setInstitutionCode("0D");
            l_BranchMarketRepayDealtCond.setMarketCode("SP");
            l_BranchMarketRepayDealtCond.setRepaymentDiv("1");
            l_BranchMarketRepayDealtCond.setRepaymentNum(0);
            TestDBUtility.insertWithDel(l_BranchMarketRepayDealtCond);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            //EqtypeContractParams
            EqtypeContractParams l_eqtypeContractParams =
                TestDBUtility.getEqtypeContractRow();
            l_eqtypeContractParams.setQuantity(223d);
            l_eqtypeContractParams.setContractType(ContractTypeEnum.SHORT);
            l_eqtypeContractParams.setRepaymentType("1");
            Date l_closeDate = new Date(2007,9,2);
            l_eqtypeContractParams.setCloseDate(l_closeDate);
            TestDBUtility.insertWithDel(l_eqtypeContractParams);

            //EqtypeLockedContractDetailsParams
            EqtypeLockedContractDetailsParams l_detailsParams =
                this.getEqtypeLockedContractDetailsParams(
                    l_eqtypeContractParams, 123d);
            TestDBUtility.insertWithDel(l_detailsParams);

            //WEB3TPContractForcedSettleResult(Œš‹Ê‹­§ŒˆÏŒ‹‰Ê)
            WEB3TPContractForcedSettleResult l_contractForceSettleResult =
                new WEB3TPContractForcedSettleResult();
            l_contractForceSettleResult.additionalMarginAccruedDays = new Integer(4);
            l_contractForceSettleResult.additionalMarginDate = WEB3DateUtility.getDate("20040202","yyyyMMdd");
            l_contractForceSettleResult.marginMaintenanceRate = new Double(0.24);

            //TradedProduct
            TradedProductParams l_tradedProductParams =
                TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(l_eqtypeContractParams.getProductId());
            l_tradedProductParams.setMarketId(l_eqtypeContractParams.getMarketId());
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_tradedProductParams);

            //EquityTradedProduct
            EqtypeTradedProductParams l_eqtypeTradedProductParams =
                TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setTradedProductId(l_tradedProductParams.getTradedProductId());
            l_eqtypeTradedProductParams.setProductId(l_eqtypeContractParams.getProductId());
            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);

            //EqtypeProductParams
            EqtypeProductParams l_eqtypeProductParams =
                TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(l_tradedProductParams.product_id);
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            //ProductParams
            ProductParams l_productPrarams =
                TestDBUtility.getProductRow();
            l_productPrarams.setProductId(l_tradedProductParams.product_id);
            l_productPrarams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.insertWithDel(l_productPrarams);

            //MarketParams
            MarketParams l_marketParams =
                TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(l_tradedProductParams.getMarketId());
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.insertWithDel(l_marketParams);
            
            //SubAccountParams
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(l_eqtypeContractParams.getAccountId());
            l_subAccountParams.setSubAccountId(l_eqtypeContractParams.getSubAccountId());
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);            
            
            //InstitutionParams
            InstitutionParams l_insititutionParams =
                TestDBUtility.getInstitutionRow();
            l_insititutionParams.setInstitutionId(l_subAccountParams.getInstitutionId());
            l_insititutionParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(l_insititutionParams);

            //BranchParams
            BranchParams l_brachParams =
                TestDBUtility.getBranchRow();
            l_brachParams.setBranchId(l_subAccountParams.getBranchId());
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.insertWithDel(l_brachParams);

            //WEB3EquityBizLogicProvider.createCommission
            WEB3GentradeCommission l_commission = new WEB3GentradeCommission();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityBizLogicProvider",
                "createCommission",
                new Class[] {WEB3GentradeSubAccount.class, String.class, Date.class, String.class,String.class, double.class, OrderCategEnum.class},
                l_commission);

            //WEB3EquityOrderManager
            WEB3EquityRealizedProfitAndLossPrice l_lossPrice =
                new WEB3EquityRealizedProfitAndLossPrice();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityOrderManager",
                "calcEstimatedRealizedProfitAndLossAmount",                
                new Class[] {WEB3GentradeCommission.class,
                        double.class,
                        WEB3GentradeSubAccount.class,
                        WEB3EquityTradedProduct.class,
                        EqTypeSettleContractOrderEntry[].class,
                        double.class,
                        EqTypeOrderUnit.class,
                        double.class,
                        double.class,
                        boolean.class},
                l_lossPrice);
            Date l_bizDate = new Date(2007,5,2);
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_bizDate);

            //
//            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            l_mpl.submitRepayTempOrder(
                l_eqtypeContractParams, 
                "123",
                l_contractForceSettleResult);
            fail();

        }
        catch(WEB3SystemLayerException l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_exc.getErrorInfo());
            log.info(STR_METHOD_NAME + "-------------------------->ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            l_exc.printStackTrace();
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
*/
    public void testSubmitRepayTempOrderT_04()
    {
        final String STR_METHOD_NAME = "testSubmitRepayTempOrderT_04()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleTempOrderCreateUnitServiceImplMock l_implMock =
             new WEB3AdminEquityForcedSettleTempOrderCreateUnitServiceImplMock();
        try
        {
            TestDBUtility.deleteAll(BranchMarketRepayDealtCondRow.TYPE);
            BranchMarketRepayDealtCondParams l_BranchMarketRepayDealtCond =
                TestDBUtility.getBranchMarketRepayDealCondRow();
            l_BranchMarketRepayDealtCond.setBranchCode("381");
            l_BranchMarketRepayDealtCond.setInstitutionCode("0D");
            l_BranchMarketRepayDealtCond.setMarketCode("SP");
            l_BranchMarketRepayDealtCond.setRepaymentDiv("1");
            l_BranchMarketRepayDealtCond.setRepaymentNum(0);
            TestDBUtility.insertWithDel(l_BranchMarketRepayDealtCond);
            
            TestDBUtility.deleteAll(InstBranchProductParams.TYPE);
            InstBranchProductParams l_InstBranchProduct = new InstBranchProductParams();
            l_InstBranchProduct.setBranchId(33381);
            l_InstBranchProduct.setCommissionProductCode("10");
            l_InstBranchProduct.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_InstBranchProduct.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_InstBranchProduct);
            
            //EqtypeContractParams
            EqtypeContractParams l_eqtypeContractParams =
                TestDBUtility.getEqtypeContractRow();
            l_eqtypeContractParams.setQuantity(223d);
            l_eqtypeContractParams.setContractType(ContractTypeEnum.SHORT);
            l_eqtypeContractParams.setRepaymentType("1");
            Date l_closeDate = new Date(2007,9,2);
            l_eqtypeContractParams.setCloseDate(l_closeDate);
            TestDBUtility.insertWithDel(l_eqtypeContractParams);

            //EqtypeLockedContractDetailsParams
            EqtypeLockedContractDetailsParams l_detailsParams =
                this.getEqtypeLockedContractDetailsParams(
                    l_eqtypeContractParams, 123d);
            TestDBUtility.insertWithDel(l_detailsParams);

            //WEB3TPContractForcedSettleResult(Œš‹Ê‹­§ŒˆÏŒ‹‰Ê)
            WEB3TPContractForcedSettleResult l_contractForceSettleResult =
                new WEB3TPContractForcedSettleResult();
            l_contractForceSettleResult.additionalMarginAccruedDays = new Integer(4);
            l_contractForceSettleResult.additionalMarginDate = WEB3DateUtility.getDate("20040202","yyyyMMdd");
            l_contractForceSettleResult.marginMaintenanceRate = new Double(0.24);

            //TradedProduct
            TradedProductParams l_tradedProductParams =
                TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(l_eqtypeContractParams.getProductId());
            l_tradedProductParams.setMarketId(l_eqtypeContractParams.getMarketId());
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_tradedProductParams);

            //EquityTradedProduct
            EqtypeTradedProductParams l_eqtypeTradedProductParams =
                TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setTradedProductId(l_tradedProductParams.getTradedProductId());
            l_eqtypeTradedProductParams.setProductId(l_tradedProductParams.product_id);
            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);

            //EqtypeProductParams
            EqtypeProductParams l_eqtypeProductParams =
                TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(l_tradedProductParams.product_id);
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            //ProductParams
            ProductParams l_productPrarams =
                TestDBUtility.getProductRow();
            l_productPrarams.setProductId(l_tradedProductParams.product_id);
            l_productPrarams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.insertWithDel(l_productPrarams);

            //MarketParams
            MarketParams l_marketParams =
                TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(l_tradedProductParams.getMarketId());
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.insertWithDel(l_marketParams);
            
            //SubAccountParams
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(l_eqtypeContractParams.getAccountId());
            l_subAccountParams.setSubAccountId(l_eqtypeContractParams.getSubAccountId());
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            //MainAccountParams
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(l_eqtypeContractParams.getAccountId());
            l_mainAccountParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            l_mainAccountParams.setInstitutionId(l_subAccountParams.getInstitutionId());
            l_mainAccountParams.setBranchId(l_subAccountParams.getBranchId());
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            //InstitutionParams
            InstitutionParams l_insititutionParams =
                TestDBUtility.getInstitutionRow();
            l_insititutionParams.setInstitutionId(l_subAccountParams.getInstitutionId());
            l_insititutionParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(l_insititutionParams);

            //BranchParams
            BranchParams l_brachParams =
                TestDBUtility.getBranchRow();
            l_brachParams.setBranchId(l_subAccountParams.getBranchId());
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.insertWithDel(l_brachParams);
            
            TestDBUtility.deleteAll(ForcedSettleOrderInqParams.TYPE);

            //WEB3EquityBizLogicProvider.createCommission
            WEB3GentradeCommission l_commission = new WEB3GentradeCommission();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityBizLogicProvider",
                "createCommission",
                new Class[] {WEB3GentradeSubAccount.class, String.class, Date.class, String.class,String.class, double.class, OrderCategEnum.class},
                l_commission);

            //WEB3EquityOrderManager//TODO
            WEB3EquityRealizedProfitAndLossPrice l_lossPrice =
                new WEB3EquityRealizedProfitAndLossPrice();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityOrderManager",
                "calcEstimatedRealizedProfitAndLossAmount",                
                new Class[] {WEB3GentradeCommission.class,
                        double.class,
                        WEB3GentradeSubAccount.class,
                        WEB3EquityTradedProduct.class,
                        EqTypeSettleContractOrderEntry[].class,
                        double.class,
                        EqTypeOrderUnit.class,
                        double.class,
                        double.class,
                        boolean.class},
                l_lossPrice);
            
            ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();
            EqTypeNewOrderValidationResult l_orderValidationResult = 
                new EqTypeNewOrderValidationResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "validateSettleContractOrder",                
                    new Class[] {SubAccount.class,
                        EqTypeSettleContractOrderSpec.class,
                        WEB3EquityContract.class},
                        l_orderValidationResult);
            
            Date l_bizDate = new Date(2007,11,2);
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_bizDate);

            l_implMock.submitRepayTempOrder(
                l_eqtypeContractParams, 
                "1",
                l_contractForceSettleResult);
            log.info(STR_METHOD_NAME + "----------------->ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            l_exc.printStackTrace();
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }  
    
   
    public void testSubmitRepayTempOrderT_05()
    {
        final String STR_METHOD_NAME = "testSubmitRepayTempOrderT_05()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleTempOrderCreateUnitServiceImplMock l_implMock =
             new WEB3AdminEquityForcedSettleTempOrderCreateUnitServiceImplMock();
        try
        {
            TestDBUtility.deleteAll(BranchMarketRepayDealtCondRow.TYPE);
            BranchMarketRepayDealtCondParams l_BranchMarketRepayDealtCond =
                TestDBUtility.getBranchMarketRepayDealCondRow();
            l_BranchMarketRepayDealtCond.setBranchCode("381");
            l_BranchMarketRepayDealtCond.setInstitutionCode("0D");
            l_BranchMarketRepayDealtCond.setMarketCode("SP");
            l_BranchMarketRepayDealtCond.setRepaymentDiv("1");
            l_BranchMarketRepayDealtCond.setRepaymentNum(0);
            TestDBUtility.insertWithDel(l_BranchMarketRepayDealtCond);
            
            //EqtypeContractParams
            EqtypeContractParams l_eqtypeContractParams =
                TestDBUtility.getEqtypeContractRow();
            l_eqtypeContractParams.setQuantity(223d);
            l_eqtypeContractParams.setContractType(ContractTypeEnum.SHORT);
            l_eqtypeContractParams.setRepaymentType("1");
            Date l_closeDate = new Date(2007,9,2);
            l_eqtypeContractParams.setCloseDate(l_closeDate);
            TestDBUtility.insertWithDel(l_eqtypeContractParams);

            //EqtypeLockedContractDetailsParams
            EqtypeLockedContractDetailsParams l_detailsParams =
                this.getEqtypeLockedContractDetailsParams(
                    l_eqtypeContractParams, 123d);
            TestDBUtility.insertWithDel(l_detailsParams);

            //WEB3TPContractForcedSettleResult(Œš‹Ê‹­§ŒˆÏŒ‹‰Ê)
            WEB3TPContractForcedSettleResult l_contractForceSettleResult =
                new WEB3TPContractForcedSettleResult();
            l_contractForceSettleResult.additionalMarginAccruedDays = new Integer(4);
            l_contractForceSettleResult.additionalMarginDate = WEB3DateUtility.getDate("20040202","yyyyMMdd");
            l_contractForceSettleResult.marginMaintenanceRate = new Double(0.24);

            //TradedProduct
            TradedProductParams l_tradedProductParams =
                TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(l_eqtypeContractParams.getProductId());
            l_tradedProductParams.setMarketId(l_eqtypeContractParams.getMarketId());
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_tradedProductParams);

            //EquityTradedProduct
            EqtypeTradedProductParams l_eqtypeTradedProductParams =
                TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setTradedProductId(l_tradedProductParams.getTradedProductId());
            l_eqtypeTradedProductParams.setProductId(l_tradedProductParams.product_id);
            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);

            //EqtypeProductParams
            EqtypeProductParams l_eqtypeProductParams =
                TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(l_tradedProductParams.product_id);
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            //ProductParams
            ProductParams l_productPrarams =
                TestDBUtility.getProductRow();
            l_productPrarams.setProductId(l_tradedProductParams.product_id);
            l_productPrarams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.insertWithDel(l_productPrarams);

            //MarketParams
            MarketParams l_marketParams =
                TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(l_tradedProductParams.getMarketId());
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.insertWithDel(l_marketParams);
            
            //SubAccountParams
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(l_eqtypeContractParams.getAccountId());
            l_subAccountParams.setSubAccountId(l_eqtypeContractParams.getSubAccountId());
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            //MainAccountParams
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(l_eqtypeContractParams.getAccountId());
            l_mainAccountParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            l_mainAccountParams.setInstitutionId(l_subAccountParams.getInstitutionId());
            l_mainAccountParams.setBranchId(l_subAccountParams.getBranchId());
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            //InstitutionParams
            InstitutionParams l_insititutionParams =
                TestDBUtility.getInstitutionRow();
            l_insititutionParams.setInstitutionId(l_subAccountParams.getInstitutionId());
            l_insititutionParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(l_insititutionParams);

            //BranchParams
            BranchParams l_brachParams =
                TestDBUtility.getBranchRow();
            l_brachParams.setBranchId(l_subAccountParams.getBranchId());
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.insertWithDel(l_brachParams);

            //WEB3EquityBizLogicProvider.createCommission
            WEB3GentradeCommission l_commission = new WEB3GentradeCommission();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityBizLogicProvider",
                "createCommission",
                new Class[] {WEB3GentradeSubAccount.class, String.class, Date.class, String.class,String.class, double.class, OrderCategEnum.class},
                l_commission);

            //WEB3EquityOrderManager
            WEB3EquityRealizedProfitAndLossPrice l_lossPrice =
                new WEB3EquityRealizedProfitAndLossPrice();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityOrderManager",
                "calcEstimatedRealizedProfitAndLossAmount",                
                new Class[] {WEB3GentradeCommission.class,
                        double.class,
                        WEB3GentradeSubAccount.class,
                        WEB3EquityTradedProduct.class,
                        EqTypeSettleContractOrderEntry[].class,
                        double.class,
                        EqTypeOrderUnit.class,
                        double.class,
                        double.class,
                        boolean.class},
                l_lossPrice);
            
            ProcessingResult processingResult =
                ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80003);
            EqTypeNewOrderValidationResult l_orderValidationResult = 
                new EqTypeNewOrderValidationResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "validateSettleContractOrder",                
                    new Class[] {SubAccount.class,
                        EqTypeSettleContractOrderSpec.class,
                        WEB3EquityContract.class},
                        l_orderValidationResult);
            
            Date l_bizDate = new Date(2007,6,2);
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_bizDate);

            l_implMock.submitRepayTempOrder(
                l_eqtypeContractParams, 
                "1",
                l_contractForceSettleResult);
            log.info(STR_METHOD_NAME + "----------------->ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            l_exc.printStackTrace();
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }     

    public void testSubmitRepayTempOrderT_06()
    {
        final String STR_METHOD_NAME = "testSubmitRepayTempOrderT_06()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleTempOrderCreateUnitServiceImplMock l_implMock =
             new WEB3AdminEquityForcedSettleTempOrderCreateUnitServiceImplMock();
        try
        {
            TestDBUtility.deleteAll(BranchMarketRepayDealtCondRow.TYPE);
            BranchMarketRepayDealtCondParams l_BranchMarketRepayDealtCond =
                TestDBUtility.getBranchMarketRepayDealCondRow();
            l_BranchMarketRepayDealtCond.setBranchCode("381");
            l_BranchMarketRepayDealtCond.setInstitutionCode("0D");
            l_BranchMarketRepayDealtCond.setMarketCode("SP");
            l_BranchMarketRepayDealtCond.setRepaymentDiv("1");
            l_BranchMarketRepayDealtCond.setRepaymentNum(0);
            TestDBUtility.insertWithDel(l_BranchMarketRepayDealtCond);
            
            //EqtypeContractParams
            EqtypeContractParams l_eqtypeContractParams =
                TestDBUtility.getEqtypeContractRow();
            l_eqtypeContractParams.setQuantity(223d);
            l_eqtypeContractParams.setContractType(ContractTypeEnum.SHORT);
            l_eqtypeContractParams.setRepaymentType("1");
            Date l_closeDate = new Date(2007,9,2);
            l_eqtypeContractParams.setCloseDate(l_closeDate);
            TestDBUtility.insertWithDel(l_eqtypeContractParams);

            //EqtypeLockedContractDetailsParams
            EqtypeLockedContractDetailsParams l_detailsParams =
                this.getEqtypeLockedContractDetailsParams(
                    l_eqtypeContractParams, 123d);
            TestDBUtility.insertWithDel(l_detailsParams);

            //WEB3TPContractForcedSettleResult(Œš‹Ê‹­§ŒˆÏŒ‹‰Ê)
            WEB3TPContractForcedSettleResult l_contractForceSettleResult =
                new WEB3TPContractForcedSettleResult();
            l_contractForceSettleResult.additionalMarginAccruedDays = new Integer(4);
            l_contractForceSettleResult.additionalMarginDate = WEB3DateUtility.getDate("20040202","yyyyMMdd");
            l_contractForceSettleResult.marginMaintenanceRate = new Double(0.24);

            //TradedProduct
            TradedProductParams l_tradedProductParams =
                TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(l_eqtypeContractParams.getProductId());
            l_tradedProductParams.setMarketId(l_eqtypeContractParams.getMarketId());
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_tradedProductParams);

            //EquityTradedProduct
            EqtypeTradedProductParams l_eqtypeTradedProductParams =
                TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setTradedProductId(l_tradedProductParams.getTradedProductId());
            l_eqtypeTradedProductParams.setProductId(l_tradedProductParams.product_id);
            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);

            //EqtypeProductParams
            EqtypeProductParams l_eqtypeProductParams =
                TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(l_tradedProductParams.product_id);
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            //ProductParams
            ProductParams l_productPrarams =
                TestDBUtility.getProductRow();
            l_productPrarams.setProductId(l_tradedProductParams.product_id);
            l_productPrarams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.insertWithDel(l_productPrarams);

            //MarketParams
            MarketParams l_marketParams =
                TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(l_tradedProductParams.getMarketId());
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.insertWithDel(l_marketParams);
            
            //SubAccountParams
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(l_eqtypeContractParams.getAccountId());
            l_subAccountParams.setSubAccountId(l_eqtypeContractParams.getSubAccountId());
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            //MainAccountParams
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(l_eqtypeContractParams.getAccountId());
            l_mainAccountParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            l_mainAccountParams.setInstitutionId(l_subAccountParams.getInstitutionId());
            l_mainAccountParams.setBranchId(l_subAccountParams.getBranchId());
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            //InstitutionParams
            InstitutionParams l_insititutionParams =
                TestDBUtility.getInstitutionRow();
            l_insititutionParams.setInstitutionId(l_subAccountParams.getInstitutionId());
            l_insititutionParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(l_insititutionParams);

            //BranchParams
            BranchParams l_brachParams =
                TestDBUtility.getBranchRow();
            l_brachParams.setBranchId(l_subAccountParams.getBranchId());
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.insertWithDel(l_brachParams);

            //WEB3EquityBizLogicProvider.createCommission
            WEB3GentradeCommission l_commission = new WEB3GentradeCommission();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityBizLogicProvider",
                "createCommission",
                new Class[] {WEB3GentradeSubAccount.class, String.class, Date.class, String.class,String.class, double.class, OrderCategEnum.class},
                l_commission);

            //WEB3EquityOrderManager
            WEB3EquityRealizedProfitAndLossPrice l_lossPrice =
                new WEB3EquityRealizedProfitAndLossPrice();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityOrderManager",
                "calcEstimatedRealizedProfitAndLossAmount",                
                new Class[] {WEB3GentradeCommission.class,
                        double.class,
                        WEB3GentradeSubAccount.class,
                        WEB3EquityTradedProduct.class,
                        EqTypeSettleContractOrderEntry[].class,
                        double.class,
                        EqTypeOrderUnit.class,
                        double.class,
                        double.class,
                        boolean.class},
                l_lossPrice);
            //EqTypeNewOrderValidationResult
            ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();
            EqTypeNewOrderValidationResult l_orderValidationResult = 
                new EqTypeNewOrderValidationResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "validateSettleContractOrder",                
                    new Class[] {SubAccount.class,
                        EqTypeSettleContractOrderSpec.class,
                        WEB3EquityContract.class},
                        l_orderValidationResult);
            //OrderSubmissionResult
            EqTypeOrderSubmissionResult l_result =
                new EqTypeOrderSubmissionResult(processingResult);           
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "submitSettleContractOrder",                
                    new Class[] {SubAccount.class,
                        EqTypeSettleContractOrderSpec.class,
                        long.class,
                        String.class,
                        boolean.class},
                        l_result);
            
            Date l_bizDate = new Date(2007,6,2);
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_bizDate);

            //
            TestDBUtility.deleteAll(TpCalcResultExecNotifyRow.TYPE);
            
            l_implMock.submitRepayTempOrder(
                l_eqtypeContractParams, 
                "123",
                l_contractForceSettleResult);
            
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            String l_strWhere = " account_id = ? ";
            Object[] l_obj = {l_subAccountParams.getAccountId() + ""}; 
            List l_list =
                l_processor.doFindAllQuery(
                TpCalcResultExecNotifyRow.TYPE,
                l_strWhere,
                l_obj);
            assertEquals(1, l_list.size());
            log.info(STR_METHOD_NAME + "----------------->ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            l_exc.printStackTrace();
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    } 
    
    public void testSubmitRepayTempOrderT_07()
    {
        final String STR_METHOD_NAME = "testSubmitRepayTempOrderT_07()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleTempOrderCreateUnitServiceImplMock l_implMock =
             new WEB3AdminEquityForcedSettleTempOrderCreateUnitServiceImplMock();
        try
        {
            TestDBUtility.deleteAll(BranchMarketRepayDealtCondRow.TYPE);
            BranchMarketRepayDealtCondParams l_BranchMarketRepayDealtCond =
                TestDBUtility.getBranchMarketRepayDealCondRow();
            l_BranchMarketRepayDealtCond.setBranchCode("381");
            l_BranchMarketRepayDealtCond.setInstitutionCode("0D");
            l_BranchMarketRepayDealtCond.setMarketCode("SP");
            l_BranchMarketRepayDealtCond.setRepaymentDiv("1");
            l_BranchMarketRepayDealtCond.setRepaymentNum(0);
            TestDBUtility.insertWithDel(l_BranchMarketRepayDealtCond);
            
            //EqtypeContractParams
            EqtypeContractParams l_eqtypeContractParams =
                TestDBUtility.getEqtypeContractRow();
            l_eqtypeContractParams.setQuantity(223d);
            l_eqtypeContractParams.setContractType(ContractTypeEnum.SHORT);
            l_eqtypeContractParams.setRepaymentType("1");
            Date l_closeDate = new Date(2007,9,2);
            l_eqtypeContractParams.setCloseDate(l_closeDate);
            TestDBUtility.insertWithDel(l_eqtypeContractParams);

            //EqtypeLockedContractDetailsParams
            EqtypeLockedContractDetailsParams l_detailsParams =
                this.getEqtypeLockedContractDetailsParams(
                    l_eqtypeContractParams, 123d);
            TestDBUtility.insertWithDel(l_detailsParams);

            //WEB3TPContractForcedSettleResult(Œš‹Ê‹­§ŒˆÏŒ‹‰Ê)
            WEB3TPContractForcedSettleResult l_contractForceSettleResult =
                new WEB3TPContractForcedSettleResult();
            l_contractForceSettleResult.additionalMarginAccruedDays = new Integer(4);
            l_contractForceSettleResult.additionalMarginDate = WEB3DateUtility.getDate("20040202","yyyyMMdd");
            l_contractForceSettleResult.marginMaintenanceRate = new Double(0.24);

            //TradedProduct
            TradedProductParams l_tradedProductParams =
                TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(l_eqtypeContractParams.getProductId());
            l_tradedProductParams.setMarketId(l_eqtypeContractParams.getMarketId());
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_tradedProductParams);

            //EquityTradedProduct
            EqtypeTradedProductParams l_eqtypeTradedProductParams =
                TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setTradedProductId(l_tradedProductParams.getTradedProductId());
            l_eqtypeTradedProductParams.setProductId(l_tradedProductParams.product_id);
            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);

            //EqtypeProductParams
            EqtypeProductParams l_eqtypeProductParams =
                TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(l_tradedProductParams.product_id);
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            //ProductParams
            ProductParams l_productPrarams =
                TestDBUtility.getProductRow();
            l_productPrarams.setProductId(l_tradedProductParams.product_id);
            l_productPrarams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.insertWithDel(l_productPrarams);

            //MarketParams
            MarketParams l_marketParams =
                TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(l_tradedProductParams.getMarketId());
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.insertWithDel(l_marketParams);
            
            //SubAccountParams
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(l_eqtypeContractParams.getAccountId());
            l_subAccountParams.setSubAccountId(l_eqtypeContractParams.getSubAccountId());
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            //MainAccountParams
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(l_eqtypeContractParams.getAccountId());
            l_mainAccountParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            l_mainAccountParams.setInstitutionId(l_subAccountParams.getInstitutionId());
            l_mainAccountParams.setBranchId(l_subAccountParams.getBranchId());
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            //InstitutionParams
            InstitutionParams l_insititutionParams =
                TestDBUtility.getInstitutionRow();
            l_insititutionParams.setInstitutionId(l_subAccountParams.getInstitutionId());
            l_insititutionParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(l_insititutionParams);

            //BranchParams
            BranchParams l_brachParams =
                TestDBUtility.getBranchRow();
            l_brachParams.setBranchId(l_subAccountParams.getBranchId());
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.insertWithDel(l_brachParams);

            //WEB3EquityBizLogicProvider.createCommission
            WEB3GentradeCommission l_commission = new WEB3GentradeCommission();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityBizLogicProvider",
                "createCommission",
                new Class[] {WEB3GentradeSubAccount.class, String.class, Date.class, String.class,String.class, double.class, OrderCategEnum.class},
                l_commission);

            //WEB3EquityOrderManager
            WEB3EquityRealizedProfitAndLossPrice l_lossPrice =
                new WEB3EquityRealizedProfitAndLossPrice();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityOrderManager",
                "calcEstimatedRealizedProfitAndLossAmount",                
                new Class[] {WEB3GentradeCommission.class,
                        double.class,
                        WEB3GentradeSubAccount.class,
                        WEB3EquityTradedProduct.class,
                        EqTypeSettleContractOrderEntry[].class,
                        double.class,
                        EqTypeOrderUnit.class,
                        double.class,
                        double.class,
                        boolean.class},
                l_lossPrice);
            //EqTypeNewOrderValidationResult
            ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();
            EqTypeNewOrderValidationResult l_orderValidationResult = 
                new EqTypeNewOrderValidationResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "validateSettleContractOrder",                
                    new Class[] {SubAccount.class,
                        EqTypeSettleContractOrderSpec.class,
                        WEB3EquityContract.class},
                        l_orderValidationResult);
            //OrderSubmissionResult
            EqTypeOrderSubmissionResult l_result =
                new EqTypeOrderSubmissionResult(processingResult);           
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "submitSettleContractOrder",                
                    new Class[] {SubAccount.class,
                        EqTypeSettleContractOrderSpec.class,
                        long.class,
                        String.class,
                        boolean.class},
                        l_result);
            
            Date l_bizDate = new Date(2007,6,2);
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_bizDate);

            //
            TestDBUtility.deleteAll(TpCalcResultExecNotifyRow.TYPE);
            
            l_implMock.submitRepayTempOrder(
                l_eqtypeContractParams, 
                "123",
                l_contractForceSettleResult);
            
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            String l_strWhere = " account_id = ? ";
            Object[] l_obj = {l_subAccountParams.getAccountId() + ""}; 
            List l_list =
                l_processor.doFindAllQuery(
                TpCalcResultExecNotifyRow.TYPE,
                l_strWhere,
                l_obj);
            assertEquals(1, l_list.size());
            log.info(STR_METHOD_NAME + "----------------->ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            l_exc.printStackTrace();
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    
    public void testSubmitRepayTempOrderT_08()
    {
        final String STR_METHOD_NAME = "testSubmitRepayTempOrderT_08()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityForcedSettleTempOrderCreateUnitServiceImplMock l_implMock =
             new WEB3AdminEquityForcedSettleTempOrderCreateUnitServiceImplMock();
        try
        {
            TestDBUtility.deleteAll(BranchMarketRepayDealtCondRow.TYPE);
            BranchMarketRepayDealtCondParams l_BranchMarketRepayDealtCond =
                TestDBUtility.getBranchMarketRepayDealCondRow();
            l_BranchMarketRepayDealtCond.setBranchCode("381");
            l_BranchMarketRepayDealtCond.setInstitutionCode("0D");
            l_BranchMarketRepayDealtCond.setMarketCode("SP");
            l_BranchMarketRepayDealtCond.setRepaymentDiv("1");
            l_BranchMarketRepayDealtCond.setRepaymentNum(0);
            TestDBUtility.insertWithDel(l_BranchMarketRepayDealtCond);
            
            //EqtypeContractParams
            EqtypeContractParams l_eqtypeContractParams =
                TestDBUtility.getEqtypeContractRow();
            l_eqtypeContractParams.setQuantity(223d);
            l_eqtypeContractParams.setContractType(ContractTypeEnum.SHORT);
            l_eqtypeContractParams.setRepaymentType("1");
            Date l_closeDate = new Date(2007,9,2);
            l_eqtypeContractParams.setCloseDate(l_closeDate);
            TestDBUtility.insertWithDel(l_eqtypeContractParams);

            //EqtypeLockedContractDetailsParams
            EqtypeLockedContractDetailsParams l_detailsParams =
                this.getEqtypeLockedContractDetailsParams(
                    l_eqtypeContractParams, 123d);
            TestDBUtility.insertWithDel(l_detailsParams);

            //WEB3TPContractForcedSettleResult(Œš‹Ê‹­§ŒˆÏŒ‹‰Ê)
            WEB3TPContractForcedSettleResult l_contractForceSettleResult =
                new WEB3TPContractForcedSettleResult();
            l_contractForceSettleResult.additionalMarginAccruedDays = new Integer(4);
            l_contractForceSettleResult.additionalMarginDate = WEB3DateUtility.getDate("20040202","yyyyMMdd");
            l_contractForceSettleResult.marginMaintenanceRate = new Double(0.24);

            //TradedProduct
            TradedProductParams l_tradedProductParams =
                TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(l_eqtypeContractParams.getProductId());
            l_tradedProductParams.setMarketId(l_eqtypeContractParams.getMarketId());
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_tradedProductParams);

            //EquityTradedProduct
            EqtypeTradedProductParams l_eqtypeTradedProductParams =
                TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setTradedProductId(l_tradedProductParams.getTradedProductId());
            l_eqtypeTradedProductParams.setProductId(l_tradedProductParams.product_id);
            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);

            //EqtypeProductParams
            EqtypeProductParams l_eqtypeProductParams =
                TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(l_tradedProductParams.product_id);
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            //ProductParams
            ProductParams l_productPrarams =
                TestDBUtility.getProductRow();
            l_productPrarams.setProductId(l_tradedProductParams.product_id);
            l_productPrarams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.insertWithDel(l_productPrarams);

            //MarketParams
            MarketParams l_marketParams =
                TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(l_tradedProductParams.getMarketId());
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.insertWithDel(l_marketParams);
            
            //SubAccountParams
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(l_eqtypeContractParams.getAccountId());
            l_subAccountParams.setSubAccountId(l_eqtypeContractParams.getSubAccountId());
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            //MainAccountParams
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(l_eqtypeContractParams.getAccountId());
            l_mainAccountParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            l_mainAccountParams.setInstitutionId(l_subAccountParams.getInstitutionId());
            l_mainAccountParams.setBranchId(l_subAccountParams.getBranchId());
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            //InstitutionParams
            InstitutionParams l_insititutionParams =
                TestDBUtility.getInstitutionRow();
            l_insititutionParams.setInstitutionId(l_subAccountParams.getInstitutionId());
            l_insititutionParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(l_insititutionParams);

            //BranchParams
            BranchParams l_brachParams =
                TestDBUtility.getBranchRow();
            l_brachParams.setBranchId(l_subAccountParams.getBranchId());
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.insertWithDel(l_brachParams);

            //WEB3EquityBizLogicProvider.createCommission
            WEB3GentradeCommission l_commission = new WEB3GentradeCommission();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityBizLogicProvider",
                "createCommission",
                new Class[] {WEB3GentradeSubAccount.class, String.class, Date.class, String.class,String.class, double.class, OrderCategEnum.class},
                l_commission);

            //WEB3EquityOrderManager
            WEB3EquityRealizedProfitAndLossPrice l_lossPrice =
                new WEB3EquityRealizedProfitAndLossPrice();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityOrderManager",
                "calcEstimatedRealizedProfitAndLossAmount",                
                new Class[] {WEB3GentradeCommission.class,
                        double.class,
                        WEB3GentradeSubAccount.class,
                        WEB3EquityTradedProduct.class,
                        EqTypeSettleContractOrderEntry[].class,
                        double.class,
                        EqTypeOrderUnit.class,
                        double.class,
                        double.class,
                        boolean.class},
                l_lossPrice);
            //EqTypeNewOrderValidationResult
            ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();
            EqTypeNewOrderValidationResult l_orderValidationResult = 
                new EqTypeNewOrderValidationResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "validateSettleContractOrder",                
                    new Class[] {SubAccount.class,
                        EqTypeSettleContractOrderSpec.class,
                        WEB3EquityContract.class},
                        l_orderValidationResult);
            //OrderSubmissionResult
            ProcessingResult processingResult2 =
                ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80003);
            EqTypeOrderSubmissionResult l_result =
                new EqTypeOrderSubmissionResult(processingResult2);           
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "submitSettleContractOrder",                
                    new Class[] {SubAccount.class,
                        EqTypeSettleContractOrderSpec.class,
                        long.class,
                        String.class,
                        boolean.class},
                        l_result);
            
            Date l_bizDate = new Date(2007,6,2);
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_bizDate);

            l_implMock.submitRepayTempOrder(
                l_eqtypeContractParams, 
                "123",
                l_contractForceSettleResult);
            fail();
        }
        catch(WEB3BusinessLayerException l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80003, l_exc.getErrorInfo());
            log.info(STR_METHOD_NAME + "------------------>ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            l_exc.printStackTrace();
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }  
    
    
    public class WEB3AdminEquityForcedSettleTempOrderCreateUnitServiceImplMock extends WEB3AdminEquityForcedSettleTempOrderCreateUnitServiceImpl
    {

        public WEB3AdminEquityForcedSettleTempOrderCreateUnitServiceImplMock()
        {
            
        }
        public void execTempOrderCreated(EqtypeContractRow l_eqtypeContractRow, String l_strForcedSettleReasonType, WEB3TPContractForcedSettleResult l_contractForceSettleResult, boolean l_blnIsSuccOrderHandling) throws WEB3BaseException
        {
        
        }

        protected void insertForceSettleErrorOrder(
            EqtypeContractRow l_eqtypeContractRow,
            String l_strForcedSettleResonDiv,
            WEB3TPContractForcedSettleResult l_contractForceSettleResult,
            double l_dblOrderNumber,
            Double l_limitPrice,
            String l_strOrderErrorReasonCode) throws WEB3BaseException
        {
            System.out.println("insertForceSettleErrorOrder is right!!");
        }
       
   }

    public EqtypeLockedContractDetailsParams getEqtypeLockedContractDetailsParams(
        EqtypeContractParams l_contractParams, double l_dblLockedQuantity)
    {
        EqtypeLockedContractDetailsParams l_params =
            new EqtypeLockedContractDetailsParams();
        l_params.setContractId(l_contractParams.getContractId());
        l_params.setAccountId(l_contractParams.getAccountId());
        l_params.setSubAccountId(l_contractParams.getSubAccountId());
        l_params.setLockedQuantity(l_dblLockedQuantity);
        l_params.setCreatedTimestamp(l_contractParams.getCreatedTimestamp());
        l_params.setLastUpdatedTimestamp(l_contractParams.getLastUpdatedTimestamp());
        return l_params;
    }
    
    
    
    public static EquityTickValuesMstParams getEquityTickValuesMstRow()
    {
        EquityTickValuesMstParams l_equityTickValuesMstParams =
            new EquityTickValuesMstParams();
        
        l_equityTickValuesMstParams.setMarketCode("1");
        l_equityTickValuesMstParams.setLowPrice(20);
        l_equityTickValuesMstParams.setTickValue(20);
        l_equityTickValuesMstParams.setCapPrice(100);
        l_equityTickValuesMstParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        l_equityTickValuesMstParams.setCreatedTimestamp(Calendar.getInstance().getTime());

        return l_equityTickValuesMstParams;
    }
    
    public static EquityLimitPriceRangeMstParams getEquityLimitPriceRangeMstRow()
    {
        EquityLimitPriceRangeMstParams l_equityLimitPriceRangeMstParams =
            new EquityLimitPriceRangeMstParams();
        
        l_equityLimitPriceRangeMstParams.setMarketCode("1");
        l_equityLimitPriceRangeMstParams.setLowPrice(20);
        l_equityLimitPriceRangeMstParams.setCapPrice(100);
        l_equityLimitPriceRangeMstParams.setRange(new Double(10));
        l_equityLimitPriceRangeMstParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        l_equityLimitPriceRangeMstParams.setCreatedTimestamp(Calendar.getInstance().getTime());

        return l_equityLimitPriceRangeMstParams;
    }
    
    public static HostEqtypeCloseOrderNotifyParams getHostEqtypeCloseOrderNotifyRow()
    {
        HostEqtypeCloseOrderNotifyParams l_hostEqtypeCloseOrderNotifyParams =
            new HostEqtypeCloseOrderNotifyParams();
        
        l_hostEqtypeCloseOrderNotifyParams.setAccountCode("2512246");
        l_hostEqtypeCloseOrderNotifyParams.setBranchCode("624");
        l_hostEqtypeCloseOrderNotifyParams.setCloseNotifyType("1");
        l_hostEqtypeCloseOrderNotifyParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_hostEqtypeCloseOrderNotifyParams.setExecutedQuantity(2);
        l_hostEqtypeCloseOrderNotifyParams.setInstitutionCode("0D");
        l_hostEqtypeCloseOrderNotifyParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        l_hostEqtypeCloseOrderNotifyParams.setNoticeType("0");
        l_hostEqtypeCloseOrderNotifyParams.setOrderRequestNumber("1");
        l_hostEqtypeCloseOrderNotifyParams.setReasonCode("1");
        l_hostEqtypeCloseOrderNotifyParams.setNoticeNumber(1);
        l_hostEqtypeCloseOrderNotifyParams.setRequestCode("1");
        l_hostEqtypeCloseOrderNotifyParams.setStatus("1");
        l_hostEqtypeCloseOrderNotifyParams.setTraderCode("1");
        l_hostEqtypeCloseOrderNotifyParams.setVirtualServerNumberMarket("TOKYO");
        
        return l_hostEqtypeCloseOrderNotifyParams;
    }
    
    public static RsvEqClosingContractSpecParams getRsvEqClosingContractSpecRow()
    {
        RsvEqClosingContractSpecParams l_rsvEqClosingContractSpecParams =
            new RsvEqClosingContractSpecParams();
        
        l_rsvEqClosingContractSpecParams.setAccountId(333812512203L);
        l_rsvEqClosingContractSpecParams.setClosingSerialNo(1);
        l_rsvEqClosingContractSpecParams.setContractId(1001);
        l_rsvEqClosingContractSpecParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_rsvEqClosingContractSpecParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        l_rsvEqClosingContractSpecParams.setOrderId(10001);
        l_rsvEqClosingContractSpecParams.setQuantity(100);
        l_rsvEqClosingContractSpecParams.setSubAccountId(333812512203L);
        
        return l_rsvEqClosingContractSpecParams;
    }
    
    public class forTestWEB3AdminEquityForcedSettleTempOrderCreateUnitServiceImpl extends
        WEB3AdminEquityForcedSettleTempOrderCreateUnitServiceImpl
    {
        protected void execCloseNotice(EqtypeOrderUnitRow l_eqtypeOrderUnitRow) throws WEB3BaseException
        {
            
        }
        protected void submitRepayTempOrder(
                EqtypeContractRow l_eqtypeContractRow,
                String l_strForcedSettleResonDiv,
                WEB3TPContractForcedSettleResult l_contractForceSettleResult) throws WEB3BaseException
        {
        
        }
        protected void expireSettleOrder(EqtypeOrderUnitRow l_eqtypeOrderUnitRow) throws WEB3BaseException
        {
            
        }
        protected void expireRsvSettleOrder(RsvEqOrderUnitRow l_rsvEqOrderUnitRow) throws WEB3BaseException
        {
            
        }
        protected void insertForceSettleErrorOrder(
                EqtypeContractRow l_eqtypeContractRow,
                String l_strForcedSettleResonDiv,
                WEB3TPContractForcedSettleResult l_contractForceSettleResult,
                double l_dblOrderNumber,
                Double l_limitPrice,
                String l_strOrderErrorReasonCode) throws WEB3BaseException
                {
            
                }
        public void execTempOrderCreated(EqtypeContractRow l_eqtypeContractRow, String l_strForcedSettleReasonType, WEB3TPContractForcedSettleResult l_contractForceSettleResult, boolean l_blnIsSuccOrderHandling) throws WEB3BaseException
        {
            // TODO Auto-generated method stub
            
        }
    }
    public class forTestWEB3AdminEquityForcedSettleTempOrderCreateUnitServiceImpl1 extends
    WEB3AdminEquityForcedSettleTempOrderCreateUnitServiceImpl
{
        protected void execCloseNotice(EqtypeOrderUnitRow l_eqtypeOrderUnitRow) throws WEB3BaseException
        {
            
        }

        public void execTempOrderCreated(EqtypeContractRow l_eqtypeContractRow, String l_strForcedSettleReasonType, WEB3TPContractForcedSettleResult l_contractForceSettleResult, boolean l_blnIsSuccOrderHandling) throws WEB3BaseException
        {
            // TODO Auto-generated method stub
            
        }
}
}

@
