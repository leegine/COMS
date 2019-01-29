head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.52.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminForcedSettleReferenceRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.eqtypeadmin.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminForcedSettleReferenceRequestTest extends TestBaseForMock
{
    /**
     *　@ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminForcedSettleReferenceRequestTest.class);

    public WEB3AdminForcedSettleReferenceRequestTest(String arg0)
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

    public void testValidate_0001()
    {
        String STR_METHOD_NAME = ".testValidate_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = null;
        l_request.accountCode = "123456";
        l_request.productCode = "12345";
        l_request.approveState = "0";
        l_request.checker ="123";
        l_request.createDateFrom = "20000601";
        l_request.createDateTo = "20070301";
        l_request.approveDateFrom = "20000601";
        l_request.approveDateTo = "20070301";
        l_request.forcedSettleReason = "0";
        l_request.closeDate = new Date();
        l_request.errorReason = "0005";
        l_request.approveType = "0";
        l_request.taxType = "0";
        l_request.marketCode = "1";
        l_request.sortKeys = new WEB3AdminForcedSettleSortKeyUnit[1];
        l_request.pageIndex = "12";
        l_request.pageSize = "12";

        try
        {
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00833, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }

    public void testValidate_0002()
    {
        String STR_METHOD_NAME = ".testValidate_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "123";
        l_request.accountCode = "123456";
        l_request.productCode = "12345";
        l_request.approveState = "0";
        l_request.checker ="123";
        l_request.createDateFrom = "20000601";
        l_request.createDateTo = "20070301";
        l_request.approveDateFrom = "20000601";
        l_request.approveDateTo = "20070301";
        l_request.forcedSettleReason = "0";
        l_request.closeDate = new Date();
        l_request.errorReason = "0005";
        l_request.approveType = "0";
        l_request.taxType = "0";
        l_request.marketCode = "1";
        
        WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
        l_sort.keyItem ="contractExecPrice";
        l_sort.ascDesc ="D";
        WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
        l_request.sortKeys = l_message;
        
        l_request.pageIndex = "1234";
        l_request.pageSize = "12";
        
        try
        {
            l_request.validate();
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
    }

    public void testValidate_0003()
    {
        String STR_METHOD_NAME = ".testValidate_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "shj";
        l_request.accountCode = "123456";
        l_request.productCode = "12345";
        l_request.approveState = "0";
        l_request.checker ="123";
        l_request.createDateFrom = "20000601";
        l_request.createDateTo = "20070301";
        l_request.approveDateFrom = "20000601";
        l_request.approveDateTo = "20070301";
        l_request.forcedSettleReason = "0";
        l_request.closeDate = new Date();
        l_request.errorReason = "0005";
        l_request.approveType = "0";
        l_request.taxType = "0";
        l_request.marketCode = "1";
        
        WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
        l_sort.keyItem ="contractExecPrice";
        l_sort.ascDesc ="D";
        WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
        l_request.sortKeys = l_message;
        
        l_request.pageIndex = "1234";
        l_request.pageSize = "12";
        
        try
        {
            l_request.validate();
            
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00779, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
    }
    
    public void testValidate_0004()
    {
        String STR_METHOD_NAME = ".testValidate_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "1234";
        l_request.accountCode = "123456";
        l_request.productCode = "12345";
        l_request.approveState = "0";
        l_request.checker ="123";
        l_request.createDateFrom = "20000601";
        l_request.createDateTo = "20070301";
        l_request.approveDateFrom = "20000601";
        l_request.approveDateTo = "20070301";
        l_request.forcedSettleReason = "0";
        l_request.closeDate = new Date();
        l_request.errorReason = "0005";
        l_request.approveType = "0";
        l_request.taxType = "0";
        l_request.marketCode = "1";
        
        WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
        l_sort.keyItem ="contractExecPrice";
        l_sort.ascDesc ="D";
        WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
        l_request.sortKeys = l_message;
        
        l_request.pageIndex = "1234";
        l_request.pageSize = "12";
        
        try
        {
            l_request.validate();
            
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00779, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
    }

  public void testValidate_0005()
  {
      String STR_METHOD_NAME = ".testValidate_0005()";
      log.entering(TEST_START + STR_METHOD_NAME);

      WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
      l_request.branchCodeList = new String[1];
      l_request.branchCodeList[0] = "123";
      l_request.accountCode = "123456";
      l_request.productCode = "12345";
      l_request.approveState = "0";
      l_request.checker ="123";
      l_request.createDateFrom = "20000601";
      l_request.createDateTo = "20070301";
      l_request.approveDateFrom = "20000601";
      l_request.approveDateTo = "20070301";
      l_request.forcedSettleReason = "0";
      l_request.closeDate = new Date();
      l_request.errorReason = "0005";
      l_request.approveType = "0";
      l_request.taxType = "0";
      l_request.marketCode = "1";
      
      WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
      l_sort.keyItem ="contractExecPrice";
      l_sort.ascDesc ="D";
      WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
      l_request.sortKeys = l_message;
      
      l_request.pageIndex = "1234";
      l_request.pageSize = "12";
      
      try
      {
          l_request.validate();
          log.exiting(TEST_END + STR_METHOD_NAME);
      }
      catch (WEB3BaseException l_ex)
      {
          log.debug(TEST_END + STR_METHOD_NAME, l_ex);
          log.exiting(TEST_END + STR_METHOD_NAME);

          fail();
      }
      catch (Exception l_ex)
      {
          log.error(TEST_END + STR_METHOD_NAME, l_ex);
          log.exiting(TEST_END + STR_METHOD_NAME);
          
          fail();
      }
  }

  public void testValidate_0006()
  {
      String STR_METHOD_NAME = ".testValidate_0006()";
      log.entering(TEST_START + STR_METHOD_NAME);

      WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
      l_request.branchCodeList = new String[1];
      l_request.branchCodeList[0] = "123";
      l_request.accountCode = "abcdef";
      l_request.productCode = "12345";
      l_request.approveState = "0";
      l_request.checker ="123";
      l_request.createDateFrom = "20000601";
      l_request.createDateTo = "20070301";
      l_request.approveDateFrom = "20000601";
      l_request.approveDateTo = "20070301";
      l_request.forcedSettleReason = "0";
      l_request.closeDate = new Date();
      l_request.errorReason = "0005";
      l_request.approveType = "0";
      l_request.taxType = "0";
      l_request.marketCode = "1";
      
      WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
      l_sort.keyItem ="contractExecPrice";
      l_sort.ascDesc ="D";
      WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
      l_request.sortKeys = l_message;
      
      l_request.pageIndex = "1234";
      l_request.pageSize = "12";
      
      try
      {
          l_request.validate();
          log.exiting(TEST_END + STR_METHOD_NAME);
          
          fail();
      }
      catch (WEB3BaseException l_ex)
      {
          log.debug(TEST_END + STR_METHOD_NAME, l_ex);
          log.exiting(TEST_END + STR_METHOD_NAME);

          assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00780,l_ex.getErrorInfo());
      }
      catch (Exception l_ex)
      {
          log.error(TEST_END + STR_METHOD_NAME, l_ex);
          log.exiting(TEST_END + STR_METHOD_NAME);
          
          fail();
      }
  }

    public void testValidate_0007()
    {
        String STR_METHOD_NAME = ".testValidate_0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "123";
        l_request.accountCode = "12345";
        l_request.productCode = "12345";
        l_request.approveState = "0";
        l_request.checker ="123";
        l_request.createDateFrom = "20000601";
        l_request.createDateTo = "20070301";
        l_request.approveDateFrom = "20000601";
        l_request.approveDateTo = "20070301";
        l_request.forcedSettleReason = "0";
        l_request.closeDate = new Date();
        l_request.errorReason = "0005";
        l_request.approveType = "0";
        l_request.taxType = "0";
        l_request.marketCode = "1";
        
        WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
        l_sort.keyItem ="contractExecPrice";
        l_sort.ascDesc ="D";
        WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
        l_request.sortKeys = l_message;
        
        l_request.pageIndex = "1234";
        l_request.pageSize = "12";
        
        try
        {
            l_request.validate();
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00780,l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
    }

  public void testValidate_0008()
  {
      String STR_METHOD_NAME = ".testValidate_0008()";
      log.entering(TEST_START + STR_METHOD_NAME);

      WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
      l_request.branchCodeList = new String[1];
      l_request.branchCodeList[0] = "123";
      l_request.accountCode = null;
      l_request.productCode = "12345";
      l_request.approveState = "0";
      l_request.checker ="123";
      l_request.createDateFrom = "20000601";
      l_request.createDateTo = "20070301";
      l_request.approveDateFrom = "20000601";
      l_request.approveDateTo = "20070301";
      l_request.forcedSettleReason = "0";
      l_request.closeDate = new Date();
      l_request.errorReason = "0005";
      l_request.approveType = "0";
      l_request.taxType = "0";
      l_request.marketCode = "1";
      
      WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
      l_sort.keyItem ="contractExecPrice";
      l_sort.ascDesc ="D";
      WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
      l_request.sortKeys = l_message;
      
      l_request.pageIndex = "1234";
      l_request.pageSize = "12";
      
      try
      {
          l_request.validate();
          log.exiting(TEST_END + STR_METHOD_NAME);
      }
      catch (WEB3BaseException l_ex)
      {
          log.debug(TEST_END + STR_METHOD_NAME, l_ex);
          log.exiting(TEST_END + STR_METHOD_NAME);

          fail();
      }
      catch (Exception l_ex)
      {
          log.error(TEST_END + STR_METHOD_NAME, l_ex);
          log.exiting(TEST_END + STR_METHOD_NAME);
          
          fail();
      }
  }

  public void testValidate_0009()
  {
      String STR_METHOD_NAME = ".testValidate_0009()";
      log.entering(TEST_START + STR_METHOD_NAME);

      WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
      l_request.branchCodeList = new String[1];
      l_request.branchCodeList[0] = "123";
      l_request.accountCode = "123456";
      l_request.productCode = null;
      l_request.approveState = "0";
      l_request.checker ="123";
      l_request.createDateFrom = "20000601";
      l_request.createDateTo = "20070301";
      l_request.approveDateFrom = "20000601";
      l_request.approveDateTo = "20070301";
      l_request.forcedSettleReason = "0";
      l_request.closeDate = new Date();
      l_request.errorReason = "0005";
      l_request.approveType = "0";
      l_request.taxType = "0";
      l_request.marketCode = "1";
      
      WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
      l_sort.keyItem ="contractExecPrice";
      l_sort.ascDesc ="D";
      WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
      l_request.sortKeys = l_message;
      
      l_request.pageIndex = "1234";
      l_request.pageSize = "12";
      
      try
      {
          l_request.validate();
          log.exiting(TEST_END + STR_METHOD_NAME);
      }
      catch (WEB3BaseException l_ex)
      {
          log.debug(TEST_END + STR_METHOD_NAME, l_ex);
          log.exiting(TEST_END + STR_METHOD_NAME);

          fail();
      }
      catch (Exception l_ex)
      {
          log.error(TEST_END + STR_METHOD_NAME, l_ex);
          log.exiting(TEST_END + STR_METHOD_NAME);
          
          fail();
      }
  }

  public void testValidate_0010()
  {
      String STR_METHOD_NAME = ".testValidate_0010()";
      log.entering(TEST_START + STR_METHOD_NAME);

      WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
      l_request.branchCodeList = new String[1];
      l_request.branchCodeList[0] = "123";
      l_request.accountCode = "123456";
      l_request.productCode = "12345";
      l_request.approveState = "0";
      l_request.checker ="123";
      l_request.createDateFrom = "20000601";
      l_request.createDateTo = "20070301";
      l_request.approveDateFrom = "20000601";
      l_request.approveDateTo = "20070301";
      l_request.forcedSettleReason = "0";
      l_request.closeDate = new Date();
      l_request.errorReason = "0005";
      l_request.approveType = "0";
      l_request.taxType = "0";
      l_request.marketCode = "1";
      
      WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
      l_sort.keyItem ="contractExecPrice";
      l_sort.ascDesc ="D";
      WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
      l_request.sortKeys = l_message;
      
      l_request.pageIndex = "1234";
      l_request.pageSize = "12";
      
      try
      {
          l_request.validate();
          log.exiting(TEST_END + STR_METHOD_NAME);
      }
      catch (WEB3BaseException l_ex)
      {
          log.debug(TEST_END + STR_METHOD_NAME, l_ex);
          log.exiting(TEST_END + STR_METHOD_NAME);

          fail();
      }
      catch (Exception l_ex)
      {
          log.error(TEST_END + STR_METHOD_NAME, l_ex);
          log.exiting(TEST_END + STR_METHOD_NAME);
          
          fail();
      }
  }

  public void testValidate_0011()
  {
      String STR_METHOD_NAME = ".testValidate_0011()";
      log.entering(TEST_START + STR_METHOD_NAME);

      WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
      l_request.branchCodeList = new String[1];
      l_request.branchCodeList[0] = "123";
      l_request.accountCode = "123456";
      l_request.productCode = "abcde";
      l_request.approveState = "0";
      l_request.checker ="123";
      l_request.createDateFrom = "20000601";
      l_request.createDateTo = "20070301";
      l_request.approveDateFrom = "20000601";
      l_request.approveDateTo = "20070301";
      l_request.forcedSettleReason = "0";
      l_request.closeDate = new Date();
      l_request.errorReason = "0005";
      l_request.approveType = "0";
      l_request.taxType = "0";
      l_request.marketCode = "1";
      
      WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
      l_sort.keyItem ="contractExecPrice";
      l_sort.ascDesc ="D";
      WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
      l_request.sortKeys = l_message;
      
      l_request.pageIndex = "1234";
      l_request.pageSize = "12";
      
      try
      {
          l_request.validate();
          log.exiting(TEST_END + STR_METHOD_NAME);
          
          fail();
      }
      catch (WEB3BaseException l_ex)
      {
          log.debug(TEST_END + STR_METHOD_NAME, l_ex);
          log.exiting(TEST_END + STR_METHOD_NAME);

          assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01067,l_ex.getErrorInfo());
      }
      catch (Exception l_ex)
      {
          log.error(TEST_END + STR_METHOD_NAME, l_ex);
          log.exiting(TEST_END + STR_METHOD_NAME);
          
          fail();
      }
  }

  public void testValidate_0012()
  {
      String STR_METHOD_NAME = ".testValidate_0012()";
      log.entering(TEST_START + STR_METHOD_NAME);

      WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
      l_request.branchCodeList = new String[1];
      l_request.branchCodeList[0] = "123";
      l_request.accountCode = "123456";
      l_request.productCode = "123";
      l_request.approveState = "0";
      l_request.checker ="123";
      l_request.createDateFrom = "20000601";
      l_request.createDateTo = "20070301";
      l_request.approveDateFrom = "20000601";
      l_request.approveDateTo = "20070301";
      l_request.forcedSettleReason = "0";
      l_request.closeDate = new Date();
      l_request.errorReason = "0005";
      l_request.approveType = "0";
      l_request.taxType = "0";
      l_request.marketCode = "1";
      
      WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
      l_sort.keyItem ="contractExecPrice";
      l_sort.ascDesc ="D";
      WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
      l_request.sortKeys = l_message;
      
      l_request.pageIndex = "1234";
      l_request.pageSize = "12";
      
      try
      {
          l_request.validate();
          log.exiting(TEST_END + STR_METHOD_NAME);
          
          fail();
      }
      catch (WEB3BaseException l_ex)
      {
          log.debug(TEST_END + STR_METHOD_NAME, l_ex);
          log.exiting(TEST_END + STR_METHOD_NAME);

          assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01067,l_ex.getErrorInfo());
      }
      catch (Exception l_ex)
      {
          log.error(TEST_END + STR_METHOD_NAME, l_ex);
          log.exiting(TEST_END + STR_METHOD_NAME);
          
          fail();
      }
  }

  public void testValidate_0013()
  {
      String STR_METHOD_NAME = ".testValidate_0013()";
      log.entering(TEST_START + STR_METHOD_NAME);

      WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
      l_request.branchCodeList = new String[1];
      l_request.branchCodeList[0] = "123";
      l_request.accountCode = "123456";
      l_request.productCode = null;
      l_request.approveState = null;
      l_request.checker ="123";
      l_request.createDateFrom = "20000601";
      l_request.createDateTo = "20070301";
      l_request.approveDateFrom = "20000601";
      l_request.approveDateTo = "20070301";
      l_request.forcedSettleReason = "0";
      l_request.closeDate = new Date();
      l_request.errorReason = "0005";
      l_request.approveType = "0";
      l_request.taxType = "0";
      l_request.marketCode = "1";
      
      WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
      l_sort.keyItem ="contractExecPrice";
      l_sort.ascDesc ="D";
      WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
      l_request.sortKeys = l_message;
      
      l_request.pageIndex = "1234";
      l_request.pageSize = "12";
      
      try
      {
          l_request.validate();
          log.exiting(TEST_END + STR_METHOD_NAME);
      }
      catch (WEB3BaseException l_ex)
      {
          log.debug(TEST_END + STR_METHOD_NAME, l_ex);
          log.exiting(TEST_END + STR_METHOD_NAME);

          fail();
      }
      catch (Exception l_ex)
      {
          log.error(TEST_END + STR_METHOD_NAME, l_ex);
          log.exiting(TEST_END + STR_METHOD_NAME);
          
          fail();
      }
  }
  
  public void testValidate_0014()
  {
      String STR_METHOD_NAME = ".testValidate_0014()";
      log.entering(TEST_START + STR_METHOD_NAME);

      WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
      l_request.branchCodeList = new String[1];
      l_request.branchCodeList[0] = "123";
      l_request.accountCode = "123456";
      l_request.productCode = null;
      l_request.approveState = "0";
      l_request.checker ="123";
      l_request.createDateFrom = "20000601";
      l_request.createDateTo = "20070301";
      l_request.approveDateFrom = "20000601";
      l_request.approveDateTo = "20070301";
      l_request.forcedSettleReason = "0";
      l_request.closeDate = new Date();
      l_request.errorReason = "0005";
      l_request.approveType = "0";
      l_request.taxType = "0";
      l_request.marketCode = "1";
      
      WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
      l_sort.keyItem ="contractExecPrice";
      l_sort.ascDesc ="D";
      WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
      l_request.sortKeys = l_message;
      
      l_request.pageIndex = "1234";
      l_request.pageSize = "12";
      
      try
      {
          l_request.validate();
          log.exiting(TEST_END + STR_METHOD_NAME);
      }
      catch (WEB3BaseException l_ex)
      {
          log.debug(TEST_END + STR_METHOD_NAME, l_ex);
          log.exiting(TEST_END + STR_METHOD_NAME);

          fail();
      }
      catch (Exception l_ex)
      {
          log.error(TEST_END + STR_METHOD_NAME, l_ex);
          log.exiting(TEST_END + STR_METHOD_NAME);
          
          fail();
      }
  }
  
  public void testValidate_0015()
  {
      String STR_METHOD_NAME = ".testValidate_0015()";
      log.entering(TEST_START + STR_METHOD_NAME);

      WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
      l_request.branchCodeList = new String[1];
      l_request.branchCodeList[0] = "123";
      l_request.accountCode = "123456";
      l_request.productCode = null;
      l_request.approveState = "1";
      l_request.checker ="123";
      l_request.createDateFrom = "20000601";
      l_request.createDateTo = "20070301";
      l_request.approveDateFrom = "20000601";
      l_request.approveDateTo = "20070301";
      l_request.forcedSettleReason = "0";
      l_request.closeDate = new Date();
      l_request.errorReason = "0005";
      l_request.approveType = "0";
      l_request.taxType = "0";
      l_request.marketCode = "1";
      
      WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
      l_sort.keyItem ="contractExecPrice";
      l_sort.ascDesc ="D";
      WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
      l_request.sortKeys = l_message;
      
      l_request.pageIndex = "1234";
      l_request.pageSize = "12";
      
      try
      {
          l_request.validate();
          log.exiting(TEST_END + STR_METHOD_NAME);
      }
      catch (WEB3BaseException l_ex)
      {
          log.debug(TEST_END + STR_METHOD_NAME, l_ex);
          log.exiting(TEST_END + STR_METHOD_NAME);

          fail();
      }
      catch (Exception l_ex)
      {
          log.error(TEST_END + STR_METHOD_NAME, l_ex);
          log.exiting(TEST_END + STR_METHOD_NAME);
          
          fail();
      }
  }
  
  public void testValidate_0016()
  {
      String STR_METHOD_NAME = ".testValidate_0016()";
      log.entering(TEST_START + STR_METHOD_NAME);

      WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
      l_request.branchCodeList = new String[1];
      l_request.branchCodeList[0] = "123";
      l_request.accountCode = "123456";
      l_request.productCode = null;
      l_request.approveState = "2";
      l_request.checker ="123";
      l_request.createDateFrom = "20000601";
      l_request.createDateTo = "20070301";
      l_request.approveDateFrom = "20000601";
      l_request.approveDateTo = "20070301";
      l_request.forcedSettleReason = "0";
      l_request.closeDate = new Date();
      l_request.errorReason = "0005";
      l_request.approveType = "0";
      l_request.taxType = "0";
      l_request.marketCode = "1";
      
      WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
      l_sort.keyItem ="contractExecPrice";
      l_sort.ascDesc ="D";
      WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
      l_request.sortKeys = l_message;
      
      l_request.pageIndex = "1234";
      l_request.pageSize = "12";
      
      try
      {
          l_request.validate();
          log.exiting(TEST_END + STR_METHOD_NAME);
      }
      catch (WEB3BaseException l_ex)
      {
          log.debug(TEST_END + STR_METHOD_NAME, l_ex);
          log.exiting(TEST_END + STR_METHOD_NAME);

          fail();
      }
      catch (Exception l_ex)
      {
          log.error(TEST_END + STR_METHOD_NAME, l_ex);
          log.exiting(TEST_END + STR_METHOD_NAME);
          
          fail();
      }
  }
  
  public void testValidate_0017()
  {
      String STR_METHOD_NAME = ".testValidate_0017()";
      log.entering(TEST_START + STR_METHOD_NAME);

      WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
      l_request.branchCodeList = new String[1];
      l_request.branchCodeList[0] = "123";
      l_request.accountCode = "123456";
      l_request.productCode = null;
      l_request.approveState = "9";
      l_request.checker ="123";
      l_request.createDateFrom = "20000601";
      l_request.createDateTo = "20070301";
      l_request.approveDateFrom = "20000601";
      l_request.approveDateTo = "20070301";
      l_request.forcedSettleReason = "0";
      l_request.closeDate = new Date();
      l_request.errorReason = "0005";
      l_request.approveType = "0";
      l_request.taxType = "0";
      l_request.marketCode = "1";
      
      WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
      l_sort.keyItem ="contractExecPrice";
      l_sort.ascDesc ="D";
      WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
      l_request.sortKeys = l_message;
      
      l_request.pageIndex = "1234";
      l_request.pageSize = "12";
      
      try
      {
          l_request.validate();
          log.exiting(TEST_END + STR_METHOD_NAME);
      }
      catch (WEB3BaseException l_ex)
      {
          log.debug(TEST_END + STR_METHOD_NAME, l_ex);
          log.exiting(TEST_END + STR_METHOD_NAME);

          fail();
      }
      catch (Exception l_ex)
      {
          log.error(TEST_END + STR_METHOD_NAME, l_ex);
          log.exiting(TEST_END + STR_METHOD_NAME);
          
          fail();
      }
  }
  
  public void testValidate_0018()
  {
      String STR_METHOD_NAME = ".testValidate_0018()";
      log.entering(TEST_START + STR_METHOD_NAME);

      WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
      l_request.branchCodeList = new String[1];
      l_request.branchCodeList[0] = "123";
      l_request.accountCode = "123456";
      l_request.productCode = null;
      l_request.approveState = "1334";
      l_request.checker ="123";
      l_request.createDateFrom = "20000601";
      l_request.createDateTo = "20070301";
      l_request.approveDateFrom = "20000601";
      l_request.approveDateTo = "20070301";
      l_request.forcedSettleReason = "0";
      l_request.closeDate = new Date();
      l_request.errorReason = "0005";
      l_request.approveType = "0";
      l_request.taxType = "0";
      l_request.marketCode = "1";
      
      WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
      l_sort.keyItem ="contractExecPrice";
      l_sort.ascDesc ="D";
      WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
      l_request.sortKeys = l_message;
      
      l_request.pageIndex = "1234";
      l_request.pageSize = "12";
      
      try
      {
          l_request.validate();
          log.exiting(TEST_END + STR_METHOD_NAME);
      
          fail();
      }
      catch (WEB3BaseException l_ex)
      {
          log.debug(TEST_END + STR_METHOD_NAME, l_ex);
          log.exiting(TEST_END + STR_METHOD_NAME);

          assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02753,l_ex.getErrorInfo());
      }
      catch (Exception l_ex)
      {
          log.error(TEST_END + STR_METHOD_NAME, l_ex);
          log.exiting(TEST_END + STR_METHOD_NAME);
          
          fail();
      }
  }
    
    public void testValidate_0019()
    {
        String STR_METHOD_NAME = ".testValidate_0019()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "123";
        l_request.accountCode = "123456";
        l_request.productCode = null;
        l_request.approveState = "9";
        l_request.checker = "123";
        l_request.createDateFrom = null;
        l_request.createDateTo = "20070301";
        l_request.approveDateFrom = "20000601";
        l_request.approveDateTo = "20070301";
        l_request.forcedSettleReason = "0";
        l_request.closeDate = new Date();
        l_request.errorReason = "0005";
        l_request.approveType = "0";
        l_request.taxType = "0";
        l_request.marketCode = "1";

        WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
        l_sort.keyItem = "contractExecPrice";
        l_sort.ascDesc = "D";
        WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
        l_request.sortKeys = l_message;

        l_request.pageIndex = "1234";
        l_request.pageSize = "12";

        try
        {
            l_request.validate();
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }

    public void testValidate_0020()
    {
        String STR_METHOD_NAME = ".testValidate_0020()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "123";
        l_request.accountCode = "123456";
        l_request.productCode = null;
        l_request.approveState = "9";
        l_request.checker = "123";
        l_request.createDateFrom = "20000601";
        l_request.createDateTo = "20070301";
        l_request.approveDateFrom = "20000601";
        l_request.approveDateTo = "20070301";
        l_request.forcedSettleReason = "0";
        l_request.closeDate = new Date();
        l_request.errorReason = "0005";
        l_request.approveType = "0";
        l_request.taxType = "0";
        l_request.marketCode = "1";

        WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
        l_sort.keyItem = "contractExecPrice";
        l_sort.ascDesc = "D";
        WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
        l_request.sortKeys = l_message;

        l_request.pageIndex = "1234";
        l_request.pageSize = "12";

        try
        {
            l_request.validate();
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    public void testValidate_0021()
    {
        String STR_METHOD_NAME = ".testValidate_0021()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "123";
        l_request.accountCode = "123456";
        l_request.productCode = null;
        l_request.approveState = "9";
        l_request.checker = "123";
        l_request.createDateFrom = "20002001";
        l_request.createDateTo = "20070301";
        l_request.approveDateFrom = "20000601";
        l_request.approveDateTo = "20070301";
        l_request.forcedSettleReason = "0";
        l_request.closeDate = new Date();
        l_request.errorReason = "0005";
        l_request.approveType = "0";
        l_request.taxType = "0";
        l_request.marketCode = "1";

        WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
        l_sort.keyItem = "contractExecPrice";
        l_sort.ascDesc = "D";
        WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
        l_request.sortKeys = l_message;

        l_request.pageIndex = "1234";
        l_request.pageSize = "12";

        try
        {
            l_request.validate();
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02754,l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }

    public void testValidate_0022()
    {
        String STR_METHOD_NAME = ".testValidate_0022()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "123";
        l_request.accountCode = "123456";
        l_request.productCode = null;
        l_request.approveState = "9";
        l_request.checker = "123";
        l_request.createDateFrom = "20000601";
        l_request.createDateTo = null;
        l_request.approveDateFrom = "20000601";
        l_request.approveDateTo = "20070301";
        l_request.forcedSettleReason = "0";
        l_request.closeDate = new Date();
        l_request.errorReason = "0005";
        l_request.approveType = "0";
        l_request.taxType = "0";
        l_request.marketCode = "1";

        WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
        l_sort.keyItem = "contractExecPrice";
        l_sort.ascDesc = "D";
        WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
        l_request.sortKeys = l_message;

        l_request.pageIndex = "1234";
        l_request.pageSize = "12";

        try
        {
            l_request.validate();
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }

    public void testValidate_0023()
    {
        String STR_METHOD_NAME = ".testValidate_0023()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "123";
        l_request.accountCode = "123456";
        l_request.productCode = null;
        l_request.approveState = "9";
        l_request.checker = "123";
        l_request.createDateFrom = "20000601";
        l_request.createDateTo = "20070301";
        l_request.approveDateFrom = "20000601";
        l_request.approveDateTo = "20070301";
        l_request.forcedSettleReason = "0";
        l_request.closeDate = new Date();
        l_request.errorReason = "0005";
        l_request.approveType = "0";
        l_request.taxType = "0";
        l_request.marketCode = "1";

        WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
        l_sort.keyItem = "contractExecPrice";
        l_sort.ascDesc = "D";
        WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
        l_request.sortKeys = l_message;

        l_request.pageIndex = "1234";
        l_request.pageSize = "12";

        try
        {
            l_request.validate();
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    public void testValidate_0024()
    {
        String STR_METHOD_NAME = ".testValidate_0024()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "123";
        l_request.accountCode = "123456";
        l_request.productCode = null;
        l_request.approveState = "9";
        l_request.checker = "123";
        l_request.createDateFrom = "20000601";
        l_request.createDateTo = "20072001";
        l_request.approveDateFrom = "20000601";
        l_request.approveDateTo = "20070301";
        l_request.forcedSettleReason = "0";
        l_request.closeDate = new Date();
        l_request.errorReason = "0005";
        l_request.approveType = "0";
        l_request.taxType = "0";
        l_request.marketCode = "1";

        WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
        l_sort.keyItem = "contractExecPrice";
        l_sort.ascDesc = "D";
        WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
        l_request.sortKeys = l_message;

        l_request.pageIndex = "1234";
        l_request.pageSize = "12";

        try
        {
            l_request.validate();
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02755,l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }

    public void testValidate_0025()
    {
        String STR_METHOD_NAME = ".testValidate_0025()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "123";
        l_request.accountCode = "123456";
        l_request.productCode = null;
        l_request.approveState = "9";
        l_request.checker = "123";
        l_request.createDateFrom = null;
        l_request.createDateTo = null;
        l_request.approveDateFrom = "20000601";
        l_request.approveDateTo = "20070301";
        l_request.forcedSettleReason = "0";
        l_request.closeDate = new Date();
        l_request.errorReason = "0005";
        l_request.approveType = "0";
        l_request.taxType = "0";
        l_request.marketCode = "1";

        WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
        l_sort.keyItem = "contractExecPrice";
        l_sort.ascDesc = "D";
        WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
        l_request.sortKeys = l_message;

        l_request.pageIndex = "1234";
        l_request.pageSize = "12";

        try
        {
            l_request.validate();
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    public void testValidate_0026()
    {
        String STR_METHOD_NAME = ".testValidate_0026()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "123";
        l_request.accountCode = "123456";
        l_request.productCode = null;
        l_request.approveState = "9";
        l_request.checker = "123";
        l_request.createDateFrom = null;
        l_request.createDateTo = "20070301";
        l_request.approveDateFrom = "20000601";
        l_request.approveDateTo = "20070301";
        l_request.forcedSettleReason = "0";
        l_request.closeDate = new Date();
        l_request.errorReason = "0005";
        l_request.approveType = "0";
        l_request.taxType = "0";
        l_request.marketCode = "1";

        WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
        l_sort.keyItem = "contractExecPrice";
        l_sort.ascDesc = "D";
        WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
        l_request.sortKeys = l_message;

        l_request.pageIndex = "1234";
        l_request.pageSize = "12";

        try
        {
            l_request.validate();
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    public void testValidate_0027()
    {
        String STR_METHOD_NAME = ".testValidate_0027()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "123";
        l_request.accountCode = "123456";
        l_request.productCode = null;
        l_request.approveState = "9";
        l_request.checker = "123";
        l_request.createDateFrom = "20000601";
        l_request.createDateTo = null;
        l_request.approveDateFrom = "20000601";
        l_request.approveDateTo = "20070301";
        l_request.forcedSettleReason = "0";
        l_request.closeDate = new Date();
        l_request.errorReason = "0005";
        l_request.approveType = "0";
        l_request.taxType = "0";
        l_request.marketCode = "1";

        WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
        l_sort.keyItem = "contractExecPrice";
        l_sort.ascDesc = "D";
        WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
        l_request.sortKeys = l_message;

        l_request.pageIndex = "1234";
        l_request.pageSize = "12";

        try
        {
            l_request.validate();
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    public void testValidate_0028()
    {
        String STR_METHOD_NAME = ".testValidate_0028()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "123";
        l_request.accountCode = "123456";
        l_request.productCode = null;
        l_request.approveState = "9";
        l_request.checker = "123";
        l_request.createDateFrom = "20000601";
        l_request.createDateTo = "20070301";
        l_request.approveDateFrom = "20000601";
        l_request.approveDateTo = "20070301";
        l_request.forcedSettleReason = "0";
        l_request.closeDate = new Date();
        l_request.errorReason = "0005";
        l_request.approveType = "0";
        l_request.taxType = "0";
        l_request.marketCode = "1";

        WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
        l_sort.keyItem = "contractExecPrice";
        l_sort.ascDesc = "D";
        WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
        l_request.sortKeys = l_message;

        l_request.pageIndex = "1234";
        l_request.pageSize = "12";

        try
        {
            l_request.validate();
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    public void testValidate_0029()
    {
        String STR_METHOD_NAME = ".testValidate_0029()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "123";
        l_request.accountCode = "123456";
        l_request.productCode = null;
        l_request.approveState = "9";
        l_request.checker = "123";
        l_request.createDateFrom = "20000601";
        l_request.createDateTo = "20000601";
        l_request.approveDateFrom = "20000601";
        l_request.approveDateTo = "20070301";
        l_request.forcedSettleReason = "0";
        l_request.closeDate = new Date();
        l_request.errorReason = "0005";
        l_request.approveType = "0";
        l_request.taxType = "0";
        l_request.marketCode = "1";

        WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
        l_sort.keyItem = "contractExecPrice";
        l_sort.ascDesc = "D";
        WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
        l_request.sortKeys = l_message;

        l_request.pageIndex = "1234";
        l_request.pageSize = "12";

        try
        {
            l_request.validate();
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    public void testValidate_0030()
    {
        String STR_METHOD_NAME = ".testValidate_0030()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "123";
        l_request.accountCode = "123456";
        l_request.productCode = null;
        l_request.approveState = "9";
        l_request.checker = "123";
        l_request.createDateFrom = "20070601";
        l_request.createDateTo = "20000601";
        l_request.approveDateFrom = "20000601";
        l_request.approveDateTo = "20070301";
        l_request.forcedSettleReason = "0";
        l_request.closeDate = new Date();
        l_request.errorReason = "0005";
        l_request.approveType = "0";
        l_request.taxType = "0";
        l_request.marketCode = "1";

        WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
        l_sort.keyItem = "contractExecPrice";
        l_sort.ascDesc = "D";
        WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
        l_request.sortKeys = l_message;

        l_request.pageIndex = "1234";
        l_request.pageSize = "12";

        try
        {
            l_request.validate();
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02715,l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }

    public void testValidate_0031()
    {
        String STR_METHOD_NAME = ".testValidate_0031()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "123";
        l_request.accountCode = "123456";
        l_request.productCode = null;
        l_request.approveState = "9";
        l_request.checker = "123";
        l_request.createDateFrom = null;
        l_request.createDateTo = "20070301";
        l_request.approveDateFrom = null;
        l_request.approveDateTo = "20070301";
        l_request.forcedSettleReason = "0";
        l_request.closeDate = new Date();
        l_request.errorReason = "0005";
        l_request.approveType = "0";
        l_request.taxType = "0";
        l_request.marketCode = "1";

        WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
        l_sort.keyItem = "contractExecPrice";
        l_sort.ascDesc = "D";
        WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
        l_request.sortKeys = l_message;

        l_request.pageIndex = "1234";
        l_request.pageSize = "12";

        try
        {
            l_request.validate();
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }

    public void testValidate_0032()
    {
        String STR_METHOD_NAME = ".testValidate_0032()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "123";
        l_request.accountCode = "123456";
        l_request.productCode = null;
        l_request.approveState = "9";
        l_request.checker = "123";
        l_request.createDateFrom = "20000601";
        l_request.createDateTo = "20070301";
        l_request.approveDateFrom = "20000601";
        l_request.approveDateTo = "20070301";
        l_request.forcedSettleReason = "0";
        l_request.closeDate = new Date();
        l_request.errorReason = "0005";
        l_request.approveType = "0";
        l_request.taxType = "0";
        l_request.marketCode = "1";

        WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
        l_sort.keyItem = "contractExecPrice";
        l_sort.ascDesc = "D";
        WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
        l_request.sortKeys = l_message;

        l_request.pageIndex = "1234";
        l_request.pageSize = "12";

        try
        {
            l_request.validate();
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }

    public void testValidate_0033()
    {
        String STR_METHOD_NAME = ".testValidate_0033()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "123";
        l_request.accountCode = "123456";
        l_request.productCode = null;
        l_request.approveState = "9";
        l_request.checker = "123";
        l_request.createDateFrom = "20000601";
        l_request.createDateTo = "20070301";
        l_request.approveDateFrom = "20002001";
        l_request.approveDateTo = "20070301";
        l_request.forcedSettleReason = "0";
        l_request.closeDate = new Date();
        l_request.errorReason = "0005";
        l_request.approveType = "0";
        l_request.taxType = "0";
        l_request.marketCode = "1";

        WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
        l_sort.keyItem = "contractExecPrice";
        l_sort.ascDesc = "D";
        WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
        l_request.sortKeys = l_message;

        l_request.pageIndex = "1234";
        l_request.pageSize = "12";

        try
        {
            l_request.validate();
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02756, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    public void testValidate_0034()
    {
        String STR_METHOD_NAME = ".testValidate_0034()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "123";
        l_request.accountCode = "123456";
        l_request.productCode = null;
        l_request.approveState = "9";
        l_request.checker = "123";
        l_request.createDateFrom = null;
        l_request.createDateTo = "20070301";
        l_request.approveDateFrom = null;
        l_request.approveDateTo = null;
        l_request.forcedSettleReason = "0";
        l_request.closeDate = new Date();
        l_request.errorReason = "0005";
        l_request.approveType = "0";
        l_request.taxType = "0";
        l_request.marketCode = "1";

        WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
        l_sort.keyItem = "contractExecPrice";
        l_sort.ascDesc = "D";
        WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
        l_request.sortKeys = l_message;

        l_request.pageIndex = "1234";
        l_request.pageSize = "12";

        try
        {
            l_request.validate();
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }

    public void testValidate_0035()
    {
        String STR_METHOD_NAME = ".testValidate_0035()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "123";
        l_request.accountCode = "123456";
        l_request.productCode = null;
        l_request.approveState = "9";
        l_request.checker = "123";
        l_request.createDateFrom = "20000601";
        l_request.createDateTo = "20070301";
        l_request.approveDateFrom = "20000601";
        l_request.approveDateTo = "20070301";
        l_request.forcedSettleReason = "0";
        l_request.closeDate = new Date();
        l_request.errorReason = "0005";
        l_request.approveType = "0";
        l_request.taxType = "0";
        l_request.marketCode = "1";

        WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
        l_sort.keyItem = "contractExecPrice";
        l_sort.ascDesc = "D";
        WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
        l_request.sortKeys = l_message;

        l_request.pageIndex = "1234";
        l_request.pageSize = "12";

        try
        {
            l_request.validate();
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }

    public void testValidate_0036()
    {
        String STR_METHOD_NAME = ".testValidate_0036()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "123";
        l_request.accountCode = "123456";
        l_request.productCode = null;
        l_request.approveState = "9";
        l_request.checker = "123";
        l_request.createDateFrom = "20000601";
        l_request.createDateTo = "20070301";
        l_request.approveDateFrom = "20000601";
        l_request.approveDateTo = "20072001";
        l_request.forcedSettleReason = "0";
        l_request.closeDate = new Date();
        l_request.errorReason = "0005";
        l_request.approveType = "0";
        l_request.taxType = "0";
        l_request.marketCode = "1";

        WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
        l_sort.keyItem = "contractExecPrice";
        l_sort.ascDesc = "D";
        WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
        l_request.sortKeys = l_message;

        l_request.pageIndex = "1234";
        l_request.pageSize = "12";

        try
        {
            l_request.validate();
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02757, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    public void testValidate_0037()
    {
        String STR_METHOD_NAME = ".testValidate_0037()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "123";
        l_request.accountCode = "123456";
        l_request.productCode = null;
        l_request.approveState = "9";
        l_request.checker = "123";
        l_request.createDateFrom = null;
        l_request.createDateTo = null;
        l_request.approveDateFrom = null;
        l_request.approveDateTo = null;
        l_request.forcedSettleReason = "0";
        l_request.closeDate = new Date();
        l_request.errorReason = "0005";
        l_request.approveType = "0";
        l_request.taxType = "0";
        l_request.marketCode = "1";

        WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
        l_sort.keyItem = "contractExecPrice";
        l_sort.ascDesc = "D";
        WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
        l_request.sortKeys = l_message;

        l_request.pageIndex = "1234";
        l_request.pageSize = "12";

        try
        {
            l_request.validate();
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    public void testValidate_0038()
    {
        String STR_METHOD_NAME = ".testValidate_0038()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "123";
        l_request.accountCode = "123456";
        l_request.productCode = null;
        l_request.approveState = "9";
        l_request.checker = "123";
        l_request.createDateFrom = null;
        l_request.createDateTo = "20070301";
        l_request.approveDateFrom = null;
        l_request.approveDateTo = "20070301";
        l_request.forcedSettleReason = "0";
        l_request.closeDate = new Date();
        l_request.errorReason = "0005";
        l_request.approveType = "0";
        l_request.taxType = "0";
        l_request.marketCode = "1";

        WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
        l_sort.keyItem = "contractExecPrice";
        l_sort.ascDesc = "D";
        WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
        l_request.sortKeys = l_message;

        l_request.pageIndex = "1234";
        l_request.pageSize = "12";

        try
        {
            l_request.validate();
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    public void testValidate_0039()
    {
        String STR_METHOD_NAME = ".testValidate_0039()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "123";
        l_request.accountCode = "123456";
        l_request.productCode = null;
        l_request.approveState = "9";
        l_request.checker = "123";
        l_request.createDateFrom = "20000601";
        l_request.createDateTo = null;
        l_request.approveDateFrom = "20000601";
        l_request.approveDateTo = null;
        l_request.forcedSettleReason = "0";
        l_request.closeDate = new Date();
        l_request.errorReason = "0005";
        l_request.approveType = "0";
        l_request.taxType = "0";
        l_request.marketCode = "1";

        WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
        l_sort.keyItem = "contractExecPrice";
        l_sort.ascDesc = "D";
        WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
        l_request.sortKeys = l_message;

        l_request.pageIndex = "1234";
        l_request.pageSize = "12";

        try
        {
            l_request.validate();
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    public void testValidate_0040()
    {
        String STR_METHOD_NAME = ".testValidate_0040()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "123";
        l_request.accountCode = "123456";
        l_request.productCode = null;
        l_request.approveState = "9";
        l_request.checker = "123";
        l_request.createDateFrom = "20000601";
        l_request.createDateTo = "20070301";
        l_request.approveDateFrom = "20000601";
        l_request.approveDateTo = "20070301";
        l_request.forcedSettleReason = "0";
        l_request.closeDate = new Date();
        l_request.errorReason = "0005";
        l_request.approveType = "0";
        l_request.taxType = "0";
        l_request.marketCode = "1";

        WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
        l_sort.keyItem = "contractExecPrice";
        l_sort.ascDesc = "D";
        WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
        l_request.sortKeys = l_message;

        l_request.pageIndex = "1234";
        l_request.pageSize = "12";

        try
        {
            l_request.validate();
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    public void testValidate_0041()
    {
        String STR_METHOD_NAME = ".testValidate_0041()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "123";
        l_request.accountCode = "123456";
        l_request.productCode = null;
        l_request.approveState = "9";
        l_request.checker = "123";
        l_request.createDateFrom = "20000601";
        l_request.createDateTo = "20000601";
        l_request.approveDateFrom = "20000601";
        l_request.approveDateTo = "20000601";
        l_request.forcedSettleReason = "0";
        l_request.closeDate = new Date();
        l_request.errorReason = "0005";
        l_request.approveType = "0";
        l_request.taxType = "0";
        l_request.marketCode = "1";

        WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
        l_sort.keyItem = "contractExecPrice";
        l_sort.ascDesc = "D";
        WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
        l_request.sortKeys = l_message;

        l_request.pageIndex = "1234";
        l_request.pageSize = "12";

        try
        {
            l_request.validate();
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    public void testValidate_0042()
    {
        String STR_METHOD_NAME = ".testValidate_0042()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "123";
        l_request.accountCode = "123456";
        l_request.productCode = null;
        l_request.approveState = "9";
        l_request.checker = "123";
        l_request.createDateFrom = "20070601";
        l_request.createDateTo = "20000601";
        l_request.approveDateFrom = "20070601";
        l_request.approveDateTo = "20000601";
        l_request.forcedSettleReason = "0";
        l_request.closeDate = new Date();
        l_request.errorReason = "0005";
        l_request.approveType = "0";
        l_request.taxType = "0";
        l_request.marketCode = "1";

        WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
        l_sort.keyItem = "contractExecPrice";
        l_sort.ascDesc = "D";
        WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
        l_request.sortKeys = l_message;

        l_request.pageIndex = "1234";
        l_request.pageSize = "12";

        try
        {
            l_request.validate();
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02715,l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    public void testValidate_0043()
    {
        String STR_METHOD_NAME = ".testValidate_0043()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "123";
        l_request.accountCode = "123456";
        l_request.productCode = null;
        l_request.approveState = null;
        l_request.checker ="123";
        l_request.createDateFrom = "20000601";
        l_request.createDateTo = "20070301";
        l_request.approveDateFrom = "20000601";
        l_request.approveDateTo = "20070301";
        l_request.forcedSettleReason = null;
        l_request.closeDate = new Date();
        l_request.errorReason = "0005";
        l_request.approveType = "0";
        l_request.taxType = "0";
        l_request.marketCode = "1";
        
        WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
        l_sort.keyItem ="contractExecPrice";
        l_sort.ascDesc ="D";
        WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
        l_request.sortKeys = l_message;
        
        l_request.pageIndex = "1234";
        l_request.pageSize = "12";
        
        try
        {
            l_request.validate();
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
    }
    
    public void testValidate_0044()
    {
        String STR_METHOD_NAME = ".testValidate_0044()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "123";
        l_request.accountCode = "123456";
        l_request.productCode = null;
        l_request.approveState = "0";
        l_request.checker ="123";
        l_request.createDateFrom = "20000601";
        l_request.createDateTo = "20070301";
        l_request.approveDateFrom = "20000601";
        l_request.approveDateTo = "20070301";
        l_request.forcedSettleReason = "0";
        l_request.closeDate = new Date();
        l_request.errorReason = "0005";
        l_request.approveType = "0";
        l_request.taxType = "0";
        l_request.marketCode = "1";
        
        WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
        l_sort.keyItem ="contractExecPrice";
        l_sort.ascDesc ="D";
        WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
        l_request.sortKeys = l_message;
        
        l_request.pageIndex = "1234";
        l_request.pageSize = "12";
        
        try
        {
            l_request.validate();
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
    }
    
    public void testValidate_0045()
    {
        String STR_METHOD_NAME = ".testValidate_0045()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "123";
        l_request.accountCode = "123456";
        l_request.productCode = null;
        l_request.approveState = "1";
        l_request.checker ="123";
        l_request.createDateFrom = "20000601";
        l_request.createDateTo = "20070301";
        l_request.approveDateFrom = "20000601";
        l_request.approveDateTo = "20070301";
        l_request.forcedSettleReason = "1";
        l_request.closeDate = new Date();
        l_request.errorReason = "0005";
        l_request.approveType = "0";
        l_request.taxType = "0";
        l_request.marketCode = "1";
        
        WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
        l_sort.keyItem ="contractExecPrice";
        l_sort.ascDesc ="D";
        WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
        l_request.sortKeys = l_message;
        
        l_request.pageIndex = "1234";
        l_request.pageSize = "12";
        
        try
        {
            l_request.validate();
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
    }
    
    public void testValidate_0046()
    {
        String STR_METHOD_NAME = ".testValidate_0046()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "123";
        l_request.accountCode = "123456";
        l_request.productCode = null;
        l_request.approveState = "2";
        l_request.checker ="123";
        l_request.createDateFrom = "20000601";
        l_request.createDateTo = "20070301";
        l_request.approveDateFrom = "20000601";
        l_request.approveDateTo = "20070301";
        l_request.forcedSettleReason = "2";
        l_request.closeDate = new Date();
        l_request.errorReason = "0005";
        l_request.approveType = "0";
        l_request.taxType = "0";
        l_request.marketCode = "1";
        
        WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
        l_sort.keyItem ="contractExecPrice";
        l_sort.ascDesc ="D";
        WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
        l_request.sortKeys = l_message;
        
        l_request.pageIndex = "1234";
        l_request.pageSize = "12";
        
        try
        {
            l_request.validate();
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
    }
    
    public void testValidate_0047()
    {
        String STR_METHOD_NAME = ".testValidate_0047()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "123";
        l_request.accountCode = "123456";
        l_request.productCode = null;
        l_request.approveState = "9";
        l_request.checker ="123";
        l_request.createDateFrom = "20000601";
        l_request.createDateTo = "20070301";
        l_request.approveDateFrom = "20000601";
        l_request.approveDateTo = "20070301";
        l_request.forcedSettleReason = "9";
        l_request.closeDate = new Date();
        l_request.errorReason = "0005";
        l_request.approveType = "0";
        l_request.taxType = "0";
        l_request.marketCode = "1";
        
        WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
        l_sort.keyItem ="contractExecPrice";
        l_sort.ascDesc ="D";
        WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
        l_request.sortKeys = l_message;
        
        l_request.pageIndex = "1234";
        l_request.pageSize = "12";
        
        try
        {
            l_request.validate();
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
    }
    
    public void testValidate_0048()
    {
        String STR_METHOD_NAME = ".testValidate_0048()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "123";
        l_request.accountCode = "123456";
        l_request.productCode = null;
        l_request.approveState = "0";
        l_request.checker ="123";
        l_request.createDateFrom = "20000601";
        l_request.createDateTo = "20070301";
        l_request.approveDateFrom = "20000601";
        l_request.approveDateTo = "20070301";
        l_request.forcedSettleReason = "7";
        l_request.closeDate = new Date();
        l_request.errorReason = "0005";
        l_request.approveType = "0";
        l_request.taxType = "0";
        l_request.marketCode = "1";
        
        WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
        l_sort.keyItem ="contractExecPrice";
        l_sort.ascDesc ="D";
        WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
        l_request.sortKeys = l_message;
        
        l_request.pageIndex = "1234";
        l_request.pageSize = "12";
        
        try
        {
            l_request.validate();
            log.exiting(TEST_END + STR_METHOD_NAME);
        
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02758,l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
    }

    public void testValidate_0049()
    {
        String STR_METHOD_NAME = ".testValidate_0049()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "123";
        l_request.accountCode = "123456";
        l_request.productCode = null;
        l_request.approveState = null;
        l_request.checker ="123";
        l_request.createDateFrom = "20000601";
        l_request.createDateTo = "20070301";
        l_request.approveDateFrom = "20000601";
        l_request.approveDateTo = "20070301";
        l_request.forcedSettleReason = null;
        l_request.closeDate = new Date();
        l_request.errorReason = null;
        l_request.approveType = "0";
        l_request.taxType = "0";
        l_request.marketCode = "1";
        
        WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
        l_sort.keyItem ="contractExecPrice";
        l_sort.ascDesc ="D";
        WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
        l_request.sortKeys = l_message;
        
        l_request.pageIndex = "1234";
        l_request.pageSize = "12";
        
        try
        {
            l_request.validate();
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
    }
    
    public void testValidate_0050()
    {
        String STR_METHOD_NAME = ".testValidate_0050()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "123";
        l_request.accountCode = "123456";
        l_request.productCode = null;
        l_request.approveState = "0";
        l_request.checker ="123";
        l_request.createDateFrom = "20000601";
        l_request.createDateTo = "20070301";
        l_request.approveDateFrom = "20000601";
        l_request.approveDateTo = "20070301";
        l_request.forcedSettleReason = "0";
        l_request.closeDate = new Date();
        l_request.errorReason = "0005";
        l_request.approveType = "0";
        l_request.taxType = "0";
        l_request.marketCode = "1";
        
        WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
        l_sort.keyItem ="contractExecPrice";
        l_sort.ascDesc ="D";
        WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
        l_request.sortKeys = l_message;
        
        l_request.pageIndex = "1234";
        l_request.pageSize = "12";
        
        try
        {
            l_request.validate();
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
    }
    
    public void testValidate_0051()
    {
        String STR_METHOD_NAME = ".testValidate_0051()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "123";
        l_request.accountCode = "123456";
        l_request.productCode = null;
        l_request.approveState = "1";
        l_request.checker ="123";
        l_request.createDateFrom = "20000601";
        l_request.createDateTo = "20070301";
        l_request.approveDateFrom = "20000601";
        l_request.approveDateTo = "20070301";
        l_request.forcedSettleReason = "1";
        l_request.closeDate = new Date();
        l_request.errorReason = "0006";
        l_request.approveType = "0";
        l_request.taxType = "0";
        l_request.marketCode = "1";
        
        WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
        l_sort.keyItem ="contractExecPrice";
        l_sort.ascDesc ="D";
        WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
        l_request.sortKeys = l_message;
        
        l_request.pageIndex = "1234";
        l_request.pageSize = "12";
        
        try
        {
            l_request.validate();
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
    }
    
    public void testValidate_0052()
    {
        String STR_METHOD_NAME = ".testValidate_0052()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "123";
        l_request.accountCode = "123456";
        l_request.productCode = null;
        l_request.approveState = "2";
        l_request.checker ="123";
        l_request.createDateFrom = "20000601";
        l_request.createDateTo = "20070301";
        l_request.approveDateFrom = "20000601";
        l_request.approveDateTo = "20070301";
        l_request.forcedSettleReason = "2";
        l_request.closeDate = new Date();
        l_request.errorReason = "0016";
        l_request.approveType = "0";
        l_request.taxType = "0";
        l_request.marketCode = "1";
        
        WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
        l_sort.keyItem ="contractExecPrice";
        l_sort.ascDesc ="D";
        WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
        l_request.sortKeys = l_message;
        
        l_request.pageIndex = "1234";
        l_request.pageSize = "12";
        
        try
        {
            l_request.validate();
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
    }
    
    public void testValidate_0053()
    {
        String STR_METHOD_NAME = ".testValidate_0053()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "123";
        l_request.accountCode = "123456";
        l_request.productCode = null;
        l_request.approveState = "9";
        l_request.checker ="123";
        l_request.createDateFrom = "20000601";
        l_request.createDateTo = "20070301";
        l_request.approveDateFrom = "20000601";
        l_request.approveDateTo = "20070301";
        l_request.forcedSettleReason = "9";
        l_request.closeDate = new Date();
        l_request.errorReason = "0017";
        l_request.approveType = "0";
        l_request.taxType = "0";
        l_request.marketCode = "1";
        
        WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
        l_sort.keyItem ="contractExecPrice";
        l_sort.ascDesc ="D";
        WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
        l_request.sortKeys = l_message;
        
        l_request.pageIndex = "1234";
        l_request.pageSize = "12";
        
        try
        {
            l_request.validate();
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
    }

    public void testValidate_0054()
    {
        String STR_METHOD_NAME = ".testValidate_0054()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "123";
        l_request.accountCode = "123456";
        l_request.productCode = null;
        l_request.approveState = "9";
        l_request.checker ="123";
        l_request.createDateFrom = "20000601";
        l_request.createDateTo = "20070301";
        l_request.approveDateFrom = "20000601";
        l_request.approveDateTo = "20070301";
        l_request.forcedSettleReason = "9";
        l_request.closeDate = new Date();
        l_request.errorReason = "9001";
        l_request.approveType = "0";
        l_request.taxType = "0";
        l_request.marketCode = "1";
        
        WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
        l_sort.keyItem ="contractExecPrice";
        l_sort.ascDesc ="D";
        WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
        l_request.sortKeys = l_message;
        
        l_request.pageIndex = "1234";
        l_request.pageSize = "12";
        
        try
        {
            l_request.validate();
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
    }

    public void testValidate_0055()
    {
        String STR_METHOD_NAME = ".testValidate_0055()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "123";
        l_request.accountCode = "123456";
        l_request.productCode = null;
        l_request.approveState = "0";
        l_request.checker ="123";
        l_request.createDateFrom = "20000601";
        l_request.createDateTo = "20070301";
        l_request.approveDateFrom = "20000601";
        l_request.approveDateTo = "20070301";
        l_request.forcedSettleReason = null;
        l_request.closeDate = new Date();
        l_request.errorReason = "abc";
        l_request.approveType = "0";
        l_request.taxType = "0";
        l_request.marketCode = "1";
        
        WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
        l_sort.keyItem ="contractExecPrice";
        l_sort.ascDesc ="D";
        WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
        l_request.sortKeys = l_message;
        
        l_request.pageIndex = "1234";
        l_request.pageSize = "12";
        
        try
        {
            l_request.validate();
            log.exiting(TEST_END + STR_METHOD_NAME);
        
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02759,l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
    }
    
    public void testValidate_0056()
    {
        String STR_METHOD_NAME = ".testValidate_0056()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "123";
        l_request.accountCode = "123456";
        l_request.productCode = null;
        l_request.approveState = "2";
        l_request.checker ="123";
        l_request.createDateFrom = "20000601";
        l_request.createDateTo = "20070301";
        l_request.approveDateFrom = "20000601";
        l_request.approveDateTo = "20070301";
        l_request.forcedSettleReason = "2";
        l_request.closeDate = new Date();
        l_request.errorReason = "0016";
        l_request.approveType = null;
        l_request.taxType = "0";
        l_request.marketCode = "1";
        
        WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
        l_sort.keyItem ="contractExecPrice";
        l_sort.ascDesc ="D";
        WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
        l_request.sortKeys = l_message;
        
        l_request.pageIndex = "1234";
        l_request.pageSize = "12";
        
        try
        {
            l_request.validate();
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
    }
    
    public void testValidate_0057()
    {
        String STR_METHOD_NAME = ".testValidate_0057()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "123";
        l_request.accountCode = "123456";
        l_request.productCode = null;
        l_request.approveState = "9";
        l_request.checker ="123";
        l_request.createDateFrom = "20000601";
        l_request.createDateTo = "20070301";
        l_request.approveDateFrom = "20000601";
        l_request.approveDateTo = "20070301";
        l_request.forcedSettleReason = "9";
        l_request.closeDate = new Date();
        l_request.errorReason = "0017";
        l_request.approveType = "0";
        l_request.taxType = "0";
        l_request.marketCode = "1";
        
        WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
        l_sort.keyItem ="contractExecPrice";
        l_sort.ascDesc ="D";
        WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
        l_request.sortKeys = l_message;
        
        l_request.pageIndex = "1234";
        l_request.pageSize = "12";
        
        try
        {
            l_request.validate();
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
    }

    public void testValidate_0058()
    {
        String STR_METHOD_NAME = ".testValidate_0058()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "123";
        l_request.accountCode = "123456";
        l_request.productCode = null;
        l_request.approveState = "9";
        l_request.checker ="123";
        l_request.createDateFrom = "20000601";
        l_request.createDateTo = "20070301";
        l_request.approveDateFrom = "20000601";
        l_request.approveDateTo = "20070301";
        l_request.forcedSettleReason = "9";
        l_request.closeDate = new Date();
        l_request.errorReason = "9001";
        l_request.approveType = "1";
        l_request.taxType = "0";
        l_request.marketCode = "1";
        
        WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
        l_sort.keyItem ="contractExecPrice";
        l_sort.ascDesc ="D";
        WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
        l_request.sortKeys = l_message;
        
        l_request.pageIndex = "1234";
        l_request.pageSize = "12";
        
        try
        {
            l_request.validate();
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
    }

    public void testValidate_0059()
    {
        String STR_METHOD_NAME = ".testValidate_0059()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "123";
        l_request.accountCode = "123456";
        l_request.productCode = null;
        l_request.approveState = "0";
        l_request.checker ="123";
        l_request.createDateFrom = "20000601";
        l_request.createDateTo = "20070301";
        l_request.approveDateFrom = "20000601";
        l_request.approveDateTo = "20070301";
        l_request.forcedSettleReason = null;
        l_request.closeDate = new Date();
        l_request.errorReason = "9001";
        l_request.approveType = "abc";
        l_request.taxType = "0";
        l_request.marketCode = "1";
        
        WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
        l_sort.keyItem ="contractExecPrice";
        l_sort.ascDesc ="D";
        WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
        l_request.sortKeys = l_message;
        
        l_request.pageIndex = "1234";
        l_request.pageSize = "12";
        
        try
        {
            l_request.validate();
            log.exiting(TEST_END + STR_METHOD_NAME);
        
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02760,l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
    }
    
    public void testValidate_0060()
    {
        String STR_METHOD_NAME = ".testValidate_0060()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "123";
        l_request.accountCode = "123456";
        l_request.productCode = null;
        l_request.approveState = "2";
        l_request.checker ="123";
        l_request.createDateFrom = "20000601";
        l_request.createDateTo = "20070301";
        l_request.approveDateFrom = "20000601";
        l_request.approveDateTo = "20070301";
        l_request.forcedSettleReason = "2";
        l_request.closeDate = new Date();
        l_request.errorReason = "0016";
        l_request.approveType = null;
        l_request.taxType = null;
        l_request.marketCode = "1";
        
        WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
        l_sort.keyItem ="contractExecPrice";
        l_sort.ascDesc ="D";
        WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
        l_request.sortKeys = l_message;
        
        l_request.pageIndex = "1234";
        l_request.pageSize = "12";
        
        try
        {
            l_request.validate();
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
    }
    
    public void testValidate_0061()
    {
        String STR_METHOD_NAME = ".testValidate_0061()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "123";
        l_request.accountCode = "123456";
        l_request.productCode = null;
        l_request.approveState = "9";
        l_request.checker ="123";
        l_request.createDateFrom = "20000601";
        l_request.createDateTo = "20070301";
        l_request.approveDateFrom = "20000601";
        l_request.approveDateTo = "20070301";
        l_request.forcedSettleReason = "9";
        l_request.closeDate = new Date();
        l_request.errorReason = "0017";
        l_request.approveType = "0";
        l_request.taxType = "0";
        l_request.marketCode = "1";
        
        WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
        l_sort.keyItem ="contractExecPrice";
        l_sort.ascDesc ="D";
        WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
        l_request.sortKeys = l_message;
        
        l_request.pageIndex = "1234";
        l_request.pageSize = "12";
        
        try
        {
            l_request.validate();
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
    }

    public void testValidate_0062()
    {
        String STR_METHOD_NAME = ".testValidate_0062()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "123";
        l_request.accountCode = "123456";
        l_request.productCode = null;
        l_request.approveState = "9";
        l_request.checker ="123";
        l_request.createDateFrom = "20000601";
        l_request.createDateTo = "20070301";
        l_request.approveDateFrom = "20000601";
        l_request.approveDateTo = "20070301";
        l_request.forcedSettleReason = "9";
        l_request.closeDate = new Date();
        l_request.errorReason = "9001";
        l_request.approveType = "1";
        l_request.taxType = "1";
        l_request.marketCode = "1";
        
        WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
        l_sort.keyItem ="contractExecPrice";
        l_sort.ascDesc ="D";
        WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
        l_request.sortKeys = l_message;
        
        l_request.pageIndex = "1234";
        l_request.pageSize = "12";
        
        try
        {
            l_request.validate();
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
    }

    public void testValidate_0063()
    {
        String STR_METHOD_NAME = ".testValidate_0063()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "123";
        l_request.accountCode = "123456";
        l_request.productCode = null;
        l_request.approveState = "0";
        l_request.checker ="123";
        l_request.createDateFrom = "20000601";
        l_request.createDateTo = "20070301";
        l_request.approveDateFrom = "20000601";
        l_request.approveDateTo = "20070301";
        l_request.forcedSettleReason = null;
        l_request.closeDate = new Date();
        l_request.errorReason = "9001";
        l_request.approveType = "0";
        l_request.taxType = "abc";
        l_request.marketCode = "1";
        
        WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
        l_sort.keyItem ="contractExecPrice";
        l_sort.ascDesc ="D";
        WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
        l_request.sortKeys = l_message;
        
        l_request.pageIndex = "1234";
        l_request.pageSize = "12";
        
        try
        {
            l_request.validate();
            log.exiting(TEST_END + STR_METHOD_NAME);
        
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01303,l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
    }
    
    public void testValidate_0064()
    {
        String STR_METHOD_NAME = ".testValidate_0064()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "123";
        l_request.accountCode = "123456";
        l_request.productCode = null;
        l_request.approveState = "9";
        l_request.checker ="123";
        l_request.createDateFrom = "20000601";
        l_request.createDateTo = "20070301";
        l_request.approveDateFrom = "20000601";
        l_request.approveDateTo = "20070301";
        l_request.forcedSettleReason = "9";
        l_request.closeDate = new Date();
        l_request.errorReason = "0017";
        l_request.approveType = "0";
        l_request.taxType = "0";
        l_request.marketCode = null;
        
        WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
        l_sort.keyItem ="contractExecPrice";
        l_sort.ascDesc ="D";
        WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
        l_request.sortKeys = l_message;
        
        l_request.pageIndex = "1234";
        l_request.pageSize = "12";
        
        try
        {
            l_request.validate();
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
    }

    public void testValidate_0065()
    {
        String STR_METHOD_NAME = ".testValidate_0065()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "123";
        l_request.accountCode = "123456";
        l_request.productCode = null;
        l_request.approveState = "9";
        l_request.checker ="123";
        l_request.createDateFrom = "20000601";
        l_request.createDateTo = "20070301";
        l_request.approveDateFrom = "20000601";
        l_request.approveDateTo = "20070301";
        l_request.forcedSettleReason = "9";
        l_request.closeDate = new Date();
        l_request.errorReason = "9001";
        l_request.approveType = "1";
        l_request.taxType = "1";
        l_request.marketCode = "1";
        
        WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
        l_sort.keyItem ="contractExecPrice";
        l_sort.ascDesc ="D";
        WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
        l_request.sortKeys = l_message;
        
        l_request.pageIndex = "1234";
        l_request.pageSize = "12";
        
        try
        {
            l_request.validate();
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
    }
    
    public void testValidate_0066()
    {
        String STR_METHOD_NAME = ".testValidate_0066()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "123";
        l_request.accountCode = "123456";
        l_request.productCode = null;
        l_request.approveState = "9";
        l_request.checker ="123";
        l_request.createDateFrom = "20000601";
        l_request.createDateTo = "20070301";
        l_request.approveDateFrom = "20000601";
        l_request.approveDateTo = "20070301";
        l_request.forcedSettleReason = "9";
        l_request.closeDate = new Date();
        l_request.errorReason = "0017";
        l_request.approveType = "0";
        l_request.taxType = "0";
        l_request.marketCode = "2";
        
        WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
        l_sort.keyItem ="contractExecPrice";
        l_sort.ascDesc ="D";
        WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
        l_request.sortKeys = l_message;
        
        l_request.pageIndex = "1234";
        l_request.pageSize = "12";
        
        try
        {
            l_request.validate();
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
    }

    public void testValidate_0067()
    {
        String STR_METHOD_NAME = ".testValidate_0067()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "123";
        l_request.accountCode = "123456";
        l_request.productCode = null;
        l_request.approveState = "9";
        l_request.checker ="123";
        l_request.createDateFrom = "20000601";
        l_request.createDateTo = "20070301";
        l_request.approveDateFrom = "20000601";
        l_request.approveDateTo = "20070301";
        l_request.forcedSettleReason = "9";
        l_request.closeDate = new Date();
        l_request.errorReason = "9001";
        l_request.approveType = "1";
        l_request.taxType = "1";
        l_request.marketCode = "3";
        
        WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
        l_sort.keyItem ="contractExecPrice";
        l_sort.ascDesc ="D";
        WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
        l_request.sortKeys = l_message;
        
        l_request.pageIndex = "1234";
        l_request.pageSize = "12";
        
        try
        {
            l_request.validate();
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
    }
    
    public void testValidate_0068()
    {
        String STR_METHOD_NAME = ".testValidate_0068()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "123";
        l_request.accountCode = "123456";
        l_request.productCode = null;
        l_request.approveState = "9";
        l_request.checker ="123";
        l_request.createDateFrom = "20000601";
        l_request.createDateTo = "20070301";
        l_request.approveDateFrom = "20000601";
        l_request.approveDateTo = "20070301";
        l_request.forcedSettleReason = "9";
        l_request.closeDate = new Date();
        l_request.errorReason = "0017";
        l_request.approveType = "0";
        l_request.taxType = "0";
        l_request.marketCode = "9";
        
        WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
        l_sort.keyItem ="contractExecPrice";
        l_sort.ascDesc ="D";
        WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
        l_request.sortKeys = l_message;
        
        l_request.pageIndex = "1234";
        l_request.pageSize = "12";
        
        try
        {
            l_request.validate();
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
    }

    public void testValidate_0069()
    {
        String STR_METHOD_NAME = ".testValidate_0069()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "123";
        l_request.accountCode = "123456";
        l_request.productCode = null;
        l_request.approveState = "9";
        l_request.checker ="123";
        l_request.createDateFrom = "20000601";
        l_request.createDateTo = "20070301";
        l_request.approveDateFrom = "20000601";
        l_request.approveDateTo = "20070301";
        l_request.forcedSettleReason = "9";
        l_request.closeDate = new Date();
        l_request.errorReason = "9001";
        l_request.approveType = "1";
        l_request.taxType = "1";
        l_request.marketCode = "4";
        
        WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
        l_sort.keyItem ="contractExecPrice";
        l_sort.ascDesc ="D";
        WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
        l_request.sortKeys = l_message;
        
        l_request.pageIndex = "1234";
        l_request.pageSize = "12";
        
        try
        {
            l_request.validate();
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
    }
    
    public void testValidate_0070()
    {
        String STR_METHOD_NAME = ".testValidate_0070()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "123";
        l_request.accountCode = "123456";
        l_request.productCode = null;
        l_request.approveState = "9";
        l_request.checker ="123";
        l_request.createDateFrom = "20000601";
        l_request.createDateTo = "20070301";
        l_request.approveDateFrom = "20000601";
        l_request.approveDateTo = "20070301";
        l_request.forcedSettleReason = "9";
        l_request.closeDate = new Date();
        l_request.errorReason = "0017";
        l_request.approveType = "0";
        l_request.taxType = "0";
        l_request.marketCode = "5";
        
        WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
        l_sort.keyItem ="contractExecPrice";
        l_sort.ascDesc ="D";
        WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
        l_request.sortKeys = l_message;
        
        l_request.pageIndex = "1234";
        l_request.pageSize = "12";
        
        try
        {
            l_request.validate();
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
    }

    public void testValidate_0071()
    {
        String STR_METHOD_NAME = ".testValidate_0071()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "123";
        l_request.accountCode = "123456";
        l_request.productCode = null;
        l_request.approveState = "9";
        l_request.checker ="123";
        l_request.createDateFrom = "20000601";
        l_request.createDateTo = "20070301";
        l_request.approveDateFrom = "20000601";
        l_request.approveDateTo = "20070301";
        l_request.forcedSettleReason = "9";
        l_request.closeDate = new Date();
        l_request.errorReason = "9001";
        l_request.approveType = "1";
        l_request.taxType = "1";
        l_request.marketCode = "6";
        
        WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
        l_sort.keyItem ="contractExecPrice";
        l_sort.ascDesc ="D";
        WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
        l_request.sortKeys = l_message;
        
        l_request.pageIndex = "1234";
        l_request.pageSize = "12";
        
        try
        {
            l_request.validate();
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
    }

    public void testValidate_0072()
    {
        String STR_METHOD_NAME = ".testValidate_0072()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "123";
        l_request.accountCode = "123456";
        l_request.productCode = null;
        l_request.approveState = "0";
        l_request.checker ="123";
        l_request.createDateFrom = "20000601";
        l_request.createDateTo = "20070301";
        l_request.approveDateFrom = "20000601";
        l_request.approveDateTo = "20070301";
        l_request.forcedSettleReason = null;
        l_request.closeDate = new Date();
        l_request.errorReason = "9001";
        l_request.approveType = "0";
        l_request.taxType = "0";
        l_request.marketCode = "abc";
        
        WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
        l_sort.keyItem ="contractExecPrice";
        l_sort.ascDesc ="D";
        WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
        l_request.sortKeys = l_message;
        
        l_request.pageIndex = "1234";
        l_request.pageSize = "12";
        
        try
        {
            l_request.validate();
            log.exiting(TEST_END + STR_METHOD_NAME);
        
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00608,l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
    }

    public void testValidate_0073()
    {
        String STR_METHOD_NAME = ".testValidate_0073()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "123";
        l_request.accountCode = "123456";
        l_request.productCode = null;
        l_request.approveState = "0";
        l_request.checker ="123";
        l_request.createDateFrom = "20000601";
        l_request.createDateTo = "20070301";
        l_request.approveDateFrom = "20000601";
        l_request.approveDateTo = "20070301";
        l_request.forcedSettleReason = null;
        l_request.closeDate = new Date();
        l_request.errorReason = "9001";
        l_request.approveType = "0";
        l_request.taxType = "0";
        l_request.marketCode = "5";
        l_request.sortKeys = null;
        l_request.pageIndex = "1234";
        l_request.pageSize = "12";
        
        try
        {
            l_request.validate();
            log.exiting(TEST_END + STR_METHOD_NAME);
        
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00231,l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
    }
    
    public void testValidate_0074()
    {
        String STR_METHOD_NAME = ".testValidate_0074()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "123";
        l_request.accountCode = "123456";
        l_request.productCode = null;
        l_request.approveState = "9";
        l_request.checker ="123";
        l_request.createDateFrom = "20000601";
        l_request.createDateTo = "20070301";
        l_request.approveDateFrom = "20000601";
        l_request.approveDateTo = "20070301";
        l_request.forcedSettleReason = "9";
        l_request.closeDate = new Date();
        l_request.errorReason = "9001";
        l_request.approveType = "1";
        l_request.taxType = "1";
        l_request.marketCode = "6";

        WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
        l_sort.keyItem ="contractExecPrice";
        l_sort.ascDesc ="D";
        WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
        l_request.sortKeys = l_message;
        
        l_request.pageIndex = "1234";
        l_request.pageSize = "12";
        
        try
        {
            l_request.validate();
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
    }
    
    public void testValidate_0075()
    {
        String STR_METHOD_NAME = ".testValidate_0075()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "123";
        l_request.accountCode = "123456";
        l_request.productCode = null;
        l_request.approveState = "9";
        l_request.checker ="123";
        l_request.createDateFrom = "20000601";
        l_request.createDateTo = "20070301";
        l_request.approveDateFrom = "20000601";
        l_request.approveDateTo = "20070301";
        l_request.forcedSettleReason = "9";
        l_request.closeDate = new Date();
        l_request.errorReason = "9001";
        l_request.approveType = "1";
        l_request.taxType = "1";
        l_request.marketCode = "6";
        
        WEB3AdminForcedSettleSortKeyUnit l_sortOne = new WEB3AdminForcedSettleSortKeyUnit();
        l_sortOne.keyItem ="contractExecPrice";
        l_sortOne.ascDesc ="D";
        WEB3AdminForcedSettleSortKeyUnit l_sorTwo = new WEB3AdminForcedSettleSortKeyUnit();
        l_sorTwo.keyItem ="contractExecPrice";
        l_sorTwo.ascDesc ="A";
        WEB3AdminForcedSettleSortKeyUnit l_sorThree = new WEB3AdminForcedSettleSortKeyUnit();
        l_sorThree.keyItem ="contractExecPrice";
        l_sorThree.ascDesc ="A";
        WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sortOne,l_sorTwo,l_sorThree};
        l_request.sortKeys = l_message;
        
        l_request.pageIndex = "1234";
        l_request.pageSize = "12";
        
        try
        {
            l_request.validate();
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
    }
    
    public void testValidate_0076()
    {
        String STR_METHOD_NAME = ".testValidate_0076()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "123";
        l_request.accountCode = "123456";
        l_request.productCode = null;
        l_request.approveState = "9";
        l_request.checker ="123";
        l_request.createDateFrom = "20000601";
        l_request.createDateTo = "20070301";
        l_request.approveDateFrom = "20000601";
        l_request.approveDateTo = "20070301";
        l_request.forcedSettleReason = "9";
        l_request.closeDate = new Date();
        l_request.errorReason = "9001";
        l_request.approveType = "1";
        l_request.taxType = "1";
        l_request.marketCode = "6";
        
        WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
        l_sort.keyItem ="contractExecPrice";
        l_sort.ascDesc ="D";
        WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
        l_request.sortKeys = l_message;
        
        l_request.pageIndex = null;
        l_request.pageSize = "12";
        
        try
        {
            l_request.validate();
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00089,l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
    }
    
    public void testValidate_0077()
    {
        String STR_METHOD_NAME = ".testValidate_0077()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "123";
        l_request.accountCode = "123456";
        l_request.productCode = null;
        l_request.approveState = "9";
        l_request.checker ="123";
        l_request.createDateFrom = "20000601";
        l_request.createDateTo = "20070301";
        l_request.approveDateFrom = "20000601";
        l_request.approveDateTo = "20070301";
        l_request.forcedSettleReason = "9";
        l_request.closeDate = new Date();
        l_request.errorReason = "9001";
        l_request.approveType = "1";
        l_request.taxType = "1";
        l_request.marketCode = "6";
        
        WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
        l_sort.keyItem ="contractExecPrice";
        l_sort.ascDesc ="D";
        WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
        l_request.sortKeys = l_message;
        
        l_request.pageIndex = "12";
        l_request.pageSize = "12";
        
        try
        {
            l_request.validate();
            log.exiting(TEST_END + STR_METHOD_NAME);
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
    }
    
    public void testValidate_0078()
    {
        String STR_METHOD_NAME = ".testValidate_0078()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "123";
        l_request.accountCode = "123456";
        l_request.productCode = null;
        l_request.approveState = "9";
        l_request.checker ="123";
        l_request.createDateFrom = "20000601";
        l_request.createDateTo = "20070301";
        l_request.approveDateFrom = "20000601";
        l_request.approveDateTo = "20070301";
        l_request.forcedSettleReason = "9";
        l_request.closeDate = new Date();
        l_request.errorReason = "9001";
        l_request.approveType = "1";
        l_request.taxType = "1";
        l_request.marketCode = "6";
        
        WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
        l_sort.keyItem ="contractExecPrice";
        l_sort.ascDesc ="D";
        WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
        l_request.sortKeys = l_message;
        
        l_request.pageIndex = "-12";
        l_request.pageSize = "12";
        
        try
        {
            l_request.validate();
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00616,l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
    }
    
    public void testValidate_0079()
    {
        String STR_METHOD_NAME = ".testValidate_0079()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "123";
        l_request.accountCode = "123456";
        l_request.productCode = null;
        l_request.approveState = "9";
        l_request.checker ="123";
        l_request.createDateFrom = "20000601";
        l_request.createDateTo = "20070301";
        l_request.approveDateFrom = "20000601";
        l_request.approveDateTo = "20070301";
        l_request.forcedSettleReason = "9";
        l_request.closeDate = new Date();
        l_request.errorReason = "9001";
        l_request.approveType = "1";
        l_request.taxType = "1";
        l_request.marketCode = "6";
        
        WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
        l_sort.keyItem ="contractExecPrice";
        l_sort.ascDesc ="D";
        WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
        l_request.sortKeys = l_message;
        
        l_request.pageIndex = "12";
        l_request.pageSize = null;
        
        try
        {
            l_request.validate();
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00091,l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
    }
    
    public void testValidate_0080()
    {
        String STR_METHOD_NAME = ".testValidate_0080()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "123";
        l_request.accountCode = "123456";
        l_request.productCode = null;
        l_request.approveState = "9";
        l_request.checker ="123";
        l_request.createDateFrom = "20000601";
        l_request.createDateTo = "20070301";
        l_request.approveDateFrom = "20000601";
        l_request.approveDateTo = "20070301";
        l_request.forcedSettleReason = "9";
        l_request.closeDate = new Date();
        l_request.errorReason = "9001";
        l_request.approveType = "1";
        l_request.taxType = "1";
        l_request.marketCode = "6";
        
        WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
        l_sort.keyItem ="contractExecPrice";
        l_sort.ascDesc ="D";
        WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
        l_request.sortKeys = l_message;
        
        l_request.pageIndex = "12";
        l_request.pageSize = "-12";
        
        try
        {
            l_request.validate();
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00617,l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
    }
    
    public void testValidate_0081()
    {
        String STR_METHOD_NAME = ".testValidate_0081()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "123";
        l_request.accountCode = "123456";
        l_request.productCode = null;
        l_request.approveState = "9";
        l_request.checker ="123";
        l_request.createDateFrom = "20000601";
        l_request.createDateTo = "20070301";
        l_request.approveDateFrom = "20000601";
        l_request.approveDateTo = "20070301";
        l_request.forcedSettleReason = "9";
        l_request.closeDate = new Date();
        l_request.errorReason = "9001";
        l_request.approveType = "1";
        l_request.taxType = "1";
        l_request.marketCode = "6";
        
        WEB3AdminForcedSettleSortKeyUnit l_sort = new WEB3AdminForcedSettleSortKeyUnit();
        l_sort.keyItem ="contractExecPrice";
        l_sort.ascDesc ="D";
        WEB3AdminForcedSettleSortKeyUnit[] l_message = {l_sort};
        l_request.sortKeys = l_message;
        
        l_request.pageIndex = "12";
        l_request.pageSize = "12";
        
        try
        {
            l_request.validate();
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
    }
}
@
