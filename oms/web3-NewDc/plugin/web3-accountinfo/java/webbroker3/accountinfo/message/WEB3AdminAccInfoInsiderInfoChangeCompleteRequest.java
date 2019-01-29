head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.05.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoInsiderInfoChangeCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l�������ҏ��ύX�������N�G�X�g(WEB3AdminAccInfoInsiderInfoChangeCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/12 ���C�g (���u) �V�K�쐬
*/

package webbroker3.accountinfo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�Ǘ��҂��q�l�������ҏ��ύX�������N�G�X�g)<BR>
 * �Ǘ��҂��q�l�������ҏ��ύX�������N�G�X�g<BR>
 * 
 * @@author ���C�g
 * @@version 1.0
 */
public class WEB3AdminAccInfoInsiderInfoChangeCompleteRequest extends WEB3GenRequest
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoInsiderInfoChangeCompleteRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_insiderInfoChangeComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082131L;

    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     */
    public String branchCode;

    /**
     * (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h<BR>
     */
    public String accountCode;

    /**
     * (�Ïؔԍ�)<BR>
     * �Ïؔԍ�<BR>
     */
    public String password;

    /**
     * (�����ҏ��)<BR>
     * �����ҏ��<BR>
     */
    public WEB3AccInfoInsiderInfo insiderInfo;

    /**
     * @@roseuid 418F3863029F
     */
    public WEB3AdminAccInfoInsiderInfoChangeCompleteRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccInfoInsiderInfoChangeCompleteResponse(this);
    }

    /**
     * ���N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * <BR>
     * �P�j�@@���X�R�[�h�̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00833<BR>
     * <BR>
     * �Q�j�@@�ڋq�R�[�h�̃`�F�b�N<BR>
     * �@@�Q�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00835<BR>
     * �@@�Q�|�Q�j�@@������6�łȂ��ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00836<BR>
     * �@@�Q�|�R�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01043<BR>
     * <BR>
     * �R�j�@@�����ҏ��̃`�F�b�N<BR>
     * �@@�R�|�P�j�@@�����ҏ�񂪖����͂̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01165<BR>
     * �@@�R�|�Q�j�@@�����ҏ��.validate()���R�[������B<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 415CEC0701D2
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@���X�R�[�h�̃`�F�b�N
        //�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B 
        if (this.branchCode == null || "".equals(this.branchCode))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00833, 
                this.getClass().getName() + STR_METHOD_NAME,
                "���X�R�[�h������");  
        }
        
        //�Q�j�@@�ڋq�R�[�h�̃`�F�b�N 
        //�Q�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B 
        if (this.accountCode == null || "".equals(this.accountCode))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00835, 
                this.getClass().getName() + STR_METHOD_NAME,
                "�ڋq�R�[�h������");  
        }        
        
        //�Q�|�Q�j�@@������6�łȂ��ꍇ�A��O���X���[����B 
        if (WEB3StringTypeUtility.getByteLength(this.accountCode) != 6)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00836, 
                this.getClass().getName() + STR_METHOD_NAME,
                "�ڋq�R�[�h������6�łȂ��ꍇ"); 
        }
        
        //�Q�|�R�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B
        if (!WEB3StringTypeUtility.isDigit(this.accountCode))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01043, 
                this.getClass().getName() + STR_METHOD_NAME,
                "�ڋq�R�[�h�����ȊO�̕������܂܂��"); 
        }
        

         
        /*
         * �R�j�@@�����ҏ��̃`�F�b�N<BR>
         * �@@�R�|�P�j�@@�����ҏ�񂪖����͂̏ꍇ�A��O���X���[����B<BR>
         *         class: WEB3BusinessLayerException<BR>
         *         tag:   BUSINESS_ERROR_01165<BR>
         */
         if(insiderInfo == null)
         {
             log.exiting(STR_METHOD_NAME);
             throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01165,
                this.getClass().getName() + STR_METHOD_NAME,
                "�����ҏ�񂪖�����");  
         }
         
         // �@@�R�|�Q�j�@@�����ҏ��.validate()���R�[������B<BR>         
        insiderInfo.validate();
        
        log.exiting(STR_METHOD_NAME); 
    }
}
@
