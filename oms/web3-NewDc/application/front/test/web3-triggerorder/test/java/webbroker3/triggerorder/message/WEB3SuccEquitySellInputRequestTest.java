head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.48.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3SuccEquitySellInputRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : (WEB3SuccEquitySellInputRequestTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/07/07 ���z(���u) �V�K�쐬
*/
package webbroker3.triggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3SuccEquitySellInputRequestTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SuccEquitySellInputRequestTest.class);

    private WEB3SuccEquitySellInputRequest l_request = null;

    public WEB3SuccEquitySellInputRequestTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        l_request = new WEB3SuccEquitySellInputRequest();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
        l_request = null;
    }

    /*
     * Test method for 'webbroker3.triggerorder.message.WEB3SuccEquitySellInputRequest.validate()'
     */
    
    //�P�j�@@�A���������ʏ��`�F�b�N
    //�@@�P�|�P�j�@@this.�A���������ʏ��==null�̏ꍇ�A
    //�@@�@@�@@�@@�@@�@@�u�A���������ʏ�񂪖��w��v�̗�O���X���[����B
    public void testValidate_C0001()
    {
        final String STR_METHOD_NAME = "testValidate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.succCommonInfo = null;
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02251, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //�P�|�Q�j�@@this.�A���������ʏ��.validate()���R�[������B
    public void testValidate_C0002()
    {
        final String STR_METHOD_NAME = "testValidate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.succCommonInfo = new WEB3SuccCommonInfo();
            l_request.succCommonInfo.parentOrderId = null;
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02258, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
   
    //�@@�P�|�R�j�@@this.�A���������ʏ��.�A����������敪���ȉ��̒l�ȊO�̏ꍇ�A
    //�@@�@@�@@�@@�@@�@@�u�A����������敪�̒l�������ΏۊO�v�̗�O��throw����B
    public void testValidate_C0003()
    {
        final String STR_METHOD_NAME = "testValidate_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.succCommonInfo = new WEB3SuccCommonInfo();
            l_request.succCommonInfo.parentOrderId = "995";
            l_request.succCommonInfo.succTradingType = "02";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02252, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
 
    //�Q�j�@@this.�A���������ʏ��.�A����������敪=="���t�i�O�񒍕��j"�̏ꍇ�A
    //�@@�@@�@@�ȉ��̃`�F�b�N���s���B
    //�@@�Q�|�P�j super.�s��R�[�h==null�̏ꍇ�́A
    //�@@�@@�@@�@@�@@�@@�u���Ύ�����͎s��R�[�h�w��͕K�{�v�̗�O��throw����B
    public void testValidate_C0004()
    {
        final String STR_METHOD_NAME = "testValidate_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.succCommonInfo = new WEB3SuccCommonInfo();
            l_request.succCommonInfo.parentOrderId = "995";
            l_request.succCommonInfo.succTradingType = "03";
            l_request.marketCode = null;
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02257, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //�R�j�@@this.�A���������ʏ��.�A����������敪=="���t�i�����c�j"�̏ꍇ�̂݁A
    //�@@�@@�@@super.validate()���R�[������B
    public void testValidate_C0005()
    {
        final String STR_METHOD_NAME = "testValidate_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.succCommonInfo = new WEB3SuccCommonInfo();
            l_request.succCommonInfo.parentOrderId = "995";
            l_request.succCommonInfo.succTradingType = "04";
            l_request.id = null;
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00080, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //normal case
    public void testValidate_C0006()
    {
        final String STR_METHOD_NAME = "testValidate_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.succCommonInfo = new WEB3SuccCommonInfo();
            l_request.succCommonInfo.parentOrderId = "995";
            l_request.succCommonInfo.succTradingType = "03";
            l_request.marketCode = "1";
            l_request.validate();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //normal case
    public void testValidate_C0007()
    {
        final String STR_METHOD_NAME = "testValidate_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.succCommonInfo = new WEB3SuccCommonInfo();
            l_request.succCommonInfo.parentOrderId = "995";
            l_request.succCommonInfo.succTradingType = "04";
            l_request.id = "101";
            l_request.validate();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
