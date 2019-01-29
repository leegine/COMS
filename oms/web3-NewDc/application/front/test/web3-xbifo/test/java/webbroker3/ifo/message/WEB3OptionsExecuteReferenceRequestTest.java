head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.25.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3OptionsExecuteReferenceRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���I�v�V�����������Ɖ�N�G�X�g(WEB3OptionsExecuteReferenceRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/04 �И��� (���u) �V�K�쐬
Revision History : 2007/10/18 �����F (���u)
*/
package webbroker3.ifo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ifo.define.WEB3IfoKeyItemDef;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3OptionsExecuteReferenceRequestTest extends TestBaseForMock
{

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility
        .getInstance(WEB3OptionsCommonRequestTest.class);
    
    public WEB3OptionsExecuteReferenceRequestTest(String name)
    {
        super(name);
    }

    /**
     * this.�Ɖ�敪��null�̒l�ł���Η�O���X���[����
     * date:
     * this.�Ɖ�敪 = null
     * �e�X�g�m�F���e:
     * BUSINESS_ERROR_00081
     */
    public void test_validate_0001()
    {
        String STR_METHOD_NAME = "test_validate_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3OptionsExecuteReferenceRequest l_request = new WEB3OptionsExecuteReferenceRequest();
        
        l_request.referenceType = null;
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.debug("" + e.getErrorInfo());
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00081, e.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * this.�Ɖ�敪���ȉ��̒l�ȊO�̏ꍇ��O���X���[����
     * �h0�F�������Ɖ�h 
     * �h1�F��������ꗗ�i��������\�Ȃ��̂̂ݕ\���j�h
     * date:
     * this.�Ɖ�敪 = "2"
     * �e�X�g�m�F���e:
     * BUSINESS_ERROR_00082
     */
    public void test_validate_0002()
    {
        String STR_METHOD_NAME = "test_validate_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3OptionsExecuteReferenceRequest l_request = new WEB3OptionsExecuteReferenceRequest();
        
        l_request.referenceType = "2";
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.debug("" + e.getErrorInfo());
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00082, e.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * this.�Ɖ�敪 = "0�F�������Ɖ�" pass
     * date:
     * this.�Ɖ�敪 = "0�F�������Ɖ�"
     * this.����ԋ敪 = �g0�F�����h
     * this.�����w���敨�I�v�V�����\�[�g�L�[.�L�[���� = �����R�[�h 
     * �����w���敨�I�v�V�����\�[�g�L�[.�����^�~�� = �gA�F�����h
     * this.�v���y�[�W�ԍ� = �g1�h
     * this.�y�[�W���\���s�� = �g1�h
     * �����R�[�h  = null
     * �e�X�g�m�F���e:
     * pass
     */
    public void test_validate_0003()
    {
        String STR_METHOD_NAME = "test_validate_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3OptionsExecuteReferenceRequest l_request = new WEB3OptionsExecuteReferenceRequest();
        
        WEB3FuturesOptionsSortKey l_futOpSortKey1 = new WEB3FuturesOptionsSortKey();
        l_futOpSortKey1.ascDesc = "A";
        l_futOpSortKey1.keyItem = "opProductCode";
        WEB3FuturesOptionsSortKey[] futOpSortKeys = {l_futOpSortKey1};
        
        //�Ɖ�敪
        l_request.referenceType = "0";
        //����ԋ敪
        l_request.execType = "0";
        //�����w���敨�I�v�V�����\�[�g�L�[
        l_request.futOpSortKeys = futOpSortKeys;
        //�v���y�[�W�ԍ�
        l_request.pageIndex = "1";
        //�y�[�W���\���s��
        l_request.pageSize = "1";
        //�����R�[�h
        l_request.opProductCode = null;
        
        try
        {
            l_request.validate();
        }
        catch (WEB3BaseException e)
        {
            log.debug("" + e.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * this.�Ɖ�敪 = "1�F��������ꗗ�i��������\�Ȃ��̂̂ݕ\���j" pass
     * date:
     * this.�Ɖ�敪 = "0�F�������Ɖ�"
     * this.����ԋ敪 = �g0�F�����h
     * this.�����w���敨�I�v�V�����\�[�g�L�[.�L�[���� = �����R�[�h 
     * �����w���敨�I�v�V�����\�[�g�L�[.�����^�~�� = �gA�F�����h
     * this.�v���y�[�W�ԍ� = �g1�h
     * this.�y�[�W���\���s�� = �g1�h
     * �����R�[�h  = null
     * �e�X�g�m�F���e:
     * pass
     */
    public void test_validate_0004()
    {
        String STR_METHOD_NAME = "test_validate_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3OptionsExecuteReferenceRequest l_request = new WEB3OptionsExecuteReferenceRequest();
        
        WEB3FuturesOptionsSortKey l_futOpSortKey1 = new WEB3FuturesOptionsSortKey();
        l_futOpSortKey1.ascDesc = "A";
        l_futOpSortKey1.keyItem = "opProductCode";
        WEB3FuturesOptionsSortKey[] futOpSortKeys = {l_futOpSortKey1};
        
        //�Ɖ�敪
        l_request.referenceType = "1";
        //����ԋ敪
        l_request.execType = "0";
        //�����w���敨�I�v�V�����\�[�g�L�[
        l_request.futOpSortKeys = futOpSortKeys;
        //�v���y�[�W�ԍ�
        l_request.pageIndex = "1";
        //�y�[�W���\���s��
        l_request.pageSize = "1";
        //�����R�[�h
        l_request.opProductCode = null;
        
        try
        {
            l_request.validate();
        }
        catch (WEB3BaseException e)
        {
            log.debug("" + e.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * this.����ԋ敪��null ���A  
     * this.����ԋ敪���ȉ��̒l�ȊO�̏ꍇ��O���X���[����B  
     * �h0�F�����h 
     * �h1�F�ꕔ�����h
     * �h2�F�S�������h 
     * date:
     * this.�Ɖ�敪 = �g0�F�������Ɖ� �h
     * this.����ԋ敪 = "3"
     * �e�X�g�m�F���e:
     * BUSINESS_ERROR_00626
     */
    public void test_validate_0006()
    {
        String STR_METHOD_NAME = "test_validate_0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3OptionsExecuteReferenceRequest l_request = new WEB3OptionsExecuteReferenceRequest();
        
        //�Ɖ�敪
        l_request.referenceType = "0";
        //����ԋ敪
        l_request.execType = "3";
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.debug("" + e.getErrorInfo());
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00626, e.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * this.����ԋ敪 =  �h0�F�����h  pass
     * date:
     * this.�Ɖ�敪 = "0�F�������Ɖ�"
     * this.����ԋ敪 = �g0�F�����h
     * this.�����w���敨�I�v�V�����\�[�g�L�[.�L�[���� = �����R�[�h 
     * �����w���敨�I�v�V�����\�[�g�L�[.�����^�~�� = �gA�F�����h
     * this.�v���y�[�W�ԍ� = �g1�h
     * this.�y�[�W���\���s�� = �g1�h
     * �����R�[�h  = null
     * �e�X�g�m�F���e:
     * pass
     */
    public void test_validate_0007()
    {
        String STR_METHOD_NAME = "test_validate_0007()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3OptionsExecuteReferenceRequest l_request = new WEB3OptionsExecuteReferenceRequest();
        
        WEB3FuturesOptionsSortKey l_futOpSortKey1 = new WEB3FuturesOptionsSortKey();
        l_futOpSortKey1.ascDesc = "A";
        l_futOpSortKey1.keyItem = "opProductCode";
        WEB3FuturesOptionsSortKey[] futOpSortKeys = {l_futOpSortKey1};
        
        //�Ɖ�敪
        l_request.referenceType = "0";
        //����ԋ敪
        l_request.execType = "0";
        //�����w���敨�I�v�V�����\�[�g�L�[
        l_request.futOpSortKeys = futOpSortKeys;
        //�v���y�[�W�ԍ�
        l_request.pageIndex = "1";
        //�y�[�W���\���s��
        l_request.pageSize = "1";
        //�����R�[�h
        l_request.opProductCode = null;
        
        try
        {
            l_request.validate();
        }
        catch (WEB3BaseException e)
        {
            log.debug("" + e.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * this.����ԋ敪 =  �h1�F�ꕔ�����h   pass
     * date:
     * this.�Ɖ�敪 = "0�F�������Ɖ�"
     * this.����ԋ敪 = �h1�F�ꕔ�����h
     * this.�����w���敨�I�v�V�����\�[�g�L�[.�L�[���� = �����R�[�h 
     * �����w���敨�I�v�V�����\�[�g�L�[.�����^�~�� = �gA�F�����h
     * this.�v���y�[�W�ԍ� = �g1�h
     * this.�y�[�W���\���s�� = �g1�h
     * �����R�[�h  = null
     * �e�X�g�m�F���e:
     * pass
     */
    public void test_validate_0008()
    {
        String STR_METHOD_NAME = "test_validate_0008()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3OptionsExecuteReferenceRequest l_request = new WEB3OptionsExecuteReferenceRequest();
        
        WEB3FuturesOptionsSortKey l_futOpSortKey1 = new WEB3FuturesOptionsSortKey();
        l_futOpSortKey1.ascDesc = "A";
        l_futOpSortKey1.keyItem = "opProductCode";
        WEB3FuturesOptionsSortKey[] futOpSortKeys = {l_futOpSortKey1};
        
        //�Ɖ�敪
        l_request.referenceType = "0";
        //����ԋ敪
        l_request.execType = "1";
        //�����w���敨�I�v�V�����\�[�g�L�[
        l_request.futOpSortKeys = futOpSortKeys;
        //�v���y�[�W�ԍ�
        l_request.pageIndex = "1";
        //�y�[�W���\���s��
        l_request.pageSize = "1";
        //�����R�[�h
        l_request.opProductCode = null;
        
        try
        {
            l_request.validate();
        }
        catch (WEB3BaseException e)
        {
            log.debug("" + e.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * this.����ԋ敪 = �h2�F�S�������h pass
     * date:
     * this.�Ɖ�敪 = "0�F�������Ɖ�"
     * this.����ԋ敪 = �h2�F�S�������h 
     * this.�����w���敨�I�v�V�����\�[�g�L�[.�L�[���� = �����R�[�h 
     * �����w���敨�I�v�V�����\�[�g�L�[.�����^�~�� = �gA�F�����h
     * this.�v���y�[�W�ԍ� = �g1�h
     * this.�y�[�W���\���s�� = �g1�h
     * �����R�[�h  = null
     * �e�X�g�m�F���e:
     * pass
     */
    public void test_validate_0009()
    {
        String STR_METHOD_NAME = "test_validate_0009()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3OptionsExecuteReferenceRequest l_request = new WEB3OptionsExecuteReferenceRequest();
        
        WEB3FuturesOptionsSortKey l_futOpSortKey1 = new WEB3FuturesOptionsSortKey();
        l_futOpSortKey1.ascDesc = "A";
        l_futOpSortKey1.keyItem = "opProductCode";
        WEB3FuturesOptionsSortKey[] futOpSortKeys = {l_futOpSortKey1};
        
        //�Ɖ�敪
        l_request.referenceType = "0";
        //����ԋ敪
        l_request.execType = "2";
        //�����w���敨�I�v�V�����\�[�g�L�[
        l_request.futOpSortKeys = futOpSortKeys;
        //�v���y�[�W�ԍ�
        l_request.pageIndex = "1";
        //�y�[�W���\���s��
        l_request.pageSize = "1";
        //�����R�[�h
        l_request.opProductCode = null;
        
        try
        {
            l_request.validate();
        }
        catch (WEB3BaseException e)
        {
            log.debug("" + e.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * this.�����w���敨�I�v�V�����\�[�g�L�[�� 
     * �@@�@@�@@�@@null�̒l�ł���Η�O���X���[����B  
     * date:
     * �����w���敨�I�v�V�����\�[�g�L�[�`�F�b�N = null
     * �e�X�g�m�F���e:
     * BUSINESS_ERROR_00231
     */
    public void test_validate_0010()
    {
        String STR_METHOD_NAME = "test_validate_0010()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3OptionsExecuteReferenceRequest l_request = new WEB3OptionsExecuteReferenceRequest();
        
        //�Ɖ�敪
        l_request.referenceType = "0";
        //����ԋ敪
        l_request.execType = "1";
        //�����w���敨�I�v�V�����\�[�g�L�[
        l_request.futOpSortKeys = null;
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.debug("" + e.getErrorInfo());
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00231, e.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * this.�����w���敨�I�v�V�����\�[�g�L�[�̗v�f���� 
     * �O�ł���Η�O���X���[����B
     * date:
     * �����w���敨�I�v�V�����\�[�g�L�[�`�F�b�N.length = 0
     * �e�X�g�m�F���e:
     * BUSINESS_ERROR_00232
     */
    public void test_validate_0011()
    {
        String STR_METHOD_NAME = "test_validate_0011()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3OptionsExecuteReferenceRequest l_request = new WEB3OptionsExecuteReferenceRequest();
        
        WEB3FuturesOptionsSortKey[] futOpSortKeys = {};
        
        //�Ɖ�敪
        l_request.referenceType = "0";
        //����ԋ敪
        l_request.execType = "1";
        //�����w���敨�I�v�V�����\�[�g�L�[
        l_request.futOpSortKeys = futOpSortKeys;
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.debug("" + e.getErrorInfo());
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00232, e.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * �����w���敨�I�v�V�����\�[�g�L�[.�L�[���ڂ�null�̒l�ł���Η�O���X���[����B
     * date:
     * �����w���敨�I�v�V�����\�[�g�L�[�`�F�b�N.�L�[���� = null
     * �e�X�g�m�F���e:
     * BUSINESS_ERROR_00085
     */
    public void test_validate_0012()
    {
        String STR_METHOD_NAME = "test_validate_0012()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3OptionsExecuteReferenceRequest l_request = new WEB3OptionsExecuteReferenceRequest();

        WEB3FuturesOptionsSortKey l_futOpSortKey1 = new WEB3FuturesOptionsSortKey();
        l_futOpSortKey1.ascDesc = "A";
        WEB3FuturesOptionsSortKey[] futOpSortKeys = {l_futOpSortKey1};
        
        //�Ɖ�敪
        l_request.referenceType = "0";
        //����ԋ敪
        l_request.execType = "1";
        //�����w���敨�I�v�V�����\�[�g�L�[
        l_request.futOpSortKeys = futOpSortKeys;
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.debug("" + e.getErrorInfo());
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00085, e.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * �����w���敨�I�v�V�����\�[�g�L�[.�L�[���ڂɈȉ��̍��ږ��ȊO�̒l��
     * �����R�[�h 
     * ����s��
     * ����敪
     * �������� 
     * ������
     * �����L������ 
     * date:
     * �����w���敨�I�v�V�����\�[�g�L�[�`�F�b�N.�L�[���� = "����"
     * �e�X�g�m�F���e:
     * BUSINESS_ERROR_00086
     */
    public void test_validate_0013()
    {
        String STR_METHOD_NAME = "test_validate_0013()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3OptionsExecuteReferenceRequest l_request = new WEB3OptionsExecuteReferenceRequest();

        WEB3FuturesOptionsSortKey l_futOpSortKey1 = new WEB3FuturesOptionsSortKey();
        l_futOpSortKey1.ascDesc = "A";
        l_futOpSortKey1.keyItem = "����";
        WEB3FuturesOptionsSortKey[] futOpSortKeys = {l_futOpSortKey1};
        
        //�Ɖ�敪
        l_request.referenceType = "0";
        //����ԋ敪
        l_request.execType = "1";
        //�����w���敨�I�v�V�����\�[�g�L�[
        l_request.futOpSortKeys = futOpSortKeys;
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.debug("" + e.getErrorInfo());
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00086, e.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * �����w���敨�I�v�V�����\�[�g�L�[.�����^�~����null�̒l�ł���Η�O���X���[����B 
     * date:
     * �����w���敨�I�v�V�����\�[�g�L�[.�����^�~�� = null
     * �e�X�g�m�F���e:
     * BUSINESS_ERROR_00087
     */
    public void test_validate_0014()
    {
        String STR_METHOD_NAME = "test_validate_0014()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3OptionsExecuteReferenceRequest l_request = new WEB3OptionsExecuteReferenceRequest();

        WEB3FuturesOptionsSortKey l_futOpSortKey1 = new WEB3FuturesOptionsSortKey();
        l_futOpSortKey1.keyItem = "opProductCode";
        WEB3FuturesOptionsSortKey[] futOpSortKeys = {l_futOpSortKey1};
        
        //�Ɖ�敪
        l_request.referenceType = "0";
        //����ԋ敪
        l_request.execType = "1";
        //�����w���敨�I�v�V�����\�[�g�L�[
        l_request.futOpSortKeys = futOpSortKeys;
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.debug("" + e.getErrorInfo());
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00087, e.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * �����w���敨�I�v�V�����\�[�g�L�[.�����^�~�����ȉ��̒l�ȊO�̏ꍇ��O���X���[����B 
     * �hA�F�����h 
     * �hD�F�~���h 
     * date:
     * �����w���敨�I�v�V�����\�[�g�L�[.�����^�~�� = "b"
     * �e�X�g�m�F���e:
     * BUSINESS_ERROR_00088
     */
    public void test_validate_0015()
    {
        String STR_METHOD_NAME = "test_validate_0015()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3OptionsExecuteReferenceRequest l_request = new WEB3OptionsExecuteReferenceRequest();

        WEB3FuturesOptionsSortKey l_futOpSortKey1 = new WEB3FuturesOptionsSortKey();
        l_futOpSortKey1.ascDesc = "b";
        l_futOpSortKey1.keyItem = "opProductCode";
        WEB3FuturesOptionsSortKey[] futOpSortKeys = {l_futOpSortKey1};
        
        //�Ɖ�敪
        l_request.referenceType = "0";
        //����ԋ敪
        l_request.execType = "1";
        //�����w���敨�I�v�V�����\�[�g�L�[
        l_request.futOpSortKeys = futOpSortKeys;
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.debug("" + e.getErrorInfo());
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00088, e.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * �����w���敨�I�v�V�����\�[�g�L�[.�L�[���� = �����R�[�h  pass
     * date:
     * this.�Ɖ�敪 = "0�F�������Ɖ�"
     * this.����ԋ敪 = "0�F�����" 
     * this.�����w���敨�I�v�V�����\�[�g�L�[.�L�[���� = �����R�[�h 
     * �����w���敨�I�v�V�����\�[�g�L�[.�����^�~�� = �gA�F�����h
     * this.�v���y�[�W�ԍ� = �g1�h
     * this.�y�[�W���\���s�� = �g1�h
     * �����R�[�h  = null
     * �e�X�g�m�F���e:
     * pass
     */
    public void test_validate_0016()
    {
        String STR_METHOD_NAME = "test_validate_0016()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3OptionsExecuteReferenceRequest l_request = new WEB3OptionsExecuteReferenceRequest();
        
        WEB3FuturesOptionsSortKey l_futOpSortKey1 = new WEB3FuturesOptionsSortKey();
        l_futOpSortKey1.ascDesc = "A";
        l_futOpSortKey1.keyItem = "opProductCode";
        WEB3FuturesOptionsSortKey[] futOpSortKeys = {l_futOpSortKey1};
        
        //�Ɖ�敪
        l_request.referenceType = "0";
        //����ԋ敪
        l_request.execType = "0";
        //�����w���敨�I�v�V�����\�[�g�L�[
        l_request.futOpSortKeys = futOpSortKeys;
        //�v���y�[�W�ԍ�
        l_request.pageIndex = "1";
        //�y�[�W���\���s��
        l_request.pageSize = "1";
        //�����R�[�h
        l_request.opProductCode = null;
        
        try
        {
            l_request.validate();
        }
        catch (WEB3BaseException e)
        {
            log.debug("" + e.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * �����w���敨�I�v�V�����\�[�g�L�[.�L�[���� = ����s��   pass
     * date:
     * this.�Ɖ�敪 = "0�F�������Ɖ�"
     * this.����ԋ敪 = "0�F�����" 
     * this.�����w���敨�I�v�V�����\�[�g�L�[.�L�[���� = ����s�� 
     * �����w���敨�I�v�V�����\�[�g�L�[.�����^�~�� = �gA�F�����h
     * this.�v���y�[�W�ԍ� = �g1�h
     * this.�y�[�W���\���s�� = �g1�h
     * �����R�[�h  = null
     * �e�X�g�m�F���e:
     * pass
     */
    public void test_validate_0017()
    {
        String STR_METHOD_NAME = "test_validate_0017()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3OptionsExecuteReferenceRequest l_request = new WEB3OptionsExecuteReferenceRequest();
        
        WEB3FuturesOptionsSortKey l_futOpSortKey1 = new WEB3FuturesOptionsSortKey();
        l_futOpSortKey1.ascDesc = "A";
        l_futOpSortKey1.keyItem = "marketCode";
        WEB3FuturesOptionsSortKey[] futOpSortKeys = {l_futOpSortKey1};
        
        //�Ɖ�敪
        l_request.referenceType = "0";
        //����ԋ敪
        l_request.execType = "0";
        //�����w���敨�I�v�V�����\�[�g�L�[
        l_request.futOpSortKeys = futOpSortKeys;
        //�v���y�[�W�ԍ�
        l_request.pageIndex = "1";
        //�y�[�W���\���s��
        l_request.pageSize = "1";
        //�����R�[�h
        l_request.opProductCode = null;
        
        try
        {
            l_request.validate();
        }
        catch (WEB3BaseException e)
        {
            log.debug("" + e.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * �����w���敨�I�v�V�����\�[�g�L�[.�L�[���� = ����敪  pass
     * date:
     * this.�Ɖ�敪 = "0�F�������Ɖ�"
     * this.����ԋ敪 = "0�F�����" 
     * this.�����w���敨�I�v�V�����\�[�g�L�[.�L�[���� = ����敪
     * �����w���敨�I�v�V�����\�[�g�L�[.�����^�~�� = �gA�F�����h
     * this.�v���y�[�W�ԍ� = �g1�h
     * this.�y�[�W���\���s�� = �g1�h
     * �����R�[�h  = null
     * �e�X�g�m�F���e:
     * pass
     */
    public void test_validate_0018()
    {
        String STR_METHOD_NAME = "test_validate_0018()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3OptionsExecuteReferenceRequest l_request = new WEB3OptionsExecuteReferenceRequest();
        
        WEB3FuturesOptionsSortKey l_futOpSortKey1 = new WEB3FuturesOptionsSortKey();
        l_futOpSortKey1.ascDesc = "A";
        l_futOpSortKey1.keyItem = "tradingType";
        WEB3FuturesOptionsSortKey[] futOpSortKeys = {l_futOpSortKey1};
        
        //�Ɖ�敪
        l_request.referenceType = "0";
        //����ԋ敪
        l_request.execType = "0";
        //�����w���敨�I�v�V�����\�[�g�L�[
        l_request.futOpSortKeys = futOpSortKeys;
        //�v���y�[�W�ԍ�
        l_request.pageIndex = "1";
        //�y�[�W���\���s��
        l_request.pageSize = "1";
        //�����R�[�h
        l_request.opProductCode = null;
        
        try
        {
            l_request.validate();
        }
        catch (WEB3BaseException e)
        {
            log.debug("" + e.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * �����w���敨�I�v�V�����\�[�g�L�[.�L�[���� = ��������  pass
     * date:
     * this.�Ɖ�敪 = "0�F�������Ɖ�"
     * this.����ԋ敪 = "0�F�����" 
     * �����w���敨�I�v�V�����\�[�g�L�[.�L�[���� = ��������
     * �����w���敨�I�v�V�����\�[�g�L�[.�����^�~�� = �gA�F�����h
     * this.�v���y�[�W�ԍ� = �g1�h
     * this.�y�[�W���\���s�� = �g1�h
     * �����R�[�h  = null
     * �e�X�g�m�F���e:
     * pass
     */
    public void test_validate_0019()
    {
        String STR_METHOD_NAME = "test_validate_0019()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3OptionsExecuteReferenceRequest l_request = new WEB3OptionsExecuteReferenceRequest();
        
        WEB3FuturesOptionsSortKey l_futOpSortKey1 = new WEB3FuturesOptionsSortKey();
        l_futOpSortKey1.ascDesc = "A";
        l_futOpSortKey1.keyItem = "orderDate";
        WEB3FuturesOptionsSortKey[] futOpSortKeys = {l_futOpSortKey1};
        
        //�Ɖ�敪
        l_request.referenceType = "0";
        //����ԋ敪
        l_request.execType = "0";
        //�����w���敨�I�v�V�����\�[�g�L�[
        l_request.futOpSortKeys = futOpSortKeys;
        //�v���y�[�W�ԍ�
        l_request.pageIndex = "1";
        //�y�[�W���\���s��
        l_request.pageSize = "1";
        //�����R�[�h
        l_request.opProductCode = null;
        
        try
        {
            l_request.validate();
        }
        catch (WEB3BaseException e)
        {
            log.debug("" + e.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * �����w���敨�I�v�V�����\�[�g�L�[.�L�[���� = ������   pass
     * date:
     * this.�Ɖ�敪 = "0�F�������Ɖ�"
     * this.����ԋ敪 = "0�F�����" 
     * �����w���敨�I�v�V�����\�[�g�L�[.�L�[���� = ������
     * �����w���敨�I�v�V�����\�[�g�L�[.�����^�~�� = �gA�F�����h
     * this.�v���y�[�W�ԍ� = �g1�h
     * this.�y�[�W���\���s�� = �g1�h
     * �����R�[�h  = null
     * �e�X�g�m�F���e:
     * pass
     */
    public void test_validate_0020()
    {
        String STR_METHOD_NAME = "test_validate_0020()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3OptionsExecuteReferenceRequest l_request = new WEB3OptionsExecuteReferenceRequest();
        
        WEB3FuturesOptionsSortKey l_futOpSortKey1 = new WEB3FuturesOptionsSortKey();
        l_futOpSortKey1.ascDesc = "A";
        l_futOpSortKey1.keyItem = "orderBizDate";
        WEB3FuturesOptionsSortKey[] futOpSortKeys = {l_futOpSortKey1};
        
        //�Ɖ�敪
        l_request.referenceType = "0";
        //����ԋ敪
        l_request.execType = "0";
        //�����w���敨�I�v�V�����\�[�g�L�[
        l_request.futOpSortKeys = futOpSortKeys;
        //�v���y�[�W�ԍ�
        l_request.pageIndex = "1";
        //�y�[�W���\���s��
        l_request.pageSize = "1";
        //�����R�[�h
        l_request.opProductCode = null;
        
        try
        {
            l_request.validate();
        }
        catch (WEB3BaseException e)
        {
            log.debug("" + e.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * �����w���敨�I�v�V�����\�[�g�L�[.�L�[���� = �����L������    pass
     * date:
     * this.�Ɖ�敪 = "0�F�������Ɖ�"
     * this.����ԋ敪 = "0�F�����" 
     * �����w���敨�I�v�V�����\�[�g�L�[.�L�[���� = �����L������
     * �����w���敨�I�v�V�����\�[�g�L�[.�����^�~�� = �gA�F�����h
     * this.�v���y�[�W�ԍ� = �g1�h
     * this.�y�[�W���\���s�� = �g1�h
     * �����R�[�h  = null
     * �e�X�g�m�F���e:
     * pass
     */
    public void test_validate_0021()
    {
        String STR_METHOD_NAME = "test_validate_0021()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3OptionsExecuteReferenceRequest l_request = new WEB3OptionsExecuteReferenceRequest();
        
        WEB3FuturesOptionsSortKey l_futOpSortKey1 = new WEB3FuturesOptionsSortKey();
        l_futOpSortKey1.ascDesc = "A";
        l_futOpSortKey1.keyItem = "expirationDate";
        WEB3FuturesOptionsSortKey[] futOpSortKeys = {l_futOpSortKey1};
        
        //�Ɖ�敪
        l_request.referenceType = "0";
        //����ԋ敪
        l_request.execType = "0";
        //�����w���敨�I�v�V�����\�[�g�L�[
        l_request.futOpSortKeys = futOpSortKeys;
        //�v���y�[�W�ԍ�
        l_request.pageIndex = "1";
        //�y�[�W���\���s��
        l_request.pageSize = "1";
        //�����R�[�h
        l_request.opProductCode = null;
        
        try
        {
            l_request.validate();
        }
        catch (WEB3BaseException e)
        {
            log.debug("" + e.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * �����w���敨�I�v�V�����\�[�g�L�[.�L�[���� = �����L������    pass
     * date:
     * this.�Ɖ�敪 = "0�F�������Ɖ�"
     * this.����ԋ敪 = "0�F�����" 
     * �����w���敨�I�v�V�����\�[�g�L�[.�L�[���� = �����L������
     * �����w���敨�I�v�V�����\�[�g�L�[.�����^�~�� = �gD�F�~���h
     * this.�v���y�[�W�ԍ� = �g1�h
     * this.�y�[�W���\���s�� = �g1�h
     * �����R�[�h  = null
     * �e�X�g�m�F���e:
     * pass
     */
    public void test_validate_0022()
    {
        String STR_METHOD_NAME = "test_validate_0022()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3OptionsExecuteReferenceRequest l_request = new WEB3OptionsExecuteReferenceRequest();
        
        WEB3FuturesOptionsSortKey l_futOpSortKey1 = new WEB3FuturesOptionsSortKey();
        l_futOpSortKey1.ascDesc = "D";
        l_futOpSortKey1.keyItem = "expirationDate";
        WEB3FuturesOptionsSortKey[] futOpSortKeys = {l_futOpSortKey1};
        
        //�Ɖ�敪
        l_request.referenceType = "0";
        //����ԋ敪
        l_request.execType = "0";
        //�����w���敨�I�v�V�����\�[�g�L�[
        l_request.futOpSortKeys = futOpSortKeys;
        //�v���y�[�W�ԍ�
        l_request.pageIndex = "1";
        //�y�[�W���\���s��
        l_request.pageSize = "1";
        //�����R�[�h
        l_request.opProductCode = null;
        
        try
        {
            l_request.validate();
        }
        catch (WEB3BaseException e)
        {
            log.debug("" + e.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * this.�v���y�[�W�ԍ���null�̒l�ł���Η�O���X���[����B
     * date:
     * this.�v���y�[�W�ԍ� = null
     * �e�X�g�m�F���e:
     * BUSINESS_ERROR_00089
     */
    public void test_validate_0023()
    {
        String STR_METHOD_NAME = "test_validate_0022()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3OptionsExecuteReferenceRequest l_request = new WEB3OptionsExecuteReferenceRequest();
        
        WEB3FuturesOptionsSortKey l_futOpSortKey1 = new WEB3FuturesOptionsSortKey();
        l_futOpSortKey1.ascDesc = "D";
        l_futOpSortKey1.keyItem = "expirationDate";
        WEB3FuturesOptionsSortKey[] futOpSortKeys = {l_futOpSortKey1};
        
        //�Ɖ�敪
        l_request.referenceType = "0";
        //����ԋ敪
        l_request.execType = "0";
        //�����w���敨�I�v�V�����\�[�g�L�[
        l_request.futOpSortKeys = futOpSortKeys;
        //�v���y�[�W�ԍ�
        l_request.pageIndex = null;
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.debug("" + e.getErrorInfo());
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00089, e.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * this.�v���y�[�W�ԍ��������ȊO�̒l�ł���Η�O���X���[����B
     * date:
     * this.�v���y�[�W�ԍ� = avc
     * �e�X�g�m�F���e:
     * BUSINESS_ERROR_00090
     */
    public void test_validate_0024()
    {
        String STR_METHOD_NAME = "test_validate_0024()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3OptionsExecuteReferenceRequest l_request = new WEB3OptionsExecuteReferenceRequest();
        
        WEB3FuturesOptionsSortKey l_futOpSortKey1 = new WEB3FuturesOptionsSortKey();
        l_futOpSortKey1.ascDesc = "D";
        l_futOpSortKey1.keyItem = "expirationDate";
        WEB3FuturesOptionsSortKey[] futOpSortKeys = {l_futOpSortKey1};
        
        //�Ɖ�敪
        l_request.referenceType = "0";
        //����ԋ敪
        l_request.execType = "0";
        //�����w���敨�I�v�V�����\�[�g�L�[
        l_request.futOpSortKeys = futOpSortKeys;
        //�v���y�[�W�ԍ�
        l_request.pageIndex = "avc";
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.debug("" + e.getErrorInfo());
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00090, e.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * this.�v���y�[�W�ԍ� = 1  
     * this.�y�[�W���\���s�� = 1  pass
     * date:
     * this.�Ɖ�敪 = "0�F�������Ɖ�"
     * this.����ԋ敪 = "0�F�����" 
     * �����w���敨�I�v�V�����\�[�g�L�[.�L�[���� = �����L������
     * �����w���敨�I�v�V�����\�[�g�L�[.�����^�~�� = �gD�F�~���h
     * this.�v���y�[�W�ԍ� = �g1�h
     * this.�y�[�W���\���s�� = �g1�h
     * �����R�[�h  = null
     * �e�X�g�m�F���e:
     * pass
     */
    public void test_validate_0025()
    {
        String STR_METHOD_NAME = "test_validate_0025()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3OptionsExecuteReferenceRequest l_request = new WEB3OptionsExecuteReferenceRequest();
        
        WEB3FuturesOptionsSortKey l_futOpSortKey1 = new WEB3FuturesOptionsSortKey();
        l_futOpSortKey1.ascDesc = "D";
        l_futOpSortKey1.keyItem = "expirationDate";
        WEB3FuturesOptionsSortKey[] futOpSortKeys = {l_futOpSortKey1};
        
        //�Ɖ�敪
        l_request.referenceType = "0";
        //����ԋ敪
        l_request.execType = "0";
        //�����w���敨�I�v�V�����\�[�g�L�[
        l_request.futOpSortKeys = futOpSortKeys;
        //�v���y�[�W�ԍ�
        l_request.pageIndex = "1";
        //�y�[�W���\���s��
        l_request.pageSize = "1";
        //�����R�[�h
        l_request.opProductCode = null;
        
        try
        {
            l_request.validate();
        }
        catch (WEB3BaseException e)
        {
            log.debug("" + e.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * this.�y�[�W���\���s����null�̒l�ł���Η�O���X���[����B
     * date:
     * this.�y�[�W���\���s�� = null
     * �e�X�g�m�F���e:
     * BUSINESS_ERROR_00091
     */
    public void test_validate_0026()
    {
        String STR_METHOD_NAME = "test_validate_0026()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3OptionsExecuteReferenceRequest l_request = new WEB3OptionsExecuteReferenceRequest();
        
        WEB3FuturesOptionsSortKey l_futOpSortKey1 = new WEB3FuturesOptionsSortKey();
        l_futOpSortKey1.ascDesc = "D";
        l_futOpSortKey1.keyItem = "expirationDate";
        WEB3FuturesOptionsSortKey[] futOpSortKeys = {l_futOpSortKey1};
        
        //�Ɖ�敪
        l_request.referenceType = "0";
        //����ԋ敪
        l_request.execType = "0";
        //�����w���敨�I�v�V�����\�[�g�L�[
        l_request.futOpSortKeys = futOpSortKeys;
        //�v���y�[�W�ԍ�
        l_request.pageIndex = "1";
        //�y�[�W���\���s�� 
        l_request.pageSize = null;
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.debug("" + e.getErrorInfo());
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00091, e.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    
    /**
     * this.�y�[�W���\���s���������ȊO�̒l�ł���Η�O���X���[����B
     * date:
     * this.�y�[�W���\���s�� =abcx
     * �e�X�g�m�F���e:
     * BUSINESS_ERROR_00092
     */
    public void test_validate_0027()
    {
        String STR_METHOD_NAME = "test_validate_0027()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3OptionsExecuteReferenceRequest l_request = new WEB3OptionsExecuteReferenceRequest();
        
        WEB3FuturesOptionsSortKey l_futOpSortKey1 = new WEB3FuturesOptionsSortKey();
        l_futOpSortKey1.ascDesc = "D";
        l_futOpSortKey1.keyItem = "expirationDate";
        WEB3FuturesOptionsSortKey[] futOpSortKeys = {l_futOpSortKey1};
        
        //�Ɖ�敪
        l_request.referenceType = "0";
        //����ԋ敪
        l_request.execType = "0";
        //�����w���敨�I�v�V�����\�[�g�L�[
        l_request.futOpSortKeys = futOpSortKeys;
        //�v���y�[�W�ԍ�
        l_request.pageIndex = "1";
        //�y�[�W���\���s�� =abcx
        l_request.pageSize = "abcx";
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.debug("" + e.getErrorInfo());
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00092, e.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    // ���������敪�`�F�b�N
    //�@@this.���������敪��null���A
    //�@@this.���������敪���ȉ��̒l�ȊO�̏ꍇ��O���X���[����B
    //�@@�@@�@@�E�h0�F�w��Ȃ��h
    //�@@�@@�@@�E�h1�F�t�w�l�h
    //�@@�@@�@@�E�h2�FW�w�l�h
    //this.���������敪��null ==3 �ُ�
    public void testValidateCase1()
    {
        String STR_METHOD_NAME = "testValidateCase1()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3OptionsExecuteReferenceRequest l_request = new WEB3OptionsExecuteReferenceRequest();
        
        WEB3FuturesOptionsSortKey[] futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
        WEB3FuturesOptionsSortKey l_sortKey = new WEB3FuturesOptionsSortKey();
        l_sortKey.ascDesc = "A";
        l_sortKey.keyItem = WEB3IfoKeyItemDef.PRODUCTCODE;
        futOpSortKeys[0] = l_sortKey;
        l_request.orderCondType = "3";
        l_request.referenceType = "1";
        l_request.futOpSortKeys = futOpSortKeys;
        l_request.pageIndex = "1";
        l_request.pageSize = "1";
        l_request.marketCode = "0";
        l_request.targetProductCode = "1";
        l_request.delivaryMonth = "1";
        l_request.opProductType = "1";
        l_request.strikePrice = "1";
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.debug("" + e.getErrorInfo());
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00212, e.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //this.���������敪��null ==0 ���팋��
    public void testValidateCase2()
    {
        String STR_METHOD_NAME = "testValidateCase2()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3OptionsExecuteReferenceRequest l_request = new WEB3OptionsExecuteReferenceRequest();
        
        WEB3FuturesOptionsSortKey[] futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
        WEB3FuturesOptionsSortKey l_sortKey = new WEB3FuturesOptionsSortKey();
        l_sortKey.ascDesc = "A";
        l_sortKey.keyItem = WEB3IfoKeyItemDef.PRODUCTCODE;
        futOpSortKeys[0] = l_sortKey;
        l_request.orderCondType = "0";
        l_request.referenceType = "1";
        l_request.futOpSortKeys = futOpSortKeys;
        l_request.pageIndex = "1";
        l_request.pageSize = "1";
        l_request.marketCode = "0";
        l_request.targetProductCode = "1";
        l_request.delivaryMonth = "1";
        l_request.opProductType = "1";
        l_request.strikePrice = "1";
        
        try
        {
            l_request.validate();
        }
        catch (WEB3BaseException e)
        {
            log.debug("" + e.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //this.���������敪��null ==1 ���팋��
    public void testValidateCase3()
    {
        String STR_METHOD_NAME = "testValidateCase3()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3OptionsExecuteReferenceRequest l_request = new WEB3OptionsExecuteReferenceRequest();
        
        WEB3FuturesOptionsSortKey[] futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
        WEB3FuturesOptionsSortKey l_sortKey = new WEB3FuturesOptionsSortKey();
        l_sortKey.ascDesc = "A";
        l_sortKey.keyItem = WEB3IfoKeyItemDef.PRODUCTCODE;
        futOpSortKeys[0] = l_sortKey;
        l_request.orderCondType = "1";
        l_request.referenceType = "1";
        l_request.futOpSortKeys = futOpSortKeys;
        l_request.pageIndex = "1";
        l_request.pageSize = "1";
        l_request.marketCode = "0";
        l_request.targetProductCode = "1";
        l_request.delivaryMonth = "1";
        l_request.opProductType = "1";
        l_request.strikePrice = "1";
        
        try
        {
            l_request.validate();
        }
        catch (WEB3BaseException e)
        {
            log.debug("" + e.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //this.���������敪��null ==2 ���팋��
    public void testValidateCase4()
    {
        String STR_METHOD_NAME = "testValidateCase4()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3OptionsExecuteReferenceRequest l_request = new WEB3OptionsExecuteReferenceRequest();
        
        WEB3FuturesOptionsSortKey[] futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
        WEB3FuturesOptionsSortKey l_sortKey = new WEB3FuturesOptionsSortKey();
        l_sortKey.ascDesc = "A";
        l_sortKey.keyItem = WEB3IfoKeyItemDef.PRODUCTCODE;
        futOpSortKeys[0] = l_sortKey;
        l_request.orderCondType = "2";
        l_request.referenceType = "1";
        l_request.futOpSortKeys = futOpSortKeys;
        l_request.pageIndex = "1";
        l_request.pageSize = "1";
        l_request.marketCode = "0";
        l_request.targetProductCode = "1";
        l_request.delivaryMonth = "1";
        l_request.opProductType = "1";
        l_request.strikePrice = "1";
        
        try
        {
            l_request.validate();
        }
        catch (WEB3BaseException e)
        {
            log.debug("" + e.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //this.���������敪==null ���팋��
    public void testValidateCase5()
    {
        String STR_METHOD_NAME = "testValidateCase5()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3OptionsExecuteReferenceRequest l_request = new WEB3OptionsExecuteReferenceRequest();
        
        WEB3FuturesOptionsSortKey[] futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
        WEB3FuturesOptionsSortKey l_sortKey = new WEB3FuturesOptionsSortKey();
        l_sortKey.ascDesc = "A";
        l_sortKey.keyItem = WEB3IfoKeyItemDef.PRODUCTCODE;
        futOpSortKeys[0] = l_sortKey;
        l_request.orderCondType = null;
        l_request.referenceType = "1";
        l_request.futOpSortKeys = futOpSortKeys;
        l_request.pageIndex = "1";
        l_request.pageSize = "1";
        l_request.marketCode = "0";
        l_request.targetProductCode = "1";
        l_request.delivaryMonth = "1";
        l_request.opProductType = "1";
        l_request.strikePrice = "1";
        
        try
        {
            l_request.validate();
        }
        catch (WEB3BaseException e)
        {
            log.debug("" + e.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    
}
@
