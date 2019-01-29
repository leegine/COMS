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
filename	WEB3AdminFPTUpdateCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҋ����@@��t�X�V���ʃ��N�G�X�g(WEB3AdminFPTUpdateCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/29 ���g (���u) �V�K�쐬
Revision History : 2007/10/09 �����q (���u) ���f��No.004
Revision History : 2008/01/25 ���n�m (���u) �d�l�ύX�E���f��No.022
Revision History : 2008/01/29 ���n�m (���u) �d�l�ύX�E���f��No.028
*/

package webbroker3.docadmin.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ValidFlagDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�Ǘ��ҋ����@@��t�X�V���ʃ��N�G�X�g)<BR>
 * �Ǘ��ҋ����@@��t�X�V���ʃ��N�G�X�g�N���X<BR>
 *
 * @@author ���g
 * @@version 1.0
 */
public class WEB3AdminFPTUpdateCommonRequest extends WEB3GenRequest
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_fpt_update_common";

    /**
     * SerialVersionUID<BR>
     */
    private static final long serialVersionUID = 200709291406L;

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTUpdateCommonRequest.class);

    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     */
    public String branchCode;

    /**
     * (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h<BR>
     */
    public String acceptCode;

    /**
     * (���ʌ�t��)<BR>
     * ���ʌ�t��<BR>
     */
    public Date docuDeliDate;

    /**
     * (���ʎ�ވꗗ)<BR>
     * ���ʎ�ވꗗ<BR>
     */
    public WEB3FPTDocumentCategoryDetailsInfoUnit[] documentCategoryList;

    /**
     * @@roseuid 46FDDD3C02DE
     */
    public WEB3AdminFPTUpdateCommonRequest()
    {

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
        return null;
    }

    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j ���X�R�[�h�`�F�b�N<BR>
     * <BR>
     * �@@�@@���N�G�X�g.���X�R�[�h�����ݒ� ����<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag�@@:�@@BUSINESS_ERROR_00833<BR>
     * �@@�@@���N�G�X�g.���X�R�[�h�����p�����ȊO ����<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag�@@:�@@BUSINESS_ERROR_01729<BR>
     * �@@�@@���N�G�X�g.���X�R�[�h��3���ȊO �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag�@@:�@@BUSINESS_ERROR_00834<BR>
     * <BR>
     * �Q�j �ڋq�R�[�h�`�F�b�N<BR>
     * <BR>
     * �@@�@@���N�G�X�g.�ڋq�R�[�h�����ݒ� ����<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag�@@:�@@BUSINESS_ERROR_00835<BR>
     * �@@�@@���N�G�X�g.�ڋq�R�[�h�����p�����ȊO ����<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag�@@:�@@BUSINESS_ERROR_01043<BR>
     * �@@�@@���N�G�X�g.�ڋq�R�[�h��6���ȊO �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag�@@:�@@BUSINESS_ERROR_00836<BR>
     * <BR>
     * �R�j ���ʌ�t���`�F�b�N<BR>
     * <BR>
     * �@@�@@���N�G�X�g.���ʌ�t�������ݒ� �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag�@@:�@@BUSINESS_ERROR_02943<BR>
     * <BR>
     * �S�j ���ʎ�ވꗗ�`�F�b�N(���ʎ�ވꗗ�z��S�v�f�ɑ΂��ă`�F�b�N���s��)<BR>
     * <BR>
     * �@@�S-�P�j ���N�G�X�g.���ʎ�ވꗗ��null�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag�@@:�@@BUSINESS_ERROR_03006<BR>
     * <BR>
     * �@@�S-�Q�j ���N�G�X�g.���ʎ�ވꗗ[index].���ʋ敪�Ǘ����null�A�܂���<BR>
     * �@@�@@�@@�@@�@@���N�G�X�g.���ʎ�ވꗗ[index].�d�q�������R�[�h�Ǘ����null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag�@@:�@@BUSINESS_ERROR_03007<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag�@@:�@@BUSINESS_ERROR_03008<BR>
     * <BR>
     * �@@�S-�R�j ���N�G�X�g.���ʎ�ވꗗ[index].���ʋ敪�Ǘ����.���ʋ敪 �����ݒ�<BR>
     * �@@�@@�@@�@@�@@���͔��p�����ȊO ���� 4���ȏ� �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag�@@:�@@BUSINESS_ERROR_02948<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag�@@:�@@BUSINESS_ERROR_02941<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag�@@:�@@BUSINESS_ERROR_02942<BR>
     * <BR>
     * �@@�S-�S�j ���N�G�X�g.���ʎ�ވꗗ[index].���ʋ敪�Ǘ����.���ʃ`�F�b�N�敪 �����ݒ�<BR>
     * �@@�@@�@@�@@�@@���͔��p�����ȊO ���� 3���ȏ� �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag�@@:�@@BUSINESS_ERROR_02944<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag�@@:�@@BUSINESS_ERROR_02945<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag�@@:�@@BUSINESS_ERROR_02946<BR>
     * <BR>
     * �@@�S-�T�j ���N�G�X�g.���ʎ�ވꗗ[index].�d�q�������R�[�h�Ǘ����`�F�b�N<BR>
     * �@@�@@�@@�@@�@@(�d�q�������R�[�h�Ǘ����z��S�v�f�ɑ΂��ă`�F�b�N���s��)<BR>
     * �@@�@@�S-�T-�P�j ���N�G�X�g.���ʎ�ވꗗ[index].�d�q�������R�[�h�Ǘ����[idx].�d�q�������R�[�h<BR>
     * �@@�@@�@@�@@�@@�����ݒ� ���� ���p�����ȊO ���� ������4�� or 7���ȊO<BR>
     * �@@�@@�@@�@@�@@�̏ꍇ�A��O���X���[����B <BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag�@@:�@@BUSINESS_ERROR_03009<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag�@@:�@@BUSINESS_ERROR_03010<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag�@@:�@@BUSINESS_ERROR_03011<BR>
     * <BR>
     * �@@�@@�S-�T-�Q�j ���N�G�X�g.���ʎ�ވꗗ[index].�d�q�������R�[�h�Ǘ����[idx].�L���敪<BR>
     * �@@�@@�@@�@@�@@�����ݒ� ���� 0�A1 �ȊO�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag�@@:�@@BUSINESS_ERROR_03012<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag�@@:�@@BUSINESS_ERROR_03014<BR>
     * <BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 46F3B756012A
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //�P�j ���X�R�[�h�`�F�b�N
        //���N�G�X�g.���X�R�[�h�����ݒ� ����
        //���N�G�X�g.���X�R�[�h�����p�����ȊO ����
        //���N�G�X�g.���X�R�[�h��3���ȊO �̏ꍇ�A��O���X���[����B
        if (this.branchCode == null)
        {
            log.debug("���X�R�[�h�����w��ł��B");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���X�R�[�h�����w��ł��B");
        }
        else if (!WEB3StringTypeUtility.isDigit(this.branchCode))
        {
            log.debug("���X�R�[�h�����l�ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01729,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���X�R�[�h�����l�ȊO�̒l�ł��B");
        }
        else if (this.branchCode.length() != 3)
        {
            log.debug("���X�R�[�h�̃T�C�Y���s���ł��B");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00834,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���X�R�[�h�̃T�C�Y���s���ł��B");
        }

        //�Q�j �ڋq�R�[�h�`�F�b�N
        //���N�G�X�g.�ڋq�R�[�h�����ݒ� ����
        //���N�G�X�g.�ڋq�R�[�h�����p�����ȊO ����
        //���N�G�X�g.�ڋq�R�[�h��6���ȊO �̏ꍇ�A��O���X���[����B
        if (this.acceptCode == null)
        {
            log.debug("�ڋq�R�[�h�����w��ł��B");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00835,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�ڋq�R�[�h�����w��ł��B");
        }
        else if (!WEB3StringTypeUtility.isDigit(this.acceptCode))
        {
            log.debug("�ڋq�R�[�h�̒l�������ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01043,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�ڋq�R�[�h�̒l�������ȊO�̒l�ł��B");
        }
        else if (this.acceptCode.length() != 6)
        {
             log.debug("�ڋq�R�[�h�̃T�C�Y���s���ł��B");
             log.exiting(STR_METHOD_NAME);

             throw new WEB3BusinessLayerException(
                 WEB3ErrorCatalog.BUSINESS_ERROR_00836,
                 this.getClass().getName() + "." + STR_METHOD_NAME,
                 "�ڋq�R�[�h�̃T�C�Y���s���ł��B");
        }

        //�R�j ���ʌ�t���`�F�b�N
        //���N�G�X�g.���ʌ�t�������ݒ� �̏ꍇ�A��O���X���[����B
        if (this.docuDeliDate == null)
        {
            log.debug("���ʌ�t�������w��ł��B");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02943,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���ʌ�t�������w��ł��B");
        }

        //�S�j ���ʎ�ވꗗ�`�F�b�N(���ʎ�ވꗗ�z��S�v�f�ɑ΂��ă`�F�b�N���s��)
        //�S-�P�j ���N�G�X�g.���ʎ�ވꗗ��null�̏ꍇ�A��O���X���[����B
        if (this.documentCategoryList == null)
        {
            log.debug("���ʎ�ވꗗ�����w��ł��B");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03006,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���ʎ�ވꗗ�����w��ł��B");
        }
        else
        {

            int l_intDocumentCategoryCount = this.documentCategoryList.length;
            for (int i = 0; i < l_intDocumentCategoryCount; i++)
            {
                if (this.documentCategoryList[i] == null)
                {
                    log.debug("���ʎ�ވꗗ�����w��ł��B");
                    log.exiting(STR_METHOD_NAME);

                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_03006,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "���ʎ�ވꗗ�����w��ł��B");
                }

                //�S-�Q�j ���N�G�X�g.���ʎ�ވꗗ[index].���ʋ敪�Ǘ����null�A�܂���
                //�@@�@@�@@�@@���N�G�X�g.���ʎ�ވꗗ[index].�d�q�������R�[�h�Ǘ����null�̏ꍇ�A
                //�@@�@@�@@�@@��O���X���[����B
                if (this.documentCategoryList[i].documentDivList == null)
                {
                    log.debug("���ʋ敪�Ǘ���񂪖��w��ł��B");
                    log.exiting(STR_METHOD_NAME);

                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_03007,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "���ʋ敪�Ǘ���񂪖��w��ł��B");
                }
                else if (this.documentCategoryList[i].batoProductCodeAdminInfo == null)
                {
                    log.debug("�d�q�������R�[�h�Ǘ���񂪖��w��ł��B");
                    log.exiting(STR_METHOD_NAME);

                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_03008,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "�d�q�������R�[�h�Ǘ���񂪖��w��ł��B");
                }

                //�S-�R�j ���N�G�X�g.���ʎ�ވꗗ[index].���ʋ敪�Ǘ����.���ʋ敪 �����ݒ�
                //�@@�@@�@@�@@���͔��p�����ȊO ���� 4���ȏ� �̏ꍇ�A��O���X���[����B
                if (this.documentCategoryList[i].documentDivList.documentDiv == null)
                {
                    log.debug("���ʋ敪�����w��ł��B");
                    log.exiting(STR_METHOD_NAME);

                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02948,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "���ʋ敪�����w��ł��B");
                }
                else if (!WEB3StringTypeUtility.isDigit(
                    this.documentCategoryList[i].documentDivList.documentDiv))
                {
                    log.debug("���ʋ敪�����p�����ȊO�̒l�ł��B");
                    log.exiting(STR_METHOD_NAME);

                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02941,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "���ʋ敪�����p�����ȊO�̒l�ł��B");
                }
                else if (this.documentCategoryList[i].documentDivList.documentDiv.length() >= 4)
                {
                    log.debug("���ʋ敪�̃T�C�Y���s���ł��B");
                    log.exiting(STR_METHOD_NAME);

                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02942,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "���ʋ敪�̃T�C�Y���s���ł��B");
                }

                //�S-�S�j ���N�G�X�g.���ʎ�ވꗗ[index].���ʋ敪�Ǘ����.���ʃ`�F�b�N�敪 �����ݒ�
                //�@@�@@�@@�@@���͔��p�����ȊO ���� 3���ȏ� �̏ꍇ�A��O���X���[����B
                if (this.documentCategoryList[i].documentDivList.docuCheckDiv == null)
                {
                    log.debug("���ʃ`�F�b�N�敪�����w��ł��B");
                    log.exiting(STR_METHOD_NAME);

                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02944,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "���ʃ`�F�b�N�敪�����w��ł��B");
                }
                else if (!WEB3StringTypeUtility.isDigit(
                    this.documentCategoryList[i].documentDivList.docuCheckDiv))
                {
                    log.debug("���ʃ`�F�b�N�敪�����p�����ȊO�̒l�ł��B");
                    log.exiting(STR_METHOD_NAME);

                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02945,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "���ʃ`�F�b�N�敪�����p�����ȊO�̒l�ł��B");
                }
                else if (this.documentCategoryList[i].documentDivList.docuCheckDiv.length() >= 3)
                {
                    log.debug("���ʃ`�F�b�N�敪�̃T�C�Y���s���ł��B");
                    log.exiting(STR_METHOD_NAME);

                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02946,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "���ʃ`�F�b�N�敪�̃T�C�Y���s���ł��B");
                }

                //�S-�T�j ���N�G�X�g.���ʎ�ވꗗ[index].�d�q�������R�[�h�Ǘ����`�F�b�N
                //�@@�@@�@@�@@(�d�q�������R�[�h�Ǘ����z��S�v�f�ɑ΂��ă`�F�b�N���s��)
                int l_intBatoProductCodeAdminInfoCounter =
                    this.documentCategoryList[i].batoProductCodeAdminInfo.length;
                for (int j = 0; j < l_intBatoProductCodeAdminInfoCounter; j++)
                {
                    //�S-�T-�P�j ���N�G�X�g.���ʎ�ވꗗ[index].�d�q�������R�[�h�Ǘ����[idx].�d�q�������R�[�h
                    //�@@�@@�@@�@@�����ݒ� ���� ���p�����ȊO ���� ������4�� or 7���ȊO
                    //�@@�@@�@@�@@�̏ꍇ�A��O���X���[����B
                    if (this.documentCategoryList[i].batoProductCodeAdminInfo[j].batoProductCode == null)
                    {
                        log.debug("�d�q�������R�[�h�����w��ł��B");
                        log.exiting(STR_METHOD_NAME);

                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_03009,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            "�d�q�������R�[�h�����w��ł��B");
                    }
                    else if (
                        !WEB3StringTypeUtility.isDigit(
                            this.documentCategoryList[i].batoProductCodeAdminInfo[j].batoProductCode))
                    {
                        log.debug("�d�q�������R�[�h�͔��p�����ȊO�̒l�ł��B");
                        log.exiting(STR_METHOD_NAME);

                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_03010,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            "�d�q�������R�[�h�͔��p�����ȊO�̒l�ł��B");
                    }
                    else if (this.documentCategoryList[i].batoProductCodeAdminInfo[j].batoProductCode.length() != 4
                        && this.documentCategoryList[i].batoProductCodeAdminInfo[j].batoProductCode.length() != 7)
                    {
                        log.debug("�d�q�������R�[�h�̃T�C�Y���s���ł��B");
                        log.exiting(STR_METHOD_NAME);

                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_03011,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            "�d�q�������R�[�h�̃T�C�Y���s���ł��B");
                    }
                    //�S-�T-�Q�j ���N�G�X�g.���ʎ�ވꗗ[index].�d�q�������R�[�h�Ǘ����[idx].�L���敪
                    //�@@�@@�@@�@@�����ݒ� ���� 0�A1 �ȊO�̏ꍇ�A��O���X���[����B
                    if (this.documentCategoryList[i].batoProductCodeAdminInfo[j].validFlag == null)
                    {
                        log.debug("�L���敪�����ݒ�ł��B");
                        log.exiting(STR_METHOD_NAME);

                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_03012,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            "�L���敪�����ݒ�ł��B");
                    }
                    else if (!WEB3ValidFlagDef.VALID.equals(
                            this.documentCategoryList[i].batoProductCodeAdminInfo[j].validFlag)
                        && !WEB3ValidFlagDef.INVALID.equals(
                            this.documentCategoryList[i].batoProductCodeAdminInfo[j].validFlag))
                    {
                        log.debug("�L���敪������`�̒l�ł��B");
                        log.exiting(STR_METHOD_NAME);

                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_03014,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            "�L���敪������`�̒l�ł��B");
                    }
                }
            }

        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
