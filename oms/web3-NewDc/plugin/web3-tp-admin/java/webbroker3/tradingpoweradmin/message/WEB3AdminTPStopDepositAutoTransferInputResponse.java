head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.44.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPStopDepositAutoTransferInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 保証金自動振替停止登録入力画面レスポンスクラス(WEB3AdminTPStopDepositAutoTransferInputResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/01/06 堀野 和美(FLJ) 新規作成
*/
package webbroker3.tradingpoweradmin.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * 保証金自動振替停止登録入力画面レスポンスクラス
 */
public class WEB3AdminTPStopDepositAutoTransferInputResponse extends WEB3GenResponse
{
    /**
     * PTYPE
     */
     public static final String PTYPE = "tradingpoweradmin_stop_depositautotransfer_input";

   /**
    * 顧客名
    */
   public String accountName;

   /**
    * @@roseuid 41DBC97A01B7
    */
   public WEB3AdminTPStopDepositAutoTransferInputResponse()
   {

   }

   /**
    * このクラスの文字列表現を返す。
    * @@return String
    */
   public String toString()
   {

       StringBuffer l_sb = new StringBuffer("WEB3AdminTPStopDepositAutoTransferInputResponse={");

       l_sb.append(",accountName=").append(this.accountName);

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
