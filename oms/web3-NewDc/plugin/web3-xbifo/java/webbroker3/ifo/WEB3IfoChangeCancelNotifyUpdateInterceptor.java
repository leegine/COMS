head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoChangeCancelNotifyUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP訂正取消通知更新インタセプタ(WEB3IfoChangeCancelNotifyUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/6/11 盧法@旭 (中訊) 新規作成
Revesion History : 2004/07/23 王暁傑 (中訊) WEB3ModifyCancelTypeDefでWEB3IfoModifyCancelTypeDefを差し替える
Revesion History : 2006/07/12 郭英 (中訊) DB更新仕様No.085, 099
Revesion History : 2006/11/29 周捷 (中訊) DB更新仕様No.124、125、126、127
Revesion History : 2007/01/25 張騰宇 (中訊) モデルNo.611 DB更新仕様No.145、153
*/

package webbroker3.ifo;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CanmodReceiptTypeDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3ModifiedResultDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3RequestTypeDef;
import webbroker3.util.WEB3LogUtility;

import webbroker3.ifo.define.WEB3IfoCanmodReceiptTypeDef;
import webbroker3.ifo.define.WEB3IfoWLimitEnableStatusDivDef;

/**
 * (先物OP訂正取消通知更新インタセプタ)<BR>
 * 先物OP訂正取消通知更新インタセプタクラス<BR>
 * @@author  盧法@旭
 * @@version 1.0
 */
public class WEB3IfoChangeCancelNotifyUpdateInterceptor extends WEB3IfoUpdateInterceptor
{
    /**
      * ログ出力ユーティリティ。
      */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3IfoChangeCancelNotifyUpdateInterceptor.class);
    /**
     * 訂正後数量<BR>
     */
    private double changedQuantity;

    /**
     * 訂正後指値<BR>
     */
    private double changedLimitPrice;

    /**
     * 訂正執行条件<BR>
     */
    private IfoOrderExecutionConditionType changeExecCondType;

    /**
     * 訂正取消結果コード<BR>
     * <BR>
     * DBレイアウト<BR>
     * 「先物OP訂正取消通知キューテーブル」参照。<BR>
     */
    private String changeCancelledResultCode;

    /**
     * 訂正取消通知区分<BR>
     * <BR>
     * 　@1：訂正完了<BR>
     * 　@2：訂正失敗<BR>
     * 　@3：取消完了<BR>
     * 　@4：取消失敗<BR>
     * 　@5：エラー<BR>
     *
     * DBレイアウト
     * 「先物OP訂正取消通知キューテーブル」参照。
     */
    private String changeCancelNotifyDivision;

    /**
     * 計算単価<BR>
     */
    private double computerUnitPrice;

    /**
     * 概算受渡代金<BR>
     */
    private double estimateDeliveryAmount;

    /**
     * (訂正後発注経路区分)<BR>
     * 訂正後発注経路区分<BR>
     */
    private String modSubmitOrderRouteDiv;

    /**
     * (訂正後注文Rev.)<RB>
     * 訂正後注文Rev.<BR>
     */
    private String modifiedOrderRev;

    /**
     * (取消通知更新インタセプタ)<BR>
     * コンストラクタ<BR>
     * @@return webbroker3.ifo.WEB3IfoChangeCancelNotifyUpdateInterceptor
     * @@roseuid 4084C0EE025A
     */
    public WEB3IfoChangeCancelNotifyUpdateInterceptor()
    {

    }

    /**
     * (set訂正後数量)<BR>
     * 訂正後数量をセットする。<BR>
     * @@param l_dblChangedQuantity - 訂正後数量
     * @@roseuid 4084C10C03B2
     */
    public void setChangedQuantity(double l_dblChangedQuantity)
    {
        this.changedQuantity = l_dblChangedQuantity;
    }

    /**
     * (set訂正後指値)<BR>
     * 訂正後指値をセットする。<BR>
     * @@param l_dblChangedLimitPrice - 訂正後指値
     * @@roseuid 4084C14203E1
     */
    public void setChangedLimitPrice(double l_dblChangedLimitPrice)
    {
        this.changedLimitPrice = l_dblChangedLimitPrice;
    }

    /**
     * (set訂正後執行条件)<BR>
     * 訂正後執行条件をセットする。<BR>
     * @@param l_execCondType - 執行条件
     * @@roseuid 4084C16000C4
     */
    public void setChangedExecCondType(IfoOrderExecutionConditionType l_execCondType)
    {
        this.changeExecCondType = l_execCondType;
    }

    /**
     * (set訂正取消結果コード)<BR>
     * 訂正取消結果コードをセットする。<BR>
     * @@param l_strChangeCancelResultCode - 訂正取消結果コード
     * @@roseuid 4084C195024A
     */
    public void setChangeCancelResultCode(String l_strChangeCancelResultCode)
    {
        this.changeCancelledResultCode = l_strChangeCancelResultCode;
    }

    /**
     * (set訂正取消通知区分)<BR>
     * 訂正取消通知区分をセットする。<BR>
     * @@param l_strChangeCancelNotifyDivision - 訂正取消通知区分
     * @@roseuid 4084CA6B0018
     */
    public void setChangeCancelNotifyDivision(String l_strChangeCancelNotifyDivision)
    {
        this.changeCancelNotifyDivision = l_strChangeCancelNotifyDivision;
    }

    /**
     * (set計算単価)<BR>
     * 計算単価をセットする。<BR>
     * @@param l_dblComputerUnitPrice - 計算単価
     * @@roseuid 4085F98901DB
     */
    public void setCalcUnitPrice(double l_dblComputerUnitPrice)
    {
        this.computerUnitPrice = l_dblComputerUnitPrice;
    }

    /**
     * (set概算受渡代金)<BR>
     * 概算受渡代金をセットする。<BR>
     * @@param l_dblEstimateDeliveryAmount - 概算受渡代金
     * @@roseuid 4085F9910036
     */
    public void setEstimateDeliveryAmount(double l_dblEstimateDeliveryAmount)
    {
        this.estimateDeliveryAmount = l_dblEstimateDeliveryAmount;
    }

    /**
     * (set訂正後発注経路区分)<BR>
     * 訂正後発注経路区分をセットする。<BR>
     * @@param l_strModSubmitOrderRouteDiv - (訂正後発注経路区分)<BR>
     * 訂正後発注経路区分<BR>
     */
    public void setModSubmitOrderRouteDiv(String l_strModSubmitOrderRouteDiv)
    {
        this.modSubmitOrderRouteDiv = l_strModSubmitOrderRouteDiv;
    }

    /**
     * (set訂正後注文Rev.)<BR>
     * 訂正後注文Rev.をセットする。<BR>
     * @@param l_strModifiedOrderRev - (訂正後注文Rev.)<BR>
     * 訂正後注文Rev.<BR>
     */
    public void setModifiedOrderRev(String l_strModifiedOrderRev)
    {
        this.modifiedOrderRev = l_strModifiedOrderRev;
    }

    /**
     * (get訂正後数量)<BR>
     * 訂正後数量を取得する。<BR>
     * @@return double
     * @@roseuid 4084E05500A5
     */
    public double getChangedQuantity()
    {
        return this.changedQuantity;
    }

    /**
     * (get訂正後指値)<BR>
     * 訂正後指値を取得する。<BR>
     * @@return double
     * @@roseuid 4084E05500B4
     */
    public double getChangedLimitPrice()
    {
        return this.changedLimitPrice;
    }

    /**
     * (get訂正後執行条件)<BR>
     * 訂正後執行条件を取得する。<BR>
     * @@return IfoOrderExecutionConditionType
     * @@roseuid 4084E05500B6
     */
    public IfoOrderExecutionConditionType getChangedExecCondType()
    {
        return this.changeExecCondType;
    }

    /**
     * (get訂正取消結果コード)<BR>
     * 訂正取消結果コードを取得する。<BR>
     * @@return double
     * @@roseuid 4084E05500B8
     */
    public String getChangeCancelResultCode()
    {
        return this.changeCancelledResultCode;
    }

    /**
     * (get訂正取消通知区分)<BR>
     * 訂正取消通知区分を取得する。<BR>
     * @@return double
     * @@roseuid 4084E05500E3
     */
    public String getChangeCancelNotifyDivision()
    {
        return this.changeCancelNotifyDivision;
    }

    /**
     * (get訂正後発注経路区分)<BR>
     * 訂正後発注経路区分を取得する。<BR>
     * @@return String
     */
    public String getModSubmitOrderRouteDiv()
    {
        return this.modSubmitOrderRouteDiv;
    }

    /**
     * (get訂正後注文Rev.)<BR>
     * 訂正後注文Rev.を取得する。<BR>
     * @@return String
     */
    public String getModifiedOrderRev()
    {
        return this.modifiedOrderRev;
    }

    /**
     * (更新値設定)<BR>
     *（mutateメソッドの実装）<BR>
     * 注文単位Paramsに拡張項目(*)設定し返却する。<BR>
     * (*) xTradeが標準実装でセットしないカスタマイズ項目。<BR>
     * (*) xTradeマニュアル「更新内容のカスタマイズ」参照。<BR>
     * １） 拡張項目セット<BR>
     * 注文単位.先物／オプション区分 == "オプション"の場合<BR>
     * 　@更新内容は、「OP訂正取消通知_注文単位テーブル.xls」の<BR>
     * 　@「OP訂正取消通知_注文単位ﾃｰﾌﾞﾙ DB更新[訂正]」シート、<BR>
     * 　@「OP訂正取消通知_注文単位ﾃｰﾌﾞﾙ DB更新[取消]」シート参照。<BR>
     * 注文単位.先物／オプション区分 == "先物"の場合<BR>
     * 　@更新内容は、「先物訂正取消通知_注文単位テーブル.xls」の<BR>
     * 　@「先物訂正取消通知_注文単位ﾃｰﾌﾞﾙ DB更新[訂正]」シート、<BR>
     * 　@「先物訂正取消通知_注文単位ﾃｰﾌﾞﾙ DB更新[取消]」シート参照。<BR>
     * @@param l_updateType
     * @@param l_dealing
     * @@param l_orderUnitParams
     * @@return webbroker3.ifo.data.IfoOrderUnitParams
     * @@roseuid 40C9957D008C
     */
    public IfoOrderUnitParams mutate(OrderManagerPersistenceType l_updateType,
        OrderManagerPersistenceContext l_dealing,
        IfoOrderUnitParams l_orderUnitParams)
    {
        final String STR_METHOD_NAME =
            ".mutate(OrderManagerPersistenceType l_updateType," +
            " OrderManagerPersistenceContext l_dealing, " +
            "IfoOrderUnitParams l_orderUnitParams) ";

        log.entering(STR_METHOD_NAME);

        if (l_orderUnitParams == null)
        {
            log.debug("Enter the if path that l_orderUnitParams is null.");
            log.error(STR_METHOD_NAME,new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName()+ STR_METHOD_NAME ));
            log.debug("Exit the if path that l_orderUnitParams is null.");
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,this.getClass().getName()+ "." + STR_METHOD_NAME);
        }

        try
        {
            //（IfoOrderUnitParamsをOP注文マネージャ.toOrderUnit()にて注文単位型にする）
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3OptionOrderManagerImpl l_opOrderMgr = (WEB3OptionOrderManagerImpl)
                l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
            IfoOrderUnit l_orderUnit = 
                (IfoOrderUnit)l_opOrderMgr.getOrderUnit(l_orderUnitParams.getOrderUnitId());
            IfoOrderUnitRow l_orderUnitRow = 
                (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
            IfoOrderUnitParams l_beforeOrderUnitParams = new IfoOrderUnitParams(l_orderUnitRow);

            String l_strChangeCancelNotifyDivision = getChangeCancelNotifyDivision();
            if (WEB3CanmodReceiptTypeDef.CHANGED.equals(l_strChangeCancelNotifyDivision)
                || WEB3CanmodReceiptTypeDef.CHANGE_FAILED.equals(l_strChangeCancelNotifyDivision))
            {
                log.debug("Enter the try path that l_orderUnitParams is not null.");
                log.entering(STR_METHOD_NAME);

                //(*1)OP注文マネージャ.isストップ注文切替中()＝trueの場合、ストップ注文切替中。
                //引数に設定する注文単位には、更新前の注文単位を指定する
                boolean l_blnIsStopOrderChanging = l_opOrderMgr.isStopOrderSwitching(l_orderUnit);

                //(*2)ストップ注文切替中(*1)、かつ、
                //先物OP訂正取消通知キューテーブル.訂正取消通知区分 == ”訂正完了”の場合、
                //ストップ注文切替OK。
                boolean l_blnIsStopOrderChgOk = false;

                if (l_blnIsStopOrderChanging && 
                    WEB3CanmodReceiptTypeDef.CHANGED.equals(l_strChangeCancelNotifyDivision))
                {    
                    l_blnIsStopOrderChgOk = true;
                }

                //注文数量
                l_orderUnitParams.setQuantity(this.changedQuantity);                

                //指値
                l_orderUnitParams.setLimitPrice(this.changedLimitPrice);

                //執行条件
                l_orderUnitParams.setExecutionConditionType(this.changeExecCondType);                                    

                //市場から確認済みの数量
                l_orderUnitParams.setConfirmedQuantity(this.changedQuantity);

                //市場から確認済みの指値
                l_orderUnitParams.setConfirmedPrice(this.changedLimitPrice);

                //注文単価
                l_orderUnitParams.setPrice(this.computerUnitPrice);

                //概算受渡代金
                l_orderUnitParams.setEstimatedPrice(this.estimateDeliveryAmount);

                // 注文訂正・取消区分
                if (WEB3ModifiedResultDef.ALL_CHANGED_NO_EXECUTED.equals(this.changeCancelledResultCode)
                    || WEB3ModifiedResultDef.ALL_CHANGED_PARTIALLY_EXECUTED.equals(this.changeCancelledResultCode))
                {
                    if (l_blnIsStopOrderChanging)
                    {
                        //ストップ注文切替中(*1)であれば、”C：W指値注文全部切替完了”
                        l_orderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.W_LIMIT_TRANSFERED);
                    }
                    else
                    {
                        //以外、”7：全部訂正完了”
                        l_orderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.CHANGED);
                    }
                }
                //（訂正取消結果ｺｰﾄﾞ == 05：一部訂正”または、
                // 08：一部訂正不能（内出来なし）”または、
                // 09：一部訂正不能（内出来あり）”）の場合
                else if ((WEB3ModifiedResultDef.PARTIALLY_CHANGED.equals(this.changeCancelledResultCode))
                    || (WEB3ModifiedResultDef.PARTIALLY_NOT_CHANGED_NO_EXECUTED.equals(this.changeCancelledResultCode))
                    || (WEB3ModifiedResultDef.PARTIALLY_NOT_CHANGED_PARTIALLY_EXECUTED.equals(this.changeCancelledResultCode)))
                {
                    if (l_blnIsStopOrderChanging)
                    {
                        //ストップ注文切替中(*1)であれば、”B：W指値注文一部切替完了”
                        l_orderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.W_LIMIT_PARTIALLY_TRANSFERED);
                    }
                    else
                    {
                        //以外、”6：一部訂正完了”
                        l_orderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.PARTIALLY_CHANGED);
                    }
                }
                //上記以外の場合
                else
                {
                    log.debug("Enter the else path:上記以外の場合");
                    if (l_blnIsStopOrderChanging)
                    {
                        //ストップ注文切替中(*1)であれば、”D：W指値注文切替失敗”
                        l_orderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.W_LIMIT_TRANSFER_ERROR);
                    }
                    else
                    {
                        // 以外、”8：訂正失敗”
                        l_orderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.CHANGE_ERROR);
                    }                        

                    log.exiting("Exit the else path:上記以外の場合");
                }

                if (l_orderUnitParams.getPriceIsNull())
                {
                    l_orderUnitParams.setConfirmedOrderPrice(null);
                }
                else
                {
                    l_orderUnitParams.setConfirmedOrderPrice(l_orderUnitParams.getPrice());
                }

                // 市場から確認済みの概算受渡代金
                if (l_orderUnitParams.getEstimatedPriceIsNull())
                {
                    l_orderUnitParams.setConfirmedEstimatedPrice(null);
                }
                else
                {
                    l_orderUnitParams.setConfirmedEstimatedPrice(l_orderUnitParams.getEstimatedPrice());
                }

                l_orderUnitParams.setConfirmedExecConditionType(l_orderUnitParams.getExecutionConditionType());

                //先物OP訂正取消通知キューテーブル.訂正取消通知区分 == ”訂正完了”の場合
                if (WEB3IfoCanmodReceiptTypeDef.CHANGED_COMPLETE.equals(this.changeCancelNotifyDivision))
                {
                     //0000：正常
                    l_orderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);
                }

                //リクエストタイプ
                //ストップ注文切替中(*1)の場合、
                if (l_blnIsStopOrderChanging)
                {
                    // －先物OP訂正取消通知キューテーブル.訂正取消通知区分 == ”訂正完了”であれば、
                    if (WEB3CanmodReceiptTypeDef.CHANGED.equals(l_strChangeCancelNotifyDivision))
                    {
                        //”2：切替完了”
                        l_orderUnitParams.setRequestType(WEB3RequestTypeDef.TRANSFERED);
                    }
                    // －先物OP訂正取消通知キューテーブル.訂正取消通知区分 == ”訂正失敗”であれば、
                    else if (WEB3CanmodReceiptTypeDef.CHANGE_FAILED.equals(l_strChangeCancelNotifyDivision))
                    {
                        //”5：失効”
                        l_orderUnitParams.setRequestType(WEB3RequestTypeDef.INVALIDATE);
                    }

                    //発注条件
                    //ストップ注文切替OK(*1）の場合、0：DEFAULT（条件指定なし）
                    //以外、（既存値）
                    l_orderUnitParams.setOrderConditionType(WEB3OrderingConditionDef.DEFAULT);

                    //発注条件演算子
                    //ストップ注文切替OK(*1）の場合、null
                    //以外、（既存値）
                    l_orderUnitParams.setOrderCondOperator(null);

                    //逆指値基準値タイプ
                    //ストップ注文切替OK(*1）の場合、null
                    //以外、（既存値）
                    l_orderUnitParams.setStopPriceType(null);

                    //逆指値基準値
                    //ストップ注文切替OK(*1）の場合、null
                    //以外、（既存値）
                    l_orderUnitParams.setStopOrderPrice(null);

                    //（W指値）訂正指値
                    //ストップ注文切替OK(*1）の場合、null
                    //以外、（既存値）
                    l_orderUnitParams.setWLimitPrice(null);

                    //元発注条件
                    //ストップ注文切替OK(*1）の場合、更新前の発注条件
                    //以外、（既存値）
                    l_orderUnitParams.setOrgOrderConditionType(
                        l_beforeOrderUnitParams.getOrderConditionType());

                    //元発注条件演算子
                    //ストップ注文切替OK(*1）の場合、更新前の発注条件演算子
                    //以外、（既存値）
                    l_orderUnitParams.setOrgOrderCondOperator(
                        l_beforeOrderUnitParams.getOrderCondOperator());

                    //元逆指値基準値タイプ
                    //ストップ注文切替OK(*1）の場合、更新前の逆指値基準値タイプ
                    //以外、（既存値）
                    l_orderUnitParams.setOrgStopPriceType(
                        l_beforeOrderUnitParams.getStopPriceType());

                    //元逆指値基準値
                    //ストップ注文切替OK(*1）の場合、更新前の逆指値基準値
                    //以外、（既存値）
                    if (l_beforeOrderUnitParams.getStopOrderPriceIsNull())
                    {
                        l_orderUnitParams.setOrgStopOrderPrice(null);
                    }
                    else
                    {
                        l_orderUnitParams.setOrgStopOrderPrice(
                            l_beforeOrderUnitParams.getStopOrderPrice());
                    }

                    //元（W指値）訂正指値
                    //ストップ注文切替OK(*1）の場合、更新前の（W指値）訂正指値
                    //以外、（既存値）
                    if (l_beforeOrderUnitParams.getWLimitPriceIsNull())
                    {
                        l_orderUnitParams.setOrgWLimitPrice(null);
                    }
                    else
                    {
                        l_orderUnitParams.setOrgWLimitPrice(
                            l_beforeOrderUnitParams.getWLimitPrice());
                    }

                    //元（W指値）執行条件
                    //ストップ注文切替OK(*1）の場合、更新前の（W指値）執行条件
                    //以外、（既存値）
                    l_orderUnitParams.setOrgWLimitExecCondType(
                        l_beforeOrderUnitParams.getWLimitExecCondType());

                    //（W指値）執行条件
                    //ストップ注文切替OK(*1）の場合、null
                    //以外、（既存値）
                    l_orderUnitParams.setWLimitExecCondType(null);
                }
                //以外の場合、（既存値）

				//注文有効状態
				//市場確認済み数量！＝注文単位.約定数量の場合、オープンに設定。
				if (WEB3CanmodReceiptTypeDef.CHANGED.equals(l_strChangeCancelNotifyDivision))
				{
					if(this.changedQuantity == l_orderUnitParams.getExecutedQuantity())
					{
						l_orderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
					}else
					{
						l_orderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
					}
					
				}

                if (l_blnIsStopOrderChgOk)
                {       
                    //（W指値）切替前指値
                    //ストップ注文切替OK(*2）の場合、更新前の市場から確認済みの指値
                    //以外、（既存値）
                    l_orderUnitParams.setWLimitBeforeLimitPrice(
                        l_beforeOrderUnitParams.getConfirmedPrice());
                    
                    //（W指値）切替前執行条件
                    //ストップ注文切替OK(*2）の場合、更新前の市場から確認済みの執行条件
                    //以外、（既存値）
                    l_orderUnitParams.setWLimitBeforeExecCondType(
                        l_beforeOrderUnitParams.getConfirmedExecConditionType());
                }

                //発注経路区分
                //先物OP訂正取消通知キューテーブル.訂正後発注経路区分≠nullの場合：
                //　@先物OP訂正取消通知キューテーブル.訂正後発注経路区分をセット。
                //上記以外の場合：（既存値）
                if (getModSubmitOrderRouteDiv() != null)
                {
                    l_orderUnitParams.setSubmitOrderRouteDiv(getModSubmitOrderRouteDiv());
                }

                //市場から確認済みの注文Rev.
                //先物OP訂正取消通知キューテーブル.訂正後注文Rev.
                l_orderUnitParams.setConfirmedOrderRev(getModifiedOrderRev());
            }

            // 取消完了または取消失敗
            if (WEB3CanmodReceiptTypeDef.CANCEL.equals(l_strChangeCancelNotifyDivision)
                || WEB3CanmodReceiptTypeDef.CANCEL_FAILED.equals(l_strChangeCancelNotifyDivision))
            {

                //指値
                l_orderUnitParams.setLimitPrice(this.changedLimitPrice);

                //市場から確認済みの数量
                l_orderUnitParams.setConfirmedQuantity(l_orderUnitParams.getQuantity());

                //執行条件
                l_orderUnitParams.setExecutionConditionType(this.changeExecCondType);

                //市場から確認済みの指値
                if (l_orderUnitParams.getLimitPriceIsNull())
                {
                    l_orderUnitParams.setConfirmedPrice(null);
                }
                else
                {
                    l_orderUnitParams.setConfirmedPrice(l_orderUnitParams.getLimitPrice());
                }

                if (WEB3ModifiedResultDef.PARTIALLY_CANCEL.equals(this.changeCancelledResultCode))
                {
                    //概算受渡代金
                    l_orderUnitParams.setEstimatedPrice(this.estimateDeliveryAmount);
                }

                //注文訂正・取消区分
                //訂正取消結果ｺｰﾄﾞ==”01：全部取消”の場合
                if (WEB3ModifiedResultDef.ALL_CANCEL.equals(this.changeCancelledResultCode))
                {
                    //全部取消完了
                    l_orderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.CANCELED);

                }
                //（訂正取消結果ｺｰﾄﾞ == 04：一部取消”または、
                //06：一部取消不能（内出来なし）”または、
                //07：一部取消不能（内出来あり）”）の場合
                else if ((WEB3ModifiedResultDef.PARTIALLY_CANCEL.equals(this.changeCancelledResultCode))
                    || (WEB3ModifiedResultDef.PARTIALLY_NOT_CANCEL_NO_EXECUTED.equals(this.changeCancelledResultCode))
                    || (WEB3ModifiedResultDef.PARTIALLY_NOT_CANCEL_EXECUTED.equals(this.changeCancelledResultCode)))
                {
                    l_orderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.PART_CANCELED);
                }
                //上記以外の場合
                else
                {
                    l_orderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.CANCEL_ERROR);
                }

                //市場から確認済みの執行条件
                l_orderUnitParams.setConfirmedExecConditionType(l_orderUnitParams.getExecutionConditionType());
                //先物OP訂正取消通知キューテーブル.訂正取消通知区分 == 取消完了の場合

                if (WEB3IfoCanmodReceiptTypeDef.CANCELED_COMPLETE.equals(this.changeCancelNotifyDivision))
                {
                    //0000：正常
                    l_orderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);
                }

                String l_strWLimitEnableStatusDiv = 
                    WEB3IfoDataAdapter.getWLimitEnableStatusDiv(l_orderUnit);
                //(*1)
                //・通知キュー.訂正取消通知区分="取消失敗"の場合、取消失敗。
                //・先物OPデータアダプタ.getＷ指値用有効状態区分()＝"リミット注文有効"の場合、
                //リミット注文有効。
                //引数に設定する注文単位には、更新前の注文単位を指定する
                if (WEB3CanmodReceiptTypeDef.CANCEL_FAILED.equals(l_strChangeCancelNotifyDivision)
                    && WEB3IfoWLimitEnableStatusDivDef.LIMIT_ENABLE.equals(l_strWLimitEnableStatusDiv))
                {
                    //発注条件
                    //取消失敗かつリミット注文有効(*1）の場合、0：DEFAULT（条件指定なし）
                    //以外、（既存値）
                    l_orderUnitParams.setOrderConditionType(WEB3OrderingConditionDef.DEFAULT);

                    //発注条件演算子
                    //取消失敗かつリミット注文有効(*1）の場合、null
                    //以外、（既存値）
                    l_orderUnitParams.setOrderCondOperator(null);

                    //逆指値基準値タイプ
                    //取消失敗かつリミット注文有効(*1）の場合、null
                    //以外、（既存値）
                    l_orderUnitParams.setStopPriceType(null);

                    //逆指値基準値
                    //取消失敗かつリミット注文有効(*1）の場合、null
                    //以外、（既存値）
                    l_orderUnitParams.setStopOrderPrice(null);

                    //（W指値）訂正指値
                    //取消失敗かつリミット注文有効(*1）の場合、null
                    //以外、（既存値）
                    l_orderUnitParams.setWLimitPrice(null);

                    //元発注条件
                    //取消失敗かつリミット注文有効(*1）の場合、更新前の発注条件
                    //以外、（既存値）
                    l_orderUnitParams.setOrgOrderConditionType(
                        l_beforeOrderUnitParams.getOrderConditionType());

                    //元発注条件演算子
                    //取消失敗かつリミット注文有効(*1）の場合、更新前の発注条件演算子
                    //以外、（既存値）
                    l_orderUnitParams.setOrgOrderCondOperator(
                        l_beforeOrderUnitParams.getOrderCondOperator());

                    //元逆指値基準値タイプ
                    //取消失敗かつリミット注文有効(*1）の場合、更新前の逆指値基準値タイプ
                    //以外、（既存値）
                    l_orderUnitParams.setOrgStopPriceType(
                        l_beforeOrderUnitParams.getStopPriceType());

                    //元逆指値基準値
                    //取消失敗かつリミット注文有効(*1）の場合、更新前の逆指値基準値
                    //以外、（既存値）
                    if (l_beforeOrderUnitParams.getStopOrderPriceIsNull())
                    {
                        l_orderUnitParams.setOrgStopOrderPrice(null);
                    }
                    else
                    {
                        l_orderUnitParams.setOrgStopOrderPrice(
                            l_beforeOrderUnitParams.getStopOrderPrice());
                    }

                    //元（W指値）訂正指値
                    //取消失敗かつリミット注文有効(*1）の場合、更新前の（W指値）訂正指値
                    //以外、（既存値）
                    if (l_beforeOrderUnitParams.getWLimitPriceIsNull())
                    {
                        l_orderUnitParams.setOrgWLimitPrice(null);
                    }
                    else
                    {
                        l_orderUnitParams.setOrgWLimitPrice(
                            l_beforeOrderUnitParams.getWLimitPrice());
                    }

                    //元（W指値）執行条件
                    //取消失敗かつリミット注文有効(*1）の場合、更新前の（W指値）執行条件
                    //以外、（既存値）
                    l_orderUnitParams.setOrgWLimitExecCondType(
                        l_beforeOrderUnitParams.getWLimitExecCondType());

                    //（W指値）執行条件
                    //取消失敗かつリミット注文有効(*1）の場合、null
                    //以外、（既存値）
                    l_orderUnitParams.setWLimitExecCondType(null);

                    if (WEB3FuturesOptionDivDef.OPTION.equals(l_orderUnitParams.getFutureOptionDiv()))
                    {
                        //注文単価
                        l_orderUnitParams.setPrice(this.computerUnitPrice);

                        //概算受渡代金
                        l_orderUnitParams.setEstimatedPrice(this.estimateDeliveryAmount);
                    }

                    //リクエストタイプ
                    l_orderUnitParams.setRequestType(WEB3RequestTypeDef.INVALIDATE);

                }
                
                if (WEB3FuturesOptionDivDef.OPTION.equals(l_orderUnitParams.getFutureOptionDiv()))
                {
                    if (l_orderUnitParams.getPriceIsNull())
                    {
                        l_orderUnitParams.setConfirmedOrderPrice(null);
                    }
                    else
                    {
                        //市場から確認済みの注文単価
                        l_orderUnitParams.setConfirmedOrderPrice(l_orderUnitParams.getPrice());
                    }
                }

                // 市場から確認済みの概算受渡代金
                if (l_orderUnitParams.getEstimatedPriceIsNull())
                {
                    l_orderUnitParams.setConfirmedEstimatedPrice(null);
                }
                else
                {
                    l_orderUnitParams.setConfirmedEstimatedPrice(l_orderUnitParams.getEstimatedPrice());
                }

                //発注経路区分
                //先物OP訂正取消通知キューテーブル.訂正後発注経路区分≠nullの場合：
                //先物OP訂正取消通知キューテーブル.訂正後発注経路区分をセット。
                //上記以外の場合：（既存値）
                if (getModSubmitOrderRouteDiv() != null)
                {
                    l_orderUnitParams.setSubmitOrderRouteDiv(getModSubmitOrderRouteDiv());
                }
            }
        }
        catch(Exception l_ex)
        {
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
	 * (更新値設定)<BR>                              
	 *（mutateメソッドの実装）<BR>                               
	 * 注文履歴Paramsに拡張項目(*)設定し返却する。<BR>                               
	 * <BR>                             
	 * (*) xTradeが標準実装でセットしないカスタマイズ項目。<BR>                              
	 * <BR>                             
	 * １）　@注文単位オブジェクト取得<BR>
	 * 引数の注文単位Params.注文ID、注文単位ＩＤに該当する注文単位オブジェクトを取得する。                               
	 * <BR>
	 * 
	 * ２）　@super.mutate(IfoOrderActionParams)をコールする。
	 * 
	 * ３）　@xTrade標準項目の更新仕様をカスタマイズする。
	 * 　@　@※xTrade標準実装では、 
	 * 　@　@※訂正失敗時には訂正取消通知を行う前の注文単位の値が設定されてしまうため。
	 *  
     * @@param l_updateType
     * @@param l_dealing
     * @@param l_orderActionParams
     * @@return webbroker3.ifo.data.l_orderActionParams
     * @@roseuid 40C9957D008C
     */
    public IfoOrderActionParams mutate(OrderManagerPersistenceType l_updateType,
        OrderManagerPersistenceContext l_dealing,
      IfoOrderActionParams l_orderActionParams)
    {
        final String STR_METHOD_NAME =
            ".mutate(OrderManagerPersistenceType l_updateType," +
            " OrderManagerPersistenceContext l_dealing, " +
            "IfoOrderActionParams l_orderActionParams) ";

        log.entering(STR_METHOD_NAME);

        if (l_orderActionParams == null)
        {
            log.debug("Enter the if path that l_orderActionParams is null.");
            log.error(STR_METHOD_NAME,new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName()+ STR_METHOD_NAME ));
            log.debug("Exit the if path that l_orderActionParams is null.");
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,this.getClass().getName()+ "." + STR_METHOD_NAME);
        }

      long l_orderUnitID;
      l_orderUnitID = l_orderActionParams.getOrderUnitId();
      FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
      OrderUnit l_ifoOrderUnit = null;
      IfoOrderUnitParams l_params = null;
      try
      {
          l_ifoOrderUnit = l_finApp.getTradingModule(
              ProductTypeEnum.IFO).getOrderManager().getOrderUnit(l_orderUnitID);
          l_params = (IfoOrderUnitParams)l_ifoOrderUnit.getDataSourceObject();
      }
      catch (NotFoundException l_nfe)
      {
          log.error(STR_METHOD_NAME, l_nfe);
      }

      super.mutate(l_updateType, l_dealing, l_orderActionParams);

      l_orderActionParams.setPrice(l_params.getLimitPrice());
      l_orderActionParams.setQuantity(l_params.getQuantity());
      l_orderActionParams.setConfirmedQuantity(l_params.getConfirmedQuantity());
      l_orderActionParams.setConfirmedPrice(l_params.getConfirmedPrice());

      log.exiting(STR_METHOD_NAME);
      return l_orderActionParams;
    }
}
@
