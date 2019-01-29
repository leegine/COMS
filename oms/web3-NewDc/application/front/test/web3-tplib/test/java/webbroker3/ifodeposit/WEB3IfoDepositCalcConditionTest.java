head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.28.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoDepositCalcConditionTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3IfoDepositCalcConditionTest
Author Name      : Daiwa Institute of Research
Revision History : 2008/08/20 �����΁i���u�j�V�K�쐬
*/
package webbroker3.ifodeposit;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;

import test.util.TestDBUtility;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3SessionTypeDef;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.BranchIndexDealtCondParams;
import webbroker3.gentrade.data.BranchIndexDealtCondRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

public class WEB3IfoDepositCalcConditionTest extends TestBaseForMock
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
            WEB3IfoDepositCalcConditionTest.class);

    public WEB3IfoDepositCalcConditionTest(String arg0)
    {
        super(arg0);
        // TODO Auto-generated constructor stub
    }
    WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition = null;
    protected void setUp() throws Exception
    {
        super.setUp();
        l_ifoDepositCalcCondition = new WEB3IfoDepositCalcCondition();

    }
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    //get�c�Ɠ�
    //����.�w��� == -1�̏ꍇ�Athis.�c�Ɠ�[T-1..T+2][0]��ԋp����B
    public void testGetBizDate_C0001()
    {
        final String STR_METHOD_NAME = "testGetBizDate_C0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            Date[] l_bizDates = new Date[4];
            l_bizDates[0] = WEB3DateUtility.getDate("20080817","yyyyMMdd");
            l_bizDates[1] = WEB3DateUtility.getDate("20080816","yyyyMMdd");
            l_bizDates[2] = WEB3DateUtility.getDate("20080815","yyyyMMdd");
            l_bizDates[3] = WEB3DateUtility.getDate("20080814","yyyyMMdd");
            l_ifoDepositCalcCondition.setBizDates(l_bizDates);
            int l_intReservedDate = -1;
            Date l_datGetBizDate = l_ifoDepositCalcCondition.getBizDate(l_intReservedDate);
            String l_getBizDate= WEB3DateUtility.formatDate(l_datGetBizDate,"yyyyMMdd");
            assertEquals("20080817",l_getBizDate);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    //get�c�Ɠ�
    //����.�w��� == 0�̏ꍇ�Athis.�c�Ɠ�[T-1..T+2][1]��ԋp����B
    public void testGetBizDate_C0002()
    {
        final String STR_METHOD_NAME = "testGetBizDate_C0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            Date[] l_bizDates = new Date[4];
            l_bizDates[0] = WEB3DateUtility.getDate("20080817","yyyyMMdd");
            l_bizDates[1] = WEB3DateUtility.getDate("20080816","yyyyMMdd");
            l_bizDates[2] = WEB3DateUtility.getDate("20080815","yyyyMMdd");
            l_bizDates[3] = WEB3DateUtility.getDate("20080814","yyyyMMdd");
            l_ifoDepositCalcCondition.setBizDates(l_bizDates);
            int l_intReservedDate = 0;
            Date l_datGetBizDate = l_ifoDepositCalcCondition.getBizDate(l_intReservedDate);
            String l_getBizDate= WEB3DateUtility.formatDate(l_datGetBizDate,"yyyyMMdd");
            assertEquals("20080816",l_getBizDate);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    //get�c�Ɠ�
    //����.�w��� == 1�̏ꍇ�Athis.�c�Ɠ�[T-1..T+2][2]��ԋp����B
    public void testGetBizDate_C0003()
    {
        final String STR_METHOD_NAME = "testGetBizDate_C0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            Date[] l_bizDates = new Date[4];
            l_bizDates[0] = WEB3DateUtility.getDate("20080817","yyyyMMdd");
            l_bizDates[1] = WEB3DateUtility.getDate("20080816","yyyyMMdd");
            l_bizDates[2] = WEB3DateUtility.getDate("20080815","yyyyMMdd");
            l_bizDates[3] = WEB3DateUtility.getDate("20080814","yyyyMMdd");
            l_ifoDepositCalcCondition.setBizDates(l_bizDates);
            int l_intReservedDate = 1;
            Date l_datGetBizDate = l_ifoDepositCalcCondition.getBizDate(l_intReservedDate);
            String l_getBizDate= WEB3DateUtility.formatDate(l_datGetBizDate,"yyyyMMdd");
            assertEquals("20080815",l_getBizDate);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    //get�c�Ɠ�
    //����.�w��� == 2�̏ꍇ�Athis.�c�Ɠ�[T-1..T+2][3]��ԋp����B
    public void testGetBizDate_C0004()
    {
        final String STR_METHOD_NAME = "testGetBizDate_C0004()";
        log.entering(STR_METHOD_NAME);

        try
        {
            Date[] l_bizDates = new Date[4];
            l_bizDates[0] = WEB3DateUtility.getDate("20080817","yyyyMMdd");
            l_bizDates[1] = WEB3DateUtility.getDate("20080816","yyyyMMdd");
            l_bizDates[2] = WEB3DateUtility.getDate("20080815","yyyyMMdd");
            l_bizDates[3] = WEB3DateUtility.getDate("20080814","yyyyMMdd");
            l_ifoDepositCalcCondition.setBizDates(l_bizDates);
            int l_intReservedDate = 2;
            Date l_datGetBizDate = l_ifoDepositCalcCondition.getBizDate(l_intReservedDate);
            String l_getBizDate= WEB3DateUtility.formatDate(l_datGetBizDate,"yyyyMMdd");
            assertEquals("20080814",l_getBizDate);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    //get�c�Ɠ�
    //����.�w��� == (-1,0,1,2)�ȊO�̏ꍇ�Anull��ԋp����
    public void testGetBizDate_C0005()
    {
        final String STR_METHOD_NAME = "testGetBizDate_C0005()";
        log.entering(STR_METHOD_NAME);

        try
        {
            Date[] l_bizDates = new Date[4];
            l_bizDates[0] = WEB3DateUtility.getDate("20080817","yyyyMMdd");
            l_bizDates[1] = WEB3DateUtility.getDate("20080816","yyyyMMdd");
            l_bizDates[2] = WEB3DateUtility.getDate("20080815","yyyyMMdd");
            l_bizDates[3] = WEB3DateUtility.getDate("20080814","yyyyMMdd");
            l_ifoDepositCalcCondition.setBizDates(l_bizDates);
            int l_intReservedDate = 5;
            Date l_datGetBizDate = l_ifoDepositCalcCondition.getBizDate(l_intReservedDate);
            assertEquals(null,l_datGetBizDate);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testGetBizDate_C0006()
    {
        final String STR_METHOD_NAME = "testGetBizDate_C0006()";
        log.entering(STR_METHOD_NAME);

        try
        {
            Date[] l_bizDates = null;
            l_ifoDepositCalcCondition.setBizDates(l_bizDates);
            int l_intReservedDate = 0;
            Date l_datGetBizDate = l_ifoDepositCalcCondition.getBizDate(l_intReservedDate);
            assertEquals(null,l_datGetBizDate);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    //get�K��؋���
    //-1��ԋp����B
    public void testGetIfoDepositPerUnit_C0001()
    {
        final String STR_METHOD_NAME = "testGetIfoDepositPerUnit_C0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            String l_strUnderlyingProductCode = "a";
            WEB3IfoDepositCalcConditionPerIndex[] l_ifoDepositCalcConditionPerIndexList = new WEB3IfoDepositCalcConditionPerIndex[0];
            l_ifoDepositCalcCondition.setIfoDepositCalcPerIndexList(l_ifoDepositCalcConditionPerIndexList);

            double l_dbGetIfoDepositPerUnit= l_ifoDepositCalcCondition.getIfoDepositPerUnit(l_strUnderlyingProductCode);
            assertEquals("-1",WEB3StringTypeUtility.formatNumber(l_dbGetIfoDepositPerUnit));
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    //get�K��؋���
    //�w���ʏ؋����v�Z����.�����Y�����R�[�h == ����.�����Y�����R�[�h�ƂȂ�
    public void testGetIfoDepositPerUnit_C0002()
    {
        final String STR_METHOD_NAME = "testGetIfoDepositPerUnit_C0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            String l_strUnderlyingProductCode = "a";
            double l_dblIfoDepositPerUnit = 10;
            WEB3IfoDepositCalcConditionPerIndex[] l_ifoDepositCalcConditionPerIndexList = new WEB3IfoDepositCalcConditionPerIndex[1];
            WEB3IfoDepositCalcConditionPerIndex l_ifoDepositCalcConditionPerIndex = new WEB3IfoDepositCalcConditionPerIndex();
            l_ifoDepositCalcConditionPerIndex.setUnderlyingProductCode(l_strUnderlyingProductCode);
            l_ifoDepositCalcConditionPerIndex.setIfoDepositPerUnit(l_dblIfoDepositPerUnit);
            l_ifoDepositCalcConditionPerIndexList[0] = l_ifoDepositCalcConditionPerIndex;
            l_ifoDepositCalcCondition.setIfoDepositCalcPerIndexList(l_ifoDepositCalcConditionPerIndexList);
            l_ifoDepositCalcConditionPerIndexList = l_ifoDepositCalcCondition.getIfoDepositCalcPerIndexList();

            double l_dbGetIfoDepositPerUnit= l_ifoDepositCalcCondition.getIfoDepositPerUnit(l_strUnderlyingProductCode);
            assertEquals("10",WEB3StringTypeUtility.formatNumber(l_dbGetIfoDepositPerUnit));
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    //get�K��؋������b�h
    //-1��ԋp����B
    public void testGetIfoDepositPerUnitRed_C0001()
    {
        final String STR_METHOD_NAME = "testGetIfoDepositPerUnitRed_C0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            String l_strUnderlyingProductCode = "a";
            WEB3IfoDepositCalcConditionPerIndex[] l_ifoDepositCalcConditionPerIndexList = new WEB3IfoDepositCalcConditionPerIndex[0];
            l_ifoDepositCalcCondition.setIfoDepositCalcPerIndexList(l_ifoDepositCalcConditionPerIndexList);

            double l_dbGetIfoDepositPerUnit= l_ifoDepositCalcCondition.getIfoDepositPerUnitRed(l_strUnderlyingProductCode);
            assertEquals("-1",WEB3StringTypeUtility.formatNumber(l_dbGetIfoDepositPerUnit));
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    //get�K��؋������b�h
    //�w���ʏ؋����v�Z����.�����Y�����R�[�h == ����.�����Y�����R�[�h�ƂȂ�
    //�w���ʏ؋����v�Z�����̋K��؋������b�h��ԋp����B
    public void testGetIfoDepositPerUnitRed_C0002()
    {
        final String STR_METHOD_NAME = "testGetIfoDepositPerUnitRed_C0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            String l_strUnderlyingProductCode = "a";
            double l_dblIfoDepositPerUnit = 10;
            WEB3IfoDepositCalcConditionPerIndex[] l_ifoDepositCalcConditionPerIndexList = new WEB3IfoDepositCalcConditionPerIndex[1];
            WEB3IfoDepositCalcConditionPerIndex l_ifoDepositCalcConditionPerIndex = new WEB3IfoDepositCalcConditionPerIndex();
            l_ifoDepositCalcConditionPerIndex.setUnderlyingProductCode(l_strUnderlyingProductCode);
            l_ifoDepositCalcConditionPerIndex.setIfoDepositPerUnitRed(l_dblIfoDepositPerUnit);
            l_ifoDepositCalcConditionPerIndexList[0] = l_ifoDepositCalcConditionPerIndex;
            l_ifoDepositCalcCondition.setIfoDepositCalcPerIndexList(l_ifoDepositCalcConditionPerIndexList);
            l_ifoDepositCalcConditionPerIndexList = l_ifoDepositCalcCondition.getIfoDepositCalcPerIndexList();

            double l_dbGetIfoDepositPerUnit= l_ifoDepositCalcCondition.getIfoDepositPerUnitRed(l_strUnderlyingProductCode);
            assertEquals("10",WEB3StringTypeUtility.formatNumber(l_dbGetIfoDepositPerUnit));
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    //get�K��؋������؋����s�����m�聄
    // break������Loop�������I�������ꍇ(�w��̌����Y�����R�[�h�����{�ΏۊO)�A-1��ԋp����B
    public void testGetIfoDepositPerUnitTemp_C0001()
    {
        final String STR_METHOD_NAME = "testGetIfoDepositPerUnitTemp_C0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            String l_strUnderlyingProductCode = "a";
            WEB3IfoDepositCalcConditionPerIndex[] l_ifoDepositCalcConditionPerIndexList = new WEB3IfoDepositCalcConditionPerIndex[0];
            l_ifoDepositCalcCondition.setIfoDepositCalcPerIndexList(l_ifoDepositCalcConditionPerIndexList);

            double l_dbGetIfoDepositPerUnit= l_ifoDepositCalcCondition.getIfoDepositPerUnitTemp(l_strUnderlyingProductCode);
            assertEquals("-1",WEB3StringTypeUtility.formatNumber(l_dbGetIfoDepositPerUnit));
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    //get�K��؋������؋����s�����m�聄
    //�w���ʏ؋����v�Z����.�����Y�����R�[�h == ����.�����Y�����R�[�h�ƂȂ�
    //�w���ʏ؋����v�Z����������΁Abreak���ĊY���̋K��؋������؋����s�����m�聄��ԋp����B
    public void testGetIfoDepositPerUnitTemp_C0002()
    {
        final String STR_METHOD_NAME = "testGetIfoDepositPerUnitTemp_C0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            String l_strUnderlyingProductCode = "a";
            double l_dblIfoDepositPerUnit = 10;
            WEB3IfoDepositCalcConditionPerIndex[] l_ifoDepositCalcConditionPerIndexList = new WEB3IfoDepositCalcConditionPerIndex[1];
            WEB3IfoDepositCalcConditionPerIndex l_ifoDepositCalcConditionPerIndex = new WEB3IfoDepositCalcConditionPerIndex();
            l_ifoDepositCalcConditionPerIndex.setUnderlyingProductCode(l_strUnderlyingProductCode);
            l_ifoDepositCalcConditionPerIndex.setIfoDepositPerUnitTemp(l_dblIfoDepositPerUnit);
            l_ifoDepositCalcConditionPerIndexList[0] = l_ifoDepositCalcConditionPerIndex;
            l_ifoDepositCalcCondition.setIfoDepositCalcPerIndexList(l_ifoDepositCalcConditionPerIndexList);
            l_ifoDepositCalcConditionPerIndexList = l_ifoDepositCalcCondition.getIfoDepositCalcPerIndexList();

            double l_dbGetIfoDepositPerUnit= l_ifoDepositCalcCondition.getIfoDepositPerUnitTemp(l_strUnderlyingProductCode);
            assertEquals("10",WEB3StringTypeUtility.formatNumber(l_dbGetIfoDepositPerUnit));
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    //get���X�ʏ؋����v�Z����
    //�����ɊY������l�����݂��Ȃ��ꍇ�A��O��throw����B
    public void testGetCalcConditionPerBranch_C0001()
    {
        final String STR_METHOD_NAME = "testGetCalcConditionPerBranch_C0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            String l_strConditionName = "a";
            Map l_map = new HashMap();
            String l_strkey = "a";
            String l_strvalue = "b";
            l_map.put(l_strkey,l_strvalue);
            l_ifoDepositCalcCondition.getCalcConditionPerBranch(l_strConditionName);
        }
        catch(WEB3BaseRuntimeException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    //get���X�ʏ؋����v�Z����
    //����.�������ږ��ɑΉ�����������ڒl���}�b�v(this.���X�ʏ؋����v�Z�����j��茟�����ԋp����B
    public void testGetCalcConditionPerBranch_C0002()
    {
        final String STR_METHOD_NAME = "testGetCalcConditionPerBranch_C0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            String l_strConditionName = "a";
            String l_strConditionValue = "a";
            l_ifoDepositCalcCondition.addCalcConditionPerBranch(l_strConditionName,l_strConditionValue);

            String l_strValue = (String)l_ifoDepositCalcCondition.getCalcConditionPerBranch(l_strConditionName);
            assertEquals("a",l_strValue);
        }

        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    //isSPAN�g�p�\
    //this.is�Ȉ�SPAN�v�Z���{( ) == false�A����
    //this.isSPAN�g���u��( ) == false�̏ꍇ�̂݁Atrue��ԋp����B
    public void testIsSPANUsable_C0001()
    {
        final String STR_METHOD_NAME = "testIsSPANUsable_C0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            boolean l_blnSimpleSPANCalcFlag = false;
            l_ifoDepositCalcCondition.setSimpleSPANCalcFlag(l_blnSimpleSPANCalcFlag);
            l_ifoDepositCalcCondition.isSimpleSPANCalc();
            boolean l_blnSPANTroubleFlag = false;
            l_ifoDepositCalcCondition.setSpanTroubleFlag(l_blnSPANTroubleFlag);
            l_ifoDepositCalcCondition.isSPANTrouble();
            boolean l_strValue = l_ifoDepositCalcCondition.isSPANUsable();
            assertEquals(true,l_strValue);
        }

        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    //isSPAN�g�p�\
    //this.is�Ȉ�SPAN�v�Z���{( ) == true�̏ꍇ�̂݁Atrue��ԋp����B
    public void testIsSPANUsable_C0002()
    {
        final String STR_METHOD_NAME = "testIsSPANUsable_C0002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            boolean l_blnSimpleSPANCalcFlag = true;
            l_ifoDepositCalcCondition.setSimpleSPANCalcFlag(l_blnSimpleSPANCalcFlag);
            l_ifoDepositCalcCondition.isSimpleSPANCalc();
            boolean l_blnSPANTroubleFlag = false;
            l_ifoDepositCalcCondition.setSpanTroubleFlag(l_blnSPANTroubleFlag);
            l_ifoDepositCalcCondition.isSPANTrouble();
            boolean l_strValue = l_ifoDepositCalcCondition.isSPANUsable();
            assertEquals(false,l_strValue);
        }

        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    //isSPAN�g�p�\
    //this.isSPAN�g���u��( ) == true�̏ꍇ�̂݁Atrue��ԋp����B
    public void testIsSPANUsable_C0003()
    {
        final String STR_METHOD_NAME = "testIsSPANUsable_C0003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            boolean l_blnSimpleSPANCalcFlag = false;
            l_ifoDepositCalcCondition.setSimpleSPANCalcFlag(l_blnSimpleSPANCalcFlag);
            l_ifoDepositCalcCondition.isSimpleSPANCalc();
            boolean l_blnSPANTroubleFlag = true;
            l_ifoDepositCalcCondition.setSpanTroubleFlag(l_blnSPANTroubleFlag);
            l_ifoDepositCalcCondition.isSPANTrouble();
            boolean l_strValue = l_ifoDepositCalcCondition.isSPANUsable();
            assertEquals(false,l_strValue);
        }

        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    //calc�؋����v�Z���
    //�[�ꎞ�ԑ�(������ԊǗ�.get����敪 == "�[��")�ȊO�̏ꍇ�A1
    public void testCalcIfoDepositCalcBaseDate_C0001()
    {
        final String STR_METHOD_NAME = "testCalcIfoDepositCalcBaseDate_C0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            MOCK_MANAGER.setIsMockUsed(true);
            Date[] l_bizDates = new Date[4];
            l_bizDates[0] = WEB3DateUtility.getDate("20080823","yyyyMMdd");
            l_bizDates[1] = WEB3DateUtility.getDate("20080824","yyyyMMdd");
            l_bizDates[2] = WEB3DateUtility.getDate("20080825","yyyyMMdd");
            l_bizDates[3] = WEB3DateUtility.getDate("20080826","yyyyMMdd");
            l_ifoDepositCalcCondition.setBizDates(l_bizDates);

            Date l_expectDate = WEB3DateUtility.getDate("20080824","yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_expectDate);

            l_ifoDepositCalcCondition.calcIfoDepositCalcBaseDate();
            int l_intreturn = l_ifoDepositCalcCondition.getIfoDepositBaseDate();
            assertEquals("1",WEB3StringTypeUtility.formatNumber(l_intreturn));
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        } 
    }
    //calc�؋����v�Z���
    //�����������ԑ�(������ԊǗ�.get������ != this.get�c�Ɠ�[T+0])�̏ꍇ�A2
    public void testCalcIfoDepositCalcBaseDate_C0002()
    {
        final String STR_METHOD_NAME = "testCalcIfoDepositCalcBaseDate_C0002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            MOCK_MANAGER.setIsMockUsed(true);
            Date[] l_bizDates = new Date[4];
            l_bizDates[0] = WEB3DateUtility.getDate("20080823","yyyyMMdd");
            l_bizDates[1] = WEB3DateUtility.getDate("20080824","yyyyMMdd");
            l_bizDates[2] = WEB3DateUtility.getDate("20080825","yyyyMMdd");
            l_bizDates[3] = WEB3DateUtility.getDate("20080826","yyyyMMdd");
            l_ifoDepositCalcCondition.setBizDates(l_bizDates);

            Date l_expectDate = WEB3DateUtility.getDate("20080820","yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_expectDate);

            l_ifoDepositCalcCondition.calcIfoDepositCalcBaseDate();
            int l_intreturn = l_ifoDepositCalcCondition.getIfoDepositBaseDate();
            assertEquals("2",WEB3StringTypeUtility.formatNumber(l_intreturn));
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
    }
    //calc�؋����v�Z���
    //�[�ꎞ�ԑ�(������ԊǗ�.get����敪 == "�[��")�̏ꍇ�A2
    public void testCalcIfoDepositCalcBaseDate_C0003()
    {
        final String STR_METHOD_NAME = "testCalcIfoDepositCalcBaseDate_C0003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            MOCK_MANAGER.setIsMockUsed(true);
            Date[] l_bizDates = new Date[4];
            l_bizDates[0] = WEB3DateUtility.getDate("20080823","yyyyMMdd");
            l_bizDates[1] = WEB3DateUtility.getDate("20080824","yyyyMMdd");
            l_bizDates[2] = WEB3DateUtility.getDate("20080825","yyyyMMdd");
            l_bizDates[3] = WEB3DateUtility.getDate("20080826","yyyyMMdd");
            l_ifoDepositCalcCondition.setBizDates(l_bizDates);

            Date l_expectDate = WEB3DateUtility.getDate("20080824","yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_expectDate);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setSessionType(WEB3SessionTypeDef.EVENING_SESSION);
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            l_ifoDepositCalcCondition.calcIfoDepositCalcBaseDate();
            int l_intreturn = l_ifoDepositCalcCondition.getIfoDepositBaseDate();
            assertEquals("2",WEB3StringTypeUtility.formatNumber(l_intreturn));
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
    }
    //WEB3IfoDepositCalcCondition�̕�����\����Ԃ��B
    public void testToString_C0001()
    {
        final String STR_METHOD_NAME = "testToString_C0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Date[] l_bizDates = new Date[1];
            l_bizDates[0] = WEB3DateUtility.getDate("20080823","yyyyMMdd"); 
            l_ifoDepositCalcCondition.setBizDates(l_bizDates);
            int l_intBaseDate = 1;
            l_ifoDepositCalcCondition.setIfoDepositCalcBaseDate(l_intBaseDate);
            double l_dblSPANFactor = 1;
            l_ifoDepositCalcCondition.setSPANFactor(l_dblSPANFactor);
            double l_dblSPANFactorRad = 1;
            l_ifoDepositCalcCondition.setSPANFactorRed(l_dblSPANFactorRad);
            double l_dblTransferPowerFactor = 1;
            l_ifoDepositCalcCondition.setTransferPowerFactor(l_dblTransferPowerFactor);
            double l_dblMinIfoDeposit = 1;
            l_ifoDepositCalcCondition.setMinIfoDeposit(l_dblMinIfoDeposit);
            double l_dblPreBizDateInfoDepositLackCharge = 1;
            l_ifoDepositCalcCondition.setPreBizDateInfoDepositLackCharge(l_dblPreBizDateInfoDepositLackCharge);
            double l_dblCurrentBizIfoDepositLackCharge = 1;
            l_ifoDepositCalcCondition.setCurrentBizIfoDepositLackCharge(l_dblCurrentBizIfoDepositLackCharge);
            WEB3IfoDepositCalcConditionPerIndex[] l_ifoDepositCalcConditionPerIndexList = new WEB3IfoDepositCalcConditionPerIndex[1];
            WEB3IfoDepositCalcConditionPerIndex l_ifoDepositCalcConditionPerIndex = new WEB3IfoDepositCalcConditionPerIndex();
            l_ifoDepositCalcCondition.setIfoDepositCalcPerIndexList(l_ifoDepositCalcConditionPerIndexList);

            String l_strToString = l_ifoDepositCalcCondition.toString();
            assertEquals("WEB3IfoDepositCalcCondition={bizDates={[0]=Sat Aug 23 00:00:00 GMT+08:00 2008}," +
                    "ifoDepositBaseDate=1," +
                    "isNewOpenTradingPowerAvailable=false,isRealPriceIfoDepositCalc=false," +
                    "isSimpleSPANCalc=false,isSPANTrouble=false,SPANFactor=1.0," +
                    "SPANFactorRed=1.0,transferPowerFactor=1.0,miniIfoDeposit=1.0," +
                    "preBizDateIfoDepositLackCharge=1.0,currentBizDateIfoDepositLackCharge=1.0," +
                    "perIndexCalcConditions={[0]=null},isIfoDepositMailFlag=false,isQuickReportReceived=false," +
                    "isLackChargeNonManagement=false,isIfodepositNonCalcSqProductFlag=false}",l_strToString);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    //WEB3IfoDepositCalcCondition�̕�����\����Ԃ��B
    public void testToString_C0002()
    {
        final String STR_METHOD_NAME = "testToString_C0002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Date[] l_bizDates = new Date[0];
            l_ifoDepositCalcCondition.setBizDates(l_bizDates);
            int l_intBaseDate = 1;
            l_ifoDepositCalcCondition.setIfoDepositCalcBaseDate(l_intBaseDate);
            double l_dblSPANFactor = 1;
            l_ifoDepositCalcCondition.setSPANFactor(l_dblSPANFactor);
            double l_dblSPANFactorRad = 1;
            l_ifoDepositCalcCondition.setSPANFactorRed(l_dblSPANFactorRad);
            double l_dblTransferPowerFactor = 1;
            l_ifoDepositCalcCondition.setTransferPowerFactor(l_dblTransferPowerFactor);
            double l_dblMinIfoDeposit = 1;
            l_ifoDepositCalcCondition.setMinIfoDeposit(l_dblMinIfoDeposit);
            double l_dblPreBizDateInfoDepositLackCharge = 1;
            l_ifoDepositCalcCondition.setPreBizDateInfoDepositLackCharge(l_dblPreBizDateInfoDepositLackCharge);
            double l_dblCurrentBizIfoDepositLackCharge = 1;
            l_ifoDepositCalcCondition.setCurrentBizIfoDepositLackCharge(l_dblCurrentBizIfoDepositLackCharge);
            WEB3IfoDepositCalcConditionPerIndex[] l_ifoDepositCalcConditionPerIndexList = new WEB3IfoDepositCalcConditionPerIndex[0];
            l_ifoDepositCalcCondition.setIfoDepositCalcPerIndexList(l_ifoDepositCalcConditionPerIndexList);

            String l_strToString = l_ifoDepositCalcCondition.toString();
            assertEquals("WEB3IfoDepositCalcCondition={bizDates={},ifoDepositBaseDate=1," +
                    "isNewOpenTradingPowerAvailable=false,isRealPriceIfoDepositCalc=false," +
                    "isSimpleSPANCalc=false,isSPANTrouble=false,SPANFactor=1.0,SPANFactorRed=1.0" +
                    ",transferPowerFactor=1.0,miniIfoDeposit=1.0,preBizDateIfoDepositLackCharge=1.0," +
                    "currentBizDateIfoDepositLackCharge=1.0,perIndexCalcConditions={}," +
                    "isIfoDepositMailFlag=false,isQuickReportReceived=false," +
                    "isLackChargeNonManagement=false,isIfodepositNonCalcSqProductFlag=false}",l_strToString);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testToString_C0003()
    {
        final String STR_METHOD_NAME = "testToString_C0003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Date[] l_bizDates = new Date[2];
            l_bizDates[0] = WEB3DateUtility.getDate("20080823","yyyyMMdd");
            l_bizDates[0] = WEB3DateUtility.getDate("20080824","yyyyMMdd");
            l_ifoDepositCalcCondition.setBizDates(l_bizDates);
            int l_intBaseDate = 1;
            l_ifoDepositCalcCondition.setIfoDepositCalcBaseDate(l_intBaseDate);
            double l_dblSPANFactor = 1;
            l_ifoDepositCalcCondition.setSPANFactor(l_dblSPANFactor);
            double l_dblSPANFactorRad = 1;
            l_ifoDepositCalcCondition.setSPANFactorRed(l_dblSPANFactorRad);
            double l_dblTransferPowerFactor = 1;
            l_ifoDepositCalcCondition.setTransferPowerFactor(l_dblTransferPowerFactor);
            double l_dblMinIfoDeposit = 1;
            l_ifoDepositCalcCondition.setMinIfoDeposit(l_dblMinIfoDeposit);
            double l_dblPreBizDateInfoDepositLackCharge = 1;
            l_ifoDepositCalcCondition.setPreBizDateInfoDepositLackCharge(l_dblPreBizDateInfoDepositLackCharge);
            double l_dblCurrentBizIfoDepositLackCharge = 1;
            l_ifoDepositCalcCondition.setCurrentBizIfoDepositLackCharge(l_dblCurrentBizIfoDepositLackCharge);
            WEB3IfoDepositCalcConditionPerIndex[] l_ifoDepositCalcConditionPerIndexList = new WEB3IfoDepositCalcConditionPerIndex[2];
            WEB3IfoDepositCalcConditionPerIndex l_ifoDepositCalcConditionPerIndex = new WEB3IfoDepositCalcConditionPerIndex();
            l_ifoDepositCalcCondition.setIfoDepositCalcPerIndexList(l_ifoDepositCalcConditionPerIndexList);

            String l_strToString = l_ifoDepositCalcCondition.toString();
            assertEquals("WEB3IfoDepositCalcCondition={bizDates={[0]=Sun Aug 24 00:00:00 GMT+08:00 2008,[1]=null}," +
                    "ifoDepositBaseDate=1,isNewOpenTradingPowerAvailable=false,isRealPriceIfoDepositCalc=false," +
                    "isSimpleSPANCalc=false,isSPANTrouble=false,SPANFactor=1.0,SPANFactorRed=1.0," +
                    "transferPowerFactor=1.0,miniIfoDeposit=1.0,preBizDateIfoDepositLackCharge=1.0," +
                    "currentBizDateIfoDepositLackCharge=1.0,perIndexCalcConditions={[0]=null,[1]=null}," +
                    "isIfoDepositMailFlag=false,isQuickReportReceived=false,isLackChargeNonManagement=false," +
                    "isIfodepositNonCalcSqProductFlag=false}",l_strToString);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    //�擾�����i���X�w���ʁj�戵�������Ƃ�Loop����
    //�i���X�w���ʁj�戵����.�K��؋����i����+�@@�O���j!= �i���X�w���ʁj�戵����.�K��؋����i����+�@@�P���j�̏ꍇ
    public void testCreateIfoDepositCalcConditionPerIndexList_C0001()
    {
        final String STR_METHOD_NAME = "testCreateIfoDepositCalcConditionPerIndexList_C0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(BranchIndexDealtCondRow.TYPE);
            BranchIndexDealtCondParams l_branchIndexDealtCondParams = TestDBUtility.getBranchIndexDealtCondRow();
            l_branchIndexDealtCondParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchIndexDealtCondParams);

            long l_lngInstId = 33L;
            String l_strBranchCode = "381";
            String l_strAccountCode = "2512246";
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_lngInstId,l_strBranchCode,l_strAccountCode);

            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_mainAccount,l_subAccountParams);

            l_ifoDepositCalcCondition.createIfoDepositCalcConditionPerIndexList(l_subAccount);

            WEB3IfoDepositCalcConditionPerIndex[] l_ifoDepositCalcConditionPerIndex = new WEB3IfoDepositCalcConditionPerIndex[1];

            l_ifoDepositCalcConditionPerIndex = l_ifoDepositCalcCondition.getIfoDepositCalcPerIndexList();

            assertEquals("0005",l_ifoDepositCalcConditionPerIndex[0].getUnderlyingProductCode());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }  
    }
    //��O���X���[����
    public void testCreateIfoDepositCalcConditionPerIndexList_C0002()
    {
        final String STR_METHOD_NAME = "testCreateIfoDepositCalcConditionPerIndexList_C0002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(BranchIndexDealtCondRow.TYPE);
            BranchIndexDealtCondParams l_branchIndexDealtCondParams = TestDBUtility.getBranchIndexDealtCondRow();
            l_branchIndexDealtCondParams.setBranchCode("10");
            TestDBUtility.insertWithDel(l_branchIndexDealtCondParams);

            long l_lngInstId = 33L;
            String l_strBranchCode = "381";
            String l_strAccountCode = "2512246";
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_lngInstId,l_strBranchCode,l_strAccountCode);

            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_mainAccount,l_subAccountParams);

            l_ifoDepositCalcCondition.createIfoDepositCalcConditionPerIndexList(l_subAccount);
  
        }
        catch(WEB3BaseRuntimeException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }  
    }
    //�i���X�w���ʁj�戵����.�K��؋����i����+�@@�O��)
    //�i���X�w���ʁj�戵����.�K��؋������b�h�i����+�@@�O���j
    public void testCreateIfoDepositCalcConditionPerIndexList_C0003()
    {
        final String STR_METHOD_NAME = "testCreateIfoDepositCalcConditionPerIndexList_C0003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(BranchIndexDealtCondRow.TYPE);
            BranchIndexDealtCondParams l_branchIndexDealtCondParams = TestDBUtility.getBranchIndexDealtCondRow();
            l_branchIndexDealtCondParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchIndexDealtCondParams);

            long l_lngInstId = 33L;
            String l_strBranchCode = "381";
            String l_strAccountCode = "2512246";
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_lngInstId,l_strBranchCode,l_strAccountCode);
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_mainAccount,l_subAccountParams);
            l_ifoDepositCalcCondition.createIfoDepositCalcConditionPerIndexList(l_subAccount);
            boolean l_QuickReportReceivedFlag = false;
            l_ifoDepositCalcCondition.setQuickReportReceivedFlag(l_QuickReportReceivedFlag);
            
            WEB3IfoDepositCalcConditionPerIndex[] l_ifoDepositCalcConditionPerIndex = new WEB3IfoDepositCalcConditionPerIndex[1];

            l_ifoDepositCalcConditionPerIndex = l_ifoDepositCalcCondition.getIfoDepositCalcPerIndexList();

            assertEquals("0005",l_ifoDepositCalcConditionPerIndex[0].getUnderlyingProductCode());
            assertEquals("11",WEB3StringTypeUtility.formatNumber(l_ifoDepositCalcConditionPerIndex[0].getIfoDepositPerUnitRed()));
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }  
    }

    //�i���X�w���ʁj�戵����.�K��؋����i����+�@@�O���j== �i���X�w���ʁj�戵����.�K��؋����i����+�@@�P���j�̏ꍇ
    public void testCreateIfoDepositCalcConditionPerIndexList_C0004()
    {
        final String STR_METHOD_NAME = "testCreateIfoDepositCalcConditionPerIndexList_C0004()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(BranchIndexDealtCondRow.TYPE);
            BranchIndexDealtCondParams l_branchIndexDealtCondParams = TestDBUtility.getBranchIndexDealtCondRow();
            l_branchIndexDealtCondParams.setIfoDepositPerUnit0(11L);
            l_branchIndexDealtCondParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchIndexDealtCondParams);

            long l_lngInstId = 33L;
            String l_strBranchCode = "381";
            String l_strAccountCode = "2512246";
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_lngInstId,l_strBranchCode,l_strAccountCode);

            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_mainAccount,l_subAccountParams);

            l_ifoDepositCalcCondition.createIfoDepositCalcConditionPerIndexList(l_subAccount);

            WEB3IfoDepositCalcConditionPerIndex[] l_ifoDepositCalcConditionPerIndex = new WEB3IfoDepositCalcConditionPerIndex[1];

            l_ifoDepositCalcConditionPerIndex = l_ifoDepositCalcCondition.getIfoDepositCalcPerIndexList();

            assertEquals("0005",l_ifoDepositCalcConditionPerIndex[0].getUnderlyingProductCode());
            assertEquals("11",WEB3StringTypeUtility.formatNumber(l_ifoDepositCalcConditionPerIndex[0].getIfoDepositPerUnitRed()));
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }  
    }
    
    public void testCreateIfoDepositCalcConditionPerIndexList_C0005()
    {
        final String STR_METHOD_NAME = "testCreateIfoDepositCalcConditionPerIndexList_C0005()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(BranchIndexDealtCondRow.TYPE);
            BranchIndexDealtCondParams l_branchIndexDealtCondParams = TestDBUtility.getBranchIndexDealtCondRow();
            l_branchIndexDealtCondParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchIndexDealtCondParams);

            long l_lngInstId = 33L;
            String l_strBranchCode = "381";
            String l_strAccountCode = "2512246";
            boolean l_QuickReportReceivedFlag = true;
            boolean l_ifoDepositMailFlagFlag = false;
            l_ifoDepositCalcCondition.setQuickReportReceivedFlag(l_QuickReportReceivedFlag);
            l_ifoDepositCalcCondition.setIfoDepositMailFlag(l_ifoDepositMailFlagFlag);
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_lngInstId,l_strBranchCode,l_strAccountCode);

            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_mainAccount,l_subAccountParams);

            l_ifoDepositCalcCondition.createIfoDepositCalcConditionPerIndexList(l_subAccount);

            WEB3IfoDepositCalcConditionPerIndex[] l_ifoDepositCalcConditionPerIndex = new WEB3IfoDepositCalcConditionPerIndex[1];

            l_ifoDepositCalcConditionPerIndex = l_ifoDepositCalcCondition.getIfoDepositCalcPerIndexList();

            assertEquals("0005",l_ifoDepositCalcConditionPerIndex[0].getUnderlyingProductCode());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }  
    }
    //calc�c�Ɠ�
    public void testCalcBizDate_C0001()
    {
        final String STR_METHOD_NAME = "testCalcBizDate_C0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            int l_intReservedDate = 0;
            l_ifoDepositCalcCondition.calcBizDate();
            Date l_dat = l_ifoDepositCalcCondition.getBizDate(l_intReservedDate);
            Date l_datGtlutils = GtlUtils.getTradingSystem().getBizDate();
            assertEquals(WEB3DateUtility.formatDate(l_datGtlutils,"yyyyMMdd"),WEB3DateUtility.formatDate(l_dat,"yyyyMMdd"));
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }  
    }
    
    public void testCalcBizDate_C0002()
    {
        final String STR_METHOD_NAME = "testCalcBizDate_C0002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            int l_intReservedDate = -1;
            l_ifoDepositCalcCondition.calcBizDate();
            Date l_dat = l_ifoDepositCalcCondition.getBizDate(l_intReservedDate);
            Date l_datGtlutils = GtlUtils.getTradingSystem().getBizDate();
            WEB3GentradeBizDate l_bizDateUtil =
                new WEB3GentradeBizDate(new Timestamp(l_datGtlutils.getTime()));
            assertEquals(WEB3DateUtility.formatDate(l_bizDateUtil.roll(-1),"yyyyMMdd"),WEB3DateUtility.formatDate(l_dat,"yyyyMMdd"));
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }  
    }
    
    public void testCalcBizDate_C0003()
    {
        final String STR_METHOD_NAME = "testCalcBizDate_C0003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            int l_intReservedDate = 1;
            l_ifoDepositCalcCondition.calcBizDate();
            Date l_dat = l_ifoDepositCalcCondition.getBizDate(l_intReservedDate);
            Date l_datGtlutils = GtlUtils.getTradingSystem().getBizDate();
            WEB3GentradeBizDate l_bizDateUtil =
                new WEB3GentradeBizDate(new Timestamp(l_datGtlutils.getTime()));
            assertEquals(WEB3DateUtility.formatDate(l_bizDateUtil.roll(1),"yyyyMMdd"),WEB3DateUtility.formatDate(l_dat,"yyyyMMdd"));
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }  
    }
    public void testCalcBizDate_C0004()
    {
        final String STR_METHOD_NAME = "testCalcBizDate_C0004()";
        log.entering(STR_METHOD_NAME);
        try
        {
            int l_intReservedDate = 2;
            l_ifoDepositCalcCondition.calcBizDate();
            Date l_dat = l_ifoDepositCalcCondition.getBizDate(l_intReservedDate);
            Date l_datGtlutils = GtlUtils.getTradingSystem().getBizDate();
            WEB3GentradeBizDate l_bizDateUtil =
                new WEB3GentradeBizDate(new Timestamp(l_datGtlutils.getTime()));
            assertEquals(WEB3DateUtility.formatDate(l_bizDateUtil.roll(2),"yyyyMMdd"),WEB3DateUtility.formatDate(l_dat,"yyyyMMdd"));
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }  
    }

}
@
