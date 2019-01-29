head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.55.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityOrderCarryOverServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 注文繰越サービスImplTest(WEB3EquityOrderCarryOverServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/01/31 趙林鵬(中訊) 新規作成
*/

package webbroker3.equity.service.delegate.stdimpls;

import com.fitechlabs.dbind.ListPage;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;

import test.util.TestDBUtility;

import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.BranchMarketDealtCondParams;
import webbroker3.gentrade.data.BranchMarketDealtCondRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3EquityOrderCarryOverServiceImplTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityOrderCarryOverServiceImplTest.class);
    

    public WEB3EquityOrderCarryOverServiceImplTest(String arg0)
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
    
    /**
    （部店市場別）取扱条件.get（部店市場別）取扱条件() = 0件
    注文単位.市場IDが作成した繰越対象となる市場IDリストに含まれない場合は、
    株・信用の有効注文の注文単位オブジェクトを削除する。
     */
    public void testGetCarryOverOrderUnitCase0001()
    {
        final String STR_METHOD_NAME = "testGetCarryOverOrderUnitCase0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            
            //SubAccountParams
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            //MarketParams
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketParams);
            
            //BranchParams
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams =
                TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setAccountId(101001010010L);
            l_eqtypeOrderUnitParams.setSubAccountId(10100101001007L);
            l_eqtypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams1 =
                TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams1.setOrderUnitId(l_eqtypeOrderUnitParams.getOrderUnitId() + 1);
            l_eqtypeOrderUnitParams1.setAccountId(101001010010L);
            l_eqtypeOrderUnitParams1.setSubAccountId(10100101001007L);
            l_eqtypeOrderUnitParams1.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeOrderUnitParams1.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams1);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams2 =
                TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams2.setOrderUnitId(l_eqtypeOrderUnitParams.getOrderUnitId() + 2);
            l_eqtypeOrderUnitParams2.setAccountId(101001010010L);
            l_eqtypeOrderUnitParams2.setSubAccountId(10100101001007L);
            l_eqtypeOrderUnitParams2.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeOrderUnitParams2.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams2);

            TestDBUtility.deleteAll(BranchMarketDealtCondRow.TYPE);

            WEB3EquityOrderCarryOverServiceImpl l_impl = new WEB3EquityOrderCarryOverServiceImpl();
            
            WEB3GentradeMainAccount l_account = new WEB3GentradeMainAccount(101001010010L);
            ListPage l_lisPages = l_impl.getCarryOverOrderUnit(l_account);
            
            assertNull(l_lisPages);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
    （部店市場別）取扱条件.get（部店市場別）取扱条件() = 1件
    注文単位.市場IDが作成した繰越対象となる市場IDリストに含まれない場合は、
    株・信用の有効注文の注文単位オブジェクトを削除する。
     */
    public void testGetCarryOverOrderUnitCase0002()
    {
        final String STR_METHOD_NAME = "testGetCarryOverOrderUnitCase0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            
            //SubAccountParams
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            //MarketParams
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3301);
            l_marketParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketParams);
            
            //BranchParams
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams =
                TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setAccountId(101001010010L);
            l_eqtypeOrderUnitParams.setSubAccountId(10100101001007L);
            l_eqtypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setMarketId(3301);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams1 =
                TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams1.setOrderUnitId(l_eqtypeOrderUnitParams.getOrderUnitId() + 1);
            l_eqtypeOrderUnitParams1.setAccountId(101001010010L);
            l_eqtypeOrderUnitParams1.setSubAccountId(10100101001007L);
            l_eqtypeOrderUnitParams1.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeOrderUnitParams1.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams1.setMarketId(3302);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams1);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams2 =
                TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams2.setOrderUnitId(l_eqtypeOrderUnitParams.getOrderUnitId() + 2);
            l_eqtypeOrderUnitParams2.setAccountId(101001010010L);
            l_eqtypeOrderUnitParams2.setSubAccountId(10100101001007L);
            l_eqtypeOrderUnitParams2.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeOrderUnitParams2.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams2.setMarketId(3303);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams2);

            TestDBUtility.deleteAll(BranchMarketDealtCondRow.TYPE);
            BranchMarketDealtCondParams l_branchMarketDealtCondParams = TestDBUtility.getBranchMarketDealCondRow();
            l_branchMarketDealtCondParams.setMartCanDealtEquity("1");
            l_branchMarketDealtCondParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_branchMarketDealtCondParams);

            WEB3EquityOrderCarryOverServiceImpl l_impl = new WEB3EquityOrderCarryOverServiceImpl();
            
            WEB3GentradeMainAccount l_account = new WEB3GentradeMainAccount(101001010010L);
            ListPage l_lisPages = l_impl.getCarryOverOrderUnit(l_account);
            
            assertEquals(1, l_lisPages.totalSize());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
    （部店市場別）取扱条件.get（部店市場別）取扱条件() = 3件
    注文単位.市場IDが作成した繰越対象となる市場IDリストに含まれない場合は、
    株・信用の有効注文の注文単位オブジェクトを削除する。
     */
    public void testGetCarryOverOrderUnitCase0003()
    {
        final String STR_METHOD_NAME = "testGetCarryOverOrderUnitCase0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            
            //SubAccountParams
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            //MarketParams
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3301);
            l_marketParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketParams);

            MarketParams l_marketParams1 = TestDBUtility.getMarketRow();
            l_marketParams1.setMarketId(3302);
            l_marketParams1.setMarketCode("2");
            TestDBUtility.insertWithDel(l_marketParams1);

            MarketParams l_marketParams2 = TestDBUtility.getMarketRow();
            l_marketParams2.setMarketId(3303);
            l_marketParams2.setMarketCode("3");
            TestDBUtility.insertWithDel(l_marketParams2);
            
            //BranchParams
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams =
                TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setAccountId(101001010010L);
            l_eqtypeOrderUnitParams.setSubAccountId(10100101001007L);
            l_eqtypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setMarketId(3301);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams1 =
                TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams1.setOrderUnitId(l_eqtypeOrderUnitParams.getOrderUnitId() + 1);
            l_eqtypeOrderUnitParams1.setAccountId(101001010010L);
            l_eqtypeOrderUnitParams1.setSubAccountId(10100101001007L);
            l_eqtypeOrderUnitParams1.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeOrderUnitParams1.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams1.setMarketId(3302);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams1);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams2 =
                TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams2.setOrderUnitId(l_eqtypeOrderUnitParams.getOrderUnitId() + 2);
            l_eqtypeOrderUnitParams2.setAccountId(101001010010L);
            l_eqtypeOrderUnitParams2.setSubAccountId(10100101001007L);
            l_eqtypeOrderUnitParams2.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeOrderUnitParams2.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams2.setMarketId(3303);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams2);

            TestDBUtility.deleteAll(BranchMarketDealtCondRow.TYPE);
            BranchMarketDealtCondParams l_branchMarketDealtCondParams = TestDBUtility.getBranchMarketDealCondRow();
            l_branchMarketDealtCondParams.setMartCanDealtEquity("1");
            l_branchMarketDealtCondParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_branchMarketDealtCondParams);
            
            BranchMarketDealtCondParams l_branchMarketDealtCondParams1 = TestDBUtility.getBranchMarketDealCondRow();
            l_branchMarketDealtCondParams1.setMartCanDealtEquity("1");
            l_branchMarketDealtCondParams1.setMarketCode("2");
            TestDBUtility.insertWithDel(l_branchMarketDealtCondParams1);
            
            BranchMarketDealtCondParams l_branchMarketDealtCondParams2 = TestDBUtility.getBranchMarketDealCondRow();
            l_branchMarketDealtCondParams2.setMartCanDealtEquity("1");
            l_branchMarketDealtCondParams2.setMarketCode("3");
            TestDBUtility.insertWithDel(l_branchMarketDealtCondParams2);

            WEB3EquityOrderCarryOverServiceImpl l_impl = new WEB3EquityOrderCarryOverServiceImpl();
            
            WEB3GentradeMainAccount l_account = new WEB3GentradeMainAccount(101001010010L);
            ListPage l_lisPages = l_impl.getCarryOverOrderUnit(l_account);
            
            assertEquals(3, l_lisPages.totalSize());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 市場ID一覧を作成0條
     株・信用の有効注文の注文単位オブジェクトを全て取得する。
     */
    public void testGetMainAccountsCase0001()
    {
        final String STR_METHOD_NAME = "testGetMainAccountsCase0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //InstitutionParams
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            //MarketParams
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3301);
            l_marketParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(BranchMarketDealtCondRow.TYPE);
            
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams =
                TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setAccountId(101001010010L);
            l_eqtypeOrderUnitParams.setSubAccountId(10100101001007L);
            l_eqtypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setMarketId(3301);
            l_eqtypeOrderUnitParams.setBizDate(
                WEB3DateUtility.formatDate(GtlUtils.getTradingSystem().getBizDate(), "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            //BranchParams
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            WEB3EquityOrderCarryOverServiceImpl l_impl = new WEB3EquityOrderCarryOverServiceImpl();
            
            WEB3GentradeInstitution l_institution =
                new WEB3GentradeInstitution(l_institutionParams.getInstitutionId());
            
            WEB3GentradeMainAccount[] l_accounts = l_impl.getMainAccounts(l_institution, 0, 999999999999L);
            
            assertEquals(l_accounts.length, 1);
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 市場ID一覧を作成1條
     株・信用の有効注文の注文単位オブジェクトを全て取得する。
     */
    public void testGetMainAccountsCase0002()
    {
        final String STR_METHOD_NAME = "testGetMainAccountsCase0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //InstitutionParams
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            //MarketParams
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3301);
            l_marketParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(BranchMarketDealtCondRow.TYPE);
            BranchMarketDealtCondParams l_branchMarketDealtCondParams =
                TestDBUtility.getBranchMarketDealCondRow();
            l_branchMarketDealtCondParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_branchMarketDealtCondParams);
            
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams =
                TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setAccountId(101001010010L);
            l_eqtypeOrderUnitParams.setSubAccountId(10100101001007L);
            l_eqtypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setMarketId(3301);
            l_eqtypeOrderUnitParams.setBizDate(
                WEB3DateUtility.formatDate(GtlUtils.getTradingSystem().getBizDate(), "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            //BranchParams
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            WEB3EquityOrderCarryOverServiceImpl l_impl = new WEB3EquityOrderCarryOverServiceImpl();
            
            WEB3GentradeInstitution l_institution =
                new WEB3GentradeInstitution(l_institutionParams.getInstitutionId());
            
            WEB3GentradeMainAccount[] l_accounts = l_impl.getMainAccounts(l_institution, 0, 999999999999L);
            
            assertEquals(l_accounts.length, 1);
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 市場ID一覧を作成3條
     株・信用の有効注文の注文単位オブジェクトを全て取得する。
     */
    public void testGetMainAccountsCase0003()
    {
        final String STR_METHOD_NAME = "testGetMainAccountsCase0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //InstitutionParams
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            //MarketParams
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3301);
            l_marketParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketParams);
            
            MarketParams l_marketParams1 = TestDBUtility.getMarketRow();
            l_marketParams1.setMarketId(3302);
            l_marketParams1.setMarketCode("2");
            TestDBUtility.insertWithDel(l_marketParams1);
            
            MarketParams l_marketParams2 = TestDBUtility.getMarketRow();
            l_marketParams2.setMarketId(3303);
            l_marketParams2.setMarketCode("3");
            TestDBUtility.insertWithDel(l_marketParams2);

            TestDBUtility.deleteAll(BranchMarketDealtCondRow.TYPE);
            BranchMarketDealtCondParams l_branchMarketDealtCondParams =
                TestDBUtility.getBranchMarketDealCondRow();
            l_branchMarketDealtCondParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_branchMarketDealtCondParams);
            
            BranchMarketDealtCondParams l_branchMarketDealtCondParams1 =
                TestDBUtility.getBranchMarketDealCondRow();
            l_branchMarketDealtCondParams1.setMarketCode("2");
            TestDBUtility.insertWithDel(l_branchMarketDealtCondParams1);
            
            BranchMarketDealtCondParams l_branchMarketDealtCondParams2 =
                TestDBUtility.getBranchMarketDealCondRow();
            l_branchMarketDealtCondParams2.setMarketCode("3");
            TestDBUtility.insertWithDel(l_branchMarketDealtCondParams2);
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams =
                TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setAccountId(101001010010L);
            l_eqtypeOrderUnitParams.setSubAccountId(10100101001007L);
            l_eqtypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setMarketId(3301);
            l_eqtypeOrderUnitParams.setBizDate(
                WEB3DateUtility.formatDate(GtlUtils.getTradingSystem().getBizDate(), "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams1 =
                TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams1.setOrderUnitId(l_eqtypeOrderUnitParams.getOrderUnitId() + 1);
            l_eqtypeOrderUnitParams1.setAccountId(101001010011L);
            l_eqtypeOrderUnitParams1.setSubAccountId(10100101001007L);
            l_eqtypeOrderUnitParams1.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeOrderUnitParams1.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams1.setMarketId(3302);
            l_eqtypeOrderUnitParams1.setBizDate(
                WEB3DateUtility.formatDate(GtlUtils.getTradingSystem().getBizDate(), "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams1);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams2 =
                TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams2.setOrderUnitId(l_eqtypeOrderUnitParams.getOrderUnitId() + 2);
            l_eqtypeOrderUnitParams2.setAccountId(101001010012L);
            l_eqtypeOrderUnitParams2.setSubAccountId(10100101001007L);
            l_eqtypeOrderUnitParams2.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeOrderUnitParams2.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams2.setMarketId(3303);
            l_eqtypeOrderUnitParams2.setBizDate(
                WEB3DateUtility.formatDate(GtlUtils.getTradingSystem().getBizDate(), "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams2);

            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setAccountCode("101001");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            MainAccountParams l_mainAccountParams1 = TestDBUtility.getMainAccountRow();
            l_mainAccountParams1.setAccountId(101001010011L);
            l_mainAccountParams1.setAccountCode("101002");
            TestDBUtility.insertWithDel(l_mainAccountParams1);
            
            MainAccountParams l_mainAccountParams2 = TestDBUtility.getMainAccountRow();
            l_mainAccountParams2.setAccountId(101001010012L);
            l_mainAccountParams2.setAccountCode("101003");
            TestDBUtility.insertWithDel(l_mainAccountParams2);
            
            //BranchParams
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            WEB3EquityOrderCarryOverServiceImpl l_impl = new WEB3EquityOrderCarryOverServiceImpl();
            
            WEB3GentradeInstitution l_institution =
                new WEB3GentradeInstitution(l_institutionParams.getInstitutionId());
            
            WEB3GentradeMainAccount[] l_accounts = l_impl.getMainAccounts(l_institution, 0, 999999999999L);
            
            assertEquals(l_accounts.length, 3);
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
