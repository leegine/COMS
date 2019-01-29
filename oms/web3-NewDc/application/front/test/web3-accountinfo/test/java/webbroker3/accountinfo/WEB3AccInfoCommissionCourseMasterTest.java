head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.06.08.30.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	1944d9c24957ec8;
filename	WEB3AccInfoCommissionCourseMasterTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : WEB3AccInfoCommissionCourseMasterTest.java
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/08/19 �k�v�u(���u) �V�K�쐬 ���f�� 132
*/
package webbroker3.accountinfo;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import test.util.TestDBUtility;

import webbroker3.accountinfo.data.CommissionCourseMasterParams;
import webbroker3.accountinfo.data.CommissionCourseMasterRow;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AccInfoCommissionCourseMasterTest extends TestBaseForMock
{

    WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AccInfoCommissionCourseMasterTest.class);
    public WEB3AccInfoCommissionCourseMasterTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    //�@@�@@�����������v�Z����B
    //�i�ύX�\�����؎w��� == 00�F�����܂���01�F���T�j�̏ꍇ
    //�A�@@�ύX�\�����ؓ����̌v�Z
    //(�v�Z���� < ���������j�̏ꍇ
    //�i�ύX�\�����؎w��� == 00�F�����j�̏ꍇ�A
    //��������(*1)�̗��c�Ɠ��iYYYYMMDD�j + �ύX�\�����؎��ԁiHHMMSS)
    public void testGetRegistEndTimestamp_C0001()
    {
        final String STR_METHOD_NAME = "testGetRegistEndTimestamp_C0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            Date l_date1 = WEB3DateUtility.getDate("20080819" + " 162245",
                WEB3GentradeTimeDef.DATE_FORMAT_YMD + " " + WEB3GentradeTimeDef.TIME_FORMAT_HMS );
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                    "getSystemTimestamp",
                    new Class[] {},
                    new Timestamp(l_date1.getTime()));
            CommissionCourseMasterParams l_commissionCourseMasterParams = TestDBUtility.getCommissionCourseMasterRow();

            WEB3AccInfoCommissionCourseMaster l_master =
                new WEB3AccInfoCommissionCourseMaster(l_commissionCourseMasterParams);
            
            assertEquals(l_master.getRegistEndTimestamp(), WEB3DateUtility.getDate(
                "20080820 000000",
                WEB3GentradeTimeDef.DATE_FORMAT_YMD + " " + WEB3GentradeTimeDef.TIME_FORMAT_HMS));
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //�@@�@@�����������v�Z����B
    //�i�ύX�\�����؎w��� == 00�F�����܂���01�F���T�j�ȊO�̏ꍇ
    //��������(*1)�̔N���iYYYYMM�j + �ύX�\�����؎w����iDD�j + �ύX�\�����؎��ԁiHHMMSS�j
    //�A�@@�ύX�\�����ؓ����̌v�Z
    //(�v�Z���� < ���������j�̏ꍇ
    //�i�ύX�\�����؎w��� == 00�F���� 01�F���T�j�ȊO�̏ꍇ�A
    //��������(*1)�̗����iYYYYMM�j + �ύX�\�����؎w����iDD�j + �ύX�\�����؎��ԁiHHMMSS�j
    public void testGetRegistEndTimestamp_C0002()
    {
        final String STR_METHOD_NAME = "testGetRegistEndTimestamp_C0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            Date l_date1 = WEB3DateUtility.getDate("20080819" + " 162245",
                    WEB3GentradeTimeDef.DATE_FORMAT_YMD + " " + WEB3GentradeTimeDef.TIME_FORMAT_HMS );
                TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                        "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                        "getSystemTimestamp",
                        new Class[] {},
                        new Timestamp(l_date1.getTime()));

            CommissionCourseMasterParams l_commissionCourseMasterParams = TestDBUtility.getCommissionCourseMasterRow();
            l_commissionCourseMasterParams.setRegistEndDaySpec("3");

            WEB3AccInfoCommissionCourseMaster l_master =
                new WEB3AccInfoCommissionCourseMaster(l_commissionCourseMasterParams);

            assertEquals(l_master.getRegistEndTimestamp(), WEB3DateUtility.getDate(
                "20080903  000000",
                WEB3GentradeTimeDef.DATE_FORMAT_YMD + " " + WEB3GentradeTimeDef.TIME_FORMAT_HMS));
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //�A�@@�ύX�\�����ؓ����̌v�Z
    //�i�@@�̌v�Z���� >= ���������j�̏ꍇ
    //�@@�̌v�Z���ʂ�Date�^�ɕϊ������l��ԋp����B
    public void testGetRegistEndTimestamp_C0003()
    {
        final String STR_METHOD_NAME = "testGetRegistEndTimestamp_C0003()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            Date l_date1 = WEB3DateUtility.getDate("20080819" + " 162245",
                    WEB3GentradeTimeDef.DATE_FORMAT_YMD + " " + WEB3GentradeTimeDef.TIME_FORMAT_HMS );
                TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                        "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                        "getSystemTimestamp",
                        new Class[] {},
                        new Timestamp(l_date1.getTime()));

            CommissionCourseMasterParams l_commissionCourseMasterParams = TestDBUtility.getCommissionCourseMasterRow();
            l_commissionCourseMasterParams.setRegistEndDaySpec("00");
            l_commissionCourseMasterParams.setRegistEndTime("235959");

            WEB3AccInfoCommissionCourseMaster l_master =
                new WEB3AccInfoCommissionCourseMaster(l_commissionCourseMasterParams);
            
            assertEquals(l_master.getRegistEndTimestamp(), WEB3DateUtility.getDate(
                "20080819 235959",
                WEB3GentradeTimeDef.DATE_FORMAT_YMD + " " + WEB3GentradeTimeDef.TIME_FORMAT_HMS));
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //�@@�@@�����������v�Z����B
    //�i�ύX�\�����؎w��� == 00�F�����܂���01�F���T�j�̏ꍇ
    //�A�@@�ύX�\�����ؓ����̌v�Z
    //(�v�Z���� < ���������j�̏ꍇ
    //�i�ύX�\�����؎w��� == 01�F���T�j�̏ꍇ�A
    //��������(*1)�̗��T���c�Ɠ��iYYYYMMDD�j+ �ύX�\�����؎��ԁiHHMMSS�j
    public void testGetRegistEndTimestamp_C0004()
    {
        final String STR_METHOD_NAME = "testGetRegistEndTimestamp_C0004()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            Date l_date1 = WEB3DateUtility.getDate("20080819" + " 162245",
                    WEB3GentradeTimeDef.DATE_FORMAT_YMD + " " + WEB3GentradeTimeDef.TIME_FORMAT_HMS );
                TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                        "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                        "getSystemTimestamp",
                        new Class[] {},
                        new Timestamp(l_date1.getTime()));

            CommissionCourseMasterParams l_commissionCourseMasterParams = TestDBUtility.getCommissionCourseMasterRow();
            l_commissionCourseMasterParams.setRegistEndDaySpec("01");
            WEB3AccInfoCommissionCourseMaster l_master =
                new WEB3AccInfoCommissionCourseMaster(l_commissionCourseMasterParams);

            assertEquals(l_master.getRegistEndTimestamp(), WEB3DateUtility.getDate(
                "20080825 000000",
                WEB3GentradeTimeDef.DATE_FORMAT_YMD + " " + WEB3GentradeTimeDef.TIME_FORMAT_HMS));
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //��������(*1)
    //�ύX�\�����؎w��� == 00�F�����j�̏ꍇ���ATradingSystem.getSystemTimestamp()��
    //�擾�������t����c�Ɠ��̏ꍇ�͗��c�Ɠ����Z�o����B
    public void testGetRegistEndTimestamp_C0005()
    {
        final String STR_METHOD_NAME = "testGetRegistEndTimestamp_C0005()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            Date l_date1 = WEB3DateUtility.getDate("20080817" + " 162245",
                    WEB3GentradeTimeDef.DATE_FORMAT_YMD + " " + WEB3GentradeTimeDef.TIME_FORMAT_HMS );
                TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                        "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                        "getSystemTimestamp",
                        new Class[] {},
                        new Timestamp(l_date1.getTime()));

            CommissionCourseMasterParams l_commissionCourseMasterParams = TestDBUtility.getCommissionCourseMasterRow();
            WEB3AccInfoCommissionCourseMaster l_master =
                new WEB3AccInfoCommissionCourseMaster(l_commissionCourseMasterParams);

            assertEquals(l_master.getRegistEndTimestamp(), WEB3DateUtility.getDate(
                 "20080818 000000",
                WEB3GentradeTimeDef.DATE_FORMAT_YMD + " " + WEB3GentradeTimeDef.TIME_FORMAT_HMS));
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //������.�M�p�����J�݃t���O == true �̏ꍇ��
    //�ȉ��̏����ŁA�ϑ��萔���R�[�X�}�X�^�e�[�u������������B
    //�،���ЃR�[�h = ����.�،���ЃR�[�h
    //�萔�����i�R�[�h = ����.�萔�����i�R�[�h
    //�萔���敪 = 0 �܂��� 1�i�����܂��͐M�p
    public void testGetHandlingPossibleCommissionCourse_C0001()
    {
        final String STR_METHOD_NAME = "testGetHandlingPossibleCommissionCourse_C0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(CommissionCourseMasterRow.TYPE);
            CommissionCourseMasterParams commissionCourseMasterParams = TestDBUtility.getCommissionCourseMasterRow();
            commissionCourseMasterParams.setCommissionDiv("1");
            TestDBUtility.insertWithDel(commissionCourseMasterParams);

            WEB3AccInfoCommissionCourseMaster[] l_commissionCourseMasters =
                WEB3AccInfoCommissionCourseMaster.getHandlingPossibleCommissionCourse("0D", "10", true);
            CommissionCourseMasterParams L_params = (CommissionCourseMasterParams)l_commissionCourseMasters[0].getDataSourceObject();
            assertEquals(L_params.getCommissionDiv(), "1");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //������.�M�p�����J�݃t���O == false �̏ꍇ��
    //�ȉ��̏����ŁA�ϑ��萔���R�[�X�}�X�^�e�[�u������������B
    //�،���ЃR�[�h = ����.�،���ЃR�[�h
    //�萔�����i�R�[�h = ����.�萔�����i�R�[�h
    //�萔���敪 = 0 �i�����j
    public void testGetHandlingPossibleCommissionCourse_C0002()
    {
        final String STR_METHOD_NAME = "testGetHandlingPossibleCommissionCourse_C0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(CommissionCourseMasterRow.TYPE);
            CommissionCourseMasterParams commissionCourseMasterParams = TestDBUtility.getCommissionCourseMasterRow();
            TestDBUtility.insertWithDel(commissionCourseMasterParams);

            WEB3AccInfoCommissionCourseMaster[] l_commissionCourseMasters =
                WEB3AccInfoCommissionCourseMaster.getHandlingPossibleCommissionCourse("0D", "10", false);
            CommissionCourseMasterParams L_params = (CommissionCourseMasterParams)l_commissionCourseMasters[0].getDataSourceObject();
            assertEquals(L_params.getCommissionDiv(), "0");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //�ϑ��萔���R�[�X�}�X�^�e�[�u��������record is null
    public void testGetHandlingPossibleCommissionCourse_C0003()
    {
        final String STR_METHOD_NAME = "testGetHandlingPossibleCommissionCourse_C0003()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(CommissionCourseMasterRow.TYPE);
            CommissionCourseMasterParams commissionCourseMasterParams = TestDBUtility.getCommissionCourseMasterRow();
            TestDBUtility.insertWithDel(commissionCourseMasterParams);

            WEB3AccInfoCommissionCourseMaster[] l_commissionCourseMasters =
                WEB3AccInfoCommissionCourseMaster.getHandlingPossibleCommissionCourse("00", "10", false);
            assertNull(l_commissionCourseMasters);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

}
@
