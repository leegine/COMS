head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.00.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminDirSecAioOrderUnitTableUpdateCompleteRequestTest.java;


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


public class WEB3AdminDirSecAioOrderUnitTableUpdateCompleteRequestTest extends TestBaseForMock
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3AdminDirSecAioOrderUnitTableUpdateCompleteRequestTest.class);

    public WEB3AdminDirSecAioOrderUnitTableUpdateCompleteRequestTest(String arg0)
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

        WEB3AdminDirSecAioOrderUnitTableUpdateCompleteRequest l_request =
            new WEB3AdminDirSecAioOrderUnitTableUpdateCompleteRequest();
        l_request.orderUnitId = "123456";
        l_request.password = "123";
        l_request.orderUnitTblKbn = "5";
        
        try
        {
            l_request.validate();
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02705, l_ex.getErrorInfo());
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

        WEB3AdminDirSecAioOrderUnitTableUpdateCompleteRequest l_request =
            new WEB3AdminDirSecAioOrderUnitTableUpdateCompleteRequest();
        l_request.orderUnitId = "123456";
        l_request.updateExecStatus = "";
        l_request.password = "123";
        l_request.orderUnitTblKbn = "2";
        
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

        WEB3AdminDirSecAioOrderUnitTableUpdateCompleteRequest l_request =
            new WEB3AdminDirSecAioOrderUnitTableUpdateCompleteRequest();
        l_request.orderUnitId = "123456";
        l_request.updateExecStatus = "aa11";
        l_request.password = "123";
        l_request.orderUnitTblKbn = "2";
        
        try
        {
            l_request.validate();
        }
        catch (WEB3BaseException l_ex)
        {
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

    //�T�j�������`�F�b�N
    //�T�|�P�j�@@WEB3StringTypeUtility.isEmpty(this.�X�V_������) == false�@@�̏ꍇ�A
    //�ȉ��̃`�F�b�N���s���B
    public void testValidate_0004()
    {
        final String STR_METHOD_NAME = " testValidate_0004()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminDirSecAioOrderUnitTableUpdateCompleteRequest l_request =
            new WEB3AdminDirSecAioOrderUnitTableUpdateCompleteRequest();
        l_request.orderUnitId = "123456";
        l_request.updateOrderBizDate = "20080717";
        l_request.updateExecStatus = "11";
        l_request.password = "123";
        l_request.orderUnitTblKbn = "2";
        
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

    //�T�|�P�|�P�jthis.�X�V_�������̌��� != 8 �ł������ꍇ�A 
    //�u�������̌������s���ł��B�v�̗�O���X���[����B
    public void testValidate_0005()
    {
        final String STR_METHOD_NAME = " testValidate_0005()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminDirSecAioOrderUnitTableUpdateCompleteRequest l_request =
            new WEB3AdminDirSecAioOrderUnitTableUpdateCompleteRequest();
        l_request.orderUnitId = "123456";
        l_request.updateOrderBizDate = "200807";
        l_request.updateExecStatus = "11";
        l_request.password = "123";
        l_request.orderUnitTblKbn = "2";
        
        try
        {
            l_request.validate();
        }
        catch (WEB3BaseException l_ex)
        {
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

}
@
