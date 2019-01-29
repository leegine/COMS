head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFPTDeleteConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҋ����@@��t�{���폜�m�F���N�G�X�g(WEB3AdminFPTDeleteConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/11/06 ���g (���u) �V�K�쐬 �d�l�ύX�E���f�� No.011
Revision History : 2008/01/25 ���n�m (���u) �d�l�ύX�E���f��No.022
*/

package webbroker3.docadmin.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (�Ǘ��ҋ����@@��t�{���폜�m�F���N�G�X�g)<BR>
 * �Ǘ��ҋ����@@��t�{���폜�m�F���N�G�X�g�N���X<BR>
 *
 * @@author ���g
 * @@version 1.0
 */
public class WEB3AdminFPTDeleteConfirmRequest extends WEB3GenRequest
{

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTDeleteConfirmRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_fpt_delete_confirm";

    /**
     * SerialVersionUID<BR>
     */
    private static final long serialVersionUID = 200711061017L;

    /**
     * (�����@@��t�{���폜�s)<BR>
     * �����@@��t�{���폜�s<BR>
     */
    public WEB3FPTHistoryInfoUnit financialProductTradeDeleteRow;

    /**
     * @@roseuid 472FC5B40343
     */
    public WEB3AdminFPTDeleteConfirmRequest()
    {

    }

    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j �����@@��t�{���폜�s == null �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@ class�@@�@@: WEB3BusinessLayerException<BR>
     * �@@�@@ tag�@@�@@�@@: BUSINESS_ERROR_02784<BR>
     * <BR>
     * �Q�j �����@@��t�{���폜�s.���X�R�[�h == null �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@ class�@@�@@: WEB3BusinessLayerException<BR>
     * �@@�@@ tag�@@�@@�@@: BUSINESS_ERROR_00833<BR>
     * <BR>
     * �R�j �����@@��t�{���폜�s.�ڋq�R�[�h == null �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@ class�@@�@@: WEB3BusinessLayerException<BR>
     * �@@�@@ tag�@@�@@�@@: BUSINESS_ERROR_00835<BR>
     * <BR>
     * �S�j �����@@��t�{���폜�s.���ʋ敪 == null �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@ class�@@�@@: WEB3BusinessLayerException<BR>
     * �@@�@@ tag�@@�@@�@@: BUSINESS_ERROR_02948<BR>
     * <BR>
     * �T�j �����@@��t�{���폜�s.�d�q�������R�[�h == null �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@ class�@@�@@: WEB3BusinessLayerException<BR>
     * �@@�@@ tag�@@�@@�@@: BUSINESS_ERROR_03009<BR>
     * <BR>
     * �U�j �����@@��t�{���폜�s.���ʌ�t�� == null �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@ class�@@�@@: WEB3BusinessLayerException<BR>
     * �@@�@@ tag�@@�@@�@@: BUSINESS_ERROR_02943<BR>
     * <BR>
     * �V�j �����@@��t�{���폜�s.���ʎ�ރR�[�h == null �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@ class�@@�@@: WEB3BusinessLayerException<BR>
     * �@@�@@ tag�@@�@@�@@: BUSINESS_ERROR_03013<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 4726CAE00273
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //�P�j �����@@��t�{���폜�s == null �̏ꍇ�A��O���X���[����B
        if (this.financialProductTradeDeleteRow == null)
        {
            log.debug("�폜�Y�����R�[�h�Ȃ��B");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02784,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�폜�Y�����R�[�h�Ȃ��B");
        }

        //�Q�j �����@@��t�{���폜�s.���X�R�[�h == null �̏ꍇ�A��O���X���[����B
        if (this.financialProductTradeDeleteRow.branchCode == null)
        {
            log.debug("���X�R�[�h�����w��ł��B");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���X�R�[�h�����w��ł��B");
        }

        //�R�j �����@@��t�{���폜�s.�ڋq�R�[�h == null �̏ꍇ�A��O���X���[����B
        if (this.financialProductTradeDeleteRow.acceptCode == null)
        {
            log.debug("�ڋq�R�[�h�����w��ł��B");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00835,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�ڋq�R�[�h�����w��ł��B");
        }

        //�S�j �����@@��t�{���폜�s.���ʋ敪 == null �̏ꍇ�A��O���X���[����B
        if (this.financialProductTradeDeleteRow.documentDiv == null)
        {
            log.debug("���ʋ敪�����w��ł��B");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02948,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���ʋ敪�����w��ł��B");
        }

        //�T�j �����@@��t�{���폜�s.�d�q�������R�[�h == null �̏ꍇ�A��O���X���[����B
        if (this.financialProductTradeDeleteRow.batoProductCode == null)
        {
            log.debug("�d�q�������R�[�h�����w��ł��B");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03009,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�d�q�������R�[�h�����w��ł��B");
        }

        //�U�j �����@@��t�{���폜�s.���ʌ�t�� == null �̏ꍇ�A��O���X���[����B
        if (this.financialProductTradeDeleteRow.docuDeliDate == null)
        {
            log.debug("���ʌ�t�������w��ł��B");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02943,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���ʌ�t�������w��ł��B");
        }

        //�V�j �����@@��t�{���폜�s.���ʎ�ރR�[�h == null �̏ꍇ�A��O���X���[����B
        if (this.financialProductTradeDeleteRow.documentCategory == null)
        {
            log.debug("���ʎ�ރR�[�h�����w��ł��B");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03013,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���ʎ�ރR�[�h�����w��ł��B");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (create���X�|���X)<BR>
     * (createResponse����)<BR>
     * <BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐����ĕԂ��B<BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminFPTDeleteConfirmResponse(this);
    }

}
@
