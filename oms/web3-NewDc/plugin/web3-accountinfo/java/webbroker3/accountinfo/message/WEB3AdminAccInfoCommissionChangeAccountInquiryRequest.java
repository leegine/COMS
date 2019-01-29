head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.01.57.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoCommissionChangeAccountInquiryRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l���萔���ύX�\���ڋq�⍇��ظ���(WEB3AdminAccInfoCommissionChangeAccountInquiryRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10  �d��(���u) �V�K�쐬
*/

package webbroker3.accountinfo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�Ǘ��҂��q�l���萔���ύX�\���ڋq�⍇��ظ���)<BR>
 * �Ǘ��҂��q�l���萔���ύX�\���ڋq�⍇��ظ���<BR>
 * @@author �d��
 * @@version 1.0
 */
public class WEB3AdminAccInfoCommissionChangeAccountInquiryRequest extends WEB3GenRequest
{

    /**
    * Logger
    */
    private static WEB3LogUtility log =
           WEB3LogUtility.getInstance(WEB3AdminAccInfoCommissionChangeAccountInquiryRequest.class);
 
 
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_commissionChangeAccountInquiry";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082144L;

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
     * @@roseuid 418F386A0138
     */
    public WEB3AdminAccInfoCommissionChangeAccountInquiryRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccInfoCommissionChangeAccountInquiryResponse(this);
    }

    /**
     * ���N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * <BR>
     * �P�j�@@���X�R�[�h�̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00833<BR>
     * <BR>
     * �Q�j�@@�ڋq�R�[�h�̃`�F�b�N<BR>
     * �@@�Q�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00835<BR>
     * �@@�Q�|�Q�j�@@������6�łȂ��ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00836<BR>
     * �@@�Q�|�R�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01043<BR>
     * <BR>
     * �R�j�@@�v���y�[�W�ԍ��`�F�b�N <BR>
     * �@@�R�|�P�j�@@�����͂̏ꍇ�A �v���y�[�W�ԍ��Ɂh�P�h���Z�b�g����B <BR>
     * �@@�R�|�Q�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00090<BR>
     * �@@�R�|�R�j�@@�}�C�i�X�l�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00616<BR>
     * <BR>
     * �S�j�@@�y�[�W���\���s���`�F�b�N <BR>
     * �@@�S�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00091<BR>
     * �@@�S�|�Q�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00092<BR>
     * �@@�S�|�R�j�@@�}�C�i�X�l�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00617<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 41513EF7007A
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME="validate()";
        log.entering(STR_METHOD_NAME);
        //* �P�j�@@���X�R�[�h�̃`�F�b�N<BR>
        //* �@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B <BR>
        //*         class: WEB3BusinessLayerException<BR>
        //*         tag:   BUSINESS_ERROR_00833<BR>
        //* <BR>
        if (this.branchCode == null || "".equals(this.branchCode))
        {
            //��O
            log.debug("[���X�R�[�h] = " + branchCode);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                this.getClass().getName() + STR_METHOD_NAME,"���X�R�[�h�̖����͂̏ꍇ");
        }
        //* �Q�j�@@�ڋq�R�[�h�̃`�F�b�N<BR>
        //* �@@�Q�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B <BR>
        //*         class: WEB3BusinessLayerException<BR>
        //*         tag:   BUSINESS_ERROR_00835<BR>
        if (this.accountCode == null || "".equals(this.accountCode))
        {
            //��O
            log.debug("[�ڋq�R�[�h] = " + accountCode);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00835,
                this.getClass().getName() + STR_METHOD_NAME, "�ڋq�R�[�h�̖����͂̏ꍇ");
        }
        
        //* �@@�Q�|�Q�j�@@������6�łȂ��ꍇ�A��O���X���[����B<BR>
        //*         class: WEB3BusinessLayerException<BR>
        //*         tag:   BUSINESS_ERROR_00836<BR>
        if (WEB3StringTypeUtility.getByteLength(this.accountCode) != 6)
        {
            //��O
            log.debug("[�ڋq�R�[�h] = " + accountCode);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00836,
                this.getClass().getName() + STR_METHOD_NAME,
                "������6�łȂ��ꍇ"); 
        }
        //* �@@�Q�|�R�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B<BR>
        //*         class: WEB3BusinessLayerException<BR>
        //*         tag:   BUSINESS_ERROR_01043<BR>
        //* <BR>
        if (!WEB3StringTypeUtility.isDigit(this.accountCode))
        {
            //��O
            log.debug("[�ڋq�R�[�h] = " + accountCode);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01043,
                this.getClass().getName() + STR_METHOD_NAME, "�����ȊO�̕������܂܂��ꍇ");
        }
        //* �R�j�@@�v���y�[�W�ԍ��`�F�b�N <BR>
        //* �@@�R�|�P�j�@@�����͂̏ꍇ�A �v���y�[�W�ԍ��Ɂh�P�h���Z�b�g����B <BR>
        if (this.pageIndex == null || "".equals(this.pageIndex))
        {
            log.debug("[�v���y�[�W�ԍ�] = " + pageIndex);
            this.pageIndex = "1";
        }
        //* �@@�R�|�Q�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B <BR>
        //*         class: WEB3BusinessLayerException<BR>
        //*         tag:   BUSINESS_ERROR_00090<BR>
        if (!WEB3StringTypeUtility.isInteger(this.pageIndex))
        {
           //��O
           log.debug("[�v���y�[�W�ԍ�] = " + pageIndex);
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_00090,
               this.getClass().getName() + STR_METHOD_NAME, "�v���y�[�W�ԍ������ȊO�̕������܂܂��ꍇ");
        }
        //* �@@�R�|�R�j�@@�}�C�i�X�l�̏ꍇ�A��O���X���[����B<BR>
        //*         class: WEB3BusinessLayerException<BR>
        //*         tag:   BUSINESS_ERROR_00616<BR>
        //* <BR>
        double l_dblPageIndex = Double.parseDouble(this.pageIndex);
        if (l_dblPageIndex <= 0)
        {
            //��O
            log.debug("[�v���y�[�W�ԍ�] = " + pageIndex);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                this.getClass().getName() + STR_METHOD_NAME, "�v���y�[�W�ԍ��}�C�i�X�l�̏ꍇ");        
        }
        //* �S�j�@@�y�[�W���\���s���`�F�b�N <BR>
        //* �@@�S�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B <BR>
        //*         class: WEB3BusinessLayerException<BR>
        //*         tag:   BUSINESS_ERROR_00091<BR>
        if (this.pageSize == null || "".equals(this.pageSize))
        {
           //��O
           log.debug("[�y�[�W���\���s��] = " + pageSize);
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_00091,
               this.getClass().getName() + STR_METHOD_NAME, "�y�[�W���\���s�������͂̏ꍇ");
        }
        //* �@@�S�|�Q�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B <BR>
        //*         class: WEB3BusinessLayerException<BR>
        //*         tag:   BUSINESS_ERROR_00092<BR>
        if (!WEB3StringTypeUtility.isInteger(this.pageSize))
        {
           //��O
           log.debug("[�y�[�W���\���s��] = " + pageSize);
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_00092,
               this.getClass().getName() + STR_METHOD_NAME, "�y�[�W���\�������ȊO�̕������܂܂��ꍇ");
        }
        //* �@@�S�|�R�j�@@�}�C�i�X�l�̏ꍇ�A��O���X���[����B<BR>
        //*         class: WEB3BusinessLayerException<BR>
        //*         tag:   BUSINESS_ERROR_00617<BR>
        //* <BR>
        double l_dblPageSize = Double.parseDouble(this.pageSize);
        if (l_dblPageSize <= 0)
        {
            //��O
            log.debug("[�y�[�W���\���s��] = " + pageSize);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName() + STR_METHOD_NAME, "�y�[�W���\���}�C�i�X�l�̏ꍇ");        
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
