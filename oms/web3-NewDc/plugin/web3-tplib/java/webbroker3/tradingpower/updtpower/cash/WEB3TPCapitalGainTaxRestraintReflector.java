head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.36.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPCapitalGainTaxRestraintReflector.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3TPAccumulatedCapitalGainTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2004/08/02 堀野 和美(FLJ) 新規作成
*/

package webbroker3.tradingpower.updtpower.cash;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.tradingpower.util.ToStringUtils;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (譲渡益税拘束金)<BR>
 * 譲渡益税拘束金を表現する。<BR>
 */
public class WEB3TPCapitalGainTaxRestraintReflector
    extends WEB3TPRestraintReflector
{

    /** ログ　@ユーティリティ　@*/
    private static final WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3TPCapitalGainTaxRestraintReflector.class);

    /**
     * (譲渡益税率)<BR>
     */
    private double taxRate;

    /**
     * (受渡日)<BR>
     */
    private Date deliveryDate;

    /**
     * (譲渡損益<当日>)<BR>
     */
    private webbroker3.tradingpower.updtpower.cash.WEB3TPCapitalGain[] capitalGain;

    /**
     * (累積譲渡損益<確定>)<BR>
     */
    private WEB3TPAccumulatedCapitalGain accumuratedCapitalGain;

    /**
     * @@roseuid 41049159030B
     */
    public WEB3TPCapitalGainTaxRestraintReflector()
    {

    }

    /**
     * (create譲渡益税拘束金)<BR>
     * 譲渡益税拘束金を生成し、返却する。<BR>
     * <BR>
     * １）　@インスタンス生成<BR>
     * <BR>
     * ２）　@値を設定<BR>
     * 	譲渡益税率＝譲渡益税率<BR>
     * 	受渡日＝受渡日<BR>
     * 	累積譲渡損益＝累積譲渡損益<BR>
     * 	譲渡損益＝譲渡損益<BR>
     * 	拘束金＝calc譲渡損益税()<BR>
     * 	変動反映開始日、変動反映終了日設定：calc変動反映日（受渡日）<BR>
     * <BR>
     * ３）　@インスタンスを返却<BR>
     * <BR>
     * @@param l_dblTaxRate - (譲渡益税率)
     * @@param l_datDeliveryDate - (受渡日)
     * @@param l_accumuratedCapitalGain - (累積譲渡損)
     * @@param l_capitalGain - (譲渡損益)
     * @@return WEB3TPCapitalGainTaxRestraintReflector
     * @@roseuid 40DABDF8004C
     */
    public static WEB3TPCapitalGainTaxRestraintReflector create(WEB3TPCalcCondition
        l_calcCondition, double l_dblTaxRate, Date l_datDeliveryDate,
        WEB3TPAccumulatedCapitalGain l_accumuratedCapitalGain,
        WEB3TPCapitalGain[] l_capitalGain)
    {
        WEB3TPCapitalGainTaxRestraintReflector l_instance = new
            WEB3TPCapitalGainTaxRestraintReflector();
        l_instance.setCalcCondition(l_calcCondition);
        l_instance.setTaxRate(l_dblTaxRate);
        l_instance.setDeliveryDate(l_datDeliveryDate);
        l_instance.setAccumuratedCapitalGain(l_accumuratedCapitalGain);
        l_instance.setCapitalGain(l_capitalGain);
        l_instance.setAmount(l_instance.calcCapitalGainTax(l_datDeliveryDate));
        l_instance.calcReflectDay(l_datDeliveryDate);

        return l_instance;

    }

    /**
     * (get譲渡益税率)<BR>
     * 譲渡益税率を返す。<BR>
     * @@return double
     * @@roseuid 40EE3525027A
     */
    public double getTaxRate()
    {
        return taxRate;
    }

    /**
     * (set譲渡益税率)<BR>
     * 引数を譲渡益税率にセットする。<BR>
     * @@param l_taxRate - (譲渡益税率)
     * @@roseuid 40EE353200C4
     */
    public void setTaxRate(double l_dblTaxRate)
    {
        taxRate = l_dblTaxRate;
    }

    /**
     * (get受渡日)<BR>
     * 受渡日を返す。<BR>
     * @@return Date
     * @@roseuid 40EE7FA703DA
     */
    public Date getDeliveryDate()
    {
        return deliveryDate;
    }

    /**
     * (set受渡日)<BR>
     * 引数を受渡日にセットする。<BR>
     * @@param l_datDeliveryDate - (受渡日)
     * @@roseuid 40EE7F9F011A
     */
    public void setDeliveryDate(Date l_datDeliveryDate)
    {
        deliveryDate = l_datDeliveryDate;
    }

    /**
     * (get累積譲渡損益)<BR>
     * 累積譲渡損益を返す。<BR>
     * @@return webbroker3.tradingpower.updtpower.cash.WEB3TPAccumulatedCapitalGain
     * @@roseuid 40EE8185013A
     */
    public WEB3TPAccumulatedCapitalGain getAccumuratedCapitalGain()
    {
        return accumuratedCapitalGain;
    }

    /**
     * (set累積譲渡損益)<BR>
     * 引数を累積譲渡損益にセットする。<BR>
     * @@param l_accumuratedCapitalGain - (累積譲渡損益)
     * @@roseuid 40EE8AE9037C
     */
    public void setAccumuratedCapitalGain(WEB3TPAccumulatedCapitalGain
                                          l_accumuratedCapitalGain)
    {
        accumuratedCapitalGain = l_accumuratedCapitalGain;
    }

    /**
     * (get譲渡損益)<BR>
     * 譲渡損益の配列中、<BR>
     * 譲渡損益.get受渡日()＝引数.受渡日の<BR>
     * 譲渡損益を返す。<BR>
     * 
     * @@param l_datDeliveryDate (受渡日)
     * @@return webbroker3.tradingpower.updtpower.cash.WEB3TPCapitalGain
     * @@throws WEB3BaseRuntimeException
     * @@roseuid 40EEA216032E
     */
    public WEB3TPCapitalGain getCapitalGain(Date l_datDeliveryDate)
    {
        final String STR_METHOD_NAME = "getCapitalGain(Date l_datDeliveryDate)";
        
        for (int i = 0; i < capitalGain.length; i++)
        {
            if (WEB3DateUtility.compareToDay(capitalGain[i].getDeliveryDate(), l_datDeliveryDate) == 0)
            {
                return capitalGain[i];
            }
        }
        
        throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + "." + STR_METHOD_NAME);

    }

    /**
     * (get譲渡損益)<BR>
     * 譲渡損益の配列を返却する。<BR>
     * @@return WEB3TPCapitalGain[]
     */
    public WEB3TPCapitalGain[] getCapitalGain()
    {
        return capitalGain;
    }

    /**
     * (set譲渡損益)<BR>
     * 引数を譲渡損益にセットする。<BR>
     * @@param l_capitalGain - (譲渡損益)
     * @@roseuid 40EE89D902EF
     */
    public void setCapitalGain(WEB3TPCapitalGain[] l_capitalGain)
    {
        capitalGain = l_capitalGain;
    }

    /**
     * (calc譲渡益税)<BR>
     * 指定日(n)により該当する計算ロジックで計算する。<BR>
     * <BR>  
     * 
     * １）　@営業日(T+0)から営業日 (T+5)まで同年の場合<BR>
     * <BR>
     * 	譲渡益税(T+3)  =  Max((譲渡損益(T+3) - 当期譲渡損), 0)<BR>
     * 	 ×　@譲渡益税率<BR>
     * 	譲渡益税(T+4)  =  Max((譲渡損益(T+3)  +  譲渡損益(T+4) -　@当期譲渡損), 0)<BR>
     * 	 ×　@譲渡益税率<BR>
     * 	譲渡益税(T+5)  =  Max((譲渡損益(T+3)  +  譲渡損益(T+4)  + 譲渡損益(T+5) - 当期譲渡損), 0)<BR>
     * 	 ×　@譲渡益税率<BR>
     * <BR>
     * ２）　@営業日(T+3)以前で翌年になる場合<BR>
     * <BR>
     * 	譲渡益税(T+3)  =　@Max((譲渡損益(T+3) - 翌月譲渡損), 0)<BR>
     * 	 ×　@譲渡益税率<BR>
     * 	譲渡益税(T+4)  =　@Max((譲渡損益(T+3) + 譲渡損益(T+4) - 翌月譲渡損), 0)<BR>
     * 	 ×　@譲渡益税率<BR>
     * 	譲渡益税(T+5)  =  Max((譲渡損益(T+3) + 譲渡損益(T+4) + 譲渡損益(T+5) - 翌月譲渡損), 0)<BR>
     * 	 ×　@譲渡益税率<BR>
     * <BR>
     * ３）　@営業日(T+4)で翌年になる場合<BR>
     * <BR>
     * 	譲渡益税(T+3)  =  Max((譲渡損益(T+3) - 当期譲渡損), 0)<BR>
     * 	 ×　@譲渡益税率<BR>
     * 	譲渡益税(T+4)  =  Max((譲渡損益(T+4) - 翌月譲渡損), 0)<BR>
     * 	 ×　@譲渡益税率<BR>
     * 	譲渡益税(T+5)  =  Max((譲渡損益(T+4)  +  譲渡損益(T+5) - 翌月譲渡損), 0)<BR>
     * 	 ×　@譲渡益税率<BR>
     * <BR>
     * ４）　@営業日(T+5)で翌年になる場合<BR>
     * <BR>
     * 	譲渡益税(T+3)  =  Max((譲渡損益(T+3) - 当期譲渡損), 0)<BR>
     * 	 ×　@譲渡益税率<BR>
     * 	譲渡益税(T+4)  =  Max((譲渡損益(T+3) + 譲渡損益(T+4) - 当期譲渡損), 0)<BR>
     * 	 ×　@譲渡益税率<BR>
     * 	譲渡益税(T+5)  =  Max((譲渡損益(T+5) - 翌月譲渡損), 0)<BR>
     * 	 ×　@譲渡益税率<BR>
     * <BR>
     * ５）　@上記のように求められる譲渡益税(n)を返却する。<BR>
     * <BR>  
     *     ※各値の取得方法@<BR>
     * ・	譲渡損益(T+3)・・・this.get譲渡損益(受渡日<T+3>)<BR>
     * ・	譲渡損益(T+4)・・・this.get譲渡損益(受渡日<T+4>)<BR>
     * ・	譲渡損益(T+5)・・・this.get譲渡損益(受渡日<T+5>)<BR>
     * ・	当期譲渡損・・・Min(this.get累積譲渡損益().get当期譲渡損益(), 0)<BR>
     * ・	翌月譲渡損・・・Min(this.get累積譲渡損益().get翌月譲渡損益(), 0)<BR>
     * <BR>
     * 
     * @@param l_datDate (指定日)
     * @@return double
     * @@roseuid 40EE3F6C0076
     */
    public double calcCapitalGainTax(Date l_datDate)
    {
        Date l_datT0 = getCalcCondition().getBizDate(0);
        Date l_datT2 = getCalcCondition().getBizDate(2);
        Date l_datT3 = getCalcCondition().getBizDate(3);
        Date l_datT4 = getCalcCondition().getBizDate(4);
        Date l_datT5 = getCalcCondition().getBizDate(5);

        double l_capitalGain = 0.0d;        
        
        //営業日(T+0)から営業日(T+5)まで同年の場合
        if (compareToYear(l_datT0, l_datT5) == 0)
        {   
            l_capitalGain = Math.max((calcTodaysCapitalGainTotal(l_datDate) -
                                    accumuratedCapitalGain.getCurrentTermLoss()), 0.0d);            
        }

        //営業日(T+0)から営業日(T+3)で翌年になる場合<BR>
        else if (compareToYear(l_datT0, l_datT3) < 0)
        {
            l_capitalGain =
                Math.max( (calcTodaysCapitalGainTotal(l_datDate) -
                           accumuratedCapitalGain.geNextMonthLoss()), 0.0d);
                           
        }

        //営業日(T+4)で翌年になる場合<BR>
        else if (compareToYear(l_datT3, l_datT4) < 0)
        {
            if (WEB3DateUtility.compareToDay(l_datT3, l_datDate) == 0)
            {
                l_capitalGain =
                    Math.max( (calcTodaysCapitalGainTotal(l_datDate) -
                               accumuratedCapitalGain.getCurrentTermLoss()), 0.0d);
                               
            }
            else if ((WEB3DateUtility.compareToDay(l_datT4, l_datDate) == 0) || 
                (WEB3DateUtility.compareToDay(l_datT5, l_datDate) == 0))
            {
                l_capitalGain =
                    Math.max( (calcTodaysCapitalGainTotal(l_datDate) -
                               accumuratedCapitalGain.geNextMonthLoss()), 0.0d);
                               
            }
        }

        //営業日(T+5)で翌年になる場合<BR>
        else if (compareToYear(l_datT4, l_datT5) < 0)
        {
            if ((WEB3DateUtility.compareToDay(l_datT3, l_datDate) == 0) || 
                (WEB3DateUtility.compareToDay(l_datT4, l_datDate) == 0))
            {
                l_capitalGain =
                    Math.max( (calcTodaysCapitalGainTotal(l_datDate) -
                               accumuratedCapitalGain.getCurrentTermLoss()), 0.0d);
                               
            }
            else if (WEB3DateUtility.compareToDay(l_datT5, l_datDate) == 0)
            {
                l_capitalGain =
                    Math.max( (calcTodaysCapitalGainTotal(l_datDate) -
                               accumuratedCapitalGain.geNextMonthLoss()), 0.0d);
            }
        }
                
        return (l_capitalGain == 0.0d) ? 0.0d : Math.floor(l_capitalGain * taxRate);

    }
        
    /**
     * (calc変動反映日)<BR>
     * 変動反映開始日、変動反映終了日を以下のようにセットする。<BR>
     * <BR>
     * 変動反映開始日	＝	引数.受渡日<BR>
     * <BR>
     * [変動反映開始日(引数.受渡日)の翌営業日から翌年となる場合]<BR>
     * 	変動反映終了日	＝	営業日[5]<BR>
     * [上記以外]<BR>
     * 	変動反映終了日	＝	引数.受渡日
     * @@param l_datDeliveryDate - (受渡日)
     * @@roseuid 40E520DA02A1
     */
    public void calcReflectDay(Date l_datDeliveryDate)
    {
        final String STR_METHOD_NAME = "calcReflectDay(Date l_datDeliveryDate)";
        
        //変動反映開始日
        setReflectStartDay(l_datDeliveryDate);
                
        //変動反映終了日       
        Date l_datNextBizDate = getCalcCondition().rollBizDate(l_datDeliveryDate, 1);
        
        //受渡日と受渡日翌日(翌営業日）で年をまたぐ場合
        if (compareToYear(l_datDeliveryDate, l_datNextBizDate) < 0)
        {
            //反映終了日はT+5
            setReflectEndDay(getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_5));
        }
        else
        {
            //同年の場合は受渡日
            setReflectEndDay(l_datDeliveryDate);
        }

    }

    /**
     * (calc譲渡損益累計<当日>)<BR>
     * 受渡日における当日以降譲渡損益の累計を返却する。<BR>
     * @@param l_datDeliveryDate (受渡日)
     * @@return double
     */
    private double calcTodaysCapitalGainTotal(Date l_datDeliveryDate)
    {
        double l_dblCapitalGainTotal = 0.0d;

        Date l_datT0 = getCalcCondition().getBizDate(0);
        Date l_datT3 = getCalcCondition().getBizDate(3);
        Date l_datT4 = getCalcCondition().getBizDate(4);
        Date l_datT5 = getCalcCondition().getBizDate(5);

        //T+3の場合
        if (WEB3DateUtility.compareToDay(l_datT3, l_datDeliveryDate) == 0)
        {
            l_dblCapitalGainTotal = getCapitalGain(l_datT3).getAmount();
            return l_dblCapitalGainTotal;
        }
        //T+4 or T+5の場合
        else
        {
            //T+0〜T+5まで同年 または T+3〜T+5の間で同年
            if ((compareToYear(l_datT0, l_datT5) == 0) ||
                (compareToYear(l_datT0, l_datT3) < 0))
            {
                //T+4の場合
                if (WEB3DateUtility.compareToDay(l_datT4, l_datDeliveryDate) == 0)
                {
                    l_dblCapitalGainTotal = getCapitalGain(l_datT3).getAmount() +
                        getCapitalGain(l_datT4).getAmount();
                }
                //T+5の場合
                else if (WEB3DateUtility.compareToDay(l_datT5, l_datDeliveryDate) == 0)
                {
                    l_dblCapitalGainTotal = getCapitalGain(l_datT3).getAmount() +
                        getCapitalGain(l_datT4).getAmount() +
                        getCapitalGain(l_datT5).getAmount();
                }
            }

            //T+4で翌年
            else if (compareToYear(l_datT3, l_datT4) < 0)
            {
                //T+4の場合
                if (WEB3DateUtility.compareToDay(l_datT4, l_datDeliveryDate) == 0)
                {
                    l_dblCapitalGainTotal = getCapitalGain(l_datT4).getAmount();
                }
                //T+5の場合
                else if (WEB3DateUtility.compareToDay(l_datT5, l_datDeliveryDate) == 0)
                {
                    l_dblCapitalGainTotal = getCapitalGain(l_datT4).getAmount() +
                        getCapitalGain(l_datT5).getAmount();
                }
            }

            else if (compareToYear(l_datT4, l_datT5) < 0)
            {
                //T+4の場合
                if (WEB3DateUtility.compareToDay(l_datT4, l_datDeliveryDate) == 0)
                {
                    l_dblCapitalGainTotal = getCapitalGain(l_datT3).getAmount() +
                        getCapitalGain(l_datT4).getAmount();
                }
                //T+5の場合
                else if (WEB3DateUtility.compareToDay(l_datT5, l_datDeliveryDate) == 0)
                {
                    l_dblCapitalGainTotal = getCapitalGain(l_datT5).getAmount();
                }
            }
        }

        //上記以外のケースの場合0を返す。
        return l_dblCapitalGainTotal;
    }

    /**
     * (compare年)<BR>
     * 二つの日付を比較する(精度は年までとする)。<BR>
     * <BR>
     * l_dat1がl_dat2の後の場合、０より大きい整数を返却する。<BR>
     * l_dat1がl_dat2の前の場合、０より小さい整数を返却する。<BR>
     * l_dat1とl_dat2が同様の場合、０を返却する。<BR>
     * 
     * @@param l_dat1 (日付1)
     * @@param l_dat2 (日付2)
     * @@return int
     */
    private static int compareToYear(Date l_dat1, Date l_dat2) 
    {
        if (l_dat1 == null) 
        {
            l_dat1 = new Date(0);
        } 
        
        if (l_dat2 == null) 
        {
            l_dat2 = new Date(0);
        }

        SimpleDateFormat l_dateFormat = GtlUtils.getThreadSafeSimpleDateFormat("yyyy");
        String l_strDate1 = l_dateFormat.format(l_dat1);
        String l_strDate2 = l_dateFormat.format(l_dat2);

        return l_strDate1.compareTo(l_strDate2);
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
            .append("taxRate", this.getTaxRate())
            .append("capitalGain[]", this.getCapitalGain())
            .append("accumuratedCapitalGain", this.getAccumuratedCapitalGain())
            .append("deliveryDate", this.getDeliveryDate())
            .appendSuper(super.toString())
            .toString();
    }

}
@
