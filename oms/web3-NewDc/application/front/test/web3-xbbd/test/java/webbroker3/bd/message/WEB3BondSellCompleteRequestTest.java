head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.20.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3BondSellCompleteRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : //TODO(WEB3BondSellCompleteRequestTest)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/05/09 ���g(���u) �V�K�쐬
Revesion History : 2007/07/05  �yWEB3�z�yCITI�t�����g�����i���j�z�Č�����C���{����
*/
package webbroker3.bd.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * @@author ���g(���u)
 * @@version 1.0
 */
public class WEB3BondSellCompleteRequestTest extends TestBaseForMock
{
    /**
     *�@@���O���[�e�B���e�B<BR> 
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3BondSellCompleteRequestTest.class);

    public WEB3BondSellCompleteRequestTest(String name)
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
//     * WEB3BondSellCompleteRequest<br>
//     * �����p�������N�G�X�g<br>
//     *this.����ID��null<br>
//     */
//    public  void test_validate_C0001()
//    {
//        final String STR_METHOD_NAME = "test_validate_C0001()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            WEB3BondSellCompleteRequest l_bondSellCompleteRequest = new WEB3BondSellCompleteRequest();
//            l_bondSellCompleteRequest.id = "1001";
//            l_bondSellCompleteRequest.faceAmount = "123";
//            l_bondSellCompleteRequest.settleDiv = "1";
//            l_bondSellCompleteRequest.orderPriceDiv = "1";
//            l_bondSellCompleteRequest.sellPrice = "1.1";
//            l_bondSellCompleteRequest.orderId = null;
//            l_bondSellCompleteRequest.validate();
//            fail();
//        }
//        catch (WEB3BaseException l_web3SystemException)
//        {
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00600,l_web3SystemException.getErrorInfo());
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
//     * WEB3BondSellCompleteRequest<br>
//     * �����p�������N�G�X�g<br>
//     *this.����ID��1001<br>
//     *this.�m�F����������null<br>
//     */
//    public  void test_validate_C0002()
//    {
//        final String STR_METHOD_NAME = "test_validate_C0002()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            WEB3BondSellCompleteRequest l_bondSellCompleteRequest = new WEB3BondSellCompleteRequest();
//            l_bondSellCompleteRequest.id = "1001";
//            l_bondSellCompleteRequest.faceAmount = "123";
//            l_bondSellCompleteRequest.settleDiv = "1";
//            l_bondSellCompleteRequest.orderPriceDiv = "1";
//            l_bondSellCompleteRequest.sellPrice = "1.1";
//            l_bondSellCompleteRequest.orderId = "1001";
//            l_bondSellCompleteRequest.checkDate = null;
//            l_bondSellCompleteRequest.validate();
//            fail();
//        }
//        catch (WEB3BaseException l_web3SystemException)
//        {
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00078,l_web3SystemException.getErrorInfo());
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
//     * WEB3BondSellCompleteRequest<br>
//     * �����p�������N�G�X�g<br>
//     *this.����ID��1001<br>
//     *this.�m�F����������20070509<br>
//     */
//    public  void test_validate_C0003()
//    {
//        final String STR_METHOD_NAME = "test_validate_C0003()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            WEB3BondSellCompleteRequest l_bondSellCompleteRequest = new WEB3BondSellCompleteRequest();
//            l_bondSellCompleteRequest.id = "1001";
//            l_bondSellCompleteRequest.faceAmount = "123";
//            l_bondSellCompleteRequest.settleDiv = "1";
//            l_bondSellCompleteRequest.orderPriceDiv = "1";
//            l_bondSellCompleteRequest.sellPrice = "1.1";
//            l_bondSellCompleteRequest.orderId = "1001";
//            l_bondSellCompleteRequest.checkDate = WEB3DateUtility.getDate("20070509","yyyyMMdd");
//            l_bondSellCompleteRequest.validate();
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
