head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeBizDate.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �c�Ɠ��v�Z(WEB3GentradeBizDate.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/12 �����@@�ǘa(SRA) �V�K�쐬
Revesion History : 2004/07/12 羐� (���u) �ύX
Revesion History : 2005/07/07 �Г� (���u) calcFeqBizDate()��ǉ�
Revesion History : 2007/12/18 �Ӑ� (���u)�y���ʁz�d�l�ύX�E���f��No.294
Revesion History : 2008/08/15 ��іQ (���u)�y���ʁz�d�l�ύX�E���f��No.333
*/
package webbroker3.gentrade;

import java.sql.Timestamp;
import java.util.Calendar;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BizDateTypeDef;
import webbroker3.util.WEB3LogUtility;

/**
 * �c�Ɠ��v�Z���[�e�B���e�B�N���X<BR>
 */
public class WEB3GentradeBizDate
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GentradeBizDate.class);

    /**
     * �v�Z���<BR>
     */
    private Timestamp baseDate = null;

    /**
     * �R���X�g���N�^<BR>
     *<BR> 
     * @@param l_tsBaseBizDate ���
     */
    public WEB3GentradeBizDate(Timestamp l_tsBaseDate)
    {
        setBaseDate(l_tsBaseDate);
    }

    /**
     * ����̍Đݒ�B<BR>
     *<BR> 
     * @@param l_tsBaseDate ���
     */
    public void setBaseDate(Timestamp l_tsBaseDate)
    {
        this.baseDate = new Timestamp(l_tsBaseDate.getTime());
    }

    /**
     * ���������Z�E���Z�����c�Ɠ������ߕԂ��܂��B<BR>
     *<BR> 
     * �P�jthis.calc�c�Ɠ�()���R�[�����A�c�Ɠ����Z�o����B<BR>
     * <BR>
     * [calc�c�Ɠ��ɓn������]<BR>
     * ����F�@@this.�v�Z���<BR>
     * ���Z�^���Z�����F�@@�p�����[�^.���Z�^���Z����<BR>
     * <BR>
     * �Q�j�Z�o���ꂽ�c�Ɠ���ԋp����B<BR>
     * @@param l_intRollDays - ���Z�^���Z����<BR>
     * ex) 1�E�E�E���c�Ɠ���ԋp<BR>
     *      0�E�E�E���c�Ɠ���ԋp(��c�Ɠ��̏ꍇ�͗�O���X���[)<BR>
     * �@@�@@-1�E�E�E�O�c�Ɠ���ԋp<BR>
     * @@throws WEB3SystemLayerException
     */
    public Timestamp roll(int l_intRoll) throws WEB3SystemLayerException
    {
        return calcBizDate(this.baseDate, l_intRoll);
    }

    /**
     * (get�T���c�Ɠ�)<BR>
     * ������܂߂��T���c�Ɠ������߂�B<BR>
     * <BR>
     * �P�j�J�����_�[�N���X�̃C���X�^���X���擾���Athis.������Z�b�g����B<BR>
     * <BR>
     * �Q�j�J�����_�[�N���X.add()���\�b�h���R�[�����A���t����j���̓��t�܂Ŗ߂��B<BR>
     * <BR>
     * �R�jthis.calc�c�Ɠ�()���\�b�h���R�[�����A�c�Ɠ����Z�o����B<BR>
     * <BR>
     * [calc�c�Ɠ�()�ɓn������] <BR>
     * ����F�@@�J�����_�[�N���X.getTime().getTime()�̖߂�l�����ɐ�������Timestamp�N���X<BR>
     * ���Z�^���Z�����F�@@+1<BR>
     * <BR>
     * �S�j�Z�o���ꂽ�c�Ɠ���ԋp����B<BR>
     * @@return Timestamp
     * @@throws WEB3BaseException
     */
    public Timestamp getWeekStartBizDate() throws WEB3BaseException
    {
        String STR_METHOD_NAME = "getWeekStartBizDate()";

        log.entering(STR_METHOD_NAME);

        //�P�j�J�����_�[�N���X�̃C���X�^���X���擾���Athis.������Z�b�g����B
        Calendar l_bizDateCalendar = Calendar.getInstance();
        l_bizDateCalendar.setTime(this.baseDate);

        //�Q�j�J�����_�[�N���X.add()���\�b�h���R�[�����A���t����j���̓��t�܂Ŗ߂��B
        int l_intSunday = 0;
        switch (l_bizDateCalendar.get(Calendar.DAY_OF_WEEK))
        {
            case Calendar.MONDAY :
                l_intSunday = -1;
                break;
            case Calendar.TUESDAY :
                l_intSunday = -2;
                break;
            case Calendar.WEDNESDAY :
                l_intSunday = -3;
                break;
            case Calendar.THURSDAY :
                l_intSunday = -4;
                break;
            case Calendar.FRIDAY :
                l_intSunday = -5;
                break;
            case Calendar.SATURDAY :
                l_intSunday = -6;
                break;
            default :

                }
        // ���j���ɐݒ肷��
        l_bizDateCalendar.add(Calendar.DATE, l_intSunday);

        //�R�jthis.calc�c�Ɠ�()���\�b�h���R�[�����A�c�Ɠ����Z�o����B
        Timestamp l_tsWeekStartBizDate =
            calcBizDate(
                new Timestamp(l_bizDateCalendar.getTime().getTime()),
                1);

        log.exiting(STR_METHOD_NAME);

        // �S�j�Z�o���ꂽ�c�Ɠ���ԋp����B
        return l_tsWeekStartBizDate;
    }

    /**
     * ������܂߂��T���c�Ɠ������ߕԂ��܂��B<BR>
     *<BR> 
     * �P�j�J�����_�[�N���X�̃C���X�^���X���擾���Athis.������Z�b�g����B<BR>
     *  <BR>
     * �Q�j�J�����_�[�N���X.add()���\�b�h���R�[�����A���t��y�j��<BR>
     *    �̓��t�܂Ői�߂�B<BR>
     *  <BR>
     * �R�jthis.calc�c�Ɠ�()���\�b�h���R�[�����A�c�Ɠ����Z�o����B<BR>
     *  <BR>
     *    [calc�c�Ɠ�()�ɓn������] <BR>
     *    ����F�@@�J�����_�[�N���X.getTime().getTime()�̖߂�l<BR>
     *    �����ɐ�������Timestamp�N���X <BR>
     *    ���Z�^���Z�����F�@@-1 <BR>
     *  <BR>
     * �S�j�Z�o���ꂽ�c�Ɠ���ԋp����B<BR>
     * @@return �T���c�Ɠ�
     * @@throws WEB3SystemLayerException
     */
    public Timestamp getWeekEndBizDate() throws WEB3SystemLayerException
    {
        String STR_METHOD_NAME = "calcWeekEndBizDate()";

        log.entering(STR_METHOD_NAME);

        //�P�j�J�����_�[�N���X�̃C���X�^���X���擾���Athis.������Z�b�g����B
        Calendar l_bizDateCalendar = Calendar.getInstance();
        l_bizDateCalendar.setTime(this.baseDate);

        //�Q�j�J�����_�[�N���X.add()���\�b�h���R�[�����A���t��y�j��
        //�̓��t�܂Ői�߂�
        int l_intSaturday = 0;
        switch (l_bizDateCalendar.get(Calendar.DAY_OF_WEEK))
        {
            case Calendar.SUNDAY :
                l_intSaturday = 6;
                break;
            case Calendar.MONDAY :
                l_intSaturday = 5;
                break;
            case Calendar.TUESDAY :
                l_intSaturday = 4;
                break;
            case Calendar.WEDNESDAY :
                l_intSaturday = 3;
                break;
            case Calendar.THURSDAY :
                l_intSaturday = 2;
                break;
            case Calendar.FRIDAY :
                l_intSaturday = 1;
                break;
            default :

                }
        // �y�j���ɐݒ肷��
        l_bizDateCalendar.add(Calendar.DATE, l_intSaturday);

        //�R�jthis.calc�c�Ɠ�()���\�b�h���R�[�����A�c�Ɠ����Z�o����B
        Timestamp l_tsWeekEndBizDate =
            calcBizDate(
                new Timestamp(l_bizDateCalendar.getTime().getTime()),
                -1);

        log.exiting(STR_METHOD_NAME);

        // �S�j�Z�o���ꂽ�c�Ɠ���ԋp����B
        return l_tsWeekEndBizDate;
    }

    /**
     * (calc�c�Ɠ�) <BR>
     * ���������Z�^���Z�����c�Ɠ����Z�o���A�ԋp����B<BR>
     *  <BR>
     * �P�j�c�Ɠ��Ƃ��ăJ�E���g���ꂽ����̐� = �p�����[�^.���Z�^���Z<BR>
     *    �����̐�Βl�ƂȂ�܂ňȉ��̏������J��Ԃ��B<BR>
     *   ���p�����[�^.���Z�^���Z���� = 0�̏ꍇ�́A1�ƂȂ�܂ŌJ��Ԃ��B<BR>
     * <BR>
     * �P�|�P�j�p�����[�^.�����1�����Z(�܂��͌��Z)����B<BR>
     * �@@�����Z���ꂽ����̗j�����擾���A�y�E���������ꍇ�́A<BR>
     *      �y�E���ȊO�ɂȂ�܂�1�����Z(�܂��͌��Z)����B<BR>
     * <BR>
     * �P�|�Q�j�ȉ��̏����ɂăJ�����_�[�e�[�u�����������A�f�[�^<BR>
     *   ���擾�ł��Ȃ������ꍇ�́A�c�Ɠ��Ƃ��ăJ�E���g����B<BR>
     * <BR>
     *   [����] <BR>
     * �@@  ���t�@@�@@�@@�@@�@@ ���@@���Z(�܂��͌��Z)���ꂽ��� <BR>
     * �@@  �c�Ɠ��敪�@@���@@�h��c�Ɠ��h <BR>
     *  <BR>
     * �Q�j�Ō�ɉc�Ɠ��Ƃ��ăJ�E���g���ꂽ�����ԋp����B<BR>
     * DB�A�N�Z�X�Ɏ��s�����ꍇ<BR>
     * class    : WEB3SystemLayerException<BR>
     * tag      : SYSTEM_ERROR_80003<BR>
     *<BR> 
     * �c�Ɠ��̌v�Z���A�������c�Ɠ��Ń��[���l��0���w�肳�ꂽ�ꍇ<BR>
     * class    : WEB3SystemLayerException<BR>
     * tag      : SYSTEM_ERROR_80010<BR>
     * @@param l_tsBaseDate - ���
     * @@param l_intRoll - ���Z�E���Z����
     * @@return �c�Ɠ�
     * @@throws WEB3SystemLayerException
     */
    private Timestamp calcBizDate(Timestamp l_tsBaseDate, int l_intRoll)
        throws WEB3SystemLayerException
    {
        String STR_METHOD_NAME = "calcBizDate(Timestamp, int)";
        log.entering(STR_METHOD_NAME);

        //���=��c�Ɠ� ���� ���Z�^���Z���� = 0�̏ꍇ�͗�O���X���[����
        String l_strBizDateType =
            WEB3GentradeTradingTimeManagement.getBizDateType(l_tsBaseDate);
        if ((WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strBizDateType))
            && (l_intRoll == 0))
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //�J�����_�[�N���X�̃C���X�^���X���擾���A������Z�b�g����
        Calendar l_bizDateCalendar = Calendar.getInstance();
        l_bizDateCalendar.setTime(l_tsBaseDate);
        // DB�����p�Ɏ����b�~���b��������
        l_bizDateCalendar.set(Calendar.HOUR_OF_DAY, 0);
        l_bizDateCalendar.set(Calendar.MINUTE, 0);
        l_bizDateCalendar.set(Calendar.SECOND, 0);
        l_bizDateCalendar.set(Calendar.MILLISECOND, 0);
        Timestamp l_tsTmpBizDate;
        int l_intRollDays = l_intRoll;

        if (l_intRollDays >= 0)
        {
            while (l_intRollDays != 0)
            {
                //�����1�����Z����
                l_bizDateCalendar.add(Calendar.DATE, 1);
                l_tsTmpBizDate =
                    new Timestamp(l_bizDateCalendar.getTime().getTime());
                //�c�Ɠ��敪�擾
                l_strBizDateType =
                    WEB3GentradeTradingTimeManagement.getBizDateType(
                        l_tsTmpBizDate);
                //�c�Ɠ��̏ꍇ
                if (!WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strBizDateType))
                {
                    l_intRollDays = l_intRollDays - 1;
                }
            }

        }
        else
        {
            while (l_intRollDays != 0)
            {
                //�����1�����Z����
                l_bizDateCalendar.add(Calendar.DATE, -1);
                l_tsTmpBizDate =
                    new Timestamp(l_bizDateCalendar.getTime().getTime());
                //�c�Ɠ��敪�擾
                l_strBizDateType =
                    WEB3GentradeTradingTimeManagement.getBizDateType(
                        l_tsTmpBizDate);
                //�c�Ɠ��̏ꍇ
                if (!WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strBizDateType))
                {
                    l_intRollDays = l_intRollDays + 1;
                }
            }
        }

        //�Ō�ɉc�Ɠ��Ƃ��ăJ�E���g���ꂽ�����ԋp����
        log.exiting(STR_METHOD_NAME);
        return new Timestamp(l_bizDateCalendar.getTime().getTime());

    }

    /**
     * (get�w��c�Ɠ�) <BR>
     * �������A�����Ŏw�肳�ꂽ�N�E���E�����̑O�܂��͌�̓��t��<BR>
     * �Z�o���A�Ԃ��B ���t�̃J�E���g���@@�́A�В[����Ƃ���B<BR>
     *  <BR>
     *�P�j�@@����.����ɑ΂��A�ȉ��̓��t�v�Z�i�N�j���s���B <BR>
     * ����.���Z�^���Z��"���Z"�̏ꍇ�́A����.�N�� ��̓��t���Z�o����B<BR> 
     * ����.���Z�^���Z��"���Z"�̏ꍇ�́A����.�N�� �O�̓��t���Z�o����B<BR> 
     *  <BR>
     * �Q�j�@@�P�j�Ōv�Z�������t�ɑ΂��A�ȉ��̓��t�v�Z�i���j���s���B<BR> 
     * ����.���Z�^���Z��"���Z"�̏ꍇ�́A����.���� ��̓��t���Z�o����B<BR> 
     * ����.���Z�^���Z��"���Z"�̏ꍇ�́A����.���� �O�̓��t���Z�o����B<BR> 
     *  <BR>
     * �v�Z�������t���A <BR>
     *     �J�����_�[��ɑ��݂��Ȃ��ꍇ�i�U���R�P�����j <BR> 
     *     �́A���Y���̌����ŏI���t���Z�o���ȍ~�Ŏg�p����B<BR> 
     *  <BR>
     * �R�j�@@�Q�j�Ōv�Z�������t�ɑ΂��A�ȉ��̓��t�v�Z�i���j���s���B<BR> 
     * ����.���Z�^���Z��"���Z"�̏ꍇ�́A����.���� ��̓��t���Z�o����B<BR> 
     * ����.���Z�^���Z��"���Z"�̏ꍇ�́A����.���� �O�̓��t���Z�o����B<BR> 
     *  <BR>
     * �S�j�@@�R�j�Ōv�Z�������t����c�Ɠ��̏ꍇ�A���O�̉c�Ɠ����Z�o����B<BR> 
     *  <BR>
     * �T�j�@@�v�Z�������t��Ԃ��B <BR>   
     * <BR>   
     * @@param l_tsBaseDate - (���) <BR>   
     *       �c�Ɠ��v�Z�Ɏg�p������ <BR> 
     * @@param l_lngYear - (�N��) <BR>
     * @@param l_lngMonth - (����) <BR>
     * @@param l_lngDay - (����) <BR> 
     * @@param l_intFlag - (���Z�^���Z)<BR>
     *       ����ɑ΂��A���Z�i�����̓��t�����߂�j�̏ꍇ�A1���Z�b�g�B<BR> 
     *       ����ɑ΂��A���Z�i�ߋ��̓��t�����߂�j�̏ꍇ�A-1���Z�b�g�B<BR>
     *  <BR>  
     * @@throws WEB3SystemLayerException<BR> 
     *  <BR> 
     */
    public static Timestamp getAppointmentBizDate(
        Timestamp l_tsBaseDate,
        long l_lngYear,
        long l_lngMonth,
        long l_lngDay,
        int l_intFlag)
        throws WEB3SystemLayerException
    {
        String STR_METHOD_NAME = "getAppointmentBizDate(Timestamp, long, long, long, int)";
        log.entering(STR_METHOD_NAME);
        
        if((l_intFlag != 1) && (l_intFlag != -1))
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                WEB3GentradeBizDate.class.getName() + "." + STR_METHOD_NAME,
                "(���Z�^���Z = " + l_intFlag + ") �F " 
                + "����ɑ΂��A���Z�i�����̓��t�����߂�j�̏ꍇ�A1���Z�b�g�B"
                +"����ɑ΂��A���Z�i�ߋ��̓��t�����߂�j�̏ꍇ�A-1���Z�b�g�B");
        }
        
        //�J�����_�[�N���X�̃C���X�^���X���擾���A������Z�b�g����B
        Calendar l_bizDateCalendar = Calendar.getInstance();
        l_bizDateCalendar.setTime(l_tsBaseDate);
        
        //get �N�� ���� ����
        int l_intYear;
        int l_intMonth;
        int l_intDay;
        if(l_intFlag == 1)
        {
            l_intYear = Integer.parseInt(String.valueOf(l_lngYear));
            l_intMonth = Integer.parseInt(String.valueOf(l_lngMonth));
            l_intDay = Integer.parseInt(String.valueOf(l_lngDay));
        }
        else
        {
            l_intYear = - Integer.parseInt(String.valueOf(l_lngYear));
            l_intMonth = - Integer.parseInt(String.valueOf(l_lngMonth));
            l_intDay = - Integer.parseInt(String.valueOf(l_lngDay));
        }
          
        //���t���v�Z����
        l_bizDateCalendar.add(Calendar.YEAR, l_intYear);
        l_bizDateCalendar.add(Calendar.MONTH, l_intMonth);
        l_bizDateCalendar.add(Calendar.DATE, l_intDay);
        
        //�v�Z�������t����c�Ɠ��̏ꍇ�A���O�̉c�Ɠ����Z�o����B
        Timestamp l_tsAppointmentBizDate = new Timestamp(l_bizDateCalendar.getTime().getTime());
        String l_strBizDateType = WEB3GentradeTradingTimeManagement.getBizDateType(l_tsAppointmentBizDate);
        if(WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strBizDateType))
        {
            WEB3GentradeBizDate l_genBizDate = new WEB3GentradeBizDate(l_tsAppointmentBizDate);
            l_tsAppointmentBizDate = l_genBizDate.roll(-1);
            
            //�����b�~���b��߂�
            Calendar l_appBizDateCalendar = Calendar.getInstance();
            l_appBizDateCalendar.setTime(l_tsAppointmentBizDate);
            l_appBizDateCalendar.set(Calendar.HOUR,l_bizDateCalendar.get(Calendar.HOUR));
            l_appBizDateCalendar.set(Calendar.MINUTE,l_bizDateCalendar.get(Calendar.MINUTE));
            l_appBizDateCalendar.set(Calendar.SECOND,l_bizDateCalendar.get(Calendar.SECOND));
            l_appBizDateCalendar.set(Calendar.MILLISECOND,l_bizDateCalendar.get(Calendar.MILLISECOND));
            l_tsAppointmentBizDate = new Timestamp(l_appBizDateCalendar.getTime().getTime());
        }

        log.exiting(STR_METHOD_NAME);
        return l_tsAppointmentBizDate;
    }

    /**
     * (calc�O���c�Ɠ�) <BR>
     * ���������Z�^���Z�����c�Ɠ����Z�o���A�ԋp����B<BR>
     * <BR>
     * �����=��c�Ɠ� ���� ���Z�^���Z���� = 0�̏ꍇ�͗�O���X���[����B<BR>
     * <BR>
     * <BR>
     * �P�j�c�Ɠ��Ƃ��ăJ�E���g���ꂽ����̐� =<BR> 
     * �p�����[�^.���Z�^���Z�����̐�Βl�ƂȂ�܂ňȉ��̏������J��Ԃ��B<BR>
     * ���p�����[�^.���Z�^���Z���� = 0�̏ꍇ�́A1�ƂȂ�܂ŌJ��Ԃ��B<BR>
     * <BR>
     * �P�|�P�j�p�����[�^.�����1�����Z(�܂��͌��Z)����B<BR>
     * �����Z���ꂽ����̗j�����擾���A�y�E���������ꍇ�́A<BR>
     * �y�E���ȊO�ɂȂ�܂�1�����Z(�܂��͌��Z)����B<BR>
     * <BR>
     * �P�|�Q�j�ȉ��̏����ɂăJ�����_�[�e�[�u���C<BR>
     * �O���C�O�s��J�����_�[���������A<BR>
     * �ǂ�����f�[�^���擾�ł��Ȃ������ꍇ�́A�c�Ɠ��Ƃ��ăJ�E���g����B<BR>
     * <BR>
     * [�J�����_�[�e�[�u����������]<BR>
     * ���t = ���Z(�܂��͌��Z)���ꂽ���<BR>
     * �c�Ɠ��敪 = �h��c�Ɠ��h<BR>
     * <BR>
     * [�O���C�O�s��J�����_�[��������]<BR>
     * �،���ЃR�[�h = �،���ЃR�[�h<BR>
     * �s��R�[�h = �s��R�[�h<BR>
     * ���t = ���Z(�܂��͌��Z)���ꂽ���<BR>
     * �c�Ɠ��敪 = �h��c�Ɠ��h<BR>
     * <BR>
     * �Q�j�Ō�ɉc�Ɠ��Ƃ��ăJ�E���g���ꂽ�����ԋp����B<BR> 
     *<BR> 
     * @@param l_strInstitutionCode �،���ЃR�[�h
     * @@param l_strMarketCode �s��R�[�h
     * @@param l_tsBaseDate ���
     * @@param l_intRoll ���Z�E���Z����
     * @@return �c�Ɠ�
     * @@throws WEB3SystemLayerException
     */
    public Timestamp calcFeqBizDate(
        String l_strInstitutionCode,
        String l_strMarketCode,
        Timestamp l_tsBaseDate,
        int l_intRoll)
        throws WEB3SystemLayerException
    {
        String STR_METHOD_NAME = "calcFeqBizDate(String, String, Timestamp, int)";
        log.entering(STR_METHOD_NAME);

        //���=��c�Ɠ� ���� ���Z�^���Z���� = 0�̏ꍇ�͗�O���X���[����
        String l_strBizDateType = 
            WEB3GentradeTradingTimeManagement.getBizDateType(l_tsBaseDate);
        String l_strFeqBizDateType = 
            WEB3GentradeTradingTimeManagement.getFeqBizDateType(
                l_strInstitutionCode,
                l_strMarketCode,
                l_tsBaseDate);
        
        if ((WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strBizDateType) || 
            (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strFeqBizDateType)))
            && (l_intRoll == 0))
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //�J�����_�[�N���X�̃C���X�^���X���擾���A������Z�b�g����
        Calendar l_bizDateCalendar = Calendar.getInstance();
        l_bizDateCalendar.setTime(l_tsBaseDate);
        // DB�����p�Ɏ����b�~���b��������
        l_bizDateCalendar.set(Calendar.HOUR_OF_DAY, 0);
        l_bizDateCalendar.set(Calendar.MINUTE, 0);
        l_bizDateCalendar.set(Calendar.SECOND, 0);
        l_bizDateCalendar.set(Calendar.MILLISECOND, 0);
        Timestamp l_tsTmpBizDate;
        int l_intRollDays = l_intRoll;

        if (l_intRollDays >= 0)
        {
            while (l_intRollDays != 0)
            {
                //�����1�����Z����
                l_bizDateCalendar.add(Calendar.DATE, 1);
                l_tsTmpBizDate =
                    new Timestamp(l_bizDateCalendar.getTime().getTime());
                //�J�����_�[�e�[�u������c�Ɠ��敪�擾
                l_strBizDateType =
                    WEB3GentradeTradingTimeManagement.getBizDateType(
                        l_tsTmpBizDate);
                //�O���C�O�s��J�����_�[����c�Ɠ��敪�擾        
                l_strFeqBizDateType = 
                    WEB3GentradeTradingTimeManagement.getFeqBizDateType(
                        l_strInstitutionCode,
                        l_strMarketCode,
                        l_tsTmpBizDate);        
                //�c�Ɠ��̏ꍇ
                if (!WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strBizDateType) &&
                    !WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strFeqBizDateType))
                {
                    l_intRollDays = l_intRollDays - 1;
                }
            }
        }
        else
        {
            while (l_intRollDays != 0)
            {
                //�����1�����Z����
                l_bizDateCalendar.add(Calendar.DATE, -1);
                l_tsTmpBizDate =
                    new Timestamp(l_bizDateCalendar.getTime().getTime());
                //�J�����_�[�e�[�u������c�Ɠ��敪�擾
                l_strBizDateType =
                    WEB3GentradeTradingTimeManagement.getBizDateType(
                        l_tsTmpBizDate);
                //�O���C�O�s��J�����_�[����c�Ɠ��敪�擾
                l_strFeqBizDateType = 
                    WEB3GentradeTradingTimeManagement.getFeqBizDateType(
                        l_strInstitutionCode,
                        l_strMarketCode,
                        l_tsTmpBizDate);        
                //�c�Ɠ��̏ꍇ
                if (!WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strBizDateType) &&
                    !WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strFeqBizDateType))
                {
                    l_intRollDays = l_intRollDays + 1;
                }
            }
        }

        //�Ō�ɉc�Ɠ��Ƃ��ăJ�E���g���ꂽ�����ԋp����
        log.exiting(STR_METHOD_NAME);
        return new Timestamp(l_bizDateCalendar.getTime().getTime());
    }

    /**
     * (calcPTS�c�Ɠ�)<BR>
     * ���������Z�^���Z�����c�Ɠ����Z�o���A�ԋp����B  <BR>
     * <BR>
     * ���@@������ԊǗ�.getPTS�c�Ɠ��敪( )�̖߂�l��"��c�Ɠ�"���A  <BR>
     * ���@@���Z�^���Z���� = 0�̏ꍇ�͗�O���X���[����B  <BR>
     * �@@�@@�mgetPTS�c�Ɠ��敪( )�̈����n  <BR>
     * �@@�@@�@@���t�F�@@���  <BR>
     * <BR>
     * �P�j�@@�c�Ɠ��Ƃ��ăJ�E���g���ꂽ����̐� = �p�����[�^.���Z�^���Z����<BR>
     * �@@�@@�@@�̐�Βl�ƂȂ�܂ňȉ��̏������J��Ԃ��B  <BR>
     * ���p�����[�^.���Z�^���Z���� = 0�̏ꍇ�́A1�ƂȂ�܂ŌJ��Ԃ��B  <BR>
     * <BR>
     * �P�|�P�j�p�����[�^.�����1�����Z(�܂��͌��Z)����B  <BR>
     * �@@���@@������ԊǗ�.getPTS�c�Ɠ��敪( )���R�[�����A<BR>
     * �@@�@@�@@�@@�߂�l��"��c�Ɠ�"�̏ꍇ��  <BR>
     * �@@���@@"��c�Ɠ�"�ȊO�ɂȂ�܂�1�����Z(�܂��͌��Z)����B  <BR>
     * �@@�@@�@@�mgetPTS�c�Ɠ��敪( )�̈����n  <BR>
     * �@@�@@�@@�@@���t�F�@@1�����Z(�܂��͌��Z)�������  <BR>
     * �@@  <BR>
     * �Q�j�@@�Ō�ɉc�Ɠ��Ƃ��ăJ�E���g���ꂽ�����ԋp����B<BR>
     * <BR>
     * @@param l_tsBaseDate - ���
     * @@param l_intRoll - ���Z�E���Z����
     * @@return �c�Ɠ�
     * @@throws WEB3SystemLayerException
     */
    public Timestamp calcPTSBizDate(Timestamp l_tsBaseDate, int l_intRoll)
        throws WEB3SystemLayerException
    {
        String STR_METHOD_NAME = "calcPTSBizDate(Timestamp, int)";
        log.entering(STR_METHOD_NAME);

        //������ԊǗ�.getPTS�c�Ɠ��敪( )�̖߂�l��"��c�Ɠ�"���A
        //���Z�^���Z���� = 0�̏ꍇ�͗�O���X���[����B
        String l_strBizDateType =
            WEB3GentradeTradingTimeManagement.getPTSBizDateType(l_tsBaseDate);
        if ((WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strBizDateType))
            && (l_intRoll == 0))
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //�J�����_�[�N���X�̃C���X�^���X���擾���A������Z�b�g����
        Calendar l_bizDateCalendar = Calendar.getInstance();
        l_bizDateCalendar.setTime(l_tsBaseDate);
        // DB�����p�Ɏ����b�~���b��������
        l_bizDateCalendar.set(Calendar.HOUR_OF_DAY, 0);
        l_bizDateCalendar.set(Calendar.MINUTE, 0);
        l_bizDateCalendar.set(Calendar.SECOND, 0);
        l_bizDateCalendar.set(Calendar.MILLISECOND, 0);
        Timestamp l_tsTmpBizDate;
        int l_intRollDays = l_intRoll;

        if (l_intRollDays >= 0)
        {
            while (l_intRollDays != 0)
            {
                //�����1�����Z����
                l_bizDateCalendar.add(Calendar.DATE, 1);
                l_tsTmpBizDate =
                    new Timestamp(l_bizDateCalendar.getTime().getTime());
                //�c�Ɠ��敪�擾
                l_strBizDateType =
                    WEB3GentradeTradingTimeManagement.getPTSBizDateType(
                        l_tsTmpBizDate);
                //�c�Ɠ��̏ꍇ
                if (!WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strBizDateType))
                {
                    l_intRollDays = l_intRollDays - 1;
                }
            }
        }
        else
        {
            while (l_intRollDays != 0)
            {
                //�����1�����Z����
                l_bizDateCalendar.add(Calendar.DATE, -1);
                l_tsTmpBizDate =
                    new Timestamp(l_bizDateCalendar.getTime().getTime());
                //�c�Ɠ��敪�擾
                l_strBizDateType =
                    WEB3GentradeTradingTimeManagement.getPTSBizDateType(
                        l_tsTmpBizDate);
                //�c�Ɠ��̏ꍇ
                if (!WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strBizDateType))
                {
                    l_intRollDays = l_intRollDays + 1;
                }
            }
        }

        //�Ō�ɉc�Ɠ��Ƃ��ăJ�E���g���ꂽ�����ԋp����
        log.exiting(STR_METHOD_NAME);
        return new Timestamp(l_bizDateCalendar.getTime().getTime());
    }
}
@
