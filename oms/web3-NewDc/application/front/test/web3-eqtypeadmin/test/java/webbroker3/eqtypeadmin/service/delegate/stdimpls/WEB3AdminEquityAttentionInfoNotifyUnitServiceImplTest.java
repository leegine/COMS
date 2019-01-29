head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.08.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminEquityAttentionInfoNotifyUnitServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (WEB3AdminEquityAttentionInfoNotifyHandlerTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/01/08 張少傑(中訊) 新規作成 モデルNo.219
*/

package webbroker3.eqtypeadmin.service.delegate.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductDao;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductUpdqDao;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductUpdqParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductUpdqRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;

import test.util.TestDBUtility;

import webbroker3.eqtypeadmin.data.HostAttentionInfoNotifyParams;
import webbroker3.eqtypeadmin.data.HostAttentionInfoNotifyRow;
import webbroker3.gentrade.data.InstitutionPreferencesParams;
import webbroker3.gentrade.data.InstitutionPreferencesRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminEquityAttentionInfoNotifyUnitServiceImplTest extends TestBaseForMock
{

    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityAttentionInfoNotifyUnitServiceImplTest.class);

    WEB3AdminEquityAttentionInfoNotifyUnitServiceImpl l_impl =
        new WEB3AdminEquityAttentionInfoNotifyUnitServiceImpl();
    
    public WEB3AdminEquityAttentionInfoNotifyUnitServiceImplTest(String arg0)
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

    /*１）　@　@キューに必要な情報が格納されていない場合、「2：銘柄未更新」をreturnする。 
 　@　@　@[条件] 
 　@　@　@　@注意情報通知キューテーブル.基準値＝null */
    public void testNotifyLimitRangeInfo_C001()
    {
        final String STR_METHOD_NAME = "testNotifyLimitRangeInfo_C001";
        log.entering(STR_METHOD_NAME);

        try
        {
            HostAttentionInfoNotifyParams l_hostAttentionInfoNotifyParams =
                new HostAttentionInfoNotifyParams();

            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_ProductParams = TestDBUtility.getProductRow();
            l_ProductParams.setEstimationPrice(50);
            l_ProductParams.setLastUpdater("XXXX");
            l_ProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_ProductParams);
            
//            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
//            EqtypeProductParams l_EqtypeProductParams = TestDBUtility.getEqtypeProductRow();
//            l_EqtypeProductParams.setProductId(3304148080000L);
//            TestDBUtility.insertWithDel(l_EqtypeProductParams);
            
//            TestDBUtility.deleteAll(TradedProductRow.TYPE);
//            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
//            l_tradedProductParams.setProductId(3304148080000L);
//            l_tradedProductParams.setMarketId(3303L);
//            l_tradedProductParams.setInstitutionCode("0D");
//            TestDBUtility.insertWithDel(l_tradedProductParams);

            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams =
                TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setTradedProductId(1006160060005L);
            l_eqtypeTradedProductParams.setProductId(3304148080000L);
            l_eqtypeTradedProductParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
//            
//            TestDBUtility.deleteAll(MarketRow.TYPE);
//            MarketParams l_MarketParams = TestDBUtility.getMarketRow();
//            TestDBUtility.insertWithDel(l_MarketParams);
//            
//            WEB3EquityTradedProduct l_equityTradedProduct =
//                new WEB3EquityTradedProduct(l_tradedProductParams);
            String l_strUpdateFlag =
                l_impl.notifyLimitRangeInfo(l_hostAttentionInfoNotifyParams, l_eqtypeTradedProductParams, null, l_ProductParams);
            assertEquals("2", l_strUpdateFlag);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //２）　@証券会社プリファ@レンステーブルを取得する。 
    //　@　@　@[検索条件] 
    //　@　@　@　@証券会社ＩＤ：　@証券会社.証券会社ＩＤ 
    //　@　@　@　@プリファ@レンス名：　@"attention.info.comp.taking.div" 
    //　@　@　@　@プリファ@レンス名の連番：　@"1"（固定） 
    //３）　@２）で証券会社プリファ@レンステーブルが取得出来なかった場合
    //　@　@　@「2：銘柄未更新」をreturnする。 
    public void testNotifyLimitRangeInfo_C002()
    {
        final String STR_METHOD_NAME = "testNotifyLimitRangeInfo_C002";
        log.entering(STR_METHOD_NAME);

        try
        {
//            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp",
//                 new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));
//            
//            ThreadLocalSystemAttributesRegistry.setAttribute("web3.attributes.basetimestampfororderbizdate",
//                 new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));
//            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
//
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
//                    "getSystemTimestamp",
//                    new Class[] {},
//                    new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));  
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            l_InstitutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_InstitutionParams);
            
            TestDBUtility.deleteAll(HostAttentionInfoNotifyRow.TYPE);
            HostAttentionInfoNotifyParams l_hostAttentionInfoNotifyParams =
                new HostAttentionInfoNotifyParams();
            l_hostAttentionInfoNotifyParams.setBasePrice(100);
            l_hostAttentionInfoNotifyParams.setHighPriceRange(1000);
            l_hostAttentionInfoNotifyParams.setLowPriceRange(10);
            l_hostAttentionInfoNotifyParams.setRowid("AXRY1");
            l_hostAttentionInfoNotifyParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_hostAttentionInfoNotifyParams);
            
            TestDBUtility.deleteAll(InstitutionPreferencesRow.TYPE);
            
//            TestDBUtility.deleteAll(EqtypeProductConditionRow.TYPE);
//            EqtypeProductConditionParams l_eqtypeProductConditionParams = new EqtypeProductConditionParams();
//            l_eqtypeProductConditionParams.setEqtypeProductConditionId(2086L);
//            l_eqtypeProductConditionParams.setInstitutionCode("0D");
//            l_eqtypeProductConditionParams.setProductCode("13320");
//            l_eqtypeProductConditionParams.setProductId(2601133200000L);
//            l_eqtypeProductConditionParams.setMarketCode("1");
//            l_eqtypeProductConditionParams.setMarketId(2601L);
//            l_eqtypeProductConditionParams.setTableName("eqtype_traded_product");
//            l_eqtypeProductConditionParams.setColumnName("price_range_unit_type");
//            l_eqtypeProductConditionParams.setLargeItemDiv("5");
//            l_eqtypeProductConditionParams.setSmallItemDiv("51");
//            l_eqtypeProductConditionParams.setDataToday("511");
//            l_eqtypeProductConditionParams.setDataNextDay("511");
//            l_eqtypeProductConditionParams.setDataPlan("511");
//            l_eqtypeProductConditionParams.setTermFrom(WEB3DateUtility.getDate("11110101","yyyy/MM/dd HH:mm:ss"));
//            l_eqtypeProductConditionParams.setTermTo(WEB3DateUtility.getDate("9999213","yyyy/MM/dd HH:mm:ss"));
//            l_eqtypeProductConditionParams.setDeleteFlg("0");
//            l_eqtypeProductConditionParams.setLastUpdater("dir");
//            l_eqtypeProductConditionParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_eqtypeProductConditionParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
//            TestDBUtility.insertWithDel(l_eqtypeProductConditionParams);
//            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_ProductParams = TestDBUtility.getProductRow();
            l_ProductParams.setEstimationPrice(50);
            l_ProductParams.setLastUpdater("XXXX");
            l_ProductParams.setProductId(2601133200000L);
            TestDBUtility.insertWithDel(l_ProductParams);
            
//            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
//            EqtypeProductParams l_EqtypeProductParams = TestDBUtility.getEqtypeProductRow();
//            l_EqtypeProductParams.setProductId(2601133200000L);
//            TestDBUtility.insertWithDel(l_EqtypeProductParams);
            
//            TestDBUtility.deleteAll(TradedProductRow.TYPE);
//            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
//            l_tradedProductParams.setTradedProductId(1006160060005L);
//            l_tradedProductParams.setProductId(2601133200000L);
//            l_tradedProductParams.setMarketId(2601L);
//            l_tradedProductParams.setInstitutionCode("0D");
//            TestDBUtility.insertWithDel(l_tradedProductParams);

            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams =
                TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setTradedProductId(1006160060005L);
            l_eqtypeTradedProductParams.setProductId(2601133200000L);
            l_eqtypeTradedProductParams.setMarketId(2601L);
            l_eqtypeTradedProductParams.setValidUntilBizDate("20040917");
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
//            
//            TestDBUtility.deleteAll(MarketRow.TYPE);
//            MarketParams l_MarketParams = TestDBUtility.getMarketRow();
//            l_MarketParams.setMarketCode("1");
//            l_MarketParams.setMarketId(2601L);
//            TestDBUtility.insertWithDel(l_MarketParams);
//            
//            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
//            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
//            l_tradingTimeParams.setInstitutionCode("0D");
//            l_tradingTimeParams.setBranchCode("123");
//            l_tradingTimeParams.setMarketCode("N1");
//            l_tradingTimeParams.setTradingTimeType("01");
//            l_tradingTimeParams.setProductCode("0");
//            l_tradingTimeParams.setBizDateType("1");
//            l_tradingTimeParams.setBizdateCalcParameter("0");
//            TestDBUtility.insertWithDel(l_tradingTimeParams);
//             
//            WEB3EquityTradedProduct l_equityTradedProduct =
//                new WEB3EquityTradedProduct(l_tradedProductParams);
            String l_strUpdateFlag =
                l_impl.notifyLimitRangeInfo(l_hostAttentionInfoNotifyParams, l_eqtypeTradedProductParams, null, l_ProductParams);
            assertEquals("2", l_strUpdateFlag);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //２）　@証券会社プリファ@レンステーブルを取得する。 
    //　@　@　@[検索条件] 
    //　@　@　@　@証券会社ＩＤ：　@証券会社.証券会社ＩＤ 
    //　@　@　@　@プリファ@レンス名：　@"attention.info.comp.taking.div" 
    //　@　@　@　@プリファ@レンス名の連番：　@"1"（固定） 
    //３）　@証券会社プリファ@レンステーブル.プリファ@レンスの値が"取込まない"の場合
    //　@　@　@「2：銘柄未更新」をreturnする。 
    public void testNotifyLimitRangeInfo_C003()
    {
        final String STR_METHOD_NAME = "testNotifyLimitRangeInfo_C003";
        log.entering(STR_METHOD_NAME);

        try
        {
//            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp",
//                 new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));
//            
//            ThreadLocalSystemAttributesRegistry.setAttribute("web3.attributes.basetimestampfororderbizdate",
//                 new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));
//            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
//
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
//                    "getSystemTimestamp",
//                    new Class[] {},
//                    new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));  
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            l_InstitutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_InstitutionParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_ProductParams = TestDBUtility.getProductRow();
            l_ProductParams.setEstimationPrice(50);
            l_ProductParams.setLastUpdater("XXXX");
            l_ProductParams.setProductId(2601133200000L);
            TestDBUtility.insertWithDel(l_ProductParams);
            
//            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
//            EqtypeProductParams l_EqtypeProductParams = TestDBUtility.getEqtypeProductRow();
//            l_EqtypeProductParams.setProductId(2601133200000L);
//            TestDBUtility.insertWithDel(l_EqtypeProductParams);
            
//            TestDBUtility.deleteAll(TradedProductRow.TYPE);
//            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
//            l_tradedProductParams.setTradedProductId(1006160060005L);
//            l_tradedProductParams.setProductId(2601133200000L);
//            l_tradedProductParams.setMarketId(2601L);
//            l_tradedProductParams.setInstitutionCode("0D");
//            TestDBUtility.insertWithDel(l_tradedProductParams);

            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams =
                TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setTradedProductId(1006160060005L);
            l_eqtypeTradedProductParams.setProductId(2601133200000L);
            l_eqtypeTradedProductParams.setMarketId(2601L);
            l_eqtypeTradedProductParams.setValidUntilBizDate("20040917");
            l_eqtypeTradedProductParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);

            TestDBUtility.deleteAll(HostAttentionInfoNotifyRow.TYPE);
            HostAttentionInfoNotifyParams l_hostAttentionInfoNotifyParams =
                new HostAttentionInfoNotifyParams();
            l_hostAttentionInfoNotifyParams.setBasePrice(100);
            l_hostAttentionInfoNotifyParams.setHighPriceRange(1000);
            l_hostAttentionInfoNotifyParams.setLowPriceRange(10);
            l_hostAttentionInfoNotifyParams.setRowid("AXRY1");
            l_hostAttentionInfoNotifyParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_hostAttentionInfoNotifyParams);
            
            TestDBUtility.deleteAll(InstitutionPreferencesRow.TYPE);
            InstitutionPreferencesParams l_institutionPreferencesParams = TestDBUtility.getInstitutionPreferencesRow();
            l_institutionPreferencesParams.setValue("0");
            l_institutionPreferencesParams.setInstitutionId(33);
            l_institutionPreferencesParams.setName("attention.info.comp.taking.div");
            l_institutionPreferencesParams.setNameSerialNo(1);
            TestDBUtility.insertWithDel(l_institutionPreferencesParams);
 
            String l_strUpdateFlag =
                l_impl.notifyLimitRangeInfo(l_hostAttentionInfoNotifyParams, l_eqtypeTradedProductParams, null, l_ProductParams);
            assertEquals("2", l_strUpdateFlag);

        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    //銘柄.優先市場IDがnullの場合 & 株式取引銘柄UPDQを取得している場合
    //銘柄を更新する
    // 評価単価 = 注意情報通知キュー.基準値(100)
    // 更新者コード = 注意情報通知キュー.データコード(AXRY1)
    //株式取引銘柄を更新す
    // 基準値（終値） = 注意情報通知キュー.基準値(100)
    // 値幅チェック区分 = "1:値幅チェックあり"
    // 値幅区分 = 『強制値幅（上限値）』『強制値幅（下限値）』を更新した結果、どちらの項目もnullでない場合、"1：円"
    // 注意情報通知キュー.制限値幅上限(200) 注意情報通知キュー.制限値幅下限(10)
    // 強制値幅（上限値）= 注意情報通知キュー.制限値幅上限-注意情報通知キュー.基準値(計算結果が100の場合)
    // 強制値幅（下限値）= 注意情報通知キュー.基準値-注意情報通知キュー.制限値幅下限(計算結果が90の場合)
    // 更新者コード = 注意情報通知キュー.データコード(AXRY1)
    // 基準値 = 注意情報通知キュー.基準値(100)
    //株式取引銘柄UPDQを更新する
    // 基準値（終値） = 注意情報通知キュー.基準値(100)
    // 更新者コード = 注意情報通知キュー.データコード(AXRY1)
    // 基準値 = 注意情報通知キュー.基準値(100)
    public void testNotifyLimitRangeInfo_C004()
    {
        final String STR_METHOD_NAME = "testNotifyLimitRangeInfo_C004";
        log.entering(STR_METHOD_NAME);

        try
        {
//            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp",
//                 new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));
//            
//            ThreadLocalSystemAttributesRegistry.setAttribute("web3.attributes.basetimestampfororderbizdate",
//                 new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));
//            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
//
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
//                    "getSystemTimestamp",
//                    new Class[] {},
//                    new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));  
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            l_InstitutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_InstitutionParams);
            
            TestDBUtility.deleteAll(HostAttentionInfoNotifyRow.TYPE);
            HostAttentionInfoNotifyParams l_hostAttentionInfoNotifyParams =
                new HostAttentionInfoNotifyParams();
            l_hostAttentionInfoNotifyParams.setBasePrice(100);
            l_hostAttentionInfoNotifyParams.setHighPriceRange(200);
            l_hostAttentionInfoNotifyParams.setLowPriceRange(10);
            l_hostAttentionInfoNotifyParams.setRequestCode("AXRY1");
            l_hostAttentionInfoNotifyParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_hostAttentionInfoNotifyParams);
            
//            TestDBUtility.deleteAll(EqtypeProductConditionRow.TYPE);
            
            TestDBUtility.deleteAll(InstitutionPreferencesRow.TYPE);
            InstitutionPreferencesParams l_institutionPreferencesParams = TestDBUtility.getInstitutionPreferencesRow();
            l_institutionPreferencesParams.setValue("1");
            l_institutionPreferencesParams.setInstitutionId(33);
            l_institutionPreferencesParams.setName("attention.info.comp.taking.div");
            l_institutionPreferencesParams.setNameSerialNo(1);
            TestDBUtility.insertWithDel(l_institutionPreferencesParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_ProductParams = TestDBUtility.getProductRow();
            l_ProductParams.setEstimationPrice(50);//評価単価
            l_ProductParams.setLastUpdater("XXXX");//更新者コード
            l_ProductParams.setProductId(2601133200000L);
            l_ProductParams.setPrimaryMarketId(null);//銘柄.優先市場IDがnull
            TestDBUtility.insertWithDel(l_ProductParams);
            
//            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
//            EqtypeProductParams l_EqtypeProductParams = TestDBUtility.getEqtypeProductRow();
//            l_EqtypeProductParams.setProductId(2601133200000L);
//            TestDBUtility.insertWithDel(l_EqtypeProductParams);
            
//            TestDBUtility.deleteAll(TradedProductRow.TYPE);
//            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
//            l_tradedProductParams.setTradedProductId(1006160060005L);
//            l_tradedProductParams.setProductId(2601133200000L);
//            l_tradedProductParams.setMarketId(2601L);
//            l_tradedProductParams.setInstitutionCode("0D");
//            TestDBUtility.insertWithDel(l_tradedProductParams);

            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams =
                TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setTradedProductId(1006160060005L);
            l_eqtypeTradedProductParams.setProductId(2601133200000L);
            l_eqtypeTradedProductParams.setMarketId(2601L);
            l_eqtypeTradedProductParams.setValidUntilBizDate("20040917");
            l_eqtypeTradedProductParams.setLastClosingPrice(50);//基準値（終値）
            l_eqtypeTradedProductParams.setPriceRangeType("0");//値幅チェック区分
            l_eqtypeTradedProductParams.setPriceRangeUnitType("0");//値幅区分
            l_eqtypeTradedProductParams.setHighCompulsivePriceRange(99);//強制値幅（上限値）
            l_eqtypeTradedProductParams.setLowCompulsivePriceRange(1);//強制値幅（下限値）
            l_eqtypeTradedProductParams.setLastUpdater("XXXX");//更新者コード
            l_eqtypeTradedProductParams.setBasePrice(50);//基準値
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductUpdqRow.TYPE);
            EqtypeTradedProductUpdqParams l_eqtypeTradedProductUpdqParams =
                TestDBUtility.getEqtypeTradedProductUpdqRow();
            l_eqtypeTradedProductUpdqParams.setTradedProductId(1006160060005L);
            l_eqtypeTradedProductUpdqParams.setProductId(2601133200000L);
            l_eqtypeTradedProductUpdqParams.setMarketId(2601L);
            l_eqtypeTradedProductUpdqParams.setValidUntilBizDate("20040917");
            l_eqtypeTradedProductUpdqParams.setLastClosingPrice(50);//基準値（終値）
            l_eqtypeTradedProductUpdqParams.setLastUpdater("XXXX");//更新者コード
            l_eqtypeTradedProductUpdqParams.setBasePrice(50);//基準値
            TestDBUtility.insertWithDel(l_eqtypeTradedProductUpdqParams);
            
//            TestDBUtility.deleteAll(MarketRow.TYPE);
//            MarketParams l_MarketParams = TestDBUtility.getMarketRow();
//            l_MarketParams.setMarketCode("1");
//            l_MarketParams.setMarketId(2601L);
//            TestDBUtility.insertWithDel(l_MarketParams);
//            
//            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
//            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
//            l_tradingTimeParams.setInstitutionCode("0D");
//            l_tradingTimeParams.setBranchCode("123");
//            l_tradingTimeParams.setMarketCode("N1");
//            l_tradingTimeParams.setTradingTimeType("01");
//            l_tradingTimeParams.setProductCode("0");
//            l_tradingTimeParams.setBizDateType("1");
//            l_tradingTimeParams.setBizdateCalcParameter("0");
//            TestDBUtility.insertWithDel(l_tradingTimeParams);
//             
//            WEB3EquityTradedProduct l_equityTradedProduct =
//                new WEB3EquityTradedProduct(l_tradedProductParams);
            String l_strUpdateFlag =
            l_impl.notifyLimitRangeInfo(l_hostAttentionInfoNotifyParams, l_eqtypeTradedProductParams, 
                    l_eqtypeTradedProductUpdqParams, l_ProductParams);
            assertEquals("1", l_strUpdateFlag);
            
            //銘柄を更新する
            ProductRow l_product = ProductDao.findRowByPk(2601133200000L);
            // 評価単価 = 注意情報通知キュー.基準値(100)
            assertEquals(100, l_product.getEstimationPrice(), 0);
            // 更新者コード = 注意情報通知キュー.データコード(AXRY1)
            assertEquals("AXRY1", l_product.getLastUpdater());
            
            //株式取引銘柄を更新す
            EqtypeTradedProductRow l_listEqtypeTradedProduct =
                EqtypeTradedProductDao.findRowByPk(1006160060005L);
            // 基準値（終値） = 注意情報通知キュー.基準値(100)
            assertEquals(100, l_listEqtypeTradedProduct.getLastClosingPrice(), 0);
            // 値幅チェック区分 = "1:値幅チェックあり"
            assertEquals("1", l_listEqtypeTradedProduct.getPriceRangeType());
            // 値幅区分 = 『強制値幅（上限値）』『強制値幅（下限値）』を更新した結果、どちらの項目もnullでない場合、"1：円"
            assertEquals("1", l_listEqtypeTradedProduct.getPriceRangeUnitType());
            // 注意情報通知キュー.制限値幅上限(200) 注意情報通知キュー.制限値幅下限(10)
            // 強制値幅（上限値）= 注意情報通知キュー.制限値幅上限-注意情報通知キュー.基準値(計算結果が0以上の場合)
            assertEquals(100, l_listEqtypeTradedProduct.getHighCompulsivePriceRange(), 0);
            // 強制値幅（下限値）= 注意情報通知キュー.基準値-注意情報通知キュー.制限値幅下限(計算結果が0以上の場合)
            assertEquals(90, l_listEqtypeTradedProduct.getLowCompulsivePriceRange(), 0);
            // 更新者コード = 注意情報通知キュー.データコード(AXRY1)
            assertEquals("AXRY1", l_listEqtypeTradedProduct.getLastUpdater());
            // 基準値 = 注意情報通知キュー.基準値(100)
            assertEquals(100, l_listEqtypeTradedProduct.getBasePrice(), 0);
            
            //株式取引銘柄UPDQを更新する
            EqtypeTradedProductUpdqRow l_listEqtypeTradedProductUpdq =
                EqtypeTradedProductUpdqDao.findRowByPk(1006160060005L, "20040917");
            // 基準値（終値） = 注意情報通知キュー.基準値(100)
            assertEquals(100, l_listEqtypeTradedProductUpdq.getLastClosingPrice(), 0);
            // 更新者コード = 注意情報通知キュー.データコード(AXRY1)
            assertEquals("AXRY1", l_listEqtypeTradedProductUpdq.getLastUpdater());
            // 基準値 = 注意情報通知キュー.基準値(100)
            assertEquals(100, l_listEqtypeTradedProductUpdq.getBasePrice(), 0);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //銘柄.優先市場IDと注意情報通知キューテーブル.市場コード（SONAR）に 
    //　@　@該当する市場IDが等しい場合 & 株式取引銘柄UPDQを取得している場合
    //銘柄を更新する
    // 評価単価 = 注意情報通知キュー.基準値(100)
    // 更新者コード = 注意情報通知キュー.データコード(AXRY1)
    //株式取引銘柄を更新す
    // 基準値（終値） = 注意情報通知キュー.基準値(100)
    // 値幅チェック区分 = "1:値幅チェックあり"
    // 値幅区分 = 既存値"0"
    // 注意情報通知キュー.制限値幅上限 = null 注意情報通知キュー.制限値幅下限(10)
    // 強制値幅（上限値）= null
    // 強制値幅（下限値）= 注意情報通知キュー.基準値-注意情報通知キュー.制限値幅下限(計算結果が90の場合)
    // 更新者コード = 注意情報通知キュー.データコード(AXRY1)
    // 基準値 = 注意情報通知キュー.基準値(100)
    //株式取引銘柄UPDQを更新する
    // 基準値（終値） = 注意情報通知キュー.基準値(100)
    // 更新者コード = 注意情報通知キュー.データコード(AXRY1)
    // 基準値 = 注意情報通知キュー.基準値(100)
    public void testNotifyLimitRangeInfo_C005()
    {
        final String STR_METHOD_NAME = "testNotifyLimitRangeInfo_C005";
        log.entering(STR_METHOD_NAME);

        try
        {
//            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp",
//                 new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));
//            
//            ThreadLocalSystemAttributesRegistry.setAttribute("web3.attributes.basetimestampfororderbizdate",
//                 new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));
//            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
//
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
//                    "getSystemTimestamp",
//                    new Class[] {},
//                    new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));  

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            l_InstitutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_InstitutionParams);
            
            TestDBUtility.deleteAll(InstitutionPreferencesRow.TYPE);
            InstitutionPreferencesParams l_institutionPreferencesParams = TestDBUtility.getInstitutionPreferencesRow();
            l_institutionPreferencesParams.setValue("1");
            l_institutionPreferencesParams.setInstitutionId(33);
            l_institutionPreferencesParams.setName("attention.info.comp.taking.div");
            l_institutionPreferencesParams.setNameSerialNo(1);
            TestDBUtility.insertWithDel(l_institutionPreferencesParams);
            
            TestDBUtility.deleteAll(HostAttentionInfoNotifyRow.TYPE);
            HostAttentionInfoNotifyParams l_hostAttentionInfoNotifyParams =
                new HostAttentionInfoNotifyParams();
            l_hostAttentionInfoNotifyParams.setBasePrice(100);
            l_hostAttentionInfoNotifyParams.setHighPriceRange(null);//制限値幅上限
            l_hostAttentionInfoNotifyParams.setLowPriceRange(10);
            l_hostAttentionInfoNotifyParams.setRequestCode("AXRY1");
            l_hostAttentionInfoNotifyParams.setInstitutionCode("0D");
            l_hostAttentionInfoNotifyParams.setSonarMarketCode("1");
            TestDBUtility.insertWithDel(l_hostAttentionInfoNotifyParams);
            
//            TestDBUtility.deleteAll(EqtypeProductConditionRow.TYPE);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_ProductParams = TestDBUtility.getProductRow();
            l_ProductParams.setEstimationPrice(50);//評価単価
            l_ProductParams.setLastUpdater("XXXX");//更新者コード
            l_ProductParams.setProductId(2601133200000L);
            l_ProductParams.setPrimaryMarketId(2601L);
            TestDBUtility.insertWithDel(l_ProductParams);
            
//            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
//            EqtypeProductParams l_EqtypeProductParams = TestDBUtility.getEqtypeProductRow();
//            l_EqtypeProductParams.setProductId(2601133200000L);
//            TestDBUtility.insertWithDel(l_EqtypeProductParams);
            
//            TestDBUtility.deleteAll(TradedProductRow.TYPE);
//            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
//            l_tradedProductParams.setTradedProductId(1006160060005L);
//            l_tradedProductParams.setProductId(2601133200000L);
//            l_tradedProductParams.setMarketId(2601L);
//            l_tradedProductParams.setInstitutionCode("0D");
//            TestDBUtility.insertWithDel(l_tradedProductParams);

            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams =
                TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setTradedProductId(1006160060005L);
            l_eqtypeTradedProductParams.setProductId(2601133200000L);
            l_eqtypeTradedProductParams.setMarketId(2601L);
            l_eqtypeTradedProductParams.setValidUntilBizDate("20040917");
            l_eqtypeTradedProductParams.setLastClosingPrice(50);//基準値（終値）
            l_eqtypeTradedProductParams.setPriceRangeType("0");//値幅チェック区分
            l_eqtypeTradedProductParams.setPriceRangeUnitType("0");//値幅区分
            l_eqtypeTradedProductParams.setHighCompulsivePriceRange(99);//強制値幅（上限値）
            l_eqtypeTradedProductParams.setLowCompulsivePriceRange(1);//強制値幅（下限値）
            l_eqtypeTradedProductParams.setLastUpdater("XXXX");//更新者コード
            l_eqtypeTradedProductParams.setBasePrice(50);//基準値
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductUpdqRow.TYPE);
            EqtypeTradedProductUpdqParams l_eqtypeTradedProductUpdqParams =
                TestDBUtility.getEqtypeTradedProductUpdqRow();
            l_eqtypeTradedProductUpdqParams.setTradedProductId(1006160060005L);
            l_eqtypeTradedProductUpdqParams.setProductId(2601133200000L);
            l_eqtypeTradedProductUpdqParams.setMarketId(2601L);
            l_eqtypeTradedProductUpdqParams.setValidUntilBizDate("20040917");
            l_eqtypeTradedProductUpdqParams.setLastClosingPrice(50);//基準値（終値）
            l_eqtypeTradedProductUpdqParams.setLastUpdater("XXXX");//更新者コード
            l_eqtypeTradedProductUpdqParams.setBasePrice(50);//基準値
            TestDBUtility.insertWithDel(l_eqtypeTradedProductUpdqParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_MarketParams = TestDBUtility.getMarketRow();
            l_MarketParams.setMarketCode("2");
            l_MarketParams.setSonarMarketCode("1");
            l_MarketParams.setMarketId(2601L);
            TestDBUtility.insertWithDel(l_MarketParams);
            
//            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
//            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
//            l_tradingTimeParams.setInstitutionCode("0D");
//            l_tradingTimeParams.setBranchCode("123");
//            l_tradingTimeParams.setMarketCode("N1");
//            l_tradingTimeParams.setTradingTimeType("01");
//            l_tradingTimeParams.setProductCode("0");
//            l_tradingTimeParams.setBizDateType("1");
//            l_tradingTimeParams.setBizdateCalcParameter("0");
//            TestDBUtility.insertWithDel(l_tradingTimeParams);
//             
//            WEB3EquityTradedProduct l_equityTradedProduct =
//                new WEB3EquityTradedProduct(l_tradedProductParams);
            String l_strUpdateFlag =
                l_impl.notifyLimitRangeInfo(l_hostAttentionInfoNotifyParams, l_eqtypeTradedProductParams, 
                        l_eqtypeTradedProductUpdqParams, l_ProductParams);
            assertEquals("1", l_strUpdateFlag);
               
            //銘柄を更新する
            ProductRow l_product = ProductDao.findRowByPk(2601133200000L);
            // 評価単価 = 注意情報通知キュー.基準値(100)
            assertEquals(100, l_product.getEstimationPrice(), 0);
            // 更新者コード = 注意情報通知キュー.データコード(AXRY1)
            assertEquals("AXRY1", l_product.getLastUpdater());
                
            //株式取引銘柄を更新す
            EqtypeTradedProductRow l_listEqtypeTradedProduct =
                EqtypeTradedProductDao.findRowByPk(1006160060005L);
            // 基準値（終値） = 注意情報通知キュー.基準値(100)
            assertEquals(100, l_listEqtypeTradedProduct.getLastClosingPrice(), 0);
            // 値幅チェック区分 = "1:値幅チェックあり"
            assertEquals("1", l_listEqtypeTradedProduct.getPriceRangeType());
            // 値幅区分 = 既存値 "0"
            assertEquals("0", l_listEqtypeTradedProduct.getPriceRangeUnitType());
            // 注意情報通知キュー.制限値幅上限 = null 注意情報通知キュー.制限値幅下限(10)
            // 強制値幅（上限値）= null
            assertTrue(l_listEqtypeTradedProduct.getHighCompulsivePriceRangeIsNull());
            // 強制値幅（下限値）= 注意情報通知キュー.基準値-注意情報通知キュー.制限値幅下限(計算結果が90の場合)
            assertEquals(90, l_listEqtypeTradedProduct.getLowCompulsivePriceRange(), 0);
            // 更新者コード = 注意情報通知キュー.データコード(AXRY1)
            assertEquals("AXRY1", l_listEqtypeTradedProduct.getLastUpdater());
            // 基準値 = 注意情報通知キュー.基準値(100)
            assertEquals(100, l_listEqtypeTradedProduct.getBasePrice(), 0);
                
            //株式取引銘柄UPDQを更新する
            EqtypeTradedProductUpdqRow l_listEqtypeTradedProductUpdq =
                EqtypeTradedProductUpdqDao.findRowByPk(1006160060005L, "20040917");
            // 基準値（終値） = 注意情報通知キュー.基準値(100)
            assertEquals(100, l_listEqtypeTradedProductUpdq.getLastClosingPrice(), 0);
            // 更新者コード = 注意情報通知キュー.データコード(AXRY1)
            assertEquals("AXRY1", l_listEqtypeTradedProductUpdq.getLastUpdater());
            // 基準値 = 注意情報通知キュー.基準値(100)
            assertEquals(100, l_listEqtypeTradedProductUpdq.getBasePrice(), 0);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //銘柄.優先市場ID不等于null的場合 並且
    //銘柄.優先市場ID和與注意情報通知キューテーブル.市場コード（SONAR）相符合的市場ID不相等的場合、銘柄不更新
    //株式取引銘柄UPDQ沒有取得 株式取引銘柄UPDQ 沒有更新
    //株式取引銘柄を更新す
    // 基準値（終値） = 注意情報通知キュー.基準値(100)
    // 値幅チェック区分 = "1:値幅チェックあり"
    // 値幅区分 = 『強制値幅（上限値）』『強制値幅（下限値）』を更新した結果、どちらの項目もnullでない場合、"1：円"
    // 注意情報通知キュー.制限値幅上限(200) 注意情報通知キュー.制限値幅下限(110)
    // 強制値幅（上限値）= 注意情報通知キュー.制限値幅上限-注意情報通知キュー.基準値(計算結果が0以上の場合)
    // 強制値幅（下限値）= 注意情報通知キュー.基準値-注意情報通知キュー.制限値幅下限(計算結果が0以下の場合 -10) null
    // 更新者コード = 注意情報通知キュー.データコード(AXRY1)
    // 基準値 = 注意情報通知キュー.基準値(100)
    public void testNotifyLimitRangeInfo_C006()
    {
        final String STR_METHOD_NAME = "testNotifyLimitRangeInfo_C006";
        log.entering(STR_METHOD_NAME);

        try
        {
//            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp",
//                 new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));
//            
//            ThreadLocalSystemAttributesRegistry.setAttribute("web3.attributes.basetimestampfororderbizdate",
//                 new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));
//            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
//
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
//                    "getSystemTimestamp",
//                    new Class[] {},
//                    new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));  

            TestDBUtility.deleteAll(InstitutionPreferencesRow.TYPE);
            InstitutionPreferencesParams l_institutionPreferencesParams = TestDBUtility.getInstitutionPreferencesRow();
            l_institutionPreferencesParams.setValue("1");
            l_institutionPreferencesParams.setInstitutionId(33);
            l_institutionPreferencesParams.setName("attention.info.comp.taking.div");
            l_institutionPreferencesParams.setNameSerialNo(1);
            TestDBUtility.insertWithDel(l_institutionPreferencesParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            l_InstitutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_InstitutionParams);
            
            TestDBUtility.deleteAll(HostAttentionInfoNotifyRow.TYPE);
            HostAttentionInfoNotifyParams l_hostAttentionInfoNotifyParams =
                new HostAttentionInfoNotifyParams();
            l_hostAttentionInfoNotifyParams.setBasePrice(100);
            l_hostAttentionInfoNotifyParams.setHighPriceRange(200);
            l_hostAttentionInfoNotifyParams.setLowPriceRange(110);//制限値幅下限
            l_hostAttentionInfoNotifyParams.setRequestCode("AXRY1");
            l_hostAttentionInfoNotifyParams.setInstitutionCode("0D");
            l_hostAttentionInfoNotifyParams.setSonarMarketCode("1");
            TestDBUtility.insertWithDel(l_hostAttentionInfoNotifyParams);
            
//            TestDBUtility.deleteAll(EqtypeProductConditionRow.TYPE);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_ProductParams = TestDBUtility.getProductRow();
            l_ProductParams.setEstimationPrice(50);//評価単価
            l_ProductParams.setLastUpdater("XXXX");//更新者コード
            l_ProductParams.setProductId(2601133200000L);
            l_ProductParams.setPrimaryMarketId(2222L);
            TestDBUtility.insertWithDel(l_ProductParams);
            
//            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
//            EqtypeProductParams l_EqtypeProductParams = TestDBUtility.getEqtypeProductRow();
//            l_EqtypeProductParams.setProductId(2601133200000L);
//            TestDBUtility.insertWithDel(l_EqtypeProductParams);
            
//            TestDBUtility.deleteAll(TradedProductRow.TYPE);
//            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
//            l_tradedProductParams.setTradedProductId(1006160060005L);
//            l_tradedProductParams.setProductId(2601133200000L);
//            l_tradedProductParams.setMarketId(2601L);
//            l_tradedProductParams.setInstitutionCode("0D");
//            TestDBUtility.insertWithDel(l_tradedProductParams);

            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams =
                TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setTradedProductId(1006160060005L);
            l_eqtypeTradedProductParams.setProductId(2601133200000L);
            l_eqtypeTradedProductParams.setMarketId(2601L);
            l_eqtypeTradedProductParams.setValidUntilBizDate("20040917");
            l_eqtypeTradedProductParams.setLastClosingPrice(50);//基準値（終値）
            l_eqtypeTradedProductParams.setPriceRangeType("0");//値幅チェック区分
            l_eqtypeTradedProductParams.setPriceRangeUnitType("0");//値幅区分
            l_eqtypeTradedProductParams.setHighCompulsivePriceRange(99);//強制値幅（上限値）
            l_eqtypeTradedProductParams.setLowCompulsivePriceRange(1);//強制値幅（下限値）
            l_eqtypeTradedProductParams.setLastUpdater("XXXX");//更新者コード
            l_eqtypeTradedProductParams.setBasePrice(50);//基準値
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductUpdqRow.TYPE);
            EqtypeTradedProductUpdqParams l_eqtypeTradedProductUpdqParams =
                TestDBUtility.getEqtypeTradedProductUpdqRow();
            l_eqtypeTradedProductUpdqParams.setTradedProductId(1006160060005L);
            l_eqtypeTradedProductUpdqParams.setProductId(2601133200000L);
            l_eqtypeTradedProductUpdqParams.setMarketId(2601L);
            l_eqtypeTradedProductUpdqParams.setValidUntilBizDate("20040917");
            l_eqtypeTradedProductUpdqParams.setLastClosingPrice(50);//基準値（終値）
            l_eqtypeTradedProductUpdqParams.setLastUpdater("XXXX");//更新者コード
            l_eqtypeTradedProductUpdqParams.setBasePrice(50);//基準値
            TestDBUtility.insertWithDel(l_eqtypeTradedProductUpdqParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_MarketParams = TestDBUtility.getMarketRow();
            l_MarketParams.setMarketCode("1");
            l_MarketParams.setSonarMarketCode("1");
            l_MarketParams.setMarketId(2601L);
            TestDBUtility.insertWithDel(l_MarketParams);
            
//            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
//            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
//            l_tradingTimeParams.setInstitutionCode("0D");
//            l_tradingTimeParams.setBranchCode("123");
//            l_tradingTimeParams.setMarketCode("N1");
//            l_tradingTimeParams.setTradingTimeType("01");
//            l_tradingTimeParams.setProductCode("0");
//            l_tradingTimeParams.setBizDateType("1");
//            l_tradingTimeParams.setBizdateCalcParameter("0");
//            TestDBUtility.insertWithDel(l_tradingTimeParams);
//             
//            WEB3EquityTradedProduct l_equityTradedProduct =
//                new WEB3EquityTradedProduct(l_tradedProductParams);
            String l_strUpdateFlag =
                l_impl.notifyLimitRangeInfo(l_hostAttentionInfoNotifyParams, l_eqtypeTradedProductParams, 
                    null, l_ProductParams);
            assertEquals("1", l_strUpdateFlag);
               
            //銘柄不更新
            ProductRow l_product = ProductDao.findRowByPk(2601133200000L);
            // 評価単価 = （既存値）
            assertEquals(50, l_product.getEstimationPrice(), 0);
            // 更新者コード = （既存値）
            assertEquals("XXXX", l_product.getLastUpdater());
                
            //株式取引銘柄を更新す
            EqtypeTradedProductRow l_listEqtypeTradedProduct =
                EqtypeTradedProductDao.findRowByPk(1006160060005L);
            // 基準値（終値） = 注意情報通知キュー.基準値(100)
            assertEquals(100, l_listEqtypeTradedProduct.getLastClosingPrice(), 0);
            // 値幅チェック区分 = "1:値幅チェックあり"
            assertEquals("1", l_listEqtypeTradedProduct.getPriceRangeType());
            // 値幅区分 = 既存値 "0"
            assertEquals("0", l_listEqtypeTradedProduct.getPriceRangeUnitType());
            // 注意情報通知キュー.制限値幅上限(200) 注意情報通知キュー.制限値幅下限(110)
            // 強制値幅（上限値）= 注意情報通知キュー.制限値幅上限-注意情報通知キュー.基準値(計算結果が0以上の場合)
            assertEquals(100, l_listEqtypeTradedProduct.getHighCompulsivePriceRange(), 0);
            // 強制値幅（下限値）= null
            assertTrue(l_listEqtypeTradedProduct.getLowCompulsivePriceRangeIsNull());
            // 更新者コード = 注意情報通知キュー.データコード(AXRY1)
            assertEquals("AXRY1", l_listEqtypeTradedProduct.getLastUpdater());
            // 基準値 = 注意情報通知キュー.基準値(100)
            assertEquals(100, l_listEqtypeTradedProduct.getBasePrice(), 0);
                
            //株式取引銘柄UPDQ不更新
            EqtypeTradedProductUpdqRow l_listEqtypeTradedProductUpdq =
                EqtypeTradedProductUpdqDao.findRowByPk(1006160060005L, "20040917");
            // 基準値（終値） = （既存値）
            assertEquals(50, l_listEqtypeTradedProductUpdq.getLastClosingPrice(), 0);
            // 更新者コード = （既存値）
            assertEquals("XXXX", l_listEqtypeTradedProductUpdq.getLastUpdater());
            // 基準値 = （既存値）
            assertEquals(50, l_listEqtypeTradedProductUpdq.getBasePrice(), 0);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //銘柄.優先市場ID不等于null的場合 並且
    //銘柄.優先市場ID和與注意情報通知キューテーブル.市場コード（SONAR）相符合的市場ID不相等的場合、銘柄不更新
    //株式取引銘柄UPDQ沒有取得 株式取引銘柄UPDQ 沒有更新
    //株式取引銘柄を更新す
    // 基準値（終値） = 注意情報通知キュー.基準値(100)
    // 値幅チェック区分 = "1:値幅チェックあり"
    // 値幅区分 = 既存値"0"
    // 注意情報通知キュー.制限値幅上限(90) 注意情報通知キュー.制限値幅下限(null)
    // 強制値幅（上限値）= 注意情報通知キュー.制限値幅上限-注意情報通知キュー.基準値(計算結果が0以上の場合)
    // 強制値幅（下限値）= 注意情報通知キュー.基準値-注意情報通知キュー.制限値幅下限(計算結果が0以下の場合 -10) null
    // 更新者コード = 注意情報通知キュー.データコード(AXRY1)
    // 基準値 = 注意情報通知キュー.基準値(100)
    public void testNotifyLimitRangeInfo_C007()
    {
        final String STR_METHOD_NAME = "testNotifyLimitRangeInfo_C007";
        log.entering(STR_METHOD_NAME);

        try
        {
//            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp",
//                 new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));
//            
//            ThreadLocalSystemAttributesRegistry.setAttribute("web3.attributes.basetimestampfororderbizdate",
//                 new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));
//            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
//
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
//                    "getSystemTimestamp",
//                    new Class[] {},
//                    new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));  

            TestDBUtility.deleteAll(InstitutionPreferencesRow.TYPE);
            InstitutionPreferencesParams l_institutionPreferencesParams = TestDBUtility.getInstitutionPreferencesRow();
            l_institutionPreferencesParams.setValue("1");
            l_institutionPreferencesParams.setInstitutionId(33);
            l_institutionPreferencesParams.setName("attention.info.comp.taking.div");
            l_institutionPreferencesParams.setNameSerialNo(1);
            TestDBUtility.insertWithDel(l_institutionPreferencesParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            l_InstitutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_InstitutionParams);
            
            TestDBUtility.deleteAll(HostAttentionInfoNotifyRow.TYPE);
            HostAttentionInfoNotifyParams l_hostAttentionInfoNotifyParams =
                new HostAttentionInfoNotifyParams();
            l_hostAttentionInfoNotifyParams.setBasePrice(100);
            l_hostAttentionInfoNotifyParams.setHighPriceRange(90);//制限値幅上限
            l_hostAttentionInfoNotifyParams.setLowPriceRange(null);//制限値幅下限
            l_hostAttentionInfoNotifyParams.setRequestCode("AXRY1");
            l_hostAttentionInfoNotifyParams.setInstitutionCode("0D");
            l_hostAttentionInfoNotifyParams.setSonarMarketCode("1");
            TestDBUtility.insertWithDel(l_hostAttentionInfoNotifyParams);
            
//            TestDBUtility.deleteAll(EqtypeProductConditionRow.TYPE);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_ProductParams = TestDBUtility.getProductRow();
            l_ProductParams.setEstimationPrice(50);//評価単価
            l_ProductParams.setLastUpdater("XXXX");//更新者コード
            l_ProductParams.setProductId(2601133200000L);
            l_ProductParams.setPrimaryMarketId(2222L);
            TestDBUtility.insertWithDel(l_ProductParams);
            
//            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
//            EqtypeProductParams l_EqtypeProductParams = TestDBUtility.getEqtypeProductRow();
//            l_EqtypeProductParams.setProductId(2601133200000L);
//            TestDBUtility.insertWithDel(l_EqtypeProductParams);
            
//            TestDBUtility.deleteAll(TradedProductRow.TYPE);
//            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
//            l_tradedProductParams.setTradedProductId(1006160060005L);
//            l_tradedProductParams.setProductId(2601133200000L);
//            l_tradedProductParams.setMarketId(2601L);
//            l_tradedProductParams.setInstitutionCode("0D");
//            TestDBUtility.insertWithDel(l_tradedProductParams);

            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams =
                TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setTradedProductId(1006160060005L);
            l_eqtypeTradedProductParams.setProductId(2601133200000L);
            l_eqtypeTradedProductParams.setMarketId(2601L);
            l_eqtypeTradedProductParams.setValidUntilBizDate("20040917");
            l_eqtypeTradedProductParams.setLastClosingPrice(50);//基準値（終値）
            l_eqtypeTradedProductParams.setPriceRangeType("0");//値幅チェック区分
            l_eqtypeTradedProductParams.setPriceRangeUnitType("0");//値幅区分
            l_eqtypeTradedProductParams.setHighCompulsivePriceRange(99);//強制値幅（上限値）
            l_eqtypeTradedProductParams.setLowCompulsivePriceRange(1);//強制値幅（下限値）
            l_eqtypeTradedProductParams.setLastUpdater("XXXX");//更新者コード
            l_eqtypeTradedProductParams.setBasePrice(50);//基準値
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductUpdqRow.TYPE);
            EqtypeTradedProductUpdqParams l_eqtypeTradedProductUpdqParams =
                TestDBUtility.getEqtypeTradedProductUpdqRow();
            l_eqtypeTradedProductUpdqParams.setTradedProductId(1006160060005L);
            l_eqtypeTradedProductUpdqParams.setProductId(2601133200000L);
            l_eqtypeTradedProductUpdqParams.setMarketId(2601L);
            l_eqtypeTradedProductUpdqParams.setValidUntilBizDate("20040917");
            l_eqtypeTradedProductUpdqParams.setLastClosingPrice(50);//基準値（終値）
            l_eqtypeTradedProductUpdqParams.setLastUpdater("XXXX");//更新者コード
            l_eqtypeTradedProductUpdqParams.setBasePrice(50);//基準値
            TestDBUtility.insertWithDel(l_eqtypeTradedProductUpdqParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_MarketParams = TestDBUtility.getMarketRow();
            l_MarketParams.setMarketCode("2");
            l_MarketParams.setSonarMarketCode("1");
            l_MarketParams.setMarketId(2601L);
            TestDBUtility.insertWithDel(l_MarketParams);
            
//            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
//            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
//            l_tradingTimeParams.setInstitutionCode("0D");
//            l_tradingTimeParams.setBranchCode("123");
//            l_tradingTimeParams.setMarketCode("N1");
//            l_tradingTimeParams.setTradingTimeType("01");
//            l_tradingTimeParams.setProductCode("0");
//            l_tradingTimeParams.setBizDateType("1");
//            l_tradingTimeParams.setBizdateCalcParameter("0");
//            TestDBUtility.insertWithDel(l_tradingTimeParams);
//             
//            WEB3EquityTradedProduct l_equityTradedProduct =
//                new WEB3EquityTradedProduct(l_tradedProductParams);
            String l_strUpdateFlag =
                l_impl.notifyLimitRangeInfo(l_hostAttentionInfoNotifyParams, l_eqtypeTradedProductParams, 
                    null, l_ProductParams);
            assertEquals("1", l_strUpdateFlag);
               
            //銘柄不更新
            ProductRow l_product = ProductDao.findRowByPk(2601133200000L);
            // 評価単価 = （既存値）
            assertEquals(50, l_product.getEstimationPrice(), 0);
            // 更新者コード = （既存値）
            assertEquals("XXXX", l_product.getLastUpdater());
                
            //株式取引銘柄を更新す
            EqtypeTradedProductRow l_listEqtypeTradedProduct =
                EqtypeTradedProductDao.findRowByPk(1006160060005L);
            // 基準値（終値） = 注意情報通知キュー.基準値(100)
            assertEquals(100, l_listEqtypeTradedProduct.getLastClosingPrice(), 0);
            // 値幅チェック区分 = "1:値幅チェックあり"
            assertEquals("1", l_listEqtypeTradedProduct.getPriceRangeType());
            // 値幅区分 = 既存値 "0"
            assertEquals("0", l_listEqtypeTradedProduct.getPriceRangeUnitType());
            // 注意情報通知キュー.制限値幅上限(90) 注意情報通知キュー.制限値幅下限(null)
            // 強制値幅（上限値）= null
            assertTrue(l_listEqtypeTradedProduct.getHighCompulsivePriceRangeIsNull());
            // 強制値幅（下限値）= null
            assertTrue(l_listEqtypeTradedProduct.getLowCompulsivePriceRangeIsNull());
            // 更新者コード = 注意情報通知キュー.データコード(AXRY1)
            assertEquals("AXRY1", l_listEqtypeTradedProduct.getLastUpdater());
            // 基準値 = 注意情報通知キュー.基準値(100)
            assertEquals(100, l_listEqtypeTradedProduct.getBasePrice(), 0);
                
            //株式取引銘柄UPDQ不更新
            EqtypeTradedProductUpdqRow l_listEqtypeTradedProductUpdq =
                EqtypeTradedProductUpdqDao.findRowByPk(1006160060005L, "20040917");
            // 基準値（終値） = （既存値）
            assertEquals(50, l_listEqtypeTradedProductUpdq.getLastClosingPrice(), 0);
            // 更新者コード = （既存値）
            assertEquals("XXXX", l_listEqtypeTradedProductUpdq.getLastUpdater());
            // 基準値 = （既存値）
            assertEquals(50, l_listEqtypeTradedProductUpdq.getBasePrice(), 0);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

//    //管理者更新済み項目Listの要素数が2件の場合、
//    //、管理者更新済み項目Listに含まれていない要素の項目に対し更新を行なう
//    //　@　@　@　@　@・high_compulsive_price_range 含まれ
//    //          ・low_compulsive_price_range含まれ
//    public void testNotifyLimitRangeInfo_C007()
//    {
//        final String STR_METHOD_NAME = "testNotifyLimitRangeInfo_C007";
//        log.entering(STR_METHOD_NAME);
//
//        try
//        {
//            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp",
//                 new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));
//            
//            ThreadLocalSystemAttributesRegistry.setAttribute("web3.attributes.basetimestampfororderbizdate",
//                 new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));
//            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
//
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
//                    "getSystemTimestamp",
//                    new Class[] {},
//                    new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));  
//
//            TestDBUtility.deleteAll(HostAttentionInfoNotifyRow.TYPE);
//            HostAttentionInfoNotifyParams l_hostAttentionInfoNotifyParams =
//                new HostAttentionInfoNotifyParams();
//            l_hostAttentionInfoNotifyParams.setBasePrice(100);
//            l_hostAttentionInfoNotifyParams.setHighPriceRange(1000);
//            l_hostAttentionInfoNotifyParams.setLowPriceRange(10);
//            l_hostAttentionInfoNotifyParams.setRequestCode("AXRY1");
//            TestDBUtility.insertWithDel(l_hostAttentionInfoNotifyParams);
//            
//            TestDBUtility.deleteAll(EqtypeProductConditionRow.TYPE);
//            
//            EqtypeProductConditionParams l_eqtypeProductConditionParams1 = new EqtypeProductConditionParams();
//            l_eqtypeProductConditionParams1.setEqtypeProductConditionId(2088L);
//            l_eqtypeProductConditionParams1.setInstitutionCode("0D");
//            l_eqtypeProductConditionParams1.setProductCode("13320");
//            l_eqtypeProductConditionParams1.setProductId(2601133200000L);
//            l_eqtypeProductConditionParams1.setMarketCode("1");
//            l_eqtypeProductConditionParams1.setMarketId(2601L);
//            l_eqtypeProductConditionParams1.setTableName("eqtype_traded_product");
//            l_eqtypeProductConditionParams1.setColumnName("low_compulsive_price_range");
//            l_eqtypeProductConditionParams1.setLargeItemDiv("5");
//            l_eqtypeProductConditionParams1.setSmallItemDiv("52");
//            l_eqtypeProductConditionParams1.setDataToday("521");
//            l_eqtypeProductConditionParams1.setDataNextDay("521");
//            l_eqtypeProductConditionParams1.setDataPlan("521");
//            l_eqtypeProductConditionParams1.setTermFrom(WEB3DateUtility.getDate("11110101","yyyy/MM/dd HH:mm:ss"));
//            l_eqtypeProductConditionParams1.setTermTo(WEB3DateUtility.getDate("9999213","yyyy/MM/dd HH:mm:ss"));
//            l_eqtypeProductConditionParams1.setDeleteFlg("0");
//            l_eqtypeProductConditionParams1.setLastUpdater("dir");
//            l_eqtypeProductConditionParams1.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_eqtypeProductConditionParams1.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
//            TestDBUtility.insertWithDel(l_eqtypeProductConditionParams1);
//            
//            EqtypeProductConditionParams l_eqtypeProductConditionParams2 = new EqtypeProductConditionParams();
//            l_eqtypeProductConditionParams2.setEqtypeProductConditionId(2090);
//            l_eqtypeProductConditionParams2.setInstitutionCode("0D");
//            l_eqtypeProductConditionParams2.setProductCode("13320");
//            l_eqtypeProductConditionParams2.setProductId(2601133200000L);
//            l_eqtypeProductConditionParams2.setMarketCode("1");
//            l_eqtypeProductConditionParams2.setMarketId(2601L);
//            l_eqtypeProductConditionParams2.setTableName("eqtype_traded_product");
//            l_eqtypeProductConditionParams2.setColumnName("high_compulsive_price_range");
//            l_eqtypeProductConditionParams2.setLargeItemDiv("5");
//            l_eqtypeProductConditionParams2.setSmallItemDiv("53");
//            l_eqtypeProductConditionParams2.setDataToday("531");
//            l_eqtypeProductConditionParams2.setDataNextDay("531");
//            l_eqtypeProductConditionParams2.setDataPlan("531");
//            l_eqtypeProductConditionParams2.setTermFrom(WEB3DateUtility.getDate("11110101","yyyy/MM/dd HH:mm:ss"));
//            l_eqtypeProductConditionParams2.setTermTo(WEB3DateUtility.getDate("9999213","yyyy/MM/dd HH:mm:ss"));
//            l_eqtypeProductConditionParams2.setDeleteFlg("0");
//            l_eqtypeProductConditionParams2.setLastUpdater("dir");
//            l_eqtypeProductConditionParams2.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_eqtypeProductConditionParams2.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
//            TestDBUtility.insertWithDel(l_eqtypeProductConditionParams2);
//            
//            TestDBUtility.deleteAll(ProductRow.TYPE);
//            ProductParams l_ProductParams = TestDBUtility.getProductRow();
//            l_ProductParams.setProductId(2601133200000L);
//            TestDBUtility.insertWithDel(l_ProductParams);
//            
//            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
//            EqtypeProductParams l_EqtypeProductParams = TestDBUtility.getEqtypeProductRow();
//            l_EqtypeProductParams.setProductId(2601133200000L);
//            TestDBUtility.insertWithDel(l_EqtypeProductParams);
//            
//            TestDBUtility.deleteAll(TradedProductRow.TYPE);
//            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
//            l_tradedProductParams.setTradedProductId(1006160060005L);
//            l_tradedProductParams.setProductId(2601133200000L);
//            l_tradedProductParams.setMarketId(2601L);
//            l_tradedProductParams.setInstitutionCode("0D");
//            TestDBUtility.insertWithDel(l_tradedProductParams);
//
//            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
//            EqtypeTradedProductParams l_eqtypeTradedProductParams =
//                TestDBUtility.getEqtypeTradedProductRow();
//            l_eqtypeTradedProductParams.setTradedProductId(1006160060005L);
//            l_eqtypeTradedProductParams.setProductId(2601133200000L);
//            l_eqtypeTradedProductParams.setMarketId(2601L);
//            l_eqtypeTradedProductParams.setValidUntilBizDate("20040917");
//            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
//            
//            TestDBUtility.deleteAll(MarketRow.TYPE);
//            MarketParams l_MarketParams = TestDBUtility.getMarketRow();
//            l_MarketParams.setMarketCode("1");
//            l_MarketParams.setMarketId(2601L);
//            TestDBUtility.insertWithDel(l_MarketParams);
//            
//            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
//            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
//            l_tradingTimeParams.setInstitutionCode("0D");
//            l_tradingTimeParams.setBranchCode("123");
//            l_tradingTimeParams.setMarketCode("N1");
//            l_tradingTimeParams.setTradingTimeType("01");
//            l_tradingTimeParams.setProductCode("0");
//            l_tradingTimeParams.setBizDateType("1");
//            l_tradingTimeParams.setBizdateCalcParameter("0");
//            TestDBUtility.insertWithDel(l_tradingTimeParams);
//             
//            WEB3EquityTradedProduct l_equityTradedProduct =
//                new WEB3EquityTradedProduct(l_tradedProductParams);
//            l_impl.notifyLimitRangeInfo(l_hostAttentionInfoNotifyParams, l_eqtypeTradedProductParams, null, null);
//            
//        }
//        catch (WEB3BusinessLayerException l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03146, l_ex.getErrorInfo());
//        }
//        catch (DataQueryException l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        catch (DataNetworkException l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        catch (WEB3BaseException l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        finally
//        {
//            try
//            {
//                EqtypeTradedProductRow l_listEqtypeTradedProductRow =
//                    EqtypeTradedProductDao.findRowByPk(1006160060005L);
//                assertEquals(100, l_listEqtypeTradedProductRow.getLastClosingPrice(), 0);
//                assertTrue(l_listEqtypeTradedProductRow.getHighCompulsivePriceRangeIsNull());
//                assertTrue(l_listEqtypeTradedProductRow.getLowCompulsivePriceRangeIsNull());
//                assertEquals(null, l_listEqtypeTradedProductRow.getPriceRangeUnitType());
//                assertEquals(100, l_listEqtypeTradedProductRow.getBasePrice(), 0);
//                assertEquals("AXRY1", l_listEqtypeTradedProductRow.getLastUpdater());
//            }
//            catch (DataFindException e)
//            {
//                fail();
//            }
//            catch (DataQueryException e)
//            {
//                fail();
//            }
//            catch (DataNetworkException e)
//            {
//                fail();
//            }
//        }
//        log.exiting(STR_METHOD_NAME);
//    }
//    
//    //管理者更新済み項目Listの要素数が2件の場合、
//    //管理者更新済み項目Listに含まれていない要素の項目に対し更新を行なう
//    //　@　@　@　@　@・high_compulsive_price_range 含まれていない
//    //          ・low_compulsive_price_range含まれていない
//    //注意情報通知キュー.制限値幅上限 - 注意情報通知キュー.基準値 > 0
//    //注意情報通知キュー.基準値 - 注意情報通知キュー.制限値幅下限 > 0
//    public void testNotifyLimitRangeInfo_C008()
//    {
//        final String STR_METHOD_NAME = "testNotifyLimitRangeInfo_C008";
//        log.entering(STR_METHOD_NAME);
//
//        try
//        {
//            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp",
//                 new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));
//            
//            ThreadLocalSystemAttributesRegistry.setAttribute("web3.attributes.basetimestampfororderbizdate",
//                 new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));
//            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
//
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
//                    "getSystemTimestamp",
//                    new Class[] {},
//                    new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));  
//
//            TestDBUtility.deleteAll(HostAttentionInfoNotifyRow.TYPE);
//            HostAttentionInfoNotifyParams l_hostAttentionInfoNotifyParams =
//                new HostAttentionInfoNotifyParams();
//            l_hostAttentionInfoNotifyParams.setBasePrice(100);
//            l_hostAttentionInfoNotifyParams.setHighPriceRange(220);
//            l_hostAttentionInfoNotifyParams.setLowPriceRange(50);
//            l_hostAttentionInfoNotifyParams.setRequestCode("AXRY1");
//            TestDBUtility.insertWithDel(l_hostAttentionInfoNotifyParams);
//            
//            TestDBUtility.deleteAll(EqtypeProductConditionRow.TYPE);
//            
//            TestDBUtility.deleteAll(EqtypeProductConditionRow.TYPE);
//            EqtypeProductConditionParams l_eqtypeProductConditionParams = new EqtypeProductConditionParams();
//            l_eqtypeProductConditionParams.setEqtypeProductConditionId(2082L);
//            l_eqtypeProductConditionParams.setInstitutionCode("0D");
//            l_eqtypeProductConditionParams.setProductCode("13320");
//            l_eqtypeProductConditionParams.setProductId(2601133200000L);
//            l_eqtypeProductConditionParams.setMarketCode("1");
//            l_eqtypeProductConditionParams.setMarketId(2601L);
//            l_eqtypeProductConditionParams.setTableName("eqtype_traded_product");
//            l_eqtypeProductConditionParams.setColumnName("last_closing_price");
//            l_eqtypeProductConditionParams.setLargeItemDiv("5");
//            l_eqtypeProductConditionParams.setSmallItemDiv("47");
//            l_eqtypeProductConditionParams.setDataToday("471");
//            l_eqtypeProductConditionParams.setDataNextDay("471");
//            l_eqtypeProductConditionParams.setDataPlan("471");
//            l_eqtypeProductConditionParams.setTermFrom(WEB3DateUtility.getDate("11110101","yyyy/MM/dd HH:mm:ss"));
//            l_eqtypeProductConditionParams.setTermTo(WEB3DateUtility.getDate("9999213","yyyy/MM/dd HH:mm:ss"));
//            l_eqtypeProductConditionParams.setDeleteFlg("0");
//            l_eqtypeProductConditionParams.setLastUpdater("dir");
//            l_eqtypeProductConditionParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_eqtypeProductConditionParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
//            TestDBUtility.insertWithDel(l_eqtypeProductConditionParams);
//            
//            EqtypeProductConditionParams l_eqtypeProductConditionParams3 = new EqtypeProductConditionParams();
//            l_eqtypeProductConditionParams3.setEqtypeProductConditionId(2100);
//            l_eqtypeProductConditionParams3.setInstitutionCode("0D");
//            l_eqtypeProductConditionParams3.setProductCode("13320");
//            l_eqtypeProductConditionParams3.setProductId(2601133200000L);
//            l_eqtypeProductConditionParams3.setMarketCode("1");
//            l_eqtypeProductConditionParams3.setMarketId(2601L);
//            l_eqtypeProductConditionParams3.setTableName("eqtype_traded_product");
//            l_eqtypeProductConditionParams3.setColumnName("base_price");
//            l_eqtypeProductConditionParams3.setLargeItemDiv("5");
//            l_eqtypeProductConditionParams3.setSmallItemDiv("56");
//            l_eqtypeProductConditionParams3.setDataToday("561");
//            l_eqtypeProductConditionParams3.setDataNextDay("561");
//            l_eqtypeProductConditionParams3.setDataPlan("561");
//            l_eqtypeProductConditionParams3.setTermFrom(WEB3DateUtility.getDate("11110101","yyyy/MM/dd HH:mm:ss"));
//            l_eqtypeProductConditionParams3.setTermTo(WEB3DateUtility.getDate("9999213","yyyy/MM/dd HH:mm:ss"));
//            l_eqtypeProductConditionParams3.setDeleteFlg("0");
//            l_eqtypeProductConditionParams3.setLastUpdater("dir");
//            l_eqtypeProductConditionParams3.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_eqtypeProductConditionParams3.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
//            TestDBUtility.insertWithDel(l_eqtypeProductConditionParams3);;
//            
//            TestDBUtility.deleteAll(ProductRow.TYPE);
//            ProductParams l_ProductParams = TestDBUtility.getProductRow();
//            l_ProductParams.setProductId(2601133200000L);
//            TestDBUtility.insertWithDel(l_ProductParams);
//            
//            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
//            EqtypeProductParams l_EqtypeProductParams = TestDBUtility.getEqtypeProductRow();
//            l_EqtypeProductParams.setProductId(2601133200000L);
//            TestDBUtility.insertWithDel(l_EqtypeProductParams);
//            
//            TestDBUtility.deleteAll(TradedProductRow.TYPE);
//            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
//            l_tradedProductParams.setTradedProductId(1006160060005L);
//            l_tradedProductParams.setProductId(2601133200000L);
//            l_tradedProductParams.setMarketId(2601L);
//            l_tradedProductParams.setInstitutionCode("0D");
//            TestDBUtility.insertWithDel(l_tradedProductParams);
//
//            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
//            EqtypeTradedProductParams l_eqtypeTradedProductParams =
//                TestDBUtility.getEqtypeTradedProductRow();
//            l_eqtypeTradedProductParams.setTradedProductId(1006160060005L);
//            l_eqtypeTradedProductParams.setProductId(2601133200000L);
//            l_eqtypeTradedProductParams.setMarketId(2601L);
//            l_eqtypeTradedProductParams.setValidUntilBizDate("20040917");
//            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
//            
//            TestDBUtility.deleteAll(MarketRow.TYPE);
//            MarketParams l_MarketParams = TestDBUtility.getMarketRow();
//            l_MarketParams.setMarketCode("1");
//            l_MarketParams.setMarketId(2601L);
//            TestDBUtility.insertWithDel(l_MarketParams);
//            
//            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
//            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
//            l_tradingTimeParams.setInstitutionCode("0D");
//            l_tradingTimeParams.setBranchCode("123");
//            l_tradingTimeParams.setMarketCode("N1");
//            l_tradingTimeParams.setTradingTimeType("01");
//            l_tradingTimeParams.setProductCode("0");
//            l_tradingTimeParams.setBizDateType("1");
//            l_tradingTimeParams.setBizdateCalcParameter("0");
//            TestDBUtility.insertWithDel(l_tradingTimeParams);
//             
//            WEB3EquityTradedProduct l_equityTradedProduct =
//                new WEB3EquityTradedProduct(l_tradedProductParams);
//            l_impl.notifyLimitRangeInfo(l_hostAttentionInfoNotifyParams, l_eqtypeTradedProductParams, null, null);
//            
//        }
//        catch (WEB3BusinessLayerException l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03146, l_ex.getErrorInfo());
//        }
//        catch (DataQueryException l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        catch (DataNetworkException l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        catch (WEB3BaseException l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        finally
//        {
//            try
//            {
//                EqtypeTradedProductRow l_listEqtypeTradedProductRow =
//                    EqtypeTradedProductDao.findRowByPk(1006160060005L);
//                
//                assertEquals(1000.0, l_listEqtypeTradedProductRow.getLastClosingPrice(), 1);
//                assertEquals(120, l_listEqtypeTradedProductRow.getHighCompulsivePriceRange(), 0);
//                assertEquals(50, l_listEqtypeTradedProductRow.getLowCompulsivePriceRange(), 0);
//                assertEquals("1", l_listEqtypeTradedProductRow.getPriceRangeUnitType());
//                assertEquals(10.0, l_listEqtypeTradedProductRow.getBasePrice(), 1);
//                assertEquals("AXRY1", l_listEqtypeTradedProductRow.getLastUpdater());
//            }
//            catch (DataFindException e)
//            {
//                fail();
//            }
//            catch (DataQueryException e)
//            {
//                fail();
//            }
//            catch (DataNetworkException e)
//            {
//                fail();
//            }
//        }
//        log.exiting(STR_METHOD_NAME);
//    }
//    
//    //管理者更新済み項目Listの要素数が2件の場合、
//    //管理者更新済み項目Listに含まれていない要素の項目に対し更新を行なう
//    //　@　@　@　@　@・high_compulsive_price_range 含まれていない
//    //          ・low_compulsive_price_range含まれていない
//    //注意情報通知キュー.制限値幅上限 - 注意情報通知キュー.基準値 < 0
//    //注意情報通知キュー.基準値 - 注意情報通知キュー.制限値幅下限 < 0
//    public void testNotifyLimitRangeInfo_C009()
//    {
//        final String STR_METHOD_NAME = "testNotifyLimitRangeInfo_C009";
//        log.entering(STR_METHOD_NAME);
//
//        try
//        {
//            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp",
//                 new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));
//            
//            ThreadLocalSystemAttributesRegistry.setAttribute("web3.attributes.basetimestampfororderbizdate",
//                 new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));
//            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
//
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
//                    "getSystemTimestamp",
//                    new Class[] {},
//                    new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));  
//
//            TestDBUtility.deleteAll(HostAttentionInfoNotifyRow.TYPE);
//            HostAttentionInfoNotifyParams l_hostAttentionInfoNotifyParams =
//                new HostAttentionInfoNotifyParams();
//            l_hostAttentionInfoNotifyParams.setBasePrice(100);
//            l_hostAttentionInfoNotifyParams.setHighPriceRange(90);
//            l_hostAttentionInfoNotifyParams.setLowPriceRange(110);
//            l_hostAttentionInfoNotifyParams.setRequestCode("AXRY1");
//            TestDBUtility.insertWithDel(l_hostAttentionInfoNotifyParams);
//            
//            TestDBUtility.deleteAll(EqtypeProductConditionRow.TYPE);
//            
//            TestDBUtility.deleteAll(EqtypeProductConditionRow.TYPE);
//            EqtypeProductConditionParams l_eqtypeProductConditionParams = new EqtypeProductConditionParams();
//            l_eqtypeProductConditionParams.setEqtypeProductConditionId(2082L);
//            l_eqtypeProductConditionParams.setInstitutionCode("0D");
//            l_eqtypeProductConditionParams.setProductCode("13320");
//            l_eqtypeProductConditionParams.setProductId(2601133200000L);
//            l_eqtypeProductConditionParams.setMarketCode("1");
//            l_eqtypeProductConditionParams.setMarketId(2601L);
//            l_eqtypeProductConditionParams.setTableName("eqtype_traded_product");
//            l_eqtypeProductConditionParams.setColumnName("last_closing_price");
//            l_eqtypeProductConditionParams.setLargeItemDiv("5");
//            l_eqtypeProductConditionParams.setSmallItemDiv("47");
//            l_eqtypeProductConditionParams.setDataToday("471");
//            l_eqtypeProductConditionParams.setDataNextDay("471");
//            l_eqtypeProductConditionParams.setDataPlan("471");
//            l_eqtypeProductConditionParams.setTermFrom(WEB3DateUtility.getDate("11110101","yyyy/MM/dd HH:mm:ss"));
//            l_eqtypeProductConditionParams.setTermTo(WEB3DateUtility.getDate("9999213","yyyy/MM/dd HH:mm:ss"));
//            l_eqtypeProductConditionParams.setDeleteFlg("0");
//            l_eqtypeProductConditionParams.setLastUpdater("dir");
//            l_eqtypeProductConditionParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_eqtypeProductConditionParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
//            TestDBUtility.insertWithDel(l_eqtypeProductConditionParams);
//            
//            EqtypeProductConditionParams l_eqtypeProductConditionParams3 = new EqtypeProductConditionParams();
//            l_eqtypeProductConditionParams3.setEqtypeProductConditionId(2100);
//            l_eqtypeProductConditionParams3.setInstitutionCode("0D");
//            l_eqtypeProductConditionParams3.setProductCode("13320");
//            l_eqtypeProductConditionParams3.setProductId(2601133200000L);
//            l_eqtypeProductConditionParams3.setMarketCode("1");
//            l_eqtypeProductConditionParams3.setMarketId(2601L);
//            l_eqtypeProductConditionParams3.setTableName("eqtype_traded_product");
//            l_eqtypeProductConditionParams3.setColumnName("base_price");
//            l_eqtypeProductConditionParams3.setLargeItemDiv("5");
//            l_eqtypeProductConditionParams3.setSmallItemDiv("56");
//            l_eqtypeProductConditionParams3.setDataToday("561");
//            l_eqtypeProductConditionParams3.setDataNextDay("561");
//            l_eqtypeProductConditionParams3.setDataPlan("561");
//            l_eqtypeProductConditionParams3.setTermFrom(WEB3DateUtility.getDate("11110101","yyyy/MM/dd HH:mm:ss"));
//            l_eqtypeProductConditionParams3.setTermTo(WEB3DateUtility.getDate("9999213","yyyy/MM/dd HH:mm:ss"));
//            l_eqtypeProductConditionParams3.setDeleteFlg("0");
//            l_eqtypeProductConditionParams3.setLastUpdater("dir");
//            l_eqtypeProductConditionParams3.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_eqtypeProductConditionParams3.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
//            TestDBUtility.insertWithDel(l_eqtypeProductConditionParams3);;
//            
//            TestDBUtility.deleteAll(ProductRow.TYPE);
//            ProductParams l_ProductParams = TestDBUtility.getProductRow();
//            l_ProductParams.setProductId(2601133200000L);
//            TestDBUtility.insertWithDel(l_ProductParams);
//            
//            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
//            EqtypeProductParams l_EqtypeProductParams = TestDBUtility.getEqtypeProductRow();
//            l_EqtypeProductParams.setProductId(2601133200000L);
//            TestDBUtility.insertWithDel(l_EqtypeProductParams);
//            
//            TestDBUtility.deleteAll(TradedProductRow.TYPE);
//            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
//            l_tradedProductParams.setTradedProductId(1006160060005L);
//            l_tradedProductParams.setProductId(2601133200000L);
//            l_tradedProductParams.setMarketId(2601L);
//            l_tradedProductParams.setInstitutionCode("0D");
//            TestDBUtility.insertWithDel(l_tradedProductParams);
//
//            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
//            EqtypeTradedProductParams l_eqtypeTradedProductParams =
//                TestDBUtility.getEqtypeTradedProductRow();
//            l_eqtypeTradedProductParams.setTradedProductId(1006160060005L);
//            l_eqtypeTradedProductParams.setProductId(2601133200000L);
//            l_eqtypeTradedProductParams.setMarketId(2601L);
//            l_eqtypeTradedProductParams.setValidUntilBizDate("20040917");
//            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
//            
//            TestDBUtility.deleteAll(MarketRow.TYPE);
//            MarketParams l_MarketParams = TestDBUtility.getMarketRow();
//            l_MarketParams.setMarketCode("1");
//            l_MarketParams.setMarketId(2601L);
//            TestDBUtility.insertWithDel(l_MarketParams);
//            
//            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
//            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
//            l_tradingTimeParams.setInstitutionCode("0D");
//            l_tradingTimeParams.setBranchCode("123");
//            l_tradingTimeParams.setMarketCode("N1");
//            l_tradingTimeParams.setTradingTimeType("01");
//            l_tradingTimeParams.setProductCode("0");
//            l_tradingTimeParams.setBizDateType("1");
//            l_tradingTimeParams.setBizdateCalcParameter("0");
//            TestDBUtility.insertWithDel(l_tradingTimeParams);
//             
//            WEB3EquityTradedProduct l_equityTradedProduct =
//                new WEB3EquityTradedProduct(l_tradedProductParams);
//            l_impl.notifyLimitRangeInfo(l_hostAttentionInfoNotifyParams, l_eqtypeTradedProductParams, null, null);
//            
//        }
//        catch (WEB3BusinessLayerException l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03146, l_ex.getErrorInfo());
//        }
//        catch (DataQueryException l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        catch (DataNetworkException l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        catch (WEB3BaseException l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        finally
//        {
//            try
//            {
//                EqtypeTradedProductRow l_listEqtypeTradedProductRow =
//                    EqtypeTradedProductDao.findRowByPk(1006160060005L);
//                assertEquals(1000, l_listEqtypeTradedProductRow.getLastClosingPrice(), 0);
//                assertTrue(l_listEqtypeTradedProductRow.getHighCompulsivePriceRangeIsNull());
//                assertTrue(l_listEqtypeTradedProductRow.getLowCompulsivePriceRangeIsNull());
//                assertEquals(null, l_listEqtypeTradedProductRow.getPriceRangeUnitType());
//                assertEquals(10, l_listEqtypeTradedProductRow.getBasePrice(), 0);
//                assertEquals("AXRY1", l_listEqtypeTradedProductRow.getLastUpdater());
//            }
//            catch (DataFindException e)
//            {
//                fail();
//            }
//            catch (DataQueryException e)
//            {
//                fail();
//            }
//            catch (DataNetworkException e)
//            {
//                fail();
//            }
//        }
//        log.exiting(STR_METHOD_NAME);
//    }
//    
//    //管理者更新済み項目Listの要素数が2件の場合、
//    //管理者更新済み項目Listに含まれていない要素の項目に対し更新を行なう
//    //　@　@　@　@　@・high_compulsive_price_range 含まれていない
//    //          ・low_compulsive_price_range含まれていない
//    //注意情報通知キュー.制限値幅上限 - 注意情報通知キュー.基準値 = 0
//    //注意情報通知キュー.基準値 - 注意情報通知キュー.制限値幅下限 = 0
//    public void testNotifyLimitRangeInfo_C010()
//    {
//        final String STR_METHOD_NAME = "testNotifyLimitRangeInfo_C010";
//        log.entering(STR_METHOD_NAME);
//
//        try
//        {
//            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp",
//                 new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));
//            
//            ThreadLocalSystemAttributesRegistry.setAttribute("web3.attributes.basetimestampfororderbizdate",
//                 new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));
//            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
//
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
//                    "getSystemTimestamp",
//                    new Class[] {},
//                    new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));  
//
//            TestDBUtility.deleteAll(HostAttentionInfoNotifyRow.TYPE);
//            HostAttentionInfoNotifyParams l_hostAttentionInfoNotifyParams =
//                new HostAttentionInfoNotifyParams();
//            l_hostAttentionInfoNotifyParams.setBasePrice(100);
//            l_hostAttentionInfoNotifyParams.setHighPriceRange(100);
//            l_hostAttentionInfoNotifyParams.setLowPriceRange(100);
//            l_hostAttentionInfoNotifyParams.setRequestCode("AXRY1");
//            TestDBUtility.insertWithDel(l_hostAttentionInfoNotifyParams);
//            
//            TestDBUtility.deleteAll(EqtypeProductConditionRow.TYPE);
//            
//            TestDBUtility.deleteAll(EqtypeProductConditionRow.TYPE);
//            EqtypeProductConditionParams l_eqtypeProductConditionParams = new EqtypeProductConditionParams();
//            l_eqtypeProductConditionParams.setEqtypeProductConditionId(2082L);
//            l_eqtypeProductConditionParams.setInstitutionCode("0D");
//            l_eqtypeProductConditionParams.setProductCode("13320");
//            l_eqtypeProductConditionParams.setProductId(2601133200000L);
//            l_eqtypeProductConditionParams.setMarketCode("1");
//            l_eqtypeProductConditionParams.setMarketId(2601L);
//            l_eqtypeProductConditionParams.setTableName("eqtype_traded_product");
//            l_eqtypeProductConditionParams.setColumnName("last_closing_price");
//            l_eqtypeProductConditionParams.setLargeItemDiv("5");
//            l_eqtypeProductConditionParams.setSmallItemDiv("47");
//            l_eqtypeProductConditionParams.setDataToday("471");
//            l_eqtypeProductConditionParams.setDataNextDay("471");
//            l_eqtypeProductConditionParams.setDataPlan("471");
//            l_eqtypeProductConditionParams.setTermFrom(WEB3DateUtility.getDate("11110101","yyyy/MM/dd HH:mm:ss"));
//            l_eqtypeProductConditionParams.setTermTo(WEB3DateUtility.getDate("9999213","yyyy/MM/dd HH:mm:ss"));
//            l_eqtypeProductConditionParams.setDeleteFlg("0");
//            l_eqtypeProductConditionParams.setLastUpdater("dir");
//            l_eqtypeProductConditionParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_eqtypeProductConditionParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
//            TestDBUtility.insertWithDel(l_eqtypeProductConditionParams);
//            
//            EqtypeProductConditionParams l_eqtypeProductConditionParams3 = new EqtypeProductConditionParams();
//            l_eqtypeProductConditionParams3.setEqtypeProductConditionId(2100);
//            l_eqtypeProductConditionParams3.setInstitutionCode("0D");
//            l_eqtypeProductConditionParams3.setProductCode("13320");
//            l_eqtypeProductConditionParams3.setProductId(2601133200000L);
//            l_eqtypeProductConditionParams3.setMarketCode("1");
//            l_eqtypeProductConditionParams3.setMarketId(2601L);
//            l_eqtypeProductConditionParams3.setTableName("eqtype_traded_product");
//            l_eqtypeProductConditionParams3.setColumnName("base_price");
//            l_eqtypeProductConditionParams3.setLargeItemDiv("5");
//            l_eqtypeProductConditionParams3.setSmallItemDiv("56");
//            l_eqtypeProductConditionParams3.setDataToday("561");
//            l_eqtypeProductConditionParams3.setDataNextDay("561");
//            l_eqtypeProductConditionParams3.setDataPlan("561");
//            l_eqtypeProductConditionParams3.setTermFrom(WEB3DateUtility.getDate("11110101","yyyy/MM/dd HH:mm:ss"));
//            l_eqtypeProductConditionParams3.setTermTo(WEB3DateUtility.getDate("9999213","yyyy/MM/dd HH:mm:ss"));
//            l_eqtypeProductConditionParams3.setDeleteFlg("0");
//            l_eqtypeProductConditionParams3.setLastUpdater("dir");
//            l_eqtypeProductConditionParams3.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_eqtypeProductConditionParams3.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
//            TestDBUtility.insertWithDel(l_eqtypeProductConditionParams3);;
//            
//            TestDBUtility.deleteAll(ProductRow.TYPE);
//            ProductParams l_ProductParams = TestDBUtility.getProductRow();
//            l_ProductParams.setProductId(2601133200000L);
//            TestDBUtility.insertWithDel(l_ProductParams);
//            
//            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
//            EqtypeProductParams l_EqtypeProductParams = TestDBUtility.getEqtypeProductRow();
//            l_EqtypeProductParams.setProductId(2601133200000L);
//            TestDBUtility.insertWithDel(l_EqtypeProductParams);
//            
//            TestDBUtility.deleteAll(TradedProductRow.TYPE);
//            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
//            l_tradedProductParams.setTradedProductId(1006160060005L);
//            l_tradedProductParams.setProductId(2601133200000L);
//            l_tradedProductParams.setMarketId(2601L);
//            l_tradedProductParams.setInstitutionCode("0D");
//            TestDBUtility.insertWithDel(l_tradedProductParams);
//
//            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
//            EqtypeTradedProductParams l_eqtypeTradedProductParams =
//                TestDBUtility.getEqtypeTradedProductRow();
//            l_eqtypeTradedProductParams.setTradedProductId(1006160060005L);
//            l_eqtypeTradedProductParams.setProductId(2601133200000L);
//            l_eqtypeTradedProductParams.setMarketId(2601L);
//            l_eqtypeTradedProductParams.setValidUntilBizDate("20040917");
//            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
//            
//            TestDBUtility.deleteAll(MarketRow.TYPE);
//            MarketParams l_MarketParams = TestDBUtility.getMarketRow();
//            l_MarketParams.setMarketCode("1");
//            l_MarketParams.setMarketId(2601L);
//            TestDBUtility.insertWithDel(l_MarketParams);
//            
//            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
//            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
//            l_tradingTimeParams.setInstitutionCode("0D");
//            l_tradingTimeParams.setBranchCode("123");
//            l_tradingTimeParams.setMarketCode("N1");
//            l_tradingTimeParams.setTradingTimeType("01");
//            l_tradingTimeParams.setProductCode("0");
//            l_tradingTimeParams.setBizDateType("1");
//            l_tradingTimeParams.setBizdateCalcParameter("0");
//            TestDBUtility.insertWithDel(l_tradingTimeParams);
//             
//            WEB3EquityTradedProduct l_equityTradedProduct =
//                new WEB3EquityTradedProduct(l_tradedProductParams);
//            l_impl.notifyLimitRangeInfo(l_hostAttentionInfoNotifyParams, l_eqtypeTradedProductParams, null, null);
//            
//        }
//        catch (WEB3BusinessLayerException l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03146, l_ex.getErrorInfo());
//        }
//        catch (DataQueryException l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        catch (DataNetworkException l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        catch (WEB3BaseException l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        finally
//        {
//            try
//            {
//                EqtypeTradedProductRow l_listEqtypeTradedProductRow =
//                    EqtypeTradedProductDao.findRowByPk(1006160060005L);
//                assertEquals(1000, l_listEqtypeTradedProductRow.getLastClosingPrice(), 0);
//                assertTrue(l_listEqtypeTradedProductRow.getHighCompulsivePriceRangeIsNull());
//                assertTrue(l_listEqtypeTradedProductRow.getLowCompulsivePriceRangeIsNull());
//                assertEquals(null, l_listEqtypeTradedProductRow.getPriceRangeUnitType());
//                assertEquals(10, l_listEqtypeTradedProductRow.getBasePrice(), 0);
//                assertEquals("AXRY1", l_listEqtypeTradedProductRow.getLastUpdater());
//            }
//            catch (DataFindException e)
//            {
//                fail();
//            }
//            catch (DataQueryException e)
//            {
//                fail();
//            }
//            catch (DataNetworkException e)
//            {
//                fail();
//            }
//        }
//        log.exiting(STR_METHOD_NAME);
//    }
//    
//    //管理者更新済み項目Listの要素数が2件の場合
//    //管理者更新済み項目Listに含まれていない要素の項目に対し更新を行なう
//    //　@　@　@　@　@・high_compulsive_price_range 含まれていない
//    //          ・low_compulsive_price_range含まれていない
//    //注意情報通知キュー.制限値幅上限 - 注意情報通知キュー.基準値 > 0
//    //注意情報通知キュー.基準値 - 注意情報通知キュー.制限値幅下限 < 0
//    public void testNotifyLimitRangeInfo_C011()
//    {
//        final String STR_METHOD_NAME = "testNotifyLimitRangeInfo_C011";
//        log.entering(STR_METHOD_NAME);
//
//        try
//        {
//            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp",
//                 new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));
//            
//            ThreadLocalSystemAttributesRegistry.setAttribute("web3.attributes.basetimestampfororderbizdate",
//                 new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));
//            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
//
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
//                    "getSystemTimestamp",
//                    new Class[] {},
//                    new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));  
//
//            TestDBUtility.deleteAll(HostAttentionInfoNotifyRow.TYPE);
//            HostAttentionInfoNotifyParams l_hostAttentionInfoNotifyParams =
//                new HostAttentionInfoNotifyParams();
//            l_hostAttentionInfoNotifyParams.setBasePrice(100.012);
//            l_hostAttentionInfoNotifyParams.setHighPriceRange(150.03);
//            l_hostAttentionInfoNotifyParams.setLowPriceRange(110.045);
//            l_hostAttentionInfoNotifyParams.setRequestCode("AXRY1");
//            TestDBUtility.insertWithDel(l_hostAttentionInfoNotifyParams);
//            
//            TestDBUtility.deleteAll(EqtypeProductConditionRow.TYPE);
//            
//            TestDBUtility.deleteAll(EqtypeProductConditionRow.TYPE);
//            EqtypeProductConditionParams l_eqtypeProductConditionParams = new EqtypeProductConditionParams();
//            l_eqtypeProductConditionParams.setEqtypeProductConditionId(2082L);
//            l_eqtypeProductConditionParams.setInstitutionCode("0D");
//            l_eqtypeProductConditionParams.setProductCode("13320");
//            l_eqtypeProductConditionParams.setProductId(2601133200000L);
//            l_eqtypeProductConditionParams.setMarketCode("1");
//            l_eqtypeProductConditionParams.setMarketId(2601L);
//            l_eqtypeProductConditionParams.setTableName("eqtype_traded_product");
//            l_eqtypeProductConditionParams.setColumnName("last_closing_price");
//            l_eqtypeProductConditionParams.setLargeItemDiv("5");
//            l_eqtypeProductConditionParams.setSmallItemDiv("47");
//            l_eqtypeProductConditionParams.setDataToday("471");
//            l_eqtypeProductConditionParams.setDataNextDay("471");
//            l_eqtypeProductConditionParams.setDataPlan("471");
//            l_eqtypeProductConditionParams.setTermFrom(WEB3DateUtility.getDate("11110101","yyyy/MM/dd HH:mm:ss"));
//            l_eqtypeProductConditionParams.setTermTo(WEB3DateUtility.getDate("9999213","yyyy/MM/dd HH:mm:ss"));
//            l_eqtypeProductConditionParams.setDeleteFlg("0");
//            l_eqtypeProductConditionParams.setLastUpdater("dir");
//            l_eqtypeProductConditionParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_eqtypeProductConditionParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
//            TestDBUtility.insertWithDel(l_eqtypeProductConditionParams);
//            
//            EqtypeProductConditionParams l_eqtypeProductConditionParams3 = new EqtypeProductConditionParams();
//            l_eqtypeProductConditionParams3.setEqtypeProductConditionId(2100);
//            l_eqtypeProductConditionParams3.setInstitutionCode("0D");
//            l_eqtypeProductConditionParams3.setProductCode("13320");
//            l_eqtypeProductConditionParams3.setProductId(2601133200000L);
//            l_eqtypeProductConditionParams3.setMarketCode("1");
//            l_eqtypeProductConditionParams3.setMarketId(2601L);
//            l_eqtypeProductConditionParams3.setTableName("eqtype_traded_product");
//            l_eqtypeProductConditionParams3.setColumnName("base_price");
//            l_eqtypeProductConditionParams3.setLargeItemDiv("5");
//            l_eqtypeProductConditionParams3.setSmallItemDiv("56");
//            l_eqtypeProductConditionParams3.setDataToday("561");
//            l_eqtypeProductConditionParams3.setDataNextDay("561");
//            l_eqtypeProductConditionParams3.setDataPlan("561");
//            l_eqtypeProductConditionParams3.setTermFrom(WEB3DateUtility.getDate("11110101","yyyy/MM/dd HH:mm:ss"));
//            l_eqtypeProductConditionParams3.setTermTo(WEB3DateUtility.getDate("9999213","yyyy/MM/dd HH:mm:ss"));
//            l_eqtypeProductConditionParams3.setDeleteFlg("0");
//            l_eqtypeProductConditionParams3.setLastUpdater("dir");
//            l_eqtypeProductConditionParams3.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_eqtypeProductConditionParams3.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
//            TestDBUtility.insertWithDel(l_eqtypeProductConditionParams3);;
//            
//            TestDBUtility.deleteAll(ProductRow.TYPE);
//            ProductParams l_ProductParams = TestDBUtility.getProductRow();
//            l_ProductParams.setProductId(2601133200000L);
//            TestDBUtility.insertWithDel(l_ProductParams);
//            
//            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
//            EqtypeProductParams l_EqtypeProductParams = TestDBUtility.getEqtypeProductRow();
//            l_EqtypeProductParams.setProductId(2601133200000L);
//            TestDBUtility.insertWithDel(l_EqtypeProductParams);
//            
//            TestDBUtility.deleteAll(TradedProductRow.TYPE);
//            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
//            l_tradedProductParams.setTradedProductId(1006160060005L);
//            l_tradedProductParams.setProductId(2601133200000L);
//            l_tradedProductParams.setMarketId(2601L);
//            l_tradedProductParams.setInstitutionCode("0D");
//            TestDBUtility.insertWithDel(l_tradedProductParams);
//
//            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
//            EqtypeTradedProductParams l_eqtypeTradedProductParams =
//                TestDBUtility.getEqtypeTradedProductRow();
//            l_eqtypeTradedProductParams.setTradedProductId(1006160060005L);
//            l_eqtypeTradedProductParams.setProductId(2601133200000L);
//            l_eqtypeTradedProductParams.setMarketId(2601L);
//            l_eqtypeTradedProductParams.setValidUntilBizDate("20040917");
//            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
//            
//            TestDBUtility.deleteAll(MarketRow.TYPE);
//            MarketParams l_MarketParams = TestDBUtility.getMarketRow();
//            l_MarketParams.setMarketCode("1");
//            l_MarketParams.setMarketId(2601L);
//            TestDBUtility.insertWithDel(l_MarketParams);
//            
//            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
//            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
//            l_tradingTimeParams.setInstitutionCode("0D");
//            l_tradingTimeParams.setBranchCode("123");
//            l_tradingTimeParams.setMarketCode("N1");
//            l_tradingTimeParams.setTradingTimeType("01");
//            l_tradingTimeParams.setProductCode("0");
//            l_tradingTimeParams.setBizDateType("1");
//            l_tradingTimeParams.setBizdateCalcParameter("0");
//            TestDBUtility.insertWithDel(l_tradingTimeParams);
//             
//            WEB3EquityTradedProduct l_equityTradedProduct =
//                new WEB3EquityTradedProduct(l_tradedProductParams);
//            l_impl.notifyLimitRangeInfo(l_hostAttentionInfoNotifyParams, l_eqtypeTradedProductParams, null, null);
//            
//        }
//        catch (WEB3BusinessLayerException l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03146, l_ex.getErrorInfo());
//        }
//        catch (DataQueryException l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        catch (DataNetworkException l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        catch (WEB3BaseException l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        finally
//        {
//            try
//            {
//                EqtypeTradedProductRow l_listEqtypeTradedProductRow =
//                    EqtypeTradedProductDao.findRowByPk(1006160060005L);
//                assertEquals(1000, l_listEqtypeTradedProductRow.getLastClosingPrice(), 0);
//                assertEquals(50.018, l_listEqtypeTradedProductRow.getHighCompulsivePriceRange(), 0);
//                assertTrue(l_listEqtypeTradedProductRow.getLowCompulsivePriceRangeIsNull());
//                assertEquals(null, l_listEqtypeTradedProductRow.getPriceRangeUnitType());
//                assertEquals(10, l_listEqtypeTradedProductRow.getBasePrice(), 0);
//                assertEquals("AXRY1", l_listEqtypeTradedProductRow.getLastUpdater());
//            }
//            catch (DataFindException e)
//            {
//                fail();
//            }
//            catch (DataQueryException e)
//            {
//                fail();
//            }
//            catch (DataNetworkException e)
//            {
//                fail();
//            }
//        }
//        log.exiting(STR_METHOD_NAME);
//    }
//    
//    //管理者更新済み項目Listの要素数が2件の場合、
//    //管理者更新済み項目Listに含まれていない要素の項目に対し更新を行なう
//    //　@　@　@　@　@・high_compulsive_price_range 含まれていない
//    //          ・low_compulsive_price_range含まれていない
//    //注意情報通知キュー.制限値幅上限 - 注意情報通知キュー.基準値 < 0
//    //注意情報通知キュー.基準値 - 注意情報通知キュー.制限値幅下限 > 0
//    public void testNotifyLimitRangeInfo_C012()
//    {
//        final String STR_METHOD_NAME = "testNotifyLimitRangeInfo_C012";
//        log.entering(STR_METHOD_NAME);
//
//        try
//        {
//            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp",
//                 new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));
//            
//            ThreadLocalSystemAttributesRegistry.setAttribute("web3.attributes.basetimestampfororderbizdate",
//                 new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));
//            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
//
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
//                    "getSystemTimestamp",
//                    new Class[] {},
//                    new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));  
//
//            TestDBUtility.deleteAll(HostAttentionInfoNotifyRow.TYPE);
//            HostAttentionInfoNotifyParams l_hostAttentionInfoNotifyParams =
//                new HostAttentionInfoNotifyParams();
//            l_hostAttentionInfoNotifyParams.setBasePrice(100.012);
//            l_hostAttentionInfoNotifyParams.setHighPriceRange(90.03);
//            l_hostAttentionInfoNotifyParams.setLowPriceRange(90.045);
//            l_hostAttentionInfoNotifyParams.setRequestCode("AXRY1");
//            TestDBUtility.insertWithDel(l_hostAttentionInfoNotifyParams);
//            
//            TestDBUtility.deleteAll(EqtypeProductConditionRow.TYPE);
//            
//            TestDBUtility.deleteAll(EqtypeProductConditionRow.TYPE);
//            EqtypeProductConditionParams l_eqtypeProductConditionParams = new EqtypeProductConditionParams();
//            l_eqtypeProductConditionParams.setEqtypeProductConditionId(2082L);
//            l_eqtypeProductConditionParams.setInstitutionCode("0D");
//            l_eqtypeProductConditionParams.setProductCode("13320");
//            l_eqtypeProductConditionParams.setProductId(2601133200000L);
//            l_eqtypeProductConditionParams.setMarketCode("1");
//            l_eqtypeProductConditionParams.setMarketId(2601L);
//            l_eqtypeProductConditionParams.setTableName("eqtype_traded_product");
//            l_eqtypeProductConditionParams.setColumnName("last_closing_price");
//            l_eqtypeProductConditionParams.setLargeItemDiv("5");
//            l_eqtypeProductConditionParams.setSmallItemDiv("47");
//            l_eqtypeProductConditionParams.setDataToday("471");
//            l_eqtypeProductConditionParams.setDataNextDay("471");
//            l_eqtypeProductConditionParams.setDataPlan("471");
//            l_eqtypeProductConditionParams.setTermFrom(WEB3DateUtility.getDate("11110101","yyyy/MM/dd HH:mm:ss"));
//            l_eqtypeProductConditionParams.setTermTo(WEB3DateUtility.getDate("9999213","yyyy/MM/dd HH:mm:ss"));
//            l_eqtypeProductConditionParams.setDeleteFlg("0");
//            l_eqtypeProductConditionParams.setLastUpdater("dir");
//            l_eqtypeProductConditionParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_eqtypeProductConditionParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
//            TestDBUtility.insertWithDel(l_eqtypeProductConditionParams);
//            
//            EqtypeProductConditionParams l_eqtypeProductConditionParams3 = new EqtypeProductConditionParams();
//            l_eqtypeProductConditionParams3.setEqtypeProductConditionId(2100);
//            l_eqtypeProductConditionParams3.setInstitutionCode("0D");
//            l_eqtypeProductConditionParams3.setProductCode("13320");
//            l_eqtypeProductConditionParams3.setProductId(2601133200000L);
//            l_eqtypeProductConditionParams3.setMarketCode("1");
//            l_eqtypeProductConditionParams3.setMarketId(2601L);
//            l_eqtypeProductConditionParams3.setTableName("eqtype_traded_product");
//            l_eqtypeProductConditionParams3.setColumnName("base_price");
//            l_eqtypeProductConditionParams3.setLargeItemDiv("5");
//            l_eqtypeProductConditionParams3.setSmallItemDiv("56");
//            l_eqtypeProductConditionParams3.setDataToday("561");
//            l_eqtypeProductConditionParams3.setDataNextDay("561");
//            l_eqtypeProductConditionParams3.setDataPlan("561");
//            l_eqtypeProductConditionParams3.setTermFrom(WEB3DateUtility.getDate("11110101","yyyy/MM/dd HH:mm:ss"));
//            l_eqtypeProductConditionParams3.setTermTo(WEB3DateUtility.getDate("9999213","yyyy/MM/dd HH:mm:ss"));
//            l_eqtypeProductConditionParams3.setDeleteFlg("0");
//            l_eqtypeProductConditionParams3.setLastUpdater("dir");
//            l_eqtypeProductConditionParams3.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_eqtypeProductConditionParams3.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
//            TestDBUtility.insertWithDel(l_eqtypeProductConditionParams3);;
//            
//            TestDBUtility.deleteAll(ProductRow.TYPE);
//            ProductParams l_ProductParams = TestDBUtility.getProductRow();
//            l_ProductParams.setProductId(2601133200000L);
//            TestDBUtility.insertWithDel(l_ProductParams);
//            
//            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
//            EqtypeProductParams l_EqtypeProductParams = TestDBUtility.getEqtypeProductRow();
//            l_EqtypeProductParams.setProductId(2601133200000L);
//            TestDBUtility.insertWithDel(l_EqtypeProductParams);
//            
//            TestDBUtility.deleteAll(TradedProductRow.TYPE);
//            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
//            l_tradedProductParams.setTradedProductId(1006160060005L);
//            l_tradedProductParams.setProductId(2601133200000L);
//            l_tradedProductParams.setMarketId(2601L);
//            l_tradedProductParams.setInstitutionCode("0D");
//            TestDBUtility.insertWithDel(l_tradedProductParams);
//
//            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
//            EqtypeTradedProductParams l_eqtypeTradedProductParams =
//                TestDBUtility.getEqtypeTradedProductRow();
//            l_eqtypeTradedProductParams.setTradedProductId(1006160060005L);
//            l_eqtypeTradedProductParams.setProductId(2601133200000L);
//            l_eqtypeTradedProductParams.setMarketId(2601L);
//            l_eqtypeTradedProductParams.setValidUntilBizDate("20040917");
//            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
//            
//            TestDBUtility.deleteAll(MarketRow.TYPE);
//            MarketParams l_MarketParams = TestDBUtility.getMarketRow();
//            l_MarketParams.setMarketCode("1");
//            l_MarketParams.setMarketId(2601L);
//            TestDBUtility.insertWithDel(l_MarketParams);
//            
//            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
//            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
//            l_tradingTimeParams.setInstitutionCode("0D");
//            l_tradingTimeParams.setBranchCode("123");
//            l_tradingTimeParams.setMarketCode("N1");
//            l_tradingTimeParams.setTradingTimeType("01");
//            l_tradingTimeParams.setProductCode("0");
//            l_tradingTimeParams.setBizDateType("1");
//            l_tradingTimeParams.setBizdateCalcParameter("0");
//            TestDBUtility.insertWithDel(l_tradingTimeParams);
//             
//            WEB3EquityTradedProduct l_equityTradedProduct =
//                new WEB3EquityTradedProduct(l_tradedProductParams);
//            l_impl.notifyLimitRangeInfo(l_hostAttentionInfoNotifyParams, l_eqtypeTradedProductParams, null, null);
//            
//        }
//        catch (WEB3BusinessLayerException l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03146, l_ex.getErrorInfo());
//        }
//        catch (DataQueryException l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        catch (DataNetworkException l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        catch (WEB3BaseException l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        finally
//        {
//            try
//            {
//                EqtypeTradedProductRow l_listEqtypeTradedProductRow =
//                    EqtypeTradedProductDao.findRowByPk(1006160060005L);
//                assertEquals(1000, l_listEqtypeTradedProductRow.getLastClosingPrice(), 0);
//                assertTrue(l_listEqtypeTradedProductRow.getHighCompulsivePriceRangeIsNull());
//                assertEquals(9.967, l_listEqtypeTradedProductRow.getLowCompulsivePriceRange(), 0);
//                assertEquals(null, l_listEqtypeTradedProductRow.getPriceRangeUnitType());
//                assertEquals(10, l_listEqtypeTradedProductRow.getBasePrice(), 0);
//                assertEquals("AXRY1", l_listEqtypeTradedProductRow.getLastUpdater());
//            }
//            catch (DataFindException e)
//            {
//                fail();
//            }
//            catch (DataQueryException e)
//            {
//                fail();
//            }
//            catch (DataNetworkException e)
//            {
//                fail();
//            }
//        }
//        log.exiting(STR_METHOD_NAME);
//    }
//    
//    //  管理者更新済み項目Listの要素数が2件の場合、
//    //管理者更新済み項目Listに含まれていない要素の項目に対し更新を行なう
//    //　@　@　@　@　@・high_compulsive_price_range 含まれていない
//    //          ・low_compulsive_price_range含まれていない
//    //注意情報通知キュー.制限値幅上限 - 注意情報通知キュー.基準値 > 0
//    //注意情報通知キュー.基準値 - 注意情報通知キュー.制限値幅下限 > 0
//    public void testNotifyLimitRangeInfo_C0013()
//    {
//        final String STR_METHOD_NAME = "testNotifyLimitRangeInfo_C0013";
//        log.entering(STR_METHOD_NAME);
//
//        try
//        {
//            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp",
//                 new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));
//            
//            ThreadLocalSystemAttributesRegistry.setAttribute("web3.attributes.basetimestampfororderbizdate",
//                 new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));
//            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
//
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
//                    "getSystemTimestamp",
//                    new Class[] {},
//                    new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));  
//
//            TestDBUtility.deleteAll(HostAttentionInfoNotifyRow.TYPE);
//            HostAttentionInfoNotifyParams l_hostAttentionInfoNotifyParams =
//                new HostAttentionInfoNotifyParams();
//            l_hostAttentionInfoNotifyParams.setBasePrice(100);
//            l_hostAttentionInfoNotifyParams.setHighPriceRange(null);
//            l_hostAttentionInfoNotifyParams.setLowPriceRange(null);
//            l_hostAttentionInfoNotifyParams.setRequestCode("AXRY1");
//            TestDBUtility.insertWithDel(l_hostAttentionInfoNotifyParams);
//            
//            TestDBUtility.deleteAll(EqtypeProductConditionRow.TYPE);
//            
//            TestDBUtility.deleteAll(EqtypeProductConditionRow.TYPE);
//            EqtypeProductConditionParams l_eqtypeProductConditionParams = new EqtypeProductConditionParams();
//            l_eqtypeProductConditionParams.setEqtypeProductConditionId(2082L);
//            l_eqtypeProductConditionParams.setInstitutionCode("0D");
//            l_eqtypeProductConditionParams.setProductCode("13320");
//            l_eqtypeProductConditionParams.setProductId(2601133200000L);
//            l_eqtypeProductConditionParams.setMarketCode("1");
//            l_eqtypeProductConditionParams.setMarketId(2601L);
//            l_eqtypeProductConditionParams.setTableName("eqtype_traded_product");
//            l_eqtypeProductConditionParams.setColumnName("last_closing_price");
//            l_eqtypeProductConditionParams.setLargeItemDiv("5");
//            l_eqtypeProductConditionParams.setSmallItemDiv("47");
//            l_eqtypeProductConditionParams.setDataToday("471");
//            l_eqtypeProductConditionParams.setDataNextDay("471");
//            l_eqtypeProductConditionParams.setDataPlan("471");
//            l_eqtypeProductConditionParams.setTermFrom(WEB3DateUtility.getDate("11110101","yyyy/MM/dd HH:mm:ss"));
//            l_eqtypeProductConditionParams.setTermTo(WEB3DateUtility.getDate("9999213","yyyy/MM/dd HH:mm:ss"));
//            l_eqtypeProductConditionParams.setDeleteFlg("0");
//            l_eqtypeProductConditionParams.setLastUpdater("dir");
//            l_eqtypeProductConditionParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_eqtypeProductConditionParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
//            TestDBUtility.insertWithDel(l_eqtypeProductConditionParams);
//            
//            EqtypeProductConditionParams l_eqtypeProductConditionParams3 = new EqtypeProductConditionParams();
//            l_eqtypeProductConditionParams3.setEqtypeProductConditionId(2100);
//            l_eqtypeProductConditionParams3.setInstitutionCode("0D");
//            l_eqtypeProductConditionParams3.setProductCode("13320");
//            l_eqtypeProductConditionParams3.setProductId(2601133200000L);
//            l_eqtypeProductConditionParams3.setMarketCode("1");
//            l_eqtypeProductConditionParams3.setMarketId(2601L);
//            l_eqtypeProductConditionParams3.setTableName("eqtype_traded_product");
//            l_eqtypeProductConditionParams3.setColumnName("base_price");
//            l_eqtypeProductConditionParams3.setLargeItemDiv("5");
//            l_eqtypeProductConditionParams3.setSmallItemDiv("56");
//            l_eqtypeProductConditionParams3.setDataToday("561");
//            l_eqtypeProductConditionParams3.setDataNextDay("561");
//            l_eqtypeProductConditionParams3.setDataPlan("561");
//            l_eqtypeProductConditionParams3.setTermFrom(WEB3DateUtility.getDate("11110101","yyyy/MM/dd HH:mm:ss"));
//            l_eqtypeProductConditionParams3.setTermTo(WEB3DateUtility.getDate("9999213","yyyy/MM/dd HH:mm:ss"));
//            l_eqtypeProductConditionParams3.setDeleteFlg("0");
//            l_eqtypeProductConditionParams3.setLastUpdater("dir");
//            l_eqtypeProductConditionParams3.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_eqtypeProductConditionParams3.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
//            TestDBUtility.insertWithDel(l_eqtypeProductConditionParams3);;
//            
//            TestDBUtility.deleteAll(ProductRow.TYPE);
//            ProductParams l_ProductParams = TestDBUtility.getProductRow();
//            l_ProductParams.setProductId(2601133200000L);
//            TestDBUtility.insertWithDel(l_ProductParams);
//            
//            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
//            EqtypeProductParams l_EqtypeProductParams = TestDBUtility.getEqtypeProductRow();
//            l_EqtypeProductParams.setProductId(2601133200000L);
//            TestDBUtility.insertWithDel(l_EqtypeProductParams);
//            
//            TestDBUtility.deleteAll(TradedProductRow.TYPE);
//            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
//            l_tradedProductParams.setTradedProductId(1006160060005L);
//            l_tradedProductParams.setProductId(2601133200000L);
//            l_tradedProductParams.setMarketId(2601L);
//            l_tradedProductParams.setInstitutionCode("0D");
//            TestDBUtility.insertWithDel(l_tradedProductParams);
//
//            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
//            EqtypeTradedProductParams l_eqtypeTradedProductParams =
//                TestDBUtility.getEqtypeTradedProductRow();
//            l_eqtypeTradedProductParams.setTradedProductId(1006160060005L);
//            l_eqtypeTradedProductParams.setProductId(2601133200000L);
//            l_eqtypeTradedProductParams.setMarketId(2601L);
//            l_eqtypeTradedProductParams.setValidUntilBizDate("20040917");
//            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
//            
//            TestDBUtility.deleteAll(MarketRow.TYPE);
//            MarketParams l_MarketParams = TestDBUtility.getMarketRow();
//            l_MarketParams.setMarketCode("1");
//            l_MarketParams.setMarketId(2601L);
//            TestDBUtility.insertWithDel(l_MarketParams);
//            
//            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
//            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
//            l_tradingTimeParams.setInstitutionCode("0D");
//            l_tradingTimeParams.setBranchCode("123");
//            l_tradingTimeParams.setMarketCode("N1");
//            l_tradingTimeParams.setTradingTimeType("01");
//            l_tradingTimeParams.setProductCode("0");
//            l_tradingTimeParams.setBizDateType("1");
//            l_tradingTimeParams.setBizdateCalcParameter("0");
//            TestDBUtility.insertWithDel(l_tradingTimeParams);
//             
//            WEB3EquityTradedProduct l_equityTradedProduct =
//                new WEB3EquityTradedProduct(l_tradedProductParams);
//            l_impl.notifyLimitRangeInfo(l_hostAttentionInfoNotifyParams, l_eqtypeTradedProductParams, null, null);
//            
//        }
//        catch (WEB3BusinessLayerException l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03146, l_ex.getErrorInfo());
//        }
//        catch (DataQueryException l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        catch (DataNetworkException l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        catch (WEB3BaseException l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        finally
//        {
//            try
//            {
//                EqtypeTradedProductRow l_listEqtypeTradedProductRow =
//                    EqtypeTradedProductDao.findRowByPk(1006160060005L);
//                
//                assertEquals(1000.0, l_listEqtypeTradedProductRow.getLastClosingPrice(), 1);
//                assertTrue(l_listEqtypeTradedProductRow.getHighCompulsivePriceRangeIsNull());
//                assertTrue(l_listEqtypeTradedProductRow.getLowCompulsivePriceRangeIsNull());
//                assertEquals(null, l_listEqtypeTradedProductRow.getPriceRangeUnitType());
//                assertEquals(10.0, l_listEqtypeTradedProductRow.getBasePrice(), 1);
//                assertEquals("AXRY1", l_listEqtypeTradedProductRow.getLastUpdater());
//            }
//            catch (DataFindException e)
//            {
//                fail();
//            }
//            catch (DataQueryException e)
//            {
//                fail();
//            }
//            catch (DataNetworkException e)
//            {
//                fail();
//            }
//        }
//        log.exiting(STR_METHOD_NAME);
//    }
//    
//    //管理者更新済み項目Listの要素数が0件の場合、該当する全ての項目に対し更新を行なう
//    //注意情報通知キュー.制限値幅上限 - 注意情報通知キュー.基準値 > 0
//    //注意情報通知キュー.基準値 - 注意情報通知キュー.制限値幅下限 > 0
//    public void testNotifyLimitRangeInfo_C0014()
//    {
//        final String STR_METHOD_NAME = "testNotifyLimitRangeInfo_C0014";
//        log.entering(STR_METHOD_NAME);
//
//        try
//        {
//            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp",
//                 new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));
//            
//            ThreadLocalSystemAttributesRegistry.setAttribute("web3.attributes.basetimestampfororderbizdate",
//                 new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));
//            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
//
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
//                    "getSystemTimestamp",
//                    new Class[] {},
//                    new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));  
//
//            TestDBUtility.deleteAll(HostAttentionInfoNotifyRow.TYPE);
//            HostAttentionInfoNotifyParams l_hostAttentionInfoNotifyParams =
//                new HostAttentionInfoNotifyParams();
//            l_hostAttentionInfoNotifyParams.setBasePrice(100);
//            l_hostAttentionInfoNotifyParams.setHighPriceRange(null);
//            l_hostAttentionInfoNotifyParams.setLowPriceRange(null);
//            l_hostAttentionInfoNotifyParams.setRequestCode("AXRY1");
//            TestDBUtility.insertWithDel(l_hostAttentionInfoNotifyParams);
//            
//            TestDBUtility.deleteAll(EqtypeProductConditionRow.TYPE);
//            
//            TestDBUtility.deleteAll(ProductRow.TYPE);
//            ProductParams l_ProductParams = TestDBUtility.getProductRow();
//            l_ProductParams.setProductId(2601133200000L);
//            TestDBUtility.insertWithDel(l_ProductParams);
//            
//            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
//            EqtypeProductParams l_EqtypeProductParams = TestDBUtility.getEqtypeProductRow();
//            l_EqtypeProductParams.setProductId(2601133200000L);
//            TestDBUtility.insertWithDel(l_EqtypeProductParams);
//            
//            TestDBUtility.deleteAll(TradedProductRow.TYPE);
//            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
//            l_tradedProductParams.setTradedProductId(1006160060005L);
//            l_tradedProductParams.setProductId(2601133200000L);
//            l_tradedProductParams.setMarketId(2601L);
//            l_tradedProductParams.setInstitutionCode("0D");
//            TestDBUtility.insertWithDel(l_tradedProductParams);
//
//            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
//            EqtypeTradedProductParams l_eqtypeTradedProductParams =
//                TestDBUtility.getEqtypeTradedProductRow();
//            l_eqtypeTradedProductParams.setTradedProductId(1006160060005L);
//            l_eqtypeTradedProductParams.setProductId(2601133200000L);
//            l_eqtypeTradedProductParams.setMarketId(2601L);
//            l_eqtypeTradedProductParams.setValidUntilBizDate("20040917");
//            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
//            
//            TestDBUtility.deleteAll(MarketRow.TYPE);
//            MarketParams l_MarketParams = TestDBUtility.getMarketRow();
//            l_MarketParams.setMarketCode("1");
//            l_MarketParams.setMarketId(2601L);
//            TestDBUtility.insertWithDel(l_MarketParams);
//            
//            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
//            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
//            l_tradingTimeParams.setInstitutionCode("0D");
//            l_tradingTimeParams.setBranchCode("123");
//            l_tradingTimeParams.setMarketCode("N1");
//            l_tradingTimeParams.setTradingTimeType("01");
//            l_tradingTimeParams.setProductCode("0");
//            l_tradingTimeParams.setBizDateType("1");
//            l_tradingTimeParams.setBizdateCalcParameter("0");
//            TestDBUtility.insertWithDel(l_tradingTimeParams);
//             
//            WEB3EquityTradedProduct l_equityTradedProduct =
//                new WEB3EquityTradedProduct(l_tradedProductParams);
//            l_impl.notifyLimitRangeInfo(l_hostAttentionInfoNotifyParams, l_eqtypeTradedProductParams, null, null);
//            
//            EqtypeTradedProductRow l_listEqtypeTradedProductRow = EqtypeTradedProductDao.findRowByPk(1006160060005L);
//            assertEquals(100, l_listEqtypeTradedProductRow.getLastClosingPrice(), 0);
//            assertTrue(l_listEqtypeTradedProductRow.getHighCompulsivePriceRangeIsNull());
//            assertTrue(l_listEqtypeTradedProductRow.getLowCompulsivePriceRangeIsNull());
//            assertEquals(null, l_listEqtypeTradedProductRow.getPriceRangeUnitType());
//            assertEquals(100, l_listEqtypeTradedProductRow.getBasePrice(), 0);
//            assertEquals("AXRY1", l_listEqtypeTradedProductRow.getLastUpdater());
//        }
//        catch (DataQueryException l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        catch (DataNetworkException l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        catch (WEB3BaseException l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        log.exiting(STR_METHOD_NAME);
//    }
//    
//    //  管理者更新済み項目Listの要素数が2件の場合、
//    //管理者更新済み項目Listに含まれていない要素の項目に対し更新を行なう
//    //　@　@　@　@　@・high_compulsive_price_range 含まれていない
//    //          ・low_compulsive_price_range含まれていない
//    //注意情報通知キュー.制限値幅上限 - 注意情報通知キュー.基準値 > 0
//    //注意情報通知キュー.基準値 - 注意情報通知キュー.制限値幅下限 > 0
//    public void testNotifyLimitRangeInfo_C0015()
//    {
//        final String STR_METHOD_NAME = "testNotifyLimitRangeInfo_C0013";
//        log.entering(STR_METHOD_NAME);
//
//        try
//        {
//            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp",
//                 new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));
//            
//            ThreadLocalSystemAttributesRegistry.setAttribute("web3.attributes.basetimestampfororderbizdate",
//                 new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));
//            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
//
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
//                    "getSystemTimestamp",
//                    new Class[] {},
//                    new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));  
//
//            TestDBUtility.deleteAll(HostAttentionInfoNotifyRow.TYPE);
//            HostAttentionInfoNotifyParams l_hostAttentionInfoNotifyParams =
//                new HostAttentionInfoNotifyParams();
//            l_hostAttentionInfoNotifyParams.setBasePrice(100);
//            l_hostAttentionInfoNotifyParams.setHighPriceRange(null);
//            l_hostAttentionInfoNotifyParams.setLowPriceRange(50);
//            l_hostAttentionInfoNotifyParams.setRequestCode("AXRY1");
//            TestDBUtility.insertWithDel(l_hostAttentionInfoNotifyParams);
//            
//            TestDBUtility.deleteAll(EqtypeProductConditionRow.TYPE);
//            
//            TestDBUtility.deleteAll(EqtypeProductConditionRow.TYPE);
//            EqtypeProductConditionParams l_eqtypeProductConditionParams = new EqtypeProductConditionParams();
//            l_eqtypeProductConditionParams.setEqtypeProductConditionId(2082L);
//            l_eqtypeProductConditionParams.setInstitutionCode("0D");
//            l_eqtypeProductConditionParams.setProductCode("13320");
//            l_eqtypeProductConditionParams.setProductId(2601133200000L);
//            l_eqtypeProductConditionParams.setMarketCode("1");
//            l_eqtypeProductConditionParams.setMarketId(2601L);
//            l_eqtypeProductConditionParams.setTableName("eqtype_traded_product");
//            l_eqtypeProductConditionParams.setColumnName("last_closing_price");
//            l_eqtypeProductConditionParams.setLargeItemDiv("5");
//            l_eqtypeProductConditionParams.setSmallItemDiv("47");
//            l_eqtypeProductConditionParams.setDataToday("471");
//            l_eqtypeProductConditionParams.setDataNextDay("471");
//            l_eqtypeProductConditionParams.setDataPlan("471");
//            l_eqtypeProductConditionParams.setTermFrom(WEB3DateUtility.getDate("11110101","yyyy/MM/dd HH:mm:ss"));
//            l_eqtypeProductConditionParams.setTermTo(WEB3DateUtility.getDate("9999213","yyyy/MM/dd HH:mm:ss"));
//            l_eqtypeProductConditionParams.setDeleteFlg("0");
//            l_eqtypeProductConditionParams.setLastUpdater("dir");
//            l_eqtypeProductConditionParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_eqtypeProductConditionParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
//            TestDBUtility.insertWithDel(l_eqtypeProductConditionParams);
//            
//            EqtypeProductConditionParams l_eqtypeProductConditionParams3 = new EqtypeProductConditionParams();
//            l_eqtypeProductConditionParams3.setEqtypeProductConditionId(2100);
//            l_eqtypeProductConditionParams3.setInstitutionCode("0D");
//            l_eqtypeProductConditionParams3.setProductCode("13320");
//            l_eqtypeProductConditionParams3.setProductId(2601133200000L);
//            l_eqtypeProductConditionParams3.setMarketCode("1");
//            l_eqtypeProductConditionParams3.setMarketId(2601L);
//            l_eqtypeProductConditionParams3.setTableName("eqtype_traded_product");
//            l_eqtypeProductConditionParams3.setColumnName("base_price");
//            l_eqtypeProductConditionParams3.setLargeItemDiv("5");
//            l_eqtypeProductConditionParams3.setSmallItemDiv("56");
//            l_eqtypeProductConditionParams3.setDataToday("561");
//            l_eqtypeProductConditionParams3.setDataNextDay("561");
//            l_eqtypeProductConditionParams3.setDataPlan("561");
//            l_eqtypeProductConditionParams3.setTermFrom(WEB3DateUtility.getDate("11110101","yyyy/MM/dd HH:mm:ss"));
//            l_eqtypeProductConditionParams3.setTermTo(WEB3DateUtility.getDate("9999213","yyyy/MM/dd HH:mm:ss"));
//            l_eqtypeProductConditionParams3.setDeleteFlg("0");
//            l_eqtypeProductConditionParams3.setLastUpdater("dir");
//            l_eqtypeProductConditionParams3.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_eqtypeProductConditionParams3.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
//            TestDBUtility.insertWithDel(l_eqtypeProductConditionParams3);;
//            
//            TestDBUtility.deleteAll(ProductRow.TYPE);
//            ProductParams l_ProductParams = TestDBUtility.getProductRow();
//            l_ProductParams.setProductId(2601133200000L);
//            TestDBUtility.insertWithDel(l_ProductParams);
//            
//            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
//            EqtypeProductParams l_EqtypeProductParams = TestDBUtility.getEqtypeProductRow();
//            l_EqtypeProductParams.setProductId(2601133200000L);
//            TestDBUtility.insertWithDel(l_EqtypeProductParams);
//            
//            TestDBUtility.deleteAll(TradedProductRow.TYPE);
//            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
//            l_tradedProductParams.setTradedProductId(1006160060005L);
//            l_tradedProductParams.setProductId(2601133200000L);
//            l_tradedProductParams.setMarketId(2601L);
//            l_tradedProductParams.setInstitutionCode("0D");
//            TestDBUtility.insertWithDel(l_tradedProductParams);
//
//            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
//            EqtypeTradedProductParams l_eqtypeTradedProductParams =
//                TestDBUtility.getEqtypeTradedProductRow();
//            l_eqtypeTradedProductParams.setTradedProductId(1006160060005L);
//            l_eqtypeTradedProductParams.setProductId(2601133200000L);
//            l_eqtypeTradedProductParams.setMarketId(2601L);
//            l_eqtypeTradedProductParams.setValidUntilBizDate("20040917");
//            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
//            
//            TestDBUtility.deleteAll(MarketRow.TYPE);
//            MarketParams l_MarketParams = TestDBUtility.getMarketRow();
//            l_MarketParams.setMarketCode("1");
//            l_MarketParams.setMarketId(2601L);
//            TestDBUtility.insertWithDel(l_MarketParams);
//            
//            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
//            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
//            l_tradingTimeParams.setInstitutionCode("0D");
//            l_tradingTimeParams.setBranchCode("123");
//            l_tradingTimeParams.setMarketCode("N1");
//            l_tradingTimeParams.setTradingTimeType("01");
//            l_tradingTimeParams.setProductCode("0");
//            l_tradingTimeParams.setBizDateType("1");
//            l_tradingTimeParams.setBizdateCalcParameter("0");
//            TestDBUtility.insertWithDel(l_tradingTimeParams);
//             
//            WEB3EquityTradedProduct l_equityTradedProduct =
//                new WEB3EquityTradedProduct(l_tradedProductParams);
//            l_impl.notifyLimitRangeInfo(l_hostAttentionInfoNotifyParams, l_eqtypeTradedProductParams, null, null);
//            
//        }
//        catch (WEB3BusinessLayerException l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03146, l_ex.getErrorInfo());
//        }
//        catch (DataQueryException l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        catch (DataNetworkException l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        catch (WEB3BaseException l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        finally
//        {
//            try
//            {
//                EqtypeTradedProductRow l_listEqtypeTradedProductRow =
//                    EqtypeTradedProductDao.findRowByPk(1006160060005L);
//                
//                assertEquals(1000.0, l_listEqtypeTradedProductRow.getLastClosingPrice(), 1);
//                assertTrue(l_listEqtypeTradedProductRow.getHighCompulsivePriceRangeIsNull());
//                assertEquals(50, l_listEqtypeTradedProductRow.getLowCompulsivePriceRange(), 0);
//                assertEquals(null, l_listEqtypeTradedProductRow.getPriceRangeUnitType());
//                assertEquals(10.0, l_listEqtypeTradedProductRow.getBasePrice(), 1);
//                assertEquals("AXRY1", l_listEqtypeTradedProductRow.getLastUpdater());
//            }
//            catch (DataFindException e)
//            {
//                fail();
//            }
//            catch (DataQueryException e)
//            {
//                fail();
//            }
//            catch (DataNetworkException e)
//            {
//                fail();
//            }
//        }
//        log.exiting(STR_METHOD_NAME);
//    }
//    
//    //管理者更新済み項目Listの要素数が0件の場合、該当する全ての項目に対し更新を行なう
//    //注意情報通知キュー.制限値幅上限 - 注意情報通知キュー.基準値 > 0
//    //注意情報通知キュー.基準値 - 注意情報通知キュー.制限値幅下限 > 0
//    public void testNotifyLimitRangeInfo_C0016()
//    {
//        final String STR_METHOD_NAME = "testNotifyLimitRangeInfo_C0014";
//        log.entering(STR_METHOD_NAME);
//
//        try
//        {
//            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp",
//                 new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));
//            
//            ThreadLocalSystemAttributesRegistry.setAttribute("web3.attributes.basetimestampfororderbizdate",
//                 new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));
//            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
//
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
//                    "getSystemTimestamp",
//                    new Class[] {},
//                    new Timestamp(WEB3DateUtility.getDate("20040917", "yyyyMMdd").getTime()));  
//
//            TestDBUtility.deleteAll(HostAttentionInfoNotifyRow.TYPE);
//            HostAttentionInfoNotifyParams l_hostAttentionInfoNotifyParams =
//                new HostAttentionInfoNotifyParams();
//            l_hostAttentionInfoNotifyParams.setBasePrice(100);
//            l_hostAttentionInfoNotifyParams.setHighPriceRange(250);
//            l_hostAttentionInfoNotifyParams.setLowPriceRange(null);
//            l_hostAttentionInfoNotifyParams.setRequestCode("AXRY1");
//            TestDBUtility.insertWithDel(l_hostAttentionInfoNotifyParams);
//            
//            TestDBUtility.deleteAll(EqtypeProductConditionRow.TYPE);
//            
//            TestDBUtility.deleteAll(ProductRow.TYPE);
//            ProductParams l_ProductParams = TestDBUtility.getProductRow();
//            l_ProductParams.setProductId(2601133200000L);
//            TestDBUtility.insertWithDel(l_ProductParams);
//            
//            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
//            EqtypeProductParams l_EqtypeProductParams = TestDBUtility.getEqtypeProductRow();
//            l_EqtypeProductParams.setProductId(2601133200000L);
//            TestDBUtility.insertWithDel(l_EqtypeProductParams);
//            
//            TestDBUtility.deleteAll(TradedProductRow.TYPE);
//            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
//            l_tradedProductParams.setTradedProductId(1006160060005L);
//            l_tradedProductParams.setProductId(2601133200000L);
//            l_tradedProductParams.setMarketId(2601L);
//            l_tradedProductParams.setInstitutionCode("0D");
//            TestDBUtility.insertWithDel(l_tradedProductParams);
//
//            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
//            EqtypeTradedProductParams l_eqtypeTradedProductParams =
//                TestDBUtility.getEqtypeTradedProductRow();
//            l_eqtypeTradedProductParams.setTradedProductId(1006160060005L);
//            l_eqtypeTradedProductParams.setProductId(2601133200000L);
//            l_eqtypeTradedProductParams.setMarketId(2601L);
//            l_eqtypeTradedProductParams.setValidUntilBizDate("20040917");
//            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
//            
//            TestDBUtility.deleteAll(MarketRow.TYPE);
//            MarketParams l_MarketParams = TestDBUtility.getMarketRow();
//            l_MarketParams.setMarketCode("1");
//            l_MarketParams.setMarketId(2601L);
//            TestDBUtility.insertWithDel(l_MarketParams);
//            
//            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
//            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
//            l_tradingTimeParams.setInstitutionCode("0D");
//            l_tradingTimeParams.setBranchCode("123");
//            l_tradingTimeParams.setMarketCode("N1");
//            l_tradingTimeParams.setTradingTimeType("01");
//            l_tradingTimeParams.setProductCode("0");
//            l_tradingTimeParams.setBizDateType("1");
//            l_tradingTimeParams.setBizdateCalcParameter("0");
//            TestDBUtility.insertWithDel(l_tradingTimeParams);
//             
//            WEB3EquityTradedProduct l_equityTradedProduct =
//                new WEB3EquityTradedProduct(l_tradedProductParams);
//            l_impl.notifyLimitRangeInfo(l_hostAttentionInfoNotifyParams, l_eqtypeTradedProductParams, null, null);
//            
//            EqtypeTradedProductRow l_listEqtypeTradedProductRow = EqtypeTradedProductDao.findRowByPk(1006160060005L);
//            assertEquals(100, l_listEqtypeTradedProductRow.getLastClosingPrice(), 0);
//            assertEquals(150, l_listEqtypeTradedProductRow.getHighCompulsivePriceRange(), 0);
//            assertTrue(l_listEqtypeTradedProductRow.getLowCompulsivePriceRangeIsNull());
//            assertEquals(null, l_listEqtypeTradedProductRow.getPriceRangeUnitType());
//            assertEquals(100, l_listEqtypeTradedProductRow.getBasePrice(), 0);
//            assertEquals("AXRY1", l_listEqtypeTradedProductRow.getLastUpdater());
//        }
//        catch (DataQueryException l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        catch (DataNetworkException l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        catch (WEB3BaseException l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        log.exiting(STR_METHOD_NAME);
//    }
}
@
