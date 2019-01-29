head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.24.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FuturesCloseMarginChangeConfirmRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e����� 
File Name        : WEB3FuturesCloseMarginChangeConfirmRequestTest.java
Author Name      : Daiwa Institute of Research  
Revesion History : 2008/03/31 �k�v�u (���u) �V�K�쐬  
*/
package webbroker3.ifo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3FuturesCloseMarginChangeConfirmRequestTest extends TestBaseForMock
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3FuturesCloseMarginChangeConfirmRequestTest.class);

    private WEB3FuturesCloseMarginChangeConfirmRequest futuresCloseMarginChangeConfirmRequest = null;

    public WEB3FuturesCloseMarginChangeConfirmRequestTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.futuresCloseMarginChangeConfirmRequest = new WEB3FuturesCloseMarginChangeConfirmRequest();
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
            futuresCloseMarginChangeConfirmRequest.orderPriceDiv = null;
            futuresCloseMarginChangeConfirmRequest.validateAtReverseOrder();
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
            futuresCloseMarginChangeConfirmRequest.orderPriceDiv = "1";
            futuresCloseMarginChangeConfirmRequest.limitPrice = "1000";
            futuresCloseMarginChangeConfirmRequest.execCondType = "1";
            futuresCloseMarginChangeConfirmRequest.expirationDateType = "1";
            futuresCloseMarginChangeConfirmRequest.orderCondType = "0";
            //this.ID��null
            futuresCloseMarginChangeConfirmRequest.id = null;
            futuresCloseMarginChangeConfirmRequest.validateAtReverseOrder();
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
    //�R�|�P�jthis.�ԍό���=null �̏ꍇ�A��O���X���[����B
    public void testValidateAtReverseOrder_C0003()
    {
        final String STR_METHOD_NAME = "testValidateAtReverseOrder_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate()
            futuresCloseMarginChangeConfirmRequest.orderPriceDiv = "1";
            futuresCloseMarginChangeConfirmRequest.limitPrice = "1000";
            futuresCloseMarginChangeConfirmRequest.execCondType = "1";
            futuresCloseMarginChangeConfirmRequest.expirationDateType = "1";
            futuresCloseMarginChangeConfirmRequest.orderCondType = "0";
            //this.ID!��null
            futuresCloseMarginChangeConfirmRequest.id = "0001";
            //this.�ԍό���=null
            futuresCloseMarginChangeConfirmRequest.closeMarginContractUnits = null;
            futuresCloseMarginChangeConfirmRequest.validateAtReverseOrder();
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

    //�R�|�Q�jthis.�ԍό��ʂ̗v�f��=0 �̏ꍇ�A��O���X���[����
    public void testValidateAtReverseOrder_C0004()
    {
        final String STR_METHOD_NAME = "testValidateAtReverseOrder_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate()
            futuresCloseMarginChangeConfirmRequest.orderPriceDiv = "1";
            futuresCloseMarginChangeConfirmRequest.limitPrice = "1000";
            futuresCloseMarginChangeConfirmRequest.execCondType = "1";
            futuresCloseMarginChangeConfirmRequest.expirationDateType = "1";
            futuresCloseMarginChangeConfirmRequest.orderCondType = "0";
            //this.ID!��null
            futuresCloseMarginChangeConfirmRequest.id = "0001";
            //this.�ԍό���!=null && this.�ԍό��ʂ̗v�f��=0
            futuresCloseMarginChangeConfirmRequest.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[]{};
            futuresCloseMarginChangeConfirmRequest.validateAtReverseOrder();
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

    //�S�j�@@�A�������E���������`�F�b�N
    //�X�[�p�[�N���X��validate�A���������\�b�h���R�[������
    public void testValidateAtReverseOrder_C0005()
    {
        final String STR_METHOD_NAME = "testValidateAtReverseOrder_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate()
            futuresCloseMarginChangeConfirmRequest.orderPriceDiv = "1";
            futuresCloseMarginChangeConfirmRequest.limitPrice = "1000";
            futuresCloseMarginChangeConfirmRequest.execCondType = "3";
            futuresCloseMarginChangeConfirmRequest.expirationDateType = "1";
            futuresCloseMarginChangeConfirmRequest.orderCondType = "0";
            //this.ID!��null
            futuresCloseMarginChangeConfirmRequest.id = "0001";
            //this.�ԍό���!=null && this.�ԍό��ʂ̗v�f��!=0 
            futuresCloseMarginChangeConfirmRequest.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit(),
                new WEB3FuturesOptionsCloseMarginContractUnit()};
            futuresCloseMarginChangeConfirmRequest.validateAtReverseOrder();
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
    public void testValidateAtReverseOrder_C0006()
    {
        final String STR_METHOD_NAME = "testValidateAtReverseOrder_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate()
            futuresCloseMarginChangeConfirmRequest.orderPriceDiv = "1";
            futuresCloseMarginChangeConfirmRequest.limitPrice = "1000";
            futuresCloseMarginChangeConfirmRequest.execCondType = "1";
            futuresCloseMarginChangeConfirmRequest.expirationDateType = "1";
            futuresCloseMarginChangeConfirmRequest.orderCondType = "0";
            //this.ID!��null
            futuresCloseMarginChangeConfirmRequest.id = "0001";
            //this.�ԍό���!=null && this.�ԍό��ʂ̗v�f��!=0 
            futuresCloseMarginChangeConfirmRequest.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit(),
                new WEB3FuturesOptionsCloseMarginContractUnit()};
            futuresCloseMarginChangeConfirmRequest.validateAtReverseOrder();
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
