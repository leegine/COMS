head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.39.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPReCalcInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �]�͍Čv�Z���̓N���XResponse�N���X(WEB3AdminTPReCalcInputResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2005/01/31 kikuchi(SCS) �V�K�쐬
*/
package webbroker3.tradingpoweradmin.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 *  �]�͍Čv�Z���͍s���X�|���X�N���X
 */
public class WEB3AdminTPReCalcInputResponse extends WEB3GenResponse
{
    public static final String PTYPE = "tradingpoweradmin_recalc_input";

   /**
    * @@roseuid 41DBC24A012F
    */
   public WEB3AdminTPReCalcInputResponse() 
   {
    
   }

   /**
    * ���̃N���X�̕�����\����Ԃ��B
    * @@return String
    */
   public String toString()
   {
       
       StringBuffer l_sb = new StringBuffer("WEB3AdminTpAssetRepeatCalcInputResponse={");

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
