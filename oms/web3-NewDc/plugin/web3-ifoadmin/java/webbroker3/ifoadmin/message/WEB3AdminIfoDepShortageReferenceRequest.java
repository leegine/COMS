head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminIfoDepShortageReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�؋����s���󋵏Ɖ�N�G�X�g(WEB3AdminIfoDepShortageReferenceRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/02/27 ���ʗ�(���u) �V�K�쐬 ���f��No.004
*/
package webbroker3.ifoadmin.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.ifoadmin.define.WEB3AdminIfoUnCancelDivDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�Ǘ��ҁE�؋����s���󋵏Ɖ�N�G�X�g)<BR>
 * �Ǘ��ҁE�؋����s���󋵏Ɖ�N�G�X�g�N���X<BR>
 * <BR>
 * @@author ���ʗ�(���u)
 * @@version 1.0
 */
public class WEB3AdminIfoDepShortageReferenceRequest extends WEB3GenRequest
{

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminIfoDepShortageReferenceRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_ifo_dep_shortage_reference";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200902271150L;

    /**
     * (�������t)<BR>
     * �������t<BR>
     * <BR>
     */
    public Date searchDate;

    /**
     * (���X�R�[�h�ꗗ)<BR>
     * ���X�R�[�h�̔z�� <BR>
     * <BR>
     * �����X�R�[�h�����͎��́APR�w�ŕێ����Ă��� <BR>
     * �@@�戵�\���X�R�[�h�ꗗ���Z�b�g�����B <BR>
     */
    public String[] branchCode;

    /**
     * (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h<BR>
     */
    public String accountCode = null;

    /**
     * (�������q�敪)<BR>
     * �������q�敪<BR>
     * <BR>
     * 0�F�������q<BR>
     * 1�F�s�������S�ڋq<BR>
     */
    public String uncancelDiv = "0";

    /**
     * (�v���y�[�W�ԍ�)<BR>
     * �v���y�[�W�ԍ� <BR>
     * <BR>
     * �\�����������y�[�W�ʒu���w��@@���擪�y�[�W��"1"�Ƃ���<BR>
     * <BR>
     * ���_�E�����[�h�@@�\����R�[�����ꂽ�ꍇ�́A <BR>
     * �@@�_�E�����[�h�y�[�W�ԍ��ƂȂ�B <BR>
     */
    public String pageIndex;

    /**
     * (�y�[�W���\���s��)<BR>
     * �y�[�W���\���s�� <BR>
     * <BR>
     * �P�y�[�W���ɕ\�����������s�����w��<BR>
     * <BR>
     * ���_�E�����[�h�@@�\����R�[�����ꂽ�ꍇ�́A <BR>
     * �@@�_�E�����[�h�����ƂȂ�B <BR>
     */
    public String pageSize;

    /**
     * (�؋����s���󋵃\�[�g�L�[)<BR>
     * �؋����s���󋵃\�[�g�L�[ <BR>
     */
    public WEB3IfoDepShortageSortKey[] sortKeys;

    /**
     * @@roseuid 49A7485403B9
     */
    public WEB3AdminIfoDepShortageReferenceRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
     * <BR>
     * �P�j�@@�������t�`�F�b�N <BR>
     * �@@�P�|�P�j�@@this.�������t== null�̏ꍇ�A <BR>
     * �@@�@@�u�������t��null�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag�@@:�@@BUSINESS_ERROR_03154<BR>
     * <BR>
     * �Q�j�@@���X�R�[�h�`�F�b�N <BR>
     * �@@�Q�|�P�j�@@this.���X�R�[�h�ꗗ == null�̏ꍇ�A <BR>
     * �@@�@@�u���X�R�[�h��null�v�̗�O���X���[����B <BR>
     * �@@�@@�iBUSINESS_ERROR_01429�j<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@this.���X�R�[�h�ꗗ�̗v�f�����ȉ��̏������s���B <BR>
     * �@@�@@�Q�|�Q�|�P�j�@@this.���X�R�[�h���ȉ��̏����ɊY������ꍇ�A <BR>
     * �@@�@@�@@�u���X�R�[�h�G���[�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�E���X�R�[�h != ���� <BR>
     * �@@�@@�@@�@@�E���X�R�[�h.length != 3 <BR>
     * �@@�@@�@@�iBUSINESS_ERROR_00779�j <BR>
     * <BR>
     * �R�j�@@�ڋq�R�[�h�`�F�b�N <BR>
     * �@@this.�ڋq�R�[�h != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B <BR>
     * �@@�R�|�P�j�@@this.�ڋq�R�[�h���ȉ��̏����ɊY������ꍇ�A <BR>
     * �@@�@@�u�ڋq�R�[�h�G���[�v�̗�O���X���[����B <BR>
     * �@@�@@�@@�E�ڋq�R�[�h != ���� <BR>
     * �@@�@@�@@�E�ڋq�R�[�h.length != 6<BR>
     * �@@�@@�iBUSINESS_ERROR_00780�j <BR>
     * <BR>
     * �S�j�@@�������q�敪�`�F�b�N <BR>
     * �@@�S�|�P�j�@@this.�������q�敪== null�̏ꍇ�A <BR>
     * �@@�@@�u�������q�敪��null�v�̗�O���X���[����B <BR>
     * �@@�@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag�@@:�@@BUSINESS_ERROR_03155<BR>
     * <BR>
     * �@@�S�|�Q�j�@@this.�������q�敪�ɉ��L�̍��ڈȊO���ݒ肳��Ă�����A <BR>
     * �@@�@@�u�������q�敪������`�̒l�v�̗�O���X���[����B <BR>
     * �@@�@@�@@�E"0�F�������q "<BR>
     * �@@�@@�@@�E"1�F�s�������S�ڋq "<BR>
     * �@@�@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag�@@:�@@BUSINESS_ERROR_03156<BR>
     * <BR>
     * �T�j�@@�\�[�g�L�[�`�F�b�N <BR>
     * �@@�T�|�P�j�@@this.�\�[�g�L�[�������͂̏ꍇ�A <BR>
     * �@@�@@�u�\�[�g�L�[��null�v�̗�O���X���[����B <BR>
     * �@@�@@�iBUSINESS_ERROR_00231�j<BR>
     * <BR>
     * �@@�T�|�Q�j�@@this.�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��B <BR>
     * �@@�@@�T�|�Q�|�P�j�@@�\�[�g�L�[.validate()���R�[������B <BR>
     * <BR>
     * �U�j�@@�v���y�[�W�ԍ��`�F�b�N <BR>
     * �@@�U�|�P�j�@@this.�v���y�[�W�ԍ� == null�ł������ꍇ�A <BR>
     * �@@�@@�@@�@@�u�v���y�[�W�ԍ���null�v�̗�O���X���[����B <BR>
     * �@@�@@�@@�iBUSINESS_ERROR_00089�j<BR>
     * <BR>
     * �@@�U�|�Q�j�@@this.�v���y�[�W�ԍ��������ȊO�̒l�ł������ꍇ�A <BR>
     * �@@�@@�@@�@@�u�v���y�[�W�ԍ��������ȊO�v�̗�O���X���[����B <BR>
     * �@@�@@�@@�iBUSINESS_ERROR_00090�j<BR>
     * <BR>
     * �@@�U�|�R�j�@@this.�v���y�[�W�ԍ� <= 0�ł������ꍇ�A <BR>
     * �@@�@@�@@�@@�u�v���y�[�W�ԍ���0�ȉ��v�̗�O���X���[����B <BR>
     * �@@�@@�@@�iBUSINESS_ERROR_00616�j<BR>
     * <BR>
     * �V�j�@@�y�[�W���\���s���`�F�b�N <BR>
     * �@@�V�|�P�j�@@this.�y�[�W���\���s�� == null�ł������ꍇ�A <BR>
     * �@@�@@�@@�@@�u�y�[�W���\���s����null�v�̗�O���X���[����B <BR>
     * �@@�@@�@@�iBUSINESS_ERROR_00091�j<BR>
     * <BR>
     * �@@�V�|�Q�j�@@this.�y�[�W���\���s���������ȊO�̒l�ł������ꍇ�A <BR>
     * �@@�@@�@@�@@�u�y�[�W���\���s���������ȊO�v�̗�O���X���[����B <BR>
     * �@@ �@@�@@�iBUSINESS_ERROR_00092�j<BR>
     * <BR>
     * �@@�V�|�R�j�@@this.�y�[�W���\���s�� <= 0�ł������ꍇ�A <BR>
     * �@@�@@�@@�@@�u�y�[�W���\���s����0�ȉ��v�̗�O���X���[����B <BR>
     * �@@�@@�@@�@@�iBUSINESS_ERROR_00617�j<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 498FCDDB0122
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@�������t�`�F�b�N
        // �@@�P�|�P�j�@@this.�������t== null�̏ꍇ�A
        // �@@�@@�u�������t��null�v�̗�O���X���[����B
        if (this.searchDate == null)
        {
            log.debug("�������t��null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03154,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�������t��null�B");
        }

        // �Q�j�@@���X�R�[�h�`�F�b�N
        // �@@�Q�|�P�j�@@this.���X�R�[�h�ꗗ == null�̏ꍇ�A
        // �@@�@@�u���X�R�[�h��null�v�̗�O���X���[����B
        if (this.branchCode == null || this.branchCode.length == 0)
        {
            log.debug("���X�R�[�h�ꗗ�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01429,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���X�R�[�h�ꗗ�����w��ł��B");
        }

        // �@@�Q�|�Q�j�@@this.���X�R�[�h�ꗗ�̗v�f�����ȉ��̏������s���B
        // �@@�@@�Q�|�Q�|�P�j�@@this.���X�R�[�h���ȉ��̏����ɊY������ꍇ�A
        // �@@�@@�@@�u���X�R�[�h�G���[�v�̗�O���X���[����B
        // �@@�@@�@@�@@�E���X�R�[�h != ����
        // �@@�@@�@@�@@�E���X�R�[�h.length != 3
        int l_intBranchCodeCnt = this.branchCode.length;
        for (int i = 0; i < l_intBranchCodeCnt; i++)
        {
            if (!WEB3StringTypeUtility.isDigit(this.branchCode[i])
                || this.branchCode[i].length() != 3)
            {
                log.debug("���X�R�[�h�̓��͂��s���ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00779,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���X�R�[�h�̓��͂��s���ł��B");
            }
        }

        // �R�j�@@�ڋq�R�[�h�`�F�b�N
        // �@@this.�ڋq�R�[�h != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B
        // �@@�R�|�P�j�@@this.�ڋq�R�[�h���ȉ��̏����ɊY������ꍇ�A
        // �@@�@@�u�ڋq�R�[�h�G���[�v�̗�O���X���[����B
        // �@@�@@�@@�E�ڋq�R�[�h != ����
        // �@@�@@�@@�E�ڋq�R�[�h.length != 6
        if (this.accountCode != null)
        {
            if (!WEB3StringTypeUtility.isDigit(this.accountCode)
                || this.accountCode.length() != 6)
            {
                log.debug("�ڋq�R�[�h�̓��͂��s���ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00780,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�ڋq�R�[�h�̓��͂��s���ł��B");
            }
        }

        // �S�j�@@�������q�敪�`�F�b�N
        // �@@�S�|�P�j�@@this.�������q�敪== null�̏ꍇ�A
        // �@@�@@�u�������q�敪��null�v�̗�O���X���[����B
        if (this.uncancelDiv == null)
        {
            log.debug("�������q�敪��null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03155,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�������q�敪��null�B");
        }

        // �@@�S�|�Q�j�@@this.�������q�敪�ɉ��L�̍��ڈȊO���ݒ肳��Ă�����A
        // �@@�@@�u�������q�敪������`�̒l�v�̗�O���X���[����B
        // �@@�@@�@@�E"0�F�������q "
        // �@@�@@�@@�E"1�F�s�������S�ڋq "
        if (!WEB3AdminIfoUnCancelDivDef.UN_CANCEL.equals(this.uncancelDiv)
            && !WEB3AdminIfoUnCancelDivDef.SHORT_GENERATION_ALL_ACCOUNT.equals(this.uncancelDiv))
        {
            log.debug("�������q�敪������`�̒l�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03156,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�������q�敪������`�̒l�B");
        }

        // �T�j�@@�\�[�g�L�[�`�F�b�N
        // �@@�T�|�P�j�@@this.�\�[�g�L�[�������͂̏ꍇ�A
        // �@@�@@�u�\�[�g�L�[��null�v�̗�O���X���[����B
        if (this.sortKeys == null || this.sortKeys.length == 0)
        {
            log.debug("�\�[�g�L�[�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�\�[�g�L�[�����w��ł��B");
        }

        // �@@�T�|�Q�j�@@this.�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��B
        // �@@�@@�T�|�Q�|�P�j�@@�\�[�g�L�[.validate()���R�[������B
        int l_intSortKeysLength = this.sortKeys.length;
        for (int i = 0; i < l_intSortKeysLength; i++)
        {
            this.sortKeys[i].validate();
        }

        // �U�j�@@�v���y�[�W�ԍ��`�F�b�N
        // �@@�U�|�P�j�@@this.�v���y�[�W�ԍ� == null�ł������ꍇ�A
        // �@@�@@�@@�@@�u�v���y�[�W�ԍ���null�v�̗�O���X���[����B
        if (this.pageIndex == null)
        {
            log.debug("�v���y�[�W�ԍ������w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�v���y�[�W�ԍ������w��ł��B");
        }

        // �@@�U�|�Q�j�@@this.�v���y�[�W�ԍ��������ȊO�̒l�ł������ꍇ�A
        // �@@�@@�@@�@@�u�v���y�[�W�ԍ��������ȊO�v�̗�O���X���[����B
        if (!WEB3StringTypeUtility.isInteger(this.pageIndex))
        {
            log.debug("�v���y�[�W�ԍ��������ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�v���y�[�W�ԍ��������ȊO�̒l�ł��B");
        }

        // �@@�U�|�R�j�@@this.�v���y�[�W�ԍ� <= 0�ł������ꍇ�A
        // �@@�@@�@@�@@�u�v���y�[�W�ԍ���0�ȉ��v�̗�O���X���[����B
        if (Integer.parseInt(this.pageIndex) <= 0)
        {
            log.debug("�v���y�[�W�ԍ��̒l��0�ȉ��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�v���y�[�W�ԍ��̒l��0�ȉ��ł��B");
        }

        // �V�j�@@�y�[�W���\���s���`�F�b�N
        // �@@�V�|�P�j�@@this.�y�[�W���\���s�� == null�ł������ꍇ�A
        // �@@�@@�@@�@@�u�y�[�W���\���s����null�v�̗�O���X���[����B
        if (this.pageSize == null)
        {
            log.debug("�y�[�W���\���s���̓��͂��s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�y�[�W���\���s���̓��͂��s���ł��B");
        }

        // �@@�V�|�Q�j�@@this.�y�[�W���\���s���������ȊO�̒l�ł������ꍇ�A
        // �@@�@@�@@�@@�u�y�[�W���\���s���������ȊO�v�̗�O���X���[����B
        if (!WEB3StringTypeUtility.isInteger(this.pageSize))
        {
            log.debug("�y�[�W���\���s���������ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�y�[�W���\���s���������ȊO�̒l�ł��B");
        }

        // �@@�V�|�R�j�@@this.�y�[�W���\���s�� <= 0�ł������ꍇ�A
        // �@@�@@�@@�@@�u�y�[�W���\���s����0�ȉ��v�̗�O���X���[����B
        if (Integer.parseInt(this.pageSize) <= 0)
        {
            log.debug("�y�[�W���\���s���̒l��0�ȉ��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�y�[�W���\���s���̒l��0�ȉ��ł��B");
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
        return new WEB3AdminIfoDepShortageReferenceResponse(this);
    }
}
@
