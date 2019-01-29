head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityOrderExecNotifyInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式出来通知インタセプタ(WEB3EquityOrderExecNotifyInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/01 SRA水落 新規作成
                 : 2006/11/03 柴双紅 (中訊) ＤＢ更新仕様No.181,モデルNo.1040,モデルNo.1026
                 : 2006/11/29 唐性峰 (中訊) モデルNo.1071,ＤＢ更新仕様No.189
*/

package webbroker3.equity;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderManager;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderExecutionParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3PriceConditionDef;
import webbroker3.common.define.WEB3RequestTypeDef;
import webbroker3.equity.define.WEB3EquityWlimitEnableStatusDivDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.util.WEB3LogUtility;

/**
 * （株式出来通知インタセプタ）。<BR>
 * <BR>
 * 現物株式出来通知時の、DB更新仕様カスタマイズ用のクラス。<BR> 
 *（EqTypeOrderManagerPersistenceEventInterceptorの実装）
 * @@author  : SRA水落
 * @@version : 1.0
 */
public class WEB3EquityOrderExecNotifyInterceptor extends WEB3EquityOrderManagerPersistenceEventInterceptor
{
    /**
     * (ログユーティリティ)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityOrderExecNotifyInterceptor.class);
    
    /**
     * (約定日時)<BR>
     * 約定日時。<BR>
     */
    private Date execTimestamp;
    
    /**
     * (約定単価)<BR>
     * 約定単価。<BR>
     */
    private double executedPrice;
    
    /**
     * (約定株数)<BR>
     * 約定株数。<BR>
     */
    private double execQuantity;
    
    /**
     * (株式出来通知インタセプタ)<BR>
     * コンストラクタ。<BR>
     * <BR>
     * @@param l_dateExecutedTimestamp （約定日時）<BR>
     * 【株式出来通知キューテーブル】約定日時。<BR>
     * @@param l_dblExecutedPrice 約定単価<BR>
     * 約定単価。<BR>
     * @@param l_dblExecQuantity 約定株数<BR>
     * 約定株数。<BR>
     * @@return WEB3EquityOrderExecNotifyInterceptor
     */
    public WEB3EquityOrderExecNotifyInterceptor(
        Date l_dateExecutedTimestamp,
        double l_dblExecutedPrice, 
        double l_dblExecQuantity) 
    {
        this.execTimestamp  = l_dateExecutedTimestamp;
        this.executedPrice = l_dblExecutedPrice;
        this.execQuantity = l_dblExecQuantity;
    }
    
    /**
     * (更新値設定)<BR>
     * （mutateメソッドの実装） <BR>
     * 株式約定Paramsに拡張項目(*)設定し返却する。 <BR>
     * <BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。 <BR>
     * <BR>
     * １）　@約定取消（引数の処理＝EqTypeOrderManagerPersistenceContext.UNDO_EXECUTION） <BR>
     * 　@　@　@の場合は、何もせずに処理を終了する。 <BR>
     * <BR>
     * ２）　@カスタマイズでの値セットを行う。 <BR>
     * <BR>
     * ２－１）　@約定対象の注文単位オブジェクトを取得する。 <BR>
     * <BR>
     * 　@　@　@引数の株式約定Params.注文単位IDに該当する注文単位オブジェクトを取得する。 <BR>
     * <BR>
     * ２－２）　@株式約定Paramsのプロパティ設定仕様をカスタマイズする。 <BR>
     * <BR>
     * 　@　@　@当クラスのプロパティ、及び２－１）で取得した注文単位オブジェクトより、 <BR>
     * 　@　@　@株式約定Paramsのプロパティを設定する。 <BR>
     * <BR>
     * 　@　@　@更新内容はDB設定論理「（約定）出来通知_約定テーブル.xls」参照。 <BR>
     * <BR>
     * ３）　@引数の株式約定Paramsを返却する。 <BR>
     *      * <BR>
     * <BR>
     * @@param l_updateType - INSERTまたは、UPDATE。 <BR>
     * EqTypeOrderManagerPersistenceTypeにて定数定義。 <BR>
     * <BR> 
     * @@param l_process - 処理<BR>
     * （EqTypeOrderManagerPersistenceContextにて定数定義）<BR>
     * @@param l_orderExecParams - 株式約定Params<BR>
     * 株式約定が保持する項目のパラメータクラス。<BR>
     * @@return EqtypeOrderExecutionParams
     */
    public EqtypeOrderExecutionParams mutate(
        OrderManagerPersistenceType l_updateType, 
        OrderManagerPersistenceContext l_process, 
        EqtypeOrderExecutionParams l_orderExecParams) 
    {
        final String STR_METHOD_NAME = "WEB3EquityOrderExecNotifyInterceptor:mutate()";
        log.entering(STR_METHOD_NAME);

        //１）　@約定取消（引数の処理＝OrderManagerPersistenceContext.UNDO_EXECUTION）
        //　@　@　@の場合は、何もせずに処理を終了する。
        if (OrderManagerPersistenceContext.UNDO_EXECUTION.equals(l_process))
        {
            log.exiting(STR_METHOD_NAME);
            return l_orderExecParams;
        }
        
        //２）　@カスタマイズでの値セットを行う
        //２－１）　@約定対象の注文単位オブジェクトを取得する
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        EqTypeOrderManager l_orderManager = (EqTypeOrderManager) l_tradingMod.getOrderManager();               

        OrderUnit l_orderUnit = null;
        EqtypeOrderUnitRow l_orderUnitRow = null;
        try
        {
            l_orderUnit = (EqTypeOrderUnit)l_orderManager.getOrderUnit(
                l_orderExecParams.getOrderUnitId());
            l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        }
        catch (NotFoundException l_nfe)
        {   
            log.error(STR_METHOD_NAME, l_nfe);   
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + STR_METHOD_NAME, 
                l_nfe.getMessage(), l_nfe);
        }

        //２－２）　@株式約定Paramsのプロパティ設定仕様をカスタマイズする
        //受渡日をセット
        l_orderExecParams.setDeliveryDate(l_orderUnitRow.getDeliveryDate());
        
        //約定日時をセット
        l_orderExecParams.setExecTimestamp(this.execTimestamp);
        
        //発注日をセット
        l_orderExecParams.setBizDate(l_orderUnitRow.getBizDate());
        
        //識別コードをセット
        l_orderExecParams.setOrderRequestNumber(l_orderUnitRow.getOrderRequestNumber());

        log.exiting(STR_METHOD_NAME);
        return l_orderExecParams;
    }    
    
    
    /**
     * (更新値設定) <BR>
     * （mutateメソッドの実装） <BR>
     * 注文単位Paramsに拡張項目(*)設定し返却する。<BR>
     * ※概算受渡代金は、当メソッド内で再計算した値をセットする。<BR>
     * <BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。<BR>
     * (*) xTradeマニュアル「更新内容のカスタマイズ」参照。<BR>
     * <BR>
     * １）　@拡張項目セット<BR>
     * <BR>
     * 　@各プロパティから、<BR>
     * 　@パラメータ.注文単位Paramsの拡張項目に値をセットする。<BR>
     * <BR>
     * 　@更新内容は、以下のDB設定論理を参照。<BR>
     * 　@・「（約定）出来通知_株式注文単位テーブル.xls」<BR>
     * 　@・「（約定取消）出来通知_株式注文単位テーブル.xls」<BR>
     * <BR>
     * ２）　@概算受渡代金を再計算し、パラメータ.注文単位Paramsにセットする。<BR>
     * <BR>
     * ２－１）再オープン(※)かつ株式データアダプタ.getＷ指値用有効状態区分＝"リミット注文有効" の場合<BR>
     *　@　@（※更新前.注文有効状態=="クローズ" かつ更新後.注文有効状態=="オープン"の場合、再オープン）<BR>
     * <BR>
     *　@　@概算受渡代金を再計算する。<BR>
     * <BR>
     *　@　@２－１－１）補助口座を取得する。<BR>
     *　@　@　@拡張アカウントマネージャ.get補助口座をコールし、補助口座を取得する。<BR>
     *　@　@　@[引数]<BR>
     *　@　@　@　@引数の注文単位Params.口座ID<BR>
     *　@　@　@　@引数の注文単位Params.補助口座ID<BR>
     * <BR>
     *　@　@２－１－２）ストップ注文失効時の概算受渡代金を再計算する。<BR>
     *　@　@　@拡張株式注文マネージャ.getストップ注文失効時概算代金計算結果()をコールする。<BR>
     *　@　@　@[引数]<BR>
     *　@　@　@　@注文単位<BR>
     *　@　@　@　@２－１－１）で取得した補助口座<BR>
     * <BR>
     * ２－２）getストップ注文失効時概算代金計算結果()！＝nullの場合<BR>
     * <BR>
     *　@　@　@　@　@以下の値を注文単位Paramsにセットし、注文単位Paramを返却する。<BR>
     * <BR>
     *　@　@　@　@　@注文単位Params.{概算受渡代金,市場から確認済みの概算受渡代金}に、<BR>
     *　@　@　@　@　@getストップ注文失効時概算代金計算結果()の戻り値.get概算受渡代金をセットする。<BR>
     * <BR>
     *　@　@　@　@　@注文単位Params.｛注文単価、市場から確認済の注文単価｝に、<BR>
     *　@　@　@　@　@getストップ注文失効時概算代金計算結果()の戻り値.get計算単価()の戻り値をセットする。<BR>
     * <BR>
     * ２－３）２－２）以外の場合、以下の処理を行う。<BR>
     * 　@２－３－１）　@手数料オブジェクトを生成する。<BR>
     * <BR>
     * 　@　@株式計算サービス.create手数料(引数の注文単位Params.注文ID)をコールする。<BR>
     * <BR>
     * 以下、引数にセットする注文単位Paramsとして、<BR>
     * 「１）　@拡張項目セット」後の注文単位Paramsを使用する。<BR>
     * <BR>
     * 　@２－３－2）　@概算受渡代金を再計算し、注文単位Paramsにセットする。<BR>
     * <BR>
     * 　@　@拡張株式注文マネージャ.calc概算受渡代金()をコールする。<BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * 　@　@　@＜calc概算受渡代金()：引数設定仕様＞<BR>
     * <BR>
     * 　@　@　@手数料：　@作成した手数料オブジェクト<BR>
     * 　@　@　@指値：　@注文単位Params.指値<BR>
     * 　@　@　@（W指値)訂正指値：　@注文単位Params.（W指値）訂正指値<BR>
     * 　@　@　@逆指値基準値：　@注文単位Params.逆指値基準値 <BR>
     * 　@　@　@執行条件：　@注文単位Params.執行条件<BR>
     * 　@　@　@（W指値）執行条件：　@注文単位Params.（W指値）執行条件<BR>
     * 　@　@　@値段条件：　@注文単位Params.値段条件<BR>
     * 　@　@　@発注条件：　@注文単位Params.発注条件<BR>
     * 　@　@　@確認時取得時価：　@null<BR>
     * 　@　@　@isストップ注文有効：　@拡張株式注文マネージャ.isストップ注文有効(注文単位)<BR>
     * 　@　@　@補助口座：　@注文単位Params.｛口座ID, 補助口座ID｝に該当する補助口座 <BR>
     * 　@　@　@取引銘柄：　@注文単位.getTradedProduct() <BR>
     * 　@　@　@株数：　@注文単位Params.市場から確認済みの数量<BR>
     * 　@　@　@is売注文：　@拡張株式注文マネージャ.is売注文(注文単位)<BR>
     * 　@　@　@約定数量：　@注文単位Params.約定数量<BR>
     * 　@　@　@合計約定金額：　@注文単位Params.合計約定金額<BR>
     * 　@　@　@isSkip金額チェック：　@true（上限金額チェックなし）<BR>
     * <BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * <BR>
     * 　@　@注文単位Params.｛概算受渡代金、市場から確認済の概算受渡代金｝に、<BR>
     * 　@　@calc概算受渡代金()の戻り値オブジェクト.get概算受渡代金()の戻り値をセットする。<BR>
     * <BR>
     * 　@　@注文単位Params.｛注文単価、市場から確認済の注文単価｝に、<BR>
     * 　@　@calc概算受渡代金()の戻り値オブジェクト.get計算単価()の戻り値をセットする。<BR>
     * 　@　@（※注文単価/市場から確認済みの注文単価セットは、全部約定以外（未約定／一部約定）のみ実行。）<BR>
     * <BR>
     * ３）　@プロパティをセットした注文単位Paramsを返却する。<BR>
     * <BR>
     * @@param l_orderManagerPersistenceType - INSERTまたは、UPDATE。 <BR>
     * <BR>
     * @@param l_orderManagerPersistenceContext - (処理) <BR>
     *（OrderManagerPersistenceContextにて定数定義） <BR>
     * <BR>
     * @@param l_eqtypeOrderUnitParams - (注文単位Params) <BR>
     * 株式注文単位が保持する項目のパラメータクラス。 <BR>
     * @@return EqtypeOrderUnitParams
     */
    public EqtypeOrderUnitParams mutate(
        OrderManagerPersistenceType l_orderManagerPersistenceType,
        OrderManagerPersistenceContext l_orderManagerPersistenceContext,
        EqtypeOrderUnitParams l_eqtypeOrderUnitParams)
    {
        final String STR_METHOD_NAME = 
            "mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, EqtypeOrderUnitParams)";
        log.entering(STR_METHOD_NAME);

        //------------------------
        // パラメータnullチェック
        //------------------------
        if (l_eqtypeOrderUnitParams == null)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // 注文単位取得
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        
        TradingModule l_tradingMod =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        EqTypeOrderManager l_orderManager =
            (EqTypeOrderManager)l_tradingMod.getOrderManager();
        EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_orderManager.toOrderUnit(l_eqtypeOrderUnitParams);
        WEB3EquityOrderManager l_eqtypeOrderManager = (WEB3EquityOrderManager)l_orderManager;
        //isストップ注文有効
        boolean l_blnStopOrderValid = false;
        //is売注文
        boolean l_blnSellOrder = false;
        try
        {
            l_blnStopOrderValid = l_eqtypeOrderManager.isStopOrderValid(l_orderUnit);
            l_blnSellOrder = l_eqtypeOrderManager.isSellOrder(l_orderUnit);
        }
        catch (WEB3BaseException l_exc)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_exc.getMessage(),
                l_exc);
        }

        //更新後.注文有効状態
        OrderOpenStatusEnum l_orderOpenStatusAftUpdate =
            l_eqtypeOrderUnitParams.getOrderOpenStatus();
        //更新前の注文単位
        EqTypeOrderUnit l_orderUnitBefUpdate = null;
        try
        {
            l_orderUnitBefUpdate =
                (EqTypeOrderUnit)l_eqtypeOrderManager.getOrderUnit(l_orderUnit.getOrderUnitId());
        }
        catch (NotFoundException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + STR_METHOD_NAME);
        }
        //更新前.注文有効状態
        OrderOpenStatusEnum l_orderOpenStatusBefUpdate =
            l_orderUnitBefUpdate.getOrderOpenStatus();
        //株式データアダプタ.getＷ指値用有効状態区分()
        String l_strWLimitEnableStatusDiv = null;
        try
        {
            l_strWLimitEnableStatusDiv =
                WEB3EquityDataAdapter.getWLimitEnableStatusDiv(l_orderUnitBefUpdate);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        // 約定取消の場合
        if (OrderManagerPersistenceContext.UNDO_EXECUTION.equals(l_orderManagerPersistenceContext))
        {            
            //---------------------------
            //指値／市場から確認済みの指値
            //---------------------------
            //値段条件＝"成行残数指値" かつ
            //約定数量==0（約定なしとなる約定取消）の場合：0
            //上記以外の場合：　@（既存値）
            if (
                WEB3PriceConditionDef.PARTIALLY_LIMIT_PRICE_ORDER
                    .equals(l_eqtypeOrderUnitParams.getPriceConditionType()) &&
                l_eqtypeOrderUnitParams.getExecutedQuantity() == 0)
            {
                // 指値
                l_eqtypeOrderUnitParams.setLimitPrice(0.0D);
                // 市場からの確認済みの指値
                l_eqtypeOrderUnitParams.setConfirmedPrice(0.0D);
            }

            //　@(*1)株式データアダプタ.getＷ指値用有効状態区分()＝"リミット注文有効"の場合、リミット注文有効。
            //　@引数に設定する注文単位には、更新前の注文単位を指定する
            //　@（EqtypeOrderUnitParamsを拡張株式注文マネージャ.toOrderUnit()にて注文単位型にする）
            //　@(*2)更新前.注文有効状態=="クローズ" かつ更新後.注文有効状態=="オープン"の場合、再オープン。
            //リミット注文有効(*1)かつ再オープン(*2)の場合
            if (WEB3EquityWlimitEnableStatusDivDef.LIMIT_ENABLE.equals(l_strWLimitEnableStatusDiv)
                && OrderOpenStatusEnum.CLOSED.equals(l_orderOpenStatusBefUpdate)
                && OrderOpenStatusEnum.OPEN.equals(l_orderOpenStatusAftUpdate))
            {
                //元発注条件     org_order_condition_type
                //リミット注文有効(*1)かつ再オープン(*2)の場合： 更新前の発注条件
                //以外の場合：（既存値）
                l_eqtypeOrderUnitParams.setOrgOrderConditionType(
                    l_eqtypeOrderUnitParams.getOrderConditionType());

                //元発注条件演算子      org_order_cond_operator
                //リミット注文有効(*1)かつ再オープン(*2)の場合： 更新前の発注条件演算子
                //以外の場合：（既存値）
                l_eqtypeOrderUnitParams.setOrgOrderCondOperator(
                    l_eqtypeOrderUnitParams.getOrderCondOperator());

                //元逆指値基準値       org_stop_order_price
                //リミット注文有効(*1)かつ再オープン(*2)の場合： 更新前の逆指値基準値
                //以外の場合：（既存値）
                if (l_eqtypeOrderUnitParams.getStopOrderPriceIsNull())
                {
                    l_eqtypeOrderUnitParams.setOrgStopOrderPrice(null);
                }
                else
                {
                    l_eqtypeOrderUnitParams.setOrgStopOrderPrice(
                        l_eqtypeOrderUnitParams.getStopOrderPrice());
                }

                //元（W指値）訂正指値        org_w_limit_price
                //リミット注文有効(*1)かつ再オープン(*2)の場合： 更新前の（W指値）訂正指値
                //以外の場合：（既存値）
                if (l_eqtypeOrderUnitParams.getWLimitPriceIsNull())
                {
                    l_eqtypeOrderUnitParams.setOrgWLimitPrice(null);
                }
                else
                {
                    l_eqtypeOrderUnitParams.setOrgWLimitPrice(
                        l_eqtypeOrderUnitParams.getWLimitPrice());
                }

                //元（W指値）執行条件    org_w_limit_exec_cond_type
                //リミット注文有効(*1)かつ再オープン(*2)の場合： 更新前の（W指値）執行条件
                //以外の場合：（既存値）
                l_eqtypeOrderUnitParams.setOrgWLimitExecCondType(
                    l_eqtypeOrderUnitParams.getWLimitExecCondType());

                //（W指値）執行条件        w_limit_exec_cond_type
                //リミット注文有効(*1)かつ再オープン(*2)の場合： null
                //以外の場合：（既存値）
                l_eqtypeOrderUnitParams.setWLimitExecCondType(null);

                //発注条件   order_condition_type
                //リミット注文有効(*1)かつ再オープン(*2)の場合：　@0:DEFAULT
                //以外の場合：（既存値）
                l_eqtypeOrderUnitParams.setOrderConditionType(WEB3OrderingConditionDef.DEFAULT);

                //発注条件演算子   order_cond_operator
                //リミット注文有効(*1)かつ再オープン(*2)の場合：　@null
                //以外の場合：（既存値）
                l_eqtypeOrderUnitParams.setOrderCondOperator(null);

                //逆指値基準値       stop_order_price
                //リミット注文有効(*1)かつ再オープン(*2)の場合：　@null
                //以外の場合：（既存値）
                l_eqtypeOrderUnitParams.setStopOrderPrice(null);

                //（W指値）訂正指値      w_limit_price
                //リミット注文有効(*1)かつ再オープン(*2)の場合：　@null
                //以外の場合：（既存値）
                l_eqtypeOrderUnitParams.setWLimitPrice(null);

                //リクエストタイプ        request_type
                //リミット注文有効(*1)かつ再オープン(*2)の場合：　@5:失効
                //以外の場合：（既存値）
                l_eqtypeOrderUnitParams.setRequestType(WEB3RequestTypeDef.INVALIDATE);
            }
        }
        // 約定の場合
        else
        {
            //---------------------------
            //指値／市場から確認済みの指値
            //---------------------------
            //値段条件＝"成行残数指値"
            //かつ 初回約定（約定数量==インタセプタのプロパティ.約定株数）
            //かつ 一部出来（注文単位.isPartiallyExecuted( )==true）の場合：
            //　@インタセプタのプロパティ.約定単価。
            //上記以外の場合：　@（既存値）
            if (WEB3PriceConditionDef.PARTIALLY_LIMIT_PRICE_ORDER.equals(l_eqtypeOrderUnitParams.getPriceConditionType()) &&
                l_orderUnit.isMarketOrder() &&
                l_orderUnit.isPartiallyExecuted())
            { 
                // 指値
                l_eqtypeOrderUnitParams.setLimitPrice(this.executedPrice);
                // 市場から確認済みの指値
                l_eqtypeOrderUnitParams.setConfirmedPrice(this.executedPrice);
            }

            // 注文訂正・取消区分
            l_eqtypeOrderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.INITIAL_VALUE);
        }

        WEB3EquityEstimatedPrice l_stopOrderExpireEstimatedPrice = null;
        //　@２－１）再オープン(※)かつ株式データアダプタ.getＷ指値用有効状態区分＝"リミット注文有効" の場合
        //　@　@（※更新前.注文有効状態=="クローズ" かつ更新後.注文有効状態=="オープン"の場合、再オープン）
        //
        //　@　@概算受渡代金を再計算する。
        if (WEB3EquityWlimitEnableStatusDivDef.LIMIT_ENABLE.equals(l_strWLimitEnableStatusDiv)
            && OrderOpenStatusEnum.CLOSED.equals(l_orderOpenStatusBefUpdate)
            && OrderOpenStatusEnum.OPEN.equals(l_orderOpenStatusAftUpdate))
        {
            //　@　@２－１－１）補助口座を取得する。
            //　@　@　@拡張アカウントマネージャ.get補助口座をコールし、補助口座を取得する。
            //　@　@　@[引数]
            //　@　@　@　@引数の注文単位Params.口座ID
            //　@　@　@　@引数の注文単位Params.補助口座ID
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            SubAccount l_subAccount = null;
            try
            {
                l_subAccount =
                    l_accountManager.getSubAccount(
                        l_eqtypeOrderUnitParams.getAccountId(),
                        l_eqtypeOrderUnitParams.getSubAccountId());
            }
            catch (NotFoundException l_nfe)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe);
            }

            //　@　@２－１－２）ストップ注文失効時の概算受渡代金を再計算する。
            //　@　@　@拡張株式注文マネージャ.getストップ注文失効時概算代金計算結果()をコールする。
            //　@　@　@[引数]
            //　@　@　@　@注文単位
            //　@　@　@　@２－１－１）で取得した補助口座
            try
            {
                l_stopOrderExpireEstimatedPrice =
                    l_eqtypeOrderManager.getStopOrderExpireEstimatedPrice(
                        l_orderUnit,
                        l_subAccount);
            }
            catch (WEB3BaseException l_ex)
            {
                log.debug(STR_METHOD_NAME, l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    l_ex.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }

        //　@２－２）getストップ注文失効時概算代金計算結果()！＝nullの場合
        //
        //　@　@　@　@　@以下の値を注文単位Paramsにセットし、注文単位Paramを返却する。
        //
        //　@　@　@　@　@注文単位Params.{概算受渡代金,市場から確認済みの概算受渡代金}に、
        //　@　@　@　@　@getストップ注文失効時概算代金計算結果()の戻り値.get概算受渡代金をセットする。
        //
        //　@　@　@　@　@注文単位Params.｛注文単価、市場から確認済の注文単価｝に、
        //　@　@　@　@　@getストップ注文失効時概算代金計算結果()の戻り値.get計算単価()の戻り値をセットする。
        if (l_stopOrderExpireEstimatedPrice != null)
        {
            l_eqtypeOrderUnitParams.setEstimatedPrice(
                l_stopOrderExpireEstimatedPrice.getEstimateDeliveryAmount());
            l_eqtypeOrderUnitParams.setConfirmedEstimatedPrice(
                l_stopOrderExpireEstimatedPrice.getEstimateDeliveryAmount());
            l_eqtypeOrderUnitParams.setPrice(
                l_stopOrderExpireEstimatedPrice.getCalcUnitPrice());
            l_eqtypeOrderUnitParams.setConfirmedOrderPrice(
                l_stopOrderExpireEstimatedPrice.getCalcUnitPrice());

            log.exiting(STR_METHOD_NAME);
            return l_eqtypeOrderUnitParams;
        }

        //　@２－３）２－２）以外の場合、以下の処理を行う。
        // 概算受渡代金の再計算を行う
        try
        {
	        WEB3EquityBizLogicProvider l_bizLogic =
	            (WEB3EquityBizLogicProvider)l_tradingMod.getBizLogicProvider();
	        // 手数料オブジェクトの作成
	        WEB3GentradeCommission l_commission =
	            l_bizLogic.createCommission(l_eqtypeOrderUnitParams.getOrderId());
	        
            // 補助口座の取得
            WEB3GentradeAccountManager l_accManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount)l_accManager.getSubAccount(
                    l_eqtypeOrderUnitParams.getAccountId(),
                    l_eqtypeOrderUnitParams.getSubAccountId());

            // 概算受渡代金の再計算
            WEB3EquityEstimatedDeliveryPrice l_calcResult =
                l_eqtypeOrderManager.calcEstimateDeliveryAmount(
                    l_commission,
                    l_eqtypeOrderUnitParams.getLimitPrice(),
                    l_eqtypeOrderUnitParams.getWLimitPrice(),
                    l_eqtypeOrderUnitParams.getStopOrderPrice(),
                    l_eqtypeOrderUnitParams.getExecutionConditionType(),
                    l_eqtypeOrderUnitParams.getWLimitExecCondType(),
                    l_eqtypeOrderUnitParams.getPriceConditionType(),
                    l_eqtypeOrderUnitParams.getOrderConditionType(),
                    null,
                    l_blnStopOrderValid,
                    l_subAccount,
                    (WEB3EquityTradedProduct)l_orderUnit.getTradedProduct(),
                    l_eqtypeOrderUnitParams.getConfirmedQuantity(),
                    l_blnSellOrder,
                    l_eqtypeOrderUnitParams.getExecutedQuantity(),
                    l_eqtypeOrderUnitParams.getExecutedAmount(),
                    true);

            // 概算受渡代金、市場から確認済の概算受渡代金のセット
            l_eqtypeOrderUnitParams.setEstimatedPrice(l_calcResult.getEstimateDeliveryAmount());
            l_eqtypeOrderUnitParams.setConfirmedEstimatedPrice(l_calcResult.getEstimateDeliveryAmount());

            if (!l_orderUnit.isFullyExecuted())
            {
                //注文単価、市場から確認済の注文単価
                l_eqtypeOrderUnitParams.setPrice(l_calcResult.getCalcUnitPrice());
                l_eqtypeOrderUnitParams.setConfirmedOrderPrice(l_calcResult.getCalcUnitPrice());
            }
        }
        catch (WEB3BaseException l_be)
        {
            throw new WEB3BaseRuntimeException(
                l_be.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_be);
        }
        catch (Exception l_ex)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage());
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_eqtypeOrderUnitParams;
    }
}@
