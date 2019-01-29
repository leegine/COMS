head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.55.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MarginExecuteReferenceServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : (WEB3MarginExecuteReferenceServiceImplTest.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2007/06/10  何文敏(中訊)　@新規作成
 */
package webbroker3.equity.service.delegate.stdimpls;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.equity.message.WEB3MarginExecuteDetailsRequest;
import webbroker3.equity.message.WEB3MarginExecuteDetailsResponse;
import webbroker3.equity.message.WEB3MarginExecuteGroup;
import webbroker3.equity.message.WEB3MarginExecuteReferenceRequest;
import webbroker3.equity.message.WEB3MarginExecuteReferenceResponse;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * @@author 何文敏(中訊)
 * @@version 1.0
 */
public class WEB3MarginExecuteReferenceServiceImplTest extends TestBaseForMock
{
    
    private String l_strProductCode = null;
    
    private String l_strMarketCode = null;
    
    private Date l_datOrderBizDate = null;
    
    private String l_strOrgOrderConditionDiv = null;
    
    private String l_strOrderConditionDiv = null;
    
    private String l_strQueryCond = null;
    
    private String[] l_strQueryDataContainer = null;
    
    private List l_lisResults = null;
    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MarginExecuteReferenceServiceImplTest.class);
    WEB3MarginExecuteReferenceServiceImpl l_impl = new WEB3MarginExecuteReferenceServiceImpl();
    
    public WEB3MarginExecuteReferenceServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        Calendar l_calendar = Calendar.getInstance();
        l_calendar.set(Calendar.YEAR,2007);
        l_calendar.set(Calendar.MONTH,9);
        l_calendar.set(Calendar.DAY_OF_MONTH,18);
        this.l_datOrderBizDate = l_calendar.getTime();
        this.l_strOrderConditionDiv = "1";
    }

    protected void tearDown() throws Exception
    {
        this.l_datOrderBizDate = null;
        this.l_strOrderConditionDiv = null;
        this.l_lisResults = null;
        super.tearDown();
    }
    
    public void testSearchOrderExecuteDetails()
    {
        final String STR_METHOD_NAME = "testSearchOrderExecuteDetails()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setExecutionConditionType(EqTypeExecutionConditionType.AT_MARKET_OPEN);
            l_eqtypeOrderUnitParams.setForcedSettleReasonType("2");
            l_eqtypeOrderUnitParams.setForcedExpireType("5");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080001L);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));
            
            WEB3MarginExecuteDetailsRequest l_request = new WEB3MarginExecuteDetailsRequest();
            l_request.id = "123456789";
            WEB3MarginExecuteDetailsResponse l_response =
                l_impl.searchOrderExecuteDetails(l_request);
            assertEquals("2", l_response.forcedSettleReason);
            assertEquals("5", l_response.forcedLapseDiv);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateExecuteReference()
    {
        final String STR_METHOD_NAME = "testCreateExecuteReference()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setExecutionConditionType(EqTypeExecutionConditionType.AT_MARKET_OPEN);
            l_eqtypeOrderUnitParams.setForcedSettleReasonType("2");
            l_eqtypeOrderUnitParams.setForcedExpireType("5");
            l_eqtypeOrderUnitParams.setSubAccountId(33381251220301L);
            l_eqtypeOrderUnitParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            l_subAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080001L);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accounMgr = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            WEB3GentradeMainAccount l_mainAccount = l_accounMgr.getMainAccount("0D", "381", "2512246");
            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount)l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            
            WEB3MarginExecuteReferenceRequest l_request = new WEB3MarginExecuteReferenceRequest();
//            l_request.execType = "1";
            l_request.pageIndex = "2";
            l_request.pageSize = "2";

            WEB3MarginExecuteReferenceServiceImplForTest l_mpl = new WEB3MarginExecuteReferenceServiceImplForTest();
            WEB3MarginExecuteReferenceResponse l_response =
                (WEB3MarginExecuteReferenceResponse)l_request.createResponse();
            WEB3MarginExecuteGroup[] l_group = l_mpl.createExecuteReference(l_subAccount, l_request, l_response);
            assertNull(l_group);
//            assertEquals(l_group[0].forcedSettleReason, "2");
//            assertEquals(l_group[0].forcedLapseDiv, "5");
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 株式注文単位テーブル.元発注条件==null 且つ 株式注文単位テーブル.発注条件!=null
     * 
     * 按「株式注文単位テーブル.発注条件」檢索
     *
     */
    public void testCreateQueryCond_C0001()
    {
        final String STR_METHOD_NAME = "testCreateQueryCond_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {

            WEB3MarginExecuteReferenceServiceImplForTest l_serviceImpl =
                new WEB3MarginExecuteReferenceServiceImplForTest();

            this.initData();
            this.l_strQueryCond = l_serviceImpl.createSearchCondCharacter(
                this.l_strProductCode,
                this.l_strMarketCode,
                this.l_datOrderBizDate,
                this.l_strOrderConditionDiv);

            this.l_strQueryDataContainer = l_serviceImpl.createSearchCondDataContainers(
                this.l_strProductCode,
                this.l_strMarketCode,
                this.l_datOrderBizDate,
                this.l_strOrderConditionDiv);

            this.getSearchData();
            assertEquals(3, this.l_lisResults.size());

            // 株式注文単位テーブル.発注条件
            assertEquals("1",((EqtypeOrderUnitRow)this.l_lisResults.get(0)).getOrderConditionType());
            assertEquals("1",((EqtypeOrderUnitRow)this.l_lisResults.get(1)).getOrderConditionType());
            assertEquals("1",((EqtypeOrderUnitRow)this.l_lisResults.get(2)).getOrderConditionType());

            // 株式注文単位テーブル.元発注条件
            assertNull(((EqtypeOrderUnitRow)this.l_lisResults.get(0)).getOrgOrderConditionType());
            assertNull(((EqtypeOrderUnitRow)this.l_lisResults.get(1)).getOrgOrderConditionType());
            assertNull(((EqtypeOrderUnitRow)this.l_lisResults.get(2)).getOrgOrderConditionType());

        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.entering(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    
    /**
     * 株式注文単位テーブル.元発注条件!=null 且つ 株式注文単位テーブル.発注条件==null
     * 
     * 按「株式注文単位テーブル.元発注条件」檢索
     *
     */
    public void testCreateQueryCond_C0002()
    {
        final String STR_METHOD_NAME = "testCreateQueryCond_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3MarginExecuteReferenceServiceImplForTest l_serviceImpl = new WEB3MarginExecuteReferenceServiceImplForTest();
            this.l_strOrgOrderConditionDiv = "2";
            this.l_strOrderConditionDiv = null;
            this.initData();
            this.l_strQueryCond = l_serviceImpl.createSearchCondCharacter(
                this.l_strProductCode,
                this.l_strMarketCode,
                this.l_datOrderBizDate,
                this.l_strOrderConditionDiv);
            
            this.l_strQueryDataContainer = l_serviceImpl.createSearchCondDataContainers(
                this.l_strProductCode,
                this.l_strMarketCode,
                this.l_datOrderBizDate,
                this.l_strOrderConditionDiv);
            
            this.getSearchData();
            assertEquals(3,this.l_lisResults.size());
            
            // 株式注文単位テーブル.元発注条件
            assertEquals("2",((EqtypeOrderUnitRow)this.l_lisResults.get(0)).getOrgOrderConditionType());
            assertEquals("2",((EqtypeOrderUnitRow)this.l_lisResults.get(1)).getOrgOrderConditionType());
            assertEquals("2",((EqtypeOrderUnitRow)this.l_lisResults.get(2)).getOrgOrderConditionType());
            
            // 株式注文単位テーブル.発注条件
            assertNull(((EqtypeOrderUnitRow)this.l_lisResults.get(0)).getOrderConditionType());
            assertNull(((EqtypeOrderUnitRow)this.l_lisResults.get(1)).getOrderConditionType());
            assertNull(((EqtypeOrderUnitRow)this.l_lisResults.get(2)).getOrderConditionType());
            
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.entering(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 株式注文単位テーブル.元発注条件=1 且つ 株式注文単位テーブル.発注条件==2
     * 
     * 按「株式注文単位テーブル.元発注条件」檢索，檢索不到數據
     *
     */
    public void testCreateQueryCond_C0003()
    {
        final String STR_METHOD_NAME = "testCreateQueryCond_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3MarginExecuteReferenceServiceImplForTest l_serviceImpl = new WEB3MarginExecuteReferenceServiceImplForTest();
            this.l_strOrgOrderConditionDiv = "1";
            this.l_strOrderConditionDiv = "2";
            this.initData();
            this.l_strQueryCond = l_serviceImpl.createSearchCondCharacter(
                this.l_strProductCode,
                this.l_strMarketCode,
                this.l_datOrderBizDate,
                this.l_strOrderConditionDiv);
            
            this.l_strQueryDataContainer = l_serviceImpl.createSearchCondDataContainers(
                this.l_strProductCode,
                this.l_strMarketCode,
                this.l_datOrderBizDate,
                this.l_strOrderConditionDiv);
            
            this.getSearchData();
            assertEquals(0,this.l_lisResults.size());            
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.entering(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 株式注文単位テーブル.元発注条件=1 且つ 株式注文単位テーブル.発注条件==1
     * 
     * 按「株式注文単位テーブル.元発注条件」檢索，檢索到數據
     *
     */
    public void testCreateQueryCond_C0004()
    {
        final String STR_METHOD_NAME = "testCreateQueryCond_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3MarginExecuteReferenceServiceImplForTest l_serviceImpl = new WEB3MarginExecuteReferenceServiceImplForTest();
            this.l_strOrgOrderConditionDiv = "1";
            this.l_strOrderConditionDiv = "1";
            this.initData();
            this.l_strQueryCond = l_serviceImpl.createSearchCondCharacter(
                this.l_strProductCode,
                this.l_strMarketCode,
                this.l_datOrderBizDate,
                this.l_strOrderConditionDiv);
            
            this.l_strQueryDataContainer = l_serviceImpl.createSearchCondDataContainers(
                this.l_strProductCode,
                this.l_strMarketCode,
                this.l_datOrderBizDate,
                this.l_strOrderConditionDiv);
            
            this.getSearchData();
            assertEquals(3,this.l_lisResults.size());
            
            // 株式注文単位テーブル.元発注条件
            assertEquals("1",((EqtypeOrderUnitRow)this.l_lisResults.get(0)).getOrgOrderConditionType());
            assertEquals("1",((EqtypeOrderUnitRow)this.l_lisResults.get(1)).getOrgOrderConditionType());
            assertEquals("1",((EqtypeOrderUnitRow)this.l_lisResults.get(2)).getOrgOrderConditionType());
            
            // 株式注文単位テーブル.発注条件
            assertEquals("1",((EqtypeOrderUnitRow)this.l_lisResults.get(0)).getOrderConditionType());
            assertEquals("1",((EqtypeOrderUnitRow)this.l_lisResults.get(1)).getOrderConditionType());
            assertEquals("1",((EqtypeOrderUnitRow)this.l_lisResults.get(2)).getOrderConditionType());
            
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.entering(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    private void initData()
    {
        final String STR_METHOD_NAME = "initData()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            // EqtypeOrderUnitRow.TYPE
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams1 = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams1.setOrderUnitId(3304148080001L);
            l_eqtypeOrderUnitParams1.setOrderType(OrderTypeEnum.EQUITY_BUY);
            l_eqtypeOrderUnitParams1.setBizDate(WEB3DateUtility.formatDate(this.l_datOrderBizDate,"yyyyMMdd"));
            l_eqtypeOrderUnitParams1.setOrgOrderConditionType(this.l_strOrgOrderConditionDiv);
            l_eqtypeOrderUnitParams1.setOrderConditionType(this.l_strOrderConditionDiv);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams1);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams2 = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams2.setOrderUnitId(3304148080002L);
            l_eqtypeOrderUnitParams2.setOrderType(OrderTypeEnum.EQUITY_BUY);
            l_eqtypeOrderUnitParams2.setBizDate(WEB3DateUtility.formatDate(this.l_datOrderBizDate,"yyyyMMdd"));
            l_eqtypeOrderUnitParams2.setOrgOrderConditionType(this.l_strOrgOrderConditionDiv);
            l_eqtypeOrderUnitParams2.setOrderConditionType(this.l_strOrderConditionDiv);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams2);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams3 = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams3.setOrderUnitId(3304148080003L);
            l_eqtypeOrderUnitParams3.setOrderType(OrderTypeEnum.EQUITY_BUY);
            l_eqtypeOrderUnitParams3.setBizDate(WEB3DateUtility.formatDate(this.l_datOrderBizDate,"yyyyMMdd"));
            l_eqtypeOrderUnitParams3.setOrgOrderConditionType(this.l_strOrgOrderConditionDiv);
            l_eqtypeOrderUnitParams3.setOrderConditionType(this.l_strOrderConditionDiv);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams3);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams4 = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams4.setOrderUnitId(3304148080004L);
            l_eqtypeOrderUnitParams4.setBizDate(WEB3DateUtility.formatDate(this.l_datOrderBizDate,"yyyyMMdd"));
            l_eqtypeOrderUnitParams4.setOrgOrderConditionType(this.l_strOrgOrderConditionDiv);
            l_eqtypeOrderUnitParams4.setOrderConditionType(this.l_strOrderConditionDiv);
            l_eqtypeOrderUnitParams4.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams4);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    private void getSearchData()
    {
        final String STR_METHOD_NAME = "getSearchData()";
        log.entering(TEST_START + STR_METHOD_NAME); 
        try
        {
            this.l_lisResults = Processors.getDefaultProcessor().doFindAllQuery(
                    EqtypeOrderUnitRow.TYPE, this.l_strQueryCond,this.l_strQueryDataContainer);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.entering(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    public class WEB3MarginExecuteReferenceServiceImplForTest extends WEB3MarginExecuteReferenceServiceImpl
    {
        protected String createSearchCondCharacter(String l_strProductCode, String l_strMarketCode,
                Date l_datOrderBizDate)
        {
            return null;
        }

        public WEB3GentradeSubAccount getSubAccount() throws WEB3BusinessLayerException, WEB3SystemLayerException
        {
            return null;
        }
    }
}
@
