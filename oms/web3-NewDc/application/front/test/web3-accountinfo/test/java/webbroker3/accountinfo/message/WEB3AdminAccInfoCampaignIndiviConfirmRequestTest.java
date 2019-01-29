head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.06.08.39.55;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5404d9c264d0465;
filename	WEB3AdminAccInfoCampaignIndiviConfirmRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountinfo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��҂��q�l���萔����������߰݌ʌڋq�w��ύX�m�Fظ���)<BR>
 * �Ǘ��҂��q�l���萔����������߰݌ʌڋq�w��ύX�m�Fظ���<BR>
 * @@author �Ј���
 * @@version 1.0
 */
public class WEB3AdminAccInfoCampaignIndiviConfirmRequestTest extends TestBaseForMock
{

    public WEB3AdminAccInfoCampaignIndiviConfirmRequestTest(String name) 
    {
        super(name);
    }
    
    private WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3AdminAccInfoCampaignIndiviConfirmRequestTest.class);
    
    /**
     *�P�j �X�V�����t���O�̃`�F�b�N 
     *�X�V�����t���O�� '0' �� '1' �� '2'�ȊO�̏ꍇ
     */
    public void testValidate_0001()
    {
        final String STR_METHOD_NAME = "testValidate_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignIndiviConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignIndiviConfirmRequest();
        
        //�X�V�����t���O
        l_request.updateFlag = "3";
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02710);
            assertEquals("�X�V�����t���O�̒l�����݂��Ȃ��R�[�h�l�ł��B",l_ex.getErrorInfo().getErrorMessage());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch (Exception e)
        {
            log.exiting(TEST_START + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     *�Q�j �o�^�^�C�v�̃`�F�b�N
     * �o�^�^�C�v�� '1'(�ʌڋq�w��) �� '2'(�����ʌڋq�w��)�ȊO�̏ꍇ
     */
    public void testValidate_0002()
    {
        final String STR_METHOD_NAME = "testValidate_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignIndiviConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignIndiviConfirmRequest();
        
        //�X�V�����t���O
        l_request.updateFlag = "1";
        
        WEB3AccInfoCampaignInfo l_accopenConditionInfo = new WEB3AccInfoCampaignInfo();
        
        l_accopenConditionInfo.registType = "0";
        
        //�萔�������L�����y�[���������
        l_request.commissionCampaignInfo = l_accopenConditionInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02711);
            assertEquals("�o�^�^�C�v��'1'(�ʌڋq�w��) �� '2'(�����ʌڋq�w��)�ȊO�̒l�ł��B",
                l_ex.getErrorInfo().getErrorMessage());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch (Exception e)
        {
            log.exiting(TEST_START + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     * [���N�G�X�g�f�[�^.�X�V�����t���O = '0'(�o�^)�̏ꍇ]
     *�R�j�@@���X�R�[�h�̃`�F�b�N
     * �����͂̏ꍇ
     */
    public void testValidate_0003()
    {
        final String STR_METHOD_NAME = "testValidate_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignIndiviConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignIndiviConfirmRequest();
        
        //�X�V�����t���O
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_accopenConditionInfo = new WEB3AccInfoCampaignInfo();
        
        //�o�^�^�C�v
        l_accopenConditionInfo.registType = "1";
        //���X�R�[�h
        l_accopenConditionInfo.branchCode = null;
        
        //�萔�������L�����y�[���������
        l_request.commissionCampaignInfo = l_accopenConditionInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00833);
            assertEquals("���X�R�[�h�����w��ł��B", l_ex.getErrorInfo().getErrorMessage());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch (Exception e)
        {
            log.exiting(TEST_START + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     * [���N�G�X�g�f�[�^.�X�V�����t���O = '0'(�o�^)�̏ꍇ]
     *�R�j�@@���X�R�[�h�̃`�F�b�N
     * �R���ȊO�̏ꍇ
     */
    public void testValidate_0004()
    {
        final String STR_METHOD_NAME = "testValidate_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignIndiviConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignIndiviConfirmRequest();
        
        //�X�V�����t���O
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_accopenConditionInfo = new WEB3AccInfoCampaignInfo();
        
        //�o�^�^�C�v
        l_accopenConditionInfo.registType = "2";
        //���X�R�[�h
        l_accopenConditionInfo.branchCode = "01";
        
        //�萔�������L�����y�[���������
        l_request.commissionCampaignInfo = l_accopenConditionInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00834);
            assertEquals("���X�R�[�h�̃T�C�Y���s���ł��B", l_ex.getErrorInfo().getErrorMessage());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch (Exception e)
        {
            log.exiting(TEST_START + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     * [���N�G�X�g�f�[�^.�X�V�����t���O = '0'(�o�^)�̏ꍇ]
     *�R�j�@@���X�R�[�h�̃`�F�b�N
     * �R���ȊO�̏ꍇ
     */
    public void testValidate_0005()
    {
        final String STR_METHOD_NAME = "testValidate_0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignIndiviConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignIndiviConfirmRequest();
        
        //�X�V�����t���O
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_accopenConditionInfo = new WEB3AccInfoCampaignInfo();
        
        //�o�^�^�C�v
        l_accopenConditionInfo.registType = "2";
        //���X�R�[�h
        l_accopenConditionInfo.branchCode = "0101";
        
        //�萔�������L�����y�[���������
        l_request.commissionCampaignInfo = l_accopenConditionInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00834);
            assertEquals("���X�R�[�h�̃T�C�Y���s���ł��B", l_ex.getErrorInfo().getErrorMessage());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch (Exception e)
        {
            log.exiting(TEST_START + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     * [���N�G�X�g�f�[�^.�X�V�����t���O = '0'(�o�^)�̏ꍇ]
     *�S�j�@@�ڋq�R�[�h�̃`�F�b�N
     * �����͂̏ꍇ
     */
    public void testValidate_0006()
    {
        final String STR_METHOD_NAME = "testValidate_0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignIndiviConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignIndiviConfirmRequest();
        
        //�X�V�����t���O
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_accopenConditionInfo = new WEB3AccInfoCampaignInfo();
        
        //�o�^�^�C�v
        l_accopenConditionInfo.registType = "2";
        //���X�R�[�h
        l_accopenConditionInfo.branchCode = "011";
        //�ڋq�R�[�h
        l_accopenConditionInfo.accountCode = null;
        
        //�萔�������L�����y�[���������
        l_request.commissionCampaignInfo = l_accopenConditionInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00835);
            assertEquals("�ڋq�R�[�h�����w��ł��B", l_ex.getErrorInfo().getErrorMessage());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch (Exception e)
        {
            log.exiting(TEST_START + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     * [���N�G�X�g�f�[�^.�X�V�����t���O = '0'(�o�^)�̏ꍇ]
     *�S�j�@@�ڋq�R�[�h�̃`�F�b�N
     * ������6�łȂ��ꍇ
     */
    public void testValidate_0007()
    {
        final String STR_METHOD_NAME = "testValidate_0007()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignIndiviConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignIndiviConfirmRequest();
        
        //�X�V�����t���O
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_accopenConditionInfo = new WEB3AccInfoCampaignInfo();
        
        //�o�^�^�C�v
        l_accopenConditionInfo.registType = "2";
        //���X�R�[�h
        l_accopenConditionInfo.branchCode = "011";
        //�ڋq�R�[�h
        l_accopenConditionInfo.accountCode = "1223";
        
        //�萔�������L�����y�[���������
        l_request.commissionCampaignInfo = l_accopenConditionInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00836);
            assertEquals("�ڋq�R�[�h�̃T�C�Y���s���ł��B", l_ex.getErrorInfo().getErrorMessage());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch (Exception e)
        {
            log.exiting(TEST_START + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     * [���N�G�X�g�f�[�^.�X�V�����t���O = '0'(�o�^)�̏ꍇ]
     *�S�j�@@�ڋq�R�[�h�̃`�F�b�N
     * ������6�łȂ��ꍇ
     */
    public void testValidate_0008()
    {
        final String STR_METHOD_NAME = "testValidate_0008()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignIndiviConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignIndiviConfirmRequest();
        
        //�X�V�����t���O
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_accopenConditionInfo = new WEB3AccInfoCampaignInfo();
        
        //�o�^�^�C�v
        l_accopenConditionInfo.registType = "2";
        //���X�R�[�h
        l_accopenConditionInfo.branchCode = "011";
        //�ڋq�R�[�h
        l_accopenConditionInfo.accountCode = "1223123";
        
        //�萔�������L�����y�[���������
        l_request.commissionCampaignInfo = l_accopenConditionInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00836);
            assertEquals("�ڋq�R�[�h�̃T�C�Y���s���ł��B", l_ex.getErrorInfo().getErrorMessage());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch (Exception e)
        {
            log.exiting(TEST_START + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     * [���N�G�X�g�f�[�^.�X�V�����t���O = '0'(�o�^)�̏ꍇ]
     *�T�j�@@�L�����y�[�����̂̃`�F�b�N
     * 101�o�C�g�ȏ�̏ꍇ
     */
    public void testValidate_0009()
    {
        final String STR_METHOD_NAME = "testValidate_0009()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignIndiviConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignIndiviConfirmRequest();
        
        //�X�V�����t���O
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_accopenConditionInfo = new WEB3AccInfoCampaignInfo();
        
        //�o�^�^�C�v
        l_accopenConditionInfo.registType = "2";
        //���X�R�[�h
        l_accopenConditionInfo.branchCode = "011";
        //�ڋq�R�[�h
        l_accopenConditionInfo.accountCode = "123456";
        //�L�����y�[������
        l_accopenConditionInfo.campaignName = "12345678901234567890123456789012345678901234567890123" +
                "456789012345678901234567890123456789012345678901";
        
        //�萔�������L�����y�[���������
        l_request.commissionCampaignInfo = l_accopenConditionInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02709);
            assertEquals("�L�����y�[�����̌����G���[�B", l_ex.getErrorInfo().getErrorMessage());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch (Exception e)
        {
            log.exiting(TEST_START + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     * [���N�G�X�g�f�[�^.�X�V�����t���O = '0'(�o�^)�̏ꍇ]
     *�T�j�@@�L�����y�[�����̂̃`�F�b�N
     * 101�o�C�g�ȏ�̏ꍇ
     */
    public void testValidate_0010()
    {
        final String STR_METHOD_NAME = "testValidate_0010()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignIndiviConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignIndiviConfirmRequest();
        
        //�X�V�����t���O
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_accopenConditionInfo = new WEB3AccInfoCampaignInfo();
        
        //�o�^�^�C�v
        l_accopenConditionInfo.registType = "2";
        //���X�R�[�h
        l_accopenConditionInfo.branchCode = "011";
        //�ڋq�R�[�h
        l_accopenConditionInfo.accountCode = "123456";
        //�L�����y�[������
        l_accopenConditionInfo.campaignName = "12345678901234567890123456789012345678901234567890123" +
            "45678901234567890123456789012345678901234567890123";
        
        //�萔�������L�����y�[���������
        l_request.commissionCampaignInfo = l_accopenConditionInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02709);
            assertEquals("�L�����y�[�����̌����G���[�B", l_ex.getErrorInfo().getErrorMessage());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch (Exception e)
        {
            log.exiting(TEST_START + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     * [���N�G�X�g�f�[�^.�X�V�����t���O = '0'(�o�^)�̏ꍇ]
     *�T�j�@@�L�����y�[�����̂̃`�F�b�N
     * [�o�^�^�C�v = '1'(�ʌڋq�w��)�̏ꍇ�̂݁A�T�|�Q�j�̃`�F�b�N���s��]
     * �����͂̏ꍇ
     */
    public void testValidate_0011()
    {
        final String STR_METHOD_NAME = "testValidate_0011()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignIndiviConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignIndiviConfirmRequest();
        
        //�X�V�����t���O
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_accopenConditionInfo = new WEB3AccInfoCampaignInfo();
        
        //�o�^�^�C�v
        l_accopenConditionInfo.registType = "1";
        //���X�R�[�h
        l_accopenConditionInfo.branchCode = "011";
        //�ڋq�R�[�h
        l_accopenConditionInfo.accountCode = "123456";
        //�L�����y�[������
        l_accopenConditionInfo.campaignName = null;
        
        //�萔�������L�����y�[���������
        l_request.commissionCampaignInfo = l_accopenConditionInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02712);
            assertEquals("�L�����y�[�����̖����̓G���[�B", l_ex.getErrorInfo().getErrorMessage());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch (Exception e)
        {
            log.exiting(TEST_START + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     * [���N�G�X�g�f�[�^.�X�V�����t���O = '0'(�o�^)�̏ꍇ]
     *�T�j�@@�L�����y�[�����̂̃`�F�b�N
     * [�o�^�^�C�v = '2'(�����ڋq�w��)�̏ꍇ�̂݁A�T�|�R�j�̃`�F�b�N���s��]
     * ���������͂���Ă���ꍇ
     */
    public void testValidate_0012()
    {
        final String STR_METHOD_NAME = "testValidate_0012()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignIndiviConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignIndiviConfirmRequest();
        
        //�X�V�����t���O
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_accopenConditionInfo = new WEB3AccInfoCampaignInfo();
        
        //�o�^�^�C�v
        l_accopenConditionInfo.registType = "2";
        //���X�R�[�h
        l_accopenConditionInfo.branchCode = "011";
        //�ڋq�R�[�h
        l_accopenConditionInfo.accountCode = "123456";
        //�L�����y�[������
        l_accopenConditionInfo.campaignName = "12";
        
        //�萔�������L�����y�[���������
        l_request.commissionCampaignInfo = l_accopenConditionInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02725);
            assertEquals("�L�����y�[�����̓��̓G���[�B", l_ex.getErrorInfo().getErrorMessage());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch (Exception e)
        {
            log.exiting(TEST_START + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     * [���N�G�X�g�f�[�^.�X�V�����t���O = '0'(�o�^)�̏ꍇ]
     *�U�j�@@�Ώۊ���From�ATo�̃`�F�b�N
     * �Ώۊ���From,�Ώۊ���To�����͂��ꂽ�ꍇ�A
     * �Ώۊ���From �� �Ώۊ���To�̏ꍇ
     */
    public void testValidate_0013()
    {
        final String STR_METHOD_NAME = "testValidate_0013()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignIndiviConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignIndiviConfirmRequest();
        
        //�X�V�����t���O
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_accopenConditionInfo = new WEB3AccInfoCampaignInfo();
        
        //�o�^�^�C�v
        l_accopenConditionInfo.registType = "2";
        //���X�R�[�h
        l_accopenConditionInfo.branchCode = "011";
        //�ڋq�R�[�h
        l_accopenConditionInfo.accountCode = "123456";
        //�L�����y�[������
        l_accopenConditionInfo.campaignName = null;
        //�Ώۊ���From
        l_accopenConditionInfo.targetPeriodFrom = WEB3DateUtility.getDate("20070107","yyyyMMdd");
        //�Ώۊ���To
        l_accopenConditionInfo.targetPeriodTo = WEB3DateUtility.getDate("20070105","yyyyMMdd");
        
        //�萔�������L�����y�[���������
        l_request.commissionCampaignInfo = l_accopenConditionInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02715);
            assertEquals("�Ώۊ��ԃG���[�B", l_ex.getErrorInfo().getErrorMessage());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch (Exception e)
        {
            log.exiting(TEST_START + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     * [���N�G�X�g�f�[�^.�X�V�����t���O = '0'(�o�^)�̏ꍇ]
     *�V�j�@@�������̃`�F�b�N
     *�����͂̏ꍇ
     */
    public void testValidate_0014()
    {
        final String STR_METHOD_NAME = "testValidate_0014()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignIndiviConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignIndiviConfirmRequest();
        
        //�X�V�����t���O
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_accopenConditionInfo = new WEB3AccInfoCampaignInfo();
        
        //�o�^�^�C�v
        l_accopenConditionInfo.registType = "2";
        //���X�R�[�h
        l_accopenConditionInfo.branchCode = "011";
        //�ڋq�R�[�h
        l_accopenConditionInfo.accountCode = "123456";
        //�L�����y�[������
        l_accopenConditionInfo.campaignName = null;
        //�Ώۊ���From
        l_accopenConditionInfo.targetPeriodFrom = WEB3DateUtility.getDate("20070107","yyyyMMdd");
        //�Ώۊ���To
        l_accopenConditionInfo.targetPeriodTo = WEB3DateUtility.getDate("20070108","yyyyMMdd");
        //������
        l_accopenConditionInfo.collectRate = null;
        
        //�萔�������L�����y�[���������
        l_request.commissionCampaignInfo = l_accopenConditionInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02080);
            assertEquals("�������������͂ł��B", l_ex.getErrorInfo().getErrorMessage());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch (Exception e)
        {
            log.exiting(TEST_START + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     * [���N�G�X�g�f�[�^.�X�V�����t���O = '0'(�o�^)�̏ꍇ]
     *�V�j�@@�������̃`�F�b�N
     *0�`100�̐����ȊO�̏ꍇ
     */
    public void testValidate_0015()
    {
        final String STR_METHOD_NAME = "testValidate_0015()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignIndiviConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignIndiviConfirmRequest();
        
        //�X�V�����t���O
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_accopenConditionInfo = new WEB3AccInfoCampaignInfo();
        
        //�o�^�^�C�v
        l_accopenConditionInfo.registType = "2";
        //���X�R�[�h
        l_accopenConditionInfo.branchCode = "011";
        //�ڋq�R�[�h
        l_accopenConditionInfo.accountCode = "123456";
        //�L�����y�[������
        l_accopenConditionInfo.campaignName = null;
        //�Ώۊ���From
        l_accopenConditionInfo.targetPeriodFrom = WEB3DateUtility.getDate("20070107","yyyyMMdd");
        //�Ώۊ���To
        l_accopenConditionInfo.targetPeriodTo = WEB3DateUtility.getDate("20070108","yyyyMMdd");
        //������
        l_accopenConditionInfo.collectRate = "101";
        
        //�萔�������L�����y�[���������
        l_request.commissionCampaignInfo = l_accopenConditionInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02082);
            assertEquals("�������̒l���s���ł��B", l_ex.getErrorInfo().getErrorMessage());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch (Exception e)
        {
            log.exiting(TEST_START + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     * [���N�G�X�g�f�[�^.�X�V�����t���O = '0'(�o�^)�̏ꍇ]
     *�V�j�@@�������̃`�F�b�N
     *0�`100�̐����ȊO�̏ꍇ
     */
    public void testValidate_0016()
    {
        final String STR_METHOD_NAME = "testValidate_0016()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignIndiviConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignIndiviConfirmRequest();
        
        //�X�V�����t���O
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_accopenConditionInfo = new WEB3AccInfoCampaignInfo();
        
        //�o�^�^�C�v
        l_accopenConditionInfo.registType = "2";
        //���X�R�[�h
        l_accopenConditionInfo.branchCode = "011";
        //�ڋq�R�[�h
        l_accopenConditionInfo.accountCode = "123456";
        //�L�����y�[������
        l_accopenConditionInfo.campaignName = null;
        //�Ώۊ���From
        l_accopenConditionInfo.targetPeriodFrom = WEB3DateUtility.getDate("20070107","yyyyMMdd");
        //�Ώۊ���To
        l_accopenConditionInfo.targetPeriodTo = WEB3DateUtility.getDate("20070108","yyyyMMdd");
        //������
        l_accopenConditionInfo.collectRate = "-1";
        
        //�萔�������L�����y�[���������
        l_request.commissionCampaignInfo = l_accopenConditionInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02082);
            assertEquals("�������̒l���s���ł��B", l_ex.getErrorInfo().getErrorMessage());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch (Exception e)
        {
            log.exiting(TEST_START + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     * [���N�G�X�g�f�[�^.�X�V�����t���O = '0'(�o�^)�̏ꍇ]
     *�V�j�@@�������̃`�F�b�N
     *0�`100�̐����ȊO�̏ꍇ
     */
    public void testValidate_0017()
    {
        final String STR_METHOD_NAME = "testValidate_0017()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignIndiviConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignIndiviConfirmRequest();
        
        //�X�V�����t���O
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_accopenConditionInfo = new WEB3AccInfoCampaignInfo();
        
        //�o�^�^�C�v
        l_accopenConditionInfo.registType = "2";
        //���X�R�[�h
        l_accopenConditionInfo.branchCode = "011";
        //�ڋq�R�[�h
        l_accopenConditionInfo.accountCode = "123456";
        //�L�����y�[������
        l_accopenConditionInfo.campaignName = null;
        //�Ώۊ���From
        l_accopenConditionInfo.targetPeriodFrom = WEB3DateUtility.getDate("20070107","yyyyMMdd");
        //�Ώۊ���To
        l_accopenConditionInfo.targetPeriodTo = WEB3DateUtility.getDate("20070108","yyyyMMdd");
        //������
        l_accopenConditionInfo.collectRate = "ab";
        
        //�萔�������L�����y�[���������
        l_request.commissionCampaignInfo = l_accopenConditionInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02082);
            assertEquals("�������̒l���s���ł��B", l_ex.getErrorInfo().getErrorMessage());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch (Exception e)
        {
            log.exiting(TEST_START + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     * [���N�G�X�g�f�[�^.�X�V�����t���O = '0'(�o�^)�̏ꍇ]
     *�W�j�@@�Ώۊ���To�̃`�F�b�N
     *�Ώۊ���To�����͂��ꂽ�ꍇ�A���t���ߋ����t�̏ꍇ
     */
    public void testValidate_0018()
    {
        final String STR_METHOD_NAME = "testValidate_0018()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignIndiviConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignIndiviConfirmRequest();
        
        //�X�V�����t���O
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_accopenConditionInfo = new WEB3AccInfoCampaignInfo();
        
        //�o�^�^�C�v
        l_accopenConditionInfo.registType = "2";
        //���X�R�[�h
        l_accopenConditionInfo.branchCode = "011";
        //�ڋq�R�[�h
        l_accopenConditionInfo.accountCode = "123456";
        //�L�����y�[������
        l_accopenConditionInfo.campaignName = null;
        //�Ώۊ���From
        l_accopenConditionInfo.targetPeriodFrom = WEB3DateUtility.getDate("20070109","yyyyMMdd");
        //�Ώۊ���To
        l_accopenConditionInfo.targetPeriodTo = WEB3DateUtility.getDate("20070108","yyyyMMdd");
        //������
        l_accopenConditionInfo.collectRate = "0";
        
        //�萔�������L�����y�[���������
        l_request.commissionCampaignInfo = l_accopenConditionInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02715);
            assertEquals("�Ώۊ��ԃG���[�B", l_ex.getErrorInfo().getErrorMessage());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch (Exception e)
        {
            log.exiting(TEST_START + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     * [���N�G�X�g�f�[�^.�X�V�����t���O = '1'(�ύX)�̏ꍇ]
     *�U�j�@@�Ώۊ���From�ATo�̃`�F�b�N
     * �Ώۊ���From,�Ώۊ���To�����͂��ꂽ�ꍇ�A
     * �Ώۊ���From �� �Ώۊ���To�̏ꍇ
     */
    public void testValidate_0019()
    {
        final String STR_METHOD_NAME = "testValidate_0019()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignIndiviConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignIndiviConfirmRequest();
        
        //�X�V�����t���O
        l_request.updateFlag = "1";
        
        WEB3AccInfoCampaignInfo l_accopenConditionInfo = new WEB3AccInfoCampaignInfo();
        
        //�o�^�^�C�v
        l_accopenConditionInfo.registType = "2";
        //�Ώۊ���From
        l_accopenConditionInfo.targetPeriodFrom = WEB3DateUtility.getDate("20070107","yyyyMMdd");
        //�Ώۊ���To
        l_accopenConditionInfo.targetPeriodTo = WEB3DateUtility.getDate("20070105","yyyyMMdd");
        
        //�萔�������L�����y�[���������
        l_request.commissionCampaignInfo = l_accopenConditionInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02715);
            assertEquals("�Ώۊ��ԃG���[�B", l_ex.getErrorInfo().getErrorMessage());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch (Exception e)
        {
            log.exiting(TEST_START + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     * [���N�G�X�g�f�[�^.�X�V�����t���O = '1'(�ύX)�̏ꍇ]
     *�V�j�@@�������̃`�F�b�N
     *�����͂̏ꍇ
     */
    public void testValidate_0020()
    {
        final String STR_METHOD_NAME = "testValidate_0020()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignIndiviConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignIndiviConfirmRequest();
        
        //�X�V�����t���O
        l_request.updateFlag = "1";
        
        WEB3AccInfoCampaignInfo l_accopenConditionInfo = new WEB3AccInfoCampaignInfo();
        
        //�o�^�^�C�v
        l_accopenConditionInfo.registType = "2";
        //�Ώۊ���From
        l_accopenConditionInfo.targetPeriodFrom = WEB3DateUtility.getDate("20070107","yyyyMMdd");
        //�Ώۊ���To
        l_accopenConditionInfo.targetPeriodTo = WEB3DateUtility.getDate("20070108","yyyyMMdd");
        //������
        l_accopenConditionInfo.collectRate = null;
        
        //�萔�������L�����y�[���������
        l_request.commissionCampaignInfo = l_accopenConditionInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02080);
            assertEquals("�������������͂ł��B", l_ex.getErrorInfo().getErrorMessage());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch (Exception e)
        {
            log.exiting(TEST_START + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     * [���N�G�X�g�f�[�^.�X�V�����t���O = '1'(�ύX)�̏ꍇ]
     *�V�j�@@�������̃`�F�b�N
     *0�`100�̐����ȊO�̏ꍇ
     */
    public void testValidate_0021()
    {
        final String STR_METHOD_NAME = "testValidate_0021()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignIndiviConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignIndiviConfirmRequest();
        
        //�X�V�����t���O
        l_request.updateFlag = "1";
        
        WEB3AccInfoCampaignInfo l_accopenConditionInfo = new WEB3AccInfoCampaignInfo();
        
        //�o�^�^�C�v
        l_accopenConditionInfo.registType = "2";
        //�Ώۊ���From
        l_accopenConditionInfo.targetPeriodFrom = WEB3DateUtility.getDate("20070107","yyyyMMdd");
        //�Ώۊ���To
        l_accopenConditionInfo.targetPeriodTo = WEB3DateUtility.getDate("20070108","yyyyMMdd");
        //������
        l_accopenConditionInfo.collectRate = "101";
        
        //�萔�������L�����y�[���������
        l_request.commissionCampaignInfo = l_accopenConditionInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02082);
            assertEquals("�������̒l���s���ł��B", l_ex.getErrorInfo().getErrorMessage());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch (Exception e)
        {
            log.exiting(TEST_START + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     * [���N�G�X�g�f�[�^.�X�V�����t���O = '1'(�ύX)�̏ꍇ]
     *�V�j�@@�������̃`�F�b�N
     *0�`100�̐����ȊO�̏ꍇ
     */
    public void testValidate_0022()
    {
        final String STR_METHOD_NAME = "testValidate_0022()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignIndiviConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignIndiviConfirmRequest();
        
        //�X�V�����t���O
        l_request.updateFlag = "1";
        
        WEB3AccInfoCampaignInfo l_accopenConditionInfo = new WEB3AccInfoCampaignInfo();
        
        //�o�^�^�C�v
        l_accopenConditionInfo.registType = "2";
        //�Ώۊ���From
        l_accopenConditionInfo.targetPeriodFrom = WEB3DateUtility.getDate("20070107","yyyyMMdd");
        //�Ώۊ���To
        l_accopenConditionInfo.targetPeriodTo = WEB3DateUtility.getDate("20070108","yyyyMMdd");
        //������
        l_accopenConditionInfo.collectRate = "-1";
        
        //�萔�������L�����y�[���������
        l_request.commissionCampaignInfo = l_accopenConditionInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02082);
            assertEquals("�������̒l���s���ł��B", l_ex.getErrorInfo().getErrorMessage());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch (Exception e)
        {
            log.exiting(TEST_START + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     * [���N�G�X�g�f�[�^.�X�V�����t���O = '1'(�ύX)�̏ꍇ]
     *�V�j�@@�������̃`�F�b�N
     *0�`100�̐����ȊO�̏ꍇ
     */
    public void testValidate_0023()
    {
        final String STR_METHOD_NAME = "testValidate_0023()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignIndiviConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignIndiviConfirmRequest();
        
        //�X�V�����t���O
        l_request.updateFlag = "1";
        
        WEB3AccInfoCampaignInfo l_accopenConditionInfo = new WEB3AccInfoCampaignInfo();
        
        //�o�^�^�C�v
        l_accopenConditionInfo.registType = "2";
        //�Ώۊ���From
        l_accopenConditionInfo.targetPeriodFrom = WEB3DateUtility.getDate("20070107","yyyyMMdd");
        //�Ώۊ���To
        l_accopenConditionInfo.targetPeriodTo = WEB3DateUtility.getDate("20070108","yyyyMMdd");
        //������
        l_accopenConditionInfo.collectRate = "ab";
        
        //�萔�������L�����y�[���������
        l_request.commissionCampaignInfo = l_accopenConditionInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02082);
            assertEquals("�������̒l���s���ł��B", l_ex.getErrorInfo().getErrorMessage());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch (Exception e)
        {
            log.exiting(TEST_START + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    
    /**
     * [���N�G�X�g�f�[�^.�X�V�����t���O = '1'(�ύX)�̏ꍇ]
     *�W�j�@@�Ώۊ���To�̃`�F�b�N
     *�Ώۊ���To�����͂��ꂽ�ꍇ�A���t���ߋ����t�̏ꍇ
     */
    //���������S�홈��
//    public void testValidate_0024()
//    {
//        final String STR_METHOD_NAME = "testValidate_0024()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        
//        WEB3AdminAccInfoCampaignIndiviConfirmRequest l_request = 
//            new WEB3AdminAccInfoCampaignIndiviConfirmRequest();
//        
//        //�X�V�����t���O
//        l_request.updateFlag = "1";
//        
//        WEB3AccInfoCampaignInfo l_accopenConditionInfo = new WEB3AccInfoCampaignInfo();
//        
//        //�o�^�^�C�v
//        l_accopenConditionInfo.registType = "2";
//        //�Ώۊ���From
//        l_accopenConditionInfo.targetPeriodFrom = WEB3DateUtility.getDate("20070107","yyyyMMdd");
//        //�Ώۊ���To
//        l_accopenConditionInfo.targetPeriodTo = WEB3DateUtility.getDate("20070108","yyyyMMdd");
//        //������
//        l_accopenConditionInfo.collectRate = "100";
//        
//        //�萔�������L�����y�[���������
//        l_request.commissionCampaignInfo = l_accopenConditionInfo;
//        
//        try
//        {
//            l_request.validate();
//            fail();
//        } 
//        catch (WEB3BaseException l_ex) 
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02715);
//            assertEquals("�Ώۊ��ԃG���[�B", l_ex.getErrorInfo().getErrorMessage());
//            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !");
//        }
//        catch (Exception e)
//        {
//            log.exiting(TEST_START + STR_METHOD_NAME);
//            fail();
//        }
//        log.exiting(TEST_START + STR_METHOD_NAME);
//    }
    
    /**
     * [���N�G�X�g�f�[�^.�X�V�����t���O = '2'(�폜)�̏ꍇ]
     *�W�j�@@�Ώۊ���To�̃`�F�b�N
     *�Ώۊ���To�����͂��ꂽ�ꍇ�A���t���ߋ����t�̏ꍇ
     */
//    public void testValidate_0025()
//    {
//        final String STR_METHOD_NAME = "testValidate_0025()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        
//        WEB3AdminAccInfoCampaignIndiviConfirmRequest l_request = 
//            new WEB3AdminAccInfoCampaignIndiviConfirmRequest();
//        
//        //�X�V�����t���O
//        l_request.updateFlag = "2";
//        
//        WEB3AccInfoCampaignInfo l_accopenConditionInfo = new WEB3AccInfoCampaignInfo();
//        
//        //�o�^�^�C�v
//        l_accopenConditionInfo.registType = "2";
//        //�Ώۊ���From
//        l_accopenConditionInfo.targetPeriodFrom = WEB3DateUtility.getDate("20070109","yyyyMMdd");
//        //�Ώۊ���To
//        l_accopenConditionInfo.targetPeriodTo = WEB3DateUtility.getDate("20070108","yyyyMMdd");
//        //������
//        l_accopenConditionInfo.collectRate = "100";
//        
//        //�萔�������L�����y�[���������
//        l_request.commissionCampaignInfo = l_accopenConditionInfo;
//        
//        try
//        {
//            l_request.validate();
//            fail();
//        } 
//        catch (WEB3BaseException l_ex) 
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02715);
//            assertEquals("�Ώۊ��ԃG���[�B", l_ex.getErrorInfo().getErrorMessage());
//            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !");
//        }
//        catch (Exception e)
//        {
//            log.exiting(TEST_START + STR_METHOD_NAME);
//            fail();
//        }
//        log.exiting(TEST_START + STR_METHOD_NAME);
//    }
    
    /**
     * [���N�G�X�g�f�[�^.�X�V�����t���O = '0'(�o�^)�̏ꍇ]
     *�W�j�@@�Ώۊ���To�̃`�F�b�N
     *�Ώۊ���To�����͂��ꂽ�ꍇ�A���t���ߋ����t�̏ꍇ
     */
    public void testValidate_0026()
    {
        final String STR_METHOD_NAME = "testValidate_0026()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignIndiviConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignIndiviConfirmRequest();
        
        //�X�V�����t���O
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_accopenConditionInfo = new WEB3AccInfoCampaignInfo();
        
        //�o�^�^�C�v
        l_accopenConditionInfo.registType = "2";
        //���X�R�[�h
        l_accopenConditionInfo.branchCode = "011";
        //�ڋq�R�[�h
        l_accopenConditionInfo.accountCode = "123456";
        //�L�����y�[������
        l_accopenConditionInfo.campaignName = null;
        //������
        l_accopenConditionInfo.collectRate = "0";
        
        //�萔�������L�����y�[���������
        l_request.commissionCampaignInfo = l_accopenConditionInfo;
        
        try
        {
            l_request.validate();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch (Exception e)
        {
            log.exiting(TEST_START + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     * [���N�G�X�g�f�[�^.�X�V�����t���O = '1'(�ύX)�̏ꍇ]
     *�W�j�@@�Ώۊ���To�̃`�F�b�N
     *�Ώۊ���To�����͂��ꂽ�ꍇ�A���t���ߋ����t�̏ꍇ
     */
    public void testValidate_0027()
    {
        final String STR_METHOD_NAME = "testValidate_0027()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignIndiviConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignIndiviConfirmRequest();
        
        //�X�V�����t���O
        l_request.updateFlag = "1";
        
        WEB3AccInfoCampaignInfo l_accopenConditionInfo = new WEB3AccInfoCampaignInfo();
        
        //�o�^�^�C�v
        l_accopenConditionInfo.registType = "2";
        //���X�R�[�h
        l_accopenConditionInfo.branchCode = "011";
        //�ڋq�R�[�h
        l_accopenConditionInfo.accountCode = "123456";
        //�L�����y�[������
        l_accopenConditionInfo.campaignName = null;
        //������
        l_accopenConditionInfo.collectRate = "0";
        
        //�萔�������L�����y�[���������
        l_request.commissionCampaignInfo = l_accopenConditionInfo;
        
        try
        {
            l_request.validate();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch (Exception e)
        {
            log.exiting(TEST_START + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     * [���N�G�X�g�f�[�^.�X�V�����t���O = '2'(�폜)�̏ꍇ]
     *�W�j�@@�Ώۊ���To�̃`�F�b�N
     *�Ώۊ���To�����͂��ꂽ�ꍇ�A���t���ߋ����t�̏ꍇ
     */
    public void testValidate_0028()
    {
        final String STR_METHOD_NAME = "testValidate_0028()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignIndiviConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignIndiviConfirmRequest();
        
        //�X�V�����t���O
        l_request.updateFlag = "2";
        
        WEB3AccInfoCampaignInfo l_accopenConditionInfo = new WEB3AccInfoCampaignInfo();
        
        //�o�^�^�C�v
        l_accopenConditionInfo.registType = "2";
        //���X�R�[�h
        l_accopenConditionInfo.branchCode = "011";
        //�ڋq�R�[�h
        l_accopenConditionInfo.accountCode = "123456";
        //�L�����y�[������
        l_accopenConditionInfo.campaignName = null;
        //�Ώۊ���From
        l_accopenConditionInfo.targetPeriodFrom = WEB3DateUtility.getDate("20070107","yyyyMMdd");
        //�Ώۊ���To
        l_accopenConditionInfo.targetPeriodTo = WEB3DateUtility.getDate("20071108","yyyyMMdd");
        //������
        l_accopenConditionInfo.collectRate = "0";
        
        //�萔�������L�����y�[���������
        l_request.commissionCampaignInfo = l_accopenConditionInfo;
        
        try
        {
            l_request.validate();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch (Exception e)
        {
            log.exiting(TEST_START + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
}
@
