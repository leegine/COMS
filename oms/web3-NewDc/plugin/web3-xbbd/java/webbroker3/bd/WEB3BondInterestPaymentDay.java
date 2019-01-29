head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondInterestPaymentDay.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 利払日(InterestPaymentDay.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/08 肖志偉 (中訊) 新規作成
*/
package webbroker3.bd;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 利払日クラス
 * 
 * @@author 肖志偉(中訊)
 * @@version 1.0
 */
public class WEB3BondInterestPaymentDay
{
    /**
     * (前回利払日)<BR>
     * 前回利払日<BR>
     */
    private Date preInterestPaymentDay;
    
    /**
     * (次回利払日)<BR>
     * 次回利払日<BR>
     */
    private Date nextInterestPaymentDay;
    
    /**
     * (利率)<BR>
     * 利率<BR>
     */
    private BigDecimal coupon;
    
    /**
     * (初回利払日)<BR>
     * 初回利払日<BR>
     */
    private Date firstInterestPaymentDay;
    
    /**
     * (最終利払日)<BR>
     * 最終利払日<BR>
     */
    private Date finalInterestPaymentDay;

    /**
     * (set前回利払日)<BR>
     * 前回利払日をセットする。<BR>
     * @@param l_datPreInterestPaymentDay - (前回利払日)<BR>
     * 前回利払日<BR>
     */
    public void setPreInterestPaymentDay(Date l_datPreInterestPaymentDay)
    {
        this.preInterestPaymentDay = l_datPreInterestPaymentDay;
    }
    
    /**
     * (set次回利払日)<BR>
     * 次回利払日をセットする。<BR>
     * @@param l_datNextInterestPaymentDay - (次回利払日)<BR>
     * 次回利払日<BR>
     */
    public void setNextInterestPaymentDay(Date l_datNextInterestPaymentDay)
    {
        this.nextInterestPaymentDay = l_datNextInterestPaymentDay;
    }
    
    /**
     * (set初回利払日)<BR>
     * 初回利払日をセットする。<BR>
     * @@param l_datFirstInterestPaymentDay - (初回利払日)<BR>
     * 初回利払日<BR>
     */
    public void setFirstInterestPaymentDay(Date l_datFirstInterestPaymentDay)
    {
        this.firstInterestPaymentDay = l_datFirstInterestPaymentDay;
    }
    
    /**
     * (set最終利払日)<BR>
     * 最終利払日をセットする。<BR>
     * @@param l_datFinalInterestPaymentDay - (最終利払日)<BR>
     * 最終利払日<BR>
     */
    public void setFinalInterestPaymentDay(Date l_datFinalInterestPaymentDay)
    {
        this.finalInterestPaymentDay = l_datFinalInterestPaymentDay;
    }
   
    /**
     * (set利率)<BR>
     * 利率をセットする。<BR>
     * @@param l_bdCoupon - (利率)<BR>
     * 利率<BR>
     */
    public void setCoupon(BigDecimal l_bdCoupon)
    {
        this.coupon = l_bdCoupon;
    }

    /**
     * (get前回利払日)<BR>
     * 前回利払日を取得する。<BR>
     * @@return Date
     */
    public Date getPreInterestPaymentDay()
    {
        return preInterestPaymentDay;
    }
    
    /**
     * (get次回利払日)<BR>
     * 次回利払日を取得する。<BR>
     * @@return Date
     */
    public Date getNextInterestPaymentDay()
    {
        return nextInterestPaymentDay;
    }
    
    /**
     * (get初回利払日)<BR>
     * 初回利払日を取得する。<BR>
     * @@return Date
     */
    public Date getFirstInterestPaymentDay()
    {
        return firstInterestPaymentDay;
    }

    /**
     * (get最終利払日)<BR>
     * 最終利払日を取得する。<BR>
     * @@return Date
     */
    public Date getFinalInterestPaymentDay()
    {
        return finalInterestPaymentDay;
    }

    /**
     * (get利率)<BR>
     * 利率を取得する。<BR>
     * @@return BigDecimal
     */
    public BigDecimal getCoupon()
    {
        return coupon;
    }
    
}
@
