head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.06.08.38.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5404d9c264d0465;
filename	WEB3AdminAccInfoCampaignAccOpenListRequestTest.java;


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
 * (�Ǘ��҂��q�l���萔����������߰݌����J�ݏ����ꗗظ���)<BR>
 * �Ǘ��҂��q�l���萔����������߰݌����J�ݏ����ꗗظ���<BR>
 * @@author �Ј���
 * @@version 1.0
 */
public class WEB3AdminAccInfoCampaignAccOpenListRequestTest extends TestBaseForMock 
{

    public WEB3AdminAccInfoCampaignAccOpenListRequestTest(String name) 
    {
        super(name);
    }
    
    private WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3AdminAccInfoCampaignAccOpenListRequestTest.class);
    
    /**
     *�P�j �L�����y�[�����̂̃`�F�b�N
     *�L�����y�[������101�o�C�g�ȏ�̏ꍇ
     */
    public void testValidate_0001()
    {
        final String STR_METHOD_NAME = "testValidate_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenListRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenListRequest();
        
        WEB3AccInfoCampaignSearchCondition l_campaignSearchItem = new WEB3AccInfoCampaignSearchCondition();
        
        //�L�����y�[������
        l_campaignSearchItem.campaignName = "1234567890123456789012345678901234567890123456789012" +
                "3456789012345678901234567890123456789012345678901";
        
        l_request.campaignSearchItem = l_campaignSearchItem;
        
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
     *�P�j �L�����y�[�����̂̃`�F�b�N
     *�L�����y�[������101�o�C�g�ȏ�̏ꍇ
     */
    public void testValidate_0002()
    {
        final String STR_METHOD_NAME = "testValidate_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenListRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenListRequest();
        
        WEB3AccInfoCampaignSearchCondition l_campaignSearchItem = new WEB3AccInfoCampaignSearchCondition();
        
        //�L�����y�[������
        l_campaignSearchItem.campaignName = "1234567890123456789012345678901234567890123456789012" +
                "345678901234567890123456789012345678901234567890112";
        
        l_request.campaignSearchItem = l_campaignSearchItem;
        
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
     *�Q�j �������̃`�F�b�N
     * �������� 0 �` 100 �̐����ȊO�̏ꍇ
     */
    public void testValidate_0003()
    {
        final String STR_METHOD_NAME = "testValidate_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenListRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenListRequest();
        
        WEB3AccInfoCampaignSearchCondition l_campaignSearchItem = new WEB3AccInfoCampaignSearchCondition();
        
        //�L�����y�[������
        l_campaignSearchItem.campaignName = "1234567890123456789012345678901234567890123456789012" +
                "34567890123456789012345678901234567890";
        //������
        l_campaignSearchItem.collectRate = "ab";
        
        l_request.campaignSearchItem = l_campaignSearchItem;
        
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
     *�Q�j �������̃`�F�b�N
     * �������� 0 �` 100 �̐����ȊO�̏ꍇ
     */
    public void testValidate_0004()
    {
        final String STR_METHOD_NAME = "testValidate_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenListRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenListRequest();
        
        WEB3AccInfoCampaignSearchCondition l_campaignSearchItem = new WEB3AccInfoCampaignSearchCondition();
        
        //�L�����y�[������
        l_campaignSearchItem.campaignName = null;
        //������
        l_campaignSearchItem.collectRate = "101";
        
        l_request.campaignSearchItem = l_campaignSearchItem;
        
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
     *�Q�j �������̃`�F�b�N
     * �������� 0 �` 100 �̐����ȊO�̏ꍇ
     */
    public void testValidate_0005()
    {
        final String STR_METHOD_NAME = "testValidate_0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenListRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenListRequest();
        
        WEB3AccInfoCampaignSearchCondition l_campaignSearchItem = new WEB3AccInfoCampaignSearchCondition();
        
        //�L�����y�[������
        l_campaignSearchItem.campaignName = null;
        //������
        l_campaignSearchItem.collectRate = "-1";
        
        l_request.campaignSearchItem = l_campaignSearchItem;
        
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
     *�R�j�@@�v���y�[�W�ԍ��`�F�b�N 
     * �����͂̏ꍇ
     */
    public void testValidate_0006()
    {
        final String STR_METHOD_NAME = "testValidate_0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenListRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenListRequest();
        
        WEB3AccInfoCampaignSearchCondition l_campaignSearchItem = new WEB3AccInfoCampaignSearchCondition();
        
        //�L�����y�[������
        l_campaignSearchItem.campaignName = null;
        //������
        l_campaignSearchItem.collectRate = "0";
        
        //�v���y�[�W�ԍ�
        l_request.pageIndex = null;
        //�y�[�W���\���s��
        l_request.pageSize = "1";
        
        WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
        l_sortKey.keyItem = "collectRate";
        l_sortKey.ascDesc = "A";
        
        WEB3AccInfoSortKey sortKeys[] = {l_sortKey};
        
        l_request.sortKeys = sortKeys;
        l_request.campaignSearchItem = l_campaignSearchItem;
        
        try
        {
            l_request.validate();
            assertEquals("1", l_request.pageIndex);
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
     *�R�j�@@�v���y�[�W�ԍ��`�F�b�N 
     * �����ȊO�̕������܂܂��ꍇ
     */
    public void testValidate_0007()
    {
        final String STR_METHOD_NAME = "testValidate_0007()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenListRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenListRequest();
        
        WEB3AccInfoCampaignSearchCondition l_campaignSearchItem = new WEB3AccInfoCampaignSearchCondition();
        
        //�L�����y�[������
        l_campaignSearchItem.campaignName = null;
        //������
        l_campaignSearchItem.collectRate = "100";
        //�v���y�[�W�ԍ�
        l_request.pageIndex = "ab";
        
        l_request.campaignSearchItem = l_campaignSearchItem;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00090);
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
     *�R�j�@@�v���y�[�W�ԍ��`�F�b�N 
     * �}�C�i�X�l�̏ꍇ
     */
    public void testValidate_0008()
    {
        final String STR_METHOD_NAME = "testValidate_0008()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenListRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenListRequest();
        
        WEB3AccInfoCampaignSearchCondition l_campaignSearchItem = new WEB3AccInfoCampaignSearchCondition();
        
        //�L�����y�[������
        l_campaignSearchItem.campaignName = null;
        //������
        l_campaignSearchItem.collectRate = "88";
        //�v���y�[�W�ԍ�
        l_request.pageIndex = "-12";
        
        l_request.campaignSearchItem = l_campaignSearchItem;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00616);
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
     *�S�j�@@�y�[�W���\���s���`�F�b�N 
     * �����͂̏ꍇ
     */
    public void testValidate_0009()
    {
        final String STR_METHOD_NAME = "testValidate_0009()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenListRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenListRequest();
        
        WEB3AccInfoCampaignSearchCondition l_campaignSearchItem = new WEB3AccInfoCampaignSearchCondition();
        
        //�L�����y�[������
        l_campaignSearchItem.campaignName = null;
        //������
        l_campaignSearchItem.collectRate = "88";
        //�v���y�[�W�ԍ�
        l_request.pageIndex = "55";
        //�y�[�W���\���s��
        l_request.pageSize = null;
        
        l_request.campaignSearchItem = l_campaignSearchItem;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02224);
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
     *�S�j�@@�y�[�W���\���s���`�F�b�N 
     * �����ȊO�̕������܂܂��ꍇ
     */
    public void testValidate_0010()
    {
        final String STR_METHOD_NAME = "testValidate_0010()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenListRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenListRequest();
        
        WEB3AccInfoCampaignSearchCondition l_campaignSearchItem = new WEB3AccInfoCampaignSearchCondition();
        
        //�L�����y�[������
        l_campaignSearchItem.campaignName = null;
        //������
        l_campaignSearchItem.collectRate = "88";
        //�v���y�[�W�ԍ�
        l_request.pageIndex = "55";
        //�y�[�W���\���s��
        l_request.pageSize = "ab";
        
        l_request.campaignSearchItem = l_campaignSearchItem;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00092);
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
     *�S�j�@@�y�[�W���\���s���`�F�b�N 
     * �}�C�i�X�l�̏ꍇ
     */
    public void testValidate_0011()
    {
        final String STR_METHOD_NAME = "testValidate_0011()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenListRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenListRequest();
        
        WEB3AccInfoCampaignSearchCondition l_campaignSearchItem = new WEB3AccInfoCampaignSearchCondition();
        
        //�L�����y�[������
        l_campaignSearchItem.campaignName = null;
        //������
        l_campaignSearchItem.collectRate = "88";
        //�v���y�[�W�ԍ�
        l_request.pageIndex = "55";
        //�y�[�W���\���s��
        l_request.pageSize = "-66";
        
        l_request.campaignSearchItem = l_campaignSearchItem;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00617);
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
     *�T�j�@@�\�[�g�L�[�̃`�F�b�N  
     * �\�[�g�L�[��������l�̏ꍇ
     */
    public void testValidate_0012()
    {
        final String STR_METHOD_NAME = "testValidate_0012()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenListRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenListRequest();
        
        WEB3AccInfoCampaignSearchCondition l_campaignSearchItem = new WEB3AccInfoCampaignSearchCondition();
        
        //�L�����y�[������
        l_campaignSearchItem.campaignName = null;
        //������
        l_campaignSearchItem.collectRate = "88";
        //�v���y�[�W�ԍ�
        l_request.pageIndex = "55";
        //�y�[�W���\���s��
        l_request.pageSize = "66";
        
        l_request.campaignSearchItem = l_campaignSearchItem;
        
        WEB3AccInfoSortKey l_sortKeys[] = null;
        
        l_request.sortKeys = l_sortKeys;
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00231);
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
     *�T�j�@@�\�[�g�L�[�̃`�F�b�N  
     * �i�\�[�g�L�[�̗v�f�� == 0�j�̏ꍇ
     */
    public void testValidate_0013()
    {
        final String STR_METHOD_NAME = "testValidate_0013()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenListRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenListRequest();
        
        WEB3AccInfoCampaignSearchCondition l_campaignSearchItem = new WEB3AccInfoCampaignSearchCondition();
        
        //�L�����y�[������
        l_campaignSearchItem.campaignName = null;
        //������
        l_campaignSearchItem.collectRate = "88";
        //�v���y�[�W�ԍ�
        l_request.pageIndex = "55";
        //�y�[�W���\���s��
        l_request.pageSize = "66";
        
        l_request.campaignSearchItem = l_campaignSearchItem;
        
        WEB3AccInfoSortKey l_sortKeys[] = new WEB3AccInfoSortKey[0];
        
        l_request.sortKeys = l_sortKeys;
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00232);
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
     *�T�j�@@�\�[�g�L�[�̃`�F�b�N  
     * �\�[�g�L�[.validate()
     */
    public void testValidate_0014()
    {
        final String STR_METHOD_NAME = "testValidate_0014()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenListRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenListRequest();
        
        WEB3AccInfoCampaignSearchCondition l_campaignSearchItem = new WEB3AccInfoCampaignSearchCondition();
        
        //�L�����y�[������
        l_campaignSearchItem.campaignName = null;
        //������
        l_campaignSearchItem.collectRate = "88";
        //�v���y�[�W�ԍ�
        l_request.pageIndex = "55";
        //�y�[�W���\���s��
        l_request.pageSize = "66";
        
        l_request.campaignSearchItem = l_campaignSearchItem;
        
        WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
        
        l_sortKey.keyItem = "branchCode";
        
        l_sortKey.ascDesc = null;
        
        WEB3AccInfoSortKey l_sortKeys[] = {l_sortKey};
        
        l_request.sortKeys = l_sortKeys;
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00087);
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
     *�T�j�@@�\�[�g�L�[�̃`�F�b�N  
     * �\�[�g�L�[.�L�[���ڂ����L�̍��ږ��ȊO�̏ꍇ
     */
    public void testValidate_0015()
    {
        final String STR_METHOD_NAME = "testValidate_0015()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenListRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenListRequest();
        
        WEB3AccInfoCampaignSearchCondition l_campaignSearchItem = new WEB3AccInfoCampaignSearchCondition();
        
        //�L�����y�[������
        l_campaignSearchItem.campaignName = null;
        //������
        l_campaignSearchItem.collectRate = "88";
        //�v���y�[�W�ԍ�
        l_request.pageIndex = "55";
        //�y�[�W���\���s��
        l_request.pageSize = "66";
        
        l_request.campaignSearchItem = l_campaignSearchItem;
        
        WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
        
        l_sortKey.keyItem = "��������";
        
        l_sortKey.ascDesc = "A";
        
        WEB3AccInfoSortKey l_sortKeys[] = {l_sortKey};
        
        l_request.sortKeys = l_sortKeys;
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00086);
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
     *�T�j�@@�\�[�g�L�[�̃`�F�b�N  
     * �\�[�g�L�[.�L�[���ڂ����L�̍��ږ��ȊO�̏ꍇ
     */
    public void testValidate_0016()
    {
        final String STR_METHOD_NAME = "testValidate_0016()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenListRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenListRequest();
        
        WEB3AccInfoCampaignSearchCondition l_campaignSearchItem = new WEB3AccInfoCampaignSearchCondition();
        
        //�L�����y�[������
        l_campaignSearchItem.campaignName = null;
        //������
        l_campaignSearchItem.collectRate = "88";
        //�v���y�[�W�ԍ�
        l_request.pageIndex = "55";
        //�y�[�W���\���s��
        l_request.pageSize = "66";
        
        l_request.campaignSearchItem = l_campaignSearchItem;
        
        WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
        
        l_sortKey.keyItem = "collectRate";
        
        l_sortKey.ascDesc = "A";
        
        WEB3AccInfoSortKey l_sortKeys[] = {l_sortKey};
        
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
     *�T�j�@@�\�[�g�L�[�̃`�F�b�N  
     * �\�[�g�L�[.�L�[���ڂ����L�̍��ږ��ȊO�̏ꍇ
     */
    public void testValidate_0017()
    {
        final String STR_METHOD_NAME = "testValidate_0017()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenListRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenListRequest();
        
        WEB3AccInfoCampaignSearchCondition l_campaignSearchItem = new WEB3AccInfoCampaignSearchCondition();
        
        //�L�����y�[������
        l_campaignSearchItem.campaignName = null;
        //������
        l_campaignSearchItem.collectRate = "88";
        //�v���y�[�W�ԍ�
        l_request.pageIndex = "55";
        //�y�[�W���\���s��
        l_request.pageSize = "66";
        
        l_request.campaignSearchItem = l_campaignSearchItem;
        
        WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
        
        l_sortKey.keyItem = "branchCode";
        
        l_sortKey.ascDesc = "A";
        
        WEB3AccInfoSortKey l_sortKeys[] = {l_sortKey};
        
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
     *�T�j�@@�\�[�g�L�[�̃`�F�b�N  
     * �\�[�g�L�[.�L�[���ڂ����L�̍��ږ��ȊO�̏ꍇ
     */
    public void testValidate_0018()
    {
        final String STR_METHOD_NAME = "testValidate_0018()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenListRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenListRequest();
        
        WEB3AccInfoCampaignSearchCondition l_campaignSearchItem = new WEB3AccInfoCampaignSearchCondition();
        
        //�L�����y�[������
        l_campaignSearchItem.campaignName = null;
        //������
        l_campaignSearchItem.collectRate = "88";
        //�v���y�[�W�ԍ�
        l_request.pageIndex = "55";
        //�y�[�W���\���s��
        l_request.pageSize = "66";
        
        l_request.campaignSearchItem = l_campaignSearchItem;
        
        WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
        
        l_sortKey.keyItem = "traderCode";
        
        l_sortKey.ascDesc = "A";
        
        WEB3AccInfoSortKey l_sortKeys[] = {l_sortKey};
        
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
     *�T�j�@@�\�[�g�L�[�̃`�F�b�N  
     * �\�[�g�L�[.�L�[���ڂ����L�̍��ږ��ȊO�̏ꍇ
     */
    public void testValidate_0019()
    {
        final String STR_METHOD_NAME = "testValidate_0019()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenListRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenListRequest();
        
        WEB3AccInfoCampaignSearchCondition l_campaignSearchItem = new WEB3AccInfoCampaignSearchCondition();
        
        //�L�����y�[������
        l_campaignSearchItem.campaignName = null;
        //������
        l_campaignSearchItem.collectRate = "88";
        //�v���y�[�W�ԍ�
        l_request.pageIndex = "55";
        //�y�[�W���\���s��
        l_request.pageSize = "66";
        
        l_request.campaignSearchItem = l_campaignSearchItem;
        
        WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
        
        l_sortKey.keyItem = "accountOpenDateFrom";
        
        l_sortKey.ascDesc = "A";
        
        WEB3AccInfoSortKey l_sortKeys[] = {l_sortKey};
        
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
     *�T�j�@@�\�[�g�L�[�̃`�F�b�N  
     * �\�[�g�L�[.�L�[���ڂ����L�̍��ږ��ȊO�̏ꍇ
     */
    public void testValidate_0020()
    {
        final String STR_METHOD_NAME = "testValidate_0020()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenListRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenListRequest();
        
        WEB3AccInfoCampaignSearchCondition l_campaignSearchItem = new WEB3AccInfoCampaignSearchCondition();
        
        //�L�����y�[������
        l_campaignSearchItem.campaignName = null;
        //������
        l_campaignSearchItem.collectRate = "88";
        //�v���y�[�W�ԍ�
        l_request.pageIndex = "55";
        //�y�[�W���\���s��
        l_request.pageSize = "66";
        
        l_request.campaignSearchItem = l_campaignSearchItem;
        
        WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
        
        l_sortKey.keyItem = "accountOpenDateTo";
        
        l_sortKey.ascDesc = "A";
        
        WEB3AccInfoSortKey l_sortKeys[] = {l_sortKey};
        
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
     *�T�j�@@�\�[�g�L�[�̃`�F�b�N  
     * �\�[�g�L�[.�L�[���ڂ����L�̍��ږ��ȊO�̏ꍇ
     */
    public void testValidate_0021()
    {
        final String STR_METHOD_NAME = "testValidate_0021()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenListRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenListRequest();
        
        WEB3AccInfoCampaignSearchCondition l_campaignSearchItem = new WEB3AccInfoCampaignSearchCondition();
        
        //�L�����y�[������
        l_campaignSearchItem.campaignName = null;
        //������
        l_campaignSearchItem.collectRate = "88";
        //�v���y�[�W�ԍ�
        l_request.pageIndex = "55";
        //�y�[�W���\���s��
        l_request.pageSize = "66";
        
        l_request.campaignSearchItem = l_campaignSearchItem;
        
        WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
        
        l_sortKey.keyItem = "registDate";
        
        l_sortKey.ascDesc = "A";
        
        WEB3AccInfoSortKey l_sortKeys[] = {l_sortKey};
        
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
     *�T�j�@@�\�[�g�L�[�̃`�F�b�N  
     * �\�[�g�L�[.�L�[���ڂ����L�̍��ږ��ȊO�̏ꍇ
     */
    public void testValidate_0022()
    {
        final String STR_METHOD_NAME = "testValidate_0022()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenListRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenListRequest();
        
        WEB3AccInfoCampaignSearchCondition l_campaignSearchItem = new WEB3AccInfoCampaignSearchCondition();
        
        //�L�����y�[������
        l_campaignSearchItem.campaignName = null;
        //������
        l_campaignSearchItem.collectRate = "88";
        //�v���y�[�W�ԍ�
        l_request.pageIndex = "55";
        //�y�[�W���\���s��
        l_request.pageSize = "66";
        
        l_request.campaignSearchItem = l_campaignSearchItem;
        
        WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
        
        l_sortKey.keyItem = "updateDate";
        
        l_sortKey.ascDesc = "A";
        
        WEB3AccInfoSortKey l_sortKeys[] = {l_sortKey};
        
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
    public void testValidate_0023()
    {
        final String STR_METHOD_NAME = "testValidate_0023()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenListRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenListRequest();
        
        WEB3AccInfoCampaignSearchCondition l_campaignSearchItem = new WEB3AccInfoCampaignSearchCondition();
        
        //�L�����y�[������
        l_campaignSearchItem.campaignName = null;
        //������
        l_campaignSearchItem.collectRate = null;
        //�v���y�[�W�ԍ�
        l_request.pageIndex = "55";
        //�y�[�W���\���s��
        l_request.pageSize = "66";
        
        l_request.campaignSearchItem = l_campaignSearchItem;
        
        WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
        
        l_sortKey.keyItem = "updateDate";
        
        l_sortKey.ascDesc = "A";
        
        WEB3AccInfoSortKey l_sortKeys[] = {l_sortKey};
        
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
