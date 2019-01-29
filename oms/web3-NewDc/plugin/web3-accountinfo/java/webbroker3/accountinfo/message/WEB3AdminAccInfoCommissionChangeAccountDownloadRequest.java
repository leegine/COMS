head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.02.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoCommissionChangeAccountDownloadRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l���萔���ύX�\���ڋq�޳�۰��ظ���(WEB3AdminAccInfoCommissionChangeAccountDownloadRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10  �d��(���u) �V�K�쐬
*/

package webbroker3.accountinfo.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�Ǘ��҂��q�l���萔���ύX�\���ڋq�޳�۰��ظ���)<BR>
 * �Ǘ��҂��q�l���萔���ύX�\���ڋq�޳�۰��ظ���<BR>
 * @@author �d��
 * @@version 1.0 
 */
public class WEB3AdminAccInfoCommissionChangeAccountDownloadRequest extends WEB3GenRequest
{
    /**
    * Logger
    */
    private static WEB3LogUtility log =
           WEB3LogUtility.getInstance(WEB3AdminAccInfoCommissionChangeAccountDownloadRequest.class);
 

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_commissionChangeAccountDownload";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082147L;

    /**
     * (�K�p�J�n��)<BR>
     * �K�p�J�n��<BR>
     */
    public Date trialStartDate;

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
     * @@roseuid 418F3864037A
     */
    public WEB3AdminAccInfoCommissionChangeAccountDownloadRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccInfoCommissionChangeAccountDownloadResponse(this);
    }

    /**
     * ���N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * <BR>
     * �P�j�@@�K�p�J�n���̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00837<BR>
     * <BR>
     * �Q�j�@@�v���y�[�W�ԍ��`�F�b�N <BR>
     * �@@�Q�|�P�j�@@�����͂̏ꍇ�A �v���y�[�W�ԍ��Ɂh�P�h���Z�b�g����B <BR>
     * �@@�Q�|�Q�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00090<BR>
     * �@@�Q�|�R�j�@@�}�C�i�X�l�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00616<BR>
     * <BR>
     * �R�j�@@�y�[�W���\���s���`�F�b�N <BR>
     * �@@�R�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00091<BR>
     * �@@�R�|�Q�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00092<BR>
     * �@@�R�|�R�j�@@�}�C�i�X�l�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00617<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4150D9CC004D
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME="validate()";
        log.entering(STR_METHOD_NAME);
        //* �P�j�@@�K�p�J�n���̃`�F�b�N<BR>
        //* �@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B <BR>
        //*         class: WEB3BusinessLayerException<BR>
        //*         tag:   BUSINESS_ERROR_00837<BR>
        //* <BR>
        if (this.trialStartDate == null)
        {
            //��O
            log.debug("[�K�p�J�n��] = " + trialStartDate);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00837,
                this.getClass().getName() + STR_METHOD_NAME, "�K�p�J�n���̖����͂̏ꍇ");
        }
        
        //* �Q�j�@@�v���y�[�W�ԍ��`�F�b�N <BR>
        //* �@@�Q�|�P�j�@@�����͂̏ꍇ�A �v���y�[�W�ԍ��Ɂh�P�h���Z�b�g����B <BR>
        if (this.pageIndex == null || "".equals(this.pageIndex))
        {
            log.debug("[�v���y�[�W�ԍ�] = " + pageIndex);
            this.pageIndex = "1"; 
        }
        //* �@@�Q�|�Q�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B <BR>
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
        //* �@@�Q�|�R�j�@@�}�C�i�X�l�̏ꍇ�A��O���X���[����B<BR>
        //*         class: WEB3BusinessLayerException<BR>
        //*         tag:   BUSINESS_ERROR_00616<BR>
        //* <BR>
        int l_dblPageIndex = Integer.parseInt(this.pageIndex);
        if (l_dblPageIndex < 0)
        {
            log.debug("[�v���y�[�W�ԍ�] = " + pageIndex);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                    this.getClass().getName() + STR_METHOD_NAME,"�v���y�[�W�ԍ��}�C�i�X�l�̏ꍇ");
        }
        //* �R�j�@@�y�[�W���\���s���`�F�b�N <BR>
        //* �@@�R�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B <BR>
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
        //* �@@�R�|�Q�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B <BR>
        //*         class: WEB3BusinessLayerException<BR>
        //*         tag:   BUSINESS_ERROR_00092<BR>
        if (!WEB3StringTypeUtility.isInteger(this.pageSize))
        {
           //��O
           log.debug("[�y�[�W���\���s��] = " + pageSize);;
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_00092,
               this.getClass().getName() + STR_METHOD_NAME, "�y�[�W���\���s�������ȊO�̕������܂܂��ꍇ");
        }
        //* �@@�R�|�R�j�@@�}�C�i�X�l�̏ꍇ�A��O���X���[����B<BR>
        //*         class: WEB3BusinessLayerException<BR>
        //*         tag:   BUSINESS_ERROR_00617<BR>
        double l_dblPageSize = Double.parseDouble(this.pageSize);
        if (l_dblPageSize <= 0)
        {
            //��O
            log.debug("[�y�[�W���\���s��] = " + pageSize);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName() + STR_METHOD_NAME, "�y�[�W���\���s���}�C�i�X�l�̏ꍇ");        
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
