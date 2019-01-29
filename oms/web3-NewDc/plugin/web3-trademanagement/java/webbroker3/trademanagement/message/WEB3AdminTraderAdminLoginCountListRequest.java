head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.22.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTraderAdminLoginCountListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁEIP�ʃ��O�C���񐔈ꗗ�������ʃ��N�G�X�g(WEB3AdminTraderAdminLoginCountListRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/09/23 �И��� (���u) �V�K�쐬 ���f��No.005,007,010
*/

package webbroker3.trademanagement.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�Ǘ��ҁEIP�ʃ��O�C���񐔈ꗗ�������ʃ��N�G�X�g)<BR>
 * �Ǘ��ҁEIP�ʃ��O�C���񐔈ꗗ�������ʃ��N�G�X�g�N���X�B<BR>
 * <BR>
 * @@author �И���
 * @@version 1.0
 */
public class WEB3AdminTraderAdminLoginCountListRequest extends WEB3GenRequest
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTraderAdminLoginCountListRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_trader_admin_login_count_list";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200809221758L;

    /**
     * (���t)<BR>
     * ���t�B('yyyymmdd' �`���œ���)<BR>
     */
    public String searchDate;

    /**
     * (����(��))<BR>
     * ����(��)�B('hh24mi'�`���œ���)<BR>
     */
    public String startTime;

    /**
     * (����(��))<BR>
     * ����(��)�B('hh24mi'�`���œ���)<BR>
     */
    public String endTime;

    /**
     * (�����N)<BR>
     * ���O�C�������񐔂̏��ʁB<BR>
     */
    public String rank;

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
     * @@roseuid 48D75CD6026F
     */
    public WEB3AdminTraderAdminLoginCountListRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j���t�`�F�b�N<BR>
     * �@@�P�|�P�jthis.���t == null�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�u�I�y���[�V�������t�����w��ł��B�v<BR>
     *           (BUSINESS_ERROR_01272)�̗�O���X���[����B<BR>
     * <BR>
     * �@@�P�|�Q�jthis.���t�̓��͌`���� 'yyyymmdd' �ł͂Ȃ��ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�u���t���s���ł��B�v<BR>
     *           (BUSINESS_ERROR_02994)�̗�O���X���[����B<BR>
     * <BR>
     * �Q�j����(��)�`�F�b�N<BR>
     * �@@�Q�|�P�jthis.����(��) == null�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�u����(��)��null�ł��B�v�̗�O���X���[����B<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag: BUSINESS_ERROR_03118<BR>
     * <BR>
     * �@@�Q�|�Q�jthis.����(��)�̓��͌`���� 'hh24mi' �ł͂Ȃ��ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�u�\�����ԁi���j���t�t�H�[�}�b�g�G���[�B�v<BR>
     *           (BUSINESS_ERROR_01065)�̗�O���X���[����B<BR>
     * <BR>
     * �R�j����(��)�`�F�b�N<BR>
     * �@@�R�|�P�jthis.����(��) == null�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�u����(��)��null�ł��B�v�̗�O���X���[����B<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag: BUSINESS_ERROR_03119<BR>
     * <BR>
     * �@@�R�|�Q�jthis.����(��)�̓��͌`���� 'hh24mi' �ł͂Ȃ��ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�u�\�����ԁi���j���t�t�H�[�}�b�g�G���[�B�v<BR>
     *           (BUSINESS_ERROR_01066)�̗�O���X���[����B<BR>
     * <BR>
     * �S�j���͎��Ԑ������`�F�b�N<BR>
     * �@@�S�|�P�jthis.����(��) > this.����(��) �̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�u�\�����ԁi���j�͕\�����ԁi���j���傫���ł��B�v<BR>
     * �@@�@@�@@�@@�@@(BUSINESS_ERROR_01051)�̗�O���X���[����B<BR>
     * <BR>
     * �@@�S�|�Q�j [this.����(��) - this.����(��)] > �P���� �̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�u�w��͈͂͂P���Ԉȓ��œ��͂��Ă��������B�v�̗�O���X���[����B<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag: BUSINESS_ERROR_03128<BR>
     * <BR>
     * �T�j�����N�`�F�b�N<BR>
     * �@@�T�|�P�jthis.�����N == null�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�u�����N�������͂ł��B�v�̗�O���X���[����B<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag: BUSINESS_ERROR_03129<BR>
     * <BR>
     * �@@�T�|�Q�jthis.�����N �ɔ��p�����ȊO�̕����������Ă����ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�u�����N�͔��p�����œ��͂��Ă��������B�v�̗�O���X���[����B<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag: BUSINESS_ERROR_03130<BR>
     * <BR>
     * �@@�T�|�R�jthis.�����N > 30 �̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�u�����N�͏��30�ʂ܂ł̕\�������ł��܂���B�v�̗�O���X���[����B<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag: BUSINESS_ERROR_03131<BR>
     * <BR>
     * �U�j�y�[�W���\���s���`�F�b�N <BR>
     * �@@�U�|�P�jthis.�y�[�W���\���s�� == null�̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�u�y�[�W���\���s���̓��͂��s���ł��B�v�̗�O���X���[����B <BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag: BUSINESS_ERROR_00091<BR>
     * <BR>
     *�@@�U�|�Q�jthis.�y�[�W���\���s�������p�����ȊO�̒l�ł������ꍇ�A <BR>
     *�@@�@@�@@�@@�@@�u�y�[�W���\���s���������ȊO�̒l�ł��B�v�̗�O���X���[����B <BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag: BUSINESS_ERROR_00092<BR>
     * <BR>
     *�@@�U�|�R�jthis.�y�[�W���\���s�� <= 0�ł������ꍇ�A <BR>
     *�@@�@@�@@�@@�@@�u�y�[�W���\���s���̒l��0�ȉ��ł��B�v�̗�O���X���[����B <BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag: BUSINESS_ERROR_00617<BR>
     * <BR>
     * �V�j�v���y�[�W�ԍ��`�F�b�N <BR>
     *�@@�V�|�P�jthis.�v���y�[�W�ԍ� == null�̏ꍇ�A <BR>
     *�@@�@@�@@�@@�@@�u�v���y�[�W�ԍ������w��ł��B�v�̗�O���X���[����B <BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag: BUSINESS_ERROR_00089<BR>
     * <BR>
     *�@@�V�|�Q�jthis.�v���y�[�W�ԍ������p�����ȊO�̒l�ł������ꍇ�A <BR>
     *�@@�@@�@@�@@�@@�u�v���y�[�W�ԍ��������ȊO�̒l�ł��B�v�̗�O���X���[����B <BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag: BUSINESS_ERROR_00090<BR>
     * <BR>
     *�@@�V�|�R�jthis.�v���y�[�W�ԍ� <= 0�ł������ꍇ�A <BR>
     *�@@�@@�@@�@@�@@�u�v���y�[�W�ԍ��̒l��0�ȉ��ł��B�v�̗�O���X���[����B<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag: BUSINESS_ERROR_00616<BR>
     * @@roseuid 48BCCEB20088
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        //�P�j���t�`�F�b�N
        //�P�|�P�jthis.���t == null�̏ꍇ�́A
        //�u�I�y���[�V�������t�����w��ł��B�v
        //(BUSINESS_ERROR_01272)�̗�O���X���[����B
        if (this.searchDate == null)
        {
            log.debug("�I�y���[�V�������t���w�肳��Ă��܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01272,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�I�y���[�V�������t���w�肳��Ă��܂���B");
        }

        //�P�|�Q�jthis.���t�̓��͌`���� 'yyyymmdd' �ł͂Ȃ��ꍇ�́A
        //�u���t���s���ł��B�v
        //(BUSINESS_ERROR_02994)�̗�O���X���[����B
        if (!WEB3StringTypeUtility.isDateStr(this.searchDate, WEB3GentradeTimeDef.DATE_FORMAT_YMD))
        {
            log.debug("���t���s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02994,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���t���s���ł��B");
        }

        //�Q�j����(��)�`�F�b�N
        //�Q�|�P�jthis.����(��) == null�̏ꍇ�́A
        //�u����(��)��null�ł��B�v�̗�O���X���[����B
        if (this.startTime == null)
        {
            log.debug("����(��)��null�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03118,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����(��)��null�ł��B");
        }

        //�Q�|�Q�jthis.����(��)�̓��͌`���� 'hh24mi' �ł͂Ȃ��ꍇ�́A
        //�u�\�����ԁi���j���t�t�H�[�}�b�g�G���[�B�v
        //(BUSINESS_ERROR_01065)�̗�O���X���[����B
        if (!WEB3StringTypeUtility.isDateStr(this.startTime, WEB3GentradeTimeDef.TIME_FORMAT_HM))
        {
            log.debug("�\�����ԁi���j���t�t�H�[�}�b�g�G���[�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01065,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�\�����ԁi���j���t�t�H�[�}�b�g�G���[�B");
        }

        //�R�j����(��)�`�F�b�N
        //�R�|�P�jthis.����(��) == null�̏ꍇ�́A
        //�u����(��)��null�ł��B�v�̗�O���X���[����B
        if (this.endTime == null)
        {
            log.debug("����(��)��null�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03119,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����(��)��null�ł��B");
        }

        //�R�|�Q�jthis.����(��)�̓��͌`���� 'hh24mi' �ł͂Ȃ��ꍇ�́A
        //�u�\�����ԁi���j���t�t�H�[�}�b�g�G���[�B�v
        //(BUSINESS_ERROR_01066)�̗�O���X���[����B
        if (!WEB3StringTypeUtility.isDateStr(this.endTime, WEB3GentradeTimeDef.TIME_FORMAT_HM))
        {
            log.debug("�\�����ԁi���j���t�t�H�[�}�b�g�G���[�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01066,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�\�����ԁi���j���t�t�H�[�}�b�g�G���[�B");
        }

        //�S�j���͎��Ԑ������`�F�b�N
        //�S�|�P�jthis.����(��) > this.����(��) �̏ꍇ�́A
        //�u�\�����ԁi���j�͕\�����ԁi���j���傫���ł��B�v
        //(BUSINESS_ERROR_01051)�̗�O���X���[����B
        Date l_datStartTime = WEB3DateUtility.getDate(this.startTime, WEB3GentradeTimeDef.TIME_FORMAT_HM);
        Date l_datEndTime = WEB3DateUtility.getDate(this.endTime, WEB3GentradeTimeDef.TIME_FORMAT_HM);
        if (WEB3DateUtility.compareToMinute(l_datStartTime, l_datEndTime) > 0)
        {
            log.debug("�\�����ԁi���j�͕\�����ԁi���j���傫���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01051,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�\�����ԁi���j�͕\�����ԁi���j���傫���ł��B");
        }

        //�S�|�Q�j [this.����(��) - this.����(��)] > �P���� �̏ꍇ�́A
        //�u�w��͈͂͂P���Ԉȓ��œ��͂��Ă��������B�v�̗�O���X���[����B
        if (WEB3DateUtility.compareToMinute(WEB3DateUtility.addHour(l_datEndTime, -1), l_datStartTime) > 0)
        {
            log.debug("�w��͈͂͂P���Ԉȓ��œ��͂��Ă��������B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03128,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�w��͈͂͂P���Ԉȓ��œ��͂��Ă��������B");
        }

        //�T�j�����N�`�F�b�N
        //�T�|�P�jthis.�����N == null�̏ꍇ�́A
        //�u�����N�������͂ł��B�v�̗�O���X���[����B
        if (this.rank == null)
        {
            log.debug("�����N�������͂ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03129,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����N�������͂ł��B");
        }

        //�T�|�Q�jthis.�����N �ɔ��p�����ȊO�̕����������Ă����ꍇ�́A
        //�u�����N�͔��p�����œ��͂��Ă��������B�v�̗�O���X���[����B
        if (!WEB3StringTypeUtility.isDigit(this.rank))
        {
            log.debug("�����N�͔��p�����œ��͂��Ă��������B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03130,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����N�͔��p�����œ��͂��Ă��������B");
        }

        //�T�|�R�jthis.�����N > 30 �̏ꍇ�́A
        //�u�����N�͏��30�ʂ܂ł̕\�������ł��܂���B�v�̗�O���X���[����B
        int l_intRank = Integer.parseInt(this.rank);
        if (l_intRank > 30)
        {
            log.debug("�����N�͏��30�ʂ܂ł̕\�������ł��܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03131,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����N�͏��30�ʂ܂ł̕\�������ł��܂���B");
        }

        //�U�j�y�[�W���\���s���`�F�b�N
        //�U�|�P�jthis.�y�[�W���\���s�� == null�̏ꍇ�A
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

        //�U�|�Q�jthis.�y�[�W���\���s�������p�����ȊO�̒l�ł������ꍇ�A
        //�u�y�[�W���\���s���������ȊO�̒l�ł��B�v�̗�O���X���[����B
        if (!WEB3StringTypeUtility.isInteger(this.pageSize))
        {
            log.debug("�y�[�W���\���s���������ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�y�[�W���\���s���������ȊO�̒l�ł��B");
        }

        //�U�|�R�jthis.�y�[�W���\���s�� <= 0�ł������ꍇ�A
        //�u�y�[�W���\���s���̒l��0�ȉ��ł��B�v�̗�O���X���[����B
        if (Integer.parseInt(this.pageSize) <= 0)
        {
            log.debug("�y�[�W���\���s���̒l��0�ȉ��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�y�[�W���\���s���̒l��0�ȉ��ł��B");
        }

        //�V�j�v���y�[�W�ԍ��`�F�b�N
        //�V�|�P�jthis.�v���y�[�W�ԍ� == null�̏ꍇ�A
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

        //�V�|�Q�jthis.�v���y�[�W�ԍ������p�����ȊO�̒l�ł������ꍇ�A
        //�u�v���y�[�W�ԍ��������ȊO�̒l�ł��B�v�̗�O���X���[����B
        if (!WEB3StringTypeUtility.isInteger(this.pageIndex))
        {
            log.debug("�v���y�[�W�ԍ��������ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�v���y�[�W�ԍ��������ȊO�̒l�ł��B");
        }

        //�V�|�R�jthis.�v���y�[�W�ԍ� <= 0�ł������ꍇ�A
        //�u�v���y�[�W�ԍ��̒l��0�ȉ��ł��B�v�̗�O���X���[����B
        if (Integer.parseInt(this.pageIndex) <= 0)
        {
            log.debug("�v���y�[�W�ԍ��̒l��0�ȉ��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�v���y�[�W�ԍ��̒l��0�ȉ��ł��B");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * ���X�|���X�f�[�^���쐬����B<BR>
     * <BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminTraderAdminLoginCountListResponse(this);
    }
}
@
