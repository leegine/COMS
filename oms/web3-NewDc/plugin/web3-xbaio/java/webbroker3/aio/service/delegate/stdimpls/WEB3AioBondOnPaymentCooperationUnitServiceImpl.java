head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.30.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioBondOnPaymentCooperationUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券出金連携UnitServiceImpl(WEB3AioBondOnPaymentCooperationUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/10/17 徐宏偉 (中訊) 新規作成
Revesion History : 2007/03/12 何文敏 (中訊)  モデルNo.710
*/
package webbroker3.aio.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AssetTransferTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitParams;

import webbroker3.aio.WEB3AioBondOnPaymentInfo;
import webbroker3.aio.WEB3AioForeignCashTransOrderUpdateInterceptor;
import webbroker3.aio.WEB3AioNewOrderSpec;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.service.delegate.WEB3AioBondOnPaymentCooperationUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BranchLockDef;
import webbroker3.common.define.WEB3SettlementDivDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.util.WEB3LogUtility;

/**
 * (債券出金連携UnitServiceImpl)<BR>
 * 債券出金連携UnitService実装クラス<BR>
 * <BR>
 * Plugin時に自動トランザクションTransactionalInterceptor(<BR>
 * TransactionalInterceptor.TX_CREATE_NEW)を指定する。<BR>
 * <BR>
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public class WEB3AioBondOnPaymentCooperationUnitServiceImpl
    implements WEB3AioBondOnPaymentCooperationUnitService
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AioBondOnPaymentCooperationUnitServiceImpl.class);
    /**
     * (submit注文)<BR>
     * 債券からの注文の登録を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（債券出金連携）submit注文」 参照<BR>
     * <BR>
     * @@param l_bondOnPaymentInfo - 債券出金情報
     * @@throws WEB3BaseException
     */
    public void submitOrder(WEB3AioBondOnPaymentInfo l_bondOnPaymentInfo)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitOrder(WEB3AioBondOnPaymentInfo l_bondOnPaymentInfo)";
        log.entering(STR_METHOD_NAME);

        if (l_bondOnPaymentInfo == null)
        {
            log.debug("パラメータNull出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        try
        {
            //1.1.get顧客(口座ID : long)
            //顧客オブジェクトを取得する。
            //[引数]
            //口座ID： 引数.債券出金情報.口座ID
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();

            MainAccount l_mainAccount =
                l_accountManager.getMainAccount(
                    l_bondOnPaymentInfo.getAccountId().longValue());

            MainAccountRow l_mainAccountRow =
                (MainAccountRow)l_mainAccount.getDataSourceObject();

            //1.2.支店ロックの場合
            //顧客.支店ロック = "1"（ロック）の場合
            if (WEB3BranchLockDef.BRANCH_LOCK.equals(l_mainAccountRow.getBranchLock()))
            {
                //1.2.1.return
                log.debug("顧客.支店ロック = 1（ロック）の場合 return");
                return;
            }

            //1.3.getInstitution( )
            //証券会社オブジェクトを取得する。
            String l_strInstitutionCode = l_mainAccountRow.getInstitutionCode();
            Institution l_institution =
                l_accountManager.getInstitution(l_strInstitutionCode);

            //1.4.get商品ID(Institution)
            //入出金用の商品IDを取得する。
            //[引数]
            //証券会社： 証券会社オブジェクト
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.AIO);
            WEB3AioOrderManager l_orderManager =
                (WEB3AioOrderManager)l_tradingModule.getOrderManager();

            long l_lngProductId = l_orderManager.getProductId(l_institution);

            //1.5.createNewOrderId( )
            //新規注文IDを取得する。
            long l_lngNewOrderId = l_orderManager.createNewOrderId();

            //1.6.入出金注文内容
            //入出金注文内容インスタンスを生成する。
            //[引数]
            //扱者： null
            Trader l_trader = null;

            //注文種別： 1001（出金注文）
            OrderTypeEnum l_orderType = OrderTypeEnum.CASH_OUT;

            //振替タイプ： 2（出金）
            AssetTransferTypeEnum l_assetTransferType = AssetTransferTypeEnum.CASH_OUT;

            //商品ID： get商品ID()の戻り値
            //金額： 円貨（引数.債券出金情報.決済区分 = "1"（円貨））の場合、引数.債券出金情報.受渡代金（円貨）
            //　@　@　@　@外貨（引数.債券出金情報.決済区分 = "2"（外貨））の場合、引数.債券出金情報.受渡代金（外貨）
            Double l_amount = new Double(0);
            double l_dblAmount = 0.0D;
            if (WEB3SettlementDivDef.JAPANESE_CURRENCY.equals(
                l_bondOnPaymentInfo.getSettlementDiv()))
            {
                log.debug("引数.債券出金情報.決済区分 = 1（円貨））の場合");
                l_amount = l_bondOnPaymentInfo.getEstimatedPrice();
            }
            else if (WEB3SettlementDivDef.FOREIGN_CURRENCY.equals(
                l_bondOnPaymentInfo.getSettlementDiv()))
            {
                log.debug("（引数.債券出金情報.決済区分 = 2（外貨））の場合");
                l_amount = l_bondOnPaymentInfo.getForeignEstimatedPrice();
            }
            if (l_amount != null)
            {
                l_dblAmount = l_amount.doubleValue();
            }
            //記述： 円貨（引数.債券出金情報.決済区分 = "1"（円貨））の場合、null
            //外貨（引数.債券出金情報.決済区分 = "2"（外貨））の場合、引数.債券出金情報.債券注文単位ID
            String l_strDescription = null;
            if (WEB3SettlementDivDef.FOREIGN_CURRENCY.equals(
                    l_bondOnPaymentInfo.getSettlementDiv()))
            {
                l_strDescription = l_bondOnPaymentInfo.getBondOrderUnitId() + "";
            }
            //振替予定日： 引数.債券出金情報.受渡日
            //決済機@関ID： null
            //注文ID： createNewOrderId( )の戻り値
            WEB3AioNewOrderSpec l_aioNewOrderSpec = new WEB3AioNewOrderSpec(
                l_trader,
                l_orderType,
                l_assetTransferType,
                l_lngProductId,
                l_dblAmount,
                l_strDescription,
                l_bondOnPaymentInfo.getDeliveryDate(),
                null,
                new Long(l_lngNewOrderId));

            //1.7.get発注日( )
            //発注日を取得する。
            Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

            //1.8.getSubAccount(補助口座タイプ : SubAccountTypeEnum)
            //補助口座オブジェクトを取得する。
            //[引数]
            //補助口座タイプ： 1（預り金口座）
            SubAccount l_subAccount =
                l_mainAccount.getSubAccount(
                    SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

            //1.9.get新規識別コード
            //新規の識別コードを取得する。
            //[引数]
            // 証券会社コード： 補助口座から取得した証券会社コード
            // 部店コード： 補助口座から取得した部店コード
            // 銘柄タイプ： 5（現金）
            WEB3HostReqOrderNumberManageService l_hostReqOrderNumberManageService =
                (WEB3HostReqOrderNumberManageService)Services.getService(
                    WEB3HostReqOrderNumberManageService.class);
            String l_strBranchCode = l_subAccount.getMainAccount().getBranch().getBranchCode();

            String l_strNewNumber = l_hostReqOrderNumberManageService.getNewNumber(
                l_subAccount.getInstitution().getInstitutionCode(),
                l_strBranchCode,
                ProductTypeEnum.CASH);

            //1.10.外貨入出金注文更新インタセプタ(入出金注文内容)
            //外貨入出金注文更新インタセプタのインスタンスを生成する。
            //[引数]
            //入出金注文内容： 入出金注文内容オブジェクト
            WEB3AioForeignCashTransOrderUpdateInterceptor
                l_foreignCashTransOrderUpdateInterceptor =
                    new WEB3AioForeignCashTransOrderUpdateInterceptor(l_aioNewOrderSpec);

            //1.11.プロパティセット
            //(*) 以下のとおりにインタセプタのプロパティをセットする。
            //インタセプタ.発注日 = 取引時間単位.get発注日()の戻り値
            l_foreignCashTransOrderUpdateInterceptor.setBizDate(l_datBizDate);

            //インタセプタ.受渡日 = 引数.債券出金情報.受渡日
            l_foreignCashTransOrderUpdateInterceptor.setDeliveryDate(
                l_bondOnPaymentInfo.getDeliveryDate());

            //インタセプタ.識別コード = get新規識別コード()の戻り値
            l_foreignCashTransOrderUpdateInterceptor.setOrderRequestNumber(
                l_strNewNumber);

            //インタセプタ.通貨コード = 引数.債券出金情報.通貨コード
            l_foreignCashTransOrderUpdateInterceptor.setCurrencyCode(
                l_bondOnPaymentInfo.getCurrencyCode());

            //インタセプタ.入出金金額(円換算額)
            //　@= 円貨（引数.債券出金情報.決済区分 = "1"（円貨））の場合、null
            //　@　@外貨（引数.債券出金情報.決済区分 = "2"（外貨））の場合、引数.債券出金情報.受渡代金（円貨）
            if (WEB3SettlementDivDef.JAPANESE_CURRENCY.equals(
                l_bondOnPaymentInfo.getSettlementDiv()))
            {
                log.debug("（引数.債券出金情報.決済区分 = 1（円貨））の場合");
                l_foreignCashTransOrderUpdateInterceptor.setConvertAmount(null);
            }
            else if (WEB3SettlementDivDef.FOREIGN_CURRENCY.equals(
                l_bondOnPaymentInfo.getSettlementDiv()))
            {
                log.debug("（引数.債券出金情報.決済区分 = 2（外貨））の場合");
                l_foreignCashTransOrderUpdateInterceptor.setConvertAmount(
                    l_bondOnPaymentInfo.getEstimatedPrice());
            }

            //1.12.setThreadLocalPersistenceEventInterceptor(
            //外貨入出金注文更新インタセプタ : AioOrderManagerPersistenceEventInterceptor)
            //外貨入出金注文更新インタセプタ： 外貨入出金注文更新インタセプタオブジェクト
            l_orderManager.setThreadLocalPersistenceEventInterceptor(
                l_foreignCashTransOrderUpdateInterceptor);

            //1.13.submitNewOrder(補助口座 : SubAccount, 商品タイプ : ProductTypeEnum,
            //注文内容 : NewOrderSpec, 注文ID : long, パスワード : String, isSkip発注審査 : boolean)
            //注文登録処理を行う。
            //[引数]
            //補助口座： 補助口座オブジェクト
            //商品タイプ： 5（現金）
            //注文内容： 入出金注文内容オブジェクト
            //注文ID： createNewOrderId()の戻り値
            //パスワード： 顧客.getTradingPassword()の戻り値をWEB3Crypt.decrypt()で復号したもの
            //isSkip発注審査： true
            WEB3Crypt l_webCrypt = new WEB3Crypt();
            OrderSubmissionResult l_submitNewOrderResult =
                l_orderManager.submitNewOrder(
                    l_subAccount,
                    ProductTypeEnum.CASH,
                    l_aioNewOrderSpec,
                    l_lngNewOrderId,
                    l_webCrypt.decrypt(l_mainAccount.getTradingPassword()),
                    true);

            if (l_submitNewOrderResult.getProcessingResult().isFailedResult())
            {
                log.debug("注文登録処理を行う Error"
                    + l_submitNewOrderResult.getProcessingResult().getErrorInfo());
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    l_submitNewOrderResult.getProcessingResult().getErrorInfo(),
                    STR_METHOD_NAME);
            }
        }
        catch (NotFoundException l_ex)
        {
            log.error("注文を取得する: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (create円貨注文)<BR>
     * 円貨用の債券出金情報インスタンスを生成する。<BR>
     * <BR>
     * １）債券出金情報インスタンスを生成する。<BR>
     * <BR>
     * ２）以下のとおりに債券出金情報のプロパティをセットする。<BR>
     * <BR>
     * ・債券出金情報.口座ID = new Long(引数.債券注文単位Params.口座ID)<BR>
     * ・債券出金情報.部店ID = new Long(引数.債券注文単位Params.部店ID)<BR>
     * ・債券出金情報.受渡日 = new Timestamp(引数.債券注文単位Params.受渡日.getTime())<BR>
     * ・債券出金情報.決済区分 = new String(引数.債券注文単位Params.決済区分)<BR>
     * ・債券出金情報.受渡代金（円貨） =<BR>
     * new Double(引数.債券注文単位Params.受渡代金（円貨）.doubleValue())<BR>
     * <BR>
     * ３）債券出金情報オブジェクトを返却する。<BR>
     * <BR>
     * @@param l_bondOrderUnitParams - 債券注文単位Paramsオブジェクト<BR>
     * @@return WEB3AioBondOnPaymentInfo
     */
    public WEB3AioBondOnPaymentInfo createJapaneseOrder(BondOrderUnitParams l_bondOrderUnitParams)
    {
        final String STR_METHOD_NAME = "createJapaneseOrder(BondOrderUnitParams l_bondOrderUnitParams)";
        log.entering(STR_METHOD_NAME);

        //１）債券出金情報インスタンスを生成する。
        WEB3AioBondOnPaymentInfo l_bondOnPaymentInfo = new WEB3AioBondOnPaymentInfo();

        //２）以下のとおりに債券出金情報のプロパティをセットする。
        // ・債券出金情報.口座ID = new Long(引数.債券注文単位Params.口座ID)
        l_bondOnPaymentInfo.setAccountId(new Long(l_bondOrderUnitParams.getAccountId()));

        // ・債券出金情報.部店ID = new Long(引数.債券注文単位Params.部店ID)
        l_bondOnPaymentInfo.setBranchId(new Long(l_bondOrderUnitParams.getBranchId()));

        // ・債券出金情報.受渡日 = new Timestamp(引数.債券注文単位Params.受渡日.getTime())
        l_bondOnPaymentInfo.setDeliveryDate(new Timestamp(
            l_bondOrderUnitParams.getDeliveryDate().getTime()));

        // ・債券出金情報.決済区分 = new String(引数.債券注文単位Params.決済区分)
        l_bondOnPaymentInfo.setSettlementDiv(l_bondOrderUnitParams.getSettlementDiv());

        // ・債券出金情報.受渡代金（円貨）=
        // new Double(引数.債券注文単位Params.受渡代金（円貨）.doubleValue())
        Double l_estimatedPrice = null;
        if (!l_bondOrderUnitParams.getEstimatedPriceIsNull())
        {
            l_estimatedPrice = new Double(l_bondOrderUnitParams.getEstimatedPrice());
        }
        l_bondOnPaymentInfo.setEstimatedPrice(l_estimatedPrice);

        //３）債券出金情報オブジェクトを返却する。
        log.exiting(STR_METHOD_NAME);
        return l_bondOnPaymentInfo;
    }

    /**
     * (create外貨注文)<BR>
     * 外貨用の債券出金情報インスタンスを生成する。
     * <BR>
     * １）債券出金情報インスタンスを生成する。 <BR>
     * <BR>
     * ２）以下のとおりに債券出金情報のプロパティをセットする。 <BR>
     * <BR>
     * ・債券出金情報.口座ID = new Long(引数.債券注文単位Params.口座ID) <BR>
     * ・債券出金情報.部店ID = new Long(引数.債券注文単位Params.部店ID) <BR>
     * ・債券出金情報.受渡日 = new Timestamp(引数.債券注文単位Params.受渡日.getTime()) <BR>
     * ・債券出金情報.通貨コード = new String(引数.債券注文単位Params.通貨コード) <BR>
     * ・債券出金情報.決済区分 = new String(引数.債券注文単位Params.決済区分) <BR>
     * ・債券出金情報.受渡代金（円貨） = <BR>
     * 　@　@new Double(引数.債券注文単位Params.受渡代金（円貨）.doubleValue()) <BR>
     * ・債券出金情報.受渡代金（外貨） = <BR>
     * 　@　@new Double(引数.債券注文単位Params.受渡代金（外貨）.doubleValue())<BR>
     * ・債券出金情報.債券注文単位ID = new Long(引数.債券注文単位Params.注文単位ID)<BR>
     * <BR>
     *  ３）債券出金情報オブジェクトを返却する。<BR>
     * <BR>
     * @@param l_bondOrderUnitParams - 債券注文単位Paramsオブジェクト<BR>
     * @@return WEB3AioBondOnPaymentInfo
     */
    public WEB3AioBondOnPaymentInfo createForeignOrder(BondOrderUnitParams l_bondOrderUnitParams)
    {
        final String STR_METHOD_NAME = "createForeignOrder(BondOrderUnitParams l_bondOrderUnitParams)";
        log.entering(STR_METHOD_NAME);

        //１）債券出金情報インスタンスを生成する。
        WEB3AioBondOnPaymentInfo l_bondOnPaymentInfo = new WEB3AioBondOnPaymentInfo();

        //２）以下のとおりに債券出金情報のプロパティをセットする。
        //* ・債券出金情報.口座ID = new Long(引数.債券注文単位Params.口座ID)
        l_bondOnPaymentInfo.setAccountId(new Long(l_bondOrderUnitParams.getAccountId()));

        //* ・債券出金情報.部店ID = new Long(引数.債券注文単位Params.部店ID)
        l_bondOnPaymentInfo.setBranchId(new Long(l_bondOrderUnitParams.getBranchId()));

        //* ・債券出金情報.受渡日 = new Timestamp(引数.債券注文単位Params.受渡日.getTime())
        l_bondOnPaymentInfo.setDeliveryDate(new Timestamp(
            l_bondOrderUnitParams.getDeliveryDate().getTime()));

        //* ・債券出金情報.通貨コード = new String(引数.債券注文単位Params.通貨コード)
        l_bondOnPaymentInfo.setCurrencyCode(l_bondOrderUnitParams.getCurrencyCode());

        //* ・債券出金情報.決済区分 = new String(引数.債券注文単位Params.決済区分)
        l_bondOnPaymentInfo.setSettlementDiv(l_bondOrderUnitParams.getSettlementDiv());

        //* ・債券出金情報.受渡代金（円貨） =
        //* 　@　@new Double(引数.債券注文単位Params.受渡代金（円貨）.doubleValue())
        Double l_estimatedPrice = null;
        if (!l_bondOrderUnitParams.getEstimatedPriceIsNull())
        {
            l_estimatedPrice = new Double(l_bondOrderUnitParams.getEstimatedPrice());
        }
        l_bondOnPaymentInfo.setEstimatedPrice(l_estimatedPrice);

        //* ・債券出金情報.受渡代金（外貨） =
        //* 　@　@new Double(引数.債券注文単位Params.受渡代金（外貨）.doubleValue())
        Double l_foreignEstimatedPrice = null;
        if (!l_bondOrderUnitParams.getForeignEstimatedPriceIsNull())
        {
            l_foreignEstimatedPrice = new Double(l_bondOrderUnitParams.getForeignEstimatedPrice());
        }
        l_bondOnPaymentInfo.setForeignEstimatedPrice(l_foreignEstimatedPrice);
        
        //・債券出金情報.債券注文単位ID = new Long(引数.債券注文単位Params.注文単位ID)
        l_bondOnPaymentInfo.setBondOrderUnitId(new Long(l_bondOrderUnitParams.getOrderUnitId()));

        //３）債券出金情報オブジェクトを返却する。
        log.exiting(STR_METHOD_NAME);
        return l_bondOnPaymentInfo;
    }

    /**
     * (calc円貨注文)<BR>
     * 円貨注文をGrossする。<BR>
     * <BR>
     * １）以下のとおりに、引数.債券注文単位Paramsのプロパティを <BR>
     * 　@ 引数.債券出金情報のプロパティに加算する。 <BR>
     * <BR>
     * ・引数.債券出金情報.受渡代金（円貨） += 引数.債券注文単位Params.受渡代金（円貨）<BR>
     * <BR>
     * @@param l_bondOnPaymentInfo - (債券出金情報)<BR>
     * 債券出金情報オブジェクト<BR>
     * @@param l_bondOrderUnitParams - (債券注文単位Params)<BR>
     * 債券注文単位Paramsオブジェクト<BR>
     */
    public void calcJapaneseOrder(WEB3AioBondOnPaymentInfo l_bondOnPaymentInfo,
        BondOrderUnitParams l_bondOrderUnitParams)
    {
        final String STR_METHOD_NAME = "calcJapaneseOrder("
            + "WEB3AioBondOnPaymentInfo, BondOrderUnitParams)";
        log.entering(STR_METHOD_NAME);

        //・引数.債券出金情報.受渡代金（円貨） += 引数.債券注文単位Params.受渡代金（円貨）
        Double l_estimatedPrice = null;
        double l_dblEstimatedPrice = 0.0D;
        if (l_bondOnPaymentInfo.getEstimatedPrice() != null)
        {
            l_dblEstimatedPrice = l_bondOnPaymentInfo.getEstimatedPrice().doubleValue();
        }
        l_estimatedPrice = new Double(l_dblEstimatedPrice
            + l_bondOrderUnitParams.getEstimatedPrice());
        l_bondOnPaymentInfo.setEstimatedPrice(l_estimatedPrice);

        log.exiting(STR_METHOD_NAME);
    }
}
@
