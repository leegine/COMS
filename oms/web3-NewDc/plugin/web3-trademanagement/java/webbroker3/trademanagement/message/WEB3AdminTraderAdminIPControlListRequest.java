head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.20.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTraderAdminIPControlListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE���O�C������IP�ꗗ���N�G�X�g(WEB3AdminTraderAdminIPControlListRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/09/23 �����F (���u) �V�K�쐬 ���f��004
*/

package webbroker3.trademanagement.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�Ǘ��ҁE���O�C������IP�ꗗ���N�G�X�g)<BR>
 * �Ǘ��ҁE���O�C������IP�ꗗ���N�G�X�g�N���X�B<BR>
 * @@author �����F
 * @@version 1.0
 */
public class WEB3AdminTraderAdminIPControlListRequest extends WEB3GenRequest
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTraderAdminIPControlListRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_trader_admin_ip_control_list";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200809221750L;

    /**
     * (�y�[�W���\���s��)<BR>
     * �y�[�W���\���s���B<BR>
     */
    public String pageSize;

    /**
     * (�v���y�[�W�ԍ�)<BR>
     * �v���y�[�W�ԍ��B<BR>
     */
    public String pageIndex;

    /**
     * (�\�[�g�L�[)<BR>
     */
    public WEB3AdminTraderAdminIPControlSortKey[] sortKeys;

    /**
     * @@roseuid 48D75CD60398
     */
    public WEB3AdminTraderAdminIPControlListRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�y�[�W���\���s���`�F�b�N<BR>
     * �@@�P�|�P�jthis.�y�[�W���\���s�� == null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�y�[�W���\���s���̓��͂��s���ł��B�v�̗�O���X���[����B<BR>
     * �@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag : BUSINESS_ERROR_00091<BR>
     * <BR>
     * �@@�P�|�Q�jthis.�y�[�W���\���s�������p�����ȊO�̒l�ł������ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�y�[�W���\���s���������ȊO�̒l�ł��B�v�̗�O���X���[����B<BR>
     * �@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag : BUSINESS_ERROR_00092<BR>
     * <BR>
     * �@@�P�|�R�jthis.�y�[�W���\���s�� <= 0�ł������ꍇ�A<BR>
     * �@@�@@�@@�@@�u�y�[�W���\���s���̒l��0�ȉ��ł��B�v�̗�O���X���[����B<BR>
     * �@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag : BUSINESS_ERROR_00617<BR>
     * <BR>
     * �Q�j�v���y�[�W�ԍ��`�F�b�N<BR>
     * �@@�Q�|�P�jthis.�v���y�[�W�ԍ� == null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�v���y�[�W�ԍ������w��ł��B�v�̗�O���X���[����B<BR>
     * �@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag : BUSINESS_ERROR_00089<BR>
     * <BR>
     * �@@�Q�|�Q�jthis.�v���y�[�W�ԍ������p�����ȊO�̒l�ł������ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�v���y�[�W�ԍ��������ȊO�̒l�ł��B�v�̗�O���X���[����B<BR>
     * �@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag : BUSINESS_ERROR_00090<BR>
     * <BR>
     * �@@�Q�|�R�jthis.�v���y�[�W�ԍ� <= 0�ł������ꍇ�A<BR>
     * �@@�@@�@@�@@�u�v���y�[�W�ԍ��̒l��0�ȉ��ł��B�v�̗�O���X���[����B<BR>
     * �@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag : BUSINESS_ERROR_00616<BR>
     * <BR>
     * �R�j�@@�\�[�g�L�[�`�F�b�N<BR>
     * �@@�R�|�P�jthis.�\�[�g�L�[ == null�ł������ꍇ<BR>
     * �@@�@@�@@�@@�u�\�[�g�L�[��null�v�̗�O���X���[����B<BR>
     * �@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag : BUSINESS_ERROR_00231<BR>
     * <BR>
     * �@@�R�|�Q�jthis.�\�[�g�L�[.length == 0�������ꍇ<BR>
     * �@@�@@�@@�@@�u�\�[�g�L�[.�v�f����0�v�̗�O���X���[����B<BR>
     * �@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag : BUSINESS_ERROR_00232<BR>
     * <BR>
     * �@@�R�|�R�jthis.�\�[�g�L�[�̑S�v�f�ɑ΂��ĉ��L�̃`�F�b�N���s���B<BR>
     * �@@�@@�R�|�R�|�P�j�\�[�g�L�[.validate()���R�[������B<BR>
     * @@roseuid 48BE52B003A4
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //�P�j�y�[�W���\���s���`�F�b�N
        //�@@�P�|�P�jthis.�y�[�W���\���s�� == null�̏ꍇ�A
        //�@@�@@�@@�@@�@@�u�y�[�W���\���s���̓��͂��s���ł��B�v�̗�O���X���[����B
        if (this.pageSize == null)
        {
            log.debug("�y�[�W���\���s���̓��͂��s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�y�[�W���\���s���̓��͂��s���ł��B");
        }

        //�@@�P�|�Q�jthis.�y�[�W���\���s�������p�����ȊO�̒l�ł������ꍇ�A
        //�@@�@@�@@�@@�@@�u�y�[�W���\���s���������ȊO�̒l�ł��B�v�̗�O���X���[����B
        if (!WEB3StringTypeUtility.isInteger(this.pageSize))
        {
            log.debug("�y�[�W���\���s���������ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�y�[�W���\���s���������ȊO�̒l�ł��B");
        }

        //�@@�P�|�R�jthis.�y�[�W���\���s�� <= 0�ł������ꍇ�A
        //�@@�@@�@@�@@�u�y�[�W���\���s���̒l��0�ȉ��ł��B�v�̗�O���X���[����B
        if (Integer.parseInt(this.pageSize) <= 0)
        {
            log.debug("�y�[�W���\���s���̒l��0�ȉ��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�y�[�W���\���s���̒l��0�ȉ��ł��B");
        }

        //�Q�j�v���y�[�W�ԍ��`�F�b�N
        //�@@�Q�|�P�jthis.�v���y�[�W�ԍ� == null�̏ꍇ�A
        //�@@�@@�@@�@@�@@�u�v���y�[�W�ԍ������w��ł��B�v�̗�O���X���[����B
        if (this.pageIndex == null)
        {
            log.debug("�v���y�[�W�ԍ������w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�v���y�[�W�ԍ������w��ł��B");
        }

        //�@@�Q�|�Q�jthis.�v���y�[�W�ԍ������p�����ȊO�̒l�ł������ꍇ�A
        //�@@�@@�@@�@@�@@�u�v���y�[�W�ԍ��������ȊO�̒l�ł��B�v�̗�O���X���[����B
        if (!WEB3StringTypeUtility.isInteger(this.pageIndex))
        {
            log.debug("�v���y�[�W�ԍ��������ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�v���y�[�W�ԍ��������ȊO�̒l�ł��B");
        }

        //�@@�Q�|�R�jthis.�v���y�[�W�ԍ� <= 0�ł������ꍇ�A
        //�@@�@@�@@�@@�u�v���y�[�W�ԍ��̒l��0�ȉ��ł��B�v�̗�O���X���[����B
        if (Integer.parseInt(this.pageIndex) <= 0)
        {
            log.debug("�v���y�[�W�ԍ��̒l��0�ȉ��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�v���y�[�W�ԍ��̒l��0�ȉ��ł��B");
        }

        //�R�j�@@�\�[�g�L�[�`�F�b�N
        //�@@�R�|�P�jthis.�\�[�g�L�[ == null�ł������ꍇ
        //�@@�@@�@@�@@�u�\�[�g�L�[��null�v�̗�O���X���[����B
        if (this.sortKeys == null)
        {
            log.debug("�\�[�g�L�[��null");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�\�[�g�L�[�����w��ł��B");
        }

        //�@@�R�|�Q�jthis.�\�[�g�L�[.length == 0�������ꍇ
        //�@@�@@�@@�@@�u�\�[�g�L�[.�v�f����0�v�̗�O���X���[����B
        if (this.sortKeys.length == 0)
        {
            log.debug("�\�[�g�L�[.�v�f����0");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�\�[�g�L�[�̗v�f�����O�ł��B");
        }

        //�@@�R�|�R�jthis.�\�[�g�L�[�̑S�v�f�ɑ΂��ĉ��L�̃`�F�b�N���s���B
        //�@@�@@�R�|�R�|�P�j�\�[�g�L�[.validate()���R�[������B
        int l_intLength = this.sortKeys.length;
        for (int i = 0; i < l_intLength; i++)
        {
            this.sortKeys[i].validate();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * ���X�|���X�f�[�^���쐬����B<BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminTraderAdminIPControlListResponse(this);
    }
}
@
