head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.45.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondDomesticProductBasicInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : àÂÁ¿î{îñ(WEB3BondDomesticProductBasicInfo.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/09 ½¶q (u) VKì¬ dlÏXEfNo.200
*/
package webbroker3.bd.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (àÂÁ¿î{îñ)<BR>
 * àÂÁ¿î{îñ<BR>
 * <BR>
 * @@author ½¶q
 * @@version 1.0
 */
public class WEB3BondDomesticProductBasicInfo extends Message
{

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
    * (Á¿¼iHOST))<BR>
    * Á¿¼iHOST)<BR>
    */
   public String productNameHost;

   /**
    * (íÊR[h)<BR>
    * íÊR[h<BR>
    */
   public String bondCategCode;

   /**
    * (­sí)<BR>
    * ­sí<BR>
    */
   public String[] issueCouponType;

   /**
    * (­sú)<BR>
    * ­sú<BR>
    */
   public Date issueDate;

   /**
    * (åP¿)<BR>
    * åP¿<BR>
    */
   public String applyPrice;

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
   public String couponPaymentDate1;

   /**
    * (¥ú2)<BR>
    * ¥ú2<BR>
    */
   public String couponPaymentDate2;

   /**
    * (Òú)<BR>
    * Òú<BR>
    */
   public Date maturityDate;

   /**
    * (åJnúiSONARj)<BR>
    * åJnúiSONARj<BR>
    */
   public Date recruitStartDateSONAR;

   /**
    * (åI¹úiSONARj)<BR>
    * åI¹úiSONARj<BR>
    */
   public Date recruitEndDateSONAR;

   /**
    * (àÂÁ¿î{îñ)<BR>
    * RXgN^<BR>
    * @@roseuid 466379500138
    */
   public WEB3BondDomesticProductBasicInfo()
   {

   }
}
@
