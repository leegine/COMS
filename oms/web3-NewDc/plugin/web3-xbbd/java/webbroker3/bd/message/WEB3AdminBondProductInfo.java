head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.49.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondProductInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : Á¿îñ(WEB3AdminBondProductInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 æâÑQ (u) VKì¬
                     2006/10/08 ü· (u) dlÏXEf106
*/

package webbroker3.bd.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (Á¿îñ)<BR>
 * Á¿îñNX
 * <BR>
 * @@author æâÑQ
 * @@version 1.0
 */
public class WEB3AdminBondProductInfo extends Message
{

    /**
     * (Á¿R[hiWEB3j)<BR>
     * Á¿R[hiWEB3j
     */
    public String productCode;

    /**
     * (Á¿¼)<BR>
     * æµÁ¿¼
     */
    public String productName;

    /**
     * (tP¿)<BR>
     * tP¿
     */
    public String buyPrice;

    /**
     * (pP¿)<BR>
     * pP¿
     */
    public String sellPrice;

    /**
     * (¦)<BR>
     * ¦
     */
    public String coupon;

    /**
     * (ÊÝR[h)<BR>
     * ÊÝ
     */
    public String currencyCode;

    /**
     * (­sú)<BR>
     * ­sú
     */
    public Date issueDate;

    /**
     * (NÔ¥ñ)<BR>
     * NÔ¥ñ
     */
    public String yearlyInterestPayments;

    /**
     * (¥ú1)<BR>
     * ¥ú1
     */
    public String interestPaymentDay1;

    /**
     * (¥ú2)<BR>
     * ¥ú2
     */
    public String interestPaymentDay2;

    /**
     * (Òú)<BR>
     * Òú
     */
    public Date maturityDate;

    /**
     *(düÌ×Ö[g)<BR>
     *düÌ×Ö[g
     */
    public String fxRateAtStock;

    /**
     * (JXgfBA)<BR>
     * JXgfBA
     */
    public WEB3AdminBondCustodianUnit custodianInfo;

    /**
     * (×Ö[g)<BR>
     * ×Ö[g
     */
    public WEB3AdminBondFxRateInfo fxRateInfo;

    /**
     * (Á¿îñ)<BR>
     * RXgN^
     * @@roseuid 44DB2ED10264
     */
    public WEB3AdminBondProductInfo()
    {

    }
}
@
