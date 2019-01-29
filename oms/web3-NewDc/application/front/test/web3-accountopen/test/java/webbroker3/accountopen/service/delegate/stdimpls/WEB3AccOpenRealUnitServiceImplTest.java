head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.07.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AccOpenRealUnitServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : ���������J�݂P���T�[�r�X�̃e�X�g�N���X(WEB3AccOpenRealUnitServiceImplTest.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/07/31 ���n�m (���u) �d�l�ύX�E�c�a�X�V�d�l ���f��No.030�A�c�a���C�A�E�g ���f��No.043
Revision History    : 2007/08/02 ���n�m (���u) �d�l�ύX�E�c�a�X�V�d�l ���f��No.031
Revision History    : 2007/08/02 ���n�m (���u) �d�l�ύX�E�c�a�X�V�d�l ���f��No.031
*/

package webbroker3.accountopen.service.delegate.stdimpls;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;

import test.util.TestDBUtility;

import webbroker3.accountopen.WEB3AccOpenBatchBranch;
import webbroker3.accountopen.WEB3AccOpenExpAccountOpen;
import webbroker3.accountopen.WEB3AccOpenHostAccRegVoucher;
import webbroker3.accountopen.data.AccOpenVoucherStatusParams;
import webbroker3.accountopen.data.BatchBranchParams;
import webbroker3.accountopen.data.ExpAccountOpenParams;
import webbroker3.accountopen.data.HostAccOpenAcceptParams;
import webbroker3.accountopen.data.HostAccRegVoucherParams;
import webbroker3.accountopen.data.HostAgencyNotifyVoucherParams;
import webbroker3.accountopen.data.HostConditionRegVoucherParams;
import webbroker3.accountopen.data.HostConditionRegVoucherRow;
import webbroker3.common.WEB3BaseException;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (���������J�݂P���T�[�r�X)<BR>
 * ���������J�݂P���T�[�r�X�̃e�X�g�N���X<BR>
 *
 * @@author ���n�m
 * @@version 1.0
 */
public class WEB3AccOpenRealUnitServiceImplTest extends TestBaseForMock
{

    /**
     * (���O�o�̓��[�e�B���e�B)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
                WEB3AccOpenRealUnitServiceImplTest.class);

    /**
     * ���������J�݂P���T�[�r�X
     */
    private WEB3AccOpenRealUnitServiceImpl l_serviceImpl = null;
    /**
     * @@param arg0
     */
    public WEB3AccOpenRealUnitServiceImplTest(String arg0)
    {
        super(arg0);
    }

    /**
     *
     */
    protected void setUp() throws Exception
    {
        super.setUp();
    }

    /**
     *
     */
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /**
     *
     */
    public void testInsertMainAccount_case0001()
    {

        String STR_METHOD_NAME = " testInsertMainAccount_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(ExpAccountOpenParams.TYPE);
            TestDBUtility.deleteAll(AccOpenVoucherStatusParams.TYPE);
            TestDBUtility.deleteAll(HostAccRegVoucherParams.TYPE);
            TestDBUtility.deleteAll(BatchBranchParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }

        l_serviceImpl = new WEB3AccOpenRealUnitServiceImpl();

        ExpAccountOpenParams l_expAccountOpenParams = TestDBUtility.getExpAccountOpenRow();

        l_expAccountOpenParams.setInstitutionCode("0D");
        l_expAccountOpenParams.setInstitutionId(33L);
        l_expAccountOpenParams.setBranchCode("381");
        l_expAccountOpenParams.setBranchId(33381L);
        l_expAccountOpenParams.setAccOpenRequestNumber("1234567890123");
        l_expAccountOpenParams.setAccountCode("2512246");
        l_expAccountOpenParams.setExAccountFlag(BooleanEnum.TRUE);
        l_expAccountOpenParams.setAccountDiv("1");
        l_expAccountOpenParams.setOrderDiv("1");
        l_expAccountOpenParams.setFamilyName("��");
        l_expAccountOpenParams.setGivenName("��");
        l_expAccountOpenParams.setFamilyNameAlt1("��");
        l_expAccountOpenParams.setGivenNameAlt1("��");
        l_expAccountOpenParams.setSex("1");
        l_expAccountOpenParams.setZipCode("1001238");
        l_expAccountOpenParams.setAddressLine1("�����s");
        l_expAccountOpenParams.setAddressLine1Kana("�Ƃ����傤��");
        l_expAccountOpenParams.setExperienceDivEquity("0");
        l_expAccountOpenParams.setExperienceDivMargin("0");
        l_expAccountOpenParams.setExperienceDivBond("0");
        l_expAccountOpenParams.setExperienceDivWt("0");
        l_expAccountOpenParams.setExperienceDivFundSk("0");
        l_expAccountOpenParams.setExperienceDivFundBd("0");
        l_expAccountOpenParams.setExperienceDivFo("0");
        l_expAccountOpenParams.setExperienceDivFEquity("0");
        l_expAccountOpenParams.setExperienceDivEtc("0");
        l_expAccountOpenParams.setExperienceFlagEquity(BooleanEnum.FALSE);
        l_expAccountOpenParams.setExperienceFlagMargin(BooleanEnum.FALSE);
        l_expAccountOpenParams.setExperienceFlagBond(BooleanEnum.FALSE);
        l_expAccountOpenParams.setExperienceFlagWt(BooleanEnum.FALSE);
        l_expAccountOpenParams.setExperienceFlagFundSk(BooleanEnum.FALSE);
        l_expAccountOpenParams.setExperienceFlagFundBd(BooleanEnum.FALSE);
        l_expAccountOpenParams.setExperienceFlagFo(BooleanEnum.FALSE);
        l_expAccountOpenParams.setExperienceFlagFEquity(BooleanEnum.FALSE);
        l_expAccountOpenParams.setExperienceFlagEtc(BooleanEnum.FALSE);
        l_expAccountOpenParams.setInterestFlagEquity(BooleanEnum.FALSE);
        l_expAccountOpenParams.setInterestFlagMinistock(BooleanEnum.FALSE);
        l_expAccountOpenParams.setInterestFlagMargin(BooleanEnum.FALSE);
        l_expAccountOpenParams.setInterestFlagBond(BooleanEnum.FALSE);
        l_expAccountOpenParams.setInterestFlagFund(BooleanEnum.FALSE);
        l_expAccountOpenParams.setInterestFlagFo(BooleanEnum.FALSE);
        l_expAccountOpenParams.setInterestFlagFEquity(BooleanEnum.FALSE);
        l_expAccountOpenParams.setInterestFlagEtc(BooleanEnum.FALSE);
        l_expAccountOpenParams.setInsiderFlag(BooleanEnum.FALSE);

        l_expAccountOpenParams.setExtItemDiv15("1");

        l_expAccountOpenParams.setCreatedTimestamp(
            WEB3DateUtility.getDate("20070731", "yyyyMMdd"));
        l_expAccountOpenParams.setLastUpdatedTimestamp(
            WEB3DateUtility.getDate("20070731", "yyyyMMdd"));

        AccOpenVoucherStatusParams l_accOpenVoucherStatusParams = new AccOpenVoucherStatusParams();

        l_accOpenVoucherStatusParams.setInstitutionCode("0D");
        l_accOpenVoucherStatusParams.setAccOpenRequestNumber("1234567890123");
        l_accOpenVoucherStatusParams.setRequestCode("43210");
        l_accOpenVoucherStatusParams.setSerialNo("333");
        l_accOpenVoucherStatusParams.setVoucherStatus("0");
        l_accOpenVoucherStatusParams.setCreatedTimestamp(
            WEB3DateUtility.getDate("20070731", "yyyyMMdd"));
        l_accOpenVoucherStatusParams.setLastUpdatedTimestamp(
            WEB3DateUtility.getDate("20070731", "yyyyMMdd"));

        HostAccRegVoucherParams l_hostAccRegVoucherParams = new HostAccRegVoucherParams();
        l_hostAccRegVoucherParams.setOrderRequestNumber("123456789");
        l_hostAccRegVoucherParams.setRequestCode("43210");
        l_hostAccRegVoucherParams.setInstitutionCode("0D");
        l_hostAccRegVoucherParams.setBranchCode("381");
        l_hostAccRegVoucherParams.setAccountCode("2512246");
        l_hostAccRegVoucherParams.setAccOpenRequestNumber("1234567890123");
        l_hostAccRegVoucherParams.setSerialNo("333");
        l_hostAccRegVoucherParams.setAccountName("��");
        l_hostAccRegVoucherParams.setAccountNameKana("��");
        l_hostAccRegVoucherParams.setCreatedTimestamp(
            WEB3DateUtility.getDate("20070731", "yyyyMMdd"));
        l_hostAccRegVoucherParams.setLastUpdatedTimestamp(
            WEB3DateUtility.getDate("20070731", "yyyyMMdd"));

        BatchBranchParams l_batchBranchParams = new BatchBranchParams();
        l_batchBranchParams.setInstitutionCode("0D");
        l_batchBranchParams.setBranchCode("381");
        l_batchBranchParams.setEquityOrderExeMailFlag(0);
        l_batchBranchParams.setEquityOrderUnexecMailFlag(1);
        l_batchBranchParams.setIfoOrderExecMailFlag(0);
        l_batchBranchParams.setIfoOrderUnexecMailFlag(1);
        l_batchBranchParams.setInformationMailFlag(0);
        l_batchBranchParams.setTransferCount(5);
        l_batchBranchParams.setTopPageId("100");
        l_batchBranchParams.setCreatedTimestamp(
            WEB3DateUtility.getDate("20070731", "yyyyMMdd"));
        l_batchBranchParams.setLastUpdatedTimestamp(
            WEB3DateUtility.getDate("20070731", "yyyyMMdd"));

        try
        {
            TestDBUtility.deleteAll(ExpAccountOpenParams.TYPE);
            TestDBUtility.insertWithDel(l_expAccountOpenParams);
            TestDBUtility.insertWithDel(l_accOpenVoucherStatusParams);
            TestDBUtility.insertWithDel(l_hostAccRegVoucherParams);
            TestDBUtility.insertWithDel(l_batchBranchParams);
        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }

        try
        {

            WEB3AccOpenExpAccountOpen l_accOpenExpAccountOpen = new WEB3AccOpenExpAccountOpen(l_expAccountOpenParams);

            String l_strOrderRequestNumber = "123456789";
            String l_strInstitutionCode = "0D";
            String l_strBranchCode = "381";
            String l_strAccountCode = "2512246";
            String l_strRequestCode = "43210";
            WEB3AccOpenHostAccRegVoucher l_accOpenHostAccRegVoucher =
                new WEB3AccOpenHostAccRegVoucher(
                    l_strOrderRequestNumber,
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode,
                    l_strRequestCode);

            WEB3AccOpenBatchBranch l_accOpenBatchBranch =
                new WEB3AccOpenBatchBranch(l_strInstitutionCode, l_strBranchCode);

            String l_strAccountId = "2512246";

            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            Calendar l_calendar =  Calendar.getInstance();
            l_calendar.set(2007,1-1,1);
            Date l_date = l_calendar.getTime();
            l_date.setHours(15);
            l_date.setMinutes(16);
            l_date.setSeconds(17);
            Timestamp l_simestamp = new Timestamp(l_date.getTime());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc." +
                    "gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[]{},
                l_simestamp);

            Method l_method =
                WEB3AccOpenRealUnitServiceImpl.class.getDeclaredMethod(
                    "insertMainAccount",
                    new Class[] {
                            WEB3AccOpenExpAccountOpen.class,
                            WEB3AccOpenHostAccRegVoucher.class,
                            WEB3AccOpenBatchBranch.class,
                            String.class,
                            String.class});

            l_method.setAccessible(true);
            l_method.invoke(
                l_serviceImpl,
                new Object[] {
                    l_accOpenExpAccountOpen,
                    l_accOpenHostAccRegVoucher,
                    l_accOpenBatchBranch,
                    l_strAccountId,
                    l_strAccountCode});

        }
        catch (NoSuchMethodException l_exNSME)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exNSME);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (InvocationTargetException l_exITE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exITE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (IllegalArgumentException l_exIAE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exIAE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exBE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }


        try
        {
            List l_lisActualResults = new ArrayList();

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisActualResults = l_queryProcessor.doFindAllQuery(MainAccountParams.TYPE);

            assertEquals(1, l_lisActualResults.size());

            MainAccountParams l_actualMainAccountParams =
                (MainAccountParams)l_lisActualResults.get(0);

            assertNull(l_actualMainAccountParams.getMarginAccOpenDate());
            assertNull(l_actualMainAccountParams.getIfoAccOpenDate());

            assertEquals("1", l_actualMainAccountParams.getOnlyMobileOpenDiv());
            assertEquals("account_open", l_actualMainAccountParams.getOnlyMblOpnDivLastUpdater());

            Date l_datExpected = WEB3DateUtility.getDate("20070101", "yyyyMMdd");
            l_datExpected.setHours(15);
            l_datExpected.setMinutes(16);
            l_datExpected.setSeconds(17);
            assertEquals(l_datExpected, l_actualMainAccountParams.getOnlyMblOpnDivTimestamp());

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(ExpAccountOpenParams.TYPE);
                TestDBUtility.deleteAll(AccOpenVoucherStatusParams.TYPE);
                TestDBUtility.deleteAll(HostAccRegVoucherParams.TYPE);
                TestDBUtility.deleteAll(BatchBranchParams.TYPE);
                TestDBUtility.deleteAll(MainAccountParams.TYPE);
            }
            catch (Exception l_exE)
            {
                log.error(TEST_END + STR_METHOD_NAME, l_exE);
                log.exiting(TEST_END + STR_METHOD_NAME);

                fail();
            }
        }
    }
    
    //�y�A���Ǘ��z���o�C����p�����Q���Ή�
    //���ʃR�[�h�� �g1�h
    public void testUpdateAccOpenOpenDateCase1()
    {
        final String STR_METHOD_NAME = "testUpdateAccOpenOpenDateCase1()";
        log.entering(STR_METHOD_NAME);
        try
        {
            HostConditionRegVoucherParams l_conditionRegVoucherParams =
                TestDBUtility.getHostConditionRegVoucherRow();
            l_conditionRegVoucherParams.setAccOpenRequestNumber("1");//���ʃR�[�h
            HostAccOpenAcceptParams l_hostAccOpenAcceptParams = TestDBUtility.getHostAccOpenAcceptRow();
            l_hostAccOpenAcceptParams.setRequestCode("GI84C");
            
            
            TestDBUtility.deleteAll(HostConditionRegVoucherRow.TYPE);
            TestDBUtility.insertWithDel(l_conditionRegVoucherParams);
            
            WEB3AccOpenRealUnitServiceImpl l_impl = new WEB3AccOpenRealUnitServiceImpl();
            
            l_impl.updateAccOpenOpenDate(l_hostAccOpenAcceptParams);
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            l_exc.printStackTrace();
            fail();
        }        
        log.exiting(STR_METHOD_NAME);
    }
    
    //�f�[�^�R�[�h�G���[
    public void testUpdateAccOpenOpenDateCase2()
    {
        final String STR_METHOD_NAME = "testUpdateAccOpenOpenDateCase2()";
        log.entering(STR_METHOD_NAME);
        try
        {
            HostConditionRegVoucherParams l_conditionRegVoucherParams =
                TestDBUtility.getHostConditionRegVoucherRow();
            l_conditionRegVoucherParams.setAccOpenRequestNumber("1");
            HostAccOpenAcceptParams l_hostAccOpenAcceptParams = TestDBUtility.getHostAccOpenAcceptRow();
            l_hostAccOpenAcceptParams.setRequestCode("GI84G");//�f�[�^�R�[�h�G���[
            
            
            TestDBUtility.deleteAll(HostConditionRegVoucherRow.TYPE);
            TestDBUtility.insertWithDel(l_conditionRegVoucherParams);
            
            WEB3AccOpenRealUnitServiceImpl l_impl = new WEB3AccOpenRealUnitServiceImpl();
            
            l_impl.updateAccOpenOpenDate(l_hostAccOpenAcceptParams);
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            l_exc.printStackTrace();
            fail();
        }        
        log.exiting(STR_METHOD_NAME);
    }

    public void testUpdateAccOpenOpenDateCase3()
    {
        final String STR_METHOD_NAME = "testUpdateAccOpenOpenDateCase3()";
        log.entering(STR_METHOD_NAME);
        try
        {
            HostAgencyNotifyVoucherParams l_hostAgencyNotifyVoucherParams =
                TestDBUtility.getHostAgencyNotifyVoucherRow();
            l_hostAgencyNotifyVoucherParams.setAccOpenRequestNumber("1");

            HostAccOpenAcceptParams l_hostAccOpenAcceptParams = TestDBUtility.getHostAccOpenAcceptRow();
            l_hostAccOpenAcceptParams.setRequestCode("GI85H");//�f�[�^�R�[�h�G���[
            
            
            TestDBUtility.deleteAll(HostAgencyNotifyVoucherParams.TYPE);
            TestDBUtility.insertWithDel(l_hostAgencyNotifyVoucherParams);
            
            WEB3AccOpenRealUnitServiceImpl l_impl = new WEB3AccOpenRealUnitServiceImpl();
            
            l_impl.updateAccOpenOpenDate(l_hostAccOpenAcceptParams);
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            l_exc.printStackTrace();
            fail();
        }        
        log.exiting(STR_METHOD_NAME);
    }
    public void testInsertMainAccount_case_1()
    {


        String STR_METHOD_NAME = " testInsertMainAccount_case_1()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(ExpAccountOpenParams.TYPE);
            TestDBUtility.deleteAll(AccOpenVoucherStatusParams.TYPE);
            TestDBUtility.deleteAll(HostAccRegVoucherParams.TYPE);
            TestDBUtility.deleteAll(BatchBranchParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }

        l_serviceImpl = new WEB3AccOpenRealUnitServiceImpl();

        ExpAccountOpenParams l_expAccountOpenParams = TestDBUtility.getExpAccountOpenRow();

        l_expAccountOpenParams.setInstitutionCode("0D");
        l_expAccountOpenParams.setInstitutionId(33L);
        l_expAccountOpenParams.setBranchCode("381");
        l_expAccountOpenParams.setBranchId(33381L);
        l_expAccountOpenParams.setAccOpenRequestNumber("1234567890123");
        l_expAccountOpenParams.setAccountCode("2512246");
        l_expAccountOpenParams.setExAccountFlag(BooleanEnum.TRUE);
        l_expAccountOpenParams.setAccountDiv("1");
        l_expAccountOpenParams.setOrderDiv("1");
        l_expAccountOpenParams.setFamilyName("��");
        l_expAccountOpenParams.setGivenName("��");
        l_expAccountOpenParams.setFamilyNameAlt1("��");
        l_expAccountOpenParams.setGivenNameAlt1("��");
        l_expAccountOpenParams.setSex("1");
        l_expAccountOpenParams.setZipCode("1001238");
        l_expAccountOpenParams.setAddressLine1("�����s");
        l_expAccountOpenParams.setAddressLine1Kana("�Ƃ����傤��");
        l_expAccountOpenParams.setExperienceDivEquity("0");
        l_expAccountOpenParams.setExperienceDivMargin("0");
        l_expAccountOpenParams.setExperienceDivBond("0");
        l_expAccountOpenParams.setExperienceDivWt("0");
        l_expAccountOpenParams.setExperienceDivFundSk("0");
        l_expAccountOpenParams.setExperienceDivFundBd("0");
        l_expAccountOpenParams.setExperienceDivFo("0");
        l_expAccountOpenParams.setExperienceDivFEquity("0");
        l_expAccountOpenParams.setExperienceDivEtc("0");
        l_expAccountOpenParams.setExperienceFlagEquity(BooleanEnum.FALSE);
        l_expAccountOpenParams.setExperienceFlagMargin(BooleanEnum.FALSE);
        l_expAccountOpenParams.setExperienceFlagBond(BooleanEnum.FALSE);
        l_expAccountOpenParams.setExperienceFlagWt(BooleanEnum.FALSE);
        l_expAccountOpenParams.setExperienceFlagFundSk(BooleanEnum.FALSE);
        l_expAccountOpenParams.setExperienceFlagFundBd(BooleanEnum.FALSE);
        l_expAccountOpenParams.setExperienceFlagFo(BooleanEnum.FALSE);
        l_expAccountOpenParams.setExperienceFlagFEquity(BooleanEnum.FALSE);
        l_expAccountOpenParams.setExperienceFlagEtc(BooleanEnum.FALSE);
        l_expAccountOpenParams.setInterestFlagEquity(BooleanEnum.FALSE);
        l_expAccountOpenParams.setInterestFlagMinistock(BooleanEnum.FALSE);
        l_expAccountOpenParams.setInterestFlagMargin(BooleanEnum.FALSE);
        l_expAccountOpenParams.setInterestFlagBond(BooleanEnum.FALSE);
        l_expAccountOpenParams.setInterestFlagFund(BooleanEnum.FALSE);
        l_expAccountOpenParams.setInterestFlagFo(BooleanEnum.FALSE);
        l_expAccountOpenParams.setInterestFlagFEquity(BooleanEnum.FALSE);
        l_expAccountOpenParams.setInterestFlagEtc(BooleanEnum.FALSE);
        l_expAccountOpenParams.setInsiderFlag(BooleanEnum.FALSE);

        l_expAccountOpenParams.setExtItemDiv15("1");

        l_expAccountOpenParams.setCreatedTimestamp(
            WEB3DateUtility.getDate("20070731", "yyyyMMdd"));
        l_expAccountOpenParams.setLastUpdatedTimestamp(
            WEB3DateUtility.getDate("20070731", "yyyyMMdd"));

        AccOpenVoucherStatusParams l_accOpenVoucherStatusParams = new AccOpenVoucherStatusParams();

        l_accOpenVoucherStatusParams.setInstitutionCode("0D");
        l_accOpenVoucherStatusParams.setAccOpenRequestNumber("1234567890123");
        l_accOpenVoucherStatusParams.setRequestCode("43210");
        l_accOpenVoucherStatusParams.setSerialNo("333");
        l_accOpenVoucherStatusParams.setVoucherStatus("0");
        l_accOpenVoucherStatusParams.setCreatedTimestamp(
            WEB3DateUtility.getDate("20070731", "yyyyMMdd"));
        l_accOpenVoucherStatusParams.setLastUpdatedTimestamp(
            WEB3DateUtility.getDate("20070731", "yyyyMMdd"));

        HostAccRegVoucherParams l_hostAccRegVoucherParams = new HostAccRegVoucherParams();
        l_hostAccRegVoucherParams.setOrderRequestNumber("123456789");
        l_hostAccRegVoucherParams.setRequestCode("43210");
        l_hostAccRegVoucherParams.setInstitutionCode("0D");
        l_hostAccRegVoucherParams.setBranchCode("381");
        l_hostAccRegVoucherParams.setAccountCode("2512246");
        l_hostAccRegVoucherParams.setAccOpenRequestNumber("1234567890123");
        l_hostAccRegVoucherParams.setSerialNo("333");
        l_hostAccRegVoucherParams.setAccountName("��");
        l_hostAccRegVoucherParams.setAccountNameKana("��");
        l_hostAccRegVoucherParams.setCreatedTimestamp(
            WEB3DateUtility.getDate("20070731", "yyyyMMdd"));
        l_hostAccRegVoucherParams.setLastUpdatedTimestamp(
            WEB3DateUtility.getDate("20070731", "yyyyMMdd"));
        
        //T0807311AIAO
        l_hostAccRegVoucherParams.setProamDiv("1");
        l_hostAccRegVoucherParams.setForeignerDivBroadcast("1");
        l_hostAccRegVoucherParams.setForeignerDivAviation("1");
        l_hostAccRegVoucherParams.setForeignerDivNtt("1");
        l_hostAccRegVoucherParams.setDividendTransferDiv("1");
        l_hostAccRegVoucherParams.setAgentDivPermanent("0");
        l_hostAccRegVoucherParams.setAgentDivLegal("0");

        BatchBranchParams l_batchBranchParams = new BatchBranchParams();
        l_batchBranchParams.setInstitutionCode("0D");
        l_batchBranchParams.setBranchCode("381");
        l_batchBranchParams.setEquityOrderExeMailFlag(0);
        l_batchBranchParams.setEquityOrderUnexecMailFlag(1);
        l_batchBranchParams.setIfoOrderExecMailFlag(0);
        l_batchBranchParams.setIfoOrderUnexecMailFlag(1);
        l_batchBranchParams.setInformationMailFlag(0);
        l_batchBranchParams.setTransferCount(5);
        l_batchBranchParams.setTopPageId("100");
        l_batchBranchParams.setCreatedTimestamp(
            WEB3DateUtility.getDate("20070731", "yyyyMMdd"));
        l_batchBranchParams.setLastUpdatedTimestamp(
            WEB3DateUtility.getDate("20070731", "yyyyMMdd"));

        try
        {
            TestDBUtility.insertWithDel(l_expAccountOpenParams);
            TestDBUtility.insertWithDel(l_accOpenVoucherStatusParams);
            TestDBUtility.insertWithDel(l_hostAccRegVoucherParams);
            TestDBUtility.insertWithDel(l_batchBranchParams);
        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }

        try
        {

            WEB3AccOpenExpAccountOpen l_accOpenExpAccountOpen = new WEB3AccOpenExpAccountOpen(l_expAccountOpenParams);

            String l_strOrderRequestNumber = "123456789";
            String l_strInstitutionCode = "0D";
            String l_strBranchCode = "381";
            String l_strAccountCode = "2512246";
            String l_strRequestCode = "43210";
            WEB3AccOpenHostAccRegVoucher l_accOpenHostAccRegVoucher =
                new WEB3AccOpenHostAccRegVoucher(
                    l_strOrderRequestNumber,
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode,
                    l_strRequestCode);

            WEB3AccOpenBatchBranch l_accOpenBatchBranch =
                new WEB3AccOpenBatchBranch(l_strInstitutionCode, l_strBranchCode);

            String l_strAccountId = "2512246";

            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            Calendar l_calendar =  Calendar.getInstance();
            l_calendar.set(2007,1-1,1);
            Date l_date = l_calendar.getTime();
            Timestamp l_simestamp = new Timestamp(l_date.getTime());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc." +
                    "gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[]{},
                l_simestamp);

            Method l_method =
                WEB3AccOpenRealUnitServiceImpl.class.getDeclaredMethod(
                    "insertMainAccount",
                    new Class[] {
                            WEB3AccOpenExpAccountOpen.class,
                            WEB3AccOpenHostAccRegVoucher.class,
                            WEB3AccOpenBatchBranch.class,
                            String.class,
                            String.class});

            l_method.setAccessible(true);
            l_method.invoke(
                l_serviceImpl,
                new Object[] {
                    l_accOpenExpAccountOpen,
                    l_accOpenHostAccRegVoucher,
                    l_accOpenBatchBranch,
                    l_strAccountId,
                    l_strAccountCode});

        }
        catch (WEB3BaseException l_exBE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exBE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }


        try
        {
            List l_lisActualResults = new ArrayList();

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisActualResults = l_queryProcessor.doFindAllQuery(MainAccountParams.TYPE);

            assertEquals(1, l_lisActualResults.size());

            MainAccountParams l_actualMainAccountParams =
                (MainAccountParams)l_lisActualResults.get(0);

            assertNull(l_actualMainAccountParams.getMarginAccOpenDate());
            assertNull(l_actualMainAccountParams.getIfoAccOpenDate());

            assertEquals("1", l_actualMainAccountParams.getOnlyMobileOpenDiv());
            assertEquals("account_open", l_actualMainAccountParams.getOnlyMblOpnDivLastUpdater());
            
            //�v���A�}�敪 = �ڋq�o�^�`�[(G11)�L���[�e�[�u��.�v���E�A�}�敪
            assertEquals("1", l_actualMainAccountParams.getProamDiv());
            //�����@@ = �ڋq�o�^�`�[(G11)�L���[�e�[�u��.�O���l�敪�i�����@@�j
            assertEquals("1", l_actualMainAccountParams.getBroadcastLaw());
            //�q��@@ = �ڋq�o�^�`�[(G11)�L���[�e�[�u��.�O���l�敪�i�q��@@�j
            assertEquals("1", l_actualMainAccountParams.getAviationLaw());
            //�m�s�s�@@ = �ڋq�o�^�`�[(G11)�L���[�e�[�u��.�O���l�敪�iNTT�@@�j
            assertEquals("1", l_actualMainAccountParams.getNttLaw());
            //�z�����U���w��敪 = �ڋq�o�^�`�[(G11)�L���[�e�[�u��.�z�����U���w��敪
            assertEquals("1", l_actualMainAccountParams.getDividendTransDesignate());
            //��C�㗝�l = �ڋq�o�^�`�[(G11)�L���[�e�[�u��.�㗝�l�敪�i��C�㗝�l�j
            assertEquals("0", l_actualMainAccountParams.getStandingProxy());
            //�@@��㗝�l = �ڋq�o�^�`�[(G11)�L���[�e�[�u��.�㗝�l�敪�i�@@��㗝�l�j
            assertEquals("0", l_actualMainAccountParams.getStatutoryAgent());
 

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(ExpAccountOpenParams.TYPE);
                TestDBUtility.deleteAll(AccOpenVoucherStatusParams.TYPE);
                TestDBUtility.deleteAll(HostAccRegVoucherParams.TYPE);
                TestDBUtility.deleteAll(BatchBranchParams.TYPE);
                TestDBUtility.deleteAll(MainAccountParams.TYPE);
            }
            catch (Exception l_exE)
            {
                log.error(TEST_END + STR_METHOD_NAME, l_exE);
                log.exiting(TEST_END + STR_METHOD_NAME);

                fail();
            }
        }  
    }

}
@
