head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.35.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPCashBalanceReflector.java;


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
Revision History : 2009/12/15 張騰宇 モデルNo.408 430
*/

package webbroker3.tradingpower.updtpower.cash;

import java.util.Date;

import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.updtpower.WEB3TPAssetReflector;
import webbroker3.tradingpower.util.ToStringUtils;
import webbroker3.util.WEB3LogUtility;

/**
 * (確定預り金)<BR>
 * 確定預り金変動（顧客勘定残高、MRF残高）を表現する。<BR>
 * <BR>
 */
public class WEB3TPCashBalanceReflector
    extends WEB3TPAssetReflector
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPCashBalanceReflector.class);

    /**
     * (MRFフラグ)<BR>
     */
    private boolean isMrf;
    

    /**
     * (残高額)<BR>
     */
    private double amount;
    
    /**
     * (保証金フラグ)<BR>
     */
    private boolean isDeposit;

    /**
     * @@roseuid 41048ADC0221
     */
    public WEB3TPCashBalanceReflector()
    {

    }

    /**
     * (create確定預り金)<BR>
     * (static)(create確定預り金) <BR>
     * <BR>
     * 確定預り金インスタンスを生成する。 <BR>
     * <BR>
     * 1)確定預り金インスタンスを生成する。 <BR>
     * 　@－デフォルトコンストラクタをコール <BR>
     * <BR>
     * 2)生成した確定預り金インスタンスの属性に値をセット <BR>
     * 　@－this.set余力計算条件(:余力計算条件 = 引数.余力計算条件) <BR>
     * 　@－this.set預り金残高(:double = 引数.預り金残高) <BR>
     * 　@－this.setMRF(:boolean = 引数.MRFフラグ) <BR>
     * 　@－this.calc変動反映日(:Date = 引数.受渡日) <BR>
     * 　@－this.set保証金(:boolean = 引数.保証金フラグ) <BR>
     * <BR>
     * 3)確定預り金インスタンスを返却する。 <BR>
     * @@param l_dblAmount - (預り金)
     * @@param l_isMrf - (MRFフラグ)
     * @@param l_datDeliveryDate - (受渡日)
     * @@param l_blnIsDeposit - (保証金フラグ)
     * @@return WEB3TPCashBalanceReflector
     * @@roseuid 40E112360291
     */
    public static WEB3TPCashBalanceReflector create(WEB3TPCalcCondition l_calcCondition,
        double l_dblAmount, boolean l_isMrf, Date l_datDeliveryDate, boolean l_blnIsDeposit)
    {
        WEB3TPCashBalanceReflector l_instance = new WEB3TPCashBalanceReflector();
        l_instance.setCalcCondition(l_calcCondition);
        l_instance.setAmount(l_dblAmount);
        l_instance.setMrf(l_isMrf);
        l_instance.calcReflectDay(l_datDeliveryDate);
        l_instance.setDeposit(l_blnIsDeposit);
        return l_instance;
    }

    /**
     * (isMRF)<BR>
     * MRFフラグを返す。<BR>
     * <BR>
     * @@return boolean
     * @@roseuid 40CD2EEC0180
     */
    public boolean isMrf()
    {
        return isMrf;
    }

    /**
     * (setMrf)<BR>
     * 引数をMRFフラグにセットする。<BR>
     * <BR>
     * @@param l_isMrf - (MRFフラグ)
     * @@roseuid 40CD2EEF022C
     */
    public void setMrf(boolean l_isMrf)
    {
        isMrf = l_isMrf;
    }

    /**
     * (get預り金残高)<BR>
     * 残高額を取得する。<BR>
     * 
     * @@return double
     */
    public double getAmount() 
    {
        return amount;
    }

    /**
     * (set預り金残高)<BR>
     * 引数の残高額をセットする。<BR>
     * 
     * @@param l_dblAmount (残高額)
     */
    public void setAmount(double l_dblAmount) 
    {
        amount = l_dblAmount;
    }

    /**
     * (calc変動反映日)<BR>
     * 変動反映開始日、変動反映終了日を計算し<BR>
     * セットする。<BR>
     * <BR>
     * １）this.isMrf()=trueの場合<BR>
     * 変動反映開始日 = 営業日[0](T+0)<BR>
     * 変動反映開始日 = 営業日[5](T+5)<BR>
     * <BR>
     * ２）１）以外<BR>
     * 変動反映開始日＝受渡日<BR>
     * 変動反映終了日＝受渡日<BR>
     * @@param l_dateDeliveryDate - (受渡日)
     * @@return Date
     * @@roseuid 40CD147402D6
     */
    public void calcReflectDay(Date l_datDeliveryDate)
    {

        if (isMrf())
        {
            //T+0
            setReflectStartDay(getCalcCondition().getBizDate(0));
            //T+5
            setReflectEndDay(getCalcCondition().getBizDate(5));
        }
        else
        {
            setReflectStartDay(l_datDeliveryDate);
            setReflectEndDay(l_datDeliveryDate);
        }
    }

    /**
     * (is保証金)<BR>
     * (is保証金)<BR>
     * <BR>
     * this.保証金フラグを返却する <BR>
     * @@return boolean
     */
    public boolean isDeposit()
    {
        return isDeposit;
    }

    /**
     * (set保証金)<BR>
     * (set保証金)<BR>
     * <BR>
     * 引数.保証金フラグを、this.保証金フラグにセットする。 <BR>
     * @@param l_blnIsDeposit - (保証金フラグ)<BR>
     */
    public void setDeposit(boolean l_blnIsDeposit)
    {
        isDeposit = l_blnIsDeposit;
    }

    /**
     * このオブジェクトの文字列表現を返す。<BR>
     * 
     * @@return String
     */
    public String toString()
    {
        return ToStringUtils
            .newToStringBuilder(this)
            .append("isMrf", this.isMrf())
            .append("amount", this.getAmount())
            .appendSuper(super.toString())
            .toString();
    }


}
@
