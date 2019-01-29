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
filename	WEB3FuturesChangeOpenContractServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数先物訂正新規建サービス実装クラス(WEB3FuturesChangeOpenContractServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/23 李強 (中訊) 新規作成
              001: 2004/9/7 李強 (中訊) BUG-381 を対応
              002: 2006/07/31 柴雙紅 (中訊) 仕様変更　@モデル492
                 : 2006/08/10 肖志偉 (中訊) 仕様変更　@モデル545
Revesion History : 2007/06/22 孫洪江 (中訊) 仕様変更　@モデル706 751
Revesion History : 2007/11/20 趙林鵬 (中訊)仕様変更 モデル809
Revesion History : 2007/11/28 趙林鵬 (中訊)Javaソース（基本設計と合っていない実装）009
Revesion History : 2008/03/17 張騰宇(中訊) モデル855
*/
package webbroker3.ifo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoOrderImpl;

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
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.define.WEB3StopBasePriceTypeDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ifo.WEB3FuturesClientRequestService;
import webbroker3.ifo.WEB3FuturesOrderManagerImpl;
import webbroker3.ifo.WEB3IfoDataAdapter;
import webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult;
import webbroker3.ifo.WEB3IfoOpenContractChangeSpec;
import webbroker3.ifo.WEB3IfoOpenContractChangeUpdateInterceptor;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoProductManagerImpl;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.message.WEB3FuturesOpenMarginChangeCompleteRequest;
import webbroker3.ifo.message.WEB3FuturesOpenMarginChangeCompleteResponse;
import webbroker3.ifo.message.WEB3FuturesOpenMarginChangeConfirmRequest;
import webbroker3.ifo.message.WEB3FuturesOpenMarginChangeConfirmResponse;
import webbroker3.ifo.service.delegate.WEB3FuturesChangeOpenContractService;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.triggerorder.base.service.delegate.WEB3ToSuccReservationIfoOrderUpdateService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (先物訂正新規建サービスImpl)<BR>
 * 株価指数先物訂正新規建サービス実装クラス<BR>
 * @@author 李強
 * @@version 1.0
 */
public class WEB3FuturesChangeOpenContractServiceImpl extends WEB3FuturesClientRequestService implements WEB3FuturesChangeOpenContractService 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FuturesChangeOpenContractServiceImpl.class);
    
    /**
     * @@roseuid 40F7A2D000CB
     */
    public WEB3FuturesChangeOpenContractServiceImpl() 
    {
     
    }
    
    /**
     * 株価指数先物訂正新規建サービス処理を実施する。<BR>
     * <BR>
     * リクエストデータの型により、validate注文()または、<BR>
     * submit注文()メソッドをコールする。<BR>
     * @@param l_request - リクエスト<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40A8A4F30368
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";

        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        
        if (l_request instanceof WEB3FuturesOpenMarginChangeConfirmRequest)
        {
            //リクエストデータの具象データ型が
            //「株価指数先物訂正新規建確認リクエスト」の場合
                l_response = validateOrder(
                    (WEB3FuturesOpenMarginChangeConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3FuturesOpenMarginChangeCompleteRequest)
        {
            //リクエストデータの具象データ型が
            //「株価指数先物当日注文約定詳細リクエスト」の場合
            l_response = submitOrder(
                (WEB3FuturesOpenMarginChangeCompleteRequest)l_request);
        }
        else
        {
            log.error("パラメータタイプ不正。");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
    
    /**
     * (validate注文)<BR>
     * 株価指数先物の訂正新規建発注審査を行う。<BR>
     * <BR>
     * 「（先物訂正新規建サービス）validate注文」参照。<BR>
     * <BR>
     * =============================================== <BR>
     * シーケンス図 : （先物訂正新規建サービス）validate注文２ <BR>
     * 具体位置     : 1.12 戻り値の取引余力結果.判定フラグ == false の場合、<BR>
     * 　@　@　@　@　@　@　@　@　@例外をスローする。 <BR>
     * class        : WEB3BusinessLayerException <BR>
     * tag          : BUSINESS_ERROR_01306 <BR>
     * =============================================== <BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 株価指数先物訂正新規建確認リクエスト<BR>
     * @@return WEB3FuturesOpenMarginChangeConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 40A8A4F30387
     */
    protected WEB3FuturesOpenMarginChangeConfirmResponse validateOrder(
        WEB3FuturesOpenMarginChangeConfirmRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validateOrder(WEB3FuturesOpenMarginChangeConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //1.validate           
            l_request.validate();
            
            //2.取引時間管理からget発注日          
            Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            Date l_datOrderBizDateDay = WEB3DateUtility.toDay(l_datOrderBizDate);
            log.debug("取引時間管理からget発注日: " + l_datOrderBizDate);         
            
            //3.IfoOrderImpl     
            TradingModule l_tradingModule = GtlUtils.getTradingModule(ProductTypeEnum.IFO);      
            WEB3FuturesOrderManagerImpl l_orderMgr = 
                (WEB3FuturesOrderManagerImpl) l_tradingModule.getOrderManager();
                
            long l_lngOrderId = Long.parseLong(l_request.id);
            IfoOrderImpl l_ifoOrderImpl = (IfoOrderImpl) l_orderMgr.getOrder(l_lngOrderId); 
            log.debug("l_lngOrderId = " + l_lngOrderId);     
                  
            //4.getOrderUnits           
            OrderUnit[] l_orderUnits = null;
            l_orderUnits = l_ifoOrderImpl.getOrderUnits();
            
            if (l_orderUnits.length==0)
            {
                log.error("該当データなし");
                throw new WEB3BaseException(
                       WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                       this.getClass().getName() + "." + STR_METHOD_NAME); 
            } 
                
            //5.create新規建訂正内容(long, long, double, double, IfoOrderExecutionConditionType, 
            //                      Date, Date, String, String, String, double, double, 
            //                      IfoOrderExecutionConditionType, String, String, boolean)
            double l_dblQuantity = 0D;
            if (WEB3StringTypeUtility.isNumber(l_request.futOrderQuantity))
            {
                l_dblQuantity = Double.parseDouble(l_request.futOrderQuantity);
            }
            
            double l_dblLimitPrice = 0D;
            boolean l_blnIsLimitPrice ;
            if (WEB3OrderPriceDivDef.MARKET_PRICE.equals(l_request.orderPriceDiv))
            {
                //成行
                l_dblLimitPrice = 0D;
                l_blnIsLimitPrice = false ;

            }
            else
            {
                //指値
                l_dblLimitPrice = Double.parseDouble(l_request.limitPrice);
                l_blnIsLimitPrice = true ;
            }             
                    
            long l_lngOrderUnitId = l_orderUnits[0].getOrderUnitId();
            log.debug("l_lngOrderUnitId = " + l_lngOrderUnitId);
            
            //訂正執行条件：　@先物OPデータアダプタ.get執行条件(リクエストデータ.執行条件) 
            IfoOrderExecutionConditionType l_changeExecCondType = 
                WEB3IfoDataAdapter.getExecutionConditionType(l_request.execCondType);      

            //OP注文マネージャ
            WEB3OptionOrderManagerImpl l_orderManager =
                (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();

            IfoOrderUnitRow l_ifoOrderUnitRow =
                (IfoOrderUnitRow)l_orderUnits[0].getDataSourceObject();

            //先物OP銘柄
            //(*1)先物OPプロダクトマネージャ.getProduct(注文単位.銘柄ID)にて取得
            WEB3IfoProductManagerImpl l_productMgr =
                (WEB3IfoProductManagerImpl)l_tradingModule.getProductManager();

            WEB3IfoProductImpl l_ifoProductImpl =
                (WEB3IfoProductImpl)l_productMgr.getProduct(l_ifoOrderUnitRow.getProductId());

            //市場
            Market l_market = l_ifoProductImpl.getPrimaryMarket();

            //訂正失効日：
            //リクエストデータ.注文有効期限 != nullの場合、OP注文マネージャ.get注文有効期限(
            //リクエストデータ.注文有効期限,先物OP銘柄(*1).銘柄コード,市場.getMarketCode(),”先物”)の戻り値
            Date l_datChangeExpirationDate = null;
            if (l_request.expirationDate == null)
            {
                l_datChangeExpirationDate = l_datOrderBizDateDay;
            }
            else
            {
                l_datChangeExpirationDate = l_orderManager.getExpirationDate(
                    l_request.expirationDate,
                    l_ifoProductImpl.getProductCode(),
                    l_market.getMarketCode(),
                    WEB3FuturesOptionDivDef.FUTURES);
            }

            // 発注条件演算子：　@ 
            //　@   リクエストデータ.発注条件区分 == ”逆指値”の場合、リクエストデータ.逆指値用発注条件演算子  
            //　@   リクエストデータ.発注条件区分 == ”W指値”の場合、リクエストデータ.W指値用発注条件演算子 
            // 逆指値基準値：　@ 
            //     リクエストデータ.発注条件区分 == ”逆指値”の場合、リクエストデータ.逆指値用発注条件単価  
            //     リクエストデータ.発注条件区分 == ”W指値”の場合、リクエストデータ.W指値用発注条件単価 
            String l_strOrderCondOperator = null;
            double l_dblStopOrderPrice = 0D;
            if(WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
            {
                l_strOrderCondOperator = l_request.stopOrderCondOperator;
                l_dblStopOrderPrice = Double.parseDouble(l_request.stopOrderCondPrice);
            }
            else if(WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
            {
                l_strOrderCondOperator = l_request.wlimitOrderCondOperator;
                l_dblStopOrderPrice = Double.parseDouble(l_request.wlimitOrderCondPrice);
            }

            //（W指値）訂正指値：　@リクエストデータ.W指値用注文単価
            double l_dblWLimitPrice = 0.0D;
            if (l_request.wLimitPrice != null)
            {
                l_dblWLimitPrice = Double.parseDouble(l_request.wLimitPrice);
            }

            //（W指値）執行条件：　@先物OPデータアダプタ.get執行条件(リクエストデータ.Ｗ指値用執行条件)
            IfoOrderExecutionConditionType l_wLimitExecCondType = 
                WEB3IfoDataAdapter.getExecutionConditionType(l_request.wlimitExecCondType);
            //（W指値）有効状態区分：　@リクエストデータ.Ｗ指値用有効状態区分  
            String l_strWLimitEnableStatusDiv = l_request.wlimitEnableStatusDiv;
            // 訂正後注文期限区分：　@リクエストデータ.注文期限区分
            String l_strExpirationDateType = l_request.expirationDateType;

            //夕場前繰越対象フラグ：
            //先物OPデータアダプタ.get夕場前繰越対象フラグ(リクエストデータ.注文期限区分, 注文単位.部店ID)の戻り値
            boolean l_blnEveningSessionCarryOverFlag = WEB3IfoDataAdapter.getEveningSessionCarryOverFlag(
                l_request.expirationDateType, l_ifoOrderUnitRow.getBranchId());

            WEB3IfoOpenContractChangeSpec l_web3IfoOpenContractChangeSpec = 
                WEB3IfoOpenContractChangeSpec.createIfoOpenContractChangeSpec(
                    l_lngOrderId, 
                    l_lngOrderUnitId,
                    l_dblQuantity,
                    l_dblLimitPrice,
                    l_changeExecCondType, 
                    l_datChangeExpirationDate,
                    l_datOrderBizDateDay,
                    l_request.orderCondType,
                    l_strOrderCondOperator,
                    WEB3StopBasePriceTypeDef.DEFAULT,
                    l_dblStopOrderPrice,
                    l_dblWLimitPrice,
                    l_wLimitExecCondType,
                    l_strWLimitEnableStatusDiv,
                    l_strExpirationDateType,
                    l_blnEveningSessionCarryOverFlag);
                
            //6.get補助口座( )       
            WEB3GentradeSubAccount l_subAccount  = (WEB3GentradeSubAccount) this.getSubAccount();
                
            //7.validate新規建訂正注文
            log.debug("l_subAccount = " + l_subAccount);
            log.debug("l_web3IfoOpenContractChangeSpec = " + l_web3IfoOpenContractChangeSpec);
              
            OrderValidationResult l_orderValidationResult = l_orderMgr.validateFuturesChangeOrder(l_subAccount, l_web3IfoOpenContractChangeSpec);
            if(l_orderValidationResult.getProcessingResult().isFailedResult())
            {
                log.debug("ProcessingResult() = " + l_orderValidationResult.getProcessingResult());
                throw new WEB3BusinessLayerException(
                    l_orderValidationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME);         
            }        
            
            //8.getDataSourceObject          
            IfoOrderUnitRow l_orderUnitRow =
                (IfoOrderUnitRow)l_orderUnits[0].getDataSourceObject();
            log.debug("l_orderUnitRow = " + l_orderUnitRow);
            
            //9.getTradedProduct       
            WEB3IfoTradedProductImpl l_ifoTradedProduct =
                (WEB3IfoTradedProductImpl)l_productMgr.getTradedProduct(l_orderUnitRow.getProductId(), l_orderUnitRow.getMarketId());
            log.debug("l_ifoTradedProduct = " + l_ifoTradedProduct);     
            
            //10.訂正数量を取得する。           
            double l_dblChangeQuantity  = 
                l_web3IfoOpenContractChangeSpec.getAfterChangeOriginalQuantity();
            log.debug("getAfterChangeOriginalQuantity = " + l_dblChangeQuantity);
            
            //11.合計約定数量を取得する(getExecutedQuantity)     
            double l_dblExecutedQuantity = l_orderUnits[0].getExecutedQuantity();
            log.debug("getExecutedQuantity = " + l_dblExecutedQuantity);
            
            //12.合計約定金額を取得する(getExecutedAmount)            
            double l_dblExecutedAmount = l_orderUnits[0].getExecutedAmount();
            log.debug("getExecutedAmount = " + l_dblExecutedAmount);
            
            if (Double.isNaN(l_dblExecutedQuantity))
            {
                l_dblExecutedQuantity = 0D;            
            }
            
            if (Double.isNaN(l_dblExecutedAmount))
            {
                l_dblExecutedAmount = 0D;     
            }
            
            //13.手数料           
            WEB3GentradeCommission l_commission = new WEB3GentradeCommission();
            log.debug("l_commission = " + l_commission);
            
            //14.(*1)プロパティセット 
            //注文チャネルをセットする。            
            l_commission.setOrderChannel(this.getLoginChannel());
            
            //証券会社コードをセットする。           
            l_commission.setInstitutionCode(l_subAccount.getInstitution().getInstitutionCode());
            
            //部店IDをセットする。                   
            l_commission.setBranchId(l_subAccount.getMainAccount().getBranch().getBranchId());
            log.debug("部店IDをセットする>> " + l_commission.getBranchId());
            
            //発注日をセットする。            
            l_commission.setOrderBizDate(new Timestamp(l_datOrderBizDate.getTime()));
            
            //取引コード（SONAR）をセットする。           
            l_commission.setSonarTradedCode(WEB3TransactionTypeSONARDef.OPEN_CONTRACT);
            log.debug("取引コードをセットする>> " + l_commission.getSonarTradedCode());
            
            //手数料商品コードをセットする。                    
            l_commission.setCommissionProductCode(WEB3CommisionProductCodeDef.INDEX_FUTURES);
            log.debug("setCommissionProductCode =" + l_commission.getCommissionProductCode());
            
            //弁済区分をセットする。                            
            l_commission.setPayType(WEB3PayTypeDef.OTHER); 
            log.debug("setPayType =" + l_commission.getPayType());
            
            //原注文注文チャネルをセットする。          
            l_commission.setOrgOrderChannel(l_orderUnitRow.getOrderChanel());
            log.debug("setOrgOrderChannel =" + l_commission.getOrgOrderChannel());
            
            //原注文手数料Noをセットする。           
            l_commission.setOrgCommissionNo(l_orderUnitRow.getCommTblNo());
            log.debug("setOrgCommissionNo = " + l_commission.getOrgCommissionNo());
            
            //原注文手数料No枝番をセットする。           
            l_commission.setOrgCommissionRevNo(l_orderUnitRow.getCommTblSubNo());
            log.debug("setOrgCommissionRevNo = " + l_commission.getOrgCommissionRevNo());              
                        
            //手数料.is指値をセットする。           
            l_commission.setIsLimitPrice(l_blnIsLimitPrice);
            log.debug("setIsLimitPrice = " + l_commission.isLimitPrice());
            
            //手数料.原資産銘柄コード = 先物OP銘柄.get原資産銘柄コード()
            l_commission.setUnderlyingProductCode(
                ((WEB3IfoProductImpl)l_ifoTradedProduct.getProduct()).getUnderlyingProductCode());
            
            //手数料.数量 = 新規建訂正内容.getAfterChangeOriginalQuantity()
            l_commission.setQuantity(l_dblChangeQuantity);
            
            //15.calc訂正時概算建代金
            log.debug("calc訂正時概算建代金");
            log.debug("手数料 = " + l_commission);
            log.debug("指値 = " + (l_request.limitPrice));
            log.debug("補助口座 = " + l_subAccount);
            log.debug("先物OP取引銘柄 = " + l_ifoTradedProduct);
            log.debug("数量 = " + l_dblChangeQuantity);
            log.debug("約定数量 = " + l_dblExecutedQuantity);
            log.debug("合計約定金額 = " + l_dblExecutedAmount);
            log.debug("isSkip金額チェック = false " );            
            
            WEB3IfoEstimateDeliveryAmountCalcResult l_estimateDeliveryAmountCalcResult = new WEB3IfoEstimateDeliveryAmountCalcResult();
            
            l_estimateDeliveryAmountCalcResult = 
                l_orderMgr.calcChangeEstimatePrice(
                    l_commission,
                    l_dblLimitPrice,
                    l_subAccount,
                    l_ifoTradedProduct,
                    l_dblChangeQuantity,
                    l_dblExecutedQuantity,
                    l_dblExecutedAmount,
                    false);  
                    
            log.debug("l_web3IfoOpenContractChangeSpec.getOrderCond() = " + l_web3IfoOpenContractChangeSpec.getOrderCond());
            Trader l_trader = this.getTrader();

            WEB3IfoOpenContractChangeUpdateInterceptor l_openContractChangeUpdateInterceptor = new WEB3IfoOpenContractChangeUpdateInterceptor(l_web3IfoOpenContractChangeSpec);
            
            //16. 
            l_openContractChangeUpdateInterceptor.setCommision(l_commission);
            l_openContractChangeUpdateInterceptor.setEstimateDeliveryAmountCalcResult(l_estimateDeliveryAmountCalcResult);
            l_openContractChangeUpdateInterceptor.setOrderCond(l_request.orderCondType);

            if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
            {
                l_openContractChangeUpdateInterceptor.setOrderCondOperator(l_request.stopOrderCondOperator);
                l_openContractChangeUpdateInterceptor.setStopOrderBasePriceType(WEB3StopBasePriceTypeDef.DEFAULT);
                l_openContractChangeUpdateInterceptor.setStopOrderBasePrice(Double.parseDouble(l_request.stopOrderCondPrice));
            }
            else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
            {
                l_openContractChangeUpdateInterceptor.setOrderCondOperator(l_request.wlimitOrderCondOperator);
                l_openContractChangeUpdateInterceptor.setStopOrderBasePriceType(WEB3StopBasePriceTypeDef.DEFAULT);
                l_openContractChangeUpdateInterceptor.setStopOrderBasePrice(Double.parseDouble(l_request.wlimitOrderCondPrice));
            }
            l_openContractChangeUpdateInterceptor.setWLimitPriceChange(l_web3IfoOpenContractChangeSpec.getWLimitPriceChange());
            
            long l_lngTraderId = 0;
            if (l_trader != null)
            {
                l_lngTraderId = l_trader.getTraderId();
            }
            l_openContractChangeUpdateInterceptor.setTraderId(l_lngTraderId);

			OpLoginSecurityService l_opLoginSec =
				(OpLoginSecurityService) Services.getService(
					OpLoginSecurityService.class);
			String l_strOrderRootDiv =
				l_opLoginSec.getSessionProperty(
					WEB3SessionAttributeDef.ORDER_ROOT_DIV);

			l_openContractChangeUpdateInterceptor.setOrderRootDiv(l_strOrderRootDiv);
                          
            //17validate取引余力
            WEB3TPTradingPowerService l_tradingPowerService = 
                (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
            Object[] l_interceptorObject = new Object[1];
            l_interceptorObject[0] = l_openContractChangeUpdateInterceptor;
            Object[] l_specObject = new Object[1];
            l_specObject[0] = l_web3IfoOpenContractChangeSpec;
            
            WEB3TPTradingPowerResult l_result = 
                l_tradingPowerService.validateTradingPower
                    (l_subAccount,
                    l_interceptorObject,
                    l_specObject,
                    l_orderUnitRow.getOrderType(),
                    false);
            if (!l_result.isResultFlg())
            {
                log.error("取引余力チェックエラー");
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01306,
                    STR_METHOD_NAME);
            }
            
            //18.WEB3GenResponse          
            WEB3FuturesOpenMarginChangeConfirmResponse l_response = 
                (WEB3FuturesOpenMarginChangeConfirmResponse)l_request.createResponse();    
                
            //19.get計算単価           
            double l_dblCalcUnitPrice = l_estimateDeliveryAmountCalcResult.getCalcUnitPrice();                  
            
            //20.get概算受渡代金           
            double l_dblEstimateDeliveryAmount = l_estimateDeliveryAmountCalcResult.getEstimateDeliveryAmount();
            log.debug("get概算受渡代金 = " + l_dblEstimateDeliveryAmount);   
            
            //21.get市場閉局警告指数(部店, String)
            WEB3GentradeBranch l_branch = (WEB3GentradeBranch)l_subAccount.getMainAccount().getBranch();
            String[] l_strTradeCloseSuspension = null;
            l_strTradeCloseSuspension = WEB3GentradeTradingTimeManagement.getTradeCloseSuspension(
                l_branch,
                WEB3FuturesOptionDivDef.FUTURES);
                
            //22.(*2)プロパティセット         
            l_response.estimatedContractPrice = WEB3StringTypeUtility.formatNumber(l_dblEstimateDeliveryAmount);
            l_response.messageSuspension = l_strTradeCloseSuspension;
            l_response.checkPrice = WEB3StringTypeUtility.formatNumber(l_dblCalcUnitPrice);
            l_response.checkDate =  l_datOrderBizDateDay;
            l_response.commission = WEB3StringTypeUtility.formatNumber(l_estimateDeliveryAmountCalcResult.getCommission());
            l_response.commissionConsumptionTax = WEB3StringTypeUtility.formatNumber(l_estimateDeliveryAmountCalcResult.getCommissionTax());
            l_response.commissionCourse = l_estimateDeliveryAmountCalcResult.getCommissionCourse();
            l_response.partExecQuantity = WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getExecutedQuantity());
            //レスポンス.注文有効期限 = 新規建注文内容.訂正失効日
            l_response.expirationDate = l_web3IfoOpenContractChangeSpec.getChangeExpirationDate();

            log.debug("l_response.estimatedContractPrice = " + l_response.estimatedContractPrice);
            log.debug("l_response.messageSuspension = " + l_response.messageSuspension);
            log.debug("l_response.checkPrice = " + l_response.checkPrice);
            log.debug("l_response.checkDate = " + l_response.checkDate);
                    
            log.exiting(STR_METHOD_NAME);        
            return l_response;
             
        }  
        catch (NotFoundException l_ex)
        {            
            log.error("テーブルに該当するデータがありません。",l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);               
            
        }

    }
    
    /**
     * (submit注文)<BR>
     * 株価指数先物の訂正新規建注文を登録する。<BR>
     * <BR>
     * 「（先物訂正新規建サービス）submit注文」参照。<BR>
     * <BR>
     * =============================================== <BR>
     * シーケンス図 : （先物訂正新規建サービス）submit注文２<BR>
     * 具体位置     : 1.13 戻り値の取引余力結果.判定フラグ == false の場合、<BR>
     * 　@　@　@　@　@　@　@　@　@例外をスローする。 <BR>
     * class        : WEB3BusinessLayerException <BR>
     * tag          : BUSINESS_ERROR_01306 <BR>
     * =============================================== <BR>
     * <BR>
     * @@param l_request - 株価指数先物訂正新規建完了リクエスト<BR>
     * @@return WEB3FuturesOpenMarginChangeCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 40A8A4F30397
     */
    protected WEB3FuturesOpenMarginChangeCompleteResponse submitOrder(
        WEB3FuturesOpenMarginChangeCompleteRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "submitOrder(WEB3FuturesOpenMarginChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);      
        
        try
        {
            //1.validate           
            l_request.validate();
            
            //2.取引時間管理からget発注日
            if (l_request.checkDate == null)
            {
                l_request.checkDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            }
            Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate(l_request.checkDate);
            Date l_datOrderBizDateNew = WEB3DateUtility.toDay(l_datOrderBizDate); 
            log.debug("取引時間管理からget発注日: " + l_datOrderBizDate);         
            
            
            //3.IfoOrderImpl     
            TradingModule l_tradingModule = GtlUtils.getTradingModule(ProductTypeEnum.IFO);   
            WEB3FuturesOrderManagerImpl l_orderMgr = 
                (WEB3FuturesOrderManagerImpl) l_tradingModule.getOrderManager();
                
            long l_lngOrderId = Long.parseLong(l_request.id);
            IfoOrderImpl l_ifoOrderImpl = (IfoOrderImpl) l_orderMgr.getOrder(l_lngOrderId); 
            log.debug("l_lngOrderId = " + l_lngOrderId);     
                  
            //4.getOrderUnits           
            OrderUnit[] l_orderUnits = null;
            l_orderUnits = l_ifoOrderImpl.getOrderUnits();
            
            //5.create新規建訂正内容(long, long, double, double, IfoOrderExecutionConditionType, 
            //                      Date, Date, String, String, String, double, double, 
            //                      IfoOrderExecutionConditionType, String, String, boolean)
            double l_dblQuantity = 0D;
            if (WEB3StringTypeUtility.isNumber(l_request.futOrderQuantity))
            {
                l_dblQuantity = Double.parseDouble(l_request.futOrderQuantity);
            }
            
            double l_dblLimitPrice = 0D;
            boolean l_blnIsLimitPrice ;
            if (WEB3OrderPriceDivDef.MARKET_PRICE.equals(l_request.orderPriceDiv))
            {
                //成行
                l_dblLimitPrice = 0D;
                l_blnIsLimitPrice=false;
            }
            else
            {
                //指値
                l_dblLimitPrice = Double.parseDouble(l_request.limitPrice);
                l_blnIsLimitPrice=true;
            }   
            
            long l_lngOrderUnitId = l_orderUnits[0].getOrderUnitId();
            log.debug("l_lngOrderUnitId = " + l_lngOrderUnitId);
                        
            //訂正執行条件：　@先物OPデータアダプタ.get執行条件(リクエストデータ.執行条件) 
            IfoOrderExecutionConditionType l_changeExecCondType = 
                WEB3IfoDataAdapter.getExecutionConditionType(l_request.execCondType);  

            //OP注文マネージャ
            WEB3OptionOrderManagerImpl l_orderManager =
                (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();

            IfoOrderUnitRow l_ifoOrderUnitRow =
                (IfoOrderUnitRow)l_orderUnits[0].getDataSourceObject();

            //先物OP銘柄
            //(*1)先物OPプロダクトマネージャ.getProduct(注文単位.銘柄ID)にて取得
            WEB3IfoProductManagerImpl l_productMgr =
                (WEB3IfoProductManagerImpl)l_tradingModule.getProductManager();

            WEB3IfoProductImpl l_ifoProductImpl =
                (WEB3IfoProductImpl)l_productMgr.getProduct(l_ifoOrderUnitRow.getProductId());

            Market l_market = l_ifoProductImpl.getPrimaryMarket();

            //訂正失効日：
            //リクエストデータ.注文有効期限 != nullの場合、OP注文マネージャ.get注文有効期限(
            //リクエストデータ.注文有効期限,先物OP銘柄(*1).銘柄コード,市場.getMarketCode(),”先物”)の戻り値
            //リクエストデータ.注文有効期限 == nullの場合、get発注日()の戻り値
            Date l_datChangeExpirationDate = null;
            if (l_request.expirationDate == null)
            {
                l_datChangeExpirationDate = l_datOrderBizDateNew;
            }
            else
            {
                l_datChangeExpirationDate = l_orderManager.getExpirationDate(
                    l_request.expirationDate,
                    l_ifoProductImpl.getProductCode(),
                    l_market.getMarketCode(),
                    WEB3FuturesOptionDivDef.FUTURES);
            }
            
            // 発注条件演算子：　@ 
            //　@   リクエストデータ.発注条件区分 == ”逆指値”の場合、リクエストデータ.逆指値用発注条件演算子  
            //　@   リクエストデータ.発注条件区分 == ”W指値”の場合、リクエストデータ.W指値用発注条件演算子 
            // 逆指値基準値：　@ 
            //     リクエストデータ.発注条件区分 == ”逆指値”の場合、リクエストデータ.逆指値用発注条件単価  
            //     リクエストデータ.発注条件区分 == ”W指値”の場合、リクエストデータ.W指値用発注条件単価 
            String l_strOrderCondOperator = null;
            double l_dblStopOrderPrice = 0D;
            if(WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
            {
                l_strOrderCondOperator = l_request.stopOrderCondOperator;
                l_dblStopOrderPrice = Double.parseDouble(l_request.stopOrderCondPrice);
            }
            else if(WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
            {
                l_strOrderCondOperator = l_request.wlimitOrderCondOperator;
                l_dblStopOrderPrice = Double.parseDouble(l_request.wlimitOrderCondPrice);
            }

            //（W指値）訂正指値：　@リクエストデータ.W指値用注文単価
            double l_dblWLimitPrice = 0.0D;
            if (l_request.wLimitPrice != null)
            {
                l_dblWLimitPrice = Double.parseDouble(l_request.wLimitPrice);
            }

            //（W指値）執行条件：　@先物OPデータアダプタ.get執行条件(リクエストデータ.Ｗ指値用執行条件)
            IfoOrderExecutionConditionType l_wLimitExecCondType = 
                WEB3IfoDataAdapter.getExecutionConditionType(l_request.wlimitExecCondType);
            //（W指値）有効状態区分：　@リクエストデータ.Ｗ指値用有効状態区分  
            String l_strWLimitEnableStatusDiv = l_request.wlimitEnableStatusDiv;
            // 訂正後注文期限区分：　@リクエストデータ.注文期限区分
            String l_strExpirationDateType = l_request.expirationDateType;

            //夕場前繰越対象フラグ：
            //先物OPデータアダプタ.get夕場前繰越対象フラグ(リクエストデータ.注文期限区分, 注文単位.部店ID)の戻り値
            boolean l_blnEveningSessionCarryOverFlag = WEB3IfoDataAdapter.getEveningSessionCarryOverFlag(
                l_request.expirationDateType, l_ifoOrderUnitRow.getBranchId());

            WEB3IfoOpenContractChangeSpec l_web3IfoOpenContractChangeSpec = 
                WEB3IfoOpenContractChangeSpec.createIfoOpenContractChangeSpec(
                    l_lngOrderId, 
                    l_lngOrderUnitId,
                    l_dblQuantity,
                    l_dblLimitPrice,
                    l_changeExecCondType, 
                    l_datChangeExpirationDate,
                    l_datOrderBizDateNew,
                    l_request.orderCondType,
                    l_strOrderCondOperator,
                    WEB3StopBasePriceTypeDef.DEFAULT,
                    l_dblStopOrderPrice,
                    l_dblWLimitPrice,
                    l_wLimitExecCondType,
                    l_strWLimitEnableStatusDiv,
                    l_strExpirationDateType,
                    l_blnEveningSessionCarryOverFlag);
                              
            //6.get補助口座( )       
            WEB3GentradeSubAccount l_subAccount  = (WEB3GentradeSubAccount) this.getSubAccount();
                
            //7.validate新規建訂正注文
            log.debug("l_subAccount = " + l_subAccount);
            log.debug("l_web3IfoOpenContractChangeSpec = " + l_web3IfoOpenContractChangeSpec);
            OrderValidationResult l_orderValidationResult = l_orderMgr.validateFuturesChangeOrder(l_subAccount, l_web3IfoOpenContractChangeSpec);
            if(l_orderValidationResult.getProcessingResult().isFailedResult())
            {
                log.debug("ProcessingResult() = " + l_orderValidationResult.getProcessingResult());
                throw new WEB3BusinessLayerException(
                    l_orderValidationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME);              
            }                   
                        
            //8.getDataSourceObject          
            IfoOrderUnitRow l_orderUnitRow =
                (IfoOrderUnitRow)l_orderUnits[0].getDataSourceObject();
            log.debug("l_orderUnitRow = " + l_orderUnitRow);
            
            //9.getTradedProduct       
            WEB3IfoTradedProductImpl l_ifoTradedProduct =
                (WEB3IfoTradedProductImpl)l_productMgr.getTradedProduct(l_orderUnitRow.getProductId(), l_orderUnitRow.getMarketId());
            log.debug("l_ifoTradedProduct = " + l_ifoTradedProduct);     
            
            //10.訂正数量を取得する。           
            double l_dblChangeQuantity  = 
                l_web3IfoOpenContractChangeSpec.getAfterChangeOriginalQuantity();
            log.debug("getAfterChangeOriginalQuantity = " + l_dblChangeQuantity);
            
            //11.合計約定数量を取得する(getExecutedQuantity)     
            double l_dblExecutedQuantity = l_orderUnits[0].getExecutedQuantity();
            log.debug("getExecutedQuantity = " + l_dblExecutedQuantity);
            
            //12.合計約定金額を取得する(getExecutedAmount)            
            double l_dblExecutedAmount = l_orderUnits[0].getExecutedAmount();
            log.debug("getExecutedAmount = " + l_dblExecutedAmount);
            
            if (Double.isNaN(l_dblExecutedQuantity))
            {
                l_dblExecutedQuantity = 0D;            
            }
            
            if (Double.isNaN(l_dblExecutedAmount))
            {
                l_dblExecutedAmount = 0D;     
            }
            
            //13.手数料           
            WEB3GentradeCommission l_commission = new WEB3GentradeCommission();
            log.debug("l_commission = " + l_commission);
            
            //14.(*1)プロパティセット 
            //注文チャネルをセットする。            
            l_commission.setOrderChannel(this.getLoginChannel());
            
            //証券会社コードをセットする。           
            l_commission.setInstitutionCode(l_subAccount.getInstitution().getInstitutionCode());
            
            //部店IDをセットする。                   
            l_commission.setBranchId(l_subAccount.getMainAccount().getBranch().getBranchId());
            log.debug("部店IDをセットする>> " + l_commission.getBranchId());
            
            //発注日をセットする。
            Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            l_commission.setOrderBizDate(new Timestamp(l_datBizDate.getTime()));
            
            //取引コード（SONAR）をセットする。           
            l_commission.setSonarTradedCode(WEB3TransactionTypeSONARDef.OPEN_CONTRACT);
            log.debug("取引コードをセットする>> " + l_commission.getSonarTradedCode());
            
            //手数料商品コードをセットする。                    
            l_commission.setCommissionProductCode(WEB3CommisionProductCodeDef.INDEX_FUTURES);
            log.debug("setCommissionProductCode =" + l_commission.getCommissionProductCode());
            
            //弁済区分をセットする。                            
            l_commission.setPayType(WEB3PayTypeDef.OTHER); 
            log.debug("setPayType =" + l_commission.getPayType());
            
            //原注文注文チャネルをセットする。          
            l_commission.setOrgOrderChannel(l_orderUnitRow.getOrderChanel());
            log.debug("setOrgOrderChannel =" + l_commission.getOrgOrderChannel());
            
            //原注文手数料Noをセットする。           
            l_commission.setOrgCommissionNo(l_orderUnitRow.getCommTblNo());
            log.debug("setOrgCommissionNo = " + l_commission.getOrgCommissionNo());
            
            //原注文手数料No枝番をセットする。           
            l_commission.setOrgCommissionRevNo(l_orderUnitRow.getCommTblSubNo());
            log.debug("setOrgCommissionRevNo = " + l_commission.getOrgCommissionRevNo());              
                        
            //手数料.is指値をセットする。
            l_commission.setIsLimitPrice(l_blnIsLimitPrice);
            log.debug("setIsLimitPrice = " + l_commission.isLimitPrice());
            
            //手数料.原資産銘柄コード = 先物OP銘柄.get原資産銘柄コード()
            l_commission.setUnderlyingProductCode(
                ((WEB3IfoProductImpl)l_ifoTradedProduct.getProduct()).getUnderlyingProductCode());
            
            //手数料.数量 = 新規建訂正内容.getAfterChangeOriginalQuantity()
            l_commission.setQuantity(l_dblChangeQuantity);
            
            //15.calc訂正時概算建代金
            log.debug("手数料 = " + l_commission);
            log.debug("確認時単価 = " + (l_request.checkPrice));
            log.debug("補助口座 = " + l_subAccount);
            log.debug("先物OP取引銘柄 = " + l_ifoTradedProduct);
            log.debug("数量 = " + l_dblChangeQuantity);
            log.debug("約定数量 = " + l_dblExecutedQuantity);
            log.debug("合計約定金額 = " + l_dblExecutedAmount);          
            
            WEB3IfoEstimateDeliveryAmountCalcResult l_estimateDeliveryAmountCalcResult = new WEB3IfoEstimateDeliveryAmountCalcResult();
            log.debug("l_web3IfoOpenContractChangeSpec.getOrderCond() = " + l_web3IfoOpenContractChangeSpec.getOrderCond());                    

            //訂正時概算建代金を計算する。
            // [calc概算建代金()に指定する引数]
            //   手数料：　@手数料オブジェクト
            //   指値：　@
            //     リクエストデータ.確認時単価!=nullの場合、リクエストデータ.確認時単価
            //     リクエストデータ.確認時単価==nullの場合、リクエストデータ.注文単価(*1)
            //    （*1　@リクエストデータ.注文単価==nullの場合はゼロをセット。）
            //   補助口座：　@補助口座オブジェクト
            //   先物OP取引銘柄：　@先物OP取引銘柄オブジェクト
            //   数量： 新規建訂正内容.getQuantity() 
            //   約定数量： getExecutedQuantity()の戻り値
            //   合計約定金額： getExecuterAmount()の戻り値
            //   isSkip金額チェック：　@false
            double l_dblCheckPrice = 0D;
            if (l_request.checkPrice != null)
            {
                l_dblCheckPrice = Double.parseDouble(l_request.checkPrice);
            }
            else
            {
                l_dblCheckPrice = l_dblLimitPrice;
            }

            l_estimateDeliveryAmountCalcResult = 
                l_orderMgr.calcChangeEstimatePrice(
                    l_commission,
                    l_dblCheckPrice,
                    l_subAccount,
                    l_ifoTradedProduct,
                    l_dblChangeQuantity,
                    l_dblExecutedQuantity,
                    l_dblExecutedAmount,
                    false);  

            //get代理入力者( )
            Trader l_trader = this.getTrader();                        


            //16.先物OP新規建訂正更新インタセプタ(新規建訂正内容)
            WEB3IfoOpenContractChangeUpdateInterceptor l_interceptor = 
                new WEB3IfoOpenContractChangeUpdateInterceptor(l_web3IfoOpenContractChangeSpec);
            
             //17. (*2) プロパティセット
            l_interceptor.setCommision(l_commission);
            l_interceptor.setEstimateDeliveryAmountCalcResult(l_estimateDeliveryAmountCalcResult);
            l_interceptor.setOrderCond(l_request.orderCondType);           

            if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
            {
                l_interceptor.setOrderCondOperator(l_request.stopOrderCondOperator);
                l_interceptor.setStopOrderBasePrice(Double.parseDouble(l_request.stopOrderCondPrice));
                l_interceptor.setStopOrderBasePriceType(WEB3StopBasePriceTypeDef.DEFAULT);
                l_interceptor.setWLimitPriceChange(0);
            }
            else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
            {
                l_interceptor.setOrderCondOperator(l_request.wlimitOrderCondOperator);
                l_interceptor.setStopOrderBasePrice(Double.parseDouble(l_request.wlimitOrderCondPrice));
                l_interceptor.setStopOrderBasePriceType(WEB3StopBasePriceTypeDef.DEFAULT);
                l_interceptor.setWLimitPriceChange(l_dblWLimitPrice);                 
            }
            
            long l_lngTraderId = 0;
            if (l_trader != null)
            {
                l_lngTraderId = l_trader.getTraderId();
            }
            l_interceptor.setTraderId(l_lngTraderId);    
            
			OpLoginSecurityService l_opLoginSec =
				(OpLoginSecurityService) Services.getService(
					OpLoginSecurityService.class);
			String l_strOrderRootDiv =
				l_opLoginSec.getSessionProperty(
					WEB3SessionAttributeDef.ORDER_ROOT_DIV);

			l_interceptor.setOrderRootDiv(l_strOrderRootDiv);

            WEB3TPTradingPowerService l_tradingPowerService = 
                (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
            Object[] l_interceptorObject = new Object[1];
            l_interceptorObject[0] = l_interceptor;
            Object[] l_specObject = new Object[1];
            l_specObject[0] = l_web3IfoOpenContractChangeSpec;
            
            WEB3TPTradingPowerResult l_result = 
                l_tradingPowerService.validateTradingPower
                (l_subAccount,
                l_interceptorObject,
                l_specObject,
                l_orderUnitRow.getOrderType(),
                false);
            if (!l_result.isResultFlg())
            {
                log.error("取引余力チェックエラー");
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01306,
                    STR_METHOD_NAME);
            }
               
            //18.setThreadLocalPersistenceEventInterceptor            
            l_orderMgr.setThreadLocalPersistenceEventInterceptor(l_interceptor);
            
            //19.submitChangeOrder(補助口座 : SubAccount, 新規建訂正内容 : ChangeOrderSpec, 取引パスワード : String, isSkip発注審査(=true) : boolean)
            OrderSubmissionResult l_orderSubmissionResult = l_orderMgr.submitChangeOrder(
                l_subAccount,
                l_web3IfoOpenContractChangeSpec,
                l_request.password,
                true);
                
            if (l_orderSubmissionResult.getProcessingResult().isFailedResult())
            {
                log.debug("ProcessingResult() = " + l_orderSubmissionResult.getProcessingResult());
                throw new WEB3BusinessLayerException(
                    l_orderSubmissionResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME); 
            }

            //is予約注文確認要(IfoOrderUnit)
            IfoOrderUnit l_ifoOrderUnit = (IfoOrderUnit)l_orderUnits[0];
            boolean l_blnIsReserveOrderExist = l_orderMgr.isReserveOrderExist(l_ifoOrderUnit);

            //予約注文確認要（is予約注文確認要() == true）の場合
            List l_lisGetOpenReserveIfoOrderUnits = null;
            if (l_blnIsReserveOrderExist)
            {
                //get有効予約注文単位一覧(親注文の注文ID : long)
                WEB3ToSuccReservationIfoOrderUpdateService l_ifoOrderUpdateService =
                    (WEB3ToSuccReservationIfoOrderUpdateService)Services.getService(
                        WEB3ToSuccReservationIfoOrderUpdateService.class);
                l_lisGetOpenReserveIfoOrderUnits =
                    l_ifoOrderUpdateService.getOpenReserveIfoOrderUnits(l_ifoOrderUnit.getOrderId());
            }

            //20.WEB3GenResponse(WEB3GenRequest)
            WEB3FuturesOpenMarginChangeCompleteResponse l_response = (WEB3FuturesOpenMarginChangeCompleteResponse) l_request.createResponse();
            
            //21.(*3) プロパティセット 
            l_response.lastUpdatedTimestamp = GtlUtils.getSystemTimestamp();
            l_response.orderActionId = l_request.id;
            if (l_lisGetOpenReserveIfoOrderUnits != null)
            {
                l_response.succSettingFlag = true;
            }
            else
            {
                l_response.succSettingFlag = false;
            }

            log.debug("l_response.lastUpdatedTimestamp = " + l_response.lastUpdatedTimestamp);
            log.debug("l_response.orderActionId = " + l_response.orderActionId);                     

            log.exiting(STR_METHOD_NAME);
            return l_response;           
        }
        catch (NotFoundException l_ex)
        {            
            log.error("テーブルに該当するデータがありません。",l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);               
        }
    }
}
@
