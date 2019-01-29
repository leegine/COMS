head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.06.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AccOpenMailAddressRegistServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountopen.service.delegate.stdimpls;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesParams;

import test.util.TestDBUtility;

import webbroker3.accountopen.data.ExpAccountOpenParams;
import webbroker3.accountopen.data.ExpAccountOpenRow;
import webbroker3.accountopen.data.MailAddressRegiParams;
import webbroker3.accountopen.data.MailAddressRegiRow;
import webbroker3.accountopen.message.WEB3AccOpenApplyConfirmRequest;
import webbroker3.accountopen.message.WEB3AccOpenMailAddrRegCompleteRequest;
import webbroker3.accountopen.message.WEB3AccOpenMailAddrRegCompleteResponse;
import webbroker3.accountopen.message.WEB3AccOpenMailAddrRegInputRequest;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.MailInfoParams;
import webbroker3.gentrade.data.MailInfoRow;
import webbroker3.gentrade.data.MailProcRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (�����J�݃��[���A�h���X�o�^�T�[�r�XImpl)<BR>
 * �����J�݃��[���A�h���X�o�^�T�[�r�X�����N���X<BR>
 * <BR>
 * @@author �И���
 * @@version 1.0
 */
public class WEB3AccOpenMailAddressRegistServiceImplTest extends TestBaseForMock
{
    /**
     * (���O�o�̓��[�e�B���e�B)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccOpenMailAddressRegistServiceImplTest.class);

    public WEB3AccOpenMailAddressRegistServiceImplTest(String name)
    {
        super(name);
    }

    protected void setUp() throws Exception
    {
        TestDBUtility.deleteAll(MailAddressRegiParams.TYPE);
        TestDBUtility.deleteAll(ExpAccountOpenRow.TYPE);
        TestDBUtility.deleteAll(MainAccountParams.TYPE);
        TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
        TestDBUtility.deleteAll(MailProcRow.TYPE);
        TestDBUtility.deleteAll(MailInfoRow.TYPE);
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        TestDBUtility.deleteAll(MailAddressRegiParams.TYPE);
        TestDBUtility.deleteAll(ExpAccountOpenRow.TYPE);
        TestDBUtility.deleteAll(MainAccountParams.TYPE);
        TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
        TestDBUtility.deleteAll(MailProcRow.TYPE);
        TestDBUtility.deleteAll(MailInfoRow.TYPE);
        super.tearDown();
    }

    /**
     * �� �����̃��N�G�X�g�f�[�^���A�����J�݃��[���A�h���X�o�^���̓��N�G�X�g�̏ꍇ<BR>
     * �|get���͉��()���R�[������B<BR>
     */
    public void test_execute_0001()
    {
        String STR_METHOD_NAME = " test_execute_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        //�����J�݃��[���A�h���X�o�^���̓��N�G�X�g
        WEB3AccOpenMailAddrRegInputRequest l_request = new WEB3AccOpenMailAddrRegInputRequest();
        
        WEB3AccOpenMailAddressRegistServiceImpl l_impl = new WEB3AccOpenMailAddressRegistServiceImpl();
        try
        {
            l_impl.execute(l_request);
            fail();
        }
        catch (WEB3BaseException e)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02775,  e.getErrorInfo());
        }
        catch (Exception e)
        {
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * �� �����̃��N�G�X�g�f�[�^���A�����J�݃��[���A�h���X�o�^�������N�G�X�g�̏ꍇ <BR>
     * �|submit�o�^()���R�[������B <BR>
     */
    public void test_execute_0002()
    {
        String STR_METHOD_NAME = " test_execute_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        //�����J�݃��[���A�h���X�o�^�������N�G�X�g
        WEB3AccOpenMailAddrRegCompleteRequest l_request = new WEB3AccOpenMailAddrRegCompleteRequest();
        l_request.institutionCode = "1111";
        l_request.branchCode = "2222";
        
        WEB3AccOpenMailAddressRegistServiceImpl l_impl = new WEB3AccOpenMailAddressRegistServiceImpl();
        try
        {
            l_impl.execute(l_request);
            fail();
        }
        catch (WEB3BaseException e)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01700,  e.getErrorInfo());
        }
        catch (Exception e)
        {
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * ���N�G�X�g = null <BR>
     */
    public void test_execute_0003()
    {
        String STR_METHOD_NAME = " test_execute_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        //�����J�݃��[���A�h���X�o�^�������N�G�X�g
        WEB3AccOpenMailAddrRegCompleteRequest l_request = null;
        
        WEB3AccOpenMailAddressRegistServiceImpl l_impl = new WEB3AccOpenMailAddressRegistServiceImpl();
        try
        {
            l_impl.execute(l_request);
            fail();
        }
        catch (WEB3BaseException e)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017,  e.getErrorInfo());
        }
        catch (Exception e)
        {
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * ���N�G�X�g != �����J�݃��[���A�h���X�o�^�������N�G�X�g<BR>
     * && != �����J�݃��[���A�h���X�o�^���̓��N�G�X�g <BR>
     */
    public void test_execute_0004()
    {
        String STR_METHOD_NAME = " test_execute_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AccOpenApplyConfirmRequest l_request = new WEB3AccOpenApplyConfirmRequest();
        
        WEB3AccOpenMailAddressRegistServiceImpl l_impl = new WEB3AccOpenMailAddressRegistServiceImpl();
        try
        {
            l_impl.execute(l_request);
            fail();
        }
        catch (WEB3BaseException e)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018,  e.getErrorInfo());
        }
        catch (Exception e)
        {
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * get���͉��
     * validate( )
     */
    public void test_getInputScreen_0001()
    {
        String STR_METHOD_NAME = " test_getInputScreen_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        //�����J�݃��[���A�h���X�o�^���̓��N�G�X�g
        WEB3AccOpenMailAddrRegInputRequest l_request = new WEB3AccOpenMailAddrRegInputRequest();
        
        WEB3AccOpenMailAddressRegistServiceImpl l_impl = new WEB3AccOpenMailAddressRegistServiceImpl();
        try
        {
            l_impl.execute(l_request);
            fail();
        }
        catch (WEB3BaseException e)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02775,  e.getErrorInfo());
        }
        catch (Exception e)
        {
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * get���͉��
     * validate������t�\
     */
    public void test_getInputScreen_0002()
    {
        String STR_METHOD_NAME = " test_getInputScreen_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        //�����J�݃��[���A�h���X�o�^���̓��N�G�X�g
        WEB3AccOpenMailAddrRegInputRequest l_request = new WEB3AccOpenMailAddrRegInputRequest();
        l_request.branchCode = "1";
        l_request.institutionCode = "2";
        
        WEB3AccOpenMailAddressRegistServiceImpl l_impl = new WEB3AccOpenMailAddressRegistServiceImpl();
        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(false);
            l_impl.execute(l_request);
            fail();
        }
        catch (WEB3BaseException e)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80006,  e.getErrorInfo());
        }
        catch (Exception e)
        {
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * (submit�o�^) <BR>
     * validate() <BR>
     */
    public void test_submitRegist_0001()
    {
        String STR_METHOD_NAME = " test_submitRegist_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        //�����J�݃��[���A�h���X�o�^�������N�G�X�g
        WEB3AccOpenMailAddrRegCompleteRequest l_request = new WEB3AccOpenMailAddrRegCompleteRequest();
        l_request.institutionCode = "1111";
        l_request.branchCode = "2222";
        
        WEB3AccOpenMailAddressRegistServiceImpl l_impl = new WEB3AccOpenMailAddressRegistServiceImpl();
        try
        {
            l_impl.submitRegist(l_request);
            fail();
        }
        catch (WEB3BaseException e)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01700,  e.getErrorInfo());
        }
        catch (Exception e)
        {
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * (submit�o�^) <BR>
     * validate������t�\<BR>
     */
    public void test_submitRegist_0002()
    {
        String STR_METHOD_NAME = " test_submitRegist_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        //�����J�݃��[���A�h���X�o�^�������N�G�X�g
        WEB3AccOpenMailAddrRegCompleteRequest l_request = new WEB3AccOpenMailAddrRegCompleteRequest();
        l_request.institutionCode = "1111";
        l_request.branchCode = "2222";
        l_request.accountFamilyName="11";
        l_request.accountName="22223";
        l_request.accountType="1";
        l_request.mailAddress="3";
        
        WEB3AccOpenMailAddressRegistServiceImpl l_impl = new WEB3AccOpenMailAddressRegistServiceImpl();
        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(false);
            l_impl.submitRegist(l_request);
            fail();
        }
        catch (WEB3BaseException e)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80006,  e.getErrorInfo());
        }
        catch (Exception e)
        {
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * (submit�o�^) <BR>
     * ���[���A�h���X�Ƃ��ēK�؂łȂ��ꍇ�iisMailAddress() == false�j�A��O���X���[����<BR>
     */
    public void test_submitRegist_0003()
    {
        String STR_METHOD_NAME = " test_submitRegist_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        //�����J�݃��[���A�h���X�o�^�������N�G�X�g
        WEB3AccOpenMailAddrRegCompleteRequest l_request = new WEB3AccOpenMailAddrRegCompleteRequest();
        l_request.institutionCode = "1111";
        l_request.branchCode = "2222";
        l_request.accountFamilyName="11";
        l_request.accountName="22223";
        l_request.accountType="1";
        l_request.mailAddress="3";
        
        WEB3AccOpenMailAddressRegistServiceImpl l_impl = new WEB3AccOpenMailAddressRegistServiceImpl();
        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            l_impl.submitRegist(l_request);
            fail();
        }
        catch (WEB3BaseException e)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00777,  e.getErrorInfo());
        }
        catch (Exception e)
        {
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * (submit�o�^) <BR>
     * validate�g�уA�h���X<BR>
     */
    public void test_submitRegist_0004()
    {
        String STR_METHOD_NAME = " test_submitRegist_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        //�����J�݃��[���A�h���X�o�^�������N�G�X�g
        WEB3AccOpenMailAddrRegCompleteRequest l_request = new WEB3AccOpenMailAddrRegCompleteRequest();
        l_request.institutionCode = "1111";
        l_request.branchCode = "2222";
        l_request.accountFamilyName="11";
        l_request.accountName="22223";
        l_request.accountType="1";
        l_request.mailAddress="3x5x56x5@@pdx.com.cn";
        
        WEB3AccOpenMailAddressRegistServiceImpl l_impl = new WEB3AccOpenMailAddressRegistServiceImpl();
        try
        {
            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_params1 = TestDBUtility.getSystemPreferencesRow();
            l_params1.setName("mobile.mail.1");
            l_params1.setValue("docomo");
            TestDBUtility.insertWithDel(l_params1);
            
            SystemPreferencesParams l_params2 = TestDBUtility.getSystemPreferencesRow();
            l_params2.setName("mobile.mail.2");
            l_params2.setValue("pdx");
            TestDBUtility.insertWithDel(l_params2);
            
            SystemPreferencesParams l_params3 = TestDBUtility.getSystemPreferencesRow();
            l_params3.setName("mobile.mail.3");
            l_params3.setValue("vodafone");
            TestDBUtility.insertWithDel(l_params3);
            
            SystemPreferencesParams l_params4 = TestDBUtility.getSystemPreferencesRow();
            l_params4.setName("mobile.mail.3");
            l_params4.setValue("softbank");
            TestDBUtility.insertWithDel(l_params4);
            
            SystemPreferencesParams l_params5 = TestDBUtility.getSystemPreferencesRow();
            l_params4.setName("mobile.mail.5");
            l_params4.setValue("ezweb");
            TestDBUtility.insertWithDel(l_params5);
            
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            l_impl.submitRegist(l_request);
            fail();
        }
        catch (WEB3BaseException e)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03168,  e.getErrorInfo());
        }
        catch (Exception e)
        {
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * (submit�o�^) <BR>
     * validate�d�����[���A�h���X<BR>
     */
    public void test_submitRegist_0005()
    {
        String STR_METHOD_NAME = " test_submitRegist_0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        //�����J�݃��[���A�h���X�o�^�������N�G�X�g
        WEB3AccOpenMailAddrRegCompleteRequest l_request = new WEB3AccOpenMailAddrRegCompleteRequest();
        l_request.institutionCode = "1";
        l_request.branchCode = "222";
        l_request.accountFamilyName="11";
        l_request.accountName="22223";
        l_request.accountType="1";
        l_request.mailAddress="3x5x56x5@@xxx.com.cn";
        
        WEB3AccOpenMailAddressRegistServiceImpl l_impl = new WEB3AccOpenMailAddressRegistServiceImpl();
        try
        {
            TestDBUtility.deleteAll(ExpAccountOpenRow.TYPE);
            ExpAccountOpenParams l_expAccountOpenParams = TestDBUtility.getExpAccountOpenRow();
            l_expAccountOpenParams.setInstitutionCode("1");
            l_expAccountOpenParams.setBranchCode("222");
            l_expAccountOpenParams.setEmailAddress("3x5x56x5@@xxx.com.cn");
            TestDBUtility.insertWithDel(l_expAccountOpenParams);
            
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_params = TestDBUtility.getMainAccountRow();
            l_params.setEmailAddress("3x5x56x5@@pdx.com.cn");
            l_params.setInstitutionCode("1");
            TestDBUtility.insertWithDel(l_params);
            
            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            
            SystemPreferencesParams l_params1 = TestDBUtility.getSystemPreferencesRow();
            l_params1.setName("mobile.mail.1");
            l_params1.setValue("docomo");
            TestDBUtility.insertWithDel(l_params1);
            
            SystemPreferencesParams l_params2 = TestDBUtility.getSystemPreferencesRow();
            l_params2.setName("mobile.mail.2");
            l_params2.setValue("pdx");
            TestDBUtility.insertWithDel(l_params2);
            
            SystemPreferencesParams l_params3 = TestDBUtility.getSystemPreferencesRow();
            l_params3.setName("mobile.mail.3");
            l_params3.setValue("vodafone");
            TestDBUtility.insertWithDel(l_params3);
            
            SystemPreferencesParams l_params4 = TestDBUtility.getSystemPreferencesRow();
            l_params4.setName("mobile.mail.3");
            l_params4.setValue("softbank");
            TestDBUtility.insertWithDel(l_params4);
            
            SystemPreferencesParams l_params5 = TestDBUtility.getSystemPreferencesRow();
            l_params4.setName("mobile.mail.5");
            l_params4.setValue("ezweb");
            TestDBUtility.insertWithDel(l_params5);
            
            
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            l_impl.submitRegist(l_request);
            fail();
        }
        catch (WEB3BaseException e)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02443,  e.getErrorInfo());
        }
        catch (Exception e)
        {
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * (submit�o�^) <BR>
     * �}������s�̓��e�́ADB�X�V�d�l�u���[���A�h���X�o�^DB�X�V�iInsert�j�d�l.xls�v�Q��<BR>
     * !(���[���I�u�W�F�N�g���擾�ł����ꍇ�̂ݏ������{)
     */
    public void test_submitRegist_0006()
    {
        String STR_METHOD_NAME = " test_submitRegist_0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        //�����J�݃��[���A�h���X�o�^�������N�G�X�g
        WEB3AccOpenMailAddrRegCompleteRequest l_request = new WEB3AccOpenMailAddrRegCompleteRequest();
        l_request.institutionCode = "1";
        l_request.branchCode = "222";
        l_request.accountFamilyName="11";
        l_request.accountName="22223";
        l_request.accountType="1";
        l_request.mailAddress="3x5x56x5@@xxx.com.cn";
        l_request.brokerageCode="s";
        l_request.linkCode="k";
        
        WEB3AccOpenMailAddressRegistServiceImpl l_impl = new WEB3AccOpenMailAddressRegistServiceImpl();
        try
        {
            TestDBUtility.deleteAll(MailAddressRegiParams.TYPE);
            TestDBUtility.deleteAll(ExpAccountOpenRow.TYPE);
//            ExpAccountOpenParams l_expAccountOpenParams = TestDBUtility.getExpAccountOpenRow();
//            l_expAccountOpenParams.setInstitutionCode("1");
//            l_expAccountOpenParams.setBranchCode("222");
//            l_expAccountOpenParams.setEmailAddress("3x5x56x5@@xxx.com.cn");
//            TestDBUtility.insertWithDel(l_expAccountOpenParams);
            
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_params = TestDBUtility.getMainAccountRow();
            l_params.setEmailAddress("3x5x56x5@@pdx.com.cn");
            l_params.setInstitutionCode("1");
            TestDBUtility.insertWithDel(l_params);
            
            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            
            SystemPreferencesParams l_params1 = TestDBUtility.getSystemPreferencesRow();
            l_params1.setName("mobile.mail.1");
            l_params1.setValue("docomo");
            TestDBUtility.insertWithDel(l_params1);
            
            SystemPreferencesParams l_params2 = TestDBUtility.getSystemPreferencesRow();
            l_params2.setName("mobile.mail.2");
            l_params2.setValue("pdx");
            TestDBUtility.insertWithDel(l_params2);
            
            SystemPreferencesParams l_params3 = TestDBUtility.getSystemPreferencesRow();
            l_params3.setName("mobile.mail.3");
            l_params3.setValue("vodafone");
            TestDBUtility.insertWithDel(l_params3);
            
            SystemPreferencesParams l_params4 = TestDBUtility.getSystemPreferencesRow();
            l_params4.setName("mobile.mail.3");
            l_params4.setValue("softbank");
            TestDBUtility.insertWithDel(l_params4);
            
            SystemPreferencesParams l_params5 = TestDBUtility.getSystemPreferencesRow();
            l_params4.setName("mobile.mail.5");
            l_params4.setValue("ezweb");
            TestDBUtility.insertWithDel(l_params5);
            
            
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            WEB3AccOpenMailAddrRegCompleteResponse l_response = l_impl.submitRegist(l_request);
            
            
            String l_strWhere1 = " institution_code = ? and branch_code = ? and email_address = ? and delete_flag = ? ";
            Object[] l_objConds1 =  new Object[]{"1", "222", "3x5x56x5@@xxx.com.cn", BooleanEnum.FALSE};
            List l_lisRecordexcs1 = null;
            try
            {
                l_lisRecordexcs1 = Processors.getDefaultProcessor().doFindAllQuery(
                    MailAddressRegiRow.TYPE,
                    l_strWhere1,
                    l_objConds1);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            
            assertNotNull(l_response.mailRegiID);
            assertEquals("0", "" + WEB3DateUtility.compareToDay(GtlUtils.getSystemTimestamp(), l_response.currentDate));
            
            MailAddressRegiRow l_mailAddressRegiRow = (MailAddressRegiRow)l_lisRecordexcs1.get(0);
//            assertEquals("", l_mailAddressRegiRow.getMailAddressRegiId());
            assertEquals("1", l_mailAddressRegiRow.getInstitutionCode());
            assertEquals("222", l_mailAddressRegiRow.getBranchCode());
            assertEquals("3x5x56x5@@xxx.com.cn", l_mailAddressRegiRow.getEmailAddress());
            assertEquals("11", l_mailAddressRegiRow.getFamilyName());
            assertEquals("22223", l_mailAddressRegiRow.getGivenName());
            assertEquals("s", l_mailAddressRegiRow.getBrokerageTraderCode());
            assertEquals("k", l_mailAddressRegiRow.getLinkDistinctionCode());
            assertEquals("1", l_mailAddressRegiRow.getAccountDiv());
            assertEquals("0", l_mailAddressRegiRow.getDeleteFlag().intValue() + "");
            assertNotNull(l_mailAddressRegiRow.getLastUpdater());
            assertEquals("0", "" + WEB3DateUtility.compareToDay(GtlUtils.getSystemTimestamp(), l_mailAddressRegiRow.getCreatedTimestamp()));
            assertEquals("0", "" + WEB3DateUtility.compareToDay(GtlUtils.getSystemTimestamp(), l_mailAddressRegiRow.getLastUpdatedTimestamp()));
            
            
            String l_strWhere = " institution_code = ? and branch_code = ? and discernment_id = ? and account_code = ? ";
            Object[] l_objConds =  new Object[]{"1", "222", "1", "----"};
            List l_lisRecordexcs = null;
            try
            {
                l_lisRecordexcs = Processors.getDefaultProcessor().doFindAllQuery(
                    MailProcRow.TYPE,
                    l_strWhere,
                    l_objConds);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            assertEquals("0", l_lisRecordexcs.size() + "");
        }
        catch (WEB3BaseException e)
        {
            fail();
        }
        catch (Exception e)
        {
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * (submit�o�^) <BR>
     * �}������s�̓��e�́ADB�X�V�d�l�u���[���A�h���X�o�^DB�X�V�iInsert�j�d�l.xls�v�Q��<BR>
     * !(���[���I�u�W�F�N�g���擾�ł����ꍇ�̂ݏ������{)
     */
    public void test_submitRegist_0007()
    {
        String STR_METHOD_NAME = " test_submitRegist_0007()";
        log.entering(TEST_START + STR_METHOD_NAME);
        //�����J�݃��[���A�h���X�o�^�������N�G�X�g
        WEB3AccOpenMailAddrRegCompleteRequest l_request = new WEB3AccOpenMailAddrRegCompleteRequest();
        l_request.institutionCode = "1";
        l_request.branchCode = "222";
        l_request.accountFamilyName="11";
        l_request.accountName="22223";
        l_request.accountType="1";
        l_request.mailAddress="3x5x56x5@@xxx.com.cn";
        l_request.brokerageCode="s";
        l_request.linkCode="k";
        
        WEB3AccOpenMailAddressRegistServiceImpl l_impl = new WEB3AccOpenMailAddressRegistServiceImpl();
        try
        {
            
            TestDBUtility.deleteAll(MailProcRow.TYPE);
            MailInfoParams  l_mailInfoParams = new  MailInfoParams();
            l_mailInfoParams.setInstitutionCode("1");
            l_mailInfoParams.setSendmailDiv("0201");
            l_mailInfoParams.setDiscernmentId("1");
            l_mailInfoParams.setSubject("f");
            l_mailInfoParams.setLastUpdater("f");
            l_mailInfoParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_mailInfoParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_mailInfoParams);
            
            
            TestDBUtility.deleteAll(MailAddressRegiParams.TYPE);
            TestDBUtility.deleteAll(ExpAccountOpenRow.TYPE);
//            ExpAccountOpenParams l_expAccountOpenParams = TestDBUtility.getExpAccountOpenRow();
//            l_expAccountOpenParams.setInstitutionCode("1");
//            l_expAccountOpenParams.setBranchCode("222");
//            l_expAccountOpenParams.setEmailAddress("3x5x56x5@@xxx.com.cn");
//            TestDBUtility.insertWithDel(l_expAccountOpenParams);
            
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_params = TestDBUtility.getMainAccountRow();
            l_params.setEmailAddress("3x5x56x5@@pdx.com.cn");
            l_params.setInstitutionCode("1");
            TestDBUtility.insertWithDel(l_params);
            
            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            
            SystemPreferencesParams l_params1 = TestDBUtility.getSystemPreferencesRow();
            l_params1.setName("mobile.mail.1");
            l_params1.setValue("docomo");
            TestDBUtility.insertWithDel(l_params1);
            
            SystemPreferencesParams l_params2 = TestDBUtility.getSystemPreferencesRow();
            l_params2.setName("mobile.mail.2");
            l_params2.setValue("pdx");
            TestDBUtility.insertWithDel(l_params2);
            
            SystemPreferencesParams l_params3 = TestDBUtility.getSystemPreferencesRow();
            l_params3.setName("mobile.mail.3");
            l_params3.setValue("vodafone");
            TestDBUtility.insertWithDel(l_params3);
            
            SystemPreferencesParams l_params4 = TestDBUtility.getSystemPreferencesRow();
            l_params4.setName("mobile.mail.3");
            l_params4.setValue("softbank");
            TestDBUtility.insertWithDel(l_params4);
            
            SystemPreferencesParams l_params5 = TestDBUtility.getSystemPreferencesRow();
            l_params4.setName("mobile.mail.5");
            l_params4.setValue("ezweb");
            TestDBUtility.insertWithDel(l_params5);
            
            
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            WEB3AccOpenMailAddrRegCompleteResponse l_response = l_impl.submitRegist(l_request);
            
            
            String l_strWhere1 = " institution_code = ? and branch_code = ? and email_address = ? and delete_flag = ? ";
            Object[] l_objConds1 =  new Object[]{"1", "222", "3x5x56x5@@xxx.com.cn", BooleanEnum.FALSE};
            List l_lisRecordexcs1 = null;
            try
            {
                l_lisRecordexcs1 = Processors.getDefaultProcessor().doFindAllQuery(
                    MailAddressRegiRow.TYPE,
                    l_strWhere1,
                    l_objConds1);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            
            MailAddressRegiRow l_mailAddressRegiRow = (MailAddressRegiRow)l_lisRecordexcs1.get(0);
//            assertEquals("", l_mailAddressRegiRow.getMailAddressRegiId());
            assertEquals("1", l_mailAddressRegiRow.getInstitutionCode());
            assertEquals("222", l_mailAddressRegiRow.getBranchCode());
            assertEquals("3x5x56x5@@xxx.com.cn", l_mailAddressRegiRow.getEmailAddress());
            assertEquals("11", l_mailAddressRegiRow.getFamilyName());
            assertEquals("22223", l_mailAddressRegiRow.getGivenName());
            assertEquals("s", l_mailAddressRegiRow.getBrokerageTraderCode());
            assertEquals("k", l_mailAddressRegiRow.getLinkDistinctionCode());
            assertEquals("1", l_mailAddressRegiRow.getAccountDiv());
            assertEquals("0", l_mailAddressRegiRow.getDeleteFlag().intValue() + "");
            assertNotNull(l_mailAddressRegiRow.getLastUpdater());
            assertEquals("0", "" + WEB3DateUtility.compareToDay(GtlUtils.getSystemTimestamp(), l_mailAddressRegiRow.getCreatedTimestamp()));
            assertEquals("0", "" + WEB3DateUtility.compareToDay(GtlUtils.getSystemTimestamp(), l_mailAddressRegiRow.getLastUpdatedTimestamp()));
            
            
            String l_strWhere = " institution_code = ? and branch_code = ? and discernment_id = ? and account_code = ? ";
            Object[] l_objConds =  new Object[]{"1", "222", "1", "----"};
            List l_lisRecordexcs = null;
            try
            {
                l_lisRecordexcs = Processors.getDefaultProcessor().doFindAllQuery(
                    MailProcRow.TYPE,
                    l_strWhere,
                    l_objConds);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            MailProcRow l_mailProcRow = (MailProcRow)l_lisRecordexcs.get(0);
            assertEquals("1", l_mailProcRow.getInstitutionCode());
            assertEquals("222", l_mailProcRow.getBranchCode());
            assertEquals("0201", l_mailProcRow.getSendmailDiv());
            assertEquals("1", l_mailProcRow.getDiscernmentId());
            assertEquals("----", l_mailProcRow.getAccountCode());
            
            assertEquals(l_mailAddressRegiRow.getMailAddressRegiId() + "", l_mailProcRow.getMailId() + "");
            assertEquals("0", "" + WEB3DateUtility.compareToDay(GtlUtils.getTradingSystem().getBizDate(), l_mailProcRow.getDate1()));
            assertEquals(l_mailAddressRegiRow.getMailAddressRegiId() + "",  l_mailProcRow.getOrderId() + "");
            assertEquals("0", l_mailProcRow.getStatus());
            
            assertEquals("3x5x56x5@@xxx.com.cn", l_mailProcRow.getEmailAddress());
            assertEquals("3x5x56x5@@xxx.com.cn", l_mailProcRow.getMailText());
            assertEquals("0", l_mailProcRow.getDeleteFlag().intValue() + "");
            assertEquals("0", "" + WEB3DateUtility.compareToDay(GtlUtils.getSystemTimestamp(), l_mailProcRow.getCreatedTimestamp()));
            assertEquals("0", "" + WEB3DateUtility.compareToDay(GtlUtils.getSystemTimestamp(), l_mailProcRow.getLastUpdatedTimestamp()));
            
            
            assertEquals(l_mailAddressRegiRow.getMailAddressRegiId() + "", l_response.mailRegiID);
            assertEquals("0", "" + WEB3DateUtility.compareToDay(GtlUtils.getSystemTimestamp(), l_response.currentDate));
        }
        catch (WEB3BaseException e)
        {
            fail();
        }
        catch (Exception e)
        {
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * validate�d�����[���A�h���X
     * �d���A�h���X�����݂���i���[���A�h���X�d���`�F�b�N.get�d���A�h���X()�̖߂�l�̒���>0�j�ꍇ�A�G���[���X���[����B
     */
    public void test_validateRepeatAddress_0001()
    {
        String STR_METHOD_NAME = " test_validateRepeatAddress_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AccOpenMailAddressRegistServiceImpl l_impl = new WEB3AccOpenMailAddressRegistServiceImpl();
        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_params = TestDBUtility.getMainAccountRow();
            l_params.setEmailAddress("3x5x56x5@@xxx.com.cn");
            l_params.setInstitutionCode("1");
            l_params.setBranchCode("222");
            TestDBUtility.insertWithDelAndCommit(l_params);
            
            Method l_method =
                WEB3AccOpenMailAddressRegistServiceImpl.class.getDeclaredMethod("validateRepeatAddress", new Class[]{String.class, String.class, String.class});
            l_method.setAccessible(true);
            l_method.invoke(l_impl, new String[]{"1", "222", "3x5x56x5@@xxx.com.cn"});
            
            fail();
        }
        catch (InvocationTargetException e)
        {
            WEB3BaseException l =  (WEB3BaseException)e.getTargetException(); 
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02443, l.getErrorInfo()); 
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * validate�d�����[���A�h���X
     * �����J�݌����q�e�[�u�����ȉ��̏����ŏd���A�h���X�������s���B 
     */
    public void test_validateRepeatAddress_0002()
    {
        String STR_METHOD_NAME = " test_validateRepeatAddress_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AccOpenMailAddressRegistServiceImpl l_impl = new WEB3AccOpenMailAddressRegistServiceImpl();
        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(ExpAccountOpenRow.TYPE);
            ExpAccountOpenParams l_expAccountOpenParams = TestDBUtility.getExpAccountOpenRow();
            l_expAccountOpenParams.setInstitutionCode("1");
            l_expAccountOpenParams.setBranchCode("222");
            l_expAccountOpenParams.setEmailAddress("3x5x56x5@@xxx.com.cn");
            TestDBUtility.insertWithDel(l_expAccountOpenParams);
            
            Method l_method =
                WEB3AccOpenMailAddressRegistServiceImpl.class.getDeclaredMethod("validateRepeatAddress", new Class[]{String.class, String.class, String.class});
            l_method.setAccessible(true);
            l_method.invoke(l_impl, new String[]{"1", "222", "3x5x56x5@@xxx.com.cn"});
            
            fail();
        }
        catch (InvocationTargetException e)
        {
            WEB3BaseException l =  (WEB3BaseException)e.getTargetException(); 
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02443, l.getErrorInfo()); 
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * validate�d�����[���A�h���X
     * ���[���A�h���X�o�^�e�[�u�����ȉ��̏����ŏd���A�h���X�������s���B 
     */
    public void test_validateRepeatAddress_0003()
    {
        String STR_METHOD_NAME = " test_validateRepeatAddress_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AccOpenMailAddressRegistServiceImpl l_impl = new WEB3AccOpenMailAddressRegistServiceImpl();
        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(ExpAccountOpenRow.TYPE);

            MailAddressRegiParams l_mailAddressRegiParams = new MailAddressRegiParams();
            l_mailAddressRegiParams.setMailAddressRegiId(1111);
            l_mailAddressRegiParams.setInstitutionCode("1");
            l_mailAddressRegiParams.setBranchCode("222");
            l_mailAddressRegiParams.setEmailAddress("3x5x56x5@@xxx.com.cn");
            
            l_mailAddressRegiParams.setFamilyName("11");
            l_mailAddressRegiParams.setGivenName("22223");
            l_mailAddressRegiParams.setBrokerageTraderCode("s");
            l_mailAddressRegiParams.setLinkDistinctionCode("k");
            l_mailAddressRegiParams.setAccountDiv("1");
            l_mailAddressRegiParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_mailAddressRegiParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_mailAddressRegiParams.setDeleteFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDelAndCommit(l_mailAddressRegiParams);
            
            Method l_method =
                WEB3AccOpenMailAddressRegistServiceImpl.class.getDeclaredMethod("validateRepeatAddress", new Class[]{String.class, String.class, String.class});
            l_method.setAccessible(true);
            l_method.invoke(l_impl, new String[]{"1", "222", "3x5x56x5@@xxx.com.cn"});
            
            fail();
        }
        catch (InvocationTargetException e)
        {
            WEB3BaseException l =  (WEB3BaseException)e.getTargetException(); 
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02443, l.getErrorInfo()); 
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
