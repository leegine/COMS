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
filename	WEB3AdminFPTDocumentCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҋ����@@��t���ʋ��ʃ��N�G�X�g(WEB3AdminFPTDocumentCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/03 �g�C�� (���u) �V�K�쐬 ���f��No.037,���f��No.044
*/
package webbroker3.docadmin.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ValidFlagDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.docadmin.define.WEB3AdminFPTProcessFlagDivDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�Ǘ��ҋ����@@��t���ʋ��ʃ��N�G�X�g)<BR>
 * �Ǘ��ҋ����@@��t���ʋ��ʃ��N�G�X�g�N���X<BR>
 *
 * @@author �g�C��
 * @@version 1.0
 */
public class WEB3AdminFPTDocumentCommonRequest extends WEB3GenRequest
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTDocumentCommonRequest.class);

    /**
     * (���ʍX�V���)<BR>
     * ���ʍX�V���<BR>
     */
    public WEB3FPTDocumentUpdateInfoUnit[] documentUpdateList;

    /**
     * (�X�V�����t���O)<BR>
     * �X�V�����t���O<BR>
     * <BR>
     * 0�F�o�^<BR>
     * 1�F�X�V<BR>
     * 2�F�폜<BR>
     */
    public String updateProcessFlag;

    /**
     * @@roseuid 47CBC5AE029F
     */
    public WEB3AdminFPTDocumentCommonRequest()
    {

    }

    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * <BR>
     * �P�j�@@�X�V�����t���O�`�F�b�N<BR>
     * <BR>
     * �@@�P-�P�j�@@this.�X�V�����t���O != (0 or 1 or 2) �̏ꍇ�A<BR>
     * �@@�@@�u�X�V�������s���ł��B�v ��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag�@@:�@@BUSINESS_ERROR_03030<BR>
     * <BR>
     * �Q�j�@@���ʍX�V���`�F�b�N<BR>
     * <BR>
     * �@@�Q-�P�j�@@���ʍX�V��� == null�̏ꍇ�A�u�X�V��񂪕s���ł��v��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag�@@:�@@BUSINESS_ERROR_03031<BR>
     * <BR>
     * �@@�Q-�Q�j�@@���ʍX�V���̒�����Loop���s���B�i�C���f�b�N�X�Findex�j<BR>
     * <BR>
     * �@@�@@�Q-�Q-�P�j�@@���ʋ敪�`�F�b�N<BR>
     * <BR>
     * �@@�@@�@@�Q-�Q-�P-�P�j�@@this.���ʍX�V���[index].���ʋ敪 == (null or "")�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@��O�iBUSINESS_ERROR_02948�j���X���[����B<BR>
     * �@@�@@�@@�Q-�Q-�P-�Q�j�@@this.���ʍX�V���[index].���ʋ敪�����p�����ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@��O�iBUSINESS_ERROR_02941�j���X���[����B<BR>
     * �@@�@@�@@�Q-�Q-�P-�R�j�@@this.���ʍX�V���[index].���ʋ敪��4���ȏ� �̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@��O�iBUSINESS_ERROR_02942�j���X���[����B<BR>
     * <BR>
     * �@@�@@�Q-�Q-�Q�j�@@���ʎ�ރR�[�h�`�F�b�N<BR>
     * <BR>
     * �@@�@@�@@�Q-�Q-�Q-�P�j�@@this.���ʍX�V���[index].���ʎ�ރR�[�h == (null or "")�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@��O�iBUSINESS_ERROR_03013�j���X���[����B<BR>
     * �@@�@@�@@�Q-�Q-�Q-�Q�j�@@this.���ʍX�V���[index].���ʎ�ރR�[�h�����p�����ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@��O�iBUSINESS_ERROR_02997�j���X���[����B<BR>
     * �@@�@@�@@�Q-�Q-�Q-�R�j�@@this.���ʍX�V���[index].���ʎ�ރR�[�h��4���ȏ�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@��O�iBUSINESS_ERROR_02997�j���X���[����B<BR>
     * <BR>
     * �@@�@@�Q-�Q-�R�j�@@���ʒʔԃ`�F�b�N<BR>
     * <BR>
     * �@@�@@�@@�Q-�Q-�R-�P�j�@@this.���ʍX�V���[index].���ʒʔ� == (null or "")�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u���ʒʔԂ����ݒ�ł��B�v��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag�@@:�@@BUSINESS_ERROR_03032<BR>
     * �@@�@@�@@�Q-�Q-�R-�Q�j�@@this.���ʍX�V���[index].���ʒʔ� �����p�����ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u���ʒʔԂ��s���ł��B�v��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag�@@:�@@BUSINESS_ERROR_03033<BR>
     * �@@�@@�@@�Q-�Q-�R-�R�j�@@this.���ʍX�V���[index].���ʒʔ� ��5���ȏ�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u���ʒʔԂ��s���ł��B�v��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag�@@:�@@BUSINESS_ERROR_03033<BR>
     * <BR>
     * �@@�@@�Q-�Q-�S�j �E�v�`�F�b�N<BR>
     * <BR>
     * �@@�@@�@@�Q-�Q-�S-�P�j�@@this.���ʍX�V���[index].�E�v != (null or "") ����<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@���p���������݂��Ă���ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�E�v�͑S�p�����œ��͂��Ă��������B�v��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag�@@:�@@BUSINESS_ERROR_03034<BR>
     * �@@�@@�@@�Q-�Q-�S-�Q�j�@@this.���ʍX�V���[index].�E�v != (null or "") ����<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@��������201�o�C�g�ȏ�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�E�v��100�����ȓ��Őݒ肵�Ă��������v��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag�@@:�@@BUSINESS_ERROR_03035<BR>
     * <BR>
     * �@@�@@�Q-�Q-�T�j�@@this.�X�V�����t���O == (0 or 1) �̏ꍇ�A�L���敪�`�F�b�N<BR>
     * <BR>
     * �@@�@@�@@�Q-�Q-�T-�P�j�@@this.���ʍX�V���[index].�L���敪 != (0 or 1) �̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�L���敪���s���ł��B�v��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag�@@:�@@BUSINESS_ERROR_03036<BR>
     * <BR>
     * �@@�@@�Q-�Q-�U�j�@@this.�X�V�����t���O == 1 �̏ꍇ�A�L���敪���`�F�b�N<BR>
     * �@@�@@�@@�@@�@@�@@�@@this.���ʍX�V���̊e�v�f�ɂ����āA<BR>
     * �@@�@@�@@�@@�@@�@@�@@���ʋ敪�Ə��ʎ�ރR�[�h���������v�f���������݂���ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�L���敪 == 0 ��2�ȏ㑶�݂���ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�u�������ʎ�ޒ��ɁA�L���ȏ��ʂ��������݂��܂��B�v��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag�@@:�@@BUSINESS_ERROR_03049<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 47BCF9760172
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        // �X�V�����t���O�`�F�b�N
        //this.�X�V�����t���O != (0 or 1 or 2) �̏ꍇ�A�u�X�V�������s���ł��B�v ��O���X���[����B
        if (!WEB3AdminFPTProcessFlagDivDef.INSERT.equals(this.updateProcessFlag) &&
            !WEB3AdminFPTProcessFlagDivDef.UPDATE.equals(this.updateProcessFlag) &&
            !WEB3AdminFPTProcessFlagDivDef.DELETE.equals(this.updateProcessFlag))
        {
            log.debug("�X�V�������s���ł��B");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03030,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�X�V�������s���ł��B");
        }

        //���ʍX�V���`�F�b�N
        // ���ʍX�V��� == null �̏ꍇ�A�u�X�V��񂪕s���ł��v��O���X���[����B
        if (this.documentUpdateList == null)
        {
            log.debug("�X�V��񂪕s���ł��B");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03031,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�X�V��񂪕s���ł��B");
        }

        //���ʍX�V���̒�����Loop���s���B�i�C���f�b�N�X�Findex�j
        //���ʋ敪�`�F�b�N
        // this.���ʍX�V���[index].���ʋ敪 == (null or "") �̏ꍇ�A
        //      ��O�iBUSINESS_ERROR_02948�j���X���[����B
        // this.���ʍX�V���[index].���ʋ敪�����p�����ȊO �̏ꍇ�A
        //      ��O�iBUSINESS_ERROR_02941�j���X���[����B
        // this.���ʍX�V���[index].���ʋ敪��4���ȏ� �̏ꍇ�A
        //      ��O�iBUSINESS_ERROR_02942�j���X���[����B
        int l_intdocumentUpdateListCnt = this.documentUpdateList.length;
        for (int i = 0; i < l_intdocumentUpdateListCnt; i++)
        {
            if (this.documentUpdateList[i].documentDiv == null || this.documentUpdateList[i].documentDiv == "")
            {
                log.debug("���ʋ敪�����w��ł��B");
                log.exiting(STR_METHOD_NAME);

                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02948,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���ʋ敪�����w��ł��B");
            }

            if (!WEB3StringTypeUtility.isDigit(this.documentUpdateList[i].documentDiv))
            {
                log.debug("���ʋ敪�����p�����ȊO�̒l�ł��B");
                log.exiting(STR_METHOD_NAME);

                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02941,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���ʋ敪�����p�����ȊO�̒l�ł��B");
            }

            if (this.documentUpdateList[i].documentDiv.length() >= 4)
            {
                log.debug("���ʋ敪�̃T�C�Y���s���ł��B");
                log.exiting(STR_METHOD_NAME);

                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02942,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���ʋ敪�̃T�C�Y���s���ł��B");
            }

            //���ʎ�ރR�[�h�`�F�b�N
            // this.���ʍX�V���[index].���ʎ�ރR�[�h == (null or "") �̏ꍇ�A
            //      ��O�iBUSINESS_ERROR_03013�j���X���[����B
            // this.���ʍX�V���[index].���ʎ�ރR�[�h�����p�����ȊO �̏ꍇ�A
            //      ��O�iBUSINESS_ERROR_02997�j���X���[����B
            // this.���ʍX�V���[index].���ʎ�ރR�[�h��4���ȏ� �̏ꍇ�A
            //      ��O�iBUSINESS_ERROR_02997�j���X���[����B
            if (this.documentUpdateList[i].documentCategory == null ||
                this.documentUpdateList[i].documentCategory == "")
            {
                log.debug("���ʎ�ރR�[�h�����w��ł��B");
                log.exiting(STR_METHOD_NAME);

                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03013,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���ʎ�ރR�[�h�����w��ł��B");
            }

            if (!WEB3StringTypeUtility.isDigit(this.documentUpdateList[i].documentCategory))
            {
                log.debug("���ʎ�ރR�[�h���s���ł��B");
                log.exiting(STR_METHOD_NAME);

                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02997,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���ʎ�ރR�[�h���s���ł��B");
            }

            if (this.documentUpdateList[i].documentCategory.length() >= 4)
            {
                log.debug("���ʎ�ރR�[�h���s���ł��B");
                log.exiting(STR_METHOD_NAME);

                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02997,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���ʎ�ރR�[�h���s���ł��B");
            }

            //���ʒʔԃ`�F�b�N
            //this.���ʍX�V���[index].���ʒʔ� == (null or "") �̏ꍇ�A
            //      �u���ʒʔԂ����ݒ�ł��B�v��O���X���[����B
            //this.���ʍX�V���[index].���ʒʔ� �����p�����ȊO �̏ꍇ�A
            //      �u���ʒʔԂ��s���ł��B�v��O���X���[����B
            //this.���ʍX�V���[index].���ʒʔ� ��5���ȏ�̏ꍇ�A
            //      �u���ʒʔԂ��s���ł��B�v��O���X���[����B
            if (this.documentUpdateList[i].documentNumber == null ||
                this.documentUpdateList[i].documentNumber == "")
            {
                log.debug("���ʒʔԂ����ݒ�ł��B");
                log.exiting(STR_METHOD_NAME);

                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03032,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���ʒʔԂ����ݒ�ł��B");
            }

            if (!WEB3StringTypeUtility.isDigit(this.documentUpdateList[i].documentNumber))
            {
                log.debug("���ʒʔԂ��s���ł��B");
                log.exiting(STR_METHOD_NAME);

                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03033,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���ʒʔԂ��s���ł��B");
            }

            if (this.documentUpdateList[i].documentNumber.length() >= 5)
            {
                log.debug("���ʒʔԂ��s���ł��B");
                log.exiting(STR_METHOD_NAME);

                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03033,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���ʒʔԂ��s���ł��B");
            }

            //�E�v�`�F�b�N
            // this.���ʍX�V���[index].�E�v != (null or "") ����
            //  ���p���������݂��Ă���ꍇ�A�u�E�v�͑S�p�����œ��͂��Ă��������B�v��O���X���[����B
            // this.���ʍX�V���[index].�E�v != (null or "") ����
            //  ��������201�o�C�g�ȏ�̏ꍇ�A�u�E�v��100�����ȓ��Őݒ肵�Ă��������v��O���X���[����B
            if (this.documentUpdateList[i].remarks != null && this.documentUpdateList[i].remarks != "")
            {
                if (!WEB3StringTypeUtility.isMulti(this.documentUpdateList[i].remarks))
                {
                    log.debug("�E�v�͑S�p�����œ��͂��Ă��������B");
                    log.exiting(STR_METHOD_NAME);

                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_03034,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "�E�v�͑S�p�����œ��͂��Ă��������B");
                }

                if (this.documentUpdateList[i].remarks.length() > 100)
                {
                    log.debug("�E�v��100�����ȓ��Őݒ肵�Ă��������B");
                    log.exiting(STR_METHOD_NAME);

                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_03035,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "�E�v��100�����ȓ��Őݒ肵�Ă��������B");
                }
            }

            // this.�X�V�����t���O == (0 or 1) �̏ꍇ�A�L���敪�`�F�b�N
            // this.���ʍX�V���[index].�L���敪 != (0 or 1) �̏ꍇ�A�u�L���敪���s���ł��B�v��O���X���[����B
            if (WEB3AdminFPTProcessFlagDivDef.INSERT.equals(this.updateProcessFlag) ||
                WEB3AdminFPTProcessFlagDivDef.UPDATE.equals(this.updateProcessFlag))
            {
                if (!WEB3ValidFlagDef.VALID.equals(this.documentUpdateList[i].validFlag) &&
                    !WEB3ValidFlagDef.INVALID.equals(this.documentUpdateList[i].validFlag))
                {
                    log.debug("�L���敪���s���ł��B");
                    log.exiting(STR_METHOD_NAME);

                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_03036,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "�L���敪���s���ł��B");
                }
            }
        }

        // this.�X�V�����t���O == 1 �̏ꍇ�A�L���敪���`�F�b�N
        //this.���ʍX�V���̊e�v�f�ɂ����āA���ʋ敪�Ə��ʎ�ރR�[�h���������v�f���������݂���ꍇ�A
        //�L���敪 == 0 ��2�ȏ㑶�݂���ꍇ�A�u�������ʎ�ޒ��ɁA�L���ȏ��ʂ��������݂��܂��B�v��O���X���[����B
        if (WEB3AdminFPTProcessFlagDivDef.UPDATE.equals(this.updateProcessFlag))
        {
            for (int i = 0; i < l_intdocumentUpdateListCnt - 1; i++)
            {
                for (int j = i + 1; j < l_intdocumentUpdateListCnt; j++)
                {
                    if (((this.documentUpdateList[i].documentDiv.equals(this.documentUpdateList[j].documentDiv)) && 
                        (this.documentUpdateList[i].documentCategory.equals(this.documentUpdateList[j].documentCategory))) 
                        && (WEB3ValidFlagDef.VALID.equals(this.documentUpdateList[i].validFlag) && 
                            WEB3ValidFlagDef.VALID.equals(this.documentUpdateList[j].validFlag)))
                    {
                        log.debug("�������ʎ�ޒ��ɁA�L���ȏ��ʂ��������݂��܂��B");
                        log.exiting(STR_METHOD_NAME);

                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_03049,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            "�������ʎ�ޒ��ɁA�L���ȏ��ʂ��������݂��܂��B");
                    }
                }
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return null;
    }
}
@
