head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.55.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FeqBizLogicProviderTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研ビジネス・イノベーション
File Name        :  外国株式計算サービス(WEB3FeqBizLogicProvider.java)
Author Name      : Daiwa Institute of Research Business Innovation
Revesion History : 2007/11/07 何文敏(中訊) 実装No.005
Revesion History : 2010/10/14 趙天月(中訊) モデルNo.556, No.557, No.558, 計算式書No.015
*/

package webbroker3.feq;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionCateg;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketPreferencesParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketPreferencesRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqFinTransactionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.stdimpls.FeqPositionManagerHelper.PersistentDataManager;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ChangeRoundDivDef;
import webbroker3.common.define.WEB3MiniStockDivDef;
import webbroker3.feq.data.ForeignCostParams;
import webbroker3.feq.data.SpecialProductForeignCostParams;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.data.GenCurrencyParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3FeqBizLogicProviderTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqBizLogicProviderTest.class);
    
    WEB3FeqBizLogicProvider l_provider = new WEB3FeqBizLogicProvider();
    public WEB3FeqBizLogicProviderTest(String arg0)
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
    
    public void testGetNettingExchangeRateCase1()
    {
        final String STR_METHOD_NAME = "testGetNettingExchangeRateCase1()";
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
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(31102102050L);
            l_subAccountParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(FeqFinTransactionRow.TYPE);
            FeqFinTransactionParams l_feqFinTransactionParams1 = TestDBUtility.getFeqFinTransactionParams();
            l_feqFinTransactionParams1.setFinTransactionId(1L);
            l_feqFinTransactionParams1.setOrderUnitId(1L);
            l_feqFinTransactionParams1.setAccountId(31102102050L);
            l_feqFinTransactionParams1.setSubAccountId(33381251220301L);
            l_feqFinTransactionParams1.setCurrencyCode("A1");
            l_feqFinTransactionParams1.setFinTransactionType(FinTransactionType.EQTYPE_FEQ_BUY);
            l_feqFinTransactionParams1.setBalanceAmountFc(125);
            TestDBUtility.insertWithDel(l_feqFinTransactionParams1);
            FeqFinTransactionParams l_feqFinTransactionParams2 = TestDBUtility.getFeqFinTransactionParams();
            l_feqFinTransactionParams2.setFinTransactionId(2L);
            l_feqFinTransactionParams2.setOrderUnitId(2L);
            l_feqFinTransactionParams2.setAccountId(31102102050L);
            l_feqFinTransactionParams2.setSubAccountId(33381251220301L);
            l_feqFinTransactionParams2.setCurrencyCode("A1");
            l_feqFinTransactionParams2.setFinTransactionType(FinTransactionType.EQTYPE_FEQ_SELL);
            l_feqFinTransactionParams2.setBalanceAmountFc(125);
            TestDBUtility.insertWithDel(l_feqFinTransactionParams2);
            FeqFinTransactionParams l_feqFinTransactionParams3 = TestDBUtility.getFeqFinTransactionParams();
            l_feqFinTransactionParams3.setFinTransactionId(3L);
            l_feqFinTransactionParams3.setOrderUnitId(2L);
            l_feqFinTransactionParams3.setAccountId(31102102050L);
            l_feqFinTransactionParams3.setSubAccountId(33381251220301L);
            l_feqFinTransactionParams3.setCurrencyCode("A1");
            l_feqFinTransactionParams3.setFinTransactionType(FinTransactionType.EQTYPE_FEQ_SELL);
            l_feqFinTransactionParams3.setBalanceAmountFc(125);
            TestDBUtility.insertWithDel(l_feqFinTransactionParams3);
            FeqFinTransactionParams l_feqFinTransactionParams4 = TestDBUtility.getFeqFinTransactionParams();
            l_feqFinTransactionParams4.setFinTransactionId(4L);
            l_feqFinTransactionParams4.setOrderUnitId(3L);
            l_feqFinTransactionParams4.setAccountId(31102102050L);
            l_feqFinTransactionParams4.setSubAccountId(33381251220301L);
            l_feqFinTransactionParams4.setCurrencyCode("A2");
            l_feqFinTransactionParams4.setFinTransactionType(FinTransactionType.EQTYPE_FEQ_SELL);
            l_feqFinTransactionParams4.setBalanceAmountFc(125);
            TestDBUtility.insertWithDel(l_feqFinTransactionParams4);
            FeqFinTransactionParams l_feqFinTransactionParams5 = TestDBUtility.getFeqFinTransactionParams();
            l_feqFinTransactionParams5.setFinTransactionId(5L);
            l_feqFinTransactionParams5.setOrderUnitId(4L);
            l_feqFinTransactionParams5.setAccountId(31102102050L);
            l_feqFinTransactionParams5.setSubAccountId(33381251220301L);
            l_feqFinTransactionParams5.setCurrencyCode("A2");
            l_feqFinTransactionParams5.setFinTransactionType(FinTransactionType.EQTYPE_FEQ_BUY);
            l_feqFinTransactionParams5.setBalanceAmountFc(125);
            TestDBUtility.insertWithDel(l_feqFinTransactionParams5);
            FeqFinTransactionParams l_feqFinTransactionParams6 = TestDBUtility.getFeqFinTransactionParams();
            l_feqFinTransactionParams6.setFinTransactionId(6L);
            l_feqFinTransactionParams6.setOrderUnitId(4L);
            l_feqFinTransactionParams6.setAccountId(31102102050L);
            l_feqFinTransactionParams6.setSubAccountId(33381251220301L);
            l_feqFinTransactionParams6.setCurrencyCode("A3");
            l_feqFinTransactionParams6.setFinTransactionType(FinTransactionType.EQTYPE_FEQ_SELL);
            l_feqFinTransactionParams6.setBalanceAmountFc(125);
            TestDBUtility.insertWithDel(l_feqFinTransactionParams6);
            FeqFinTransactionParams l_feqFinTransactionParams7 = TestDBUtility.getFeqFinTransactionParams();
            l_feqFinTransactionParams7.setFinTransactionId(7L);
            l_feqFinTransactionParams7.setOrderUnitId(4L);
            l_feqFinTransactionParams7.setAccountId(31102102050L);
            l_feqFinTransactionParams7.setSubAccountId(33381251220301L);
            l_feqFinTransactionParams7.setCurrencyCode("A3");
            l_feqFinTransactionParams7.setFinTransactionType(FinTransactionType.EQTYPE_FEQ_SELL);
            l_feqFinTransactionParams7.setBalanceAmountFc(125);
            TestDBUtility.insertWithDel(l_feqFinTransactionParams7);
            
            TestDBUtility.deleteAll(GenCurrencyParams.TYPE);
            GenCurrencyParams l_currencyParams = TestDBUtility.getGenCurrencyRow();
            l_currencyParams.setInstitutionCode("0D");
            l_currencyParams.setCurrencyCode("A1");
            l_currencyParams.setCurrentSellExecRate(111.1);
            l_currencyParams.setCurrentBuyExecRate(110.0);
            TestDBUtility.insertWithDel(l_currencyParams);
            GenCurrencyParams l_currencyParams1 = TestDBUtility.getGenCurrencyRow();
            l_currencyParams1.setInstitutionCode("0D");
            l_currencyParams1.setCurrencyCode("A2");
            l_currencyParams1.setCurrentSellExecRate(222.2);
            l_currencyParams1.setCurrentBuyExecRate(220.0);
            TestDBUtility.insertWithDel(l_currencyParams1);
            
            long[] l_lngOrderUnitIDs = new long[4];
            l_lngOrderUnitIDs[0] = 1L;
            l_lngOrderUnitIDs[1] = 2L;
            l_lngOrderUnitIDs[2] = 3L;
            l_lngOrderUnitIDs[3] = 4L;
            WEB3FeqPositionManagerHelper l_feqPositionManagerHelper =
                new WEB3FeqPositionManagerHelper(ProductTypeEnum.FOREIGN_EQUITY);
            PersistentDataManager l_PersistentDataManager = l_feqPositionManagerHelper.getPersistenceManager();
            WEB3FeqPositionManagerHelper.WEB3FeqUpdateDataManager l_dataManager = 
                (WEB3FeqPositionManagerHelper.WEB3FeqUpdateDataManager)l_PersistentDataManager;
            List l_lisFinTransactionForLumpObj = l_dataManager.getFinTransactionForLumpObj(l_lngOrderUnitIDs);
            
            HashMap l_hmNettingExchangeRate = l_provider.getNettingExchangeRate(l_lisFinTransactionForLumpObj);
            
            assertEquals("111.1", ((Double)l_hmNettingExchangeRate.get("A1")).toString());
            assertEquals("220.0", ((Double)l_hmNettingExchangeRate.get("A2")).toString());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetNettingExchangeRateCase2()
    {
        final String STR_METHOD_NAME = "testGetNettingExchangeRateCase2()";
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
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(31102102050L);
            l_subAccountParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(FeqFinTransactionRow.TYPE);
            FeqFinTransactionParams l_feqFinTransactionParams1 = TestDBUtility.getFeqFinTransactionParams();
            l_feqFinTransactionParams1.setFinTransactionId(1L);
            l_feqFinTransactionParams1.setOrderUnitId(1L);
            l_feqFinTransactionParams1.setAccountId(31102102050L);
            l_feqFinTransactionParams1.setSubAccountId(33381251220301L);
            l_feqFinTransactionParams1.setCurrencyCode("A1");
            l_feqFinTransactionParams1.setFinTransactionType(FinTransactionType.EQTYPE_FEQ_BUY);
            l_feqFinTransactionParams1.setBalanceAmountFc(125);
            TestDBUtility.insertWithDel(l_feqFinTransactionParams1);
            FeqFinTransactionParams l_feqFinTransactionParams2 = TestDBUtility.getFeqFinTransactionParams();
            l_feqFinTransactionParams2.setFinTransactionId(2L);
            l_feqFinTransactionParams2.setOrderUnitId(2L);
            l_feqFinTransactionParams2.setAccountId(31102102050L);
            l_feqFinTransactionParams2.setSubAccountId(33381251220301L);
            l_feqFinTransactionParams2.setCurrencyCode("A1");
            l_feqFinTransactionParams2.setFinTransactionType(FinTransactionType.EQTYPE_FEQ_SELL);
            l_feqFinTransactionParams2.setBalanceAmountFc(125);
            TestDBUtility.insertWithDel(l_feqFinTransactionParams2);
            FeqFinTransactionParams l_feqFinTransactionParams3 = TestDBUtility.getFeqFinTransactionParams();
            l_feqFinTransactionParams3.setFinTransactionId(3L);
            l_feqFinTransactionParams3.setOrderUnitId(2L);
            l_feqFinTransactionParams3.setAccountId(31102102050L);
            l_feqFinTransactionParams3.setSubAccountId(33381251220301L);
            l_feqFinTransactionParams3.setCurrencyCode("A1");
            l_feqFinTransactionParams3.setFinTransactionType(FinTransactionType.EQTYPE_FEQ_SELL);
            l_feqFinTransactionParams3.setBalanceAmountFc(125);
            TestDBUtility.insertWithDel(l_feqFinTransactionParams3);
            FeqFinTransactionParams l_feqFinTransactionParams4 = TestDBUtility.getFeqFinTransactionParams();
            l_feqFinTransactionParams4.setFinTransactionId(4L);
            l_feqFinTransactionParams4.setOrderUnitId(3L);
            l_feqFinTransactionParams4.setAccountId(31102102050L);
            l_feqFinTransactionParams4.setSubAccountId(33381251220301L);
            l_feqFinTransactionParams4.setCurrencyCode("A2");
            l_feqFinTransactionParams4.setFinTransactionType(FinTransactionType.EQTYPE_FEQ_SELL);
            l_feqFinTransactionParams4.setBalanceAmountFc(25);
            TestDBUtility.insertWithDel(l_feqFinTransactionParams4);
            FeqFinTransactionParams l_feqFinTransactionParams5 = TestDBUtility.getFeqFinTransactionParams();
            l_feqFinTransactionParams5.setFinTransactionId(5L);
            l_feqFinTransactionParams5.setOrderUnitId(4L);
            l_feqFinTransactionParams5.setAccountId(31102102050L);
            l_feqFinTransactionParams5.setSubAccountId(33381251220301L);
            l_feqFinTransactionParams5.setCurrencyCode("A2");
            l_feqFinTransactionParams5.setFinTransactionType(FinTransactionType.EQTYPE_FEQ_BUY);
            l_feqFinTransactionParams5.setBalanceAmountFc(125);
            TestDBUtility.insertWithDel(l_feqFinTransactionParams5);
            FeqFinTransactionParams l_feqFinTransactionParams6 = TestDBUtility.getFeqFinTransactionParams();
            l_feqFinTransactionParams6.setFinTransactionId(6L);
            l_feqFinTransactionParams6.setOrderUnitId(4L);
            l_feqFinTransactionParams6.setAccountId(31102102050L);
            l_feqFinTransactionParams6.setSubAccountId(33381251220301L);
            l_feqFinTransactionParams6.setCurrencyCode("A3");
            l_feqFinTransactionParams6.setFinTransactionType(FinTransactionType.EQTYPE_FEQ_SELL);
            l_feqFinTransactionParams6.setBalanceAmountFc(125);
            TestDBUtility.insertWithDel(l_feqFinTransactionParams6);
            FeqFinTransactionParams l_feqFinTransactionParams7 = TestDBUtility.getFeqFinTransactionParams();
            l_feqFinTransactionParams7.setFinTransactionId(7L);
            l_feqFinTransactionParams7.setOrderUnitId(4L);
            l_feqFinTransactionParams7.setAccountId(31102102050L);
            l_feqFinTransactionParams7.setSubAccountId(33381251220301L);
            l_feqFinTransactionParams7.setCurrencyCode("A3");
            l_feqFinTransactionParams7.setFinTransactionType(FinTransactionType.EQTYPE_FEQ_SELL);
            l_feqFinTransactionParams7.setBalanceAmountFc(125);
            TestDBUtility.insertWithDel(l_feqFinTransactionParams7);
            
            TestDBUtility.deleteAll(GenCurrencyParams.TYPE);
            GenCurrencyParams l_currencyParams = TestDBUtility.getGenCurrencyRow();
            l_currencyParams.setInstitutionCode("0D");
            l_currencyParams.setCurrencyCode("A1");
            l_currencyParams.setCurrentSellExecRate(111.1);
            l_currencyParams.setCurrentBuyExecRate(110.0);
            TestDBUtility.insertWithDel(l_currencyParams);
            GenCurrencyParams l_currencyParams1 = TestDBUtility.getGenCurrencyRow();
            l_currencyParams1.setInstitutionCode("0D");
            l_currencyParams1.setCurrencyCode("A2");
            l_currencyParams1.setCurrentSellExecRate(222.2);
            l_currencyParams1.setCurrentBuyExecRate(220.0);
            TestDBUtility.insertWithDel(l_currencyParams1);
            
            long[] l_lngOrderUnitIDs = new long[4];
            l_lngOrderUnitIDs[0] = 1L;
            l_lngOrderUnitIDs[1] = 2L;
            l_lngOrderUnitIDs[2] = 3L;
            l_lngOrderUnitIDs[3] = 4L;
            WEB3FeqPositionManagerHelper l_feqPositionManagerHelper =
                new WEB3FeqPositionManagerHelper(ProductTypeEnum.FOREIGN_EQUITY);
            PersistentDataManager l_PersistentDataManager = l_feqPositionManagerHelper.getPersistenceManager();
            WEB3FeqPositionManagerHelper.WEB3FeqUpdateDataManager l_dataManager = 
                (WEB3FeqPositionManagerHelper.WEB3FeqUpdateDataManager)l_PersistentDataManager;
            List l_lisFinTransactionForLumpObj = l_dataManager.getFinTransactionForLumpObj(l_lngOrderUnitIDs);
            
            HashMap l_hmNettingExchangeRate = l_provider.getNettingExchangeRate(l_lisFinTransactionForLumpObj);
            
            assertEquals("111.1", ((Double)l_hmNettingExchangeRate.get("A1")).toString());
            assertEquals("220.0", ((Double)l_hmNettingExchangeRate.get("A2")).toString());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetNettingExchangeRateCase3()
    {
        final String STR_METHOD_NAME = "testGetNettingExchangeRateCase3()";
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
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(31102102050L);
            l_subAccountParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(FeqFinTransactionRow.TYPE);
            FeqFinTransactionParams l_feqFinTransactionParams1 = TestDBUtility.getFeqFinTransactionParams();
            l_feqFinTransactionParams1.setFinTransactionId(1L);
            l_feqFinTransactionParams1.setOrderUnitId(1L);
            l_feqFinTransactionParams1.setAccountId(31102102050L);
            l_feqFinTransactionParams1.setSubAccountId(33381251220301L);
            l_feqFinTransactionParams1.setCurrencyCode("A1");
            l_feqFinTransactionParams1.setFinTransactionType(FinTransactionType.EQTYPE_FEQ_BUY);
            l_feqFinTransactionParams1.setBalanceAmountFc(125);
            TestDBUtility.insertWithDel(l_feqFinTransactionParams1);
            FeqFinTransactionParams l_feqFinTransactionParams2 = TestDBUtility.getFeqFinTransactionParams();
            l_feqFinTransactionParams2.setFinTransactionId(2L);
            l_feqFinTransactionParams2.setOrderUnitId(2L);
            l_feqFinTransactionParams2.setAccountId(31102102050L);
            l_feqFinTransactionParams2.setSubAccountId(33381251220301L);
            l_feqFinTransactionParams2.setCurrencyCode("A1");
            l_feqFinTransactionParams2.setFinTransactionType(FinTransactionType.EQTYPE_FEQ_BUY);
            l_feqFinTransactionParams2.setBalanceAmountFc(125);
            TestDBUtility.insertWithDel(l_feqFinTransactionParams2);
            FeqFinTransactionParams l_feqFinTransactionParams3 = TestDBUtility.getFeqFinTransactionParams();
            l_feqFinTransactionParams3.setFinTransactionId(3L);
            l_feqFinTransactionParams3.setOrderUnitId(2L);
            l_feqFinTransactionParams3.setAccountId(31102102050L);
            l_feqFinTransactionParams3.setSubAccountId(33381251220301L);
            l_feqFinTransactionParams3.setCurrencyCode("A1");
            l_feqFinTransactionParams3.setFinTransactionType(FinTransactionType.EQTYPE_FEQ_BUY);
            l_feqFinTransactionParams3.setBalanceAmountFc(125);
            TestDBUtility.insertWithDel(l_feqFinTransactionParams3);
            FeqFinTransactionParams l_feqFinTransactionParams4 = TestDBUtility.getFeqFinTransactionParams();
            l_feqFinTransactionParams4.setFinTransactionId(4L);
            l_feqFinTransactionParams4.setOrderUnitId(3L);
            l_feqFinTransactionParams4.setAccountId(31102102050L);
            l_feqFinTransactionParams4.setSubAccountId(33381251220301L);
            l_feqFinTransactionParams4.setCurrencyCode("A2");
            l_feqFinTransactionParams4.setFinTransactionType(FinTransactionType.EQTYPE_FEQ_SELL);
            l_feqFinTransactionParams4.setBalanceAmountFc(125);
            TestDBUtility.insertWithDel(l_feqFinTransactionParams4);
            FeqFinTransactionParams l_feqFinTransactionParams5 = TestDBUtility.getFeqFinTransactionParams();
            l_feqFinTransactionParams5.setFinTransactionId(5L);
            l_feqFinTransactionParams5.setOrderUnitId(4L);
            l_feqFinTransactionParams5.setAccountId(31102102050L);
            l_feqFinTransactionParams5.setSubAccountId(33381251220301L);
            l_feqFinTransactionParams5.setCurrencyCode("A2");
            l_feqFinTransactionParams5.setFinTransactionType(FinTransactionType.EQTYPE_FEQ_SELL);
            l_feqFinTransactionParams5.setBalanceAmountFc(125);
            TestDBUtility.insertWithDel(l_feqFinTransactionParams5);
            FeqFinTransactionParams l_feqFinTransactionParams6 = TestDBUtility.getFeqFinTransactionParams();
            l_feqFinTransactionParams6.setFinTransactionId(6L);
            l_feqFinTransactionParams6.setOrderUnitId(4L);
            l_feqFinTransactionParams6.setAccountId(31102102050L);
            l_feqFinTransactionParams6.setSubAccountId(33381251220301L);
            l_feqFinTransactionParams6.setCurrencyCode("A3");
            l_feqFinTransactionParams6.setFinTransactionType(FinTransactionType.EQTYPE_FEQ_SELL);
            l_feqFinTransactionParams6.setBalanceAmountFc(125);
            TestDBUtility.insertWithDel(l_feqFinTransactionParams6);
            FeqFinTransactionParams l_feqFinTransactionParams7 = TestDBUtility.getFeqFinTransactionParams();
            l_feqFinTransactionParams7.setFinTransactionId(7L);
            l_feqFinTransactionParams7.setOrderUnitId(4L);
            l_feqFinTransactionParams7.setAccountId(31102102050L);
            l_feqFinTransactionParams7.setSubAccountId(33381251220301L);
            l_feqFinTransactionParams7.setCurrencyCode("A3");
            l_feqFinTransactionParams7.setFinTransactionType(FinTransactionType.EQTYPE_FEQ_SELL);
            l_feqFinTransactionParams7.setBalanceAmountFc(125);
            TestDBUtility.insertWithDel(l_feqFinTransactionParams7);
            
            TestDBUtility.deleteAll(GenCurrencyParams.TYPE);
            GenCurrencyParams l_currencyParams = TestDBUtility.getGenCurrencyRow();
            l_currencyParams.setInstitutionCode("0D");
            l_currencyParams.setCurrencyCode("A1");
            l_currencyParams.setCurrentSellExecRate(111.1);
            l_currencyParams.setCurrentBuyExecRate(110.0);
            TestDBUtility.insertWithDel(l_currencyParams);
            GenCurrencyParams l_currencyParams1 = TestDBUtility.getGenCurrencyRow();
            l_currencyParams1.setInstitutionCode("0D");
            l_currencyParams1.setCurrencyCode("A2");
            l_currencyParams1.setCurrentSellExecRate(222.2);
            l_currencyParams1.setCurrentBuyExecRate(220.0);
            TestDBUtility.insertWithDel(l_currencyParams1);
            
            long[] l_lngOrderUnitIDs = new long[4];
            l_lngOrderUnitIDs[0] = 1L;
            l_lngOrderUnitIDs[1] = 2L;
            l_lngOrderUnitIDs[2] = 3L;
            l_lngOrderUnitIDs[3] = 4L;
            WEB3FeqPositionManagerHelper l_feqPositionManagerHelper =
                new WEB3FeqPositionManagerHelper(ProductTypeEnum.FOREIGN_EQUITY);
            PersistentDataManager l_PersistentDataManager = l_feqPositionManagerHelper.getPersistenceManager();
            WEB3FeqPositionManagerHelper.WEB3FeqUpdateDataManager l_dataManager = 
                (WEB3FeqPositionManagerHelper.WEB3FeqUpdateDataManager)l_PersistentDataManager;
            List l_lisFinTransactionForLumpObj = l_dataManager.getFinTransactionForLumpObj(l_lngOrderUnitIDs);
            
            HashMap l_hmNettingExchangeRate = l_provider.getNettingExchangeRate(l_lisFinTransactionForLumpObj);
            assertTrue(l_hmNettingExchangeRate.isEmpty());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCalcAllAmount()
    {
        final String STR_METHOD_NAME = "testCalcAllAmount()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //InstitutionRow
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);
            
            
            TestDBUtility.deleteAll(FeqFinTransactionRow.TYPE);
            FeqFinTransactionParams l_feqFinTransactionParams = new FeqFinTransactionParams();
            l_feqFinTransactionParams.setFinTransactionId(2L);
            l_feqFinTransactionParams.setAccountId(1L);
            l_feqFinTransactionParams.setOrderUnitId(1L);
            l_feqFinTransactionParams.setSubAccountId(1L);
            l_feqFinTransactionParams.setProductId(1L);
            l_feqFinTransactionParams.setFinTransactionType(FinTransactionType.EQTYPE_FEQ_SELL);
            l_feqFinTransactionParams.setFinTransactionCateg(FinTransactionCateg.EQTYPE_ASSET);
            l_feqFinTransactionParams.setTaxType(TaxTypeEnum.UNDEFINED);
            l_feqFinTransactionParams.setBizDate("20070506");
            l_feqFinTransactionParams.setDeliveryDate(WEB3DateUtility.getDate("20070506", "yyyyMMdd"));
            l_feqFinTransactionParams.setCurrencyCode("12");
            l_feqFinTransactionParams.setNetAmount(2D);
            l_feqFinTransactionParams.setNetAmountFc(1D);
            l_feqFinTransactionParams.setFxRate(0.5D);
            l_feqFinTransactionParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            l_feqFinTransactionParams.setQuantity(1D);
            l_feqFinTransactionParams.setCommissionFee(0D);
            l_feqFinTransactionParams.setCommissionFeeTax(0D);
            l_feqFinTransactionParams.setCommissionFeeTaxFc(0D);
            l_feqFinTransactionParams.setCommissionFeeFc(0D);
            l_feqFinTransactionParams.setBalanceAmountFc(0D);
            l_feqFinTransactionParams.setForeignCommissionFee(0D);
            l_feqFinTransactionParams.setForeignTax(0D);
            l_feqFinTransactionParams.setForeignFeeExt1(0D);
            l_feqFinTransactionParams.setRegNo("2");
            l_feqFinTransactionParams.setForeignFeeExt2(0D);
            l_feqFinTransactionParams.setCapitalGain(0D);
            l_feqFinTransactionParams.setCapitalGainFc(0D);
            l_feqFinTransactionParams.setCapitalGainTax(0D);
            l_feqFinTransactionParams.setCapitalGainTaxFc(0D);
            l_feqFinTransactionParams.setDeleteFlag(BooleanEnum.FALSE);
            l_feqFinTransactionParams.setFinTransactionTimestamp(WEB3DateUtility.getDate("20071106", "yyyyMMdd"));
            l_feqFinTransactionParams.setCreatedTimestamp(WEB3DateUtility.getDate("20071106", "yyyyMMdd"));
            l_feqFinTransactionParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071106", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_feqFinTransactionParams);
            
            TestDBUtility.deleteAll(FeqProductParams.TYPE);
            FeqProductParams l_feqProductParams = TestDBUtility.getFeqProductRow();
            l_feqProductParams.setProductId(l_feqFinTransactionParams.getProductId());
            TestDBUtility.insertWithDel(l_feqProductParams);
            
            TestDBUtility.deleteAll(GenCurrencyParams.TYPE);
            GenCurrencyParams l_currencyParams = TestDBUtility.getGenCurrencyRow();
            l_currencyParams.setInstitutionCode(l_feqProductParams.getInstitutionCode());
            l_currencyParams.setCurrencyCode(l_feqProductParams.getCurrencyCode());
            TestDBUtility.insertWithDel(l_currencyParams);
            
            //ProductParams
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(l_feqProductParams.getProductId());
            TestDBUtility.insertWithDel(l_productParams);
            
            int l_intMax = 10;
            String l_strMarketCode = "3001";
            double[] l_dblTradePrices = new double[]{1};
            double[] l_dblTradePriceFcs = new double[]{1};
            WEB3FeqAmountCalcResult l_calcResult = new WEB3FeqAmountCalcResult();
            double l_dblTradePriceFcTotal = 1D;
            int l_intDecimalPlace = 1;
            FeqFinTransactionParams[] l_feqFinTransactionParamss = new FeqFinTransactionParams[1];
            l_feqFinTransactionParamss[0] = l_feqFinTransactionParams;
            WEB3FeqProduct l_feqProduct = new WEB3FeqProduct(l_feqProductParams);
            Date l_ExecDate = WEB3DateUtility.getDate("20070504", "yyyyMMdd");
            double[] l_amountTotals = new double[]{1,2,3,4,5,6,7,8,9,10};
            l_provider.calcAllAmount(
                l_intMax,
                l_strMarketCode,
                l_dblTradePrices,
                l_dblTradePriceFcs,
                l_calcResult,
                l_dblTradePriceFcTotal,
                l_intDecimalPlace,
                l_feqFinTransactionParamss,
                l_feqProduct,
                l_ExecDate,
                l_amountTotals);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCalcFeqAmountTotal()
    {
        final String STR_METHOD_NAME = "testCalcAllAmount()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(FeqFinTransactionRow.TYPE);
            FeqFinTransactionParams l_feqFinTransactionParams = new FeqFinTransactionParams();
            l_feqFinTransactionParams.setFinTransactionId(2L);
            l_feqFinTransactionParams.setAccountId(1L);
            l_feqFinTransactionParams.setOrderUnitId(1L);
            l_feqFinTransactionParams.setSubAccountId(1L);
            l_feqFinTransactionParams.setProductId(1L);
            l_feqFinTransactionParams.setFinTransactionType(FinTransactionType.EQTYPE_FEQ_SELL);
            l_feqFinTransactionParams.setFinTransactionCateg(FinTransactionCateg.EQTYPE_ASSET);
            l_feqFinTransactionParams.setTaxType(TaxTypeEnum.UNDEFINED);
            l_feqFinTransactionParams.setBizDate("20070506");
            l_feqFinTransactionParams.setDeliveryDate(WEB3DateUtility.getDate("20070506", "yyyyMMdd"));
            l_feqFinTransactionParams.setCurrencyCode("12");
            l_feqFinTransactionParams.setNetAmount(2D);
            l_feqFinTransactionParams.setNetAmountFc(1D);
            l_feqFinTransactionParams.setFxRate(0.5D);
            l_feqFinTransactionParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            l_feqFinTransactionParams.setQuantity(1D);
            l_feqFinTransactionParams.setCommissionFee(0D);
            l_feqFinTransactionParams.setCommissionFeeTax(0D);
            l_feqFinTransactionParams.setCommissionFeeTaxFc(0D);
            l_feqFinTransactionParams.setCommissionFeeFc(0D);
            l_feqFinTransactionParams.setBalanceAmountFc(0D);
            l_feqFinTransactionParams.setForeignCommissionFee(0D);
            l_feqFinTransactionParams.setForeignTax(0D);
            l_feqFinTransactionParams.setForeignFeeExt1(0D);
            l_feqFinTransactionParams.setRegNo("2");
            l_feqFinTransactionParams.setForeignFeeExt2(0D);
            l_feqFinTransactionParams.setCapitalGain(0D);
            l_feqFinTransactionParams.setCapitalGainFc(0D);
            l_feqFinTransactionParams.setCapitalGainTax(0D);
            l_feqFinTransactionParams.setCapitalGainTaxFc(0D);
            l_feqFinTransactionParams.setDeleteFlag(BooleanEnum.FALSE);
            l_feqFinTransactionParams.setFinTransactionTimestamp(WEB3DateUtility.getDate("20071106", "yyyyMMdd"));
            l_feqFinTransactionParams.setCreatedTimestamp(WEB3DateUtility.getDate("20071106", "yyyyMMdd"));
            l_feqFinTransactionParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071106", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_feqFinTransactionParams);
            
            //FeqOrderUnit
            TestDBUtility.deleteAll(FeqOrderUnitParams.TYPE);
            FeqOrderUnitParams l_feqOrderUnitParams = TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitParams.setOrderUnitId(1L);
            TestDBUtility.insertWithDel(l_feqOrderUnitParams);
            
            
            TestDBUtility.deleteAll(FeqProductParams.TYPE);
            FeqProductParams l_feqProductParams = TestDBUtility.getFeqProductRow();
            l_feqProductParams.setProductId(l_feqFinTransactionParams.getProductId());
            TestDBUtility.insertWithDel(l_feqProductParams);
            
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(l_feqFinTransactionParams.getMarketId());
            TestDBUtility.insertWithDel(l_marketParams);
            
            //ProductParams
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(l_feqProductParams.getProductId());
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(l_feqFinTransactionParams.getAccountId());
            l_subAccountParams.setSubAccountId(l_feqFinTransactionParams.getSubAccountId());
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            WEB3FeqProduct l_feqProduct = new WEB3FeqProduct(l_feqProductParams);
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = 
                l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
            WEB3FeqOrderManager l_orderManager = 
                (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
            WEB3GentradeAccountManager l_accountManager = 
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            WEB3GentradeFinObjectManager l_finObjectManager = 
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            WEB3GentradeMarket l_market = 
                (WEB3GentradeMarket)l_finObjectManager.getMarket(l_feqFinTransactionParams.getMarketId());
            
            Date l_datBaseDate = WEB3DateUtility.getDate("20070123", "yyyyMMdd");
            Date l_ExecDate = WEB3DateUtility.getDate("20070123", "yyyyMMdd");
            double l_dblTradePriceFcTotal = 12D;
            WEB3FeqBizLogicProviderForTest l_forTest = new WEB3FeqBizLogicProviderForTest();
            l_forTest.calcFeqAmountTotal(l_feqFinTransactionParams, 
                l_feqProduct,
                l_orderManager,
                l_accountManager,
                l_market,
                l_datBaseDate,
                l_ExecDate,
                l_dblTradePriceFcTotal);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
    }

    public class WEB3FeqBizLogicProviderForTest extends WEB3FeqBizLogicProvider
    {
        public WEB3FeqAmountCalcResult calcFeqAmount(
                WEB3GentradeSubAccount l_subAccount,
                WEB3FeqProduct l_feqProduct,
                WEB3GentradeMarket l_market,
                Date l_datBaseDate,
                Date l_datExecDate,
                double l_dblTradePriceFc,
                double l_dblFxRate,
                boolean l_blnIsBuy,
                boolean l_blnIsExecCalc,
                boolean l_blnIsLimitPrice,
                String l_strOrderChannel) throws WEB3BaseException
            {
                WEB3FeqAmountCalcResult l_result = new WEB3FeqAmountCalcResult();
                return l_result;
            }
    }

    public void testCalcBookValuePrice()
    {
        final String STR_METHOD_NAME = "testCalcBookValuePrice()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3FeqBizLogicProvider l_provider = new WEB3FeqBizLogicProvider();

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(1001L);
            l_subAccountParams.setSubAccountId(2002L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(AssetParams.TYPE);
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            l_assetParams.setAccountId(1001);
            l_assetParams.setSubAccountId(2002);
            l_assetParams.setProductId(1001);
            l_assetParams.setTaxType(TaxTypeEnum.NORMAL);
            l_assetParams.setMiniStockDiv(WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);
            l_assetParams.setQuantityForBookValue(6);
            l_assetParams.setBookValue(4);
            TestDBUtility.insertWithDel(l_assetParams);

            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);
            TaxTypeEnum l_taxType = TaxTypeEnum.NORMAL;
            BigDecimal l_bdResult =
                l_provider.calcBookValuePrice(l_subAccount, 1001l, l_taxType, 16);
            log.debug(String.valueOf(l_bdResult));
            assertEquals(new BigDecimal("0.6666666666666667"), l_bdResult);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
    }

    public void testCalcEstimatedBookValuePrice()
    {
        final String STR_METHOD_NAME = "testCalcEstimatedBookValuePrice()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3FeqBizLogicProvider l_provider = new WEB3FeqBizLogicProvider();

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(1001L);
            l_subAccountParams.setSubAccountId(2002L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(AssetParams.TYPE);
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            l_assetParams.setAccountId(1001);
            l_assetParams.setSubAccountId(2002);
            l_assetParams.setProductId(1001);
            l_assetParams.setTaxType(TaxTypeEnum.NORMAL);
            l_assetParams.setMiniStockDiv(WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);
            l_assetParams.setQuantityForBookValue(3);
            l_assetParams.setBookValue(1);
            TestDBUtility.insertWithDel(l_assetParams);

            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);
            TaxTypeEnum l_taxType = TaxTypeEnum.NORMAL;
            BigDecimal l_dblResult =
                l_provider.calcEstimatedBookValuePrice(l_subAccount, 1001l, l_taxType);
            log.debug(String.valueOf(l_dblResult));
            assertEquals(new BigDecimal("0.33333"), l_dblResult);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
    }

    public void testCalcEstimatedBookValuePrice1()
    {
        final String STR_METHOD_NAME = "testCalcEstimatedBookValuePrice1()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3FeqBizLogicProvider l_provider = new WEB3FeqBizLogicProvider();

            BigDecimal l_dblResult =
                l_provider.calcEstimatedBookValuePrice(2, 3);
            log.debug(String.valueOf(l_dblResult));
            assertEquals(new BigDecimal("0.666666666666666667"), l_dblResult);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
    }

    public void testCalcForeignCCYAmount()
    {
        final String STR_METHOD_NAME = "testCalcForeignCCYAmount()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3FeqBizLogicProvider l_provider = new WEB3FeqBizLogicProvider();

            BigDecimal l_bdAmount = new BigDecimal("2");
            String l_strChangeFccyRoundDiv = WEB3ChangeRoundDivDef.ROUNDING_OFF;
            l_provider.calcForeignCCYAmount(l_bdAmount, 0, 1, l_strChangeFccyRoundDiv);
            fail();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.SYSTEM_ERROR_80017);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
    }

    public void testCalcForeignCCYAmount1()
    {
        final String STR_METHOD_NAME = "testCalcForeignCCYAmount1()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3FeqBizLogicProvider l_provider = new WEB3FeqBizLogicProvider();

            BigDecimal l_bdAmount = new BigDecimal("2");
            l_provider.calcForeignCCYAmount(l_bdAmount, 1, 1, "3");
            fail();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.SYSTEM_ERROR_80017);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
    }

    public void testCalcForeignCCYAmount2()
    {
        final String STR_METHOD_NAME = "testCalcForeignCCYAmount2()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3FeqBizLogicProvider l_provider = new WEB3FeqBizLogicProvider();

            BigDecimal l_bdAmount = new BigDecimal("2");
            String l_strChangeFccyRoundDiv = WEB3ChangeRoundDivDef.ROUNDING_OFF;
            BigDecimal l_bdResult =
                l_provider.calcForeignCCYAmount(l_bdAmount, 3, 2, l_strChangeFccyRoundDiv);
            assertEquals(l_bdResult, new BigDecimal("0.67"));
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
    }

    public void testCalcForeignCCYAmount3()
    {
        final String STR_METHOD_NAME = "testCalcForeignCCYAmount3()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3FeqBizLogicProvider l_provider = new WEB3FeqBizLogicProvider();

            BigDecimal l_bdAmount = new BigDecimal("2");
            String l_strChangeFccyRoundDiv = WEB3ChangeRoundDivDef.CUTTING_OFF;
            BigDecimal l_bdResult =
                l_provider.calcForeignCCYAmount(l_bdAmount, 3, 2, l_strChangeFccyRoundDiv);
            assertEquals(l_bdResult, new BigDecimal("0.66"));
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
    }

    public void testCalcForeignCCYAmount4()
    {
        final String STR_METHOD_NAME = "testCalcForeignCCYAmount4()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3FeqBizLogicProvider l_provider = new WEB3FeqBizLogicProvider();

            BigDecimal l_bdAmount = new BigDecimal("1");
            String l_strChangeFccyRoundDiv = WEB3ChangeRoundDivDef.RAISING_TO_A_UNIT;
            BigDecimal l_bdResult =
                l_provider.calcForeignCCYAmount(l_bdAmount, 3, 2, l_strChangeFccyRoundDiv);
            assertEquals(l_bdResult, new BigDecimal("0.34"));
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    public void testCalcForeignCost_CASE001()
    {
        final String STR_METHOD_NAME = "testCalcForeignCost_CASE001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(ForeignCostParams.TYPE);
            ForeignCostParams l_params = this.getForeignCostParams();
            l_params.setAddAmount(333.335);
            l_params.setScale(2);
            l_params.setRoundDiv("0");
            l_params.setSideDiv("2");
            TestDBUtility.insertWithDel(l_params);
            
            WEB3FeqBizLogicProvider l_provider = new WEB3FeqBizLogicProvider();
            WEB3FeqForeignCost l_cost = l_provider.calcForeignCost(new Long(888), "123456789", "0D", "11", 123d,
                WEB3DateUtility.getDate("20080203", "yyyyMMdd"), false);
            assertEquals(702.4, l_cost.getForeignFeeExt1(), 0);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
   
    public void testCalcForeignCost_CASE003()
    {
        final String STR_METHOD_NAME = "testCalcForeignCost_CASE003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(ForeignCostParams.TYPE);
            TestDBUtility.deleteAll(MarketParams.TYPE);
            TestDBUtility.deleteAll(MarketPreferencesRow.TYPE);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("11");
            l_marketParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_marketParams);

            ForeignCostParams l_params = this.getForeignCostParams();
            l_params.setAddAmount(333.33512);
            l_params.setScale(2);
            l_params.setRoundDiv("1");
            l_params.setSideDiv("1");
            l_params.setCommisionRate(24.5);
            TestDBUtility.insertWithDel(l_params);
            
            ForeignCostParams l_params2 = this.getForeignCostParams();
            l_params2.setAddAmount(333.33512);
            l_params2.setScale(2);
            l_params2.setRoundDiv("1");
            l_params2.setSideDiv("2");
            l_params2.setCommisionRate(14.5);
            TestDBUtility.insertWithDel(l_params2);
            
            WEB3FeqBizLogicProvider l_provider = new WEB3FeqBizLogicProvider();
            WEB3FeqForeignCost l_cost = l_provider.calcForeignCost(new Long(888), "123456789", "0D", "11", 123d,
                WEB3DateUtility.getDate("20080203", "yyyyMMdd"), true);
            assertEquals(363.47, l_cost.getForeignFeeExt1(), 0);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCalcForeignCost_CASE004()
    {
        final String STR_METHOD_NAME = "testCalcForeignCost_CASE004()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(ForeignCostParams.TYPE);
            TestDBUtility.deleteAll(MarketParams.TYPE);
            TestDBUtility.deleteAll(MarketPreferencesRow.TYPE);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("11");
            l_marketParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_marketParams);

            ForeignCostParams l_params = this.getForeignCostParams();
            l_params.setAddAmount(333.33512);
            l_params.setScale(2);
            l_params.setRoundDiv("1");
            l_params.setSideDiv("1");
            l_params.setCommisionRate(24.5);
            TestDBUtility.insertWithDel(l_params);
            
            ForeignCostParams l_params2 = this.getForeignCostParams();
            l_params2.setAddAmount(333.33512);
            l_params2.setScale(2);
            l_params2.setRoundDiv("1");
            l_params2.setSideDiv("2");
            l_params2.setCommisionRate(14.5);
            TestDBUtility.insertWithDel(l_params2);
            
            WEB3FeqBizLogicProvider l_provider = new WEB3FeqBizLogicProvider();
            WEB3FeqForeignCost l_cost = l_provider.calcForeignCost(new Long(888), "123456789", "0D", "11", 123d,
                WEB3DateUtility.getDate("20080203", "yyyyMMdd"), false);
            assertEquals(351.17, l_cost.getForeignFeeExt1(), 0);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCalcForeignCost_CASE005()
    {
        final String STR_METHOD_NAME = "testCalcForeignCost_CASE005()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(ForeignCostParams.TYPE);
            TestDBUtility.deleteAll(MarketParams.TYPE);
            TestDBUtility.deleteAll(MarketPreferencesRow.TYPE);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("11");
            l_marketParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_marketParams);
            
            MarketPreferencesParams l_marketPreferencesParams =
                TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesParams.setMarketId(l_marketParams.getMarketId());
            l_marketPreferencesParams.setName("feq.buy.commision.rate");
            l_marketPreferencesParams.setNameSerialNo(1);
            l_marketPreferencesParams.setValue("10");
            TestDBUtility.insertWithDel(l_marketPreferencesParams);

            ForeignCostParams l_params = this.getForeignCostParams();
            l_params.setAddAmount(333.33512);
            l_params.setScale(2);
            l_params.setRoundDiv("1");
            l_params.setSideDiv("1");
            l_params.setCommisionRate(24.5);
            TestDBUtility.insertWithDel(l_params);
            
            WEB3FeqBizLogicProvider l_provider = new WEB3FeqBizLogicProvider();
            WEB3FeqForeignCost l_cost = l_provider.calcForeignCost(new Long(888), "123456789", "0D", "11", 123d,
                WEB3DateUtility.getDate("20080203", "yyyyMMdd"), true);
            assertEquals(363.47, l_cost.getForeignFeeExt1(), 0);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCalcForeignCost_CASE006()
    {
        final String STR_METHOD_NAME = "testCalcForeignCost_CASE006()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(ForeignCostParams.TYPE);
            TestDBUtility.deleteAll(MarketParams.TYPE);
            TestDBUtility.deleteAll(MarketPreferencesRow.TYPE);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("11");
            l_marketParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_marketParams);
            
            MarketPreferencesParams l_marketPreferencesParams =
                TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesParams.setMarketId(l_marketParams.getMarketId());
            l_marketPreferencesParams.setName("feq.buy.commision.rate");
            l_marketPreferencesParams.setNameSerialNo(1);
            l_marketPreferencesParams.setValue("10");
            TestDBUtility.insertWithDel(l_marketPreferencesParams);

            ForeignCostParams l_params = this.getForeignCostParams();
            l_params.setAddAmount(333.33512);
            l_params.setScale(2);
            l_params.setRoundDiv("2");
            l_params.setSideDiv("1");
            l_params.setCommisionRate(24.5);
            TestDBUtility.insertWithDel(l_params);
            
            WEB3FeqBizLogicProvider l_provider = new WEB3FeqBizLogicProvider();
            WEB3FeqForeignCost l_cost = l_provider.calcForeignCost(new Long(888), "123456789", "0D", "11", 123d,
                WEB3DateUtility.getDate("20080203", "yyyyMMdd"), true);
            assertEquals(363.48, l_cost.getForeignFeeExt1(), 0);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetForeignCost_CASE001()
    {
        final String STR_METHOD_NAME = "testGetForeignCost_CASE001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(ForeignCostParams.TYPE);
            TestDBUtility.deleteAll(MarketParams.TYPE);
            TestDBUtility.deleteAll(MarketPreferencesRow.TYPE);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("11");
            l_marketParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_marketParams);

            ForeignCostParams l_params = this.getForeignCostParams();
            l_params.setAddAmount(333.33512);
            l_params.setScale(2);
            l_params.setRoundDiv("1");
            l_params.setSideDiv("1");
            l_params.setCommisionRate(24.5);
            TestDBUtility.insertWithDel(l_params);
            
            ForeignCostParams l_params2 = this.getForeignCostParams();
            l_params2.setAddAmount(333.33512);
            l_params2.setScale(2);
            l_params2.setRoundDiv("1");
            l_params2.setSideDiv("2");
            l_params2.setCommisionRate(24.5);
            TestDBUtility.insertWithDel(l_params2);
            
            WEB3FeqBizLogicProvider l_provider = new WEB3FeqBizLogicProvider();
            ForeignCostParams[] l_cost = l_provider.getForeignCost(new Long(888), "123456789", "0D", "11", 123d,
                WEB3DateUtility.getDate("20080203", "yyyyMMdd"),null,"1");
            assertEquals(1, l_cost.length);
            assertEquals("1", l_cost[0].getSideDiv());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetForeignCost_CASE002()
    {
        final String STR_METHOD_NAME = "testGetForeignCost_CASE002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(ForeignCostParams.TYPE);
            TestDBUtility.deleteAll(MarketParams.TYPE);
            TestDBUtility.deleteAll(MarketPreferencesRow.TYPE);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("11");
            l_marketParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_marketParams);

            ForeignCostParams l_params = this.getForeignCostParams();
            l_params.setAddAmount(333.33512);
            l_params.setScale(2);
            l_params.setRoundDiv("1");
            l_params.setSideDiv("1");
            l_params.setCommisionRate(24.5);
            TestDBUtility.insertWithDel(l_params);
            
            ForeignCostParams l_params2 = this.getForeignCostParams();
            l_params2.setAddAmount(333.33512);
            l_params2.setScale(2);
            l_params2.setRoundDiv("1");
            l_params2.setSideDiv("2");
            l_params2.setCostDiv("12");
            l_params2.setCommisionRate(24.5);
            TestDBUtility.insertWithDel(l_params2);
            
            WEB3FeqBizLogicProvider l_provider = new WEB3FeqBizLogicProvider();
            ForeignCostParams[] l_cost = l_provider.getForeignCost(new Long(888), "123456789", "0D", "11", 123d,
                WEB3DateUtility.getDate("20080203", "yyyyMMdd"),"12","2");
            assertEquals(1, l_cost.length);
            assertEquals("2", l_cost[0].getSideDiv());
            assertEquals("12", l_cost[0].getCostDiv());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetForeignCost_CASE003()
    {
        final String STR_METHOD_NAME = "testGetForeignCost_CASE003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(ForeignCostParams.TYPE);
            TestDBUtility.deleteAll(MarketParams.TYPE);
            TestDBUtility.deleteAll(MarketPreferencesRow.TYPE);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("11");
            l_marketParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_marketParams);

            ForeignCostParams l_params = this.getForeignCostParams();
            l_params.setAddAmount(333.33512);
            l_params.setScale(2);
            l_params.setRoundDiv("1");
            l_params.setSideDiv("1");
            l_params.setCommisionRate(24.5);
            TestDBUtility.insertWithDel(l_params);
            
            ForeignCostParams l_params2 = this.getForeignCostParams();
            l_params2.setAddAmount(333.33512);
            l_params2.setScale(2);
            l_params2.setRoundDiv("1");
            l_params2.setSideDiv("2");
            l_params2.setCostDiv("12");
            l_params2.setCommisionRate(24.5);
            TestDBUtility.insertWithDel(l_params2);
            
            WEB3FeqBizLogicProvider l_provider = new WEB3FeqBizLogicProvider();
            ForeignCostParams[] l_cost = l_provider.getForeignCost(new Long(888), "123456789", "0D", "11", 123d,
                WEB3DateUtility.getDate("20080203", "yyyyMMdd"),"12","1");
            assertEquals(0, l_cost.length);

        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    
    /**
     * (1) 引数：銘柄IDを追加
     * (2) 諸経費区分 == null の場合 銘柄別海外諸経費マスタテーブルを検索し、
     *     特殊銘柄用海外諸経費マスタ行の配列を取得しない場合、
     *     ２）取得した海外諸経費マスタ行の配列を返却する。
     */
    public void testGetForeignCost_CASE004()
    {
        final String STR_METHOD_NAME = "testGetForeignCost_CASE004()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(ForeignCostParams.TYPE);
            TestDBUtility.deleteAll(SpecialProductForeignCostParams.TYPE);

            // 海外諸経費マスタテーブル
            // (pk: 証券会社コード, 市場コード, 諸経費区分, 摘要開始年月日, 売買代金（FROM）, 売買区分)
            
            ForeignCostParams l_params1 = this.getForeignCostParams();
            // 証券会社コード institution_code = "0D"
            // 市場コード market_code = "11"
            // 諸経費区分 cost_div = "03"
            // 摘要開始年月日 appli_start_date = 20080103
            // 摘要終了年月日 appli_end_date = 20080303
            // 売買代金（FROM）amount_from = 100
            // 売買代金（TO） amount_to = 200
            // 売買区分 side_div = "1"
            TestDBUtility.insertWithDel(l_params1);
            
            ForeignCostParams l_params2 = this.getForeignCostParams();
            // 諸経費区分 cost_div = "04"
            l_params2.setCostDiv("04");
            // 売買代金（FROM）amount_from = 102
            l_params2.setAmountFrom(102);
            TestDBUtility.insertWithDel(l_params2);
            
            // 特殊銘柄用海外諸経費マスタテーブル
            // (pk: 証券会社コード, 市場コード, 銘柄ＩＤ, 現地銘柄コード, 諸経費区分, 摘要開始年月日, 売買代金（FROM）, 売買区分)
            
            SpecialProductForeignCostParams l_productForeignCostParams1 = this.getSpecialProductForeignCostParams();
            // 証券会社コード institution_code = "0D"
            // 市場コード market_code = "11"
            // 銘柄ＩＤ product_id = 888
            // 現地銘柄コード
            // 諸経費区分 cost_div = "03"
            // 摘要開始年月日 appli_start_date = 20080108
            // 摘要終了年月日 appli_end_date = 20080309
            // 売買代金（FROM）amount_from = 95
            // 売買代金（TO） amount_to = 300
            // 売買区分 side_div = "1"
            TestDBUtility.insertWithDel(l_productForeignCostParams1);

            WEB3FeqBizLogicProvider l_provider = new WEB3FeqBizLogicProvider();
            ForeignCostParams[] l_cost = l_provider.getForeignCost(new Long(777),"123456789", "0D", "11", 123d,
                WEB3DateUtility.getDate("20080203", "yyyyMMdd"),null,"1");
            
            assertEquals(2, l_cost.length);

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
                TestDBUtility.deleteAll(ForeignCostParams.TYPE);
                TestDBUtility.deleteAll(SpecialProductForeignCostParams.TYPE);
                log.exiting(STR_METHOD_NAME);
            }
            catch(Exception l_ex)
            {
                log.exiting(STR_METHOD_NAME);
                log.error(l_ex.getMessage(), l_ex);
            }
        }
    }


    /**
     * (1)引数：現地銘柄コードを追加
     * (2)諸経費区分 == null の場合 銘柄別海外諸経費マスタテーブルを検索し、
     *    銘柄別海外諸経費マスタ行の配列を取得しない場合、
     *    ２）取得した海外諸経費マスタ行の配列を返却する。
     */
    public void testGetForeignCost_CASE007()
    {
        final String STR_METHOD_NAME = "testGetForeignCost_CASE007()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(ForeignCostParams.TYPE);
            TestDBUtility.deleteAll(SpecialProductForeignCostParams.TYPE);

            // 海外諸経費マスタテーブル
            // (pk: 証券会社コード, 市場コード, 諸経費区分, 摘要開始年月日, 売買代金（FROM）, 売買区分)
            
            ForeignCostParams l_params1 = this.getForeignCostParams();
            // 証券会社コード institution_code = "0D"
            // 市場コード market_code = "11"
            // 諸経費区分 cost_div = "03"
            // 摘要開始年月日 appli_start_date = 20080103
            // 摘要終了年月日 appli_end_date = 20080303
            // 売買代金（FROM）amount_from = 100
            // 売買代金（TO） amount_to = 200
            // 売買区分 side_div = "1"
            TestDBUtility.insertWithDel(l_params1);
            
            ForeignCostParams l_params2 = this.getForeignCostParams();
            // 諸経費区分 cost_div = "04"
            l_params2.setCostDiv("04");
            // 売買代金（FROM）amount_from = 102
            l_params2.setAmountFrom(102);
            TestDBUtility.insertWithDel(l_params2);
            
            // 銘柄別海外諸経費マスタテーブル
            // (pk: 証券会社コード, 市場コード, 銘柄ＩＤ, 現地銘柄コード, 諸経費区分, 摘要開始年月日, 売買代金（FROM）, 売買区分)
            
            SpecialProductForeignCostParams l_productForeignCostParams1 = this.getSpecialProductForeignCostParams();
            // 証券会社コード institution_code = "0D"
            // 市場コード market_code = "11"
            // 銘柄ＩＤ product_id = 888
            // 諸経費区分 cost_div = "03"
            // 摘要開始年月日 appli_start_date = 20080108
            // 摘要終了年月日 appli_end_date = 20080309
            // 売買代金（FROM）amount_from = 95
            // 売買代金（TO） amount_to = 300
            // 売買区分 side_div = "1"
            // 現地銘柄コード = "123456789"
            TestDBUtility.insertWithDel(l_productForeignCostParams1);
            
            WEB3FeqBizLogicProvider l_provider = new WEB3FeqBizLogicProvider();
            ForeignCostParams[] l_cost = l_provider.getForeignCost(new Long(888), "888888888", "0D", "11", 123d,
                WEB3DateUtility.getDate("20080203", "yyyyMMdd"), null, "1");
            
            assertEquals(2, l_cost.length);

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
                TestDBUtility.deleteAll(ForeignCostParams.TYPE);
                TestDBUtility.deleteAll(SpecialProductForeignCostParams.TYPE);
                log.exiting(STR_METHOD_NAME);
            }
            catch(Exception l_ex)
            {
                log.exiting(STR_METHOD_NAME);
                log.error(l_ex.getMessage(), l_ex);
            }
        }
    }


    /**
     * (1)銘柄別海外諸経費マスタ行の配列を取得した場合、
     * (2)諸経費区分 != null の場合 銘柄別海外諸経費マスタテーブルを検索し、
     *    銘柄別海外諸経費マスタ行.諸経費区分は、取得した配列の各海外諸経費マスタ行.諸経費区分と一致する場合、
     *    「銘柄ID」「現地銘柄コード」以外のフィールド、海外諸経費マスタ行を銘柄別海外諸経費マスタ行に付け替える。
     *    付け替える後の海外諸経費マスタ行の配列を返却する。
     */
    public void testGetForeignCost_CASE005()
    {
        final String STR_METHOD_NAME = "testGetForeignCost_CASE005()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAllAndCommit(ForeignCostParams.TYPE);
            TestDBUtility.deleteAllAndCommit(SpecialProductForeignCostParams.TYPE);

            // 海外諸経費マスタテーブル
            // (pk: 証券会社コード, 市場コード, 諸経費区分, 摘要開始年月日, 売買代金（FROM）, 売買区分)
            
            ForeignCostParams l_params1 = this.getForeignCostParams();
            // 証券会社コード institution_code = "0D"
            // 市場コード market_code = "11"
            // 諸経費区分 cost_div = "03"
            // 摘要開始年月日 appli_start_date = 20080103
            // 摘要終了年月日 appli_end_date = 20080303
            // 売買代金（FROM）amount_from = 100
            // 売買代金（TO） amount_to = 200
            // 売買区分 side_div = "1"
            TestDBUtility.insertWithDelAndCommit(l_params1);
            
            ForeignCostParams l_params2 = this.getForeignCostParams();
            // 諸経費区分 cost_div = "04"
            l_params2.setCostDiv("04");
            // 売買代金（FROM）amount_from = 102
            l_params2.setAmountFrom(102);
            TestDBUtility.insertWithDelAndCommit(l_params2);
            
            ForeignCostParams l_params3 = this.getForeignCostParams();
            // 諸経費区分 cost_div = "01"
            l_params3.setCostDiv("01");
            // 売買代金（FROM）amount_from = 103
            l_params3.setAmountFrom(103);
            TestDBUtility.insertWithDelAndCommit(l_params3);
            
            ForeignCostParams l_params6 = this.getForeignCostParams();
            // 諸経費区分 cost_div = "02"
            l_params6.setCostDiv("02");
            // 売買代金（FROM）amount_from = 120
            l_params6.setAmountFrom(120);
            TestDBUtility.insertWithDelAndCommit(l_params6);
            
            // 銘柄別海外諸経費マスタテーブル
            // (pk: 証券会社コード, 市場コード, 銘柄ＩＤ, 諸経費区分, 摘要開始年月日, 売買代金（FROM）, 売買区分)
            
            SpecialProductForeignCostParams l_productForeignCostParams1 = this.getSpecialProductForeignCostParams();
            // 証券会社コード institution_code = "0D"
            // 市場コード market_code = "11"
            // 銘柄ＩＤ product_id = 888
            // 諸経費区分 cost_div = "03"
            // 摘要開始年月日 appli_start_date = 20080108
            // 摘要終了年月日 appli_end_date = 20080309
            // 売買代金（FROM）amount_from = 95
            // 売買代金（TO） amount_to = 300
            // 売買区分 side_div = "1"
            TestDBUtility.insertWithDelAndCommit(l_productForeignCostParams1);
            
            SpecialProductForeignCostParams l_productForeignCostParams2 = this.getSpecialProductForeignCostParams();
            // 銘柄ＩＤ product_id = 888
            // 諸経費区分 cost_div = "04"
            l_productForeignCostParams2.setCostDiv("04");
            // 売買代金（FROM）amount_from = 106
            l_productForeignCostParams2.setAmountFrom(106);
            TestDBUtility.insertWithDelAndCommit(l_productForeignCostParams2);
            
            WEB3FeqBizLogicProvider l_provider = new WEB3FeqBizLogicProvider();
            ForeignCostParams[] l_cost = l_provider.getForeignCost(new Long(888), "123456789", "0D", "11", 123d,
                WEB3DateUtility.getDate("20080203", "yyyyMMdd"), "03", "1");
            
            assertEquals(1, l_cost.length);
            assertEquals("03", l_cost[0].getCostDiv());
            assertEquals(95, (int)l_cost[0].getAmountFrom());
            
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
                TestDBUtility.deleteAll(ForeignCostParams.TYPE);
                TestDBUtility.deleteAll(SpecialProductForeignCostParams.TYPE);
                log.exiting(STR_METHOD_NAME);
            }
            catch(Exception l_ex)
            {
                log.exiting(STR_METHOD_NAME);
                log.error(l_ex.getMessage(), l_ex);
            }
        }
    }
    
    /**
     * (1)諸経費区分 == null の場合、海外諸経費マスタテーブルを検索し、
     *    同一諸経費区分のレコードが複数行取得された場合は例外をスローする。
     *    ※「同一区分の複数行のレコードが選択されました」
     */
    public void testGetForeignCost_CASE006()
    {
        final String STR_METHOD_NAME = "testGetForeignCost_CASE006()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(ForeignCostParams.TYPE);
            TestDBUtility.deleteAll(SpecialProductForeignCostParams.TYPE);

            // 海外諸経費マスタテーブル
            // (pk: 証券会社コード, 市場コード, 諸経費区分, 摘要開始年月日, 売買代金（FROM）, 売買区分)
            
            ForeignCostParams l_params1 = this.getForeignCostParams();
            // 証券会社コード institution_code = "0D"
            // 市場コード market_code = "11"
            // 諸経費区分 cost_div = "03"
            // 摘要開始年月日 appli_start_date = 20080103
            // 摘要終了年月日 appli_end_date = 20080303
            // 売買代金（FROM）amount_from = 100
            // 売買代金（TO） amount_to = 200
            // 売買区分 side_div = "1"
            TestDBUtility.insertWithDel(l_params1);
            
            ForeignCostParams l_params2 = this.getForeignCostParams();
            // 諸経費区分 cost_div = "03"
            // 売買代金（FROM）amount_from = 102
            l_params2.setAmountFrom(102);
            TestDBUtility.insertWithDel(l_params2);
            
            ForeignCostParams l_params3 = this.getForeignCostParams();
            // 諸経費区分 cost_div = "01"
            l_params3.setCostDiv("01");
            // 売買代金（FROM）amount_from = 103
            l_params3.setAmountFrom(103);
            TestDBUtility.insertWithDel(l_params3);
            
            ForeignCostParams l_params6 = this.getForeignCostParams();
            // 諸経費区分 cost_div = "01"
            l_params6.setCostDiv("01");
            // 売買代金（FROM）amount_from = 120
            l_params6.setAmountFrom(120);
            TestDBUtility.insertWithDel(l_params6);
            
            ForeignCostParams l_params4 = this.getForeignCostParams();
            // 諸経費区分 cost_div = "02"
            l_params4.setCostDiv("02");
            // 売買代金（FROM）amount_from = 104
            l_params4.setAmountFrom(104);
            TestDBUtility.insertWithDel(l_params4);
            
            ForeignCostParams l_params5 = this.getForeignCostParams();
            // 諸経費区分 cost_div = "04"
            l_params5.setCostDiv("04");
            // 売買代金（FROM）amount_from = 105
            l_params5.setAmountFrom(105);
            TestDBUtility.insertWithDel(l_params5);
            
            
            // 銘柄別海外諸経費マスタテーブル
            // (pk: 証券会社コード, 市場コード, 銘柄ＩＤ, 諸経費区分, 摘要開始年月日, 売買代金（FROM）, 売買区分)
            
            SpecialProductForeignCostParams l_productForeignCostParams1 = this.getSpecialProductForeignCostParams();
            // 証券会社コード institution_code = "0D"
            // 市場コード market_code = "11"
            // 銘柄ＩＤ product_id = 888
            // 諸経費区分 cost_div = "03"
            // 摘要開始年月日 appli_start_date = 20080108
            // 摘要終了年月日 appli_end_date = 20080309
            // 売買代金（FROM）amount_from = 95
            // 売買代金（TO） amount_to = 300
            // 売買区分 side_div = "1"
            TestDBUtility.insertWithDel(l_productForeignCostParams1);
            
            SpecialProductForeignCostParams l_productForeignCostParams3 = this.getSpecialProductForeignCostParams();
            // 銘柄ＩＤ product_id = 888
            // 諸経費区分 cost_div = "01"
            l_productForeignCostParams3.setCostDiv("01");
            // 売買代金（FROM）amount_from = 107
            l_productForeignCostParams3.setAmountFrom(107);
            TestDBUtility.insertWithDel(l_productForeignCostParams3);
            
            SpecialProductForeignCostParams l_productForeignCostParams4 = this.getSpecialProductForeignCostParams();
            // 銘柄ＩＤ product_id = 999
            l_productForeignCostParams4.setProductId(999);
            // 諸経費区分 cost_div = "02"
            l_productForeignCostParams4.setCostDiv("02");
            // 売買代金（FROM）amount_from = 108
            l_productForeignCostParams4.setAmountFrom(108);
            TestDBUtility.insertWithDel(l_productForeignCostParams4);
            
            
            WEB3FeqBizLogicProvider l_provider = new WEB3FeqBizLogicProvider();
            l_provider.getForeignCost(new Long(888), "123456789", "0D", "11", 123d,
                WEB3DateUtility.getDate("20080203", "yyyyMMdd"), null, "1");
            
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03209, l_ex.getErrorInfo());
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
                TestDBUtility.deleteAll(ForeignCostParams.TYPE);
                TestDBUtility.deleteAll(SpecialProductForeignCostParams.TYPE);
                log.exiting(STR_METHOD_NAME);
            }
            catch(Exception l_ex)
            {
                log.exiting(STR_METHOD_NAME);
                log.error(l_ex.getMessage(), l_ex);
            }
        }
    }
    
    /**
     * (1)諸経費区分 == null の場合、特殊銘柄用海外諸経費マスタテーブルを検索し、
     *    同一諸経費区分のレコードが複数行取得された場合は例外をスローする。
     *    ※「同一区分の複数行のレコードが選択されました」
     */
    public void testGetForeignCost_CASE008()
    {
        final String STR_METHOD_NAME = "testGetForeignCost_CASE008()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(ForeignCostParams.TYPE);
            TestDBUtility.deleteAll(SpecialProductForeignCostParams.TYPE);

            // 海外諸経費マスタテーブル
            // (pk: 証券会社コード, 市場コード, 諸経費区分, 摘要開始年月日, 売買代金（FROM）, 売買区分)
            
            ForeignCostParams l_params1 = this.getForeignCostParams();
            // 証券会社コード institution_code = "0D"
            // 市場コード market_code = "11"
            // 諸経費区分 cost_div = "03"
            // 摘要開始年月日 appli_start_date = 20080103
            // 摘要終了年月日 appli_end_date = 20080303
            // 売買代金（FROM）amount_from = 100
            // 売買代金（TO） amount_to = 200
            // 売買区分 side_div = "1"
            TestDBUtility.insertWithDel(l_params1);
            
            ForeignCostParams l_params3 = this.getForeignCostParams();
            // 諸経費区分 cost_div = "01"
            l_params3.setCostDiv("01");
            // 売買代金（FROM）amount_from = 103
            l_params3.setAmountFrom(103);
            TestDBUtility.insertWithDel(l_params3);
            
            ForeignCostParams l_params4 = this.getForeignCostParams();
            // 諸経費区分 cost_div = "02"
            l_params4.setCostDiv("02");
            // 売買代金（FROM）amount_from = 104
            l_params4.setAmountFrom(104);
            TestDBUtility.insertWithDel(l_params4);
            
            ForeignCostParams l_params5 = this.getForeignCostParams();
            // 諸経費区分 cost_div = "04"
            l_params5.setCostDiv("04");
            // 売買代金（FROM）amount_from = 105
            l_params5.setAmountFrom(105);
            TestDBUtility.insertWithDel(l_params5);
            
            
            // 銘柄別海外諸経費マスタテーブル
            // (pk: 証券会社コード, 市場コード, 銘柄ＩＤ, 諸経費区分, 摘要開始年月日, 売買代金（FROM）, 売買区分)
            
            SpecialProductForeignCostParams l_productForeignCostParams1 = this.getSpecialProductForeignCostParams();
            // 証券会社コード institution_code = "0D"
            // 市場コード market_code = "11"
            // 銘柄ＩＤ product_id = 888
            // 諸経費区分 cost_div = "03"
            // 摘要開始年月日 appli_start_date = 20080108
            // 摘要終了年月日 appli_end_date = 20080309
            // 売買代金（FROM）amount_from = 95
            // 売買代金（TO） amount_to = 300
            // 売買区分 side_div = "1"
            TestDBUtility.insertWithDel(l_productForeignCostParams1);
            
            SpecialProductForeignCostParams l_productForeignCostParams3 = this.getSpecialProductForeignCostParams();
            // 銘柄ＩＤ product_id = 888
            // 諸経費区分 cost_div = "03"
            l_productForeignCostParams3.setCostDiv("01");
            // 売買代金（FROM）amount_from = 107
            l_productForeignCostParams3.setAmountFrom(107);
            TestDBUtility.insertWithDel(l_productForeignCostParams3);
            
            SpecialProductForeignCostParams l_productForeignCostParams4 = this.getSpecialProductForeignCostParams();
            // 銘柄ＩＤ product_id = 999
            l_productForeignCostParams4.setProductId(999);
            // 諸経費区分 cost_div = "03"
            l_productForeignCostParams4.setCostDiv("03");
            // 売買代金（FROM）amount_from = 108
            l_productForeignCostParams4.setAmountFrom(108);
            TestDBUtility.insertWithDel(l_productForeignCostParams4);
            
            
            WEB3FeqBizLogicProvider l_provider = new WEB3FeqBizLogicProvider();
            l_provider.getForeignCost(new Long(888), "123456789", "0D", "11", 123d,
                WEB3DateUtility.getDate("20080203", "yyyyMMdd"), null, "1");
            
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03209, l_ex.getErrorInfo());
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
                TestDBUtility.deleteAll(ForeignCostParams.TYPE);
                TestDBUtility.deleteAll(SpecialProductForeignCostParams.TYPE);
                log.exiting(STR_METHOD_NAME);
            }
            catch(Exception l_ex)
            {
                log.exiting(STR_METHOD_NAME);
                log.error(l_ex.getMessage(), l_ex);
            }
        }
    }
    
    public void testCalcAllAmount_Case001()
    {
        final String STR_METHOD_NAME = "testCalcAllAmount_Case001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //InstitutionRow
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(FeqFinTransactionRow.TYPE);
            FeqFinTransactionParams l_feqFinTransactionParams = this.getFeqFinTransactionParams();
            l_feqFinTransactionParams.setFinTransactionType(FinTransactionType.EQTYPE_FEQ_BUY);
            TestDBUtility.insertWithDel(l_feqFinTransactionParams);
            
            TestDBUtility.deleteAll(FeqProductParams.TYPE);
            FeqProductParams l_feqProductParams = TestDBUtility.getFeqProductRow();
            l_feqProductParams.setProductId(l_feqFinTransactionParams.getProductId());
            TestDBUtility.insertWithDel(l_feqProductParams);
            
            TestDBUtility.deleteAll(GenCurrencyParams.TYPE);
            GenCurrencyParams l_currencyParams = TestDBUtility.getGenCurrencyRow();
            l_currencyParams.setInstitutionCode(l_feqProductParams.getInstitutionCode());
            l_currencyParams.setCurrencyCode(l_feqProductParams.getCurrencyCode());
            TestDBUtility.insertWithDel(l_currencyParams);
            
            //ProductParams
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(l_feqProductParams.getProductId());
            TestDBUtility.insertWithDel(l_productParams);
            
            //MarketParams
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("11");
            l_marketParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_marketParams);
            
            //ForeignCostParams
            TestDBUtility.deleteAll(ForeignCostParams.TYPE);
            
            ForeignCostParams l_params = this.getForeignCostParams();
            l_params.setScale(2);
            l_params.setSideDiv("1");
            l_params.setCostDiv("01");
            l_params.setInstitutionCode("0D");
            l_params.setMarketCode("11");
            l_params.setAmountFrom(10);
            l_params.setAmountTo(30);
            TestDBUtility.insertWithDel(l_params);
            
            ForeignCostParams l_params2 = this.getForeignCostParams();
            l_params2.setScale(3);
            l_params2.setSideDiv("2");
            l_params2.setCostDiv("01");
            l_params2.setInstitutionCode("0D");
            l_params2.setMarketCode("11");
            l_params2.setAmountFrom(10);
            l_params2.setAmountTo(30);
            TestDBUtility.insertWithDel(l_params2);

            int l_intMax = 10;
            String l_strMarketCode = "11";
            double[] l_dblTradePrices = new double[1];
            double[] l_dblTradePriceFcs = new double[1];
            l_dblTradePriceFcs[0] = 24.5;

            WEB3FeqAmountCalcResult l_calcResult = new WEB3FeqAmountCalcResult();
            l_calcResult.setForeignCommissionFee(10.12);
            double l_dblTradePriceFcTotal = 100D;
            int l_intDecimalPlace = 1;
            FeqFinTransactionParams[] l_feqFinTransactionParamss = new FeqFinTransactionParams[1];
            l_feqFinTransactionParamss[0] = l_feqFinTransactionParams;

            WEB3FeqProduct l_feqProduct = new WEB3FeqProduct(l_feqProductParams);
            Date l_ExecDate = WEB3DateUtility.getDate("20080203", "yyyyMMdd");
            double[] l_amountTotals = new double[10];
            l_provider.calcAllAmount(
                l_intMax,
                l_strMarketCode,
                l_dblTradePrices,
                l_dblTradePriceFcs,
                l_calcResult,
                l_dblTradePriceFcTotal,
                l_intDecimalPlace,
                l_feqFinTransactionParamss,
                l_feqProduct,
                l_ExecDate,
                l_amountTotals);
            assertEquals(2.47,l_amountTotals[0],0);

        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCalcAllAmount_Case002()
    {
        final String STR_METHOD_NAME = "testCalcAllAmount_Case002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //InstitutionRow
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(FeqFinTransactionRow.TYPE);
            
            FeqFinTransactionParams l_feqFinTransactionParams2 = this.getFeqFinTransactionParams();
            l_feqFinTransactionParams2.setFinTransactionType(FinTransactionType.EQTYPE_FEQ_SELL);
            TestDBUtility.insertWithDel(l_feqFinTransactionParams2);
            
            TestDBUtility.deleteAll(FeqProductParams.TYPE);
            FeqProductParams l_feqProductParams = TestDBUtility.getFeqProductRow();
            l_feqProductParams.setProductId(l_feqFinTransactionParams2.getProductId());
            TestDBUtility.insertWithDel(l_feqProductParams);
            
            TestDBUtility.deleteAll(GenCurrencyParams.TYPE);
            GenCurrencyParams l_currencyParams = TestDBUtility.getGenCurrencyRow();
            l_currencyParams.setInstitutionCode(l_feqProductParams.getInstitutionCode());
            l_currencyParams.setCurrencyCode(l_feqProductParams.getCurrencyCode());
            TestDBUtility.insertWithDel(l_currencyParams);
            
            //ProductParams
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(l_feqProductParams.getProductId());
            TestDBUtility.insertWithDel(l_productParams);
            
            //MarketParams
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("11");
            l_marketParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_marketParams);
            
            //ForeignCostParams
            TestDBUtility.deleteAll(ForeignCostParams.TYPE);
            
            ForeignCostParams l_params = this.getForeignCostParams();
            l_params.setScale(2);
            l_params.setSideDiv("1");
            l_params.setCostDiv("01");
            l_params.setInstitutionCode("0D");
            l_params.setMarketCode("11");
            l_params.setAmountFrom(10);
            l_params.setAmountTo(30);
            TestDBUtility.insertWithDel(l_params);
            
            ForeignCostParams l_params2 = this.getForeignCostParams();
            l_params2.setScale(3);
            l_params2.setSideDiv("2");
            l_params2.setCostDiv("01");
            l_params2.setInstitutionCode("0D");
            l_params2.setMarketCode("11");
            l_params2.setAmountFrom(10);
            l_params2.setAmountTo(30);
            TestDBUtility.insertWithDel(l_params2);

            int l_intMax = 10;
            String l_strMarketCode = "11";
            double[] l_dblTradePrices = new double[1];
            double[] l_dblTradePriceFcs = new double[1];
            l_dblTradePriceFcs[0] = 24.5;

            WEB3FeqAmountCalcResult l_calcResult = new WEB3FeqAmountCalcResult();
            l_calcResult.setForeignCommissionFee(10.12);
            double l_dblTradePriceFcTotal = 100D;
            int l_intDecimalPlace = 1;
            FeqFinTransactionParams[] l_feqFinTransactionParamss = new FeqFinTransactionParams[1];
            l_feqFinTransactionParamss[0] = l_feqFinTransactionParams2;

            WEB3FeqProduct l_feqProduct = new WEB3FeqProduct(l_feqProductParams);
            Date l_ExecDate = WEB3DateUtility.getDate("20080203", "yyyyMMdd");
            double[] l_amountTotals = new double[10];
            l_provider.calcAllAmount(
                l_intMax,
                l_strMarketCode,
                l_dblTradePrices,
                l_dblTradePriceFcs,
                l_calcResult,
                l_dblTradePriceFcTotal,
                l_intDecimalPlace,
                l_feqFinTransactionParamss,
                l_feqProduct,
                l_ExecDate,
                l_amountTotals);
            assertEquals(2.479,l_amountTotals[0],0);

        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    public ForeignCostParams getForeignCostParams()
    {
        ForeignCostParams l_params = new ForeignCostParams();
        //証券会社コード    institution_code    VARCHAR2    3    NotNull
        l_params.setInstitutionCode("0D");
        //市場コード    market_code    VARCHAR2    2    NotNull
        l_params.setMarketCode("11");
        //諸経費区分    cost_div    VARCHAR2    2    NotNull
        l_params.setCostDiv("03");
        //摘要開始年月日    appli_start_date    DATE        NotNull
        l_params.setAppliStartDate(WEB3DateUtility.getDate("20080103", "yyyyMMdd"));
        //摘要終了年月日    appli_end_date    DATE        NULL
        l_params.setAppliEndDate(WEB3DateUtility.getDate("20080303", "yyyyMMdd"));
        //売買代金（FROM）    amount_from    NUMBER    13 11  2   NotNull
        l_params.setAmountFrom(100);
        //売買代金（TO）    amount_to    NUMBER    13 11  2   NULL
        l_params.setAmountTo(200);
        //徴収率    commision_rate    NUMBER    8  3   5   NULL
        l_params.setCommisionRate(300.05);
        //付加金額    add_amount    NUMBER    14    9   5   NULL
        l_params.setAddAmount(900.05);
        //小数部桁数    scale    NUMBER    6    Notnull
        l_params.setScale(2);
        //計算結果丸め方式    round_div    VARCHAR2    1    Notnull
        l_params.setRoundDiv("0");
        //更新者コード    last_updater    VARCHAR2    20    NULL
        l_params.setLastUpdater("aaaaa");
        //作成日付    created_timestamp    DATE        NotNull
        l_params.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        //更新日付    last_updated_timestamp    DATE        NotNull
        l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        //売買区分
        l_params.setSideDiv("1");
        return l_params;
    }
    
    private SpecialProductForeignCostParams getSpecialProductForeignCostParams()
    {
        SpecialProductForeignCostParams l_params = new SpecialProductForeignCostParams();
        
        //証券会社コード    institution_code    VARCHAR2    3    NotNull
        l_params.setInstitutionCode("0D");
        //市場コード    market_code    VARCHAR2    2    NotNull
        l_params.setMarketCode("11");
        //銘柄ＩＤ
        l_params.setProductId(888);
        //現地銘柄コード
        l_params.setOffshoreProductCode("123456789");
        //諸経費区分    cost_div    VARCHAR2    2    NotNull
        l_params.setCostDiv("03");
        //摘要開始年月日    appli_start_date    DATE        NotNull
        l_params.setAppliStartDate(WEB3DateUtility.getDate("20080108", "yyyyMMdd"));
        //摘要終了年月日    appli_end_date    DATE        NULL
        l_params.setAppliEndDate(WEB3DateUtility.getDate("20080309", "yyyyMMdd"));
        //売買代金（FROM）    amount_from    NUMBER    13 11  2   NotNull
        l_params.setAmountFrom(95);
        //売買代金（TO）    amount_to    NUMBER    13 11  2   NULL
        l_params.setAmountTo(300);
        //徴収率    commision_rate    NUMBER    8  3   5   NULL
        l_params.setCommisionRate(400.05);
        //付加金額    add_amount    NUMBER    14    9   5   NULL
        l_params.setAddAmount(1000.05);
        //小数部桁数    scale    NUMBER    6    Notnull
        l_params.setScale(2);
        //計算結果丸め方式    round_div    VARCHAR2    1    Notnull
        l_params.setRoundDiv("0");
        //更新者コード    last_updater    VARCHAR2    20    NULL
        l_params.setLastUpdater("aaaaa");
        //作成日付    created_timestamp    DATE        NotNull
        l_params.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        //更新日付    last_updated_timestamp    DATE        NotNull
        l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        //売買区分
        l_params.setSideDiv("1");
        
        return l_params;
    }
    
    public FeqFinTransactionParams getFeqFinTransactionParams()
    {
        FeqFinTransactionParams l_feqFinTransactionParams = new FeqFinTransactionParams();
        l_feqFinTransactionParams.setFinTransactionId(2L);
        l_feqFinTransactionParams.setAccountId(1L);
        l_feqFinTransactionParams.setOrderUnitId(1L);
        l_feqFinTransactionParams.setSubAccountId(1L);
        l_feqFinTransactionParams.setProductId(1L);
        l_feqFinTransactionParams.setFinTransactionType(FinTransactionType.EQTYPE_FEQ_SELL);
        l_feqFinTransactionParams.setFinTransactionCateg(FinTransactionCateg.EQTYPE_ASSET);
        l_feqFinTransactionParams.setTaxType(TaxTypeEnum.UNDEFINED);
        l_feqFinTransactionParams.setBizDate("20070506");
        l_feqFinTransactionParams.setDeliveryDate(WEB3DateUtility.getDate("20070506", "yyyyMMdd"));
        l_feqFinTransactionParams.setCurrencyCode("12");
        l_feqFinTransactionParams.setNetAmount(2D);
        l_feqFinTransactionParams.setNetAmountFc(1D);
        l_feqFinTransactionParams.setFxRate(0.5D);
        l_feqFinTransactionParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
        l_feqFinTransactionParams.setQuantity(1D);
        l_feqFinTransactionParams.setCommissionFee(0D);
        l_feqFinTransactionParams.setCommissionFeeTax(0D);
        l_feqFinTransactionParams.setCommissionFeeTaxFc(0D);
        l_feqFinTransactionParams.setCommissionFeeFc(0D);
        l_feqFinTransactionParams.setBalanceAmountFc(0D);
        l_feqFinTransactionParams.setForeignCommissionFee(0D);
        l_feqFinTransactionParams.setForeignTax(0D);
        l_feqFinTransactionParams.setForeignFeeExt1(0D);
        l_feqFinTransactionParams.setRegNo("2");
        l_feqFinTransactionParams.setForeignFeeExt2(0D);
        l_feqFinTransactionParams.setCapitalGain(0D);
        l_feqFinTransactionParams.setCapitalGainFc(0D);
        l_feqFinTransactionParams.setCapitalGainTax(0D);
        l_feqFinTransactionParams.setCapitalGainTaxFc(0D);
        l_feqFinTransactionParams.setDeleteFlag(BooleanEnum.FALSE);
        l_feqFinTransactionParams.setFinTransactionTimestamp(WEB3DateUtility.getDate("20071106", "yyyyMMdd"));
        l_feqFinTransactionParams.setCreatedTimestamp(WEB3DateUtility.getDate("20071106", "yyyyMMdd"));
        l_feqFinTransactionParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071106", "yyyyMMdd"));
        
        return l_feqFinTransactionParams;
        
    }
}
@
