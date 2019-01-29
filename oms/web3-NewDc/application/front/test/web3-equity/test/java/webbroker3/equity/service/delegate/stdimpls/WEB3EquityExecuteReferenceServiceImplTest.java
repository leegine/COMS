head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.55.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityExecuteReferenceServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3EquityExecuteReferenceServiceImplTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2007/10/18 金傑（中訊）新規作成
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
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

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.data.BranchMarketPtsDealtCondParams;
import webbroker3.gentrade.data.BranchMarketPtsDealtCondRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3EquityExecuteReferenceServiceImplTest  extends TestBaseForMock
{

    private WEB3EquityExecuteReferenceServiceImpl l_serviceImpl = null;
    
    private String l_strProductCode = null;
    
    private String l_strMarketCode = null;
    
    private Date l_datOrderBizDate = null;
    
    private String l_strOrgOrderConditionDiv = null;
    
    private String l_strOrderConditionDiv = null;
    
    private String l_strQueryCond = null;
    
    private String[] l_strQueryDataContainer = null;
    
    private List l_lisResults = null;

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3EquityExecuteReferenceServiceImplTest.class);

    public WEB3EquityExecuteReferenceServiceImplTest(String name)
    {
        super(name);
    }
    
    protected void setUp() throws Exception
    {
        super.setUp();
        this.l_serviceImpl = new WEB3EquityExecuteReferenceServiceImplForTest();
        Calendar l_calendar = Calendar.getInstance();
        l_calendar.set(Calendar.YEAR,2007);
        l_calendar.set(Calendar.MONTH,9);
        l_calendar.set(Calendar.DAY_OF_MONTH,18);
        this.l_datOrderBizDate = l_calendar.getTime();
    }
    
    protected void tearDown() throws Exception
    {
        this.l_serviceImpl = null;
        this.l_datOrderBizDate = null;
        this.l_strOrderConditionDiv = null;
        this.l_lisResults = null;
        super.tearDown();
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
            this.l_strOrderConditionDiv = "1";
            this.initData();
            this.l_strQueryCond = this.l_serviceImpl.createQueryCond(
                this.l_strProductCode,
                this.l_strMarketCode,
                this.l_datOrderBizDate,
                true,
                true,
                this.l_strOrderConditionDiv);
            
            this.l_strQueryDataContainer = this.l_serviceImpl.createQueryCondDataContainer(
                this.l_strProductCode,
                this.l_strMarketCode,
                this.l_datOrderBizDate,
                this.l_strOrderConditionDiv);
            
            this.getSearchData();
            assertEquals(3,this.l_lisResults.size());
            
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
            this.l_strOrgOrderConditionDiv = "2";
            this.l_strOrderConditionDiv = null;
            this.initData();
            this.l_strQueryCond = this.l_serviceImpl.createQueryCond(
                this.l_strProductCode,
                this.l_strMarketCode,
                this.l_datOrderBizDate,
                true,
                true,
                this.l_strOrderConditionDiv);
            
            this.l_strQueryDataContainer = this.l_serviceImpl.createQueryCondDataContainer(
                this.l_strProductCode,
                this.l_strMarketCode,
                this.l_datOrderBizDate,
                this.l_strOrderConditionDiv);
            
            this.getSearchData();
            assertEquals(5,this.l_lisResults.size());
            
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
            this.l_strOrgOrderConditionDiv = "1";
            this.l_strOrderConditionDiv = "2";
            this.initData();
            this.l_strQueryCond = this.l_serviceImpl.createQueryCond(
                this.l_strProductCode,
                this.l_strMarketCode,
                this.l_datOrderBizDate,
                true,
                true,
                this.l_strOrderConditionDiv);
            
            this.l_strQueryDataContainer = this.l_serviceImpl.createQueryCondDataContainer(
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
        final String STR_METHOD_NAME = "testCreateQueryCond_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.l_strOrgOrderConditionDiv = "1";
            this.l_strOrderConditionDiv = "1";
            this.initData();
            this.l_strQueryCond = this.l_serviceImpl.createQueryCond(
                this.l_strProductCode,
                this.l_strMarketCode,
                this.l_datOrderBizDate,
                true,
                true,
                this.l_strOrderConditionDiv);
            
            this.l_strQueryDataContainer = this.l_serviceImpl.createQueryCondDataContainer(
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
    
    /**
     * パラメータ.市場コード＝NULL（市場コード指定なし）の場合、PTS注文を検索対象外とする。
      （部店市場別・PTS）取扱条件.get取扱可能市場()取得0
     */
    public void testCreateQueryCond_C0005()
    {
        final String STR_METHOD_NAME = "testCreateQueryCond_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.initData();
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondRow.TYPE);
            
            MarketParams l_MarketParams2 = TestDBUtility.getMarketRow();
            l_MarketParams2.setMarketId(3302);
            l_MarketParams2.setMarketCode("2");
            TestDBUtility.insertWithDel(l_MarketParams2);
            
            MarketParams l_MarketParams3 = TestDBUtility.getMarketRow();
            l_MarketParams3.setMarketId(3303);
            l_MarketParams3.setMarketCode("3");
            TestDBUtility.insertWithDel(l_MarketParams3);
            
            MarketParams l_MarketParams4 = TestDBUtility.getMarketRow();
            l_MarketParams4.setMarketId(3304);
            l_MarketParams4.setMarketCode("4");
            TestDBUtility.insertWithDel(l_MarketParams4);
            
            MarketParams l_MarketParams5 = TestDBUtility.getMarketRow();
            l_MarketParams5.setMarketId(3305);
            l_MarketParams5.setMarketCode("5");
            TestDBUtility.insertWithDel(l_MarketParams5);

            this.l_strQueryCond = this.l_serviceImpl.createQueryCond(
                this.l_strProductCode,
                this.l_strMarketCode,
                this.l_datOrderBizDate,
                true,
                true,
                this.l_strOrderConditionDiv);
            
            this.l_strQueryDataContainer = this.l_serviceImpl.createQueryCondDataContainer(
                this.l_strProductCode,
                this.l_strMarketCode,
                this.l_datOrderBizDate,
                this.l_strOrderConditionDiv);
            
            this.getSearchData();
            assertEquals(5,this.l_lisResults.size());
            
            assertEquals("3301",((EqtypeOrderUnitRow)this.l_lisResults.get(0)).getMarketId() + "");
            assertEquals("3302",((EqtypeOrderUnitRow)this.l_lisResults.get(1)).getMarketId() + "");
            assertEquals("3303",((EqtypeOrderUnitRow)this.l_lisResults.get(2)).getMarketId() + "");
            assertEquals("3304",((EqtypeOrderUnitRow)this.l_lisResults.get(3)).getMarketId() + "");
            assertEquals("3305",((EqtypeOrderUnitRow)this.l_lisResults.get(4)).getMarketId() + "");
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
     * パラメータ.市場コード＝NULL（市場コード指定なし）の場合、PTS注文を検索対象外とする。
     （部店市場別・PTS）取扱条件.get取扱可能市場()取得1，
     */
    public void testCreateQueryCond_C0006()
    {
        final String STR_METHOD_NAME = "testCreateQueryCond_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.initData();
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondRow.TYPE);
            BranchMarketPtsDealtCondParams l_branchMarketPtsDealtCondParams =
                TestDBUtility.getBranchMarketPtsDealtCondRow();
            l_branchMarketPtsDealtCondParams.setMarketCode("5");
            l_branchMarketPtsDealtCondParams.setMartCanDealtEquity("1");
            TestDBUtility.insertWithDel(l_branchMarketPtsDealtCondParams);
            
            MarketParams l_MarketParams2 = TestDBUtility.getMarketRow();
            l_MarketParams2.setMarketId(3302);
            l_MarketParams2.setMarketCode("2");
            TestDBUtility.insertWithDel(l_MarketParams2);
            
            MarketParams l_MarketParams3 = TestDBUtility.getMarketRow();
            l_MarketParams3.setMarketId(3303);
            l_MarketParams3.setMarketCode("3");
            TestDBUtility.insertWithDel(l_MarketParams3);
            
            MarketParams l_MarketParams4 = TestDBUtility.getMarketRow();
            l_MarketParams4.setMarketId(3304);
            l_MarketParams4.setMarketCode("4");
            TestDBUtility.insertWithDel(l_MarketParams4);
            
            MarketParams l_MarketParams5 = TestDBUtility.getMarketRow();
            l_MarketParams5.setMarketId(3305);
            l_MarketParams5.setMarketCode("5");
            TestDBUtility.insertWithDel(l_MarketParams5);

            this.l_strQueryCond = this.l_serviceImpl.createQueryCond(
                this.l_strProductCode,
                this.l_strMarketCode,
                this.l_datOrderBizDate,
                true,
                true,
                this.l_strOrderConditionDiv);
            
            this.l_strQueryDataContainer = this.l_serviceImpl.createQueryCondDataContainer(
                this.l_strProductCode,
                this.l_strMarketCode,
                this.l_datOrderBizDate,
                this.l_strOrderConditionDiv);
            
            this.getSearchData();
            assertEquals(4,this.l_lisResults.size());
            
            assertEquals("3301",((EqtypeOrderUnitRow)this.l_lisResults.get(0)).getMarketId() + "");
            assertEquals("3302",((EqtypeOrderUnitRow)this.l_lisResults.get(1)).getMarketId() + "");
            assertEquals("3303",((EqtypeOrderUnitRow)this.l_lisResults.get(2)).getMarketId() + "");
            assertEquals("3304",((EqtypeOrderUnitRow)this.l_lisResults.get(3)).getMarketId() + "");
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
     * パラメータ.市場コード＝NULL（市場コード指定なし）の場合、PTS注文を検索対象外とする。
     （部店市場別・PTS）取扱条件.get取扱可能市場()取得3，
     */
    public void testCreateQueryCond_C0007()
    {
        final String STR_METHOD_NAME = "testCreateQueryCond_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.initData();
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondRow.TYPE);
            BranchMarketPtsDealtCondParams l_branchMarketPtsDealtCondParams =
                TestDBUtility.getBranchMarketPtsDealtCondRow();
            l_branchMarketPtsDealtCondParams.setMarketCode("5");
            l_branchMarketPtsDealtCondParams.setMartCanDealtEquity("1");
            TestDBUtility.insertWithDel(l_branchMarketPtsDealtCondParams);
            
            BranchMarketPtsDealtCondParams l_branchMarketPtsDealtCondParams1 =
                TestDBUtility.getBranchMarketPtsDealtCondRow();
            l_branchMarketPtsDealtCondParams1.setMarketCode("4");
            l_branchMarketPtsDealtCondParams1.setMartCanDealtEquity("1");
            TestDBUtility.insertWithDel(l_branchMarketPtsDealtCondParams1);
            
            BranchMarketPtsDealtCondParams l_branchMarketPtsDealtCondParams2 =
                TestDBUtility.getBranchMarketPtsDealtCondRow();
            l_branchMarketPtsDealtCondParams2.setMarketCode("3");
            l_branchMarketPtsDealtCondParams2.setMartCanDealtEquity("1");
            TestDBUtility.insertWithDel(l_branchMarketPtsDealtCondParams2);
            
            
            MarketParams l_MarketParams2 = TestDBUtility.getMarketRow();
            l_MarketParams2.setMarketId(3302);
            l_MarketParams2.setMarketCode("2");
            TestDBUtility.insertWithDel(l_MarketParams2);
            
            MarketParams l_MarketParams3 = TestDBUtility.getMarketRow();
            l_MarketParams3.setMarketId(3303);
            l_MarketParams3.setMarketCode("3");
            TestDBUtility.insertWithDel(l_MarketParams3);
            
            MarketParams l_MarketParams4 = TestDBUtility.getMarketRow();
            l_MarketParams4.setMarketId(3304);
            l_MarketParams4.setMarketCode("4");
            TestDBUtility.insertWithDel(l_MarketParams4);
            
            MarketParams l_MarketParams5 = TestDBUtility.getMarketRow();
            l_MarketParams5.setMarketId(3305);
            l_MarketParams5.setMarketCode("5");
            TestDBUtility.insertWithDel(l_MarketParams5);

            this.l_strQueryCond = this.l_serviceImpl.createQueryCond(
                this.l_strProductCode,
                this.l_strMarketCode,
                this.l_datOrderBizDate,
                true,
                true,
                this.l_strOrderConditionDiv);
            
            this.l_strQueryDataContainer = this.l_serviceImpl.createQueryCondDataContainer(
                this.l_strProductCode,
                this.l_strMarketCode,
                this.l_datOrderBizDate,
                this.l_strOrderConditionDiv);
            
            this.getSearchData();
            assertEquals(2,this.l_lisResults.size());
            
            assertEquals("3301",((EqtypeOrderUnitRow)this.l_lisResults.get(0)).getMarketId() + "");
            assertEquals("3302",((EqtypeOrderUnitRow)this.l_lisResults.get(1)).getMarketId() + "");
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
            l_eqtypeOrderUnitParams1.setMarketId(3301);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams1);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams2 = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams2.setOrderUnitId(3304148080002L);
            l_eqtypeOrderUnitParams2.setOrderType(OrderTypeEnum.EQUITY_BUY);
            l_eqtypeOrderUnitParams2.setBizDate(WEB3DateUtility.formatDate(this.l_datOrderBizDate,"yyyyMMdd"));
            l_eqtypeOrderUnitParams2.setOrgOrderConditionType(this.l_strOrgOrderConditionDiv);
            l_eqtypeOrderUnitParams2.setOrderConditionType(this.l_strOrderConditionDiv);
            l_eqtypeOrderUnitParams2.setMarketId(3302);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams2);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams3 = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams3.setOrderUnitId(3304148080003L);
            l_eqtypeOrderUnitParams3.setOrderType(OrderTypeEnum.EQUITY_BUY);
            l_eqtypeOrderUnitParams3.setBizDate(WEB3DateUtility.formatDate(this.l_datOrderBizDate,"yyyyMMdd"));
            l_eqtypeOrderUnitParams3.setOrgOrderConditionType(this.l_strOrgOrderConditionDiv);
            l_eqtypeOrderUnitParams3.setOrderConditionType(this.l_strOrderConditionDiv);
            l_eqtypeOrderUnitParams3.setMarketId(3303);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams3);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams4 = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams4.setOrderUnitId(3304148080004L);
            l_eqtypeOrderUnitParams4.setOrderType(OrderTypeEnum.EQUITY_BUY);
            l_eqtypeOrderUnitParams4.setBizDate(WEB3DateUtility.formatDate(this.l_datOrderBizDate,"yyyyMMdd"));
            l_eqtypeOrderUnitParams4.setMarketId(3304);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams4);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams5 = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams5.setOrderUnitId(3304148080005L);
            l_eqtypeOrderUnitParams5.setOrderType(OrderTypeEnum.EQUITY_BUY);
            l_eqtypeOrderUnitParams5.setBizDate(WEB3DateUtility.formatDate(this.l_datOrderBizDate,"yyyyMMdd"));
            l_eqtypeOrderUnitParams5.setMarketId(3305);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams5);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_MarketParams = TestDBUtility.getMarketRow();
            l_MarketParams.setMarketId(3301);
            l_MarketParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_MarketParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(l_subAccountParams.getAccountId());
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondRow.TYPE);
            
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
    
    private class WEB3EquityExecuteReferenceServiceImplForTest extends WEB3EquityExecuteReferenceServiceImpl
    {
        public WEB3GentradeSubAccount getSubAccount() throws WEB3BaseException
        {
            WEB3GentradeSubAccount l_subAccount = null;
            try
            {
                l_subAccount = new WEB3GentradeSubAccount(333812512203L, 33381251220301L);
            }
            catch (Exception l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                log.entering(TEST_END);
                fail();
            }
            return l_subAccount;
        }
    }
}
@
