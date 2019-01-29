head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.40.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPChangeAssetEvalDivCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3AdminTPChangeAssetEvalDivCommonRequest�N���X(WEB3AdminTPChangeAssetEvalDivCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/01/06 �x�� �a��(FLJ) �V�K�쐬
*/
package webbroker3.tradingpoweradmin.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * �]�͌v�Z���@@�ύX���ʃ��N�G�X�g�N���X
 */
public class WEB3AdminTPChangeAssetEvalDivCommonRequest extends WEB3GenRequest
{
    public static final String PTYPE = "tradingpoweradmin_change_assetevaldiv_common";

   /**
    * (���X�R�[�h)
    */
   public String branchCode;

   /**
    * (�ڋq�R�[�h)
    */
   public String accountCode;

   /**
    * (�a��،��]�����敪)
    */
   public String assetEvalDiv;

   /**
    * @@roseuid 41DBC14F0083
    */
   public WEB3AdminTPChangeAssetEvalDivCommonRequest()
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
    *
    * �Q�j�a��،��]�����敪�̃`�F�b�N
    * �@@�ȉ��ɊY������ꍇ�u�a��،��]�����敪��null�v�̗�O���X���[����B
    * �@@�@@�@@�@@�@@�Ethis.�a��،��]�����敪 == null
    * @@roseuid 41D23C74010A
    */
   public void validate() throws WEB3BusinessLayerException

   {
  		final String METHOD_NAME = "validate";

  		//�P�j���X�R�[�h�A�ڋq�R�[�h�`�F�b�N

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

   		//�Q�j�a��،��]�����敪�̃`�F�b�N
   		if(assetEvalDiv == null)
   		{
   			throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01891, METHOD_NAME);
   		}
   }

   /**
    * @@return webbroker3.common.message.WEB3GenResponse
    * @@roseuid 41DBC20300D2
    */
   public WEB3GenResponse createResponse()
   {
       return null;
   }


}
@
