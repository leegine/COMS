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
filename	WEB3FeqCancelUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式取消更新インタセプタ(WEB3FeqCancelUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 王亞洲 (中訊) 新規作成
                 : 2005/07/25 芦露(中訊) レビュー
*/

package webbroker3.feq;

import java.math.BigDecimal;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (外国株式取消更新インタセプタ)<BR>
 * 外国株式取消更新インタセプタクラス<BR>
 *
 * @@author 王亞洲
 * @@version 1.0
 */
public class WEB3FeqCancelUpdateInterceptor extends WEB3FeqUpdateInterceptor
{
    /**
     * ログユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqCancelUpdateInterceptor.class);

    /**
     * (代理入力者)<BR>
     * 代理入力者<BR>
     */
    private Trader trader;

    /**
     * (外国株式取消更新イベントインタセプタ)<BR>
     * コンストラクタ<BR>
     * <BR>
     * パラメータの項目を、各プロパティにセットする。<BR>
     * @@param l_trader - (代理入力者)<BR>
     * 代理入力者<BR>
     * @@roseuid 42D0D1FF0242
     */
    public WEB3FeqCancelUpdateInterceptor(Trader l_trader)
    {
        this.trader = l_trader;
    }

    /**
     * (（注文単位）更新値設定)<BR>
     * （mutateメソッドの実装） <BR>
     * <BR>
     * １）　@注文（ﾍｯﾀﾞ）テーブル更新<BR>
     * 　@super.mutate(OrderManagerPersistenceType,<BR>
     * 　@OrderManagerPersistenceContext, FeqOrderUnitParams)を<BR>
     * 　@コールする。<BR>
     * <BR>
     * ２）　@注文単位テーブル更新<BR>
     *   注文単位Paramsに拡張項目(*)設定し返却する。 <BR>
     * <BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。 <BR>
     * <BR>
     * 項目設定内容は、<BR>
     * DB更新仕様「取消_外株注文単位テーブル.xls」参照。<BR>
     * @@param l_updateType - (更新タイプ)<BR>
     * 更新タイプ<BR>
     * @@param l_context - (処理)<BR>
     * 処理<BR>
     * @@param l_feqOrderUnitParams - (注文単位行)<BR>
     * 注文単位行（：注文単位Params）<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams
     * @@roseuid 429B4D520379
     */
    public FeqOrderUnitParams mutate(
        OrderManagerPersistenceType l_updateType,
        OrderManagerPersistenceContext l_context,
        FeqOrderUnitParams l_feqOrderUnitParams)
    {
        final String STR_METHOD_NAME = "mutate(OrderManagerPersistenceType, "
            + "OrderManagerPersistenceContext, FeqOrderUnitParams)";
        log.entering(STR_METHOD_NAME);

        if(l_feqOrderUnitParams == null)
        {
            log.debug(" 注文単位Params値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName()+ "." + STR_METHOD_NAME);
        }
        // １）　@注文（ﾍｯﾀﾞ）テーブル更新
        l_feqOrderUnitParams = super.mutate(l_updateType, l_context, l_feqOrderUnitParams);

        // 注文訂正・取消区分
        if (l_feqOrderUnitParams.getConfirmedQuantityIsNull())
        {
            l_feqOrderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.CANCELED);
        }
        else
        {
            l_feqOrderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.CANCELING);
        }

        // 注文エラー理由コード
        l_feqOrderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);

        // 更新者コード
        // 顧客入力の場合
        if (this.trader == null)
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            AccountManager l_accountMgr = l_finApp.getAccountManager();
            // 顧客コードを取得
            String l_strAccountCode;
            try
            {
                l_strAccountCode = l_accountMgr.getMainAccount(l_feqOrderUnitParams.getAccountId()).getAccountCode();
            }
            catch (NotFoundException l_ex)
            {
                log.error("DBへのアクセスに失敗しました:", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            l_feqOrderUnitParams.setLastUpdater(l_strAccountCode);
        }
        // 代理入力の場合
        else
        {
            l_feqOrderUnitParams.setLastUpdater(this.trader.getTraderCode());
        }
        
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
        String l_strOrderRootDiv =
            l_opLoginSec.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV);
        l_feqOrderUnitParams.setOrderRootDiv(l_strOrderRootDiv);
        
        TradingModule l_tradingModule = 
            GtlUtils.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3FeqOrderManager l_orderManager = 
            (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
        WEB3FeqOrderUnit l_feqOrderUnit =
            (WEB3FeqOrderUnit)l_orderManager.toOrderUnit(l_feqOrderUnitParams);
        if (!l_feqOrderUnit.isUnexecuted())
        {
            WEB3FeqFinTransactionManager l_feqFinTransactionManager =
                (WEB3FeqFinTransactionManager)l_tradingModule.getFinTransactionManager();
            double l_dblEstimatedPrice = 0.0D;
            double l_dblEstimatedPriceFc = 0.0D;
            try
            {
                l_dblEstimatedPrice = l_feqFinTransactionManager.getNetAmount(l_feqOrderUnit);
                l_dblEstimatedPriceFc = l_feqFinTransactionManager.getNetAmountFc(l_feqOrderUnit);
            }
            catch (WEB3BaseException l_wbe)
            {
                log.error(l_wbe.getMessage(), l_wbe);
                throw new WEB3BaseRuntimeException(
                    l_wbe.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_wbe.getMessage(),
                    l_wbe);
            }
            if (l_dblEstimatedPrice < 0.0D)
            {
                l_dblEstimatedPrice *= -1D;
            }
            if (l_dblEstimatedPriceFc < 0.0D)
            {
                l_dblEstimatedPriceFc =
                    new BigDecimal(String.valueOf(l_dblEstimatedPriceFc)).multiply(new BigDecimal("-1")).doubleValue();
            }
            l_feqOrderUnitParams.setEstimatedPrice(l_dblEstimatedPrice);
            l_feqOrderUnitParams.setFEstimatedPrice(l_dblEstimatedPriceFc);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_feqOrderUnitParams;
    }

    /**
     * (get代理入力者)<BR>
     * this.代理入力者を返却する。<BR>
     * @@return Trader
     * @@roseuid 429B4D47007C
     */
    public Trader getTrader()
    {
        return this.trader;
    }
}
@
