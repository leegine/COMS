head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.32.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AioMarketRequestSenderServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : //TODO(WEB3AioMarketRequestSenderServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/10 徐宏偉 (中訊) 新規作成
Revesion History : 2007/07/31 金傑(中訊) 仕様変更モデルNo.747
*/
package webbroker3.aio.marketadaptor;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketRequestSendResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderManager;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AssetTransferTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderActionRow;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderRow;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbaio.market.messages.AioNewOrderMarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.xbaio.stdimpls.AioOrderManagerImpl;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqProductRow;

import test.util.TestDBUtility;

import webbroker3.aio.data.HostForeignCashTransOrderDao;
import webbroker3.aio.data.HostForeignCashTransOrderRow;
import webbroker3.aio.data.HostMrgsecTransferRow;
import webbroker3.aio.data.HostTransferOrderParams;
import webbroker3.aio.data.HostTransferOrderRow;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.CalendarParams;
import webbroker3.gentrade.data.CalendarRow;
import webbroker3.gentrade.data.FinInstitutionParams;
import webbroker3.gentrade.data.FinInstitutionRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * XXXXXXクラス//TODO
 *
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public class WEB3AioMarketRequestSenderServiceImplTest extends TestBaseForMock
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioMarketRequestSenderServiceImplTest.class);

    WEB3AioMarketRequestSenderServiceImpl l_impl = new WEB3AioMarketRequestSenderServiceImplForTest();
    public WEB3AioMarketRequestSenderServiceImplTest(String arg0)
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

    /*
     * 
     */
    public void testCreateHostForeignCashoutOrderData_case001()
    {
        final String STR_METHOD_NAME = "testCreateHostForeignCashoutOrderData_case001";
        log.exiting(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(HostForeignCashTransOrderRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountCode("321");
            l_mainAccountParams.setBranchId(222);
            l_mainAccountParams.setInstitutionId(123);
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setInstitutionCode("0D");
            //口座ＩＤ]
            l_subAccountParams.setAccountId(333812512203L);
            //補助口座ＩＤ
            l_subAccountParams.setSubAccountId(33381251220301L);
            //補助口座タイプ
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setBranchId(222);
            l_subAccountParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_subAccountParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("624");
            l_branchParams.setBranchId(222);
            TestDBUtility.insertWithDel(l_branchParams);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);
            AioOrderUnitParams l_params = TestDBUtility.getAioOrderUnitRow();
            l_params.setOrderUnitId(10);
            l_params.setAccountId(333812512203L);
            l_params.setOrderRequestNumber("24");
            l_params.setCurrencyCode("10");
            TestDBUtility.insertWithDel(l_params);

            FinInstitutionParams l_finInstitutionParams = this.getFinInstitutionRow();
            l_finInstitutionParams.setInstitutionCode("0D");
            l_finInstitutionParams.setFinInstitutionCode("000000000000000");
            TestDBUtility.insertWithDel(l_finInstitutionParams);

            AioOrderManagerImpl l_aioOrderManagerImpl = new AioOrderManagerImpl();
            AioOrderUnit l_aioOrderUnit =
                (AioOrderUnit)l_aioOrderManagerImpl.getOrderUnit(10);
            this.l_impl.createHostForeignCashoutOrderData(l_aioOrderUnit);

            String p_requestCode = "GI804";
            String p_institutionCode = "0D";
            String p_branchCode = "624";
            String p_accountCode = "321";
            String p_orderRequestNumber = "24";
            HostForeignCashTransOrderRow l_hostForeignCashTransOrderRow =
                (HostForeignCashTransOrderRow)HostForeignCashTransOrderDao.findRowByPk(
                    p_requestCode, p_institutionCode, p_branchCode, p_accountCode, p_orderRequestNumber);
            assertEquals("6", l_hostForeignCashTransOrderRow.getCashTransferType());
            assertEquals("61", l_hostForeignCashTransOrderRow.getSonarCode());
        }
        catch (Exception l_ex)
        {
            log.error("Error In Exception...", l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * 
     */
    public void testCreateHostForeignCashoutOrderData_case002()
    {
        final String STR_METHOD_NAME = "testCreateHostForeignCashoutOrderData_case002";
        log.exiting(STR_METHOD_NAME);

        try
        {
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountCode("321");
            l_mainAccountParams.setBranchId(222);
            l_mainAccountParams.setInstitutionId(123);
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setInstitutionCode("0D");
            //口座ＩＤ]
            l_subAccountParams.setAccountId(333812512203L);
            //補助口座ＩＤ
            l_subAccountParams.setSubAccountId(33381251220301L);
            //補助口座タイプ
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setBranchId(222);
            l_subAccountParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("624");
            l_branchParams.setBranchId(222);
            TestDBUtility.insertWithDel(l_branchParams);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);
            AioOrderUnitParams l_params = TestDBUtility.getAioOrderUnitRow();
            l_params.setOrderUnitId(10);
            l_params.setAccountId(333812512203L);
            l_params.setOrderRequestNumber("24");
            l_params.setCurrencyCode("10");
            TestDBUtility.insertWithDel(l_params);

            TestDBUtility.deleteAll(FinInstitutionRow.TYPE);

            AioOrderManagerImpl l_aioOrderManagerImpl = new AioOrderManagerImpl();
            AioOrderUnit l_aioOrderUnit =
                (AioOrderUnit)l_aioOrderManagerImpl.getOrderUnit(10);
            this.l_impl.createHostForeignCashoutOrderData(l_aioOrderUnit);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug("Error in WEB3BaseException..", l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("Error In Exception...", l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 金融機@関テーブル(fin_institution)
     */

    private static FinInstitutionParams getFinInstitutionRow()
    {
        FinInstitutionParams l_finInstitutionParams = new FinInstitutionParams();
       // 1    証券会社コード   institution_code   VARCHAR2   3   NotNull  
        l_finInstitutionParams.setInstitutionCode("0D");
       // 2    金融機@関コード   fin_institution_code   VARCHAR2   15   NotNull   "金融機@関コード　@AAAABBBCDDDDDDD（A:金融機@関,B:支店コード,C:預金科目,D:口座番号)"            
        l_finInstitutionParams.setFinInstitutionCode("000000000000000");
        // 3    金融機@関名（漢字）   fin_institution_name_kanji   VARCHAR2   50   NotNull   入金連絡画面表示用            
        l_finInstitutionParams.setFinInstitutionNameKanji("xxxxxxxxxxxx");
        // 4    金融機@関名（カナ）   fin_institution_name_kana   VARCHAR2   50   NotNull   確認用            
        l_finInstitutionParams.setFinInstitutionNameKana("cccccccccccccccc");
        // 5    入出金伝票キュー用入出金方法@   cash_transfer_type   VARCHAR2   1         ６：郵便振込 ７：銀行振込（NET） ９：銀行振込（普通）            
        l_finInstitutionParams.setCashTransferType("6");
        // 6    入出金伝票キュー用相手コード   cash_transfer_sonar_code   VARCHAR2   2         証券会社に確認中 
        l_finInstitutionParams.setCashTransferSonarCode("61");
        return l_finInstitutionParams;
    }

    public void testCreateTransferOrderQueueData_C0001()
    {
        final String STR_METHOD_NAME = "testCreateTransferOrderQueueData_C0001";
        log.exiting(STR_METHOD_NAME);        

        try
        {
            //スタティックメソッドの準備
            Calendar l_calendar =  Calendar.getInstance();
            l_calendar.set(2007, 6-1, 14);
            Timestamp l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsAppliyDate);
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setTradingTimeType("9");
            l_clendarContext.setMarketCode("sp");
            l_clendarContext.setProductCode("1");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext",
                l_clendarContext);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams =
                TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setTradingTimeType("9");
            l_tradingTimeParams.setMarketCode("sp");
            l_tradingTimeParams.setProductCode("1");
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            //DBにデータをインサート
            AioOrderUnitParams l_params = new AioOrderUnitParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(AioOrderUnitRow.TYPE);
            l_params = TestDBUtility.getAioOrderUnitRow();
            l_params.setOrderUnitId(10);
            l_params.setOrderType(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE);
            l_params.setMqStatus("0");
            l_params.setQuantity(5500);
            l_params.setOrderRequestNumber("123");
            l_params.setAccountId(333812512246L);
            l_params.setDeliveryDate(WEB3DateUtility.getDate("20090323", "yyyyMMdd"));
            l_params.setRemarkName("01");
            TestDBUtility.insertWithDel(l_params);

            MainAccountParams l_mainAccountParams = new MainAccountParams();
            l_processor.doDeleteAllQuery(MainAccountRow.TYPE);
            l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams l_institutionParams = new InstitutionParams();
            l_processor.doDeleteAllQuery(InstitutionRow.TYPE);
            l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33L);
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            BranchParams l_branchParams = new BranchParams();
            l_processor.doDeleteAllQuery(BranchRow.TYPE);
            l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("624");
            l_branchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_branchParams);

            CalendarParams l_calendarParams = new CalendarParams();
            l_processor.doDeleteAllQuery(CalendarRow.TYPE);
            l_calendar.set(2007, 6-1, 14);
            l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
            l_calendarParams.setHoliday(l_tsAppliyDate);
            l_calendarParams.setBizDateType("0");
            l_calendarParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_calendarParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_calendarParams);

            l_processor.doDeleteAllQuery(HostTransferOrderRow.TYPE);

            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                    WEB3DateUtility.getDate("20090321", "yyyyMMdd"));

            //実際のメソッドを実行
            AioOrderManagerImpl l_aioOrderManagerImpl = new AioOrderManagerImpl();
            AioOrderUnit l_aioOrderUnit = (AioOrderUnit)l_aioOrderManagerImpl.getOrderUnit(10);
            l_impl.createTransferOrderQueueData(l_aioOrderUnit);

            //比較
            List l_lisRows = l_processor.doFindAllQuery(HostTransferOrderRow.TYPE, null, null, null, null);
            HostTransferOrderRow l_hostTransferOrderRow = (HostTransferOrderRow)l_lisRows.get(0);
            assertEquals(l_hostTransferOrderRow.getTransferAmountDebitor(), 5500);
            assertEquals(l_hostTransferOrderRow.getTransferAmountCreditor(), 0);
            assertEquals(l_hostTransferOrderRow.getRemarkCode(), "01");
            assertEquals(l_hostTransferOrderRow.getRequestCode(), "GI806");
            assertEquals(l_hostTransferOrderRow.getInvoluntaryTransfer(), "9");
            assertEquals(l_hostTransferOrderRow.getInstitutionCode(), "0D");
            assertEquals(l_hostTransferOrderRow.getBranchCode(), "624");
            assertEquals(l_hostTransferOrderRow.getAccountCode(), "2512246");
            assertEquals(l_hostTransferOrderRow.getTraderCode(), "     ");
            assertEquals(l_hostTransferOrderRow.getRemarkName(), "          ");
            assertEquals(l_hostTransferOrderRow.getOriginalTransferDate(), "    ");
            assertEquals(l_hostTransferOrderRow.getCancelDiv(), " ");
            assertEquals(l_hostTransferOrderRow.getOrderRequestNumber(), "123");
            assertEquals(0, WEB3DateUtility.compareToDay(
                l_hostTransferOrderRow.getTransferDate(), WEB3DateUtility.getDate("20040716","yyyyMMdd")));
            assertEquals(0, WEB3DateUtility.compareToDay(
                l_hostTransferOrderRow.getTransferTime(), WEB3DateUtility.getDate("20040716060000","yyyyMMddHHmmss")));
            assertEquals(l_hostTransferOrderRow.getStatus(), "3");
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testCreateTransferOrderQueueData_C0002()
    {
        final String STR_METHOD_NAME = "testCreateTransferOrderQueueData_C0002()";
        log.exiting(STR_METHOD_NAME);        

        try
        {
            //スタティックメソッドの準備
            Calendar l_calendar =  Calendar.getInstance();
            l_calendar.set(2007, 6-1, 14);
            Timestamp l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsAppliyDate);
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setTradingTimeType("9");
            l_clendarContext.setMarketCode("sp");
            l_clendarContext.setProductCode("1");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext",
                l_clendarContext);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams =
                TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setTradingTimeType("9");
            l_tradingTimeParams.setMarketCode("sp");
            l_tradingTimeParams.setProductCode("1");
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            //DBにデータをインサート
            AioOrderUnitParams l_params = new AioOrderUnitParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(AioOrderUnitRow.TYPE);
            l_params = TestDBUtility.getAioOrderUnitRow();
            l_params.setOrderUnitId(10);
            l_params.setOrderType(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN);
            l_params.setMqStatus("0");
            l_params.setQuantity(100);
            l_params.setOrderRequestNumber("123");
            l_params.setAccountId(333812512246L);
            l_params.setRemarkCode("99");
            l_params.setRemarkName("0011");
            l_params.setTransferType(AssetTransferTypeEnum.CASH_IN);
            l_params.setDeliveryDate(WEB3DateUtility.getDate("20090323", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_params);

            MainAccountParams l_mainAccountParams = new MainAccountParams();
            l_processor.doDeleteAllQuery(MainAccountRow.TYPE);
            l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams l_institutionParams = new InstitutionParams();
            l_processor.doDeleteAllQuery(InstitutionRow.TYPE);
            l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33L);
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            BranchParams l_branchParams = new BranchParams();
            l_processor.doDeleteAllQuery(BranchRow.TYPE);
            l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("624");
            l_branchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_branchParams);

            CalendarParams l_calendarParams = new CalendarParams();
            l_processor.doDeleteAllQuery(CalendarRow.TYPE);
            l_calendar.set(2007, 6-1, 14);
            l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
            l_calendarParams.setHoliday(l_tsAppliyDate);
            l_calendarParams.setBizDateType("0");
            l_calendarParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_calendarParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_calendarParams);

            l_processor.doDeleteAllQuery(HostTransferOrderRow.TYPE);

            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                    WEB3DateUtility.getDate("20090321", "yyyyMMdd"));

            //実際のメソッドを実行
            AioOrderManagerImpl l_aioOrderManagerImpl = new AioOrderManagerImpl();
            AioOrderUnit l_aioOrderUnit = (AioOrderUnit)l_aioOrderManagerImpl.getOrderUnit(10);
            l_impl.createTransferOrderQueueData(l_aioOrderUnit);

            //比較
            List l_lisRows = l_processor.doFindAllQuery(HostTransferOrderRow.TYPE, null, null, null, null);
            HostTransferOrderRow l_hostTransferOrderRow = (HostTransferOrderRow)l_lisRows.get(0);
            assertEquals(l_hostTransferOrderRow.getTransferAmountDebitor(), 100);
            assertEquals(l_hostTransferOrderRow.getTransferAmountCreditor(), 0);
            assertEquals(l_hostTransferOrderRow.getRemarkCode(), "99");
            assertEquals(l_hostTransferOrderRow.getRequestCode(), "GI806");
            assertEquals(l_hostTransferOrderRow.getInvoluntaryTransfer(), "9");
            assertEquals(l_hostTransferOrderRow.getInstitutionCode(), "0D");
            assertEquals(l_hostTransferOrderRow.getBranchCode(), "624");
            assertEquals(l_hostTransferOrderRow.getAccountCode(), "2512246");
            assertEquals(l_hostTransferOrderRow.getTraderCode(), "     ");
            assertEquals(l_hostTransferOrderRow.getRemarkName(), "          ");
            assertEquals(l_hostTransferOrderRow.getCancelDiv(), " ");
            assertEquals(l_hostTransferOrderRow.getOrderRequestNumber(), "123");
            assertEquals("    ", l_hostTransferOrderRow.getOriginalTransferDate());
            assertEquals(0, WEB3DateUtility.compareToDay(
                l_hostTransferOrderRow.getTransferDate(), WEB3DateUtility.getDate("20040716","yyyyMMdd")));
            assertEquals(0, WEB3DateUtility.compareToDay(
                l_hostTransferOrderRow.getTransferTime(), WEB3DateUtility.getDate("20040716060000","yyyyMMddHHmmss")));
            assertEquals(l_hostTransferOrderRow.getStatus(), "3");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testCreateTransferOrderQueueData_C0003()
    {
        final String STR_METHOD_NAME = "testCreateTransferOrderQueueData_C0003()";
        log.exiting(STR_METHOD_NAME);        

        try
        {
            //スタティックメソッドの準備
            Calendar l_calendar =  Calendar.getInstance();
            l_calendar.set(2007, 6-1, 14);
            Timestamp l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsAppliyDate);
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setTradingTimeType("9");
            l_clendarContext.setMarketCode("sp");
            l_clendarContext.setProductCode("1");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext",
                l_clendarContext);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams =
                TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setTradingTimeType("9");
            l_tradingTimeParams.setMarketCode("sp");
            l_tradingTimeParams.setProductCode("1");
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            //DBにデータをインサート
            AioOrderUnitParams l_params = new AioOrderUnitParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(AioOrderUnitRow.TYPE);
            l_params = TestDBUtility.getAioOrderUnitRow();
            l_params.setOrderUnitId(10);
            l_params.setOrderType(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN);
            l_params.setMqStatus("0");
            l_params.setQuantity(100);
            l_params.setOrderRequestNumber("123");
            l_params.setAccountId(333812512246L);
            l_params.setRemarkCode(null);
            l_params.setRemarkName("0012");
            l_params.setTransferType(AssetTransferTypeEnum.CASH_IN);
            TestDBUtility.insertWithDel(l_params);

            MainAccountParams l_mainAccountParams = new MainAccountParams();
            l_processor.doDeleteAllQuery(MainAccountRow.TYPE);
            l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams l_institutionParams = new InstitutionParams();
            l_processor.doDeleteAllQuery(InstitutionRow.TYPE);
            l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33L);
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            BranchParams l_branchParams = new BranchParams();
            l_processor.doDeleteAllQuery(BranchRow.TYPE);
            l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("624");
            l_branchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_branchParams);

            CalendarParams l_calendarParams = new CalendarParams();
            l_processor.doDeleteAllQuery(CalendarRow.TYPE);
            l_calendar.set(2007, 6-1, 14);
            l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
            l_calendarParams.setHoliday(l_tsAppliyDate);
            l_calendarParams.setBizDateType("0");
            l_calendarParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_calendarParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_calendarParams);

            l_processor.doDeleteAllQuery(HostTransferOrderRow.TYPE);
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                    WEB3DateUtility.getDate("20090321", "yyyyMMdd"));

            //実際のメソッドを実行
            AioOrderManagerImpl l_aioOrderManagerImpl = new AioOrderManagerImpl();
            AioOrderUnit l_aioOrderUnit = (AioOrderUnit)l_aioOrderManagerImpl.getOrderUnit(10);
            l_impl.createTransferOrderQueueData(l_aioOrderUnit);

            //比較
            List l_lisRows = l_processor.doFindAllQuery(HostTransferOrderRow.TYPE, null, null, null, null);
            HostTransferOrderRow l_hostTransferOrderRow = (HostTransferOrderRow)l_lisRows.get(0);
            assertEquals(l_hostTransferOrderRow.getTransferAmountDebitor(), 100);
            assertEquals(l_hostTransferOrderRow.getTransferAmountCreditor(), 0);
            assertEquals(l_hostTransferOrderRow.getRemarkCode(), "72");
            assertEquals(l_hostTransferOrderRow.getRequestCode(), "GI806");
            assertEquals(l_hostTransferOrderRow.getInstitutionCode(), "0D");
            assertEquals(l_hostTransferOrderRow.getBranchCode(), "624");
            assertEquals(l_hostTransferOrderRow.getAccountCode(), "2512246");
            assertEquals(l_hostTransferOrderRow.getTraderCode(), "     ");
            assertEquals(l_hostTransferOrderRow.getRemarkName(), "          ");
            assertEquals("9", l_hostTransferOrderRow.getInvoluntaryTransfer());
            assertEquals(l_hostTransferOrderRow.getCancelDiv(), " ");
            assertEquals("    ", l_hostTransferOrderRow.getOriginalTransferDate());
            assertEquals(l_hostTransferOrderRow.getOrderRequestNumber(), "123");
            assertEquals(0, WEB3DateUtility.compareToDay(
                l_hostTransferOrderRow.getTransferDate(), WEB3DateUtility.getDate("20040716","yyyyMMdd")));
            assertEquals(0, WEB3DateUtility.compareToDay(
                l_hostTransferOrderRow.getTransferTime(), WEB3DateUtility.getDate("20040716060000","yyyyMMddHHmmss")));
            assertEquals(l_hostTransferOrderRow.getStatus(), "3");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testCreateTransferOrderQueueData_C0004()
    {
        final String STR_METHOD_NAME = "testCreateTransferOrderQueueData_C0004()";
        log.exiting(STR_METHOD_NAME);        

        try
        {
            //スタティックメソッドの準備
            Calendar l_calendar =  Calendar.getInstance();
            l_calendar.set(2007, 6-1, 14);
            Timestamp l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsAppliyDate);
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setTradingTimeType("9");
            l_clendarContext.setMarketCode("sp");
            l_clendarContext.setProductCode("1");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext",
                l_clendarContext);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams =
                TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setTradingTimeType("9");
            l_tradingTimeParams.setMarketCode("sp");
            l_tradingTimeParams.setProductCode("1");
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            //DBにデータをインサート
            AioOrderUnitParams l_params = new AioOrderUnitParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(AioOrderUnitRow.TYPE);
            l_params = TestDBUtility.getAioOrderUnitRow();
            l_params.setOrderUnitId(10);
            l_params.setOrderType(OrderTypeEnum.MARGIN_FROM_DEPOSIT_AMOUNT);
            l_params.setMqStatus("0");
            l_params.setQuantity(100);
            l_params.setOrderRequestNumber("123");
            l_params.setAccountId(333812512246L);
            l_params.setRemarkCode("99");
            l_params.setRemarkName("0014");
            l_params.setTransferType(AssetTransferTypeEnum.CASH_OUT);
            TestDBUtility.insertWithDel(l_params);

            MainAccountParams l_mainAccountParams = new MainAccountParams();
            l_processor.doDeleteAllQuery(MainAccountRow.TYPE);
            l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams l_institutionParams = new InstitutionParams();
            l_processor.doDeleteAllQuery(InstitutionRow.TYPE);
            l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33L);
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            BranchParams l_branchParams = new BranchParams();
            l_processor.doDeleteAllQuery(BranchRow.TYPE);
            l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("624");
            l_branchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_branchParams);

            CalendarParams l_calendarParams = new CalendarParams();
            l_processor.doDeleteAllQuery(CalendarRow.TYPE);
            l_calendar.set(2007, 6-1, 14);
            l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
            l_calendarParams.setHoliday(l_tsAppliyDate);
            l_calendarParams.setBizDateType("0");
            l_calendarParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_calendarParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_calendarParams);

            l_processor.doDeleteAllQuery(HostTransferOrderRow.TYPE);

            l_processor.doDeleteAllQuery(HostTransferOrderRow.TYPE);
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                    WEB3DateUtility.getDate("20090321", "yyyyMMdd"));

            //実際のメソッドを実行
            AioOrderManagerImpl l_aioOrderManagerImpl = new AioOrderManagerImpl();
            AioOrderUnit l_aioOrderUnit = (AioOrderUnit)l_aioOrderManagerImpl.getOrderUnit(10);
            l_impl.createTransferOrderQueueData(l_aioOrderUnit);

            //比較
            List l_lisRows = l_processor.doFindAllQuery(HostTransferOrderRow.TYPE, null, null, null, null);
            HostTransferOrderRow l_hostTransferOrderRow = (HostTransferOrderRow)l_lisRows.get(0);
            assertEquals(l_hostTransferOrderRow.getTransferAmountDebitor(), 0);
            assertEquals(l_hostTransferOrderRow.getTransferAmountCreditor(), 100);
            assertEquals(l_hostTransferOrderRow.getRemarkCode(), "99");
            assertEquals(l_hostTransferOrderRow.getRequestCode(), "GI806");
            assertEquals(l_hostTransferOrderRow.getInstitutionCode(), "0D");
            assertEquals(l_hostTransferOrderRow.getBranchCode(), "624");
            assertEquals(l_hostTransferOrderRow.getAccountCode(), "2512246");
            assertEquals(l_hostTransferOrderRow.getTraderCode(), "     ");
            assertEquals(l_hostTransferOrderRow.getRemarkName(), "          ");
            assertEquals(l_hostTransferOrderRow.getCancelDiv(), " ");
            assertEquals("9", l_hostTransferOrderRow.getInvoluntaryTransfer());
            assertEquals(l_hostTransferOrderRow.getOrderRequestNumber(), "123");
            assertEquals(0, WEB3DateUtility.compareToDay(
                l_hostTransferOrderRow.getTransferDate(), WEB3DateUtility.getDate("20040716","yyyyMMdd")));
            assertEquals(0, WEB3DateUtility.compareToDay(
                l_hostTransferOrderRow.getTransferTime(), WEB3DateUtility.getDate("20040716060000","yyyyMMddHHmmss")));
            assertEquals(l_hostTransferOrderRow.getStatus(), "3");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testCreateTransferOrderQueueData_C0005()
    {
        final String STR_METHOD_NAME = "testCreateTransferOrderQueueData_C0005()";
        log.exiting(STR_METHOD_NAME);        

        try
        {
            //スタティックメソッドの準備
            Calendar l_calendar =  Calendar.getInstance();
            l_calendar.set(2007, 6-1, 14);
            Timestamp l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsAppliyDate);
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setTradingTimeType("9");
            l_clendarContext.setMarketCode("sp");
            l_clendarContext.setProductCode("1");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext",
                l_clendarContext);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams =
                TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setTradingTimeType("9");
            l_tradingTimeParams.setMarketCode("sp");
            l_tradingTimeParams.setProductCode("1");
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            //DBにデータをインサート
            AioOrderUnitParams l_params = new AioOrderUnitParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(AioOrderUnitRow.TYPE);
            l_params = TestDBUtility.getAioOrderUnitRow();
            l_params.setOrderUnitId(10);
            l_params.setOrderType(OrderTypeEnum.MARGIN_FROM_DEPOSIT_AMOUNT);
            l_params.setMqStatus("0");
            l_params.setQuantity(100);
            l_params.setOrderRequestNumber("123");
            l_params.setAccountId(333812512246L);
            l_params.setRemarkCode(null);
            l_params.setRemarkName("0014");
            l_params.setTransferType(AssetTransferTypeEnum.CASH_OUT);
            TestDBUtility.insertWithDel(l_params);

            MainAccountParams l_mainAccountParams = new MainAccountParams();
            l_processor.doDeleteAllQuery(MainAccountRow.TYPE);
            l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams l_institutionParams = new InstitutionParams();
            l_processor.doDeleteAllQuery(InstitutionRow.TYPE);
            l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33L);
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            BranchParams l_branchParams = new BranchParams();
            l_processor.doDeleteAllQuery(BranchRow.TYPE);
            l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("624");
            l_branchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_branchParams);

            CalendarParams l_calendarParams = new CalendarParams();
            l_processor.doDeleteAllQuery(CalendarRow.TYPE);
            l_calendar.set(2007, 6-1, 14);
            l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
            l_calendarParams.setHoliday(l_tsAppliyDate);
            l_calendarParams.setBizDateType("0");
            l_calendarParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_calendarParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_calendarParams);

            l_processor.doDeleteAllQuery(HostTransferOrderRow.TYPE);

            l_processor.doDeleteAllQuery(HostTransferOrderRow.TYPE);
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                    WEB3DateUtility.getDate("20090321", "yyyyMMdd"));

            //実際のメソッドを実行
            AioOrderManagerImpl l_aioOrderManagerImpl = new AioOrderManagerImpl();
            AioOrderUnit l_aioOrderUnit = (AioOrderUnit)l_aioOrderManagerImpl.getOrderUnit(10);
            l_impl.createTransferOrderQueueData(l_aioOrderUnit);

            //比較
            List l_lisRows = l_processor.doFindAllQuery(HostTransferOrderRow.TYPE, null, null, null, null);
            HostTransferOrderRow l_hostTransferOrderRow = (HostTransferOrderRow)l_lisRows.get(0);
            assertEquals(l_hostTransferOrderRow.getTransferAmountDebitor(), 0);
            assertEquals(l_hostTransferOrderRow.getTransferAmountCreditor(), 100);
            assertEquals(l_hostTransferOrderRow.getRemarkCode(), "72");
            assertEquals(l_hostTransferOrderRow.getRequestCode(), "GI806");
            assertEquals(l_hostTransferOrderRow.getInstitutionCode(), "0D");
            assertEquals(l_hostTransferOrderRow.getBranchCode(), "624");
            assertEquals(l_hostTransferOrderRow.getAccountCode(), "2512246");
            assertEquals(l_hostTransferOrderRow.getTraderCode(), "     ");
            assertEquals(l_hostTransferOrderRow.getRemarkName(), "          ");
            assertEquals(l_hostTransferOrderRow.getCancelDiv(), " ");
            assertEquals("9", l_hostTransferOrderRow.getInvoluntaryTransfer());
            assertEquals(l_hostTransferOrderRow.getOrderRequestNumber(), "123");
            assertEquals(0, WEB3DateUtility.compareToDay(
                l_hostTransferOrderRow.getTransferDate(), WEB3DateUtility.getDate("20040716","yyyyMMdd")));
            assertEquals(0, WEB3DateUtility.compareToDay(
                l_hostTransferOrderRow.getTransferTime(), WEB3DateUtility.getDate("20040716060000","yyyyMMddHHmmss")));
            assertEquals(l_hostTransferOrderRow.getStatus(), "3");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testCreateTransferOrderQueueData_C0006()
    {
        final String STR_METHOD_NAME = "testCreateTransferOrderQueueData_C0006()";
        log.exiting(STR_METHOD_NAME);        

        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            //スタティックメソッドの準備
            Calendar l_calendar =  Calendar.getInstance();
            l_calendar.set(2007, 6-1, 14);
            Timestamp l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsAppliyDate);
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setTradingTimeType("9");
            l_clendarContext.setMarketCode("sp");
            l_clendarContext.setProductCode("1");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext",
                l_clendarContext);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams =
                TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setTradingTimeType("9");
            l_tradingTimeParams.setMarketCode("sp");
            l_tradingTimeParams.setProductCode("1");
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            //DBにデータをインサート
            AioOrderUnitParams l_params = new AioOrderUnitParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(AioOrderUnitRow.TYPE);
            l_params = TestDBUtility.getAioOrderUnitRow();
            l_params.setOrderUnitId(10);
            l_params.setOrderType(OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT);
            l_params.setMqStatus("0");
            l_params.setQuantity(100);
            l_params.setOrderRequestNumber("123");
            l_params.setAccountId(333812512246L);
            l_params.setRemarkCode("41");
            l_params.setRemarkName(null);
            l_params.setTransferType(AssetTransferTypeEnum.CASH_OUT);
            l_params.setDeliveryDate(WEB3DateUtility.getDate("20090323", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_params);

            MainAccountParams l_mainAccountParams = new MainAccountParams();
            l_processor.doDeleteAllQuery(MainAccountRow.TYPE);
            l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams l_institutionParams = new InstitutionParams();
            l_processor.doDeleteAllQuery(InstitutionRow.TYPE);
            l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33L);
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            BranchParams l_branchParams = new BranchParams();
            l_processor.doDeleteAllQuery(BranchRow.TYPE);
            l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("624");
            l_branchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_branchParams);

            CalendarParams l_calendarParams = new CalendarParams();
            l_processor.doDeleteAllQuery(CalendarRow.TYPE);
            l_calendar.set(2007, 6-1, 14);
            l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
            l_calendarParams.setHoliday(l_tsAppliyDate);
            l_calendarParams.setBizDateType("0");
            l_calendarParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_calendarParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_calendarParams);

            l_processor.doDeleteAllQuery(HostTransferOrderRow.TYPE);

            l_processor.doDeleteAllQuery(HostTransferOrderRow.TYPE);
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                    WEB3DateUtility.getDate("20090321", "yyyyMMdd"));

            //実際のメソッドを実行
            AioOrderManagerImpl l_aioOrderManagerImpl = new AioOrderManagerImpl();
            AioOrderUnit l_aioOrderUnit = (AioOrderUnit)l_aioOrderManagerImpl.getOrderUnit(10);
            l_impl.createTransferOrderQueueData(l_aioOrderUnit);

            //比較
            List l_lisRows = l_processor.doFindAllQuery(HostTransferOrderRow.TYPE, null, null, null, null);
            HostTransferOrderRow l_hostTransferOrderRow = (HostTransferOrderRow)l_lisRows.get(0);
            assertEquals(l_hostTransferOrderRow.getTransferAmountDebitor(), 100);
            assertEquals(l_hostTransferOrderRow.getTransferAmountCreditor(), 0);
            assertEquals(l_hostTransferOrderRow.getRemarkCode(), "41");
            assertEquals(l_hostTransferOrderRow.getRequestCode(), "GI806");
            assertEquals(l_hostTransferOrderRow.getInstitutionCode(), "0D");
            assertEquals(l_hostTransferOrderRow.getBranchCode(), "624");
            assertEquals(l_hostTransferOrderRow.getAccountCode(), "2512246");
            assertEquals(l_hostTransferOrderRow.getTraderCode(), "     ");
            assertEquals(l_hostTransferOrderRow.getRemarkName(), "          ");
            assertEquals(l_hostTransferOrderRow.getCancelDiv(), " ");
            assertEquals("9", l_hostTransferOrderRow.getInvoluntaryTransfer());
            assertEquals("    ", l_hostTransferOrderRow.getOriginalTransferDate());
            assertEquals(l_hostTransferOrderRow.getOrderRequestNumber(), "123");
            assertEquals(0, WEB3DateUtility.compareToDay(
                l_hostTransferOrderRow.getTransferDate(), WEB3DateUtility.getDate("20040716","yyyyMMdd")));
            assertEquals(0, WEB3DateUtility.compareToDay(
                l_hostTransferOrderRow.getTransferTime(), WEB3DateUtility.getDate("20040716060000","yyyyMMddHHmmss")));
            assertEquals(l_hostTransferOrderRow.getStatus(), "3");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testCreateTransferOrderQueueData_C0007()
    {
        final String STR_METHOD_NAME = "testCreateTransferOrderQueueData_C0007()";
        log.exiting(STR_METHOD_NAME);        

        try
        {
            //スタティックメソッドの準備
            Calendar l_calendar =  Calendar.getInstance();
            l_calendar.set(2007, 6-1, 14);
            Timestamp l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsAppliyDate);
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setTradingTimeType("9");
            l_clendarContext.setMarketCode("sp");
            l_clendarContext.setProductCode("1");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext",
                l_clendarContext);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams =
                TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setTradingTimeType("9");
            l_tradingTimeParams.setMarketCode("sp");
            l_tradingTimeParams.setProductCode("1");
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            //DBにデータをインサート
            AioOrderUnitParams l_params = new AioOrderUnitParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(AioOrderUnitRow.TYPE);
            l_params = TestDBUtility.getAioOrderUnitRow();
            l_params.setOrderUnitId(10);
            l_params.setOrderType(OrderTypeEnum.CFD_FROM_DEPOSIT_AMOUNT);
            l_params.setMqStatus("0");
            l_params.setQuantity(100);
            l_params.setOrderRequestNumber("123");
            l_params.setAccountId(333812512246L);
            l_params.setRemarkCode("41");
            l_params.setRemarkName(null);
            l_params.setTransferType(AssetTransferTypeEnum.CASH_OUT);
            TestDBUtility.insertWithDel(l_params);

            MainAccountParams l_mainAccountParams = new MainAccountParams();
            l_processor.doDeleteAllQuery(MainAccountRow.TYPE);
            l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams l_institutionParams = new InstitutionParams();
            l_processor.doDeleteAllQuery(InstitutionRow.TYPE);
            l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33L);
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            BranchParams l_branchParams = new BranchParams();
            l_processor.doDeleteAllQuery(BranchRow.TYPE);
            l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("624");
            l_branchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_branchParams);

            CalendarParams l_calendarParams = new CalendarParams();
            l_processor.doDeleteAllQuery(CalendarRow.TYPE);
            l_calendar.set(2007, 6-1, 14);
            l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
            l_calendarParams.setHoliday(l_tsAppliyDate);
            l_calendarParams.setBizDateType("0");
            l_calendarParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_calendarParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_calendarParams);

            l_processor.doDeleteAllQuery(HostTransferOrderRow.TYPE);

            l_processor.doDeleteAllQuery(HostTransferOrderRow.TYPE);
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                    WEB3DateUtility.getDate("20090321", "yyyyMMdd"));

            //実際のメソッドを実行
            AioOrderManagerImpl l_aioOrderManagerImpl = new AioOrderManagerImpl();
            AioOrderUnit l_aioOrderUnit = (AioOrderUnit)l_aioOrderManagerImpl.getOrderUnit(10);
            l_impl.createTransferOrderQueueData(l_aioOrderUnit);

            //比較
            List l_lisRows = l_processor.doFindAllQuery(HostTransferOrderRow.TYPE, null, null, null, null);
            HostTransferOrderRow l_hostTransferOrderRow = (HostTransferOrderRow)l_lisRows.get(0);
            assertEquals(l_hostTransferOrderRow.getTransferAmountDebitor(), 100);
            assertEquals(l_hostTransferOrderRow.getTransferAmountCreditor(), 0);
            assertEquals(l_hostTransferOrderRow.getRemarkCode(), "41");
            assertEquals(l_hostTransferOrderRow.getRequestCode(), "GI806");
            assertEquals(l_hostTransferOrderRow.getInstitutionCode(), "0D");
            assertEquals(l_hostTransferOrderRow.getBranchCode(), "624");
            assertEquals(l_hostTransferOrderRow.getAccountCode(), "2512246");
            assertEquals(l_hostTransferOrderRow.getTraderCode(), "     ");
            assertEquals(l_hostTransferOrderRow.getRemarkName(), "          ");
            assertEquals(l_hostTransferOrderRow.getCancelDiv(), " ");
            assertEquals("9", l_hostTransferOrderRow.getInvoluntaryTransfer());
            assertEquals("0716", l_hostTransferOrderRow.getOriginalTransferDate());
            assertEquals(l_hostTransferOrderRow.getOrderRequestNumber(), "123");
            assertEquals(0, WEB3DateUtility.compareToDay(
                l_hostTransferOrderRow.getTransferDate(), WEB3DateUtility.getDate("20040716","yyyyMMdd")));
            assertEquals(0, WEB3DateUtility.compareToDay(
                l_hostTransferOrderRow.getTransferTime(), WEB3DateUtility.getDate("20040716060000","yyyyMMddHHmmss")));
            assertEquals(l_hostTransferOrderRow.getStatus(), "3");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testCreateTransferOrderQueueData_C0008()
    {
        final String STR_METHOD_NAME = "testCreateTransferOrderQueueData_C0008()";
        log.exiting(STR_METHOD_NAME);        

        try
        {
            //スタティックメソッドの準備
            Calendar l_calendar =  Calendar.getInstance();
            l_calendar.set(2007, 6-1, 14);
            Timestamp l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsAppliyDate);
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setTradingTimeType("9");
            l_clendarContext.setMarketCode("sp");
            l_clendarContext.setProductCode("1");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext",
                l_clendarContext);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams =
                TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setTradingTimeType("9");
            l_tradingTimeParams.setMarketCode("sp");
            l_tradingTimeParams.setProductCode("1");
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            //DBにデータをインサート
            AioOrderUnitParams l_params = new AioOrderUnitParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(AioOrderUnitRow.TYPE);
            l_params = TestDBUtility.getAioOrderUnitRow();
            l_params.setOrderUnitId(10);
            l_params.setOrderType(OrderTypeEnum.CFD_FROM_DEPOSIT_AMOUNT);
            l_params.setMqStatus("0");
            l_params.setQuantity(100);
            l_params.setOrderRequestNumber("123");
            l_params.setAccountId(333812512246L);
            l_params.setRemarkCode("99");
            l_params.setRemarkName("101");
            l_params.setTransferType(AssetTransferTypeEnum.CASH_IN);
            l_params.setDeliveryDate(WEB3DateUtility.getDate("20090323", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_params);

            MainAccountParams l_mainAccountParams = new MainAccountParams();
            l_processor.doDeleteAllQuery(MainAccountRow.TYPE);
            l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams l_institutionParams = new InstitutionParams();
            l_processor.doDeleteAllQuery(InstitutionRow.TYPE);
            l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33L);
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            BranchParams l_branchParams = new BranchParams();
            l_processor.doDeleteAllQuery(BranchRow.TYPE);
            l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("624");
            l_branchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_branchParams);

            CalendarParams l_calendarParams = new CalendarParams();
            l_processor.doDeleteAllQuery(CalendarRow.TYPE);
            l_calendar.set(2007, 6-1, 14);
            l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
            l_calendarParams.setHoliday(l_tsAppliyDate);
            l_calendarParams.setBizDateType("0");
            l_calendarParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_calendarParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_calendarParams);

            l_processor.doDeleteAllQuery(HostTransferOrderRow.TYPE);

            l_processor.doDeleteAllQuery(HostTransferOrderRow.TYPE);
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                    WEB3DateUtility.getDate("20090321", "yyyyMMdd"));

            //実際のメソッドを実行
            AioOrderManagerImpl l_aioOrderManagerImpl = new AioOrderManagerImpl();
            AioOrderUnit l_aioOrderUnit = (AioOrderUnit)l_aioOrderManagerImpl.getOrderUnit(10);
            l_impl.createTransferOrderQueueData(l_aioOrderUnit);

            //比較
            List l_lisRows = l_processor.doFindAllQuery(HostTransferOrderRow.TYPE, null, null, null, null);
            HostTransferOrderRow l_hostTransferOrderRow = (HostTransferOrderRow)l_lisRows.get(0);
            assertEquals(l_hostTransferOrderRow.getTransferAmountDebitor(), 100);
            assertEquals(l_hostTransferOrderRow.getTransferAmountCreditor(), 0);
            assertEquals(l_hostTransferOrderRow.getRemarkCode(), "99");
            assertEquals(l_hostTransferOrderRow.getRequestCode(), "GI806");
            assertEquals(l_hostTransferOrderRow.getInstitutionCode(), "0D");
            assertEquals(l_hostTransferOrderRow.getBranchCode(), "624");
            assertEquals(l_hostTransferOrderRow.getAccountCode(), "2512246");
            assertEquals(l_hostTransferOrderRow.getTraderCode(), "     ");
            assertEquals(l_hostTransferOrderRow.getRemarkName(), "101");
            assertEquals(l_hostTransferOrderRow.getCancelDiv(), " ");
            assertEquals(" ", l_hostTransferOrderRow.getInvoluntaryTransfer());
            assertEquals("    ", l_hostTransferOrderRow.getOriginalTransferDate());
            assertEquals(l_hostTransferOrderRow.getOrderRequestNumber(), "123");
            assertEquals(0, WEB3DateUtility.compareToDay(
                l_hostTransferOrderRow.getTransferDate(), WEB3DateUtility.getDate("20040716","yyyyMMdd")));
            assertEquals(0, WEB3DateUtility.compareToDay(
                l_hostTransferOrderRow.getTransferTime(), WEB3DateUtility.getDate("20040716060000","yyyyMMddHHmmss")));
            assertEquals(l_hostTransferOrderRow.getStatus(), "3");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testCreateTransferOrderQueueData_C0009()
    {
        final String STR_METHOD_NAME = "testCreateTransferOrderQueueData_C0009()";
        log.exiting(STR_METHOD_NAME);        

        try
        {
            //スタティックメソッドの準備
            Calendar l_calendar =  Calendar.getInstance();
            l_calendar.set(2007, 6-1, 14);
            Timestamp l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsAppliyDate);
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setTradingTimeType("9");
            l_clendarContext.setMarketCode("sp");
            l_clendarContext.setProductCode("1");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext",
                l_clendarContext);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams =
                TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setTradingTimeType("9");
            l_tradingTimeParams.setMarketCode("sp");
            l_tradingTimeParams.setProductCode("1");
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            //DBにデータをインサート
            AioOrderUnitParams l_params = new AioOrderUnitParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(AioOrderUnitRow.TYPE);
            l_params = TestDBUtility.getAioOrderUnitRow();
            l_params.setOrderUnitId(10);
            l_params.setOrderType(OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE);
            l_params.setMqStatus("0");
            l_params.setQuantity(100);
            l_params.setOrderRequestNumber("123");
            l_params.setAccountId(333812512246L);
            l_params.setRemarkCode("41");
            l_params.setRemarkName(null);
            l_params.setTransferType(AssetTransferTypeEnum.CASH_OUT);
            TestDBUtility.insertWithDel(l_params);

            MainAccountParams l_mainAccountParams = new MainAccountParams();
            l_processor.doDeleteAllQuery(MainAccountRow.TYPE);
            l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams l_institutionParams = new InstitutionParams();
            l_processor.doDeleteAllQuery(InstitutionRow.TYPE);
            l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33L);
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            BranchParams l_branchParams = new BranchParams();
            l_processor.doDeleteAllQuery(BranchRow.TYPE);
            l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("624");
            l_branchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_branchParams);

            CalendarParams l_calendarParams = new CalendarParams();
            l_processor.doDeleteAllQuery(CalendarRow.TYPE);
            l_calendar.set(2007, 6-1, 14);
            l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
            l_calendarParams.setHoliday(l_tsAppliyDate);
            l_calendarParams.setBizDateType("0");
            l_calendarParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_calendarParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_calendarParams);

            l_processor.doDeleteAllQuery(HostTransferOrderRow.TYPE);

            l_processor.doDeleteAllQuery(HostTransferOrderRow.TYPE);
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                    WEB3DateUtility.getDate("20090321", "yyyyMMdd"));

            //実際のメソッドを実行
            AioOrderManagerImpl l_aioOrderManagerImpl = new AioOrderManagerImpl();
            AioOrderUnit l_aioOrderUnit = (AioOrderUnit)l_aioOrderManagerImpl.getOrderUnit(10);
            l_impl.createTransferOrderQueueData(l_aioOrderUnit);

            //比較
            List l_lisRows = l_processor.doFindAllQuery(HostTransferOrderRow.TYPE, null, null, null, null);
            HostTransferOrderRow l_hostTransferOrderRow = (HostTransferOrderRow)l_lisRows.get(0);
            assertEquals(l_hostTransferOrderRow.getTransferAmountDebitor(), 0);
            assertEquals(l_hostTransferOrderRow.getTransferAmountCreditor(), 100);
            assertEquals(l_hostTransferOrderRow.getRemarkCode(), "41");
            assertEquals(l_hostTransferOrderRow.getRequestCode(), "GI806");
            assertEquals(l_hostTransferOrderRow.getInstitutionCode(), "0D");
            assertEquals(l_hostTransferOrderRow.getBranchCode(), "624");
            assertEquals(l_hostTransferOrderRow.getAccountCode(), "2512246");
            assertEquals(l_hostTransferOrderRow.getTraderCode(), "     ");
            assertEquals(l_hostTransferOrderRow.getRemarkName(), "          ");
            assertEquals(l_hostTransferOrderRow.getCancelDiv(), " ");
            assertEquals("9", l_hostTransferOrderRow.getInvoluntaryTransfer());
            assertEquals("0716", l_hostTransferOrderRow.getOriginalTransferDate());
            assertEquals(l_hostTransferOrderRow.getOrderRequestNumber(), "123");
            assertEquals(0, WEB3DateUtility.compareToDay(
                l_hostTransferOrderRow.getTransferDate(), WEB3DateUtility.getDate("20040716","yyyyMMdd")));
            assertEquals(0, WEB3DateUtility.compareToDay(
                l_hostTransferOrderRow.getTransferTime(), WEB3DateUtility.getDate("20040716060000","yyyyMMddHHmmss")));
            assertEquals(l_hostTransferOrderRow.getStatus(), "3");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testCreateTransferOrderQueueData_C0010()
    {
        final String STR_METHOD_NAME = "testCreateTransferOrderQueueData_C0010()";
        log.exiting(STR_METHOD_NAME);        

        try
        {
            //スタティックメソッドの準備
            Calendar l_calendar =  Calendar.getInstance();
            l_calendar.set(2007, 6-1, 14);
            Timestamp l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsAppliyDate);
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setTradingTimeType("9");
            l_clendarContext.setMarketCode("sp");
            l_clendarContext.setProductCode("1");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext",
                l_clendarContext);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams =
                TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setTradingTimeType("9");
            l_tradingTimeParams.setMarketCode("sp");
            l_tradingTimeParams.setProductCode("1");
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            //DBにデータをインサート
            AioOrderUnitParams l_params = new AioOrderUnitParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(AioOrderUnitRow.TYPE);
            l_params = TestDBUtility.getAioOrderUnitRow();
            l_params.setOrderUnitId(10);
            l_params.setOrderType(OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE);
            l_params.setMqStatus("0");
            l_params.setQuantity(100);
            l_params.setOrderRequestNumber("123");
            l_params.setAccountId(333812512246L);
            l_params.setRemarkCode("99");
            l_params.setRemarkName("101");
            l_params.setDeliveryDate(WEB3DateUtility.getDate("20090323", "yyyyMMdd"));
            l_params.setTransferType(AssetTransferTypeEnum.CASH_IN);
            TestDBUtility.insertWithDel(l_params);

            MainAccountParams l_mainAccountParams = new MainAccountParams();
            l_processor.doDeleteAllQuery(MainAccountRow.TYPE);
            l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams l_institutionParams = new InstitutionParams();
            l_processor.doDeleteAllQuery(InstitutionRow.TYPE);
            l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33L);
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            BranchParams l_branchParams = new BranchParams();
            l_processor.doDeleteAllQuery(BranchRow.TYPE);
            l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("624");
            l_branchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_branchParams);

            CalendarParams l_calendarParams = new CalendarParams();
            l_processor.doDeleteAllQuery(CalendarRow.TYPE);
            l_calendar.set(2007, 6-1, 14);
            l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
            l_calendarParams.setHoliday(l_tsAppliyDate);
            l_calendarParams.setBizDateType("0");
            l_calendarParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_calendarParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_calendarParams);

            l_processor.doDeleteAllQuery(HostTransferOrderRow.TYPE);

            l_processor.doDeleteAllQuery(HostTransferOrderRow.TYPE);
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                    WEB3DateUtility.getDate("20090321", "yyyyMMdd"));

            //実際のメソッドを実行
            AioOrderManagerImpl l_aioOrderManagerImpl = new AioOrderManagerImpl();
            AioOrderUnit l_aioOrderUnit = (AioOrderUnit)l_aioOrderManagerImpl.getOrderUnit(10);
            l_impl.createTransferOrderQueueData(l_aioOrderUnit);

            //比較
            List l_lisRows = l_processor.doFindAllQuery(HostTransferOrderRow.TYPE, null, null, null, null);
            HostTransferOrderRow l_hostTransferOrderRow = (HostTransferOrderRow)l_lisRows.get(0);
            assertEquals(l_hostTransferOrderRow.getTransferAmountDebitor(), 0);
            assertEquals(l_hostTransferOrderRow.getTransferAmountCreditor(), 100);
            assertEquals(l_hostTransferOrderRow.getRemarkCode(), "99");
            assertEquals(l_hostTransferOrderRow.getRequestCode(), "GI806");
            assertEquals(l_hostTransferOrderRow.getInstitutionCode(), "0D");
            assertEquals(l_hostTransferOrderRow.getBranchCode(), "624");
            assertEquals(l_hostTransferOrderRow.getAccountCode(), "2512246");
            assertEquals(l_hostTransferOrderRow.getTraderCode(), "     ");
            assertEquals(l_hostTransferOrderRow.getRemarkName(), "101");
            assertEquals(l_hostTransferOrderRow.getCancelDiv(), " ");
            assertEquals(" ", l_hostTransferOrderRow.getInvoluntaryTransfer());
            assertEquals("    ", l_hostTransferOrderRow.getOriginalTransferDate());
            assertEquals(l_hostTransferOrderRow.getOrderRequestNumber(), "123");
            assertEquals(0, WEB3DateUtility.compareToDay(
                l_hostTransferOrderRow.getTransferDate(), WEB3DateUtility.getDate("20040716","yyyyMMdd")));
            assertEquals(0, WEB3DateUtility.compareToDay(
                l_hostTransferOrderRow.getTransferTime(), WEB3DateUtility.getDate("20040716060000","yyyyMMddHHmmss")));
            assertEquals(l_hostTransferOrderRow.getStatus(), "3");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testCreateTransferOrderQueueData_C0011()
    {
        final String STR_METHOD_NAME = "testCreateTransferOrderQueueData_C0011()";
        log.exiting(STR_METHOD_NAME);        

        try
        {
            //スタティックメソッドの準備
            Calendar l_calendar =  Calendar.getInstance();
            l_calendar.set(2007, 6-1, 14);
            Timestamp l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsAppliyDate);
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setTradingTimeType("9");
            l_clendarContext.setMarketCode("sp");
            l_clendarContext.setProductCode("1");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext",
                l_clendarContext);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams =
                TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setTradingTimeType("9");
            l_tradingTimeParams.setMarketCode("sp");
            l_tradingTimeParams.setProductCode("1");
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            //DBにデータをインサート
            AioOrderUnitParams l_params = new AioOrderUnitParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(AioOrderUnitRow.TYPE);
            l_params = TestDBUtility.getAioOrderUnitRow();
            l_params.setOrderUnitId(10);
            l_params.setOrderType(OrderTypeEnum.DEPOSIT_AMOUNT_FROM_CFD);
            l_params.setMqStatus("0");
            l_params.setQuantity(100);
            l_params.setOrderRequestNumber("123");
            l_params.setAccountId(333812512246L);
            l_params.setRemarkCode("41");
            l_params.setRemarkName("101");
            l_params.setTransferType(AssetTransferTypeEnum.CASH_OUT);
            TestDBUtility.insertWithDel(l_params);

            MainAccountParams l_mainAccountParams = new MainAccountParams();
            l_processor.doDeleteAllQuery(MainAccountRow.TYPE);
            l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams l_institutionParams = new InstitutionParams();
            l_processor.doDeleteAllQuery(InstitutionRow.TYPE);
            l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33L);
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            BranchParams l_branchParams = new BranchParams();
            l_processor.doDeleteAllQuery(BranchRow.TYPE);
            l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("624");
            l_branchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_branchParams);

            CalendarParams l_calendarParams = new CalendarParams();
            l_processor.doDeleteAllQuery(CalendarRow.TYPE);
            l_calendar.set(2007, 6-1, 14);
            l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
            l_calendarParams.setHoliday(l_tsAppliyDate);
            l_calendarParams.setBizDateType("0");
            l_calendarParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_calendarParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_calendarParams);

            l_processor.doDeleteAllQuery(HostTransferOrderRow.TYPE);

            l_processor.doDeleteAllQuery(HostTransferOrderRow.TYPE);
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                    WEB3DateUtility.getDate("20090321", "yyyyMMdd"));

            //実際のメソッドを実行
            AioOrderManagerImpl l_aioOrderManagerImpl = new AioOrderManagerImpl();
            AioOrderUnit l_aioOrderUnit = (AioOrderUnit)l_aioOrderManagerImpl.getOrderUnit(10);
            l_impl.createTransferOrderQueueData(l_aioOrderUnit);

            //比較
            List l_lisRows = l_processor.doFindAllQuery(HostTransferOrderRow.TYPE, null, null, null, null);
            HostTransferOrderRow l_hostTransferOrderRow = (HostTransferOrderRow)l_lisRows.get(0);
            assertEquals(l_hostTransferOrderRow.getTransferAmountDebitor(), 0);
            assertEquals(l_hostTransferOrderRow.getTransferAmountCreditor(), 100);
            assertEquals(l_hostTransferOrderRow.getRemarkCode(), "41");
            assertEquals(l_hostTransferOrderRow.getRequestCode(), "GI806");
            assertEquals(l_hostTransferOrderRow.getInstitutionCode(), "0D");
            assertEquals(l_hostTransferOrderRow.getBranchCode(), "624");
            assertEquals(l_hostTransferOrderRow.getAccountCode(), "2512246");
            assertEquals(l_hostTransferOrderRow.getTraderCode(), "     ");
            assertEquals(l_hostTransferOrderRow.getRemarkName(), "101");
            assertEquals(l_hostTransferOrderRow.getCancelDiv(), " ");
            assertEquals("9", l_hostTransferOrderRow.getInvoluntaryTransfer());
            assertEquals("0716", l_hostTransferOrderRow.getOriginalTransferDate());
            assertEquals(l_hostTransferOrderRow.getOrderRequestNumber(), "123");
            assertEquals(0, WEB3DateUtility.compareToDay(
                l_hostTransferOrderRow.getTransferDate(), WEB3DateUtility.getDate("20040716","yyyyMMdd")));
            assertEquals(0, WEB3DateUtility.compareToDay(
                l_hostTransferOrderRow.getTransferTime(), WEB3DateUtility.getDate("20040716060000","yyyyMMddHHmmss")));
            assertEquals(l_hostTransferOrderRow.getStatus(), "3");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testCreateTransferOrderQueueData_C0012()
    {
        final String STR_METHOD_NAME = "testCreateTransferOrderQueueData_C0012()";
        log.exiting(STR_METHOD_NAME);        

        try
        {
            //スタティックメソッドの準備
            Calendar l_calendar =  Calendar.getInstance();
            l_calendar.set(2007, 6-1, 14);
            Timestamp l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsAppliyDate);
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setTradingTimeType("9");
            l_clendarContext.setMarketCode("sp");
            l_clendarContext.setProductCode("1");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext",
                l_clendarContext);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams =
                TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setTradingTimeType("9");
            l_tradingTimeParams.setMarketCode("sp");
            l_tradingTimeParams.setProductCode("1");
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            //DBにデータをインサート
            AioOrderUnitParams l_params = new AioOrderUnitParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(AioOrderUnitRow.TYPE);
            l_params = TestDBUtility.getAioOrderUnitRow();
            l_params.setOrderUnitId(10);
            l_params.setOrderType(OrderTypeEnum.DEPOSIT_AMOUNT_FROM_CFD);
            l_params.setMqStatus("0");
            l_params.setQuantity(100);
            l_params.setOrderRequestNumber("123");
            l_params.setAccountId(333812512246L);
            l_params.setRemarkCode("99");
            l_params.setRemarkName("101");
            l_params.setTransferType(AssetTransferTypeEnum.CASH_IN);
            l_params.setDeliveryDate(WEB3DateUtility.getDate("20090323", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_params);

            MainAccountParams l_mainAccountParams = new MainAccountParams();
            l_processor.doDeleteAllQuery(MainAccountRow.TYPE);
            l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams l_institutionParams = new InstitutionParams();
            l_processor.doDeleteAllQuery(InstitutionRow.TYPE);
            l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33L);
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            BranchParams l_branchParams = new BranchParams();
            l_processor.doDeleteAllQuery(BranchRow.TYPE);
            l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("624");
            l_branchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_branchParams);

            CalendarParams l_calendarParams = new CalendarParams();
            l_processor.doDeleteAllQuery(CalendarRow.TYPE);
            l_calendar.set(2007, 6-1, 14);
            l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
            l_calendarParams.setHoliday(l_tsAppliyDate);
            l_calendarParams.setBizDateType("0");
            l_calendarParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_calendarParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_calendarParams);

            l_processor.doDeleteAllQuery(HostTransferOrderRow.TYPE);

            l_processor.doDeleteAllQuery(HostTransferOrderRow.TYPE);
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                    WEB3DateUtility.getDate("20090321", "yyyyMMdd"));

            //実際のメソッドを実行
            AioOrderManagerImpl l_aioOrderManagerImpl = new AioOrderManagerImpl();
            AioOrderUnit l_aioOrderUnit = (AioOrderUnit)l_aioOrderManagerImpl.getOrderUnit(10);
            l_impl.createTransferOrderQueueData(l_aioOrderUnit);

            //比較
            List l_lisRows = l_processor.doFindAllQuery(HostTransferOrderRow.TYPE, null, null, null, null);
            HostTransferOrderRow l_hostTransferOrderRow = (HostTransferOrderRow)l_lisRows.get(0);
            assertEquals(l_hostTransferOrderRow.getTransferAmountDebitor(), 0);
            assertEquals(l_hostTransferOrderRow.getTransferAmountCreditor(), 100);
            assertEquals(l_hostTransferOrderRow.getRemarkCode(), "99");
            assertEquals(l_hostTransferOrderRow.getRequestCode(), "GI806");
            assertEquals(l_hostTransferOrderRow.getInstitutionCode(), "0D");
            assertEquals(l_hostTransferOrderRow.getBranchCode(), "624");
            assertEquals(l_hostTransferOrderRow.getAccountCode(), "2512246");
            assertEquals(l_hostTransferOrderRow.getTraderCode(), "     ");
            assertEquals(l_hostTransferOrderRow.getRemarkName(), "101");
            assertEquals(l_hostTransferOrderRow.getCancelDiv(), " ");
            assertEquals(" ", l_hostTransferOrderRow.getInvoluntaryTransfer());
            assertEquals("    ", l_hostTransferOrderRow.getOriginalTransferDate());
            assertEquals(l_hostTransferOrderRow.getOrderRequestNumber(), "123");
            assertEquals(0, WEB3DateUtility.compareToDay(
                l_hostTransferOrderRow.getTransferDate(), WEB3DateUtility.getDate("20040716","yyyyMMdd")));
            assertEquals(0, WEB3DateUtility.compareToDay(
                l_hostTransferOrderRow.getTransferTime(), WEB3DateUtility.getDate("20040716060000","yyyyMMddHHmmss")));
            assertEquals(l_hostTransferOrderRow.getStatus(), "3");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testCreateTransferOrderQueueData_C0013()
    {
        final String STR_METHOD_NAME = "testCreateTransferOrderQueueData_Case001()";
        log.exiting(STR_METHOD_NAME);        

        try
        {
            //スタティックメソッドの準備
            Calendar l_calendar =  Calendar.getInstance();
            l_calendar.set(2007, 6-1, 14);
            Timestamp l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsAppliyDate);
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setTradingTimeType("9");
            l_clendarContext.setMarketCode("sp");
            l_clendarContext.setProductCode("1");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext",
                l_clendarContext);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams =
                TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setTradingTimeType("9");
            l_tradingTimeParams.setMarketCode("sp");
            l_tradingTimeParams.setProductCode("1");
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            //DBにデータをインサート
            AioOrderUnitParams l_params = new AioOrderUnitParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(AioOrderUnitRow.TYPE);
            l_params = TestDBUtility.getAioOrderUnitRow();
            l_params.setOrderUnitId(10);
            l_params.setOrderType(OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT);
            l_params.setMqStatus("0");
            l_params.setQuantity(5500);
            l_params.setOrderRequestNumber("123");
            l_params.setAccountId(333812512246L);
            l_params.setDeliveryDate(WEB3DateUtility.getDate("20090323", "yyyyMMdd"));
            l_params.setRemarkName("01");
            TestDBUtility.insertWithDel(l_params);

            MainAccountParams l_mainAccountParams = new MainAccountParams();
            l_processor.doDeleteAllQuery(MainAccountRow.TYPE);
            l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams l_institutionParams = new InstitutionParams();
            l_processor.doDeleteAllQuery(InstitutionRow.TYPE);
            l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33L);
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            BranchParams l_branchParams = new BranchParams();
            l_processor.doDeleteAllQuery(BranchRow.TYPE);
            l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("624");
            l_branchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_branchParams);

            CalendarParams l_calendarParams = new CalendarParams();
            l_processor.doDeleteAllQuery(CalendarRow.TYPE);
            l_calendar.set(2007, 6-1, 14);
            l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
            l_calendarParams.setHoliday(l_tsAppliyDate);
            l_calendarParams.setBizDateType("0");
            l_calendarParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_calendarParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_calendarParams);

            l_processor.doDeleteAllQuery(HostTransferOrderRow.TYPE);

            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                    WEB3DateUtility.getDate("20090321", "yyyyMMdd"));

            //実際のメソッドを実行
            AioOrderManagerImpl l_aioOrderManagerImpl = new AioOrderManagerImpl();
            AioOrderUnit l_aioOrderUnit = (AioOrderUnit)l_aioOrderManagerImpl.getOrderUnit(10);
            l_impl.createTransferOrderQueueData(l_aioOrderUnit);

            //比較
            List l_lisRows = l_processor.doFindAllQuery(HostTransferOrderRow.TYPE, null, null, null, null);
            HostTransferOrderRow l_hostTransferOrderRow = (HostTransferOrderRow)l_lisRows.get(0);
            assertEquals(l_hostTransferOrderRow.getTransferAmountDebitor(), 0);
            assertEquals(l_hostTransferOrderRow.getTransferAmountCreditor(), 5500);
            assertEquals(l_hostTransferOrderRow.getRemarkCode(), "01");
            assertEquals(l_hostTransferOrderRow.getRequestCode(), "GI806");
            assertEquals(l_hostTransferOrderRow.getInvoluntaryTransfer(), "9");
            assertEquals(l_hostTransferOrderRow.getInstitutionCode(), "0D");
            assertEquals(l_hostTransferOrderRow.getBranchCode(), "624");
            assertEquals(l_hostTransferOrderRow.getAccountCode(), "2512246");
            assertEquals(l_hostTransferOrderRow.getTraderCode(), "     ");
            assertEquals(l_hostTransferOrderRow.getRemarkName(), "          ");
            assertEquals(l_hostTransferOrderRow.getOriginalTransferDate(), "    ");
            assertEquals(l_hostTransferOrderRow.getCancelDiv(), " ");
            assertEquals(l_hostTransferOrderRow.getOrderRequestNumber(), "123");
            assertEquals(0, WEB3DateUtility.compareToDay(
                l_hostTransferOrderRow.getTransferDate(), WEB3DateUtility.getDate("20040716","yyyyMMdd")));
            assertEquals(0, WEB3DateUtility.compareToDay(
                l_hostTransferOrderRow.getTransferTime(), WEB3DateUtility.getDate("20040716060000","yyyyMMddHHmmss")));
            assertEquals(l_hostTransferOrderRow.getStatus(), "3");
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testCreateTransferOrderQueueData_C0014()
    {
        final String STR_METHOD_NAME = "testCreateTransferOrderQueueData_Case002()";
        log.exiting(STR_METHOD_NAME);        

        try
        {
            //スタティックメソッドの準備
            Calendar l_calendar =  Calendar.getInstance();
            l_calendar.set(2007, 6-1, 14);
            Timestamp l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsAppliyDate);
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setTradingTimeType("9");
            l_clendarContext.setMarketCode("sp");
            l_clendarContext.setProductCode("1");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext",
                l_clendarContext);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams =
                TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setTradingTimeType("9");
            l_tradingTimeParams.setMarketCode("sp");
            l_tradingTimeParams.setProductCode("1");
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                    WEB3DateUtility.getDate("20040716","yyyyMMdd"));

            //DBにデータをインサート
            AioOrderUnitParams l_params = new AioOrderUnitParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(AioOrderUnitRow.TYPE);
            l_params = TestDBUtility.getAioOrderUnitRow();
            l_params.setOrderUnitId(10);
            l_params.setOrderType(OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT);
            l_params.setMqStatus("0");
            l_params.setQuantity(100);
            l_params.setOrderRequestNumber("123");
            l_params.setAccountId(333812512246L);
            l_params.setRemarkCode("99");
            l_params.setRemarkName("0015");
            l_params.setTransferType(AssetTransferTypeEnum.CASH_IN);
            TestDBUtility.insertWithDel(l_params);

            MainAccountParams l_mainAccountParams = new MainAccountParams();
            l_processor.doDeleteAllQuery(MainAccountRow.TYPE);
            l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams l_institutionParams = new InstitutionParams();
            l_processor.doDeleteAllQuery(InstitutionRow.TYPE);
            l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33L);
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            BranchParams l_branchParams = new BranchParams();
            l_processor.doDeleteAllQuery(BranchRow.TYPE);
            l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("624");
            l_branchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_branchParams);

            CalendarParams l_calendarParams = new CalendarParams();
            l_processor.doDeleteAllQuery(CalendarRow.TYPE);
            l_calendar.set(2007, 6-1, 14);
            l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
            l_calendarParams.setHoliday(l_tsAppliyDate);
            l_calendarParams.setBizDateType("0");
            l_calendarParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_calendarParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_calendarParams);

            l_processor.doDeleteAllQuery(HostTransferOrderRow.TYPE);

            l_processor.doDeleteAllQuery(HostTransferOrderRow.TYPE);
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                    WEB3DateUtility.getDate("20090321", "yyyyMMdd"));

            //実際のメソッドを実行
            AioOrderManagerImpl l_aioOrderManagerImpl = new AioOrderManagerImpl();
            AioOrderUnit l_aioOrderUnit = (AioOrderUnit)l_aioOrderManagerImpl.getOrderUnit(10);
            l_impl.createTransferOrderQueueData(l_aioOrderUnit);

            //比較
            List l_lisRows = l_processor.doFindAllQuery(HostTransferOrderRow.TYPE, null, null, null, null);
            HostTransferOrderRow l_hostTransferOrderRow = (HostTransferOrderRow)l_lisRows.get(0);
            assertEquals(l_hostTransferOrderRow.getTransferAmountDebitor(), 100);
            assertEquals(l_hostTransferOrderRow.getTransferAmountCreditor(), 0);
            assertEquals(l_hostTransferOrderRow.getRemarkCode(), "99");
            assertEquals(l_hostTransferOrderRow.getRequestCode(), "GI806");
            assertEquals(l_hostTransferOrderRow.getInstitutionCode(), "0D");
            assertEquals(l_hostTransferOrderRow.getBranchCode(), "624");
            assertEquals(l_hostTransferOrderRow.getAccountCode(), "2512246");
            assertEquals(l_hostTransferOrderRow.getTraderCode(), "     ");
            assertEquals(l_hostTransferOrderRow.getRemarkName(), "0015");
            assertEquals(l_hostTransferOrderRow.getCancelDiv(), " ");
            assertEquals(" ", l_hostTransferOrderRow.getInvoluntaryTransfer());
            assertEquals("0716", l_hostTransferOrderRow.getOriginalTransferDate());
            assertEquals(l_hostTransferOrderRow.getOrderRequestNumber(), "123");
            assertEquals(0, WEB3DateUtility.compareToDay(
                l_hostTransferOrderRow.getTransferDate(), WEB3DateUtility.getDate("20040716","yyyyMMdd")));
            assertEquals(0, WEB3DateUtility.compareToDay(
                l_hostTransferOrderRow.getTransferTime(), WEB3DateUtility.getDate("20040716060000","yyyyMMddHHmmss")));
            assertEquals(l_hostTransferOrderRow.getStatus(), "3");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 振替注文（預り金から信用保証金）の場合
     *
     */
    public void testSend_C0001()
    {
        final String STR_METHOD_NAME = "testSend_C0001";
        log.exiting(TEST_START+STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
            AioOrderUnitParams l_params = TestDBUtility.getAioOrderUnitRow();
            l_params.setOrderUnitId(10);
            l_params.setAccountId(333812512203L);
            l_params.setOrderRequestNumber("24");
            l_params.setCurrencyCode("10");
            l_params.setOrderId(10);
            l_params.setOrderType(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE);
            TestDBUtility.insertWithDel(l_params);
            AioNewOrderMarketRequestMessage l_cashTransOrderRequest = new AioNewOrderMarketRequestMessageForTest();
            MarketRequestSendResult l_returnResult = l_impl.send(l_cashTransOrderRequest);
            assertTrue(l_returnResult.getProcessingResult().isFailedResult());
            assertEquals("新規振替注文送信",(l_returnResult.getProcessingResult().getErrorInfo()).getErrorMessage());
            
        }
        catch (Exception l_ex)
        {
            log.debug(""+l_ex);
            fail();
        }
        log.exiting(TEST_END+STR_METHOD_NAME);
    }

    public void testSend_C0002()
    {
    	final String STR_METHOD_NAME = "testSend_C0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
        	TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
        	AioOrderUnitParams l_aioOrderUnitParams =
        		TestDBUtility.getAioOrderUnitRow();
        	l_aioOrderUnitParams.setOrderId(10);
        	l_aioOrderUnitParams.setOrderType(OrderTypeEnum.CFD_FROM_DEPOSIT_AMOUNT);
        	l_aioOrderUnitParams.setOrderRootDiv("6");
        	l_aioOrderUnitParams.setAccountId(132);
        	l_aioOrderUnitParams.setSubAccountId(123);
        	TestDBUtility.insertWithDel(l_aioOrderUnitParams);

        	TestDBUtility.deleteAll(SubAccountRow.TYPE);
        	SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        	l_subAccountParams.setAccountId(132);
        	l_subAccountParams.setSubAccountId(235);
        	TestDBUtility.insertWithDel(l_subAccountParams);

        	AioNewOrderMarketRequestMessage l_aioNewOrderMarketRequest =
        		new AioNewOrderMarketRequestMessageForTest();

        	WEB3AioMarketRequestSenderServiceImpl l_aioMarketRequestSenderServiceImpl =
        		new WEB3AioMarketRequestSenderServiceImpl();
        	MarketRequestSendResult l_returnResult =
        		l_aioMarketRequestSenderServiceImpl.send(l_aioNewOrderMarketRequest);

        	assertEquals(
    			l_returnResult.getProcessingResult().getErrorInfo(),
    			WEB3ErrorCatalog.SYSTEM_ERROR_80005);
        }
        catch (Exception l_ex)
        {
        	log.error(l_ex.getMessage(), l_ex);
        	log.exiting(STR_METHOD_NAME);
        	fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testSend_C0003()
    {
    	final String STR_METHOD_NAME = "testSend_C0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
        	TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
        	AioOrderUnitParams l_aioOrderUnitParams =
        		TestDBUtility.getAioOrderUnitRow();
        	l_aioOrderUnitParams.setOrderId(10);
        	l_aioOrderUnitParams.setOrderType(OrderTypeEnum.CFD_FROM_DEPOSIT_AMOUNT);
        	l_aioOrderUnitParams.setOrderRootDiv("9");
        	l_aioOrderUnitParams.setAccountId(132);
        	l_aioOrderUnitParams.setSubAccountId(123);
        	TestDBUtility.insertWithDel(l_aioOrderUnitParams);

        	TestDBUtility.deleteAll(SubAccountRow.TYPE);
        	SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        	l_subAccountParams.setAccountId(132);
        	l_subAccountParams.setSubAccountId(123);
        	TestDBUtility.insertWithDel(l_subAccountParams);

        	AioNewOrderMarketRequestMessage l_aioNewOrderMarketRequest =
        		new AioNewOrderMarketRequestMessageForTest();

        	MarketRequestSendResult l_returnResult =
        		l_impl.send(l_aioNewOrderMarketRequest);

        	assertEquals(l_returnResult.getMessageTokenId(), 0);
        }
        catch (Exception l_ex)
        {
        	log.error(l_ex.getMessage(), l_ex);
        	log.exiting(STR_METHOD_NAME);
        	fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testSend_C0004()
    {
    	final String STR_METHOD_NAME = "testSend_C0004()";
        log.entering(STR_METHOD_NAME);

        try
        {
        	TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
        	AioOrderUnitParams l_aioOrderUnitParams =
        		TestDBUtility.getAioOrderUnitRow();
        	l_aioOrderUnitParams.setOrderId(10);
        	l_aioOrderUnitParams.setOrderType(OrderTypeEnum.DEPOSIT_AMOUNT_FROM_CFD);
        	l_aioOrderUnitParams.setOrderRootDiv("6");
        	l_aioOrderUnitParams.setAccountId(132);
        	l_aioOrderUnitParams.setSubAccountId(123);
        	TestDBUtility.insertWithDel(l_aioOrderUnitParams);

        	TestDBUtility.deleteAll(SubAccountRow.TYPE);
        	SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        	l_subAccountParams.setAccountId(132);
        	l_subAccountParams.setSubAccountId(253);
        	TestDBUtility.insertWithDel(l_subAccountParams);

        	AioNewOrderMarketRequestMessage l_aioNewOrderMarketRequest =
        		new AioNewOrderMarketRequestMessageForTest();

        	WEB3AioMarketRequestSenderServiceImpl l_aioMarketRequestSenderServiceImpl =
        		new WEB3AioMarketRequestSenderServiceImpl();
        	MarketRequestSendResult l_returnResult =
        		l_aioMarketRequestSenderServiceImpl.send(l_aioNewOrderMarketRequest);

        	assertEquals(
    			l_returnResult.getProcessingResult().getErrorInfo(),
    			WEB3ErrorCatalog.SYSTEM_ERROR_80005);
        }
        catch (Exception l_ex)
        {
        	log.error(l_ex.getMessage(), l_ex);
        	log.exiting(STR_METHOD_NAME);
        	fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testSend_C0005()
    {
    	final String STR_METHOD_NAME = "testSend_C0005()";
        log.entering(STR_METHOD_NAME);

        try
        {
        	TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
        	AioOrderUnitParams l_aioOrderUnitParams =
        		TestDBUtility.getAioOrderUnitRow();
        	l_aioOrderUnitParams.setOrderId(10);
        	l_aioOrderUnitParams.setOrderType(OrderTypeEnum.DEPOSIT_AMOUNT_FROM_CFD);
        	l_aioOrderUnitParams.setOrderRootDiv("9");
        	l_aioOrderUnitParams.setAccountId(132);
        	l_aioOrderUnitParams.setSubAccountId(123);
        	TestDBUtility.insertWithDel(l_aioOrderUnitParams);

        	TestDBUtility.deleteAll(SubAccountRow.TYPE);
        	SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        	l_subAccountParams.setAccountId(132);
        	l_subAccountParams.setSubAccountId(253);
        	TestDBUtility.insertWithDel(l_subAccountParams);

        	AioNewOrderMarketRequestMessage l_aioNewOrderMarketRequest =
        		new AioNewOrderMarketRequestMessageForTest();

        	MarketRequestSendResult l_returnResult =
        		l_impl.send(l_aioNewOrderMarketRequest);

        	assertEquals(l_returnResult.getMessageTokenId(), 0);
        }
        catch (Exception l_ex)
        {
        	log.error(l_ex.getMessage(), l_ex);
        	log.exiting(STR_METHOD_NAME);
        	fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    //連動:
    //注文単位.銘柄タイプ = 1（株式）の場合、0：機@構連動
    //預託: ブランク
    public void testCreateSecurityTransferOrderQueueData_C0001()
    {
        final String STR_METHOD_NAME = "testCreateSecurityTransferOrderQueueData_C0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(HostMrgsecTransferRow.TYPE);

            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setOrderId(123);
            l_aioOrderUnitParams.setAccountId(101);
            l_aioOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            l_aioOrderUnitParams.setProductId(111);
            l_aioOrderUnitParams.setOrderRequestNumber("1200");
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.COLLATERAL_SECURITIES_FROM_SAFE_DEPOSIT);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101);
            l_mainAccountParams.setInstitutionId(133);
            l_mainAccountParams.setBranchId(140);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(133);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(140);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(111);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            TestDBUtility.deleteAll(FeqProductRow.TYPE);

            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(111);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);

            TradingModule l_tradingModule = GtlUtils.getTradingModule(ProductTypeEnum.AIO);
            AioOrderManager l_orderManager = 
                (AioOrderManager)l_tradingModule.getOrderManager();
            OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(123);
    
            AioOrderUnit l_aioOrderUnit = (AioOrderUnit)l_orderUnits[0];

            WEB3AioMarketRequestSenderServiceImpl l_impl = new WEB3AioMarketRequestSenderServiceImpl();

            l_impl.createSecurityTransferOrderQueueData(l_aioOrderUnit);

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisQueryResult = l_queryProcessor.doFindAllQuery(HostMrgsecTransferRow.TYPE);

            assertEquals(1, l_lisQueryResult.size());
            assertEquals("0", ((HostMrgsecTransferRow)l_lisQueryResult.get(0)).getInterlock());
            assertEquals(" ", ((HostMrgsecTransferRow)l_lisQueryResult.get(0)).getDeposit());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    //連動:
    //注文単位.銘柄タイプ != 1（株式）の場合、 ：ブランク
    //預託: ブランク
    public void testCreateSecurityTransferOrderQueueData_C0002()
    {
        final String STR_METHOD_NAME = "testCreateSecurityTransferOrderQueueData_C0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(HostMrgsecTransferRow.TYPE);

            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setOrderId(123);
            l_aioOrderUnitParams.setAccountId(101);
            l_aioOrderUnitParams.setProductType(ProductTypeEnum.BOND);
            l_aioOrderUnitParams.setProductId(111);
            l_aioOrderUnitParams.setOrderRequestNumber("1200");
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.COLLATERAL_SECURITIES_FROM_SAFE_DEPOSIT);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101);
            l_mainAccountParams.setInstitutionId(133);
            l_mainAccountParams.setBranchId(140);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(133);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(140);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(111);
            l_productParams.setProductType(ProductTypeEnum.BOND);
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(BondProductRow.TYPE);
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(111);
            TestDBUtility.insertWithDel(l_bondProductParams);

            TradingModule l_tradingModule = GtlUtils.getTradingModule(ProductTypeEnum.AIO);
            AioOrderManager l_orderManager = 
                (AioOrderManager)l_tradingModule.getOrderManager();
            OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(123);
    
            AioOrderUnit l_aioOrderUnit = (AioOrderUnit)l_orderUnits[0];

            WEB3AioMarketRequestSenderServiceImpl l_impl = new WEB3AioMarketRequestSenderServiceImpl();

            l_impl.createSecurityTransferOrderQueueData(l_aioOrderUnit);

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisQueryResult = l_queryProcessor.doFindAllQuery(HostMrgsecTransferRow.TYPE);

            assertEquals(1, l_lisQueryResult.size());
            assertEquals(" ", ((HostMrgsecTransferRow)l_lisQueryResult.get(0)).getInterlock());
            assertEquals(" ", ((HostMrgsecTransferRow)l_lisQueryResult.get(0)).getDeposit());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testUpdateTransferProcessDiv1_C0001()
    {
        final String STR_METHOD_NAME = "testUpdateTransferProcessDiv1_C0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setOrderUnitId(123);
            l_aioOrderUnitParams.setLastOrderActionSerialNo(1002);
            l_aioOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate("20081010", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(111101111010L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(AioOrderActionRow.TYPE);
            AioOrderActionParams l_aioOrderActionParams = TestDBUtility.getAioOrderActionRow();
            l_aioOrderActionParams.setOrderId(123);
            l_aioOrderActionParams.setOrderActionSerialNo(1002);
            TestDBUtility.insertWithDel(l_aioOrderActionParams);

            TestDBUtility.deleteAll(AioOrderRow.TYPE);
            AioOrderParams l_aioOrderParams = TestDBUtility.getAioOrderRow();
            l_aioOrderParams.setOrderId(123);
            TestDBUtility.insertWithDel(l_aioOrderActionParams);

            TestDBUtility.deleteAll(HostTransferOrderRow.TYPE);
            HostTransferOrderParams l_hostTransferOrderParams =
                TestDBUtility.getHostTransferOrderRow();
            l_hostTransferOrderParams.setRequestCode("GI806");
            l_hostTransferOrderParams.setAccountCode("1002");
            l_hostTransferOrderParams.setOrderRequestNumber("1003");
            l_hostTransferOrderParams.setStatus("3");
            TestDBUtility.insertWithDel(l_hostTransferOrderParams);

            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                WEB3DateUtility.getDate("20081010", "yyyyMMdd"));

            WEB3AioMarketRequestSenderServiceImpl l_impl = new WEB3AioMarketRequestSenderServiceImpl();

            l_impl.updateTransferProcessDiv(
                    "0D", 
                    "381", 
                    "1002", 
                    "1003", 
                    123, 
                    "20090321", 
                    "20090321");

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisRows = l_queryProcessor.doFindAllQuery(HostTransferOrderRow.TYPE);

            assertEquals(1, l_lisRows.size());

            HostTransferOrderRow l_hostTransferOrderRow =
                (HostTransferOrderRow)l_lisRows.get(0);
            assertEquals("0321", l_hostTransferOrderRow.getOriginalTransferDate());
        }
        catch (Exception l_ex)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testUpdateTransferProcessDiv1_C0002()
    {
        final String STR_METHOD_NAME = "testUpdateTransferProcessDiv1_C0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setOrderUnitId(123);
            l_aioOrderUnitParams.setLastOrderActionSerialNo(1002);
            l_aioOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate("20090321", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(111101111010L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(AioOrderActionRow.TYPE);
            AioOrderActionParams l_aioOrderActionParams = TestDBUtility.getAioOrderActionRow();
            l_aioOrderActionParams.setOrderId(123);
            l_aioOrderActionParams.setOrderActionSerialNo(1002);
            TestDBUtility.insertWithDel(l_aioOrderActionParams);

            TestDBUtility.deleteAll(AioOrderRow.TYPE);
            AioOrderParams l_aioOrderParams = TestDBUtility.getAioOrderRow();
            l_aioOrderParams.setOrderId(123);
            TestDBUtility.insertWithDel(l_aioOrderActionParams);

            TestDBUtility.deleteAll(HostTransferOrderRow.TYPE);
            HostTransferOrderParams l_hostTransferOrderParams =
                TestDBUtility.getHostTransferOrderRow();
            l_hostTransferOrderParams.setRequestCode("GI806");
            l_hostTransferOrderParams.setAccountCode("1002");
            l_hostTransferOrderParams.setOrderRequestNumber("1003");
            l_hostTransferOrderParams.setStatus("3");
            TestDBUtility.insertWithDel(l_hostTransferOrderParams);

            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                WEB3DateUtility.getDate("20090321", "yyyyMMdd"));

            WEB3AioMarketRequestSenderServiceImpl l_impl = new WEB3AioMarketRequestSenderServiceImpl();

            l_impl.updateTransferProcessDiv(
                    "0D", 
                    "381", 
                    "1002", 
                    "1003", 
                    123, 
                    "20090321", 
                    "20090321");

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisRows = l_queryProcessor.doFindAllQuery(HostTransferOrderRow.TYPE);

            assertEquals(1, l_lisRows.size());

            HostTransferOrderRow l_hostTransferOrderRow =
                (HostTransferOrderRow)l_lisRows.get(0);
            assertEquals("    ", l_hostTransferOrderRow.getOriginalTransferDate());
        }
        catch (Exception l_ex)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testUpdateTransferProcessDiv2_C0001()
    {
        final String STR_METHOD_NAME = "testUpdateTransferProcessDiv2_C0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setOrderUnitId(123);
            l_aioOrderUnitParams.setLastOrderActionSerialNo(1002);
            l_aioOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate("20081010", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(111101111010L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(AioOrderActionRow.TYPE);
            AioOrderActionParams l_aioOrderActionParams = TestDBUtility.getAioOrderActionRow();
            l_aioOrderActionParams.setOrderId(123);
            l_aioOrderActionParams.setOrderActionSerialNo(1002);
            TestDBUtility.insertWithDel(l_aioOrderActionParams);

            TestDBUtility.deleteAll(AioOrderRow.TYPE);
            AioOrderParams l_aioOrderParams = TestDBUtility.getAioOrderRow();
            l_aioOrderParams.setOrderId(123);
            TestDBUtility.insertWithDel(l_aioOrderActionParams);

            TestDBUtility.deleteAll(HostTransferOrderRow.TYPE);
            HostTransferOrderParams l_hostTransferOrderParams =
                TestDBUtility.getHostTransferOrderRow();
            l_hostTransferOrderParams.setRequestCode("GI806");
            l_hostTransferOrderParams.setAccountCode("1002");
            l_hostTransferOrderParams.setOrderRequestNumber("1003");
            l_hostTransferOrderParams.setStatus("3");
            TestDBUtility.insertWithDel(l_hostTransferOrderParams);

            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                WEB3DateUtility.getDate("20081010", "yyyyMMdd"));

            WEB3AioMarketRequestSenderServiceImpl l_impl = new WEB3AioMarketRequestSenderServiceImpl();

            l_impl.updateTransferProcessDiv(
                    "0D", 
                    "381", 
                    "1002", 
                    "1003", 
                    123, 
                    "20090321", 
                    "20090321",
                    "500",
                    "02");

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisRows = l_queryProcessor.doFindAllQuery(HostTransferOrderRow.TYPE);

            assertEquals(1, l_lisRows.size());

            HostTransferOrderRow l_hostTransferOrderRow =
                (HostTransferOrderRow)l_lisRows.get(0);
            assertEquals("0321", l_hostTransferOrderRow.getOriginalTransferDate());
            assertEquals(500, l_hostTransferOrderRow.getTransferAmountDebitor());
            assertEquals("02", l_hostTransferOrderRow.getRemarkCode());
        }
        catch (Exception l_ex)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testUpdateTransferProcessDiv2_C0002()
    {
        final String STR_METHOD_NAME = "testUpdateTransferProcessDiv2_C0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setOrderUnitId(123);
            l_aioOrderUnitParams.setLastOrderActionSerialNo(1002);
            l_aioOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate("20081010", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(111101111010L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(AioOrderActionRow.TYPE);
            AioOrderActionParams l_aioOrderActionParams = TestDBUtility.getAioOrderActionRow();
            l_aioOrderActionParams.setOrderId(123);
            l_aioOrderActionParams.setOrderActionSerialNo(1002);
            TestDBUtility.insertWithDel(l_aioOrderActionParams);

            TestDBUtility.deleteAll(AioOrderRow.TYPE);
            AioOrderParams l_aioOrderParams = TestDBUtility.getAioOrderRow();
            l_aioOrderParams.setOrderId(123);
            TestDBUtility.insertWithDel(l_aioOrderActionParams);

            TestDBUtility.deleteAll(HostTransferOrderRow.TYPE);
            HostTransferOrderParams l_hostTransferOrderParams =
                TestDBUtility.getHostTransferOrderRow();
            l_hostTransferOrderParams.setRequestCode("GI806");
            l_hostTransferOrderParams.setAccountCode("1002");
            l_hostTransferOrderParams.setOrderRequestNumber("1003");
            l_hostTransferOrderParams.setStatus("3");
            TestDBUtility.insertWithDel(l_hostTransferOrderParams);

            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                WEB3DateUtility.getDate("20090321", "yyyyMMdd"));

            WEB3AioMarketRequestSenderServiceImpl l_impl = new WEB3AioMarketRequestSenderServiceImpl();

            l_impl.updateTransferProcessDiv(
                    "0D", 
                    "381", 
                    "1002", 
                    "1003", 
                    123, 
                    "20090321", 
                    "20090321",
                    "500",
                    "01");

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisRows = l_queryProcessor.doFindAllQuery(HostTransferOrderRow.TYPE);

            assertEquals(1, l_lisRows.size());

            HostTransferOrderRow l_hostTransferOrderRow =
                (HostTransferOrderRow)l_lisRows.get(0);
            assertEquals("    ", l_hostTransferOrderRow.getOriginalTransferDate());
            assertEquals(500, l_hostTransferOrderRow.getTransferAmountDebitor());
            assertEquals("01", l_hostTransferOrderRow.getRemarkCode());
        }
        catch (Exception l_ex)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    private class WEB3AioMarketRequestSenderServiceImplForTest extends WEB3AioMarketRequestSenderServiceImpl
    {
        public void openTransferOrderSend(AioOrderUnit l_orderUnit) throws WEB3BaseException
        {
            ErrorInfo l_errorInfo = new ErrorInfo();
            l_errorInfo.setErrorMessage("新規振替注文送信");
            throw new WEB3BaseException(l_errorInfo, "新規振替注文送信");
        }
    }
    
    private class AioNewOrderMarketRequestMessageForTest implements AioNewOrderMarketRequestMessage
    {

        public AioOrderUnitRow getAioOrderUnitRow()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public SubAccount getSubAccount()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public long getOrderId()
        {
            return 10;
        }
        
    }
}
@
