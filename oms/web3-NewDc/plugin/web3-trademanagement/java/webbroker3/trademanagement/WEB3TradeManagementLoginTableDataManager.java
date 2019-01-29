head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.25.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3TradeManagementLoginTableDataManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���O�C���e�[�u���f�[�^�}�l�[�W��(WEB3TradeManagementLoginTableDataManager.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/09/22 �юu�� (���u) �V�K�쐬
Revision History : 2008/10/06 �юu�� (���u) ���f��013
Revision History : 2008/10/17 �юu�� (���u) ���f��018
*/
package webbroker3.trademanagement;

import java.util.Date;

import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.gentrade.data.LoginHistoryPastRow;
import webbroker3.gentrade.data.LoginHistoryRow;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;


/**
 * (���O�C���e�[�u���f�[�^�}�l�[�W��)<BR>
 * ���O�C���e�[�u���̊Ǘ��������Ȃ��N���X�B<BR>
 * <BR>
 * @@author �юu��(���u)
 * @@version 1.0
 */
public class WEB3TradeManagementLoginTableDataManager
{
    /**
     * ���O���[�e�B���e�B�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3TradeManagementLoginTableDataManager.class);

    /**
     * @@roseuid 48D75CD6006B
     */
    public WEB3TradeManagementLoginTableDataManager()
    {

    }

    /**
     * ��ʓ��͂��ꂽ���t�ɂ���ĎQ�Ɛ�e�[�u����ω������鏈���B <BR>
     * <BR>
     * �P�j ���͎��ԑт̃`�F�b�N <BR>
     * <BR>
     * �@@�@@�P�|�P�j ���ݎ����� 0:00:00�`3:29:59 �̏ꍇ ���L�̏��������{�B <BR>
     * �@@�@@�@@�P�|�P�|�P�j �@@yyyymmdd033000 <= (����)���t + (����)����(��) < �Ayyyymmdd033000 <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�̏ꍇ�́A���O�C�������e�[�u��RowType��ԋp����B <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@����ȊO�̏ꍇ�́A���O�C���ߋ������e�[�u��RowType��ԋp����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�� �@@yyyymmdd�͌��ݓ��t -1�� <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�Ayyyymmdd�͌��ݓ��t <BR>
     * <BR>
     * �@@�@@�P�|�Q�j ���ݎ����� 3:30:00�`23:59:59 �̏ꍇ ���L�̏��������{�B <BR>
     * �@@�@@�@@�P�|�Q�|�P�j �@@yyyymmdd033000 <= (����)���t + (����)����(��) < �Ayyyymmdd033000 <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�̏ꍇ�́A���O�C�������e�[�u��RowType��ԋp����B <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@����ȊO�̏ꍇ�́A���O�C���ߋ������e�[�u��RowType��ԋp����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�� �@@yyyymmdd�͌��ݓ��t <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�Ayyyymmdd�͌��ݓ��t +1�� <BR>
     * <BR>
     * @@param l_strDate - (���t)<BR>
     * ��ʂ�����͂��ꂽ���t�B'yyyymmdd' �`���B<BR>
     * @@param l_strStartTime - (����(��))<BR>
     * ����(��)�B(hh24mi �`��)<BR>
     * @@return RowType
     * @@roseuid 48D1E5DA01FA
     */
    public RowType getRowType(String l_strDate, String l_strStartTime)
    {
        final String STR_METHOD_NAME = "getRowType(String, String)";
        log.entering(STR_METHOD_NAME);

        //���ݓ���
        Date l_datSystemTime = GtlUtils.getSystemTimestamp();

        //���ݓ��t
        String l_strSystemTimeYMD =
            WEB3DateUtility.formatDate(l_datSystemTime, WEB3GentradeTimeDef.DATE_FORMAT_YMD);

        Date l_datSystemTimeYMD =
            WEB3DateUtility.getDate(l_strSystemTimeYMD, WEB3GentradeTimeDef.DATE_FORMAT_YMD);

        //(����)���t + (����)����(��)
        Date l_dateForCheck =
            WEB3DateUtility.getDate(
                l_strDate + l_strStartTime,
                WEB3GentradeTimeDef.DATE_FORMAT_YMD + WEB3GentradeTimeDef.TIME_FORMAT_HM);

        //���ݓ��t ��yyyymmdd033000
        Date l_datThreeThirty =
            WEB3DateUtility.addMinute(l_datSystemTimeYMD, 210);

        //���ݓ��t -1�� ��yyyymmdd033000
        Date l_datThreeThirtyYesterday =
            WEB3DateUtility.addMinute(l_datSystemTimeYMD, -1230);

        //���ݓ��t +1�� ��yyyymmdd033000
        Date l_datThreeThirtyForTomorrow =
            WEB3DateUtility.addMinute(l_datSystemTimeYMD, 1650);

        //�P�j ���͎��ԑт̃`�F�b�N
        //�@@�@@�P�|�P�j ���ݎ����� 0:00�`3:29:59 �̏ꍇ ���L�̏��������{�B
        if (l_datSystemTime.before(l_datThreeThirty))
        {
            //�P�|�P�|�P�j �@@yyyymmdd033000 <= (����)���t + (����)����(��) < �Ayyyymmdd033000
            if (WEB3DateUtility.compareToSecond(l_dateForCheck, l_datThreeThirtyYesterday) == 0
                || (l_dateForCheck.after(l_datThreeThirtyYesterday) && l_dateForCheck.before(l_datThreeThirty)))
            {
                //���O�C�������e�[�u��RowType��ԋp����B
                log.exiting(STR_METHOD_NAME);
                return LoginHistoryRow.TYPE;
            }
            else
            {
                //����ȊO�̏ꍇ�́A���O�C���ߋ������e�[�u��RowType��ԋp����B
                log.exiting(STR_METHOD_NAME);
                return LoginHistoryPastRow.TYPE;
            }
        }
        //�P�|�Q�j ���ݎ����� 3:30�`23:59:59 �̏ꍇ ���L�̏��������{�B
        else
        {
            //�P�|�Q�|�P�j �@@yyyymmdd033000 <= (����)���t + (����)����(��) < �Ayyyymmdd033000
            if (WEB3DateUtility.compareToSecond(l_dateForCheck, l_datThreeThirty) == 0
                || (l_dateForCheck.after(l_datThreeThirty) && l_dateForCheck.before(l_datThreeThirtyForTomorrow)))
            {
                //���O�C�������e�[�u��RowType��ԋp����B
                log.exiting(STR_METHOD_NAME);
                return LoginHistoryRow.TYPE;
            }
            else
            {
                //����ȊO�̏ꍇ�́A���O�C���ߋ������e�[�u��RowType��ԋp����B
                log.exiting(STR_METHOD_NAME);
                return LoginHistoryPastRow.TYPE;
            }

        }

    }
}
@
