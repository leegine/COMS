head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.40.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPReCalcResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 余力再計算実行クラスResponseクラス(WEB3AdminTpReCalcResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2005/01/31 kikuchi(SCS) 新規作成
*/
package webbroker3.tradingpoweradmin.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;

/**
 *  余力再計算実行レスポンスクラス
 */
public class WEB3AdminTPReCalcResponse extends WEB3GenResponse
{
    public static final String PTYPE = "tradingpoweradmin_recalc";

   /**
    * 実行件数
    */
   public String practiceCnt;

   /**
    * 非実行件数
    */
   public String failCnt;

   /**
    * 受付日時
    */
   public Date receiptDay;

    
   /**
    * @@roseuid 41DBC24A012F
    */
   public WEB3AdminTPReCalcResponse() 
   {
    
   }

   /**
    * このクラスの文字列表現を返す。
    * @@return String
    */
   public String toString()
   {
       
       StringBuffer l_sb = new StringBuffer("WEB3AdminTPReCalcResponse={");

       l_sb.append("renewCnt=").append(practiceCnt);
       l_sb.append("failCnt=").append(failCnt);
       l_sb.append("receiptDay=").append(receiptDay);

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
