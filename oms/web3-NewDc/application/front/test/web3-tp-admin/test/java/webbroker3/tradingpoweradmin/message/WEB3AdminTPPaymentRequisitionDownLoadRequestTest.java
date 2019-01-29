head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.35.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminTPPaymentRequisitionDownLoadRequestTest.java;


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
Revision History : 2008/10/16 �I�O (���u) �V�K�쐬 
*/
package webbroker3.tradingpoweradmin.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminTPPaymentRequisitionDownLoadRequestTest extends TestBaseForMock
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTPPaymentRequisitionDownLoadRequestTest.class);
    
    public WEB3AdminTPPaymentRequisitionDownLoadRequestTest(String arg0)
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
     * Test method for 'webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionDownLoadRequest.validate()'
     */

    //�P�j�ڋq�����̃`�F�b�N 
    //�ڋq������null�̏ꍇ 
    //��O���X���[����B
    public void testValidate_0001()
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminTPPaymentRequisitionDownLoadRequest l_request = new WEB3AdminTPPaymentRequisitionDownLoadRequest();
        
        try
        {
            //�ڋq���� = null
            l_request.validate();
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_03140);
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //�Q�j�������R�̃`�F�b�N �@@
    // �������R��null�̏ꍇ 
    // ��O���X���[����B
    public void testValidate_0002()
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminTPPaymentRequisitionDownLoadRequest l_request = new WEB3AdminTPPaymentRequisitionDownLoadRequest();
        
        try
        {
            //�ڋq���� = "0"
            //�������R = null
            l_request.customerAttribute = "0";
            l_request.validate();
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_03136);
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    //�Q�j�������R�̃`�F�b�N
    //���֋�/���ʗ��֋����I������Ă���ꍇ
    //������ 0 �łȂ���΁A��O���X���[����B
    public void testValidate_0003()
    {
        final String STR_METHOD_NAME = "validate";
        log.entering(STR_METHOD_NAME);

        WEB3AdminTPPaymentRequisitionDownLoadRequest l_request = new WEB3AdminTPPaymentRequisitionDownLoadRequest();

        try
        {
            //�ڋq���� = "0"
            //�������R = "1"
            //���� = "1"
            l_request.customerAttribute = "0";
            l_request.claimReason = "1";
            l_request.days = "1";
            l_request.validate();
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_03138);
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    //�Q�j�������R�̃`�F�b�N
    //�s�����i�����j���I������Ă���ꍇ
    //������ 0 �łȂ���΁A��O���X���[����B
    public void testValidate_0004()
    {
        final String STR_METHOD_NAME = "validate";
        log.entering(STR_METHOD_NAME);

        WEB3AdminTPPaymentRequisitionDownLoadRequest l_request = new WEB3AdminTPPaymentRequisitionDownLoadRequest();

        try
        {
            //�ڋq���� = "0"
            //�������R = "2"
            //���� = "1"
            l_request.customerAttribute = "0";
            l_request.claimReason = "2";
            l_request.days = "1";
            l_request.validate();
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_03138);
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    //�Q�j�������R�̃`�F�b�N
    //�w��Ȃ����I������Ă���ꍇ
    //������ 0 �łȂ���΁A��O���X���[����B
    public void testValidate_0005()
    {
        final String STR_METHOD_NAME = "validate";
        log.entering(STR_METHOD_NAME);

        WEB3AdminTPPaymentRequisitionDownLoadRequest l_request = new WEB3AdminTPPaymentRequisitionDownLoadRequest();

        try
        {
            //�ڋq���� = "0"
            //�������R = "5"
            //���� = "1"
            l_request.customerAttribute = "0";
            l_request.claimReason = "5";
            l_request.days = "1";
            l_request.validate();
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_03138);
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    //�R�j���X�R�[�h�̃`�F�b�N
    //���X�R�[�h��null�̏ꍇ
    //��O���X���[����B
    public void testValidate_0006()
    {
        final String STR_METHOD_NAME = "validate";
        log.entering(STR_METHOD_NAME);

        WEB3AdminTPPaymentRequisitionDownLoadRequest l_request = new WEB3AdminTPPaymentRequisitionDownLoadRequest();

        try
        {
            //�ڋq���� = "0"
            //�������R = "3"
            //���� = "1"
            //���X�R�[�h = null
            l_request.customerAttribute = "0";
            l_request.claimReason = "3";
            l_request.days = "1";
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

    //�R�j���X�R�[�h�̃`�F�b�N
    //���X�R�[�h.length��3�ȊO�̏ꍇ
    //��O���X���[����B
    public void testValidate_0007()
    {
        final String STR_METHOD_NAME = "validate";
        log.entering(STR_METHOD_NAME);

        WEB3AdminTPPaymentRequisitionDownLoadRequest l_request = new WEB3AdminTPPaymentRequisitionDownLoadRequest();

        try
        {
            //�ڋq���� = "0"
            //�������R = "3"
            //���� = "1"
            //���X�R�[�h = "1234"
            l_request.customerAttribute = "0";
            l_request.claimReason = "3";
            l_request.days = "1";
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

    //�R�j���X�R�[�h�̃`�F�b�N
    //���X�R�[�h�����p�����ȊO�̏ꍇ
    //��O���X���[����B
    public void testValidate_0008()
    {
        final String STR_METHOD_NAME = "validate";
        log.entering(STR_METHOD_NAME);

        WEB3AdminTPPaymentRequisitionDownLoadRequest l_request = new WEB3AdminTPPaymentRequisitionDownLoadRequest();

        try
        {
            //�ڋq���� = "0"
            //�������R = "3"
            //���� = "1"
            //���X�R�[�h = "�P�Q�R"
            l_request.customerAttribute = "0";
            l_request.claimReason = "3";
            l_request.days = "1";
            l_request.branchCode = "�P�Q�R";
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

    //�S�j�ڋq�R�[�h�̃`�F�b�N 
    //�ڋq�R�[�h��null�ȊO�@@and  �ڋq�R�[�h.length��6�ȊO �̏ꍇ
    //��O���X���[����B
    public void testValidate_0009()
    {
        final String STR_METHOD_NAME = "validate";
        log.entering(STR_METHOD_NAME);

        WEB3AdminTPPaymentRequisitionDownLoadRequest l_request = new WEB3AdminTPPaymentRequisitionDownLoadRequest();

        try
        {
            //�ڋq���� = "0"
            //�������R = "3"
            //���� = "1"
            //���X�R�[�h = "123"
            //�ڋq�R�[�h = "1234567"
            l_request.customerAttribute = "0";
            l_request.claimReason = "3";
            l_request.days = "1";
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

    //�S�j�ڋq�R�[�h�̃`�F�b�N 
    //�ڋq�R�[�h��null�ȊO�@@and  �ڋq�R�[�h�����p�����ȊO �̏ꍇ
    //��O���X���[����B
    public void testValidate_0010()
    {
        final String STR_METHOD_NAME = "validate";
        log.entering(STR_METHOD_NAME);

        WEB3AdminTPPaymentRequisitionDownLoadRequest l_request = new WEB3AdminTPPaymentRequisitionDownLoadRequest();

        try
        {
            //�ڋq���� = "0"
            //�������R = "3"
            //���� = "1"
            //���X�R�[�h = "123"
            //�ڋq�R�[�h = "�P�Q�R�S�T�U"
            l_request.customerAttribute = "0";
            l_request.claimReason = "3";
            l_request.days = "1";
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

    //�T�j���҃R�[�h�̃`�F�b�N 
    //���҃R�[�h��null�ȊO�@@and  ���҃R�[�h.length��5�ȊO�̏ꍇ
    //��O���X���[����B
    public void testValidate_0011()
    {
        final String STR_METHOD_NAME = "validate";
        log.entering(STR_METHOD_NAME);

        WEB3AdminTPPaymentRequisitionDownLoadRequest l_request = new WEB3AdminTPPaymentRequisitionDownLoadRequest();

        try
        {
            //�ڋq���� = "0"
            //�������R = "3"
            //���� = "1"
            //���X�R�[�h = "123"
            //�ڋq�R�[�h = "123456"
            //���҃R�[�h = "123456"
            l_request.customerAttribute = "0";
            l_request.claimReason = "3";
            l_request.days = "1";
            l_request.branchCode = "123";
            l_request.accountCode = "123456";
            l_request.traderCode = "123456";
            l_request.validate();
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_01912);
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidate_0012()
    {
        final String STR_METHOD_NAME = "validate";
        log.entering(STR_METHOD_NAME);

        WEB3AdminTPPaymentRequisitionDownLoadRequest l_request = new WEB3AdminTPPaymentRequisitionDownLoadRequest();

        try
        {
            //�ڋq���� = "0"
            //�������R = "3"
            //���� = "1"
            //���X�R�[�h = "123"
            //�ڋq�R�[�h = null
            //���҃R�[�h = null
            l_request.customerAttribute = "0";
            l_request.claimReason = "3";
            l_request.days = "1";
            l_request.branchCode = "123";
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
