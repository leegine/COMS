head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.36.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPMutualFundCapitalGainTaxRestraintReflector.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3TPMutualFundCapitalGainTaxRestraintReflector.java
Author Name      : Daiwa Institute of Research
Revision History : 2005/10/17 齋藤 栄三(FLJ) 新規作成
*/

package webbroker3.tradingpower.updtpower.cash;

import java.util.Date;

import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.tradingpower.util.ToStringUtils;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (投資信託譲渡益税拘束金)<BR>
 * 投資信託譲渡益税拘束金を表現する。
 */
public class WEB3TPMutualFundCapitalGainTaxRestraintReflector
    extends WEB3TPRestraintReflector
{
    /** ログ　@ユーティリティ　@*/
    private static final WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3TPMutualFundCapitalGainTaxRestraintReflector.class);
    
    public WEB3TPMutualFundCapitalGainTaxRestraintReflector()
    {

    }

    /**
     * (create投資信託譲渡益税拘束金)<BR>
     * 投資信託譲渡益税拘束金を生成し、返却する。<BR>
     * <BR>
     * １）　@インスタンス生成<BR>
     * <BR>
     * ２）　@値を設定<BR>
     * 	拘束金      ＝  投資信託注文.源泉徴収拘束金<BR>
     * 	変動反映開始日、変動反映終了日設定：calc変動反映日(受渡日))<BR>
     * <BR>
     * ３）　@インスタンスを返却<BR>
     * <BR>
     * @@param l_calcCondition (計算条件)
     * @@param l_dblAmount (源泉徴収拘束金)
     * @@param l_datDeliveryDate (受渡日)
     * @@return WEB3TPMutualFundCapitalGainTaxRestraintReflector
     */
    public static WEB3TPMutualFundCapitalGainTaxRestraintReflector create(WEB3TPCalcCondition l_calcCondition,
        double l_dblAmount, Date l_datDeliveryDate)
    {
        WEB3TPMutualFundCapitalGainTaxRestraintReflector l_instance = new WEB3TPMutualFundCapitalGainTaxRestraintReflector();
        l_instance.setCalcCondition(l_calcCondition);
        l_instance.setAmount(l_dblAmount);
        l_instance.calcReflectDay(l_datDeliveryDate);
        return l_instance;
    }

    /**
     * (calc変動反映日)<BR>
     * 変動反映開始日、変動反映終了日を以下のようにセットする。<BR>
     * <BR>
     * [引数.受渡日 < 営業日[5]の場合]<BR>
     * 変動反映開始日＝引数.受渡日<BR>
     * [上記以外]<BR>
     * 変動反映開始日＝営業日[5]<BR>
     * <BR> 
     * 変動反映終了日＝営業日[5]
      * <BR>
     * @@param l_datDeliveryDate - 受渡日
     */
    public void calcReflectDay(Date l_datDeliveryDate)
    {
        final String STR_METHOD_NAME = "calcReflectDay(Date l_datDeliveryDate)";

        Date l_datT5 = getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_5);

         //引数.受渡日 < 営業日[5]の場合]
         //変動反映開始日＝引数.受渡日
        if(WEB3DateUtility.compareToDay(l_datDeliveryDate, l_datT5) < 0)
        {
            //開始日       
            setReflectStartDay(l_datDeliveryDate);
        }
        else
        {
            //開始日       
            setReflectStartDay(l_datT5);
        }

        //終了日
        setReflectEndDay(l_datT5);
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
