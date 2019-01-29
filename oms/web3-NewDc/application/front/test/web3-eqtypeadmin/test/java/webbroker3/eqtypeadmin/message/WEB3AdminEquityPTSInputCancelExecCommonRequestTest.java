head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.53.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminEquityPTSInputCancelExecCommonRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE����(PTS)�o�����͎�����ʃ��N�G�X�gTest(WEB3AdminEquityPTSInputCancelExecCommonRequestTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/02/01 ��іQ(���u)
*/

package webbroker3.eqtypeadmin.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminEquityPTSInputCancelExecCommonRequestTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityPTSInputCancelExecCommonRequestTest.class);

    public WEB3AdminEquityPTSInputCancelExecCommonRequestTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    /**
     * this.��芔������ == null �̏ꍇ�A��O���X���[����B
     */
    public void testValidateCase0001()
    {
        final String STR_METHOD_NAME = "testValidateCase0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3AdminEquityPTSInputCancelExecCommonRequest l_request =
                new WEB3AdminEquityPTSInputCancelExecCommonRequest();
            l_request.execQuantity = null;
            
            l_request.validate();
            fail();
            
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02989);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }  
    }
    
    /**
     * this.��芔�����������l�ȊO�̏ꍇ�A��O���X���[����B
     */
    public void testValidateCase0002()
    {
        final String STR_METHOD_NAME = "testValidateCase0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3AdminEquityPTSInputCancelExecCommonRequest l_request =
                new WEB3AdminEquityPTSInputCancelExecCommonRequest();
            l_request.execQuantity = "abc";
            
            l_request.validate();
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02990);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }  
    }
    
    /**
     * this.��芔������ == 0 �̏ꍇ�A��O���X���[����B
     */
    public void testValidateCase0003()
    {
        final String STR_METHOD_NAME = "testValidateCase0003()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3AdminEquityPTSInputCancelExecCommonRequest l_request =
                new WEB3AdminEquityPTSInputCancelExecCommonRequest();
            l_request.execQuantity = "0";
            
            l_request.validate();
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02991);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }  
    }
    
    /**
     * this.��芔������ < 0 �̏ꍇ�A��O���X���[����B
     */
    public void testValidateCase0004()
    {
        final String STR_METHOD_NAME = "testValidateCase0004()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3AdminEquityPTSInputCancelExecCommonRequest l_request =
                new WEB3AdminEquityPTSInputCancelExecCommonRequest();
            l_request.execQuantity = "-1";
            
            l_request.validate();
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02991);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }  
    }
    
    /**
     * this.��芔�������̌��� �� 8�� �̏ꍇ�A��O���X���[����B
     */
    public void testValidateCase0005()
    {
        final String STR_METHOD_NAME = "testValidateCase0005()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3AdminEquityPTSInputCancelExecCommonRequest l_request =
                new WEB3AdminEquityPTSInputCancelExecCommonRequest();
            l_request.execQuantity = "123456789";
            
            l_request.validate();
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02992);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }  
    }
    
    /**
     * this.���P�� == null �̏ꍇ�A��O���X���[����B 
     */
    public void testValidateCase0006()
    {
        final String STR_METHOD_NAME = "testValidateCase0006()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3AdminEquityPTSInputCancelExecCommonRequest l_request =
                new WEB3AdminEquityPTSInputCancelExecCommonRequest();
            l_request.execQuantity = "12345678";
            l_request.execPrice = null;
            l_request.validate();
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02021);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }  
    }
    
    /**
     * this.���P�������l�ȊO�̏ꍇ�A��O���X���[����B 
     */
    public void testValidateCase0007()
    {
        final String STR_METHOD_NAME = "testValidateCase0007()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3AdminEquityPTSInputCancelExecCommonRequest l_request =
                new WEB3AdminEquityPTSInputCancelExecCommonRequest();
            l_request.execQuantity = "12345678";
            l_request.execPrice = "a";
            l_request.validate();
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02022);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }  
    }
    
    /**
     * this.���P�� == 0 �̏ꍇ�A��O���X���[����B 
     */
    public void testValidateCase0008()
    {
        final String STR_METHOD_NAME = "testValidateCase0008()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3AdminEquityPTSInputCancelExecCommonRequest l_request =
                new WEB3AdminEquityPTSInputCancelExecCommonRequest();
            l_request.execQuantity = "12345678";
            l_request.execPrice = "0";
            l_request.validate();
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02023);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }  
    }
    
    /**
     * this.���P�� < 0 �̏ꍇ�A��O���X���[����B
     */
    public void testValidateCase0009()
    {
        final String STR_METHOD_NAME = "testValidateCase0009()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3AdminEquityPTSInputCancelExecCommonRequest l_request =
                new WEB3AdminEquityPTSInputCancelExecCommonRequest();
            l_request.execQuantity = "12345678";
            l_request.execPrice = "-1";
            l_request.validate();
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02023);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }  
    }
    
    /**
     *this.���P���̌��� �� 8�� �̏ꍇ�A��O���X���[����B
     */
    public void testValidateCase0010()
    {
        final String STR_METHOD_NAME = "testValidateCase0010()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3AdminEquityPTSInputCancelExecCommonRequest l_request =
                new WEB3AdminEquityPTSInputCancelExecCommonRequest();
            l_request.execQuantity = "12345678";
            l_request.execPrice = "123456789";
            l_request.validate();
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02993);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }  
    }
    
    /**
     *this.��������null�̏ꍇ�A 
�@@  �u��������null�v�̗�O���X���[����B 
     */
    public void testValidateCase0011()
    {
        final String STR_METHOD_NAME = "testValidateCase0011()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3AdminEquityPTSInputCancelExecCommonRequest l_request =
                new WEB3AdminEquityPTSInputCancelExecCommonRequest();
            l_request.execQuantity = "12345678";
            l_request.execPrice = "12345678";
            l_request.executionTimeStamp = null;
            l_request.validate();
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02184);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }  
    }
    
    /**
    �@@this.����ID��null�̏ꍇ�A  
    �@@�u����ID��null�v�̗�O���X���[����B 
     */
    public void testValidateCase0012()
    {
        final String STR_METHOD_NAME = "testValidateCase0012()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3AdminEquityPTSInputCancelExecCommonRequest l_request =
                new WEB3AdminEquityPTSInputCancelExecCommonRequest();
            l_request.execQuantity = "12345678";
            l_request.execPrice = "12345678";
            l_request.executionTimeStamp = WEB3DateUtility.getDate("20080201", "yyyyMMdd");
            l_request.orderId = null;
            l_request.validate();
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00600);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }  
    }
    
    /**
   �@@���팋���B
    */
   public void testValidateCase0013()
   {
       final String STR_METHOD_NAME = "testValidateCase0013()";
       log.entering(STR_METHOD_NAME);
       
       try
       {
           WEB3AdminEquityPTSInputCancelExecCommonRequest l_request =
               new WEB3AdminEquityPTSInputCancelExecCommonRequest();
           l_request.execQuantity = "12345678";
           l_request.execPrice = "12345678";
           l_request.executionTimeStamp = WEB3DateUtility.getDate("20080201", "yyyyMMdd");
           l_request.orderId = "123";
           l_request.validate();
       }
       catch (Exception l_ex)
       {
           log.error(ERROR + l_ex.getMessage(), l_ex);
           fail();
       }
       log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
       log.exiting(TEST_END + STR_METHOD_NAME);
   }

}
@
