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
filename	WEB3RuitoProduct.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 拡張累投銘柄クラス(WEB3RuitoProduct)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/8  韋念瓊 (中訊) 新規作成
                   2004/12/02 韋念瓊 (中訊) 残対応
*/
package webbroker3.xbruito;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbruito.stdimpls.RuitoProductImpl;

import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * 拡張累投銘柄クラス。<BR>
 */
public class WEB3RuitoProduct extends RuitoProductImpl
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RuitoProduct.class);
    
    /**
     * コンストラクタ。<BR>
     * <BR>
     * super(銘柄Row)をコールする。<BR>
     * @@param l_productRow - 銘柄Row<BR>
     * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
     * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
     * @@roseuid 406BEF2503A8
     */
    public WEB3RuitoProduct(ProductRow l_productRow)
        throws DataQueryException, DataNetworkException
    {
        super(l_productRow);
    }

    /**
     * コンストラクタ。<BR>
     * <BR>
     * super(累投銘柄Row)をコールする。<BR>
     * @@param l_ruitoProductRow - 累投銘柄Row<BR>
     * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
     * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
     * @@roseuid 406BEF250399
     */
    public WEB3RuitoProduct(RuitoProductRow l_ruitoProductRow)
        throws DataQueryException, DataNetworkException
    {
        super(l_ruitoProductRow);
    }

    /**
     * this.銘柄名を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get銘柄名()を返す。<BR>
     * @@return String
     * @@roseuid 407636F1035B
     */
    public String getProductName()
    {
        String l_strProductName =
            ((RuitoProductParams) 
            this.getDataSourceObject()).getStandardName().trim();
        return l_strProductName;
    }

    /**
     * this.コースを返す。<BR>
     * <BR>
     * this.getDataSourceObject().getコース()を返す。<BR>
     * @@return String
     * @@roseuid 4076370802CE
     */
    public String getCourse()
    {
        String l_strCourse = ((RuitoProductParams) 
        this.getDataSourceObject()).getCourse().trim();
        return l_strCourse;
    }

    /**
     * this.プランを返す。<BR>
     * <BR>
     * this.getDataSourceObject().getプラン()を返す。<BR>
     * @@return String
     * @@roseuid 407637120280
     */
    public String getPlan()
    {
        String l_strPlan = ((RuitoProductParams) 
        this.getDataSourceObject()).getPlan().trim();
        return l_strPlan;
    }

    /**
     * this.MRFコードを返す。<BR>
     * <BR>
     * this.getDataSourceObject().getMRFコード()を返す。<BR>
     * @@return String
     * @@roseuid 4076371B031C
     */
    public String getMRFCode()
    {
        String l_strMRFCode =
            ((RuitoProductParams) 
            this.getDataSourceObject()).getMrfFundCode().trim();
        return l_strMRFCode;
    }

    /**
     * this.買付上限金額を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get買付上限金額()を返す。<BR>
     * @@return double
     * @@roseuid 407637270195
     */
    public double getBuyMaxPrice()
    {
        double l_dblBuyMaxPrice =
            ((RuitoProductParams) this.getDataSourceObject()).getBuyMaxPrice();
        return l_dblBuyMaxPrice;
    }

    /**
     * this.解約上限金額を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get解約上限金額()を返す。<BR>
     * @@return double
     * @@roseuid 407637380270
     */
    public double getSellMaxPrice()
    {
        double l_dblSellMaxPrice =
            ((RuitoProductParams) this.getDataSourceObject()).getSellMaxPrice();
        return l_dblSellMaxPrice;
    }

    /**
     * this.買付下限金額を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get買付下限金額()を返す。<BR>
     * @@return double
     * @@roseuid 40763743033B
     */
    public double getBuyMinPrice()
    {
        double l_dblBuyMinPrice =
            ((RuitoProductParams) this.getDataSourceObject()).getBuyMinPrice();
        return l_dblBuyMinPrice;
    }

    /**
     * this.解約下限金額を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get解約下限金額()を返す。<BR>
     * @@return double
     * @@roseuid 4076374F00DA
     */
    public double getSellMinPrice()
    {
        double l_dblSellMinPrice =
            ((RuitoProductParams) this.getDataSourceObject()).getSellMinPrice();
        return l_dblSellMinPrice;
    }

    /**
     * this.受渡方法@を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get受渡方法@()を返す。<BR>
     * <BR>
     * 0：選択可能<BR>
     * 1：銀行振込<BR>
     * 2：証券口座入金<BR>
     * @@return String
     * @@roseuid 407A53C603D4
     */
    public String getPaymentMethod()
    {
        String l_strPaymentMethod =
            ((RuitoProductParams) this.getDataSourceObject()).getPaymentMethod();
        return l_strPaymentMethod;
    }

    /**
     * this.指定方法@（買付）を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get指定方法@（買付）()を返す。<BR>
     * <BR>
     * 0：選択可能<BR>
     * 3：金額指定<BR>
     * 4：口数指定<BR>
     * @@return String
     * @@roseuid 407A544202BB
     */
    public String getPaymentMethodBuy()
    {
        String l_strPaymentMethodBuy =
            ((RuitoProductParams) 
            this.getDataSourceObject()).getBuyDesignateMethod();
        return l_strPaymentMethodBuy;
    }

    /**
     * this.指定方法@（解約）を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get指定方法@（解約）()を返す。<BR>
     * <BR>
     * 0：選択可能<BR>
     * 2：全部指定<BR>
     * 3：金額指定<BR>
     * 4：口数指定<BR>
     * @@return String
     * @@roseuid 407A544202BC
     */
    public String getPaymentMethodSell()
    {
        String l_strPaymentMethodSell =
            ((RuitoProductParams) 
            this.getDataSourceObject()).getSellDesignateMethod();
        return l_strPaymentMethodSell;
    }
    
    /**
     * (is買付可能)
     * 当銘柄が指定された発注日に買付可能かをチェックする。 <BR>
     * <BR>
     * １）　@買付開始日を取得する。<BR>
     *  this.getDataSourceObject().get買付開始日()をコールして、買付開始日を取得する。<BR>
     * ２）　@買付終了日を取得する。<BR>
     *  this.getDataSourceObject().get買付終了日()をコールして、買付終了日を取得する。<BR>
     * <BR>
     * ３）　@買付開始日あるいは買付終了日が設定されていない場合は false を返す。<BR>
     * <BR>
     * ４）　@買付開始日、買付終了日、引数.発注日をYYYYMMDD形式の文字列に変換する。<BR>
     * <BR>
     * ５）　@４）で変換した文字列が以下の条件に合致する場合は true を、<BR>
     *      そうでない場合は false を返す。<BR>
     * <BR>
     *      買付開始日 ≦ 発注日 ≦ 買付終了日<BR>
     * @@param l_datBizDate - 発注日
     * @@return booleans
     * @@roseuid 407A544202BC
     */
    public boolean isBuyPossible(Date l_datBizDate)
    {
        final String STR_METHOD_NAME =
            "isBuyPossible(Date l_datBizDate)";        
        log.entering(STR_METHOD_NAME);
        
        //１）買付開始日を取得する。
        RuitoProductRow l_ruitoProductRow = (RuitoProductRow)this.getDataSourceObject();
        Date l_datBuyStartDate = l_ruitoProductRow.getBuyStartDate();
        
        //２）買付終了日を取得する。
        Date l_datBuyEndDate = l_ruitoProductRow.getBuyEndDate();
        
        //３）買付開始日あるいは買付終了日が設定されていない場合は false を返す。 
        if (l_datBuyStartDate == null || l_datBuyEndDate == null)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
       
        //４）買付開始日、買付終了日、引数.発注日をYYYYMMDD形式の文字列に変換する。
        Date l_datFormatBizDate =
            WEB3DateUtility.getDate(
            WEB3DateUtility.formatDate(l_datBizDate, "yyyyMMdd"),
            "yyyyMMdd");
        Date l_datFormatBuyStartDate =
            WEB3DateUtility.getDate(
            WEB3DateUtility.formatDate(l_datBuyStartDate, "yyyyMMdd"),
            "yyyyMMdd");
        Date l_datFormatBuyEndDate =
            WEB3DateUtility.getDate(
            WEB3DateUtility.formatDate(l_datBuyEndDate, "yyyyMMdd"),
            "yyyyMMdd");
        
        log.debug("買付開始日 = " + WEB3DateUtility.formatDate(l_datFormatBuyStartDate, "yyyyMMdd"));
        log.debug("発注日 = " + WEB3DateUtility.formatDate(l_datFormatBizDate, "yyyyMMdd"));
        log.debug("買付終了日 = " + WEB3DateUtility.formatDate(l_datFormatBuyEndDate, "yyyyMMdd"));
        
        //５）　@４）で変換した文字列が以下の条件に合致する場合は true を、
        //      そうでない場合は false を返す。
        //      買付開始日 ≦ 発注日 ≦ 買付終了日
        if (WEB3DateUtility.compareToDay(l_datFormatBizDate, l_datFormatBuyStartDate) >= 0 &&
            WEB3DateUtility.compareToDay(l_datFormatBizDate, l_datFormatBuyEndDate) <= 0)
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }        
    }
    
    /**
     * (is解約可能)
     * 当銘柄が指定された発注日に解約可能かをチェックする。<BR>
     * <BR>
     * １）　@解約開始日を取得する。<BR>
     *  this.getDataSourceObject().get解約開始日()をコールして、解約開始日を取得する。<BR>
     * ２）　@解約終了日を取得する。<BR>
     *  this.getDataSourceObject().get解約終了日()をコールして、解約終了日を取得する。<BR>
     * <BR>
     * ３）　@解約開始日あるいは解約終了日が設定されていない場合は false を返す。 <BR>
     * <BR>
     * ４）　@解約開始日、解約終了日、引数.発注日をYYYYMMDD形式の文字列に変換する。<BR>
     * <BR>
     * ５）　@４）で変換した文字列が以下の条件に合致する場合は true を、<BR>
     *      そうでない場合は false を返す。
     * <BR>
     *      解約開始日 ≦ 発注日 ≦ 解約終了日<BR>
     * @@param l_datBizDate - 発注日
     * @@return boolean
     * @@roseuid 407A544202BC
     */
    public boolean isSellPossible(Date l_datBizDate)
    {
        final String STR_METHOD_NAME =
            "isSellPossible(Date l_datBizDate)";           
        log.entering(STR_METHOD_NAME);
        
        //１）解約開始日を取得する。
        RuitoProductRow l_ruitoProductRow = (RuitoProductRow)this.getDataSourceObject();
        Date l_datSellStartDate = l_ruitoProductRow.getSellStartDate();
        
        //２）解約終了日を取得する。
        Date l_datSellEndDate = l_ruitoProductRow.getSellEndDate();
        
        //３）解約開始日あるいは解約終了日が設定されていない場合は false を返す。 
        if (l_datSellStartDate == null || l_datSellEndDate == null)
        {
            log.debug("解約開始日あるいは解約終了日が設定されていない場合は false を返す。");
            log.exiting(STR_METHOD_NAME);
            return false;
        }
       
        //４）解約開始日、解約終了日、引数.発注日をYYYYMMDD形式の文字列に変換する。
        Date l_datFormatBizDate =
            WEB3DateUtility.getDate(
            WEB3DateUtility.formatDate(l_datBizDate, "yyyyMMdd"),
            "yyyyMMdd");
        Date l_datFormatSellStartDate =
            WEB3DateUtility.getDate(
            WEB3DateUtility.formatDate(l_datSellStartDate, "yyyyMMdd"),
            "yyyyMMdd");
        Date l_datFormatSellEndDate =
            WEB3DateUtility.getDate(
            WEB3DateUtility.formatDate(l_datSellEndDate, "yyyyMMdd"),
            "yyyyMMdd");        
        
        log.debug("解約開始日 = " + WEB3DateUtility.formatDate(l_datFormatSellStartDate, "yyyyMMdd"));
        log.debug("発注日 = " + WEB3DateUtility.formatDate(l_datFormatBizDate, "yyyyMMdd"));
        log.debug("解約終了日 = " + WEB3DateUtility.formatDate(l_datFormatSellEndDate, "yyyyMMdd"));
        
        //５）　@４）で変換した文字列が以下の条件に合致する場合は true を、
        //      そうでない場合は false を返す。
        //      解約開始日 ≦ 発注日 ≦ 解約終了日
        if (WEB3DateUtility.compareToDay(l_datFormatBizDate, l_datFormatSellStartDate) >= 0 &&
            WEB3DateUtility.compareToDay(l_datFormatBizDate, l_datFormatSellEndDate) <= 0)
        {
            log.debug("解約開始日 ≦ 発注日 ≦ 解約終了日");
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }
}
@
