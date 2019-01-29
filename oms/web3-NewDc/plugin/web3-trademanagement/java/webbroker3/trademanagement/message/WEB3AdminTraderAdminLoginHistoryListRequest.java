head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.22.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTraderAdminLoginHistoryListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE���O�C�������ꗗ�������ʃ��N�G�X�g(WEB3AdminTraderAdminLoginHistoryListRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/09/23 �И��� (���u) �V�K�쐬 ���f��No.005,007,010
Revision History : 2008/10/06 ������ (���u) �d�l�ύX ���f��No.014,No.015
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
 * (�Ǘ��ҁE���O�C�������ꗗ�������ʃ��N�G�X�g)<BR>
 * �Ǘ��ҁE���O�C�������ꗗ�������ʃ��N�G�X�g�N���X�B<BR>
 * <BR>
 * @@author �И���
 * @@version 1.0
 */
public class WEB3AdminTraderAdminLoginHistoryListRequest extends WEB3GenRequest
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTraderAdminLoginHistoryListRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_trader_admin_login_history_list";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200809221747L;

    /**
     * (���t)<BR>
     * ���t�B('yyyymmdd' �`���œ��͂��鎖)<BR>
     */
    public String searchDate;

    /**
     * (IP�A�h���X)<BR>
     * IP�A�h���X�B<BR>
     */
    public String ipAddress;

    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h�B<BR>
     */
    public String branchCode;

    /**
     * (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h�B<BR>
     */
    public String accountCode;

    /**
     * (����(��))<BR>
     * ����(��)�B<BR>
     */
    public String startTime;

    /**
     * (����(��))<BR>
     * ����(��)�B<BR>
     */
    public String endTime;

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
     * (�\�[�g�L�[�ꗗ)<BR>
     * �\�[�g�L�[�ꗗ�B<BR>
     */
    public WEB3AdminTraderAdminLoginHistorySortKey[] sortKeys;

    /**
     * @@roseuid 48D75CD60137
     */
    public WEB3AdminTraderAdminLoginHistoryListRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j���t�`�F�b�N<BR>
     * �@@�P�|�P�jthis.���t == null�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�u�I�y���[�V�������t�����w��ł��B�v<BR>
     * �@@�@@�@@�@@�@@(BUSINESS_ERROR_01272)�̗�O���X���[����B<BR>
     * <BR>
     * �@@�P�|�Q�jthis.���t�̓��͌`���� 'yyyymmdd' �ł͂Ȃ��ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�u���t���s���ł��B�v<BR>
     * �@@�@@�@@�@@�@@(BUSINESS_ERROR_02994)�̗�O���X���[����B<BR>
     * <BR>
     * �Q�j����(��)�`�F�b�N<BR>
     * �@@�Q�|�P�jthis.����(��) == null�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�u����(��)��null�ł��B�v�̗�O���X���[����B<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag: BUSINESS_ERROR_03118<BR>
     * <BR>
     * �@@�Q�|�Q�jthis.����(��)�̓��͌`���� 'hh24mi' �ł͂Ȃ��ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�u�\�����ԁi���j���t�t�H�[�}�b�g�G���[�B�v<BR>
     * �@@�@@�@@�@@�@@(BUSINESS_ERROR_01065)�̗�O���X���[����B<BR>
     * <BR>
     * �R�j����(��)�`�F�b�N<BR>
     * �@@�R�|�P�jthis.����(��) == null�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�u����(��)��null�ł��B�v�̗�O���X���[����B<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag: BUSINESS_ERROR_03119<BR>
     * <BR>
     * �@@�R�|�Q�jthis.����(��)�̓��͌`���� 'hh24mi' �ł͂Ȃ��ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�u�\�����ԁi���j���t�t�H�[�}�b�g�G���[�B�v<BR>
     * �@@�@@�@@�@@�@@(BUSINESS_ERROR_01066)�̗�O���X���[����B<BR>
     * <BR>
     * �S�j���͎��Ԑ������`�F�b�N<BR>
     * �@@�S�|�P�jthis.����(��) > this.����(��) �̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�u�\�����ԁi���j�͕\�����ԁi���j���傫���ł��B�v<BR>
     * �@@�@@�@@�@@�@@(BUSINESS_ERROR_01051)�̗�O���X���[����B<BR>
     * <BR>
     * �@@�S�|�Q�j [this.����(��) - this.����(��)] > �R���� �̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�u�w��͈͂͂R���Ԉȓ��œ��͂��Ă��������B�v�̗�O���X���[����B<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag: BUSINESS_ERROR_03120<BR>
     * <BR>
     * �T�j�@@�\�[�g�L�[�`�F�b�N<BR>
     * �@@�T�|�P�jthis.�\�[�g�L�[ == null�ł������ꍇ<BR>
     * �@@�@@�@@�@@�u�\�[�g�L�[��null�v�̗�O���X���[����B<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag:  BUSINESS_ERROR_00231<BR>
     * <BR>
     * �@@�T�|�Q�jthis.�\�[�g�L�[.length == 0�������ꍇ<BR>
     * �@@�@@�@@�@@�u�\�[�g�L�[.�v�f����0�v�̗�O���X���[����B<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag: BUSINESS_ERROR_00232<BR>
     * <BR>
     * �@@�T�|�R�jthis.�\�[�g�L�[�̑S�v�f�ɑ΂��ĉ��L�̃`�F�b�N���s���B<BR>
     * �@@�@@�T�|�R�|�P�j�\�[�g�L�[.validate()���R�[������B<BR>
     * <BR>
     * �U�j���X�`�F�b�N<BR>
     * �@@�U�|�P�jthis.���X !=�@@null �̏ꍇ�A���L���������{�B<BR>
     * <BR>
     * �@@�@@�@@�U�|�P�|�P�jthis.���X �̒l�����p�����ȊO�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�u���X�R�[�h�����l�ȊO�̒l�ł��B�v<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@(BUSINESS_ERROR_01729)�̗�O���X���[����B<BR>
     * <BR>
     * �V�j�ڋq�R�[�h�`�F�b�N<BR>
     * �@@�V�|�P�jthis.�ڋq�R�[�h !=�@@null �̏ꍇ�A���L���������{�B<BR>
     * <BR>
     * �@@�@@�@@�V�|�P�|�P�jthis.�ڋq�R�[�h �̒l�����p�����ȊO�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�u�ڋq�R�[�h�̒l�������ȊO�̒l�ł��B�v<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@(BUSINESS_ERROR_01043)�̗�O���X���[����B<BR>
     * <BR>
     * �W�jIP�A�h���X�`�F�b�N<BR>
     * �@@�W�|�P�jthis.IP�A�h���X !=�@@null �̏ꍇ�A���L���������{�B<BR>
     * <BR>
     * �@@�@@�@@�W�|�P�|�P�jthis.IP�A�h���X��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@WEB3StringTypeUtility.isIpAddress()==false<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�̏ꍇ�uIP�A�h���X�̒l���s���ł��B�v�̗�O���X���[����B<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag: BUSINESS_ERROR_03121<BR>
     * <BR>
     * �X�j�y�[�W���\���s���`�F�b�N <BR>
     * �@@�X�|�P�jthis.�y�[�W���\���s�� == null�̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�u�y�[�W���\���s���̓��͂��s���ł��B�v�̗�O���X���[����B <BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag: BUSINESS_ERROR_00091<BR>
     * <BR>
     * �@@�X�|�Q�jthis.�y�[�W���\���s�������p�����ȊO�̒l�ł������ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�u�y�[�W���\���s���������ȊO�̒l�ł��B�v�̗�O���X���[����B<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag: BUSINESS_ERROR_00092<BR>
     * <BR>
     * �@@�X�|�R�jthis.�y�[�W���\���s�� <= 0�ł������ꍇ�A <BR>
     * �@@�@@�@@�@@�u�y�[�W���\���s���̒l��0�ȉ��ł��B�v�̗�O���X���[����B <BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag: BUSINESS_ERROR_00617<BR>
     * <BR>
     * �P�O�j�v���y�[�W�ԍ��`�F�b�N <BR>
     * �@@�P�O�|�P�jthis.�v���y�[�W�ԍ� == null�̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�u�v���y�[�W�ԍ������w��ł��B�v�̗�O���X���[����B <BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag: BUSINESS_ERROR_00089<BR>
     * <BR>
     * �@@�P�O�|�Q�jthis.�v���y�[�W�ԍ������p�����ȊO�̒l�ł������ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�u�v���y�[�W�ԍ��������ȊO�̒l�ł��B�v�̗�O���X���[����B <BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag: BUSINESS_ERROR_00090<BR>
     * <BR>
     * �@@�P�O�|�R�jthis.�v���y�[�W�ԍ� <= 0�ł������ꍇ�A <BR>
     * �@@�@@�@@�@@�u�v���y�[�W�ԍ��̒l��0�ȉ��ł��B�v�̗�O���X���[����B <BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag: BUSINESS_ERROR_00616<BR>
     * <BR>
     * @@roseuid 48BCA06401DB
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //�P�j���t�`�F�b�N
        //�P�|�P�jthis.���t == null�̏ꍇ�́A
        //�u�I�y���[�V�������t�����w��ł��B�v
        //(BUSINESS_ERROR_01272)�̗�O���X���[����B]
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
        //(BUSINESS_ERROR_01066)�̗�O���X���[����
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

        //�S�|�Q�j [this.����(��) - this.����(��)] > �R���� �̏ꍇ�́A
        //�u�w��͈͂͂R���Ԉȓ��œ��͂��Ă��������B�v�̗�O���X���[����B
        if (WEB3DateUtility.compareToMinute(
            WEB3DateUtility.addHour(l_datEndTime, -3), l_datStartTime) > 0)
        {
            log.debug("�w��͈͂͂R���Ԉȓ��œ��͂��Ă��������B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03120,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�w��͈͂͂R���Ԉȓ��œ��͂��Ă��������B");
        }

        //�T�j�@@�\�[�g�L�[�`�F�b�N
        //�T�|�P�jthis.�\�[�g�L�[ == null�ł������ꍇ
        //�u�\�[�g�L�[��null�v�̗�O���X���[����B
        if (this.sortKeys == null)
        {
            log.debug("�\�[�g�L�[�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog. BUSINESS_ERROR_00231,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�\�[�g�L�[�����w��ł��B");
        }

        //�T�|�Q�jthis.�\�[�g�L�[.length == 0�������ꍇ
        //�u�\�[�g�L�[.�v�f����0�v�̗�O���X���[����B
        if (this.sortKeys.length == 0)
        {
            log.debug("�\�[�g�L�[�̗v�f�����O�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�\�[�g�L�[�̗v�f�����O�ł��B");
        }

        //�T�|�R�jthis.�\�[�g�L�[�̑S�v�f�ɑ΂��ĉ��L�̃`�F�b�N���s���B
        //�T�|�R�|�P�j�\�[�g�L�[.validate()���R�[������B
        int l_intLength = this.sortKeys.length;
        for (int i = 0; i < l_intLength; i++)
        {
            this.sortKeys[i].validate();
        }

        //�U�j���X�`�F�b�N
        //�U�|�P�jthis.���X !=�@@null �̏ꍇ�A���L���������{�B
        if (this.branchCode != null)
        {
            //�U�|�P�|�P�jthis.���X �̒l�����p�����ȊO�̏ꍇ�́A
            //�u���X�R�[�h�����l�ȊO�̒l�ł��B�v
            //(BUSINESS_ERROR_01729)�̗�O���X���[����B
            if (!WEB3StringTypeUtility.isDigit(this.branchCode))
            {
                log.debug("���X�R�[�h�����l�ȊO�̒l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01729,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���X�R�[�h�����l�ȊO�̒l�ł��B");
            }
        }

        //�V�j�ڋq�R�[�h�`�F�b�N
        //�V�|�P�jthis.�ڋq�R�[�h !=�@@null �̏ꍇ�A���L���������{�B
        if (this.accountCode != null)
        {
            //�V�|�P�|�P�jthis.�ڋq�R�[�h �̒l�����p�����ȊO�̏ꍇ�́A
            //�u�ڋq�R�[�h�̒l�������ȊO�̒l�ł��B�v
            //(BUSINESS_ERROR_01043)�̗�O���X���[����B
            if (!WEB3StringTypeUtility.isDigit(this.accountCode))
            {
                log.debug("�ڋq�R�[�h�̒l�������ȊO�̒l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01043,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�ڋq�R�[�h�̒l�������ȊO�̒l�ł��B");
            }
        }

        //�W�jIP�A�h���X�`�F�b�N
        //�W�|�P�jthis.IP�A�h���X !=�@@null �̏ꍇ�A���L���������{�B
        if (this.ipAddress != null)
        {
            //�W�|�P�|�P�jthis.IP�A�h���X��
            //WEB3StringTypeUtility.isIpAddress()==false
            //�̏ꍇ�uIP�A�h���X�̒l���s���ł��B�v�̗�O���X���[����B
            if (!WEB3StringTypeUtility.isIpAddress(this.ipAddress))
            {
                log.debug("IP�A�h���X�̒l���s���ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03121,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "IP�A�h���X�̒l���s���ł��B");
            }
        }

        //�X�j�y�[�W���\���s���`�F�b�N
        //�X�|�P�jthis.�y�[�W���\���s�� == null�̏ꍇ�A
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

        //�X�|�Q�jthis.�y�[�W���\���s�������p�����ȊO�̒l�ł������ꍇ�A
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

        //�X�|�R�jthis.�y�[�W���\���s�� <= 0�ł������ꍇ�A
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

        //�P�O�j�v���y�[�W�ԍ��`�F�b�N
        //�P�O�|�P�jthis.�v���y�[�W�ԍ� == null�̏ꍇ�A
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

        //�P�O�|�Q�jthis.�v���y�[�W�ԍ������p�����ȊO�̒l�ł������ꍇ�A
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

        //�P�O�|�R�jthis.�v���y�[�W�ԍ� <= 0�ł������ꍇ�A
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
        return new WEB3AdminTraderAdminLoginHistoryListResponse(this);
    }
}
@
