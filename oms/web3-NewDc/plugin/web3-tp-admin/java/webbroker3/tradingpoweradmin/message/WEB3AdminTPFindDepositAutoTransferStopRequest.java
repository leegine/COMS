head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.43.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPFindDepositAutoTransferStopRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ۏ؋������U�֒�~�ڋq�������N�G�X�g�N���X(WEB3AdminTPFindDepositAutoTransferStopRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/01/06 �x�� �a��(FLJ) �V�K�쐬
*/
package webbroker3.tradingpoweradmin.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * �ۏ؋������U�֒�~�ڋq�������N�G�X�g�N���X
 */
public class WEB3AdminTPFindDepositAutoTransferStopRequest extends WEB3GenRequest
{

    /**
     * PTYPE
     */
     public static final String PTYPE = "tradingpoweradmin_find_depositautotransfer_stop";

     /**
    * ���X�R�[�h
    */
   public String branchCodes[];

   /**
    * �ڋq�R�[�h
    */
   public String accountCode;

   /**
    * @@roseuid 41DBC97901A7
    */
   public WEB3AdminTPFindDepositAutoTransferStopRequest()
   {

   }

   /**
    * �`�F�b�N���e�F
    *
    * �P�j���X�R�[�h�`�F�b�N
    * �ȉ��̃`�F�b�N���s���B
    * �P�|�P�j���X�R�[�h���ȉ��̏����ɊY������ꍇ�A
    * �u���X�R�[�h�G���[�v�̗�O���X���[����B
    * ���X�R�[�h != null�̏ꍇ
    * �E���X�R�[�h.length != 3
    * �E���X�R�[�h != ���l
    *
    * �Q�|�P�j
    * [�ڋq�R�[�h != null�̏ꍇ]
    * ���X�R�[�h null�`�F�b�N�A����3��
    * �ڋq�R�[�h ����6��
    * @@roseuid 41D252D703BA
    */
   public void validate() throws WEB3BusinessLayerException
   {
       final String METHOD_NAME = "validate()";

       //���X�͕K���w�肳��Ă���O��Ȃ̂ōŏ��Ƀ`�F�b�N
  		//���X�R�[�h�`�F�b�N
  		if(branchCodes == null || branchCodes.length == 0)
  			throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00779, METHOD_NAME);

  		for(int i = 0; i < branchCodes.length; i++)
  		{
  		    if(branchCodes[i].length() != 3)
  		        throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00779, METHOD_NAME);
  	  		try
  			{
  	  			Integer.parseInt(branchCodes[i]);
  			}
  	  		catch(NumberFormatException ne)
  			{
  	  			throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00779, METHOD_NAME, ne.getMessage());
  			}
  		}

  		//�ڋq�R�[�h�`�F�b�N
  		if(accountCode != null)
  		{
  		    if(accountCode.length() != 6)
  		        throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00780, METHOD_NAME);
	  		try
			{
	  			Integer.parseInt(accountCode);
			}
	  		catch(NumberFormatException ne)
			{
	  			throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00780, METHOD_NAME, ne.getMessage());
			}
  		}


   }

   /**
    * @@return webbroker3.common.message.WEB3GenResponse
    * @@roseuid 41DBC97901F5
    */
   public WEB3GenResponse createResponse()
   {
    return new WEB3AdminTPFindDepositAutoTransferStopResponse();
   }
}
@
