head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.26.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3OptionsOpenMarginConfirmRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���I�v�V�����V�K�������m�F���N�G�X�g(WEB3OptionsOpenMarginConfirmRequestTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/10 ���^�](���u) �V�K�쐬
*/

package webbroker3.ifo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3OptionsOpenMarginConfirmRequestTest extends TestBaseForMock
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility
        .getInstance(WEB3OptionsOpenMarginConfirmRequestTest.class);

    public WEB3OptionsOpenMarginConfirmRequestTest(String arg0)
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
     * �Q�j�@@���敪�`�F�b�N
     * �Q�|�P�jthis.���敪��null�̒l�ł���Η�O���X���[����B
     */
    public void testValidate_0001()
    {
        String STR_METHOD_NAME = ".testValidate_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginConfirmRequest l_request = new WEB3OptionsOpenMarginConfirmRequest();
        //��������
        l_request.opOrderQuantity = "1";
        
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

        
        l_request.contractType = null;

        try
        {
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00263, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    //���敪�`�F�b�N
    //this.���敪���h1�F�����h�h2�F����"�ȊO�̏ꍇ��O���X���[����B
    public void testValidate_0002()
    {
        String STR_METHOD_NAME = ".testValidate_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginConfirmRequest l_request = new WEB3OptionsOpenMarginConfirmRequest();
        //��������
        l_request.opOrderQuantity = "1";
        
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

        
        l_request.contractType = "3";

        try
        {
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00264, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    //�R�|�P)����s��`�F�b�N
    public void testValidate_0003()
    {
        String STR_METHOD_NAME = ".testValidate_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginConfirmRequest l_request = new WEB3OptionsOpenMarginConfirmRequest();
        //��������
        l_request.opOrderQuantity = "1";
        
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

        
        l_request.contractType = "1";
        l_request.marketCode = null;
        
        try
        {
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00265, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    //�R�|�Q)this.����s�ꂪ�ȉ��̒l�ȊO�̒l�̏ꍇ��O���X���[����B
    //�@@�E�h1�F�����h 
    //�@@�E�h2�F���h
    public void testValidate_0004()
    {
        String STR_METHOD_NAME = ".testValidate_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginConfirmRequest l_request = new WEB3OptionsOpenMarginConfirmRequest();
        //��������
        l_request.opOrderQuantity = "1";
        
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

        
        l_request.contractType = "1";
        l_request.marketCode = "3";
        
        try
        {
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01087, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    //�S�|�P�w����ʃ`�F�b�N
    //this.�w����ʂ�null�̒l�ł���Η�O���X���[����B
    public void testValidate_0005()
    {
        String STR_METHOD_NAME = ".testValidate_0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginConfirmRequest l_request = new WEB3OptionsOpenMarginConfirmRequest();
        //��������
        l_request.opOrderQuantity = "1";
        
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

        
        l_request.contractType = "1";
        l_request.marketCode = "2";
        l_request.targetProductCode= null;
        
        try
        {
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00266, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    //�S�|�Q�jthis.�w����ʂ������ȊO�̒l�ł���Η�O���X���[����B
    public void testValidate_0006()
    {
        String STR_METHOD_NAME = ".testValidate_0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginConfirmRequest l_request = new WEB3OptionsOpenMarginConfirmRequest();
        //��������
        l_request.opOrderQuantity = "1";
        
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

        
        l_request.contractType = "1";
        l_request.marketCode = "2";
        l_request.targetProductCode= "a";
        
        try
        {
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02441, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    //�S�|�R�jthis.�w����ʂ̌������S���ȊO�̒l�ł���Η�O���X���[����B
    public void testValidate_0007()
    {
        String STR_METHOD_NAME = ".testValidate_0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginConfirmRequest l_request = new WEB3OptionsOpenMarginConfirmRequest();
        //��������
        l_request.opOrderQuantity = "1";
        
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

        
        l_request.contractType = "1";
        l_request.marketCode = "2";
        l_request.targetProductCode= "12345";

        try
        {
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02442, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    //�T�j�����`�F�b�N
    //�T�|�P�jthis.������null�̒l�ł���Η�O���X���[����B
    public void testValidate_0008()
    {
        String STR_METHOD_NAME = ".testValidate_0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginConfirmRequest l_request = new WEB3OptionsOpenMarginConfirmRequest();
        //��������
        l_request.opOrderQuantity = "1";
        
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

        
        l_request.contractType = "1";
        l_request.marketCode = "2";
        l_request.targetProductCode= "1234";
        l_request.delivaryMonth= null;
        
        try
        {
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00267, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    //�T�|�Q�jthis.�������x�x�x�x�l�l�`���̒l�ȊO�̒l�ł���Η�O���X���[����B
    public void testValidate_0009()
    {
        String STR_METHOD_NAME = ".testValidate_0009()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginConfirmRequest l_request = new WEB3OptionsOpenMarginConfirmRequest();
        //��������
        l_request.opOrderQuantity = "1";
        
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

        
        l_request.contractType = "1";
        l_request.marketCode = "2";
        l_request.targetProductCode= "1234";
        l_request.delivaryMonth= "abcdef";
        
        try
        {
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00268, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    //�U�j�@@�I�v�V�������i�敪�`�F�b�N
    //�@@�U�|�P�jthis.�I�v�V�������i�敪��null�̒l�ł���Η�O���X���[����B
    public void testValidate_0010()
    {
        String STR_METHOD_NAME = ".testValidate_0010()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginConfirmRequest l_request = new WEB3OptionsOpenMarginConfirmRequest();
        //��������
        l_request.opOrderQuantity = "1";
        
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

        
        l_request.contractType = "1";
        l_request.marketCode = "2";
        l_request.targetProductCode= "1234";
        l_request.delivaryMonth= "199912";
        l_request.opProductType=null;
        
        try
        {
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00269, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    //�U�|�Q�jthis.�I�v�V�������i�敪���ȉ��̒l�ȊO�̏ꍇ��O���X���[����B
    public void testValidate_0011()
    {
        String STR_METHOD_NAME = ".testValidate_0011()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginConfirmRequest l_request = new WEB3OptionsOpenMarginConfirmRequest();
        //��������
        l_request.opOrderQuantity = "1";
        
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

        
        l_request.contractType = "1";
        l_request.marketCode = "2";
        l_request.targetProductCode= "1234";
        l_request.delivaryMonth= "199912";
        l_request.opProductType="a";
        
        try
        {
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00270, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    //�V�j�@@�s�g���i�`�F�b�N
    //�@@�V�|�P�jthis.�s�g���i��null�̒l�ł���Η�O���X���[����B
    public void testValidate_0012()
    {
        String STR_METHOD_NAME = ".testValidate_0012()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginConfirmRequest l_request = new WEB3OptionsOpenMarginConfirmRequest();
        //��������
        l_request.opOrderQuantity = "1";
        
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

        
        l_request.contractType = "1";
        l_request.marketCode = "2";
        l_request.targetProductCode= "1234";
        l_request.delivaryMonth= "199912";
        l_request.opProductType="C";
        l_request.strikePrice =null;
        
        try
        {
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00271, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    //�V�|�Q�jthis.�s�g���i�������ȊO�̒l�ł���Η�O���X���[����B
    public void testValidate_0013()
    {
        String STR_METHOD_NAME = ".testValidate_0013()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginConfirmRequest l_request = new WEB3OptionsOpenMarginConfirmRequest();
        //��������
        l_request.opOrderQuantity = "1";
        
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

        
        l_request.contractType = "1";
        l_request.marketCode = "2";
        l_request.targetProductCode= "1234";
        l_request.delivaryMonth= "199912";
        l_request.opProductType="C";
        l_request.strikePrice ="a";
        
        try
        {
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00272, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    //�V�|�R�jthis.�s�g���i�����O�̒l�ł���Η�O���X���[����B
    public void testValidate_0014()
    {
        String STR_METHOD_NAME = ".testValidate_0014()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginConfirmRequest l_request = new WEB3OptionsOpenMarginConfirmRequest();
        //��������
        l_request.opOrderQuantity = "1";
        
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

        
        l_request.contractType = "1";
        l_request.marketCode = "2";
        l_request.targetProductCode= "1234";
        l_request.delivaryMonth= "199912";
        l_request.opProductType="C";
        l_request.strikePrice ="-1";
        
        try
        {
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00273, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    //�V�|�S�jthis.�s�g���i���W���𒴂���l�ł���Η�O���X���[����B
    public void testValidate_0015()
    {
        String STR_METHOD_NAME = ".testValidate_0015()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginConfirmRequest l_request = new WEB3OptionsOpenMarginConfirmRequest();
        //��������
        l_request.opOrderQuantity = "1";
        
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

        
        l_request.contractType = "1";
        l_request.marketCode = "2";
        l_request.targetProductCode= "1234";
        l_request.delivaryMonth= "199912";
        l_request.opProductType="C";
        l_request.strikePrice ="123456789";
        
        try
        {
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00274, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    //�W�|�P�jthis.�������ʂ�null�̒l�ł���Η�O���X���[����B
    public void testValidate_0016()
    {
        String STR_METHOD_NAME = ".testValidate_0016()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginConfirmRequest l_request = new WEB3OptionsOpenMarginConfirmRequest();
        //��������
        l_request.opOrderQuantity = "1";
        
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

        
        l_request.contractType = "1";
        l_request.marketCode = "2";
        l_request.targetProductCode= "1234";
        l_request.delivaryMonth= "199912";
        l_request.opProductType="C";
        l_request.strikePrice ="12345678";
        l_request.opOrderQuantity= null;
        
        try
        {
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00074, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    //�W�|�Q�jthis.�������ʂ������ȊO�̒l�ł���Η�O���X���[����B
    public void testValidate_0017()
    {
        String STR_METHOD_NAME = ".testValidate_0017()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginConfirmRequest l_request = new WEB3OptionsOpenMarginConfirmRequest();
        //��������
        l_request.opOrderQuantity = "1";
        
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

        
        l_request.contractType = "1";
        l_request.marketCode = "2";
        l_request.targetProductCode= "1234";
        l_request.delivaryMonth= "199912";
        l_request.opProductType="C";
        l_request.strikePrice ="12345678";
        l_request.opOrderQuantity= "a";
        
        try
        {
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00075, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    //�W�|�R�jthis.�������ʂ����O�̒l�ł���Η�O���X���[����B
    public void testValidate_0018()
    {
        String STR_METHOD_NAME = ".testValidate_0018()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginConfirmRequest l_request = new WEB3OptionsOpenMarginConfirmRequest();
        //��������
        l_request.opOrderQuantity = "1";
        
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

        
        l_request.contractType = "1";
        l_request.marketCode = "2";
        l_request.targetProductCode= "1234";
        l_request.delivaryMonth= "199912";
        l_request.opProductType="C";
        l_request.strikePrice ="12345678";
        l_request.opOrderQuantity= "-1";
        
        try
        {
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00076, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    //�W�|�S�jthis.�������ʂ��T���𒴂���l�ł���Η�O���X���[����B
    public void testValidate_0019()
    {
        String STR_METHOD_NAME = ".testValidate_0019()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginConfirmRequest l_request = new WEB3OptionsOpenMarginConfirmRequest();
        //��������
        l_request.opOrderQuantity = "1";
        
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

        
        l_request.contractType = "1";
        l_request.marketCode = "2";
        l_request.targetProductCode= "1234";
        l_request.delivaryMonth= "199912";
        l_request.opProductType="C";
        l_request.strikePrice ="12345678";
        l_request.opOrderQuantity= "123456";
        
        try
        {
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00077, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    //����
    public void testValidate_0020()
    {
        String STR_METHOD_NAME = ".testValidate_0020()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginConfirmRequest l_request = new WEB3OptionsOpenMarginConfirmRequest();
        //��������
        l_request.opOrderQuantity = "1";
        
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

        
        l_request.contractType = "1";
        l_request.marketCode = "2";
        l_request.targetProductCode= "1234";
        l_request.delivaryMonth= "199912";
        l_request.opProductType="C";
        l_request.strikePrice ="12345678";
        l_request.opOrderQuantity= "12345";
        
        try
        {
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
}
@
