head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.43.23;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondApplyBuyInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : Âå/tüÍX|X(WEB3BondApplyBuyInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/05 «ô (u) VKì¬
                       : 2006/09/29 £«F (u) f 098
*/

package webbroker3.bd.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (Âå/tüÍX|X)<BR>
 * Âå/tüÍX|X<BR>
 * <BR>
 * @@author «ô
 * @@version 1.0
 */
public class WEB3BondApplyBuyInputResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "bond_applyBuyInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200609051106L;

    /**
     * (tÂ\z)<BR>
     * tÂ\z<BR>
     */
    public String tradingPower;

    /**
     * (Á¿ID)<BR>
     * Á¿ID<BR>
     */
    public String productId;

    /**
     * (Á¿¼)<BR>
     * Á¿¼<BR>
     */
    public String productName;

    /**
     * (íÊR[h)<BR>
     * íÊR[h<BR>
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
     * (¦)<BR>
     * ¦<BR>
     */
    public String coupon;

    /**
     * (NÔ¥ñ)<BR>
     * NÔ¥ñ<BR>
     */
    public String yearlyInterestPayments;

    /**
     * (¥ú1)<BR>
     * ¥ú1<BR>
     */
    public String interestPaymentDay1;

    /**
     * (¥ú2)<BR>
     * ¥ú2<BR>
     */
    public String interestPaymentDay2;

    /**
     * (ÊÝR[h)<BR>
     * ÊÝR[h<BR>
     */
    public String currencyCode;

    /**
     * (\PÊ)<BR>
     * \PÊ<BR>
     */
    public String tradeUnit;

    /**
     * (Åá\Ê)<BR>
     * Åá\Ê<BR>
     */
    public String minOrderQuantity;

    /**
     * (Å\Ê)<BR>
     * Å\Ê<BR>
     */
    public String maxOrderQuantity;

    /**
     * (åJnú)<BR>
     * åJnú<BR>
     */
    public Date recruitStartDate;

    /**
     * (åI¹ú)<BR>
     * åI¹ú<BR>
     */
    public Date recruitEndDate;

    /**
     * (tP¿)<BR>
     * tP¿<BR>
     */
    public String buyPrice;

    /**
     * (­sú)<BR>
     * ­sú<BR>
     */
    public Date issueDate;

    /**
     * (Òú)<BR>
     * Òú<BR>
     */
    public Date maturityDate;

    /**
     * (tî×Ö[g)<BR>
     * tî×Ö[g<BR>
     */
    public String buyBaseFxRate;

    /**
     * (Ïæªê)<BR>
     * Ïæªê<BR>
     * <BR>
     * 1F~Ý<BR>
     * 2FOÝ<BR>
     */
    public String[] settleDivList;

    /**
     * (düÌ×Ö[g)<BR>
     * düÌ×Ö[g<BR>
     */
    public String fxRateAtStock;

    /**
     * (Âå/tüÍX|X)<BR>
     * RXgN^<BR>
     * @@roseuid 44C59B670129
     */
    public WEB3BondApplyBuyInputResponse()
    {

    }

    /**
     * RXgN^B<BR>
     * wè³ê½NGXgIuWFNgÉÎ·éX|XIuWFNgð¶¬·éB<BR>
     * @@param l_request - NGXgIuWFNg
     */
    public WEB3BondApplyBuyInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
