head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.43.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPFindCalcControlResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �]�͐���@@�\�������X�|���X�N���X(WEB3AdminTPFindCalcControlResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/01/06 �x�� �a��(FLJ) �V�K�쐬
*/
package webbroker3.tradingpoweradmin.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * �]�͐���@@�\�������X�|���X�N���X
 */
public class WEB3AdminTPFindCalcControlResponse extends WEB3GenResponse
{
    /**
     * PTYPE
     */
     public static final String PTYPE = "tradingpoweradmin_find_calccontrol";

   /**
    * �]�͐�����ꗗ
    */
   public webbroker3.tradingpoweradmin.message.WEB3AdminTPCalcControlInfo[] calcControlInfos;

   /**
    * @@roseuid 41DBC9280292
    */
   public WEB3AdminTPFindCalcControlResponse()
   {

   }

	   /**
	    * ���̃N���X�̕�����\����Ԃ��B
	    * @@return String
	    */
	   public String toString()
	   {

	       StringBuffer l_sb = new StringBuffer("WEB3AdminTPFindCalcControlResponse={");

	        if(this.calcControlInfos != null)
	        {
	            l_sb.append("calcControlInfos[]={");
	            for(int i = 0; i < this.calcControlInfos.length; i++)
	            {
	                l_sb.append(calcControlInfos[i].toString());
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
