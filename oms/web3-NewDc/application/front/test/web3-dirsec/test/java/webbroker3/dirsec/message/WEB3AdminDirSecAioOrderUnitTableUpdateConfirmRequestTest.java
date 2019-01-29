head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.00.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminDirSecAioOrderUnitTableUpdateConfirmRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.dirsec.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;


public class WEB3AdminDirSecAioOrderUnitTableUpdateConfirmRequestTest extends TestBaseForMock
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3AdminDirSecAioOrderUnitTableUpdateConfirmRequestTest.class);

    public WEB3AdminDirSecAioOrderUnitTableUpdateConfirmRequestTest(String arg0)
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
    
    public void testValidate_0001()
    {
        final String STR_METHOD_NAME = " testValidate_0001()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminDirSecAioOrderUnitTableUpdateConfirmRequest l_request =
            new WEB3AdminDirSecAioOrderUnitTableUpdateConfirmRequest();
        
        try
        {
            l_request.validate();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_0002()
    {
        final String STR_METHOD_NAME = " testValidate_0002()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminDirSecAioOrderUnitTableUpdateConfirmRequest l_request =
            new WEB3AdminDirSecAioOrderUnitTableUpdateConfirmRequest();
        l_request.updateExecStatus = "";
        
        try
        {
            l_request.validate();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_0003()
    {
        final String STR_METHOD_NAME = " testValidate_0003()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminDirSecAioOrderUnitTableUpdateConfirmRequest l_request =
            new WEB3AdminDirSecAioOrderUnitTableUpdateConfirmRequest();
        l_request.updateExecStatus = "aa11";
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals("����Ԃ������ȊO�̒l�ł��B", l_ex.getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_0004()
    {
        final String STR_METHOD_NAME = " testValidate_0004()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminDirSecAioOrderUnitTableUpdateConfirmRequest l_request =
            new WEB3AdminDirSecAioOrderUnitTableUpdateConfirmRequest();
        l_request.updateExecStatus = "111";
        
        try
        {
            l_request.validate();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_0005()
    {
        final String STR_METHOD_NAME = " testValidate_0005()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminDirSecAioOrderUnitTableUpdateConfirmRequest l_request =
            new WEB3AdminDirSecAioOrderUnitTableUpdateConfirmRequest();
        l_request.updateExecStatus = "11.11";
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals("����Ԃ������ȊO�̒l�ł��B", l_ex.getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    /*
     * this.�X�V_�������̌��� != 8 �ł������ꍇ
     * ?�o:WEB3ErrorCatalog.BUSINESS_ERROR_02160
     */
    public void testValidate_0006()
    {
        final String STR_METHOD_NAME = " testValidate_0006()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminDirSecAioOrderUnitTableUpdateConfirmRequest l_request =
            new WEB3AdminDirSecAioOrderUnitTableUpdateConfirmRequest();
        
        try
        {
            l_request.updateOrderBizDate = "123";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02160, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    //�S�j�������`�F�b�N
    //�S�|�P�j�@@WEB3StringTypeUtility.isEmpty(this.�X�V_������) == true�@@�̏ꍇ�A
    //�ȉ��̃`�F�b�N���s���B
    public void testValidate_0007()
    {
        final String STR_METHOD_NAME = " testValidate_0007()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminDirSecAioOrderUnitTableUpdateConfirmRequest l_request =
            new WEB3AdminDirSecAioOrderUnitTableUpdateConfirmRequest();
        
        try
        {
            l_request.validate();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

}
@
