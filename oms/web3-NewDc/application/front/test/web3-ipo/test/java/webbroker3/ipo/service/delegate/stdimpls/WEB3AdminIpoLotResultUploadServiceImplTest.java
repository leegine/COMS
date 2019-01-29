head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.41.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminIpoLotResultUploadServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.ipo.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Calendar;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3LotResultDef;
import webbroker3.ipo.WEB3AdminIpoLotResultUploadCsv;
import webbroker3.ipo.WEB3IpoOrderImpl;
import webbroker3.ipo.WEB3IpoOrderManagerImpl;
import webbroker3.ipo.WEB3IpoProductImpl;
import webbroker3.ipo.data.IpoOrderParams;
import webbroker3.ipo.data.IpoOrderRow;
import webbroker3.ipo.data.IpoProductParams;
import webbroker3.ipo.data.IpoProductRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminIpoLotResultUploadServiceImplTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminIpoLotResultUploadServiceImplTest.class);
    
    WEB3AdminIpoLotResultUploadServiceImpl l_impl =
        new WEB3AdminIpoLotResultUploadServiceImpl();
    
    public WEB3AdminIpoLotResultUploadServiceImplTest(String arg0)
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
    public void testValidateIpoOrderNewLot1()
    {
        final String STR_METHOD_NAME = "testValidateIpoOrderNewLot1()";
        log.debug(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(IpoOrderRow.TYPE);
            IpoOrderParams l_ipoOrderParams = this.getIpoOrderRow();
            TestDBUtility.insertWithDel(l_ipoOrderParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(4641635131L);
            l_mainAccountParams.setAccountCode("1234");
            l_mainAccountParams.setBranchId(624);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams    l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("02624");
            l_branchParams.setBranchId(624);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            ArrayList l_list = new ArrayList();
           
            String l_strAccountCode = "1234";
            String l_strBranchCode = "02624";
            double l_dblElectedQuantity = 100;
            l_impl.validateIpoOrderNewLot(l_list, l_strAccountCode, l_strBranchCode, l_dblElectedQuantity);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getErrorMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00510, l_ex.getErrorInfo());
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    public void testValidateIpoOrderNewLot2()
    {
        final String STR_METHOD_NAME = "testValidateIpoOrderNewLot2()";
        log.debug(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(IpoOrderRow.TYPE);
            IpoOrderParams l_ipoOrderParams = this.getIpoOrderRow();
            l_ipoOrderParams.setBookbuildingCreatedTimestamp(WEB3DateUtility.getDate("20070430","yyyyMMdd"));
            l_ipoOrderParams.setOrderStatus(OrderStatusEnum.CANCEL_ACCEPTED);
            l_ipoOrderParams.setLotResult("0");     
            l_ipoOrderParams.setIpoProductId(12);
            TestDBUtility.insertWithDel(l_ipoOrderParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(4641635131L);
            l_mainAccountParams.setAccountCode("1234");
            l_mainAccountParams.setBranchId(624);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("624");
            l_branchParams.setBranchId(624);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(IpoProductRow.TYPE);
            IpoProductParams l_ipoProductParams = this.getIpoProductRow();
            l_ipoProductParams.setBookbuildingEndDatetime(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_ipoProductParams);
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3IpoOrderManagerImpl l_ipoOrderManagerImpl = 
                (WEB3IpoOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getOrderManager();
            WEB3IpoOrderImpl[] l_ipoOrderImpl = l_ipoOrderManagerImpl.getOpenOrderUnits(12,null);
            System.out.println("l_ipoOrderImpl" + l_ipoOrderImpl.length);

            ArrayList l_list = new ArrayList();
            l_list.add(l_ipoOrderImpl[0]);   

            String l_strAccountCode = "1234";
            String l_strBranchCode = "624";
            double l_dblElectedQuantity = 100;
            l_impl.validateIpoOrderNewLot(l_list, l_strAccountCode, l_strBranchCode, l_dblElectedQuantity);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    /**
     * WEB3ErrorCatalog.BUSINESS_ERROR_00830
     *
     */
    public void testValidateIpoOrderNewLot3()
    {
        final String STR_METHOD_NAME = "testValidateIpoOrderNewLot3()";
        log.debug(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(IpoOrderRow.TYPE);
            IpoOrderParams l_ipoOrderParams = this.getIpoOrderRow();
            TestDBUtility.insertWithDel(l_ipoOrderParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(4641635131L);
            l_mainAccountParams.setAccountCode("1234");
            l_mainAccountParams.setBranchId(624);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams    l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("02624");
            l_branchParams.setBranchId(624);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            ArrayList l_list = new ArrayList();
           
            String l_strAccountCode = "1234";
            String l_strBranchCode = "02624";
            double l_dblElectedQuantity = 100;
            l_impl.validateIpoOrderNewLot(l_list, null, null, l_dblElectedQuantity);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getErrorMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00830, l_ex.getErrorInfo());
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    public void testvalidateIpoOrderAdvanceLot1()
    {
        final String STR_METHOD_NAME = "testvalidateIpoOrderAdvanceLot1()";
        log.debug(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(IpoOrderRow.TYPE);
            IpoOrderParams l_ipoOrderParams = this.getIpoOrderRow();
            TestDBUtility.insertWithDel(l_ipoOrderParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(4641635131L);
            l_mainAccountParams.setAccountCode("1234");
            l_mainAccountParams.setBranchId(624);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams    l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("02624");
            l_branchParams.setBranchId(624);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            ArrayList l_list = new ArrayList();
           
            String l_strAccountCode = "1234";
            String l_strBranchCode = "02624";
            double l_dblElectedQuantity = 100;
            l_impl.validateIpoOrderAdvanceLot(l_list, l_strAccountCode, l_strBranchCode, l_dblElectedQuantity);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getErrorMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01995, l_ex.getErrorInfo());
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    public void testvalidateIpoOrderAdvanceLot2()
    {
        final String STR_METHOD_NAME = "testvalidateIpoOrderAdvanceLot2()";
        log.debug(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(IpoOrderRow.TYPE);
            IpoOrderParams l_ipoOrderParams = this.getIpoOrderRow();
            l_ipoOrderParams.setBookbuildingCreatedTimestamp(WEB3DateUtility.getDate("20070430","yyyyMMdd"));
            l_ipoOrderParams.setOrderStatus(OrderStatusEnum.CANCEL_ACCEPTED);
            l_ipoOrderParams.setLotResultRetry("0");     
            l_ipoOrderParams.setIpoProductId(12);
            l_ipoOrderParams.setOfferingDiv("1");
            l_ipoOrderParams.setApplicationQuantity(200);
            TestDBUtility.insertWithDel(l_ipoOrderParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(4641635131L);
            l_mainAccountParams.setAccountCode("1234");
            l_mainAccountParams.setBranchId(624);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("624");
            l_branchParams.setBranchId(624);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(IpoProductRow.TYPE);
            IpoProductParams l_ipoProductParams = this.getIpoProductRow();
            l_ipoProductParams.setBookbuildingEndDatetime(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_ipoProductParams);
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3IpoOrderManagerImpl l_ipoOrderManagerImpl = 
                (WEB3IpoOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getOrderManager();
            WEB3IpoOrderImpl[] l_ipoOrderImpl = l_ipoOrderManagerImpl.getOpenOrderUnits(12,null);
            System.out.println("l_ipoOrderImpl" + l_ipoOrderImpl.length);

            ArrayList l_list = new ArrayList();
            l_list.add(l_ipoOrderImpl[0]);   

            String l_strAccountCode = "1234";
            String l_strBranchCode = "624";
            double l_dblElectedQuantity = 100;
            l_impl.validateIpoOrderAdvanceLot(l_list, l_strAccountCode, l_strBranchCode, l_dblElectedQuantity);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    /**
     * 明細行（繰上抽選）のチェックを行う
     * WEB3ErrorCatalog.SYSTEM_ERROR_80003
     *
     */
    public void test_validateDetailsLineAdvanceLot_0001()
    {
        final String STR_METHOD_NAME = "test_validateDetailsLineAdvanceLot_0001()";
        log.debug(STR_METHOD_NAME);
        
        WEB3AdminIpoLotResultUploadCsvForTest l_lotResultCsvForTest = new WEB3AdminIpoLotResultUploadCsvForTest(1L);
        ArrayList l_assessmentList = new ArrayList();
        
        WEB3IpoOrderImpl l_ipoOrder = null;
        
        String[] l_strDetailsLines = {"1","2"};
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            TestDBUtility.deleteAll(IpoOrderRow.TYPE);
            IpoOrderParams l_ipoOrderParams = this.getIpoOrderRow();
            l_ipoOrderParams.setBookbuildingCreatedTimestamp(Calendar.getInstance().getTime());
            l_ipoOrderParams.setOrderStatus(OrderStatusEnum.CANCEL_ACCEPTED);
            l_ipoOrderParams.setLotResultRetry("0");     
            l_ipoOrderParams.setIpoProductId(12);
            l_ipoOrderParams.setOfferingDiv("1");
            l_ipoOrderParams.setLotResult(WEB3LotResultDef.SUPPLEMENT);
            TestDBUtility.insertWithDel(l_ipoOrderParams);
            
            
            TestDBUtility.deleteAll(IpoProductRow.TYPE);
            IpoProductParams l_ipoProductParams = this.getIpoProductRow();
            l_ipoProductParams.setBookbuildingEndDatetime(WEB3DateUtility.getDate("20070430","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_ipoProductParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("07");
//            l_institutionParams.setBookbuildingEndDatetime(WEB3DateUtility.getDate("20070430","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionCode("07");
            l_branchParams.setBranchCode("1");
//            l_institutionParams.setBookbuildingEndDatetime(WEB3DateUtility.getDate("20070430","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_branchParams);
        }
        catch (WEB3BaseException e1)
        {
            fail();
        }
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "getMainAccount",
                    new Class[] {String.class, String.class, String.class},
                    new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003,""));
                    
            l_ipoOrder = new WEB3IpoOrderImpl(1234L);
        }
        catch (Exception e)
        {
            fail();
        }
        l_assessmentList.add(l_ipoOrder);

        try
        {
            l_impl.validateDetailsLineAdvanceLot(l_lotResultCsvForTest,l_assessmentList,l_strDetailsLines);
        }
        catch (WEB3BaseException e)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00398,e.getErrorInfo());
        }
    }
    
    
    /**
     * 明細行（繰上抽選）のチェックを行う
     * WEB3ErrorCatalog.SYSTEM_ERROR_80005
     *
     */
    public void test_validateDetailsLineAdvanceLot_0002()
    {
        final String STR_METHOD_NAME = "test_validateDetailsLineAdvanceLot_0002()";
        log.debug(STR_METHOD_NAME);
        
        WEB3AdminIpoLotResultUploadCsvForTest l_lotResultCsvForTest = new WEB3AdminIpoLotResultUploadCsvForTest(1L);
        ArrayList l_assessmentList = new ArrayList();
        
        WEB3IpoOrderImpl l_ipoOrder = null;
        
        String[] l_strDetailsLines = {"1","2"};
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            TestDBUtility.deleteAll(IpoOrderRow.TYPE);
            IpoOrderParams l_ipoOrderParams = this.getIpoOrderRow();
            l_ipoOrderParams.setBookbuildingCreatedTimestamp(Calendar.getInstance().getTime());
            l_ipoOrderParams.setOrderStatus(OrderStatusEnum.CANCEL_ACCEPTED);
            l_ipoOrderParams.setLotResultRetry("0");     
            l_ipoOrderParams.setIpoProductId(12);
            l_ipoOrderParams.setOfferingDiv("1");
            l_ipoOrderParams.setLotResult(WEB3LotResultDef.SUPPLEMENT);
            TestDBUtility.insertWithDel(l_ipoOrderParams);
            
            
            TestDBUtility.deleteAll(IpoProductRow.TYPE);
            IpoProductParams l_ipoProductParams = this.getIpoProductRow();
            l_ipoProductParams.setBookbuildingEndDatetime(WEB3DateUtility.getDate("20070430","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_ipoProductParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("07");
//            l_institutionParams.setBookbuildingEndDatetime(WEB3DateUtility.getDate("20070430","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionCode("07");
            l_branchParams.setBranchCode("1");
//            l_institutionParams.setBookbuildingEndDatetime(WEB3DateUtility.getDate("20070430","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_branchParams);
        }
        catch (WEB3BaseException e1)
        {
            fail();
        }
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "getMainAccount",
                    new Class[] {String.class, String.class, String.class},
                    new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005,""));
                    
            l_ipoOrder = new WEB3IpoOrderImpl(1234L);
        }
        catch (Exception e)
        {
            fail();
        }
        l_assessmentList.add(l_ipoOrder);

        try
        {
            l_impl.validateDetailsLineAdvanceLot(l_lotResultCsvForTest,l_assessmentList,l_strDetailsLines);
        }
        catch (WEB3BaseException e)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00398,e.getErrorInfo());
        }
    }
    
    /**
     * 明細行（繰上抽選）のチェックを行う
     * WEB3ErrorCatalog.SYSTEM_ERROR_80006
     *
     */
    public void test_validateDetailsLineAdvanceLot_0003()
    {
        final String STR_METHOD_NAME = "test_validateDetailsLineAdvanceLot_0003()";
        log.debug(STR_METHOD_NAME);
        
        WEB3AdminIpoLotResultUploadCsvForTest l_lotResultCsvForTest = new WEB3AdminIpoLotResultUploadCsvForTest(1L);
        ArrayList l_assessmentList = new ArrayList();
        
        WEB3IpoOrderImpl l_ipoOrder = null;
        
        String[] l_strDetailsLines = {"1","2"};
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            TestDBUtility.deleteAll(IpoOrderRow.TYPE);
            IpoOrderParams l_ipoOrderParams = this.getIpoOrderRow();
            l_ipoOrderParams.setBookbuildingCreatedTimestamp(Calendar.getInstance().getTime());
            l_ipoOrderParams.setOrderStatus(OrderStatusEnum.CANCEL_ACCEPTED);
            l_ipoOrderParams.setLotResultRetry("0");     
            l_ipoOrderParams.setIpoProductId(12);
            l_ipoOrderParams.setOfferingDiv("1");
            l_ipoOrderParams.setLotResult(WEB3LotResultDef.SUPPLEMENT);
            TestDBUtility.insertWithDel(l_ipoOrderParams);
            
            
            TestDBUtility.deleteAll(IpoProductRow.TYPE);
            IpoProductParams l_ipoProductParams = this.getIpoProductRow();
            l_ipoProductParams.setBookbuildingEndDatetime(WEB3DateUtility.getDate("20070430","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_ipoProductParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("07");
//            l_institutionParams.setBookbuildingEndDatetime(WEB3DateUtility.getDate("20070430","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionCode("07");
            l_branchParams.setBranchCode("1");
//            l_institutionParams.setBookbuildingEndDatetime(WEB3DateUtility.getDate("20070430","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_branchParams);
        }
        catch (WEB3BaseException e1)
        {
            fail();
        }
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "getMainAccount",
                    new Class[] {String.class, String.class, String.class},
                    new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80006,""));
                    
            l_ipoOrder = new WEB3IpoOrderImpl(1234L);
        }
        catch (Exception e)
        {
            fail();
        }
        l_assessmentList.add(l_ipoOrder);

        try
        {
            l_impl.validateDetailsLineAdvanceLot(l_lotResultCsvForTest,l_assessmentList,l_strDetailsLines);
        }
        catch (WEB3BaseException e)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00398,e.getErrorInfo());
        }
    }
    
    /**
     * 明細行（繰上抽選）のチェックを行う
     * WEB3ErrorCatalog.SYSTEM_ERROR_80017
     *
     */
    public void test_validateDetailsLineAdvanceLot_0004()
    {
        final String STR_METHOD_NAME = "test_validateDetailsLineAdvanceLot_0004()";
        log.debug(STR_METHOD_NAME);
        
        WEB3AdminIpoLotResultUploadCsvForTest l_lotResultCsvForTest = new WEB3AdminIpoLotResultUploadCsvForTest(1L);
        ArrayList l_assessmentList = new ArrayList();
        
        WEB3IpoOrderImpl l_ipoOrder = null;
        
        String[] l_strDetailsLines = {"1","2"};
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            TestDBUtility.deleteAll(IpoOrderRow.TYPE);
            IpoOrderParams l_ipoOrderParams = this.getIpoOrderRow();
            l_ipoOrderParams.setBookbuildingCreatedTimestamp(Calendar.getInstance().getTime());
            l_ipoOrderParams.setOrderStatus(OrderStatusEnum.CANCEL_ACCEPTED);
            l_ipoOrderParams.setLotResultRetry("0");     
            l_ipoOrderParams.setIpoProductId(12);
            l_ipoOrderParams.setOfferingDiv("1");
            l_ipoOrderParams.setLotResult(WEB3LotResultDef.SUPPLEMENT);
            TestDBUtility.insertWithDel(l_ipoOrderParams);
            
            
            TestDBUtility.deleteAll(IpoProductRow.TYPE);
            IpoProductParams l_ipoProductParams = this.getIpoProductRow();
            l_ipoProductParams.setBookbuildingEndDatetime(WEB3DateUtility.getDate("20070430","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_ipoProductParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("07");
//            l_institutionParams.setBookbuildingEndDatetime(WEB3DateUtility.getDate("20070430","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionCode("07");
            l_branchParams.setBranchCode("1");
//            l_institutionParams.setBookbuildingEndDatetime(WEB3DateUtility.getDate("20070430","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_branchParams);
        }
        catch (WEB3BaseException e1)
        {
            fail();
        }
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "getMainAccount",
                    new Class[] {String.class, String.class, String.class},
                    new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,""));
                    
            l_ipoOrder = new WEB3IpoOrderImpl(1234L);
        }
        catch (Exception e)
        {
            fail();
        }
        l_assessmentList.add(l_ipoOrder);

        try
        {
            l_impl.validateDetailsLineAdvanceLot(l_lotResultCsvForTest,l_assessmentList,l_strDetailsLines);
        }
        catch (WEB3BaseException e)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00830,e.getErrorInfo());
        }
    }
    
    /**
     * 明細行（繰上抽選）のチェックを行う
     * WEB3ErrorCatalog.SYSTEM_ERROR_80008
     *
     */
    public void test_validateDetailsLineAdvanceLot_0005()
    {
        final String STR_METHOD_NAME = "test_validateDetailsLineAdvanceLot_0005()";
        log.debug(STR_METHOD_NAME);
        
        WEB3AdminIpoLotResultUploadCsvForTest l_lotResultCsvForTest = new WEB3AdminIpoLotResultUploadCsvForTest(1L);
        ArrayList l_assessmentList = new ArrayList();
        
        WEB3IpoOrderImpl l_ipoOrder = null;
        
        String[] l_strDetailsLines = {"1","2"};
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            TestDBUtility.deleteAll(IpoOrderRow.TYPE);
            IpoOrderParams l_ipoOrderParams = this.getIpoOrderRow();
            l_ipoOrderParams.setBookbuildingCreatedTimestamp(Calendar.getInstance().getTime());
            l_ipoOrderParams.setOrderStatus(OrderStatusEnum.CANCEL_ACCEPTED);
            l_ipoOrderParams.setLotResultRetry("0");     
            l_ipoOrderParams.setIpoProductId(12);
            l_ipoOrderParams.setOfferingDiv("1");
            l_ipoOrderParams.setLotResult(WEB3LotResultDef.SUPPLEMENT);
            TestDBUtility.insertWithDel(l_ipoOrderParams);
            
            
            TestDBUtility.deleteAll(IpoProductRow.TYPE);
            IpoProductParams l_ipoProductParams = this.getIpoProductRow();
            l_ipoProductParams.setBookbuildingEndDatetime(WEB3DateUtility.getDate("20070430","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_ipoProductParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("07");
//            l_institutionParams.setBookbuildingEndDatetime(WEB3DateUtility.getDate("20070430","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionCode("07");
            l_branchParams.setBranchCode("1");
//            l_institutionParams.setBookbuildingEndDatetime(WEB3DateUtility.getDate("20070430","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_branchParams);
        }
        catch (WEB3BaseException e1)
        {
            fail();
        }
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "getMainAccount",
                    new Class[] {String.class, String.class, String.class},
                    new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80008,""));
                    
            l_ipoOrder = new WEB3IpoOrderImpl(1234L);
        }
        catch (Exception e)
        {
            fail();
        }
        l_assessmentList.add(l_ipoOrder);

        try
        {
            l_impl.validateDetailsLineAdvanceLot(l_lotResultCsvForTest,l_assessmentList,l_strDetailsLines);
        }
        catch (WEB3BaseException e)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80008,e.getErrorInfo());
        }
    }
    
    /**
     * validateIPO申告（新規抽選）
     * WEB3ErrorCatalog.BUSINESS_ERROR_00830
     * 部店コード = null
     */
    public void test_validateIpoOrderNewLot_0001()
    {
        final String STR_METHOD_NAME = "test_validateIpoOrderNewLot_0001()";
        log.debug(STR_METHOD_NAME);
        
        try
        {
            l_impl.validateIpoOrderNewLot(null,"1",null,1D);
        }
        catch (WEB3BaseException e)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00830,e.getErrorInfo());
        }
    }
    
    /**
     * validateIPO申告（新規抽選）
     * WEB3ErrorCatalog.BUSINESS_ERROR_00830
     * 顧客コード = null
     */
    public void test_validateIpoOrderNewLot_0002()
    {
        final String STR_METHOD_NAME = "test_validateIpoOrderNewLot_0002()";
        log.debug(STR_METHOD_NAME);
        
        try
        {
            l_impl.validateIpoOrderNewLot(null,null,"2",1D);
        }
        catch (WEB3BaseException e)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00830,e.getErrorInfo());
        }
    }
    
    /**
     * validateIPO申告（繰上抽選）
     * WEB3ErrorCatalog.BUSINESS_ERROR_00830
     * 部店コード = null
     */
    public void test_validateIpoOrderAdvanceLot_0001()
    {
        final String STR_METHOD_NAME = "test_validateIpoOrderAdvanceLot_0001()";
        log.debug(STR_METHOD_NAME);
        
        try
        {
            l_impl.validateIpoOrderAdvanceLot(null,null,"2",1D);
        }
        catch (WEB3BaseException e)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00830,e.getErrorInfo());
        }
    }
    
    /**
     * validateIPO申告（繰上抽選）
     * WEB3ErrorCatalog.BUSINESS_ERROR_00830
     * 顧客コード = null
     */
    public void test_validateIpoOrderAdvanceLot_0002()
    {
        final String STR_METHOD_NAME = "test_validateIpoOrderAdvanceLot_0002()";
        log.debug(STR_METHOD_NAME);
        
        try
        {
            l_impl.validateIpoOrderAdvanceLot(null,"1",null,1D);
        }
        catch (WEB3BaseException e)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00830,e.getErrorInfo());
        }
    }
    
    class WEB3AdminIpoLotResultUploadCsvForTest extends WEB3AdminIpoLotResultUploadCsv
    {

        public WEB3AdminIpoLotResultUploadCsvForTest(long l_lngUploadId)
        {
            super(l_lngUploadId);
        }
        
        /**
         * this.IPO銘柄を返却する。
         * @@return webbroker3.ipo.WEB3IpoProductImpl
         * @@roseuid 40F5070B01AF
         */
        public WEB3IpoProductImpl getIpoProduct() 
        {
            WEB3IpoProductImpl l_product = null;
            try
            {
                l_product = new WEB3IpoProductImpl(12L);
            }
            catch (Exception e)
            {
                fail();
            }
            //this.IPO銘柄を返却する
            return l_product;
         
        }
        
        public void addAllotQuantity(WEB3IpoOrderImpl l_ipoOrder, WEB3IpoProductImpl l_ipoProduct) 
        {
            
        }
        
        public int addRow(String l_rowString) throws WEB3SystemLayerException
        {
            return 1;
        }
        
        public String getAccountCode(int l_intLineNo) 
        {
            return "1";
        }
        
        public void validateItemLength(int l_intLineNo) throws WEB3BaseException
        {
            
        }
        public double getElectedQuantity(int l_intLineNo) 
        {
            return 0.0D;
        }
        public String getLotResult(int l_intLineNo) 
        {
            return WEB3LotResultDef.DEFEAT;
        }
        public Long getSubstitutePriority(int l_intLineNo) 
        {
            return new Long(1);
        }
        public void validateSubstitutePriority(String l_strLotResult, Long l_lngSubstitutePriority) throws WEB3BaseException
        {
            
        }
        public String getBranchCode(int l_intLineNo) 
        {
            return "1";
        }
        public void saveUploadError(ErrorInfo l_errorInfo) throws WEB3SystemLayerException
        {
            
        }
        public void validateNewLotLotResult(String l_strLotResult) throws WEB3BaseException
        {
            
        }
    }
    
    /**
     * validate明細行（新規抽選）
     * WEB3ErrorCatalog.SYSTEM_ERROR_80003
     */
    public void test_validateDetailsLineNewLot_0001()
    {
        final String STR_METHOD_NAME = "test_validateDetailsLineNewLot_0001()";
        log.debug(STR_METHOD_NAME);
        
        WEB3AdminIpoLotResultUploadCsvForTest l_lotResultCsvForTest = new WEB3AdminIpoLotResultUploadCsvForTest(1L);
        ArrayList l_assessmentList = new ArrayList();
        
        WEB3IpoOrderImpl l_ipoOrder = null;
        
        String[] l_strDetailsLines = {"1","2"};
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            TestDBUtility.deleteAll(IpoOrderRow.TYPE);
            IpoOrderParams l_ipoOrderParams = this.getIpoOrderRow();
            l_ipoOrderParams.setBookbuildingCreatedTimestamp(Calendar.getInstance().getTime());
            l_ipoOrderParams.setOrderStatus(OrderStatusEnum.CANCEL_ACCEPTED);
            l_ipoOrderParams.setLotResultRetry("0");     
            l_ipoOrderParams.setIpoProductId(12);
            l_ipoOrderParams.setOfferingDiv("1");
            l_ipoOrderParams.setLotResult(WEB3LotResultDef.SUPPLEMENT);
            TestDBUtility.insertWithDel(l_ipoOrderParams);
            
            
            TestDBUtility.deleteAll(IpoProductRow.TYPE);
            IpoProductParams l_ipoProductParams = this.getIpoProductRow();
            l_ipoProductParams.setBookbuildingEndDatetime(WEB3DateUtility.getDate("20070430","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_ipoProductParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("07");
//            l_institutionParams.setBookbuildingEndDatetime(WEB3DateUtility.getDate("20070430","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionCode("07");
            l_branchParams.setBranchCode("1");
//            l_institutionParams.setBookbuildingEndDatetime(WEB3DateUtility.getDate("20070430","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_branchParams);
        }
        catch (WEB3BaseException e1)
        {
            fail();
        }
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "getMainAccount",
                    new Class[] {String.class, String.class, String.class},
                    new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003,""));
                    
            l_ipoOrder = new WEB3IpoOrderImpl(1234L);
        }
        catch (Exception e)
        {
            fail();
        }
        l_assessmentList.add(l_ipoOrder);

        try
        {
            l_impl.validateDetailsLineNewLot(l_lotResultCsvForTest,l_assessmentList,l_strDetailsLines);
        }
        catch (WEB3BaseException e)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00398,e.getErrorInfo());
        }
    }
    
    /**
     * validate明細行（新規抽選）
     * WEB3ErrorCatalog.SYSTEM_ERROR_80005
     */
    public void test_validateDetailsLineNewLot_0002()
    {
        final String STR_METHOD_NAME = "test_validateDetailsLineNewLot_0002()";
        log.debug(STR_METHOD_NAME);
        
        WEB3AdminIpoLotResultUploadCsvForTest l_lotResultCsvForTest = new WEB3AdminIpoLotResultUploadCsvForTest(1L);
        ArrayList l_assessmentList = new ArrayList();
        
        WEB3IpoOrderImpl l_ipoOrder = null;
        
        String[] l_strDetailsLines = {"1","2"};
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            TestDBUtility.deleteAll(IpoOrderRow.TYPE);
            IpoOrderParams l_ipoOrderParams = this.getIpoOrderRow();
            l_ipoOrderParams.setBookbuildingCreatedTimestamp(Calendar.getInstance().getTime());
            l_ipoOrderParams.setOrderStatus(OrderStatusEnum.CANCEL_ACCEPTED);
            l_ipoOrderParams.setLotResultRetry("0");     
            l_ipoOrderParams.setIpoProductId(12);
            l_ipoOrderParams.setOfferingDiv("1");
            l_ipoOrderParams.setLotResult(WEB3LotResultDef.SUPPLEMENT);
            TestDBUtility.insertWithDel(l_ipoOrderParams);
            
            
            TestDBUtility.deleteAll(IpoProductRow.TYPE);
            IpoProductParams l_ipoProductParams = this.getIpoProductRow();
            l_ipoProductParams.setBookbuildingEndDatetime(WEB3DateUtility.getDate("20070430","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_ipoProductParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("07");
//            l_institutionParams.setBookbuildingEndDatetime(WEB3DateUtility.getDate("20070430","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionCode("07");
            l_branchParams.setBranchCode("1");
//            l_institutionParams.setBookbuildingEndDatetime(WEB3DateUtility.getDate("20070430","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_branchParams);
        }
        catch (WEB3BaseException e1)
        {
            fail();
        }
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "getMainAccount",
                    new Class[] {String.class, String.class, String.class},
                    new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005,""));
                    
            l_ipoOrder = new WEB3IpoOrderImpl(1234L);
        }
        catch (Exception e)
        {
            fail();
        }
        l_assessmentList.add(l_ipoOrder);

        try
        {
            l_impl.validateDetailsLineNewLot(l_lotResultCsvForTest,l_assessmentList,l_strDetailsLines);
        }
        catch (WEB3BaseException e)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00398,e.getErrorInfo());
        }
    }
    
    /**
     * validate明細行（新規抽選）
     * 
     */
    public void test_validateDetailsLineNewLot_0003()
    {
        final String STR_METHOD_NAME = "test_validateDetailsLineNewLot_0003()";
        log.debug(STR_METHOD_NAME);
        
        WEB3AdminIpoLotResultUploadCsvForTest l_lotResultCsvForTest = new WEB3AdminIpoLotResultUploadCsvForTest(1L);
        ArrayList l_assessmentList = new ArrayList();
        
        WEB3IpoOrderImpl l_ipoOrder = null;
        
        String[] l_strDetailsLines = {"1","2"};
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            TestDBUtility.deleteAll(IpoOrderRow.TYPE);
            IpoOrderParams l_ipoOrderParams = this.getIpoOrderRow();
            l_ipoOrderParams.setBookbuildingCreatedTimestamp(Calendar.getInstance().getTime());
            l_ipoOrderParams.setOrderStatus(OrderStatusEnum.CANCEL_ACCEPTED);
            l_ipoOrderParams.setLotResultRetry("0");     
            l_ipoOrderParams.setIpoProductId(12);
            l_ipoOrderParams.setOfferingDiv("1");
            l_ipoOrderParams.setLotResult(WEB3LotResultDef.SUPPLEMENT);
            TestDBUtility.insertWithDel(l_ipoOrderParams);
            
            
            TestDBUtility.deleteAll(IpoProductRow.TYPE);
            IpoProductParams l_ipoProductParams = this.getIpoProductRow();
            l_ipoProductParams.setBookbuildingEndDatetime(WEB3DateUtility.getDate("20070430","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_ipoProductParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("07");
//            l_institutionParams.setBookbuildingEndDatetime(WEB3DateUtility.getDate("20070430","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionCode("07");
            l_branchParams.setBranchCode("1");
//            l_institutionParams.setBookbuildingEndDatetime(WEB3DateUtility.getDate("20070430","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_branchParams);
        }
        catch (WEB3BaseException e1)
        {
            fail();
        }
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "getMainAccount",
                    new Class[] {String.class, String.class, String.class},
                    new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80006,""));
                    
            l_ipoOrder = new WEB3IpoOrderImpl(1234L);
        }
        catch (Exception e)
        {
            fail();
        }
        l_assessmentList.add(l_ipoOrder);

        try
        {
            l_impl.validateDetailsLineNewLot(l_lotResultCsvForTest,l_assessmentList,l_strDetailsLines);
        }
        catch (WEB3BaseException e)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00398,e.getErrorInfo());
        }
    }
    
    /**
     * validate明細行（新規抽選）
     * WEB3ErrorCatalog.SYSTEM_ERROR_80017
     */
    public void test_validateDetailsLineNewLot_0004()
    {
        final String STR_METHOD_NAME = "test_validateDetailsLineNewLot_0004()";
        log.debug(STR_METHOD_NAME);
        
        WEB3AdminIpoLotResultUploadCsvForTest l_lotResultCsvForTest = new WEB3AdminIpoLotResultUploadCsvForTest(1L);
        ArrayList l_assessmentList = new ArrayList();
        
        WEB3IpoOrderImpl l_ipoOrder = null;
        
        String[] l_strDetailsLines = {"1","2"};
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            TestDBUtility.deleteAll(IpoOrderRow.TYPE);
            IpoOrderParams l_ipoOrderParams = this.getIpoOrderRow();
            l_ipoOrderParams.setBookbuildingCreatedTimestamp(Calendar.getInstance().getTime());
            l_ipoOrderParams.setOrderStatus(OrderStatusEnum.CANCEL_ACCEPTED);
            l_ipoOrderParams.setLotResultRetry("0");     
            l_ipoOrderParams.setIpoProductId(12);
            l_ipoOrderParams.setOfferingDiv("1");
            l_ipoOrderParams.setLotResult(WEB3LotResultDef.SUPPLEMENT);
            TestDBUtility.insertWithDel(l_ipoOrderParams);
            
            
            TestDBUtility.deleteAll(IpoProductRow.TYPE);
            IpoProductParams l_ipoProductParams = this.getIpoProductRow();
            l_ipoProductParams.setBookbuildingEndDatetime(WEB3DateUtility.getDate("20070430","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_ipoProductParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("07");
//            l_institutionParams.setBookbuildingEndDatetime(WEB3DateUtility.getDate("20070430","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionCode("07");
            l_branchParams.setBranchCode("1");
//            l_institutionParams.setBookbuildingEndDatetime(WEB3DateUtility.getDate("20070430","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_branchParams);
        }
        catch (WEB3BaseException e1)
        {
            fail();
        }
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "getMainAccount",
                    new Class[] {String.class, String.class, String.class},
                    new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,""));
                    
            l_ipoOrder = new WEB3IpoOrderImpl(1234L);
        }
        catch (Exception e)
        {
            fail();
        }
        l_assessmentList.add(l_ipoOrder);

        try
        {
            l_impl.validateDetailsLineNewLot(l_lotResultCsvForTest,l_assessmentList,l_strDetailsLines);
        }
        catch (WEB3BaseException e)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00830,e.getErrorInfo());
        }
    }
    
    /**
     * validate明細行（新規抽選）
     * WEB3ErrorCatalog.SYSTEM_ERROR_80008
     */
    public void test_validateDetailsLineNewLot_0005()
    {
        final String STR_METHOD_NAME = "test_validateDetailsLineNewLot_0005()";
        log.debug(STR_METHOD_NAME);
        
        WEB3AdminIpoLotResultUploadCsvForTest l_lotResultCsvForTest = new WEB3AdminIpoLotResultUploadCsvForTest(1L);
        ArrayList l_assessmentList = new ArrayList();
        
        WEB3IpoOrderImpl l_ipoOrder = null;
        
        String[] l_strDetailsLines = {"1","2"};
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            TestDBUtility.deleteAll(IpoOrderRow.TYPE);
            IpoOrderParams l_ipoOrderParams = this.getIpoOrderRow();
            l_ipoOrderParams.setBookbuildingCreatedTimestamp(Calendar.getInstance().getTime());
            l_ipoOrderParams.setOrderStatus(OrderStatusEnum.CANCEL_ACCEPTED);
            l_ipoOrderParams.setLotResultRetry("0");     
            l_ipoOrderParams.setIpoProductId(12);
            l_ipoOrderParams.setOfferingDiv("1");
            l_ipoOrderParams.setLotResult(WEB3LotResultDef.SUPPLEMENT);
            TestDBUtility.insertWithDel(l_ipoOrderParams);
            
            
            TestDBUtility.deleteAll(IpoProductRow.TYPE);
            IpoProductParams l_ipoProductParams = this.getIpoProductRow();
            l_ipoProductParams.setBookbuildingEndDatetime(WEB3DateUtility.getDate("20070430","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_ipoProductParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("07");
//            l_institutionParams.setBookbuildingEndDatetime(WEB3DateUtility.getDate("20070430","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionCode("07");
            l_branchParams.setBranchCode("1");
//            l_institutionParams.setBookbuildingEndDatetime(WEB3DateUtility.getDate("20070430","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_branchParams);
        }
        catch (WEB3BaseException e1)
        {
            fail();
        }
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "getMainAccount",
                    new Class[] {String.class, String.class, String.class},
                    new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80008,""));
                    
            l_ipoOrder = new WEB3IpoOrderImpl(1234L);
        }
        catch (Exception e)
        {
            fail();
        }
        l_assessmentList.add(l_ipoOrder);

        try
        {
            l_impl.validateDetailsLineNewLot(l_lotResultCsvForTest,l_assessmentList,l_strDetailsLines);
        }
        catch (WEB3BaseException e)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80008,e.getErrorInfo());
        }
    }
        
    public static IpoOrderParams getIpoOrderRow()
    {
        IpoOrderParams l_ipoOrderParams = new IpoOrderParams();
        l_ipoOrderParams.setIpoOrderId(1234);
        l_ipoOrderParams.setIpoProductId(2234);
        l_ipoOrderParams.setBranchId(624);
        l_ipoOrderParams.setAccountId(4641635131L);
        l_ipoOrderParams.setSubAccountId(416513131L);
        l_ipoOrderParams.setLastOrderActionSerialNo(0);
        l_ipoOrderParams.setQuantity(150);
        l_ipoOrderParams.setLimitPrice(34);
        l_ipoOrderParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
        l_ipoOrderParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
        
        return l_ipoOrderParams;

    }
    
    public static IpoProductParams getIpoProductRow()
    {
        IpoProductParams l_ipoProductParams = new IpoProductParams();
        l_ipoProductParams.setIpoProductId(12);
        l_ipoProductParams.setInstitutionCode("07");
        l_ipoProductParams.setProductCode("1234");
        l_ipoProductParams.setProductType(ProductTypeEnum.IPO);
        l_ipoProductParams.setIpoRegistDiv("1");
        l_ipoProductParams.setIpoRegistDetailDiv("1");
        l_ipoProductParams.setPublicMarket("as");
        l_ipoProductParams.setProvisionalValueDiv("1");
        
        
        return l_ipoProductParams;

    }
    

}
@
