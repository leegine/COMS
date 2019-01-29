head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.06.08.39.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5404d9c264d0465;
filename	WEB3AdminAccInfoCampaignIndiviListRequestTest.java;


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
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��҂��q�l���萔����������߰݌ʌڋq�w��ꗗظ���Test)<BR>
 * �Ǘ��҂��q�l���萔����������߰݌ʌڋq�w��ꗗظ���Test<BR>
 * @@author �Ј���
 * @@version 1.0
 */
public class WEB3AdminAccInfoCampaignIndiviListRequestTest extends TestBaseForMock 
{
    public WEB3AdminAccInfoCampaignIndiviListRequestTest(String name) 
    {
        super(name);
    }
    
    private WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3AdminAccInfoCampaignIndiviListRequestTest.class);
    
    /**
     *�P�j�@@���X�R�[�h�̃`�F�b�N   
     *�R���ȊO�̏ꍇ
     */
    public void testValidate_0001()
    {
        final String STR_METHOD_NAME = "testValidate_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AccInfoCampaignSearchCondition l_campaignSearchItem = new WEB3AccInfoCampaignSearchCondition();
        
        //���X�R�[�h
        l_campaignSearchItem.branchCode = "1234";
        
        WEB3AdminAccInfoCampaignIndiviListRequest l_request = new  WEB3AdminAccInfoCampaignIndiviListRequest();
        
        l_request.campaignSearchItem = l_campaignSearchItem;
        
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
     *�P�j�@@���X�R�[�h�̃`�F�b�N   
     *�R���ȊO�̏ꍇ
     */
    public void testValidate_0002()
    {
        final String STR_METHOD_NAME = "testValidate_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AccInfoCampaignSearchCondition l_campaignSearchItem = new WEB3AccInfoCampaignSearchCondition();
        
        //���X�R�[�h
        l_campaignSearchItem.branchCode = "12";
        
        WEB3AdminAccInfoCampaignIndiviListRequest l_request = new  WEB3AdminAccInfoCampaignIndiviListRequest();
        l_request.campaignSearchItem = l_campaignSearchItem;
        
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
     * �Q�j�@@�ڋq�R�[�h�̃`�F�b�N
     * �V���ȏ�̏ꍇ
     */
    public void testValidate_0003()
    {
        final String STR_METHOD_NAME = "testValidate_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AccInfoCampaignSearchCondition l_campaignSearchItem = new WEB3AccInfoCampaignSearchCondition();
        
        //���X�R�[�h
        l_campaignSearchItem.branchCode = "123";
        //�ڋq�R�[�h
        l_campaignSearchItem.accountCode = "1234567";
        
        WEB3AdminAccInfoCampaignIndiviListRequest l_request = new  WEB3AdminAccInfoCampaignIndiviListRequest();
        
        l_request.campaignSearchItem = l_campaignSearchItem;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00836,l_ex.getErrorInfo());
            assertEquals("�ڋq�R�[�h�̃T�C�Y���s���ł��B",l_ex.getErrorInfo().getErrorMessage());
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
     * �Q�j�@@�ڋq�R�[�h�̃`�F�b�N
     * �V���ȏ�̏ꍇ
     */
    public void testValidate_0004()
    {
        final String STR_METHOD_NAME = "testValidate_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AccInfoCampaignSearchCondition l_campaignSearchItem = new WEB3AccInfoCampaignSearchCondition();
        
        //���X�R�[�h
        l_campaignSearchItem.branchCode = "123";
        //�ڋq�R�[�h
        l_campaignSearchItem.accountCode = "12345678";
        
        WEB3AdminAccInfoCampaignIndiviListRequest l_request = new  WEB3AdminAccInfoCampaignIndiviListRequest();
        
        l_request.campaignSearchItem = l_campaignSearchItem;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00836,l_ex.getErrorInfo());
            assertEquals("�ڋq�R�[�h�̃T�C�Y���s���ł��B",l_ex.getErrorInfo().getErrorMessage());
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
     * �R�j�@@�L�����y�[�����̂̃`�F�b�N
     * 101�o�C�g�ȏ�̏ꍇ
     */
    public void testValidate_0005()
    {
        final String STR_METHOD_NAME = "testValidate_0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AccInfoCampaignSearchCondition l_campaignSearchItem = new WEB3AccInfoCampaignSearchCondition();
        
        //���X�R�[�h
        l_campaignSearchItem.branchCode = "123";
        //�ڋq�R�[�h
        l_campaignSearchItem.accountCode = "123456";
        //�L�����y�[������
        l_campaignSearchItem.campaignName = "1234567890123456789012345678901234567890" +
                "123456789012345678901234567890123456789012345678901234567890123";
        
        WEB3AdminAccInfoCampaignIndiviListRequest l_request = new  WEB3AdminAccInfoCampaignIndiviListRequest();
        
        l_request.campaignSearchItem = l_campaignSearchItem;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02709,l_ex.getErrorInfo());
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
     * �R�j�@@�L�����y�[�����̂̃`�F�b�N
     * 101�o�C�g�ȏ�̏ꍇ
     */
    public void testValidate_0007()
    {
        final String STR_METHOD_NAME = "testValidate_0007()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AccInfoCampaignSearchCondition l_campaignSearchItem = new WEB3AccInfoCampaignSearchCondition();
        
        //���X�R�[�h
        l_campaignSearchItem.branchCode = "123";
        //�ڋq�R�[�h
        l_campaignSearchItem.accountCode = "123456";
        //�L�����y�[������
        l_campaignSearchItem.campaignName = "1234567890123456789012345678901234567890" +
                "1234567890123456789012345678901234567890123456789012345678901";
        
        WEB3AdminAccInfoCampaignIndiviListRequest l_request = new  WEB3AdminAccInfoCampaignIndiviListRequest();
        
        l_request.campaignSearchItem = l_campaignSearchItem;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02709,l_ex.getErrorInfo());
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
     * �S�j�@@�������̃`�F�b�N
     * 0�`100�̐����ȊO�̏ꍇ
     */
    public void testValidate_0008()
    {
        final String STR_METHOD_NAME = "testValidate_0008()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AccInfoCampaignSearchCondition l_campaignSearchItem = new WEB3AccInfoCampaignSearchCondition();
        
        //���X�R�[�h
        l_campaignSearchItem.branchCode = "123";
        //�ڋq�R�[�h
        l_campaignSearchItem.accountCode = "123456";
        //�L�����y�[������
        l_campaignSearchItem.campaignName = null;
        //������
        l_campaignSearchItem.collectRate = "ab";
        
        WEB3AdminAccInfoCampaignIndiviListRequest l_request = new  WEB3AdminAccInfoCampaignIndiviListRequest();
        
        l_request.campaignSearchItem = l_campaignSearchItem;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02082,l_ex.getErrorInfo());
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
     * �S�j�@@�������̃`�F�b�N
     * 0�`100�̐����ȊO�̏ꍇ
     */
    public void testValidate_0009()
    {
        final String STR_METHOD_NAME = "testValidate_0009()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AccInfoCampaignSearchCondition l_campaignSearchItem = new WEB3AccInfoCampaignSearchCondition();
        
        //���X�R�[�h
        l_campaignSearchItem.branchCode = "123";
        //�ڋq�R�[�h
        l_campaignSearchItem.accountCode = "123456";
        //�L�����y�[������
        l_campaignSearchItem.campaignName = "1234567890123456789012345678901234567890" +
                "12345678901234567890123456789012345678901234567890123456789";
        //������
        l_campaignSearchItem.collectRate = "-1";
        
        WEB3AdminAccInfoCampaignIndiviListRequest l_request = new  WEB3AdminAccInfoCampaignIndiviListRequest();
        
        l_request.campaignSearchItem = l_campaignSearchItem;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02082,l_ex.getErrorInfo());
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
     * �S�j�@@�������̃`�F�b�N
     * 0�`100�̐����ȊO�̏ꍇ
     */
    public void testValidate_0010()
    {
        final String STR_METHOD_NAME = "testValidate_0010()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AccInfoCampaignSearchCondition l_campaignSearchItem = new WEB3AccInfoCampaignSearchCondition();
        
        //���X�R�[�h
        l_campaignSearchItem.branchCode = "123";
        //�ڋq�R�[�h
        l_campaignSearchItem.accountCode = "123456";
        //�L�����y�[������
        l_campaignSearchItem.campaignName = "1234567890123456789012345678901234567890" +
                "12345678901234567890123456789012345678901234567890123456789";
        //������
        l_campaignSearchItem.collectRate = "101";
        
        WEB3AdminAccInfoCampaignIndiviListRequest l_request = new  WEB3AdminAccInfoCampaignIndiviListRequest();
        
        l_request.campaignSearchItem = l_campaignSearchItem;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02082,l_ex.getErrorInfo());
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
     * �T�j�@@�v���y�[�W�ԍ��`�F�b�N 
     * �����͂̏ꍇ
     */
    public void testValidate_0011()
    {
        final String STR_METHOD_NAME = "testValidate_0011()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AccInfoCampaignSearchCondition l_campaignSearchItem = new WEB3AccInfoCampaignSearchCondition();
        
        //���X�R�[�h
        l_campaignSearchItem.branchCode = "123";
        //�ڋq�R�[�h
        l_campaignSearchItem.accountCode = "123456";
        //�L�����y�[������
        l_campaignSearchItem.campaignName = "1234567890123456789012345678901234567890" +
                "12345678901234567890123456789012345678901234567890123456789";
        //������
        l_campaignSearchItem.collectRate = "0";
        
        WEB3AdminAccInfoCampaignIndiviListRequest l_request = new  WEB3AdminAccInfoCampaignIndiviListRequest();
        
        l_request.campaignSearchItem = l_campaignSearchItem;
        
        //�v���y�[�W�ԍ�
        l_request.pageIndex = null;
        //�y�[�W���\���s��
        l_request.pageSize = "12";

        WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
        
        l_sortKey.keyItem = "branchCode";
        l_sortKey.ascDesc = "A";
        
        WEB3AccInfoSortKey l_sortKeys[] = {l_sortKey};
        
        // �\�[�g�L�[
        l_request.sortKeys = l_sortKeys;
        
        try
        {
            l_request.validate();
            assertEquals("1",l_request.pageIndex);
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
     * �T�j�@@�v���y�[�W�ԍ��`�F�b�N 
     * �����ȊO�̕������܂܂��ꍇ
     */
    public void testValidate_0012()
    {
        final String STR_METHOD_NAME = "testValidate_0012()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AccInfoCampaignSearchCondition l_campaignSearchItem = new WEB3AccInfoCampaignSearchCondition();
        
        //���X�R�[�h
        l_campaignSearchItem.branchCode = "123";
        //�ڋq�R�[�h
        l_campaignSearchItem.accountCode = "123456";
        //�L�����y�[������
        l_campaignSearchItem.campaignName = "1234567890123456789012345678901234567890" +
                "12345678901234567890123456789012345678901234567890123456789";
        //������
        l_campaignSearchItem.collectRate = "100";
        
        WEB3AdminAccInfoCampaignIndiviListRequest l_request = new  WEB3AdminAccInfoCampaignIndiviListRequest();
        
        l_request.campaignSearchItem = l_campaignSearchItem;
        
        //�v���y�[�W�ԍ�
        l_request.pageIndex = "ab";
        //�y�[�W���\���s��
        l_request.pageSize = "12";

        WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
        
        l_sortKey.keyItem = "branchCode";
        l_sortKey.ascDesc = "A";
        
        WEB3AccInfoSortKey l_sortKeys[] = {l_sortKey};
        
        // �\�[�g�L�[
        l_request.sortKeys = l_sortKeys;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00090,l_ex.getErrorInfo());
            assertEquals("�v���y�[�W�ԍ��������ȊO�̒l�ł��B",l_ex.getErrorInfo().getErrorMessage());
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
     * �T�j�@@�v���y�[�W�ԍ��`�F�b�N 
     * �}�C�i�X�l�̏ꍇ
     */
    public void testValidate_0013()
    {
        final String STR_METHOD_NAME = "testValidate_0013()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AccInfoCampaignSearchCondition l_campaignSearchItem = new WEB3AccInfoCampaignSearchCondition();
        
        //���X�R�[�h
        l_campaignSearchItem.branchCode = "123";
        //�ڋq�R�[�h
        l_campaignSearchItem.accountCode = "123456";
        //�L�����y�[������
        l_campaignSearchItem.campaignName = "1234567890123456789012345678901234567890" +
                "12345678901234567890123456789012345678901234567890123456789";
        //������
        l_campaignSearchItem.collectRate = "50";
        
        WEB3AdminAccInfoCampaignIndiviListRequest l_request = new  WEB3AdminAccInfoCampaignIndiviListRequest();
        
        l_request.campaignSearchItem = l_campaignSearchItem;
        
        //�v���y�[�W�ԍ�
        l_request.pageIndex = "-100";
        //�y�[�W���\���s��
        l_request.pageSize = "12";

        WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
        
        l_sortKey.keyItem = "branchCode";
        l_sortKey.ascDesc = "A";
        
        WEB3AccInfoSortKey l_sortKeys[] = {l_sortKey};
        
        // �\�[�g�L�[
        l_request.sortKeys = l_sortKeys;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00616,l_ex.getErrorInfo());
            assertEquals("�v���y�[�W�ԍ��̒l��0�ȉ��ł��B",l_ex.getErrorInfo().getErrorMessage());
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
     * �U�j�@@�y�[�W���\���s���`�F�b�N 
     * �����͂̏ꍇ
     */
    public void testValidate_0014()
    {
        final String STR_METHOD_NAME = "testValidate_0014()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AccInfoCampaignSearchCondition l_campaignSearchItem = new WEB3AccInfoCampaignSearchCondition();
        
        //���X�R�[�h
        l_campaignSearchItem.branchCode = "123";
        //�ڋq�R�[�h
        l_campaignSearchItem.accountCode = "123456";
        //�L�����y�[������
        l_campaignSearchItem.campaignName = "1234567890123456789012345678901234567890" +
                "12345678901234567890123456789012345678901234567890123456789";
        //������
        l_campaignSearchItem.collectRate = "50";
        
        WEB3AdminAccInfoCampaignIndiviListRequest l_request = new  WEB3AdminAccInfoCampaignIndiviListRequest();
        
        l_request.campaignSearchItem = l_campaignSearchItem;
        
        //�v���y�[�W�ԍ�
        l_request.pageIndex = "123";
        //�y�[�W���\���s��
        l_request.pageSize = null;

        WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
        
        l_sortKey.keyItem = "branchCode";
        l_sortKey.ascDesc = "A";
        
        WEB3AccInfoSortKey l_sortKeys[] = {l_sortKey};
        
        // �\�[�g�L�[
        l_request.sortKeys = l_sortKeys;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02224,l_ex.getErrorInfo());
            assertEquals("�y�[�W���\���s���������͂ł��B",l_ex.getErrorInfo().getErrorMessage());
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
     * �U�j�@@�y�[�W���\���s���`�F�b�N 
     * �y�[�W�������ȊO�̕������܂܂��ꍇ
     */
    public void testValidate_0015()
    {
        final String STR_METHOD_NAME = "testValidate_0015()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AccInfoCampaignSearchCondition l_campaignSearchItem = new WEB3AccInfoCampaignSearchCondition();
        
        //���X�R�[�h
        l_campaignSearchItem.branchCode = "123";
        //�ڋq�R�[�h
        l_campaignSearchItem.accountCode = "123456";
        //�L�����y�[������
        l_campaignSearchItem.campaignName = "1234567890123456789012345678901234567890" +
                "12345678901234567890123456789012345678901234567890123456789";
        //������
        l_campaignSearchItem.collectRate = "50";
        
        WEB3AdminAccInfoCampaignIndiviListRequest l_request = new  WEB3AdminAccInfoCampaignIndiviListRequest();
        
        l_request.campaignSearchItem = l_campaignSearchItem;
        
        //�v���y�[�W�ԍ�
        l_request.pageIndex = "123";
        //�y�[�W���\���s��
        l_request.pageSize = "ab";

        WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
        
        l_sortKey.keyItem = "branchCode";
        l_sortKey.ascDesc = "A";
        
        WEB3AccInfoSortKey l_sortKeys[] = {l_sortKey};
        
        // �\�[�g�L�[
        l_request.sortKeys = l_sortKeys;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00092,l_ex.getErrorInfo());
            assertEquals("�y�[�W���\���s���������ȊO�̒l�ł��B",l_ex.getErrorInfo().getErrorMessage());
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
     * �U�j�@@�y�[�W���\���s���`�F�b�N 
     * �}�C�i�X�l�̏ꍇ
     */
    public void testValidate_0016()
    {
        final String STR_METHOD_NAME = "testValidate_0016()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AccInfoCampaignSearchCondition l_campaignSearchItem = new WEB3AccInfoCampaignSearchCondition();
        
        //���X�R�[�h
        l_campaignSearchItem.branchCode = "123";
        //�ڋq�R�[�h
        l_campaignSearchItem.accountCode = "123456";
        //�L�����y�[������
        l_campaignSearchItem.campaignName = "1234567890123456789012345678901234567890" +
                "12345678901234567890123456789012345678901234567890123456789";
        //������
        l_campaignSearchItem.collectRate = "50";
        
        WEB3AdminAccInfoCampaignIndiviListRequest l_request = new  WEB3AdminAccInfoCampaignIndiviListRequest();
        
        l_request.campaignSearchItem = l_campaignSearchItem;
        
        //�v���y�[�W�ԍ�
        l_request.pageIndex = "123";
        //�y�[�W���\���s��
        l_request.pageSize = "-100";

        WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
        
        l_sortKey.keyItem = "branchCode";
        l_sortKey.ascDesc = "A";
        
        WEB3AccInfoSortKey l_sortKeys[] = {l_sortKey};
        
        // �\�[�g�L�[
        l_request.sortKeys = l_sortKeys;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00617,l_ex.getErrorInfo());
            assertEquals("�y�[�W���\���s���̒l��0�ȉ��ł��B",l_ex.getErrorInfo().getErrorMessage());
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
     * �V�j�@@�\�[�g�L�[�̃`�F�b�N
     * ������l�̏ꍇ
     */
    public void testValidate_0017()
    {
        final String STR_METHOD_NAME = "testValidate_0017()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AccInfoCampaignSearchCondition l_campaignSearchItem = new WEB3AccInfoCampaignSearchCondition();
        
        //���X�R�[�h
        l_campaignSearchItem.branchCode = "123";
        //�ڋq�R�[�h
        l_campaignSearchItem.accountCode = "123456";
        //�L�����y�[������
        l_campaignSearchItem.campaignName = "1234567890123456789012345678901234567890" +
                "12345678901234567890123456789012345678901234567890123456789";
        //������
        l_campaignSearchItem.collectRate = "50";
        
        WEB3AdminAccInfoCampaignIndiviListRequest l_request = new  WEB3AdminAccInfoCampaignIndiviListRequest();
        
        l_request.campaignSearchItem = l_campaignSearchItem;
        
        //�v���y�[�W�ԍ�
        l_request.pageIndex = "123";
        //�y�[�W���\���s��
        l_request.pageSize = "25";

        WEB3AccInfoSortKey l_sortKeys[] = null;
        
        // �\�[�g�L�[
        l_request.sortKeys = l_sortKeys;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00231,l_ex.getErrorInfo());
            assertEquals("�\�[�g�L�[�����w��ł��B",l_ex.getErrorInfo().getErrorMessage());
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
     * �V�j�@@�\�[�g�L�[�̃`�F�b�N
     * �i�\�[�g�L�[�̗v�f�� == 0�j�̏ꍇ
     */
    public void testValidate_0018()
    {
        final String STR_METHOD_NAME = "testValidate_0018()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AccInfoCampaignSearchCondition l_campaignSearchItem = new WEB3AccInfoCampaignSearchCondition();
        
        //���X�R�[�h
        l_campaignSearchItem.branchCode = "123";
        //�ڋq�R�[�h
        l_campaignSearchItem.accountCode = "123456";
        //�L�����y�[������
        l_campaignSearchItem.campaignName = "1234567890123456789012345678901234567890" +
                "12345678901234567890123456789012345678901234567890123456789";
        //������
        l_campaignSearchItem.collectRate = "50";
        
        WEB3AdminAccInfoCampaignIndiviListRequest l_request = new  WEB3AdminAccInfoCampaignIndiviListRequest();
        
        l_request.campaignSearchItem = l_campaignSearchItem;
        
        //�v���y�[�W�ԍ�
        l_request.pageIndex = "123";
        //�y�[�W���\���s��
        l_request.pageSize = "25";

        WEB3AccInfoSortKey l_sortKeys[] = new WEB3AccInfoSortKey[0];
        
        // �\�[�g�L�[
        l_request.sortKeys = l_sortKeys;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00232,l_ex.getErrorInfo());
            assertEquals("�\�[�g�L�[�̗v�f�����O�ł��B",l_ex.getErrorInfo().getErrorMessage());
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
     * �V�j�@@�\�[�g�L�[�̃`�F�b�N
     * �\�[�g�L�[.validate()���R�[������B 
     */
    public void testValidate_0019()
    {
        final String STR_METHOD_NAME = "testValidate_0019()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AccInfoCampaignSearchCondition l_campaignSearchItem = new WEB3AccInfoCampaignSearchCondition();
        
        //���X�R�[�h
        l_campaignSearchItem.branchCode = "123";
        //�ڋq�R�[�h
        l_campaignSearchItem.accountCode = "123456";
        //�L�����y�[������
        l_campaignSearchItem.campaignName = "1234567890123456789012345678901234567890" +
                "12345678901234567890123456789012345678901234567890123456789";
        //������
        l_campaignSearchItem.collectRate = "50";
        
        WEB3AdminAccInfoCampaignIndiviListRequest l_request = new  WEB3AdminAccInfoCampaignIndiviListRequest();
        
        l_request.campaignSearchItem = l_campaignSearchItem;
        
        //�v���y�[�W�ԍ�
        l_request.pageIndex = "123";
        //�y�[�W���\���s��
        l_request.pageSize = "25";

        WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
      
        l_sortKey.keyItem = "branchCode";
        
        l_sortKey.ascDesc = null;
        
        WEB3AccInfoSortKey l_sortKeys[] = {l_sortKey};
        
        // �\�[�g�L�[
        l_request.sortKeys = l_sortKeys;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00087,l_ex.getErrorInfo());
            assertEquals("�����^�~�������w��ł��B",l_ex.getErrorInfo().getErrorMessage());
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
     * �V�j�@@�\�[�g�L�[�̃`�F�b�N
     * �\�[�g�L�[.�L�[���ڂ����L�̍��ږ��ȊO�̏ꍇ
     */
    public void testValidate_0020()
    {
        final String STR_METHOD_NAME = "testValidate_0020()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AccInfoCampaignSearchCondition l_campaignSearchItem = new WEB3AccInfoCampaignSearchCondition();
        
        //���X�R�[�h
        l_campaignSearchItem.branchCode = "123";
        //�ڋq�R�[�h
        l_campaignSearchItem.accountCode = "123456";
        //�L�����y�[������
        l_campaignSearchItem.campaignName = "1234567890123456789012345678901234567890" +
                "12345678901234567890123456789012345678901234567890123456789";
        //������
        l_campaignSearchItem.collectRate = "50";
        
        WEB3AdminAccInfoCampaignIndiviListRequest l_request = new  WEB3AdminAccInfoCampaignIndiviListRequest();
        
        l_request.campaignSearchItem = l_campaignSearchItem;
        
        //�v���y�[�W�ԍ�
        l_request.pageIndex = "123";
        //�y�[�W���\���s��
        l_request.pageSize = "25";

        WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
      
        l_sortKey.keyItem = "��������";
        
        l_sortKey.ascDesc = "A";
        
        WEB3AccInfoSortKey l_sortKeys[] = {l_sortKey};
        // �\�[�g�L�[
        l_request.sortKeys = l_sortKeys;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00086,l_ex.getErrorInfo());
            assertEquals("�\�[�g�L�[�̃L�[���ڂ̒l�����݂��Ȃ��R�[�h�l�ł��B",l_ex.getErrorInfo().getErrorMessage());
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
     * �V�j�@@�\�[�g�L�[�̃`�F�b�N
     * �\�[�g�L�[.�L�[���ڂ����L�̍��ږ��ȊO�̏ꍇ
     */
    public void testValidate_0021()
    {
        final String STR_METHOD_NAME = "testValidate_0021()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AccInfoCampaignSearchCondition l_campaignSearchItem = new WEB3AccInfoCampaignSearchCondition();
        
        //���X�R�[�h
        l_campaignSearchItem.branchCode = "123";
        //�ڋq�R�[�h
        l_campaignSearchItem.accountCode = "123456";
        //�L�����y�[������
        l_campaignSearchItem.campaignName = "1234567890123456789012345678901234567890" +
                "12345678901234567890123456789012345678901234567890123456789";
        //������
        l_campaignSearchItem.collectRate = "50";
        
        WEB3AdminAccInfoCampaignIndiviListRequest l_request = new  WEB3AdminAccInfoCampaignIndiviListRequest();
        
        l_request.campaignSearchItem = l_campaignSearchItem;
        
        //�v���y�[�W�ԍ�
        l_request.pageIndex = "123";
        //�y�[�W���\���s��
        l_request.pageSize = "25";
        
        WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
        l_sortKey.keyItem = "branchCode";
        l_sortKey.ascDesc = "A";

        WEB3AccInfoSortKey[] l_sortKeys = {l_sortKey};
        // �\�[�g�L�[
        l_request.sortKeys = l_sortKeys;
        
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
     * �V�j�@@�\�[�g�L�[�̃`�F�b�N
     * �\�[�g�L�[.�L�[���ڂ����L�̍��ږ��ȊO�̏ꍇ
     */
    public void testValidate_0022()
    {
        final String STR_METHOD_NAME = "testValidate_0022()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AccInfoCampaignSearchCondition l_campaignSearchItem = new WEB3AccInfoCampaignSearchCondition();
        
        //���X�R�[�h
        l_campaignSearchItem.branchCode = "123";
        //�ڋq�R�[�h
        l_campaignSearchItem.accountCode = "123456";
        //�L�����y�[������
        l_campaignSearchItem.campaignName = "1234567890123456789012345678901234567890" +
                "12345678901234567890123456789012345678901234567890123456789";
        //������
        l_campaignSearchItem.collectRate = "50";
        
        WEB3AdminAccInfoCampaignIndiviListRequest l_request = new  WEB3AdminAccInfoCampaignIndiviListRequest();
        
        l_request.campaignSearchItem = l_campaignSearchItem;
        
        //�v���y�[�W�ԍ�
        l_request.pageIndex = "123";
        //�y�[�W���\���s��
        l_request.pageSize = "25";
        
        WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
        l_sortKey.keyItem = "accountCode";
        l_sortKey.ascDesc = "A";
        
        WEB3AccInfoSortKey[] l_sortKeys = {l_sortKey};
        
        // �\�[�g�L�[
        l_request.sortKeys = l_sortKeys;
        
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
     * �V�j�@@�\�[�g�L�[�̃`�F�b�N
     * �\�[�g�L�[.�L�[���ڂ����L�̍��ږ��ȊO�̏ꍇ
     */
    public void testValidate_0023()
    {
        final String STR_METHOD_NAME = "testValidate_0023()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AccInfoCampaignSearchCondition l_campaignSearchItem = new WEB3AccInfoCampaignSearchCondition();
        
        //���X�R�[�h
        l_campaignSearchItem.branchCode = "123";
        //�ڋq�R�[�h
        l_campaignSearchItem.accountCode = "123456";
        //�L�����y�[������
        l_campaignSearchItem.campaignName = "1234567890123456789012345678901234567890" +
                "12345678901234567890123456789012345678901234567890123456789";
        //������
        l_campaignSearchItem.collectRate = "50";
        
        WEB3AdminAccInfoCampaignIndiviListRequest l_request = new  WEB3AdminAccInfoCampaignIndiviListRequest();
        
        l_request.campaignSearchItem = l_campaignSearchItem;
        
        //�v���y�[�W�ԍ�
        l_request.pageIndex = "123";
        //�y�[�W���\���s��
        l_request.pageSize = "25";
        
        WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
        l_sortKey.keyItem = "commProductCode";
        l_sortKey.ascDesc = "A";
        
        WEB3AccInfoSortKey[] l_sortKeys = {l_sortKey};
        
        // �\�[�g�L�[
        l_request.sortKeys = l_sortKeys;
        
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
     * �V�j�@@�\�[�g�L�[�̃`�F�b�N
     * �\�[�g�L�[.�L�[���ڂ����L�̍��ږ��ȊO�̏ꍇ
     */
    public void testValidate_0024()
    {
        final String STR_METHOD_NAME = "testValidate_0024()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AccInfoCampaignSearchCondition l_campaignSearchItem = new WEB3AccInfoCampaignSearchCondition();
        
        //���X�R�[�h
        l_campaignSearchItem.branchCode = "123";
        //�ڋq�R�[�h
        l_campaignSearchItem.accountCode = "123456";
        //�L�����y�[������
        l_campaignSearchItem.campaignName = "1234567890123456789012345678901234567890" +
                "12345678901234567890123456789012345678901234567890123456789";
        //������
        l_campaignSearchItem.collectRate = "50";
        
        WEB3AdminAccInfoCampaignIndiviListRequest l_request = new  WEB3AdminAccInfoCampaignIndiviListRequest();
        
        l_request.campaignSearchItem = l_campaignSearchItem;
        
        //�v���y�[�W�ԍ�
        l_request.pageIndex = "123";
        //�y�[�W���\���s��
        l_request.pageSize = "25";
        
        WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
        l_sortKey.keyItem = "collectRate";
        l_sortKey.ascDesc = "A";
        
        WEB3AccInfoSortKey[] l_sortKeys = {l_sortKey};

        // �\�[�g�L�[
        l_request.sortKeys = l_sortKeys;
        
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
     * �V�j�@@�\�[�g�L�[�̃`�F�b�N
     * �\�[�g�L�[.�L�[���ڂ����L�̍��ږ��ȊO�̏ꍇ
     */
    public void testValidate_0025()
    {
        final String STR_METHOD_NAME = "testValidate_0025()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AccInfoCampaignSearchCondition l_campaignSearchItem = new WEB3AccInfoCampaignSearchCondition();
        
        //���X�R�[�h
        l_campaignSearchItem.branchCode = "123";
        //�ڋq�R�[�h
        l_campaignSearchItem.accountCode = "123456";
        //�L�����y�[������
        l_campaignSearchItem.campaignName = "1234567890123456789012345678901234567890" +
                "12345678901234567890123456789012345678901234567890123456789";
        //������
        l_campaignSearchItem.collectRate = "50";
        
        WEB3AdminAccInfoCampaignIndiviListRequest l_request = new  WEB3AdminAccInfoCampaignIndiviListRequest();
        
        l_request.campaignSearchItem = l_campaignSearchItem;
        
        //�v���y�[�W�ԍ�
        l_request.pageIndex = "123";
        //�y�[�W���\���s��
        l_request.pageSize = "25";
        
        WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
        l_sortKey.keyItem = "registDate";
        l_sortKey.ascDesc = "A";
        
        WEB3AccInfoSortKey[] l_sortKeys = {l_sortKey};
        
        // �\�[�g�L�[
        l_request.sortKeys = l_sortKeys;
        
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
     * �V�j�@@�\�[�g�L�[�̃`�F�b�N
     * �\�[�g�L�[.�L�[���ڂ����L�̍��ږ��ȊO�̏ꍇ
     */
    public void testValidate_0026()
    {
        final String STR_METHOD_NAME = "testValidate_0026()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AccInfoCampaignSearchCondition l_campaignSearchItem = new WEB3AccInfoCampaignSearchCondition();
        
        //���X�R�[�h
        l_campaignSearchItem.branchCode = "123";
        //�ڋq�R�[�h
        l_campaignSearchItem.accountCode = "123456";
        //�L�����y�[������
        l_campaignSearchItem.campaignName = "1234567890123456789012345678901234567890" +
                "12345678901234567890123456789012345678901234567890123456789";
        //������
        l_campaignSearchItem.collectRate = "50";
        
        WEB3AdminAccInfoCampaignIndiviListRequest l_request = new  WEB3AdminAccInfoCampaignIndiviListRequest();
        
        l_request.campaignSearchItem = l_campaignSearchItem;
        
        //�v���y�[�W�ԍ�
        l_request.pageIndex = "123";
        //�y�[�W���\���s��
        l_request.pageSize = "25";
        
        WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
        l_sortKey.keyItem = "updateDate";
        l_sortKey.ascDesc = "A";

        WEB3AccInfoSortKey[] l_sortKeys = {l_sortKey};
        // �\�[�g�L�[
        l_request.sortKeys = l_sortKeys;
        
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
     * null
     */
    public void testValidate_0027()
    {
        final String STR_METHOD_NAME = "testValidate_0026()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AccInfoCampaignSearchCondition l_campaignSearchItem = new WEB3AccInfoCampaignSearchCondition();
        
        //���X�R�[�h
        l_campaignSearchItem.branchCode = null;
        //�ڋq�R�[�h
        l_campaignSearchItem.accountCode = null;
        //�L�����y�[������
        l_campaignSearchItem.campaignName = null;
        //������
        l_campaignSearchItem.collectRate = null;
        
        WEB3AdminAccInfoCampaignIndiviListRequest l_request = new  WEB3AdminAccInfoCampaignIndiviListRequest();
        
        l_request.campaignSearchItem = l_campaignSearchItem;
        
        //�v���y�[�W�ԍ�
        l_request.pageIndex = "123";
        //�y�[�W���\���s��
        l_request.pageSize = "25";
        
        WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
        l_sortKey.keyItem = "updateDate";
        l_sortKey.ascDesc = "A";

        WEB3AccInfoSortKey[] l_sortKeys = {l_sortKey};
        // �\�[�g�L�[
        l_request.sortKeys = l_sortKeys;
        
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
