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
filename	WEB3IfoAcceptedUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP注文受付更新インタセプタ(WEB3IfoAcceptedUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/6/10 盧法@旭 (中訊) 新規作成
Revesion History : 2004/08/14 王暁傑 (Sinocom) 対応名称  【株価指数オプション】ソースコードチェック指摘事項(JP)20040802.xls No.28
Revesion History : 2006/7/11 李俊 (中訊) ＤＢ更新仕様 096
Revesion History : 2006/07/13 郭英 (中訊) DB更新仕様No097
Revesion History : 2007/01/29 趙林鵬 (中訊) 仕様変更 モデルNo.606,ＤＢ更新仕様No.142
*/

package webbroker3.ifo;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (先物OP注文受付更新インタセプタ)<BR>
 * 先物OP注文受付更新インタセプタクラス<BR>
 * @@author 盧法@旭
 * @@version 1.0
 */
public class WEB3IfoAcceptedUpdateInterceptor extends WEB3IfoOrderUpdateInterceptor 
{

    /**
     * ログ出力ユーティリティ。
     */
    public static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3IfoAcceptedUpdateInterceptor.class);
        
    /**
     * エラーコード<BR>
     */
    private String errorCode;    

    /**
     * 発注経路区分<BR>
     */
    private String submitOrderRouteDiv;

    /**
     * @@roseuid 40C07C0C00EA
     */
    public WEB3IfoAcceptedUpdateInterceptor() 
    {
     
    }
    
    /**
     * (先物OP注文受付更新インタセプタ)<BR>
     * コンストラクタ<BR>
     * <BR>
     * 引数のエラーコード、発注経路区分を自身のプロパティにセット、自身のインスタンスを返却する。<BR>
     * @@param l_strErrorCode - (エラーコード)<BR>
     * エラーコード<BR>
     * @@param l_strSubmitOrderRouteDiv - (発注経路区分)<BR>
     * 発注経路区分<BR>
     * @@return webbroker3.ifo.WEB3IfoAcceptedUpdateInterceptor
     * @@roseuid 40836BDB03D4
     */
    public WEB3IfoAcceptedUpdateInterceptor(String l_strErrorCode, String l_strSubmitOrderRouteDiv) 
    {   
        this.errorCode = l_strErrorCode;

        this.submitOrderRouteDiv = l_strSubmitOrderRouteDiv;
    }
    
    /**
     * (更新値設定)<BR>
     *（mutateメソッドの実装）<BR>
     * 注文単位Paramsに拡張項目(*)設定し返却する。<BR>
     *   xTradeが標準実装でセットしないカスタマイズ項目。<BR>
     *   xTradeマニュアル「更新内容のカスタマイズ」参照。<BR>
     *　@ プロパティの内容より、パラメータ.注文単位Params <BR>
     *   の拡張項目に値をセットし、返却する。<BR>
     *  【ｘTrade】補足資料.DB更新<BR>
     *     更新内容は下記を参照。<BR>
     *    「先OP注文受付_注文単位テーブル.xls」<BR>
     *　@   注文状態が”発注済（新規注文）：ORDERED”の場合：<BR>
     *　@  「（先OP注文受付[受付完了]）注文単位テーブル」シート<BR>
     *　@   注文状態が”発注失敗（新規注文）：NOT_ORDERED”の場合： <BR>
     *　@  「（先OP注文受付[受付エラー]）注文単位テーブル」シート<BR>
     *    （注文状態が”発注済（新規注文）：ORDERED”<BR>
     *     または”発注失敗（新規注文）：NOT_ORDERED”の場合のみ<BR>
     *   処理を実施する） <BR>
     * @@param l_updateType- (処理)<BR>
     *   OrderManagerPersistenceTypeにて定数定義。<BR>
     * @@param l_orderManagerPersistenceContext - (処理)<BR>
     *   OrderManagerPersistenceContextにて定数定義<BR>
     * @@param l_orderUnitParams - (注文単位Params)<BR>
     *   株式注文単位が保持する項目のパラメータクラス。<BR>
     * @@return webbroker3.ifo.data.IfoOrderUnitParams
     * @@roseuid 4083655203D4
     */
    public IfoOrderUnitParams mutate(
        OrderManagerPersistenceType l_updateType, 
        OrderManagerPersistenceContext l_dealing, 
        IfoOrderUnitParams l_orderUnitParams) 
    {
        final String STR_METHOD_NAME = "WEB3IfoAcceptedUpdateInterceptor:mutate()";
        log.entering(STR_METHOD_NAME);
        
        if(l_orderUnitParams == null)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName()+ "." + STR_METHOD_NAME);
        }        

        try
        {
            if (OrderStatusEnum.ORDERED.equals(l_orderUnitParams.getOrderStatus()))
            {
                //(*1)更新前の発注条件＝"逆指値"の場合、逆指値
                IfoOrderUnitParams l_orderUnitParamsCopy = 
                    new IfoOrderUnitParams(l_orderUnitParams);
                boolean l_blnStopLimitPrice = false;
                if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(
                    l_orderUnitParamsCopy.getOrderConditionType()))
                {
                    l_blnStopLimitPrice = true;
                }                
                
                //市場から確認済みの注文単価
                if (l_orderUnitParams.getPriceIsNull())
                {
                    l_orderUnitParams.setConfirmedOrderPrice(null);
                }
                else
                {
                    l_orderUnitParams.setConfirmedOrderPrice(l_orderUnitParams.getPrice());
                }

                //市場から確認済みの概算受渡代金を設定する
                if (l_orderUnitParams.getEstimatedPriceIsNull())
                {
                    l_orderUnitParams.setConfirmedEstimatedPrice(null);
                }
                else
                {
                    l_orderUnitParams.setConfirmedEstimatedPrice(l_orderUnitParams.getEstimatedPrice());
                }

                //市場から確認済みの執行条件
                l_orderUnitParams.setConfirmedExecConditionType(l_orderUnitParams.getExecutionConditionType()); 

                //注文エラー理由コード
                l_orderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL); 

                //発注経路区分
                if(this.submitOrderRouteDiv != null)
                {
                    l_orderUnitParams.setSubmitOrderRouteDiv(this.submitOrderRouteDiv);
                }

                if (l_blnStopLimitPrice)
                {
                    //発注条件
                    //逆指値(*1）の場合、0：DEFAULT（条件指定なし）
                    l_orderUnitParams.setOrderConditionType(WEB3OrderingConditionDef.DEFAULT);
                    
                    //発注条件演算子
                    //逆指値(*1)の場合、null
                    l_orderUnitParams.setOrderCondOperator(null);
                    
                    //逆指値基準値タイプ
                    //逆指値(*1)の場合、null
                    l_orderUnitParams.setStopPriceType(null);
                    
                    //逆指値基準値
                    //逆指値(*1)の場合、null
                    l_orderUnitParams.setStopOrderPrice(null);
                    
                    //元発注条件
                    //逆指値(*1）の場合、更新前の発注条件
                    l_orderUnitParams.setOrgOrderConditionType(
                        l_orderUnitParamsCopy.getOrderConditionType());

                    //元発注条件演算子
                    //逆指値（*1)の場合、更新前の発注条件演算子
                    l_orderUnitParams.setOrgOrderCondOperator(
                        l_orderUnitParamsCopy.getOrderCondOperator());

                    //元逆指値基準値タイプ
                    //逆指値（*1)の場合、更新前の逆指値基準値タイプ
                    l_orderUnitParams.setOrgStopPriceType(
                        l_orderUnitParamsCopy.getStopPriceType());
                    
                    //元逆指値基準値
                    //逆指値（*1)の場合、更新前の逆指値基準値
                    if (l_orderUnitParamsCopy.getStopOrderPriceIsNull())
                    {
                        l_orderUnitParams.setOrgStopOrderPrice(null);
                    }
                    else
                    {
                        l_orderUnitParams.setOrgStopOrderPrice(
                            l_orderUnitParamsCopy.getStopOrderPrice());
                    }
                }
            }
            else if (OrderStatusEnum.NOT_ORDERED.equals(l_orderUnitParams.getOrderStatus()))
            {
                //注文エラー理由コード
                l_orderUnitParams.setErrorReasonCode(this.errorCode);

                //発注経路区分
                if(this.submitOrderRouteDiv != null)
                {
                    l_orderUnitParams.setSubmitOrderRouteDiv(this.submitOrderRouteDiv);
                }
            }                                    
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName()+ "." + STR_METHOD_NAME);
        }
                
        log.exiting(STR_METHOD_NAME);       
        return l_orderUnitParams;
    }
}
@
