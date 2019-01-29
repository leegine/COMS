head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.45.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoSettleContractUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP返済更新インタセプタクラス(WEB3IfoSettleContractUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/6/11 盧法@旭 (中訊) 新規作成
Revesion History : 2004/07/22 王暁傑 (中訊) WEB3TransactionTypeSONARDefで WEB3IfoSonarTradeCodeDefを差し替える
Revesion History : 2004/07/29  盧法@旭 (Sinocom) 対応バッグ WEB3_IFO_UT-000107
Revesion History : 2006/07/12 郭英 (中訊) DB更新仕様No.086,092
Revesion History : 2007/01/30 柴双紅 (中訊) DB更新仕様No.135,151
Revesion History : 2007/06/11 孟亜南 (中訊) 仕様変更モデルNo.650 DB更新仕様No.168
Revesion History : 2007/06/21 孟亜南 (中訊) DB更新仕様No.186
Revesion History : 2008/03/17 金傑 (中訊) DB更新仕様No.197
Revesion History : 2008/04/28 張騰宇 (中訊) モデル868 DB更新仕様No.207
Revesion History : 2008/05/07 張騰宇 (中訊) モデル884 DB更新仕様No.209
*/

package webbroker3.ifo;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3RequestTypeDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.ifo.define.WEB3IfoVoucherNoFirstNumDef;
import webbroker3.ifo.service.delegate.WEB3IfoFrontOrderService;
import webbroker3.util.WEB3LogUtility;

/**
 * (先物OP返済更新インタセプタ)<BR>
 * 先物OP返済更新インタセプタクラス<BR>
 * @@author 盧法@旭
 * @@version 1.0
 */
public class WEB3IfoSettleContractUpdateInterceptor extends WEB3IfoOrderUpdateInterceptor 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3IfoSettleContractUpdateInterceptor.class);

    /**
     * (先物OP返済注文内容)
     */
    private WEB3IfoSettleContractOrderSpec futuresOptionSettleContractOrderSpec;
    
    /**
     * 決済順序<BR>
     * 0：ランダム　@1：単価益順　@2：単価損順　@3：建日順<BR>
     * 一括返済時の場合設定<BR>
     */
    private String settletSequence;

    /**
     * (立会区分)<BR>
     * 1 ： 夕場（夕場実施する会社の夕場時間帯のみ設定）<BR>
     * null ： 上記以外<BR>
     */
    private String sessionType;

    /**
     * (扱者コード（SONAR）)<BR>
     * 扱者コード（SONAR） <BR>
     * <BR>
     * ※先物OP連続注文発注処理で使用 <BR>
     * <BR>
     * 連続注文発注処理では、先物OP注文単位.扱者コード（SONAR）に <BR>
     * 予約注文単位.扱者コード（SONAR）を設定するため。 <BR>
     * <BR>
     * 連続注文発注処理以外はセットされないのでnullである。<BR>
     */
    private String sonarTraderCode;

    /**
     * (初回注文の注文チャネル)<BR>
     * 初回注文の注文チャネル <BR>
     * <BR>
     * ※先物OP連続注文発注処理で使用<BR>
     */
    private String orderChanel;

    /**
     * (注文経路区分)<BR>
     * 注文経路区分<BR>
     * <BR>
     * ※先物OP連続注文発注処理で使用<BR>
     */
    private String orderRootDiv;

    /**
     * @@roseuid 40C07C0200CB
     */
    public WEB3IfoSettleContractUpdateInterceptor() 
    {
     
    }
    
    /**
     * (先物OP返済更新インタセプタ)
     * コンストラクタ<BR>
     * <BR>
     * インスタンスを生成し、引数の先物OP返済注文内容をプロパティにセットする。<BR>
     * @@param l_settleContractOrderSpec - 先物OP返済注文内容
     * @@return webbroker3.ifo.WEB3IfoSettleContractUpdateInterceptor
     * @@roseuid 405E8A6503C4
     */
    public WEB3IfoSettleContractUpdateInterceptor(WEB3IfoSettleContractOrderSpec l_settleContractOrderSpec) 
    {
        this.futuresOptionSettleContractOrderSpec = l_settleContractOrderSpec;  
    }
    
    /**
     * (更新値設定)
     * （mutateメソッドの実装）<BR>
     * 注文単位Paramsに拡張項目(*)設定し返却する。<BR>
     * <BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。<BR>
     * (*) xTradeマニュアル「更新内容のカスタマイズ」参照。<BR>
     * <BR>
     * 　@プロパティの内容より、パラメータ.注文単位Paramsの拡張項目に値をセットし、<BR>
     * 返却する。<BR>
     * <BR>
     * 【ｘTrade】補足資料.DB更新<BR>
     *   注文単位.先物／オプション区分 == "先物"の場合 <BR>
     *      「先物返済_注文単位テーブル仕様.xls」参照 <BR>
     *   注文単位.先物／オプション区分 == "オプション"の場合<BR>
     *      「OP返済_注文単位テーブル仕様.xls」参照 <BR>
     * @@param l_updateType - (更新タイプ)<BR>
     * INSERTまたは、UPDATE。<BR>
     * <BR>
     * OrderManagerPersistenceTypeにて定数定義。<BR>
     * 
     * @@param l_process - 処理<BR>
     * （OrderManagerPersistenceContextにて定数定義）<BR>
     * @@param l_orderUnitParams - 注文単位Params<BR>
     * 株式注文単位が保持する項目のパラメータクラス。<BR>
     * @@return webbroker3.ifo.data.IfoOrderUnitParams
     * @@roseuid 405E899001B0
     */
    public IfoOrderUnitParams mutate(
        OrderManagerPersistenceType l_updateType,
        OrderManagerPersistenceContext l_process, 
        IfoOrderUnitParams l_orderUnitParams) 
    {
        //return null;
        final String STR_METHOD_NAME =
             "mutate(OrderManagerPersistenceType l_updateType," +
             "OrderManagerPersistenceContext l_process, " +
             "IfoOrderUnitParams l_orderUnitParams)";
        final String l_strBaseNumber = WEB3IfoVoucherNoFirstNumDef.FIRST_NUMBER; 
        
        log.entering(STR_METHOD_NAME);
        
        if(l_orderUnitParams == null)
        {
            log.debug("Enter the if path l_orderUnitParams is null.");
            log.error(STR_METHOD_NAME,new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName()+ STR_METHOD_NAME ));
            log.debug("Exit the if path l_orderUnitParams is null.");
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,this.getClass().getName()+  "." + STR_METHOD_NAME);
        }
                
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingMod =
            l_finApp.getTradingModule(ProductTypeEnum.IFO);
        //get product manager
        WEB3IfoProductManagerImpl l_productMgr =
            (WEB3IfoProductManagerImpl) l_tradingMod.getProductManager();
           
        //get WEB3Session Object
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService) Services.getService(
            OpLoginSecurityService.class);

        //get MainAccountRow
        MainAccount l_mainAccount = null;
        try
        {    
            log.debug("Enter the try path and get the account object.");
            l_mainAccount =
                l_finApp.getAccountManager().getMainAccount(l_orderUnitParams.getAccountId());
            log.debug("Succeeded in get the Account object.");
        }
        catch (NotFoundException l_nfe)
        {   log.debug("Failed to get the Account Object.");        
            log.error(STR_METHOD_NAME,l_nfe);   
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80006,this.getClass().getName()+  "." + STR_METHOD_NAME);
        }
     
        MainAccountRow l_mainAccountRow = (MainAccountRow) l_mainAccount.getDataSourceObject();
        //get Market Object
        Market l_market = null;
        long l_lngMarketId = l_orderUnitParams.getMarketId();
        try
        {
            log.debug("Enter the try and get the market object"); 
            l_market =
                l_finApp.getFinObjectManager().getMarket(l_lngMarketId);
            log.debug("Exit the try and succeed get the market object.");
        }
        catch (NotFoundException l_nfe)
        {
            log.debug("Failed to get the market object.");
            log.error(STR_METHOD_NAME,l_nfe);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80006,this.getClass().getName()+  "." + STR_METHOD_NAME);   
         }

        try
        {   
            log.debug("Enter the try path that l_orderUnitParams is not null.");
            //先物OP銘柄テーブル.先物／オプション区分を設定する
            Product l_product = l_productMgr.getProduct(l_orderUnitParams.getProductId());
            IfoProductRow l_productRow =(IfoProductRow)l_product.getDataSourceObject();
            l_orderUnitParams.setFutureOptionDiv(l_productRow.getFutureOptionDiv());
            log.debug("FutureOptionDiv is:" + l_productRow.getFutureOptionDiv());
            //インタセプタ.発注条件を設定する
            l_orderUnitParams.setOrderConditionType(this.orderCond);
            log.debug("発注条件を設定:"+ this.orderCond);
            
            if (WEB3OrderingConditionDef.DEFAULT.equals(this.orderCond))
            {                
                //インタセプタ.発注条件演算子を設定する
                l_orderUnitParams.setOrderCondOperator(null);   
                
                //インタセプタ.逆指値基準値タイプを設定する
                l_orderUnitParams.setStopPriceType(null);
                
                //インタセプタ.逆指値基準値を設定する
                l_orderUnitParams.setStopOrderPrice(null);
                            
                //（W指値）訂正指値)
                l_orderUnitParams.setWLimitPrice(null);
                // リクエストタイプ
                l_orderUnitParams.setRequestType(null);
            }
            else
            {               
                //インタセプタ.発注条件演算子を設定する
                l_orderUnitParams.setOrderCondOperator(this.orderCondOperator);                
                log.debug("発注条件演算子:"+ this.orderCondOperator);
                
                //インタセプタ.逆指値基準値タイプを設定する
                l_orderUnitParams.setStopPriceType(this.stopOrderBasePriceType);
                
                //インタセプタ.逆指値基準値を設定する
                l_orderUnitParams.setStopOrderPrice(this.stopOrderBasePrice);   
                
                // （W指値）訂正指値)<BR>            
                if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(this.orderCond))
                {
                    l_orderUnitParams.setWLimitPrice(null);
                }
                else
                {
                    l_orderUnitParams.setWLimitPrice(this.wLimitPriceChange);
                }
                // リクエストタイプ
                l_orderUnitParams.setRequestType(WEB3RequestTypeDef.DEFAULT);
            }
            //初回注文の注文チャネル
            if (this.orderChanel == null)
            {
                //get 初回注文の注文チャネル
                String l_strOrderChannel =
                     l_opLoginSec.getSessionProperty(WEB3SessionAttributeDef.ORDER_CHANNEL);
                l_orderUnitParams.setOrderChanel(l_strOrderChannel);
            }
            else
            {
                l_orderUnitParams.setOrderChanel(this.orderChanel);
            }
            //受注日時          
            Timestamp l_timeStamp = (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
            l_orderUnitParams.setReceivedDateTime(l_timeStamp);
            log.debug("受注日時:" + l_timeStamp);
            //初回注文の手数料No     
            l_orderUnitParams.setCommTblNo(this.commRevMstId.getCommissionNo());
            log.debug("初回注文の手数料No:" + this.commRevMstId.getCommissionNo());   
            //初回注文の手数料No枝番
            l_orderUnitParams.setCommTblSubNo(this.commRevMstId.getCommissionRevNo());
            log.debug("初回注文の手数料No枝番:" + this.commRevMstId.getCommissionRevNo());
            //扱者コード（SONAR）
            if (this.sonarTraderCode == null)
            {
                //顧客.扱者コード（SONAR）
                l_orderUnitParams.setSonarTraderCode(l_mainAccountRow.getSonarTraderCode());
            }
            else
            {
                //インタセプタ.扱者コード（SONAR）
                l_orderUnitParams.setSonarTraderCode(this.sonarTraderCode);
            }
            log.debug("扱者コード（SONAR）" + l_mainAccountRow.getSonarTraderCode());
            //注文単価
            l_orderUnitParams.setPrice(this.estimateDeliveryAmounCalcResult.getCalcUnitPrice());   
            
            //識別コード                    
            String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
            WEB3HostReqOrderNumberManageService l_numberService =
                (WEB3HostReqOrderNumberManageService) Services.getService(
                WEB3HostReqOrderNumberManageService.class);
            String l_strOrderRequestNumber =
            l_numberService.getNewNumber(
                futuresOptionSettleContractOrderSpec.getInstitutionCode(),
                l_strBranchCode,
                ProductTypeEnum.IFO);
            
            l_orderUnitParams.order_request_number = l_strOrderRequestNumber;
            
            //伝票Noを設定する
            int l_intRequestNumberLength = l_strOrderRequestNumber.length() - 3;
            String l_strVoucherNo = l_strBaseNumber + l_strOrderRequestNumber.substring(l_intRequestNumberLength);
            l_orderUnitParams.setVoucherNo(l_strVoucherNo);
            log.debug("伝票Noを設定する:" + l_strVoucherNo);
            //概算受渡代金
            l_orderUnitParams.setEstimatedPrice(this.estimateDeliveryAmounCalcResult.getEstimateDeliveryAmount());
            log.debug("概算受渡代金:" + this.estimateDeliveryAmounCalcResult.getEstimateDeliveryAmount());
            //取引コード（SONAR）
            l_orderUnitParams.setSonarTradedCode(WEB3TransactionTypeSONARDef.SETTLE_CONTRACT);
            log.debug("取引コード:" + WEB3TransactionTypeSONARDef.SETTLE_CONTRACT);
            //市場コード（SONAR）
            MarketRow l_marketRow = (MarketRow)l_market.getDataSourceObject();
            l_orderUnitParams.setSonarMarketCode(l_marketRow.getSonarMarketCode());
            log.debug("市場コード（SONAR）:" + l_marketRow.getSonarMarketCode());
            
            //手数料商品コード 
            if (WEB3FuturesOptionDivDef.OPTION.equals(l_orderUnitParams.getFutureOptionDiv()))
            {
                l_orderUnitParams.setCommProductCode(
                    WEB3CommisionProductCodeDef.INDEX_OP);            
            }
            else if (WEB3FuturesOptionDivDef.FUTURES.equals(l_orderUnitParams.getFutureOptionDiv()))
            {
                l_orderUnitParams.setCommProductCode(
                    WEB3CommisionProductCodeDef.INDEX_FUTURES);
            }            
            log.debug("手数料商品コード :" + l_orderUnitParams.getCommProductCode());
            
            //注文訂正・取消区分
            l_orderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.INITIAL_VALUE);
            log.debug("注文訂正・取消区分:" + WEB3ModifyCancelTypeDef.INITIAL_VALUE);
            //注文経路区分
            if (this.orderRootDiv == null)
            {
                //get 注文経路区分
                String l_strOrderRootDiv =
                     l_opLoginSec.getSessionProperty(
                     WEB3SessionAttributeDef.ORDER_ROOT_DIV);
                l_orderUnitParams.setOrderRootDiv(l_strOrderRootDiv);
            }
            else
            {
                l_orderUnitParams.setOrderRootDiv(this.orderRootDiv);
            }
            //市場から確認済みの注文単価
            l_orderUnitParams.setConfirmedOrderPrice(null);
            log.debug("市場から確認済みの注文単価:" +  l_orderUnitParams.getConfirmedOrderPrice());
            //市場から確認済みの概算受渡代金
            l_orderUnitParams.setConfirmedEstimatedPrice(null);
            log.debug("市場から確認済みの概算受渡代金:"+ l_orderUnitParams.getConfirmedEstimatedPrice());
            //市場から確認済みの執行条件
            l_orderUnitParams.setConfirmedExecConditionType(null);
            log.debug("市場から確認済みの執行条件:"+ l_orderUnitParams.getConfirmedExecConditionType());
            //決済順序 
            l_orderUnitParams.setClosingOrder(this.settletSequence);
            log.debug("決済順序:" + l_orderUnitParams.getClosingOrder());
            //注文エラー理由コード
            l_orderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);
            log.debug("注文エラー理由コード:" + l_orderUnitParams.getErrorReasonCode());
            log.debug("request type:" + WEB3RequestTypeDef.DEFAULT);

            //初回注文の注文単位ＩＤ
            l_orderUnitParams.setFirstOrderUnitId(this.futuresOptionSettleContractOrderSpec.getFirstOrderUnitId());

            //元発注条件
            l_orderUnitParams.setOrgOrderConditionType(null);
            
            //元発注条件演算子
            l_orderUnitParams.setOrgOrderCondOperator(null);
            
            //元逆指値基準値タイプ
            l_orderUnitParams.setOrgStopPriceType(null);
            
            //元逆指値基準値
            l_orderUnitParams.setOrgStopOrderPrice(null);
            
            //元（W指値）訂正指値
            l_orderUnitParams.setOrgWLimitPrice(null);
            
            //元（W指値）執行条件
            l_orderUnitParams.setOrgWLimitExecCondType(null);
                        
            //（W指値）執行条件
            //返済注文内容.get（W指値）執行条件
            //※発注条件＝（0：DEFAULT（条件指定なし）、1：逆指値）の場合は、null
            if (WEB3OrderingConditionDef.DEFAULT.equals(this.orderCond))
            {
                l_orderUnitParams.setWLimitExecCondType(null);
            }
            else
            {
                if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(this.orderCond))
                {
                    l_orderUnitParams.setWLimitExecCondType(null);
                }
                else
                {
                    l_orderUnitParams.setWLimitExecCondType(
                        this.futuresOptionSettleContractOrderSpec.getWLimitExecCondType());
                }
            }
            
            //（W指値）切替前指値
            l_orderUnitParams.setWLimitBeforeLimitPrice(null);
            
            //（W指値）切替前執行条件
            l_orderUnitParams.setWLimitBeforeExecCondType(null);

            //発注経路区分:先物OP発注サービス.get発注経路区分()の戻り値
            WEB3IfoFrontOrderService l_ifoOrderService =
                (WEB3IfoFrontOrderService)Services.getService(WEB3IfoFrontOrderService.class);
            String l_strSubmitOrderRouteDiv =
                l_ifoOrderService.getSubmitOrderRouteDiv(
                    l_mainAccountRow.getInstitutionCode(),
                    l_marketRow.getMarketCode());
            l_orderUnitParams.setSubmitOrderRouteDiv(l_strSubmitOrderRouteDiv);

            //夕場前繰越対象フラグ
            boolean l_blnEveningSessionCarryoverFlag =
                this.futuresOptionSettleContractOrderSpec.getEveningSessionCarryoverFlag();
            if (l_blnEveningSessionCarryoverFlag)
            {
                l_orderUnitParams.setEveningSessionCarryoverFlag(
                    BooleanEnum.TRUE);
            }
            else
            {
                l_orderUnitParams.setEveningSessionCarryoverFlag(
                    BooleanEnum.FALSE);
            }

            //立会区分
            l_orderUnitParams.setSessionType(this.sessionType);

            //日計り区分
            l_orderUnitParams.setDayTradeType(this.commRevMstId.getDayTradeType());

            // 注文期限区分 expiration_date_type 返済注文内容.get注文期限区分()の戻り値
            l_orderUnitParams.setExpirationDateType(
                this.futuresOptionSettleContractOrderSpec.getExpirationDateType());
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
        catch(Exception l_ex)
        {
            log.debug("Enter the catch path and report the exception.");   
            log.error(
                "__an unexpected error__",
                new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex));
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80003,this.getClass().getName()+  "." + STR_METHOD_NAME);
           
        }
        log.exiting(STR_METHOD_NAME);
        return l_orderUnitParams;
    }
    
    /**
     * (set決済順序)<BR>
     * 決済順序をセットする。<BR>
     * @@param l_strSettleSequence - 決済順序
     * 
     * 0：ランダム　@1：単価益順　@2：単価損順　@3：建日順
     * 一括返済時の場合設定
     * @@roseuid 4068E03801FF
     */
    public void setSettleSequence(String l_strSettleSequence) 
    {
        this.settletSequence = l_strSettleSequence;
    }
    
    /**
     * (get決済順序)<BR>
     * 決済順序を取得する。<BR>
     * @@return String
     * @@roseuid 4068E04503C4
     */
    public String getSettleSequence() 
    {
        return this.settletSequence;
    }
    
    /**
     * (get立会区分)<BR>
     * 立会区分を取得する。<BR>
     * <BR>
     * @@return String
     */
    public String getSessionType()
    {
        return this.sessionType;
    }

    /**
     * (set立会区分)<BR>
     * 立会区分をセットする。<BR>
     * @@param l_strSessionType - (立会区分)<BR>
     * 立会区分<BR>
     * 1 ： 夕場（夕場実施する会社の夕場時間帯のみ設定）<BR>
     * 上記以外 ： null<BR>
     */
    public void setSessionType(String l_strSessionType)
    {
        this.sessionType = l_strSessionType;
    }

    /**
     * (set扱者コード（SONAR）)<BR>
     * 扱者コード（SONAR）をセットする。<BR>
     * @@param l_strSonarTraderCode - (扱者コード（SONAR）)<BR>
     * 扱者コード（SONAR）<BR>
     */
    public void setSonarTraderCode(String l_strSonarTraderCode)
    {
        this.sonarTraderCode = l_strSonarTraderCode;
    }

    /**
     * (set初回注文の注文チャネル)<BR>
     * 初回注文の注文チャネルをセットする。<BR>
     * @@param l_strOrderChanel - (初回注文の注文チャネル)<BR>
     * 初回注文の注文チャネル<BR>
     */
    public void setOrderChanel(String l_strOrderChanel)
    {
        this.orderChanel = l_strOrderChanel;
    }

    /**
     * (set注文経路区分)<BR>
     * 注文経路区分をセットする。<BR>
     * @@param l_strOrderRootDiv - (注文経路区分)<BR>
     * 注文経路区分<BR>
     */
    public void setOrderRootDiv(String l_strOrderRootDiv)
    {
        this.orderRootDiv = l_strOrderRootDiv;
    }
}
@
