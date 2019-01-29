head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.08.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminTraderAdminIPControlListRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE���O�C������IP�ꗗ���N�G�X�g(WEB3AdminTraderAdminIPControlListRequestTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/09/23 �����F (���u) �V�K�쐬 ���f��004
*/

package webbroker3.trademanagement.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminTraderAdminIPControlListRequestTest extends TestBaseForMock
{

    public WEB3AdminTraderAdminIPControlListRequestTest(String name)
    {
        super(name);
    }

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTraderAdminIPControlListRequestTest.class);

    WEB3AdminTraderAdminIPControlListRequest l_request = new WEB3AdminTraderAdminIPControlListRequest();
    
    /**
     * this.�y�[�W���\���s�� == null�̏ꍇ�A
     * �u�y�[�W���\���s���̓��͂��s���ł��B�v�̗�O���X���[����B 
     */
    public void test_validate_0001()
    {
        String STR_METHOD_NAME = "test_validate_0001()";
        log.entering(STR_METHOD_NAME);

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
     * this.�y�[�W���\���s�������p�����ȊO�̒l�ł������ꍇ�A 
     * �u�y�[�W���\���s���������ȊO�̒l�ł��B�v�̗�O���X���[����B 
     */
    public void test_validate_0002()
    {
        String STR_METHOD_NAME = "test_validate_0002()";
        log.entering(STR_METHOD_NAME);

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
     * this.�y�[�W���\���s�� <= 0�ł������ꍇ�A 
     * �u�y�[�W���\���s���̒l��0�ȉ��ł��B�v�̗�O���X���[����B
     */
    public void test_validate_0003()
    {
        String STR_METHOD_NAME = "test_validate_0003()";
        log.entering(STR_METHOD_NAME);
        
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
     * this.�y�[�W���\���s��="1"
     * this.�v���y�[�W�ԍ� == null�̏ꍇ�A
     * �u�v���y�[�W�ԍ������w��ł��B�v�̗�O���X���[����B 
     */
    public void test_validate_0004()
    {
        String STR_METHOD_NAME = "test_validate_0004()";
        log.entering(STR_METHOD_NAME);
        
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
     * this.�y�[�W���\���s��="1"
     * this.�v���y�[�W�ԍ������p�����ȊO�̒l�ł������ꍇ�A 
     * �u�v���y�[�W�ԍ��������ȊO�̒l�ł��B�v�̗�O���X���[����B
     */
    public void test_validate_0005()
    {
        String STR_METHOD_NAME = "test_validate_0005()";
        log.entering(STR_METHOD_NAME);

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
     * this.�y�[�W���\���s��="1"
     * this.�v���y�[�W�ԍ� <= 0�ł������ꍇ�A 
     * �u�v���y�[�W�ԍ��̒l��0�ȉ��ł��B�v�̗�O���X���[����B
     */
    public void test_validate_0006()
    {
        String STR_METHOD_NAME = "test_validate_0006()";
        log.entering(STR_METHOD_NAME);

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
     * this.�y�[�W���\���s��="1"
     * this.�v���y�[�W�ԍ�="1"
     * this.�\�[�g�L�[ == null�ł������ꍇ
     * �u�\�[�g�L�[��null�v�̗�O���X���[����B
     */
    public void test_validate_0007()
    {
        String STR_METHOD_NAME = "test_validate_0007()";
        log.entering(STR_METHOD_NAME);

        //�y�[�W���\���s��
        l_request.pageSize = "1";
        //�v���y�[�W�ԍ�
        l_request.pageIndex = "1";
        //�\�[�g�L�[
        l_request.sortKeys = null;
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00231, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * this.�y�[�W���\���s��="1"
     * this.�v���y�[�W�ԍ�="1"
     * this.�\�[�g�L�[.length == 0�������ꍇ
     * �u�\�[�g�L�[.�v�f����0�v�̗�O���X���[����B
     */
    public void test_validate_0008()
    {
        String STR_METHOD_NAME = "test_validate_0008()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminTraderAdminIPControlSortKey[] l_sortKeys = new WEB3AdminTraderAdminIPControlSortKey[]{};
        
        //�y�[�W���\���s��
        l_request.pageSize = "1";
        //�v���y�[�W�ԍ�
        l_request.pageIndex = "1";
        //�\�[�g�L�[
        l_request.sortKeys = l_sortKeys;
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00232, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * this.�y�[�W���\���s��="1"
     * this.�v���y�[�W�ԍ�="1"
     * �\�[�g�L�[.validate()���R�[������
     */
    public void test_validate_0009()
    {
        String STR_METHOD_NAME = "test_validate_0009()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminTraderAdminIPControlSortKey l_sortKey= new WEB3AdminTraderAdminIPControlSortKey();
        l_sortKey.keyItem = null;
        WEB3AdminTraderAdminIPControlSortKey[] l_sortKeys = new WEB3AdminTraderAdminIPControlSortKey[]{l_sortKey};
        
        //�y�[�W���\���s��
        l_request.pageSize = "1";
        //�v���y�[�W�ԍ�
        l_request.pageIndex = "1";
        //�\�[�g�L�[
        l_request.sortKeys = l_sortKeys;
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00085, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
