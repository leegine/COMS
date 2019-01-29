head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.15.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminAccOpenApplyDataDelCmpRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �Ǘ��Ҍ����J�ݎ��������f�[�^�폜�������N�G�X�g(WEB3AdminAccOpenApplyDataDelCmpRequest.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2008/12/12 ���m�a(���u) �V�K�쐬 ���f��No.160
*/

package webbroker3.accountopen.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;


/**
 * (�Ǘ��Ҍ����J�ݎ��������f�[�^�폜�������N�G�X�g)<BR>
 * �Ǘ��Ҍ����J�ݎ��������f�[�^�폜�������N�G�X�g<BR>
 * <BR>
 * @@author ���m�a
 * @@version 1.0
 */
public class WEB3AdminAccOpenApplyDataDelCmpRequestTest extends TestBaseForMock
{

    /**
     * ���O�o�̓��[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccOpenApplyDataDelCmpRequestTest.class);

    WEB3AdminAccOpenApplyDataDelCmpRequest l_request =
        new WEB3AdminAccOpenApplyDataDelCmpRequest();

    public WEB3AdminAccOpenApplyDataDelCmpRequestTest(String arg0)
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
     * �ُ�
     * 
     * �P�j�@@���ʃR�[�h�̃`�F�b�N
     *  �P�|�P�j�@@�����͂̏ꍇ�A
     *   �iBUSINESS_ERROR_00829�j��O���X���[����B
     */
    public void testValidate_C001()
    {
        final String STR_METHOD_NAME = "testValidate_C001()";
        log.entering(STR_METHOD_NAME);
        l_request.requestNumber = null;

        try
        {
            l_request.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00829, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * �ُ�
     * 
     *�P�|�Q�j�@@���p�����ȊO���܂܂��ꍇ�A
     * �iBUSINESS_ERROR_01820�j��O���X���[����B
     */
    public void testValidate_C002()
    {
        final String STR_METHOD_NAME = "testValidate_C002()";
        log.entering(STR_METHOD_NAME);
        l_request.requestNumber = "1ac2";

        try
        {
            l_request.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01820, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * �ُ�
     * 
     * �Q�j�@@�m�F�t���O�̃`�F�b�N
     *  �Q�|�P�j�@@this.�m�F�t���O�����`�F�b�N�̏ꍇ�A
     * �iBUSINESS_ERROR_03141�j��O���X���[����B
     */
    public void testValidate_C003()
    {
        final String STR_METHOD_NAME = "testValidate_C003()";
        log.entering(STR_METHOD_NAME);
        l_request.requestNumber = "0";

        try
        {
            l_request.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03141, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage());
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * ����
     */
    public void testValidate_C004()
    {
        final String STR_METHOD_NAME = "testValidate_C004()";
        log.entering(STR_METHOD_NAME);
        l_request.requestNumber = "123";
        l_request.checkFlag = "1";

        try
        {
            l_request.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage(), l_ex);
            fail();
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
