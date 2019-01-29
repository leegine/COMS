head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.41.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPStopDepositAutoTransferInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ۏ؋������U�֒�~�o�^���͉�ʃ��N�G�X�g�N���X(WEB3AdminTPStopDepositAutoTransferInputRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/01/06 �x�� �a��(FLJ) �V�K�쐬
*/
package webbroker3.tradingpoweradmin.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * �ۏ؋������U�֒�~�o�^���͉�ʃ��N�G�X�g�N���X
 */
public class WEB3AdminTPStopDepositAutoTransferInputRequest extends WEB3GenRequest
{

    /**
     * PTYPE
     */
     public static final String PTYPE = "tradingpoweradmin_stop_depositautotransfer_input";

   /**
    * ���X�R�[�h
    */
   public String branchCode;

   /**
    * �ڋq�R�[�h
    */
   public String accountCode;

   /**
    * @@roseuid 41DBC978035D
    */
   public WEB3AdminTPStopDepositAutoTransferInputRequest()
   {

   }

   /**
    * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B
    * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j
    *
    * �P�j���X�R�[�h�A�ڋq�R�[�h�`�F�b�N
    * �ȉ��̃`�F�b�N���s���B
    * �P�|�P�jthis.���X�R�[�h���ȉ��̏����ɊY������ꍇ�A
    * �u���X�R�[�h�G���[�v�̗�O���X���[����B
    * �Ethis.���X�R�[�h == null
    * �Ethis.���X�R�[�h.length != 3
    * �Ethis.���X�R�[�h != ���l
    *
    * �P�|�Q�jthis.�ڋq�R�[�h���ȉ��̏����ɊY������ꍇ�A
    * �u�ڋq�R�[�h�G���[�v�̗�O���X���[����B
    * �Ethis.�ڋq�R�[�h == null
    * �Ethis.�ڋq�R�[�h.length != 6
    * �Ethis.�ڋq�R�[�h != ���l
    * @@roseuid 41BE420D030B
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
    * @@roseuid 41DBC97803AB
    */
   public WEB3GenResponse createResponse()
   {
       return new WEB3AdminTPStopDepositAutoTransferInputResponse();
   }

}
@
