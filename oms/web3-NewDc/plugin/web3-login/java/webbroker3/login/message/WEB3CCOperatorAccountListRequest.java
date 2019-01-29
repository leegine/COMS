head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.25.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3CCOperatorAccountListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : CC�I�y���[�^�Ώیڋq�ꗗ���N�G�X�g(WEB3CCOperatorAccountListRequest.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/07/23 ���n�m (���u) �V�K�쐬�E���f��No.039
*/

package webbroker3.login.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.login.define.WEB3TraderAccountInfosSortKeyDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (CC�I�y���[�^�Ώیڋq�ꗗ���N�G�X�g)<BR>
 * CC�I�y���[�^�Ώیڋq�ꗗ���N�G�X�g�N���X<BR>
 *
 * @@author ���n�m
 * @@version 1.0
 */
public class WEB3CCOperatorAccountListRequest extends WEB3GenRequest
{

    /**
     * (���O�o�̓��[�e�B���e�B)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3CCOperatorAccountListRequest.class);

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200707231120L;

    /**
     * TAGNAME<BR>
     */
    public static final String TAGNAME = "request";

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "cc_operator_account_list";

    /**
     * (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h
     */
    public String acceptCode;

    /**
     * (�����O(����))<BR>
     * �����O(����)
     */
    public String nameKanji;

    /**
     * (�����O(�J�i))<BR>
     * �����O(�J�i)
     */
    public String nameKana;

    /**
     * (�v���y�[�W�ԍ�)<BR>
     * �v���y�[�W�ԍ�
     */
    public String pageIndex;

    /**
     * (�y�[�W���\���s��)<BR>
     * �y�[�W���\���s��
     */
    public String pageSize;

    /**
     * (�\�[�g�L�[)<BR>
     * �Ώیڋq�\�[�g�L�[�I�u�W�F�N�g�̔z��
     */
    public WEB3TraderAccountInfosSortKey[] sortKeys;

    /**
     * (CC�I�y���[�^�Ώیڋq�ꗗ���N�G�X�g)<BR>
     * �R���X�g���N�^<BR>
     */
    public WEB3CCOperatorAccountListRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�\�[�g�L�[�`�F�b�N<BR>
     * �@@�P�|�P�jthis.�\�[�g�L�[ == null�ł������ꍇ<BR>
     * �@@�@@�@@�@@�u�\�[�g�L�[��null�v�̗�O���X���[����B<BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag�@@:�@@BUSINESS_ERROR_00231<BR>
     * <BR>
     * �@@�P�|�Q�jthis.�\�[�g�L�[.length == 0�������ꍇ<BR>
     * �@@�@@�@@�@@�u�\�[�g�L�[.�v�f����0�v�̗�O���X���[����B<BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag�@@:�@@BUSINESS_ERROR_00232<BR>
     * <BR>
     * �@@�P�|�R�jthis.�\�[�g�L�[�̑S�v�f�ɑ΂���<BR>
     * �@@�@@�@@�@@���L�̃`�F�b�N���s���B<BR>
     * �@@�@@�P�|�R�|�P�j�\�[�g�L�[.validate()���R�[������B<BR>
     * <BR>
     * �@@�@@�P�|�R�|�Q�j�\�[�g�L�[.�L�[���ڂɉ��L�̍��ڈȊO���ݒ肳��Ă�����A<BR>
     * �@@�@@�@@�u�\�[�g�L�[.�L�[���ڂ�����`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�E�u�ڋq�R�[�h�v<BR>
     * �@@�@@�E�u���O�v<BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag�@@:�@@BUSINESS_ERROR_00086<BR>
     * <BR>
     * �Q�j�v���y�[�W�ԍ��`�F�b�N<BR>
     * �@@�Q�|�P�jthis.�v���y�[�W�ԍ� == null�ł������ꍇ�A<BR>
     * �@@�@@�@@�@@�u�v���y�[�W�ԍ���null�v�̗�O���X���[����B<BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag�@@:�@@BUSINESS_ERROR_00089<BR>
     * <BR>
     * �@@�Q�|�Q�jthis.�v���y�[�W�ԍ��������ȊO�̒l�ł������ꍇ�A<BR>
     * �@@�@@�@@�@@�u�v���y�[�W�ԍ��������ȊO�v�̗�O���X���[����B<BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag�@@:�@@BUSINESS_ERROR_00090<BR>
     * <BR>
     * �@@�Q�|�R�jthis.�v���y�[�W�ԍ� �� 0�ł������ꍇ�A<BR>
     * �@@�@@�@@�@@�u�v���y�[�W�ԍ���0�ȉ��v�̗�O���X���[����B<BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag�@@:�@@BUSINESS_ERROR_00616<BR>
     * <BR>
     * �R�j�y�[�W���\���s���`�F�b�N<BR>
     * �@@�R�|�P�jthis.�y�[�W���\���s�� == null�ł������ꍇ�A<BR>
     * �@@�@@�@@�@@�u�y�[�W���\���s����null�v�̗�O���X���[����B<BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag�@@:�@@BUSINESS_ERROR_02224<BR>
     * <BR>
     * �@@�R�|�Q�jthis.�y�[�W���\���s���������ȊO�̒l�ł������ꍇ�A<BR>
     * �@@�@@�@@�@@�u�y�[�W���\���s���������ȊO�v�̗�O���X���[����B<BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag�@@:�@@BUSINESS_ERROR_00092<BR>
     * <BR>
     * �@@�R�|�R�jthis.�y�[�W���\���s�� �� 0�ł������ꍇ�A<BR>
     * �@@�@@�@@�@@�u'�y�[�W���\���s���̒l��0�ȉ��ł��B�v�̗�O���X���[����B<BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag�@@:�@@BUSINESS_ERROR_00617<BR>
     * <BR>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        // �P�j�\�[�g�L�[�`�F�b�N
        // �@@�P�|�P�jthis.�\�[�g�L�[ == null�ł������ꍇ
        // �@@�@@�@@�@@�u�\�[�g�L�[��null�v�̗�O���X���[����B
        if (this.sortKeys == null)
        {
            log.debug("�\�[�g�L�[�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + STR_METHOD_NAME,
                "�\�[�g�L�[�����w��ł��B");
        }

        // �@@�P�|�Q�jthis.�\�[�g�L�[.length == 0�������ꍇ
        // �@@�@@�@@�@@�u�\�[�g�L�[.�v�f����0�v�̗�O���X���[����B
        int l_intSortKeysLen = this.sortKeys.length;
        if (l_intSortKeysLen == 0)
        {
            log.debug("�\�[�g�L�[�̗v�f�����O�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                this.getClass().getName() + STR_METHOD_NAME,
                "�\�[�g�L�[�̗v�f�����O�ł��B");
        }

        // �@@�P�|�R�jthis.�\�[�g�L�[�̑S�v�f�ɑ΂���
        // �@@�@@�@@�@@���L�̃`�F�b�N���s���B
        for (int i = 0 ; i < l_intSortKeysLen; i++)
        {
            // �@@�@@�P�|�R�|�P�j�\�[�g�L�[.validate()���R�[������B
            sortKeys[i].validate();

            // �@@�@@�P�|�R�|�Q�j�\�[�g�L�[.�L�[���ڂɉ��L�̍��ڈȊO��
            // �@@�@@�@@�ݒ肳��Ă�����A
            // �@@�@@�@@�u�\�[�g�L�[.�L�[���ڂ�����`�̒l�v�̗�O���X���[����B
            // �@@�@@�E�u�ڋq�R�[�h�v
            // �@@�@@�E�u���O�v
            if (!WEB3TraderAccountInfosSortKeyDef.ACCEPT_CODE.equals(sortKeys[i].keyItem)
                && !WEB3TraderAccountInfosSortKeyDef.NAME_KANA.equals(sortKeys[i].keyItem))
            {
                log.debug("�\�[�g�L�[�̃L�[���ڂ̒l�����݂��Ȃ��R�[�h�l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�\�[�g�L�[�̃L�[���ڂ̒l�����݂��Ȃ��R�[�h�l�ł��B");
            }
        }

        // �Q�j�v���y�[�W�ԍ��`�F�b�N
        // �@@�Q�|�P�jthis.�v���y�[�W�ԍ� == null�ł������ꍇ�A
        // �@@�@@�@@�@@�u�v���y�[�W�ԍ���null�v�̗�O���X���[����B
        if (this.pageIndex == null)
        {
            log.debug("�v���y�[�W�ԍ������w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName() + STR_METHOD_NAME,
                "�v���y�[�W�ԍ������w��ł��B");
        }

        // �@@�Q�|�Q�jthis.�v���y�[�W�ԍ��������ȊO�̒l�ł������ꍇ�A
        // �@@�@@�@@�@@�u�v���y�[�W�ԍ��������ȊO�v�̗�O���X���[����B
        if (!WEB3StringTypeUtility.isInteger(this.pageIndex))
        {
            log.debug("�v���y�[�W�ԍ��������ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + STR_METHOD_NAME,
                "�v���y�[�W�ԍ��������ȊO�̒l�ł��B");
        }

        // �@@�Q�|�R�jthis.�v���y�[�W�ԍ� �� 0�ł������ꍇ�A
        // �@@�@@�@@�@@�u�v���y�[�W�ԍ���0�ȉ��v�̗�O���X���[����B
        if (Integer.parseInt(this.pageIndex) <= 0)
        {
            log.debug("�v���y�[�W�ԍ��̒l��0�ȉ��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                this.getClass().getName() + STR_METHOD_NAME,
                "�v���y�[�W�ԍ��̒l��0�ȉ��ł��B");
        }

        // �R�j�y�[�W���\���s���`�F�b�N
        // �@@�R�|�P�jthis.�y�[�W���\���s�� == null�ł������ꍇ�A
        // �@@�@@�@@�@@�u�y�[�W���\���s����null�v�̗�O���X���[����B
        if (this.pageSize == null)
        {
            log.debug("�y�[�W���\���s���������͂ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02224,
                this.getClass().getName() + STR_METHOD_NAME,
                "�y�[�W���\���s���������͂ł��B");
        }

        // �@@�R�|�Q�jthis.�y�[�W���\���s���������ȊO�̒l�ł������ꍇ�A
        // �@@�@@�@@�@@�u�y�[�W���\���s���������ȊO�v�̗�O���X���[����B
        if (!WEB3StringTypeUtility.isInteger(this.pageSize))
        {
            log.debug("�y�[�W���\���s���������ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + STR_METHOD_NAME,
                "�y�[�W���\���s���������ȊO�̒l�ł��B");
        }

        // �@@�R�|�R�jthis.�y�[�W���\���s�� �� 0�ł������ꍇ�A
        // �@@�@@�@@�@@�u'�y�[�W���\���s���̒l��0�ȉ��ł��B�v�̗�O���X���[����B
        if (Integer.parseInt(this.pageSize) <= 0)
        {
            log.debug("�y�[�W���\���s���̒l��0�ȉ��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName() + STR_METHOD_NAME,
                "�y�[�W���\���s���̒l��0�ȉ��ł��B");
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3CCOperatorAccountListResponse(this);
    }

}
@
