head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.25.22;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3OptionsCloseMarginConfirmRequestTest_OT.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���I�v�V�����ԍϊm�F���N�G�X�g�N���X(WEB3OptionsCloseMarginConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/11 ������ �V�K�쐬
*/
package webbroker3.ifo.message;

import test.util.JunitTestBase;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
/**
 * @@author ������
 * @@version 1.0
 */
public class WEB3OptionsCloseMarginConfirmRequestTest_OT extends JunitTestBase
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3OptionsCloseMarginConfirmRequestTest.class);

    WEB3OptionsCloseMarginConfirmRequest l_request = null;
    
	public WEB3OptionsCloseMarginConfirmRequestTest_OT(String arg0)
	{
		super(arg0);
	}

	protected void setUp() throws Exception
	{
		super.setUp();
        l_request = new WEB3OptionsCloseMarginConfirmRequest();
	}

	protected void tearDown() throws Exception
	{
		super.tearDown();
	}

    /**
     * �X�[�p�[�N���X��validate���\�b�h���Ăяo��
     * validate�e�o��ُ�
     * �����w���I�v�V�������ʓ��̓��N�G�X.�����P���敪= null.
     */
	public void test_validate_case0001()
	{
        final String STR_METHOD_NAME = "test_validate_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00184);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }       
	}
	
    /**
     * this.�ԍό���=null �̏ꍇ
     */	
	public void test_validate_case0002()
	
	{
        final String STR_METHOD_NAME = "test_validate_case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            this.commonRequest();
            l_request.closeMarginContractUnits = null;
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00178);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }		
	}
	
    /**
     * this.�ԍό��ʂ̗v�f��=0 �̏ꍇ�A 
     * ��O���X���[����B
     */
	public void test_validate_case0003()
	{
        final String STR_METHOD_NAME = "test_validate_case0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3FuturesOptionsCloseMarginContractUnit[] l_closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[0];
            this.commonRequest();
            l_request.closeMarginContractUnits = l_closeMarginContractUnits;
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00178);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }		
	}
	
    /**
   �@@ this.���Ϗ�����null and 
   �@@ this.���Ϗ������i�ȉ��̒l�j �̏ꍇ�A��O���X���[����B 
    �E�h0�F�����_���h 
    �E�h1�F�P���v���h 
    �E�h2�F�P�������h 
    �E�h3�F�������h
    */
	public void test_validate_case0004()
	{
        final String STR_METHOD_NAME = "test_validate_case0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3FuturesOptionsCloseMarginContractUnit[] l_closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[1];
            this.commonRequest();
            l_request.closeMarginContractUnits = l_closeMarginContractUnits;
            l_request.closingOrder = "4";
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00179);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }	
	}

    /**
    �ꊇ�ԍ�(this.�ԍό��ʂ̗v�f��>1)�@@and�@@ 
    �@@this.���Ϗ���==null�̏ꍇ�A��O�u�ꊇ�ԍώ��A���Ϗ����͎w�肵�Ă��������B�v���X���[����B
    */
	public void test_validate_case0005()
	{
        final String STR_METHOD_NAME = "test_validate_case0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3FuturesOptionsCloseMarginContractUnit[] l_closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[2];
            this.commonRequest();
            l_request.closeMarginContractUnits = l_closeMarginContractUnits;
            l_request.closingOrder = null;
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02304);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }		
	}
	
    /**
	�ithis.���Ϗ���=null �j and this.��������=null �̏ꍇ
    */	
	public void test_validate_case0006()
	{
        final String STR_METHOD_NAME = "test_validate_case0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3FuturesOptionsCloseMarginContractUnit[] l_closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[2];
            this.commonRequest();
            l_request.closeMarginContractUnits = l_closeMarginContractUnits;
            l_request.closingOrder = "1";
            l_request.opOrderQuantity = null;         
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00245);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }		
	}
	
    /**
    �ithis.���Ϗ���= �h1�F�P���v���h �j and this.��������=null �̏ꍇ
    */
	public void test_validate_case0007()
	{
        final String STR_METHOD_NAME = "test_validate_case0007()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3FuturesOptionsCloseMarginContractUnit[] l_closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[2];
            this.commonRequest();
            l_request.closeMarginContractUnits = l_closeMarginContractUnits;
            l_request.closingOrder = "2";
            l_request.opOrderQuantity = null;         
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00245);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }        
	}
	
    /**
    �ithis.���Ϗ���= 2�F�P�������h�j and this.��������=null �̏ꍇ
    */	
	public void test_validate_case0008()
	{
        final String STR_METHOD_NAME = "test_validate_case0008()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3FuturesOptionsCloseMarginContractUnit[] l_closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[2];
            this.commonRequest();
            l_request.closeMarginContractUnits = l_closeMarginContractUnits;
            l_request.closingOrder = "3";
            l_request.opOrderQuantity = null;         
            l_request.validateATReserveOrder();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00245);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }		
	}
	
    /**
    �ithis.���Ϗ���= �h3�F�������h�j and this.��������=null �̏ꍇ
    */
	public void test_validate_case0009()
	{
        final String STR_METHOD_NAME = "test_validate_case0009()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3FuturesOptionsCloseMarginContractUnit[] l_closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[1];
            this.commonRequest();
            l_request.closeMarginContractUnits = l_closeMarginContractUnits;
            l_request.opOrderQuantity = null;         
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00245);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }		
	}
	
    /**
    this.�������ʁ�null and this.�������ʁ����� �̏ꍇ�A 
    ��O���X���[����B 
    */
	public void test_validate_case0010()
	{
        final String STR_METHOD_NAME = "test_validate_case0010()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3FuturesOptionsCloseMarginContractUnit[] l_closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[2];
            this.commonRequest();
            l_request.closeMarginContractUnits = l_closeMarginContractUnits;
            l_request.closingOrder = "1";
            l_request.opOrderQuantity = "abc";         
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00075);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }		
	}
	
    /**
    this.�������ʁ�null and this.��������=0 �̏ꍇ�A 
    ��O���X���[����B 
    */
	public void test_validate_case0011()
	{
        final String STR_METHOD_NAME = "test_validate_case0011()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3FuturesOptionsCloseMarginContractUnit[] l_closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[1];
            this.commonRequest();
            l_request.closeMarginContractUnits = l_closeMarginContractUnits;
            l_request.opOrderQuantity = "0";         
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00076);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }		
	}
	
    /**
    this.�������ʁ�null and this.��������<0 �̏ꍇ�A 
    ��O���X���[����B 
    */
	public void test_validate_case0012()
	{
        final String STR_METHOD_NAME = "test_validate_case0012()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3FuturesOptionsCloseMarginContractUnit[] l_closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[1];
            this.commonRequest();
            l_request.closeMarginContractUnits = l_closeMarginContractUnits;
            l_request.opOrderQuantity = "-1";         
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00076);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }		
	}
	
    /**
    this.�������ʁ�null and this.��������<0 �̏ꍇ�A 
    ��O���X���[����B 
    */
	public void test_validate_case0013()
	{
        final String STR_METHOD_NAME = "test_validate_case0013()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3FuturesOptionsCloseMarginContractUnit[] l_closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[1];
            WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit =
                new WEB3FuturesOptionsCloseMarginContractUnit();
            l_closeMarginContractUnit.id = null;
            l_closeMarginContractUnits[0] = l_closeMarginContractUnit;
            this.commonRequest();
            l_request.closeMarginContractUnits = l_closeMarginContractUnits;
            l_request.opOrderQuantity = "1";
            
            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00080);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }		
	}
	
     /**
     this.���Ϗ���=�h0�F�����_���h and �v�f���̂��ׂĂ̌��Ϗ���=null or 0 �̏ꍇ�A 
        ��O���X���[����B
    */
    public void test_validate_case0014()
    {
       final String STR_METHOD_NAME = "test_validate_case0014()";
      log.entering(TEST_START + STR_METHOD_NAME);
      
      try
      {
          WEB3FuturesOptionsCloseMarginContractUnit[] l_closeMarginContractUnits =
              new WEB3FuturesOptionsCloseMarginContractUnit[3];
          
          WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit =
              new WEB3FuturesOptionsCloseMarginContractUnit();
          
          WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit1 =
              new WEB3FuturesOptionsCloseMarginContractUnit();
          
          WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit2 =
              new WEB3FuturesOptionsCloseMarginContractUnit();
          
          l_closeMarginContractUnit.id = "123";
          l_closeMarginContractUnit1.id = "234";
          l_closeMarginContractUnit2.id = "345";
          
          l_closeMarginContractUnits[0] = l_closeMarginContractUnit;
          l_closeMarginContractUnits[1] = l_closeMarginContractUnit1;
          l_closeMarginContractUnits[2] = l_closeMarginContractUnit2;
          this.commonRequest();
          l_request.closeMarginContractUnits = l_closeMarginContractUnits;
          l_request.closingOrder = "0";
          l_request.opOrderQuantity = "1";
          l_request.validate();
          fail();
      }
      catch(WEB3BaseException l_ex)
      {
          log.debug(STR_METHOD_NAME, l_ex);
          assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00180);
          log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
      }
      catch (Exception l_ex)
      {
          fail();
          log.error(ERROR + l_ex.getMessage(), l_ex);
      }
   }
  
    /**
    this.���Ϗ���=�h0�F�����_���h and �v�f���̂��ׂĂ̌��Ϗ���=null or 0 �̏ꍇ�A 
       ��O���X���[����B
   */
   public void test_validate_case0015()
   {
        final String STR_METHOD_NAME = "test_validate_case0015()";
       log.entering(TEST_START + STR_METHOD_NAME);
     
       try
       {
          WEB3FuturesOptionsCloseMarginContractUnit[] l_closeMarginContractUnits =
             new WEB3FuturesOptionsCloseMarginContractUnit[3];
         
          WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit =
             new WEB3FuturesOptionsCloseMarginContractUnit();
         
          WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit1 =
             new WEB3FuturesOptionsCloseMarginContractUnit();
         
          WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit2 =
             new WEB3FuturesOptionsCloseMarginContractUnit();
         
          l_closeMarginContractUnit.id = "123";
          l_closeMarginContractUnit1.id = "234";
          l_closeMarginContractUnit2.id = "345";
          
          l_closeMarginContractUnit.settlePriority = "0";
                  
          l_closeMarginContractUnits[0] = l_closeMarginContractUnit;
          l_closeMarginContractUnits[1] = l_closeMarginContractUnit1;
          l_closeMarginContractUnits[2] = l_closeMarginContractUnit2;
          this.commonRequest();
          l_request.closeMarginContractUnits = l_closeMarginContractUnits;
          l_request.closingOrder = "0";
          l_request.opOrderQuantity = "1";
         
          l_request.validate();
          fail();
        }
        catch(WEB3BaseException l_ex)
        {
             log.debug(STR_METHOD_NAME, l_ex);
             assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00180);
             log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
        	fail();
        	log.error(ERROR + l_ex.getMessage(), l_ex);
        }
    }
    /**
    this.���Ϗ���=�h0�F�����_���h and 
�@@�@@�@@�@@  �i�ԍό���.���Ϗ���>0 and �ԍό���.����>0 �ƂȂ�g�ݍ��킹�����݂��Ȃ��j �ꍇ�A 
      ��O���X���[����B
   */
    public void test_validate_case0016()
    {
    	final String STR_METHOD_NAME = "test_validate_case0016()";
    	log.entering(TEST_START + STR_METHOD_NAME);
    
    	try
    	{
    		WEB3FuturesOptionsCloseMarginContractUnit[] l_closeMarginContractUnits =
    			new WEB3FuturesOptionsCloseMarginContractUnit[3];
        
    		WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit =
    			new WEB3FuturesOptionsCloseMarginContractUnit();
        
    		WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit1 =
    			new WEB3FuturesOptionsCloseMarginContractUnit();
        
    		WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit2 =
    			new WEB3FuturesOptionsCloseMarginContractUnit();
        
    		l_closeMarginContractUnit.id = "123";
    		l_closeMarginContractUnit1.id = "234";
    		l_closeMarginContractUnit2.id = "345";
        
    		l_closeMarginContractUnit.settlePriority = "1";
    		l_closeMarginContractUnit.contractOrderQuantity = "0";
                 
    		l_closeMarginContractUnits[0] = l_closeMarginContractUnit;
    		l_closeMarginContractUnits[1] = l_closeMarginContractUnit1;
    		l_closeMarginContractUnits[2] = l_closeMarginContractUnit2;
    		this.commonRequest();
    		l_request.closeMarginContractUnits = l_closeMarginContractUnits;
    		l_request.closingOrder = "0";
    		l_request.opOrderQuantity = "1";
        
    		l_request.validate();
    		fail();
    	}
    	catch(WEB3BaseException l_ex)
    	{
    		log.debug(STR_METHOD_NAME, l_ex);
    		assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00180);
    		log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
    	}
    	catch (Exception l_ex)
    	{
    		fail();
        log.error(ERROR + l_ex.getMessage(), l_ex);
    	}
    }
    
    /**
    this.���Ϗ���=�inull or �h1�F�P���v���h or �h2�F�P�������h or �h3�F�������h�j �̏ꍇ 
     �ԍό��ʂ̗v�f�������L�̃`�F�b�N���J��Ԃ��čs���B
     �ԍό���.���Ϗ��ʁ�null �̏ꍇ�A  
      ��O���X���[����B
   */
    public void test_validate_case0017()
    {
    	final String STR_METHOD_NAME = "test_validate_case0017()";
    	log.entering(TEST_START + STR_METHOD_NAME);
    
    	try
    	{
    		WEB3FuturesOptionsCloseMarginContractUnit[] l_closeMarginContractUnits =
    			new WEB3FuturesOptionsCloseMarginContractUnit[1];
        
    		WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit =
    			new WEB3FuturesOptionsCloseMarginContractUnit();
        
    		l_closeMarginContractUnit.id = "123";
    	
    		l_closeMarginContractUnit.settlePriority = "1";
    		l_closeMarginContractUnit.contractOrderQuantity = "0";
                 
    		l_closeMarginContractUnits[0] = l_closeMarginContractUnit;
    		this.commonRequest();
    		l_request.closeMarginContractUnits = l_closeMarginContractUnits;
    		l_request.closingOrder = null;
    		l_request.opOrderQuantity = "1";
        
    		l_request.validate();
    		fail();
    	}
    	catch(WEB3BaseException l_ex)
    	{
    		log.debug(STR_METHOD_NAME, l_ex);
    		assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00183);
    		log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
    	}
    	catch (Exception l_ex)
    	{
    		fail();
        log.error(ERROR + l_ex.getMessage(), l_ex);
    	}
    }
  
    /**
    this.���Ϗ���=�inull or �h1�F�P���v���h or �h2�F�P�������h or �h3�F�������h�j �̏ꍇ 
     �ԍό��ʂ̗v�f�������L�̃`�F�b�N���J��Ԃ��čs���B
     �ԍό���.���Ϗ��ʁ�null �̏ꍇ�A  
      ��O���X���[����B
   */
    public void test_validate_case0018()
    {
    	final String STR_METHOD_NAME = "test_validate_case0018()";
    	log.entering(TEST_START + STR_METHOD_NAME);
    
    	try
    	{
    		WEB3FuturesOptionsCloseMarginContractUnit[] l_closeMarginContractUnits =
    			new WEB3FuturesOptionsCloseMarginContractUnit[3];
        
    		WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit =
    			new WEB3FuturesOptionsCloseMarginContractUnit();
        
    		WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit1 =
    			new WEB3FuturesOptionsCloseMarginContractUnit();
        
    		WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit2 =
    			new WEB3FuturesOptionsCloseMarginContractUnit();
        
    		l_closeMarginContractUnit.id = "123";
    		l_closeMarginContractUnit1.id = "234";
    		l_closeMarginContractUnit2.id = "345";
        
    		l_closeMarginContractUnit.settlePriority = "1";
    		l_closeMarginContractUnit.contractOrderQuantity = "0";
                 
    		l_closeMarginContractUnits[0] = l_closeMarginContractUnit;
    		l_closeMarginContractUnits[1] = l_closeMarginContractUnit1;
    		l_closeMarginContractUnits[2] = l_closeMarginContractUnit2;
    		this.commonRequest();
    		l_request.closeMarginContractUnits = l_closeMarginContractUnits;
    		l_request.closingOrder = "1";
    		l_request.opOrderQuantity = "1";
        
    		l_request.validate();
    		fail();
    	}
    	catch(WEB3BaseException l_ex)
    	{
    		log.debug(STR_METHOD_NAME, l_ex);
    		assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00183);
    		log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
    	}
    	catch (Exception l_ex)
    	{
    		fail();
        log.error(ERROR + l_ex.getMessage(), l_ex);
    	}
    }
  
    /**
    this.���Ϗ���=�inull or �h1�F�P���v���h or �h2�F�P�������h or �h3�F�������h�j �̏ꍇ 
     �ԍό��ʂ̗v�f�������L�̃`�F�b�N���J��Ԃ��čs���B
     �ԍό���.���Ϗ��ʁ�null �̏ꍇ�A  
      ��O���X���[����B
   */
    public void test_validate_case0019()
    {
    	final String STR_METHOD_NAME = "test_validate_case0019()";
    	log.entering(TEST_START + STR_METHOD_NAME);
    
    	try
    	{
    		WEB3FuturesOptionsCloseMarginContractUnit[] l_closeMarginContractUnits =
    			new WEB3FuturesOptionsCloseMarginContractUnit[3];
        
    		WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit =
    			new WEB3FuturesOptionsCloseMarginContractUnit();
        
    		WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit1 =
    			new WEB3FuturesOptionsCloseMarginContractUnit();
        
    		WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit2 =
    			new WEB3FuturesOptionsCloseMarginContractUnit();
        
    		l_closeMarginContractUnit.id = "123";
    		l_closeMarginContractUnit1.id = "234";
    		l_closeMarginContractUnit2.id = "345";
        
    		l_closeMarginContractUnit.settlePriority = "1";
    		l_closeMarginContractUnit.contractOrderQuantity = "0";
                 
    		l_closeMarginContractUnits[0] = l_closeMarginContractUnit;
    		l_closeMarginContractUnits[1] = l_closeMarginContractUnit1;
    		l_closeMarginContractUnits[2] = l_closeMarginContractUnit2;
    		this.commonRequest();
    		l_request.closeMarginContractUnits = l_closeMarginContractUnits;
    		l_request.closingOrder = "2";
    		l_request.opOrderQuantity = "1";
        
    		l_request.validate();
    		fail();
    	}
    	catch(WEB3BaseException l_ex)
    	{
    		log.debug(STR_METHOD_NAME, l_ex);
    		assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00183);
    		log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
    	}
    	catch (Exception l_ex)
    	{
    		fail();
        log.error(ERROR + l_ex.getMessage(), l_ex);
    	}
    }
  
    /**
    this.���Ϗ���=�inull or �h1�F�P���v���h or �h2�F�P�������h or �h3�F�������h�j �̏ꍇ 
     �ԍό��ʂ̗v�f�������L�̃`�F�b�N���J��Ԃ��čs���B
     �ԍό���.���Ϗ��ʁ�null �̏ꍇ�A  
      ��O���X���[����B
   */
    public void test_validate_case0020()
    {
    	final String STR_METHOD_NAME = "test_validate_case0020()";
    	log.entering(TEST_START + STR_METHOD_NAME);
    
    	try
    	{
    		WEB3FuturesOptionsCloseMarginContractUnit[] l_closeMarginContractUnits =
    			new WEB3FuturesOptionsCloseMarginContractUnit[3];
        
    		WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit =
    			new WEB3FuturesOptionsCloseMarginContractUnit();
        
    		WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit1 =
    			new WEB3FuturesOptionsCloseMarginContractUnit();
        
    		WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit2 =
    			new WEB3FuturesOptionsCloseMarginContractUnit();
        
    		l_closeMarginContractUnit.id = "123";
    		l_closeMarginContractUnit1.id = "234";
    		l_closeMarginContractUnit2.id = "345";
        
    		l_closeMarginContractUnit.settlePriority = "1";
    		l_closeMarginContractUnit.contractOrderQuantity = "0";
                 
    		l_closeMarginContractUnits[0] = l_closeMarginContractUnit;
    		l_closeMarginContractUnits[1] = l_closeMarginContractUnit1;
    		l_closeMarginContractUnits[2] = l_closeMarginContractUnit2;
    		this.commonRequest();
    		l_request.closeMarginContractUnits = l_closeMarginContractUnits;
    		l_request.closingOrder = "3";
    		l_request.opOrderQuantity = "1";
        
    		l_request.validate();
    		fail();
    	}
    	catch(WEB3BaseException l_ex)
    	{
    		log.debug(STR_METHOD_NAME, l_ex);
    		assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00183);
    		log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
    	}
    	catch (Exception l_ex)
    	{
    		fail();
        log.error(ERROR + l_ex.getMessage(), l_ex);
    	}
    }
  
    /**
    this.���Ϗ���=�inull or �h1�F�P���v���h or �h2�F�P�������h or �h3�F�������h�j �̏ꍇ 
     �ԍό��ʂ̗v�f�������L�̃`�F�b�N���J��Ԃ��čs���B
     �ԍό��ʂ�validate()���\�b�h���Ăяo���B 
      ��O���X���[����B
   */
    public void test_validate_case0021()
    {
    	final String STR_METHOD_NAME = "test_validate_case0021()";
    	log.entering(TEST_START + STR_METHOD_NAME);
    
    	try
    	{
    		WEB3FuturesOptionsCloseMarginContractUnit[] l_closeMarginContractUnits =
    			new WEB3FuturesOptionsCloseMarginContractUnit[1];
        
    		WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit =
    			new WEB3FuturesOptionsCloseMarginContractUnit();
            		  	
    		l_closeMarginContractUnit.settlePriority = null;
    		l_closeMarginContractUnit.contractOrderQuantity = "0";
                 
    		l_closeMarginContractUnits[0] = l_closeMarginContractUnit;
    		this.commonRequest();
    		l_request.closeMarginContractUnits = l_closeMarginContractUnits;
    		l_request.closingOrder = null;
    		l_request.opOrderQuantity = "1";
        
    		l_request.validate();
    		fail();
    	}
    	catch(WEB3BaseException l_ex)
    	{
    		log.debug(STR_METHOD_NAME, l_ex);
    		assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00080);
    		log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
    	}
    	catch (Exception l_ex)
    	{
    		fail();
        log.error(ERROR + l_ex.getMessage(), l_ex);
    	}
    }
  
    /**
    this.���Ϗ���=�inull or �h1�F�P���v���h or �h2�F�P�������h or �h3�F�������h�j �̏ꍇ 
     �ԍό��ʂ̗v�f�������L�̃`�F�b�N���J��Ԃ��čs���B
     �ԍό��ʂ�validate()���\�b�h���Ăяo���B 
      ��O���X���[����B
   */
    public void test_validate_case0022()
    {
    	final String STR_METHOD_NAME = "test_validate_case0022()";
    	log.entering(TEST_START + STR_METHOD_NAME);
    
    	try
    	{
    		WEB3FuturesOptionsCloseMarginContractUnit[] l_closeMarginContractUnits =
    			new WEB3FuturesOptionsCloseMarginContractUnit[1];
        
    		WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit =
    			new WEB3FuturesOptionsCloseMarginContractUnit();
            		  	
    		l_closeMarginContractUnit.settlePriority = null;
    		l_closeMarginContractUnit.contractOrderQuantity = "0";
                 
    		l_closeMarginContractUnits[0] = l_closeMarginContractUnit;
    		this.commonRequest();
    		l_request.closeMarginContractUnits = l_closeMarginContractUnits;
    		l_request.closingOrder = "1";
    		l_request.opOrderQuantity = "1";
        
    		l_request.validate();
    		fail();
    	}
    	catch(WEB3BaseException l_ex)
    	{
    		log.debug(STR_METHOD_NAME, l_ex);
    		assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00080);
    		log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
    	}
    	catch (Exception l_ex)
    	{
    		fail();
        log.error(ERROR + l_ex.getMessage(), l_ex);
    	}
    }
  
    /**
    this.���Ϗ���=�inull or �h1�F�P���v���h or �h2�F�P�������h or �h3�F�������h�j �̏ꍇ 
     �ԍό��ʂ̗v�f�������L�̃`�F�b�N���J��Ԃ��čs���B
     �ԍό��ʂ�validate()���\�b�h���Ăяo���B 
      ��O���X���[����B
   */
    public void test_validate_case0023()
    {
    	final String STR_METHOD_NAME = "test_validate_case0023()";
    	log.entering(TEST_START + STR_METHOD_NAME);
    
    	try
    	{
    		WEB3FuturesOptionsCloseMarginContractUnit[] l_closeMarginContractUnits =
    			new WEB3FuturesOptionsCloseMarginContractUnit[1];
        
    		WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit =
    			new WEB3FuturesOptionsCloseMarginContractUnit();
            		  	
    		l_closeMarginContractUnit.settlePriority = null;
    		l_closeMarginContractUnit.contractOrderQuantity = "0";
                 
    		l_closeMarginContractUnits[0] = l_closeMarginContractUnit;
    		this.commonRequest();
    		l_request.closeMarginContractUnits = l_closeMarginContractUnits;
    		l_request.closingOrder = "2";
    		l_request.opOrderQuantity = "1";
        
    		l_request.validate();
    		fail();
    	}
    	catch(WEB3BaseException l_ex)
    	{
    		log.debug(STR_METHOD_NAME, l_ex);
    		assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00080);
    		log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
    	}
    	catch (Exception l_ex)
    	{
    		fail();
        log.error(ERROR + l_ex.getMessage(), l_ex);
    	}
    }
  
    /**
    this.���Ϗ���=�inull or �h1�F�P���v���h or �h2�F�P�������h or �h3�F�������h�j �̏ꍇ 
     �ԍό��ʂ̗v�f�������L�̃`�F�b�N���J��Ԃ��čs���B
     �ԍό��ʂ�validate()���\�b�h���Ăяo���B 
      ��O���X���[����B
   */
    public void test_validate_case0024()
    {
    	final String STR_METHOD_NAME = "test_validate_case0024()";
    	log.entering(TEST_START + STR_METHOD_NAME);
    
    	try
    	{
    		WEB3FuturesOptionsCloseMarginContractUnit[] l_closeMarginContractUnits =
    			new WEB3FuturesOptionsCloseMarginContractUnit[1];
        
    		WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit =
    			new WEB3FuturesOptionsCloseMarginContractUnit();
            		  	
    		l_closeMarginContractUnit.settlePriority = null;
    		l_closeMarginContractUnit.contractOrderQuantity = "0";
                 
    		l_closeMarginContractUnits[0] = l_closeMarginContractUnit;
    		this.commonRequest();
    		l_request.closeMarginContractUnits = l_closeMarginContractUnits;
    		l_request.closingOrder = "3";
    		l_request.opOrderQuantity = "1";
        
    		l_request.validate();
    		fail();
    	}
    	catch(WEB3BaseException l_ex)
    	{
    		log.debug(STR_METHOD_NAME, l_ex);
    		assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00080);
    		log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
    	}
    	catch (Exception l_ex)
    	{
    		fail();
        log.error(ERROR + l_ex.getMessage(), l_ex);
    	}
    }
    
    /**
    validate����ʉ߁B
    */
    public void test_validate_case0025()
    {
        final String STR_METHOD_NAME = "test_validate_case0025()";
       log.entering(TEST_START + STR_METHOD_NAME);
       
       try
       {
           WEB3FuturesOptionsCloseMarginContractUnit[] l_closeMarginContractUnits =
               new WEB3FuturesOptionsCloseMarginContractUnit[1];
           
           WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit =
               new WEB3FuturesOptionsCloseMarginContractUnit();
           
           l_closeMarginContractUnit.id = "123";
           l_closeMarginContractUnits[0]= l_closeMarginContractUnit;
           
           this.commonRequest();
           l_request.closeMarginContractUnits = l_closeMarginContractUnits;
           l_request.closingOrder = "1";
           l_request.opOrderQuantity = "1";
           l_request.validate();
       }
       catch (Exception l_ex)
       {
           fail();
           log.error(ERROR + l_ex.getMessage(), l_ex);
       }
       log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
    }
 
  
	/*
	 �X�[�p�[�N���X��validate���\�b�h���Ăяo�� 
	*/
	public void test_validateAT_case0001()
	{

        final String STR_METHOD_NAME = "test_validateAT_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_request.validateATReserveOrder();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00184);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }      
	}
	
    /**
     * this.�ԍό���=null �̏ꍇ
     */	
	public void test_validateAT_case0002()
	
	{
        final String STR_METHOD_NAME = "test_validateAT_case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            this.commonRequest();
            l_request.closeMarginContractUnits = null;
            l_request.validateATReserveOrder();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00178);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }		
	}
	
    /**
     * this.�ԍό��ʂ̗v�f��=0 �̏ꍇ�A 
     * ��O���X���[����B
     */
	public void test_validateAT_case0003()
	{
        final String STR_METHOD_NAME = "test_validateAT_case0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3FuturesOptionsCloseMarginContractUnit[] l_closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[0];
            this.commonRequest();
            l_request.closeMarginContractUnits = l_closeMarginContractUnits;
            l_request.validateATReserveOrder();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00178);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }		
	}
	
    /**
   �@@ this.���Ϗ�����null and 
   �@@ this.���Ϗ������i�ȉ��̒l�j �̏ꍇ�A��O���X���[����B 
    �E�h0�F�����_���h 
    �E�h1�F�P���v���h 
    �E�h2�F�P�������h 
    �E�h3�F�������h
    */
	public void test_validateAT_case0004()
	{
        final String STR_METHOD_NAME = "test_validateAT_case0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3FuturesOptionsCloseMarginContractUnit[] l_closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[1];
            this.commonRequest();
            l_request.closeMarginContractUnits = l_closeMarginContractUnits;
            l_request.closingOrder = "4";
            l_request.validateATReserveOrder();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00179);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }	
	}

    /**
    �ithis.���Ϗ���= �h1�F�P���v���h �j and this.��������=null �̏ꍇ
    */
	public void test_validateAT_case0005()
	{
        final String STR_METHOD_NAME = "test_validateAT_case0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3FuturesOptionsCloseMarginContractUnit[] l_closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[2];
            this.commonRequest();
            l_request.closeMarginContractUnits = l_closeMarginContractUnits;
            l_request.closingOrder = "2";
            l_request.opOrderQuantity = null;         
            l_request.validateATReserveOrder();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00245);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }        
	}
	
    /**
    �ithis.���Ϗ���= 2�F�P�������h�j and this.��������=null �̏ꍇ
    */	
	public void test_validateAT_case0006()
	{
        final String STR_METHOD_NAME = "test_validateAT_case0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3FuturesOptionsCloseMarginContractUnit[] l_closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[2];
            this.commonRequest();
            l_request.closeMarginContractUnits = l_closeMarginContractUnits;
            l_request.closingOrder = "3";
            l_request.opOrderQuantity = null;         
            l_request.validateATReserveOrder();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00245);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }		
	}
	
    /**
    �ithis.���Ϗ���= �h3�F�������h�j and this.��������=null �̏ꍇ
    */
	public void test_validateAT_case0007()
	{
        final String STR_METHOD_NAME = "test_validateAT_case0007()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3FuturesOptionsCloseMarginContractUnit[] l_closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[1];
            this.commonRequest();
            l_request.closeMarginContractUnits = l_closeMarginContractUnits;
            l_request.opOrderQuantity = null;         
            l_request.validateATReserveOrder();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00245);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }		
	}
	
    /**
	�ithis.���Ϗ���=null �j and this.��������=null �̏ꍇ
    */	
	public void test_validateAT_case0008()
	{
        final String STR_METHOD_NAME = "test_validateAT_case0008()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3FuturesOptionsCloseMarginContractUnit[] l_closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[2];
            this.commonRequest();
            l_request.closeMarginContractUnits = l_closeMarginContractUnits;
            l_request.closingOrder = "1";
            l_request.opOrderQuantity = null;         
            l_request.validateATReserveOrder();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00245);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }		
	}
	
    /**
    this.�������ʁ�null and this.�������ʁ����� �̏ꍇ�A 
    ��O���X���[����B 
    */
	public void test_validateAT_case0009()
	{
        final String STR_METHOD_NAME = "test_validateAT_case0009()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3FuturesOptionsCloseMarginContractUnit[] l_closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[2];
            this.commonRequest();
            l_request.closeMarginContractUnits = l_closeMarginContractUnits;
            l_request.closingOrder = "1";
            l_request.opOrderQuantity = "abc";         
            l_request.validateATReserveOrder();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00075);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }		
	}
	
    /**
    this.�������ʁ�null and this.��������=0 �̏ꍇ�A 
    ��O���X���[����B 
    */
	public void test_validateAT_case0010()
	{
        final String STR_METHOD_NAME = "test_validateAT_case0010()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3FuturesOptionsCloseMarginContractUnit[] l_closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[1];
            this.commonRequest();
            l_request.closeMarginContractUnits = l_closeMarginContractUnits;
            l_request.opOrderQuantity = "0";         
            l_request.validateATReserveOrder();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00076);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }		
	}
	
    /**
    this.�������ʁ�null and this.��������<0 �̏ꍇ�A 
    ��O���X���[����B 
    */
	public void test_validateAT_case0011()
	{
        final String STR_METHOD_NAME = "test_validateAT_case0011()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3FuturesOptionsCloseMarginContractUnit[] l_closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[1];
            this.commonRequest();
            l_request.closeMarginContractUnits = l_closeMarginContractUnits;
            l_request.opOrderQuantity = "-1";         
            l_request.validateATReserveOrder();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00076);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }		
	}
	
    /**
    �ԍό��ʂ̒������ʃ`�F�b�N
    ���Ϗ������h0�F�����_���h�̏ꍇ�̂݁A�ԍό��ʂ̒�������=null �̏ꍇ�A  
      ��O���X���[����B
   */
    public void test_validateAT_case0012()
    {
    	final String STR_METHOD_NAME = "test_validateAT_case0012()";
    	log.entering(TEST_START + STR_METHOD_NAME);
    
    	try
    	{
    		WEB3FuturesOptionsCloseMarginContractUnit[] l_closeMarginContractUnits =
    			new WEB3FuturesOptionsCloseMarginContractUnit[3];
        
    		WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit =
    			new WEB3FuturesOptionsCloseMarginContractUnit();
    		
    		WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit1 =
    			new WEB3FuturesOptionsCloseMarginContractUnit();
    		
    		WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit2 =
    			new WEB3FuturesOptionsCloseMarginContractUnit();
    		
    		l_closeMarginContractUnit.id = "123";
    		l_closeMarginContractUnit1.id = "234";
    		l_closeMarginContractUnit2.id = "345";
    		
    		l_closeMarginContractUnit.settlePriority = "1";
    		l_closeMarginContractUnit.contractOrderQuantity = null;
                 
    		l_closeMarginContractUnits[0] = l_closeMarginContractUnit;
    		l_closeMarginContractUnits[1] = l_closeMarginContractUnit1;
    		l_closeMarginContractUnits[2] = l_closeMarginContractUnit2;
    		
    		this.commonRequest();
    		l_request.closeMarginContractUnits = l_closeMarginContractUnits;
    		l_request.closingOrder = "0";
    		l_request.opOrderQuantity = "1";
        	l_request.validateATReserveOrder();
    		fail();
    	}
    	catch(WEB3BaseException l_ex)
    	{
    		log.debug(STR_METHOD_NAME, l_ex);
    		assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_03060);
    		log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
    	}
    	catch (Exception l_ex)
    	{
    		fail();
        log.error(ERROR + l_ex.getMessage(), l_ex);
    	}
    }
  
    /**
    �ԍό��ʂ̒������ʃ`�F�b�N
    ���Ϗ������h0�F�����_���h�̏ꍇ�̂݁A�ԍό��ʂ̒�������=abc �̏ꍇ�A  
      ��O���X���[����B
   */
    public void test_validateAT_case0013()
    {
    	final String STR_METHOD_NAME = "test_validateAT_case0013()";
    	log.entering(TEST_START + STR_METHOD_NAME);
    
    	try
    	{
    		WEB3FuturesOptionsCloseMarginContractUnit[] l_closeMarginContractUnits =
    			new WEB3FuturesOptionsCloseMarginContractUnit[3];
        
    		WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit =
    			new WEB3FuturesOptionsCloseMarginContractUnit();
    		
    		WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit1 =
    			new WEB3FuturesOptionsCloseMarginContractUnit();
    		
    		WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit2 =
    			new WEB3FuturesOptionsCloseMarginContractUnit();
    		
    		l_closeMarginContractUnit.id = "123";
    		l_closeMarginContractUnit1.id = "234";
    		l_closeMarginContractUnit2.id = "345";
    		
    		l_closeMarginContractUnit.settlePriority = "1";
    		l_closeMarginContractUnit.contractOrderQuantity = "abc";
                 
    		l_closeMarginContractUnits[0] = l_closeMarginContractUnit;
    		l_closeMarginContractUnits[1] = l_closeMarginContractUnit1;
    		l_closeMarginContractUnits[2] = l_closeMarginContractUnit2;
    		
    		this.commonRequest();
    		l_request.closeMarginContractUnits = l_closeMarginContractUnits;
    		l_request.closingOrder = "0";
    		l_request.opOrderQuantity = "1";
        	l_request.validateATReserveOrder();
    		fail();
    	}
    	catch(WEB3BaseException l_ex)
    	{
    		log.debug(STR_METHOD_NAME, l_ex);
    		assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_03060);
    		log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
    	}
    	catch (Exception l_ex)
    	{
    		fail();
        log.error(ERROR + l_ex.getMessage(), l_ex);
    	}
    }
    /**
    �ԍό��ʂ̒������ʃ`�F�b�N
    ���Ϗ������h0�F�����_���h�̏ꍇ�̂݁A�ԍό��ʂ̒�������=null �̏ꍇ�A  
      ��O���X���[����B
   */
    public void test_validateAT_case0014()
    {
    	final String STR_METHOD_NAME = "test_validateAT_case0014()";
    	log.entering(TEST_START + STR_METHOD_NAME);
    
    	try
    	{
    		WEB3FuturesOptionsCloseMarginContractUnit[] l_closeMarginContractUnits =
    			new WEB3FuturesOptionsCloseMarginContractUnit[3];
        
    		WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit =
    			new WEB3FuturesOptionsCloseMarginContractUnit();
    		
    		WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit1 =
    			new WEB3FuturesOptionsCloseMarginContractUnit();
    		
    		WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit2 =
    			new WEB3FuturesOptionsCloseMarginContractUnit();
    		
    		l_closeMarginContractUnit.id = "123";
    		l_closeMarginContractUnit1.id = "234";
    		l_closeMarginContractUnit2.id = "345";
    		
    		l_closeMarginContractUnit.settlePriority = "1";
    		l_closeMarginContractUnit.contractOrderQuantity = "-1";
                 
    		l_closeMarginContractUnits[0] = l_closeMarginContractUnit;
    		l_closeMarginContractUnits[1] = l_closeMarginContractUnit1;
    		l_closeMarginContractUnits[2] = l_closeMarginContractUnit2;
    		
    		this.commonRequest();
    		l_request.closeMarginContractUnits = l_closeMarginContractUnits;
    		l_request.closingOrder = "0";
    		l_request.opOrderQuantity = "1";
        	l_request.validateATReserveOrder();
    		fail();
    	}
    	catch(WEB3BaseException l_ex)
    	{
    		log.debug(STR_METHOD_NAME, l_ex);
    		assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_03060);
    		log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
    	}
    	catch (Exception l_ex)
    	{
    		fail();
        log.error(ERROR + l_ex.getMessage(), l_ex);
    	}
    }
    
    /**
    �ԍό��ʂ̒������ʃ`�F�b�N
    ���Ϗ������h0�F�����_���h�̏ꍇ�̂݁A�ԍό��ʂ̒�������=null �̏ꍇ�A  
      ��O���X���[����B
   */
    public void test_validateAT_case0015()
    {
    	final String STR_METHOD_NAME = "test_validateAT_case0015()";
    	log.entering(TEST_START + STR_METHOD_NAME);
    
    	try
    	{
    		WEB3FuturesOptionsCloseMarginContractUnit[] l_closeMarginContractUnits =
    			new WEB3FuturesOptionsCloseMarginContractUnit[3];
        
    		WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit =
    			new WEB3FuturesOptionsCloseMarginContractUnit();
    		
    		WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit1 =
    			new WEB3FuturesOptionsCloseMarginContractUnit();
    		
    		WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit2 =
    			new WEB3FuturesOptionsCloseMarginContractUnit();
    		
    		l_closeMarginContractUnit.id = "123";
    		l_closeMarginContractUnit1.id = "234";
    		l_closeMarginContractUnit2.id = "345";
    		
    		l_closeMarginContractUnit.settlePriority = "1";
    		l_closeMarginContractUnit.contractOrderQuantity = "123456789";
                 
    		l_closeMarginContractUnits[0] = l_closeMarginContractUnit;
    		l_closeMarginContractUnits[1] = l_closeMarginContractUnit1;
    		l_closeMarginContractUnits[2] = l_closeMarginContractUnit2;
    		
    		this.commonRequest();
    		l_request.closeMarginContractUnits = l_closeMarginContractUnits;
    		l_request.closingOrder = "0";
    		l_request.opOrderQuantity = "1";
        	l_request.validateATReserveOrder();
    		fail();
    	}
    	catch(WEB3BaseException l_ex)
    	{
    		log.debug(STR_METHOD_NAME, l_ex);
    		assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_03060);
    		log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
    	}
    	catch (Exception l_ex)
    	{
    		fail();
        log.error(ERROR + l_ex.getMessage(), l_ex);
    	}
    }
    
    /**
    validateATReserveOrder����ʉ߁B
   */
    public void test_validateAT_case0016()
    {
    	final String STR_METHOD_NAME = "test_validateAT_case0016()";
    	log.entering(TEST_START + STR_METHOD_NAME);
    
    	try
    	{
    		WEB3FuturesOptionsCloseMarginContractUnit[] l_closeMarginContractUnits =
    			new WEB3FuturesOptionsCloseMarginContractUnit[3];
        
    		WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit =
    			new WEB3FuturesOptionsCloseMarginContractUnit();
    		
    		WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit1 =
    			new WEB3FuturesOptionsCloseMarginContractUnit();
    		
    		WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit2 =
    			new WEB3FuturesOptionsCloseMarginContractUnit();
    		
    		l_closeMarginContractUnit.id = "123";
    		l_closeMarginContractUnit1.id = "234";
    		l_closeMarginContractUnit2.id = "345";
    		
    		l_closeMarginContractUnit.settlePriority = "1";
    		l_closeMarginContractUnit.contractOrderQuantity = "12345678";
    		
    		l_closeMarginContractUnit1.settlePriority = "1";
    		l_closeMarginContractUnit1.contractOrderQuantity = "1234567";
    		
    		l_closeMarginContractUnit2.settlePriority = "1";
    		l_closeMarginContractUnit2.contractOrderQuantity = "100007";
                 
    		l_closeMarginContractUnits[0] = l_closeMarginContractUnit;
    		l_closeMarginContractUnits[1] = l_closeMarginContractUnit1;
    		l_closeMarginContractUnits[2] = l_closeMarginContractUnit2;
    		
    		this.commonRequest();
    		l_request.closeMarginContractUnits = l_closeMarginContractUnits;
    		l_request.closingOrder = "0";
    		l_request.opOrderQuantity = "1";
        	l_request.validateATReserveOrder();    		
    	}
    	catch (Exception l_ex)
    	{
    		fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
    	}
    }
    
    private void commonRequest()
    {
        l_request.orderPriceDiv = "1";
        l_request.limitPrice = "5";
        l_request.execCondType = "1";
        l_request.expirationDateType = "2";
        l_request.expirationDate = WEB3DateUtility.getDate("20070828", "yyyyMMdd");
        l_request.orderCondType = "1";
        l_request.stopPremium_underlyingAssets = "0";
        l_request.stopOrderCondPrice = "5";
        l_request.stopOrderCondOperator = "1";
    }  

}
@
