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
filename	WEB3FeqChangeUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式訂正更新インタセプタ(WEB3FeqChangeUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14  張玲(中訊) 新規作成
                 : 2005/07/28 呉艶飛(中訊) レビュー
*/

package webbroker3.feq;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderEventTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.util.WEB3LogUtility;


/**
 * (外国株式訂正更新インタセプタ)<BR>
 * 外国株式訂正更新インタセプタクラス<BR>
 * 
 * @@ author 張玲 
 * @@ version 1.0 
 */
public class WEB3FeqChangeUpdateInterceptor extends WEB3FeqUpdateInterceptor 
{
    
    /**
     * (ログユーティリティ)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqChangeUpdateInterceptor.class);
        
    /**
     * (注文内容)<BR>
     * 外国株式訂正注文内容オブジェクト<BR>
     */
    private WEB3FeqChangeOrderSpec orderSpec;
    
    /**
     * (金額計算結果)<BR>
     * 外国株式金額計算結果オブジェクト<BR>
     */
    private WEB3FeqAmountCalcResult amountRound;
    
    /**
     * (計算単価)<BR>
     * 計算単価<BR>
     */
    private double roundPrice;
    
    /**
     * (代理入力者)<BR>
     * 代理入力者<BR>
     */
    private Trader trader;
    
    
    /**
     * @@roseuid 42D0D26B034B
     */
    public WEB3FeqChangeUpdateInterceptor() 
    {
     
    }
    
    /**
     * (外国株式訂正更新イベントインタセプタ)<BR>
     * コンストラクタ<BR>
     * <BR>
     * パラメータの項目を、各プロパティにセットする。<BR>
     * @@param l_orderSpec - (注文内容)<BR>
     * 注文内容
     * 
     * @@param l_amountRound - (計算結果)
     * 計算結果
     * 
     * @@param l_dblRoundPrice - (計算単価)
     * 計算単価
     * 
     * @@param l_trader - (代理入力者)
     * 代理入力者
     * @@roseuid 42975C180129
     */
    public WEB3FeqChangeUpdateInterceptor(
        WEB3FeqChangeOrderSpec l_orderSpec, 
        WEB3FeqAmountCalcResult l_amountRound, 
        double l_dblRoundPrice, 
        Trader l_trader) 
    {
        this.orderSpec = l_orderSpec;
        this.amountRound = l_amountRound;
        this.roundPrice = l_dblRoundPrice;
        this.trader = l_trader;
    }
    
    /**
     * (（注文単位）更新値設定)<BR>
     * （mutateメソッドの実装） <BR>
     * <BR>
     * １）　@注文（ﾍｯﾀﾞ）テーブル更新<BR>
     * 　@super.mutate(OrderManagerPersistenceType,<BR>
     * 　@ OrderManagerPersistenceContext, FeqOrderUnitParams)<BR>
     * 　@をコールする。<BR>
     * <BR>
     * ２）　@注文単位テーブル更新<BR>
     * 　@注文単位Paramsに拡張項目(*)設定し返却する。 <BR>
     * <BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。<BR> 
     * <BR>
     * 項目設定内容は、<BR>
     * DB更新仕様「訂正_外株注文単位テーブル.xls」参照。<BR>
     * @@param l_updateType - (更新タイプ)<BR>
     * 更新タイプ
     * @@param l_context - (処理)<BR>
     * 処理
     * @@param l_feqOrderUnitParams - (注文単位行)<BR>
     * 注文単位行（：注文単位Params）
     * @@return FeqOrderUnitParams
     * @@roseuid 42975C1401E4
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
        
        if (l_feqOrderUnitParams == null)
        {
            log.debug("注文単位ParamsがNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);            
        }    
        
        //引数.更新タイプ != nullの場合、注文（ﾍｯﾀﾞ）テーブル更新を行う
        if (l_updateType != null)
        {
            //１）　@注文（ﾍｯﾀﾞ）テーブル更新
            l_feqOrderUnitParams = super.mutate(
                l_updateType,
                l_context,
                l_feqOrderUnitParams);
        }
                
        //２）　@注文単位テーブル更新                    
        //執行条件
        //注文内容.getExecConditionType( )
        l_feqOrderUnitParams.setExecutionConditionType(this.orderSpec.getChangeExecutionCondition());
        //発注条件演算子
        //注文内容.get訂正発注条件演算子()
        l_feqOrderUnitParams.setOrderCondOperator(this.orderSpec.getChangeOrderCondOperator());
        
        //注文内容.get訂正発注条件単価()
        //（* 発注条件＝0：DEFAULT（条件指定なし）の場合は、null）
        if (WEB3OrderingConditionDef.DEFAULT.equals(this.orderSpec.getOrderConditionType()))
        {
            l_feqOrderUnitParams.setStopOrderPrice(null);
        }
        else
        {
            l_feqOrderUnitParams.setStopOrderPrice(this.orderSpec.getChangeOrderCondPrice());
        }

        //注文内容.get訂正（W指値）訂正指値()
        //（* 発注条件＝（0：DEFAULT（条件指定なし）、1：逆指値）の場合は、null）
        if (WEB3OrderingConditionDef.DEFAULT.equals(this.orderSpec.getOrderConditionType())
            || WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(this.orderSpec.getOrderConditionType()))
        {
            l_feqOrderUnitParams.setWLimitPrice(null);
        }
        else
        {
            l_feqOrderUnitParams.setWLimitPrice(this.orderSpec.getChangeWLimitPrice());
        }

        //注文失効日付  
        //注文内容.get訂正注文有効期限( )
        if (this.orderSpec.getChangeOrderExpirationDate() != null)
        {        
            l_feqOrderUnitParams.setExpirationDate(this.orderSpec.getChangeOrderExpirationDate());
        }
        //注文状態   
        if (l_feqOrderUnitParams.getConfirmedQuantityIsNull())
        {
            l_feqOrderUnitParams.setOrderStatus(OrderStatusEnum.MODIFIED);
        }
        else
        {
            l_feqOrderUnitParams.setOrderStatus(OrderStatusEnum.MODIFY_ACCEPTED);
        }
        //注文単価   
        //インタセプタ.get注文単価( )（* 概算受渡代金算出に使用した単価）               
        l_feqOrderUnitParams.setPrice(this.roundPrice);
        //概算受渡代金    
        //金額計算結果.get受渡代金( )    
        l_feqOrderUnitParams.setEstimatedPrice(this.amountRound.getNetAmount());
        //概算受渡代金（外貨） 
        //金額計算結果.get受渡代金（外貨）( )       
        l_feqOrderUnitParams.setFEstimatedPrice(this.amountRound.getNetAmountFc());
        //注文訂正・取消区分  
        if (l_feqOrderUnitParams.getConfirmedQuantityIsNull())
        {
            WEB3FeqOrderUnit l_orderUnit = new WEB3FeqOrderUnit(l_feqOrderUnitParams);
            if (l_orderUnit.isUnexecuted())
            {
                l_feqOrderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.CHANGED);
            }
            else
            {
                l_feqOrderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.PARTIALLY_CHANGED);
            }
        }
        else
        {
            l_feqOrderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.CHANGING);
        }
        
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);   

        //注文経路区分 
        //セッションより取得した同項目の値
      
        String l_orderRootDiv ="";
        String l_orderRootDivString = WEB3SessionAttributeDef.ORDER_ROOT_DIV;
        l_orderRootDiv = l_opLoginSec.getSessionProperty(l_orderRootDivString);
        l_feqOrderUnitParams.setOrderRootDiv(l_orderRootDiv);

        //注文エラー理由コード
        //0000：正常
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
                log.error("該当するデータがありません。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
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
        
        log.exiting(STR_METHOD_NAME);
        return l_feqOrderUnitParams;
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
     * ２）　@以下の手続きを実施する。<BR>
     * 　@・ステイタス変更<BR>
     * 　@　@-パラメータ.注文履歴Params.注文イベントタイプに”変更注文”をセットする。<BR>
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
        
        l_feqOrderActionParams =
            super.mutate(
                l_updateType,
                l_context,
                l_feqOrderActionParams);
        
        l_feqOrderActionParams.setOrderEventType(OrderEventTypeEnum.CHANGE);
        
        log.exiting(STR_METHOD_NAME);
        return l_feqOrderActionParams;
    }
    
    /**
     * (get注文内容)<BR>
     * this.注文内容を返却する。<BR>
     * @@return WEB3FeqChangeOrderSpec
     * @@roseuid 4299E919029F
     */
    public WEB3FeqChangeOrderSpec getOrderSpec() 
    {
        return this.orderSpec;
    }
    
    /**
     * (get金額計算結果)<BR>
     * this.金額計算結果を返却する。<BR>
     * @@return WEB3FeqAmountCalcResult
     * @@roseuid 4299E91902A0
     */
    public WEB3FeqAmountCalcResult getAmountRound() 
    {
        return this.amountRound;
    }
    
    /**
     * (get計算単価)<BR>
     * this.計算単価を返却する。<BR>
     * @@return double
     * @@roseuid 4299E91902A1
     */
    public double getRoundPrice() 
    {
        return this.roundPrice;
    }
    
    /**
     * (get代理入力者)<BR>
     * this.代理入力者を返却する。<BR>
     * @@return Trader
     * @@roseuid 4299ECAE02ED
     */
    public Trader getTrader() 
    {
        return this.trader;
    }
}
@
