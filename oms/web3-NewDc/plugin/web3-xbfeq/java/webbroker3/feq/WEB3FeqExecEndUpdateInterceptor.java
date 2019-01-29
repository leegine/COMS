head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqExecEndUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式出来終了更新イベントインタセプタ(WEB3FeqExecEndUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/13  魏馨(中訊) 新規作成
                 : 2005/07/25  芦露(中訊) レビュー
*/

package webbroker3.feq;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
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
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (外国株式出来終了更新イベントインタセプタ)<BR>
 * 外国株式出来終了更新イベントインタセプタ<BR>
 * 
 * @@ author 魏馨<BR> 
 * @@ version 1.0<BR>
 */
public class WEB3FeqExecEndUpdateInterceptor extends WEB3FeqUpdateInterceptor 
{
    
    /**
     * (ログユーティリティ)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqExecEndUpdateInterceptor.class);
        
    /**
     * (外国株式出来終了更新イベントインタセプタ)<BR>
     * コンストラクタ<BR>
     * @@roseuid 42B6A39101C3
     */
    public WEB3FeqExecEndUpdateInterceptor() 
    {
     
    }
    
    /**
     * (（注文単位）更新値設定)<BR>
     * （mutateメソッドの実装） <BR>
     * <BR>
     * １）　@注文（ﾍｯﾀﾞ）テーブル更新<BR>
     * 　@super.mutate(OrderManagerPersistenceType, <BR>
     * 　@OrderManagerPersistenceContext, FeqOrderUnitParams)<BR>
     * 　@をコールする。<BR>
     * <BR>
     * ２）　@注文単位テーブル更新<BR>
     * 　@注文単位Paramsに拡張項目(*)設定し返却する。 <BR>
     * 　@項目設定内容は、【xTrade】補足資料.<BR>
     * 　@DB更新\\20.(管)出来終了<BR>
     * 　@「外株出来終了_外株注文単位仕様.xls」参照。<BR>
     * <BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。 <BR>
     * @@param l_updateType - (更新タイプ)<BR>
     * 更新タイプ<BR>
     * @@param l_context - (処理)<BR>
     * 処理<BR>
     * @@param l_feqOrderUnitParams - (注文単位行)<BR>
     * 注文単位行（：注文単位Params）<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams
     * @@roseuid 42B6A32001C3
     */
    public FeqOrderUnitParams mutate(OrderManagerPersistenceType l_updateType,
        OrderManagerPersistenceContext l_context, 
        FeqOrderUnitParams l_feqOrderUnitParams) 
    {
        final String STR_METHOD_NAME = "mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, FeqOrderUnitParams)";
        log.entering(STR_METHOD_NAME);

        if (l_feqOrderUnitParams == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータ値がNULL");
        }        
        
        //注文（ﾍｯﾀﾞ）テーブル更新
        l_feqOrderUnitParams = super.mutate(l_updateType, l_context, l_feqOrderUnitParams);
                
        //出来終了処理日時
        l_feqOrderUnitParams.setExecEndTimestamp(GtlUtils.getSystemTimestamp());
        FeqOrderRow l_feqOrderRow = null;
        
        try
        {            
            l_feqOrderRow = (FeqOrderRow) FeqOrderDao.findRowByOrderId(l_feqOrderUnitParams.getOrderId());
            //更新者コード = 注文.更新者コード
            l_feqOrderUnitParams.setLastUpdater(l_feqOrderRow.getLastUpdater());
        }
        catch (DataFindException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
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
     * 注文履歴Paramsに拡張項目(*)設定し返却する。<BR>
     * <BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。 <BR>
     * <BR>
     * １）　@注文単位オブジェクト取得 <BR>
     * <BR>
     * 引数の注文履歴Params.注文ID、<BR> 
     * 注文単位ＩＤに該当する注文単位オブジェクトを取得する。<BR> 
     * <BR>
     * ２）　@拡張項目セット <BR>
     * 　@パラメータ.注文履歴Paramsの拡張項目に値をセットし、返却する。 <BR>
     * 　@項目設定内容は、【xTrade】補足資料.DB更新\\20.<BR>
     * 　@（管）出来終了「外株出来終了_外株注文履歴仕様.xls」参照。<BR>
     * @@param l_updateType
     * @@param l_context
     * @@param l_feqOrderActionParams
     * @@return com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderActionParams
     * @@roseuid 42B6A32001E2
     */
    public FeqOrderActionParams mutate(OrderManagerPersistenceType l_updateType,
        OrderManagerPersistenceContext l_context,
        FeqOrderActionParams l_feqOrderActionParams) 
    {
        final String STR_METHOD_NAME = "mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, FeqOrderActionParams)";
        log.entering(STR_METHOD_NAME);
        
        if (l_feqOrderActionParams == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータ値がNULL");
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
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + "." + STR_METHOD_NAME);  
        }
        
        FeqOrderUnitRow l_feqOrderUnitRow = (FeqOrderUnitRow) l_feqOrderUnit.getDataSourceObject();
        //発注条件 = 注文単位.発注条件
        l_feqOrderActionParams.setOrderConditionType(l_feqOrderUnitRow.getOrderConditionType());
        
        //発注条件演算子 = 注文単位.発注条件演算子
        l_feqOrderActionParams.setOrderCondOperator(l_feqOrderUnitRow.getOrderCondOperator());
        
        //逆指値基準値 = 注文単位.逆指値基準値
        if (l_feqOrderUnitRow.getStopOrderPriceIsNull())
        {
            l_feqOrderActionParams.setStopOrderPrice(null);
        }
        else
        {
            l_feqOrderActionParams.setStopOrderPrice(l_feqOrderUnitRow.getStopOrderPrice());
        }
        
        //（W指値）訂正指値 = 注文単位.（W指値）訂正指値
        if (l_feqOrderUnitRow.getWLimitPriceIsNull())
        {
            l_feqOrderActionParams.setWLimitPrice(null);
        }
        else
        {
            l_feqOrderActionParams.setWLimitPrice(l_feqOrderUnitRow.getWLimitPrice());
        }
        
        //注文失効日付
        l_feqOrderActionParams.setExpirationDate(l_feqOrderUnitRow.getExpirationDate());
        
        //約定日時
        l_feqOrderActionParams.setExecTimestamp(null);
        
        //概算受渡代金
        if (l_feqOrderUnitRow.getEstimatedPriceIsNull())
        {
            l_feqOrderActionParams.setEstimatedPrice(null);
        }
        else
        {
            l_feqOrderActionParams.setEstimatedPrice(l_feqOrderUnitRow.getEstimatedPrice());
        }
        
        //概算受渡代金（外貨）
        if (l_feqOrderUnitRow.getFEstimatedPriceIsNull())
        {
            l_feqOrderActionParams.setFEstimatedPrice(null);
        }
        else
        {
            l_feqOrderActionParams.setFEstimatedPrice(l_feqOrderUnitRow.getFEstimatedPrice());
        }
        
        //注文訂正・取消区分 = 注文単位.注文訂正・取消区分
        l_feqOrderActionParams.setModifyCancelType(l_feqOrderUnitRow.getModifyCancelType());
        
        //注文経路区分
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
        
        //注文エラー理由コード = 0000:正常
        l_feqOrderActionParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);
        
        //更新者コード = 注文単位.更新者コード
        l_feqOrderActionParams.setLastUpdater(l_feqOrderUnitRow.getLastUpdater());
        
        log.exiting(STR_METHOD_NAME);
        return l_feqOrderActionParams;
    }
}
@
