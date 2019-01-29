head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.58.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondDomesticApplyProductInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 国内債券応募銘柄情報(WEB3BondDomesticApplyProductInfo.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/23 武波 (中訊) 新規作成 モデルNo.227
*/
package webbroker3.bd.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (国内債券応募銘柄情報)<BR>
 * 国内債券応募銘柄情報<BR>
 * <BR>
 * @@author 武波
 * @@version 1.0
 */
public class WEB3BondDomesticApplyProductInfo extends Message
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
     * (利率)<BR>
     * 利率<BR>
     */
    public String coupon;

    /**
     * (利率(課税後))<BR>
     * 利率(課税後)<BR>
     */
    public String rateAfterTax;

    /**
     * (応募単価)<BR>
     * 応募単価<BR>
     */
    public String applyPrice;

    /**
     * (申込単位)<BR>
     * 申込単位<BR>
     */
    public String applyUnit;

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
     * (利払日1)<BR>
     * 利払日1<BR>
     */
    public String couponPaymentDate1;

    /**
     * (利払日2)<BR>
     * 利払日2<BR>
     */
    public String couponPaymentDate2;

    /**
     * (年間利払回数)<BR>
     * 年間利払回数<BR>
     */
    public String yearlyInterestPayments;

    /**
     * (取扱開始日時)<BR>
     * 取扱開始日時<BR>
     */
    public Date tradeStartDate;

    /**
     * (取扱終了日時)<BR>
     * 取扱終了日時<BR>
     */
    public Date tradeEndDate;

    /**
     * (取引可能区分)<BR>
     * 取引可能区分<BR>
     * <BR>
     * 0：不可<BR>
     * 1：可能<BR>
     * 2：応募枠超過<BR>
     */
    public String tradingPossDiv;

    /**
     * (国内債券応募銘柄情報)<BR>
     * コンストラクタ
     * @@roseuid 466645BA01F1
     */
    public WEB3BondDomesticApplyProductInfo()
    {

    }
}
@
