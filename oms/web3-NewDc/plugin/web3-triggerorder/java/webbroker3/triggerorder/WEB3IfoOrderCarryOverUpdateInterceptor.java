head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.57.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3IfoOrderCarryOverUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP注文繰越更新インタセプタ(WEB3IfoOrderCarryOverUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/10 張威 (中訊) 新規作成
              001: 2004/07/21 王暁傑(中訊) mutate()を修正
              001: 2004/07/21 王暁傑(中訊) mutate()を修正 対応バッグ WEB3_IFO_UT-000050 WEB3_IFO_UT-000051
                 : 2006/7/27 肖志偉 (中訊) DB更新仕様No.102,106
                 : 2006/10/16 肖志偉 (中訊) DB更新仕様No.116
Revesion History : 2007/01/25 張騰宇 (中訊) DB更新仕様No.144、159
Revesion History : 2007/06/21 孟亜南 (中訊) DB更新仕様No.187
Revesion History : 2007/06/21 孟亜南 (中訊) モデルNo.744
Revesion History : 2007/06/21 孟亜南 (中訊) モデルNo.765 DB更新仕様No.190
Revesion History : 2008/04/15 趙林鵬 (中訊) トリガー注文ＤＢ更新仕様052,053
*/

package webbroker3.triggerorder;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3RequestTypeDef;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult;
import webbroker3.ifo.WEB3IfoUpdateInterceptor;
import webbroker3.ifo.define.WEB3IfoVoucherNoFirstNumDef;
import webbroker3.ifo.service.delegate.WEB3IfoFrontOrderService;
import webbroker3.util.WEB3LogUtility;

/**
 * (先物OP注文繰越更新インタセプタ)<BR>
 * 先物OP注文繰越更新インタセプタクラス<BR>
 * @@author 張威
 * @@version 1.0
 */
public class WEB3IfoOrderCarryOverUpdateInterceptor extends WEB3IfoUpdateInterceptor
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3IfoOrderCarryOverUpdateInterceptor.class);

    /**
     * (（繰越元）注文単位Params)<BR>
     * 繰越元の注文単位行オブジェクト<BR>
     */
    protected IfoOrderUnitParams carryOverOrderUnitParams;

    /**
     * エラーコード
     */
    protected String errorCode;

    /**
     * 手数料オブジェクト
     */
    protected WEB3GentradeCommission commissionFee;

    /**
     * 概算受渡代金計算結果オブジェクト
     */
    protected WEB3IfoEstimateDeliveryAmountCalcResult estimatedDeliveryAmountCalculationResult;

    /**
     * 立会区分
     */
    protected String sessionType;

    /**
     * @@roseuid 40C09F9003D8
     */
    public WEB3IfoOrderCarryOverUpdateInterceptor()
    {

    }

    /**
     * (先物OP注文繰越更新インタセプタ)<BR>
     * コンストラクタ<BR>
     * <BR>
     * インスタンスを生成し、引数を自身のプロパティにセットする。<BR>
     * @@param l_orderUnitParams - （繰越元）注文単位行オブジェクト
     * 
     * @@param l_strOrderErrorReasonCode - 注文エラー理由コード<BR>
     * <BR>
     * DBレイアウト<BR>
     * 注文単位テーブル仕様.xls<BR>
     * 「（注文単位テーブル補足）注文エラー理由コード」シート参照。<BR>
     * 
     * @@return webbroker3.ifo.WEB3IfoOrderCarryOverUpdateInterceptor
     * @@roseuid 40A0A0050079
     */
    public WEB3IfoOrderCarryOverUpdateInterceptor(
        IfoOrderUnitParams l_orderUnitParams,
        String l_strOrderErrorReasonCode)
    {
        this.carryOverOrderUnitParams = l_orderUnitParams;
        this.errorCode = l_strOrderErrorReasonCode;
    }

    /**
     * (set（繰越元）注文単位Params)
     * @@param l_orderUnitParams - (（繰越元）注文単位Params)<BR>
     * （繰越元）注文単位行オブジェクト<BR>
     * @@roseuid 40A09EC201FF
     */
    public void setOrderUnitParams(IfoOrderUnitParams l_orderUnitParams)
    {
        carryOverOrderUnitParams = l_orderUnitParams;
    }

    /**
     * (更新値設定)<BR>
     * （mutateメソッドの実装）<BR>
     * 引数の注文単位Paramsに拡張項目(*)を設定し返却する。<BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。<BR>
     * <BR>
     * －繰越元注文の場合(*1)<BR>
     * 　@セット内容については、以下のファ@イルを参照。<BR>
     * <BR>
     * 　@【ｘTrade】補足資料.DB更新<BR>
     * 　@「OP注文繰越_注文単位テーブル仕様[繰越元].xls」<BR>
     * <BR>
     * －翌日注文の場合（*2）<BR>
     * 　@セット内容については、以下のファ@イルを参照。<BR>
     * <BR>
     * 　@【ｘTrade】補足資料.DB更新<BR>
     * 　@「OP注文繰越_注文単位テーブル仕様[翌日注文（夕場注文）].xls」<BR>
     * <BR>
     * <BR>
     * 　@(*1) 繰越元注文の判定<BR>
     * 　@this.（繰越元）注文単位Params.注文単位ＩＤ == <BR>
     * 引数.注文単位Params.注文単位ＩＤ<BR>
     * <BR>
     * 　@(*2) 翌日注文の判定<BR>
     * 　@this.（繰越元）注文単位Params.注文単位ＩＤ != <BR>
     * 引数.注文単位Params.注文単位ＩＤ<BR>
     * @@param l_updateType - (更新タイプ)
     * @@param l_context - (コンテキスト)
     * @@param l_orderUnitRow - 注文単位行オブジェクト
     * @@return webbroker3.ifo.data.IfoOrderUnitParams
     * @@roseuid 40A09F1A01A2
     */
    public IfoOrderUnitParams mutate(
        OrderManagerPersistenceType l_updateType,
        OrderManagerPersistenceContext l_context,
        IfoOrderUnitParams l_orderUnitParams)
    {
        final String STR_METHOD_NAME = getClass().getName() + ".mutate() ";

        log.debug(STR_METHOD_NAME + ": ENTER");
        try
        {
            if (this.carryOverOrderUnitParams.order_unit_id == l_orderUnitParams.order_unit_id)
            {
                //繰越元注文の場合
                
                //注文エラー理由コード
                l_orderUnitParams.setErrorReasonCode(this.errorCode);
            }
            else if (this.carryOverOrderUnitParams.order_unit_id != l_orderUnitParams.order_unit_id)
            {
                //翌日注文の場合
                
                //先物／オプション区分
                l_orderUnitParams.setFutureOptionDiv(this.carryOverOrderUnitParams.future_option_div);
                
                //発注条件
                l_orderUnitParams.setOrderConditionType(this.carryOverOrderUnitParams.order_condition_type);
                
                //発注条件演算子
                l_orderUnitParams.setOrderCondOperator(this.carryOverOrderUnitParams.getOrderCondOperator());

                //逆指値基準値タイプ
                l_orderUnitParams.setStopPriceType(this.carryOverOrderUnitParams.getStopPriceType());

                //逆指値基準値
                if (this.carryOverOrderUnitParams.getStopOrderPriceIsNull())
                {
                    l_orderUnitParams.setStopOrderPrice(null);
                }
                else
                {
                    l_orderUnitParams.setStopOrderPrice(this.carryOverOrderUnitParams.getStopOrderPrice());
                }
                
                //（W指値）訂正指値
                if (this.carryOverOrderUnitParams.getWLimitPriceIsNull())
                {
                    l_orderUnitParams.setWLimitPrice(null);
                }
                else
                {
                    l_orderUnitParams.setWLimitPrice(this.carryOverOrderUnitParams.getWLimitPrice());
                }
                
                //リクエストタイプ
                if (WEB3OrderingConditionDef.DEFAULT.equals(l_orderUnitParams.getOrderConditionType()))
                {
                    l_orderUnitParams.setRequestType(this.carryOverOrderUnitParams.getRequestType());        
                }
                else
                {
                    l_orderUnitParams.setRequestType(WEB3RequestTypeDef.DEFAULT);                                                            
                }
                
                //初回注文の注文チャネル
                l_orderUnitParams.setOrderChanel(this.carryOverOrderUnitParams.order_chanel);

                //伝票No
                //"9"(WebBroker)＋識別コードの下３桁
                FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                
                SubAccount l_subAccount = l_finApp.getAccountManager().getSubAccount(this.carryOverOrderUnitParams.getAccountId(), this.carryOverOrderUnitParams.getSubAccountId());
                
                WEB3HostReqOrderNumberManageService l_numberService =
                    (WEB3HostReqOrderNumberManageService) Services.getService(
                    WEB3HostReqOrderNumberManageService.class);
                                
                String l_strOrderRequestNumber =
                    l_numberService.getNewNumber(
                        l_subAccount.getInstitution().getInstitutionCode(),
                        l_subAccount.getMainAccount().getBranch().getBranchCode(),
                        ProductTypeEnum.IFO);
                        
                int l_intNumberOfChars = l_strOrderRequestNumber.length();
                               
                String l_strOrderRequestNumberLastThreeDigits =
                    l_strOrderRequestNumber.substring(l_intNumberOfChars - 3);
                    
                l_orderUnitParams.setVoucherNo(WEB3IfoVoucherNoFirstNumDef.FIRST_NUMBER + l_strOrderRequestNumberLastThreeDigits);
                

                //初回注文の手数料No
                l_orderUnitParams.setCommTblNo(this.commissionFee.getCommissionNo());

                //初回注文の手数料No枝番
                l_orderUnitParams.setCommTblSubNo(this.commissionFee.getCommissionRevNo());

                //扱者コード（SONAR）
                l_orderUnitParams.setSonarTraderCode(this.carryOverOrderUnitParams.getSonarTraderCode());

                //注文単価
                l_orderUnitParams.setPrice(
                    this.estimatedDeliveryAmountCalculationResult.getCalcUnitPrice());

                //識別コード

                //識別テーブルを、証券会社コード・部店コード・銘柄タイプにて検索し、
                //該当行の識別コード（SEQ NO）+1の値。
                //（* 該当行の識別コードをインクリメントした値で識別コードテーブルを更新する。識別コードテーブルに行が存在しない場合はInsertする）
                l_orderUnitParams.setOrderRequestNumber(l_strOrderRequestNumber);

                //概算受渡代金
                l_orderUnitParams.setEstimatedPrice(
                    this.estimatedDeliveryAmountCalculationResult.getEstimateDeliveryAmount());

                //取引コード（SONAR）
                l_orderUnitParams.setSonarTradedCode(this.carryOverOrderUnitParams.sonar_traded_code);

                //市場コード（SONAR）
                long l_lngMarketId = l_orderUnitParams.getMarketId();
                
                //NotFoundException
                String l_strMarketCode =
                    l_finApp.getFinObjectManager().getMarket(l_lngMarketId).getMarketCode();
                l_orderUnitParams.setSonarMarketCode(l_strMarketCode);

                //手数料商品コード                 
                l_orderUnitParams.setCommProductCode(this.commissionFee.getCommissionProductCode());                

                //注文訂正・取消区分
                l_orderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.INITIAL_VALUE);

                //注文経路区分
                l_orderUnitParams.setOrderRootDiv(this.carryOverOrderUnitParams.getOrderRootDiv());

                //市場から確認済みの注文単価
                l_orderUnitParams.setConfirmedOrderPrice(null);

                //市場から確認済みの概算受渡代金
                l_orderUnitParams.setConfirmedEstimatedPrice(null);

                //市場から確認済みの執行条件
                l_orderUnitParams.setConfirmedExecConditionType(null);

                //決済順序         
                l_orderUnitParams.setClosingOrder(this.carryOverOrderUnitParams.getClosingOrder());

                //注文エラー理由コード
                l_orderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);

                // 繰越元注文単位.初回注文の注文単位ＩＤ = 0 または nullの場合、繰越元注文単位.注文単位ＩＤ
                // それ以外の場合、繰越元注文単位.初回注文の注文単位ＩＤ
                if (carryOverOrderUnitParams.getFirstOrderUnitIdIsNull() 
                    || carryOverOrderUnitParams.getFirstOrderUnitId() == 0)
                {
                    l_orderUnitParams.setFirstOrderUnitId(carryOverOrderUnitParams.getOrderUnitId());
                }
                else
                {
                    l_orderUnitParams.setFirstOrderUnitId(carryOverOrderUnitParams.getFirstOrderUnitId());
                }
                
                // 受注日時
                // 繰越元注文単位の同項目
                l_orderUnitParams.setReceivedDateTime(carryOverOrderUnitParams.getReceivedDateTime());
                
                //元発注条件
                l_orderUnitParams.setOrgOrderConditionType(carryOverOrderUnitParams.getOrgOrderConditionType());
                
                //元発注条件演算子
                l_orderUnitParams.setOrgOrderCondOperator(carryOverOrderUnitParams.getOrgOrderCondOperator());
                
                //元逆指値基準値タイプ
                l_orderUnitParams.setOrgStopPriceType(carryOverOrderUnitParams.getOrgStopPriceType());
                
                //元逆指値基準値
                if (carryOverOrderUnitParams.getOrgStopOrderPriceIsNull())
                {
                    l_orderUnitParams.setOrgStopOrderPrice(null);
                }
                else
                {
                    l_orderUnitParams.setOrgStopOrderPrice(carryOverOrderUnitParams.getOrgStopOrderPrice());
                }
                
                //元（W指値）訂正指値
                if (carryOverOrderUnitParams.getOrgWLimitPriceIsNull())
                {
                    l_orderUnitParams.setOrgWLimitPrice(null);
                }
                else
                {
                    l_orderUnitParams.setOrgWLimitPrice(carryOverOrderUnitParams.getOrgWLimitPrice());
                }
                
                //元（W指値）執行条件
                l_orderUnitParams.setOrgWLimitExecCondType(carryOverOrderUnitParams.getOrgWLimitExecCondType());
                
                //（W指値）執行条件
                l_orderUnitParams.setWLimitExecCondType(carryOverOrderUnitParams.getWLimitExecCondType());
                
                //（W指値）切替前指値
                if (carryOverOrderUnitParams.getWLimitBeforeLimitPriceIsNull())
                {
                    l_orderUnitParams.setWLimitBeforeLimitPrice(null);
                }
                else
                {
                    l_orderUnitParams.setWLimitBeforeLimitPrice(carryOverOrderUnitParams.getWLimitBeforeLimitPrice());
                }
                
                //（W指値）切替前執行条件
                l_orderUnitParams.setWLimitBeforeExecCondType(carryOverOrderUnitParams.getWLimitBeforeExecCondType());

                //発注遅延フラグ
                l_orderUnitParams.setSubmitOrderDelayFlag(carryOverOrderUnitParams.getSubmitOrderDelayFlag());
                
                //発注経路区分
                WEB3IfoFrontOrderService l_ifoFrontOrderService =
                    (WEB3IfoFrontOrderService)Services.getService(WEB3IfoFrontOrderService.class);
                l_orderUnitParams.setSubmitOrderRouteDiv(
                    l_ifoFrontOrderService.getSubmitOrderRouteDiv(
                        l_subAccount.getInstitution().getInstitutionCode(),
                        l_strMarketCode));

                //夕場前繰越対象フラグ
                l_orderUnitParams.setEveningSessionCarryoverFlag(
                    carryOverOrderUnitParams.getEveningSessionCarryoverFlag());

                //立会区分
                l_orderUnitParams.setSessionType(this.sessionType);

                //日計り区分
                if (OrderCategEnum.IDX_FUTURES_CLOSE.equals(carryOverOrderUnitParams.getOrderCateg())
                    || OrderCategEnum.IDX_OPTIONS_CLOSE.equals(carryOverOrderUnitParams.getOrderCateg()))
                {
                    l_orderUnitParams.setDayTradeType(commissionFee.getDayTradeType());
                }
                else if (OrderCategEnum.IDX_FUTURES_OPEN.equals(carryOverOrderUnitParams.getOrderCateg())
                   || OrderCategEnum.IDX_OPTIONS_OPEN.equals(carryOverOrderUnitParams.getOrderCateg()))
                {
                    l_orderUnitParams.setDayTradeType(null);
                }

                //注文期限区分
                l_orderUnitParams.setExpirationDateType(
                    this.carryOverOrderUnitParams.getExpirationDateType());
            }

            log.debug(STR_METHOD_NAME + ": END");
            return l_orderUnitParams;
        }
        catch (NotFoundException l_ex)
        {
            log.error(
                "データ不整合エラー。",
                new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    STR_METHOD_NAME,
                    l_ex.toString(),
                    l_ex));

            log.debug(STR_METHOD_NAME + ": END");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName()+  "." + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName()+  "." + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }        
    }

    /**
     * (set手数料)<BR>
     * 手数料オブジェクトをセットする。<BR>
     * @@param l_commissionFee - 手数料オブジェクト
     * @@roseuid 40A0C2420138
     */
    public void setCommissionFee(WEB3GentradeCommission l_commissionFee)
    {
        this.commissionFee = l_commissionFee;
    }

    /**
     * 概算受渡代金計算結果をセットする。
     * @@param l_estimateDeliveryAmountCalcResult - 概算受渡代金計算結果オブジェクト
     * @@roseuid 40A0C2420167
     */
    public void setEstimateDeliveryAmountCalcResult(WEB3IfoEstimateDeliveryAmountCalcResult l_estimateDeliveryAmountCalcResult)
    {
        this.estimatedDeliveryAmountCalculationResult = l_estimateDeliveryAmountCalcResult;
    }

    /**
     * (set立会区分)<BR>
     * @@param l_sessionType - 立会区分
     */
    public void setSessionType(String l_strSessionType)
    {
        this.sessionType = l_strSessionType;
    }
}
@
