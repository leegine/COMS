head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.20.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3BondSellCommonRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : //TODO(WEB3BondSellCommonRequestTest)
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
public class WEB3BondSellCommonRequestTest extends TestBaseForMock
{

    /**
     *�@@���O���[�e�B���e�B<BR> 
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3BondSellCommonRequestTest.class);

    public WEB3BondSellCommonRequestTest(String name)
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
//     * WEB3BondSellCommonRequest<br>
//     * �����p���ʃ��N�G�X�g<br>
//     *this.�h�c == null<br>
//     */
//    public  void test_validate_C0001()
//    {
//        final String STR_METHOD_NAME = "test_validate_C0001()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            WEB3BondSellCommonRequest l_bondSellCommonRequest = new WEB3BondSellCommonRequest();
//            l_bondSellCommonRequest.id = null;
//            l_bondSellCommonRequest.validate();
//            fail();
//        }
//        catch (WEB3BaseException l_web3SystemException)
//        {
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01919,l_web3SystemException.getErrorInfo());
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
//     * WEB3BondSellCommonRequest<br>
//     * �����p���ʃ��N�G�X�g<br>
//     *this.�h�c == 1001<br>
//     *this.�z�ʋ��z�������łȂ��ꍇ<br>
//     *this.�z�ʋ��z������ == "a"<br>
//     */
//    public  void test_validate_C0002()
//    {
//        final String STR_METHOD_NAME = "test_validate_C0002()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            WEB3BondSellCommonRequest l_bondSellCommonRequest = new WEB3BondSellCommonRequest();
//            l_bondSellCommonRequest.id = "1001";
//            l_bondSellCommonRequest.faceAmount = "a";
//            l_bondSellCommonRequest.validate();
//            fail();
//        }
//        catch (WEB3BaseException l_web3SystemException)
//        {
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02641,l_web3SystemException.getErrorInfo());
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
//     * WEB3BondSellCommonRequest<br>
//     * �����p���ʃ��N�G�X�g<br>
//     *this.�h�c == 1001<br>
//     *this.�z�ʋ��z�������łȂ��ꍇ<br>
//     *this.�z�ʋ��z������ == "-1"<br>
//     */
//    public  void test_validate_C0003()
//    {
//        final String STR_METHOD_NAME = "test_validate_C0003()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            WEB3BondSellCommonRequest l_bondSellCommonRequest = new WEB3BondSellCommonRequest();
//            l_bondSellCommonRequest.id = "1001";
//            l_bondSellCommonRequest.faceAmount = "-1";
//            l_bondSellCommonRequest.validate();
//            fail();
//        }
//        catch (WEB3BaseException l_web3SystemException)
//        {
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02636,l_web3SystemException.getErrorInfo());
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
//     * WEB3BondSellCommonRequest<br>
//     * �����p���ʃ��N�G�X�g<br>
//     *this.�h�c == 1001<br>
//     *this.�z�ʋ��z�������łȂ��ꍇ<br>
//     *this.�z�ʋ��z������ == "1111111"<br>
//     *this.���ϋ敪���ȉ���`�l�ȊO<br>
//     *this.settleDiv == "0"<br>
//     */
//    public  void test_validate_C0004()
//    {
//        final String STR_METHOD_NAME = "test_validate_C0004()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            WEB3BondSellCommonRequest l_bondSellCommonRequest = new WEB3BondSellCommonRequest();
//            l_bondSellCommonRequest.id = "1001";
//            l_bondSellCommonRequest.faceAmount = "1111111";
//            l_bondSellCommonRequest.settleDiv = "0";
//            l_bondSellCommonRequest.validate();
//            fail();
//        }
//        catch (WEB3BaseException l_web3SystemException)
//        {
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02112,l_web3SystemException.getErrorInfo());
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
//     * WEB3BondSellCommonRequest<br>
//     * �����p���ʃ��N�G�X�g<br>
//     *this.�h�c == 1001<br>
//     *this.�z�ʋ��z�������łȂ��ꍇ<br>
//     *this.�z�ʋ��z������ == "1111111"<br>
//     *this.���ϋ敪���ȉ���`�l�ȊO<br>
//     *this.settleDiv == "1"<br>
//     *�w�l�敪 �� null�̏ꍇ
//     *�w�l�敪���ȉ��̒l�łȂ��ꍇ
//     */
//    public  void test_validate_C0005()
//    {
//        final String STR_METHOD_NAME = "test_validate_C0005()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            WEB3BondSellCommonRequest l_bondSellCommonRequest = new WEB3BondSellCommonRequest();
//            l_bondSellCommonRequest.id = "1001";
//            l_bondSellCommonRequest.faceAmount = "1111111";
//            l_bondSellCommonRequest.settleDiv = "1";
//            l_bondSellCommonRequest.orderPriceDiv = "0";
//            l_bondSellCommonRequest.validate();
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
//     * WEB3BondSellCommonRequest<br>
//     * �����p���ʃ��N�G�X�g<br>
//     *this.�h�c == 1001<br>
//     *this.�z�ʋ��z�������łȂ��ꍇ<br>
//     *this.�z�ʋ��z������ == "1111111"<br>
//     *this.���ϋ敪���ȉ���`�l�ȊO<br>
//     *this.settleDiv == "1"<br>
//     *�w�l�敪 �� null�̏ꍇ<br>
//     *�w�l�敪���ȉ��̒l == �w�l<br>
//     *���p�P�� == null<br>
//     */
//    public  void test_validate_C0006()
//    {
//        final String STR_METHOD_NAME = "test_validate_C0006()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            WEB3BondSellCommonRequest l_bondSellCommonRequest = new WEB3BondSellCommonRequest();
//            l_bondSellCommonRequest.id = "1001";
//            l_bondSellCommonRequest.faceAmount = "1111111";
//            l_bondSellCommonRequest.settleDiv = "1";
//            l_bondSellCommonRequest.orderPriceDiv = "1";
//            l_bondSellCommonRequest.sellPrice = null;
//            l_bondSellCommonRequest.validate();
//            fail();
//        }
//        catch (WEB3BaseException l_web3SystemException)
//        {
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02770,l_web3SystemException.getErrorInfo());
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
//     * WEB3BondSellCommonRequest<br>
//     * �����p���ʃ��N�G�X�g<br>
//     *this.�h�c == 1001<br>
//     *this.�z�ʋ��z�������łȂ��ꍇ<br>
//     *this.�z�ʋ��z������ == "1111111"<br>
//     *this.���ϋ敪���ȉ���`�l�ȊO<br>
//     *this.settleDiv == "1"<br>
//     *�w�l�敪 �� null�̏ꍇ<br>
//     *�w�l�敪���ȉ��̒l == �w�l<br>
//     *���p�P�� == 1.11<br>
//     */
//    public  void test_validate_C0007()
//    {
//        final String STR_METHOD_NAME = "test_validate_C0007()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            WEB3BondSellCommonRequest l_bondSellCommonRequest = new WEB3BondSellCommonRequest();
//            l_bondSellCommonRequest.id = "1001";
//            l_bondSellCommonRequest.faceAmount = "1111111";
//            l_bondSellCommonRequest.settleDiv = "1";
//            l_bondSellCommonRequest.orderPriceDiv = "1";
//            l_bondSellCommonRequest.sellPrice = "11111.11";
//            l_bondSellCommonRequest.validate();
//            fail();
//        }
//        catch (WEB3BaseException l_web3SystemException)
//        {
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02771,l_web3SystemException.getErrorInfo());
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
//     * WEB3BondSellCommonRequest<br>
//     * �����p���ʃ��N�G�X�g<br>
//     *this.�h�c == 1001<br>
//     *this.�z�ʋ��z�������łȂ��ꍇ<br>
//     *this.�z�ʋ��z������ == "1111111"<br>
//     *this.���ϋ敪���ȉ���`�l�ȊO<br>
//     *this.settleDiv == "1"<br>
//     *�w�l�敪 �� null�̏ꍇ<br>
//     *�w�l�敪���ȉ��̒l == �w�l<br>
//     *���p�P�� == 11.11a<br>
//     */
//    public  void test_validate_C0008()
//    {
//        final String STR_METHOD_NAME = "test_validate_C0008()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            WEB3BondSellCommonRequest l_bondSellCommonRequest = new WEB3BondSellCommonRequest();
//            l_bondSellCommonRequest.id = "1001";
//            l_bondSellCommonRequest.faceAmount = "1111111";
//            l_bondSellCommonRequest.settleDiv = "1";
//            l_bondSellCommonRequest.orderPriceDiv = "1";
//            l_bondSellCommonRequest.sellPrice = "11.11a";
//            l_bondSellCommonRequest.validate();
//            fail();
//        }
//        catch (WEB3BaseException l_web3SystemException)
//        {
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02772,l_web3SystemException.getErrorInfo());
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
//     * WEB3BondSellCommonRequest<br>
//     * �����p���ʃ��N�G�X�g<br>
//     *this.�h�c == 1001<br>
//     *this.�z�ʋ��z�������łȂ��ꍇ<br>
//     *this.�z�ʋ��z������ == "1111111"<br>
//     *this.���ϋ敪���ȉ���`�l�ȊO<br>
//     *this.settleDiv == "1"<br>
//     *�w�l�敪 �� null�̏ꍇ<br>
//     *�w�l�敪���ȉ��̒l == �w�l<br>
//     *���p�P�� == -11111.11<br>
//     */
//    public  void test_validate_C0009()
//    {
//        final String STR_METHOD_NAME = "test_validate_C0009()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            WEB3BondSellCommonRequest l_bondSellCommonRequest = new WEB3BondSellCommonRequest();
//            l_bondSellCommonRequest.id = "1001";
//            l_bondSellCommonRequest.faceAmount = "1111111";
//            l_bondSellCommonRequest.settleDiv = "1";
//            l_bondSellCommonRequest.orderPriceDiv = "1";
//            l_bondSellCommonRequest.sellPrice = "-11.11";
//            l_bondSellCommonRequest.validate();
//            fail();
//        }
//        catch (WEB3BaseException l_web3SystemException)
//        {
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02773,l_web3SystemException.getErrorInfo());
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
//     * WEB3BondSellCommonRequest<br>
//     * �����p���ʃ��N�G�X�g<br>
//     *this.�h�c == 1001<br>
//     *this.�z�ʋ��z�������łȂ��ꍇ<br>
//     *this.�z�ʋ��z������ == "1111111"<br>
//     *this.���ϋ敪���ȉ���`�l�ȊO<br>
//     *this.settleDiv == "1"<br>
//     *�w�l�敪 �� null�̏ꍇ<br>
//     *�w�l�敪���ȉ��̒l == �w�l<br>
//     *���p�P�� == 11111.11<br>
//     *�����t���O == 1�̏ꍇ<br>
//     *�בփ��X�N�t���O == 1�̏ꍇ<br>
//     *���l =< 500�̏ꍇ<br>
//     */
//    public  void test_validate_C0010()
//    {
//        final String STR_METHOD_NAME = "test_validate_C0010()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            WEB3BondSellCommonRequest l_bondSellCommonRequest = new WEB3BondSellCommonRequest();
//            l_bondSellCommonRequest.id = "1001";
//            l_bondSellCommonRequest.faceAmount = "1111111";
//            l_bondSellCommonRequest.settleDiv = "1";
//            l_bondSellCommonRequest.orderPriceDiv = "1";
//            l_bondSellCommonRequest.sellPrice = "11.11";
//            l_bondSellCommonRequest.validate();
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
