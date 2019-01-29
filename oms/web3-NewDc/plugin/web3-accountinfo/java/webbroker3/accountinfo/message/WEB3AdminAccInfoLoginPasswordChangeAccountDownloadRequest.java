head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.08.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoLoginPasswordChangeAccountDownloadRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l����߽ܰ�ޕύX�ڋq�޳�۰��ظ���(WEB3AdminAccInfoLoginPasswordChangeAccountDownloadRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/15 ����� (���u) �V�K�쐬
*/

package webbroker3.accountinfo.message;

import java.util.Date;

import webbroker3.accountinfo.define.WEB3AccInfoKeyItemDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�Ǘ��҂��q�l����߽ܰ�ޕύX�ڋq�޳�۰��ظ���)<BR>
 * �Ǘ��҂��q�l����߽ܰ�ޕύX�ڋq�޳�۰��ظ���<BR>
 *
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3AdminAccInfoLoginPasswordChangeAccountDownloadRequest extends WEB3GenRequest
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoLoginPasswordChangeAccountDownloadRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_loginPasswordChangeAccountDownload";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082126L;

    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     */
    public String[] branchCode;

    /**
     * (�J�n��)<BR>
     * �J�n���i���j<BR>
     */
    public Date startDate;

    /**
     * (�I����)<BR>
     * �I�����i���j<BR>
     */
    public Date endDate;

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
    public WEB3AccInfoSortKey[] sortKeys;

    /**
     * @@roseuid 418F3854035B
     */
    public WEB3AdminAccInfoLoginPasswordChangeAccountDownloadRequest()
    {

    }
    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccInfoLoginPasswordChangeAccountDownloadResponse(this);
    }

    /**
     * (validate)<BR>
     * ���N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * <BR>
     * �P�j�@@���X�R�[�h�̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00833<BR>
     * <BR>
     * �Q�j�@@�J�n���̃`�F�b�N<BR>
     * �@@�Q�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01149<BR>
     * <BR>
     * �R�j�@@�I�����̃`�F�b�N<BR>
     * �@@�R�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01150<BR>
     * �@@�R�|�Q�j�@@�i�J�n�� > �I�����j�̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01151<BR>
     * <BR>
     * �S�j�@@�v���y�[�W�ԍ��`�F�b�N <BR>
     * �@@�S�|�P�j�@@�����͂̏ꍇ�A �v���y�[�W�ԍ��Ɂh�P�h���Z�b�g����B <BR>
     * �@@�S�|�Q�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00090<BR>
     * �@@�S�|�R�j�@@�}�C�i�X�l�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00616<BR>
     * <BR>
     * �T�j�@@�y�[�W���\���s���`�F�b�N <BR>
     * �@@�T�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00091<BR>
     * �@@�T�|�Q�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00092<BR>
     * �@@�T�|�R�j�@@�}�C�i�X�l�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00617<BR>
     * <BR>
     * �U�j�@@�\�[�g�L�[�̃`�F�b�N  <BR>
     * �@@�U�|�P�j�@@�\�[�g�L�[��������l�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00231<BR>
     * �@@�U�|�Q�j�@@�i�\�[�g�L�[�̗v�f�� == 0�j�̏ꍇ�A ��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00813<BR>
     * �@@�U�|�R�j�@@�\�[�g�L�[�̗v�f�����A���L�̃`�F�b�N���J��Ԃ��čs���B  <BR>
     * �@@�@@�@@�U�|�R�|�P�j�@@�\�[�g�L�[.validate()���R�[������B <BR>
     * �@@�@@�@@�U�|�R�|�Q�j�@@�\�[�g�L�[.�L�[���ڂ����L�̍��ږ��ȊO�̏ꍇ�A <BR>
     * ��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01152<BR>
     * �@@�@@�@@�@@ �p�X���[�h�ύX�ڋq���.���X�R�[�h<BR>
     * �@@�@@�@@�@@ �p�X���[�h�ύX�ڋq�����.�ڋq�R�[�h<BR>
     * �@@�@@�@@�@@ �p�X���[�h�ύX�ڋq�����.�X�V��<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4147D58300B7
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@���X�R�[�h�̃`�F�b�N
        //�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        if (this.branchCode == null || branchCode.length == 0)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                this.getClass().getName() + STR_METHOD_NAME,
                " ���X�R�[�h�����w��ł�");
        }

        //�Q�j�@@�J�n���̃`�F�b�N
        //�Q�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        if (this.startDate == null)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01149,
                this.getClass().getName() + STR_METHOD_NAME,
                " �J�n������͂��܂���");
        }

        //�R�j�@@�I�����̃`�F�b�N
        //�R�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        if (this.endDate == null)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01150,
                this.getClass().getName() + STR_METHOD_NAME,
                " �I��������͂��܂���");
        }

        //�R�|�Q�j�@@�i�J�n�� > �I�����j�̏ꍇ�A��O���X���[����B
        if (WEB3DateUtility.compareToDay(this.startDate, this.endDate) > 0)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01151,
                this.getClass().getName() + STR_METHOD_NAME,
                " �i�J�n�� > �I�����j�ł�");
        }

        //�S�j�@@�v���y�[�W�ԍ��`�F�b�N
        //�S�|�P�j�@@�����͂̏ꍇ�A �v���y�[�W�ԍ��Ɂh�P�h���Z�b�g����B
        if (this.pageIndex == null || "".equals(this.pageIndex))
        {
            this.pageIndex = "1";
        }

        //�S�|�Q�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B
        if (!WEB3StringTypeUtility.isInteger(this.pageIndex))
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + STR_METHOD_NAME,
                " �v���y�[�W�ԍ��������ȊO�̒l�ł�");
        }

        //�S�|�R�j�@@�}�C�i�X�l�̏ꍇ�A��O���X���[����B
        int l_lngPageIndex = Integer.parseInt(this.pageIndex);
        if (l_lngPageIndex <= 0)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                this.getClass().getName() + STR_METHOD_NAME,
                " �v���y�[�W�ԍ��̒l��0�ȉ��ł�");
        }

        //�T�j�@@�y�[�W���\���s���`�F�b�N
        //�T�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        if (this.pageSize == null || "".equals(this.pageSize))
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                this.getClass().getName() + STR_METHOD_NAME,
                " �y�[�W���\���s���̓��͂��s���ł�");
        }

        //�T�|�Q�j�@@�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B
        if (!WEB3StringTypeUtility.isInteger(this.pageSize))
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + STR_METHOD_NAME,
                " �y�[�W���\���s���������ȊO�̒l�ł�");
        }

        //�T�|�R�j�@@�}�C�i�X�l�̏ꍇ�A��O���X���[����B
        int l_lngPageSize = Integer.parseInt(this.pageSize);
        if (l_lngPageSize <= 0)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName() + STR_METHOD_NAME,
                " �y�[�W���\���s���̒l��0�ȉ��ł�");
        }

        //�U�j�@@�\�[�g�L�[�̃`�F�b�N
        //�U�|�P�j�@@�\�[�g�L�[��������l�̏ꍇ�A��O���X���[����B
        if (this.sortKeys == null)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + STR_METHOD_NAME,
                " �\�[�g�L�[�����w��ł�");
        }

        //�U�|�Q�j�@@�i�\�[�g�L�[�̗v�f�� == 0�j�̏ꍇ�A ��O���X���[����B
        if (this.sortKeys.length == 0)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                this.getClass().getName() + STR_METHOD_NAME,
                " �\�[�g�L�[�̗v�f�����O�ł�");
        }

        //�U�|�R�j�@@�\�[�g�L�[�̗v�f�����A���L�̃`�F�b�N���J��Ԃ��čs���B
        for (int i = 0; i < this.sortKeys.length; i++)
        {

            //�U�|�R�|�P�j�@@�\�[�g�L�[.validate()���R�[������B
            this.sortKeys[i].validate();

            //�U�|�R�|�Q�j�@@�\�[�g�L�[.�L�[���ڂ����L�̍��ږ��ȊO�̏ꍇ�A ��O���X���[����B
            //�p�X���[�h�ύX�ڋq���.���X�R�[�h
            //�p�X���[�h�ύX�ڋq�����.�ڋq�R�[�h
            //�p�X���[�h�ύX�ڋq�����.�X�V��

            if (!WEB3AccInfoKeyItemDef.BRANCH_CODE.equals(this.sortKeys[i].keyItem)
                && !WEB3AccInfoKeyItemDef.ACCOUNT_CODE.equals(this.sortKeys[i].keyItem)
                && !WEB3AccInfoKeyItemDef.UPDATED_DATE.equals(this.sortKeys[i].keyItem))
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    this.getClass().getName() + STR_METHOD_NAME,
                    " �\�[�g�L�[�̃L�[���ڂ̒l�����݂��Ȃ��R�[�h�l�ł�");
            }
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
