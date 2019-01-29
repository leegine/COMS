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
filename	WEB3MutualFundTradeOrderNotifyConfirmInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託売買注文通知確定インタセプタ(WEB3MutualFundTradeOrderNotifyConfirmInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/06 周勇 (中訊) 新規作成
                   2004/08/20 黄建 (中訊) レビュー   
                   2004/12/06 于美麗 (中訊) 残対応
                   2005/10/21 韋念瓊 (中訊) フィデリティ対応
*/
package webbroker3.mf;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitParams;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ChannelDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * 投資信託売買注文通知確定インタセプタ<BR>
 * 
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3MutualFundTradeOrderNotifyConfirmInterceptor
    extends WEB3DefaultMutualFundOrderConfirmInterceptor
{
    /**
     * 受渡日<BR>
     */
    protected Timestamp deliveryDate;
    
    /**
     * 発注日<BR>
     */
    protected Timestamp BizDate;
    
    /**
     * 受注日時<BR>
     */
    protected Timestamp acceptDate;
    
    /**
     * 識別コード<BR>
     */
    protected String discriminationCode;
    
    /**
     * 計算基準価額<BR>
     */
    protected double constantValue;
    
    /**
     * 計算基準価額（乗換先）<BR>
     */
    protected double switchingConstantValue;
    
    /**
     * 概算受渡代金<BR>
     */
    protected double estimatedPrice;
    
    /**
     * 概算売買口数<BR>
     */
    protected double estimatedQty;
    
    /**
     * 受渡方法@<BR>
     */
    protected String deliveryDiv;
    
    /**
     * 投信タイプ<BR>
     * <BR>
     * 0：その他　@1：国内　@2：国外<BR>
     */
    protected String mutualFundType;
    
    /**
     * 約定日<BR>
     */
    protected Timestamp executionTimestamp;
    
    /**
     * 決済区分<BR>
     * <BR>
     * 1：円貨　@2：外貨<BR>
     */
    protected String settlementType;
    
    /**
     * 無手数料区分<BR>
     * <BR>
     * 空白：無関係　@5：乗換優遇　@9：無手数料<BR>
     */
    protected String noCommissionDivision;
    
    /**
     * 銘柄コード（乗換先）<BR>
     */
    protected String switchingSubjectMutualProductCode;
    
    /**
     * 請求区分<BR>
     * <BR>
     * 0：解約　@1：買取<BR>
     */
    protected String requestDivision;
    
    /**
     * 扱者コード（SONAR）<BR>
     */
    protected String sonarTraderCode;
    
    /**
     * 投信解約区分<BR>
     */
    protected String mutualFundSellDiv;
    
    /**
     * 基準価額適用日<BR>
     */
    protected Timestamp constantValueAppDate;
    
    /**
     * 概算買付口数（乗換先）<BR>
     */
    protected double switchingEstimatedQty;
    
    /**
     * 税区分（乗換先）<BR>
     */
    protected TaxTypeEnum switchingSubjectTaxDivision;
    
    /**
     * 注文種別<BR>
     */
    protected OrderTypeEnum orderType;
    
    /**
     * CPU No<BR>
     *（SONARでの注文の管理番号） 
     */
    protected String CPUNo;
    
        
    /**
     * 入金日<BR>
     */
    protected Timestamp paymentDate;

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3MutualFundTradeOrderNotifyConfirmInterceptor.class);
            
    /**
     * (投信売買注文通知確定インタセプタ)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40AD8E1C0327
     */
    public WEB3MutualFundTradeOrderNotifyConfirmInterceptor()
    {
    }
    
    /**
     * (更新値設定)<BR>
     * （mutateの実装）<BR>
     * <BR>
     * 注文・取消処理の中で、投信注文履歴データの作成・更新時に呼ばれる。<BR>
     * 引数で与えられた投信注文履歴Paramsに値を設定し、投信注文履歴Paramsを返す。 <BR>
     * <BR>
     * １）　@注文エラー理由コードの設定を行う。 <BR>
     * 　@－投信注文履歴Params.set注文エラー理由コード()をコールし、<BR>
     * 注文エラー理由コードの設定を行う。 <BR>
     * 　@　@［set注文エラー理由コードに渡すパラメタ］ <BR>
     * 　@　@　@注文エラー理由コード： null <BR>
     * <BR>
     * ２）　@引数で与えられた投信注文履歴Paramsを返す。 <BR>
     * <BR>
     * @@param l_orderManagerPersistenceType - (永続化タイプ)<BR>
     * データの更新または挿入中に呼ぶかどうか指定<BR>
     * @@param l_orderManagerPersistenceContext - (永続化コンテキスト)<BR>
     * 呼び出し時のコンテキストを指定。例えば現物注文中など。<BR>
     * @@param l_mutualFundOrderActionParams - (投信注文履歴Params)<BR>
     * 永続化前の投信注文履歴Params<BR>
     * @@return MutualFundOrderActionParams
     * @@roseuid 40AD90DC0097
     */
    public MutualFundOrderActionParams mutate(
        OrderManagerPersistenceType l_orderManagerPersistenceType,
        OrderManagerPersistenceContext l_orderManagerPersistenceContext,
        MutualFundOrderActionParams l_mutualFundOrderActionParams)
    {
        final String STR_METHOD_NAME =
            "mutate(OrderManagerPersistenceType l_orderManagerPersistenceType," +
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
        
        //１）　@注文エラー理由コードの設定を行う
        l_mutualFundOrderActionParams.setErrorReasonCode(null);
        
        log.exiting(STR_METHOD_NAME);
        //２）　@引数で与えられた投信注文履歴Paramsを返す
        return l_mutualFundOrderActionParams;
    }
    /**
     * (更新値設定)<BR>
     * （mutateの実装）<BR>
     * <BR>
     * 注文・取消処理の中で、投信注文単位データの作成・更新時に呼ばれる。<BR>
     * 引数で与えられた投信注文単位Paramsに値を設定し、投信注文単位Paramsを返す。 <BR>
     * <BR>
     * １）　@当オブジェクトに設定されている値を投信注文単位Paramsに設定する。 <BR>
     * <BR>
     *     投信注文単位Params.set注文種別(this.get注文種別( )) <BR>
     * 　@　@投信注文単位Params.set発注日(this.get発注日( ))  <BR>
     * 　@　@投信注文単位Params.set受渡日(this.get受渡日( ))<BR>
     * 　@　@投信注文単位Params.set受注日時(this.get受注日時( ))<BR>
     * 　@　@投信注文単位Params.set扱者コード（SONAR）(this.get扱者コード（SONAR）( ))<BR>
     * 　@　@投信注文単位Params.set識別コード(this.get識別コード( ))<BR>
     * 　@　@投信注文単位Params.set計算基準価額(this.get計算基準価額( ))<BR>
     * 　@　@投信注文単位Params.set基準価額適用日(this.get基準価額適用日( ))<BR>
     * 　@　@投信注文単位Params.set概算受渡代金(this.get概算受渡代金( ))<BR>
     * 　@　@投信注文単位Params.set概算売買口数(this.get概算売買口数( ))<BR>
     * 　@　@投信注文単位Params.set税区分（乗換先）(this.get税区分（乗換先）( ))<BR>
     * 　@　@投信注文単位Params.set受渡方法@(this.get受渡方法@( ))<BR>
     * 　@　@投信注文単位Params.set投信タイプ(this.get投信タイプ( ))<BR>
     * 　@　@投信注文単位Params.set約定日(this.get約定日( ))<BR>
     * 　@　@投信注文単位Params.set決済区分(this.get決済区分( ))<BR>
     * 　@　@投信注文単位Params.set無手数料区分(this.get無手数料区分( ))<BR>
     * 　@　@投信注文単位Params.set銘柄コード（乗換先）(this.get銘柄コード（乗換先）( ))<BR>
     * 　@　@投信注文単位Params.set請求区分(this.get請求区分( ))<BR>
     * 　@　@投信注文単位Params.set投信解約区分(this.get投信解約区分( )) <BR>
     *     投信注文単位Params.set入金日(this.get入金日( )) <BR>
     * <BR>
     * ２）　@初回注文の注文チャネルの設定を行う<BR>
     * 　@－投信注文単位Params.set初回注文の注文チャネル()をコールし、<BR>
     * 初回注文の注文チャネルの設定を行う。 <BR>
     * 　@　@［set初回注文の注文チャネルに渡すパラメタ］ <BR>
     * 　@　@　@初回注文の注文チャネル： ”0：営業店”<BR>
     * <BR>
     * ３）　@注文経路区分の設定を行う<BR>
     * 　@－投信注文単位Params.set注文経路区分()をコールし、注文経路区分の設定を行う。 <BR>
     * 　@　@［set注文経路区分に渡すパラメタ］ <BR>
     * 　@　@　@注文経路区分： ”9：HOST”<BR>
     * <BR>
     * ４）　@投信注文単位Paramsオブジェクトに、以下の設定を行う。<BR>
     * 　@　@投信注文単位Params.set約定状態(null)<BR>
     * 　@　@投信注文単位Params.set注文エラー理由コード(null)<BR>
     * <BR>
     * ５）　@計算基準価額（乗換先）の設定を行う。<BR>
     * 　@－投信注文単位Params.set計算基準価額（乗換先）()をコールし、<BR>
     * 計算基準価額（乗換先）の設定を行う。<BR>
     * 　@　@［set計算基準価額（乗換先）に渡すパラメタ］<BR>
     * 　@　@　@　@(*) Double.isNan(this.get計算基準価額（乗換先）())がtrueを返す場合は、<BR>
     * 　@　@　@　@　@　@(Double)nullを設定する。<BR>
     * 　@　@　@　@(*) Double.isNan(this.get計算基準価額（乗換先）())がfalseを返す場合は、<BR>
     * 　@　@　@　@　@　@this.get計算基準価額（乗換先）()の戻り値を設定する。<BR>
     * <BR>
     * ６）　@概算買付口数（乗換先）の設定を行う。<BR>
     * 　@－投信注文単位Params.set概算買付口数（乗換先）()をコールし、<BR>
     * 概算買付口数（乗換先）の設定を行う。<BR>
     * 　@　@［set概算買付口数（乗換先）に渡すパラメタ］<BR>
     * 　@　@　@　@(*) Double.isNan(this.get概算買付口数（乗換先）())がtrueを返す場合は、<BR>
     * 　@　@　@　@　@　@(Double)nullを設定する。<BR>
     * 　@　@　@　@(*) Double.isNan(this.get概算買付口数（乗換先）())がfalseを返す場合は、<BR>
     * 　@　@　@　@　@　@this.get概算買付口数（乗換先）()の戻り値を設定する。<BR>
     * <BR>
     * ７）　@引数で与えられた投信注文単位Paramsを返す。 <BR>
     * @@param l_orderManagerPersistenceType - (永続化タイプ)<BR>
     * データの更新または挿入中に呼ぶかどうか指定<BR>
     * @@param l_orderManagerPersistenceContext - (永続化コンテキスト)<BR>
     * 呼び出し時のコンテキストを指定。例えば現物注文中など。<BR>
     * @@param l_mutualFundOrderUnitParams - (投信注文単位Params)<BR>
     * 永続化前の投信注文単位Params<BR>
     * @@return MutualFundOrderUnitParams
     * @@roseuid 40AD90DC00B6
     */
    public MutualFundOrderUnitParams mutate(
        OrderManagerPersistenceType l_orderManagerPersistenceType,
        OrderManagerPersistenceContext l_orderManagerPersistenceContext,
        MutualFundOrderUnitParams l_mutualFundOrderUnitParams)
    {
        final String STR_METHOD_NAME =
            "mutate(OrderManagerPersistenceType l_orderManagerPersistenceType," +
            "OrderManagerPersistenceContext l_orderManagerPersistenceContext," +
            " MutualFundOrderUnitParams l_mutualFundOrderUnitParams)";
        log.entering(STR_METHOD_NAME);
        if (l_mutualFundOrderUnitParams == null)
        {
            log.debug("the parameter of method is null");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }         
        
        //１）　@当オブジェクトに設定されている値を投信注文単位Paramsに設定する 
        // 投信注文単位Params.set発注日(this.get発注日( )) 
        String l_strBizDate = 
            WEB3DateUtility.formatDate(
                    this.getBizDate(),
                    "yyyyMMdd");
        l_mutualFundOrderUnitParams.setBizDate(l_strBizDate);  
        
        //投信注文単位Params.set注文種別(this.get注文種別( )) 
        l_mutualFundOrderUnitParams.setOrderType(this.getOrderType());
        
        //投信注文単位Params.set受渡日(this.get受渡日( ))
        l_mutualFundOrderUnitParams.setDeliveryDate(this.deliveryDate);
        
        //投信注文単位Params.set受注日時(this.get受注日時( ))
        l_mutualFundOrderUnitParams.setReceivedDateTime(this.acceptDate);

        //投信注文単位Params.set扱者コード（SONAR）(this.get扱者コード（SONAR）( )) 
        l_mutualFundOrderUnitParams.setSonarTraderCode(this.sonarTraderCode);
        
        //投信注文単位Params.set識別コード(this.get識別コード( )) 
        l_mutualFundOrderUnitParams.setOrderRequestNumber(
            this.discriminationCode);
        
        //投信注文単位Params.set計算基準価額(this.get計算基準価額( ))
        l_mutualFundOrderUnitParams.setCalcConstantValue(this.constantValue);
        
        //投信注文単位Params.set基準価額適用日(this.get基準価額適用日( ))
        l_mutualFundOrderUnitParams.setConstantValueAppDate(
            this.constantValueAppDate);
        
        //投信注文単位Params.set概算受渡代金(this.get概算受渡代金( ))
        l_mutualFundOrderUnitParams.setEstimatedPrice(
            this.estimatedPrice);
        
        //投信注文単位Params.set概算売買口数(this.get概算売買口数( ))
        l_mutualFundOrderUnitParams.setEstimateDealingQty(
            this.estimatedQty);
        
        //投信注文単位Params.set税区分（乗換先）(this.get税区分（乗換先）( ))
        l_mutualFundOrderUnitParams.setSwtTaxType(
            this.switchingSubjectTaxDivision);
        
        //投信注文単位Params.set受渡方法@(this.get受渡方法@( ))
        l_mutualFundOrderUnitParams.setPaymentMethod(this.deliveryDiv);
        
        //投信注文単位Params.set投信タイプ(this.get投信タイプ( ))
        if (MutualFundTypeEnum.DOMESTIC.intValue() == Integer.parseInt(this.mutualFundType))
        {
            l_mutualFundOrderUnitParams.setFundType(
                MutualFundTypeEnum.DOMESTIC);
        }
        if (MutualFundTypeEnum.FOREIGN.intValue() == Integer.parseInt(this.mutualFundType))
        {
            l_mutualFundOrderUnitParams.setFundType(MutualFundTypeEnum.FOREIGN);
        }
        if (MutualFundTypeEnum.OTHER.intValue() == Integer.parseInt(this.mutualFundType))
        {
            l_mutualFundOrderUnitParams.setFundType(MutualFundTypeEnum.OTHER);
        }
        
        //投信注文単位Params.set約定日(this.get約定日( ))
        l_mutualFundOrderUnitParams.setExecDate(this.executionTimestamp);
        
        //投信注文単位Params.set決済区分(this.get決済区分( ))
        l_mutualFundOrderUnitParams.setSettlementDiv(this.settlementType);
        
        //投信注文単位Params.set無手数料区分(this.get無手数料区分( ))
        l_mutualFundOrderUnitParams.setNoContractCommissionDiv(
            this.noCommissionDivision);
        
        //投信注文単位Params.set銘柄コード（乗換先）(this.get銘柄コード（乗換先）( ))
        l_mutualFundOrderUnitParams.setSwtProductCode(
            this.switchingSubjectMutualProductCode);
        
        //投信注文単位Params.set請求区分(this.get請求区分( ))
        l_mutualFundOrderUnitParams.setRequestDiv(this.requestDivision);
        
        //投信注文単位Params.set投信解約区分(this.get投信解約区分( ))
        l_mutualFundOrderUnitParams.setFundSellDiv(this.mutualFundSellDiv);
        
		//投信注文単位Params.set入金日(this.get入金日( )) 
        l_mutualFundOrderUnitParams.setPaymentDate(this.getPaymentDate());
        
        l_mutualFundOrderUnitParams.setCpuNo(this.getCPUNo());
        
        //２）　@初回注文の注文チャネルの設定を行う
        //  －投信注文単位Params.set初回注文の注文チャネル()をコールし、初回注文の注文チャネルの設定を行う
        l_mutualFundOrderUnitParams.setOrderChanel(WEB3ChannelDef.BRANCH);
        
        //４）　@注文経路区分の設定を行う
        //  －投信注文単位Params.set注文経路区分()をコールし、注文経路区分の設定を行う。
        l_mutualFundOrderUnitParams.setOrderRootDiv(WEB3OrderRootDivDef.HOST);
        
        //５）　@投信注文単位Paramsオブジェクトに、以下の設定を行う
        //投信注文単位Params.set約定状態(null)
        //投信注文単位Params.set注文エラー理由コード(null)
        l_mutualFundOrderUnitParams.setExecStatus(null);
        l_mutualFundOrderUnitParams.setErrorReasonCode(null);
        
        //６）　@計算基準価額（乗換先）の設定を行う。
        if (Double.isNaN(this.getSwitchingConstantValue()) == true)
        {
            l_mutualFundOrderUnitParams.setSwtCalcConstantValue(null);
        }
        else if (Double.isNaN(this.getSwitchingConstantValue()) == false)
        {
            l_mutualFundOrderUnitParams.setSwtCalcConstantValue(
                this.getSwitchingConstantValue());
        }
       
        //７）　@概算買付口数（乗換先）の設定を行う。
        if (Double.isNaN(this.getSwitchingEstimatedQty()) == true)
        {
            l_mutualFundOrderUnitParams.setSwtEstimateDealingQty(null);
        }
        else if (Double.isNaN(this.getSwitchingEstimatedQty()) == false)
        {
            l_mutualFundOrderUnitParams.setSwtEstimateDealingQty(
                this.getSwitchingEstimatedQty());
        }
        log.exiting(STR_METHOD_NAME);
    
        //８）　@引数で与えられた投信注文単位Paramsを返す
        return l_mutualFundOrderUnitParams;
    }
    
    /**
     * 受渡日の設定を行う。<BR>
     * @@param l_tsDeliveryDate - 受渡日<BR>
     * @@roseuid 40AD9C050385
     */
    public void setDeliveryDate(Timestamp l_tsDeliveryDate)
    {
        this.deliveryDate = l_tsDeliveryDate;
    }
    
    /**
     * this.受渡日を返す。<BR>
     * @@return Timestamp
     * @@roseuid 40AD9C0C03B4
     */
    public Timestamp getDeliveryDate()
    {
        return this.deliveryDate;
    }
    
    /**
     * 発注日の設定を行う。<BR>
     * @@param l_tsDeliveryDate - 発注日<BR>
     * @@roseuid 40AD9C050385
     */
    public void setBizDate(Timestamp l_tsDeliveryDate)
    {
        this.BizDate = l_tsDeliveryDate;
    }
    
    /**
     * this.発注日を返す。<BR>
     * @@return Timestamp
     * @@roseuid 40AD9C0C03B4
     */
    public Timestamp getBizDate()
    {
        return this.BizDate;
    }
   
    /**
     * 受注日時の設定を行う。<BR>
     * @@param l_tsAcceptDate - 受注日時<BR>
     * @@roseuid 40AD9BD90308
     */
    public void setAcceptDate(Timestamp l_tsAcceptDate)
    {
        this.acceptDate = l_tsAcceptDate;
    }
   
    /**
     * this.受注日時を返す。<BR>
     * @@return Timestamp
     * @@roseuid 40AD9BD90317
     */
    public Timestamp getAcceptDate()
    {
        return this.acceptDate;
    }
   
    /**
     * 識別コードの設定を行う。<BR>
     * @@param l_strDiscriminationCode - 識別コード<BR>
     * @@roseuid 40AD9BD90356
     */
    public void setDiscriminationCode(String l_strDiscriminationCode)
    {
        this.discriminationCode = l_strDiscriminationCode;
    }
   
    /**
     * this.識別コードを返す。<BR>
     * @@return String
     * @@roseuid 40AD9BD90375
     */
    public String getDiscriminationCode()
    {
        return this.discriminationCode;
    }
   
    /**
     * 計算基準価額の設定を行う。<BR>
     * @@param l_dblConstantValue - 基準価額<BR>
     * @@roseuid 40AD9BD90394
     */
    public void setConstantValue(double l_dblConstantValue)
    {
        this.constantValue = l_dblConstantValue;
    }
   
    /**
     * this.計算基準価額を返す。<BR>
     * @@return double
     * @@roseuid 40AD9BD903A4
     */
    public double getConstantValue()
    {
        return this.constantValue;
    }
   
    /**
     * 計算基準価額（乗換先）の設定を行う。<BR>
     * @@param l_dblSwitchingConstantValue - 基準価額（乗換先）<BR>
     * @@roseuid 40D7D33600D8
     */
    public void setSwitchingConstantValue(double l_dblSwitchingConstantValue)
    {
        this.switchingConstantValue = l_dblSwitchingConstantValue;
    }
   
    /**
     * this.計算基準価額（乗換先）を返す。<BR>
     * @@return double
     * @@roseuid 40D7D33600E8
     */
    public double getSwitchingConstantValue()
    {
        return this.switchingConstantValue;
    }
   
    /**
     * 概算受渡代金の設定を行う。<BR>
     * @@param l_dblEstimatedPrice - 概算受渡代金<BR>
     * @@roseuid 40AD9BD903C3
     */
    public void setEstimatedPrice(double l_dblEstimatedPrice)
    {
        this.estimatedPrice = l_dblEstimatedPrice;
    }
   
    /**
     * this.概算受渡代金を返す。<BR>
     * @@return double
     * @@roseuid 40AD9BD903E3
     */
    public double getEstimatedPrice()
    {
        return this.estimatedPrice;
    }
   
    /**
     * 概算売買口数の設定を行う。<BR>
     * @@param l_dblEstimatedQty - 概算売買口数<BR>
     * @@roseuid 40AD9BDA001A
     */
    public void setEstimatedQty(double l_dblEstimatedQty)
    {
        this.estimatedQty = l_dblEstimatedQty;
    }
   
    /**
     * this.概算売買口数を返す。<BR>
     * @@return double
     * @@roseuid 40AD9BDA0029
     */
    public double getEstimatedQty()
    {
        return this.estimatedQty;
    }
   
    /**
     * 受渡方法@の設定を行う。<BR>
     * @@param l_strDeliveryDiv - 受渡方法@<BR>
     * @@roseuid 40AD9BDA0049
     */
    public void setDeliveryDiv(String l_strDeliveryDiv)
    {
        this.deliveryDiv = l_strDeliveryDiv;
    }
   
    /**
     * this.受渡方法@を返す。<BR>
     * @@return String
     * @@roseuid 40AD9BDA0068
     */
    public String getDeliveryDiv()
    {
        return this.deliveryDiv;
    }
   
    /**
     * 投信タイプの設定を行う。<BR>
     * @@param l_strMutualFundType - 投信タイプ<BR>
     * @@roseuid 40AD9BDA0087
     */
    public void setMutualFundType(String l_strMutualFundType)
    {
        this.mutualFundType = l_strMutualFundType;
    }
   
    /**
     * this.投信タイプを返す。<BR>
     * @@return String
     * @@roseuid 40AD9BDA0097
     */
    public String getMutualFundType()
    {
        return this.mutualFundType;
    }
   
    /**
     * 約定日の設定を行う。<BR>
     * @@param l_tsExecutionTimestamp - 約定日<BR>
     * @@roseuid 40AD9BDA00B6
     */
    public void setExecutionTimestamp(Timestamp l_tsExecutionTimestamp)
    {
        this.executionTimestamp = l_tsExecutionTimestamp;
    }
   
    /**
     * this.約定日を返す。<BR>
     * @@return Timestamp
     * @@roseuid 40AD9BDA00D5
     */
    public Timestamp getExecutionTimestamp()
    {
        return this.executionTimestamp;
    }
   
    /**
     * 決済区分の設定を行う。<BR>
     * @@param l_strSettlementType - 決済区分<BR>
     * @@roseuid 40AD9BDA0133
     */
    public void setSettlementType(String l_strSettlementType)
    {
        this.settlementType = l_strSettlementType;
    }
   
    /**
     * this.決済区分を返す。<BR>
     * @@return String
     * @@roseuid 40AD9BDA0152
     */
    public String getSettlementType()
    {
        return this.settlementType;
    }
   
    /**
     * 無手数料区分の設定を行う。<BR>
     * @@param l_strNoCommissionDivision - 無手数料区分<BR>
     * @@roseuid 40AD9BDA0172
     */
    public void setNoCommissionDivision(String l_strNoCommissionDivision)
    {
        this.noCommissionDivision = l_strNoCommissionDivision;
    }
   
    /**
     * this.無手数料区分を返す。<BR>
     * @@return String
     * @@roseuid 40AD9BDA0191
     */
    public String getNoCommissionDivision()
    {
        return this.noCommissionDivision;
    }
   
    /**
     * 銘柄コード（乗換先）の設定を行う。<BR>
     * @@param l_strSwitchingSubjectMutualProductCode - 銘柄コード（乗換先）<BR>
     * @@roseuid 40AD9BDA01FE
     */
    public void setSwitchingSubjectMutualProductCode(String l_strSwitchingSubjectMutualProductCode)
    {
        this.switchingSubjectMutualProductCode =
            l_strSwitchingSubjectMutualProductCode;
    }
   
    /**
     * this.銘柄コード（乗換先）を返す。<BR>
     * @@return String
     * @@roseuid 40AD9BDA021D
     */
    public String getSwitchingSubjectMutualProductCode()
    {
        return this.switchingSubjectMutualProductCode;
    }
   
    /**
     * 請求区分の設定を行う。<BR>
     * @@param l_strRequestDivision - 請求区分<BR>
     * @@roseuid 40AD9BDA023D
     */
    public void setRequestDivision(String l_strRequestDivision)
    {
        this.requestDivision = l_strRequestDivision;
    }
   
    /**
     * this.請求区分を返す。<BR>
     * @@return String
     * @@roseuid 40AD9BDA025C
     */
    public String getRequestDivision()
    {
        return this.requestDivision;
    }
   
    /**
     * 扱者コード（SONAR）の設定を行う。<BR>
     * @@param l_strSonarTraderCode - 扱者コード（SONAR）<BR>
     * @@roseuid 40AD9BDA027B
     */
    public void setSonarTraderCode(String l_strSonarTraderCode)
    {
        this.sonarTraderCode = l_strSonarTraderCode;
    }
   
    /**
     * this.扱者コード（SONAR）を返す。<BR>
     * @@return String
     * @@roseuid 40AD9BDA029A
     */
    public String getSonarTraderCode()
    {
        return this.sonarTraderCode;
    }
   
    /**
     * 投信解約区分の設定を行う。<BR>
     * @@param l_strMutualFundSellDiv - 投信解約区分<BR>
     * @@roseuid 40AD9BDA02BA
     */
    public void setMutualFundSellDiv(String l_strMutualFundSellDiv)
    {
        this.mutualFundSellDiv = l_strMutualFundSellDiv;
    }
   
    /**
     * this.投信解約区分を返す。<BR>
     * @@return String
     * @@roseuid 40AD9BDA02D9
     */
    public String getMutualFundSellDiv()
    {
        return this.mutualFundSellDiv;
    }
   
    /**
     * 基準価額適用日の設定を行う。<BR>
     * @@param l_tsConstantValueAppDate - 基準価額適用日<BR>
     * @@roseuid 40CFE62D02FD
     */
    public void setConstantValueAppDate(Timestamp l_tsConstantValueAppDate)
    {
        this.constantValueAppDate = l_tsConstantValueAppDate;
    }
   
    /**
     * this.基準価額適用日を返す。<BR>
     * @@return Timestamp
     * @@roseuid 40CFE62D030C
     */
    public Timestamp getConstantValueAppDate()
    {
        return this.constantValueAppDate;
    }
   
    /**
     * 概算買付口数（乗換先）の設定を行う。<BR>
     * @@param l_dblSwitchingEstimatedQty - 乗換先概算買付口数<BR>
     * @@roseuid 40D2BCFF0159
     */
    public void setSwitchingEstimatedQty(double l_dblSwitchingEstimatedQty)
    {
        this.switchingEstimatedQty = l_dblSwitchingEstimatedQty;
    }
   
    /**
     * this.概算買付口数（乗換先）を返す。<BR>
     * @@return double
     * @@roseuid 40D2BCFF0169
     */
    public double getSwitchingEstimatedQty()
    {
        return this.switchingEstimatedQty;
    }
   
    /**
     * 税区分（乗換先）の設定を行う。<BR>
     * @@param l_switchingSubjectTaxDivision - 税区分（乗換先）<BR>
     * @@roseuid 40D2BCFF016A
     */
    public void setSwitchingSubjectTaxDivision(TaxTypeEnum l_switchingSubjectTaxDivision)
    {
        this.switchingSubjectTaxDivision = l_switchingSubjectTaxDivision;
    }
   
    /**
     * this.税区分（乗換先）を返す。<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum
     * @@roseuid 40D2BCFF0178
     */
    public TaxTypeEnum getSwitchingSubjectTaxDivision()
    {
        return this.switchingSubjectTaxDivision;
    }
    
    /**
     * (set注文種別)
     * 注文種別の設定を行う。<BR>
     * @@param l_orderType - 注文種別<BR>
     * @@roseuid 40AD92050133
     */
    public void setOrderType(OrderTypeEnum l_orderType) 
    {
        this.orderType = l_orderType;
    }
    
    /**
     * (get注文種別)
     * this.注文種別を返す。<BR>
     * @@return OrderTypeEnum
     * @@roseuid 40AD91EC00E5
     */
    public OrderTypeEnum getOrderType() 
    {
        return orderType;
    }
    
    /**
     * (set入金日)
     * 入金日の設定を行う。<BR>
     * @@param l_paymentDate - 入金日<BR>
     * @@roseuid 40AD92050133
     */
    public void setPaymentDate(Timestamp l_paymentDate) 
    {
        this.paymentDate = l_paymentDate;
    }
    
    /**
     * (get入金日)
     * this.入金日を返す。<BR>
     * @@return Timestamp
     * @@roseuid 40AD91EC00E5
     */
    public Timestamp getPaymentDate() 
    {
        return paymentDate;
    }  
    
    /**
     * (setCPUNo)
     * CPUNoの設定を行う。<BR>
     * @@param CPUNo - String <BR>
     * @@roseuid 40AD92050133
     */
    public void setCPUNo(String l_strCpuNo) 
    {
        this.CPUNo = l_strCpuNo;
    }
    
    /**
     * (getCPUNo)
     * this.CPUNoを返す。<BR>
     * @@return String
     * @@roseuid 40AD91EC00E5
     */
    public String getCPUNo() 
    {
        return CPUNo;
    }
}
@
