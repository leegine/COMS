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
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �]�͍Čv�Z���s�N���XResponse�N���X(WEB3AdminTpReCalcResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2005/01/31 kikuchi(SCS) �V�K�쐬
*/
package webbroker3.tradingpoweradmin.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;

/**
 *  �]�͍Čv�Z���s���X�|���X�N���X
 */
public class WEB3AdminTPReCalcResponse extends WEB3GenResponse
{
    public static final String PTYPE = "tradingpoweradmin_recalc";

   /**
    * ���s����
    */
   public String practiceCnt;

   /**
    * ����s����
    */
   public String failCnt;

   /**
    * ��t����
    */
   public Date receiptDay;

    
   /**
    * @@roseuid 41DBC24A012F
    */
   public WEB3AdminTPReCalcResponse() 
   {
    
   }

   /**
    * ���̃N���X�̕�����\����Ԃ��B
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
