head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.25.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FuturesCloseMarginChangeCompleteRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e����� 
File Name        : WEB3FuturesCloseMarginChangeCompleteRequestTest.java
Author Name      : Daiwa Institute of Research  
Revesion History : 2008/03/31 �k�v�u (���u) �V�K�쐬  
*/
package webbroker3.ifo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3FuturesCloseMarginChangeCompleteRequestTest extends TestBaseForMock
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3FuturesCloseMarginChangeCompleteRequestTest.class);

    private WEB3FuturesCloseMarginChangeCompleteRequest futuresCloseMarginChangeCompleteRequest = null;

    public WEB3FuturesCloseMarginChangeCompleteRequestTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.futuresCloseMarginChangeCompleteRequest = new WEB3FuturesCloseMarginChangeCompleteRequest();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    //�P�j�@@�X�[�p�[�N���X��validate �`�F�b�N
    public void testValidateAtReverseOrder_C0001()
    {
        final String STR_METHOD_NAME = "testValidateAtReverseOrder_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            futuresCloseMarginChangeCompleteRequest.orderPriceDiv = null;
            futuresCloseMarginChangeCompleteRequest.validateAtReverseOrder();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00184, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //�Q�j�@@�h�c�`�F�b�N
    //this.�h�c=null �̏ꍇ�A��O���X���[����B
    public void testValidateAtReverseOrder_C0002()
    {
        final String STR_METHOD_NAME = "testValidateAtReverseOrder_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate()
            futuresCloseMarginChangeCompleteRequest.orderPriceDiv = "1";
            futuresCloseMarginChangeCompleteRequest.limitPrice = "1000";
            futuresCloseMarginChangeCompleteRequest.execCondType = "1";
            futuresCloseMarginChangeCompleteRequest.expirationDateType = "1";
            futuresCloseMarginChangeCompleteRequest.orderCondType = "0";
            //this.ID��null
            futuresCloseMarginChangeCompleteRequest.id = null;
            futuresCloseMarginChangeCompleteRequest.validateAtReverseOrder();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00080, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //�R�j�@@�ԍό��ʃ`�F�b�N
    //  �R�|�P�jthis.�ԍό���=null �̏ꍇ�A
    //          ��O���X���[����B
    public void testValidateAtReverseOrder_C0003()
    {
        final String STR_METHOD_NAME = "testValidateAtReverseOrder_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate()
            futuresCloseMarginChangeCompleteRequest.orderPriceDiv = "1";
            futuresCloseMarginChangeCompleteRequest.limitPrice = "1000";
            futuresCloseMarginChangeCompleteRequest.execCondType = "1";
            futuresCloseMarginChangeCompleteRequest.expirationDateType = "1";
            futuresCloseMarginChangeCompleteRequest.orderCondType = "0";
            //this.ID!��null
            futuresCloseMarginChangeCompleteRequest.id = "0001";
            //this.�ԍό���=null
            futuresCloseMarginChangeCompleteRequest.closeMarginContractUnits = null;
            futuresCloseMarginChangeCompleteRequest.validateAtReverseOrder();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00178, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //�S�j�@@�m�F���P���`�F�b�N
    //�@@this.�m�F���P����null�̏ꍇ�A
    //�@@�u�m�F���P����null�v�̗�O���X���[����B
    public void testValidateAtReverseOrder_C0004()
    {
        final String STR_METHOD_NAME = "testValidateAtReverseOrder_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate()
            futuresCloseMarginChangeCompleteRequest.orderPriceDiv = "1";
            futuresCloseMarginChangeCompleteRequest.limitPrice = "1000";
            futuresCloseMarginChangeCompleteRequest.execCondType = "1";
            futuresCloseMarginChangeCompleteRequest.expirationDateType = "1";
            futuresCloseMarginChangeCompleteRequest.orderCondType = "0";
            //this.ID!��null
            futuresCloseMarginChangeCompleteRequest.id = "0001";
            //this.�ԍό���!=null
            futuresCloseMarginChangeCompleteRequest.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit(),
                    new WEB3FuturesOptionsCloseMarginContractUnit()};
            //this.�m�F���P����null
            futuresCloseMarginChangeCompleteRequest.checkPrice = null;
            futuresCloseMarginChangeCompleteRequest.validateAtReverseOrder();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00206, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //�T�j�@@�m�F���������`�F�b�N
    //�@@this.�m�F����������null�̏ꍇ�A
    //�@@�u�m�F����������null�v�̗�O���X���[����B
    public void testValidateAtReverseOrder_C0005()
    {
        final String STR_METHOD_NAME = "testValidateAtReverseOrder_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate()
            futuresCloseMarginChangeCompleteRequest.orderPriceDiv = "1";
            futuresCloseMarginChangeCompleteRequest.limitPrice = "1000";
            futuresCloseMarginChangeCompleteRequest.execCondType = "1";
            futuresCloseMarginChangeCompleteRequest.expirationDateType = "1";
            futuresCloseMarginChangeCompleteRequest.orderCondType = "0";
            //this.ID!��null
            futuresCloseMarginChangeCompleteRequest.id = "0001";
            //this.�ԍό���!=null this.�ԍό��ʂ̗v�f��!=0 
            futuresCloseMarginChangeCompleteRequest.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit(),
                new WEB3FuturesOptionsCloseMarginContractUnit()};
            //this.�m�F���P��!��null
            futuresCloseMarginChangeCompleteRequest.checkPrice = "1000";
            //this.�m�F����������null
            futuresCloseMarginChangeCompleteRequest.checkDate = null;
            futuresCloseMarginChangeCompleteRequest.validateAtReverseOrder();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00078, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //�U�j�@@�A�������E���������`�F�b�N
    //�@@�X�[�p�[�N���X��validate�A���������\�b�h���R�[������B
    public void testValidateAtReverseOrder_C0006()
    {
        final String STR_METHOD_NAME = "testValidateAtReverseOrder_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate()
            futuresCloseMarginChangeCompleteRequest.orderPriceDiv = "1";
            futuresCloseMarginChangeCompleteRequest.limitPrice = "1000";
            futuresCloseMarginChangeCompleteRequest.execCondType = "3";
            futuresCloseMarginChangeCompleteRequest.expirationDateType = "1";
            futuresCloseMarginChangeCompleteRequest.orderCondType = "0";
            //this.ID!��null
            futuresCloseMarginChangeCompleteRequest.id = "0001";
            //this.�ԍό���!=null this.�ԍό��ʂ̗v�f��!=0 
            futuresCloseMarginChangeCompleteRequest.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit(),
                new WEB3FuturesOptionsCloseMarginContractUnit()};
            //this.�m�F���P��!��null
            futuresCloseMarginChangeCompleteRequest.checkPrice = "1000";
            //this.�m�F��������!��null
            futuresCloseMarginChangeCompleteRequest.checkDate = WEB3DateUtility.getDate("20010325", "yyyyMMdd");
            futuresCloseMarginChangeCompleteRequest.validateAtReverseOrder();
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

    //normal case
    public void testValidateAtReverseOrder_C0007()
    {
        final String STR_METHOD_NAME = "testValidateAtReverseOrder_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate()
            futuresCloseMarginChangeCompleteRequest.orderPriceDiv = "1";
            futuresCloseMarginChangeCompleteRequest.limitPrice = "1000";
            futuresCloseMarginChangeCompleteRequest.execCondType = "1";
            futuresCloseMarginChangeCompleteRequest.expirationDateType = "1";
            futuresCloseMarginChangeCompleteRequest.orderCondType = "0";
            //this.ID!��null
            futuresCloseMarginChangeCompleteRequest.id = "0001";
            //this.�ԍό���!=null
            futuresCloseMarginChangeCompleteRequest.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit(),
                new WEB3FuturesOptionsCloseMarginContractUnit()};
            //this.�m�F���P��!��null
            futuresCloseMarginChangeCompleteRequest.checkPrice = "1000";
            //this.�m�F��������!��null
            futuresCloseMarginChangeCompleteRequest.checkDate = WEB3DateUtility.getDate("20010325", "yyyyMMdd");
            futuresCloseMarginChangeCompleteRequest.validateAtReverseOrder();
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
