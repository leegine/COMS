head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.00.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminDirSecAPMngListRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : WEB3AdminDirSecAPMngListRequestTest.java
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/07/23 �k�v�u(���u) �V�K�쐬 ���f�� 132
*/
package webbroker3.dirsec.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminDirSecAPMngListRequestTest extends TestBaseForMock
{
    WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3AdminDirSecAPMngListRequestTest.class);
        
    WEB3AdminDirSecAPMngListRequest l_request = null;
        
    public WEB3AdminDirSecAPMngListRequestTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        l_request = new WEB3AdminDirSecAPMngListRequest();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    //�P�j�y�[�W���\���s���`�F�b�N
    //�@@�P�|�P�jthis.�y�[�W���\���s�� == null�̏ꍇ
    //�@@�@@�@@�@@�u�y�[�W���\���s���̓��͂��s���ł��B�v�̗�O���X���[����B
    public void testValidate_C0001()
    {
        final String STR_METHOD_NAME = "testValidate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.pageSize = null;
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00091, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //�P�|�Q�jthis.�y�[�W���\���s�������p�����ȊO�̒l�ł������ꍇ�A
    //�@@�@@�@@�@@�u�y�[�W���\���s���������ȊO�̒l�ł��B�v�̗�O���X���[����
    public void testValidate_C0002()
    {
        final String STR_METHOD_NAME = "testValidate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.pageSize = "aa";
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00092, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //�P�|�R�jthis.�y�[�W���\���s�� <= 0�ł������ꍇ�A
    //�@@�@@�@@�u�y�[�W���\���s���̒l��0�ȉ��ł��B�v�̗�O���X���[����B
    public void testValidate_C0003()
    {
        final String STR_METHOD_NAME = "testValidate_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.pageSize = "0";
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00617, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //�Q�j�\���ԍ��`�F�b�N
    //�@@�Q�|�P�jthis.�\���y�[�W�ԍ� == null�̏ꍇ�A
    //�@@�@@�@@�@@�u�v���y�[�W�ԍ������w��ł��B�v�̗�O���X���[����B
    public void testValidate_C0004()
    {
        final String STR_METHOD_NAME = "testValidate_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.pageSize = "1111";
            l_request.pageIndex = null;
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00089, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //�Q�|�Q�jthis.�\���y�[�W�ԍ������p�����ȊO�̒l�ł������ꍇ�A
    //�@@�@@�@@�u�v���y�[�W�ԍ��������ȊO�̒l�ł��B�v�̗�O���X���[����B
    public void testValidate_C0005()
    {
        final String STR_METHOD_NAME = "testValidate_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.pageSize = "1111";
            l_request.pageIndex = "aaa";
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00090, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //�Q�|�R�jthis.�\���y�[�W�ԍ� <= 0�ł������ꍇ�A
    //�@@�u�v���y�[�W�ԍ��̒l��0�ȉ��ł��B�v�̗�O���X���[����B
    public void testValidate_C0006()
    {
        final String STR_METHOD_NAME = "testValidate_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.pageSize = "1111";
            l_request.pageIndex = "0";
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00616, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //�R�j�@@�\�[�g�L�[�`�F�b�N
    //�@@�R�|�P�jthis.�\�[�g�L�[ == null�ł������ꍇ
    //�@@�@@�@@�u�\�[�g�L�[��null�v�̗�O���X���[����B
    public void testValidate_C0007()
    {
        final String STR_METHOD_NAME = "testValidate_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.pageSize = "1111";
            l_request.pageIndex = "1111";
            l_request.sortKeys = null;
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00231, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //�R�|�Q�jthis.�\�[�g�L�[.length == 0�������ꍇ
    //�@@�u�\�[�g�L�[.�v�f����0�v�̗�O���X���[����B
    public void testValidate_C0008()
    {
        final String STR_METHOD_NAME = "testValidate_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.pageSize = "1111";
            l_request.pageIndex = "1111";
            l_request.sortKeys =new WEB3AdminDirSecAPMngForcedStartSortKey[]{};
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00232, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //�R�|�R�jthis.�\�[�g�L�[�̑S�v�f�ɑ΂��ĉ��L�̃`�F�b�N���s���B
    //�R�|�R�|�P�j�\�[�g�L�[.validate()���R�[������B
    //��1���f�o���ُ�
    public void testValidate_C0009()
    {
        final String STR_METHOD_NAME = "testValidate_C0009()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.pageSize = "1111";
            l_request.pageIndex = "1111";
            l_request.sortKeys =new WEB3AdminDirSecAPMngForcedStartSortKey[]{
                new WEB3AdminDirSecAPMngForcedStartSortKey(),
                new WEB3AdminDirSecAPMngForcedStartSortKey(),
                new WEB3AdminDirSecAPMngForcedStartSortKey()};
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00085, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //�R�|�R�jthis.�\�[�g�L�[�̑S�v�f�ɑ΂��ĉ��L�̃`�F�b�N���s���B
    //�R�|�R�|�P�j�\�[�g�L�[.validate()���R�[������B
    //��2���f�o���ُ�
    public void testValidate_C0010()
    {
        final String STR_METHOD_NAME = "testValidate_C0010()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.pageSize = "1111";
            l_request.pageIndex = "1111";
            l_request.sortKeys =new WEB3AdminDirSecAPMngForcedStartSortKey[]{
                new WEB3AdminDirSecAPMngForcedStartSortKey(),
                new WEB3AdminDirSecAPMngForcedStartSortKey(),
                new WEB3AdminDirSecAPMngForcedStartSortKey()};
            l_request.sortKeys[0].keyItem = "1111";
            l_request.sortKeys[0].ascDesc = WEB3AscDescDef.ASC;
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex)
        {
            assertEquals(l_request.sortKeys[0].ascDesc, WEB3AscDescDef.ASC);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00085, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //�R�|�R�jthis.�\�[�g�L�[�̑S�v�f�ɑ΂��ĉ��L�̃`�F�b�N���s���B
    //�R�|�R�|�P�j�\�[�g�L�[.validate()���R�[������B
    //��3���f�o���ُ�
    public void testValidate_C0011()
    {
        final String STR_METHOD_NAME = "testValidate_C0011()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.pageSize = "1111";
            l_request.pageIndex = "1111";
            l_request.sortKeys =new WEB3AdminDirSecAPMngForcedStartSortKey[]{
                new WEB3AdminDirSecAPMngForcedStartSortKey(),
                new WEB3AdminDirSecAPMngForcedStartSortKey(),
                new WEB3AdminDirSecAPMngForcedStartSortKey()};
            l_request.sortKeys[0].keyItem = "1111";
            l_request.sortKeys[0].ascDesc = WEB3AscDescDef.ASC;
            l_request.sortKeys[1].keyItem = "1111";
            l_request.sortKeys[1].ascDesc = WEB3AscDescDef.ASC;
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex)
        {
            assertEquals(l_request.sortKeys[1].ascDesc, WEB3AscDescDef.ASC);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00085, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //normalcase
    public void testValidate_C0012()
    {
        final String STR_METHOD_NAME = "testValidate_C0012()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.pageSize = "1111";
            l_request.pageIndex = "1111";
            l_request.sortKeys =new WEB3AdminDirSecAPMngForcedStartSortKey[]{
                new WEB3AdminDirSecAPMngForcedStartSortKey(),
                new WEB3AdminDirSecAPMngForcedStartSortKey(),
                new WEB3AdminDirSecAPMngForcedStartSortKey()};
            l_request.sortKeys[0].keyItem = "1111";
            l_request.sortKeys[0].ascDesc = WEB3AscDescDef.ASC;
            l_request.sortKeys[1].keyItem = "1111";
            l_request.sortKeys[1].ascDesc = WEB3AscDescDef.ASC;
            l_request.sortKeys[2].keyItem = "1111";
            l_request.sortKeys[2].ascDesc = WEB3AscDescDef.ASC;
            l_request.validate();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

}
@
