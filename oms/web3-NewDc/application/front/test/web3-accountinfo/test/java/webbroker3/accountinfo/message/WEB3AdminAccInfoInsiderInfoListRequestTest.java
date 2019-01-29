head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.06.08.39.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5404d9c264d0465;
filename	WEB3AdminAccInfoInsiderInfoListRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountinfo.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminAccInfoInsiderInfoListRequestTest extends TestBaseForMock
{
    /**
     * ���O�o�̓��[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoInsiderInfoListRequestTest.class);

    WEB3AdminAccInfoInsiderInfoListRequest l_request;

    public WEB3AdminAccInfoInsiderInfoListRequestTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        l_request = new WEB3AdminAccInfoInsiderInfoListRequest();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /**
     * �P�|�P�j���X�R�[�h�����͂̏ꍇ�A��O���X���[����B
     */
    public void testValidate_Case001()
    {
        final String STR_METHOD_NAME = "testValidate_Case001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.branchCode = null;
            l_request.validate();
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00833, l_ex.getErrorInfo());
            log.exiting(STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �P�|�Q�j���X�R�[�h�v�f����0�̏ꍇ�A��O���X���[����B
     */
    public void testValidate_Case002()
    {
        final String STR_METHOD_NAME = "testValidate_Case002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            String[] branchCode = new String[0];
            l_request.branchCode = branchCode;
            l_request.validate();
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00779, l_ex.getErrorInfo());
            log.exiting(STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �P�|�R�|�P�j���X�R�[�h�v�f��������3�łȂ��ꍇ�A��O���X���[����B
     */
    public void testValidate_Case003()
    {
        final String STR_METHOD_NAME = "testValidate_Case003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            String[] branchCode = new String[2];
            branchCode[0] = "101";
            branchCode[1] = "10";
            l_request.branchCode = branchCode;
            l_request.validate();
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00834, l_ex.getErrorInfo());
            log.exiting(STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �P�|�R�|�Q�j���X�R�[�h�v�f�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B
     */
    public void testValidate_Case004()
    {
        final String STR_METHOD_NAME = "testValidate_Case004()";
        log.entering(STR_METHOD_NAME);

        try
        {
            String[] branchCode = new String[2];
            branchCode[0] = "101";
            branchCode[1] = "-10";
            l_request.branchCode = branchCode;
            l_request.validate();
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01729, l_ex.getErrorInfo());
            log.exiting(STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �Q�|�P�j�ڋq�R�[�h�����͂łȂ��ꍇ�A������6�łȂ��ꍇ�A��O���X���[����B
     */
    public void testValidate_Case005()
    {
        final String STR_METHOD_NAME = "testValidate_Case005()";
        log.entering(STR_METHOD_NAME);

        try
        {
            String[] branchCode = new String[2];
            branchCode[0] = "101";
            branchCode[1] = "102";
            l_request.branchCode = branchCode;
            l_request.accountCode = "123";
            l_request.validate();
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00836, l_ex.getErrorInfo());
            log.exiting(STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �Q�|�Q�j�ڋq�R�[�h�����͂łȂ��ꍇ�A�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B
     */
    public void testValidate_Case006()
    {
        final String STR_METHOD_NAME = "testValidate_Case006()";
        log.entering(STR_METHOD_NAME);

        try
        {
            String[] branchCode = new String[2];
            branchCode[0] = "101";
            branchCode[1] = "102";
            l_request.branchCode = branchCode;
            l_request.accountCode = "-45123";
            l_request.validate();
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01043, l_ex.getErrorInfo());
            log.exiting(STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �R�|�P�j�����R�[�h�����͂łȂ��ꍇ�A������5�łȂ��ꍇ�A��O���X���[����B
     */
    public void testValidate_Case007()
    {
        final String STR_METHOD_NAME = "testValidate_Case007()";
        log.entering(STR_METHOD_NAME);

        try
        {
            String[] branchCode = new String[2];
            branchCode[0] = "101";
            branchCode[1] = "102";
            l_request.branchCode = branchCode;
            l_request.accountCode = "123456";
            l_request.productCode = "100";
            l_request.validate();
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00439, l_ex.getErrorInfo());
            log.exiting(STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �R�|�Q�j�����R�[�h�����͂łȂ��ꍇ�A�����ȊO�̕������܂܂��ꍇ�A��O���X���[����B
     */
    public void testValidate_Case008()
    {
        final String STR_METHOD_NAME = "testValidate_Case008()";
        log.entering(STR_METHOD_NAME);

        try
        {
            String[] branchCode = new String[2];
            branchCode[0] = "101";
            branchCode[1] = "102";
            l_request.branchCode = branchCode;
            l_request.accountCode = "123456";
            l_request.productCode = "1000c";
            l_request.validate();
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00815, l_ex.getErrorInfo());
            log.exiting(STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �S�|�P�j�@@�\�[�g�L�[�������͂̏ꍇ�A��O���X���[����B
     */
    public void testValidate_Case009()
    {
        final String STR_METHOD_NAME = "testValidate_Case009()";
        log.entering(STR_METHOD_NAME);

        try
        {
            String[] branchCode = new String[2];
            branchCode[0] = "101";
            branchCode[1] = "102";
            l_request.branchCode = branchCode;
            l_request.accountCode = "123456";
            l_request.productCode = "10000";
            l_request.sortKeys = null;
            l_request.validate();
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00231, l_ex.getErrorInfo());
            log.exiting(STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �S�|�Q�j�i�\�[�g�L�[�̗v�f�� == 0�j�̏ꍇ�A ��O���X���[����B
     */
    public void testValidate_Case010()
    {
        final String STR_METHOD_NAME = "testValidate_Case010()";
        log.entering(STR_METHOD_NAME);

        try
        {
            String[] branchCode = new String[2];
            branchCode[0] = "101";
            branchCode[1] = "102";
            l_request.branchCode = branchCode;
            l_request.accountCode = "123456";
            l_request.productCode = "10000";
            WEB3AccInfoSortKey[] sortKeys = new WEB3AccInfoSortKey[0];
            l_request.sortKeys = sortKeys;
            l_request.validate();
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00232, l_ex.getErrorInfo());
            log.exiting(STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �S�|�R�|�P�j�\�[�g�L�[.validate()���R�[������B
     */
    public void testValidate_Case011()
    {
        final String STR_METHOD_NAME = "testValidate_Case011()";
        log.entering(STR_METHOD_NAME);

        try
        {
            String[] branchCode = new String[2];
            branchCode[0] = "101";
            branchCode[1] = "102";
            l_request.branchCode = branchCode;
            l_request.accountCode = "123456";
            l_request.productCode = "10000";
            WEB3AccInfoSortKey[] sortKeys = new WEB3AccInfoSortKey[2];
            WEB3AccInfoSortKey sortKeys0 = new WEB3AccInfoSortKey();
            sortKeys0.keyItem = "123";
            sortKeys0.ascDesc = null;
            sortKeys[0] = sortKeys0;
            WEB3AccInfoSortKey sortKeys1 = new WEB3AccInfoSortKey();
            sortKeys1.keyItem = "456";
            sortKeys1.ascDesc = "A";
            sortKeys[1] = sortKeys1;
            l_request.sortKeys = sortKeys;
            l_request.validate();
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00087, l_ex.getErrorInfo());
            log.exiting(STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �S�|�R�|�Q�j�@@�\�[�g�L�[.�L�[���ڂ����L�̍��ږ��ȊO�̏ꍇ�A ��O���X���[����B
     *�@@�@@�@@�@@ �����ҏ��ꗗUnit.���X�R�[�h
     *�@@�@@�@@�@@ �����ҏ��ꗗUnit.�ڋq�R�[�h
     *�@@�@@�@@�@@ �����ҏ��ꗗUnit.�����R�[�h
     *�@@�@@�@@�@@ �����ҏ��ꗗUnit.�֌W�R�[�h
     *�@@�@@�@@�@@ �����ҏ��ꗗUnit.������
     *�@@�@@�@@�@@ �����ҏ��ꗗUnit.��E��
     *�@@�@@�@@�@@ �����ҏ��ꗗUnit.�o�^�󋵋敪
     */
    public void testValidate_Case012()
    {
        final String STR_METHOD_NAME = "testValidate_Case012()";
        log.entering(STR_METHOD_NAME);

        try
        {
            String[] branchCode = new String[2];
            branchCode[0] = "101";
            branchCode[1] = "102";
            l_request.branchCode = branchCode;
            l_request.accountCode = "123456";
            l_request.productCode = "10000";
            WEB3AccInfoSortKey[] sortKeys = new WEB3AccInfoSortKey[2];
            WEB3AccInfoSortKey sortKeys0 = new WEB3AccInfoSortKey();
            sortKeys0.keyItem = "abc";
            sortKeys0.ascDesc = "A";
            sortKeys[0] = sortKeys0;
            WEB3AccInfoSortKey sortKeys1 = new WEB3AccInfoSortKey();
            sortKeys1.keyItem = "branchCode";
            sortKeys1.ascDesc = "D";
            sortKeys[1] = sortKeys1;
            l_request.sortKeys = sortKeys;
            l_request.validate();
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00086, l_ex.getErrorInfo());
            log.exiting(STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �T�|�Q�j�v���y�[�W�ԍ������ȊO�̕������܂܂��ꍇ�A��O���X���[����B
     */
    public void testValidate_Case013()
    {
        final String STR_METHOD_NAME = "testValidate_Case013()";
        log.entering(STR_METHOD_NAME);

        try
        {
            String[] branchCode = new String[2];
            branchCode[0] = "101";
            branchCode[1] = "102";
            l_request.branchCode = branchCode;
            l_request.accountCode = "123456";
            l_request.productCode = "10000";
            WEB3AccInfoSortKey[] sortKeys = new WEB3AccInfoSortKey[2];
            WEB3AccInfoSortKey sortKeys0 = new WEB3AccInfoSortKey();
            sortKeys0.keyItem = "accountCode";
            sortKeys0.ascDesc = "A";
            sortKeys[0] = sortKeys0;
            WEB3AccInfoSortKey sortKeys1 = new WEB3AccInfoSortKey();
            sortKeys1.keyItem = "branchCode";
            sortKeys1.ascDesc = "D";
            sortKeys[1] = sortKeys1;
            l_request.sortKeys = sortKeys;
            l_request.pageIndex = "123.45";
            l_request.validate();
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00090, l_ex.getErrorInfo());
            log.exiting(STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �T�|�R�j�v���y�[�W�ԍ��}�C�i�X�l�̏ꍇ�A��O���X���[����B
     */
    public void testValidate_Case014()
    {
        final String STR_METHOD_NAME = "testValidate_Case014()";
        log.entering(STR_METHOD_NAME);

        try
        {
            String[] branchCode = new String[2];
            branchCode[0] = "101";
            branchCode[1] = "102";
            l_request.branchCode = branchCode;
            l_request.accountCode = "123456";
            l_request.productCode = "10000";
            WEB3AccInfoSortKey[] sortKeys = new WEB3AccInfoSortKey[2];
            WEB3AccInfoSortKey sortKeys0 = new WEB3AccInfoSortKey();
            sortKeys0.keyItem = "accountCode";
            sortKeys0.ascDesc = "A";
            sortKeys[0] = sortKeys0;
            WEB3AccInfoSortKey sortKeys1 = new WEB3AccInfoSortKey();
            sortKeys1.keyItem = "branchCode";
            sortKeys1.ascDesc = "D";
            sortKeys[1] = sortKeys1;
            l_request.sortKeys = sortKeys;
            l_request.pageIndex = "-123";
            l_request.validate();
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00616, l_ex.getErrorInfo());
            log.exiting(STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �U�|�P�j�y�[�W���\���s�������͂̏ꍇ�A��O���X���[����B
     */
    public void testValidate_Case015()
    {
        final String STR_METHOD_NAME = "testValidate_Case015()";
        log.entering(STR_METHOD_NAME);

        try
        {
            String[] branchCode = new String[2];
            branchCode[0] = "101";
            branchCode[1] = "102";
            l_request.branchCode = branchCode;
            l_request.accountCode = "123456";
            l_request.productCode = "10000";
            WEB3AccInfoSortKey[] sortKeys = new WEB3AccInfoSortKey[2];
            WEB3AccInfoSortKey sortKeys0 = new WEB3AccInfoSortKey();
            sortKeys0.keyItem = "accountCode";
            sortKeys0.ascDesc = "A";
            sortKeys[0] =sortKeys0;
            WEB3AccInfoSortKey sortKeys1 = new WEB3AccInfoSortKey();
            sortKeys1.keyItem = "branchCode";
            sortKeys1.ascDesc = "D";
            sortKeys[1] = sortKeys1;
            l_request.sortKeys = sortKeys;
            l_request.pageIndex = "1230";
            l_request.pageSize = null;
            l_request.validate();
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00091, l_ex.getErrorInfo());
            log.exiting(STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �U�|�Q�j�y�[�W���\���s�������ȊO�̕������܂܂��ꍇ�A��O���X���[����B
     */
    public void testValidate_Case016()
    {
        final String STR_METHOD_NAME = "testValidate_Case016()";
        log.entering(STR_METHOD_NAME);

        try
        {
            String[] branchCode = new String[2];
            branchCode[0] = "101";
            branchCode[1] = "102";
            l_request.branchCode = branchCode;
            l_request.accountCode = "123456";
            l_request.productCode = "10000";
            WEB3AccInfoSortKey[] sortKeys = new WEB3AccInfoSortKey[2];
            WEB3AccInfoSortKey sortKeys0 = new WEB3AccInfoSortKey();
            sortKeys0.keyItem = "accountCode";
            sortKeys0.ascDesc = "A";
            sortKeys[0] = sortKeys0;
            WEB3AccInfoSortKey sortKeys1 = new WEB3AccInfoSortKey();
            sortKeys1.keyItem = "branchCode";
            sortKeys1.ascDesc = "D";
            sortKeys[1] = sortKeys1;
            l_request.sortKeys = sortKeys;
            l_request.pageIndex = "1230";
            l_request.pageSize = "12a";
            l_request.validate();
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00092, l_ex.getErrorInfo());
            log.exiting(STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �U�|�R�j�@@�}�C�i�X�l�̏ꍇ�A��O���X���[����B
     */
    public void testValidate_Case017()
    {
        final String STR_METHOD_NAME = "testValidate_Case017()";
        log.entering(STR_METHOD_NAME);

        try
        {
            String[] branchCode = new String[2];
            branchCode[0] = "101";
            branchCode[1] = "102";
            l_request.branchCode = branchCode;
            l_request.accountCode = "123456";
            l_request.productCode = "10000";
            WEB3AccInfoSortKey[] sortKeys = new WEB3AccInfoSortKey[2];
            WEB3AccInfoSortKey sortKeys0 = new WEB3AccInfoSortKey();
            sortKeys0.keyItem = "accountCode";
            sortKeys0.ascDesc = "A";
            sortKeys[0] = sortKeys0;
            WEB3AccInfoSortKey sortKeys1 = new WEB3AccInfoSortKey();
            sortKeys1.keyItem = "branchCode";
            sortKeys1.ascDesc = "D";
            sortKeys[1] = sortKeys1;
            l_request.sortKeys = sortKeys;
            l_request.pageIndex = "1230";
            l_request.pageSize = "-456";
            l_request.validate();
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00617, l_ex.getErrorInfo());
            log.exiting(STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    

    /**
     * �T�|�P�j�v���y�[�W�ԍ������͂̏ꍇ�A �v���y�[�W�ԍ��Ɂh�P�h���Z�b�g����B
     */
    public void testValidate_Case018()
    {
        final String STR_METHOD_NAME = "testValidate_Case018()";
        log.entering(STR_METHOD_NAME);

        try
        {
            String[] branchCode = new String[2];
            branchCode[0] = "101";
            branchCode[1] = "102";
            l_request.branchCode = branchCode;
            l_request.accountCode = "123456";
            l_request.productCode = "10000";
            WEB3AccInfoSortKey[] sortKeys = new WEB3AccInfoSortKey[2];
            WEB3AccInfoSortKey sortKeys0 = new WEB3AccInfoSortKey();
            sortKeys0.keyItem = "accountCode";
            sortKeys0.ascDesc = "A";
            sortKeys[0] = sortKeys0;
            WEB3AccInfoSortKey sortKeys1 = new WEB3AccInfoSortKey();
            sortKeys1.keyItem = "branchCode";
            sortKeys1.ascDesc = "D";
            sortKeys[1] = sortKeys1;
            l_request.sortKeys = sortKeys;
            l_request.pageIndex = null;
            l_request.pageSize = "456";
            l_request.validate();
            assertEquals("1", l_request.pageIndex);
            log.exiting(STR_METHOD_NAME);

        }
        catch(WEB3BusinessLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
}
@
