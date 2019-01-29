head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.52.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformPTSAccountListResultRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �Ǘ��ҁEPTS�\���q�ꗗ�⍇���������ʃ��N�G�X�g�N���X(WEB3AdminInformPTSAccountListResultRequest.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2008/02/28 �Ӑ�(���u) �V�K�쐬 ���f��No.128
*/

package webbroker3.inform.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�Ǘ��ҁEPTS�\���q�ꗗ�⍇���������ʃ��N�G�X�g�N���X)<BR>
 * �Ǘ��ҁEPTS�\���q�ꗗ�⍇���������ʃ��N�G�X�g�N���X<BR>
 * <BR>
 * @@author �Ӑ�
 * @@version 1.0
 */
public class WEB3AdminInformPTSAccountListResultRequest extends WEB3GenRequest
{
    /**
     * (���O�o�̓��[�e�B���e�B)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminInformPTSAccountListResultRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_inform_pts_account_list_result";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200802281637L;

    /**
     * (�v���y�[�W�ԍ�)<BR>
     * �v���y�[�W�ԍ�
     */
    public String pageIndex;

    /**
     * (�y�[�W���\������)<BR>
     * �y�[�W���\������
     */
    public String pageSize;

    /**
     * (�\�[�g�L�[)<BR>
     * �\�[�g�L�[
     */
    public WEB3AdminInformPTSAccountListInqSortKey[] sortKeys;

    /**
     * (��������)<BR>
     * ��������
     */
    public WEB3AdminInformPTSAccountListInqCondition searchCondition;

    /**
     * @@roseuid 47C522D40338
     */
    public WEB3AdminInformPTSAccountListResultRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminInformPTSAccountListResultResponse(this);
    }

    /**
     * �������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@��������<BR>
     * <BR>
     * �@@�P�|�P�j�@@����������null�̏ꍇ�A��O��throw����B<BR>
     * �@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_00945<BR>
     * <BR>
     * <BR>
     * �@@�P�|�Q�j�@@��������.validate()���R�[������B<BR>
     * <BR>
     * �Q�j�@@�\�[�g�L�[<BR>
     * <BR>
     * �@@�Q�|�P�j�@@�\�[�g�L�[��null�̏ꍇ�A��O��throw����B<BR>
     * �@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_00231<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@�\�[�g�L�[�̊e�v�f�ɂ��āA�\�[�g�L�[.validate()���R�[������B<BR>
     * <BR>
     * �R�j�@@�v���y�[�W�ԍ�<BR>
     * <BR>
     * �@@�R�|�P�j�@@�v���y�[�W�ԍ���null�̏ꍇ�A��O��throw����B<BR>
     * �@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_00089<BR>
     * <BR>
     * �@@�R�|�Q�j�@@�v���y�[�W�ԍ����ȉ��̏ꍇ�A��O��throw����B<BR>
     * <BR>
     * �@@�@@�v���y�[�W�ԍ� != ���p���� or<BR>
     * �@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_00090<BR>
     * <BR>
     * �@@�@@�v���y�[�W�ԍ� <= 0<BR>
     * �@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_00616<BR>
     * <BR>
     * �S�j�y�[�W���\������<BR>
     * <BR>
     * �@@�S�|�P�j�@@�y�[�W���\��������null�̏ꍇ�A��O��throw����B<BR>
     * �@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_02224<BR>
     * <BR>
     * �@@�S�|�Q�j�@@�y�[�W���\���������ȉ��̏ꍇ�A��O��throw����B<BR>
     * <BR>
     * �@@�@@�y�[�W���\���s�� != ���p���� or<BR>
     * �@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_00092<BR>
     * <BR>
     * �@@�@@�y�[�W���\���s�� <= 0<BR>
     * �@@�@@�@@class�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag�@@�@@:�@@BUSINESS_ERROR_00617<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 47B5435403E0
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@��������
        //����������null�̏ꍇ�A��O��throw����
        if (this.searchCondition == null)
        {
            log.debug("�������������w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00945,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�������������w��ł��B");
        }

        //��������.validate()���R�[������B
        this.searchCondition.validate();

        //�Q�j�@@�\�[�g�L�[
        //�\�[�g�L�[��null�̏ꍇ�A��O��throw����
        if (sortKeys == null)
        {
            log.debug("�\�[�g�L�[�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�\�[�g�L�[�����w��ł��B");
        }

        //�\�[�g�L�[�̊e�v�f�ɂ��āA�\�[�g�L�[.validate()���R�[������
        for (int i = 0; i < sortKeys.length; i++)
        {
            sortKeys[i].validate();
        }

        //�R�j�@@�v���y�[�W�ԍ�
        //�v���y�[�W�ԍ���null�̏ꍇ�A��O��throw����
        if (pageIndex == null)
        {
            log.debug("�v���y�[�W�ԍ������w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�v���y�[�W�ԍ������w��ł��B");
        }

        //�v���y�[�W�ԍ����ȉ��̏ꍇ�A��O��throw����B
        //�v���y�[�W�ԍ� != ���p����
        if (!WEB3StringTypeUtility.isDigit(pageIndex))
        {
            log.debug("�v���y�[�W�ԍ��������ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�v���y�[�W�ԍ��������ȊO�̒l�ł��B");
        }

        //�v���y�[�W�ԍ� <= 0
        if (Integer.parseInt(pageIndex) <= 0)
        {
            log.debug("�v���y�[�W�ԍ��̒l��0�ȉ��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�v���y�[�W�ԍ��̒l��0�ȉ��ł��B");
        }

        //�S�j�y�[�W���\������
        //�y�[�W���\��������null�̏ꍇ�A��O��throw����B
        if (pageSize == null)
        {
            log.debug("�y�[�W���\���s���������͂ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02224,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�y�[�W���\���s���������͂ł��B");
        }

        //�y�[�W���\���������ȉ��̏ꍇ�A��O��throw����B
        //�y�[�W���\���s�� != ���p����
        if (!WEB3StringTypeUtility.isDigit(pageSize))
        {
            log.debug("�y�[�W���\���s���������ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�y�[�W���\���s���������ȊO�̒l�ł��B");
        }

        //�y�[�W���\���s�� <= 0
        if (Integer.parseInt(pageSize) <= 0)
        {
            log.debug("�y�[�W���\���s���̒l��0�ȉ��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�y�[�W���\���s���̒l��0�ȉ��ł��B");
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
