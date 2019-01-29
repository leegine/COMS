head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.20.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3ToSuccSettingListServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.triggerorder.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
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
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.data.BranchIndexDealtCondParams;
import webbroker3.gentrade.data.BranchIndexDealtCondRow;
import webbroker3.gentrade.data.BranchMarketPtsDealtCondParams;
import webbroker3.gentrade.data.EnableOrderConditionParams;
import webbroker3.gentrade.data.EnableOrderConditionRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.triggerorder.WEB3ToSuccIfoOrderUnitImpl;
import webbroker3.triggerorder.base.data.RsvEqOrderUnitParams;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitParams;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitRow;
import webbroker3.triggerorder.message.WEB3SuccOrderUnit;
import webbroker3.triggerorder.message.WEB3SuccProductInfo;
import webbroker3.triggerorder.message.WEB3SuccSettingListRequest;
import webbroker3.triggerorder.message.WEB3SuccSortKey;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * 連続設定一覧サービスImplのテスト<BR>
 * @@author 趙林鵬(中訊)
 * @@version 1.0
 */
public class WEB3ToSuccSettingListServiceImplTest extends TestBaseForMock
{

    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccSettingListServiceImplTest.class);

    public WEB3ToSuccSettingListServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    //連続設定一覧サービスImpl
    WEB3ToSuccSettingListServiceImpl l_toSuccSettingListServiceImpl =
        new WEB3ToSuccSettingListServiceImpl();

    /**
     * (create検索条件文字列)<BR>
     * (create検索条件データコンテナ)<BR>
     * test_Query_0001()<BR>
     */
    public void test_Query_0001()
    {
        //log
        final String STR_METHOD_NAME = "test_Query_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        //商品区分
        String l_strCommodityDiv = "1";
        //銘柄コード
        String l_productCode = null;
        //発注日
        Date l_orderBizDate = WEB3DateUtility.getDate("20071223","yyyyMMdd");

        try 
        {
            //証券会社
        	WEB3GentradeInstitution l_institution =  new WEB3GentradeInstitution("0D");

            //市場Rowを作成
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.deleteAll(l_marketParams.TYPE);
            TestDBUtility.insertWithDel(l_marketParams);

            // 補助口座
            SubAccountRow l_subAccountParams = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);

            //株式注文単位テーブル（ヘッダ）Rowを作成
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams =TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setBizDate("20071223");
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.SWAP_MARGIN_LONG);
            TestDBUtility.deleteAll(l_eqtypeOrderUnitParams.TYPE);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);

            //顧客マスターRowを作成
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            //株式予約注文単位テーブル(rsv_eq_order_unit)
            RsvEqOrderUnitParams l_rsvEqOrderUnitParams = TestDBUtility.getRsvEqOrderUnitRow();
            l_rsvEqOrderUnitParams.setSubAccountId(33381251220301L);
            l_rsvEqOrderUnitParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_rsvEqOrderUnitParams);

            // (部店市場別.PTS)取扱条件Rowを作成 BranchMarketPtsDealtCondParams            
            BranchMarketPtsDealtCondParams l_branchMarketPtsDealtCondParams1 = new BranchMarketPtsDealtCondParams();
            l_branchMarketPtsDealtCondParams1.setInstitutionCode("0D");
            l_branchMarketPtsDealtCondParams1.setBranchCode("381");
            l_branchMarketPtsDealtCondParams1.setMarketCode("SP");
            l_branchMarketPtsDealtCondParams1.setMartCanDealtEquity("1");
            TestDBUtility.deleteAll(l_branchMarketPtsDealtCondParams1.TYPE);
            TestDBUtility.insertWithDel(l_branchMarketPtsDealtCondParams1);

            // WEB3GentradeSubAccount l_subAccount;
            // long l_lngAccountId,
            // long l_lngSubAccountId
            long l_lngAccountId=333812512203L;
            long l_lngSubAccountId=33381251220301L;

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(l_lngAccountId, l_lngSubAccountId);

            // create検索条件文字列
            String l_strSearchCond = l_toSuccSettingListServiceImpl.createQueryString(
                l_strCommodityDiv,
                l_productCode,
                l_orderBizDate,
                l_subAccount);

            log.debug(l_strSearchCond);

            // create検索条件データコンテナ
            Object[] l_searchCondContainers =
                l_toSuccSettingListServiceImpl.createQueryContainer(
                        l_institution, 
                        l_subAccount, 
                        l_strCommodityDiv, 
                        l_productCode, 
                        l_orderBizDate);

            //doFindAllQuery(
            //final String tableName, final String where, final String oderby, 
            //final String conditions, final Object bindVars[])
            List l_result = Processors.getDefaultProcessor().doFindAllQuery(
                EqtypeOrderUnitRow.TYPE, l_strSearchCond, null, null, l_searchCondContainers);

            assertEquals(0, l_result.size());

            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

    }

    /**
     * (create検索条件文字列)<BR>
     * (create検索条件データコンテナ)<BR>
     * test_Query_0002()<BR>
     */
    public void test_Query_0002()
    {
        //log
        final String STR_METHOD_NAME = "test_Query_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        //商品区分
        String l_strCommodityDiv = "1";
        //銘柄コード
        String l_productCode = null;
        //発注日
        Date l_orderBizDate = WEB3DateUtility.getDate("20071223","yyyyMMdd");

        try
        {
            //証券会社
            WEB3GentradeInstitution l_institution =  new WEB3GentradeInstitution("0D");

            //市場Rowを作成
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.deleteAll(l_marketParams.TYPE);
            TestDBUtility.insertWithDel(l_marketParams);

            // 補助口座
            SubAccountRow l_subAccountParams = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);

            //株式注文単位テーブル（ヘッダ）Rowを作成
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams =TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderUnitId(3304148080001L);
            l_eqtypeOrderUnitParams.setOrderId(123456788L);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_BUY);
            l_eqtypeOrderUnitParams.setBizDate("20071223");
            l_eqtypeOrderUnitParams.setMarketId(3300L);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            //顧客マスターRowを作成
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            //株式予約注文単位テーブル(rsv_eq_order_unit)
            RsvEqOrderUnitParams l_rsvEqOrderUnitParams = TestDBUtility.getRsvEqOrderUnitRow();
            l_rsvEqOrderUnitParams.setAccountId(333812512203L);
            l_rsvEqOrderUnitParams.setBizDate("20071223");
            TestDBUtility.insertWithDel(l_rsvEqOrderUnitParams);

            // (部店市場別.PTS)取扱条件Rowを作成 BranchMarketPtsDealtCondParams            
            BranchMarketPtsDealtCondParams l_branchMarketPtsDealtCondParams1 = new BranchMarketPtsDealtCondParams();
            l_branchMarketPtsDealtCondParams1.setInstitutionCode("0D");
            l_branchMarketPtsDealtCondParams1.setBranchCode("381");
            l_branchMarketPtsDealtCondParams1.setMarketCode("SP");
            l_branchMarketPtsDealtCondParams1.setMartCanDealtEquity("1");
            TestDBUtility.deleteAll(l_branchMarketPtsDealtCondParams1.TYPE);
            TestDBUtility.insertWithDel(l_branchMarketPtsDealtCondParams1);
             
            // WEB3GentradeSubAccount l_subAccount;
            // long l_lngAccountId,
            // long l_lngSubAccountId
            long l_lngAccountId=333812512203L;
            long l_lngSubAccountId=33381251220301L;

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(l_lngAccountId, l_lngSubAccountId);

            // create検索条件文字列
            String l_strSearchCond = l_toSuccSettingListServiceImpl.createQueryString(
                l_strCommodityDiv,
                l_productCode,
                l_orderBizDate,
                l_subAccount);

            log.debug(l_strSearchCond);

            // create検索条件データコンテナ
            Object[] l_searchCondContainers =
                l_toSuccSettingListServiceImpl.createQueryContainer(
                        l_institution, 
                        l_subAccount, 
                        l_strCommodityDiv, 
                        l_productCode, 
                        l_orderBizDate);

            //doFindAllQuery(
            //final String tableName, final String where, final String oderby, 
            //final String conditions, final Object bindVars[])
            List l_result = Processors.getDefaultProcessor().doFindAllQuery(
                EqtypeOrderUnitRow.TYPE, l_strSearchCond, null, null, l_searchCondContainers);

            assertEquals(1, l_result.size());

            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        { 
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

    }

    /**
     * (create検索条件文字列)<BR>
     * (create検索条件データコンテナ)<BR>
     * test_Query_0003()<BR>
     */
    public void test_Query_0003()
    {
        //log
        final String STR_METHOD_NAME = "test_Query_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        //商品区分
        String l_strCommodityDiv = "1";
        //銘柄コード
        String l_productCode = null;
        //発注日
        Date l_orderBizDate = WEB3DateUtility.getDate("20071223","yyyyMMdd");

        try
        {
            //証券会社
            WEB3GentradeInstitution l_institution =  new WEB3GentradeInstitution("0D");

            //市場Rowを作成
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.deleteAll(l_marketParams.TYPE);
            TestDBUtility.insertWithDel(l_marketParams);

            // 補助口座
            SubAccountRow l_subAccountParams = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);

            //株式注文単位テーブル（ヘッダ）Rowを作成
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams =TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderUnitId(3304148080001L);
            l_eqtypeOrderUnitParams.setOrderId(123456788L);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_BUY);
            l_eqtypeOrderUnitParams.setBizDate("20071223");
            l_eqtypeOrderUnitParams.setMarketId(3300L);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams1 =TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams1.setOrderUnitId(3304148080002L);
            l_eqtypeOrderUnitParams1.setOrderId(123456787L);
            l_eqtypeOrderUnitParams1.setOrderType(OrderTypeEnum.EQUITY_SELL);
            l_eqtypeOrderUnitParams1.setBizDate("20071223");
            l_eqtypeOrderUnitParams1.setMarketId(3302L);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams1);

            //顧客マスターRowを作成
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            //株式予約注文単位テーブル(rsv_eq_order_unit)
            RsvEqOrderUnitParams l_rsvEqOrderUnitParams = TestDBUtility.getRsvEqOrderUnitRow();
            l_rsvEqOrderUnitParams.setAccountId(333812512203L);
            l_rsvEqOrderUnitParams.setBizDate("20071223");
            TestDBUtility.insertWithDel(l_rsvEqOrderUnitParams);

            // (部店市場別.PTS)取扱条件Rowを作成 BranchMarketPtsDealtCondParams            
            BranchMarketPtsDealtCondParams l_branchMarketPtsDealtCondParams1 = new BranchMarketPtsDealtCondParams();
            l_branchMarketPtsDealtCondParams1.setInstitutionCode("0D");
            l_branchMarketPtsDealtCondParams1.setBranchCode("381");
            l_branchMarketPtsDealtCondParams1.setMarketCode("SP");
            l_branchMarketPtsDealtCondParams1.setMartCanDealtEquity("1");
            TestDBUtility.deleteAll(l_branchMarketPtsDealtCondParams1.TYPE);
            TestDBUtility.insertWithDel(l_branchMarketPtsDealtCondParams1);
             
            // WEB3GentradeSubAccount l_subAccount;
            // long l_lngAccountId,
            // long l_lngSubAccountId
            long l_lngAccountId=333812512203L;
            long l_lngSubAccountId=33381251220301L;

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(l_lngAccountId, l_lngSubAccountId);

            // create検索条件文字列
            String l_strSearchCond = l_toSuccSettingListServiceImpl.createQueryString(
                l_strCommodityDiv,
                l_productCode,
                l_orderBizDate,
                l_subAccount);

            log.debug(l_strSearchCond);

            // create検索条件データコンテナ
            Object[] l_searchCondContainers =
                l_toSuccSettingListServiceImpl.createQueryContainer(
                        l_institution, 
                        l_subAccount, 
                        l_strCommodityDiv, 
                        l_productCode, 
                        l_orderBizDate);

            //doFindAllQuery(
            //final String tableName, final String where, final String oderby, 
            //final String conditions, final Object bindVars[])
            List l_result = Processors.getDefaultProcessor().doFindAllQuery(
                EqtypeOrderUnitRow.TYPE, l_strSearchCond, null, null, l_searchCondContainers);

            assertEquals(2, l_result.size());

            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        { 
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

    }

    /**
     * (create検索条件文字列)<BR>
     * (create検索条件データコンテナ)<BR>
     * test_Query_0004()<BR>
     */
    public void test_Query_0004()
    {
        //log
        final String STR_METHOD_NAME = "test_Query_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        //商品区分
        String l_strCommodityDiv = "1";
        //銘柄コード
        String l_productCode = null;
        //発注日
        Date l_orderBizDate = WEB3DateUtility.getDate("20071223","yyyyMMdd");

        try
        {
            //証券会社
            WEB3GentradeInstitution l_institution =  new WEB3GentradeInstitution("0D");

            //市場Rowを作成
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("11");
            TestDBUtility.insertWithDel(l_marketParams);

            MarketParams l_marketParams2 = TestDBUtility.getMarketRow();
            l_marketParams2.setMarketId(3301L);
            TestDBUtility.insertWithDel(l_marketParams2);
            
            MarketParams l_marketParams1 = TestDBUtility.getMarketRow();
            l_marketParams1.setMarketId(3302L);
            l_marketParams1.setInstitutionCode("0D");
            l_marketParams1.setMarketCode("10");
            TestDBUtility.insertWithDel(l_marketParams1);
            
            // 補助口座
            SubAccountRow l_subAccountParams = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);

            //株式注文単位テーブル（ヘッダ）Rowを作成
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams =TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderUnitId(3304148080001L);
            l_eqtypeOrderUnitParams.setOrderId(123456788L);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_BUY);
            l_eqtypeOrderUnitParams.setBizDate("20071223");
            l_eqtypeOrderUnitParams.setMarketId(3300L);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams1 =TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams1.setOrderUnitId(3304148080002L);
            l_eqtypeOrderUnitParams1.setOrderId(123456787L);
            l_eqtypeOrderUnitParams1.setOrderType(OrderTypeEnum.EQUITY_SELL);
            l_eqtypeOrderUnitParams1.setBizDate("20071223");
            l_eqtypeOrderUnitParams1.setMarketId(3300L);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams1);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams2 =TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams2.setOrderUnitId(3304148080002L);
            l_eqtypeOrderUnitParams2.setOrderId(123456786L);
            l_eqtypeOrderUnitParams2.setOrderType(OrderTypeEnum.EQUITY_SELL);
            l_eqtypeOrderUnitParams2.setBizDate("20071223");
            l_eqtypeOrderUnitParams2.setMarketId(3300L);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams2);

            //顧客マスターRowを作成
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            //株式予約注文単位テーブル(rsv_eq_order_unit)
            RsvEqOrderUnitParams l_rsvEqOrderUnitParams = TestDBUtility.getRsvEqOrderUnitRow();
            l_rsvEqOrderUnitParams.setAccountId(333812512203L);
            l_rsvEqOrderUnitParams.setBizDate("20071223");
            TestDBUtility.insertWithDel(l_rsvEqOrderUnitParams);

            // (部店市場別.PTS)取扱条件Rowを作成 BranchMarketPtsDealtCondParams            
            BranchMarketPtsDealtCondParams l_branchMarketPtsDealtCondParams1 = new BranchMarketPtsDealtCondParams();
            l_branchMarketPtsDealtCondParams1.setInstitutionCode("0D");
            l_branchMarketPtsDealtCondParams1.setBranchCode("381");
            l_branchMarketPtsDealtCondParams1.setMarketCode("11");
            l_branchMarketPtsDealtCondParams1.setMartCanDealtEquity("1");
            l_branchMarketPtsDealtCondParams1.setMaxHandlingPrice(10000L);
            TestDBUtility.insertWithDel(l_branchMarketPtsDealtCondParams1);
            
            BranchMarketPtsDealtCondParams l_branchMarketPtsDealtCondParams2 = new BranchMarketPtsDealtCondParams();
            l_branchMarketPtsDealtCondParams2.setInstitutionCode("0D");
            l_branchMarketPtsDealtCondParams2.setBranchCode("381");
            l_branchMarketPtsDealtCondParams2.setMarketCode("11");
            l_branchMarketPtsDealtCondParams2.setMartCanDealtEquity("1");
            TestDBUtility.insertWithDel(l_branchMarketPtsDealtCondParams2);
             
            // WEB3GentradeSubAccount l_subAccount;
            // long l_lngAccountId,
            // long l_lngSubAccountId
            long l_lngAccountId=333812512203L;
            long l_lngSubAccountId=33381251220301L;

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(l_lngAccountId, l_lngSubAccountId);

            // create検索条件文字列
            String l_strSearchCond = l_toSuccSettingListServiceImpl.createQueryString(
                l_strCommodityDiv,
                l_productCode,
                l_orderBizDate,
                l_subAccount);

            log.debug(l_strSearchCond);

            // create検索条件データコンテナ
            Object[] l_searchCondContainers =
                l_toSuccSettingListServiceImpl.createQueryContainer(
                        l_institution, 
                        l_subAccount, 
                        l_strCommodityDiv, 
                        l_productCode, 
                        l_orderBizDate);

            //doFindAllQuery(
            //final String tableName, final String where, final String oderby, 
            //final String conditions, final Object bindVars[])
            List l_result = Processors.getDefaultProcessor().doFindAllQuery(
                EqtypeOrderUnitRow.TYPE, l_strSearchCond, null, null, l_searchCondContainers);

            assertEquals(2, l_result.size());

            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

    }

    public void testGetListScreen_0001()
    {
        final String STR_METHOD_NAME = "testGetListScreen_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3SuccSettingListRequest l_request = new WEB3SuccSettingListRequest();
            l_request.commodityTypeList = new String[]{"3"};
            l_request.sortKeys = new WEB3SuccSortKey[1];
            l_request.sortKeys[0] = new WEB3SuccSortKey();
            l_request.sortKeys[0].keyItem = "commodityType";
            l_request.sortKeys[0].ascDesc = "A";
            l_request.pageIndex = "1";
            l_request.pageSize = "5";

            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams1.setInstitutionCode("0D");
            l_tradingTimeParams1.setBranchCode("123");
            l_tradingTimeParams1.setTradingTimeType("11");
            l_tradingTimeParams1.setBizDateType("1");
            l_tradingTimeParams1.setMarketCode("0");
            l_tradingTimeParams1.setProductCode("0005");
            TestDBUtility.insertWithDel(l_tradingTimeParams1);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("0D");
            l_marketParams.setMarketCode("N1");
            TestDBUtility.insertWithDel(l_marketParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("123");
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006149081018L);
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006149081018L);
            TestDBUtility.insertWithDel(l_ifoProductParams);

            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);

            TestDBUtility.deleteAll(BranchIndexDealtCondRow.TYPE);
            BranchIndexDealtCondParams l_branchIndexDealtCondParams =
                TestDBUtility.getBranchIndexDealtCondRow();
            l_branchIndexDealtCondParams.setBranchCode("123");
            l_branchIndexDealtCondParams.setInstitutionCode("0D");
            l_branchIndexDealtCondParams.setFutureOptionDiv("3");
            l_branchIndexDealtCondParams.setEnableOrder("1");
            TestDBUtility.insertWithDel(l_branchIndexDealtCondParams);
            
            TestDBUtility.deleteAll(EnableOrderConditionRow.TYPE);
            EnableOrderConditionParams l_enableOrderConditionParams = TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setInstitutionCode("0D");
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.IFO);
            l_enableOrderConditionParams.setFutureOptionDiv("1");
            l_enableOrderConditionParams.setMarginTradingDiv("0");
            l_enableOrderConditionParams.setMarketCode("0");
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {}, new Long(333812512246L));

            WEB3ToSuccIfoOrderUnitImpl l_orderUnitImpl =
                new WEB3ToSuccIfoOrderUnitImpl(l_rsvIfoOrderUnitParams);

            List l_lisOrderUnits = new ArrayList();
            l_lisOrderUnits.add(l_orderUnitImpl);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "getOrderUnits",
                    new Class[]{ SubAccount.class,
                    ProductTypeEnum.class, String.class,String[].class,String.class },
                    l_lisOrderUnits);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccDataGettingServiceImpl",
                    "createSuccOrderUnit", new Class[]
                    {WEB3SuccOrderUnit.class, OrderUnit.class, boolean.class}, null);

            WEB3ToSuccSettingListServiceImpl l_serviceImpl = new WEB3ToSuccSettingListServiceImplForTest();
            l_serviceImpl.getListScreen(l_request);

            WEB3GentradeTradingClendarContext l_clendarContext =
                (WEB3GentradeTradingClendarContext)
                    ThreadLocalSystemAttributesRegistry.getAttribute("web3.tradingcalendarcontext");
            
            assertEquals("0", l_clendarContext.getMarketCode());
            assertEquals("11", l_clendarContext.getTradingTimeType());
            assertEquals("0", l_clendarContext.getProductCode());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * パラメータ.商品区分 == "現物株式" or "信用取引"の場合
     */
    public void testCreateSortCond_0001()
    {
        final String STR_METHOD_NAME = "testCreateSortCond_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3SuccSortKey[] l_succSortKeys = new WEB3SuccSortKey[4];
            l_succSortKeys[0] = new WEB3SuccSortKey();
            l_succSortKeys[0].keyItem = "commodityType";
            l_succSortKeys[0].ascDesc = "A";
            l_succSortKeys[1] = new WEB3SuccSortKey();
            l_succSortKeys[1].keyItem = "execCondType";
            l_succSortKeys[1].ascDesc = "A";
            l_succSortKeys[2] = new WEB3SuccSortKey();
            l_succSortKeys[2].keyItem = "orderCondType";
            l_succSortKeys[2].ascDesc = "D";
            l_succSortKeys[3] = new WEB3SuccSortKey();
            l_succSortKeys[3].keyItem = "orderDate";
            l_succSortKeys[3].ascDesc = "A";
            String l_strSortCond = l_toSuccSettingListServiceImpl.createSortCond("1", l_succSortKeys);
            
            assertEquals("execution_condition_type ASC , " +
                "nvl(org_order_condition_type, " +
                "order_condition_type) DESC , " +
                "created_timestamp ASC , " +
                "last_updated_timestamp ASC", l_strSortCond);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * パラメータ.商品区分 == "先物" or "オプション"の場合
     */
    public void testCreateSortCond_0002()
    {
        final String STR_METHOD_NAME = "testCreateSortCond_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3SuccSortKey[] l_succSortKeys = new WEB3SuccSortKey[3];
            l_succSortKeys[0] = new WEB3SuccSortKey();
            l_succSortKeys[0].keyItem = "commodityType";
            l_succSortKeys[0].ascDesc = "A";
            l_succSortKeys[1] = new WEB3SuccSortKey();
            l_succSortKeys[1].keyItem = "orderDate";
            l_succSortKeys[1].ascDesc = "A";
            l_succSortKeys[2] = new WEB3SuccSortKey();
            l_succSortKeys[2].keyItem = "orderCondType";
            l_succSortKeys[2].ascDesc = "D";
            String l_strSortCond = l_toSuccSettingListServiceImpl.createSortCond("3", l_succSortKeys);
            
            assertEquals("received_date_time ASC , last_updated_timestamp ASC", l_strSortCond);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    private class WEB3ToSuccSettingListServiceImplForTest extends WEB3ToSuccSettingListServiceImpl
    {
        protected String[] createQueryContainer(
            WEB3GentradeInstitution l_institution, 
            WEB3GentradeSubAccount l_subAccount, 
            String l_strCommodityDiv, 
            String l_strProductCode, 
            Date l_datBizDate) throws WEB3BaseException
        {
            return new String[]{"222", "111"};
        }
        
        protected WEB3SuccProductInfo[] createIfoProductInfo(List l_lisOrderUnitList) throws WEB3BaseException
        {
            WEB3SuccProductInfo l_succProductInfo = new WEB3SuccProductInfo();
            l_succProductInfo.productCode = "0";
            l_succProductInfo.productName = "ddd";
            return new WEB3SuccProductInfo[]{l_succProductInfo};
        }
    }
}
@
