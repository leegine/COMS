head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.38.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminAioSLRegistProductReferenceServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 担保登録銘柄照会サービスImplTest(WEB3AdminAioSLRegistProductReferenceServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/13 張騰宇 (中訊) 新規作成 モデル760
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;

import test.util.TestDBUtility;

import webbroker3.aio.data.SecurityProductParams;
import webbroker3.aio.data.SecurityProductRow;
import webbroker3.aio.message.WEB3AdminSLProductChangeInputRequest;
import webbroker3.aio.message.WEB3AdminSLProductRegiListRequest;
import webbroker3.aio.message.WEB3AdminSLProductRegiListResponse;
import webbroker3.aio.message.WEB3SLProductInfoUnit;
import webbroker3.aio.message.WEB3SLSortKey;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdminPermissionRow;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminAioSLRegistProductReferenceServiceImplTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioSLRegistProductReferenceServiceImplTest.class);

    public WEB3AdminAioSLRegistProductReferenceServiceImplTest(String arg0)
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
     * execute
     */
    public void testExecute0001()
    {
        final String STR_METHOD_NAME = "testExecute0001";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminAioSLRegistProductReferenceServiceImpl l_impl =
                new WEB3AdminAioSLRegistProductReferenceServiceImpl();
            WEB3AdminSLProductRegiListRequest l_request = null;
            l_impl.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.SYSTEM_ERROR_80017);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }

        
        log.exiting(STR_METHOD_NAME);

    }
    
    public void testExecute0002()
    {
        final String STR_METHOD_NAME = "testExecute0002";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminAioSLRegistProductReferenceServiceImpl l_impl =
                new WEB3AdminAioSLRegistProductReferenceServiceImpl();
            WEB3AdminSLProductChangeInputRequest l_request = new WEB3AdminSLProductChangeInputRequest();
            l_impl.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.SYSTEM_ERROR_80018);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }

        
        log.exiting(STR_METHOD_NAME);

    }
    
    public void testExecute0003()
    {
        final String STR_METHOD_NAME = "testExecute0003";
        log.entering(STR_METHOD_NAME);

        try
        {
            EqtypeProductParams l_eqTypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqTypeProductParams.setProductId(3304148080000L);
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            TestDBUtility.insertWithDel(l_eqTypeProductParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setStandardName("シンセンテルス");
            l_productParams.setProductId(3304148080000L);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.insertWithDel(l_productParams);
            
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0L);
            l_administratorParams.setAdministratorId(33381330001L);
            l_administratorParams.setAdministratorCode("330001");
            l_administratorParams.setInstitutionId(33L);
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setPermissionLevel("331");
            
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);
            
            AdminPermissionParams l_adminPerissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPerissionParams.setInstitutionCode("0D");
            l_adminPerissionParams.setPermissionLevel("331");
            l_adminPerissionParams.setTransactionCategory("B0602");
            l_adminPerissionParams.setUpdatePermissionFlag(BooleanEnum.FALSE);
            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            TestDBUtility.insertWithDel(l_adminPerissionParams);
            
            SecurityProductParams l_securityProductParams = TestDBUtility.getSecurityProductRow();
            l_securityProductParams.setProductType(ProductTypeEnum.AIO);
            l_securityProductParams.setProductId(3304148080000L);
            l_securityProductParams.setProductCode("1234567");
            l_securityProductParams.setEstimationRatio(100);
            l_securityProductParams.setFitFlg("1");
            l_securityProductParams.setApplyTermFrom(WEB3DateUtility.getDate("20040917","yyyyMMdd"));
            l_securityProductParams.setApplyTermTo(WEB3DateUtility.getDate("20190917","yyyyMMdd"));
            l_securityProductParams.setInstitutionCode("0D");
            TestDBUtility.deleteAll(SecurityProductRow.TYPE);
            TestDBUtility.insertWithDel(l_securityProductParams);
            

            
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(0));
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getLoginInfo",
                    new Class[] {},
                    new LoginInfoImpl());

            WEB3AdminAioSLRegistProductReferenceServiceImpl l_impl =
                new WEB3AdminAioSLRegistProductReferenceServiceImpl();
            WEB3AdminSLProductRegiListRequest l_request = new WEB3AdminSLProductRegiListRequest();
            l_request.productType = "8";
            l_request.productCode = "123456";
            l_request.qualifiedDiv = "1";
            l_request.targetPeriodDiv = "0";
            l_request.pageIndex = "1";
            l_request.pageSize = "1";
            WEB3SLSortKey[] l_sortKeys = new WEB3SLSortKey[2];
            WEB3SLSortKey l_sortKey1 = new WEB3SLSortKey();
            l_sortKey1.keyItem = "productType";
            l_sortKey1.ascDesc = "A";
            WEB3SLSortKey l_sortKey2 = new WEB3SLSortKey();
            l_sortKey2.keyItem = "productCode";
            l_sortKey2.ascDesc = "A";
            
            l_sortKeys[0] = l_sortKey1;
            l_sortKeys[1] = l_sortKey2;
            l_request.sortKeys = l_sortKeys;
            l_impl.execute(l_request);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }

        
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * createソートキー
     *
     */
    public void test_createSortKey_0001()
    {
        final String STR_METHOD_NAME = "test_createSortKey_0001";
        log.entering(STR_METHOD_NAME);
        WEB3AdminAioSLRegistProductReferenceServiceImpl l_impl =
            new WEB3AdminAioSLRegistProductReferenceServiceImpl();
        WEB3SLSortKey l_sortKeys0 = new WEB3SLSortKey();
        WEB3SLSortKey l_sortKeys1 = new WEB3SLSortKey();
        WEB3SLSortKey l_sortKeys2 = new WEB3SLSortKey();
        
        
        l_sortKeys0.keyItem = "productType";
        l_sortKeys0.ascDesc = "A";
        
        l_sortKeys1.keyItem = "productCode";
        l_sortKeys1.ascDesc = "D";

        l_sortKeys2.keyItem = "targetPeriodFrom";
        l_sortKeys2.ascDesc = "A";
        
        WEB3SLSortKey[] l_sortKeys = new WEB3SLSortKey[]{l_sortKeys0,l_sortKeys1,l_sortKeys2};
        
        Method method;
        try
        {
            method = WEB3AdminAioSLRegistProductReferenceServiceImpl.class.getDeclaredMethod("createSortKey", 
                    new Class[]{WEB3SLSortKey[].class});
            method.setAccessible(true);
            String l_str =
                (String)method.invoke(l_impl, new Object[]{l_sortKeys});
            
            assertEquals(" product_type " + "asc," + " product_code " + "desc," + " apply_term_from " + "asc", l_str);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }

        
        log.exiting(STR_METHOD_NAME);

    }
    
    public void testCreateSLProductInfoUnitCase1()
    {
        final String STR_METHOD_NAME = "testCreateSLProductInfoUnitCase1()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setStandardName("シンセンテルス");
            l_productParams.setProductId(3304148080000L);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.insertWithDel(l_productParams);
            
            WEB3AdminAioSLRegistProductReferenceServiceImpl l_impl =
                new WEB3AdminAioSLRegistProductReferenceServiceImpl();
            SecurityProductParams l_securityProductParams = TestDBUtility.getSecurityProductRow();
            l_securityProductParams.setProductType(null);
            l_securityProductParams.setProductId(3304148080000L);
            l_securityProductParams.setProductCode("1");
            l_securityProductParams.setProductType(ProductTypeEnum.AIO);
            l_securityProductParams.setEstimationRatio(100);
            l_securityProductParams.setFitFlg("0");
            l_securityProductParams.setApplyTermFrom(WEB3DateUtility.getDate("20070917","yyyyMMdd"));
            
            Method method =
                WEB3AdminAioSLRegistProductReferenceServiceImpl.class.getDeclaredMethod("createSLProductInfoUnit", 
                        new Class[]{SecurityProductParams.class});
            method.setAccessible(true);
            WEB3SLProductInfoUnit l_slProductInfoUnit =
                (WEB3SLProductInfoUnit)method.invoke(l_impl, new Object[]{l_securityProductParams});
            
            assertEquals(l_slProductInfoUnit.productId, 3304148080000L);
            assertEquals(l_slProductInfoUnit.productCode, "1");
            assertEquals(l_slProductInfoUnit.productType.toString(), "8");
            assertEquals(l_slProductInfoUnit.productName, "シンセンテルス");
            assertEquals(l_slProductInfoUnit.qualifiedDiv, "0");
            assertEquals(l_slProductInfoUnit.weight, "100.0");
            assertEquals(WEB3DateUtility.formatDate(l_slProductInfoUnit.targetPeriodFrom, "yyyyMMdd"), "20070917");
            
            assertNull(l_slProductInfoUnit.targetPeriodTo);
            assertNull(l_slProductInfoUnit.reason);
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateSLProductInfoUnitCase2()
    {
        final String STR_METHOD_NAME = "testCreateSLProductInfoUnitCase2()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setStandardName("シンセンテルス");
            l_productParams.setProductId(3304148080000L);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.insertWithDel(l_productParams);
            
            WEB3AdminAioSLRegistProductReferenceServiceImpl l_impl =
                new WEB3AdminAioSLRegistProductReferenceServiceImpl();
            SecurityProductParams l_securityProductParams = TestDBUtility.getSecurityProductRow();
            l_securityProductParams.setProductType(null);
            l_securityProductParams.setProductId(3304148080000L);
            l_securityProductParams.setProductCode("1");
            l_securityProductParams.setProductType(ProductTypeEnum.AIO);
            l_securityProductParams.setEstimationRatio(100);
            l_securityProductParams.setFitFlg("0");
            l_securityProductParams.setApplyTermFrom(WEB3DateUtility.getDate("20070917","yyyyMMdd"));
            
            l_securityProductParams.setApplyTermTo(WEB3DateUtility.getDate("20070917","yyyyMMdd"));
            l_securityProductParams.setReason("zhu");
            
            Method method =
                WEB3AdminAioSLRegistProductReferenceServiceImpl.class.getDeclaredMethod("createSLProductInfoUnit", 
                        new Class[]{SecurityProductParams.class});
            method.setAccessible(true);
            WEB3SLProductInfoUnit l_slProductInfoUnit =
                (WEB3SLProductInfoUnit)method.invoke(l_impl, new Object[]{l_securityProductParams});
            
            assertEquals(l_slProductInfoUnit.productId, 3304148080000L);
            assertEquals(l_slProductInfoUnit.productCode, "1");
            assertEquals(l_slProductInfoUnit.productType.toString(), "8");
            assertEquals(l_slProductInfoUnit.productName, "シンセンテルス");
            assertEquals(l_slProductInfoUnit.qualifiedDiv, "0");
            assertEquals(l_slProductInfoUnit.weight, "100.0");
            assertEquals(WEB3DateUtility.formatDate(l_slProductInfoUnit.targetPeriodFrom, "yyyyMMdd"), "20070917");
            
            assertEquals(WEB3DateUtility.formatDate(l_slProductInfoUnit.targetPeriodTo, "yyyyMMdd"), "20070917");
            assertEquals(l_slProductInfoUnit.reason, "zhu");
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //create検索条件データコンテナ 
    public void testCreateQueryDataContainerCase1()
    {
        final String STR_METHOD_NAME = "testCreateQueryDataContainerCase1()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            
            WEB3AdminAioSLRegistProductReferenceServiceImpl l_impl =
                new WEB3AdminAioSLRegistProductReferenceServiceImpl();
            WEB3AdminSLProductRegiListRequest l_request = new WEB3AdminSLProductRegiListRequest();
            l_request.productType = "1";
            l_request.productCode = "123";
            l_request.qualifiedDiv = "1";
            l_request.targetPeriodDiv = "1";
            l_request.pageIndex = "1";
            l_request.pageSize = "1";
            
            Method method =
                WEB3AdminAioSLRegistProductReferenceServiceImpl.class.getDeclaredMethod("createQueryDataContainer", 
                        new Class[]{String.class, WEB3AdminSLProductRegiListRequest.class});
            method.setAccessible(true);
            Object[] l_objs =
                (Object[])method.invoke(l_impl, new Object[]{"10", l_request});
            assertEquals(l_objs.length, 6);
            assertEquals(l_objs[0].toString(), "10");//証券会社コード
            assertEquals(l_objs[1].toString(), "1");//銘柄タイプ
            assertEquals(l_objs[2].toString(), "123");//銘柄コード
            assertEquals(l_objs[3].toString(), "1");//適格区分
            assertEquals(WEB3DateUtility.formatDate((Date)l_objs[4], "yyyyMMdd"),
                    WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(), "yyyyMMdd"));//適用期間from
            assertEquals(WEB3DateUtility.formatDate((Date)l_objs[5], "yyyyMMdd"),
                    WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(), "yyyyMMdd"));//適用期間to

        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //create検索条件データコンテナ 
    public void testCreateQueryDataContainerCase2()
    {
        final String STR_METHOD_NAME = "testCreateQueryDataContainerCase2()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            
            WEB3AdminAioSLRegistProductReferenceServiceImpl l_impl =
                new WEB3AdminAioSLRegistProductReferenceServiceImpl();
            WEB3AdminSLProductRegiListRequest l_request = new WEB3AdminSLProductRegiListRequest();
//            l_request.productType = "1";
//            l_request.productCode = "123";
//            l_request.qualifiedDiv = "1";
//            l_request.targetPeriodDiv = "1";
//            l_request.pageIndex = "1";
//            l_request.pageSize = "1";
            
            Method method =
                WEB3AdminAioSLRegistProductReferenceServiceImpl.class.getDeclaredMethod("createQueryDataContainer", 
                        new Class[]{String.class, WEB3AdminSLProductRegiListRequest.class});
            method.setAccessible(true);
            Object[] l_objs =
                (Object[])method.invoke(l_impl, new Object[]{"10", l_request});
            assertEquals(l_objs.length, 1);
            assertEquals(l_objs[0].toString(), "10");//証券会社コード
//            assertEquals(l_objs[1].toString(), "1");//銘柄タイプ
//            assertEquals(l_objs[2].toString(), "123");//銘柄コード
//            assertEquals(l_objs[3].toString(), "1");//適格区分
//            assertEquals(WEB3DateUtility.formatDate((Date)l_objs[4], "yyyyMMdd"),
//                    WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(), "yyyyMMdd"));//適用期間from
//            assertEquals(WEB3DateUtility.formatDate((Date)l_objs[5], "yyyyMMdd"),
//                    WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(), "yyyyMMdd"));//適用期間to

        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //create検索条件文字列
    public void testCreateQueryStringrCase1()
    {
        final String STR_METHOD_NAME = "testCreateQueryStringrCase1()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3AdminAioSLRegistProductReferenceServiceImpl l_impl =
                new WEB3AdminAioSLRegistProductReferenceServiceImpl();
            WEB3AdminSLProductRegiListRequest l_request = new WEB3AdminSLProductRegiListRequest();
//            l_request.productType = "1";
//            l_request.productCode = "123";
//            l_request.qualifiedDiv = "1";
//            l_request.targetPeriodDiv = "1";
//            l_request.pageIndex = "1";
//            l_request.pageSize = "1";
            
            Method method =
                WEB3AdminAioSLRegistProductReferenceServiceImpl.class.getDeclaredMethod("createQueryString", 
                        new Class[]{WEB3AdminSLProductRegiListRequest.class});
            method.setAccessible(true);
            String l_str =
                (String)method.invoke(l_impl, new Object[]{l_request});
            assertEquals(l_str, "institution_code = ? ");

        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //create検索条件文字列
    public void testCreateQueryStringrCase2()
    {
        final String STR_METHOD_NAME = "testCreateQueryStringrCase2()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3AdminAioSLRegistProductReferenceServiceImpl l_impl =
                new WEB3AdminAioSLRegistProductReferenceServiceImpl();
            WEB3AdminSLProductRegiListRequest l_request = new WEB3AdminSLProductRegiListRequest();
            l_request.productType = "1";
            l_request.productCode = "123";
            l_request.qualifiedDiv = "1";
            l_request.targetPeriodDiv = "1";
//            l_request.pageIndex = "1";
//            l_request.pageSize = "1";
            
            Method method =
                WEB3AdminAioSLRegistProductReferenceServiceImpl.class.getDeclaredMethod("createQueryString", 
                        new Class[]{WEB3AdminSLProductRegiListRequest.class});
            method.setAccessible(true);
            String l_str =
                (String)method.invoke(l_impl, new Object[]{l_request});
            assertEquals(l_str,
                "institution_code = ? and product_type = ? and product_code like ? || '%' and fit_flg = ?" +
                " and (apply_term_from > ? or apply_term_to < ? ) ");

        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //create検索条件文字列
    public void testCreateQueryStringrCase3()
    {
        final String STR_METHOD_NAME = "testCreateQueryStringrCase3()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3AdminAioSLRegistProductReferenceServiceImpl l_impl =
                new WEB3AdminAioSLRegistProductReferenceServiceImpl();
            WEB3AdminSLProductRegiListRequest l_request = new WEB3AdminSLProductRegiListRequest();
            l_request.productType = "1";
            l_request.productCode = "123";
            l_request.qualifiedDiv = "1";
            l_request.targetPeriodDiv = "0";
            
            Method method =
                WEB3AdminAioSLRegistProductReferenceServiceImpl.class.getDeclaredMethod("createQueryString", 
                        new Class[]{WEB3AdminSLProductRegiListRequest.class});
            method.setAccessible(true);
            String l_str =
                (String)method.invoke(l_impl, new Object[]{l_request});
            assertEquals(l_str,
                "institution_code = ? and product_type = ? and product_code like ? || '%' and fit_flg = ? " +
                "and apply_term_from <= ? and (apply_term_to >= ? or apply_term_to is null)");

        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 
     *
     */
    public void testCreateQueryStringrCase4()
    {
        final String STR_METHOD_NAME = "testCreateQueryStringrCase4()";
        log.entering(STR_METHOD_NAME);
        String l_str = null;
        
        try
        {
            SecurityProductParams l_securityProductParams = TestDBUtility.getSecurityProductRow();
            l_securityProductParams.setProductType(null);
            l_securityProductParams.setProductId(3304148080000L);
            l_securityProductParams.setInstitutionCode("23");
            l_securityProductParams.setProductCode("23");
            l_securityProductParams.setProductType(ProductTypeEnum.AIO);
            l_securityProductParams.setEstimationRatio(100);
            l_securityProductParams.setFitFlg("1");
            l_securityProductParams.setApplyTermFrom(WEB3DateUtility.getDate("20070917","yyyyMMdd"));
            l_securityProductParams.setApplyTermTo(null);
            
            TestDBUtility.deleteAll(SecurityProductRow.TYPE);
            TestDBUtility.insertWithDel(l_securityProductParams);
            
            WEB3AdminAioSLRegistProductReferenceServiceImpl l_impl =
                new WEB3AdminAioSLRegistProductReferenceServiceImpl();
            WEB3AdminSLProductRegiListRequest l_request = new WEB3AdminSLProductRegiListRequest();
            l_request.targetPeriodDiv = "0";
            
            Method method =
                WEB3AdminAioSLRegistProductReferenceServiceImpl.class.getDeclaredMethod("createQueryString", 
                        new Class[]{WEB3AdminSLProductRegiListRequest.class});
            method.setAccessible(true);
            l_str =
                (String)method.invoke(l_impl, new Object[]{l_request});
//            assertEquals(l_str,
//                "institution_code = ? and apply_term_from <= ? and (apply_term_to is null or apply_term_to >= ?)");

        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }
        
        Object[] l_bindVars =
        {"23", new Date(GtlUtils.getSystemTimestamp().getTime()), new Date(GtlUtils.getSystemTimestamp().getTime())};
        
        List l_lisRows = null;
        try
        {
            //getDefaultProcessor
            //Rowタイプ： 担保銘柄Row.TYPE
            //Where： create取得条件文字列()の戻り値
            //orderBy： createソートキー()の戻り値
            //condition： null
            //リスト： create検索条件データコンテナ()の戻り値
            //ページサイズ： リクエスト.ページ内表示行数
            //ページ番号： リクエスト.要求ページ番号 - 1
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRows = l_queryProcessor.doFindAllQuery(
                SecurityProductRow.TYPE,
                l_str,
                null,
                l_bindVars);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        assertEquals("23", ((SecurityProductRow)l_lisRows.get(0)).getProductCode());
        log.exiting(STR_METHOD_NAME);
    }
    
    //get担保登録銘柄一覧
    //validate権限(機@能カテゴリコード : String, is更新 : boolean)check失敗
    public void testGetSLProductRegiListCase1()
    {
        final String STR_METHOD_NAME = "testGetSLProductRegiListCase1()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0L);
            
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);
            
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(0));
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getLoginInfo",
                    new Class[] {},
                    new LoginInfoImpl());

            WEB3AdminAioSLRegistProductReferenceServiceImpl l_impl =
                new WEB3AdminAioSLRegistProductReferenceServiceImpl();
            WEB3AdminSLProductRegiListRequest l_request = new WEB3AdminSLProductRegiListRequest();
            l_request.productType = "1";
            l_request.productCode = "123";
            l_request.qualifiedDiv = "1";
            l_request.targetPeriodDiv = "0";
            l_request.pageIndex = "1";
            l_request.pageSize = "1";
            WEB3SLSortKey[] l_sortKeys = new WEB3SLSortKey[1];
            l_sortKeys[0] = new WEB3SLSortKey();
            l_request.sortKeys = l_sortKeys;
            l_impl.getSLProductRegiList(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.SYSTEM_ERROR_80003);
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //doFindAllQuery()でレコードが取得できない場合、
    //レスポンスオブジェクトを生成し、returnする。
    //修改為：検索対象レコードが存在しない場合、例外をスローする
    //BUSINESS_ERROR_00398
    public void testGetSLProductRegiListCase2()
    {
        final String STR_METHOD_NAME = "testGetSLProductRegiListCase2()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0L);
            l_administratorParams.setAdministratorId(33381330001L);
            l_administratorParams.setAdministratorCode("330001");
            l_administratorParams.setInstitutionId(33L);
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setPermissionLevel("331");
            
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);
            
            AdminPermissionParams l_adminPerissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPerissionParams.setInstitutionCode("0D");
            l_adminPerissionParams.setPermissionLevel("331");
            l_adminPerissionParams.setTransactionCategory("B0602");
            l_adminPerissionParams.setUpdatePermissionFlag(BooleanEnum.FALSE);
            
            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            TestDBUtility.insertWithDel(l_adminPerissionParams);
            
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(0));
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getLoginInfo",
                    new Class[] {},
                    new LoginInfoImpl());

            WEB3AdminAioSLRegistProductReferenceServiceImpl l_impl =
                new WEB3AdminAioSLRegistProductReferenceServiceImpl();
            WEB3AdminSLProductRegiListRequest l_request = new WEB3AdminSLProductRegiListRequest();
            l_request.productType = "1";
            l_request.productCode = "123";
            l_request.qualifiedDiv = "1";
            l_request.targetPeriodDiv = "0";
            l_request.pageIndex = "5";
            l_request.pageSize = "1";
            WEB3SLSortKey[] l_sortKeys = new WEB3SLSortKey[1];
            WEB3SLSortKey l_sortKey1 = new WEB3SLSortKey();
            l_sortKey1.keyItem = "productType";
            l_sortKey1.ascDesc = "A";
//            WEB3SLSortKey l_sortKey2 = new WEB3SLSortKey();
//            l_sortKey2.keyItem = "productCode";
//            l_sortKey2.ascDesc = "A";
            
            l_sortKeys[0] = l_sortKey1;
//            l_sortKeys[1] = l_sortKey2;
            l_request.sortKeys = l_sortKeys;
            WEB3AdminSLProductRegiListResponse l_response =
                l_impl.getSLProductRegiList(l_request);
//            assertNull(l_response.stockLoanProductInfoList);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00398);
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetSLProductRegiListCase3()
    {
        final String STR_METHOD_NAME = "testGetSLProductRegiListCase3()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setStandardName("シンセンテルス");
            l_productParams.setProductId(3304148080000L);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.insertWithDel(l_productParams);
            
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0L);
            l_administratorParams.setAdministratorId(33381330001L);
            l_administratorParams.setAdministratorCode("330001");
            l_administratorParams.setInstitutionId(33L);
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setPermissionLevel("331");
            
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);
            
            AdminPermissionParams l_adminPerissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPerissionParams.setInstitutionCode("0D");
            l_adminPerissionParams.setPermissionLevel("331");
            l_adminPerissionParams.setTransactionCategory("B0602");
            l_adminPerissionParams.setUpdatePermissionFlag(BooleanEnum.FALSE);
            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            TestDBUtility.insertWithDel(l_adminPerissionParams);
            
            SecurityProductParams l_securityProductParams = TestDBUtility.getSecurityProductRow();
            l_securityProductParams.setProductType(null);
            l_securityProductParams.setProductId(3304148080000L);
            l_securityProductParams.setProductCode("1234567");
            l_securityProductParams.setProductType(ProductTypeEnum.AIO);
            l_securityProductParams.setEstimationRatio(100);
            l_securityProductParams.setFitFlg("1");
            l_securityProductParams.setApplyTermFrom(WEB3DateUtility.getDate("20070917","yyyyMMdd"));
            
            TestDBUtility.deleteAll(SecurityProductRow.TYPE);
            TestDBUtility.insertWithDel(l_securityProductParams);
            

            
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(0));
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getLoginInfo",
                    new Class[] {},
                    new LoginInfoImpl());

            WEB3AdminAioSLRegistProductReferenceServiceImpl l_impl =
                new WEB3AdminAioSLRegistProductReferenceServiceImpl();
            WEB3AdminSLProductRegiListRequest l_request = new WEB3AdminSLProductRegiListRequest();
            l_request.pageIndex = "1";
            l_request.pageSize = "1";
            WEB3SLSortKey[] l_sortKeys = new WEB3SLSortKey[2];
            WEB3SLSortKey l_sortKey1 = new WEB3SLSortKey();
            l_sortKey1.keyItem = "productType";
            l_sortKey1.ascDesc = "A";
            WEB3SLSortKey l_sortKey2 = new WEB3SLSortKey();
            l_sortKey2.keyItem = "productCode";
            l_sortKey2.ascDesc = "A";
            
            l_sortKeys[0] = l_sortKey1;
            l_sortKeys[1] = l_sortKey2;
            l_request.sortKeys = l_sortKeys;
            
            WEB3AdminSLProductRegiListResponse l_response =
                l_impl.getSLProductRegiList(l_request);
            assertEquals(l_response.totalPages, "1");
            assertEquals(l_response.totalRecords, "1");
            assertEquals(l_response.pageIndex, "1");
            
            WEB3SLProductInfoUnit[] l_productInfoUnits =
                l_response.stockLoanProductInfoList;
            assertEquals(l_productInfoUnits.length, 1);
            
            
            assertEquals(l_productInfoUnits[0].productId, 3304148080000L);
            assertEquals(l_productInfoUnits[0].productCode, "1234567");
            assertEquals(l_productInfoUnits[0].productType.toString(), "8");
            assertEquals(l_productInfoUnits[0].productName, "シンセンテルス");
            assertEquals(l_productInfoUnits[0].qualifiedDiv, "1");
            assertEquals(l_productInfoUnits[0].weight, "100.0");
            assertEquals(WEB3DateUtility.formatDate(l_productInfoUnits[0].targetPeriodFrom, "yyyyMMdd"), "20070917");
            
            assertNull(l_productInfoUnits[0].targetPeriodTo);
            assertNull(l_productInfoUnits[0].reason);
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetSLProductRegiListCase4()
    {
        final String STR_METHOD_NAME = "testGetSLProductRegiListCase4()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setStandardName("シンセンテルス");
            l_productParams.setProductId(3304148080000L);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.insertWithDel(l_productParams);
            
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0L);
            l_administratorParams.setAdministratorId(33381330001L);
            l_administratorParams.setAdministratorCode("330001");
            l_administratorParams.setInstitutionId(33L);
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setPermissionLevel("331");
            
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);
            
            AdminPermissionParams l_adminPerissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPerissionParams.setInstitutionCode("0D");
            l_adminPerissionParams.setPermissionLevel("331");
            l_adminPerissionParams.setTransactionCategory("B0602");
            l_adminPerissionParams.setUpdatePermissionFlag(BooleanEnum.FALSE);
            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            TestDBUtility.insertWithDel(l_adminPerissionParams);
            
            SecurityProductParams l_securityProductParams = TestDBUtility.getSecurityProductRow();
            l_securityProductParams.setProductType(null);
            l_securityProductParams.setProductId(3304148080000L);
            l_securityProductParams.setProductCode("1234567");
            l_securityProductParams.setProductType(ProductTypeEnum.AIO);
            l_securityProductParams.setEstimationRatio(100);
            l_securityProductParams.setFitFlg("1");
            l_securityProductParams.setApplyTermFrom(WEB3DateUtility.getDate("20040917","yyyyMMdd"));
            l_securityProductParams.setApplyTermTo(WEB3DateUtility.getDate("20190917","yyyyMMdd"));
            
            TestDBUtility.deleteAll(SecurityProductRow.TYPE);
            TestDBUtility.insertWithDel(l_securityProductParams);
            

            
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(0));
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getLoginInfo",
                    new Class[] {},
                    new LoginInfoImpl());

            WEB3AdminAioSLRegistProductReferenceServiceImpl l_impl =
                new WEB3AdminAioSLRegistProductReferenceServiceImpl();
            WEB3AdminSLProductRegiListRequest l_request = new WEB3AdminSLProductRegiListRequest();
            l_request.productType = "8";
            l_request.productCode = "123456";
            l_request.qualifiedDiv = "1";
            l_request.targetPeriodDiv = "0";
            l_request.pageIndex = "1";
            l_request.pageSize = "1";
            WEB3SLSortKey[] l_sortKeys = new WEB3SLSortKey[2];
            WEB3SLSortKey l_sortKey1 = new WEB3SLSortKey();
            l_sortKey1.keyItem = "productType";
            l_sortKey1.ascDesc = "A";
            WEB3SLSortKey l_sortKey2 = new WEB3SLSortKey();
            l_sortKey2.keyItem = "productCode";
            l_sortKey2.ascDesc = "A";
            
            l_sortKeys[0] = l_sortKey1;
            l_sortKeys[1] = l_sortKey2;
            l_request.sortKeys = l_sortKeys;
            
            WEB3AdminSLProductRegiListResponse l_response =
                l_impl.getSLProductRegiList(l_request);
            assertEquals(l_response.totalPages, "1");
            assertEquals(l_response.totalRecords, "1");
            assertEquals(l_response.pageIndex, "1");
            
            WEB3SLProductInfoUnit[] l_productInfoUnits =
                l_response.stockLoanProductInfoList;
            assertEquals(l_productInfoUnits.length, 1);
            
            
            assertEquals(l_productInfoUnits[0].productId, 3304148080000L);
            assertEquals(l_productInfoUnits[0].productCode, "1234567");
            assertEquals(l_productInfoUnits[0].productType.toString(), "8");
            assertEquals(l_productInfoUnits[0].productName, "シンセンテルス");
            assertEquals(l_productInfoUnits[0].qualifiedDiv, "1");
            assertEquals(l_productInfoUnits[0].weight, "100.0");
            assertEquals(WEB3DateUtility.formatDate(l_productInfoUnits[0].targetPeriodFrom, "yyyyMMdd"), "20040917");
            
            assertNull(l_productInfoUnits[0].reason);
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
}
@
