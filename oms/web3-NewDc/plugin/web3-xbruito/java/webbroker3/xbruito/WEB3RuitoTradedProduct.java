head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoTradedProduct.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 拡張累投取引銘柄クラス(WEB3RuitoTradedProduct)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/8 韋念瓊 (中訊) 新規作成
*/
package webbroker3.xbruito;

import java.util.Date;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingCalendar;
import com.fitechlabs.xtrade.plugin.tc.xbruito.stdimpls.RuitoTradedProductImpl;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoTradedProductParams;

/**
 * 拡張累投取引銘柄クラス。<BR>
 */
public class WEB3RuitoTradedProduct extends RuitoTradedProductImpl
{
    /**
     * コンストラクタ。<BR>
     * @@param l_tradedProductRow - 取引銘柄Row<BR>
     * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
     * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
     * @@roseuid 406BEFFA0399
     */
    public WEB3RuitoTradedProduct(TradedProductRow l_tradedProductRow)
        throws DataQueryException, DataNetworkException
    {
        super(l_tradedProductRow);
    }

    /**
     * コンストラクタ。<BR>
     * @@param l_ruitoTradeProductRow - 累投取引銘柄Row<BR>
     * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
     * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
     * @@roseuid 406BEFFA0389
     */
    public WEB3RuitoTradedProduct(RuitoTradedProductRow l_ruitoTradeProductRow)
        throws DataQueryException, DataNetworkException
    {
        super(l_ruitoTradeProductRow);
    }

    /**
     * （getDeliveryDateShiftDaysのオーバーロード）<BR>
     * <BR>
     * 受渡日が基準日の何営業日後かを返す。<BR>
     * <BR>
     * １）　@引数（is買付）が true <BR>
     * の場合はthis.get受渡日移動日数（買付）()の戻り値を返す。<BR>
     * <BR>
     * ２）　@そうでない場合はthis.get受渡日移動日数（解約）()の戻り値を返す。<BR>
     * @@param isBuy - is買付。<BR>
     * <BR>
     * 買付の場合は true を、そうでない場合は false を返す。<BR>
     * @@return int
     * @@roseuid 406BDFA9002E
     */
    protected int getDeliveryDateShiftDays(boolean isBuy)
    {
        if (isBuy)
        {
            return this.getDeliveryDateShiftDaysBuy();
        }
        else
        {
            return this.getDeliveryDateShiftsDaysSell();
        }
    }

    /**
     * （getDailyDeliveryDateのオーバーロード）<BR>
     * <BR>
     * 有効日に対する受渡日を返す。<BR>
     * <BR>
     * １）　@取引カレンダーを取得する<BR>
     * 　@GtlUtils.getFinObjectManager().getTradingCalendar()をコールして、<BR>
     * 取引カレンダーを取得する。<BR>
     * 　@［getTradingCalendarに渡すパラメタ］<BR>
     * 　@　@取引銘柄ID： this.getTradedProductId()の戻り値<BR>
     * <BR>
     * ２）　@発注日を取得する<BR>
     * 　@取引カレンダー.getCurrentBizDate()をコールして発注日を取得する。<BR>
     * <BR>
     * ３）　@受渡日移動日数の取得<BR>
     * 　@this.get受渡日移動日数()をコールして、受渡日移動日数を取得する。<BR>
     * 　@［get受渡日移動日数に渡すパラメタ］<BR>
     * 　@　@is買付： 引数.is買付<BR>
     * <BR>
     * ４）　@受渡日を取得する<BR>
     * 　@取引カレンダー.roll()をコールして、受渡日を取得する。<BR>
     * 　@［rollに渡すパラメタ］<BR>
     * 　@　@発注日： 取得した発注日<BR>
     * 　@　@移動日数： 取得した受渡日移動日数<BR>
     * <BR>
     * ５）　@取得した受渡日を返す。<BR>
     * @@param isBuy - is買付。<BR>
     * <BR>
     * 買付の場合は true を、そうでない場合は false を指定する。<BR>
     * @@return Date
     * @@roseuid 406BE2910108
     */
    public Date getDailyDeliveryDate(boolean isBuy)
    {
        //1)取引カレンダーを取得する
        TradingCalendar l_tradeCalendar;
        l_tradeCalendar =
            GtlUtils.getFinObjectManager().getTradingCalendar(
            this.getTradedProductId());

        //2)発注日を取得する
        Date l_datCurrentBizDate;
            l_datCurrentBizDate = l_tradeCalendar.getCurrentBizDate();

        //3)受渡日移動日数の取得
        int l_intDeliveryShiftDays = this.getDeliveryDateShiftDays(isBuy);
        //4)受渡日を取得する
        Date l_datDeliveryDate = l_tradeCalendar.roll(
            l_datCurrentBizDate, l_intDeliveryShiftDays);
        return l_datDeliveryDate;
    }

    /**
     * 買付停止を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get買付停止()を返す。<BR>
     * @@return String
     * @@roseuid 40763DF1008C
     */
    public String getBuyStop()
    {
        String l_strBuyStop =
            ((RuitoTradedProductParams) 
            this.getDataSourceObject()).getBuyStop().trim();
        return l_strBuyStop;
    }

    /**
     * 解約停止を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get解約停止()を返す。<BR>
     * @@return String
     * @@roseuid 40763DFA02DE
     */
    public String getSellStop()
    {
        String l_strSellStop =
            ((RuitoTradedProductParams) 
            this.getDataSourceObject()).getSellStop().trim();
        return l_strSellStop;
    }

    /**
     * 受渡日移動日数（買付）を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get受渡移動日数（買付）()を返す。<BR>
     * @@return int
     * @@roseuid 40763E2D02FD
     */
    public int getDeliveryDateShiftDaysBuy()
    {
        int l_intDeliveryDateShiftDaysBuy =
            ((RuitoTradedProductParams) 
            this.getDataSourceObject()).getBuyDeliveryDateShiftDays();
        return l_intDeliveryDateShiftDaysBuy;
    }

    /**
     * 受渡日移動日数（解約）を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get受渡移動日数（解約）()を返す。<BR>
     * @@return int
     * @@roseuid 40763E430186
     */
    public int getDeliveryDateShiftsDaysSell()
    {
        int l_intDeliveryDateShiftDaysSell =
            ((RuitoTradedProductParams) 
            this.getDataSourceObject()).getSellDeliveryDateShiftDays();
        return l_intDeliveryDateShiftDaysSell;
    }
}
@
