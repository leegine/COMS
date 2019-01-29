head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionChangeOpenContractServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : OP訂正新規建サービスImpl(WEB3OptionChangeOpenContractServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/17 李頴淵 (中訊) 新規作成
              001: 2004/07/22 王暁傑 (中訊) WEB3ExecutionConditionDefでWEB3IfoExecCondTypeDefを差し替える
              002: 2004/07/22 王暁傑 (中訊) WEB3OrderingConditionDefでWEB3IfoOrderCondTypeDefを差し替える
              003: 2004/07/23 王暁傑 (中訊) WEB3TransactionTypeSONARDefでWEB3IfoTransactionTypeDefを差し替える
              004: 2004/07/26 王暁傑 (中訊) 対応バッグ WEB3_IFO_UT-000018
              005: 2004/07/30 王暁傑 (中訊) 対応バッグ WEB3_IFO_UT-000074
              006: 2004/07/30 王暁傑 (中訊) 対応バッグ WEB3_IFO_UT-000078、79
              007: 2004/07/30 王暁傑 (中訊) 対応バッグ WEB3_IFO_UT-000080、100              
              008: 2004/08/09 王暁傑 (Sinocom) 対応名称:【WEB3-XBIFO-A-CD-0082】
              009: 2004/08/13 呉艶飛　@(中訊) STBUG(IFO_ST-000079)を対応
              010: 2004/08/14 王暁傑 対応 【株価指数オプション】ソースコードチェック指摘事項(JP)20040802 No020
              011: 2004/08/15 呉艶飛　@(中訊) STBUG(IFO_ST-000083)を対応
              012: 2006/7/13 徐宏偉 (中訊) 仕様変更　@モデル469、474、478
              013: 2006/08/10 肖志偉 (中訊) 仕様変更 モデル545
              014: 2006/08/30 唐性峰  (中訊) モデルNo.551対応
              015: 2006/08/31 唐性峰  (中訊) モデルNo.552対応
              016: 2006/11/28 徐大方  (中訊) モデルNo.573対応
              017: 2006/11/30 徐大方  (中訊) モデルNo.584,585対応
              018: 2006/12/06 徐大方  (中訊) モデルNo.592,594対応
Revesion History : 2007/06/11 孟亜南 (中訊) 仕様変更モデルNo.672
Revesion History : 2007/11/20 何文敏 (中訊) 仕様変更モデルNo.804, No.815, No.819
Revesion History : 2008/04/14 張騰宇(中訊) モデル856 869
*/

package webbroker3.ifo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.ChangeOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3PayTypeDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ifo.WEB3IfoDataAdapter;
import webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult;
import webbroker3.ifo.WEB3IfoOpenContractChangeSpec;
import webbroker3.ifo.WEB3IfoOpenContractChangeUpdateInterceptor;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoProductManagerImpl;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.WEB3OptionClientRequestService;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.define.WEB3IfoWLimitEnableStatusDivDef;
import webbroker3.ifo.message.WEB3OptionsOpenMarginChangeCompleteRequest;
import webbroker3.ifo.message.WEB3OptionsOpenMarginChangeCompleteResponse;
import webbroker3.ifo.message.WEB3OptionsOpenMarginChangeConfirmRequest;
import webbroker3.ifo.message.WEB3OptionsOpenMarginChangeConfirmResponse;
import webbroker3.ifo.service.delegate.WEB3OptionChangeOpenContractService;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.triggerorder.base.service.delegate.WEB3ToSuccReservationIfoOrderUpdateService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (OP訂正新規建サービスImpl)<BR>
 * <BR>
 * 株価指数オプション訂正新規建サービス実装クラス<BR>
 * 
 * @@author 李頴淵
 * @@version 1.0
 */
public class WEB3OptionChangeOpenContractServiceImpl extends WEB3OptionClientRequestService implements WEB3OptionChangeOpenContractService 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3OptionChangeOpenContractServiceImpl.class);
    
    /**
     * @@roseuid 40C0BF99037A
     */
    public WEB3OptionChangeOpenContractServiceImpl() 
    {
     
    }
    
    /**
     * 株価指数オプション訂正新規建サービス処理を実施する。<BR>
     * <BR>
     * リクエストデータの型により、validate注文()または、<BR>
     * submit注文()メソッドをコールする。<BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4056BC690359
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
            getClass().getName() + ".execute(WEB3GenRequest)";

        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        
        if (l_request instanceof WEB3OptionsOpenMarginChangeConfirmRequest)
        {
            //リクエストデータの具象データ型が
            //「株価指数オプション訂正新規建確認リクエスト」の場合
            log.debug("validateOrderメソッドを実行する");
                l_response = validateOrder(
                    (WEB3OptionsOpenMarginChangeConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3OptionsOpenMarginChangeCompleteRequest)
        {
            //リクエストデータの具象データ型が
            //「株価指数オプション当日注文約定詳細リクエスト」の場合
            log.debug("submitOrderメソッドを実行する");
            l_response = submitOrder(
                (WEB3OptionsOpenMarginChangeCompleteRequest)l_request);
        }
        else
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
    
    /**
     * (validate注文)
     * 株価指数オプションの訂正新規建発注審査を行う。<BR>
     * <BR>
     * 「（OP訂正新規建サービス）validate注文」参照。<BR>
     * <BR>
     * =============================================== <BR>
     * シーケンス図 : （OP訂正新規建サービス）validate注文２ <BR>
     * 具体位置     : 1.15 戻り値の取引余力結果.判定フラグ == false の場合、<BR>
     * 　@　@　@　@　@　@　@　@　@例外をスローする。 <BR>
     * class        : WEB3BusinessLayerException <BR>
     * tag          : BUSINESS_ERROR_01306 <BR>
     * =============================================== <BR>
     * <BR>
     * @@param l_request - 株価指数オプション訂正新規建確認リクエスト
     * @@return webbroker3.ifo.message.WEB3OptionsOpenMarginChangeConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 4056BC930116
     */
    protected WEB3OptionsOpenMarginChangeConfirmResponse validateOrder(WEB3OptionsOpenMarginChangeConfirmRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = getClass().getName() + ".validateOrder(WEB3OptionsCloseMarginChangeConfirmRequest)";
        
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class); 
        WEB3OptionOrderManagerImpl l_orderManagerImpl = 
            (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
        try
        {
            //1.validate
            log.debug("l_request.validate()");
            l_request.validate();
            
            //2.取引時間管理からget発注日
            log.debug("取引時間管理からget発注日");
            Date l_dateOrderBizDate = null;
            l_dateOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            
            //3.IfoOrderImpl
            log.debug("IfoOrderImpl");
            log.debug("l_request.id =" + l_request.id);

            OrderUnit l_orderUnit = l_orderManagerImpl.getOrderUnits(Long.parseLong(l_request.id))[0];
            
            //4.create新規建訂正内容(long, long, double, double, 
            //IfoOrderExecutionConditionType, Date, Date, String, String, String, 
            //double, double, IfoOrderExecutionConditionType, String, String)
            WEB3IfoOpenContractChangeSpec l_ifoOpenContractChangeSpec = null;
            
            log.debug("新規建訂正内容");
            
            // 注文ID：　@注文単位.getOrderId() 
            long l_lngOrderId = l_orderUnit.getOrderId();
            log.debug("getOrderUnitId()"); 
            
            // 注文単位ＩＤ：　@注文単位.getOrderUnitId() 
            long l_lngOrderUnitId = l_orderUnit.getOrderUnitId();
            log.debug("l_lngOrderUnitId = " + l_lngOrderUnitId);
          
            //訂正後数量：　@リクエストデータ.数量 
            double l_dblOpOrderQuantity = 0.0D;
            if (l_request.opOrderQuantity != null)
            {
                l_dblOpOrderQuantity = Double.parseDouble(l_request.opOrderQuantity);
            }
            
            // 訂正後指値：　@リクエストデータ.注文単価 
            double l_dblLimitPrice = 0.0D;
            if (l_request.limitPrice != null)
            {
                l_dblLimitPrice = Double.parseDouble(l_request.limitPrice);
            }
            
            // 訂正執行条件：　@先物OPデータアダプタ.get執行条件(リクエストデータ.執行条件) 
            IfoOrderExecutionConditionType l_changeExecCondType = 
            	WEB3IfoDataAdapter.getExecutionConditionType(l_request.execCondType);

            // OP注文マネージャ
            WEB3OptionOrderManagerImpl l_optionOrderManagerImpl = 
                (WEB3OptionOrderManagerImpl) l_finApp.getTradingModule(
                    ProductTypeEnum.IFO).getOrderManager();

            // 訂正失効日：
            // リクエストデータ.注文有効期限 != nullの場合、OP注文マネージャ.get注文有
            // 効期限(リクエストデータ.注文有効期限,先物OP銘柄(*1).銘柄コード,市場
            // .getMarketCode(),”オプション”)の戻り値
            // 　@リクエストデータ.注文有効期限 == nullの場合、get発注日()の戻り値
            Date l_datchangeExpirationDate = null;
            IfoOrderUnit l_ifoOrderUnit = (IfoOrderUnit)l_orderUnit;
            IfoOrderUnitRow l_ifoOrderUnitRow = (IfoOrderUnitRow)l_ifoOrderUnit.getDataSourceObject();
            if (l_request.expirationDate != null)
            {
                // 先物OPプロダクトマネージャ.getProduct(注文単位.銘柄ID)にて取得
                TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
                WEB3IfoProductManagerImpl l_productMgr =
                    (WEB3IfoProductManagerImpl)l_tradingModule.getProductManager();
                Product l_product = null;
                try
                {
                    l_product = l_productMgr.getProduct(l_ifoOrderUnitRow.getProductId());
                }
                catch (NotFoundException l_ex)
                {
                    log.error("テーブルに該当するデータがありません。", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                WEB3IfoProductImpl l_ifoProductImpl = (WEB3IfoProductImpl)l_product;
                // 先物OP銘柄(*1).銘柄コード
                String l_strProductCode = l_ifoProductImpl.getProductCode();
                // 市場.getMarketCode()
                Market l_market = l_ifoProductImpl.getPrimaryMarket(); 
                String l_strMarketCode = l_market.getMarketCode();

                l_datchangeExpirationDate = l_optionOrderManagerImpl.getExpirationDate(
                    l_request.expirationDate,
                    l_strProductCode,
                    l_strMarketCode,
                    WEB3FuturesOptionDivDef.OPTION);
            }
            else
            {
                log.debug("l_request.expirationDate <> null");
                l_datchangeExpirationDate = l_dateOrderBizDate;
            }

            // 発注条件：　@リクエストデータ.発注条件区分 
            String l_strOrderCondType = l_request.orderCondType;
            
            // 発注条件演算子：　@ 
            // 　@リクエストデータ.発注条件区分 == ”逆指値”の場合、リクエストデータ.逆指値用発注条件演算子 
            // 　@リクエストデータ.発注条件区分 == ”W指値”の場合、リクエストデータ.W指値用発注条件演算子
            String l_strOrderCondOperator = null;
            if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
            {
            	l_strOrderCondOperator = l_request.stopOrderCondOperator;
            }
            else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
            {
            	l_strOrderCondOperator = l_request.wlimitOrderCondOperator;
            }
           
            //逆指値基準値タイプ：　@ 
            //リクエストデータ.発注条件区分 == ”逆指値”の場合、リクエストデータ.逆指値用プレミアム／原資産価格 
            //リクエストデータ.発注条件区分 == ”W指値”の場合、リクエストデータ.W指値用プレミアム／原資産価格
            String l_strStopPriceType = null;
            if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
            {               
                l_strStopPriceType = l_request.stopPremium_underlyingAssets;      
            }
            else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
            {

                    l_strStopPriceType = l_request.wlimitPremium_underlyingAssets;
            }
            
            // 逆指値基準値：　@ 
            // 　@リクエストデータ.発注条件区分 == ”逆指値”の場合、リクエストデータ.逆指値用発注条件単価 
            // 　@リクエストデータ.発注条件区分 == ”W指値”の場合、リクエストデータ.W指値用発注条件単価 
            double l_dblStopOrderPrice = 0.0D;
            if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
            {
                if (l_request.stopOrderCondPrice != null)
                {
                    l_dblStopOrderPrice = Double.parseDouble(l_request.stopOrderCondPrice);
                }            	
            }
            else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
            {
                if (l_request.wlimitOrderCondPrice != null)
                {
                    l_dblStopOrderPrice = Double.parseDouble(l_request.wlimitOrderCondPrice);
                } 
            }
            
            // （W指値）訂正指値：　@リクエストデータ.W指値用注文単価 
            double l_dblWLimitOrderChange = 0.0D;
            if (l_request.wLimitPrice != null)
            {
                l_dblWLimitOrderChange = Double.parseDouble(l_request.wLimitPrice);
            }
            
            // （W指値）執行条件：　@先物OPデータアダプタ.get執行条件(リクエストデータ.Ｗ指値用執行条件)   
            IfoOrderExecutionConditionType l_wlimitExecCondType = 
            	WEB3IfoDataAdapter.getExecutionConditionType(l_request.wlimitExecCondType);
            
            // （W指値）有効状態区分：　@リクエストデータ.Ｗ指値用有効状態区分 
            String l_strWlimitEnableStatusDiv = l_request.wlimitEnableStatusDiv;
            
            // 訂正後注文期限区分：　@リクエストデータ.注文期限区分
            String l_strExpirationDateType = l_request.expirationDateType;
            
            //夕場前繰越対象フラグ：　@先物OPデータアダプタ.get夕場前繰越対象フラグ
            //(リクエストデータ.注文期限区分, 注文単位.部店ID)の戻り値
            boolean l_blnEveningSessionOrderFlag = WEB3IfoDataAdapter.getEveningSessionCarryOverFlag(
                l_strExpirationDateType, l_orderUnit.getBranchId());

            l_ifoOpenContractChangeSpec = 
            	WEB3IfoOpenContractChangeSpec.createIfoOpenContractChangeSpec(
		    		l_lngOrderId, 
		    		l_lngOrderUnitId, 
		    		l_dblOpOrderQuantity, 
		    		l_dblLimitPrice,
		    		l_changeExecCondType,
		    		l_datchangeExpirationDate,
                    l_dateOrderBizDate,
		    		l_strOrderCondType,
		    		l_strOrderCondOperator,
		    		l_strStopPriceType,
		    		l_dblStopOrderPrice,
		    		l_dblWLimitOrderChange,
		    		l_wlimitExecCondType,
                    l_strWlimitEnableStatusDiv,      		
		    		l_strExpirationDateType,
                    l_blnEveningSessionOrderFlag);

            //5.get補助口座( )
            log.debug("get補助口座( )");
            SubAccount l_subAccount = this.getSubAccount();
            WEB3GentradeSubAccount l_GentradeSubAccount = null;
            l_GentradeSubAccount = (WEB3GentradeSubAccount)l_subAccount;
                
            //6.validate新規建訂正注文
            log.debug("l_subAccount = " + l_subAccount);
            log.debug("l_WEB3IfoOpenContractChangeSpec = " + l_ifoOpenContractChangeSpec);
            log.debug("validate新規建訂正注文");

            OrderValidationResult l_result = null;
            l_result = l_orderManagerImpl.validateChangeOrder(l_GentradeSubAccount,l_ifoOpenContractChangeSpec);
            
            if (l_result.getProcessingResult().isFailedResult())
            {
                log.debug("ProcessingResult() = " + l_result.getProcessingResult());
                throw new WEB3BusinessLayerException(
                    l_result.getProcessingResult().getErrorInfo(),
                    STR_METHOD_NAME);                
            }
			            
			//7.1（OP訂正新規建サービス）validate注文２ （参照）
            //7.2.getTradedProduct
            log.debug("getTradedProduct");
            TradedProduct l_tradedProduct = null;
            l_tradedProduct = l_orderUnit.getTradedProduct();
                
            //7.3.getDataSourceObject
            log.debug("getDataSourceObject");
            IfoOrderUnitRow l_orderUnitRow = null;
            l_orderUnitRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
            
            //7.4.getAfterChangeOriginalQuantity
            log.debug("getAfterChangeOriginalQuantity");
            double l_afterChangeOriginalQuantity = 0;
            l_afterChangeOriginalQuantity = l_ifoOpenContractChangeSpec.getAfterChangeOriginalQuantity();

            if (Double.isNaN(l_afterChangeOriginalQuantity))
            {
                l_afterChangeOriginalQuantity = 0D;
            }

            //7.5.getSide
            log.debug("getSide");
            SideEnum l_side = null;
            l_side = l_orderUnit.getSide();
                
            //7.6.getExecutedQuantity
            log.debug("getExecutedQuantity");
            double l_dblExecutedQuantity = 0;
            l_dblExecutedQuantity = l_orderUnit.getExecutedQuantity();

            if (Double.isNaN(l_dblExecutedQuantity))
            {
                l_dblExecutedQuantity = 0;        
            }
                            
            //7.7.getExecutedAmount
            log.debug("getExecutedAmount");
            double l_dblExecutedAmount = 0;
            l_dblExecutedAmount = l_orderUnit.getExecutedAmount();
                        
            if (Double.isNaN(l_dblExecutedAmount))
            {
                l_dblExecutedAmount = 0;        
            }
            
            //7.8.手数料
            //手数料オブジェクトを生成する。
            log.debug("手数料");
            WEB3GentradeCommission l_gentradeCommission = null;
            l_gentradeCommission = new WEB3GentradeCommission();
                
            //7.9.(*1)プロパティセット 
            //注文チャネルをセットする。
            log.debug("注文チャネルをセットする。");
            l_gentradeCommission.setOrderChannel(this.getLoginChannel());
            
            //証券会社IDをセットする。
            log.debug("証券会社IDをセットする。");
            l_gentradeCommission.setInstitutionCode(l_subAccount.getInstitution().getInstitutionCode());
            
            //部店IDをセットする。
            log.debug("部店IDをセットする。");
            log.debug("BranchId() =" + l_subAccount.getMainAccount().getBranch().getBranchId());
            l_gentradeCommission.setBranchId(l_subAccount.getMainAccount().getBranch().getBranchId());
            
            //発注日をセットする。
            log.debug("発注日をセットする。");
            l_gentradeCommission.setOrderBizDate(new Timestamp(l_dateOrderBizDate.getTime()));
            
            //取引コード（SONAR）をセットする。
            log.debug("取引コードをセットする。");
            l_gentradeCommission.setSonarTradedCode(WEB3TransactionTypeSONARDef.OPEN_CONTRACT);            
          
            //手数料商品コードをセットする。
            log.debug("手数料商品コードをセットする。");
            log.debug("CommissionProductCode =" + WEB3CommisionProductCodeDef.INDEX_OP);
            l_gentradeCommission.setCommissionProductCode(WEB3CommisionProductCodeDef.INDEX_OP);
            
            //原注文注文チャネルをセットする。
            log.debug("原注文注文チャネルをセットする。");
            l_gentradeCommission.setOrgOrderChannel(l_orderUnitRow.getOrderChanel());
            
            //原注文手数料Noをセットする。
            log.debug("原注文手数料Noをセットする。");
            l_gentradeCommission.setOrgCommissionNo(l_orderUnitRow.getCommTblNo());
            
            //原注文手数料No枝番をセットする。
            log.debug("原注文手数料No枝番をセットする。");
            l_gentradeCommission.setOrgCommissionRevNo(l_orderUnitRow.getCommTblSubNo());
            
            //弁済区分をセットする。                 
            l_gentradeCommission.setPayType(WEB3PayTypeDef.OTHER);   

            boolean l_blnIsLimitPrice = false;
            if (WEB3OrderPriceDivDef.MARKET_PRICE.equals(l_request.orderPriceDiv))
            {
                //成行
                l_blnIsLimitPrice = false;
            }
            else
            {
                //指値
                l_blnIsLimitPrice = true;
            }
            l_gentradeCommission.setIsLimitPrice(l_blnIsLimitPrice);
            
            //手数料.原資産銘柄コード = 先物OP銘柄.get原資産銘柄コード()
            l_gentradeCommission.setUnderlyingProductCode(
                ((WEB3IfoProductImpl)l_tradedProduct.getProduct()).getUnderlyingProductCode());
            
            //手数料.数量 = 新規建訂正内容.getAfterChangeOriginalQuantity()
            l_gentradeCommission.setQuantity(l_afterChangeOriginalQuantity);
            
            //7.10.calc訂正時概算受渡代金
            log.debug("calc訂正時概算受渡代金1");
            log.debug("l_gentradeCommission = " + l_gentradeCommission);
            log.debug("l_subAccount = " + l_subAccount);
            log.debug("l_tradedProduct = " + l_tradedProduct);
            log.debug("l_WEB3IfoOpenContractChangeSpec = " + l_ifoOpenContractChangeSpec.getAfterChangeOriginalQuantity());
            log.debug("l_side = " + l_side);
            log.debug("l_dblExecutedQuantity = " + l_dblExecutedQuantity);
            log.debug("l_dblExecutedAmount = " + l_dblExecutedAmount);
                        
            WEB3IfoTradedProductImpl l_ifoTradedProductImpl = new WEB3IfoTradedProductImpl(l_tradedProduct.getTradedProductId());
            WEB3IfoEstimateDeliveryAmountCalcResult l_estimateDeliveryAmountCalcResult = null;
            WEB3IfoEstimateDeliveryAmountCalcResult l_wEstimateDeliveryAmountCalcResult = null;
            l_estimateDeliveryAmountCalcResult = 
                l_orderManagerImpl.calcChangeEstimateDeliveryAmount(
                    l_gentradeCommission,l_dblLimitPrice,
                    l_GentradeSubAccount,
                    l_ifoTradedProductImpl,
                    l_ifoOpenContractChangeSpec.getAfterChangeOriginalQuantity(),
                    l_side,
                    false,
                    l_dblExecutedQuantity,
                    l_dblExecutedAmount,
                    false);
            
			//7.11.(*2)プロパティセット
			//買建、かつ、リミット注文が有効なW指値注文の場合
			//(注文単位.getSide() == "BUY" &&　@リクエストデータ.Ｗ指値用有効状態区分 == "リミット注文有効" 
            //＆＆ 新規建訂正注文内容.発注条件() == "W指値")
			//処理を行う。
            WEB3GentradeCommission l_wGentradeCommission = new WEB3GentradeCommission();
            if (SideEnum.BUY.equals(l_side) 
                && WEB3IfoWLimitEnableStatusDivDef.LIMIT_ENABLE.equals(
            		l_request.wlimitEnableStatusDiv)
                && WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(
            		l_ifoOpenContractChangeSpec.getOrderCond()))
            {
				//7.11.1.[setIs指値()に指定する引数] 
				// is指値：　@新規建訂正内容.(W指値)訂正指値 != 0の場合、true。以外、false。
                if (l_ifoOpenContractChangeSpec.getWLimitPriceChange() != 0)
                {
                    l_wGentradeCommission.setIsLimitPrice(true);
                }
                else
                {
                    l_wGentradeCommission.setIsLimitPrice(false);
                }

                l_wGentradeCommission.setOrderChannel(this.getLoginChannel());

                //証券会社IDをセットする。
                log.debug("証券会社IDをセットする。");
                l_wGentradeCommission.setInstitutionCode(l_subAccount.getInstitution().getInstitutionCode());

                //部店IDをセットする。
                log.debug("部店IDをセットする。");
                log.debug("BranchId() =" + l_subAccount.getMainAccount().getBranch().getBranchId());
                l_wGentradeCommission.setBranchId(l_subAccount.getMainAccount().getBranch().getBranchId());

                //発注日をセットする。
                log.debug("発注日をセットする。");
                l_wGentradeCommission.setOrderBizDate(new Timestamp(l_dateOrderBizDate.getTime()));

                //取引コード（SONAR）をセットする。
                log.debug("取引コードをセットする。");
                l_wGentradeCommission.setSonarTradedCode(WEB3TransactionTypeSONARDef.OPEN_CONTRACT);            

                //手数料商品コードをセットする。
                log.debug("手数料商品コードをセットする。");
                log.debug("CommissionProductCode =" + WEB3CommisionProductCodeDef.INDEX_OP);
                l_wGentradeCommission.setCommissionProductCode(WEB3CommisionProductCodeDef.INDEX_OP);

                //原注文注文チャネルをセットする。
                log.debug("原注文注文チャネルをセットする。");
                l_wGentradeCommission.setOrgOrderChannel(l_orderUnitRow.getOrderChanel());

                //原注文手数料Noをセットする。
                log.debug("原注文手数料Noをセットする。");
                l_wGentradeCommission.setOrgCommissionNo(l_orderUnitRow.getCommTblNo());

                //原注文手数料No枝番をセットする。
                log.debug("原注文手数料No枝番をセットする。");
                l_wGentradeCommission.setOrgCommissionRevNo(l_orderUnitRow.getCommTblSubNo());

                //弁済区分をセットする。                 
                l_wGentradeCommission.setPayType(WEB3PayTypeDef.OTHER);   

                //手数料.原資産銘柄コード = 先物OP銘柄.get原資産銘柄コード()
                l_wGentradeCommission.setUnderlyingProductCode(
                    ((WEB3IfoProductImpl)l_tradedProduct.getProduct()).getUnderlyingProductCode());

                //手数料.数量 = 新規建訂正内容.getAfterChangeOriginalQuantity()
                l_wGentradeCommission.setQuantity(l_afterChangeOriginalQuantity);
                
				//7.11.2.calc訂正時概算受渡代金(手数料, double, SubAccount, 先物OP取引銘柄,
				//double, SideEnum, boolean, double, double, boolean)
				//[calc訂正時概算受渡代金()に指定する引数] 
				// 手数料：　@W指値用手数料オブジェクト 
				// 指値：　@新規建訂正内容.(W指値)訂正指値 
				// 補助口座：　@補助口座オブジェクト 
				// 先物OP取引銘柄：　@先物OP取引銘柄オブジェクト 
				// 数量： 新規建訂正内容.getAfterChangeOriginalQuantity() 
				// 売買： 注文単位.getSide() 
				// is返済注文：　@false 
				// 約定数量：　@注文単位.getExecutedQuantity() 
				// 合計約定金額：　@注文単位.getExecutedAmount() 
				// isSkip金額チェック：　@false 
                l_wEstimateDeliveryAmountCalcResult = 
                    l_orderManagerImpl.calcChangeEstimateDeliveryAmount(
                        l_wGentradeCommission,
                        l_ifoOpenContractChangeSpec.getWLimitPriceChange(),
                        l_GentradeSubAccount,
                        l_ifoTradedProductImpl,
                        l_ifoOpenContractChangeSpec.getAfterChangeOriginalQuantity(),
                        l_side,
                        false,
                        l_dblExecutedQuantity,
                        l_dblExecutedAmount,
                        false);                
            }
            
            //7.12.get代理入力者( )
            //代理入力者オブジェクトを取得する。 
            Trader l_trader = this.getTrader();

			WEB3IfoOpenContractChangeUpdateInterceptor l_openContractChangeUpdateInterceptor =
				new WEB3IfoOpenContractChangeUpdateInterceptor(
					l_ifoOpenContractChangeSpec);

            WEB3IfoEstimateDeliveryAmountCalcResult l_calcResultForCheck = null;

            boolean l_blnIsWLimitCommission = false;
            log.debug("プロパティセット");
            l_openContractChangeUpdateInterceptor.setOrderCond(l_request.orderCondType);
            if (SideEnum.BUY.equals(l_side) 
            && WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_ifoOpenContractChangeSpec.getOrderCond()))
            {
                if (l_estimateDeliveryAmountCalcResult.getRestraintTurnover() >= l_wEstimateDeliveryAmountCalcResult.getRestraintTurnover())
                {
                    l_openContractChangeUpdateInterceptor.setEstimateDeliveryAmountCalcResult
                        (l_estimateDeliveryAmountCalcResult);

					l_calcResultForCheck = l_estimateDeliveryAmountCalcResult;
                    l_openContractChangeUpdateInterceptor.setCommision(l_gentradeCommission);
                }
                else
                {
                    l_blnIsWLimitCommission = true;
                    l_openContractChangeUpdateInterceptor.setEstimateDeliveryAmountCalcResult
                      (l_wEstimateDeliveryAmountCalcResult);

                    l_calcResultForCheck = l_wEstimateDeliveryAmountCalcResult;
                    l_openContractChangeUpdateInterceptor.setCommision(l_wGentradeCommission);
                }
            }
            else
            {
                l_openContractChangeUpdateInterceptor.setEstimateDeliveryAmountCalcResult
                    (l_estimateDeliveryAmountCalcResult);

                l_calcResultForCheck = l_estimateDeliveryAmountCalcResult;
                l_openContractChangeUpdateInterceptor.setCommision(l_gentradeCommission);
            }
            if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
            {
                log.debug("WEB3IfoOrderCondTypeDef.STOP_ORDER.equals(l_request.orderCondType)");
                l_openContractChangeUpdateInterceptor.setOrderCondOperator(l_request.stopOrderCondOperator);
                l_openContractChangeUpdateInterceptor.setStopOrderBasePriceType(l_request.stopPremium_underlyingAssets);
                l_openContractChangeUpdateInterceptor.setStopOrderBasePrice(Double.parseDouble(l_request.stopOrderCondPrice));
            }
            else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
            {
                log.debug("WEB3IfoOrderCondTypeDef.W_LIMIT_PRICE.equals(l_request.orderCondType)");
                l_openContractChangeUpdateInterceptor.setOrderCondOperator(l_request.wlimitOrderCondOperator);
                l_openContractChangeUpdateInterceptor.setStopOrderBasePriceType(l_request.wlimitPremium_underlyingAssets);
                l_openContractChangeUpdateInterceptor.setStopOrderBasePrice(Double.parseDouble(l_request.wlimitOrderCondPrice));
            }
            l_openContractChangeUpdateInterceptor.setWLimitPriceChange(l_ifoOpenContractChangeSpec.getWLimitPriceChange());
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
			
            //7.15.validate取引余力(補助口座 : 補助口座, 注文内容インタセプタ : Object[], 
			//注文内容 : Object[], 注文種別 : OrderTypeEnum, 余力更新フラグ : boolean)
			//[引数] 
			// 補助口座： 補助口座オブジェクト 
			// 注文内容インタセプタ： 先物OP新規建訂正更新インタセプタを要素とした配列 
			// 注文内容： 新規建訂正内容を要素とした配列 
			// 注文種別： 注文単位.注文種別 
			// 余力更新フラグ： false

            WEB3TPTradingPowerService l_tradingPowerService = 
                (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
            Object[] l_interceptorObject = new Object[1];
            l_interceptorObject[0] = l_openContractChangeUpdateInterceptor;
            Object[] l_specObject = new Object[1];
            l_specObject[0] = l_ifoOpenContractChangeSpec;
            
            WEB3TPTradingPowerResult l_tpTradingPoweResult = 
                l_tradingPowerService.validateTradingPower
                (l_GentradeSubAccount,
                l_interceptorObject,
                l_specObject,
                l_orderUnit.getOrderType(),
                false);
            if (!l_tpTradingPoweResult.isResultFlg())
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01306,
                    STR_METHOD_NAME);
            }
 
                
            //7.16.WEB3GenResponse
            WEB3GenResponse l_genResponse = l_request.createResponse();
            WEB3OptionsOpenMarginChangeConfirmResponse l_response = (WEB3OptionsOpenMarginChangeConfirmResponse)l_genResponse;

            //26.get市場閉局警告指数(部店, String)
            log.debug("get市場閉局警告指数");
            Branch l_branch = null;
            l_branch = l_subAccount.getMainAccount().getBranch();
            String[] l_strTradeCloseSuspension = null;
            
            l_strTradeCloseSuspension = WEB3GentradeTradingTimeManagement.getTradeCloseSuspension((WEB3GentradeBranch)l_branch,WEB3FuturesOptionDivDef.OPTION);
            //7.23.(*4)プロパティセット
            log.debug("プロパティセット");

            l_response.estimatedPrice = WEB3StringTypeUtility.formatNumber(l_calcResultForCheck.getEstimateDeliveryAmount());
            l_response.messageSuspension = l_strTradeCloseSuspension;

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

            l_response.checkDate = WEB3DateUtility.toDay(l_dateOrderBizDate);
            l_response.commission = WEB3StringTypeUtility.formatNumber(l_calcResultForCheck.getCommission());
            l_response.commissionCourse = l_calcResultForCheck.getCommissionCourse();
            l_response.commissionConsumptionTax = WEB3StringTypeUtility.formatNumber
                (l_calcResultForCheck.getCommissionTax());

            l_response.partExecQuantity = WEB3StringTypeUtility.formatNumber(l_dblExecutedQuantity);

            //レスポンス.注文有効期限 = 新規建訂正内容.訂正失効日
            l_response.expirationDate = l_ifoOpenContractChangeSpec.getChangeExpirationDate();

            log.debug("l_response.estimatedPrice = " + l_response.estimatedPrice);
            log.debug("l_response.messageSuspension = " + l_response.messageSuspension);
            log.debug("l_response.checkPrice = " + l_response.checkPrice);
            log.debug("l_response.checkDate = " + l_response.checkDate);
                    
            log.exiting(STR_METHOD_NAME);
        
            return l_response;
             
        }  
        catch (DataQueryException l_ex)
        {            
            log.error("DBへのアクセスに失敗しました。",l_ex);                                    
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);                           
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。",l_ex);            
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
            
        }
       
    }
    
    /**
     * 株価指数オプションの訂正新規建注文を登録する。<BR>
     * <BR>
     * 「（OP訂正新規建サービス）submit注文」参照。<BR>
     * <BR>
     * =============================================== <BR>
     * シーケンス図 : （OP訂正新規建サービス）submit注文２ <BR>
     * 具体位置     : 1.15 戻り値の取引余力結果.判定フラグ == false の場合、<BR>
     * 　@　@　@　@　@　@　@　@　@例外をスローする。 <BR>
     * class        : WEB3BusinessLayerException <BR>
     * tag          : BUSINESS_ERROR_01306 <BR>
     * =============================================== <BR>
     * <BR>
     * @@param l_request - 株価指数オプション訂正新規建完了リクエスト
     * @@return WEB3OptionsOpenMarginChangeCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 4056BC930136
     */
    protected WEB3OptionsOpenMarginChangeCompleteResponse submitOrder
        (WEB3OptionsOpenMarginChangeCompleteRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = getClass().getName() + ".submitOrder(WEB3OptionsOpenMarginChangeCompleteRequest)";
        
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class); 
        WEB3OptionOrderManagerImpl l_orderManagerImpl = 
            (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
        try
        {
            //1.1.validate
            log.debug("validate");
            l_request.validate();
        
            //1.2.取引時間管理からget発注日
            log.debug("取引時間管理からget発注日");
            Date l_datOrderBizDate = null;
            if (l_request.checkDate == null)
            {
                l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            }
            else
            {
                l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate(l_request.checkDate);
            }

            //1.3.IfoOrderImpl
            OrderUnit l_orderUnit = l_orderManagerImpl.getOrderUnits(Long.parseLong(l_request.id))[0];
        
            //1.4.create新規建訂正内容(long, long, double, double, 
            //IfoOrderExecutionConditionType, Date, Date, String, String, String, 
            //double, double, IfoOrderExecutionConditionType, String, String)
            WEB3IfoOpenContractChangeSpec l_ifoOpenContractChangeSpec = null;
            
            log.debug("新規建訂正内容");
            
            // 注文ID：　@注文単位.getOrderId() 
            long l_lngOrderId = l_orderUnit.getOrderId();
            log.debug("getOrderUnitId()"); 
            
            // 注文単位ＩＤ：　@注文単位.getOrderUnitId() 
            long l_lngOrderUnitId = l_orderUnit.getOrderUnitId();
            log.debug("l_lngOrderUnitId = " + l_lngOrderUnitId);
            
            // 訂正後数量：　@リクエストデータ.数量 
            double l_dblOpOrderQuantity = 0.0D;
            if (l_request.opOrderQuantity != null)
            {
                l_dblOpOrderQuantity = Double.parseDouble(l_request.opOrderQuantity);
            }
          
            // 訂正後指値：　@リクエストデータ.注文単価 
            double l_dblLimitPrice = 0.0D;
            if (l_request.limitPrice != null)
            {
                l_dblLimitPrice = Double.parseDouble(l_request.limitPrice);
            }
            
            // 訂正執行条件：　@先物OPデータアダプタ.get執行条件(リクエストデータ.執行条件) 
            IfoOrderExecutionConditionType l_changeExecCondType = 
            	WEB3IfoDataAdapter.getExecutionConditionType(l_request.execCondType);

            // OP注文マネージャ
            WEB3OptionOrderManagerImpl l_optionOrderManagerImpl = 
                (WEB3OptionOrderManagerImpl) l_finApp.getTradingModule(
                    ProductTypeEnum.IFO).getOrderManager();

            // 訂正失効日：　@ 
            Date l_datchangeExpirationDate = null;
            IfoOrderUnit l_ifoOrderUnit = (IfoOrderUnit)l_orderUnit;
            IfoOrderUnitRow l_ifoOrderUnitRow = (IfoOrderUnitRow)l_ifoOrderUnit.getDataSourceObject();
            // リクエストデータ.注文有効期限 != nullの場合
            if (l_request.expirationDate != null)
            {
                // 先物OPプロダクトマネージャ.getProduct(注文単位.銘柄ID)にて取得
                TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
                WEB3IfoProductManagerImpl l_productMgr =
                    (WEB3IfoProductManagerImpl)l_tradingModule.getProductManager();
                Product l_product = null;
                try
                {
                    l_product = l_productMgr.getProduct(l_ifoOrderUnitRow.getProductId());
                }
                catch (NotFoundException l_ex)
                {
                    log.error("テーブルに該当するデータがありません。", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                WEB3IfoProductImpl l_ifoProductImpl = (WEB3IfoProductImpl)l_product;
                // 先物OP銘柄(*1).銘柄コード
                String l_strProductCode = l_ifoProductImpl.getProductCode();
                // 市場.getMarketCode()
                Market l_market = l_ifoProductImpl.getPrimaryMarket(); 
                String l_strMarketCode = l_market.getMarketCode();
                l_datchangeExpirationDate = l_optionOrderManagerImpl.getExpirationDate(
                    l_request.expirationDate,
                    l_strProductCode,
                    l_strMarketCode,
                    WEB3FuturesOptionDivDef.OPTION);
            }
            else
            {
                log.debug("l_request.expirationDate <> null");
                l_datchangeExpirationDate = l_datOrderBizDate;
            }
            
            // 発注日：　@get発注日()の戻り値 
            
            // 発注条件：　@リクエストデータ.発注条件区分 
            String l_strOrderCondType = l_request.orderCondType;
            
            // 発注条件演算子：　@ 
            // 　@リクエストデータ.発注条件区分 == ”逆指値”の場合、リクエストデータ.逆指値用発注条件演算子 
            // 　@リクエストデータ.発注条件区分 == ”W指値”の場合、リクエストデータ.W指値用発注条件演算子
            String l_strOrderCondOperator = null;
            if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
            {
            	l_strOrderCondOperator = l_request.stopOrderCondOperator;
            }
            else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
            {
            	l_strOrderCondOperator = l_request.wlimitOrderCondOperator;
            }
           
            // 逆指値基準値タイプ：　@ 
            // 　@リクエストデータ.発注条件区分 == ”逆指値”の場合、リクエストデータ.逆指値用プレミアム／原資産価格 
            // 　@リクエストデータ.発注条件区分 == ”W指値”の場合、リクエストデータ.W指値用プレミアム／原資産価格 
            String l_strStopPriceType = null;
            if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
            {
            	l_strStopPriceType = l_request.stopPremium_underlyingAssets;
            }
            else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
            {
            	l_strStopPriceType = l_request.wlimitPremium_underlyingAssets;
            }
            
            // 逆指値基準値：　@ 
            // 　@リクエストデータ.発注条件区分 == ”逆指値”の場合、リクエストデータ.逆指値用発注条件単価 
            // 　@リクエストデータ.発注条件区分 == ”W指値”の場合、リクエストデータ.W指値用発注条件単価 
            double l_dblStopOrderPrice = 0.0D;
            if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
            {
                if (l_request.stopOrderCondPrice != null)
                {
                    l_dblStopOrderPrice = Double.parseDouble(l_request.stopOrderCondPrice);
                }            	
            }
            else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
            {
                if (l_request.wlimitOrderCondPrice != null)
                {
                    l_dblStopOrderPrice = Double.parseDouble(l_request.wlimitOrderCondPrice);
                } 
            }
            
            // （W指値）訂正指値：　@リクエストデータ.W指値用注文単価 
            double l_dblWLimitOrderChange = 0.0D;
            if (l_request.wLimitPrice != null)
            {
                l_dblWLimitOrderChange = Double.parseDouble(l_request.wLimitPrice);
            }
            
            // （W指値）執行条件：　@先物OPデータアダプタ.get執行条件(リクエストデータ.Ｗ指値用執行条件)   
            IfoOrderExecutionConditionType l_wlimitExecCondType = 
            	WEB3IfoDataAdapter.getExecutionConditionType(l_request.wlimitExecCondType);
            
            // （W指値）有効状態区分：　@リクエストデータ.Ｗ指値用有効状態区分 
            String l_strWlimitEnableStatusDiv = l_request.wlimitEnableStatusDiv;
            
            // 訂正後注文期限区分：　@リクエストデータ.注文期限区分
            String l_strExpirationDateType = l_request.expirationDateType;
            
            //夕場前繰越対象フラグ：　@先物OPデータアダプタ.get夕場前繰越対象フラグ
            //(リクエストデータ.注文期限区分, 注文単位.部店ID)の戻り値
            boolean l_blnEveningSessionOrderFlag = WEB3IfoDataAdapter.getEveningSessionCarryOverFlag(
                l_strExpirationDateType, l_orderUnit.getBranchId());

            l_ifoOpenContractChangeSpec = WEB3IfoOpenContractChangeSpec.createIfoOpenContractChangeSpec(
        		l_lngOrderId, 
        		l_lngOrderUnitId, 
        		l_dblOpOrderQuantity, 
        		l_dblLimitPrice,
        		l_changeExecCondType,
        		l_datchangeExpirationDate,
                l_datOrderBizDate,
        		l_strOrderCondType,
        		l_strOrderCondOperator,
        		l_strStopPriceType,
        		l_dblStopOrderPrice,
        		l_dblWLimitOrderChange,
        		l_wlimitExecCondType,
        		l_strWlimitEnableStatusDiv,      		
        		l_strExpirationDateType,
                l_blnEveningSessionOrderFlag);

            //1.5.get補助口座( )
            log.debug("get補助口座( )");
            WEB3GentradeSubAccount l_subAccount = null;
            l_subAccount = (WEB3GentradeSubAccount)getSubAccount();
                
            //1.6.validate新規建訂正注文
            log.debug("l_subAccount = " + l_subAccount);
            log.debug("l_WEB3IfoOpenContractChangeSpec = " + l_ifoOpenContractChangeSpec);
            log.debug("validate新規建訂正注文");

            OrderValidationResult l_result = null;
            l_result = l_orderManagerImpl.validateChangeOrder(l_subAccount,l_ifoOpenContractChangeSpec);
            
            if (l_result.getProcessingResult().isFailedResult())
            {
                log.debug("ProcessingResult() = " + l_result.getProcessingResult());
                throw new WEB3BusinessLayerException(
                    l_result.getProcessingResult().getErrorInfo(),
                    STR_METHOD_NAME);                
            }
            //1.7.1（OP訂正新規建サービス）submit注文２ （参照）
            //1.7.2.getTradedProduct
            log.debug("getTradedProduct");
            TradedProduct l_tradedProduct = null;
            l_tradedProduct = l_orderUnit.getTradedProduct();
            
            //1.7.3.getDataSourceObject
            log.debug("getDataSourceObject");
            IfoOrderUnitRow l_orderUnitRow = null;
            l_orderUnitRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
            
            //1.7.4.getAfterChangeOriginalQuantity
            log.debug("getAfterChangeOriginalQuantity");
            double l_afterChangeOriginalQuantity = 0;
            l_afterChangeOriginalQuantity = l_ifoOpenContractChangeSpec.getAfterChangeOriginalQuantity();

            if (Double.isNaN(l_afterChangeOriginalQuantity))
            {
                l_afterChangeOriginalQuantity = 0D;
            }
  
            //1.7.5.getSide
            log.debug("getSide");
            SideEnum l_side = null;
            l_side = l_orderUnit.getSide();
            
            //1.7.6.getExecutedQuantity
            log.debug("getExecutedQuantity");
            double l_dblExecutedQuantity = 0;
            l_dblExecutedQuantity = l_orderUnit.getExecutedQuantity();
            if (Double.isNaN(l_dblExecutedQuantity))
            {
                l_dblExecutedQuantity = 0D;
            }

            //1.7.7.getExecutedAmount
            log.debug("getExecutedAmount");
            double l_dblExecutedAmount = 0;
            l_dblExecutedAmount = l_orderUnit.getExecutedAmount();
            if (Double.isNaN(l_dblExecutedAmount))
            {
                l_dblExecutedAmount = 0D;
            }
            
            //1.7.8.手数料
            log.debug("手数料");
            WEB3GentradeCommission l_gentradeCommission = null;
            l_gentradeCommission = new WEB3GentradeCommission();
            
            //1.7.9.(*1)プロパティセット
            log.debug("プロパティセット"); 
            //注文チャネルをセットする。
            l_gentradeCommission.setOrderChannel(this.getLoginChannel());
            
            //証券会社IDをセットする。
            l_gentradeCommission.setInstitutionCode(l_subAccount.getInstitution().getInstitutionCode());
            
            //部店IDをセットする。
            l_gentradeCommission.setBranchId(l_subAccount.getMainAccount().getBranch().getBranchId());
            
            //発注日をセットする。
            l_gentradeCommission.setOrderBizDate(new Timestamp(l_datOrderBizDate.getTime()));            
            
            //取引コード（SONAR）をセットする。            
             l_gentradeCommission.setSonarTradedCode(WEB3TransactionTypeSONARDef.OPEN_CONTRACT);            
             
            //手数料商品コードをセットする。
            l_gentradeCommission.setCommissionProductCode(WEB3CommisionProductCodeDef.INDEX_OP);
            
            //手数料.is指値 = 
            //　@1)リクエストデータ.確認時発注日 == nullの場合、
            //　@ リクエスト.注文単価!=0ならば、true。以外、false。
            //　@2)上記以外、this.is指値(リクエストデータ)。
            boolean l_blnIsLimitPrice = false;
            
            if (l_request.checkDate == null)
            {
                if (WEB3OrderPriceDivDef.MARKET_PRICE.equals(l_request.orderPriceDiv))
                {
                    l_blnIsLimitPrice = false;
                }
                else
                {
                    l_blnIsLimitPrice = true;
                }
            }
            else
            {
                l_blnIsLimitPrice = this.isLimitPrice(l_request);
            }
            
            l_gentradeCommission.setIsLimitPrice(l_blnIsLimitPrice);
            
            //原注文注文チャネルをセットする。
            l_gentradeCommission.setOrgOrderChannel(l_orderUnitRow.getOrderChanel());
            
            //原注文手数料Noをセットする。
            l_gentradeCommission.setOrgCommissionNo(l_orderUnitRow.getCommTblNo());
            
            //原注文手数料No枝番をセットする。
            l_gentradeCommission.setOrgCommissionRevNo(l_orderUnitRow.getCommTblSubNo());
            
            //弁済区分をセットする。                  
            l_gentradeCommission.setPayType(WEB3PayTypeDef.OTHER); 
            
            //手数料.原資産銘柄コード = 先物OP銘柄.get原資産銘柄コード()
            l_gentradeCommission.setUnderlyingProductCode(
                ((WEB3IfoProductImpl)l_tradedProduct.getProduct()).getUnderlyingProductCode());
            
            //手数料.数量 = 新規建訂正内容.getAfterChangeOriginalQuantity()
            l_gentradeCommission.setQuantity(l_afterChangeOriginalQuantity);
            
            log.debug("calc訂正時概算受渡代金1");
            WEB3IfoTradedProductImpl l_ifoTradedProductImpl = new WEB3IfoTradedProductImpl(l_tradedProduct.getTradedProductId());
            WEB3IfoEstimateDeliveryAmountCalcResult l_estimateDeliveryAmountCalcResult = null;

            double l_dblPrice = 0D;
            double l_dblLimitPriceTemp = 0D;
            double l_dblWLimitPriceTemp = 0D;
            if (l_request.limitPrice != null)
            {
                l_dblLimitPriceTemp = Double.parseDouble(l_request.limitPrice);
            }
            if (l_request.wLimitPrice != null)
            {
                l_dblWLimitPriceTemp = Double.parseDouble(l_request.wLimitPrice);
            }
            if (l_request.checkDate != null)
            {
                if (l_request.checkPrice != null)
                {
                    l_dblPrice = Double.parseDouble(l_request.checkPrice); 
                }
                else
                {
                    if (SideEnum.BUY.equals(l_side) && l_dblLimitPriceTemp < l_dblWLimitPriceTemp)
                    {
                        l_dblPrice = l_dblWLimitPriceTemp;
                    }
                    else
                    {
                        l_dblPrice = l_dblLimitPriceTemp;
                    }
                }
            }
            else
            {
                l_dblPrice = l_dblLimitPriceTemp; 
            }

            //1.7.10.概算受渡代金を計算する。
            //[calc訂正時概算受渡代金()に指定する引数]
            //手数料：　@手数料オブジェクト
            //指値：
            //　@[リクエストデータ.確認時発注日 != nullの場合]
            //　@　@リクエストデータ.確認時単価 != nullの場合、リクエストデータ.確認時単価。
            //　@　@リクエストデータ.確認時単価 == nullの場合、
            //　@　@　@注文単位.getSide() == "買" and リクエストデータ.注文単価(*1) <
            //                           リクエストデータ.W指値用注文単価(*2) ならば、
            //　@　@　@　@リクエストデータ.W指値用注文単価(*2)をセット。
            //　@　@　@以外、リクエストデータ.注文単価(*1)をセット。
            //　@[上記以外]（リッチクライアント起動）
            //　@　@リクエストデータ.注文単価(*1)をセット。
            //　@　@(*1　@注文単価==nullの場合、ゼロをセット）
            //　@　@(*2　@W指値用注文単価==nullの場合、ゼロをセット）
            //補助口座：　@補助口座オブジェクト
            //先物OP取引銘柄：　@先物OP取引銘柄オブジェクト
            //数量： 新規建訂正内容.getQuantity()
            //売買： 注文単位.getSide()
            //is返済注文：　@false
            //約定数量：　@注文単位.getExecutedQuantity()
            //合計約定金額：　@注文単位.getExecutedAmount()
            //isSkip金額チェック：　@false
            l_estimateDeliveryAmountCalcResult = 
                l_orderManagerImpl.calcChangeEstimateDeliveryAmount(
                    l_gentradeCommission,
                    l_dblPrice,
                    l_subAccount,
                    l_ifoTradedProductImpl,
                    l_afterChangeOriginalQuantity,
                    l_side,
                    false,
                    l_dblExecutedQuantity,
                    l_dblExecutedAmount,
                    false); 
            
            WEB3IfoEstimateDeliveryAmountCalcResult l_amountCalcResult = null;
            
			//1.7.11.(*2)分岐フロー
			//完了処理直接起動時 かつ リミット注文が有効なW指値注文（買建）の場合
			//（リクエストデータ.確認時発注日 == null && 
			// 注文単位.getSide() == "BUY" && 
			// 新規建訂正注文内容.発注条件() == "W指値" &&
			// リクエストデータ.Ｗ指値用有効状態区分 == "リミット注文有効"）、処理を行う。
			//
			//※完了処理直接起動時でない場合は、確認処理にて高い方の概算受渡代金
			//　@が確定している（比較済である）為、計算不要。
            WEB3GentradeCommission l_wLimitPriceCommission = new WEB3GentradeCommission();
            if (l_request.checkDate == null 
        		&& SideEnum.BUY.equals(l_side) 
        		&& WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(
    				l_ifoOpenContractChangeSpec.getOrderCond()) 
        		&& WEB3IfoWLimitEnableStatusDivDef.LIMIT_ENABLE.equals(
    				l_request.wlimitEnableStatusDiv))
            {
                //1.7.11.1.[setIs指値()に指定する引数]
                //is指値：　@新規建訂正内容.(W指値)訂正指値 != 0の場合、true。以外、false。
                if (l_ifoOpenContractChangeSpec.getWLimitPriceChange() != 0)
                {
                    l_wLimitPriceCommission.setIsLimitPrice(true);
                }
                else
                {
                    l_wLimitPriceCommission.setIsLimitPrice(false);
                }

                l_wLimitPriceCommission.setOrderChannel(this.getLoginChannel());

                //証券会社IDをセットする。
                l_wLimitPriceCommission.setInstitutionCode(l_subAccount.getInstitution().getInstitutionCode());

                //部店IDをセットする。
                l_wLimitPriceCommission.setBranchId(l_subAccount.getMainAccount().getBranch().getBranchId());
 
                //発注日をセットする。
                l_wLimitPriceCommission.setOrderBizDate(new Timestamp(l_datOrderBizDate.getTime()));            

                //取引コード（SONAR）をセットする。            
                l_wLimitPriceCommission.setSonarTradedCode(WEB3TransactionTypeSONARDef.OPEN_CONTRACT);            

                //手数料商品コードをセットする。
                l_wLimitPriceCommission.setCommissionProductCode(WEB3CommisionProductCodeDef.INDEX_OP);

                //原注文注文チャネルをセットする。
                l_wLimitPriceCommission.setOrgOrderChannel(l_orderUnitRow.getOrderChanel());

                //原注文手数料Noをセットする。
                l_wLimitPriceCommission.setOrgCommissionNo(l_orderUnitRow.getCommTblNo());

                //原注文手数料No枝番をセットする。
                l_wLimitPriceCommission.setOrgCommissionRevNo(l_orderUnitRow.getCommTblSubNo());

                //弁済区分をセットする。                  
                l_wLimitPriceCommission.setPayType(WEB3PayTypeDef.OTHER); 

                //手数料.原資産銘柄コード = 先物OP銘柄.get原資産銘柄コード()
                l_wLimitPriceCommission.setUnderlyingProductCode(
                    ((WEB3IfoProductImpl)l_tradedProduct.getProduct()).getUnderlyingProductCode());

                //手数料.数量 = 新規建訂正内容.getAfterChangeOriginalQuantity()
                l_wLimitPriceCommission.setQuantity(l_afterChangeOriginalQuantity);

                //1.7.11.2.W指値訂正時の概算受渡代金を計算する。
                //[calc訂正時概算受渡代金()に指定する引数]
                //手数料：　@手数料オブジェクト
                //指値：　@新規建訂正内容.(W指値)訂正指値
                //補助口座：　@補助口座オブジェクト
                //先物OP取引銘柄：　@先物OP取引銘柄オブジェクト
                //数量： 新規建訂正内容.getAfterChangeOriginalQuantity()
                //売買： 注文単位.getSide()
                //is返済注文：　@false
                //約定数量：　@注文単位.getExecutedQuantity()
                //合計約定金額：　@注文単位.getExecutedAmount()
                //isSkip金額チェック：　@false
                l_amountCalcResult = 
                    l_orderManagerImpl.calcChangeEstimateDeliveryAmount(
                        l_wLimitPriceCommission,
                        l_ifoOpenContractChangeSpec.getWLimitPriceChange(),
                        l_subAccount,
                        l_ifoTradedProductImpl,
                        l_afterChangeOriginalQuantity,
                        l_side,
                        false,
                        l_dblExecutedQuantity,
                        l_dblExecutedAmount,
                        false); 
            }
            
            //1.7.12.get代理入力者( )
            Trader l_trader = this.getTrader();

            //1.7.13.先物OP新規建訂正更新インタセプタ(新規建訂正内容)
			WEB3IfoOpenContractChangeUpdateInterceptor l_openContractChangeUpdateInterceptor =
				new WEB3IfoOpenContractChangeUpdateInterceptor(
					l_ifoOpenContractChangeSpec);
            
            //1.7.14. (*2) プロパティセット
            log.debug("プロパティセット");
            //［概算受渡代金と手数料の設定］
            //(*)リクエストデータ.確認時発注日==null、かつ、買建、かつ、W指値注文の場合、以下判定を行う。
            //戻り値(*1)と戻り値(*2)の拘束売買代金を比較して、
            //比較結果の高いほうの戻り値の概算受渡代金計算オブジェクトを使用する。
            if (l_request.checkDate == null 
                    && SideEnum.BUY.equals(l_side) 
                    && WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_ifoOpenContractChangeSpec.getOrderCond()))
            {
               if (l_amountCalcResult.getRestraintTurnover() > l_estimateDeliveryAmountCalcResult.getRestraintTurnover())
               {
                   l_openContractChangeUpdateInterceptor.setEstimateDeliveryAmountCalcResult(l_amountCalcResult);
                   l_openContractChangeUpdateInterceptor.setCommision(l_wLimitPriceCommission);
               }
               else
               {
                   l_openContractChangeUpdateInterceptor.setEstimateDeliveryAmountCalcResult(l_estimateDeliveryAmountCalcResult);
                   l_openContractChangeUpdateInterceptor.setCommision(l_gentradeCommission);
               }
            }
            else
            {
                l_openContractChangeUpdateInterceptor.setEstimateDeliveryAmountCalcResult(l_estimateDeliveryAmountCalcResult);
                l_openContractChangeUpdateInterceptor.setCommision(l_gentradeCommission);
            }

            l_openContractChangeUpdateInterceptor.setOrderCond(l_request.orderCondType);
            if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
            {
                log.debug("WEB3IfoOrderCondTypeDef.STOP_ORDER.equals(l_request.orderCondType)");
                l_openContractChangeUpdateInterceptor.setOrderCondOperator(l_request.stopOrderCondOperator);
                l_openContractChangeUpdateInterceptor.setStopOrderBasePriceType(l_request.stopPremium_underlyingAssets);
                l_openContractChangeUpdateInterceptor.setStopOrderBasePrice(Double.parseDouble(l_request.stopOrderCondPrice));
            }
            else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
            {
                log.debug("WEB3IfoOrderCondTypeDef.W_LIMIT_PRICE.equals(l_request.orderCondType)");
                l_openContractChangeUpdateInterceptor.setOrderCondOperator(l_request.wlimitOrderCondOperator);
                l_openContractChangeUpdateInterceptor.setStopOrderBasePriceType(l_request.wlimitPremium_underlyingAssets);
                l_openContractChangeUpdateInterceptor.setStopOrderBasePrice(Double.parseDouble(l_request.wlimitOrderCondPrice));
            }
            l_openContractChangeUpdateInterceptor.setWLimitPriceChange(l_ifoOpenContractChangeSpec.getWLimitPriceChange());
            
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

            WEB3TPTradingPowerService l_tradingPowerService = 
                (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
            Object[] l_interceptorObject = new Object[1];
            l_interceptorObject[0] = l_openContractChangeUpdateInterceptor;
            Object[] l_specObject = new Object[1];
            l_specObject[0] = l_ifoOpenContractChangeSpec;

			//1.7.15.validate取引余力(補助口座 : 補助口座, 注文内容インタセプタ : Object[],
			//注文内容 : Object[], 注文種別 : OrderTypeEnum, 余力更新フラグ : boolean)
            //[引数] 
			// 補助口座： 補助口座オブジェクト 
			// 注文内容インタセプタ： 先物OP新規建訂正更新インタセプタを要素とした配列 
			// 注文内容： 新規建訂正内容を要素とした配列 
			// 注文種別： 注文単位.注文種別 
			// 余力更新フラグ： true 
            WEB3TPTradingPowerResult l_tpTradingPowerResult = 
                l_tradingPowerService.validateTradingPower
                (l_subAccount,
                l_interceptorObject,
                l_specObject,
                l_orderUnit.getOrderType(),
                true);
            if (!l_tpTradingPowerResult.isResultFlg())
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01306,
                    this.getClass().getName() + "." +STR_METHOD_NAME);
            }
            
            //1.7.16.setThreadLocalPersistenceEventInterceptor
            l_orderManagerImpl.setThreadLocalPersistenceEventInterceptor(l_openContractChangeUpdateInterceptor);
            
            //1.7.17.submitChangeOrder(補助口座 : SubAccount, 新規建訂正内容 : ChangeOrderSpec, 取引パスワード : String, isSkip発注審査(=true) : boolean)
            OrderSubmissionResult l_orderResult = null;
            l_orderResult = l_orderManagerImpl.submitChangeOrder((SubAccount)l_subAccount,(ChangeOrderSpec)l_ifoOpenContractChangeSpec,l_request.password,true);
                      
            if (l_orderResult.getProcessingResult().isSuccessfulResult())
            {
                //is予約注文確認要(IfoOrderUnit)
                boolean l_blnIsReserveOrderExist = l_orderManagerImpl.isReserveOrderExist(l_ifoOrderUnit);

                //予約注文確認要（is予約注文確認要() == true）の場合
                List l_lisOpenReserveIfoOrderUnits = null;
                if (l_blnIsReserveOrderExist)
                {
                    //get有効予約注文単位一覧(親注文の注文ID : long)
                    WEB3ToSuccReservationIfoOrderUpdateService l_ifoOrderUpdateService =
                        (WEB3ToSuccReservationIfoOrderUpdateService)Services.getService(
                            WEB3ToSuccReservationIfoOrderUpdateService.class);
                    l_lisOpenReserveIfoOrderUnits =
                        l_ifoOrderUpdateService.getOpenReserveIfoOrderUnits(l_ifoOrderUnit.getOrderId());
                }

                //1.7.18.WEB3GenResponse(WEB3GenRequest)
                WEB3OptionsOpenMarginChangeCompleteResponse l_response = (WEB3OptionsOpenMarginChangeCompleteResponse) l_request.createResponse();

                //1.7.19..(*3) プロパティセット
                l_response.lastUpdatedTimestamp = GtlUtils.getSystemTimestamp();
                l_response.orderActionId = l_request.id;
                if (l_lisOpenReserveIfoOrderUnits != null)
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
            else
            {
                log.debug("ProcessingResult() = " + l_orderResult.getProcessingResult());
                throw new WEB3BusinessLayerException(
                    l_orderResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }                        
        }
        catch (DataQueryException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);            
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);           
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    /**
     * (is指値)<BR>
     * 指値注文かどうか判別する。 <BR>
     * 指値注文の場合は、true。以外、falseを返却する。 <BR>
     * ※非リッチクライアント（リクエストデータ.注文ID != null）の場合 <BR>
     * 　@のみ使用すること。 <BR>
     * <BR>
     * 以下、引数.リクエストデータの値を使用して判別を行う。 <BR>
     * <BR>
     * 確認時単価 == nullの場合、trueを返却する。<BR>
     * 以外、falseを返却する。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 株価指数オプション訂正新規建注文完了リクエストオブジェクト<BR>
     * @@return boolean
     * @@throws WEB3BaseException 
     */
    protected boolean isLimitPrice(
        WEB3OptionsOpenMarginChangeCompleteRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            "isLimitPrice(WEB3OptionsOpenMarginChangeCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
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

}
@
