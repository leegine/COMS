head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.52.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminForcedSettleReferenceRequestTest_20070913.java;


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
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminForcedSettleReferenceRequestTest_20070913 extends TestBaseForMock
{
    /**
     *　@ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminForcedSettleReferenceRequestTest_20070913.class);

    public WEB3AdminForcedSettleReferenceRequestTest_20070913(String arg0)
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

    public void testValidate_001()
    {
        String STR_METHOD_NAME = ".testValidate_001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "123";
        l_request.accountCode = "123456";
        l_request.productCode = null;
        l_request.approveState = "9";//
        l_request.checker = "123";
        l_request.createDateFrom = "200802010000";
        l_request.createDateTo = "200703010000";
        l_request.approveDateFrom = "200706010000";
        l_request.approveDateTo = "200003010000";
        l_request.forcedSettleReason = "0";//
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
        catch (WEB3BusinessLayerException l_ex)
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

    public void testValidate_002()
    {
        String STR_METHOD_NAME = ".testValidate_002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "123";
        l_request.accountCode = "123456";
        l_request.productCode = null;
        l_request.approveState = "0";//
        l_request.checker = "123";
        l_request.createDateFrom = "200802010000";
        l_request.createDateTo = "200703010000";
        l_request.approveDateFrom = "200706010000";
        l_request.approveDateTo = "200003010000";
        l_request.forcedSettleReason = "0";//
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
        catch (WEB3BusinessLayerException l_ex)
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

    public void testValidate_003()
    {
        String STR_METHOD_NAME = ".testValidate_003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "123";
        l_request.accountCode = "123456";
        l_request.productCode = null;
        l_request.approveState = "1";//
        l_request.checker = "123";
        l_request.createDateFrom = "200802010000";
        l_request.createDateTo = "200703010000";
        l_request.approveDateFrom = "200706010000";
        l_request.approveDateTo = "200003010000";
        l_request.forcedSettleReason = "0";//
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
        catch (WEB3BusinessLayerException l_ex)
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

    public void testValidate_004()
    {
        String STR_METHOD_NAME = ".testValidate_004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "123";
        l_request.accountCode = "123456";
        l_request.productCode = null;
        l_request.approveState = "2";//
        l_request.checker = "123";
        l_request.createDateFrom = "200802010000";
        l_request.createDateTo = "200703010000";
        l_request.approveDateFrom = "200706010000";
        l_request.approveDateTo = "200003010000";
        l_request.forcedSettleReason = "0";//
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
        catch (WEB3BusinessLayerException l_ex)
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

    public void testValidate_005()
    {
        String STR_METHOD_NAME = ".testValidate_005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "123";
        l_request.accountCode = "123456";
        l_request.productCode = null;
        l_request.approveState = "4";//
        l_request.checker = "123";
        l_request.createDateFrom = "200202010000";
        l_request.createDateTo = "200703010000";
        l_request.approveDateFrom = "200706010000";
        l_request.approveDateTo = "200003010000";
        l_request.forcedSettleReason = "0";//
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
        catch (WEB3BusinessLayerException l_ex)
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
    
    public void testValidate_006()
    {
        String STR_METHOD_NAME = ".testValidate_006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "123";
        l_request.accountCode = "123456";
        l_request.productCode = null;
        l_request.approveState = "1";//
        l_request.checker = "123";
        l_request.createDateFrom = "200202010000";
        l_request.createDateTo = "200703010000";
        l_request.approveDateFrom = "200706010000";
        l_request.approveDateTo = "200003010000";
        l_request.forcedSettleReason = "0";//
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
        catch (WEB3BusinessLayerException l_ex)
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

    public void testValidate_007()
    {
        String STR_METHOD_NAME = ".testValidate_007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "123";
        l_request.accountCode = "123456";
        l_request.productCode = null;
        l_request.approveState = "1";//
        l_request.checker = "123";
        l_request.createDateFrom = "200202010000";
        l_request.createDateTo = "200703010000";
        l_request.approveDateFrom = "200206010000";
        l_request.approveDateTo = "200803010000";
        l_request.forcedSettleReason = "0";//
        l_request.closeDate = new Date();
        l_request.errorReason = "1";
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
        catch (WEB3BusinessLayerException l_ex)
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
    
    public void testValidate_008()
    {
        String STR_METHOD_NAME = ".testValidate_008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "123";
        l_request.accountCode = "123456";
        l_request.productCode = null;
        l_request.approveState = "1";//
        l_request.checker = "123";
        l_request.createDateFrom = "200202010000";
        l_request.createDateTo = "200703010000";
        l_request.approveDateFrom = "200206010000";
        l_request.approveDateTo = "200803010000";
        l_request.forcedSettleReason = "1";//
        l_request.closeDate = new Date();
        l_request.errorReason = "1";
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
        catch (WEB3BusinessLayerException l_ex)
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
    
    public void testValidate_009()
    {
        String STR_METHOD_NAME = ".testValidate_009()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "123";
        l_request.accountCode = "123456";
        l_request.productCode = null;
        l_request.approveState = "1";//
        l_request.checker = "123";
        l_request.createDateFrom = "200202010000";
        l_request.createDateTo = "200703010000";
        l_request.approveDateFrom = "200206010000";
        l_request.approveDateTo = "200803010000";
        l_request.forcedSettleReason = "2";//
        l_request.closeDate = new Date();
        l_request.errorReason = "1";
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
        catch (WEB3BusinessLayerException l_ex)
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
    
    public void testValidate_010()
    {
        String STR_METHOD_NAME = ".testValidate_010()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "123";
        l_request.accountCode = "123456";
        l_request.productCode = null;
        l_request.approveState = "1";//
        l_request.checker = "123";
        l_request.createDateFrom = "200202010000";
        l_request.createDateTo = "200703010000";
        l_request.approveDateFrom = "200206010000";
        l_request.approveDateTo = "200803010000";
        l_request.forcedSettleReason = "9";//
        l_request.closeDate = new Date();
        l_request.errorReason = "1";
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
        catch (WEB3BusinessLayerException l_ex)
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
    
    public void testValidate_011()
    {
        String STR_METHOD_NAME = ".testValidate_011()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "123";
        l_request.accountCode = "123456";
        l_request.productCode = null;
        l_request.approveState = "1";//
        l_request.checker = "123";
        l_request.createDateFrom = "200202010000";
        l_request.createDateTo = "200703010000";
        l_request.approveDateFrom = "200206010000";
        l_request.approveDateTo = "200803010000";
        l_request.forcedSettleReason = "5";//異常
        l_request.closeDate = new Date();
        l_request.errorReason = "1";
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
        catch (WEB3BusinessLayerException l_ex)
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
    
    public void testValidate_012()
    {
        String STR_METHOD_NAME = ".testValidate_012()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "123";
        l_request.accountCode = "123456";
        l_request.productCode = null;
        l_request.approveState = "1";//
        l_request.checker = "123";
        l_request.createDateFrom = "200202010000";
        l_request.createDateTo = "200703010000";
        l_request.approveDateFrom = "200206010000";
        l_request.approveDateTo = "200803010000";
        l_request.forcedSettleReason = "3";//保証金維持率割（場間）
        l_request.closeDate = new Date();
        l_request.errorReason = "1";
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
        catch (WEB3BusinessLayerException l_ex)
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
    
    public void testValidate_013()
    {
        String STR_METHOD_NAME = ".testValidate_013()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "123";
        l_request.accountCode = "123456";
        l_request.productCode = null;
        l_request.approveState = "1";//
        l_request.checker = "123";
        l_request.createDateFrom = "200202010000";
        l_request.createDateTo = "200703010000";
        l_request.approveDateFrom = "200206010000";
        l_request.approveDateTo = "200803010000";
        l_request.forcedSettleReason = "4";//保証金維持率割（法@定）
        l_request.closeDate = new Date();
        l_request.errorReason = "1";
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
        catch (WEB3BusinessLayerException l_ex)
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
    
    public void testValidate_014()
    {
        String STR_METHOD_NAME = ".testValidate_014()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "123";
        l_request.accountCode = "123456";
        l_request.productCode = null;
        l_request.approveState = "1";//
        l_request.checker = "123";
        l_request.createDateFrom = "200202010000";
        l_request.createDateTo = "200703010000";
        l_request.approveDateFrom = "200206010000";
        l_request.approveDateTo = "200803010000";
        l_request.forcedSettleReason = "90";//追証(第一)期日超過
        l_request.closeDate = new Date();
        l_request.errorReason = "1";
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
        catch (WEB3BusinessLayerException l_ex)
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
    
    public void testValidate_015()
    {
        String STR_METHOD_NAME = ".testValidate_015()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceRequest l_request = new WEB3AdminForcedSettleReferenceRequest();
        l_request.branchCodeList = new String[1];
        l_request.branchCodeList[0] = "123";
        l_request.accountCode = "123456";
        l_request.productCode = null;
        l_request.approveState = "1";//
        l_request.checker = "123";
        l_request.createDateFrom = "200202010000";
        l_request.createDateTo = "200703010000";
        l_request.approveDateFrom = "200206010000";
        l_request.approveDateTo = "200803010000";
        l_request.forcedSettleReason = "91";//追証(第二)期日超過
        l_request.closeDate = new Date();
        l_request.errorReason = "1";
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
        catch (WEB3BusinessLayerException l_ex)
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
}
@
