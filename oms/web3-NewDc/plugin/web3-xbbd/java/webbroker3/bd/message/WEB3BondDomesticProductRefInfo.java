head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.57.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondDomesticProductRefInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : àÂÁ¿Æïîñ(WEB3BondDomesticProductRefInfo.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/09 ½¶q (u) VKì¬ dlÏXEfNo.200
*/
package webbroker3.bd.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (àÂÁ¿Æïîñ)<BR>
 * àÂÁ¿Æïîñ<BR>
 * <BR>
 * @@author ½¶q
 * @@version 1.0
 */
public class WEB3BondDomesticProductRefInfo extends Message
{

   /**
    * (Á¿ID)<BR>
    * Á¿ID<BR>
    */
   public String productId;

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
    * (Á¿¼iHOSTj)<BR>
    * Á¿¼iHOSTj<BR>
    */
   public String productNameHost;

   /**
    * (Á¿¼iWEB3))<BR>
    * Á¿¼iWEB3)<BR>
    */
   public String productNameWEB3;

   /**
    * (åP¿)<BR>
    * åP¿<BR>
    */
   public String applyPrice;

   /**
    * (æµæª)<BR>
    * æµæª<BR>
    * <BR>
    * 0FsÂ<BR>
    * 2FÚq<BR>
    */
   public String tradeHandleDiv;

   /**
    * (¦)<BR>
    * ¦<BR>
    */
   public String coupon;

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
    * (àÂÁ¿Æïîñ)<BR>
    * RXgN^<BR>
    * @@roseuid 466379A50138
    */
   public WEB3BondDomesticProductRefInfo()
   {

   }
}
@
