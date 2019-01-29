head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.11;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesOpenContractServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 株価指数先物新規建注文サービス実装クラス(WEB3FuturesOpenMarginServiceImpl.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/07/21 呉艶飛 (中訊) 新規作成
                    : 2006/07/28 肖志偉 (中訊) 仕様変更　@モデル483 
Revesion History    : 2007/06/21 張騰宇 (中訊)仕様変更 モデル680
Revesion History    : 2007/11/19 趙林鵬 (中訊)仕様変更 モデル808
Revesion History    : 2008/03/13 張騰宇(中訊) モデル832 861 865
*/

package webbroker3.ifo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.ordersubmitter.io.IfoOpenContractOrderSpec;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3PayTypeDef;
import webbroker3.common.define.WEB3StopBasePriceTypeDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ifo.WEB3FuturesClientRequestService;
import webbroker3.ifo.WEB3FuturesOrderManagerImpl;
import webbroker3.ifo.WEB3IfoDataAdapter;
import webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult;
import webbroker3.ifo.WEB3IfoOpenContractOrderSpec;
import webbroker3.ifo.WEB3IfoOpenContractUpdateInterceptor;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoProductManagerImpl;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.define.WEB3DivisionTypeDef;
import webbroker3.ifo.define.WEB3IfoContractTypeDef;
import webbroker3.ifo.message.WEB3FuturesOpenMarginCompleteRequest;
import webbroker3.ifo.message.WEB3FuturesOpenMarginCompleteResponse;
import webbroker3.ifo.message.WEB3FuturesOpenMarginConfirmRequest;
import webbroker3.ifo.message.WEB3FuturesOpenMarginConfirmResponse;
import webbroker3.ifo.service.delegate.WEB3FuturesOpenContractRequestAdapter;
import webbroker3.ifo.service.delegate.WEB3FuturesOpenContractService;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (先物新規建サービスImpl)<BR>
 * 株価指数先物新規建注文サービス実装クラス<BR>
 * 
 * @@author 呉艶飛
 * @@version 1.0
 */
public class WEB3FuturesOpenContractServiceImpl extends WEB3FuturesClientRequestService implements WEB3FuturesOpenContractService
{

    /**
      * Logger
      */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3FuturesOpenContractServiceImpl.class);

    /**
     * @@roseuid 40F7A2C5007D
     */
    public WEB3FuturesOpenContractServiceImpl()
    {

    }

    /**
     * (validate注文)<BR>
     * 株価指数先物の新規建発注審査を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（先物新規建サービス）validate注文」参照。<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 株価指数先物新規建注文確認リクエストデータオブジェクト<BR>
     * @@return WEB3FuturesOpenMarginConfirmResponse
     * @@roseuid 40A84B3C037D
     */
    protected WEB3FuturesOpenMarginConfirmResponse validateOrder(WEB3FuturesOpenMarginConfirmRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrder(WEB3FuturesOpenMarginConfirmRequest l_request))";
        log.entering(STR_METHOD_NAME);

        //リクエストデータのチェックを実施する。
        l_request.validate();

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        //3.get補助口座()
        WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount) getSubAccount();
        //get代理入力者
        Trader l_trader = this.getTrader();
        
        //取得証券会社
        Institution l_institution = l_subAccount.getInstitution();
        
        //銘柄オブジェクトを取得する。
        WEB3IfoProductManagerImpl l_ifoProductManagerImpl = 
            (WEB3IfoProductManagerImpl) l_finApp.getTradingModule(ProductTypeEnum.IFO).getProductManager();
        WEB3IfoProductImpl l_ifoProductImpl = null;
        try
        {
            if (l_request.futProductCode == null)
            {
                //[get銘柄()に指定する引数] 
                //証券会社：　@ 
                //  補助口座に関連する証券会社オブジェクト 
                //原資産銘柄コード：　@ 
                //  リクエストデータ.原資産銘柄コード 
                //限月：　@ 
                //  リクエストデータ.限月 
                //先物オプション商品：　@”先物” 
                //行使価格：　@0 
                //分割：　@000：DEFAULT 
                //対象市場コード：　@ 
                //  リクエストデータ.市場コード 
                l_ifoProductImpl =
                    l_ifoProductManagerImpl.getIfoProduct(
                        l_institution,
                        l_request.targetProductCode,
                        l_request.delivaryMonth,
                        IfoDerivativeTypeEnum.FUTURES,
                        0,
                        WEB3DivisionTypeDef.DIVISION_DEFAULT,
                        l_request.marketCode);
            }
            else            
            {
                //該当する銘柄が存在しない場合は例外をスローする。 
                //[get銘柄()に指定する引数] 
                //証券会社：　@ 
                //  補助口座に関連する証券会社オブジェクト 
                //銘柄コード： 
                //  リクエストデータ.銘柄コード    
                l_ifoProductImpl = 
                    l_ifoProductManagerImpl.getIfoProduct(l_institution, l_request.futProductCode);
            }
     
        }
        catch (NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME,l_nfe);
            //該当する銘柄が存在しない場合は例外をスローする。
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00301, 
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //発注日を取得する。
        Date l_datCheckDate = WEB3DateUtility.toDay(WEB3GentradeTradingTimeManagement.getOrderBizDate());

        //createリクエストアダプタ(WEB3GenRequest)
        WEB3FuturesOpenContractRequestAdapter l_adapter = createRequestAdapter(l_request);

        //get単価( )
        double l_dblLimitPrice = l_adapter.getPrice();

        //新規建注文内容オブジェクトを生成する。
        WEB3IfoOpenContractOrderSpec l_ifoOpenContractOrderSpec = null;
        
        //執行条件：　@先物OPデータアダプタ.get執行条件(リクエストデータ.執行条件)の戻り値
        IfoOrderExecutionConditionType l_exectionContractType = 
            WEB3IfoDataAdapter.getExecutionConditionType(l_request.execCondType);

        //is買建：　@リクエストデータ.建区分 == ”買建”の場合true、以外false
        boolean l_blnContractType = false;
        if (WEB3IfoContractTypeDef.OPEN_BUY.equals(l_request.contractType))
        {
            l_blnContractType = true;
        }

        //(W指値)訂正指値：　@リクエストデータ.W指値用注文単価
        double l_dblWLimitPrice = 0D;
        if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_request.wLimitOrderPriceDiv)
            && WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            //指値
            l_dblWLimitPrice = Double.parseDouble(l_request.wLimitPrice);
        }
        else
        {
            l_dblWLimitPrice = 0D;
        }
        
        //逆指値基準値：
        double l_dblStopOrderBasePrice = 0D;
        
        //[リクエストデータ.発注条件区分 == ”逆指値”の場合
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            //リクエストデータ.逆指値用発注条件単価
            l_dblStopOrderBasePrice = Double.parseDouble(l_request.stopOrderCondPrice);
        }
        
        //　@[リクエストデータ.発注条件区分 == ”W指値”の場合]
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            //リクエストデータ.W指値用発注条件単価
            l_dblStopOrderBasePrice = Double.parseDouble(l_request.wlimitOrderCondPrice);
        }
        
        //（W指値）執行条件：　@
        //  先物OPデータアダプタ.get執行条件(リクエストデータ.W指値用執行条件)の戻り値
        IfoOrderExecutionConditionType l_executionConditionType = 
            WEB3IfoDataAdapter.getExecutionConditionType(l_request.wlimitExecCondType);

        //初回注文の注文単位ID：
        //　@先物OPデータアダプタ.get初回注文の注文単位ID(リクエストデータ.注文期限区分)の戻り値
        Long l_lngFirstOrderUnitId = WEB3IfoDataAdapter.getFirstOrderUnitId(l_request.expirationDateType);

        //夕場前繰越対象フラグ：
        //　@先物OPデータアダプタ.get夕場前繰越対象フラグ(リクエストデータ.注文期限区分, 部店.部店ID)の戻り値
        boolean l_blnEveningSessionCarryOverFlag = WEB3IfoDataAdapter.getEveningSessionCarryOverFlag(
            l_request.expirationDateType, l_subAccount.getWeb3GenBranch().getBranchId());

        //OP注文マネージャ
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3OptionOrderManagerImpl l_orderManager =
            (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();

        //注文失効日：　@OP注文マネージャ.get注文有効期限(
        //リクエストデータ.注文有効期限,先物OP銘柄.銘柄コード,リクエストデータ.取引市場,”先物”)の戻り値
        Date l_datExpirationDate = l_orderManager.getExpirationDate(
            l_request.expirationDate,
            l_ifoProductImpl.getProductCode(),
            l_request.marketCode,
            WEB3FuturesOptionDivDef.FUTURES);

        l_ifoOpenContractOrderSpec =
            WEB3IfoOpenContractOrderSpec.createOpenContractOrderSpec(
                l_institution.getInstitutionCode(),
                (WEB3GentradeTrader) l_trader,
                l_blnContractType,
                l_request.marketCode,
                l_ifoProductImpl,
                Double.parseDouble(l_request.futOrderQuantity),
                l_dblLimitPrice,
                l_exectionContractType,
                l_datExpirationDate,
                l_request.orderCondType,
                l_dblStopOrderBasePrice,
                l_dblWLimitPrice,
                l_executionConditionType,
                l_request.expirationDateType,
                l_lngFirstOrderUnitId,
                l_blnEveningSessionCarryOverFlag);

        WEB3FuturesOrderManagerImpl l_futuresOrderManagerImpl = 
            (WEB3FuturesOrderManagerImpl) l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
        
        //[validate先物新規建注文()に指定する引数] 
        //arg0（補助口座）：　@補助口座 
        //arg1（新規建注文内容）：　@create新規建注文内容()にて作成した新規建注文内容 
        NewOrderValidationResult l_result;
        l_result = 
            l_futuresOrderManagerImpl.validateFuturesOpenContractOrder(
                l_subAccount, 
                l_ifoOpenContractOrderSpec);
        
        if (l_result.getProcessingResult().isFailedResult())
        {
            log.debug("ProcessingResult() = " + l_result.getProcessingResult());
            throw new WEB3BaseException(
                l_result.getProcessingResult().getErrorInfo(), 
                STR_METHOD_NAME);
        }
        
        double l_dblQuantity = l_ifoOpenContractOrderSpec.getQuantity();
        if (Double.isNaN(l_dblQuantity))
        {
            l_dblQuantity = 0D;
        }
            
        //手数料オブジェクトを生成する。
        WEB3GentradeCommission l_gentradeCommission = new WEB3GentradeCommission();

        //手数料.注文チャネル = this.getログインチャネル()
        l_gentradeCommission.setOrderChannel(this.getLoginChannel());

        //手数料.証券会社コード = 補助口座.get証券会社().getInstitutionCode()
        l_gentradeCommission.setInstitutionCode(l_subAccount.getInstitution().getInstitutionCode());

        //手数料.部店ID = 補助口座.get取引店().getBranchId()
        l_gentradeCommission.setBranchId(l_subAccount.getWeb3GenBranch().getBranchId());

        //手数料.発注日 = 取引時間管理.get発注日()
        l_gentradeCommission.setOrderBizDate(new Timestamp(l_datCheckDate.getTime()));

        //手数料.取引コード(SONAR) = ”51：建”
        l_gentradeCommission.setSonarTradedCode(WEB3TransactionTypeSONARDef.OPEN_CONTRACT);

        //手数料.手数料商品コード = ”50：先物”
        l_gentradeCommission.setCommissionProductCode(WEB3CommisionProductCodeDef.INDEX_FUTURES);
        
        //手数料.弁済区分 = ”00：その他”
        l_gentradeCommission.setPayType(WEB3PayTypeDef.OTHER);

        //手数料.is指値 
        l_gentradeCommission.setIsLimitPrice(l_ifoOpenContractOrderSpec.isLimitOrder());
        
        //手数料.原資産銘柄コード = 先物OP銘柄.get原資産銘柄コード()
        l_gentradeCommission.setUnderlyingProductCode(
            l_ifoProductImpl.getUnderlyingProductCode());
        
        //手数料.数量 = 新規建注文内容.getQuantity()
        l_gentradeCommission.setQuantity(l_dblQuantity);
        
        //取引銘柄オブジェクトを取得する。
        WEB3IfoTradedProductImpl l_ifoTradedProductImpl = null; 
        try
        {
            l_ifoTradedProductImpl = 
                l_ifoProductManagerImpl.getIfoTradedProduct(
                    l_subAccount.getInstitution(), 
                    l_ifoProductImpl.getProductCode(), 
                    l_request.marketCode);
        }
        catch (NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME, l_nfe);
            //該当する銘柄が存在しない場合は例外をスローする。
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00301, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //概算受渡代金計算結果クラスコンストラクタ。
        WEB3IfoEstimateDeliveryAmountCalcResult l_ifoEstimateDeliveryAmountCalcResult = 
            new WEB3IfoEstimateDeliveryAmountCalcResult();
        //[calc建代金()に指定する引数] 
        //手数料：　@手数料オブジェクト 
        //計算単価：　@新規建注文内容.getLimitPrice()
        //補助口座：　@補助口座オブジェクト 
        //先物OP取引銘柄：　@先物OP取引銘柄オブジェクト 
        //数量： 新規建注文内容.getQuantity() 
        //isSkip金額チェック：　@false 
        l_ifoEstimateDeliveryAmountCalcResult = 
            l_futuresOrderManagerImpl.calcEstimatePrice(
                l_gentradeCommission, 
                l_ifoOpenContractOrderSpec.getLimitPrice(), 
                l_subAccount, 
                l_ifoTradedProductImpl, 
                l_dblQuantity, 
                false);
       
        //物OP新規建更新インタセプタ
        WEB3IfoOpenContractUpdateInterceptor l_ifoOpenContractUpdateInterceptor = 
            new WEB3IfoOpenContractUpdateInterceptor((WEB3IfoOpenContractOrderSpec) l_ifoOpenContractOrderSpec);
        //(*2)インタセプタオブジェクトのプロパティに以下の値をセットする。
        //インタセプタ.手数料 = （calc建代金()引数の手数料オブジェクト）
        l_ifoOpenContractUpdateInterceptor.setCommision(l_gentradeCommission);
        
        //インタセプタ.概算受渡代金計算結果 = （calc建代金()の戻り値）
        l_ifoOpenContractUpdateInterceptor.setEstimateDeliveryAmountCalcResult(
            l_ifoEstimateDeliveryAmountCalcResult);
        
        //インタセプタ.発注条件 = リクエストデータ.発注条件区分
        l_ifoOpenContractUpdateInterceptor.setOrderCond(l_request.orderCondType);
        
        //インタセプタ.逆指値基準値タイプ = 0(原資産時価) （*発注条件区分で逆指値/W指値を判定）
        l_ifoOpenContractUpdateInterceptor.setStopOrderBasePriceType(WEB3StopBasePriceTypeDef.DEFAULT); 
        
        //インタセプタ.発注条件演算子 = リクエストデータ.発注条件演算子　@（*発注条件区分で逆指値/W指値を判定）
        //インタセプタ.逆指値基準値 = リクエストデータ.発注条件単価　@（*発注条件区分で逆指値/W指値を判定）
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            //発注条件区分が逆指値の場合
            
            //インタセプタ.発注条件演算子
            l_ifoOpenContractUpdateInterceptor.setOrderCondOperator(l_request.stopOrderCondOperator);
                                      
            //インタセプタ.逆指値基準値
            double l_dblStopOrderCondPrice = 0D;
            if (WEB3StringTypeUtility.isNumber(l_request.stopOrderCondPrice))
            {
                l_dblStopOrderCondPrice = Double.parseDouble(l_request.stopOrderCondPrice);
            }
            l_ifoOpenContractUpdateInterceptor.setStopOrderBasePrice(l_dblStopOrderCondPrice);
                                    
        }
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            //発注条件区分がW指値の場合
            
            //インタセプタ.発注条件演算子
            l_ifoOpenContractUpdateInterceptor.setOrderCondOperator(l_request.wlimitOrderCondOperator);
            //インタセプタ.逆指値基準値
            double l_dblwlimitOrderCondPrice = 0D;
            if (WEB3StringTypeUtility.isNumber(l_request.wlimitOrderCondPrice))
            {
                l_dblwlimitOrderCondPrice = Double.parseDouble(l_request.wlimitOrderCondPrice);
            }
            l_ifoOpenContractUpdateInterceptor.setStopOrderBasePrice(l_dblwlimitOrderCondPrice);            
        }
        
        //インタセプタ.(W指値)訂正指値 = 新規建注文内容.(W指値)訂正指値
        l_ifoOpenContractUpdateInterceptor.setWLimitPriceChange(l_ifoOpenContractOrderSpec.getWLimitPriceChange());

        //インタセプタ.立会区分 = 取引時間管理.get立会区分
        l_ifoOpenContractUpdateInterceptor.setSessionType(WEB3GentradeTradingTimeManagement.getSessionType());

        //validate取引余力
        //　@　@補助口座 ： 補助口座
        //　@　@注文内容インタセプタ[] ： 先物OP新規建更新インタセプタを要素とした配列
        //　@　@注文内容[] ： 新規建注文内容を要素とした配列
        //　@　@余力更新フラグ ： false     
        Object[] l_objOrderSpecInterceptors = new Object[]{l_ifoOpenContractUpdateInterceptor};
        Object[] l_objOrderSpecs = new Object[]{l_ifoOpenContractOrderSpec};
        validateTradingPower(l_subAccount, l_objOrderSpecInterceptors, l_objOrderSpecs, false);
        
        WEB3GentradeBranch l_branch = (WEB3GentradeBranch) l_subAccount.getMainAccount().getBranch();
        WEB3FuturesOpenMarginConfirmResponse l_response = 
            (WEB3FuturesOpenMarginConfirmResponse) l_request.createResponse();

        //市場閉局警告指数
        String[] l_tradeCloseMarkets = 
            WEB3GentradeTradingTimeManagement.getTradeCloseSuspension(l_branch, WEB3FuturesOptionDivDef.FUTURES);

        //set単価
        //先物新規建注文リクエストアダプタ ： 生成した同名オブジェクト   
        //レスポンス ： 生成したレスポンス
        setPrice(l_adapter, l_response);

        //レスポンスデータに以下の通りプロパティをセットす
        //レスポンス.注文ID = 先物注文マネージャ.createNewOrderId()の戻り値
        l_response.orderId = "" + l_futuresOrderManagerImpl.createNewOrderId();
        
        IfoProductRow l_ifoProductRow = (IfoProductRow) l_ifoProductImpl.getDataSourceObject();
        //レスポンス.銘柄コード = 先物OP銘柄.銘柄コード
        l_response.futProductCode = l_ifoProductImpl.getProductCode();
        //レスポンス.銘柄名 = 先物OP銘柄.銘柄名
        l_response.futProductName = l_ifoProductRow.getStandardName();

        //レスポンス.概算建代金 = 概算受渡代金計算結果.概算受渡代金
        l_response.estimatedContractPrice = 
            WEB3StringTypeUtility.formatNumber(l_ifoEstimateDeliveryAmountCalcResult.getEstimateDeliveryAmount());
        
        //レスポンス.手数料コース = 概算受渡代金計算結果.手数料コース
        l_response.commissionCourse = l_ifoEstimateDeliveryAmountCalcResult.getCommissionCourse();
        
        //レスポンス.手数料 = 概算受渡代金計算結果.手数料
        l_response.commission = 
            WEB3StringTypeUtility.formatNumber(l_ifoEstimateDeliveryAmountCalcResult.getCommission());
        
        //レスポンス.手数料消費税 = 概算受渡代金計算結果.手数料消費税
        l_response.commissionConsumptionTax = 
            WEB3StringTypeUtility.formatNumber(l_ifoEstimateDeliveryAmountCalcResult.getCommissionTax());
        
        //レスポンス.確認時単価 = 概算受渡代金計算結果.計算単価        
        l_response.checkPrice = 
            WEB3StringTypeUtility.formatNumber(l_ifoEstimateDeliveryAmountCalcResult.getCalcUnitPrice());
        
        //レスポンス.確認時発注日 = get発注日()の戻り値
        l_response.checkDate = l_datCheckDate;
        
        //レスポンス.取引終了警告文言 = get市場閉局警告指数()の戻り値
        l_response.messageSuspension = l_tradeCloseMarkets;

        //レスポンス.注文有効期限 = 新規建注文内容.注文失効日
        l_response.expirationDate = l_ifoOpenContractOrderSpec.getOrderExpDate();

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }

    /**
     * (submit注文)<BR>
     * 株価指数先物新規建注文を登録する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（先物新規建サービス）submit注文」参照。<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 株価指数先物新規建注文完了リクエストデータオブジェクト<BR>
     * @@return WEB3FuturesOpenMarginCompleteResponse
     * @@roseuid 40A84B3C039C
     */
    protected WEB3FuturesOpenMarginCompleteResponse submitOrder(WEB3FuturesOpenMarginCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitOrder(WEB3OptionsOpenMarginCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        
        //リクエストデータのチェックを実施する。
        l_request.validate();

        //発注日を取得する。
        //リクエストデータ.確認時発注日!=nullの場合、コール。
        Date l_datBizDate = null;
        if (l_request.checkDate != null)
        {
            l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate(l_request.checkDate);
        }
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        //3.get補助口座()
        WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount) getSubAccount();
        
        //get代理入力者
        Trader l_trader = this.getTrader();

        //createリクエストアダプタ(WEB3GenRequest)
        WEB3FuturesOpenContractRequestAdapter l_adapter = createRequestAdapter(l_request);

        //get単価( )
        double l_dblLimitPrice = l_adapter.getPrice();

        //取得証券会社
        Institution l_institution = l_subAccount.getInstitution();

        //先物OP取引銘柄オブジェクトを取得する。
        WEB3IfoProductManagerImpl l_ifoProductManagerImpl = 
            (WEB3IfoProductManagerImpl) l_finApp.getTradingModule(ProductTypeEnum.IFO).getProductManager();

        WEB3IfoProductImpl l_ifoProductImpl = null;

        //銘柄オブジェクトを取得する。該当する銘柄が存在しない場合は例外をスローする。
        try
        {
            //リクエストデータ.銘柄コード!=nullの場合、コール。
            if (l_request.futProductCode != null)
            {
                //証券会社：　@    
                //補助口座に関連する証券会社オブジェクト 
                //銘柄コード： 
                // リクエストデータ.銘柄コード 
                l_ifoProductImpl = l_ifoProductManagerImpl.getIfoProduct(l_institution, l_request.futProductCode);
            }
            //リクエストデータ.銘柄コード==nullの場合、コール。
            else
            {
                //銘柄オブジェクトを取得する。
                //該当する銘柄が存在しない場合は例外をスローする。
                //[get銘柄()に指定する引数]
                //証券会社：補助口座に関連する証券会社オブジェクト
                //原資産銘柄コード：リクエストデータ.指数種別
                //限月：リクエストデータ.限月
                //先物オプション商品：　@”先物”
                //行使価格：　@0
                //分割：　@000：DEFAULT
                //対象市場コード：リクエストデータ.取引市場
                l_ifoProductImpl = l_ifoProductManagerImpl.getIfoProduct(
                    l_institution,
                    l_request.targetProductCode,
                    l_request.delivaryMonth,
                    IfoDerivativeTypeEnum.FUTURES,
                    0,
                    WEB3DivisionTypeDef.DIVISION_DEFAULT,
                    l_request.marketCode);
            }
        }
        catch (NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME, l_nfe);
            //該当する銘柄が存在しない場合は例外をスローする。
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00301, this.getClass().getName() + STR_METHOD_NAME);
        }

        //新規建注文内容オブジェクトを生成する。
        WEB3IfoOpenContractOrderSpec l_ifoOpenContractOrderSpec = null;

        //執行条件：　@先物OPデータアダプタ.get執行条件(リクエストデータ.執行条件)の戻り値
        IfoOrderExecutionConditionType l_exectionContractType = 
            WEB3IfoDataAdapter.getExecutionConditionType(l_request.execCondType);

        //is買建：　@リクエストデータ.建区分 == ”買建”の場合true、以外false
        boolean l_blnContractType = false;
        if (WEB3IfoContractTypeDef.OPEN_BUY.equals(l_request.contractType))
        {
            l_blnContractType = true;
        }

        //(W指値)訂正指値：　@リクエストデータ.W指値用注文単価
        double l_dblWLimitPrice = 0D;
        if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_request.wLimitOrderPriceDiv)
            && WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            //指値
            l_dblWLimitPrice = Double.parseDouble(l_request.wLimitPrice);
        }
        else
        {
            l_dblWLimitPrice = 0D;
        }

        //逆指値基準値：
        double l_dblStopOrderBasePrice = 0D;
        
        //[リクエストデータ.発注条件区分 == ”逆指値”の場合
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            //リクエストデータ.逆指値用発注条件単価
            l_dblStopOrderBasePrice = Double.parseDouble(l_request.stopOrderCondPrice);
        }
        
        //　@[リクエストデータ.発注条件区分 == ”W指値”の場合]
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            //リクエストデータ.W指値用発注条件単価
            l_dblStopOrderBasePrice = Double.parseDouble(l_request.wlimitOrderCondPrice);
        }
        
        //（W指値）執行条件：　@
        //  先物OPデータアダプタ.get執行条件(リクエストデータ.W指値用執行条件)の戻り値
        IfoOrderExecutionConditionType l_executionConditionType = 
            WEB3IfoDataAdapter.getExecutionConditionType(l_request.wlimitExecCondType);

        //初回注文の注文単位ID：
        //　@先物OPデータアダプタ.get初回注文の注文単位ID(リクエストデータ.注文期限区分)の戻り値
        Long l_lngFirstOrderUnitId = WEB3IfoDataAdapter.getFirstOrderUnitId(l_request.expirationDateType);

        //夕場前繰越対象フラグ：
        //　@先物OPデータアダプタ.get夕場前繰越対象フラグ(リクエストデータ.注文期限区分, 部店.部店ID)の戻り値
        boolean l_blnEveningSessionCarryOverFlag = WEB3IfoDataAdapter.getEveningSessionCarryOverFlag(
            l_request.expirationDateType, l_subAccount.getWeb3GenBranch().getBranchId());

        //OP注文マネージャ
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3OptionOrderManagerImpl l_orderManager =
            (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();

        //注文失効日：　@OP注文マネージャ.get注文有効期限(
        //リクエストデータ.注文有効期限,先物OP銘柄.銘柄コード,リクエストデータ.取引市場,”先物”)の戻り値
        Date l_datExpirationDate = l_orderManager.getExpirationDate(
            l_request.expirationDate,
            l_ifoProductImpl.getProductCode(),
            l_request.marketCode,
            WEB3FuturesOptionDivDef.FUTURES);

        l_ifoOpenContractOrderSpec =
            WEB3IfoOpenContractOrderSpec.createOpenContractOrderSpec(
                l_institution.getInstitutionCode(),
                (WEB3GentradeTrader) l_trader,
                l_blnContractType,
                l_request.marketCode,
                l_ifoProductImpl,
                Double.parseDouble(l_request.futOrderQuantity),
                l_dblLimitPrice,
                l_exectionContractType,
                l_datExpirationDate,
                l_request.orderCondType,
                l_dblStopOrderBasePrice,
                l_dblWLimitPrice,
                l_executionConditionType,
                l_request.expirationDateType,
                l_lngFirstOrderUnitId,
                l_blnEveningSessionCarryOverFlag);

        //先物OP新規建注文の発注審査を実施する。
        WEB3FuturesOrderManagerImpl l_futuresManagerImpl = 
            (WEB3FuturesOrderManagerImpl) l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
        //[validate先物新規建注文()に指定する引数] 
        //arg0（補助口座）：　@補助口座 
        //arg1（新規建注文内容）：　@create新規建注文内容()にて作成した新規建注文内容 
        NewOrderValidationResult l_newOrderValidationResult = 
            l_futuresManagerImpl.validateFuturesOpenContractOrder(l_subAccount, l_ifoOpenContractOrderSpec);
        if (l_newOrderValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug("ProcessingResult() = " + l_newOrderValidationResult.getProcessingResult());
            throw new WEB3BaseException(
                l_newOrderValidationResult.getProcessingResult().getErrorInfo(), 
                STR_METHOD_NAME);
        }
        
        double l_dblQuantity = l_ifoOpenContractOrderSpec.getQuantity();
        if (Double.isNaN(l_dblQuantity))
        {
            l_dblQuantity = 0D;
        }
        
        //手数料オブジェクトを生成する。
        WEB3GentradeCommission l_gentradeCommission = new WEB3GentradeCommission();
        //手数料オブジェクトにアクセサメソッドを使用しプロパティをセットする。
        //手数料.注文チャネル = this.getログインチャネル()
        l_gentradeCommission.setOrderChannel(this.getLoginChannel());
        
        //手数料.証券会社コード = 補助口座.get証券会社().getInstitutionCode()
        l_gentradeCommission.setInstitutionCode(l_subAccount.getInstitution().getInstitutionCode());
        
        //手数料.部店ID = 補助口座.get取引店().getBranchId()
        l_gentradeCommission.setBranchId((l_subAccount).getWeb3GenBranch().getBranchId());
        
        //手数料.発注日 = 取引時間管理.get発注日()
        Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        l_gentradeCommission.setOrderBizDate(new Timestamp(l_datOrderBizDate.getTime()));
        
        //手数料.取引コード(SONAR) = ”51：建”
        l_gentradeCommission.setSonarTradedCode(WEB3TransactionTypeSONARDef.OPEN_CONTRACT);
        
        //手数料.手数料商品コード = ”50：先物”
        l_gentradeCommission.setCommissionProductCode(WEB3CommisionProductCodeDef.INDEX_FUTURES);
        
        //手数料.弁済区分 = ”00：その他”
        l_gentradeCommission.setPayType(WEB3PayTypeDef.OTHER);

        //手数料.is指値
        boolean l_blnIsLimitPrice = l_ifoOpenContractOrderSpec.isLimitOrder();
        l_gentradeCommission.setIsLimitPrice(l_blnIsLimitPrice);
        
        //手数料.原資産銘柄コード = 先物OP銘柄.get原資産銘柄コード()
        l_gentradeCommission.setUnderlyingProductCode(
            l_ifoProductImpl.getUnderlyingProductCode());
        
        //手数料.数量 = 新規建注文内容.getQuantity()
        l_gentradeCommission.setQuantity(l_dblQuantity);
        
        //取引銘柄オブジェクトを取得する。
        WEB3IfoTradedProductImpl l_ifoTradedProductImpl = null;
        try
        {
            l_ifoTradedProductImpl = 
                l_ifoProductManagerImpl.getIfoTradedProduct(
                    l_subAccount.getInstitution(), 
                    l_ifoProductImpl.getProductCode(), 
                    l_request.marketCode);
        }
        catch (NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME, l_nfe);
            //該当する銘柄が存在しない場合は例外をスローする。
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00301, this.getClass().getName() + STR_METHOD_NAME);
        }
        //概算受渡代金計算結果クラスコンストラクタ。
        WEB3IfoEstimateDeliveryAmountCalcResult l_ifoEstimateDeliveryAmountCalcResuil = 
            new WEB3IfoEstimateDeliveryAmountCalcResult();
                
        //[calc建代金()に指定する引数] 
        //手数料：　@手数料オブジェクト 
        //計算単価：
        //　@ リクエストデータ.確認時単価!=nullの場合、リクエストデータ.確認時単価。
        //   リクエストデータ.確認時単価==nullの場合、新規建注文内容.getLimitPrice()
        //  （*1　@リクエストデータ.注文単価==nullの場合はゼロをセット。）
        //補助口座：　@補助口座オブジェクト 
        //先物OP取引銘柄：　@先物OP取引銘柄オブジェクト 
        //数量： 新規建注文内容.getQuantity() 
        //isSkip金額チェック：　@false 
        double l_dblCheckPrice = 0D;
        if (l_request.checkPrice != null)
        {
            l_dblCheckPrice = Double.parseDouble(l_request.checkPrice);
        }
        else
        {
            l_dblCheckPrice = l_ifoOpenContractOrderSpec.getLimitPrice();
        }

        l_ifoEstimateDeliveryAmountCalcResuil = 
            l_futuresManagerImpl.calcEstimatePrice(
                l_gentradeCommission, 
                l_dblCheckPrice, 
                l_subAccount, 
                l_ifoTradedProductImpl, 
                l_dblQuantity, 
                false);
        
        //インタセプタを生成する。
        WEB3IfoOpenContractUpdateInterceptor l_ifoOpenContractUpdateInterceptor = 
            new WEB3IfoOpenContractUpdateInterceptor(l_ifoOpenContractOrderSpec);
        //インタセプタ.手数料 = （calc建代金()引数の手数料オブジェクト）
        l_ifoOpenContractUpdateInterceptor.setCommision(l_gentradeCommission);
        
        //インタセプタ.概算受渡代金計算結果 = （calc建代金()の戻り値）
        l_ifoOpenContractUpdateInterceptor.setEstimateDeliveryAmountCalcResult(l_ifoEstimateDeliveryAmountCalcResuil);
        
        //インタセプタ.発注条件 = リクエストデータ.発注条件区分        
        l_ifoOpenContractUpdateInterceptor.setOrderCond(l_request.orderCondType);
                
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            //発注条件区分が逆指値の場合
            
            //インタセプタ.発注条件演算子
            l_ifoOpenContractUpdateInterceptor.setOrderCondOperator(l_request.stopOrderCondOperator);
            //インタセプタ.逆指値基準値タイプ                 
            l_ifoOpenContractUpdateInterceptor.setStopOrderBasePriceType(WEB3StopBasePriceTypeDef.DEFAULT);            
            //インタセプタ.逆指値基準値
            double l_dblStopOrderCondPrice = 0D;
            if (WEB3StringTypeUtility.isNumber(l_request.stopOrderCondPrice))
            {
                l_dblStopOrderCondPrice = Double.parseDouble(l_request.stopOrderCondPrice);
            }
            l_ifoOpenContractUpdateInterceptor.setStopOrderBasePrice(l_dblStopOrderCondPrice);
                                    
        }
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            //発注条件区分がW指値の場合
            
            //インタセプタ.発注条件演算子
            l_ifoOpenContractUpdateInterceptor.setOrderCondOperator(l_request.wlimitOrderCondOperator);
            //インタセプタ.逆指値基準値タイプ                 
            l_ifoOpenContractUpdateInterceptor.setStopOrderBasePriceType(WEB3StopBasePriceTypeDef.DEFAULT);
            //インタセプタ.逆指値基準値
            double l_dblwlimitOrderCondPrice = 0D;
            if (WEB3StringTypeUtility.isNumber(l_request.wlimitOrderCondPrice))
            {
                l_dblwlimitOrderCondPrice = Double.parseDouble(l_request.wlimitOrderCondPrice);
            }
            l_ifoOpenContractUpdateInterceptor.setStopOrderBasePrice(l_dblwlimitOrderCondPrice);            
        }
        
        //インタセプタ.(W指値)訂正指値 = 新規建注文内容.(W指値)訂正指値
        double l_dblWLimitPriceChange = l_ifoOpenContractOrderSpec.getWLimitPriceChange();
        if (Double.isNaN(l_dblWLimitPriceChange))
        {
            l_dblWLimitPriceChange = 0D;
        }
        l_ifoOpenContractUpdateInterceptor.setWLimitPriceChange(l_dblWLimitPriceChange);

        //インタセプタ.立会区分 = 取引時間管理.get立会区分
        l_ifoOpenContractUpdateInterceptor.setSessionType(WEB3GentradeTradingTimeManagement.getSessionType());

        //validate取引余力(SubAccount, Object[], Object[], boolean)
        //　@　@補助口座 ： 補助口座
        //　@　@注文内容インタセプタ[] ： 先物OP新規建更新インタセプタを要素とした配列
        //　@　@注文内容[] ： 新規建注文内容を要素とした配列
        //　@　@余力更新フラグ ： false
        Object[] l_objOrderSpecInterceptors = new Object[]{l_ifoOpenContractUpdateInterceptor};
        Object[] l_objOrderSpecs = new Object[]{l_ifoOpenContractOrderSpec};
        validateTradingPower(l_subAccount, l_objOrderSpecInterceptors, l_objOrderSpecs, false);

        //インタセプタをセットする。
        l_futuresManagerImpl.setThreadLocalPersistenceEventInterceptor(l_ifoOpenContractUpdateInterceptor);

        //submit新規建注文
        //　@補助口座 ： 補助口座オブジェクト
        //　@新規建注文内容 ： create新規建注文内容()で生成した新規建注文内容
        //　@注文ID ： リクエストデータ.注文ID != nullの場合、リクエストデータ.注文ID
        //　@　@　@　@　@　@リクエストデータ.注文ID == nullの場合、先物注文マネージャ.createNewOrderId()の戻り値
        //　@取引パスワード ： リクエストデータ.暗証番号
        //　@新規建リクエストアダプタ ： createリクエストアダプタ()で生成した先物新規建リクエストアダプタオブジェクト
        //　@先物OP概算受渡代金計算結果 ： calc概算建代金()の戻り値
        long l_lngOrderId = 0;
        if (l_request.orderId != null)
        {
            l_lngOrderId = Long.parseLong(l_request.orderId);
        }
        else
        {
            l_lngOrderId = l_futuresManagerImpl.createNewOrderId();
        }
        submitOpenContractOrder(
            (WEB3GentradeSubAccount)l_subAccount,
            l_ifoOpenContractOrderSpec,
            l_lngOrderId,
            l_request.password,
            l_adapter,
            l_ifoEstimateDeliveryAmountCalcResuil);

        //インタセプタにプロパティをセットする。
        WEB3FuturesOpenMarginCompleteResponse l_response = 
            (WEB3FuturesOpenMarginCompleteResponse) l_request.createResponse();

        //レスポンス.更新時間 = 現在日時(GtlUtils.getSystemTimestamp())
        l_response.lastUpdatedTimestamp = GtlUtils.getSystemTimestamp();

        //レスポンス.識別番号 =
        //　@リクエストデータ.注文ID==nullの場合、先物注文マネージャ.createNewOrderID()の戻り値。
        //  リクエストデータ.注文ID!=nullの場合、リクエストデータ.注文ID
        l_response.orderActionId = "" + l_lngOrderId;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * 株価指数先物新規建注文サービス処理を実施する。<BR>
     * リクエストデータの型により、validate()注文または、<BR>
     * submit注文()メソッドをコールする。<BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40A84B3C03AC
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3FuturesOpenMarginConfirmRequest)
        {
            l_response = this.validateOrder((WEB3FuturesOpenMarginConfirmRequest) l_request);
            log.exiting(STR_METHOD_NAME);
        }
        else if (l_request instanceof WEB3FuturesOpenMarginCompleteRequest)
        {
            l_response = this.submitOrder((WEB3FuturesOpenMarginCompleteRequest) l_request);
            log.exiting(STR_METHOD_NAME);
        }
        else
        {
            log.error(STR_METHOD_NAME + "パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80018, STR_METHOD_NAME);
        }
        return l_response;

    }

    /**
     * (validate取引余力)<BR>
     * 証拠金のチェックを行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「（先物新規建サービス）validate取引余力」を参照。<BR>
     * =============================================== <BR>
     * シーケンス図 : (先物新規建サービス)validate取引余力 <BR>
     * 具体位置     : 取引余力結果.is判定フラグ( )==falseの場合<BR>
     * 　@　@　@　@　@　@　@　@　@throw：取引余力エラー <BR>
     * class        : WEB3BusinessLayerException <BR>
     * tag          : BUSINESS_ERROR_01306 <BR>
     * =============================================== <BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト。<BR>
     * @@param l_objOrderSpecInterceptors - (注文内容インタセプタ)<BR>
     * 注文内容インタセプタ。<BR>
     * @@param l_objOrderSpecs - (注文内容)<BR>
     * 注文内容。<BR>
     * @@param l_blnUpdateFlg - (余力更新フラグ)<BR>
     * 余力更新フラグ。 <BR>
     * <BR>
     * true ： 余力再計算処理を実施する  <BR>
     * false ： 余力再計算処理を実施しない<BR>
     * @@return WEB3TPTradingPowerResult
     * @@throws WEB3BaseException
     */
    protected WEB3TPTradingPowerResult validateTradingPower(
        SubAccount l_subAccount,
        Object[] l_objOrderSpecInterceptors,
        Object[] l_objOrderSpecs,
        boolean l_blnUpdateFlg) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateTradingPower(SubAccount, Object[], Object[], boolean)";
        log.entering(STR_METHOD_NAME);

        OrderTypeEnum l_orderTypeEnum = null;

        WEB3TPTradingPowerService l_tradingPowerService =
            (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);

        //証拠金・余力チェックを行う。
        //注文種別 ：
        if (((WEB3IfoOpenContractOrderSpec)l_objOrderSpecs[0]).isBuyToOpenOrder())
        {
            //引数.注文内容[0].isBuyToOpenOrder() == trueの場合、"先物新規買建"
            l_orderTypeEnum = OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN;
        }
        else
        {
            //以外の場合、"先物新規売建"
            l_orderTypeEnum = OrderTypeEnum.IDX_FUTURES_SELL_TO_OPEN;
        }

        //証拠金・余力チェックを行う。
        //　@[引数]
        //　@補助口座 ： 引数.補助口座
        //　@注文内容インタセプタ[] ： 引数.注文内容インタセプタ[]
        //　@注文内容[] ： 引数.注文内容[]
        //　@注文種別 ：
        //　@　@　@引数.注文内容[0].isBuyToOpenOrder() == trueの場合、"先物新規買建"
        //　@　@　@以外の場合、"先物新規売建"
        //　@余力更新フラグ ： 引数.余力更新フラグ
        WEB3TPTradingPowerResult l_tradingPowerResult = l_tradingPowerService.validateTradingPower(
            (WEB3GentradeSubAccount)l_subAccount,
            l_objOrderSpecInterceptors,
            l_objOrderSpecs,
            l_orderTypeEnum,
            l_blnUpdateFlg);

        if (!l_tradingPowerResult.isResultFlg())
        {
            log.debug("取引余力チェックエラー。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01306,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "取引余力チェックエラー。");
        }

        log.exiting(STR_METHOD_NAME);
        return l_tradingPowerResult;
    }

    /**
     * (submit新規建注文)<BR>
     * 先物新規建注文の注文登録を行う。 <BR>
     * <BR>
     * ※処理の詳細はシーケンス図「（先物新規建サービス）submit注文」参照<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト。<BR>
     * @@param l_ifoOpenContractOrderSpec - (新規建注文内容)<BR>
     * 新規建注文内容オブジェクト。<BR>
     * @@param l_lngOrderId - (注文ID)<BR>
     * 注文ID。<BR>
     * @@param l_strTradingPassword - (取引パスワード)<BR>
     * 取引パスワード。<BR>
     * @@param l_requestAdapter - (新規建リクエストアダプタ)<BR>
     * 先物新規建リクエストアダプタオブジェクト。<BR>
     * @@param l_estimateDeliveryAmountCalcResult - (先物OP概算受渡代金計算結果)<BR>
     * 先物OP概算受渡代金計算結果。<BR>
     * @@throws WEB3BaseException
     */
    protected void submitOpenContractOrder(
        WEB3GentradeSubAccount l_subAccount,
        IfoOpenContractOrderSpec l_ifoOpenContractOrderSpec,
        long l_lngOrderId,
        String l_strTradingPassword,
        WEB3FuturesOpenContractRequestAdapter l_requestAdapter,
        WEB3IfoEstimateDeliveryAmountCalcResult l_estimateDeliveryAmountCalcResult)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitOpenContractOrder(WEB3GentradeSubAccount, " +
            "IfoOpenContractOrderSpec, long, String, WEB3FuturesOpenContractRequestAdapter, " +
            "WEB3IfoEstimateDeliveryAmountCalcResult)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3FuturesOrderManagerImpl l_futuresManagerImpl =
            (WEB3FuturesOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();

        //補助口座 ： 引数.補助口座オブジェクト
        //新規建注文内容 ： 引数.新規建注文内容
        //注文ID ： 引数.注文ID
        //取引パスワード ： 引数.取引パスワード
        //isSkip発注審査 ： true（固定）
        OrderSubmissionResult l_submissionResult =
            l_futuresManagerImpl.submitOpenContractOrder(
            l_subAccount,
            l_ifoOpenContractOrderSpec,
            l_lngOrderId,
            l_strTradingPassword,
            true);

        if (l_submissionResult.getProcessingResult().isFailedResult())
        {
            //例外をスローする
            log.debug("ProcessingResult() = " + l_submissionResult.getProcessingResult());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_submissionResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (createリクエストアダプタ)<BR>
     * リクエストアダプタのインスタンスを生成する。 <BR>
     * <BR>
     * ※処理の詳細はシーケンス図 <BR>
     * 「（先物新規建サービス）validate注文／（先物新規建サービス）submit注文」参照<BR>
     * @@param l_request - (リクエスト)<BR>
     * リクエストオブジェクト。<BR>
     * @@return WEB3FuturesOpenContractRequestAdapter
     * @@throws WEB3BaseException
     */
    protected WEB3FuturesOpenContractRequestAdapter createRequestAdapter(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createRequestAdapter(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        //リクエストアダプタを作成する。
        //リクエスト ： 引数.リクエストデータ
        WEB3FuturesOpenContractRequestAdapter l_adapter =
            WEB3FuturesOpenContractRequestAdapter.create(l_request);

        log.exiting(STR_METHOD_NAME);
        return l_adapter;
    }

    /**
     * (set単価)<BR>
     * 何もせずにreturnする。（カラ実装） <BR>
     * @@param l_adapter - (先物新規建注文リクエストアダプタ)<BR>
     * 先物新規建注文リクエストアダプタオブジェクト。<BR>
     * @@param l_response - (レスポンス)<BR>
     * レスポンスオブジェクト。<BR>
     */
    protected void setPrice(WEB3FuturesOpenContractRequestAdapter l_adapter, WEB3GenResponse l_response)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "setPrice(WEB3FuturesOpenContractRequestAdapter, WEB3GenResponse)";
        log.entering(STR_METHOD_NAME);

        //何もせずにreturnする。（カラ実装）
        log.exiting(STR_METHOD_NAME);
        return;
    }
}
@
