head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.24.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3OptionsCloseMarginChangeConfirmRequestTest_OT.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���I�v�V���������ԍϊm�F���N�G�X�g(WEB3OptionsCloseMarginChangeConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2008/06/05 �򐉃i (���u) �V�K�쐬
*/

package webbroker3.ifo.message;

import test.util.JunitTestBase;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * @@author �򐉃i
 * @@version 1.0 
 */
public class WEB3OptionsCloseMarginChangeConfirmRequestTest_OT extends JunitTestBase{
    private static WEB3LogUtility log =
	        WEB3LogUtility.getInstance(WEB3OptionsCloseMarginChangeConfirmRequest.class);
	   
	WEB3OptionsCloseMarginChangeConfirmRequest l_request = 
		new WEB3OptionsCloseMarginChangeConfirmRequest();
	
	public WEB3OptionsCloseMarginChangeConfirmRequestTest_OT(String arg0) {
		super(arg0);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/*
	 * this.�����P���敪��null�̒l�ł���Η�O���X���[����B
	 */
	public void testValidate_case001() {

        final String STR_METHOD_NAME = "testValidate_case001()";
        log.entering(TEST_START + STR_METHOD_NAME);
		try 
		{
			l_request.validate();
			fail();
		}
		catch(WEB3BaseException l_ex)
		{
			log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00184);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
		}
		catch(Exception l_ex)
		{
			fail();
			log.error(ERROR + l_ex.getMessage(), l_ex);
		}
	}

	/**
	 * this.�h�c=null?�̏ꍇ�A��O���X���[����B
	 */
	public void testValidate_case002() {

        final String STR_METHOD_NAME = "testValidate_case002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        // �����P���敪
        l_request.orderPriceDiv = "0";
        // ���s����
        l_request.execCondType = "1";
        //���������敪
        l_request.expirationDateType = "1";
        // ���������敪
        l_request.orderCondType = "0";
		try 
		{
			l_request.validate();
			fail();
		}
		catch(WEB3BaseException l_ex)
		{
			log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00080);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
		}
		catch(Exception l_ex)
		{
			fail();
			log.error(ERROR + l_ex.getMessage(), l_ex);
		}
	}
	
	/**
	 * this.�ԍό���=null �̏ꍇ�A��O���X���[����B
	 */
	public void testValidate_case003() {

        final String STR_METHOD_NAME = "testValidate_case003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        // �����P���敪
        l_request.orderPriceDiv = "0";
        // ���s����
        l_request.execCondType = "1";
        //���������敪
        l_request.expirationDateType = "1";
        // ���������敪
        l_request.orderCondType = "0";
        l_request.id = "1001";
        //�ԍό���
        l_request.closeMarginContractUnits = null;
		try 
		{
			l_request.validate();
			fail();
		}
		catch(WEB3BaseException l_ex)
		{
			log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00178);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
		}
		catch(Exception l_ex)
		{
			fail();
			log.error(ERROR + l_ex.getMessage(), l_ex);
		}
	}
	
	/**
	 * this.�ԍό��ʂ̗v�f��=0?�̏ꍇ�A��O���X���[����B
	 */
	public void testValidate_case004() {

        final String STR_METHOD_NAME = "testValidate_case004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        // �����P���敪
        l_request.orderPriceDiv = "0";
        // ���s����
        l_request.execCondType = "1";
        //���������敪
        l_request.expirationDateType = "1";
        // ���������敪
        l_request.orderCondType = "0";
        l_request.id = "1001";
        //�ԍό���
        l_request.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[0];
		try 
		{
			l_request.validate();
			fail();
		}
		catch(WEB3BaseException l_ex)
		{
			log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00178);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
		}
		catch(Exception l_ex)
		{
			fail();
			log.error(ERROR + l_ex.getMessage(), l_ex);
		}
	}
	
	/**
	 * this.�������ʁ�null and this.�������ʁ����� �̏ꍇ�A ��O���X���[����B 
	 */
	public void testValidate_case005() {

        final String STR_METHOD_NAME = "testValidate_case005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        // �����P���敪
        l_request.orderPriceDiv = "0";
        // ���s����
        l_request.execCondType = "1";
        //���������敪
        l_request.expirationDateType = "1";
        // ���������敪
        l_request.orderCondType = "0";
        l_request.id = "1001";
        //�ԍό���
        l_request.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[2];
        WEB3FuturesOptionsCloseMarginContractUnit l_unit = new WEB3FuturesOptionsCloseMarginContractUnit();
        l_request.closeMarginContractUnits[0] = l_unit;
        l_request.opOrderQuantity = "a";
		try 
		{
			l_request.validate();
			fail();
		}
		catch(WEB3BaseException l_ex)
		{
			log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00075);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
		}
		catch(Exception l_ex)
		{
			fail();
			log.error(ERROR + l_ex.getMessage(), l_ex);
		}
	}
	
	/**
	 * this.�������ʁ�null and this.�������� = 0 �̏ꍇ�A ��O���X���[����B 
	 */
	public void testValidate_case006() {

        final String STR_METHOD_NAME = "testValidate_case006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        // �����P���敪
        l_request.orderPriceDiv = "0";
        // ���s����
        l_request.execCondType = "1";
        //���������敪
        l_request.expirationDateType = "1";
        // ���������敪
        l_request.orderCondType = "0";
        l_request.id = "1001";
        //�ԍό���
        l_request.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[2];
        WEB3FuturesOptionsCloseMarginContractUnit l_unit = new WEB3FuturesOptionsCloseMarginContractUnit();
        l_request.closeMarginContractUnits[0] = l_unit;
        l_request.opOrderQuantity = "0";
		try 
		{
			l_request.validate();
			fail();
		}
		catch(WEB3BaseException l_ex)
		{
			log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00076);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
		}
		catch(Exception l_ex)
		{
			fail();
			log.error(ERROR + l_ex.getMessage(), l_ex);
		}
	}
	
	/**
	 * this.�������ʁ�null and this.�������� < 0 �̏ꍇ�A ��O���X���[����B 
	 */
	public void testValidate_case007() {

        final String STR_METHOD_NAME = "testValidate_case007()";
        log.entering(TEST_START + STR_METHOD_NAME);
        // �����P���敪
        l_request.orderPriceDiv = "0";
        // ���s����
        l_request.execCondType = "1";
        //���������敪
        l_request.expirationDateType = "1";
        // ���������敪
        l_request.orderCondType = "0";
        l_request.id = "1001";
        //�ԍό���
        l_request.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[2];
        WEB3FuturesOptionsCloseMarginContractUnit l_unit = new WEB3FuturesOptionsCloseMarginContractUnit();
        l_request.closeMarginContractUnits[0] = l_unit;
        l_request.opOrderQuantity = "-6";
		try 
		{
			l_request.validate();
			fail();
		}
		catch(WEB3BaseException l_ex)
		{
			log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00076);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
		}
		catch(Exception l_ex)
		{
			fail();
			log.error(ERROR + l_ex.getMessage(), l_ex);
		}
	}
	
	/**
	 * �ԍό���.���Ϗ��ʁ�null and �ԍό���.����=null �̏ꍇ�A��O���X���[����B
	 */
	public void testValidate_case008() {

        final String STR_METHOD_NAME = "testValidate_case007()";
        log.entering(TEST_START + STR_METHOD_NAME);
        // �����P���敪
        l_request.orderPriceDiv = "0";
        // ���s����
        l_request.execCondType = "1";
        //���������敪
        l_request.expirationDateType = "1";
        // ���������敪
        l_request.orderCondType = "0";
        l_request.id = "1001";
        //�ԍό���
        l_request.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[1];
        WEB3FuturesOptionsCloseMarginContractUnit l_unit = new WEB3FuturesOptionsCloseMarginContractUnit();
        l_unit.id = "1001";
        l_unit.settlePriority = "1";
        l_request.closeMarginContractUnits[0] = l_unit;
        
		try 
		{
			l_request.validate();
			fail();
		}
		catch(WEB3BaseException l_ex)
		{
			log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00180);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
		}
		catch(Exception l_ex)
		{
			fail();
			log.error(ERROR + l_ex.getMessage(), l_ex);
		}
	}
	
	/**
	 * this.�������� = null and �i���ׂĂ̕ԍό��ʂ̐���=0 �j �̏ꍇ�A��O���X���[����B
	 */
	public void testValidate_case009() {

        final String STR_METHOD_NAME = "testValidate_case009()";
        log.entering(TEST_START + STR_METHOD_NAME);
        // �����P���敪
        l_request.orderPriceDiv = "0";
        // ���s����
        l_request.execCondType = "1";
        //���������敪
        l_request.expirationDateType = "1";
        // ���������敪
        l_request.orderCondType = "0";
        l_request.id = "1001";
        //�ԍό���
        l_request.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[1];
        WEB3FuturesOptionsCloseMarginContractUnit l_unit = new WEB3FuturesOptionsCloseMarginContractUnit();
        l_unit.id = "1001";
        l_unit.settlePriority = "1";
        l_unit.contractOrderQuantity = "0";
        l_request.closeMarginContractUnits[0] = l_unit;
        
		try 
		{
			l_request.validate();
			fail();
		}
		catch(WEB3BaseException l_ex)
		{
			log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00285);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
		}
		catch(Exception l_ex)
		{
			fail();
			log.error(ERROR + l_ex.getMessage(), l_ex);
		}
	}
	
	/**
	 * this.�������� = null and �i���ׂĂ̕ԍό��ʂ̐���=null �j �̏ꍇ�A��O���X���[����B
	 */
	public void testValidate_case010() {

        final String STR_METHOD_NAME = "testValidate_case010()";
        log.entering(TEST_START + STR_METHOD_NAME);
        // �����P���敪
        l_request.orderPriceDiv = "0";
        // ���s����
        l_request.execCondType = "1";
        //���������敪
        l_request.expirationDateType = "1";
        // ���������敪
        l_request.orderCondType = "0";
        l_request.id = "1001";
        
        //�ԍό���
        l_request.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnitForTest[1];
        WEB3FuturesOptionsCloseMarginContractUnitForTest l_unit = new WEB3FuturesOptionsCloseMarginContractUnitForTest();
        l_unit.id = "1001";
        l_unit.settlePriority = "1";
        l_request.closeMarginContractUnits[0] = l_unit;
        
		try 
		{
			l_request.validate();
			fail();
		}
		catch(WEB3BaseException l_ex)
		{
			log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00285);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
		}
		catch(Exception l_ex)
		{
			fail();
			log.error(ERROR + l_ex.getMessage(), l_ex);
		}
	}
	
	/**
	 * ����ɝ�
	 */
	public void testValidate_case011() {

        final String STR_METHOD_NAME = "testValidate_case011()";
        log.entering(TEST_START + STR_METHOD_NAME);
        // �����P���敪
        l_request.orderPriceDiv = "0";
        // ���s����
        l_request.execCondType = "1";
        //���������敪
        l_request.expirationDateType = "1";
        // ���������敪
        l_request.orderCondType = "0";
        l_request.id = "1001";
        
        //�ԍό���
        l_request.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnitForTest[1];
        WEB3FuturesOptionsCloseMarginContractUnitForTest l_unit = new WEB3FuturesOptionsCloseMarginContractUnitForTest();
        l_unit.id = "1001";
        l_unit.settlePriority = "1";
        l_unit.contractOrderQuantity = "2";
        l_request.closeMarginContractUnits[0] = l_unit;
        
		try 
		{
			l_request.validate();
		}
		catch(Exception l_ex)
		{
			fail();
			log.error(ERROR + l_ex.getMessage(), l_ex);
		}
	}
	
	/**
	 * this.�����P���敪��null�̒l�ł���Η�O���X���[����B
	 */
	public void testValidateATReserveOrder_case001() {
        final String STR_METHOD_NAME = "testValidateATReserveOrder_case001()";
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
		catch(Exception l_ex)
		{
			fail();
			log.error(ERROR + l_ex.getMessage(), l_ex);
		}

	}
	
	/**
	 * this.�h�c=null?�̏ꍇ�A��O���X���[����B
	 */
	public void testValidateATReserveOrder_case002() {
        final String STR_METHOD_NAME = "testValidateATReserveOrder_case002()";
        log.entering(TEST_START + STR_METHOD_NAME);
		try 
		{
			// �����P���敪
	        l_request.orderPriceDiv = "0";
	        // ���s����
	        l_request.execCondType = "1";
	        //���������敪
	        l_request.expirationDateType = "1";
	        // ���������敪
	        l_request.orderCondType = "0";
	        
			l_request.validateATReserveOrder();
			fail();
		}
		catch(WEB3BaseException l_ex)
		{
			log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00600);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
		}
		catch(Exception l_ex)
		{
			fail();
			log.error(ERROR + l_ex.getMessage(), l_ex);
		}

	}
	
	/**
	 * this.�ԍό���=null �̏ꍇ�A��O���X���[����B
	 */
	public void testValidateATReserveOrder_case003() {
        final String STR_METHOD_NAME = "testValidateATReserveOrder_case003()";
        log.entering(TEST_START + STR_METHOD_NAME);
		try 
		{
            // �����P���敪
	        l_request.orderPriceDiv = "0";
	        // ���s����
	        l_request.execCondType = "1";
	        //���������敪
	        l_request.expirationDateType = "1";
	        // ���������敪
	        l_request.orderCondType = "0";
	        
	        l_request.id = "2001";
	        
			l_request.validateATReserveOrder();
			fail();
		}
		catch(WEB3BaseException l_ex)
		{
			log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00178);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
		}
		catch(Exception l_ex)
		{
			fail();
			log.error(ERROR + l_ex.getMessage(), l_ex);
		}

	}
	
	/**
	 * this.�ԍό��ʂ̗v�f��=0?�̏ꍇ�A��O���X���[����B
	 */
	public void testValidateATReserveOrder_case004() {
        final String STR_METHOD_NAME = "testValidateATReserveOrder_case004()";
        log.entering(TEST_START + STR_METHOD_NAME);
		try 
		{
            // �����P���敪
	        l_request.orderPriceDiv = "0";
	        // ���s����
	        l_request.execCondType = "1";
	        //���������敪
	        l_request.expirationDateType = "1";
	        // ���������敪
	        l_request.orderCondType = "0";
	        
	        l_request.id = "2001";
            // �ԍό���
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
		catch(Exception l_ex)
		{
			fail();
			log.error(ERROR + l_ex.getMessage(), l_ex);
		}

	}
	
	/**
	 * �@@���s������"������"�̏ꍇ�́A�u�A�������͎��s�����w��s�v�̗�O��throw����B
	 */
	public void testValidateATReserveOrder_case005() {
        final String STR_METHOD_NAME = "testValidateATReserveOrder_case005()";
        log.entering(TEST_START + STR_METHOD_NAME);
		try 
		{
            // �����P���敪
	        l_request.orderPriceDiv = "0";
	        // ���s����
	        l_request.execCondType = "1";
	        //���������敪
	        l_request.expirationDateType = "1";
	        // ���������敪
	        l_request.orderCondType = "0";
	        
	        l_request.id = "2001";
	        
	        l_request.execCondType = "3";
            // �ԍό���
	        l_request.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[1];
	        WEB3FuturesOptionsCloseMarginContractUnit l_unit = new WEB3FuturesOptionsCloseMarginContractUnit();
	        l_request.closeMarginContractUnits[0] = l_unit;
			l_request.validateATReserveOrder();
			fail();
		}
		catch(WEB3BaseException l_ex)
		{
			log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_02235);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
		}
		catch(Exception l_ex)
		{
			fail();
			log.error(ERROR + l_ex.getMessage(), l_ex);
		}

	}
	
	/**
	 * ����ɝ�,����I���B
	 */
	public void testValidateATReserveOrder_case006() {
        final String STR_METHOD_NAME = "testValidateATReserveOrder_case006()";
        log.entering(TEST_START + STR_METHOD_NAME);
		try 
		{
            // �����P���敪
	        l_request.orderPriceDiv = "0";
	        // ���s����
	        l_request.execCondType = "1";
	        //���������敪
	        l_request.expirationDateType = "1";
	        // ���������敪
	        l_request.orderCondType = "0";
	        
	        l_request.id = "2001";
	        
	        l_request.execCondType = "1";
            // �ԍό���
	        l_request.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[1];
	        WEB3FuturesOptionsCloseMarginContractUnit l_unit = new WEB3FuturesOptionsCloseMarginContractUnit();
	        l_request.closeMarginContractUnits[0] = l_unit;
			l_request.validateATReserveOrder();
		}
		catch(Exception l_ex)
		{
			l_ex.printStackTrace();
			fail();
			log.error(ERROR + l_ex.getMessage(), l_ex);
		}

	}

	private class WEB3FuturesOptionsCloseMarginContractUnitForTest extends WEB3FuturesOptionsCloseMarginContractUnit
	{
	    public void validate() throws WEB3BaseException 
	    {
	    	
	    }
	}
}
@
