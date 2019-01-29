head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.51.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformAccSwElecDeliChangeConfRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�����ؑցE�d�q��t�\���ύX�m�F���N�G�X�g�iWEB3AdminInformAccSwElecDeliChangeConfRequest.java�j
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/19 �g�E�N�| (���u) �V�K�쐬�@@�d�l�ύX���f��110
*/

package webbroker3.inform.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�Ǘ��ҁE�����ؑցE�d�q��t�\���ύX�m�F���N�G�X�g)<BR>
 * �Ǘ��ҁE�����ؑցE�d�q��t�\���ύX�m�F���N�G�X�g<BR>
 * <BR>
 * @@author �g�E�N�|
 * @@version 1.0
 */
public class WEB3AdminInformAccSwElecDeliChangeConfRequest extends WEB3GenRequest
{
    /**
     * (���O�o�̓��[�e�B���e�B)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminInformAccSwElecDeliChangeConfRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_inform_acc_sw_elec_deli_change_conf";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200709191125L;

    /**
     * (�A�����)<BR>
     * �A�����<BR>
     */
    public String informType;

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
     * (���ʃR�[�h)<BR>
     * ���ʃR�[�h<BR>
     */
    public String requestNumber;

    /**
     * (�ύX����)<BR>
     * �ύX����<BR>
     */
    public WEB3AdminInformAccSwitchElecDeliApplyInfo changedInfo;

    /**
     * @@roseuid 46F0900C0305
     */
    public WEB3AdminInformAccSwElecDeliChangeConfRequest()
    {

    }

    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B <BR>
     * <BR>
     * �P�j�@@���X�R�[�h�̃`�F�b�N <BR>
     * �@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B  <BR>
     * �@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag  : BUSINESS_ERROR_00833<BR>
     * �@@�P�|�Q�j�@@������3�łȂ��ꍇ�A��O���X���[����B <BR>
     * �@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag  : BUSINESS_ERROR_00834<BR>
     * �@@�P�|�R�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B <BR>
     * �@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag  : BUSINESS_ERROR_01729<BR>
     * <BR>
     * �Q�j�@@�ڋq�R�[�h�̃`�F�b�N <BR>
     * �@@�Q�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B <BR>
     * �@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag  : BUSINESS_ERROR_00835<BR>
     * �@@�Q�|�Q�j�@@������6�łȂ��ꍇ�A��O���X���[����B <BR>
     * �@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag  : BUSINESS_ERROR_00836<BR>
     * �@@�Q�|�R�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B <BR>
     * �@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag  : BUSINESS_ERROR_01043<BR>
     * <BR>
     * �R�j�@@�A����ʂ̃`�F�b�N<BR>
     * �@@�R�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B  <BR>
     * �@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag  : BUSINESS_ERROR_01817<BR>
     * <BR>
     * �S�j�@@���ʃR�[�h�̃`�F�b�N<BR>
     * �@@�S�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B <BR>
     * �@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag  : BUSINESS_ERROR_00829<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 46DE40540066
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@���X�R�[�h�̃`�F�b�N
        //�@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.branchCode))
        {
            log.debug("���X�R�[�h�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���X�R�[�h�����w��ł��B");
        }

        //�@@�P�|�Q�j�@@������3�łȂ��ꍇ�A��O���X���[����B
        if (this.branchCode.length() != 3)
        {
            log.debug("���X�R�[�h�̃T�C�Y���s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00834,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���X�R�[�h�̃T�C�Y���s���ł��B");
        }

        //�@@�P�|�R�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B
        if (!WEB3StringTypeUtility.isDigit(this.branchCode))
        {
            log.debug("���X�R�[�h�����l�ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01729,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���X�R�[�h�����l�ȊO�̒l�ł��B");
        }

        //�Q�j�@@�ڋq�R�[�h�̃`�F�b�N
        //�@@�Q�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.accountCode))
        {
            log.debug("�ڋq�R�[�h�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00835,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�ڋq�R�[�h�����w��ł��B");
        }

        //�@@�Q�|�Q�j�@@������6�łȂ��ꍇ�A��O���X���[����B
        if (this.accountCode.length() != 6)
        {
            log.debug("�ڋq�R�[�h�̃T�C�Y���s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00836,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�ڋq�R�[�h�̃T�C�Y���s���ł��B");
        }

        //�@@�Q�|�R�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B
        if (!WEB3StringTypeUtility.isDigit(this.accountCode))
        {
            log.debug("�ڋq�R�[�h�̒l�������ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01043,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�ڋq�R�[�h�̒l�������ȊO�̒l�ł��B");
        }

        //�R�j�@@�A����ʂ̃`�F�b�N
        //�@@�R�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.informType))
        {
            log.debug("�A����ʂ����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01817,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�A����ʂ����w��ł��B");
        }

        //�S�j�@@���ʃR�[�h�̃`�F�b�N
        //�@@�S�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.requestNumber))
        {
            log.debug("���ʃR�[�h�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00829,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���ʃR�[�h�����w��ł��B");
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
        return new WEB3AdminInformAccSwElecDeliChangeConfResponse(this);
    }
}
@
