head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.07.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminTraderAdminLoginCountListRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁEIP�ʃ��O�C���񐔈ꗗ�������ʃ��N�G�X�g(WEB3AdminTraderAdminLoginCountListRequestTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/09/23 �И��� (���u) �V�K�쐬 ���f��No.005
*/
package webbroker3.trademanagement.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminTraderAdminLoginCountListRequestTest extends TestBaseForMock
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTraderAdminLoginCountListRequestTest.class);
    
    WEB3AdminTraderAdminLoginCountListRequest l_request = new WEB3AdminTraderAdminLoginCountListRequest();
    
    public WEB3AdminTraderAdminLoginCountListRequestTest(String name)
    {
        super(name);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    /**
     * this.���t == null�̏ꍇ��
     * �u�I�y���[�V�������t�����w��ł��B�v 
     */
    public void test_validate_0001()
    {
        String STR_METHOD_NAME = "test_validate_0001()";
        log.entering(STR_METHOD_NAME);

        l_request.searchDate = null;
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01272, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * this.���t�̓��͌`���� 'yyyymmdd' �ł͂Ȃ��ꍇ�́A 
     * �u���t���s���ł��B�v
     */
    public void test_validate_0002()
    {
        String STR_METHOD_NAME = "test_validate_0002()";
        log.entering(STR_METHOD_NAME);

        l_request.searchDate = "2008-08-08";
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02994, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * this.���t="20080922" 
     * this.����(��) == null�̏ꍇ�́A
     * �u����(��)��null�ł��B�v�̗�O���X���[����
     */
    public void test_validate_0003()
    {
        String STR_METHOD_NAME = "test_validate_0003()";
        log.entering(STR_METHOD_NAME);

        //���t
        l_request.searchDate = "20080922";
        //����(��)
        l_request.startTime = null;
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03118, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * this.���t="20080922" 
     * this.����(��)�̓��͌`���� 'hh24mi' �ł͂Ȃ��ꍇ�́A 
     * �u�\�����ԁi���j���t�t�H�[�}�b�g�G���[�B�v 
     */
    public void test_validate_0004()
    {
        String STR_METHOD_NAME = "test_validate_0004()";
        log.entering(STR_METHOD_NAME);

        //���t
        l_request.searchDate = "20080922";
        //����(��)
        l_request.startTime = "12:30";
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01065, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * this.���t="20080922" 
     * this.����(��)="1230"
     * this.����(��) == null�̏ꍇ�́A
     * �@@�u����(��)��null�ł��B�v�̗�O���X���[����
     */
    public void test_validate_0005()
    {
        String STR_METHOD_NAME = "test_validate_0005()";
        log.entering(STR_METHOD_NAME);

        //���t
        l_request.searchDate = "20080922";
        //����(��)
        l_request.startTime = "1230";
        //����(��)
        l_request.endTime = null;
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03119, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * this.���t="20080922" 
     * this.����(��)="1230"
     * this.����(��)�̓��͌`���� 'hh24mi' �ł͂Ȃ��ꍇ�́A 
     * �u�\�����ԁi���j���t�t�H�[�}�b�g�G���[�B�v 
     */
    public void test_validate_0006()
    {
        String STR_METHOD_NAME = "test_validate_0006()";
        log.entering(STR_METHOD_NAME);

        //���t
        l_request.searchDate = "20080922";
        //����(��)
        l_request.startTime = "1230";
        //����(��)
        l_request.endTime = "232301";
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01066, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * this.���t="20080922" 
     * this.����(��)="2330"
     * this.����(��)="2323"
     * this.����(��) > this.����(��) �̏ꍇ��
     * �u�\�����ԁi���j�͕\�����ԁi���j���傫���ł��B�v 
     */
    public void test_validate_0007()
    {
        String STR_METHOD_NAME = "test_validate_0007()";
        log.entering(STR_METHOD_NAME);

        //���t
        l_request.searchDate = "20080922";
        //����(��)
        l_request.startTime = "2330";
        //����(��)
        l_request.endTime = "2323";
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01051, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * this.���t="20080922" 
     * this.����(��)="1030"
     * this.����(��)="1420"
     * [this.����(��) - this.����(��)] > �P���� �̏ꍇ�́A 
     * �u�w��͈͂͂P���Ԉȓ��œ��͂��Ă��������B�v�̗�O���X���[����B 
     */
    public void test_validate_0008()
    {
        String STR_METHOD_NAME = "test_validate_0008()";
        log.entering(STR_METHOD_NAME);

        //���t
        l_request.searchDate = "20080922";
        //����(��)
        l_request.startTime = "1030";
        //����(��)
        l_request.endTime = "1420";
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03128, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * this.���t="20080922" 
     * this.����(��)="1030"
     * this.����(��)="1120"
     * this.�����N == null�̏ꍇ�́A 
     * �u�����N�������͂ł��B�v�̗�O���X���[����B
     */
    public void test_validate_0009()
    {
        String STR_METHOD_NAME = "test_validate_0009()";
        log.entering(STR_METHOD_NAME);

        //���t
        l_request.searchDate = "20080922";
        //����(��)
        l_request.startTime = "1030";
        //����(��)
        l_request.endTime = "1120";
        //�����N
        l_request.rank = null;
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03129, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * this.���t="20080922" 
     * this.����(��)="1030"
     * this.����(��)="1120"
     * this.�����N �ɔ��p�����ȊO�̕����������Ă����ꍇ�� 
     * �u�����N�͔��p�����œ��͂��Ă��������B�v�̗�O���X���[����B
     */
    public void test_validate_0010()
    {
        String STR_METHOD_NAME = "test_validate_0010()";
        log.entering(STR_METHOD_NAME);

        //���t
        l_request.searchDate = "20080922";
        //����(��)
        l_request.startTime = "1030";
        //����(��)
        l_request.endTime = "1120";
        //�����N
        l_request.rank = "aad";
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03130, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * this.���t="20080922" 
     * this.����(��)="1030"
     * this.����(��)="1120"
     * this.�����N > 30 �̏ꍇ�́A
     * �u�����N�͏��30�ʂ܂ł̕\�������ł��܂���B�v�̗�O���X���[����B 
     */
    public void test_validate_0011()
    {
        String STR_METHOD_NAME = "test_validate_0011()";
        log.entering(STR_METHOD_NAME);

        //���t
        l_request.searchDate = "20080922";
        //����(��)
        l_request.startTime = "1030";
        //����(��)
        l_request.endTime = "1120";
        //�����N
        l_request.rank = "45";
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03131, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * this.���t="20080922" 
     * this.����(��)="1030"
     * this.����(��)="1120"
     * this.�����N="30"
     * this.�y�[�W���\���s�� == null�̏ꍇ�A
     * �u�y�[�W���\���s���̓��͂��s���ł��B�v�̗�O���X���[����B 
     */
    public void test_validate_0012()
    {
        String STR_METHOD_NAME = "test_validate_0012()";
        log.entering(STR_METHOD_NAME);

        //���t
        l_request.searchDate = "20080922";
        //����(��)
        l_request.startTime = "1030";
        //����(��)
        l_request.endTime = "1120";
        //�����N
        l_request.rank = "30";
        //�y�[�W���\���s��
        l_request.pageSize = null;
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00091, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * this.���t="20080922" 
     * this.����(��)="1030"
     * this.����(��)="1120"
     * this.�����N="30"
     * this.�y�[�W���\���s�������p�����ȊO�̒l�ł������ꍇ�A 
     * �u�y�[�W���\���s���������ȊO�̒l�ł��B�v�̗�O���X���[����B 
     */
    public void test_validate_0013()
    {
        String STR_METHOD_NAME = "test_validate_0013()";
        log.entering(STR_METHOD_NAME);

        //���t
        l_request.searchDate = "20080922";
        //����(��)
        l_request.startTime = "1030";
        //����(��)
        l_request.endTime = "1120";
        //�����N
        l_request.rank = "30";
        //�y�[�W���\���s��
        l_request.pageSize = "aa";
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00092, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * this.���t="20080922" 
     * this.����(��)="1030"
     * this.����(��)="1120"
     * this.�����N="30"
     * this.�y�[�W���\���s�� <= 0�ł������ꍇ�A 
     * �u�y�[�W���\���s���̒l��0�ȉ��ł��B�v�̗�O���X���[����B
     */
    public void test_validate_0014()
    {
        String STR_METHOD_NAME = "test_validate_0014()";
        log.entering(STR_METHOD_NAME);

        //���t
        l_request.searchDate = "20080922";
        //����(��)
        l_request.startTime = "1030";
        //����(��)
        l_request.endTime = "1120";
        //�����N
        l_request.rank = "30";
        //�y�[�W���\���s��
        l_request.pageSize = "0";
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00617, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * this.���t="20080922" 
     * this.����(��)="1030"
     * this.����(��)="1120"
     * this.�����N="30"
     * this.�y�[�W���\���s��="1"
     * this.�v���y�[�W�ԍ� == null�̏ꍇ�A
     * �u�v���y�[�W�ԍ������w��ł��B�v�̗�O���X���[����B 
     */
    public void test_validate_0015()
    {
        String STR_METHOD_NAME = "test_validate_0015()";
        log.entering(STR_METHOD_NAME);

        //���t
        l_request.searchDate = "20080922";
        //����(��)
        l_request.startTime = "1030";
        //����(��)
        l_request.endTime = "1120";
        //�����N
        l_request.rank = "30";
        //�y�[�W���\���s��
        l_request.pageSize = "1";
        //�v���y�[�W�ԍ�
        l_request.pageIndex = null;
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00089, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * this.���t="20080922" 
     * this.����(��)="1030"
     * this.����(��)="1120"
     * this.�����N="30"
     * this.�y�[�W���\���s��="1"
     * this.�v���y�[�W�ԍ������p�����ȊO�̒l�ł������ꍇ�A 
     * �u�v���y�[�W�ԍ��������ȊO�̒l�ł��B�v�̗�O���X���[����B
     */
    public void test_validate_0016()
    {
        String STR_METHOD_NAME = "test_validate_0016()";
        log.entering(STR_METHOD_NAME);

        //���t
        l_request.searchDate = "20080922";
        //����(��)
        l_request.startTime = "1030";
        //����(��)
        l_request.endTime = "1120";
        //�����N
        l_request.rank = "30";
        //�y�[�W���\���s��
        l_request.pageSize = "1";
        //�v���y�[�W�ԍ�
        l_request.pageIndex = "a";
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00090, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * this.���t="20080922" 
     * this.����(��)="1030"
     * this.����(��)="1120"
     * this.�����N="30"
     * this.�y�[�W���\���s��="1"
     * this.�v���y�[�W�ԍ� <= 0�ł������ꍇ�A 
     * �u�v���y�[�W�ԍ��̒l��0�ȉ��ł��B�v�̗�O���X���[����B
     */
    public void test_validate_0017()
    {
        String STR_METHOD_NAME = "test_validate_0017()";
        log.entering(STR_METHOD_NAME);

        //���t
        l_request.searchDate = "20080922";
        //����(��)
        l_request.startTime = "1030";
        //����(��)
        l_request.endTime = "1120";
        //�����N
        l_request.rank = "30";
        //�y�[�W���\���s��
        l_request.pageSize = "1";
        //�v���y�[�W�ԍ�
        l_request.pageIndex = "0";
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00616, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * this.���t="20080922" 
     * this.����(��)="1030"
     * this.����(��)="1120"
     * this.�����N="30"
     * this.�y�[�W���\���s��="1"
     * this.�v���y�[�W�ԍ�="1"
     */
    public void test_validate_0018()
    {
        String STR_METHOD_NAME = "test_validate_0018()";
        log.entering(STR_METHOD_NAME);

        //���t
        l_request.searchDate = "20080922";
        //����(��)
        l_request.startTime = "1030";
        //����(��)
        l_request.endTime = "1120";
        //�����N
        l_request.rank = "30";
        //�y�[�W���\���s��
        l_request.pageSize = "1";
        //�v���y�[�W�ԍ�
        l_request.pageIndex = "1";
        
        try
        {
            l_request.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            fail();
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
