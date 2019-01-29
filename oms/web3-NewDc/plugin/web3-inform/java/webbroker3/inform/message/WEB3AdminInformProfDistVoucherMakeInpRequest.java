head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.47.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformProfDistVoucherMakeInpRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �Ǘ��ҁE�����`�[�쐬���̓��N�G�X�g(WEB3AdminInformProfDistVoucherMakeInpRequest.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/06/04 �Ӑ�(���u) �V�K�쐬 ���f��No.054�A073
Revision History    : 2007/06/14 ���n�m(���u) �C�� ���f��No.083
Revision History    : 2007/06/22 ����(SCS) �C�� ���f��No.095
*/

package webbroker3.inform.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3TransferDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.inform.define.WEB3InformRegistDivDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�Ǘ��ҁE�����`�[�쐬���̓��N�G�X�g)<BR>
 * �Ǘ��ҁE�����`�[�쐬���̓��N�G�X�g�N���X
 */
public class WEB3AdminInformProfDistVoucherMakeInpRequest extends WEB3GenRequest
{
    /**
     * (���O�o�̓��[�e�B���e�B)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminInformProfDistVoucherMakeInpRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_inform_prof_dist_voucher_make_inp";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200706042212L;

    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h
     */
    public String branchCode;

    /**
     * (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h
     */
    public String accountCode;

    /**
     * (�w��敪)<BR>
     * �w��敪
     */
    public String specifyDiv;

    /**
     * (�����R�[�h)<BR>
     * �����R�[�h
     */
    public String productCode;

    /**
     * (�o�^�敪)<BR>
     * �o�^�敪
     */
    public String registDiv;

    /**
     * (���i)<BR>
     * ���i
     */
    public String product;

    /**
     * (�U�֋敪)<BR>
     * �U�֋敪
     */
    public String transferDiv;

    /**
     * @@roseuid 4663A9D60232
     */
    public WEB3AdminInformProfDistVoucherMakeInpRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminInformProfDistVoucherMakeInpResponse(this);
    }

    /**
     * ���N�G�X�g�f�[�^�̊ȈՃ`�F�b�N���s���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j���X�R�[�h<BR>
     * <BR>
     * this.���X�R�[�h == null or<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag: BUSINESS_ERROR_02174<BR>
     * <BR>
     * this.���X�R�[�h != ���p���� or<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag: BUSINESS_ERROR_01729<BR>
     * this.���X�R�[�h.length() != 3<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag: BUSINESS_ERROR_00779<BR>
     * �@@<BR>
     * �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �Q�j�ڋq�R�[�h<BR>
     * <BR>
     * this.�ڋq�R�[�h == null or<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag: BUSINESS_ERROR_00835<BR>
     * <BR>
     * this.�ڋq�R�[�h != ���p���� or<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag: BUSINESS_ERROR_01043<BR>
     * <BR>
     * this.�ڋq�R�[�h.length() != 6<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag: BUSINESS_ERROR_00780<BR>
     * <BR>
     * �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �R�j�w��敪<BR>
     * <BR>
     * this.�w��敪 != null �̏ꍇ�A�ȉ��̃`�F�b�N<BR>
     * �@@this.�w��敪 != ���p�p���� or<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag: BUSINESS_ERROR_02794<BR>
     * <BR>
     * �@@this.�w��敪.length() != 1<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag: BUSINESS_ERROR_02795<BR>
     * <BR>
     * �@@�̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �S�j�����R�[�h<BR>
     * <BR>
     * this.�����R�[�h != null �̏ꍇ�A�ȉ��̃`�F�b�N<BR>
     * �@@this.�����R�[�h != ���p����<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag: BUSINESS_ERROR_00815<BR>
     * <BR>
     * �@@�̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �T�j�o�^�敪<BR>
     * <BR>
     * this.�o�^�敪 != null �̏ꍇ�A�ȉ��̃`�F�b�N<BR>
     * �@@this.�o�^�敪 != 1�F�V�K or 3�F�폜<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag: BUSINESS_ERROR_00841<BR>
     * <BR>
     * �@@�̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �U�j���i<BR>
     * <BR>
     * this.���i != null �̏ꍇ�A�ȉ��̃`�F�b�N<BR>
     * �@@this.���i != ���p�p���� or<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag: BUSINESS_ERROR_02792<BR>
     * <BR>
     * �@@this.���i.length() != 2<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag: BUSINESS_ERROR_02793<BR>
     * <BR>
     * �@@�̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �V�j�U�֋敪<BR>
     * <BR>
     * this.�U�֋敪 != null �̏ꍇ�A�ȉ��̃`�F�b�N<BR>
     * �@@this.�U�֋敪 != �P�F��s�U�� or �T�F�X�֐U��<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag: BUSINESS_ERROR_01772<BR>
     * <BR>
     * �@@�̏ꍇ�A��O���X���[����B<BR>
     * @@throws  WEB3BaseException
     * @@roseuid 4643C934002B
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        // this.���X�R�[�h == null
        if (this.branchCode == null)
        {
            log.debug("���X�R�[�h��null�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02174,
                this.getClass().getName() + STR_METHOD_NAME,
                "���X�R�[�h��null�ł��B");
        }

        // this.���X�R�[�h != ���p����
        if (!WEB3StringTypeUtility.isDigit(this.branchCode))
        {
            log.debug("���X�R�[�h�����l�ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01729,
                this.getClass().getName() + STR_METHOD_NAME,
                "���X�R�[�h�����l�ȊO�̒l�ł��B");
        }

        // this.���X�R�[�h.length() != 3
        if (this.branchCode.length() != 3)
        {
            log.debug("���X�R�[�h�̓��͂��s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00779,
                this.getClass().getName() + STR_METHOD_NAME,
                "���X�R�[�h�̓��͂��s���ł��B");
        }

        // this.�ڋq�R�[�h == null
        if (this.accountCode == null)
        {
            log.debug("�ڋq�R�[�h�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00835,
                this.getClass().getName() + STR_METHOD_NAME,
                "�ڋq�R�[�h�����w��ł��B");
        }

        // this.�ڋq�R�[�h != ���p����
        if (!WEB3StringTypeUtility.isDigit(this.accountCode))
        {
            log.debug("�ڋq�R�[�h�̒l�������ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01043,
                this.getClass().getName() + STR_METHOD_NAME,
                "�ڋq�R�[�h�̒l�������ȊO�̒l�ł��B");
        }

        // this.�ڋq�R�[�h.length() != 6
        if (this.accountCode.length() != 6)
        {
            log.debug("�ڋq�R�[�h�̓��͂��s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00780,
                this.getClass().getName() + STR_METHOD_NAME,
                "�ڋq�R�[�h�̓��͂��s���ł��B");
        }

        // this.�w��敪 != null �̏ꍇ�A�ȉ��̃`�F�b�N
        if (this.specifyDiv != null)
        {
            // this.�w��敪 != ���p�p����
            if (!WEB3StringTypeUtility.isLetterOrDigit(this.specifyDiv))
            {
                log.debug("�w��敪�����p�p�����ȊO�̒l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02794,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�w��敪�����p�p�����ȊO�̒l�ł��B");
            }

            // this.�w��敪.length() != 1
            if (this.specifyDiv.length() != 1)
            {
                log.debug("�w��敪�̃T�C�Y���s���ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02795,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�w��敪�̃T�C�Y���s���ł��B");
            }
        }

        // this.�����R�[�h != null �̏ꍇ�A�ȉ��̃`�F�b�N
        if (this.productCode != null)
        {
            // this.�����R�[�h != ���p����
            if (!WEB3StringTypeUtility.isDigit(this.productCode))
            {
                log.debug("�����R�[�h�������ȊO�̒l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00815,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�����R�[�h�������ȊO�̒l�ł��B");
            }
        }

        // this.�o�^�敪 == null
        if (this.registDiv == null)
        {
            log.debug("�o�^�敪�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02835,
                this.getClass().getName() + STR_METHOD_NAME,
                "�o�^�敪�����w��ł��B");
        }

        // this.�o�^�敪 != 1�F�V�K or 3�F�폜
        if (!WEB3InformRegistDivDef.REGISTRATION.equals(this.registDiv) &&
            !WEB3InformRegistDivDef.DELETE.equals(this.registDiv))
        {
            log.debug("�o�^�敪�����݂��Ȃ��R�[�h�l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00841,
                this.getClass().getName() + STR_METHOD_NAME,
                "�o�^�敪�����݂��Ȃ��R�[�h�l�ł��B");
        }

        // this.���i != null �̏ꍇ�A�ȉ��̃`�F�b�N
        if (this.product != null)
        {
            // this.���i != ���p�p����
            if (!WEB3StringTypeUtility.isLetterOrDigit(this.product))
            {
                log.debug("���i�����p�p�����ȊO�̒l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02792,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "���i�����p�p�����ȊO�̒l�ł��B");
            }

            // ���i�̃����O�X��1���Ń`�F�b�N����悤�ɏC�� 2007.06.22 SCS����
            // this.���i.length() != 1
            if (this.product.length() != 1)
            {
                log.debug("���i�̃T�C�Y���s���ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02793,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "���i�̃T�C�Y���s���ł��B");
            }
        }

        // this.�U�֋敪 != null �̏ꍇ�A�ȉ��̃`�F�b�N
        if (this.transferDiv != null)
        {
            // this.�U�֋敪 != �P�F��s�U�� or �T�F�X�֐U��
            if (!WEB3TransferDivDef.BANK_TRANSFER.equals(this.transferDiv) &&
                !WEB3TransferDivDef.POSTAL_TRANSFER.equals(this.transferDiv))
            {
                log.debug("�U�֋敪�����݂��Ȃ��R�[�h�l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01772,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�U�֋敪�����݂��Ȃ��R�[�h�l�ł��B");
            }
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
