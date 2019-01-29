head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.23.54;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FuturesCommonRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3FuturesCommonRequestTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2006/06/14 ���^�] (���u) �V�K�쐬
*/

package webbroker3.ifo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3FuturesCommonRequestTest extends TestBaseForMock
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility
        .getInstance(WEB3FuturesCommonRequestTest.class);

    public WEB3FuturesCommonRequestTest(String arg0)
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
    
    public void testValidate_0001()
    {
        String STR_METHOD_NAME = ".testValidate_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3FuturesCommonRequest l_request = new WEB3FuturesCommonRequest();
        l_request.futOrderQuantity = "1";
        l_request.orderPriceDiv = "1";
        l_request.limitPrice = "1";
        l_request.execCondType ="3";
        l_request.expirationDateType = "3";

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

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02817, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    public void testValidate_0002()
    {
        String STR_METHOD_NAME = ".testValidate_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3FuturesCommonRequest l_request = new WEB3FuturesCommonRequest();
        //��������
        l_request.futOrderQuantity = "1";
        
        //�����P���敪
        l_request.orderPriceDiv = "1";
        
        //�����P��
        l_request.limitPrice = "1";
        
        //���s����
        l_request.execCondType ="1";
        
        //���������敪
        l_request.expirationDateType = "3";
        
        //�����L������
        l_request.expirationDate = null;
        
        //���������敪
        l_request.orderCondType = "0";
        
        //�t�w�l�p���������P��
        l_request.stopOrderCondPrice = null;
        
        //�t�w�l�p�����������Z�q
        l_request.stopOrderCondOperator = null;
        
        //�v�w�l�p���������P��
        l_request.wlimitOrderCondPrice = null;
        
        //�v�w�l�p�����������Z�q
        l_request.wlimitOrderCondOperator = null;
        
        //�v�w�l�p�����P���敪
        l_request.wLimitOrderPriceDiv = null;
        
        //�v�w�l�p�����P��
        l_request.wLimitPrice = "0";
        
        //�v�w�l�p���s����
        l_request.wlimitExecCondType = null;
        
        //�v�w�l�p�L����ԋ敪
        l_request.wlimitEnableStatusDiv = "0";

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
    
    public void testValidate_0003()
    {
        String STR_METHOD_NAME = ".testValidate_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3FuturesCommonRequest l_request = new WEB3FuturesCommonRequest();
        //��������
        l_request.futOrderQuantity = "1";
        
        //�����P���敪
        l_request.orderPriceDiv = "1";
        
        //�����P��
        l_request.limitPrice = "1";
        
        //���s����
        l_request.execCondType ="1";
        
        //���������敪
        l_request.expirationDateType = "2";
        
        //�����L������
        l_request.expirationDate = WEB3DateUtility.getDate("20070301","yyyyMMdd");
        
        //���������敪
        l_request.orderCondType = "0";
        
        //�t�w�l�p���������P��
        l_request.stopOrderCondPrice = null;
        
        //�t�w�l�p�����������Z�q
        l_request.stopOrderCondOperator = null;
        
        //�v�w�l�p���������P��
        l_request.wlimitOrderCondPrice = null;
        
        //�v�w�l�p�����������Z�q
        l_request.wlimitOrderCondOperator = null;
        
        //�v�w�l�p�����P���敪
        l_request.wLimitOrderPriceDiv = null;
        
        //�v�w�l�p�����P��
        l_request.wLimitPrice = "0";
        
        //�v�w�l�p���s����
        l_request.wlimitExecCondType = null;
        
        //�v�w�l�p�L����ԋ敪
        l_request.wlimitEnableStatusDiv = "0";
        
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
    
    public void testValidate_0004()
    {
        String STR_METHOD_NAME = ".testValidate_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3FuturesCommonRequest l_request = new WEB3FuturesCommonRequest();
        //��������
        l_request.futOrderQuantity = "1";
        
        //�����P���敪
        l_request.orderPriceDiv = "1";
        
        //�����P��
        l_request.limitPrice = "1";
        
        //���s����
        l_request.execCondType ="1";
        
        //���������敪
        l_request.expirationDateType = "3";
        
        //�����L������
        l_request.expirationDate = WEB3DateUtility.getDate("20070301","yyyyMMdd");
        
        //���������敪
        l_request.orderCondType = "0";
        
        //�t�w�l�p���������P��
        l_request.stopOrderCondPrice = null;
        
        //�t�w�l�p�����������Z�q
        l_request.stopOrderCondOperator = null;
        
        //�v�w�l�p���������P��
        l_request.wlimitOrderCondPrice = null;
        
        //�v�w�l�p�����������Z�q
        l_request.wlimitOrderCondOperator = null;
        
        //�v�w�l�p�����P���敪
        l_request.wLimitOrderPriceDiv = null;
        
        //�v�w�l�p�����P��
        l_request.wLimitPrice = "0";
        
        //�v�w�l�p���s����
        l_request.wlimitExecCondType = null;
        
        //�v�w�l�p�L����ԋ敪
        l_request.wlimitEnableStatusDiv = "0";
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
            
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02818, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    public void testValidate_0005()
    {
        String STR_METHOD_NAME = ".testValidate_0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3FuturesCommonRequest l_request = new WEB3FuturesCommonRequest();
        //��������
        l_request.futOrderQuantity = "1";
        
        //�����P���敪
        l_request.orderPriceDiv = "1";
        
        //�����P��
        l_request.limitPrice = "1";
        
        //���s����
        l_request.execCondType ="1";
        
        //���������敪
        l_request.expirationDateType = "2";
        
        //�����L������
        l_request.expirationDate = WEB3DateUtility.getDate("20070301","yyyyMMdd");
        
        //���������敪
        l_request.orderCondType = "0";
        
        //�t�w�l�p���������P��
        l_request.stopOrderCondPrice = null;
        
        //�t�w�l�p�����������Z�q
        l_request.stopOrderCondOperator = null;
        
        //�v�w�l�p���������P��
        l_request.wlimitOrderCondPrice = null;
        
        //�v�w�l�p�����������Z�q
        l_request.wlimitOrderCondOperator = null;
        
        //�v�w�l�p�����P���敪
        l_request.wLimitOrderPriceDiv = null;
        
        //�v�w�l�p�����P��
        l_request.wLimitPrice = "0";
        
        //�v�w�l�p���s����
        l_request.wlimitExecCondType = null;
        
        //�v�w�l�p�L����ԋ敪
        l_request.wlimitEnableStatusDiv = "0";

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
    
    public void testValidate_0006()
    {
        String STR_METHOD_NAME = ".testValidate_0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3FuturesCommonRequest l_request = new WEB3FuturesCommonRequest();
        //��������
        l_request.futOrderQuantity = "1";
        
        //�����P���敪
        l_request.orderPriceDiv = "1";
        
        //�����P��
        l_request.limitPrice = "1";
        
        //���s����
        l_request.execCondType ="1";
        
        //���������敪
        l_request.expirationDateType = "3";
        
        //�����L������
        l_request.expirationDate = null;
        
        //���������敪
        l_request.orderCondType = "0";
        
        //�t�w�l�p���������P��
        l_request.stopOrderCondPrice = null;
        
        //�t�w�l�p�����������Z�q
        l_request.stopOrderCondOperator = null;
        
        //�v�w�l�p���������P��
        l_request.wlimitOrderCondPrice = null;
        
        //�v�w�l�p�����������Z�q
        l_request.wlimitOrderCondOperator = null;
        
        //�v�w�l�p�����P���敪
        l_request.wLimitOrderPriceDiv = null;
        
        //�v�w�l�p�����P��
        l_request.wLimitPrice = "0";
        
        //�v�w�l�p���s����
        l_request.wlimitExecCondType = null;
        
        //�v�w�l�p�L����ԋ敪
        l_request.wlimitEnableStatusDiv = "0";

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
    
    public void testValidate_0007()
    {
        String STR_METHOD_NAME = ".testValidate_0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3FuturesCommonRequest l_request = new WEB3FuturesCommonRequest();
        //��������
        l_request.futOrderQuantity = "1";
        
        //�����P���敪
        l_request.orderPriceDiv = "1";
        
        //�����P��
        l_request.limitPrice = "1";
        
        //���s����
        l_request.execCondType ="1";
        
        //���������敪
        l_request.expirationDateType = "3";
        
        //�����L������
        l_request.expirationDate = null;
        
        //���������敪
        l_request.orderCondType = "2";
        
        //�t�w�l�p���������P��
        l_request.stopOrderCondPrice = null;
        
        //�t�w�l�p�����������Z�q
        l_request.stopOrderCondOperator = null;
        
        //�v�w�l�p���������P��
        l_request.wlimitOrderCondPrice = "2";
        
        //�v�w�l�p�����������Z�q
        l_request.wlimitOrderCondOperator = "1";
        
        //�v�w�l�p�����P���敪
        l_request.wLimitOrderPriceDiv = "1";
        
        //�v�w�l�p�����P��
        l_request.wLimitPrice = "1";
        
        //�v�w�l�p���s����
        l_request.wlimitExecCondType = "7";
        
        //�v�w�l�p�L����ԋ敪
        l_request.wlimitEnableStatusDiv = "0";

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
            
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02503, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    public void testValidate_0008()
    {
        String STR_METHOD_NAME = ".testValidate_0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3FuturesCommonRequest l_request = new WEB3FuturesCommonRequest();
        //��������
        l_request.futOrderQuantity = "1";
        
        //�����P���敪
        l_request.orderPriceDiv = "1";
        
        //�����P��
        l_request.limitPrice = "1";
        
        //���s����
        l_request.execCondType ="1";
        
        //���������敪
        l_request.expirationDateType = "3";
        
        //�����L������
        l_request.expirationDate = null;
        
        //���������敪
        l_request.orderCondType = "0";
        
        //�t�w�l�p���������P��
        l_request.stopOrderCondPrice = null;
        
        //�t�w�l�p�����������Z�q
        l_request.stopOrderCondOperator = null;
        
        //�v�w�l�p���������P��
        l_request.wlimitOrderCondPrice = null;
        
        //�v�w�l�p�����������Z�q
        l_request.wlimitOrderCondOperator = null;
        
        //�v�w�l�p�����P���敪
        l_request.wLimitOrderPriceDiv = null;
        
        //�v�w�l�p�����P��
        l_request.wLimitPrice = null;
        
        //�v�w�l�p���s����
        l_request.wlimitExecCondType = null;
        
        //�v�w�l�p�L����ԋ敪
        l_request.wlimitEnableStatusDiv = "0";

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

    public void testValidate_0009()
    {
        String STR_METHOD_NAME = ".testValidate_0009()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3FuturesCommonRequest l_request = new WEB3FuturesCommonRequest();
        //��������
        l_request.futOrderQuantity = "1";
        
        //�����P���敪
        l_request.orderPriceDiv = "1";
        
        //�����P��
        l_request.limitPrice = "1";
        
        //���s����
        l_request.execCondType ="1";
        
        //���������敪
        l_request.expirationDateType = "1";
        
        //�����L������
        l_request.expirationDate = null;
        
        //���������敪
        l_request.orderCondType = "2";
        
        //�t�w�l�p���������P��
        l_request.stopOrderCondPrice = null;
        
        //�t�w�l�p�����������Z�q
        l_request.stopOrderCondOperator = null;
        
        //�v�w�l�p���������P��
        l_request.wlimitOrderCondPrice = "2";
        
        //�v�w�l�p�����������Z�q
        l_request.wlimitOrderCondOperator = "1";
        
        //�v�w�l�p�����P���敪
        l_request.wLimitOrderPriceDiv = "1";
        
        //�v�w�l�p�����P��
        l_request.wLimitPrice = "1";
        
        //�v�w�l�p���s����
        l_request.wlimitExecCondType = "7";
        
        //�v�w�l�p�L����ԋ敪
        l_request.wlimitEnableStatusDiv = "0";

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
    
    public void testValidate_0010()
    {
        String STR_METHOD_NAME = ".testValidate_0010()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3FuturesCommonRequest l_request = new WEB3FuturesCommonRequest();
        //��������
        l_request.futOrderQuantity = "1";
        
        //�����P���敪
        l_request.orderPriceDiv = "1";
        
        //�����P��
        l_request.limitPrice = "1";
        
        //���s����
        l_request.execCondType ="1";
        
        //���������敪
        l_request.expirationDateType = "3";
        
        //�����L������
        l_request.expirationDate = null;
        
        //���������敪
        l_request.orderCondType = "2";
        
        //�t�w�l�p���������P��
        l_request.stopOrderCondPrice = null;
        
        //�t�w�l�p�����������Z�q
        l_request.stopOrderCondOperator = null;
        
        //�v�w�l�p���������P��
        l_request.wlimitOrderCondPrice = "2";
        
        //�v�w�l�p�����������Z�q
        l_request.wlimitOrderCondOperator = "1";
        
        //�v�w�l�p�����P���敪
        l_request.wLimitOrderPriceDiv = "1";
        
        //�v�w�l�p�����P��
        l_request.wLimitPrice = "1";
        
        //�v�w�l�p���s����
        l_request.wlimitExecCondType = "1";
        
        //�v�w�l�p�L����ԋ敪
        l_request.wlimitEnableStatusDiv = "0";

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
    
    public void testValidate_0011()
    {
        String STR_METHOD_NAME = ".testValidate_0011()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3FuturesCommonRequest l_request = new WEB3FuturesCommonRequest();
        //��������
        l_request.futOrderQuantity = "1";
        
        //�����P���敪
        l_request.orderPriceDiv = "1";
        
        //�����P��
        l_request.limitPrice = "1";
        
        //���s����
        l_request.execCondType ="1";
        
        //���������敪
        l_request.expirationDateType = "4";
        
        //�����L������
        l_request.expirationDate = null;
        
        //���������敪
        l_request.orderCondType = "2";
        
        //�t�w�l�p���������P��
        l_request.stopOrderCondPrice = null;
        
        //�t�w�l�p�����������Z�q
        l_request.stopOrderCondOperator = null;
        
        //�v�w�l�p���������P��
        l_request.wlimitOrderCondPrice = "2";
        
        //�v�w�l�p�����������Z�q
        l_request.wlimitOrderCondOperator = "1";
        
        //�v�w�l�p�����P���敪
        l_request.wLimitOrderPriceDiv = "1";
        
        //�v�w�l�p�����P��
        l_request.wLimitPrice = "1";
        
        //�v�w�l�p���s����
        l_request.wlimitExecCondType = "1";
        
        //�v�w�l�p�L����ԋ敪
        l_request.wlimitEnableStatusDiv = "0";

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
            
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00209, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }

    //validate�A������
    //�P�j�@@���s�����`�F�b�N
    public void testValidateSuccOrder_0012()
    {
        final String STR_METHOD_NAME = "testValidateSuccOrder_C0012()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3FuturesCommonRequest l_request = new WEB3FuturesCommonRequest();
        try
        {
            l_request.execCondType = "3";
            l_request.validateSuccOrder();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02235, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //validate�A������
    //�Q�j�@@���������`�F�b�N
    public void testValidateSuccOrder_0013()
    {
        final String STR_METHOD_NAME = "testValidateSuccOrder_C0013()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3FuturesCommonRequest l_request = new WEB3FuturesCommonRequest();
        try
        {
            //�@@���s�����`�F�b�N
            l_request.execCondType = "1";
            //�Q�j�@@���������`�F�b�N
            l_request.orderCondType = "1";
            l_request.validateSuccOrder();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02236, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //validate�A������
    //normal case
    public void testValidateSuccOrder_0014()
    {
        final String STR_METHOD_NAME = "testValidateSuccOrder_C0014()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3FuturesCommonRequest l_request = new WEB3FuturesCommonRequest();
        try
        {
            //�@@���s�����`�F�b�N
            l_request.execCondType = "1";
            //�Q�j�@@���������`�F�b�N
            l_request.orderCondType = "0";
            l_request.validateSuccOrder();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
