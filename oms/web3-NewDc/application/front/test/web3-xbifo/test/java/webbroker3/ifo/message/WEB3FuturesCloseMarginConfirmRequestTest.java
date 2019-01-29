head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.23.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FuturesCloseMarginConfirmRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : WEB3FuturesCloseMarginConfirmRequestTest.java
 Author Name      : Daiwa Institute of Research
 Revesion History : 2008/03/28 �k�v�u (���u) �V�K�쐬
 */
package webbroker3.ifo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3FuturesCloseMarginConfirmRequestTest extends TestBaseForMock
{

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3FuturesCloseMarginConfirmRequestTest.class);

    private WEB3FuturesCloseMarginConfirmRequest futuresCloseMarginConfirmRequest = null;

    public WEB3FuturesCloseMarginConfirmRequestTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.futuresCloseMarginConfirmRequest = new WEB3FuturesCloseMarginConfirmRequest();
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
            futuresCloseMarginConfirmRequest.orderPriceDiv = null;
            futuresCloseMarginConfirmRequest.validateAtReverseOrder();
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
    //�Q�j�@@�ԍό��ʃ`�F�b�N
    //�Q�|�P�jthis.�ԍό���=null �̏ꍇ�A
    //�u�ԍό��ʂ����w��ł��B�v�̗�O���X���[����B
    public void testValidateAtReverseOrder_C0002()
    {
        final String STR_METHOD_NAME = "testValidateAtReverseOrder_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate()
            futuresCloseMarginConfirmRequest.orderPriceDiv = "1";
            futuresCloseMarginConfirmRequest.limitPrice = "1000";
            futuresCloseMarginConfirmRequest.execCondType = "1";
            futuresCloseMarginConfirmRequest.expirationDateType = "1";
            futuresCloseMarginConfirmRequest.orderCondType = "0";
            //�Q�|�P�jthis.�ԍό���=null
            futuresCloseMarginConfirmRequest.closeMarginContractUnits = null;
            futuresCloseMarginConfirmRequest.validateAtReverseOrder();
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

    //�Q�|�Q�jthis.�ԍό��ʂ̗v�f��=0 �̏ꍇ�A
    // �u�ԍό��ʂ����w��ł��B�v�̗�O���X���[����B
    public void testValidateAtReverseOrder_C0003()
    {
        final String STR_METHOD_NAME = "testValidateAtReverseOrder_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate()
            futuresCloseMarginConfirmRequest.orderPriceDiv = "1";
            futuresCloseMarginConfirmRequest.limitPrice = "1000";
            futuresCloseMarginConfirmRequest.execCondType = "1";
            futuresCloseMarginConfirmRequest.expirationDateType = "1";
            futuresCloseMarginConfirmRequest.orderCondType = "0";
            //�Q�|�Q�jthis.�ԍό��ʂ̗v�f��=0
            futuresCloseMarginConfirmRequest.closeMarginContractUnits = new
                WEB3FuturesOptionsCloseMarginContractUnit[]{};
            futuresCloseMarginConfirmRequest.validateAtReverseOrder();
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

    //�R�j�@@���Ϗ����`�F�b�N
    //this.���Ϗ�����null and this.���Ϗ������i�ȉ��̒l�j �̏ꍇ�A
    //�u���Ϗ����̒l�����݂��Ȃ��R�[�h�l�ł��B�v�̗�O���X���[����B
    //�@@�@@�@@�@@�E�h0�F�����_���h
    //�@@�@@�@@�@@�E�h1�F�P���v���h
    //�@@�@@�@@�@@�E�h2�F�P�������h
    //�@@�@@�@@�@@�E�h3�F�������h
    public void testValidateAtReverseOrder_C0004()
    {
        final String STR_METHOD_NAME = "testValidateAtReverseOrder_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate()
            futuresCloseMarginConfirmRequest.orderPriceDiv = "1";
            futuresCloseMarginConfirmRequest.limitPrice = "1000";
            futuresCloseMarginConfirmRequest.execCondType = "1";
            futuresCloseMarginConfirmRequest.expirationDateType = "1";
            futuresCloseMarginConfirmRequest.orderCondType = "0";
            //this.�ԍό���!=null and this.�ԍό��ʂ̗v�f��!=0
            futuresCloseMarginConfirmRequest.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[]
                {new WEB3FuturesOptionsCloseMarginContractUnit(),
                new WEB3FuturesOptionsCloseMarginContractUnit()};
            //this.���Ϗ���="4"
            futuresCloseMarginConfirmRequest.closingOrder = "4";
            futuresCloseMarginConfirmRequest.validateAtReverseOrder();
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

    //�S�j�@@�������ʃ`�F�b�N
    //�@@�S�|�P�jthis.���Ϗ���=�inull or �h1�F�P���v���h or �h2�F�P�������h or �h3�F�������h�j and
    //          this.��������=null �̏ꍇ�A
    //          �u���Ϗ����������_���w��ȊO�̏ꍇ�A���ʂ͕K�{���͍��ڂł��B�v�̗�O���X���[����B
    public void testValidateAtReverseOrder_C0005()
    {
        final String STR_METHOD_NAME = "testValidateAtReverseOrder_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate()
            futuresCloseMarginConfirmRequest.orderPriceDiv = "1";
            futuresCloseMarginConfirmRequest.limitPrice = "1000";
            futuresCloseMarginConfirmRequest.execCondType = "1";
            futuresCloseMarginConfirmRequest.expirationDateType = "1";
            futuresCloseMarginConfirmRequest.orderCondType = "0";
            //this.�ԍό���!=null and this.�ԍό��ʂ̗v�f��!=0
            futuresCloseMarginConfirmRequest.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[]
                {new WEB3FuturesOptionsCloseMarginContractUnit(),
                new WEB3FuturesOptionsCloseMarginContractUnit()};
            //this.���Ϗ���="1"
            futuresCloseMarginConfirmRequest.closingOrder = "1";
            futuresCloseMarginConfirmRequest.validateAtReverseOrder();
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

    //�S�|�Q�jthis.�������ʁ�null and this.�������ʁ����� �̏ꍇ�A
    //          �u�������ʂ������ȊO�̒l�ł��B�v�̗�O���X���[����B
    public void testValidateAtReverseOrder_C0006()
    {
        final String STR_METHOD_NAME = "testValidateAtReverseOrder_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate()
            futuresCloseMarginConfirmRequest.orderPriceDiv = "1";
            futuresCloseMarginConfirmRequest.limitPrice = "1000";
            futuresCloseMarginConfirmRequest.execCondType = "1";
            futuresCloseMarginConfirmRequest.expirationDateType = "1";
            futuresCloseMarginConfirmRequest.orderCondType = "0";
            //this.�ԍό���!=null and this.�ԍό��ʂ̗v�f��!=0
            futuresCloseMarginConfirmRequest.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[]
                {new WEB3FuturesOptionsCloseMarginContractUnit(),
                new WEB3FuturesOptionsCloseMarginContractUnit()};
            //this.���Ϗ���="1"
            futuresCloseMarginConfirmRequest.closingOrder = "1";
            futuresCloseMarginConfirmRequest.futOrderQuantity = "aaa";
            futuresCloseMarginConfirmRequest.validateAtReverseOrder();
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

    //�S�|�R�jthis.�������ʁ�null and this.�������ʁ�0 �̏ꍇ�A
    //          �u�������ʂ�0�ȉ��̒l�ł��B�v�̗�O���X���[����B
    public void testValidateAtReverseOrder_C0007()
    {
        final String STR_METHOD_NAME = "testValidateAtReverseOrder_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate()
            futuresCloseMarginConfirmRequest.orderPriceDiv = "1";
            futuresCloseMarginConfirmRequest.limitPrice = "1000";
            futuresCloseMarginConfirmRequest.execCondType = "1";
            futuresCloseMarginConfirmRequest.expirationDateType = "1";
            futuresCloseMarginConfirmRequest.orderCondType = "0";
            //this.�ԍό���!=null and this.�ԍό��ʂ̗v�f��!=0
            futuresCloseMarginConfirmRequest.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[]
                {new WEB3FuturesOptionsCloseMarginContractUnit(),
                new WEB3FuturesOptionsCloseMarginContractUnit()};
            //this.���Ϗ���="1"
            futuresCloseMarginConfirmRequest.closingOrder = "1";
            futuresCloseMarginConfirmRequest.futOrderQuantity = "0";
            futuresCloseMarginConfirmRequest.validateAtReverseOrder();
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

    //�T�j�@@�ԍό��ʂ̒������ʃ`�F�b�N
    //�@@�T�|�P�j���Ϗ������h0�F�����_���h�̏ꍇ�̂݁A
    //�@@�@@�@@�@@�ԍό��ʂ̗v�f����
    //�@@�@@�@@�@@���L�̃`�F�b�N���J��Ԃ��čs���B
    //           �Enull
    //�@@�@@�@@�@@�@@�@@�E�����ȊO
    //�@@�@@�@@�@@�@@�@@�E�O�ȉ��̐���
    //�@@�@@�@@�@@�@@�@@�E�W���𒴂��鐔��
    public void testValidateAtReverseOrder_C0008()
    {
        final String STR_METHOD_NAME = "testValidateAtReverseOrder_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate()
            futuresCloseMarginConfirmRequest.orderPriceDiv = "1";
            futuresCloseMarginConfirmRequest.limitPrice = "1000";
            futuresCloseMarginConfirmRequest.execCondType = "1";
            futuresCloseMarginConfirmRequest.expirationDateType = "1";
            futuresCloseMarginConfirmRequest.orderCondType = "0";
            //this.�ԍό���!=null and this.�ԍό��ʂ̗v�f��!=0
            futuresCloseMarginConfirmRequest.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[]
                {new WEB3FuturesOptionsCloseMarginContractUnit(),
                new WEB3FuturesOptionsCloseMarginContractUnit()};
            //this.���Ϗ���="1"
            futuresCloseMarginConfirmRequest.closingOrder = "0";
            futuresCloseMarginConfirmRequest.futOrderQuantity = "1000";
            futuresCloseMarginConfirmRequest.closeMarginContractUnits[0].contractOrderQuantity = null;
            futuresCloseMarginConfirmRequest.validateAtReverseOrder();
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

    //normal case
    public void testValidateAtReverseOrder_C0009()
    {
        final String STR_METHOD_NAME = "testValidateAtReverseOrder_C0009()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate()
            futuresCloseMarginConfirmRequest.orderPriceDiv = "1";
            futuresCloseMarginConfirmRequest.limitPrice = "1000";
            futuresCloseMarginConfirmRequest.execCondType = "1";
            futuresCloseMarginConfirmRequest.expirationDateType = "1";
            futuresCloseMarginConfirmRequest.orderCondType = "0";
            //this.�ԍό���!=null and this.�ԍό��ʂ̗v�f��!=0
            futuresCloseMarginConfirmRequest.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[]
                {new WEB3FuturesOptionsCloseMarginContractUnit(),
                new WEB3FuturesOptionsCloseMarginContractUnit()};
            //this.���Ϗ���="1"
            futuresCloseMarginConfirmRequest.closingOrder = "1";
            futuresCloseMarginConfirmRequest.futOrderQuantity = "1000";
            futuresCloseMarginConfirmRequest.validateAtReverseOrder();
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
