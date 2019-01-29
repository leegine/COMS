head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.43.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPFindCalcControlRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �]�͐���@@�\�������N�G�X�g�N���X(WEB3AdminTPFindCalcControlRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/01/06 �x�� �a��(FLJ) �V�K�쐬
Revision History : 2007/07/26 ��іQ (���u) ���f���FNo.004
*/
package webbroker3.tradingpoweradmin.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * �]�͐���@@�\�������N�G�X�g�N���X
 */
public class WEB3AdminTPFindCalcControlRequest extends WEB3GenRequest
{

    /**
     * PTYPE
     */
     public static final String PTYPE = "tradingpoweradmin_find_calccontrol";

   /**
    * ���X�R�[�h
    */
   public String[] branchCodes;

   /**
    * �ڋq�R�[�h
    */
   public String accountCode;

   /**
    * �����~�敪
    */
   public String tradingStop;

   /**
    * �M�p�V�K���]�͋敪
    */
   public String marginOpenPositionStop;

   /**
    * �敨OP�V�K���]�͋敪
    */
   public String ifoOpenPositionStop;

   /**
    * �o���]�͋敪
    */
   public String paymentStop;

   /**
    * ���̑����i���t�敪
    */
   public String otherTradingStop;

   /**
    * (�Ǐؖ������敪)<BR>
    * 0:�Ǐؖ������Ȃ� 1:�Ǐؖ���������<BR>
    */
   public String additionalDepositStop;

   /**
    * @@roseuid 41DBC92701F5
    */
   public WEB3AdminTPFindCalcControlRequest()
   {

   }

   /**
    * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B
    * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j
    *
    * [���X�R�[�h[] != null�̏ꍇ]
    * ���X�R�[�h null�`�F�b�N�A����3��

    * [�ڋq�R�[�h != null�̏ꍇ]
    * ���X�R�[�h null�`�F�b�N�A����3��
    * �ڋq�R�[�h ����6��
    *
    * [[�����~�敪 != null�̏ꍇ]
    * �����~�敪 = �h�]�͕s�h
    *
    *[�M�p�V�K���]�͋敪 != null�̏ꍇ]
    *�M�p�V�K���]�͋敪 = �h�]�͕s�h
    *
    *[�敨OP�V�K���]�͋敪 != null�̏ꍇ]
    *�敨OP�V�K���]�͋敪 = �h�]�͕s�h
    *
    *[�o���]�͋敪 != null�̏ꍇ]
    *�o���]�͋敪 = �h�]�͕s�h
    *
    *[���̑����i���t�]�͋敪 != null�̏ꍇ]
    *���̑����i���t�]�͋敪 = �h�]�͕s�h
    *
    * @@roseuid 41B9348803B3
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
    * @@roseuid 41DBC9270253
    */
   public WEB3GenResponse createResponse()
   {
    return new WEB3AdminTPFindCalcControlResponse();
   }


}
@
