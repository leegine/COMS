head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.25.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminFPTListReferenceServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.docadmin.service.delegate.stdimpls;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.docadmin.message.WEB3AdminFPTListReferenceRequest;
import webbroker3.docadmin.message.WEB3AdminFPTListReferenceResponse;
import webbroker3.docadmin.message.WEB3AdminFPTRegistCompleteRequest;
import webbroker3.docadmin.message.WEB3AdminFPTSearchInputRequest;
import webbroker3.docadmin.message.WEB3AdminFPTSearchInputResponse;
import webbroker3.docadmin.message.WEB3AdminFPTSortKey;
import webbroker3.docadmin.message.WEB3FPTDocumentDivAdminInfoUnit;
import webbroker3.docadmin.message.WEB3FPTHistoryInfoUnit;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorTypeParams;
import webbroker3.gentrade.data.DocCategoryManagementParams;
import webbroker3.gentrade.data.DocDeliveryManagementParams;
import webbroker3.gentrade.data.DocDivManagementParams;
import webbroker3.gentrade.data.DocDivManagementRow;
import webbroker3.ipo.data.IpoProductParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminFPTListReferenceServiceImplTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTListReferenceServiceImplTest.class);

    public WEB3AdminFPTListReferenceServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        this.deleteDB();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
        this.deleteDB();
    }

    public void testCreateHistoryInfoUnit_0001()
    {
        final String STR_METHOD_NAME = " testCreateHistoryInfoUnit_0001()";
        log.entering(STR_METHOD_NAME);

        DocDeliveryManagementParams l_params = new DocDeliveryManagementParams();
        l_params.setInstitutionCode("123");
        l_params.setBranchCode("123");
        l_params.setDocumentDiv("1");
        WEB3AdminFPTListReferenceServiceImpl l_impl = new WEB3AdminFPTListReferenceServiceImpl();
        try
        {
            TestDBUtility.deleteAll(DocDivManagementRow.TYPE);
            
            Method l_method = WEB3AdminFPTListReferenceServiceImpl.class.getDeclaredMethod(
                "createHistoryInfoUnit",
                new Class[]{DocDeliveryManagementParams.class, WEB3GentradeInstitution.class});
            l_method.setAccessible(true);
            l_method.invoke(l_impl, new Object[]{l_params, null});
            
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (InvocationTargetException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01279, ((WEB3BaseException)l_ex.getTargetException()).getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testCreateHistoryInfoUnit_0002()
    {
        final String STR_METHOD_NAME = " testCreateHistoryInfoUnit_0002()";
        log.entering(STR_METHOD_NAME);

        Date l_newDate = new Date();
        DocDeliveryManagementParams l_params = new DocDeliveryManagementParams();
        l_params.setInstitutionCode("0D");
        l_params.setBranchCode("381");
        l_params.setDocumentDiv("1");
        l_params.setAccountCode("2512246");
        l_params.setProductCode("N8080");
        l_params.setDeliveryDate(l_newDate);
        l_params.setDeleteFlag(BooleanEnum.FALSE);
        l_params.setLastUpdater("123456");
        l_params.setCreatedTimestamp(l_newDate);
        l_params.setLastUpdatedTimestamp(l_newDate);
        l_params.setDocumentCategory("001");
        
        WEB3AdminFPTListReferenceServiceImpl l_impl = new WEB3AdminFPTListReferenceServiceImpl();
        try
        {
            TestDBUtility.deleteAll(DocDivManagementRow.TYPE);
            DocDivManagementParams l_docDivManagementParams = new DocDivManagementParams();
            l_docDivManagementParams.setInstitutionCode("0D");
            l_docDivManagementParams.setBranchCode("381");
            l_docDivManagementParams.setDocumentDiv("1");
            l_docDivManagementParams.setDocumentCheckDiv("1");
            l_docDivManagementParams.setDocumentNumber("12");
            l_docDivManagementParams.setDocumentName("そこです");
            l_docDivManagementParams.setLastUpdater("12345");
            l_docDivManagementParams.setDocumentCategory("001");
            l_docDivManagementParams.setCreatedTimestamp(new Date());
            l_docDivManagementParams.setLastUpdatedTimestamp(new Date());
            l_docDivManagementParams.setDocumentCategory("001");
            TestDBUtility.insertWithDel(l_docDivManagementParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(TestDBUtility.getInstitutionRow());
            WEB3GentradeInstitution l_gentradeInstitution = new WEB3GentradeInstitution("0D");

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.insertWithDel(TestDBUtility.getMainAccountRow());

            IpoProductParams l_ipoProductParams = new IpoProductParams();
            //IPO銘柄ＩＤ
            l_ipoProductParams.setIpoProductId(123456);
            //証券会社コード
            l_ipoProductParams.setInstitutionCode("0D");
            //銘柄コード
            l_ipoProductParams.setProductCode("N8080");
            //銘柄タイプ
            l_ipoProductParams.setProductType(ProductTypeEnum.OTHER);
            //IPO登録区分
            l_ipoProductParams.setIpoRegistDiv("1");
            //IPO登録区分詳細
            l_ipoProductParams.setIpoRegistDetailDiv("1");
            l_ipoProductParams.setDeleteFlag(BooleanEnum.FALSE);
            //公開市場
            l_ipoProductParams.setPublicMarket("1");
            //仮条件区分
            l_ipoProductParams.setProvisionalValueDiv("1");
            l_ipoProductParams.setStandardName("田中さん");
            TestDBUtility.deleteAll(IpoProductParams.TYPE);
            TestDBUtility.insertWithDel(l_ipoProductParams);

            //MainAccountParams
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setBranchCode("381");
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setAccountCode("2512246");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            //InstitutionParams
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);
            
            //DocCategoryManagementParams
            DocCategoryManagementParams l_docCategoryParams =
                this.getDocCategoryManagementRow();
            l_docCategoryParams.setDocumentCategory("001");
            l_docCategoryParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_docCategoryParams);
            
            Method l_method = WEB3AdminFPTListReferenceServiceImpl.class.getDeclaredMethod(
                "createHistoryInfoUnit",
                new Class[]{DocDeliveryManagementParams.class, WEB3GentradeInstitution.class});
            l_method.setAccessible(true);
            WEB3FPTHistoryInfoUnit l_fPTHistoryInfoUnit =
                (WEB3FPTHistoryInfoUnit)l_method.invoke(l_impl, new Object[]{l_params, l_gentradeInstitution});

            //部店コード
            assertEquals("381", l_fPTHistoryInfoUnit.branchCode);
            //顧客コード
            assertEquals("251224", l_fPTHistoryInfoUnit.acceptCode);
            //電子鳩銘柄コード
            assertEquals("N8080", l_fPTHistoryInfoUnit.batoProductCode);
            //銘柄名
            assertEquals("", l_fPTHistoryInfoUnit.productName);
            //書面区分
            assertEquals("1", l_fPTHistoryInfoUnit.documentDiv);
            //書面名称
            assertEquals("そこです", l_fPTHistoryInfoUnit.documentNames);
            //書面交付日
            assertEquals(l_newDate, l_fPTHistoryInfoUnit.docuDeliDate);
            //削除フラグ
            assertEquals("0", l_fPTHistoryInfoUnit.deleteFlg);
            //更新者コード
            assertEquals("123456", l_fPTHistoryInfoUnit.updaterCode);
            //作成日付
            assertEquals(l_newDate, l_fPTHistoryInfoUnit.createDate);
            assertEquals(l_newDate, l_fPTHistoryInfoUnit.updateTimeStamp);
            
            //書面種類コード
            assertEquals("001", l_fPTHistoryInfoUnit.documentCategory);
            //書面種類名称
            assertEquals("jiddk", l_fPTHistoryInfoUnit.documentCategoryName);
            //書面種類通番
            assertEquals("8080", l_fPTHistoryInfoUnit.documentCategoryNumber);
            

            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateSortKey_0001()
    {
        final String STR_METHOD_NAME = " testCreateSortKey_0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminFPTListReferenceServiceImpl l_impl = new WEB3AdminFPTListReferenceServiceImpl();
            Method l_method = WEB3AdminFPTListReferenceServiceImpl.class.getDeclaredMethod(
                "createSortKey",
                new Class[]{WEB3AdminFPTSortKey[].class});
            l_method.setAccessible(true);
            String l_strSortKey =
                (String)l_method.invoke(l_impl, new Object[]{new WEB3AdminFPTSortKey[0]});

            assertEquals("", l_strSortKey);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateSortKey_0002()
    {
        final String STR_METHOD_NAME = " testCreateSortKey_0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminFPTSortKey l_sorKey = new WEB3AdminFPTSortKey();
            l_sorKey.keyItem = "branchCode";
            l_sorKey.ascDesc = "A";
            
            WEB3AdminFPTSortKey[] l_adminFPTSortKeys = new WEB3AdminFPTSortKey[1];
            l_adminFPTSortKeys[0] = l_sorKey;

            WEB3AdminFPTListReferenceServiceImpl l_impl = new WEB3AdminFPTListReferenceServiceImpl();
            Method l_method = WEB3AdminFPTListReferenceServiceImpl.class.getDeclaredMethod(
                "createSortKey",
                new Class[]{WEB3AdminFPTSortKey[].class});
            l_method.setAccessible(true);
            String l_strSortKey =
                (String)l_method.invoke(l_impl, new Object[]{l_adminFPTSortKeys});

            assertEquals(" branch_code  ASC  ", l_strSortKey);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateSortKey_0003()
    {
        final String STR_METHOD_NAME = " testCreateSortKey_0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminFPTSortKey l_sorKey = new WEB3AdminFPTSortKey();
            l_sorKey.keyItem = "branchCode";
            l_sorKey.ascDesc = "A";
            
            WEB3AdminFPTSortKey l_sorKey1 = new WEB3AdminFPTSortKey();
            l_sorKey1.keyItem = "acceptCode";
            l_sorKey1.ascDesc = "A";
            
            WEB3AdminFPTSortKey[] l_adminFPTSortKeys = new WEB3AdminFPTSortKey[2];
            l_adminFPTSortKeys[0] = l_sorKey;
            l_adminFPTSortKeys[1] = l_sorKey1;

            WEB3AdminFPTListReferenceServiceImpl l_impl = new WEB3AdminFPTListReferenceServiceImpl();
            Method l_method = WEB3AdminFPTListReferenceServiceImpl.class.getDeclaredMethod(
                "createSortKey",
                new Class[]{WEB3AdminFPTSortKey[].class});
            l_method.setAccessible(true);
            String l_strSortKey =
                (String)l_method.invoke(l_impl, new Object[]{l_adminFPTSortKeys});

            assertEquals(" branch_code  ASC  ,  account_code  ASC  ", l_strSortKey);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateSortKey_0004()
    {
        final String STR_METHOD_NAME = " testCreateSortKey_0004()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminFPTSortKey l_sorKey = new WEB3AdminFPTSortKey();
            l_sorKey.keyItem = "branchCode";
            l_sorKey.ascDesc = "A";
            
            WEB3AdminFPTSortKey l_sorKey1 = new WEB3AdminFPTSortKey();
            l_sorKey1.keyItem = "acceptCode";
            l_sorKey1.ascDesc = "A";
            
            WEB3AdminFPTSortKey l_sorKey2 = new WEB3AdminFPTSortKey();
            l_sorKey2.keyItem = "productCode";
            l_sorKey2.ascDesc = "D";
            
            WEB3AdminFPTSortKey[] l_adminFPTSortKeys = new WEB3AdminFPTSortKey[3];
            l_adminFPTSortKeys[0] = l_sorKey;
            l_adminFPTSortKeys[1] = l_sorKey1;
            l_adminFPTSortKeys[2] = l_sorKey2;

            WEB3AdminFPTListReferenceServiceImpl l_impl = new WEB3AdminFPTListReferenceServiceImpl();
            Method l_method = WEB3AdminFPTListReferenceServiceImpl.class.getDeclaredMethod(
                "createSortKey",
                new Class[]{WEB3AdminFPTSortKey[].class});
            l_method.setAccessible(true);
            String l_strSortKey =
                (String)l_method.invoke(l_impl, new Object[]{l_adminFPTSortKeys});

            assertEquals(" branch_code  ASC  ,  account_code  ASC  ", l_strSortKey);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateSortKey_0005()
    {
        final String STR_METHOD_NAME = " testCreateSortKey_0005()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminFPTSortKey l_sorKey = new WEB3AdminFPTSortKey();
            l_sorKey.keyItem = "branchCode";
            l_sorKey.ascDesc = "A";
            
            WEB3AdminFPTSortKey l_sorKey1 = new WEB3AdminFPTSortKey();
            l_sorKey1.keyItem = "acceptCode";
            l_sorKey1.ascDesc = "A";
            
            WEB3AdminFPTSortKey l_sorKey2 = new WEB3AdminFPTSortKey();
            l_sorKey2.keyItem = "productCode";
            l_sorKey2.ascDesc = "D";
            
            WEB3AdminFPTSortKey l_sorKey3 = new WEB3AdminFPTSortKey();
            l_sorKey3.keyItem = "documentDiv";
            l_sorKey3.ascDesc = "A";
            
            WEB3AdminFPTSortKey[] l_adminFPTSortKeys = new WEB3AdminFPTSortKey[4];
            l_adminFPTSortKeys[0] = l_sorKey;
            l_adminFPTSortKeys[1] = l_sorKey1;
            l_adminFPTSortKeys[2] = l_sorKey2;
            l_adminFPTSortKeys[3] = l_sorKey3;

            WEB3AdminFPTListReferenceServiceImpl l_impl = new WEB3AdminFPTListReferenceServiceImpl();
            Method l_method = WEB3AdminFPTListReferenceServiceImpl.class.getDeclaredMethod(
                "createSortKey",
                new Class[]{WEB3AdminFPTSortKey[].class});
            l_method.setAccessible(true);
            String l_strSortKey =
                (String)l_method.invoke(l_impl, new Object[]{l_adminFPTSortKeys});

            assertEquals(" branch_code  ASC  ,  account_code  ASC  ", l_strSortKey);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateSortKey_0006()
    {
        final String STR_METHOD_NAME = " testCreateSortKey_0006()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminFPTSortKey l_sorKey = new WEB3AdminFPTSortKey();
            l_sorKey.keyItem = "branchCode";
            l_sorKey.ascDesc = "A";
            
            WEB3AdminFPTSortKey l_sorKey1 = new WEB3AdminFPTSortKey();
            l_sorKey1.keyItem = "acceptCode";
            l_sorKey1.ascDesc = "A";
            
            WEB3AdminFPTSortKey l_sorKey2 = new WEB3AdminFPTSortKey();
            l_sorKey2.keyItem = "productCode";
            l_sorKey2.ascDesc = "D";
            
            WEB3AdminFPTSortKey l_sorKey3 = new WEB3AdminFPTSortKey();
            l_sorKey3.keyItem = "documentDiv";
            l_sorKey3.ascDesc = "A";
            
            WEB3AdminFPTSortKey l_sorKey4 = new WEB3AdminFPTSortKey();
            l_sorKey4.keyItem = "docuDeliDate";
            l_sorKey4.ascDesc = "A";
            
            WEB3AdminFPTSortKey[] l_adminFPTSortKeys = new WEB3AdminFPTSortKey[5];
            l_adminFPTSortKeys[0] = l_sorKey;
            l_adminFPTSortKeys[1] = l_sorKey3;
            l_adminFPTSortKeys[2] = l_sorKey2;
            l_adminFPTSortKeys[3] = l_sorKey1;
            l_adminFPTSortKeys[4] = l_sorKey4;

            WEB3AdminFPTListReferenceServiceImpl l_impl = new WEB3AdminFPTListReferenceServiceImpl();
            Method l_method = WEB3AdminFPTListReferenceServiceImpl.class.getDeclaredMethod(
                "createSortKey",
                new Class[]{WEB3AdminFPTSortKey[].class});
            l_method.setAccessible(true);
            String l_strSortKey =
                (String)l_method.invoke(l_impl, new Object[]{l_adminFPTSortKeys});

            assertEquals(" branch_code  ASC  ,  account_code  ASC  ,  delivery_date  ASC  ", l_strSortKey);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateSortKey_0007()
    {
        final String STR_METHOD_NAME = " testCreateSortKey_0007()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminFPTSortKey l_sorKey = new WEB3AdminFPTSortKey();
            l_sorKey.keyItem = "branchCode213";
            l_sorKey.ascDesc = "A";
            
            WEB3AdminFPTSortKey[] l_adminFPTSortKeys = new WEB3AdminFPTSortKey[1];
            l_adminFPTSortKeys[0] = l_sorKey;

            WEB3AdminFPTListReferenceServiceImpl l_impl = new WEB3AdminFPTListReferenceServiceImpl();
            Method l_method = WEB3AdminFPTListReferenceServiceImpl.class.getDeclaredMethod(
                "createSortKey",
                new Class[]{WEB3AdminFPTSortKey[].class});
            l_method.setAccessible(true);
            String l_strSortKey =
                (String)l_method.invoke(l_impl, new Object[]{l_adminFPTSortKeys});

            assertEquals("", l_strSortKey);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateSortKey_0008()
    {
        final String STR_METHOD_NAME = " testCreateSortKey_0008()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminFPTSortKey l_sorKey = new WEB3AdminFPTSortKey();
            l_sorKey.keyItem = "documentCategory";
            l_sorKey.ascDesc = "A";
            
            WEB3AdminFPTSortKey[] l_adminFPTSortKeys = new WEB3AdminFPTSortKey[1];
            l_adminFPTSortKeys[0] = l_sorKey;

            WEB3AdminFPTListReferenceServiceImpl l_impl = new WEB3AdminFPTListReferenceServiceImpl();
            Method l_method = WEB3AdminFPTListReferenceServiceImpl.class.getDeclaredMethod(
                "createSortKey",
                new Class[]{WEB3AdminFPTSortKey[].class});
            l_method.setAccessible(true);
            String l_strSortKey =
                (String)l_method.invoke(l_impl, new Object[]{l_adminFPTSortKeys});

            assertEquals(" document_category  ASC  ", l_strSortKey);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateSortKey_0009()
    {
        final String STR_METHOD_NAME = " testCreateSortKey_0009()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminFPTSortKey l_sorKey = new WEB3AdminFPTSortKey();
            l_sorKey.keyItem = "batoProductCode";
            l_sorKey.ascDesc = "A";
            
            WEB3AdminFPTSortKey[] l_adminFPTSortKeys = new WEB3AdminFPTSortKey[1];
            l_adminFPTSortKeys[0] = l_sorKey;

            WEB3AdminFPTListReferenceServiceImpl l_impl = new WEB3AdminFPTListReferenceServiceImpl();
            Method l_method = WEB3AdminFPTListReferenceServiceImpl.class.getDeclaredMethod(
                "createSortKey",
                new Class[]{WEB3AdminFPTSortKey[].class});
            l_method.setAccessible(true);
            String l_strSortKey =
                (String)l_method.invoke(l_impl, new Object[]{l_adminFPTSortKeys});

            assertEquals(" product_code  ASC  ", l_strSortKey);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateSortKey_0010()
    {
        final String STR_METHOD_NAME = " testCreateSortKey_0010()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminFPTSortKey l_sorKey = new WEB3AdminFPTSortKey();
            l_sorKey.keyItem = "deemedDeliveryDate";
            l_sorKey.ascDesc = "D";
            
            WEB3AdminFPTSortKey[] l_adminFPTSortKeys = new WEB3AdminFPTSortKey[1];
            l_adminFPTSortKeys[0] = l_sorKey;

            WEB3AdminFPTListReferenceServiceImpl l_impl = new WEB3AdminFPTListReferenceServiceImpl();
            Method l_method = WEB3AdminFPTListReferenceServiceImpl.class.getDeclaredMethod(
                "createSortKey",
                new Class[]{WEB3AdminFPTSortKey[].class});
            l_method.setAccessible(true);
            String l_strSortKey =
                (String)l_method.invoke(l_impl, new Object[]{l_adminFPTSortKeys});

            assertEquals(" deemed_delivery_date  DESC NULLS LAST  ", l_strSortKey);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateSortKey_0011()
    {
        final String STR_METHOD_NAME = " testCreateSortKey_0011()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminFPTSortKey l_sorKey = new WEB3AdminFPTSortKey();
            l_sorKey.keyItem = "deemedDeliveryDate";
            l_sorKey.ascDesc = "A";
            
            WEB3AdminFPTSortKey[] l_adminFPTSortKeys = new WEB3AdminFPTSortKey[1];
            l_adminFPTSortKeys[0] = l_sorKey;

            WEB3AdminFPTListReferenceServiceImpl l_impl = new WEB3AdminFPTListReferenceServiceImpl();
            Method l_method = WEB3AdminFPTListReferenceServiceImpl.class.getDeclaredMethod(
                "createSortKey",
                new Class[]{WEB3AdminFPTSortKey[].class});
            l_method.setAccessible(true);
            String l_strSortKey =
                (String)l_method.invoke(l_impl, new Object[]{l_adminFPTSortKeys});

            assertEquals(" deemed_delivery_date  ASC NULLS FIRST  ", l_strSortKey);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateQueryDataContainer_0001()
    {
        final String STR_METHOD_NAME = " testCreateQueryDataContainer_0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3FPTDocumentDivAdminInfoUnit[] l_documentDivList =
                new WEB3FPTDocumentDivAdminInfoUnit[1];
            WEB3FPTDocumentDivAdminInfoUnit l_infoUnit0 =
                new WEB3FPTDocumentDivAdminInfoUnit();
            //111111111
            l_infoUnit0.documentCategory = "001";
            l_documentDivList[0] = l_infoUnit0;
            
            WEB3AdminFPTListReferenceRequest l_request = new WEB3AdminFPTListReferenceRequest();
            l_request.branchCode = new String[]{"123"};
            l_request.documentDivList = l_documentDivList;

            WEB3AdminFPTListReferenceServiceImpl l_impl = new WEB3AdminFPTListReferenceServiceImpl();
            Method l_method = WEB3AdminFPTListReferenceServiceImpl.class.getDeclaredMethod(
                "createQueryDataContainer",
                new Class[]{String.class, WEB3AdminFPTListReferenceRequest.class});
            l_method.setAccessible(true);
            Object[] l_queryDataContainers =
                (Object[])l_method.invoke(l_impl, new Object[]{"0D", l_request});

            assertEquals("0D", l_queryDataContainers[0]);
            assertEquals("123", l_queryDataContainers[1]);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateQueryDataContainer_0002()
    {
        final String STR_METHOD_NAME = " testCreateQueryDataContainer_0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3FPTDocumentDivAdminInfoUnit[] l_documentDivList =
                new WEB3FPTDocumentDivAdminInfoUnit[1];
            WEB3FPTDocumentDivAdminInfoUnit l_infoUnit0 =
                new WEB3FPTDocumentDivAdminInfoUnit();
            //111111111
            l_infoUnit0.documentCategory = "001";
            l_documentDivList[0] = l_infoUnit0;
            
            WEB3AdminFPTListReferenceRequest l_request = new WEB3AdminFPTListReferenceRequest();
            l_request.documentDivList = l_documentDivList;
            l_request.branchCode = new String[]{"12345"};
            WEB3AdminFPTListReferenceServiceImpl l_impl = new WEB3AdminFPTListReferenceServiceImpl();
            Method l_method = WEB3AdminFPTListReferenceServiceImpl.class.getDeclaredMethod(
                "createQueryDataContainer",
                new Class[]{String.class, WEB3AdminFPTListReferenceRequest.class});
            l_method.setAccessible(true);
            Object[] l_queryDataContainers =
                (Object[])l_method.invoke(l_impl, new Object[]{"0D", l_request});

            assertEquals(4, l_queryDataContainers.length);
            assertEquals("0D", l_queryDataContainers[0]);
            assertEquals("12345", l_queryDataContainers[1]);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateQueryDataContainer_0003()
    {
        final String STR_METHOD_NAME = " testCreateQueryDataContainer_0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3FPTDocumentDivAdminInfoUnit[] l_documentDivList =
                new WEB3FPTDocumentDivAdminInfoUnit[1];
            WEB3FPTDocumentDivAdminInfoUnit l_infoUnit0 =
                new WEB3FPTDocumentDivAdminInfoUnit();
            //111111111
            l_infoUnit0.documentCategory = "001";
            l_documentDivList[0] = l_infoUnit0;
            
            WEB3AdminFPTListReferenceRequest l_request = new WEB3AdminFPTListReferenceRequest();
            l_request.documentDivList = l_documentDivList;
            l_request.branchCode = new String[]{"12345"};
            l_request.acceptCode = "123";
            WEB3AdminFPTListReferenceServiceImpl l_impl = new WEB3AdminFPTListReferenceServiceImpl();
            Method l_method = WEB3AdminFPTListReferenceServiceImpl.class.getDeclaredMethod(
                "createQueryDataContainer",
                new Class[]{String.class, WEB3AdminFPTListReferenceRequest.class});
            l_method.setAccessible(true);
            Object[] l_queryDataContainers =
                (Object[])l_method.invoke(l_impl, new Object[]{"0D", l_request});

            assertEquals(5, l_queryDataContainers.length);
            assertEquals("0D", l_queryDataContainers[0]);
            assertEquals("12345", l_queryDataContainers[1]);
            assertEquals("123", l_queryDataContainers[2]);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateQueryDataContainer_0004()
    {
        final String STR_METHOD_NAME = " testCreateQueryDataContainer_0004()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3FPTDocumentDivAdminInfoUnit[] l_documentDivList =
                new WEB3FPTDocumentDivAdminInfoUnit[1];
            WEB3FPTDocumentDivAdminInfoUnit l_infoUnit0 =
                new WEB3FPTDocumentDivAdminInfoUnit();
            //111111111
            l_infoUnit0.documentCategory = "001";
            l_documentDivList[0] = l_infoUnit0;
            
            WEB3AdminFPTListReferenceRequest l_request = new WEB3AdminFPTListReferenceRequest();
            l_request.documentDivList = l_documentDivList;
            l_request.branchCode = new String[]{"12345"};
            l_request.acceptCode = "123";
//            l_request.productCode = "1234";
            WEB3AdminFPTListReferenceServiceImpl l_impl = new WEB3AdminFPTListReferenceServiceImpl();
            Method l_method = WEB3AdminFPTListReferenceServiceImpl.class.getDeclaredMethod(
                "createQueryDataContainer",
                new Class[]{String.class, WEB3AdminFPTListReferenceRequest.class});
            l_method.setAccessible(true);
            Object[] l_queryDataContainers =
                (Object[])l_method.invoke(l_impl, new Object[]{"0D", l_request});

            assertEquals(5, l_queryDataContainers.length);
            assertEquals("0D", l_queryDataContainers[0]);
            assertEquals("12345", l_queryDataContainers[1]);
            assertEquals("123", l_queryDataContainers[2]);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateQueryDataContainer_0005()
    {
        final String STR_METHOD_NAME = " testCreateQueryDataContainer_0005()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3FPTDocumentDivAdminInfoUnit[] l_documentDivList =
                new WEB3FPTDocumentDivAdminInfoUnit[1];
            WEB3FPTDocumentDivAdminInfoUnit l_infoUnit0 =
                new WEB3FPTDocumentDivAdminInfoUnit();
            //111111111
            l_infoUnit0.documentCategory = "001";
            l_documentDivList[0] = l_infoUnit0;
            
            WEB3AdminFPTListReferenceRequest l_request = new WEB3AdminFPTListReferenceRequest();
            l_request.documentDivList = l_documentDivList;
            l_request.branchCode = new String[]{"12345"};
            l_request.acceptCode = "123";
//            l_request.productCode = "1234";
//            l_request.documentDiv = "123456";

            WEB3AdminFPTListReferenceServiceImpl l_impl = new WEB3AdminFPTListReferenceServiceImpl();
            Method l_method = WEB3AdminFPTListReferenceServiceImpl.class.getDeclaredMethod(
                "createQueryDataContainer",
                new Class[]{String.class, WEB3AdminFPTListReferenceRequest.class});
            l_method.setAccessible(true);
            Object[] l_queryDataContainers =
                (Object[])l_method.invoke(l_impl, new Object[]{"0D", l_request});

            assertEquals(5, l_queryDataContainers.length);
            assertEquals("0D", l_queryDataContainers[0]);
            assertEquals("12345", l_queryDataContainers[1]);
            assertEquals("123", l_queryDataContainers[2]);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateQueryDataContainer_0006()
    {
        final String STR_METHOD_NAME = " testCreateQueryDataContainer_0006()";
        log.entering(STR_METHOD_NAME);

        try
        {
            Date l_dat = new Date();
            WEB3FPTDocumentDivAdminInfoUnit[] l_documentDivList =
                new WEB3FPTDocumentDivAdminInfoUnit[1];
            WEB3FPTDocumentDivAdminInfoUnit l_infoUnit0 =
                new WEB3FPTDocumentDivAdminInfoUnit();
            //111111111
            l_infoUnit0.documentCategory = "001";
            l_documentDivList[0] = l_infoUnit0;
            
            WEB3AdminFPTListReferenceRequest l_request = new WEB3AdminFPTListReferenceRequest();
            l_request.documentDivList = l_documentDivList;
            l_request.branchCode = new String[]{"12345"};
            l_request.acceptCode = "123";
//            l_request.productCode = "1234";
//            l_request.documentDiv = "123456";
            l_request.docuDeliDateFrom = l_dat;

            WEB3AdminFPTListReferenceServiceImpl l_impl = new WEB3AdminFPTListReferenceServiceImpl();
            Method l_method = WEB3AdminFPTListReferenceServiceImpl.class.getDeclaredMethod(
                "createQueryDataContainer",
                new Class[]{String.class, WEB3AdminFPTListReferenceRequest.class});
            l_method.setAccessible(true);
            Object[] l_queryDataContainers =
                (Object[])l_method.invoke(l_impl, new Object[]{"0D", l_request});

            assertEquals(6, l_queryDataContainers.length);
            assertEquals("0D", l_queryDataContainers[0]);
            assertEquals("12345", l_queryDataContainers[1]);
            assertEquals("123", l_queryDataContainers[2]);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateQueryDataContainer_0007()
    {
        final String STR_METHOD_NAME = " testCreateQueryDataContainer_0007()";
        log.entering(STR_METHOD_NAME);

        try
        {
            Date l_dat = new Date();
            WEB3FPTDocumentDivAdminInfoUnit[] l_documentDivList =
                new WEB3FPTDocumentDivAdminInfoUnit[1];
            WEB3FPTDocumentDivAdminInfoUnit l_infoUnit0 =
                new WEB3FPTDocumentDivAdminInfoUnit();
            //111111111
            l_infoUnit0.documentDiv = "1";
            l_infoUnit0.documentCategory = "001";
            l_documentDivList[0] = l_infoUnit0;
            
            WEB3AdminFPTListReferenceRequest l_request = new WEB3AdminFPTListReferenceRequest();
            l_request.documentDivList = l_documentDivList;
            l_request.branchCode = new String[]{"12345"};
            l_request.acceptCode = "123";
            l_request.docuDeliDateFrom = l_dat;
            l_request.docuDeliDateTo = l_dat;

            WEB3AdminFPTListReferenceServiceImpl l_impl = new WEB3AdminFPTListReferenceServiceImpl();
            Method l_method = WEB3AdminFPTListReferenceServiceImpl.class.getDeclaredMethod(
                "createQueryDataContainer",
                new Class[]{String.class, WEB3AdminFPTListReferenceRequest.class});
            l_method.setAccessible(true);
            Object[] l_queryDataContainers =
                (Object[])l_method.invoke(l_impl, new Object[]{"0D", l_request});

            assertEquals(7, l_queryDataContainers.length);
            assertEquals("0D", l_queryDataContainers[0]);
            assertEquals("12345", l_queryDataContainers[1]);
            assertEquals("123", l_queryDataContainers[2]);
            assertEquals("1", l_queryDataContainers[3]);
            assertEquals("001", l_queryDataContainers[4]);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateQueryDataContainer_0008()
    {
        final String STR_METHOD_NAME = " testCreateQueryDataContainer_0008()";
        log.entering(STR_METHOD_NAME);

        try
        {
            Date l_dat = new Date();
            WEB3FPTDocumentDivAdminInfoUnit[] l_documentDivList =
                new WEB3FPTDocumentDivAdminInfoUnit[2];
            WEB3FPTDocumentDivAdminInfoUnit l_infoUnit0 =
                new WEB3FPTDocumentDivAdminInfoUnit();
            //111111111
            l_infoUnit0.documentDiv = "1";
            l_infoUnit0.documentCategory = "001";
            l_documentDivList[0] = l_infoUnit0;
            
            WEB3FPTDocumentDivAdminInfoUnit l_infoUnit1 =
                new WEB3FPTDocumentDivAdminInfoUnit();
            
            //22222
            l_infoUnit1.documentDiv = "1";
            l_infoUnit1.documentCategory = "002";
            l_documentDivList[1] = l_infoUnit1;
            
            WEB3AdminFPTListReferenceRequest l_request = new WEB3AdminFPTListReferenceRequest();
            l_request.documentDivList = l_documentDivList;
            l_request.branchCode = new String[]{"12345"};
            l_request.acceptCode = "123";
            l_request.docuDeliDateFrom = l_dat;
            l_request.docuDeliDateTo = l_dat;

            WEB3AdminFPTListReferenceServiceImpl l_impl = new WEB3AdminFPTListReferenceServiceImpl();
            Method l_method = WEB3AdminFPTListReferenceServiceImpl.class.getDeclaredMethod(
                "createQueryDataContainer",
                new Class[]{String.class, WEB3AdminFPTListReferenceRequest.class});
            l_method.setAccessible(true);
            Object[] l_queryDataContainers =
                (Object[])l_method.invoke(l_impl, new Object[]{"0D", l_request});

            assertEquals(9, l_queryDataContainers.length);
            assertEquals("0D", l_queryDataContainers[0]);
            assertEquals("12345", l_queryDataContainers[1]);
            assertEquals("123", l_queryDataContainers[2]);
            assertEquals("1", l_queryDataContainers[3]);
            assertEquals("001", l_queryDataContainers[4]);
            assertEquals("1", l_queryDataContainers[5]);
            assertEquals("002", l_queryDataContainers[6]);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateQueryString_0001()
    {
        final String STR_METHOD_NAME = " testCreateQueryString_0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3FPTDocumentDivAdminInfoUnit[] l_documentDivList =
                new WEB3FPTDocumentDivAdminInfoUnit[0];

            WEB3AdminFPTListReferenceRequest l_request = new WEB3AdminFPTListReferenceRequest();
            l_request.documentDivList = l_documentDivList;
                
            l_request.branchCode = new String[]{"123"};
            WEB3AdminFPTListReferenceServiceImpl l_impl = new WEB3AdminFPTListReferenceServiceImpl();
            Method l_method = WEB3AdminFPTListReferenceServiceImpl.class.getDeclaredMethod(
                "createQueryString",
                new Class[]{WEB3AdminFPTListReferenceRequest.class});
            l_method.setAccessible(true);
            String l_createQueryString =
                (String)l_method.invoke(l_impl, new Object[]{l_request});

            assertEquals(" institution_code = ?  and branch_code = ? ", l_createQueryString);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateQueryString_0002()
    {
        final String STR_METHOD_NAME = " testCreateQueryString_0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3FPTDocumentDivAdminInfoUnit[] l_documentDivList =
                new WEB3FPTDocumentDivAdminInfoUnit[0];
            
            WEB3AdminFPTListReferenceRequest l_request = new WEB3AdminFPTListReferenceRequest();
            l_request.documentDivList = l_documentDivList;
            l_request.branchCode = new String[]{"12345"};
            WEB3AdminFPTListReferenceServiceImpl l_impl = new WEB3AdminFPTListReferenceServiceImpl();
            Method l_method = WEB3AdminFPTListReferenceServiceImpl.class.getDeclaredMethod(
                "createQueryString",
                new Class[]{WEB3AdminFPTListReferenceRequest.class});
            l_method.setAccessible(true);
            String l_createQueryString =
                (String)l_method.invoke(l_impl, new Object[]{l_request});

            assertEquals(" institution_code = ?  and branch_code = ? ", l_createQueryString);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateQueryString_0003()
    {
        final String STR_METHOD_NAME = " testCreateQueryString_0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3FPTDocumentDivAdminInfoUnit[] l_documentDivList =
                new WEB3FPTDocumentDivAdminInfoUnit[0];
            
            WEB3AdminFPTListReferenceRequest l_request = new WEB3AdminFPTListReferenceRequest();
            l_request.documentDivList = l_documentDivList;
            l_request.branchCode = new String[]{"12345"};
            l_request.acceptCode = "123";
            WEB3AdminFPTListReferenceServiceImpl l_impl = new WEB3AdminFPTListReferenceServiceImpl();
            Method l_method = WEB3AdminFPTListReferenceServiceImpl.class.getDeclaredMethod(
                "createQueryString",
                new Class[]{WEB3AdminFPTListReferenceRequest.class});
            l_method.setAccessible(true);
            String l_createQueryString =
                (String)l_method.invoke(l_impl, new Object[]{l_request});

            assertEquals(" institution_code = ?  and branch_code = ?  and account_code like ? || '%' ", l_createQueryString);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateQueryString_0004()
    {
        final String STR_METHOD_NAME = " testCreateQueryString_0004()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3FPTDocumentDivAdminInfoUnit[] l_documentDivList =
                new WEB3FPTDocumentDivAdminInfoUnit[0];
            
            WEB3AdminFPTListReferenceRequest l_request = new WEB3AdminFPTListReferenceRequest();
            l_request.documentDivList = l_documentDivList;
            l_request.branchCode = new String[]{"12345"};
            l_request.acceptCode = "123";
//            l_request.productCode = "1234";
            WEB3AdminFPTListReferenceServiceImpl l_impl = new WEB3AdminFPTListReferenceServiceImpl();
            Method l_method = WEB3AdminFPTListReferenceServiceImpl.class.getDeclaredMethod(
                "createQueryString",
                new Class[]{WEB3AdminFPTListReferenceRequest.class});
            l_method.setAccessible(true);
            String l_createQueryString =
                (String)l_method.invoke(l_impl, new Object[]{l_request});

            assertEquals(" institution_code = ?  and branch_code = ?  and account_code like ? || '%' ", l_createQueryString);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateQueryString_0005()
    {
        final String STR_METHOD_NAME = " testCreateQueryString_0005()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3FPTDocumentDivAdminInfoUnit[] l_documentDivList =
                new WEB3FPTDocumentDivAdminInfoUnit[0];
            
            WEB3AdminFPTListReferenceRequest l_request = new WEB3AdminFPTListReferenceRequest();
            l_request.documentDivList = l_documentDivList;
            l_request.branchCode = new String[]{"12345"};
            l_request.acceptCode = "123";
//            l_request.productCode = "1234";
//            l_request.documentDiv = "123456";

            WEB3AdminFPTListReferenceServiceImpl l_impl = new WEB3AdminFPTListReferenceServiceImpl();
            Method l_method = WEB3AdminFPTListReferenceServiceImpl.class.getDeclaredMethod(
                "createQueryString",
                new Class[]{WEB3AdminFPTListReferenceRequest.class});
            l_method.setAccessible(true);
            String l_createQueryString =
                (String)l_method.invoke(l_impl, new Object[]{l_request});

            assertEquals(" institution_code = ?  and branch_code = ?  and account_code like ? || '%' ", l_createQueryString);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateQueryString_0006()
    {
        final String STR_METHOD_NAME = " testCreateQueryString_0006()";
        log.entering(STR_METHOD_NAME);

        try
        {
            Date l_dat = new Date();
            WEB3FPTDocumentDivAdminInfoUnit[] l_documentDivList =
                new WEB3FPTDocumentDivAdminInfoUnit[0];
            
            WEB3AdminFPTListReferenceRequest l_request = new WEB3AdminFPTListReferenceRequest();
            l_request.documentDivList = l_documentDivList;
            l_request.branchCode = new String[]{"12345"};
            l_request.acceptCode = "123";
//            l_request.productCode = "1234";
//            l_request.documentDiv = "123456";
            l_request.docuDeliDateFrom = l_dat;

            WEB3AdminFPTListReferenceServiceImpl l_impl = new WEB3AdminFPTListReferenceServiceImpl();
            Method l_method = WEB3AdminFPTListReferenceServiceImpl.class.getDeclaredMethod(
                "createQueryString",
                new Class[]{WEB3AdminFPTListReferenceRequest.class});
            l_method.setAccessible(true);
            String l_createQueryString =
                (String)l_method.invoke(l_impl, new Object[]{l_request});

            assertEquals(" institution_code = ?  and branch_code = ?  and account_code like ? || '%'  and delivery_date >= ? ", l_createQueryString);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateQueryString_0007()
    {
        final String STR_METHOD_NAME = " testCreateQueryString_0007()";
        log.entering(STR_METHOD_NAME);

        try
        {
            Date l_dat = new Date();
            WEB3FPTDocumentDivAdminInfoUnit[] l_documentDivList =
                new WEB3FPTDocumentDivAdminInfoUnit[0];
            
            WEB3AdminFPTListReferenceRequest l_request = new WEB3AdminFPTListReferenceRequest();
            l_request.documentDivList = l_documentDivList;
            l_request.branchCode = new String[]{"12345"};
            l_request.acceptCode = "123";
//            l_request.productCode = "1234";
//            l_request.documentDiv = "123456";
            l_request.docuDeliDateFrom = l_dat;
            l_request.docuDeliDateTo = l_dat;

            WEB3AdminFPTListReferenceServiceImpl l_impl = new WEB3AdminFPTListReferenceServiceImpl();
            Method l_method = WEB3AdminFPTListReferenceServiceImpl.class.getDeclaredMethod(
                "createQueryString",
                new Class[]{WEB3AdminFPTListReferenceRequest.class});
            l_method.setAccessible(true);
            String l_createQueryString =
                (String)l_method.invoke(l_impl, new Object[]{l_request});

            assertEquals(" institution_code = ?  and branch_code = ?  and account_code like ? || '%'  and delivery_date >= ?  and delivery_date <= ? ", l_createQueryString);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateQueryString_0008()
    {
        final String STR_METHOD_NAME = " testCreateQueryString_0008()";
        log.entering(STR_METHOD_NAME);

        try
        {
            Date l_dat = new Date();
            WEB3FPTDocumentDivAdminInfoUnit[] l_documentDivList =
                new WEB3FPTDocumentDivAdminInfoUnit[1];
            WEB3FPTDocumentDivAdminInfoUnit l_documentDivList0 =
                new WEB3FPTDocumentDivAdminInfoUnit();
            l_documentDivList0.docuCheckDiv = "1";
            l_documentDivList0.documentCategory = "001";
            WEB3AdminFPTListReferenceRequest l_request = new WEB3AdminFPTListReferenceRequest();
            l_request.documentDivList = l_documentDivList;
            l_request.branchCode = new String[]{"381"};
            l_request.acceptCode = "123";
            l_request.docuDeliDateFrom = l_dat;
            l_request.docuDeliDateTo = l_dat;

            WEB3AdminFPTListReferenceServiceImpl l_impl = new WEB3AdminFPTListReferenceServiceImpl();
            Method l_method = WEB3AdminFPTListReferenceServiceImpl.class.getDeclaredMethod(
                "createQueryString",
                new Class[]{WEB3AdminFPTListReferenceRequest.class});
            l_method.setAccessible(true);
            String l_createQueryString =
                (String)l_method.invoke(l_impl, new Object[]{l_request});

            assertEquals(" institution_code = ?  and branch_code = ?  and account_code like ? || '%'  and document_div = ? and document_category = ?  and delivery_date >= ?  and delivery_date <= ? ", l_createQueryString);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateQueryString_0009()
    {
        final String STR_METHOD_NAME = " testCreateQueryString_0009()";
        log.entering(STR_METHOD_NAME);

        try
        {
            Date l_dat = new Date();
            WEB3FPTDocumentDivAdminInfoUnit[] l_documentDivList =
                new WEB3FPTDocumentDivAdminInfoUnit[2];
            //1111111
            WEB3FPTDocumentDivAdminInfoUnit l_documentDivList0 =
                new WEB3FPTDocumentDivAdminInfoUnit();
            l_documentDivList0.docuCheckDiv = "1";
            l_documentDivList0.documentCategory = "001";
            //222222222
            WEB3FPTDocumentDivAdminInfoUnit l_documentDivList1 =
                new WEB3FPTDocumentDivAdminInfoUnit();
            l_documentDivList1.docuCheckDiv = "1";
            l_documentDivList1.documentCategory = "002";
            
            WEB3AdminFPTListReferenceRequest l_request = new WEB3AdminFPTListReferenceRequest();
            l_request.documentDivList = l_documentDivList;
            l_request.branchCode = new String[]{"381"};
            l_request.acceptCode = "123";
            l_request.docuDeliDateFrom = l_dat;
            l_request.docuDeliDateTo = l_dat;

            WEB3AdminFPTListReferenceServiceImpl l_impl = new WEB3AdminFPTListReferenceServiceImpl();
            Method l_method = WEB3AdminFPTListReferenceServiceImpl.class.getDeclaredMethod(
                "createQueryString",
                new Class[]{WEB3AdminFPTListReferenceRequest.class});
            l_method.setAccessible(true);
            String l_createQueryString =
                (String)l_method.invoke(l_impl, new Object[]{l_request});

            assertEquals(" institution_code = ?  and branch_code = ?  and account_code like ? || '%' " + 
                    " and(  (document_div = ? and document_category = ?)  or  (document_div = ? and document_category = ?)  ) " +
                    " and delivery_date >= ? " +
                    " and delivery_date <= ? ", l_createQueryString);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    
    public void testGetListSearchInput_0001()
    {
        final String STR_METHOD_NAME = " testGetListSearchInput_0001()";
        log.entering(STR_METHOD_NAME);
 
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
 
        AdministratorParams l_adminStratorParams = TestDBUtility.getAdministratorRow();
        l_adminStratorParams.setBranchCode(l_mainAccountParams.getBranchCode());
        l_adminStratorParams.setInstitutionId(l_mainAccountParams.getInstitutionId());
 
        AdminPermissionParams l_dminPermissionParams = TestDBUtility.getAdminPermissionRow();
        l_dminPermissionParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
        l_dminPermissionParams.setPermissionLevel(l_adminStratorParams.getPermissionLevel());
        l_dminPermissionParams.setTransactionCategory(WEB3TransactionCategoryDef.FPT_REGIST_INQUIRY);
 
        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            TestDBUtility.deleteAll(DocDivManagementParams.TYPE);
 
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_adminStratorParams);
            TestDBUtility.insertWithDel(l_dminPermissionParams);
            
            //DocCategoryManagementParams
            DocCategoryManagementParams l_params =
                this.getDocCategoryManagementRow();
            l_params.setDocumentCategory("001");
            l_params.setBranchCode("381");
            TestDBUtility.insertWithDel(l_params);
            
            //DocDivManagementParams
            DocDivManagementParams l_docDivManagementParams =
                this.getDocDivManagementRow();
            l_docDivManagementParams.setInstitutionCode("0D");
            l_docDivManagementParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_docDivManagementParams);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
 
        try
        {
 
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_adminStratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
 
            WEB3AdminFPTSearchInputRequest l_request = new WEB3AdminFPTSearchInputRequest();
 
            WEB3AdminFPTListReferenceServiceImpl l_impl =
                new WEB3AdminFPTListReferenceServiceImpl();
 
            WEB3AdminFPTSearchInputResponse l_response =
                l_impl.getListSearchInput(l_request);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(MainAccountParams.TYPE);
                TestDBUtility.deleteAll(AdministratorParams.TYPE);
                TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
                TestDBUtility.deleteAll(DocDivManagementParams.TYPE);
            }
            catch (Exception l_ex)
            {
                log.error(STR_METHOD_NAME, l_ex);
                log.exiting(STR_METHOD_NAME);
                fail();
            }
            finally
            {
                log.exiting(STR_METHOD_NAME);
            }
        }
    }
 
    public void testGetListSearchInput_0002()
    {
        final String STR_METHOD_NAME = " testGetListSearchInput_0002()";
        log.entering(STR_METHOD_NAME);
 
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
 
        AdministratorParams l_adminStratorParams = TestDBUtility.getAdministratorRow();
        l_adminStratorParams.setBranchCode(l_mainAccountParams.getBranchCode());
        l_adminStratorParams.setInstitutionId(l_mainAccountParams.getInstitutionId());
 
        AdminPermissionParams l_dminPermissionParams = TestDBUtility.getAdminPermissionRow();
        l_dminPermissionParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
        l_dminPermissionParams.setPermissionLevel(l_adminStratorParams.getPermissionLevel());
        l_dminPermissionParams.setTransactionCategory(WEB3TransactionCategoryDef.FPT_REGIST_INQUIRY);
 
        DocDivManagementParams l_docDivManagementParams = new DocDivManagementParams();
        l_docDivManagementParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
        l_docDivManagementParams.setBranchCode(l_mainAccountParams.getBranchCode());
        l_docDivManagementParams.setDocumentDiv("0");
        l_docDivManagementParams.setDocumentCheckDiv("1");
        l_docDivManagementParams.setDocumentNumber("2");
        l_docDivManagementParams.setDocumentName("abc");
        l_docDivManagementParams.setDocumentCategory("001");
 
        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            TestDBUtility.deleteAll(DocDivManagementParams.TYPE);
 
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_adminStratorParams);
            TestDBUtility.insertWithDel(l_dminPermissionParams);
            TestDBUtility.insertWithDel(l_docDivManagementParams);
            
            //DocCategoryManagementParams
            DocCategoryManagementParams l_params =
                this.getDocCategoryManagementRow();
            l_params.setDocumentCategory("001");
            l_params.setBranchCode("381");
            TestDBUtility.insertWithDel(l_params);

        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
 
        try
        {
 
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_adminStratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
 
            WEB3AdminFPTSearchInputRequest l_request = new WEB3AdminFPTSearchInputRequest();
 
            WEB3AdminFPTListReferenceServiceImpl l_impl =
                new WEB3AdminFPTListReferenceServiceImpl();
 
            WEB3AdminFPTSearchInputResponse l_response =
                l_impl.getListSearchInput(l_request);
 
            assertEquals(1, l_response.documentDivList.length);
            assertNotNull(l_response.documentDivList[0]);
            assertEquals("0", l_response.documentDivList[0].documentDiv);
            assertEquals("1", l_response.documentDivList[0].docuCheckDiv);
            assertEquals("abc", l_response.documentDivList[0].documentNames);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(MainAccountParams.TYPE);
                TestDBUtility.deleteAll(AdministratorParams.TYPE);
                TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
                TestDBUtility.deleteAll(DocDivManagementParams.TYPE);
            }
            catch (Exception l_ex)
            {
                log.error(STR_METHOD_NAME, l_ex);
                log.exiting(STR_METHOD_NAME);
                fail();
            }
            finally
            {
                log.exiting(STR_METHOD_NAME);
            }
        }
    }
 
    public void testGetListSearchInput_0003()
    {
        final String STR_METHOD_NAME = " testGetListSearchInput_0003()";
        log.entering(STR_METHOD_NAME);
 
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
 
        AdministratorParams l_adminStratorParams = TestDBUtility.getAdministratorRow();
        l_adminStratorParams.setBranchCode(l_mainAccountParams.getBranchCode());
        l_adminStratorParams.setInstitutionId(l_mainAccountParams.getInstitutionId());
 
        AdminPermissionParams l_dminPermissionParams = TestDBUtility.getAdminPermissionRow();
        l_dminPermissionParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
        l_dminPermissionParams.setPermissionLevel(l_adminStratorParams.getPermissionLevel());
        l_dminPermissionParams.setTransactionCategory(WEB3TransactionCategoryDef.FPT_REGIST_INQUIRY);
 
        DocDivManagementParams l_docDivManagementParams = new DocDivManagementParams();
        l_docDivManagementParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
        l_docDivManagementParams.setBranchCode(l_mainAccountParams.getBranchCode());
        l_docDivManagementParams.setDocumentCategory("001");
 

        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            TestDBUtility.deleteAll(DocDivManagementParams.TYPE);
 
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_adminStratorParams);
            TestDBUtility.insertWithDel(l_dminPermissionParams);
 
            l_docDivManagementParams.setDocumentDiv("0");
            l_docDivManagementParams.setDocumentCheckDiv("1");
            l_docDivManagementParams.setDocumentNumber("2");
            l_docDivManagementParams.setDocumentName("abc");
            TestDBUtility.insertWithDel(l_docDivManagementParams);
 
            l_docDivManagementParams.setDocumentDiv("1");
            l_docDivManagementParams.setDocumentCheckDiv("11");
            l_docDivManagementParams.setDocumentNumber("22");
            l_docDivManagementParams.setDocumentName("xyz");
            TestDBUtility.insertWithDel(l_docDivManagementParams);
            
            //DocCategoryManagementParams
            DocCategoryManagementParams l_params =
                this.getDocCategoryManagementRow();
            l_params.setDocumentCategory("001");
            l_params.setBranchCode("381");
            TestDBUtility.insertWithDel(l_params);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
 
        try
        {
 
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_adminStratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
 
            WEB3AdminFPTSearchInputRequest l_request = new WEB3AdminFPTSearchInputRequest();
 
            WEB3AdminFPTListReferenceServiceImpl l_impl =
                new WEB3AdminFPTListReferenceServiceImpl();
 
            WEB3AdminFPTSearchInputResponse l_response =
                l_impl.getListSearchInput(l_request);
 
            assertEquals(2, l_response.documentDivList.length);
            assertNotNull(l_response.documentDivList[0]);
            assertEquals("0", l_response.documentDivList[0].documentDiv);
            assertEquals("1", l_response.documentDivList[0].docuCheckDiv);
            assertEquals("abc", l_response.documentDivList[0].documentNames);
 
            assertNotNull(l_response.documentDivList[1]);
            assertEquals("1", l_response.documentDivList[1].documentDiv);
            assertEquals("11", l_response.documentDivList[1].docuCheckDiv);
            assertEquals("xyz", l_response.documentDivList[1].documentNames);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(MainAccountParams.TYPE);
                TestDBUtility.deleteAll(AdministratorParams.TYPE);
                TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
                TestDBUtility.deleteAll(DocDivManagementParams.TYPE);
            }
            catch (Exception l_ex)
            {
                log.error(STR_METHOD_NAME, l_ex);
                log.exiting(STR_METHOD_NAME);
                fail();
            }
            finally
            {
                log.exiting(STR_METHOD_NAME);
            }
        }
    }
    
    public void testGetListReference_0001()
    {
        final String STR_METHOD_NAME = " testGetListReference_0001()";
        log.entering(STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        WEB3AdminFPTSortKey[] l_sortKey = new WEB3AdminFPTSortKey[0];
        
        WEB3FPTDocumentDivAdminInfoUnit[] l_documentDivList =
            new WEB3FPTDocumentDivAdminInfoUnit[0];
        
        WEB3AdminFPTListReferenceRequestForTest l_request = new WEB3AdminFPTListReferenceRequestForTest();
        l_request.documentDivList = l_documentDivList;
        
        l_request.sortKeys = l_sortKey;
        l_request.pageSize = "1";
        l_request.pageIndex = "1";
        l_request.branchCode = new String[]{"123"};
        
        LoginInfoImpl l_loginInfoImpl = new LoginInfoImpl();
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getLoginInfo",
            new Class[] {},
            l_loginInfoImpl);
        
        try
        {            
            TestDBUtility.deleteAll(DocDeliveryManagementParams.TYPE);

            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0L);
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);
            
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("G0102");
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            TestDBUtility.insertWithDel(l_administratorTypeParams);
            
            WEB3AdminFPTListReferenceServiceImpl l_impl = new WEB3AdminFPTListReferenceServiceImpl();
            WEB3AdminFPTListReferenceResponse l_response = l_impl.getListReference(l_request);
            
            assertEquals(0, l_response.financialProductTradeList.length);
            assertEquals("1", l_response.pageIndex);
            assertEquals("0", l_response.totalPages);
            assertEquals("0", l_response.totalRecords);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00398, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetListReference_0002()
    {
        final String STR_METHOD_NAME = " testGetListReference_0002()";
        log.entering(STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        WEB3AdminFPTSortKey[] l_sortKey = new WEB3AdminFPTSortKey[0];
        WEB3FPTDocumentDivAdminInfoUnit[] l_documentDivList =
            new WEB3FPTDocumentDivAdminInfoUnit[0];
        
        WEB3AdminFPTListReferenceRequestForTest l_request = new WEB3AdminFPTListReferenceRequestForTest();
        l_request.documentDivList = l_documentDivList;
        l_request.sortKeys = l_sortKey;
        l_request.pageSize = "1";
        l_request.pageIndex = "1";
        l_request.branchCode = new String[]{"381"};
        
        LoginInfoImpl l_loginInfoImpl = new LoginInfoImpl();
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getLoginInfo",
            new Class[] {},
            l_loginInfoImpl);
        
        Date l_newDate = new Date();
        DocDeliveryManagementParams l_params = new DocDeliveryManagementParams();
        l_params.setInstitutionCode("0D");
        l_params.setBranchCode("381");
        l_params.setDocumentDiv("1");
        l_params.setAccountCode("2512246");
        l_params.setProductCode("N8080");
        l_params.setDeliveryDate(l_newDate);
        l_params.setDeleteFlag(BooleanEnum.FALSE);
        l_params.setLastUpdater("123456");
        l_params.setCreatedTimestamp(l_newDate);
        l_params.setLastUpdatedTimestamp(l_newDate);
        l_params.setDocumentCategory("001");
        try
        {
            TestDBUtility.deleteAll(DocDeliveryManagementParams.TYPE);
            TestDBUtility.insertWithDel(l_params);
            
            TestDBUtility.deleteAll(DocDivManagementRow.TYPE);
            DocDivManagementParams l_docDivManagementParams = new DocDivManagementParams();
            l_docDivManagementParams.setInstitutionCode("0D");
            l_docDivManagementParams.setBranchCode("381");
            l_docDivManagementParams.setDocumentDiv("1");
            l_docDivManagementParams.setDocumentCheckDiv("1");
            l_docDivManagementParams.setDocumentNumber("12");
            l_docDivManagementParams.setDocumentName("そこです");
            l_docDivManagementParams.setLastUpdater("12345");
            l_docDivManagementParams.setCreatedTimestamp(new Date());
            l_docDivManagementParams.setLastUpdatedTimestamp(new Date());
            l_docDivManagementParams.setDocumentCategory("001");
            TestDBUtility.insertWithDel(l_docDivManagementParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(TestDBUtility.getInstitutionRow());
            WEB3GentradeInstitution l_gentradeInstitution = new WEB3GentradeInstitution("0D");
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(TestDBUtility.getInstitutionRow());

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.insertWithDel(TestDBUtility.getMainAccountRow());

            IpoProductParams l_ipoProductParams = new IpoProductParams();
            //IPO銘柄ＩＤ
            l_ipoProductParams.setIpoProductId(123456);
            //証券会社コード
            l_ipoProductParams.setInstitutionCode("0D");
            //銘柄コード
            l_ipoProductParams.setProductCode("N8080");
            //銘柄タイプ
            l_ipoProductParams.setProductType(ProductTypeEnum.OTHER);
            //IPO登録区分
            l_ipoProductParams.setIpoRegistDiv("1");
            //IPO登録区分詳細
            l_ipoProductParams.setIpoRegistDetailDiv("1");
            l_ipoProductParams.setDeleteFlag(BooleanEnum.FALSE);
            //公開市場
            l_ipoProductParams.setPublicMarket("1");
            //仮条件区分
            l_ipoProductParams.setProvisionalValueDiv("1");
            l_ipoProductParams.setStandardName("田中さん");
            TestDBUtility.deleteAll(IpoProductParams.TYPE);
            TestDBUtility.insertWithDel(l_ipoProductParams);
            
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0L);
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);
            
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("G0102");
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            TestDBUtility.insertWithDel(l_administratorTypeParams);
            
            //DocCategoryManagementParams
            DocCategoryManagementParams l_docParams =
                this.getDocCategoryManagementRow();
            l_docParams.setDocumentCategory("001");
            l_docParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_docParams);
            
            WEB3AdminFPTListReferenceServiceImpl l_impl = new WEB3AdminFPTListReferenceServiceImpl();
            WEB3AdminFPTListReferenceResponse l_response = l_impl.getListReference(l_request);
            
            assertEquals(1, l_response.financialProductTradeList.length);
            assertEquals("1", l_response.pageIndex);
            assertEquals("1", l_response.totalPages);
            assertEquals("1", l_response.totalRecords);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetListReference_0003()
    {
        final String STR_METHOD_NAME = " testGetListReference_0003()";
        log.entering(STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        WEB3AdminFPTSortKey[] l_sortKey = new WEB3AdminFPTSortKey[0];
        WEB3FPTDocumentDivAdminInfoUnit[] l_documentDivList =
            new WEB3FPTDocumentDivAdminInfoUnit[0];
        
        WEB3AdminFPTListReferenceRequestForTest l_request = new WEB3AdminFPTListReferenceRequestForTest();
        l_request.documentDivList = l_documentDivList;
        l_request.sortKeys = l_sortKey;
        l_request.pageSize = "1";
        l_request.pageIndex = "2";
        l_request.branchCode = new String[]{"381"};
        
        LoginInfoImpl l_loginInfoImpl = new LoginInfoImpl();
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getLoginInfo",
            new Class[] {},
            l_loginInfoImpl);
        
        Date l_newDate = new Date();
        DocDeliveryManagementParams l_params = new DocDeliveryManagementParams();
        l_params.setAccountId(12345L);
        l_params.setInstitutionCode("0D");
        l_params.setBranchCode("381");
        l_params.setDocumentDiv("1");
        l_params.setAccountCode("2512246");
        l_params.setProductCode("N8080");
        l_params.setDeliveryDate(l_newDate);
        l_params.setDeleteFlag(BooleanEnum.FALSE);
        l_params.setLastUpdater("123456");
        l_params.setCreatedTimestamp(l_newDate);
        l_params.setLastUpdatedTimestamp(l_newDate);
        l_params.setDocumentCategory("001");

        try
        {
            TestDBUtility.deleteAll(DocDeliveryManagementParams.TYPE);
            TestDBUtility.insertWithDel(l_params);

            DocDeliveryManagementParams l_params1 = new DocDeliveryManagementParams();
            l_params1 = l_params;
            l_params1.setAccountId(123456L);
            l_params1.setProductCode("N8081");
            TestDBUtility.insertWithDel(l_params1);
            
            TestDBUtility.deleteAll(DocDivManagementRow.TYPE);
            DocDivManagementParams l_docDivManagementParams = new DocDivManagementParams();
            l_docDivManagementParams.setInstitutionCode("0D");
            l_docDivManagementParams.setBranchCode("381");
            l_docDivManagementParams.setDocumentDiv("1");
            l_docDivManagementParams.setDocumentCheckDiv("1");
            l_docDivManagementParams.setDocumentNumber("12");
            l_docDivManagementParams.setDocumentName("そこです");
            l_docDivManagementParams.setLastUpdater("12345");
            l_docDivManagementParams.setCreatedTimestamp(new Date());
            l_docDivManagementParams.setLastUpdatedTimestamp(new Date());
            l_docDivManagementParams.setDocumentCategory("001");
            TestDBUtility.insertWithDel(l_docDivManagementParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(TestDBUtility.getInstitutionRow());
            WEB3GentradeInstitution l_gentradeInstitution = new WEB3GentradeInstitution("0D");
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(TestDBUtility.getInstitutionRow());

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.insertWithDel(TestDBUtility.getMainAccountRow());

            IpoProductParams l_ipoProductParams = new IpoProductParams();
            //IPO銘柄ＩＤ
            l_ipoProductParams.setIpoProductId(123456);
            //証券会社コード
            l_ipoProductParams.setInstitutionCode("0D");
            //銘柄コード
            l_ipoProductParams.setProductCode("N8080");
            //銘柄タイプ
            l_ipoProductParams.setProductType(ProductTypeEnum.OTHER);
            //IPO登録区分
            l_ipoProductParams.setIpoRegistDiv("1");
            //IPO登録区分詳細
            l_ipoProductParams.setIpoRegistDetailDiv("1");
            l_ipoProductParams.setDeleteFlag(BooleanEnum.FALSE);
            //公開市場
            l_ipoProductParams.setPublicMarket("1");
            //仮条件区分
            l_ipoProductParams.setProvisionalValueDiv("1");
            l_ipoProductParams.setStandardName("田中さん");
            TestDBUtility.deleteAll(IpoProductParams.TYPE);
            TestDBUtility.insertWithDel(l_ipoProductParams);
            
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0L);
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);
            
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("G0102");
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            TestDBUtility.insertWithDel(l_administratorTypeParams);
            
            //DocCategoryManagementParams
            DocCategoryManagementParams l_docParams =
                this.getDocCategoryManagementRow();
            l_docParams.setDocumentCategory("001");
            l_docParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_docParams);
            
            WEB3AdminFPTListReferenceServiceImpl l_impl = new WEB3AdminFPTListReferenceServiceImpl();
            WEB3AdminFPTListReferenceResponse l_response = l_impl.getListReference(l_request);
            
            assertEquals(1, l_response.financialProductTradeList.length);
//            assertEquals("N8081", l_response.financialProductTradeList[0].productCode);
            assertEquals("2", l_response.pageIndex);
            assertEquals("2", l_response.totalPages);
            assertEquals("2", l_response.totalRecords);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetListReference_0004()
    {
        final String STR_METHOD_NAME = " testGetListReference_0004()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3AdminFPTListReferenceRequest l_request = new WEB3AdminFPTListReferenceRequest();
            l_request.branchCode = new String[]{"aaaaaa"};

            WEB3AdminFPTListReferenceServiceImpl l_impl = new WEB3AdminFPTListReferenceServiceImpl();
            WEB3AdminFPTListReferenceResponse l_response = l_impl.getListReference(l_request);
            
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01729, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetListReference_0005()
    {
        final String STR_METHOD_NAME = " testGetListReference_0005()";
        log.entering(STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        WEB3AdminFPTSortKey[] l_sortKey = new WEB3AdminFPTSortKey[0];
        WEB3AdminFPTListReferenceRequestForTest l_request = new WEB3AdminFPTListReferenceRequestForTest();
        l_request.sortKeys = l_sortKey;
        l_request.pageSize = "1";
        l_request.pageIndex = "1";
        
        LoginInfoImpl l_loginInfoImpl = new LoginInfoImpl();
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getLoginInfo",
            new Class[] {},
            l_loginInfoImpl);
        
        try
        {
            TestDBUtility.deleteAll(DocDeliveryManagementParams.TYPE);

            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0L);
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);
            
//            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
//            l_adminPermissionParams.setInstitutionCode("0D");
//            l_adminPermissionParams.setPermissionLevel("331");
//            l_adminPermissionParams.setTransactionCategory("G0102");
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
//            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            TestDBUtility.insertWithDel(l_administratorTypeParams);
            
            WEB3AdminFPTListReferenceServiceImpl l_impl = new WEB3AdminFPTListReferenceServiceImpl();
            WEB3AdminFPTListReferenceResponse l_response = l_impl.getListReference(l_request);
            
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetListReference_0006()
    {
        final String STR_METHOD_NAME = " testGetListReference_0006()";
        log.entering(STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        WEB3AdminFPTSortKey[] l_sortKey = new WEB3AdminFPTSortKey[0];
        WEB3AdminFPTListReferenceRequestForTest l_request = new WEB3AdminFPTListReferenceRequestForTest();
        l_request.sortKeys = l_sortKey;
        l_request.branchCode = new String[]{"0000"};
        l_request.pageSize = "1";
        l_request.pageIndex = "1";
        
        LoginInfoImpl l_loginInfoImpl = new LoginInfoImpl();
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getLoginInfo",
            new Class[] {},
            l_loginInfoImpl);
        
        try
        {
            TestDBUtility.deleteAll(DocDeliveryManagementParams.TYPE);

            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0L);
            l_administratorParams.setBranchCode("381");
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);
            
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("G0102");
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.FALSE);
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            TestDBUtility.insertWithDel(l_administratorTypeParams);
            
            WEB3AdminFPTListReferenceServiceImpl l_impl = new WEB3AdminFPTListReferenceServiceImpl();
            WEB3AdminFPTListReferenceResponse l_response = l_impl.getListReference(l_request);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01074, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetListReference_0007()
    {
        final String STR_METHOD_NAME = " testGetListReference_0007()";
        log.entering(STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        WEB3AdminFPTSortKey[] l_sortKey = new WEB3AdminFPTSortKey[1];
        l_sortKey[0] = new WEB3AdminFPTSortKey();
        l_sortKey[0].keyItem = "deemedDeliveryDate";
        l_sortKey[0].ascDesc = "A";
        WEB3FPTDocumentDivAdminInfoUnit[] l_documentDivList =
            new WEB3FPTDocumentDivAdminInfoUnit[0];
        
        WEB3AdminFPTListReferenceRequestForTest l_request = new WEB3AdminFPTListReferenceRequestForTest();
        l_request.documentDivList = l_documentDivList;
        l_request.sortKeys = l_sortKey;
        l_request.pageSize = "5";
        l_request.pageIndex = "1";
        l_request.branchCode = new String[]{"381", "624", "123"};
        
        LoginInfoImpl l_loginInfoImpl = new LoginInfoImpl();
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getLoginInfo",
            new Class[] {},
            l_loginInfoImpl);
        
        Date l_newDate = new Date();
        //1111111
        
        DocDeliveryManagementParams l_params = new DocDeliveryManagementParams();
        l_params.setAccountId(12345L);
        l_params.setInstitutionCode("0D");
        l_params.setBranchCode("381");
        l_params.setDocumentDiv("1");
        l_params.setAccountCode("2512246");
        l_params.setProductCode("N8080");
        l_params.setDeliveryDate(WEB3DateUtility.getDate("20080416", "yyyyMMdd"));
        l_params.setDeleteFlag(BooleanEnum.FALSE);
        l_params.setLastUpdater("123456");
        l_params.setCreatedTimestamp(l_newDate);
        l_params.setLastUpdatedTimestamp(l_newDate);
        l_params.setDocumentCategory("001");
        l_params.setDeemedDeliveryDate(null);
        
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doDeleteAllQuery(DocDeliveryManagementParams.TYPE);
            
            //111
            l_queryProcessor.doInsertQuery(l_params);
            
            //222222
            l_params.setBranchCode("624");
            l_params.setDeemedDeliveryDate(WEB3DateUtility.getDate("20080417", "yyyyMMdd"));
            l_params.setDeliveryDate(WEB3DateUtility.getDate("20080417", "yyyyMMdd"));
            l_queryProcessor.doInsertQuery(l_params);

            //333333
            l_params.setBranchCode("123");
            l_params.setDeemedDeliveryDate(null);
            l_params.setDeliveryDate(WEB3DateUtility.getDate("20080418", "yyyyMMdd"));
            l_queryProcessor.doInsertQuery(l_params);
            
            l_queryProcessor.doDeleteAllQuery(MainAccountParams.TYPE);
            //1111
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountCode("2512246");
            l_mainAccountParams.setAccountId(123456789);
            l_mainAccountParams.setBranchId(12456789);
            l_queryProcessor.doInsertQuery(l_mainAccountParams);
            
            //222
            l_mainAccountParams.setBranchCode("381");
            l_mainAccountParams.setAccountId(123045678);
            l_mainAccountParams.setBranchId(123045678);
            l_queryProcessor.doInsertQuery(l_mainAccountParams);
            
            //333
            l_mainAccountParams.setBranchCode("123");
            l_mainAccountParams.setAccountId(123401567);
            l_mainAccountParams.setBranchId(123401567);
            l_queryProcessor.doInsertQuery(l_mainAccountParams);
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        try
        {
            
            TestDBUtility.deleteAll(DocDivManagementRow.TYPE);
            DocDivManagementParams l_docDivManagementParams = new DocDivManagementParams();
            l_docDivManagementParams.setInstitutionCode("0D");
            l_docDivManagementParams.setBranchCode("381");
            l_docDivManagementParams.setDocumentDiv("1");
            l_docDivManagementParams.setDocumentCheckDiv("1");
            l_docDivManagementParams.setDocumentNumber("12");
            l_docDivManagementParams.setDocumentName("そこです");
            l_docDivManagementParams.setLastUpdater("12345");
            l_docDivManagementParams.setCreatedTimestamp(new Date());
            l_docDivManagementParams.setLastUpdatedTimestamp(new Date());
            l_docDivManagementParams.setDocumentCategory("001");
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doInsertQuery(l_docDivManagementParams);
            
            //22
            l_docDivManagementParams.setBranchCode("624");
            l_queryProcessor.doInsertQuery(l_docDivManagementParams);
            //33
            l_docDivManagementParams.setBranchCode("123");
            l_queryProcessor.doInsertQuery(l_docDivManagementParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(TestDBUtility.getInstitutionRow());
            WEB3GentradeInstitution l_gentradeInstitution = new WEB3GentradeInstitution("0D");
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(TestDBUtility.getInstitutionRow());

            IpoProductParams l_ipoProductParams = new IpoProductParams();
            //IPO銘柄ＩＤ
            l_ipoProductParams.setIpoProductId(123456);
            //証券会社コード
            l_ipoProductParams.setInstitutionCode("0D");
            //銘柄コード
            l_ipoProductParams.setProductCode("N8080");
            //銘柄タイプ
            l_ipoProductParams.setProductType(ProductTypeEnum.OTHER);
            //IPO登録区分
            l_ipoProductParams.setIpoRegistDiv("1");
            //IPO登録区分詳細
            l_ipoProductParams.setIpoRegistDetailDiv("1");
            l_ipoProductParams.setDeleteFlag(BooleanEnum.FALSE);
            //公開市場
            l_ipoProductParams.setPublicMarket("1");
            //仮条件区分
            l_ipoProductParams.setProvisionalValueDiv("1");
            l_ipoProductParams.setStandardName("田中さん");
            TestDBUtility.deleteAll(IpoProductParams.TYPE);
            TestDBUtility.insertWithDel(l_ipoProductParams);
            
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0L);
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);
            
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("G0102");
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            TestDBUtility.insertWithDel(l_administratorTypeParams);
            
            //DocCategoryManagementParams
            l_queryProcessor.doDeleteAllQuery(DocCategoryManagementParams.TYPE);
            DocCategoryManagementParams l_docParams =
                this.getDocCategoryManagementRow();
            l_docParams.setDocumentCategory("001");
            l_docParams.setBranchCode("381");
            l_queryProcessor.doInsertQuery(l_docParams);
            
            l_docParams.setBranchCode("624");
            l_queryProcessor.doInsertQuery(l_docParams);
            l_docParams.setBranchCode("123");
            l_queryProcessor.doInsertQuery(l_docParams);
            
            WEB3AdminFPTListReferenceServiceImpl l_impl = new WEB3AdminFPTListReferenceServiceImpl();
            WEB3AdminFPTListReferenceResponse l_response = l_impl.getListReference(l_request);
            
            assertEquals(3, l_response.financialProductTradeList.length);
            assertNull(l_response.financialProductTradeList[0].deemedDeliveryDate);
            assertNull(l_response.financialProductTradeList[1].deemedDeliveryDate);
            assertNotNull(l_response.financialProductTradeList[2].deemedDeliveryDate);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetListReference_0008()
    {
        final String STR_METHOD_NAME = " testGetListReference_0008()";
        log.entering(STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        WEB3AdminFPTSortKey[] l_sortKey = new WEB3AdminFPTSortKey[1];
        l_sortKey[0] = new WEB3AdminFPTSortKey();
        l_sortKey[0].keyItem = "deemedDeliveryDate";
        l_sortKey[0].ascDesc = "D";
        WEB3FPTDocumentDivAdminInfoUnit[] l_documentDivList =
            new WEB3FPTDocumentDivAdminInfoUnit[0];
        
        WEB3AdminFPTListReferenceRequestForTest l_request = new WEB3AdminFPTListReferenceRequestForTest();
        l_request.documentDivList = l_documentDivList;
        l_request.sortKeys = l_sortKey;
        l_request.pageSize = "5";
        l_request.pageIndex = "1";
        int cc = 1;
        l_request.branchCode = new String[]{"381", "624", "123"};
        
        LoginInfoImpl l_loginInfoImpl = new LoginInfoImpl();
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getLoginInfo",
            new Class[] {},
            l_loginInfoImpl);
        
        Date l_newDate = new Date();
        //1111111
        
        DocDeliveryManagementParams l_params = new DocDeliveryManagementParams();
        l_params.setAccountId(12345L);
        l_params.setInstitutionCode("0D");
        l_params.setBranchCode("381");
        l_params.setDocumentDiv("1");
        l_params.setAccountCode("2512246");
        l_params.setProductCode("N8080");
        l_params.setDeliveryDate(WEB3DateUtility.getDate("20080416", "yyyyMMdd"));
        l_params.setDeleteFlag(BooleanEnum.FALSE);
        l_params.setLastUpdater("123456");
        l_params.setCreatedTimestamp(l_newDate);
        l_params.setLastUpdatedTimestamp(l_newDate);
        l_params.setDocumentCategory("001");
        l_params.setDeemedDeliveryDate(null);
        
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doDeleteAllQuery(DocDeliveryManagementParams.TYPE);
            
            //111
            l_queryProcessor.doInsertQuery(l_params);
            
            //222222
            l_params.setBranchCode("624");
            l_params.setDeemedDeliveryDate(WEB3DateUtility.getDate("20080417", "yyyyMMdd"));
            l_params.setDeliveryDate(WEB3DateUtility.getDate("20080417", "yyyyMMdd"));
            l_queryProcessor.doInsertQuery(l_params);

            //333333
            l_params.setBranchCode("123");
            l_params.setDeemedDeliveryDate(null);
            l_params.setDeliveryDate(WEB3DateUtility.getDate("20080418", "yyyyMMdd"));
            l_queryProcessor.doInsertQuery(l_params);
            
            l_queryProcessor.doDeleteAllQuery(MainAccountParams.TYPE);
            //1111
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountCode("2512246");
            l_mainAccountParams.setAccountId(123456789);
            l_mainAccountParams.setBranchId(12456789);
            l_queryProcessor.doInsertQuery(l_mainAccountParams);
            
            //222
            l_mainAccountParams.setBranchCode("381");
            l_mainAccountParams.setAccountId(123045678);
            l_mainAccountParams.setBranchId(123045678);
            l_queryProcessor.doInsertQuery(l_mainAccountParams);
            
            //333
            l_mainAccountParams.setBranchCode("123");
            l_mainAccountParams.setAccountId(123401567);
            l_mainAccountParams.setBranchId(123401567);
            l_queryProcessor.doInsertQuery(l_mainAccountParams);
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        try
        {
            
            TestDBUtility.deleteAll(DocDivManagementRow.TYPE);
            DocDivManagementParams l_docDivManagementParams = new DocDivManagementParams();
            l_docDivManagementParams.setInstitutionCode("0D");
            l_docDivManagementParams.setBranchCode("381");
            l_docDivManagementParams.setDocumentDiv("1");
            l_docDivManagementParams.setDocumentCheckDiv("1");
            l_docDivManagementParams.setDocumentNumber("12");
            l_docDivManagementParams.setDocumentName("そこです");
            l_docDivManagementParams.setLastUpdater("12345");
            l_docDivManagementParams.setCreatedTimestamp(new Date());
            l_docDivManagementParams.setLastUpdatedTimestamp(new Date());
            l_docDivManagementParams.setDocumentCategory("001");
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doInsertQuery(l_docDivManagementParams);
            
            //22
            l_docDivManagementParams.setBranchCode("624");
            l_queryProcessor.doInsertQuery(l_docDivManagementParams);
            //33
            l_docDivManagementParams.setBranchCode("123");
            l_queryProcessor.doInsertQuery(l_docDivManagementParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(TestDBUtility.getInstitutionRow());
            WEB3GentradeInstitution l_gentradeInstitution = new WEB3GentradeInstitution("0D");
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(TestDBUtility.getInstitutionRow());

            IpoProductParams l_ipoProductParams = new IpoProductParams();
            //IPO銘柄ＩＤ
            l_ipoProductParams.setIpoProductId(123456);
            //証券会社コード
            l_ipoProductParams.setInstitutionCode("0D");
            //銘柄コード
            l_ipoProductParams.setProductCode("N8080");
            //銘柄タイプ
            l_ipoProductParams.setProductType(ProductTypeEnum.OTHER);
            //IPO登録区分
            l_ipoProductParams.setIpoRegistDiv("1");
            //IPO登録区分詳細
            l_ipoProductParams.setIpoRegistDetailDiv("1");
            l_ipoProductParams.setDeleteFlag(BooleanEnum.FALSE);
            //公開市場
            l_ipoProductParams.setPublicMarket("1");
            //仮条件区分
            l_ipoProductParams.setProvisionalValueDiv("1");
            l_ipoProductParams.setStandardName("田中さん");
            TestDBUtility.deleteAll(IpoProductParams.TYPE);
            TestDBUtility.insertWithDel(l_ipoProductParams);
            
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0L);
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);
            
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("G0102");
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            TestDBUtility.insertWithDel(l_administratorTypeParams);
            
            //DocCategoryManagementParams
            l_queryProcessor.doDeleteAllQuery(DocCategoryManagementParams.TYPE);
            DocCategoryManagementParams l_docParams =
                this.getDocCategoryManagementRow();
            l_docParams.setDocumentCategory("001");
            l_docParams.setBranchCode("381");
            l_queryProcessor.doInsertQuery(l_docParams);
            
            l_docParams.setBranchCode("624");
            l_queryProcessor.doInsertQuery(l_docParams);
            l_docParams.setBranchCode("123");
            l_queryProcessor.doInsertQuery(l_docParams);
            
            WEB3AdminFPTListReferenceServiceImpl l_impl = new WEB3AdminFPTListReferenceServiceImpl();
            WEB3AdminFPTListReferenceResponse l_response = l_impl.getListReference(l_request);
            
            assertEquals(3, l_response.financialProductTradeList.length);
            assertNotNull(l_response.financialProductTradeList[0].deemedDeliveryDate);
            assertNull(l_response.financialProductTradeList[1].deemedDeliveryDate);
            assertNull(l_response.financialProductTradeList[2].deemedDeliveryDate);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExecute_0001()
    {
        final String STR_METHOD_NAME = " testExecute_0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3AdminDocAdminFPTListReferenceServiceImplForTest l_impl = new WEB3AdminDocAdminFPTListReferenceServiceImplForTest();
            WEB3GenResponse l_response = l_impl.execute(null);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExecute_0002()
    {
        final String STR_METHOD_NAME = " testExecute_0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3AdminFPTSearchInputRequest l_request = new WEB3AdminFPTSearchInputRequest();
            WEB3AdminDocAdminFPTListReferenceServiceImplForTest l_impl = new WEB3AdminDocAdminFPTListReferenceServiceImplForTest();
            WEB3GenResponse l_response = l_impl.execute(l_request);
            
            assertEquals(WEB3AdminFPTSearchInputResponse.class, l_response.getClass());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExecute_0004()
    {
        final String STR_METHOD_NAME = " testExecute_0004()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3AdminDocAdminFPTListReferenceServiceImplForTest l_impl = new WEB3AdminDocAdminFPTListReferenceServiceImplForTest();
            WEB3GenResponse l_response = l_impl.execute(new WEB3AdminFPTRegistCompleteRequest());
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_ex.getErrorInfo());
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExecute_0003()
    {
        final String STR_METHOD_NAME = " testExecute_0003()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3AdminFPTListReferenceRequest l_request = new WEB3AdminFPTListReferenceRequest();
            WEB3AdminDocAdminFPTListReferenceServiceImplForTest l_impl = new WEB3AdminDocAdminFPTListReferenceServiceImplForTest();
            WEB3GenResponse l_response = l_impl.execute(l_request);
            
            assertEquals(WEB3AdminFPTListReferenceResponse.class, l_response.getClass());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateQueryCondition_0001()
    {
        final String STR_METHOD_NAME = "testValidateQueryCondition_0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3FPTDocumentDivAdminInfoUnit[] l_documentDivList =
                new WEB3FPTDocumentDivAdminInfoUnit[0];
            
            WEB3AdminFPTListReferenceServiceImpl l_impl =
                new WEB3AdminFPTListReferenceServiceImpl();

            String l_strInstitutionCode = "0D";
            
            WEB3AdminFPTListReferenceRequest l_request =
                new WEB3AdminFPTListReferenceRequest();
            l_request.documentDivList = l_documentDivList;
            
            Method l_method = WEB3AdminFPTListReferenceServiceImpl.class.getDeclaredMethod(
                "validateQueryCondition",
                new Class[]{String.class, WEB3AdminFPTListReferenceRequest.class});
            l_method.setAccessible(true);
            l_method.invoke(l_impl, new Object[]{l_strInstitutionCode, l_request});
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateQueryCondition_0002()
    {
        final String STR_METHOD_NAME = "testValidateQueryCondition_0002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3FPTDocumentDivAdminInfoUnit[] l_documentDivList =
                new WEB3FPTDocumentDivAdminInfoUnit[1];
            l_documentDivList[0] = null;
            
            WEB3AdminFPTListReferenceServiceImpl l_impl =
                new WEB3AdminFPTListReferenceServiceImpl();

            String l_strInstitutionCode = "0D";
            
            WEB3AdminFPTListReferenceRequest l_request =
                new WEB3AdminFPTListReferenceRequest();
            l_request.documentDivList = l_documentDivList;
            
            Method l_method = WEB3AdminFPTListReferenceServiceImpl.class.getDeclaredMethod(
                "validateQueryCondition",
                new Class[]{String.class, WEB3AdminFPTListReferenceRequest.class});
            l_method.setAccessible(true);
            l_method.invoke(l_impl, new Object[]{l_strInstitutionCode, l_request});
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateQueryCondition_0003()
    {
        final String STR_METHOD_NAME = "testValidateQueryCondition_0003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3FPTDocumentDivAdminInfoUnit[] l_documentDivList =
                new WEB3FPTDocumentDivAdminInfoUnit[1];
            WEB3FPTDocumentDivAdminInfoUnit l_infoUnit0 =
                new WEB3FPTDocumentDivAdminInfoUnit();
            //111111111
            l_infoUnit0.documentCategory = "001";
            l_documentDivList[0] = l_infoUnit0;
            
            WEB3AdminFPTListReferenceServiceImpl l_impl =
                new WEB3AdminFPTListReferenceServiceImpl();

            
            String l_strInstitutionCode = "0D";
            WEB3AdminFPTListReferenceRequest l_request =
                new WEB3AdminFPTListReferenceRequest();
            l_request.documentDivList = l_documentDivList;
            l_request.branchCode = new String[]{"624"};

            Method l_method = WEB3AdminFPTListReferenceServiceImpl.class.getDeclaredMethod(
                "validateQueryCondition",
                new Class[]{String.class, WEB3AdminFPTListReferenceRequest.class});
            l_method.setAccessible(true);
            l_method.invoke(l_impl, new Object[]{l_strInstitutionCode, l_request});
            fail();
        }
        catch (InvocationTargetException l_ex)
        {
            WEB3BusinessLayerException l_exc =
                (WEB3BusinessLayerException)l_ex.getTargetException();
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02997, l_exc.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateQueryCondition_0004()
    {
        final String STR_METHOD_NAME = "testValidateQueryCondition_0004()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3FPTDocumentDivAdminInfoUnit[] l_documentDivList =
                new WEB3FPTDocumentDivAdminInfoUnit[3];
            WEB3FPTDocumentDivAdminInfoUnit l_infoUnit0 =
                new WEB3FPTDocumentDivAdminInfoUnit();
            //111111111
            l_infoUnit0.documentCategory = "001";
            l_documentDivList[0] = l_infoUnit0;
            
            //222222222222
            l_infoUnit0.documentCategory = "002";
            l_documentDivList[1] = l_infoUnit0;
            //3333333333
            l_infoUnit0.documentCategory = "003";
            l_documentDivList[2] = l_infoUnit0;
            
            WEB3AdminFPTListReferenceServiceImpl l_impl =
                new WEB3AdminFPTListReferenceServiceImpl();

            
            String l_strInstitutionCode = "0D";
            WEB3AdminFPTListReferenceRequest l_request =
                new WEB3AdminFPTListReferenceRequest();
            l_request.documentDivList = l_documentDivList;
            l_request.branchCode = new String[]{"624"};

            //DocCategoryManagementParams
            DocCategoryManagementParams l_params =
                this.getDocCategoryManagementRow();
            l_params.setDocumentCategory("001");
            TestDBUtility.insertWithDel(l_params);
            
            //2222222
            l_params.setDocumentCategory("002");
            TestDBUtility.insertWithDel(l_params);
            
            //333
            l_params.setDocumentCategory("003");
            TestDBUtility.insertWithDel(l_params);
            
            Method l_method = WEB3AdminFPTListReferenceServiceImpl.class.getDeclaredMethod(
                "validateQueryCondition",
                new Class[]{String.class, WEB3AdminFPTListReferenceRequest.class});
            l_method.setAccessible(true);
            l_method.invoke(l_impl, new Object[]{l_strInstitutionCode, l_request});

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    private class WEB3AdminFPTListReferenceRequestForTest extends WEB3AdminFPTListReferenceRequest
    {
        public void validate() throws WEB3BaseException
        {
            
        }
    }
    
    private class WEB3AdminDocAdminFPTListReferenceServiceImplForTest extends WEB3AdminFPTListReferenceServiceImpl
    {
        protected WEB3AdminFPTSearchInputResponse getListSearchInput(
                WEB3AdminFPTSearchInputRequest l_request) throws WEB3BaseException
        {
            return new WEB3AdminFPTSearchInputResponse();
        }
        
        protected WEB3AdminFPTListReferenceResponse getListReference(
                WEB3AdminFPTListReferenceRequest l_request) throws WEB3BaseException
        {
            return new WEB3AdminFPTListReferenceResponse();
        }
    }
    
    public DocCategoryManagementParams getDocCategoryManagementRow()
    {
        DocCategoryManagementParams l_params =
            new DocCategoryManagementParams();
        
        //証券会社コード     institution_code  VARCHAR2  3  NotNull
        l_params.setInstitutionCode("0D");
        //部店コード   branch_code  VARCHAR2  3  NotNull
        l_params.setBranchCode("624");
        //書面種類コード     document_category  VARCHAR2  3  NotNull
        l_params.setDocumentCategory("001");
        //書面種類名称      document_cate_name  VARCHAR2  200  Null
        l_params.setDocumentCateName("jiddk");
        //書面種類名称_英語   document_cate_name_E  VARCHAR2  200  Null
        l_params.setDocumentCateNameE("jidck");
        //交付対象    delivery_target  VARCHAR2  2  Null
        l_params.setDeliveryTarget("0");
        //更新者コード      last_updater  VARCHAR2  20  Null
        l_params.setLastUpdater("123456");
        //作成日付    created_timestamp  DATE    NotNull
        l_params.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        //更新日付    last_updated_timestamp  DATE    NotNull
        l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        return l_params;
    }
    
    public DocDivManagementParams getDocDivManagementRow()
    {
        DocDivManagementParams l_params = new DocDivManagementParams();
        //証券会社コード  institution_code     VARCHAR2  3   NotNull
        l_params.setInstitutionCode("0D");
        //部店コード  branch_code    VARCHAR2  3   NotNull
        l_params.setBranchCode("624");
        //書面区分  document_div    VARCHAR2  3   NotNull
        l_params.setDocumentDiv("1");
        //書面チェック区分  document_check_div      VARCHAR2  2   NotNull
        l_params.setDocumentCheckDiv("1");
        //書面通番  document_number     VARCHAR2  2   NotNull
        l_params.setDocumentNumber("1");
        //書面名称  document_name   VARCHAR2  40   Null
        l_params.setDocumentName("jiddk");
        //更新者コード  last_updater      VARCHAR2  20   Null
        //作成日付  created_timestamp   DATE     NotNull
        l_params.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        //更新日付  last_updated_timestamp      DATE     NotNull
        l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        //書面種類コード  document_category    VARCHAR2  3   NotNull
        l_params.setDocumentCategory("001");
        return l_params;
    }
    
    public void deleteDB()
    {
        try
        {
            //DocCategoryManagementParams
            TestDBUtility.deleteAll(DocCategoryManagementParams.TYPE);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }
}
@
