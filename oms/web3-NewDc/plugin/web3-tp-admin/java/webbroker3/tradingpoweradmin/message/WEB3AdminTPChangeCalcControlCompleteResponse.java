head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.41.31;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPChangeCalcControlCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 余力制御機@能変更完了レスポンスクラス(WEB3AdminTPChangeCalcControlCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/01/06 堀野 和美(FLJ) 新規作成
*/
package webbroker3.tradingpoweradmin.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;

/**
 * 余力制御機@能変更完了レスポンスクラス
 */
public class WEB3AdminTPChangeCalcControlCompleteResponse extends WEB3GenResponse
{
    /**
     * PTYPE
     */
     public static final String PTYPE = "tradingpoweradmin_change_calccontrol_complete";

   /**
    * 更新日時
    */
   public Date lastUpdatedTime;

   /**
    * @@roseuid 41DBC92900CD
    */
   public WEB3AdminTPChangeCalcControlCompleteResponse()
   {
   }


   /**
    * このクラスの文字列表現を返す。
    * @@return String
    */
   public String toString()
   {

       StringBuffer l_sb = new StringBuffer("WEB3AdminTPFindCalcControlResponse={");

       l_sb.append("lastUpdatedTime=").append(this.lastUpdatedTime);
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
