head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.20.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminBondDomesticRecruitLimitManageCompleteRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҍ��������X�ʉ���g�Ǘ��������N�G�X�g(WEB3AdminBondDomesticRecruitLimitManageCompleteRequestTest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/08/01 �ؕk (���u) �V�K�쐬�@@�d�l�ύX�E���f��No.215
*/

package webbroker3.bd.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

import junit.framework.TestCase;

public class WEB3AdminBondDomesticRecruitLimitManageCompleteRequestTest extends TestBaseForMock
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondDomesticRecruitLimitManageCompleteRequestTest.class);

        WEB3AdminBondDomesticRecruitLimitManageCompleteRequest l_request =
            new WEB3AdminBondDomesticRecruitLimitManageCompleteRequest();

    public WEB3AdminBondDomesticRecruitLimitManageCompleteRequestTest(String arg0)
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
    public void testValidate0001()
    {
        final String STR_METHOD_NAME = " testValidate0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_request.productId = null;

        try
        {
            l_request.validate();

            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(TEST_START + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_START + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02229,l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_START + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_START + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidate0002()
    {
        final String STR_METHOD_NAME = " testValidate0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_request.productId = "1";
        l_request.password = null;
        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(TEST_START + STR_METHOD_NAME,l_ex);
            log.exiting(TEST_START + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01090,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(TEST_START + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_START + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidate0003()
    {
        final String STR_METHOD_NAME = " testValidate0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_request.productId = "1";
        l_request.password = "1";

        try
        {
            l_request.validate();
            assertTrue(true);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(TEST_START + STR_METHOD_NAME,l_ex);
            log.exiting(TEST_START + STR_METHOD_NAME);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error(TEST_START + STR_METHOD_NAME,l_ex);
            log.exiting(TEST_START + STR_METHOD_NAME);
            fail();
        }
    }

}
@
