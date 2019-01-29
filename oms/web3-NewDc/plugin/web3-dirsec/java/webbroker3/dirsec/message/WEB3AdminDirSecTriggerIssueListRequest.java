head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.13.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecTriggerIssueListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�g���K�[���s�����ꗗ���N�G�X�g(WEB3AdminDirSecTriggerIssueListRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/04/17 ���O(���u) �V�K�쐬 ���f��No.116�ANo.118�ANo.122�ANo.123
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
 * (�Ǘ��ҁE�g���K�[���s�����ꗗ���N�G�X�g)<BR>
 * �Ǘ��ҁE�g���K�[���s�����ꗗ���N�G�X�g�N���X�B<BR>
 * <BR>
 * @@author ���O
 * @@version 1.0
 */
public class WEB3AdminDirSecTriggerIssueListRequest extends WEB3GenRequest
{
    /**
     * ���O�o�̓��[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminDirSecTriggerIssueListRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_dir_sec_trigger_issue_list";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200804171518L;

    /**
     * (�y�[�W���\���s��)<BR>
     * �g���K�[���sJOB�ꗗ��ʂŕ\������s����ێ��B<BR>
     */
    public String pageSize;

    /**
     * (�\���y�[�W�ԍ�)<BR>
     * �\���y�[�W�ԍ��B<BR>
     */
    public String pageIndex;

    /**
     * (�\�[�g�L�[)<BR>
     */
    public WEB3AdminDirSecTriggerIssueSortKey[] sortKeys;

    /**
     * @@roseuid 4806E0540031
     */
    public WEB3AdminDirSecTriggerIssueListRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�y�[�W���\���s���`�F�b�N<BR>
     * �@@�P�|�P�jthis.�y�[�W���\���s�� == null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�y�[�W���\���s���̓��͂��s���ł��B�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_00091<BR>
     * <BR>
     * �@@�P�|�Q�jthis.�y�[�W���\���s���������ȊO�̒l�ł������ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�y�[�W���\���s���������ȊO�̒l�ł��B�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_00092<BR>
     * <BR>
     * �@@�P�|�R�jthis.�y�[�W���\���s�� <= 0�ł������ꍇ�A<BR>
     * �@@�@@�@@�@@�u�y�[�W���\���s���̒l��0�ȉ��ł��B�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_00617<BR>
     * <BR>
     * �Q�j�\���ԍ��`�F�b�N<BR>
     * �@@�Q�|�P�jthis.�\���y�[�W�ԍ� == null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�v���y�[�W�ԍ������w��ł��B�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_00089<BR>
     * <BR>
     * �@@�Q�|�Q�jthis.�\���y�[�W�ԍ��������ȊO�̒l�ł������ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�v���y�[�W�ԍ��������ȊO�̒l�ł��B�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_00090<BR>
     * <BR>
     * �@@�Q�|�R�jthis.�\���y�[�W�ԍ� <= 0�ł������ꍇ�A<BR>
     * �@@�@@�@@�@@�u�v���y�[�W�ԍ��̒l��0�ȉ��ł��B�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_00616<BR>
     * <BR>
     * �R�j�@@�\�[�g�L�[�`�F�b�N<BR>
     * �@@�R�|�P�jthis.�g���K�[���s�\�[�g�L�[ == null�ł������ꍇ<BR>
     * �@@�@@�@@�@@�u�\�[�g�L�[��null�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_00231<BR>
     * <BR>
     * �@@�R�|�Q�jthis.�g���K�[���s�\�[�g�L�[.length == 0�������ꍇ<BR>
     * �@@�@@�@@�@@�u�\�[�g�L�[.�v�f����0�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_00232<BR>
     * <BR>
     * �@@�R�|�R�jthis.�g���K�[���s�\�[�g�L�[�̑S�v�f�ɑ΂���<BR>
     * �@@�@@�@@�@@���L�̃`�F�b�N���s���B<BR>
     * �@@�@@�R�|�R�|�P�j�g���K�[���s�\�[�g�L�[.validate()���R�[������B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47CFAA6601F3
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //�y�[�W���\���s���`�F�b�N
        //this.�y�[�W���\���s�� == null�̏ꍇ�A
        //�u�y�[�W���\���s���̓��͂��s���ł��B�v�̗�O���X���[����B
        if (this.pageSize == null)
        {
            log.debug("�y�[�W���\���s���̓��͂��s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�y�[�W���\���s���̓��͂��s���ł��B");
        }

        //this.�y�[�W���\���s���������ȊO�̒l�ł������ꍇ�A
        //�u�y�[�W���\���s���������ȊO�̒l�ł��B�v�̗�O���X���[����B
        else if (!(WEB3StringTypeUtility.isInteger(this.pageSize)))
        {
            log.debug("�y�[�W���\���s���������ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�y�[�W���\���s���������ȊO�̒l�ł��B");
        }

        //this.�y�[�W���\���s�� <= 0�ł������ꍇ�A
        //�u�y�[�W���\���s���̒l��0�ȉ��ł��B�v�̗�O���X���[����B
        else if (Integer.parseInt(this.pageSize) <= 0)
        {
            log.debug("�y�[�W���\���s���̒l��0�ȉ��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�y�[�W���\���s���̒l��0�ȉ��ł��B");
        }

        //�\���ԍ��`�F�b�N
        //this.�\���y�[�W�ԍ� == null�̏ꍇ�A
        //�u�v���y�[�W�ԍ������w��ł��B�v�̗�O���X���[����B
        if (this.pageIndex == null)
        {
            log.debug("�v���y�[�W�ԍ������w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�v���y�[�W�ԍ������w��ł��B");
        }

        //this.�\���y�[�W�ԍ��������ȊO�̒l�ł������ꍇ�A
        //�u�v���y�[�W�ԍ��������ȊO�̒l�ł��B�v�̗�O���X���[����B
        else if (!(WEB3StringTypeUtility.isInteger(this.pageIndex)))
        {
            log.debug("�v���y�[�W�ԍ��������ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�v���y�[�W�ԍ��������ȊO�̒l�ł��B");
        }

        //this.�\���y�[�W�ԍ� <= 0�ł������ꍇ�A
        //�u�v���y�[�W�ԍ��̒l��0�ȉ��ł��B�v�̗�O���X���[����B
        else if (Integer.parseInt(this.pageIndex) <= 0)
        {
            log.debug("�v���y�[�W�ԍ��̒l��0�ȉ��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�v���y�[�W�ԍ��̒l��0�ȉ��ł��B");
        }

        //�\�[�g�L�[�`�F�b�N
        //this.�g���K�[���s�\�[�g�L�[ == null�ł������ꍇ
        //�u�\�[�g�L�[��null�v�̗�O���X���[����B
        if (this.sortKeys == null)
        {
            log.debug("�\�[�g�L�[�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�\�[�g�L�[�����w��ł��B");
        }

        //this.�g���K�[���s�\�[�g�L�[.length == 0�������ꍇ
        //�u�\�[�g�L�[.�v�f����0�v�̗�O���X���[����B

        else if (this.sortKeys.length == 0)
        {
            log.debug("�\�[�g�L�[�̗v�f�����O�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�\�[�g�L�[�̗v�f�����O�ł��B");
        }

        //this.�g���K�[���s�\�[�g�L�[�̑S�v�f�ɑ΂���
        //���L�̃`�F�b�N���s���B
        //�g���K�[���s�\�[�g�L�[.validate()���R�[������B
        else
        {
            int l_intLength = this.sortKeys.length;
            for (int i = 0; i < l_intLength; i++)
            {
                this.sortKeys[i].validate();
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
        return new WEB3AdminDirSecTriggerIssueListResponse(this);
    }
}
@
