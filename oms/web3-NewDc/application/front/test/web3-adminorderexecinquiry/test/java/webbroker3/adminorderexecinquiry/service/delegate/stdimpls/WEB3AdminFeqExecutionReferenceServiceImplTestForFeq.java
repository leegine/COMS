head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.50.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminFeqExecutionReferenceServiceImplTestForFeq.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.adminorderexecinquiry.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqProductParams;

import test.util.TestDBUtility;

import webbroker3.feq.WEB3FeqOrderUnit;
import webbroker3.gentrade.data.GenCurrencyParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminFeqExecutionReferenceServiceImplTestForFeq extends TestBaseForMock
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFeqExecutionReferenceServiceImplTestForFeq.class);
    
    WEB3AdminFeqExecutionReferenceServiceImpl l_impl =
        new WEB3AdminFeqExecutionReferenceServiceImpl();
    
    public WEB3AdminFeqExecutionReferenceServiceImplTestForFeq(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        this.deleteDb();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
        this.deleteDb();
    }

    public void testCreateFeqOrderExecInquiryUnitList_T01()
    {
        final String STR_METHOD_NAME = "testCreateFeqOrderExecInquiryUnitList_T01()";
        log.entering(STR_METHOD_NAME);
        try
        { 
            //ProductParams
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            TestDBUtility.insertWithDel(l_productParams);
            
            //FeqProductParams
            FeqProductParams l_feqProductParams = TestDBUtility.getFeqProductRow();
            l_feqProductParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            l_feqProductParams.setProductId(l_productParams.getProductId());
            TestDBUtility.insertWithDel(l_feqProductParams);

            //FeqOrderUnitParams
            FeqOrderUnitParams l_feqOrderUnitParasm = TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitParasm.setBranchId(123);
            l_feqOrderUnitParasm.setProductId(l_productParams.getProductId());
            l_feqOrderUnitParasm.setMarketId(123);
            l_feqOrderUnitParasm.setTraderId(null);
            l_feqOrderUnitParasm.setOrderType(OrderTypeEnum.FEQ_BUY);
            l_feqOrderUnitParasm.setExecutionConditionType(FeqExecutionConditionType.NONE);
            l_feqOrderUnitParasm.setExecutedQuantity(100);
            TestDBUtility.insertWithDel(l_feqOrderUnitParasm);
            
            //BranchParams
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(123);
            l_branchParams.setBranchCode("624");
            TestDBUtility.insertWithDel(l_branchParams);
            
            //MainAccountParams
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(l_feqOrderUnitParasm.getAccountId());
            TestDBUtility.insertWithDel(l_mainAccountParams);

            //MarketParams
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(123);
            TestDBUtility.insertWithDel(l_marketParams);

            //InstitutionParams
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            //GenCurrencyParams
            GenCurrencyParams l_genCurrencyParams = TestDBUtility.getGenCurrencyRow();
            l_genCurrencyParams.setInstitutionCode(l_feqProductParams.getInstitutionCode());
            l_genCurrencyParams.setCurrencyCode(l_feqProductParams.getCurrencyCode());
            TestDBUtility.insertWithDel(l_genCurrencyParams);
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            OrderManager l_orderManager =
                l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY).getOrderManager();
            WEB3FeqOrderUnit l_feqOrderUnit =
                (WEB3FeqOrderUnit)l_orderManager.getOrderUnit(l_feqOrderUnitParasm.getOrderUnitId());            

            l_impl.createFeqOrderExecInquiryUnitList(new WEB3FeqOrderUnit[]{l_feqOrderUnit});
            
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void deleteDb()
    {
        try
        {
            //FeqOrderUnitParams
            TestDBUtility.deleteAll(FeqOrderUnitParams.TYPE);
            
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }

}
@
