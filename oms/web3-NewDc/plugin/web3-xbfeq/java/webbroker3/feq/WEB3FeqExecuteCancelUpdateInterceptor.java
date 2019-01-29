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
filename	WEB3FeqExecuteCancelUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式約定取消更新イベントインタセプタ(WEB3FeqExecuteCancelUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research           
Revesion History : 2005/07/14  張玲(中訊) 新規作成
                 : 2005/07/28 呉艶飛(中訊) レビュー
Revesion History : 2008/01/23 柴双紅(中訊) モデルNo.372
*/

package webbroker3.feq;

import java.math.BigDecimal;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetUnitParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetUnitSalesParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqPositionManagerPersistenceEventInterceptor;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqFinTransactionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderDao;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderExecutionDao;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderExecutionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderExecutionRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitDao;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.util.WEB3LogUtility;

/**
 * (外国株式約定取消更新イベントインタセプタ) <BR>
 * 外国株式約定取消更新イベントインタセプタ <BR>
 *
 * @@author 張玲
 * @@version 1.0
 */
public class WEB3FeqExecuteCancelUpdateInterceptor extends WEB3FeqUpdateInterceptor implements
    FeqPositionManagerPersistenceEventInterceptor
{
    /**
     * ログユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqExecuteCancelUpdateInterceptor.class);

    /**
     * (外株約定) <BR>
     * 約定取消対象の外国株式約定オブジェクト <BR>
     */
    private WEB3FeqOrderExecution feqExecution;

    /**
     * (注文単位)<BR>
     * 更新前の注文単位オブジェクト。
     */
    private FeqOrderUnit feqOrderUnit;
    
    /**
     * @@roseuid 42D0D2AD038A
     */
    public WEB3FeqExecuteCancelUpdateInterceptor()
    {

    }

    /**
     * (外国株式約定取消更新イベントインタセプタ) <BR>
     * コンストラクタ <BR>
     * <BR>
     * 約定オブジェクトをプロパティにセットしインスタンス生成する。 <BR>
     *
     * @@param l_feqExecute -
     *            (外株約定) <BR>
     *            外国株式約定オブジェクト
     * @@roseuid 42AD57B40230
     */
    public WEB3FeqExecuteCancelUpdateInterceptor(WEB3FeqOrderExecution l_feqExecute)
    {
        this.feqExecution = l_feqExecute;
    }

    /**
     * (（注文単位）更新値設定) <BR>
     * （mutateメソッドの実装） <BR>
     * <BR>
     * １） 注文（ﾍｯﾀﾞ）テーブル更新 <BR>
     * super.mutate(OrderManagerPersistenceType, <BR>
     * OrderManagerPersistenceContext, FeqOrderUnitParams) <BR>
     * をコールする。 <BR>
     * <BR>
     * ２） 注文単位テーブル更新 <BR>
     * 注文単位Paramsに拡張項目(*)設定し返却する。 <BR>
     * 項目設定内容は、【xTrade】補足資料.DB更新\\ <BR>
     * 13.管理者・出来約定取消「外株出来取消_外株注文単位仕様.xls」参照。 <BR>
     * <BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。 <BR>
     *
     * @@param l_updateType -
     *            (更新タイプ) <BR>
     *            更新タイプ
     * @@param l_context -
     *            (処理) <BR>
     *            処理
     * @@param l_feqOrderUnitParams -
     *            (注文単位行) <BR>
     *            注文単位行（：注文単位Params）
     * @@return FeqOrderUnitParams
     * @@roseuid 42AD57B401F2
     */
    public FeqOrderUnitParams mutate(
        OrderManagerPersistenceType l_updateType,
        OrderManagerPersistenceContext l_context,
        FeqOrderUnitParams l_feqOrderUnitParams)
    {
        final String STR_METHOD_NAME = "mutate(OrderManagerPersistenceType, "
            + "OrderManagerPersistenceContext, FeqOrderUnitParams)";
        log.entering(STR_METHOD_NAME);

        if (l_feqOrderUnitParams == null)
        {
            log.debug(" 注文単位Params値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // １） 注文（ﾍｯﾀﾞ）テーブル更新
        l_feqOrderUnitParams = super.mutate(l_updateType, l_context, l_feqOrderUnitParams);
        
        // ２） 注文単位テーブル更新
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
            double l_dblUndoAmount =
                feqExecution.getExecutionPrice() * feqExecution.getExecutionQuantity();
            BigDecimal l_bdUndoAmount = new BigDecimal(l_dblUndoAmount);
            l_bdUndoAmount = l_bdUndoAmount.setScale(l_intScale, BigDecimal.ROUND_HALF_EVEN);
            l_dblUndoAmount = l_bdUndoAmount.doubleValue();
            double l_dblUndoAmountYen =
                l_bizLogicProvider.calcJPYAmount(
                    l_dblUndoAmount,
                    feqExecution.getFxRate(),
                    l_strFCCYRoundDiv);
            FeqOrderUnitRow l_orderUnitRow =
                (FeqOrderUnitRow)this.feqOrderUnit.getDataSourceObject();
            BigDecimal l_bdExecutedAmount =
                new BigDecimal(String.valueOf(l_orderUnitRow.getExecutedAmount()));
            l_feqOrderUnitParams.setExecutedAmount(l_bdExecutedAmount.subtract(l_bdUndoAmount).doubleValue());
            l_feqOrderUnitParams.setExecutedAmountYen(l_orderUnitRow.getExecutedAmountYen() - l_dblUndoAmountYen);
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
        
        // 更新者コード
        try
        {
            FeqOrderRow l_orderRow = FeqOrderDao.findRowByPk(l_feqOrderUnitParams.getOrderId());
            if (l_orderRow == null)
            {
                log.error("テーブルに該当するデータがありません。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            String l_strLastUpdater = l_orderRow.getLastUpdater();
            l_feqOrderUnitParams.setLastUpdater(l_strLastUpdater);
        }
        catch (DataFindException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
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
     * (（注文履歴）更新値設定) <BR>
     * （mutateメソッドの実装） <BR>
     * 注文履歴Paramsに拡張項目(*)設定し返却する。 <BR>
     * <BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。 <BR>
     * <BR>
     * １） 注文単位オブジェクト取得 <BR>
     * <BR>
     * 引数の注文履歴Params.注文ID、 <BR>
     * 注文単位ＩＤに該当する注文単位オブジェクトを取得する。 <BR>
     * <BR>
     * ２） 拡張項目セット <BR>
     * パラメータ.注文履歴Paramsの拡張項目に値をセットし、返却する。 <BR>
     * 項目設定内容は、 <BR>
     * 【xTrade】補足資料.DB更新\\ <BR>
     * 13.管理者・出来約定取消「外株出来取消_外株注文履歴仕様.xls」参照。 <BR>
     *
     * @@param l_updateType
     * @@param l_context
     * @@param l_feqOrderActionParams
     * @@return FeqOrderActionParams
     * @@roseuid 42AD57B40211
     */
    public FeqOrderActionParams mutate(
        OrderManagerPersistenceType l_updateType,
        OrderManagerPersistenceContext l_context,
        FeqOrderActionParams l_feqOrderActionParams)
    {
        final String STR_METHOD_NAME = "mutate(OrderManagerPersistenceType, "
            + "OrderManagerPersistenceContext, FeqOrderActionParams)";
        log.entering(STR_METHOD_NAME);

        if (l_feqOrderActionParams == null)
        {
            log.debug(" 注文履歴Params値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // １） 注文単位オブジェクト取得
        FeqOrderUnitRow l_orderUnitRow = null;
        try
        {
            l_orderUnitRow = FeqOrderUnitDao.findRowByPk(
                l_feqOrderActionParams.getOrderUnitId());
        }
        catch (DataFindException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        if (l_orderUnitRow == null)
        {
            log.debug("テーブルに該当するデータがありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // ２） 拡張項目セット
        // 発注条件
        l_feqOrderActionParams.setOrderConditionType(l_orderUnitRow.getOrderConditionType());

        // 発注条件演算子
        l_feqOrderActionParams.setOrderCondOperator(l_orderUnitRow.getOrderCondOperator());

        // 逆指値基準値
        if (l_orderUnitRow.getStopOrderPriceIsNull())
        {
            l_feqOrderActionParams.setStopOrderPrice(null);
        }
        else
        {
            l_feqOrderActionParams.setStopOrderPrice(l_orderUnitRow.getStopOrderPrice());
        }

        // （W指値）訂正指値
        if (l_orderUnitRow.getWLimitPriceIsNull())
        {
            l_feqOrderActionParams.setWLimitPrice(null);
        }
        else
        {
            l_feqOrderActionParams.setWLimitPrice(l_orderUnitRow.getWLimitPrice());
        }

        // 注文失効日付
        l_feqOrderActionParams.setExpirationDate(l_orderUnitRow.getExpirationDate());

        // 約定日時
        l_feqOrderActionParams.setExecTimestamp(feqExecution.getExecutionTimestamp());

        // 概算受渡代金
        if (l_orderUnitRow.getEstimatedPriceIsNull())
        {
            l_feqOrderActionParams.setEstimatedPrice(null);
        }
        else
        {
            l_feqOrderActionParams.setEstimatedPrice(l_orderUnitRow.getEstimatedPrice());
        }

        // 概算受渡代金（外貨）
        if (l_orderUnitRow.getFEstimatedPriceIsNull())
        {
            l_feqOrderActionParams.setFEstimatedPrice(null);
        }
        else
        {
            l_feqOrderActionParams.setFEstimatedPrice(l_orderUnitRow.getFEstimatedPrice());
        }

        // 注文訂正・取消区分
        l_feqOrderActionParams.setModifyCancelType(l_orderUnitRow.getModifyCancelType());

        // 注文経路区分
        l_feqOrderActionParams.setOrderRootDiv(l_orderUnitRow.getOrderRootDiv());

        // 市場から確認済みの注文単価
        if (l_orderUnitRow.getConfirmedOrderPriceIsNull())
        {
            l_feqOrderActionParams.setConfirmedOrderPrice(null);
        }
        else
        {
            l_feqOrderActionParams.setConfirmedOrderPrice(l_orderUnitRow.getConfirmedOrderPrice());
        }

        // 市場から確認済みの概算受渡代金
        if (l_orderUnitRow.getConfirmedEstimatedPriceIsNull())
        {
            l_feqOrderActionParams.setConfirmedEstimatedPrice(null);
        }
        else
        {
            l_feqOrderActionParams.setConfirmedEstimatedPrice(l_orderUnitRow.getConfirmedEstimatedPrice());
        }

        // 市場から確認済みの概算受渡代金（外貨）
        if (l_orderUnitRow.getConfirmedFEstimatedPriceIsNull())
        {
            l_feqOrderActionParams.setConfirmedFEstimatedPrice(null);
        }
        else
        {
            l_feqOrderActionParams.setConfirmedFEstimatedPrice(l_orderUnitRow.getConfirmedFEstimatedPrice());
        }

        // 市場から確認済みの執行条件
        l_feqOrderActionParams.setConfirmedExecConditionType(l_orderUnitRow.getConfirmedExecConditionType());

        // 注文エラー理由コード
        l_feqOrderActionParams.setErrorReasonCode(l_orderUnitRow.getErrorReasonCode());

        // 更新者コード
        l_feqOrderActionParams.setLastUpdater(l_orderUnitRow.getLastUpdater());

        //取消対象の外株約定.約定単価
        if (Double.isNaN(this.feqExecution.getExecutionPrice()))
        {
            l_feqOrderActionParams.setExecutedPrice(null);
        }
        else
        {
            l_feqOrderActionParams.setExecutedPrice(this.feqExecution.getExecutionPrice()); 
        }
        //取消対象の外株約定.約定数量
        l_feqOrderActionParams.setExecutedQuantity(this.feqExecution.getExecutionQuantity());
        log.exiting(STR_METHOD_NAME);
        return l_feqOrderActionParams;
    }

    /**
     * (（約定）更新値設定) <BR>
     * （mutateメソッドの実装） <BR>
     * 約定Paramsに拡張項目(*)設定し返却する。 <BR>
     * <BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。 <BR>
     * <BR>
     * 項目設定内容は、 <BR>
     * 【xTrade】補足資料.DB更新\\ <BR>
     * 13.管理者・出来約定取消「外株出来取消_外株約定仕様.xls」参照。 <BR>
     * <BR>
     *
     * @@param l_updateType -
     *            (更新タイプ) <BR>
     *            更新タイプ
     * @@param l_context -
     *            (処理) <BR>
     *            処理
     * @@param l_feqOrderExecutionParams -
     *            (約定行) <BR>
     *            約定行（：約定Params）
     * @@return FeqOrderExecutionParams
     * @@roseuid 42AE6BD7024E
     */
    public FeqOrderExecutionParams mutate(
        OrderManagerPersistenceType l_updateType,
        OrderManagerPersistenceContext l_context,
        FeqOrderExecutionParams l_feqOrderExecutionParams)
    {
        final String STR_METHOD_NAME = "mutate(OrderManagerPersistenceType, "
            + "OrderManagerPersistenceContext, FeqOrderExecutionParams)";
        log.entering(STR_METHOD_NAME);
        
        if (l_feqOrderExecutionParams == null)
        {
            log.debug(" 約定Params値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // 注文単位オブジェクト取得
        FeqOrderUnitRow l_orderUnitRow = null;
        try
        {
            l_orderUnitRow = FeqOrderUnitDao.findRowByPk(
                l_feqOrderExecutionParams.getOrderUnitId());
        }
        catch (DataFindException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        if (l_orderUnitRow == null)
        {
            log.debug("テーブルに該当するデータがありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // (*) xTradeが標準実装でセットしないカスタマイズ項目。
        // 更新者コード
        l_feqOrderExecutionParams.setLastUpdater(l_orderUnitRow.getLastUpdater());

        log.exiting(STR_METHOD_NAME);
        return l_feqOrderExecutionParams;
    }

    /**
     * （mutateBeforeInsertの実装） <BR>
     * <BR>
     * 未使用。 <BR>
     * 引数のAssetParamsを返却する。 <BR>
     *
     * @@param arg0
     * @@return AssetParams
     * @@roseuid 42AEA8210069
     */
    public AssetParams mutateBeforeInsert(AssetParams arg0)
    {
        return arg0;
    }

    /**
     * （mutateBeforeInsertの実装） <BR>
     * <BR>
     * 未使用。 <BR>
     * 引数のFeqFinTransactionParamsを返却する。 <BR>
     *
     * @@param arg0
     * @@return FeqFinTransactionParams
     * @@roseuid 42AEA8210088
     */
    public FeqFinTransactionParams mutateBeforeInsert(FeqFinTransactionParams arg0)
    {
        return arg0;
    }

    /**
     * （mutateBeforeInsertの実装） <BR>
     * <BR>
     * 未使用。 <BR>
     * 引数のAssetUnitParamsを返却する。 <BR>
     *
     * @@param arg0
     * @@return AssetUnitParams
     * @@roseuid 42AEA82100A8
     */
    public AssetUnitParams mutateBeforeInsert(AssetUnitParams arg0)
    {
        return arg0;
    }

    /**
     * （mutateBeforeInsertの実装） <BR>
     * <BR>
     * 未使用。 <BR>
     * 引数のAssetUnitSalesParamsを返却する。 <BR>
     *
     * @@param arg0
     * @@return AssetUnitSalesParams
     * @@roseuid 42AEA82100C7
     */
    public AssetUnitSalesParams mutateBeforeInsert(AssetUnitSalesParams arg0)
    {
        return arg0;
    }

    /**
     * （mutateBeforeUpdateの実装） <BR>
     * <BR>
     * 未使用。 <BR>
     * 引数のMapを返却する。 <BR>
     *
     * @@param arg0
     * @@param arg1
     * @@return Map
     * @@roseuid 42AEA82100D7
     */
    public Map mutateBeforeUpdate(AssetParams arg0, Map arg1)
    {
        return arg1;
    }

    /**
     * (（トランザクション（取引勘定明細））更新前更新値設定) <BR>
     * （mutateBeforeUpdateの実装） <BR>
     * 引数のMapに拡張項目(*)を設定し返却する。 <BR>
     * <BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。 <BR>
     * <BR>
     * 項目設定内容は、 <BR>
     * 【xTrade】補足資料.DB更新\\ <BR>
     * 13.管理者・出来約定取消「外株出来取消_トランザクション <BR>
     * （取引勘定明細）仕様.xls」参照。 <BR>
     * <BR>
     * ※Mapへは、key： 更新列物理名、value： 更新値として <BR>
     * 設定すること。 <BR>
     *
     * @@param l_feqFinTransactionParams
     * @@param l_map
     * @@return Map
     * @@roseuid 42AEA82100F6
     */
    public Map mutateBeforeUpdate(FeqFinTransactionParams l_feqFinTransactionParams, Map l_map)
    {
        final String STR_METHOD_NAME = "mutateBeforeUpdate(FeqFinTransactionParams, Map)";
        log.entering(STR_METHOD_NAME);

        if (l_feqFinTransactionParams == null)
        {
            log.debug("パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);            
        }    
             
        // this.約定IDに該当する約定取得
        FeqOrderExecutionRow l_orderExecRow = null;
        try
        {
            l_orderExecRow = FeqOrderExecutionDao.findRowByPk(
                l_feqFinTransactionParams.getOrderExecutionId());
        }
        catch (DataFindException l_ex)
        {
            log.error("DBへのアクセスに失敗しました:", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました:", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました:", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        if (l_orderExecRow == null)
        {
            log.error("テーブルに該当するデータがありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // (*) xTradeが標準実装でセットしないカスタマイズ項目。
        l_map.put("last_updater", l_orderExecRow.getLastUpdater());

        log.exiting(STR_METHOD_NAME);
        return l_map;
    }

    /**
     * （mutateBeforeUpdateの実装） <BR>
     * <BR>
     * 未使用。 <BR>
     * 引数のMapを返却する。 <BR>
     *
     * @@param arg0
     * @@param arg1
     * @@return Map
     * @@roseuid 42AEA8210115
     */
    public Map mutateBeforeUpdate(AssetUnitParams arg0, Map arg1)
    {
        return arg1;
    }

    /**
     * （mutateBeforeUpdateの実装） <BR>
     * <BR>
     * 未使用。 <BR>
     * 引数のMapを返却する。 <BR>
     *
     * @@param arg0
     * @@param arg1
     * @@return Map
     * @@roseuid 42AEA8210134
     */
    public Map mutateBeforeUpdate(AssetUnitSalesParams arg0, Map arg1)
    {
        return arg1;
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
}@
