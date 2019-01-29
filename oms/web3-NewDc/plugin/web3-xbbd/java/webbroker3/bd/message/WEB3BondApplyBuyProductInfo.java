head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.46.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondApplyBuyProductInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券応募/買付銘柄情報(WEB3BondApplyBuyProductInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/05 唐性峰 (中訊) 新規作成
*/

package webbroker3.bd.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (債券応募/買付銘柄情報)<BR>
 * 債券応募/買付銘柄情報<BR>
 * <BR>
 * @@author 唐性峰
 * @@version 1.0
 */
public class WEB3BondApplyBuyProductInfo extends Message
{
    
    /**
     * (銘柄ID)<BR>
     * 銘柄ID<BR>
     */
    public String productId;
    
    /**
     * (銘柄名)<BR>
     * 銘柄名<BR>
     */
    public String productName;
    
    /**
     * (種別コード)<BR>
     * 種別コード<BR>
     */
    public String bondCategCode;
    
    /**
     * (S&P)<BR>
     * S&P<BR>
     */
    public String sAndP;
    
    /**
     * (Moody's)<BR>
     * Moody's<BR>
     */
    public String moodys;
    
    /**
     * (利率)<BR>
     * 利率<BR>
     */
    public String coupon;
    
    /**
     * (年間利払回数)<BR>
     * 年間利払回数<BR>
     */
    public String yearlyInterestPayments;
    
    /**
     * (利払日1)<BR>
     * 利払日1<BR>
     */
    public String interestPaymentDay1;
    
    /**
     * (利払日2)<BR>
     * 利払日2<BR>
     */
    public String interestPaymentDay2;
    
    /**
     * (通貨コード)<BR>
     * 通貨コード<BR>
     */
    public String currencyCode;
    
    /**
     * (申込単位)<BR>
     * 申込単位<BR>
     */
    public String tradeUnit;
    
    /**
     * (最低申込数量)<BR>
     * 最低申込数量<BR>
     */
    public String minOrderQuantity;
    
    /**
     * (最高申込数量)<BR>
     * 最高申込数量<BR>
     */
    public String maxOrderQuantity;
    
    /**
     * (応募開始日)<BR>
     * 応募開始日<BR>
     */
    public Date recruitStartDate;
    
    /**
     * (応募終了日)<BR>
     * 応募終了日<BR>
     */
    public Date recruitEndDate;
    
    /**
     * (買付単価)<BR>
     * 買付単価<BR>
     */
    public String buyPrice;
    
    /**
     * (発行日)<BR>
     * 発行日<BR>
     */
    public Date issueDate;
    
    /**
     * (償還日)<BR>
     * 償還日<BR>
     */
    public Date maturityDate;
    
    /**
     * (取引可能区分)<BR>
     * 取引可能区分<BR>
     * <BR>
     * 1：応募<BR>
     * 2：買付<BR>
     */
    public String posibleDiv;
    
    /**
     * (債券応募/買付銘柄情報)<BR>
     * コンストラクタ<BR>
     * @@roseuid 44BDBBAE0176
     */
    public WEB3BondApplyBuyProductInfo() 
    {
     
    }
}
@
