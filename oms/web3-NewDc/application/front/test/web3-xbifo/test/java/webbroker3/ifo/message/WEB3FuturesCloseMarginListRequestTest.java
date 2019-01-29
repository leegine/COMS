head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.24.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FuturesCloseMarginListRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : (WEB3FuturesCloseMarginListRequestTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/06/04 ���z(���u) �V�K�쐬
*/
package webbroker3.ifo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3FuturesCloseMarginListRequestTest extends TestBaseForMock
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FuturesCloseMarginListRequestTest.class);
    
    WEB3FuturesCloseMarginListRequest l_request = null;
    
    public WEB3FuturesCloseMarginListRequestTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        l_request = new WEB3FuturesCloseMarginListRequest();
    }

    protected void tearDown() throws Exception
    {
        l_request = null;
        super.tearDown();
    }

    /*
     * Test method for 'webbroker3.ifo.message.WEB3FuturesCloseMarginListRequest.validate()'
     */
    
    //�P�j�@@�\�[�g�L�[�`�F�b�N
    //�P�|�P�j�����w���敨�I�v�V�����\�[�g�L�[��null�̒l�ł���Η�O���X���[����
    public void testValidate_C0001()
    {
        String STR_METHOD_NAME = "testValidate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00231, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    //�P�|�Q�jthis.�����w���敨�I�v�V�����\�[�g�L�[�̗v�f�����O�ł���Η�O���X���[����B
    public void testValidate_C0002()
    {
        String STR_METHOD_NAME = "testValidate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_request.futOpSortKeys = new WEB3FuturesOptionsSortKey[0];
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00232, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    //�P�|�R�jthis.�����w���敨�I�v�V�����\�[�g�L�[�̗v�f�����J��Ԃ��ă`�F�b�N���s���B
    //�P�|�R�|�P�j�\�[�g�L�[.�L�[���ڂ�null�̒l�ł���Η�O���X���[����B
    public void testValidate_C0003()
    {
        String STR_METHOD_NAME = "testValidate_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_request.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_request.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00085, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    //�P�|�R�|�Q�j�\�[�g�L�[.�L�[���ڂɈȉ��̍��ږ��ȊO�̒l�����݂������O���X���[����B
    //�����R�[�h
    //���敪
    //���v
    //���v(���o�)
    public void testValidate_C0004()
    {
        String STR_METHOD_NAME = "testValidate_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_request.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_request.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_request.futOpSortKeys[0].keyItem = "other";
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00086, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    //�P�|�R�|�R�j�\�[�g�L�[.�����^�~����null�̒l�ł���Η�O���X���[����B
    public void testValidate_C0005()
    {
        String STR_METHOD_NAME = "testValidate_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_request.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_request.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_request.futOpSortKeys[0].keyItem = "futProductCode";
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00087, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    //�P�|�R�|�S�j�\�[�g�L�[.�����^�~�����ȉ��̒l�ȊO�̏ꍇ��O���X���[����B
    //�hA�F�����h
    //�hD�F�~���h
    public void testValidate_C0006()
    {
        String STR_METHOD_NAME = "testValidate_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_request.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_request.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_request.futOpSortKeys[0].keyItem = "futProductCode";
            l_request.futOpSortKeys[0].ascDesc = "other";
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00088, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    //�Q�j�@@�v���y�[�W�ԍ��`�F�b�N
    //�Q�|�P�jthis.�v���y�[�W�ԍ���null�̒l�ł���Η�O���X���[����B
    public void testValidate_C0007()
    {
        String STR_METHOD_NAME = "testValidate_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_request.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_request.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_request.futOpSortKeys[0].keyItem = "income";
            l_request.futOpSortKeys[0].ascDesc = "A";
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00089, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }    
    
    //�Q�|�Q�jthis.�v���y�[�W�ԍ��������ȊO�̒l�ł���Η�O���X���[����B
    public void testValidate_C0008()
    {
        String STR_METHOD_NAME = "testValidate_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_request.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_request.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_request.futOpSortKeys[0].keyItem = "incomeCost";
            l_request.futOpSortKeys[0].ascDesc = "D";
            l_request.pageIndex = "a";
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00090, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    //�Q�|�Q�jthis.�v���y�[�W�ԍ��������ȊO�̒l�ł���Η�O���X���[����B
    public void testValidate_C0009()
    {
        String STR_METHOD_NAME = "testValidate_C0009()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_request.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_request.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_request.futOpSortKeys[0].keyItem = "incomeCost";
            l_request.futOpSortKeys[0].ascDesc = "D";
            l_request.pageIndex = ",";
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00090, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    //�R�j�@@�y�[�W���\���s���`�F�b�N
    //�R�|�P�jthis.�y�[�W���\���s����null�̒l�ł���Η�O���X���[����B
    public void testValidate_C0010()
    {
        String STR_METHOD_NAME = "testValidate_C0010()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_request.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_request.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_request.futOpSortKeys[0].keyItem = "contractType";
            l_request.futOpSortKeys[0].ascDesc = "D";
            l_request.pageIndex = "1";
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00091, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    //�R�|�Q�jthis.�y�[�W���\���s���������ȊO�̒l�ł���Η�O���X���[����B
    public void testValidate_C0011()
    {
        String STR_METHOD_NAME = "testValidate_C0011()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_request.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_request.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_request.futOpSortKeys[0].keyItem = "contractType";
            l_request.futOpSortKeys[0].ascDesc = "D";
            l_request.pageIndex = "0";
            l_request.pageSize = "a";
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00092, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    //�R�|�Q�jthis.�y�[�W���\���s���������ȊO�̒l�ł���Η�O���X���[����B
    public void testValidate_C0012()
    {
        String STR_METHOD_NAME = "testValidate_C0012()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_request.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_request.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_request.futOpSortKeys[0].keyItem = "contractType";
            l_request.futOpSortKeys[0].ascDesc = "D";
            l_request.pageIndex = "0";
            l_request.pageSize = ",";
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00092, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
   
    //�S�j �����ݒ�`�F�b�N
    //�S�|�P�j�ȉ��̑S�Ẵ��N�G�X�g���ڂ�ݒ肵�Ă���ꍇ�A��O���X���[����B
    //    �@@(�����R�[�h�Ɩ������荀�ڂ��ǂ�����ݒ肳��Ă���ꍇ)
    public void testValidate_C0013()
    {
        String STR_METHOD_NAME = "testValidate_C0013()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_request.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_request.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_request.futOpSortKeys[0].keyItem = "contractType";
            l_request.futOpSortKeys[0].ascDesc = "D";
            l_request.pageIndex = "1";
            l_request.pageSize = "0";
            l_request.futProductCode = "a";
            l_request.marketCode = "b1";
            l_request.targetProductCode = "b2";
            l_request.delivaryMonth = "b3";
            l_request.validate();
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00830, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    // �S�|�Q�j�����I�����Ɏ���s��,�w�����,�����̂����ꂩ�̍��ڂ�              
    // �ݒ肳��Ă��Ȃ��ꍇ�A��O���X���[����B
    public void testValidate_C0014()
    {
        String STR_METHOD_NAME = "testValidate_C0014()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_request.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_request.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_request.futOpSortKeys[0].keyItem = "contractType";
            l_request.futOpSortKeys[0].ascDesc = "D";
            l_request.pageIndex = "1";
            l_request.pageSize = "0";
            l_request.futProductCode = "a";
            l_request.marketCode = null;
            l_request.targetProductCode = "b2";
            l_request.delivaryMonth = "b3";
            l_request.validate();
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00830, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    // �S�|�Q�j�����I�����Ɏ���s��,�w�����,�����̂����ꂩ�̍��ڂ�              
    // �ݒ肳��Ă��Ȃ��ꍇ�A��O���X���[����B    
    public void testValidate_C0015()
    {
        String STR_METHOD_NAME = "testValidate_C0015()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_request.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_request.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_request.futOpSortKeys[0].keyItem = "contractType";
            l_request.futOpSortKeys[0].ascDesc = "D";
            l_request.pageIndex = "1";
            l_request.pageSize = "0";
            l_request.futProductCode = "a";
            l_request.marketCode = "b1";
            l_request.targetProductCode = null;
            l_request.delivaryMonth = "b3";
            l_request.validate();
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00830, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    // �S�|�Q�j�����I�����Ɏ���s��,�w�����,�����̂����ꂩ�̍��ڂ�              
    // �ݒ肳��Ă��Ȃ��ꍇ�A��O���X���[����B    
    public void testValidate_C0016()
    {
        String STR_METHOD_NAME = "testValidate_C0016()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_request.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_request.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_request.futOpSortKeys[0].keyItem = "contractType";
            l_request.futOpSortKeys[0].ascDesc = "D";
            l_request.pageIndex = "1";
            l_request.pageSize = "0";
            l_request.futProductCode = "a";
            l_request.marketCode = "b1";
            l_request.targetProductCode = "b2";
            l_request.delivaryMonth = null;
            l_request.validate();
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00830, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    // �S�|�Q�j�����I�����Ɏ���s��,�w�����,�����̂����ꂩ�̍��ڂ�              
    // �ݒ肳��Ă��Ȃ��ꍇ�A��O���X���[����B    
    public void testValidate_C0017()
    {
        String STR_METHOD_NAME = "testValidate_C0017()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_request.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_request.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_request.futOpSortKeys[0].keyItem = "contractType";
            l_request.futOpSortKeys[0].ascDesc = "D";
            l_request.pageIndex = "1";
            l_request.pageSize = "0";
            l_request.futProductCode = null;
            l_request.marketCode = "b1";
            l_request.targetProductCode = "b2";
            l_request.delivaryMonth = null;
            l_request.validate();
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00830, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    //normal case
    public void testValidate_C0018()
    {
        String STR_METHOD_NAME = "testValidate_C0018()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_request.futOpSortKeys = new WEB3FuturesOptionsSortKey[2];
            l_request.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_request.futOpSortKeys[0].keyItem = "contractType";
            l_request.futOpSortKeys[0].ascDesc = "D";
            l_request.futOpSortKeys[1] = new WEB3FuturesOptionsSortKey();
            l_request.futOpSortKeys[1].keyItem = "income";
            l_request.futOpSortKeys[1].ascDesc = "A";
            l_request.pageIndex = "1";
            l_request.pageSize = "0";
            l_request.futProductCode = "a";
            l_request.marketCode = null;
            l_request.targetProductCode = null;
            l_request.delivaryMonth = null;
            l_request.validate();
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    //normal case
    public void testValidate_C0019()
    {
        String STR_METHOD_NAME = "testValidate_C0019()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_request.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_request.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_request.futOpSortKeys[0].keyItem = "contractType";
            l_request.futOpSortKeys[0].ascDesc = "D";
            l_request.pageIndex = "1";
            l_request.pageSize = "0";
            l_request.futProductCode = null;
            l_request.marketCode = null;
            l_request.targetProductCode = null;
            l_request.delivaryMonth = null;
            l_request.validate();
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    //normal case
    public void testValidate_C0020()
    {
        String STR_METHOD_NAME = "testValidate_C0020()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_request.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_request.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_request.futOpSortKeys[0].keyItem = "contractType";
            l_request.futOpSortKeys[0].ascDesc = "D";
            l_request.pageIndex = "1";
            l_request.pageSize = "0";
            l_request.futProductCode = null;
            l_request.marketCode = "b1";
            l_request.targetProductCode = "b2";
            l_request.delivaryMonth = "b3";
            l_request.validate();
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
}
@
