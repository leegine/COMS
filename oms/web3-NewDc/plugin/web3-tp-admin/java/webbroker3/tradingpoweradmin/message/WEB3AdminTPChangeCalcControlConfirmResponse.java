head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.41.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPChangeCalcControlConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �]�͐���@@�\�ύX�m�F���X�|���X�N���X(WEB3AdminTPChangeCalcControlConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/01/06 �x�� �a��(FLJ) �V�K�쐬
*/
package webbroker3.tradingpoweradmin.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * �]�͐���@@�\�ύX�m�F���X�|���X�N���X
 */
public class WEB3AdminTPChangeCalcControlConfirmResponse extends WEB3GenResponse
{
    /**
     * PTYPE
     */
     public static final String PTYPE = "tradingpoweradmin_change_calccontrol_confirm";

   /**
    * @@roseuid 41DBC9290021
    */
   public WEB3AdminTPChangeCalcControlConfirmResponse()
   {

   }

   /**
    * ���̃N���X�̕�����\����Ԃ��B
    * @@return String
    */
   public String toString()
   {

       StringBuffer l_sb = new StringBuffer("WEB3AdminTPChangeCalcControlConfirmResponse={");

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
