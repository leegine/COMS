head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.44.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPFindPaymentRequisitionResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������������X�|���X�N���X(WEB3AdminTPFindPaymentRequisitionResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/01/06 �x�� �a��(FLJ) �V�K�쐬
*/
package webbroker3.tradingpoweradmin.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * ���������������X�|���X�N���X
 */
public class WEB3AdminTPFindPaymentRequisitionResponse extends WEB3GenResponse
{

    /**
     * PTYPE
     */
    public static final String PTYPE = "tradingpoweradmin_find_paymentrequisition";

     /**
      * ���������ꗗ
      */
     public WEB3AdminTPPaymentRequisition[] paymentRequisitions;

   /**
    * @@roseuid 41DE249902F4
    */
     public WEB3AdminTPFindPaymentRequisitionResponse()
     {

     }

   /**
    * ���̃N���X�̕�����\����Ԃ��B
    * @@return String
    */
     public String toString()
     {
         StringBuffer l_sb = new StringBuffer("WEB3AdminTPFindPaymentRequisitionResponse={");

         if(this.paymentRequisitions != null)
         {
             l_sb.append("paymentRequisitions[]={");
             for(int i = 0; i < this.paymentRequisitions.length; i++)
             {
                 l_sb.append(paymentRequisitions[i].toString());
                 l_sb.append(",");
             }
             l_sb.append("}");
         }
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
