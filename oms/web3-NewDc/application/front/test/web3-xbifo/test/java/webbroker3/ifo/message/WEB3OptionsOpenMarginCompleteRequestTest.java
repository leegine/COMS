head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.25.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3OptionsOpenMarginCompleteRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���I�v�V�����V�K�������������N�G�X�g(WEB3OptionsOpenMarginCompleteRequestTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/11 ���^�](���u) �V�K�쐬
*/

package webbroker3.ifo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

public class WEB3OptionsOpenMarginCompleteRequestTest extends TestBaseForMock
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility
        .getInstance(WEB3OptionsOpenMarginCompleteRequestTest.class);

    public WEB3OptionsOpenMarginCompleteRequestTest(String arg0)
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
    
    
    //�Q�j�@@�����`�F�b�N 
    //this.�����R�[�h��null�̏ꍇ�A���L�����ȊO�͗�O�u�����w��G���[�v���X���[����B 
    //�@@�Ethis.�w�����!=null�@@&&�@@this.����!=null�@@&&�@@
    //     this.�I�v�V�������i�敪!=null�@@&&�@@this.�s�g���i!=null
    public void testValidate_0001()
    {
        String STR_METHOD_NAME = ".testValidate_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginCompleteRequest l_request = new WEB3OptionsOpenMarginCompleteRequest();
        
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

        l_request.opProductCode= null; 
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

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00334, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    //�Q�j�@@�����`�F�b�N 
    //this.�����R�[�h��null�̏ꍇ�A���L�����ȊO�͗�O�u�����w��G���[�v���X���[����B 
    //�@@�Ethis.�w�����!=null�@@&&�@@this.����!=null�@@&&�@@
    //     this.�I�v�V�������i�敪!=null�@@&&�@@this.�s�g���i!=null
    public void testValidate_0002()
    {
        String STR_METHOD_NAME = ".testValidate_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginCompleteRequest l_request = new WEB3OptionsOpenMarginCompleteRequest();
        
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

        l_request.opProductCode= null; 
        l_request.targetProductCode= "0005"; 
        l_request.delivaryMonth = null;
        
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

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00334, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
//  �Q�j�@@�����`�F�b�N 
    //this.�����R�[�h��null�̏ꍇ�A���L�����ȊO�͗�O�u�����w��G���[�v���X���[����B 
    //�@@�Ethis.�w�����!=null�@@&&�@@this.����!=null�@@&&�@@
    //     this.�I�v�V�������i�敪!=null�@@&&�@@this.�s�g���i!=null
    public void testValidate_0003()
    {
        String STR_METHOD_NAME = ".testValidate_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginCompleteRequest l_request = new WEB3OptionsOpenMarginCompleteRequest();
        
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

        l_request.opProductCode= null; 
        l_request.targetProductCode= "0005"; 
        l_request.delivaryMonth = "199912";
        l_request.opProductType = null;
        
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

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00334, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
//  �Q�j�@@�����`�F�b�N 
    //this.�����R�[�h��null�̏ꍇ�A���L�����ȊO�͗�O�u�����w��G���[�v���X���[����B 
    //�@@�Ethis.�w�����!=null�@@&&�@@this.����!=null�@@&&�@@
    //     this.�I�v�V�������i�敪!=null�@@&&�@@this.�s�g���i!=null
    public void testValidate_0004()
    {
        String STR_METHOD_NAME = ".testValidate_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginCompleteRequest l_request = new WEB3OptionsOpenMarginCompleteRequest();
        
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

        l_request.opProductCode= null; 
        l_request.targetProductCode= "0005"; 
        l_request.delivaryMonth = "199912";
        l_request.opProductType = "C";
        l_request.strikePrice=null;
        
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

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00334, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    //�R�j�@@���敪�`�F�b�N
    //�@@�R�|�P�jthis.���敪��null�̒l�ł���Η�O���X���[����B
    public void testValidate_0005()
    {
        String STR_METHOD_NAME = ".testValidate_0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginCompleteRequest l_request = new WEB3OptionsOpenMarginCompleteRequest();
        
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

        l_request.opProductCode= null; 
        l_request.targetProductCode= "0005"; 
        l_request.delivaryMonth = "199912";
        l_request.opProductType = "C";
        l_request.strikePrice="1";
        l_request.contractType=null;
        
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
    
    //�@@�R�|�Q�jthis.���敪���ȉ��̒l�ȊO�̏ꍇ��O���X���[����B
    //�@@�@@�@@�@@�E�h1�F�����h
    //�@@�@@�@@�@@�E�h2�F�����h
    public void testValidate_0006()
    {
        String STR_METHOD_NAME = ".testValidate_0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginCompleteRequest l_request = new WEB3OptionsOpenMarginCompleteRequest();
        
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

        l_request.opProductCode= null; 
        l_request.targetProductCode= "0005"; 
        l_request.delivaryMonth = "199912";
        l_request.opProductType = "C";
        l_request.strikePrice="1";
        l_request.contractType="4";
        
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
    
    
    //�S�j�@@����s��`�F�b�N
    //�@@�S�|�P�jthis.����s�ꂪnull�̒l�ł���Η�O���X���[����B
    public void testValidate_0007()
    {
        String STR_METHOD_NAME = ".testValidate_0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginCompleteRequest l_request = new WEB3OptionsOpenMarginCompleteRequest();
        
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

        l_request.opProductCode= null; 
        l_request.targetProductCode= "0005"; 
        l_request.delivaryMonth = "199912";
        l_request.opProductType = "C";
        l_request.strikePrice="1";
        l_request.contractType="1";
        l_request.marketCode=null;
        
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
    
    
    //�@@�S�|�Q)this.����s�ꂪ�ȉ��̒l�ȊO�̒l�̏ꍇ��O���X���[����B 
    //       �E�h1�F�����h
    //       �E�h2�F���h
    public void testValidate_0008()
    {
        String STR_METHOD_NAME = ".testValidate_0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginCompleteRequest l_request = new WEB3OptionsOpenMarginCompleteRequest();
        
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

        l_request.opProductCode= null; 
        l_request.targetProductCode= "0005"; 
        l_request.delivaryMonth = "199912";
        l_request.opProductType = "C";
        l_request.strikePrice="1";
        l_request.contractType="1";
        l_request.marketCode="4";
        
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
      
    //�T�j�@@�������ʃ`�F�b�N
    //�@@�T�|�P�jthis.�������ʂ�null�̒l�ł���Η�O���X���[����B
    public void testValidate_0009()
    {
        String STR_METHOD_NAME = ".testValidate_0009()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginCompleteRequest l_request = new WEB3OptionsOpenMarginCompleteRequest();
        
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

        l_request.opProductCode= null; 
        l_request.targetProductCode= "0005"; 
        l_request.delivaryMonth = "199912";
        l_request.opProductType = "C";
        l_request.strikePrice="1";
        l_request.contractType="1";
        l_request.marketCode="1";
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
    
    //�@@�T�|�Q�jthis.�������ʂ������ȊO�̒l�ł���Η�O���X���[����B
    public void testValidate_0010()
    {
        String STR_METHOD_NAME = ".testValidate_0010()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginCompleteRequest l_request = new WEB3OptionsOpenMarginCompleteRequest();
        
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

        l_request.opProductCode= null; 
        l_request.targetProductCode= "0005"; 
        l_request.delivaryMonth = "199912";
        l_request.opProductType = "C";
        l_request.strikePrice="1";
        l_request.contractType="1";
        l_request.marketCode="1";
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
    
    //�@@�T�|�R�jthis.�������ʂ����O�̒l�ł���Η�O���X���[����B
    public void testValidate_0011()
    {
        String STR_METHOD_NAME = ".testValidate_0011()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginCompleteRequest l_request = new WEB3OptionsOpenMarginCompleteRequest();
        
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

        l_request.opProductCode= null; 
        l_request.targetProductCode= "0005"; 
        l_request.delivaryMonth = "199912";
        l_request.opProductType = "C";
        l_request.strikePrice="1";
        l_request.contractType="1";
        l_request.marketCode="1";
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
    
    // �@@�T�|�S�jthis.�������ʂ��T���𒴂���l�ł���Η�O���X���[����B
    public void testValidate_0012()
    {
        String STR_METHOD_NAME = ".testValidate_0012()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginCompleteRequest l_request = new WEB3OptionsOpenMarginCompleteRequest();
        
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

        l_request.opProductCode= null; 
        l_request.targetProductCode= "0005"; 
        l_request.delivaryMonth = "199912";
        l_request.opProductType = "C";
        l_request.strikePrice="1";
        l_request.contractType="1";
        l_request.marketCode="1";
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

    // �V�j�@@�m�F���������`�F�b�N(this.����ID==null�̏ꍇ�A�`�F�b�N���s��Ȃ��j
    // �@@this.�m�F����������null�̒l�ł���Η�O���X���[����B
    public void testValidate_0013()
    {
        String STR_METHOD_NAME = ".testValidate_0013()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginCompleteRequest l_request = new WEB3OptionsOpenMarginCompleteRequest();
        
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

        l_request.opProductCode= null; 
        l_request.targetProductCode= "0005"; 
        l_request.delivaryMonth = "199912";
        l_request.opProductType = "C";
        l_request.strikePrice="1";
        l_request.contractType="1";
        l_request.marketCode="1";
        l_request.opOrderQuantity= "12345";
        l_request.orderId="1";
        l_request.checkDate=null;
        
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

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00078, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    //����
    public void testValidate_0014()
    {
        String STR_METHOD_NAME = ".testValidate_0014()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginCompleteRequest l_request = new WEB3OptionsOpenMarginCompleteRequest();
        
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

        l_request.opProductCode= null; 
        l_request.targetProductCode= "0005"; 
        l_request.delivaryMonth = "199912";
        l_request.opProductType = "C";
        l_request.strikePrice="1";
        l_request.contractType="1";
        l_request.marketCode="1";
        l_request.opOrderQuantity= "12345";
        l_request.orderId=null;
        l_request.checkDate=null;
        
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
