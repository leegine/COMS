head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.29.58;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSLRepayApplyServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 証券担保ローン返済申込サービスImpl(WEB3AioSLRepayApplyServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/13 柴双紅 (中訊) 仕様変更 モデルNo.757,モデルNo.775
Revision History : 2007/10/16 柴双紅 (中訊) 仕様変更 モデルNo.806
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AssetTransferTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;

import webbroker3.aio.WEB3AioNewOrderSpec;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.WEB3AioSLRepayUpdateInterceptor;
import webbroker3.aio.message.WEB3SLRepayApplyCompleteRequest;
import webbroker3.aio.message.WEB3SLRepayApplyCompleteResponse;
import webbroker3.aio.message.WEB3SLRepayApplyConfirmRequest;
import webbroker3.aio.message.WEB3SLRepayApplyConfirmResponse;
import webbroker3.aio.service.delegate.WEB3AioSLRepayApplyService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (証券担保ローン返済申込サービスImpl)<BR>
 * 証券担保ローン返済申込サービス実装クラス<BR>
 * <BR>
 * @@author 柴双紅
 * @@version 1.0
 */
public class WEB3AioSLRepayApplyServiceImpl extends WEB3ClientRequestService implements WEB3AioSLRepayApplyService
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSLRepayApplyServiceImpl.class);

    /**
     * @@roseuid 46E8908303C5
     */
    public WEB3AioSLRepayApplyServiceImpl()
    {

    }

    /**
     * 証券担保ローン返済申込サービス処理を実施する。 <BR>
     * <BR>
     * リクエストデータの型によりvalidate注文()、またはsubmit注文()メソッドをコールする。<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3SLRepayApplyConfirmRequest)
        {
            l_response =
                this.validateOrder(
                    (WEB3SLRepayApplyConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3SLRepayApplyCompleteRequest)
        {
            l_response =
                this.submitOrder(
                    (WEB3SLRepayApplyCompleteRequest)l_request);
        }
        else
        {
            log.debug("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータタイプ不正。");
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate注文)<BR>
     * 証券担保ローン返済申込の発注審査を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（証券担保ローン返済申込）validate注文」参照。<BR>
     * <BR>
     * ========================================================<BR>
     * シーケンス図:（証券担保ローン返済申込）validate注文<BR>
     * 具体位置：is証券担保ローン口座開設( )<BR>
     * 　@戻り値 == false の場合、例外をスローする。<BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag: BUSINESS_ERROR_02914<BR>
     * ========================================================<BR>
     * <BR>
     * ========================================================<BR>
     * シーケンス図:（証券担保ローン返済申込）validate注文<BR>
     * 具体位置：get直近振込日(SubAccount, Date)<BR>
     * 　@get直近振込日()の戻り値 > リクエストデータ.返済予定日 の場合、<BR>
     * 　@　@　@例外をスローする。<BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag: BUSINESS_ERROR_02915<BR>
     * ========================================================<BR>
     * <BR>
     * ========================================================<BR>
     * シーケンス図:（証券担保ローン返済申込）validate注文<BR>
     * 具体位置：validate取引余力(補助口座 : 補助口座, 注文内容インタセプタ : Object[],<BR>
     * 　@注文内容 : Object[], 注文種別 : OrderTypeEnum, 余力更新フラグ : boolean)<BR>
     * 　@　@戻り値の取引余力結果.判定フラグ == false の場合、例外をスローする。<BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag: BUSINESS_ERROR_01306<BR>
     * ========================================================<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3SLRepayApplyConfirmResponse
     * @@throws WEB3BaseException
     */
    protected WEB3SLRepayApplyConfirmResponse validateOrder(WEB3SLRepayApplyConfirmRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateOrder(WEB3SLRepayApplyConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //validate( )
        l_request.validate();

        //get補助口座(補助口座タイプ : SubAccountTypeEnum)
        //[引数]
        //補助口座タイプ： 1（預り金口座）
        SubAccount l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

        //validate注文(SubAccount)
        //[引数]
        //補助口座： get補助口座()の戻り値
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3AioOrderManager l_aioOrderManager =
            (WEB3AioOrderManager)l_finApp.getTradingModule(
                ProductTypeEnum.AIO).getOrderManager();
        l_aioOrderManager.validateOrder(l_subAccount);

        //getMainAccount( )
        WEB3GentradeMainAccount l_gentradeMainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();

        //is証券担保ローン口座開設( )
        boolean l_blnIsSecuredLoanAccountOpen = l_gentradeMainAccount.isSecuredLoanAccountOpen();
        if (!l_blnIsSecuredLoanAccountOpen)
        {
            //戻り値 == false の場合、例外をスローする。
            log.debug("証券担保ローン口座が未開設です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02914,
                this.getClass().getName() + STR_METHOD_NAME,
                "証券担保ローン口座が未開設です。");
        }

        //get発注日( )
        Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

        //get直近振込日(SubAccount, Date)
        //[引数]
        //補助口座： get補助口座()の戻り値
        //発注日： get発注日()の戻り値
        Date l_datClosestTransferDate =
            l_aioOrderManager.getClosestTransferDate(
                l_subAccount,
                l_datOrderBizDate);

        if (WEB3DateUtility.compare(l_datClosestTransferDate, l_request.repayScheduledDate) > 0)
        {
            //get直近振込日()の戻り値 > リクエストデータ.返済予定日 の場合、例外をスローする。
            log.debug("直近振込日は返済予定日より大きいです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02915,
                this.getClass().getName() + STR_METHOD_NAME,
                "直近振込日は返済予定日より大きいです。");
        }

        //validateSL返済重複注文(SubAccount, Date)
        //[引数]
        //補助口座： get補助口座()の戻り値
        //受渡日： リクエストデータ.返済予定日
        l_aioOrderManager.validateSLRepayDuplicateOrder(l_subAccount, l_request.repayScheduledDate);

        //get代理入力者( )
        Trader l_trader = this.getTrader();

        //get商品ID(Institution)
        //[引数]
        //証券会社： 補助口座.getInstitution()の戻り値
        long l_lngProductId = l_aioOrderManager.getProductId(l_subAccount.getInstitution());

        //入出金注文内容(Trader, OrderTypeEnum, AssetTransferTypeEnum, long, double, String, Date, String, Long)
        //[引数]
        //代理入力者： get代理入力者()の戻り値
        //注文種別： 1020（振替注文（預かり金からオリックスクレジット））
        //振替タイプ： 2（出金）
        //商品ID： get商品ID()の戻り値
        //金額： リクエストデータ.返済額
        //記述： null
        //振替予定日： リクエストデータ.返済予定日
        //決済機@関ID： null
        //注文ID： null
        WEB3AioNewOrderSpec l_aioNewOrderSpec =
            new WEB3AioNewOrderSpec(
                l_trader,
                OrderTypeEnum.TO_ORIX_CREDIT,
                AssetTransferTypeEnum.CASH_OUT,
                l_lngProductId,
                Double.parseDouble(l_request.repayAmt),
                null,
                l_request.repayScheduledDate,
                null,
                null);

        //証券担保ローン返済更新インタセプタ(入出金注文内容)
        //[引数]
        //入出金注文内容： 入出金注文内容
        WEB3AioSLRepayUpdateInterceptor l_slrepayUpdateInterceptor =
            new WEB3AioSLRepayUpdateInterceptor(l_aioNewOrderSpec);

        //プロパティセット
        //インタセプタ.発注日 = （以下のとおり）
        //    get発注日()の戻り値 = 入出金注文内容.振替予定日 or
        //    get発注日()の戻り値の翌営業日 = 入出金注文内容.振替予定日 の場合、get発注日()の戻り値
        //    それ以外の場合、入出金注文内容.振替予定日の前営業日
        Date l_datEstimatedTransferDate = l_aioNewOrderSpec.getEstimatedTransferDate();
        WEB3GentradeBizDate l_gentradeOrderBizDate =
            new WEB3GentradeBizDate(new Timestamp(l_datOrderBizDate.getTime()));
        if (WEB3DateUtility.compare(l_datOrderBizDate, l_datEstimatedTransferDate) == 0
            || WEB3DateUtility.compare(l_gentradeOrderBizDate.roll(1), l_datEstimatedTransferDate) == 0)
        {
            l_slrepayUpdateInterceptor.setOrderBizDate(l_datOrderBizDate);
        }
        else
        {
            WEB3GentradeBizDate l_gentradeTransferDate =
                new WEB3GentradeBizDate(new Timestamp(l_datEstimatedTransferDate.getTime()));
            l_slrepayUpdateInterceptor.setOrderBizDate(l_gentradeTransferDate.roll(-1));
        }

        //インタセプタ.受渡日 = 入出金注文内容.振替予定日
        l_slrepayUpdateInterceptor.setDeliveryDate(l_datEstimatedTransferDate);

        //validate取引余力(補助口座 : 補助口座, 注文内容インタセプタ : Object[],
        //   注文内容 : Object[], 注文種別 : OrderTypeEnum, 余力更新フラグ : boolean)
        //[引数]
        //補助口座：　@補助口座オブジェクト
        //注文内容インタセプタ： 証券担保ローン返済更新インタセプタの配列
        //注文内容： 入出金注文内容の配列
        //注文種別： 1020（振替注文（預かり金からオリックスクレジット））
        //余力更新フラグ： false
        WEB3TPTradingPowerService l_tradingPowerService =
            (WEB3TPTradingPowerService)Services.getService(
                WEB3TPTradingPowerService.class);
        WEB3TPTradingPowerResult l_tradingPowerResult =
            l_tradingPowerService.validateTradingPower(
                (WEB3GentradeSubAccount)l_subAccount,
                new Object[]{l_slrepayUpdateInterceptor},
                new Object[]{l_aioNewOrderSpec},
                OrderTypeEnum.TO_ORIX_CREDIT,
                false);
        if (!l_tradingPowerResult.isResultFlg())
        {
            //戻り値の取引余力結果.判定フラグ == false の場合、例外をスローする。
            log.debug("取引余力チェックエラー。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01306,
                this.getClass().getName() + STR_METHOD_NAME,
                "取引余力チェックエラー。");
        }

        //createResponse( )
        WEB3SLRepayApplyConfirmResponse l_confirmResponse =
            (WEB3SLRepayApplyConfirmResponse)l_request.createResponse();

        //プロパティセット
        //レスポンス.返済額 = リクエストデータ.返済額
        l_confirmResponse.repayAmt = l_request.repayAmt;

        //レスポンス.返済予定日 = リクエストデータ.返済予定日
        l_confirmResponse.repayScheduledDate = l_request.repayScheduledDate;

        log.exiting(STR_METHOD_NAME);
        return l_confirmResponse;
    }

    /**
     * (submit注文)<BR>
     * 証券担保ローン返済申込の登録を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（証券担保ローン返済申込）submit注文」参照。<BR>
     * <BR>
     * ========================================================<BR>
     * シーケンス図:（証券担保ローン返済申込）submit注文<BR>
     * 具体位置：is証券担保ローン口座開設( )<BR>
     * 　@戻り値 == false の場合、例外をスローする。<BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag: BUSINESS_ERROR_02914<BR>
     * ========================================================<BR>
     * <BR>
     * ========================================================<BR>
     * シーケンス図:（証券担保ローン返済申込）submit注文<BR>
     * 具体位置：get直近振込日(SubAccount, Date)<BR>
     * 　@get直近振込日()の戻り値 > リクエストデータ.返済予定日 の場合、<BR>
     * 　@　@例外をスローする。<BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag: BUSINESS_ERROR_02915<BR>
     * ========================================================<BR>
     * <BR>
     * ========================================================<BR>
     * シーケンス図:（証券担保ローン返済申込）submit注文<BR>
     * 具体位置：validate取引余力(補助口座 : 補助口座, 注文内容インタセプタ : Object[],<BR>
     * 　@注文内容 : Object[], 注文種別 : OrderTypeEnum, 余力更新フラグ : boolean)<BR>
     * 　@　@戻り値の取引余力結果.判定フラグ == false の場合、例外をスローする。<BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag: BUSINESS_ERROR_01306<BR>
     * ========================================================<BR>
     * <BR>
     * ========================================================<BR>
     * シーケンス図:（証券担保ローン返済申込）submit注文<BR>
     * 具体位置：getOrderUnits(注文ＩＤ : long)<BR>
     * 　@取得できなかった場合、例外をスローする。<BR>
     * 　@　@　@class: WEB3SystemLayerException<BR>
     * 　@　@　@　@tag: SYSTEM_ERROR_80005<BR>
     * ========================================================<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3SLRepayApplyCompleteResponse
     * @@throws WEB3BaseException
     */
    protected WEB3SLRepayApplyCompleteResponse submitOrder(WEB3SLRepayApplyCompleteRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitOrder(WEB3SLRepayApplyCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //validate( )
        l_request.validate();

        //get補助口座(補助口座タイプ : SubAccountTypeEnum)
        //[引数]
        //補助口座タイプ： 1（預り金口座）
        SubAccount l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

        //validate注文(SubAccount)
        //[引数]
        //補助口座： get補助口座()の戻り値
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3AioOrderManager l_aioOrderManager =
            (WEB3AioOrderManager)l_finApp.getTradingModule(
                ProductTypeEnum.AIO).getOrderManager();
        l_aioOrderManager.validateOrder(l_subAccount);

        //getMainAccount( )
        WEB3GentradeMainAccount l_gentradeMainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();

        //is証券担保ローン口座開設( )
        boolean l_blnIsSecuredLoanAccountOpen = l_gentradeMainAccount.isSecuredLoanAccountOpen();
        if (!l_blnIsSecuredLoanAccountOpen)
        {
            //戻り値 == false の場合、例外をスローする。
            log.debug("証券担保ローン口座が未開設です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02914,
                this.getClass().getName() + STR_METHOD_NAME,
                "証券担保ローン口座が未開設です。");
        }

        //get発注日( )
        Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

        //get直近振込日(SubAccount, Date)
        //[引数]
        //補助口座： get補助口座()の戻り値
        //発注日： get発注日()の戻り値
        Date l_datClosestTransferDate =
            l_aioOrderManager.getClosestTransferDate(
                l_subAccount,
                l_datOrderBizDate);

        if (WEB3DateUtility.compare(l_datClosestTransferDate, l_request.repayScheduledDate) > 0)
        {
            //get直近振込日()の戻り値 > リクエストデータ.返済予定日 の場合、例外をスローする。
            log.debug("直近振込日は返済予定日より大きいです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02915,
                this.getClass().getName() + STR_METHOD_NAME,
                "直近振込日は返済予定日より大きいです。");
        }

        //validateSL返済重複注文(SubAccount, Date)
        //[引数]
        //補助口座： get補助口座()の戻り値
        //受渡日： リクエストデータ.返済予定日
        l_aioOrderManager.validateSLRepayDuplicateOrder(l_subAccount, l_request.repayScheduledDate);

        //get代理入力者( )
        Trader l_trader = this.getTrader();

        //get商品ID(Institution)
        //[引数]
        //証券会社： 補助口座.getInstitution()の戻り値
        long l_lngProductId = l_aioOrderManager.getProductId(l_subAccount.getInstitution());

        //入出金注文内容(Trader, OrderTypeEnum, AssetTransferTypeEnum, long, double, String, Date, String, Long)
        //[引数]
        //代理入力者： get代理入力者()の戻り値
        //注文種別： 1020（振替注文（預かり金からオリックスクレジット））
        //振替タイプ： 2（出金）
        //商品ID： get商品ID()の戻り値
        //金額： リクエストデータ.返済額
        //記述： null
        //振替予定日： リクエストデータ.返済予定日
        //決済機@関ID： null
        //注文ID： null
        WEB3AioNewOrderSpec l_aioNewOrderSpec =
            new WEB3AioNewOrderSpec(
                l_trader,
                OrderTypeEnum.TO_ORIX_CREDIT,
                AssetTransferTypeEnum.CASH_OUT,
                l_lngProductId,
                Double.parseDouble(l_request.repayAmt),
                null,
                l_request.repayScheduledDate,
                null,
                null);

        //証券担保ローン返済更新インタセプタ(入出金注文内容)
        //[引数]
        //入出金注文内容： 入出金注文内容
        WEB3AioSLRepayUpdateInterceptor l_slrepayUpdateInterceptor =
            new WEB3AioSLRepayUpdateInterceptor(l_aioNewOrderSpec);

        //プロパティセット
        //インタセプタ.発注日 = （以下のとおり）
        //    get発注日()の戻り値 = 入出金注文内容.振替予定日 or
        //    get発注日()の戻り値の翌営業日 = 入出金注文内容.振替予定日 の場合、get発注日()の戻り値
        //    それ以外の場合、入出金注文内容.振替予定日の前営業日
        Date l_datEstimatedTransferDate = l_aioNewOrderSpec.getEstimatedTransferDate();
        WEB3GentradeBizDate l_gentradeOrderBizDate =
            new WEB3GentradeBizDate(new Timestamp(l_datOrderBizDate.getTime()));
        if (WEB3DateUtility.compare(l_datOrderBizDate, l_datEstimatedTransferDate) == 0
            || WEB3DateUtility.compare(l_gentradeOrderBizDate.roll(1), l_datEstimatedTransferDate) == 0)
        {
            l_slrepayUpdateInterceptor.setOrderBizDate(l_datOrderBizDate);
        }
        else
        {
            WEB3GentradeBizDate l_gentradeTransferDate =
                new WEB3GentradeBizDate(new Timestamp(l_datEstimatedTransferDate.getTime()));
            l_slrepayUpdateInterceptor.setOrderBizDate(l_gentradeTransferDate.roll(-1));
        }

        //インタセプタ.受渡日 = 入出金注文内容.振替予定日
        l_slrepayUpdateInterceptor.setDeliveryDate(l_datEstimatedTransferDate);

        //validate取引余力(補助口座 : 補助口座, 注文内容インタセプタ : Object[],
        //  注文内容 : Object[], 注文種別 : OrderTypeEnum, 余力更新フラグ : boolean)
        //[引数]
        //補助口座：　@補助口座オブジェクト
        //注文内容インタセプタ： 証券担保ローン返済更新インタセプタの配列
        //注文内容： 入出金注文内容の配列
        //注文種別： 1020（振替注文（預かり金からオリックスクレジット））
        //余力更新フラグ： true
        WEB3TPTradingPowerService l_tradingPowerService =
            (WEB3TPTradingPowerService)Services.getService(
                WEB3TPTradingPowerService.class);
        WEB3TPTradingPowerResult l_tradingPowerResult =
            l_tradingPowerService.validateTradingPower(
                (WEB3GentradeSubAccount)l_subAccount,
                new Object[]{l_slrepayUpdateInterceptor},
                new Object[]{l_aioNewOrderSpec},
                OrderTypeEnum.TO_ORIX_CREDIT,
                true);
        if (!l_tradingPowerResult.isResultFlg())
        {
            //戻り値の取引余力結果.判定フラグ == false の場合、例外をスローする。
            log.debug("取引余力チェックエラー。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01306,
                this.getClass().getName() + STR_METHOD_NAME,
                "取引余力チェックエラー。");
        }

        //createNewOrderId( )
        long l_lngNewOrderId = l_aioOrderManager.createNewOrderId();

        //setThreadLocalPersistenceEventInterceptor(
        //証券担保ローン返済更新インタセプタ : AioOrderManagerPersistenceEventInterceptor)
        //[引数]
        // 証券担保ローン返済更新インタセプタ：　@生成した証券担保ローン返済更新インタセプタ
        l_aioOrderManager.setThreadLocalPersistenceEventInterceptor(l_slrepayUpdateInterceptor);

        //submitNewOrder(補助口座 : SubAccount, 商品タイプ : ProductTypeEnum,
        //入出金注文内容 : NewOrderSpec, 注文ＩＤ : long, パスワード : String, isSkip発注審査 : boolean)
        //[引数]
        //補助口座：　@補助口座オブジェクト
        //商品タイプ：　@5（現金）
        //入出金注文内容：　@入出金注文内容オブジェクト
        //注文ＩＤ：　@createNewOrderId( )の戻り値
        //パスワード：　@リクエストデータ.暗証番号
        //isSkip発注審査：　@true
        OrderSubmissionResult l_submissionResult =
            l_aioOrderManager.submitNewOrder(
                l_subAccount,
                ProductTypeEnum.CASH,
                l_aioNewOrderSpec,
                l_lngNewOrderId,
                l_request.password,
                true);

        if (l_submissionResult.getProcessingResult().isFailedResult())
        {
            log.debug("注文登録処理失敗である");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                l_submissionResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME,
                "注文登録処理失敗である");
        }

        //getOrderUnits(注文ＩＤ : long)
        //[引数]
        //注文ＩＤ：　@createNewOrderId( )の戻り値
        OrderUnit[] l_orderUnits = l_aioOrderManager.getOrderUnits(l_lngNewOrderId);
        if (l_orderUnits.length <= 0)
        {
            //取得できなかった場合、例外をスローする。
            log.debug("テーブルに該当するデータがありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                "テーブルに該当するデータがありません。");
        }
        AioOrderUnit l_aioOrderUnit = (AioOrderUnit)l_orderUnits[0];
        AioOrderUnitRow l_aioOrderUnitRow =
            (AioOrderUnitRow)l_aioOrderUnit.getDataSourceObject();

        //createResponse( )
        WEB3SLRepayApplyCompleteResponse l_completeResponse =
            (WEB3SLRepayApplyCompleteResponse)l_request.createResponse();

        //プロパティセット
        //レスポンス.返済額 = リクエストデータ.返済額
        l_completeResponse.repayAmt = l_request.repayAmt;

        //レスポンス.返済予定日 = リクエストデータ.返済予定日
        l_completeResponse.repayScheduledDate = l_request.repayScheduledDate;

        //レスポンス.更新時間 = 注文単位.更新日付
        l_completeResponse.lastUpdatedTimestamp =
            l_aioOrderUnitRow.getLastUpdatedTimestamp();

        //レスポンス.注文ID = createNewOrderId( )の戻り値 の戻り値
        l_completeResponse.orderId = l_lngNewOrderId + "";

        log.exiting(STR_METHOD_NAME);
        return l_completeResponse;
    }
}
@
