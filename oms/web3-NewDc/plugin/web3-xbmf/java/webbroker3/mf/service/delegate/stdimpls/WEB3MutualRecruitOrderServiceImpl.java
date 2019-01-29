head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.42.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualRecruitOrderServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託募集注文サービス実装クラス(WEB3MutualRecruitOrderServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/09/29 黄建 (中訊) 新規作成
Revesion History : 2006/06/26 張秋穎 (中訊) 仕様変更(モデル)：419
Revesion History : 2006/09/12 趙林鵬 (中訊) 仕様変更・モデル489、ＤＢ更新仕様078、079
Revesion History : 2006/10/12 趙林鵬 (中訊) 仕様変更・モデル503、ＤＢ更新仕様083
Revesion History : 2007/04/09 張騰宇 (中訊) モデル557　@実装005
*/

package webbroker3.mf.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinObjectManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.common.define.WEB3BuySellSettlementDivDef;
import webbroker3.common.define.WEB3BuySellSwtSpecityDivDef;
import webbroker3.common.define.WEB3CommissionDivDef;
import webbroker3.common.define.WEB3MfRecruitMqSendDivDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.BranchPreferencesDao;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.gentrade.data.OrderUnitIntroduceDivParams;
import webbroker3.mf.WEB3MutualClientRequestService;
import webbroker3.mf.WEB3MutualFundEstimatedPrice;
import webbroker3.mf.WEB3MutualFundNewOrderApplyConfirmInterceptor;
import webbroker3.mf.WEB3MutualFundNewOrderSpec;
import webbroker3.mf.WEB3MutualFundOrderManager;
import webbroker3.mf.WEB3MutualFundProduct;
import webbroker3.mf.WEB3MutualFundProductManager;
import webbroker3.mf.WEB3MutualFundTradingTimeManagement;
import webbroker3.mf.define.WEB3MFEstimatedPriceCurrencyCodeDef;
import webbroker3.mf.define.WEB3MFProcessDivDef;
import webbroker3.mf.define.WEB3ProcessDivDef;
import webbroker3.mf.message.WEB3MutualApplyCompleteRequest;
import webbroker3.mf.message.WEB3MutualApplyCompleteResponse;
import webbroker3.mf.message.WEB3MutualApplyConfirmRequest;
import webbroker3.mf.message.WEB3MutualApplyConfirmResponse;
import webbroker3.mf.service.delegate.WEB3MutualRecruitOrderService;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * 投資信託募集注文サービス実装クラス
 *
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3MutualRecruitOrderServiceImpl
        extends WEB3MutualClientRequestService implements WEB3MutualRecruitOrderService
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualRecruitOrderServiceImpl.class);

    /**
     * リクエストデータによって、以下のいずれかのメソッドをコールする。<BR>
     * 　@validate募集注文() <BR>
     * 　@submit募集注文() <BR>
     * @@param l_request - (リクエストデータ)
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4153F8F00140
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("パラメータNull。");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3MutualApplyConfirmRequest)
        {
            //validate募集注文
            l_response =
                this.validateOrder((WEB3MutualApplyConfirmRequest) l_request);
        }
        else if (l_request instanceof WEB3MutualApplyCompleteRequest)
        {
            //submit募集注文
            l_response =
                this.submitOrder((WEB3MutualApplyCompleteRequest) l_request);
        }
        else
        {
            log.debug(" パラメータ値が不正する！");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate募集注文)<BR>
     * <BR>
     * 投資信託募集注文審査を行う。<BR>
     * シーケンス図 <BR>
     *「(投信)募集注文審査」参照。<BR>
     *<BR>
     *==========================================================<BR>
     * シーケンス図「(投信)募集注文審査」: <BR>
     *    1.13.1((is特定口座開設 特定口座チェック<BR>
     *    －is特定口座開設()＝falseの場合、例外をスローする<BR>
     *    class: WEB3BusinessLayerException<BR>
     *    tag:   BUSINESS_ERROR_02096<BR>
     *==========================================================<BR>
     *<BR>
     *==========================================================<BR>
     * シーケンス図「(投信)募集注文審査」: <BR>
     *    1.22(is判定フラグ( )<BR>
     *    is判定フラグ()の戻り値がfalseの場合、[取引余力チェックエラー]として例外をスローする。<BR>
     *    class: WEB3BusinessLayerException<BR>
     *    tag:   BUSINESS_ERROR_01306<BR>
     * ==========================================================<BR>
     * @@param l_request - (投信募集注文確認リクエスト)
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4153F8F00140
     */
    public WEB3MutualApplyConfirmResponse validateOrder(WEB3MutualApplyConfirmRequest l_request)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "validateOrder(WEB3MutualApplyConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);

        //1.1 validate( )
        l_request.validate();

        //1.2 get補助口座( )
        WEB3GentradeSubAccount l_subAccount =
            (WEB3GentradeSubAccount) this.getSubAccount();

        //1.3 get投信銘柄(Institution, String)
        Institution l_institution = l_subAccount.getInstitution();
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3MutualFundProductManager l_mutualFundProductManager =
            (WEB3MutualFundProductManager) l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getProductManager();
        WEB3MutualFundProduct l_mutualFundProduct = null;
        try
        {
            l_mutualFundProduct =
                (WEB3MutualFundProduct) l_mutualFundProductManager.getMutualFundProduct(
                    l_institution,
                    l_request.mutualProductCode);
        }
        //拡張投信銘柄が取得出来ない場合、例外をスローする
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundException__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00377,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //1.4 getCommonOrderValidator( )
        WEB3GentradeOrderValidator l_orderValidator =
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();

        //1.5 validate取引可能顧客(顧客, Timestamp)
        WEB3GentradeMainAccount l_genMainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        Timestamp l_tsOrderedDate = new Timestamp(l_request.orderedDate.getTime());

        OrderValidationResult l_validationResult =
            l_orderValidator.validateAccountForTrading(l_genMainAccount, l_tsOrderedDate);

        if (l_validationResult.getProcessingResult().isFailedResult())
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_validationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //1.6 validate注文受付可能( )
        WEB3MutualFundTradingTimeManagement.validateOrderAccept();

        //1.7 reset銘柄コード(String)
        WEB3MutualFundTradingTimeManagement.resetProductCode(
            l_request.mutualProductCode);

        //1.8 setTimestamp( )
        WEB3MutualFundTradingTimeManagement.setTimestamp();

        //1.9 validate新規注文
        WEB3MutualFundOrderManager l_mutualFundOrderManager =
            (WEB3MutualFundOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getOrderManager();

        NewOrderValidationResult l_newOrderValidationResult =
            l_mutualFundOrderManager.validateNewOrder(
                l_subAccount,
                l_mutualFundProduct,
                Double.parseDouble(l_request.mutualOrderQuantity),
                WEB3ProcessDivDef.RECRUIT,
                null,
                l_request.specifyDiv,
                null,
                null,
                null);

        if (!l_newOrderValidationResult.getProcessingResult().isSuccessfulResult())
        {
            throw new WEB3BusinessLayerException(
                l_newOrderValidationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //1.10一括区分取得
        //    １）部店用プリファ@レンスから「投信募集注文一括送信区分」を取得する。
        //    [部店用プリファ@レンスの取得条件]
        //       部店ID ： 取得した補助口座.getMainAccount().getBranchId()
        //       プリファ@レンス名 ： mf.recruit.mq.send.div
        //       プリファ@レンス名の連番 ： 1
        //  ※レコード無しは「一括送信する」
        //
        //２）以下の条件と一致する場合は true を、それ以外の場合は false を一括区分へセットする。
        //    部店用プリファ@レンス.投信募集注文一括送信区分 == 「一括送信する」
        boolean l_blnMfRecruitMqSendDiv = true;
        BranchPreferencesRow l_row = null;
        try
        {
            l_row = BranchPreferencesDao.findRowByBranchIdNameNameSerialNo(
                l_subAccount.getMainAccount().getBranch().getBranchId(),
                WEB3BranchPreferencesNameDef.MF_RECRUIT_MQ_SEND_DIV,
                1);
        }
        catch (DataFindException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        if (l_row == null || WEB3MfRecruitMqSendDivDef.DEFAULT.equals(l_row.getValue()))
        {
            l_blnMfRecruitMqSendDiv = true;
        }
        else
        {
            l_blnMfRecruitMqSendDiv = false;
        }

        //1.11 get発注日(Date, OrderTypeEnum, boolean)
        //発注日：　@リクエストデータ.発注日
        //注文種別：　@OrderTypeEnum.投資信託募集注文
        //一括区分：　@取得した一括区分
        Date l_datOrderBizDate =
            WEB3MutualFundTradingTimeManagement.getOrderBizDate(
                l_request.orderedDate,
                OrderTypeEnum.MF_RECRUIT,
                l_blnMfRecruitMqSendDiv);

        //1.12 get募集終了日
        Timestamp l_tsRecruitEndDate = l_mutualFundProduct.getRecruitEndDate();

        //1.13 get募集終了日（SONAR）( )
        Timestamp l_tsApplyAbleEndDateSonar = l_mutualFundProduct.getApplyAbleEndDateSonar();

        //1.14 get顧客()
        //拡張アカウントマネージャ取得する
        WEB3GentradeAccountManager l_web3GentradeAccountManager =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        try
        {
            //－拡張アカウントマネージャ.get顧客()をコールし、顧客オブジェクトを取得する
            l_genMainAccount =
                (WEB3GentradeMainAccount) l_web3GentradeAccountManager.getMainAccount(
                    l_subAccount.getAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundException__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //1.15 if リクエストデータ.口座区分 == ”1:特定”
        if (WEB3TaxTypeSpecialDef.SPECIAL.equals(l_request.taxType))
        {
            //1.15.1 is特定口座開設
            //特定口座チェック is特定口座開設()がfalseの場合、例外をスローする
            boolean l_blnIsSpecialAccountEstablished =
                l_genMainAccount.isSpecialAccountEstablished(
                    l_tsRecruitEndDate, l_subAccount);

            if (!l_blnIsSpecialAccountEstablished)
            {
                log.debug("特定口座未開設エラー。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02096,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "特定口座未開設エラー。");
            }
        }

        //1.16 calc概算受渡代金
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);
        String l_strOrderChanel =
            l_opLoginSec.getSessionProperty(WEB3SessionAttributeDef.ORDER_CHANNEL);

        WEB3MutualFundEstimatedPrice l_mutualFundEstimatedPrice =
            l_mutualFundOrderManager.calcEstimateDeliveryAmount(
                l_subAccount,
                l_mutualFundProduct,
                null,
                WEB3MFProcessDivDef.RECRUIT,
                Double.parseDouble(l_request.mutualOrderQuantity),
                l_request.specifyDiv,
                l_request.settleDiv,
                null,
                l_request.taxType,
                l_strOrderChanel,
                l_request.orderedDate);

        //1.17 投信新規注文確定インタセプタ（募集）
        WEB3MutualFundNewOrderApplyConfirmInterceptor l_mfNewOrderApplyConfirmInterceptor =
            new WEB3MutualFundNewOrderApplyConfirmInterceptor();

        //1.18 投信新規注文確定インタセプタ（募集）へのプロパティセット
        //受渡日：
        //取得した一括区分==trueなら取得した募集終了日(SONAR)、
        //取得した一括区分==falseなら取得した募集終了日
        if (l_blnMfRecruitMqSendDiv)
        {
            l_mfNewOrderApplyConfirmInterceptor.setDeliveryDate(l_tsApplyAbleEndDateSonar);
        }
        else
        {
            l_mfNewOrderApplyConfirmInterceptor.setDeliveryDate(l_tsRecruitEndDate);
        }

        //注文チャネル：this.getログインチャネル()の戻り値
        l_mfNewOrderApplyConfirmInterceptor.setOrderChannel(this.getLoginChannel());

        //計算基準価額：取得した拡張投信銘柄オブジェクト.get募集価額()の戻り値
        l_mfNewOrderApplyConfirmInterceptor.setConstantValue(
            l_mutualFundProduct.getRecruitConstantValue());

        //計算基準価額（乗換先）：Double.NaN
        l_mfNewOrderApplyConfirmInterceptor.setSwitchingConstantValue(Double.NaN);

        //基準価額適用日：null
        l_mfNewOrderApplyConfirmInterceptor.setConstantValueAppDate(null);

        //概算受渡代金：取得した概算受渡代金オブジェクト.get概算受渡代金()の戻り値
        l_mfNewOrderApplyConfirmInterceptor.setEstimatedPrice(
            l_mutualFundEstimatedPrice.getEstimatedPrice());

        //概算売買口数：取得した概算受渡代金オブジェクト.get概算売買口数()の戻り値
        l_mfNewOrderApplyConfirmInterceptor.setEstimatedQty(
            l_mutualFundEstimatedPrice.getEstimatedQty());

        //概算買付口数（乗換先）：Double.NaN
        l_mfNewOrderApplyConfirmInterceptor.setSwitchingEstimatedQty(Double.NaN);

        //税区分（乗換先）：null
        l_mfNewOrderApplyConfirmInterceptor.setSwitchingSubjectTaxDivision(null);

        //受渡方法@：null
        l_mfNewOrderApplyConfirmInterceptor.setDeliveryDiv(null);

        //投信タイプ：取得した拡張投信銘柄.getMutualFundType()の戻り値
        l_mfNewOrderApplyConfirmInterceptor.setMutualFundType(
            Integer.toString(l_mutualFundProduct.getMutualFundType().intValue()));

        //投信解約区分：null
        l_mfNewOrderApplyConfirmInterceptor.setMutualFundSellDiv(null);

        //約定日：取得した発注日
        l_mfNewOrderApplyConfirmInterceptor.setExecutionTimestamp(
            new Timestamp(l_datOrderBizDate.getTime()));

        //決済区分：リクエストデータ.決済区分
        l_mfNewOrderApplyConfirmInterceptor.setSettlementType(l_request.settleDiv);

        //無手数料区分：(*)
        //拡張投信注文マネージャ.is手数料無料顧客() == true の場合、”9：無手数料”を設定する。
        //  [is手数料無料顧客()に渡すパラメータ]
        //    顧客： 顧客オブジェクト
        //    銘柄： 取得した拡張投信銘柄オブジェクト
        if (l_mutualFundOrderManager.isCommissionFreeAccount(l_genMainAccount, l_mutualFundProduct))
        {
            l_mfNewOrderApplyConfirmInterceptor.setNoCommissionDivision(
                WEB3CommissionDivDef.NO_COMMISSION);
        }
        //取得した拡張投信銘柄.is乗換優遇対象()の戻り値が
        //    ”0：償還優遇不可”の場合、ブランクを設定する。
        //    それ以外の場合、”5：乗換優遇”を設定する。
        else if (l_mutualFundProduct.isSwitchingPerferenceObject())
        {
            l_mfNewOrderApplyConfirmInterceptor.setNoCommissionDivision(
                WEB3CommissionDivDef.SWITCHING_PREFERENCE);
        }
        else
        {
            //取得した拡張投信銘柄.is乗換優遇対象()の戻り値がfalseの場合はブランクを設定する
            l_mfNewOrderApplyConfirmInterceptor.setNoCommissionDivision(" ");
        }

        //銘柄コード（乗換先）：null
        l_mfNewOrderApplyConfirmInterceptor.setSwitchingSubjectMutualProductCode(null);

        //請求区分：null
        l_mfNewOrderApplyConfirmInterceptor.setRequestDivision(null);

        //注文経路区分：セッションから取得した同項目の値
        l_mfNewOrderApplyConfirmInterceptor.setOrderChannelDivision(
            l_opLoginSec.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV));

        //出金注文識別コード：null
        l_mfNewOrderApplyConfirmInterceptor.setPaymentOrderReqNumber(null);

        //一括区分：取得した一括区分
        l_mfNewOrderApplyConfirmInterceptor.setNorealDiv(l_blnMfRecruitMqSendDiv);

        //注文終了日：取得した募集終了日
        l_mfNewOrderApplyConfirmInterceptor.setOrderEndDate(l_tsRecruitEndDate);

        //1.19 set入金日(Timestamp)
        //取得した一括区分==trueなら
        //取得した募集終了日(SONAR)
        //取得した一括区分==falseなら
        //取得した発注日の翌営業日
        WEB3GentradeBizDate l_gentradeBizDate =
            new WEB3GentradeBizDate(new Timestamp(l_datOrderBizDate.getTime()));

        if (l_blnMfRecruitMqSendDiv)
        {
            l_mfNewOrderApplyConfirmInterceptor.setPaymentDate(l_tsApplyAbleEndDateSonar);
        }
        else
        {
            l_mfNewOrderApplyConfirmInterceptor.setPaymentDate(l_gentradeBizDate.roll(1));
        }

        //1.20 get代理入力者( )
        WEB3GentradeTrader l_trader = (WEB3GentradeTrader) this.getTrader();

        //1.21 拡張投信新規注文内容
        //[引数]
        //代理入力者： this.get代理入力者()の戻り値
        //is買付： true
        //銘柄コード： リクエストデータ.銘柄コード
        //注文数量： リクエストデータ.数量
        //注文数量タイプ：
        //(*) リクエストデータ.指定方法@が”3：金額”の場合は
        //QuantityTypeEnum.金額を指定。
        //(*) リクエストデータ.指定方法@が”4：口数”の場合は
        //QuantityTypeEnum.数量を指定。
        QuantityTypeEnum l_orderQuantityType = null;
        if (WEB3BuySellSwtSpecityDivDef.PRICE_DESIGNATE.equals(l_request.specifyDiv))
        {
            l_orderQuantityType = QuantityTypeEnum.AMOUNT;
        }
        else if (WEB3BuySellSwtSpecityDivDef.QUANTITY_DESIGNATE.equals(l_request.specifyDiv))
        {
            l_orderQuantityType = QuantityTypeEnum.QUANTITY;
        }
        //税区分：
        //(*) リクエストデータ.口座区分が”0：一般”の場合、
        //TaxTypeEnum.NORMALを設定する。
        //(*) リクエストデータ.口座区分が”1：特定”の場合、
        //TaxTypeEnum.SPECIALを設定する。
        TaxTypeEnum l_taxType = null;
        if (WEB3TaxTypeSpecialDef.NORMAL.equals(l_request.taxType))
        {
            l_taxType = TaxTypeEnum.NORMAL;
        }
        else if (WEB3TaxTypeSpecialDef.SPECIAL.equals(l_request.taxType))
        {
            l_taxType = TaxTypeEnum.SPECIAL;
        }
        WEB3MutualFundNewOrderSpec l_mutualFundNewOrderSpec =
            new WEB3MutualFundNewOrderSpec(
                l_trader,
                true,
                l_request.mutualProductCode,
                Double.parseDouble(l_request.mutualOrderQuantity),
                l_orderQuantityType,
                l_taxType);

        //1.22 validate取引余力
        WEB3TPTradingPowerService l_tpTradingPowerService =
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);

        Object[] l_arrNewOrderConfirmInterceptors =
            {l_mfNewOrderApplyConfirmInterceptor};

        Object[] l_arrNewOrderSpecs = {l_mutualFundNewOrderSpec};
        WEB3TPTradingPowerResult l_result =
            l_tpTradingPowerService.validateTradingPower(
                l_subAccount,
                l_arrNewOrderConfirmInterceptors,
                l_arrNewOrderSpecs,
                OrderTypeEnum.MF_RECRUIT,
                false);

        //1.23 is判定フラグ()＝false の場合
        //is判定フラグ()の戻り値がfalseの場合、[取引余力チェックエラー]として例外をスローする
        if(!l_result.isResultFlg())
        {
            log.debug("取引余力チェックエラー");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01306,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "取引余力チェックエラー");
        }

        //1.24 createNewOrderId( )
        long l_lngNewOrderId = l_mutualFundOrderManager.createNewOrderId();

        //1.25 createResponse( )
        WEB3MutualApplyConfirmResponse l_response =
            (WEB3MutualApplyConfirmResponse)l_request.createResponse();

        //1.26 プロパティセット
        //投信募集注文確認レスポンスに値を設定する。

        //概算受渡代金通貨コード：
        //(*)リクエストデータ.決済方法@が”1：円貨”の場合は”T0：円”を設定する。
        if (WEB3BuySellSettlementDivDef.JAPANESE_CURRENCY.equals(l_request.settleDiv))
        {
            l_response.estimatedPriceCurrencyCode =
                WEB3MFEstimatedPriceCurrencyCodeDef.T0;
        }
        else if (WEB3BuySellSettlementDivDef.FOREIGN_CURRENCY.equals(l_request.settleDiv))
        {
            //(*)リクエストデータ.決済方法@が”2：外貨”の場合は
            //取得した拡張投信銘柄オブジェクト.get通貨コード()の戻り値を設定する。
            l_response.estimatedPriceCurrencyCode = l_mutualFundProduct.getCurrencyCode();
        }

        //概算受渡代金： 取得した概算受渡代金オブジェクト.get概算受渡代金()を設定する。
        l_response.estimatedPrice =
            WEB3StringTypeUtility.formatNumber(
                l_mutualFundEstimatedPrice.getEstimatedPrice());

        //概算売買口数： 取得した概算受渡代金オブジェクト.get概算売買口数()の戻り値を設定する。
        l_response.estimatedQty =
            WEB3StringTypeUtility.formatNumber(
                l_mutualFundEstimatedPrice.getEstimatedQty());

        //注文ID：拡張投信注文マネージャ.createNewOrderId())の戻り値
        l_response.orderId = l_lngNewOrderId + "";

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit募集注文)<BR>
     * <BR>
     * 投資信託募集注文登録を行う。<BR>
     * シーケンス図 <BR>
     *「(投信)募集注文登録」参照<BR>
     *<BR>
     *==========================================================<BR>
     * シーケンス図「(投信)募集注文登録」: <BR>
     *     1.15((is特定口座開設(Date, 補助口座) 特定口座チェック<BR>
     *     －is特定口座開設()＝falseの場合、（特定口座チェックエラー）として例外をスローする<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_02096<BR>
     *==========================================================<BR>
     * <BR>
     *==========================================================<BR>
     * シーケンス図「(投信)募集注文登録」: <BR>
     *     1.25((if is判定フラグ() == false)<BR>
     *     is判定フラグ()の戻り値がfalseの場合、[取引余力チェックエラー]として例外をスローする。<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_01306<BR>
     *==========================================================<BR>
     * @@param l_request - (投信募集注文完了リクエスト)
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4153F8F00140
     */
    public WEB3MutualApplyCompleteResponse submitOrder(WEB3MutualApplyCompleteRequest l_request)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "submitOrder(WEB3MutualApplyCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);

        //1.1 validate( )
        l_request.validate();

        //1.2 get補助口座( )
        WEB3GentradeSubAccount l_subAccount =
            (WEB3GentradeSubAccount) this.getSubAccount();

        //1.3 get投信銘柄(Institution, String)
        Institution l_institution = l_subAccount.getInstitution();
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3MutualFundProductManager l_mutualFundProductManager =
            (WEB3MutualFundProductManager) l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getProductManager();
        WEB3MutualFundProduct l_mutualFundProduct = null;
        try
        {
            l_mutualFundProduct =
                (WEB3MutualFundProduct) l_mutualFundProductManager.getMutualFundProduct(
                    l_institution,
                    l_request.mutualProductCode);
        }
        //拡張投信銘柄が取得出来ない場合、例外をスローする
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundException__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00377,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
        }

        //1.4 getCommonOrderValidator( )
        WEB3GentradeOrderValidator l_orderValidator =
                (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();

        //1.5 validate取引可能顧客(顧客, Timestamp)
        WEB3GentradeMainAccount l_genMainAccount =
                (WEB3GentradeMainAccount)l_subAccount.getMainAccount();

        Timestamp l_tsOrderedDate = new Timestamp(l_request.orderedDate.getTime());
        OrderValidationResult l_validationResult =
                l_orderValidator.validateAccountForTrading(l_genMainAccount, l_tsOrderedDate);

        if (l_validationResult.getProcessingResult().isFailedResult())
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_validationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //1.6 get代理入力者( )
        Trader l_trader = this.getTrader();

        //1.7 validate取引パスワード
        OrderValidationResult l_validationResultPassword =
            l_orderValidator.validateTradingPassword(
                l_trader,
                l_subAccount,
                l_request.password);

        if (l_validationResultPassword.getProcessingResult().isFailedResult())
        {
            log.debug("取引パスワードが不正です");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00009,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "取引パスワードが不正です。");
        }

        //1.8 validate注文受付可能( )
        WEB3MutualFundTradingTimeManagement.validateOrderAccept();

        //1.9 reset銘柄コード(String)
        WEB3MutualFundTradingTimeManagement.resetProductCode(
            l_request.mutualProductCode);

        //1.10 setTimestamp( )
        WEB3MutualFundTradingTimeManagement.setTimestamp();

        //1.11 validate新規注文
        WEB3MutualFundOrderManager l_mutualFundOrderManager =
            (WEB3MutualFundOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getOrderManager();

        NewOrderValidationResult l_newOrderValidationResult =
            l_mutualFundOrderManager.validateNewOrder(
                l_subAccount,
                l_mutualFundProduct,
                Double.parseDouble(l_request.mutualOrderQuantity),
                WEB3ProcessDivDef.RECRUIT,
                null,
                l_request.specifyDiv,
                null,
                null,
                null);

        if (!l_newOrderValidationResult.getProcessingResult().isSuccessfulResult())
        {
            throw new WEB3BusinessLayerException(
                l_newOrderValidationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //1.12一括区分取得
        //    １）部店用プリファ@レンスから「投信募集注文一括送信区分」を取得する。
        //    [部店用プリファ@レンスの取得条件]
        //       部店ID ： 取得した補助口座.getMainAccount().getBranchId()
        //       プリファ@レンス名 ： mf.recruit.mq.send.div
        //       プリファ@レンス名の連番 ： 1
        //  ※レコード無しは「一括送信する」
        //
        //２）以下の条件と一致する場合は true を、それ以外の場合は false を一括区分へセットする。
        //    部店用プリファ@レンス.投信募集注文一括送信区分 == 「一括送信する」
        boolean l_blnMfRecruitMqSendDiv = true;
        BranchPreferencesRow l_row = null;
        try
        {
            l_row = BranchPreferencesDao.findRowByBranchIdNameNameSerialNo(
                l_subAccount.getMainAccount().getBranch().getBranchId(),
                WEB3BranchPreferencesNameDef.MF_RECRUIT_MQ_SEND_DIV,
                1);
        }
        catch (DataFindException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        if (l_row == null || WEB3MfRecruitMqSendDivDef.DEFAULT.equals(l_row.getValue()))
        {
            l_blnMfRecruitMqSendDiv = true;
        }
        else
        {
            l_blnMfRecruitMqSendDiv = false;
        }

        //1.13 get発注日(Date, OrderTypeEnum, boolean)
        //発注日：　@リクエストデータ.発注日
        //注文種別：　@OrderTypeEnum.投資信託募集注文
        //一括区分：　@取得した一括区分
        Date l_datOrderBizDate =
            WEB3MutualFundTradingTimeManagement.getOrderBizDate(
                l_request.orderedDate,
                OrderTypeEnum.MF_RECRUIT,
                l_blnMfRecruitMqSendDiv);

        //1.14 get募集終了日
        Timestamp l_tsRecruitEndDate = l_mutualFundProduct.getRecruitEndDate();

        //1.15 get募集終了日（SONAR）( )
        Timestamp l_tsApplyAbleEndDateSonar = l_mutualFundProduct.getApplyAbleEndDateSonar();

        //1.16 get顧客()
        //拡張アカウントマネージャ取得する
        WEB3GentradeAccountManager l_web3GentradeAccountManager =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        try
        {
            //－拡張アカウントマネージャ.get顧客()をコールし、顧客オブジェクトを取得する
            l_genMainAccount =
                (WEB3GentradeMainAccount) l_web3GentradeAccountManager.getMainAccount(
                    l_subAccount.getAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundException__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //1.17 if リクエストデータ.口座区分 == ”1:特定”
        if (WEB3TaxTypeSpecialDef.SPECIAL.equals(l_request.taxType))
        {
            //1.17.1 is特定口座開設
            //特定口座チェック is特定口座開設()がfalseの場合、例外をスローする
            boolean l_blnIsSpecialAccountEstablished =
                l_genMainAccount.isSpecialAccountEstablished(
                    l_tsRecruitEndDate, l_subAccount);

            if (!l_blnIsSpecialAccountEstablished)
            {
                log.debug("特定口座未開設エラー。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02096,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "特定口座未開設エラー。");
            }
        }

        //1.18 calc概算受渡代金
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);
        String l_strOrderChanel =
            l_opLoginSec.getSessionProperty(WEB3SessionAttributeDef.ORDER_CHANNEL);

        WEB3MutualFundEstimatedPrice l_mutualFundEstimatedPrice =
            l_mutualFundOrderManager.calcEstimateDeliveryAmount(
                l_subAccount,
                l_mutualFundProduct,
                null,
                WEB3MFProcessDivDef.RECRUIT,
                Double.parseDouble(l_request.mutualOrderQuantity),
                l_request.specifyDiv,
                l_request.settleDiv,
                null,
                l_request.taxType,
                l_strOrderChanel,
                l_request.orderedDate);

        //1.19 投信新規注文確定インタセプタ（募集）
        WEB3MutualFundNewOrderApplyConfirmInterceptor l_mfNewOrderApplyConfirmInterceptor =
            new WEB3MutualFundNewOrderApplyConfirmInterceptor();

        //1.20 set入金日(Timestamp)
        //取得した一括区分==trueなら
        //取得した募集終了日(SONAR)
        //取得した一括区分==falseなら
        //取得した発注日の翌営業日
        WEB3GentradeBizDate l_gentradeBizDate =
            new WEB3GentradeBizDate(new Timestamp(l_datOrderBizDate.getTime()));

        if (l_blnMfRecruitMqSendDiv)
        {
            l_mfNewOrderApplyConfirmInterceptor.setPaymentDate(l_tsApplyAbleEndDateSonar);
        }
        else
        {
            l_mfNewOrderApplyConfirmInterceptor.setPaymentDate(l_gentradeBizDate.roll(1));
        }

        //1.21 setThreadLocalPersistenceEventInterceptor(MutualFundOrderManagerPersistenceEventInterceptor)
        l_mutualFundOrderManager.setThreadLocalPersistenceEventInterceptor(l_mfNewOrderApplyConfirmInterceptor);

        //1.22 投信新規注文確定インタセプタ（募集）へのプロパティセット

        //受渡日：
        //取得した一括区分==trueなら取得した募集終了日(SONAR)、
        //取得した一括区分==falseなら取得した募集終了日
        if (l_blnMfRecruitMqSendDiv)
        {
            l_mfNewOrderApplyConfirmInterceptor.setDeliveryDate(l_tsApplyAbleEndDateSonar);
        }
        else
        {
            l_mfNewOrderApplyConfirmInterceptor.setDeliveryDate(l_tsRecruitEndDate);
        }

        //注文チャネル：this.getログインチャネル()の戻り値
        l_mfNewOrderApplyConfirmInterceptor.setOrderChannel(this.getLoginChannel());

        //計算基準価額：取得した拡張投信銘柄オブジェクト.get募集価額()の戻り値
        l_mfNewOrderApplyConfirmInterceptor.setConstantValue(
            l_mutualFundProduct.getRecruitConstantValue());

        //計算基準価額（乗換先）：Double.NaN
        l_mfNewOrderApplyConfirmInterceptor.setSwitchingConstantValue(Double.NaN);

        //基準価額適用日：null
        l_mfNewOrderApplyConfirmInterceptor.setConstantValueAppDate(null);

        //概算受渡代金：取得した概算受渡代金オブジェクト.get概算受渡代金()の戻り値
        l_mfNewOrderApplyConfirmInterceptor.setEstimatedPrice(
            l_mutualFundEstimatedPrice.getEstimatedPrice());

        //概算売買口数：取得した概算受渡代金オブジェクト.get概算売買口数()の戻り値
        l_mfNewOrderApplyConfirmInterceptor.setEstimatedQty(
            l_mutualFundEstimatedPrice.getEstimatedQty());

        //概算買付口数（乗換先）：Double.NaN
        l_mfNewOrderApplyConfirmInterceptor.setSwitchingEstimatedQty(Double.NaN);

        //税区分（乗換先）：null
        l_mfNewOrderApplyConfirmInterceptor.setSwitchingSubjectTaxDivision(null);

        //受渡方法@：null
        l_mfNewOrderApplyConfirmInterceptor.setDeliveryDiv(null);

        //投信タイプ：取得した拡張投信銘柄.getMutualFundType()の戻り値
        l_mfNewOrderApplyConfirmInterceptor.setMutualFundType(
            Integer.toString(l_mutualFundProduct.getMutualFundType().intValue()));

        //投信解約区分：null
        l_mfNewOrderApplyConfirmInterceptor.setMutualFundSellDiv(null);

        //約定日：取得した発注日
        l_mfNewOrderApplyConfirmInterceptor.setExecutionTimestamp(
            new Timestamp(l_datOrderBizDate.getTime()));

        //決済区分：リクエストデータ.決済区分
        l_mfNewOrderApplyConfirmInterceptor.setSettlementType(l_request.settleDiv);

        //無手数料区分：(*)
                //拡張投信注文マネージャ.is手数料無料顧客() == true の場合、”9：無手数料”を設定する。
        //  [is手数料無料顧客()に渡すパラメータ]
        //    顧客： 顧客オブジェクト
        //    銘柄： 取得した拡張投信銘柄オブジェクト
                if (l_mutualFundOrderManager.isCommissionFreeAccount(l_genMainAccount, l_mutualFundProduct))
        {
            l_mfNewOrderApplyConfirmInterceptor.setNoCommissionDivision(
                WEB3CommissionDivDef.NO_COMMISSION);
        }
        //取得した拡張投信銘柄.is乗換優遇対象()の戻り値が
        //”0：償還優遇不可”の場合、ブランクを設定する。
        //それ以外の場合、”5：乗換優遇”を設定する。
        else if (l_mutualFundProduct.isSwitchingPerferenceObject())
        {
            l_mfNewOrderApplyConfirmInterceptor.setNoCommissionDivision(
                WEB3CommissionDivDef.SWITCHING_PREFERENCE);
        }
        else
        {
            //取得した拡張投信銘柄.is乗換優遇対象()の戻り値がfalseの場合はブランクを設定する
            l_mfNewOrderApplyConfirmInterceptor.setNoCommissionDivision(" ");
        }

        //銘柄コード（乗換先）：null
        l_mfNewOrderApplyConfirmInterceptor.setSwitchingSubjectMutualProductCode(null);

        //請求区分：null
        l_mfNewOrderApplyConfirmInterceptor.setRequestDivision(null);

        //注文経路区分： セッションから取得した同項目の値
        l_mfNewOrderApplyConfirmInterceptor.setOrderChannelDivision(
            l_opLoginSec.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV));

        //出金注文識別コード：null
        l_mfNewOrderApplyConfirmInterceptor.setPaymentOrderReqNumber(null);

        //一括区分：取得した一括区分
        l_mfNewOrderApplyConfirmInterceptor.setNorealDiv(l_blnMfRecruitMqSendDiv);

        //注文終了日：取得した募集終了日
        l_mfNewOrderApplyConfirmInterceptor.setOrderEndDate(l_tsRecruitEndDate);

        //1.21 拡張投信新規注文内容
        //[引数]
        //代理入力者： this.get代理入力者()の戻り値
        //is買付： true
        //銘柄コード： リクエストデータ.銘柄コード
        //注文数量： リクエストデータ.数量
        //注文数量タイプ：
        //(*) リクエストデータ.指定方法@が”3：金額”の場合は
        //QuantityTypeEnum.金額を指定。
        //(*) リクエストデータ.指定方法@が”4：口数”の場合は
        //QuantityTypeEnum.数量を指定。
        QuantityTypeEnum l_orderQuantityType = null;
        if (WEB3BuySellSwtSpecityDivDef.PRICE_DESIGNATE.equals(l_request.specifyDiv))
        {
            l_orderQuantityType = QuantityTypeEnum.AMOUNT;
        }
        else if (WEB3BuySellSwtSpecityDivDef.QUANTITY_DESIGNATE.equals(l_request.specifyDiv))
        {
            l_orderQuantityType = QuantityTypeEnum.QUANTITY;
        }

        //税区分：
        //(*) リクエストデータ.口座区分が”0：一般”の場合、
        //TaxTypeEnum.NORMALを設定する。
        //(*) リクエストデータ.口座区分が”1：特定”の場合、
        //TaxTypeEnum.SPECIALを設定する。
        TaxTypeEnum l_taxType = null;
        if (WEB3TaxTypeSpecialDef.NORMAL.equals(l_request.taxType))
        {
            l_taxType = TaxTypeEnum.NORMAL;
        }
        else if (WEB3TaxTypeSpecialDef.SPECIAL.equals(l_request.taxType))
        {
            l_taxType = TaxTypeEnum.SPECIAL;
        }
        WEB3MutualFundNewOrderSpec l_expMutualFundNewOrderSpec =
            new WEB3MutualFundNewOrderSpec(
                l_trader,
                true,
                l_request.mutualProductCode,
                Double.parseDouble(l_request.mutualOrderQuantity),
                l_orderQuantityType,
                l_taxType);

        //1.22 validate取引余力
        WEB3TPTradingPowerService l_tpTradingPowerService =
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);

        Object[] l_arrNewOrderConfirmInterceptors =
            {l_mfNewOrderApplyConfirmInterceptor};

        Object[] l_arrNewOrderSpecs = {l_expMutualFundNewOrderSpec};

        WEB3TPTradingPowerResult l_resule =
            l_tpTradingPowerService.validateTradingPower(
                l_subAccount,
                l_arrNewOrderConfirmInterceptors,
                l_arrNewOrderSpecs,
                OrderTypeEnum.MF_RECRUIT,
                true);

        //1.23 is判定フラグ( )
        //1.24 is判定フラグ()＝false の場合
        //is判定フラグ()の戻り値がfalseの場合、[取引余力チェックエラー]として例外をスローする
        if(!l_resule.isResultFlg())
        {
            log.debug("取引余力チェックエラー");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01306,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "取引余力チェックエラー");
        }

        //1.25 submitNewOrder
        //[引数]
        // 補助口座：　@取得した補助口座オブジェクト
        // 銘柄タイプ： ProductTypeEnum.投資信託
        // 新規注文内容： 拡張投信新規注文内容
        // 注文ID： リクエストデータ.注文ID
        // 取引パスワード： リクエストデータ.暗証番号
        // is発注審査省略： true
        OrderSubmissionResult l_orderSubmissionResult = null;
        l_orderSubmissionResult =
            l_mutualFundOrderManager.submitNewOrder(
                l_subAccount,
                ProductTypeEnum.MUTUAL_FUND,
                l_expMutualFundNewOrderSpec,
                Long.parseLong(l_request.orderId),
                l_request.password,
                true);
        if (l_orderSubmissionResult.getProcessingResult().isSuccessfulResult()== false)
        {
            log.debug(
                "l_mutualFundOrderManager.submitNewOrder()." +
                "getProcessingResult().isSuccessfulResult() == false");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00191,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "新規注文失敗");
        }

        //1.26.＜リクエストデータ．紹介区分　@!= NULL　@の場合＞
        if (l_request.introduceStoreDiv != null)
        {
            //1.26.1getOrderUnits(arg0 : long)
            //投信注文単位オブジェクトを取得する。
            //［getOrderUnitsに渡すパラメタ］
            //　@　@　@arg0： リクエスト.注文ID
            OrderUnit[] l_orderUnits =
                l_mutualFundOrderManager.getOrderUnits(Long.parseLong(l_request.orderId));
            MutualFundOrderUnit[] l_mfOrderUnits = new MutualFundOrderUnit[l_orderUnits.length];
            for (int i = 0; i < l_orderUnits.length; i++)
            {
                l_mfOrderUnits[i] = (MutualFundOrderUnit) l_orderUnits[i];
            }
            MutualFundOrderUnitRow l_orderUnitRow =
                (MutualFundOrderUnitRow) l_mfOrderUnits[0].getDataSourceObject();
            MutualFundOrderUnitParams l_orderUnitParams =
                new MutualFundOrderUnitParams(l_orderUnitRow);
            //1.26.2.注文単位紹介区分( )
            OrderUnitIntroduceDivParams l_introduceDivParams = new OrderUnitIntroduceDivParams();
            //1.26.3.<プロパティセット>
            //注文単位ID   =　@取得した注文単位.注文単位ID
            l_introduceDivParams.setOrderUnitId(l_orderUnitParams.getOrderUnitId());
            //口座ID         =  取得した注文単位.口座ID
            l_introduceDivParams.setAccountId(l_orderUnitParams.getAccountId());
            //銘柄タイプ     =  引数にProductTypeEnum.投資信託
            l_introduceDivParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
            //紹介区分      =  リクエスト.紹介区分
            l_introduceDivParams.setIntroduceBranchDiv(l_request.introduceStoreDiv);
            //紹介店コード  =  リクエスト.紹介店コード
            l_introduceDivParams.setIntroduceBranchCode(l_request.introduceStoreCode);
            //更新者コード
            //　@ ・顧客入力の場合
            //       　@取得した注文単位.口座IDに該当する口座コードをセットする。
            // 　@・代理入力の場合
            //       　@取得した注文単位.取引者IDに該当する扱者コードをセットする。
            WEB3GentradeMainAccount l_gentradeMainAccount = null;
            WEB3GentradeAccountManager l_gentradeAccountManager =
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            if (getTrader() == null)
            {
                try
                {
                    l_gentradeMainAccount =
                        (WEB3GentradeMainAccount) l_gentradeAccountManager.getMainAccount(
                            l_orderUnitParams.getAccountId());
                } catch (NotFoundException l_ex)
                {
                    log.error("テーブルに該当するデータがありません。", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                l_introduceDivParams.setLastUpdater(l_gentradeMainAccount.getAccountCode());
            }
            else
            {
                FinObjectManager l_finObjMgr = l_finApp.getFinObjectManager();
                Trader l_trder = null;
                try
                {
                    l_trder = l_finObjMgr.getTrader(l_orderUnitParams.getTraderId());
                } catch (NotFoundException l_ex)
                {
                    log.error("テーブルに該当するデータがありません。", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                l_introduceDivParams.setLastUpdater(l_trder.getTraderCode());
            }
            //作成日付 = 現在時刻
            l_introduceDivParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            //更新日付 = 現在時刻
            l_introduceDivParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            //1.26.3.saveNew注文単位紹介区分( )
            //注文単位紹介区分テーブルにインサートする。
            try
            {
                Processors.getDefaultProcessor().doInsertQuery(l_introduceDivParams);
            }
            catch (DataFindException l_ex)
            {
                log.error("テーブルに該当するデータがありません。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }

        //1.27 getAttribute(String)
        Date l_datAttribute = null;
        l_datAttribute = (Date)ThreadLocalSystemAttributesRegistry.getAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
        log.debug("WEB3MutualBuyServiceImpl.submitBuyOrder::l_datAttribute = " + l_datAttribute);

        //1.28 createResponse( )
        WEB3MutualApplyCompleteResponse l_response =
            (WEB3MutualApplyCompleteResponse)l_request.createResponse();

        //1.29 プロパティセット
        //以下のプロパティをセットする。

        //更新時間：取得した処理時間
        l_response.lastUpdatedTimestamp = l_datAttribute;

        //識別番号：リクエストの注文ID
        l_response.orderActionId = l_request.orderId;
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
