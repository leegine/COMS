head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.09.43;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMobileOfficeRegistConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�m�F���N�G�X�g(WEB3AdminAccInfoMobileOfficeRegistConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 �J�N���V (���u) �V�K�쐬
                   2006/10/30 ����� (���u) �d�l�ύX���f��No.139
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
 * (�Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�m�F���N�G�X�g)<BR>
 * �Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�m�F���N�G�X�g<BR>
 * @@author �J�N���V
 * @@version 1.0 
 */
public class WEB3AdminAccInfoMobileOfficeRegistConfirmRequest extends WEB3GenRequest
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_mobileOfficeRegistConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082110L;
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoMobileOfficeRegistConfirmRequest.class);


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
     * (�ύX����)<BR>
     * �ύX����<BR>
     */
    public WEB3AccInfoMobileOfficeInfo changedMobileOfficeInfo;
    
    /**
     * (�ύX�O���)<BR>
     * �ύX�O���<BR>
     */
    public WEB3AccInfoMobileOfficeInfo preMobileOfficeInfo;

    /**
     * @@roseuid 418F385900BB
     */
    public WEB3AdminAccInfoMobileOfficeRegistConfirmRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccInfoMobileOfficeRegistConfirmResponse(this);
    }

    /**
     * (validate)<BR>
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
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
     * �R�j�@@�ύX����̃`�F�b�N<BR>
     * �@@�R�|�P�j�@@this.�ύX����.validate()���R�[������B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 414178CA0321
     */
    public void validate() throws WEB3BaseException
    {
        
        final String STR_METHOD_NAME = " validate() ";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@���X�R�[�h�̃`�F�b�N<BR>
        //�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        if(this.branchCode == null || "".equals(this.branchCode))
        {
            
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                this.getClass().getName(),
                " ���X�R�[�h==null�ł���");            
        }
        
        //�Q�j�@@�ڋq�R�[�h�̃`�F�b�N<BR>
        //�Q�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        if(this.accountCode == null || "".equals(this.accountCode))
        {
            
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00835,
                this.getClass().getName(),
                " �ڋq�R�[�h��null�ł���"); 
        }
        
        //�Q�|�Q�j�@@������6�łȂ��ꍇ�A��O���X���[����B
        if(WEB3StringTypeUtility.getByteLength(this.accountCode) != 6)
        {
            
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00836,
                this.getClass().getName(),
                " ������6�łȂ��ꍇ");
        }
        
        //�Q�|�R�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����
        if(!WEB3StringTypeUtility.isDigit(this.accountCode))
        {
            
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01043,
                this.getClass().getName(),
                " �ڋq�R�[�h�ɐ����ȊO�̕���������̏ꍇ�G���[�ł���");
        }
        
        //�R�j�@@�ύX����̃`�F�b�N<BR>
        //�R�|�P�j�@@this.�ύX����.validate()���R�[������
        this.changedMobileOfficeInfo.validate();
        
        log.exiting(STR_METHOD_NAME);

    }
}
@
