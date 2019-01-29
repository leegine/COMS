head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.44.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPStopDepositAutoTransferCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ۏ؋������U�֒�~�o�^���ʃ��N�G�X�g�N���X(WEB3AdminTPStopDepositAutoTransferCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/01/06 �x�� �a��(FLJ) �V�K�쐬
*/
package webbroker3.tradingpoweradmin.message;

import java.util.Date;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * �ۏ؋������U�֒�~�o�^���ʃ��N�G�X�g�N���X
 */
public class WEB3AdminTPStopDepositAutoTransferCommonRequest extends WEB3GenRequest
{
    /**
     * PTYPE
     */
     public static final String PTYPE = "tradingpoweradmin_stop_depositautotransfer_common";

   /**
    * ���X�R�[�h
    */
   public String branchCode;

   /**
    * �ڋq�R�[�h
    */
   public String accountCode;

   /**
    * ��~�J�n��
    */
   public Date autotransferStopStart;

   /**
    * ��~�I����
    */
   public Date autotransferStopEnd;

   /**
    * @@roseuid 41DBC979008E
    */
   public WEB3AdminTPStopDepositAutoTransferCommonRequest()
   {

   }

   /**
    * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B
    * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j
    *
    * �P�j���X�R�[�h�A�ڋq�R�[�h�`�F�b�N
    * �ȉ��̃`�F�b�N���s���B
    * �P�|�P�j���X�R�[�h���ȉ��̏����ɊY������ꍇ�A
    * �u���X�R�[�h�G���[�v�̗�O���X���[����B
    * �E���X�R�[�h == null
    * �E���X�R�[�h.length != 3
    * �E���X�R�[�h != ���l
    *
    * �P�|�Q�j�ڋq�R�[�h���ȉ��̏����ɊY������ꍇ�A
    * �u�ڋq�R�[�h�G���[�v�̗�O���X���[����B
    * �E�ڋq�R�[�h == null
    * �E�ڋq�R�[�h.length != 6
    * �E�ڋq�R�[�h != ���l
    *
    * �Q�j��~���Ԃ̃`�F�b�N
    * �ȉ��̃`�F�b�N���s���B
    * �Q�|�P�j��~�J�n�����ȉ��ɊY�������ꍇ�u��~�J�n���G���[�v�̗�O���X���[����B
    * �E��~�J�n�� == null
    * �E��~�J�n�� > ��~�I����
    *
    * �Q�|�Q�j��~�I�������ȉ��ɊY�������ꍇ�u��~�I�����G���[�v�̗�O���X���[����B
    * �E��~�I���� == null
    * @@roseuid 41D124410072
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

        //�P�j�������Ԏw��̏ꍇ
        if(this.autotransferStopStart == null)
        {
  			throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01900, METHOD_NAME);
        }
        if(this.autotransferStopEnd == null)
        {
  			throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01901, METHOD_NAME);
        }

        if(this.autotransferStopStart.after(this.autotransferStopEnd))
        {
  			throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01902, METHOD_NAME);
        }

   }

   /**
    * @@return webbroker3.common.message.WEB3GenResponse
    * @@roseuid 41DBC97900EC
    */
   public WEB3GenResponse createResponse()
   {
       return null;
   }
}
@
