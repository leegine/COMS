head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.59.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FPTDocumentGetServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright           : (株)大和総研ビジネス・イノベーション
 File Name           : WEB3FPTDocumentGetServiceImplTest.java
 Author Name         : Daiwa Institute of Research Business Innovation
 Revision History    : 2010/11/18 劉レイ(北京中訊) 新規作成
 */
package webbroker3.gentrade.service.delegate.stdimpls;

import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImplForMock;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3DocumentCheckDivDef;
import webbroker3.gentrade.data.BatoProductManagementParams;
import webbroker3.gentrade.data.BatoProductManagementRow;
import webbroker3.gentrade.data.DocCategoryManagementParams;
import webbroker3.gentrade.data.DocCategoryManagementRow;
import webbroker3.gentrade.data.DocDivManagementParams;
import webbroker3.gentrade.data.DocDivManagementRow;
import webbroker3.gentrade.message.WEB3FPTDocumentGetRequest;
import webbroker3.gentrade.message.WEB3FPTDocumentGetResponse;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3FPTDocumentGetServiceImplTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FPTDocumentGetServiceImplTest.class);

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public WEB3FPTDocumentGetServiceImplTest(String name)
    {
        super(name);
    }

    /**
     * 書面種類管理テーブル
     * @@return
     */
    public DocCategoryManagementParams getDocCategoryManagementRow()
    {
        DocCategoryManagementParams params = new DocCategoryManagementParams();
        params.setInstitutionCode("0D");
        params.setBranchCode("381");
        params.setDocumentCategory("001");
        params.setDocumentCateName("あります");
        params.setDocumentCateNameE("arimasu");
        params.setDeliveryTarget("0");
        params.setLastUpdater("123456789");
        params.setCreatedTimestamp(WEB3DateUtility.getDate("20070707", "yyyyMMdd"));
        params.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070707", "yyyyMMdd"));
        
        return params;
    }
    
    /**
     * 書面区分管理テーブル
     * @@return
     */
    public DocDivManagementParams getDocDivManagementRow()
    {
        DocDivManagementParams params = new DocDivManagementParams();
        params.setInstitutionCode("0D");
        params.setBranchCode("381");
        params.setDocumentDiv("000");
        params.setDocumentCheckDiv("1");
        params.setDocumentNumber("0");
        params.setDocumentCategory("001");
        params.setCreatedTimestamp(WEB3DateUtility.getDate("20070707", "yyyyMMdd"));
        params.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070707", "yyyyMMdd"));
        
        return params;
    }
    
    /**
     * 電子鳩銘柄コード管理テーブル
     * @@return
     */
    public BatoProductManagementParams getBatoProductManagementRow()
    {
        BatoProductManagementParams params = new BatoProductManagementParams();
        params.setInstitutionCode("0D");
        params.setBranchCode("381");
        params.setDocumentDiv("000");
        params.setBatoProductCode("0000000");
        params.setCreatedTimestamp(WEB3DateUtility.getDate("20070707", "yyyyMMdd"));
        params.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070707", "yyyyMMdd"));
        params.setValidFlag("0");
        
        return params;
    }
    
    public void testExecuteCase0001()
    {
        final String STR_METHOD_NAME = "testExecuteCase0001()";
        log.entering(STR_METHOD_NAME);
        
        WEB3FPTDocumentGetServiceImpl l_impl =
            new WEB3FPTDocumentGetServiceImpl();
        
        MOCK_MANAGER.setIsMockUsed(true);
        LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImplForMock);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(3338111123L));
        
        TraderParams l_traderParams = TestDBUtility.getTraderRow();
        
        WEB3FPTDocumentGetRequest l_request = null;
        
        try
        {
            TestDBUtility.deleteAll(TraderRow.TYPE);

            TestDBUtility.insertWithDel(l_traderParams);
            
            l_impl.execute(l_request);
        }
        catch(WEB3SystemLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();   
        }
    }
    
    public void testExecuteCase0002()
    {
        final String STR_METHOD_NAME = "testExecuteCase0002()";
        log.entering(STR_METHOD_NAME);
        
        WEB3FPTDocumentGetServiceImpl l_impl =
            new WEB3FPTDocumentGetServiceImpl();
        
        MOCK_MANAGER.setIsMockUsed(true);
        LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImplForMock);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(3338111123L));
        
        TraderParams l_traderParams = TestDBUtility.getTraderRow();
        
        WEB3FPTDocumentGetRequest l_request = new WEB3FPTDocumentGetRequest();
        
        l_request.deliveryTarget = null;
        
        try
        {
            TestDBUtility.deleteAll(TraderRow.TYPE);

            TestDBUtility.insertWithDel(l_traderParams);
            
            l_impl.execute(l_request);
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03223,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();   
        }
    }
    
    public void testExecuteCase0003()
    {
        final String STR_METHOD_NAME = "testExecuteCase0003()";
        log.entering(STR_METHOD_NAME);
        
        WEB3FPTDocumentGetServiceImpl l_impl =
            new WEB3FPTDocumentGetServiceImpl();
        
        MOCK_MANAGER.setIsMockUsed(true);
        
        LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImplForMock);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(3338111123L));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        TraderParams l_traderParams = TestDBUtility.getTraderRow();
        InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
        institutionParams.setInstitutionId(l_mainAccountParams.getInstitutionId());
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        DocCategoryManagementParams l_docCategoryManagementParams =
            this.getDocCategoryManagementRow();
        
        WEB3FPTDocumentGetRequest l_request = new WEB3FPTDocumentGetRequest();
        
        l_request.deliveryTarget = "1";
        
        log.debug("顧客.証券会社コード:" +  l_mainAccountParams.getInstitutionCode());
        log.debug("顧客.部店コード:" +  l_mainAccountParams.getBranchCode());
        log.debug("リクエスト.交付対象:" +  l_request.deliveryTarget);
        
        try
        {
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TestDBUtility.insertWithDel(l_traderParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(DocCategoryManagementRow.TYPE);
            TestDBUtility.insertWithDel(l_docCategoryManagementParams);
            
            l_impl.execute(l_request);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();   
        }
    }
    
    public void testExecuteCase0004()
    {
        final String STR_METHOD_NAME = "testExecuteCase0004()";
        log.entering(STR_METHOD_NAME);
        
        WEB3FPTDocumentGetServiceImpl l_impl =
            new WEB3FPTDocumentGetServiceImpl();
        
        MOCK_MANAGER.setIsMockUsed(true);
        
        LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImplForMock);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(3338111123L));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        TraderParams l_traderParams = TestDBUtility.getTraderRow();
        InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
        institutionParams.setInstitutionId(l_mainAccountParams.getInstitutionId());
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        DocCategoryManagementParams l_docCategoryManagementParams =
            this.getDocCategoryManagementRow();
        
        WEB3FPTDocumentGetRequest l_request = new WEB3FPTDocumentGetRequest();
        
        l_request.deliveryTarget = "0";
        
        log.debug("顧客.証券会社コード:" +  l_mainAccountParams.getInstitutionCode());
        log.debug("顧客.部店コード:" +  l_mainAccountParams.getBranchCode());
        log.debug("リクエスト.交付対象:" +  l_request.deliveryTarget);
        
        try
        {
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TestDBUtility.insertWithDel(l_traderParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(DocCategoryManagementRow.TYPE);
            TestDBUtility.insertWithDel(l_docCategoryManagementParams);
            
            l_impl.execute(l_request);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();   
        }
    }
    
    public void testExecuteCase0005()
    {
        final String STR_METHOD_NAME = "testExecuteCase0005()";
        log.entering(STR_METHOD_NAME);
        
        WEB3FPTDocumentGetServiceImpl l_impl =
            new WEB3FPTDocumentGetServiceImpl();
        
        MOCK_MANAGER.setIsMockUsed(true);
        
        LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImplForMock);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(3338111123L));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        TraderParams l_traderParams = TestDBUtility.getTraderRow();
        InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
        institutionParams.setInstitutionId(l_mainAccountParams.getInstitutionId());
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        DocCategoryManagementParams l_docCategoryManagementParams1 =
            this.getDocCategoryManagementRow();
        DocCategoryManagementParams l_docCategoryManagementParams2 =
            this.getDocCategoryManagementRow();
        l_docCategoryManagementParams2.setDocumentCategory("002");
        
        WEB3FPTDocumentGetRequest l_request = new WEB3FPTDocumentGetRequest();
        
        l_request.deliveryTarget = "0";
        
        log.debug("顧客.証券会社コード:" +  l_mainAccountParams.getInstitutionCode());
        log.debug("顧客.部店コード:" +  l_mainAccountParams.getBranchCode());
        log.debug("リクエスト.交付対象:" +  l_request.deliveryTarget);
        
        try
        {
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TestDBUtility.insertWithDel(l_traderParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(DocCategoryManagementRow.TYPE);
            TestDBUtility.insertWithDel(l_docCategoryManagementParams1);
            TestDBUtility.insertWithDel(l_docCategoryManagementParams2);
            
            l_impl.execute(l_request);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();   
        }
    }
    
    public void testExecuteCase0006()
    {
        final String STR_METHOD_NAME = "testExecuteCase0006()";
        log.entering(STR_METHOD_NAME);
        
        WEB3FPTDocumentGetServiceImpl l_impl =
            new WEB3FPTDocumentGetServiceImpl();
        
        MOCK_MANAGER.setIsMockUsed(true);
        
        LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImplForMock);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(3338111123L));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));
        
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        
        TraderParams l_traderParams = TestDBUtility.getTraderRow();
        
        InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
        institutionParams.setInstitutionId(l_mainAccountParams.getInstitutionId());
        
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        
        DocCategoryManagementParams l_docCategoryManagementParams =
            this.getDocCategoryManagementRow();
        
        DocDivManagementParams l_docDivManagementParams =
            this.getDocDivManagementRow();
        
        WEB3FPTDocumentGetRequest l_request = new WEB3FPTDocumentGetRequest();
        
        l_request.deliveryTarget = "0";
        
        log.debug("顧客.証券会社コード:" +  l_mainAccountParams.getInstitutionCode());
        log.debug("顧客.部店コード:" +  l_mainAccountParams.getBranchCode());
        log.debug("リクエスト.交付対象:" +  l_request.deliveryTarget);
        log.debug("書面種類管理テーブル.書面種類コード:" + l_docCategoryManagementParams.getDocumentCategory());
        
        try
        {
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TestDBUtility.insertWithDel(l_traderParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(DocCategoryManagementRow.TYPE);
            TestDBUtility.insertWithDel(l_docCategoryManagementParams);
            
            TestDBUtility.deleteAll(DocDivManagementRow.TYPE);
            TestDBUtility.insertWithDel(l_docDivManagementParams);
            
            l_impl.execute(l_request);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();   
        }
    }
    
    public void testExecuteCase0007()
    {
        final String STR_METHOD_NAME = "testExecuteCase0007()";
        log.entering(STR_METHOD_NAME);
        
        WEB3FPTDocumentGetServiceImpl l_impl =
            new WEB3FPTDocumentGetServiceImpl();
        
        MOCK_MANAGER.setIsMockUsed(true);
        
        LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImplForMock);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(3338111123L));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));
        
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        
        TraderParams l_traderParams = TestDBUtility.getTraderRow();
        
        InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
        institutionParams.setInstitutionId(l_mainAccountParams.getInstitutionId());
        
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        
        DocCategoryManagementParams l_docCategoryManagementParams =
            this.getDocCategoryManagementRow();
        
        DocDivManagementParams l_docDivManagementParams =
            this.getDocDivManagementRow();
        l_docDivManagementParams.setDocumentCheckDiv(WEB3DocumentCheckDivDef.FINANCIAL_PRODUCTS_EXCHANGE_LAW);
        
        WEB3FPTDocumentGetRequest l_request = new WEB3FPTDocumentGetRequest();
        
        l_request.deliveryTarget = "0";
        
        log.debug("顧客.証券会社コード:" +  l_mainAccountParams.getInstitutionCode());
        log.debug("顧客.部店コード:" +  l_mainAccountParams.getBranchCode());
        log.debug("リクエスト.交付対象:" +  l_request.deliveryTarget);
        log.debug("書面種類管理テーブル.書面種類コード:" + l_docCategoryManagementParams.getDocumentCategory());
        
        try
        {
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TestDBUtility.insertWithDel(l_traderParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(DocCategoryManagementRow.TYPE);
            TestDBUtility.insertWithDel(l_docCategoryManagementParams);
            
            TestDBUtility.deleteAll(DocDivManagementRow.TYPE);
            TestDBUtility.insertWithDel(l_docDivManagementParams);
            
            l_impl.execute(l_request);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();   
        }
    }
    
    public void testExecuteCase0008()
    {
        final String STR_METHOD_NAME = "testExecuteCase0008()";
        log.entering(STR_METHOD_NAME);
        
        WEB3FPTDocumentGetServiceImpl l_impl =
            new WEB3FPTDocumentGetServiceImpl();
        
        MOCK_MANAGER.setIsMockUsed(true);
        
        LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImplForMock);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(3338111123L));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));
        
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        
        TraderParams l_traderParams = TestDBUtility.getTraderRow();
        
        InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
        institutionParams.setInstitutionId(l_mainAccountParams.getInstitutionId());
        
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        
        DocCategoryManagementParams l_docCategoryManagementParams =
            this.getDocCategoryManagementRow();
        
        DocDivManagementParams l_docDivManagementParams =
            this.getDocDivManagementRow();
        l_docDivManagementParams.setDocumentCheckDiv(WEB3DocumentCheckDivDef.FINANCIAL_PRODUCTS_EXCHANGE_LAW);
        
        BatoProductManagementParams l_batoProductManagementParams =
            this.getBatoProductManagementRow();
        
        WEB3FPTDocumentGetRequest l_request = new WEB3FPTDocumentGetRequest();
        
        l_request.deliveryTarget = "0";
        
        log.debug("顧客.証券会社コード:" +  l_mainAccountParams.getInstitutionCode());
        log.debug("顧客.部店コード:" +  l_mainAccountParams.getBranchCode());
        log.debug("リクエスト.交付対象:" +  l_request.deliveryTarget);
        log.debug("書面種類管理テーブル.書面種類コード:" + l_docCategoryManagementParams.getDocumentCategory());
        log.debug("書面区分管理テーブル.書面区分:" + l_docDivManagementParams.getDocumentDiv());
        
        try
        {
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TestDBUtility.insertWithDel(l_traderParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(DocCategoryManagementRow.TYPE);
            TestDBUtility.insertWithDel(l_docCategoryManagementParams);
            
            TestDBUtility.deleteAll(DocDivManagementRow.TYPE);
            TestDBUtility.insertWithDel(l_docDivManagementParams);
            
            TestDBUtility.deleteAll(BatoProductManagementRow.TYPE);
            TestDBUtility.insertWithDel(l_batoProductManagementParams);
            
            l_impl.execute(l_request);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();   
        }
    }
    
    public void testExecuteCase0009()
    {
        final String STR_METHOD_NAME = "testExecuteCase0009()";
        log.entering(STR_METHOD_NAME);
        
        WEB3FPTDocumentGetServiceImpl l_impl =
            new WEB3FPTDocumentGetServiceImpl();
        
        MOCK_MANAGER.setIsMockUsed(true);
        
        LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImplForMock);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(3338111123L));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));
        
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        
        TraderParams l_traderParams = TestDBUtility.getTraderRow();
        
        InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
        institutionParams.setInstitutionId(l_mainAccountParams.getInstitutionId());
        
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        
        DocCategoryManagementParams l_docCategoryManagementParams =
            this.getDocCategoryManagementRow();
        
        DocDivManagementParams l_docDivManagementParams =
            this.getDocDivManagementRow();
        l_docDivManagementParams.setDocumentCheckDiv(WEB3DocumentCheckDivDef.FINANCIAL_PRODUCTS_EXCHANGE_LAW);
        
        BatoProductManagementParams l_batoProductManagementParams =
            this.getBatoProductManagementRow();
        l_batoProductManagementParams.setBatoProductCode("0010000");
        
        WEB3FPTDocumentGetRequest l_request = new WEB3FPTDocumentGetRequest();
        
        l_request.deliveryTarget = "0";
        
        log.debug("顧客.証券会社コード:" +  l_mainAccountParams.getInstitutionCode());
        log.debug("顧客.部店コード:" +  l_mainAccountParams.getBranchCode());
        log.debug("リクエスト.交付対象:" +  l_request.deliveryTarget);
        log.debug("書面種類管理テーブル.書面種類コード:" + l_docCategoryManagementParams.getDocumentCategory());
        log.debug("書面区分管理テーブル.書面区分:" + l_docDivManagementParams.getDocumentDiv());
        
        try
        {
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TestDBUtility.insertWithDel(l_traderParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(DocCategoryManagementRow.TYPE);
            TestDBUtility.insertWithDel(l_docCategoryManagementParams);
            
            TestDBUtility.deleteAll(DocDivManagementRow.TYPE);
            TestDBUtility.insertWithDel(l_docDivManagementParams);
            
            TestDBUtility.deleteAll(BatoProductManagementRow.TYPE);
            TestDBUtility.insertWithDel(l_batoProductManagementParams);
            
            l_impl.execute(l_request);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();   
        }
    }
    
    public void testExecuteCase0010()
    {
        final String STR_METHOD_NAME = "testExecuteCase0010()";
        log.entering(STR_METHOD_NAME);
        
        WEB3FPTDocumentGetServiceImpl l_impl =
            new WEB3FPTDocumentGetServiceImpl();
        
        MOCK_MANAGER.setIsMockUsed(true);
        
        LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImplForMock);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(3338111123L));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));
        
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        
        TraderParams l_traderParams = TestDBUtility.getTraderRow();
        
        InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
        institutionParams.setInstitutionId(l_mainAccountParams.getInstitutionId());
        
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        
        DocCategoryManagementParams l_docCategoryManagementParams =
            this.getDocCategoryManagementRow();
        
        DocDivManagementParams l_docDivManagementParams =
            this.getDocDivManagementRow();
        l_docDivManagementParams.setDocumentCheckDiv(WEB3DocumentCheckDivDef.FINANCIAL_PRODUCTS_EXCHANGE_LAW);
        
        BatoProductManagementParams l_batoProductManagementParams =
            this.getBatoProductManagementRow();
        l_batoProductManagementParams.setBatoProductCode("0010000");
        
        BatoProductManagementParams l_batoProductManagementParams1 =
            this.getBatoProductManagementRow();
        l_batoProductManagementParams1.setBatoProductCode("0012222");
        
        WEB3FPTDocumentGetRequest l_request = new WEB3FPTDocumentGetRequest();
        
        l_request.deliveryTarget = "0";
        
        log.debug("顧客.証券会社コード:" +  l_mainAccountParams.getInstitutionCode());
        log.debug("顧客.部店コード:" +  l_mainAccountParams.getBranchCode());
        log.debug("リクエスト.交付対象:" +  l_request.deliveryTarget);
        log.debug("書面種類管理テーブル.書面種類コード:" + l_docCategoryManagementParams.getDocumentCategory());
        log.debug("書面区分管理テーブル.書面区分:" + l_docDivManagementParams.getDocumentDiv());
        
        try
        {
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TestDBUtility.insertWithDel(l_traderParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(DocCategoryManagementRow.TYPE);
            TestDBUtility.insertWithDel(l_docCategoryManagementParams);
            
            TestDBUtility.deleteAll(DocDivManagementRow.TYPE);
            TestDBUtility.insertWithDel(l_docDivManagementParams);
            
            TestDBUtility.deleteAll(BatoProductManagementRow.TYPE);
            TestDBUtility.insertWithDel(l_batoProductManagementParams);
            TestDBUtility.insertWithDel(l_batoProductManagementParams1);
            
            l_impl.execute(l_request);
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03005,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();   
        }
    }
    
    public void testExecuteCase0011()
    {
        final String STR_METHOD_NAME = "testExecuteCase0011()";
        log.entering(STR_METHOD_NAME);
        
        WEB3FPTDocumentGetServiceImpl l_impl =
            new WEB3FPTDocumentGetServiceImpl();
        
        MOCK_MANAGER.setIsMockUsed(true);
        
        LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImplForMock);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(3338111123L));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "isDocumentDelivery",
                new Class[]{ String.class, String.class },
                Boolean.TRUE);
        
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        
        TraderParams l_traderParams = TestDBUtility.getTraderRow();
        
        InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
        institutionParams.setInstitutionId(l_mainAccountParams.getInstitutionId());
        
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        
        DocCategoryManagementParams l_docCategoryManagementParams =
            this.getDocCategoryManagementRow();
        
        DocDivManagementParams l_docDivManagementParams =
            this.getDocDivManagementRow();
        l_docDivManagementParams.setDocumentCheckDiv(WEB3DocumentCheckDivDef.FINANCIAL_PRODUCTS_EXCHANGE_LAW);
        
        BatoProductManagementParams l_batoProductManagementParams =
            this.getBatoProductManagementRow();
        l_batoProductManagementParams.setBatoProductCode("0010000");
        
        WEB3FPTDocumentGetRequest l_request = new WEB3FPTDocumentGetRequest();
        
        l_request.deliveryTarget = "0";
        
        log.debug("顧客.証券会社コード:" +  l_mainAccountParams.getInstitutionCode());
        log.debug("顧客.部店コード:" +  l_mainAccountParams.getBranchCode());
        log.debug("リクエスト.交付対象:" +  l_request.deliveryTarget);
        log.debug("書面種類管理テーブル.書面種類コード:" + l_docCategoryManagementParams.getDocumentCategory());
        log.debug("書面区分管理テーブル.書面区分:" + l_docDivManagementParams.getDocumentDiv());
        
        WEB3FPTDocumentGetResponse l_response = null;
        
        try
        {
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TestDBUtility.insertWithDel(l_traderParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(DocCategoryManagementRow.TYPE);
            TestDBUtility.insertWithDel(l_docCategoryManagementParams);
            
            TestDBUtility.deleteAll(DocDivManagementRow.TYPE);
            TestDBUtility.insertWithDel(l_docDivManagementParams);
            
            TestDBUtility.deleteAll(BatoProductManagementRow.TYPE);
            TestDBUtility.insertWithDel(l_batoProductManagementParams);
            
            l_response = (WEB3FPTDocumentGetResponse)l_impl.execute(l_request);
            
            assertEquals("1", l_response.documentList[0].deliverFlag);
            assertEquals("000", l_response.documentList[0].documentDiv);
            assertEquals("001", l_response.documentList[0].documentCategory);
            assertEquals("0010000", l_response.documentList[0].batoProductCode);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();   
        }
    }
    
    public void testExecuteCase0012()
    {
        final String STR_METHOD_NAME = "testExecuteCase0012()";
        log.entering(STR_METHOD_NAME);
        
        WEB3FPTDocumentGetServiceImpl l_impl =
            new WEB3FPTDocumentGetServiceImpl();
        
        MOCK_MANAGER.setIsMockUsed(true);
        
        LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImplForMock);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(3338111123L));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "isDocumentDelivery",
                new Class[]{ String.class, String.class },
                Boolean.FALSE);
        
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        
        TraderParams l_traderParams = TestDBUtility.getTraderRow();
        
        InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
        institutionParams.setInstitutionId(l_mainAccountParams.getInstitutionId());
        
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        
        DocCategoryManagementParams l_docCategoryManagementParams =
            this.getDocCategoryManagementRow();
        
        DocDivManagementParams l_docDivManagementParams =
            this.getDocDivManagementRow();
        l_docDivManagementParams.setDocumentCheckDiv(WEB3DocumentCheckDivDef.FINANCIAL_PRODUCTS_EXCHANGE_LAW);
        
        BatoProductManagementParams l_batoProductManagementParams =
            this.getBatoProductManagementRow();
        l_batoProductManagementParams.setBatoProductCode("0010000");
        
        WEB3FPTDocumentGetRequest l_request = new WEB3FPTDocumentGetRequest();
        
        l_request.deliveryTarget = "0";
        
        log.debug("顧客.証券会社コード:" +  l_mainAccountParams.getInstitutionCode());
        log.debug("顧客.部店コード:" +  l_mainAccountParams.getBranchCode());
        log.debug("リクエスト.交付対象:" +  l_request.deliveryTarget);
        log.debug("書面種類管理テーブル.書面種類コード:" + l_docCategoryManagementParams.getDocumentCategory());
        log.debug("書面区分管理テーブル.書面区分:" + l_docDivManagementParams.getDocumentDiv());
        
        WEB3FPTDocumentGetResponse l_response = null;
        
        try
        {
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TestDBUtility.insertWithDel(l_traderParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(DocCategoryManagementRow.TYPE);
            TestDBUtility.insertWithDel(l_docCategoryManagementParams);
            
            TestDBUtility.deleteAll(DocDivManagementRow.TYPE);
            TestDBUtility.insertWithDel(l_docDivManagementParams);
            
            TestDBUtility.deleteAll(BatoProductManagementRow.TYPE);
            TestDBUtility.insertWithDel(l_batoProductManagementParams);
            
            l_response = (WEB3FPTDocumentGetResponse)l_impl.execute(l_request);
            
            assertEquals("0", l_response.documentList[0].deliverFlag);
            assertEquals("000", l_response.documentList[0].documentDiv);
            assertEquals("001", l_response.documentList[0].documentCategory);
            assertEquals("0010000", l_response.documentList[0].batoProductCode);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();   
        }
    }
}
@
