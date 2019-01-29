head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqOrderAcceptUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式注文受付更新イベントインタセプタ(WEB3FeqOrderAcceptUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/15 王煜 (中訊) 新規作成
                   2005/07/27 艾興　@(中訊) レビュー
                   2006/10/17 徐大方(中訊) ＤＢ更新仕様068
                   2006/11/21 徐大方(中訊) 障害対応K00010
                   2006/12/19 徐宏偉(中訊) ＤＢ更新仕様077
*/

package webbroker3.feq;

import java.math.BigDecimal;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderAction;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderEventTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.LockedAssetDetailsParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultCancelOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultCancelOrderRejectedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultCancelOrderSentMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultChangeOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultChangeOrderRejectedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultChangeOrderSentMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultNewOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultNewOrderRejectedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultNewOrderSentMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultOrderInvalidatedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultUndoOrderInvalidatedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.MarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqOrderAction;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqOrderManager;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderActionRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.feq.define.WEB3FeqChangeCancelDivDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (外国株式注文受付更新イベントインタセプタ)<BR>
 * 外国株式注文受付更新イベントインタセプタ<BR>
 *
 * @@author 王煜(中訊)
 * @@version 1.0
 */
public class WEB3FeqOrderAcceptUpdateInterceptor extends WEB3FeqUpdateInterceptor
{
    /**
     * (ログユーティリティ)<BR>
     */
    protected static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqOrderAcceptUpdateInterceptor.class);


    /**
     * (市場レスポンスメッセージ)<BR>
     * 市場レスポンスメッセージ<BR>
     * <BR>
     * ※具象クラスは以下の何れか<BR>
     * 　@・DefaultNewOrderAcceptedMarketResponseMessage（注文受付済）<BR>
     * 　@・DefaultNewOrderRejectedMarketResponseMessage（注文受付エラー）<BR>
     * 　@・DefaultNewOrderSentMarketResponseMessage<BR>
     *    （注文受付済取消；受付済の取消）<BR>
     * 　@・DefaultChangeOrderSentMarketResponseMessage<BR>
     *    （注文受付済取消；訂正済の取消）<BR>
     * 　@・DefaultCancelOrderSentMarketResponseMessage<BR>
     *    （注文受付済取消；取消済の取消）<BR>
     * 　@・DefaultChangeOrderAcceptedMarketResponseMessage（訂正済）<BR>
     * 　@・DefaultChangeOrderRejectedMarketResponseMessage（訂正エラー）<BR>
     * 　@・DefaultCancelOrderAcceptedMarketResponseMessage（取消済）<BR>
     * 　@・DefaultCancelOrderRejectedMarketResponseMessage（取消エラー）<BR>
     * 　@・DefaultOrderInvalidatedMarketResponseMessage（出来ず）<BR>
     */
    private MarketResponseMessage marketResponseMessage;

    /**
     * @@roseuid 42D0D7C700FA
     */
    public WEB3FeqOrderAcceptUpdateInterceptor()
    {

    }

    /**
     * (外国株式注文受付更新イベントインタセプタ)<BR>
     * コンストラクタ<BR>
     * <BR>
     * 市場レスポンスメッセージをプロパティにセットしインスタンス生成する。<BR>
     * @@param l_marketResponseMessage - (市場レスポンスメッセージ)<BR>
     * 市場レスポンスメッセージ<BR>
     * <BR>
     * ※具象クラスは以下の何れか<BR>
     * 　@・DefaultNewOrderAcceptedMarketResponseMessage（注文受付済）<BR>
     * 　@・DefaultNewOrderRejectedMarketResponseMessage（注文受付エラー）<BR>
     * 　@・DefaultNewOrderSentMarketResponseMessage<BR>
     *     （注文受付済取消；受付済の取消）<BR>
     * 　@・DefaultChangeOrderSentMarketResponseMessage<BR>
     *     （注文受付済取消；訂正済の取消）<BR>
     * 　@・DefaultCancelOrderSentMarketResponseMessage<BR>
     *     （注文受付済取消；取消済の取消）<BR>
     * 　@・DefaultChangeOrderAcceptedMarketResponseMessage（訂正済）<BR>
     * 　@・DefaultChangeOrderRejectedMarketResponseMessage（訂正エラー）<BR>
     * 　@・DefaultCancelOrderAcceptedMarketResponseMessage（取消済）<BR>
     * 　@・DefaultCancelOrderRejectedMarketResponseMessage（取消エラー）<BR>
     * 　@・DefaultOrderInvalidatedMarketResponseMessage（出来ず）<BR>
     * @@roseuid 42A93E36015D
     */
    public WEB3FeqOrderAcceptUpdateInterceptor(
        MarketResponseMessage l_marketResponseMessage)
    {
        this.marketResponseMessage = l_marketResponseMessage;
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
     * ２）　@注文単位テーブル更新値設定<BR>
     * 　@注文単位Paramsに拡張項目<BR>
     * 　@（xTradeが標準実装でセットしないカスタマイズ項目）を設定し返却する。 <BR>
     * 　@項目設定内容は、<BR>
     * 　@【xTrade】補足資料.DB更新\\15.<BR>
     * 　@（管）注文受付取消認証\\「外株受付_外株注文単位仕様.xls」<BR>
     * 　@ファ@イルの対応するワークシート参照。<BR>
     * 　@対応するワークシートは以下の通り。<BR>
     * <BR>
     * ①@　@注文受付済の場合（this.市場レスポンスメッセージinstanceof<BR>
     * 　@DefaultNewOrderAcceptedMarketResponseMessage）<BR>
     * 　@⇒外株受付_外株注文単位 DB更新（注文受付済）<BR>
     * <BR>
     * ②　@注文受付エラーの場合<BR>（this.市場レスポンスメッセージ instanceof<BR>
     * 　@DefaultNewOrderRejectedMarketResponseMessage）<BR>
     * 　@⇒外株受付_外株注文単位 DB更新（注文受付エラー）<BR>
     * <BR>
     * ③　@受付済の取消の場合<BR>（this.市場レスポンスメッセージinstanceof<BR>
     * 　@DefaultNewOrderSentMarketResponseMessage）<BR>
     * ④　@訂正済の取消の場合<BR>（this.市場レスポンスメッセージinstanceof<BR>
     * 　@DefaultChangeOrderSentMarketResponseMessage）<BR>
     *<BR>
     * ⑤　@取消済の取消の場合（this.市場レスポンスメッセージinstanceof<BR>
     * 　@DefaultCancelOrderSentMarketResponseMessage）<BR>
     * 　@⇒外株受付_外株注文単位 DB更新（受付済取消）<BR>
     * <BR>
     * 　@※③～⑤で、且つ売付の場合<BR>
     * 　@（注文単位行.注文種別 == ”外株売り”）、ロック中の<BR>
     * 　@資産詳細テーブルの対象行(*1)を更新（DB-Update）する。更新内容は、<BR>
     * 　@　@「外株受付_ロック中の資産詳細テーブル.xls<BR>
     * 　@　@＃外株受付_ロック中の資産詳細DB更新（注文受付済取消）」参照。<BR>
     * <BR>
     * 　@　@(*1) ロック中の資産詳細テーブル対象行取得<BR>
     * 　@　@ポジションマネージャ.getロック中の資産詳細()にて取得する。<BR>
     * <BR>
     * 　@　@[getロック中の資産詳細()に指定する引数]<BR>
     * 　@　@口座ＩＤ：　@注文単位行.口座ＩＤ<BR>
     * 　@　@補助口座ＩＤ：　@注文単位行.補助口座ＩＤ <BR>
     * 　@　@銘柄ＩＤ：　@注文単位行.銘柄ＩＤ <BR>
     * 　@　@税区分：　@注文単位行.税区分 <BR>
     * <BR>
     * ⑥　@訂正済の場合<BR>（this.市場レスポンスメッセージinstanceof<BR>
     * 　@DefaultChangeOrderAcceptedMarketResponseMessage）<BR>
     * 　@⇒外株受付_外株注文単位 DB更新（訂正済）<BR>
     *
     * ⑦　@訂正エラーの場合<BR>（this.市場レスポンスメッセージinstanceof<BR>
     * 　@DefaultChangeOrderRejectedMarketResponseMessage）<BR>
     * 　@⇒外株受付_外株注文単位 DB更新（訂正エラー）<BR>
     * <BR>
     * ⑧　@取消済の場合<BR>（this.市場レスポンスメッセージinstanceof<BR>
     * 　@DefaultCancelOrderAcceptedMarketResponseMessage）<BR>
     * 　@⇒外株受付_外株注文単位 DB更新（取消済）<BR>
     * <BR>
     * ⑨　@取消エラーの場合<BR>（this.市場レスポンスメッセージinstanceof<BR>
     * 　@DefaultCancelOrderRejectedMarketResponseMessage）<BR>
     * 　@⇒外株受付_外株注文単位 DB更新（取消エラー）<BR>
     * <BR>
     * ⑩　@出来ず（失効）の場合<BR>（this.市場レスポンスメッセージinstanceof<BR>
     * 　@DefaultOrderInvalidatedMarketResponseMessage）<BR>
     * 　@⇒外株受付_外株注文単位 DB更新（出来ず）<BR>
     * <BR>
     * ⑪　@出来ず（失効）の取消の場合<BR>（this.市場レスポンスメッセージ<BR>
     * 　@instanceofDefaultUndoOrderInvalidatedMarketResponseMessage）<BR>
     * 　@⇒外株受付_外株注文単位 DB更新（受付済取消（失効取消））<BR>
     * @@param l_updateType - (更新タイプ)<BR>
     * 更新タイプ<BR>
     * @@param l_context - (処理)<BR>
     * 処理<BR>
     * @@param l_orderUnitParams - (注文単位行)<BR>
     * 注文単位行（：注文単位Params）<BR>
     * @@return FeqOrderUnitParams
     * @@roseuid 42A93E360159
     */
    public FeqOrderUnitParams mutate(
        OrderManagerPersistenceType l_updateType,
        OrderManagerPersistenceContext l_context,
        FeqOrderUnitParams l_orderUnitParams)
    {
        final String STR_METHOD_NAME =
            "mutate(OrderManagerPersistenceType, "
            + "OrderManagerPersistenceContext, "
            + "FeqOrderUnitParams)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnitParams == null)
        {
            log.error("パラメータNull出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }        

        // １） 注文（ﾍｯﾀﾞ）テーブル更新
        l_orderUnitParams = super.mutate(l_updateType, l_context, l_orderUnitParams);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        FeqOrderManager l_orderManager = (FeqOrderManager)l_tradingModule.getOrderManager();

        // 注文オブジェクト取得
        FeqOrderRow l_orderRow = null;
        try
        {
            l_orderRow = (FeqOrderRow) l_orderManager.getOrder(
                l_orderUnitParams.getOrderId()).getDataSourceObject();
        }
        catch (NotFoundException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        if (l_orderRow == null)
        {
            log.error("テーブルに該当するデータがありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        // 更新者コード
        String l_strLastUpdater = l_orderRow.getLastUpdater();
        log.debug("更新者コード = " + l_strLastUpdater);

        FeqOrderUnit l_orderUnit = (FeqOrderUnit)l_orderManager.toOrderUnit(l_orderUnitParams);

        // ２）　@注文単位テーブル更新値設定
        // ①@　@注文受付済の場合
        if (this.marketResponseMessage instanceof DefaultNewOrderAcceptedMarketResponseMessage)
        {
            // 市場から確認済みの注文単価
            l_orderUnitParams.setConfirmedOrderPrice(l_orderUnitParams.price);

            // 市場から確認済みの概算受渡代金
            l_orderUnitParams.setConfirmedEstimatedPrice(l_orderUnitParams.estimated_price);

            // 市場から確認済みの概算受渡代金（外貨）
            l_orderUnitParams.setConfirmedFEstimatedPrice(l_orderUnitParams.f_estimated_price);

            // 市場から確認済みの執行条件
            l_orderUnitParams.setConfirmedExecConditionType(l_orderUnitParams.getExecutionConditionType());

            // 更新者コード
            l_orderUnitParams.setLastUpdater(l_strLastUpdater);
        }

        // ②　@注文受付エラーの場合
        else if (this.marketResponseMessage instanceof DefaultNewOrderRejectedMarketResponseMessage)
        {
            // 更新者コード
            l_orderUnitParams.setLastUpdater(l_strLastUpdater);
        }

        // ③　@受付済の取消の場合
        // ④　@訂正済の取消の場合
        // ⑤　@取消済の取消の場合
        else if (this.marketResponseMessage instanceof DefaultNewOrderSentMarketResponseMessage ||
            this.marketResponseMessage instanceof DefaultChangeOrderSentMarketResponseMessage ||
            this.marketResponseMessage instanceof DefaultCancelOrderSentMarketResponseMessage)
        {
            FeqOrderAction l_orderActionMax = null;
            if (this.marketResponseMessage instanceof DefaultNewOrderSentMarketResponseMessage)
            {
                //市場から確認済みの数量
                l_orderUnitParams.setConfirmedQuantity(null);

                //市場から確認済みの指値
                l_orderUnitParams.setConfirmedPrice(null);

                //市場から確認済みの注文単価
                l_orderUnitParams.setConfirmedOrderPrice(null);

                //市場から確認済みの概算受渡代金
                l_orderUnitParams.setConfirmedEstimatedPrice(null);

                //市場から確認済みの概算受渡代金（外貨）
                l_orderUnitParams.setConfirmedFEstimatedPrice(null);

                //市場から確認済みの執行条件
                l_orderUnitParams.setConfirmedExecConditionType(null);
            }
            if (this.marketResponseMessage instanceof DefaultChangeOrderSentMarketResponseMessage)
            {
                //(*1) 訂正前数量：　@
                //　@注文単位.getOrderActions()の戻り値配列より、
                //　@注文イベントタイプ==”2：変更注文”&&注文状態==”7：受付済”）に当てはまるもののうち、
                //　@注文履歴番号が最大のものを取得する。
                OrderAction[] l_orderActions = l_orderUnit.getOrderActions();
                int l_intOrdActSerialNoMax = 0;
                boolean l_blnIsMaxAction = true;
                for (int i = 0; i < l_orderActions.length; i ++)
                {
                    FeqOrderAction l_orderAction = (FeqOrderAction)l_orderActions[i];
                    if (i == 0)
                    {
                        l_intOrdActSerialNoMax = l_orderAction.getOrderActionSerialNo();
                    }
                    if (OrderEventTypeEnum.CHANGE.equals(l_orderAction.getOrderEventType()) &&
                        OrderStatusEnum.MODIFY_ACCEPTED.equals(l_orderAction.getOrderStatus()))
                    {
                        if (l_blnIsMaxAction)
                        {
                            l_blnIsMaxAction = false;
                            l_orderActionMax = l_orderAction;
                        }
                        int l_intSerialNo = l_orderAction.getOrderActionSerialNo();
                        if (l_intSerialNo > l_intOrdActSerialNoMax)
                        {
                            l_intOrdActSerialNoMax = l_intSerialNo;
                            l_orderActionMax = l_orderAction;
                        }
                    }
                }
                if (l_orderActionMax == null)
                {
                    log.error(this.getClass().getName() + "." + STR_METHOD_NAME
                        + "データ不整合エラー:失効注文履歴を取得なし。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
                FeqOrderActionRow l_orderActionMaxRow = (FeqOrderActionRow)l_orderActionMax.getDataSourceObject();
                
                //市場から確認済みの数量:
                //取得した注文履歴.市場確認済みの数量を訂正前数量として使用する。
                if (l_orderActionMaxRow.getConfirmedQuantityIsNull())
                {
                    l_orderUnitParams.setConfirmedQuantity(null);
                }
                else
                {
                    l_orderUnitParams.setConfirmedQuantity(l_orderActionMaxRow.getConfirmedQuantity());
                }

                //市場から確認済みの指値
                //取得した注文履歴.市場確認済みの指値を訂正前指値として使用する。
                if (l_orderActionMaxRow.getConfirmedPriceIsNull())
                {
                    l_orderUnitParams.setConfirmedPrice(null);
                }
                else
                {
                    l_orderUnitParams.setConfirmedPrice(l_orderActionMaxRow.getConfirmedPrice());
                }

                //注文訂正・取消区分 = （訂正済の取消）の場合、５（訂正中）
                l_orderUnitParams.setModifyCancelType(WEB3FeqChangeCancelDivDef.CHANGING);

                //市場から確認済みの注文単価
                //取得した注文履歴.市場から確認済みの注文単価を訂正前指値として使用する。
                if (l_orderActionMaxRow.getConfirmedOrderPriceIsNull())
                {
                    l_orderUnitParams.setConfirmedOrderPrice(null);
                }
                else
                {
                    l_orderUnitParams.setConfirmedOrderPrice(l_orderActionMaxRow.getConfirmedOrderPrice());
                }

                //市場から確認済みの概算受渡代金
                //取得した注文履歴.市場から確認済みの概算受渡代金を訂正前概算受渡代金として使用する。
                if (l_orderActionMaxRow.getConfirmedEstimatedPriceIsNull())
                {
                    l_orderUnitParams.setConfirmedEstimatedPrice(null);
                }
                else
                {
                    l_orderUnitParams.setConfirmedEstimatedPrice(l_orderActionMaxRow.getConfirmedEstimatedPrice());
                }

                //市場から確認済みの概算受渡代金（外貨）
                //　@取得した注文履歴.市場から確認済みの概算受渡代金（外貨）を訂正前概算受渡代金（外貨）として使用する。
                if (l_orderActionMaxRow.getConfirmedFEstimatedPriceIsNull())
                {
                    l_orderUnitParams.setConfirmedFEstimatedPrice(null);
                }
                else
                {
                    l_orderUnitParams.setConfirmedFEstimatedPrice(l_orderActionMaxRow.getConfirmedFEstimatedPrice());
                }

                //市場から確認済みの執行条件
                //取得した注文履歴.市場から確認済みの執行条件を訂正前執行条件として使用する。
                l_orderUnitParams.setConfirmedExecConditionType(l_orderActionMaxRow.getConfirmedExecConditionType());
                
            }

            //（取消済の取消）の場合、1:オープン；OPEN
            if (this.marketResponseMessage instanceof DefaultCancelOrderSentMarketResponseMessage)
            {
                l_orderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);

                //注文訂正・取消区分 = （取消済の取消）の場合、１（取消中）
                l_orderUnitParams.setModifyCancelType(WEB3FeqChangeCancelDivDef.CANCELING);
            }

            //更新者コード
            l_orderUnitParams.setLastUpdater(l_strLastUpdater);
            
            // ※③～⑤で、且つ売付の場合（注文単位行.注文種別 == ”外株売り”）、
            // ロック中の資産詳細テーブルの対象行(*1)を更新（DB-Update）する。
            if (OrderTypeEnum.FEQ_SELL.equals(l_orderUnitParams.getOrderType()))
            {
                // (*1) ロック中の資産詳細テーブル対象行取得
                // ポジションマネージャ.getロック中の資産詳細()にて取得する。
                WEB3FeqPositionManager l_positionManager =
                    (WEB3FeqPositionManager)l_tradingModule.getPositionManager();
                LockedAssetDetailsParams l_lockedAssertDetailsParams = null;
                try
                {
                    l_lockedAssertDetailsParams = l_positionManager.getLockedAssetDetails(
                        l_orderUnitParams.getAccountId(),
                        l_orderUnitParams.getSubAccountId(),
                        l_orderUnitParams.getProductId(),
                        l_orderUnitParams.getTaxType());
                }
                catch (WEB3BaseException l_ex)
                {
                    log.error(STR_METHOD_NAME, l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        l_ex.getErrorInfo(),
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getErrorMessage(),
                        l_ex);
                }
                if (l_lockedAssertDetailsParams == null)
                {
                    log.error(this.getClass().getName() + "." + STR_METHOD_NAME
                        + "データ不整合エラー:資産詳細を取得なし。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
                // ロック中数量
                // （既存値） ＋ ※調整数量
                double l_dblLockedQuanity = l_lockedAssertDetailsParams.getLockedQuantity();

                //※調整数量：
                //　@●（受付済の取消）の場合、0。
                double l_dblAdjustQuanity = 0D;

                //　@●（訂正済の取消）の場合、
                if (this.marketResponseMessage instanceof DefaultChangeOrderSentMarketResponseMessage)
                {
                    //(*1) 訂正前数量：　@
                    //　@注文単位.getOrderActions()の戻り値配列より、
                    //　@（注文イベントタイプ==”2：変更注文”&&注文状態==”7：受付済”）に当てはまるもののうち、
                    //　@注文履歴番号が最大のものを取得する。
                    //　@取得した注文履歴.市場確認済みの数量を訂正前数量として使用する。
                    if (l_orderActionMax == null)
                    {
                        log.error(this.getClass().getName() + "." + STR_METHOD_NAME
                            + "データ不整合エラー:失効注文履歴を取得なし。");
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BaseRuntimeException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                            this.getClass().getName() + "." + STR_METHOD_NAME);
                    }
                    FeqOrderActionRow l_l_orderActionMaxRow = (FeqOrderActionRow)l_orderActionMax.getDataSourceObject();
                    double l_dblBeforeQuanity = l_l_orderActionMaxRow.getConfirmedQuantity();

                    //　@訂正前数量(*1)－（訂正後）注文単位.市場から確認済み数量
                    l_dblAdjustQuanity = l_dblBeforeQuanity - l_orderUnitParams.getConfirmedQuantity();
                }

                //　@●（取消済の取消）の場合、
                else if (this.marketResponseMessage instanceof DefaultCancelOrderSentMarketResponseMessage)
                {
                    //　@　@(注文数量－約定数量)
                    l_dblAdjustQuanity = l_orderUnitParams.getQuantity() - l_orderUnitParams.getExecutedQuantity();
                }
                l_lockedAssertDetailsParams.setLockedQuantity(l_dblLockedQuanity + l_dblAdjustQuanity);

                // 更新（DB-Update）する。
                try
                {
                    QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                    l_queryProcessor.doUpdateQuery(l_lockedAssertDetailsParams);
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
            //end if ※③～⑤で、且つ売付の場合（注文単位行.注文種別 == ”外株売り”）    
            }
        }

        // ⑥　@訂正済の場合
        else if (this.marketResponseMessage instanceof DefaultChangeOrderAcceptedMarketResponseMessage)
        {
            // 注文訂正・取消区分
            l_orderUnitParams.setModifyCancelType(
                ((l_orderUnitParams.getExecutedQuantity() == Double.NaN) || (l_orderUnitParams.getExecutedQuantity() == 0))
                ? WEB3ModifyCancelTypeDef.CHANGED : WEB3ModifyCancelTypeDef.PARTIALLY_CHANGED);

            // 市場から確認済みの注文単価
            l_orderUnitParams.setConfirmedOrderPrice(l_orderUnitParams.price);

            // 市場から確認済みの概算受渡代金
            l_orderUnitParams.setConfirmedEstimatedPrice(l_orderUnitParams.estimated_price);
            
            //市場から確認済みの概算受渡代金（外貨）
            l_orderUnitParams.setConfirmedFEstimatedPrice(l_orderUnitParams.f_estimated_price);

            // 市場から確認済みの執行条件
            l_orderUnitParams.setConfirmedExecConditionType(l_orderUnitParams.getExecutionConditionType());

            // 更新者コード
            l_orderUnitParams.setLastUpdater(l_strLastUpdater);
        }

        // ⑦　@訂正エラーの場合
        else if (this.marketResponseMessage instanceof DefaultChangeOrderRejectedMarketResponseMessage)
        {
            //執行条件:市場から確認済みの執行条件
            l_orderUnitParams.setExecutionConditionType(l_orderUnitParams.getConfirmedExecConditionType());
            
            //注文単価:市場から確認済みの注文単価
            l_orderUnitParams.setPrice(l_orderUnitParams.confirmed_order_price);
            
            //概算受渡代金:市場から確認済みの概算受渡代金
            l_orderUnitParams.setEstimatedPrice(l_orderUnitParams.confirmed_estimated_price);
            
            //概算受渡代金（外貨）:市場から確認済みの概算受渡代金（外貨）
            l_orderUnitParams.setFEstimatedPrice(l_orderUnitParams.confirmed_f_estimated_price);
            
            // 注文訂正・取消区分
            l_orderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.CHANGE_ERROR);

            // 更新者コード
            l_orderUnitParams.setLastUpdater(l_strLastUpdater);
        }

        // ⑧　@取消済の場合
        else if (this.marketResponseMessage instanceof DefaultCancelOrderAcceptedMarketResponseMessage)
        {
            // 注文訂正・取消区分
            l_orderUnitParams.setModifyCancelType(
			((l_orderUnitParams.getExecutedQuantity() == Double.NaN) || (l_orderUnitParams.getExecutedQuantity() == 0))
                ? WEB3ModifyCancelTypeDef.CANCELED : WEB3ModifyCancelTypeDef.PART_CANCELED);

            // 更新者コード
            l_orderUnitParams.setLastUpdater(l_strLastUpdater);
        }

        // ⑨　@取消エラーの場合
        else if (this.marketResponseMessage instanceof DefaultCancelOrderRejectedMarketResponseMessage)
        {
            // 概算受渡代金
            l_orderUnitParams.setEstimatedPrice(l_orderUnitParams.getConfirmedEstimatedPrice());
            
            //概算受渡代金（外貨）
            l_orderUnitParams.setFEstimatedPrice(l_orderUnitParams.getConfirmedFEstimatedPrice());
            
            // 注文訂正・取消区分
            l_orderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.CANCEL_ERROR);

            // 更新者コード
            l_orderUnitParams.setLastUpdater(l_strLastUpdater);

        }

        // ⑩　@出来ず（失効）の場合
        else if (this.marketResponseMessage instanceof DefaultOrderInvalidatedMarketResponseMessage)
        {
            if (!l_orderUnit.isUnexecuted())
            {
                double l_dblExtimatedPrice = 0.0D;
                double l_dblFExtimatedPrice = 0.0D;
                WEB3FeqFinTransactionManager l_finTransactionManager =
                    (WEB3FeqFinTransactionManager)l_tradingModule.getFinTransactionManager();
                try
                {
                    // トランザクションマネージャ.get受渡代金合計(注文単位)の戻り値。
                    l_dblExtimatedPrice = l_finTransactionManager.getNetAmount(l_orderUnit);
                    // トランザクションマネージャ.get受渡代金合計（外貨）(注文単位)の戻り値。
                    l_dblFExtimatedPrice = l_finTransactionManager.getNetAmountFc(l_orderUnit);
                }
                catch (WEB3BaseException l_ex)
                {
                    log.error(STR_METHOD_NAME, l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        l_ex.getErrorInfo(),
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                if (l_dblExtimatedPrice < 0.0D)
                {
                    l_dblExtimatedPrice *= -1D;
                }
                if (l_dblFExtimatedPrice < 0.0D)
                {
                    l_dblFExtimatedPrice =
                        new BigDecimal(String.valueOf(l_dblFExtimatedPrice)).multiply(new BigDecimal("-1")).doubleValue();
                }
                // 概算受渡代金
                l_orderUnitParams.setEstimatedPrice(l_dblExtimatedPrice);
    
                // 概算受渡代金（外貨）
                l_orderUnitParams.setFEstimatedPrice(l_dblFExtimatedPrice);
            }

            // 市場から確認済みの概算受渡代金:概算受渡代金
            l_orderUnitParams.setConfirmedEstimatedPrice(l_orderUnitParams.estimated_price);

            // 市場から確認済みの概算受渡代金（外貨）:概算受渡代金（外貨）
            l_orderUnitParams.setConfirmedFEstimatedPrice(l_orderUnitParams.f_estimated_price);

            // 更新者コード
            l_orderUnitParams.setLastUpdater(l_strLastUpdater);

        }

        // ⑪　@出来ず（失効）の取消の場合:
        else if (this.marketResponseMessage instanceof DefaultUndoOrderInvalidatedMarketResponseMessage)
        {
            // (*1) 失効前注文履歴
            // 1) 失効履歴を取得する。
            OrderAction[] l_orderActions = l_orderUnit.getOrderActions();
            // （注文イベントタイプ==”92：失効済”） && 注文履歴番号が最大の履歴を取得。
            FeqOrderAction l_orderActionMax = null;
            int l_intOrdActSerialNoMax = 0;
            boolean l_blnIsMaxAction = true;
            for (int i = 0; i < l_orderActions.length; i ++)
            {
                FeqOrderAction l_orderAction = (FeqOrderAction)l_orderActions[i];
                if (i == 0)
                {
                    l_intOrdActSerialNoMax = l_orderAction.getOrderActionSerialNo();
                }
                if (OrderEventTypeEnum.EXPIRE.equals(l_orderAction.getOrderEventType()))
                {
                    if (l_blnIsMaxAction)
                    {
                        l_blnIsMaxAction = false;
                        l_orderActionMax = l_orderAction;
                    }
                    int l_intSerialNo = l_orderAction.getOrderActionSerialNo();
                    if (l_intSerialNo > l_intOrdActSerialNoMax)
                    {
                        l_intOrdActSerialNoMax = l_intSerialNo;
                        l_orderActionMax = l_orderAction;
                    }
                }
            }
            if (l_orderActionMax == null)
            {
                log.error(this.getClass().getName() + "." + STR_METHOD_NAME
                    + "データ不整合エラー:失効注文履歴を取得なし。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            // 2) 失効履歴の直前（失効履歴.注文履歴番号 - 1）である注文履歴を
            // 失効前注文履歴とする。
            FeqOrderAction l_orderActionBeforeMax = null;
            for (int i = l_orderActions.length - 1; i >= 0 ; i --)
            {
                FeqOrderAction l_orderAction = (FeqOrderAction)l_orderActions[i];
                int l_intSerialNo = l_orderAction.getOrderActionSerialNo();
                if (l_intSerialNo == l_intOrdActSerialNoMax - 1)
                {
                    l_orderActionBeforeMax = l_orderAction;
                    break;
                }
            }
            if (l_orderActionBeforeMax == null)
            {
                log.error(this.getClass().getName() + "." + STR_METHOD_NAME
                    + "データ不整合エラー:失効前注文履歴を取得なし。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            FeqOrderActionRow l_actionRow = (FeqOrderActionRow)l_orderActionBeforeMax.getDataSourceObject();

            // 概算受渡代金
            if (l_actionRow.getEstimatedPriceIsNull())
            {
                l_orderUnitParams.setEstimatedPrice(null);
            }
            else
            {
                l_orderUnitParams.setEstimatedPrice(l_actionRow.getEstimatedPrice());
            }

            // 概算受渡代金（外貨）
            if (l_actionRow.getFEstimatedPriceIsNull())
            {
                l_orderUnitParams.setFEstimatedPrice(null);
            }
            else
            {
                l_orderUnitParams.setFEstimatedPrice(l_actionRow.getFEstimatedPrice());
            }

            // 市場から確認済みの概算受渡代金
            if (l_actionRow.getConfirmedEstimatedPriceIsNull())
            {
                l_orderUnitParams.setConfirmedEstimatedPrice(null);
            }
            else
            {
                l_orderUnitParams.setConfirmedEstimatedPrice(l_actionRow.getConfirmedEstimatedPrice());
            }
            
            // 市場から確認済みの概算受渡代金（外貨）:概算受渡代金（外貨）
            if (l_actionRow.getConfirmedFEstimatedPriceIsNull())
            {
                l_orderUnitParams.setConfirmedFEstimatedPrice(null);
            }
            else
            {
                l_orderUnitParams.setConfirmedFEstimatedPrice(l_actionRow.getConfirmedFEstimatedPrice());
            }

            // 更新者コード
            l_orderUnitParams.setLastUpdater(l_strLastUpdater);
        }

        log.exiting(STR_METHOD_NAME);
        return l_orderUnitParams;
    }
    
    /**
     * (（注文履歴）更新値設定)<BR>
     * （mutateメソッドの実装）<BR>
     * <BR>
     * 注文履歴Paramsに拡張項目(*)設定し返却する。<BR>
     * <BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。<BR>
     * <BR>
     * １）　@スーパークラスの同メソッド実施。<BR>
     * 　@-super.mutate()を実施する。<BR>
     * <BR>
     * ２）　@訂正失敗の場合（パラメータ.注文履歴Params.注文状態=="発注失敗（変更注文）"の場合のみ、<BR>
     * 　@　@xTrade標準項目の更新仕様をカスタマイズする。<BR>
     * 　@　@※xTrade標準実装では、訂正失敗時には<BR>
     * 　@　@※訂正失敗処理を行う前の注文単位の値が設定されてしまうため。<BR>
     * <BR>
     * 　@　@注文単価（price）：　@注文単位.指値 をセット。<BR>
     * 　@　@注文数量（quantity）：　@注文単位の同項目の値をセット。<BR>
     * @@param l_updateType - (更新タイプ)<BR>
     * 更新タイプ
     * @@param l_context - (処理)<BR>
     * 処理
     * @@param l_feqOrderUnitParams - (注文履歴行)<BR>
     * 注文履歴行（：注文履歴Params）
     * @@return FeqOrderActionParams
     */
    public FeqOrderActionParams mutate(
        OrderManagerPersistenceType l_updateType,
        OrderManagerPersistenceContext l_context,
        FeqOrderActionParams l_feqOrderActionParams)
    {
        final String STR_METHOD_NAME =
              "mutate(OrderManagerPersistenceType, "
            + "OrderManagerPersistenceContext, "
            + "FeqOrderActionParams)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingMod =
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        FeqOrderManager l_orderManager = (FeqOrderManager)l_tradingMod.getOrderManager();               
        FeqOrderUnit l_orderUnit = null;
        try 
        {
            l_orderUnit =
                (FeqOrderUnit)l_orderManager.getOrderUnit(
                    l_feqOrderActionParams.getOrderUnitId());
        } 
        catch (NotFoundException l_nfe) 
        {
            log.error(l_nfe.getMessage(), l_nfe);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        FeqOrderUnitRow l_orderUnitRow =
            (FeqOrderUnitRow)l_orderUnit.getDataSourceObject();
        
        l_feqOrderActionParams =
            super.mutate(
                l_updateType,
                l_context,
                l_feqOrderActionParams);
        
        if (OrderStatusEnum.NOT_MODIFIED.equals(l_feqOrderActionParams.getOrderStatus()))
        {
            l_feqOrderActionParams.setPrice(l_orderUnitRow.getLimitPrice());
            l_feqOrderActionParams.setQuantity(l_orderUnitRow.getQuantity());
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_feqOrderActionParams;
    }
}
@
