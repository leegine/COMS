head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.14.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecAccRegVoucherStatUpdConfRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �Ǘ��ҁE�ڋq���o�^�`�[�X�e�[�^�X�X�V�m�F���N�G�X�g(WEB3AdminDirSecAccRegVoucherStatUpdConfRequest.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/06/12 �����q (���u) �V�K�쐬 �d�l�ύX ���f��No.098
*/

package webbroker3.dirsec.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�Ǘ��ҁE�ڋq���o�^�`�[�X�e�[�^�X�X�V�m�F���N�G�X�g)<BR>
 * �Ǘ��ҁE�ڋq���o�^�`�[�X�e�[�^�X�X�V�m�F���N�G�X�g�N���X�B<BR>
 * <BR>
 * @@author �����q
 * @@version 1.0
 */
public class WEB3AdminDirSecAccRegVoucherStatUpdConfRequest extends WEB3GenRequest
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3AdminDirSecAccRegVoucherStatUpdConfRequest.class);

    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_dirsec_acc_reg_voucher_stat_upd_conf";

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 200706121120L;

    /**
     * (�X�V_�`�[�쐬��)<BR>
     * �C����̓`�[�쐬��<BR>
     * <BR>
     * 3�F��t����<BR>
     * 4�F��t�G���[<BR>
     */
    public String updateVoucherMakeStatus;

    /**
     * (�X�V_�G���[���R�R�[�h)<BR>
     * �C����̃G���[���R�R�[�h<BR>
     */
    public String updateErrorReasonCode;

    /**
     * @@roseuid 466E0B6B0089
     */
    public WEB3AdminDirSecAccRegVoucherStatUpdConfRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j �`�[�쐬�󋵃`�F�b�N<BR>
     * �@@�P-�P�j this.�X�V_�`�[�쐬�� == null �̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�`�[�쐬�󋵂���͂��Ă��������B�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag: BUSINESS_ERROR_02825<BR>
     * <BR>
     * �Q�j �G���[���R�R�[�h�`�F�b�N<BR>
     * �@@�Q�|�P�j this.�X�V_�G���[���R�R�[�h == null �̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�G���[���R�R�[�h����͂��Ă��������B�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag: BUSINESS_ERROR_02826<BR>
     * <BR>
     * �@@�Q-�Q�j this.�X�V_�G���[���R�R�[�h�̒��� > 4 OR<BR>
     * �@@�@@�@@�@@�@@this.�X�V_�G���[���R�R�[�h�����p�p���ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�u�G���[���R�R�[�h���s���ł��B�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag: BUSINESS_ERROR_02827<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 4656454301E0
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //�����N�G�X�g�f�[�^�̐������`�F�b�N���s��
        //�i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j
        //�P�j �`�[�쐬�󋵃`�F�b�N
        //  �P-�P�j this.�X�V_�`�[�쐬�� == null �̏ꍇ
        //          �u�`�[�쐬�󋵂���͂��Ă��������B�v�̗�O���X���[����
        if (this.updateVoucherMakeStatus == null)
        {
            log.debug("�`�[�쐬�󋵂���͂��Ă��������B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02825,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�`�[�쐬�󋵂���͂��Ă��������B");
        }
        //�Q�j �G���[���R�R�[�h�`�F�b�N
        //  �Q�|�P�j this.�X�V_�G���[���R�R�[�h == null �̏ꍇ
        //           �u�G���[���R�R�[�h����͂��Ă��������B�v�̗�O���X���[����
        if (this.updateErrorReasonCode == null)
        {
            log.debug("�G���[���R�R�[�h����͂��Ă��������B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02826,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�G���[���R�R�[�h����͂��Ă��������B");
        }
        //  �Q-�Q�j this.�X�V_�G���[���R�R�[�h�̒��� > 4 OR
        //               this.�X�V_�G���[���R�R�[�h�����p�p���ȊO�̏ꍇ
        //          �u�G���[���R�R�[�h���s���ł��B�v�̗�O���X���[����
        if (this.updateErrorReasonCode.length() > 4
            || !WEB3StringTypeUtility.isLetterOrDigit(this.updateErrorReasonCode))
        {
            log.debug("�G���[���R�R�[�h���s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02827,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�G���[���R�R�[�h���s���ł��B");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * <BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminDirSecAccRegVoucherStatUpdConfResponse(this);
    }
}
@
