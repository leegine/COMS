head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.39.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPReCalcRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �]�͍Čv�Z���sRequest�N���X(WEB3AdminTPReCalcRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2005/01/31 kikuchi(SCS) �V�K�쐬
*/
package webbroker3.tradingpoweradmin.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 *  �]�͍Čv�Z���N�G�X�g�N���X
 */
public class WEB3AdminTPReCalcRequest extends WEB3GenRequest 
{
   
    /**
     * PTYPE
     */
     public static final String PTYPE = "tradingpoweradmin_recalc";

   /**
    * ���X�R�[�h
    */
   public String branchCodes;

   /**
    * �Ώیڋq�敪
    */
   public String accountProperty;

   /**
    * �ڋq�R�[�h(��)
    */
   public String accountCodeSt;

   /**
    * �ڋq�R�[�h(��)
    */
   public String accountCodeEd;
   
   /**
    */
   public WEB3AdminTPReCalcRequest() 
   {
    
   }
   
   /**
    * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B 
    * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j 
    * 
    * [���X�R�[�h[] != null�̏ꍇ]
    * ���X�R�[�h null�`�F�b�N�A���p�����`�F�b�N�A����3��
    *
    * �Ώیڋq�敪��4:�ڋq�w��̎��݈̂ȉ��̃`�F�b�N���s���B
    * [�ڋq�R�[�h(��) != null or �ڋq�R�[�h(��) != null�̏ꍇ]
    * �ڋq�R�[�h(��) null�`�F�b�N�A���p�����`�F�b�N�A����7��
    * �ڋq�R�[�h(��) null�`�F�b�N�A���p�����`�F�b�N�A����7��
    * 
    */
   public void validate() throws WEB3BusinessLayerException
   {
        final String METHOD_NAME = "validate()";

        //���X�R�[�h�`�F�b�N
        if(branchCodes == null)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00833, METHOD_NAME);                
        }
  		else if(branchCodes.length() != 3)
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00834, METHOD_NAME);
  	  	try
		{
    		Integer.parseInt(branchCodes);   			
		}
  		catch(NumberFormatException ne)
		{
  			throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01729, METHOD_NAME, ne.getMessage());  	  			
		}
  		
        //�Ώیڋq�敪���ڋq�w��̂Ƃ��̂݃`�F�b�N
        if (accountProperty.equals("4"))
        {
            //�ڋq�R�[�h(��)
            if(accountCodeSt == null)
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00835, METHOD_NAME);
            }
	        if(accountCodeSt.length() != 7)
	            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00836, METHOD_NAME);
            try
            {
                Integer.parseInt(accountCodeSt);   			
            }
            catch(NumberFormatException ne)
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01330, METHOD_NAME, ne.getMessage());	  			
            }

            //�ڋq�R�[�h(��)
            if(accountCodeEd == null)
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00835, METHOD_NAME);
            }            
            if(accountCodeEd.length() != 7)
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00836, METHOD_NAME);               
            try
            {
                Integer.parseInt(accountCodeEd);           
            }
            catch(NumberFormatException ne)
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01331, METHOD_NAME, ne.getMessage());              
            }

            //�ڋq�R�[�h(��)��(��)�`�F�b�N
            if(accountCodeSt.compareTo(accountCodeEd) > 0)
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00795, METHOD_NAME);
            }
            
	    }
        else
        {
            //�ڋq�R�[�h(��)
            if(accountCodeSt != null)
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01936, METHOD_NAME);
            }

            //�ڋq�R�[�h(��)
            if( accountCodeEd != null)
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01936, METHOD_NAME);
            }

        }
   }
   
   /**
    * @@return webbroker3.common.message.WEB3GenResponse
    */
   public WEB3GenResponse createResponse() 
   {
    return new WEB3AdminTPReCalcResponse();
   }
   
   
}
@
