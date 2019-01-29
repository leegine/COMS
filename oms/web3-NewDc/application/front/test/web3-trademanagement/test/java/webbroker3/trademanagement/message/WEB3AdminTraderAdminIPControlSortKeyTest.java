head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.08.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminTraderAdminIPControlSortKeyTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE���O�C������IP�ꗗ�\�[�g�L�[(WEB3AdminTraderAdminIPControlSortKeyTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/09/23 �����F (���u) �V�K�쐬 ���f��004
*/
package webbroker3.trademanagement.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminTraderAdminIPControlSortKeyTest extends TestBaseForMock 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTraderAdminIPControlSortKeyTest.class);

    WEB3AdminTraderAdminIPControlSortKey l_request = new WEB3AdminTraderAdminIPControlSortKey();
    
    public WEB3AdminTraderAdminIPControlSortKeyTest(String name)
    {
        super(name);
    }

    /**
     * this.�L�[���ڂ�null�̏ꍇ�A��O��throw����B 
     */
    public void test_validate_0001()
    {
        String STR_METHOD_NAME = "test_validate_0001()";
        log.entering(STR_METHOD_NAME);

        l_request.keyItem = null;
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
    
    /**
     * this.�L�[���ڂɈȉ��̍��ڈȊO���ݒ肳��Ă���ꍇ�A��O��throw����B 
     * �EIP�A�h���X
     * �E�K�p�J�n����
     * �E�X�e�[�^�X 
     */
    public void test_validate_0002()
    {
        String STR_METHOD_NAME = "test_validate_0002()";
        log.entering(STR_METHOD_NAME);

        l_request.keyItem = "a";
        try
        {
            l_request.validate();
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
     * this.�L�[����="ipAddress"
     * this.�����^�~����null�̏ꍇ�A��O��throw����B 
     */
    public void test_validate_0003()
    {
        String STR_METHOD_NAME = "test_validate_0003()";
        log.entering(STR_METHOD_NAME);

        //�L�[����
        l_request.keyItem = "ipAddress";
        //�����^�~��
        l_request.ascDesc = null;
        
        try
        {
            l_request.validate();
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
     * this.�L�[����="startDate"
     * this.�����^�~��="A"
     */
    public void test_validate_0004()
    {
        String STR_METHOD_NAME = "test_validate_0004()";
        log.entering(STR_METHOD_NAME);

        //�L�[����
        l_request.keyItem = "startDate";
        //�����^�~��
        l_request.ascDesc = "A";
        
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
    
    /**
     * this.�L�[����="status"
     * this.�����^�~��="D"
     */
    public void test_validate_0005()
    {
        String STR_METHOD_NAME = "test_validate_0005()";
        log.entering(STR_METHOD_NAME);

        //�L�[����
        l_request.keyItem = "status";
        //�����^�~��
        l_request.ascDesc = "D";
        
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
