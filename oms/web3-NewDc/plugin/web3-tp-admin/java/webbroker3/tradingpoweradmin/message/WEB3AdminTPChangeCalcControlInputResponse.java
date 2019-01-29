head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.39.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPChangeCalcControlInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 余力制御機@能変更入力画面レスポンスクラス(WEB3AdminTPChangeCalcControlInputResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/01/06 堀野 和美(FLJ) 新規作成
*/
package webbroker3.tradingpoweradmin.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * 余力制御機@能変更入力画面レスポンスクラス
 */
public class WEB3AdminTPChangeCalcControlInputResponse extends WEB3GenResponse
{
    /**
     * PTYPE
     */
     public static final String PTYPE = "tradingpoweradmin_change_calccontrol_input";

   /**
    * 余力制御情報
    */
   public WEB3AdminTPCalcControlInfo calcControlInfo;

   /**
    * @@roseuid 41DBC928035D
    */
   public WEB3AdminTPChangeCalcControlInputResponse()
   {

   }

   /**
    * このクラスの文字列表現を返す。
    * @@return String
    */
   public String toString()
   {

       StringBuffer l_sb = new StringBuffer("WEB3AdminTPChangeCalcControlInputResponse={");

       l_sb.append("calcControlInfo={").append(this.calcControlInfo);
       l_sb.append(",calcConditionId=").append(this.calcControlInfo.calcConditionId);
       l_sb.append(",branchCode=").append(this.calcControlInfo.branchCode);
       l_sb.append(",accountCode=").append(this.calcControlInfo.accountCode);
       l_sb.append(",accountName=").append(this.calcControlInfo.accountName);
       l_sb.append(",tradingStop=").append(this.calcControlInfo.tradingStop);
       l_sb.append(",marginOpenPositionStop=").append(this.calcControlInfo.marginOpenPositionStop);
       l_sb.append(",ifoOpenPositionStop=").append(this.calcControlInfo.ifoOpenPositionStop);
       l_sb.append(",paymentStop=").append(this.calcControlInfo.paymentStop);
       l_sb.append(",otherTradingStop=").append(this.calcControlInfo.otherTradingStop);
       l_sb.append("}");

       if(errorInfo != null)
       {
           l_sb.append("errorInfo={");
           l_sb.append("errorTag=").append(errorInfo.getErrorTag());
           l_sb.append(",errorCode=").append(errorInfo.getErrorCode());
           l_sb.append(",errorMessage=").append(errorInfo.getErrorMessage());
           l_sb.append("}");

       }

       l_sb.append("}");

       return l_sb.toString();


   }

}
@
