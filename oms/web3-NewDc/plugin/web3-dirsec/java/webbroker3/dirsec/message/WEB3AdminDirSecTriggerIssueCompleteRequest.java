head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.10.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecTriggerIssueCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�g���K�[���s�����������N�G�X�g(WEB3AdminDirSecTriggerIssueCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/04/17 ���O(���u) �V�K�쐬 ���f��No.116�ANo.118�ANo.120
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
 * (�Ǘ��ҁE�g���K�[���s�����������N�G�X�g)<BR>
 * �Ǘ��ҁE�g���K�[���s�����������N�G�X�g�N���X�B<BR>
 * <BR>
 * @@author ���O
 * @@version 1.0
 */
public class WEB3AdminDirSecTriggerIssueCompleteRequest extends WEB3GenRequest
{
    /**
     * ���O�o�̓��[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminDirSecTriggerIssueCompleteRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_dir_sec_trigger_issue_complete";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200804171357L;

    /**
     * (�g���K�[���s���)<BR>
     * �u�g���K�[���s���e�[�u���v����擾�����g���K�[���sJOB���R�[�h��ێ�����B<BR>
     */
    public WEB3AdminDirSecTriggerIssueRecordDetail triggerIssueInfo;

    /**
     * (�Ïؔԍ�)<BR>
     * ��ʂ�����͂����Ïؔԍ���ێ��B<BR>
     */
    public String password;

    /**
     * @@roseuid 4806E0540179
     */
    public WEB3AdminDirSecTriggerIssueCompleteRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�g���K�[���s���R�[�h�ڍ� != null �̏ꍇ�ɁA���L�̏������s���B<BR>
     * <BR>
     * �@@�P�|�P�j�V�F�����̃`�F�b�N<BR>
     * �@@�@@�@@�@@�@@�g���K�[���s���R�[�h�ڍ�.�V�F������ == null or<BR>
     * �@@�@@�@@�@@�@@�g���K�[���s���R�[�h�ڍ�.�V�F������ == "" �̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@��O���X���[����B<BR>
     * �@@�@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_03071<BR>
     * <BR>
     * �@@�P�|�Q�j�g���K�[���̃`�F�b�N<BR>
     * �@@�@@�@@�@@�@@�g���K�[���s���R�[�h�ڍ�.�g���K�[���� == null or<BR>
     * �@@�@@�@@�@@�@@�g���K�[���s���R�[�h�ڍ�.�g���K�[���� == "" �̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@��O���X���[����B<BR>
     * �@@�@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_03072<BR>
     * <BR>
     * �@@�P�|�R�j�Ĕ��s�\�t���O�`�F�b�N<BR>
     * �@@�@@�@@�@@�@@�g���K�[���s���R�[�h�ڍ�.�Ĕ��s�\�t���O == null or<BR>
     * �@@�@@�@@�@@�@@�g���K�[���s���R�[�h�ڍ�.�Ĕ��s�\�t���O == "" �̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@��O���X���[����B<BR>
     * �@@�@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_03073<BR>
     * <BR>
     * �@@�P�|�S�j���[�U�[�f�[�^�`�F�b�N<BR>
     * �@@�@@�@@�@@�@@�g���K�[���s���R�[�h�ڍ�.���[�U�[�f�[�^ == "" �̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@��O���X���[����B<BR>
     * �@@�@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_03074<BR>
     * <BR>
     * �@@�P�|�T�j�f�[�^�R�[�h�`�F�b�N<BR>
     * �@@�@@�@@�@@�@@�g���K�[���s���R�[�h�ڍ�.�f�[�^�R�[�h == null or<BR>
     * �@@�@@�@@�@@�@@�g���K�[���s���R�[�h�ڍ�.�f�[�^�R�[�h == "" �̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@��O���X���[����B<BR>
     * �@@�@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_02828<BR>
     * <BR>
     * �Q�j�g���K�[���s���R�[�h�ڍ� == null �̏ꍇ�A<BR>
     * �@@�@@�u���R�[�h�����݂��܂���B�v��O���X���[����B<BR>
     * �@@�@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_02837<BR>
     * <BR>
     * �R�j�@@�Ïؔԍ��`�F�b�N<BR>
     * �@@�R-�P�j�@@this.�Ïؔԍ� == null or this.�Ïؔԍ� == ""�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�Ïؔԍ����s���ł��B�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_02832<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47CFAB3401DD
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //�g���K�[���s���R�[�h�ڍ� != null �̏ꍇ�ɁA���L�̏������s���B
        if (this.triggerIssueInfo != null)
        {
            //�V�F�����̃`�F�b�N
            //�g���K�[���s���R�[�h�ڍ�.�V�F������ == null or
            //�g���K�[���s���R�[�h�ڍ�.�V�F������ == "" �̏ꍇ�A
            //��O���X���[����B
            if (WEB3StringTypeUtility.isEmpty(this.triggerIssueInfo.shellName))
            {
                log.debug("�V�F�����̂����w��ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03071,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�V�F�����̂����w��ł��B");
            }

            //�g���K�[���̃`�F�b�N
            //�g���K�[���s���R�[�h�ڍ�.�g���K�[���� == null or
            //�g���K�[���s���R�[�h�ڍ�.�g���K�[���� == "" �̏ꍇ�A
            //��O���X���[����B
            if (WEB3StringTypeUtility.isEmpty(this.triggerIssueInfo.triggerName))
            {
                log.debug("�g���K�[���̂����w��ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03072,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�g���K�[���̂����w��ł��B");
            }

            //�Ĕ��s�\�t���O�`�F�b�N
            //�g���K�[���s���R�[�h�ڍ�.�Ĕ��s�\�t���O == null or
            //�g���K�[���s���R�[�h�ڍ�.�Ĕ��s�\�t���O == "" �̏ꍇ�A
            //��O���X���[����B
            if (WEB3StringTypeUtility.isEmpty(this.triggerIssueInfo.reissuePossibleFlag))
            {
                log.debug("�Ĕ��s�\�t���O�����w��ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03073,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�Ĕ��s�\�t���O�����w��ł��B");
            }

            //���[�U�[�f�[�^�`�F�b�N
            //�g���K�[���s���R�[�h�ڍ�.���[�U�[�f�[�^ == "" �̏ꍇ�A
            //��O���X���[����B
            if ("".equals(this.triggerIssueInfo.userData))
            {
                log.debug("���[�U�[�f�[�^�����w��ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03074,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���[�U�[�f�[�^�����w��ł��B");
            }

            //�f�[�^�R�[�h�`�F�b�N
            //�g���K�[���s���R�[�h�ڍ�.�f�[�^�R�[�h == null or
            //�g���K�[���s���R�[�h�ڍ�.�f�[�^�R�[�h == "" �̏ꍇ�A
            //��O���X���[����B
            if (WEB3StringTypeUtility.isEmpty(this.triggerIssueInfo.dataCode))
            {
                log.debug("�f�[�^�R�[�h���s���ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02828,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�f�[�^�R�[�h���s���ł��B");
            }

        }

        //�g���K�[���s���R�[�h�ڍ� == null �̏ꍇ�A
        //�u���R�[�h�����݂��܂���B�v��O���X���[����B
        if (this.triggerIssueInfo == null)
        {
            log.debug("���R�[�h�����݂��܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02837,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���R�[�h�����݂��܂���B");
        }

        //�Ïؔԍ��`�F�b�N
        //this.�Ïؔԍ� == null or this.�Ïؔԍ� == ""�̏ꍇ�A
        //�u�Ïؔԍ����s���ł��B�v�̗�O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.password))
        {
            log.debug("�Ïؔԍ����s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02832,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�Ïؔԍ����s���ł��B");
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
        return new WEB3AdminDirSecTriggerIssueCompleteResponse(this);
    }
}
@
