head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.47.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformAccSwElecDeliApplyCmpRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�����ؑցE�d�q��t�\���������N�G�X�g(WEB3AdminInformAccSwElecDeliApplyCmpRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/18 ���^�] (���u) �V�K�쐬 �d�l�ύX���f��097
Revision History : 2007/08/30 �g�E�N�| (���u) �d�l�ύX���f��107
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
 * (�Ǘ��ҁE�����ؑցE�d�q��t�\���������N�G�X�g)<BR>
 * �Ǘ��ҁE�����ؑցE�d�q��t�\���������N�G�X�g�N���X<BR>
 *
 * @@author ���^�]
 * @@version 1.0
 */
public class WEB3AdminInformAccSwElecDeliApplyCmpRequest extends WEB3GenRequest
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminInformAccSwElecDeliApplyCmpRequest.class);

    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_inform_acc_sw_elec_deli_apply_cmp";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200707182208L;

    /**
     * (�A�����)<BR>
     */
    public String informType;

    /**
     * (���X�R�[�h)<BR>
     */
    public String branchCode;

    /**
     * (�ڋq�R�[�h)<BR>
     */
    public String accountCode;

    /**
     * (���ʃR�[�h)<BR>
     */
    public String requestNumber;

    /**
     * (�Ïؔԍ�)<BR>
     */
    public String password;

    /**
     * (�ύX����)<BR>
     * �����ؑցE�d�q��t�\�����<BR>
     */
    public WEB3AdminInformAccSwitchElecDeliApplyInfo changedInfo;

    public WEB3AdminInformAccSwElecDeliApplyCmpRequest()
    {

    }

    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@���X�R�[�h�̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00833<BR>
     * �@@�P�|�Q�j�@@������3�łȂ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00834<BR>
     * �@@�P�|�R�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_01729<BR>
     * <BR>
     * �Q�j�@@�ڋq�R�[�h�̃`�F�b�N<BR>
     * �@@�Q�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00835<BR>
     * �@@�Q�|�Q�j�@@������6�łȂ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00836<BR>
     * �@@�Q�|�R�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_01043<BR>
     * <BR>
     * �R�j�@@�A����ʂ̃`�F�b�N<BR>
     * �@@�R�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_01817<BR>
     * <BR>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        // �P�j�@@���X�R�[�h�̃`�F�b�N
        // �@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        if (this.branchCode == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���X�R�[�h�����w��ł��B");
        }

        // �@@�P�|�Q�j�@@������3�łȂ��ꍇ�A��O���X���[����B
        if (this.branchCode.length() != 3)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00834,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���X�R�[�h�̃T�C�Y���s���ł��B");
        }

        // �@@�P�|�R�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B
        if (!(WEB3StringTypeUtility.isDigit(this.branchCode)))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01729,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���X�R�[�h�����l�ȊO�̒l�ł��B");
        }

        // �Q�j�@@�ڋq�R�[�h�̃`�F�b�N
        // �@@�Q�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        if (this.accountCode == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00835,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�ڋq�R�[�h�����w��ł��B");
        }

        // �@@�Q�|�Q�j�@@������6�łȂ��ꍇ�A��O���X���[����B
        if (this.accountCode.length() != 6)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00836,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�ڋq�R�[�h�̃T�C�Y���s���ł��B");
        }

        // �@@�Q�|�R�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B<BR>
        if (!(WEB3StringTypeUtility.isDigit(this.accountCode)))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01043,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�ڋq�R�[�h�̒l�������ȊO�̒l�ł��B");
        }

        // �R�j�@@�A����ʂ̃`�F�b�N
        // �@@�R�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        if (this.informType == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01817,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�A����ʂ����w��ł��B");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminInformAccSwElecDeliApplyCmpResponse(this);
    }
}
@
