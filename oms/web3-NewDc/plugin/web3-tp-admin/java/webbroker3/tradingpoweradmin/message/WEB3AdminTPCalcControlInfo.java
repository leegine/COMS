head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.43.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPCalcControlInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : ]Í§äîñNX(WEB3AdminTPCalcControlInfo.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/01/06 xì aü(FLJ) VKì¬
Revision History : 2007/07/26 æâÑQ (u) fFNo.006
*/
package webbroker3.tradingpoweradmin.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * ]Í§äîñNX
 */
public class WEB3AdminTPCalcControlInfo extends Message
{
   /**
    * Úq]ÍðID
    */
   public String calcConditionId;

   /**
    * XR[h
    */
   public String branchCode;

   /**
    * ÚqR[h
    */
   public String accountCode;

   /**
    * Úq¼
    */
   public String accountName;

   /**
    * æøâ~æª
    */
   public String tradingStop;

   /**
    * MpVK]Íæª
    */
   public String marginOpenPositionStop;

   /**
    * æ¨OPVK]Íæª
    */
   public String ifoOpenPositionStop;

   /**
    * oà]Íæª
    */
   public String paymentStop;

   /**
    * »Ì¼¤it]Íæª
    */
   public String otherTradingStop;

   /**
    * (ÇØ¢üàæª)<BR>
    * 0:ÇØ¢üàÈµ 1:ÇØ¢üà è<BR>
    */
   public String additionalDepositStop;

   /**
    * @@roseuid 41DBC92901D6
    */
   public WEB3AdminTPCalcControlInfo()
   {

   }

   /**
    * ±ÌNXÌ¶ñ\»ðÔ·B
    * @@return String
    */
   public String toString()
   {

       StringBuffer l_sb = new StringBuffer("WEB3AdminTPCalcControlInfo={");
       l_sb.append(",calcConditionId=").append(this.calcConditionId);
       l_sb.append(",branchCode=").append(this.branchCode);
       l_sb.append("calcConditionId=").append(this.accountCode);
       l_sb.append(",accountName=").append(this.accountName);
       l_sb.append(",tradingStop=").append(this.tradingStop);
       l_sb.append(",marginOpenPositionStop=").append(this.marginOpenPositionStop);
       l_sb.append(",ifoOpenPositionStop=").append(this.ifoOpenPositionStop);
       l_sb.append(",paymentStop=").append(this.paymentStop);
       l_sb.append(",otherTradingStop=").append(this.otherTradingStop);
       l_sb.append("}");

       return l_sb.toString();

   }

}
@
