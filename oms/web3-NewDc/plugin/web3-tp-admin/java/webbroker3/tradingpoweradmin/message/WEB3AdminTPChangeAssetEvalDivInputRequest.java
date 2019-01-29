head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.43.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPChangeAssetEvalDivInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3AdminTPChangeAssetEvalDivInputRequest�N���X(WEB3AdminTPChangeAssetEvalDivInputRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/01/06 �x�� �a��(FLJ) �V�K�쐬
*/
package webbroker3.tradingpoweradmin.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * �]�͌v�Z���@@�ύX���͉�ʃ��N�G�X�g�N���X
 */
public class WEB3AdminTPChangeAssetEvalDivInputRequest extends WEB3GenRequest
{

    public static final String PTYPE = "tradingpoweradmin_change_assetevaldiv_input";

   /**
    * ���X�R�[�h
    */
   public String branchCode;

   /**
    * �ڋq�R�[�h
    */
   public String accountCode;

   /**
    * @@roseuid 41DBB1DE0087
    */
   public WEB3AdminTPChangeAssetEvalDivInputRequest()
   {

   }

   /**
    * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B
    * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j
    *
    * �P�j���X�R�[�h�A�ڋq�R�[�h�`�F�b�N
    * �@@�ȉ��̃`�F�b�N���s���B
    * �@@�P�|�P�jthis.���X�R�[�h���ȉ��̏����ɊY������ꍇ�A
    * �@@�@@�@@�@@�@@�u���X�R�[�h�G���[�v�̗�O���X���[����B
    * �@@�@@�@@�@@�@@�Ethis.���X�R�[�h == null
    * �@@�@@�@@�@@�@@�Ethis.���X�R�[�h.length != 3
    * �@@�@@�@@�@@�@@�Ethis.���X�R�[�h != ���l
    *
    * �@@�P�|�Q�jthis.�ڋq�R�[�h���ȉ��̏����ɊY������ꍇ�A
    * �@@�@@�@@�@@�@@�u�ڋq�R�[�h�G���[�v�̗�O���X���[����B
    * �@@�@@�@@�@@�@@�Ethis.�ڋq�R�[�h == null
    * �@@�@@�@@�@@�@@�Ethis.�ڋq�R�[�h.length != 6
    * �@@�@@�@@�@@�@@�Ethis.�ڋq�R�[�h != ���l
    * @@roseuid 41BD464400C2
    */
   public void validate() throws WEB3BusinessLayerException
   {
   		final String METHOD_NAME = "validate";

   		//���X�R�[�h�`�F�b�N
   		if(branchCode == null ||
   				branchCode.length() != 3)
   		{
   			throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00779, METHOD_NAME);
   		}
   		try
		{
   			Integer.parseInt(branchCode);
		}
   		catch(NumberFormatException ne)
		{
   			throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00779, METHOD_NAME, ne.getMessage());

		}

   		//�ڋq�R�[�h�`�F�b�N
   		if(accountCode == null ||
   				accountCode.length() != 6)
   		{
   			throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00780, METHOD_NAME);
   		}
   		try
		{
   			Integer.parseInt(accountCode);
		}
   		catch(NumberFormatException ne)
		{
   			throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00780, METHOD_NAME, ne.getMessage());

		}


   }

   /**
    * @@return webbroker3.common.message.WEB3GenResponse
    * @@roseuid 41DBB1DE01CF
    */
   public WEB3GenResponse createResponse()
   {
       return new WEB3AdminTPChangeAssetEvalDivInputResponse();
   }

   /**
    * ���̃N���X�̕�����\����Ԃ��B
    * @@return String
    */
   public String toString()
   {

       StringBuffer l_sb = new StringBuffer("WEB3AdminTPChangeAssetEvalDivInputRequest={");
//       l_sb.append(super.toString());
       l_sb.append("branchCode=").append(branchCode);
       l_sb.append(",accountCode=").append(accountCode);
       l_sb.append("}");

       return l_sb.toString();

   }
}
@
