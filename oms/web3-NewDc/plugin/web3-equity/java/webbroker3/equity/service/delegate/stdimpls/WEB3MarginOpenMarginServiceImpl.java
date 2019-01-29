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
filename	WEB3MarginOpenMarginServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引新規建サービスImpl(WEB3MarginOpenMarginServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/28 王暁傑 (Sinocom) 新規作成 
Revesion History : 2006/11/24 唐性峰 (Sinocom)　@モデルNo.1001,No.1003,No.1025,No.1079
Revesion History : 2006/12/26 張騰宇 (中訊) モデル 1091
Revesion History : 2007/01/08 柴双紅 (中訊) モデル 1097
Revesion History : 2007/01/17 唐性峰　@(中訊)モデルNo.1107
Revesion History : 2007/01/24 唐性峰　@(中訊)モデルNo.1112
Revesion History : 2007/06/13 何文敏　@(中訊)モデルNo.1169
Revesion History : 2007/08/08 武波(中訊) 仕様変更モデル1192
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3RepaymentDivDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

import webbroker3.equity.WEB3EquityBizLogicProvider;
import webbroker3.equity.WEB3EquityEstimatedContractPrice;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.WEB3MarginClientRequestService;
import webbroker3.equity.WEB3MarginNewOrderValidationResult;
import webbroker3.equity.WEB3MarginOpenContractOrderSpec;
import webbroker3.equity.WEB3MarginOpenMarginUpdateInterceptor;
import webbroker3.equity.define.WEB3MarginTradeTypeDef;
import webbroker3.equity.message.WEB3MarginCommissionInfoUnit;
import webbroker3.equity.message.WEB3MarginOpenMarginCompleteRequest;
import webbroker3.equity.message.WEB3MarginOpenMarginCompleteResponse;
import webbroker3.equity.message.WEB3MarginOpenMarginConfirmRequest;
import webbroker3.equity.message.WEB3MarginOpenMarginConfirmResponse;
import webbroker3.equity.service.delegate.WEB3MarginOpenMarginRequestAdapter;
import webbroker3.equity.service.delegate.WEB3MarginOpenMarginService;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import webbroker3.gentrade.WEB3GentradeBranchMarketRepayDealtCond;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.BranchMarketRepayDealtCondRow;

/** 
 * （信用取引新規建サービスImpl）。<BR>
 * <BR>
 * 信用取引新規建サービス実装クラス
 * @@author 王暁傑
 * @@version 1.0
 */
public class WEB3MarginOpenMarginServiceImpl extends WEB3MarginClientRequestService implements WEB3MarginOpenMarginService 
{

    /**
     * (ログ出力ユーティリティ)。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MarginOpenMarginServiceImpl.class);
            
    /**
     * (コンストラクタ)。<BR>
     * @@roseuid 4140066F004D
     */
    public WEB3MarginOpenMarginServiceImpl() 
    {
    }
    
    /**
     * (execute)。<BR>
     * <BR>
     * 信用取引新規建サービス処理を実施する。<BR>
     * <BR>
     * リクエストデータの型により、validate注文()メソッド、<BR>
     * submit注文()メソッドのいずれかをコールする。<BR>
     * <BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40555CB4024B
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = getClass().getName() + "." + "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        
        if (l_request instanceof WEB3MarginOpenMarginConfirmRequest)
        {
            l_response = this.validateOrder((WEB3MarginOpenMarginConfirmRequest) l_request);
        }
        else if (l_request instanceof WEB3MarginOpenMarginCompleteRequest)
        {
            l_response = this.submitOrder((WEB3MarginOpenMarginCompleteRequest) l_request);
        }
        else
        {
            log.error(STR_METHOD_NAME + "パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80018, STR_METHOD_NAME);
        }
        return l_response;
    }
    
    /**
     * (validate注文)。<BR>
     * <BR>
     * 信用取引新規建発注審査を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（信用取引新規建サービス）validate注文」参照。<BR>
     * <BR>
     *  取引銘柄オブジェクトを取得できない場合は、<BR>
     * 「指定銘柄は指定市場での取扱不可」の例外をthrow<BR>
     *  class    : WEB3BusinessLayerException<BR>
     *  tag      : BUSINESS_ERROR_00638<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 信用取引新規建注文確認リクエストデータオブジェクト
     * @@return WEB3MarginOpenMarginConfirmResponse
     * @@roseuid 4055636B0345
     */
    protected WEB3MarginOpenMarginConfirmResponse validateOrder(WEB3MarginOpenMarginConfirmRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrder";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        
        //リクエスト.validate()
        l_request.validate();
        
        //補助口座を取得
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        
        //代理入力者を取得
        Trader l_trader = this.getTrader();
        
        //信用取引新規建リクエストアダプタ を生成
        WEB3MarginOpenMarginRequestAdapter l_openMarginRequestAdapter = this.createRequestAdapter(l_request);
        
        //is買建：　@信用取引新規建リクエストアダプタ.is買建( ) 
        boolean l_blnIsBuy = l_openMarginRequestAdapter.isLong();
        
        //銘柄コード：　@リクエスト.銘柄コード 
        String l_strProductCode = l_request.productCode;

        //数量：　@リクエスト.注文株数 
        double l_dblQuantity = Double.parseDouble(l_request.orderQuantity);

        // get市場コード( )
        l_request.marketCode = l_openMarginRequestAdapter.getMarketCode();

        //市場コード：　@リクエスト.市場コード
        String l_strMarketCode = l_request.marketCode;

        // reset市場コード(市場コード : String)
        WEB3GentradeTradingTimeManagement.resetMarketCode(l_strMarketCode);

        //指値：　@get単価()の戻り値
        double l_dblLimitPrice = l_openMarginRequestAdapter.getPrice();

        //執行条件：　@信用取引新規建リクエストアダプタ.get執行条件( ) 
        EqTypeExecutionConditionType l_strExecutionCondition = l_openMarginRequestAdapter.getExecutionCondition();
        
        //注文失効日：　@信用取引新規建リクエストアダプタ.get注文有効期限()の戻り値
        //※”当日限り”注文の場合、nullがセットされる。
        Date l_datExpirationDate = null;
        if (WEB3OrderExpirationDateTypeDef.DAY_LIMIT.equals(l_request.expirationDateType))
        {
            l_datExpirationDate = null;
        }
        else if (WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(l_request.expirationDateType))
        {
            l_datExpirationDate = l_openMarginRequestAdapter.getExpirationDate();
        }
        //税区分：　@信用取引新規建リクエストアダプタ.get税区分( ) 
        TaxTypeEnum l_taxType = l_openMarginRequestAdapter.getTaxType();

        //get（Ｗ指値）執行条件( )
        //（Ｗ指値）執行条件を取得する。
        //　@　@リクエスト.発注条件区分＝ （”指定なし”、”逆指値”）の場合は、nullをセット。
        //　@リクエスト.発注条件区分＝”W指値”の場合は、
        //　@　@信用取引新規建リクエストアダプタ.get（Ｗ指値）執行条件の戻り値をセット。
        EqTypeExecutionConditionType l_wLimitExecCondType = null;
        if (WEB3OrderingConditionDef.DEFAULT.equals(l_request.orderCondType)
            || WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            l_wLimitExecCondType = null;
        }
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            l_wLimitExecCondType = l_openMarginRequestAdapter.getWLimitExecCondType();
        }

        //発注条件：　@リクエスト.発注条件区分 
        String l_strOrderCondType = l_request.orderCondType;
        
        //発注条件演算子： 
        //リクエスト.発注条件区分＝”指定なし”の場合は、0。 
        //リクエスト.発注条件区分＝”逆指値”の場合は、リクエスト.逆指値用発注条件演算子。 
        //リクエスト.発注条件区分＝”W指値”の場合は、リクエスト.W指値用発注条件演算子。 
        //逆指値基準値： 
        //リクエスト.発注条件区分＝”指定なし”の場合は、0。 
        //リクエスト.発注条件区分＝”逆指値”の場合は、リクエスト.逆指値用発注条件単価。 
        //リクエスト.発注条件区分＝”W指値”の場合は、リクエスト.W指値用発注条件単価。
        //（W指値）訂正指値： 
        //リクエスト.発注条件区分＝（”指定なし”、”逆指値”）の場合は、0。 
        //リクエスト.発注条件区分＝”W指値”の場合は、リクエスト.W指値用注文単価(*1)。 
        //(*1)nullの場合は、0をセット。 
        String l_strOrderCondOperator = null;
        double l_dblStopBasePrice = 0D;
        double l_dblWLimitChangePrice = 0D;
        if (WEB3OrderingConditionDef.DEFAULT.equals(l_request.orderCondType))
        {
            l_strOrderCondOperator = "0";
            l_dblStopBasePrice = 0D;
            l_dblWLimitChangePrice = 0D;
        }
        else if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            l_strOrderCondOperator = l_request.stopOrderCondOperator;
            l_dblStopBasePrice = Double.parseDouble(l_request.stopOrderCondPrice);
            l_dblWLimitChangePrice = 0D;
        }
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            l_strOrderCondOperator = l_request.wlimitOrderCondOperator;
            l_dblStopBasePrice = Double.parseDouble(l_request.wlimitOrderCondPrice);
            if (WEB3StringTypeUtility.isNumber(l_request.wLimitPrice))
            {
                l_dblWLimitChangePrice = Double.parseDouble(l_request.wLimitPrice);                
            }
        }
        
        //弁済区分：　@リクエスト.弁済.弁済区分 
        String l_strRepaymentDiv = l_request.repayment.repaymentDiv;
        
        //弁済期限値：　@リクエスト.弁済.弁済期限         
        double l_dblRepaymentNum = 0D;
        if (WEB3StringTypeUtility.isNumber(l_request.repayment.repaymentTimeLimit))
        {
            l_dblRepaymentNum = Double.parseDouble(l_request.repayment.repaymentTimeLimit);
        }
        
        //初回注文の注文単位ID： 
        //リクエスト.注文期限区分＝”当日限り”の場合は、nullをセット。 
        //リクエスト.注文期限区分＝”出来るまで注文”の場合は、0をセット。
        Long l_lngFirstOrderUnitId = null;;
        if (WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(l_request.expirationDateType))
        {
            l_lngFirstOrderUnitId = new Long(0);
        }
        else if (WEB3OrderExpirationDateTypeDef.DAY_LIMIT.equals(l_request.expirationDateType))
        {
            l_lngFirstOrderUnitId = null;
        }

        //create新規建注文内容
        WEB3MarginOpenContractOrderSpec l_openContractOrderSpec = 
            WEB3MarginOpenContractOrderSpec.createOpenMarginOrderSpec(
                l_trader, 
                l_blnIsBuy, 
                l_strProductCode, 
                l_strMarketCode, 
                l_dblQuantity, 
                l_dblLimitPrice,
                l_strExecutionCondition, 
                l_datExpirationDate, 
                l_taxType, 
                l_request.priceCondType,
                l_strOrderCondType, 
                l_strOrderCondOperator, 
                l_dblStopBasePrice, 
                l_dblWLimitChangePrice, 
                l_strRepaymentDiv, 
                l_dblRepaymentNum, 
                l_lngFirstOrderUnitId,
                l_wLimitExecCondType);
        
        //create手数料()
        WEB3EquityBizLogicProvider l_bizLogicProvider =
            (WEB3EquityBizLogicProvider)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getBizLogicProvider();
		//        補助口座：　@this.get補助口座( ) 
		//        市場コード：　@リクエスト.市場コード 
		//        発注日：　@確認時（validate注文）の場合、取引時間管理.get発注日(void)。 
		//　@　@　@        完了時（submit注文）の場合、リクエスト.確認時発注日。 
		//        注文チャネル：　@this.getログインチャネル( ) 
		//        信用取引区分：　@リクエスト.弁済.弁済区分 
		//        弁済期限値：　@リクエスト.弁済.弁済期限 
		//        注文カテゴリ：　@OrderCategEnum.”新規建注文”（OPEN_MARGIN）

        WEB3GentradeCommission l_commission = l_bizLogicProvider.createCommission(
            l_subAccount, 
            l_strMarketCode, 
            WEB3GentradeTradingTimeManagement.getOrderBizDate(), 
            this.getLoginChannel(), 
            l_strRepaymentDiv, 
            l_dblRepaymentNum, 
            OrderCategEnum.OPEN_MARGIN);
            
        WEB3EquityProductManager l_productManager =
            (WEB3EquityProductManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();
        
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
        
        //validate銘柄コード（信用）：売買停止中かどうかのチェック
        //（翌日上場予定銘柄への対応：売建指値チェックより前に実行要）
		WEB3EquityProduct l_product =
            (WEB3EquityProduct)l_orderManager.validateProductCode(
            l_strProductCode, l_subAccount.getInstitution().getInstitutionCode(), l_strRepaymentDiv);
        
        //get取引銘柄
        //証券会社：　@補助口座.証券会社IDの証券会社オブジェクト 
        //銘柄コード：　@リクエスト.銘柄コード 
        //市場コード：　@リクエスト.市場コード
        WEB3EquityTradedProduct l_tradedProduct = null;
        try
        {
            l_tradedProduct =
                (WEB3EquityTradedProduct)l_productManager.getTradedProduct(
                l_subAccount.getInstitution(), l_strProductCode, l_strMarketCode);    
        }
        catch (NotFoundException l_nfex)
        {
            //取引銘柄オブジェクトを取得できない場合は、
            //「指定銘柄は指定市場での取扱不可」の例外をthrow
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00638, STR_METHOD_NAME);
        }
        
        //calc注文時建代金
        //引数は以下の通りに設定する。
        //
        //手数料 : 作成した手数料オブジェクト
        //指値 : 新規建注文内容.getLimitPrice()
        //（W指値）訂正指値 :　@新規建注文内容.get（W指値）訂正指値()
        //逆指値基準値 : 新規建注文内容.get逆指値基準値()
        //執行条件 : 新規建注文内容.getExecConditionType()
        //（W指値）執行条件 : 新規建注文内容.get（W指値）執行条件()
        //値段条件 : 新規建注文内容.get値段条件()
        //発注条件 : 新規建注文内容.get発注条件()
        //確認時取得時価 :
        //　@確認の場合、null（固定）
        //　@完了の場合、リクエスト.確認時単価
        //isストップ注文有効 : false（固定）
        //is売建 :　@信用取引新規建リクエストアダプタ.is売建()
        //補助口座 : this.get補助口座()
        //取引銘柄 : get取引銘柄()
        //株数 : リクエスト.注文株数
        //約定数量 : 0固定
        //合計約定金額 : 0固定
        //isSkip金額チェック : false（スキップしない）固定
        boolean l_blnIsSale = l_openMarginRequestAdapter.isShort();
        
        WEB3EquityEstimatedContractPrice l_dblContractAmountAtOrder =
            l_orderManager.calcContractAmountAtOrder(
                l_commission,
                l_openContractOrderSpec.getLimitPrice(),
                l_openContractOrderSpec.getWLimitPrice(),
                l_openContractOrderSpec.getStopOrderPrice(),
                l_openContractOrderSpec.getExecConditionType(),
                l_openContractOrderSpec.getWlimitExecCondType(),
                l_openContractOrderSpec.getPriceConditionType(),
                l_openContractOrderSpec.getOrderConditionType(),
                null,
                false,
                l_blnIsSale,
                l_subAccount,
                l_tradedProduct,
                l_dblQuantity,
                0,
                0,
                false);

        //set計算単価
        //計算単価をセットする。
        //引数は以下の通りにセットする。
        //計算単価：　@calc注文時建代金( )の戻り値オブジェクト.get計算単価( )
        l_openContractOrderSpec.setCalcUnitPrice(
            l_dblContractAmountAtOrder.getCalcUnitPrice());

        //set建代金
        //引数は以下の通りに設定する。
        //建代金：　@calc注文時建代金( )の戻り値オブジェクト.get概算建代金( )
        l_openContractOrderSpec.setContractAmount(
            l_dblContractAmountAtOrder.getEstimatedContractPrice());

        //validate新規建注文
        WEB3MarginNewOrderValidationResult l_newOrderValidationResult = 
            (WEB3MarginNewOrderValidationResult) l_orderManager.validateOpenContractOrder(l_subAccount, l_openContractOrderSpec);

        //throw発注審査結果エラー情報(OrderValidationResult, 証券会社, String)
        //発注審査結果：　@validate新規建注文()の戻り値
        //証券会社：　@補助口座.getInstitution()
        //銘柄コード：　@信用新規建注文内容.getProductCode()
        l_orderManager.throwOrderValidationResultErrorInfo(
            l_newOrderValidationResult,
            l_subAccount.getInstitution(),
            l_openContractOrderSpec.getProductCode());

        //validate取引余力
        WEB3TPTradingPowerResult l_tpResult =
            this.validateTradingPower(
                l_subAccount,
                l_openContractOrderSpec,
                false,
                l_commission,
                l_newOrderValidationResult);
        
        WEB3MarginOpenMarginConfirmResponse l_openMarginConfirmResponse =
            (WEB3MarginOpenMarginConfirmResponse)l_request.createResponse();
        
        //set単価(信用取引新規建リクエストアダプタ, WEB3GenResponse)
        //引数は以下の通りに設定する。
        //信用取引新規建リクエストアダプタ：　@生成した同名オブジェクト
        //レスポンス：生成したレスポンス
        setPrice(l_openMarginRequestAdapter, l_openMarginConfirmResponse);

        try
        {
            //レスポンス.確認時発注日：　@取引時間管理.get発注日(void)の戻り値
            l_openMarginConfirmResponse.checkDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        
            //レスポンス.概算受渡代金：　@信用新規建注文内容.get建代金( )の戻り値
            l_openMarginConfirmResponse.estimatedPrice =
                WEB3StringTypeUtility.formatNumber(l_openContractOrderSpec.getContractAmount());
        
            //レスポンス.取引終了警告市場コード一覧：　@取引時間管理.get市場閉局警告市場( )の戻り値配列
            String[] l_strTradeCloseMarkets =
                WEB3GentradeTradingTimeManagement.getTradeCloseMarket(
                l_subAccount.getWeb3GenBranch(), ProductTypeEnum.EQUITY, l_strRepaymentDiv);
            l_openMarginConfirmResponse.messageSuspension = l_strTradeCloseMarkets;
        
            //レスポンス.銘柄名：　@拡張プロダクトマネージャ.getProduct(補助口座.証券会社コード, リクエスト.銘柄コード)
            //で取得した株式銘柄.銘柄名
            EqtypeProductRow l_eqtypeProductRow =
                (EqtypeProductRow)l_productManager.getProduct(
                l_subAccount.getInstitution(), l_strProductCode).getDataSourceObject();
            l_openMarginConfirmResponse.productName = l_eqtypeProductRow.getStandardName();
            
            //calc委託手数料
            l_bizLogicProvider.calcCommission(l_commission, l_subAccount);
            
            //レスポンス.手数料情報：　@(* 上記で編集した信用手数料情報オブジェクト)
            WEB3MarginCommissionInfoUnit l_commissionInfoUnit = new WEB3MarginCommissionInfoUnit();
            //信用取引手数料情報.手数料コース  ＝　@手数料.get手数料コースコード
            //信用取引手数料情報.手数料       ＝　@手数料.get手数料金額( )
            //信用取引手数料情報.手数料消費税    ＝　@株式計算サービス.calc消費税( )
            l_commissionInfoUnit.commissionCourse = l_commission.getCommissionCourseDiv();
            l_commissionInfoUnit.commission = WEB3StringTypeUtility.formatNumber(l_commission.getCommission());
            l_commissionInfoUnit.commissionConsumptionTax = WEB3StringTypeUtility.formatNumber(
                l_bizLogicProvider.calcSalesTax(l_commission.getCommission(), l_commission.getOrderBizDate(), l_subAccount));
            
            l_openMarginConfirmResponse.commissionInfo = l_commissionInfoUnit;
            
            //部店市場弁済別）取扱条件を生成
            //証券会社コード：　@補助口座.証券会社コード 
            //部店コード：　@get補助口座().getMainAccount(),部店コード 
            //市場コード：　@リクエスト.市場コード 
            //弁済区分：　@リクエスト.弁済.弁済区分 
            //弁済期限値：　@リクエスト.弁済.弁済期限 

            WEB3GentradeBranchMarketRepayDealtCond l_branchMarketRepayDealtCond = 
                new WEB3GentradeBranchMarketRepayDealtCond(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_subAccount.getMainAccount().getBranch().getBranchCode(),
                    l_strMarketCode,
                    l_strRepaymentDiv,
                    l_dblRepaymentNum);
            BranchMarketRepayDealtCondRow l_branchMarketRepayDealtCondRow =
                (BranchMarketRepayDealtCondRow)l_branchMarketRepayDealtCond.getDataSourceObject();  
            
            //レスポンス.金利：　@
            //リクエスト.取引区分＝"新規買建注文"の場合、（部店市場弁済別）取扱条件.買方金利。
            //リクエスト.取引区分＝"新規売建注文"の場合、（部店市場弁済別）取扱条件.売方金利。
            String l_strInterestRates = null;
            if (WEB3MarginTradeTypeDef.OPEN_LONG_MARGIN.equals(l_request.tradingType))
            {
                //リクエスト.取引区分＝"新規買建注文"の場合、（部店市場弁済別）取扱条件.買方金利。
                l_strInterestRates = WEB3StringTypeUtility.formatNumber(l_branchMarketRepayDealtCondRow.getBuyInterestRate());
            }
            else if (WEB3MarginTradeTypeDef.OPEN_SHORT_MARGIN.equals(l_request.tradingType))
            {
                //リクエスト.取引区分＝"新規売建注文"の場合、（部店市場弁済別）取扱条件.売方金利。
                l_strInterestRates = WEB3StringTypeUtility.formatNumber(l_branchMarketRepayDealtCondRow.getSellInterestRate());
            }
            l_openMarginConfirmResponse.interestRates = l_strInterestRates;
            
            //レスポンス.清算期間(**2)：　@（部店市場弁済別）取扱条件.建株諸経費清算期間
            //(**2)リクエスト.弁済.弁済区分＝"一般信用"、かつリクエスト.弁済.弁済期限値＝"9999999"（＝ALL9）の場合のみセット。
            //以外、nullをセット。
            String l_strClearUpTerm = null;
            
            if (WEB3RepaymentDivDef.REPAYMENT_DIV_MARGIN_GEN.equals(l_strRepaymentDiv)
                && 9999999 == l_dblRepaymentNum)
            {
                l_strClearUpTerm =WEB3StringTypeUtility.formatNumber(l_branchMarketRepayDealtCondRow.getContLiquidateTerm());
            }
            l_openMarginConfirmResponse.clearUpTerm = l_strClearUpTerm;
            
            //レスポンス.確認時単価：　@calc注文時建代金()の戻り値.get確認時取得単価()
            l_openMarginConfirmResponse.checkPrice =
                l_dblContractAmountAtOrder.getCheckGetCurrentPrice();
            
            //レスポンス.注文ID：　@拡張株式注文マネージャ.createNewOrderId( )の戻り値
            l_openMarginConfirmResponse.orderId = "" + l_orderManager.createNewOrderId();
            
            //isインサイダー警告表示
            boolean l_boolIsInsider = l_orderManager.isInsiderMessageSuspension(l_subAccount, l_eqtypeProductRow.getProductId());

            //レスポンス.インサイダー警告表示フラグ
            l_openMarginConfirmResponse.insiderWarningFlag = l_boolIsInsider; 

            if (l_tpResult != null)
            {
                l_openMarginConfirmResponse.attentionObjectionType =
                    l_tpResult.getAttentionObjectionType();
            }

            //レスポンス.市場コード
            l_openMarginConfirmResponse.marketCode = l_request.marketCode;

            //レスポンス.注文有効期限
            l_openMarginConfirmResponse.expirationDate = l_openMarginRequestAdapter.getExpirationDate();

            log.exiting(STR_METHOD_NAME);
            return l_openMarginConfirmResponse;
            
        }
        catch (NotFoundException l_nfex)
        {            
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, STR_METHOD_NAME);
        }
        
    }
    
    /**
     * (submit注文)。<BR>
     * <BR>
     * 信用取引新規建注文登録を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（信用取引新規建サービス）submit注文」参照。<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 信用取引新規建注文完了リクエストデータオブジェクト
     * @@return WEB3MarginOpenMarginCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 405563760103
     */
    protected WEB3MarginOpenMarginCompleteResponse submitOrder(WEB3MarginOpenMarginCompleteRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitOrder";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        
        //リクエスト.validate()
        l_request.validate();
        
        //補助口座を取得
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        
        //代理入力者を取得
        Trader l_trader = this.getTrader();
        
        //信用取引新規建リクエストアダプタ を生成
        WEB3MarginOpenMarginRequestAdapter l_openMarginRequestAdapter = this.createRequestAdapter(l_request);
                
        //is買建：　@信用取引新規建リクエストアダプタ.is買建( ) 
        boolean l_blnIsBuy = l_openMarginRequestAdapter.isLong();
        
        //銘柄コード：　@リクエスト.銘柄コード 
        String l_strProductCode = l_request.productCode;

        //数量：　@リクエスト.注文株数 
        double l_dblQuantity = Double.parseDouble(l_request.orderQuantity);

        // get市場コード( )
        l_request.marketCode = l_openMarginRequestAdapter.getMarketCode();

        //市場コード：　@リクエスト.市場コード
        String l_strMarketCode = l_request.marketCode;

        // reset市場コード(市場コード : String)
        WEB3GentradeTradingTimeManagement.resetMarketCode(l_strMarketCode);

        //指値：　@get単価()の戻り値
        double l_dblLimitPrice = l_openMarginRequestAdapter.getPrice();
        
        //get発注日(確認時発注日 : Date)
        if (l_request.checkDate == null)
        {
            l_request.checkDate =
                WEB3GentradeTradingTimeManagement.getOrderBizDate();
        }
        Date l_orderBizDate =
            WEB3GentradeTradingTimeManagement.getOrderBizDate(l_request.checkDate);

        //執行条件：　@信用取引新規建リクエストアダプタ.get執行条件( ) 
        EqTypeExecutionConditionType l_strExecutionCondition = l_openMarginRequestAdapter.getExecutionCondition();
        
        //注文失効日：　@信用取引新規建リクエストアダプタ.get注文有効期限()の戻り値
        //※”当日限り”注文の場合、nullがセットされる。
        Date l_datExpirationDate = null;
        if (WEB3OrderExpirationDateTypeDef.DAY_LIMIT.equals(l_request.expirationDateType))
        {
            l_datExpirationDate = null;
        }
        else if (WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(l_request.expirationDateType))
        {
            l_datExpirationDate = l_openMarginRequestAdapter.getExpirationDate();
        }
        //税区分：　@信用取引新規建リクエストアダプタ.get税区分( ) 
        TaxTypeEnum l_taxType = l_openMarginRequestAdapter.getTaxType();

        //get（Ｗ指値）執行条件( )
        //（Ｗ指値）執行条件を取得する。
        //　@　@リクエスト.発注条件区分＝ （”指定なし”、”逆指値”）の場合は、nullをセット。
        //　@リクエスト.発注条件区分＝”W指値”の場合は、
        //　@　@信用取引新規建リクエストアダプタ.get（Ｗ指値）執行条件の戻り値をセット。
        EqTypeExecutionConditionType l_wLimitExecCondType = null;
        if (WEB3OrderingConditionDef.DEFAULT.equals(l_request.orderCondType)
            || WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            l_wLimitExecCondType = null;
        }
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            l_wLimitExecCondType = l_openMarginRequestAdapter.getWLimitExecCondType();
        }

        //発注条件：　@リクエスト.発注条件区分 
        String l_strOrderCondType = l_request.orderCondType;
        
        //発注条件演算子： 
        //リクエスト.発注条件区分＝”指定なし”の場合は、0。 
        //リクエスト.発注条件区分＝”逆指値”の場合は、リクエスト.逆指値用発注条件演算子。 
        //リクエスト.発注条件区分＝”W指値”の場合は、リクエスト.W指値用発注条件演算子。 
        //逆指値基準値： 
        //リクエスト.発注条件区分＝”指定なし”の場合は、0。 
        //リクエスト.発注条件区分＝”逆指値”の場合は、リクエスト.逆指値用発注条件単価。 
        //リクエスト.発注条件区分＝”W指値”の場合は、リクエスト.W指値用発注条件単価。
        //（W指値）訂正指値： 
        //リクエスト.発注条件区分＝（”指定なし”、”逆指値”）の場合は、0。 
        //リクエスト.発注条件区分＝”W指値”の場合は、リクエスト.W指値用注文単価(*1)。 
        String l_strOrderCondOperator = null;
        double l_dblStopBasePrice = 0D;
        double l_dblWLimitChangePrice = 0D;
        if (WEB3OrderingConditionDef.DEFAULT.equals(l_request.orderCondType))
        {
            l_strOrderCondOperator = "0";
            l_dblStopBasePrice = 0D;
            l_dblWLimitChangePrice = 0D;
        }
        else if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            l_strOrderCondOperator = l_request.stopOrderCondOperator;
            l_dblStopBasePrice = Double.parseDouble(l_request.stopOrderCondPrice);
            l_dblWLimitChangePrice = 0D;
        }
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            l_strOrderCondOperator = l_request.wlimitOrderCondOperator;
            l_dblStopBasePrice = Double.parseDouble(l_request.wlimitOrderCondPrice);
            if (WEB3StringTypeUtility.isNumber(l_request.wLimitPrice))
            {
                l_dblWLimitChangePrice = Double.parseDouble(l_request.wLimitPrice);                
            }
        }
        
        //弁済区分：　@リクエスト.弁済.弁済区分 
        String l_strRepaymentDiv = l_request.repayment.repaymentDiv;
        
        //弁済期限値：　@リクエスト.弁済.弁済期限         
        double l_dblRepaymentNum = 0D;
        if (WEB3StringTypeUtility.isNumber(l_request.repayment.repaymentTimeLimit))
        {
            l_dblRepaymentNum = Double.parseDouble(l_request.repayment.repaymentTimeLimit);
        }
        
        //初回注文の注文単位ID： 
        //リクエスト.注文期限区分＝”当日限り”の場合は、nullをセット。 
        //リクエスト.注文期限区分＝”出来るまで注文”の場合は、0をセット。
        Long l_lngFirstOrderUnitId = null;;
        if (WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(l_request.expirationDateType))
        {
            l_lngFirstOrderUnitId = new Long(0);
        }
        else if (WEB3OrderExpirationDateTypeDef.DAY_LIMIT.equals(l_request.expirationDateType))
        {
            l_lngFirstOrderUnitId = null;
        }
        
        //create新規建注文内容
        WEB3MarginOpenContractOrderSpec l_openContractOrderSpec = 
            WEB3MarginOpenContractOrderSpec.createOpenMarginOrderSpec(
                l_trader, 
                l_blnIsBuy, 
                l_strProductCode, 
                l_strMarketCode, 
                l_dblQuantity, 
                l_dblLimitPrice, 
                l_strExecutionCondition, 
                l_datExpirationDate, 
                l_taxType, 
                l_request.priceCondType,
                l_strOrderCondType, 
                l_strOrderCondOperator, 
                l_dblStopBasePrice, 
                l_dblWLimitChangePrice, 
                l_strRepaymentDiv, 
                l_dblRepaymentNum, 
                l_lngFirstOrderUnitId,
                l_wLimitExecCondType);
        
        //create手数料()
        WEB3EquityBizLogicProvider l_bizLogicProvider =
            (WEB3EquityBizLogicProvider)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getBizLogicProvider();
//        補助口座：　@this.get補助口座( ) 
//        市場コード：　@リクエスト.市場コード 
//        発注日：　@確認時（validate注文）の場合、取引時間管理.get発注日(void)。 
//　@　@　@        完了時（submit注文）の場合、リクエスト.確認時発注日。 
//        注文チャネル：　@this.getログインチャネル( ) 
//        信用取引区分：　@リクエスト.弁済.弁済区分 
//        弁済期限値：　@リクエスト.弁済.弁済期限 
//        注文カテゴリ：　@OrderCategEnum.”新規建注文”（OPEN_MARGIN）

        WEB3GentradeCommission l_commission = l_bizLogicProvider.createCommission(
            l_subAccount, 
            l_strMarketCode, 
		    l_orderBizDate, 
            this.getLoginChannel(), 
            l_strRepaymentDiv, 
            l_dblRepaymentNum, 
            OrderCategEnum.OPEN_MARGIN);
            
        WEB3EquityProductManager l_productManager =
            (WEB3EquityProductManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();
        
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
        
        //get取引銘柄               
        //証券会社：　@補助口座.証券会社IDの証券会社オブジェクト 
        //銘柄コード：　@リクエスト.銘柄コード 
        //市場コード：　@リクエスト.市場コード
        WEB3EquityTradedProduct l_tradedProduct = null;
        try
        {
            l_tradedProduct =
                (WEB3EquityTradedProduct)l_productManager.getTradedProduct(
                l_subAccount.getInstitution(), l_strProductCode, l_strMarketCode);    
        }
        catch (NotFoundException l_nfex)
        {
            //取引銘柄オブジェクトを取得できない場合は、
            //「指定銘柄は指定市場での取扱不可」の例外をthrow
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00638, STR_METHOD_NAME);
        }
        
        OrderTypeEnum l_orderType = null;
        if (l_openMarginRequestAdapter.isLong())
        {
            l_orderType = OrderTypeEnum.MARGIN_LONG;
        }
        else
        {
            l_orderType = OrderTypeEnum.MARGIN_SHORT;
        } 
        
        //calc注文時建代金
        //引数は以下の通りに設定する。
        //
        //手数料 : 作成した手数料オブジェクト
        //指値 : 新規建注文内容.getLimitPrice()
        //（W指値）訂正指値 :　@新規建注文内容.get（W指値）訂正指値()
        //逆指値基準値 : 新規建注文内容.get逆指値基準値()
        //執行条件 : 新規建注文内容.getExecConditionType()
        //（W指値）執行条件 : 新規建注文内容.get（W指値）執行条件()
        //値段条件 : 新規建注文内容.get値段条件()
        //発注条件 : 新規建注文内容.get発注条件()
        //確認時取得時価 :
        //　@確認の場合、null（固定）
        //　@完了の場合、リクエスト.確認時単価
        //isストップ注文有効 : false（固定）
        //is売建 :　@信用取引新規建リクエストアダプタ.is売建()
        //補助口座 : this.get補助口座()
        //取引銘柄 : get取引銘柄()
        //株数 : リクエスト.注文株数
        //約定数量 : 0固定
        //合計約定金額 : 0固定
        //isSkip金額チェック : false（スキップしない）固定
        boolean l_blnIsSale = l_openMarginRequestAdapter.isShort();
        
        WEB3EquityEstimatedContractPrice l_dblContractAmountAtOrder =
            l_orderManager.calcContractAmountAtOrder(
                l_commission,
                l_openContractOrderSpec.getLimitPrice(),
                l_openContractOrderSpec.getWLimitPrice(),
                l_openContractOrderSpec.getStopOrderPrice(),
                l_openContractOrderSpec.getExecConditionType(),
                l_openContractOrderSpec.getWlimitExecCondType(),
                l_openContractOrderSpec.getPriceConditionType(),
                l_openContractOrderSpec.getOrderConditionType(),
                l_request.checkPrice,
                false,
                l_blnIsSale,
                l_subAccount,
                l_tradedProduct,
                l_dblQuantity,
                0,
                0,
                false);

        //set計算単価
        //計算単価をセットする。
        //引数は以下の通りにセットする。
        //計算単価：　@calc注文時建代金( )の戻り値オブジェクト.get計算単価( )
        l_openContractOrderSpec.setCalcUnitPrice(
            l_dblContractAmountAtOrder.getCalcUnitPrice());

        //set建代金
        //引数は以下の通りに設定する。
        //建代金：　@calc注文時建代金( )の戻り値オブジェクト.get概算建代金( )
        l_openContractOrderSpec.setContractAmount(
            l_dblContractAmountAtOrder.getEstimatedContractPrice());

        // validate新規建注文
        WEB3MarginNewOrderValidationResult l_newOrderValidationResult = 
            (WEB3MarginNewOrderValidationResult) l_orderManager.validateOpenContractOrder(l_subAccount, l_openContractOrderSpec);

        //throw発注審査結果エラー情報(OrderValidationResult, 証券会社, String)
        //発注審査結果：　@validate新規建注文()の戻り値
        //証券会社：　@補助口座.getInstitution()
        //銘柄コード：　@信用新規建注文内容.getProductCode()
        l_orderManager.throwOrderValidationResultErrorInfo(
            l_newOrderValidationResult,
            l_subAccount.getInstitution(),
            l_openContractOrderSpec.getProductCode());

        //calc委託手数料
        l_bizLogicProvider.calcCommission(l_commission, l_subAccount);

        //validate取引余力
        this.validateTradingPower(
            l_subAccount,
            l_openContractOrderSpec,
            true,
            l_commission,
            l_newOrderValidationResult);
        
        //submit新規建注文
        if (l_request.orderId == null)
        {
            l_request.orderId =
                String.valueOf(l_orderManager.createNewOrderId());
        }
        this.submitOpenContractOrder(
            l_subAccount,
            l_openContractOrderSpec,
            Long.parseLong(l_request.orderId),
            l_request.password,
            l_openMarginRequestAdapter);
        
        WEB3MarginOpenMarginCompleteResponse l_openMarginCompleteResponse =
            (WEB3MarginOpenMarginCompleteResponse)l_request.createResponse();
        
        //isインサイダー警告表示
        WEB3EquityProduct l_product = null;
        try
        {
            l_product =
                (WEB3EquityProduct)l_productManager.getProduct(
                    l_subAccount.getInstitution(),
                    l_strProductCode);
        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        boolean l_boolIsInsider =
            l_orderManager.isInsiderMessageSuspension(
                l_subAccount,
                l_product.getProductId());
        
        //レスポンス.更新時間
        l_openMarginCompleteResponse.lastUpdatedTimestamp = GtlUtils.getSystemTimestamp();
        
        //レスポンス.識別番号
        l_openMarginCompleteResponse.orderActionId = l_request.orderId;
        
        //レスポンス.インサイダー警告表示フラグ
        l_openMarginCompleteResponse.insiderWarningFlag = l_boolIsInsider; 
        
        //レスポンス.市場コード
        l_openMarginCompleteResponse.marketCode = l_request.marketCode;

        //レスポンス.注文有効期限
        l_openMarginCompleteResponse.expirationDate = l_openMarginRequestAdapter.getExpirationDate();

        log.exiting(STR_METHOD_NAME);
        return l_openMarginCompleteResponse;
    }
    
    /**
     * (createリクエストアダプタ)<BR>
     * リクエストアダプタのインスタンスを生成する。<BR>
     * <BR>
     * 信用取引新規建リクエストアダプタ.create(引数のリクエスト)をコールする。<BR>
     * @@param l_request - (リクエスト)<BR>
     * リクエストデータ。
     * @@return WEB3MarginOpenMarginRequestAdapter
     */
    protected WEB3MarginOpenMarginRequestAdapter createRequestAdapter(WEB3GenRequest l_request)
    {
        final String STR_METHOD_NAME = "createRequestAdapter(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3MarginOpenMarginRequestAdapter l_requestAdaptor =
            WEB3MarginOpenMarginRequestAdapter.create(l_request);
        
        log.exiting(STR_METHOD_NAME);
        return l_requestAdaptor;
    }
    
    /**
     * (validate取引余力)<BR>
     * 取引余力をチェックする。<BR>
     * シーケンス図「（信用取引新規建サービス）validate取引余力」を参照。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト。
     * @@param l_orderSpec - (信用新規建注文内容)<BR>
     * 信用新規建注文内容オブジェクト。
     * @@param l_blnUpdateFlg - (余力更新フラグ)<BR>
     * 余力更新フラグ。<BR>
     * （false：　@確認時、true：　@完了時）
     * @@param l_commission - (手数料)<BR>
     * 手数料オブジェクト。
     * @@param l_validationResult - (発注審査結果)<BR>
     * 信用新規建新規注文発注審査結果オブジェクト。
     * @@return WEB3TPTradingPowerResult
     * @@throws WEB3BaseException
     */
    protected WEB3TPTradingPowerResult validateTradingPower(
        WEB3GentradeSubAccount l_subAccount,
        WEB3MarginOpenContractOrderSpec l_orderSpec,
        boolean l_blnUpdateFlg,
        WEB3GentradeCommission l_commission,
        WEB3MarginNewOrderValidationResult l_validationResult)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateTradingPower(WEB3GentradeSubAccount, WEB3MarginOpenContractOrderSpec, boolean, WEB3GentradeCommission, WEB3MarginNewOrderValidationResult)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        
        boolean l_bIsShortSellRegulationTarget =
            l_validationResult.getShortSellingRestraint();
        
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
        String l_strOrderRootDiv =
            l_opLoginSec.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV);     
        WEB3MarginOpenMarginUpdateInterceptor l_interceptor =
            new WEB3MarginOpenMarginUpdateInterceptor(
                l_orderSpec,
                l_commission,
                this.getLoginChannel(),
                l_strOrderRootDiv,
                l_bIsShortSellRegulationTarget);
        
        Object[] l_orderSpecIntercepters = { l_interceptor };
        Object[] l_orderSpecs = { l_orderSpec };
        OrderTypeEnum l_orderType;
        if (l_orderSpec.isLongOrder())
        {
            l_orderType = OrderTypeEnum.MARGIN_LONG;
        }
        else
        {
            l_orderType = OrderTypeEnum.MARGIN_SHORT;
        }
        WEB3TPTradingPowerService l_tradingPowerService
            = (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
        WEB3TPTradingPowerResult l_tpResult =
            l_tradingPowerService.validateTradingPower(
                l_subAccount,
                l_orderSpecIntercepters,
                l_orderSpecs,
                l_orderType,
                l_blnUpdateFlg);
        if (l_tpResult.isResultFlg() == false)
        {
            l_orderManager.throwTpErrorInfo(l_tpResult, l_orderType);
        }
        
        if (l_blnUpdateFlg)
        {
            l_orderManager.setThreadLocalPersistenceEventInterceptor(l_interceptor);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_tpResult;
    }
    
    /**
     * (submit新規建注文)<BR>
     * 信用新規建注文を登録する。<BR>
     * <BR>
     * 拡張株式注文マネージャ.submitOpenContractOrder(<BR>
     * 補助口座, 信用新規建内容, 注文ID, 取引パスワード, true（＝発注審査をスキップする）)をコールする。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト。
     * @@param l_orderSpec - (信用新規建注文内容)<BR>
     * 信用新規建注文内容オブジェクト。
     * @@param l_lngOrderId - (注文ID)<BR>
     * 予約注文の注文ID。
     * @@param l_strTradingPassword - (取引パスワード)<BR>
     * 取引パスワード。
     * @@param l_requestAdaptor - (新規建リクエストアダプタ)<BR>
     * 信用取引新規建リクエストアダプタオブジェクト。
     * @@throws WEB3BaseException
     */
    protected void submitOpenContractOrder(
        WEB3GentradeSubAccount l_subAccount,
        WEB3MarginOpenContractOrderSpec l_orderSpec,
        long l_lngOrderId,
        String l_strTradingPassword,
        WEB3MarginOpenMarginRequestAdapter l_requestAdaptor)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitOpenContractOrder(WEB3GentradeSubAccount, WEB3MarginOpenContractOrderSpec, long, String, WEB3MarginOpenMarginRequestAdapter)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        EqTypeOrderSubmissionResult l_result =
            l_orderManager.submitOpenContractOrder(
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
     * (set単価)<BR>
     * 何もせずにリターンする。<BR>
     * <BR>
     * @@param l_requestAdapter - (信用取引新規建リクエストアダプタ)<BR>
     * 信用取引新規建リクエストアダプタ。<BR>
     * @@param l_response - (レスポンス)<BR>
     * レスポンス<BR>
     * @@throws WEB3BaseException
     */
    protected void setPrice(
        WEB3MarginOpenMarginRequestAdapter l_requestAdapter,
        WEB3GenResponse l_response)
        throws WEB3BaseException
    {
        
    }
}
@
