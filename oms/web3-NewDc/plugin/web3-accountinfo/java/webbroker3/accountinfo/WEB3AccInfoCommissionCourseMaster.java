head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.23.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoCommissionCourseMaster.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ϑ��萔���R�[�X�}�X�^(WEB3AccInfoCommissionCourseMaster)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/09 ����� (���u) �V�K�쐬
                   2006/01/31 ��؁iSRA) �d�l�ύX�Ǘ�No.084
                   2006/06/30 ������ (���u) �d�l�ύX�Ǘ�No.112
                   2006/07/29 �R�c (SCS) �d�l�ύX�Ǘ�No.120
Revesion History : 2008/08/18 �k�v�u (���u) �d�l�ύX�E���f��No.239,242,246
*/

package webbroker3.accountinfo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.accountinfo.data.CommissionCourseMasterDao;
import webbroker3.accountinfo.data.CommissionCourseMasterParams;
import webbroker3.accountinfo.data.CommissionCourseMasterRow;
import webbroker3.accountinfo.define.WEB3AccInfoRegistEndDaySpecDivDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccInfoCommissionDivDef;
import webbroker3.common.define.WEB3AppliStartDateDivDef;
import webbroker3.common.define.WEB3AppliTermDivDef;
import webbroker3.common.define.WEB3BizDateTypeDef;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeUtils;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (�ϑ��萔���R�[�X�}�X�^)<BR>
 * �ϑ��萔���R�[�X�}�X�^�N���X<BR>
 *
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3AccInfoCommissionCourseMaster implements BusinessObject
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoCommissionCourseMaster.class);

    /**
     * (�ϑ��萔���R�[�X�}�X�^�s)<BR>
     * �ϑ��萔���R�[�X�}�X�^�s�I�u�W�F�N�g<BR>
     * <BR>
     * �� �ϑ��萔���R�[�X�}�X�^Params�N���X��DDL��莩����������B<BR>
     */
    private CommissionCourseMasterParams commissionCourseMasterParams;

    /**
     * (�ϑ��萔���R�[�X�}�X�^)<BR>
     * �R���X�g���N�^�B<BR>
     * <BR>
     * �w��s�I�u�W�F�N�g���v���p�e�B�ɃZ�b�g���A�C���X�^���X�𐶐�����B <BR>
     * @@param l_commissionCourseMasterParams - �ϑ��萔���R�[�X�}�X�^�s�I�u�W�F�N�g<BR>
     * <BR>
     * ���@@�ϑ��萔���R�[�X�}�X�^Params��DDL��莩����������B<BR>
     * @@roseuid 413D9D960038
     */
    public WEB3AccInfoCommissionCourseMaster(CommissionCourseMasterParams l_commissionCourseMasterParams)
    {
        this.commissionCourseMasterParams = l_commissionCourseMasterParams;
    }

    /**
     * (�ϑ��萔���R�[�X�}�X�^)<BR>
     * �R���X�g���N�^�B<BR>
     * <BR>
     * �،���ЃR�[�h�C�萔�����i�R�[�h�C�萔���R�[�X�R�[�h�ɊY������s���A<BR>
     * �ϑ��萔���R�[�X�}�X�^�e�[�u����茟������B<BR>
     * �i�Y���s���Ȃ��ꍇ�́A��O���X���[����j<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01168<BR>
     * <BR>
     * �������ʂ̈ϑ��萔���R�[�X�}�X�^�s�I�u�W�F�N�g�������Ɏw�肵�āA<BR>
     * �R���X�g���N�^���R�[������B <BR>
     * �R���X�g���N�^�̖߂�l��ԋp����B <BR>
     *
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strCommissionProductCode - �萔�����i�R�[�h
     * @@param l_strCommissionCourseCode - �萔���R�[�X�R�[�h
     * @@return webbroker3.accountinfo.WEB3AccInfoCommissionCourseMaster
     * @@roseuid 413D86280103
     */
    public WEB3AccInfoCommissionCourseMaster(String l_strInstitutionCode, String l_strCommissionProductCode, String l_strCommissionCourseCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " WEB3AccInfoCommissionCourseMaster(String, String, String)";
        log.entering(STR_METHOD_NAME);

        //�،���ЃR�[�h�C�萔�����i�R�[�h�C�萔���R�[�X�R�[�h�ɊY������s���A
        //�ϑ��萔���R�[�X�}�X�^�e�[�u����茟������B
        try
        {
            CommissionCourseMasterRow l_commissionCourseMasterRow = CommissionCourseMasterDao.findRowByPk(
                l_strInstitutionCode,           //�،���ЃR�[�h
                l_strCommissionProductCode,     //�萔�����i�R�[�h
                l_strCommissionCourseCode       //�萔���R�[�X�R�[�h
                );
                log.debug("l_strInstitutionCode===" + l_strInstitutionCode);
                log.debug("l_strCommissionProductCode===" + l_strCommissionProductCode);
                log.debug("l_strCommissionCourseCode===" + l_strCommissionCourseCode);

            if (l_commissionCourseMasterRow == null)
            {
                //�Y���s���Ȃ��ꍇ�́A��O���X���[����
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01168,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            else
            {
                this.commissionCourseMasterParams = (CommissionCourseMasterParams)l_commissionCourseMasterRow;
            }

        }
        catch (DataFindException l_ex)
        {
            //�Y���s���Ȃ��ꍇ�́A��O���X���[����
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01168,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �igetDataSourceObject�̎����j <BR>
     * <BR>
     * this.�ϑ��萔���R�[�X�}�X�^�s��ԋp����B <BR>
     * @@return Object
     * @@roseuid 413D6E360076
     */
    public Object getDataSourceObject()
    {
        return this.commissionCourseMasterParams;
    }

    /**
     * �@@this.�ϑ��萔���R�[�X�}�X�^�s���R�s�[���āA�������e�̕ʃC���X�^���X��<BR>
     * �쐬����iclone�j�B <BR>
     * �쐬�����R�s�[�����g��this.�ϑ��萔���R�[�X�}�X�^�s�ɃZ�b�g����B <BR>
     * @@roseuid 413D6E360086
     */
    public void createForUpdateParams()
    {
        CommissionCourseMasterParams l_params = new CommissionCourseMasterParams(this.commissionCourseMasterParams);
        this.commissionCourseMasterParams = l_params;
    }

    /**
     * (get�ύX�\�����ؓ���)<BR>
     * �ύX�\�����ؓ������擾����B<BR>
     * <BR>
     * this.�ϑ��萔���R�[�X�}�X�^�s.�ύX�\�����؎w����C<BR>
     * this.�ϑ��萔���R�[�X�}�X�^�s.�ύX�\�����؎���<BR>
     * ���A�ύX�\�����ؓ�����ҏW����B<BR>
     * <BR>
     * �� �ύX�\�����ؓ����̕ҏW<BR>
     * <BR>
     * �@@�y���t�̌v�Z�z<BR>
     * �@@�@@�@@�����������v�Z����B<BR>
     * �@@�|�i�ύX�\�����؎w��� == 00�F�����܂���01�F���T�j�̏ꍇ�A<BR>
     * �@@�@@��������(*1)�̔N�����iYYYYMMDD�j + �ύX�\�����؎��ԁiHHMMSS�j<BR>
     * <BR>
     * �@@�|�ȊO�̏ꍇ�A<BR>
     * �@@�@@��������(*1)�̔N���iYYYYMM�j + �ύX�\�����؎w����iDD�j + <BR>
     * �ύX�\�����؎��ԁiHHMMSS�j<BR>
     * <BR>
     * �@@�A�@@�ύX�\�����ؓ����̌v�Z<BR>
     * �@@�|�i�@@�̌v�Z���� >= ���������j�̏ꍇ�A�@@�̌v�Z���ʂ�Date�^��<BR>
     * �ϊ������l��ԋp����B<BR>
     * �@@�|�ȊO�̏ꍇ�A�ȉ���Date�^�l��ԋp����B<BR>
     * �@@�@@�|�i�ύX�\�����؎w��� == 00�F�����j�̏ꍇ�A<BR>
     * �@@�@@�@@���������̗��c�Ɠ��iYYYYMMDD�j + <BR>
     * �ύX�\�����؎��ԁiHHMMSS�j<BR>
     * <BR>
     * �@@�@@�|�i�ύX�\�����؎w��� == 01�F���T�j�̏ꍇ�A<BR>
     * �@@�@@�@@���������̗��T���c�Ɠ��iYYYYMMDD�j+ �ύX�\�����؎��ԁiHHMMSS�j<BR>
     * <BR>
     * �@@�@@�@@�����T���c�Ɠ��͈ȉ��̏����ɂĎ擾����B <BR>
     * �@@�@@�@@�@@�P�D�u�c�Ɠ��v�Z(��� : Timestamp)�v�R�[��<BR>
     * �@@�@@�@@�@@�@@�@@[����] ����F�������� ����7����<BR>
     * �@@�@@�@@�@@�Q�D�uget�T���c�Ɠ��v�R�[��<BR>
     * <BR>
     * �@@�@@�|�ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@���������̗����iYYYYMM�j + �ύX�\�����؎w����iDD�j + �ύX�\�����؎��ԁiHHMMSS�j<BR>
     * <BR>
     * �@@(*1)�@@���������F�@@�i�ύX�\�����؎w��� == 00�F�����j�̏ꍇ���ATradingSystem.getSystemTimestamp()��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�擾�������t����c�Ɠ��̏ꍇ�͗��c�Ɠ����Z�o����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�i�ύX�\�����؎w��� == 01�F���T�j�̏ꍇ�A���T���c�Ɠ����g�p����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@����ȊO�̏ꍇ�́ATradingSystem.getSystemTimestamp()�̖߂�l���g�p����B <BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�����T���c�Ɠ��͈ȉ��̏����ɂĎ擾����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�P�D�u�c�Ɠ��v�Z(��� : Timestamp)�v�R�[��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@[����] ����FTradingSystem.getSystemTimestamp()�̖߂�l<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�Q�D�uget�T���c�Ɠ��v�R�[��<BR>
     * @@return Date
     * @@roseuid 413D6F3E0086
     */
    public Date getRegistEndTimestamp() throws WEB3BaseException
    {

        final String STR_METHOD_NAME = " getRegistEndTimestamp()";
        log.entering(STR_METHOD_NAME);

        //�ύX�\�����؎w���
        String l_strRegistEndDaySpec = this.commissionCourseMasterParams.getRegistEndDaySpec();
        //�ύX�\�����؎���
        String l_strRegistEndTime = this.commissionCourseMasterParams.getRegistEndTime();

        //��������(*1)
        //�ύX�\�����؎w��� == 00�F�����j�̏ꍇ���ATradingSystem.getSystemTimestamp()��
        //�擾�������t����c�Ɠ��̏ꍇ�͗��c�Ɠ����Z�o����B
        Timestamp l_datProcessDate = GtlUtils.getSystemTimestamp();
        String l_strProcessDateYMD = null;
        if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(
            WEB3GentradeTradingTimeManagement.getBizDateType(l_datProcessDate))
            && WEB3AccInfoRegistEndDaySpecDivDef.EVERYDAY.equals(l_strRegistEndDaySpec))
        {
            WEB3GentradeBizDate l_date = new WEB3GentradeBizDate(l_datProcessDate);
            Timestamp l_tsNextProcessDate = l_date.roll(1);
            l_strProcessDateYMD =
                WEB3DateUtility.formatDate(l_tsNextProcessDate, WEB3GentradeTimeDef.DATE_FORMAT_YMD);
        }
        //�i�ύX�\�����؎w��� == 01�F���T�j�̏ꍇ�A���T���c�Ɠ����g�p����B
        else if (WEB3AccInfoRegistEndDaySpecDivDef.EVERYWEEK.equals(l_strRegistEndDaySpec))
        {
            //�����T���c�Ɠ��͈ȉ��̏����ɂĎ擾����B
            //�P�D�u�c�Ɠ��v�Z(��� : Timestamp)�v�R�[��
            //[����] ����FTradingSystem.getSystemTimestamp()�̖߂�l
            WEB3GentradeBizDate l_date = new WEB3GentradeBizDate(l_datProcessDate);
            //�Q�D�uget�T���c�Ɠ��v�R�[��
            Timestamp l_tsThisWeekProcessDate = l_date.getWeekStartBizDate();
            l_strProcessDateYMD =
                WEB3DateUtility.formatDate(l_tsThisWeekProcessDate, WEB3GentradeTimeDef.DATE_FORMAT_YMD);
        }
        else
        {
            l_strProcessDateYMD =
                WEB3DateUtility.formatDate(l_datProcessDate, WEB3GentradeTimeDef.DATE_FORMAT_YMD);
        }
        String l_strProcessDateYM =
            WEB3DateUtility.formatDate(l_datProcessDate, WEB3GentradeTimeDef.DATE_FORMAT_YM);

        //�ύX�\�����ؓ���
        Date l_datRegistEndDate = null;

        //�@@�@@�����������v�Z����B
        //�i�ύX�\�����؎w��� == 00�F�����܂���01�F���T�j�̏ꍇ
        if (WEB3AccInfoRegistEndDaySpecDivDef.EVERYDAY.equals(l_strRegistEndDaySpec)
            || WEB3AccInfoRegistEndDaySpecDivDef.EVERYWEEK.equals(l_strRegistEndDaySpec))
        {
            //��������(*1)�̔N�����iYYYYMMDD�j + �ύX�\�����؎��ԁiHHMMSS�j
            String l_strDate = l_strProcessDateYMD + " " + l_strRegistEndTime;
            l_datRegistEndDate = WEB3DateUtility.getDate(l_strDate,
                WEB3GentradeTimeDef.DATE_FORMAT_YMD + " " + WEB3GentradeTimeDef.TIME_FORMAT_HMS);
        }
        else
        {
            //�ȊO�̏ꍇ�A
            //��������(*1)�̔N���iYYYYMM�j + �ύX�\�����؎w����iDD�j + �ύX�\�����؎��ԁiHHMMSS�j
            String l_strDate = l_strProcessDateYM + l_strRegistEndDaySpec + " " + l_strRegistEndTime;
            l_datRegistEndDate = WEB3DateUtility.getDate(l_strDate,
                WEB3GentradeTimeDef.DATE_FORMAT_YMD + " " + WEB3GentradeTimeDef.TIME_FORMAT_HMS);
        }

        //�A�@@�ύX�\�����ؓ����̌v�Z
        //(�v�Z���� < ���������j�̏ꍇ
        if (WEB3DateUtility.compareToSecond(l_datRegistEndDate, l_datProcessDate) < 0)
        {
            Calendar l_calendar = new GregorianCalendar();
            l_calendar.setTime(l_datProcessDate);   //��������

            if (WEB3AccInfoRegistEndDaySpecDivDef.EVERYDAY.equals(l_strRegistEndDaySpec))
            {
                //�i�ύX�\�����؎w��� == 00�F�����j�̏ꍇ�A
                //���������̗��c�Ɠ��iYYYYMMDD�j + �ύX�\�����؎��ԁiHHMMSS�j
                WEB3GentradeBizDate l_date = new WEB3GentradeBizDate(l_datProcessDate);
                Timestamp l_tsNextProcessDate = l_date.roll(1);
                String l_strDateYMD =
                    WEB3DateUtility.formatDate(l_tsNextProcessDate, WEB3GentradeTimeDef.DATE_FORMAT_YMD);
                String l_strDate = l_strDateYMD + " " + l_strRegistEndTime;
                l_datRegistEndDate = WEB3DateUtility.getDate(l_strDate,
                    WEB3GentradeTimeDef.DATE_FORMAT_YMD + " " + WEB3GentradeTimeDef.TIME_FORMAT_HMS);
            }
            else if (WEB3AccInfoRegistEndDaySpecDivDef.EVERYWEEK.equals(l_strRegistEndDaySpec))
            {
                //�i�ύX�\�����؎w��� == 01�F���T�j�̏ꍇ
                //���������̗��T���c�Ɠ��iYYYYMMDD�j+ �ύX�\�����؎��ԁiHHMMSS�j
                //�����T���c�Ɠ��͈ȉ��̏����ɂĎ擾����B
                //�P�D�u�c�Ɠ��v�Z(��� : Timestamp)�v�R�[��
                //[����] ����F�������� ����7����
                Date l_datProcessDateSevenDayAfter = WEB3DateUtility.addDay(l_datProcessDate, 7);
                WEB3GentradeBizDate l_dateOfNextWeek =
                    new WEB3GentradeBizDate(new Timestamp(l_datProcessDateSevenDayAfter.getTime()));
                //�Q�D�uget�T���c�Ɠ��v�R�[��
                Timestamp l_tsNextWeekProcessDate = l_dateOfNextWeek.getWeekStartBizDate();
                String l_strDateYMD =
                    WEB3DateUtility.formatDate(l_tsNextWeekProcessDate, WEB3GentradeTimeDef.DATE_FORMAT_YMD);
                String l_strDate = l_strDateYMD + " " + l_strRegistEndTime;
                l_datRegistEndDate = WEB3DateUtility.getDate(l_strDate,
                    WEB3GentradeTimeDef.DATE_FORMAT_YMD + " " + WEB3GentradeTimeDef.TIME_FORMAT_HMS);
            }
            else
            {
                //�ȊO�̏ꍇ�A
                //���������̗����iYYYYMM�j + �ύX�\�����؎w����iDD�j + �ύX�\�����؎��ԁiHHMMSS�j
                l_calendar.add(Calendar.MONTH, 1);
                String l_strDateYM =
                    WEB3DateUtility.formatDate(l_calendar.getTime(), WEB3GentradeTimeDef.DATE_FORMAT_YM);
                String l_strDate = l_strDateYM + l_strRegistEndDaySpec + " " + l_strRegistEndTime;
                l_datRegistEndDate = WEB3DateUtility.getDate(
                    l_strDate, WEB3GentradeTimeDef.DATE_FORMAT_YMD + " " + WEB3GentradeTimeDef.TIME_FORMAT_HMS);
            }

        }

        log.exiting(STR_METHOD_NAME);
        return l_datRegistEndDate;
    }

    /**
     * (get�K�p�J�n����)<BR>
     * �ύX�K�p�J�n�������擾����B<BR>
     * <BR>
     * this.get�ύX�\�����ؓ���()�C<BR>
     * this.�ϑ��萔���R�[�X�}�X�^�s.�ύX�K�p�J�n���w��敪�C<BR>
     * this.�ϑ��萔���R�[�X�}�X�^�s.�ύX�K�p�J�n�����C<BR>
     * this.�ϑ��萔���R�[�X�}�X�^�s.�K�p�J�n�^�I������<BR>
     * ���A�ύX�K�p�J�n������ҏW����B<BR>
     * <BR>
     * �� �ύX�K�p�J�n�����̕ҏW<BR>
     * �@@�y���t�̌v�Z�z<BR>
     * �@@�@@�|�i�ύX�K�p�J�n���w��敪 == 1�F�\�����̗����i�����c�Ɠ��j�j�̏ꍇ�A<BR>
     * �@@�@@�ύX�\�����ؓ���(*1)�̗����̌����c�Ɠ�<BR>
     * <BR>
     * �@@�@@�|�i�ύX�K�p�J�n���w��敪 == 2�F�\�����̗��X���i�����c�Ɠ��j�j�̏ꍇ�A<BR>
     * �@@�@@�ύX�\�����ؓ���(*1)�̗��X���̌����c�Ɠ�<BR>
     * <BR>
     * �@@�@@�|�i�ύX�K�p�J�n���w��敪 == 9�F�����w��j�̏ꍇ�A<BR>
     * �@@�@@�ύX�\�����ؓ���(*1)�̓��t����A�c�Ɠ��x�[�X�� <BR>
     * [�ύX�K�p�J�n����] ��i�В[�j�̓��t�B<BR>
     * <BR>
     * �@@�@@��L�Ōv�Z�������t�iYYMMDD�j�{�K�p�J�n�^�I�����ԁiHHMMSS�j��<BR>
     * Date�^�ŕԋp����B<BR>
     * <BR>
     * (*1)�@@�ύX�\�����ؓ����F�@@this.get�ύX�\�����ؓ���()<BR>
     * @@return Date
     * @@roseuid 413D775202F7
     */
    public Date getAppliStartTimestamp() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getAppliStartTimestamp()";
        log.entering(STR_METHOD_NAME);

        //this.get�ύX�\�����ؓ���()
        Date l_datRegistEndDate = getRegistEndTimestamp();
        Calendar l_calendar = new GregorianCalendar();
        l_calendar.setTime(l_datRegistEndDate);

        //this.�ϑ��萔���R�[�X�}�X�^�s.�ύX�K�p�J�n���w��敪
        String l_strAppliStartDateDiv = this.commissionCourseMasterParams.getAppliStartDateDiv();
        //this.�ϑ��萔���R�[�X�}�X�^�s.�ύX�K�p�J�n����
        int l_intAppliStartDayCount = this.commissionCourseMasterParams.getAppliStartDayCount();
        //this.�ϑ��萔���R�[�X�}�X�^�s.�K�p�J�n�^�I������
        String l_strAppliStartEndTime = this.commissionCourseMasterParams.getAppliStartEndTime();

        //�ύX�K�p�J�n���t
        String l_strDateYMD = "";

        if (WEB3AppliStartDateDivDef.NEXT_MONTH_OF_APPLI_DAY.equals(l_strAppliStartDateDiv))
        {
            //�i�ύX�K�p�J�n���w��敪 == 1�F�\�����̗����i�����c�Ɠ��j�j�̏ꍇ�A
            //�ύX�\�����ؓ����̗����̌����c�Ɠ�
            l_calendar.add(Calendar.MONTH, 1);
            l_calendar.set(Calendar.DATE, l_calendar.getActualMinimum(Calendar.DATE));

            l_strDateYMD = WEB3DateUtility.formatDate(WEB3GentradeUtils.getBizDate(l_calendar.getTime(), 0), "yyyyMMdd");
        }
        else if (WEB3AppliStartDateDivDef.TWO_MONTHS_AFTER_OF_APPLI_DAY.equals(l_strAppliStartDateDiv))
        {
            //�i�ύX�K�p�J�n���w��敪 == 2�F�\�����̗��X���i�����c�Ɠ��j�j�̏ꍇ�A
            //�ύX�\�����ؓ����̗��X���̌����c�Ɠ�
            l_calendar.add(Calendar.MONTH, 2);
            l_calendar.set(Calendar.DATE, l_calendar.getActualMinimum(Calendar.DATE));

            l_strDateYMD = WEB3DateUtility.formatDate(WEB3GentradeUtils.getBizDate(l_calendar.getTime(), 0), "yyyyMMdd");
        }
        else if (WEB3AppliStartDateDivDef.DAYS_DESIGNATED.equals(l_strAppliStartDateDiv))
        {
            //�i�ύX�K�p�J�n���w��敪 == 9�F�����w��j�̏ꍇ�A
            // �ύX�\�����ؓ���(*1)�̓��t����A�c�Ɠ��x�[�X�� [�ύX�K�p�J�n����] ��i�В[�j�̓��t�B

            l_strDateYMD = WEB3DateUtility.formatDate(WEB3GentradeUtils.getBizDate(l_calendar.getTime(), l_intAppliStartDayCount), "yyyyMMdd");
        }
        else
        {
            log.error("�f�[�^�s�����G���[: �ύX�K�p�J�n���w��敪 = " + l_strAppliStartDateDiv);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + STR_METHOD_NAME
                );
        }

        //��L�Ōv�Z�������t�iYYMMDD�j�{�K�p�J�n�^�I�����ԁiHHMMSS�j��Date�^�ŕԋp����B
        String l_strDate = l_strDateYMD + " " + l_strAppliStartEndTime;

        //�ύX�K�p�J�n����
        Date l_datAppliStartDate = WEB3DateUtility.getDate(l_strDate, "yyyyMMdd HHmmss");

        log.exiting(STR_METHOD_NAME);
        return l_datAppliStartDate;
    }

    /**
     * (get�K�p�I������)<BR>
     * �ύX�K�p�I���������擾����B<BR>
     * <BR>
     * this.get�K�p�J�n����()�C<BR>
     * this.�ϑ��萔���R�[�X�}�X�^�s.�K�p���ԋ敪�C<BR>
     * this.�ϑ��萔���R�[�X�}�X�^�s.�K�p���Ԑ�<BR>
     * ���A�ύX�K�p�I��������ҏW����B<BR>
     * <BR>
     * �� �ύX�K�p�I�������̕ҏW<BR>
     * <BR>
     * �@@�@@�|�i�K�p���ԋ敪 == 0�FDEFAULT�i���Ԑ����Ȃ��j�j�̏ꍇ�A<BR>
     * �@@�@@���t�ő�l�iHighValue�F9999/12/31 00�F00�F00�j��ԋp����B<BR>
     * <BR>
     * �@@�@@�|�i�K�p���Ԑ��敪 == 1�F�N���w��j�̏ꍇ�A<BR>
     * �@@�@@�K�p�J�n����(*1)�� [�K�p���Ԑ�] �N��i�В[�j�̓����B<BR>
     * <BR>
     * �@@�@@�|�i�K�p���Ԑ��敪 == 2�F�����w��j�̏ꍇ�A<BR>
     * �@@�@@�K�p�J�n����(*1)�� [�K�p���Ԑ�] ����i�В[�j�̓����B<BR>
     * <BR>
     * �@@�@@�|�i�K�p���Ԑ��敪 == 3�F�����w��j�̏ꍇ�A<BR>
     * �@@�@@�K�p�J�n����(*1)�� [�K�p���Ԑ�] ����i�В[�j�̓����B<BR>
     * <BR>
     * �N���w��^�����w��ŁA�J�����_�[�㑶�݂��Ȃ����t�ɂȂ�ꍇ�́A<BR>
     * �ŏI���ɕ␳����B<BR>
     * 2004/8/31��1������@@�̏ꍇ�́A2004/9/30�B<BR>
     * <BR>
     * (*1)�@@�K�p�J�n�����F�@@this.get�K�p�J�n����()<BR>
     * @@return Date
     * @@roseuid 413D767002B8
     */
    public Date getAppliEndTimestamp() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getAppliEndTimestamp()";
        log.entering(STR_METHOD_NAME);

        //this.get�K�p�J�n����()
        Date l_datAppliStartDate = getAppliStartTimestamp();
        Calendar l_calendar = new GregorianCalendar();
        l_calendar.setTime(l_datAppliStartDate);

        //�ύX�K�p�I������
        Date l_datAppliEndDate = null;

        //this.�ϑ��萔���R�[�X�}�X�^�s.�K�p���ԋ敪
        String l_strAppliTermDiv = this.commissionCourseMasterParams.getAppliTermDiv();
        // this.�ϑ��萔���R�[�X�}�X�^�s.�K�p���Ԑ�
        int l_intAppliTermDateCount = this.commissionCourseMasterParams.getAppliTermDateCount();

        if (WEB3AppliTermDivDef.DEFAULT.equals(l_strAppliTermDiv))
        {
            //�i�K�p���ԋ敪 == 0�FDEFAULT�i���Ԑ����Ȃ��j�j�̏ꍇ�A
            //���t�ő�l�iHighValue�F9999/12/31 00�F00�F00�j��ԋp����B

            l_datAppliEndDate = WEB3DateUtility.getDate("9999-12-31 00:00:00", "yyyy-MM-dd HH:mm:ss");
        }
        else if (WEB3AppliTermDivDef.YEARS_DESIGNATED.equals(l_strAppliTermDiv))
        {
            //�i�K�p���Ԑ��敪 == 1�F�N���w��j�̏ꍇ�A
            //�K�p�J�n����(*1)�� [�K�p���Ԑ�] �N��i�В[�j�̓����B
            l_calendar.add(Calendar.YEAR, l_intAppliTermDateCount);
            l_datAppliEndDate = l_calendar.getTime();
        }
        else if (WEB3AppliTermDivDef.MONTHS_DESIGNATED.equals(l_strAppliTermDiv))
        {
            //�i�K�p���Ԑ��敪 == 2�F�����w��j�̏ꍇ�A
            //�K�p�J�n����(*1)�� [�K�p���Ԑ�] ����i�В[�j�̓����B
            l_calendar.add(Calendar.MONTH, l_intAppliTermDateCount);
            l_datAppliEndDate = l_calendar.getTime();
        }
        else if (WEB3AppliTermDivDef.DAYS_DESIGNATED.equals(l_strAppliTermDiv))
        {
            //�i�K�p���Ԑ��敪 == 3�F�����w��j�̏ꍇ�A
            //�K�p�J�n����(*1)�� [�K�p���Ԑ�] ����i�В[�j�̓����B
            l_calendar.add(Calendar.DATE, l_intAppliTermDateCount);
            l_datAppliEndDate = l_calendar.getTime();
        }
        else
        {
            log.error("�f�[�^�s�����G���[: �K�p���ԋ敪 = " + l_strAppliTermDiv);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + STR_METHOD_NAME
                );
        }

        log.exiting(STR_METHOD_NAME);
        return l_datAppliEndDate;
    }

    /**
     * (get�،���ЃR�[�h)<BR>
     * this.�ϑ��萔���R�[�X�}�X�^�s.�،���ЃR�[�h��ԋp����B<BR>
     * @@return String
     * @@roseuid 413D9F040113
     */
    public String getInstitutionCode()
    {
        return this.commissionCourseMasterParams.getInstitutionCode();
    }

    /**
     * (get�萔�����i�R�[�h)<BR>
     * this.�ϑ��萔���R�[�X�}�X�^�s.�萔�����i�R�[�h��ԋp����B<BR>
     * @@return String
     * @@roseuid 413D9F350141
     */
    public String getCommissionProductCode()
    {
        return this.commissionCourseMasterParams.getCommProductCode();
    }

    /**
     * (get�萔���R�[�X�R�[�h)<BR>
     * this.�ϑ��萔���R�[�X�}�X�^�s.�萔���R�[�X�R�[�h��ԋp����B<BR>
     * @@return String
     * @@roseuid 413D9F5502D8
     */
    public String getCommissionCourseCode()
    {
        return this.commissionCourseMasterParams.getCommissionCourseDiv();
    }

    /**
     * (get�萔���敪)<BR>
     * this.�ϑ��萔���R�[�X�}�X�^�s.�萔���敪��ԋp����B<BR>
     * @@return String
     */
    public String getCommissionDiv()
    {
        return this.commissionCourseMasterParams.getCommissionDiv();
    }

    /**
     * (get�戵�\�ϑ��萔���R�[�X)<BR>
     * �istatic ���\�b�h�j<BR>
     * �،���ЂɊY������ϑ��萔���R�[�X�}�X�^��z��ɂĎ擾����B<BR>
     * <BR>
     * �P�j�@@�ϑ��萔���R�[�X�}�X�^�e�[�u������<BR>
     * �@@�ȉ��̏����ŁA�ϑ��萔���R�[�X�}�X�^�e�[�u������������B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�،���ЃR�[�h = �،���ЃR�[�h<BR>
     * �@@�萔�����i�R�[�h = �萔�����i�R�[�h<BR>
     * <BR>
     * �Q�j�@@�ԋp�l����<BR>
     * �@@Hashtable�𐶐����A�擾�����s�I�u�W�F�N�g�e�v�f�ɂ���<BR>
     * �ϑ��萔���R�[�X�}�X�^�I�u�W�F�N�g�𐶐����ԋp����B<BR>
     * <BR>
     * �@@[�R���X�g���N�^�̈���]<BR>
     * �@@�ϑ��萔���R�[�X�}�X�^�s�F�@@�i�e�v�f�j<BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strCommissionProductCode - �萔�����i�R�[�h
     * @@return webbroker3.accountinfo.WEB3AccInfoCommissionCourseMaster[]
     * @@roseuid 413DA9DF0190
     */
    public static WEB3AccInfoCommissionCourseMaster[] getHandlingPossibleCommissionCourse(String l_strInstitutionCode, String l_strCommissionProductCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getHandlingPossibleCommissionCourse(String, String)";
        log.entering(STR_METHOD_NAME);

        List l_lisRecords = null;

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            String l_strQueryString = "institution_code = ? and comm_product_code = ?";
            Object[] l_queryDataContainer = new Object[] {
                l_strInstitutionCode,
                l_strCommissionProductCode
                };

            //�ȉ��̏����ŁA�ϑ��萔���R�[�X�}�X�^�e�[�u������������B
            //[����]
            //�،���ЃR�[�h = �،���ЃR�[�h
            //�萔�����i�R�[�h = �萔�����i�R�[�h
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                CommissionCourseMasterRow.TYPE,
                l_strQueryString,
                l_queryDataContainer
                );
        }
        catch (DataFindException l_ex)
        {
            log.error("�\�����Ȃ��V�X�e���G���[���������܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                WEB3AccInfoCommissionCourseMaster.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccInfoCommissionCourseMaster.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccInfoCommissionCourseMaster.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        int l_intSize = l_lisRecords.size();

        if (l_intSize == 0)
        {
            return null;
        }

        WEB3AccInfoCommissionCourseMaster[] l_commissionCourseMasters =
            new WEB3AccInfoCommissionCourseMaster[l_intSize];

        for (int i = 0; i < l_intSize; i++)
        {
            l_commissionCourseMasters[i] =
                new WEB3AccInfoCommissionCourseMaster((CommissionCourseMasterParams)l_lisRecords.get(i));
        }

        log.exiting(STR_METHOD_NAME);
        return l_commissionCourseMasters;

    }

    /**
     * (get�戵�\�ϑ��萔���R�[�X)<BR>
     * �istatic ���\�b�h�j<BR>
     * �،���ЂɊY������ϑ��萔���R�[�X�}�X�^��z��ɂĎ擾����B<BR>
     * <BR>
     * �P�j�ϑ��萔���R�[�X�}�X�^�e�[�u������������B<BR>
     * <BR>
     * �@@�@@������.�M�p�����J�݃t���O == true �̏ꍇ��<BR>
     * �@@�@@�@@�ȉ��̏����ŁA�ϑ��萔���R�[�X�}�X�^�e�[�u������������B<BR>
     * <BR>
     * �@@�@@�@@[����]<BR>
     * �@@�@@�@@�،���ЃR�[�h = ����.�،���ЃR�[�h<BR>
     * �@@�@@�@@�萔�����i�R�[�h = ����.�萔�����i�R�[�h<BR>
     * �@@�@@�@@�萔���敪 = 0 �܂��� 1�i�����܂��͐M�p�j<BR>
     * <BR>
     * �@@�@@����L�ɓ��Ă͂܂�Ȃ��ꍇ��<BR>
     * �@@�@@�@@�ȉ��̏����ŁA�ϑ��萔���R�[�X�}�X�^�e�[�u������������B<BR>
     * <BR>
     * �@@�@@�@@[����]<BR>
     * �@@�@@�@@�،���ЃR�[�h = ����.�،���ЃR�[�h<BR>
     * �@@�@@�@@�萔�����i�R�[�h = ����.�萔�����i�R�[�h<BR>
     * �@@�@@�@@�萔���敪 = 0 �i�����j<BR>
     * <BR>
     * �Q�jHashtable�𐶐����A�擾�����s�I�u�W�F�N�g�e�v�f�ɂ��Ĉϑ��萔���R�[�X�}�X�^�I�u�W�F�N�g�𐶐����ԋp����B
     * <BR>
     * �@@�@@[�R���X�g���N�^�̈���]<BR>
     * �@@�@@�ϑ��萔���R�[�X�}�X�^�s�F�@@�i�e�v�f�j<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)�j<BR>
     * �،���ЃR�[�h�B�j<BR>
     * @@param l_strCommissionProductCode - (�萔�����i�R�[�h)�j<BR>
     * �萔�����i�R�[�h�B�j<BR>
     * @@param l_strMarginOpenFlag - (�M�p�����J�݃t���O)�j<BR>
     * �M�p�����J�݃t���O�B�j<BR>
     * @@return WEB3AccInfoCommissionCourseMaster[]
     * @@throws WEB3BaseException
     */
    public static WEB3AccInfoCommissionCourseMaster[] getHandlingPossibleCommissionCourse(
        String l_strInstitutionCode,
        String l_strCommissionProductCode,
        boolean l_strMarginOpenFlag)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getHandlingPossibleCommissionCourse(String, String, boolean)";
        log.entering(STR_METHOD_NAME);

        List l_lisRecords = new ArrayList();

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            StringBuffer l_strWhere = new StringBuffer();
            l_strWhere.append(" institution_code = ? ");
            l_strWhere.append(" and comm_product_code = ? ");
            Object[] l_queryDataContainer = null;
            //����.�M�p�����J�݃t���O == true �̏ꍇ
            if (l_strMarginOpenFlag)
            {
                l_strWhere.append(" and ( commission_div = ? ");
                l_strWhere.append(" or commission_div= ? ) ");
                l_queryDataContainer = new Object[] {
                    l_strInstitutionCode,
                    l_strCommissionProductCode,
                    WEB3AccInfoCommissionDivDef.EQUITY_TRADE_COMMISSION,
                    WEB3AccInfoCommissionDivDef.MARGIN_TRADE_COMMISSION
                    };
            }
            //��L�ɓ��Ă͂܂�Ȃ��ꍇ
            else
            {
                l_strWhere.append(" and commission_div = ? ");
                l_queryDataContainer = new Object[] {
                    l_strInstitutionCode,
                    l_strCommissionProductCode,
                    WEB3AccInfoCommissionDivDef.EQUITY_TRADE_COMMISSION
                    };
            }

            //�ȉ��̏����ŁA�ϑ��萔���R�[�X�}�X�^�e�[�u������������B
            //[����]
            //�،���ЃR�[�h = �،���ЃR�[�h
            //�萔�����i�R�[�h = �萔�����i�R�[�h
            //�萔���敪 = 0 �܂��� 1�i�����܂��͐M�p�j������.�M�p�����J�݃t���O == true �̏ꍇ��
            //�萔���敪 = 0 �i�����j����L�ɓ��Ă͂܂�Ȃ��ꍇ��
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                CommissionCourseMasterRow.TYPE,
                l_strWhere.toString(),
                l_queryDataContainer
                );
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccInfoCommissionCourseMaster.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccInfoCommissionCourseMaster.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        int l_intSize = l_lisRecords.size();

        if (l_intSize == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        WEB3AccInfoCommissionCourseMaster[] l_commissionCourseMasters =
            new WEB3AccInfoCommissionCourseMaster[l_intSize];

        for (int i = 0; i < l_intSize; i++)
        {
            l_commissionCourseMasters[i] =
                new WEB3AccInfoCommissionCourseMaster((CommissionCourseMasterParams)l_lisRecords.get(i));
        }

        log.exiting(STR_METHOD_NAME);
        return l_commissionCourseMasters;
    }
}
@
