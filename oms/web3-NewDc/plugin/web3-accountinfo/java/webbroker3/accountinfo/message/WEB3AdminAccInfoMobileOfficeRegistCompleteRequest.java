head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.01.56.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMobileOfficeRegistCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�������N�G�X�g(WEB3AdminAccInfoMobileOfficeRegistCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 �J�N���V (���u) �V�K�쐬
*/

package webbroker3.accountinfo.message;

import webbroker3.accountinfo.define.WEB3JudgmentResultDivDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�������N�G�X�g)<BR>
 * �Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�������N�G�X�g<BR>
 * @@author �J�N���V
 * @@version 1.0 
 */
public class WEB3AdminAccInfoMobileOfficeRegistCompleteRequest extends WEB3GenRequest
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_mobileOfficeRegistComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082111L;
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoMobileOfficeRegistCompleteRequest.class);


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
     * (���茋�ʋ敪)<BR>
     * ���茋�ʋ敪<BR>
     * <BR>
     * 1�F�@@���F<BR>
     * 2�F�@@�s��<BR>
     */
    public String judgmentResultDiv;

    /**
     * (�Ïؔԍ�)<BR>
     * �Ïؔԍ�<BR>
     */
    public String password;

    /**
     * (�ύX����)<BR>
     * �ύX����<BR>
     */
    public WEB3AccInfoMobileOfficeInfo changedMobileOfficeInfo;

    /**
     * @@roseuid 418F385901D4
     */
    public WEB3AdminAccInfoMobileOfficeRegistCompleteRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccInfoMobileOfficeRegistCompleteResponse(this);
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
     * �R�j�@@���茋�ʋ敪�̃`�F�b�N<BR>
     * �@@�R�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01157<BR>
     * �@@�R�|�Q�j�@@�s���ȃR�[�h�l�̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01158<BR>
     * <BR>
     * �S�j�@@�ύX����̃`�F�b�N<BR>
     * �@@this.�ύX����.validate()���R�[������B<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 414178AF01AA
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
        
        //�Q�|�R�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B
        if(!WEB3StringTypeUtility.isDigit(this.accountCode))
        {
            
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01043,
                this.getClass().getName(),
                " �ڋq�R�[�h�ɐ����ȊO�̕���������̏ꍇ�G���[�ł���");
        }
        
        //�R�j�@@���茋�ʋ敪�̃`�F�b�N<BR>
        //�R�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        if(this.judgmentResultDiv == null || "".equals(this.judgmentResultDiv))
        {
            
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01157,
                this.getClass().getName(),
                " ���茋�ʋ敪����͂��܂���");
            
        }
        
        //�R�|�Q�j�@@�s���ȃR�[�h�l�̏ꍇ�A��O���X���[����B
        if(!WEB3JudgmentResultDivDef.CONSENT.equals(this.judgmentResultDiv)
            && !WEB3JudgmentResultDivDef.IMPOSSIBILITY.equals(this.judgmentResultDiv))
        {
            
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01158,
                this.getClass().getName(),
                " ���茋�ʋ敪�̃R�[�h�l���s���ł�");
        }        
        
        // �S�j�@@�ύX����̃`�F�b�N<BR>
        //this.�ύX����.validate()���R�[������B
        this.changedMobileOfficeInfo.validate();
        

        log.exiting(STR_METHOD_NAME);

    }
}
@