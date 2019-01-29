head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.24.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3OptionsCloseMarginChangeCompleteRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���I�v�V���������ԍϊ������N�G�X�gTest(WEB3OptionsCloseMarginChangeCompleteRequestTest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2008/03/25 �����F (���u) �V�K�쐬
*/

package webbroker3.ifo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3OptionsCloseMarginChangeCompleteRequestTest extends TestBaseForMock
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3OptionsCloseMarginChangeCompleteRequestTest.class);
    
    WEB3OptionsCloseMarginChangeCompleteRequest l_request = new WEB3OptionsCloseMarginChangeCompleteRequest();

    public WEB3OptionsCloseMarginChangeCompleteRequestTest(String arg0)
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
     * Test method for 'webbroker3.ifo.message.WEB3OptionsCloseMarginChangeCompleteRequest.validateATReserveOrder()'
     */
    //�A�������Ή�
    //validateATReserveOrder
    //�X�[�p�[�N���X��validate���\�b�h���Ăяo��
    public void testValidateATReserveOrderCase1()
    {
        final String STR_METHOD_NAME = "test_validateATReserveOrder_case1()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_request.validateATReserveOrder();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00184);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }
    }
    
    //�Q�j�@@�h�c�`�F�b�N 
    //�@@this.�h�c=null �̏ꍇ�A��O���X���[����
    public void testValidateATReserveOrderCase2()
    {
        final String STR_METHOD_NAME = "test_validateATReserveOrder_case2()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
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
            
            l_request.validateATReserveOrder();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00600);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }
    }

    //�R�j�@@�ԍό��ʃ`�F�b�N 
    //�R�|�P�jthis.�ԍό���=null �̏ꍇ�A 
    //��O���X���[����B
    public void testValidateATReserveOrderCase3()
    {
        final String STR_METHOD_NAME = "test_validateATReserveOrder_case3()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
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
            
            l_request.id = "1101";
            
            l_request.validateATReserveOrder();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00178);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }
    }
    
    //�R�|�Q�jthis.�ԍό��ʂ̗v�f��=0 �̏ꍇ�A 
    //��O���X���[����
    public void testValidateATReserveOrderCase4()
    {
        final String STR_METHOD_NAME = "test_validateATReserveOrder_case4()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
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
            
            l_request.id = "1101";
            
            l_request.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[0];
            
            l_request.validateATReserveOrder();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00178);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }
    }
    
    //�S�j�@@�m�F���P���`�F�b�N 
    //�@@this.�m�F���P����null�̏ꍇ�A 
    //�@@�u�m�F���P����null�v�̗�O���X���[����
    public void testValidateATReserveOrderCase5()
    {
        final String STR_METHOD_NAME = "test_validateATReserveOrder_case5()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
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
            
            l_request.id = "1101";
            
            l_request.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[2];
            WEB3FuturesOptionsCloseMarginContractUnit l_unit1 = new WEB3FuturesOptionsCloseMarginContractUnit();
            l_request.closeMarginContractUnits[0] = l_unit1;
            l_request.validateATReserveOrder();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00206);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }
    }
    
    //�T�j�@@�m�F���������`�F�b�N 
    //�@@this.�m�F����������null�̏ꍇ�A 
    //�@@�u�m�F����������null�v�̗�O���X���[����
    public void testValidateATReserveOrderCase6()
    {
        final String STR_METHOD_NAME = "test_validateATReserveOrder_case6()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
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
            
            l_request.id = "1101";
            
            l_request.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[2];
            WEB3FuturesOptionsCloseMarginContractUnit l_unit1 = new WEB3FuturesOptionsCloseMarginContractUnit();
            l_request.closeMarginContractUnits[0] = l_unit1;
            
            l_request.checkPrice = "10";
            
            l_request.validateATReserveOrder();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00078);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }
    }
    
    //�U�j�@@�A�������E���������`�F�b�N 
    //�@@�X�[�p�[�N���X��validate�A���������\�b�h���R�[������
    public void testValidateATReserveOrderCase7()
    {
        final String STR_METHOD_NAME = "test_validateATReserveOrder_case7()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
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
            
            l_request.id = "1101";
            
            l_request.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[2];
            WEB3FuturesOptionsCloseMarginContractUnit l_unit1 = new WEB3FuturesOptionsCloseMarginContractUnit();
            l_request.closeMarginContractUnits[0] = l_unit1;
            
            l_request.checkPrice = "10";
            
            l_request.checkDate = WEB3DateUtility.getDate("20080325", "yyyyMMdd");
            
            l_request.validateATReserveOrder();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_02236);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }
    }

    //�ȏ�check����ʉ�
    public void testValidateATReserveOrderCase8()
    {
        final String STR_METHOD_NAME = "test_validateATReserveOrder_case8()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            //�����P���敪
            l_request.orderPriceDiv = "1";

            //�����P��
            l_request.limitPrice = "111";

            //���s����
//            l_request.execCondType = "1";

            //���������敪
            l_request.expirationDateType ="3";

            //�����L������
            l_request.expirationDate = null;

            //���������敪
//            l_request.orderCondType = "2";

            //�t�w�l�p�v���~�A���^�����Y���i
            l_request.stopPremium_underlyingAssets = null;

            //�t�w�l�p���������P��
            l_request.stopOrderCondPrice = null;

            //�t�w�l�p�����������Z�q
            l_request.stopOrderCondOperator = null;

//            //�v�w�l�p�v���~�A���^�����Y���i
//            l_request.wlimitPremium_underlyingAssets = null;
//
//            //�v�w�l�p���������P��
//            l_request.wlimitOrderCondPrice = null;
//
//            //�v�w�l�p�����������Z�q
//            l_request.wlimitOrderCondOperator = null;

            //�v�w�l�p�����P���敪
//            l_request.wLimitOrderPriceDiv = "1";
//
//            //�v�w�l�p�����P��
//            l_request.wLimitPrice = "1";
//            
//            //W�w�l�p���s����
//            l_request.wlimitExecCondType = "1";
//            
//            //W�w�l�p�L����ԋ敪
//            l_request.wlimitEnableStatusDiv = "1";
            
            l_request.id = "1101";
            
            l_request.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[2];
            WEB3FuturesOptionsCloseMarginContractUnit l_unit1 = new WEB3FuturesOptionsCloseMarginContractUnit();
            l_request.closeMarginContractUnits[0] = l_unit1;
            //���s�����`�F�b�N
            //���s������"������"�̏ꍇ
            l_request.execCondType = "1";
            
            //���������敪��"�w��Ȃ�"
            l_request.orderCondType = "0";
            
            l_request.checkPrice = "10";
            
            l_request.checkDate = WEB3DateUtility.getDate("20080325", "yyyyMMdd");
            
            l_request.validateATReserveOrder();
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
            
        }
    }
    
}
@
