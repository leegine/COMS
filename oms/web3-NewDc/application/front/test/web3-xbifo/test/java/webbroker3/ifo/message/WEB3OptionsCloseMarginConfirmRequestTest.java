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
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数オプション返済確認リクエストTEST(WEB3OptionsCloseMarginConfirmRequestTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/08/28 趙林鵬(中訊) 新規作成
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
     * ログ出力ユーティリティ。
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
     * スーパークラスのvalidateメソッドを呼び出す
     * validate抛出一個異常
     * 株価指数オプション共通入力リクエス.注文単価区分= null.
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
     * this.返済建玉=null の場合
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
     * this.返済建玉の要素数=0 の場合、 
     * 例外をスローする。
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
    　@this.決済順序≠null and 
    　@this.決済順序≠（以下の値） の場合、例外をスローする。 
    ・”0：ランダム” 
    ・”1：単価益順” 
    ・”2：単価損順” 
    ・”3：建日順”
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
    一括返済(this.返済建玉の要素数>1)　@and　@ 
    　@this.決済順序==nullの場合、例外「一括返済時、決済順序は指定してください。」をスローする。
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
   （this.決済順序=null ） and this.注文数量=null の場合
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
      （this.決済順序= ”1：単価益順” ） and this.注文数量=null の場合
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
     （this.決済順序= 2：単価損順”） and this.注文数量=null の場合
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
     （this.決済順序= ”3：建日順”） and this.注文数量=null の場合
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
     this.注文数量≠null and this.注文数量≠数字 の場合、 
     例外をスローする。 
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
     this.注文数量≠null and this.注文数量=0 の場合、 
     例外をスローする。 
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
     this.注文数量≠null and this.注文数量< 0 の場合、 
     例外をスローする。 
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
     this.決済順序=”0：ランダム” の場合、返済建玉の要素数分下記のチェックを繰り返して行う。 
      ・返済建玉のvalidate()抛出一個異常。。 
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
      this.決済順序=”0：ランダム” and 要素内のすべての決済順位=null or 0 の場合、 
          例外をスローする。
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
      this.決済順序=”0：ランダム” and （返済建玉.決済順位>0 and 返済建玉.数量>0 となる組み合わせが存在しない） 場合、 
          例外をスローする
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
    this.決済順序=null の場合 
     返済建玉の要素数分下記のチェックを繰り返して行う。 
    返済建玉.決済順位≠null の場合、 
      例外をスローする。 
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
    this.決済順序=”1：単価益順” の場合 
     返済建玉の要素数分下記のチェックを繰り返して行う。 
    返済建玉.決済順位≠null の場合、 
      例外をスローする。 
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
    this.決済順序= 2：単価損順” の場合 
     返済建玉の要素数分下記のチェックを繰り返して行う。 
    返済建玉.決済順位≠null の場合、 
      例外をスローする。 
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
    this.決済順序=”3：建日順” の場合 
     返済建玉の要素数分下記のチェックを繰り返して行う。 
    返済建玉.決済順位≠null の場合、 
      例外をスローする。 
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
    this.決済順序=（null or ”1：単価益順” or ”2：単価損順” or ”3：建日順”） の場合 
    返済建玉の要素数分下記のチェックを繰り返して行う。 
    返済建玉のvalidate()メソッドを呼び出す。抛出一個異常。
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
    validate正常通過。
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
    
    //連続注文対応
    //validateATReserveOrder
    //スーパークラスのvalidateメソッドを呼び出す
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
    
    //）this.返済建玉=null の場合
    public void test_validateATReserveOrder_case2()
    {
        final String STR_METHOD_NAME = "test_validateATReserveOrder_case2()";
       log.entering(TEST_START + STR_METHOD_NAME);
       
       try
       {
           //注文数量
           l_request.opOrderQuantity = "1";
           
           //注文単価区分
           l_request.orderPriceDiv = "1";

           //注文単価
           l_request.limitPrice = "111";

           //執行条件
           l_request.execCondType = "1";

           //注文期限区分
           l_request.expirationDateType ="3";

           //注文有効期限
           l_request.expirationDate = null;

           //発注条件区分
           l_request.orderCondType = "2";

           //逆指値用プレミアム／原資産価格
           l_request.stopPremium_underlyingAssets = null;

           //逆指値用発注条件単価
           l_request.stopOrderCondPrice = null;

           //逆指値用発注条件演算子
           l_request.stopOrderCondOperator = null;

           //Ｗ指値用プレミアム／原資産価格
           l_request.wlimitPremium_underlyingAssets = "1";

           //Ｗ指値用発注条件単価
           l_request.wlimitOrderCondPrice = "2";

           //Ｗ指値用発注条件演算子
           l_request.wlimitOrderCondOperator = "1";

           //Ｗ指値用注文単価区分
           l_request.wLimitOrderPriceDiv = "1";

           //Ｗ指値用注文単価
           l_request.wLimitPrice = "1";
           
           //W指値用執行条件
           l_request.wlimitExecCondType = "1";
           
           //W指値用有効状態区分
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
    
    //this.返済建玉の要素数=0 の場合
    public void test_validateATReserveOrder_case3()
    {
        final String STR_METHOD_NAME = "test_validateATReserveOrder_case3()";
       log.entering(TEST_START + STR_METHOD_NAME);
       
       try
       {
           //注文数量
           l_request.opOrderQuantity = "1";
           
           //注文単価区分
           l_request.orderPriceDiv = "1";

           //注文単価
           l_request.limitPrice = "111";

           //執行条件
           l_request.execCondType = "1";

           //注文期限区分
           l_request.expirationDateType ="3";

           //注文有効期限
           l_request.expirationDate = null;

           //発注条件区分
           l_request.orderCondType = "2";

           //逆指値用プレミアム／原資産価格
           l_request.stopPremium_underlyingAssets = null;

           //逆指値用発注条件単価
           l_request.stopOrderCondPrice = null;

           //逆指値用発注条件演算子
           l_request.stopOrderCondOperator = null;

           //Ｗ指値用プレミアム／原資産価格
           l_request.wlimitPremium_underlyingAssets = "1";

           //Ｗ指値用発注条件単価
           l_request.wlimitOrderCondPrice = "2";

           //Ｗ指値用発注条件演算子
           l_request.wlimitOrderCondOperator = "1";

           //Ｗ指値用注文単価区分
           l_request.wLimitOrderPriceDiv = "1";

           //Ｗ指値用注文単価
           l_request.wLimitPrice = "1";
           
           //W指値用執行条件
           l_request.wlimitExecCondType = "1";
           
           //W指値用有効状態区分
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
    
    //決済順序チェック 
    //　@this.決済順序≠null and 
    //　@this.決済順序≠（以下の値） の場合、例外をスローする。 
    //　@　@　@　@・”0：ランダム” 
    //　@　@　@　@・”1：単価益順” 
    //　@　@　@　@・”2：単価損順” 
    //　@　@　@　@・”3：建日順”
    public void test_validateATReserveOrder_case4()
    {
        final String STR_METHOD_NAME = "test_validateATReserveOrder_case4()";
       log.entering(TEST_START + STR_METHOD_NAME);
       
       try
       {
           //注文数量
           l_request.opOrderQuantity = "1";
           
           //注文単価区分
           l_request.orderPriceDiv = "1";

           //注文単価
           l_request.limitPrice = "111";

           //執行条件
           l_request.execCondType = "1";

           //注文期限区分
           l_request.expirationDateType ="3";

           //注文有効期限
           l_request.expirationDate = null;

           //発注条件区分
           l_request.orderCondType = "2";

           //逆指値用プレミアム／原資産価格
           l_request.stopPremium_underlyingAssets = null;

           //逆指値用発注条件単価
           l_request.stopOrderCondPrice = null;

           //逆指値用発注条件演算子
           l_request.stopOrderCondOperator = null;

           //Ｗ指値用プレミアム／原資産価格
           l_request.wlimitPremium_underlyingAssets = "1";

           //Ｗ指値用発注条件単価
           l_request.wlimitOrderCondPrice = "2";

           //Ｗ指値用発注条件演算子
           l_request.wlimitOrderCondOperator = "1";

           //Ｗ指値用注文単価区分
           l_request.wLimitOrderPriceDiv = "1";

           //Ｗ指値用注文単価
           l_request.wLimitPrice = "1";
           
           //W指値用執行条件
           l_request.wlimitExecCondType = "1";
           
           //W指値用有効状態区分
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
    
    //４）　@注文数量チェック 
    //　@４－１）this.決済順序=（null or ”1：単価益順” or ”2：単価損順” or ”3：建日順”） and 
    //          this.注文数量=null の場合
    public void test_validateATReserveOrder_case5()
    {
        final String STR_METHOD_NAME = "test_validateATReserveOrder_case5()";
       log.entering(TEST_START + STR_METHOD_NAME);
       
       try
       {
           
           //注文単価区分
           l_request.orderPriceDiv = "1";

           //注文単価
           l_request.limitPrice = "111";

           //執行条件
           l_request.execCondType = "1";

           //注文期限区分
           l_request.expirationDateType ="3";

           //注文有効期限
           l_request.expirationDate = null;

           //発注条件区分
           l_request.orderCondType = "2";

           //逆指値用プレミアム／原資産価格
           l_request.stopPremium_underlyingAssets = null;

           //逆指値用発注条件単価
           l_request.stopOrderCondPrice = null;

           //逆指値用発注条件演算子
           l_request.stopOrderCondOperator = null;

           //Ｗ指値用プレミアム／原資産価格
           l_request.wlimitPremium_underlyingAssets = "1";

           //Ｗ指値用発注条件単価
           l_request.wlimitOrderCondPrice = "2";

           //Ｗ指値用発注条件演算子
           l_request.wlimitOrderCondOperator = "1";

           //Ｗ指値用注文単価区分
           l_request.wLimitOrderPriceDiv = "1";

           //Ｗ指値用注文単価
           l_request.wLimitPrice = "1";
           
           //W指値用執行条件
           l_request.wlimitExecCondType = "1";
           
           //W指値用有効状態区分
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
    
    //４－２）this.注文数量≠null and this.注文数量≠数字 の場合
    public void test_validateATReserveOrder_case6()
    {
        final String STR_METHOD_NAME = "test_validateATReserveOrder_case6()";
       log.entering(TEST_START + STR_METHOD_NAME);
       
       try
       {
           
           //注文単価区分
           l_request.orderPriceDiv = "1";

           //注文単価
           l_request.limitPrice = "111";

           //執行条件
           l_request.execCondType = "1";

           //注文期限区分
           l_request.expirationDateType ="3";

           //注文有効期限
           l_request.expirationDate = null;

           //発注条件区分
           l_request.orderCondType = "2";

           //逆指値用プレミアム／原資産価格
           l_request.stopPremium_underlyingAssets = null;

           //逆指値用発注条件単価
           l_request.stopOrderCondPrice = null;

           //逆指値用発注条件演算子
           l_request.stopOrderCondOperator = null;

           //Ｗ指値用プレミアム／原資産価格
           l_request.wlimitPremium_underlyingAssets = "1";

           //Ｗ指値用発注条件単価
           l_request.wlimitOrderCondPrice = "2";

           //Ｗ指値用発注条件演算子
           l_request.wlimitOrderCondOperator = "1";

           //Ｗ指値用注文単価区分
           l_request.wLimitOrderPriceDiv = "1";

           //Ｗ指値用注文単価
           l_request.wLimitPrice = "1";
           
           //W指値用執行条件
           l_request.wlimitExecCondType = "1";
           
           //W指値用有効状態区分
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
    
    //this.注文数量≠null and this.注文数量≦0 の場合
    public void test_validateATReserveOrder_case7()
    {
        final String STR_METHOD_NAME = "test_validateATReserveOrder_case7()";
       log.entering(TEST_START + STR_METHOD_NAME);
       
       try
       {
           
           //注文単価区分
           l_request.orderPriceDiv = "1";

           //注文単価
           l_request.limitPrice = "111";

           //執行条件
           l_request.execCondType = "1";

           //注文期限区分
           l_request.expirationDateType ="3";

           //注文有効期限
           l_request.expirationDate = null;

           //発注条件区分
           l_request.orderCondType = "2";

           //逆指値用プレミアム／原資産価格
           l_request.stopPremium_underlyingAssets = null;

           //逆指値用発注条件単価
           l_request.stopOrderCondPrice = null;

           //逆指値用発注条件演算子
           l_request.stopOrderCondOperator = null;

           //Ｗ指値用プレミアム／原資産価格
           l_request.wlimitPremium_underlyingAssets = "1";

           //Ｗ指値用発注条件単価
           l_request.wlimitOrderCondPrice = "2";

           //Ｗ指値用発注条件演算子
           l_request.wlimitOrderCondOperator = "1";

           //Ｗ指値用注文単価区分
           l_request.wLimitOrderPriceDiv = "1";

           //Ｗ指値用注文単価
           l_request.wLimitPrice = "1";
           
           //W指値用執行条件
           l_request.wlimitExecCondType = "1";
           
           //W指値用有効状態区分
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
    
    //５）　@返済建玉の注文数量チェック 
    //　@５－１）決済順序＝”0：ランダム”の場合のみ、 
    //　@　@　@　@返済建玉の要素数分 
    //　@　@　@　@下記のチェックを繰り返して行う。 
    //　@　@　@　@※反対取引の場合は、実際には１明細のみが設定されてくる。 
    //　@　@　@　@※また、ランダム以外の場合は、リクエスト.注文数量を使用するので、 
    //　@　@　@　@※チェック不要。 
    //　@　@　@　@・返済建玉.注文数量 が以下のいずれかの場合は、 
    //　@　@　@　@　@「返済建玉の注文数量指定が不正」の例外をスローする。 
    //　@　@　@　@　@　@・null  
    public void test_validateATReserveOrder_case8()
    {
        final String STR_METHOD_NAME = "test_validateATReserveOrder_case8()";
       log.entering(TEST_START + STR_METHOD_NAME);
       
       try
       {
           
           //注文単価区分
           l_request.orderPriceDiv = "1";

           //注文単価
           l_request.limitPrice = "111";

           //執行条件
           l_request.execCondType = "1";

           //注文期限区分
           l_request.expirationDateType ="3";

           //注文有効期限
           l_request.expirationDate = null;

           //発注条件区分
           l_request.orderCondType = "2";

           //逆指値用プレミアム／原資産価格
           l_request.stopPremium_underlyingAssets = null;

           //逆指値用発注条件単価
           l_request.stopOrderCondPrice = null;

           //逆指値用発注条件演算子
           l_request.stopOrderCondOperator = null;

           //Ｗ指値用プレミアム／原資産価格
           l_request.wlimitPremium_underlyingAssets = "1";

           //Ｗ指値用発注条件単価
           l_request.wlimitOrderCondPrice = "2";

           //Ｗ指値用発注条件演算子
           l_request.wlimitOrderCondOperator = "1";

           //Ｗ指値用注文単価区分
           l_request.wLimitOrderPriceDiv = "1";

           //Ｗ指値用注文単価
           l_request.wLimitPrice = "1";
           
           //W指値用執行条件
           l_request.wlimitExecCondType = "1";
           
           //W指値用有効状態区分
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
    
    //５）　@返済建玉の注文数量チェック 
    //　@５－１）決済順序＝”0：ランダム”の場合のみ、 
    //　@　@　@　@返済建玉の要素数分 
    //　@　@　@　@下記のチェックを繰り返して行う。 
    //　@　@　@　@※反対取引の場合は、実際には１明細のみが設定されてくる。 
    //　@　@　@　@※また、ランダム以外の場合は、リクエスト.注文数量を使用するので、 
    //　@　@　@　@※チェック不要。 
    //　@　@　@　@・返済建玉.注文数量 が以下のいずれかの場合は、 
    //　@　@　@　@　@「返済建玉の注文数量指定が不正」の例外をスローする。 
    //　@　@　@　@　@　@・数字以外  
    public void test_validateATReserveOrder_case9()
    {
        final String STR_METHOD_NAME = "test_validateATReserveOrder_case9()";
       log.entering(TEST_START + STR_METHOD_NAME);
       
       try
       {
           
           //注文単価区分
           l_request.orderPriceDiv = "1";

           //注文単価
           l_request.limitPrice = "111";

           //執行条件
           l_request.execCondType = "1";

           //注文期限区分
           l_request.expirationDateType ="3";

           //注文有効期限
           l_request.expirationDate = null;

           //発注条件区分
           l_request.orderCondType = "2";

           //逆指値用プレミアム／原資産価格
           l_request.stopPremium_underlyingAssets = null;

           //逆指値用発注条件単価
           l_request.stopOrderCondPrice = null;

           //逆指値用発注条件演算子
           l_request.stopOrderCondOperator = null;

           //Ｗ指値用プレミアム／原資産価格
           l_request.wlimitPremium_underlyingAssets = "1";

           //Ｗ指値用発注条件単価
           l_request.wlimitOrderCondPrice = "2";

           //Ｗ指値用発注条件演算子
           l_request.wlimitOrderCondOperator = "1";

           //Ｗ指値用注文単価区分
           l_request.wLimitOrderPriceDiv = "1";

           //Ｗ指値用注文単価
           l_request.wLimitPrice = "1";
           
           //W指値用執行条件
           l_request.wlimitExecCondType = "1";
           
           //W指値用有効状態区分
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
    
    //５）　@返済建玉の注文数量チェック 
    //　@５－１）決済順序＝”0：ランダム”の場合のみ、 
    //　@　@　@　@返済建玉の要素数分 
    //　@　@　@　@下記のチェックを繰り返して行う。 
    //　@　@　@　@※反対取引の場合は、実際には１明細のみが設定されてくる。 
    //　@　@　@　@※また、ランダム以外の場合は、リクエスト.注文数量を使用するので、 
    //　@　@　@　@※チェック不要。 
    //　@　@　@　@・返済建玉.注文数量 が以下のいずれかの場合は、 
    //　@　@　@　@　@「返済建玉の注文数量指定が不正」の例外をスローする。 
    //　@　@　@　@　@　@・０以下の数字  
    public void test_validateATReserveOrder_case10()
    {
        final String STR_METHOD_NAME = "test_validateATReserveOrder_case10()";
       log.entering(TEST_START + STR_METHOD_NAME);
       
       try
       {
           
           //注文単価区分
           l_request.orderPriceDiv = "1";

           //注文単価
           l_request.limitPrice = "111";

           //執行条件
           l_request.execCondType = "1";

           //注文期限区分
           l_request.expirationDateType ="3";

           //注文有効期限
           l_request.expirationDate = null;

           //発注条件区分
           l_request.orderCondType = "2";

           //逆指値用プレミアム／原資産価格
           l_request.stopPremium_underlyingAssets = null;

           //逆指値用発注条件単価
           l_request.stopOrderCondPrice = null;

           //逆指値用発注条件演算子
           l_request.stopOrderCondOperator = null;

           //Ｗ指値用プレミアム／原資産価格
           l_request.wlimitPremium_underlyingAssets = "1";

           //Ｗ指値用発注条件単価
           l_request.wlimitOrderCondPrice = "2";

           //Ｗ指値用発注条件演算子
           l_request.wlimitOrderCondOperator = "1";

           //Ｗ指値用注文単価区分
           l_request.wLimitOrderPriceDiv = "1";

           //Ｗ指値用注文単価
           l_request.wLimitPrice = "1";
           
           //W指値用執行条件
           l_request.wlimitExecCondType = "1";
           
           //W指値用有効状態区分
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
    
    //５）　@返済建玉の注文数量チェック 
    //　@５－１）決済順序＝”0：ランダム”の場合のみ、 
    //　@　@　@　@返済建玉の要素数分 
    //　@　@　@　@下記のチェックを繰り返して行う。 
    //　@　@　@　@※反対取引の場合は、実際には１明細のみが設定されてくる。 
    //　@　@　@　@※また、ランダム以外の場合は、リクエスト.注文数量を使用するので、 
    //　@　@　@　@※チェック不要。 
    //　@　@　@　@・返済建玉.注文数量 が以下のいずれかの場合は、 
    //　@　@　@　@　@「返済建玉の注文数量指定が不正」の例外をスローする。 
    //　@　@　@　@　@　@・８桁を超える数字
    public void test_validateATReserveOrder_case11()
    {
        final String STR_METHOD_NAME = "test_validateATReserveOrder_case11()";
       log.entering(TEST_START + STR_METHOD_NAME);
       
       try
       {
           
           //注文単価区分
           l_request.orderPriceDiv = "1";

           //注文単価
           l_request.limitPrice = "111";

           //執行条件
           l_request.execCondType = "1";

           //注文期限区分
           l_request.expirationDateType ="3";

           //注文有効期限
           l_request.expirationDate = null;

           //発注条件区分
           l_request.orderCondType = "2";

           //逆指値用プレミアム／原資産価格
           l_request.stopPremium_underlyingAssets = null;

           //逆指値用発注条件単価
           l_request.stopOrderCondPrice = null;

           //逆指値用発注条件演算子
           l_request.stopOrderCondOperator = null;

           //Ｗ指値用プレミアム／原資産価格
           l_request.wlimitPremium_underlyingAssets = "1";

           //Ｗ指値用発注条件単価
           l_request.wlimitOrderCondPrice = "2";

           //Ｗ指値用発注条件演算子
           l_request.wlimitOrderCondOperator = "1";

           //Ｗ指値用注文単価区分
           l_request.wLimitOrderPriceDiv = "1";

           //Ｗ指値用注文単価
           l_request.wLimitPrice = "1";
           
           //W指値用執行条件
           l_request.wlimitExecCondType = "1";
           
           //W指値用有効状態区分
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
    
    //スーパークラスのvalidateメソッドを呼び出す
    //２）　@返済建玉チェック 
    //３）　@決済順序チェック 
    //４）　@注文数量チェック 
    //５）　@返済建玉の注文数量チェック 
    public void test_validateATReserveOrder_case12()
    {
        final String STR_METHOD_NAME = "test_validateATReserveOrder_case12()";
       log.entering(TEST_START + STR_METHOD_NAME);
       
       try
       {
           
           //注文単価区分
           l_request.orderPriceDiv = "1";

           //注文単価
           l_request.limitPrice = "111";

           //執行条件
           l_request.execCondType = "1";

           //注文期限区分
           l_request.expirationDateType ="3";

           //注文有効期限
           l_request.expirationDate = null;

           //発注条件区分
           l_request.orderCondType = "2";

           //逆指値用プレミアム／原資産価格
           l_request.stopPremium_underlyingAssets = null;

           //逆指値用発注条件単価
           l_request.stopOrderCondPrice = null;

           //逆指値用発注条件演算子
           l_request.stopOrderCondOperator = null;

           //Ｗ指値用プレミアム／原資産価格
           l_request.wlimitPremium_underlyingAssets = "1";

           //Ｗ指値用発注条件単価
           l_request.wlimitOrderCondPrice = "2";

           //Ｗ指値用発注条件演算子
           l_request.wlimitOrderCondOperator = "1";

           //Ｗ指値用注文単価区分
           l_request.wLimitOrderPriceDiv = "1";

           //Ｗ指値用注文単価
           l_request.wLimitPrice = "1";
           
           //W指値用執行条件
           l_request.wlimitExecCondType = "1";
           
           //W指値用有効状態区分
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
