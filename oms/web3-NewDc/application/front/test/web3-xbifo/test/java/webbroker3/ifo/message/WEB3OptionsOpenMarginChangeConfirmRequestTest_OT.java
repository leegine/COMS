head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.23.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3OptionsOpenMarginChangeConfirmRequestTest_OT.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���I�v�V���������V�K���m�F���N�G�X�g(WEB3OptionsOpenMarginChangeConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2008/06/05 �ŗm (���u) �V�K�쐬
*/

package webbroker3.ifo.message;

import test.util.JunitTestBase;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 *�i�����w���I�v�V���������V�K���m�F���N�G�X�g�̃e�X�g�j<BR>
 * 
 * @@author �ŗm
 * @@version 1.0
 */
public class WEB3OptionsOpenMarginChangeConfirmRequestTest_OT extends JunitTestBase 
{

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility
    .getInstance(WEB3OptionsOpenMarginChangeConfirmRequestTest_OT.class);
	
	public WEB3OptionsOpenMarginChangeConfirmRequestTest_OT(String arg0) 
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

    /**
     * �����P���敪�`�F�b�N
     * this.�����P���敪��null�̒l�ł���Η�O���X���[����B
     * �e�X�g�m�F���e: BUSINESS_ERROR_00184
     */
	public void testValidate_0001()
    {
        String STR_METHOD_NAME = "test_validate_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3OptionsOpenMarginChangeConfirmRequest l_request = new WEB3OptionsOpenMarginChangeConfirmRequest();
        
        l_request.orderPriceDiv = null;
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.debug("" + e.getErrorInfo());
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00184, e.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * �h�c�`�F�b�N
     * this.�h�c��null�̒l�ł���Η�O���X���[����B
     * �e�X�g�m�F���e: BUSINESS_ERROR_00080
     */
	public void testValidate_0002()
    {
        String STR_METHOD_NAME = "test_validate_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3OptionsOpenMarginChangeConfirmRequest l_request = new WEB3OptionsOpenMarginChangeConfirmRequest();
        
        //�����P���敪
        l_request.orderPriceDiv = "1";

        //�����P��
        l_request.limitPrice = "111";

        //���s����
        l_request.execCondType = "1";

        //���������敪
        l_request.expirationDateType ="3";

        //�����L������
        l_request.expirationDate = null;

        //���������敪
        l_request.orderCondType = "2";

        //�t�w�l�p�v���~�A���^�����Y���i
        l_request.stopPremium_underlyingAssets = null;

        //�t�w�l�p���������P��
        l_request.stopOrderCondPrice = null;

        //�t�w�l�p�����������Z�q
        l_request.stopOrderCondOperator = null;

        //�v�w�l�p�v���~�A���^�����Y���i
        l_request.wlimitPremium_underlyingAssets = "1";

        //�v�w�l�p���������P��
        l_request.wlimitOrderCondPrice = "2";

        //�v�w�l�p�����������Z�q
        l_request.wlimitOrderCondOperator = "1";

        //�v�w�l�p�����P���敪
        l_request.wLimitOrderPriceDiv = "1";

        //�v�w�l�p�����P��
        l_request.wLimitPrice = "1";
        
        //W�w�l�p���s����
        l_request.wlimitExecCondType = "1";
        
        //W�w�l�p�L����ԋ敪
        l_request.wlimitEnableStatusDiv = "1";
        
        l_request.id = null;
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.debug("" + e.getErrorInfo());
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00080, e.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }	

    /**
     * �������ʃ`�F�b�N
     * this.�������ʂ�null�̒l�ł���Η�O���X���[����B
     * �e�X�g�m�F���e: BUSINESS_ERROR_00074
     */
	public void testValidate_0003()
    {
        String STR_METHOD_NAME = "test_validate_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3OptionsOpenMarginChangeConfirmRequest l_request = new WEB3OptionsOpenMarginChangeConfirmRequest();
        
        
        //�����P���敪
        l_request.orderPriceDiv = "1";

        //�����P��
        l_request.limitPrice = "111";

        //���s����
        l_request.execCondType = "1";

        //���������敪
        l_request.expirationDateType ="3";

        //�����L������
        l_request.expirationDate = null;

        //���������敪
        l_request.orderCondType = "2";

        //�t�w�l�p�v���~�A���^�����Y���i
        l_request.stopPremium_underlyingAssets = null;

        //�t�w�l�p���������P��
        l_request.stopOrderCondPrice = null;

        //�t�w�l�p�����������Z�q
        l_request.stopOrderCondOperator = null;

        //�v�w�l�p�v���~�A���^�����Y���i
        l_request.wlimitPremium_underlyingAssets = "1";

        //�v�w�l�p���������P��
        l_request.wlimitOrderCondPrice = "2";

        //�v�w�l�p�����������Z�q
        l_request.wlimitOrderCondOperator = "1";

        //�v�w�l�p�����P���敪
        l_request.wLimitOrderPriceDiv = "1";

        //�v�w�l�p�����P��
        l_request.wLimitPrice = "1";
        
        //W�w�l�p���s����
        l_request.wlimitExecCondType = "1";
        
        //W�w�l�p�L����ԋ敪
        l_request.wlimitEnableStatusDiv = "1";
        
        //�h�c
        l_request.id = "0001";
        
        l_request.opOrderQuantity = null;
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.debug("" + e.getErrorInfo());
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00074, e.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * �������ʃ`�F�b�N
     * this.�������ʂ������ȊO�̒l�ł���Η�O���X���[����B
     * �e�X�g�m�F���e: BUSINESS_ERROR_00075
     */
	public void testValidate_0004()
    {
        String STR_METHOD_NAME = "test_validate_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3OptionsOpenMarginChangeConfirmRequest l_request = new WEB3OptionsOpenMarginChangeConfirmRequest();
        
        //�����P���敪
        l_request.orderPriceDiv = "1";

        //�����P��
        l_request.limitPrice = "111";

        //���s����
        l_request.execCondType = "1";

        //���������敪
        l_request.expirationDateType ="3";

        //�����L������
        l_request.expirationDate = null;

        //���������敪
        l_request.orderCondType = "2";

        //�t�w�l�p�v���~�A���^�����Y���i
        l_request.stopPremium_underlyingAssets = null;

        //�t�w�l�p���������P��
        l_request.stopOrderCondPrice = null;

        //�t�w�l�p�����������Z�q
        l_request.stopOrderCondOperator = null;

        //�v�w�l�p�v���~�A���^�����Y���i
        l_request.wlimitPremium_underlyingAssets = "1";

        //�v�w�l�p���������P��
        l_request.wlimitOrderCondPrice = "2";

        //�v�w�l�p�����������Z�q
        l_request.wlimitOrderCondOperator = "1";

        //�v�w�l�p�����P���敪
        l_request.wLimitOrderPriceDiv = "1";

        //�v�w�l�p�����P��
        l_request.wLimitPrice = "1";
        
        //W�w�l�p���s����
        l_request.wlimitExecCondType = "1";
        
        //W�w�l�p�L����ԋ敪
        l_request.wlimitEnableStatusDiv = "1";
        
        //�h�c
        l_request.id = "0001";
        
        l_request.opOrderQuantity = "a";
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.debug("" + e.getErrorInfo());
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00075, e.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * �������ʃ`�F�b�N
     * this.�������ʂ����O�̒l�ł���Η�O���X���[����B
     * �e�X�g�m�F���e: BUSINESS_ERROR_00076
     */
	public void testValidate_0005()
    {
        String STR_METHOD_NAME = "test_validate_0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3OptionsOpenMarginChangeConfirmRequest l_request = new WEB3OptionsOpenMarginChangeConfirmRequest();
        
        //�����P���敪
        l_request.orderPriceDiv = "1";

        //�����P��
        l_request.limitPrice = "111";

        //���s����
        l_request.execCondType = "1";

        //���������敪
        l_request.expirationDateType ="3";

        //�����L������
        l_request.expirationDate = null;

        //���������敪
        l_request.orderCondType = "2";

        //�t�w�l�p�v���~�A���^�����Y���i
        l_request.stopPremium_underlyingAssets = null;

        //�t�w�l�p���������P��
        l_request.stopOrderCondPrice = null;

        //�t�w�l�p�����������Z�q
        l_request.stopOrderCondOperator = null;

        //�v�w�l�p�v���~�A���^�����Y���i
        l_request.wlimitPremium_underlyingAssets = "1";

        //�v�w�l�p���������P��
        l_request.wlimitOrderCondPrice = "2";

        //�v�w�l�p�����������Z�q
        l_request.wlimitOrderCondOperator = "1";

        //�v�w�l�p�����P���敪
        l_request.wLimitOrderPriceDiv = "1";

        //�v�w�l�p�����P��
        l_request.wLimitPrice = "1";
        
        //W�w�l�p���s����
        l_request.wlimitExecCondType = "1";
        
        //W�w�l�p�L����ԋ敪
        l_request.wlimitEnableStatusDiv = "1";
        
        //�h�c
        l_request.id = "0001";
        
        l_request.opOrderQuantity = "-18";
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.debug("" + e.getErrorInfo());
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00076, e.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * �������ʃ`�F�b�N
     * this.�������ʂ��T���𒴂���l�ł���Η�O���X���[����B
     * �e�X�g�m�F���e: BUSINESS_ERROR_00077
     */
	public void testValidate_0006()
    {
        String STR_METHOD_NAME = "test_validate_0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3OptionsOpenMarginChangeConfirmRequest l_request = new WEB3OptionsOpenMarginChangeConfirmRequest();
        
        //�����P���敪
        l_request.orderPriceDiv = "1";

        //�����P��
        l_request.limitPrice = "111";

        //���s����
        l_request.execCondType = "1";

        //���������敪
        l_request.expirationDateType ="3";

        //�����L������
        l_request.expirationDate = null;

        //���������敪
        l_request.orderCondType = "2";

        //�t�w�l�p�v���~�A���^�����Y���i
        l_request.stopPremium_underlyingAssets = null;

        //�t�w�l�p���������P��
        l_request.stopOrderCondPrice = null;

        //�t�w�l�p�����������Z�q
        l_request.stopOrderCondOperator = null;

        //�v�w�l�p�v���~�A���^�����Y���i
        l_request.wlimitPremium_underlyingAssets = "1";

        //�v�w�l�p���������P��
        l_request.wlimitOrderCondPrice = "2";

        //�v�w�l�p�����������Z�q
        l_request.wlimitOrderCondOperator = "1";

        //�v�w�l�p�����P���敪
        l_request.wLimitOrderPriceDiv = "1";

        //�v�w�l�p�����P��
        l_request.wLimitPrice = "1";
        
        //W�w�l�p���s����
        l_request.wlimitExecCondType = "1";
        
        //W�w�l�p�L����ԋ敪
        l_request.wlimitEnableStatusDiv = "1";
        
        //�h�c
        l_request.id = "0001";
        
        l_request.opOrderQuantity = "123456";
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.debug("" + e.getErrorInfo());
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00077, e.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * �������ʃ`�F�b�N
     * �������� = "1000"
     * ����I��
     */
	public void testValidate_0007()
    {
        String STR_METHOD_NAME = "test_validate_0007()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3OptionsOpenMarginChangeConfirmRequest l_request = new WEB3OptionsOpenMarginChangeConfirmRequest();
        
        //�����P���敪
        l_request.orderPriceDiv = "1";

        //�����P��
        l_request.limitPrice = "111";

        //���s����
        l_request.execCondType = "1";

        //���������敪
        l_request.expirationDateType ="3";

        //�����L������
        l_request.expirationDate = null;

        //���������敪
        l_request.orderCondType = "2";

        //�t�w�l�p�v���~�A���^�����Y���i
        l_request.stopPremium_underlyingAssets = null;

        //�t�w�l�p���������P��
        l_request.stopOrderCondPrice = null;

        //�t�w�l�p�����������Z�q
        l_request.stopOrderCondOperator = null;

        //�v�w�l�p�v���~�A���^�����Y���i
        l_request.wlimitPremium_underlyingAssets = "1";

        //�v�w�l�p���������P��
        l_request.wlimitOrderCondPrice = "2";

        //�v�w�l�p�����������Z�q
        l_request.wlimitOrderCondOperator = "1";

        //�v�w�l�p�����P���敪
        l_request.wLimitOrderPriceDiv = "1";

        //�v�w�l�p�����P��
        l_request.wLimitPrice = "1";
        
        //W�w�l�p���s����
        l_request.wlimitExecCondType = "1";
        
        //W�w�l�p�L����ԋ敪
        l_request.wlimitEnableStatusDiv = "1";
        
        //�h�c
        l_request.id = "0001";
        
        //��������
        l_request.opOrderQuantity = "1000";
        
        try
        {
            l_request.validate();

        }
        catch (WEB3BaseException e)
        {
            log.debug("" + e.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
	
}
@
