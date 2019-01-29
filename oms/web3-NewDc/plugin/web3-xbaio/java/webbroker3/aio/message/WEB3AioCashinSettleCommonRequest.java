head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.53.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinSettleCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �}���`�o���N���ϋ��ʃ��N�G�X�g(WEB3AioCashinSettleCommonRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/11 ���E (���u) �V�K�쐬              
                   2004/10/22 ���� (���u) ���r���[         
*/
package webbroker3.aio.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�}���`�o���N���ϋ��ʃ��N�G�X�g)<BR>
 * �}���`�o���N���ϋ��ʃ��N�G�X�g�N���X<BR>
 * 
 * @@author ���E(���u)
 * @@version 1.0
 */
public class WEB3AioCashinSettleCommonRequest extends WEB3GenRequest 
{
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200409291947L; 
   
    /**
     * (AP�w�Z�b�V����ID)<BR>
     * ����PF���烊�_�C���N�g���ꂽAP�w�Z�b�V����ID<BR>
     * �iWEB3�ɂēƎ��ɐݒ肵�Ă��鍀�ځj<BR>
     */
    public String apSessionId;
    
    /**
     * (�،���ЃR�[�h)<BR>
     * ����PF���烊�_�C���N�g���ꂽ�،���ЃR�[�h<BR>
     * �iWEB3�ɂēƎ��ɐݒ肵�Ă��鍀�ځj<BR>
     */
    public String institutionCode;
    
    /**
     * (���X�R�[�h)<BR>
     * ����PF���烊�_�C���N�g���ꂽ���X�R�[�h<BR>
     * �iWEB3�ɂēƎ��ɐݒ肵�Ă��鍀�ځj<BR>
     */
    public String branchCode;
    
    /**
     * (�ڋq�R�[�h)<BR>
     * ����PF���烊�_�C���N�g���ꂽ�ڋq�R�[�h<BR>
     * �iWEB3�ɂēƎ��ɐݒ肵�Ă��鍀�ځj<BR>
     * <BR>
     */
    public String accountCode;
    
    /**
     * (���ʃR�[�h)<BR>
     * ����PF���烊�_�C���N�g���ꂽ���ʃR�[�h<BR>
     * �iWEB3�ɂēƎ��ɐݒ肵�Ă��鍀�ځj<BR>
     */
    public String orderRequestNumber;
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashinSettleCommonRequest.class);
    
    /**
     * �f�t�H���g�R���X�g���N
     * @@roseuid 4158EB33022F
     */
    public WEB3AioCashinSettleCommonRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4158EB330258
     */
    public WEB3GenResponse createResponse() 
    {
        return null;
    }
    
    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �Q�jAP�w�Z�b�V����ID�`�F�b�N<BR>
     *   ���N�G�X�g�f�[�^.AP�w�Z�b�V����ID = null  �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00769<BR>
     * <BR>
     * <BR>
     * �R�j�،���ЃR�[�h�`�F�b�N<BR>
     *   ���N�G�X�g�f�[�^.�،���ЃR�[�h = null  �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00827<BR>
     * <BR>
     * <BR>
     * �S�j���X�R�[�h�`�F�b�N<BR>
     *   ���N�G�X�g�f�[�^.���X�R�[�h = null  �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00779<BR>
     * <BR>
     * <BR>
     * �T�j�ڋq�R�[�h�`�F�b�N<BR>
     *   ���N�G�X�g�f�[�^.�ڋq�R�[�h = null  �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00835<BR>
     * <BR>
     * <BR>
     * �U�j���ʃR�[�h�`�F�b�N<BR>
     *   ���N�G�X�g�f�[�^.���ʃR�[�h = null  �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00829<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 40EA3BE101CE
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //�Q�jAP�w�Z�b�V����ID�`�F�b�N 
        //���N�G�X�g�f�[�^.AP�w�Z�b�V����ID = null 
        if(WEB3StringTypeUtility.isEmpty(this.apSessionId))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00769,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���N�G�X�g�f�[�^.AP�w�Z�b�V����ID = null");
        }
        
        //�R�j�،���ЃR�[�h�`�F�b�N 
        //���N�G�X�g�f�[�^.�،���ЃR�[�h = null 
        if(WEB3StringTypeUtility.isEmpty(this.institutionCode))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00827,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���N�G�X�g�f�[�^.�،���ЃR�[�h = null");
        }
        
        //�S�j���X�R�[�h�`�F�b�N 
        //���N�G�X�g�f�[�^.���X�R�[�h = null 
        if(WEB3StringTypeUtility.isEmpty(this.branchCode))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00779,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���N�G�X�g�f�[�^.���X�R�[�h = null");
        }
        //�T�j�ڋq�R�[�h�`�F�b�N 
        //���N�G�X�g�f�[�^.�ڋq�R�[�h = null 
        if(WEB3StringTypeUtility.isEmpty(this.accountCode))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00835,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���N�G�X�g�f�[�^.�ڋq�R�[�h = null");
        }
        //�U�j���ʃR�[�h�`�F�b�N 
        //���N�G�X�g�f�[�^.���ʃR�[�h = null 
        if(WEB3StringTypeUtility.isEmpty(this.orderRequestNumber))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00829,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���N�G�X�g�f�[�^.���ʃR�[�h = null");
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
