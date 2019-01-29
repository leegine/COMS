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
filename	WEB3MutualFundTradedProduct.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 拡張投信取引銘柄クラス(WEB3MutualFundTradedProduct)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/04 于美麗 (中訊) 新規作成
                   2004/12/06 于美麗 (中訊) 残対応
*/
package webbroker3.mf;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingCalendar;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.stdimpls.MutualFundTradedProductImpl;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.define.WEB3BuyPossibleDivDef;
import webbroker3.common.define.WEB3SellPossibleDivDef;
import webbroker3.common.define.WEB3SwtPossibleDivDef;
import webbroker3.mf.define.WEB3MFRecruitPossibleDivDef;
import webbroker3.util.WEB3LogUtility;

/**
 * 拡張投信取引銘柄クラス
 * 
 * @@author 于美麗(中訊)
 * @@version 1.0
 */
public class WEB3MutualFundTradedProduct extends MutualFundTradedProductImpl
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualFundTradedProduct.class);

    /**
     * (拡張投信取引銘柄)<BR>
     * コンストラクタ。<BR>
     * @@param l_tradedProductRow - 取引銘柄Row
     * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
     * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
     * @@roseuid 40AD8F42006D
     */
    public WEB3MutualFundTradedProduct(TradedProductRow l_tradedProductRow)
        throws DataQueryException, DataNetworkException
    {
        super(l_tradedProductRow);
    }

    /**
     * (拡張投信取引銘柄)<BR>
     * コンストラクタ。<BR>
     * @@param l_mutualFundTradedProductRow - 投信取引銘柄Row
     * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
     * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
     * @@roseuid 40AD8F42004E
     */
    public WEB3MutualFundTradedProduct(MutualFundTradedProductRow l_mutualFundTradedProductRow)
        throws DataQueryException, DataNetworkException
    {
        super(l_mutualFundTradedProductRow);
    }

    /**
     * (get約定日)<BR>
     * 発注日に対する約定日を返す。<BR>
     * <BR>
     * １）　@取引カレンダーを取得する<BR>
     * 　@GtlUtils.getFinObjectManager().getTradingCalendar()をコールして、<BR>取引カレンダーを取得する。<BR>
     * 　@［getTradingCalendarに渡すパラメタ］<BR>
     * 　@　@取引銘柄ID： this.getTradedProductId()の戻り値<BR>
     * <BR>
     * ２）　@発注日を取得する <BR>
     * 　@投信取引時間管理.get投信発注日()をコールして発注日を取得する。<BR>
     * <BR>
     * ３）　@約定日移動日数の取得<BR>
     * 　@this.get約定日移動日数()をコールして、約定日移動日数を取得する。<BR>
     * <BR>
     * ４）　@約定日を取得する<BR>
     * 　@取引カレンダー.roll()をコールして、約定日を取得する。<BR>
     * 　@［rollに渡すパラメタ］<BR>
     * 　@　@発注日： 取得した発注日<BR>
     * 　@　@移動日数： 取得した約定日移動日数<BR>
     * <BR>
     * ５）　@取得した約定日を返す。<BR>
     * <BR>
     * @@return Date
     * @@throws WEB3BaseException
     * @@roseuid 40AD623D006C
     */
    public Date getExecutedDate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getExecutedDate()";
        log.entering(STR_METHOD_NAME);

        //１）　@取引カレンダーを取得する
        TradingCalendar l_tradingCalendar =
            GtlUtils.getFinObjectManager().getTradingCalendar(
                this.getTradedProductId());

        //２）　@発注日を取得する
        Date l_datCurrentBizDate = 
            WEB3MutualFundTradingTimeManagement.getMutualOrderBizDate();

        //３）　@約定日移動日数の取得
        int intDateShiftDays = this.getExecDateShiftDays();

        //４）　@約定日を取得する
        Date l_datExecutedDate =
            l_tradingCalendar.roll(l_datCurrentBizDate, intDateShiftDays);

        //５）　@取得した約定日を返す
        log.exiting(STR_METHOD_NAME);
        return l_datExecutedDate;
    }

    /**
     * (get未済期間)<BR>
     * （getDeliveryDateShiftDaysのオーバーロード）<BR>
     * <BR>
     * 未済期間を返す。<BR>
     * <BR>
     * １）　@引数（is買付）が true の場合はthis.get未済期間（買付）()<BR>
     * の戻り値を返す。<BR>
     * <BR>
     * ２）　@そうでない場合はthis.get未済期間（解約）()の戻り値を返す。<BR>
     * <BR>
     * @@param l_blnIsAcquired - is買付。<BR>
     * <BR>
     * 買付の場合は true を、そうでない場合は false を返す。<BR>
     * 
     * @@return int
     * @@roseuid 40AD62A203D7
     */
    public int getOutstandingTerm(boolean l_blnIsAcquired)
    {
        final String STR_METHOD_NAME = "getOutstandingTerm(boolean l_blnIsAcquired)";
        log.entering(STR_METHOD_NAME);   
        int l_intOutstandingTerm = 0;
        if (l_blnIsAcquired)
        {
            //１）　@引数（is買付）が true の場合
            l_intOutstandingTerm = this.getOutstandingTermAcquired();
        }
        else
        {
            //２）　@そうでない場合
            l_intOutstandingTerm = this.getOutstandingTermSell();
        }
        log.exiting(STR_METHOD_NAME);
        return l_intOutstandingTerm;
    }

    /**
     * (get受渡日)<BR>
     * （getDailyDeliveryDateのオーバーロード）<BR>
     * <BR>
     * 約定日に対する受渡日を返す。<BR>
     * <BR>
     * １）　@取引カレンダーを取得する<BR>
     * 　@GtlUtils.getFinObjectManager().getTradingCalendar()をコールして、<BR>取引カレンダーを取得する。<BR>
     * 　@［getTradingCalendarに渡すパラメタ］<BR>
     * 　@　@取引銘柄ID： this.getTradedProductId()の戻り値<BR>
     * <BR>
     * ２）　@約定日を取得する<BR>
     * 　@this.get約定日()をコールして約定日を取得する。<BR>
     * <BR>
     * ３）　@未済期間の取得<BR>
     * 　@this.get未済期間()をコールして、未済期間を取得する。<BR>
     * 　@［get未済期間に渡すパラメタ］<BR>
     * 　@　@is買付： 引数.is買付<BR>
     * <BR>
     * ４）　@受渡日を取得する<BR>
     * 　@取引カレンダー.roll()をコールして、受渡日を取得する。<BR>
     * 　@［rollに渡すパラメタ］<BR>
     * 　@　@基準日： 取得した約定日<BR>
     * 　@　@移動日数： 取得した未済期間 - 1<BR>
     * <BR>
     * ５）　@取得した受渡日を返す。<BR>
     * <BR>
     * @@param l_blnIsAcquired - is買付。<BR>
     * <BR>
     * 買付の場合は true を、そうでない場合は false を指定する。<BR>
     * @@return Date
     * @@throws WEB3BaseException
     * @@roseuid 40AD62A3000E
     */
    public Date getDeliveryDate(boolean l_blnIsAcquired) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getDeliveryDate(boolean l_blnIsAcquired)";
        log.entering(STR_METHOD_NAME);

        //１）　@取引カレンダーを取得する
        TradingCalendar l_tradingCalendar =
            GtlUtils.getFinObjectManager().getTradingCalendar(
                this.getTradedProductId());

        //２）　@約定日を取得する
        Date l_datExecutedDate = this.getExecutedDate();

        //３）　@未済期間の取得
        int intOutstandingTerm = this.getOutstandingTerm(l_blnIsAcquired);

        //４）　@受渡日を取得する
        Date l_datDeliveryDate =
            l_tradingCalendar.roll(l_datExecutedDate, intOutstandingTerm - 1);

        //５）　@取得した受渡日を返す
        log.exiting(STR_METHOD_NAME);
        return l_datDeliveryDate;
    }

    /**
     * (get未済期間（買付）)<BR>
     * 未済期間（買付）を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get未済期間（買付）()を返す。<BR>
     * @@return int
     * @@roseuid 40AD62BF00D9
     */
    public int getOutstandingTermAcquired()
    {
        final String STR_METHOD_NAME = "getOutstandingTermAcquired()";
        log.entering(STR_METHOD_NAME); 
        int l_intOutstandingTermAcquired =
            ((MutualFundTradedProductRow) this.getDataSourceObject()).getBuyUndeliveringTerm();
        log.exiting(STR_METHOD_NAME);        
        return l_intOutstandingTermAcquired;
    }

    /**
     * (get未済期間（解約）)<BR>
     * 未済期間（解約）を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get未済期間（解約）()を返す。<BR>
     * @@return int
     * @@roseuid 40AD62BF00E9
     */
    public int getOutstandingTermSell()
    {
        final String STR_METHOD_NAME = "getOutstandingTermSell()";
        log.entering(STR_METHOD_NAME); 
        int l_intOutstandingTermSell =
            ((MutualFundTradedProductRow) this.getDataSourceObject()).getSellUndeliveringTerm();
        log.exiting(STR_METHOD_NAME);
        return l_intOutstandingTermSell;
    }

    /**
     * (get約定日移動日数)<BR>
     * 約定日移動日数を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get約定日移動日数()を返す。<BR>
     * @@return int
     * @@roseuid 40AD7BD9030D
     */
    public int getExecDateShiftDays()
    {
        final String STR_METHOD_NAME = "getExecDateShiftDays()";
        log.entering(STR_METHOD_NAME);        
        int l_intExecDateShiftDays =
            ((MutualFundTradedProductRow) this.getDataSourceObject()).getExecDateShiftdays();
        log.exiting(STR_METHOD_NAME);
        return l_intExecDateShiftDays;
    }

    /**
     * (is買付可能)<BR>
     * 買付可能な場合は true を、そうでない場合は false を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get買付可能区分()の戻り値が”0：買付不可”<BR>
     * の場合は false を、そうでない場合は true を返す。<BR>
     * @@return boolean
     * @@roseuid 40AD940201D4
     */
    public boolean isAcquiredPossible()
    {
        final String STR_METHOD_NAME = "isAcquiredPossible()";
        log.entering(STR_METHOD_NAME); 
        if (WEB3BuyPossibleDivDef.NOT_ACQUIRED.equals(
                ((MutualFundTradedProductRow) this.getDataSourceObject()).getBuyPossibleDiv()))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
    }

    /**
     * (is解約可能)<BR>
     * 解約請求可能な場合は true を、そうでない場合は false を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get解約可能区分()の戻り値が<BR>
     * ”0：解約不可”の場合は false を、そうでない場合は true を返す。<BR>
     * @@return boolean
     * @@roseuid 40AD944E01C5
     */
    public boolean isSellPossible()
    {
        final String STR_METHOD_NAME = "isSellPossible()";
        log.entering(STR_METHOD_NAME); 
        if (WEB3SellPossibleDivDef.NOT_SELL.equals(
                ((MutualFundTradedProductRow) this.getDataSourceObject()).getSellPossibleDiv()))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
    }

    /**
     * (is乗換可能)<BR>
     * 乗換可能な場合は true を、そうでない場合は false を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get乗換可能区分()の戻り値が<BR>
     * ”0：乗換不可”の場合は false を、そうでない場合は true を返す。<BR>
     * @@return boolean
     * @@roseuid 40AD946A03C8
     */
    public boolean isSwitchingPossible()
    {
        final String STR_METHOD_NAME = "isSwitchingPossible()";
        log.entering(STR_METHOD_NAME); 
        if (WEB3SwtPossibleDivDef.NOT_SWITCHING.equals(
                ((MutualFundTradedProductRow) this.getDataSourceObject()).getSwtPossibleDiv()))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
    }
    
    /**
     * (is募集可能)<BR>
     * 募集可能な場合は true を、そうでない場合は false を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get募集可能区分()の戻り値が”0：募集不可”<BR>
     * の場合は false を、そうでない場合は true を返す。<BR>
     * @@return boolean
     * @@roseuid 40AD946A03C8
     */
    public boolean  isRecruitPossible()
    {
        final String STR_METHOD_NAME = " isRecruitPossible()";
        log.entering(STR_METHOD_NAME);
        //this.getDataSourceObject().get募集可能区分()の戻り値が”0：募集不可”
        //の場合は false を、そうでない場合は true を返す
        String l_strRecruitPossibleDiv = 
            ((MutualFundTradedProductRow) this.getDataSourceObject()).getRecruitPossibleDiv();
        if (WEB3MFRecruitPossibleDivDef.NOT_RECRUIT.equals(l_strRecruitPossibleDiv))
        {
            log.exiting(STR_METHOD_NAME); 
            return false;
        }
        log.exiting(STR_METHOD_NAME);
        return true;
    }
    
    /**
     * (get約定日)<BR>
     * 引数.発注日に対する約定日を返す。<BR>
     * <BR>
     * １）　@取引カレンダーを取得する<BR>
     * 　@GtlUtils.getFinObjectManager().getTradingCalendar()をコールして、<BR>取引カレンダーを取得する。<BR>
     * 　@［getTradingCalendarに渡すパラメタ］<BR>
     * 　@　@取引銘柄ID： this.getTradedProductId()の戻り値<BR>
     * <BR>
     * ２）　@約定日移動日数の取得<BR>
     * 　@this.get約定日移動日数()をコールして、約定日移動日数を取得する。<BR>
     * <BR>
     * ３）　@約定日を取得する<BR>
     * 　@取引カレンダー.roll()をコールして、約定日を取得する。<BR>
     * 　@［rollに渡すパラメタ］<BR>
     * 　@　@発注日： 引数.発注日<BR>
     * 　@　@移動日数： 取得した約定日移動日数<BR>
     * <BR>
     * ４）　@取得した約定日を返す。<BR>
     * <BR>
     * @@param Date l_datCurrentBizDate -発注日
     * @@return Date
     * @@throws WEB3BaseException
     */
    public Date getExecutedDate(Date l_datCurrentBizDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getExecutedDate()";
        log.entering(STR_METHOD_NAME);

        //１）　@取引カレンダーを取得する
        TradingCalendar l_tradingCalendar =
            GtlUtils.getFinObjectManager().getTradingCalendar(
                this.getTradedProductId());

        //２）　@約定日移動日数の取得
        int intDateShiftDays = this.getExecDateShiftDays();

        //３）　@約定日を取得する
        Date l_datExecutedDate =
            l_tradingCalendar.roll(l_datCurrentBizDate, intDateShiftDays);

        //４）　@取得した約定日を返す
        log.exiting(STR_METHOD_NAME);
        return l_datExecutedDate;
    }
    
    /**
     * (get受渡日)<BR>
     * （getDailyDeliveryDateのオーバーロード）<BR>
     * <BR>
     * 引数.約定日に対する受渡日を返す。<BR>
     * <BR>
     * １）　@取引カレンダーを取得する<BR>
     * 　@GtlUtils.getFinObjectManager().getTradingCalendar()をコールして、<BR>取引カレンダーを取得する。<BR>
     * 　@［getTradingCalendarに渡すパラメタ］<BR>
     * 　@　@取引銘柄ID： this.getTradedProductId()の戻り値<BR>
     * <BR>
     * ２）　@未済期間の取得<BR>
     * 　@this.get未済期間()をコールして、未済期間を取得する。<BR>
     * 　@［get未済期間に渡すパラメタ］<BR>
     * 　@　@is買付： 引数.is買付<BR>
     * <BR>
     * ３）　@受渡日を取得する<BR>
     * 　@取引カレンダー.roll()をコールして、受渡日を取得する。<BR>
     * 　@［rollに渡すパラメタ］<BR>
     * 　@　@基準日： 引数.約定日<BR>
     * 　@　@移動日数： 取得した未済期間 - 1<BR>
     * <BR>
     * ４）　@取得した受渡日を返す。<BR>
     * <BR>
     * @@param l_blnIsAcquired - is買付。<BR>
     * @@param l_datSwtExecutedDate - 約定日 <BR>
     * <BR>
     * 買付の場合は true を、そうでない場合は false を指定する。<BR>
     * @@return Date
     * @@throws WEB3BaseException
     * @@roseuid 40AD62A3000E
     */
    public Date getDeliveryDate(boolean l_blnIsAcquired, Date l_datSwtExecutedDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getDeliveryDate(boolean l_blnIsAcquired)";
        log.entering(STR_METHOD_NAME);

        //１）　@取引カレンダーを取得する
        TradingCalendar l_tradingCalendar =
            GtlUtils.getFinObjectManager().getTradingCalendar(
                this.getTradedProductId());

        //３）　@未済期間の取得
        int intOutstandingTerm = this.getOutstandingTerm(l_blnIsAcquired);

        //４）　@受渡日を取得する
        Date l_datDeliveryDate =
            l_tradingCalendar.roll(l_datSwtExecutedDate, intOutstandingTerm - 1);

        //５）　@取得した受渡日を返す
        log.exiting(STR_METHOD_NAME);
        return l_datDeliveryDate;
    }
}
@
