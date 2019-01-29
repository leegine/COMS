head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.52.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminSLAccountOpenApplyListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҏ،��S�ۃ��[���\���ڋq�ꗗ���N�G�X�g(WEB3AdminSLAccountOpenApplyListRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/07 �����q (���u) �V�K�쐬 �d�l�ύX�E���f��No.756
*/

package webbroker3.aio.message;

import java.util.Date;

import webbroker3.aio.define.WEB3ApplyStateDivDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�Ǘ��ҏ،��S�ۃ��[���\���ڋq�ꗗ���N�G�X�g)<BR>
 * �Ǘ��ҏ،��S�ۃ��[���\���ڋq�ꗗ���N�G�X�g<BR>
 *
 * @@author �����q
 * @@version 1.0
 */
public class WEB3AdminSLAccountOpenApplyListRequest extends WEB3GenRequest
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminSLAccountOpenApplyListRequest.class);

    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_sl_account_open_apply_list";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200709071051L;

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
     * (�X�g�b�N���[�������ԍ�)<BR>
     * �X�g�b�N���[�������ԍ�<BR>
     */
    public String stockLoanAccount;

    /**
     * (�\����)<BR>
     * �\����<BR>
     * <BR>
     * 0�F�\�� 1�F�J�� 2�F���� 3�F��� 4�F��<BR>
     */
    public String applyStatus;

    /**
     * (�\����From)<BR>
     * �\����From<BR>
     */
    public Date applyDateFrom;

    /**
     * (�\����To)<BR>
     * �\����To<BR>
     */
    public Date applyDateTo;

    /**
     * (�J�ݓ�From)<BR>
     * �J�ݓ�From<BR>
     */
    public Date accountOpenDateFrom;

    /**
     * (�J�ݓ�To)<BR>
     * �J�ݓ�To<BR>
     */
    public Date accountOpenDateTo;

    /**
     * (�����From)<BR>
     * �����From<BR>
     */
    public Date executeDateFrom;

    /**
     * (�����To)<BR>
     * �����To<BR>
     */
    public Date executeDateTo;

    /**
     * (����From)<BR>
     * ����From<BR>
     */
    public Date cancelDateFrom;

    /**
     * (����To)<BR>
     * ����To<BR>
     */
    public Date cancelDateTo;

    /**
     * (����From)<BR>
     * ����From<BR>
     */
    public Date closeDateFrom;

    /**
     * (����To)<BR>
     * ����To<BR>
     */
    public Date closeDateTo;

    /**
     * (�v���y�[�W�ԍ�)<BR>
     * �v���y�[�W�ԍ�<BR>
     */
    public String pageIndex;

    /**
     * (�y�[�W���\���s��)<BR>
     * �y�[�W���\���s��<BR>
     */
    public String pageSize;

    /**
     * (�\�[�g�L�[)<BR>
     * �\�[�g�L�[<BR>
     */
    public WEB3SLSortKey[] sortKeys;

    /**
     * @@roseuid 46E0BE47036B
     */
    public WEB3AdminSLAccountOpenApplyListRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * 1)�@@���X�R�[�h�̃`�F�b�N<BR>
     * �@@1-1)�@@this.���X�R�[�h !=null �ł���A������!=3���̏ꍇ�A��O���X���[����B<BR>
     * �@@class:�@@WEB3BusinessLayerException<BR>
     * �@@tag�@@:�@@BUSINESS_ERROR_00834<BR>
     * �@@1-2)�@@���p�����ȊO�̕��������͂���Ă���ꍇ�A��O���X���[����B<BR>
     * �@@class:�@@WEB3BusinessLayerException<BR>
     * �@@tag�@@:�@@BUSINESS_ERROR_01729<BR>
     * <BR>
     * 2)�@@�ڋq�R�[�h�̃`�F�b�N<BR>
     * �@@2-1)�@@this.�ڋq�R�[�h !=null �ł���A������!=6���̏ꍇ�A��O���X���[����B<BR>
     * �@@class:�@@WEB3BusinessLayerException<BR>
     * �@@tag�@@:�@@BUSINESS_ERROR_00836<BR>
     * �@@2-2)�@@���p�����ȊO�̕��������͂���Ă���ꍇ�A��O���X���[����B<BR>
     * �@@class:�@@WEB3BusinessLayerException<BR>
     * �@@tag�@@:�@@BUSINESS_ERROR_01043<BR>
     * <BR>
     * 3)�@@�X�g�b�N���[�������ԍ��̃`�F�b�N<BR>
     * �@@3-1)�@@this.�X�g�b�N���[�������ԍ� !=null�ł���A<BR>
     * �@@�@@�@@��������11���ȏ�̏ꍇ�A��O���X���[����B<BR>
     * �@@class:�@@WEB3BusinessLayerException<BR>
     * �@@tag�@@:�@@BUSINESS_ERROR_02902<BR>
     * �@@3-2)�@@���p�����ȊO�̕��������͂���Ă���ꍇ�A��O���X���[����B<BR>
     * �@@class:�@@WEB3BusinessLayerException<BR>
     * �@@tag�@@:�@@BUSINESS_ERROR_02903<BR>
     * <BR>
     * 4)�@@�\���󋵂̃`�F�b�N<BR>
     * �@@this.�\���� !=null �ł���A�ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@0�F�i�\���j<BR>
     * �@@�@@�@@1�F�i�J�݁j<BR>
     * �@@�@@�@@2�F�i����j<BR>
     * �@@�@@�@@3�F�i���j<BR>
     * �@@�@@�@@4�F�i���j<BR>
     * �@@class:�@@WEB3BusinessLayerException<BR>
     * �@@tag�@@:�@@BUSINESS_ERROR_02904<BR>
     * <BR>
     * 5)�@@�\�������t�������`�F�b�N<BR>
     * �@@5-1)�@@this.�\����From !=null ���� <BR>
     *  �@@�@@this.�\����To!=null�̏ꍇ�ȉ��̃`�F�b�N���s���B<BR>
     * �@@5-2)�@@this.�\����From �� this.�\����To �̏ꍇ�A��O���X���[����B<BR>
     * �@@class:�@@WEB3BusinessLayerException<BR>
     * �@@tag�@@:�@@BUSINESS_ERROR_01762<BR>
     * <BR>
     * 6)�@@�J�ݓ����t�������`�F�b�N<BR>
     * �@@6-1)�@@this.�J�ݓ�From !=null ���� <BR>
     * �@@�@@�@@this.�J�ݓ�To!=null�̏ꍇ�ȉ��̃`�F�b�N���s���B<BR>
     * �@@6-2)�@@this.�J�ݓ�From �� this.�J�ݓ�To �̏ꍇ�A��O���X���[����B<BR>
     * �@@class:�@@WEB3BusinessLayerException<BR>
     * �@@tag�@@:�@@BUSINESS_ERROR_01328<BR>
     * <BR>
     * 7)�@@��������t�������`�F�b�N<BR>
     * �@@7-1)�@@this.�����From !=null ���� <BR>
     * �@@�@@�@@this.�����To!=null�̏ꍇ�ȉ��̃`�F�b�N���s���B<BR>
     * �@@7-2)�@@this.�����From �� this.�����To �̏ꍇ�A��O���X���[����B<BR>
     * �@@class:�@@WEB3BusinessLayerException<BR>
     * �@@tag�@@:�@@BUSINESS_ERROR_02905<BR>
     * <BR>
     * 8)�@@�������t�������`�F�b�N<BR>
     * �@@8-1)�@@this.����From !=null ���� <BR>
     * �@@�@@�@@this.����To!=null�̏ꍇ�ȉ��̃`�F�b�N���s���B<BR>
     * �@@8-2)�@@this.����From �� this.����To �̏ꍇ�A��O���X���[����B<BR>
     * �@@class:�@@WEB3BusinessLayerException<BR>
     * �@@tag�@@:�@@BUSINESS_ERROR_02906<BR>
     * <BR>
     * 9)�@@�������t�������`�F�b�N<BR>
     * �@@9-1)�@@this.����From !=null ���� <BR>
     * �@@�@@�@@this.����To!=null�̏ꍇ�ȉ��̃`�F�b�N���s���B<BR>
     * �@@9-2)�@@this.����From �� this.����To �̏ꍇ�A��O���X���[����B<BR>
     * �@@class:�@@WEB3BusinessLayerException<BR>
     * �@@tag�@@:�@@BUSINESS_ERROR_02907<BR>
     * 10)�@@�S�ۃ��[���\�[�g�L�[�̃`�F�b�N<BR>
     * �@@10-1)�@@this.�S�ۃ��[���\�[�g�L�[==null�̏ꍇ�A��O���X���[����B<BR>
     * �@@class:�@@WEB3BusinessLayerException<BR>
     * �@@tag�@@:�@@BUSINESS_ERROR_00231<BR>
     * �@@10-2)�@@this.�S�ۃ��[���\�[�g�L�[�̗v�f��==0�̏ꍇ�A��O���X���[����B<BR>
     * �@@class:�@@WEB3BusinessLayerException<BR>
     * �@@tag�@@:�@@BUSINESS_ERROR_00232<BR>
     * �@@10-3)�@@this.�S�ۃ��[���\�[�g�L�[�̗v�f�����A�ȉ����J��Ԃ��B<BR>
     * �@@�@@10-3-1)�@@this.�S�ۃ��[���\�[�g�L�[.�L�[����==null�̏ꍇ�A��O���X���[����B<BR>
     * �@@class:�@@WEB3BusinessLayerException<BR>
     * �@@tag�@@:�@@BUSINESS_ERROR_00085<BR>
     * �@@�@@10-3-2)�@@this.�S�ۃ��[���\�[�g�L�[.�����^�~��==null�̏ꍇ�A��O���X���[����B<BR>
     * �@@class:�@@WEB3BusinessLayerException<BR>
     * �@@tag�@@:�@@BUSINESS_ERROR_00087<BR>
     * �@@�@@10-3-3)�@@this.�S�ۃ��[���\�[�g�L�[.�����^�~�����ȉ��̒l�ȊO�������ꍇ�A<BR>
     * ��O���X���[����B<BR>
     * �@@�@@�@@"A:����"<BR>
     * �@@�@@�@@"D:�~��"<BR>
     * �@@class:�@@WEB3BusinessLayerException<BR>
     * �@@tag�@@:�@@BUSINESS_ERROR_00088<BR>
     * <BR>
     * 11)�@@�v���y�[�W�ԍ��`�F�b�N<BR>
     * �@@11-1)�@@this.�v���y�[�W�ԍ�==null�̒l�ł���Η�O���X���[����B<BR>
     * �@@class:�@@WEB3BusinessLayerException<BR>
     * �@@tag�@@:�@@BUSINESS_ERROR_00089<BR>
     * �@@11-2)�@@this.�v���y�[�W�ԍ��������ȊO�̒l�ł���Η�O���X���[����B<BR>
     * �@@class:�@@WEB3BusinessLayerException<BR>
     * �@@tag�@@:�@@BUSINESS_ERROR_00090<BR>
     * <BR>
     * 12)�@@�y�[�W���\���s���`�F�b�N<BR>
     * �@@12-1)�@@this.�y�[�W���\���s��==null�̒l�ł���Η�O���X���[����B<BR>
     * �@@class:�@@WEB3BusinessLayerException<BR>
     * �@@tag�@@:�@@BUSINESS_ERROR_00091<BR>
     * �@@12-2)�@@this.�y�[�W���\���s���������ȊO�̒l�ł���Η�O���X���[����B<BR>
     * �@@class:�@@WEB3BusinessLayerException<BR>
     * �@@tag�@@:�@@BUSINESS_ERROR_00092<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 46CE630F0358
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.exiting(STR_METHOD_NAME);

        // 1) ���X�R�[�h�̃`�F�b�N
        // 1-1) this.���X�R�[�h !=null �ł���A������!=3���̏ꍇ�A��O���X���[����B
        if (this.branchCode != null && this.branchCode.length() != 3)
        {
            log.debug("���X�R�[�h�̃T�C�Y���s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00834,
                getClass().getName() + "." + STR_METHOD_NAME,
                "���X�R�[�h�̃T�C�Y���s���ł��B");
        }
        // 1-2) ���p�����ȊO�̕��������͂���Ă���ꍇ�A��O���X���[����B
        if (this.branchCode != null && !WEB3StringTypeUtility.isDigit(this.branchCode))
        {
            log.debug("���X�R�[�h�����l�ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01729,
                getClass().getName() + "." + STR_METHOD_NAME,
                "���X�R�[�h�����l�ȊO�̒l�ł��B");
        }
        // 2-1) this.�ڋq�R�[�h !=null �ł���A������!=6���̏ꍇ�A��O���X���[����B
        if (this.accountCode != null && this.accountCode.length() != 6)
        {
            log.debug("�ڋq�R�[�h�̃T�C�Y���s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00836,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�ڋq�R�[�h�̃T�C�Y���s���ł��B");
        }
        //  2-2) ���p�����ȊO�̕��������͂���Ă���ꍇ�A��O���X���[����B
        if (this.accountCode != null && !WEB3StringTypeUtility.isDigit(this.accountCode))
        {
            log.debug("�ڋq�R�[�h�̒l�������ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01043,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�ڋq�R�[�h�̒l�������ȊO�̒l�ł��B");
        }
        // 3-1) this.�X�g�b�N���[�������ԍ� !=null �ł���A��������11���ȏ�̏ꍇ�A��O���X���[����B
        if (this.stockLoanAccount != null && this.stockLoanAccount.length() >= 11)
        {
            log.debug("�X�g�b�N���[�������ԍ��̃T�C�Y���s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02902,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�X�g�b�N���[�������ԍ��̃T�C�Y���s���ł��B");
        }
        // 3-2) ���p�����ȊO�̕��������͂���Ă���ꍇ�A��O���X���[����B
        if (this.stockLoanAccount != null && !WEB3StringTypeUtility.isDigit(this.stockLoanAccount))
        {
            log.debug("�X�g�b�N���[�������ԍ������p�����ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02903,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�X�g�b�N���[�������ԍ������p�����ȊO�̒l�ł��B");
        }
        // this.�\���� !=null �ł���A�ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B
        // 0�F�i�\���j
        // 1�F�i�J�݁j
        // 2�F�i����j
        // 3�F�i���j
        // 4�F�i���j
        if (this.applyStatus != null &&
            !WEB3ApplyStateDivDef.APPLY.equals(this.applyStatus) &&
            !WEB3ApplyStateDivDef.ACCOUNT_OPEN.equals(this.applyStatus) &&
            !WEB3ApplyStateDivDef.EXECUTE.equals(this.applyStatus) &&
            !WEB3ApplyStateDivDef.CANCEL.equals(this.applyStatus) &&
            !WEB3ApplyStateDivDef.CLOSE.equals(this.applyStatus))
        {
            log.debug("�\���󋵂̒l�����݂��Ȃ��R�[�h�l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02904,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�\���󋵂̒l�����݂��Ȃ��R�[�h�l�ł��B");
        }
        //  5-1) this.�\����From !=null ���� this.�\����To !=null�̏ꍇ�ȉ��̃`�F�b�N���s���B
        if (this.applyDateFrom != null && this.applyDateTo != null)
        {
            // 5-2) this.�\����From �� this.�\����To �̏ꍇ�A��O���X���[����B
            if (WEB3DateUtility.compareToDay(this.applyDateFrom, this.applyDateTo) > 0)
            {
                log.debug("�\�����i���j�͐\�����i���j���傫���ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01762,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "�\�����i���j�͐\�����i���j���傫���ł��B");
            }
        }
        // 6-1) this.�J�ݓ�From !=null ���� this.�J�ݓ�To !=null�̏ꍇ�ȉ��̃`�F�b�N���s���B
        if (this.accountOpenDateFrom != null && this.accountOpenDateTo != null)
        {
            // 6-2) this.�J�ݓ�From �� this.�J�ݓ�To �̏ꍇ�A��O���X���[����B
            if (WEB3DateUtility.compareToDay(this.accountOpenDateFrom, this.accountOpenDateTo) > 0)
            {
                log.debug("�����J�ݓ��i���j�͌����J�ݓ��i���j���傫���ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01328,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "�����J�ݓ��i���j�͌����J�ݓ��i���j���傫���ł��B");
            }
        }
        // 7-1) this.�����From !=null ���� this.�����To !=null�̏ꍇ�ȉ��̃`�F�b�N���s���B
        if (this.executeDateFrom != null && this.executeDateTo != null)
        {
            // 7-2) this.�����From �� this.�����To �̏ꍇ�A��O���X���[����B
            if (WEB3DateUtility.compareToDay(this.executeDateFrom, this.executeDateTo) > 0)
            {
                log.debug("�����From�͐����To���傫���ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02905,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "�����From�͐����To���傫���ł��B");
            }
        }
        // 8-1) this.����From !=null ���� this.����To !=null�̏ꍇ�ȉ��̃`�F�b�N���s���B
        if (this.cancelDateFrom != null && this.cancelDateTo != null)
        {
            // 8-2) this.����From �� this.����To �̏ꍇ�A��O���X���[����B
            if (WEB3DateUtility.compareToDay(this.cancelDateFrom, this.cancelDateTo) > 0)
            {
                log.debug("����From�͉���To���傫���ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02906,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "����From�͉���To���傫���ł��B");
            }
        }
        // 9-1) this.����From !=null ���� this.����To !=null�̏ꍇ�ȉ��̃`�F�b�N���s���B
        if (this.closeDateFrom != null && this.closeDateTo != null)
        {
            // 9-2) this.����From �� this.����To �̏ꍇ�A��O���X���[����B
            if (WEB3DateUtility.compareToDay(this.closeDateFrom, this.closeDateTo) > 0)
            {
                log.debug("����From�͕���To���傫���ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02907,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "����From�͕���To���傫���ł��B");
            }
        }
        // 10-1) this.�S�ۃ��[���\�[�g�L�[==null�̏ꍇ�A��O���X���[����B
        if (this.sortKeys == null)
        {
            log.debug("�\�[�g�L�[�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�\�[�g�L�[�����w��ł��B");
        }
        // 10-2) this.�S�ۃ��[���\�[�g�L�[�̗v�f��==0�̏ꍇ�A��O���X���[����B
        if (this.sortKeys.length == 0)
        {
            log.debug("�\�[�g�L�[�̗v�f�����O�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�\�[�g�L�[�̗v�f�����O�ł��B");
        }

        int l_intLength = this.sortKeys.length;
        for (int i = 0; i < l_intLength; i++)
        {
            // 10-3-1) this.�S�ۃ��[���\�[�g�L�[.�L�[����==null�̏ꍇ�A��O���X���[����B
            if (this.sortKeys[i].keyItem == null)
            {
                log.debug("�\�[�g�L�[�̃L�[���ڂ����w��ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00085,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "�\�[�g�L�[�̃L�[���ڂ����w��ł��B");
            }
            // 10-3-2) this.�S�ۃ��[���\�[�g�L�[.�����^�~��==null�̏ꍇ�A��O���X���[����B
            if (this.sortKeys[i].ascDesc == null)
            {
                log.debug("�����^�~�������w��ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00087,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "�����^�~�������w��ł��B");
            }
            // 10-3-3) this.�S�ۃ��[���\�[�g�L�[.�����^�~�����ȉ��̒l�ȊO�������ꍇ�A��O���X���[����B
            // "A:����"
            // "D:�~��"
            if (!(WEB3AscDescDef.ASC.equals(this.sortKeys[i].ascDesc) ||
                    WEB3AscDescDef.DESC.equals(this.sortKeys[i].ascDesc)))
            {
                log.debug("�����^�~�����hA�F�����h�A�hD�F�~���h�ȊO�̒l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00088,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�����^�~�����hA�F�����h�A�hD�F�~���h�ȊO�̒l�ł��B");
            }
        }

        // 11-1) this.�v���y�[�W�ԍ�==null�̒l�ł���Η�O���X���[����B
        if (this.pageIndex == null)
        {
            log.debug("�v���y�[�W�ԍ������w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�v���y�[�W�ԍ������w��ł��B");
        }
        //  11-2) this.�v���y�[�W�ԍ��������ȊO�̒l�ł���Η�O���X���[����B
        if (!WEB3StringTypeUtility.isDigit(this.pageIndex))
        {
            log.debug("�v���y�[�W�ԍ��������ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�v���y�[�W�ԍ��������ȊO�̒l�ł��B");
        }
        // 12-1) this.�y�[�W���\���s��==null�̒l�ł���Η�O���X���[����B
        if (this.pageSize == null)
        {
            log.debug("�y�[�W���\���s���̓��͂��s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�y�[�W���\���s���̓��͂��s���ł��B");
        }
        // 12-2) this.�y�[�W���\���s���������ȊO�̒l�ł���Η�O���X���[����B
        if (!WEB3StringTypeUtility.isDigit(this.pageSize))
        {
            log.debug("�y�[�W���\���s���������ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                getClass().getName() + "." + STR_METHOD_NAME,
                "�y�[�W���\���s���������ȊO�̒l�ł��B");
        }
    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * @@return ���X�|���X�I�u�W�F�N�g<BR>
     * @@roseuid 46CE83B20329
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminSLAccountOpenApplyListResponse(this);
    }
}
@
