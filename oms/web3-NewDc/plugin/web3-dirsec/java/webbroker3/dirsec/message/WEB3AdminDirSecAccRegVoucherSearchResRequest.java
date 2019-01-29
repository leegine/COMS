head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.09.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecAccRegVoucherSearchResRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �Ǘ��ҁE�ڋq���o�^�`�[�������ʃ��N�G�X�g(WEB3AdminDirSecAccRegVoucherSearchResRequest.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/06/12  �����q (���u) �V�K�쐬 �d�l�ύX ���f��No.098
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
 * (�Ǘ��ҁE�ڋq���o�^�`�[�������ʃ��N�G�X�g)<BR>
 * �Ǘ��ҁE�ڋq���o�^�`�[�������ʃ��N�G�X�g�N���X�B<BR>
 * <BR>
 * @@author �����q
 * @@version 1.0
 */
public class WEB3AdminDirSecAccRegVoucherSearchResRequest extends WEB3GenRequest
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3AdminDirSecAccRegVoucherSearchResRequest.class);

    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_dirsec_acc_reg_voucher_search_res";

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 200706121120L;

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
     * (�f�[�^�R�[�h)<BR>
     * �f�[�^�R�[�h<BR>
     */
    public String dataCode;

    /**
     * (�`�[���M��)<BR>
     * �`�[���M��<BR>
     */
    public String voucherSendDate;

    /**
     * @@roseuid 466E0B6B0173
     */
    public WEB3AdminDirSecAccRegVoucherSearchResRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j �����K�{���ڃ`�F�b�N<BR>
     * �@@�P-�P�j this.���X�R�[�h == null OR this.�ڋq�R�[�h == null �̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�����K�{���ڂ�����܂���B�v�̗�O���X���[����B<BR>
     * �@@�@@class�@@�@@: WEB3BusinessLayerException<BR>
     * �@@�@@tag�@@�@@�@@: BUSINESS_ERROR_02833<BR>
     * <BR>
     * �@@�P-�Q�j this.���X�R�[�h�����p�����ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u���X�R�[�h���s���ł��B�v�̗�O���X���[����B<BR>
     * �@@�@@class�@@�@@: WEB3BusinessLayerException<BR>
     * �@@�@@tag�@@�@@�@@: BUSINESS_ERROR_00779<BR>
     * <BR>
     * �@@�P-�R�j this.�ڋq�R�[�h�����p�����ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�ڋq�R�[�h���s���ł��B�v�̗�O���X���[����B<BR>
     * �@@�@@class�@@�@@: WEB3BusinessLayerException<BR>
     * �@@�@@tag�@@�@@�@@: BUSINESS_ERROR_00780<BR>
     * <BR>
     * <BR>
     * �Q�j �f�[�^�R�[�h�`�F�b�N<BR>
     * �@@�Q-�P�j this.�f�[�^�R�[�h != null AND this.�f�[�^�R�[�h �̒��� > 5 �̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�f�[�^�R�[�h���s���ł��B�v�̗�O���X���[����B<BR>
     * �@@�@@class�@@�@@: WEB3BusinessLayerException<BR>
     * �@@�@@tag�@@�@@�@@: BUSINESS_ERROR_02828<BR>
     * <BR>
     * �@@�Q-�Q�j this.�f�[�^�R�[�h != null AND this.�f�[�^�R�[�h�����p�p���ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�f�[�^�R�[�h���s���ł��B�v�̗�O���X���[����B<BR>
     * �@@�@@class�@@�@@: WEB3BusinessLayerException<BR>
     * �@@�@@tag�@@�@@�@@: BUSINESS_ERROR_02828<BR>
     * <BR>
     * �R�j �`�[���M���`�F�b�N<BR>
     * �@@�R-�P�j this.�`�[���M�� != null AND this.�`�[���M���̒��� != 8 �̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�`�[���M�����s���ł��B�v�̗�O���X���[����B<BR>
     * �@@�@@class�@@�@@: WEB3BusinessLayerException<BR>
     * �@@�@@tag�@@�@@�@@: BUSINESS_ERROR_02834<BR>
     * <BR>
     * �@@�R-�Q�j this.�`�[���M�� != null AND this.�`�[���M��������łȂ��ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�`�[���M�����s���ł��B�v�̗�O���X���[����B<BR>
     * �@@�@@class�@@�@@: WEB3BusinessLayerException<BR>
     * �@@�@@tag�@@�@@�@@: BUSINESS_ERROR_02834<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 465647A301A9
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        //�P�j �����K�{���ڃ`�F�b�N
        // �P-�P�j this.���X�R�[�h == null OR this.�ڋq�R�[�h == null �̏ꍇ�A
        //       �u�����K�{���ڂ�����܂���B�v�̗�O���X���[����B
        if (this.branchCode == null || this.accountCode == null)
        {
            log.debug("�����K�{���ڂ�����܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02833,
                this.getClass().getName() + STR_METHOD_NAME,
                "�����K�{���ڂ�����܂���B");
        }

        // �P-�Q�j this.���X�R�[�h�����p�����ȊO�̏ꍇ�A
        //       �u���X�R�[�h���s���ł��B�v�̗�O���X���[����B
        if (!WEB3StringTypeUtility.isDigit(this.branchCode))
        {
            log.debug("���X�R�[�h���s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00779,
                this.getClass().getName() + STR_METHOD_NAME,
                "���X�R�[�h���s���ł��B");
        }

        // �P-�R�j this.�ڋq�R�[�h�����p�����ȊO�̏ꍇ�A
        //       �u�ڋq�R�[�h���s���ł��B�v�̗�O���X���[����B
        if (!WEB3StringTypeUtility.isDigit(this.accountCode))
        {
            log.debug("�ڋq�R�[�h���s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00780,
                this.getClass().getName() + STR_METHOD_NAME,
                "�ڋq�R�[�h���s���ł��B");
        }

        //�Q�j �f�[�^�R�[�h�`�F�b�N
        // �Q-�P�j this.�f�[�^�R�[�h != null AND this.�f�[�^�R�[�h �̒��� > 5 �̏ꍇ�A
        //       �u�f�[�^�R�[�h���s���ł��B�v�̗�O���X���[����B
        if (this.dataCode != null && this.dataCode.length() > 5)
        {
            log.debug("�f�[�^�R�[�h���s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02828,
                this.getClass().getName() + STR_METHOD_NAME,
                "�f�[�^�R�[�h���s���ł��B");
        }

        // �Q-�Q�j this.�f�[�^�R�[�h != null AND this.�f�[�^�R�[�h�����p�p���ȊO�̏ꍇ�A
        //       �u�f�[�^�R�[�h���s���ł��B�v�̗�O���X���[����B
        if (this.dataCode != null
            && (!WEB3StringTypeUtility.isLetterOrDigit(this.dataCode)))
        {
            log.debug("�f�[�^�R�[�h���s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02828,
                this.getClass().getName() + STR_METHOD_NAME,
                "�f�[�^�R�[�h���s���ł��B");
        }

        //�R�j �`�[���M���`�F�b�N
        // �R-�P�j this.�`�[���M�� != null AND this.�`�[���M���̒��� != 8 �̏ꍇ�A
        //       �u�`�[���M�����s���ł��B�v�̗�O���X���[����B
        if (this.voucherSendDate != null && this.voucherSendDate.length() != 8)
        {
            log.debug("�`�[���M�����s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02834,
                this.getClass().getName() + STR_METHOD_NAME,
                "�`�[���M�����s���ł��B");
        }

        // �R-�Q�j this.�`�[���M�� != null AND this.�`�[���M��������łȂ��ꍇ�A
        //       �u�`�[���M�����s���ł��B�v�̗�O���X���[����B
        if (this.voucherSendDate != null
            && !WEB3StringTypeUtility.isDateStr(this.voucherSendDate, "yyyyMMdd"))
        {
            log.debug("�`�[���M�����s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02834,
                this.getClass().getName() + STR_METHOD_NAME,
                "�`�[���M�����s���ł��B");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * <BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminDirSecAccRegVoucherSearchResResponse(this);
    }
}
@
