head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqExecuteUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式約定更新イベントインタセプタ(WEB3FeqExecuteUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14  張玲(中訊) 新規作成
                 : 2005/07/28 呉艶飛(中訊) レビュー
Revesion History : 2007/08/20 謝旋 (中訊) ＤＢ更新仕様087
Revesion History : 2008/01/23 柴双紅(中訊) モデルNo.372
*/

package webbroker3.feq;


import java.math.BigDecimal;
import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderDao;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderExecutionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.feq.data.HostFeqOrderExecNotifyParams;
import webbroker3.feq.define.WEB3FeqLocalSystemAttributesDef;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.feq.util.WEB3FeqOrderUtility;

/**
 * (外国株式約定更新イベントインタセプタ)<BR>
 * 外国株式約定更新イベントインタセプタ<BR>
 * 
 * @@ author 張玲 
 * @@ version 1.0 
 */
public class WEB3FeqExecuteUpdateInterceptor extends WEB3FeqUpdateInterceptor 
{

    /**
     * (ログユーティリティ)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqExecuteUpdateInterceptor.class);
    
    /**
     * (外株出来通知キュー)<BR>
     * 外株出来通知キュー行オブジェクト<BR>
     * <BR>
     * ※外株出来通知キューParamsはDDLより自動生成する。<BR>
     */
    private HostFeqOrderExecNotifyParams feqOrderExecNotify;
    
    /**
     * (注文単位)<BR>
     * 更新前の注文単位オブジェクト。
     */
    private FeqOrderUnit feqOrderUnit;
    
    /**
     * (約定数量)<BR>
     * 約定数量。
     */
    private double execQuantity;
    
    /**
     * (約定単価)<BR>
     * 約定単価。
     */
    private double execPrice;
    
    /**
     * (為替レート)<BR>
     * 為替レート。
     */
    private double fxRate;
    
    /**
     * @@roseuid 42D0D2D6000F
     */
    public WEB3FeqExecuteUpdateInterceptor() 
    {
     
    }
    
    /**
     * (外国株式約定更新イベントインタセプタ)<BR>
     * コンストラクタ<BR>
     * <BR>
     * 出来通知キューをプロパティにセットしインスタンス生成する。<BR>
     * @@param l_hostFeqOrderExecNotifyParams - (外株出来通知キュー)<BR>
     * 外株出来通知キュー行オブジェクト
     * @@roseuid 428B055D0031
     */
    public WEB3FeqExecuteUpdateInterceptor(HostFeqOrderExecNotifyParams l_hostFeqOrderExecNotifyParams) 
    {
        this.feqOrderExecNotify = l_hostFeqOrderExecNotifyParams;
    }
    
    /**
     * (（注文単位）更新値設定)<BR>
     * （mutateメソッドの実装） <BR>
     * <BR>
     * １）　@注文（ﾍｯﾀﾞ）テーブル更新<BR>
     * 　@super.mutate(OrderManagerPersistenceType,<BR>
     * 　@OrderManagerPersistenceContext, FeqOrderUnitParams)<BR>
     * 　@をコールする。<BR>
     * <BR>
     * ２）　@注文単位テーブル更新<BR>
     * 　@注文単位Paramsに拡張項目(*)設定し返却する。<BR> 
     * 　@項目設定内容は、<BR>
     * 　@【xTrade】補足資料.DB更新\\11.管理者・出来入力<BR>
     * 　@「外株出来_外株注文単位仕様.xls」参照。<BR>
     * <BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。 <BR>
     * @@param l_updateType - (更新タイプ)<BR>
     * 更新タイプ
     * @@param l_context - (処理)<BR>
     * 処理
     * @@param l_feqOrderUnitParams - (注文単位行)<BR>
     * 注文単位行（：注文単位Params）
     * @@return FeqOrderUnitParams
     * @@roseuid 428B026B0060
     */
    public FeqOrderUnitParams mutate(
        OrderManagerPersistenceType l_updateType, 
        OrderManagerPersistenceContext l_context, 
        FeqOrderUnitParams l_feqOrderUnitParams) 
    {
        final String STR_METHOD_NAME = 
            "mutate(OrderManagerPersistenceType, "
            + "OrderManagerPersistenceContext, " 
            + "FeqOrderUnitParams)";
        log.entering(STR_METHOD_NAME);
        
        //１）　@注文（ﾍｯﾀﾞ）テーブル更新
        if (l_feqOrderUnitParams == null)
        {
            log.debug("注文単位ParamsがNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);            
        }

        l_feqOrderUnitParams = super.mutate(
            l_updateType,
            l_context,
            l_feqOrderUnitParams);
            
        //２）　@注文単位テーブル更新        
        //約定最終通番
        //入力がある場合（外株出来通知キューParams.約定通番 != null）、
        // 入力値（外株出来通知キューParams.約定通番）をセット
        if (this.feqOrderExecNotify.getExecSerialNo() != null)
        {
            l_feqOrderUnitParams.setLastExecutionSerialNo(Integer.parseInt(this.feqOrderExecNotify.getExecSerialNo()));
        } 
        
        try
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
            WEB3FeqBizLogicProvider l_bizLogicProvider =
                (WEB3FeqBizLogicProvider)l_tradingModule.getBizLogicProvider();
            WEB3FeqProduct l_product = (WEB3FeqProduct)this.feqOrderUnit.getProduct();
            WEB3GentradeCurrency l_genCurrency = l_product.getCurrency();
            int l_intScale = l_genCurrency.getScale();
            String l_strFCCYRoundDiv = l_genCurrency.getChangeFCcyRoundDiv();
            double l_dblFillAmount = this.execPrice * this.execQuantity;
            BigDecimal l_bdFillAmount = new BigDecimal(l_dblFillAmount);
            l_bdFillAmount = l_bdFillAmount.setScale(l_intScale, BigDecimal.ROUND_HALF_EVEN);
            l_dblFillAmount = l_bdFillAmount.doubleValue();
            double l_dblFillAmountYen =
                l_bizLogicProvider.calcJPYAmount(
                    l_dblFillAmount,
                    this.fxRate,
                    l_strFCCYRoundDiv);
            FeqOrderUnitRow l_orderUnitRow =
                (FeqOrderUnitRow)this.feqOrderUnit.getDataSourceObject();
            BigDecimal l_bdExecutedAmount =
                new BigDecimal(String.valueOf(l_orderUnitRow.getExecutedAmount()));
            l_feqOrderUnitParams.setExecutedAmount(l_bdExecutedAmount.add(l_bdFillAmount).doubleValue());
            l_feqOrderUnitParams.setExecutedAmountYen(l_orderUnitRow.getExecutedAmountYen() + l_dblFillAmountYen);
        }
        catch (WEB3BaseException l_wbe)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_wbe.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_wbe.getMessage(),
                l_wbe);
        }
        
        //注文訂正・取消区分
        //0：初期値（WEB3ModifyCancelTypeDefにて定義）
        l_feqOrderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.INITIAL_VALUE);
        
        //注文.更新者コード
        FeqOrderRow l_feqOrderRow = null;
        try
        {            
            l_feqOrderRow = (FeqOrderRow) FeqOrderDao.findRowByOrderId(l_feqOrderUnitParams.getOrderId());

            //更新者コード = 注文.更新者コード
            l_feqOrderUnitParams.setLastUpdater(l_feqOrderRow.getLastUpdater());
            if (this.feqOrderExecNotify.getExecutionSeqNoIsNull())
            {
                l_feqOrderUnitParams.setExecutionSeqNo(null);
            }
            else
            {
                l_feqOrderUnitParams.setExecutionSeqNo(this.feqOrderExecNotify.getExecutionSeqNo());
            }
        }
        catch (DataFindException l_ex)
        {
            log.error("データが重複しています: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80004,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_feqOrderUnitParams;
    }
    
    /**
     * (（注文履歴）更新値設定)<BR>
     * （mutateメソッドの実装） <BR>
     * 注文履歴Paramsに拡張項目(*)設定し返却する。 <BR>
     * <BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。 <BR>
     * <BR>
     * １）　@注文単位オブジェクト取得 <BR>
     * <BR>
     * 引数の注文履歴Params.注文ID、<BR>
     * 注文単位ＩＤに該当する注文単位オブジェクトを取得する。 <BR>
     * <BR>
     * ２）　@拡張項目セット <BR>
     * 　@パラメータ.注文履歴Paramsの拡張項目に値をセットし、返却する。 <BR>
     * 　@項目設定内容は、<BR>
     * 　@【xTrade】補足資料.DB更新\11.管理者・出来入力<BR>
     * 　@「外株出来_外株注文履歴仕様.xls」<BR>
     * @@param l_updateType
     * @@param l_context
     * @@param l_feqOrderActionParams
     * @@return FeqOrderActionParams
     * @@roseuid 42AD54F5031B
     */
    public FeqOrderActionParams mutate(
        OrderManagerPersistenceType l_updateType, 
        OrderManagerPersistenceContext l_context, 
        FeqOrderActionParams l_feqOrderActionParams) 
    {
        final String STR_METHOD_NAME = 
            "mutate(OrderManagerPersistenceType, "
            + "OrderManagerPersistenceContext, "
            + "FeqOrderActionParams) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_feqOrderActionParams == null)
        {
            log.debug("注文履歴ParamsがNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        long l_lngOrderUnitId = l_feqOrderActionParams.getOrderUnitId();
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3FeqOrderManager l_orderManager = (WEB3FeqOrderManager) l_tradingMod.getOrderManager();
        FeqOrderUnit l_feqOrderUnit = null;

        try 
        {
            // 注文単位オブジェクト取得
            l_feqOrderUnit = (FeqOrderUnit) l_orderManager.getOrderUnit(l_lngOrderUnitId);
        } 
        catch (NotFoundException l_ex) 
        {
            log.error(STR_METHOD_NAME, l_ex);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);  
        }
                
        if (l_feqOrderUnit == null)
        {
            log.debug("テーブルに該当するデータがありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        FeqOrderUnitRow l_feqOrderUnitRow = (FeqOrderUnitRow) l_feqOrderUnit.getDataSourceObject();

        //注文単位.発注条件   
        l_feqOrderActionParams.setOrderConditionType(l_feqOrderUnitRow.getOrderConditionType());

        //注文単位.発注条件演算子
        l_feqOrderActionParams.setOrderCondOperator(l_feqOrderUnitRow.getOrderCondOperator());

        //注文単位.逆指値基準値
        if (l_feqOrderUnitRow.getStopOrderPriceIsNull())
        {
            l_feqOrderActionParams.setStopOrderPrice(null);
        }
        else
        {
            l_feqOrderActionParams.setStopOrderPrice(l_feqOrderUnitRow.getStopOrderPrice());
        }        
        
        //注文単位.（W指値）訂正指値
        if (l_feqOrderUnitRow.getWLimitPriceIsNull())
        {
            l_feqOrderActionParams.setWLimitPrice(null);
        }
        else
        {
            l_feqOrderActionParams.setWLimitPrice(l_feqOrderUnitRow.getWLimitPrice());
        }        
        
        //注文単位.注文失効日付    
        l_feqOrderActionParams.setExpirationDate(l_feqOrderUnitRow.getExpirationDate());
        
        //約定日時        
        Timestamp l_tsNowDate = GtlUtils.getTradingSystem( ).getSystemTimestamp();
        
        //nullの場合、現在日時をセット
        if (this.feqOrderExecNotify.getExecTimestamp() == null)
        {
            l_feqOrderActionParams.setExecTimestamp(l_tsNowDate); 
        }
        //外株出来通知キューParams.約定日時
        else
        {
            l_feqOrderActionParams.setExecTimestamp(this.feqOrderExecNotify.getExecTimestamp());
        }
        
        //注文単位.概算受渡代金
        if (l_feqOrderUnitRow.getEstimatedPriceIsNull())
        {
            l_feqOrderActionParams.setEstimatedPrice(null);
        }
        else
        {
            l_feqOrderActionParams.setEstimatedPrice(l_feqOrderUnitRow.getEstimatedPrice());
        }        
        
        //注文単位.概算受渡代金（外貨）
        if (l_feqOrderUnitRow.getFEstimatedPriceIsNull())
        {
            l_feqOrderActionParams.setFEstimatedPrice(null);
        }
        else
        {
            l_feqOrderActionParams.setFEstimatedPrice(l_feqOrderUnitRow.getFEstimatedPrice());
        }        
        
        //注文単位.注文訂正・取消区分
        l_feqOrderActionParams.setModifyCancelType(l_feqOrderUnitRow.getModifyCancelType());
        
        //注文単位.注文経路区分
        l_feqOrderActionParams.setOrderRootDiv(l_feqOrderUnitRow.getOrderRootDiv());
        
        //市場から確認済みの注文単価 = 注文単位.市場から確認済みの注文単価
        if (l_feqOrderUnitRow.getConfirmedOrderPriceIsNull())
        {
            l_feqOrderActionParams.setConfirmedOrderPrice(null);
        }
        else
        {
            l_feqOrderActionParams.setConfirmedOrderPrice(l_feqOrderUnitRow.getConfirmedOrderPrice());
        }
        
        //市場から確認済みの概算受渡代金 = 注文単位.市場から確認済みの概算受渡代金
        if (l_feqOrderUnitRow.getConfirmedEstimatedPriceIsNull())
        {
            l_feqOrderActionParams.setConfirmedEstimatedPrice(null);
        }
        else
        {
            l_feqOrderActionParams.setConfirmedEstimatedPrice(l_feqOrderUnitRow.getConfirmedEstimatedPrice());
        }

        //市場から確認済みの概算受渡代金（外貨）= 注文単位.市場から確認済みの概算受渡代金（外貨）
        if (l_feqOrderUnitRow.getConfirmedFEstimatedPriceIsNull())
        {
            l_feqOrderActionParams.setConfirmedFEstimatedPrice(null);
        }
        else
        {
            l_feqOrderActionParams.setConfirmedFEstimatedPrice(l_feqOrderUnitRow.getConfirmedFEstimatedPrice());
        }
        
        //市場から確認済みの執行条件 = 注文単位.市場から確認済みの執行条件
        l_feqOrderActionParams.setConfirmedExecConditionType(l_feqOrderUnitRow.getConfirmedExecConditionType());
        
        //注文単位.注文エラー理由コード
        l_feqOrderActionParams.setErrorReasonCode(l_feqOrderUnitRow.getErrorReasonCode());
        
        //更新者コード = 注文単位.更新者コード
        l_feqOrderActionParams.setLastUpdater(l_feqOrderUnitRow.getLastUpdater());   
        
        log.exiting(STR_METHOD_NAME);
        return l_feqOrderActionParams;
    }
    
    /**
     * (（約定）更新値設定)<BR>
     * （mutateメソッドの実装） <BR>
     * 約定Paramsに拡張項目(*)設定し返却する。 <BR>
     * <BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。 <BR>
     * <BR>
     * 項目設定内容は、<BR>
     * 【xTrade】補足資料.DB更新\\<BR>
     * 11.管理者・出来入力「外株出来_外株約定仕様.xls」参照。<BR>
     * @@param l_updateType - (更新タイプ)<BR>
     * 更新タイプ
     * @@param l_context - (処理)<BR>
     * 処理
     * @@param l_feqOrderExecutionParams - (約定行)<BR>
     * 約定行（：約定Params）
     * @@return FeqOrderExecutionParams
     * @@roseuid 428B026B007F
     */
    public FeqOrderExecutionParams mutate(
        OrderManagerPersistenceType l_updateType, 
        OrderManagerPersistenceContext l_context, 
        FeqOrderExecutionParams l_feqOrderExecutionParams) 
    {
        final String STR_METHOD_NAME = 
            "mutate(OrderManagerPersistenceType, "
            + "OrderManagerPersistenceContext, "
            + "FeqOrderExecutionParams) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_feqOrderExecutionParams == null)
        {
            log.debug("約定ParamsがNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        long l_lngOrderUnitId = l_feqOrderExecutionParams.getOrderUnitId();
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3FeqOrderManager l_orderManager = (WEB3FeqOrderManager) l_tradingMod.getOrderManager();
        FeqOrderUnit l_feqOrderUnit = null;

        try 
        {
            // 注文単位オブジェクト取得
            l_feqOrderUnit = (FeqOrderUnit) l_orderManager.getOrderUnit(l_lngOrderUnitId);
        } 
        catch (NotFoundException l_ex) 
        {
            log.error(STR_METHOD_NAME, l_ex);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);  
        }

        if (l_feqOrderUnit == null)
        {
            log.debug("テーブルに該当するデータがありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        FeqOrderUnitRow l_feqOrderUnitRow = (FeqOrderUnitRow) l_feqOrderUnit.getDataSourceObject();
        
        //約定日時
        Timestamp l_tsNowDate = GtlUtils.getTradingSystem( ).getSystemTimestamp();
        
        //nullの場合、現在日時をセット
        if (this.feqOrderExecNotify.getExecTimestamp() == null)
        {
            l_feqOrderExecutionParams.setExecTimestamp(l_tsNowDate); 
        }
        //外株出来通知キューParams.約定日時
        else
        {
            l_feqOrderExecutionParams.setExecTimestamp(this.feqOrderExecNotify.getExecTimestamp());
        }
                
        Timestamp l_tsExecTimestamp = l_feqOrderExecutionParams.getExecTimestamp();        
        WEB3GentradeBizDate l_gentradeBizDate = new WEB3GentradeBizDate(new Timestamp(l_tsExecTimestamp.getTime()));
                                        
        //受渡日 = 約定日時（時間部分は00:00:00）の３営業日後
        try
        {
            String l_strMarketCode = l_finApp.getFinObjectManager().getMarket(l_feqOrderExecutionParams.getMarketId()).getMarketCode();        
            String l_strInstitutionCode = l_finApp.getAccountManager().getMainAccount(l_feqOrderExecutionParams.getAccountId()).getInstitution().getInstitutionCode();  
            l_feqOrderExecutionParams.setDeliveryDate(WEB3DateUtility.toDay(l_gentradeBizDate.roll(3)));
        }
        catch (NotFoundException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);  
        }
        catch (WEB3SystemLayerException l_ex)
        {
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);      
        }

        //現地受渡日
        //nullの場合、受渡日をセット
        if(this.feqOrderExecNotify.getFDeliveryDate() == null)
        {
            l_feqOrderExecutionParams.setFDeliveryDate(l_feqOrderExecutionParams.getDeliveryDate());
        }
        //外株出来通知キューParams.現地受渡日
        else
        {
            l_feqOrderExecutionParams.setFDeliveryDate(this.feqOrderExecNotify.getFDeliveryDate());
        }

        //注文単位.発注日
        l_feqOrderExecutionParams.setBizDate(l_feqOrderUnitRow.getBizDate());

        //注文単位.決済区分
        l_feqOrderExecutionParams.setSettleDiv(l_feqOrderUnitRow.getSettleDiv());

        //注文単位.識別コード
        l_feqOrderExecutionParams.setOrderRequestNumber(l_feqOrderUnitRow.getOrderRequestNumber());

        //注文単位.運用コード
        l_feqOrderExecutionParams.setOrderEmpCode(l_feqOrderUnitRow.getOrderEmpCode());

        //ThreadLocalSystemAttributesRegistry.getAttribute(”約定経路区分”)
        //（※各サービスインタセプタにてセットした値）        
        l_feqOrderExecutionParams.setOrderExecRouteDiv(
            ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3FeqLocalSystemAttributesDef.FEQ_ORDER_EXEC_ROUTE_DIV).toString());

        //注文単位.更新者コード
        l_feqOrderExecutionParams.setLastUpdater(l_feqOrderUnitRow.getLastUpdater());
        
        //売買代金　@小数点第三位を四捨五入（sonarの仕様）
        double l_dblForeignTradePricewk =
            new BigDecimal(String.valueOf(l_feqOrderExecutionParams.getExecPrice()))
            .multiply(new BigDecimal(String.valueOf(l_feqOrderExecutionParams.getExecQuantity())))
            .doubleValue();
        double l_dblForeignTradePrice = 
            WEB3FeqOrderUtility.getCutOutValue(
                l_dblForeignTradePricewk,
                2,
                WEB3FeqOrderUtility.ROUND);  
        l_feqOrderExecutionParams.setForeignTradePrice(l_dblForeignTradePrice);
        
        log.exiting(STR_METHOD_NAME);
        return l_feqOrderExecutionParams;
    }
    
    /**
     * (set注文単位)<BR>
     * 注文単位オブジェクトを設定する。
     * @@param l_feqOrderUnit - (注文単位)<BR>
     * 注文単位オブジェクト。
     */
    public void setFeqOrderUnit(FeqOrderUnit l_feqOrderUnit)
    {
        this.feqOrderUnit = l_feqOrderUnit;
    }
    
    /**
     * (get注文単位)<BR>
     * 注文単位オブジェクトを返す。
     * @@return FeqOrderUnit
     */
    public FeqOrderUnit getFeqOrderUnit()
    {
        return this.feqOrderUnit;
    }
    
    /**
     * (set約定数量)<BR>
     * 約定数量を設定する。
     * @@param l_dblExecQuantity - (約定数量)<BR>
     * 約定数量。
     */
    public void setExecQuantity(double l_dblExecQuantity)
    {
        this.execQuantity = l_dblExecQuantity;
    }
    
    /**
     * (get約定数量)<BR>
     * 約定数量を返す。
     * @@return double
     */
    public double getExecQuantity()
    {
        return this.execQuantity;
    }
    
    /**
     * (set約定単価)<BR>
     * 約定単価を設定する。
     * @@param l_dblExecPrice - (約定単価)<BR>
     * 約定単価。
     */
    public void setExecPrice(double l_dblExecPrice)
    {
        this.execPrice = l_dblExecPrice;
    }
    
    /**
     * (get約定単価)<BR>
     * 約定単価を返す。
     * @@return double
     */
    public double getExecPrice()
    {
        return this.execPrice;
    }
    
    /**
     * (set為替レート)<BR>
     * 為替レートを設定する。
     * @@param l_dblFxRate - (為替レート)<BR>
     * 為替レート。
     */
    public void setFxRate(double l_dblFxRate)
    {
        this.fxRate = l_dblFxRate;
    }
    
    /**
     * (get為替レート)<BR>
     * 為替レートを返す。
     * @@return double
     */
    public double getFxRate()
    {
        return this.fxRate;
    }
}
@
