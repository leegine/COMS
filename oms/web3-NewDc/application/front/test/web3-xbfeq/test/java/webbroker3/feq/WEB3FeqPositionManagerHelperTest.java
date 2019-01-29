head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.55.58;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FeqPositionManagerHelperTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研ビジネス・イノベーション
File Name        : 外国株式ポジションヘルパー(WEB3FeqPositionManagerHelper.java)
Author Name      : Daiwa Institute of Research Business Innovation
Revision History : 2007/11/07 何文敏(中訊) 仕様変更モデルNo.358
Revision History : 2010/09/12 趙天月(中訊) 仕様変更モデルNo.545
*/
package webbroker3.feq;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketPreferencesParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqFinTransactionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderActionRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderExecutionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderExecutionRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqTradedProductParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3InstitutionPreferencesNameDef;
import webbroker3.common.define.WEB3MarginAccountOpenDivDef;
import webbroker3.common.define.WEB3MarketPreferencesNameDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.data.EquityCommAccountCondMstParams;
import webbroker3.gentrade.data.EquityCommCondMstParams;
import webbroker3.gentrade.data.EquityCommCondMstRow;
import webbroker3.gentrade.data.EquityCommCondParams;
import webbroker3.gentrade.data.EquityCommCondRow;
import webbroker3.gentrade.data.GenCurrencyParams;
import webbroker3.gentrade.data.InstitutionPreferencesParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3FeqPositionManagerHelperTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqPositionManagerHelperTest.class);
    WEB3FeqPositionManagerHelper l_help =
        new WEB3FeqPositionManagerHelper(ProductTypeEnum.FOREIGN_EQUITY);

    public WEB3FeqPositionManagerHelperTest(String arg0)
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

//    public void testUpdateTransaction()
//    {
//        final String STR_METHOD_NAME = "testUpdateTransaction()";
//        log.entering(STR_METHOD_NAME);
//        
//        try
//        {
//            WEB3FeqPositionManagerHelperForTest l_forTest = new WEB3FeqPositionManagerHelperForTest(ProductTypeEnum.FOREIGN_EQUITY);
//            MOCK_MANAGER.setIsMockUsed(true);
//            TestDBUtility.deleteAll(FeqFinTransactionRow.TYPE);
//            FeqFinTransactionParams l_feqFinTransactionParams = new FeqFinTransactionParams();
//            l_feqFinTransactionParams.setFinTransactionId(2L);
//            l_feqFinTransactionParams.setAccountId(1L);
//            l_feqFinTransactionParams.setOrderUnitId(1L);
//            l_feqFinTransactionParams.setSubAccountId(1L);
//            l_feqFinTransactionParams.setProductId(1L);
//            l_feqFinTransactionParams.setFinTransactionType(FinTransactionType.EQTYPE_FEQ_SELL);
//            l_feqFinTransactionParams.setFinTransactionCateg(FinTransactionCateg.EQTYPE_ASSET);
//            l_feqFinTransactionParams.setTaxType(TaxTypeEnum.UNDEFINED);
//            l_feqFinTransactionParams.setBizDate("20070506");
//            l_feqFinTransactionParams.setDeliveryDate(WEB3DateUtility.getDate("20070506", "yyyyMMdd"));
//            l_feqFinTransactionParams.setCurrencyCode("12");
//            l_feqFinTransactionParams.setNetAmount(2D);
//            l_feqFinTransactionParams.setNetAmountFc(1D);
//            l_feqFinTransactionParams.setFxRate(0.5D);
//            l_feqFinTransactionParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
//            l_feqFinTransactionParams.setQuantity(1D);
//            l_feqFinTransactionParams.setCommissionFee(0D);
//            l_feqFinTransactionParams.setCommissionFeeTax(0D);
//            l_feqFinTransactionParams.setCommissionFeeTaxFc(0D);
//            l_feqFinTransactionParams.setCommissionFeeFc(0D);
//            l_feqFinTransactionParams.setBalanceAmountFc(0D);
//            l_feqFinTransactionParams.setForeignCommissionFee(0D);
//            l_feqFinTransactionParams.setForeignTax(0D);
//            l_feqFinTransactionParams.setForeignFeeExt1(0D);
//            l_feqFinTransactionParams.setRegNo("2");
//            l_feqFinTransactionParams.setForeignFeeExt2(0D);
//            l_feqFinTransactionParams.setCapitalGain(0D);
//            l_feqFinTransactionParams.setCapitalGainFc(0D);
//            l_feqFinTransactionParams.setCapitalGainTax(0D);
//            l_feqFinTransactionParams.setCapitalGainTaxFc(0D);
//            l_feqFinTransactionParams.setDeleteFlag(BooleanEnum.FALSE);
//            l_feqFinTransactionParams.setFinTransactionTimestamp(WEB3DateUtility.getDate("20071106", "yyyyMMdd"));
//            l_feqFinTransactionParams.setCreatedTimestamp(WEB3DateUtility.getDate("20071106", "yyyyMMdd"));
//            l_feqFinTransactionParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071106", "yyyyMMdd"));
//            TestDBUtility.insertWithDel(l_feqFinTransactionParams);
//            
//            TestDBUtility.deleteAll(SubAccountRow.TYPE);
//            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
//            l_subAccountParams.setAccountId(1L);
//            l_subAccountParams.setSubAccountId(1L);
//            TestDBUtility.insertWithDel(l_subAccountParams);
//            
//            TestDBUtility.deleteAll(ProductRow.TYPE);
//            ProductParams l_productParams = TestDBUtility.getProductRow();
//            l_productParams.setProductId(1L);
//            TestDBUtility.insertWithDel(l_productParams);
//            
//            TestDBUtility.deleteAll(InstitutionRow.TYPE);
//            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//            l_institutionParams.setInstitutionCode("0D");
//            TestDBUtility.insertWithDel(l_institutionParams);
//            
//            TestDBUtility.deleteAll(FeqProductRow.TYPE);
//            FeqProductParams l_feqProductParams = TestDBUtility.getFeqProductRow();
//            l_feqProductParams.setProductId(1L);
//            TestDBUtility.insertWithDel(l_feqProductParams);
//            
//            TestDBUtility.deleteAll(FeqOrderUnitRow.TYPE);
//            FeqOrderUnitParams l_feqOrderUnitRow = TestDBUtility.getFeqOrderUnitRow();
//            l_feqOrderUnitRow.setOrderUnitId(1L);
//            TestDBUtility.insertWithDel(l_feqOrderUnitRow);
//            
//            WEB3FeqAmountCalcResult[] l_feqAmountCalcResultDetails = new WEB3FeqAmountCalcResult[1];
//            WEB3FeqAmountCalcResult l_result = new WEB3FeqAmountCalcResult();
//            l_feqAmountCalcResultDetails[0] = l_result;
//            l_feqAmountCalcResultDetails[0].setBalanceAmount(1D);
//            l_feqAmountCalcResultDetails[0].setCommissionBranchNumber("1");
//            l_feqAmountCalcResultDetails[0].setCommissionFeeFc(1D);
//            l_feqAmountCalcResultDetails[0].setCommissionFeeTaxFc(1D);
//            l_feqAmountCalcResultDetails[0].setNetAmount(2D);
//            l_feqAmountCalcResultDetails[0].setCommissionNumber("1");
//            l_feqAmountCalcResultDetails[0].setCommissionBranchNumber("1");
//            WEB3FeqAmountCalcResult feqAmountCalcResultTotal = new WEB3FeqAmountCalcResult();
//            feqAmountCalcResultTotal.setBalanceAmountFc(1D);
//            feqAmountCalcResultTotal.setCommissionNumber("1");
//            feqAmountCalcResultTotal.setCommissionBranchNumber("1");
//            WEB3FeqAmountCalcResultFactor l_factor = new WEB3FeqAmountCalcResultFactor();
//            l_factor.setFeqAmountCalcResultDetails(l_feqAmountCalcResultDetails);
//            l_factor.setFeqAmountCalcResultTotal(feqAmountCalcResultTotal);
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.feq.WEB3FeqBizLogicProvider",
//                    "calcFeqAmountFactor",
//                    new Class[]{FeqFinTransactionParams[].class},
//                    l_factor);
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.feq.WEB3FeqBizLogicProvider", 
//                    "calcCapitalProfitLoss",
//                    new Class[]
//                    { double.class, double.class, long.class, WEB3GentradeSubAccount.class, TaxTypeEnum.class},
//                    new Double(12));
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.feq.WEB3FeqBizLogicProvider", "calcForeignCCYAmount", new Class[]
//                    { double.class, double.class, long.class, boolean.class, boolean.class},
//                    new Double(12));
//            
//            l_forTest.updateTransaction(1, true);
//            
//            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
//                    "webbroker3.feq.WEB3FeqBizLogicProvider", "calcForeignCCYAmount", new Class[]
//                    { double.class, double.class, long.class, boolean.class, boolean.class});
//            assertEquals(0.5, ((Double)l_paramsValue1.getFirstCalled()[1]).doubleValue(), 0);
//        }
//        catch (Exception l_ex)
//        {
//            log.debug(l_ex.getMessage(), l_ex);
//            fail();   
//        }
//        
//        log.exiting(STR_METHOD_NAME);
//    }
//    
//    public void testUpdateTransaction_case0001()
//    {
//        final String STR_METHOD_NAME = "testUpdateTransaction_case0001()";
//        log.entering(STR_METHOD_NAME);
//        
//        try
//        {
//            WEB3FeqPositionManagerHelperForTest l_forTest = new WEB3FeqPositionManagerHelperForTest(ProductTypeEnum.FOREIGN_EQUITY);
//            MOCK_MANAGER.setIsMockUsed(true);
//            TestDBUtility.deleteAll(FeqFinTransactionRow.TYPE);
//            FeqFinTransactionParams l_feqFinTransactionParams = new FeqFinTransactionParams();
//            l_feqFinTransactionParams.setFinTransactionId(2L);
//            l_feqFinTransactionParams.setAccountId(1L);
//            l_feqFinTransactionParams.setOrderUnitId(1L);
//            l_feqFinTransactionParams.setSubAccountId(1L);
//            l_feqFinTransactionParams.setProductId(1L);
//            l_feqFinTransactionParams.setFinTransactionType(FinTransactionType.EQTYPE_FEQ_SELL);
//            l_feqFinTransactionParams.setFinTransactionCateg(FinTransactionCateg.EQTYPE_ASSET);
//            l_feqFinTransactionParams.setTaxType(TaxTypeEnum.UNDEFINED);
//            l_feqFinTransactionParams.setBizDate("20070506");
//            l_feqFinTransactionParams.setDeliveryDate(WEB3DateUtility.getDate("20070506", "yyyyMMdd"));
//            l_feqFinTransactionParams.setCurrencyCode("12");
//            l_feqFinTransactionParams.setNetAmount(2D);
//            l_feqFinTransactionParams.setNetAmountFc(1D);
//            l_feqFinTransactionParams.setFxRate(0.5D);
//            l_feqFinTransactionParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
//            l_feqFinTransactionParams.setQuantity(1D);
//            l_feqFinTransactionParams.setCommissionFee(0D);
//            l_feqFinTransactionParams.setCommissionFeeTax(0D);
//            l_feqFinTransactionParams.setCommissionFeeTaxFc(0D);
//            l_feqFinTransactionParams.setCommissionFeeFc(0D);
//            l_feqFinTransactionParams.setBalanceAmountFc(0D);
//            l_feqFinTransactionParams.setForeignCommissionFee(0D);
//            l_feqFinTransactionParams.setForeignTax(0D);
//            l_feqFinTransactionParams.setForeignFeeExt1(0D);
//            l_feqFinTransactionParams.setRegNo("2");
//            l_feqFinTransactionParams.setForeignFeeExt2(0D);
//            l_feqFinTransactionParams.setCapitalGain(0D);
//            l_feqFinTransactionParams.setCapitalGainFc(0D);
//            l_feqFinTransactionParams.setCapitalGainTax(0D);
//            l_feqFinTransactionParams.setCapitalGainTaxFc(0D);
//            l_feqFinTransactionParams.setDeleteFlag(BooleanEnum.FALSE);
//            l_feqFinTransactionParams.setFinTransactionTimestamp(WEB3DateUtility.getDate("20071106", "yyyyMMdd"));
//            l_feqFinTransactionParams.setCreatedTimestamp(WEB3DateUtility.getDate("20071106", "yyyyMMdd"));
//            l_feqFinTransactionParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071106", "yyyyMMdd"));
//            TestDBUtility.insertWithDel(l_feqFinTransactionParams);
//            
//            FeqFinTransactionParams l_feqFinTransactionParams1 = new FeqFinTransactionParams();
//            l_feqFinTransactionParams1.setFinTransactionId(1L);
//            l_feqFinTransactionParams1.setAccountId(1L);
//            l_feqFinTransactionParams1.setOrderUnitId(2L);
//            l_feqFinTransactionParams1.setSubAccountId(1L);
//            l_feqFinTransactionParams1.setProductId(1L);
//            l_feqFinTransactionParams1.setFinTransactionType(FinTransactionType.EQTYPE_FEQ_SELL);
//            l_feqFinTransactionParams1.setFinTransactionCateg(FinTransactionCateg.EQTYPE_ASSET);
//            l_feqFinTransactionParams1.setTaxType(TaxTypeEnum.UNDEFINED);
//            l_feqFinTransactionParams1.setBizDate("20070506");
//            l_feqFinTransactionParams1.setDeliveryDate(WEB3DateUtility.getDate("20070506", "yyyyMMdd"));
//            l_feqFinTransactionParams1.setCurrencyCode("12");
//            l_feqFinTransactionParams1.setNetAmount(2D);
//            l_feqFinTransactionParams1.setNetAmountFc(1D);
//            l_feqFinTransactionParams1.setFxRate(0.5D);
//            l_feqFinTransactionParams1.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
//            l_feqFinTransactionParams1.setQuantity(1D);
//            l_feqFinTransactionParams1.setCommissionFee(0D);
//            l_feqFinTransactionParams1.setCommissionFeeTax(0D);
//            l_feqFinTransactionParams1.setCommissionFeeTaxFc(0D);
//            l_feqFinTransactionParams1.setCommissionFeeFc(0D);
//            l_feqFinTransactionParams1.setBalanceAmountFc(0D);
//            l_feqFinTransactionParams1.setForeignCommissionFee(0D);
//            l_feqFinTransactionParams1.setForeignTax(0D);
//            l_feqFinTransactionParams1.setForeignFeeExt1(0D);
//            l_feqFinTransactionParams1.setRegNo("2");
//            l_feqFinTransactionParams1.setForeignFeeExt2(0D);
//            l_feqFinTransactionParams1.setCapitalGain(0D);
//            l_feqFinTransactionParams1.setCapitalGainFc(0D);
//            l_feqFinTransactionParams1.setCapitalGainTax(0D);
//            l_feqFinTransactionParams1.setCapitalGainTaxFc(0D);
//            l_feqFinTransactionParams1.setDeleteFlag(BooleanEnum.FALSE);
//            l_feqFinTransactionParams1.setFinTransactionTimestamp(WEB3DateUtility.getDate("20071106", "yyyyMMdd"));
//            l_feqFinTransactionParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20071106", "yyyyMMdd"));
//            l_feqFinTransactionParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071106", "yyyyMMdd"));
//            TestDBUtility.insertWithDel(l_feqFinTransactionParams1);
//            
//            TestDBUtility.deleteAll(SubAccountRow.TYPE);
//            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
//            l_subAccountParams.setAccountId(1L);
//            l_subAccountParams.setSubAccountId(1L);
//            TestDBUtility.insertWithDel(l_subAccountParams);
//            
//            TestDBUtility.deleteAll(ProductRow.TYPE);
//            ProductParams l_productParams = TestDBUtility.getProductRow();
//            l_productParams.setProductId(1L);
//            TestDBUtility.insertWithDel(l_productParams);
//            
//            TestDBUtility.deleteAll(InstitutionRow.TYPE);
//            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//            l_institutionParams.setInstitutionCode("0D");
//            TestDBUtility.insertWithDel(l_institutionParams);
//            
//            TestDBUtility.deleteAll(FeqProductRow.TYPE);
//            FeqProductParams l_feqProductParams = TestDBUtility.getFeqProductRow();
//            l_feqProductParams.setProductId(1L);
//            TestDBUtility.insertWithDel(l_feqProductParams);
//            
//            TestDBUtility.deleteAll(FeqOrderUnitRow.TYPE);
//            FeqOrderUnitParams l_feqOrderUnitRow = TestDBUtility.getFeqOrderUnitRow();
//            l_feqOrderUnitRow.setOrderUnitId(1L);
//            TestDBUtility.insertWithDel(l_feqOrderUnitRow);
//            
//            FeqOrderUnitParams l_feqOrderUnitRow1 = TestDBUtility.getFeqOrderUnitRow();
//            l_feqOrderUnitRow1.setOrderUnitId(2L);
//            l_feqOrderUnitRow1.setOrderId(1L);
//            l_feqOrderUnitRow1.setOrderRequestNumber("3");
//            l_feqOrderUnitRow1.setOrderEmpCode("1235");
//
//            TestDBUtility.insertWithDel(l_feqOrderUnitRow1);
//            
//            WEB3FeqAmountCalcResult[] l_feqAmountCalcResultDetails = new WEB3FeqAmountCalcResult[2];
//            WEB3FeqAmountCalcResult l_result = new WEB3FeqAmountCalcResult();
//            WEB3FeqAmountCalcResult l_result1 = new WEB3FeqAmountCalcResult();
//            l_feqAmountCalcResultDetails[0] = l_result;
//            l_feqAmountCalcResultDetails[1] = l_result1;
//            l_feqAmountCalcResultDetails[0].setBalanceAmount(1D);
//            l_feqAmountCalcResultDetails[0].setCommissionBranchNumber("1");
//            l_feqAmountCalcResultDetails[0].setCommissionFeeFc(1D);
//            l_feqAmountCalcResultDetails[0].setCommissionFeeTaxFc(1D);
//            l_feqAmountCalcResultDetails[0].setNetAmount(2D);
//            l_feqAmountCalcResultDetails[0].setCommissionNumber("1");
//            l_feqAmountCalcResultDetails[0].setCommissionBranchNumber("1");
//            l_feqAmountCalcResultDetails[1].setBalanceAmount(1D);
//            l_feqAmountCalcResultDetails[1].setCommissionBranchNumber("1");
//            l_feqAmountCalcResultDetails[1].setCommissionFeeFc(1D);
//            l_feqAmountCalcResultDetails[1].setCommissionFeeTaxFc(1D);
//            l_feqAmountCalcResultDetails[1].setNetAmount(2D);
//            l_feqAmountCalcResultDetails[1].setCommissionNumber("1");
//            l_feqAmountCalcResultDetails[1].setCommissionBranchNumber("1");
//            WEB3FeqAmountCalcResult feqAmountCalcResultTotal = new WEB3FeqAmountCalcResult();
//            feqAmountCalcResultTotal.setBalanceAmountFc(1D);
//            feqAmountCalcResultTotal.setCommissionNumber("1");
//            feqAmountCalcResultTotal.setCommissionBranchNumber("1");
//            WEB3FeqAmountCalcResultFactor l_factor = new WEB3FeqAmountCalcResultFactor();
//            l_factor.setFeqAmountCalcResultDetails(l_feqAmountCalcResultDetails);
//            l_factor.setFeqAmountCalcResultTotal(feqAmountCalcResultTotal);
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.feq.WEB3FeqBizLogicProvider",
//                    "calcFeqAmountFactor",
//                    new Class[]{FeqFinTransactionParams[].class},
//                    l_factor);
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.feq.WEB3FeqBizLogicProvider", 
//                    "calcCapitalProfitLoss",
//                    new Class[]
//                    { double.class, double.class, long.class, WEB3GentradeSubAccount.class, TaxTypeEnum.class},
//                    new Double(12));
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.feq.WEB3FeqBizLogicProvider", "calcForeignCCYAmount", new Class[]
//                    { double.class, double.class, long.class, boolean.class, boolean.class},
//                    new Double(12));
//
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.feq.WEB3FeqOrderManager",
//                    "updateEstimatedPrice",
//                    new Class[]{WEB3FeqOrderUnit.class, Date.class},
//                    null);
//                    
//            long[] l_lngOrderUnitId = new long[]{1, 2};
//            Date l_dat = WEB3DateUtility.getDate("20071106", "yyyyMMdd");
//            l_forTest.updateTransaction(l_lngOrderUnitId, l_dat);
//            
//            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
//                    "webbroker3.feq.WEB3FeqBizLogicProvider", "calcForeignCCYAmount", new Class[]
//                    { double.class, double.class, long.class, boolean.class, boolean.class});
//            assertEquals(0.5, ((Double)l_paramsValue1.getFirstCalled()[1]).doubleValue(), 0);
//        }
//        catch (Exception l_ex)
//        {
//            log.debug(l_ex.getMessage(), l_ex);
//            fail();   
//        }
//        
//        log.exiting(STR_METHOD_NAME);
//    }
//    
//    public class WEB3FeqPositionManagerHelperForTest extends WEB3FeqPositionManagerHelper
//    {
//        public WEB3FeqPositionManagerHelperForTest(ProductTypeEnum tradingModuleType)
//        {
//            super(tradingModuleType);
//        }
//
//        protected void notifyGtl(FeqFinTransactionParams l_feqFinTransactionParams)
//        {
//            
//        }
//    }
    
    //更新後簿価 = 更新前簿価－（更新前）受渡代金＋（更新後）受渡代金
  public void testAssetUpdateNettingExchangeRateAdoptionCase1()
  {
      final String STR_METHOD_NAME = "testAssetUpdateNettingExchangeRateAdoptionCase1()";
      log.entering(STR_METHOD_NAME);
      
      try
      {
          TestDBUtility.deleteAll(AssetParams.TYPE);
          AssetParams l_assetParams = TestDBUtility.getAssetRow();
          l_assetParams.setAssetId(1001);
          l_assetParams.setAccountId(101001010010L);
          l_assetParams.setSubAccountId(10100101001007L);
          l_assetParams.setTaxType(TaxTypeEnum.NORMAL);
//          l_assetParams.setQuantityForBookValue(10000);
//          l_assetParams.setBookValue(0);
          TestDBUtility.insertWithDel(l_assetParams);
          
          WEB3FeqPositionManagerHelper l_helper = new WEB3FeqPositionManagerHelper(ProductTypeEnum.FOREIGN_EQUITY);
          l_helper.assetUpdateNettingExchangeRateAdoption(1001, 100, 150);
          
          
          AssetParams l_assetParam = null;
          try
          {
              l_assetParam = new AssetParams(AssetDao.findRowByPk(1001));
              assertEquals(l_assetParam.getBookValue(), 50.0, 0);
          }
          catch (DataFindException l_ex)
          {
              log.error("保有資産該当データなし。", l_ex);
              log.exiting(STR_METHOD_NAME);
              throw new WEB3SystemLayerException(
                  WEB3ErrorCatalog.BUSINESS_ERROR_00204,
                  this.getClass().getName() + "." + STR_METHOD_NAME,
                  l_ex.getMessage(),
                  l_ex);
          }
          catch (DataQueryException l_ex)
          {
              log.error("DBへのアクセスに失敗しました。 ", l_ex);
              log.exiting(STR_METHOD_NAME);
              throw new WEB3SystemLayerException(
                  WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                  this.getClass().getName() + "." + STR_METHOD_NAME,
                  l_ex.getMessage(),
                  l_ex);
          }
          catch (DataNetworkException l_ex)        
          {
              log.error("DBへのアクセスに失敗しました。 ", l_ex);
              log.exiting(STR_METHOD_NAME);
              throw new WEB3SystemLayerException(
                  WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                  this.getClass().getName() + "." + STR_METHOD_NAME,
                  l_ex.getMessage(),
                  l_ex);
          }
      }
      catch (Exception l_ex)
      {
          log.debug(l_ex.getMessage(), l_ex);
          fail();   
      }
  }
  
  /** ２）保有資産Params.税区分＝"一般口座"かつ <BR>
  * 　@　@保有資産Param.数量（簿価単価計算用）＞0 かつ <BR>
  * 　@　@保有資産Param.簿価（簿価単価計算用）＝0の場合、 <BR>
  * 　@　@何もせず処理をreturnする。 <BR>
  */
  public void testAssetUpdateNettingExchangeRateAdoptionCase2()
  {
      final String STR_METHOD_NAME = "testAssetUpdateNettingExchangeRateAdoptionCase2()";
      log.entering(STR_METHOD_NAME);
      
      try
      {
          TestDBUtility.deleteAll(AssetParams.TYPE);
          AssetParams l_assetParams = TestDBUtility.getAssetRow();
          l_assetParams.setAssetId(1001);
          l_assetParams.setAccountId(101001010010L);
          l_assetParams.setSubAccountId(10100101001007L);
          l_assetParams.setTaxType(TaxTypeEnum.NORMAL);
          l_assetParams.setQuantityForBookValue(10000);
          l_assetParams.setBookValue(0);
          TestDBUtility.insertWithDel(l_assetParams);
          
          WEB3FeqPositionManagerHelper l_helper = new WEB3FeqPositionManagerHelper(ProductTypeEnum.FOREIGN_EQUITY);
          l_helper.assetUpdateNettingExchangeRateAdoption(1001, 100, 150);
          
          
          AssetParams l_assetParam = null;
          try
          {
              l_assetParam = new AssetParams(AssetDao.findRowByPk(1001));
              assertEquals(l_assetParam.getBookValue(), 0.0, 0);
          }
          catch (DataFindException l_ex)
          {
              log.error("保有資産該当データなし。", l_ex);
              log.exiting(STR_METHOD_NAME);
              throw new WEB3SystemLayerException(
                  WEB3ErrorCatalog.BUSINESS_ERROR_00204,
                  this.getClass().getName() + "." + STR_METHOD_NAME,
                  l_ex.getMessage(),
                  l_ex);
          }
          catch (DataQueryException l_ex)
          {
              log.error("DBへのアクセスに失敗しました。 ", l_ex);
              log.exiting(STR_METHOD_NAME);
              throw new WEB3SystemLayerException(
                  WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                  this.getClass().getName() + "." + STR_METHOD_NAME,
                  l_ex.getMessage(),
                  l_ex);
          }
          catch (DataNetworkException l_ex)        
          {
              log.error("DBへのアクセスに失敗しました。 ", l_ex);
              log.exiting(STR_METHOD_NAME);
              throw new WEB3SystemLayerException(
                  WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                  this.getClass().getName() + "." + STR_METHOD_NAME,
                  l_ex.getMessage(),
                  l_ex);
          }
      }
      catch (Exception l_ex)
      {
          log.debug(l_ex.getMessage(), l_ex);
          fail();   
      }
  }
  
  /**
    * １）資産IDより保有資産Paramを取得する。 <BR>
    * ※保有資産に該当するデータがない、例外をスローする。<BR>
    * 　@class: WEB3BusinessLayerException<BR>
    * 　@tag 　@:   BUSINESS_ERROR_00204<BR>
   */
  public void testAssetUpdateNettingExchangeRateAdoptionCase3()
  {
      final String STR_METHOD_NAME = "testAssetUpdateNettingExchangeRateAdoptionCase3()";
      log.entering(STR_METHOD_NAME);
      
      try
      {
          TestDBUtility.deleteAll(AssetParams.TYPE);
          AssetParams l_assetParams = TestDBUtility.getAssetRow();
          l_assetParams.setAssetId(1001);
          l_assetParams.setAccountId(101001010010L);
          l_assetParams.setSubAccountId(10100101001007L);
          l_assetParams.setTaxType(TaxTypeEnum.NORMAL);
          l_assetParams.setQuantityForBookValue(10000);
          l_assetParams.setBookValue(0);
          TestDBUtility.insertWithDel(l_assetParams);
          
          WEB3FeqPositionManagerHelper l_helper = new WEB3FeqPositionManagerHelper(ProductTypeEnum.FOREIGN_EQUITY);
          l_helper.assetUpdateNettingExchangeRateAdoption(1002, 100, 150);
          fail();
      }
      catch (WEB3BaseException l_ex)
      {
          log.debug(l_ex.getMessage(), l_ex);
          assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00204);
      }
      catch (Exception l_ex)
      {
          log.debug(l_ex.getMessage(), l_ex);
          fail();   
      }
  }
  
  //updateトランザクション（ネッティング採用）
  
  public void testUpdateTransactionNettingAdoptionCase1()
  {
      final String STR_METHOD_NAME = "testUpdateTransactionNettingAdoptionCase1()";
      log.entering(STR_METHOD_NAME);
      
      try
      {
          TestDBUtility.deleteAll(FeqFinTransactionRow.TYPE);
          long[] l_lngOrderUnitIDs = new long[4];
          l_lngOrderUnitIDs[0] = 1L;
          l_lngOrderUnitIDs[1] = 2L;
          l_lngOrderUnitIDs[2] = 3L;
          l_lngOrderUnitIDs[3] = 4L;
          
          WEB3FeqPositionManagerHelper l_helper = new WEB3FeqPositionManagerHelper(ProductTypeEnum.FOREIGN_EQUITY);
          l_helper.updateTransactionNettingAdoption(l_lngOrderUnitIDs, WEB3DateUtility.getDate("20100127", "yyyyMMdd"));
          
      }
      catch (Exception l_ex)
      {
          log.debug(l_ex.getMessage(), l_ex);
          fail();   
      }
  }

  
  //getネッティング為替レート
  public void testUpdateTransactionNettingAdoptionCase2()
  {
      final String STR_METHOD_NAME = "testUpdateTransactionNettingAdoptionCase2()";
      log.entering(STR_METHOD_NAME);
      
      try
      {
          TestDBUtility.deleteAll(AssetParams.TYPE);
          AssetParams l_assetParams = TestDBUtility.getAssetRow();
          l_assetParams.setAssetId(1001);
          l_assetParams.setAccountId(31102102050L);
          l_assetParams.setSubAccountId(33381251220301L);
          l_assetParams.setTaxType(TaxTypeEnum.NORMAL);
          l_assetParams.setQuantityForBookValue(10000);
          l_assetParams.setBookValue(0);
          TestDBUtility.insertWithDel(l_assetParams);
          TestDBUtility.deleteAll(InstitutionParams.TYPE);
          InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
          l_institutionParams.setInstitutionId(33);
          l_institutionParams.setInstitutionCode("0D");
          TestDBUtility.insertWithDel(l_institutionParams);
          InstitutionParams l_institutionParams1 = TestDBUtility.getInstitutionRow();
          l_institutionParams1.setInstitutionId(11);
          l_institutionParams1.setInstitutionCode("2D");
          TestDBUtility.insertWithDel(l_institutionParams1);
          
          TestDBUtility.deleteAll(MainAccountRow.TYPE);
          MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
          l_mainAccountParams.setAccountId(31102102050L);
          TestDBUtility.insertWithDel(l_mainAccountParams);
          
          TestDBUtility.deleteAll(BranchParams.TYPE);
          BranchParams l_BranchParams = TestDBUtility.getBranchRow();
          TestDBUtility.insertWithDel(l_BranchParams);
          
          TestDBUtility.deleteAll(SubAccountRow.TYPE);
          SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
          l_subAccountParams.setAccountId(31102102050L);
          l_subAccountParams.setSubAccountId(33381251220301L);
          l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
          TestDBUtility.insertWithDel(l_subAccountParams);
          
          TestDBUtility.deleteAll(FeqFinTransactionRow.TYPE);
          FeqFinTransactionParams l_feqFinTransactionParams1 = TestDBUtility.getFeqFinTransactionParams();
          l_feqFinTransactionParams1.setFinTransactionId(1L);
          l_feqFinTransactionParams1.setAssetId(1001);
          l_feqFinTransactionParams1.setOrderUnitId(1L);
          l_feqFinTransactionParams1.setOrderId(111111L);
          l_feqFinTransactionParams1.setAccountId(31102102050L);
          l_feqFinTransactionParams1.setSubAccountId(33381251220301L);
          l_feqFinTransactionParams1.setCurrencyCode("A1");
          l_feqFinTransactionParams1.setFinTransactionType(FinTransactionType.EQTYPE_FEQ_BUY);
          l_feqFinTransactionParams1.setBalanceAmountFc(125);
          l_feqFinTransactionParams1.setOrderExecutionId(1);
          l_feqFinTransactionParams1.setProductId(111111);
          l_feqFinTransactionParams1.setMarketId(33);
          l_feqFinTransactionParams1.setPrice(10);
          l_feqFinTransactionParams1.setRegNo("1");
          TestDBUtility.insertWithDel(l_feqFinTransactionParams1);
          FeqFinTransactionParams l_feqFinTransactionParams2 = TestDBUtility.getFeqFinTransactionParams();
          l_feqFinTransactionParams2.setFinTransactionId(2L);
          l_feqFinTransactionParams2.setAssetId(1001);
          l_feqFinTransactionParams2.setOrderUnitId(1L);
          l_feqFinTransactionParams2.setOrderId(111111L);
          l_feqFinTransactionParams2.setAccountId(31102102050L);
          l_feqFinTransactionParams2.setSubAccountId(33381251220301L);
          l_feqFinTransactionParams2.setCurrencyCode("A1");
          l_feqFinTransactionParams2.setFinTransactionType(FinTransactionType.EQTYPE_FEQ_SELL);
          l_feqFinTransactionParams2.setBalanceAmountFc(125);
          l_feqFinTransactionParams2.setOrderExecutionId(2);
          l_feqFinTransactionParams2.setProductId(111111);
          l_feqFinTransactionParams2.setMarketId(33);
          l_feqFinTransactionParams2.setPrice(20);
          l_feqFinTransactionParams2.setRegNo("2");
          TestDBUtility.insertWithDel(l_feqFinTransactionParams2);
          FeqFinTransactionParams l_feqFinTransactionParams4 = TestDBUtility.getFeqFinTransactionParams();
          l_feqFinTransactionParams4.setFinTransactionId(4L);
          l_feqFinTransactionParams4.setAssetId(1001);
          l_feqFinTransactionParams4.setOrderUnitId(3L);
          l_feqFinTransactionParams4.setOrderId(333333L);
          l_feqFinTransactionParams4.setAccountId(31102102050L);
          l_feqFinTransactionParams4.setSubAccountId(33381251220301L);
          l_feqFinTransactionParams4.setCurrencyCode("A2");
          l_feqFinTransactionParams4.setFinTransactionType(FinTransactionType.EQTYPE_FEQ_SELL);
          l_feqFinTransactionParams4.setBalanceAmountFc(125);
          l_feqFinTransactionParams4.setOrderExecutionId(4);
          l_feqFinTransactionParams4.setProductId(333333);
          l_feqFinTransactionParams4.setMarketId(11);
          l_feqFinTransactionParams4.setPrice(30);
          l_feqFinTransactionParams4.setRegNo("4");
          TestDBUtility.insertWithDel(l_feqFinTransactionParams4);
          
          TestDBUtility.deleteAll(GenCurrencyParams.TYPE);
          GenCurrencyParams l_currencyParams = TestDBUtility.getGenCurrencyRow();
          l_currencyParams.setInstitutionCode("0D");
          l_currencyParams.setCurrencyCode("A1");
          l_currencyParams.setCurrentSellExecRate(111.1);
          l_currencyParams.setCurrentBuyExecRate(110.0);
          l_currencyParams.setScale(6);
          TestDBUtility.insertWithDel(l_currencyParams);
          GenCurrencyParams l_currencyParams1 = TestDBUtility.getGenCurrencyRow();
          l_currencyParams1.setInstitutionCode("0D");
          l_currencyParams1.setCurrencyCode("A2");
          l_currencyParams1.setCurrentSellExecRate(222.2);
          l_currencyParams1.setCurrentBuyExecRate(220.0);
          l_currencyParams1.setScale(6);
          TestDBUtility.insertWithDel(l_currencyParams1);
          
          TestDBUtility.deleteAll(FeqOrderExecutionRow.TYPE);
          FeqOrderExecutionParams l_FeqOrderExecutionParams = TestDBUtility.getFeqOrderExecutionParams();
          l_FeqOrderExecutionParams.setOrderExecutionId(1);
          l_FeqOrderExecutionParams.setFxRate(100);
          TestDBUtility.insertWithDel(l_FeqOrderExecutionParams);
          FeqOrderExecutionParams l_FeqOrderExecutionParams1 = TestDBUtility.getFeqOrderExecutionParams();
          l_FeqOrderExecutionParams1.setOrderExecutionId(2);
          l_FeqOrderExecutionParams1.setFxRate(100);
          TestDBUtility.insertWithDel(l_FeqOrderExecutionParams1);
          FeqOrderExecutionParams l_FeqOrderExecutionParams3 = TestDBUtility.getFeqOrderExecutionParams();
          l_FeqOrderExecutionParams3.setOrderExecutionId(4);
          l_FeqOrderExecutionParams3.setFxRate(100);
          TestDBUtility.insertWithDel(l_FeqOrderExecutionParams3);
          
          TestDBUtility.deleteAll(ProductParams.TYPE);
          ProductParams l_ProductParams = TestDBUtility.getProductRow();
          l_ProductParams.setProductId(111111);
          l_ProductParams.setInstitutionCode("0D");
          TestDBUtility.insertWithDel(l_ProductParams);
          ProductParams l_ProductParams2 = TestDBUtility.getProductRow();
          l_ProductParams2.setProductId(333333);
          l_ProductParams2.setInstitutionCode("2D");
          TestDBUtility.insertWithDel(l_ProductParams2);
          
          TestDBUtility.deleteAll(FeqProductParams.TYPE);
          FeqProductParams l_FeqProductParams = TestDBUtility.getFeqProductRow();
          l_FeqProductParams.setProductId(111111);
          l_FeqProductParams.setProductCode("111");
          l_FeqProductParams.setInstitutionCode("0D");
          l_FeqProductParams.setCurrencyCode("A1");
          l_FeqProductParams.setMarketCode("11");
          TestDBUtility.insertWithDel(l_FeqProductParams);
          FeqProductParams l_FeqProductParams2 = TestDBUtility.getFeqProductRow();
          l_FeqProductParams2.setProductId(333333);
          l_FeqProductParams2.setProductCode("333");
          l_FeqProductParams2.setInstitutionCode("2D");
          l_FeqProductParams2.setMarketCode("33");
          TestDBUtility.insertWithDel(l_FeqProductParams2);
          
          TestDBUtility.deleteAll(TradedProductParams.TYPE);
          TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
          l_tradedProductParams.setProductId(111111);
          l_tradedProductParams.setTradedProductId(1111);
          l_tradedProductParams.setMarketId(11);
//          l_tradedProductParams.setValidForBizDate(null);
          TestDBUtility.insertWithDel(l_tradedProductParams);
          TradedProductParams l_tradedProductParams1 = TestDBUtility.getTradedProductRow();
          l_tradedProductParams1.setProductId(333333);
          l_tradedProductParams1.setTradedProductId(3333);
          l_tradedProductParams.setMarketId(33);
//          l_tradedProductParams1.setValidForBizDate(null);
          TestDBUtility.insertWithDel(l_tradedProductParams1);
          
          TestDBUtility.deleteAll(FeqTradedProductParams.TYPE);
          FeqTradedProductParams l_feqTradedProductParams = TestDBUtility.getFeqTradedProductRow();
          l_feqTradedProductParams.setProductId(111111);
          l_feqTradedProductParams.setTradedProductId(1111);
          l_feqTradedProductParams.setMarketId(11);
          l_feqTradedProductParams.setValidForBizDate(null);
          l_feqTradedProductParams.setInstitutionCode("0D");
          TestDBUtility.insertWithDel(l_feqTradedProductParams);
          FeqTradedProductParams l_feqTradedProductParams1 = TestDBUtility.getFeqTradedProductRow();
          l_feqTradedProductParams1.setProductId(333333);
          l_feqTradedProductParams1.setTradedProductId(3333);
          l_feqTradedProductParams1.setMarketId(33);
          l_feqTradedProductParams1.setValidForBizDate(null);
          l_feqTradedProductParams1.setInstitutionCode("2D");
          TestDBUtility.insertWithDel(l_feqTradedProductParams1);
          
          TestDBUtility.deleteAll(MarketParams.TYPE);
          MarketParams l_marketParams = TestDBUtility.getMarketRow();
          l_marketParams.setMarketId(33);
          l_marketParams.setMarketCode("33");
          l_marketParams.setInstitutionCode("2D");
          TestDBUtility.insertWithDel(l_marketParams);
          MarketParams l_marketParams1 = TestDBUtility.getMarketRow();
          l_marketParams1.setMarketId(11);
          l_marketParams1.setMarketCode("11");
          l_marketParams1.setInstitutionCode("0D");
          TestDBUtility.insertWithDel(l_marketParams1);
          
          TestDBUtility.deleteAll(FeqOrderUnitParams.TYPE);
          FeqOrderUnitParams l_FeqOrderUnitParams = TestDBUtility.getFeqOrderUnitRow();
          l_FeqOrderUnitParams.setAccountId(31102102050L);
          l_FeqOrderUnitParams.setSubAccountId(33381251220301L);
          l_FeqOrderUnitParams.setOrderUnitId(1L);
          l_FeqOrderUnitParams.setOrderId(111111L);
          l_FeqOrderUnitParams.setOrderRequestNumber("1");
          l_FeqOrderUnitParams.setOrderEmpCode("1");
          l_FeqOrderUnitParams.setProductId(111111);
          l_FeqOrderUnitParams.setMarketId(11);
          l_FeqOrderUnitParams.setLastOrderActionSerialNo(1);
          l_FeqOrderUnitParams.setConfirmedQuantity(1200);
          TestDBUtility.insertWithDel(l_FeqOrderUnitParams);
          FeqOrderUnitParams l_FeqOrderUnitParams1 = TestDBUtility.getFeqOrderUnitRow();
          l_FeqOrderUnitParams1.setAccountId(31102102050L);
          l_FeqOrderUnitParams1.setSubAccountId(33381251220301L);
          l_FeqOrderUnitParams1.setOrderUnitId(3L);
          l_FeqOrderUnitParams1.setOrderId(333333L);
          l_FeqOrderUnitParams1.setOrderRequestNumber("3");
          l_FeqOrderUnitParams1.setOrderEmpCode("3");
          l_FeqOrderUnitParams1.setProductId(333333);
          l_FeqOrderUnitParams1.setMarketId(33);
          l_FeqOrderUnitParams1.setLastOrderActionSerialNo(3);
          l_FeqOrderUnitParams1.setConfirmedQuantity(1200);
          TestDBUtility.insertWithDel(l_FeqOrderUnitParams1);
          
          TestDBUtility.deleteAll(FeqOrderActionParams.TYPE);
          FeqOrderActionParams l_FeqOrderActionParams = TestDBUtility.getFeqOrderActionParams();
          l_FeqOrderActionParams.setOrderUnitId(1L);
          l_FeqOrderActionParams.setOrderActionSerialNo(1);
          TestDBUtility.insertWithDel(l_FeqOrderActionParams);
          
          TestDBUtility.deleteAll(EquityCommAccountCondMstParams.TYPE);
          EquityCommAccountCondMstParams l_EquityCommAccountCondMstParams = TestDBUtility.getEquityCommAccountCondMstRow();
          l_EquityCommAccountCondMstParams.setInstitutionCode("0D");
          l_EquityCommAccountCondMstParams.setBranchId(33381);
          l_EquityCommAccountCondMstParams.setAccountId(31102102050L);
          l_EquityCommAccountCondMstParams.setCommProductCode("40");
          l_EquityCommAccountCondMstParams.setValidUntilBizDate("20100127");
          TestDBUtility.insertWithDel(l_EquityCommAccountCondMstParams);
          EquityCommAccountCondMstParams l_EquityCommAccountCondMstParams1 = TestDBUtility.getEquityCommAccountCondMstRow();
          l_EquityCommAccountCondMstParams1.setInstitutionCode("0D");
          l_EquityCommAccountCondMstParams1.setBranchId(33381);
          l_EquityCommAccountCondMstParams1.setAccountId(31102102050L);
          l_EquityCommAccountCondMstParams1.setCommProductCode("40");
          l_EquityCommAccountCondMstParams1.setValidUntilBizDate("20080201");
          TestDBUtility.insertWithDel(l_EquityCommAccountCondMstParams1);
          EquityCommAccountCondMstParams l_EquityCommAccountCondMstParams2 = TestDBUtility.getEquityCommAccountCondMstRow();
          l_EquityCommAccountCondMstParams2.setInstitutionCode("0D");
          l_EquityCommAccountCondMstParams2.setBranchId(33381);
          l_EquityCommAccountCondMstParams2.setAccountId(31102102050L);
          l_EquityCommAccountCondMstParams2.setCommProductCode("40");
          l_EquityCommAccountCondMstParams2.setValidUntilBizDate("20070228");
          TestDBUtility.insertWithDel(l_EquityCommAccountCondMstParams2);
          
          TestDBUtility.deleteAll(EquityCommCondRow.TYPE);
          EquityCommCondParams l_EquityCommCondParams = new EquityCommCondParams();
          //証券会社コード  institution_code
          l_EquityCommCondParams.setInstitutionCode("0D");
          //手数料商品コード    comm_product_code
          l_EquityCommCondParams.setCommProductCode("40");
          //登録No         reg_no
          l_EquityCommCondParams.setRegNo("01 ");
          //適用開始年月日  appli_start_date
          l_EquityCommCondParams.setAppliStartDate(WEB3DateUtility.getDate("20061230", "yyyyMMdd"));
          //適用終了年月日  appli_end_date
          l_EquityCommCondParams.setAppliEndDate(WEB3DateUtility.getDate("20101230", "yyyyMMdd"));
          //シーケンスNo  sequence_no
          l_EquityCommCondParams.setSequenceNo("1");
          //売買代金（自）  min_turnover
          l_EquityCommCondParams.setMinTurnover(10);
          //売買代金（至）  max_turnover
          l_EquityCommCondParams.setMaxTurnover(100000);
          //徴収率      charge_ratio
          l_EquityCommCondParams.setChargeRatio(10);
          //付加金額        added_price
          l_EquityCommCondParams.setAddedPrice(100);
          //登録区分        reg_type
          //登録年月日    reg_date
          //最終更新日    final_mod_date
          //最終更新時間  final_mod_time
          //作成日付        created_timestamp
          //更新日付        last_updated_timestamp
          l_EquityCommCondParams.setCreatedTimestamp(Calendar.getInstance().getTime());
          l_EquityCommCondParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
          //手数料乗数   commition_per_unit
          l_EquityCommCondParams.setCommitionPerUnit(100);
          TestDBUtility.insertWithDel(l_EquityCommCondParams);
          
          TestDBUtility.deleteAll(EquityCommCondMstRow.TYPE);
          EquityCommCondMstParams l_EquityCommCondMstParams = new EquityCommCondMstParams();
          //証券会社コード         institution_code              NotNull
          l_EquityCommCondMstParams.setInstitutionCode("0D");
          //手数料商品コード            comm_product_code  NotNull
          l_EquityCommCondMstParams.setCommProductCode("40");
          //登録No                reg_no                   NotNull
          l_EquityCommCondMstParams.setRegNo("01 ");
          //適用開始年月日         appli_start_date  NotNull
          //適用終了年月日         appli_end_date  NotNull
          l_EquityCommCondMstParams.setAppliStartDate(WEB3DateUtility.getDate("20061230", "yyyyMMdd"));
          l_EquityCommCondMstParams.setAppliEndDate(WEB3DateUtility.getDate("20101230", "yyyyMMdd"));
          //最高手数料           max_commission   NULL
          l_EquityCommCondMstParams.setMaxCommission(1000000);
          //最低手数料           min_commission    NotNull
          l_EquityCommCondMstParams.setMinCommission(10);
          //一口区分                share_type        NULL
          //手数料コースコード           commission_course_div      NotNull
          l_EquityCommCondMstParams.setCommissionCourseDiv("04");
          //備考              reference_column
          //特別徴収    設定開始年月日     spc_start_date
          //    設定終了年月日     spc_end_date
          //    徴収率         spc_charge_ratio
          //    最高手数料       spc_max_commission
          //    最低手数料       spc_min_commission
          //登録区分                reg_type
          //登録年月日           reg_date
          //最終更新日           final_mod_date
          //最終更新時間          final_mod_time
          //作成日付                created_timestamp
          //更新日付                last_updated_timestamp
          l_EquityCommCondMstParams.setCreatedTimestamp(Calendar.getInstance().getTime());
          l_EquityCommCondMstParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
          TestDBUtility.insertWithDel(l_EquityCommCondMstParams);
          
          
          MOCK_MANAGER.setIsMockUsed(true);
//          TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                  "webbroker3.feq.WEB3FeqBizLogicProvider",
//                  "calcCommission",
//                  new Class[] {WEB3GentradeCommission.class, SubAccount.class},
//                  null);
          
          WEB3FeqAmountCalcResult l_result = new WEB3FeqAmountCalcResult();
          l_result.setCommissionNumber("1");
          l_result.setCommissionBranchNumber("0");
          TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                  "webbroker3.feq.WEB3FeqBizLogicProvider",
                  "calcFeqAmount",
                  new Class[] {WEB3GentradeSubAccount.class,WEB3FeqProduct.class,WEB3GentradeMarket.class,
                          Date.class,Date.class,double.class,double.class,boolean.class,boolean.class,
                          boolean.class,String.class },
                          l_result);
          
          TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                  "webbroker3.feq.WEB3FeqBizLogicProvider",
                  "calcSalesTax",
                  new Class[] {double.class, Date.class, SubAccount.class },
                  new Double(10));
          
          long[] l_lngOrderUnitIDs = new long[4];
          l_lngOrderUnitIDs[0] = 1L;
          l_lngOrderUnitIDs[1] = 2L;
          l_lngOrderUnitIDs[2] = 3L;
          l_lngOrderUnitIDs[3] = 4L;
          
          WEB3FeqPositionManagerHelper l_helper = new WEB3FeqPositionManagerHelper(ProductTypeEnum.FOREIGN_EQUITY);
          l_helper.updateTransactionNettingAdoption(l_lngOrderUnitIDs, WEB3DateUtility.getDate("20100127", "yyyyMMdd"));

      }
      catch (Exception l_ex)
      {
          log.debug(l_ex.getMessage(), l_ex);
          fail();   
      }
  }
  
    //reverseAssetPositionByTrans
  public void testReverseAssetPositionByTransCase1()
  {
      final String STR_METHOD_NAME = "testReverseAssetPositionByTransCase1()";
      log.entering(STR_METHOD_NAME);
      
      try
      {
          FeqFinTransactionParams l_feqFinTransactionParams1 = TestDBUtility.getFeqFinTransactionParams();
          l_feqFinTransactionParams1.setFinTransactionId(1L);
          l_feqFinTransactionParams1.setOrderUnitId(1L);
          l_feqFinTransactionParams1.setOrderId(111111L);
          l_feqFinTransactionParams1.setAccountId(31102102050L);
          l_feqFinTransactionParams1.setSubAccountId(33381251220301L);
          l_feqFinTransactionParams1.setCurrencyCode("A1");
          l_feqFinTransactionParams1.setFinTransactionType(FinTransactionType.EQTYPE_FEQ_BUY);
          l_feqFinTransactionParams1.setBalanceAmountFc(125);
          l_feqFinTransactionParams1.setOrderExecutionId(1);
          l_feqFinTransactionParams1.setProductId(111111);
          l_feqFinTransactionParams1.setMarketId(33);
          l_feqFinTransactionParams1.setPrice(10);
          l_feqFinTransactionParams1.setRegNo("1");
          TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
          TestDBUtility.deleteAll(MarketPreferencesParams.TYPE);
          WEB3FeqPositionManagerHelper l_helper = new WEB3FeqPositionManagerHelper(ProductTypeEnum.FOREIGN_EQUITY);
          l_helper.reverseAssetPositionByTrans(l_feqFinTransactionParams1, SideEnum.BUY);
          
      }
      catch (Exception l_ex)
      {
          log.debug(l_ex.getMessage(), l_ex);
          fail();   
      }
  }
    
  public void testReverseAssetPositionByTransCase2()
  {
      final String STR_METHOD_NAME = "testReverseAssetPositionByTransCase2()";
      log.entering(STR_METHOD_NAME);
      
      try
      {
          TestDBUtility.deleteAll(InstitutionParams.TYPE);
          InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
          l_institutionParams.setInstitutionCode("0D");
          TestDBUtility.insertWithDel(l_institutionParams);
          
          TestDBUtility.deleteAll(MainAccountRow.TYPE);
          MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
          l_mainAccountParams.setAccountId(31102102050L);
          TestDBUtility.insertWithDel(l_mainAccountParams);
          
          MarketParams l_marketParams1 = TestDBUtility.getMarketRow();
          l_marketParams1.setMarketId(33);
          l_marketParams1.setMarketCode("33");
          l_marketParams1.setInstitutionCode("0D");
          TestDBUtility.insertWithDel(l_marketParams1);
          
          TestDBUtility.deleteAll(AssetParams.TYPE);
          AssetParams l_assetParams = TestDBUtility.getAssetRow();
          l_assetParams.setAssetId(1001);
          l_assetParams.setAccountId(31102102050L);
          l_assetParams.setSubAccountId(33381251220301L);
          l_assetParams.setTaxType(TaxTypeEnum.NORMAL);
          l_assetParams.setQuantityForBookValue(10000);
          l_assetParams.setBookValue(0);
          TestDBUtility.insertWithDel(l_assetParams);
          
          FeqFinTransactionParams l_feqFinTransactionParams1 = TestDBUtility.getFeqFinTransactionParams();
          l_feqFinTransactionParams1.setFinTransactionId(1L);
          l_feqFinTransactionParams1.setOrderUnitId(1L);
          l_feqFinTransactionParams1.setOrderId(111111L);
          l_feqFinTransactionParams1.setAccountId(31102102050L);
          l_feqFinTransactionParams1.setSubAccountId(33381251220301L);
          l_feqFinTransactionParams1.setCurrencyCode("A1");
          l_feqFinTransactionParams1.setFinTransactionType(FinTransactionType.EQTYPE_FEQ_BUY);
          l_feqFinTransactionParams1.setBalanceAmountFc(125);
          l_feqFinTransactionParams1.setOrderExecutionId(1);
          l_feqFinTransactionParams1.setProductId(111111);
          l_feqFinTransactionParams1.setMarketId(33);
          l_feqFinTransactionParams1.setPrice(10);
          l_feqFinTransactionParams1.setRegNo("1");
          l_feqFinTransactionParams1.setAssetId(1001);
          
          TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
          InstitutionPreferencesParams l_InstitutionPreferencesParams  = TestDBUtility.getInstitutionPreferencesRow();
          l_InstitutionPreferencesParams.setInstitutionId(33);
          l_InstitutionPreferencesParams.setName("feq.day.trade.div");
          l_InstitutionPreferencesParams.setNameSerialNo(1);
          TestDBUtility.insertWithDel(l_InstitutionPreferencesParams);
          
          TestDBUtility.deleteAll(MarketPreferencesParams.TYPE);
//          MarketPreferencesParams l_MarketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
//          l_MarketPreferencesParams.setMarketId(33);
//          l_MarketPreferencesParams.setName("feq.day.trade.market.div");
//          l_MarketPreferencesParams.setNameSerialNo(1);
//          TestDBUtility.insertWithDel(l_MarketPreferencesParams);
          
          WEB3FeqPositionManagerHelper l_helper = new WEB3FeqPositionManagerHelper(ProductTypeEnum.FOREIGN_EQUITY);
          l_helper.reverseAssetPositionByTrans(l_feqFinTransactionParams1, SideEnum.BUY);
          
      }
      catch (Exception l_ex)
      {
          log.debug(l_ex.getMessage(), l_ex);
          fail();   
      }
  }
  
  public void testReverseAssetPositionByTransCase3()
  {
      final String STR_METHOD_NAME = "testReverseAssetPositionByTransCase3()";
      log.entering(STR_METHOD_NAME);
      
      try
      {
          TestDBUtility.deleteAll(InstitutionParams.TYPE);
          InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
          l_institutionParams.setInstitutionCode("0D");
          TestDBUtility.insertWithDel(l_institutionParams);
          
          TestDBUtility.deleteAll(MainAccountRow.TYPE);
          MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
          l_mainAccountParams.setAccountId(31102102050L);
          TestDBUtility.insertWithDel(l_mainAccountParams);
          
          MarketParams l_marketParams1 = TestDBUtility.getMarketRow();
          l_marketParams1.setMarketId(33);
          l_marketParams1.setMarketCode("33");
          l_marketParams1.setInstitutionCode("0D");
          TestDBUtility.insertWithDel(l_marketParams1);
          
          TestDBUtility.deleteAll(AssetParams.TYPE);
          AssetParams l_assetParams = TestDBUtility.getAssetRow();
          l_assetParams.setAssetId(1001);
          l_assetParams.setAccountId(31102102050L);
          l_assetParams.setSubAccountId(33381251220301L);
          l_assetParams.setTaxType(TaxTypeEnum.NORMAL);
          l_assetParams.setQuantityForBookValue(10000);
          l_assetParams.setBookValue(0);
          TestDBUtility.insertWithDel(l_assetParams);
          
          FeqFinTransactionParams l_feqFinTransactionParams1 = TestDBUtility.getFeqFinTransactionParams();
          l_feqFinTransactionParams1.setFinTransactionId(1L);
          l_feqFinTransactionParams1.setOrderUnitId(1L);
          l_feqFinTransactionParams1.setOrderId(111111L);
          l_feqFinTransactionParams1.setAccountId(31102102050L);
          l_feqFinTransactionParams1.setSubAccountId(33381251220301L);
          l_feqFinTransactionParams1.setCurrencyCode("A1");
          l_feqFinTransactionParams1.setFinTransactionType(FinTransactionType.EQTYPE_FEQ_BUY);
          l_feqFinTransactionParams1.setBalanceAmountFc(125);
          l_feqFinTransactionParams1.setOrderExecutionId(1);
          l_feqFinTransactionParams1.setProductId(111111);
          l_feqFinTransactionParams1.setMarketId(33);
          l_feqFinTransactionParams1.setPrice(10);
          l_feqFinTransactionParams1.setRegNo("1");
          l_feqFinTransactionParams1.setAssetId(1001);
          
          TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
//          InstitutionPreferencesParams l_InstitutionPreferencesParams  = TestDBUtility.getInstitutionPreferencesRow();
//          l_InstitutionPreferencesParams.setInstitutionId(33);
//          l_InstitutionPreferencesParams.setName("feq.day.trade.div");
//          l_InstitutionPreferencesParams.setNameSerialNo(1);
//          TestDBUtility.insertWithDel(l_InstitutionPreferencesParams);
          
          TestDBUtility.deleteAll(MarketPreferencesParams.TYPE);
          MarketPreferencesParams l_MarketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
          l_MarketPreferencesParams.setMarketId(33);
          l_MarketPreferencesParams.setName("feq.day.trade.market.div");
          l_MarketPreferencesParams.setNameSerialNo(1);
          l_MarketPreferencesParams.setValue("1");
          TestDBUtility.insertWithDel(l_MarketPreferencesParams);
          
          WEB3FeqPositionManagerHelper l_helper = new WEB3FeqPositionManagerHelper(ProductTypeEnum.FOREIGN_EQUITY);
          l_helper.reverseAssetPositionByTrans(l_feqFinTransactionParams1, SideEnum.BUY);
          
      }
      catch (Exception l_ex)
      {
          log.debug(l_ex.getMessage(), l_ex);
          fail();   
      }
  }
  
  public void testReverseAssetPositionByTransCase4()
  {
      final String STR_METHOD_NAME = "testReverseAssetPositionByTransCase4()";
      log.entering(STR_METHOD_NAME);
      
      try
      {
          TestDBUtility.deleteAll(InstitutionParams.TYPE);
          InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
          l_institutionParams.setInstitutionCode("0D");
          TestDBUtility.insertWithDel(l_institutionParams);
          
          TestDBUtility.deleteAll(MainAccountRow.TYPE);
          MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
          l_mainAccountParams.setAccountId(31102102050L);
          TestDBUtility.insertWithDel(l_mainAccountParams);
          
          MarketParams l_marketParams1 = TestDBUtility.getMarketRow();
          l_marketParams1.setMarketId(33);
          l_marketParams1.setMarketCode("33");
          l_marketParams1.setInstitutionCode("0D");
          TestDBUtility.insertWithDel(l_marketParams1);
          
          TestDBUtility.deleteAll(AssetParams.TYPE);
          AssetParams l_assetParams = TestDBUtility.getAssetRow();
          l_assetParams.setAssetId(1001);
          l_assetParams.setAccountId(31102102050L);
          l_assetParams.setSubAccountId(33381251220301L);
          l_assetParams.setTaxType(TaxTypeEnum.NORMAL);
          l_assetParams.setQuantityForBookValue(10000);
          l_assetParams.setBookValue(0);
          TestDBUtility.insertWithDel(l_assetParams);
          
          FeqFinTransactionParams l_feqFinTransactionParams1 = TestDBUtility.getFeqFinTransactionParams();
          l_feqFinTransactionParams1.setFinTransactionId(1L);
          l_feqFinTransactionParams1.setOrderUnitId(1L);
          l_feqFinTransactionParams1.setOrderId(111111L);
          l_feqFinTransactionParams1.setAccountId(31102102050L);
          l_feqFinTransactionParams1.setSubAccountId(33381251220301L);
          l_feqFinTransactionParams1.setCurrencyCode("A1");
          l_feqFinTransactionParams1.setFinTransactionType(FinTransactionType.EQTYPE_FEQ_BUY);
          l_feqFinTransactionParams1.setBalanceAmountFc(125);
          l_feqFinTransactionParams1.setOrderExecutionId(1);
          l_feqFinTransactionParams1.setProductId(111111);
          l_feqFinTransactionParams1.setMarketId(33);
          l_feqFinTransactionParams1.setPrice(10);
          l_feqFinTransactionParams1.setRegNo("1");
          l_feqFinTransactionParams1.setAssetId(1001);
          
          TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
          InstitutionPreferencesParams l_InstitutionPreferencesParams  = TestDBUtility.getInstitutionPreferencesRow();
          l_InstitutionPreferencesParams.setInstitutionId(33);
          l_InstitutionPreferencesParams.setName("feq.day.trade.div");
          l_InstitutionPreferencesParams.setNameSerialNo(1);
          TestDBUtility.insertWithDel(l_InstitutionPreferencesParams);
          
          TestDBUtility.deleteAll(MarketPreferencesParams.TYPE);
          MarketPreferencesParams l_MarketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
          l_MarketPreferencesParams.setMarketId(33);
          l_MarketPreferencesParams.setName("feq.day.trade.market.div");
          l_MarketPreferencesParams.setNameSerialNo(1);
          l_MarketPreferencesParams.setValue("1");
          TestDBUtility.insertWithDel(l_MarketPreferencesParams);
          
          WEB3FeqPositionManagerHelper l_helper = new WEB3FeqPositionManagerHelper(ProductTypeEnum.FOREIGN_EQUITY);
          l_helper.reverseAssetPositionByTrans(l_feqFinTransactionParams1, SideEnum.BUY);
          
      }
      catch (Exception l_ex)
      {
          log.debug(l_ex.getMessage(), l_ex);
          fail();   
      }
  }
  
  public void testReverseAssetPositionByTransCase5()
  {
      final String STR_METHOD_NAME = "testReverseAssetPositionByTransCase5()";
      log.entering(STR_METHOD_NAME);
      
      try
      {
          TestDBUtility.deleteAll(InstitutionParams.TYPE);
          InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
          l_institutionParams.setInstitutionCode("0D");
          TestDBUtility.insertWithDel(l_institutionParams);
          
          TestDBUtility.deleteAll(MainAccountRow.TYPE);
          MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
          l_mainAccountParams.setAccountId(31102102050L);
          TestDBUtility.insertWithDel(l_mainAccountParams);
          
          MarketParams l_marketParams1 = TestDBUtility.getMarketRow();
          l_marketParams1.setMarketId(33);
          l_marketParams1.setMarketCode("33");
          l_marketParams1.setInstitutionCode("0D");
          TestDBUtility.insertWithDel(l_marketParams1);
          
          TestDBUtility.deleteAll(AssetParams.TYPE);
          AssetParams l_assetParams = TestDBUtility.getAssetRow();
          l_assetParams.setAssetId(1001);
          l_assetParams.setAccountId(31102102050L);
          l_assetParams.setSubAccountId(33381251220301L);
          l_assetParams.setTaxType(TaxTypeEnum.NORMAL);
          l_assetParams.setQuantityForBookValue(10000);
          l_assetParams.setBookValue(0);
          TestDBUtility.insertWithDel(l_assetParams);
          
          FeqFinTransactionParams l_feqFinTransactionParams1 = TestDBUtility.getFeqFinTransactionParams();
          l_feqFinTransactionParams1.setFinTransactionId(1L);
          l_feqFinTransactionParams1.setOrderUnitId(1L);
          l_feqFinTransactionParams1.setOrderId(111111L);
          l_feqFinTransactionParams1.setAccountId(31102102050L);
          l_feqFinTransactionParams1.setSubAccountId(33381251220301L);
          l_feqFinTransactionParams1.setCurrencyCode("A1");
          l_feqFinTransactionParams1.setFinTransactionType(FinTransactionType.EQTYPE_FEQ_SELL);
          l_feqFinTransactionParams1.setBalanceAmountFc(125);
          l_feqFinTransactionParams1.setOrderExecutionId(1);
          l_feqFinTransactionParams1.setProductId(111111);
          l_feqFinTransactionParams1.setMarketId(33);
          l_feqFinTransactionParams1.setPrice(10);
          l_feqFinTransactionParams1.setRegNo("1");
          l_feqFinTransactionParams1.setAssetId(1001);
          
          TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
          InstitutionPreferencesParams l_InstitutionPreferencesParams  = TestDBUtility.getInstitutionPreferencesRow();
          l_InstitutionPreferencesParams.setInstitutionId(33);
          l_InstitutionPreferencesParams.setName("feq.day.trade.div");
          l_InstitutionPreferencesParams.setNameSerialNo(1);
          TestDBUtility.insertWithDel(l_InstitutionPreferencesParams);
          
          TestDBUtility.deleteAll(MarketPreferencesParams.TYPE);
          MarketPreferencesParams l_MarketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
          l_MarketPreferencesParams.setMarketId(33);
          l_MarketPreferencesParams.setName("feq.day.trade.market.div");
          l_MarketPreferencesParams.setNameSerialNo(1);
          l_MarketPreferencesParams.setValue("1");
          TestDBUtility.insertWithDel(l_MarketPreferencesParams);
          
          WEB3FeqPositionManagerHelper l_helper = new WEB3FeqPositionManagerHelper(ProductTypeEnum.FOREIGN_EQUITY);
          l_helper.reverseAssetPositionByTrans(l_feqFinTransactionParams1, SideEnum.SELL);
          
      }
      catch (Exception l_ex)
      {
          log.debug(l_ex.getMessage(), l_ex);
          fail();   
      }
  }
  
  public void testReverseAssetPositionByTransCase6()
  {
      final String STR_METHOD_NAME = "testReverseAssetPositionByTransCase6()";
      log.entering(STR_METHOD_NAME);
      
      try
      {
          TestDBUtility.deleteAll(InstitutionParams.TYPE);
          InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
          l_institutionParams.setInstitutionCode("0D");
          TestDBUtility.insertWithDel(l_institutionParams);
          
          TestDBUtility.deleteAll(MainAccountRow.TYPE);
          MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
          l_mainAccountParams.setAccountId(31102102050L);
          TestDBUtility.insertWithDel(l_mainAccountParams);
          
          MarketParams l_marketParams1 = TestDBUtility.getMarketRow();
          l_marketParams1.setMarketId(33);
          l_marketParams1.setMarketCode("33");
          l_marketParams1.setInstitutionCode("0D");
          TestDBUtility.insertWithDel(l_marketParams1);
          
          TestDBUtility.deleteAll(AssetParams.TYPE);
          AssetParams l_assetParams = TestDBUtility.getAssetRow();
          l_assetParams.setAssetId(1001);
          l_assetParams.setAccountId(31102102050L);
          l_assetParams.setSubAccountId(33381251220301L);
          l_assetParams.setTaxType(TaxTypeEnum.NORMAL);
          l_assetParams.setQuantityForBookValue(10000);
          l_assetParams.setBookValue(0);
          TestDBUtility.insertWithDel(l_assetParams);
          
          FeqFinTransactionParams l_feqFinTransactionParams1 = TestDBUtility.getFeqFinTransactionParams();
          l_feqFinTransactionParams1.setFinTransactionId(1L);
          l_feqFinTransactionParams1.setOrderUnitId(1L);
          l_feqFinTransactionParams1.setOrderId(111111L);
          l_feqFinTransactionParams1.setAccountId(31102102050L);
          l_feqFinTransactionParams1.setSubAccountId(33381251220301L);
          l_feqFinTransactionParams1.setCurrencyCode("A1");
          l_feqFinTransactionParams1.setFinTransactionType(FinTransactionType.EQTYPE_FEQ_SELL);
          l_feqFinTransactionParams1.setBalanceAmountFc(125);
          l_feqFinTransactionParams1.setOrderExecutionId(1);
          l_feqFinTransactionParams1.setProductId(111111);
          l_feqFinTransactionParams1.setMarketId(33);
          l_feqFinTransactionParams1.setPrice(10);
          l_feqFinTransactionParams1.setRegNo("1");
          l_feqFinTransactionParams1.setAssetId(1001);
          
          TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
//          InstitutionPreferencesParams l_InstitutionPreferencesParams  = TestDBUtility.getInstitutionPreferencesRow();
//          l_InstitutionPreferencesParams.setInstitutionId(33);
//          l_InstitutionPreferencesParams.setName("feq.day.trade.div");
//          l_InstitutionPreferencesParams.setNameSerialNo(1);
//          TestDBUtility.insertWithDel(l_InstitutionPreferencesParams);
          
          TestDBUtility.deleteAll(MarketPreferencesParams.TYPE);
          MarketPreferencesParams l_MarketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
          l_MarketPreferencesParams.setMarketId(33);
          l_MarketPreferencesParams.setName("feq.day.trade.market.div");
          l_MarketPreferencesParams.setNameSerialNo(1);
          l_MarketPreferencesParams.setValue("1");
          TestDBUtility.insertWithDel(l_MarketPreferencesParams);
          
          WEB3FeqPositionManagerHelper l_helper = new WEB3FeqPositionManagerHelper(ProductTypeEnum.FOREIGN_EQUITY);
          l_helper.reverseAssetPositionByTrans(l_feqFinTransactionParams1, SideEnum.SELL);
          
      }
      catch (Exception l_ex)
      {
          log.debug(l_ex.getMessage(), l_ex);
          fail();   
      }
  }
  
  public void testReverseAssetPositionByTransCase7()
  {
      final String STR_METHOD_NAME = "testReverseAssetPositionByTransCase7()";
      log.entering(STR_METHOD_NAME);
      
      try
      {
          TestDBUtility.deleteAll(InstitutionParams.TYPE);
          InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
          l_institutionParams.setInstitutionCode("0D");
          TestDBUtility.insertWithDel(l_institutionParams);
          
          TestDBUtility.deleteAll(MainAccountRow.TYPE);
          MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
          l_mainAccountParams.setAccountId(31102102050L);
          TestDBUtility.insertWithDel(l_mainAccountParams);
          
          MarketParams l_marketParams1 = TestDBUtility.getMarketRow();
          l_marketParams1.setMarketId(33);
          l_marketParams1.setMarketCode("33");
          l_marketParams1.setInstitutionCode("0D");
          TestDBUtility.insertWithDel(l_marketParams1);
          
          TestDBUtility.deleteAll(AssetParams.TYPE);
          AssetParams l_assetParams = TestDBUtility.getAssetRow();
          l_assetParams.setAssetId(1001);
          l_assetParams.setAccountId(31102102050L);
          l_assetParams.setSubAccountId(33381251220301L);
          l_assetParams.setTaxType(TaxTypeEnum.NORMAL);
          l_assetParams.setQuantityForBookValue(10000);
          l_assetParams.setBookValue(0);
          TestDBUtility.insertWithDel(l_assetParams);
          
          FeqFinTransactionParams l_feqFinTransactionParams1 = TestDBUtility.getFeqFinTransactionParams();
          l_feqFinTransactionParams1.setFinTransactionId(1L);
          l_feqFinTransactionParams1.setOrderUnitId(1L);
          l_feqFinTransactionParams1.setOrderId(111111L);
          l_feqFinTransactionParams1.setAccountId(31102102050L);
          l_feqFinTransactionParams1.setSubAccountId(33381251220301L);
          l_feqFinTransactionParams1.setCurrencyCode("A1");
          l_feqFinTransactionParams1.setFinTransactionType(FinTransactionType.EQTYPE_FEQ_SELL);
          l_feqFinTransactionParams1.setBalanceAmountFc(125);
          l_feqFinTransactionParams1.setOrderExecutionId(1);
          l_feqFinTransactionParams1.setProductId(111111);
          l_feqFinTransactionParams1.setMarketId(33);
          l_feqFinTransactionParams1.setPrice(10);
          l_feqFinTransactionParams1.setRegNo("1");
          l_feqFinTransactionParams1.setAssetId(1001);
          
          TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
          InstitutionPreferencesParams l_InstitutionPreferencesParams  = TestDBUtility.getInstitutionPreferencesRow();
          l_InstitutionPreferencesParams.setInstitutionId(33);
          l_InstitutionPreferencesParams.setName("feq.day.trade.div");
          l_InstitutionPreferencesParams.setNameSerialNo(1);
          TestDBUtility.insertWithDel(l_InstitutionPreferencesParams);
          
          TestDBUtility.deleteAll(MarketPreferencesParams.TYPE);
//          MarketPreferencesParams l_MarketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
//          l_MarketPreferencesParams.setMarketId(33);
//          l_MarketPreferencesParams.setName("feq.day.trade.market.div");
//          l_MarketPreferencesParams.setNameSerialNo(1);
//          l_MarketPreferencesParams.setValue("1");
//          TestDBUtility.insertWithDel(l_MarketPreferencesParams);
          
          WEB3FeqPositionManagerHelper l_helper = new WEB3FeqPositionManagerHelper(ProductTypeEnum.FOREIGN_EQUITY);
          l_helper.reverseAssetPositionByTrans(l_feqFinTransactionParams1, SideEnum.SELL);
          
      }
      catch (Exception l_ex)
      {
          log.debug(l_ex.getMessage(), l_ex);
          fail();   
      }
  }
  
  public void testReverseAssetPositionByTransCase8()
  {
      final String STR_METHOD_NAME = "testReverseAssetPositionByTransCase8()";
      log.entering(STR_METHOD_NAME);
      
      try
      {
          TestDBUtility.deleteAll(InstitutionParams.TYPE);
          InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
          l_institutionParams.setInstitutionCode("0D");
          TestDBUtility.insertWithDel(l_institutionParams);
          
          TestDBUtility.deleteAll(MainAccountRow.TYPE);
          MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
          l_mainAccountParams.setAccountId(31102102050L);
          TestDBUtility.insertWithDel(l_mainAccountParams);
          
          MarketParams l_marketParams1 = TestDBUtility.getMarketRow();
          l_marketParams1.setMarketId(33);
          l_marketParams1.setMarketCode("33");
          l_marketParams1.setInstitutionCode("0D");
          TestDBUtility.insertWithDel(l_marketParams1);
          
          TestDBUtility.deleteAll(AssetParams.TYPE);
          AssetParams l_assetParams = TestDBUtility.getAssetRow();
          l_assetParams.setAssetId(1001);
          l_assetParams.setAccountId(31102102050L);
          l_assetParams.setSubAccountId(33381251220301L);
          l_assetParams.setTaxType(TaxTypeEnum.NORMAL);
          l_assetParams.setQuantityForBookValue(10000);
          l_assetParams.setBookValue(0);
          TestDBUtility.insertWithDel(l_assetParams);
          
          FeqFinTransactionParams l_feqFinTransactionParams1 = TestDBUtility.getFeqFinTransactionParams();
          l_feqFinTransactionParams1.setFinTransactionId(1L);
          l_feqFinTransactionParams1.setOrderUnitId(1L);
          l_feqFinTransactionParams1.setOrderId(111111L);
          l_feqFinTransactionParams1.setAccountId(31102102050L);
          l_feqFinTransactionParams1.setSubAccountId(33381251220301L);
          l_feqFinTransactionParams1.setCurrencyCode("A1");
          l_feqFinTransactionParams1.setFinTransactionType(FinTransactionType.EQTYPE_FEQ_SELL);
          l_feqFinTransactionParams1.setBalanceAmountFc(125);
          l_feqFinTransactionParams1.setOrderExecutionId(1);
          l_feqFinTransactionParams1.setProductId(111111);
          l_feqFinTransactionParams1.setMarketId(33);
          l_feqFinTransactionParams1.setPrice(10);
          l_feqFinTransactionParams1.setRegNo("1");
          l_feqFinTransactionParams1.setAssetId(1001);
          
          TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
//          InstitutionPreferencesParams l_InstitutionPreferencesParams  = TestDBUtility.getInstitutionPreferencesRow();
//          l_InstitutionPreferencesParams.setInstitutionId(33);
//          l_InstitutionPreferencesParams.setName("feq.day.trade.div");
//          l_InstitutionPreferencesParams.setNameSerialNo(1);
//          TestDBUtility.insertWithDel(l_InstitutionPreferencesParams);
          
          TestDBUtility.deleteAll(MarketPreferencesParams.TYPE);
//          MarketPreferencesParams l_MarketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
//          l_MarketPreferencesParams.setMarketId(33);
//          l_MarketPreferencesParams.setName("feq.day.trade.market.div");
//          l_MarketPreferencesParams.setNameSerialNo(1);
//          l_MarketPreferencesParams.setValue("1");
//          TestDBUtility.insertWithDel(l_MarketPreferencesParams);

          WEB3FeqPositionManagerHelper l_helper = new WEB3FeqPositionManagerHelper(ProductTypeEnum.FOREIGN_EQUITY);
          l_helper.reverseAssetPositionByTrans(l_feqFinTransactionParams1, SideEnum.SELL);
          
      }
      catch (Exception l_ex)
      {
          log.debug(l_ex.getMessage(), l_ex);
          fail();   
      }
  }
  
  //(applyTo保有資産残高)
  public void testApplyToAssetPositionCase1()
  {
      final String STR_METHOD_NAME = "testApplyToAssetPositionCase1()";
      log.entering(STR_METHOD_NAME);
      
      try
      {
          TestDBUtility.deleteAll(InstitutionParams.TYPE);
          InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
          l_institutionParams.setInstitutionCode("0D");
          TestDBUtility.insertWithDel(l_institutionParams);
          
          TestDBUtility.deleteAll(MainAccountRow.TYPE);
          MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
          l_mainAccountParams.setAccountId(31102102050L);
          TestDBUtility.insertWithDel(l_mainAccountParams);
          
          MarketParams l_marketParams1 = TestDBUtility.getMarketRow();
          l_marketParams1.setMarketId(33);
          l_marketParams1.setMarketCode("33");
          l_marketParams1.setInstitutionCode("0D");
          TestDBUtility.insertWithDel(l_marketParams1);
          
          TestDBUtility.deleteAll(AssetParams.TYPE);
          AssetParams l_assetParams = TestDBUtility.getAssetRow();
          l_assetParams.setAssetId(1001);
          l_assetParams.setAccountId(31102102050L);
          l_assetParams.setSubAccountId(33381251220301L);
          l_assetParams.setTaxType(TaxTypeEnum.NORMAL);
          l_assetParams.setQuantityForBookValue(10000);
          l_assetParams.setBookValue(0);
          TestDBUtility.insertWithDel(l_assetParams);
          
          FeqFinTransactionParams l_feqFinTransactionParams1 = TestDBUtility.getFeqFinTransactionParams();
          l_feqFinTransactionParams1.setFinTransactionId(1L);
          l_feqFinTransactionParams1.setOrderUnitId(1L);
          l_feqFinTransactionParams1.setOrderId(111111L);
          l_feqFinTransactionParams1.setAccountId(31102102050L);
          l_feqFinTransactionParams1.setSubAccountId(33381251220301L);
          l_feqFinTransactionParams1.setCurrencyCode("A1");
          l_feqFinTransactionParams1.setFinTransactionType(FinTransactionType.EQTYPE_FEQ_SELL);
          l_feqFinTransactionParams1.setBalanceAmountFc(125);
          l_feqFinTransactionParams1.setOrderExecutionId(1);
          l_feqFinTransactionParams1.setProductId(111111);
          l_feqFinTransactionParams1.setMarketId(33);
          l_feqFinTransactionParams1.setPrice(10);
          l_feqFinTransactionParams1.setRegNo("1");
          l_feqFinTransactionParams1.setAssetId(1001);
          
          TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
          InstitutionPreferencesParams l_InstitutionPreferencesParams  = TestDBUtility.getInstitutionPreferencesRow();
          l_InstitutionPreferencesParams.setInstitutionId(33);
          l_InstitutionPreferencesParams.setName("feq.day.trade.div");
          l_InstitutionPreferencesParams.setNameSerialNo(1);
          TestDBUtility.insertWithDel(l_InstitutionPreferencesParams);
          
          TestDBUtility.deleteAll(MarketPreferencesParams.TYPE);
//          MarketPreferencesParams l_MarketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
//          l_MarketPreferencesParams.setMarketId(33);
//          l_MarketPreferencesParams.setName("feq.day.trade.market.div");
//          l_MarketPreferencesParams.setNameSerialNo(1);
//          l_MarketPreferencesParams.setValue("1");
//          TestDBUtility.insertWithDel(l_MarketPreferencesParams);
          
          WEB3FeqPositionManagerHelper l_helper = new WEB3FeqPositionManagerHelper(ProductTypeEnum.FOREIGN_EQUITY);
          l_helper.reverseAssetPositionByTrans(l_feqFinTransactionParams1, SideEnum.SELL);
          
      }
      catch (Exception l_ex)
      {
          log.debug(l_ex.getMessage(), l_ex);
          fail();   
      }
  }
  
  /**
   * 買付（トランザクション（取引勘定明細）行.ﾄﾗﾝｻﾞｸｼｮﾝﾀｲﾌﾟ==”外株買い”）の場合
   * isネッティング処理==true
   * 
   * 保有資産更新（簿価更新用）する--->保有資産に該当するデータがない、例外をスローする。--->cause wrong (l_fransactionParams[j].getAssetId())
   */
  public void testUpdateTransaction_case001()
  {
      final String STR_METHOD_NAME = "testUpdateTransaction_case001()";
      log.entering(STR_METHOD_NAME);
      
      try
      {
          // トランザクション（取引勘定明細）テーブル
          TestDBUtility.deleteAllAndCommit(FeqFinTransactionParams.TYPE);
          //
          FeqFinTransactionParams l_feqFinTransactionParam1 = TestDBUtility.getFeqFinTransactionParams();
          l_feqFinTransactionParam1.setFinTransactionId(123456789);
          l_feqFinTransactionParam1.setOrderUnitId(1234L);
          l_feqFinTransactionParam1.setDeleteFlag(BooleanEnum.FALSE);
          l_feqFinTransactionParam1.setFinTransactionType(FinTransactionType.EQTYPE_FEQ_BUY);
          l_feqFinTransactionParam1.setAssetId(123456789L);
          l_feqFinTransactionParam1.setMarketId(3303L);
          TestDBUtility.insertWithDelAndCommit(l_feqFinTransactionParam1);
          //
          FeqFinTransactionParams l_feqFinTransactionParam2 = TestDBUtility.getFeqFinTransactionParams();
          l_feqFinTransactionParam2.setFinTransactionId(123456780);
          l_feqFinTransactionParam2.setOrderUnitId(1235L);
          l_feqFinTransactionParam2.setDeleteFlag(BooleanEnum.FALSE);   
          l_feqFinTransactionParam2.setFinTransactionType(FinTransactionType.EQTYPE_FEQ_BUY);
          l_feqFinTransactionParam2.setAssetId(123456789L);
          l_feqFinTransactionParam2.setMarketId(3303L);
          TestDBUtility.insertWithDelAndCommit(l_feqFinTransactionParam2);
          //
          FeqFinTransactionParams l_feqFinTransactionParam3 = TestDBUtility.getFeqFinTransactionParams();
          l_feqFinTransactionParam3.setFinTransactionId(123456788);
          l_feqFinTransactionParam3.setOrderUnitId(1235L);
          l_feqFinTransactionParam3.setDeleteFlag(BooleanEnum.FALSE);
          l_feqFinTransactionParam3.setProductId(556789132);//
          l_feqFinTransactionParam3.setAssetId(123456788L);
          TestDBUtility.insertWithDelAndCommit(l_feqFinTransactionParam3);
          
          // 補助口座
          TestDBUtility.deleteAllAndCommit(SubAccountParams.TYPE);
          SubAccountParams l_subAccountParam = TestDBUtility.getSubAccountRow();
          l_subAccountParam.setAccountId(31102102050L);
          l_subAccountParam.setSubAccountId(33381251220301L);
          l_subAccountParam.setInstitutionId(33);
          TestDBUtility.insertWithDelAndCommit(l_subAccountParam);
          
          // 保有資産テーブル
          TestDBUtility.deleteAllAndCommit(AssetParams.TYPE);
          AssetParams l_assetParams = TestDBUtility.getAssetRow();
          l_assetParams.setAssetId(1001);//
          TestDBUtility.insertWithDelAndCommit(l_assetParams);
          
          // 市場テーブル
          TestDBUtility.deleteAllAndCommit(MarketParams.TYPE);
          MarketParams l_marketParams = TestDBUtility.getMarketRow();
          l_marketParams.setMarketId(3303L);
          TestDBUtility.insertWithDelAndCommit(l_marketParams);
          
          // 市場用プリファ@レンステーブル
          TestDBUtility.deleteAllAndCommit(MarketPreferencesParams.TYPE);
          MarketPreferencesParams l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
          l_marketPreferencesParams.setMarketId(3304L);
          l_marketPreferencesParams.setName(WEB3MarketPreferencesNameDef.FEQ_DAY_TRADE_MARKET_DIV);
          l_marketPreferencesParams.setNameSerialNo(1);
          l_marketPreferencesParams.setValue("1");
          TestDBUtility.insertWithDelAndCommit(l_marketPreferencesParams);
          
          // 証券会社テーブル
          TestDBUtility.deleteAllAndCommit(InstitutionParams.TYPE);
          InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
          l_institutionParams.setInstitutionId(33);
          TestDBUtility.insertWithDelAndCommit(l_institutionParams);
          
          // 証券会社プリファ@レンステーブル
          TestDBUtility.deleteAllAndCommit(InstitutionPreferencesParams.TYPE);
          InstitutionPreferencesParams l_institutionPreferencesParams = TestDBUtility.getInstitutionPreferencesRow();
          l_institutionPreferencesParams.setInstitutionId(34);
          l_institutionPreferencesParams.setName(WEB3InstitutionPreferencesNameDef.FEQ_DAY_TRADE_DIV);
          l_institutionPreferencesParams.setNameSerialNo(1);
          l_institutionPreferencesParams.setValue("1");
          TestDBUtility.insertWithDelAndCommit(l_institutionPreferencesParams);
          
          
          //
          this.updateTransaction(new long[]{1234L,1235L}, GtlUtils.getSystemTimestamp(), true);
      }
      catch (WEB3SystemLayerException l_ex)
      {
          assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00204, l_ex.getErrorInfo());
      }
      catch (Exception l_ex)
      {
          log.error(l_ex.getMessage(), l_ex);
          fail();
      }
      finally
      {
          try
          {
              TestDBUtility.deleteAll(FeqFinTransactionParams.TYPE);
              TestDBUtility.deleteAll(FeqOrderActionRow.TYPE);
              TestDBUtility.deleteAll(AssetParams.TYPE);
          }
          catch (Exception l_ex)
          {
              log.error(l_ex.getMessage(), l_ex);
          }
          finally
          {
              log.exiting(STR_METHOD_NAME);
          }
      }
  }
  
  
  /**
   * 買付（トランザクション（取引勘定明細）行.ﾄﾗﾝｻﾞｸｼｮﾝﾀｲﾌﾟ==”外株買い”）の場合
   *    isネッティング処理==false
   *    is日計り取引採用()==true
   *    is日計り市場()==false
   * 保有資産更新（簿価更新用）しない
   */ 
  public void testUpdateTransaction_case002()
  {
      final String STR_METHOD_NAME = "testUpdateTransaction_case002()";
      log.entering(STR_METHOD_NAME);
      
      try
      {
          // トランザクション（取引勘定明細）テーブル
          TestDBUtility.deleteAllAndCommit(FeqFinTransactionParams.TYPE);
          //
          FeqFinTransactionParams l_feqFinTransactionParam1 = TestDBUtility.getFeqFinTransactionParams();
          l_feqFinTransactionParam1.setFinTransactionId(123456789);
          l_feqFinTransactionParam1.setOrderUnitId(1234L);
          l_feqFinTransactionParam1.setDeleteFlag(BooleanEnum.FALSE);
          l_feqFinTransactionParam1.setFinTransactionType(FinTransactionType.EQTYPE_FEQ_BUY);
          l_feqFinTransactionParam1.setAssetId(123456789L);
          l_feqFinTransactionParam1.setMarketId(3303L);
          TestDBUtility.insertWithDelAndCommit(l_feqFinTransactionParam1);
          //
          FeqFinTransactionParams l_feqFinTransactionParam2 = TestDBUtility.getFeqFinTransactionParams();
          l_feqFinTransactionParam2.setFinTransactionId(123456780);
          l_feqFinTransactionParam2.setOrderUnitId(1235L);
          l_feqFinTransactionParam2.setDeleteFlag(BooleanEnum.FALSE);   
          l_feqFinTransactionParam2.setFinTransactionType(FinTransactionType.EQTYPE_FEQ_BUY);
          l_feqFinTransactionParam2.setAssetId(123456789L);
          l_feqFinTransactionParam2.setMarketId(3303L);
          TestDBUtility.insertWithDelAndCommit(l_feqFinTransactionParam2);
          //
          FeqFinTransactionParams l_feqFinTransactionParam3 = TestDBUtility.getFeqFinTransactionParams();
          l_feqFinTransactionParam3.setFinTransactionId(123456788);
          l_feqFinTransactionParam3.setOrderUnitId(1235L);
          l_feqFinTransactionParam3.setDeleteFlag(BooleanEnum.FALSE);
          l_feqFinTransactionParam3.setProductId(556789132);//
          l_feqFinTransactionParam3.setAssetId(123456788L);
          TestDBUtility.insertWithDelAndCommit(l_feqFinTransactionParam3);
          
          // 補助口座
          TestDBUtility.deleteAllAndCommit(SubAccountParams.TYPE);
          SubAccountParams l_subAccountParam = TestDBUtility.getSubAccountRow();
          l_subAccountParam.setAccountId(31102102050L);
          l_subAccountParam.setSubAccountId(33381251220301L);
          l_subAccountParam.setInstitutionId(33);
          TestDBUtility.insertWithDelAndCommit(l_subAccountParam);
          
          // 保有資産テーブル
          TestDBUtility.deleteAllAndCommit(AssetParams.TYPE);
          AssetParams l_assetParams = TestDBUtility.getAssetRow();
          l_assetParams.setAssetId(1001);//
          TestDBUtility.insertWithDelAndCommit(l_assetParams);
          
          // 市場テーブル
          TestDBUtility.deleteAllAndCommit(MarketParams.TYPE);
          MarketParams l_marketParams = TestDBUtility.getMarketRow();
          l_marketParams.setMarketId(3303L);
          TestDBUtility.insertWithDelAndCommit(l_marketParams);
          
          // 市場用プリファ@レンステーブル
          TestDBUtility.deleteAllAndCommit(MarketPreferencesParams.TYPE);
          MarketPreferencesParams l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
          l_marketPreferencesParams.setMarketId(3304L);
          l_marketPreferencesParams.setName(WEB3MarketPreferencesNameDef.FEQ_DAY_TRADE_MARKET_DIV);
          l_marketPreferencesParams.setNameSerialNo(1);
          l_marketPreferencesParams.setValue("1");
          TestDBUtility.insertWithDelAndCommit(l_marketPreferencesParams);
          
          // 証券会社テーブル
          TestDBUtility.deleteAllAndCommit(InstitutionParams.TYPE);
          InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
          l_institutionParams.setInstitutionId(33);
          TestDBUtility.insertWithDelAndCommit(l_institutionParams);
          
          // 証券会社プリファ@レンステーブル
          TestDBUtility.deleteAllAndCommit(InstitutionPreferencesParams.TYPE);
          InstitutionPreferencesParams l_institutionPreferencesParams = TestDBUtility.getInstitutionPreferencesRow();
          l_institutionPreferencesParams.setInstitutionId(33);
          l_institutionPreferencesParams.setName(WEB3InstitutionPreferencesNameDef.FEQ_DAY_TRADE_DIV);
          l_institutionPreferencesParams.setNameSerialNo(1);
          l_institutionPreferencesParams.setValue("1");
          TestDBUtility.insertWithDelAndCommit(l_institutionPreferencesParams);
          
          
          //
          this.updateTransaction(new long[]{1234L,1235L}, GtlUtils.getSystemTimestamp(), false);
      }
      catch (WEB3SystemLayerException l_ex)
      {
          assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
      }
      catch (Exception l_ex)
      {
          log.error(l_ex.getMessage(), l_ex);
          fail();
      }
      finally
      {
          try
          {
              TestDBUtility.deleteAll(FeqFinTransactionParams.TYPE);
              TestDBUtility.deleteAll(FeqOrderActionRow.TYPE);
              TestDBUtility.deleteAll(AssetParams.TYPE);
          }
          catch (Exception l_ex)
          {
              log.error(l_ex.getMessage(), l_ex);
          }
          finally
          {
              log.exiting(STR_METHOD_NAME);
          }
      }
  }
  
  /**
   * 買付（トランザクション（取引勘定明細）行.ﾄﾗﾝｻﾞｸｼｮﾝﾀｲﾌﾟ==”外株買い”）の場合
   *    isネッティング処理==false
   *    is日計り取引採用()==false
   *    is日計り市場()==true
   * 保有資産更新（簿価更新用）しない
   */ 
  public void testUpdateTransaction_case003()
  {
      final String STR_METHOD_NAME = "testUpdateTransaction_case003()";
      log.entering(STR_METHOD_NAME);
      
      try
      {
          // トランザクション（取引勘定明細）テーブル
          TestDBUtility.deleteAllAndCommit(FeqFinTransactionParams.TYPE);
          //
          FeqFinTransactionParams l_feqFinTransactionParam1 = TestDBUtility.getFeqFinTransactionParams();
          l_feqFinTransactionParam1.setFinTransactionId(123456789);
          l_feqFinTransactionParam1.setOrderUnitId(1234L);
          l_feqFinTransactionParam1.setDeleteFlag(BooleanEnum.FALSE);
          l_feqFinTransactionParam1.setFinTransactionType(FinTransactionType.EQTYPE_FEQ_BUY);
          l_feqFinTransactionParam1.setAssetId(123456789L);
          l_feqFinTransactionParam1.setMarketId(3303L);
          TestDBUtility.insertWithDelAndCommit(l_feqFinTransactionParam1);
          //
          FeqFinTransactionParams l_feqFinTransactionParam2 = TestDBUtility.getFeqFinTransactionParams();
          l_feqFinTransactionParam2.setFinTransactionId(123456780);
          l_feqFinTransactionParam2.setOrderUnitId(1235L);
          l_feqFinTransactionParam2.setDeleteFlag(BooleanEnum.FALSE);   
          l_feqFinTransactionParam2.setFinTransactionType(FinTransactionType.EQTYPE_FEQ_BUY);
          l_feqFinTransactionParam2.setAssetId(123456789L);
          l_feqFinTransactionParam2.setMarketId(3303L);
          TestDBUtility.insertWithDelAndCommit(l_feqFinTransactionParam2);
          //
          FeqFinTransactionParams l_feqFinTransactionParam3 = TestDBUtility.getFeqFinTransactionParams();
          l_feqFinTransactionParam3.setFinTransactionId(123456788);
          l_feqFinTransactionParam3.setOrderUnitId(1235L);
          l_feqFinTransactionParam3.setDeleteFlag(BooleanEnum.FALSE);
          l_feqFinTransactionParam3.setProductId(556789132);//
          l_feqFinTransactionParam3.setAssetId(123456788L);
          TestDBUtility.insertWithDelAndCommit(l_feqFinTransactionParam3);
          
          // 補助口座
          TestDBUtility.deleteAllAndCommit(SubAccountParams.TYPE);
          SubAccountParams l_subAccountParam = TestDBUtility.getSubAccountRow();
          l_subAccountParam.setAccountId(31102102050L);
          l_subAccountParam.setSubAccountId(33381251220301L);
          l_subAccountParam.setInstitutionId(33);
          TestDBUtility.insertWithDelAndCommit(l_subAccountParam);
          
          // 保有資産テーブル
          TestDBUtility.deleteAllAndCommit(AssetParams.TYPE);
          AssetParams l_assetParams = TestDBUtility.getAssetRow();
          l_assetParams.setAssetId(1001);//
          TestDBUtility.insertWithDelAndCommit(l_assetParams);
          
          // 市場テーブル
          TestDBUtility.deleteAllAndCommit(MarketParams.TYPE);
          MarketParams l_marketParams = TestDBUtility.getMarketRow();
          l_marketParams.setMarketId(3303L);
          TestDBUtility.insertWithDelAndCommit(l_marketParams);
          
          // 市場用プリファ@レンステーブル
          TestDBUtility.deleteAllAndCommit(MarketPreferencesParams.TYPE);
          MarketPreferencesParams l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
          l_marketPreferencesParams.setMarketId(3303L);
          l_marketPreferencesParams.setName(WEB3MarketPreferencesNameDef.FEQ_DAY_TRADE_MARKET_DIV);
          l_marketPreferencesParams.setNameSerialNo(1);
          l_marketPreferencesParams.setValue("1");
          TestDBUtility.insertWithDelAndCommit(l_marketPreferencesParams);
          
          // 証券会社テーブル
          TestDBUtility.deleteAllAndCommit(InstitutionParams.TYPE);
          InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
          l_institutionParams.setInstitutionId(33);
          TestDBUtility.insertWithDelAndCommit(l_institutionParams);
          
          // 証券会社プリファ@レンステーブル
          TestDBUtility.deleteAllAndCommit(InstitutionPreferencesParams.TYPE);
          InstitutionPreferencesParams l_institutionPreferencesParams = TestDBUtility.getInstitutionPreferencesRow();
          l_institutionPreferencesParams.setInstitutionId(34);
          l_institutionPreferencesParams.setName(WEB3InstitutionPreferencesNameDef.FEQ_DAY_TRADE_DIV);
          l_institutionPreferencesParams.setNameSerialNo(1);
          l_institutionPreferencesParams.setValue("1");
          TestDBUtility.insertWithDelAndCommit(l_institutionPreferencesParams);
          
          
          //
          this.updateTransaction(new long[]{1234L,1235L}, GtlUtils.getSystemTimestamp(), false);
      }
      catch (WEB3SystemLayerException l_ex)
      {
          assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
      }
      catch (Exception l_ex)
      {
          log.error(l_ex.getMessage(), l_ex);
          fail();
      }
      finally
      {
          try
          {
              TestDBUtility.deleteAll(FeqFinTransactionParams.TYPE);
              TestDBUtility.deleteAll(FeqOrderActionRow.TYPE);
              TestDBUtility.deleteAll(AssetParams.TYPE);
          }
          catch (Exception l_ex)
          {
              log.error(l_ex.getMessage(), l_ex);
          }
          finally
          {
              log.exiting(STR_METHOD_NAME);
          }
      }
  }
  
  /**
   * 買付（トランザクション（取引勘定明細）行.ﾄﾗﾝｻﾞｸｼｮﾝﾀｲﾌﾟ==”外株買い”）の場合
   *    isネッティング処理==false
   *    is日計り取引採用()==true
   *    is日計り市場()==true
   * 保有資産更新（簿価更新用）する
   */ 
  public void testUpdateTransaction_case004()
  {
      final String STR_METHOD_NAME = "testUpdateTransaction_case004()";
      log.entering(STR_METHOD_NAME);
      
      try
      {
          // トランザクション（取引勘定明細）テーブル
          TestDBUtility.deleteAllAndCommit(FeqFinTransactionParams.TYPE);
          //
          FeqFinTransactionParams l_feqFinTransactionParam1 = TestDBUtility.getFeqFinTransactionParams();
          l_feqFinTransactionParam1.setFinTransactionId(123456789);
          l_feqFinTransactionParam1.setOrderUnitId(1234L);
          l_feqFinTransactionParam1.setDeleteFlag(BooleanEnum.FALSE);
          l_feqFinTransactionParam1.setFinTransactionType(FinTransactionType.EQTYPE_FEQ_BUY);
          l_feqFinTransactionParam1.setAssetId(123456789L);
          l_feqFinTransactionParam1.setMarketId(3303L);
          TestDBUtility.insertWithDelAndCommit(l_feqFinTransactionParam1);
          //
          FeqFinTransactionParams l_feqFinTransactionParam2 = TestDBUtility.getFeqFinTransactionParams();
          l_feqFinTransactionParam2.setFinTransactionId(123456780);
          l_feqFinTransactionParam2.setOrderUnitId(1235L);
          l_feqFinTransactionParam2.setDeleteFlag(BooleanEnum.FALSE);   
          l_feqFinTransactionParam2.setFinTransactionType(FinTransactionType.EQTYPE_FEQ_BUY);
          l_feqFinTransactionParam2.setAssetId(123456789L);
          l_feqFinTransactionParam2.setMarketId(3303L);
          TestDBUtility.insertWithDelAndCommit(l_feqFinTransactionParam2);
          //
          FeqFinTransactionParams l_feqFinTransactionParam3 = TestDBUtility.getFeqFinTransactionParams();
          l_feqFinTransactionParam3.setFinTransactionId(123456788);
          l_feqFinTransactionParam3.setOrderUnitId(1235L);
          l_feqFinTransactionParam3.setDeleteFlag(BooleanEnum.FALSE);
          l_feqFinTransactionParam3.setProductId(556789132);//
          l_feqFinTransactionParam3.setAssetId(123456788L);
          TestDBUtility.insertWithDelAndCommit(l_feqFinTransactionParam3);
          
          // 補助口座
          TestDBUtility.deleteAllAndCommit(SubAccountParams.TYPE);
          SubAccountParams l_subAccountParam = TestDBUtility.getSubAccountRow();
          l_subAccountParam.setAccountId(31102102050L);
          l_subAccountParam.setSubAccountId(33381251220301L);
          l_subAccountParam.setInstitutionId(33);
          TestDBUtility.insertWithDelAndCommit(l_subAccountParam);
          
          // 保有資産テーブル
          TestDBUtility.deleteAllAndCommit(AssetParams.TYPE);
          AssetParams l_assetParams = TestDBUtility.getAssetRow();
          l_assetParams.setAssetId(1001);//
          TestDBUtility.insertWithDelAndCommit(l_assetParams);
          
          // 市場テーブル
          TestDBUtility.deleteAllAndCommit(MarketParams.TYPE);
          MarketParams l_marketParams = TestDBUtility.getMarketRow();
          l_marketParams.setMarketId(3303L);
          TestDBUtility.insertWithDelAndCommit(l_marketParams);
          
          // 市場用プリファ@レンステーブル
          TestDBUtility.deleteAllAndCommit(MarketPreferencesParams.TYPE);
          MarketPreferencesParams l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
          l_marketPreferencesParams.setMarketId(3303L);
          l_marketPreferencesParams.setName(WEB3MarketPreferencesNameDef.FEQ_DAY_TRADE_MARKET_DIV);
          l_marketPreferencesParams.setNameSerialNo(1);
          l_marketPreferencesParams.setValue("1");
          TestDBUtility.insertWithDelAndCommit(l_marketPreferencesParams);
          
          // 証券会社テーブル
          TestDBUtility.deleteAllAndCommit(InstitutionParams.TYPE);
          InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
          l_institutionParams.setInstitutionId(33);
          TestDBUtility.insertWithDelAndCommit(l_institutionParams);
          
          // 証券会社プリファ@レンステーブル
          TestDBUtility.deleteAllAndCommit(InstitutionPreferencesParams.TYPE);
          InstitutionPreferencesParams l_institutionPreferencesParams = TestDBUtility.getInstitutionPreferencesRow();
          l_institutionPreferencesParams.setInstitutionId(33);
          l_institutionPreferencesParams.setName(WEB3InstitutionPreferencesNameDef.FEQ_DAY_TRADE_DIV);
          l_institutionPreferencesParams.setNameSerialNo(1);
          l_institutionPreferencesParams.setValue("1");
          TestDBUtility.insertWithDelAndCommit(l_institutionPreferencesParams);
          
          
          //
          this.updateTransaction(new long[]{1234L,1235L}, GtlUtils.getSystemTimestamp(), false);
      }
      catch (WEB3SystemLayerException l_ex)
      {
          assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00204, l_ex.getErrorInfo());
      }
      catch (Exception l_ex)
      {
          log.error(l_ex.getMessage(), l_ex);
          fail();
      }
      finally
      {
          try
          {
              TestDBUtility.deleteAll(FeqFinTransactionParams.TYPE);
              TestDBUtility.deleteAll(FeqOrderActionRow.TYPE);
              TestDBUtility.deleteAll(AssetParams.TYPE);
          }
          catch (Exception l_ex)
          {
              log.error(l_ex.getMessage(), l_ex);
          }
          finally
          {
              log.exiting(STR_METHOD_NAME);
          }
      }
  }
  
  
  /**
   * 買付（トランザクション（取引勘定明細）行.ﾄﾗﾝｻﾞｸｼｮﾝﾀｲﾌﾟ != ”外株買い”）の場合
   *    isネッティング処理==true
   * 保有資産更新（簿価更新用）しない
   */ 
  public void testUpdateTransaction_case005()
  {
      final String STR_METHOD_NAME = "testUpdateTransaction_case005()";
      log.entering(STR_METHOD_NAME);
      
      try
      {
          // トランザクション（取引勘定明細）テーブル
          TestDBUtility.deleteAllAndCommit(FeqFinTransactionParams.TYPE);
          //
          FeqFinTransactionParams l_feqFinTransactionParam1 = TestDBUtility.getFeqFinTransactionParams();
          l_feqFinTransactionParam1.setFinTransactionId(123456789);
          l_feqFinTransactionParam1.setOrderUnitId(1234L);
          l_feqFinTransactionParam1.setDeleteFlag(BooleanEnum.FALSE);
          l_feqFinTransactionParam1.setAssetId(123456789L);
          l_feqFinTransactionParam1.setMarketId(3303L);
          TestDBUtility.insertWithDelAndCommit(l_feqFinTransactionParam1);
          //
          FeqFinTransactionParams l_feqFinTransactionParam2 = TestDBUtility.getFeqFinTransactionParams();
          l_feqFinTransactionParam2.setFinTransactionId(123456780);
          l_feqFinTransactionParam2.setOrderUnitId(1235L);
          l_feqFinTransactionParam2.setDeleteFlag(BooleanEnum.FALSE);   
          l_feqFinTransactionParam2.setAssetId(123456789L);
          l_feqFinTransactionParam2.setMarketId(3303L);
          TestDBUtility.insertWithDelAndCommit(l_feqFinTransactionParam2);
          //
          FeqFinTransactionParams l_feqFinTransactionParam3 = TestDBUtility.getFeqFinTransactionParams();
          l_feqFinTransactionParam3.setFinTransactionId(123456788);
          l_feqFinTransactionParam3.setOrderUnitId(1235L);
          l_feqFinTransactionParam3.setDeleteFlag(BooleanEnum.FALSE);
          l_feqFinTransactionParam3.setProductId(556789132);//
          l_feqFinTransactionParam3.setAssetId(123456788L);
          TestDBUtility.insertWithDelAndCommit(l_feqFinTransactionParam3);
          
          // 補助口座
          TestDBUtility.deleteAllAndCommit(SubAccountParams.TYPE);
          SubAccountParams l_subAccountParam = TestDBUtility.getSubAccountRow();
          l_subAccountParam.setAccountId(31102102050L);
          l_subAccountParam.setSubAccountId(33381251220301L);
          l_subAccountParam.setInstitutionId(33);
          TestDBUtility.insertWithDelAndCommit(l_subAccountParam);
          
          // 保有資産テーブル
          TestDBUtility.deleteAllAndCommit(AssetParams.TYPE);
          AssetParams l_assetParams = TestDBUtility.getAssetRow();
          l_assetParams.setAssetId(1001);//
          TestDBUtility.insertWithDelAndCommit(l_assetParams);
          
          // 市場テーブル
          TestDBUtility.deleteAllAndCommit(MarketParams.TYPE);
          MarketParams l_marketParams = TestDBUtility.getMarketRow();
          l_marketParams.setMarketId(3303L);
          TestDBUtility.insertWithDelAndCommit(l_marketParams);
          
          // 市場用プリファ@レンステーブル
          TestDBUtility.deleteAllAndCommit(MarketPreferencesParams.TYPE);
          MarketPreferencesParams l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
          l_marketPreferencesParams.setMarketId(3304L);
          l_marketPreferencesParams.setName(WEB3MarketPreferencesNameDef.FEQ_DAY_TRADE_MARKET_DIV);
          l_marketPreferencesParams.setNameSerialNo(1);
          l_marketPreferencesParams.setValue("1");
          TestDBUtility.insertWithDelAndCommit(l_marketPreferencesParams);
          
          // 証券会社テーブル
          TestDBUtility.deleteAllAndCommit(InstitutionParams.TYPE);
          InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
          l_institutionParams.setInstitutionId(33);
          TestDBUtility.insertWithDelAndCommit(l_institutionParams);
          
          // 証券会社プリファ@レンステーブル
          TestDBUtility.deleteAllAndCommit(InstitutionPreferencesParams.TYPE);
          InstitutionPreferencesParams l_institutionPreferencesParams = TestDBUtility.getInstitutionPreferencesRow();
          l_institutionPreferencesParams.setInstitutionId(33);
          l_institutionPreferencesParams.setName(WEB3InstitutionPreferencesNameDef.FEQ_DAY_TRADE_DIV);
          l_institutionPreferencesParams.setNameSerialNo(1);
          l_institutionPreferencesParams.setValue("1");
          TestDBUtility.insertWithDelAndCommit(l_institutionPreferencesParams);
          
          
          //
          this.updateTransaction(new long[]{1234L,1235L}, GtlUtils.getSystemTimestamp(), true);
      }
      catch (WEB3SystemLayerException l_ex)
      {
          assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
      }
      catch (Exception l_ex)
      {
          log.error(l_ex.getMessage(), l_ex);
          fail();
      }
      finally
      {
          try
          {
              TestDBUtility.deleteAll(FeqFinTransactionParams.TYPE);
              TestDBUtility.deleteAll(FeqOrderActionRow.TYPE);
              TestDBUtility.deleteAll(AssetParams.TYPE);
          }
          catch (Exception l_ex)
          {
              log.error(l_ex.getMessage(), l_ex);
          }
          finally
          {
              log.exiting(STR_METHOD_NAME);
          }
      }
  }
  
  /**
   * ①@買付（トランザクション（取引勘定明細）行.ﾄﾗﾝｻﾞｸｼｮﾝﾀｲﾌﾟ==”外株買い”）の場合
   * ②is信用口座開設()の戻り値 == trueの場合
   */
  public void testUpdateTransactionNettingAdoptionCase3()
  {
      final String STR_METHOD_NAME = "testUpdateTransactionNettingAdoptionCase3()";
      log.entering(STR_METHOD_NAME);
      
      try
      {
          TestDBUtility.deleteAll(AssetParams.TYPE);
          AssetParams l_assetParams = TestDBUtility.getAssetRow();
          l_assetParams.setAssetId(1001);
          l_assetParams.setAccountId(31102102050L);
          l_assetParams.setSubAccountId(33381251220301L);
          l_assetParams.setTaxType(TaxTypeEnum.NORMAL);
          l_assetParams.setQuantityForBookValue(10000);
          l_assetParams.setBookValue(0);
          TestDBUtility.insertWithDel(l_assetParams);
          TestDBUtility.deleteAll(InstitutionParams.TYPE);
          InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
          l_institutionParams.setInstitutionId(33);
          l_institutionParams.setInstitutionCode("0D");
          TestDBUtility.insertWithDel(l_institutionParams);
          InstitutionParams l_institutionParams1 = TestDBUtility.getInstitutionRow();
          l_institutionParams1.setInstitutionId(11);
          l_institutionParams1.setInstitutionCode("2D");
          TestDBUtility.insertWithDel(l_institutionParams1);
          
          TestDBUtility.deleteAll(MainAccountRow.TYPE);
          MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
          l_mainAccountParams.setAccountId(31102102050L);
          l_mainAccountParams.setMarginGenAccOpenDiv(WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN);
          TestDBUtility.insertWithDel(l_mainAccountParams);
          
          TestDBUtility.deleteAll(BranchParams.TYPE);
          BranchParams l_BranchParams = TestDBUtility.getBranchRow();
          TestDBUtility.insertWithDel(l_BranchParams);
          
          TestDBUtility.deleteAll(SubAccountRow.TYPE);
          SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
          l_subAccountParams.setAccountId(31102102050L);
          l_subAccountParams.setSubAccountId(33381251220301L);
          l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
          TestDBUtility.insertWithDel(l_subAccountParams);
          
          TestDBUtility.deleteAll(FeqFinTransactionRow.TYPE);
          FeqFinTransactionParams l_feqFinTransactionParams1 = TestDBUtility.getFeqFinTransactionParams();
          l_feqFinTransactionParams1.setFinTransactionId(1L);
          l_feqFinTransactionParams1.setOrderUnitId(1L);
          l_feqFinTransactionParams1.setOrderId(111111L);
          l_feqFinTransactionParams1.setAccountId(31102102050L);
          l_feqFinTransactionParams1.setSubAccountId(33381251220301L);
          l_feqFinTransactionParams1.setCurrencyCode("A1");
          l_feqFinTransactionParams1.setFinTransactionType(FinTransactionType.EQTYPE_FEQ_BUY);
          l_feqFinTransactionParams1.setBalanceAmountFc(125);
          l_feqFinTransactionParams1.setOrderExecutionId(1);
          l_feqFinTransactionParams1.setProductId(111111);
          l_feqFinTransactionParams1.setMarketId(33);
          l_feqFinTransactionParams1.setPrice(10.12345679);
          l_feqFinTransactionParams1.setRegNo("1");
          l_feqFinTransactionParams1.setAssetId(1001);
          TestDBUtility.insertWithDel(l_feqFinTransactionParams1);
          FeqFinTransactionParams l_feqFinTransactionParams2 = TestDBUtility.getFeqFinTransactionParams();
          l_feqFinTransactionParams2.setFinTransactionId(2L);
          l_feqFinTransactionParams2.setOrderUnitId(1L);
          l_feqFinTransactionParams2.setOrderId(111111L);
          l_feqFinTransactionParams2.setAccountId(31102102050L);
          l_feqFinTransactionParams2.setSubAccountId(33381251220301L);
          l_feqFinTransactionParams2.setCurrencyCode("A1");
          l_feqFinTransactionParams2.setFinTransactionType(FinTransactionType.EQTYPE_FEQ_SELL);
          l_feqFinTransactionParams2.setBalanceAmountFc(125);
          l_feqFinTransactionParams2.setOrderExecutionId(2);
          l_feqFinTransactionParams2.setProductId(111111);
          l_feqFinTransactionParams2.setMarketId(33);
          l_feqFinTransactionParams2.setPrice(20);
          l_feqFinTransactionParams2.setRegNo("2");
          l_feqFinTransactionParams2.setAssetId(1001);
          TestDBUtility.insertWithDel(l_feqFinTransactionParams2);
          
          TestDBUtility.deleteAll(GenCurrencyParams.TYPE);
          GenCurrencyParams l_currencyParams = TestDBUtility.getGenCurrencyRow();
          l_currencyParams.setInstitutionCode("0D");
          l_currencyParams.setCurrencyCode("A1");
          l_currencyParams.setCurrentSellExecRate(111.1);
          l_currencyParams.setCurrentBuyExecRate(110.0);
          l_currencyParams.setScale(2);
          TestDBUtility.insertWithDel(l_currencyParams);
          GenCurrencyParams l_currencyParams1 = TestDBUtility.getGenCurrencyRow();
          l_currencyParams1.setInstitutionCode("0D");
          l_currencyParams1.setCurrencyCode("A2");
          l_currencyParams1.setCurrentSellExecRate(222.2);
          l_currencyParams1.setCurrentBuyExecRate(220.0);
          l_currencyParams1.setScale(2);
          TestDBUtility.insertWithDel(l_currencyParams1);
          
          TestDBUtility.deleteAll(FeqOrderExecutionRow.TYPE);
          FeqOrderExecutionParams l_FeqOrderExecutionParams = TestDBUtility.getFeqOrderExecutionParams();
          l_FeqOrderExecutionParams.setOrderExecutionId(1);
          l_FeqOrderExecutionParams.setFxRate(100);
          TestDBUtility.insertWithDel(l_FeqOrderExecutionParams);
          FeqOrderExecutionParams l_FeqOrderExecutionParams1 = TestDBUtility.getFeqOrderExecutionParams();
          l_FeqOrderExecutionParams1.setOrderExecutionId(2);
          l_FeqOrderExecutionParams1.setFxRate(100);
          TestDBUtility.insertWithDel(l_FeqOrderExecutionParams1);
          FeqOrderExecutionParams l_FeqOrderExecutionParams3 = TestDBUtility.getFeqOrderExecutionParams();
          l_FeqOrderExecutionParams3.setOrderExecutionId(4);
          l_FeqOrderExecutionParams3.setFxRate(100);
          TestDBUtility.insertWithDel(l_FeqOrderExecutionParams3);
          
          TestDBUtility.deleteAll(ProductParams.TYPE);
          ProductParams l_ProductParams = TestDBUtility.getProductRow();
          l_ProductParams.setProductId(111111);
          l_ProductParams.setInstitutionCode("0D");
          TestDBUtility.insertWithDel(l_ProductParams);
          ProductParams l_ProductParams2 = TestDBUtility.getProductRow();
          l_ProductParams2.setProductId(333333);
          l_ProductParams2.setInstitutionCode("2D");
          TestDBUtility.insertWithDel(l_ProductParams2);
          
          TestDBUtility.deleteAll(FeqProductParams.TYPE);
          FeqProductParams l_FeqProductParams = TestDBUtility.getFeqProductRow();
          l_FeqProductParams.setProductId(111111);
          l_FeqProductParams.setProductCode("111");
          l_FeqProductParams.setInstitutionCode("0D");
          l_FeqProductParams.setCurrencyCode("A1");
          l_FeqProductParams.setMarketCode("11");
          TestDBUtility.insertWithDel(l_FeqProductParams);
          FeqProductParams l_FeqProductParams2 = TestDBUtility.getFeqProductRow();
          l_FeqProductParams2.setProductId(333333);
          l_FeqProductParams2.setProductCode("333");
          l_FeqProductParams2.setInstitutionCode("2D");
          l_FeqProductParams2.setMarketCode("33");
          TestDBUtility.insertWithDel(l_FeqProductParams2);
          
          TestDBUtility.deleteAll(TradedProductParams.TYPE);
          TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
          l_tradedProductParams.setProductId(111111);
          l_tradedProductParams.setTradedProductId(1111);
          l_tradedProductParams.setMarketId(11);
//          l_tradedProductParams.setValidForBizDate(null);
          TestDBUtility.insertWithDel(l_tradedProductParams);
          TradedProductParams l_tradedProductParams1 = TestDBUtility.getTradedProductRow();
          l_tradedProductParams1.setProductId(333333);
          l_tradedProductParams1.setTradedProductId(3333);
          l_tradedProductParams.setMarketId(33);
//          l_tradedProductParams1.setValidForBizDate(null);
          TestDBUtility.insertWithDel(l_tradedProductParams1);
          
          TestDBUtility.deleteAll(FeqTradedProductParams.TYPE);
          FeqTradedProductParams l_feqTradedProductParams = TestDBUtility.getFeqTradedProductRow();
          l_feqTradedProductParams.setProductId(111111);
          l_feqTradedProductParams.setTradedProductId(1111);
          l_feqTradedProductParams.setMarketId(11);
          l_feqTradedProductParams.setValidForBizDate(null);
          l_feqTradedProductParams.setInstitutionCode("0D");
          TestDBUtility.insertWithDel(l_feqTradedProductParams);
          FeqTradedProductParams l_feqTradedProductParams1 = TestDBUtility.getFeqTradedProductRow();
          l_feqTradedProductParams1.setProductId(333333);
          l_feqTradedProductParams1.setTradedProductId(3333);
          l_feqTradedProductParams1.setMarketId(33);
          l_feqTradedProductParams1.setValidForBizDate(null);
          l_feqTradedProductParams1.setInstitutionCode("2D");
          TestDBUtility.insertWithDel(l_feqTradedProductParams1);
          
          TestDBUtility.deleteAll(MarketParams.TYPE);
          MarketParams l_marketParams = TestDBUtility.getMarketRow();
          l_marketParams.setMarketId(33);
          l_marketParams.setMarketCode("33");
          l_marketParams.setInstitutionCode("2D");
          TestDBUtility.insertWithDel(l_marketParams);
          MarketParams l_marketParams1 = TestDBUtility.getMarketRow();
          l_marketParams1.setMarketId(11);
          l_marketParams1.setMarketCode("11");
          l_marketParams1.setInstitutionCode("0D");
          TestDBUtility.insertWithDel(l_marketParams1);
          
          TestDBUtility.deleteAll(FeqOrderUnitParams.TYPE);
          FeqOrderUnitParams l_FeqOrderUnitParams = TestDBUtility.getFeqOrderUnitRow();
          l_FeqOrderUnitParams.setAccountId(31102102050L);
          l_FeqOrderUnitParams.setSubAccountId(33381251220301L);
          l_FeqOrderUnitParams.setOrderUnitId(1L);
          l_FeqOrderUnitParams.setOrderId(111111L);
          l_FeqOrderUnitParams.setOrderRequestNumber("1");
          l_FeqOrderUnitParams.setOrderEmpCode("1");
          l_FeqOrderUnitParams.setProductId(111111);
          l_FeqOrderUnitParams.setMarketId(11);
          l_FeqOrderUnitParams.setLastOrderActionSerialNo(1);
          l_FeqOrderUnitParams.setConfirmedQuantity(1200);
          TestDBUtility.insertWithDel(l_FeqOrderUnitParams);
          FeqOrderUnitParams l_FeqOrderUnitParams1 = TestDBUtility.getFeqOrderUnitRow();
          l_FeqOrderUnitParams1.setAccountId(31102102050L);
          l_FeqOrderUnitParams1.setSubAccountId(33381251220301L);
          l_FeqOrderUnitParams1.setOrderUnitId(3L);
          l_FeqOrderUnitParams1.setOrderId(333333L);
          l_FeqOrderUnitParams1.setOrderRequestNumber("3");
          l_FeqOrderUnitParams1.setOrderEmpCode("3");
          l_FeqOrderUnitParams1.setProductId(333333);
          l_FeqOrderUnitParams1.setMarketId(33);
          l_FeqOrderUnitParams1.setLastOrderActionSerialNo(3);
          l_FeqOrderUnitParams1.setConfirmedQuantity(1200);
          TestDBUtility.insertWithDel(l_FeqOrderUnitParams1);
          
          TestDBUtility.deleteAll(FeqOrderActionParams.TYPE);
          FeqOrderActionParams l_FeqOrderActionParams = TestDBUtility.getFeqOrderActionParams();
          l_FeqOrderActionParams.setOrderUnitId(1L);
          l_FeqOrderActionParams.setOrderActionSerialNo(1);
          TestDBUtility.insertWithDel(l_FeqOrderActionParams);
          
          TestDBUtility.deleteAll(EquityCommAccountCondMstParams.TYPE);
          EquityCommAccountCondMstParams l_EquityCommAccountCondMstParams = TestDBUtility.getEquityCommAccountCondMstRow();
          l_EquityCommAccountCondMstParams.setInstitutionCode("0D");
          l_EquityCommAccountCondMstParams.setBranchId(33381);
          l_EquityCommAccountCondMstParams.setAccountId(31102102050L);
          l_EquityCommAccountCondMstParams.setCommProductCode("40");
          l_EquityCommAccountCondMstParams.setValidUntilBizDate("20100127");
          TestDBUtility.insertWithDel(l_EquityCommAccountCondMstParams);
          EquityCommAccountCondMstParams l_EquityCommAccountCondMstParams1 = TestDBUtility.getEquityCommAccountCondMstRow();
          l_EquityCommAccountCondMstParams1.setInstitutionCode("0D");
          l_EquityCommAccountCondMstParams1.setBranchId(33381);
          l_EquityCommAccountCondMstParams1.setAccountId(31102102050L);
          l_EquityCommAccountCondMstParams1.setCommProductCode("40");
          l_EquityCommAccountCondMstParams1.setValidUntilBizDate("20080201");
          TestDBUtility.insertWithDel(l_EquityCommAccountCondMstParams1);
          EquityCommAccountCondMstParams l_EquityCommAccountCondMstParams2 = TestDBUtility.getEquityCommAccountCondMstRow();
          l_EquityCommAccountCondMstParams2.setInstitutionCode("0D");
          l_EquityCommAccountCondMstParams2.setBranchId(33381);
          l_EquityCommAccountCondMstParams2.setAccountId(31102102050L);
          l_EquityCommAccountCondMstParams2.setCommProductCode("40");
          l_EquityCommAccountCondMstParams2.setValidUntilBizDate("20070228");
          TestDBUtility.insertWithDel(l_EquityCommAccountCondMstParams2);
          
          TestDBUtility.deleteAll(EquityCommCondRow.TYPE);
          EquityCommCondParams l_EquityCommCondParams = new EquityCommCondParams();
          //証券会社コード  institution_code
          l_EquityCommCondParams.setInstitutionCode("0D");
          //手数料商品コード    comm_product_code
          l_EquityCommCondParams.setCommProductCode("40");
          //登録No         reg_no
          l_EquityCommCondParams.setRegNo("01 ");
          //適用開始年月日  appli_start_date
          l_EquityCommCondParams.setAppliStartDate(WEB3DateUtility.getDate("20061230", "yyyyMMdd"));
          //適用終了年月日  appli_end_date
          l_EquityCommCondParams.setAppliEndDate(WEB3DateUtility.getDate("20101230", "yyyyMMdd"));
          //シーケンスNo  sequence_no
          l_EquityCommCondParams.setSequenceNo("1");
          //売買代金（自）  min_turnover
          l_EquityCommCondParams.setMinTurnover(10);
          //売買代金（至）  max_turnover
          l_EquityCommCondParams.setMaxTurnover(100000);
          //徴収率      charge_ratio
          l_EquityCommCondParams.setChargeRatio(10);
          //付加金額        added_price
          l_EquityCommCondParams.setAddedPrice(100);
          //登録区分        reg_type
          //登録年月日    reg_date
          //最終更新日    final_mod_date
          //最終更新時間  final_mod_time
          //作成日付        created_timestamp
          //更新日付        last_updated_timestamp
          l_EquityCommCondParams.setCreatedTimestamp(Calendar.getInstance().getTime());
          l_EquityCommCondParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
          //手数料乗数   commition_per_unit
          l_EquityCommCondParams.setCommitionPerUnit(100);
          TestDBUtility.insertWithDel(l_EquityCommCondParams);
          
          TestDBUtility.deleteAll(EquityCommCondMstRow.TYPE);
          EquityCommCondMstParams l_EquityCommCondMstParams = new EquityCommCondMstParams();
          //証券会社コード         institution_code              NotNull
          l_EquityCommCondMstParams.setInstitutionCode("0D");
          //手数料商品コード            comm_product_code  NotNull
          l_EquityCommCondMstParams.setCommProductCode("40");
          //登録No                reg_no                   NotNull
          l_EquityCommCondMstParams.setRegNo("01 ");
          //適用開始年月日         appli_start_date  NotNull
          //適用終了年月日         appli_end_date  NotNull
          l_EquityCommCondMstParams.setAppliStartDate(WEB3DateUtility.getDate("20061230", "yyyyMMdd"));
          l_EquityCommCondMstParams.setAppliEndDate(WEB3DateUtility.getDate("20101230", "yyyyMMdd"));
          //最高手数料           max_commission   NULL
          l_EquityCommCondMstParams.setMaxCommission(1000000);
          //最低手数料           min_commission    NotNull
          l_EquityCommCondMstParams.setMinCommission(10);
          //一口区分                share_type        NULL
          //手数料コースコード           commission_course_div      NotNull
          l_EquityCommCondMstParams.setCommissionCourseDiv("04");
          //備考              reference_column
          //特別徴収    設定開始年月日     spc_start_date
          //    設定終了年月日     spc_end_date
          //    徴収率         spc_charge_ratio
          //    最高手数料       spc_max_commission
          //    最低手数料       spc_min_commission
          //登録区分                reg_type
          //登録年月日           reg_date
          //最終更新日           final_mod_date
          //最終更新時間          final_mod_time
          //作成日付                created_timestamp
          //更新日付                last_updated_timestamp
          l_EquityCommCondMstParams.setCreatedTimestamp(Calendar.getInstance().getTime());
          l_EquityCommCondMstParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
          TestDBUtility.insertWithDel(l_EquityCommCondMstParams);
          
          
          MOCK_MANAGER.setIsMockUsed(true);
//          TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                  "webbroker3.feq.WEB3FeqBizLogicProvider",
//                  "calcCommission",
//                  new Class[] {WEB3GentradeCommission.class, SubAccount.class},
//                  null);
          
          WEB3FeqAmountCalcResult l_result = new WEB3FeqAmountCalcResult();
          l_result.setCommissionNumber("1");
          l_result.setCommissionBranchNumber("0");
          TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                  "webbroker3.feq.WEB3FeqBizLogicProvider",
                  "calcFeqAmount",
                  new Class[] {WEB3GentradeSubAccount.class,WEB3FeqProduct.class,WEB3GentradeMarket.class,
                          Date.class,Date.class,double.class,double.class,boolean.class,boolean.class,
                          boolean.class,String.class },
                          l_result);
          
          TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                  "webbroker3.feq.WEB3FeqBizLogicProvider",
                  "calcSalesTax",
                  new Class[] {double.class, Date.class, SubAccount.class },
                  new Double(10));
          
          long[] l_lngOrderUnitIDs = new long[4];
          l_lngOrderUnitIDs[0] = 1L;
          l_lngOrderUnitIDs[1] = 2L;
          l_lngOrderUnitIDs[2] = 3L;
          l_lngOrderUnitIDs[3] = 4L;
          
          WEB3FeqPositionManagerHelper l_helper = new WEB3FeqPositionManagerHelper(ProductTypeEnum.FOREIGN_EQUITY);
          l_helper.updateTransactionNettingAdoption(l_lngOrderUnitIDs, WEB3DateUtility.getDate("20100127", "yyyyMMdd"));

      }
      catch (Exception l_ex)
      {
          log.debug(l_ex.getMessage(), l_ex);
          fail();   
      }
  }
  
  /**
   * ①@売付（トランザクション（取引勘定明細）行.ﾄﾗﾝｻﾞｸｼｮﾝﾀｲﾌﾟ==”外株売り”）の場合
   * ②is信用口座開設()の戻り値 == falseの場合
   */
  public void testUpdateTransactionNettingAdoptionCase4()
  {
      final String STR_METHOD_NAME = "testUpdateTransactionNettingAdoptionCase4()";
      log.entering(STR_METHOD_NAME);
      
      try
      {
          TestDBUtility.deleteAll(AssetParams.TYPE);
          AssetParams l_assetParams = TestDBUtility.getAssetRow();
          l_assetParams.setAssetId(1001);
          l_assetParams.setAccountId(31102102050L);
          l_assetParams.setSubAccountId(33381251220301L);
          l_assetParams.setTaxType(TaxTypeEnum.NORMAL);
          l_assetParams.setQuantityForBookValue(10000);
          l_assetParams.setBookValue(0);
          TestDBUtility.insertWithDel(l_assetParams);
          TestDBUtility.deleteAll(InstitutionParams.TYPE);
          InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
          l_institutionParams.setInstitutionId(33);
          l_institutionParams.setInstitutionCode("0D");
          TestDBUtility.insertWithDel(l_institutionParams);
          InstitutionParams l_institutionParams1 = TestDBUtility.getInstitutionRow();
          l_institutionParams1.setInstitutionId(11);
          l_institutionParams1.setInstitutionCode("2D");
          TestDBUtility.insertWithDel(l_institutionParams1);
          
          TestDBUtility.deleteAll(MainAccountRow.TYPE);
          MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
          l_mainAccountParams.setAccountId(31102102050L);
          TestDBUtility.insertWithDel(l_mainAccountParams);
          
          TestDBUtility.deleteAll(BranchParams.TYPE);
          BranchParams l_BranchParams = TestDBUtility.getBranchRow();
          TestDBUtility.insertWithDel(l_BranchParams);
          
          TestDBUtility.deleteAll(SubAccountRow.TYPE);
          SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
          l_subAccountParams.setAccountId(31102102050L);
          l_subAccountParams.setSubAccountId(33381251220301L);
          l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
          TestDBUtility.insertWithDel(l_subAccountParams);
          
          TestDBUtility.deleteAll(FeqFinTransactionRow.TYPE);
          FeqFinTransactionParams l_feqFinTransactionParams1 = TestDBUtility.getFeqFinTransactionParams();
          l_feqFinTransactionParams1.setFinTransactionId(1L);
          l_feqFinTransactionParams1.setOrderUnitId(1L);
          l_feqFinTransactionParams1.setOrderId(111111L);
          l_feqFinTransactionParams1.setAccountId(31102102050L);
          l_feqFinTransactionParams1.setSubAccountId(33381251220301L);
          l_feqFinTransactionParams1.setCurrencyCode("A1");
          l_feqFinTransactionParams1.setFinTransactionType(FinTransactionType.EQTYPE_FEQ_BUY);
          l_feqFinTransactionParams1.setBalanceAmountFc(125);
          l_feqFinTransactionParams1.setOrderExecutionId(1);
          l_feqFinTransactionParams1.setProductId(111111);
          l_feqFinTransactionParams1.setMarketId(33);
          l_feqFinTransactionParams1.setPrice(10);
          l_feqFinTransactionParams1.setRegNo("1");
          l_feqFinTransactionParams1.setAssetId(1001);
          TestDBUtility.insertWithDel(l_feqFinTransactionParams1);
          FeqFinTransactionParams l_feqFinTransactionParams2 = TestDBUtility.getFeqFinTransactionParams();
          l_feqFinTransactionParams2.setFinTransactionId(2L);
          l_feqFinTransactionParams2.setOrderUnitId(1L);
          l_feqFinTransactionParams2.setOrderId(111111L);
          l_feqFinTransactionParams2.setAccountId(31102102050L);
          l_feqFinTransactionParams2.setSubAccountId(33381251220301L);
          l_feqFinTransactionParams2.setCurrencyCode("A1");
          l_feqFinTransactionParams2.setFinTransactionType(FinTransactionType.EQTYPE_FEQ_SELL);
          l_feqFinTransactionParams2.setBalanceAmountFc(125);
          l_feqFinTransactionParams2.setOrderExecutionId(2);
          l_feqFinTransactionParams2.setProductId(111111);
          l_feqFinTransactionParams2.setMarketId(33);
          l_feqFinTransactionParams2.setPrice(20);
          l_feqFinTransactionParams2.setRegNo("2");
          l_feqFinTransactionParams2.setAssetId(1001);
          TestDBUtility.insertWithDel(l_feqFinTransactionParams2);
          
          TestDBUtility.deleteAll(GenCurrencyParams.TYPE);
          GenCurrencyParams l_currencyParams = TestDBUtility.getGenCurrencyRow();
          l_currencyParams.setInstitutionCode("0D");
          l_currencyParams.setCurrencyCode("A1");
          l_currencyParams.setCurrentSellExecRate(111.1);
          l_currencyParams.setCurrentBuyExecRate(110.0);
          l_currencyParams.setScale(6);
          TestDBUtility.insertWithDel(l_currencyParams);
          GenCurrencyParams l_currencyParams1 = TestDBUtility.getGenCurrencyRow();
          l_currencyParams1.setInstitutionCode("0D");
          l_currencyParams1.setCurrencyCode("A2");
          l_currencyParams1.setCurrentSellExecRate(222.2);
          l_currencyParams1.setCurrentBuyExecRate(220.0);
          l_currencyParams1.setScale(6);
          TestDBUtility.insertWithDel(l_currencyParams1);
          
          TestDBUtility.deleteAll(FeqOrderExecutionRow.TYPE);
          FeqOrderExecutionParams l_FeqOrderExecutionParams = TestDBUtility.getFeqOrderExecutionParams();
          l_FeqOrderExecutionParams.setOrderExecutionId(1);
          l_FeqOrderExecutionParams.setFxRate(100);
          TestDBUtility.insertWithDel(l_FeqOrderExecutionParams);
          FeqOrderExecutionParams l_FeqOrderExecutionParams1 = TestDBUtility.getFeqOrderExecutionParams();
          l_FeqOrderExecutionParams1.setOrderExecutionId(2);
          l_FeqOrderExecutionParams1.setFxRate(100);
          TestDBUtility.insertWithDel(l_FeqOrderExecutionParams1);
          FeqOrderExecutionParams l_FeqOrderExecutionParams3 = TestDBUtility.getFeqOrderExecutionParams();
          l_FeqOrderExecutionParams3.setOrderExecutionId(4);
          l_FeqOrderExecutionParams3.setFxRate(100);
          TestDBUtility.insertWithDel(l_FeqOrderExecutionParams3);
          
          TestDBUtility.deleteAll(ProductParams.TYPE);
          ProductParams l_ProductParams = TestDBUtility.getProductRow();
          l_ProductParams.setProductId(111111);
          l_ProductParams.setInstitutionCode("0D");
          TestDBUtility.insertWithDel(l_ProductParams);
          ProductParams l_ProductParams2 = TestDBUtility.getProductRow();
          l_ProductParams2.setProductId(333333);
          l_ProductParams2.setInstitutionCode("2D");
          TestDBUtility.insertWithDel(l_ProductParams2);
          
          TestDBUtility.deleteAll(FeqProductParams.TYPE);
          FeqProductParams l_FeqProductParams = TestDBUtility.getFeqProductRow();
          l_FeqProductParams.setProductId(111111);
          l_FeqProductParams.setProductCode("111");
          l_FeqProductParams.setInstitutionCode("0D");
          l_FeqProductParams.setCurrencyCode("A1");
          l_FeqProductParams.setMarketCode("11");
          TestDBUtility.insertWithDel(l_FeqProductParams);
          FeqProductParams l_FeqProductParams2 = TestDBUtility.getFeqProductRow();
          l_FeqProductParams2.setProductId(333333);
          l_FeqProductParams2.setProductCode("333");
          l_FeqProductParams2.setInstitutionCode("2D");
          l_FeqProductParams2.setMarketCode("33");
          TestDBUtility.insertWithDel(l_FeqProductParams2);
          
          TestDBUtility.deleteAll(TradedProductParams.TYPE);
          TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
          l_tradedProductParams.setProductId(111111);
          l_tradedProductParams.setTradedProductId(1111);
          l_tradedProductParams.setMarketId(11);
//          l_tradedProductParams.setValidForBizDate(null);
          TestDBUtility.insertWithDel(l_tradedProductParams);
          TradedProductParams l_tradedProductParams1 = TestDBUtility.getTradedProductRow();
          l_tradedProductParams1.setProductId(333333);
          l_tradedProductParams1.setTradedProductId(3333);
          l_tradedProductParams.setMarketId(33);
//          l_tradedProductParams1.setValidForBizDate(null);
          TestDBUtility.insertWithDel(l_tradedProductParams1);
          
          TestDBUtility.deleteAll(FeqTradedProductParams.TYPE);
          FeqTradedProductParams l_feqTradedProductParams = TestDBUtility.getFeqTradedProductRow();
          l_feqTradedProductParams.setProductId(111111);
          l_feqTradedProductParams.setTradedProductId(1111);
          l_feqTradedProductParams.setMarketId(11);
          l_feqTradedProductParams.setValidForBizDate(null);
          l_feqTradedProductParams.setInstitutionCode("0D");
          TestDBUtility.insertWithDel(l_feqTradedProductParams);
          FeqTradedProductParams l_feqTradedProductParams1 = TestDBUtility.getFeqTradedProductRow();
          l_feqTradedProductParams1.setProductId(333333);
          l_feqTradedProductParams1.setTradedProductId(3333);
          l_feqTradedProductParams1.setMarketId(33);
          l_feqTradedProductParams1.setValidForBizDate(null);
          l_feqTradedProductParams1.setInstitutionCode("2D");
          TestDBUtility.insertWithDel(l_feqTradedProductParams1);
          
          TestDBUtility.deleteAll(MarketParams.TYPE);
          MarketParams l_marketParams = TestDBUtility.getMarketRow();
          l_marketParams.setMarketId(33);
          l_marketParams.setMarketCode("33");
          l_marketParams.setInstitutionCode("2D");
          TestDBUtility.insertWithDel(l_marketParams);
          MarketParams l_marketParams1 = TestDBUtility.getMarketRow();
          l_marketParams1.setMarketId(11);
          l_marketParams1.setMarketCode("11");
          l_marketParams1.setInstitutionCode("0D");
          TestDBUtility.insertWithDel(l_marketParams1);
          
          TestDBUtility.deleteAll(FeqOrderUnitParams.TYPE);
          FeqOrderUnitParams l_FeqOrderUnitParams = TestDBUtility.getFeqOrderUnitRow();
          l_FeqOrderUnitParams.setAccountId(31102102050L);
          l_FeqOrderUnitParams.setSubAccountId(33381251220301L);
          l_FeqOrderUnitParams.setOrderUnitId(1L);
          l_FeqOrderUnitParams.setOrderId(111111L);
          l_FeqOrderUnitParams.setOrderRequestNumber("1");
          l_FeqOrderUnitParams.setOrderEmpCode("1");
          l_FeqOrderUnitParams.setProductId(111111);
          l_FeqOrderUnitParams.setMarketId(11);
          l_FeqOrderUnitParams.setLastOrderActionSerialNo(1);
          l_FeqOrderUnitParams.setConfirmedQuantity(1200);
          TestDBUtility.insertWithDel(l_FeqOrderUnitParams);
          FeqOrderUnitParams l_FeqOrderUnitParams1 = TestDBUtility.getFeqOrderUnitRow();
          l_FeqOrderUnitParams1.setAccountId(31102102050L);
          l_FeqOrderUnitParams1.setSubAccountId(33381251220301L);
          l_FeqOrderUnitParams1.setOrderUnitId(3L);
          l_FeqOrderUnitParams1.setOrderId(333333L);
          l_FeqOrderUnitParams1.setOrderRequestNumber("3");
          l_FeqOrderUnitParams1.setOrderEmpCode("3");
          l_FeqOrderUnitParams1.setProductId(333333);
          l_FeqOrderUnitParams1.setMarketId(33);
          l_FeqOrderUnitParams1.setLastOrderActionSerialNo(3);
          l_FeqOrderUnitParams1.setConfirmedQuantity(1200);
          TestDBUtility.insertWithDel(l_FeqOrderUnitParams1);
          
          TestDBUtility.deleteAll(FeqOrderActionParams.TYPE);
          FeqOrderActionParams l_FeqOrderActionParams = TestDBUtility.getFeqOrderActionParams();
          l_FeqOrderActionParams.setOrderUnitId(1L);
          l_FeqOrderActionParams.setOrderActionSerialNo(1);
          TestDBUtility.insertWithDel(l_FeqOrderActionParams);
          
          TestDBUtility.deleteAll(EquityCommAccountCondMstParams.TYPE);
          EquityCommAccountCondMstParams l_EquityCommAccountCondMstParams = TestDBUtility.getEquityCommAccountCondMstRow();
          l_EquityCommAccountCondMstParams.setInstitutionCode("0D");
          l_EquityCommAccountCondMstParams.setBranchId(33381);
          l_EquityCommAccountCondMstParams.setAccountId(31102102050L);
          l_EquityCommAccountCondMstParams.setCommProductCode("40");
          l_EquityCommAccountCondMstParams.setValidUntilBizDate("20100127");
          TestDBUtility.insertWithDel(l_EquityCommAccountCondMstParams);
          EquityCommAccountCondMstParams l_EquityCommAccountCondMstParams1 = TestDBUtility.getEquityCommAccountCondMstRow();
          l_EquityCommAccountCondMstParams1.setInstitutionCode("0D");
          l_EquityCommAccountCondMstParams1.setBranchId(33381);
          l_EquityCommAccountCondMstParams1.setAccountId(31102102050L);
          l_EquityCommAccountCondMstParams1.setCommProductCode("40");
          l_EquityCommAccountCondMstParams1.setValidUntilBizDate("20080201");
          TestDBUtility.insertWithDel(l_EquityCommAccountCondMstParams1);
          EquityCommAccountCondMstParams l_EquityCommAccountCondMstParams2 = TestDBUtility.getEquityCommAccountCondMstRow();
          l_EquityCommAccountCondMstParams2.setInstitutionCode("0D");
          l_EquityCommAccountCondMstParams2.setBranchId(33381);
          l_EquityCommAccountCondMstParams2.setAccountId(31102102050L);
          l_EquityCommAccountCondMstParams2.setCommProductCode("40");
          l_EquityCommAccountCondMstParams2.setValidUntilBizDate("20070228");
          TestDBUtility.insertWithDel(l_EquityCommAccountCondMstParams2);
          
          TestDBUtility.deleteAll(EquityCommCondRow.TYPE);
          EquityCommCondParams l_EquityCommCondParams = new EquityCommCondParams();
          //証券会社コード  institution_code
          l_EquityCommCondParams.setInstitutionCode("0D");
          //手数料商品コード    comm_product_code
          l_EquityCommCondParams.setCommProductCode("40");
          //登録No         reg_no
          l_EquityCommCondParams.setRegNo("01 ");
          //適用開始年月日  appli_start_date
          l_EquityCommCondParams.setAppliStartDate(WEB3DateUtility.getDate("20061230", "yyyyMMdd"));
          //適用終了年月日  appli_end_date
          l_EquityCommCondParams.setAppliEndDate(WEB3DateUtility.getDate("20101230", "yyyyMMdd"));
          //シーケンスNo  sequence_no
          l_EquityCommCondParams.setSequenceNo("1");
          //売買代金（自）  min_turnover
          l_EquityCommCondParams.setMinTurnover(10);
          //売買代金（至）  max_turnover
          l_EquityCommCondParams.setMaxTurnover(100000);
          //徴収率      charge_ratio
          l_EquityCommCondParams.setChargeRatio(10);
          //付加金額        added_price
          l_EquityCommCondParams.setAddedPrice(100);
          //登録区分        reg_type
          //登録年月日    reg_date
          //最終更新日    final_mod_date
          //最終更新時間  final_mod_time
          //作成日付        created_timestamp
          //更新日付        last_updated_timestamp
          l_EquityCommCondParams.setCreatedTimestamp(Calendar.getInstance().getTime());
          l_EquityCommCondParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
          //手数料乗数   commition_per_unit
          l_EquityCommCondParams.setCommitionPerUnit(100);
          TestDBUtility.insertWithDel(l_EquityCommCondParams);
          
          TestDBUtility.deleteAll(EquityCommCondMstRow.TYPE);
          EquityCommCondMstParams l_EquityCommCondMstParams = new EquityCommCondMstParams();
          //証券会社コード         institution_code              NotNull
          l_EquityCommCondMstParams.setInstitutionCode("0D");
          //手数料商品コード            comm_product_code  NotNull
          l_EquityCommCondMstParams.setCommProductCode("40");
          //登録No                reg_no                   NotNull
          l_EquityCommCondMstParams.setRegNo("01 ");
          //適用開始年月日         appli_start_date  NotNull
          //適用終了年月日         appli_end_date  NotNull
          l_EquityCommCondMstParams.setAppliStartDate(WEB3DateUtility.getDate("20061230", "yyyyMMdd"));
          l_EquityCommCondMstParams.setAppliEndDate(WEB3DateUtility.getDate("20101230", "yyyyMMdd"));
          //最高手数料           max_commission   NULL
          l_EquityCommCondMstParams.setMaxCommission(1000000);
          //最低手数料           min_commission    NotNull
          l_EquityCommCondMstParams.setMinCommission(10);
          //一口区分                share_type        NULL
          //手数料コースコード           commission_course_div      NotNull
          l_EquityCommCondMstParams.setCommissionCourseDiv("04");
          //備考              reference_column
          //特別徴収    設定開始年月日     spc_start_date
          //    設定終了年月日     spc_end_date
          //    徴収率         spc_charge_ratio
          //    最高手数料       spc_max_commission
          //    最低手数料       spc_min_commission
          //登録区分                reg_type
          //登録年月日           reg_date
          //最終更新日           final_mod_date
          //最終更新時間          final_mod_time
          //作成日付                created_timestamp
          //更新日付                last_updated_timestamp
          l_EquityCommCondMstParams.setCreatedTimestamp(Calendar.getInstance().getTime());
          l_EquityCommCondMstParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
          TestDBUtility.insertWithDel(l_EquityCommCondMstParams);
          
          
          MOCK_MANAGER.setIsMockUsed(true);
//          TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                  "webbroker3.feq.WEB3FeqBizLogicProvider",
//                  "calcCommission",
//                  new Class[] {WEB3GentradeCommission.class, SubAccount.class},
//                  null);
          
          WEB3FeqAmountCalcResult l_result = new WEB3FeqAmountCalcResult();
          l_result.setCommissionNumber("1");
          l_result.setCommissionBranchNumber("0");
          TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                  "webbroker3.feq.WEB3FeqBizLogicProvider",
                  "calcFeqAmount",
                  new Class[] {WEB3GentradeSubAccount.class,WEB3FeqProduct.class,WEB3GentradeMarket.class,
                          Date.class,Date.class,double.class,double.class,boolean.class,boolean.class,
                          boolean.class,String.class },
                          l_result);
          
          TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                  "webbroker3.feq.WEB3FeqBizLogicProvider",
                  "calcSalesTax",
                  new Class[] {double.class, Date.class, SubAccount.class },
                  new Double(10));
          
          long[] l_lngOrderUnitIDs = new long[4];
          l_lngOrderUnitIDs[0] = 1L;
          l_lngOrderUnitIDs[1] = 2L;
          l_lngOrderUnitIDs[2] = 3L;
          l_lngOrderUnitIDs[3] = 4L;
          
          WEB3FeqPositionManagerHelper l_helper = new WEB3FeqPositionManagerHelper(ProductTypeEnum.FOREIGN_EQUITY);
          l_helper.updateTransactionNettingAdoption(l_lngOrderUnitIDs, WEB3DateUtility.getDate("20100127", "yyyyMMdd"));

      }
      catch (Exception l_ex)
      {
          log.debug(l_ex.getMessage(), l_ex);
          fail();   
      }
  }
  
  private void updateTransaction(
      long[] l_lngOrderUnitIds,
      Date l_datBaseDate,
      boolean l_blnIsNetting) throws WEB3BaseException
      {
          final String STR_METHOD_NAME = "updateTransaction(long[], Date, boolean)";
          log.entering(STR_METHOD_NAME);
          
          if (l_lngOrderUnitIds == null || l_lngOrderUnitIds.length == 0)
          {
              log.exiting(STR_METHOD_NAME);
              throw new WEB3BaseRuntimeException(
                  WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                  this.getClass().getName() + "." + STR_METHOD_NAME);
          }
          
          //外国株式更新データマネージャを取得する。 
          WEB3FeqPositionManagerHelper.WEB3FeqUpdateDataManager l_dataManager = 
              (WEB3FeqPositionManagerHelper.WEB3FeqUpdateDataManager)l_help.getPersistenceManager();
          FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
          TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
          WEB3FeqBizLogicProvider l_bizlogicProvider = (WEB3FeqBizLogicProvider)l_tradingModule.getBizLogicProvider();
          WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
          WEB3FeqOrderManager l_orderManager = (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
          
          
          try
          {
              //1.1手数料一括対象となる既約定の取引勘定明細をリストで取得する。
              List l_lisLumpObj = l_dataManager.getFinTransactionForLumpObj(l_lngOrderUnitIds);
              //1.2該当データなし または 初回約定の場合（get取引勘定明細For一括対象().size() == 1）、処理を終了する。
              if (l_lisLumpObj == null || l_lisLumpObj.isEmpty() || l_lisLumpObj.size() == 1 )
              {
                  return;
              }
              //1.3一括対象となるトランザクションを格納するArrayListを生成する。
              List listObj = new ArrayList();
              //1.4get取引勘定明細For一括対象()の戻り値の要素数分Loop処理
              for (int i = 0; i < l_lisLumpObj.size(); i++)
              {
                  //1.4.1add(arg0 : Object)
                  FeqFinTransactionRow l_finTransactionRow = (FeqFinTransactionRow) l_lisLumpObj.get(i);
                  log.debug("FinTransactionId = " + l_finTransactionRow.getFinTransactionId());
                  log.debug("OrderUnitId = " + l_finTransactionRow.getOrderUnitId());
                  listObj.add(l_finTransactionRow);
                  
                  //1.4.2(*)以下の条件のいずれかに該当する場合、手数料一括処理を実施する。
                  //①@現在処理している要素が最後の要素の場合
                  //②次の要素(index + 1)と、今回の要素について、一括対象キー(*1)が
                  //異なった場合
                  boolean l_blnIsNextFactor = false;
                  if ((i + 1) < l_lisLumpObj.size())
                  {
                      FeqFinTransactionRow l_finTransactionRowNext = (FeqFinTransactionRow) l_lisLumpObj.get(i + 1);
                      //(*1)一括対象キー・・・以下の値を文字列連結したもの。
                      //トランザクション.銘柄ID + トランザクション.トランザクションタイプ
                      //+  トランザクション.決済区分 + トランザクション.発注日
                      StringBuffer l_strLumpObj = new StringBuffer();
                      l_strLumpObj.append(l_finTransactionRow.getProductId());
                      l_strLumpObj.append(l_finTransactionRow.getFinTransactionType().toString());
                      l_strLumpObj.append(l_finTransactionRow.getSettleDiv());
                      l_strLumpObj.append(l_finTransactionRow.getBizDate());
      
                      StringBuffer l_strLumpObjNext = new StringBuffer();
                      l_strLumpObjNext.append(l_finTransactionRowNext.getProductId());
                      l_strLumpObjNext.append(l_finTransactionRowNext.getFinTransactionType().toString());
                      l_strLumpObjNext.append(l_finTransactionRowNext.getSettleDiv());
                      l_strLumpObjNext.append(l_finTransactionRowNext.getBizDate());
                      
                      if(!(l_strLumpObj.toString().equals(l_strLumpObjNext.toString())))
                      {
                          l_blnIsNextFactor = true;
                      }
                  }
                  boolean l_blnIsLastFactor = false; 
                  if ((i + 1) == l_lisLumpObj.size())
                  {
                      l_blnIsLastFactor = true;
                  }
                  
                  if (l_blnIsLastFactor || l_blnIsNextFactor)
                  {
                      //1.4.2.1同一注文チェック
                      boolean l_blnIsOrderUnit = false;
                      
                      Map l_mapOrderUnitId = new HashMap();
                      for (int k = 0; k < listObj.size(); k++)
                      {
                          FeqFinTransactionRow l_row = (FeqFinTransactionRow)listObj.get(k);
                          Long l_lngOrderUnitId = new Long(l_row.getOrderUnitId());
                          l_mapOrderUnitId.put(l_lngOrderUnitId, l_lngOrderUnitId);
                      }
                      if (l_mapOrderUnitId.size() == 1)
                      {
                          l_blnIsOrderUnit = true;
                      }
      
                      
                      if (l_blnIsOrderUnit)
                      {
                          listObj.clear();
                          continue;
                      }
                      //1.4.2.2一括対象となるトランザクション（取引勘定明細）の配列を生成する。
                      FeqFinTransactionParams[] l_fransactionParams = new FeqFinTransactionParams[listObj.size()];
                      listObj.toArray(l_fransactionParams);
                      //1.4.2.3外国株式金額按分計算を行う。 
//                      WEB3FeqAmountCalcResultFactor l_resultFactor = l_bizlogicProvider.calcFeqAmountFactor(l_fransactionParams);
                      //1.4.2.4外国株式計算結果（合計）を取得する。
//                      WEB3FeqAmountCalcResult l_calcResult = l_resultFactor.getFeqAmountCalcResultTotal();
                                          
                      //1.4.2.5toArray()の戻り値各要素毎のLOOP処理
                      for (int j = 0; j < l_fransactionParams.length; j++)
                      {
                          double l_dblNetAmountBeforeUpdate = l_fransactionParams[j].getNetAmount();
                          //1.4.2.5.1委託手数料を取得する。 
//                          double l_dblCommisionFee = l_resultFactor.getCommisionFee(j);
//                          log.debug("委託手数料 = " + l_dblCommisionFee);
                          //1.4.2.5.2委託手数料消費税を取得する。 
//                          double l_dblCommisionFeeTax = l_resultFactor.getCommisionFeeTax(j);
//                          log.debug("委託手数料消費税 = " + l_dblCommisionFeeTax);
                          //1.4.2.5.3現地清算代金（円貨）を取得する。 
//                          double l_dblBalanceAmount = l_resultFactor.getBalanceAmount(j);
//                          log.debug("現地清算代金（円貨） = " + l_dblBalanceAmount);
                          //1.4.2.5.4get委託手数料（外貨）(
//                          double l_dblCommisionFeeFc = l_resultFactor.getCommisionFeeFc(j);
//                          log.debug("委託手数料（外貨） = " + l_dblCommisionFeeFc);
                          //1.4.2.5.5get委託手数料消費税（外貨）(
//                          double l_dblCommisionFeeTaxFc = l_resultFactor.getCommisionFeeTaxFc(j);
//                          log.debug("委託手数料消費税（外貨） = " + l_dblCommisionFeeTaxFc);
                          //1.4.2.5.6現地清算代金を取得する。 
//                          double l_dblBalanceAmountFc = l_resultFactor.getBalanceAmountFc(j);
//                          log.debug("現地清算代金 = " + l_dblBalanceAmountFc);
                          //1.4.2.5.7現地手数料を取得する。 
//                          double l_dblForeignCommissionFee = l_resultFactor.getForeignCommissionFee(j);
//                          log.debug("現地手数料 = " + l_dblForeignCommissionFee);
                          //1.4.2.5.8現地取引税を取得する。
//                          double l_dblForeignTax = l_resultFactor.getForeignTax(j);
//                          log.debug("現地取引税 = " + l_dblForeignTax);
                          //1.4.2.5.9その他コスト１を取得する。 
//                          double l_dblForeignFeeExt1 = l_resultFactor.getForeignFeeExt1(j);
//                          log.debug("その他コスト１ = " + l_dblForeignFeeExt1);
                          //1.4.2.5.10その他コスト２を取得する。 
//                          double l_dblForeignFeeExt2 = l_resultFactor.getForeignFeeExt2(j);
//                          log.debug("その他コスト２ = " + l_dblForeignFeeExt2);
//                          double l_dblNetAmount = l_resultFactor.getNetAmount(j);
//                          log.debug("受渡代金 = " + l_dblNetAmount);
//                          double l_dblNetAmountFc = l_resultFactor.getNetAmountFc(j);
//                          log.debug("受渡代金（外貨） = " + l_dblNetAmountFc);
                          //1.4.2.5.11トランザクションタイプを取得する。 
                          FinTransactionType l_finTransactionType = l_fransactionParams[j].getFinTransactionType();
      
//                          double l_dblProfitLoss = 0.0D;
//                          double l_dblCapitalGainTax = 0.0D;
//                          double l_dblProfitLossFc = 0.0D;
//                          double l_dblCapitalGainTaxFc = 0.0D;
                          //補助口座ＩＤを取得する。
                          long l_lngSubAccountId = l_fransactionParams[j].getSubAccountId();
//                          log.debug("補助口座ＩＤ = " + l_lngSubAccountId);
                          //getSubAccountId()に該当する補助口座
                          WEB3GentradeSubAccount l_subAccount = 
                              (WEB3GentradeSubAccount)l_accountManager.getSubAccount(
                                  l_fransactionParams[j].getAccountId(), 
                                  l_lngSubAccountId);
                          //1.4.2.5.12売の場合（ﾄﾗﾝｻﾞｸｼｮﾝﾀｲﾌﾟ=="外株売り"）、譲渡損益／譲渡益税を計算する
//                          if (FinTransactionType.EQTYPE_FEQ_SELL.equals(l_finTransactionType))
//                          {
//                              //1.4.2.5.12.2約定数量を取得する。
//                              double l_dblQuantity = l_fransactionParams[j].getQuantity();
//                              log.debug("約定数量 = " + l_dblQuantity);
//                              //1.4.2.5.12.3銘柄ＩＤを取得する。
//                              long l_lngProductId = l_fransactionParams[j].getProductId();
//                              log.debug("銘柄ＩＤ = " + l_lngProductId);
  //
//                              //1.4.2.5.12.5税区分を取得する。
//                              TaxTypeEnum l_taxTypeEnum = l_fransactionParams[j].getTaxType();
//                              //1.4.2.5.12.6譲渡損益を計算する。 
//                              l_dblProfitLoss = 
//                                  l_bizlogicProvider.calcCapitalProfitLoss(
//                                      l_dblNetAmount, 
//                                      l_dblQuantity, 
//                                      l_lngProductId, 
//                                      l_subAccount, 
//                                      l_taxTypeEnum);
//                              log.debug("譲渡損益 = " + l_dblProfitLoss);
//                              //1.4.2.5.12.7受渡日を取得する。
//                              Date l_datDeliveryDate = l_fransactionParams[j].getDeliveryDate();
//                              log.debug("受渡日 = " + l_datDeliveryDate);
//                              //1.4.2.5.12.8譲渡益税を計算する。 
//                              l_dblCapitalGainTax = 
//                                  l_bizlogicProvider.calcCapitalGainTax(l_subAccount, l_taxTypeEnum, l_dblProfitLoss, l_datDeliveryDate);
//                              log.debug("譲渡益税 = " + l_dblCapitalGainTax);
//                              double l_dbFxRate = l_fransactionParams[j].getFxRate();
//                              l_dblProfitLossFc =
//                                  l_bizlogicProvider.calcForeignCCYAmount(
//                                      l_dblProfitLoss,
//                                      l_dbFxRate,
//                                      l_lngProductId,
//                                      false,
//                                      true);
//                              log.debug("譲渡益金額（外貨）= " + l_dblProfitLossFc);
//                  
//                              l_dblCapitalGainTaxFc =
//                                  l_bizlogicProvider.calcForeignCCYAmount(
//                                      l_dblCapitalGainTax,
//                                      l_dbFxRate,
//                                      l_lngProductId,
//                                      false,
//                                      true);
//                              log.debug("譲渡益税額（外貨）= " + l_dblCapitalGainTaxFc);
//                          }
                          
                          //1.4.2.5.13(*) プロパティセット
                          //【ｘTrade】補足資料.DB更新\\20.（管）出来終了
                          //「外株出来終了_トランザクション（取引勘定明細）仕様.xls」参照。

//                          if (FinTransactionType.EQTYPE_FEQ_BUY.equals(l_finTransactionType))
//                          {
//                              l_fransactionParams[j].setNetAmount(l_dblNetAmount * -1);
//                              l_fransactionParams[j].setNetAmountFc(l_dblNetAmountFc * -1);
//                              l_fransactionParams[j].setCapitalGain(0);
//                              l_fransactionParams[j].setCapitalGainTax(0);
//                              l_fransactionParams[j].setCapitalGainFc(0);
//                              l_fransactionParams[j].setCapitalGainTaxFc(0);
//                          }
  //
//                          else if (FinTransactionType.EQTYPE_FEQ_SELL.equals(l_finTransactionType))
//                          {
//                              l_fransactionParams[j].setNetAmount(l_dblNetAmount);
//                              l_fransactionParams[j].setNetAmountFc(l_dblNetAmountFc);
//                              l_fransactionParams[j].setCapitalGain(l_dblProfitLoss);
//                              l_fransactionParams[j].setCapitalGainTax(l_dblCapitalGainTax);
//                              l_fransactionParams[j].setCapitalGainFc(l_dblProfitLossFc);
//                              l_fransactionParams[j].setCapitalGainTaxFc(l_dblCapitalGainTaxFc);
//                          }
                          
//                          //一口按分後の委託手数料
//                          l_fransactionParams[j].setCommissionFee(l_dblCommisionFee);
//                          //一口按分後の委託手数料消費税
//                          l_fransactionParams[j].setCommissionFeeTax(l_dblCommisionFeeTax);
//                          //一口按分後の登録No
//                          l_fransactionParams[j].setRegNo(
//                              l_calcResult.getCommissionNumber() + l_calcResult.getCommissionBranchNumber());
//                          //一口按分後の徴収率
//                          l_fransactionParams[j].setChargeRatio(l_calcResult.getChargeRatio());
//                          //一口按分後の現地清算代金（円貨）
//                          l_fransactionParams[j].setBalanceAmount(l_dblBalanceAmount);
//                          //一口按分後の委託手数料（外貨）
//                          l_fransactionParams[j].setCommissionFeeFc(l_dblCommisionFeeFc);
//                          //一口按分後の委託手数料消費税（外貨）
//                          l_fransactionParams[j].setCommissionFeeTaxFc(l_dblCommisionFeeTaxFc);
//                          //一口按分後の現地清算代金（外貨）
//                          l_fransactionParams[j].setBalanceAmountFc(l_dblBalanceAmountFc);
//                          //一口按分後の現地手数料
//                          l_fransactionParams[j].setForeignCommissionFee(l_dblForeignCommissionFee);
//                          //一口按分後の現地取引税
//                          l_fransactionParams[j].setForeignTax(l_dblForeignTax);
//                          //一口按分後のその他コスト１
//                          l_fransactionParams[j].setForeignFeeExt1(l_dblForeignFeeExt1);
//                          //一口按分後のその他コスト２
//                          l_fransactionParams[j].setForeignFeeExt2(l_dblForeignFeeExt2);
                          //約定経路区分
                          //セッションよりログインＩＤを取得、ログインＩＤに該当する管理者.管理者コード。
                          //ログインＩＤが取得できない場合は、null。.更新者コード                        
//                          try
//                          {
//                              OpLoginSecurityService l_opLoginSecurityService =
//                                  (OpLoginSecurityService)Services.getService(
//                                  OpLoginSecurityService.class);
//                              
//                              l_opLoginSecurityService.getLoginId();
//                                                          
//                              WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
//                              l_fransactionParams[j].setLastUpdater(l_administrator.getAdministratorCode());
//                          }
//                          catch (IllegalSessionStateException l_ex)
//                          {
//                              l_fransactionParams[j].setLastUpdater(null);
//                          }
//                          //現在日時
//                          l_fransactionParams[j].setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
//                          
//                          //1.4.2.5.14トランザクション（取引勘定明細）を更新する。 
//                          Map l_mapValues = new HashMap();
//                          //受渡代金 
//                          l_mapValues.put("net_amount", new BigDecimal(l_fransactionParams[j].getNetAmount()));
//                          //受渡代金（外貨） 
//                          l_mapValues.put("net_amount_fc", new BigDecimal(l_fransactionParams[j].getNetAmountFc()));
//                          //委託手数料 
//                          l_mapValues.put("commission_fee", new BigDecimal(l_fransactionParams[j].getCommissionFee()));
//                          //委託手数料消費税 
//                          l_mapValues.put("commission_fee_tax", new BigDecimal(l_fransactionParams[j].getCommissionFeeTax()));
//                          //登録No
//                          l_mapValues.put("reg_no", l_fransactionParams[j].getRegNo());
//                          //徴収率
//                          l_mapValues.put("charge_ratio", new BigDecimal(l_calcResult.getChargeRatio()));
//                          //現地清算代金（円貨） 
//                          l_mapValues.put("balance_amount", new BigDecimal(l_fransactionParams[j].getBalanceAmount()));
//                          //委託手数料（外貨） 
//                          l_mapValues.put("commission_fee_fc", new BigDecimal(l_fransactionParams[j].getCommissionFeeFc()));
//                          //委託手数料消費税（外貨） 
//                          l_mapValues.put("commission_fee_tax_fc", new BigDecimal(l_fransactionParams[j].getCommissionFeeTaxFc()));
//                          //現地清算代金（外貨） 
//                          l_mapValues.put("balance_amount_fc", new BigDecimal(l_fransactionParams[j].getBalanceAmountFc()));
//                          //現地手数料 
//                          l_mapValues.put("foreign_commission_fee", new BigDecimal(l_fransactionParams[j].getForeignCommissionFee()));
//                          //現地取引税 
//                          l_mapValues.put("foreign_tax", new BigDecimal(l_fransactionParams[j].getForeignTax()));
//                          //その他コスト１ 
//                          l_mapValues.put("foreign_fee_ext1", new BigDecimal(l_fransactionParams[j].getForeignFeeExt1()));
//                          //その他コスト２ 
//                          l_mapValues.put("foreign_fee_ext2", new BigDecimal(l_fransactionParams[j].getForeignFeeExt2()));
//                          //譲渡益金額 
//                          l_mapValues.put("capital_gain", new BigDecimal(l_fransactionParams[j].getCapitalGain()));
//                          //譲渡益税額 
//                          l_mapValues.put("capital_gain_tax", new BigDecimal(l_fransactionParams[j].getCapitalGainTax()));
//                          //譲渡益金額（外貨）
//                          l_mapValues.put("capital_gain_fc", new BigDecimal(l_fransactionParams[j].getCapitalGainFc()));
//                          //譲渡益税額（外貨）
//                          l_mapValues.put("capital_gain_tax_fc", new BigDecimal(l_fransactionParams[j].getCapitalGainTaxFc()));
//                          //更新者コード 
//                          l_mapValues.put("last_updater", l_fransactionParams[j].getLastUpdater());
//                          //更新日付 
//                          l_mapValues.put("last_updated_timestamp", l_fransactionParams[j].getLastUpdatedTimestamp());
//                          
//                          l_dataManager.updateFinTransaction(l_fransactionParams[j], l_mapValues);
//                          //1.4.2.5.15顧客勘定明細，補助口座を更新する。 
//                          this.notifyGtl(l_fransactionParams[j]);
  //
                          boolean l_blnIsDayTradeAdoption = false;
                          boolean l_blnIsDayTradeMarket = false;
                          WEB3GentradeMarket l_market =
                              (WEB3GentradeMarket)(l_finApp.getFinObjectManager().getMarket(l_fransactionParams[j].getMarketId()));
                          WEB3GentradeInstitution l_institution = (WEB3GentradeInstitution)l_subAccount.getInstitution();
                          l_blnIsDayTradeAdoption = l_institution.isDayTradeAdoption();
                          l_blnIsDayTradeMarket = l_market.isDayTradeMarket();

                          // 買付（トランザクション（取引勘定明細）行.ﾄﾗﾝｻﾞｸｼｮﾝﾀｲﾌﾟ==”外株買い”）の場合
                          if (FinTransactionType.EQTYPE_FEQ_BUY.equals(l_finTransactionType))
                          {
                              // isネッティング処理==true || (isネッティング処理==false 且つ
                              // is日計り取引採用()==true 且つ　@is日計り市場()==true)の場合
                              if (l_blnIsNetting || (l_blnIsNetting == false
                                  && l_blnIsDayTradeAdoption && l_blnIsDayTradeMarket))
                              {
                                  //保有資産更新（簿価更新用）(long, double, double)
                                  l_help.assetUpdateNettingExchangeRateAdoption(
                                      l_fransactionParams[j].getAssetId(),
                                      l_dblNetAmountBeforeUpdate,
                                      l_fransactionParams[j].getNetAmount());
                              }
                          }
                      }

                      //1.4.2.6toArray()の戻り値.注文単位IDのうち、ユニークな注文単位ID全てについてLoop処理
                      for (int k = 0; k < listObj.size(); k++)
                      {
                          FeqFinTransactionRow l_transactionRow = (FeqFinTransactionRow) listObj.get(k);
                          //1.4.2.6.1手数料一括再計算の結果を注文単位、注文履歴に更新する。 
                          WEB3FeqOrderUnit l_orderUnit = (WEB3FeqOrderUnit)l_orderManager.getOrderUnit(l_transactionRow.getOrderUnitId());
                          l_datBaseDate = l_transactionRow.getFinTransactionTimestamp();
                          l_orderManager.updateEstimatedPrice(l_orderUnit, l_datBaseDate);
                      }
                      //1.4.2.7ArrayListの要素をクリアする。
                      listObj.clear();
                  }
              }
          }
          catch (NotFoundException l_ex)
          {
              log.error("テーブルに該当するデータがありません。: ", l_ex);
              log.exiting(STR_METHOD_NAME);
              throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                  this.getClass().getName() + "." + STR_METHOD_NAME, 
                  l_ex.getMessage(), 
                  l_ex);
          }
//          catch (DataException l_ex)
//          {
//              log.error("DBへのアクセスに失敗しました: ", l_ex);
//              log.exiting(STR_METHOD_NAME);
//              throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
//                  this.getClass().getName() + "." + STR_METHOD_NAME, 
//                  l_ex.getMessage(), 
//                  l_ex);
//          }

      }
}
@
