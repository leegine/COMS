head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.38.23;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondBalanceReferenceDetailUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : ÂcÆï¾×(WEB3BondBalanceReferenceDetailUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/20 æâÑQ (u) VKì¬
*/

package webbroker3.bd.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (ÂcÆï¾×)<BR>
 * ÂcÆï¾×<BR>
 * 
 * @@author æâÑQ
 * @@version 1.0
 */

public class WEB3BondBalanceReferenceDetailUnit extends Message
{
    /**
     * ÛLYID<BR>
     */
    public String id;
    
    /**
     * (Â^Cv )<BR>
     * Â^Cv <BR>
     * <BR>
     * 0FÂ<BR>
     * 4FOÂ<BR>
     * 8FWB<BR>
     * 9FCB<BR>
     * 10FàÂ<BR>
     */
    public String bondKind;
    
    /**
     * (íÊ)<BR>
     * íÊ<BR>
     */
    public String bondCategCode;
    
    /**
     * (Á¿R[h)<BR>
     * Á¿R[h<BR>
     */
    public String productCode;
    
    /**
     * (ñR[h)<BR>
     * ñR[h<BR>
     */
    public String productIssueCode;
    
    /**
     * (Á¿¼)<BR>
     * Á¿¼<BR>
     */
    public String productName;
    
    /**
     * (ûÀæª)<BR>
     * ûÀæª<BR>
     */
    public String taxType;
    
    /**
     * (pÂ\Ê)<BR>
     * pÂ\Ê<BR>
     */
    public String sellAbleQty;
    
    /**
     * (ps\Ê)<BR>
     * ps\Ê<BR>
     */
    public String sellDisableQty;
    
    /**
     * (ÊÝ)<BR>
     * ÊÝ<BR>
     */
    public String currencyCode;
    
    /**
     * (pi]¿jP¿)<BR>
     * pi]¿jP¿<BR>
     */
    public String sellEvaluationPrice;
    
    /**
     * (TZ]¿zi~Ýj)<BR>
     * TZ]¿zi~Ýj<BR>
     */
    public String yenEstimatedAsset;
    
    /**
     * (TZ]¿ziOÝj)<BR>
     * TZ]¿ziOÝj<BR>
     */
    public String foreignEstimatedAsset;
    
    /**
     * (­sú)<BR>
     * ­sú<BR>
     */
    public Date issueDate;
    
    /**
     * (­s¿i)<BR>
     * ­s¿i<BR>
     */
    public String issuePrice;
    
    /**
     * (Òú)<BR>
     * Òú<BR>
     */
    public Date maturityDate;
    
    /**
     * (NÔ¥¢ñ)<BR>
     * NÔ¥¢ñ<BR>
     */
    public String yearlyInterestPayments;
    
    /**
     * (¥úP)<BR>
     * ¥úPiMM/dd`®jj<BR>
     */
    public String interestPaymentDay1;
    
    /**
     * (¥úQ)<BR>
     * ¥úQiMM/dd`®jj<BR>
     */
    public String interestPaymentDay2;
    
    /**
     * (N¦)<BR>
     * N¦<BR>
     */
    public String coupon;
       
    /**
     * (pÂ\æª)<BR>
     * pÂ\æª<BR>
     * <BR>
     * 0FsÂ<BR>
     * 1FÂ<BR>
     */
    public String sellPossDiv;
    
    /**
     * (ÂcÆï¾×)<BR>
     * RXgN^<BR>
     */
    public WEB3BondBalanceReferenceDetailUnit()
    {
        
    }

}
@
