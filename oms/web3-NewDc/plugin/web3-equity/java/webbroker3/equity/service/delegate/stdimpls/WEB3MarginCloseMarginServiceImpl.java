head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginCloseMarginServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引返済サービスImpl(WEB3MarginCloseMarginServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/09 呉艶飛 (中訊) 新規作成
Revesion History : 2004/12/09 森川   (SRA) 残案件対応
Revesion History : 2005/01/06 岡村和明(SRA) JavaDoc修正
Revesion History : 2006/11/27 柴双紅(中訊) モデル1010
Revesion History : 2007/01/17 唐性峰　@(中訊)モデルNo.1107
Revesion History : 2007/06/04 何文敏 (中訊) 仕様変更モデルNo.1154,モデルNo.1161
Revesion History : 2007/06/14 何文敏(中訊) 仕様変更モデル1171
Revesion History : 2007/08/08 武波(中訊) 仕様変更モデル1192
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityBizLogicProvider;
import webbroker3.equity.WEB3EquityContract;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityRealizedProfitAndLossPrice;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.WEB3EquityTypeOrderManagerReusableValidations;
import webbroker3.equity.WEB3MarginClientRequestService;
import webbroker3.equity.WEB3MarginCloseMarginUpdateInterceptor;
import webbroker3.equity.WEB3MarginSettleContractOrderSpec;
import webbroker3.equity.define.WEB3MarginBeforeRequestDivDef;
import webbroker3.equity.message.WEB3MarginAfterRepayCalcInfoResponse;
import webbroker3.equity.message.WEB3MarginCloseMarginAttentionUnit;
import webbroker3.equity.message.WEB3MarginCloseMarginCompleteRequest;
import webbroker3.equity.message.WEB3MarginCloseMarginCompleteResponse;
import webbroker3.equity.message.WEB3MarginCloseMarginConfirmRequest;
import webbroker3.equity.message.WEB3MarginCloseMarginConfirmResponse;
import webbroker3.equity.message.WEB3MarginCloseMarginContractUnit;
import webbroker3.equity.message.WEB3MarginCommissionInfoUnit;
import webbroker3.equity.message.WEB3MarginContractUnit;
import webbroker3.equity.service.delegate.WEB3MarginCloseMarginRequestAdapter;
import webbroker3.equity.service.delegate.WEB3MarginCloseMarginService;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.tradingpower.WEB3TPAttentionObjection;
import webbroker3.tradingpower.WEB3TPTradingPowerAfterRepayService;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.tradingpower.define.WEB3TPAttentionObjectionTypeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeNewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderEntry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

/**
 * （信用取引返済サービスImpl）。<BR>
 * <BR>
 * 信用取引返済サービス実装クラス
 * @@author 呉艶飛
 * @@version 1.0
 */
public class WEB3MarginCloseMarginServiceImpl extends WEB3MarginClientRequestService implements WEB3MarginCloseMarginService
{

    /**
     * (ログ出力ユーティリティ)。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3MarginCloseMarginServiceImpl.class);

    /**
     * (execute)。<BR>
     * <BR>
     * 信用取引返済サービス処理を実施する。<BR>
     * <BR>
     * リクエストデータの型により、validate注文()メソッド、<BR>
     * submit注文()メソッドのいずれかをコールする。<BR>
     * <BR>
     * @@param l_request - リクエストデータ<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40569A440057
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;
        
        if (l_request == null)
        {
            log.error(STR_METHOD_NAME + "パラメータNull出来ない。");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
        }

        if (l_request instanceof WEB3MarginCloseMarginConfirmRequest) //validate注文
        {
            l_response = validateOrder((WEB3MarginCloseMarginConfirmRequest) l_request);
        }
        else if (l_request instanceof WEB3MarginCloseMarginCompleteRequest) //submitOrder注文
        {
            l_response = submitOrder((WEB3MarginCloseMarginCompleteRequest) l_request);
        }
        else
        {
            log.error(STR_METHOD_NAME + "パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80018, STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate注文)。<BR>
     * <BR>
     * 信用取引返済発注審査を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（信用取引返済サービス）validate注文１」及び<BR>
     * 「（信用取引返済サービス）validate注文２」参照。<BR>
     * <BR>
     * @@param l_request - リクエストデータ<BR>
     * @@return WEB3MarginCloseMarginConfirmResponse<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40569A8E0299
     */
    protected WEB3GenResponse validateOrder(WEB3MarginCloseMarginConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrder(WEB3MarginCloseMarginConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3MarginCloseMarginConfirmResponse l_response = null;
        try
        {
            //リクエストデータの整合性をチェックする。
            l_request.validate();
            //補助口座を取得する
            WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
            //代理入力者の扱者オブジェクトを取得する。
            WEB3GentradeTrader l_trader = (WEB3GentradeTrader) this.getTrader();

            // validate手動強制決済可能
            WEB3EquityTypeOrderManagerReusableValidations l_orderMgrResValidations =
                (WEB3EquityTypeOrderManagerReusableValidations)
                    WEB3EquityTypeOrderManagerReusableValidations.getInstance();
            boolean l_blnIsManualForcedSettleFlag = l_request.manualForcedSettleFlag;
            l_orderMgrResValidations.validateManualForcedSettlePossible(l_blnIsManualForcedSettleFlag, l_trader);

            //信用取引返済リクエストアダプタオブジェクトを生成する。
            WEB3MarginCloseMarginRequestAdapter l_adapter = this.createRequestAdapter(l_request);

            //執行条件を取得する。
            EqTypeExecutionConditionType l_conditionType = l_adapter.getExecCondType();

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderManager = (WEB3EquityOrderManager) l_tradingModule.getOrderManager();
            WEB3GentradeFinObjectManager l_finObjectManager = (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();
            WEB3EquityBizLogicProvider l_provide = (WEB3EquityBizLogicProvider) l_tradingModule.getBizLogicProvider();
                        
            //建株オブジェクトを取得する。
            WEB3EquityContract l_contract = l_adapter.getContract();
            EqtypeContractRow l_contractRow = (EqtypeContractRow) l_contract.getDataSourceObject();

            //get（Ｗ指値）執行条件
            //（Ｗ指値）執行条件：
            //・リクエスト.発注条件区分＝（”指定なし”、”逆指値”）の場合は、nullをセット。
            //　@・リクエスト.発注条件区分＝”W指値”の場合は、
            //　@　@　@信用取引返済リクエスト.get（Ｗ指値）執行条件( )をセット。
            EqTypeExecutionConditionType l_eqyTypeExecutionConditonType = null;
            if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
            {
                l_eqyTypeExecutionConditonType = l_adapter.getWLimitExecCondType();
            }

            //市場オブジェクトを取得する。
            WEB3GentradeMarket l_market = (WEB3GentradeMarket) l_finObjectManager.getMarket(l_contract.getMarketId());
            //取引時間コンテキストに市場コードをセットする。
            WEB3GentradeTradingTimeManagement.resetMarketCode(l_market.getMarketCode());
            
            //決済建株エントリを作成する。
            EqTypeSettleContractOrderEntry[] l_orderEntry =
                this.createClosingContractEntry(
                    l_request.closeMarginContractUnits,
                    l_adapter);

            //注文失効日：　@リクエスト.注文有効期限　@※”当日限り”注文の場合、nullがセットされる。
            Date l_datExpirationDate = null;
            
            Long l_lngOrderUnitId = null;
            if (WEB3OrderExpirationDateTypeDef.DAY_LIMIT.equals(l_request.expirationDateType))
            {
                l_datExpirationDate = null;
                l_lngOrderUnitId = null;
            }
            else
            {
                // 信用取引返済リクエストアダプタ.get注文有効期限() 
                l_datExpirationDate = l_adapter.getExpirationDate();
                l_lngOrderUnitId = new Long(0);
            }
            //逆指値基準値
            double l_dblStopOrderPrice = 0D;
            //発注条件演算子：
            String l_strOrderCondOperator = null;
            //（W指値）訂正指値：
            double l_dblWLimitPrice = 0D;
            if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
            {
                l_strOrderCondOperator = l_request.wlimitOrderCondOperator;
                l_dblStopOrderPrice = Double.parseDouble(l_request.wlimitOrderCondPrice);
                if (l_request.wLimitPrice != null)
                {
                    l_dblWLimitPrice = Double.parseDouble(l_request.wLimitPrice);
                }
            }
            else if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
            {
                l_strOrderCondOperator = l_request.stopOrderCondOperator;
                l_dblStopOrderPrice = Double.parseDouble(l_request.stopOrderCondPrice);
                l_dblWLimitPrice = 0D;
            }
            else
            {
                l_strOrderCondOperator = "0";
                l_dblStopOrderPrice = 0D;
                l_dblWLimitPrice = 0D;
            }

            //返済注文内容を作成する。
            //初回注文の注文単位ID：
            //・リクエスト.注文期限区分＝”当日限り”の場合は、nullをセット。
            //・リクエスト.注文期限区分＝”出来るまで注文”の場合は、0をセット。
            WEB3MarginSettleContractOrderSpec l_orderSpec =
                WEB3MarginSettleContractOrderSpec.createCloseMarginOrderSpec(
                    l_trader,
                    l_orderEntry,
                    l_adapter.getPrice(),
                    l_conditionType,
                    l_datExpirationDate,
                    l_contractRow.getTaxType(),
                    l_request.priceCondType,
                    l_request.orderCondType,
                    l_strOrderCondOperator,
                    l_dblStopOrderPrice,
                    l_dblWLimitPrice,
                    l_request.closingOrder,
                    l_lngOrderUnitId,
                    l_eqyTypeExecutionConditonType);

            //返済注文の発注審査を行う。
            EqTypeNewOrderValidationResult l_eqTypeNewOrderValidationResult =
                this.validateSettleContractOrder(l_subAccount, l_orderSpec, l_adapter);

            //throw発注審査結果エラー情報(OrderValidationResult, 証券会社, String)
            //発注審査結果：　@validate返済注文()の戻り値
            //証券会社：　@補助口座.getInstitution()
            //銘柄コード：　@建株.getProduct().getProductCode()
            WEB3EquityProduct l_eqtypeProduct = (WEB3EquityProduct)l_contract.getProduct();
            l_orderManager.throwOrderValidationResultErrorInfo(
                l_eqTypeNewOrderValidationResult,
                l_subAccount.getInstitution(),
                l_eqtypeProduct.getProductCode());

            //発注日を取得する。
            Date l_orderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

            //手数料オブジェクトを生成する。
            WEB3GentradeCommission l_commission =
                l_provide.createCommission(
                    l_subAccount,
                    l_market.getMarketCode(),
                    l_orderBizDate,
                    this.getLoginChannel(),
                    l_contractRow.getRepaymentType(),
                    l_contractRow.getRepaymentNum(),
                    OrderCategEnum.CLOSE_MARGIN);

            //指値かどうかを手数料フラグにセットする。
            l_commission.setIsLimitPrice(l_orderSpec.isLimitOrder());

            //取引銘柄オブジェクトを取得する。
            WEB3EquityTradedProduct l_tradedProduct = (WEB3EquityTradedProduct) l_contract.getTradedProduct();

            //概算決済損益代金を取得する。
            WEB3EquityRealizedProfitAndLossPrice l_profitAndLossCalcResult =
                this.getEstimatedRealizedProfitAndLossAmount(
                    l_commission,
                    l_adapter.getPrice(),
                    l_subAccount,
                    l_tradedProduct,
                    l_orderEntry,
                    l_orderSpec.getTotalQuantity(),
                    null,
                    0D,
                    0D,
                    false,
                    l_adapter);

            // 信用返済更新インタセプタ生成
            OpLoginSecurityService l_opLoginSec =
                (OpLoginSecurityService)Services.getService(
                    OpLoginSecurityService.class);
            String l_strOrderRootDiv =
                l_opLoginSec.getSessionProperty(
                    WEB3SessionAttributeDef.ORDER_ROOT_DIV);
            WEB3MarginCloseMarginUpdateInterceptor l_updateInterceptor =
                new WEB3MarginCloseMarginUpdateInterceptor(
                    l_orderSpec,
                    l_commission,
                    l_profitAndLossCalcResult,
                    l_contractRow.getRepaymentType(),
                    l_contractRow.getRepaymentNum(),
                    this.getLoginChannel(),
                    l_strOrderRootDiv);
            
            //返済後余力表示からのリクエストの場合
            if (l_request.requestFromType != null &&
                WEB3MarginBeforeRequestDivDef.AFTER_REPAY.equals(l_request.requestFromType))
            {
                WEB3MarginAfterRepayCalcInfoResponse l_afterRepayResponse =
                    new WEB3MarginAfterRepayCalcInfoResponse(l_request);
                l_afterRepayResponse.interceptor = l_updateInterceptor;
                l_afterRepayResponse.orderSpec = l_orderSpec;
                log.exiting(STR_METHOD_NAME);
                return l_afterRepayResponse;
            }
            
            //レスポンスデータを生成する。
            l_response = (WEB3MarginCloseMarginConfirmResponse)l_request.createResponse();
            
            WEB3MarginContractUnit[] l_contractUnits =
                this.createMarginContractUnitList(
                    l_orderEntry,
                    l_profitAndLossCalcResult.getCalcUnitPrice(),
                    l_adapter);
            
            //信用取引手数料情報を作成
            WEB3MarginCommissionInfoUnit l_commissionInfoUnit = new WEB3MarginCommissionInfoUnit();
            //信用取引手数料情報.手数料コース  ＝　@手数料.get手数料コースコード
            //信用取引手数料情報.手数料       ＝　@手数料.get手数料金額( )
            //信用取引手数料情報.手数料消費税    ＝　@株式計算サービス.calc消費税( )
            l_commissionInfoUnit.commissionCourse = l_commission.getCommissionCourseDiv();
            l_commissionInfoUnit.commission = WEB3StringTypeUtility.formatNumber(l_commission.getCommission());
            l_commissionInfoUnit.commissionConsumptionTax = WEB3StringTypeUtility.formatNumber(
                l_provide.calcSalesTax(l_commission.getCommission(), new Timestamp(l_orderBizDate.getTime()), l_subAccount));
            
            //閉局間近の市場コードを取得する。
            String[] l_strCloseMarket = WEB3GentradeTradingTimeManagement.getTradeCloseMarket(l_subAccount.getWeb3GenBranch(), ProductTypeEnum.EQUITY, l_contractRow.getRepaymentType());

            //概算決済損益代金を取得する。
            double l_dblLossAmount = l_profitAndLossCalcResult.getEstimatedRealizedProfitAndLossAmount();
            //計算単価を取得する。
            double l_dblUnitPrice = l_profitAndLossCalcResult.getCalcUnitPrice();

            //新規注文IDを取得する。
            long l_lngOrderId = l_orderManager.createNewOrderId();
           
            // create返済時注意文言
            WEB3TPAttentionObjection l_attentionObjection =
                this.createCloseMarginAttentionWording(
                    l_subAccount,
	                l_updateInterceptor,
	                l_orderSpec);
            
            //set単価(信用取引返済リクエストアダプタ, WEB3GenResponse)
            //引数は以下の通りに設定する。
            //信用取引返済リクエストアダプタ：　@生成した同名オブジェクト
            //レスポンス：生成したレスポンス
            setPrice(l_adapter, l_response);

            //レスポンスデータに以下の通りプロパティを設定する。
            //レスポンス.確認時発注日：　@取引時間管理.get発注日(void)の戻り値
            l_response.checkDate = WEB3DateUtility.toDay(l_orderBizDate);
            //レスポンス.概算受渡代金：　@概算決済損益代金計算結果.get概算決済損益代金()の戻り値
            l_response.estimatedPrice = WEB3StringTypeUtility.formatNumber(l_dblLossAmount);
            //レスポンス.取引終了警告市場コード一覧：　@取引時間管理.get市場閉局警告市場()の戻り値配列
            l_response.messageSuspension = l_strCloseMarket;
            //レスポンス.建株明細一覧：　@(*上記で編集した建株明細オブジェクトの配列)
            l_response.contractUnits = l_contractUnits;
            //レスポンス.手数料情報：　@(*上記で編集した信用手数料情報オブジェクト）
            l_response.commissionInfo = l_commissionInfoUnit;
            //レスポンス.確認時単価：　@概算決済損益代金計算結果.get計算単価()の戻り値
            l_response.checkPrice = WEB3StringTypeUtility.formatNumber(l_dblUnitPrice);
            //レスポンス.注文ID：　@拡張株式注文マネージャ.createNewOrderId( )の戻り値
            l_response.orderId = "" + l_lngOrderId;
            //レスポンス.インサイダー警告表示フラグ：　@拡張株式注文マネージャー.isインサイダー警告表示()の戻り値
			l_response.insiderWarningFlag = l_orderManager.isInsiderMessageSuspension(l_subAccount, l_contractRow.getProductId());

            WEB3MarginCloseMarginAttentionUnit l_attentionUnit = new WEB3MarginCloseMarginAttentionUnit();
			// レスポンス.返済時注意文言.注意文言表示区分
            if (l_attentionObjection == null)
            {
                //this.create返済時注意文言()の戻り値 == nullの場合、nullをセット(連続注文時)
                l_attentionUnit.attentionDispDiv = null;
            }
            else
            {
                //this.create返済時注意文言()の戻り値 != nullの場合、戻り値をセット
                l_attentionUnit.attentionDispDiv = l_attentionObjection.attentionObjectionType;
            }

            // レスポンス.返済時注意文言.入金請求額
            if (l_attentionObjection == null)
            {
                //this.create返済時注意文言()の戻り値 == nullの場合、nullをセット(連続注文時)
                l_attentionUnit.payClaimAmount = null;
            }
            else if (l_attentionUnit.attentionDispDiv.equals(WEB3TPAttentionObjectionTypeDef.NO_ATTENTION))
            {
                // 注意文言表示なしの場合はnullをセット
	            l_attentionUnit.payClaimAmount = null;
            }
            else
            {
                // 以外、create返済時注意文言()の戻り値.入金請求額
	            l_attentionUnit.payClaimAmount = WEB3StringTypeUtility.formatNumber(l_attentionObjection.demandAmount);
            }

            // レスポンス.返済時注意文言 = プロパティセットした返済時注意文言
            l_response.closeMarginAttention = l_attentionUnit;

            //レスポンス.注文有効期限
            l_response.expirationDate = l_adapter.getExpirationDate();
        }
        catch (NotFoundException l_nfe)
        {
            log.error(this.getClass().getName() + STR_METHOD_NAME, l_nfe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME, l_nfe.getMessage(), l_nfe);
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit注文)。<BR>
     * <BR>
     * 信用取引返済注文登録を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（信用取引返済サービス）submit注文１」及び<BR>
     * 「（信用取引返済サービス）submit注文２」参照。<BR>
     * @@param l_requestOrder - リクエストデータ<BR>
     * <BR>
     * @@return WEB3MarginCloseMarginCompleteResponse<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40569ADB028A
     */
    protected WEB3MarginCloseMarginCompleteResponse submitOrder(WEB3MarginCloseMarginCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitOrder(WEB3MarginCloseMarginCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3MarginCloseMarginCompleteResponse l_response = null;
        try
        {
            //リクエストデータの整合性をチェックする。
            l_request.validate();
            //補助口座を取得する
            WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
            //代理入力者の扱者オブジェクトを取得する。
            WEB3GentradeTrader l_trader = (WEB3GentradeTrader) this.getTrader();

            //validate手動強制決済可能
            WEB3EquityTypeOrderManagerReusableValidations l_orderMgrResValidations =
                (WEB3EquityTypeOrderManagerReusableValidations)
                    WEB3EquityTypeOrderManagerReusableValidations.getInstance();
            boolean l_blnIsManualForcedSettleFlag = l_request.manualForcedSettleFlag;
            l_orderMgrResValidations.validateManualForcedSettlePossible(l_blnIsManualForcedSettleFlag, l_trader);

            //信用取引返済リクエストアダプタオブジェクトを生成する。
            WEB3MarginCloseMarginRequestAdapter l_adapter = this.createRequestAdapter(l_request);

            //執行条件を取得する。
            EqTypeExecutionConditionType l_conditionType = l_adapter.getExecCondType();

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderManager = (WEB3EquityOrderManager) l_tradingModule.getOrderManager();
            WEB3GentradeFinObjectManager l_finObjectManager = (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();
            WEB3EquityBizLogicProvider l_provide = (WEB3EquityBizLogicProvider) l_tradingModule.getBizLogicProvider();
           
            //建株オブジェクトを取得する。
            WEB3EquityContract l_contract = l_adapter.getContract();
            EqtypeContractRow l_contractRow = (EqtypeContractRow) l_contract.getDataSourceObject();

            //get（Ｗ指値）執行条件
            //（Ｗ指値）執行条件：
            //・リクエスト.発注条件区分＝（”指定なし”、”逆指値”）の場合は、nullをセット。
            //　@・リクエスト.発注条件区分＝”W指値”の場合は、
            //　@　@　@信用取引返済リクエスト.get（Ｗ指値）執行条件( )をセット。
            EqTypeExecutionConditionType l_eqyTypeExecutionConditonType = null;
            if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
            {
                l_eqyTypeExecutionConditonType = l_adapter.getWLimitExecCondType();
            }

            //市場オブジェクトを取得する。
            WEB3GentradeMarket l_market = (WEB3GentradeMarket) l_finObjectManager.getMarket(l_contract.getMarketId());
            //取引時間コンテキストに市場コードをセットする。
            WEB3GentradeTradingTimeManagement.resetMarketCode(l_market.getMarketCode());
            
            //決済建株エントリを作成する。
            EqTypeSettleContractOrderEntry[] l_orderEntry =
                this.createClosingContractEntry(
                    l_request.closeMarginContractUnits,
                    l_adapter);

            //注文失効日：　@リクエスト.注文有効期限　@※”当日限り”注文の場合、nullがセットされる。
            Date l_datExpirationDate = null;
            Long l_lngOrderUnitId = null;
            if (WEB3OrderExpirationDateTypeDef.DAY_LIMIT.equals(l_request.expirationDateType))
            {
                l_datExpirationDate = null;
                l_lngOrderUnitId = null;
            }
            else
            {
                // 信用取引返済リクエストアダプタ.get注文有効期限() 
                l_datExpirationDate = l_adapter.getExpirationDate();
                l_lngOrderUnitId = new Long(0);
            }
            //逆指値基準値
            double l_dblStopOrderPrice = 0D;
            //発注条件演算子：
            String l_strOrderCondOperator = null;
            //（W指値）訂正指値：
            double l_dblWLimitPrice = 0D;
            if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
            {
                l_strOrderCondOperator = l_request.wlimitOrderCondOperator;
                l_dblStopOrderPrice = Double.parseDouble(l_request.wlimitOrderCondPrice);
                if (l_request.wLimitPrice != null)
                {
                    l_dblWLimitPrice = Double.parseDouble(l_request.wLimitPrice);
                }
            }
            else if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
            {
                l_strOrderCondOperator = l_request.stopOrderCondOperator;
                l_dblStopOrderPrice = Double.parseDouble(l_request.stopOrderCondPrice);
                l_dblWLimitPrice = 0D;
            }
            else
            {
                l_strOrderCondOperator = "0";
                l_dblStopOrderPrice = 0D;
                l_dblWLimitPrice = 0D;
            }
            //返済注文内容を作成する。
            //初回注文の注文単位ID：
            //・リクエスト.注文期限区分＝”当日限り”の場合は、nullをセット。
            //・リクエスト.注文期限区分＝”出来るまで注文”の場合は、0をセット。
            WEB3MarginSettleContractOrderSpec l_orderSpec =
                WEB3MarginSettleContractOrderSpec.createCloseMarginOrderSpec(
                    l_trader,
                    l_orderEntry,
                    l_adapter.getPrice(),
                    l_conditionType,
                    l_datExpirationDate,
                    l_contractRow.getTaxType(),
                    l_request.priceCondType,
                    l_request.orderCondType,
                    l_strOrderCondOperator,
                    l_dblStopOrderPrice,
                    l_dblWLimitPrice,
                    l_request.closingOrder,
                    l_lngOrderUnitId,
                    l_eqyTypeExecutionConditonType);

            //返済注文の発注審査を行う。
            EqTypeNewOrderValidationResult l_eqTypeNewOrderValidationResult =
                this.validateSettleContractOrder(l_subAccount, l_orderSpec, l_adapter);

            //throw発注審査結果エラー情報(OrderValidationResult, 証券会社, String)
            //発注審査結果：　@validate返済注文()の戻り値
            //証券会社：　@補助口座.getInstitution()
            //銘柄コード：　@建株.getProduct().getProductCode()
            WEB3EquityProduct l_eqtypeProduct = (WEB3EquityProduct)l_contract.getProduct();
            l_orderManager.throwOrderValidationResultErrorInfo(
                l_eqTypeNewOrderValidationResult,
                l_subAccount.getInstitution(),
                l_eqtypeProduct.getProductCode());

            //発注日を取得する。
            if (l_request.checkDate == null)
            {
                l_request.checkDate =
                    WEB3GentradeTradingTimeManagement.getOrderBizDate();
            }
            Date l_orderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate(l_request.checkDate);

            //手数料オブジェクトを生成する。
            WEB3GentradeCommission l_commission =
                l_provide.createCommission(
                    l_subAccount,
                    l_market.getMarketCode(),
                    l_orderBizDate,
                    this.getLoginChannel(),
                    l_contractRow.getRepaymentType(),
                    l_contractRow.getRepaymentNum(),
                    OrderCategEnum.CLOSE_MARGIN);

            //指値かどうかを手数料フラグにセットする。
            l_commission.setIsLimitPrice(l_orderSpec.isLimitOrder());

            //取引銘柄オブジェクトを取得する。
            WEB3EquityTradedProduct l_tradedProduct = (WEB3EquityTradedProduct) l_contract.getTradedProduct();
            
            //概算決済損益代金を取得する。
            if (l_request.checkPrice == null)
            {
                l_request.checkPrice =
                    WEB3StringTypeUtility.formatNumber(l_adapter.getPrice());
            }
            WEB3EquityRealizedProfitAndLossPrice l_profitAndLossCalcResult =
                this.getEstimatedRealizedProfitAndLossAmount(
                    l_commission,
                    Double.parseDouble(l_request.checkPrice),
                    l_subAccount,
                    l_tradedProduct,
                    l_orderEntry,
                    l_orderSpec.getTotalQuantity(),
                    null,
                    0D,
                    0D,
                    false,
                    l_adapter);

            //返済注文登録処理を行う。
            if (l_request.orderId == null)
            {
                l_request.orderId =
                    String.valueOf(l_orderManager.createNewOrderId());
            }
            this.submitSettleContractOrder(
                l_subAccount,
                l_orderSpec,
                Long.parseLong(l_request.orderId),
                l_request.password,
                l_commission,
                l_profitAndLossCalcResult,
                l_adapter);

            //余力再計算を行う。
            this.execReCalcTradingPower(l_subAccount);

            l_response = (WEB3MarginCloseMarginCompleteResponse)l_request.createResponse();
            l_response.lastUpdatedTimestamp = GtlUtils.getSystemTimestamp();
            l_response.orderActionId = l_request.orderId;
            l_response.insiderWarningFlag =
                l_orderManager.isInsiderMessageSuspension(
                    l_subAccount,
                    l_contract.getProduct().getProductId()); 
            //レスポンス.注文有効期限
            l_response.expirationDate = l_adapter.getExpirationDate();
        }
        catch (NotFoundException l_nfe)
        {
            log.error(this.getClass().getName() + STR_METHOD_NAME, l_nfe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME, l_nfe.getMessage(), l_nfe);
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (createリクエストアダプタ)<BR>
     * リクエストアダプタのインスタンスを生成する。<BR>
     * <BR>
     * 信用取引返済リクエストアダプタ.create(引数のリクエスト)をコールする。<BR>
     * @@param l_request - (リクエスト)<BR>
     * リクエストデータ。
     * @@return WEB3MarginCloseMarginRequestAdapter
     */
    protected WEB3MarginCloseMarginRequestAdapter createRequestAdapter(WEB3GenRequest l_request)
    {
        final String STR_METHOD_NAME = "createRequestAdapter(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3MarginCloseMarginRequestAdapter l_requestAdaptor =
            WEB3MarginCloseMarginRequestAdapter.create(l_request);
        
        log.exiting(STR_METHOD_NAME);
        return l_requestAdaptor;
    }
    
    /**
     * (create決済建株エントリ)<BR>
     * 決済建株エントリを作成する。<BR>
     * <BR>
     * 拡張株式注文マネージャ.create決済建株エントリ()をコールする。<BR>
     * <BR>
     * [create決済建株エントリ()に指定する引数]<BR>
     * 注文単位ID：　@0(新規注文)<BR>
     * 注文株数：　@パラメータ.リクエストアダプタ.get注文株数()戻り値<BR>
     * 決済建株明細一覧[]：　@パラメータ.決済建株明細一覧[]<BR>
     * @@param l_closeMarginContractUnits - (決済建株明細一覧)<BR>
     * 信用取引決済建株オブジェクトの配列。<BR>
     * （リクエストデータ）
     * @@param l_requestAdaptor - (リクエストアダプタ)<BR>
     * 信用取引返済リクエストアダプタオブジェクト。
     * @@return EqTypeSettleContractOrderEntry[]
     * @@throws WEB3BaseException
     */
    protected EqTypeSettleContractOrderEntry[] createClosingContractEntry(
        WEB3MarginCloseMarginContractUnit[] l_closeMarginContractUnits,
        WEB3MarginCloseMarginRequestAdapter l_requestAdaptor)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createClosingContractEntry(WEB3MarginCloseMarginContractUnit[], WEB3MarginCloseMarginRequestAdapter)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        EqTypeSettleContractOrderEntry[] l_settleContractOrderEntrys =
            l_orderManager.createClosingContractEntry(
                0L,
                l_requestAdaptor.getOrderQuantity(),
                l_closeMarginContractUnits);
        
        log.exiting(STR_METHOD_NAME);
        return l_settleContractOrderEntrys;
    }
    
    /**
     * (get概算決済損益代金)<BR>
     * 概算決済損益代金を取得する。<BR>
     * <BR>
     * 拡張株式注文マネージャ.calc概算決済損益代金()をコールする。<BR>
     * <BR>
     * [calc概算決済損益代金()にセットするパラメータ]<BR>
     * 　@手数料：　@パラメータの同項目<BR>
     * 　@指値：　@パラメータの同項目<BR>
     * 　@補助口座：　@パラメータの同項目<BR>
     * 　@取引銘柄：　@パラメータの同項目<BR>
     * 　@決済建株エントリ： 　@パラメータの同項目<BR>
     * 　@数量：　@パラメータの同項目<BR>
     * 　@注文単位：　@パラメータの同項目<BR>
     * 　@今回約定数量：　@パラメータの同項目<BR>
     * 　@今回約定単価：　@パラメータの同項目<BR>
     * 　@isSkip金額チェック：　@パラメータの同項目<BR>
     * 　@建株：　@nul<BR>
     * @@param l_genCommission - (手数料)<BR>
     * 手数料オブジェクト。
     * @@param l_dblLimitPrice - (指値)<BR>
     * 指値。<BR>
     * 成行の場合は0をセット。
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト。
     * @@param l_equityTradedProduct - (取引銘柄)<BR>
     * 取引銘柄オブジェクト。
     * @@param l_settleContractOrderEntrys - (決済建株エントリ)<BR>
     * 決済建株エントリの配列。
     * @@param l_dblQuantity - (数量)<BR>
     * 数量。
     * @@param l_orderUnit - (注文単位)<BR>
     * 訂正元／約定対象／約定取消対象注文の注文単位オブジェクト<BR>
     * （新規の注文登録時はnullをセット）
     * @@param l_dblNowExecQuantity - (今回約定数量)<BR>
     * 今回約定数量<BR>
     * （約定／約定取消の場合に編集）
     * @@param l_dblNowExecPrice - (今回約定単価)<BR>
     * 今回約定単価<BR>
     * （約定／約定取消の場合に編集）
     * @@param l_isSkipAmountRange - (isSkip金額チェック)<BR>
     * 計算結果の代金について、妥当性チェックをスキップするかのフラグ。<BR>
     * <BR>
     * チェックを行う場合はfalse、チェックを行わない（スキップする）場合はtrueを指定する。
     * @@param l_requestAdaptor - (リクエストアダプタ)<BR>
     * 信用取引返済リクエストアダプタオブジェクト<BR>
     * @@return WEB3EquityRealizedProfitAndLossPrice
     * @@throws WEB3BaseException
     */
    protected WEB3EquityRealizedProfitAndLossPrice getEstimatedRealizedProfitAndLossAmount(
        WEB3GentradeCommission l_genCommission,
        double l_dblLimitPrice,
        SubAccount l_subAccount,
        WEB3EquityTradedProduct l_equityTradedProduct,
        EqTypeSettleContractOrderEntry[] l_settleContractOrderEntrys,
        double l_dblQuantity,
        EqTypeOrderUnit l_orderUnit,
        double l_dblNowExecQuantity,
        double l_dblNowExecPrice,
        boolean l_isSkipAmountRange,
        WEB3MarginCloseMarginRequestAdapter l_requestAdaptor)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = 
            "getEstimatedRealizedProfitAndLossAmount(WEB3GentradeCommission, " +
            "double, SubAccount, WEB3EquityTradedProduct, " +
            "EqTypeSettleContractOrderEntry[], double, EqTypeOrderUnit, double, double, boolean)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        WEB3EquityRealizedProfitAndLossPrice l_realizedProfitAndLossPrice =
            l_orderManager.calcEstimatedRealizedProfitAndLossAmount(
                l_genCommission,
                l_dblLimitPrice,
                (WEB3GentradeSubAccount)l_subAccount,
                l_equityTradedProduct,
                l_settleContractOrderEntrys,
                l_dblQuantity,
                l_orderUnit,
                l_dblNowExecQuantity,
                l_dblNowExecPrice,
                l_isSkipAmountRange,
                null);
        
        log.exiting(STR_METHOD_NAME);
        return l_realizedProfitAndLossPrice;
    }
    
    /**
     * (create建株明細一覧)<BR>
     * 決済建株エントリより信用取引建株明細の一覧を<BR>
     * 作成する。<BR>
     * <BR>
     * １）　@ArrayListを生成する。<BR>
     * <BR>
     * ２）　@パラメータ.決済建株エントリの要素数分、<BR>
     * 　@以下の処理を実施する。<BR>
     * 　@２－１）　@株式ポジションマネージャ.get建株()にて<BR>
     * 　@　@建株を取得する。<BR>
     * <BR>
     * 　@　@[get建株()に指定する引数]<BR>
     * 　@　@　@建株ID：　@処理対象の要素.getContractId()<BR>
     * <BR>
     * 　@２－２）　@信用取引建株明細インスタンスを生成する。<BR>
     * <BR>
     * 　@２－３）　@生成したインスタンスに以下のプロパティをセットする。<BR>
     * 　@　@ID：　@建株.建株ID<BR>
     * 　@　@建日 = 建株.建日<BR>
     * 　@　@建単価 = 建株.建単価<BR>
     * 　@　@建株数 = 建株.建株数<BR>
     * 　@　@建代金 = 建株.get建代金(注文株数(*1))<BR>
     * 　@　@評価損益 =<BR>
     * 　@　@　@建株.get評価損益（建株諸経費考慮）(計算単価(*2), 注文株数)<BR>
     * 　@　@注文株数 = 注文株数<BR>
     * 　@　@内出来数量 = null<BR>
     * 　@　@決済順位 = index + 1<BR>
     * <BR>
     * 　@　@(*1)注文株数・・・処理対象の要素.getQuantity()<BR>
     * <BR>
     * 　@　@(*2)計算単価<BR>
     * 　@　@　@　@成行注文（引数のリクエストアダプタ.get単価()==0）の場合、引数の計算単価（＝時価）。<BR>
     * 　@　@　@　@指値注文の場合、時価を取得してセット。<BR>
     * 　@　@　@　@　@（拡張プロダクトマネージャ.get時価(取引銘柄)。<BR>
     * 　@　@　@　@　@　@取引銘柄は、 建株.getTradedProduct()で取得する。）<BR>
     * <BR>
     * 　@２－４）　@ArrayListにプロパティセットしたインスタンスを追加する。<BR>
     * <BR>
     * ３）　@ArrayList.toArray()の戻り値を返却する。<BR>
     * @@param l_settleContractOrderEntrys - (決済建株エントリ)<BR>
     * 決済建株エントリの配列。
     * @@param l_dblUnitPrice - (計算単価)<BR>
     * 計算単価。
     * @@param l_requestAdaptor - (リクエストアダプタ)<BR>
     * 信用取引返済リクエストアダプタオブジェクト。
     * @@return WEB3MarginContractUnit[]
     * @@throws WEB3BaseException
     */
    protected WEB3MarginContractUnit[] createMarginContractUnitList(
        EqTypeSettleContractOrderEntry[] l_settleContractOrderEntrys,
        double l_dblUnitPrice,
        WEB3MarginCloseMarginRequestAdapter l_requestAdaptor)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createMarginContractUnitList(EqTypeSettleContractOrderEntry[], double, WEB3MarginCloseMarginRequestAdapter)";
        log.entering(STR_METHOD_NAME);
        
        List l_lstContractUnit = new ArrayList();
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityPositionManager l_positionManager =
            (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
        WEB3EquityProductManager l_productManager =
            (WEB3EquityProductManager)l_tradingModule.getProductManager();
        int l_intOrderEntryLength = 0;
        if (l_settleContractOrderEntrys != null)
        {
            l_intOrderEntryLength = l_settleContractOrderEntrys.length;
        }
        
        double l_dblCalcUnitPrice = 0.0D;
        for (int i = 0; i < l_intOrderEntryLength; i++)
        {
            WEB3EquityContract l_equityContract = null;
            try
            {
                l_equityContract =
                    (WEB3EquityContract)l_positionManager.getContract(l_settleContractOrderEntrys[i].getContractId());
            }
            catch (NotFoundException l_nfe)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe);
            }
            
            WEB3MarginContractUnit l_contractUnit = new WEB3MarginContractUnit();
            l_contractUnit.id = Long.toString(l_equityContract.getContractId());
            l_contractUnit.openDate = WEB3DateUtility.toDay(l_equityContract.getOpenDate());
            double l_dblContractPrice = l_equityContract.getContractPrice();
            if (Double.isNaN(l_dblContractPrice))
            {
                l_dblContractPrice = 0D;
            }
            l_contractUnit.contractPrice = WEB3StringTypeUtility.formatNumber(l_dblContractPrice);
            double l_dblContractQuantity = l_equityContract.getQuantity();
            if (Double.isNaN(l_dblContractQuantity))
            {
                l_dblContractPrice = 0D;
            }
            l_contractUnit.contractQuantity = WEB3StringTypeUtility.formatNumber(l_dblContractQuantity);
            double l_dblQuantity = l_settleContractOrderEntrys[i].getQuantity();
            if (Double.isNaN(l_dblQuantity))
            {
                l_dblQuantity = 0D;
            }
            l_contractUnit.contractExecPrice = WEB3StringTypeUtility.formatNumber(
                l_equityContract.getContractAmount(l_dblQuantity));
                
            if (l_dblCalcUnitPrice == 0.0D)
            {
	            if (l_requestAdaptor.getPrice() == 0.0D)
	            {
	                l_dblCalcUnitPrice = l_dblUnitPrice;
	            }
	            else
	            {
	                l_dblCalcUnitPrice =
	                    l_productManager.getCurrentPrice(
	                        (EqTypeTradedProduct)l_equityContract.getTradedProduct());
	            }
            }
            l_contractUnit.appraisalProfitLoss =
                WEB3StringTypeUtility.formatNumber(
                    l_equityContract.getAppraisalProfitOrLossExpenses(
                        l_dblCalcUnitPrice, l_dblQuantity));

            l_contractUnit.orderQuantity = WEB3StringTypeUtility.formatNumber(l_dblQuantity);
            l_contractUnit.partContQuantity = null;
            l_contractUnit.settlePriority = Integer.toString(i + 1);
            
            l_lstContractUnit.add(l_contractUnit);
        }
        
        WEB3MarginContractUnit[] l_contractUnits =
            new WEB3MarginContractUnit[l_lstContractUnit.size()];
        l_lstContractUnit.toArray(l_contractUnits);
        
        log.exiting(STR_METHOD_NAME);
        return l_contractUnits;
    }
    
    /**
     * (validate返済注文)<BR>
     * 信用取引返済注文発注審査を行う。<BR>
     * <BR>
     * 拡張株式注文マネージャ.validate返済注文()<BR>
     * をコールする。<BR>
     * <BR>
     * [validate返済注文()に指定する引数]<BR>
     * 　@補助口座：　@パラメータ.補助口座<BR>
     * 　@信用返済注文内容：　@パラメータ.信用返済注文内容<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト。
     * @@param l_orderSpec - (信用返済注文内容)<BR>
     * 信用返済注文内容オブジェクト。
     * @@param l_requestAdaptor - (リクエストアダプタ)<BR>
     * 信用取引返済リクエストアダプタオブジェクト。
     * @@return EqTypeNewOrderValidationResult
     * @@throws WEB3BaseException
     */
    protected EqTypeNewOrderValidationResult validateSettleContractOrder(
        WEB3GentradeSubAccount l_subAccount,
        EqTypeSettleContractOrderSpec l_orderSpec,
        WEB3MarginCloseMarginRequestAdapter l_requestAdaptor)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateSettleContractOrder(WEB3GentradeSubAccount, EqTypeSettleContractOrderSpec, WEB3MarginCloseMarginRequestAdapter)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        EqTypeNewOrderValidationResult l_result =
            l_orderManager.validateSettleContractOrder(l_subAccount, l_orderSpec);

        log.exiting(STR_METHOD_NAME);
        return l_result;
    }
    
    /**
     * (submit返済注文)<BR>
     * 信用取引返済注文を登録する。<BR>
     * <BR>
     * １）　@信用返済更新インタセプタを生成し、<BR>
     * 　@拡張株式注文マネージャのThreadLocalにセットする。<BR>
     * <BR>
     * 　@[コンストラクタに指定する引数]<BR>
     * 　@　@信用返済注文内容：　@信用返済注文内容<BR>
     * 　@　@手数料：　@手数料<BR>
     * 　@　@概算決済損益代金計算結果：　@計算結果<BR>
     * 　@　@弁済区分：　@建株(*1).弁済区分<BR>
     * 　@　@弁済期限値：　@建株.弁済期限値<BR>
     * 　@　@初回注文の注文チャネル：　@this.getログインチャネル()<BR>
     * 　@　@注文経路区分：　@セッションから取得<BR>
     * 　@　@手動強制決済フラグ：　@引数のリクエストアダプタ.リクエスト.手動強制決済フラグ<BR>
     * <BR>
     * 　@　@(*1)返済リクエストアダプタ.get建株()にて取得。<BR>
     * <BR>
     * ２）　@拡張株式注文マネージャ.submitSettleContractOrder(<BR>
     * 補助口座, 信用返済注文内容, 注文ID, 取引パスワード, true（＝発注審査をスキップする）)をコールする。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト。
     * @@param l_orderSpec - (信用返済注文内容)<BR>
     * 信用返済注文内容オブジェクト。
     * @@param l_lngOrderId - (注文ID)<BR>
     * 注文ID。
     * @@param l_strTradingPassword - (取引パスワード)<BR>
     * 取引パスワード。
     * @@param l_commission - (手数料)<BR>
     * 手数料オブジェクト。
     * @@param l_profitAndLossCalcResult - (計算結果)<BR>
     * 概算決済損益代金計算結果オブジェクト。
     * @@param l_requestAdaptor - (返済リクエストアダプタ)<BR>
     * 信用取引返済リクエストアダプタオブジェクト。
     * @@throws WEB3BaseException
     */
    protected void submitSettleContractOrder(
        WEB3GentradeSubAccount l_subAccount,
        WEB3MarginSettleContractOrderSpec l_orderSpec,
        long l_lngOrderId,
        String l_strTradingPassword,
        WEB3GentradeCommission l_commission,
        WEB3EquityRealizedProfitAndLossPrice l_profitAndLossCalcResult,
        WEB3MarginCloseMarginRequestAdapter l_requestAdaptor)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitSettleContractOrder(WEB3GentradeSubAccount, WEB3MarginSettleContractOrderSpec, long, String, WEB3GentradeCommission, WEB3EquityRealizedProfitAndLossPrice, WEB3MarginCloseMarginRequestAdapter)";
        log.entering(STR_METHOD_NAME);
        
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
        String l_orderRootDiv = l_opLoginSec.getSessionProperty(
            WEB3SessionAttributeDef.ORDER_ROOT_DIV);
        WEB3EquityContract l_contract = l_requestAdaptor.getContract();
        EqtypeContractRow l_contractRow = (EqtypeContractRow)l_contract.getDataSourceObject();
        // 手動強制決済フラグ：　@引数のリクエストアダプタ.リクエスト.手動強制決済フラグ
        WEB3MarginCloseMarginCompleteRequest l_closeMarginComfireRequest =
            (WEB3MarginCloseMarginCompleteRequest)l_requestAdaptor.request;
        boolean l_blnIsManualForcedSettleFlag = l_closeMarginComfireRequest.manualForcedSettleFlag;
        WEB3MarginCloseMarginUpdateInterceptor l_updateInterceptor =
            new WEB3MarginCloseMarginUpdateInterceptor(
                l_orderSpec,
                l_commission,
                l_profitAndLossCalcResult,
                l_contractRow.getRepaymentType(),
                l_contractRow.getRepaymentNum(),
                this.getLoginChannel(),
                l_orderRootDiv,
                l_blnIsManualForcedSettleFlag);
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_updateInterceptor);
        
        EqTypeOrderSubmissionResult l_result =
            l_orderManager.submitSettleContractOrder(
                l_subAccount,
                l_orderSpec,
                l_lngOrderId,
                l_strTradingPassword,
                true);
        if (l_result.getProcessingResult().isFailedResult())
        {
            log.debug("ProcessingResult() = " + l_result.getProcessingResult());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_result.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (create返済時注意文言)<BR>
     * レスポンスに設定する、返済時注意文言を取得する。<BR>
     * <BR>
     * 返済後余力サービス.create返済時注意文言()をコールし、 <BR>
     * 戻り値を返却する。<BR>
     * <BR>
     * [返済時注意文言()に指定する引数]<BR> 
     * 補助口座：　@引数の補助口座  <BR>
     * 注文内容インタセプタ：　@引数の信用返済更新インタセプタ<BR>  
     * 注文内容：　@引数の返済注文内容<BR>
     * @@param　@l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト
     * @@param l_updateInterceptor - (信用返済更新インタセプタ)<BR>
     * 信用返済更新インタセプタ
     * @@param l_orderSpec - (返済注文内容)<BR>
     * 返済注文内容
     * @@return WEB3TPAttentionObjection
     * @@throws WEB3BaseException
     */
    protected WEB3TPAttentionObjection createCloseMarginAttentionWording(
        WEB3GentradeSubAccount l_subAccount,
        WEB3MarginCloseMarginUpdateInterceptor l_updateInterceptor,
        WEB3MarginSettleContractOrderSpec l_orderSpec)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createCloseMarginAttentionWording(WEB3GentradeSubAccount l_subAccount, WEB3MarginCloseMarginUpdateInterceptor l_updateInterceptor, WEB3MarginSettleContractOrderSpec l_orderSpec)";
        log.entering(STR_METHOD_NAME);

        // 返済後余力サービス取得
        WEB3TPTradingPowerAfterRepayService l_tpAfterRepay =
            (WEB3TPTradingPowerAfterRepayService)Services.getService(WEB3TPTradingPowerAfterRepayService.class);

        // create返済時注意文言
        WEB3TPAttentionObjection l_tpAttObj = 
            l_tpAfterRepay.createWEB3TPAttentionObjection(
                l_subAccount,
                l_updateInterceptor,
                l_orderSpec);
            
        log.exiting(STR_METHOD_NAME);
        return l_tpAttObj;
    }

    /**
     * (exec余力再計算)<BR>
     * 余力再計算を行う。<BR>
     * <BR>
     * 引数は以下の通り指定する。 <BR>
     * <BR>
     * 補助口座：　@get補助口座()の戻り値<BR>
     * <BR>
     * @@param　@l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト
     * @@throws WEB3BaseException
     */
    protected void execReCalcTradingPower(WEB3GentradeSubAccount l_subAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execReCalcTradingPower(WEB3GentradeSubAccount l_subAccount)";
        log.entering(STR_METHOD_NAME);

        //取引余力サービス.余力再計算（補助口座）
        WEB3TPTradingPowerService l_tradingPowerService
            = (WEB3TPTradingPowerService) Services.getService(WEB3TPTradingPowerService.class);
        l_tradingPowerService.reCalcTradingPower(l_subAccount);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set単価)<BR>
     * 何もせずにリターンする。<BR>
     * <BR>
     * @@param l_requestAdapter - (信用取引返済リクエストアダプタ)<BR>
     * 信用取引返済リクエストアダプタ。<BR>
     * @@param l_response - (レスポンス)<BR>
     * レスポンス<BR>
     * @@throws WEB3BaseException
     */
    protected void setPrice(
        WEB3MarginCloseMarginRequestAdapter l_requestAdapter,
        WEB3GenResponse l_response)
        throws WEB3BaseException
    {
        
    }
}
@
