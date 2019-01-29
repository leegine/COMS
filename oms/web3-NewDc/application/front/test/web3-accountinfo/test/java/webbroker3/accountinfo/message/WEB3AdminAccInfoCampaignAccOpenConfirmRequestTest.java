head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.06.08.39.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5404d9c264d0465;
filename	WEB3AdminAccInfoCampaignAccOpenConfirmRequestTest.java;


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
 * (�Ǘ��҂��q�l���萔����������߰݌����J�ݏ����ύX�m�Fظ���)<BR>
 * �Ǘ��҂��q�l���萔����������߰݌����J�ݏ����ύX�m�Fظ���<BR>
 * @@author �Ј���
 * @@version 1.0
 */
public class WEB3AdminAccInfoCampaignAccOpenConfirmRequestTest extends TestBaseForMock 
{

    public WEB3AdminAccInfoCampaignAccOpenConfirmRequestTest(String name) 
    {
        super(name);
    }
    
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminAccInfoCampaignAccOpenConfirmRequestTest.class);
    
    /**
     *�P�j �X�V�����t���O�̃`�F�b�N
     *�X�V�����t���O != (0 or 1 or 2) �̏ꍇ
     */
    public void testValidate_0001()
    {
        final String STR_METHOD_NAME = "testValidate_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenConfirmRequest();
        
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
     *�Q�j �X�V�����t���O�� 0 �i�o�^�j�̏ꍇ�A
     *  ���i�R�[�h�̃`�F�b�N
     *  ���i�R�[�h�z��null�̏ꍇ
     */
    public void testValidate_0002()
    {
        final String STR_METHOD_NAME = "testValidate_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenConfirmRequest();
        
        //�X�V�����t���O
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
        
        String[] l_strItemCode = null;
        //���i�R�[�h�z��
        l_commissionCampaignInfo.itemCode = l_strItemCode;
        
        l_request.commissionCampaignInfo = l_commissionCampaignInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02718);
            assertEquals("���i���I���G���[�B",l_ex.getErrorInfo().getErrorMessage());
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
     *�Q�j �X�V�����t���O�� 0 �i�o�^�j�̏ꍇ�A
     *  �L�����y�[�����̂̃`�F�b�N
     *  �L�����y�[�����̂������͂̏ꍇ
     */
    public void testValidate_0003()
    {
        final String STR_METHOD_NAME = "testValidate_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenConfirmRequest();
        
        //�X�V�����t���O
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
        
        String[] l_strItemCode = {"12"};
        //���i�R�[�h�z��
        l_commissionCampaignInfo.itemCode = l_strItemCode;
        //�L�����y�[������
        l_commissionCampaignInfo.campaignName = null;
        
        l_request.commissionCampaignInfo = l_commissionCampaignInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02712);
            assertEquals("�L�����y�[�����̖����̓G���[�B",l_ex.getErrorInfo().getErrorMessage());
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
     *�Q�j �X�V�����t���O�� 0 �i�o�^�j�̏ꍇ�A
     *  �L�����y�[�����̂̃`�F�b�N
     *  �L�����y�[������101�o�C�g�ȏ�̏ꍇ
     */
    public void testValidate_0004()
    {
        final String STR_METHOD_NAME = "testValidate_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenConfirmRequest();
        
        //�X�V�����t���O
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
        
        String[] l_strItemCode = {"12"};
        //���i�R�[�h�z��
        l_commissionCampaignInfo.itemCode = l_strItemCode;
        //�L�����y�[������
        l_commissionCampaignInfo.campaignName = "12345678901234567890123456789012345678901234" +
                "567890123456789012345678901234567890123456789012345678901";
        
        l_request.commissionCampaignInfo = l_commissionCampaignInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02709);
            assertEquals("�L�����y�[�����̌����G���[�B",l_ex.getErrorInfo().getErrorMessage());
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
     *�Q�j �X�V�����t���O�� 0 �i�o�^�j�̏ꍇ�A
     *  �L�����y�[�����̂̃`�F�b�N
     *  �L�����y�[������101�o�C�g�ȏ�̏ꍇ
     */
    public void testValidate_0005()
    {
        final String STR_METHOD_NAME = "testValidate_0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenConfirmRequest();
        
        //�X�V�����t���O
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
        
        String[] l_strItemCode = {"12"};
        //���i�R�[�h�z��
        l_commissionCampaignInfo.itemCode = l_strItemCode;
        //�L�����y�[������
        l_commissionCampaignInfo.campaignName = "12345678901234567890123456789012345678901234" +
                "567890123456789012345678901234567890123456789012345678901123";
        
        l_request.commissionCampaignInfo = l_commissionCampaignInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02709);
            assertEquals("�L�����y�[�����̌����G���[�B",l_ex.getErrorInfo().getErrorMessage());
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
     *�Q�j �X�V�����t���O�� 0 �i�o�^�j�̏ꍇ�A
     *   ���X�R�[�h�̃`�F�b�N
     *   ���X�R�[�h��3���ȊO�̏ꍇ
     */
    public void testValidate_0006()
    {
        final String STR_METHOD_NAME = "testValidate_0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenConfirmRequest();
        
        //�X�V�����t���O
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
        
        String[] l_strItemCode = {"12"};
        //���i�R�[�h�z��
        l_commissionCampaignInfo.itemCode = l_strItemCode;
        //�L�����y�[������
        l_commissionCampaignInfo.campaignName = "0";
        //���X�R�[�h
        l_commissionCampaignInfo.branchCode = "0";
        
        l_request.commissionCampaignInfo = l_commissionCampaignInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00834);
            assertEquals("���X�R�[�h�̃T�C�Y���s���ł��B",l_ex.getErrorInfo().getErrorMessage());
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
     *�Q�j �X�V�����t���O�� 0 �i�o�^�j�̏ꍇ�A
     *   ���X�R�[�h�̃`�F�b�N
     *   ���X�R�[�h��3���ȊO�̏ꍇ
     */
    public void testValidate_0007()
    {
        final String STR_METHOD_NAME = "testValidate_0007()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenConfirmRequest();
        
        //�X�V�����t���O
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
        
        String[] l_strItemCode = {"12"};
        //���i�R�[�h�z��
        l_commissionCampaignInfo.itemCode = l_strItemCode;
        //�L�����y�[������
        l_commissionCampaignInfo.campaignName = "12";
        //���X�R�[�h
        l_commissionCampaignInfo.branchCode = "1234";
        
        l_request.commissionCampaignInfo = l_commissionCampaignInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00834);
            assertEquals("���X�R�[�h�̃T�C�Y���s���ł��B",l_ex.getErrorInfo().getErrorMessage());
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
     *�Q�j �X�V�����t���O�� 0 �i�o�^�j�̏ꍇ�A
     *   ���҃R�[�h�̃`�F�b�N
     *   ���҃R�[�h��6���ȏ�̏ꍇ
     */
    public void testValidate_0008()
    {
        final String STR_METHOD_NAME = "testValidate_0008()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenConfirmRequest();
        
        //�X�V�����t���O
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
        
        String[] l_strItemCode = {"12"};
        //���i�R�[�h�z��
        l_commissionCampaignInfo.itemCode = l_strItemCode;
        //�L�����y�[������
        l_commissionCampaignInfo.campaignName = "12";
        //���X�R�[�h
        l_commissionCampaignInfo.branchCode = "123";
        //���҃R�[�h
        l_commissionCampaignInfo.traderCode = "123456";
        
        l_request.commissionCampaignInfo = l_commissionCampaignInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_01912);
            assertEquals("���҃R�[�h�i������j�̒������s���ł��B",l_ex.getErrorInfo().getErrorMessage());
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
     *�Q�j �X�V�����t���O�� 0 �i�o�^�j�̏ꍇ�A
     *   ���҃R�[�h�̃`�F�b�N
     *   ���҃R�[�h��6���ȏ�̏ꍇ
     */
    public void testValidate_0009()
    {
        final String STR_METHOD_NAME = "testValidate_0009()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenConfirmRequest();
        
        //�X�V�����t���O
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
        
        String[] l_strItemCode = {"12"};
        //���i�R�[�h�z��
        l_commissionCampaignInfo.itemCode = l_strItemCode;
        //�L�����y�[������
        l_commissionCampaignInfo.campaignName = "12";
        //���X�R�[�h
        l_commissionCampaignInfo.branchCode = "123";
        //���҃R�[�h
        l_commissionCampaignInfo.traderCode = "1234567";
        
        l_request.commissionCampaignInfo = l_commissionCampaignInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_01912);
            assertEquals("���҃R�[�h�i������j�̒������s���ł��B",l_ex.getErrorInfo().getErrorMessage());
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
     *�Q�j �X�V�����t���O�� 0 �i�o�^�j�̏ꍇ�A
     *   �Ώۊ���From==null && �Ώۊ���To==null
     *   �����J�݌o�ߊ��ԁi���j�������� && �����J�݌o�ߊ��ԁi���j�������͂̏ꍇ
     */
    public void testValidate_0010()
    {
        final String STR_METHOD_NAME = "testValidate_0010()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenConfirmRequest();
        
        //�X�V�����t���O
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
        
        String[] l_strItemCode = {"12"};
        //���i�R�[�h�z��
        l_commissionCampaignInfo.itemCode = l_strItemCode;
        //�L�����y�[������
        l_commissionCampaignInfo.campaignName = "12";
        //���X�R�[�h
        l_commissionCampaignInfo.branchCode = "123";
        //���҃R�[�h
        l_commissionCampaignInfo.traderCode = "12345";
        //�����J�݌o�ߊ��ԁi���j
        l_commissionCampaignInfo.accopenPassPeriodMonth = "23";
        //�����J�݌o�ߊ��ԁi���j
        l_commissionCampaignInfo.accopenPassPeriodDay = "32";
        
        l_request.commissionCampaignInfo = l_commissionCampaignInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02715);
            assertEquals("�Ώۊ��ԃG���[�B",l_ex.getErrorInfo().getErrorMessage());
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
     *�Q�j �X�V�����t���O�� 0 �i�o�^�j�̏ꍇ�A
     *   �Ώۊ���From==null && �Ώۊ���To==null
     *   �����J�݌o�ߊ��ԁi���j�������� && �����J�݌o�ߊ��ԁi���j�������͂̏ꍇ
     */
    public void testValidate_0011()
    {
        final String STR_METHOD_NAME = "testValidate_0011()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenConfirmRequest();
        
        //�X�V�����t���O
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
        
        String[] l_strItemCode = {"12"};
        //���i�R�[�h�z��
        l_commissionCampaignInfo.itemCode = l_strItemCode;
        //�L�����y�[������
        l_commissionCampaignInfo.campaignName = "12";
        //���X�R�[�h
        l_commissionCampaignInfo.branchCode = "123";
        //���҃R�[�h
        l_commissionCampaignInfo.traderCode = "12345";
        //�����J�݌o�ߊ��ԁi���j
        l_commissionCampaignInfo.accopenPassPeriodMonth = "1";
        //�����J�݌o�ߊ��ԁi���j
        l_commissionCampaignInfo.accopenPassPeriodDay = "a";
        //�����J�ݓ�To
        l_commissionCampaignInfo.accountOpenDateTo = WEB3DateUtility.getDate("20060108","yyyyMMdd");
        
        l_request.commissionCampaignInfo = l_commissionCampaignInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02715);
            assertEquals("�Ώۊ��ԃG���[�B",l_ex.getErrorInfo().getErrorMessage());
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
     *�Q�j �X�V�����t���O�� 0 �i�o�^�j�̏ꍇ�A
     *   �Ώۊ���From==null && �Ώۊ���To==null
     *   �����J�݌o�ߊ��ԁi���j���O�`�P�Q�̐����ȊO�̏ꍇ
     */
    public void testValidate_0012()
    {
        final String STR_METHOD_NAME = "testValidate_0012()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenConfirmRequest();
        
        //�X�V�����t���O
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
        
        String[] l_strItemCode = {"12"};
        //���i�R�[�h�z��
        l_commissionCampaignInfo.itemCode = l_strItemCode;
        //�L�����y�[������
        l_commissionCampaignInfo.campaignName = "12";
        //���X�R�[�h
        l_commissionCampaignInfo.branchCode = "123";
        //���҃R�[�h
        l_commissionCampaignInfo.traderCode = "12345";
        //�����J�݌o�ߊ��ԁi���j
        l_commissionCampaignInfo.accopenPassPeriodMonth = "11";
        //�����J�݌o�ߊ��ԁi���j
        l_commissionCampaignInfo.accopenPassPeriodDay = "36";
        
        l_request.commissionCampaignInfo = l_commissionCampaignInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02715);
            assertEquals("�Ώۊ��ԃG���[�B",l_ex.getErrorInfo().getErrorMessage());
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
     *�Q�j �X�V�����t���O�� 0 �i�o�^�j�̏ꍇ�A
     *   �Ώۊ���From==null && �Ώۊ���To==null
     *   �����J�݌o�ߊ��ԁi���j���O�`�P�Q�̐����ȊO�̏ꍇ
     */
    public void testValidate_0013()
    {
        final String STR_METHOD_NAME = "testValidate_0013()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenConfirmRequest();
        
        //�X�V�����t���O
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
        
        String[] l_strItemCode = {"12"};
        //���i�R�[�h�z��
        l_commissionCampaignInfo.itemCode = l_strItemCode;
        //�L�����y�[������
        l_commissionCampaignInfo.campaignName = "12";
        //���X�R�[�h
        l_commissionCampaignInfo.branchCode = "123";
        //���҃R�[�h
        l_commissionCampaignInfo.traderCode = "12345";
        //�����J�݌o�ߊ��ԁi���j
        l_commissionCampaignInfo.accopenPassPeriodMonth = "-1";
        //�����J�݌o�ߊ��ԁi���j
        l_commissionCampaignInfo.accopenPassPeriodDay = "26";
        
        l_request.commissionCampaignInfo = l_commissionCampaignInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02715);
            assertEquals("�Ώۊ��ԃG���[�B",l_ex.getErrorInfo().getErrorMessage());
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
     *�Q�j �X�V�����t���O�� 0 �i�o�^�j�̏ꍇ�A
     *   �Ώۊ���From==null && �Ώۊ���To==null
     *   �����J�݌o�ߊ��ԁi���j���O�`�R�P�̐����ȊO�̏ꍇ
     */
    public void testValidate_0014()
    {
        final String STR_METHOD_NAME = "testValidate_0014()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenConfirmRequest();
        
        //�X�V�����t���O
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
        
        String[] l_strItemCode = {"12"};
        //���i�R�[�h�z��
        l_commissionCampaignInfo.itemCode = l_strItemCode;
        //�L�����y�[������
        l_commissionCampaignInfo.campaignName = "12";
        //���X�R�[�h
        l_commissionCampaignInfo.branchCode = "123";
        //���҃R�[�h
        l_commissionCampaignInfo.traderCode = "12345";
        //�����J�݌o�ߊ��ԁi���j
        l_commissionCampaignInfo.accopenPassPeriodMonth = "0";
        //�����J�݌o�ߊ��ԁi���j
        l_commissionCampaignInfo.accopenPassPeriodDay = "32";
        
        l_request.commissionCampaignInfo = l_commissionCampaignInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02715);
            assertEquals("�Ώۊ��ԃG���[�B",l_ex.getErrorInfo().getErrorMessage());
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
     *�Q�j �X�V�����t���O�� 0 �i�o�^�j�̏ꍇ�A
     *   �Ώۊ���From==null && �Ώۊ���To==null
     *   �����J�݌o�ߊ��ԁi���j���O�`�R�P�̐����ȊO�̏ꍇ
     */
    public void testValidate_0015()
    {
        final String STR_METHOD_NAME = "testValidate_0015()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenConfirmRequest();
        
        //�X�V�����t���O
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
        
        String[] l_strItemCode = {"12"};
        //���i�R�[�h�z��
        l_commissionCampaignInfo.itemCode = l_strItemCode;
        //�L�����y�[������
        l_commissionCampaignInfo.campaignName = "12";
        //���X�R�[�h
        l_commissionCampaignInfo.branchCode = "123";
        //���҃R�[�h
        l_commissionCampaignInfo.traderCode = "12345";
        //�����J�݌o�ߊ��ԁi���j
        l_commissionCampaignInfo.accopenPassPeriodMonth = "12";
        //�����J�݌o�ߊ��ԁi���j
        l_commissionCampaignInfo.accopenPassPeriodDay = "-1";
        
        l_request.commissionCampaignInfo = l_commissionCampaignInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02715);
            assertEquals("�Ώۊ��ԃG���[�B",l_ex.getErrorInfo().getErrorMessage());
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
     *�Q�j �X�V�����t���O�� 0 �i�o�^�j�̏ꍇ�A
     *   �Ώۊ���From==null && �Ώۊ���To==null
     *   �����J�ݓ�To!=null�̏ꍇ
     *   �����J�ݓ�To�Ɍ����J�݌o�ߊ���(��)�ƌ����J�݌o�ߊ��ԁi���j�����Z�������t���ߋ����t�̏ꍇ
     */
    public void testValidate_0016()
    {
        final String STR_METHOD_NAME = "testValidate_0016()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenConfirmRequest();
        
        //�X�V�����t���O
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
        
        String[] l_strItemCode = {"12"};
        //���i�R�[�h�z��
        l_commissionCampaignInfo.itemCode = l_strItemCode;
        //�L�����y�[������
        l_commissionCampaignInfo.campaignName = "12";
        //���X�R�[�h
        l_commissionCampaignInfo.branchCode = "123";
        //���҃R�[�h
        l_commissionCampaignInfo.traderCode = "12345";
        //�����J�݌o�ߊ��ԁi���j
        l_commissionCampaignInfo.accopenPassPeriodMonth = "0";
        //�����J�݌o�ߊ��ԁi���j
        l_commissionCampaignInfo.accopenPassPeriodDay = "0";
        //�����J�ݓ�To
        l_commissionCampaignInfo.accountOpenDateTo = WEB3DateUtility.getDate("20070108","yyyyMMdd");
        
        l_request.commissionCampaignInfo = l_commissionCampaignInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02715);
            assertEquals("�Ώۊ��ԃG���[�B",l_ex.getErrorInfo().getErrorMessage());
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
     *�Q�j �X�V�����t���O�� 0 �i�o�^�j�̏ꍇ�A
     *   �Ώۊ���From==null && �Ώۊ���To==null
     *   �������̃`�F�b�N
     *   �������������͂̏ꍇ
     */
    public void testValidate_0017()
    {
        final String STR_METHOD_NAME = "testValidate_0017()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenConfirmRequest();
        
        //�X�V�����t���O
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
        
        String[] l_strItemCode = {"12"};
        //���i�R�[�h�z��
        l_commissionCampaignInfo.itemCode = l_strItemCode;
        //�L�����y�[������
        l_commissionCampaignInfo.campaignName = "12";
        //���X�R�[�h
        l_commissionCampaignInfo.branchCode = "123";
        //���҃R�[�h
        l_commissionCampaignInfo.traderCode = "12345";
        //�����J�݌o�ߊ��ԁi���j
        l_commissionCampaignInfo.accopenPassPeriodMonth = "0";
        //�����J�݌o�ߊ��ԁi���j
        l_commissionCampaignInfo.accopenPassPeriodDay = "31";
        
        l_commissionCampaignInfo.accountOpenDiv="1";
        
        l_commissionCampaignInfo.collectRate ="";
        
        l_request.commissionCampaignInfo = l_commissionCampaignInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02080);
            assertEquals("�������������͂ł��B",l_ex.getErrorInfo().getErrorMessage());
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
     *�Q�j �X�V�����t���O�� 0 �i�o�^�j�̏ꍇ�A
     *   �Ώۊ���From==null && �Ώۊ���To==null
     *   �������̃`�F�b�N
     *   �������� 0 �` 100 �̐����ȊO�̏ꍇ
     */
    public void testValidate_0018()
    {
        final String STR_METHOD_NAME = "testValidate_0018()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenConfirmRequest();
        
        //�X�V�����t���O
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
        
        String[] l_strItemCode = {"12"};
        //���i�R�[�h�z��
        l_commissionCampaignInfo.itemCode = l_strItemCode;
        //�L�����y�[������
        l_commissionCampaignInfo.campaignName = "12";
        //���X�R�[�h
        l_commissionCampaignInfo.branchCode = "123";
        //���҃R�[�h
        l_commissionCampaignInfo.traderCode = "12345";
        //�����J�݌o�ߊ��ԁi���j
        l_commissionCampaignInfo.accopenPassPeriodMonth = "12";
        //�����J�݌o�ߊ��ԁi���j
        l_commissionCampaignInfo.accopenPassPeriodDay = "31";
        //�����J�ݓ�To
        l_commissionCampaignInfo.accountOpenDateTo = WEB3DateUtility.getDate("20070108","yyyyMMdd");
        //������
        l_commissionCampaignInfo.collectRate = "101";
        
        l_commissionCampaignInfo.accountOpenDiv="1";
        
        l_request.commissionCampaignInfo = l_commissionCampaignInfo;
        
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02082);
            assertEquals("�������̒l���s���ł��B",l_ex.getErrorInfo().getErrorMessage());
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
     *�Q�j �X�V�����t���O�� 0 �i�o�^�j�̏ꍇ�A
     *   �Ώۊ���From==null && �Ώۊ���To==null
     *   �����J�ݓ�To!=null�̏ꍇ
     *   �����J�ݓ�To�Ɍ����J�݌o�ߊ���(��)�ƌ����J�݌o�ߊ��ԁi���j�����Z�������t���ߋ����t�̏ꍇ
     *   �������̃`�F�b�N
     *   �������� 0 �` 100 �̐����ȊO�̏ꍇ
     */
    public void testValidate_0019()
    {
        final String STR_METHOD_NAME = "testValidate_0019()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenConfirmRequest();
        
        //�X�V�����t���O
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
        
        String[] l_strItemCode = {"12"};
        //���i�R�[�h�z��
        l_commissionCampaignInfo.itemCode = l_strItemCode;
        //�L�����y�[������
        l_commissionCampaignInfo.campaignName = "12";
        //���X�R�[�h
        l_commissionCampaignInfo.branchCode = "123";
        //���҃R�[�h
        l_commissionCampaignInfo.traderCode = "12345";
        //�����J�݌o�ߊ��ԁi���j
        l_commissionCampaignInfo.accopenPassPeriodMonth = "12";
        //�����J�݌o�ߊ��ԁi���j
        l_commissionCampaignInfo.accopenPassPeriodDay = "31";
        //�����J�ݓ�To
        l_commissionCampaignInfo.accountOpenDateTo = WEB3DateUtility.getDate("20070108","yyyyMMdd");
        //������
        l_commissionCampaignInfo.collectRate = "-1";
        
        l_commissionCampaignInfo.accountOpenDiv="1";
        
        l_request.commissionCampaignInfo = l_commissionCampaignInfo;
        
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02082);
            assertEquals("�������̒l���s���ł��B",l_ex.getErrorInfo().getErrorMessage());
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
     *�Q�j �X�V�����t���O�� 0 �i�o�^�j�̏ꍇ�A
     *   �Ώۊ���From==null && �Ώۊ���To==null
     *   �����J�ݓ�To!=null�̏ꍇ
     *   �����J�ݓ�To�Ɍ����J�݌o�ߊ���(��)�ƌ����J�݌o�ߊ��ԁi���j�����Z�������t���ߋ����t�̏ꍇ
     *   �������̃`�F�b�N
     *   �������� 0 �` 100 �̐����ȊO�̏ꍇ
     */
    public void testValidate_0020()
    {
        final String STR_METHOD_NAME = "testValidate_0020()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenConfirmRequest();
        
        //�X�V�����t���O
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
        
        String[] l_strItemCode = {"12"};
        //���i�R�[�h�z��
        l_commissionCampaignInfo.itemCode = l_strItemCode;
        //�L�����y�[������
        l_commissionCampaignInfo.campaignName = "12";
        //���X�R�[�h
        l_commissionCampaignInfo.branchCode = "123";
        //���҃R�[�h
        l_commissionCampaignInfo.traderCode = "12345";
        //�����J�݌o�ߊ��ԁi���j
        l_commissionCampaignInfo.accopenPassPeriodMonth = "12";
        //�����J�݌o�ߊ��ԁi���j
        l_commissionCampaignInfo.accopenPassPeriodDay = "31";
        //�����J�ݓ�To
        l_commissionCampaignInfo.accountOpenDateTo = WEB3DateUtility.getDate("20070108","yyyyMMdd");
        //������
        l_commissionCampaignInfo.collectRate = "ab";
        
        l_commissionCampaignInfo.accountOpenDiv="1";
        
        l_request.commissionCampaignInfo = l_commissionCampaignInfo;
        
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02082);
            assertEquals("�������̒l���s���ł��B",l_ex.getErrorInfo().getErrorMessage());
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
     *�Q�j �X�V�����t���O�� 0 �i�o�^�j�̏ꍇ�A
     *   �����J�݌o�ߊ��ԁi���j==null && �����J�݌o�ߊ��ԁi���j==null 
     *   �Ώۊ���From���͑Ώۊ���To�������͂̏ꍇ
     */
    public void testValidate_0021()
    {
        final String STR_METHOD_NAME = "testValidate_0021()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenConfirmRequest();
        
        //�X�V�����t���O
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
        
        String[] l_strItemCode = {"12"};
        //���i�R�[�h�z��
        l_commissionCampaignInfo.itemCode = l_strItemCode;
        //�L�����y�[������
        l_commissionCampaignInfo.campaignName = "12";
        //���X�R�[�h
        l_commissionCampaignInfo.branchCode = "123";
        //���҃R�[�h
        l_commissionCampaignInfo.traderCode = "12345";
        //�Ώۊ���From
        l_commissionCampaignInfo.targetPeriodFrom =  WEB3DateUtility.getDate("20070108","yyyyMMdd");
        
        l_request.commissionCampaignInfo = l_commissionCampaignInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02713);
            assertEquals("�Ώۊ��Ԗ����̓G���[�B",l_ex.getErrorInfo().getErrorMessage());
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
     *�Q�j �X�V�����t���O�� 0 �i�o�^�j�̏ꍇ�A
     *   �����J�݌o�ߊ��ԁi���j==null && �����J�݌o�ߊ��ԁi���j==null 
     *   �Ώۊ���From���͑Ώۊ���To�������͂̏ꍇ
     */
    public void testValidate_0022()
    {
        final String STR_METHOD_NAME = "testValidate_0022()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenConfirmRequest();
        
        //�X�V�����t���O
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
        
        String[] l_strItemCode = {"12"};
        //���i�R�[�h�z��
        l_commissionCampaignInfo.itemCode = l_strItemCode;
        //�L�����y�[������
        l_commissionCampaignInfo.campaignName = "12";
        //���X�R�[�h
        l_commissionCampaignInfo.branchCode = "123";
        //���҃R�[�h
        l_commissionCampaignInfo.traderCode = "12345";
        //�Ώۊ���To
        l_commissionCampaignInfo.targetPeriodTo =  WEB3DateUtility.getDate("20070108","yyyyMMdd");
        
        l_request.commissionCampaignInfo = l_commissionCampaignInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02713);
            assertEquals("�Ώۊ��Ԗ����̓G���[�B",l_ex.getErrorInfo().getErrorMessage());
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
     *�Q�j �X�V�����t���O�� 0 �i�o�^�j�̏ꍇ�A
     *   �����J�݌o�ߊ��ԁi���j==null && �����J�݌o�ߊ��ԁi���j==null 
     *   �Ώۊ���From > �Ώۊ���To�̏ꍇ
     */
    public void testValidate_0023()
    {
        final String STR_METHOD_NAME = "testValidate_0023()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenConfirmRequest();
        
        //�X�V�����t���O
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
        
        String[] l_strItemCode = {"12"};
        //���i�R�[�h�z��
        l_commissionCampaignInfo.itemCode = l_strItemCode;
        //�L�����y�[������
        l_commissionCampaignInfo.campaignName = "12";
        //���X�R�[�h
        l_commissionCampaignInfo.branchCode = "123";
        //���҃R�[�h
        l_commissionCampaignInfo.traderCode = "12345";
        //�Ώۊ���To
        l_commissionCampaignInfo.targetPeriodTo =  WEB3DateUtility.getDate("20070108","yyyyMMdd");
        //�Ώۊ���From
        l_commissionCampaignInfo.targetPeriodFrom =  WEB3DateUtility.getDate("20071108","yyyyMMdd");
        
        l_request.commissionCampaignInfo = l_commissionCampaignInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02715);
            assertEquals("�Ώۊ��ԃG���[�B",l_ex.getErrorInfo().getErrorMessage());
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
     *�Q�j �X�V�����t���O�� 0 �i�o�^�j�̏ꍇ�A
     *   �����J�݌o�ߊ��ԁi���j==null && �����J�݌o�ߊ��ԁi���j==null 
     *   �Ώۊ���To�̓��t���ߋ����t�̏ꍇ
     */
    public void testValidate_0024()
    {
        final String STR_METHOD_NAME = "testValidate_0024()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenConfirmRequest();
        
        //�X�V�����t���O
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
        
        String[] l_strItemCode = {"12"};
        //���i�R�[�h�z��
        l_commissionCampaignInfo.itemCode = l_strItemCode;
        //�L�����y�[������
        l_commissionCampaignInfo.campaignName = "12";
        //���X�R�[�h
        l_commissionCampaignInfo.branchCode = "123";
        //���҃R�[�h
        l_commissionCampaignInfo.traderCode = "12345";
        //�Ώۊ���To
        l_commissionCampaignInfo.targetPeriodTo =  WEB3DateUtility.getDate("20070104","yyyyMMdd");
        //�Ώۊ���From
        l_commissionCampaignInfo.targetPeriodFrom =  WEB3DateUtility.getDate("20070108","yyyyMMdd");
        
        l_request.commissionCampaignInfo = l_commissionCampaignInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02715);
            assertEquals("�Ώۊ��ԃG���[�B",l_ex.getErrorInfo().getErrorMessage());
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
     *�Q�j �X�V�����t���O�� 0 �i�o�^�j�̏ꍇ�A
     *   �����J�݋敪�̃`�F�b�N
     *   �����J�݋敪�� 1:���������@@2:�M�p���� 3:�敨OP�����@@4:FX�����@@5:���������� �ȊO�̏ꍇ
     */
    public void testValidate_0025()
    {
//        final String STR_METHOD_NAME = "testValidate_0025()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        
//        WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request = 
//            new WEB3AdminAccInfoCampaignAccOpenConfirmRequest();
//        
//        //�X�V�����t���O
//        l_request.updateFlag = "0";
//        
//        WEB3AccInfoCampaignInfo l_commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
//        
//        String[] l_strItemCode = {"12"};
//        //���i�R�[�h�z��
//        l_commissionCampaignInfo.itemCode = l_strItemCode;
//        //�L�����y�[������
//        l_commissionCampaignInfo.campaignName = "12";
//        //���X�R�[�h
//        l_commissionCampaignInfo.branchCode = "123";
//        //���҃R�[�h
//        l_commissionCampaignInfo.traderCode = "12345";
//
//        //�Ώۊ���To
//        l_commissionCampaignInfo.targetPeriodTo =  WEB3DateUtility.getDate("20071108","yyyyMMdd");
//        //�Ώۊ���From
//        l_commissionCampaignInfo.targetPeriodFrom =  WEB3DateUtility.getDate("20070104","yyyyMMdd");
//        //������
//        l_commissionCampaignInfo.collectRate = "100";
//        //�����J�݋敪
//        l_commissionCampaignInfo.accountOpenDiv = "6";
//        
//        l_request.commissionCampaignInfo = l_commissionCampaignInfo;
//        
//        try
//        {
//            l_request.validate();
//            fail();
//        } 
//        catch (WEB3BaseException l_ex) 
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02719);
//            assertEquals("�����J�݋敪�G���[�B",l_ex.getErrorInfo().getErrorMessage());
//            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !");
//        }
//        catch (Exception e)
//        {
//            log.exiting(TEST_START + STR_METHOD_NAME);
//            fail();
//        }
//        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     *�Q�j �X�V�����t���O�� 0 �i�o�^�j�̏ꍇ�A
     *    �����J�ݓ��̃`�F�b�N
     *    (�Ώۊ���From != null && �Ώۊ���To != null) AND (�����J�ݓ�From !=null OR �����J�ݓ�To != null)�̏ꍇ�A
     */
    public void testValidate_0026()
    {
        final String STR_METHOD_NAME = "testValidate_0026()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenConfirmRequest();
        
        //�X�V�����t���O
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
        
        String[] l_strItemCode = {"12"};
        //���i�R�[�h�z��
        l_commissionCampaignInfo.itemCode = l_strItemCode;
        //�L�����y�[������
        l_commissionCampaignInfo.campaignName = "12";
        //���X�R�[�h
        l_commissionCampaignInfo.branchCode = "123";
        //���҃R�[�h
        l_commissionCampaignInfo.traderCode = "12345";
        //�Ώۊ���To
        l_commissionCampaignInfo.targetPeriodTo =  WEB3DateUtility.getDate("20071108","yyyyMMdd");
        //�Ώۊ���From
        l_commissionCampaignInfo.targetPeriodFrom =  WEB3DateUtility.getDate("20070104","yyyyMMdd");
        //������
        l_commissionCampaignInfo.collectRate = "100";
        //�����J�݋敪
        l_commissionCampaignInfo.accountOpenDiv = "1";
        //�����J�ݓ�From
        l_commissionCampaignInfo.accountOpenDateFrom = WEB3DateUtility.getDate("20070104","yyyyMMdd");
        
        l_request.commissionCampaignInfo = l_commissionCampaignInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02720);
            assertEquals("�����J�ݓ��G���[�B",l_ex.getErrorInfo().getErrorMessage());
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
     *�Q�j �X�V�����t���O�� 0 �i�o�^�j�̏ꍇ�A
     *    �����J�ݓ��̃`�F�b�N
     *    (�Ώۊ���From != null && �Ώۊ���To != null) AND (�����J�ݓ�From !=null OR �����J�ݓ�To != null)�̏ꍇ�A
     */
    public void testValidate_0027()
    {
        final String STR_METHOD_NAME = "testValidate_0027()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenConfirmRequest();
        
        //�X�V�����t���O
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
        
        String[] l_strItemCode = {"12"};
        //���i�R�[�h�z��
        l_commissionCampaignInfo.itemCode = l_strItemCode;
        //�L�����y�[������
        l_commissionCampaignInfo.campaignName = "12";
        //���X�R�[�h
        l_commissionCampaignInfo.branchCode = "123";
        //���҃R�[�h
        l_commissionCampaignInfo.traderCode = "12345";
        //�Ώۊ���To
        l_commissionCampaignInfo.targetPeriodTo =  WEB3DateUtility.getDate("20071108","yyyyMMdd");
        //�Ώۊ���From
        l_commissionCampaignInfo.targetPeriodFrom =  WEB3DateUtility.getDate("20070104","yyyyMMdd");
        //������
        l_commissionCampaignInfo.collectRate = "100";
        //�����J�݋敪
        l_commissionCampaignInfo.accountOpenDiv = "2";
        //�����J�ݓ�To
        l_commissionCampaignInfo.accountOpenDateTo = WEB3DateUtility.getDate("20070104","yyyyMMdd");
        
        l_request.commissionCampaignInfo = l_commissionCampaignInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02720);
            assertEquals("�����J�ݓ��G���[�B",l_ex.getErrorInfo().getErrorMessage());
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
     *�Q�j �X�V�����t���O�� 0 �i�o�^�j�̏ꍇ�A
     *    �����J�ݓ��̃`�F�b�N
     *    �����J�ݓ�To���ߋ����t�̏ꍇ
     */
    public void testValidate_0029()
    {
        final String STR_METHOD_NAME = "testValidate_0029()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenConfirmRequest();
        
        //�X�V�����t���O
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
        
        String[] l_strItemCode = {"12"};
        //���i�R�[�h�z��
        l_commissionCampaignInfo.itemCode = l_strItemCode;
        //�L�����y�[������
        l_commissionCampaignInfo.campaignName = "12";
        //���X�R�[�h
        l_commissionCampaignInfo.branchCode = "123";
        //���҃R�[�h
        l_commissionCampaignInfo.traderCode = "12345";
        //�Ώۊ���From
        l_commissionCampaignInfo.targetPeriodFrom =  WEB3DateUtility.getDate("20070104","yyyyMMdd");
        //������
        l_commissionCampaignInfo.collectRate = "100";
        //�����J�݋敪
        l_commissionCampaignInfo.accountOpenDiv = "4";
        
        //�����J�݌o�ߊ��ԁi���j
        l_commissionCampaignInfo.accopenPassPeriodMonth = "12";
        //�����J�݌o�ߊ��ԁi���j
        l_commissionCampaignInfo.accopenPassPeriodDay = "31";
        //�����J�ݓ�To
        l_commissionCampaignInfo.accountOpenDateTo = WEB3DateUtility.getDate("20070104","yyyyMMdd");
        
        l_request.commissionCampaignInfo = l_commissionCampaignInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02720);
            assertEquals("�����J�ݓ��G���[�B",l_ex.getErrorInfo().getErrorMessage());
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
     *�Q�j �X�V�����t���O�� 1 �i�X�V�j�̏ꍇ�A
     *    �����J�ݓ��̃`�F�b�N
     *    �����J�ݓ�To���ߋ����t�̏ꍇ
     */
    public void testValidate_0030()
    {
        final String STR_METHOD_NAME = "testValidate_0030()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenConfirmRequest();
        
        //�X�V�����t���O
        l_request.updateFlag = "1";
        
        WEB3AccInfoCampaignInfo l_commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
        
        String[] l_strItemCode = {"12"};
        //���i�R�[�h�z��
        l_commissionCampaignInfo.itemCode = l_strItemCode;
        //�L�����y�[������
        l_commissionCampaignInfo.campaignName = "12";
        //���X�R�[�h
        l_commissionCampaignInfo.branchCode = "123";
        //���҃R�[�h
        l_commissionCampaignInfo.traderCode = "12345";
        //�Ώۊ���From
        l_commissionCampaignInfo.targetPeriodFrom =  WEB3DateUtility.getDate("20070104","yyyyMMdd");
        //������
        l_commissionCampaignInfo.collectRate = "100";
        //�����J�݋敪
        l_commissionCampaignInfo.accountOpenDiv = "5";
        
        //�����J�݌o�ߊ��ԁi���j
        l_commissionCampaignInfo.accopenPassPeriodMonth = "12";
        //�����J�݌o�ߊ��ԁi���j
        l_commissionCampaignInfo.accopenPassPeriodDay = "31";
        //�����J�ݓ�To
        l_commissionCampaignInfo.accountOpenDateTo = WEB3DateUtility.getDate("20070104","yyyyMMdd");
        
        l_request.commissionCampaignInfo = l_commissionCampaignInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02720);
            assertEquals("�����J�ݓ��G���[�B",l_ex.getErrorInfo().getErrorMessage());
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
     *�R�j �X�V�����t���O�� 1 �i�X�V�j�̏ꍇ�A
     *    �萔�������L�����y�[������ID�̃`�F�b�N
     *    �萔�������L�����y�[������ID�������͂̏ꍇ
     */
    public void testValidate_0031()
    {
        final String STR_METHOD_NAME = "testValidate_0031()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenConfirmRequest();
        
        //�X�V�����t���O
        l_request.updateFlag = "1";
        
        WEB3AccInfoCampaignInfo l_commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
        
        String[] l_strItemCode = {"12"};
        //���i�R�[�h�z��
        l_commissionCampaignInfo.itemCode = l_strItemCode;
        //�L�����y�[������
        l_commissionCampaignInfo.campaignName = "12";
        //���X�R�[�h
        l_commissionCampaignInfo.branchCode = "123";
        //���҃R�[�h
        l_commissionCampaignInfo.traderCode = "12345";
        //�Ώۊ���From
        l_commissionCampaignInfo.targetPeriodFrom =  WEB3DateUtility.getDate("20070104","yyyyMMdd");
        
        l_commissionCampaignInfo.targetPeriodTo =  WEB3DateUtility.getDate("20070108","yyyyMMdd");
        
        //������
        l_commissionCampaignInfo.collectRate = "100";
        //�����J�݋敪
        l_commissionCampaignInfo.accountOpenDiv = "1";
        
        //�����J�݌o�ߊ��ԁi���j
        l_commissionCampaignInfo.accopenPassPeriodMonth = "12";
        //�����J�݌o�ߊ��ԁi���j
        l_commissionCampaignInfo.accopenPassPeriodDay = "31";
        
        l_commissionCampaignInfo.accountOpenDateFrom = WEB3DateUtility.getDate("20071104","yyyyMMdd");
        //�����J�ݓ�To
        l_commissionCampaignInfo.accountOpenDateTo = WEB3DateUtility.getDate("20071108","yyyyMMdd");
        
        l_commissionCampaignInfo.registType = "0";
        
        l_request.commissionCampaignInfo = l_commissionCampaignInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02716);
            assertEquals("�萔�������L�����y�[������ID�����w��ł��B",l_ex.getErrorInfo().getErrorMessage());
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
     *�R�j �X�V�����t���O�� 2�i�폜�j�̏ꍇ�A
     *    �萔�������L�����y�[������ID�̃`�F�b�N
     *    �萔�������L�����y�[������ID�������͂̏ꍇ
     */
    public void testValidate_0032()
    {
        final String STR_METHOD_NAME = "testValidate_0032()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenConfirmRequest();
        
        //�X�V�����t���O
        l_request.updateFlag = "2";
        
        WEB3AccInfoCampaignInfo l_commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
        
        l_request.commissionCampaignInfo = l_commissionCampaignInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02716);
            assertEquals("�萔�������L�����y�[������ID�����w��ł��B",l_ex.getErrorInfo().getErrorMessage());
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
     *�S�j �o�^�^�C�v�̃`�F�b�N
     *�o�^�^�C�v != 0 �̏ꍇ
     */
    public void testValidate_0033()
    {
        final String STR_METHOD_NAME = "testValidate_0033()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenConfirmRequest();
        
//        //�X�V�����t���O
//        l_request.updateFlag = "2";
//             
//        WEB3AccInfoCampaignInfo l_commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
//        
//        //�萔�������L�����y�[������ID
//        l_commissionCampaignInfo.campaignId = "12";
//        //�o�^�^�C�v 
//        l_commissionCampaignInfo.registType = "1";
        
        //�X�V�����t���O
        l_request.updateFlag = "1";
        
        WEB3AccInfoCampaignInfo l_commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
        
        String[] l_strItemCode = {"12"};
        //���i�R�[�h�z��
        l_commissionCampaignInfo.itemCode = l_strItemCode;
        //�L�����y�[������
        l_commissionCampaignInfo.campaignName = "12";
        //���X�R�[�h
        l_commissionCampaignInfo.branchCode = "123";
        //���҃R�[�h
        l_commissionCampaignInfo.traderCode = "12345";
        //�Ώۊ���From
        l_commissionCampaignInfo.targetPeriodFrom =  WEB3DateUtility.getDate("20070104","yyyyMMdd");
        
        l_commissionCampaignInfo.targetPeriodTo =  WEB3DateUtility.getDate("20070108","yyyyMMdd");
        
        //������
        l_commissionCampaignInfo.collectRate = "100";
        //�����J�݋敪
        l_commissionCampaignInfo.accountOpenDiv = "1";
        
        //�����J�݌o�ߊ��ԁi���j
        l_commissionCampaignInfo.accopenPassPeriodMonth = "12";
        //�����J�݌o�ߊ��ԁi���j
        l_commissionCampaignInfo.accopenPassPeriodDay = "31";
        
        l_commissionCampaignInfo.accountOpenDateFrom = WEB3DateUtility.getDate("20071104","yyyyMMdd");
        //�����J�ݓ�To
        l_commissionCampaignInfo.accountOpenDateTo = WEB3DateUtility.getDate("20071108","yyyyMMdd");
        
        l_commissionCampaignInfo.registType = "1";
        
        
        l_request.commissionCampaignInfo = l_commissionCampaignInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02722);
            assertEquals("�o�^�^�C�v��'0'�ȊO�̒l�ł��B",l_ex.getErrorInfo().getErrorMessage());
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
     *OK_0
     */
    public void testValidate_0034()
    {
        final String STR_METHOD_NAME = "testValidate_0034()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenConfirmRequest();
        
        //�X�V�����t���O
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
        
        String[] l_strItemCode = {"12"};
        //���i�R�[�h�z��
        l_commissionCampaignInfo.itemCode = l_strItemCode;
        //�L�����y�[������
        l_commissionCampaignInfo.campaignName = "12";
        //���X�R�[�h
        l_commissionCampaignInfo.branchCode = "123";
        //���҃R�[�h
        l_commissionCampaignInfo.traderCode = "12345";
        //�Ώۊ���From
        l_commissionCampaignInfo.targetPeriodFrom =  WEB3DateUtility.getDate("20070104","yyyyMMdd");
        //������
        l_commissionCampaignInfo.collectRate = "100";
        //�����J�݋敪
        l_commissionCampaignInfo.accountOpenDiv = "1";
        
        //�����J�݌o�ߊ��ԁi���j
        l_commissionCampaignInfo.accopenPassPeriodMonth = "12";
        //�����J�݌o�ߊ��ԁi���j
        l_commissionCampaignInfo.accopenPassPeriodDay = "31";
        //�����J�ݓ�To
        l_commissionCampaignInfo.accountOpenDateTo = WEB3DateUtility.getDate("20071104","yyyyMMdd");
        
        //�萔�������L�����y�[������ID
        l_commissionCampaignInfo.campaignId = "12";
        //�o�^�^�C�v 
        l_commissionCampaignInfo.registType = "0";
        
        l_request.commissionCampaignInfo = l_commissionCampaignInfo;
        
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
     *OK_1
     */
    public void testValidate_0035()
    {
        final String STR_METHOD_NAME = "testValidate_0035()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenConfirmRequest();
        
        //�X�V�����t���O
        l_request.updateFlag = "1";
        
        WEB3AccInfoCampaignInfo l_commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
        
        String[] l_strItemCode = {"12"};
        //���i�R�[�h�z��
        l_commissionCampaignInfo.itemCode = l_strItemCode;
        //�L�����y�[������
        l_commissionCampaignInfo.campaignName = "12";
        //���X�R�[�h
        l_commissionCampaignInfo.branchCode = "123";
        //���҃R�[�h
        l_commissionCampaignInfo.traderCode = "12345";
        //�Ώۊ���From
        l_commissionCampaignInfo.targetPeriodFrom =  WEB3DateUtility.getDate("20070104","yyyyMMdd");
        //������
        l_commissionCampaignInfo.collectRate = "100";
        //�����J�݋敪
        l_commissionCampaignInfo.accountOpenDiv = "1";
        
        //�����J�݌o�ߊ��ԁi���j
        l_commissionCampaignInfo.accopenPassPeriodMonth = "12";
        //�����J�݌o�ߊ��ԁi���j
        l_commissionCampaignInfo.accopenPassPeriodDay = "31";
        //�����J�ݓ�To
        l_commissionCampaignInfo.accountOpenDateTo = WEB3DateUtility.getDate("20071104","yyyyMMdd");
        
        //�萔�������L�����y�[������ID
        l_commissionCampaignInfo.campaignId = "12";
        //�o�^�^�C�v 
        l_commissionCampaignInfo.registType = "0";
        
        l_request.commissionCampaignInfo = l_commissionCampaignInfo;
        
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
     * OK_2
     */
    public void testValidate_0036()
    {
        final String STR_METHOD_NAME = "testValidate_0036()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenConfirmRequest();
        
        //�X�V�����t���O
        l_request.updateFlag = "2";
             
        WEB3AccInfoCampaignInfo l_commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
        
        //�萔�������L�����y�[������ID
        l_commissionCampaignInfo.campaignId = "12";
        //�o�^�^�C�v 
        l_commissionCampaignInfo.registType = "0";
        
        l_request.commissionCampaignInfo = l_commissionCampaignInfo;
        
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
     *�Q�j �X�V�����t���O�� 0 �i�o�^�j�̏ꍇ�A
     *   �Ώۊ���From==null && �Ώۊ���To==null
     *   �����J�݌o�ߊ��ԁi���j�������� && �����J�݌o�ߊ��ԁi���j�������͂̏ꍇ
     */
    public void testValidate_0037()
    {
        final String STR_METHOD_NAME = "testValidate_0037()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenConfirmRequest();
        
        //�X�V�����t���O
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
        
        String[] l_strItemCode = {"12"};
        //���i�R�[�h�z��
        l_commissionCampaignInfo.itemCode = l_strItemCode;
        //�L�����y�[������
        l_commissionCampaignInfo.campaignName = "12";
        //���X�R�[�h
        l_commissionCampaignInfo.branchCode = "123";
        //���҃R�[�h
        l_commissionCampaignInfo.traderCode = "12345";
        //�����J�݌o�ߊ��ԁi���j
        l_commissionCampaignInfo.accopenPassPeriodMonth = null;
        //�����J�݌o�ߊ��ԁi���j
        l_commissionCampaignInfo.accopenPassPeriodDay = null;
        
        l_request.commissionCampaignInfo = l_commissionCampaignInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02713);
            assertEquals("�Ώۊ��Ԗ����̓G���[�B",l_ex.getErrorInfo().getErrorMessage());
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
     *�Q�j �X�V�����t���O�� 0 �i�o�^�j�̏ꍇ�A
     *   �Ώۊ���From==null && �Ώۊ���To==null
     *   �����J�݌o�ߊ��ԁi���j�������� && �����J�݌o�ߊ��ԁi���j�������͂̏ꍇ
     */
    public void testValidate_0038()
    {
        final String STR_METHOD_NAME = "testValidate_0038()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenConfirmRequest();
        
        //�X�V�����t���O
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
        
        String[] l_strItemCode = {"12"};
        //���i�R�[�h�z��
        l_commissionCampaignInfo.itemCode = l_strItemCode;
        //�L�����y�[������
        l_commissionCampaignInfo.campaignName = "12";
        //���X�R�[�h
        l_commissionCampaignInfo.branchCode = "123";
        //���҃R�[�h
        l_commissionCampaignInfo.traderCode = "12345";
        //�����J�݌o�ߊ��ԁi���j
        l_commissionCampaignInfo.accopenPassPeriodMonth = null;
        //�����J�݌o�ߊ��ԁi���j
        //l_commissionCampaignInfo.accopenPassPeriodDay = "23";
        //�����J�ݓ�To
        l_commissionCampaignInfo.targetPeriodTo = WEB3DateUtility.getDate("20060107","yyyyMMdd");
        
        l_commissionCampaignInfo.targetPeriodFrom = WEB3DateUtility.getDate("20060108","yyyyMMdd");
        
        l_request.commissionCampaignInfo = l_commissionCampaignInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02715);
            assertEquals("�Ώۊ��ԃG���[�B",l_ex.getErrorInfo().getErrorMessage());
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
     *�Q�j �X�V�����t���O�� 0 �i�o�^�j�̏ꍇ�A
     *   �Ώۊ���From==null && �Ώۊ���To==null
     *   �����J�ݓ�To!=null�̏ꍇ
     *   �����J�ݓ�To�Ɍ����J�݌o�ߊ���(��)�ƌ����J�݌o�ߊ��ԁi���j�����Z�������t���ߋ����t�̏ꍇ
     *   �������̃`�F�b�N
     *   �������� 0 �` 100 �̐����ȊO�̏ꍇ
     */
    public void testValidate_0039()
    {
        final String STR_METHOD_NAME = "testValidate_0039()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenConfirmRequest();
        
        //�X�V�����t���O
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
        
        String[] l_strItemCode = {"12"};
        //���i�R�[�h�z��
        l_commissionCampaignInfo.itemCode = l_strItemCode;
        //�L�����y�[������
        l_commissionCampaignInfo.campaignName = "12";
        //���X�R�[�h
        l_commissionCampaignInfo.branchCode = "123";
        //���҃R�[�h
        l_commissionCampaignInfo.traderCode = "12345";
        //�����J�݌o�ߊ��ԁi���j
        l_commissionCampaignInfo.accopenPassPeriodMonth = "12";
        //�����J�݌o�ߊ��ԁi���j
        l_commissionCampaignInfo.accopenPassPeriodDay = "31";
        //�����J�ݓ�To
        l_commissionCampaignInfo.accountOpenDateTo = WEB3DateUtility.getDate("20070108","yyyyMMdd");
        //������
        l_commissionCampaignInfo.collectRate = "1.5";
        
        l_commissionCampaignInfo.accountOpenDiv = "1";
        l_request.commissionCampaignInfo = l_commissionCampaignInfo;
        
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02082);
            assertEquals("�������̒l���s���ł��B",l_ex.getErrorInfo().getErrorMessage());
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
     * null
     */
    public void testValidate_0040()
    {
        final String STR_METHOD_NAME = "testValidate_0028()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenConfirmRequest();
        
        //�X�V�����t���O
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
        
        String[] l_strItemCode = {"12"};
        //���i�R�[�h�z��
        l_commissionCampaignInfo.itemCode = l_strItemCode;
        //�L�����y�[������
        l_commissionCampaignInfo.campaignName = "12";
        //���X�R�[�h
        l_commissionCampaignInfo.branchCode = null;
        //���҃R�[�h
        l_commissionCampaignInfo.traderCode = null;
        //�Ώۊ���To
        l_commissionCampaignInfo.targetPeriodTo =  WEB3DateUtility.getDate("20071104","yyyyMMdd");
        //�Ώۊ���From
        l_commissionCampaignInfo.targetPeriodFrom =  WEB3DateUtility.getDate("20070104","yyyyMMdd");
        //������
        l_commissionCampaignInfo.collectRate = "100";
        //�����J�݋敪
        l_commissionCampaignInfo.accountOpenDiv = "3";
        
        l_commissionCampaignInfo.accountOpenDateFrom = WEB3DateUtility.getDate("20071104","yyyyMMdd");
        l_commissionCampaignInfo.accountOpenDateTo = WEB3DateUtility.getDate("20071106","yyyyMMdd");
        
        
        l_request.commissionCampaignInfo = l_commissionCampaignInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02722);
            assertEquals("�o�^�^�C�v��'0'�ȊO�̒l�ł��B",l_ex.getErrorInfo().getErrorMessage());
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
