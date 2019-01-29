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
filename	WEB3AdminFPTDocumentListReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҋ����@@��t���ʏƉ�ꗗ���N�G�X�g(WEB3AdminFPTDocumentListReferenceRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/03 �g�C�� (���u) �V�K�쐬 ���f��No.037
*/
package webbroker3.docadmin.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�Ǘ��ҋ����@@��t���ʏƉ�ꗗ���N�G�X�g)<BR>
 * �Ǘ��ҋ����@@��t���ʏƉ�ꗗ���N�G�X�g�N���X<BR>
 *
 * @@author �g�C��
 * @@version 1.0
 */
public class WEB3AdminFPTDocumentListReferenceRequest extends WEB3GenRequest
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTDocumentListReferenceRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_fpt_document_list_reference";

    /**
     * SerialVersionUID<BR>
     */
    private static final long serialVersionUID = 200803031745L;

    /**
     * (���ʋ敪�Ǘ��ꗗ)<BR>
     * ���ʋ敪�Ǘ��ꗗ<BR>
     */
    public WEB3FPTDocumentDivAdminInfoUnit[] documentDivList;

    /**
     * (�\�[�g�L�[)<BR>
     * �\�[�g�L�[<BR>
     * <BR>
     * �L�[���ڂ͈ȉ��̂Ƃ���<BR>
     * <BR>
     * �E�d�q�������R�[�h<BR>
     * �E�L���敪<BR>
     */
    public WEB3AdminFPTSortKey[] sortKeys;

    /**
     * @@roseuid 47CBC5AE0251
     */
    public WEB3AdminFPTDocumentListReferenceRequest()
    {

    }

    /**
     * ���N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * <BR>
     * <BR>
     * �P�j�@@���ʋ敪�Ǘ��ꗗ�`�F�b�N(���ʋ敪�Ǘ��ꗗ�z��S�v�f�ɑ΂��ă`�F�b�N���s��)<BR>
     * <BR>
     * �@@�P-�P�j�@@this.���ʋ敪�Ǘ����ꗗ == null�̏ꍇ�A<BR>
     * �@@�@@��O�iBUSINESS_ERROR_03007�j���X���[����B<BR>
     * <BR>
     * �@@�P-�Q�j�@@this.���ʋ敪�Ǘ����ꗗ[index].���ʋ敪 == (null or "")�̏ꍇ�A<BR>
     * �@@�@@��O�iBUSINESS_ERROR_02948�j���X���[����B<BR>
     * �@@�P-�R�j�@@this.���ʋ敪�Ǘ����ꗗ[index].���ʋ敪�����p�����ȊO �̏ꍇ�A<BR>
     * �@@�@@��O�iBUSINESS_ERROR_02941�j���X���[����B<BR>
     * �@@�P-�S�j�@@this.���ʋ敪�Ǘ����ꗗ[index].���ʋ敪��4���ȏ� �̏ꍇ�A<BR>
     * �@@�@@��O�iBUSINESS_ERROR_02942�j���X���[����B<BR>
     * <BR>
     * �@@�P-�T�j�@@this.���ʋ敪�Ǘ����ꗗ[index].���ʎ�ރR�[�h == (null or "")�̏ꍇ�A<BR>
     * �@@�@@��O�iBUSINESS_ERROR_03013�j���X���[����B<BR>
     * �@@�P-�U�j�@@this.���ʋ敪�Ǘ����ꗗ[index].���ʎ�ރR�[�h�����p�����ȊO�̏ꍇ�A<BR>
     * �@@�@@��O�iBUSINESS_ERROR_02997�j���X���[����B<BR>
     * �@@�P-�V�j�@@this.���ʋ敪�Ǘ����ꗗ[index].���ʎ�ރR�[�h��4���ȏ� �̏ꍇ�A<BR>
     * �@@�@@��O�iBUSINESS_ERROR_02997�j���X���[����B<BR>
     * <BR>
     * <BR>
     * �Q�j�@@�\�[�g�L�[�`�F�b�N <BR>
     * <BR>
     * �@@�@@this.�\�[�g�L�[ = null or<BR>
     * �@@�@@this.�\�[�g�L�[.length() = 0�̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 47BD21D4018E
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //�@@this.���ʋ敪�Ǘ����ꗗ == null
        // �̏ꍇ�A��O�iBUSINESS_ERROR_03007�j���X���[����B
        if (this.documentDivList == null)
        {
            log.debug("���ʋ敪�Ǘ���񂪖��w��ł��B");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03007,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���ʋ敪�Ǘ���񂪖��w��ł��B");
        }

        int l_intdocumentDivListCnt = this.documentDivList.length;
        for (int i = 0; i < l_intdocumentDivListCnt; i++)
        {
            // this.���ʋ敪�Ǘ����ꗗ[index].���ʋ敪 == (null or "") �̏ꍇ�A
            //��O�iBUSINESS_ERROR_02948�j���X���[����B
            if (this.documentDivList[i].documentDiv == null || this.documentDivList[i].documentDiv == "")
            {
                log.debug("���ʋ敪�����w��ł��B");
                log.exiting(STR_METHOD_NAME);

                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02948,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���ʋ敪�����w��ł��B");
            }

            //this.���ʋ敪�Ǘ����ꗗ[index].���ʋ敪�����p�����ȊO �̏ꍇ�A
            //��O�iBUSINESS_ERROR_02941�j���X���[����B
            if (!WEB3StringTypeUtility.isDigit(this.documentDivList[i].documentDiv))
            {
                log.debug("���ʋ敪�����p�����ȊO�̒l�ł��B");
                log.exiting(STR_METHOD_NAME);

                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02941,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���ʋ敪�����p�����ȊO�̒l�ł��B");
            }

            //this.���ʋ敪�Ǘ����ꗗ[index].���ʋ敪��4���ȏ� �̏ꍇ�A
            //��O�iBUSINESS_ERROR_02942�j���X���[����B

            if (this.documentDivList[i].documentDiv.length() >= 4)
            {
                log.debug("���ʋ敪�̃T�C�Y���s���ł��B");
                log.exiting(STR_METHOD_NAME);

                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02942,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���ʋ敪�̃T�C�Y���s���ł��B");
            }

            //this.���ʋ敪�Ǘ����ꗗ[index].���ʎ�ރR�[�h == (null or "") �̏ꍇ�A
            //��O�iBUSINESS_ERROR_03013�j���X���[����B
            if (this.documentDivList[i].documentCategory == null || this.documentDivList[i].documentCategory == "")
            {
                log.debug("���ʎ�ރR�[�h�����w��ł��B");
                log.exiting(STR_METHOD_NAME);

                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03013,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���ʎ�ރR�[�h�����w��ł��B");
            }

            //this.���ʋ敪�Ǘ����ꗗ[index].���ʎ�ރR�[�h�����p�����ȊO �̏ꍇ�A
            //��O�iBUSINESS_ERROR_02997�j���X���[����B
            if (!WEB3StringTypeUtility.isDigit(this.documentDivList[i].documentCategory))
            {
                log.debug("���ʎ�ރR�[�h���s���ł��B");
                log.exiting(STR_METHOD_NAME);

                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02997,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���ʎ�ރR�[�h���s���ł��B");
            }

            // this.���ʋ敪�Ǘ����ꗗ[index].���ʎ�ރR�[�h��4���ȏ� �̏ꍇ�A
            //��O�iBUSINESS_ERROR_02997�j���X���[����B
            if (this.documentDivList[i].documentCategory.length() >= 4)
            {
                log.debug("���ʎ�ރR�[�h���s���ł��B");
                log.exiting(STR_METHOD_NAME);

                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02997,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���ʎ�ރR�[�h���s���ł��B");
            }
        }

        //�\�[�g�L�[�`�F�b�N
        //this.�\�[�g�L�[ = null or
        //this.�\�[�g�L�[.length() = 0
        //�̏ꍇ�A��O���X���[����B
        if (this.sortKeys == null || this.sortKeys.length == 0)
        {
            log.debug("�\�[�g�L�[�����w��ł��B");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�\�[�g�L�[�����w��ł��B");
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
        return new WEB3AdminFPTDocumentListReferenceResponse(this);
    }
}
@
