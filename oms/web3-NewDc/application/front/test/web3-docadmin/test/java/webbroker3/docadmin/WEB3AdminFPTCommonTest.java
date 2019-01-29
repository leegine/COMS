head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.06.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminFPTCommonTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.docadmin;

import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundProduct;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.data.BatoProductManagementParams;
import webbroker3.gentrade.data.DocCategoryManagementParams;
import webbroker3.gentrade.data.DocDivManagementParams;
import webbroker3.gentrade.data.DocDivManagementRow;
import webbroker3.ipo.data.IpoProductParams;
import webbroker3.ipo.data.IpoProductRow;
import webbroker3.mf.WEB3MutualFundProduct;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminFPTCommonTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTCommonTest.class);
    
    WEB3AdminFPTCommon l_common = new WEB3AdminFPTCommon();

    public WEB3AdminFPTCommonTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testGetProductName_case0001()
    {
        final String STR_METHOD_NAME = "testGetProductName_case0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(IpoProductRow.TYPE);
            IpoProductParams l_ipoProductParams = new IpoProductParams();
            l_ipoProductParams.setIpoProductId(123456L);
            l_ipoProductParams.setInstitutionCode("0D");
            l_ipoProductParams.setProductCode("1234");
            l_ipoProductParams.setProductType(ProductTypeEnum.IPO);
            l_ipoProductParams.setIpoRegistDiv("1");
            l_ipoProductParams.setIpoRegistDetailDiv("1");
            l_ipoProductParams.setPublicMarket("10");
            l_ipoProductParams.setProvisionalValueDiv("1");
            l_ipoProductParams.setDeleteFlag(BooleanEnum.FALSE);
            l_ipoProductParams.setStandardName("xiaochai");
            TestDBUtility.insertWithDel(l_ipoProductParams);
            
            Institution l_institution = new WEB3GentradeInstitution(l_institutionParams);
            
            String l_strProductName = l_common.getProductName(l_institution, "1234", "1");
            assertEquals(l_strProductName,"xiaochai");
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(IpoProductRow.TYPE);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetProductName_case0002()
    {
        final String STR_METHOD_NAME = "testGetProductName_case0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(IpoProductRow.TYPE);
            IpoProductParams l_ipoProductParams = new IpoProductParams();
            l_ipoProductParams.setIpoProductId(123456L);
            l_ipoProductParams.setInstitutionCode("0D");
            l_ipoProductParams.setProductCode("1234");
            l_ipoProductParams.setProductType(ProductTypeEnum.IPO);
            l_ipoProductParams.setIpoRegistDiv("1");
            l_ipoProductParams.setIpoRegistDetailDiv("1");
            l_ipoProductParams.setPublicMarket("10");
            l_ipoProductParams.setProvisionalValueDiv("1");
            l_ipoProductParams.setDeleteFlag(BooleanEnum.FALSE);
            l_ipoProductParams.setStandardName("xiaochai");
            TestDBUtility.insertWithDel(l_ipoProductParams);
            
            Institution l_institution = new WEB3GentradeInstitution(l_institutionParams);
            
            String l_strProductName = l_common.getProductName(l_institution, "123", "1");
            assertEquals(l_strProductName,"");
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(IpoProductRow.TYPE);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetProductName_case0003()
    {
        final String STR_METHOD_NAME = "testGetProductName_case0003()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(IpoProductRow.TYPE);
            IpoProductParams l_ipoProductParams = new IpoProductParams();
            l_ipoProductParams.setIpoProductId(123456L);
            l_ipoProductParams.setInstitutionCode("0D");
            l_ipoProductParams.setProductCode("1234");
            l_ipoProductParams.setProductType(ProductTypeEnum.IPO);
            l_ipoProductParams.setIpoRegistDiv("1");
            l_ipoProductParams.setIpoRegistDetailDiv("1");
            l_ipoProductParams.setPublicMarket("10");
            l_ipoProductParams.setProvisionalValueDiv("1");
            l_ipoProductParams.setDeleteFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_ipoProductParams);
            
            Institution l_institution = new WEB3GentradeInstitution(l_institutionParams);
            
            String l_strProductName = l_common.getProductName(l_institution, "1234", "1");
            assertEquals(l_strProductName,"");
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(IpoProductRow.TYPE);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetProductName_case0004()
    {
        final String STR_METHOD_NAME = "testGetProductName_case0004()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setProductId(l_productParams.getProductId());
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setStandardName("xiao chai");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            
            Method method = WEB3AdminFPTCommon.class.getDeclaredMethod("getMFProductName", 
                    new Class[]{Institution.class, String.class});
                method.setAccessible(true);
                            
            MutualFundProduct l_mutualFundProduct = new WEB3MutualFundProduct(l_mutualFundProductParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.mf.WEB3MutualFundProductManager", "getMutualFundProduct", new Class[]
                    { Institution.class, String.class },
                    l_mutualFundProduct);
            
            Institution l_institution = new WEB3GentradeInstitution(l_institutionParams);
            
            String l_strProductName = l_common.getProductName(l_institution, "0001000", "2");
            assertEquals("xiao chai" , l_strProductName);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(IpoProductRow.TYPE);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetProductName_case0005()
    {
        final String STR_METHOD_NAME = "testGetProductName_case0005()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setProductId(l_productParams.getProductId());
            l_mutualFundProductParams.setStandardName("xiao chai");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            
            Method method = WEB3AdminFPTCommon.class.getDeclaredMethod("getMFProductName", 
                    new Class[]{Institution.class, String.class});
                method.setAccessible(true);
            
            Institution l_institution = new WEB3GentradeInstitution(l_institutionParams);
            
            String l_strProductName = l_common.getProductName(l_institution, "000100", "2");
            assertEquals("" , l_strProductName);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(IpoProductRow.TYPE);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetProductName_case0006()
    {
        final String STR_METHOD_NAME = "testGetProductName_case0006()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setProductId(l_productParams.getProductId());
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            
            Method method = WEB3AdminFPTCommon.class.getDeclaredMethod("getMFProductName", 
                    new Class[]{Institution.class, String.class});
                method.setAccessible(true);
                            
            MutualFundProduct l_mutualFundProduct = new WEB3MutualFundProduct(l_productParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.mf.WEB3MutualFundProductManager", "getMutualFundProduct", new Class[]
                    { Institution.class, String.class },
                    l_mutualFundProduct);
            
            Institution l_institution = new WEB3GentradeInstitution(l_institutionParams);
            
            String l_strProductName = l_common.getProductName(l_institution, "0001000", "2");
            assertEquals("" , l_strProductName);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(IpoProductRow.TYPE);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetProductName_case0007()
    {
        final String STR_METHOD_NAME = "testGetProductName_case0007()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);
            
            Institution l_institution = new WEB3GentradeInstitution(l_institutionParams);
            
            String l_strProductName = l_common.getProductName(l_institution, "0001000", "3");
            assertEquals("" , l_strProductName);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(IpoProductRow.TYPE);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetProductName_case0008()
    {
        final String STR_METHOD_NAME = "testGetProductName_case0008()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);
            
            Institution l_institution = new WEB3GentradeInstitution(l_institutionParams);
            
            String l_strProductName = l_common.getProductName(l_institution, "0001000", "4");
            assertEquals("" , l_strProductName);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(IpoProductRow.TYPE);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetProductName_case0009()
    {
        final String STR_METHOD_NAME = "testGetProductName_case0009()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);
            
            Institution l_institution = new WEB3GentradeInstitution(l_institutionParams);
            
            String l_strProductName = l_common.getProductName(l_institution, "0001000", "5");
            assertEquals("" , l_strProductName);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(IpoProductRow.TYPE);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetProductName_case0010()
    {
        final String STR_METHOD_NAME = "testGetProductName_case0010()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            String l_strProductName = l_common.getProductName(null, "0001000", "5");
            assertEquals("" , l_strProductName);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(IpoProductRow.TYPE);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.SYSTEM_ERROR_80017);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetIPOStandardName_case0001()
    {
        final String STR_METHOD_NAME = "testGetIPOStandardName_case0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(IpoProductRow.TYPE);
            IpoProductParams l_ipoProductParams = new IpoProductParams();
            l_ipoProductParams.setIpoProductId(123456L);
            l_ipoProductParams.setInstitutionCode("0D");
            l_ipoProductParams.setProductCode("1234");
            l_ipoProductParams.setProductType(ProductTypeEnum.IPO);
            l_ipoProductParams.setIpoRegistDiv("1");
            l_ipoProductParams.setIpoRegistDetailDiv("1");
            l_ipoProductParams.setPublicMarket("10");
            l_ipoProductParams.setProvisionalValueDiv("1");
            l_ipoProductParams.setDeleteFlag(BooleanEnum.FALSE);
            l_ipoProductParams.setStandardName("xiaochai");
            TestDBUtility.insertWithDel(l_ipoProductParams);
                        
            Method method = WEB3AdminFPTCommon.class.getDeclaredMethod("getIPOStandardName", 
                new Class[]{String.class, String.class});
            method.setAccessible(true);
            
            String l_str = (String)method.invoke(l_common, new Object[]{"0D", "1234"});
            assertEquals(l_str, "xiaochai");
            
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetIPOStandardName_case0002()
    {
        final String STR_METHOD_NAME = "testGetIPOStandardName_case0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(IpoProductRow.TYPE);
                        
            Method method = WEB3AdminFPTCommon.class.getDeclaredMethod("getIPOStandardName", 
                new Class[]{String.class, String.class});
            method.setAccessible(true);
            
            String l_str = (String)method.invoke(l_common, new Object[]{"0D", "1234"});
            assertEquals(l_str, null);
            
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetMFProductName_case0001()
    {
        final String STR_METHOD_NAME = "testGetMFProductName_case0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setProductId(l_productParams.getProductId());
            l_mutualFundProductParams.setStandardName("xiao chai");
            l_mutualFundProductParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            
            Method method = WEB3AdminFPTCommon.class.getDeclaredMethod("getMFProductName", 
                    new Class[]{Institution.class, String.class});
                method.setAccessible(true);
                
            Institution l_institution = new WEB3GentradeInstitution(l_institutionParams);
            
            MutualFundProduct l_mutualFundProduct = new WEB3MutualFundProduct(l_productParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.mf.WEB3MutualFundProductManager", "getMutualFundProduct", new Class[]
                    { Institution.class, String.class },
                    l_mutualFundProduct);
            
            String l_str = (String)method.invoke(l_common, new Object[]{l_institution, "0001000"});
            assertEquals(l_str, "xiao chai");
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
    }
    
    public void testGetMFProductName_case0002()
    {
        final String STR_METHOD_NAME = "testGetMFProductName_case0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setProductId(l_productParams.getProductId());
            l_mutualFundProductParams.setStandardName("xiao chai");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            
            Method method = WEB3AdminFPTCommon.class.getDeclaredMethod("getMFProductName", 
                    new Class[]{Institution.class, String.class});
                method.setAccessible(true);
                
            Institution l_institution = new WEB3GentradeInstitution(l_institutionParams);
            
            String l_str = (String)method.invoke(l_common, new Object[]{l_institution, "0001000"});
            assertEquals(l_str, null);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
    }
    
    public void testGetSystemPreferences()
    {
        final String STR_METHOD_NAME = "testGetSystemPreferences()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            String l_strValue = WEB3AdminFPTCommon.getSystemPreferences("123");
            assertNull(l_strValue);

            SystemPreferencesParams l_systemPreferencesParams = TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("123");
            l_systemPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_strValue = WEB3AdminFPTCommon.getSystemPreferences("123");
            assertEquals("1", l_strValue);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
    }

    public void testGetDocumentCategoryDetailsInfoUnit_case0001()
    {
        final String STR_METHOD_NAME = "testGetDocumentCategoryDetailsInfoUnit_case0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(DocDivManagementRow.TYPE);
            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);
            TestDBUtility.deleteAll(DocCategoryManagementParams.TYPE);

            DocCategoryManagementParams l_docCategoryManagementParams = new DocCategoryManagementParams();
            l_docCategoryManagementParams.setBranchCode("381");
            l_docCategoryManagementParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070521","yyyyMMdd"));
            l_docCategoryManagementParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070521","yyyyMMdd"));
            l_docCategoryManagementParams.setInstitutionCode("0D");
            l_docCategoryManagementParams.setDocumentCategory("111");
            l_docCategoryManagementParams.setDocumentCateName("test");
            TestDBUtility.insertWithDel(l_docCategoryManagementParams);
            assertEquals(0,
                WEB3AdminFPTCommon.getDocumentCategoryDetailsInfoUnit("0D", "381", "010", "111").length);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02998, l_ex.getErrorInfo());
        }
        try
        {
            DocDivManagementParams l_docDivManagementParams = new DocDivManagementParams();
            l_docDivManagementParams.setInstitutionCode("0D");
            l_docDivManagementParams.setBranchCode("381");
            l_docDivManagementParams.setDocumentDiv("010");
            l_docDivManagementParams.setDocumentCheckDiv("1");
            l_docDivManagementParams.setDocumentNumber("12");
            l_docDivManagementParams.setDocumentName("‚»‚±‚Å‚·");
            l_docDivManagementParams.setLastUpdater("12345");
            l_docDivManagementParams.setCreatedTimestamp(new Date());
            l_docDivManagementParams.setLastUpdatedTimestamp(new Date());
            l_docDivManagementParams.setDocumentCategory("111");
            TestDBUtility.insertWithDel(l_docDivManagementParams);

            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);

            BatoProductManagementParams l_batoProductManagementParams = new BatoProductManagementParams();
            l_batoProductManagementParams.setInstitutionCode("0D");
            l_batoProductManagementParams.setBranchCode("381");
            l_batoProductManagementParams.setBatoProductCode("1111111");
            l_batoProductManagementParams.setDocumentDiv("010");
            l_batoProductManagementParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_batoProductManagementParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_batoProductManagementParams.setValidFlag("0");
            TestDBUtility.insertWithDel(l_batoProductManagementParams);

            assertEquals(1,
                WEB3AdminFPTCommon.getDocumentCategoryDetailsInfoUnit("0D", "381", "010", "111").length);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
    }


    public void testGetDocumentCategoryDetailsInfoUnit_case0002()
    {
        final String STR_METHOD_NAME = "testGetDocumentCategoryDetailsInfoUnit_case0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(DocDivManagementRow.TYPE);
            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);
            
            DocDivManagementParams l_docDivManagementParams = new DocDivManagementParams();
            l_docDivManagementParams.setInstitutionCode("0D");
            l_docDivManagementParams.setBranchCode("381");
            l_docDivManagementParams.setDocumentDiv("010");
            l_docDivManagementParams.setDocumentCheckDiv("1");
            l_docDivManagementParams.setDocumentNumber("12");
            l_docDivManagementParams.setDocumentName("‚»‚±‚Å‚·");
            l_docDivManagementParams.setLastUpdater("12345");
            l_docDivManagementParams.setCreatedTimestamp(new Date());
            l_docDivManagementParams.setLastUpdatedTimestamp(new Date());
            l_docDivManagementParams.setDocumentCategory("111");
            TestDBUtility.insertWithDel(l_docDivManagementParams);
            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);
            WEB3AdminFPTCommon.getDocumentCategoryDetailsInfoUnit("0D", "381", "010", "111");
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02999, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
    }
}
@
