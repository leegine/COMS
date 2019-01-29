head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.00.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoPasswordChangeCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���q�l���Ïؔԍ��ύX���N�G�X�g(WEB3AccInfoPasswordChangeCompleteRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/12 ����� (���u) �V�K�쐬
*/

package webbroker3.accountinfo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (���q�l���Ïؔԍ��ύX���N�G�X�g)<BR>
 * ���q�l���Ïؔԍ��ύX���N�G�X�g<BR>
 * 
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3AccInfoPasswordChangeCompleteRequest extends WEB3GenRequest
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoPasswordChangeCompleteRequest.class);      
        
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "accInfo_passwordChangeComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082152L;

    /**
     * (���O�C����)<BR>
     * ���O�C����<BR>
     * �� ��ʂ�����͂���郆�[�U�R�[�h<BR>
     */
    public String loginName;

    /**
     * (���Ïؔԍ�)<BR>
     * ���Ïؔԍ�<BR>
     */
    public String oldPassword;

    /**
     * (�V�Ïؔԍ��P)<BR>
     * �V�Ïؔԍ��P<BR>
     */
    public String newPassword1;

    /**
     * (�V�Ïؔԍ��Q)<BR>
     * �V�Ïؔԍ��Q<BR>
     */
    public String newPassword2;

    /**
     * @@roseuid 418F39F603B9
     */
    public WEB3AccInfoPasswordChangeCompleteRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AccInfoPasswordChangeCompleteResponse(this);
    }

    /**
     * ���N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * <BR>
     * �P�j�@@���O�C�����̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01091<BR>
     * <BR>
     * �Q�j�@@���Ïؔԍ��̃`�F�b�N<BR>
     * �@@�Q�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_90210<BR>
     * <BR>
     * �R�j�@@�V�Ïؔԍ��P�C�V�Ïؔԍ��Q�̃`�F�b�N<BR>
     * �@@�R�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01093<BR>
     * �@@�R�|�Q�j�@@�V�Ïؔԍ��P�ƐV�Ïؔԍ��Q������łȂ��ꍇ�A<BR>
     * ��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_90211<BR>
     * @@throws WEB3BaseException
     * @@roseuid 416B8322003A
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@���O�C�����̃`�F�b�N
        //�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        
        if (this.loginName == null || "".equals(this.loginName))
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01091, 
                this.getClass().getName() + STR_METHOD_NAME,
                " ���O�C��������͂��܂���");  
        }
        
        //�Q�j�@@���Ïؔԍ��̃`�F�b�N 
        //�Q�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        if (this.oldPassword == null || "".equals(this.oldPassword))
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_90210, 
                this.getClass().getName() + STR_METHOD_NAME,
                " �p�X���[�h���ύX�ł��܂���ł����B�R�[���Z���^�[�ɂ��⍇��������");  
        }
        
        //�R�j�@@�V�Ïؔԍ��P�C�V�Ïؔԍ��Q�̃`�F�b�N
        //�R�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B 
        if (this.newPassword1 == null || "".equals(this.newPassword1)
            || this.newPassword2 == null || "".equals(this.newPassword2))
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01093, 
                this.getClass().getName() + STR_METHOD_NAME,
                " �V�Ïؔԍ��P�����͐V�Ïؔԍ��Q����͂��܂���");  
        }
        
        //�R�|�Q�j�@@�V�Ïؔԍ��P�ƐV�Ïؔԍ��Q������łȂ��ꍇ�A��O���X���[����B
        if (!this.newPassword1.equals(this.newPassword2))
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_90211, 
                this.getClass().getName() + STR_METHOD_NAME,
                " �V�p�X���[�h���m�F�p�̂��̂ƈ�v���Ă���܂���B���m�F�̏�A�ēx���͂��ĉ�����");
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
