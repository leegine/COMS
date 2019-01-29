head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualTPAdjustConfirmInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信管理者余力調整確定インタセプタ(WEB3AdminMutualTPAdjustConfirmInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/12/19 韋念瓊 (中訊) 新規作成      
*/

package webbroker3.mf;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitParams;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ChannelDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3SettlementDivDef;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * 投信管理者余力調整確定インタセプタ<BR>
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0  
 */
public class WEB3AdminMutualTPAdjustConfirmInterceptor
    extends WEB3DefaultMutualFundOrderConfirmInterceptor 
{        
    /**
     *  ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminMutualTPAdjustConfirmInterceptor.class);
        
    /**
     * 発注日<BR>
     */
    protected Timestamp orderBizDate;
        
    /**
     * 約定日<BR>
     */
    protected Timestamp executionDate;
    
    /**
     * 受渡日<BR>
     */
    protected Timestamp deliveryDate;
    
    /**
     * 概算受渡代金<BR>
     */
    protected double estimatedPrice;
    
    /**
     * 計算基準価額<BR>
     */
    protected double constantValue;
    
    /**
     * 基準価額適用日<BR>
     */
    protected Timestamp constantValueAppDate;
    
    /**
     * 投信管理者余力調整確定インタセプタ<BR>
     * コンストラクタ<BR>
     * @@roseuid 40AD8E0E03B4
     */
    public WEB3AdminMutualTPAdjustConfirmInterceptor() 
    {
    }
    
    /**
     * (更新値設定)
     * （mutateの実装） <BR>
     * <BR>
     * 注文・取消処理の中で、投信注文単位データの作成・更新時に呼ばれる。 <BR> 
     * 引数で与えられた投信注文単位Paramsに値を設定し、投信注文単位Paramsを返す。<BR> 
     * <BR>
     * １） 注文単位Paramsの項目に値を設定し、返却する。 <BR>
     * 　@項目設定内容は、
     *  DB更新仕様「投資信託管理者余力調整_投信注文単位テーブル仕様.xls」を参照。<BR>
     * <BR>
     * @@param l_orderManagerPersistenceType - (永続化タイプ)<BR>
     * データの更新または挿入中に呼ぶかどうか指定<BR>
     * @@param l_orderManagerPersistenceContext - (永続化コンテキスト)<BR>
     * 呼び出し時のコンテキストを指定。例えば現物注文中など。<BR>
     * @@param l_mutualFundOrderUnitParams - (投信注文単位Params)<BR>
     * 永続化前の投信注文単位Params<BR>
     * @@return MutualFundOrderUnitParams
     * @@roseuid 40AD8E93025C
     */
    public MutualFundOrderUnitParams mutate(
        OrderManagerPersistenceType l_orderManagerPersistenceType,
        OrderManagerPersistenceContext l_orderManagerPersistenceContext,
        MutualFundOrderUnitParams l_mutualFundOrderUnitParams)  
    {
        final String STR_METHOD_NAME = "mutate(" +
            "OrderManagerPersistenceType l_orderManagerPersistenceType," +
            "OrderManagerPersistenceContext l_orderManagerPersistenceContext, " +
            "MutualFundOrderUnitParams l_mutualFundOrderUnitParams))";
        log.entering(STR_METHOD_NAME);
        
        if  (l_mutualFundOrderUnitParams == null)
        {
            log.debug(" パラメータNull出来ない。With " +
                "(永続化前の投信注文単位Params)l_mutualFundOrderUnitParams" + 
                l_mutualFundOrderUnitParams);        
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータはNullである");
        }        
        
        //１） 注文単位Paramsの項目に値を設定し、返却する。 
        //項目設定内容は、DB更新仕様「投資信託管理者余力調整_投信注文単位テーブル仕様.xls」を参照。
        
        //投信注文単位Params.set受渡日(投信管理者余力調整確定インタセプタ.受渡日)
        l_mutualFundOrderUnitParams.setDeliveryDate(this.deliveryDate);
        
        //投信注文単位Params.set発注日(投信管理者余力調整確定インタセプタ.発注日) 
        l_mutualFundOrderUnitParams.setBizDate(
            WEB3DateUtility.formatDate(this.orderBizDate, "yyyyMMdd"));

        //投信注文単位Params.set初回注文の注文チャネル(”0：営業店”) 
        l_mutualFundOrderUnitParams.setOrderChanel(WEB3ChannelDef.BRANCH);
        
        //投信注文単位Params.set受注日時(
        //  サーバ側でサービスが起動された時間（計算式書（共通）(*2) 処理日付　@を参照）) 
        Timestamp l_tsOrderAcceptDate =
            (Timestamp) ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
        
        l_mutualFundOrderUnitParams.setReceivedDateTime(l_tsOrderAcceptDate);
        
        //投信注文単位Params.set扱者コード（SONAR）(null) 
        l_mutualFundOrderUnitParams.setSonarTraderCode(null);
        
        //投信注文単位Params.set識別コード(null) 
        l_mutualFundOrderUnitParams.setOrderRequestNumber(null);
        
        //投信注文単位Params.set計算基準価額(投信管理者余力調整確定インタセプタ.計算基準価額
        //（※拡張投信銘柄.get買付基準価額()）) 
        l_mutualFundOrderUnitParams.setCalcConstantValue(this.constantValue);
        
        //投信注文単位Params.set計算基準価額（乗換先）(null) 
        l_mutualFundOrderUnitParams.setSwtCalcConstantValue(null);
        
        //投信注文単位Params.set基準価額適用日(投信管理者余力調整確定インタセプタ.基準価額適用日
        //（※拡張投信銘柄.get基準価額適用日()）) 
        l_mutualFundOrderUnitParams.setConstantValueAppDate(
            this.constantValueAppDate);
        
        //投信注文単位Params.set概算受渡代金(投信管理者余力調整確定インタセプタ.概算受渡代金) 
        l_mutualFundOrderUnitParams.setEstimatedPrice(this.estimatedPrice);
        
        //投信注文単位Params.set概算売買口数(null) 
        l_mutualFundOrderUnitParams.setEstimateDealingQty(null);
        
        //投信注文単位Params.set概算買付口数（乗換先）(null) 
        l_mutualFundOrderUnitParams.setSwtEstimateDealingQty(null);
        
        //投信注文単位Params.set税区分（乗換先）(null) 
        l_mutualFundOrderUnitParams.setSwtTaxType(null);
        
        //投信注文単位Params.set銘柄コード（乗換先）(null) 
        l_mutualFundOrderUnitParams.setSwtProductCode(null);
        
        //投信注文単位Params.set受渡方法@(null) 
        l_mutualFundOrderUnitParams.setPaymentMethod(null);
        
        //投信注文単位Params.set投信タイプ(”2：国外”) 
        l_mutualFundOrderUnitParams.setFundType(MutualFundTypeEnum.FOREIGN);
        
        //投信注文単位Params.set投信解約区分(null) 
        l_mutualFundOrderUnitParams.setFundSellDiv(null);
        
        //投信注文単位Params.set約定日(投信管理者余力調整確定インタセプタ.約定日) 
        l_mutualFundOrderUnitParams.setExecDate(this.executionDate);
        
        //投信注文単位Params.set約定状態(null) 
        l_mutualFundOrderUnitParams.setExecStatus(null);
        
        //投信注文単位Params.set決済区分(1：円貨) 
        l_mutualFundOrderUnitParams.setSettlementDiv(
            WEB3SettlementDivDef.JAPANESE_CURRENCY);
        
        //投信注文単位Params.set無手数料区分(null) 
        l_mutualFundOrderUnitParams.setNoContractCommissionDiv(null);
        
        //投信注文単位Params.set請求区分(null) 
        l_mutualFundOrderUnitParams.setRequestDiv(null);
        
        //投信注文単位Params.set注文経路区分(9：HOST) 
        l_mutualFundOrderUnitParams.setOrderRootDiv(WEB3OrderRootDivDef.HOST);
        
        //投信注文単位Params.set注文エラー理由コード(NULL) 
        l_mutualFundOrderUnitParams.setErrorReasonCode(null);
        
        //投信注文単位Params.set入金日(null) 
        l_mutualFundOrderUnitParams.setPaymentDate(null);
        
        //投信注文単位Params.set源泉徴収拘束金(NULL) 
        l_mutualFundOrderUnitParams.setWithholdingTaxRestriction(null);
        
        //投信注文単位Params.set出金注文識別コード(NULL) 
        l_mutualFundOrderUnitParams.setPaymentOrderReqNumber(null);
        
        //投信注文単位Params.setCPU No.(null) 
        l_mutualFundOrderUnitParams.setCpuNo(null);
        
        //投信注文単位Paramsを返す。
        log.exiting(STR_METHOD_NAME); 
        return l_mutualFundOrderUnitParams;
    }
    
    /**
     * (更新値設定)<BR>
     * （mutateの実装）<BR>
     * <BR>
     * 注文・取消処理の中で、投信注文履歴データの作成・更新時に呼ばれる。  <BR>
     * 引数で与えられた投信注文履歴Paramsに値を設定し、投信注文履歴Paramsを返す。<BR> 
     * <BR>
     * １）　@注文エラー理由コードの設定を行う。   <BR>
     * 　@－投信注文履歴Params.set注文エラー理由コード()をコールし、<BR>
     *          注文エラー理由コードの設定を行う。  <BR> 
     * 　@　@［set注文エラー理由コードに渡すパラメタ］ <BR>
     * 　@　@　@注文エラー理由コード： null <BR>
     * <BR>
     * ２）　@引数で与えられた投信注文履歴Paramsを返す。<BR>
     * <BR>
     * @@param l_orderManagerPersistenceType - (永続化タイプ)<BR>
     * データの更新または挿入中に呼ぶかどうか指定<BR>
     * @@param l_orderManagerPersistenceContext - (永続化コンテキスト)<BR>
     * 呼び出し時のコンテキストを指定。例えば現物注文中など。<BR>
     * @@param l_mutualFundOrderActionParams - (投信注文履歴Params)<BR>
     * 永続化前の投信注文履歴Params<BR>
     * @@return MutualFundOrderActionParams
     * @@roseuid 40AAF0FF00F9
     */
    public MutualFundOrderActionParams mutate(
        OrderManagerPersistenceType l_orderManagerPersistenceType,
        OrderManagerPersistenceContext l_orderManagerPersistenceContext,
        MutualFundOrderActionParams l_mutualFundOrderActionParams)
    {
        final String STR_METHOD_NAME ="mutate(" +
            "OrderManagerPersistenceType l_orderManagerPersistenceType," +
            "OrderManagerPersistenceContext l_orderManagerPersistenceContext," +
            "MutualFundOrderActionParams l_mutualFundOrderActionParams)";
        log.entering(STR_METHOD_NAME);    
        if (l_mutualFundOrderActionParams == null)
        {
            log.debug("the parameter of method is null");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }        
        
        //１）　@注文エラー理由コードの設定を行う。   
        //－投信注文履歴Params.set注文エラー理由コード()をコールし、
        //  注文エラー理由コードの設定を行う。   
        //　@［set注文エラー理由コードに渡すパラメタ］   
        //　@　@注文エラー理由コード： null   
        l_mutualFundOrderActionParams.setErrorReasonCode(null);                

        //２）　@引数で与えられた投信注文履歴Paramsを返す。
        log.exiting(STR_METHOD_NAME);
        return l_mutualFundOrderActionParams;
    }
    
    /**
     * (set発注日)
     * this.発注日を返却する。<BR>
     * @@param l_orderBizDate - 発注日<BR>
     * @@roseuid 40AD92050133
     */
    public void setOrderBizDate(Timestamp l_orderBizDate) 
    {
        this.orderBizDate = l_orderBizDate;
    }
    
    /**
     * (get発注日)
     * this.発注日を返却する。<BR>
     * @@return Timestamp
     * @@roseuid 40AD91EC00E5
     */
    public Timestamp getOrderBizDate() 
    {
        return orderBizDate;
    }
    
    /**
     * (set約定日)
     * this.約定日を返却する。<BR>
     * @@param l_executionDate - 約定日<BR>
     * @@roseuid 40AD92050133
     */
    public void setExecutionDate(Timestamp l_executionDate) 
    {
        this.executionDate = l_executionDate;
    }
    
    /**
     * (get約定日)
     * this.約定日を返却する。<BR>
     * @@return Timestamp
     * @@roseuid 40AD91EC00E5
     */
    public Timestamp getExecutionDate() 
    {
        return executionDate;
    }
    
    /**
     * (set受渡日)
     * this.受渡日を返却する。<BR>
     * @@param l_deliveryDate - 受渡日<BR>
     * @@roseuid 40AD92050133
     */
    public void setDeliveryDate(Timestamp l_deliveryDate) 
    {
        this.deliveryDate = l_deliveryDate;
    }
    
    /**
     * (get受渡日)
     * this.受渡日を返却する。<BR>
     * @@return Timestamp
     * @@roseuid 40AD91EC00E5
     */
    public Timestamp getDeliveryDate() 
    {
        return deliveryDate;
    }
    
    /**
     * (set概算受渡代金)
     * this.概算受渡代金を返却する。<BR>
     * @@param l_estimatedPrice - 概算受渡代金<BR>
     * @@roseuid 40AD92050133
     */
    public void setEstimatedPrice(double l_estimatedPrice) 
    {
        this.estimatedPrice = l_estimatedPrice;
    }
    
    /**
     * (get概算受渡代金)
     * this.概算受渡代金を返却する。<BR>
     * @@return Timestamp
     * @@roseuid 40AD91EC00E5
     */
    public double getEstimatedPrice() 
    {
        return estimatedPrice;
    }
    
    /**
     * (set計算基準価額)
     * this.計算基準価額を返却する。<BR>
     * @@param l_constantValue - 計算基準価額<BR>
     * @@roseuid 40AD92050133
     */
    public void setConstantValue(double l_constantValue) 
    {
        this.constantValue = l_constantValue;
    }
    
    /**
     * (get計算基準価額)
     * this.計算基準価額を返却する。<BR>
     * @@return Timestamp
     * @@roseuid 40AD91EC00E5
     */
    public double getConstantValue() 
    {
        return constantValue;
    }
    
    /**
     * (set基準価額適用日)
     * this.基準価額適用日を返却する。<BR>
     * @@param l_constantValueAppDate - 基準価額適用日<BR>
     * @@roseuid 40AD92050133
     */
    public void setConstantValueAppDate(Timestamp l_constantValueAppDate) 
    {
        this.constantValueAppDate = l_constantValueAppDate;
    }
    
    /**
     * (get基準価額適用日)
     * this.基準価額適用日を返却する。<BR>
     * @@return Timestamp
     * @@roseuid 40AD91EC00E5
     */
    public Timestamp getConstantValueAppDate() 
    {
        return constantValueAppDate;
    }

}
@
