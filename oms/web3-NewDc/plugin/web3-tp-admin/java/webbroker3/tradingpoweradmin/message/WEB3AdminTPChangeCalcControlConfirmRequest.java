head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.42.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPChangeCalcControlConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 余力制御機@能変更確認リクエストクラス(WEB3AdminTPChangeCalcControlConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/01/06 堀野 和美(FLJ) 新規作成
*/
package webbroker3.tradingpoweradmin.message;

import webbroker3.common.message.WEB3GenResponse;


/**
 * 余力制御機@能変更確認リクエストクラス
 */
public class WEB3AdminTPChangeCalcControlConfirmRequest extends WEB3AdminTPChangeCalcControlCommonRequest
{
    /**
     * PTYPE
     */
     public static final String PTYPE = "tradingpoweradmin_change_calccontrol_confirm";

   /**
    * @@roseuid 41DBC92801E6
    */
   public WEB3AdminTPChangeCalcControlConfirmRequest()
   {

   }

   /**
    * @@return webbroker3.common.message.WEB3GenResponse
    */
   public WEB3GenResponse createResponse()
   {
       return new WEB3AdminTPChangeCalcControlConfirmResponse();
   }

   /**
    * このクラスの文字列表現を返す。
    * @@return String
    */
   public String toString()
   {
       StringBuffer l_sb = new StringBuffer("WEB3AdminTPChangeCalcControlConfirmRequest={");
//       l_sb.append(super.toString());
       l_sb.append("calcConditionId=").append(this.calcConditionId);
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
