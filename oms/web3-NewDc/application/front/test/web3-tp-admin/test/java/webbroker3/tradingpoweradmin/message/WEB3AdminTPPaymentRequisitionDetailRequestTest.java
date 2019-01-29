head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.35.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminTPPaymentRequisitionDetailRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������ڋq�����_�E�����[�h���N�G�X�gTest(WEB3AdminTPPaymentRequisitionDownLoadRequestTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/17 �I�O (���u) �V�K�쐬 
*/
package webbroker3.tradingpoweradmin.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;


public class WEB3AdminTPPaymentRequisitionDetailRequestTest extends TestBaseForMock
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTPPaymentRequisitionDownLoadRequestTest.class);

    public WEB3AdminTPPaymentRequisitionDetailRequestTest(String arg0)
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
     * Test method for 'webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionDetailRequest.validate()'
     */

    //�P�j���X�R�[�h�̃`�F�b�N
    //���X�R�[�h��null�̏ꍇ
    //��O���X���[����B
    public void testValidate_0001()
    {
        final String STR_METHOD_NAME = "validate";
        log.entering(STR_METHOD_NAME);

        WEB3AdminTPPaymentRequisitionDetailRequest l_request = new WEB3AdminTPPaymentRequisitionDetailRequest();

        try
        {
            //���X�R�[�h = null
            l_request.validate();
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00779);
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    //�P�j���X�R�[�h�̃`�F�b�N
    //���X�R�[�h.length��3�ȊO�̏ꍇ
    //��O���X���[����B
    public void testValidate_0002()
    {
        final String STR_METHOD_NAME = "validate";
        log.entering(STR_METHOD_NAME);

        WEB3AdminTPPaymentRequisitionDetailRequest l_request = new WEB3AdminTPPaymentRequisitionDetailRequest();

        try
        {
            //���X�R�[�h = "1234"
            l_request.branchCode = "1234";
            l_request.validate();
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00779);
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    //�P�j���X�R�[�h�̃`�F�b�N
    //���X�R�[�h�����p�����ȊO�̏ꍇ
    //��O���X���[����B
    public void testValidate_0003()
    {
        final String STR_METHOD_NAME = "validate";
        log.entering(STR_METHOD_NAME);

        WEB3AdminTPPaymentRequisitionDetailRequest l_request = new WEB3AdminTPPaymentRequisitionDetailRequest();

        try
        {
            //���X�R�[�h = "a12"
            l_request.branchCode = "a12";
            l_request.validate();
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00779);
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    //�Q�j�ڋq�R�[�h�̃`�F�b�N 
    //�ڋq�R�[�h��null�̏ꍇ
    //��O���X���[����B
    public void testValidate_0004()
    {
        final String STR_METHOD_NAME = "validate";
        log.entering(STR_METHOD_NAME);

        WEB3AdminTPPaymentRequisitionDetailRequest l_request = new WEB3AdminTPPaymentRequisitionDetailRequest();

        try
        {
            //���X�R�[�h = "123"
            //�ڋq�R�[�h = null
            l_request.branchCode = "123";
            l_request.validate();
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00780);
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    //�Q�j�ڋq�R�[�h�̃`�F�b�N 
    //�ڋq�R�[�h.length��6�ȊO�̏ꍇ
    //��O���X���[����B
    public void testValidate_0005()
    {
        final String STR_METHOD_NAME = "validate";
        log.entering(STR_METHOD_NAME);

        WEB3AdminTPPaymentRequisitionDetailRequest l_request = new WEB3AdminTPPaymentRequisitionDetailRequest();

        try
        {
            //���X�R�[�h = "123"
            //�ڋq�R�[�h = "1234567"
            l_request.branchCode = "123";
            l_request.accountCode = "1234567";
            l_request.validate();
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00780);
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    //�Q�j�ڋq�R�[�h�̃`�F�b�N 
    //�ڋq�R�[�h�����p�����ȊO�̏ꍇ
    //��O���X���[����B
    public void testValidate_0006()
    {
        final String STR_METHOD_NAME = "validate";
        log.entering(STR_METHOD_NAME);

        WEB3AdminTPPaymentRequisitionDetailRequest l_request = new WEB3AdminTPPaymentRequisitionDetailRequest();

        try
        {
            //���X�R�[�h = "123"
            //�ڋq�R�[�h = "�P�Q�R�S�T�U"
            l_request.branchCode = "123";
            l_request.accountCode = "�P�Q�R�S�T�U";
            l_request.validate();
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00780);
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidate_0007()
    {
        final String STR_METHOD_NAME = "validate";
        log.entering(STR_METHOD_NAME);

        WEB3AdminTPPaymentRequisitionDetailRequest l_request = new WEB3AdminTPPaymentRequisitionDetailRequest();

        try
        {
            //���X�R�[�h = "123"
            //�ڋq�R�[�h = "123456"
            l_request.branchCode = "123";
            l_request.accountCode = "123456";
            l_request.validate();
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

}
@
