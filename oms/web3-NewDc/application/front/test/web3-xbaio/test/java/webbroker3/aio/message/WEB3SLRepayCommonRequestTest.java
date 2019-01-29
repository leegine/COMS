head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.22.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3SLRepayCommonRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �،��S�ۃ��[���ԍϐ\�����ʃ��N�G�X�g�̃e�X�g�N���X(WEB3SLRepayCommonRequestTest.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/09/23 �����q (���u) �V�K�쐬
*/
package webbroker3.aio.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * �،��S�ۃ��[���ԍϐ\�����ʃ��N�G�X�g
 * @@author �����q
 *
 */

public class WEB3SLRepayCommonRequestTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SLRepayCommonRequestTest.class);
    
    WEB3SLRepayApplyCommonRequest l_request = new WEB3SLRepayApplyCommonRequest();

    public WEB3SLRepayCommonRequestTest(String arg0)
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

    public void testValidate_case0001()
    {
        final String STR_METHOD_NAME = "testValidate_case0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals("�ԍϊz�����w��ł��B", l_ex.getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_case0002()
    {
        final String STR_METHOD_NAME = "testValidate_case0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.repayAmt = "sdkjfl";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals("�ԍϊz�������ȊO�̒l�ł��B", l_ex.getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_case0003()
    {
        final String STR_METHOD_NAME = "testValidate_case0003()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.repayAmt = "�P�Q�R�T�S";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals("�ԍϊz�������ȊO�̒l�ł��B", l_ex.getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_case0004()
    {
        final String STR_METHOD_NAME = "testValidate_case0004()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.repayAmt = "0";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals("�ԍϊz�̒l��0�ȉ��ł��B", l_ex.getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_case0005()
    {
        final String STR_METHOD_NAME = "testValidate_case0005()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.repayAmt = "-123";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals("�ԍϊz�̒l��0�ȉ��ł��B", l_ex.getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_case0006()
    {
        final String STR_METHOD_NAME = "testValidate_case0006()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.repayAmt = "1234567890123";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals("�ԍϊz�̃T�C�Y���s���ł��B", l_ex.getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_case0007()
    {
        final String STR_METHOD_NAME = "testValidate_case0007()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.repayAmt = "123456";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals("�ԍϗ\��������w��ł��B", l_ex.getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_case0008()
    {
        final String STR_METHOD_NAME = "testValidate_case0008()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_request.repayAmt = "123456";
            l_request.repayScheduledDate = WEB3DateUtility.getDate("20070809", "yyyyMMdd");
            l_request.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals("�ԍϗ\��������w��ł��B", l_ex.getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
