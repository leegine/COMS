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
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : àÂåÁ¿îñ(WEB3BondDomesticApplyProductInfo.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/23 g (u) VKì¬ fNo.227
*/
package webbroker3.bd.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (àÂåÁ¿îñ)<BR>
 * àÂåÁ¿îñ<BR>
 * <BR>
 * @@author g
 * @@version 1.0
 */
public class WEB3BondDomesticApplyProductInfo extends Message
{

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
     * (¦)<BR>
     * ¦<BR>
     */
    public String coupon;

    /**
     * (¦(ÛÅã))<BR>
     * ¦(ÛÅã)<BR>
     */
    public String rateAfterTax;

    /**
     * (åP¿)<BR>
     * åP¿<BR>
     */
    public String applyPrice;

    /**
     * (\PÊ)<BR>
     * \PÊ<BR>
     */
    public String applyUnit;

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
     * (¥ú1)<BR>
     * ¥ú1<BR>
     */
    public String couponPaymentDate1;

    /**
     * (¥ú2)<BR>
     * ¥ú2<BR>
     */
    public String couponPaymentDate2;

    /**
     * (NÔ¥ñ)<BR>
     * NÔ¥ñ<BR>
     */
    public String yearlyInterestPayments;

    /**
     * (æµJnú)<BR>
     * æµJnú<BR>
     */
    public Date tradeStartDate;

    /**
     * (æµI¹ú)<BR>
     * æµI¹ú<BR>
     */
    public Date tradeEndDate;

    /**
     * (æøÂ\æª)<BR>
     * æøÂ\æª<BR>
     * <BR>
     * 0FsÂ<BR>
     * 1FÂ\<BR>
     * 2Fåg´ß<BR>
     */
    public String tradingPossDiv;

    /**
     * (àÂåÁ¿îñ)<BR>
     * RXgN^
     * @@roseuid 466645BA01F1
     */
    public WEB3BondDomesticApplyProductInfo()
    {

    }
}
@
