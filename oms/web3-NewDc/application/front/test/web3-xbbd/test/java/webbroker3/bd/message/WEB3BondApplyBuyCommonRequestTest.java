head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.21.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3BondApplyBuyCommonRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : //TODO(WEB3BondApplyBuyCommonRequestTest)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/05/09 ���g(���u) �V�K�쐬
Revesion History : 2007/07/05  �yWEB3�z�yCITI�t�����g�����i���j�z�Č�����C���{����
*/
package webbroker3.bd.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * @@author ���g(���u)
 * @@version 1.0
 */
public class WEB3BondApplyBuyCommonRequestTest extends TestBaseForMock
{
    /**
     *�@@���O���[�e�B���e�B<BR> 
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3BondApplyBuyCommonRequestTest.class);

    public WEB3BondApplyBuyCommonRequestTest(String name)
    {
        super(name);
        // TODO Auto-generated constructor stub
    }

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

//    /*
//     * WEB3BondApplyBuyCommonRequest<br>
//     * ������/���t���ʃ��N�G�X�g<br>
//     * ����敪 == "1"<br>
//     *����ID != null<br>
//     *���ϋ敪 == "1"<br>
//     *�z�ʋ��z == "11111"<br>
//     *�w�l�敪 == 0�̏ꍇ<br>
//     */
//    public  void test_validate_C0001()
//    {
//        final String STR_METHOD_NAME = "test_validate_C0001()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            WEB3BondApplyBuyCommonRequest l_bondApplyBuyCommonRequest = new WEB3BondApplyBuyCommonRequest();
//            l_bondApplyBuyCommonRequest.tradeDiv = "1";
//            l_bondApplyBuyCommonRequest.productId = "1001";
//            l_bondApplyBuyCommonRequest.settleDiv = "1";
//            l_bondApplyBuyCommonRequest.faceAmount = "11111";
//            l_bondApplyBuyCommonRequest.orderPriceDiv = "0";
//            l_bondApplyBuyCommonRequest.validate();
//            fail();
//        }
//        catch (WEB3BaseException l_web3SystemException)
//        {
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02763,l_web3SystemException.getErrorInfo());
//        }
//        catch (Exception l_ex)
//        {
//            log.error(STR_METHOD_NAME, l_ex);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//
//    /*
//     * WEB3BondApplyBuyCommonRequest<br>
//     * ������/���t���ʃ��N�G�X�g<br>
//     * ����敪 == "1"<br>
//     *����ID != null<br>
//     *���ϋ敪 == "1"<br>
//     *�z�ʋ��z == "11111"<br>
//     *�w�l�敪 == 1�̏ꍇ<br>
//     *���t�P��==null�̏ꍇ<br>
//     */
//    public  void test_validate_C0002()
//    {
//        final String STR_METHOD_NAME = "test_validate_C0002()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            WEB3BondApplyBuyCommonRequest l_bondApplyBuyCommonRequest = new WEB3BondApplyBuyCommonRequest();
//            l_bondApplyBuyCommonRequest.tradeDiv = "1";
//            l_bondApplyBuyCommonRequest.productId = "1001";
//            l_bondApplyBuyCommonRequest.settleDiv = "1";
//            l_bondApplyBuyCommonRequest.faceAmount = "11111";
//            l_bondApplyBuyCommonRequest.orderPriceDiv = "1";
//            l_bondApplyBuyCommonRequest.buyPrice = null;
//            l_bondApplyBuyCommonRequest.validate();
//            fail();
//        }
//        catch (WEB3BaseException l_web3SystemException)
//        {
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02764,l_web3SystemException.getErrorInfo());
//        }
//        catch (Exception l_ex)
//        {
//            log.error(STR_METHOD_NAME, l_ex);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//
//    /*
//     * WEB3BondApplyBuyCommonRequest<br>
//     * ������/���t���ʃ��N�G�X�g<br>
//     * ����敪 == "1"<br>
//     *����ID != null<br>
//     *���ϋ敪 == "1"<br>
//     *�z�ʋ��z == "11111"<br>
//     *�w�l�敪 == 1�̏ꍇ<br>
//     *���t�P��==11111.11�̏ꍇ<br>
//     */
//    public  void test_validate_C0003()
//    {
//        final String STR_METHOD_NAME = "test_validate_C0003()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            WEB3BondApplyBuyCommonRequest l_bondApplyBuyCommonRequest = new WEB3BondApplyBuyCommonRequest();
//            l_bondApplyBuyCommonRequest.tradeDiv = "1";
//            l_bondApplyBuyCommonRequest.productId = "1001";
//            l_bondApplyBuyCommonRequest.settleDiv = "1";
//            l_bondApplyBuyCommonRequest.faceAmount = "11111";
//            l_bondApplyBuyCommonRequest.orderPriceDiv = "1";
//            l_bondApplyBuyCommonRequest.buyPrice = "11111.11";
//            l_bondApplyBuyCommonRequest.validate();
//            fail();
//        }
//        catch (WEB3BaseException l_web3SystemException)
//        {
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02765,l_web3SystemException.getErrorInfo());
//        }
//        catch (Exception l_ex)
//        {
//            log.error(STR_METHOD_NAME, l_ex);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//    
//    /*
//     * WEB3BondApplyBuyCommonRequest<br>
//     * ������/���t���ʃ��N�G�X�g<br>
//     * ����敪 == "1"<br>
//     *����ID != null<br>
//     *���ϋ敪 == "1"<br>
//     *�z�ʋ��z == "11111"<br>
//     *�w�l�敪 == 1�̏ꍇ<br>
//     *���t�P��==1.11a�̏ꍇ<br>
//     */
//    public  void test_validate_C0004()
//    {
//        final String STR_METHOD_NAME = "test_validate_C0004()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            WEB3BondApplyBuyCommonRequest l_bondApplyBuyCommonRequest = new WEB3BondApplyBuyCommonRequest();
//            l_bondApplyBuyCommonRequest.tradeDiv = "1";
//            l_bondApplyBuyCommonRequest.productId = "1001";
//            l_bondApplyBuyCommonRequest.settleDiv = "1";
//            l_bondApplyBuyCommonRequest.faceAmount = "11111";
//            l_bondApplyBuyCommonRequest.orderPriceDiv = "1";
//            l_bondApplyBuyCommonRequest.buyPrice = "1.11a";
//            l_bondApplyBuyCommonRequest.validate();
//            fail();
//        }
//        catch (WEB3BaseException l_web3SystemException)
//        {
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02766,l_web3SystemException.getErrorInfo());
//        }
//        catch (Exception l_ex)
//        {
//            log.error(STR_METHOD_NAME, l_ex);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//    
//    /*
//     * WEB3BondApplyBuyCommonRequest<br>
//     * ������/���t���ʃ��N�G�X�g<br>
//     * ����敪 == "1"<br>
//     *����ID != null<br>
//     *���ϋ敪 == "1"<br>
//     *�z�ʋ��z == "11111"<br>
//     *�w�l�敪 == 1�̏ꍇ<br>
//     *���t�P��==-1.11�̏ꍇ<br>
//     */
//    public  void test_validate_C0005()
//    {
//        final String STR_METHOD_NAME = "test_validate_C0005()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            WEB3BondApplyBuyCommonRequest l_bondApplyBuyCommonRequest = new WEB3BondApplyBuyCommonRequest();
//            l_bondApplyBuyCommonRequest.tradeDiv = "1";
//            l_bondApplyBuyCommonRequest.productId = "1001";
//            l_bondApplyBuyCommonRequest.settleDiv = "1";
//            l_bondApplyBuyCommonRequest.faceAmount = "11111";
//            l_bondApplyBuyCommonRequest.orderPriceDiv = "1";
//            l_bondApplyBuyCommonRequest.buyPrice = "-1.11";
//            l_bondApplyBuyCommonRequest.validate();
//            fail();
//        }
//        catch (WEB3BaseException l_web3SystemException)
//        {
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02767,l_web3SystemException.getErrorInfo());
//        }
//        catch (Exception l_ex)
//        {
//            log.error(STR_METHOD_NAME, l_ex);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//    
//    /*
//     * WEB3BondApplyBuyCommonRequest<br>
//     * ������/���t���ʃ��N�G�X�g<br>
//     * ����敪 == "1"<br>
//     *����ID != null<br>
//     *���ϋ敪 == "1"<br>
//     *�z�ʋ��z == "11111"<br>
//     *�w�l�敪 == 1�̏ꍇ<br>
//     *���t�P��==1.11�̏ꍇ<br>
//     *�����t���O �� null�̏ꍇ<br>
//     *�����t���O���ȉ��̒l == 0<br>
//     */
//    public  void test_validate_C0006()
//    {
//        final String STR_METHOD_NAME = "test_validate_C0006()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            WEB3BondApplyBuyCommonRequest l_bondApplyBuyCommonRequest = new WEB3BondApplyBuyCommonRequest();
//            l_bondApplyBuyCommonRequest.tradeDiv = "1";
//            l_bondApplyBuyCommonRequest.productId = "1001";
//            l_bondApplyBuyCommonRequest.settleDiv = "1";
//            l_bondApplyBuyCommonRequest.faceAmount = "11111";
//            l_bondApplyBuyCommonRequest.orderPriceDiv = "1";
//            l_bondApplyBuyCommonRequest.buyPrice = "1.11";
//            l_bondApplyBuyCommonRequest.recommendedFlag = "0";
//            l_bondApplyBuyCommonRequest.validate();
//            fail();
//        }
//        catch (WEB3BaseException l_web3SystemException)
//        {
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02768,l_web3SystemException.getErrorInfo());
//        }
//        catch (Exception l_ex)
//        {
//            log.error(STR_METHOD_NAME, l_ex);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//
//    /*
//     * WEB3BondApplyBuyCommonRequest<br>
//     * ������/���t���ʃ��N�G�X�g<br>
//     * ����敪 == "1"<br>
//     *����ID != null<br>
//     *���ϋ敪 == "1"<br>
//     *�z�ʋ��z == "11111"<br>
//     *�w�l�敪 == 1�̏ꍇ<br>
//     *���t�P��==1.11�̏ꍇ<br>
//     *�����t���O �� null�̏ꍇ<br>
//     *�����t���O���ȉ��̒l == 1<br>
//     *�בփ��X�N�t���O���ȉ��̒l == 0<br>
//     */
//    public  void test_validate_C0007()
//    {
//        final String STR_METHOD_NAME = "test_validate_C0007()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            WEB3BondApplyBuyCommonRequest l_bondApplyBuyCommonRequest = new WEB3BondApplyBuyCommonRequest();
//            l_bondApplyBuyCommonRequest.tradeDiv = "1";
//            l_bondApplyBuyCommonRequest.productId = "1001";
//            l_bondApplyBuyCommonRequest.settleDiv = "1";
//            l_bondApplyBuyCommonRequest.faceAmount = "11111";
//            l_bondApplyBuyCommonRequest.orderPriceDiv = "1";
//            l_bondApplyBuyCommonRequest.buyPrice = "1.11";
//            l_bondApplyBuyCommonRequest.recommendedFlag = "1";
//            l_bondApplyBuyCommonRequest.fxRiskFlag = "0";
//            l_bondApplyBuyCommonRequest.validate();
//            fail();
//        }
//        catch (WEB3BaseException l_web3SystemException)
//        {
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02769,l_web3SystemException.getErrorInfo());
//        }
//        catch (Exception l_ex)
//        {
//            log.error(STR_METHOD_NAME, l_ex);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//
//    /*
//     * WEB3BondApplyBuyCommonRequest<br>
//     * ������/���t���ʃ��N�G�X�g<br>
//     * ����敪 == "1"<br>
//     *����ID != null<br>
//     *���ϋ敪 == "1"<br>
//     *�z�ʋ��z == "11111"<br>
//     *�w�l�敪 == 1�̏ꍇ<br>
//     *���t�P��==1.11�̏ꍇ<br>
//     *�����t���O �� null�̏ꍇ<br>
//     *�����t���O���ȉ��̒l == 1<br>
//     *�בփ��X�N�t���O���ȉ��̒l == 1<br>
//     *���l > 500�̏ꍇ<br>
//     */
//    public  void test_validate_C0008()
//    {
//        final String STR_METHOD_NAME = "test_validate_C0008()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            WEB3BondApplyBuyCommonRequest l_bondApplyBuyCommonRequest = new WEB3BondApplyBuyCommonRequest();
//            l_bondApplyBuyCommonRequest.tradeDiv = "1";
//            l_bondApplyBuyCommonRequest.productId = "1001";
//            l_bondApplyBuyCommonRequest.settleDiv = "1";
//            l_bondApplyBuyCommonRequest.faceAmount = "11111";
//            l_bondApplyBuyCommonRequest.orderPriceDiv = "1";
//            l_bondApplyBuyCommonRequest.buyPrice = "1.11";
//            l_bondApplyBuyCommonRequest.recommendedFlag = "1";
//            l_bondApplyBuyCommonRequest.fxRiskFlag = "1";
//            String l_str = "aaaaaaaaaaaaa";
//            StringBuffer l_stringBuffer = new StringBuffer();
//            for(int i = 0;i < 50; i++)
//            {
//                l_stringBuffer.append(l_str);
//            }
//            l_bondApplyBuyCommonRequest.bdRemark = l_stringBuffer.toString();
//            l_bondApplyBuyCommonRequest.validate();
//            fail();
//        }
//        catch (WEB3BaseException l_web3SystemException)
//        {
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00487,l_web3SystemException.getErrorInfo());
//        }
//        catch (Exception l_ex)
//        {
//            log.error(STR_METHOD_NAME, l_ex);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//    
//    /*
//     * WEB3BondApplyBuyCommonRequest<br>
//     * ������/���t���ʃ��N�G�X�g<br>
//     * ����敪 == "1"<br>
//     *����ID != null<br>
//     *���ϋ敪 == "1"<br>
//     *�z�ʋ��z == "11111"<br>
//     *�w�l�敪 == 1�̏ꍇ<br>
//     *���t�P��==1.11�̏ꍇ<br>
//     */
//    public  void test_validate_C0006()
//    {
//        final String STR_METHOD_NAME = "test_validate_C0006()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            WEB3BondApplyBuyCommonRequest l_bondApplyBuyCommonRequest = new WEB3BondApplyBuyCommonRequest();
//            l_bondApplyBuyCommonRequest.tradeDiv = "1";
//            l_bondApplyBuyCommonRequest.productId = "1001";
//            l_bondApplyBuyCommonRequest.settleDiv = "1";
//            l_bondApplyBuyCommonRequest.faceAmount = "11111";
//            l_bondApplyBuyCommonRequest.orderPriceDiv = "1";
//            l_bondApplyBuyCommonRequest.buyPrice = "1.11";
//            l_bondApplyBuyCommonRequest.validate();
//        }
//        catch (Exception l_ex)
//        {
//            log.error(STR_METHOD_NAME, l_ex);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
 
    public void testTest()
    {
        
    }
}
@
