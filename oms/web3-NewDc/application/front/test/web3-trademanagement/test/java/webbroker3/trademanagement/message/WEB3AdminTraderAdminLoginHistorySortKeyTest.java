head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.08.22;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminTraderAdminLoginHistorySortKeyTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE���O�C�������ꗗ�������ʃ\�[�g�L�[(WEB3AdminTraderAdminLoginHistorySortKeyTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/09/23 �И��� (���u) �V�K�쐬 ���f��No.005
*/

package webbroker3.trademanagement.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminTraderAdminLoginHistorySortKeyTest extends TestBaseForMock
{
    /**
     * (���O�o�̓��[�e�B���e�B)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
                WEB3AdminTraderAdminLoginHistorySortKeyTest.class);

    WEB3AdminTraderAdminLoginHistorySortKey l_adminLoginHistorySortKey =
        new WEB3AdminTraderAdminLoginHistorySortKey();

    public WEB3AdminTraderAdminLoginHistorySortKeyTest(String name)
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
     * this.�L�[���ځ�null
     * �u�\�[�g�L�[.�L�[���ڂ�null�v�̗�O���X���[����B
     */
    public void test_validate_0001()
    {
        String STR_METHOD_NAME = "test_validate_0001()";
        log.entering(STR_METHOD_NAME);

        l_adminLoginHistorySortKey.keyItem = null;
        try
        {
            l_adminLoginHistorySortKey.validate();
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

    /**
     * this.�L�[���ڂɈȉ��̍��ڈȊO���ݒ肳��Ă���ꍇ�A��O���X���[����B
     * �E���O�C������
     * �EIP�A�h���X
     * �E�ڋq�R�[�h
     */
    public void test_validate_0002()
    {
        String STR_METHOD_NAME = "test_validate_0002()";
        log.entering(STR_METHOD_NAME);

        l_adminLoginHistorySortKey.keyItem = "111";
        try
        {
            l_adminLoginHistorySortKey.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00086, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * this.�L�[���� = ���O�C������
     * this.�����^�~����null�̏ꍇ�A
     * �u�\�[�g�L�[.�����^�~����null�v�̗�O���X���[����B
     */
    public void test_validate_0003()
    {
        String STR_METHOD_NAME = "test_validate_0003()";
        log.entering(STR_METHOD_NAME);

        l_adminLoginHistorySortKey.keyItem = "loginDate";
        l_adminLoginHistorySortKey.ascDesc = null;

        try
        {
            l_adminLoginHistorySortKey.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00087, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * this.�L�[����=IP�A�h���X
     * this.�����^�~�������L�̍��ڈȊO�̏ꍇ�A
     * �u�\�[�g�L�[.�����^�~��������`�̒l�v�̗�O���X���[����B
     * �E�hA�F�����h
     * �E�hD�F�~���h
     */
    public void test_validate_0004()
    {
        String STR_METHOD_NAME = "test_validate_0004()";
        log.entering(STR_METHOD_NAME);

        l_adminLoginHistorySortKey.keyItem = "ipAddress";
        l_adminLoginHistorySortKey.ascDesc = "11A";
        try
        {
            l_adminLoginHistorySortKey.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00088, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * this.�L�[����=�ڋq�R�[�h
     * this.�����^�~��=�hA�F�����h
     */
    public void test_validate_0005()
    {
        String STR_METHOD_NAME = "test_validate_0005()";
        log.entering(STR_METHOD_NAME);

        l_adminLoginHistorySortKey.keyItem = "accountCode";
        l_adminLoginHistorySortKey.ascDesc = "A";
        try
        {
            l_adminLoginHistorySortKey.validate();
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

    /**
     * this.�L�[����=�ڋq�R�[�h
     * this.�����^�~��=�hD�F�~���h
     */
    public void test_validate_0006()
    {
        String STR_METHOD_NAME = "test_validate_0006()";
        log.entering(STR_METHOD_NAME);

        l_adminLoginHistorySortKey.keyItem = "accountCode";
        l_adminLoginHistorySortKey.ascDesc = "D";
        try
        {
            l_adminLoginHistorySortKey.validate();
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
