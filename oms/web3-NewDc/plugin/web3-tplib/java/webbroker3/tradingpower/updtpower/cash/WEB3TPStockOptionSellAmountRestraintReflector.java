head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.35.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPStockOptionSellAmountRestraintReflector.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ストックオプション売付代金拘束金(WEB3TPStockOptionSellAmountRestraintReflector)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/14 車進(中訊)　@新規作成
*/

package webbroker3.tradingpower.updtpower.cash;

import java.util.Date;

import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.tradingpower.util.ToStringUtils;
import webbroker3.util.WEB3LogUtility;

/**
 * (ストックオプション売付代金拘束金)<BR>
 * <BR>
 * ストックオプション残高の売付代金の使用を<BR>
 * トランザクション発生日（＝約定日）翌日まで<BR>
 * 制限する目的で計上する拘束金<BR>
 */
public class WEB3TPStockOptionSellAmountRestraintReflector 
    extends WEB3TPRestraintReflector 
{

    /**
     * ログユーティリティ
     */
    private static final WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3TPStockOptionSellAmountRestraintReflector.class);    
    
    /**
     * (ストックオプション売付代金拘束金)<BR>
     * (デフォルトコンストラクタ)<BR>
     * @@roseuid 44E1499D0396
     */
    public WEB3TPStockOptionSellAmountRestraintReflector() 
    {
     
    }
    
    /**
     * (createストックオプション売付代金拘束金)<BR>
     * (static)(createストックオプション売付代金拘束金)<BR>
     * <BR>
     * ストックオプション売付代金拘束金を作成し、返却する。<BR>
     * <BR>
     * 1)ストックオプション売付代金拘束金インスタンスを生成する。<BR>
     * 　@-デフォルトコンストラクタをコール<BR>
     * <BR>
     * 2)生成したストックオプション売付代金拘束金インスタンスの属性に値をセット<BR>
     * <BR>
     * 　@－this.set余力計算条件(:余力計算条件 = 引数.余力計算条件)<BR>
     * 　@－this.set拘束金(:double = 引数.受渡代金)<BR>
     * 　@－this.calc変動反映日(:Date = 引数.受渡日)<BR>
     * <BR>
     * 3)ストックオプション売付代金拘束金インスタンスを返却する。<BR>
     * @@param l_calcCondition - (余力計算条件)<BR>
     * (余力計算条件)<BR>
     * @@param l_dblAmount - (受渡代金)<BR>
     * (受渡代金)<BR>
     * @@param l_datDeliveryDate - (受渡日)<BR>
     * (受渡日)<BR>
     * @@return WEB3TPStockOptionSellAmountRestraintReflector
     * @@roseuid 44E149AE0377
     */
    public static WEB3TPStockOptionSellAmountRestraintReflector createStockOptionSellAmountRestraintReflector(
        WEB3TPCalcCondition l_calcCondition, 
        double l_dblAmount, 
        Date l_datDeliveryDate) 
    {
        final String METHOD_NAME = "createStockOptionSellAmountRestraintReflector(" +
            "WEB3TPCalcCondition, double, Date)";
        log.entering(METHOD_NAME);
        
       //1)ストックオプション売付代金拘束金インスタンスを生成する。
       // 　@-デフォルトコンストラクタをコール
        WEB3TPStockOptionSellAmountRestraintReflector l_instance =  
            new WEB3TPStockOptionSellAmountRestraintReflector();
        
        //2)生成したストックオプション売付代金拘束金インスタンスの属性に値をセット
        // 　@－this.set余力計算条件(:余力計算条件 = 引数.余力計算条件)
        // 　@－this.set拘束金(:double = 引数.受渡代金)
        //　@ －this.calc変動反映日(:Date = 引数.受渡日)
        l_instance.setCalcCondition(l_calcCondition);
        l_instance.setAmount(l_dblAmount);
        l_instance.calcReflectDay(l_datDeliveryDate);
        
        //3)ストックオプション売付代金拘束金インスタンスを返却する。
        log.exiting(METHOD_NAME);
        return l_instance;
    }
    
    /**
     * (calc変動反映日)<BR>
     * <BR>
     * 変動反映開始日、変動反映終了日をセットする。<BR>
     * －this.set変動反映開始日(:Date = 引数.受渡日)<BR>
     * －this.set変動反映終了日(:Date = T+5)<BR>
     * <BR>
     * ※T+5 = this.get余力計算条件().get営業日(:int = 5)<BR>
     * @@param l_datDeliveryDate - (受渡日)<BR>
     * (受渡日）<BR>
     * @@roseuid 44E1494300B8
     */
    public void calcReflectDay(Date l_datDeliveryDate) 
    {
        final String METHOD_NAME = "calcReflectDay(Date l_datDeliveryDate)";
        log.entering(METHOD_NAME);
        
        //変動反映開始日、変動反映終了日をセットする。
        //－this.set変動反映開始日(:Date = 引数.受渡日)
        //－this.set変動反映終了日(:Date = T+5)
        Date l_datT5 = getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_5);
        this.setReflectStartDay(l_datDeliveryDate);
        this.setReflectEndDay(l_datT5);

        log.exiting(METHOD_NAME);
    }
    
    /**
     * このオブジェクトの文字列表現を返す。
     * 
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
