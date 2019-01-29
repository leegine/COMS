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
filename	WEB3IfoOpenContractChangeSpec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 新規建訂正内容(WEB3IfoOpenContractChangeSpec.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/6/10 艾興 (中訊) 新規作成
                 : 2006/7/13 肖志偉 (中訊) 仕様変更　@モデル469,478
Revesion History : 2007/06/11 孟亜南 (中訊) 仕様変更モデルNo.677
*/
package webbroker3.ifo;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.ordersubmitter.io.IfoChangeOpenContractOrderSpec;

import webbroker3.ifo.define.WEB3IfoWLimitEnableStatusDivDef;
import webbroker3.util.WEB3LogUtility;

/**
 * 新規建訂正内容<BR>
 * 新規建訂正内容クラス<BR>
 * 新規建訂正内容の入力を表現する。<BR>
 * 注文マネージャに渡すパラメタ。<BR>
 * <BR>
 * xTradeのIfoChangeOrderSpecを拡張したクラス。
 * @@author  艾興
 * @@version 1.0
 */
public class WEB3IfoOpenContractChangeSpec extends IfoChangeOpenContractOrderSpec 
{   
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IfoOpenContractChangeSpec.class);
            
    /**
     * 証券会社コード<BR>
     */
    private String institutionCode;
    
    /**
     * 発注日<BR>
     */
    private Date orderBizDate;
    
    /**
     * 訂正執行条件<BR>
     */
    private IfoOrderExecutionConditionType changeExecCondType;
    
    /**
     * 訂正失効日<BR>
     */
    private Date changeExpirationDate;
    
    /**
     * 発注条件<BR>
     * 　@0：DEFAULT（条件指定なし）　@1：逆指値　@2：W指値<BR>
     */
    private String orderCond;
    
    /**
     * 発注条件演算子<BR>
     * 　@0： Equal<BR>
     * 　@1： 基準値以上<BR>
     * 　@2： 基準値以下<BR>
     */
    private String orderCondOperator;
    
    /**
     * 逆指値基準値タイプ<BR>
     * 　@0： DEFAULT（原資産時価）<BR>
     * 　@1： プレミアム<BR>
     */
    private String stopOrderBasePriceType;
    
    /**
     * 逆指値基準値<BR>
     */
    private double stopOrderBasePrice;
    
    /**
     * （W指値）訂正指値<BR>
     */
    private double wLimitPriceChange;

    /**
     * （W指値）執行条件<BR>
     */
    private IfoOrderExecutionConditionType wLimitExecCondType;
    
    /**
     * （W指値）有効状態区分<BR> 
     * 0： リミット注文有効<BR>  
     * 1： ストップ注文有効<BR>  
     */
    private String wLimitEnableStatusDiv;
    
    /**
     * 訂正後注文期限区分<BR>
     * 1：当日限り　@2：出来るまで注文
     */
    private String expirationDateType;
    
    /**
     * 夕場前繰越対象フラグ<BR>
     * false：夕場前繰越なし<BR>
     * true：夕場前繰越あり<BR>
     */
    private boolean eveningSessionCarryoverFlag;
 
    /**
     * コンストラクタ。<BR>
     * @@param l_lngOrderID - 注文ＩＤ<BR>
     * @@param l_lngOrderUnitID - 注文単位ID<BR>
     * @@param l_dblQuantityAfterChange - 訂正後数量<BR>
     * @@param l_dblLimitPriceAfterChange - 訂正後指値<BR>
     * @@return webbroker3.ifo.WEB3IfoOpenContractChangeSpec
     * @@roseuid 406A367E035C
     */
    public WEB3IfoOpenContractChangeSpec(
        long l_lngOrderID,
        long l_lngOrderUnitID,
        double l_dblQuantityAfterChange,
        double l_dblLimitPriceAfterChange) 
    {        
        super(l_lngOrderID, l_lngOrderUnitID, l_dblQuantityAfterChange, l_dblLimitPriceAfterChange);        
    }
    
    /**
     * (create新規建訂正内容)<BR>
     * （staticメソッド）<BR>
     * 新規建訂正内容オブジェクトを生成し返却する。<BR>
     * @@param l_lngOrderId - (注文ID)<BR>
     * 注文ID<BR>
     * @@param l_lngOrderUnitId - (注文単位ID)<BR>
     * 注文単位ID<BR>
     * @@param l_dblQuantityAfterChange - (訂正後数量)<BR>
     * 訂正後数量<BR>
     * @@param l_dblLimitPriceAfterChange - (訂正後指値)<BR>
     * 訂正後指値<BR>
     * @@param l_changeExecCondType - (訂正執行条件)<BR>
     * 訂正執行条件<BR>
     * @@param l_datChangeExpirationDate - (訂正失効日)<BR>
     * 訂正失効日<BR>
     * @@param l_datOrderBizDate - (発注日)<BR>
     * 発注日<BR>
     * @@param l_strOrderCond - (発注条件)<BR>
     * 発注条件<BR>
     * @@param l_strOrderCondOperator - (発注条件演算子)<BR>
     * 発注条件演算子<BR>
     * @@param l_strStopPriceType - (逆指値基準値タイプ)<BR>
     * 逆指値基準値タイプ<BR>
     * @@param l_dblStopOrderPrice - (逆指値基準値)<BR>
     * 逆指値基準値<BR>
     * @@param l_dblWLimitPrice - ((W指値)訂正指値)<BR>
     * (W指値)訂正指値<BR>
     * @@param l_wLimitExecCondType - ((W指値)執行条件)<BR>
     * (W指値)執行条件<BR>
     * @@param l_strWLimitEnableStatusDiv - ((W指値)有効状態区分)<BR>
     * (W指値)有効状態区分<BR>
     * @@param l_strExpirationDateType - (訂正後注文期限区分)<BR>
     * 訂正後注文期限区分<BR>
     * @@param l_blnEveningSessionCarryoverFlag - (夕場前繰越対象フラグ)<BR>
     * 夕場前繰越対象フラグ<BR>
     * @@return WEB3IfoOpenContractChangeSpec
     */
    public static WEB3IfoOpenContractChangeSpec createIfoOpenContractChangeSpec(
        long l_lngOrderId,
        long l_lngOrderUnitId,
        double l_dblQuantityAfterChange,
        double l_dblLimitPriceAfterChange,
        IfoOrderExecutionConditionType l_changeExecCondType,
        Date l_datChangeExpirationDate,
        Date l_datOrderBizDate,
        String l_strOrderCond,
        String l_strOrderCondOperator,
        String l_strStopPriceType,
        double l_dblStopOrderPrice,
        double l_dblWLimitPrice,
        IfoOrderExecutionConditionType l_wLimitExecCondType,
        String l_strWLimitEnableStatusDiv,
        String l_strExpirationDateType,
        boolean l_blnEveningSessionCarryoverFlag)
    {
        final String STR_METHOD_NAME =  "createIfoOpenContractChangeSpec()";      
        log.entering(STR_METHOD_NAME) ;

        //1.1.IfoChangeOpenContractOrderSpec(arg0 : long, arg1 : long, arg2 : double, arg3 : double)
        WEB3IfoOpenContractChangeSpec l_ifoOpenContractChangeSpec = 
            new  WEB3IfoOpenContractChangeSpec(
                l_lngOrderId, 
                l_lngOrderUnitId, 
                l_dblQuantityAfterChange, 
                l_dblLimitPriceAfterChange);
            
        // 1.2.set訂正執行条件 
        //リクエストデータ執行条件が”無条件”の場合
        l_ifoOpenContractChangeSpec.setChangeExecCondType(l_changeExecCondType);
                
        //1.3.set訂正失効日(Date)
        l_ifoOpenContractChangeSpec.setChangeExpirationDate(l_datChangeExpirationDate);
                
        //1.4.set発注日(Date)
        l_ifoOpenContractChangeSpec.setOrderBizDate(l_datOrderBizDate);
                
        //1.5.set発注条件(String)
        l_ifoOpenContractChangeSpec.setOrderCond(l_strOrderCond);
                
        //1.6.set発注条件演算子(String)
        l_ifoOpenContractChangeSpec.setOrderCondOperator(l_strOrderCondOperator);

        //1.7.set逆指値基準値タイプ
        l_ifoOpenContractChangeSpec.setStopOrderBasePriceType(l_strStopPriceType);
                
        //1.8.set逆指値基準値(double)
        l_ifoOpenContractChangeSpec.setStopOrderBasePrice(l_dblStopOrderPrice);
              
        //1.9.set（W指値）訂正指値(double)
        l_ifoOpenContractChangeSpec.setWLimitPriceChange(l_dblWLimitPrice);
           
        //1.10.set（W指値）執行条件(IfoOrderExecutionConditionType)
        l_ifoOpenContractChangeSpec.setWLimitExecCondType(l_wLimitExecCondType);
            
        //1.11.set（W指値）有効状態区分(String)
        l_ifoOpenContractChangeSpec.setWLimitEnableStatusDiv(l_strWLimitEnableStatusDiv);
            
        //1.12.set訂正後注文期限区分(String)
        l_ifoOpenContractChangeSpec.setExpirationDateType(l_strExpirationDateType);
        
        //夕場前繰越対象フラグ
        l_ifoOpenContractChangeSpec.setEveningSessionCarryoverFlag(l_blnEveningSessionCarryoverFlag);

        log.exiting(STR_METHOD_NAME);
        return l_ifoOpenContractChangeSpec;
    }

    /**
     * (get証券会社コード)<BR>
     * 証券会社コードを取得する。<BR>
     * @@return String
     * @@roseuid 40678E930057
     */
    public String getInstitutionCode() 
    {
        return this.institutionCode;
    }
    
    /**
     * (set証券会社コード)<BR>
     * 証券会社コードをセットする。<BR>
     * @@param l_strInstitutionCode - 証券会社コード<BR>
     * @@roseuid 40678E930058
     */
    public void setInstitutionCode(String l_strInstitutionCode) 
    {
        this.institutionCode = l_strInstitutionCode;
    }
    
    /**
     * (get発注日)<BR>
     * 発注日を取得する。<BR>
     * @@return Date
     * @@roseuid 409F52DD0058
     */
    public Date getOrderBizDate() 
    {
        return this.orderBizDate;
    }
    
    /**
     * (set発注日)<BR>
     * 発注日をセットする。<BR>
     * @@param l_datOrderBizDate - 発注日<BR>
     * @@roseuid 409F52DD0059
     */
    public void setOrderBizDate(Date l_datOrderBizDate) 
    {
        this.orderBizDate = l_datOrderBizDate; 
    }
    
    /**
     * (get訂正執行条件)<BR>
     * 訂正執行条件を取得する。<BR>
     * @@return IfoOrderExecutionConditionType
     * @@roseuid 409F52DD0068
     */
    public IfoOrderExecutionConditionType getChangeExecCondType() 
    {
        return this.changeExecCondType; 
    }
    
    /**
     * (set訂正執行条件)<BR>
     * 訂正執行条件をセットする。<BR>
     * @@param l_executionConditionTypeChange - 訂正執行条件<BR>
     * @@roseuid 409F52DD0069
     */
    public void setChangeExecCondType(IfoOrderExecutionConditionType l_executionConditionTypeChange) 
    {
        this.changeExecCondType = l_executionConditionTypeChange;
    }
    
    /**
     * (get訂正失効日)<BR>
     * 訂正失効日を取得する。<BR>
     * @@return Date
     * @@roseuid 409F52DD0087
     */
    public Date getChangeExpirationDate() 
    {
        return this.changeExpirationDate; 
    }
    
    /**
     * (set訂正失効日)<BR>
     * 訂正失効日をセットする。<BR>
     * @@param l_datExpirationDateChange - 訂正失効日<BR>
     * @@roseuid 409F52DD0088
     */
    public void setChangeExpirationDate(Date l_datExpirationDateChange) 
    {
        this.changeExpirationDate = l_datExpirationDateChange;     
    }
    
    /**
     * (get発注条件)<BR>
     * 発注条件を取得する。<BR>
     * @@return String
     * @@roseuid 409F52DD0097
     */
    public String getOrderCond() 
    {
        return this.orderCond;
    }
    
    /**
     * (set発注条件)<BR>
     * 発注条件をセットする。<BR>
     * @@param l_strOrderCond - 発注条件<BR>
     * @@roseuid 409F52DD0098
     */
    public void setOrderCond(String l_strOrderCond) 
    {
        this.orderCond = l_strOrderCond;     
    }
    
    /**
     * (get発注条件演算子)<BR>
     * 発注条件演算子を取得する。<BR>
     * @@return String
     * @@roseuid 409F52DD009A
     */
    public String getOrderCondOperator() 
    {
        return this.orderCondOperator;
    }
    
    /**
     * (set発注条件演算子)<BR>
     * 発注条件演算子をセットする。<BR>
     * @@param l_strOrderCondOperator - 発注条件演算子<BR>
     * @@roseuid 409F52DD009B
     */
    public void setOrderCondOperator(String l_strOrderCondOperator) 
    {
        this.orderCondOperator = l_strOrderCondOperator;   
    }
    
    /**
     * (get逆指値基準値タイプ)<BR>
     * 逆指値基準値タイプを取得する。<BR>
     * @@return String
     * @@roseuid 409F52DD009D
     */
    public String getStopOrderBasePriceType() 
    {
        return this.stopOrderBasePriceType;
    }
    
    /**
     * (set逆指値基準値タイプ)<BR>
     * 逆指値基準値タイプをセットする。<BR>
     * @@param l_strStopOrderBasePriceType - 逆指値基準値タイプ<BR>
     * @@roseuid 409F52DD00A6
     */
    public void setStopOrderBasePriceType(String l_strStopOrderBasePriceType) 
    {
        this.stopOrderBasePriceType = l_strStopOrderBasePriceType;    
    }
    
    /**
     * (get逆指値基準値)<BR>
     * 逆指値基準値を取得する。<BR>
     * @@return double
     * @@roseuid 409F52DD00A8
     */
    public double getStopOrderBasePrice() 
    {
        return this.stopOrderBasePrice;
    }
    
    /**
     * (set逆指値基準値)<BR>
     * 逆指値基準値をセットする。<BR>
     * @@param l_dblStopOrderBasePrice - 逆指値基準値<BR>
     * @@roseuid 409F52DD00A9
     */
    public void setStopOrderBasePrice(double l_dblStopOrderBasePrice) 
    {
        this.stopOrderBasePrice = l_dblStopOrderBasePrice;   
    }
    
    /**
     * (set（W指値）訂正指値)<BR>
     * (W指値)訂正指値をセットする。<BR>
     * @@param l_dblWLimitPriceChange - (W指値)訂正指値<BR>
     * @@roseuid 409F52DD00AB
     */
    public void setWLimitPriceChange(double l_dblWLimitPriceChange) 
    {
        this.wLimitPriceChange = l_dblWLimitPriceChange;     
    }
    
    /**
     * (get（W指値）訂正指値)<BR>
     * (W指値)訂正指値を取得する。<BR>
     * @@return double
     * @@roseuid 409F52DD00AD
     */
    public double getWLimitPriceChange() 
    {
     return this.wLimitPriceChange;
    }
    
    /**
     * (get（W指値）執行条件)<BR>
     * （W指値）執行条件を取得する。<BR>
     * @@return IfoOrderExecutionConditionType
     */
    public IfoOrderExecutionConditionType getWLimitExecCondType()
    {
        return this.wLimitExecCondType;
    }
    
    /**
     * (set（W指値）執行条件)<BR>
     * （W指値）執行条件をセットする。<BR>
     * @@param l_wLimitExecCondType - ((W指値)執行条件)<BR>
     * （W指値）執行条件 
     */
    public void setWLimitExecCondType(
        IfoOrderExecutionConditionType l_wLimitExecCondType)
    {
       this.wLimitExecCondType = l_wLimitExecCondType;
    }
    
    /**
     * (get（W指値）有効状態区分)<BR>
     * （W指値）有効状態区分を取得する。<BR>
     * @@return String
     */
    public String getWLimitEnableStatusDiv()
    {
        return this.wLimitEnableStatusDiv;
    }
    
    /**
     * (set（W指値）有効状態区分)<BR>
     * （W指値）有効状態区分をセットする。<BR>
     * @@param l_strWLimitEnableStatusDiv - (（W指値）有効状態区分)<BR>
     * （W指値）有効状態区分
     */
    public void setWLimitEnableStatusDiv(String l_strWLimitEnableStatusDiv)
    {
        this.wLimitEnableStatusDiv = l_strWLimitEnableStatusDiv;
    }
    
    /**
     * (set訂正後注文期限区分)<BR>
     * 訂正後注文期限区分をセットする。<BR>
     * @@param l_strExpirationDateType - 訂正後注文期限区分<BR>
     */
    public void setExpirationDateType(String l_strExpirationDateType) 
    {
        this.expirationDateType = l_strExpirationDateType;     
    }
    /**
     * (get訂正後注文期限区分)<BR>
     * 訂正後注文期限区分を取得する。<BR>
     * @@return String
     */
    public String getExpirationDateType() 
    {
        return this.expirationDateType;
    }
    
    /**
     * (isストップ注文有効)<BR>
     * this.（W指値）有効状態区分 == "ストップ注文有効"の場合、true、 <BR>
     * 以外、falseを返却する。<BR>
     * @@return boolean
     */
    public boolean isStopOrderOpen()
    {
        final String STR_METHOD_NAME = "isStopOrderOpen()";      
        log.entering(STR_METHOD_NAME) ;

        if ( WEB3IfoWLimitEnableStatusDivDef.STOP_ENABLE.equals(this.wLimitEnableStatusDiv))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        log.exiting(STR_METHOD_NAME);
        return false;
    }
    
    /**
     * (get夕場前繰越対象フラグ)<BR>
     * 夕場前繰越対象フラグを取得する。<BR>
     * @@return boolean
     */
    public boolean getEveningSessionCarryoverFlag()
    {
        return this.eveningSessionCarryoverFlag;
    }

    /**
     * (set夕場前繰越対象フラグ)<BR>
     * 夕場前繰越対象フラグを設定する。<BR>
     * @@param l_blnEveningSessionCarryoverFlag - (夕場前繰越対象フラグ)<BR>
     * 夕場前繰越対象フラグ
     */
    public void setEveningSessionCarryoverFlag(boolean l_blnEveningSessionCarryoverFlag)
    {
        this.eveningSessionCarryoverFlag = l_blnEveningSessionCarryoverFlag;
    }
}
@
