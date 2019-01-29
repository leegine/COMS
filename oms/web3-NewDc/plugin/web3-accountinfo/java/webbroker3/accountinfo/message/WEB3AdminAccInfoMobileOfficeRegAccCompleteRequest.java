head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.01.57.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMobileOfficeRegAccCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�\���ꊇ���芮�����N�G�X�g(WEB3AdminAccInfoMobileOfficeRegAccCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/12/14 ���� (���u) �V�K�쐬 ���f��No.153
*/
package webbroker3.accountinfo.message;

import webbroker3.accountinfo.define.WEB3JudgmentResultDivDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�\���ꊇ���芮�����N�G�X�g)<BR>
 * �Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�\���ꊇ���芮�����N�G�X�g�N���X
 *
 * @@author ����
 * @@version 1.0
 */
public class WEB3AdminAccInfoMobileOfficeRegAccCompleteRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_mobileOfficeRegAccComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200612141400L;

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoMobileOfficeRegAccCompleteRequest.class);

    /**
     * (�Ïؔԍ�)<BR>
     * �Ïؔԍ�<BR>
     */
    public String password;

    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h�̔z��<BR>
     */
    public String[] branchCode;

    /**
     * (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h�̔z��<BR>
     */
    public String[] accountCode;

    /**
     * (���茋�ʋ敪)<BR>
     * ���茋�ʋ敪<BR>
     * <BR>
     * 1�F�@@���F<BR>
     * 2�F�@@�s��<BR>
     */
    public String judgmentResultDiv;

    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B <BR>
     * <BR>
     * �P�j�@@���X�R�[�h�̃`�F�b�N <BR>
     * �@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B  <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00833<BR>
     * <BR>
     * �Q�j�@@�ڋq�R�[�h�̃`�F�b�N <BR>
     * �@@�Q�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B  <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00835<BR>
     * <BR>
     * �R�j�@@���茋�ʋ敪�̃`�F�b�N <BR>
     * �@@�R�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B  <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01157<BR>
     * <BR>
     * �@@�R�|�Q�j�@@�s���ȃR�[�h�l�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01158<BR>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@���X�R�[�h�̃`�F�b�N
        //�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        if (this.branchCode == null || this.branchCode.length == 0)
        {
            log.debug("���X�R�[�h�����͂̏ꍇ�A��O���X���[");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                getClass().getName() + STR_METHOD_NAME,
                "���X�R�[�h�����w��ł��B");
        }

        //�Q�j�@@�ڋq�R�[�h�̃`�F�b�N
        //�Q�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        if (this.accountCode == null || this.accountCode.length == 0)
        {
            log.debug("�ڋq�R�[�h�����͂̏ꍇ�A��O���X���[");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00835,
                getClass().getName() + STR_METHOD_NAME,
                "�ڋq�R�[�h�����w��ł��B");
        }

        //�R�j�@@���茋�ʋ敪�̃`�F�b�N
        //�R�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        if (this.judgmentResultDiv == null)
        {
            log.debug("���茋�ʋ敪�����͂̏ꍇ�A��O���X���[");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01157,
                getClass().getName() + STR_METHOD_NAME,
                "���茋�ʋ敪�����w��ł��B");
        }

        //�R�|�Q�j�@@�s���ȃR�[�h�l�̏ꍇ�A��O���X���[����B
        else if (!WEB3JudgmentResultDivDef.CONSENT.equals(judgmentResultDiv)
            && !WEB3JudgmentResultDivDef.IMPOSSIBILITY.equals(judgmentResultDiv))
        {
            log.debug("���茋�ʋ敪���s���ȃR�[�h�l�̏ꍇ�A��O���X���[");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01158,
                getClass().getName() + STR_METHOD_NAME,
                "���茋�ʋ敪�����݂��Ȃ��R�[�h�l�ł��B");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccInfoMobileOfficeRegAccCompleteResponse(this);
    }
}
@
