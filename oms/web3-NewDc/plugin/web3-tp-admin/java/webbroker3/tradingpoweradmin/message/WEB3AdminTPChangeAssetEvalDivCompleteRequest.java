head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.42.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPChangeAssetEvalDivCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3AdminTPChangeAssetEvalDivCompleteRequest�N���X(WEB3AdminTPChangeAssetEvalDivCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/01/06 �x�� �a��(FLJ) �V�K�쐬
*/
package webbroker3.tradingpoweradmin.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;


/**
 * �]�͌v�Z���@@�ύX�������N�G�X�g�N���X
 */
public class WEB3AdminTPChangeAssetEvalDivCompleteRequest extends WEB3AdminTPChangeAssetEvalDivCommonRequest
{
   /**
    * PTYPE
    */
    public static final String PTYPE = "tradingpoweradmin_change_assetevaldiv_complete";

    /**
     * �Ïؔԍ�
     */
    public String adminPassword;

    /**
    * @@roseuid 41DBC23803B0
    */
   public WEB3AdminTPChangeAssetEvalDivCompleteRequest()
   {

   }

   /**
    * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B
    * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j
    *
    * �P�j�ύX���e�`�F�b�N
    * �e�N���X.validate()���ĂԁB
    *
    * �Q�j�Ǘ��҃p�X���[�h�̃`�F�b�N
    * �ȉ��ɊY������ꍇ�u�Ïؔԍ������w��ł��B�v�̗�O���X���[����B
    * �Ethis.�Ǘ��҃p�X���[�h == null
    * @@roseuid 41B9073202B9
    */
   public void validate() throws WEB3BusinessLayerException
   {
       final String METHOD_NAME = "validate()";
       super.validate();
  		if(adminPassword == null)
  		{
  			throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01090, METHOD_NAME);
  		}
   }

   /**
    * @@return webbroker3.common.message.WEB3GenResponse
    * @@roseuid 41DBC20300D2
    */
   public WEB3GenResponse createResponse()
   {
       return new WEB3AdminTPChangeAssetEvalDivCompleteResponse();
   }

   /**
    * ���̃N���X�̕�����\����Ԃ��B
    * @@return String
    */
   public String toString()
   {
       StringBuffer l_sb = new StringBuffer("WEB3AdminTPChangeAssetEvalDivCompleteRequest={");
//       l_sb.append(super.toString());
       l_sb.append("branchCode=").append(branchCode);
       l_sb.append(",accountCode=").append(accountCode);
       l_sb.append(",assetEvalDiv=").append(this.assetEvalDiv);
       l_sb.append(",adminPassword=").append(this.adminPassword);
       l_sb.append("}");

       return l_sb.toString();

   }
}
@
