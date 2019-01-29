head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.39.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPReleaseDepositAutoTransferConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 保証金自動振替停止解除確認レスポンスクラス(WEB3AdminTPReleaseDepositAutoTransferConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/01/06 堀野 和美(FLJ) 新規作成
*/
package webbroker3.tradingpoweradmin.message;

import webbroker3.common.message.WEB3GenResponse;


/**
 * 保証金自動振替停止解除確認レスポンスクラス
 */
public class WEB3AdminTPReleaseDepositAutoTransferConfirmResponse extends WEB3GenResponse
{
    /**
     * PTYPE
     */
     public static final String PTYPE = "tradingpoweradmin_release_depositautotransfer_confirm";

     /**
      * このクラスの文字列表現を返す。
      * @@return String
      */
     public String toString()
     {

         StringBuffer l_sb = new StringBuffer("WEB3AdminTPReleaseDepositAutoTransferConfirmResponse={");

         if(errorInfo != null)
         {
             l_sb.append(",errorInfo={");
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
