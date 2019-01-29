head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.22.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminSLProductRegiListRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �S�ۖ����o�^�ꗗ���N�G�X�gTest(WEB3AdminSLProductRegiListRequestTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/14 �����F (���u) �V�K�쐬 ���f�� 760
*/

package webbroker3.aio.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminSLProductRegiListRequestTest extends TestBaseForMock
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(
                 WEB3AdminSLProductRegiListRequestTest.class);

    public WEB3AdminSLProductRegiListRequestTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /*
     * Test method for 'webbroker3.aio.message.WEB3AdminSLProductRegiListRequest.validate()'
     */
    //���N�G�X�g.�����^�C�v != null and ���N�G�X�g.�����^�C�v�����p�����ȊO
    public void testValidate1()
    {
        final String STR_METHOD_NAME = "testValidate1()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminSLProductRegiListRequest l_request = new WEB3AdminSLProductRegiListRequest();
        
        try
        {
            l_request.productType = "asd";
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02916, l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //���N�G�X�g.�����R�[�h != null and ���N�G�X�g.�����R�[�h�����p�����ȊO
    public void testValidate2()
    {
        final String STR_METHOD_NAME = "testValidate2()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminSLProductRegiListRequest l_request = new WEB3AdminSLProductRegiListRequest();
        
        try
        {
            l_request.productType = "1";
            l_request.productCode = "asd";
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00815, l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //���N�G�X�g.�K�i�敪 != null and ���N�G�X�g.�K�i�敪�����p�����ȊO
    public void testValidate3()
    {
        final String STR_METHOD_NAME = "testValidate3()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminSLProductRegiListRequest l_request = new WEB3AdminSLProductRegiListRequest();
        
        try
        {
            l_request.productType = "1";
            l_request.productCode = "123";
            l_request.qualifiedDiv = "a";
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02925, l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //���N�G�X�g.�K�i�敪 != null and���N�G�X�g.�K�i�敪.length() != 1
    public void testValidate4()
    {
        final String STR_METHOD_NAME = "testValidate4()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminSLProductRegiListRequest l_request = new WEB3AdminSLProductRegiListRequest();
        
        try
        {
            l_request.productType = "1";
            l_request.productCode = "123";
            l_request.qualifiedDiv = "12";
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02931, l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //�S�j�K�p���ԋ敪�`�F�b�N  ���N�G�X�g.�K�p���ԋ敪 != null and  
    //(���N�G�X�g.�K�p���ԋ敪�����p�����ȊO) 
    public void testValidate14()
    {
        final String STR_METHOD_NAME = "testValidate14()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminSLProductRegiListRequest l_request = new WEB3AdminSLProductRegiListRequest();
        
        try
        {
            l_request.productType = "1";
            l_request.productCode = "123";
            l_request.qualifiedDiv = "1";
            l_request.targetPeriodDiv = "a";
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02932, l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //�S�j�K�p���ԋ敪�`�F�b�N  ���N�G�X�g.�K�p���ԋ敪 != null and  
    //(���N�G�X�g.�K�p���ԋ敪.length() != 1)
    public void testValidate15()
    {
        final String STR_METHOD_NAME = "testValidate15()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminSLProductRegiListRequest l_request = new WEB3AdminSLProductRegiListRequest();
        
        try
        {
            l_request.productType = "1";
            l_request.productCode = "123";
            l_request.qualifiedDiv = "1";
            l_request.targetPeriodDiv = "12";
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02933, l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //�T�j �v���y�[�W�ԍ��`�F�b�N    ���N�G�X�g.�v���y�[�W�ԍ� = null
    public void testValidate5()
    {
        final String STR_METHOD_NAME = "testValidate5()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminSLProductRegiListRequest l_request = new WEB3AdminSLProductRegiListRequest();
        
        try
        {
            l_request.productType = "1";
            l_request.productCode = "123";
            l_request.qualifiedDiv = "1";
            l_request.targetPeriodDiv = "1";
            l_request.pageIndex = "";
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00089, l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //���N�G�X�g.�v���y�[�W�ԍ��������ȊO
    public void testValidate6()
    {
        final String STR_METHOD_NAME = "testValidate6()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminSLProductRegiListRequest l_request = new WEB3AdminSLProductRegiListRequest();
        
        try
        {
            l_request.productType = "1";
            l_request.productCode = "123";
            l_request.qualifiedDiv = "1";
            l_request.targetPeriodDiv = "1";
            l_request.pageIndex = "aa";
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00090, l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //���N�G�X�g.�v���y�[�W�ԍ� <= 0 
    public void testValidate77()
    {
        final String STR_METHOD_NAME = "testValidate77()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminSLProductRegiListRequest l_request = new WEB3AdminSLProductRegiListRequest();
        
        try
        {
            l_request.productType = "1";
            l_request.productCode = "123";
            l_request.qualifiedDiv = "1";
            l_request.targetPeriodDiv = "1";
            l_request.pageIndex = "0";
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00616, l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
//    //���N�G�X�g.�v���y�[�W�ԍ� <= 0 
//    public void testValidate7()
//    {
//        final String STR_METHOD_NAME = "testValidate7()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        
//        WEB3AdminSLProductRegiListRequest l_request = new WEB3AdminSLProductRegiListRequest();
//        
//        try
//        {
//            l_request.productType = "1";
//            l_request.productCode = "123";
//            l_request.qualifiedDiv = "1";
//            l_request.targetPeriodDiv = "1";
//            l_request.pageIndex = "-1";
//            l_request.validate();
//            fail();
//        }
//        catch(WEB3BaseException l_ex)
//        {
//            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00616, l_ex.getErrorInfo());
//            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>test pass !");
//        }
//        catch(Exception l_ex)
//        {
//            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
//            fail();
//        }
//
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
    
    //�U�j�y�[�W���\���s���`�F�b�N     ���N�G�X�g.�y�[�W���\���s�� = null
    public void testValidate8()
    {
        final String STR_METHOD_NAME = "testValidate8()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminSLProductRegiListRequest l_request = new WEB3AdminSLProductRegiListRequest();
        
        try
        {
            l_request.productType = "1";
            l_request.productCode = "123";
            l_request.qualifiedDiv = "1";
            l_request.targetPeriodDiv = "1";
            l_request.pageIndex = "1";
            l_request.pageSize = "";
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02224, l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //���N�G�X�g.�y�[�W���\���s���������ȊO
    public void testValidate9()
    {
        final String STR_METHOD_NAME = "testValidate9()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminSLProductRegiListRequest l_request = new WEB3AdminSLProductRegiListRequest();
        
        try
        {
            l_request.productType = "1";
            l_request.productCode = "123";
            l_request.qualifiedDiv = "1";
            l_request.targetPeriodDiv = "1";
            l_request.pageIndex = "1";
            l_request.pageSize = "a";
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00092, l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //���N�G�X�g.�y�[�W���\���s�� <= 0
    public void testValidate111()
    {
        final String STR_METHOD_NAME = "testValidate111()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminSLProductRegiListRequest l_request = new WEB3AdminSLProductRegiListRequest();
        
        try
        {
            l_request.productType = "1";
            l_request.productCode = "123";
            l_request.qualifiedDiv = "1";
            l_request.targetPeriodDiv = "1";
            l_request.pageIndex = "1";
            l_request.pageSize = "0";
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00617, l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
//    //���N�G�X�g.�y�[�W���\���s�� <= 0
//    public void testValidate10()
//    {
//        final String STR_METHOD_NAME = "testValidate10()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        
//        WEB3AdminSLProductRegiListRequest l_request = new WEB3AdminSLProductRegiListRequest();
//        
//        try
//        {
//            l_request.productType = "1";
//            l_request.productCode = "123";
//            l_request.qualifiedDiv = "1";
//            l_request.targetPeriodDiv = "1";
//            l_request.pageIndex = "1";
//            l_request.pageSize = "-1";
//            l_request.validate();
//            fail();
//        }
//        catch(WEB3BaseException l_ex)
//        {
//            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00617, l_ex.getErrorInfo());
//            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>test pass !");
//        }
//        catch(Exception l_ex)
//        {
//            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
//            fail();
//        }
//
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//    
    //�V�j�\�[�g�L�[�`�F�b�N  ���N�G�X�g�f�[�^.�\�[�g�L�[ = null 
    public void testValidate11()
    {
        final String STR_METHOD_NAME = "testValidate11()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminSLProductRegiListRequest l_request = new WEB3AdminSLProductRegiListRequest();
        
        try
        {
            l_request.productType = "1";
            l_request.productCode = "123";
            l_request.qualifiedDiv = "1";
            l_request.targetPeriodDiv = "1";
            l_request.pageIndex = "1";
            l_request.pageSize = "1";
            WEB3SLSortKey[] l_sortKeys = null;
            l_request.sortKeys = l_sortKeys;
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00231, l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //���N�G�X�g�f�[�^.�\�[�g�L�[.length() = 0 
    public void testValidate12()
    {
        final String STR_METHOD_NAME = "testValidate12()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminSLProductRegiListRequest l_request = new WEB3AdminSLProductRegiListRequest();
        
        try
        {
            l_request.productType = "1";
            l_request.productCode = "123";
            l_request.qualifiedDiv = "1";
            l_request.targetPeriodDiv = "1";
            l_request.pageIndex = "1";
            l_request.pageSize = "1";
            WEB3SLSortKey[] l_sortKeys = new WEB3SLSortKey[0];
            l_request.sortKeys = l_sortKeys;
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00232, l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //���팋��
    public void testValidate13()
    {
        final String STR_METHOD_NAME = "testValidate13()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminSLProductRegiListRequest l_request = new WEB3AdminSLProductRegiListRequest();
        
        try
        {
            l_request.productType = "1";
            l_request.productCode = "123";
            l_request.qualifiedDiv = "1";
            l_request.targetPeriodDiv = "1";
            l_request.pageIndex = "1";
            l_request.pageSize = "1";
            WEB3SLSortKey[] l_sortKeys = new WEB3SLSortKey[1];
            l_sortKeys[0] = new WEB3SLSortKey();
            l_request.sortKeys = l_sortKeys;
            l_request.validate();
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
