head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.08.30;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminEquityAttentionInfoNotifyServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3AdminEquityAttentionInfoNotifyServiceImplTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2009/01/08 劉剣（中訊）新規作成
*/
package webbroker3.eqtypeadmin.service.delegate.stdimpls;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductUpdqParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductUpdqRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.eqtypeadmin.data.AttentionInfoHistoryParams;
import webbroker3.eqtypeadmin.data.AttentionInfoHistoryRow;
import webbroker3.eqtypeadmin.data.HostAttentionInfoNotifyParams;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityAttentionInfoNotifyRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityForcedSettleOrderApproveMainRequest;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityAttentionInfoNotifyServiceImpl.WEB3AdminEquityAttentionInfoNotifyTransactionCallback;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.ExtMailProcRow;
import webbroker3.gentrade.data.MailInfoParams;
import webbroker3.gentrade.data.MailInfoRow;
import webbroker3.gentrade.data.MailProcDao;
import webbroker3.gentrade.data.MailProcRow;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminEquityAttentionInfoNotifyServiceImplTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3AdminEquityAttentionInfoNotifyServiceImplTest.class);
    
    private WEB3AdminEquityAttentionInfoNotifyServiceImpl l_impl = null;
    
    public WEB3AdminEquityAttentionInfoNotifyServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        l_impl = new WEB3AdminEquityAttentionInfoNotifyServiceImpl();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /*
     * 送信メール区分 : 
     * 注意情報履歴テーブル.処理結果区分＝"エラー"の場合
     * 識別ID :  
     * 注意情報履歴テーブル.処理結果区分＝"エラー"の場合、"1" 
     * 株式銘柄≠nullの場合
     * process_result_div = 処理エラー
     */
    /*public void testInsertSendMail_C0000()
    {
        final String STR_METHOD_NAME = "testInsertSendMail_C0000()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setStandardName("xyz");
            TestDBUtility.insertWithDel(l_productParams);
            
            EqtypeProductParams l_eqtypeProductParams = new EqtypeProductParams();
            l_eqtypeProductParams.setProductId(3304148080000L);
            l_eqtypeProductParams.setInstitutionCode("0D");
            l_eqtypeProductParams.setProductCode("1001");
            l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeProductParams.setYearlyBooksClosingDate(WEB3DateUtility.getDate("20090108", "yyyyMMdd"));
            l_eqtypeProductParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_eqtypeProductParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_eqtypeProductParams.setStandardName("xyz111");
            WEB3EquityProduct l_equityProduct = new WEB3EquityProduct(l_eqtypeProductParams);
            
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            l_marketParams.setMarketCode("6K");
            l_marketParams.setMarketName("ksk");
            TestDBUtility.insertWithDel(l_marketParams);
            Market l_market = new WEB3GentradeMarket(3303L);
            
            AttentionInfoHistoryParams l_attentionInfoHistoryParams = new AttentionInfoHistoryParams();
            l_attentionInfoHistoryParams.setStandardName("myn");
            l_attentionInfoHistoryParams.setAttentionInfoHistoryId(1001);
            l_attentionInfoHistoryParams.setAttentionInfoType("1");//
            l_attentionInfoHistoryParams.setInstitutionCode("0D");
            l_attentionInfoHistoryParams.setMarketId(1002);
            l_attentionInfoHistoryParams.setValidUntilBizDate("30");
            l_attentionInfoHistoryParams.setAttentionInfoDivCode("A001");//
            l_attentionInfoHistoryParams.setInfoGenerationTimestamp(WEB3DateUtility.getDate("20090108", "yyyyMMdd"));
            l_attentionInfoHistoryParams.setTradeStopRestartTimestamp(WEB3DateUtility.getDate("20090208", "yyyyMMdd"));
            l_attentionInfoHistoryParams.setOrdReceiptRestartTimestamp(WEB3DateUtility.getDate("20090308", "yyyyMMdd"));
            
            l_attentionInfoHistoryParams.setOldEstimationPrice(11);
            l_attentionInfoHistoryParams.setNewEstimationPrice(12);
            l_attentionInfoHistoryParams.setOldLastClosingPrice(13);
            l_attentionInfoHistoryParams.setNewLastClosingPrice(14);
            l_attentionInfoHistoryParams.setOldBasePrice(15);
            l_attentionInfoHistoryParams.setNewBasePrice(16);
            
            l_attentionInfoHistoryParams.setOldPriceRangeType("a");
            l_attentionInfoHistoryParams.setNewPriceRangeType("b");
            l_attentionInfoHistoryParams.setOldPriceRangeUnitType("c");
            l_attentionInfoHistoryParams.setNewPriceRangeUnitType("d");
            
            l_attentionInfoHistoryParams.setOldHighCompPriceRange(17);
            l_attentionInfoHistoryParams.setNewHighPriceRange(18);
            l_attentionInfoHistoryParams.setOldLowCompPriceRange(9);
            l_attentionInfoHistoryParams.setNewLowPriceRange(19);
            
            l_attentionInfoHistoryParams.setOldLastClosingPriceUpdq(20);
            l_attentionInfoHistoryParams.setNewLastClosingPriceUpdq(21);
            
            l_attentionInfoHistoryParams.setOldBasePriceUpdq(22);
            l_attentionInfoHistoryParams.setNewBasePriceUpdq(23);
//            l_attentionInfoHistoryParams.setoldhighpricerange
            
            l_attentionInfoHistoryParams.setFreeFormatTitle("hgfdsa");
            l_attentionInfoHistoryParams.setFreeFormatText("qwerty");
            
            l_attentionInfoHistoryParams.setProcessResultDiv("9");
            l_attentionInfoHistoryParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_attentionInfoHistoryParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());


            TestDBUtility.deleteAll(MailInfoRow.TYPE);
            MailInfoParams l_mailInfoParams = new MailInfoParams();
            l_mailInfoParams.setInstitutionCode("0D");
            l_mailInfoParams.setSendmailDiv("3104");//送信メール区分
            l_mailInfoParams.setDiscernmentId("1");//識別ID
            l_mailInfoParams.setSubject("abc");
            l_mailInfoParams.setLastUpdater("007");
            l_mailInfoParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_mailInfoParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_mailInfoParams.setMailText(
                    "product_code = %%product_code%%" +
                    "product_name = %%product_name%%" +
                    "market_code = %%market_code%%" +
                    "market_name = %%market_name%%" +
                    "info_generation_timestamp = %%info_generation_timestamp%%" +
                    "trade_stop_restart_timestamp = %%trade_stop_restart_timestamp%%" +
                    "ord_receipt_restart_timestamp = %%ord_receipt_restart_timestamp%%" +
                    "old_estimation_price = %%old_estimation_price%%" +
                    "new_estimation_price = %%new_estimation_price%%" +
                    "old_last_closing_price = %%old_last_closing_price%%" +
                    "new_last_closing_price = %%new_last_closing_price%%" +
                    "old_base_price = %%old_base_price%%" +
                    "new_base_price = %%new_base_price%%" +
                    "old_price_range_type = %%old_price_range_type%%" +
                    "new_price_range_type = %%new_price_range_type%%" +
                    "old_price_range_unit_type = %%old_price_range_unit_type%%" +
                    "new_price_range_unit_type = %%new_price_range_unit_type%%" +
                    "old_high_price_range = %%old_high_price_range%%" +
                    "new_high_price_range = %%new_high_price_range%%" +
                    "old_low_price_range = %%old_low_price_range%%" +
                    "new_low_price_range = %%new_low_price_range%%" +
                    "old_last_closing_price_updq = %%old_last_closing_price_updq%%" +
                    "new_last_closing_price_updq = %%new_last_closing_price_updq%%" +
                    "old_base_price_updq = %%old_base_price_updq%%" +
                    "new_base_price_updq = %%new_base_price_updq%%" +
                    "title = %%title%%" +
                    "text = %%text%%" +
                    "process_result_div = %%process_result_div%%");
            TestDBUtility.insertWithDel(l_mailInfoParams);

            TestDBUtility.deleteAll(MailProcRow.TYPE);

            WEB3AdminEquityAttentionInfoNotifyTransactionCallback l_callback =
                l_impl.new WEB3AdminEquityAttentionInfoNotifyTransactionCallback();
            
            Method l_method = WEB3AdminEquityAttentionInfoNotifyTransactionCallback.class.getDeclaredMethod(
                    "insertSendMail",
                    new Class[]{WEB3EquityProduct.class, Market.class, AttentionInfoHistoryParams.class});
            l_method.setAccessible(true);
            Object[] l_obj = {l_equityProduct, l_market, l_attentionInfoHistoryParams};
            l_method.invoke(l_callback, l_obj);

            MailProcRow l_mailProcRow =
                MailProcDao.findRowByInstitutionCodeBranchCodeSendmailDivDiscernmentIdAccountCodeMailId(
                        "0D",
                        "000",
                        "3104",//
                        "1",//
                        "0000000",
                        1001);

            assertEquals("0", l_mailProcRow.getStatus());
            assertEquals(null, l_mailProcRow.getEmailAddress());
            assertEquals(null, l_mailProcRow.getSendEmailAddress());
            assertEquals("abc", l_mailProcRow.getSubject());
            assertEquals(
                    "product_code = 1001" +
                    "product_name = myn" +
                    "market_code = 6K" +
                    "market_name = ksk" +
                    "info_generation_timestamp = 2009/01/08 00:00:00" +
                    "trade_stop_restart_timestamp = 2009/02/08 00:00:00" +
                    "ord_receipt_restart_timestamp = 2009/03/08 00:00:00" +
                    "old_estimation_price = 11.0" +
                    "new_estimation_price = 12.0" +
                    "old_last_closing_price = 13.0" +
                    "new_last_closing_price = 14.0" +
                    "old_base_price = 15.0" +
                    "new_base_price = 16.0" +
                    "old_price_range_type = a" +
                    "new_price_range_type = b" +
                    "old_price_range_unit_type = c" +
                    "new_price_range_unit_type = d" +
                    "old_high_price_range = 32.0" +
                    "new_high_price_range = 18.0" +
                    "old_low_price_range = 6.0" +
                    "new_low_price_range = 19.0" +
                    "old_last_closing_price_updq = 20.0" +
                    "new_last_closing_price_updq = 21.0" +
                    "old_base_price_updq = 22.0" +
                    "new_base_price_updq = 23.0" +
                    "title = hgfdsa" +
                    "text = qwerty" +
                    "process_result_div = 処理エラー", l_mailProcRow.getMailText());
            assertEquals(BooleanEnum.FALSE, l_mailProcRow.getDeleteFlag());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }*/
    
    /*
     * 注意情報履歴テーブル.注意情報種別＝"売停情報"の場合
     * 注意情報履歴テーブル.注意情報区分コード＝"売買停止（注文受付可）"の場合
     * 株式銘柄≠nullの場合
     * process_result_div = データ更新済
     */
    /*public void testInsertSendMail_C0001()
    {
        final String STR_METHOD_NAME = "testInsertSendMail_C0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setStandardName("xyz");
            TestDBUtility.insertWithDel(l_productParams);
            
            EqtypeProductParams l_eqtypeProductParams = new EqtypeProductParams();
            l_eqtypeProductParams.setProductId(3304148080000L);
            l_eqtypeProductParams.setInstitutionCode("0D");
            l_eqtypeProductParams.setProductCode("1001");
            l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeProductParams.setYearlyBooksClosingDate(WEB3DateUtility.getDate("20090108", "yyyyMMdd"));
            l_eqtypeProductParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_eqtypeProductParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_eqtypeProductParams.setStandardName("xyz");
            WEB3EquityProduct l_equityProduct = new WEB3EquityProduct(l_eqtypeProductParams);
            
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_marketParams);
            Market l_market = new WEB3GentradeMarket(3303L);
            
            AttentionInfoHistoryParams l_attentionInfoHistoryParams = new AttentionInfoHistoryParams();
            l_attentionInfoHistoryParams.setAttentionInfoHistoryId(1001);
            l_attentionInfoHistoryParams.setAttentionInfoType("1");//
            l_attentionInfoHistoryParams.setInstitutionCode("0D");
            l_attentionInfoHistoryParams.setMarketId(1002);
            l_attentionInfoHistoryParams.setValidUntilBizDate("30");
            l_attentionInfoHistoryParams.setAttentionInfoDivCode("A001");//
            l_attentionInfoHistoryParams.setInfoGenerationTimestamp(WEB3DateUtility.getDate("20090108", "yyyyMMdd"));
            
            l_attentionInfoHistoryParams.setStandardName("myn");
            l_attentionInfoHistoryParams.setInfoGenerationTimestamp(WEB3DateUtility.getDate("20090108", "yyyyMMdd"));
            l_attentionInfoHistoryParams.setTradeStopRestartTimestamp(WEB3DateUtility.getDate("20090208", "yyyyMMdd"));
            l_attentionInfoHistoryParams.setOrdReceiptRestartTimestamp(WEB3DateUtility.getDate("20090308", "yyyyMMdd"));
            
            l_attentionInfoHistoryParams.setOldEstimationPrice(11);
            l_attentionInfoHistoryParams.setNewEstimationPrice(12);
            l_attentionInfoHistoryParams.setOldLastClosingPrice(13);
            l_attentionInfoHistoryParams.setNewLastClosingPrice(14);
            l_attentionInfoHistoryParams.setOldBasePrice(15);
            l_attentionInfoHistoryParams.setNewBasePrice(16);
            
            l_attentionInfoHistoryParams.setOldPriceRangeType("a");
            l_attentionInfoHistoryParams.setNewPriceRangeType("b");
            l_attentionInfoHistoryParams.setOldPriceRangeUnitType("c");
            l_attentionInfoHistoryParams.setNewPriceRangeUnitType("d");
            
            l_attentionInfoHistoryParams.setOldHighCompPriceRange(17);
            l_attentionInfoHistoryParams.setNewHighPriceRange(18);
            l_attentionInfoHistoryParams.setOldLowCompPriceRange(9);
            l_attentionInfoHistoryParams.setNewLowPriceRange(19);
            
            l_attentionInfoHistoryParams.setOldLastClosingPriceUpdq(20);
            l_attentionInfoHistoryParams.setNewLastClosingPriceUpdq(21);
            
            l_attentionInfoHistoryParams.setOldBasePriceUpdq(22);
            l_attentionInfoHistoryParams.setNewBasePriceUpdq(23);
            
            l_attentionInfoHistoryParams.setFreeFormatTitle("hgfdsa");
            l_attentionInfoHistoryParams.setFreeFormatText("qwerty");
            
            l_attentionInfoHistoryParams.setProcessResultDiv("1");
            l_attentionInfoHistoryParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_attentionInfoHistoryParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());


            TestDBUtility.deleteAll(MailInfoRow.TYPE);
            MailInfoParams l_mailInfoParams = new MailInfoParams();
            l_mailInfoParams.setInstitutionCode("0D");
            l_mailInfoParams.setSendmailDiv("3101");
            l_mailInfoParams.setDiscernmentId("1");
            l_mailInfoParams.setSubject("abc");
            l_mailInfoParams.setLastUpdater("007");
            l_mailInfoParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_mailInfoParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_mailInfoParams.setMailText(
                    "product_code = %%product_code%%" +
                    "product_name = %%product_name%%" +
                    "market_code = %%market_code%%" +
                    "market_name = %%market_name%%" +
                    "info_generation_timestamp = %%info_generation_timestamp%%" +
                    "trade_stop_restart_timestamp = %%trade_stop_restart_timestamp%%" +
                    "ord_receipt_restart_timestamp = %%ord_receipt_restart_timestamp%%" +
                    "old_estimation_price = %%old_estimation_price%%" +
                    "new_estimation_price = %%new_estimation_price%%" +
                    "old_last_closing_price = %%old_last_closing_price%%" +
                    "new_last_closing_price = %%new_last_closing_price%%" +
                    "old_base_price = %%old_base_price%%" +
                    "new_base_price = %%new_base_price%%" +
                    "old_price_range_type = %%old_price_range_type%%" +
                    "new_price_range_type = %%new_price_range_type%%" +
                    "old_price_range_unit_type = %%old_price_range_unit_type%%" +
                    "new_price_range_unit_type = %%new_price_range_unit_type%%" +
                    "old_high_price_range = %%old_high_price_range%%" +
                    "new_high_price_range = %%new_high_price_range%%" +
                    "old_low_price_range = %%old_low_price_range%%" +
                    "new_low_price_range = %%new_low_price_range%%" +
                    "old_last_closing_price_updq = %%old_last_closing_price_updq%%" +
                    "new_last_closing_price_updq = %%new_last_closing_price_updq%%" +
                    "old_base_price_updq = %%old_base_price_updq%%" +
                    "new_base_price_updq = %%new_base_price_updq%%" +
                    "title = %%title%%" +
                    "text = %%text%%" +
                    "process_result_div = %%process_result_div%%");
            TestDBUtility.insertWithDel(l_mailInfoParams);

            TestDBUtility.deleteAll(MailProcRow.TYPE);

            WEB3AdminEquityAttentionInfoNotifyTransactionCallback l_callback =
                l_impl.new WEB3AdminEquityAttentionInfoNotifyTransactionCallback();
            
            Method l_method = WEB3AdminEquityAttentionInfoNotifyTransactionCallback.class.getDeclaredMethod(
                    "insertSendMail",
                    new Class[]{WEB3EquityProduct.class, Market.class, AttentionInfoHistoryParams.class});
            l_method.setAccessible(true);
            Object[] l_obj = {l_equityProduct, l_market, l_attentionInfoHistoryParams};
            l_method.invoke(l_callback, l_obj);

            MailProcRow l_mailProcRow =
                MailProcDao.findRowByInstitutionCodeBranchCodeSendmailDivDiscernmentIdAccountCodeMailId(
                        "0D",
                        "000",
                        "3101",//
                        "1",//
                        "0000000",
                        1001);

            assertEquals("0", l_mailProcRow.getStatus());
            assertEquals(null, l_mailProcRow.getEmailAddress());
            assertEquals(null, l_mailProcRow.getSendEmailAddress());
            assertEquals("abc", l_mailProcRow.getSubject());
            assertEquals(
                    "product_code = 1001" +
                    "product_name = myn" +
                    "market_code = SP" +
                    "market_name = シンガポール" +
                    "info_generation_timestamp = 2009/01/08 00:00:00" +
                    "trade_stop_restart_timestamp = 2009/02/08 00:00:00" +
                    "ord_receipt_restart_timestamp = 2009/03/08 00:00:00" +
                    "old_estimation_price = 11.0" +
                    "new_estimation_price = 12.0" +
                    "old_last_closing_price = 13.0" +
                    "new_last_closing_price = 14.0" +
                    "old_base_price = 15.0" +
                    "new_base_price = 16.0" +
                    "old_price_range_type = a" +
                    "new_price_range_type = b" +
                    "old_price_range_unit_type = c" +
                    "new_price_range_unit_type = d" +
                    "old_high_price_range = 32.0" +
                    "new_high_price_range = 18.0" +
                    "old_low_price_range = 6.0" +
                    "new_low_price_range = 19.0" +
                    "old_last_closing_price_updq = 20.0" +
                    "new_last_closing_price_updq = 21.0" +
                    "old_base_price_updq = 22.0" +
                    "new_base_price_updq = 23.0" +
                    "title = hgfdsa" +
                    "text = qwerty" +
                    "process_result_div = データ更新済", l_mailProcRow.getMailText());
            assertEquals(BooleanEnum.FALSE, l_mailProcRow.getDeleteFlag());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }*/
    /*
     * 注意情報履歴テーブル.注意情報種別＝"制限値幅情報"の場合
     * 注意情報履歴テーブル.注意情報区分コード＝"売買停止（注文受付不可）"の場合
     * 株式銘柄≠nullの場合
     * 注意情報履歴テーブル.処理結果区分＝"正常（更新無）"の場合、"メール送信済" 
     */
    /*public void testInsertSendMail_C0002()
    {
        final String STR_METHOD_NAME = "testInsertSendMail_C0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setStandardName("xyz");
            TestDBUtility.insertWithDel(l_productParams);
            
            EqtypeProductParams l_eqtypeProductParams = new EqtypeProductParams();
            l_eqtypeProductParams.setProductId(3304148080000L);
            l_eqtypeProductParams.setInstitutionCode("0D");
            l_eqtypeProductParams.setProductCode("1001");
            l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeProductParams.setYearlyBooksClosingDate(WEB3DateUtility.getDate("20090108", "yyyyMMdd"));
            l_eqtypeProductParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_eqtypeProductParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_eqtypeProductParams.setStandardName("xyz");
            WEB3EquityProduct l_equityProduct = new WEB3EquityProduct(l_eqtypeProductParams);
            
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_marketParams);
            Market l_market = new WEB3GentradeMarket(3303L);
            
            AttentionInfoHistoryParams l_attentionInfoHistoryParams = new AttentionInfoHistoryParams();
            l_attentionInfoHistoryParams.setAttentionInfoHistoryId(1001);
            l_attentionInfoHistoryParams.setAttentionInfoType("2");//
            l_attentionInfoHistoryParams.setInstitutionCode("0D");
            l_attentionInfoHistoryParams.setMarketId(1002);
            l_attentionInfoHistoryParams.setValidUntilBizDate("30");
            l_attentionInfoHistoryParams.setAttentionInfoDivCode("A002");//
            
            l_attentionInfoHistoryParams.setStandardName("myn");
            l_attentionInfoHistoryParams.setInfoGenerationTimestamp(WEB3DateUtility.getDate("20090108", "yyyyMMdd"));
            l_attentionInfoHistoryParams.setTradeStopRestartTimestamp(WEB3DateUtility.getDate("20090208", "yyyyMMdd"));
            l_attentionInfoHistoryParams.setOrdReceiptRestartTimestamp(WEB3DateUtility.getDate("20090308", "yyyyMMdd"));
            
            l_attentionInfoHistoryParams.setOldEstimationPrice(11);
            l_attentionInfoHistoryParams.setNewEstimationPrice(12);
            l_attentionInfoHistoryParams.setOldLastClosingPrice(13);
            l_attentionInfoHistoryParams.setNewLastClosingPrice(14);
            l_attentionInfoHistoryParams.setOldBasePrice(15);
            l_attentionInfoHistoryParams.setNewBasePrice(16);
            
            l_attentionInfoHistoryParams.setOldPriceRangeType("a");
            l_attentionInfoHistoryParams.setNewPriceRangeType("b");
            l_attentionInfoHistoryParams.setOldPriceRangeUnitType("c");
            l_attentionInfoHistoryParams.setNewPriceRangeUnitType("d");
            
            l_attentionInfoHistoryParams.setOldHighCompPriceRange(17);
            l_attentionInfoHistoryParams.setNewHighPriceRange(18);
            l_attentionInfoHistoryParams.setOldLowCompPriceRange(9);
            l_attentionInfoHistoryParams.setNewLowPriceRange(19);
            
            l_attentionInfoHistoryParams.setOldLastClosingPriceUpdq(20);
            l_attentionInfoHistoryParams.setNewLastClosingPriceUpdq(21);
            
            l_attentionInfoHistoryParams.setOldBasePriceUpdq(22);
            l_attentionInfoHistoryParams.setNewBasePriceUpdq(23);
            
            l_attentionInfoHistoryParams.setFreeFormatTitle("hgfdsa");
            l_attentionInfoHistoryParams.setFreeFormatText("qwerty");
            
            l_attentionInfoHistoryParams.setProcessResultDiv("2");//
            l_attentionInfoHistoryParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_attentionInfoHistoryParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());


            TestDBUtility.deleteAll(MailInfoRow.TYPE);
            MailInfoParams l_mailInfoParams = new MailInfoParams();
            l_mailInfoParams.setInstitutionCode("0D");
            l_mailInfoParams.setSendmailDiv("3102");
            l_mailInfoParams.setDiscernmentId("2");
            l_mailInfoParams.setSubject("abc");
            l_mailInfoParams.setLastUpdater("007");
            l_mailInfoParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_mailInfoParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_mailInfoParams.setMailText(
                    "product_code = %%product_code%%" +
                    "product_name = %%product_name%%" +
                    "market_code = %%market_code%%" +
                    "market_name = %%market_name%%" +
                    "info_generation_timestamp = %%info_generation_timestamp%%" +
                    "trade_stop_restart_timestamp = %%trade_stop_restart_timestamp%%" +
                    "ord_receipt_restart_timestamp = %%ord_receipt_restart_timestamp%%" +
                    "old_estimation_price = %%old_estimation_price%%" +
                    "new_estimation_price = %%new_estimation_price%%" +
                    "old_last_closing_price = %%old_last_closing_price%%" +
                    "new_last_closing_price = %%new_last_closing_price%%" +
                    "old_base_price = %%old_base_price%%" +
                    "new_base_price = %%new_base_price%%" +
                    "old_price_range_type = %%old_price_range_type%%" +
                    "new_price_range_type = %%new_price_range_type%%" +
                    "old_price_range_unit_type = %%old_price_range_unit_type%%" +
                    "new_price_range_unit_type = %%new_price_range_unit_type%%" +
                    "old_high_price_range = %%old_high_price_range%%" +
                    "new_high_price_range = %%new_high_price_range%%" +
                    "old_low_price_range = %%old_low_price_range%%" +
                    "new_low_price_range = %%new_low_price_range%%" +
                    "old_last_closing_price_updq = %%old_last_closing_price_updq%%" +
                    "new_last_closing_price_updq = %%new_last_closing_price_updq%%" +
                    "old_base_price_updq = %%old_base_price_updq%%" +
                    "new_base_price_updq = %%new_base_price_updq%%" +
                    "title = %%title%%" +
                    "text = %%text%%" +
                    "process_result_div = %%process_result_div%%");
            TestDBUtility.insertWithDel(l_mailInfoParams);

            TestDBUtility.deleteAll(MailProcRow.TYPE);

            WEB3AdminEquityAttentionInfoNotifyTransactionCallback l_callback =
                l_impl.new WEB3AdminEquityAttentionInfoNotifyTransactionCallback();
            
            Method l_method = WEB3AdminEquityAttentionInfoNotifyTransactionCallback.class.getDeclaredMethod(
                    "insertSendMail",
                    new Class[]{WEB3EquityProduct.class, Market.class, AttentionInfoHistoryParams.class});
            l_method.setAccessible(true);
            Object[] l_obj = {l_equityProduct, l_market, l_attentionInfoHistoryParams};
            l_method.invoke(l_callback, l_obj);

            MailProcRow l_mailProcRow =
                MailProcDao.findRowByInstitutionCodeBranchCodeSendmailDivDiscernmentIdAccountCodeMailId(
                        "0D",
                        "000",
                        "3102",//
                        "2",//
                        "0000000",
                        1001);

            assertEquals("0", l_mailProcRow.getStatus());
            assertEquals(null, l_mailProcRow.getEmailAddress());
            assertEquals(null, l_mailProcRow.getSendEmailAddress());
            assertEquals("abc", l_mailProcRow.getSubject());
            assertEquals(
                    "product_code = 1001" +
                    "product_name = myn" +
                    "market_code = SP" +
                    "market_name = シンガポール" +
                    "info_generation_timestamp = 2009/01/08 00:00:00" +
                    "trade_stop_restart_timestamp = 2009/02/08 00:00:00" +
                    "ord_receipt_restart_timestamp = 2009/03/08 00:00:00" +
                    "old_estimation_price = 11.0" +
                    "new_estimation_price = 12.0" +
                    "old_last_closing_price = 13.0" +
                    "new_last_closing_price = 14.0" +
                    "old_base_price = 15.0" +
                    "new_base_price = 16.0" +
                    "old_price_range_type = a" +
                    "new_price_range_type = b" +
                    "old_price_range_unit_type = c" +
                    "new_price_range_unit_type = d" +
                    "old_high_price_range = 32.0" +
                    "new_high_price_range = 18.0" +
                    "old_low_price_range = 6.0" +
                    "new_low_price_range = 19.0" +
                    "old_last_closing_price_updq = 20.0" +
                    "new_last_closing_price_updq = 21.0" +
                    "old_base_price_updq = 22.0" +
                    "new_base_price_updq = 23.0" +
                    "title = hgfdsa" +
                    "text = qwerty" +
                    "process_result_div = メール送信済", l_mailProcRow.getMailText());
            assertEquals(BooleanEnum.FALSE, l_mailProcRow.getDeleteFlag());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }*/
    /*
     * 注意情報履歴テーブル.注意情報種別＝"フリーフォーマット"の場合
     * 注意情報履歴テーブル.注意情報区分コード＝"売買停止（注文受付可）の取消"の場合
     * 株式銘柄≠nullの場合
     */
    /*public void testInsertSendMail_C0003()
    {
        final String STR_METHOD_NAME = "testInsertSendMail_C0003()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setStandardName("xyz");
            TestDBUtility.insertWithDel(l_productParams);
            
            EqtypeProductParams l_eqtypeProductParams = new EqtypeProductParams();
            l_eqtypeProductParams.setProductId(3304148080000L);
            l_eqtypeProductParams.setInstitutionCode("0D");
            l_eqtypeProductParams.setProductCode("1001");
            l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeProductParams.setYearlyBooksClosingDate(WEB3DateUtility.getDate("20090108", "yyyyMMdd"));
            l_eqtypeProductParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_eqtypeProductParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_eqtypeProductParams.setStandardName("xyz");
            WEB3EquityProduct l_equityProduct = new WEB3EquityProduct(l_eqtypeProductParams);
            
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_marketParams);
            Market l_market = new WEB3GentradeMarket(3303L);
            
            AttentionInfoHistoryParams l_attentionInfoHistoryParams = new AttentionInfoHistoryParams();
            l_attentionInfoHistoryParams.setAttentionInfoHistoryId(1001);
            l_attentionInfoHistoryParams.setAttentionInfoType("3");//
            l_attentionInfoHistoryParams.setInstitutionCode("0D");
            l_attentionInfoHistoryParams.setMarketId(1002);
            l_attentionInfoHistoryParams.setValidUntilBizDate("30");
            l_attentionInfoHistoryParams.setAttentionInfoDivCode("A003");//
            
            l_attentionInfoHistoryParams.setStandardName("myn");
            l_attentionInfoHistoryParams.setInfoGenerationTimestamp(WEB3DateUtility.getDate("20090108", "yyyyMMdd"));
            l_attentionInfoHistoryParams.setTradeStopRestartTimestamp(WEB3DateUtility.getDate("20090208", "yyyyMMdd"));
            l_attentionInfoHistoryParams.setOrdReceiptRestartTimestamp(WEB3DateUtility.getDate("20090308", "yyyyMMdd"));
            
            l_attentionInfoHistoryParams.setOldEstimationPrice(11);
            l_attentionInfoHistoryParams.setNewEstimationPrice(12);
            l_attentionInfoHistoryParams.setOldLastClosingPrice(13);
            l_attentionInfoHistoryParams.setNewLastClosingPrice(14);
            l_attentionInfoHistoryParams.setOldBasePrice(15);
            l_attentionInfoHistoryParams.setNewBasePrice(16);
            
            l_attentionInfoHistoryParams.setOldPriceRangeType("a");
            l_attentionInfoHistoryParams.setNewPriceRangeType("b");
            l_attentionInfoHistoryParams.setOldPriceRangeUnitType("c");
            l_attentionInfoHistoryParams.setNewPriceRangeUnitType("d");
            
            l_attentionInfoHistoryParams.setOldHighCompPriceRange(17);
            l_attentionInfoHistoryParams.setNewHighPriceRange(18);
            l_attentionInfoHistoryParams.setOldLowCompPriceRange(9);
            l_attentionInfoHistoryParams.setNewLowPriceRange(19);
            
            l_attentionInfoHistoryParams.setOldLastClosingPriceUpdq(20);
            l_attentionInfoHistoryParams.setNewLastClosingPriceUpdq(21);
            
            l_attentionInfoHistoryParams.setOldBasePriceUpdq(22);
            l_attentionInfoHistoryParams.setNewBasePriceUpdq(23);
            
            l_attentionInfoHistoryParams.setFreeFormatTitle("hgfdsa");
            l_attentionInfoHistoryParams.setFreeFormatText("qwerty");
            
            l_attentionInfoHistoryParams.setProcessResultDiv("2");//
            l_attentionInfoHistoryParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_attentionInfoHistoryParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());


            TestDBUtility.deleteAll(MailInfoRow.TYPE);
            MailInfoParams l_mailInfoParams = new MailInfoParams();
            l_mailInfoParams.setInstitutionCode("0D");
            l_mailInfoParams.setSendmailDiv("3103");//
            l_mailInfoParams.setDiscernmentId("3");//
            l_mailInfoParams.setSubject("abc");
            l_mailInfoParams.setLastUpdater("007");
            l_mailInfoParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_mailInfoParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_mailInfoParams.setMailText(
                    "product_code = %%product_code%%" +
                    "product_name = %%product_name%%" +
                    "market_code = %%market_code%%" +
                    "market_name = %%market_name%%" +
                    "info_generation_timestamp = %%info_generation_timestamp%%" +
                    "trade_stop_restart_timestamp = %%trade_stop_restart_timestamp%%" +
                    "ord_receipt_restart_timestamp = %%ord_receipt_restart_timestamp%%" +
                    "old_estimation_price = %%old_estimation_price%%" +
                    "new_estimation_price = %%new_estimation_price%%" +
                    "old_last_closing_price = %%old_last_closing_price%%" +
                    "new_last_closing_price = %%new_last_closing_price%%" +
                    "old_base_price = %%old_base_price%%" +
                    "new_base_price = %%new_base_price%%" +
                    "old_price_range_type = %%old_price_range_type%%" +
                    "new_price_range_type = %%new_price_range_type%%" +
                    "old_price_range_unit_type = %%old_price_range_unit_type%%" +
                    "new_price_range_unit_type = %%new_price_range_unit_type%%" +
                    "old_high_price_range = %%old_high_price_range%%" +
                    "new_high_price_range = %%new_high_price_range%%" +
                    "old_low_price_range = %%old_low_price_range%%" +
                    "new_low_price_range = %%new_low_price_range%%" +
                    "old_last_closing_price_updq = %%old_last_closing_price_updq%%" +
                    "new_last_closing_price_updq = %%new_last_closing_price_updq%%" +
                    "old_base_price_updq = %%old_base_price_updq%%" +
                    "new_base_price_updq = %%new_base_price_updq%%" +
                    "title = %%title%%" +
                    "text = %%text%%" +
                    "process_result_div = %%process_result_div%%");
            TestDBUtility.insertWithDel(l_mailInfoParams);

            TestDBUtility.deleteAll(MailProcRow.TYPE);

            WEB3AdminEquityAttentionInfoNotifyTransactionCallback l_callback =
                l_impl.new WEB3AdminEquityAttentionInfoNotifyTransactionCallback();
            
            Method l_method = WEB3AdminEquityAttentionInfoNotifyTransactionCallback.class.getDeclaredMethod(
                    "insertSendMail",
                    new Class[]{WEB3EquityProduct.class, Market.class, AttentionInfoHistoryParams.class});
            l_method.setAccessible(true);
            Object[] l_obj = {l_equityProduct, l_market, l_attentionInfoHistoryParams};
            l_method.invoke(l_callback, l_obj);

            MailProcRow l_mailProcRow =
                MailProcDao.findRowByInstitutionCodeBranchCodeSendmailDivDiscernmentIdAccountCodeMailId(
                        "0D",
                        "000",
                        "3103",//
                        "3",//
                        "0000000",
                        1001);

            assertEquals("0", l_mailProcRow.getStatus());
            assertEquals(null, l_mailProcRow.getEmailAddress());
            assertEquals(null, l_mailProcRow.getSendEmailAddress());
            assertEquals("abc", l_mailProcRow.getSubject());
            assertEquals(
                    "product_code = 1001" +
                    "product_name = myn" +
                    "market_code = SP" +
                    "market_name = シンガポール" +
                    "info_generation_timestamp = 2009/01/08 00:00:00" +
                    "trade_stop_restart_timestamp = 2009/02/08 00:00:00" +
                    "ord_receipt_restart_timestamp = 2009/03/08 00:00:00" +
                    "old_estimation_price = 11.0" +
                    "new_estimation_price = 12.0" +
                    "old_last_closing_price = 13.0" +
                    "new_last_closing_price = 14.0" +
                    "old_base_price = 15.0" +
                    "new_base_price = 16.0" +
                    "old_price_range_type = a" +
                    "new_price_range_type = b" +
                    "old_price_range_unit_type = c" +
                    "new_price_range_unit_type = d" +
                    "old_high_price_range = 32.0" +
                    "new_high_price_range = 18.0" +
                    "old_low_price_range = 6.0" +
                    "new_low_price_range = 19.0" +
                    "old_last_closing_price_updq = 20.0" +
                    "new_last_closing_price_updq = 21.0" +
                    "old_base_price_updq = 22.0" +
                    "new_base_price_updq = 23.0" +
                    "title = hgfdsa" +
                    "text = qwerty" +
                    "process_result_div = メール送信済", l_mailProcRow.getMailText());
            assertEquals(BooleanEnum.FALSE, l_mailProcRow.getDeleteFlag());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }*/
    /*
     * 注意情報履歴テーブル.注意情報種別＝"フリーフォーマット"の場合
     * 注意情報履歴テーブル.注意情報区分コード＝"売買停止（注文受付不可）の取消"の場合
     * 株式銘柄≠nullの場合
     */
    /*public void testInsertSendMail_C0004()
    {
        final String STR_METHOD_NAME = "testInsertSendMail_C0004()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setStandardName("xyz");
            TestDBUtility.insertWithDel(l_productParams);
            
            EqtypeProductParams l_eqtypeProductParams = new EqtypeProductParams();
            l_eqtypeProductParams.setProductId(3304148080000L);
            l_eqtypeProductParams.setInstitutionCode("0D");
            l_eqtypeProductParams.setProductCode("1001");
            l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeProductParams.setYearlyBooksClosingDate(WEB3DateUtility.getDate("20090108", "yyyyMMdd"));
            l_eqtypeProductParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_eqtypeProductParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_eqtypeProductParams.setStandardName("xyz");
            WEB3EquityProduct l_equityProduct = new WEB3EquityProduct(l_eqtypeProductParams);
            
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_marketParams);
            Market l_market = new WEB3GentradeMarket(3303L);
            
            AttentionInfoHistoryParams l_attentionInfoHistoryParams = new AttentionInfoHistoryParams();
            l_attentionInfoHistoryParams.setAttentionInfoHistoryId(1001);
            l_attentionInfoHistoryParams.setAttentionInfoType("3");//
            l_attentionInfoHistoryParams.setInstitutionCode("0D");
            l_attentionInfoHistoryParams.setMarketId(1002);
            l_attentionInfoHistoryParams.setValidUntilBizDate("30");
            l_attentionInfoHistoryParams.setAttentionInfoDivCode("A004");//
            l_attentionInfoHistoryParams.setStandardName("myn");
            l_attentionInfoHistoryParams.setInfoGenerationTimestamp(WEB3DateUtility.getDate("20090108", "yyyyMMdd"));
            l_attentionInfoHistoryParams.setTradeStopRestartTimestamp(WEB3DateUtility.getDate("20090208", "yyyyMMdd"));
            l_attentionInfoHistoryParams.setOrdReceiptRestartTimestamp(WEB3DateUtility.getDate("20090308", "yyyyMMdd"));
            
            l_attentionInfoHistoryParams.setOldEstimationPrice(11);
            l_attentionInfoHistoryParams.setNewEstimationPrice(12);
            l_attentionInfoHistoryParams.setOldLastClosingPrice(13);
            l_attentionInfoHistoryParams.setNewLastClosingPrice(14);
            l_attentionInfoHistoryParams.setOldBasePrice(15);
            l_attentionInfoHistoryParams.setNewBasePrice(16);
            
            l_attentionInfoHistoryParams.setOldPriceRangeType("a");
            l_attentionInfoHistoryParams.setNewPriceRangeType("b");
            l_attentionInfoHistoryParams.setOldPriceRangeUnitType("c");
            l_attentionInfoHistoryParams.setNewPriceRangeUnitType("d");
            
            l_attentionInfoHistoryParams.setOldHighCompPriceRange(17);
            l_attentionInfoHistoryParams.setNewHighPriceRange(18);
            l_attentionInfoHistoryParams.setOldLowCompPriceRange(9);
            l_attentionInfoHistoryParams.setNewLowPriceRange(19);
            
            l_attentionInfoHistoryParams.setOldLastClosingPriceUpdq(20);
            l_attentionInfoHistoryParams.setNewLastClosingPriceUpdq(21);
            
            l_attentionInfoHistoryParams.setOldBasePriceUpdq(22);
            l_attentionInfoHistoryParams.setNewBasePriceUpdq(23);
            
            l_attentionInfoHistoryParams.setFreeFormatTitle("hgfdsa");
            l_attentionInfoHistoryParams.setFreeFormatText("qwerty");
            
            l_attentionInfoHistoryParams.setProcessResultDiv("2");//
            l_attentionInfoHistoryParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_attentionInfoHistoryParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());


            TestDBUtility.deleteAll(MailInfoRow.TYPE);
            MailInfoParams l_mailInfoParams = new MailInfoParams();
            l_mailInfoParams.setInstitutionCode("0D");
            l_mailInfoParams.setSendmailDiv("3103");//
            l_mailInfoParams.setDiscernmentId("4");//
            l_mailInfoParams.setSubject("abc");
            l_mailInfoParams.setLastUpdater("007");
            l_mailInfoParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_mailInfoParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_mailInfoParams.setMailText(
                    "product_code = %%product_code%%" +
                    "product_name = %%product_name%%" +
                    "market_code = %%market_code%%" +
                    "market_name = %%market_name%%" +
                    "info_generation_timestamp = %%info_generation_timestamp%%" +
                    "trade_stop_restart_timestamp = %%trade_stop_restart_timestamp%%" +
                    "ord_receipt_restart_timestamp = %%ord_receipt_restart_timestamp%%" +
                    "old_estimation_price = %%old_estimation_price%%" +
                    "new_estimation_price = %%new_estimation_price%%" +
                    "old_last_closing_price = %%old_last_closing_price%%" +
                    "new_last_closing_price = %%new_last_closing_price%%" +
                    "old_base_price = %%old_base_price%%" +
                    "new_base_price = %%new_base_price%%" +
                    "old_price_range_type = %%old_price_range_type%%" +
                    "new_price_range_type = %%new_price_range_type%%" +
                    "old_price_range_unit_type = %%old_price_range_unit_type%%" +
                    "new_price_range_unit_type = %%new_price_range_unit_type%%" +
                    "old_high_price_range = %%old_high_price_range%%" +
                    "new_high_price_range = %%new_high_price_range%%" +
                    "old_low_price_range = %%old_low_price_range%%" +
                    "new_low_price_range = %%new_low_price_range%%" +
                    "old_last_closing_price_updq = %%old_last_closing_price_updq%%" +
                    "new_last_closing_price_updq = %%new_last_closing_price_updq%%" +
                    "old_base_price_updq = %%old_base_price_updq%%" +
                    "new_base_price_updq = %%new_base_price_updq%%" +
                    "title = %%title%%" +
                    "text = %%text%%" +
                    "process_result_div = %%process_result_div%%");
            TestDBUtility.insertWithDel(l_mailInfoParams);

            TestDBUtility.deleteAll(MailProcRow.TYPE);

            WEB3AdminEquityAttentionInfoNotifyTransactionCallback l_callback =
                l_impl.new WEB3AdminEquityAttentionInfoNotifyTransactionCallback();
            
            Method l_method = WEB3AdminEquityAttentionInfoNotifyTransactionCallback.class.getDeclaredMethod(
                    "insertSendMail",
                    new Class[]{WEB3EquityProduct.class, Market.class, AttentionInfoHistoryParams.class});
            l_method.setAccessible(true);
            Object[] l_obj = {l_equityProduct, l_market, l_attentionInfoHistoryParams};
            l_method.invoke(l_callback, l_obj);

            MailProcRow l_mailProcRow =
                MailProcDao.findRowByInstitutionCodeBranchCodeSendmailDivDiscernmentIdAccountCodeMailId(
                        "0D",
                        "000",
                        "3103",//
                        "4",//
                        "0000000",
                        1001);

            assertEquals("0", l_mailProcRow.getStatus());
            assertEquals(null, l_mailProcRow.getEmailAddress());
            assertEquals(null, l_mailProcRow.getSendEmailAddress());
            assertEquals("abc", l_mailProcRow.getSubject());
            assertEquals(
                    "product_code = 1001" +
                    "product_name = myn" +
                    "market_code = SP" +
                    "market_name = シンガポール" +
                    "info_generation_timestamp = 2009/01/08 00:00:00" +
                    "trade_stop_restart_timestamp = 2009/02/08 00:00:00" +
                    "ord_receipt_restart_timestamp = 2009/03/08 00:00:00" +
                    "old_estimation_price = 11.0" +
                    "new_estimation_price = 12.0" +
                    "old_last_closing_price = 13.0" +
                    "new_last_closing_price = 14.0" +
                    "old_base_price = 15.0" +
                    "new_base_price = 16.0" +
                    "old_price_range_type = a" +
                    "new_price_range_type = b" +
                    "old_price_range_unit_type = c" +
                    "new_price_range_unit_type = d" +
                    "old_high_price_range = 32.0" +
                    "new_high_price_range = 18.0" +
                    "old_low_price_range = 6.0" +
                    "new_low_price_range = 19.0" +
                    "old_last_closing_price_updq = 20.0" +
                    "new_last_closing_price_updq = 21.0" +
                    "old_base_price_updq = 22.0" +
                    "new_base_price_updq = 23.0" +
                    "title = hgfdsa" +
                    "text = qwerty" +
                    "process_result_div = メール送信済", l_mailProcRow.getMailText());
            assertEquals(BooleanEnum.FALSE, l_mailProcRow.getDeleteFlag());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }*/
    /*
     * 注意情報履歴テーブル.注意情報種別＝"フリーフォーマット"の場合
     * 注意情報履歴テーブル.注意情報区分コード＝"売買停止（注文受付可）の解除"の場合
     * 株式銘柄=nullの場合
     */
    /*public void testInsertSendMail_C0005()
    {
        final String STR_METHOD_NAME = "testInsertSendMail_C0005()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setStandardName("xyz");
            TestDBUtility.insertWithDel(l_productParams);
            
            EqtypeProductParams l_eqtypeProductParams = new EqtypeProductParams();
            l_eqtypeProductParams.setProductId(3304148080000L);
            l_eqtypeProductParams.setInstitutionCode("0D");
            l_eqtypeProductParams.setProductCode("1001");
            l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeProductParams.setYearlyBooksClosingDate(WEB3DateUtility.getDate("20090108", "yyyyMMdd"));
            l_eqtypeProductParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_eqtypeProductParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_eqtypeProductParams.setStandardName("xyz");
//            WEB3EquityProduct l_equityProduct = new WEB3EquityProduct(l_eqtypeProductParams);
            
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_marketParams);
            Market l_market = new WEB3GentradeMarket(3303L);
            
            AttentionInfoHistoryParams l_attentionInfoHistoryParams = new AttentionInfoHistoryParams();
            l_attentionInfoHistoryParams.setAttentionInfoHistoryId(1001);
            l_attentionInfoHistoryParams.setAttentionInfoType("3");//
            l_attentionInfoHistoryParams.setInstitutionCode("0D");
            l_attentionInfoHistoryParams.setMarketId(1002);
            l_attentionInfoHistoryParams.setValidUntilBizDate("30");
            l_attentionInfoHistoryParams.setAttentionInfoDivCode("A005");//
            
            l_attentionInfoHistoryParams.setStandardName("myn");
            l_attentionInfoHistoryParams.setInfoGenerationTimestamp(WEB3DateUtility.getDate("20090108", "yyyyMMdd"));
            l_attentionInfoHistoryParams.setTradeStopRestartTimestamp(WEB3DateUtility.getDate("20090208", "yyyyMMdd"));
            l_attentionInfoHistoryParams.setOrdReceiptRestartTimestamp(WEB3DateUtility.getDate("20090308", "yyyyMMdd"));
            
            l_attentionInfoHistoryParams.setOldEstimationPrice(11);
            l_attentionInfoHistoryParams.setNewEstimationPrice(12);
            l_attentionInfoHistoryParams.setOldLastClosingPrice(13);
            l_attentionInfoHistoryParams.setNewLastClosingPrice(14);
            l_attentionInfoHistoryParams.setOldBasePrice(15);
            l_attentionInfoHistoryParams.setNewBasePrice(16);
            
            l_attentionInfoHistoryParams.setOldPriceRangeType("a");
            l_attentionInfoHistoryParams.setNewPriceRangeType("b");
            l_attentionInfoHistoryParams.setOldPriceRangeUnitType("c");
            l_attentionInfoHistoryParams.setNewPriceRangeUnitType("d");
            
            l_attentionInfoHistoryParams.setOldHighCompPriceRange(17);
            l_attentionInfoHistoryParams.setNewHighPriceRange(18);
            l_attentionInfoHistoryParams.setOldLowCompPriceRange(9);
            l_attentionInfoHistoryParams.setNewLowPriceRange(19);
            
            l_attentionInfoHistoryParams.setOldLastClosingPriceUpdq(20);
            l_attentionInfoHistoryParams.setNewLastClosingPriceUpdq(21);
            
            l_attentionInfoHistoryParams.setOldBasePriceUpdq(22);
            l_attentionInfoHistoryParams.setNewBasePriceUpdq(23);
            
            l_attentionInfoHistoryParams.setFreeFormatTitle("hgfdsa");
            l_attentionInfoHistoryParams.setFreeFormatText("qwerty");
            
            l_attentionInfoHistoryParams.setProcessResultDiv("2");//
            
            l_attentionInfoHistoryParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_attentionInfoHistoryParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());


            TestDBUtility.deleteAll(MailInfoRow.TYPE);
            MailInfoParams l_mailInfoParams = new MailInfoParams();
            l_mailInfoParams.setInstitutionCode("0D");
            l_mailInfoParams.setSendmailDiv("3103");//
            l_mailInfoParams.setDiscernmentId("5");//
            l_mailInfoParams.setSubject("abc");
            l_mailInfoParams.setLastUpdater("007");
            l_mailInfoParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_mailInfoParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_mailInfoParams.setMailText(
                    "product_code = %%product_code%%" +
                    "product_name = %%product_name%%" +
                    "market_code = %%market_code%%" +
                    "market_name = %%market_name%%" +
                    "info_generation_timestamp = %%info_generation_timestamp%%" +
                    "trade_stop_restart_timestamp = %%trade_stop_restart_timestamp%%" +
                    "ord_receipt_restart_timestamp = %%ord_receipt_restart_timestamp%%" +
                    "old_estimation_price = %%old_estimation_price%%" +
                    "new_estimation_price = %%new_estimation_price%%" +
                    "old_last_closing_price = %%old_last_closing_price%%" +
                    "new_last_closing_price = %%new_last_closing_price%%" +
                    "old_base_price = %%old_base_price%%" +
                    "new_base_price = %%new_base_price%%" +
                    "old_price_range_type = %%old_price_range_type%%" +
                    "new_price_range_type = %%new_price_range_type%%" +
                    "old_price_range_unit_type = %%old_price_range_unit_type%%" +
                    "new_price_range_unit_type = %%new_price_range_unit_type%%" +
                    "old_high_price_range = %%old_high_price_range%%" +
                    "new_high_price_range = %%new_high_price_range%%" +
                    "old_low_price_range = %%old_low_price_range%%" +
                    "new_low_price_range = %%new_low_price_range%%" +
                    "old_last_closing_price_updq = %%old_last_closing_price_updq%%" +
                    "new_last_closing_price_updq = %%new_last_closing_price_updq%%" +
                    "old_base_price_updq = %%old_base_price_updq%%" +
                    "new_base_price_updq = %%new_base_price_updq%%" +
                    "title = %%title%%" +
                    "text = %%text%%" +
                    "process_result_div = %%process_result_div%%");
            TestDBUtility.insertWithDel(l_mailInfoParams);

            TestDBUtility.deleteAll(MailProcRow.TYPE);

            WEB3AdminEquityAttentionInfoNotifyTransactionCallback l_callback =
                l_impl.new WEB3AdminEquityAttentionInfoNotifyTransactionCallback();
            
            Method l_method = WEB3AdminEquityAttentionInfoNotifyTransactionCallback.class.getDeclaredMethod(
                    "insertSendMail",
                    new Class[]{WEB3EquityProduct.class, Market.class, AttentionInfoHistoryParams.class});
            l_method.setAccessible(true);
            Object[] l_obj = {null, l_market, l_attentionInfoHistoryParams};
            l_method.invoke(l_callback, l_obj);

            MailProcRow l_mailProcRow =
                MailProcDao.findRowByInstitutionCodeBranchCodeSendmailDivDiscernmentIdAccountCodeMailId(
                        "0D",
                        "000",
                        "3103",//
                        "5",//
                        "0000000",
                        1001);

            assertEquals("0", l_mailProcRow.getStatus());
            assertEquals(null, l_mailProcRow.getEmailAddress());
            assertEquals(null, l_mailProcRow.getSendEmailAddress());
            assertEquals("abc", l_mailProcRow.getSubject());
            assertEquals(
                    "product_code = " +
                    "product_name = myn" +
                    "market_code = SP" +
                    "market_name = シンガポール" +
                    "info_generation_timestamp = 2009/01/08 00:00:00" +
                    "trade_stop_restart_timestamp = 2009/02/08 00:00:00" +
                    "ord_receipt_restart_timestamp = 2009/03/08 00:00:00" +
                    "old_estimation_price = 11.0" +
                    "new_estimation_price = 12.0" +
                    "old_last_closing_price = 13.0" +
                    "new_last_closing_price = 14.0" +
                    "old_base_price = 15.0" +
                    "new_base_price = 16.0" +
                    "old_price_range_type = a" +
                    "new_price_range_type = b" +
                    "old_price_range_unit_type = c" +
                    "new_price_range_unit_type = d" +
                    "old_high_price_range = 32.0" +
                    "new_high_price_range = 18.0" +
                    "old_low_price_range = 6.0" +
                    "new_low_price_range = 19.0" +
                    "old_last_closing_price_updq = 20.0" +
                    "new_last_closing_price_updq = 21.0" +
                    "old_base_price_updq = 22.0" +
                    "new_base_price_updq = 23.0" +
                    "title = hgfdsa" +
                    "text = qwerty" +
                    "process_result_div = メール送信済", l_mailProcRow.getMailText());
            assertEquals(BooleanEnum.FALSE, l_mailProcRow.getDeleteFlag());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }*/
    /*
     * 注意情報履歴テーブル.注意情報種別＝"フリーフォーマット"の場合
     * 注意情報履歴テーブル.注意情報区分コード＝"売買停止（注文受付不可）の解除"の場合
     * 株式銘柄=nullの場合
     */
    /*public void testInsertSendMail_C0006()
    {
        final String STR_METHOD_NAME = "testInsertSendMail_C0006()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setStandardName("xyz");
            TestDBUtility.insertWithDel(l_productParams);
            
            EqtypeProductParams l_eqtypeProductParams = new EqtypeProductParams();
            l_eqtypeProductParams.setProductId(3304148080000L);
            l_eqtypeProductParams.setInstitutionCode("0D");
            l_eqtypeProductParams.setProductCode("1001");
            l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeProductParams.setYearlyBooksClosingDate(WEB3DateUtility.getDate("20090108", "yyyyMMdd"));
            l_eqtypeProductParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_eqtypeProductParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_eqtypeProductParams.setStandardName("xyz");
//            WEB3EquityProduct l_equityProduct = new WEB3EquityProduct(l_eqtypeProductParams);
            
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_marketParams);
            Market l_market = new WEB3GentradeMarket(3303L);
            
            AttentionInfoHistoryParams l_attentionInfoHistoryParams = new AttentionInfoHistoryParams();
            l_attentionInfoHistoryParams.setAttentionInfoHistoryId(1001);
            l_attentionInfoHistoryParams.setAttentionInfoType("3");//
            l_attentionInfoHistoryParams.setInstitutionCode("0D");
            l_attentionInfoHistoryParams.setMarketId(1002);
            l_attentionInfoHistoryParams.setValidUntilBizDate("30");
            l_attentionInfoHistoryParams.setAttentionInfoDivCode("A006");//
            l_attentionInfoHistoryParams.setStandardName("myn");
            l_attentionInfoHistoryParams.setInfoGenerationTimestamp(WEB3DateUtility.getDate("20090108", "yyyyMMdd"));
            l_attentionInfoHistoryParams.setTradeStopRestartTimestamp(WEB3DateUtility.getDate("20090208", "yyyyMMdd"));
            l_attentionInfoHistoryParams.setOrdReceiptRestartTimestamp(WEB3DateUtility.getDate("20090308", "yyyyMMdd"));
            
            l_attentionInfoHistoryParams.setOldEstimationPrice(11);
            l_attentionInfoHistoryParams.setNewEstimationPrice(12);
            l_attentionInfoHistoryParams.setOldLastClosingPrice(13);
            l_attentionInfoHistoryParams.setNewLastClosingPrice(14);
            l_attentionInfoHistoryParams.setOldBasePrice(15);
            l_attentionInfoHistoryParams.setNewBasePrice(16);
            
            l_attentionInfoHistoryParams.setOldPriceRangeType("a");
            l_attentionInfoHistoryParams.setNewPriceRangeType("b");
            l_attentionInfoHistoryParams.setOldPriceRangeUnitType("c");
            l_attentionInfoHistoryParams.setNewPriceRangeUnitType("d");
            
            l_attentionInfoHistoryParams.setOldHighCompPriceRange(17);
            l_attentionInfoHistoryParams.setNewHighPriceRange(18);
            l_attentionInfoHistoryParams.setOldLowCompPriceRange(9);
            l_attentionInfoHistoryParams.setNewLowPriceRange(19);
            
            l_attentionInfoHistoryParams.setOldLastClosingPriceUpdq(20);
            l_attentionInfoHistoryParams.setNewLastClosingPriceUpdq(21);
            
            l_attentionInfoHistoryParams.setOldBasePriceUpdq(22);
            l_attentionInfoHistoryParams.setNewBasePriceUpdq(23);
            
            l_attentionInfoHistoryParams.setFreeFormatTitle("hgfdsa");
            l_attentionInfoHistoryParams.setFreeFormatText("qwerty");
            
            l_attentionInfoHistoryParams.setProcessResultDiv("2");//
            l_attentionInfoHistoryParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_attentionInfoHistoryParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());


            TestDBUtility.deleteAll(MailInfoRow.TYPE);
            MailInfoParams l_mailInfoParams = new MailInfoParams();
            l_mailInfoParams.setInstitutionCode("0D");
            l_mailInfoParams.setSendmailDiv("3103");//
            l_mailInfoParams.setDiscernmentId("6");//
            l_mailInfoParams.setSubject("abc");
            l_mailInfoParams.setLastUpdater("007");
            l_mailInfoParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_mailInfoParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_mailInfoParams.setMailText(
                    "product_code = %%product_code%%" +
                    "product_name = %%product_name%%" +
                    "market_code = %%market_code%%" +
                    "market_name = %%market_name%%" +
                    "info_generation_timestamp = %%info_generation_timestamp%%" +
                    "trade_stop_restart_timestamp = %%trade_stop_restart_timestamp%%" +
                    "ord_receipt_restart_timestamp = %%ord_receipt_restart_timestamp%%" +
                    "old_estimation_price = %%old_estimation_price%%" +
                    "new_estimation_price = %%new_estimation_price%%" +
                    "old_last_closing_price = %%old_last_closing_price%%" +
                    "new_last_closing_price = %%new_last_closing_price%%" +
                    "old_base_price = %%old_base_price%%" +
                    "new_base_price = %%new_base_price%%" +
                    "old_price_range_type = %%old_price_range_type%%" +
                    "new_price_range_type = %%new_price_range_type%%" +
                    "old_price_range_unit_type = %%old_price_range_unit_type%%" +
                    "new_price_range_unit_type = %%new_price_range_unit_type%%" +
                    "old_high_price_range = %%old_high_price_range%%" +
                    "new_high_price_range = %%new_high_price_range%%" +
                    "old_low_price_range = %%old_low_price_range%%" +
                    "new_low_price_range = %%new_low_price_range%%" +
                    "old_last_closing_price_updq = %%old_last_closing_price_updq%%" +
                    "new_last_closing_price_updq = %%new_last_closing_price_updq%%" +
                    "old_base_price_updq = %%old_base_price_updq%%" +
                    "new_base_price_updq = %%new_base_price_updq%%" +
                    "title = %%title%%" +
                    "text = %%text%%" +
                    "process_result_div = %%process_result_div%%");
            TestDBUtility.insertWithDel(l_mailInfoParams);

            TestDBUtility.deleteAll(MailProcRow.TYPE);

            WEB3AdminEquityAttentionInfoNotifyTransactionCallback l_callback =
                l_impl.new WEB3AdminEquityAttentionInfoNotifyTransactionCallback();
            
            Method l_method = WEB3AdminEquityAttentionInfoNotifyTransactionCallback.class.getDeclaredMethod(
                    "insertSendMail",
                    new Class[]{WEB3EquityProduct.class, Market.class, AttentionInfoHistoryParams.class});
            l_method.setAccessible(true);
            Object[] l_obj = {null, l_market, l_attentionInfoHistoryParams};
            l_method.invoke(l_callback, l_obj);

            MailProcRow l_mailProcRow =
                MailProcDao.findRowByInstitutionCodeBranchCodeSendmailDivDiscernmentIdAccountCodeMailId(
                        "0D",
                        "000",
                        "3103",//
                        "6",//
                        "0000000",
                        1001);

            assertEquals("0", l_mailProcRow.getStatus());
            assertEquals(null, l_mailProcRow.getEmailAddress());
            assertEquals(null, l_mailProcRow.getSendEmailAddress());
            assertEquals("abc", l_mailProcRow.getSubject());
            assertEquals(
                    "product_code = " +
                    "product_name = myn" +
                    "market_code = SP" +
                    "market_name = シンガポール" +
                    "info_generation_timestamp = 2009/01/08 00:00:00" +
                    "trade_stop_restart_timestamp = 2009/02/08 00:00:00" +
                    "ord_receipt_restart_timestamp = 2009/03/08 00:00:00" +
                    "old_estimation_price = 11.0" +
                    "new_estimation_price = 12.0" +
                    "old_last_closing_price = 13.0" +
                    "new_last_closing_price = 14.0" +
                    "old_base_price = 15.0" +
                    "new_base_price = 16.0" +
                    "old_price_range_type = a" +
                    "new_price_range_type = b" +
                    "old_price_range_unit_type = c" +
                    "new_price_range_unit_type = d" +
                    "old_high_price_range = 32.0" +
                    "new_high_price_range = 18.0" +
                    "old_low_price_range = 6.0" +
                    "new_low_price_range = 19.0" +
                    "old_last_closing_price_updq = 20.0" +
                    "new_last_closing_price_updq = 21.0" +
                    "old_base_price_updq = 22.0" +
                    "new_base_price_updq = 23.0" +
                    "title = hgfdsa" +
                    "text = qwerty" +
                    "process_result_div = メール送信済", l_mailProcRow.getMailText());
            assertEquals(BooleanEnum.FALSE, l_mailProcRow.getDeleteFlag());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }*/
    /*
     * 注意情報履歴テーブル.注意情報種別＝"フリーフォーマット"の場合
     * 注意情報履歴テーブル.注意情報区分コード＝"売買停止（注文受付可）の解除の取消"の場合
     * 株式銘柄=nullの場合
     */
    /*public void testInsertSendMail_C0007()
    {
        final String STR_METHOD_NAME = "testInsertSendMail_C0007()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setStandardName("xyz");
            TestDBUtility.insertWithDel(l_productParams);
            
            EqtypeProductParams l_eqtypeProductParams = new EqtypeProductParams();
            l_eqtypeProductParams.setProductId(3304148080000L);
            l_eqtypeProductParams.setInstitutionCode("0D");
            l_eqtypeProductParams.setProductCode("1001");
            l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeProductParams.setYearlyBooksClosingDate(WEB3DateUtility.getDate("20090108", "yyyyMMdd"));
            l_eqtypeProductParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_eqtypeProductParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_eqtypeProductParams.setStandardName("xyz");
//            WEB3EquityProduct l_equityProduct = new WEB3EquityProduct(l_eqtypeProductParams);
            
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_marketParams);
            Market l_market = new WEB3GentradeMarket(3303L);
            
            AttentionInfoHistoryParams l_attentionInfoHistoryParams = new AttentionInfoHistoryParams();
            l_attentionInfoHistoryParams.setAttentionInfoHistoryId(1001);
            l_attentionInfoHistoryParams.setAttentionInfoType("3");//
            l_attentionInfoHistoryParams.setInstitutionCode("0D");
            l_attentionInfoHistoryParams.setMarketId(1002);
            l_attentionInfoHistoryParams.setValidUntilBizDate("30");
            l_attentionInfoHistoryParams.setAttentionInfoDivCode("A007");//
            l_attentionInfoHistoryParams.setStandardName("myn");
            l_attentionInfoHistoryParams.setInfoGenerationTimestamp(WEB3DateUtility.getDate("20090108", "yyyyMMdd"));
            l_attentionInfoHistoryParams.setTradeStopRestartTimestamp(WEB3DateUtility.getDate("20090208", "yyyyMMdd"));
            l_attentionInfoHistoryParams.setOrdReceiptRestartTimestamp(WEB3DateUtility.getDate("20090308", "yyyyMMdd"));
            
            l_attentionInfoHistoryParams.setOldEstimationPrice(11);
            l_attentionInfoHistoryParams.setNewEstimationPrice(12);
            l_attentionInfoHistoryParams.setOldLastClosingPrice(13);
            l_attentionInfoHistoryParams.setNewLastClosingPrice(14);
            l_attentionInfoHistoryParams.setOldBasePrice(15);
            l_attentionInfoHistoryParams.setNewBasePrice(16);
            
            l_attentionInfoHistoryParams.setOldPriceRangeType("a");
            l_attentionInfoHistoryParams.setNewPriceRangeType("b");
            l_attentionInfoHistoryParams.setOldPriceRangeUnitType("c");
            l_attentionInfoHistoryParams.setNewPriceRangeUnitType("d");
            
            l_attentionInfoHistoryParams.setOldHighCompPriceRange(17);
            l_attentionInfoHistoryParams.setNewHighPriceRange(18);
            l_attentionInfoHistoryParams.setOldLowCompPriceRange(9);
            l_attentionInfoHistoryParams.setNewLowPriceRange(19);
            
            l_attentionInfoHistoryParams.setOldLastClosingPriceUpdq(20);
            l_attentionInfoHistoryParams.setNewLastClosingPriceUpdq(21);
            
            l_attentionInfoHistoryParams.setOldBasePriceUpdq(22);
            l_attentionInfoHistoryParams.setNewBasePriceUpdq(23);
            
            l_attentionInfoHistoryParams.setFreeFormatTitle("hgfdsa");
            l_attentionInfoHistoryParams.setFreeFormatText("qwerty");
            
            l_attentionInfoHistoryParams.setProcessResultDiv("2");//
            l_attentionInfoHistoryParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_attentionInfoHistoryParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());


            TestDBUtility.deleteAll(MailInfoRow.TYPE);
            MailInfoParams l_mailInfoParams = new MailInfoParams();
            l_mailInfoParams.setInstitutionCode("0D");
            l_mailInfoParams.setSendmailDiv("3103");//
            l_mailInfoParams.setDiscernmentId("7");//
            l_mailInfoParams.setSubject("abc");
            l_mailInfoParams.setLastUpdater("007");
            l_mailInfoParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_mailInfoParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_mailInfoParams.setMailText(
                    "product_code = %%product_code%%" +
                    "product_name = %%product_name%%" +
                    "market_code = %%market_code%%" +
                    "market_name = %%market_name%%" +
                    "info_generation_timestamp = %%info_generation_timestamp%%" +
                    "trade_stop_restart_timestamp = %%trade_stop_restart_timestamp%%" +
                    "ord_receipt_restart_timestamp = %%ord_receipt_restart_timestamp%%" +
                    "old_estimation_price = %%old_estimation_price%%" +
                    "new_estimation_price = %%new_estimation_price%%" +
                    "old_last_closing_price = %%old_last_closing_price%%" +
                    "new_last_closing_price = %%new_last_closing_price%%" +
                    "old_base_price = %%old_base_price%%" +
                    "new_base_price = %%new_base_price%%" +
                    "old_price_range_type = %%old_price_range_type%%" +
                    "new_price_range_type = %%new_price_range_type%%" +
                    "old_price_range_unit_type = %%old_price_range_unit_type%%" +
                    "new_price_range_unit_type = %%new_price_range_unit_type%%" +
                    "old_high_price_range = %%old_high_price_range%%" +
                    "new_high_price_range = %%new_high_price_range%%" +
                    "old_low_price_range = %%old_low_price_range%%" +
                    "new_low_price_range = %%new_low_price_range%%" +
                    "old_last_closing_price_updq = %%old_last_closing_price_updq%%" +
                    "new_last_closing_price_updq = %%new_last_closing_price_updq%%" +
                    "old_base_price_updq = %%old_base_price_updq%%" +
                    "new_base_price_updq = %%new_base_price_updq%%" +
                    "title = %%title%%" +
                    "text = %%text%%" +
                    "process_result_div = %%process_result_div%%");
            TestDBUtility.insertWithDel(l_mailInfoParams);

            TestDBUtility.deleteAll(MailProcRow.TYPE);

            WEB3AdminEquityAttentionInfoNotifyTransactionCallback l_callback =
                l_impl.new WEB3AdminEquityAttentionInfoNotifyTransactionCallback();
            
            Method l_method = WEB3AdminEquityAttentionInfoNotifyTransactionCallback.class.getDeclaredMethod(
                    "insertSendMail",
                    new Class[]{WEB3EquityProduct.class, Market.class, AttentionInfoHistoryParams.class});
            l_method.setAccessible(true);
            Object[] l_obj = {null, l_market, l_attentionInfoHistoryParams};
            l_method.invoke(l_callback, l_obj);

            MailProcRow l_mailProcRow =
                MailProcDao.findRowByInstitutionCodeBranchCodeSendmailDivDiscernmentIdAccountCodeMailId(
                        "0D",
                        "000",
                        "3103",//
                        "7",//
                        "0000000",
                        1001);

            assertEquals("0", l_mailProcRow.getStatus());
            assertEquals(null, l_mailProcRow.getEmailAddress());
            assertEquals(null, l_mailProcRow.getSendEmailAddress());
            assertEquals("abc", l_mailProcRow.getSubject());
            assertEquals(
                    "product_code = " +
                    "product_name = myn" +
                    "market_code = SP" +
                    "market_name = シンガポール" +
                    "info_generation_timestamp = 2009/01/08 00:00:00" +
                    "trade_stop_restart_timestamp = 2009/02/08 00:00:00" +
                    "ord_receipt_restart_timestamp = 2009/03/08 00:00:00" +
                    "old_estimation_price = 11.0" +
                    "new_estimation_price = 12.0" +
                    "old_last_closing_price = 13.0" +
                    "new_last_closing_price = 14.0" +
                    "old_base_price = 15.0" +
                    "new_base_price = 16.0" +
                    "old_price_range_type = a" +
                    "new_price_range_type = b" +
                    "old_price_range_unit_type = c" +
                    "new_price_range_unit_type = d" +
                    "old_high_price_range = 32.0" +
                    "new_high_price_range = 18.0" +
                    "old_low_price_range = 6.0" +
                    "new_low_price_range = 19.0" +
                    "old_last_closing_price_updq = 20.0" +
                    "new_last_closing_price_updq = 21.0" +
                    "old_base_price_updq = 22.0" +
                    "new_base_price_updq = 23.0" +
                    "title = hgfdsa" +
                    "text = qwerty" +
                    "process_result_div = メール送信済", l_mailProcRow.getMailText());
            assertEquals(BooleanEnum.FALSE, l_mailProcRow.getDeleteFlag());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }*/
    /*
     * 注意情報履歴テーブル.注意情報種別＝"フリーフォーマット"の場合
     * 注意情報履歴テーブル.注意情報区分コード＝"売買停止（注文受付不可）の解除の取消"の場合
     * 株式銘柄=nullの場合
     */
    /*public void testInsertSendMail_C0008()
    {
        final String STR_METHOD_NAME = "testInsertSendMail_C0008()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setStandardName("xyz");
            TestDBUtility.insertWithDel(l_productParams);
            
            EqtypeProductParams l_eqtypeProductParams = new EqtypeProductParams();
            l_eqtypeProductParams.setProductId(3304148080000L);
            l_eqtypeProductParams.setInstitutionCode("0D");
            l_eqtypeProductParams.setProductCode("1001");
            l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeProductParams.setYearlyBooksClosingDate(WEB3DateUtility.getDate("20090108", "yyyyMMdd"));
            l_eqtypeProductParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_eqtypeProductParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_eqtypeProductParams.setStandardName("xyz");
//            WEB3EquityProduct l_equityProduct = new WEB3EquityProduct(l_eqtypeProductParams);
            
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_marketParams);
            Market l_market = new WEB3GentradeMarket(3303L);
            
            AttentionInfoHistoryParams l_attentionInfoHistoryParams = new AttentionInfoHistoryParams();
            l_attentionInfoHistoryParams.setAttentionInfoHistoryId(1001);
            l_attentionInfoHistoryParams.setAttentionInfoType("3");//
            l_attentionInfoHistoryParams.setInstitutionCode("0D");
            l_attentionInfoHistoryParams.setMarketId(1002);
            l_attentionInfoHistoryParams.setValidUntilBizDate("30");
            l_attentionInfoHistoryParams.setAttentionInfoDivCode("A008");//
            
            l_attentionInfoHistoryParams.setStandardName("myn");
            l_attentionInfoHistoryParams.setInfoGenerationTimestamp(WEB3DateUtility.getDate("20090108", "yyyyMMdd"));
            l_attentionInfoHistoryParams.setTradeStopRestartTimestamp(WEB3DateUtility.getDate("20090208", "yyyyMMdd"));
            l_attentionInfoHistoryParams.setOrdReceiptRestartTimestamp(WEB3DateUtility.getDate("20090308", "yyyyMMdd"));
            
            l_attentionInfoHistoryParams.setOldEstimationPrice(11);
            l_attentionInfoHistoryParams.setNewEstimationPrice(12);
            l_attentionInfoHistoryParams.setOldLastClosingPrice(13);
            l_attentionInfoHistoryParams.setNewLastClosingPrice(14);
            l_attentionInfoHistoryParams.setOldBasePrice(15);
            l_attentionInfoHistoryParams.setNewBasePrice(16);
            
            l_attentionInfoHistoryParams.setOldPriceRangeType("a");
            l_attentionInfoHistoryParams.setNewPriceRangeType("b");
            l_attentionInfoHistoryParams.setOldPriceRangeUnitType("c");
            l_attentionInfoHistoryParams.setNewPriceRangeUnitType("d");
            
            l_attentionInfoHistoryParams.setOldHighCompPriceRange(17);
            l_attentionInfoHistoryParams.setNewHighPriceRange(18);
            l_attentionInfoHistoryParams.setOldLowCompPriceRange(9);
            l_attentionInfoHistoryParams.setNewLowPriceRange(19);
            
            l_attentionInfoHistoryParams.setOldLastClosingPriceUpdq(20);
            l_attentionInfoHistoryParams.setNewLastClosingPriceUpdq(21);
            
            l_attentionInfoHistoryParams.setOldBasePriceUpdq(22);
            l_attentionInfoHistoryParams.setNewBasePriceUpdq(23);
            
            l_attentionInfoHistoryParams.setFreeFormatTitle("hgfdsa");
            l_attentionInfoHistoryParams.setFreeFormatText("qwerty");
            
            l_attentionInfoHistoryParams.setProcessResultDiv("2");//
            
            l_attentionInfoHistoryParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_attentionInfoHistoryParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());


            TestDBUtility.deleteAll(MailInfoRow.TYPE);
            MailInfoParams l_mailInfoParams = new MailInfoParams();
            l_mailInfoParams.setInstitutionCode("0D");
            l_mailInfoParams.setSendmailDiv("3103");//
            l_mailInfoParams.setDiscernmentId("8");//
            l_mailInfoParams.setSubject("abc");
            l_mailInfoParams.setLastUpdater("007");
            l_mailInfoParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_mailInfoParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_mailInfoParams.setMailText(
                    "product_code = %%product_code%%" +
                    "product_name = %%product_name%%" +
                    "market_code = %%market_code%%" +
                    "market_name = %%market_name%%" +
                    "info_generation_timestamp = %%info_generation_timestamp%%" +
                    "trade_stop_restart_timestamp = %%trade_stop_restart_timestamp%%" +
                    "ord_receipt_restart_timestamp = %%ord_receipt_restart_timestamp%%" +
                    "old_estimation_price = %%old_estimation_price%%" +
                    "new_estimation_price = %%new_estimation_price%%" +
                    "old_last_closing_price = %%old_last_closing_price%%" +
                    "new_last_closing_price = %%new_last_closing_price%%" +
                    "old_base_price = %%old_base_price%%" +
                    "new_base_price = %%new_base_price%%" +
                    "old_price_range_type = %%old_price_range_type%%" +
                    "new_price_range_type = %%new_price_range_type%%" +
                    "old_price_range_unit_type = %%old_price_range_unit_type%%" +
                    "new_price_range_unit_type = %%new_price_range_unit_type%%" +
                    "old_high_price_range = %%old_high_price_range%%" +
                    "new_high_price_range = %%new_high_price_range%%" +
                    "old_low_price_range = %%old_low_price_range%%" +
                    "new_low_price_range = %%new_low_price_range%%" +
                    "old_last_closing_price_updq = %%old_last_closing_price_updq%%" +
                    "new_last_closing_price_updq = %%new_last_closing_price_updq%%" +
                    "old_base_price_updq = %%old_base_price_updq%%" +
                    "new_base_price_updq = %%new_base_price_updq%%" +
                    "title = %%title%%" +
                    "text = %%text%%" +
                    "process_result_div = %%process_result_div%%");
            TestDBUtility.insertWithDel(l_mailInfoParams);

            TestDBUtility.deleteAll(MailProcRow.TYPE);

            WEB3AdminEquityAttentionInfoNotifyTransactionCallback l_callback =
                l_impl.new WEB3AdminEquityAttentionInfoNotifyTransactionCallback();
            
            Method l_method = WEB3AdminEquityAttentionInfoNotifyTransactionCallback.class.getDeclaredMethod(
                    "insertSendMail",
                    new Class[]{WEB3EquityProduct.class, Market.class, AttentionInfoHistoryParams.class});
            l_method.setAccessible(true);
            Object[] l_obj = {null, l_market, l_attentionInfoHistoryParams};
            l_method.invoke(l_callback, l_obj);

            MailProcRow l_mailProcRow =
                MailProcDao.findRowByInstitutionCodeBranchCodeSendmailDivDiscernmentIdAccountCodeMailId(
                        "0D",
                        "000",
                        "3103",//
                        "8",//
                        "0000000",
                        1001);

            assertEquals("0", l_mailProcRow.getStatus());
            assertEquals(null, l_mailProcRow.getEmailAddress());
            assertEquals(null, l_mailProcRow.getSendEmailAddress());
            assertEquals("abc", l_mailProcRow.getSubject());
            assertEquals(
                    "product_code = " +
                    "product_name = myn" +
                    "market_code = SP" +
                    "market_name = シンガポール" +
                    "info_generation_timestamp = 2009/01/08 00:00:00" +
                    "trade_stop_restart_timestamp = 2009/02/08 00:00:00" +
                    "ord_receipt_restart_timestamp = 2009/03/08 00:00:00" +
                    "old_estimation_price = 11.0" +
                    "new_estimation_price = 12.0" +
                    "old_last_closing_price = 13.0" +
                    "new_last_closing_price = 14.0" +
                    "old_base_price = 15.0" +
                    "new_base_price = 16.0" +
                    "old_price_range_type = a" +
                    "new_price_range_type = b" +
                    "old_price_range_unit_type = c" +
                    "new_price_range_unit_type = d" +
                    "old_high_price_range = 32.0" +
                    "new_high_price_range = 18.0" +
                    "old_low_price_range = 6.0" +
                    "new_low_price_range = 19.0" +
                    "old_last_closing_price_updq = 20.0" +
                    "new_last_closing_price_updq = 21.0" +
                    "old_base_price_updq = 22.0" +
                    "new_base_price_updq = 23.0" +
                    "title = hgfdsa" +
                    "text = qwerty" +
                    "process_result_div = メール送信済", l_mailProcRow.getMailText());
            assertEquals(BooleanEnum.FALSE, l_mailProcRow.getDeleteFlag());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }*/
    /*
     * 注意情報履歴テーブル.注意情報種別＝"フリーフォーマット"の場合
     * 注意情報履歴テーブル.注意情報区分コード＝"売買中断"の場合
     * 株式銘柄=nullの場合
     */
    /*public void testInsertSendMail_C0009()
    {
        final String STR_METHOD_NAME = "testInsertSendMail_C0009()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setStandardName("xyz");
            TestDBUtility.insertWithDel(l_productParams);
            
            EqtypeProductParams l_eqtypeProductParams = new EqtypeProductParams();
            l_eqtypeProductParams.setProductId(3304148080000L);
            l_eqtypeProductParams.setInstitutionCode("0D");
            l_eqtypeProductParams.setProductCode("1001");
            l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeProductParams.setYearlyBooksClosingDate(WEB3DateUtility.getDate("20090108", "yyyyMMdd"));
            l_eqtypeProductParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_eqtypeProductParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_eqtypeProductParams.setStandardName("xyz");
//            WEB3EquityProduct l_equityProduct = new WEB3EquityProduct(l_eqtypeProductParams);
            
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_marketParams);
            Market l_market = new WEB3GentradeMarket(3303L);
            
            AttentionInfoHistoryParams l_attentionInfoHistoryParams = new AttentionInfoHistoryParams();
            l_attentionInfoHistoryParams.setAttentionInfoHistoryId(1001);
            l_attentionInfoHistoryParams.setAttentionInfoType("3");//
            l_attentionInfoHistoryParams.setInstitutionCode("0D");
            l_attentionInfoHistoryParams.setMarketId(1002);
            l_attentionInfoHistoryParams.setValidUntilBizDate("30");
            l_attentionInfoHistoryParams.setAttentionInfoDivCode("A011");//
            l_attentionInfoHistoryParams.setStandardName("myn");
            l_attentionInfoHistoryParams.setInfoGenerationTimestamp(WEB3DateUtility.getDate("20090108", "yyyyMMdd"));
            l_attentionInfoHistoryParams.setTradeStopRestartTimestamp(WEB3DateUtility.getDate("20090208", "yyyyMMdd"));
            l_attentionInfoHistoryParams.setOrdReceiptRestartTimestamp(WEB3DateUtility.getDate("20090308", "yyyyMMdd"));
            
            l_attentionInfoHistoryParams.setOldEstimationPrice(11);
            l_attentionInfoHistoryParams.setNewEstimationPrice(12);
            l_attentionInfoHistoryParams.setOldLastClosingPrice(13);
            l_attentionInfoHistoryParams.setNewLastClosingPrice(14);
            l_attentionInfoHistoryParams.setOldBasePrice(15);
            l_attentionInfoHistoryParams.setNewBasePrice(16);
            
            l_attentionInfoHistoryParams.setOldPriceRangeType("a");
            l_attentionInfoHistoryParams.setNewPriceRangeType("b");
            l_attentionInfoHistoryParams.setOldPriceRangeUnitType("c");
            l_attentionInfoHistoryParams.setNewPriceRangeUnitType("d");
            
            l_attentionInfoHistoryParams.setOldHighCompPriceRange(17);
            l_attentionInfoHistoryParams.setNewHighPriceRange(18);
            l_attentionInfoHistoryParams.setOldLowCompPriceRange(9);
            l_attentionInfoHistoryParams.setNewLowPriceRange(19);
            
            l_attentionInfoHistoryParams.setOldLastClosingPriceUpdq(20);
            l_attentionInfoHistoryParams.setNewLastClosingPriceUpdq(21);
            
            l_attentionInfoHistoryParams.setOldBasePriceUpdq(22);
            l_attentionInfoHistoryParams.setNewBasePriceUpdq(23);
            
            l_attentionInfoHistoryParams.setFreeFormatTitle("hgfdsa");
            l_attentionInfoHistoryParams.setFreeFormatText("qwerty");
            
            l_attentionInfoHistoryParams.setProcessResultDiv("2");//
            
            l_attentionInfoHistoryParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_attentionInfoHistoryParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());


            TestDBUtility.deleteAll(MailInfoRow.TYPE);
            MailInfoParams l_mailInfoParams = new MailInfoParams();
            l_mailInfoParams.setInstitutionCode("0D");
            l_mailInfoParams.setSendmailDiv("3103");//
            l_mailInfoParams.setDiscernmentId("9");//
            l_mailInfoParams.setSubject("abc");
            l_mailInfoParams.setLastUpdater("007");
            l_mailInfoParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_mailInfoParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_mailInfoParams.setMailText(
                    "product_code = %%product_code%%" +
                    "product_name = %%product_name%%" +
                    "market_code = %%market_code%%" +
                    "market_name = %%market_name%%" +
                    "info_generation_timestamp = %%info_generation_timestamp%%" +
                    "trade_stop_restart_timestamp = %%trade_stop_restart_timestamp%%" +
                    "ord_receipt_restart_timestamp = %%ord_receipt_restart_timestamp%%" +
                    "old_estimation_price = %%old_estimation_price%%" +
                    "new_estimation_price = %%new_estimation_price%%" +
                    "old_last_closing_price = %%old_last_closing_price%%" +
                    "new_last_closing_price = %%new_last_closing_price%%" +
                    "old_base_price = %%old_base_price%%" +
                    "new_base_price = %%new_base_price%%" +
                    "old_price_range_type = %%old_price_range_type%%" +
                    "new_price_range_type = %%new_price_range_type%%" +
                    "old_price_range_unit_type = %%old_price_range_unit_type%%" +
                    "new_price_range_unit_type = %%new_price_range_unit_type%%" +
                    "old_high_price_range = %%old_high_price_range%%" +
                    "new_high_price_range = %%new_high_price_range%%" +
                    "old_low_price_range = %%old_low_price_range%%" +
                    "new_low_price_range = %%new_low_price_range%%" +
                    "old_last_closing_price_updq = %%old_last_closing_price_updq%%" +
                    "new_last_closing_price_updq = %%new_last_closing_price_updq%%" +
                    "old_base_price_updq = %%old_base_price_updq%%" +
                    "new_base_price_updq = %%new_base_price_updq%%" +
                    "title = %%title%%" +
                    "text = %%text%%" +
                    "process_result_div = %%process_result_div%%");
            TestDBUtility.insertWithDel(l_mailInfoParams);

            TestDBUtility.deleteAll(MailProcRow.TYPE);

            WEB3AdminEquityAttentionInfoNotifyTransactionCallback l_callback =
                l_impl.new WEB3AdminEquityAttentionInfoNotifyTransactionCallback();
            
            Method l_method = WEB3AdminEquityAttentionInfoNotifyTransactionCallback.class.getDeclaredMethod(
                    "insertSendMail",
                    new Class[]{WEB3EquityProduct.class, Market.class, AttentionInfoHistoryParams.class});
            l_method.setAccessible(true);
            Object[] l_obj = {null, l_market, l_attentionInfoHistoryParams};
            l_method.invoke(l_callback, l_obj);

            MailProcRow l_mailProcRow =
                MailProcDao.findRowByInstitutionCodeBranchCodeSendmailDivDiscernmentIdAccountCodeMailId(
                        "0D",
                        "000",
                        "3103",//
                        "9",//
                        "0000000",
                        1001);

            assertEquals("0", l_mailProcRow.getStatus());
            assertEquals(null, l_mailProcRow.getEmailAddress());
            assertEquals(null, l_mailProcRow.getSendEmailAddress());
            assertEquals("abc", l_mailProcRow.getSubject());
            assertEquals(
                    "product_code = " +
                    "product_name = myn" +
                    "market_code = SP" +
                    "market_name = シンガポール" +
                    "info_generation_timestamp = 2009/01/08 00:00:00" +
                    "trade_stop_restart_timestamp = 2009/02/08 00:00:00" +
                    "ord_receipt_restart_timestamp = 2009/03/08 00:00:00" +
                    "old_estimation_price = 11.0" +
                    "new_estimation_price = 12.0" +
                    "old_last_closing_price = 13.0" +
                    "new_last_closing_price = 14.0" +
                    "old_base_price = 15.0" +
                    "new_base_price = 16.0" +
                    "old_price_range_type = a" +
                    "new_price_range_type = b" +
                    "old_price_range_unit_type = c" +
                    "new_price_range_unit_type = d" +
                    "old_high_price_range = 32.0" +
                    "new_high_price_range = 18.0" +
                    "old_low_price_range = 6.0" +
                    "new_low_price_range = 19.0" +
                    "old_last_closing_price_updq = 20.0" +
                    "new_last_closing_price_updq = 21.0" +
                    "old_base_price_updq = 22.0" +
                    "new_base_price_updq = 23.0" +
                    "title = hgfdsa" +
                    "text = qwerty" +
                    "process_result_div = メール送信済", l_mailProcRow.getMailText());
            assertEquals(BooleanEnum.FALSE, l_mailProcRow.getDeleteFlag());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }*/
    /*
     * 注意情報履歴テーブル.注意情報種別＝"フリーフォーマット"の場合
     * 注意情報履歴テーブル.注意情報区分コード＝"売買中断の取消"の場合
     * 株式銘柄=nullの場合
     */
    /*public void testInsertSendMail_C0010()
    {
        final String STR_METHOD_NAME = "testInsertSendMail_C0010()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setStandardName("xyz");
            TestDBUtility.insertWithDel(l_productParams);
            
            EqtypeProductParams l_eqtypeProductParams = new EqtypeProductParams();
            l_eqtypeProductParams.setProductId(3304148080000L);
            l_eqtypeProductParams.setInstitutionCode("0D");
            l_eqtypeProductParams.setProductCode("1001");
            l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeProductParams.setYearlyBooksClosingDate(WEB3DateUtility.getDate("20090108", "yyyyMMdd"));
            l_eqtypeProductParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_eqtypeProductParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_eqtypeProductParams.setStandardName("xyz");
//            WEB3EquityProduct l_equityProduct = new WEB3EquityProduct(l_eqtypeProductParams);
            
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_marketParams);
            Market l_market = new WEB3GentradeMarket(3303L);
            
            AttentionInfoHistoryParams l_attentionInfoHistoryParams = new AttentionInfoHistoryParams();
            l_attentionInfoHistoryParams.setAttentionInfoHistoryId(1001);
            l_attentionInfoHistoryParams.setAttentionInfoType("3");//
            l_attentionInfoHistoryParams.setInstitutionCode("0D");
            l_attentionInfoHistoryParams.setMarketId(1002);
            l_attentionInfoHistoryParams.setValidUntilBizDate("30");
            l_attentionInfoHistoryParams.setAttentionInfoDivCode("A012");//
            l_attentionInfoHistoryParams.setStandardName("myn");
            l_attentionInfoHistoryParams.setInfoGenerationTimestamp(WEB3DateUtility.getDate("20090108", "yyyyMMdd"));
            l_attentionInfoHistoryParams.setTradeStopRestartTimestamp(WEB3DateUtility.getDate("20090208", "yyyyMMdd"));
            l_attentionInfoHistoryParams.setOrdReceiptRestartTimestamp(WEB3DateUtility.getDate("20090308", "yyyyMMdd"));
            
            l_attentionInfoHistoryParams.setOldEstimationPrice(11);
            l_attentionInfoHistoryParams.setNewEstimationPrice(12);
            l_attentionInfoHistoryParams.setOldLastClosingPrice(13);
            l_attentionInfoHistoryParams.setNewLastClosingPrice(14);
            l_attentionInfoHistoryParams.setOldBasePrice(15);
            l_attentionInfoHistoryParams.setNewBasePrice(16);
            
            l_attentionInfoHistoryParams.setOldPriceRangeType("a");
            l_attentionInfoHistoryParams.setNewPriceRangeType("b");
            l_attentionInfoHistoryParams.setOldPriceRangeUnitType("c");
            l_attentionInfoHistoryParams.setNewPriceRangeUnitType("d");
            
            l_attentionInfoHistoryParams.setOldHighCompPriceRange(17);
            l_attentionInfoHistoryParams.setNewHighPriceRange(18);
            l_attentionInfoHistoryParams.setOldLowCompPriceRange(9);
            l_attentionInfoHistoryParams.setNewLowPriceRange(19);
            
            l_attentionInfoHistoryParams.setOldLastClosingPriceUpdq(20);
            l_attentionInfoHistoryParams.setNewLastClosingPriceUpdq(21);
            
            l_attentionInfoHistoryParams.setOldBasePriceUpdq(22);
            l_attentionInfoHistoryParams.setNewBasePriceUpdq(23);
            
            l_attentionInfoHistoryParams.setFreeFormatTitle("hgfdsa");
            l_attentionInfoHistoryParams.setFreeFormatText("qwerty");
            
            l_attentionInfoHistoryParams.setProcessResultDiv("2");//
            
            l_attentionInfoHistoryParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_attentionInfoHistoryParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());


            TestDBUtility.deleteAll(MailInfoRow.TYPE);
            MailInfoParams l_mailInfoParams = new MailInfoParams();
            l_mailInfoParams.setInstitutionCode("0D");
            l_mailInfoParams.setSendmailDiv("3103");//
            l_mailInfoParams.setDiscernmentId("10");//
            l_mailInfoParams.setSubject("abc");
            l_mailInfoParams.setLastUpdater("007");
            l_mailInfoParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_mailInfoParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_mailInfoParams.setMailText(
                    "product_code = %%product_code%%" +
                    "product_name = %%product_name%%" +
                    "market_code = %%market_code%%" +
                    "market_name = %%market_name%%" +
                    "info_generation_timestamp = %%info_generation_timestamp%%" +
                    "trade_stop_restart_timestamp = %%trade_stop_restart_timestamp%%" +
                    "ord_receipt_restart_timestamp = %%ord_receipt_restart_timestamp%%" +
                    "old_estimation_price = %%old_estimation_price%%" +
                    "new_estimation_price = %%new_estimation_price%%" +
                    "old_last_closing_price = %%old_last_closing_price%%" +
                    "new_last_closing_price = %%new_last_closing_price%%" +
                    "old_base_price = %%old_base_price%%" +
                    "new_base_price = %%new_base_price%%" +
                    "old_price_range_type = %%old_price_range_type%%" +
                    "new_price_range_type = %%new_price_range_type%%" +
                    "old_price_range_unit_type = %%old_price_range_unit_type%%" +
                    "new_price_range_unit_type = %%new_price_range_unit_type%%" +
                    "old_high_price_range = %%old_high_price_range%%" +
                    "new_high_price_range = %%new_high_price_range%%" +
                    "old_low_price_range = %%old_low_price_range%%" +
                    "new_low_price_range = %%new_low_price_range%%" +
                    "old_last_closing_price_updq = %%old_last_closing_price_updq%%" +
                    "new_last_closing_price_updq = %%new_last_closing_price_updq%%" +
                    "old_base_price_updq = %%old_base_price_updq%%" +
                    "new_base_price_updq = %%new_base_price_updq%%" +
                    "title = %%title%%" +
                    "text = %%text%%" +
                    "process_result_div = %%process_result_div%%");
            TestDBUtility.insertWithDel(l_mailInfoParams);

            TestDBUtility.deleteAll(MailProcRow.TYPE);

            WEB3AdminEquityAttentionInfoNotifyTransactionCallback l_callback =
                l_impl.new WEB3AdminEquityAttentionInfoNotifyTransactionCallback();
            
            Method l_method = WEB3AdminEquityAttentionInfoNotifyTransactionCallback.class.getDeclaredMethod(
                    "insertSendMail",
                    new Class[]{WEB3EquityProduct.class, Market.class, AttentionInfoHistoryParams.class});
            l_method.setAccessible(true);
            Object[] l_obj = {null, l_market, l_attentionInfoHistoryParams};
            l_method.invoke(l_callback, l_obj);

            MailProcRow l_mailProcRow =
                MailProcDao.findRowByInstitutionCodeBranchCodeSendmailDivDiscernmentIdAccountCodeMailId(
                        "0D",
                        "000",
                        "3103",//
                        "10",//
                        "0000000",
                        1001);

            assertEquals("0", l_mailProcRow.getStatus());
            assertEquals(null, l_mailProcRow.getEmailAddress());
            assertEquals(null, l_mailProcRow.getSendEmailAddress());
            assertEquals("abc", l_mailProcRow.getSubject());
            assertEquals(
                    "product_code = " +
                    "product_name = myn" +
                    "market_code = SP" +
                    "market_name = シンガポール" +
                    "info_generation_timestamp = 2009/01/08 00:00:00" +
                    "trade_stop_restart_timestamp = 2009/02/08 00:00:00" +
                    "ord_receipt_restart_timestamp = 2009/03/08 00:00:00" +
                    "old_estimation_price = 11.0" +
                    "new_estimation_price = 12.0" +
                    "old_last_closing_price = 13.0" +
                    "new_last_closing_price = 14.0" +
                    "old_base_price = 15.0" +
                    "new_base_price = 16.0" +
                    "old_price_range_type = a" +
                    "new_price_range_type = b" +
                    "old_price_range_unit_type = c" +
                    "new_price_range_unit_type = d" +
                    "old_high_price_range = 32.0" +
                    "new_high_price_range = 18.0" +
                    "old_low_price_range = 6.0" +
                    "new_low_price_range = 19.0" +
                    "old_last_closing_price_updq = 20.0" +
                    "new_last_closing_price_updq = 21.0" +
                    "old_base_price_updq = 22.0" +
                    "new_base_price_updq = 23.0" +
                    "title = hgfdsa" +
                    "text = qwerty" +
                    "process_result_div = メール送信済", l_mailProcRow.getMailText());
            assertEquals(BooleanEnum.FALSE, l_mailProcRow.getDeleteFlag());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }*/
    /*
     * 注意情報履歴テーブル.注意情報種別＝"フリーフォーマット"の場合
     * 注意情報履歴テーブル.注意情報区分コード＝"売買中断の解除"の場合
     * 株式銘柄=nullの場合
     */
    /*public void testInsertSendMail_C0011()
    {
        final String STR_METHOD_NAME = "testInsertSendMail_C0011()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setStandardName("xyz");
            TestDBUtility.insertWithDel(l_productParams);
            
            EqtypeProductParams l_eqtypeProductParams = new EqtypeProductParams();
            l_eqtypeProductParams.setProductId(3304148080000L);
            l_eqtypeProductParams.setInstitutionCode("0D");
            l_eqtypeProductParams.setProductCode("1001");
            l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeProductParams.setYearlyBooksClosingDate(WEB3DateUtility.getDate("20090108", "yyyyMMdd"));
            l_eqtypeProductParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_eqtypeProductParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_eqtypeProductParams.setStandardName("xyz");
//            WEB3EquityProduct l_equityProduct = new WEB3EquityProduct(l_eqtypeProductParams);
            
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_marketParams);
            Market l_market = new WEB3GentradeMarket(3303L);
            
            AttentionInfoHistoryParams l_attentionInfoHistoryParams = new AttentionInfoHistoryParams();
            l_attentionInfoHistoryParams.setAttentionInfoHistoryId(1001);
            l_attentionInfoHistoryParams.setAttentionInfoType("3");//
            l_attentionInfoHistoryParams.setInstitutionCode("0D");
            l_attentionInfoHistoryParams.setMarketId(1002);
            l_attentionInfoHistoryParams.setValidUntilBizDate("30");
            l_attentionInfoHistoryParams.setAttentionInfoDivCode("A013");//
            l_attentionInfoHistoryParams.setStandardName("myn");
            l_attentionInfoHistoryParams.setInfoGenerationTimestamp(WEB3DateUtility.getDate("20090108", "yyyyMMdd"));
            l_attentionInfoHistoryParams.setTradeStopRestartTimestamp(WEB3DateUtility.getDate("20090208", "yyyyMMdd"));
            l_attentionInfoHistoryParams.setOrdReceiptRestartTimestamp(WEB3DateUtility.getDate("20090308", "yyyyMMdd"));
            
            l_attentionInfoHistoryParams.setOldEstimationPrice(11);
            l_attentionInfoHistoryParams.setNewEstimationPrice(12);
            l_attentionInfoHistoryParams.setOldLastClosingPrice(13);
            l_attentionInfoHistoryParams.setNewLastClosingPrice(14);
            l_attentionInfoHistoryParams.setOldBasePrice(15);
            l_attentionInfoHistoryParams.setNewBasePrice(16);
            
            l_attentionInfoHistoryParams.setOldPriceRangeType("a");
            l_attentionInfoHistoryParams.setNewPriceRangeType("b");
            l_attentionInfoHistoryParams.setOldPriceRangeUnitType("c");
            l_attentionInfoHistoryParams.setNewPriceRangeUnitType("d");
            
            l_attentionInfoHistoryParams.setOldHighCompPriceRange(17);
            l_attentionInfoHistoryParams.setNewHighPriceRange(18);
            l_attentionInfoHistoryParams.setOldLowCompPriceRange(9);
            l_attentionInfoHistoryParams.setNewLowPriceRange(19);
            
            l_attentionInfoHistoryParams.setOldLastClosingPriceUpdq(20);
            l_attentionInfoHistoryParams.setNewLastClosingPriceUpdq(21);
            
            l_attentionInfoHistoryParams.setOldBasePriceUpdq(22);
            l_attentionInfoHistoryParams.setNewBasePriceUpdq(23);
            
            l_attentionInfoHistoryParams.setFreeFormatTitle("hgfdsa");
            l_attentionInfoHistoryParams.setFreeFormatText("qwerty");
            
            l_attentionInfoHistoryParams.setProcessResultDiv("2");//
            l_attentionInfoHistoryParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_attentionInfoHistoryParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());


            TestDBUtility.deleteAll(MailInfoRow.TYPE);
            MailInfoParams l_mailInfoParams = new MailInfoParams();
            l_mailInfoParams.setInstitutionCode("0D");
            l_mailInfoParams.setSendmailDiv("3103");//
            l_mailInfoParams.setDiscernmentId("11");//
            l_mailInfoParams.setSubject("abc");
            l_mailInfoParams.setLastUpdater("007");
            l_mailInfoParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_mailInfoParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_mailInfoParams.setMailText(
                    "product_code = %%product_code%%" +
                    "product_name = %%product_name%%" +
                    "market_code = %%market_code%%" +
                    "market_name = %%market_name%%" +
                    "info_generation_timestamp = %%info_generation_timestamp%%" +
                    "trade_stop_restart_timestamp = %%trade_stop_restart_timestamp%%" +
                    "ord_receipt_restart_timestamp = %%ord_receipt_restart_timestamp%%" +
                    "old_estimation_price = %%old_estimation_price%%" +
                    "new_estimation_price = %%new_estimation_price%%" +
                    "old_last_closing_price = %%old_last_closing_price%%" +
                    "new_last_closing_price = %%new_last_closing_price%%" +
                    "old_base_price = %%old_base_price%%" +
                    "new_base_price = %%new_base_price%%" +
                    "old_price_range_type = %%old_price_range_type%%" +
                    "new_price_range_type = %%new_price_range_type%%" +
                    "old_price_range_unit_type = %%old_price_range_unit_type%%" +
                    "new_price_range_unit_type = %%new_price_range_unit_type%%" +
                    "old_high_price_range = %%old_high_price_range%%" +
                    "new_high_price_range = %%new_high_price_range%%" +
                    "old_low_price_range = %%old_low_price_range%%" +
                    "new_low_price_range = %%new_low_price_range%%" +
                    "old_last_closing_price_updq = %%old_last_closing_price_updq%%" +
                    "new_last_closing_price_updq = %%new_last_closing_price_updq%%" +
                    "old_base_price_updq = %%old_base_price_updq%%" +
                    "new_base_price_updq = %%new_base_price_updq%%" +
                    "title = %%title%%" +
                    "text = %%text%%" +
                    "process_result_div = %%process_result_div%%");
            TestDBUtility.insertWithDel(l_mailInfoParams);

            TestDBUtility.deleteAll(MailProcRow.TYPE);

            WEB3AdminEquityAttentionInfoNotifyTransactionCallback l_callback =
                l_impl.new WEB3AdminEquityAttentionInfoNotifyTransactionCallback();
            
            Method l_method = WEB3AdminEquityAttentionInfoNotifyTransactionCallback.class.getDeclaredMethod(
                    "insertSendMail",
                    new Class[]{WEB3EquityProduct.class, Market.class, AttentionInfoHistoryParams.class});
            l_method.setAccessible(true);
            Object[] l_obj = {null, l_market, l_attentionInfoHistoryParams};
            l_method.invoke(l_callback, l_obj);

            MailProcRow l_mailProcRow =
                MailProcDao.findRowByInstitutionCodeBranchCodeSendmailDivDiscernmentIdAccountCodeMailId(
                        "0D",
                        "000",
                        "3103",//
                        "11",//
                        "0000000",
                        1001);

            assertEquals("0", l_mailProcRow.getStatus());
            assertEquals(null, l_mailProcRow.getEmailAddress());
            assertEquals(null, l_mailProcRow.getSendEmailAddress());
            assertEquals("abc", l_mailProcRow.getSubject());
            assertEquals(
                    "product_code = " +
                    "product_name = myn" +
                    "market_code = SP" +
                    "market_name = シンガポール" +
                    "info_generation_timestamp = 2009/01/08 00:00:00" +
                    "trade_stop_restart_timestamp = 2009/02/08 00:00:00" +
                    "ord_receipt_restart_timestamp = 2009/03/08 00:00:00" +
                    "old_estimation_price = 11.0" +
                    "new_estimation_price = 12.0" +
                    "old_last_closing_price = 13.0" +
                    "new_last_closing_price = 14.0" +
                    "old_base_price = 15.0" +
                    "new_base_price = 16.0" +
                    "old_price_range_type = a" +
                    "new_price_range_type = b" +
                    "old_price_range_unit_type = c" +
                    "new_price_range_unit_type = d" +
                    "old_high_price_range = 32.0" +
                    "new_high_price_range = 18.0" +
                    "old_low_price_range = 6.0" +
                    "new_low_price_range = 19.0" +
                    "old_last_closing_price_updq = 20.0" +
                    "new_last_closing_price_updq = 21.0" +
                    "old_base_price_updq = 22.0" +
                    "new_base_price_updq = 23.0" +
                    "title = hgfdsa" +
                    "text = qwerty" +
                    "process_result_div = メール送信済", l_mailProcRow.getMailText());
            assertEquals(BooleanEnum.FALSE, l_mailProcRow.getDeleteFlag());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }*/
    /*
     * 注意情報履歴テーブル.注意情報種別＝"フリーフォーマット"の場合
     * 注意情報履歴テーブル.注意情報区分コード＝"売買中断の解除の取消"の場合
     * 株式銘柄=nullの場合
     */
    /*public void testInsertSendMail_C0012()
    {
        final String STR_METHOD_NAME = "testInsertSendMail_C0012()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setStandardName("xyz");
            TestDBUtility.insertWithDel(l_productParams);
            
            EqtypeProductParams l_eqtypeProductParams = new EqtypeProductParams();
            l_eqtypeProductParams.setProductId(3304148080000L);
            l_eqtypeProductParams.setInstitutionCode("0D");
            l_eqtypeProductParams.setProductCode("1001");
            l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeProductParams.setYearlyBooksClosingDate(WEB3DateUtility.getDate("20090108", "yyyyMMdd"));
            l_eqtypeProductParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_eqtypeProductParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_eqtypeProductParams.setStandardName("xyz");
//            WEB3EquityProduct l_equityProduct = new WEB3EquityProduct(l_eqtypeProductParams);
            
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_marketParams);
            Market l_market = new WEB3GentradeMarket(3303L);
            
            AttentionInfoHistoryParams l_attentionInfoHistoryParams = new AttentionInfoHistoryParams();
            l_attentionInfoHistoryParams.setAttentionInfoHistoryId(1001);
            l_attentionInfoHistoryParams.setAttentionInfoType("3");//
            l_attentionInfoHistoryParams.setInstitutionCode("0D");
            l_attentionInfoHistoryParams.setMarketId(1002);
            l_attentionInfoHistoryParams.setValidUntilBizDate("30");
            l_attentionInfoHistoryParams.setAttentionInfoDivCode("A014");//
            l_attentionInfoHistoryParams.setStandardName("myn");
            l_attentionInfoHistoryParams.setInfoGenerationTimestamp(WEB3DateUtility.getDate("20090108", "yyyyMMdd"));
            l_attentionInfoHistoryParams.setTradeStopRestartTimestamp(WEB3DateUtility.getDate("20090208", "yyyyMMdd"));
            l_attentionInfoHistoryParams.setOrdReceiptRestartTimestamp(WEB3DateUtility.getDate("20090308", "yyyyMMdd"));
            
            l_attentionInfoHistoryParams.setOldEstimationPrice(11);
            l_attentionInfoHistoryParams.setNewEstimationPrice(12);
            l_attentionInfoHistoryParams.setOldLastClosingPrice(13);
            l_attentionInfoHistoryParams.setNewLastClosingPrice(14);
            l_attentionInfoHistoryParams.setOldBasePrice(15);
            l_attentionInfoHistoryParams.setNewBasePrice(16);
            
            l_attentionInfoHistoryParams.setOldPriceRangeType("a");
            l_attentionInfoHistoryParams.setNewPriceRangeType("b");
            l_attentionInfoHistoryParams.setOldPriceRangeUnitType("c");
            l_attentionInfoHistoryParams.setNewPriceRangeUnitType("d");
            
            l_attentionInfoHistoryParams.setOldHighCompPriceRange(17);
            l_attentionInfoHistoryParams.setNewHighPriceRange(18);
            l_attentionInfoHistoryParams.setOldLowCompPriceRange(9);
            l_attentionInfoHistoryParams.setNewLowPriceRange(19);
            
            l_attentionInfoHistoryParams.setOldLastClosingPriceUpdq(20);
            l_attentionInfoHistoryParams.setNewLastClosingPriceUpdq(21);
            
            l_attentionInfoHistoryParams.setOldBasePriceUpdq(22);
            l_attentionInfoHistoryParams.setNewBasePriceUpdq(23);
            
            l_attentionInfoHistoryParams.setFreeFormatTitle("hgfdsa");
            l_attentionInfoHistoryParams.setFreeFormatText("qwerty");
            
            l_attentionInfoHistoryParams.setProcessResultDiv("2");//
            
            l_attentionInfoHistoryParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_attentionInfoHistoryParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());


            TestDBUtility.deleteAll(MailInfoRow.TYPE);
            MailInfoParams l_mailInfoParams = new MailInfoParams();
            l_mailInfoParams.setInstitutionCode("0D");
            l_mailInfoParams.setSendmailDiv("3103");//
            l_mailInfoParams.setDiscernmentId("12");//
            l_mailInfoParams.setSubject("abc");
            l_mailInfoParams.setLastUpdater("007");
            l_mailInfoParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_mailInfoParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_mailInfoParams.setMailText(
                    "product_code = %%product_code%%" +
                    "product_name = %%product_name%%" +
                    "market_code = %%market_code%%" +
                    "market_name = %%market_name%%" +
                    "info_generation_timestamp = %%info_generation_timestamp%%" +
                    "trade_stop_restart_timestamp = %%trade_stop_restart_timestamp%%" +
                    "ord_receipt_restart_timestamp = %%ord_receipt_restart_timestamp%%" +
                    "old_estimation_price = %%old_estimation_price%%" +
                    "new_estimation_price = %%new_estimation_price%%" +
                    "old_last_closing_price = %%old_last_closing_price%%" +
                    "new_last_closing_price = %%new_last_closing_price%%" +
                    "old_base_price = %%old_base_price%%" +
                    "new_base_price = %%new_base_price%%" +
                    "old_price_range_type = %%old_price_range_type%%" +
                    "new_price_range_type = %%new_price_range_type%%" +
                    "old_price_range_unit_type = %%old_price_range_unit_type%%" +
                    "new_price_range_unit_type = %%new_price_range_unit_type%%" +
                    "old_high_price_range = %%old_high_price_range%%" +
                    "new_high_price_range = %%new_high_price_range%%" +
                    "old_low_price_range = %%old_low_price_range%%" +
                    "new_low_price_range = %%new_low_price_range%%" +
                    "old_last_closing_price_updq = %%old_last_closing_price_updq%%" +
                    "new_last_closing_price_updq = %%new_last_closing_price_updq%%" +
                    "old_base_price_updq = %%old_base_price_updq%%" +
                    "new_base_price_updq = %%new_base_price_updq%%" +
                    "title = %%title%%" +
                    "text = %%text%%" +
                    "process_result_div = %%process_result_div%%");
            TestDBUtility.insertWithDel(l_mailInfoParams);

            TestDBUtility.deleteAll(MailProcRow.TYPE);

            WEB3AdminEquityAttentionInfoNotifyTransactionCallback l_callback =
                l_impl.new WEB3AdminEquityAttentionInfoNotifyTransactionCallback();
            
            Method l_method = WEB3AdminEquityAttentionInfoNotifyTransactionCallback.class.getDeclaredMethod(
                    "insertSendMail",
                    new Class[]{WEB3EquityProduct.class, Market.class, AttentionInfoHistoryParams.class});
            l_method.setAccessible(true);
            Object[] l_obj = {null, l_market, l_attentionInfoHistoryParams};
            l_method.invoke(l_callback, l_obj);

            MailProcRow l_mailProcRow =
                MailProcDao.findRowByInstitutionCodeBranchCodeSendmailDivDiscernmentIdAccountCodeMailId(
                        "0D",
                        "000",
                        "3103",//
                        "12",//
                        "0000000",
                        1001);

            assertEquals("0", l_mailProcRow.getStatus());
            assertEquals(null, l_mailProcRow.getEmailAddress());
            assertEquals(null, l_mailProcRow.getSendEmailAddress());
            assertEquals("abc", l_mailProcRow.getSubject());
            assertEquals(
                    "product_code = " +
                    "product_name = myn" +
                    "market_code = SP" +
                    "market_name = シンガポール" +
                    "info_generation_timestamp = 2009/01/08 00:00:00" +
                    "trade_stop_restart_timestamp = 2009/02/08 00:00:00" +
                    "ord_receipt_restart_timestamp = 2009/03/08 00:00:00" +
                    "old_estimation_price = 11.0" +
                    "new_estimation_price = 12.0" +
                    "old_last_closing_price = 13.0" +
                    "new_last_closing_price = 14.0" +
                    "old_base_price = 15.0" +
                    "new_base_price = 16.0" +
                    "old_price_range_type = a" +
                    "new_price_range_type = b" +
                    "old_price_range_unit_type = c" +
                    "new_price_range_unit_type = d" +
                    "old_high_price_range = 32.0" +
                    "new_high_price_range = 18.0" +
                    "old_low_price_range = 6.0" +
                    "new_low_price_range = 19.0" +
                    "old_last_closing_price_updq = 20.0" +
                    "new_last_closing_price_updq = 21.0" +
                    "old_base_price_updq = 22.0" +
                    "new_base_price_updq = 23.0" +
                    "title = hgfdsa" +
                    "text = qwerty" +
                    "process_result_div = メール送信済", l_mailProcRow.getMailText());
            assertEquals(BooleanEnum.FALSE, l_mailProcRow.getDeleteFlag());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }*/
    /*//TODO
     * 注意情報履歴テーブル.注意情報種別＝"フリーフォーマット"の場合
     * 注意情報履歴テーブル.注意情報区分コード＝"新規上場銘柄の初値が付いた場合"の場合, 注意情報履歴テーブル.処理結果区分＝"正常"の場合、"1"
     * 株式銘柄=nullの場合
     */
    /*public void testInsertSendMail_C0013()
    {
        final String STR_METHOD_NAME = "testInsertSendMail_C0013()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setStandardName("xyz");
            TestDBUtility.insertWithDel(l_productParams);
            
            EqtypeProductParams l_eqtypeProductParams = new EqtypeProductParams();
            l_eqtypeProductParams.setProductId(3304148080000L);
            l_eqtypeProductParams.setInstitutionCode("0D");
            l_eqtypeProductParams.setProductCode("1001");
            l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeProductParams.setYearlyBooksClosingDate(WEB3DateUtility.getDate("20090108", "yyyyMMdd"));
            l_eqtypeProductParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_eqtypeProductParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_eqtypeProductParams.setStandardName("xyz");
//            WEB3EquityProduct l_equityProduct = new WEB3EquityProduct(l_eqtypeProductParams);
            
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_marketParams);
            Market l_market = new WEB3GentradeMarket(3303L);
            
            AttentionInfoHistoryParams l_attentionInfoHistoryParams = new AttentionInfoHistoryParams();
            l_attentionInfoHistoryParams.setAttentionInfoHistoryId(1001);
            l_attentionInfoHistoryParams.setAttentionInfoType("3");//
            l_attentionInfoHistoryParams.setInstitutionCode("0D");
            l_attentionInfoHistoryParams.setMarketId(1002);
            l_attentionInfoHistoryParams.setValidUntilBizDate("30");
            l_attentionInfoHistoryParams.setAttentionInfoDivCode("A031");//
            l_attentionInfoHistoryParams.setStandardName("myn");
            l_attentionInfoHistoryParams.setInfoGenerationTimestamp(WEB3DateUtility.getDate("20090108", "yyyyMMdd"));
            l_attentionInfoHistoryParams.setTradeStopRestartTimestamp(WEB3DateUtility.getDate("20090208", "yyyyMMdd"));
            l_attentionInfoHistoryParams.setOrdReceiptRestartTimestamp(WEB3DateUtility.getDate("20090308", "yyyyMMdd"));
            
            l_attentionInfoHistoryParams.setOldEstimationPrice(11);
            l_attentionInfoHistoryParams.setNewEstimationPrice(12);
            l_attentionInfoHistoryParams.setOldLastClosingPrice(13);
            l_attentionInfoHistoryParams.setNewLastClosingPrice(14);
            l_attentionInfoHistoryParams.setOldBasePrice(15);
            l_attentionInfoHistoryParams.setNewBasePrice(16);
            
            l_attentionInfoHistoryParams.setOldPriceRangeType("a");
            l_attentionInfoHistoryParams.setNewPriceRangeType("b");
            l_attentionInfoHistoryParams.setOldPriceRangeUnitType("c");
            l_attentionInfoHistoryParams.setNewPriceRangeUnitType("d");
            
            l_attentionInfoHistoryParams.setOldHighCompPriceRange(17);
            l_attentionInfoHistoryParams.setNewHighPriceRange(18);
            l_attentionInfoHistoryParams.setOldLowCompPriceRange(9);
            l_attentionInfoHistoryParams.setNewLowPriceRange(19);
            
            l_attentionInfoHistoryParams.setOldLastClosingPriceUpdq(20);
            l_attentionInfoHistoryParams.setNewLastClosingPriceUpdq(21);
            
            l_attentionInfoHistoryParams.setOldBasePriceUpdq(22);
            l_attentionInfoHistoryParams.setNewBasePriceUpdq(23);
            
            l_attentionInfoHistoryParams.setFreeFormatTitle("hgfdsa");
            l_attentionInfoHistoryParams.setFreeFormatText("qwerty");
            
            l_attentionInfoHistoryParams.setProcessResultDiv("1");//
            l_attentionInfoHistoryParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_attentionInfoHistoryParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());


            TestDBUtility.deleteAll(MailInfoRow.TYPE);
            MailInfoParams l_mailInfoParams = new MailInfoParams();
            l_mailInfoParams.setInstitutionCode("0D");
            l_mailInfoParams.setSendmailDiv("3103");//
            l_mailInfoParams.setDiscernmentId("1");//
            l_mailInfoParams.setSubject("abc");
            l_mailInfoParams.setLastUpdater("007");
            l_mailInfoParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_mailInfoParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_mailInfoParams.setMailText(
                    "product_code = %%product_code%%" +
                    "product_name = %%product_name%%" +
                    "market_code = %%market_code%%" +
                    "market_name = %%market_name%%" +
                    "info_generation_timestamp = %%info_generation_timestamp%%" +
                    "trade_stop_restart_timestamp = %%trade_stop_restart_timestamp%%" +
                    "ord_receipt_restart_timestamp = %%ord_receipt_restart_timestamp%%" +
                    "old_estimation_price = %%old_estimation_price%%" +
                    "new_estimation_price = %%new_estimation_price%%" +
                    "old_last_closing_price = %%old_last_closing_price%%" +
                    "new_last_closing_price = %%new_last_closing_price%%" +
                    "old_base_price = %%old_base_price%%" +
                    "new_base_price = %%new_base_price%%" +
                    "old_price_range_type = %%old_price_range_type%%" +
                    "new_price_range_type = %%new_price_range_type%%" +
                    "old_price_range_unit_type = %%old_price_range_unit_type%%" +
                    "new_price_range_unit_type = %%new_price_range_unit_type%%" +
                    "old_high_price_range = %%old_high_price_range%%" +
                    "new_high_price_range = %%new_high_price_range%%" +
                    "old_low_price_range = %%old_low_price_range%%" +
                    "new_low_price_range = %%new_low_price_range%%" +
                    "old_last_closing_price_updq = %%old_last_closing_price_updq%%" +
                    "new_last_closing_price_updq = %%new_last_closing_price_updq%%" +
                    "old_base_price_updq = %%old_base_price_updq%%" +
                    "new_base_price_updq = %%new_base_price_updq%%" +
                    "title = %%title%%" +
                    "text = %%text%%" +
                    "process_result_div = %%process_result_div%%");
            TestDBUtility.insertWithDel(l_mailInfoParams);

            TestDBUtility.deleteAll(MailProcRow.TYPE);

            WEB3AdminEquityAttentionInfoNotifyTransactionCallback l_callback =
                l_impl.new WEB3AdminEquityAttentionInfoNotifyTransactionCallback();
            
            Method l_method = WEB3AdminEquityAttentionInfoNotifyTransactionCallback.class.getDeclaredMethod(
                    "insertSendMail",
                    new Class[]{WEB3EquityProduct.class, Market.class, AttentionInfoHistoryParams.class});
            l_method.setAccessible(true);
            Object[] l_obj = {null, l_market, l_attentionInfoHistoryParams};
            l_method.invoke(l_callback, l_obj);

            MailProcRow l_mailProcRow =
                MailProcDao.findRowByInstitutionCodeBranchCodeSendmailDivDiscernmentIdAccountCodeMailId(
                        "0D",
                        "000",
                        "3103",//
                        "1",//
                        "0000000",
                        1001);

            assertEquals("0", l_mailProcRow.getStatus());
            assertEquals(null, l_mailProcRow.getEmailAddress());
            assertEquals(null, l_mailProcRow.getSendEmailAddress());
            assertEquals("abc", l_mailProcRow.getSubject());
            assertEquals(
                    "product_code = " +
                    "product_name = myn" +
                    "market_code = SP" +
                    "market_name = シンガポール" +
                    "info_generation_timestamp = 2009/01/08 00:00:00" +
                    "trade_stop_restart_timestamp = 2009/02/08 00:00:00" +
                    "ord_receipt_restart_timestamp = 2009/03/08 00:00:00" +
                    "old_estimation_price = 11.0" +
                    "new_estimation_price = 12.0" +
                    "old_last_closing_price = 13.0" +
                    "new_last_closing_price = 14.0" +
                    "old_base_price = 15.0" +
                    "new_base_price = 16.0" +
                    "old_price_range_type = a" +
                    "new_price_range_type = b" +
                    "old_price_range_unit_type = c" +
                    "new_price_range_unit_type = d" +
                    "old_high_price_range = 32.0" +
                    "new_high_price_range = 18.0" +
                    "old_low_price_range = 6.0" +
                    "new_low_price_range = 19.0" +
                    "old_last_closing_price_updq = 20.0" +
                    "new_last_closing_price_updq = 21.0" +
                    "old_base_price_updq = 22.0" +
                    "new_base_price_updq = 23.0" +
                    "title = hgfdsa" +
                    "text = qwerty" +
                    "process_result_div = データ更新済", l_mailProcRow.getMailText());
            assertEquals(BooleanEnum.FALSE, l_mailProcRow.getDeleteFlag());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }*/
    /*
     * 注意情報履歴テーブル.注意情報種別＝"フリーフォーマット"の場合
     * 注意情報履歴テーブル.注意情報区分コード＝"新規上場銘柄の初値が付いた場合"の場合, 注意情報履歴テーブル.処理結果区分＝"正常（更新無）"の場合、"2" 
     * 株式銘柄=nullの場合
     */
    /*public void testInsertSendMail_C0014()
    {
        final String STR_METHOD_NAME = "testInsertSendMail_C0014()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setStandardName("xyz");
            TestDBUtility.insertWithDel(l_productParams);
            
            EqtypeProductParams l_eqtypeProductParams = new EqtypeProductParams();
            l_eqtypeProductParams.setProductId(3304148080000L);
            l_eqtypeProductParams.setInstitutionCode("0D");
            l_eqtypeProductParams.setProductCode("1001");
            l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeProductParams.setYearlyBooksClosingDate(WEB3DateUtility.getDate("20090108", "yyyyMMdd"));
            l_eqtypeProductParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_eqtypeProductParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_eqtypeProductParams.setStandardName("xyz");
//            WEB3EquityProduct l_equityProduct = new WEB3EquityProduct(l_eqtypeProductParams);
            
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_marketParams);
            Market l_market = new WEB3GentradeMarket(3303L);
            
            AttentionInfoHistoryParams l_attentionInfoHistoryParams = new AttentionInfoHistoryParams();
            l_attentionInfoHistoryParams.setAttentionInfoHistoryId(1001);
            l_attentionInfoHistoryParams.setAttentionInfoType("3");//
            l_attentionInfoHistoryParams.setInstitutionCode("0D");
            l_attentionInfoHistoryParams.setMarketId(1002);
            l_attentionInfoHistoryParams.setValidUntilBizDate("30");
            l_attentionInfoHistoryParams.setAttentionInfoDivCode("A031");//
            l_attentionInfoHistoryParams.setStandardName("myn");
            l_attentionInfoHistoryParams.setInfoGenerationTimestamp(WEB3DateUtility.getDate("20090108", "yyyyMMdd"));
            l_attentionInfoHistoryParams.setTradeStopRestartTimestamp(WEB3DateUtility.getDate("20090208", "yyyyMMdd"));
            l_attentionInfoHistoryParams.setOrdReceiptRestartTimestamp(WEB3DateUtility.getDate("20090308", "yyyyMMdd"));
            
            l_attentionInfoHistoryParams.setOldEstimationPrice(11);
            l_attentionInfoHistoryParams.setNewEstimationPrice(12);
            l_attentionInfoHistoryParams.setOldLastClosingPrice(13);
            l_attentionInfoHistoryParams.setNewLastClosingPrice(14);
            l_attentionInfoHistoryParams.setOldBasePrice(15);
            l_attentionInfoHistoryParams.setNewBasePrice(16);
            
            l_attentionInfoHistoryParams.setOldPriceRangeType("a");
            l_attentionInfoHistoryParams.setNewPriceRangeType("b");
            l_attentionInfoHistoryParams.setOldPriceRangeUnitType("c");
            l_attentionInfoHistoryParams.setNewPriceRangeUnitType("d");
            
            l_attentionInfoHistoryParams.setOldHighCompPriceRange(17);
            l_attentionInfoHistoryParams.setNewHighPriceRange(18);
            l_attentionInfoHistoryParams.setOldLowCompPriceRange(9);
            l_attentionInfoHistoryParams.setNewLowPriceRange(19);
            
            l_attentionInfoHistoryParams.setOldLastClosingPriceUpdq(20);
            l_attentionInfoHistoryParams.setNewLastClosingPriceUpdq(21);
            
            l_attentionInfoHistoryParams.setOldBasePriceUpdq(22);
            l_attentionInfoHistoryParams.setNewBasePriceUpdq(23);
            
            l_attentionInfoHistoryParams.setFreeFormatTitle("hgfdsa");
            l_attentionInfoHistoryParams.setFreeFormatText("qwerty");
            
            l_attentionInfoHistoryParams.setProcessResultDiv("2");//
            l_attentionInfoHistoryParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_attentionInfoHistoryParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());


            TestDBUtility.deleteAll(MailInfoRow.TYPE);
            MailInfoParams l_mailInfoParams = new MailInfoParams();
            l_mailInfoParams.setInstitutionCode("0D");
            l_mailInfoParams.setSendmailDiv("3103");//
            l_mailInfoParams.setDiscernmentId("2");//
            l_mailInfoParams.setSubject("abc");
            l_mailInfoParams.setLastUpdater("007");
            l_mailInfoParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_mailInfoParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_mailInfoParams.setMailText(
                    "product_code = %%product_code%%" +
                    "product_name = %%product_name%%" +
                    "market_code = %%market_code%%" +
                    "market_name = %%market_name%%" +
                    "info_generation_timestamp = %%info_generation_timestamp%%" +
                    "trade_stop_restart_timestamp = %%trade_stop_restart_timestamp%%" +
                    "ord_receipt_restart_timestamp = %%ord_receipt_restart_timestamp%%" +
                    "old_estimation_price = %%old_estimation_price%%" +
                    "new_estimation_price = %%new_estimation_price%%" +
                    "old_last_closing_price = %%old_last_closing_price%%" +
                    "new_last_closing_price = %%new_last_closing_price%%" +
                    "old_base_price = %%old_base_price%%" +
                    "new_base_price = %%new_base_price%%" +
                    "old_price_range_type = %%old_price_range_type%%" +
                    "new_price_range_type = %%new_price_range_type%%" +
                    "old_price_range_unit_type = %%old_price_range_unit_type%%" +
                    "new_price_range_unit_type = %%new_price_range_unit_type%%" +
                    "old_high_price_range = %%old_high_price_range%%" +
                    "new_high_price_range = %%new_high_price_range%%" +
                    "old_low_price_range = %%old_low_price_range%%" +
                    "new_low_price_range = %%new_low_price_range%%" +
                    "old_last_closing_price_updq = %%old_last_closing_price_updq%%" +
                    "new_last_closing_price_updq = %%new_last_closing_price_updq%%" +
                    "old_base_price_updq = %%old_base_price_updq%%" +
                    "new_base_price_updq = %%new_base_price_updq%%" +
                    "title = %%title%%" +
                    "text = %%text%%" +
                    "process_result_div = %%process_result_div%%");
            TestDBUtility.insertWithDel(l_mailInfoParams);

            TestDBUtility.deleteAll(MailProcRow.TYPE);

            WEB3AdminEquityAttentionInfoNotifyTransactionCallback l_callback =
                l_impl.new WEB3AdminEquityAttentionInfoNotifyTransactionCallback();
            
            Method l_method = WEB3AdminEquityAttentionInfoNotifyTransactionCallback.class.getDeclaredMethod(
                    "insertSendMail",
                    new Class[]{WEB3EquityProduct.class, Market.class, AttentionInfoHistoryParams.class});
            l_method.setAccessible(true);
            Object[] l_obj = {null, l_market, l_attentionInfoHistoryParams};
            l_method.invoke(l_callback, l_obj);

            MailProcRow l_mailProcRow =
                MailProcDao.findRowByInstitutionCodeBranchCodeSendmailDivDiscernmentIdAccountCodeMailId(
                        "0D",
                        "000",
                        "3103",//
                        "2",//
                        "0000000",
                        1001);

            assertEquals("0", l_mailProcRow.getStatus());
            assertEquals(null, l_mailProcRow.getEmailAddress());
            assertEquals(null, l_mailProcRow.getSendEmailAddress());
            assertEquals("abc", l_mailProcRow.getSubject());
            assertEquals(
                    "product_code = " +
                    "product_name = myn" +
                    "market_code = SP" +
                    "market_name = シンガポール" +
                    "info_generation_timestamp = 2009/01/08 00:00:00" +
                    "trade_stop_restart_timestamp = 2009/02/08 00:00:00" +
                    "ord_receipt_restart_timestamp = 2009/03/08 00:00:00" +
                    "old_estimation_price = 11.0" +
                    "new_estimation_price = 12.0" +
                    "old_last_closing_price = 13.0" +
                    "new_last_closing_price = 14.0" +
                    "old_base_price = 15.0" +
                    "new_base_price = 16.0" +
                    "old_price_range_type = a" +
                    "new_price_range_type = b" +
                    "old_price_range_unit_type = c" +
                    "new_price_range_unit_type = d" +
                    "old_high_price_range = 32.0" +
                    "new_high_price_range = 18.0" +
                    "old_low_price_range = 6.0" +
                    "new_low_price_range = 19.0" +
                    "old_last_closing_price_updq = 20.0" +
                    "new_last_closing_price_updq = 21.0" +
                    "old_base_price_updq = 22.0" +
                    "new_base_price_updq = 23.0" +
                    "title = hgfdsa" +
                    "text = qwerty" +
                    "process_result_div = メール送信済", l_mailProcRow.getMailText());
            assertEquals(BooleanEnum.FALSE, l_mailProcRow.getDeleteFlag());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }*/
    
    /*
     * 注意情報履歴テーブル.注意情報種別＝"フリーフォーマット"の場合
     * 注意情報履歴テーブル.注意情報区分コード＝"新規上場銘柄の初値が付いた場合"の場合, 注意情報履歴テーブル.処理結果区分＝"正常（更新無）"の場合、"2" 
     * 株式銘柄=nullの場合
     * 市場=nullの場合
     */
    /*public void testInsertSendMail_C0015()
    {
        final String STR_METHOD_NAME = "testInsertSendMail_C0015()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setStandardName("xyz");
            TestDBUtility.insertWithDel(l_productParams);
            
            EqtypeProductParams l_eqtypeProductParams = new EqtypeProductParams();
            l_eqtypeProductParams.setProductId(3304148080000L);
            l_eqtypeProductParams.setInstitutionCode("0D");
            l_eqtypeProductParams.setProductCode("1001");
            l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeProductParams.setYearlyBooksClosingDate(WEB3DateUtility.getDate("20090108", "yyyyMMdd"));
            l_eqtypeProductParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_eqtypeProductParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_eqtypeProductParams.setStandardName("xyz");
//            WEB3EquityProduct l_equityProduct = new WEB3EquityProduct(l_eqtypeProductParams);
            
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_marketParams);
            Market l_market = null;
//                new WEB3GentradeMarket(3303L);
            
            AttentionInfoHistoryParams l_attentionInfoHistoryParams = new AttentionInfoHistoryParams();
            l_attentionInfoHistoryParams.setAttentionInfoHistoryId(1001);
            l_attentionInfoHistoryParams.setAttentionInfoType("3");//
            l_attentionInfoHistoryParams.setInstitutionCode("0D");
            l_attentionInfoHistoryParams.setMarketId(1002);
            l_attentionInfoHistoryParams.setValidUntilBizDate("30");
            l_attentionInfoHistoryParams.setAttentionInfoDivCode("A031");//
            l_attentionInfoHistoryParams.setStandardName("myn");
            l_attentionInfoHistoryParams.setInfoGenerationTimestamp(WEB3DateUtility.getDate("20090108", "yyyyMMdd"));
            l_attentionInfoHistoryParams.setTradeStopRestartTimestamp(WEB3DateUtility.getDate("20090208", "yyyyMMdd"));
            l_attentionInfoHistoryParams.setOrdReceiptRestartTimestamp(WEB3DateUtility.getDate("20090308", "yyyyMMdd"));
            
            l_attentionInfoHistoryParams.setOldEstimationPrice(11);
            l_attentionInfoHistoryParams.setNewEstimationPrice(12);
            l_attentionInfoHistoryParams.setOldLastClosingPrice(13);
            l_attentionInfoHistoryParams.setNewLastClosingPrice(14);
            l_attentionInfoHistoryParams.setOldBasePrice(15);
            l_attentionInfoHistoryParams.setNewBasePrice(16);
            
            l_attentionInfoHistoryParams.setOldPriceRangeType("a");
            l_attentionInfoHistoryParams.setNewPriceRangeType("b");
            l_attentionInfoHistoryParams.setOldPriceRangeUnitType("c");
            l_attentionInfoHistoryParams.setNewPriceRangeUnitType("d");
            
            l_attentionInfoHistoryParams.setOldHighCompPriceRange(17);
            l_attentionInfoHistoryParams.setNewHighPriceRange(18);
            l_attentionInfoHistoryParams.setOldLowCompPriceRange(9);
            l_attentionInfoHistoryParams.setNewLowPriceRange(19);
            
            l_attentionInfoHistoryParams.setOldLastClosingPriceUpdq(20);
            l_attentionInfoHistoryParams.setNewLastClosingPriceUpdq(21);
            
            l_attentionInfoHistoryParams.setOldBasePriceUpdq(22);
            l_attentionInfoHistoryParams.setNewBasePriceUpdq(23);
            
            l_attentionInfoHistoryParams.setFreeFormatTitle("hgfdsa");
            l_attentionInfoHistoryParams.setFreeFormatText("qwerty");
            
            l_attentionInfoHistoryParams.setProcessResultDiv("2");//
            l_attentionInfoHistoryParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_attentionInfoHistoryParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());


            TestDBUtility.deleteAll(MailInfoRow.TYPE);
            MailInfoParams l_mailInfoParams = new MailInfoParams();
            l_mailInfoParams.setInstitutionCode("0D");
            l_mailInfoParams.setSendmailDiv("3103");//
            l_mailInfoParams.setDiscernmentId("2");//
            l_mailInfoParams.setSubject("abc");
            l_mailInfoParams.setLastUpdater("007");
            l_mailInfoParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_mailInfoParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_mailInfoParams.setMailText(
                    "product_code = %%product_code%%" +
                    "product_name = %%product_name%%" +
                    "market_code = %%market_code%%" +
                    "market_name = %%market_name%%" +
                    "info_generation_timestamp = %%info_generation_timestamp%%" +
                    "trade_stop_restart_timestamp = %%trade_stop_restart_timestamp%%" +
                    "ord_receipt_restart_timestamp = %%ord_receipt_restart_timestamp%%" +
                    "old_estimation_price = %%old_estimation_price%%" +
                    "new_estimation_price = %%new_estimation_price%%" +
                    "old_last_closing_price = %%old_last_closing_price%%" +
                    "new_last_closing_price = %%new_last_closing_price%%" +
                    "old_base_price = %%old_base_price%%" +
                    "new_base_price = %%new_base_price%%" +
                    "old_price_range_type = %%old_price_range_type%%" +
                    "new_price_range_type = %%new_price_range_type%%" +
                    "old_price_range_unit_type = %%old_price_range_unit_type%%" +
                    "new_price_range_unit_type = %%new_price_range_unit_type%%" +
                    "old_high_price_range = %%old_high_price_range%%" +
                    "new_high_price_range = %%new_high_price_range%%" +
                    "old_low_price_range = %%old_low_price_range%%" +
                    "new_low_price_range = %%new_low_price_range%%" +
                    "old_last_closing_price_updq = %%old_last_closing_price_updq%%" +
                    "new_last_closing_price_updq = %%new_last_closing_price_updq%%" +
                    "old_base_price_updq = %%old_base_price_updq%%" +
                    "new_base_price_updq = %%new_base_price_updq%%" +
                    "title = %%title%%" +
                    "text = %%text%%" +
                    "process_result_div = %%process_result_div%%");
            TestDBUtility.insertWithDel(l_mailInfoParams);

            TestDBUtility.deleteAll(MailProcRow.TYPE);

            WEB3AdminEquityAttentionInfoNotifyTransactionCallback l_callback =
                l_impl.new WEB3AdminEquityAttentionInfoNotifyTransactionCallback();
            
            Method l_method = WEB3AdminEquityAttentionInfoNotifyTransactionCallback.class.getDeclaredMethod(
                    "insertSendMail",
                    new Class[]{WEB3EquityProduct.class, Market.class, AttentionInfoHistoryParams.class});
            l_method.setAccessible(true);
            Object[] l_obj = {null, l_market, l_attentionInfoHistoryParams};
            l_method.invoke(l_callback, l_obj);

            MailProcRow l_mailProcRow =
                MailProcDao.findRowByInstitutionCodeBranchCodeSendmailDivDiscernmentIdAccountCodeMailId(
                        "0D",
                        "000",
                        "3103",//
                        "2",//
                        "0000000",
                        1001);

            assertEquals("0", l_mailProcRow.getStatus());
            assertEquals(null, l_mailProcRow.getEmailAddress());
            assertEquals(null, l_mailProcRow.getSendEmailAddress());
            assertEquals("abc", l_mailProcRow.getSubject());
            assertEquals(
                    "product_code = " +
                    "product_name = myn" +
                    "market_code = " +
                    "market_name = " +
                    "info_generation_timestamp = 2009/01/08 00:00:00" +
                    "trade_stop_restart_timestamp = 2009/02/08 00:00:00" +
                    "ord_receipt_restart_timestamp = 2009/03/08 00:00:00" +
                    "old_estimation_price = 11.0" +
                    "new_estimation_price = 12.0" +
                    "old_last_closing_price = 13.0" +
                    "new_last_closing_price = 14.0" +
                    "old_base_price = 15.0" +
                    "new_base_price = 16.0" +
                    "old_price_range_type = a" +
                    "new_price_range_type = b" +
                    "old_price_range_unit_type = c" +
                    "new_price_range_unit_type = d" +
                    "old_high_price_range = 32.0" +
                    "new_high_price_range = 18.0" +
                    "old_low_price_range = 6.0" +
                    "new_low_price_range = 19.0" +
                    "old_last_closing_price_updq = 20.0" +
                    "new_last_closing_price_updq = 21.0" +
                    "old_base_price_updq = 22.0" +
                    "new_base_price_updq = 23.0" +
                    "title = hgfdsa" +
                    "text = qwerty" +
                    "process_result_div = メール送信済", l_mailProcRow.getMailText());
            assertEquals(BooleanEnum.FALSE, l_mailProcRow.getDeleteFlag());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }*/
    
    /*
     * 注意情報履歴テーブル.注意情報種別＝"フリーフォーマット"の場合
     * 注意情報履歴テーブル.注意情報区分コード＝"新規上場銘柄の初値が付いた場合"の場合, 注意情報履歴テーブル.処理結果区分＝"正常（更新無）"の場合、"2" 
     * 株式銘柄=nullの場合
     * 市場=nullの場合
     * 評価単価（変更前） = null
     * 評価単価（変更後）  = null
     * 基準値（終値）（変更前） = null
     * 基準値（終値）（変更後） = null
     * 基準値（変更前） != null
     * 基準値（変更後） = null
     * 値幅チェック区分（変更前） = null
     * 値幅チェック区分(変更後) = null
     * 値幅区分（変更前） = null
     * 値幅区分（変更後） = null 
     * 制限値幅上限（変更前）  = null
     * 制限値幅上限（変更前）  = null
     * 制限値幅下限（変更前）  = null
     * 制限値幅下限（変更後） = null
     * 基準値（終値）（翌日）（変更前）  = null
     * 基準値（終値）（翌日）（変更後） = null
     * 基準値（翌日）（変更前）  = null
     * 基準値（翌日）（変更前） = null
     */
    /*public void testInsertSendMail_C0016()
    {
        final String STR_METHOD_NAME = "testInsertSendMail_C0016()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setStandardName("xyz");
            TestDBUtility.insertWithDel(l_productParams);
            
            EqtypeProductParams l_eqtypeProductParams = new EqtypeProductParams();
            l_eqtypeProductParams.setProductId(3304148080000L);
            l_eqtypeProductParams.setInstitutionCode("0D");
            l_eqtypeProductParams.setProductCode("1001");
            l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeProductParams.setYearlyBooksClosingDate(WEB3DateUtility.getDate("20090108", "yyyyMMdd"));
            l_eqtypeProductParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_eqtypeProductParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_eqtypeProductParams.setStandardName("xyz");
//            WEB3EquityProduct l_equityProduct = new WEB3EquityProduct(l_eqtypeProductParams);
            
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_marketParams);
            Market l_market = null;
//                new WEB3GentradeMarket(3303L);
            
            AttentionInfoHistoryParams l_attentionInfoHistoryParams = new AttentionInfoHistoryParams();
            l_attentionInfoHistoryParams.setAttentionInfoHistoryId(1001);
            l_attentionInfoHistoryParams.setAttentionInfoType("3");//
            l_attentionInfoHistoryParams.setInstitutionCode("0D");
            l_attentionInfoHistoryParams.setMarketId(1002);
            l_attentionInfoHistoryParams.setValidUntilBizDate("30");
            l_attentionInfoHistoryParams.setAttentionInfoDivCode("A031");//
            l_attentionInfoHistoryParams.setStandardName("myn");
            l_attentionInfoHistoryParams.setInfoGenerationTimestamp(WEB3DateUtility.getDate("20090108", "yyyyMMdd"));
            l_attentionInfoHistoryParams.setTradeStopRestartTimestamp(WEB3DateUtility.getDate("20090208", "yyyyMMdd"));
            l_attentionInfoHistoryParams.setOrdReceiptRestartTimestamp(WEB3DateUtility.getDate("20090308", "yyyyMMdd"));
            
//            l_attentionInfoHistoryParams.setOldEstimationPrice(11);
//            l_attentionInfoHistoryParams.setNewEstimationPrice(12);
//            l_attentionInfoHistoryParams.setOldLastClosingPrice(13);
//            l_attentionInfoHistoryParams.setNewLastClosingPrice(14);
            l_attentionInfoHistoryParams.setOldBasePrice(15);
//            l_attentionInfoHistoryParams.setNewBasePrice(16);
            
            l_attentionInfoHistoryParams.setOldPriceRangeType("a");
            l_attentionInfoHistoryParams.setNewPriceRangeType("b");
            l_attentionInfoHistoryParams.setOldPriceRangeUnitType("c");
            l_attentionInfoHistoryParams.setNewPriceRangeUnitType("d");
            
//            l_attentionInfoHistoryParams.setOldHighCompPriceRange(17);
//            l_attentionInfoHistoryParams.setNewHighPriceRange(18);
//            l_attentionInfoHistoryParams.setOldLowCompPriceRange(9);
//            l_attentionInfoHistoryParams.setNewLowPriceRange(19);
//            
//            l_attentionInfoHistoryParams.setOldLastClosingPriceUpdq(20);
//            l_attentionInfoHistoryParams.setNewLastClosingPriceUpdq(21);
//            
//            l_attentionInfoHistoryParams.setOldBasePriceUpdq(22);
//            l_attentionInfoHistoryParams.setNewBasePriceUpdq(23);
            
            l_attentionInfoHistoryParams.setFreeFormatTitle("hgfdsa");
            l_attentionInfoHistoryParams.setFreeFormatText("qwerty");
            
            l_attentionInfoHistoryParams.setProcessResultDiv("2");//
            l_attentionInfoHistoryParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_attentionInfoHistoryParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());


            TestDBUtility.deleteAll(MailInfoRow.TYPE);
            MailInfoParams l_mailInfoParams = new MailInfoParams();
            l_mailInfoParams.setInstitutionCode("0D");
            l_mailInfoParams.setSendmailDiv("3103");//
            l_mailInfoParams.setDiscernmentId("2");//
            l_mailInfoParams.setSubject("abc");
            l_mailInfoParams.setLastUpdater("007");
            l_mailInfoParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_mailInfoParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_mailInfoParams.setMailText(
                    "product_code = %%product_code%%" +
                    "product_name = %%product_name%%" +
                    "market_code = %%market_code%%" +
                    "market_name = %%market_name%%" +
                    "info_generation_timestamp = %%info_generation_timestamp%%" +
                    "trade_stop_restart_timestamp = %%trade_stop_restart_timestamp%%" +
                    "ord_receipt_restart_timestamp = %%ord_receipt_restart_timestamp%%" +
                    "old_estimation_price = %%old_estimation_price%%" +
                    "new_estimation_price = %%new_estimation_price%%" +
                    "old_last_closing_price = %%old_last_closing_price%%" +
                    "new_last_closing_price = %%new_last_closing_price%%" +
                    "old_base_price = %%old_base_price%%" +
                    "new_base_price = %%new_base_price%%" +
                    "old_price_range_type = %%old_price_range_type%%" +
                    "new_price_range_type = %%new_price_range_type%%" +
                    "old_price_range_unit_type = %%old_price_range_unit_type%%" +
                    "new_price_range_unit_type = %%new_price_range_unit_type%%" +
                    "old_high_price_range = %%old_high_price_range%%" +
                    "new_high_price_range = %%new_high_price_range%%" +
                    "old_low_price_range = %%old_low_price_range%%" +
                    "new_low_price_range = %%new_low_price_range%%" +
                    "old_last_closing_price_updq = %%old_last_closing_price_updq%%" +
                    "new_last_closing_price_updq = %%new_last_closing_price_updq%%" +
                    "old_base_price_updq = %%old_base_price_updq%%" +
                    "new_base_price_updq = %%new_base_price_updq%%" +
                    "title = %%title%%" +
                    "text = %%text%%" +
                    "process_result_div = %%process_result_div%%");
            TestDBUtility.insertWithDel(l_mailInfoParams);

            TestDBUtility.deleteAll(MailProcRow.TYPE);

            WEB3AdminEquityAttentionInfoNotifyTransactionCallback l_callback =
                l_impl.new WEB3AdminEquityAttentionInfoNotifyTransactionCallback();
            
            Method l_method = WEB3AdminEquityAttentionInfoNotifyTransactionCallback.class.getDeclaredMethod(
                    "insertSendMail",
                    new Class[]{WEB3EquityProduct.class, Market.class, AttentionInfoHistoryParams.class});
            l_method.setAccessible(true);
            Object[] l_obj = {null, l_market, l_attentionInfoHistoryParams};
            l_method.invoke(l_callback, l_obj);

            MailProcRow l_mailProcRow =
                MailProcDao.findRowByInstitutionCodeBranchCodeSendmailDivDiscernmentIdAccountCodeMailId(
                        "0D",
                        "000",
                        "3103",//
                        "2",//
                        "0000000",
                        1001);

            assertEquals("0", l_mailProcRow.getStatus());
            assertEquals(null, l_mailProcRow.getEmailAddress());
            assertEquals(null, l_mailProcRow.getSendEmailAddress());
            assertEquals("abc", l_mailProcRow.getSubject());
            assertEquals(
                    "product_code = " +
                    "product_name = myn" +
                    "market_code = " +
                    "market_name = " +
                    "info_generation_timestamp = 2009/01/08 00:00:00" +
                    "trade_stop_restart_timestamp = 2009/02/08 00:00:00" +
                    "ord_receipt_restart_timestamp = 2009/03/08 00:00:00" +
                    "old_estimation_price = " +
                    "new_estimation_price = " +
                    "old_last_closing_price = " +
                    "new_last_closing_price = " +
                    "old_base_price = 15.0" +
                    "new_base_price = " +
                    "old_price_range_type = a" +
                    "new_price_range_type = b" +
                    "old_price_range_unit_type = c" +
                    "new_price_range_unit_type = d" +
                    "old_high_price_range = " +
                    "new_high_price_range = " +
                    "old_low_price_range = " +
                    "new_low_price_range = " +
                    "old_last_closing_price_updq = " +
                    "new_last_closing_price_updq = " +
                    "old_base_price_updq = " +
                    "new_base_price_updq = " +
                    "title = hgfdsa" +
                    "text = qwerty" +
                    "process_result_div = メール送信済", l_mailProcRow.getMailText());
            assertEquals(BooleanEnum.FALSE, l_mailProcRow.getDeleteFlag());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }*/
    
    /*
     * 注意情報履歴テーブル.注意情報種別＝"フリーフォーマット"の場合
     * 注意情報履歴テーブル.注意情報区分コード＝"新規上場銘柄の初値が付いた場合"の場合, 注意情報履歴テーブル.処理結果区分＝"正常（更新無）"の場合、"2" 
     * 株式銘柄=nullの場合
     * 市場=nullの場合
     * 評価単価（変更前） = null
     * 評価単価（変更後）  = null
     * 基準値（終値）（変更前） = null
     * 基準値（終値）（変更後） = null
     * 基準値（変更前） = null
     * 基準値（変更後） = null
     * 値幅チェック区分（変更前） = null
     * 値幅チェック区分(変更後) = null
     * 値幅区分（変更前） = null
     * 値幅区分（変更後） = null 
     * 制限値幅上限（変更前）  = null
     * 制限値幅上限（変更前）  = null
     * 制限値幅下限（変更前）  = null
     * 制限値幅下限（変更後） = null
     * 基準値（終値）（翌日）（変更前）  = null
     * 基準値（終値）（翌日）（変更後） = null
     * 基準値（翌日）（変更前）  = null
     * 基準値（翌日）（変更前） = null
     */
    /*public void testInsertSendMail_C0017()
    {
        final String STR_METHOD_NAME = "testInsertSendMail_C0017()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setStandardName("xyz");
            TestDBUtility.insertWithDel(l_productParams);
            
            EqtypeProductParams l_eqtypeProductParams = new EqtypeProductParams();
            l_eqtypeProductParams.setProductId(3304148080000L);
            l_eqtypeProductParams.setInstitutionCode("0D");
            l_eqtypeProductParams.setProductCode("1001");
            l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeProductParams.setYearlyBooksClosingDate(WEB3DateUtility.getDate("20090108", "yyyyMMdd"));
            l_eqtypeProductParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_eqtypeProductParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_eqtypeProductParams.setStandardName("xyz");
//            WEB3EquityProduct l_equityProduct = new WEB3EquityProduct(l_eqtypeProductParams);
            
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_marketParams);
            Market l_market = null;
//                new WEB3GentradeMarket(3303L);
            
            AttentionInfoHistoryParams l_attentionInfoHistoryParams = new AttentionInfoHistoryParams();
            l_attentionInfoHistoryParams.setAttentionInfoHistoryId(1001);
            l_attentionInfoHistoryParams.setAttentionInfoType("3");//
            l_attentionInfoHistoryParams.setInstitutionCode("0D");
            l_attentionInfoHistoryParams.setMarketId(1002);
            l_attentionInfoHistoryParams.setValidUntilBizDate("30");
            l_attentionInfoHistoryParams.setAttentionInfoDivCode("A031");//
            l_attentionInfoHistoryParams.setStandardName("myn");
            l_attentionInfoHistoryParams.setInfoGenerationTimestamp(WEB3DateUtility.getDate("20090108", "yyyyMMdd"));
            l_attentionInfoHistoryParams.setTradeStopRestartTimestamp(WEB3DateUtility.getDate("20090208", "yyyyMMdd"));
            l_attentionInfoHistoryParams.setOrdReceiptRestartTimestamp(WEB3DateUtility.getDate("20090308", "yyyyMMdd"));
            
//            l_attentionInfoHistoryParams.setOldEstimationPrice(11);
//            l_attentionInfoHistoryParams.setNewEstimationPrice(12);
//            l_attentionInfoHistoryParams.setOldLastClosingPrice(13);
//            l_attentionInfoHistoryParams.setNewLastClosingPrice(14);
//            l_attentionInfoHistoryParams.setOldBasePrice(15);
//            l_attentionInfoHistoryParams.setNewBasePrice(16);
            
            l_attentionInfoHistoryParams.setOldPriceRangeType("a");
            l_attentionInfoHistoryParams.setNewPriceRangeType("b");
            l_attentionInfoHistoryParams.setOldPriceRangeUnitType("c");
            l_attentionInfoHistoryParams.setNewPriceRangeUnitType("d");
            
            l_attentionInfoHistoryParams.setOldHighCompPriceRange(17);
//            l_attentionInfoHistoryParams.setNewHighPriceRange(18);
            l_attentionInfoHistoryParams.setOldLowCompPriceRange(9);
//            l_attentionInfoHistoryParams.setNewLowPriceRange(19);
//            
//            l_attentionInfoHistoryParams.setOldLastClosingPriceUpdq(20);
//            l_attentionInfoHistoryParams.setNewLastClosingPriceUpdq(21);
//            
//            l_attentionInfoHistoryParams.setOldBasePriceUpdq(22);
//            l_attentionInfoHistoryParams.setNewBasePriceUpdq(23);
            
            l_attentionInfoHistoryParams.setFreeFormatTitle("hgfdsa");
            l_attentionInfoHistoryParams.setFreeFormatText("qwerty");
            
            l_attentionInfoHistoryParams.setProcessResultDiv("2");//
            l_attentionInfoHistoryParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_attentionInfoHistoryParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());


            TestDBUtility.deleteAll(MailInfoRow.TYPE);
            MailInfoParams l_mailInfoParams = new MailInfoParams();
            l_mailInfoParams.setInstitutionCode("0D");
            l_mailInfoParams.setSendmailDiv("3103");//
            l_mailInfoParams.setDiscernmentId("2");//
            l_mailInfoParams.setSubject("abc");
            l_mailInfoParams.setLastUpdater("007");
            l_mailInfoParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_mailInfoParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_mailInfoParams.setMailText(
                    "product_code = %%product_code%%" +
                    "product_name = %%product_name%%" +
                    "market_code = %%market_code%%" +
                    "market_name = %%market_name%%" +
                    "info_generation_timestamp = %%info_generation_timestamp%%" +
                    "trade_stop_restart_timestamp = %%trade_stop_restart_timestamp%%" +
                    "ord_receipt_restart_timestamp = %%ord_receipt_restart_timestamp%%" +
                    "old_estimation_price = %%old_estimation_price%%" +
                    "new_estimation_price = %%new_estimation_price%%" +
                    "old_last_closing_price = %%old_last_closing_price%%" +
                    "new_last_closing_price = %%new_last_closing_price%%" +
                    "old_base_price = %%old_base_price%%" +
                    "new_base_price = %%new_base_price%%" +
                    "old_price_range_type = %%old_price_range_type%%" +
                    "new_price_range_type = %%new_price_range_type%%" +
                    "old_price_range_unit_type = %%old_price_range_unit_type%%" +
                    "new_price_range_unit_type = %%new_price_range_unit_type%%" +
                    "old_high_price_range = %%old_high_price_range%%" +
                    "new_high_price_range = %%new_high_price_range%%" +
                    "old_low_price_range = %%old_low_price_range%%" +
                    "new_low_price_range = %%new_low_price_range%%" +
                    "old_last_closing_price_updq = %%old_last_closing_price_updq%%" +
                    "new_last_closing_price_updq = %%new_last_closing_price_updq%%" +
                    "old_base_price_updq = %%old_base_price_updq%%" +
                    "new_base_price_updq = %%new_base_price_updq%%" +
                    "title = %%title%%" +
                    "text = %%text%%" +
                    "process_result_div = %%process_result_div%%");
            TestDBUtility.insertWithDel(l_mailInfoParams);

            TestDBUtility.deleteAll(MailProcRow.TYPE);

            WEB3AdminEquityAttentionInfoNotifyTransactionCallback l_callback =
                l_impl.new WEB3AdminEquityAttentionInfoNotifyTransactionCallback();
            
            Method l_method = WEB3AdminEquityAttentionInfoNotifyTransactionCallback.class.getDeclaredMethod(
                    "insertSendMail",
                    new Class[]{WEB3EquityProduct.class, Market.class, AttentionInfoHistoryParams.class});
            l_method.setAccessible(true);
            Object[] l_obj = {null, l_market, l_attentionInfoHistoryParams};
            l_method.invoke(l_callback, l_obj);

            MailProcRow l_mailProcRow =
                MailProcDao.findRowByInstitutionCodeBranchCodeSendmailDivDiscernmentIdAccountCodeMailId(
                        "0D",
                        "000",
                        "3103",//
                        "2",//
                        "0000000",
                        1001);

            assertEquals("0", l_mailProcRow.getStatus());
            assertEquals(null, l_mailProcRow.getEmailAddress());
            assertEquals(null, l_mailProcRow.getSendEmailAddress());
            assertEquals("abc", l_mailProcRow.getSubject());
            assertEquals(
                    "product_code = " +
                    "product_name = myn" +
                    "market_code = " +
                    "market_name = " +
                    "info_generation_timestamp = 2009/01/08 00:00:00" +
                    "trade_stop_restart_timestamp = 2009/02/08 00:00:00" +
                    "ord_receipt_restart_timestamp = 2009/03/08 00:00:00" +
                    "old_estimation_price = " +
                    "new_estimation_price = " +
                    "old_last_closing_price = " +
                    "new_last_closing_price = " +
                    "old_base_price = " +
                    "new_base_price = " +
                    "old_price_range_type = a" +
                    "new_price_range_type = b" +
                    "old_price_range_unit_type = c" +
                    "new_price_range_unit_type = d" +
                    "old_high_price_range = " +
                    "new_high_price_range = " +
                    "old_low_price_range = " +
                    "new_low_price_range = " +
                    "old_last_closing_price_updq = " +
                    "new_last_closing_price_updq = " +
                    "old_base_price_updq = " +
                    "new_base_price_updq = " +
                    "title = hgfdsa" +
                    "text = qwerty" +
                    "process_result_div = メール送信済", l_mailProcRow.getMailText());
            assertEquals(BooleanEnum.FALSE, l_mailProcRow.getDeleteFlag());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
//    /*
//     * 注意情報履歴テーブル.注意情報種別＝"フリーフォーマット"の場合
//     * 注意情報履歴テーブル.注意情報区分コード＝"新規上場銘柄の初値が付いた場合"の場合, 注意情報履歴テーブル.処理結果区分＝"管理者一部更新済み"の場合
//     * 株式銘柄=nullの場合
//     */
//    public void testInsertSendMail_C0015()
//    {
//        final String STR_METHOD_NAME = "testInsertSendMail_C0015()";
//        log.entering(STR_METHOD_NAME);
//        
//        try
//        {
//            TestDBUtility.deleteAll(ProductRow.TYPE);
//            ProductParams l_productParams = TestDBUtility.getProductRow();
//            l_productParams.setProductId(3304148080000L);
//            l_productParams.setStandardName("xyz");
//            TestDBUtility.insertWithDel(l_productParams);
//            
//            EqtypeProductParams l_eqtypeProductParams = new EqtypeProductParams();
//            l_eqtypeProductParams.setProductId(3304148080000L);
//            l_eqtypeProductParams.setInstitutionCode("0D");
//            l_eqtypeProductParams.setProductCode("1001");
//            l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
//            l_eqtypeProductParams.setYearlyBooksClosingDate(WEB3DateUtility.getDate("20090108", "yyyyMMdd"));
//            l_eqtypeProductParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_eqtypeProductParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_eqtypeProductParams.setStandardName("xyz");
////            WEB3EquityProduct l_equityProduct = new WEB3EquityProduct(l_eqtypeProductParams);
//            
//            
//            MarketParams l_marketParams = TestDBUtility.getMarketRow();
//            l_marketParams.setMarketId(3303L);
//            TestDBUtility.insertWithDel(l_marketParams);
//            Market l_market = new WEB3GentradeMarket(3303L);
//            
//            AttentionInfoHistoryParams l_attentionInfoHistoryParams = new AttentionInfoHistoryParams();
//            l_attentionInfoHistoryParams.setAttentionInfoHistoryId(1001);
//            l_attentionInfoHistoryParams.setAttentionInfoType("3");//
//            l_attentionInfoHistoryParams.setInstitutionCode("0D");
//            l_attentionInfoHistoryParams.setMarketId(1002);
//            l_attentionInfoHistoryParams.setValidUntilBizDate("30");
//            l_attentionInfoHistoryParams.setAttentionInfoDivCode("A031");//
//            l_attentionInfoHistoryParams.setInfoGenerationTimestamp(WEB3DateUtility.getDate("20090108", "yyyyMMdd"));
//            l_attentionInfoHistoryParams.setProcessResultDiv("4");//
//            l_attentionInfoHistoryParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_attentionInfoHistoryParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
//
//
//            TestDBUtility.deleteAll(MailInfoRow.TYPE);
//            MailInfoParams l_mailInfoParams = new MailInfoParams();
//            l_mailInfoParams.setInstitutionCode("0D");
//            l_mailInfoParams.setSendmailDiv("3103");//
//            l_mailInfoParams.setDiscernmentId("3");//
//            l_mailInfoParams.setSubject("abc");
//            l_mailInfoParams.setLastUpdater("007");
//            l_mailInfoParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_mailInfoParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_mailInfoParams.setMailText("product_code = %%product_code%%, product_name = %%product_name%%, new_low_price_range = %%new_low_price_range%%, old_low_price_range = %%old_low_price_range%%, process_result_div = %%process_result_div%%");
//            TestDBUtility.insertWithDel(l_mailInfoParams);
//
//            TestDBUtility.deleteAll(MailProcRow.TYPE);
//
//            WEB3AdminEquityAttentionInfoNotifyTransactionCallback l_callback =
//                l_impl.new WEB3AdminEquityAttentionInfoNotifyTransactionCallback();
//            
//            Method l_method = WEB3AdminEquityAttentionInfoNotifyTransactionCallback.class.getDeclaredMethod(
//                    "insertSendMail",
//                    new Class[]{WEB3EquityProduct.class, Market.class, AttentionInfoHistoryParams.class});
//            l_method.setAccessible(true);
//            Object[] l_obj = {null, l_market, l_attentionInfoHistoryParams};
//            l_method.invoke(l_callback, l_obj);
//
//            MailProcRow l_mailProcRow =
//                MailProcDao.findRowByInstitutionCodeBranchCodeSendmailDivDiscernmentIdAccountCodeMailId(
//                        "0D",
//                        "000",
//                        "3103",//
//                        "3",//
//                        "0000000",
//                        1001);
//
//            assertEquals("0", l_mailProcRow.getStatus());
//            assertEquals(null, l_mailProcRow.getEmailAddress());
//            assertEquals(null, l_mailProcRow.getSendEmailAddress());
//            assertEquals("abc", l_mailProcRow.getSubject());
//            assertEquals("product_code = , product_name = , new_low_price_range = , old_low_price_range = , process_result_div = 4", l_mailProcRow.getMailText());
//            assertEquals(BooleanEnum.FALSE, l_mailProcRow.getDeleteFlag());
//        }
//        catch (Exception l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//    }
//    /*
//     * 注意情報履歴テーブル.注意情報種別＝"フリーフォーマット"の場合
//     * 注意情報履歴テーブル.注意情報区分コード＝"新規上場銘柄の初値が付いた場合"の場合, 注意情報履歴テーブル.処理結果区分＝"エラー"の場合
//     * 株式銘柄=nullの場合
//     */
//    public void testInsertSendMail_C0022()
//    {
//        final String STR_METHOD_NAME = "testInsertSendMail_C0022()";
//        log.entering(STR_METHOD_NAME);
//        
//        try
//        {
//            TestDBUtility.deleteAll(ProductRow.TYPE);
//            ProductParams l_productParams = TestDBUtility.getProductRow();
//            l_productParams.setProductId(3304148080000L);
//            l_productParams.setStandardName("xyz");
//            TestDBUtility.insertWithDel(l_productParams);
//            
//            EqtypeProductParams l_eqtypeProductParams = new EqtypeProductParams();
//            l_eqtypeProductParams.setProductId(3304148080000L);
//            l_eqtypeProductParams.setInstitutionCode("0D");
//            l_eqtypeProductParams.setProductCode("1001");
//            l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
//            l_eqtypeProductParams.setYearlyBooksClosingDate(WEB3DateUtility.getDate("20090108", "yyyyMMdd"));
//            l_eqtypeProductParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_eqtypeProductParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_eqtypeProductParams.setStandardName("xyz");
////            WEB3EquityProduct l_equityProduct = new WEB3EquityProduct(l_eqtypeProductParams);
//            
//            
//            MarketParams l_marketParams = TestDBUtility.getMarketRow();
//            l_marketParams.setMarketId(3303L);
//            TestDBUtility.insertWithDel(l_marketParams);
//            Market l_market = new WEB3GentradeMarket(3303L);
//            
//            AttentionInfoHistoryParams l_attentionInfoHistoryParams = new AttentionInfoHistoryParams();
//            l_attentionInfoHistoryParams.setAttentionInfoHistoryId(1001);
//            l_attentionInfoHistoryParams.setAttentionInfoType("3");//
//            l_attentionInfoHistoryParams.setInstitutionCode("0D");
//            l_attentionInfoHistoryParams.setMarketId(1002);
//            l_attentionInfoHistoryParams.setValidUntilBizDate("30");
//            l_attentionInfoHistoryParams.setAttentionInfoDivCode("A031");//
//            l_attentionInfoHistoryParams.setInfoGenerationTimestamp(WEB3DateUtility.getDate("20090108", "yyyyMMdd"));
//            l_attentionInfoHistoryParams.setProcessResultDiv("9");//
//            l_attentionInfoHistoryParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_attentionInfoHistoryParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
//
//
//            TestDBUtility.deleteAll(MailInfoRow.TYPE);
//            MailInfoParams l_mailInfoParams = new MailInfoParams();
//            l_mailInfoParams.setInstitutionCode("0D");
//            l_mailInfoParams.setSendmailDiv("3103");//
//            l_mailInfoParams.setDiscernmentId("4");//
//            l_mailInfoParams.setSubject("abc");
//            l_mailInfoParams.setLastUpdater("007");
//            l_mailInfoParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_mailInfoParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_mailInfoParams.setMailText("product_code = %%product_code%%, product_name = %%product_name%%, new_low_price_range = %%new_low_price_range%%, old_low_price_range = %%old_low_price_range%%, process_result_div = %%process_result_div%%");
//            TestDBUtility.insertWithDel(l_mailInfoParams);
//
//            TestDBUtility.deleteAll(MailProcRow.TYPE);
//
//            WEB3AdminEquityAttentionInfoNotifyTransactionCallback l_callback =
//                l_impl.new WEB3AdminEquityAttentionInfoNotifyTransactionCallback();
//            
//            Method l_method = WEB3AdminEquityAttentionInfoNotifyTransactionCallback.class.getDeclaredMethod(
//                    "insertSendMail",
//                    new Class[]{WEB3EquityProduct.class, Market.class, AttentionInfoHistoryParams.class});
//            l_method.setAccessible(true);
//            Object[] l_obj = {null, l_market, l_attentionInfoHistoryParams};
//            l_method.invoke(l_callback, l_obj);
//
//            MailProcRow l_mailProcRow =
//                MailProcDao.findRowByInstitutionCodeBranchCodeSendmailDivDiscernmentIdAccountCodeMailId(
//                        "0D",
//                        "000",
//                        "3103",//
//                        "4",//
//                        "0000000",
//                        1001);
//
//            assertEquals("0", l_mailProcRow.getStatus());
//            assertEquals(null, l_mailProcRow.getEmailAddress());
//            assertEquals(null, l_mailProcRow.getSendEmailAddress());
//            assertEquals("abc", l_mailProcRow.getSubject());
//            assertEquals("product_code = , product_name = , new_low_price_range = , old_low_price_range = , process_result_div = 9", l_mailProcRow.getMailText());
//            assertEquals(BooleanEnum.FALSE, l_mailProcRow.getDeleteFlag());
//        }
//        catch (Exception l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//    }
//    /*
//     * 注意情報履歴テーブル.注意情報種別＝"フリーフォーマット"の場合
//     * 注意情報履歴テーブル.注意情報区分コード＝"フリーフォーマット"の場合
//     * 株式銘柄=nullの場合
//     */
//    public void testInsertSendMail_C0016()
//    {
//        final String STR_METHOD_NAME = "testInsertSendMail_C0016()";
//        log.entering(STR_METHOD_NAME);
//        
//        try
//        {
//            TestDBUtility.deleteAll(ProductRow.TYPE);
//            ProductParams l_productParams = TestDBUtility.getProductRow();
//            l_productParams.setProductId(3304148080000L);
//            l_productParams.setStandardName("xyz");
//            TestDBUtility.insertWithDel(l_productParams);
//            
//            EqtypeProductParams l_eqtypeProductParams = new EqtypeProductParams();
//            l_eqtypeProductParams.setProductId(3304148080000L);
//            l_eqtypeProductParams.setInstitutionCode("0D");
//            l_eqtypeProductParams.setProductCode("1001");
//            l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
//            l_eqtypeProductParams.setYearlyBooksClosingDate(WEB3DateUtility.getDate("20090108", "yyyyMMdd"));
//            l_eqtypeProductParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_eqtypeProductParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_eqtypeProductParams.setStandardName("xyz");
////            WEB3EquityProduct l_equityProduct = new WEB3EquityProduct(l_eqtypeProductParams);
//            
//            
//            MarketParams l_marketParams = TestDBUtility.getMarketRow();
//            l_marketParams.setMarketId(3303L);
//            TestDBUtility.insertWithDel(l_marketParams);
//            Market l_market = new WEB3GentradeMarket(3303L);
//            
//            AttentionInfoHistoryParams l_attentionInfoHistoryParams = new AttentionInfoHistoryParams();
//            l_attentionInfoHistoryParams.setAttentionInfoHistoryId(1001);
//            l_attentionInfoHistoryParams.setAttentionInfoType("3");//
//            l_attentionInfoHistoryParams.setInstitutionCode("0D");
//            l_attentionInfoHistoryParams.setMarketId(1002);
//            l_attentionInfoHistoryParams.setValidUntilBizDate("30");
//            l_attentionInfoHistoryParams.setAttentionInfoDivCode("A081");//
//            l_attentionInfoHistoryParams.setInfoGenerationTimestamp(WEB3DateUtility.getDate("20090108", "yyyyMMdd"));
//            l_attentionInfoHistoryParams.setProcessResultDiv("4");//
//            l_attentionInfoHistoryParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_attentionInfoHistoryParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
//
//
//            TestDBUtility.deleteAll(MailInfoRow.TYPE);
//            MailInfoParams l_mailInfoParams = new MailInfoParams();
//            l_mailInfoParams.setInstitutionCode("0D");
//            l_mailInfoParams.setSendmailDiv("3103");//
//            l_mailInfoParams.setDiscernmentId("1");//
//            l_mailInfoParams.setSubject("abc");
//            l_mailInfoParams.setLastUpdater("007");
//            l_mailInfoParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_mailInfoParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_mailInfoParams.setMailText("product_code = %%product_code%%, product_name = %%product_name%%, new_low_price_range = %%new_low_price_range%%, old_low_price_range = %%old_low_price_range%%, process_result_div = %%process_result_div%%");
//            TestDBUtility.insertWithDel(l_mailInfoParams);
//
//            TestDBUtility.deleteAll(MailProcRow.TYPE);
//
//            WEB3AdminEquityAttentionInfoNotifyTransactionCallback l_callback =
//                l_impl.new WEB3AdminEquityAttentionInfoNotifyTransactionCallback();
//            
//            Method l_method = WEB3AdminEquityAttentionInfoNotifyTransactionCallback.class.getDeclaredMethod(
//                    "insertSendMail",
//                    new Class[]{WEB3EquityProduct.class, Market.class, AttentionInfoHistoryParams.class});
//            l_method.setAccessible(true);
//            Object[] l_obj = {null, l_market, l_attentionInfoHistoryParams};
//            l_method.invoke(l_callback, l_obj);
//
//            MailProcRow l_mailProcRow =
//                MailProcDao.findRowByInstitutionCodeBranchCodeSendmailDivDiscernmentIdAccountCodeMailId(
//                        "0D",
//                        "000",
//                        "3103",//
//                        "1",//
//                        "0000000",
//                        1001);
//
//            assertEquals("0", l_mailProcRow.getStatus());
//            assertEquals(null, l_mailProcRow.getEmailAddress());
//            assertEquals(null, l_mailProcRow.getSendEmailAddress());
//            assertEquals("abc", l_mailProcRow.getSubject());
//            assertEquals("product_code = , product_name = , new_low_price_range = , old_low_price_range = , process_result_div = 4", l_mailProcRow.getMailText());
//            assertEquals(BooleanEnum.FALSE, l_mailProcRow.getDeleteFlag());
//        }
//        catch (Exception l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//    }
//    /*
//     * 注意情報履歴テーブル.注意情報種別＝"フリーフォーマット"の場合
//     * 注意情報履歴テーブル.注意情報区分コード＝"フリーフォーマット"の場合
//     * 注意情報履歴テーブル.処理結果区分＝"エラー"の場合
//     * 株式銘柄=nullの場合
//     */
//    public void testInsertSendMail_C0017()
//    {
//        final String STR_METHOD_NAME = "testInsertSendMail_C0017()";
//        log.entering(STR_METHOD_NAME);
//        
//        try
//        {
//            TestDBUtility.deleteAll(ProductRow.TYPE);
//            ProductParams l_productParams = TestDBUtility.getProductRow();
//            l_productParams.setProductId(3304148080000L);
//            l_productParams.setStandardName("xyz");
//            TestDBUtility.insertWithDel(l_productParams);
//            
//            EqtypeProductParams l_eqtypeProductParams = new EqtypeProductParams();
//            l_eqtypeProductParams.setProductId(3304148080000L);
//            l_eqtypeProductParams.setInstitutionCode("0D");
//            l_eqtypeProductParams.setProductCode("1001");
//            l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
//            l_eqtypeProductParams.setYearlyBooksClosingDate(WEB3DateUtility.getDate("20090108", "yyyyMMdd"));
//            l_eqtypeProductParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_eqtypeProductParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_eqtypeProductParams.setStandardName("xyz");
////            WEB3EquityProduct l_equityProduct = new WEB3EquityProduct(l_eqtypeProductParams);
//            
//            
//            MarketParams l_marketParams = TestDBUtility.getMarketRow();
//            l_marketParams.setMarketId(3303L);
//            TestDBUtility.insertWithDel(l_marketParams);
//            Market l_market = new WEB3GentradeMarket(3303L);
//            
//            AttentionInfoHistoryParams l_attentionInfoHistoryParams = new AttentionInfoHistoryParams();
//            l_attentionInfoHistoryParams.setAttentionInfoHistoryId(1001);
//            l_attentionInfoHistoryParams.setAttentionInfoType("3");//
//            l_attentionInfoHistoryParams.setInstitutionCode("0D");
//            l_attentionInfoHistoryParams.setMarketId(1002);
//            l_attentionInfoHistoryParams.setValidUntilBizDate("30");
//            l_attentionInfoHistoryParams.setAttentionInfoDivCode("A081");//
//            l_attentionInfoHistoryParams.setInfoGenerationTimestamp(WEB3DateUtility.getDate("20090108", "yyyyMMdd"));
//            l_attentionInfoHistoryParams.setProcessResultDiv("9");//
//            l_attentionInfoHistoryParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_attentionInfoHistoryParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_attentionInfoHistoryParams.setNewLowPriceRange(1);
//            l_attentionInfoHistoryParams.setNewBasePrice(2);
//            l_attentionInfoHistoryParams.setNewHighPriceRange(3);
//
//
//            TestDBUtility.deleteAll(MailInfoRow.TYPE);
//            MailInfoParams l_mailInfoParams = new MailInfoParams();
//            l_mailInfoParams.setInstitutionCode("0D");
//            l_mailInfoParams.setSendmailDiv("3103");//
//            l_mailInfoParams.setDiscernmentId("2");//
//            l_mailInfoParams.setSubject("abc");
//            l_mailInfoParams.setLastUpdater("007");
//            l_mailInfoParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_mailInfoParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_mailInfoParams.setMailText("product_code = %%product_code%%, product_name = %%product_name%%, new_low_price_range = %%new_low_price_range%%, old_low_price_range = %%old_low_price_range%%, process_result_div = %%process_result_div%%, new_base_price = %%new_base_price%%, new_high_price_range = %%new_high_price_range%%, old_base_price = %%old_base_price%%");
//            TestDBUtility.insertWithDel(l_mailInfoParams);
//
//            TestDBUtility.deleteAll(MailProcRow.TYPE);
//
//            WEB3AdminEquityAttentionInfoNotifyTransactionCallback l_callback =
//                l_impl.new WEB3AdminEquityAttentionInfoNotifyTransactionCallback();
//            
//            Method l_method = WEB3AdminEquityAttentionInfoNotifyTransactionCallback.class.getDeclaredMethod(
//                    "insertSendMail",
//                    new Class[]{WEB3EquityProduct.class, Market.class, AttentionInfoHistoryParams.class});
//            l_method.setAccessible(true);
//            Object[] l_obj = {null, l_market, l_attentionInfoHistoryParams};
//            l_method.invoke(l_callback, l_obj);
//
//            MailProcRow l_mailProcRow =
//                MailProcDao.findRowByInstitutionCodeBranchCodeSendmailDivDiscernmentIdAccountCodeMailId(
//                        "0D",
//                        "000",
//                        "3103",//
//                        "2",//
//                        "0000000",
//                        1001);
//
//            assertEquals("0", l_mailProcRow.getStatus());
//            assertEquals(null, l_mailProcRow.getEmailAddress());
//            assertEquals(null, l_mailProcRow.getSendEmailAddress());
//            assertEquals("abc", l_mailProcRow.getSubject());
//            assertEquals("product_code = , product_name = , new_low_price_range = 1.0, old_low_price_range = , process_result_div = 9, new_base_price = 2.0, new_high_price_range = 3.0, old_base_price = ", l_mailProcRow.getMailText());
//            assertEquals(BooleanEnum.FALSE, l_mailProcRow.getDeleteFlag());
//        }
//        catch (Exception l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//    }
//    /*
//     * 注意情報履歴テーブル.注意情報種別＝"フリーフォーマット"の場合
//     * 注意情報履歴テーブル.注意情報区分コード＝"フリーフォーマット"の場合
//     * 株式銘柄=nullの場合
//     */
//    public void testInsertSendMail_C0018()
//    {
//        final String STR_METHOD_NAME = "testInsertSendMail_C0018()";
//        log.entering(STR_METHOD_NAME);
//        
//        try
//        {
//            TestDBUtility.deleteAll(ProductRow.TYPE);
//            ProductParams l_productParams = TestDBUtility.getProductRow();
//            l_productParams.setProductId(3304148080000L);
//            l_productParams.setStandardName("xyz");
//            TestDBUtility.insertWithDel(l_productParams);
//            
//            EqtypeProductParams l_eqtypeProductParams = new EqtypeProductParams();
//            l_eqtypeProductParams.setProductId(3304148080000L);
//            l_eqtypeProductParams.setInstitutionCode("0D");
//            l_eqtypeProductParams.setProductCode("1001");
//            l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
//            l_eqtypeProductParams.setYearlyBooksClosingDate(WEB3DateUtility.getDate("20090108", "yyyyMMdd"));
//            l_eqtypeProductParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_eqtypeProductParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_eqtypeProductParams.setStandardName("xyz");
////            WEB3EquityProduct l_equityProduct = new WEB3EquityProduct(l_eqtypeProductParams);
//            
//            
//            MarketParams l_marketParams = TestDBUtility.getMarketRow();
//            l_marketParams.setMarketId(3303L);
//            TestDBUtility.insertWithDel(l_marketParams);
//            Market l_market = new WEB3GentradeMarket(3303L);
//            
//            AttentionInfoHistoryParams l_attentionInfoHistoryParams = new AttentionInfoHistoryParams();
//            l_attentionInfoHistoryParams.setAttentionInfoHistoryId(1001);
//            l_attentionInfoHistoryParams.setAttentionInfoType("3");//
//            l_attentionInfoHistoryParams.setInstitutionCode("0D");
//            l_attentionInfoHistoryParams.setMarketId(1002);
//            l_attentionInfoHistoryParams.setValidUntilBizDate("30");
//            l_attentionInfoHistoryParams.setAttentionInfoDivCode("A081");//
//            l_attentionInfoHistoryParams.setInfoGenerationTimestamp(WEB3DateUtility.getDate("20090108", "yyyyMMdd"));
//            l_attentionInfoHistoryParams.setProcessResultDiv("4");//
//            l_attentionInfoHistoryParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_attentionInfoHistoryParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_attentionInfoHistoryParams.setOldBasePrice(1);
//
//
//            TestDBUtility.deleteAll(MailInfoRow.TYPE);
//            MailInfoParams l_mailInfoParams = new MailInfoParams();
//            l_mailInfoParams.setInstitutionCode("0D");
//            l_mailInfoParams.setSendmailDiv("3103");//
//            l_mailInfoParams.setDiscernmentId("1");//
//            l_mailInfoParams.setSubject("abc");
//            l_mailInfoParams.setLastUpdater("007");
//            l_mailInfoParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_mailInfoParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_mailInfoParams.setMailText("product_code = %%product_code%%, product_name = %%product_name%%, new_low_price_range = %%new_low_price_range%%, old_low_price_range = %%old_low_price_range%%, process_result_div = %%process_result_div%%, new_base_price = %%new_base_price%%, new_high_price_range = %%new_high_price_range%%, old_base_price = %%old_base_price%%");
//            TestDBUtility.insertWithDel(l_mailInfoParams);
//
//            TestDBUtility.deleteAll(MailProcRow.TYPE);
//
//            WEB3AdminEquityAttentionInfoNotifyTransactionCallback l_callback =
//                l_impl.new WEB3AdminEquityAttentionInfoNotifyTransactionCallback();
//            
//            Method l_method = WEB3AdminEquityAttentionInfoNotifyTransactionCallback.class.getDeclaredMethod(
//                    "insertSendMail",
//                    new Class[]{WEB3EquityProduct.class, Market.class, AttentionInfoHistoryParams.class});
//            l_method.setAccessible(true);
//            Object[] l_obj = {null, l_market, l_attentionInfoHistoryParams};
//            l_method.invoke(l_callback, l_obj);
//
//            MailProcRow l_mailProcRow =
//                MailProcDao.findRowByInstitutionCodeBranchCodeSendmailDivDiscernmentIdAccountCodeMailId(
//                        "0D",
//                        "000",
//                        "3103",//
//                        "1",//
//                        "0000000",
//                        1001);
//
//            assertEquals("0", l_mailProcRow.getStatus());
//            assertEquals(null, l_mailProcRow.getEmailAddress());
//            assertEquals(null, l_mailProcRow.getSendEmailAddress());
//            assertEquals("abc", l_mailProcRow.getSubject());
//            assertEquals("product_code = , product_name = , new_low_price_range = , old_low_price_range = , process_result_div = 4, new_base_price = , new_high_price_range = , old_base_price = 1.0", l_mailProcRow.getMailText());
//            assertEquals(BooleanEnum.FALSE, l_mailProcRow.getDeleteFlag());
//        }
//        catch (Exception l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//    }
//    /*
//     * 注意情報履歴テーブル.注意情報種別＝"フリーフォーマット"の場合
//     * 注意情報履歴テーブル.注意情報区分コード＝"フリーフォーマット"の場合
//     * 株式銘柄=nullの場合
//     * メール.getメール本文() = null 
//     */
//    public void testInsertSendMail_C0019()
//    {
//        final String STR_METHOD_NAME = "testInsertSendMail_C0019()";
//        log.entering(STR_METHOD_NAME);
//        
//        try
//        {
//            TestDBUtility.deleteAll(ProductRow.TYPE);
//            ProductParams l_productParams = TestDBUtility.getProductRow();
//            l_productParams.setProductId(3304148080000L);
//            l_productParams.setStandardName("xyz");
//            TestDBUtility.insertWithDel(l_productParams);
//            
//            EqtypeProductParams l_eqtypeProductParams = new EqtypeProductParams();
//            l_eqtypeProductParams.setProductId(3304148080000L);
//            l_eqtypeProductParams.setInstitutionCode("0D");
//            l_eqtypeProductParams.setProductCode("1001");
//            l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
//            l_eqtypeProductParams.setYearlyBooksClosingDate(WEB3DateUtility.getDate("20090108", "yyyyMMdd"));
//            l_eqtypeProductParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_eqtypeProductParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_eqtypeProductParams.setStandardName("xyz");
////            WEB3EquityProduct l_equityProduct = new WEB3EquityProduct(l_eqtypeProductParams);
//            
//            
//            MarketParams l_marketParams = TestDBUtility.getMarketRow();
//            l_marketParams.setMarketId(3303L);
//            TestDBUtility.insertWithDel(l_marketParams);
//            Market l_market = new WEB3GentradeMarket(3303L);
//            
//            AttentionInfoHistoryParams l_attentionInfoHistoryParams = new AttentionInfoHistoryParams();
//            l_attentionInfoHistoryParams.setAttentionInfoHistoryId(1001);
//            l_attentionInfoHistoryParams.setAttentionInfoType("3");//
//            l_attentionInfoHistoryParams.setInstitutionCode("0D");
//            l_attentionInfoHistoryParams.setMarketId(1002);
//            l_attentionInfoHistoryParams.setValidUntilBizDate("30");
//            l_attentionInfoHistoryParams.setAttentionInfoDivCode("A081");//
//            l_attentionInfoHistoryParams.setInfoGenerationTimestamp(WEB3DateUtility.getDate("20090108", "yyyyMMdd"));
//            l_attentionInfoHistoryParams.setProcessResultDiv("4");//
//            l_attentionInfoHistoryParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_attentionInfoHistoryParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
//
//            TestDBUtility.deleteAll(MailInfoRow.TYPE);
//            MailInfoParams l_mailInfoParams = new MailInfoParams();
//            l_mailInfoParams.setInstitutionCode("0D");
//            l_mailInfoParams.setSendmailDiv("3103");//
//            l_mailInfoParams.setDiscernmentId("1");//
//            l_mailInfoParams.setSubject("abc");
//            l_mailInfoParams.setLastUpdater("007");
//            l_mailInfoParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_mailInfoParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
////            l_mailInfoParams.setMailText("product_code = %%product_code%%, product_name = %%product_name%%, new_low_price_range = %%new_low_price_range%%, old_low_price_range = %%old_low_price_range%%, process_result_div = %%process_result_div%%");
//            TestDBUtility.insertWithDel(l_mailInfoParams);
//
//            TestDBUtility.deleteAll(MailProcRow.TYPE);
//
//            WEB3AdminEquityAttentionInfoNotifyTransactionCallback l_callback =
//                l_impl.new WEB3AdminEquityAttentionInfoNotifyTransactionCallback();
//            
//            Method l_method = WEB3AdminEquityAttentionInfoNotifyTransactionCallback.class.getDeclaredMethod(
//                    "insertSendMail",
//                    new Class[]{WEB3EquityProduct.class, Market.class, AttentionInfoHistoryParams.class});
//            l_method.setAccessible(true);
//            Object[] l_obj = {null, l_market, l_attentionInfoHistoryParams};
//            l_method.invoke(l_callback, l_obj);
//
//            MailProcRow l_mailProcRow =
//                MailProcDao.findRowByInstitutionCodeBranchCodeSendmailDivDiscernmentIdAccountCodeMailId(
//                        "0D",
//                        "000",
//                        "3103",//
//                        "1",//
//                        "0000000",
//                        1001);
//
//            assertEquals("0", l_mailProcRow.getStatus());
//            assertEquals(null, l_mailProcRow.getEmailAddress());
//            assertEquals(null, l_mailProcRow.getSendEmailAddress());
//            assertEquals("abc", l_mailProcRow.getSubject());
//            assertEquals(null, l_mailProcRow.getMailText());
//            assertEquals(BooleanEnum.FALSE, l_mailProcRow.getDeleteFlag());
//        }
//        catch (Exception l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//    }
//    /*
//     * 注意情報履歴テーブル.注意情報種別＝"フリーフォーマット"の場合
//     * 注意情報履歴テーブル.注意情報区分コード＝"フリーフォーマット"の場合
//     * 株式銘柄=nullの場合
//     */
//    public void testInsertSendMail_C0020()
//    {
//        final String STR_METHOD_NAME = "testInsertSendMail_C0020()";
//        log.entering(STR_METHOD_NAME);
//        
//        try
//        {
//            TestDBUtility.deleteAll(ProductRow.TYPE);
//            ProductParams l_productParams = TestDBUtility.getProductRow();
//            l_productParams.setProductId(3304148080000L);
//            l_productParams.setStandardName("xyz");
//            TestDBUtility.insertWithDel(l_productParams);
//            
//            EqtypeProductParams l_eqtypeProductParams = new EqtypeProductParams();
//            l_eqtypeProductParams.setProductId(3304148080000L);
//            l_eqtypeProductParams.setInstitutionCode("0D");
//            l_eqtypeProductParams.setProductCode("1001");
//            l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
//            l_eqtypeProductParams.setYearlyBooksClosingDate(WEB3DateUtility.getDate("20090108", "yyyyMMdd"));
//            l_eqtypeProductParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_eqtypeProductParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_eqtypeProductParams.setStandardName("xyz");
////            WEB3EquityProduct l_equityProduct = new WEB3EquityProduct(l_eqtypeProductParams);
//            
//            
//            MarketParams l_marketParams = TestDBUtility.getMarketRow();
//            l_marketParams.setMarketId(3303L);
//            TestDBUtility.insertWithDel(l_marketParams);
//            Market l_market = new WEB3GentradeMarket(3303L);
//            
//            AttentionInfoHistoryParams l_attentionInfoHistoryParams = new AttentionInfoHistoryParams();
//            l_attentionInfoHistoryParams.setAttentionInfoHistoryId(1001);
//            l_attentionInfoHistoryParams.setAttentionInfoType("3");//
//            l_attentionInfoHistoryParams.setInstitutionCode("0D");
//            l_attentionInfoHistoryParams.setMarketId(1002);
//            l_attentionInfoHistoryParams.setValidUntilBizDate("30");
//            l_attentionInfoHistoryParams.setAttentionInfoDivCode("A081");//
//            l_attentionInfoHistoryParams.setInfoGenerationTimestamp(WEB3DateUtility.getDate("20090108", "yyyyMMdd"));
//            l_attentionInfoHistoryParams.setProcessResultDiv("4");//
//            l_attentionInfoHistoryParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_attentionInfoHistoryParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_attentionInfoHistoryParams.setOldBasePrice(1);
//            l_attentionInfoHistoryParams.setOldHighCompPriceRange(0);
//            l_attentionInfoHistoryParams.setOldLowCompPriceRange(0);
//
//
//            TestDBUtility.deleteAll(MailInfoRow.TYPE);
//            MailInfoParams l_mailInfoParams = new MailInfoParams();
//            l_mailInfoParams.setInstitutionCode("0D");
//            l_mailInfoParams.setSendmailDiv("3103");//
//            l_mailInfoParams.setDiscernmentId("1");//
//            l_mailInfoParams.setSubject("abc");
//            l_mailInfoParams.setLastUpdater("007");
//            l_mailInfoParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_mailInfoParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_mailInfoParams.setMailText("product_code = %%product_code%%, product_name = %%product_name%%, new_low_price_range = %%new_low_price_range%%, old_low_price_range = %%old_low_price_range%%, process_result_div = %%process_result_div%%, new_base_price = %%new_base_price%%, new_high_price_range = %%new_high_price_range%%, old_base_price = %%old_base_price%%");
//            TestDBUtility.insertWithDel(l_mailInfoParams);
//
//            TestDBUtility.deleteAll(MailProcRow.TYPE);
//
//            WEB3AdminEquityAttentionInfoNotifyTransactionCallback l_callback =
//                l_impl.new WEB3AdminEquityAttentionInfoNotifyTransactionCallback();
//            
//            Method l_method = WEB3AdminEquityAttentionInfoNotifyTransactionCallback.class.getDeclaredMethod(
//                    "insertSendMail",
//                    new Class[]{WEB3EquityProduct.class, Market.class, AttentionInfoHistoryParams.class});
//            l_method.setAccessible(true);
//            Object[] l_obj = {null, l_market, l_attentionInfoHistoryParams};
//            l_method.invoke(l_callback, l_obj);
//
//            MailProcRow l_mailProcRow =
//                MailProcDao.findRowByInstitutionCodeBranchCodeSendmailDivDiscernmentIdAccountCodeMailId(
//                        "0D",
//                        "000",
//                        "3103",//
//                        "1",//
//                        "0000000",
//                        1001);
//
//            assertEquals("0", l_mailProcRow.getStatus());
//            assertEquals(null, l_mailProcRow.getEmailAddress());
//            assertEquals(null, l_mailProcRow.getSendEmailAddress());
//            assertEquals("abc", l_mailProcRow.getSubject());
//            assertEquals("product_code = , product_name = , new_low_price_range = , old_low_price_range = 1.0, process_result_div = 4, new_base_price = , new_high_price_range = , old_base_price = 1.0", l_mailProcRow.getMailText());
//            assertEquals(BooleanEnum.FALSE, l_mailProcRow.getDeleteFlag());
//        }
//        catch (Exception l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//    }
//    /*
//     * 注意情報履歴テーブル.注意情報種別＝"フリーフォーマット"の場合
//     * 注意情報履歴テーブル.注意情報区分コード＝"フリーフォーマット"の場合
//     * 株式銘柄=nullの場合
//     */
//    public void testInsertSendMail_C0021()
//    {
//        final String STR_METHOD_NAME = "testInsertSendMail_C0021()";
//        log.entering(STR_METHOD_NAME);
//        
//        try
//        {
//            TestDBUtility.deleteAll(ProductRow.TYPE);
//            ProductParams l_productParams = TestDBUtility.getProductRow();
//            l_productParams.setProductId(3304148080000L);
//            l_productParams.setStandardName("xyz");
//            TestDBUtility.insertWithDel(l_productParams);
//            
//            EqtypeProductParams l_eqtypeProductParams = new EqtypeProductParams();
//            l_eqtypeProductParams.setProductId(3304148080000L);
//            l_eqtypeProductParams.setInstitutionCode("0D");
//            l_eqtypeProductParams.setProductCode("1001");
//            l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
//            l_eqtypeProductParams.setYearlyBooksClosingDate(WEB3DateUtility.getDate("20090108", "yyyyMMdd"));
//            l_eqtypeProductParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_eqtypeProductParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_eqtypeProductParams.setStandardName("xyz");
////            WEB3EquityProduct l_equityProduct = new WEB3EquityProduct(l_eqtypeProductParams);
//            
//            
//            MarketParams l_marketParams = TestDBUtility.getMarketRow();
//            l_marketParams.setMarketId(3303L);
//            TestDBUtility.insertWithDel(l_marketParams);
//            Market l_market = new WEB3GentradeMarket(3303L);
//            
//            AttentionInfoHistoryParams l_attentionInfoHistoryParams = new AttentionInfoHistoryParams();
//            l_attentionInfoHistoryParams.setAttentionInfoHistoryId(1001);
//            l_attentionInfoHistoryParams.setAttentionInfoType("3");//
//            l_attentionInfoHistoryParams.setInstitutionCode("0D");
//            l_attentionInfoHistoryParams.setMarketId(1002);
//            l_attentionInfoHistoryParams.setValidUntilBizDate("30");
//            l_attentionInfoHistoryParams.setAttentionInfoDivCode("A081");//
//            l_attentionInfoHistoryParams.setInfoGenerationTimestamp(WEB3DateUtility.getDate("20090108", "yyyyMMdd"));
//            l_attentionInfoHistoryParams.setProcessResultDiv("4");//
//            l_attentionInfoHistoryParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_attentionInfoHistoryParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_attentionInfoHistoryParams.setOldBasePrice(1);
//            l_attentionInfoHistoryParams.setOldHighCompPriceRange(-1);
//            l_attentionInfoHistoryParams.setOldLowCompPriceRange(1);
//
//
//            TestDBUtility.deleteAll(MailInfoRow.TYPE);
//            MailInfoParams l_mailInfoParams = new MailInfoParams();
//            l_mailInfoParams.setInstitutionCode("0D");
//            l_mailInfoParams.setSendmailDiv("3103");//
//            l_mailInfoParams.setDiscernmentId("1");//
//            l_mailInfoParams.setSubject("abc");
//            l_mailInfoParams.setLastUpdater("007");
//            l_mailInfoParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_mailInfoParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_mailInfoParams.setMailText("product_code = %%product_code%%, product_name = %%product_name%%, new_low_price_range = %%new_low_price_range%%, old_low_price_range = %%old_low_price_range%%, process_result_div = %%process_result_div%%, new_base_price = %%new_base_price%%, new_high_price_range = %%new_high_price_range%%, old_base_price = %%old_base_price%%, old_high_price_range = %%old_high_price_range%%");
//            TestDBUtility.insertWithDel(l_mailInfoParams);
//
//            TestDBUtility.deleteAll(MailProcRow.TYPE);
//
//            WEB3AdminEquityAttentionInfoNotifyTransactionCallback l_callback =
//                l_impl.new WEB3AdminEquityAttentionInfoNotifyTransactionCallback();
//            
//            Method l_method = WEB3AdminEquityAttentionInfoNotifyTransactionCallback.class.getDeclaredMethod(
//                    "insertSendMail",
//                    new Class[]{WEB3EquityProduct.class, Market.class, AttentionInfoHistoryParams.class});
//            l_method.setAccessible(true);
//            Object[] l_obj = {null, l_market, l_attentionInfoHistoryParams};
//            l_method.invoke(l_callback, l_obj);
//
//            MailProcRow l_mailProcRow =
//                MailProcDao.findRowByInstitutionCodeBranchCodeSendmailDivDiscernmentIdAccountCodeMailId(
//                        "0D",
//                        "000",
//                        "3103",//
//                        "1",//
//                        "0000000",
//                        1001);
//
//            assertEquals("0", l_mailProcRow.getStatus());
//            assertEquals(null, l_mailProcRow.getEmailAddress());
//            assertEquals(null, l_mailProcRow.getSendEmailAddress());
//            assertEquals("abc", l_mailProcRow.getSubject());
//            assertEquals("product_code = , product_name = , new_low_price_range = , old_low_price_range = , process_result_div = 4, new_base_price = , new_high_price_range = , old_base_price = 1.0, old_high_price_range = ", l_mailProcRow.getMailText());
//            assertEquals(BooleanEnum.FALSE, l_mailProcRow.getDeleteFlag());
//        }
//        catch (Exception l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//    }
   
    /*
     * パラメータ値不正。
     * WEB3ErrorCatalog.SYSTEM_ERROR_80017
     */
    public void testExecute_C0001()
    {
        final String STR_METHOD_NAME = "testExecute_C0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
//            WEB3AdminEquityAttentionInfoNotifyRequest l_request = new WEB3AdminEquityAttentionInfoNotifyRequest();
            
            l_impl.execute(null);
            
            fail();
            
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    /*
     * パラメータタイプ不正。
     * WEB3ErrorCatalog.SYSTEM_ERROR_80018
     */
    public void testExecute_C0002()
    {
        final String STR_METHOD_NAME = "testExecute_C0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3AdminEquityForcedSettleOrderApproveMainRequest l_request = new WEB3AdminEquityForcedSettleOrderApproveMainRequest();
            
            l_impl.execute(l_request);
            
            fail();
            
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * "AXQY1"(MAXAS-AP：注意情報（売停情報）通知)固定
     * 銘柄テーブル != null
     * 処理区分 = 1：処理済
     */
    public void test_execute_0001()
    {
        final String STR_METHOD_NAME = "test_execute_0001()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityAttentionInfoNotifyRequest l_request = new WEB3AdminEquityAttentionInfoNotifyRequest();
        WEB3AdminEquityAttentionInfoNotifyServiceImpl l_service = new WEB3AdminEquityAttentionInfoNotifyServiceImpl();
        try
        {
            TestDBUtility.deleteAllAndCommit(InstitutionParams.TYPE);
            InstitutionParams l_institution = TestDBUtility.getInstitutionRow();
            l_institution.setInstitutionCode("0D");
            TestDBUtility.insertWithDelAndCommit(l_institution);
            
            TestDBUtility.deleteAllAndCommit(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setInstitutionCode("0D");
            l_marketParams.setMarketCode("9");
            l_marketParams.setSonarMarketCode("5");
            TestDBUtility.insertWithDelAndCommit(l_marketParams);
            
            TestDBUtility.deleteAllAndCommit(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setInstitutionCode("0D");
            l_productParams.setProductId(111111111111L);
            l_productParams.setStandardName("テルス");
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDelAndCommit(l_productParams);
            
            TestDBUtility.deleteAllAndCommit(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(1006160060005L);
            l_tradedProductParams.setInstitutionCode("0D");
            l_tradedProductParams.setProductId(111111111111L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDelAndCommit(l_tradedProductParams);
            
            TestDBUtility.deleteAllAndCommit(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setInstitutionCode("0D");
            l_eqtypeProductParams.setProductId(111111111111L);
            l_eqtypeProductParams.setStandardName("シンセンテルス");
            l_eqtypeProductParams.setProductCode("11111");
            TestDBUtility.insertWithDelAndCommit(l_eqtypeProductParams);
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            
            TestDBUtility.deleteAllAndCommit(EqtypeTradedProductParams.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setInstitutionCode("0D");
            l_eqtypeTradedProductParams.setProductId(111111111111L);
            l_eqtypeTradedProductParams.setTradedProductId(1006160060005L);
            l_eqtypeTradedProductParams.setValidUntilBizDate(WEB3DateUtility.formatDate(l_finApp.getTradingSystem().getBizDate(), WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_eqtypeTradedProductParams.setMarketId(3303L);
            //基準値
            l_eqtypeTradedProductParams.setBasePrice(12);
            //強制値幅（上限値）
            l_eqtypeTradedProductParams.setHighCompulsivePriceRange(15);
            //強制値幅（下限値）
            l_eqtypeTradedProductParams.setLowCompulsivePriceRange(18);
            TestDBUtility.insertWithDelAndCommit(l_eqtypeTradedProductParams);
            
            //注意情報通知キューテーブル
            TestDBUtility.deleteAllAndCommit(HostAttentionInfoNotifyParams.TYPE);
            HostAttentionInfoNotifyParams l_hostAttentionInfoNotifyParams = new HostAttentionInfoNotifyParams();
            //データコード
            //"AXQY1"(MAXAS-AP：注意情報（売停情報）通知)固定
            l_hostAttentionInfoNotifyParams.setRequestCode("AXQY1");
            l_hostAttentionInfoNotifyParams.setStatus("0");
            l_hostAttentionInfoNotifyParams.setInstitutionCode("0D");
            l_hostAttentionInfoNotifyParams.setSonarMarketCode("5");
            l_hostAttentionInfoNotifyParams.setProductCode("11111");
            l_hostAttentionInfoNotifyParams.setInfoGenerationTimestamp(Calendar.getInstance().getTime());
            //注意情報区分コード
            l_hostAttentionInfoNotifyParams.setAttentionInfoDivCode("A001");
            //基準値（変更後）
            l_hostAttentionInfoNotifyParams.setBasePrice(19);
            //制限値幅上限
            l_hostAttentionInfoNotifyParams.setHighPriceRange(25);
            //制限値幅下限
            l_hostAttentionInfoNotifyParams.setLowPriceRange(15);
            //情報発生日時
            l_hostAttentionInfoNotifyParams.setInfoGenerationTimestamp(Calendar.getInstance().getTime());
            //注文受付再開日時
            l_hostAttentionInfoNotifyParams.setOrdReceiptRestartTimestamp(Calendar.getInstance().getTime());
            //売買停止日時／売買再開日時
            l_hostAttentionInfoNotifyParams.setTradeStopRestartTimestamp(Calendar.getInstance().getTime());
//            l_hostAttentionInfoNotifyParams.setStatus("s");
            TestDBUtility.insertWithDelAndCommit(l_hostAttentionInfoNotifyParams);
            
            TestDBUtility.deleteAllAndCommit(MailInfoParams.TYPE);
            MailInfoParams l_mailInfoParams = new MailInfoParams();
            l_mailInfoParams.setInstitutionCode("0D");
            l_mailInfoParams.setSendmailDiv("3101");
            l_mailInfoParams.setDiscernmentId("1");
            l_mailInfoParams.setSubject("あいうえおabced1258");
            l_mailInfoParams.setLastUpdater("0213");
            l_mailInfoParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_mailInfoParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDelAndCommit(l_mailInfoParams);
            TestDBUtility.deleteAllAndCommit(AttentionInfoHistoryParams.TYPE);
            
            l_service.execute(l_request);
            
            Object[] l_values1 = {"AXQY1", "5", "1"};
            QueryProcessor l_processor1 = Processors.getDefaultProcessor();
            List l_lisHostAttentionInfoNotifyRows = l_processor1.doFindAllQuery(
                    HostAttentionInfoNotifyParams.TYPE,
                    "request_code = ? and " +
                    "sonar_market_code = ? and " +
                    "status = ?",
                    l_values1);
            
            assertEquals("1", l_lisHostAttentionInfoNotifyRows.size() + "");
            //注意情報通知キュー.データコード＝"注意情報（売停情報）通知"の場合
            //　@"1：売停情報"
            Object[] l_values = {"1", "0D", new Long(3303L), "A001"};
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            List l_lisAttentionInfoHistoryRows = l_processor.doFindAllQuery(
                AttentionInfoHistoryRow.TYPE,
                "attention_info_type = ? and " +
                "institution_code = ? and " +
                "market_id = ? and " +
                "attention_info_div_code = ? ",
                l_values);
            
            AttentionInfoHistoryRow l_row = (AttentionInfoHistoryRow)l_lisAttentionInfoHistoryRows.get(0);
//            assertEquals("", l_row.getAttentionInfoHistoryId() + "");
            assertEquals("1", l_row.getAttentionInfoType());
            assertEquals("0D", l_row.getInstitutionCode());
            assertEquals("111111111111", l_row.getProductId() + "");
            assertEquals("シンセンテルス", l_row.getStandardName() + "");
            assertEquals("3303", l_row.getMarketId() + "");
            assertEquals(
                WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(), "yyyyMMdd"),
                WEB3DateUtility.formatDate(
                WEB3DateUtility.getDate(l_row.getValidUntilBizDate(), "yyyyMMdd"),"yyyyMMdd"));
            assertEquals("A001", l_row.getAttentionInfoDivCode());
//            
//            assertEquals("0.0", l_row.getOldBasePrice() + "");
//            assertEquals("0.0", l_row.getOldHighCompPriceRange() + "");
//            assertEquals("0.0", l_row.getOldLowCompPriceRange() + "");
//            assertEquals("0.0", l_row.getNewBasePrice() + "");
////            assertEquals("12", l_row.getOldBasePrice() + "");
////            assertEquals("15", l_row.getOldHighCompPriceRange() + "");
////            assertEquals("18", l_row.getOldLowCompPriceRange() + "");
////            assertEquals("19", l_row.getNewBasePrice() + "");
////            //注意情報通知キュー.制限値幅上限-注意情報通知キュー.基準値
////            assertEquals("6", l_row.getNewHighCompPriceRange() + "");
////            //注意情報通知キュー.基準値-注意情報通知キュー.制限値幅下限
////            assertEquals("", l_row.getNewLowCompPriceRange() + "");
//            
//            //注意情報通知キュー.制限値幅上限-注意情報通知キュー.基準値
//            assertEquals("0.0", l_row.getNewHighPriceRange() + "");
//            //注意情報通知キュー.基準値-注意情報通知キュー.制限値幅下限
//            assertEquals("0.0", l_row.getNewLowPriceRange() + "");
//            //
//            assertNull(l_row.getFreeFormatTitle());
//            assertNull(l_row.getFreeFormatText());
//            
//            assertEquals(
//                WEB3DateUtility.formatDate(Calendar.getInstance().getTime(), "yyyyMMdd"),
//                WEB3DateUtility.formatDate(l_row.getInfoGenerationTimestamp(), "yyyyMMdd"));
//            assertEquals(
//                WEB3DateUtility.formatDate(Calendar.getInstance().getTime(), "yyyyMMdd"),
//                WEB3DateUtility.formatDate(l_row.getOrdReceiptRestartTimestamp(), "yyyyMMdd"));
//            assertEquals(
//                WEB3DateUtility.formatDate(Calendar.getInstance().getTime(), "yyyyMMdd"),
//                WEB3DateUtility.formatDate(l_row.getTradeStopRestartTimestamp(), "yyyyMMdd"));
//            
//            assertEquals("1", l_row.getProcessResultDiv());
//            
//            assertEquals(
//                WEB3DateUtility.formatDate(Calendar.getInstance().getTime(), "yyyyMMdd"),
//                WEB3DateUtility.formatDate(l_row.getCreatedTimestamp(), "yyyyMMdd"));
//            assertEquals(
//                WEB3DateUtility.formatDate(Calendar.getInstance().getTime(), "yyyyMMdd"),
//                WEB3DateUtility.formatDate(l_row.getLastUpdatedTimestamp(), "yyyyMMdd"));

        } catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    
    /**
     * "AXQY1"(MAXAS-AP：注意情報（売停情報）通知)固定
     * 銘柄テーブル NotFoundException
     * 処理区分 = 9：エラー
     */
    public void test_execute_0002()
    {
        final String STR_METHOD_NAME = "test_execute_0002()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityAttentionInfoNotifyRequest l_request = new WEB3AdminEquityAttentionInfoNotifyRequest();
        WEB3AdminEquityAttentionInfoNotifyServiceImpl l_service = new WEB3AdminEquityAttentionInfoNotifyServiceImpl();
        try
        {
            TestDBUtility.deleteAllAndCommit(InstitutionParams.TYPE);
            InstitutionParams l_institution = TestDBUtility.getInstitutionRow();
            l_institution.setInstitutionCode("0D");
            TestDBUtility.insertWithDelAndCommit(l_institution);
            
            TestDBUtility.deleteAllAndCommit(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setInstitutionCode("0D");
            l_marketParams.setMarketCode("9");
            l_marketParams.setSonarMarketCode("5");
            TestDBUtility.insertWithDelAndCommit(l_marketParams);
            
            TestDBUtility.deleteAllAndCommit(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setInstitutionCode("0D");
            l_productParams.setProductId(111111111111L);//
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDelAndCommit(l_productParams);
            
            TestDBUtility.deleteAllAndCommit(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(1006160060005L);
            l_tradedProductParams.setInstitutionCode("0D");
            l_tradedProductParams.setProductId(111111111111L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDelAndCommit(l_tradedProductParams);
            
            TestDBUtility.deleteAllAndCommit(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setInstitutionCode("0D");
            l_eqtypeProductParams.setProductId(111111111112L);//
            l_eqtypeProductParams.setProductCode("11111");
            TestDBUtility.insertWithDelAndCommit(l_eqtypeProductParams);
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            
            TestDBUtility.deleteAllAndCommit(EqtypeTradedProductParams.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setInstitutionCode("0D");
            l_eqtypeTradedProductParams.setProductId(111111111111L);//
            l_eqtypeTradedProductParams.setTradedProductId(1006160060005L);
            l_eqtypeTradedProductParams.setValidUntilBizDate(WEB3DateUtility.formatDate(l_finApp.getTradingSystem().getBizDate(), WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_eqtypeTradedProductParams.setMarketId(3303L);
            //基準値
            l_eqtypeTradedProductParams.setBasePrice(12);
            //強制値幅（上限値）
            l_eqtypeTradedProductParams.setHighCompulsivePriceRange(15);
            //強制値幅（下限値）
            l_eqtypeTradedProductParams.setLowCompulsivePriceRange(18);
            TestDBUtility.insertWithDelAndCommit(l_eqtypeTradedProductParams);
            
            //注意情報通知キューテーブル
            TestDBUtility.deleteAllAndCommit(HostAttentionInfoNotifyParams.TYPE);
            HostAttentionInfoNotifyParams l_hostAttentionInfoNotifyParams = new HostAttentionInfoNotifyParams();
            //データコード
            //"AXQY1"(MAXAS-AP：注意情報（売停情報）通知)固定
            l_hostAttentionInfoNotifyParams.setRequestCode("AXQY1");
            l_hostAttentionInfoNotifyParams.setStatus("0");
            l_hostAttentionInfoNotifyParams.setInstitutionCode("0D");
            l_hostAttentionInfoNotifyParams.setSonarMarketCode("5");
            l_hostAttentionInfoNotifyParams.setProductCode("11111");
            l_hostAttentionInfoNotifyParams.setInfoGenerationTimestamp(Calendar.getInstance().getTime());
            //注意情報区分コード
            l_hostAttentionInfoNotifyParams.setAttentionInfoDivCode("A001");
            //基準値（変更後）
            l_hostAttentionInfoNotifyParams.setBasePrice(19);
            //制限値幅上限
            l_hostAttentionInfoNotifyParams.setHighPriceRange(25);
            //制限値幅下限
            l_hostAttentionInfoNotifyParams.setLowPriceRange(15);
            //情報発生日時
            l_hostAttentionInfoNotifyParams.setInfoGenerationTimestamp(Calendar.getInstance().getTime());
            //注文受付再開日時
            l_hostAttentionInfoNotifyParams.setOrdReceiptRestartTimestamp(Calendar.getInstance().getTime());
            //売買停止日時／売買再開日時
            l_hostAttentionInfoNotifyParams.setTradeStopRestartTimestamp(Calendar.getInstance().getTime());
//            l_hostAttentionInfoNotifyParams.setStatus("s");
            TestDBUtility.insertWithDelAndCommit(l_hostAttentionInfoNotifyParams);
            
            TestDBUtility.deleteAllAndCommit(MailInfoParams.TYPE);
            MailInfoParams l_mailInfoParams = new MailInfoParams();
            l_mailInfoParams.setInstitutionCode("0D");
            l_mailInfoParams.setSendmailDiv("3104");
            l_mailInfoParams.setDiscernmentId("1");
            l_mailInfoParams.setSubject("あいうえおabced1258");
            l_mailInfoParams.setLastUpdater("0213");
            l_mailInfoParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_mailInfoParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDelAndCommit(l_mailInfoParams);
            TestDBUtility.deleteAllAndCommit(AttentionInfoHistoryParams.TYPE);
            
            l_service.execute(l_request);
            
            Object[] l_values1 = {"AXQY1", "5", "9"};
            QueryProcessor l_processor1 = Processors.getDefaultProcessor();
            List l_lisHostAttentionInfoNotifyRows = l_processor1.doFindAllQuery(
                    HostAttentionInfoNotifyParams.TYPE,
                    "request_code = ? and " +
                    "sonar_market_code = ? and " +
                    "status = ?",
                    l_values1);
            
            assertEquals("1", l_lisHostAttentionInfoNotifyRows.size() + "");
//            //注意情報通知キュー.データコード＝"注意情報（売停情報）通知"の場合
//            //　@"1：売停情報"
//            Object[] l_values = {"1", "0D", new Long(3303L), "A001", "1"};
//            QueryProcessor l_processor = Processors.getDefaultProcessor();
//            List l_lisHostAttentionInfoNotifyParams = l_processor.doFindAllQuery(
//                AttentionInfoHistoryRow.TYPE,
//                "attention_info_type = ? and " +
//                "institution_code = ? and " +
//                "market_id = ? and " +
//                "attention_info_div_code = ? and " +
//                "process_result_div = ?",
//                l_values);
            
//            AttentionInfoHistoryRow l_row = (AttentionInfoHistoryRow)l_lisAttentionInfoHistoryRows.get(0);
////            assertEquals("", l_row.getAttentionInfoHistoryId() + "");
//            assertEquals("1", l_row.getAttentionInfoType());
//            assertEquals("0D", l_row.getInstitutionCode());
//            assertEquals("111111111111", l_row.getProductId() + "");
//            assertEquals("3303", l_row.getMarketId() + "");
//            assertEquals(
//                WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(), "yyyyMMdd"),
//                WEB3DateUtility.formatDate(
//                WEB3DateUtility.getDate(l_row.getValidUntilBizDate(), "yyyyMMdd"),"yyyyMMdd"));
//            assertEquals("A001", l_row.getAttentionInfoDivCode());
//            
//            assertEquals("0.0", l_row.getOldBasePrice() + "");
//            assertEquals("0.0", l_row.getOldHighCompPriceRange() + "");
//            assertEquals("0.0", l_row.getOldLowCompPriceRange() + "");
//            assertEquals("0.0", l_row.getNewBasePrice() + "");
////            assertEquals("12", l_row.getOldBasePrice() + "");
////            assertEquals("15", l_row.getOldHighCompPriceRange() + "");
////            assertEquals("18", l_row.getOldLowCompPriceRange() + "");
////            assertEquals("19", l_row.getNewBasePrice() + "");
////            //注意情報通知キュー.制限値幅上限-注意情報通知キュー.基準値
////            assertEquals("6", l_row.getNewHighCompPriceRange() + "");
////            //注意情報通知キュー.基準値-注意情報通知キュー.制限値幅下限
////            assertEquals("", l_row.getNewLowCompPriceRange() + "");
//            
//            //注意情報通知キュー.制限値幅上限-注意情報通知キュー.基準値
//            assertEquals("0.0", l_row.getNewHighPriceRange() + "");
//            //注意情報通知キュー.基準値-注意情報通知キュー.制限値幅下限
//            assertEquals("0.0", l_row.getNewLowPriceRange() + "");
//            //
//            assertNull(l_row.getFreeFormatTitle());
//            assertNull(l_row.getFreeFormatText());
//            
//            assertEquals(
//                WEB3DateUtility.formatDate(Calendar.getInstance().getTime(), "yyyyMMdd"),
//                WEB3DateUtility.formatDate(l_row.getInfoGenerationTimestamp(), "yyyyMMdd"));
//            assertEquals(
//                WEB3DateUtility.formatDate(Calendar.getInstance().getTime(), "yyyyMMdd"),
//                WEB3DateUtility.formatDate(l_row.getOrdReceiptRestartTimestamp(), "yyyyMMdd"));
//            assertEquals(
//                WEB3DateUtility.formatDate(Calendar.getInstance().getTime(), "yyyyMMdd"),
//                WEB3DateUtility.formatDate(l_row.getTradeStopRestartTimestamp(), "yyyyMMdd"));
//            
//            assertEquals("1", l_row.getProcessResultDiv());
//            
//            assertEquals(
//                WEB3DateUtility.formatDate(Calendar.getInstance().getTime(), "yyyyMMdd"),
//                WEB3DateUtility.formatDate(l_row.getCreatedTimestamp(), "yyyyMMdd"));
//            assertEquals(
//                WEB3DateUtility.formatDate(Calendar.getInstance().getTime(), "yyyyMMdd"),
//                WEB3DateUtility.formatDate(l_row.getLastUpdatedTimestamp(), "yyyyMMdd"));

        } catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    
    /**
     * "AXRY1"(MAXAS-AP：注意情報（制限値幅情報）通知)固定
     * 銘柄テーブル != null
     * 株式取引銘柄マスタテーブル != null
     * 株式取引銘柄マスタupdqテーブル 
     * 処理区分 = 1：処理済
     */
    public void test_execute_0003()
    {
        final String STR_METHOD_NAME = "test_execute_0003()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityAttentionInfoNotifyRequest l_request = new WEB3AdminEquityAttentionInfoNotifyRequest();
        WEB3AdminEquityAttentionInfoNotifyServiceImpl l_service = new WEB3AdminEquityAttentionInfoNotifyServiceImpl();
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityAttentionInfoNotifyUnitServiceImpl",
                "notifyLimitRangeInfo",
                new Class[] {HostAttentionInfoNotifyParams.class, WEB3EquityTradedProduct.class, EqtypeTradedProductUpdqRow.class, ProductRow.class},
                new Object[] {null, null, null, null});
                    
            TestDBUtility.deleteAllAndCommit(InstitutionParams.TYPE);
            InstitutionParams l_institution = TestDBUtility.getInstitutionRow();
            l_institution.setInstitutionCode("0D");
            TestDBUtility.insertWithDelAndCommit(l_institution);
            
            TestDBUtility.deleteAllAndCommit(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setInstitutionCode("0D");
            l_marketParams.setMarketCode("9");
            l_marketParams.setSonarMarketCode("5");
            TestDBUtility.insertWithDelAndCommit(l_marketParams);
            
            TestDBUtility.deleteAllAndCommit(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setInstitutionCode("0D");
            l_productParams.setProductId(111111111111L);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDelAndCommit(l_productParams);
            
            TestDBUtility.deleteAllAndCommit(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(1006160060005L);
            l_tradedProductParams.setInstitutionCode("0D");
            l_tradedProductParams.setProductId(111111111111L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDelAndCommit(l_tradedProductParams);
            
            TestDBUtility.deleteAllAndCommit(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setInstitutionCode("0D");
            l_eqtypeProductParams.setProductId(111111111111L);
            l_eqtypeProductParams.setProductCode("11111");
            TestDBUtility.insertWithDelAndCommit(l_eqtypeProductParams);
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            
            TestDBUtility.deleteAllAndCommit(EqtypeTradedProductParams.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setInstitutionCode("0D");
            l_eqtypeTradedProductParams.setProductId(111111111111L);
            l_eqtypeTradedProductParams.setTradedProductId(1006160060005L);
            l_eqtypeTradedProductParams.setValidUntilBizDate(WEB3DateUtility.formatDate(l_finApp.getTradingSystem().getBizDate(), WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_eqtypeTradedProductParams.setMarketId(3303L);
            //基準値
            l_eqtypeTradedProductParams.setBasePrice(12);
            //強制値幅（上限値）
            l_eqtypeTradedProductParams.setHighCompulsivePriceRange(15);
            //強制値幅（下限値）
            l_eqtypeTradedProductParams.setLowCompulsivePriceRange(11);
            TestDBUtility.insertWithDelAndCommit(l_eqtypeTradedProductParams);
            
            //注意情報通知キューテーブル
            TestDBUtility.deleteAllAndCommit(HostAttentionInfoNotifyParams.TYPE);
            HostAttentionInfoNotifyParams l_hostAttentionInfoNotifyParams = new HostAttentionInfoNotifyParams();
            //データコード
            //"AXRY1"(MAXAS-AP：注意情報（制限値幅情報）通知)固定
            l_hostAttentionInfoNotifyParams.setRequestCode("AXRY1");
            l_hostAttentionInfoNotifyParams.setStatus("0");
            l_hostAttentionInfoNotifyParams.setInstitutionCode("0D");
            l_hostAttentionInfoNotifyParams.setSonarMarketCode("5");
            l_hostAttentionInfoNotifyParams.setProductCode("11111");
            l_hostAttentionInfoNotifyParams.setInfoGenerationTimestamp(Calendar.getInstance().getTime());
            //注意情報区分コード
            l_hostAttentionInfoNotifyParams.setAttentionInfoDivCode("A001");
            //基準値（変更後）
            l_hostAttentionInfoNotifyParams.setBasePrice(19);
            //制限値幅上限
            l_hostAttentionInfoNotifyParams.setHighPriceRange(25);
            //制限値幅下限
            l_hostAttentionInfoNotifyParams.setLowPriceRange(15);
            //情報発生日時
            l_hostAttentionInfoNotifyParams.setInfoGenerationTimestamp(Calendar.getInstance().getTime());
            //注文受付再開日時
            l_hostAttentionInfoNotifyParams.setOrdReceiptRestartTimestamp(Calendar.getInstance().getTime());
            //売買停止日時／売買再開日時
            l_hostAttentionInfoNotifyParams.setTradeStopRestartTimestamp(Calendar.getInstance().getTime());
            //表題
            l_hostAttentionInfoNotifyParams.setFreeFormatTitle("faffffsdksadkhhhkakfksfhk");
            //本文
            l_hostAttentionInfoNotifyParams.setFreeFormatText("asdasdsadsadasdasdsadas");
//            l_hostAttentionInfoNotifyParams.setStatus("s");
            TestDBUtility.insertWithDelAndCommit(l_hostAttentionInfoNotifyParams);
            
            TestDBUtility.deleteAllAndCommit(MailInfoParams.TYPE);
            MailInfoParams l_mailInfoParams = new MailInfoParams();
            l_mailInfoParams.setInstitutionCode("0D");
            l_mailInfoParams.setSendmailDiv("3102");
            l_mailInfoParams.setDiscernmentId("1");
            l_mailInfoParams.setSubject("あいうえおabced1258");
            l_mailInfoParams.setLastUpdater("0213");
            l_mailInfoParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_mailInfoParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDelAndCommit(l_mailInfoParams);
            
            TestDBUtility.deleteAllAndCommit(EqtypeTradedProductUpdqParams.TYPE);
            EqtypeTradedProductUpdqParams l_eqtypeTradedProductUpdqParams = TestDBUtility.getEqtypeTradedProductUpdqRow();
            l_eqtypeTradedProductUpdqParams.setProductId(111111111111L);
            l_eqtypeTradedProductUpdqParams.setMarketId(3303L);
            l_eqtypeTradedProductUpdqParams.setValidUntilBizDate(WEB3DateUtility.formatDate(l_finApp.getTradingSystem().getBizDate(), WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            TestDBUtility.insertWithDelAndCommit(l_eqtypeTradedProductUpdqParams);
            
            TestDBUtility.deleteAllAndCommit(AttentionInfoHistoryParams.TYPE);
            l_service.execute(l_request);
            
            Object[] l_values1 = {"AXRY1", "5", "1"};
            QueryProcessor l_processor1 = Processors.getDefaultProcessor();
            List l_lisHostAttentionInfoNotifyRows = l_processor1.doFindAllQuery(
                    HostAttentionInfoNotifyParams.TYPE,
                    "request_code = ? and " +
                    "sonar_market_code = ? and " +
                    "status = ?",
                    l_values1);
            
            assertEquals("1", l_lisHostAttentionInfoNotifyRows.size() + "");
            
//            //注意情報通知キュー.データコード＝"注意情報（制限値幅情報）通知"の場合
//            //　@"2：制限値幅情報"
//            Object[] l_values = {"2", "0D", new Long(3303L), "A001", "1"};
//            QueryProcessor l_processor = Processors.getDefaultProcessor();
//            List l_lisAttentionInfoHistoryRows = l_processor.doFindAllQuery(
//                AttentionInfoHistoryRow.TYPE,
//                "attention_info_type = ? and " +
//                "institution_code = ? and " +
//                "market_id = ? and " +
//                "attention_info_div_code = ? and " +
//                "process_result_div = ?",
//                l_values);
//            
//            AttentionInfoHistoryRow l_row = (AttentionInfoHistoryRow)l_lisAttentionInfoHistoryRows.get(0);
////            assertEquals("", l_row.getAttentionInfoHistoryId() + "");
//            assertEquals("2", l_row.getAttentionInfoType());
//            assertEquals("0D", l_row.getInstitutionCode());
//            assertEquals("111111111111", l_row.getProductId() + "");
//            assertEquals("3303", l_row.getMarketId() + "");
//            assertEquals(
//                WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(), "yyyyMMdd"),
//                WEB3DateUtility.formatDate(
//                WEB3DateUtility.getDate(l_row.getValidUntilBizDate(), "yyyyMMdd"),"yyyyMMdd"));
//            assertEquals("A001", l_row.getAttentionInfoDivCode());
//            
////            assertEquals("0.0", l_row.getOldBasePrice() + "");
////            assertEquals("0.0", l_row.getOldHighCompPriceRange() + "");
////            assertEquals("0.0", l_row.getOldLowCompPriceRange() + "");
////            assertEquals("0.0", l_row.getNewBasePrice() + "");
//            assertEquals("12.0", l_row.getOldBasePrice() + "");
//            assertEquals("15.0", l_row.getOldHighCompPriceRange() + "");
//            assertEquals("11.0", l_row.getOldLowCompPriceRange() + "");
//            assertEquals("19.0", l_row.getNewBasePrice() + "");
//            //注意情報通知キュー.制限値幅上限-注意情報通知キュー.基準値
//            assertEquals("25.0", l_row.getNewHighPriceRange() + "");
//            //注意情報通知キュー.基準値-注意情報通知キュー.制限値幅下限
//            assertEquals("15.0", l_row.getNewLowPriceRange() + "");
//            
////            //注意情報通知キュー.制限値幅上限-注意情報通知キュー.基準値
////            assertEquals("0.0", l_row.getNewHighCompPriceRange() + "");
////            //注意情報通知キュー.基準値-注意情報通知キュー.制限値幅下限
////            assertEquals("0.0", l_row.getNewLowCompPriceRange() + "");
//            //表題
//            assertNull(l_row.getFreeFormatTitle());
//            //本文
//            assertNull(l_row.getFreeFormatText());
//            
//            assertEquals(
//                WEB3DateUtility.formatDate(Calendar.getInstance().getTime(), "yyyyMMdd"),
//                WEB3DateUtility.formatDate(l_row.getInfoGenerationTimestamp(), "yyyyMMdd"));
//            assertEquals(
//                WEB3DateUtility.formatDate(Calendar.getInstance().getTime(), "yyyyMMdd"),
//                WEB3DateUtility.formatDate(l_row.getOrdReceiptRestartTimestamp(), "yyyyMMdd"));
//            assertEquals(
//                WEB3DateUtility.formatDate(Calendar.getInstance().getTime(), "yyyyMMdd"),
//                WEB3DateUtility.formatDate(l_row.getTradeStopRestartTimestamp(), "yyyyMMdd"));
//            
//            assertEquals("1", l_row.getProcessResultDiv());
//            
//            assertEquals(
//                WEB3DateUtility.formatDate(Calendar.getInstance().getTime(), "yyyyMMdd"),
//                WEB3DateUtility.formatDate(l_row.getCreatedTimestamp(), "yyyyMMdd"));
//            assertEquals(
//                WEB3DateUtility.formatDate(Calendar.getInstance().getTime(), "yyyyMMdd"),
//                WEB3DateUtility.formatDate(l_row.getLastUpdatedTimestamp(), "yyyyMMdd"));

        } catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * "AXRY1"(MAXAS-AP：注意情報（制限値幅情報）通知)固定
     * 銘柄テーブル != null
     * 株式取引銘柄マスタテーブル = null
     * 株式取引銘柄マスタupdqテーブル 
     * 処理区分 = 9：エラー
     */
    public void test_execute_0004()
    {
        final String STR_METHOD_NAME = "test_execute_0004()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityAttentionInfoNotifyRequest l_request = new WEB3AdminEquityAttentionInfoNotifyRequest();
        WEB3AdminEquityAttentionInfoNotifyServiceImpl l_service = new WEB3AdminEquityAttentionInfoNotifyServiceImpl();
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityAttentionInfoNotifyUnitServiceImpl",
                "notifyLimitRangeInfo",
                new Class[] {HostAttentionInfoNotifyParams.class, WEB3EquityTradedProduct.class, EqtypeTradedProductUpdqRow.class, ProductRow.class},
                new Object[] {null, null, null, null});
                    
            TestDBUtility.deleteAllAndCommit(InstitutionParams.TYPE);
            InstitutionParams l_institution = TestDBUtility.getInstitutionRow();
            l_institution.setInstitutionCode("0D");
            TestDBUtility.insertWithDelAndCommit(l_institution);
            
            TestDBUtility.deleteAllAndCommit(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setInstitutionCode("0D");
            l_marketParams.setMarketCode("9");
            l_marketParams.setSonarMarketCode("5");
//            l_marketParams.setMarketId(3300L);
            TestDBUtility.insertWithDelAndCommit(l_marketParams);
            
            TestDBUtility.deleteAllAndCommit(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setInstitutionCode("0D");
            l_productParams.setProductId(111111111111L);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDelAndCommit(l_productParams);
            
            TestDBUtility.deleteAllAndCommit(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(1006160060005L);
            l_tradedProductParams.setInstitutionCode("0D");
            l_tradedProductParams.setProductId(111111111111L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDelAndCommit(l_tradedProductParams);
            
            TestDBUtility.deleteAllAndCommit(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setInstitutionCode("0D");
            l_eqtypeProductParams.setProductId(111111111111L);
            l_eqtypeProductParams.setProductCode("11111");
            TestDBUtility.insertWithDelAndCommit(l_eqtypeProductParams);
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            
            TestDBUtility.deleteAllAndCommit(EqtypeTradedProductParams.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setInstitutionCode("0D");
            l_eqtypeTradedProductParams.setProductId(111111111112L);//
            l_eqtypeTradedProductParams.setTradedProductId(1006160060005L);
            l_eqtypeTradedProductParams.setValidUntilBizDate(WEB3DateUtility.formatDate(l_finApp.getTradingSystem().getBizDate(), WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_eqtypeTradedProductParams.setMarketId(3303L);
            //基準値
            l_eqtypeTradedProductParams.setBasePrice(12);
            //強制値幅（上限値）
            l_eqtypeTradedProductParams.setHighCompulsivePriceRange(15);
            //強制値幅（下限値）
            l_eqtypeTradedProductParams.setLowCompulsivePriceRange(11);
            TestDBUtility.insertWithDelAndCommit(l_eqtypeTradedProductParams);
            
            //注意情報通知キューテーブル
            TestDBUtility.deleteAllAndCommit(HostAttentionInfoNotifyParams.TYPE);
            HostAttentionInfoNotifyParams l_hostAttentionInfoNotifyParams = new HostAttentionInfoNotifyParams();
            //データコード
            //"AXRY1"(MAXAS-AP：注意情報（制限値幅情報）通知)固定
            l_hostAttentionInfoNotifyParams.setRequestCode("AXRY1");
            l_hostAttentionInfoNotifyParams.setStatus("0");
            l_hostAttentionInfoNotifyParams.setInstitutionCode("0D");
            l_hostAttentionInfoNotifyParams.setSonarMarketCode("5");
            l_hostAttentionInfoNotifyParams.setProductCode("11111");
            l_hostAttentionInfoNotifyParams.setInfoGenerationTimestamp(Calendar.getInstance().getTime());
            //注意情報区分コード
            l_hostAttentionInfoNotifyParams.setAttentionInfoDivCode("A001");
            //基準値（変更後）
            l_hostAttentionInfoNotifyParams.setBasePrice(19);
            //制限値幅上限
            l_hostAttentionInfoNotifyParams.setHighPriceRange(25);
            //制限値幅下限
            l_hostAttentionInfoNotifyParams.setLowPriceRange(15);
            //情報発生日時
            l_hostAttentionInfoNotifyParams.setInfoGenerationTimestamp(Calendar.getInstance().getTime());
            //注文受付再開日時
            l_hostAttentionInfoNotifyParams.setOrdReceiptRestartTimestamp(Calendar.getInstance().getTime());
            //売買停止日時／売買再開日時
            l_hostAttentionInfoNotifyParams.setTradeStopRestartTimestamp(Calendar.getInstance().getTime());
            //表題
            l_hostAttentionInfoNotifyParams.setFreeFormatTitle("faffffsdksadkhhhkakfksfhk");
            //本文
            l_hostAttentionInfoNotifyParams.setFreeFormatText("asdasdsadsadasdasdsadas");
//            l_hostAttentionInfoNotifyParams.setStatus("s");
            TestDBUtility.insertWithDelAndCommit(l_hostAttentionInfoNotifyParams);
            
            TestDBUtility.deleteAllAndCommit(MailInfoParams.TYPE);
            MailInfoParams l_mailInfoParams = new MailInfoParams();
            l_mailInfoParams.setInstitutionCode("0D");
            l_mailInfoParams.setSendmailDiv("3104");
            l_mailInfoParams.setDiscernmentId("1");
            l_mailInfoParams.setSubject("あいうえおabced1258");
            l_mailInfoParams.setLastUpdater("0213");
            l_mailInfoParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_mailInfoParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDelAndCommit(l_mailInfoParams);
            
            TestDBUtility.deleteAllAndCommit(EqtypeTradedProductUpdqParams.TYPE);
            EqtypeTradedProductUpdqParams l_eqtypeTradedProductUpdqParams = TestDBUtility.getEqtypeTradedProductUpdqRow();
            l_eqtypeTradedProductUpdqParams.setProductId(111111111111L);
            l_eqtypeTradedProductUpdqParams.setMarketId(3303L);
            l_eqtypeTradedProductUpdqParams.setValidUntilBizDate(WEB3DateUtility.formatDate(l_finApp.getTradingSystem().getBizDate(), WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            TestDBUtility.insertWithDelAndCommit(l_eqtypeTradedProductUpdqParams);
            
            TestDBUtility.deleteAllAndCommit(AttentionInfoHistoryParams.TYPE);
            l_service.execute(l_request);
            
            Object[] l_values1 = {"AXRY1", "5", "9"};
            QueryProcessor l_processor1 = Processors.getDefaultProcessor();
            List l_lisHostAttentionInfoNotifyRows = l_processor1.doFindAllQuery(
                    HostAttentionInfoNotifyParams.TYPE,
                    "request_code = ? and " +
                    "sonar_market_code = ? and " +
                    "status = ?",
                    l_values1);
            
            assertEquals("1", l_lisHostAttentionInfoNotifyRows.size() + "");
            
//            //注意情報通知キュー.データコード＝"注意情報（制限値幅情報）通知"の場合
//            //　@"2：制限値幅情報"
//            Object[] l_values = {"2", "0D", new Long(3303L), "A001", "1"};
//            QueryProcessor l_processor = Processors.getDefaultProcessor();
//            List l_lisAttentionInfoHistoryRows = l_processor.doFindAllQuery(
//                AttentionInfoHistoryRow.TYPE,
//                "attention_info_type = ? and " +
//                "institution_code = ? and " +
//                "market_id = ? and " +
//                "attention_info_div_code = ? and " +
//                "process_result_div = ?",
//                l_values);
//            
//            AttentionInfoHistoryRow l_row = (AttentionInfoHistoryRow)l_lisAttentionInfoHistoryRows.get(0);
////            assertEquals("", l_row.getAttentionInfoHistoryId() + "");
//            assertEquals("2", l_row.getAttentionInfoType());
//            assertEquals("0D", l_row.getInstitutionCode());
//            assertEquals("111111111111", l_row.getProductId() + "");
//            assertEquals("3303", l_row.getMarketId() + "");
//            assertEquals(
//                WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(), "yyyyMMdd"),
//                WEB3DateUtility.formatDate(
//                WEB3DateUtility.getDate(l_row.getValidUntilBizDate(), "yyyyMMdd"),"yyyyMMdd"));
//            assertEquals("A001", l_row.getAttentionInfoDivCode());
//            
////            assertEquals("0.0", l_row.getOldBasePrice() + "");
////            assertEquals("0.0", l_row.getOldHighCompPriceRange() + "");
////            assertEquals("0.0", l_row.getOldLowCompPriceRange() + "");
////            assertEquals("0.0", l_row.getNewBasePrice() + "");
//            assertEquals("12.0", l_row.getOldBasePrice() + "");
//            assertEquals("15.0", l_row.getOldHighCompPriceRange() + "");
//            assertEquals("11.0", l_row.getOldLowCompPriceRange() + "");
//            assertEquals("19.0", l_row.getNewBasePrice() + "");
//            //注意情報通知キュー.制限値幅上限-注意情報通知キュー.基準値
//            assertEquals("25.0", l_row.getNewHighPriceRange() + "");
//            //注意情報通知キュー.基準値-注意情報通知キュー.制限値幅下限
//            assertEquals("15.0", l_row.getNewLowPriceRange() + "");
//            
////            //注意情報通知キュー.制限値幅上限-注意情報通知キュー.基準値
////            assertEquals("0.0", l_row.getNewHighCompPriceRange() + "");
////            //注意情報通知キュー.基準値-注意情報通知キュー.制限値幅下限
////            assertEquals("0.0", l_row.getNewLowCompPriceRange() + "");
//            //表題
//            assertNull(l_row.getFreeFormatTitle());
//            //本文
//            assertNull(l_row.getFreeFormatText());
//            
//            assertEquals(
//                WEB3DateUtility.formatDate(Calendar.getInstance().getTime(), "yyyyMMdd"),
//                WEB3DateUtility.formatDate(l_row.getInfoGenerationTimestamp(), "yyyyMMdd"));
//            assertEquals(
//                WEB3DateUtility.formatDate(Calendar.getInstance().getTime(), "yyyyMMdd"),
//                WEB3DateUtility.formatDate(l_row.getOrdReceiptRestartTimestamp(), "yyyyMMdd"));
//            assertEquals(
//                WEB3DateUtility.formatDate(Calendar.getInstance().getTime(), "yyyyMMdd"),
//                WEB3DateUtility.formatDate(l_row.getTradeStopRestartTimestamp(), "yyyyMMdd"));
//            
//            assertEquals("1", l_row.getProcessResultDiv());
//            
//            assertEquals(
//                WEB3DateUtility.formatDate(Calendar.getInstance().getTime(), "yyyyMMdd"),
//                WEB3DateUtility.formatDate(l_row.getCreatedTimestamp(), "yyyyMMdd"));
//            assertEquals(
//                WEB3DateUtility.formatDate(Calendar.getInstance().getTime(), "yyyyMMdd"),
//                WEB3DateUtility.formatDate(l_row.getLastUpdatedTimestamp(), "yyyyMMdd"));

        } catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * "AXSY1"(MAXAS-AP：注意情報（フリーフォーマット）通知)固定
     */
    public void test_execute_0005()
    {
        final String STR_METHOD_NAME = "test_execute_0005()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityAttentionInfoNotifyRequest l_request = new WEB3AdminEquityAttentionInfoNotifyRequest();
        WEB3AdminEquityAttentionInfoNotifyServiceImpl l_service = new WEB3AdminEquityAttentionInfoNotifyServiceImpl();
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityAttentionInfoNotifyUnitServiceImpl",
                "notifyLimitRangeInfo",
                new Class[] {HostAttentionInfoNotifyParams.class, WEB3EquityTradedProduct.class},
                new Object[] {null, null});
                    
            TestDBUtility.deleteAllAndCommit(InstitutionParams.TYPE);
            InstitutionParams l_institution = TestDBUtility.getInstitutionRow();
            l_institution.setInstitutionCode("0D");
            TestDBUtility.insertWithDelAndCommit(l_institution);
            
            TestDBUtility.deleteAllAndCommit(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setInstitutionCode("0D");
            l_marketParams.setMarketCode("9");
            l_marketParams.setSonarMarketCode("5");
            TestDBUtility.insertWithDelAndCommit(l_marketParams);
            
            TestDBUtility.deleteAllAndCommit(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setInstitutionCode("0D");
            l_productParams.setProductId(111111111111L);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDelAndCommit(l_productParams);
            
            TestDBUtility.deleteAllAndCommit(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(1006160060005L);
            l_tradedProductParams.setInstitutionCode("0D");
            l_tradedProductParams.setProductId(111111111111L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDelAndCommit(l_tradedProductParams);
            
            TestDBUtility.deleteAllAndCommit(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setInstitutionCode("0D");
            l_eqtypeProductParams.setProductId(111111111111L);
            l_eqtypeProductParams.setProductCode("11111");
            TestDBUtility.insertWithDelAndCommit(l_eqtypeProductParams);
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            
            TestDBUtility.deleteAllAndCommit(EqtypeTradedProductParams.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setInstitutionCode("0D");
            l_eqtypeTradedProductParams.setProductId(111111111111L);
            l_eqtypeTradedProductParams.setTradedProductId(1006160060005L);
            l_eqtypeTradedProductParams.setValidUntilBizDate(WEB3DateUtility.formatDate(l_finApp.getTradingSystem().getBizDate(), WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_eqtypeTradedProductParams.setMarketId(3303L);
            //基準値
            l_eqtypeTradedProductParams.setBasePrice(12);
            //強制値幅（上限値）
            l_eqtypeTradedProductParams.setHighCompulsivePriceRange(15);
            //強制値幅（下限値）
            l_eqtypeTradedProductParams.setLowCompulsivePriceRange(11);
            TestDBUtility.insertWithDelAndCommit(l_eqtypeTradedProductParams);
            
            //注意情報通知キューテーブル
            TestDBUtility.deleteAllAndCommit(HostAttentionInfoNotifyParams.TYPE);
            HostAttentionInfoNotifyParams l_hostAttentionInfoNotifyParams = new HostAttentionInfoNotifyParams();
            //データコード
            //"AXSY1"(MAXAS-AP：注意情報（フリーフォーマット）通知)固定
            l_hostAttentionInfoNotifyParams.setRequestCode("AXSY1");
            l_hostAttentionInfoNotifyParams.setStatus("0");
            l_hostAttentionInfoNotifyParams.setInstitutionCode("0D");
            l_hostAttentionInfoNotifyParams.setSonarMarketCode("5");
            l_hostAttentionInfoNotifyParams.setProductCode("11111");
//            l_hostAttentionInfoNotifyParams.setatt
            l_hostAttentionInfoNotifyParams.setInfoGenerationTimestamp(Calendar.getInstance().getTime());
            //注意情報区分コード
            l_hostAttentionInfoNotifyParams.setAttentionInfoDivCode("A001");
            //基準値（変更後）
            l_hostAttentionInfoNotifyParams.setBasePrice(19);
            //制限値幅上限
            l_hostAttentionInfoNotifyParams.setHighPriceRange(25);
            //制限値幅下限
            l_hostAttentionInfoNotifyParams.setLowPriceRange(15);
            //情報発生日時
            l_hostAttentionInfoNotifyParams.setInfoGenerationTimestamp(Calendar.getInstance().getTime());
            //注文受付再開日時
            l_hostAttentionInfoNotifyParams.setOrdReceiptRestartTimestamp(Calendar.getInstance().getTime());
            //売買停止日時／売買再開日時
            l_hostAttentionInfoNotifyParams.setTradeStopRestartTimestamp(Calendar.getInstance().getTime());
            //表題
            l_hostAttentionInfoNotifyParams.setFreeFormatTitle("faffffsdksadkhhhkakfksfhk");
            //本文
            l_hostAttentionInfoNotifyParams.setFreeFormatText("asdasdsadsadasdasdsadas");
            TestDBUtility.insertWithDelAndCommit(l_hostAttentionInfoNotifyParams);
            
            TestDBUtility.deleteAllAndCommit(MailInfoParams.TYPE);
            MailInfoParams l_mailInfoParams = new MailInfoParams();
            l_mailInfoParams.setInstitutionCode("0D");
            l_mailInfoParams.setSendmailDiv("3103");
            l_mailInfoParams.setDiscernmentId("1");
            l_mailInfoParams.setSubject("あいうえおabced1258");
            l_mailInfoParams.setLastUpdater("0213");
            l_mailInfoParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_mailInfoParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDelAndCommit(l_mailInfoParams);
            
            TestDBUtility.deleteAllAndCommit(AttentionInfoHistoryParams.TYPE);
            l_service.execute(l_request);
            
            Object[] l_values1 = {"AXSY1", "5", "1"};
            QueryProcessor l_processor1 = Processors.getDefaultProcessor();
            List l_lisHostAttentionInfoNotifyRows = l_processor1.doFindAllQuery(
                    HostAttentionInfoNotifyParams.TYPE,
                    "request_code = ? and " +
                    "sonar_market_code = ? and " +
                    "status = ?",
                    l_values1);
            
            assertEquals("1", l_lisHostAttentionInfoNotifyRows.size() + "");
//            //注意情報通知キュー.データコード＝"注意情報（制限値幅情報）通知"の場合
//            //3：フリーフォーマット
//            Object[] l_values = {"3", "0D", new Long(3303L), "A001", "1"};
//            QueryProcessor l_processor = Processors.getDefaultProcessor();
//            List l_lisAttentionInfoHistoryRows = l_processor.doFindAllQuery(
//                AttentionInfoHistoryRow.TYPE,
//                "attention_info_type = ? and " +
//                "institution_code = ? and " +
//                "market_id = ? and " +
//                "attention_info_div_code = ? and " +
//                "process_result_div = ?",
//                l_values);
//            
//            AttentionInfoHistoryRow l_row = (AttentionInfoHistoryRow)l_lisAttentionInfoHistoryRows.get(0);
////            assertEquals("", l_row.getAttentionInfoHistoryId() + "");
//            assertEquals("3", l_row.getAttentionInfoType());
//            assertEquals("0D", l_row.getInstitutionCode());
//            assertEquals("111111111111", l_row.getProductId() + "");
//            assertEquals("3303", l_row.getMarketId() + "");
//            assertEquals(
//                WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(), "yyyyMMdd"),
//                WEB3DateUtility.formatDate(
//                WEB3DateUtility.getDate(l_row.getValidUntilBizDate(), "yyyyMMdd"),"yyyyMMdd"));
//            assertEquals("A001", l_row.getAttentionInfoDivCode());
//            
//            assertEquals("0.0", l_row.getOldBasePrice() + "");
//            assertEquals("0.0", l_row.getOldHighCompPriceRange() + "");
//            assertEquals("0.0", l_row.getOldLowCompPriceRange() + "");
//            assertEquals("0.0", l_row.getNewBasePrice() + "");
////            assertEquals("12.0", l_row.getOldBasePrice() + "");
////            assertEquals("15.0", l_row.getOldHighCompPriceRange() + "");
////            assertEquals("11.0", l_row.getOldLowCompPriceRange() + "");
////            assertEquals("19.0", l_row.getNewBasePrice() + "");
////            //注意情報通知キュー.制限値幅上限-注意情報通知キュー.基準値
////            assertEquals("6.0", l_row.getNewHighCompPriceRange() + "");
////            //注意情報通知キュー.基準値-注意情報通知キュー.制限値幅下限
////            assertEquals("4.0", l_row.getNewLowCompPriceRange() + "");
//            
//            //注意情報通知キュー.制限値幅上限-注意情報通知キュー.基準値
//            assertEquals("0.0", l_row.getNewHighPriceRange() + "");
//            //注意情報通知キュー.基準値-注意情報通知キュー.制限値幅下限
//            assertEquals("0.0", l_row.getNewLowPriceRange() + "");
//            //表題
//            assertEquals("faffffsdksadkhhhkakfksfhk", l_row.getFreeFormatTitle());
//            //本文
//            assertEquals("asdasdsadsadasdasdsadas", l_row.getFreeFormatText());
//            
//            assertEquals(
//                WEB3DateUtility.formatDate(Calendar.getInstance().getTime(), "yyyyMMdd"),
//                WEB3DateUtility.formatDate(l_row.getInfoGenerationTimestamp(), "yyyyMMdd"));
//            assertEquals(
//                WEB3DateUtility.formatDate(Calendar.getInstance().getTime(), "yyyyMMdd"),
//                WEB3DateUtility.formatDate(l_row.getOrdReceiptRestartTimestamp(), "yyyyMMdd"));
//            assertEquals(
//                WEB3DateUtility.formatDate(Calendar.getInstance().getTime(), "yyyyMMdd"),
//                WEB3DateUtility.formatDate(l_row.getTradeStopRestartTimestamp(), "yyyyMMdd"));
//            
//            assertEquals("1", l_row.getProcessResultDiv());
//            
//            assertEquals(
//                WEB3DateUtility.formatDate(Calendar.getInstance().getTime(), "yyyyMMdd"),
//                WEB3DateUtility.formatDate(l_row.getCreatedTimestamp(), "yyyyMMdd"));
//            assertEquals(
//                WEB3DateUtility.formatDate(Calendar.getInstance().getTime(), "yyyyMMdd"),
//                WEB3DateUtility.formatDate(l_row.getLastUpdatedTimestamp(), "yyyyMMdd"));

        } catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * "AXRY1"(MAXAS-AP：注意情報（制限値幅情報）通知)固定
     * 銘柄テーブル = null
     * 株式取引銘柄マスタテーブル = null
     * 株式取引銘柄マスタupdqテーブル = null
     */
    public void test_execute_0006()
    {
        final String STR_METHOD_NAME = "test_execute_0006()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityAttentionInfoNotifyRequest l_request = new WEB3AdminEquityAttentionInfoNotifyRequest();
        WEB3AdminEquityAttentionInfoNotifyServiceImpl l_service = new WEB3AdminEquityAttentionInfoNotifyServiceImpl();
        try
        {
            Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(Calendar.YEAR,2007);
            l_calendar.set(Calendar.MONTH,2);
            l_calendar.set(Calendar.DAY_OF_MONTH,14);
            l_calendar.set(Calendar.HOUR_OF_DAY,15);
            l_calendar.set(Calendar.MINUTE,00);
            l_calendar.set(Calendar.SECOND,01);

            Timestamp l_tsBizDate = new Timestamp(l_calendar.getTimeInMillis());

            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, l_tsBizDate);

            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                l_tsBizDate);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityAttentionInfoNotifyUnitServiceImpl",
                "notifyLimitRangeInfo",
                new Class[] {HostAttentionInfoNotifyParams.class, WEB3EquityTradedProduct.class, EqtypeTradedProductUpdqRow.class, ProductRow.class},
                new Object[] {null, null, null, null});
                    
            TestDBUtility.deleteAllAndCommit(InstitutionParams.TYPE);
            InstitutionParams l_institution = TestDBUtility.getInstitutionRow();
            l_institution.setInstitutionCode("0D");
            TestDBUtility.insertWithDelAndCommit(l_institution);
            
            TestDBUtility.deleteAllAndCommit(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setInstitutionCode("0D");
            l_marketParams.setMarketCode("9");
            l_marketParams.setSonarMarketCode("5");
//            l_marketParams.setMarketId(3300L);
            TestDBUtility.insertWithDelAndCommit(l_marketParams);
            
            TestDBUtility.deleteAllAndCommit(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setInstitutionCode("0D");
            l_productParams.setProductId(111111111111L);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDelAndCommit(l_productParams);
            
            TestDBUtility.deleteAllAndCommit(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(1006160060005L);
            l_tradedProductParams.setInstitutionCode("0D");
            l_tradedProductParams.setProductId(111111111111L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDelAndCommit(l_tradedProductParams);
            
            TestDBUtility.deleteAllAndCommit(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setInstitutionCode("0D");
            l_eqtypeProductParams.setProductId(111111111111L);
            l_eqtypeProductParams.setProductCode("11111");
            TestDBUtility.insertWithDelAndCommit(l_eqtypeProductParams);
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            
            TestDBUtility.deleteAllAndCommit(EqtypeTradedProductParams.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setInstitutionCode("0D");
            l_eqtypeTradedProductParams.setProductId(111111111112L);//
            l_eqtypeTradedProductParams.setTradedProductId(1006160060005L);
            l_eqtypeTradedProductParams.setValidUntilBizDate(WEB3DateUtility.formatDate(l_finApp.getTradingSystem().getBizDate(), WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_eqtypeTradedProductParams.setMarketId(3303L);
            //基準値
            l_eqtypeTradedProductParams.setBasePrice(12);
            //強制値幅（上限値）
            l_eqtypeTradedProductParams.setHighCompulsivePriceRange(15);
            //強制値幅（下限値）
            l_eqtypeTradedProductParams.setLowCompulsivePriceRange(11);
            TestDBUtility.insertWithDelAndCommit(l_eqtypeTradedProductParams);
            
            //注意情報通知キューテーブル
            TestDBUtility.deleteAllAndCommit(HostAttentionInfoNotifyParams.TYPE);
            HostAttentionInfoNotifyParams l_hostAttentionInfoNotifyParams = new HostAttentionInfoNotifyParams();
            //データコード
            //"AXRY1"(MAXAS-AP：注意情報（制限値幅情報）通知)固定
            l_hostAttentionInfoNotifyParams.setRequestCode("AXRY1");
            l_hostAttentionInfoNotifyParams.setStatus("0");
            l_hostAttentionInfoNotifyParams.setInstitutionCode("0D");
            l_hostAttentionInfoNotifyParams.setSonarMarketCode("5");
            l_hostAttentionInfoNotifyParams.setProductCode("11111");
            l_hostAttentionInfoNotifyParams.setInfoGenerationTimestamp(Calendar.getInstance().getTime());
            //注意情報区分コード
            l_hostAttentionInfoNotifyParams.setAttentionInfoDivCode("A001");
            //基準値（変更後）
            l_hostAttentionInfoNotifyParams.setBasePrice(19);
            //制限値幅上限
            l_hostAttentionInfoNotifyParams.setHighPriceRange(25);
            //制限値幅下限
            l_hostAttentionInfoNotifyParams.setLowPriceRange(15);
            //情報発生日時
            l_hostAttentionInfoNotifyParams.setInfoGenerationTimestamp(Calendar.getInstance().getTime());
            //注文受付再開日時
            l_hostAttentionInfoNotifyParams.setOrdReceiptRestartTimestamp(Calendar.getInstance().getTime());
            //売買停止日時／売買再開日時
            l_hostAttentionInfoNotifyParams.setTradeStopRestartTimestamp(Calendar.getInstance().getTime());
            //表題
            l_hostAttentionInfoNotifyParams.setFreeFormatTitle("faffffsdksadkhhhkakfksfhk");
            //本文
            l_hostAttentionInfoNotifyParams.setFreeFormatText("asdasdsadsadasdasdsadas");
//            l_hostAttentionInfoNotifyParams.setStatus("s");
            TestDBUtility.insertWithDelAndCommit(l_hostAttentionInfoNotifyParams);
            
            TestDBUtility.deleteAllAndCommit(MailInfoParams.TYPE);
            MailInfoParams l_mailInfoParams = new MailInfoParams();
            l_mailInfoParams.setInstitutionCode("0D");//
            l_mailInfoParams.setSendmailDiv("3102");//
            l_mailInfoParams.setDiscernmentId("1");//
            l_mailInfoParams.setSubject("あいうえおabced1258");
            l_mailInfoParams.setLastUpdater("0213");
            l_mailInfoParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_mailInfoParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_mailInfoParams.setMailText(
                    "old_estimation_price = %%old_estimation_price%%" +
                    "old_last_closing_price = %%old_last_closing_price%%" +
                    "old_base_price = %%old_base_price%%" +
                    "old_price_range_type = %%old_price_range_type%%" +
                    "old_price_range_unit_type = %%old_price_range_unit_type%%" +
                    "old_high_price_range = %%old_high_price_range%%" +
                    "old_low_price_range = %%old_low_price_range%%" +
                    "old_last_closing_price_updq = %%old_last_closing_price_updq%%" +
                    "old_base_price_updq = %%old_base_price_updq%%");
            TestDBUtility.insertWithDelAndCommit(l_mailInfoParams);
            
            TestDBUtility.deleteAllAndCommit(EqtypeTradedProductUpdqParams.TYPE);
            EqtypeTradedProductUpdqParams l_eqtypeTradedProductUpdqParams = TestDBUtility.getEqtypeTradedProductUpdqRow();
            l_eqtypeTradedProductUpdqParams.setProductId(111111111111L);
            l_eqtypeTradedProductUpdqParams.setMarketId(3303L);
            l_eqtypeTradedProductUpdqParams.setValidUntilBizDate(WEB3DateUtility.formatDate(l_finApp.getTradingSystem().getBizDate(), WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            TestDBUtility.insertWithDelAndCommit(l_eqtypeTradedProductUpdqParams);
            
            TestDBUtility.deleteAllAndCommit(AttentionInfoHistoryParams.TYPE);
            TestDBUtility.deleteAllAndCommit(MailProcRow.TYPE);
            
            l_service.execute(l_request);

            Object[] l_values1 = {"0D"};
            QueryProcessor l_processor1 = Processors.getDefaultProcessor();
            List l_lisMailProcRows = l_processor1.doFindAllQuery(
                    MailProcRow.TYPE,
                    "institution_code = ?",
                    l_values1);
            MailProcRow l_row = (MailProcRow)l_lisMailProcRows.get(0);
            //String l_str = l_row.getMailText();
            /*assertEquals("old_estimation_price = " +
                    "old_last_closing_price = " +
                    "old_base_price = " +
                    "old_price_range_type = " +
                    "old_price_range_unit_type = " +
                    "old_high_price_range = " +
                    "old_low_price_range = " +
                    "old_last_closing_price_updq = " +
                    "old_base_price_updq = ", l_str);*/
            assertEquals(" ", l_row.getMailText());
            

        } catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    //注意情報通知キュー.データコード＝"注意情報（売停情報）通知"の場合
    //更新前の銘柄 = null & 市場 = null  & 更新前の株式取引銘柄 =null & 更新前の株式取引銘柄updq =null
    //注意情報通知キュー.処理区分＝"1：処理済"でかつ
    //　@銘柄更新フラグ＝"1：銘柄更新済み"の場合
    public void testInsertAttentionInfoHistory_C0001()
    {
        final String STR_METHOD_NAME = "testInsertAttentionInfoHistory_C0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(AttentionInfoHistoryRow.TYPE);
            HostAttentionInfoNotifyParams l_hostAttentionInfoNotifyParams = new HostAttentionInfoNotifyParams();
            l_hostAttentionInfoNotifyParams.setRequestCode("AXQY1");//"注意情報（売停情報）通知"
            l_hostAttentionInfoNotifyParams.setInstitutionCode("0D");
            l_hostAttentionInfoNotifyParams.setAttentionInfoDivCode("A001");//注意情報区分コード
            l_hostAttentionInfoNotifyParams.setInfoGenerationTimestamp(WEB3DateUtility.getDate("20090109", "yyyyMMdd"));
            l_hostAttentionInfoNotifyParams.setOrdReceiptRestartTimestamp(WEB3DateUtility.getDate("20090109", "yyyyMMdd"));
            l_hostAttentionInfoNotifyParams.setTradeStopRestartTimestamp(WEB3DateUtility.getDate("20090109", "yyyyMMdd"));
            l_hostAttentionInfoNotifyParams.setStatus("1");//処理区分＝"1：処理済"
            
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_marketParams);
            Market l_market = new WEB3GentradeMarket(3303L);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setTradedProductId(1006160060005L);
            l_eqtypeTradedProductParams.setProductId(3304148080000L);
            l_eqtypeTradedProductParams.setBasePrice(10.0D);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);

            WEB3AdminEquityAttentionInfoNotifyTransactionCallback l_callback =
                l_impl.new WEB3AdminEquityAttentionInfoNotifyTransactionCallback();
            
            Method l_method = WEB3AdminEquityAttentionInfoNotifyTransactionCallback.class.getDeclaredMethod(
                    "insertAttentionInfoHistory",
                    new Class[]{HostAttentionInfoNotifyParams.class,
                            Market.class, ProductRow.class, EqtypeTradedProductRow.class, 
                            EqtypeTradedProductUpdqRow.class, EqtypeProductRow.class, String.class});

            
            l_method.setAccessible(true);
            Object[] l_obj =
                {l_hostAttentionInfoNotifyParams, l_market, null, null, null, null, "1"};
            AttentionInfoHistoryParams l_params = (AttentionInfoHistoryParams)l_method.invoke(l_callback, l_obj);

            //注意情報履歴ID = 自動採番した値
            //注意情報種別   =  "1：売停情報"
            assertEquals("1", l_params.getAttentionInfoType());
            //証券会社コード = 注意情報通知キュー.証券会社コード (0D)
            assertEquals("0D", l_params.getInstitutionCode());
            //銘柄ＩＤ = null
            //銘柄名 = null
            //市場ＩＤ = null
            //有効日 = GtlUtils.getSystemTimestamp( )のYYYYMMDD
            assertEquals(WEB3DateUtility.formatDate(
                    GtlUtils.getSystemTimestamp(), "yyyyMMdd"), l_params.getValidUntilBizDate());
            //注意情報区分コード = 注意情報通知キュー.注意情報区分コード (A001)
            assertEquals("A001", l_params.getAttentionInfoDivCode());
            //評価単価（変更前） = null
            assertTrue(l_params.getOldEstimationPriceIsNull());
            //評価単価（変更後） = null
            assertTrue(l_params.getNewEstimationPriceIsNull());
            //基準値（終値）（変更前） = null
            assertTrue(l_params.getOldLastClosingPriceIsNull());
            //基準値（終値）（変更後） = null
            assertTrue(l_params.getNewLastClosingPriceIsNull());
            //基準値（変更前） = null
            assertTrue(l_params.getOldBasePriceIsNull());
            //基準値（変更後） = null
            assertTrue(l_params.getNewBasePriceIsNull());
            //値幅チェック区分（変更前） = null
            assertNull(l_params.getOldPriceRangeType());
            //値幅チェック区分(変更後) = null
            assertNull(l_params.getNewPriceRangeType());
            //値幅区分（変更前） = null
            assertNull(l_params.getOldPriceRangeUnitType());
            //値幅区分（変更後） = null
            assertNull(l_params.getNewPriceRangeUnitType());
            //強制値幅（上限値）（変更前） = null
            assertTrue(l_params.getOldHighCompPriceRangeIsNull());
            //制限値幅上限（変更後） = null
            assertTrue(l_params.getNewHighPriceRangeIsNull());
            //強制値幅（下限値）（変更前） = null
            assertTrue(l_params.getOldLowCompPriceRangeIsNull());
            //制限値幅下限（変更後） = null
            assertTrue(l_params.getNewLowPriceRangeIsNull());
            //基準値（終値）（翌日）（変更前） = null
            assertTrue(l_params.getOldLastClosingPriceUpdqIsNull());
            //基準値（終値）（翌日）（変更後） = null
            assertTrue(l_params.getNewLastClosingPriceUpdqIsNull());
            //基準値（翌日）（変更前） = null
            assertTrue(l_params.getOldBasePriceUpdqIsNull());
            //基準値（翌日）（変更後） = null
            assertTrue(l_params.getNewBasePriceUpdqIsNull());
            //表題 = null
            assertNull(l_params.getFreeFormatTitle());
            //本文 = null
            assertNull(l_params.getFreeFormatText());
            //情報発生日時 = 注意情報通知キュー.情報発生日時
            assertEquals(WEB3DateUtility.getDate("20090109", "yyyyMMdd"), l_params.getInfoGenerationTimestamp());
            //注文受付再開日時 = 注意情報通知キュー.注文受付再開日時
            assertEquals(WEB3DateUtility.getDate("20090109", "yyyyMMdd"), l_params.getOrdReceiptRestartTimestamp());
            //売買停止日時／売買再開日時 = 注意情報通知キュー.売買停止日時／売買再開日時
            assertEquals(WEB3DateUtility.getDate("20090109", "yyyyMMdd"), l_params.getTradeStopRestartTimestamp());
            //処理結果区分 = 1：正常
            assertEquals("1", l_params.getProcessResultDiv());
            //作成日付 = 現在日時
            //更新日付 = 現在日時
        }
        catch (Exception l_ex)
        {
            log.debug(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    //注意情報通知キュー.データコード＝"注意情報（制限値幅情報）通知"の場合※１　@
    //更新前の銘柄 = null & 市場 =null  & 更新前の株式取引銘柄 =null & 更新前の株式取引銘柄updq =null
    //注意情報通知キュー.処理区分＝"1：処理済"でかつ
    // 銘柄更新フラグ＝"2：銘柄未更新"の場合
    public void testInsertAttentionInfoHistory_C0002()
    {
        final String STR_METHOD_NAME = "testInsertAttentionInfoHistory_C0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(AttentionInfoHistoryRow.TYPE);
            HostAttentionInfoNotifyParams l_hostAttentionInfoNotifyParams = new HostAttentionInfoNotifyParams();
            l_hostAttentionInfoNotifyParams.setRequestCode("AXRY1");//"注意情報（制限値幅情報）通知"
            l_hostAttentionInfoNotifyParams.setInstitutionCode("0D");
            l_hostAttentionInfoNotifyParams.setAttentionInfoDivCode("A001");//注意情報区分コード
            l_hostAttentionInfoNotifyParams.setInfoGenerationTimestamp(WEB3DateUtility.getDate("20090109", "yyyyMMdd"));
            l_hostAttentionInfoNotifyParams.setOrdReceiptRestartTimestamp(WEB3DateUtility.getDate("20090109", "yyyyMMdd"));
            l_hostAttentionInfoNotifyParams.setTradeStopRestartTimestamp(WEB3DateUtility.getDate("20090109", "yyyyMMdd"));
            l_hostAttentionInfoNotifyParams.setStatus("1");//処理区分＝1：処理済
            l_hostAttentionInfoNotifyParams.setBasePrice(100);//注意情報通知キュー.基準値
            l_hostAttentionInfoNotifyParams.setHighPriceRange(200);//制限値幅上限
            l_hostAttentionInfoNotifyParams.setLowPriceRange(10);//制限値幅下限
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_marketParams);

            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setTradedProductId(1006160060005L);
            l_eqtypeTradedProductParams.setProductId(3304148080000L);
            l_eqtypeTradedProductParams.setBasePrice(10.0D);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);

            WEB3AdminEquityAttentionInfoNotifyTransactionCallback l_callback =
                l_impl.new WEB3AdminEquityAttentionInfoNotifyTransactionCallback();
            
            Method l_method = WEB3AdminEquityAttentionInfoNotifyTransactionCallback.class.getDeclaredMethod(
                    "insertAttentionInfoHistory",
                    new Class[]{HostAttentionInfoNotifyParams.class,
                            Market.class, ProductRow.class, EqtypeTradedProductRow.class, 
                            EqtypeTradedProductUpdqRow.class, EqtypeProductRow.class, String.class});

            
            l_method.setAccessible(true);
            Object[] l_obj =
                {l_hostAttentionInfoNotifyParams, null, null, null, null, null, "2"};
            AttentionInfoHistoryParams l_params = (AttentionInfoHistoryParams)l_method.invoke(l_callback, l_obj);

            //注意情報履歴ID = 自動採番した値
            //注意情報種別   =  "2：制限値幅情報"
            assertEquals("2", l_params.getAttentionInfoType());
            //証券会社コード = 注意情報通知キュー.証券会社コード (0D)
            assertEquals("0D", l_params.getInstitutionCode());
            //銘柄ＩＤ = null
            assertTrue(l_params.getProductIdIsNull());
            //銘柄名 = null
            assertNull(l_params.getStandardName());
            //市場ＩＤ = null
            assertTrue(l_params.getMarketIdIsNull());
            //有効日 = GtlUtils.getSystemTimestamp( )のYYYYMMDD
            assertEquals(WEB3DateUtility.formatDate(
                    GtlUtils.getSystemTimestamp(), "yyyyMMdd"), l_params.getValidUntilBizDate());
            //注意情報区分コード = 注意情報通知キュー.注意情報区分コード (A001)
            assertEquals("A001", l_params.getAttentionInfoDivCode());
            //評価単価（変更前） = null
            assertTrue(l_params.getOldEstimationPriceIsNull());
            //評価単価（変更後） = 注意情報通知キュー.基準値
            assertEquals(100, l_params.getNewEstimationPrice(), 0);
            //基準値（終値）（変更前） = null
            assertTrue(l_params.getOldLastClosingPriceIsNull());
            //基準値（終値）（変更後） = 注意情報通知キュー.基準値
            assertEquals(100, l_params.getNewLastClosingPrice(), 0);
            //基準値（変更前） = null
            assertTrue(l_params.getOldBasePriceIsNull());
            //基準値（変更後） = 注意情報通知キュー.基準値
            assertEquals(100, l_params.getNewBasePrice(), 0);
            //値幅チェック区分（変更前） = null
            assertNull(l_params.getOldPriceRangeType());
            //値幅チェック区分(変更後) = "1:値幅チェックあり"
            assertEquals("1", l_params.getNewPriceRangeType());
            //値幅区分（変更前） = null
            assertNull(l_params.getOldPriceRangeUnitType());
            //値幅区分（変更後） = "1：円"
            assertEquals("1", l_params.getNewPriceRangeUnitType());
            //強制値幅（上限値）（変更前） = null
            assertTrue(l_params.getOldHighCompPriceRangeIsNull());
            //制限値幅上限（変更後） = 注意情報通知キュー.制限値幅上限
            assertEquals(200, l_params.getNewHighPriceRange(), 0);
            //強制値幅（下限値）（変更前） = null
            assertTrue(l_params.getOldLowCompPriceRangeIsNull());
            //制限値幅下限（変更後） = 注意情報通知キュー.制限値幅下限
            assertEquals(10, l_params.getNewLowPriceRange(), 0);
            //基準値（終値）（翌日）（変更前） = null
            assertTrue(l_params.getOldLastClosingPriceUpdqIsNull());
            //基準値（終値）（翌日）（変更後） = 注意情報通知キュー.基準値
            assertEquals(100, l_params.getNewLastClosingPriceUpdq(), 0);
            //基準値（翌日）（変更前） = null
            assertTrue(l_params.getOldBasePriceUpdqIsNull());
            //基準値（翌日）（変更後） = 注意情報通知キュー.基準値
            assertEquals(100, l_params.getNewBasePriceUpdq(), 0);
            //表題 = null
            assertNull(l_params.getFreeFormatTitle());
            //本文 = null
            assertNull(l_params.getFreeFormatText());
            //情報発生日時 = 注意情報通知キュー.情報発生日時
            assertEquals(WEB3DateUtility.getDate("20090109", "yyyyMMdd"), l_params.getInfoGenerationTimestamp());
            //注文受付再開日時 = 注意情報通知キュー.注文受付再開日時
            assertEquals(WEB3DateUtility.getDate("20090109", "yyyyMMdd"), l_params.getOrdReceiptRestartTimestamp());
            //売買停止日時／売買再開日時 = 注意情報通知キュー.売買停止日時／売買再開日時
            assertEquals(WEB3DateUtility.getDate("20090109", "yyyyMMdd"), l_params.getTradeStopRestartTimestamp());
            //処理結果区分 = 2：正常（更新無）
            assertEquals("2", l_params.getProcessResultDiv());
            //作成日付 = 現在日時
            //更新日付 = 現在日時
        }
        catch (Exception l_ex)
        {
            log.debug(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    //注意情報通知キュー.データコード＝"注意情報（制限値幅情報）通知"の場合※１　@
    //更新前の銘柄 ≠ null & 市場 ≠null & 更新前の株式取引銘柄 ≠ null & 更新前の株式取引銘柄updq ≠ null
    //注意情報通知キュー.処理区分＝"9：エラー"の場合
    public void testInsertAttentionInfoHistory_C0003()
    {
        final String STR_METHOD_NAME = "testInsertAttentionInfoHistory_C0003()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(AttentionInfoHistoryRow.TYPE);
            HostAttentionInfoNotifyParams l_hostAttentionInfoNotifyParams = new HostAttentionInfoNotifyParams();
            l_hostAttentionInfoNotifyParams.setRequestCode("AXRY1");//"注意情報（制限値幅情報）通知"
            l_hostAttentionInfoNotifyParams.setInstitutionCode("0D");
            l_hostAttentionInfoNotifyParams.setAttentionInfoDivCode("A001");//注意情報区分コード
            l_hostAttentionInfoNotifyParams.setInfoGenerationTimestamp(WEB3DateUtility.getDate("20090109", "yyyyMMdd"));
            l_hostAttentionInfoNotifyParams.setOrdReceiptRestartTimestamp(WEB3DateUtility.getDate("20090109", "yyyyMMdd"));
            l_hostAttentionInfoNotifyParams.setTradeStopRestartTimestamp(WEB3DateUtility.getDate("20090109", "yyyyMMdd"));
            l_hostAttentionInfoNotifyParams.setStatus("9");//処理区分＝9：エラー
            l_hostAttentionInfoNotifyParams.setBasePrice(100);//注意情報通知キュー.基準値
            l_hostAttentionInfoNotifyParams.setHighPriceRange(200);//制限値幅上限
            l_hostAttentionInfoNotifyParams.setLowPriceRange(10);//制限値幅下限
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_marketParams);
            Market l_market = new WEB3GentradeMarket(3303L);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setStandardName("シンセンテルス");
            l_productParams.setEstimationPrice(123);
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setTradedProductId(1006160060005L);
            l_eqtypeTradedProductParams.setProductId(3304148080000L);
            l_eqtypeTradedProductParams.setBasePrice(10.0D);
            l_eqtypeTradedProductParams.setLastClosingPrice(234);
            l_eqtypeTradedProductParams.setPriceRangeType("0");
            l_eqtypeTradedProductParams.setPriceRangeUnitType("0");
            l_eqtypeTradedProductParams.setHighCompulsivePriceRange(200);
            l_eqtypeTradedProductParams.setLowCompulsivePriceRange(10);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);

            TestDBUtility.deleteAll(EqtypeTradedProductUpdqRow.TYPE);
            EqtypeTradedProductUpdqParams l_eqtypeTradedProductUpdqParams =
                TestDBUtility.getEqtypeTradedProductUpdqRow();
            l_eqtypeTradedProductUpdqParams.setTradedProductId(1006160060005L);
            l_eqtypeTradedProductUpdqParams.setProductId(3304148080000L);
            l_eqtypeTradedProductUpdqParams.setMarketId(3303L);
            l_eqtypeTradedProductUpdqParams.setValidUntilBizDate("20040917");
            l_eqtypeTradedProductUpdqParams.setLastClosingPrice(50);//基準値（終値）
            l_eqtypeTradedProductUpdqParams.setLastUpdater("XXXX");//更新者コード
            l_eqtypeTradedProductUpdqParams.setBasePrice(50);//基準値
            
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setInstitutionCode("0D");
            l_eqtypeProductParams.setProductId(111111111111L);
            l_eqtypeProductParams.setStandardName("あいうえお");
            l_eqtypeProductParams.setProductCode("11111");
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            WEB3AdminEquityAttentionInfoNotifyTransactionCallback l_callback =
                l_impl.new WEB3AdminEquityAttentionInfoNotifyTransactionCallback();
            
            Method l_method = WEB3AdminEquityAttentionInfoNotifyTransactionCallback.class.getDeclaredMethod(
                    "insertAttentionInfoHistory",
                    new Class[]{HostAttentionInfoNotifyParams.class,
                            Market.class, ProductRow.class, EqtypeTradedProductRow.class, 
                            EqtypeTradedProductUpdqRow.class, EqtypeProductRow.class, String.class});

            
            l_method.setAccessible(true);
            Object[] l_obj =
                {l_hostAttentionInfoNotifyParams, l_market, l_productParams,
                    l_eqtypeTradedProductParams, l_eqtypeTradedProductUpdqParams, l_eqtypeProductParams, "1"};
            AttentionInfoHistoryParams l_params = (AttentionInfoHistoryParams)l_method.invoke(l_callback, l_obj);

            //注意情報履歴ID = 自動採番した値
            //注意情報種別   =  "2：制限値幅情報"
            assertEquals("2", l_params.getAttentionInfoType());
            //証券会社コード = 注意情報通知キュー.証券会社コード (0D)
            assertEquals("0D", l_params.getInstitutionCode());
            //銘柄ＩＤ = 更新前の銘柄.銘柄ＩＤ
            assertEquals(111111111111L, l_params.getProductId());
            //銘柄名 = 更新前の銘柄.銘柄名
            assertEquals("あいうえお", l_params.getStandardName());
            //市場ＩＤ = 市場.市場ＩＤ
            assertEquals(3303L, l_params.getMarketId());
            //有効日 = GtlUtils.getSystemTimestamp( )のYYYYMMDD
            assertEquals(WEB3DateUtility.formatDate(
                    GtlUtils.getSystemTimestamp(), "yyyyMMdd"), l_params.getValidUntilBizDate());
            //注意情報区分コード = 注意情報通知キュー.注意情報区分コード (A001)
            assertEquals("A001", l_params.getAttentionInfoDivCode());
            //評価単価（変更前） = 更新前の銘柄.評価単価
            assertEquals(123, l_params.getOldEstimationPrice(), 0);
            //評価単価（変更後） = 注意情報通知キュー.基準値
            assertEquals(100, l_params.getNewEstimationPrice(), 0);
            //基準値（終値）（変更前） = 更新前の株式取引銘柄.基準値（終値）
            assertEquals(234, l_params.getOldLastClosingPrice(), 0);
            //基準値（終値）（変更後） = 注意情報通知キュー.基準値
            assertEquals(100, l_params.getNewLastClosingPrice(), 0);
            //基準値（変更前） = 更新前の株式取引銘柄.基準値
            assertEquals(10, l_params.getOldBasePrice(), 0);
            //基準値（変更後） = 注意情報通知キュー.基準値
            assertEquals(100, l_params.getNewBasePrice(), 0);
            //値幅チェック区分（変更前） = 更新前の株式取引銘柄.値幅チェック区分
            assertEquals("0", l_params.getOldPriceRangeType());
            //値幅チェック区分(変更後) = "1:値幅チェックあり"
            assertEquals("1", l_params.getNewPriceRangeType());
            //値幅区分（変更前） = 更新前の株式取引銘柄.値幅区分
            assertEquals("0", l_params.getOldPriceRangeUnitType());
            //値幅区分（変更後） = "1：円"
            assertEquals("1", l_params.getNewPriceRangeUnitType());
            //強制値幅（上限値）（変更前） = 更新前の株式取引銘柄.強制値幅（上限値）
            assertEquals(200, l_params.getOldHighCompPriceRange(), 0);
            //制限値幅上限（変更後） = 注意情報通知キュー.制限値幅上限
            assertEquals(200, l_params.getNewHighPriceRange(), 0);
            //強制値幅（下限値）（変更前） = 更新前の株式取引銘柄.強制値幅（下限値）
            assertEquals(10, l_params.getOldLowCompPriceRange(), 0);
            //制限値幅下限（変更後） = 注意情報通知キュー.制限値幅下限
            assertEquals(10, l_params.getNewLowPriceRange(), 0);
            //基準値（終値）（翌日）（変更前） = 更新前の株式取引銘柄updq.基準値（終値）
            assertEquals(50, l_params.getOldLastClosingPriceUpdq(), 0);
            //基準値（終値）（翌日）（変更後） = 注意情報通知キュー.基準値
            assertEquals(100, l_params.getNewLastClosingPriceUpdq(), 0);
            //基準値（翌日）（変更前） = 更新前の株式取引銘柄updq.基準値
            assertEquals(50, l_params.getOldBasePriceUpdq(), 0);
            //基準値（翌日）（変更後） = 注意情報通知キュー.基準値
            assertEquals(100, l_params.getNewBasePriceUpdq(), 0);
            //表題 = null
            assertNull(l_params.getFreeFormatTitle());
            //本文 = null
            assertNull(l_params.getFreeFormatText());
            //情報発生日時 = 注意情報通知キュー.情報発生日時
            assertEquals(WEB3DateUtility.getDate("20090109", "yyyyMMdd"), l_params.getInfoGenerationTimestamp());
            //注文受付再開日時 = 注意情報通知キュー.注文受付再開日時
            assertEquals(WEB3DateUtility.getDate("20090109", "yyyyMMdd"), l_params.getOrdReceiptRestartTimestamp());
            //売買停止日時／売買再開日時 = 注意情報通知キュー.売買停止日時／売買再開日時
            assertEquals(WEB3DateUtility.getDate("20090109", "yyyyMMdd"), l_params.getTradeStopRestartTimestamp());
            //処理結果区分 = 9：エラー
            assertEquals("9", l_params.getProcessResultDiv());
            //作成日付 = 現在日時
            //更新日付 = 現在日時
        }
        catch (Exception l_ex)
        {
            log.debug(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    //※２　@注意情報通知キューテーブル.データコード＝"注意情報（フリーフォーマット）通知"の場合セット
    //更新前の銘柄 = null & 市場 =null & 更新前の株式取引銘柄 =null & 更新前の株式取引銘柄updq =null
    public void testInsertAttentionInfoHistory_C0004()
    {
        final String STR_METHOD_NAME = "testInsertAttentionInfoHistory_C0004()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(AttentionInfoHistoryRow.TYPE);
            HostAttentionInfoNotifyParams l_hostAttentionInfoNotifyParams = new HostAttentionInfoNotifyParams();
            l_hostAttentionInfoNotifyParams.setRequestCode("AXSY1");//"注意情報（フリーフォーマット）通知"
            l_hostAttentionInfoNotifyParams.setInstitutionCode("0D");
            l_hostAttentionInfoNotifyParams.setAttentionInfoDivCode("A001");//注意情報区分コード
            l_hostAttentionInfoNotifyParams.setInfoGenerationTimestamp(WEB3DateUtility.getDate("20090109", "yyyyMMdd"));
            l_hostAttentionInfoNotifyParams.setOrdReceiptRestartTimestamp(WEB3DateUtility.getDate("20090109", "yyyyMMdd"));
            l_hostAttentionInfoNotifyParams.setTradeStopRestartTimestamp(WEB3DateUtility.getDate("20090109", "yyyyMMdd"));
            l_hostAttentionInfoNotifyParams.setStatus("1");//処理区分＝1：正常
            l_hostAttentionInfoNotifyParams.setBasePrice(100);//注意情報通知キュー.基準値
            l_hostAttentionInfoNotifyParams.setHighPriceRange(200);//制限値幅上限
            l_hostAttentionInfoNotifyParams.setLowPriceRange(10);//制限値幅下限
            l_hostAttentionInfoNotifyParams.setFreeFormatTitle("tttt");//表題
            l_hostAttentionInfoNotifyParams.setFreeFormatText("yyyy");//本文

            WEB3AdminEquityAttentionInfoNotifyTransactionCallback l_callback =
                l_impl.new WEB3AdminEquityAttentionInfoNotifyTransactionCallback();
            
            Method l_method = WEB3AdminEquityAttentionInfoNotifyTransactionCallback.class.getDeclaredMethod(
                    "insertAttentionInfoHistory",
                    new Class[]{HostAttentionInfoNotifyParams.class,
                            Market.class, ProductRow.class, EqtypeTradedProductRow.class, 
                            EqtypeTradedProductUpdqRow.class, EqtypeProductRow.class, String.class});

            
            l_method.setAccessible(true);
            Object[] l_obj =
                {l_hostAttentionInfoNotifyParams, null, null,
                    null, null, null, "1"};
            AttentionInfoHistoryParams l_params = (AttentionInfoHistoryParams)l_method.invoke(l_callback, l_obj);

            //注意情報履歴ID = 自動採番した値
            //注意情報種別   =  "3：フリーフォーマット"
            assertEquals("3", l_params.getAttentionInfoType());
            //証券会社コード = 注意情報通知キュー.証券会社コード (0D)
            assertEquals("0D", l_params.getInstitutionCode());
            //銘柄ＩＤ = null
            assertTrue(l_params.getProductIdIsNull());
            //銘柄名 = null
            assertNull(l_params.getStandardName());
            //市場ＩＤ = null
            assertTrue(l_params.getMarketIdIsNull());
            //有効日 = GtlUtils.getSystemTimestamp( )のYYYYMMDD
            assertEquals(WEB3DateUtility.formatDate(
                    GtlUtils.getSystemTimestamp(), "yyyyMMdd"), l_params.getValidUntilBizDate());
            //注意情報区分コード = 注意情報通知キュー.注意情報区分コード (A001)
            assertEquals("A001", l_params.getAttentionInfoDivCode());
            //評価単価（変更前） = null
            assertTrue(l_params.getOldEstimationPriceIsNull());
            //評価単価（変更後） = null
            assertTrue(l_params.getNewEstimationPriceIsNull());
            //基準値（終値）（変更前） = null
            assertTrue(l_params.getOldLastClosingPriceIsNull());
            //基準値（終値）（変更後） = null
            assertTrue(l_params.getNewLastClosingPriceIsNull());
            //基準値（変更前） = null
            assertTrue(l_params.getOldBasePriceIsNull());
            //基準値（変更後） = null
            assertTrue(l_params.getNewBasePriceIsNull());
            //値幅チェック区分（変更前） = null
            assertNull(l_params.getOldPriceRangeType());
            //値幅チェック区分(変更後) = null
            assertNull(l_params.getNewPriceRangeType());
            //値幅区分（変更前） = null
            assertNull(l_params.getOldPriceRangeUnitType());
            //値幅区分（変更後） = null
            assertNull(l_params.getNewPriceRangeUnitType());
            //強制値幅（上限値）（変更前） = null
            assertTrue(l_params.getOldHighCompPriceRangeIsNull());
            //制限値幅上限（変更後） = null
            assertTrue(l_params.getNewHighPriceRangeIsNull());
            //強制値幅（下限値）（変更前） = null
            assertTrue(l_params.getOldLowCompPriceRangeIsNull());
            //制限値幅下限（変更後） = null
            assertTrue(l_params.getNewLowPriceRangeIsNull());
            //基準値（終値）（翌日）（変更前） = null
            assertTrue(l_params.getOldLastClosingPriceUpdqIsNull());
            //基準値（終値）（翌日）（変更後） = null
            assertTrue(l_params.getNewLastClosingPriceUpdqIsNull());
            //基準値（翌日）（変更前） = null
            assertTrue(l_params.getOldBasePriceUpdqIsNull());
            //基準値（翌日）（変更後） = null
            assertTrue(l_params.getNewBasePriceUpdqIsNull());
            //表題 = 注意情報通知キュー.表題
            assertEquals("tttt", l_params.getFreeFormatTitle());
            //本文 = 注意情報通知キュー.本文
            assertEquals("yyyy", l_params.getFreeFormatText());
            //情報発生日時 = 注意情報通知キュー.情報発生日時
            assertEquals(WEB3DateUtility.getDate("20090109", "yyyyMMdd"), l_params.getInfoGenerationTimestamp());
            //注文受付再開日時 = 注意情報通知キュー.注文受付再開日時
            assertEquals(WEB3DateUtility.getDate("20090109", "yyyyMMdd"), l_params.getOrdReceiptRestartTimestamp());
            //売買停止日時／売買再開日時 = 注意情報通知キュー.売買停止日時／売買再開日時
            assertEquals(WEB3DateUtility.getDate("20090109", "yyyyMMdd"), l_params.getTradeStopRestartTimestamp());
            //処理結果区分 = 1：正常
            assertEquals("1", l_params.getProcessResultDiv());
            //作成日付 = 現在日時
            //更新日付 = 現在日時
        }
        catch (Exception l_ex)
        {
            log.debug(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    //※２　@注意情報通知キューテーブル.データコード＝"注意情報（フリーフォーマット）通知"の場合セット
    //更新前の銘柄 ≠ null & 市場 ≠null & 更新前の株式取引銘柄 ≠ null & 更新前の株式取引銘柄updq ≠ null
    public void testInsertAttentionInfoHistory_C0005()
    {
        final String STR_METHOD_NAME = "testInsertAttentionInfoHistory_C0005()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(AttentionInfoHistoryRow.TYPE);
            HostAttentionInfoNotifyParams l_hostAttentionInfoNotifyParams = new HostAttentionInfoNotifyParams();
            l_hostAttentionInfoNotifyParams.setRequestCode("AXSY1");//"注意情報（フリーフォーマット）通知"
            l_hostAttentionInfoNotifyParams.setInstitutionCode("0D");
            l_hostAttentionInfoNotifyParams.setAttentionInfoDivCode("A001");//注意情報区分コード
            l_hostAttentionInfoNotifyParams.setInfoGenerationTimestamp(WEB3DateUtility.getDate("20090109", "yyyyMMdd"));
            l_hostAttentionInfoNotifyParams.setOrdReceiptRestartTimestamp(WEB3DateUtility.getDate("20090109", "yyyyMMdd"));
            l_hostAttentionInfoNotifyParams.setTradeStopRestartTimestamp(WEB3DateUtility.getDate("20090109", "yyyyMMdd"));
            l_hostAttentionInfoNotifyParams.setStatus("1");//処理区分＝1：正常
            l_hostAttentionInfoNotifyParams.setBasePrice(100);//注意情報通知キュー.基準値
            l_hostAttentionInfoNotifyParams.setHighPriceRange(200);//制限値幅上限
            l_hostAttentionInfoNotifyParams.setLowPriceRange(10);//制限値幅下限
            l_hostAttentionInfoNotifyParams.setLowPriceRange(10);//制限値幅下限
            l_hostAttentionInfoNotifyParams.setFreeFormatTitle("tttt");//表題
            l_hostAttentionInfoNotifyParams.setFreeFormatText("yyyy");//本文
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_marketParams);
            Market l_market = new WEB3GentradeMarket(3303L);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setStandardName("シンセンテルス");
            l_productParams.setEstimationPrice(123);
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setTradedProductId(1006160060005L);
            l_eqtypeTradedProductParams.setProductId(3304148080000L);
            l_eqtypeTradedProductParams.setBasePrice(10.0D);
            l_eqtypeTradedProductParams.setLastClosingPrice(234);
            l_eqtypeTradedProductParams.setPriceRangeType("0");
            l_eqtypeTradedProductParams.setPriceRangeUnitType("0");
            l_eqtypeTradedProductParams.setHighCompulsivePriceRange(200);
            l_eqtypeTradedProductParams.setLowCompulsivePriceRange(10);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);

            TestDBUtility.deleteAll(EqtypeTradedProductUpdqRow.TYPE);
            EqtypeTradedProductUpdqParams l_eqtypeTradedProductUpdqParams =
                TestDBUtility.getEqtypeTradedProductUpdqRow();
            l_eqtypeTradedProductUpdqParams.setTradedProductId(1006160060005L);
            l_eqtypeTradedProductUpdqParams.setProductId(3304148080000L);
            l_eqtypeTradedProductUpdqParams.setMarketId(3303L);
            l_eqtypeTradedProductUpdqParams.setValidUntilBizDate("20040917");
            l_eqtypeTradedProductUpdqParams.setLastClosingPrice(50);//基準値（終値）
            l_eqtypeTradedProductUpdqParams.setLastUpdater("XXXX");//更新者コード
            l_eqtypeTradedProductUpdqParams.setBasePrice(50);//基準値
            
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setInstitutionCode("0D");
            l_eqtypeProductParams.setProductId(111111111111L);
            l_eqtypeProductParams.setStandardName("あいうえお");
            l_eqtypeProductParams.setProductCode("11111");
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            WEB3AdminEquityAttentionInfoNotifyTransactionCallback l_callback =
                l_impl.new WEB3AdminEquityAttentionInfoNotifyTransactionCallback();
            
            Method l_method = WEB3AdminEquityAttentionInfoNotifyTransactionCallback.class.getDeclaredMethod(
                    "insertAttentionInfoHistory",
                    new Class[]{HostAttentionInfoNotifyParams.class,
                            Market.class, ProductRow.class, EqtypeTradedProductRow.class, 
                            EqtypeTradedProductUpdqRow.class, EqtypeProductRow.class, String.class});

            
            l_method.setAccessible(true);
            Object[] l_obj =
                {l_hostAttentionInfoNotifyParams, l_market, l_productParams,
                    l_eqtypeTradedProductParams, l_eqtypeTradedProductUpdqParams, l_eqtypeProductParams, "1"};
            l_method.invoke(l_callback, l_obj);

            QueryProcessor l_processor = Processors.getDefaultProcessor();
            String l_strQuery = " product_id = ?";
            Object[] l_dataContainers = {new Long(111111111111L)};
           
            List l_lisRow = l_processor.doFindAllQuery(AttentionInfoHistoryRow.TYPE, 
                l_strQuery, l_dataContainers);
            AttentionInfoHistoryRow l_params = (AttentionInfoHistoryRow)l_lisRow.get(0);
            //注意情報履歴ID = 自動採番した値
            //注意情報種別   =  "3：フリーフォーマット"
            assertEquals("3", l_params.getAttentionInfoType());
            //証券会社コード = 注意情報通知キュー.証券会社コード (0D)
            assertEquals("0D", l_params.getInstitutionCode());
            //銘柄ＩＤ = 更新前の銘柄.銘柄ＩＤ
            assertEquals(111111111111L, l_params.getProductId());
            //銘柄名 = 更新前の銘柄.銘柄名
            assertEquals("あいうえお", l_params.getStandardName());
            //市場ＩＤ = 市場.市場ＩＤ
            assertEquals(3303L, l_params.getMarketId());
            //有効日 = GtlUtils.getSystemTimestamp( )のYYYYMMDD
            assertEquals(WEB3DateUtility.formatDate(
                    GtlUtils.getSystemTimestamp(), "yyyyMMdd"), l_params.getValidUntilBizDate());
            //注意情報区分コード = 注意情報通知キュー.注意情報区分コード (A001)
            assertEquals("A001", l_params.getAttentionInfoDivCode());
            //評価単価（変更前） = null
            assertTrue(l_params.getOldEstimationPriceIsNull());
            //評価単価（変更後） = null
            assertTrue(l_params.getNewEstimationPriceIsNull());
            //基準値（終値）（変更前） = null
            assertTrue(l_params.getOldLastClosingPriceIsNull());
            //基準値（終値）（変更後） = null
            assertTrue(l_params.getNewLastClosingPriceIsNull());
            //基準値（変更前） = null
            assertTrue(l_params.getOldBasePriceIsNull());
            //基準値（変更後） = null
            assertTrue(l_params.getNewBasePriceIsNull());
            //値幅チェック区分（変更前） = null
            assertNull(l_params.getOldPriceRangeType());
            //値幅チェック区分(変更後) = null
            assertNull(l_params.getNewPriceRangeType());
            //値幅区分（変更前） = null
            assertNull(l_params.getOldPriceRangeUnitType());
            //値幅区分（変更後） = null
            assertNull(l_params.getNewPriceRangeUnitType());
            //強制値幅（上限値）（変更前） = null
            assertTrue(l_params.getOldHighCompPriceRangeIsNull());
            //制限値幅上限（変更後） = null
            assertTrue(l_params.getNewHighPriceRangeIsNull());
            //強制値幅（下限値）（変更前） = null
            assertTrue(l_params.getOldLowCompPriceRangeIsNull());
            //制限値幅下限（変更後） = null
            assertTrue(l_params.getNewLowPriceRangeIsNull());
            //基準値（終値）（翌日）（変更前） = null
            assertTrue(l_params.getOldLastClosingPriceUpdqIsNull());
            //基準値（終値）（翌日）（変更後） = null
            assertTrue(l_params.getNewLastClosingPriceUpdqIsNull());
            //基準値（翌日）（変更前） = null
            assertTrue(l_params.getOldBasePriceUpdqIsNull());
            //基準値（翌日）（変更後） = null
            assertTrue(l_params.getNewBasePriceUpdqIsNull());
            //表題 = 注意情報通知キュー.表題
            assertEquals("tttt", l_params.getFreeFormatTitle());
            //本文 = 注意情報通知キュー.本文
            assertEquals("yyyy", l_params.getFreeFormatText());
            //情報発生日時 = 注意情報通知キュー.情報発生日時
            assertEquals(WEB3DateUtility.getDate("20090109", "yyyyMMdd"), l_params.getInfoGenerationTimestamp());
            //注文受付再開日時 = 注意情報通知キュー.注文受付再開日時
            assertEquals(WEB3DateUtility.getDate("20090109", "yyyyMMdd"), l_params.getOrdReceiptRestartTimestamp());
            //売買停止日時／売買再開日時 = 注意情報通知キュー.売買停止日時／売買再開日時
            assertEquals(WEB3DateUtility.getDate("20090109", "yyyyMMdd"), l_params.getTradeStopRestartTimestamp());
            //処理結果区分 = 1：正常
            assertEquals("1", l_params.getProcessResultDiv());
            //作成日付 = 現在日時
            //更新日付 = 現在日時
        }
        catch (Exception l_ex)
        {
            log.debug(""+ l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    /*
     * 注意情報履歴テーブル.注意情報種別＝"売停情報"の場合
     * 株式銘柄＝nullの場合
     * 市場=nullの場合
     * 注意情報履歴テーブル.売買停止日時=nullの場合
     * 注意情報履歴テーブル.注文受付再開日時=nullの場合
     */
    public void testInsertSendMail_0001()
    {
        final String STR_METHOD_NAME = "testInsertSendMail_0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            /*TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setStandardName("pname");
            TestDBUtility.insertWithDel(l_productParams);
            
            EqtypeProductParams l_eqtypeProductParams = new EqtypeProductParams();
            l_eqtypeProductParams.setProductId(3304148080000L);
            l_eqtypeProductParams.setInstitutionCode("0D");
            l_eqtypeProductParams.setProductCode("1002");
            l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeProductParams.setYearlyBooksClosingDate(WEB3DateUtility.getDate("20090108", "yyyyMMdd"));
            l_eqtypeProductParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_eqtypeProductParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_eqtypeProductParams.setStandardName("pname");
            WEB3EquityProduct l_equityProduct = new WEB3EquityProduct(l_eqtypeProductParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_marketParams);
            Market l_market = new WEB3GentradeMarket(3303L);*/
            
            AttentionInfoHistoryParams l_attentionInfoHistoryParams = new AttentionInfoHistoryParams();
            l_attentionInfoHistoryParams.setAttentionInfoHistoryId(1001);
            l_attentionInfoHistoryParams.setAttentionInfoType("1");//
            l_attentionInfoHistoryParams.setInstitutionCode("0D");
            l_attentionInfoHistoryParams.setMarketId(1002);
            l_attentionInfoHistoryParams.setValidUntilBizDate("30");
            l_attentionInfoHistoryParams.setAttentionInfoDivCode("A011");//
            l_attentionInfoHistoryParams.setStandardName("pname");          
            l_attentionInfoHistoryParams.setInfoGenerationTimestamp(WEB3DateUtility.getDate("20090506", "yyyyMMdd"));
            //l_attentionInfoHistoryParams.setTradeStopRestartTimestamp(WEB3DateUtility.getDate("20090208", "yyyyMMdd"));
            //l_attentionInfoHistoryParams.setOrdReceiptRestartTimestamp(WEB3DateUtility.getDate("20090308", "yyyyMMdd"));
            
            l_attentionInfoHistoryParams.setOldEstimationPrice(11);
            l_attentionInfoHistoryParams.setNewEstimationPrice(12);
            l_attentionInfoHistoryParams.setOldLastClosingPrice(13);
            l_attentionInfoHistoryParams.setNewLastClosingPrice(14);
            l_attentionInfoHistoryParams.setOldBasePrice(15);
            l_attentionInfoHistoryParams.setNewBasePrice(16);
            
            l_attentionInfoHistoryParams.setOldPriceRangeType("a");
            l_attentionInfoHistoryParams.setNewPriceRangeType("b");
            l_attentionInfoHistoryParams.setOldPriceRangeUnitType("c");
            l_attentionInfoHistoryParams.setNewPriceRangeUnitType("d");
            
            l_attentionInfoHistoryParams.setOldHighCompPriceRange(17);
            l_attentionInfoHistoryParams.setNewHighPriceRange(18);
            l_attentionInfoHistoryParams.setOldLowCompPriceRange(9);
            l_attentionInfoHistoryParams.setNewLowPriceRange(19);
            
            l_attentionInfoHistoryParams.setOldLastClosingPriceUpdq(20);
            l_attentionInfoHistoryParams.setNewLastClosingPriceUpdq(21);
            
            l_attentionInfoHistoryParams.setOldBasePriceUpdq(22);
            l_attentionInfoHistoryParams.setNewBasePriceUpdq(23);
            
            l_attentionInfoHistoryParams.setFreeFormatTitle("fftitle");
            l_attentionInfoHistoryParams.setFreeFormatText("fftext");
            
            l_attentionInfoHistoryParams.setProcessResultDiv("2");
            
            l_attentionInfoHistoryParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_attentionInfoHistoryParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

            TestDBUtility.deleteAll(MailInfoRow.TYPE);
            MailInfoParams l_mailInfoParams = new MailInfoParams();
            l_mailInfoParams.setInstitutionCode("0D");
            l_mailInfoParams.setSendmailDiv("3101");
            l_mailInfoParams.setDiscernmentId("9");
            l_mailInfoParams.setSubject("abc");
            l_mailInfoParams.setLastUpdater("007");
            l_mailInfoParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_mailInfoParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_mailInfoParams);

            TestDBUtility.deleteAll(MailProcRow.TYPE);
            TestDBUtility.deleteAll(ExtMailProcRow.TYPE);

            WEB3AdminEquityAttentionInfoNotifyTransactionCallback l_callback =
                l_impl.new WEB3AdminEquityAttentionInfoNotifyTransactionCallback();
            
            Method l_method = WEB3AdminEquityAttentionInfoNotifyTransactionCallback.class.getDeclaredMethod(
                    "insertSendMail",
                    new Class[]{WEB3EquityProduct.class, Market.class, AttentionInfoHistoryParams.class});
            l_method.setAccessible(true);
            Object[] l_obj = {null, null, l_attentionInfoHistoryParams};
            l_method.invoke(l_callback, l_obj);

            MailProcRow l_mailProcRow =
                MailProcDao.findRowByInstitutionCodeBranchCodeSendmailDivDiscernmentIdAccountCodeMailId(
                        "0D",
                        "000",
                        "3101",
                        "9",
                        "0000000",
                        1001);
            
            StringBuffer l_sbQueryString = new StringBuffer();
            l_sbQueryString.append("institution_code = ? and ");
            l_sbQueryString.append("branch_code = ? and ");
            l_sbQueryString.append("sendmail_div = ? and ");
            l_sbQueryString.append("discernment_id = ? and ");
            l_sbQueryString.append("account_code = ? ");
            
            Object[] l_values = {"0D", "000", "3101", "9", "0000000"};
            
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            List l_lisResults = l_processor.doFindAllQuery(
                ExtMailProcRow.TYPE,
                l_sbQueryString.toString(),
                l_values);
            
            int l_intCnt = l_lisResults.size();
            ExtMailProcRow[] l_extMailProcRows =
                new ExtMailProcRow[l_intCnt];
            l_lisResults.toArray(l_extMailProcRows);

            assertEquals("0", l_mailProcRow.getStatus());
            assertEquals(null, l_mailProcRow.getEmailAddress());
            assertEquals(null, l_mailProcRow.getSendEmailAddress());
            assertEquals("abc", l_mailProcRow.getSubject());
            assertEquals("pname", l_mailProcRow.getName1());
            assertEquals(" ", l_mailProcRow.getName2()); 
            assertEquals("fftext", l_mailProcRow.getMailText());
            assertEquals(BooleanEnum.FALSE, l_mailProcRow.getDeleteFlag());
            
            assertEquals(1, l_extMailProcRows.length);
            assertEquals("2009/05/06 00:00:00", l_extMailProcRows[0].getItemContents());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    /*
     * 注意情報履歴テーブル.注意情報種別＝"売停情報"の場合
     * 株式銘柄≠nullの場合
     * 市場≠nullの場合
     * 注意情報履歴テーブル.売買停止日時≠nullの場合
     * 注意情報履歴テーブル.注文受付再開日時≠nullの場合
     */
    public void testInsertSendMail_0002()
    {
        final String STR_METHOD_NAME = "testInsertSendMail_0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setStandardName("pname");
            TestDBUtility.insertWithDel(l_productParams);
            
            EqtypeProductParams l_eqtypeProductParams = new EqtypeProductParams();
            l_eqtypeProductParams.setProductId(3304148080000L);
            l_eqtypeProductParams.setInstitutionCode("0D");
            l_eqtypeProductParams.setProductCode("1002");
            l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeProductParams.setYearlyBooksClosingDate(WEB3DateUtility.getDate("20090108", "yyyyMMdd"));
            l_eqtypeProductParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_eqtypeProductParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_eqtypeProductParams.setStandardName("pname");
            WEB3EquityProduct l_equityProduct = new WEB3EquityProduct(l_eqtypeProductParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_marketParams);
            Market l_market = new WEB3GentradeMarket(3303L);
            
            AttentionInfoHistoryParams l_attentionInfoHistoryParams = new AttentionInfoHistoryParams();
            l_attentionInfoHistoryParams.setAttentionInfoHistoryId(1001);
            l_attentionInfoHistoryParams.setAttentionInfoType("1");
            l_attentionInfoHistoryParams.setInstitutionCode("0D");
            l_attentionInfoHistoryParams.setMarketId(1002);
            l_attentionInfoHistoryParams.setValidUntilBizDate("30");
            l_attentionInfoHistoryParams.setAttentionInfoDivCode("A011");
            //l_attentionInfoHistoryParams.setStandardName("pname");          
            l_attentionInfoHistoryParams.setInfoGenerationTimestamp(WEB3DateUtility.getDate("20090506", "yyyyMMdd"));
            l_attentionInfoHistoryParams.setTradeStopRestartTimestamp(WEB3DateUtility.getDate("20090516", "yyyyMMdd"));
            l_attentionInfoHistoryParams.setOrdReceiptRestartTimestamp(WEB3DateUtility.getDate("20090526", "yyyyMMdd"));
            
            l_attentionInfoHistoryParams.setOldEstimationPrice(11);
            l_attentionInfoHistoryParams.setNewEstimationPrice(12);
            l_attentionInfoHistoryParams.setOldLastClosingPrice(13);
            l_attentionInfoHistoryParams.setNewLastClosingPrice(14);
            l_attentionInfoHistoryParams.setOldBasePrice(15);
            l_attentionInfoHistoryParams.setNewBasePrice(16);
            
            l_attentionInfoHistoryParams.setOldPriceRangeType("a");
            l_attentionInfoHistoryParams.setNewPriceRangeType("b");
            l_attentionInfoHistoryParams.setOldPriceRangeUnitType("c");
            l_attentionInfoHistoryParams.setNewPriceRangeUnitType("d");
            
            l_attentionInfoHistoryParams.setOldHighCompPriceRange(17);
            l_attentionInfoHistoryParams.setNewHighPriceRange(18);
            l_attentionInfoHistoryParams.setOldLowCompPriceRange(9);
            l_attentionInfoHistoryParams.setNewLowPriceRange(19);
            
            l_attentionInfoHistoryParams.setOldLastClosingPriceUpdq(20);
            l_attentionInfoHistoryParams.setNewLastClosingPriceUpdq(21);
            
            l_attentionInfoHistoryParams.setOldBasePriceUpdq(22);
            l_attentionInfoHistoryParams.setNewBasePriceUpdq(23);
            
            l_attentionInfoHistoryParams.setFreeFormatTitle("fftitle");
            l_attentionInfoHistoryParams.setFreeFormatText("fftext");
            
            l_attentionInfoHistoryParams.setProcessResultDiv("2");
            
            l_attentionInfoHistoryParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_attentionInfoHistoryParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

            TestDBUtility.deleteAll(MailInfoRow.TYPE);
            MailInfoParams l_mailInfoParams = new MailInfoParams();
            l_mailInfoParams.setInstitutionCode("0D");
            l_mailInfoParams.setSendmailDiv("3101");
            l_mailInfoParams.setDiscernmentId("9");
            l_mailInfoParams.setSubject("abc");
            l_mailInfoParams.setLastUpdater("007");
            l_mailInfoParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_mailInfoParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_mailInfoParams);

            TestDBUtility.deleteAll(MailProcRow.TYPE);
            TestDBUtility.deleteAll(ExtMailProcRow.TYPE);

            WEB3AdminEquityAttentionInfoNotifyTransactionCallback l_callback =
                l_impl.new WEB3AdminEquityAttentionInfoNotifyTransactionCallback();
            
            Method l_method = WEB3AdminEquityAttentionInfoNotifyTransactionCallback.class.getDeclaredMethod(
                    "insertSendMail",
                    new Class[]{WEB3EquityProduct.class, Market.class, AttentionInfoHistoryParams.class});
            l_method.setAccessible(true);
            Object[] l_obj = {l_equityProduct, l_market, l_attentionInfoHistoryParams};
            l_method.invoke(l_callback, l_obj);

            MailProcRow l_mailProcRow =
                MailProcDao.findRowByInstitutionCodeBranchCodeSendmailDivDiscernmentIdAccountCodeMailId(
                        "0D",
                        "000",
                        "3101",
                        "9",
                        "0000000",
                        1001);

            StringBuffer l_sbQueryString = new StringBuffer();
            l_sbQueryString.append("institution_code = ? and ");
            l_sbQueryString.append("branch_code = ? and ");
            l_sbQueryString.append("sendmail_div = ? and ");
            l_sbQueryString.append("discernment_id = ? and ");
            l_sbQueryString.append("account_code = ?");
            
            Object[] l_values = {"0D", "000", "3101", "9", "0000000"};
            
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            List l_lisResults = l_processor.doFindAllQuery(
                ExtMailProcRow.TYPE,
                l_sbQueryString.toString(),
                l_values);
            
            int l_intCnt = l_lisResults.size();
            ExtMailProcRow[] l_extMailProcRows =
                new ExtMailProcRow[l_intCnt];
            l_lisResults.toArray(l_extMailProcRows);
            
            assertEquals("0", l_mailProcRow.getStatus());
            assertEquals(null, l_mailProcRow.getEmailAddress());
            assertEquals(null, l_mailProcRow.getSendEmailAddress());
            assertEquals("abc", l_mailProcRow.getSubject());
            assertEquals(" ", l_mailProcRow.getName1());
            assertEquals("シンガポール", l_mailProcRow.getName2()); 
            assertEquals("fftext", l_mailProcRow.getMailText());
            assertEquals(BooleanEnum.FALSE, l_mailProcRow.getDeleteFlag());
            
            assertEquals(5, l_extMailProcRows.length);
            assertEquals("2009/05/06 00:00:00", l_extMailProcRows[0].getItemContents());
            assertEquals("SP", l_extMailProcRows[1].getItemContents());
            assertEquals("2009/05/26 00:00:00", l_extMailProcRows[2].getItemContents());
            assertEquals("1002", l_extMailProcRows[3].getItemContents());
            assertEquals("2009/05/16 00:00:00", l_extMailProcRows[4].getItemContents());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    /*
     * 注意情報履歴テーブル.注意情報種別＝"制限値幅情報"の場合
     * 株式銘柄＝nullの場合
     * 市場=nullの場合
     * 注意情報履歴テーブル.売買停止日時＝nullの場合
     * 注意情報履歴テーブル.注文受付再開日時＝nullの場合
     * 注意情報履歴テーブル.表題≠nullの場合
     * 其他項 = nullの場合
     */
    public void testInsertSendMail_0003()
    {
        final String STR_METHOD_NAME = "testInsertSendMail_0003()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            /*TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setStandardName("pname");
            TestDBUtility.insertWithDel(l_productParams);
            
            EqtypeProductParams l_eqtypeProductParams = new EqtypeProductParams();
            l_eqtypeProductParams.setProductId(3304148080000L);
            l_eqtypeProductParams.setInstitutionCode("0D");
            l_eqtypeProductParams.setProductCode("1002");
            l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeProductParams.setYearlyBooksClosingDate(WEB3DateUtility.getDate("20090108", "yyyyMMdd"));
            l_eqtypeProductParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_eqtypeProductParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_eqtypeProductParams.setStandardName("pname");
            WEB3EquityProduct l_equityProduct = new WEB3EquityProduct(l_eqtypeProductParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_marketParams);
            Market l_market = new WEB3GentradeMarket(3303L);*/
            
            AttentionInfoHistoryParams l_attentionInfoHistoryParams = new AttentionInfoHistoryParams();
            l_attentionInfoHistoryParams.setAttentionInfoHistoryId(1001);
            l_attentionInfoHistoryParams.setAttentionInfoType("2");
            l_attentionInfoHistoryParams.setInstitutionCode("0D");
            l_attentionInfoHistoryParams.setMarketId(1002);
            l_attentionInfoHistoryParams.setValidUntilBizDate("30");
            l_attentionInfoHistoryParams.setAttentionInfoDivCode("A011");
            l_attentionInfoHistoryParams.setStandardName("pname");          
            l_attentionInfoHistoryParams.setInfoGenerationTimestamp(WEB3DateUtility.getDate("20090506", "yyyyMMdd"));
            //l_attentionInfoHistoryParams.setTradeStopRestartTimestamp(WEB3DateUtility.getDate("20090208", "yyyyMMdd"));
            //l_attentionInfoHistoryParams.setOrdReceiptRestartTimestamp(WEB3DateUtility.getDate("20090308", "yyyyMMdd"));
            
            /*l_attentionInfoHistoryParams.setOldEstimationPrice(11);
            l_attentionInfoHistoryParams.setNewEstimationPrice(12);
            l_attentionInfoHistoryParams.setOldLastClosingPrice(13);
            l_attentionInfoHistoryParams.setNewLastClosingPrice(14);
            l_attentionInfoHistoryParams.setOldBasePrice(15);
            l_attentionInfoHistoryParams.setNewBasePrice(16);
            
            l_attentionInfoHistoryParams.setOldPriceRangeType("a");
            l_attentionInfoHistoryParams.setNewPriceRangeType("b");
            l_attentionInfoHistoryParams.setOldPriceRangeUnitType("c");
            l_attentionInfoHistoryParams.setNewPriceRangeUnitType("d");
            
            l_attentionInfoHistoryParams.setOldHighCompPriceRange(17);
            l_attentionInfoHistoryParams.setNewHighPriceRange(18);
            l_attentionInfoHistoryParams.setOldLowCompPriceRange(9);
            l_attentionInfoHistoryParams.setNewLowPriceRange(19);
            
            l_attentionInfoHistoryParams.setOldLastClosingPriceUpdq(20);
            l_attentionInfoHistoryParams.setNewLastClosingPriceUpdq(21);
            
            l_attentionInfoHistoryParams.setOldBasePriceUpdq(22);
            l_attentionInfoHistoryParams.setNewBasePriceUpdq(23);
            
            l_attentionInfoHistoryParams.setFreeFormatTitle("fftitle");
            l_attentionInfoHistoryParams.setFreeFormatText("fftext");*/
            
            l_attentionInfoHistoryParams.setProcessResultDiv("2");
            
            l_attentionInfoHistoryParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_attentionInfoHistoryParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

            TestDBUtility.deleteAll(MailInfoRow.TYPE);
            MailInfoParams l_mailInfoParams = new MailInfoParams();
            l_mailInfoParams.setInstitutionCode("0D");
            l_mailInfoParams.setSendmailDiv("3102");
            l_mailInfoParams.setDiscernmentId("9");
            l_mailInfoParams.setSubject("abc");
            l_mailInfoParams.setLastUpdater("007");
            l_mailInfoParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_mailInfoParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_mailInfoParams);

            TestDBUtility.deleteAll(MailProcRow.TYPE);
            TestDBUtility.deleteAll(ExtMailProcRow.TYPE);

            WEB3AdminEquityAttentionInfoNotifyTransactionCallback l_callback =
                l_impl.new WEB3AdminEquityAttentionInfoNotifyTransactionCallback();
            
            Method l_method = WEB3AdminEquityAttentionInfoNotifyTransactionCallback.class.getDeclaredMethod(
                    "insertSendMail",
                    new Class[]{WEB3EquityProduct.class, Market.class, AttentionInfoHistoryParams.class});
            l_method.setAccessible(true);
            Object[] l_obj = {null, null, l_attentionInfoHistoryParams};
            l_method.invoke(l_callback, l_obj);

            MailProcRow l_mailProcRow =
                MailProcDao.findRowByInstitutionCodeBranchCodeSendmailDivDiscernmentIdAccountCodeMailId(
                        "0D",
                        "000",
                        "3102",
                        "9",
                        "0000000",
                        1001);
            
            StringBuffer l_sbQueryString = new StringBuffer();
            l_sbQueryString.append("institution_code = ? and ");
            l_sbQueryString.append("branch_code = ? and ");
            l_sbQueryString.append("sendmail_div = ? and ");
            l_sbQueryString.append("discernment_id = ? and ");
            l_sbQueryString.append("account_code = ?");
            
            Object[] l_values = {"0D", "000", "3102", "9", "0000000"};
            
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            List l_lisResults = l_processor.doFindAllQuery(
                ExtMailProcRow.TYPE,
                l_sbQueryString.toString(),
                l_values);
            
            int l_intCnt = l_lisResults.size();
            ExtMailProcRow[] l_extMailProcRows =
                new ExtMailProcRow[l_intCnt];
            l_lisResults.toArray(l_extMailProcRows);

            assertEquals("0", l_mailProcRow.getStatus());
            assertEquals(null, l_mailProcRow.getEmailAddress());
            assertEquals(null, l_mailProcRow.getSendEmailAddress());
            assertEquals("abc", l_mailProcRow.getSubject());
            assertEquals("pname", l_mailProcRow.getName1());
            assertEquals(" ", l_mailProcRow.getName2()); 
            assertEquals(" ", l_mailProcRow.getMailText());
            assertEquals(BooleanEnum.FALSE, l_mailProcRow.getDeleteFlag());
            
            assertEquals(1, l_extMailProcRows.length);
            assertEquals("2009/05/06 00:00:00", l_extMailProcRows[0].getItemContents());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    /*
     * 注意情報履歴テーブル.注意情報種別＝"制限値幅情報"の場合
     * 株式銘柄≠nullの場合
     * 市場≠nullの場合
     * 注意情報履歴テーブル.売買停止日時≠nullの場合
     * 注意情報履歴テーブル.注文受付再開日時≠nullの場合
     * 注意情報履歴テーブル.表題≠nullの場合其他項 ≠ nullの場合
     */
    public void testInsertSendMail_0004()
    {
        final String STR_METHOD_NAME = "testInsertSendMail_0004()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setStandardName("pname");
            TestDBUtility.insertWithDel(l_productParams);
            
            EqtypeProductParams l_eqtypeProductParams = new EqtypeProductParams();
            l_eqtypeProductParams.setProductId(3304148080000L);
            l_eqtypeProductParams.setInstitutionCode("0D");
            l_eqtypeProductParams.setProductCode("1002");
            l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeProductParams.setYearlyBooksClosingDate(WEB3DateUtility.getDate("20090108", "yyyyMMdd"));
            l_eqtypeProductParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_eqtypeProductParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_eqtypeProductParams.setStandardName("pname");
            WEB3EquityProduct l_equityProduct = new WEB3EquityProduct(l_eqtypeProductParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_marketParams);
            Market l_market = new WEB3GentradeMarket(3303L);
            
            AttentionInfoHistoryParams l_attentionInfoHistoryParams = new AttentionInfoHistoryParams();
            l_attentionInfoHistoryParams.setAttentionInfoHistoryId(1001);
            l_attentionInfoHistoryParams.setAttentionInfoType("2");
            l_attentionInfoHistoryParams.setInstitutionCode("0D");
            l_attentionInfoHistoryParams.setMarketId(1002);
            l_attentionInfoHistoryParams.setValidUntilBizDate("30");
            l_attentionInfoHistoryParams.setAttentionInfoDivCode("A011");
            l_attentionInfoHistoryParams.setStandardName("pname");          
            l_attentionInfoHistoryParams.setInfoGenerationTimestamp(WEB3DateUtility.getDate("20090506", "yyyyMMdd"));
            l_attentionInfoHistoryParams.setTradeStopRestartTimestamp(WEB3DateUtility.getDate("20090516", "yyyyMMdd"));
            l_attentionInfoHistoryParams.setOrdReceiptRestartTimestamp(WEB3DateUtility.getDate("20090526", "yyyyMMdd"));
            
            l_attentionInfoHistoryParams.setOldEstimationPrice(11);
            l_attentionInfoHistoryParams.setNewEstimationPrice(12);
            l_attentionInfoHistoryParams.setOldLastClosingPrice(13);
            l_attentionInfoHistoryParams.setNewLastClosingPrice(14);
            l_attentionInfoHistoryParams.setOldBasePrice(15);
            l_attentionInfoHistoryParams.setNewBasePrice(16);
            
            l_attentionInfoHistoryParams.setOldPriceRangeType("a");
            l_attentionInfoHistoryParams.setNewPriceRangeType("b");
            l_attentionInfoHistoryParams.setOldPriceRangeUnitType("c");
            l_attentionInfoHistoryParams.setNewPriceRangeUnitType("d");
            
            l_attentionInfoHistoryParams.setOldHighCompPriceRange(17);
            l_attentionInfoHistoryParams.setNewHighPriceRange(18);
            l_attentionInfoHistoryParams.setOldLowCompPriceRange(9);
            l_attentionInfoHistoryParams.setNewLowPriceRange(19);
            
            l_attentionInfoHistoryParams.setOldLastClosingPriceUpdq(20);
            l_attentionInfoHistoryParams.setNewLastClosingPriceUpdq(21);
            
            l_attentionInfoHistoryParams.setOldBasePriceUpdq(22);
            l_attentionInfoHistoryParams.setNewBasePriceUpdq(23);
            
            l_attentionInfoHistoryParams.setFreeFormatTitle("fftitle");
            l_attentionInfoHistoryParams.setFreeFormatText("fftext");
            
            l_attentionInfoHistoryParams.setProcessResultDiv("2");
            
            l_attentionInfoHistoryParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_attentionInfoHistoryParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

            TestDBUtility.deleteAll(MailInfoRow.TYPE);
            MailInfoParams l_mailInfoParams = new MailInfoParams();
            l_mailInfoParams.setInstitutionCode("0D");
            l_mailInfoParams.setSendmailDiv("3102");
            l_mailInfoParams.setDiscernmentId("9");
            l_mailInfoParams.setSubject("abc");
            l_mailInfoParams.setLastUpdater("007");
            l_mailInfoParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_mailInfoParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_mailInfoParams);

            TestDBUtility.deleteAll(MailProcRow.TYPE);
            TestDBUtility.deleteAll(ExtMailProcRow.TYPE);

            WEB3AdminEquityAttentionInfoNotifyTransactionCallback l_callback =
                l_impl.new WEB3AdminEquityAttentionInfoNotifyTransactionCallback();
            
            Method l_method = WEB3AdminEquityAttentionInfoNotifyTransactionCallback.class.getDeclaredMethod(
                    "insertSendMail",
                    new Class[]{WEB3EquityProduct.class, Market.class, AttentionInfoHistoryParams.class});
            l_method.setAccessible(true);
            Object[] l_obj = {l_equityProduct, l_market, l_attentionInfoHistoryParams};
            l_method.invoke(l_callback, l_obj);

            MailProcRow l_mailProcRow =
                MailProcDao.findRowByInstitutionCodeBranchCodeSendmailDivDiscernmentIdAccountCodeMailId(
                        "0D",
                        "000",
                        "3102",
                        "9",
                        "0000000",
                        1001);
            
            StringBuffer l_sbQueryString = new StringBuffer();
            l_sbQueryString.append("institution_code = ? and ");
            l_sbQueryString.append("branch_code = ? and ");
            l_sbQueryString.append("sendmail_div = ? and ");
            l_sbQueryString.append("discernment_id = ? and ");
            l_sbQueryString.append("account_code = ?");
            
            Object[] l_values = {"0D", "000", "3102", "9", "0000000"};
            
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            List l_lisResults = l_processor.doFindAllQuery(
                ExtMailProcRow.TYPE,
                l_sbQueryString.toString(),
                l_values);
            
            int l_intCnt = l_lisResults.size();
            ExtMailProcRow[] l_extMailProcRows =
                new ExtMailProcRow[l_intCnt];
            l_lisResults.toArray(l_extMailProcRows);

            assertEquals("0", l_mailProcRow.getStatus());
            assertEquals(null, l_mailProcRow.getEmailAddress());
            assertEquals(null, l_mailProcRow.getSendEmailAddress());
            assertEquals("abc", l_mailProcRow.getSubject());
            assertEquals("pname", l_mailProcRow.getName1());
            assertEquals("シンガポール", l_mailProcRow.getName2()); 
            assertEquals("fftext", l_mailProcRow.getMailText());
            assertEquals(BooleanEnum.FALSE, l_mailProcRow.getDeleteFlag());
            
            assertEquals(21, l_extMailProcRows.length);
            assertEquals("2009/05/06 00:00:00", l_extMailProcRows[0].getItemContents());
            assertEquals("SP", l_extMailProcRows[1].getItemContents());
            assertEquals("16.0", l_extMailProcRows[2].getItemContents());
            assertEquals("23.0", l_extMailProcRows[3].getItemContents());
            assertEquals("12.0", l_extMailProcRows[4].getItemContents());
            assertEquals("18.0", l_extMailProcRows[5].getItemContents());
            assertEquals("14.0", l_extMailProcRows[6].getItemContents());
            assertEquals("21.0", l_extMailProcRows[7].getItemContents());
            assertEquals("19.0", l_extMailProcRows[8].getItemContents());
            assertEquals("b", l_extMailProcRows[9].getItemContents());
            assertEquals("d", l_extMailProcRows[10].getItemContents());
            assertEquals("15.0", l_extMailProcRows[11].getItemContents());
            assertEquals("22.0", l_extMailProcRows[12].getItemContents());
            assertEquals("11.0", l_extMailProcRows[13].getItemContents());
            assertEquals("32.0", l_extMailProcRows[14].getItemContents());
            assertEquals("13.0", l_extMailProcRows[15].getItemContents());
            assertEquals("20.0", l_extMailProcRows[16].getItemContents());
            assertEquals("6.0", l_extMailProcRows[17].getItemContents());
            assertEquals("a", l_extMailProcRows[18].getItemContents());
            assertEquals("c", l_extMailProcRows[19].getItemContents());
            assertEquals("1002", l_extMailProcRows[20].getItemContents());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    /*
     * 注意情報履歴テーブル.注意情報種別＝"ﾌﾘｰﾌｫｰﾏｯﾄ情報"の場合
     * 株式銘柄≠nullの場合
     * 市場=nullの場合
     * 注意情報履歴テーブル.売買停止日時≠nullの場合
     * 注意情報履歴テーブル.注文受付再開日時≠nullの場合
     * 注意情報履歴テーブル.表題=nullの場合
     * 其他項 ≠ nullの場合
     */
    public void testInsertSendMail_0005()
    {
        final String STR_METHOD_NAME = "testInsertSendMail_0005()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setStandardName("pname");
            TestDBUtility.insertWithDel(l_productParams);
            
            EqtypeProductParams l_eqtypeProductParams = new EqtypeProductParams();
            l_eqtypeProductParams.setProductId(3304148080000L);
            l_eqtypeProductParams.setInstitutionCode("0D");
            l_eqtypeProductParams.setProductCode("1002");
            l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeProductParams.setYearlyBooksClosingDate(WEB3DateUtility.getDate("20090108", "yyyyMMdd"));
            l_eqtypeProductParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_eqtypeProductParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_eqtypeProductParams.setStandardName("pname");
            WEB3EquityProduct l_equityProduct = new WEB3EquityProduct(l_eqtypeProductParams);
            
            /*MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_marketParams);
            Market l_market = new WEB3GentradeMarket(3303L);*/
            
            AttentionInfoHistoryParams l_attentionInfoHistoryParams = new AttentionInfoHistoryParams();
            l_attentionInfoHistoryParams.setAttentionInfoHistoryId(1001);
            l_attentionInfoHistoryParams.setAttentionInfoType("3");
            l_attentionInfoHistoryParams.setInstitutionCode("0D");
            l_attentionInfoHistoryParams.setMarketId(1002);
            l_attentionInfoHistoryParams.setValidUntilBizDate("30");
            l_attentionInfoHistoryParams.setAttentionInfoDivCode("A011");
            l_attentionInfoHistoryParams.setStandardName("pname");          
            l_attentionInfoHistoryParams.setInfoGenerationTimestamp(WEB3DateUtility.getDate("20090506", "yyyyMMdd"));
            l_attentionInfoHistoryParams.setTradeStopRestartTimestamp(WEB3DateUtility.getDate("20090516", "yyyyMMdd"));
            l_attentionInfoHistoryParams.setOrdReceiptRestartTimestamp(WEB3DateUtility.getDate("20090526", "yyyyMMdd"));
            
            l_attentionInfoHistoryParams.setOldEstimationPrice(11);
            l_attentionInfoHistoryParams.setNewEstimationPrice(12);
            l_attentionInfoHistoryParams.setOldLastClosingPrice(13);
            l_attentionInfoHistoryParams.setNewLastClosingPrice(14);
            l_attentionInfoHistoryParams.setOldBasePrice(15);
            l_attentionInfoHistoryParams.setNewBasePrice(16);
            
            l_attentionInfoHistoryParams.setOldPriceRangeType("a");
            l_attentionInfoHistoryParams.setNewPriceRangeType("b");
            l_attentionInfoHistoryParams.setOldPriceRangeUnitType("c");
            l_attentionInfoHistoryParams.setNewPriceRangeUnitType("d");
            
            l_attentionInfoHistoryParams.setOldHighCompPriceRange(17);
            l_attentionInfoHistoryParams.setNewHighPriceRange(18);
            l_attentionInfoHistoryParams.setOldLowCompPriceRange(9);
            l_attentionInfoHistoryParams.setNewLowPriceRange(19);
            
            l_attentionInfoHistoryParams.setOldLastClosingPriceUpdq(20);
            l_attentionInfoHistoryParams.setNewLastClosingPriceUpdq(21);
            
            l_attentionInfoHistoryParams.setOldBasePriceUpdq(22);
            l_attentionInfoHistoryParams.setNewBasePriceUpdq(23);
            
            //l_attentionInfoHistoryParams.setFreeFormatTitle("fftitle");
            //l_attentionInfoHistoryParams.setFreeFormatText("fftext");
            
            l_attentionInfoHistoryParams.setProcessResultDiv("2");
            
            l_attentionInfoHistoryParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_attentionInfoHistoryParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

            TestDBUtility.deleteAll(MailInfoRow.TYPE);
            MailInfoParams l_mailInfoParams = new MailInfoParams();
            l_mailInfoParams.setInstitutionCode("0D");
            l_mailInfoParams.setSendmailDiv("3103");
            l_mailInfoParams.setDiscernmentId("9");
            l_mailInfoParams.setSubject("abc");
            l_mailInfoParams.setLastUpdater("007");
            l_mailInfoParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_mailInfoParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_mailInfoParams);

            TestDBUtility.deleteAll(MailProcRow.TYPE);
            TestDBUtility.deleteAll(ExtMailProcRow.TYPE);

            WEB3AdminEquityAttentionInfoNotifyTransactionCallback l_callback =
                l_impl.new WEB3AdminEquityAttentionInfoNotifyTransactionCallback();
            
            Method l_method = WEB3AdminEquityAttentionInfoNotifyTransactionCallback.class.getDeclaredMethod(
                    "insertSendMail",
                    new Class[]{WEB3EquityProduct.class, Market.class, AttentionInfoHistoryParams.class});
            l_method.setAccessible(true);
            Object[] l_obj = {l_equityProduct, null, l_attentionInfoHistoryParams};
            l_method.invoke(l_callback, l_obj);

            MailProcRow l_mailProcRow =
                MailProcDao.findRowByInstitutionCodeBranchCodeSendmailDivDiscernmentIdAccountCodeMailId(
                        "0D",
                        "000",
                        "3103",
                        "9",
                        "0000000",
                        1001);
            
            StringBuffer l_sbQueryString = new StringBuffer();
            l_sbQueryString.append("institution_code = ? and ");
            l_sbQueryString.append("branch_code = ? and ");
            l_sbQueryString.append("sendmail_div = ? and ");
            l_sbQueryString.append("discernment_id = ? and ");
            l_sbQueryString.append("account_code = ?");
            
            Object[] l_values = {"0D", "000", "3103", "9", "0000000"};
            
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            List l_lisResults = l_processor.doFindAllQuery(
                ExtMailProcRow.TYPE,
                l_sbQueryString.toString(),
                l_values);
            
            int l_intCnt = l_lisResults.size();
            ExtMailProcRow[] l_extMailProcRows =
                new ExtMailProcRow[l_intCnt];
            l_lisResults.toArray(l_extMailProcRows);

            assertEquals("0", l_mailProcRow.getStatus());
            assertEquals(null, l_mailProcRow.getEmailAddress());
            assertEquals(null, l_mailProcRow.getSendEmailAddress());
            assertEquals("abc", l_mailProcRow.getSubject());
            assertEquals("pname", l_mailProcRow.getName1());
            assertEquals(" ", l_mailProcRow.getName2()); 
            assertEquals(" ", l_mailProcRow.getMailText());
            assertEquals(BooleanEnum.FALSE, l_mailProcRow.getDeleteFlag());
            
            assertEquals(1, l_extMailProcRows.length);
            assertEquals("2009/05/06 00:00:00", l_extMailProcRows[0].getItemContents());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
   
    /*
     * 注意情報履歴テーブル.注意情報種別＝"ﾌﾘｰﾌｫｰﾏｯﾄ情報"の場合
     * 株式銘柄≠nullの場合
     * 市場≠nullの場合
     * 注意情報履歴テーブル.表題≠nullの場合
     * 其他項≠ nullの場合
     */
    public void testInsertSendMail_0006()
    {
        final String STR_METHOD_NAME = "testInsertSendMail_0006()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setStandardName("pname");
            TestDBUtility.insertWithDel(l_productParams);
            
            EqtypeProductParams l_eqtypeProductParams = new EqtypeProductParams();
            l_eqtypeProductParams.setProductId(3304148080000L);
            l_eqtypeProductParams.setInstitutionCode("0D");
            l_eqtypeProductParams.setProductCode("1002");
            l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeProductParams.setYearlyBooksClosingDate(WEB3DateUtility.getDate("20090108", "yyyyMMdd"));
            l_eqtypeProductParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_eqtypeProductParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_eqtypeProductParams.setStandardName("pname");
            WEB3EquityProduct l_equityProduct = new WEB3EquityProduct(l_eqtypeProductParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_marketParams);
            Market l_market = new WEB3GentradeMarket(3303L);
            
            AttentionInfoHistoryParams l_attentionInfoHistoryParams = new AttentionInfoHistoryParams();
            l_attentionInfoHistoryParams.setAttentionInfoHistoryId(1001);
            l_attentionInfoHistoryParams.setAttentionInfoType("3");
            l_attentionInfoHistoryParams.setInstitutionCode("0D");
            l_attentionInfoHistoryParams.setMarketId(1002);
            l_attentionInfoHistoryParams.setValidUntilBizDate("30");
            l_attentionInfoHistoryParams.setAttentionInfoDivCode("A011");
            l_attentionInfoHistoryParams.setStandardName("pname");          
            l_attentionInfoHistoryParams.setInfoGenerationTimestamp(WEB3DateUtility.getDate("20090506", "yyyyMMdd"));
            l_attentionInfoHistoryParams.setTradeStopRestartTimestamp(WEB3DateUtility.getDate("20090516", "yyyyMMdd"));
            l_attentionInfoHistoryParams.setOrdReceiptRestartTimestamp(WEB3DateUtility.getDate("20090526", "yyyyMMdd"));
            
            l_attentionInfoHistoryParams.setOldEstimationPrice(11);
            l_attentionInfoHistoryParams.setNewEstimationPrice(12);
            l_attentionInfoHistoryParams.setOldLastClosingPrice(13);
            l_attentionInfoHistoryParams.setNewLastClosingPrice(14);
            l_attentionInfoHistoryParams.setOldBasePrice(15);
            l_attentionInfoHistoryParams.setNewBasePrice(16);
            
            l_attentionInfoHistoryParams.setOldPriceRangeType("a");
            l_attentionInfoHistoryParams.setNewPriceRangeType("b");
            l_attentionInfoHistoryParams.setOldPriceRangeUnitType("c");
            l_attentionInfoHistoryParams.setNewPriceRangeUnitType("d");
            
            l_attentionInfoHistoryParams.setOldHighCompPriceRange(17);
            l_attentionInfoHistoryParams.setNewHighPriceRange(18);
            l_attentionInfoHistoryParams.setOldLowCompPriceRange(9);
            l_attentionInfoHistoryParams.setNewLowPriceRange(19);
            
            l_attentionInfoHistoryParams.setOldLastClosingPriceUpdq(20);
            l_attentionInfoHistoryParams.setNewLastClosingPriceUpdq(21);
            
            l_attentionInfoHistoryParams.setOldBasePriceUpdq(22);
            l_attentionInfoHistoryParams.setNewBasePriceUpdq(23);
            
            l_attentionInfoHistoryParams.setFreeFormatTitle("fftitle");
            l_attentionInfoHistoryParams.setFreeFormatText("fftext");
            
            l_attentionInfoHistoryParams.setProcessResultDiv("2");
            
            l_attentionInfoHistoryParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_attentionInfoHistoryParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

            TestDBUtility.deleteAll(MailInfoRow.TYPE);
            MailInfoParams l_mailInfoParams = new MailInfoParams();
            l_mailInfoParams.setInstitutionCode("0D");
            l_mailInfoParams.setSendmailDiv("3103");
            l_mailInfoParams.setDiscernmentId("9");
            l_mailInfoParams.setSubject("abc");
            l_mailInfoParams.setLastUpdater("007");
            l_mailInfoParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_mailInfoParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_mailInfoParams);

            TestDBUtility.deleteAll(MailProcRow.TYPE);
            TestDBUtility.deleteAll(ExtMailProcRow.TYPE);

            WEB3AdminEquityAttentionInfoNotifyTransactionCallback l_callback =
                l_impl.new WEB3AdminEquityAttentionInfoNotifyTransactionCallback();
            
            Method l_method = WEB3AdminEquityAttentionInfoNotifyTransactionCallback.class.getDeclaredMethod(
                    "insertSendMail",
                    new Class[]{WEB3EquityProduct.class, Market.class, AttentionInfoHistoryParams.class});
            l_method.setAccessible(true);
            Object[] l_obj = {l_equityProduct, l_market, l_attentionInfoHistoryParams};
            l_method.invoke(l_callback, l_obj);

            MailProcRow l_mailProcRow =
                MailProcDao.findRowByInstitutionCodeBranchCodeSendmailDivDiscernmentIdAccountCodeMailId(
                        "0D",
                        "000",
                        "3103",
                        "9",
                        "0000000",
                        1001);
            
            StringBuffer l_sbQueryString = new StringBuffer();
            l_sbQueryString.append("institution_code = ? and ");
            l_sbQueryString.append("branch_code = ? and ");
            l_sbQueryString.append("sendmail_div = ? and ");
            l_sbQueryString.append("discernment_id = ? and ");
            l_sbQueryString.append("account_code = ?");
            
            Object[] l_values = {"0D", "000", "3103", "9", "0000000"};
            
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            List l_lisResults = l_processor.doFindAllQuery(
                ExtMailProcRow.TYPE,
                l_sbQueryString.toString(),
                l_values);
            
            int l_intCnt = l_lisResults.size();
            ExtMailProcRow[] l_extMailProcRows =
                new ExtMailProcRow[l_intCnt];
            l_lisResults.toArray(l_extMailProcRows);
            
            assertEquals("0", l_mailProcRow.getStatus());
            assertEquals(null, l_mailProcRow.getEmailAddress());
            assertEquals(null, l_mailProcRow.getSendEmailAddress());
            assertEquals("abc", l_mailProcRow.getSubject());
            assertEquals("pname", l_mailProcRow.getName1());
            assertEquals("シンガポール", l_mailProcRow.getName2()); 
            assertEquals("fftext", l_mailProcRow.getMailText());
            assertEquals(BooleanEnum.FALSE, l_mailProcRow.getDeleteFlag());
            
            assertEquals(3, l_extMailProcRows.length);
            assertEquals("2009/05/06 00:00:00", l_extMailProcRows[0].getItemContents());
            assertEquals("SP", l_extMailProcRows[1].getItemContents());
            assertEquals("fftitle", l_extMailProcRows[2].getItemContents());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    /*
     * 注意情報履歴テーブル.注意情報種別＝"エラー"の場合
     * 株式銘柄=nullの場合
     * 市場=nullの場合
     * 其他項 = nullの場合
     */
    public void testInsertSendMail_0007()
    {
        final String STR_METHOD_NAME = "testInsertSendMail_0007()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            /*TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setStandardName("pname");
            TestDBUtility.insertWithDel(l_productParams);
            
            EqtypeProductParams l_eqtypeProductParams = new EqtypeProductParams();
            l_eqtypeProductParams.setProductId(3304148080000L);
            l_eqtypeProductParams.setInstitutionCode("0D");
            l_eqtypeProductParams.setProductCode("1002");
            l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeProductParams.setYearlyBooksClosingDate(WEB3DateUtility.getDate("20090108", "yyyyMMdd"));
            l_eqtypeProductParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_eqtypeProductParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_eqtypeProductParams.setStandardName("pname");
            WEB3EquityProduct l_equityProduct = new WEB3EquityProduct(l_eqtypeProductParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_marketParams);
            Market l_market = new WEB3GentradeMarket(3303L);*/
            
            AttentionInfoHistoryParams l_attentionInfoHistoryParams = new AttentionInfoHistoryParams();
            l_attentionInfoHistoryParams.setAttentionInfoHistoryId(1001);
            l_attentionInfoHistoryParams.setAttentionInfoType("1");
            l_attentionInfoHistoryParams.setInstitutionCode("0D");
            l_attentionInfoHistoryParams.setMarketId(1002);
            l_attentionInfoHistoryParams.setValidUntilBizDate("30");
            l_attentionInfoHistoryParams.setAttentionInfoDivCode("A011");
            l_attentionInfoHistoryParams.setStandardName("pname");          
            l_attentionInfoHistoryParams.setInfoGenerationTimestamp(WEB3DateUtility.getDate("20090506", "yyyyMMdd"));
            //l_attentionInfoHistoryParams.setTradeStopRestartTimestamp(WEB3DateUtility.getDate("20090208", "yyyyMMdd"));
            //l_attentionInfoHistoryParams.setOrdReceiptRestartTimestamp(WEB3DateUtility.getDate("20090308", "yyyyMMdd"));
            
            /*l_attentionInfoHistoryParams.setOldEstimationPrice(11);
            l_attentionInfoHistoryParams.setNewEstimationPrice(12);
            l_attentionInfoHistoryParams.setOldLastClosingPrice(13);
            l_attentionInfoHistoryParams.setNewLastClosingPrice(14);
            l_attentionInfoHistoryParams.setOldBasePrice(15);
            l_attentionInfoHistoryParams.setNewBasePrice(16);
            
            l_attentionInfoHistoryParams.setOldPriceRangeType("a");
            l_attentionInfoHistoryParams.setNewPriceRangeType("b");
            l_attentionInfoHistoryParams.setOldPriceRangeUnitType("c");
            l_attentionInfoHistoryParams.setNewPriceRangeUnitType("d");
            
            l_attentionInfoHistoryParams.setOldHighCompPriceRange(17);
            l_attentionInfoHistoryParams.setNewHighPriceRange(18);
            l_attentionInfoHistoryParams.setOldLowCompPriceRange(9);
            l_attentionInfoHistoryParams.setNewLowPriceRange(19);
            
            l_attentionInfoHistoryParams.setOldLastClosingPriceUpdq(20);
            l_attentionInfoHistoryParams.setNewLastClosingPriceUpdq(21);
            
            l_attentionInfoHistoryParams.setOldBasePriceUpdq(22);
            l_attentionInfoHistoryParams.setNewBasePriceUpdq(23);
            
            l_attentionInfoHistoryParams.setFreeFormatTitle("fftitle");
            l_attentionInfoHistoryParams.setFreeFormatText("fftext");*/
            
            l_attentionInfoHistoryParams.setProcessResultDiv("9");
            
            l_attentionInfoHistoryParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_attentionInfoHistoryParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

            TestDBUtility.deleteAll(MailInfoRow.TYPE);
            MailInfoParams l_mailInfoParams = new MailInfoParams();
            l_mailInfoParams.setInstitutionCode("0D");
            l_mailInfoParams.setSendmailDiv("3104");
            l_mailInfoParams.setDiscernmentId("1");
            l_mailInfoParams.setSubject("abc");
            l_mailInfoParams.setLastUpdater("007");
            l_mailInfoParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_mailInfoParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_mailInfoParams);

            TestDBUtility.deleteAll(MailProcRow.TYPE);
            TestDBUtility.deleteAll(ExtMailProcRow.TYPE);

            WEB3AdminEquityAttentionInfoNotifyTransactionCallback l_callback =
                l_impl.new WEB3AdminEquityAttentionInfoNotifyTransactionCallback();
            
            Method l_method = WEB3AdminEquityAttentionInfoNotifyTransactionCallback.class.getDeclaredMethod(
                    "insertSendMail",
                    new Class[]{WEB3EquityProduct.class, Market.class, AttentionInfoHistoryParams.class});
            l_method.setAccessible(true);
            Object[] l_obj = {null, null, l_attentionInfoHistoryParams};
            l_method.invoke(l_callback, l_obj);

            MailProcRow l_mailProcRow =
                MailProcDao.findRowByInstitutionCodeBranchCodeSendmailDivDiscernmentIdAccountCodeMailId(
                        "0D",
                        "000",
                        "3104",
                        "1",
                        "0000000",
                        1001);
            
            StringBuffer l_sbQueryString = new StringBuffer();
            l_sbQueryString.append("institution_code = ? and ");
            l_sbQueryString.append("branch_code = ? and ");
            l_sbQueryString.append("sendmail_div = ? and ");
            l_sbQueryString.append("discernment_id = ? and ");
            l_sbQueryString.append("account_code = ?");
            
            Object[] l_values = {"0D", "000", "3104", "1", "0000000"};
            
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            List l_lisResults = l_processor.doFindAllQuery(
                ExtMailProcRow.TYPE,
                l_sbQueryString.toString(),
                l_values);
            
            int l_intCnt = l_lisResults.size();
            ExtMailProcRow[] l_extMailProcRows =
                new ExtMailProcRow[l_intCnt];
            l_lisResults.toArray(l_extMailProcRows);

            assertEquals("0", l_mailProcRow.getStatus());
            assertEquals(null, l_mailProcRow.getEmailAddress());
            assertEquals(null, l_mailProcRow.getSendEmailAddress());
            assertEquals("abc", l_mailProcRow.getSubject());
            assertEquals("pname", l_mailProcRow.getName1());
            assertEquals(" ", l_mailProcRow.getName2()); 
            assertEquals(" ", l_mailProcRow.getMailText());
            assertEquals(BooleanEnum.FALSE, l_mailProcRow.getDeleteFlag());
            
            assertEquals(1, l_extMailProcRows.length);
            assertEquals("2009/05/06 00:00:00", l_extMailProcRows[0].getItemContents());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
   
    /*
     * 注意情報履歴テーブル.注意情報種別＝"エラー"の場合
     * 株式銘柄≠nullの場合
     * 市場≠nullの場合
     * 注意情報履歴テーブル.売買停止日時≠nullの場合
     * 注意情報履歴テーブル.注文受付再開日時≠nullの場合
     * 注意情報履歴テーブル.表題≠nullの場合其他項 ≠ nullの場合
     */
    public void testInsertSendMail_0008()
    {
        final String STR_METHOD_NAME = "testInsertSendMail_0008()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setStandardName("pname");
            TestDBUtility.insertWithDel(l_productParams);
            
            EqtypeProductParams l_eqtypeProductParams = new EqtypeProductParams();
            l_eqtypeProductParams.setProductId(3304148080000L);
            l_eqtypeProductParams.setInstitutionCode("0D");
            l_eqtypeProductParams.setProductCode("1002");
            l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeProductParams.setYearlyBooksClosingDate(WEB3DateUtility.getDate("20090108", "yyyyMMdd"));
            l_eqtypeProductParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_eqtypeProductParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_eqtypeProductParams.setStandardName("pname");
            WEB3EquityProduct l_equityProduct = new WEB3EquityProduct(l_eqtypeProductParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_marketParams);
            Market l_market = new WEB3GentradeMarket(3303L);
            
            AttentionInfoHistoryParams l_attentionInfoHistoryParams = new AttentionInfoHistoryParams();
            l_attentionInfoHistoryParams.setAttentionInfoHistoryId(1001);
            l_attentionInfoHistoryParams.setAttentionInfoType("1");
            l_attentionInfoHistoryParams.setInstitutionCode("0D");
            l_attentionInfoHistoryParams.setMarketId(1002);
            l_attentionInfoHistoryParams.setValidUntilBizDate("30");
            l_attentionInfoHistoryParams.setAttentionInfoDivCode("A011");
            l_attentionInfoHistoryParams.setStandardName("pname");          
            l_attentionInfoHistoryParams.setInfoGenerationTimestamp(WEB3DateUtility.getDate("20090506", "yyyyMMdd"));
            l_attentionInfoHistoryParams.setTradeStopRestartTimestamp(WEB3DateUtility.getDate("20090516", "yyyyMMdd"));
            l_attentionInfoHistoryParams.setOrdReceiptRestartTimestamp(WEB3DateUtility.getDate("20090526", "yyyyMMdd"));
            
            l_attentionInfoHistoryParams.setOldEstimationPrice(11);
            l_attentionInfoHistoryParams.setNewEstimationPrice(12);
            l_attentionInfoHistoryParams.setOldLastClosingPrice(13);
            l_attentionInfoHistoryParams.setNewLastClosingPrice(14);
            l_attentionInfoHistoryParams.setOldBasePrice(15);
            l_attentionInfoHistoryParams.setNewBasePrice(16);
            
            l_attentionInfoHistoryParams.setOldPriceRangeType("a");
            l_attentionInfoHistoryParams.setNewPriceRangeType("b");
            l_attentionInfoHistoryParams.setOldPriceRangeUnitType("c");
            l_attentionInfoHistoryParams.setNewPriceRangeUnitType("d");
            
            l_attentionInfoHistoryParams.setOldHighCompPriceRange(17);
            l_attentionInfoHistoryParams.setNewHighPriceRange(18);
            l_attentionInfoHistoryParams.setOldLowCompPriceRange(9);
            l_attentionInfoHistoryParams.setNewLowPriceRange(19);
            
            l_attentionInfoHistoryParams.setOldLastClosingPriceUpdq(20);
            l_attentionInfoHistoryParams.setNewLastClosingPriceUpdq(21);
            
            l_attentionInfoHistoryParams.setOldBasePriceUpdq(22);
            l_attentionInfoHistoryParams.setNewBasePriceUpdq(23);
            
            l_attentionInfoHistoryParams.setFreeFormatTitle("fftitle");
            l_attentionInfoHistoryParams.setFreeFormatText("fftext");
            
            l_attentionInfoHistoryParams.setProcessResultDiv("9");
            
            l_attentionInfoHistoryParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_attentionInfoHistoryParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

            TestDBUtility.deleteAll(MailInfoRow.TYPE);
            MailInfoParams l_mailInfoParams = new MailInfoParams();
            l_mailInfoParams.setInstitutionCode("0D");
            l_mailInfoParams.setSendmailDiv("3104");
            l_mailInfoParams.setDiscernmentId("1");
            l_mailInfoParams.setSubject("abc");
            l_mailInfoParams.setLastUpdater("007");
            l_mailInfoParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_mailInfoParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_mailInfoParams);

            TestDBUtility.deleteAll(MailProcRow.TYPE);
            TestDBUtility.deleteAll(ExtMailProcRow.TYPE);

            WEB3AdminEquityAttentionInfoNotifyTransactionCallback l_callback =
                l_impl.new WEB3AdminEquityAttentionInfoNotifyTransactionCallback();
            
            Method l_method = WEB3AdminEquityAttentionInfoNotifyTransactionCallback.class.getDeclaredMethod(
                    "insertSendMail",
                    new Class[]{WEB3EquityProduct.class, Market.class, AttentionInfoHistoryParams.class});
            l_method.setAccessible(true);
            Object[] l_obj = {l_equityProduct, l_market, l_attentionInfoHistoryParams};
            l_method.invoke(l_callback, l_obj);

            MailProcRow l_mailProcRow =
                MailProcDao.findRowByInstitutionCodeBranchCodeSendmailDivDiscernmentIdAccountCodeMailId(
                        "0D",
                        "000",
                        "3104",
                        "1",
                        "0000000",
                        1001);

            StringBuffer l_sbQueryString = new StringBuffer();
            l_sbQueryString.append("institution_code = ? and ");
            l_sbQueryString.append("branch_code = ? and ");
            l_sbQueryString.append("sendmail_div = ? and ");
            l_sbQueryString.append("discernment_id = ? and ");
            l_sbQueryString.append("account_code = ?");
            
            Object[] l_values = {"0D", "000", "3104", "1", "0000000"};
            
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            List l_lisResults = l_processor.doFindAllQuery(
                ExtMailProcRow.TYPE,
                l_sbQueryString.toString(),
                l_values);
            
            int l_intCnt = l_lisResults.size();
            ExtMailProcRow[] l_extMailProcRows =
                new ExtMailProcRow[l_intCnt];
            l_lisResults.toArray(l_extMailProcRows);
            
            assertEquals("0", l_mailProcRow.getStatus());
            assertEquals(null, l_mailProcRow.getEmailAddress());
            assertEquals(null, l_mailProcRow.getSendEmailAddress());
            assertEquals("abc", l_mailProcRow.getSubject());
            assertEquals("pname", l_mailProcRow.getName1());
            assertEquals("シンガポール", l_mailProcRow.getName2()); 
            assertEquals("fftext", l_mailProcRow.getMailText());
            assertEquals(BooleanEnum.FALSE, l_mailProcRow.getDeleteFlag());
            
            assertEquals(24, l_extMailProcRows.length);
            assertEquals("2009/05/06 00:00:00", l_extMailProcRows[0].getItemContents());
            assertEquals("SP", l_extMailProcRows[1].getItemContents());
            assertEquals("16.0", l_extMailProcRows[2].getItemContents());
            assertEquals("23.0", l_extMailProcRows[3].getItemContents());
            assertEquals("12.0", l_extMailProcRows[4].getItemContents());
            assertEquals("18.0", l_extMailProcRows[5].getItemContents());
            assertEquals("14.0", l_extMailProcRows[6].getItemContents());
            assertEquals("21.0", l_extMailProcRows[7].getItemContents());
            assertEquals("19.0", l_extMailProcRows[8].getItemContents());
            assertEquals("b", l_extMailProcRows[9].getItemContents());
            assertEquals("d", l_extMailProcRows[10].getItemContents());
            assertEquals("15.0", l_extMailProcRows[11].getItemContents());
            assertEquals("22.0", l_extMailProcRows[12].getItemContents());
            assertEquals("11.0", l_extMailProcRows[13].getItemContents());
            assertEquals("32.0", l_extMailProcRows[14].getItemContents());
            assertEquals("13.0", l_extMailProcRows[15].getItemContents());
            assertEquals("20.0", l_extMailProcRows[16].getItemContents());
            assertEquals("6.0", l_extMailProcRows[17].getItemContents());
            assertEquals("a", l_extMailProcRows[18].getItemContents());
            assertEquals("c", l_extMailProcRows[19].getItemContents());
            assertEquals("2009/05/26 00:00:00", l_extMailProcRows[20].getItemContents());
            assertEquals("1002", l_extMailProcRows[21].getItemContents());
            assertEquals("fftitle", l_extMailProcRows[22].getItemContents());
            assertEquals("2009/05/16 00:00:00", l_extMailProcRows[23].getItemContents());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    /*
     * 注意情報履歴テーブル.注意情報種別＝"制限値幅情報"
     * 注意情報履歴テーブル.基準値（変更前） = null
     * 注意情報履歴テーブル.強制値幅（上限値）（変更前）≠null
     * 注意情報履歴テーブル.制限値幅下限（変更前） ≠ null
     */
    public void testInsertSendMail_0009()
    {
        final String STR_METHOD_NAME = "testInsertSendMail_0009()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setStandardName("pname");
            TestDBUtility.insertWithDel(l_productParams);
            
            EqtypeProductParams l_eqtypeProductParams = new EqtypeProductParams();
            l_eqtypeProductParams.setProductId(3304148080000L);
            l_eqtypeProductParams.setInstitutionCode("0D");
            l_eqtypeProductParams.setProductCode("1002");
            l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeProductParams.setYearlyBooksClosingDate(WEB3DateUtility.getDate("20090108", "yyyyMMdd"));
            l_eqtypeProductParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_eqtypeProductParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_eqtypeProductParams.setStandardName("pname");
            WEB3EquityProduct l_equityProduct = new WEB3EquityProduct(l_eqtypeProductParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_marketParams);
            Market l_market = new WEB3GentradeMarket(3303L);
            
            AttentionInfoHistoryParams l_attentionInfoHistoryParams = new AttentionInfoHistoryParams();
            l_attentionInfoHistoryParams.setAttentionInfoHistoryId(1001);
            l_attentionInfoHistoryParams.setAttentionInfoType("2");
            l_attentionInfoHistoryParams.setInstitutionCode("0D");
            l_attentionInfoHistoryParams.setMarketId(1002);
            l_attentionInfoHistoryParams.setValidUntilBizDate("30");
            l_attentionInfoHistoryParams.setAttentionInfoDivCode("A011");
            l_attentionInfoHistoryParams.setStandardName("pname");          
            l_attentionInfoHistoryParams.setInfoGenerationTimestamp(WEB3DateUtility.getDate("20090506", "yyyyMMdd"));
            l_attentionInfoHistoryParams.setTradeStopRestartTimestamp(WEB3DateUtility.getDate("20090516", "yyyyMMdd"));
            l_attentionInfoHistoryParams.setOrdReceiptRestartTimestamp(WEB3DateUtility.getDate("20090526", "yyyyMMdd"));
            
            l_attentionInfoHistoryParams.setOldEstimationPrice(11);
            l_attentionInfoHistoryParams.setNewEstimationPrice(12);
            l_attentionInfoHistoryParams.setOldLastClosingPrice(13);
            l_attentionInfoHistoryParams.setNewLastClosingPrice(14);
            //l_attentionInfoHistoryParams.setOldBasePrice(15);
            l_attentionInfoHistoryParams.setNewBasePrice(16);
            
            l_attentionInfoHistoryParams.setOldPriceRangeType("a");
            l_attentionInfoHistoryParams.setNewPriceRangeType("b");
            l_attentionInfoHistoryParams.setOldPriceRangeUnitType("c");
            l_attentionInfoHistoryParams.setNewPriceRangeUnitType("d");
            
            l_attentionInfoHistoryParams.setOldHighCompPriceRange(17);
            l_attentionInfoHistoryParams.setNewHighPriceRange(18);
            l_attentionInfoHistoryParams.setOldLowCompPriceRange(9);
            l_attentionInfoHistoryParams.setNewLowPriceRange(19);
            
            l_attentionInfoHistoryParams.setOldLastClosingPriceUpdq(20);
            l_attentionInfoHistoryParams.setNewLastClosingPriceUpdq(21);
            
            l_attentionInfoHistoryParams.setOldBasePriceUpdq(22);
            l_attentionInfoHistoryParams.setNewBasePriceUpdq(23);
            
            l_attentionInfoHistoryParams.setFreeFormatTitle("fftitle");
            l_attentionInfoHistoryParams.setFreeFormatText("fftext");
            
            l_attentionInfoHistoryParams.setProcessResultDiv("2");
            
            l_attentionInfoHistoryParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_attentionInfoHistoryParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

            TestDBUtility.deleteAll(MailInfoRow.TYPE);
            MailInfoParams l_mailInfoParams = new MailInfoParams();
            l_mailInfoParams.setInstitutionCode("0D");
            l_mailInfoParams.setSendmailDiv("3102");
            l_mailInfoParams.setDiscernmentId("9");
            l_mailInfoParams.setSubject("abc");
            l_mailInfoParams.setLastUpdater("007");
            l_mailInfoParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_mailInfoParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_mailInfoParams);

            TestDBUtility.deleteAll(MailProcRow.TYPE);
            TestDBUtility.deleteAll(ExtMailProcRow.TYPE);

            WEB3AdminEquityAttentionInfoNotifyTransactionCallback l_callback =
                l_impl.new WEB3AdminEquityAttentionInfoNotifyTransactionCallback();
            
            Method l_method = WEB3AdminEquityAttentionInfoNotifyTransactionCallback.class.getDeclaredMethod(
                    "insertSendMail",
                    new Class[]{WEB3EquityProduct.class, Market.class, AttentionInfoHistoryParams.class});
            l_method.setAccessible(true);
            Object[] l_obj = {l_equityProduct, l_market, l_attentionInfoHistoryParams};
            l_method.invoke(l_callback, l_obj);

            MailProcRow l_mailProcRow =
                MailProcDao.findRowByInstitutionCodeBranchCodeSendmailDivDiscernmentIdAccountCodeMailId(
                        "0D",
                        "000",
                        "3102",
                        "9",
                        "0000000",
                        1001);
            
            StringBuffer l_sbQueryString = new StringBuffer();
            l_sbQueryString.append("institution_code = ? and ");
            l_sbQueryString.append("branch_code = ? and ");
            l_sbQueryString.append("sendmail_div = ? and ");
            l_sbQueryString.append("discernment_id = ? and ");
            l_sbQueryString.append("account_code = ?");
            
            Object[] l_values = {"0D", "000", "3102", "9", "0000000"};
            
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            List l_lisResults = l_processor.doFindAllQuery(
                ExtMailProcRow.TYPE,
                l_sbQueryString.toString(),
                l_values);
            
            int l_intCnt = l_lisResults.size();
            ExtMailProcRow[] l_extMailProcRows =
                new ExtMailProcRow[l_intCnt];
            l_lisResults.toArray(l_extMailProcRows);

            assertEquals("0", l_mailProcRow.getStatus());
            assertEquals(null, l_mailProcRow.getEmailAddress());
            assertEquals(null, l_mailProcRow.getSendEmailAddress());
            assertEquals("abc", l_mailProcRow.getSubject());
            assertEquals("pname", l_mailProcRow.getName1());
            assertEquals("シンガポール", l_mailProcRow.getName2()); 
            assertEquals("fftext", l_mailProcRow.getMailText());
            assertEquals(BooleanEnum.FALSE, l_mailProcRow.getDeleteFlag());
            
            assertEquals(18, l_extMailProcRows.length);
            assertEquals("2009/05/06 00:00:00", l_extMailProcRows[0].getItemContents());
            assertEquals("SP", l_extMailProcRows[1].getItemContents());
            assertEquals("16.0", l_extMailProcRows[2].getItemContents());
            assertEquals("23.0", l_extMailProcRows[3].getItemContents());
            assertEquals("12.0", l_extMailProcRows[4].getItemContents());
            assertEquals("18.0", l_extMailProcRows[5].getItemContents());
            assertEquals("14.0", l_extMailProcRows[6].getItemContents());
            assertEquals("21.0", l_extMailProcRows[7].getItemContents());
            assertEquals("19.0", l_extMailProcRows[8].getItemContents());
            assertEquals("b", l_extMailProcRows[9].getItemContents());
            assertEquals("d", l_extMailProcRows[10].getItemContents());
            //assertEquals("15.0", l_extMailProcRows[11].getItemContents());
            assertEquals("22.0", l_extMailProcRows[11].getItemContents());
            assertEquals("11.0", l_extMailProcRows[12].getItemContents());
            //assertEquals("32.0", l_extMailProcRows[14].getItemContents());
            assertEquals("13.0", l_extMailProcRows[13].getItemContents());
            assertEquals("20.0", l_extMailProcRows[14].getItemContents());
            //assertEquals("6.0", l_extMailProcRows[17].getItemContents());
            assertEquals("a", l_extMailProcRows[15].getItemContents());
            assertEquals("c", l_extMailProcRows[16].getItemContents());
            assertEquals("1002", l_extMailProcRows[17].getItemContents());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
   
      /*
       * 注意情報履歴テーブル.注意情報種別＝"制限値幅情報"
       * 注意情報履歴テーブル.基準値（変更前）≠ null
       * 注意情報履歴テーブル.強制値幅（上限値）（変更前）=null
       * 注意情報履歴テーブル.制限値幅下限（変更前） = null
       */
      public void testInsertSendMail_0010()
      {
          final String STR_METHOD_NAME = "testInsertSendMail_0010()";
          log.entering(STR_METHOD_NAME);
          
          try
          {
              TestDBUtility.deleteAll(ProductRow.TYPE);
              ProductParams l_productParams = TestDBUtility.getProductRow();
              l_productParams.setProductId(3304148080000L);
              l_productParams.setStandardName("pname");
              TestDBUtility.insertWithDel(l_productParams);
              
              EqtypeProductParams l_eqtypeProductParams = new EqtypeProductParams();
              l_eqtypeProductParams.setProductId(3304148080000L);
              l_eqtypeProductParams.setInstitutionCode("0D");
              l_eqtypeProductParams.setProductCode("1002");
              l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
              l_eqtypeProductParams.setYearlyBooksClosingDate(WEB3DateUtility.getDate("20090108", "yyyyMMdd"));
              l_eqtypeProductParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
              l_eqtypeProductParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
              l_eqtypeProductParams.setStandardName("pname");
              WEB3EquityProduct l_equityProduct = new WEB3EquityProduct(l_eqtypeProductParams);
              
              MarketParams l_marketParams = TestDBUtility.getMarketRow();
              l_marketParams.setMarketId(3303L);
              TestDBUtility.insertWithDel(l_marketParams);
              Market l_market = new WEB3GentradeMarket(3303L);
              
              AttentionInfoHistoryParams l_attentionInfoHistoryParams = new AttentionInfoHistoryParams();
              l_attentionInfoHistoryParams.setAttentionInfoHistoryId(1001);
              l_attentionInfoHistoryParams.setAttentionInfoType("2");
              l_attentionInfoHistoryParams.setInstitutionCode("0D");
              l_attentionInfoHistoryParams.setMarketId(1002);
              l_attentionInfoHistoryParams.setValidUntilBizDate("30");
              l_attentionInfoHistoryParams.setAttentionInfoDivCode("A011");
              l_attentionInfoHistoryParams.setStandardName("pname");          
              l_attentionInfoHistoryParams.setInfoGenerationTimestamp(WEB3DateUtility.getDate("20090506", "yyyyMMdd"));
              l_attentionInfoHistoryParams.setTradeStopRestartTimestamp(WEB3DateUtility.getDate("20090516", "yyyyMMdd"));
              l_attentionInfoHistoryParams.setOrdReceiptRestartTimestamp(WEB3DateUtility.getDate("20090526", "yyyyMMdd"));
              
              l_attentionInfoHistoryParams.setOldEstimationPrice(11);
              l_attentionInfoHistoryParams.setNewEstimationPrice(12);
              l_attentionInfoHistoryParams.setOldLastClosingPrice(13);
              l_attentionInfoHistoryParams.setNewLastClosingPrice(14);
              l_attentionInfoHistoryParams.setOldBasePrice(15);
              l_attentionInfoHistoryParams.setNewBasePrice(16);
              
              l_attentionInfoHistoryParams.setOldPriceRangeType("a");
              l_attentionInfoHistoryParams.setNewPriceRangeType("b");
              l_attentionInfoHistoryParams.setOldPriceRangeUnitType("c");
              l_attentionInfoHistoryParams.setNewPriceRangeUnitType("d");
              
              //l_attentionInfoHistoryParams.setOldHighCompPriceRange(17);
              l_attentionInfoHistoryParams.setNewHighPriceRange(18);
              //l_attentionInfoHistoryParams.setOldLowCompPriceRange(9);
              l_attentionInfoHistoryParams.setNewLowPriceRange(19);
              
              l_attentionInfoHistoryParams.setOldLastClosingPriceUpdq(20);
              l_attentionInfoHistoryParams.setNewLastClosingPriceUpdq(21);
              
              l_attentionInfoHistoryParams.setOldBasePriceUpdq(22);
              l_attentionInfoHistoryParams.setNewBasePriceUpdq(23);
              
              l_attentionInfoHistoryParams.setFreeFormatTitle("fftitle");
              l_attentionInfoHistoryParams.setFreeFormatText("fftext");
              
              l_attentionInfoHistoryParams.setProcessResultDiv("2");//
              
              l_attentionInfoHistoryParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
              l_attentionInfoHistoryParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

              TestDBUtility.deleteAll(MailInfoRow.TYPE);
              MailInfoParams l_mailInfoParams = new MailInfoParams();
              l_mailInfoParams.setInstitutionCode("0D");
              l_mailInfoParams.setSendmailDiv("3102");
              l_mailInfoParams.setDiscernmentId("9");
              l_mailInfoParams.setSubject("abc");
              l_mailInfoParams.setLastUpdater("007");
              l_mailInfoParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
              l_mailInfoParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
              TestDBUtility.insertWithDel(l_mailInfoParams);

              TestDBUtility.deleteAll(MailProcRow.TYPE);
              TestDBUtility.deleteAll(ExtMailProcRow.TYPE);

              WEB3AdminEquityAttentionInfoNotifyTransactionCallback l_callback =
                  l_impl.new WEB3AdminEquityAttentionInfoNotifyTransactionCallback();
              
              Method l_method = WEB3AdminEquityAttentionInfoNotifyTransactionCallback.class.getDeclaredMethod(
                      "insertSendMail",
                      new Class[]{WEB3EquityProduct.class, Market.class, AttentionInfoHistoryParams.class});
              l_method.setAccessible(true);
              Object[] l_obj = {l_equityProduct, l_market, l_attentionInfoHistoryParams};
              l_method.invoke(l_callback, l_obj);

              MailProcRow l_mailProcRow =
                  MailProcDao.findRowByInstitutionCodeBranchCodeSendmailDivDiscernmentIdAccountCodeMailId(
                          "0D",
                          "000",
                          "3102",
                          "9",
                          "0000000",
                          1001);
              
              StringBuffer l_sbQueryString = new StringBuffer();
              l_sbQueryString.append("institution_code = ? and ");
              l_sbQueryString.append("branch_code = ? and ");
              l_sbQueryString.append("sendmail_div = ? and ");
              l_sbQueryString.append("discernment_id = ? and ");
              l_sbQueryString.append("account_code = ?");
              
              Object[] l_values = {"0D", "000", "3102", "9", "0000000"};
              
              QueryProcessor l_processor = Processors.getDefaultProcessor();
              List l_lisResults = l_processor.doFindAllQuery(
                  ExtMailProcRow.TYPE,
                  l_sbQueryString.toString(),
                  l_values);
              
              int l_intCnt = l_lisResults.size();
              ExtMailProcRow[] l_extMailProcRows =
                  new ExtMailProcRow[l_intCnt];
              l_lisResults.toArray(l_extMailProcRows);

              assertEquals("0", l_mailProcRow.getStatus());
              assertEquals(null, l_mailProcRow.getEmailAddress());
              assertEquals(null, l_mailProcRow.getSendEmailAddress());
              assertEquals("abc", l_mailProcRow.getSubject());
              assertEquals("pname", l_mailProcRow.getName1());
              assertEquals("シンガポール", l_mailProcRow.getName2()); 
              assertEquals("fftext", l_mailProcRow.getMailText());
              assertEquals(BooleanEnum.FALSE, l_mailProcRow.getDeleteFlag());
              
              assertEquals(19, l_extMailProcRows.length);
              assertEquals("2009/05/06 00:00:00", l_extMailProcRows[0].getItemContents());
              assertEquals("SP", l_extMailProcRows[1].getItemContents());
              assertEquals("16.0", l_extMailProcRows[2].getItemContents());
              assertEquals("23.0", l_extMailProcRows[3].getItemContents());
              assertEquals("12.0", l_extMailProcRows[4].getItemContents());
              assertEquals("18.0", l_extMailProcRows[5].getItemContents());
              assertEquals("14.0", l_extMailProcRows[6].getItemContents());
              assertEquals("21.0", l_extMailProcRows[7].getItemContents());
              assertEquals("19.0", l_extMailProcRows[8].getItemContents());
              assertEquals("b", l_extMailProcRows[9].getItemContents());
              assertEquals("d", l_extMailProcRows[10].getItemContents());
              assertEquals("15.0", l_extMailProcRows[11].getItemContents());
              assertEquals("22.0", l_extMailProcRows[12].getItemContents());
              assertEquals("11.0", l_extMailProcRows[13].getItemContents());
              //assertEquals("32.0", l_extMailProcRows[14].getItemContents());
              assertEquals("13.0", l_extMailProcRows[14].getItemContents());
              assertEquals("20.0", l_extMailProcRows[15].getItemContents());
              //assertEquals("6.0", l_extMailProcRows[17].getItemContents());
              assertEquals("a", l_extMailProcRows[16].getItemContents());
              assertEquals("c", l_extMailProcRows[17].getItemContents());
              assertEquals("1002", l_extMailProcRows[18].getItemContents());
          }
          catch (Exception l_ex)
          {
              log.exiting(STR_METHOD_NAME);
              fail();
          }
      }
   
      /*
       * 注意情報履歴テーブル.注意情報種別＝"制限値幅情報"
       * 注意情報履歴テーブル.基準値（変更前） = 15
       * 注意情報履歴テーブル.強制値幅（上限値）（変更前）= 17
       * 注意情報履歴テーブル.制限値幅下限（変更前） = 15
       */
      public void testInsertSendMail_0011()
      {
          final String STR_METHOD_NAME = "testInsertSendMail_0011()";
          log.entering(STR_METHOD_NAME);
          
          try
          {
              TestDBUtility.deleteAll(ProductRow.TYPE);
              ProductParams l_productParams = TestDBUtility.getProductRow();
              l_productParams.setProductId(3304148080000L);
              l_productParams.setStandardName("pname");
              TestDBUtility.insertWithDel(l_productParams);
              
              EqtypeProductParams l_eqtypeProductParams = new EqtypeProductParams();
              l_eqtypeProductParams.setProductId(3304148080000L);
              l_eqtypeProductParams.setInstitutionCode("0D");
              l_eqtypeProductParams.setProductCode("1002");
              l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
              l_eqtypeProductParams.setYearlyBooksClosingDate(WEB3DateUtility.getDate("20090108", "yyyyMMdd"));
              l_eqtypeProductParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
              l_eqtypeProductParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
              l_eqtypeProductParams.setStandardName("pname");
              WEB3EquityProduct l_equityProduct = new WEB3EquityProduct(l_eqtypeProductParams);
              
              MarketParams l_marketParams = TestDBUtility.getMarketRow();
              l_marketParams.setMarketId(3303L);
              TestDBUtility.insertWithDel(l_marketParams);
              Market l_market = new WEB3GentradeMarket(3303L);
              
              AttentionInfoHistoryParams l_attentionInfoHistoryParams = new AttentionInfoHistoryParams();
              l_attentionInfoHistoryParams.setAttentionInfoHistoryId(1001);
              l_attentionInfoHistoryParams.setAttentionInfoType("2");
              l_attentionInfoHistoryParams.setInstitutionCode("0D");
              l_attentionInfoHistoryParams.setMarketId(1002);
              l_attentionInfoHistoryParams.setValidUntilBizDate("30");
              l_attentionInfoHistoryParams.setAttentionInfoDivCode("A011");
              l_attentionInfoHistoryParams.setStandardName("pname");          
              l_attentionInfoHistoryParams.setInfoGenerationTimestamp(WEB3DateUtility.getDate("20090506", "yyyyMMdd"));
              l_attentionInfoHistoryParams.setTradeStopRestartTimestamp(WEB3DateUtility.getDate("20090516", "yyyyMMdd"));
              l_attentionInfoHistoryParams.setOrdReceiptRestartTimestamp(WEB3DateUtility.getDate("20090526", "yyyyMMdd"));
              
              l_attentionInfoHistoryParams.setOldEstimationPrice(11);
              l_attentionInfoHistoryParams.setNewEstimationPrice(12);
              l_attentionInfoHistoryParams.setOldLastClosingPrice(13);
              l_attentionInfoHistoryParams.setNewLastClosingPrice(14);
              l_attentionInfoHistoryParams.setOldBasePrice(15);
              l_attentionInfoHistoryParams.setNewBasePrice(16);
              
              l_attentionInfoHistoryParams.setOldPriceRangeType("a");
              l_attentionInfoHistoryParams.setNewPriceRangeType("b");
              l_attentionInfoHistoryParams.setOldPriceRangeUnitType("c");
              l_attentionInfoHistoryParams.setNewPriceRangeUnitType("d");
              
              l_attentionInfoHistoryParams.setOldHighCompPriceRange(17);
              l_attentionInfoHistoryParams.setNewHighPriceRange(18);
              l_attentionInfoHistoryParams.setOldLowCompPriceRange(15);
              l_attentionInfoHistoryParams.setNewLowPriceRange(19);
              
              l_attentionInfoHistoryParams.setOldLastClosingPriceUpdq(20);
              l_attentionInfoHistoryParams.setNewLastClosingPriceUpdq(21);
              
              l_attentionInfoHistoryParams.setOldBasePriceUpdq(22);
              l_attentionInfoHistoryParams.setNewBasePriceUpdq(23);
              
              l_attentionInfoHistoryParams.setFreeFormatTitle("fftitle");
              l_attentionInfoHistoryParams.setFreeFormatText("fftext");
              
              l_attentionInfoHistoryParams.setProcessResultDiv("2");
              
              l_attentionInfoHistoryParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
              l_attentionInfoHistoryParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

              TestDBUtility.deleteAll(MailInfoRow.TYPE);
              MailInfoParams l_mailInfoParams = new MailInfoParams();
              l_mailInfoParams.setInstitutionCode("0D");
              l_mailInfoParams.setSendmailDiv("3102");
              l_mailInfoParams.setDiscernmentId("9");
              l_mailInfoParams.setSubject("abc");
              l_mailInfoParams.setLastUpdater("007");
              l_mailInfoParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
              l_mailInfoParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
              TestDBUtility.insertWithDel(l_mailInfoParams);

              TestDBUtility.deleteAll(MailProcRow.TYPE);
              TestDBUtility.deleteAll(ExtMailProcRow.TYPE);

              WEB3AdminEquityAttentionInfoNotifyTransactionCallback l_callback =
                  l_impl.new WEB3AdminEquityAttentionInfoNotifyTransactionCallback();
              
              Method l_method = WEB3AdminEquityAttentionInfoNotifyTransactionCallback.class.getDeclaredMethod(
                      "insertSendMail",
                      new Class[]{WEB3EquityProduct.class, Market.class, AttentionInfoHistoryParams.class});
              l_method.setAccessible(true);
              Object[] l_obj = {l_equityProduct, l_market, l_attentionInfoHistoryParams};
              l_method.invoke(l_callback, l_obj);

              MailProcRow l_mailProcRow =
                  MailProcDao.findRowByInstitutionCodeBranchCodeSendmailDivDiscernmentIdAccountCodeMailId(
                          "0D",
                          "000",
                          "3102",
                          "9",
                          "0000000",
                          1001);
              
              StringBuffer l_sbQueryString = new StringBuffer();
              l_sbQueryString.append("institution_code = ? and ");
              l_sbQueryString.append("branch_code = ? and ");
              l_sbQueryString.append("sendmail_div = ? and ");
              l_sbQueryString.append("discernment_id = ? and ");
              l_sbQueryString.append("account_code = ?");
              
              Object[] l_values = {"0D", "000", "3102", "9", "0000000"};
              
              QueryProcessor l_processor = Processors.getDefaultProcessor();
              List l_lisResults = l_processor.doFindAllQuery(
                  ExtMailProcRow.TYPE,
                  l_sbQueryString.toString(),
                  l_values);
              
              int l_intCnt = l_lisResults.size();
              ExtMailProcRow[] l_extMailProcRows =
                  new ExtMailProcRow[l_intCnt];
              l_lisResults.toArray(l_extMailProcRows);

              assertEquals("0", l_mailProcRow.getStatus());
              assertEquals(null, l_mailProcRow.getEmailAddress());
              assertEquals(null, l_mailProcRow.getSendEmailAddress());
              assertEquals("abc", l_mailProcRow.getSubject());
              assertEquals("pname", l_mailProcRow.getName1());
              assertEquals("シンガポール", l_mailProcRow.getName2()); 
              assertEquals("fftext", l_mailProcRow.getMailText());
              assertEquals(BooleanEnum.FALSE, l_mailProcRow.getDeleteFlag());
              
              assertEquals(20, l_extMailProcRows.length);
              assertEquals("2009/05/06 00:00:00", l_extMailProcRows[0].getItemContents());
              assertEquals("SP", l_extMailProcRows[1].getItemContents());
              assertEquals("16.0", l_extMailProcRows[2].getItemContents());
              assertEquals("23.0", l_extMailProcRows[3].getItemContents());
              assertEquals("12.0", l_extMailProcRows[4].getItemContents());
              assertEquals("18.0", l_extMailProcRows[5].getItemContents());
              assertEquals("14.0", l_extMailProcRows[6].getItemContents());
              assertEquals("21.0", l_extMailProcRows[7].getItemContents());
              assertEquals("19.0", l_extMailProcRows[8].getItemContents());
              assertEquals("b", l_extMailProcRows[9].getItemContents());
              assertEquals("d", l_extMailProcRows[10].getItemContents());
              assertEquals("15.0", l_extMailProcRows[11].getItemContents());
              assertEquals("22.0", l_extMailProcRows[12].getItemContents());
              assertEquals("11.0", l_extMailProcRows[13].getItemContents());
              assertEquals("32.0", l_extMailProcRows[14].getItemContents());
              assertEquals("13.0", l_extMailProcRows[15].getItemContents());
              assertEquals("20.0", l_extMailProcRows[16].getItemContents());
              //assertEquals("6.0", l_extMailProcRows[17].getItemContents());
              assertEquals("a", l_extMailProcRows[17].getItemContents());
              assertEquals("c", l_extMailProcRows[18].getItemContents());
              assertEquals("1002", l_extMailProcRows[19].getItemContents());
          }
          catch (Exception l_ex)
          {
              log.exiting(STR_METHOD_NAME);
              fail();
          }
      }
  
      /*
       * 注意情報履歴テーブル.注意情報種別＝"エラー"
       * 注意情報履歴テーブル.基準値（変更前） = null
       * 注意情報履歴テーブル.強制値幅（上限値）（変更前）≠null
       * 注意情報履歴テーブル.制限値幅下限（変更前） ≠ null
       */
      public void testInsertSendMail_0012()
      {
          final String STR_METHOD_NAME = "testInsertSendMail_0012()";
          log.entering(STR_METHOD_NAME);
          
          try
          {
              TestDBUtility.deleteAll(ProductRow.TYPE);
              ProductParams l_productParams = TestDBUtility.getProductRow();
              l_productParams.setProductId(3304148080000L);
              l_productParams.setStandardName("pname");
              TestDBUtility.insertWithDel(l_productParams);
              
              EqtypeProductParams l_eqtypeProductParams = new EqtypeProductParams();
              l_eqtypeProductParams.setProductId(3304148080000L);
              l_eqtypeProductParams.setInstitutionCode("0D");
              l_eqtypeProductParams.setProductCode("1002");
              l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
              l_eqtypeProductParams.setYearlyBooksClosingDate(WEB3DateUtility.getDate("20090108", "yyyyMMdd"));
              l_eqtypeProductParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
              l_eqtypeProductParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
              l_eqtypeProductParams.setStandardName("pname");
              WEB3EquityProduct l_equityProduct = new WEB3EquityProduct(l_eqtypeProductParams);
              
              MarketParams l_marketParams = TestDBUtility.getMarketRow();
              l_marketParams.setMarketId(3303L);
              TestDBUtility.insertWithDel(l_marketParams);
              Market l_market = new WEB3GentradeMarket(3303L);
              
              AttentionInfoHistoryParams l_attentionInfoHistoryParams = new AttentionInfoHistoryParams();
              l_attentionInfoHistoryParams.setAttentionInfoHistoryId(1001);
              l_attentionInfoHistoryParams.setAttentionInfoType("1");
              l_attentionInfoHistoryParams.setInstitutionCode("0D");
              l_attentionInfoHistoryParams.setMarketId(1002);
              l_attentionInfoHistoryParams.setValidUntilBizDate("30");
              l_attentionInfoHistoryParams.setAttentionInfoDivCode("A011");
              l_attentionInfoHistoryParams.setStandardName("pname");          
              l_attentionInfoHistoryParams.setInfoGenerationTimestamp(WEB3DateUtility.getDate("20090506", "yyyyMMdd"));
              l_attentionInfoHistoryParams.setTradeStopRestartTimestamp(WEB3DateUtility.getDate("20090516", "yyyyMMdd"));
              l_attentionInfoHistoryParams.setOrdReceiptRestartTimestamp(WEB3DateUtility.getDate("20090526", "yyyyMMdd"));
              
              l_attentionInfoHistoryParams.setOldEstimationPrice(11);
              l_attentionInfoHistoryParams.setNewEstimationPrice(12);
              l_attentionInfoHistoryParams.setOldLastClosingPrice(13);
              l_attentionInfoHistoryParams.setNewLastClosingPrice(14);
              //l_attentionInfoHistoryParams.setOldBasePrice(15);
              l_attentionInfoHistoryParams.setNewBasePrice(16);
              
              l_attentionInfoHistoryParams.setOldPriceRangeType("a");
              l_attentionInfoHistoryParams.setNewPriceRangeType("b");
              l_attentionInfoHistoryParams.setOldPriceRangeUnitType("c");
              l_attentionInfoHistoryParams.setNewPriceRangeUnitType("d");
              
              //l_attentionInfoHistoryParams.setOldHighCompPriceRange(17);
              l_attentionInfoHistoryParams.setNewHighPriceRange(18);
              //l_attentionInfoHistoryParams.setOldLowCompPriceRange(9);
              l_attentionInfoHistoryParams.setNewLowPriceRange(19);
              
              l_attentionInfoHistoryParams.setOldLastClosingPriceUpdq(20);
              l_attentionInfoHistoryParams.setNewLastClosingPriceUpdq(21);
              
              l_attentionInfoHistoryParams.setOldBasePriceUpdq(22);
              l_attentionInfoHistoryParams.setNewBasePriceUpdq(23);
              
              l_attentionInfoHistoryParams.setFreeFormatTitle("fftitle");
              l_attentionInfoHistoryParams.setFreeFormatText("fftext");
              
              l_attentionInfoHistoryParams.setProcessResultDiv("9");
              
              l_attentionInfoHistoryParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
              l_attentionInfoHistoryParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

              TestDBUtility.deleteAll(MailInfoRow.TYPE);
              MailInfoParams l_mailInfoParams = new MailInfoParams();
              l_mailInfoParams.setInstitutionCode("0D");
              l_mailInfoParams.setSendmailDiv("3104");
              l_mailInfoParams.setDiscernmentId("1");
              l_mailInfoParams.setSubject("abc");
              l_mailInfoParams.setLastUpdater("007");
              l_mailInfoParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
              l_mailInfoParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
              TestDBUtility.insertWithDel(l_mailInfoParams);

              TestDBUtility.deleteAll(MailProcRow.TYPE);
              TestDBUtility.deleteAll(ExtMailProcRow.TYPE);

              WEB3AdminEquityAttentionInfoNotifyTransactionCallback l_callback =
                  l_impl.new WEB3AdminEquityAttentionInfoNotifyTransactionCallback();
              
              Method l_method = WEB3AdminEquityAttentionInfoNotifyTransactionCallback.class.getDeclaredMethod(
                      "insertSendMail",
                      new Class[]{WEB3EquityProduct.class, Market.class, AttentionInfoHistoryParams.class});
              l_method.setAccessible(true);
              Object[] l_obj = {l_equityProduct, l_market, l_attentionInfoHistoryParams};
              l_method.invoke(l_callback, l_obj);

              MailProcRow l_mailProcRow =
                  MailProcDao.findRowByInstitutionCodeBranchCodeSendmailDivDiscernmentIdAccountCodeMailId(
                          "0D",
                          "000",
                          "3104",
                          "1",
                          "0000000",
                          1001);

              StringBuffer l_sbQueryString = new StringBuffer();
              l_sbQueryString.append("institution_code = ? and ");
              l_sbQueryString.append("branch_code = ? and ");
              l_sbQueryString.append("sendmail_div = ? and ");
              l_sbQueryString.append("discernment_id = ? and ");
              l_sbQueryString.append("account_code = ?");
              
              Object[] l_values = {"0D", "000", "3104", "1", "0000000"};
              
              QueryProcessor l_processor = Processors.getDefaultProcessor();
              List l_lisResults = l_processor.doFindAllQuery(
                  ExtMailProcRow.TYPE,
                  l_sbQueryString.toString(),
                  l_values);
              
              int l_intCnt = l_lisResults.size();
              ExtMailProcRow[] l_extMailProcRows =
                  new ExtMailProcRow[l_intCnt];
              l_lisResults.toArray(l_extMailProcRows);
              
              assertEquals("0", l_mailProcRow.getStatus());
              assertEquals(null, l_mailProcRow.getEmailAddress());
              assertEquals(null, l_mailProcRow.getSendEmailAddress());
              assertEquals("abc", l_mailProcRow.getSubject());
              assertEquals("pname", l_mailProcRow.getName1());
              assertEquals("シンガポール", l_mailProcRow.getName2()); 
              assertEquals("fftext", l_mailProcRow.getMailText());
              assertEquals(BooleanEnum.FALSE, l_mailProcRow.getDeleteFlag());
              
              assertEquals(21, l_extMailProcRows.length);
              assertEquals("2009/05/06 00:00:00", l_extMailProcRows[0].getItemContents());
              assertEquals("SP", l_extMailProcRows[1].getItemContents());
              assertEquals("16.0", l_extMailProcRows[2].getItemContents());
              assertEquals("23.0", l_extMailProcRows[3].getItemContents());
              assertEquals("12.0", l_extMailProcRows[4].getItemContents());
              assertEquals("18.0", l_extMailProcRows[5].getItemContents());
              assertEquals("14.0", l_extMailProcRows[6].getItemContents());
              assertEquals("21.0", l_extMailProcRows[7].getItemContents());
              assertEquals("19.0", l_extMailProcRows[8].getItemContents());
              assertEquals("b", l_extMailProcRows[9].getItemContents());
              assertEquals("d", l_extMailProcRows[10].getItemContents());
              //assertEquals("15.0", l_extMailProcRows[11].getItemContents());
              assertEquals("22.0", l_extMailProcRows[11].getItemContents());
              assertEquals("11.0", l_extMailProcRows[12].getItemContents());
              //assertEquals("32.0", l_extMailProcRows[14].getItemContents());
              assertEquals("13.0", l_extMailProcRows[13].getItemContents());
              assertEquals("20.0", l_extMailProcRows[14].getItemContents());
              //assertEquals("6.0", l_extMailProcRows[17].getItemContents());
              assertEquals("a", l_extMailProcRows[15].getItemContents());
              assertEquals("c", l_extMailProcRows[16].getItemContents());
              assertEquals("2009/05/26 00:00:00", l_extMailProcRows[17].getItemContents());
              assertEquals("1002", l_extMailProcRows[18].getItemContents());
              assertEquals("fftitle", l_extMailProcRows[19].getItemContents());
              assertEquals("2009/05/16 00:00:00", l_extMailProcRows[20].getItemContents());
          }
          catch (Exception l_ex)
          {
              log.exiting(STR_METHOD_NAME);
              fail();
          }
      }
   
      /*
       * 注意情報履歴テーブル.注意情報種別＝"エラー"
       * 注意情報履歴テーブル.基準値（変更前）≠ null
       * 注意情報履歴テーブル.強制値幅（上限値）（変更前）=null
       * 注意情報履歴テーブル.制限値幅下限（変更前） = null
       */
      public void testInsertSendMail_0013()
      {
          final String STR_METHOD_NAME = "testInsertSendMail_0013()";
          log.entering(STR_METHOD_NAME);
          
          try
          {
              TestDBUtility.deleteAll(ProductRow.TYPE);
              ProductParams l_productParams = TestDBUtility.getProductRow();
              l_productParams.setProductId(3304148080000L);
              l_productParams.setStandardName("pname");
              TestDBUtility.insertWithDel(l_productParams);
              
              EqtypeProductParams l_eqtypeProductParams = new EqtypeProductParams();
              l_eqtypeProductParams.setProductId(3304148080000L);
              l_eqtypeProductParams.setInstitutionCode("0D");
              l_eqtypeProductParams.setProductCode("1002");
              l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
              l_eqtypeProductParams.setYearlyBooksClosingDate(WEB3DateUtility.getDate("20090108", "yyyyMMdd"));
              l_eqtypeProductParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
              l_eqtypeProductParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
              l_eqtypeProductParams.setStandardName("pname");
              WEB3EquityProduct l_equityProduct = new WEB3EquityProduct(l_eqtypeProductParams);
              
              MarketParams l_marketParams = TestDBUtility.getMarketRow();
              l_marketParams.setMarketId(3303L);
              TestDBUtility.insertWithDel(l_marketParams);
              Market l_market = new WEB3GentradeMarket(3303L);
              
              AttentionInfoHistoryParams l_attentionInfoHistoryParams = new AttentionInfoHistoryParams();
              l_attentionInfoHistoryParams.setAttentionInfoHistoryId(1001);
              l_attentionInfoHistoryParams.setAttentionInfoType("1");
              l_attentionInfoHistoryParams.setInstitutionCode("0D");
              l_attentionInfoHistoryParams.setMarketId(1002);
              l_attentionInfoHistoryParams.setValidUntilBizDate("30");
              l_attentionInfoHistoryParams.setAttentionInfoDivCode("A011");
              l_attentionInfoHistoryParams.setStandardName("pname");          
              l_attentionInfoHistoryParams.setInfoGenerationTimestamp(WEB3DateUtility.getDate("20090506", "yyyyMMdd"));
              l_attentionInfoHistoryParams.setTradeStopRestartTimestamp(WEB3DateUtility.getDate("20090516", "yyyyMMdd"));
              l_attentionInfoHistoryParams.setOrdReceiptRestartTimestamp(WEB3DateUtility.getDate("20090526", "yyyyMMdd"));
              
              l_attentionInfoHistoryParams.setOldEstimationPrice(11);
              l_attentionInfoHistoryParams.setNewEstimationPrice(12);
              l_attentionInfoHistoryParams.setOldLastClosingPrice(13);
              l_attentionInfoHistoryParams.setNewLastClosingPrice(14);
              l_attentionInfoHistoryParams.setOldBasePrice(15);
              l_attentionInfoHistoryParams.setNewBasePrice(16);
              
              l_attentionInfoHistoryParams.setOldPriceRangeType("a");
              l_attentionInfoHistoryParams.setNewPriceRangeType("b");
              l_attentionInfoHistoryParams.setOldPriceRangeUnitType("c");
              l_attentionInfoHistoryParams.setNewPriceRangeUnitType("d");
              
              //l_attentionInfoHistoryParams.setOldHighCompPriceRange(17);
              l_attentionInfoHistoryParams.setNewHighPriceRange(18);
              //l_attentionInfoHistoryParams.setOldLowCompPriceRange(9);
              l_attentionInfoHistoryParams.setNewLowPriceRange(19);
              
              l_attentionInfoHistoryParams.setOldLastClosingPriceUpdq(20);
              l_attentionInfoHistoryParams.setNewLastClosingPriceUpdq(21);
              
              l_attentionInfoHistoryParams.setOldBasePriceUpdq(22);
              l_attentionInfoHistoryParams.setNewBasePriceUpdq(23);
              
              l_attentionInfoHistoryParams.setFreeFormatTitle("fftitle");
              l_attentionInfoHistoryParams.setFreeFormatText("fftext");
              
              l_attentionInfoHistoryParams.setProcessResultDiv("9");
              
              l_attentionInfoHistoryParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
              l_attentionInfoHistoryParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

              TestDBUtility.deleteAll(MailInfoRow.TYPE);
              MailInfoParams l_mailInfoParams = new MailInfoParams();
              l_mailInfoParams.setInstitutionCode("0D");
              l_mailInfoParams.setSendmailDiv("3104");
              l_mailInfoParams.setDiscernmentId("1");
              l_mailInfoParams.setSubject("abc");
              l_mailInfoParams.setLastUpdater("007");
              l_mailInfoParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
              l_mailInfoParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
              TestDBUtility.insertWithDel(l_mailInfoParams);

              TestDBUtility.deleteAll(MailProcRow.TYPE);
              TestDBUtility.deleteAll(ExtMailProcRow.TYPE);

              WEB3AdminEquityAttentionInfoNotifyTransactionCallback l_callback =
                  l_impl.new WEB3AdminEquityAttentionInfoNotifyTransactionCallback();
              
              Method l_method = WEB3AdminEquityAttentionInfoNotifyTransactionCallback.class.getDeclaredMethod(
                      "insertSendMail",
                      new Class[]{WEB3EquityProduct.class, Market.class, AttentionInfoHistoryParams.class});
              l_method.setAccessible(true);
              Object[] l_obj = {l_equityProduct, l_market, l_attentionInfoHistoryParams};
              l_method.invoke(l_callback, l_obj);

              MailProcRow l_mailProcRow =
                  MailProcDao.findRowByInstitutionCodeBranchCodeSendmailDivDiscernmentIdAccountCodeMailId(
                          "0D",
                          "000",
                          "3104",//
                          "1",//
                          "0000000",
                          1001);

              StringBuffer l_sbQueryString = new StringBuffer();
              l_sbQueryString.append("institution_code = ? and ");
              l_sbQueryString.append("branch_code = ? and ");
              l_sbQueryString.append("sendmail_div = ? and ");
              l_sbQueryString.append("discernment_id = ? and ");
              l_sbQueryString.append("account_code = ?");
              
              Object[] l_values = {"0D", "000", "3104", "1", "0000000"};
              
              QueryProcessor l_processor = Processors.getDefaultProcessor();
              List l_lisResults = l_processor.doFindAllQuery(
                  ExtMailProcRow.TYPE,
                  l_sbQueryString.toString(),
                  l_values);
              
              int l_intCnt = l_lisResults.size();
              ExtMailProcRow[] l_extMailProcRows =
                  new ExtMailProcRow[l_intCnt];
              l_lisResults.toArray(l_extMailProcRows);
              
              assertEquals("0", l_mailProcRow.getStatus());
              assertEquals(null, l_mailProcRow.getEmailAddress());
              assertEquals(null, l_mailProcRow.getSendEmailAddress());
              assertEquals("abc", l_mailProcRow.getSubject());
              assertEquals("pname", l_mailProcRow.getName1());
              assertEquals("シンガポール", l_mailProcRow.getName2()); 
              assertEquals("fftext", l_mailProcRow.getMailText());
              assertEquals(BooleanEnum.FALSE, l_mailProcRow.getDeleteFlag());
              
              assertEquals(22, l_extMailProcRows.length);
              assertEquals("2009/05/06 00:00:00", l_extMailProcRows[0].getItemContents());
              assertEquals("SP", l_extMailProcRows[1].getItemContents());
              assertEquals("16.0", l_extMailProcRows[2].getItemContents());
              assertEquals("23.0", l_extMailProcRows[3].getItemContents());
              assertEquals("12.0", l_extMailProcRows[4].getItemContents());
              assertEquals("18.0", l_extMailProcRows[5].getItemContents());
              assertEquals("14.0", l_extMailProcRows[6].getItemContents());
              assertEquals("21.0", l_extMailProcRows[7].getItemContents());
              assertEquals("19.0", l_extMailProcRows[8].getItemContents());
              assertEquals("b", l_extMailProcRows[9].getItemContents());
              assertEquals("d", l_extMailProcRows[10].getItemContents());
              assertEquals("15.0", l_extMailProcRows[11].getItemContents());
              assertEquals("22.0", l_extMailProcRows[12].getItemContents());
              assertEquals("11.0", l_extMailProcRows[13].getItemContents());
              //assertEquals("32.0", l_extMailProcRows[14].getItemContents());
              assertEquals("13.0", l_extMailProcRows[14].getItemContents());
              assertEquals("20.0", l_extMailProcRows[15].getItemContents());
              //assertEquals("6.0", l_extMailProcRows[17].getItemContents());
              assertEquals("a", l_extMailProcRows[16].getItemContents());
              assertEquals("c", l_extMailProcRows[17].getItemContents());
              assertEquals("2009/05/26 00:00:00", l_extMailProcRows[18].getItemContents());
              assertEquals("1002", l_extMailProcRows[19].getItemContents());
              assertEquals("fftitle", l_extMailProcRows[20].getItemContents());
              assertEquals("2009/05/16 00:00:00", l_extMailProcRows[21].getItemContents());
          }
          catch (Exception l_ex)
          {
              log.exiting(STR_METHOD_NAME);
              fail();
          }
      }
   
      /*
       * 注意情報履歴テーブル.注意情報種別＝"エラー"
       * 注意情報履歴テーブル.基準値（変更前） = 15
       * 注意情報履歴テーブル.強制値幅（上限値）（変更前）= 17
       * 注意情報履歴テーブル.制限値幅下限（変更前） = 15
       */
      public void testInsertSendMail_0014()
      {
          final String STR_METHOD_NAME = "testInsertSendMail_0014()";
          log.entering(STR_METHOD_NAME);
          
          try
          {
              TestDBUtility.deleteAll(ProductRow.TYPE);
              ProductParams l_productParams = TestDBUtility.getProductRow();
              l_productParams.setProductId(3304148080000L);
              l_productParams.setStandardName("pname");
              TestDBUtility.insertWithDel(l_productParams);
              
              EqtypeProductParams l_eqtypeProductParams = new EqtypeProductParams();
              l_eqtypeProductParams.setProductId(3304148080000L);
              l_eqtypeProductParams.setInstitutionCode("0D");
              l_eqtypeProductParams.setProductCode("1002");
              l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
              l_eqtypeProductParams.setYearlyBooksClosingDate(WEB3DateUtility.getDate("20090108", "yyyyMMdd"));
              l_eqtypeProductParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
              l_eqtypeProductParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
              l_eqtypeProductParams.setStandardName("pname");
              WEB3EquityProduct l_equityProduct = new WEB3EquityProduct(l_eqtypeProductParams);
              
              MarketParams l_marketParams = TestDBUtility.getMarketRow();
              l_marketParams.setMarketId(3303L);
              TestDBUtility.insertWithDel(l_marketParams);
              Market l_market = new WEB3GentradeMarket(3303L);
              
              AttentionInfoHistoryParams l_attentionInfoHistoryParams = new AttentionInfoHistoryParams();
              l_attentionInfoHistoryParams.setAttentionInfoHistoryId(1001);
              l_attentionInfoHistoryParams.setAttentionInfoType("1");
              l_attentionInfoHistoryParams.setInstitutionCode("0D");
              l_attentionInfoHistoryParams.setMarketId(1002);
              l_attentionInfoHistoryParams.setValidUntilBizDate("30");
              l_attentionInfoHistoryParams.setAttentionInfoDivCode("A011");
              l_attentionInfoHistoryParams.setStandardName("pname");          
              l_attentionInfoHistoryParams.setInfoGenerationTimestamp(WEB3DateUtility.getDate("20090506", "yyyyMMdd"));
              l_attentionInfoHistoryParams.setTradeStopRestartTimestamp(WEB3DateUtility.getDate("20090516", "yyyyMMdd"));
              l_attentionInfoHistoryParams.setOrdReceiptRestartTimestamp(WEB3DateUtility.getDate("20090526", "yyyyMMdd"));
              
              l_attentionInfoHistoryParams.setOldEstimationPrice(11);
              l_attentionInfoHistoryParams.setNewEstimationPrice(12);
              l_attentionInfoHistoryParams.setOldLastClosingPrice(13);
              l_attentionInfoHistoryParams.setNewLastClosingPrice(14);
              l_attentionInfoHistoryParams.setOldBasePrice(15);
              l_attentionInfoHistoryParams.setNewBasePrice(16);
              
              l_attentionInfoHistoryParams.setOldPriceRangeType("a");
              l_attentionInfoHistoryParams.setNewPriceRangeType("b");
              l_attentionInfoHistoryParams.setOldPriceRangeUnitType("c");
              l_attentionInfoHistoryParams.setNewPriceRangeUnitType("d");
              
              l_attentionInfoHistoryParams.setOldHighCompPriceRange(17);
              l_attentionInfoHistoryParams.setNewHighPriceRange(18);
              l_attentionInfoHistoryParams.setOldLowCompPriceRange(15);
              l_attentionInfoHistoryParams.setNewLowPriceRange(19);
              
              l_attentionInfoHistoryParams.setOldLastClosingPriceUpdq(20);
              l_attentionInfoHistoryParams.setNewLastClosingPriceUpdq(21);
              
              l_attentionInfoHistoryParams.setOldBasePriceUpdq(22);
              l_attentionInfoHistoryParams.setNewBasePriceUpdq(23);
              
              l_attentionInfoHistoryParams.setFreeFormatTitle("fftitle");
              l_attentionInfoHistoryParams.setFreeFormatText("fftext");
              
              l_attentionInfoHistoryParams.setProcessResultDiv("9");
              
              l_attentionInfoHistoryParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
              l_attentionInfoHistoryParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

              TestDBUtility.deleteAll(MailInfoRow.TYPE);
              MailInfoParams l_mailInfoParams = new MailInfoParams();
              l_mailInfoParams.setInstitutionCode("0D");
              l_mailInfoParams.setSendmailDiv("3104");
              l_mailInfoParams.setDiscernmentId("1");
              l_mailInfoParams.setSubject("abc");
              l_mailInfoParams.setLastUpdater("007");
              l_mailInfoParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
              l_mailInfoParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
              TestDBUtility.insertWithDel(l_mailInfoParams);

              TestDBUtility.deleteAll(MailProcRow.TYPE);
              TestDBUtility.deleteAll(ExtMailProcRow.TYPE);

              WEB3AdminEquityAttentionInfoNotifyTransactionCallback l_callback =
                  l_impl.new WEB3AdminEquityAttentionInfoNotifyTransactionCallback();
              
              Method l_method = WEB3AdminEquityAttentionInfoNotifyTransactionCallback.class.getDeclaredMethod(
                      "insertSendMail",
                      new Class[]{WEB3EquityProduct.class, Market.class, AttentionInfoHistoryParams.class});
              l_method.setAccessible(true);
              Object[] l_obj = {l_equityProduct, l_market, l_attentionInfoHistoryParams};
              l_method.invoke(l_callback, l_obj);

              MailProcRow l_mailProcRow =
                  MailProcDao.findRowByInstitutionCodeBranchCodeSendmailDivDiscernmentIdAccountCodeMailId(
                          "0D",
                          "000",
                          "3104",
                          "1",
                          "0000000",
                          1001);

              StringBuffer l_sbQueryString = new StringBuffer();
              l_sbQueryString.append("institution_code = ? and ");
              l_sbQueryString.append("branch_code = ? and ");
              l_sbQueryString.append("sendmail_div = ? and ");
              l_sbQueryString.append("discernment_id = ? and ");
              l_sbQueryString.append("account_code = ?");
              
              Object[] l_values = {"0D", "000", "3104", "1", "0000000"};
              
              QueryProcessor l_processor = Processors.getDefaultProcessor();
              List l_lisResults = l_processor.doFindAllQuery(
                  ExtMailProcRow.TYPE,
                  l_sbQueryString.toString(),
                  l_values);
              
              int l_intCnt = l_lisResults.size();
              ExtMailProcRow[] l_extMailProcRows =
                  new ExtMailProcRow[l_intCnt];
              l_lisResults.toArray(l_extMailProcRows);
              
              assertEquals("0", l_mailProcRow.getStatus());
              assertEquals(null, l_mailProcRow.getEmailAddress());
              assertEquals(null, l_mailProcRow.getSendEmailAddress());
              assertEquals("abc", l_mailProcRow.getSubject());
              assertEquals("pname", l_mailProcRow.getName1());
              assertEquals("シンガポール", l_mailProcRow.getName2()); 
              assertEquals("fftext", l_mailProcRow.getMailText());
              assertEquals(BooleanEnum.FALSE, l_mailProcRow.getDeleteFlag());
              
              assertEquals(23, l_extMailProcRows.length);
              assertEquals("2009/05/06 00:00:00", l_extMailProcRows[0].getItemContents());
              assertEquals("SP", l_extMailProcRows[1].getItemContents());
              assertEquals("16.0", l_extMailProcRows[2].getItemContents());
              assertEquals("23.0", l_extMailProcRows[3].getItemContents());
              assertEquals("12.0", l_extMailProcRows[4].getItemContents());
              assertEquals("18.0", l_extMailProcRows[5].getItemContents());
              assertEquals("14.0", l_extMailProcRows[6].getItemContents());
              assertEquals("21.0", l_extMailProcRows[7].getItemContents());
              assertEquals("19.0", l_extMailProcRows[8].getItemContents());
              assertEquals("b", l_extMailProcRows[9].getItemContents());
              assertEquals("d", l_extMailProcRows[10].getItemContents());
              assertEquals("15.0", l_extMailProcRows[11].getItemContents());
              assertEquals("22.0", l_extMailProcRows[12].getItemContents());
              assertEquals("11.0", l_extMailProcRows[13].getItemContents());
              assertEquals("32.0", l_extMailProcRows[14].getItemContents());
              assertEquals("13.0", l_extMailProcRows[15].getItemContents());
              assertEquals("20.0", l_extMailProcRows[16].getItemContents());
              //assertEquals("6.0", l_extMailProcRows[17].getItemContents());
              assertEquals("a", l_extMailProcRows[17].getItemContents());
              assertEquals("c", l_extMailProcRows[18].getItemContents());
              assertEquals("2009/05/26 00:00:00", l_extMailProcRows[19].getItemContents());
              assertEquals("1002", l_extMailProcRows[20].getItemContents());
              assertEquals("fftitle", l_extMailProcRows[21].getItemContents());
              assertEquals("2009/05/16 00:00:00", l_extMailProcRows[22].getItemContents());
          }
          catch (Exception l_ex)
          {
              log.exiting(STR_METHOD_NAME);
              fail();
          }
      }
}
@
