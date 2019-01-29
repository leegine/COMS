head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.05.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminSrvRegiServiceChangeCommonRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3AdminSrvRegiServiceChangeCommonRequestTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2007/06/14 ���� (���u) �V�K�쐬
*/
package webbroker3.srvregi.message;

import java.util.Calendar;
import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.srvregi.WEB3SrvRegiServiceInfoManagementTest;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminSrvRegiServiceChangeCommonRequestTest extends TestBaseForMock
{

    private WEB3AdminSrvRegiServiceChangeCommonRequest l_commonRequest = null;

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility
            .getInstance(WEB3SrvRegiServiceInfoManagementTest.class);

    public WEB3AdminSrvRegiServiceChangeCommonRequestTest(String name)
    {
        super(name);
    }
    
    protected void setUp() throws Exception
    {
        super.setUp();
        this.l_commonRequest = new WEB3AdminSrvRegiServiceChangeCommonRequest();
        this.initData();
        
    }
    
    protected void tearDown() throws Exception
    {
        this.l_commonRequest = null;
        super.tearDown();
    }
    
    /**
     * �����Ώۊ��Ԃ����l�ȊO�̒l�ł��B
     * �e�o�ُ�:BUSINESS_ERROR_02802
     *
     */
    public void testValidate_C0001()
    {
        final String STR_METHOD_NAME = "testValidate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.l_commonRequest.serviceDiv = "10";
            this.l_commonRequest.applyDiv = "1";
            this.l_commonRequest.serviceName = "jiddk";
            this.l_commonRequest.summary = "�";
            this.l_commonRequest.serviceStatus = "2";
            this.l_commonRequest.lotteryDiv = "1";
            this.l_commonRequest.chargeInfo = null;
            this.l_commonRequest.applyAbleStartDate = "20070201";
            this.l_commonRequest.applyAbleEndDate = "20070403";
            this.l_commonRequest.freeTargetPeriod = "jdf";
            this.l_commonRequest.validate();
            fail();
        }
        catch(WEB3BaseException l_web3BaseException)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02802,l_web3BaseException.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     * �����Ώۊ��ԁ�2���̏ꍇ�B
     * �e�o�ُ�:BUSINESS_ERROR_02803
     *
     */
    public void testValidate_C0002()
    {
        final String STR_METHOD_NAME = "testValidate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.l_commonRequest.freeTargetPeriod = "123";
            this.l_commonRequest.validate();
            fail();
        }
        catch(WEB3BaseException l_web3BaseException)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02803,l_web3BaseException.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     * �����Ώۊ��ԁ�'1' �̏ꍇ�B
     * �e�o�ُ�:BUSINESS_ERROR_02822
     *
     */
    public void testValidate_C0003()
    {
        final String STR_METHOD_NAME = "testValidate_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.l_commonRequest.freeTargetPeriod = "0";
            this.l_commonRequest.validate();
            fail();
        }
        catch(WEB3BaseException l_web3BaseException)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02822,l_web3BaseException.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     * �����Ώۊ��Ԑ���ʉ�
     *
     */
    public void testValidate_C0004()
    {
        final String STR_METHOD_NAME = "testValidate_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.l_commonRequest.freeTargetPeriod = "2";
            this.l_commonRequest.validate();
            fail();
        }
        catch(WEB3BaseException l_web3BaseException)
        {
            assertTrue(true);
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     * �񋟌`���̃`�F�b�N
     * this.�񋟌`��!=null && �ȉ��̒l�ȊO�̏ꍇ
     * �@@�@@�@@�E�����񋟂̂�  
�@@�@@ *�@@     �E�L���^������  
�@@�@@ *       �E�����񋟂̂�(�E�c�~��) 
�@@�@@ *       �E�L���^������(�E�c�~��) 
�@@�@@ *
�@@�@@ * �e�o�ُ�M���FBUSINESS_ERROR_01180
     *
     */
    public void testValidate_C0005()
    {
        final String STR_METHOD_NAME = "testValidate_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.l_commonRequest.freeTargetPeriod = "2";
            this.l_commonRequest.validate();
            fail();
        }
        catch(WEB3BaseException l_web3BaseException)
        {
            log.error("", l_web3BaseException);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01180,l_web3BaseException.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * �X�e�[�^�X�A���p���ԗ������A��W���ԏ��̃`�F�b�N 
�@@�@@ *
�@@�@@ * �e�o�ُ�M���FBUSINESS_ERROR_01287
     *
     */
    public void testValidate_C0006()
    {
        final String STR_METHOD_NAME = "testValidate_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.l_commonRequest.freeTargetPeriod = "2";
            this.l_commonRequest.offerType = "3";
            this.l_commonRequest.commissionAttainTotal = "2";
            this.l_commonRequest.serviceStatus = "1";
            this.l_commonRequest.consentSentence = "2";
            this.l_commonRequest.applyCommissionConditions = new WEB3SrvRegiApplyCommissionCondition[1];
            this.l_commonRequest.applyCommissionConditions[0]= new WEB3SrvRegiApplyCommissionCondition();
            this.l_commonRequest.applyCommissionConditions[0].productKindDiv = "1";
            this.l_commonRequest.lotteryDiv="0";
            this.l_commonRequest.validate();
            fail();
        }
        catch(WEB3BaseException l_web3BaseException)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01287,l_web3BaseException.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * this.�X�e�[�^�X!="��~��" && �ithis.�񋟌`��="�����񋟂̂�" ���́A
     *    this.�񋟌`��="�����񋟂̂�(�E�c�~��)"�j &&
     *    this.���p���ԗ������!=null && ���p���ԗ������̗v�f����0�� &&
     *    this.���p���ԗ������̑S�Ă̖����敪��"����"�ł͂Ȃ������ꍇ�A��O���X���[����
     *    
     *    �e�o�ُ�M���FBUSINESS_ERROR_01906
     */
    public void testValidate_C0007()
    {
        final String STR_METHOD_NAME = "testValidate_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.l_commonRequest.freeTargetPeriod = "2";
            this.l_commonRequest.offerType = "2";
            this.l_commonRequest.commissionAttainTotal = "2";
            this.l_commonRequest.serviceStatus = "1";
            this.l_commonRequest.consentSentence = "2";
            this.l_commonRequest.applyCommissionConditions = new WEB3SrvRegiApplyCommissionCondition[1];
            this.l_commonRequest.applyCommissionConditions[0]= new WEB3SrvRegiApplyCommissionCondition();
            this.l_commonRequest.applyCommissionConditions[0].productKindDiv = "1";
            this.l_commonRequest.lotteryDiv="1";
            this.l_commonRequest.applyInfo = new WEB3SrvRegiLotteryInfo[2];
            this.l_commonRequest.applyInfo[0] = new WEB3SrvRegiLotteryInfo();
            this.l_commonRequest.applyInfo[0].invalidDiv = true;
            this.l_commonRequest.applyInfo[1] = new WEB3SrvRegiLotteryInfo();
            this.l_commonRequest.applyInfo[1].invalidDiv = false;
            this.l_commonRequest.applyInfo[1].useDiv="1";
            
            Calendar l_calApplyStartDate = Calendar.getInstance();
            l_calApplyStartDate.set(2007,8,10);
            
            this.l_commonRequest.applyInfo[1].applyStartDate = l_calApplyStartDate.getTime();
            
            Calendar l_calApplyEndDate = Calendar.getInstance();
            l_calApplyEndDate.set(2007,8,12);
            
            this.l_commonRequest.applyInfo[1].applyEndDate = l_calApplyEndDate.getTime();
            
            
            Calendar l_calTrialStartDate = Calendar.getInstance();
            l_calTrialStartDate.set(2007,9,16);
            this.l_commonRequest.applyInfo[1].trialStartDate = l_calTrialStartDate.getTime();
            
            this.l_commonRequest.applyInfo[1].trialEndDate = new Date();
            this.l_commonRequest.applyInfo[1].applyMax = "5";
            
            Calendar l_calPaymentDate = Calendar.getInstance();
            l_calPaymentDate.set(2007,9,15);
            this.l_commonRequest.applyInfo[1].paymentDate = l_calPaymentDate.getTime();
            
            Calendar l_calLotteryDate = Calendar.getInstance();
            l_calLotteryDate.set(2007,8,15);
            
            this.l_commonRequest.applyInfo[1].lotteryDate = l_calLotteryDate.getTime();
            this.l_commonRequest.applyInfo[1].chargeAmt = "2";
            
            this.l_commonRequest.chargeInfo = new WEB3SrvRegiChargeInfo[4];
            this.l_commonRequest.chargeInfo[0] = new WEB3SrvRegiChargeInfo();
            this.l_commonRequest.chargeInfo[0].invalidDiv = false;//�L��
            this.l_commonRequest.chargeInfo[0].chargeDiv = "1";
            this.l_commonRequest.chargeInfo[0].chargePeriod = "1";
            this.l_commonRequest.chargeInfo[0].chargeAmt = "3";
            
            this.l_commonRequest.chargeInfo[1] = new WEB3SrvRegiChargeInfo();
            this.l_commonRequest.chargeInfo[1].invalidDiv = false;//�L��
            this.l_commonRequest.chargeInfo[1].chargeDiv = "2";
            this.l_commonRequest.chargeInfo[1].chargePeriod = "1";
            this.l_commonRequest.chargeInfo[1].chargeAmt = "3";
            
            this.l_commonRequest.chargeInfo[2] = new WEB3SrvRegiChargeInfo();
            this.l_commonRequest.chargeInfo[2].invalidDiv = false;//�L��
            this.l_commonRequest.chargeInfo[2].chargeDiv = "2";
            this.l_commonRequest.chargeInfo[2].chargePeriod = "1";
            this.l_commonRequest.chargeInfo[2].chargeAmt = "3";
            
            this.l_commonRequest.chargeInfo[3] = new WEB3SrvRegiChargeInfo();
            this.l_commonRequest.chargeInfo[3].invalidDiv = false;//�L��
            this.l_commonRequest.chargeInfo[3].chargeDiv = "2";
            this.l_commonRequest.chargeInfo[3].chargePeriod = "1";
            this.l_commonRequest.chargeInfo[3].chargeAmt = "3";
            
            this.l_commonRequest.validate();
            
            fail();

        }
        catch(WEB3BaseException l_web3BaseException)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01906,l_web3BaseException.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * ���팋��
     */
    public void testValidate_C0008()
    {
        final String STR_METHOD_NAME = "testValidate_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.l_commonRequest.freeTargetPeriod = "2";
            this.l_commonRequest.offerType = "3";
            this.l_commonRequest.commissionAttainTotal = "2";
            this.l_commonRequest.serviceStatus = "1";
            this.l_commonRequest.consentSentence = "2";
            this.l_commonRequest.applyCommissionConditions = new WEB3SrvRegiApplyCommissionCondition[1];
            this.l_commonRequest.applyCommissionConditions[0]= new WEB3SrvRegiApplyCommissionCondition();
            this.l_commonRequest.applyCommissionConditions[0].productKindDiv = "1";
            this.l_commonRequest.lotteryDiv="1";
            this.l_commonRequest.applyInfo = new WEB3SrvRegiLotteryInfo[2];
            this.l_commonRequest.applyInfo[0] = new WEB3SrvRegiLotteryInfo();
            this.l_commonRequest.applyInfo[0].invalidDiv = true;
            this.l_commonRequest.applyInfo[1] = new WEB3SrvRegiLotteryInfo();
            this.l_commonRequest.applyInfo[1].invalidDiv = false;
            this.l_commonRequest.applyInfo[1].useDiv="1";
            
            Calendar l_calApplyStartDate = Calendar.getInstance();
            l_calApplyStartDate.set(2007,8,10);
            
            this.l_commonRequest.applyInfo[1].applyStartDate = l_calApplyStartDate.getTime();
            
            Calendar l_calApplyEndDate = Calendar.getInstance();
            l_calApplyEndDate.set(2007,8,12);
            
            this.l_commonRequest.applyInfo[1].applyEndDate = l_calApplyEndDate.getTime();
            
            
            Calendar l_calTrialStartDate = Calendar.getInstance();
            l_calTrialStartDate.set(2007,9,16);
            this.l_commonRequest.applyInfo[1].trialStartDate = l_calTrialStartDate.getTime();
            
            this.l_commonRequest.applyInfo[1].trialEndDate = new Date();
            this.l_commonRequest.applyInfo[1].applyMax = "5";
            
            Calendar l_calPaymentDate = Calendar.getInstance();
            l_calPaymentDate.set(2007,9,15);
            this.l_commonRequest.applyInfo[1].paymentDate = l_calPaymentDate.getTime();
            
            Calendar l_calLotteryDate = Calendar.getInstance();
            l_calLotteryDate.set(2007,8,15);
            
            this.l_commonRequest.applyInfo[1].lotteryDate = l_calLotteryDate.getTime();
            this.l_commonRequest.applyInfo[1].chargeAmt = "2";
            
            this.l_commonRequest.chargeInfo = new WEB3SrvRegiChargeInfo[4];
            this.l_commonRequest.chargeInfo[0] = new WEB3SrvRegiChargeInfo();
            this.l_commonRequest.chargeInfo[0].invalidDiv = false;//�L��
            this.l_commonRequest.chargeInfo[0].chargeDiv = "1";
            this.l_commonRequest.chargeInfo[0].chargePeriod = "1";
            this.l_commonRequest.chargeInfo[0].chargeAmt = "3";
            
            this.l_commonRequest.chargeInfo[1] = new WEB3SrvRegiChargeInfo();
            this.l_commonRequest.chargeInfo[1].invalidDiv = false;//�L��
            this.l_commonRequest.chargeInfo[1].chargeDiv = "2";
            this.l_commonRequest.chargeInfo[1].chargePeriod = "1";
            this.l_commonRequest.chargeInfo[1].chargeAmt = "3";
            
            this.l_commonRequest.chargeInfo[2] = new WEB3SrvRegiChargeInfo();
            this.l_commonRequest.chargeInfo[2].invalidDiv = false;//�L��
            this.l_commonRequest.chargeInfo[2].chargeDiv = "2";
            this.l_commonRequest.chargeInfo[2].chargePeriod = "1";
            this.l_commonRequest.chargeInfo[2].chargeAmt = "3";
            
            this.l_commonRequest.chargeInfo[3] = new WEB3SrvRegiChargeInfo();
            this.l_commonRequest.chargeInfo[3].invalidDiv = true;//����
            this.l_commonRequest.chargeInfo[3].chargeDiv = "2";
            this.l_commonRequest.chargeInfo[3].chargePeriod = "1";
            this.l_commonRequest.chargeInfo[3].chargeAmt = "3";
            
            this.l_commonRequest.validate();

        }
        catch(WEB3BaseException l_web3BaseException)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * ���팋��
     */
    public void testValidate_C0009()
    {
        final String STR_METHOD_NAME = "testValidate_C0009()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.l_commonRequest.freeTargetPeriod = "2";
            this.l_commonRequest.offerType = "3";
            this.l_commonRequest.commissionAttainTotal = "2";
            this.l_commonRequest.serviceStatus = "1";
            this.l_commonRequest.consentSentence = "2";
            this.l_commonRequest.applyCommissionConditions = new WEB3SrvRegiApplyCommissionCondition[1];
            this.l_commonRequest.applyCommissionConditions[0]= new WEB3SrvRegiApplyCommissionCondition();
            this.l_commonRequest.applyCommissionConditions[0].productKindDiv = "1";
            this.l_commonRequest.lotteryDiv="1";
            this.l_commonRequest.applyInfo = new WEB3SrvRegiLotteryInfo[2];
            this.l_commonRequest.applyInfo[0] = new WEB3SrvRegiLotteryInfo();
            this.l_commonRequest.applyInfo[0].invalidDiv = true;
            this.l_commonRequest.applyInfo[1] = new WEB3SrvRegiLotteryInfo();
            this.l_commonRequest.applyInfo[1].invalidDiv = false;
            this.l_commonRequest.applyInfo[1].useDiv="1";
            this.l_commonRequest.hashCalOrderDiv = "9";
            
            Calendar l_calApplyStartDate = Calendar.getInstance();
            l_calApplyStartDate.set(2007,8,10);
            
            this.l_commonRequest.applyInfo[1].applyStartDate = l_calApplyStartDate.getTime();
            
            Calendar l_calApplyEndDate = Calendar.getInstance();
            l_calApplyEndDate.set(2007,8,12);
            
            this.l_commonRequest.applyInfo[1].applyEndDate = l_calApplyEndDate.getTime();
            
            
            Calendar l_calTrialStartDate = Calendar.getInstance();
            l_calTrialStartDate.set(2007,9,16);
            this.l_commonRequest.applyInfo[1].trialStartDate = l_calTrialStartDate.getTime();
            
            this.l_commonRequest.applyInfo[1].trialEndDate = new Date();
            this.l_commonRequest.applyInfo[1].applyMax = "5";
            
            Calendar l_calPaymentDate = Calendar.getInstance();
            l_calPaymentDate.set(2007,9,15);
            this.l_commonRequest.applyInfo[1].paymentDate = l_calPaymentDate.getTime();
            
            Calendar l_calLotteryDate = Calendar.getInstance();
            l_calLotteryDate.set(2007,8,15);
            
            this.l_commonRequest.applyInfo[1].lotteryDate = l_calLotteryDate.getTime();
            this.l_commonRequest.applyInfo[1].chargeAmt = "2";
            
            this.l_commonRequest.chargeInfo = new WEB3SrvRegiChargeInfo[4];
            this.l_commonRequest.chargeInfo[0] = new WEB3SrvRegiChargeInfo();
            this.l_commonRequest.chargeInfo[0].invalidDiv = false;//�L��
            this.l_commonRequest.chargeInfo[0].chargeDiv = "1";
            this.l_commonRequest.chargeInfo[0].chargePeriod = "1";
            this.l_commonRequest.chargeInfo[0].chargeAmt = "3";
            
            this.l_commonRequest.chargeInfo[1] = new WEB3SrvRegiChargeInfo();
            this.l_commonRequest.chargeInfo[1].invalidDiv = false;//�L��
            this.l_commonRequest.chargeInfo[1].chargeDiv = "2";
            this.l_commonRequest.chargeInfo[1].chargePeriod = "1";
            this.l_commonRequest.chargeInfo[1].chargeAmt = "3";
            
            this.l_commonRequest.chargeInfo[2] = new WEB3SrvRegiChargeInfo();
            this.l_commonRequest.chargeInfo[2].invalidDiv = false;//�L��
            this.l_commonRequest.chargeInfo[2].chargeDiv = "2";
            this.l_commonRequest.chargeInfo[2].chargePeriod = "1";
            this.l_commonRequest.chargeInfo[2].chargeAmt = "3";
            
            this.l_commonRequest.chargeInfo[3] = new WEB3SrvRegiChargeInfo();
            this.l_commonRequest.chargeInfo[3].invalidDiv = true;//����
            this.l_commonRequest.chargeInfo[3].chargeDiv = "2";
            this.l_commonRequest.chargeInfo[3].chargePeriod = "1";
            this.l_commonRequest.chargeInfo[3].chargeAmt = "3";
            
            this.l_commonRequest.validate();

        }
        catch(WEB3BusinessLayerException l_web3BaseException)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01844, l_web3BaseException.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * ���팋��
     */
    public void testValidate_C0010()
    {
        final String STR_METHOD_NAME = "testValidate_C0010()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.l_commonRequest.freeTargetPeriod = "2";
            this.l_commonRequest.offerType = "3";
            this.l_commonRequest.commissionAttainTotal = "2";
            this.l_commonRequest.serviceStatus = "1";
            this.l_commonRequest.consentSentence = "2";
            this.l_commonRequest.applyCommissionConditions = new WEB3SrvRegiApplyCommissionCondition[1];
            this.l_commonRequest.applyCommissionConditions[0]= new WEB3SrvRegiApplyCommissionCondition();
            this.l_commonRequest.applyCommissionConditions[0].productKindDiv = "1";
            this.l_commonRequest.lotteryDiv="1";
            this.l_commonRequest.applyInfo = new WEB3SrvRegiLotteryInfo[2];
            this.l_commonRequest.applyInfo[0] = new WEB3SrvRegiLotteryInfo();
            this.l_commonRequest.applyInfo[0].invalidDiv = true;
            this.l_commonRequest.applyInfo[1] = new WEB3SrvRegiLotteryInfo();
            this.l_commonRequest.applyInfo[1].invalidDiv = false;
            this.l_commonRequest.applyInfo[1].useDiv="1";
            this.l_commonRequest.hashCalOrderDiv = "7";
            
            Calendar l_calApplyStartDate = Calendar.getInstance();
            l_calApplyStartDate.set(2007,8,10);
            
            this.l_commonRequest.applyInfo[1].applyStartDate = l_calApplyStartDate.getTime();
            
            Calendar l_calApplyEndDate = Calendar.getInstance();
            l_calApplyEndDate.set(2007,8,12);
            
            this.l_commonRequest.applyInfo[1].applyEndDate = l_calApplyEndDate.getTime();
            
            
            Calendar l_calTrialStartDate = Calendar.getInstance();
            l_calTrialStartDate.set(2007,9,16);
            this.l_commonRequest.applyInfo[1].trialStartDate = l_calTrialStartDate.getTime();
            
            this.l_commonRequest.applyInfo[1].trialEndDate = new Date();
            this.l_commonRequest.applyInfo[1].applyMax = "5";
            
            Calendar l_calPaymentDate = Calendar.getInstance();
            l_calPaymentDate.set(2007,9,15);
            this.l_commonRequest.applyInfo[1].paymentDate = l_calPaymentDate.getTime();
            
            Calendar l_calLotteryDate = Calendar.getInstance();
            l_calLotteryDate.set(2007,8,15);
            
            this.l_commonRequest.applyInfo[1].lotteryDate = l_calLotteryDate.getTime();
            this.l_commonRequest.applyInfo[1].chargeAmt = "2";
            
            this.l_commonRequest.chargeInfo = new WEB3SrvRegiChargeInfo[4];
            this.l_commonRequest.chargeInfo[0] = new WEB3SrvRegiChargeInfo();
            this.l_commonRequest.chargeInfo[0].invalidDiv = false;//�L��
            this.l_commonRequest.chargeInfo[0].chargeDiv = "1";
            this.l_commonRequest.chargeInfo[0].chargePeriod = "1";
            this.l_commonRequest.chargeInfo[0].chargeAmt = "3";
            
            this.l_commonRequest.chargeInfo[1] = new WEB3SrvRegiChargeInfo();
            this.l_commonRequest.chargeInfo[1].invalidDiv = false;//�L��
            this.l_commonRequest.chargeInfo[1].chargeDiv = "2";
            this.l_commonRequest.chargeInfo[1].chargePeriod = "1";
            this.l_commonRequest.chargeInfo[1].chargeAmt = "3";
            
            this.l_commonRequest.chargeInfo[2] = new WEB3SrvRegiChargeInfo();
            this.l_commonRequest.chargeInfo[2].invalidDiv = false;//�L��
            this.l_commonRequest.chargeInfo[2].chargeDiv = "2";
            this.l_commonRequest.chargeInfo[2].chargePeriod = "1";
            this.l_commonRequest.chargeInfo[2].chargeAmt = "3";
            
            this.l_commonRequest.chargeInfo[3] = new WEB3SrvRegiChargeInfo();
            this.l_commonRequest.chargeInfo[3].invalidDiv = true;//����
            this.l_commonRequest.chargeInfo[3].chargeDiv = "2";
            this.l_commonRequest.chargeInfo[3].chargePeriod = "1";
            this.l_commonRequest.chargeInfo[3].chargeAmt = "3";
            
            this.l_commonRequest.validate();

        }
        catch(WEB3BaseException l_web3BaseException)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * ���팋��
     */
    public void testValidate_C0011()
    {
        final String STR_METHOD_NAME = "testValidate_C0011()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.l_commonRequest.freeTargetPeriod = "2";
            this.l_commonRequest.offerType = "3";
            this.l_commonRequest.commissionAttainTotal = "2";
            this.l_commonRequest.serviceStatus = "1";
            this.l_commonRequest.consentSentence = "2";
            this.l_commonRequest.applyCommissionConditions = new WEB3SrvRegiApplyCommissionCondition[1];
            this.l_commonRequest.applyCommissionConditions[0]= new WEB3SrvRegiApplyCommissionCondition();
            this.l_commonRequest.applyCommissionConditions[0].productKindDiv = "1";
            this.l_commonRequest.lotteryDiv="1";
            this.l_commonRequest.applyInfo = new WEB3SrvRegiLotteryInfo[2];
            this.l_commonRequest.applyInfo[0] = new WEB3SrvRegiLotteryInfo();
            this.l_commonRequest.applyInfo[0].invalidDiv = true;
            this.l_commonRequest.applyInfo[1] = new WEB3SrvRegiLotteryInfo();
            this.l_commonRequest.applyInfo[1].invalidDiv = false;
            this.l_commonRequest.applyInfo[1].useDiv="1";
            this.l_commonRequest.hashCalOrderDiv = "8";
            
            Calendar l_calApplyStartDate = Calendar.getInstance();
            l_calApplyStartDate.set(2007,8,10);
            
            this.l_commonRequest.applyInfo[1].applyStartDate = l_calApplyStartDate.getTime();
            
            Calendar l_calApplyEndDate = Calendar.getInstance();
            l_calApplyEndDate.set(2007,8,12);
            
            this.l_commonRequest.applyInfo[1].applyEndDate = l_calApplyEndDate.getTime();
            
            
            Calendar l_calTrialStartDate = Calendar.getInstance();
            l_calTrialStartDate.set(2007,9,16);
            this.l_commonRequest.applyInfo[1].trialStartDate = l_calTrialStartDate.getTime();
            
            this.l_commonRequest.applyInfo[1].trialEndDate = new Date();
            this.l_commonRequest.applyInfo[1].applyMax = "5";
            
            Calendar l_calPaymentDate = Calendar.getInstance();
            l_calPaymentDate.set(2007,9,15);
            this.l_commonRequest.applyInfo[1].paymentDate = l_calPaymentDate.getTime();
            
            Calendar l_calLotteryDate = Calendar.getInstance();
            l_calLotteryDate.set(2007,8,15);
            
            this.l_commonRequest.applyInfo[1].lotteryDate = l_calLotteryDate.getTime();
            this.l_commonRequest.applyInfo[1].chargeAmt = "2";
            
            this.l_commonRequest.chargeInfo = new WEB3SrvRegiChargeInfo[4];
            this.l_commonRequest.chargeInfo[0] = new WEB3SrvRegiChargeInfo();
            this.l_commonRequest.chargeInfo[0].invalidDiv = false;//�L��
            this.l_commonRequest.chargeInfo[0].chargeDiv = "1";
            this.l_commonRequest.chargeInfo[0].chargePeriod = "1";
            this.l_commonRequest.chargeInfo[0].chargeAmt = "3";
            
            this.l_commonRequest.chargeInfo[1] = new WEB3SrvRegiChargeInfo();
            this.l_commonRequest.chargeInfo[1].invalidDiv = false;//�L��
            this.l_commonRequest.chargeInfo[1].chargeDiv = "2";
            this.l_commonRequest.chargeInfo[1].chargePeriod = "1";
            this.l_commonRequest.chargeInfo[1].chargeAmt = "3";
            
            this.l_commonRequest.chargeInfo[2] = new WEB3SrvRegiChargeInfo();
            this.l_commonRequest.chargeInfo[2].invalidDiv = false;//�L��
            this.l_commonRequest.chargeInfo[2].chargeDiv = "2";
            this.l_commonRequest.chargeInfo[2].chargePeriod = "1";
            this.l_commonRequest.chargeInfo[2].chargeAmt = "3";
            
            this.l_commonRequest.chargeInfo[3] = new WEB3SrvRegiChargeInfo();
            this.l_commonRequest.chargeInfo[3].invalidDiv = true;//����
            this.l_commonRequest.chargeInfo[3].chargeDiv = "2";
            this.l_commonRequest.chargeInfo[3].chargePeriod = "1";
            this.l_commonRequest.chargeInfo[3].chargeAmt = "3";
            
            this.l_commonRequest.validate();

        }
        catch(WEB3BaseException l_web3BaseException)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
//    public void testValidate_C0005()
//    {
//        final String STR_METHOD_NAME = "testValidate_C0005()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            this.l_commonRequest.serviceDiv = "10";
//            this.l_commonRequest.applyDiv = "1";
//            this.l_commonRequest.serviceName = "jiddk";
//            this.l_commonRequest.summary = "�";
//            this.l_commonRequest.lotteryDiv = "1";
//            this.l_commonRequest.chargeInfo = null;
//            this.l_commonRequest.applyAbleStartDate = "20070201";
//            this.l_commonRequest.applyAbleEndDate = "20070403";
//            this.l_commonRequest.freeTargetPeriod = null;
//            this.l_commonRequest.serviceStatus = "0";
//            this.l_commonRequest.consentSentence = "123";
//            this.l_commonRequest.serviceContent = "dsfdsfwefewf";
//            this.l_commonRequest.explainURL = "Ddfdsfsdafsdafdsaf";
//            this.l_commonRequest.confirmMailDiv = "1";
//            this.l_commonRequest.noticeMailDiv = "1";
//            this.l_commonRequest.hashCalHowToDiv = "0";
//            this.l_commonRequest.hashCalOrderDiv = "6";//�����y
//            this.l_commonRequest.sendHowToDiv = "4";
//            this.l_commonRequest.sendParamDiv = "0";
//            this.l_commonRequest.cryptAccountCodeDiv = "1";
//            this.l_commonRequest.hashList = null;
//            this.l_commonRequest.paramList = null;
//            this.l_commonRequest.url = "djkfdsjfkldjf";
//            this.l_commonRequest.url2 = "djkfdsjfkldjf";
//            this.l_commonRequest.noticeMailDate = "20050203";
//            this.l_commonRequest.validate();
//            
//        }
//        catch(Exception l_exc)
//        {
//            log.error(STR_METHOD_NAME, l_exc);
//            fail();
//        }
//        log.exiting(STR_METHOD_NAME);
//    }
//    
//    public void testValidate_C0006()
//    {
//        final String STR_METHOD_NAME = "testValidate_C0006()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            this.l_commonRequest.serviceDiv = "10";
//            this.l_commonRequest.applyDiv = "1";
//            this.l_commonRequest.serviceName = "jiddk";
//            this.l_commonRequest.summary = "�";
//            this.l_commonRequest.lotteryDiv = "1";
//            this.l_commonRequest.chargeInfo = null;
//            this.l_commonRequest.applyAbleStartDate = "20070201";
//            this.l_commonRequest.applyAbleEndDate = "20070403";
//            this.l_commonRequest.freeTargetPeriod = null;
//            this.l_commonRequest.serviceStatus = "0";
//            this.l_commonRequest.consentSentence = "123";
//            this.l_commonRequest.serviceContent = "dsfdsfwefewf";
//            this.l_commonRequest.explainURL = "Ddfdsfsdafsdafdsaf";
//            this.l_commonRequest.confirmMailDiv = "1";
//            this.l_commonRequest.noticeMailDiv = "1";
//            this.l_commonRequest.hashCalHowToDiv = "1";
//            this.l_commonRequest.hashCalOrderDiv = "1";//�����y
//            this.l_commonRequest.sendHowToDiv = "4";
//            this.l_commonRequest.sendParamDiv = "0";
//            this.l_commonRequest.cryptAccountCodeDiv = "1";
//            this.l_commonRequest.hashList = null;
//            this.l_commonRequest.paramList = null;
//            this.l_commonRequest.url = "djkfdsjfkldjf";
//            this.l_commonRequest.url2 = "djkfdsjfkldjf";
//            this.l_commonRequest.noticeMailDate = "20050203";
//            this.l_commonRequest.validate();
//            
//        }
//        catch(Exception l_exc)
//        {
//            log.error(STR_METHOD_NAME, l_exc);
//            fail();
//        }
//        log.exiting(STR_METHOD_NAME);
//    }
//    
//
//    public void testValidate_C0007()
//    {
//        final String STR_METHOD_NAME = "testValidate_C0007()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            this.l_commonRequest.serviceDiv = "10";
//            this.l_commonRequest.applyDiv = "1";
//            this.l_commonRequest.serviceName = "jiddk";
//            this.l_commonRequest.summary = "�";
//            this.l_commonRequest.lotteryDiv = "1";
//            this.l_commonRequest.chargeInfo = null;
//            this.l_commonRequest.applyAbleStartDate = "20070201";
//            this.l_commonRequest.applyAbleEndDate = "20070403";
//            this.l_commonRequest.freeTargetPeriod = null;
//            this.l_commonRequest.serviceStatus = "0";
//            this.l_commonRequest.consentSentence = "123";
//            this.l_commonRequest.serviceContent = "dsfdsfwefewf";
//            this.l_commonRequest.explainURL = "Ddfdsfsdafsdafdsaf";
//            this.l_commonRequest.confirmMailDiv = "1";
//            this.l_commonRequest.noticeMailDiv = "1";
//            this.l_commonRequest.hashCalHowToDiv = "1";//�����y
//            this.l_commonRequest.hashCalOrderDiv = "6";//�����y
//            this.l_commonRequest.sendHowToDiv = "4";
//            this.l_commonRequest.sendParamDiv = "0";
//            this.l_commonRequest.cryptAccountCodeDiv = "1";
//            this.l_commonRequest.hashList = null;
//            this.l_commonRequest.paramList = null;
//            this.l_commonRequest.url = "djkfdsjfkldjf";
//            this.l_commonRequest.url2 = "djkfdsjfkldjf";
//            this.l_commonRequest.noticeMailDate = "20050203";
//            this.l_commonRequest.validate();
//            fail();
//        }
//        catch(WEB3BusinessLayerException l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01855, l_ex.getErrorInfo());
//        }
//        catch(Exception l_exc)
//        {
//            log.error(STR_METHOD_NAME, l_exc);
//            fail();
//        }
//        log.exiting(STR_METHOD_NAME);
//    }
    
    private void initData()
    {
        this.l_commonRequest.serviceDiv = "22";
        this.l_commonRequest.applyDiv = "1";
        this.l_commonRequest.serviceName = "abc";
        this.l_commonRequest.summary = "�";
        this.l_commonRequest.serviceStatus = "2";
        this.l_commonRequest.chargeInfo = new WEB3SrvRegiChargeInfo[0];
        this.l_commonRequest.lotteryDiv = "1";
        this.l_commonRequest.serviceStatus = "0";
        this.l_commonRequest.confirmMailDiv = "0";
        this.l_commonRequest.noticeMailDiv = "0";
        this.l_commonRequest.offerType = "4";
    }

}
@
