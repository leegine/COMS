head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.24.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FuturesCloseMarginCompleteRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e����� 
File Name        : WEB3FuturesCloseMarginCompleteRequestTest.java
Author Name      : Daiwa Institute of Research  
Revesion History : 2008/03/31 �k�v�u (���u) �V�K�쐬  
*/
package webbroker3.ifo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3FuturesCloseMarginCompleteRequestTest extends TestBaseForMock
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3FuturesCloseMarginCompleteRequestTest.class);

    private WEB3FuturesCloseMarginCompleteRequest futuresCloseMarginCompleteRequest = null;

    public WEB3FuturesCloseMarginCompleteRequestTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.futuresCloseMarginCompleteRequest = new WEB3FuturesCloseMarginCompleteRequest();
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
            futuresCloseMarginCompleteRequest.orderPriceDiv = null;
            futuresCloseMarginCompleteRequest.validateAtReverseOrder();
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

    //�Q�j�@@����ID�`�F�b�N
    //�@@�Q�|�P�jthis.����ID��null�̏ꍇ�A
    //�@@�@@�@@�@@�@@�u����ID��null�v�̗�O���X���[����B
    public void testValidateAtReverseOrder_C0002()
    {
        final String STR_METHOD_NAME = "testValidateAtReverseOrder_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate()
            futuresCloseMarginCompleteRequest.orderPriceDiv = "1";
            futuresCloseMarginCompleteRequest.limitPrice = "1000";
            futuresCloseMarginCompleteRequest.execCondType = "1";
            futuresCloseMarginCompleteRequest.expirationDateType = "1";
            futuresCloseMarginCompleteRequest.orderCondType = "0";
            //this.����ID��null
            futuresCloseMarginCompleteRequest.orderId = null;
            futuresCloseMarginCompleteRequest.validateAtReverseOrder();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00600, l_ex.getErrorInfo());
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
    //          �u�ԍό��ʂ����w��ł��B�v�̗�O���X���[����B
    public void testValidateAtReverseOrder_C0003()
    {
        final String STR_METHOD_NAME = "testValidateAtReverseOrder_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate()
            futuresCloseMarginCompleteRequest.orderPriceDiv = "1";
            futuresCloseMarginCompleteRequest.limitPrice = "1000";
            futuresCloseMarginCompleteRequest.execCondType = "1";
            futuresCloseMarginCompleteRequest.expirationDateType = "1";
            futuresCloseMarginCompleteRequest.orderCondType = "0";
            //this.����ID!��null
            futuresCloseMarginCompleteRequest.orderId = "0001";
            //�Q�|�P�jthis.�ԍό���=null
            futuresCloseMarginCompleteRequest.closeMarginContractUnits = null;
            futuresCloseMarginCompleteRequest.validateAtReverseOrder();
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

    // �R�|�Q�jthis.�ԍό��ʂ̗v�f��=0 �̏ꍇ�A
    //          �u�ԍό��ʂ����w��ł��B�v�̗�O���X���[����B
    public void testValidateAtReverseOrder_C0004()
    {
        final String STR_METHOD_NAME = "testValidateAtReverseOrder_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate()
            futuresCloseMarginCompleteRequest.orderPriceDiv = "1";
            futuresCloseMarginCompleteRequest.limitPrice = "1000";
            futuresCloseMarginCompleteRequest.execCondType = "1";
            futuresCloseMarginCompleteRequest.expirationDateType = "1";
            futuresCloseMarginCompleteRequest.orderCondType = "0";
            //this.����ID!��null
            futuresCloseMarginCompleteRequest.orderId = "0001";
            //�Q�|�Q�jthis.�ԍό��ʂ̗v�f��=0
            futuresCloseMarginCompleteRequest.closeMarginContractUnits = new
                WEB3FuturesOptionsCloseMarginContractUnit[]{};
            futuresCloseMarginCompleteRequest.validateAtReverseOrder();
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

    //�S�j�@@���Ϗ����`�F�b�N
    //�@@this.���Ϗ�����null and
    //�@@this.���Ϗ������i�ȉ��̒l�j �̏ꍇ�A�u���Ϗ����̒l�����݂��Ȃ��R�[�h�l�ł��B�v�̗�O���X���[����B
    //�@@�@@�@@�@@�E�h0�F�����_���h
    //�@@�@@�@@�@@�E�h1�F�P���v���h
    //�@@�@@�@@�@@�E�h2�F�P�������h
    //�@@�@@�@@�@@�E�h3�F�������h
    public void testValidateAtReverseOrder_C0005()
    {
        final String STR_METHOD_NAME = "testValidateAtReverseOrder_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate()
            futuresCloseMarginCompleteRequest.orderPriceDiv = "1";
            futuresCloseMarginCompleteRequest.limitPrice = "1000";
            futuresCloseMarginCompleteRequest.execCondType = "1";
            futuresCloseMarginCompleteRequest.expirationDateType = "1";
            futuresCloseMarginCompleteRequest.orderCondType = "0";
            //this.����ID!��null
            futuresCloseMarginCompleteRequest.orderId = "0001";
            //this.�ԍό���!=null and this.�ԍό��ʂ̗v�f��!=0
            futuresCloseMarginCompleteRequest.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[]
                {new WEB3FuturesOptionsCloseMarginContractUnit(),
                new WEB3FuturesOptionsCloseMarginContractUnit()};
            //this.���Ϗ���="4"
            futuresCloseMarginCompleteRequest.closingOrder = "4";
            futuresCloseMarginCompleteRequest.validateAtReverseOrder();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00179, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //�T�j�@@�������ʃ`�F�b�N
    //�@@�T�|�P�jthis.���Ϗ���=�inull or �h1�F�P���v���h or �h2�F�P�������h or �h3�F�������h�j and
    //          this.��������=null �̏ꍇ�A
    //          �u���Ϗ����������_���w��ȊO�̏ꍇ�A���ʂ͕K�{���͍��ڂł��B�v�̗�O���X���[����B
    public void testValidateAtReverseOrder_C0006()
    {
        final String STR_METHOD_NAME = "testValidateAtReverseOrder_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate()
            futuresCloseMarginCompleteRequest.orderPriceDiv = "1";
            futuresCloseMarginCompleteRequest.limitPrice = "1000";
            futuresCloseMarginCompleteRequest.execCondType = "1";
            futuresCloseMarginCompleteRequest.expirationDateType = "1";
            futuresCloseMarginCompleteRequest.orderCondType = "0";
            //this.����ID!��null
            futuresCloseMarginCompleteRequest.orderId = "0001";
            //this.�ԍό���!=null and this.�ԍό��ʂ̗v�f��!=0
            futuresCloseMarginCompleteRequest.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[]
                {new WEB3FuturesOptionsCloseMarginContractUnit(),
                new WEB3FuturesOptionsCloseMarginContractUnit()};
            //this.���Ϗ���="1"
            futuresCloseMarginCompleteRequest.closingOrder = "1";
            futuresCloseMarginCompleteRequest.validateAtReverseOrder();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00245, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //�T�|�Q�jthis.�������ʁ�null and this.�������ʁ����� �̏ꍇ�A
    //          �u�������ʂ������ȊO�̒l�ł��B�v�̗�O���X���[����B
    public void testValidateAtReverseOrder_C0007()
    {
        final String STR_METHOD_NAME = "testValidateAtReverseOrder_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate()
            futuresCloseMarginCompleteRequest.orderPriceDiv = "1";
            futuresCloseMarginCompleteRequest.limitPrice = "1000";
            futuresCloseMarginCompleteRequest.execCondType = "1";
            futuresCloseMarginCompleteRequest.expirationDateType = "1";
            futuresCloseMarginCompleteRequest.orderCondType = "0";
            //this.����ID!��null
            futuresCloseMarginCompleteRequest.orderId = "0001";
            //this.�ԍό���!=null and this.�ԍό��ʂ̗v�f��!=0
            futuresCloseMarginCompleteRequest.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[]
                {new WEB3FuturesOptionsCloseMarginContractUnit(),
                new WEB3FuturesOptionsCloseMarginContractUnit()};
            //this.���Ϗ���="1"
            futuresCloseMarginCompleteRequest.closingOrder = "1";
            futuresCloseMarginCompleteRequest.futOrderQuantity = "aaa";
            futuresCloseMarginCompleteRequest.validateAtReverseOrder();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00075, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //�T�|�R�jthis.�������ʁ�null and this.�������ʁ�0 �̏ꍇ�A
    //          �u�������ʂ�0�ȉ��̒l�ł��B�v�̗�O���X���[����B
    public void testValidateAtReverseOrder_C0008()
    {
        final String STR_METHOD_NAME = "testValidateAtReverseOrder_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate()
            futuresCloseMarginCompleteRequest.orderPriceDiv = "1";
            futuresCloseMarginCompleteRequest.limitPrice = "1000";
            futuresCloseMarginCompleteRequest.execCondType = "1";
            futuresCloseMarginCompleteRequest.expirationDateType = "1";
            futuresCloseMarginCompleteRequest.orderCondType = "0";
            //this.����ID!��null
            futuresCloseMarginCompleteRequest.orderId = "0001";
            //this.�ԍό���!=null and this.�ԍό��ʂ̗v�f��!=0
            futuresCloseMarginCompleteRequest.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[]
                {new WEB3FuturesOptionsCloseMarginContractUnit(),
                new WEB3FuturesOptionsCloseMarginContractUnit()};
            //this.���Ϗ���="1"
            futuresCloseMarginCompleteRequest.closingOrder = "1";
            futuresCloseMarginCompleteRequest.futOrderQuantity = "0";
            futuresCloseMarginCompleteRequest.validateAtReverseOrder();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00076, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //�U�j�@@�ԍό��ʂ̒������ʃ`�F�b�N
    //�@@�U�|�P�j���Ϗ������h0�F�����_���h�̏ꍇ�̂݁A
    //�@@�@@�@@�@@�ԍό��ʂ̗v�f����
    //�@@�@@�@@�@@���L�̃`�F�b�N���J��Ԃ��čs���B
    //           �Enull
    public void testValidateAtReverseOrder_C0009()
    {
        final String STR_METHOD_NAME = "testValidateAtReverseOrder_C0009()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate()
            futuresCloseMarginCompleteRequest.orderPriceDiv = "1";
            futuresCloseMarginCompleteRequest.limitPrice = "1000";
            futuresCloseMarginCompleteRequest.execCondType = "1";
            futuresCloseMarginCompleteRequest.expirationDateType = "1";
            futuresCloseMarginCompleteRequest.orderCondType = "0";
            //this.����ID!��null
            futuresCloseMarginCompleteRequest.orderId = "0001";
            //this.�ԍό���!=null and this.�ԍό��ʂ̗v�f��!=0
            futuresCloseMarginCompleteRequest.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[]
                {new WEB3FuturesOptionsCloseMarginContractUnit(),
                new WEB3FuturesOptionsCloseMarginContractUnit()};
            //this.���Ϗ���="1"
            futuresCloseMarginCompleteRequest.closingOrder = "0";
            futuresCloseMarginCompleteRequest.futOrderQuantity = "1000";
            futuresCloseMarginCompleteRequest.closeMarginContractUnits[0].contractOrderQuantity = null;
            futuresCloseMarginCompleteRequest.validateAtReverseOrder();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03060, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //�V�j�@@�m�F���P���`�F�b�N
    //�@@this.�m�F���P����null�ł������ꍇ�A�u�m�F���P����null�v��
    //�@@��O���X���[����B
    public void testValidateAtReverseOrder_C0010()
    {
        final String STR_METHOD_NAME = "testValidateAtReverseOrder_C0011()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate()
            futuresCloseMarginCompleteRequest.orderPriceDiv = "1";
            futuresCloseMarginCompleteRequest.limitPrice = "1000";
            futuresCloseMarginCompleteRequest.execCondType = "1";
            futuresCloseMarginCompleteRequest.expirationDateType = "1";
            futuresCloseMarginCompleteRequest.orderCondType = "0";
            //this.����ID!��null
            futuresCloseMarginCompleteRequest.orderId = "0001";
            //this.�ԍό���!=null and this.�ԍό��ʂ̗v�f��!=0
            futuresCloseMarginCompleteRequest.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[]
                {new WEB3FuturesOptionsCloseMarginContractUnit(),
                new WEB3FuturesOptionsCloseMarginContractUnit()};
            //this.���Ϗ���="1"
            futuresCloseMarginCompleteRequest.closingOrder = "1";
            futuresCloseMarginCompleteRequest.futOrderQuantity = "1000";
            //this.�m�F���P����null
            futuresCloseMarginCompleteRequest.checkPrice = null;
            futuresCloseMarginCompleteRequest.validateAtReverseOrder();
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

    //�W�j�@@�m�F���������`�F�b�N
    //�@@this.�m�F����������null�ł������ꍇ�A�u�m�F����������null�v��
    //�@@��O���X���[����B
    public void testValidateAtReverseOrder_C0011()
    {
        final String STR_METHOD_NAME = "testValidateAtReverseOrder_C0011()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate()
            futuresCloseMarginCompleteRequest.orderPriceDiv = "1";
            futuresCloseMarginCompleteRequest.limitPrice = "1000";
            futuresCloseMarginCompleteRequest.execCondType = "1";
            futuresCloseMarginCompleteRequest.expirationDateType = "1";
            futuresCloseMarginCompleteRequest.orderCondType = "0";
            //this.����ID!��null
            futuresCloseMarginCompleteRequest.orderId = "0001";
            //this.�ԍό���!=null and this.�ԍό��ʂ̗v�f��!=0
            futuresCloseMarginCompleteRequest.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[]
                {new WEB3FuturesOptionsCloseMarginContractUnit(),
                new WEB3FuturesOptionsCloseMarginContractUnit()};
            //this.���Ϗ���="1"
            futuresCloseMarginCompleteRequest.closingOrder = "1";
            futuresCloseMarginCompleteRequest.futOrderQuantity = "1000";
            //this.�m�F���P��!��null
            futuresCloseMarginCompleteRequest.checkPrice = "1000";
            //this.�m�F����������null
            futuresCloseMarginCompleteRequest.checkDate = null;
            futuresCloseMarginCompleteRequest.validateAtReverseOrder();
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

    //normal case
    public void testValidateAtReverseOrder_C0012()
    {
        final String STR_METHOD_NAME = "testValidateAtReverseOrder_C0012()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate()
            futuresCloseMarginCompleteRequest.orderPriceDiv = "1";
            futuresCloseMarginCompleteRequest.limitPrice = "1000";
            futuresCloseMarginCompleteRequest.execCondType = "1";
            futuresCloseMarginCompleteRequest.expirationDateType = "1";
            futuresCloseMarginCompleteRequest.orderCondType = "0";
            //this.����ID!��null
            futuresCloseMarginCompleteRequest.orderId = "0001";
            //this.�ԍό���!=null and this.�ԍό��ʂ̗v�f��!=0
            futuresCloseMarginCompleteRequest.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[]
                {new WEB3FuturesOptionsCloseMarginContractUnit(),
                new WEB3FuturesOptionsCloseMarginContractUnit()};
            //this.���Ϗ���="1"
            futuresCloseMarginCompleteRequest.closingOrder = "1";
            futuresCloseMarginCompleteRequest.futOrderQuantity = "1000";
            //this.�m�F���P��!��null
            futuresCloseMarginCompleteRequest.checkPrice = "1000";
            //this.�m�F��������!��null
            futuresCloseMarginCompleteRequest.checkDate = WEB3DateUtility.getDate("20010325", "yyyyMMdd");
            futuresCloseMarginCompleteRequest.validateAtReverseOrder();
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
    //�U�j�@@�ԍό��ʂ̒������ʃ`�F�b�N
    //�@@�U�|�P�j���Ϗ������h0�F�����_���h�̏ꍇ�̂݁A
    //�@@�@@�@@�@@�ԍό��ʂ̗v�f����
    //�@@�@@�@@�@@���L�̃`�F�b�N���J��Ԃ��čs���B
    //�@@�@@�@@�@@�@@�@@�E�����ȊO
    public void testValidateAtReverseOrder_C0013()
    {
        final String STR_METHOD_NAME = "testValidateAtReverseOrder_C0013()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate()
            futuresCloseMarginCompleteRequest.orderPriceDiv = "1";
            futuresCloseMarginCompleteRequest.limitPrice = "1000";
            futuresCloseMarginCompleteRequest.execCondType = "1";
            futuresCloseMarginCompleteRequest.expirationDateType = "1";
            futuresCloseMarginCompleteRequest.orderCondType = "0";
            //this.����ID!��null
            futuresCloseMarginCompleteRequest.orderId = "0001";
            //this.�ԍό���!=null and this.�ԍό��ʂ̗v�f��!=0
            futuresCloseMarginCompleteRequest.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[]
                {new WEB3FuturesOptionsCloseMarginContractUnit(),
                new WEB3FuturesOptionsCloseMarginContractUnit()};
            //this.���Ϗ���="1"
            futuresCloseMarginCompleteRequest.closingOrder = "0";
            futuresCloseMarginCompleteRequest.futOrderQuantity = "1000";
            futuresCloseMarginCompleteRequest.closeMarginContractUnits[0].contractOrderQuantity = "aaa";
            futuresCloseMarginCompleteRequest.validateAtReverseOrder();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03060, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    //�U�j�@@�ԍό��ʂ̒������ʃ`�F�b�N
    //�@@�U�|�P�j���Ϗ������h0�F�����_���h�̏ꍇ�̂݁A
    //�@@�@@�@@�@@�ԍό��ʂ̗v�f����
    //�@@�@@�@@�@@���L�̃`�F�b�N���J��Ԃ��čs���B
    //�@@�@@�@@�@@�@@�@@�E�O�ȉ��̐���
    public void testValidateAtReverseOrder_C0014()
    {
        final String STR_METHOD_NAME = "testValidateAtReverseOrder_C0014()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate()
            futuresCloseMarginCompleteRequest.orderPriceDiv = "1";
            futuresCloseMarginCompleteRequest.limitPrice = "1000";
            futuresCloseMarginCompleteRequest.execCondType = "1";
            futuresCloseMarginCompleteRequest.expirationDateType = "1";
            futuresCloseMarginCompleteRequest.orderCondType = "0";
            //this.����ID!��null
            futuresCloseMarginCompleteRequest.orderId = "0001";
            //this.�ԍό���!=null and this.�ԍό��ʂ̗v�f��!=0
            futuresCloseMarginCompleteRequest.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[]
                {new WEB3FuturesOptionsCloseMarginContractUnit(),
                new WEB3FuturesOptionsCloseMarginContractUnit()};
            //this.���Ϗ���="1"
            futuresCloseMarginCompleteRequest.closingOrder = "0";
            futuresCloseMarginCompleteRequest.futOrderQuantity = "1000";
            futuresCloseMarginCompleteRequest.closeMarginContractUnits[0].contractOrderQuantity = "-1000";
            futuresCloseMarginCompleteRequest.validateAtReverseOrder();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03060, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    //�U�j�@@�ԍό��ʂ̒������ʃ`�F�b�N
    //�@@�U�|�P�j���Ϗ������h0�F�����_���h�̏ꍇ�̂݁A
    //�@@�@@�@@�@@�ԍό��ʂ̗v�f����
    //�@@�@@�@@�@@���L�̃`�F�b�N���J��Ԃ��čs���B
    //�@@�@@�@@�@@�@@�@@�E�W���𒴂��鐔��
    public void testValidateAtReverseOrder_C0015()
    {
        final String STR_METHOD_NAME = "testValidateAtReverseOrder_C0015()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate()
            futuresCloseMarginCompleteRequest.orderPriceDiv = "1";
            futuresCloseMarginCompleteRequest.limitPrice = "1000";
            futuresCloseMarginCompleteRequest.execCondType = "1";
            futuresCloseMarginCompleteRequest.expirationDateType = "1";
            futuresCloseMarginCompleteRequest.orderCondType = "0";
            //this.����ID!��null
            futuresCloseMarginCompleteRequest.orderId = "0001";
            //this.�ԍό���!=null and this.�ԍό��ʂ̗v�f��!=0
            futuresCloseMarginCompleteRequest.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[]
                {new WEB3FuturesOptionsCloseMarginContractUnit(),
                new WEB3FuturesOptionsCloseMarginContractUnit()};
            //this.���Ϗ���="1"
            futuresCloseMarginCompleteRequest.closingOrder = "0";
            futuresCloseMarginCompleteRequest.futOrderQuantity = "1000";
            futuresCloseMarginCompleteRequest.closeMarginContractUnits[0].contractOrderQuantity = "111111111";
            futuresCloseMarginCompleteRequest.validateAtReverseOrder();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03060, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    //�U�j�@@�ԍό��ʂ̒������ʃ`�F�b�N
    //�@@�U�|�P�j���Ϗ������h0�F�����_���h�̏ꍇ�̂݁A
    //this.closeMarginContractUnits.length=1
    public void testValidateAtReverseOrder_C0016()
    {
        final String STR_METHOD_NAME = "testValidateAtReverseOrder_C0016()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate()
            futuresCloseMarginCompleteRequest.orderPriceDiv = "1";
            futuresCloseMarginCompleteRequest.limitPrice = "1000";
            futuresCloseMarginCompleteRequest.execCondType = "1";
            futuresCloseMarginCompleteRequest.expirationDateType = "1";
            futuresCloseMarginCompleteRequest.orderCondType = "0";
            //this.����ID!��null
            futuresCloseMarginCompleteRequest.orderId = "0001";
            //this.�ԍό���!=null and this.�ԍό��ʂ̗v�f��!=0
            futuresCloseMarginCompleteRequest.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[]
                {new WEB3FuturesOptionsCloseMarginContractUnit()};
            //this.���Ϗ���="1"
            futuresCloseMarginCompleteRequest.closingOrder = "0";
            futuresCloseMarginCompleteRequest.futOrderQuantity = "1000";
            futuresCloseMarginCompleteRequest.closeMarginContractUnits[0].contractOrderQuantity = "1000";
            //this.�m�F���P��!��null
            futuresCloseMarginCompleteRequest.checkPrice = "1000";
            //this.�m�F��������!��null
            futuresCloseMarginCompleteRequest.checkDate = WEB3DateUtility.getDate("20010325", "yyyyMMdd");
            futuresCloseMarginCompleteRequest.validateAtReverseOrder();
            assertEquals("1000",futuresCloseMarginCompleteRequest.closeMarginContractUnits[0].contractOrderQuantity);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    //�U�j�@@�ԍό��ʂ̒������ʃ`�F�b�N
    //�@@�U�|�P�j���Ϗ������h0�F�����_���h�̏ꍇ�̂݁A
    //this.closeMarginContractUnits.length=3
    public void testValidateAtReverseOrder_C0017()
    {
        final String STR_METHOD_NAME = "testValidateAtReverseOrder_C0017()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate()
            futuresCloseMarginCompleteRequest.orderPriceDiv = "1";
            futuresCloseMarginCompleteRequest.limitPrice = "1000";
            futuresCloseMarginCompleteRequest.execCondType = "1";
            futuresCloseMarginCompleteRequest.expirationDateType = "1";
            futuresCloseMarginCompleteRequest.orderCondType = "0";
            //this.����ID!��null
            futuresCloseMarginCompleteRequest.orderId = "0001";
            //this.�ԍό���!=null and this.�ԍό��ʂ̗v�f��!=0
            futuresCloseMarginCompleteRequest.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[]
                {new WEB3FuturesOptionsCloseMarginContractUnit(),
                new WEB3FuturesOptionsCloseMarginContractUnit(),
                new WEB3FuturesOptionsCloseMarginContractUnit()};
            //this.���Ϗ���="1"
            futuresCloseMarginCompleteRequest.closingOrder = "0";
            futuresCloseMarginCompleteRequest.futOrderQuantity = "1000";
            futuresCloseMarginCompleteRequest.closeMarginContractUnits[0].contractOrderQuantity = "1000";
            futuresCloseMarginCompleteRequest.closeMarginContractUnits[1].contractOrderQuantity = "1000";
            futuresCloseMarginCompleteRequest.closeMarginContractUnits[1].contractOrderQuantity = "-1000";
            futuresCloseMarginCompleteRequest.validateAtReverseOrder();
            assertEquals("1000",futuresCloseMarginCompleteRequest.closeMarginContractUnits[0].contractOrderQuantity);
            assertEquals("1000",futuresCloseMarginCompleteRequest.closeMarginContractUnits[1].contractOrderQuantity);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03060, l_ex.getErrorInfo());
            log.error(STR_METHOD_NAME,l_ex);
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
