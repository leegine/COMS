head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.16.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AccOpenMailAddrRegCompleteRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����J�݃��[���A�h���X�o�^�������N�G�X�g(WEB3AccOpenMailAddrRegCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/08/10 �����F(���u) �V�K�쐬 ���f�� 162
*/

package webbroker3.accountopen.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AccOpenMailAddrRegCompleteRequestTest extends TestBaseForMock
{

    /**
     * ���O�o�̓��[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccOpenMailAddrRegCompleteRequestTest.class);

    public WEB3AccOpenMailAddrRegCompleteRequestTest(String arg0)
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

    /*
     * Test method for 'webbroker3.accountopen.message.WEB3AccOpenMailAddrRegCompleteRequest.validate()'
     */
    //�P�j�@@�،���ЃR�[�h�̃`�F�b�N 
    //�@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B 
    public void testValidateCase1()
    {
        final String STR_METHOD_NAME = "testValidateCase1()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AccOpenMailAddrRegCompleteRequest l_request =
                new WEB3AccOpenMailAddrRegCompleteRequest();
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02775, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    //�Q�j�@@���X�R�[�h�̃`�F�b�N 
    //�@@�Q�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B 
    public void testValidateCase2()
    {
        final String STR_METHOD_NAME = "testValidateCase2()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AccOpenMailAddrRegCompleteRequest l_request =
                new WEB3AccOpenMailAddrRegCompleteRequest();
            l_request.institutionCode = "0D";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02174, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //�R�j�@@���[���A�h���X�̃`�F�b�N 
    //�@@�R�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B 
    public void testValidateCase3()
    {
        final String STR_METHOD_NAME = "testValidateCase3()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AccOpenMailAddrRegCompleteRequest l_request =
                new WEB3AccOpenMailAddrRegCompleteRequest();
            l_request.institutionCode = "0D";
            l_request.branchCode = "101";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01700, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //�S�j�@@���̃`�F�b�N 
    //�@@�S�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
    public void testValidateCase4()
    {
        final String STR_METHOD_NAME = "testValidateCase4()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AccOpenMailAddrRegCompleteRequest l_request =
                new WEB3AccOpenMailAddrRegCompleteRequest();
            l_request.institutionCode = "0D";
            l_request.branchCode = "101";
            l_request.mailAddress = "yu@@sinocom.cn";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03167, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    //�T�j�@@�����敪�̃`�F�b�N 
    //�@@�T�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B 
    public void testValidateCase5()
    {
        final String STR_METHOD_NAME = "testValidateCase5()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AccOpenMailAddrRegCompleteRequest l_request =
                new WEB3AccOpenMailAddrRegCompleteRequest();
            l_request.institutionCode = "0D";
            l_request.branchCode = "101";
            l_request.mailAddress = "yu@@sinocom.cn";
            l_request.accountFamilyName = "zhang";
            
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00604, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //�T�j�@@�����敪�̃`�F�b�N 
    //�@@�T�|�Q�j�@@�u0�F�l�A�J�E���g�A1�F�@@�l�A�J�E���g�v�ȊO�̏ꍇ�A��O���X���[����B 
    public void testValidateCase6()
    {
        final String STR_METHOD_NAME = "testValidateCase6()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AccOpenMailAddrRegCompleteRequest l_request =
                new WEB3AccOpenMailAddrRegCompleteRequest();
            l_request.institutionCode = "0D";
            l_request.branchCode = "101";
            l_request.mailAddress = "yu@@sinocom.cn";
            l_request.accountFamilyName = "zhang";
            l_request.accountType = "2";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00605, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //����
    public void testValidateCase7()
    {
        final String STR_METHOD_NAME = "testValidateCase7()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AccOpenMailAddrRegCompleteRequest l_request =
                new WEB3AccOpenMailAddrRegCompleteRequest();
            l_request.institutionCode = "0D";
            l_request.branchCode = "101";
            l_request.mailAddress = "yu@@sinocom.cn";
            l_request.accountFamilyName = "zhang";
            l_request.accountType = "1";
            l_request.validate();
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
