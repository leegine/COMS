head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.19.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioOtherCountReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���̑������Ɖ�N�G�X�g(WEB3AdminAioOtherCountReferenceRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/11 ��O��(���u) �V�K�쐬
*/

package webbroker3.aio.message;

import webbroker3.aio.define.WEB3AioOtherOrderProductDivDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (���̑������Ɖ�N�G�X�g)<BR>
 * ���̑������Ɖ�N�G�X�g�N���X
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */
public class WEB3AdminAioOtherCountReferenceRequest extends WEB3GenRequest 
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioOtherCountReferenceRequest.class);  
    
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "admin_aio_other_count_reference";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200503171454L;
    
    /**
     * (���X�R�[�h)<BR>
     * ��ʂɂē��͂��ꂽ���X�R�[�h
     */
    public String[] branchCodeList;
    
    /**
     * (���i�敪)<BR>
     * ��ʂɂđI�����ꂽ���i�敪 <BR>
     * <BR>
     * 1:�I�����C�������@@ <BR>
     * 2:�ב֕ۏ؋��@@ <BR>
     * 3:�O�������i�O���A�g�j<BR>
     */
    public String commodityDiv;
    
    /**
     * (���ϋ@@��ID)<BR>
     * ��ʂɂđI�����ꂽ���ϋ@@�ւ�ID <BR>
     * ���i�敪�ɋ��Z�@@�֘A�g���I�����ꂽ�ꍇ�A�K�{�B <BR>
     */
    public String paySchemeId;
    
    /** 
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B <BR>
     * <BR>
     * �P�j���X�R�[�h�`�F�b�N <BR>
     * <BR>
     * ���N�G�X�g�f�[�^.���X�R�[�h = null or <BR>
     * ���N�G�X�g�f�[�^.���X�R�[�h.length() != 3 <BR>
     * <BR>
     * �̏ꍇ�A��O���X���[����B <BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00779<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00834<BR>
     * <BR>
     * �Q�j���i�敪�`�F�b�N <BR>
     * <BR>
     * ���N�G�X�g�f�[�^.���i�敪 = null <BR>
     * <BR>
     * �̏ꍇ�A��O���X���[����B <BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02182<BR>
     * <BR>
     * �R�j���ϋ@@��ID�`�F�b�N <BR>
     * <BR>
     * ���N�G�X�g�f�[�^.���i�敪 = �I�����C�������̏ꍇ�� <BR>
     * ���N�G�X�g�f�[�^.���ϋ@@��ID = null or <BR>
     * ���N�G�X�g�f�[�^.���ϋ@@��ID.length() != 11 or <BR>
     * ���N�G�X�g�f�[�^.���ϋ@@��ID.startsWith("ComOndebi") = false <BR>
     * <BR>
     * �̏ꍇ�A��O���X���[����B <BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00767<BR>
     * <BR>
     */
   public void validate() throws WEB3BaseException
   {
       final String STR_METHOD_NAME = "validate()";
       log.entering(STR_METHOD_NAME);
       
       //�P�j���X�R�[�h�`�F�b�N 
       //���N�G�X�g�f�[�^.���X�R�[�h = null or 
       //���N�G�X�g�f�[�^.���X�R�[�h.length() != 3 
       //�̏ꍇ�A��O���X���[����B 
       if(this.branchCodeList == null || 
          this.branchCodeList.length ==0)
       {
    		log.exiting(STR_METHOD_NAME);
	    	throw new WEB3BusinessLayerException(
		    	WEB3ErrorCatalog.BUSINESS_ERROR_00833,
			    this.getClass().getName() + "." + STR_METHOD_NAME,
			    "���X�R�[�h�����w��ł��B");
       }

       for (int i = 0; i < this.branchCodeList.length; i++)
       {
           if (this.branchCodeList[i].length() != 3)
           {                
               log.exiting(STR_METHOD_NAME);                
               throw new WEB3BusinessLayerException(
                   WEB3ErrorCatalog.BUSINESS_ERROR_00834 ,
                   this.getClass().getName() + "." + STR_METHOD_NAME,
                   "���X�R�[�h�̃T�C�Y���s���ł��B");
           }
       }

       //�Q�j���i�敪�`�F�b�N 
       //���N�G�X�g�f�[�^.���i�敪 = null 
       //�̏ꍇ�A��O���X���[����B
       if (WEB3StringTypeUtility.isEmpty(this.commodityDiv))
       {
           log.exiting(STR_METHOD_NAME);
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_02182,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               "���N�G�X�g�f�[�^.���i�敪 = null");
       }
       
       //�R�j���ϋ@@��ID�`�F�b�N 
       //���N�G�X�g�f�[�^.���i�敪 = �I�����C�������̏ꍇ�� 
       //���N�G�X�g�f�[�^.���ϋ@@��ID = null or 
       //���N�G�X�g�f�[�^.���ϋ@@��ID.length() != 11 or 
       //���N�G�X�g�f�[�^.���ϋ@@��ID.startsWith("ComOndebi") = false 
       //�̏ꍇ�A��O���X���[����B
       if (WEB3AioOtherOrderProductDivDef.ONLINE_CASHIN.equals(
               this.commodityDiv))
       {
           if (WEB3StringTypeUtility.isEmpty(this.paySchemeId) || 
               (this.paySchemeId.length() != 11 && !this.paySchemeId.equals("0")) ||
               (!this.paySchemeId.startsWith("ComOndebi") && !this.paySchemeId.equals("0")))
           {
               log.exiting(STR_METHOD_NAME);
               throw new WEB3BusinessLayerException(
                   WEB3ErrorCatalog.BUSINESS_ERROR_00767,
                   this.getClass().getName() + "." + STR_METHOD_NAME,
                   "���N�G�X�g�f�[�^.���ϋ@@��ID = null or " +
                   "���N�G�X�g�f�[�^.���ϋ@@��ID.length() != 11 or " +
                   "���N�G�X�g�f�[�^.���ϋ@@��ID.startsWith(ComOndebi) = false");
           }
       }
   }
    
    /**
     * @@roseuid 423552AB0000
     */
    public WEB3AdminAioOtherCountReferenceRequest() 
    {
     
    }
    
    /**
     * �icreateResponse�̎����j<BR>
     * <BR>
     * ���̑������Ɖ�X�|���X�I�u�W�F�N�g��ԋp����B
     * @@return WEB3GenResponse
     * @@roseuid 41E7904C00FA
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminAioOtherCountReferenceResponse(this);
    }
}
@
