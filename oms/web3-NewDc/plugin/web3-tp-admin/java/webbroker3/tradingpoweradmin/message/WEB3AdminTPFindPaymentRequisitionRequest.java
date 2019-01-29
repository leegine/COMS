head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.41.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPFindPaymentRequisitionRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������������N�G�X�g�N���X(WEB3AdminTPFindPaymentRequisitionRequest.java)
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
 * ���������������N�G�X�g�N���X
 */
public class WEB3AdminTPFindPaymentRequisitionRequest extends WEB3GenRequest
{
    /**
     * PTYPE
     */
     public static final String PTYPE = "tradingpoweradmin_find_paymentrequisition";


     /**
      * ���X�R�[�h
      */
     public String[] branchCodes;

   /**
    * �ڋq�R�[�h
    */
   public String accountCode;

   /**
    * �����J�n��
    */
   public Date startDate;

   /**
    * �����I����
    */
   public Date endDate;

   /**
    * ���������敪
    */
   public String[] paymentRequisitionDivisions;

   /**
    * ���ы敪
    */
   public String[] requisitionStatuses;

   /**
    * �v�Z���敪
    */
   public String[] calclationSources;

   /**
    * @@roseuid 41DE22F90100
    */
   public WEB3AdminTPFindPaymentRequisitionRequest()
   {

   }

   /**
    * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B
�@@�@@* �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j
    *�P�j���X�R�[�h�`�F�b�N
    * this.���X�R�[�h���ȉ��̏����ɊY������ꍇ�A
    * �u���X�R�[�h�G���[�v�̗�O���X���[����B
    * �Ethis.���X�R�[�h == null
    * �Ethis.���X�R�[�h.length != 3
    * �Ethis.���X�R�[�h != ���l
    *
    * �Q�j�ڋq�w��̏ꍇ(�ڋq�R�[�h  != null�̂Ƃ�)
    * this.�ڋq�R�[�h���ȉ��̏����ɊY������ꍇ�A
    * �u�ڋq�R�[�h�G���[�v�̗�O���X���[����B
    * �Ethis.�ڋq�R�[�h.length != 6
    * �Ethis.�ڋq�R�[�h != ���l
    *
    * �R�j�������Ԏw��̏ꍇ
    * (�����J�n�� != null && �����I���� != null�̂Ƃ�)
    * �ȉ��ɊY�������ꍇ�u�������Ԃ̎w�肪�s���v�̗�O���X���[����B
    * �E�����J�n�� > �����I����
    *
    * @@roseuid 41BD68E101CC
    */
   public void validate() throws WEB3BusinessLayerException
   {
       final String METHOD_NAME = "validate()";

 		//�P�j���X�R�[�h�`�F�b�N
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

        //�Q�j�ڋq�w��̏ꍇ
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

        //�R�j�������Ԏw��̏ꍇ
        if(this.startDate != null && this.endDate != null)
        {
            if(startDate.after(endDate))
            {
      			throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01899, METHOD_NAME);
            }
        }


   }

   /**
    * @@return webbroker3.common.message.WEB3GenResponse
    * @@roseuid 41DE22F9019C
    */
   public WEB3GenResponse createResponse()
   {
       return new WEB3AdminTPFindPaymentRequisitionResponse();
   }


}
@
