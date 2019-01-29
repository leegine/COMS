head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.15.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AccOpenMailAddrRegInputRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����J�݃��[���A�h���X�o�^���̓��N�G�X�g(WEB3AccOpenMailAddrRegInputRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/08/10 �����F(���u) �V�K�쐬 ���f�� 162
*/

package webbroker3.accountopen.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AccOpenMailAddrRegInputRequestTest extends TestBaseForMock
{
    /**
     * ���O�o�̓��[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccOpenMailAddrRegInputRequestTest.class);

    public WEB3AccOpenMailAddrRegInputRequestTest(String arg0)
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
     * Test method for 'webbroker3.accountopen.message.WEB3AccOpenMailAddrRegInputRequest.validate()'
     */
    // �@@�P�j�@@�،���ЃR�[�h�̃`�F�b�N 
    //�@@�@@�@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B 
    public void testValidateCase1()
    {
        final String STR_METHOD_NAME = "testValidateCase1()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AccOpenMailAddrRegInputRequest l_request =
                new WEB3AccOpenMailAddrRegInputRequest();
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
            WEB3AccOpenMailAddrRegInputRequest l_request =
                new WEB3AccOpenMailAddrRegInputRequest();
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
    
    public void testValidateCase3()
    {
        final String STR_METHOD_NAME = "testValidateCase3()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AccOpenMailAddrRegInputRequest l_request =
                new WEB3AccOpenMailAddrRegInputRequest();
            l_request.institutionCode = "0D";
            l_request.branchCode = "101";
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
