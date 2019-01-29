head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.42.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPChangeAssetEvalDivInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3AdminTPChangeAssetEvalDivInputResponseクラス(WEB3AdminTPChangeAssetEvalDivInputResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/01/06 堀野 和美(FLJ) 新規作成
*/
package webbroker3.tradingpoweradmin.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * 余力計算方法@変更入力画面レスポンスクラス
 */
public class WEB3AdminTPChangeAssetEvalDivInputResponse extends WEB3GenResponse
{
    public static final String PTYPE = "tradingpoweradmin_change_assetevaldiv_input";

   /**
    * 顧客名
    */
   public String accountName;

   /**
    * 預り証券評価制区分
    */
   public String assetEvalDiv;

   /**
    * @@roseuid 41DBC24A012F
    */
   public WEB3AdminTPChangeAssetEvalDivInputResponse()
   {

   }

   /**
    * このクラスの文字列表現を返す。
    * @@return String
    */
   public String toString()
   {

       StringBuffer l_sb = new StringBuffer("WEB3AdminTPChangeAssetEvalDivInputResponse={");

       l_sb.append("accountName=").append(accountName);
       l_sb.append(",assetEvalDiv=").append(assetEvalDiv);

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
