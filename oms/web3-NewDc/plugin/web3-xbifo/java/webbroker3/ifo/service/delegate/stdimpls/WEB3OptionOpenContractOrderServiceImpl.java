head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionOpenContractOrderServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : OP新規建注文サービスImpl(WEB3OptionOpenContractOrderServiceImpl.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/06/21 呉艶飛 (中訊) 新規作成
                 001: 2004/07/22 王暁傑 (中訊) WEB3CommisionProductCodeDefでWEB3IfoCommissionProductCodeDefを差し替える
                 002: 2004/08/01 王暁傑 (中訊) 対応バッグ WEB3_IFO_UT-000103、104、105
                 003: 2004/08/09 王暁傑 (Sinocom) 対応名称:【WEB3-XBIFO-A-CD-0082】
                 004: 2004/08/10 王暁傑 (Sinocom) 対応バッグ JPU00031
                 005: 2004/08/13 呉艶飛　@(中訊) STBUG(IFO_ST-000079)を対応
                 006: 2004/08/15 呉艶飛　@(中訊) STBUG(IFO_ST-000083)を対応
                 007: 2006/07/13 唐性峰  (中訊) モデルNo.466、474、522対応
                 008: 2006/08/30 唐性峰  (中訊) モデルNo.551対応
                 009: 2006/08/31 唐性峰  (中訊) モデルNo.552対応
                 010: 2006/11/28 徐大方  (中訊) モデルNo.573対応
                 011: 2006/11/30 徐大方  (中訊) モデルNo.584対応
Revesion History    : 2007/06/11 張騰宇　@(中訊) モデル 662
Revesion History    : 2007/11/19 何文敏　@(中訊) モデル 803
Revesion History    : 2008/04/10 張騰宇　@(中訊) モデル 848 869 875
Revesion History    : 2008/05/29 トウ鋒鋼　@(中訊) モデル 887
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.ordersubmitter.io.IfoOpenContractOrderSpec;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3PayTypeDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;

import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import webbroker3.ifo.WEB3IfoDataAdapter;
import webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult;
import webbroker3.ifo.WEB3IfoOpenContractOrderSpec;
import webbroker3.ifo.WEB3IfoOpenContractUpdateInterceptor;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoProductManagerImpl;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.WEB3OptionClientRequestService;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.define.WEB3DivisionTypeDef;
import webbroker3.ifo.define.WEB3IfoContractTypeDef;
import webbroker3.ifo.define.WEB3IfoProductTypeDef;
import webbroker3.ifo.message.WEB3OptionsOpenMarginCompleteRequest;
import webbroker3.ifo.message.WEB3OptionsOpenMarginCompleteResponse;
import webbroker3.ifo.message.WEB3OptionsOpenMarginConfirmRequest;
import webbroker3.ifo.message.WEB3OptionsOpenMarginConfirmResponse;
import webbroker3.ifo.service.delegate.WEB3OptionOpenContractOrderRequestAdapter;
import webbroker3.ifo.service.delegate.WEB3OptionOpenContractOrderService;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;

/**
 * (OP新規建注文サービスImpl)<BR>
 * <BR>
 * 株価指数オプション新規建注文サービス実装クラス<BR>
 * @@author 呉艶飛
 * @@version 1.0
 */
public class WEB3OptionOpenContractOrderServiceImpl extends WEB3OptionClientRequestService implements WEB3OptionOpenContractOrderService
{

    /**
      * Logger
      */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(
            WEB3OptionOpenContractOrderServiceImpl.class);

    /**
     * @@roseuid 40C0BAF1002E
     */
    public WEB3OptionOpenContractOrderServiceImpl()
    {

    }

    /**
     * (validate注文)<BR>
     * <BR>
     * 株価指数オプションの新規建発注審査を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（OP新規建サービス）validate注文」参照。<BR>
     * <BR>
     * @@param l_request - 株価指数オプション新規建注文確認リクエストデータオブジェクト
     * @@return WEB3OptionsOpenMarginConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 404EF125015F
     */
    protected WEB3OptionsOpenMarginConfirmResponse validateOrder(WEB3OptionsOpenMarginConfirmRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrder(WEB3OptionsOpenMarginConfirmRequest l_request))";
        log.entering(STR_METHOD_NAME);

        //リクエストデータのチェックを実施する。
        l_request.validate();

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        //3.get補助口座()
        WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount) getSubAccount();

        Trader l_trader = this.getTrader();
        //取得証券会社
        Institution l_institution = l_subAccount.getInstitution();

        //銘柄オブジェクトを取得する。
        WEB3IfoProductManagerImpl l_ifoProductManagerImpl = 
            (WEB3IfoProductManagerImpl)l_finApp.getTradingModule(
                ProductTypeEnum.IFO).getProductManager();

        WEB3IfoProductImpl l_ifoProductImpl = null;
        if (l_request.opProductCode == null)
        {
            try
            {
                if (WEB3IfoProductTypeDef.CALL_OPTIONS.equals(l_request.opProductType))
                {                    
                    l_ifoProductImpl = l_ifoProductManagerImpl.getIfoProduct(
                        l_institution,
                        l_request.targetProductCode,
                        l_request.delivaryMonth,
                        IfoDerivativeTypeEnum.CALL_OPTIONS,
                        Double.parseDouble(l_request.strikePrice),
                        WEB3DivisionTypeDef.DIVISION_DEFAULT,
                        l_request.marketCode);                                               
                }
                else if (WEB3IfoProductTypeDef.PUT_OPTIONS.equals(l_request.opProductType))
                {                    
                    l_ifoProductImpl = l_ifoProductManagerImpl.getIfoProduct(
                        l_institution,
                        l_request.targetProductCode,
                        l_request.delivaryMonth,
                        IfoDerivativeTypeEnum.PUT_OPTIONS,
                        Double.parseDouble(l_request.strikePrice),
                        WEB3DivisionTypeDef.DIVISION_DEFAULT,
                        l_request.marketCode);                   
                }                
            }
            catch (NotFoundException l_nfex)
            {
                log.error(STR_METHOD_NAME, l_nfex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00735, 
                    STR_METHOD_NAME);
            }
        }        
        else
        {
            //該当する銘柄が存在しない場合は例外をスローする。
            try
            {
                l_ifoProductImpl = l_ifoProductManagerImpl.getIfoProduct(
                    l_institution, 
                    l_request.opProductCode);                              
            }
            catch (NotFoundException l_nfex)
            {
                log.error(STR_METHOD_NAME, l_nfex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00301, 
                    STR_METHOD_NAME);
            }
        }

        //発注日を取得する。
        Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

        //createリクエストアダプタ
        //[引数] リクエスト　@：　@引数.リクエストデータ
        WEB3OptionOpenContractOrderRequestAdapter l_requestAdapter = this.createRequestAdapter(l_request);

        //get単価( )
        double l_dblLimitPrice = l_requestAdapter.getPrice();

        //新規建注文内容オブジェクトを生成する。
        WEB3IfoOpenContractOrderSpec l_ifoOpenContractOrderSpec = null;
        IfoOrderExecutionConditionType l_orderExecutionConditionType = null;
        
        //執行条件：　@
        //先物OPデータアダプタ.get執行条件(リクエストデータ.執行条件)の戻り値
        l_orderExecutionConditionType = 
            WEB3IfoDataAdapter.getExecutionConditionType(l_request.execCondType);
        
        //is買建：　@リクエストデータ.建区分 == ”買建”の場合true、以外false 
        boolean l_blnIsBuyContract = false;
        if (WEB3IfoContractTypeDef.OPEN_BUY.equals(l_request.contractType))
        {
            l_blnIsBuyContract = true;
        }
        
        //(W指値)訂正指値：　@リクエストデータ.W指値用注文単価 
        double l_dblWLimitPrice = 0D;
        if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType) &&
            WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_request.wLimitOrderPriceDiv))
        {
            //指値
            l_dblWLimitPrice = Double.parseDouble(l_request.wLimitPrice);
        }
        else 
        {
            l_dblWLimitPrice = 0D;
        }
        
        //逆指値基準値： 
        //　@[リクエストデータ.発注条件区分 == ”逆指値”の場合]
        //   リクエストデータ.逆指値用発注条件単価
        //  [リクエストデータ.発注条件区分 == ”W指値”の場合]
        //   リクエストデータ.W指値用発注条件単価
        double l_dblStopOrderBasePrice = 0.0D;
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
        {   
            l_dblStopOrderBasePrice = Double.parseDouble(l_request.stopOrderCondPrice);           
        }
        else if(WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            l_dblStopOrderBasePrice = Double.parseDouble(l_request.wlimitOrderCondPrice);            
        }
        
        //（W指値）執行条件：　@
        //先物OPデータアダプタ.get執行条件(リクエストデータ.W指値用執行条件)の戻り値
        IfoOrderExecutionConditionType l_wLimitExecCondType = 
            WEB3IfoDataAdapter.getExecutionConditionType(l_request.wlimitExecCondType);

        // 注文失効日：　@OP注文マネージャ.get注文有効期限(リクエストデータ.注文有効期限,
        // 先物OP銘柄.銘柄コード,リクエストデータ.取引市場,”オプション”)の戻り値
        // OP注文マネージャ
        WEB3OptionOrderManagerImpl l_optionOrderManagerImpl =
            (WEB3OptionOrderManagerImpl) l_finApp.getTradingModule(
                ProductTypeEnum.IFO).getOrderManager();
        // 先物OP銘柄.銘柄コード
        String l_strProductCode = l_ifoProductImpl.getProductCode();
        Date l_datExpirationDate = l_optionOrderManagerImpl.getExpirationDate(
            l_request.expirationDate,
            l_strProductCode,
            l_request.marketCode,
            WEB3FuturesOptionDivDef.OPTION);

        //初回注文の注文単位ID：
        //先物OPデータアダプタ.get初回注文の注文単位ID(リクエストデータ.注文期限区分)の戻り値
        Long l_lngFirstOrderUnitId =
            WEB3IfoDataAdapter.getFirstOrderUnitId(l_request.expirationDateType);
        
        //夕場前繰越対象フラグ：　@先物OPデータアダプタ.get夕場前繰越対象フラグ
        //(リクエストデータ.注文期限区分, 補助口座に該当する部店.部店ID)の戻り値
        boolean l_blnEveningSessionCarryOverFlag =
            WEB3IfoDataAdapter.getEveningSessionCarryOverFlag(
                l_request.expirationDateType,
                l_subAccount.getMainAccount().getBranch().getBranchId());
                    
        l_ifoOpenContractOrderSpec =
            WEB3IfoOpenContractOrderSpec.createOpenContractOrderSpec(
                l_institution.getInstitutionCode(),
                (WEB3GentradeTrader) l_trader,
                l_blnIsBuyContract,
                l_request.marketCode,
                l_ifoProductImpl,
                Double.parseDouble(l_request.opOrderQuantity),
                l_dblLimitPrice,
                l_orderExecutionConditionType,
                l_datExpirationDate,
                l_request.orderCondType,
                l_dblStopOrderBasePrice,
                l_dblWLimitPrice,
                l_wLimitExecCondType,
                l_request.expirationDateType,
                l_lngFirstOrderUnitId,
                l_blnEveningSessionCarryOverFlag);
 
        //先物OP新規建注文の発注審査を実施する。
        NewOrderValidationResult l_result = null;
        l_result = l_optionOrderManagerImpl.validateOpenContractOrder(
            l_subAccount, 
            l_ifoOpenContractOrderSpec);
        if (l_result.getProcessingResult().isFailedResult())
        {
            log.debug("ProcessingResult() = " + l_result.getProcessingResult());
            throw new WEB3BusinessLayerException(
                l_result.getProcessingResult().getErrorInfo(),
                STR_METHOD_NAME);
        }
        
        //新規建注文内容.getQuantity()を取得する。
        double l_dblQuantity = l_ifoOpenContractOrderSpec.getQuantity();
        if (Double.isNaN(l_dblQuantity))
        {
            l_dblQuantity = 0D;
        }
        
        //手数料オブジェクトを生成する。
        WEB3GentradeCommission l_gentradeCommission = new WEB3GentradeCommission();
        //(*1)手数料オブジェクトにアクセサメソッドを使用しプロパティをセットする。

        //手数料.注文チャネル = this.getログインチャネル()
        l_gentradeCommission.setOrderChannel(this.getLoginChannel());

        //手数料.証券会社コード = 補助口座.get証券会社().getInstitutionCode()
        l_gentradeCommission.setInstitutionCode(l_subAccount.getInstitution().getInstitutionCode());        

        //手数料.部店ID = 補助口座.get取引店().getBranchId()
        l_gentradeCommission.setBranchId(((WEB3GentradeSubAccount) l_subAccount).getWeb3GenBranch().getBranchId());        

        //手数料.発注日 = 取引時間管理.get発注日()
        l_gentradeCommission.setOrderBizDate(new Timestamp(l_datBizDate.getTime()));

        //手数料.取引コード(SONAR) = ”51：建”
        l_gentradeCommission.setSonarTradedCode(WEB3TransactionTypeSONARDef.OPEN_CONTRACT);
        
        //手数料.手数料商品コード = ”51：株価指数OP”
        l_gentradeCommission.setCommissionProductCode(WEB3CommisionProductCodeDef.INDEX_OP);        
        
        //手数料.弁済区分 = ”00：その他”               
        l_gentradeCommission.setPayType(WEB3PayTypeDef.OTHER);      
        
        //手数料.is指値 = 新規建注文内容.isLimitOrder()
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
            l_ifoTradedProductImpl = l_ifoProductManagerImpl.getIfoTradedProduct(
                l_subAccount.getInstitution(), 
                l_ifoProductImpl.getProductCode(), 
                l_request.marketCode);    
        }
        catch (NotFoundException l_nfex)
        {
            log.error(STR_METHOD_NAME, l_nfex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00301, 
                STR_METHOD_NAME);
        }

        //概算受渡代金計算結果クラスコンストラクタ。
        //[calc概算受渡代金()に指定する引数] 
        //手数料：　@手数料オブジェクト 
        //計算単価：　@注文内容.getLimitPrice() 
        //補助口座：　@補助口座オブジェクト 
        //先物OP取引銘柄：　@先物OP取引銘柄オブジェクト 
        //数量： 新規建注文内容.getQuantity() 
        //売買： 
        //  リクエストデータ.建区分 = ”買”の場合SideEnum.BUY 
        //  リクエストデータ.建区分 = ”売”の場合SideEnum.SELL 
        //is返済注文：　@false 
        //isSkip金額チェック：　@false 
        WEB3IfoEstimateDeliveryAmountCalcResult l_ifoEstimateDeliveryAmountCalcResult =
            new WEB3IfoEstimateDeliveryAmountCalcResult();

        SideEnum l_side = null;
        if (WEB3IfoContractTypeDef.OPEN_BUY.equalsIgnoreCase(l_request.contractType))
        {
            l_side = SideEnum.BUY;
        }
        else
        {
            l_side = SideEnum.SELL;
        }

        //概算受渡代金を計算する。            
        l_ifoEstimateDeliveryAmountCalcResult =
            l_optionOrderManagerImpl.calcEstimateDeliveryAmount(
                l_gentradeCommission,
                l_ifoOpenContractOrderSpec.getLimitPrice(),
                l_subAccount,
                l_ifoTradedProductImpl,
                l_dblQuantity,
                l_side,
                false,
                false);

        WEB3GentradeCommission l_wGentradeCommission = new WEB3GentradeCommission();
        //概算受渡代金計算結果クラスコンストラクタ。
        WEB3IfoEstimateDeliveryAmountCalcResult l_ifoEstimateDeliveryAmountCalcResultW = 
            new WEB3IfoEstimateDeliveryAmountCalcResult();
                  
        //買建、かつ、W指値注文の場合
        //(新規建注文内容.isBuyToOpenOrder() == true &&
        // 新規建注文内容.発注条件() == "W指値")処理を行う。
        if (l_ifoOpenContractOrderSpec.isBuyToOpenOrder() 
            && WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(
                l_ifoOpenContractOrderSpec.getOrderCond()))
        {
            //(*2)W指値用手数料オブジェクトに以下の通りプロパティをセットする
            //W指値用手数料.注文チャネル = this.getログインチャネル()
            l_wGentradeCommission.setOrderChannel(this.getLoginChannel());
            
            //W指値用手数料.証券会社コード = 補助口座.get証券会社().getInstitutionCode()
            l_wGentradeCommission.setInstitutionCode(l_subAccount.getInstitution().getInstitutionCode());
            
            //W指値用手数料.部店ID = 補助口座.get取引店().getBranchId()
            l_wGentradeCommission.setBranchId(l_subAccount.getWeb3GenBranch().getBranchId());
            
            //W指値用手数料.発注日 = 取引時間管理.get発注日()
            l_wGentradeCommission.setOrderBizDate(
                new Timestamp(WEB3GentradeTradingTimeManagement.getOrderBizDate().getTime()));
            
            //W指値用手数料.取引コード(SONAR) = ”51：建”
            l_wGentradeCommission.setSonarTradedCode(WEB3TransactionTypeSONARDef.OPEN_CONTRACT);

            //W指値用手数料.手数料商品コード = ”51：株価指数OP”
            l_wGentradeCommission.setCommissionProductCode(WEB3CommisionProductCodeDef.INDEX_OP);        
            
            //W指値用手数料.弁済区分 = ”00：その他”
            l_wGentradeCommission.setPayType(WEB3PayTypeDef.OTHER);
            
            //W指値用手数料.is指値 = 新規建注文内容.(W指値)訂正指値 != 0の場合、true。以外、false。
            if (l_ifoOpenContractOrderSpec.getWLimitPriceChange() == 0 )
            {
                l_wGentradeCommission.setIsLimitPrice(false);                
            }
            else
            {
                l_wGentradeCommission.setIsLimitPrice(true);
            }
            
            //W指値用手数料.原資産銘柄コード = 先物OP銘柄.get原資産銘柄コード()
            l_wGentradeCommission.setUnderlyingProductCode(
                l_ifoProductImpl.getUnderlyingProductCode());
        
            //W指値用手数料.数量 = 新規建注文内容.getQuantity()
            l_wGentradeCommission.setQuantity(l_dblQuantity);
            
            //W指値訂正時の概算受渡代金を計算する。
            //[calc概算受渡代金()に指定する引数]
            //手数料：　@W指値用手数料オブジェクト
            //計算単価：　@新規建注文内容.(W指値)訂正指値
            //補助口座：　@補助口座オブジェクト
            //先物OP取引銘柄：　@先物OP取引銘柄オブジェクト
            //数量： 新規建注文内容.getQuantity()
            //売買： 
            //  リクエストデータ.建区分 = ”買”の場合SideEnum.BUY
            //  リクエストデータ.建区分 = ”売”の場合SideEnum.SELL
            //is返済注文：　@false
            //isSkip金額チェック：　@false
            l_ifoEstimateDeliveryAmountCalcResultW =
                l_optionOrderManagerImpl.calcEstimateDeliveryAmount(
                    l_wGentradeCommission,
                    l_ifoOpenContractOrderSpec.getWLimitPriceChange(),
                    l_subAccount,
                    l_ifoTradedProductImpl,
                    l_dblQuantity,
                    l_side,
                    false,
                    false);   
        }

        //(*3)インタセプタオブジェクトのプロパティに以下の値をセットする。
        WEB3IfoOpenContractUpdateInterceptor l_ifoOpenContractUpdateInterceptor = 
            new WEB3IfoOpenContractUpdateInterceptor(l_ifoOpenContractOrderSpec);
        
        WEB3IfoEstimateDeliveryAmountCalcResult l_calcResultForCheck = null;
        boolean l_blnIsWLimitCommission = false;
        //[概算受渡代金と手数料の設定] 
        //(*)買建、かつ、W指値注文の場合は、以下の判定を行う
        //戻り値(*1)と戻り値(*2)の拘束売買代金を比較して、
        //比較結果が高いほうの戻り値の概算受渡代金計算結果オブジェクトを使用する
        if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_ifoOpenContractOrderSpec.getOrderCond())
            && l_ifoOpenContractOrderSpec.isBuyToOpenOrder())
        {
            if (l_ifoEstimateDeliveryAmountCalcResult.getRestraintTurnover() >= l_ifoEstimateDeliveryAmountCalcResultW.getRestraintTurnover())
            {
                //インタセプタ.手数料 = （calc概算受渡代金()引数の手数料オブジェクト）
                l_ifoOpenContractUpdateInterceptor.setCommision(l_gentradeCommission);
                //インタセプタ.概算受渡代金計算結果 = （calc概算受渡代金()の戻り値）
                l_ifoOpenContractUpdateInterceptor.setEstimateDeliveryAmountCalcResult(l_ifoEstimateDeliveryAmountCalcResult);
                
                l_calcResultForCheck = l_ifoEstimateDeliveryAmountCalcResult;
            }
            else
            {
                l_blnIsWLimitCommission = true;
                //インタセプタ.手数料 = （calc概算受渡代金()引数の手数料オブジェクト）
                l_ifoOpenContractUpdateInterceptor.setCommision(l_wGentradeCommission);
                //インタセプタ.概算受渡代金計算結果 = （calc概算受渡代金()の戻り値）
                l_ifoOpenContractUpdateInterceptor.setEstimateDeliveryAmountCalcResult(l_ifoEstimateDeliveryAmountCalcResultW);
                l_calcResultForCheck = l_ifoEstimateDeliveryAmountCalcResultW;
            }
        }
        else
        {
            //インタセプタ.手数料 = （calc概算受渡代金()引数の手数料オブジェクト）
            l_ifoOpenContractUpdateInterceptor.setCommision(l_gentradeCommission);
            //インタセプタ.概算受渡代金計算結果 = （calc概算受渡代金()の戻り値）
            l_ifoOpenContractUpdateInterceptor.setEstimateDeliveryAmountCalcResult(l_ifoEstimateDeliveryAmountCalcResult);
            l_calcResultForCheck = l_ifoEstimateDeliveryAmountCalcResult;
        }

        //インタセプタ.発注条件 = リクエストデータ.発注条件区分
        l_ifoOpenContractUpdateInterceptor.setOrderCond(l_request.orderCondType);
        
        //インタセプタ.発注条件演算子 = リクエストデータ.発注条件演算子　@（*発注条件区分で逆指値/W指値を判定）
        //インタセプタ.逆指値基準値タイプ = リクエストデータ.プレミアム／原資産価格　@（*発注条件区分で逆指値/W指値を判定）
        //インタセプタ.逆指値基準値 = リクエストデータ.発注条件単価　@（*発注条件区分で逆指値/W指値を判定）        
        if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            l_ifoOpenContractUpdateInterceptor.setOrderCondOperator(l_request.wlimitOrderCondOperator);
            l_ifoOpenContractUpdateInterceptor.setStopOrderBasePriceType(l_request.wlimitPremium_underlyingAssets);
            l_ifoOpenContractUpdateInterceptor.setStopOrderBasePrice(Double.parseDouble(l_request.wlimitOrderCondPrice));            
        }
        else if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            l_ifoOpenContractUpdateInterceptor.setOrderCondOperator(l_request.stopOrderCondOperator);
            l_ifoOpenContractUpdateInterceptor.setStopOrderBasePriceType(l_request.stopPremium_underlyingAssets);
            l_ifoOpenContractUpdateInterceptor.setStopOrderBasePrice(Double.parseDouble(l_request.stopOrderCondPrice));               
        }
        //インタセプタ.(W指値)訂正指値 = 新規建注文内容.(W指値)訂正指値      
        l_ifoOpenContractUpdateInterceptor.setWLimitPriceChange(l_ifoOpenContractOrderSpec.getWLimitPriceChange());
        //インタセプタ.立会区分 = 取引時間管理.get立会区分()
        l_ifoOpenContractUpdateInterceptor.setSessionType(WEB3GentradeTradingTimeManagement.getSessionType());

        //　@取引余力サービス.validate取引余力()をコールする。
        //　@[引数]
        //　@　@補助口座　@：　@補助口座
        //　@　@注文内容インタセプタ[]　@：　@先物OP新規建更新インタセプタを要素とした配列
        //　@　@注文内容[]　@：　@新規建注文内容を要素とした配列
        //　@　@余力更新フラグ　@：　@false
        Object[] l_orderSpecIntercepter = {l_ifoOpenContractUpdateInterceptor};
        Object[] l_orderSpec = {l_ifoOpenContractOrderSpec};
        this.validateTradingPower(l_subAccount, l_orderSpecIntercepter, l_orderSpec, false);

        WEB3GentradeBranch l_branch = (WEB3GentradeBranch) l_subAccount.getMainAccount().getBranch();
        WEB3OptionsOpenMarginConfirmResponse l_response = 
            (WEB3OptionsOpenMarginConfirmResponse) l_request.createResponse();

        //市場閉局警告指数
        String[] l_tradeCloseMarkets = 
            WEB3GentradeTradingTimeManagement.getTradeCloseSuspension(l_branch, WEB3FuturesOptionDivDef.OPTION);

        //set単価
        //　@[指定する引数]
        //　@OP新規建注文リクエストアダプタ　@：　@生成した同名オブジェクト
        //　@レスポンス　@：　@生成したレスポンス
        this.setPrice(l_requestAdapter, l_response);

        //(*4)レスポンスデータに以下の通りプロパティをセットする。
        //レスポンス.注文ID = OP注文マネージャ.createNewOrderId()の戻り値
        l_response.orderId = "" + l_optionOrderManagerImpl.createNewOrderId();
        
        IfoProductRow l_ifoProductRow = (IfoProductRow) l_ifoProductImpl.getDataSourceObject();
        //レスポンス.銘柄コード = 先物OP銘柄.銘柄コード
        l_response.opProductCode = l_ifoProductImpl.getProductCode();
        //レスポンス.銘柄名 = 先物OP銘柄.銘柄名
        l_response.opProductName = l_ifoProductRow.getStandardName();
        
        //レスポンス.概算建代金 = (*)概算受渡代金計算結果.概算受渡代金
        l_response.estimatedPrice = 
            WEB3StringTypeUtility.formatNumber(l_calcResultForCheck.getEstimateDeliveryAmount());

        //レスポンス.手数料コース = (*)概算受渡代金計算結果.手数料コース
        l_response.commissionCourse = l_calcResultForCheck.getCommissionCourse();
        
        //レスポンス.手数料 = (*)概算受渡代金計算結果.手数料
        l_response.commission = WEB3StringTypeUtility.formatNumber(l_calcResultForCheck.getCommission());
        
        //レスポンス.手数料消費税 = (*)概算受渡代金計算結果.手数料消費税
        l_response.commissionConsumptionTax = 
            WEB3StringTypeUtility.formatNumber(l_calcResultForCheck.getCommissionTax());
        
        //レスポンス.確認時単価 = (*)時価を取得している場合（概算受渡代金の手数料オブジェクト.is指値() == falseの場合）、
        //概算受渡代金計算結果.計算単価
        //(*)買建、かつ、W指値注文の場合は、証拠金・余力チェックに使用した概算受渡計算結果を用いる
        if (l_blnIsWLimitCommission)
        {
            if (!l_wGentradeCommission.isLimitPrice())
            {
                l_response.checkPrice = WEB3StringTypeUtility.formatNumber(l_calcResultForCheck.getCalcUnitPrice());
            }
        }
        else
        {
            if (!l_gentradeCommission.isLimitPrice())
            {
                l_response.checkPrice = WEB3StringTypeUtility.formatNumber(l_calcResultForCheck.getCalcUnitPrice());
            }
        }
        
        //レスポンス.確認時発注日 = get発注日()の戻り値
        l_response.checkDate = WEB3DateUtility.toDay(l_datBizDate);
        //レスポンス.取引終了警告文言 = get市場閉局警告指数()の戻り値
        l_response.messageSuspension = l_tradeCloseMarkets;
        //レスポンス.注文有効期限 = 新規建注文内容.注文失効日
        l_response.expirationDate = l_ifoOpenContractOrderSpec.getOrderExpDate();

        log.exiting(STR_METHOD_NAME);
        return l_response;

    }

    /**
     * (submit注文)<BR>
     * <BR>
     * 株価指数オプション新規建注文を登録する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（OP新規建サービス）submit注文」参照。<BR>
     * <BR>
     * <BR>
     * @@param l_request - 株価指数オプション新規建注文完了リクエストデータオブジェクト
     * @@return WEB3OptionsOpenMarginCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 404EF125017F
     */
    protected WEB3OptionsOpenMarginCompleteResponse submitOrder(WEB3OptionsOpenMarginCompleteRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitOrder(WEB3OptionsOpenMarginCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        
        //リクエストデータのチェックを実施する。
        l_request.validate();

        //発注日を取得する。        
        //[get発注日()に指定する引数] 
        //確認時発注日 = リクエストデータ.確認時発注日 
        Date l_datBizDate = null;
        if (l_request.checkDate != null)
        {
            l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate(l_request.checkDate);
        }

                
        //3.get補助口座()
        WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount) getSubAccount();

        Trader l_trader = this.getTrader();

        //createリクエストアダプタ(WEB3GenRequest)
        WEB3OptionOpenContractOrderRequestAdapter l_requestAdapter = this.createRequestAdapter(l_request);

        //get単価( )
        double l_dblLimitPrice = l_requestAdapter.getPrice();

        //取得証券会社
        Institution l_institution = l_subAccount.getInstitution();

        //先物OP取引銘柄オブジェクトを取得する。        
        //銘柄オブジェクトを取得する。該当する銘柄が存在しない場合は例外をスローする。        
        WEB3IfoProductManagerImpl l_ifoProductManagerImpl = 
            (WEB3IfoProductManagerImpl) l_finApp.getTradingModule(ProductTypeEnum.IFO).getProductManager();

        WEB3IfoProductImpl l_ifoProductImpl = null;
        
        IfoDerivativeTypeEnum l_derivativeTypeEnum = null;
        if (WEB3IfoProductTypeDef.CALL_OPTIONS.equals(l_request.opProductType))
        {
            l_derivativeTypeEnum = IfoDerivativeTypeEnum.CALL_OPTIONS;
        }
        else if (WEB3IfoProductTypeDef.PUT_OPTIONS.equals(l_request.opProductType))
        {
            l_derivativeTypeEnum = IfoDerivativeTypeEnum.PUT_OPTIONS;
        }
        double l_dblStrikePrice = 0.0D;
        if (l_request.strikePrice != null)
        {
            l_dblStrikePrice = Double.parseDouble(l_request.strikePrice);
        }
        //銘柄オブジェクトを取得する。該当する銘柄が存在しない場合は例外をスローする。
        //証券会社：　@ 
        // 補助口座に関連する証券会社オブジェクト 
        //銘柄コード： 
        // リクエストデータ.銘柄コード 
        try
        {
            if (l_request.opProductCode != null)
            {    
                l_ifoProductImpl = l_ifoProductManagerImpl.getIfoProduct(l_institution, l_request.opProductCode);                 
            }
            //先物OPプロダクトマネージャ.get銘柄(証券会社、原資産銘柄コード、・・・)はリクエストデータ.銘柄コード==の場合、コール。
            //銘柄オブジェクトを取得する。 
            else
            {
                l_ifoProductImpl = 
                    l_ifoProductManagerImpl.getIfoProduct(
                    l_institution, 
                    l_request.targetProductCode, 
                    l_request.delivaryMonth, 
                    l_derivativeTypeEnum, 
                    l_dblStrikePrice,
                    WEB3DivisionTypeDef.DIVISION_DEFAULT,
                    l_request.marketCode);
            }
        }
        catch (NotFoundException l_nfex)
        {
            log.error(STR_METHOD_NAME, l_nfex);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00301, STR_METHOD_NAME);
        } 
        
        //新規建注文内容オブジェクトを生成する。
        WEB3IfoOpenContractOrderSpec l_ifoOpenContractOrderSpec = null;
        IfoOrderExecutionConditionType l_orderExecutionConditionType = null;
        
        //執行条件：　@
        //先物OPデータアダプタ.get執行条件(リクエストデータ.執行条件)の戻り値 
        l_orderExecutionConditionType = 
            WEB3IfoDataAdapter.getExecutionConditionType(l_request.execCondType);
       
        //is買建：　@リクエストデータ.建区分 == ”買建”の場合true、以外false 
        boolean l_blnIsBuyContract = false;
        if (WEB3IfoContractTypeDef.OPEN_BUY.equals(l_request.contractType))
        {
            l_blnIsBuyContract = true;
        }

        double l_dblWLimitPrice = 0D;
        //(W指値)訂正指値：　@リクエストデータ.W指値用注文単価 
        if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType)
            && WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_request.wLimitOrderPriceDiv))
        {
            //指値
            l_dblWLimitPrice = Double.parseDouble(l_request.wLimitPrice);
        }
        else 
        {
            l_dblWLimitPrice = 0D;
        }
        
        //逆指値基準値： 
        //　@[リクエストデータ.発注条件区分 == ”逆指値”の場合]
        //   リクエストデータ.逆指値用発注条件単価
        //  [リクエストデータ.発注条件区分 == ”W指値”の場合]
        //   リクエストデータ.W指値用発注条件単価
        double l_dblStopOrderBasePrice = 0.0D;
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
        {   
            l_dblStopOrderBasePrice = Double.parseDouble(l_request.stopOrderCondPrice);            
        }
        else if(WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            l_dblStopOrderBasePrice = Double.parseDouble(l_request.wlimitOrderCondPrice);            
        }
        
        //（W指値）執行条件：　@
        //先物OPデータアダプタ.get執行条件(リクエストデータ.W指値用執行条件)の戻り値
        IfoOrderExecutionConditionType l_wLimitExecCondType = 
            WEB3IfoDataAdapter.getExecutionConditionType(l_request.wlimitExecCondType);
        
        //初回注文の注文単位ID：
        //先物OPデータアダプタ.get初回注文の注文単位ID(リクエストデータ.注文期限区分)の戻り値
        Long l_lngFirstOrderUnitId =
            WEB3IfoDataAdapter.getFirstOrderUnitId(l_request.expirationDateType);

        //夕場前繰越対象フラグ：　@先物OPデータアダプタ.get夕場前繰越対象フラグ
        //(リクエストデータ.注文期限区分, 補助口座に該当する部店.部店ID)の戻り値
        boolean l_blnEveningSessionCarryOverFlag =
            WEB3IfoDataAdapter.getEveningSessionCarryOverFlag(
                l_request.expirationDateType,
                l_subAccount.getMainAccount().getBranch().getBranchId());

        // 注文失効日：　@OP注文マネージャ.get注文有効期限(リクエストデータ.注文有効期限,
        // 先物OP銘柄.銘柄コード,リクエストデータ.取引市場,”オプション”)の戻り値
        // OP注文マネージャ
        WEB3OptionOrderManagerImpl l_optionOrderManagerImpl =
            (WEB3OptionOrderManagerImpl) l_finApp.getTradingModule(
                ProductTypeEnum.IFO).getOrderManager();
        // 先物OP銘柄.銘柄コード
        String l_strProductCode = l_ifoProductImpl.getProductCode();
        Date l_datExpirationDate = l_optionOrderManagerImpl.getExpirationDate(
            l_request.expirationDate,
            l_strProductCode,
            l_request.marketCode,
            WEB3FuturesOptionDivDef.OPTION);

        l_ifoOpenContractOrderSpec =
            WEB3IfoOpenContractOrderSpec.createOpenContractOrderSpec(
                l_institution.getInstitutionCode(),
                (WEB3GentradeTrader) l_trader,
                l_blnIsBuyContract,
                l_request.marketCode,
                l_ifoProductImpl,
                Double.parseDouble(l_request.opOrderQuantity),
                l_dblLimitPrice,
                l_orderExecutionConditionType,
                l_datExpirationDate,
                l_request.orderCondType,
                l_dblStopOrderBasePrice,
                l_dblWLimitPrice,
                l_wLimitExecCondType,
                l_request.expirationDateType,
                l_lngFirstOrderUnitId,
                l_blnEveningSessionCarryOverFlag);

        //先物OP新規建注文の発注審査を実施する。        
        NewOrderValidationResult l_newOrderValidationResult = 
            l_optionOrderManagerImpl.validateOpenContractOrder(l_subAccount, l_ifoOpenContractOrderSpec);
        
        if (l_newOrderValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug("ProcessingResult() = " + l_newOrderValidationResult.getProcessingResult());
            throw new WEB3BaseException(
                l_newOrderValidationResult.getProcessingResult().getErrorInfo(),
                STR_METHOD_NAME);
        }
        
        //新規建注文内容.getQuantity()を取得する。
        double l_dblQuantity = l_ifoOpenContractOrderSpec.getQuantity();
        if (Double.isNaN(l_dblQuantity))
        {
            l_dblQuantity = 0D;
        }
        
        //手数料オブジェクトを生成する。
        WEB3GentradeCommission l_gentradeCommission = new WEB3GentradeCommission();        
        //(*1)手数料オブジェクトにアクセサメソッドを使用しプロパティをセットする。
        
        //手数料.注文チャネル = this.getログインチャネル()
        l_gentradeCommission.setOrderChannel(this.getLoginChannel());
        
        //手数料.証券会社コード = 補助口座.get証券会社().getInstitutionCode()
        l_gentradeCommission.setInstitutionCode(l_subAccount.getInstitution().getInstitutionCode());
        
        //手数料.部店ID = 補助口座.get取引店().getBranchId()
        l_gentradeCommission.setBranchId((l_subAccount).getWeb3GenBranch().getBranchId());
        
        Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        //手数料.発注日 = 取引時間管理.get発注日()
        l_gentradeCommission.setOrderBizDate(new Timestamp(l_datOrderBizDate.getTime()));
        
        //手数料.取引コード(SONAR) = ”51：建”
        l_gentradeCommission.setSonarTradedCode(WEB3TransactionTypeSONARDef.OPEN_CONTRACT);
        
        //手数料.手数料商品コード = ”51：株価指数OP”
        l_gentradeCommission.setCommissionProductCode(WEB3CommisionProductCodeDef.INDEX_OP);
        
        //手数料.弁済区分 = ”00：その他”        
        l_gentradeCommission.setPayType(WEB3PayTypeDef.OTHER);

        //手数料.is指値をセットする。
        //　@1)リクエストデータ.注文ID == nullの場合、
        //　@ 新規建注文内容.isLimitOrder()
        //　@2)上記以外、this.is指値(リクエストデータ)。
        if(l_request.orderId == null)
        {
            l_gentradeCommission.setIsLimitPrice(l_ifoOpenContractOrderSpec.isLimitOrder());   
        }
        else
        {
            l_gentradeCommission.setIsLimitPrice(this.isLimitPrice(l_request));
        }
        
        //手数料.原資産銘柄コード = 先物OP銘柄.get原資産銘柄コード()
        l_gentradeCommission.setUnderlyingProductCode(
            l_ifoProductImpl.getUnderlyingProductCode());
        
        //手数料.数量 = 新規建注文内容.getQuantity()
        l_gentradeCommission.setQuantity(l_dblQuantity);
        
        WEB3IfoTradedProductImpl l_ifoTradedProductImpl = null;
        try
        {
            l_ifoTradedProductImpl = 
                l_ifoProductManagerImpl.getIfoTradedProduct(l_subAccount.getInstitution(), l_ifoProductImpl.getProductCode(), l_request.marketCode);    
        }
        catch (NotFoundException l_nfex)
        {
            log.error(STR_METHOD_NAME, l_nfex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00301, 
                STR_METHOD_NAME);
        } 
        
        //概算受渡代金計算結果クラスコンストラクタ。
        WEB3IfoEstimateDeliveryAmountCalcResult l_ifoEstimateDeliveryAmountCalcResult = 
            new WEB3IfoEstimateDeliveryAmountCalcResult();        

        SideEnum l_side = null;
        if (WEB3IfoContractTypeDef.OPEN_BUY.equalsIgnoreCase(l_request.contractType))
        {
            l_side = SideEnum.BUY;
        }
        else
        {
            l_side = SideEnum.SELL;
        }

        double l_dblPrice = 0D;
        double l_dblWLimitPriceTemp = 0D;
        if (l_request.wLimitPrice != null)
        {
            l_dblWLimitPriceTemp = Double.parseDouble(l_request.wLimitPrice);
        }
        if (l_request.orderId != null)
        {
            if (l_request.checkPrice != null)
            {
                l_dblPrice = Double.parseDouble(l_request.checkPrice); 
            }
            else
            {
                if (l_ifoOpenContractOrderSpec.isBuyToOpenOrder()
                    && l_ifoOpenContractOrderSpec.getLimitPrice() < l_dblWLimitPriceTemp)
                {
                    l_dblPrice = l_dblWLimitPriceTemp;
                }
                else
                {
                    l_dblPrice = l_ifoOpenContractOrderSpec.getLimitPrice();
                }
            }
        }
        else
        {
            l_dblPrice = l_ifoOpenContractOrderSpec.getLimitPrice(); 
        }

        //[calc概算受渡代金()に指定する引数]
        //手数料：　@手数料オブジェクト
        //指値：
        //　@[リクエストデータ.注文ID != nullの場合]
        //　@　@リクエストデータ.確認時単価 != nullの場合、リクエストデータ.確認時単価。
        //　@　@リクエストデータ.確認時単価 == nullの場合、
        //　@　@　@注文内容.isBuyToOpenOrder() == true 
        //      and 注文内容.getLimitPrice() < リクエストデータ.W指値用注文単価(*1) ならば、
        //　@　@　@　@リクエストデータ.W指値用注文単価(*1)をセット。
        //　@　@　@以外、注文内容.getLimitPrice()をセット。
        //　@[上記以外]（リッチクライアント起動）
        //　@　@注文内容.getLimitPrice()をセット。
        //　@(*1　@W指値用注文単価 == nullの場合、ゼロをセット）
        //補助口座：　@補助口座オブジェクト
        //先物OP取引銘柄：　@先物OP取引銘柄オブジェクト
        //数量： 新規建注文内容.getQuantity()
        //売買：
        //  リクエストデータ.建区分 = ”買”の場合SideEnum.BUY
        //  リクエストデータ.建区分 = ”売”の場合SideEnum.SELL
        //is返済注文：　@false
        //isSkip金額チェック：　@false
        l_ifoEstimateDeliveryAmountCalcResult =
            l_optionOrderManagerImpl.calcEstimateDeliveryAmount(
            l_gentradeCommission,
            l_dblPrice,
            l_subAccount,
            l_ifoTradedProductImpl,
            l_dblQuantity,
            l_side,
            false,
            false);

        //リクエストデータ.確認時単価==null
        //W指値用手数料オブジェクト作成後、calc概算受渡代金()をコールする処理を追加。
        //手数料オブジェクトを生成する。
        WEB3GentradeCommission l_wLimitPriceCommission = new WEB3GentradeCommission();
        WEB3IfoEstimateDeliveryAmountCalcResult l_amountCalcResult = null;
        //リクエストデータ.注文ID==null&&買建&&W指値の場合、
        if (l_request.orderId == null && l_ifoOpenContractOrderSpec.isBuyToOpenOrder() 
            && WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_ifoOpenContractOrderSpec.getOrderCond()))
        {
            //(*3)W指値用手数料オブジェクトに以下の通りプロパティをセットする
            //W指値用手数料.注文チャネル = this.getログインチャネル()
            l_wLimitPriceCommission.setOrderChannel(this.getLoginChannel());
            
            //W指値用手数料.証券会社コード = 補助口座.get証券会社().getInstitutionCode()
            l_wLimitPriceCommission.setInstitutionCode(l_subAccount.getInstitution().getInstitutionCode());
            
            //W指値用手数料.部店ID = 補助口座.get取引店().getBranchId()
            l_wLimitPriceCommission.setBranchId((l_subAccount).getWeb3GenBranch().getBranchId());
            
            //W指値用手数料.発注日 = 取引時間管理.get発注日()
            l_wLimitPriceCommission.setOrderBizDate(new Timestamp(l_datOrderBizDate.getTime()));
            
            //W指値用手数料.取引コード(SONAR) = ”51：建”
            l_wLimitPriceCommission.setSonarTradedCode(WEB3TransactionTypeSONARDef.OPEN_CONTRACT);
            
            //W指値用手数料.手数料商品コード = ”51：株価指数OP”
            l_wLimitPriceCommission.setCommissionProductCode(WEB3CommisionProductCodeDef.INDEX_OP);
            
            //W指値用手数料.弁済区分 = ”00：その他”        
            l_wLimitPriceCommission.setPayType(WEB3PayTypeDef.OTHER);
            
            //W指値用手数料.is指値をセットする。
            if (l_ifoOpenContractOrderSpec.getWLimitPriceChange() != 0)
            {
                l_wLimitPriceCommission.setIsLimitPrice(true);
            }
            else
            {
                l_wLimitPriceCommission.setIsLimitPrice(false);
            }
            
            //W指値用手数料.原資産銘柄コード = 先物OP銘柄.get原資産銘柄コード()
            l_wLimitPriceCommission.setUnderlyingProductCode(
                l_ifoProductImpl.getUnderlyingProductCode());
        
            //W指値用手数料.数量 = 新規建注文内容.getQuantity()
            l_wLimitPriceCommission.setQuantity(l_dblQuantity);
            
            //W指値訂正時の概算受渡代金を計算する。 
            //[calc概算受渡代金()に指定する引数] 
            //手数料：　@W指値用手数料オブジェクト 
            //計算単価：　@新規建注文内容.(W指値)訂正指値 
            //補助口座：　@補助口座オブジェクト 
            //先物OP取引銘柄：　@先物OP取引銘柄オブジェクト 
            //数量： 新規建注文内容.getQuantity() 
            //売買：  
            //　@リクエストデータ.建区分 = ”買”の場合SideEnum.BUY 
            //　@リクエストデータ.建区分 = ”売”の場合SideEnum.SELL 
            //is返済注文：　@false 
            //isSkip金額チェック：　@false                 
            l_amountCalcResult =
                l_optionOrderManagerImpl.calcEstimateDeliveryAmount(
                l_wLimitPriceCommission,
                l_ifoOpenContractOrderSpec.getWLimitPriceChange(),
                l_subAccount,
                l_ifoTradedProductImpl,
                l_dblQuantity,
                l_side,
                false,
                false);
            
        }
        
        //(*4)インタセプタオブジェクトのプロパティに以下の値をセットする。
        //インタセプタを生成する。
        //[コンストラクタの引数] 
        //新規建注文内容：　@create新規建注文内容()で生成した注文内容。 
        WEB3IfoOpenContractUpdateInterceptor l_ifoOpenContractUpdateInterceptor = 
            new WEB3IfoOpenContractUpdateInterceptor(l_ifoOpenContractOrderSpec);
        
        //［概算受渡代金と手数料の設定］
        //(*)リクエストデータ.注文ID==null、かつ、買建、かつ、W指値注文の場合、以下判定を行う。
        //戻り値(*1)と戻り値(*2)の拘束売買代金を比較して、
        //比較結果の高いほうの戻り値の概算受渡代金計算オブジェクトを使用する。
        //インタセプタ.手数料 = （calc概算受渡代金()引数の手数料オブジェクト）
        if (l_request.orderId == null && l_ifoOpenContractOrderSpec.isBuyToOpenOrder() 
            && WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_ifoOpenContractOrderSpec.getOrderCond()))
        {
           if (l_amountCalcResult.getRestraintTurnover() > l_ifoEstimateDeliveryAmountCalcResult.getRestraintTurnover())
           {
               l_ifoOpenContractUpdateInterceptor.setEstimateDeliveryAmountCalcResult(l_amountCalcResult);
               l_ifoOpenContractUpdateInterceptor.setCommision(l_wLimitPriceCommission);
           }
           else
           {
               l_ifoOpenContractUpdateInterceptor.setEstimateDeliveryAmountCalcResult(l_ifoEstimateDeliveryAmountCalcResult);
               l_ifoOpenContractUpdateInterceptor.setCommision(l_gentradeCommission);
           }
        }
        else
        {
            l_ifoOpenContractUpdateInterceptor.setEstimateDeliveryAmountCalcResult(l_ifoEstimateDeliveryAmountCalcResult);
            l_ifoOpenContractUpdateInterceptor.setCommision(l_gentradeCommission);
        }
        
        //インタセプタ.発注条件 = リクエストデータ.発注条件区分
        l_ifoOpenContractUpdateInterceptor.setOrderCond(l_request.orderCondType);
        
        //インタセプタ.発注条件演算子 = リクエストデータ.発注条件演算子　@（*発注条件区分で逆指値/W指値を判定）
        //インタセプタ.逆指値基準値タイプ = リクエストデータ.プレミアム／原資産価格　@（*発注条件区分で逆指値/W指値を判定）
        //インタセプタ.逆指値基準値 = リクエストデータ.発注条件単価　@（*発注条件区分で逆指値/W指値を判定）
        double l_dblStopOrderCondPrice = 0D;
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            l_dblStopOrderCondPrice = Double.parseDouble(l_request.stopOrderCondPrice);
        }
        
        double l_dblwLimitOrderCondPrice = 0D;
        if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            l_dblwLimitOrderCondPrice = Double.parseDouble(l_request.wlimitOrderCondPrice);
        }
        
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            l_ifoOpenContractUpdateInterceptor.setOrderCondOperator(l_request.stopOrderCondOperator);
            l_ifoOpenContractUpdateInterceptor.setStopOrderBasePriceType(l_request.stopPremium_underlyingAssets);
            l_ifoOpenContractUpdateInterceptor.setStopOrderBasePrice(l_dblStopOrderCondPrice);         
        }
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            l_ifoOpenContractUpdateInterceptor.setOrderCondOperator(l_request.wlimitOrderCondOperator);
            l_ifoOpenContractUpdateInterceptor.setStopOrderBasePriceType(l_request.wlimitPremium_underlyingAssets);
            l_ifoOpenContractUpdateInterceptor.setStopOrderBasePrice(l_dblwLimitOrderCondPrice);  
        }
      
        //インタセプタ.(W指値)訂正指値 = 新規建注文内容.(W指値)訂正指値
        l_ifoOpenContractUpdateInterceptor.setWLimitPriceChange(l_ifoOpenContractOrderSpec.getWLimitPriceChange());
        //インタセプタ.立会区分 = 取引時間管理.get立会区分()
        l_ifoOpenContractUpdateInterceptor.setSessionType(WEB3GentradeTradingTimeManagement.getSessionType());

        //　@取引余力サービス.validate取引余力()をコールする。
        //　@[引数]
        //　@　@補助口座　@：　@補助口座
        //　@　@注文内容インタセプタ[]　@：　@先物OP新規建更新インタセプタを要素とした配列
        //　@　@注文内容[]　@：　@新規建注文内容を要素とした配列
        //　@　@余力更新フラグ　@：　@true
        Object[] l_orderSpecIntercepter = {l_ifoOpenContractUpdateInterceptor};
        Object[] l_orderSpec = {l_ifoOpenContractOrderSpec};
        this.validateTradingPower(l_subAccount, l_orderSpecIntercepter, l_orderSpec, true);

        //インタセプタをセットする。
        l_optionOrderManagerImpl.setThreadLocalPersistenceEventInterceptor(l_ifoOpenContractUpdateInterceptor);

        //新規建注文登録処理を行う。
        //arg2（注文ＩＤ）： 
        //リクエストデータ.注文ID!=nullの場合、リクエストデータ.注文IDを設定。 
        //リクエストデータ.注文ID==nullの場合、OP注文マネージャ.createNewOrderId()の戻り値を設定。 
        long l_lngOrderId = 0;
        if (l_request.orderId != null)
        {
            l_lngOrderId = Long.parseLong(l_request.orderId);
        }
        else
        {
            l_lngOrderId = l_optionOrderManagerImpl.createNewOrderId();
        }

        //OP新規建注文登録処理を行う。
        //　@[指定する引数]
        //　@補助口座　@：　@補助口座オブジェクト
        //　@新規建注文内容　@：　@create新規建注文内容()で生成した新規建注文内容
        //　@注文ID　@：　@リクエストデータ.注文ID != nullの場合、リクエストデータ.注文ID
        //　@　@　@　@　@　@　@リクエストデータ.注文ID == nullの場合、OP注文マネージャ.createNewOrderId()の戻り値
        //　@取引パスワード　@：　@リクエストデータ.暗証番号
        //　@新規建リクエストアダプタ　@：　@createリクエストアダプタ()で生成したOP新規建リクエストアダプタオブジェクト
        //　@先物OP概算受渡代金計算結果　@：　@calc概算受渡代金()の戻り値
        this.submitOpenContractOrder(
            (WEB3GentradeSubAccount)l_subAccount,
            l_ifoOpenContractOrderSpec,
            l_lngOrderId,
            l_request.password,
            l_requestAdapter,
            l_ifoEstimateDeliveryAmountCalcResult);

        //(*5)レスポンスデータに以下の通りプロパティをセットする。
        WEB3OptionsOpenMarginCompleteResponse l_response =
            (WEB3OptionsOpenMarginCompleteResponse) l_request.createResponse();

        //レスポンス.更新時間 = 現在日時(GtlUtils.getSystemTimestamp())
        l_response.lastUpdatedTimestamp = GtlUtils.getSystemTimestamp();
        //リクエストデータ.注文ID!=nullの場合、リクエストデータ.注文IDを設定。
        //リクエストデータ.注文ID==nullの場合、OP注文マネージャ.createNewOrderId()の戻り値を設定。
        l_response.orderActionId = "" + l_lngOrderId;

       log.exiting(STR_METHOD_NAME);
       return l_response;
    }

    /**
     * 株価指数オプション注文サービス処理を実施する。<BR>
     * リクエストデータの型により、validate()注文または、submit注文()メソッドをコールする。<BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 404EF2AC004D
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        
        if (l_request instanceof WEB3OptionsOpenMarginConfirmRequest)
        {
            l_response = this.validateOrder((WEB3OptionsOpenMarginConfirmRequest) l_request);
        }
        else if (l_request instanceof WEB3OptionsOpenMarginCompleteRequest)
        {         
            l_response = this.submitOrder((WEB3OptionsOpenMarginCompleteRequest) l_request);
        }
        else
        {
            log.error(STR_METHOD_NAME + "パラメータタイプ不正。");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018, 
                STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;        
    }
    
    /**
     * (is指値)<BR>
     * 指値注文かどうか判別する。<BR>
     * 指値注文の場合は、true。以外、falseを返却する。<BR> 
     * ※非リッチクライアント（リクエストデータ.注文ID != null）の場合<BR>
     * 　@のみ使用すること。<BR> 
     * <BR>
     * 以下、引数.リクエストデータの値を使用して判別を行う。<BR>
     * <BR>
     * 確認時単価 == nullの場合、trueを返却する。<BR>
     * 以外、falseを返却する。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 株価指数オプション新規建注文完了リクエストオブジェクト<BR>
     * @@return boolean
     * @@throws WEB3BaseException 
     */
    protected boolean isLimitPrice(
        WEB3OptionsOpenMarginCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =  
            "isLimitPrice(WEB3OptionsOpenMarginCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null )
        {
            log.debug("リクエストデータ = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "リクエストデータ = null。");
        }

        if (l_request.checkPrice == null)
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }

    /**
     * (validate取引余力)<BR>
     * 証拠金・余力のチェックを行う。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「（OP新規建サービス）validate取引余力」を参照。<BR>
     * =============================================== <BR>
     * シーケンス図 : （OP新規建サービス）validate取引余力 <BR>
     * 具体位置     : 取引余力結果.is判定フラグ( )==falseの場合<BR>
     * 　@　@　@　@　@　@　@　@throw：取引余力エラー<BR>
     * class        : WEB3BusinessLayerException<BR>
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

        WEB3TPTradingPowerService l_tradingPowerService =
            (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
        //　@[引数]
        //　@補助口座　@：　@引数.補助口座
        //　@注文内容インタセプタ[]　@：　@引数.注文内容インタセプタ[]
        //　@注文内容[]　@：　@引数.注文内容[]
        //　@注文種別　@：
        //　@　@　@引数.注文内容[0].isBuyToOpenOrder() == trueの場合、"OP新規買建"
        //　@　@　@以外の場合、"OP新規売建"
        //　@余力更新フラグ　@：　@引数.余力更新フラグ
        OrderTypeEnum l_orderTypeEnum = null;
        WEB3IfoOpenContractOrderSpec l_ifoOpenContractOrderSpec =
            (WEB3IfoOpenContractOrderSpec)l_objOrderSpecs[0];
        if (l_ifoOpenContractOrderSpec.isBuyToOpenOrder())
        {
            l_orderTypeEnum = OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN;
        }
        else
        {
            l_orderTypeEnum = OrderTypeEnum.IDX_OPTIONS_SELL_TO_OPEN;
        }

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
     * オプション新規建注文の注文登録を行う。 <BR>
     * <BR>
     * ※処理の詳細はシーケンス図「（OP新規建サービス）submit注文」参照<BR>
      * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト。<BR>
     * @@param l_ifoOpenContractOrderSpec - (新規建注文内容)<BR>
     * 新規建注文内容オブジェクト。<BR>
     * @@param l_lngOrderId - (注文ID)<BR>
     * 注文ID。<BR>
     * @@param l_strTradingPassword - (取引パスワード)<BR>
     * 取引パスワード。<BR>
     * @@param l_requestAdapter - (新規建リクエストアダプタ)<BR>
     * OP新規建リクエストアダプタオブジェクト。<BR>
     * @@param l_estimateDeliveryAmountCalcResult - (先物OP概算受渡代金計算結果)<BR>
     * 先物OP概算受渡代金計算結果。<BR>
     * @@throws WEB3BaseException
     */
    protected void submitOpenContractOrder(
        WEB3GentradeSubAccount l_subAccount,
        IfoOpenContractOrderSpec l_ifoOpenContractOrderSpec,
        long l_lngOrderId,
        String l_strTradingPassword,
        WEB3OptionOpenContractOrderRequestAdapter l_requestAdapter,
        WEB3IfoEstimateDeliveryAmountCalcResult l_estimateDeliveryAmountCalcResult)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitOpenContractOrder(WEB3GentradeSubAccount, " +
            "IfoOpenContractOrderSpec, long, String, WEB3OptionOpenContractOrderRequestAdapter, " +
            "WEB3IfoEstimateDeliveryAmountCalcResult)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3OptionOrderManagerImpl l_optionOrderManagerImpl =
            (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();

        //　@[引数]
        //　@補助口座　@：　@引数.補助口座オブジェクト
        //　@新規建注文内容　@：　@引数.新規建注文内容
        //　@注文ＩＤ　@：　@引数.注文ID
        //　@取引パスワード　@：　@引数.取引パスワード
        //　@isSkip発注審査　@：　@true（固定）
        OrderSubmissionResult l_orderResult = 
            l_optionOrderManagerImpl.submitOpenContractOrder(
                l_subAccount,
                l_ifoOpenContractOrderSpec,
                l_lngOrderId,
                l_strTradingPassword,
                true);

        if (l_orderResult.getProcessingResult().isFailedResult())
        {
            log.debug("ProcessingResult() = " + l_orderResult.getProcessingResult());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_orderResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (createリクエストアダプタ)<BR>
     * リクエストアダプタのインスタンスを生成する。 <BR>
     * <BR>
     * ※処理の詳細はシーケンス図 <BR>
     * 「（OP新規建サービス）validate注文／（OP新規建サービス）submit注文」参照<BR>
     * @@param l_request - (リクエスト)<BR>
     * リクエストオブジェクト。<BR>
     * @@return WEB3OptionOpenContractOrderRequestAdapter
     * @@throws WEB3BaseException
     */
    protected WEB3OptionOpenContractOrderRequestAdapter createRequestAdapter(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createRequestAdapter(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        //リクエストアダプタを作成する。
        //リクエスト ： 引数.リクエストデータ
        WEB3OptionOpenContractOrderRequestAdapter l_adapter =
            WEB3OptionOpenContractOrderRequestAdapter.create(l_request);

        log.exiting(STR_METHOD_NAME);
        return l_adapter;
    }

    /**
     * (set単価)<BR>
     * 何もせずにreturnをする。（カラ実装）<BR>
     * @@param l_adapter - (OP新規建注文リクエストアダプタ)<BR>
     * OP新規建注文リクエストアダプタオブジェクト。<BR>
     * @@param l_response - (レスポンス)<BR>
     * レスポンスオブジェクト。<BR>
     * @@throws WEB3BaseException
     */
    protected void setPrice(WEB3OptionOpenContractOrderRequestAdapter l_adapter, WEB3GenResponse l_response)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "setPrice(WEB3OptionOpenContractOrderRequestAdapter, WEB3GenResponse)";
        log.entering(STR_METHOD_NAME);

        //何もせずにreturnする。（カラ実装）
        log.exiting(STR_METHOD_NAME);
        return;
    }
}@
