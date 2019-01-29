head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.36.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPCashDepositRestraintReflector.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 預り金担保出金拘束金(WEB3TPCashDepositRestraintReflector.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/11 徐宏偉 (中訊) 新規作成
*/
package webbroker3.tradingpower.updtpower.cash;

import java.util.Date;

import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.tradingpower.util.ToStringUtils;
import webbroker3.util.WEB3LogUtility;

/**
 * (預り金担保出金拘束金) <BR>
 * <BR>
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public class WEB3TPCashDepositRestraintReflector extends WEB3TPRestraintReflector
{
    /**
     *  ログユーティリティ　@
     */
    private static final WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3TPCashDepositRestraintReflector.class);
    
    /**
     * (デフォルトコンストラクタ) 
     */
    public WEB3TPCashDepositRestraintReflector() 
    {
     
    }
    
    /**
     * (static)(create預り金担保出金拘束金) <BR>
     * <BR>
     * 預り金担保出金拘束金を作成し、返却する。 <BR>
     * <BR>
     * 1)預り金担保出金拘束金インスタンスを生成する。 <BR>
     * 　@－デフォルトコンストラクタをコール <BR>
     * <BR>
     * 2)生成した預り金担保出金拘束金インスタンス(="預り金担保出金拘束金")の属性に値をセット<BR> 
     * <BR>
     * 　@－"預り金担保出金拘束金".set余力計算条件(:余力計算条件 = 引数.余力計算条件)<BR> 
     * 　@－"預り金担保出金拘束金".set拘束金(:double = 引数.出金停止額) <BR>
     * 　@－"預り金担保出金拘束金".calc変動反映日(:Date = 引数.処理日) <BR>
     * <BR>
     * 3)"預り金担保出金拘束金"を返却する。 <BR>
     * @@param l_calcCondition - (余力計算条件)
     * @@param l_dblPaymentStopAmount - (出金停止額)
     * @@param l_datDealDate - (処理日)
     * @@return WEB3CashDepositRestraintReflector
     */
    public static WEB3TPCashDepositRestraintReflector createCashDepositRestraint(
        WEB3TPCalcCondition l_calcCondition, 
        double l_dblPaymentStopAmount,
        Date l_datDealDate)
    {
        final String STR_METHOD_NAME = "createCashDepositRestraint(WEB3TPCalcCondition, double, Date)";
        log.entering(STR_METHOD_NAME);
        
        //1)預り金担保出金拘束金インスタンスを生成する。
        WEB3TPCashDepositRestraintReflector l_restraint = 
            new WEB3TPCashDepositRestraintReflector();
        
        // 2)生成した預り金担保出金拘束金インスタンス(="預り金担保出金拘束金")の属性に値をセット<BR> 
        // <BR>
        // 　@－"預り金担保出金拘束金".set余力計算条件(:余力計算条件 = 引数.余力計算条件)<BR> 
        // 　@－"預り金担保出金拘束金".set拘束金(:double = 引数.出金停止額) <BR>
        // 　@－"預り金担保出金拘束金".calc変動反映日(:Date = 引数.処理日) <BR>
        l_restraint.setCalcCondition(l_calcCondition);
        l_restraint.setAmount(l_dblPaymentStopAmount);
        l_restraint.calcReflectDay(l_datDealDate);
        
        //3)"預り金担保出金拘束金"を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_restraint;
    }

    /**
     * (calc変動反映日) <BR>
     * <BR>
     * 変動反映開始日、変動反映終了日をセットする。 <BR>
     * <BR>
     * １）引数.受渡日の翌営業日(="受渡日+1")を取得する。 <BR>
     * 　@－"受渡日+1" = this.get余力計算条件().roll営業日(:Date = 引数.受渡日, :int = 1) <BR>
     * <BR>
     * ２）変動反映開始日、変動反映終了日をセットする。 <BR>
     * 　@－this.set変動反映開始日(:Date = "受渡日+1") <BR>
     * 　@－this.set変動反映終了日(:Date = T+5) <BR>
     * <BR>
     * ※T+5 = this.get余力計算条件().get営業日(:int = 5) <BR>
     * @@param l_datDeliveryDate - (受渡日)
     */
    public void calcReflectDay(Date l_datDeliveryDate) 
    {
        final String STR_METHOD_NAME = "calcReflectDay(Date l_datDeliveryDate)";
        log.entering(STR_METHOD_NAME);
        
        //１）引数.受渡日の翌営業日(="受渡日+1")を取得する。
        //－"受渡日+1" = this.get余力計算条件().roll営業日(:Date = 引数.受渡日, :int = 1)
        Date l_datNextBizDate = getCalcCondition().rollBizDate(l_datDeliveryDate, 1);
        
        // ２）変動反映開始日、変動反映終了日をセットする。 <BR>
        // 　@－this.set変動反映開始日(:Date = "受渡日+1") <BR>
        // 　@－this.set変動反映終了日(:Date = T+5) <BR>
        this.setReflectStartDay(l_datNextBizDate);
        this.setReflectEndDay(this.getCalcCondition().getBizDate(
            WEB3TPSpecifiedPointDef.T_5));
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * このオブジェクトの文字列表現を返す。<BR>
     * <BR>
     * @@return String
     */
    public String toString()
    {
        return ToStringUtils
            .newToStringBuilder(this)
            .appendSuper(super.toString())
            .toString();
    } 
}

@
