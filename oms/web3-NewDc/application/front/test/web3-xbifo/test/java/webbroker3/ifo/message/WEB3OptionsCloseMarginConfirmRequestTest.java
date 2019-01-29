head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.23.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3OptionsCloseMarginConfirmRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���I�v�V�����ԍϊm�F���N�G�X�gTEST(WEB3OptionsCloseMarginConfirmRequestTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/08/28 ��іQ(���u) �V�K�쐬
*/

package webbroker3.ifo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3OptionsCloseMarginConfirmRequestTest extends TestBaseForMock
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3OptionsCloseMarginConfirmRequestTest.class);

    WEB3OptionsCloseMarginConfirmRequest l_request = null;
    
    public WEB3OptionsCloseMarginConfirmRequestTest(String arg0)
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
    �@@this.���Ϗ�����null and 
    �@@this.���Ϗ������i�ȉ��̒l�j �̏ꍇ�A��O���X���[����B 
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
              new WEB3FuturesOptionsCloseMarginContractUnit[1];
          this.commonRequest();
          l_request.closeMarginContractUnits = l_closeMarginContractUnits;
          l_request.closingOrder = null;
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
                 new WEB3FuturesOptionsCloseMarginContractUnit[1];
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
     �ithis.���Ϗ���= 2�F�P�������h�j and this.��������=null �̏ꍇ
     */
     public void test_validate_case0008()
     {
         final String STR_METHOD_NAME = "test_validate_case0008()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3FuturesOptionsCloseMarginContractUnit[] l_closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[1];
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
            l_request.closingOrder = "3";
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
                new WEB3FuturesOptionsCloseMarginContractUnit[1];
            this.commonRequest();
            l_request.closeMarginContractUnits = l_closeMarginContractUnits;
            l_request.closingOrder = "3";
            l_request.opOrderQuantity = "a";
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
            l_request.closingOrder = "3";
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
     this.�������ʁ�null and this.��������< 0 �̏ꍇ�A 
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
            l_request.closingOrder = "3";
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
     this.���Ϗ���=�h0�F�����_���h �̏ꍇ�A�ԍό��ʂ̗v�f�������L�̃`�F�b�N���J��Ԃ��čs���B 
      �E�ԍό��ʂ�validate()�e�o��ُ�B�B 
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
            l_request.closingOrder = "0";
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
      this.���Ϗ���=�h0�F�����_���h and �i�ԍό���.���Ϗ���>0 and �ԍό���.����>0 �ƂȂ�g�ݍ��킹�����݂��Ȃ��j �ꍇ�A 
          ��O���X���[����
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
    this.���Ϗ���=null �̏ꍇ 
     �ԍό��ʂ̗v�f�������L�̃`�F�b�N���J��Ԃ��čs���B 
    �ԍό���.���Ϗ��ʁ�null �̏ꍇ�A 
      ��O���X���[����B 
  */
  public void test_validate_case0016()
  {
      final String STR_METHOD_NAME = "test_validate_case0016()";
     log.entering(TEST_START + STR_METHOD_NAME);
     
     try
     {
         WEB3FuturesOptionsCloseMarginContractUnit[] l_closeMarginContractUnits =
             new WEB3FuturesOptionsCloseMarginContractUnit[1];
         WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit =
             new WEB3FuturesOptionsCloseMarginContractUnit();
         
         
         l_closeMarginContractUnit.id = "123";
         l_closeMarginContractUnit.settlePriority = "1";
         l_closeMarginContractUnit.contractOrderQuantity = "1";
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
    this.���Ϗ���=�h1�F�P���v���h �̏ꍇ 
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
           l_closeMarginContractUnit.contractOrderQuantity = "1";
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
    this.���Ϗ���= 2�F�P�������h �̏ꍇ 
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
               new WEB3FuturesOptionsCloseMarginContractUnit[1];
           WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit =
               new WEB3FuturesOptionsCloseMarginContractUnit();

           l_closeMarginContractUnit.id = "123";
           l_closeMarginContractUnit.settlePriority = "1";
           l_closeMarginContractUnit.contractOrderQuantity = "1";
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
    this.���Ϗ���=�h3�F�������h �̏ꍇ 
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
           l_closeMarginContractUnit1.settlePriority = "2";
           l_closeMarginContractUnit2.settlePriority = "3";
           l_closeMarginContractUnit.contractOrderQuantity = "1";

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
    �ԍό��ʂ�validate()���\�b�h���Ăяo���B�e�o��ُ�B
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
    
    //�A�������Ή�
    //validateATReserveOrder
    //�X�[�p�[�N���X��validate���\�b�h���Ăяo��
    public void test_validateATReserveOrder_case1()
    {
        final String STR_METHOD_NAME = "test_validateATReserveOrder_case1()";
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
       catch (Exception l_ex)
       {
           fail();
           log.error(ERROR + l_ex.getMessage(), l_ex);
       }
    }
    
    //�jthis.�ԍό���=null �̏ꍇ
    public void test_validateATReserveOrder_case2()
    {
        final String STR_METHOD_NAME = "test_validateATReserveOrder_case2()";
       log.entering(TEST_START + STR_METHOD_NAME);
       
       try
       {
           //��������
           l_request.opOrderQuantity = "1";
           
           //�����P���敪
           l_request.orderPriceDiv = "1";

           //�����P��
           l_request.limitPrice = "111";

           //���s����
           l_request.execCondType = "1";

           //���������敪
           l_request.expirationDateType ="3";

           //�����L������
           l_request.expirationDate = null;

           //���������敪
           l_request.orderCondType = "2";

           //�t�w�l�p�v���~�A���^�����Y���i
           l_request.stopPremium_underlyingAssets = null;

           //�t�w�l�p���������P��
           l_request.stopOrderCondPrice = null;

           //�t�w�l�p�����������Z�q
           l_request.stopOrderCondOperator = null;

           //�v�w�l�p�v���~�A���^�����Y���i
           l_request.wlimitPremium_underlyingAssets = "1";

           //�v�w�l�p���������P��
           l_request.wlimitOrderCondPrice = "2";

           //�v�w�l�p�����������Z�q
           l_request.wlimitOrderCondOperator = "1";

           //�v�w�l�p�����P���敪
           l_request.wLimitOrderPriceDiv = "1";

           //�v�w�l�p�����P��
           l_request.wLimitPrice = "1";
           
           //W�w�l�p���s����
           l_request.wlimitExecCondType = "1";
           
           //W�w�l�p�L����ԋ敪
           l_request.wlimitEnableStatusDiv = "1";
           
           l_request.validateATReserveOrder();
           fail();
       }
       catch(WEB3BaseException l_ex)
       {
           log.debug(STR_METHOD_NAME, l_ex);
           assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00178);
           log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
       }
       catch (Exception l_ex)
       {
           fail();
           log.error(ERROR + l_ex.getMessage(), l_ex);
       }
    }
    
    //this.�ԍό��ʂ̗v�f��=0 �̏ꍇ
    public void test_validateATReserveOrder_case3()
    {
        final String STR_METHOD_NAME = "test_validateATReserveOrder_case3()";
       log.entering(TEST_START + STR_METHOD_NAME);
       
       try
       {
           //��������
           l_request.opOrderQuantity = "1";
           
           //�����P���敪
           l_request.orderPriceDiv = "1";

           //�����P��
           l_request.limitPrice = "111";

           //���s����
           l_request.execCondType = "1";

           //���������敪
           l_request.expirationDateType ="3";

           //�����L������
           l_request.expirationDate = null;

           //���������敪
           l_request.orderCondType = "2";

           //�t�w�l�p�v���~�A���^�����Y���i
           l_request.stopPremium_underlyingAssets = null;

           //�t�w�l�p���������P��
           l_request.stopOrderCondPrice = null;

           //�t�w�l�p�����������Z�q
           l_request.stopOrderCondOperator = null;

           //�v�w�l�p�v���~�A���^�����Y���i
           l_request.wlimitPremium_underlyingAssets = "1";

           //�v�w�l�p���������P��
           l_request.wlimitOrderCondPrice = "2";

           //�v�w�l�p�����������Z�q
           l_request.wlimitOrderCondOperator = "1";

           //�v�w�l�p�����P���敪
           l_request.wLimitOrderPriceDiv = "1";

           //�v�w�l�p�����P��
           l_request.wLimitPrice = "1";
           
           //W�w�l�p���s����
           l_request.wlimitExecCondType = "1";
           
           //W�w�l�p�L����ԋ敪
           l_request.wlimitEnableStatusDiv = "1";
           
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
       catch (Exception l_ex)
       {
           fail();
           log.error(ERROR + l_ex.getMessage(), l_ex);
       }
    }
    
    //���Ϗ����`�F�b�N 
    //�@@this.���Ϗ�����null and 
    //�@@this.���Ϗ������i�ȉ��̒l�j �̏ꍇ�A��O���X���[����B 
    //�@@�@@�@@�@@�E�h0�F�����_���h 
    //�@@�@@�@@�@@�E�h1�F�P���v���h 
    //�@@�@@�@@�@@�E�h2�F�P�������h 
    //�@@�@@�@@�@@�E�h3�F�������h
    public void test_validateATReserveOrder_case4()
    {
        final String STR_METHOD_NAME = "test_validateATReserveOrder_case4()";
       log.entering(TEST_START + STR_METHOD_NAME);
       
       try
       {
           //��������
           l_request.opOrderQuantity = "1";
           
           //�����P���敪
           l_request.orderPriceDiv = "1";

           //�����P��
           l_request.limitPrice = "111";

           //���s����
           l_request.execCondType = "1";

           //���������敪
           l_request.expirationDateType ="3";

           //�����L������
           l_request.expirationDate = null;

           //���������敪
           l_request.orderCondType = "2";

           //�t�w�l�p�v���~�A���^�����Y���i
           l_request.stopPremium_underlyingAssets = null;

           //�t�w�l�p���������P��
           l_request.stopOrderCondPrice = null;

           //�t�w�l�p�����������Z�q
           l_request.stopOrderCondOperator = null;

           //�v�w�l�p�v���~�A���^�����Y���i
           l_request.wlimitPremium_underlyingAssets = "1";

           //�v�w�l�p���������P��
           l_request.wlimitOrderCondPrice = "2";

           //�v�w�l�p�����������Z�q
           l_request.wlimitOrderCondOperator = "1";

           //�v�w�l�p�����P���敪
           l_request.wLimitOrderPriceDiv = "1";

           //�v�w�l�p�����P��
           l_request.wLimitPrice = "1";
           
           //W�w�l�p���s����
           l_request.wlimitExecCondType = "1";
           
           //W�w�l�p�L����ԋ敪
           l_request.wlimitEnableStatusDiv = "1";
           
           l_request.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[2];
           l_request.closingOrder = "4";
           l_request.validateATReserveOrder();
           fail();
       }
       catch(WEB3BaseException l_ex)
       {
           log.debug(STR_METHOD_NAME, l_ex);
           assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00179);
           log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
       }
       catch (Exception l_ex)
       {
           fail();
           log.error(ERROR + l_ex.getMessage(), l_ex);
       }
    }
    
    //�S�j�@@�������ʃ`�F�b�N 
    //�@@�S�|�P�jthis.���Ϗ���=�inull or �h1�F�P���v���h or �h2�F�P�������h or �h3�F�������h�j and 
    //          this.��������=null �̏ꍇ
    public void test_validateATReserveOrder_case5()
    {
        final String STR_METHOD_NAME = "test_validateATReserveOrder_case5()";
       log.entering(TEST_START + STR_METHOD_NAME);
       
       try
       {
           
           //�����P���敪
           l_request.orderPriceDiv = "1";

           //�����P��
           l_request.limitPrice = "111";

           //���s����
           l_request.execCondType = "1";

           //���������敪
           l_request.expirationDateType ="3";

           //�����L������
           l_request.expirationDate = null;

           //���������敪
           l_request.orderCondType = "2";

           //�t�w�l�p�v���~�A���^�����Y���i
           l_request.stopPremium_underlyingAssets = null;

           //�t�w�l�p���������P��
           l_request.stopOrderCondPrice = null;

           //�t�w�l�p�����������Z�q
           l_request.stopOrderCondOperator = null;

           //�v�w�l�p�v���~�A���^�����Y���i
           l_request.wlimitPremium_underlyingAssets = "1";

           //�v�w�l�p���������P��
           l_request.wlimitOrderCondPrice = "2";

           //�v�w�l�p�����������Z�q
           l_request.wlimitOrderCondOperator = "1";

           //�v�w�l�p�����P���敪
           l_request.wLimitOrderPriceDiv = "1";

           //�v�w�l�p�����P��
           l_request.wLimitPrice = "1";
           
           //W�w�l�p���s����
           l_request.wlimitExecCondType = "1";
           
           //W�w�l�p�L����ԋ敪
           l_request.wlimitEnableStatusDiv = "1";
           
           l_request.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[2];
           l_request.closingOrder = "1";
           l_request.validateATReserveOrder();
           fail();
       }
       catch(WEB3BaseException l_ex)
       {
           log.debug(STR_METHOD_NAME, l_ex);
           assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00245);
           log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
       }
       catch (Exception l_ex)
       {
           fail();
           log.error(ERROR + l_ex.getMessage(), l_ex);
       }
    }
    
    //�S�|�Q�jthis.�������ʁ�null and this.�������ʁ����� �̏ꍇ
    public void test_validateATReserveOrder_case6()
    {
        final String STR_METHOD_NAME = "test_validateATReserveOrder_case6()";
       log.entering(TEST_START + STR_METHOD_NAME);
       
       try
       {
           
           //�����P���敪
           l_request.orderPriceDiv = "1";

           //�����P��
           l_request.limitPrice = "111";

           //���s����
           l_request.execCondType = "1";

           //���������敪
           l_request.expirationDateType ="3";

           //�����L������
           l_request.expirationDate = null;

           //���������敪
           l_request.orderCondType = "2";

           //�t�w�l�p�v���~�A���^�����Y���i
           l_request.stopPremium_underlyingAssets = null;

           //�t�w�l�p���������P��
           l_request.stopOrderCondPrice = null;

           //�t�w�l�p�����������Z�q
           l_request.stopOrderCondOperator = null;

           //�v�w�l�p�v���~�A���^�����Y���i
           l_request.wlimitPremium_underlyingAssets = "1";

           //�v�w�l�p���������P��
           l_request.wlimitOrderCondPrice = "2";

           //�v�w�l�p�����������Z�q
           l_request.wlimitOrderCondOperator = "1";

           //�v�w�l�p�����P���敪
           l_request.wLimitOrderPriceDiv = "1";

           //�v�w�l�p�����P��
           l_request.wLimitPrice = "1";
           
           //W�w�l�p���s����
           l_request.wlimitExecCondType = "1";
           
           //W�w�l�p�L����ԋ敪
           l_request.wlimitEnableStatusDiv = "1";
           
           l_request.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[2];
           l_request.closingOrder = "1";
           l_request.opOrderQuantity = "a";
           l_request.validateATReserveOrder();
           fail();
       }
       catch(WEB3BaseException l_ex)
       {
           log.debug(STR_METHOD_NAME, l_ex);
           assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00075);
           log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
       }
       catch (Exception l_ex)
       {
           fail();
           log.error(ERROR + l_ex.getMessage(), l_ex);
       }
    }
    
    //this.�������ʁ�null and this.�������ʁ�0 �̏ꍇ
    public void test_validateATReserveOrder_case7()
    {
        final String STR_METHOD_NAME = "test_validateATReserveOrder_case7()";
       log.entering(TEST_START + STR_METHOD_NAME);
       
       try
       {
           
           //�����P���敪
           l_request.orderPriceDiv = "1";

           //�����P��
           l_request.limitPrice = "111";

           //���s����
           l_request.execCondType = "1";

           //���������敪
           l_request.expirationDateType ="3";

           //�����L������
           l_request.expirationDate = null;

           //���������敪
           l_request.orderCondType = "2";

           //�t�w�l�p�v���~�A���^�����Y���i
           l_request.stopPremium_underlyingAssets = null;

           //�t�w�l�p���������P��
           l_request.stopOrderCondPrice = null;

           //�t�w�l�p�����������Z�q
           l_request.stopOrderCondOperator = null;

           //�v�w�l�p�v���~�A���^�����Y���i
           l_request.wlimitPremium_underlyingAssets = "1";

           //�v�w�l�p���������P��
           l_request.wlimitOrderCondPrice = "2";

           //�v�w�l�p�����������Z�q
           l_request.wlimitOrderCondOperator = "1";

           //�v�w�l�p�����P���敪
           l_request.wLimitOrderPriceDiv = "1";

           //�v�w�l�p�����P��
           l_request.wLimitPrice = "1";
           
           //W�w�l�p���s����
           l_request.wlimitExecCondType = "1";
           
           //W�w�l�p�L����ԋ敪
           l_request.wlimitEnableStatusDiv = "1";
           
           l_request.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[2];
           l_request.closingOrder = "1";
           l_request.opOrderQuantity = "0";
           l_request.validateATReserveOrder();
           fail();
       }
       catch(WEB3BaseException l_ex)
       {
           log.debug(STR_METHOD_NAME, l_ex);
           assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00076);
           log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
       }
       catch (Exception l_ex)
       {
           fail();
           log.error(ERROR + l_ex.getMessage(), l_ex);
       }
    }
    
    //�T�j�@@�ԍό��ʂ̒������ʃ`�F�b�N 
    //�@@�T�|�P�j���Ϗ������h0�F�����_���h�̏ꍇ�̂݁A 
    //�@@�@@�@@�@@�ԍό��ʂ̗v�f���� 
    //�@@�@@�@@�@@���L�̃`�F�b�N���J��Ԃ��čs���B 
    //�@@�@@�@@�@@�����Ύ���̏ꍇ�́A���ۂɂ͂P���ׂ݂̂��ݒ肳��Ă���B 
    //�@@�@@�@@�@@���܂��A�����_���ȊO�̏ꍇ�́A���N�G�X�g.�������ʂ��g�p����̂ŁA 
    //�@@�@@�@@�@@���`�F�b�N�s�v�B 
    //�@@�@@�@@�@@�E�ԍό���.�������� ���ȉ��̂����ꂩ�̏ꍇ�́A 
    //�@@�@@�@@�@@�@@�u�ԍό��ʂ̒������ʎw�肪�s���v�̗�O���X���[����B 
    //�@@�@@�@@�@@�@@�@@�Enull  
    public void test_validateATReserveOrder_case8()
    {
        final String STR_METHOD_NAME = "test_validateATReserveOrder_case8()";
       log.entering(TEST_START + STR_METHOD_NAME);
       
       try
       {
           
           //�����P���敪
           l_request.orderPriceDiv = "1";

           //�����P��
           l_request.limitPrice = "111";

           //���s����
           l_request.execCondType = "1";

           //���������敪
           l_request.expirationDateType ="3";

           //�����L������
           l_request.expirationDate = null;

           //���������敪
           l_request.orderCondType = "2";

           //�t�w�l�p�v���~�A���^�����Y���i
           l_request.stopPremium_underlyingAssets = null;

           //�t�w�l�p���������P��
           l_request.stopOrderCondPrice = null;

           //�t�w�l�p�����������Z�q
           l_request.stopOrderCondOperator = null;

           //�v�w�l�p�v���~�A���^�����Y���i
           l_request.wlimitPremium_underlyingAssets = "1";

           //�v�w�l�p���������P��
           l_request.wlimitOrderCondPrice = "2";

           //�v�w�l�p�����������Z�q
           l_request.wlimitOrderCondOperator = "1";

           //�v�w�l�p�����P���敪
           l_request.wLimitOrderPriceDiv = "1";

           //�v�w�l�p�����P��
           l_request.wLimitPrice = "1";
           
           //W�w�l�p���s����
           l_request.wlimitExecCondType = "1";
           
           //W�w�l�p�L����ԋ敪
           l_request.wlimitEnableStatusDiv = "1";
           
           l_request.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[2];
           WEB3FuturesOptionsCloseMarginContractUnit l_unit1 = new WEB3FuturesOptionsCloseMarginContractUnit();
           WEB3FuturesOptionsCloseMarginContractUnit l_unit2 = new WEB3FuturesOptionsCloseMarginContractUnit();
           l_request.closeMarginContractUnits[0] = l_unit1;
           l_request.closeMarginContractUnits[1] = l_unit2;
           l_request.closingOrder = "0";
           l_request.opOrderQuantity = "100";
           l_unit1.contractOrderQuantity = "10";
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
           log.error(ERROR + l_ex.getMessage(), l_ex);
           fail();
       }
    }
    
    //�T�j�@@�ԍό��ʂ̒������ʃ`�F�b�N 
    //�@@�T�|�P�j���Ϗ������h0�F�����_���h�̏ꍇ�̂݁A 
    //�@@�@@�@@�@@�ԍό��ʂ̗v�f���� 
    //�@@�@@�@@�@@���L�̃`�F�b�N���J��Ԃ��čs���B 
    //�@@�@@�@@�@@�����Ύ���̏ꍇ�́A���ۂɂ͂P���ׂ݂̂��ݒ肳��Ă���B 
    //�@@�@@�@@�@@���܂��A�����_���ȊO�̏ꍇ�́A���N�G�X�g.�������ʂ��g�p����̂ŁA 
    //�@@�@@�@@�@@���`�F�b�N�s�v�B 
    //�@@�@@�@@�@@�E�ԍό���.�������� ���ȉ��̂����ꂩ�̏ꍇ�́A 
    //�@@�@@�@@�@@�@@�u�ԍό��ʂ̒������ʎw�肪�s���v�̗�O���X���[����B 
    //�@@�@@�@@�@@�@@�@@�E�����ȊO  
    public void test_validateATReserveOrder_case9()
    {
        final String STR_METHOD_NAME = "test_validateATReserveOrder_case9()";
       log.entering(TEST_START + STR_METHOD_NAME);
       
       try
       {
           
           //�����P���敪
           l_request.orderPriceDiv = "1";

           //�����P��
           l_request.limitPrice = "111";

           //���s����
           l_request.execCondType = "1";

           //���������敪
           l_request.expirationDateType ="3";

           //�����L������
           l_request.expirationDate = null;

           //���������敪
           l_request.orderCondType = "2";

           //�t�w�l�p�v���~�A���^�����Y���i
           l_request.stopPremium_underlyingAssets = null;

           //�t�w�l�p���������P��
           l_request.stopOrderCondPrice = null;

           //�t�w�l�p�����������Z�q
           l_request.stopOrderCondOperator = null;

           //�v�w�l�p�v���~�A���^�����Y���i
           l_request.wlimitPremium_underlyingAssets = "1";

           //�v�w�l�p���������P��
           l_request.wlimitOrderCondPrice = "2";

           //�v�w�l�p�����������Z�q
           l_request.wlimitOrderCondOperator = "1";

           //�v�w�l�p�����P���敪
           l_request.wLimitOrderPriceDiv = "1";

           //�v�w�l�p�����P��
           l_request.wLimitPrice = "1";
           
           //W�w�l�p���s����
           l_request.wlimitExecCondType = "1";
           
           //W�w�l�p�L����ԋ敪
           l_request.wlimitEnableStatusDiv = "1";
           
           l_request.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[1];
           WEB3FuturesOptionsCloseMarginContractUnit l_unit1 = new WEB3FuturesOptionsCloseMarginContractUnit();
           l_request.closeMarginContractUnits[0] = l_unit1;
           l_request.closingOrder = "0";
           l_request.opOrderQuantity = "100";
           l_unit1.contractOrderQuantity = "a";
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
           log.error(ERROR + l_ex.getMessage(), l_ex);
           fail();
       }
    }
    
    //�T�j�@@�ԍό��ʂ̒������ʃ`�F�b�N 
    //�@@�T�|�P�j���Ϗ������h0�F�����_���h�̏ꍇ�̂݁A 
    //�@@�@@�@@�@@�ԍό��ʂ̗v�f���� 
    //�@@�@@�@@�@@���L�̃`�F�b�N���J��Ԃ��čs���B 
    //�@@�@@�@@�@@�����Ύ���̏ꍇ�́A���ۂɂ͂P���ׂ݂̂��ݒ肳��Ă���B 
    //�@@�@@�@@�@@���܂��A�����_���ȊO�̏ꍇ�́A���N�G�X�g.�������ʂ��g�p����̂ŁA 
    //�@@�@@�@@�@@���`�F�b�N�s�v�B 
    //�@@�@@�@@�@@�E�ԍό���.�������� ���ȉ��̂����ꂩ�̏ꍇ�́A 
    //�@@�@@�@@�@@�@@�u�ԍό��ʂ̒������ʎw�肪�s���v�̗�O���X���[����B 
    //�@@�@@�@@�@@�@@�@@�E�O�ȉ��̐���  
    public void test_validateATReserveOrder_case10()
    {
        final String STR_METHOD_NAME = "test_validateATReserveOrder_case10()";
       log.entering(TEST_START + STR_METHOD_NAME);
       
       try
       {
           
           //�����P���敪
           l_request.orderPriceDiv = "1";

           //�����P��
           l_request.limitPrice = "111";

           //���s����
           l_request.execCondType = "1";

           //���������敪
           l_request.expirationDateType ="3";

           //�����L������
           l_request.expirationDate = null;

           //���������敪
           l_request.orderCondType = "2";

           //�t�w�l�p�v���~�A���^�����Y���i
           l_request.stopPremium_underlyingAssets = null;

           //�t�w�l�p���������P��
           l_request.stopOrderCondPrice = null;

           //�t�w�l�p�����������Z�q
           l_request.stopOrderCondOperator = null;

           //�v�w�l�p�v���~�A���^�����Y���i
           l_request.wlimitPremium_underlyingAssets = "1";

           //�v�w�l�p���������P��
           l_request.wlimitOrderCondPrice = "2";

           //�v�w�l�p�����������Z�q
           l_request.wlimitOrderCondOperator = "1";

           //�v�w�l�p�����P���敪
           l_request.wLimitOrderPriceDiv = "1";

           //�v�w�l�p�����P��
           l_request.wLimitPrice = "1";
           
           //W�w�l�p���s����
           l_request.wlimitExecCondType = "1";
           
           //W�w�l�p�L����ԋ敪
           l_request.wlimitEnableStatusDiv = "1";
           
           l_request.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[1];
           WEB3FuturesOptionsCloseMarginContractUnit l_unit1 = new WEB3FuturesOptionsCloseMarginContractUnit();
           l_request.closeMarginContractUnits[0] = l_unit1;
           l_request.closingOrder = "0";
           l_request.opOrderQuantity = "100";
           l_unit1.contractOrderQuantity = "0";
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
           log.error(ERROR + l_ex.getMessage(), l_ex);
           fail();
       }
    }
    
    //�T�j�@@�ԍό��ʂ̒������ʃ`�F�b�N 
    //�@@�T�|�P�j���Ϗ������h0�F�����_���h�̏ꍇ�̂݁A 
    //�@@�@@�@@�@@�ԍό��ʂ̗v�f���� 
    //�@@�@@�@@�@@���L�̃`�F�b�N���J��Ԃ��čs���B 
    //�@@�@@�@@�@@�����Ύ���̏ꍇ�́A���ۂɂ͂P���ׂ݂̂��ݒ肳��Ă���B 
    //�@@�@@�@@�@@���܂��A�����_���ȊO�̏ꍇ�́A���N�G�X�g.�������ʂ��g�p����̂ŁA 
    //�@@�@@�@@�@@���`�F�b�N�s�v�B 
    //�@@�@@�@@�@@�E�ԍό���.�������� ���ȉ��̂����ꂩ�̏ꍇ�́A 
    //�@@�@@�@@�@@�@@�u�ԍό��ʂ̒������ʎw�肪�s���v�̗�O���X���[����B 
    //�@@�@@�@@�@@�@@�@@�E�W���𒴂��鐔��
    public void test_validateATReserveOrder_case11()
    {
        final String STR_METHOD_NAME = "test_validateATReserveOrder_case11()";
       log.entering(TEST_START + STR_METHOD_NAME);
       
       try
       {
           
           //�����P���敪
           l_request.orderPriceDiv = "1";

           //�����P��
           l_request.limitPrice = "111";

           //���s����
           l_request.execCondType = "1";

           //���������敪
           l_request.expirationDateType ="3";

           //�����L������
           l_request.expirationDate = null;

           //���������敪
           l_request.orderCondType = "2";

           //�t�w�l�p�v���~�A���^�����Y���i
           l_request.stopPremium_underlyingAssets = null;

           //�t�w�l�p���������P��
           l_request.stopOrderCondPrice = null;

           //�t�w�l�p�����������Z�q
           l_request.stopOrderCondOperator = null;

           //�v�w�l�p�v���~�A���^�����Y���i
           l_request.wlimitPremium_underlyingAssets = "1";

           //�v�w�l�p���������P��
           l_request.wlimitOrderCondPrice = "2";

           //�v�w�l�p�����������Z�q
           l_request.wlimitOrderCondOperator = "1";

           //�v�w�l�p�����P���敪
           l_request.wLimitOrderPriceDiv = "1";

           //�v�w�l�p�����P��
           l_request.wLimitPrice = "1";
           
           //W�w�l�p���s����
           l_request.wlimitExecCondType = "1";
           
           //W�w�l�p�L����ԋ敪
           l_request.wlimitEnableStatusDiv = "1";
           
           l_request.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[1];
           WEB3FuturesOptionsCloseMarginContractUnit l_unit1 = new WEB3FuturesOptionsCloseMarginContractUnit();
           l_request.closeMarginContractUnits[0] = l_unit1;
           l_request.closingOrder = "0";
           l_request.opOrderQuantity = "100";
           l_unit1.contractOrderQuantity = "123456780";
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
           log.error(ERROR + l_ex.getMessage(), l_ex);
           fail();
       }
    }
    
    //�X�[�p�[�N���X��validate���\�b�h���Ăяo��
    //�Q�j�@@�ԍό��ʃ`�F�b�N 
    //�R�j�@@���Ϗ����`�F�b�N 
    //�S�j�@@�������ʃ`�F�b�N 
    //�T�j�@@�ԍό��ʂ̒������ʃ`�F�b�N 
    public void test_validateATReserveOrder_case12()
    {
        final String STR_METHOD_NAME = "test_validateATReserveOrder_case12()";
       log.entering(TEST_START + STR_METHOD_NAME);
       
       try
       {
           
           //�����P���敪
           l_request.orderPriceDiv = "1";

           //�����P��
           l_request.limitPrice = "111";

           //���s����
           l_request.execCondType = "1";

           //���������敪
           l_request.expirationDateType ="3";

           //�����L������
           l_request.expirationDate = null;

           //���������敪
           l_request.orderCondType = "2";

           //�t�w�l�p�v���~�A���^�����Y���i
           l_request.stopPremium_underlyingAssets = null;

           //�t�w�l�p���������P��
           l_request.stopOrderCondPrice = null;

           //�t�w�l�p�����������Z�q
           l_request.stopOrderCondOperator = null;

           //�v�w�l�p�v���~�A���^�����Y���i
           l_request.wlimitPremium_underlyingAssets = "1";

           //�v�w�l�p���������P��
           l_request.wlimitOrderCondPrice = "2";

           //�v�w�l�p�����������Z�q
           l_request.wlimitOrderCondOperator = "1";

           //�v�w�l�p�����P���敪
           l_request.wLimitOrderPriceDiv = "1";

           //�v�w�l�p�����P��
           l_request.wLimitPrice = "1";
           
           //W�w�l�p���s����
           l_request.wlimitExecCondType = "1";
           
           //W�w�l�p�L����ԋ敪
           l_request.wlimitEnableStatusDiv = "1";
           
           l_request.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[1];
           WEB3FuturesOptionsCloseMarginContractUnit l_unit1 = new WEB3FuturesOptionsCloseMarginContractUnit();
           l_request.closeMarginContractUnits[0] = l_unit1;
           l_request.closingOrder = "0";
           l_request.opOrderQuantity = "100";
           l_unit1.contractOrderQuantity = "1234567";
           l_request.validateATReserveOrder();
       }
       catch (Exception l_ex)
       {
           log.error(ERROR + l_ex.getMessage(), l_ex);
           fail();
       }
    }
}
@
