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
Copyright        : ()εa€ Ψ\[VVXeζρ
File Name        : WEB3AdminTPChangeAssetEvalDivInputResponseNX(WEB3AdminTPChangeAssetEvalDivInputResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/01/06 xμ aό(FLJ) VKμ¬
*/
package webbroker3.tradingpoweradmin.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * ]ΝvZϋ@@ΟXόΝζΚX|XNX
 */
public class WEB3AdminTPChangeAssetEvalDivInputResponse extends WEB3GenResponse
{
    public static final String PTYPE = "tradingpoweradmin_change_assetevaldiv_input";

   /**
    * ΪqΌ
    */
   public String accountName;

   /**
    * aθΨ]Ώ§ζͺ
    */
   public String assetEvalDiv;

   /**
    * @@roseuid 41DBC24A012F
    */
   public WEB3AdminTPChangeAssetEvalDivInputResponse()
   {

   }

   /**
    * ±ΜNXΜΆρ\»πΤ·B
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
