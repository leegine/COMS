head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.56.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPTradingPowerCalcMarginWrapper.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 資産余力情報<信用顧客>Wrapper（WEB3TPTradingPowerCalcMarginWrapper.java）
Author Name      : Daiwa Institute of Research
Revision History : 2010/01/28 武波 (中訊) 新規作成 モデルNo.447
*/
package webbroker3.tradingpower;

import java.util.List;

import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.util.WEB3LogUtility;

/**
 * （資産余力情報<信用顧客>Wrapper）<BR>
 * 資産余力情報<信用顧客>クラスのサブクラス<BR>
 *
 * @@author 武波(中訊)
 * @@version 1.0  
 */
public class WEB3TPTradingPowerCalcMarginWrapper extends WEB3TPTradingPowerCalcMargin
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPTradingPowerCalcMarginWrapper.class);

    /**
     * @@roseuid 410DF85F0091
     */
    public WEB3TPTradingPowerCalcMarginWrapper()
    {

    }

    /**
     * (コンストラクタ) <BR>
     * <BR>
     * スーパークラスのコンストラクタをコールする。 <BR>
     * <BR>
     * 引数： <BR>
     * 　@余力計算結果<信用顧客>＝引数.余力計算結果<信用顧客> <BR>
     * 　@余力計算条件＝引数.余力計算条件<BR>
     * @@param l_calcResult - （余力計算結果） <BR>
     * @@param l_calcCondition - （余力計算条件） <BR>
     */
    public WEB3TPTradingPowerCalcMarginWrapper(List l_calcResult, WEB3TPCalcCondition l_calcCondition)
    {
        super(l_calcResult, l_calcCondition);
    }

    /**
     * (getその他拘束金)<BR>
     * <BR>
     * 引数で指定された指定日(=n)の、「即日入金対象銘柄拘束金」を返却する。<BR>
     * <BR>
     * １）　@引数チェックを行う。<BR>
     * nが0以上5以下でない時、0を返却する。<BR>
     * <BR>
     * ２）　@指定日の「即日入金対象銘柄拘束金」を返却する。<BR>
     * <BR>
     * ［返却値］<BR>
     * this.余力計算結果詳細Params<信用顧客>.即日入金対象銘柄拘束金(T+n)<BR>
     * @@param l_intSpecifiedPoint
     * @@return double
     */
    public double getOtherRestraint(int l_intSpecifiedPoint)
    {
        final String STR_METHOD_NAME = "getOtherRestraint(int)";
        log.entering(STR_METHOD_NAME);
        double l_dblOtherRestraint = 0D;

        switch (l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0 :
                //即日入金対象銘柄拘束金(T+n)
                l_dblOtherRestraint = this.calcResultDetailMargin.getTodayDepFundRestraint0();
                break;

            case WEB3TPSpecifiedPointDef.T_1 :
                //即日入金対象銘柄拘束金(T+n)
                l_dblOtherRestraint = this.calcResultDetailMargin.getTodayDepFundRestraint1();
                break;

            case WEB3TPSpecifiedPointDef.T_2 :
                //即日入金対象銘柄拘束金(T+n)
                l_dblOtherRestraint = this.calcResultDetailMargin.getTodayDepFundRestraint2();
                break;

            case WEB3TPSpecifiedPointDef.T_3 :
                //即日入金対象銘柄拘束金(T+n)
                l_dblOtherRestraint = this.calcResultDetailMargin.getTodayDepFundRestraint3();
                break;

            case WEB3TPSpecifiedPointDef.T_4 :
                //即日入金対象銘柄拘束金(T+n)
                l_dblOtherRestraint = this.calcResultDetailMargin.getTodayDepFundRestraint4();
                break;

            case WEB3TPSpecifiedPointDef.T_5 :
                //即日入金対象銘柄拘束金(T+n)
                l_dblOtherRestraint = this.calcResultDetailMargin.getTodayDepFundRestraint5();
                break;

            default :
                //nが0以上5以下でない時、0を返却する。
                l_dblOtherRestraint = 0D;
        }

        //取得したその他拘束金を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_dblOtherRestraint;
    }
}
@
