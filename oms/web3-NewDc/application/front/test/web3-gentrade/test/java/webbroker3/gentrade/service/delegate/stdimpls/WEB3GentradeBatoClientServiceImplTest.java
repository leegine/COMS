head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.59.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3GentradeBatoClientServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 電子鳩システム接続サービス実装クラスTest(WEB3GentradeBatoClientServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/05/26 趙林鵬(中訊) 新規作成
*/

package webbroker3.gentrade.service.delegate.stdimpls;

import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImplForMock;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.BatoBranchProductPrefParams;
import webbroker3.gentrade.data.BatoBranchProductPrefRow;
import webbroker3.gentrade.data.BatoDoctypeManagementParams;
import webbroker3.gentrade.data.BatoDoctypeManagementRow;
import webbroker3.gentrade.data.BatoFunctionPrefParams;
import webbroker3.gentrade.data.BatoFunctionPrefRow;
import webbroker3.gentrade.data.BatoInstBranchPrefParams;
import webbroker3.gentrade.data.BatoInstBranchPrefRow;
import webbroker3.gentrade.message.WEB3GentradeMultiCheckResults;
import webbroker3.gentrade.message.WEB3GentradeMultiDocCheckResultUnit;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3GentradeBatoClientServiceImplTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3GentradeBatoClientServiceImplTest.class);

    public WEB3GentradeBatoClientServiceImplTest(String arg0)
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
     * get電子鳩接続情報
     * 作成電子鳩URL
     */
    public void testBatoConnectionInfoCase0001()
    {
        final String STR_METHOD_NAME = "testBatoConnectionInfoCase0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(BatoInstBranchPrefRow.TYPE);
            BatoInstBranchPrefParams l_batoInstBranchPrefParams = 
                TestDBUtility.getBatoInstBranchPrefRow();
            l_batoInstBranchPrefParams.setUrl("https//www.abc.cn/sinocom");
            TestDBUtility.insertWithDel(l_batoInstBranchPrefParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountCode("123456");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            WEB3GentradeMainAccount l_mainAccount = 
                new WEB3GentradeMainAccount(l_mainAccountParams.getAccountId());
            
            WEB3GentradeBatoClientServiceImpl l_impl = new WEB3GentradeBatoClientServiceImpl();
            
            String l_strHashValue = "HASHSTRING=" + l_impl.getHashValue(
                    l_mainAccount,
                    l_batoInstBranchPrefParams.getHashField1(),
                    l_batoInstBranchPrefParams.getHashField2());
            
            String l_strBatoConnectionInfo = l_impl.getBatoConnectionInfo(l_mainAccount);

            assertEquals("https//www.abc.cn/sinocom?COMPCODE=0D&" + 
                    "BRANCODE=381&USERID=123456&" + l_strHashValue, 
                l_strBatoConnectionInfo);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME); 
    }
    
    /**
     * validate複数銘柄目論見書閲覧
     * 正常結束
     */
    public void testValidateMultiProspectusCase0001()
    {
        final String STR_METHOD_NAME = "testValidateMultiProspectusCase0001()";
        log.entering(STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},new Long(101001010010L));

            LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImplForMock);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
   
            TestDBUtility.deleteAll(BatoInstBranchPrefRow.TYPE);
            BatoInstBranchPrefParams l_batoInstBranchPrefParams = 
                TestDBUtility.getBatoInstBranchPrefRow();
            l_batoInstBranchPrefParams.setUrl("https//www.com.jp/denshibatososo");
            TestDBUtility.insertWithDel(l_batoInstBranchPrefParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setAccountCode("123456");
            TestDBUtility.insertWithDel(l_mainAccountParams);
   
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(BatoFunctionPrefRow.TYPE);
            BatoFunctionPrefParams l_batoFunctionPrefParams = TestDBUtility.getBatoFunctionPrefRow();
            TestDBUtility.insertWithDel(l_batoFunctionPrefParams);
            
            TestDBUtility.deleteAll(BatoBranchProductPrefRow.TYPE);
            BatoBranchProductPrefParams l_batoBranchProductPrefParams =
                TestDBUtility.getBatoBranchProductPrefRow();
            l_batoBranchProductPrefParams.setOperatorInputFlag("0");
            TestDBUtility.insertWithDel(l_batoBranchProductPrefParams);
   
            BatoBranchProductPrefParams l_batoBranchProductPrefParams1 =
                TestDBUtility.getBatoBranchProductPrefRow();
            l_batoBranchProductPrefParams1.setProductCode("124");
            TestDBUtility.insertWithDel(l_batoBranchProductPrefParams1);
            
            BatoBranchProductPrefParams l_batoBranchProductPrefParams2 =
                TestDBUtility.getBatoBranchProductPrefRow();
            l_batoBranchProductPrefParams2.setProductCode("125");
            TestDBUtility.insertWithDel(l_batoBranchProductPrefParams2);
   
            TestDBUtility.deleteAll(BatoDoctypeManagementRow.TYPE);
            BatoDoctypeManagementParams l_batoDoctypeManagementParams =
                TestDBUtility.getBatoDoctypeManagementRow();
            TestDBUtility.insertWithDel(l_batoDoctypeManagementParams);
            
            BatoDoctypeManagementParams l_batoDoctypeManagementParams1 =
                TestDBUtility.getBatoDoctypeManagementRow();
            l_batoDoctypeManagementParams1.setProductCode("124");
            l_batoDoctypeManagementParams1.setTypeCode("222");
            TestDBUtility.insertWithDel(l_batoDoctypeManagementParams1);
            
            BatoDoctypeManagementParams l_batoDoctypeManagementParams2 =
                TestDBUtility.getBatoDoctypeManagementRow();
            l_batoDoctypeManagementParams2.setProductCode("125");
            l_batoDoctypeManagementParams2.setTypeCode("333");
            TestDBUtility.insertWithDel(l_batoDoctypeManagementParams2);
   
            WEB3GentradeMultiDocCheckResultUnit[] l_multiDocCheckResultUnit =
                new WEB3GentradeMultiDocCheckResultUnit[3];
            l_multiDocCheckResultUnit[0] = new WEB3GentradeMultiDocCheckResultUnit();
            l_multiDocCheckResultUnit[0].productCode = "123";
            l_multiDocCheckResultUnit[0].typeCode = "111";
            l_multiDocCheckResultUnit[1] = new WEB3GentradeMultiDocCheckResultUnit();
            l_multiDocCheckResultUnit[1].productCode = "124";
            l_multiDocCheckResultUnit[1].typeCode = "222";
            l_multiDocCheckResultUnit[2] = new WEB3GentradeMultiDocCheckResultUnit();
            l_multiDocCheckResultUnit[2].productCode = "125";
            l_multiDocCheckResultUnit[2].typeCode = "333";

            boolean l_blnIsCheckFlag = true;

            WEB3GentradeBatoClientServiceImplForTest l_impl = new WEB3GentradeBatoClientServiceImplForTest();

            WEB3GentradeMainAccount l_mainAccount = 
            new WEB3GentradeMainAccount(l_mainAccountParams.getAccountId());
            String l_strHashValue = l_impl.getHashValue(
                l_mainAccount,
                l_batoInstBranchPrefParams.getHashField1(),
                l_batoInstBranchPrefParams.getHashField2());
            
            WEB3GentradeMultiCheckResults l_multiCheckResults =
                l_impl.validateMultiProspectus(l_multiDocCheckResultUnit, l_blnIsCheckFlag);
            
            assertEquals(l_multiCheckResults.checkResult, l_multiDocCheckResultUnit);
            assertEquals("https//www.com.jp/denshibatososo", l_multiCheckResults.url);
            assertEquals(l_strHashValue, l_multiCheckResults.hashValue);
            assertFalse(l_multiCheckResults.batoFailureFlag);
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /**
     * validate複数銘柄目論見書閲覧
     * 会社部店別プリファ@レンス.システム障害フラグ == ”障害中” の場合
     * 部店商品別プリファ@レンス.障害時注文可否フラグ == ”注文不可”の場合例外をスローする。
     */
    public void testValidateMultiProspectusCase0002()
    {
        final String STR_METHOD_NAME = "testValidateMultiProspectusCase0002()";
        log.entering(STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},new Long(101001010010L));

                
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
                  new Long(1001));

            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            l_traderParams.setTraderId(1001);
            TestDBUtility.insertWithDel(l_traderParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(BatoInstBranchPrefRow.TYPE);
            BatoInstBranchPrefParams l_batoInstBranchPrefParams = 
                TestDBUtility.getBatoInstBranchPrefRow();
            l_batoInstBranchPrefParams.setUrl("https//www.com.jp/denshibatososo");
            l_batoInstBranchPrefParams.setSystemFailureFlag("1");
            TestDBUtility.insertWithDel(l_batoInstBranchPrefParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setAccountCode("123456");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(BatoFunctionPrefRow.TYPE);
            BatoFunctionPrefParams l_batoFunctionPrefParams = TestDBUtility.getBatoFunctionPrefRow();
            TestDBUtility.insertWithDel(l_batoFunctionPrefParams);
            
            TestDBUtility.deleteAll(BatoBranchProductPrefRow.TYPE);
            BatoBranchProductPrefParams l_batoBranchProductPrefParams =
                TestDBUtility.getBatoBranchProductPrefRow();
            l_batoBranchProductPrefParams.setOrderAtSystemFailureFlag("0");
            TestDBUtility.insertWithDel(l_batoBranchProductPrefParams);

            TestDBUtility.deleteAll(BatoDoctypeManagementRow.TYPE);
            BatoDoctypeManagementParams l_batoDoctypeManagementParams =
                TestDBUtility.getBatoDoctypeManagementRow();
            TestDBUtility.insertWithDel(l_batoDoctypeManagementParams);

            WEB3GentradeMultiDocCheckResultUnit[] l_multiDocCheckResultUnit =
                new WEB3GentradeMultiDocCheckResultUnit[1];
            l_multiDocCheckResultUnit[0] = new WEB3GentradeMultiDocCheckResultUnit();
            l_multiDocCheckResultUnit[0].productCode = "123";
            l_multiDocCheckResultUnit[0].typeCode = "111";
            
            boolean l_blnIsCheckFlag = true;

            WEB3GentradeBatoClientServiceImplForTest l_impl = new WEB3GentradeBatoClientServiceImplForTest();

            
            l_impl.validateMultiProspectus(l_multiDocCheckResultUnit, l_blnIsCheckFlag);
            fail();

        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_01984);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
            log.exiting(TEST_END + STR_METHOD_NAME);

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    /**
     * validate複数銘柄目論見書閲覧
     * 会社部店別プリファ@レンス.システム障害フラグ == ”障害中” の場合
     * get代理入力者()の戻り値 != null and
       部店商品別プリファ@レンス.代理入力可否フラグ == ”代理入力不可”
       例外をスローする。
     */
    public void testValidateMultiProspectusCase0003()
    {
        final String STR_METHOD_NAME = "testValidateMultiProspectusCase0003()";
        log.entering(STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},new Long(101001010010L));

                
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
                  new Long(1001));

            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            l_traderParams.setLoginId(1001);
            TestDBUtility.insertWithDel(l_traderParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(BatoInstBranchPrefRow.TYPE);
            BatoInstBranchPrefParams l_batoInstBranchPrefParams = 
                TestDBUtility.getBatoInstBranchPrefRow();
            l_batoInstBranchPrefParams.setUrl("https//www.com.jp/denshibatososo");
            l_batoInstBranchPrefParams.setSystemFailureFlag("1");
            TestDBUtility.insertWithDel(l_batoInstBranchPrefParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setAccountCode("123456");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(BatoFunctionPrefRow.TYPE);
            BatoFunctionPrefParams l_batoFunctionPrefParams = TestDBUtility.getBatoFunctionPrefRow();
            TestDBUtility.insertWithDel(l_batoFunctionPrefParams);
            
            TestDBUtility.deleteAll(BatoBranchProductPrefRow.TYPE);
            BatoBranchProductPrefParams l_batoBranchProductPrefParams =
                TestDBUtility.getBatoBranchProductPrefRow();
            l_batoBranchProductPrefParams.setOrderAtSystemFailureFlag("1");
            l_batoBranchProductPrefParams.setOperatorInputFlag("0");
            TestDBUtility.insertWithDel(l_batoBranchProductPrefParams);

            TestDBUtility.deleteAll(BatoDoctypeManagementRow.TYPE);
            BatoDoctypeManagementParams l_batoDoctypeManagementParams =
                TestDBUtility.getBatoDoctypeManagementRow();
            TestDBUtility.insertWithDel(l_batoDoctypeManagementParams);

            WEB3GentradeMultiDocCheckResultUnit[] l_multiDocCheckResultUnit =
                new WEB3GentradeMultiDocCheckResultUnit[1];
            l_multiDocCheckResultUnit[0] = new WEB3GentradeMultiDocCheckResultUnit();
            l_multiDocCheckResultUnit[0].productCode = "123";
            l_multiDocCheckResultUnit[0].typeCode = "111";
            
            boolean l_blnIsCheckFlag = true;

            WEB3GentradeBatoClientServiceImplForTest l_impl = new WEB3GentradeBatoClientServiceImplForTest();

            
            l_impl.validateMultiProspectus(l_multiDocCheckResultUnit, l_blnIsCheckFlag);
            fail();

        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_01985);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
            log.exiting(TEST_END + STR_METHOD_NAME);

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    /**
     * validate複数銘柄目論見書閲覧
     * 会社部店別プリファ@レンス.システム障害フラグ == ”障害中” の場合
     * 正常結束
     */
    public void testValidateMultiProspectusCase0004()
    {
        final String STR_METHOD_NAME = "testValidateMultiProspectusCase0004()";
        log.entering(STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},new Long(101001010010L));

                
            LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImplForMock);

            TestDBUtility.deleteAll(TraderRow.TYPE);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(BatoInstBranchPrefRow.TYPE);
            BatoInstBranchPrefParams l_batoInstBranchPrefParams = 
                TestDBUtility.getBatoInstBranchPrefRow();
            l_batoInstBranchPrefParams.setUrl("https//www.com.jp/denshibatososo");
            l_batoInstBranchPrefParams.setSystemFailureFlag("1");
            TestDBUtility.insertWithDel(l_batoInstBranchPrefParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setAccountCode("123456");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(BatoFunctionPrefRow.TYPE);
            BatoFunctionPrefParams l_batoFunctionPrefParams = TestDBUtility.getBatoFunctionPrefRow();
            TestDBUtility.insertWithDel(l_batoFunctionPrefParams);
            
            TestDBUtility.deleteAll(BatoBranchProductPrefRow.TYPE);
            BatoBranchProductPrefParams l_batoBranchProductPrefParams =
                TestDBUtility.getBatoBranchProductPrefRow();
            l_batoBranchProductPrefParams.setOrderAtSystemFailureFlag("1");
            l_batoBranchProductPrefParams.setOperatorInputFlag("0");
            TestDBUtility.insertWithDel(l_batoBranchProductPrefParams);

            TestDBUtility.deleteAll(BatoDoctypeManagementRow.TYPE);
            BatoDoctypeManagementParams l_batoDoctypeManagementParams =
                TestDBUtility.getBatoDoctypeManagementRow();
            TestDBUtility.insertWithDel(l_batoDoctypeManagementParams);

            WEB3GentradeMultiDocCheckResultUnit[] l_multiDocCheckResultUnit =
                new WEB3GentradeMultiDocCheckResultUnit[1];
            l_multiDocCheckResultUnit[0] = new WEB3GentradeMultiDocCheckResultUnit();
            l_multiDocCheckResultUnit[0].productCode = "123";
            l_multiDocCheckResultUnit[0].typeCode = "111";
            
            boolean l_blnIsCheckFlag = true;

            WEB3GentradeBatoClientServiceImplForTest l_impl = new WEB3GentradeBatoClientServiceImplForTest();

            
            WEB3GentradeMultiCheckResults l_multiCheckResults =
                l_impl.validateMultiProspectus(l_multiDocCheckResultUnit, l_blnIsCheckFlag);
            assertNull(l_multiCheckResults.checkResult);
            assertNull(l_multiCheckResults.url);
            assertNull(l_multiCheckResults.hashValue);
            assertTrue(l_multiCheckResults.batoFailureFlag);
            
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
      /**
      * validate複数銘柄目論見書閲覧
      * カンマ区切りで区切られた値が”履歴無し”の場合
       get代理入力者()の戻り値 != null and
       取得した代理入力可否フラグ == ”代理入力不可” and
       引数.代理入力不可時チェックフラグ==trueの場合、例外をスローする。
      */
     public void testValidateMultiProspectusCase0005()
     {
         final String STR_METHOD_NAME = "testValidateMultiProspectusCase0005()";
         log.entering(STR_METHOD_NAME);
         
         TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
         try
         {
             TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                     "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                     "getAccountId",
                     new Class[] {},new Long(101001010010L));
    
                 
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
                     new Long(1001));

               TestDBUtility.deleteAll(TraderRow.TYPE);
               TraderParams l_traderParams = TestDBUtility.getTraderRow();
               l_traderParams.setLoginId(1001);
               TestDBUtility.insertWithDel(l_traderParams);

             TestDBUtility.deleteAll(SubAccountRow.TYPE);
             SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
             l_subAccountParams.setAccountId(101001010010L);
             l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
             TestDBUtility.insertWithDel(l_subAccountParams);
    
             TestDBUtility.deleteAll(BatoInstBranchPrefRow.TYPE);
             BatoInstBranchPrefParams l_batoInstBranchPrefParams = 
                 TestDBUtility.getBatoInstBranchPrefRow();
             l_batoInstBranchPrefParams.setUrl("https//www.com.jp/denshibatososo");
             TestDBUtility.insertWithDel(l_batoInstBranchPrefParams);
             
             TestDBUtility.deleteAll(MainAccountRow.TYPE);
             MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
             l_mainAccountParams.setAccountId(101001010010L);
             l_mainAccountParams.setAccountCode("123456");
             TestDBUtility.insertWithDel(l_mainAccountParams);
    
             TestDBUtility.deleteAll(InstitutionRow.TYPE);
             InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
             TestDBUtility.insertWithDel(l_institutionParams);
             
             TestDBUtility.deleteAll(BranchRow.TYPE);
             BranchParams l_branchParams = TestDBUtility.getBranchRow();
             TestDBUtility.insertWithDel(l_branchParams);
             
             TestDBUtility.deleteAll(BatoFunctionPrefRow.TYPE);
             BatoFunctionPrefParams l_batoFunctionPrefParams = TestDBUtility.getBatoFunctionPrefRow();
             TestDBUtility.insertWithDel(l_batoFunctionPrefParams);
             
             TestDBUtility.deleteAll(BatoBranchProductPrefRow.TYPE);
             BatoBranchProductPrefParams l_batoBranchProductPrefParams =
                 TestDBUtility.getBatoBranchProductPrefRow();
             l_batoBranchProductPrefParams.setOperatorInputFlag("0");
             TestDBUtility.insertWithDel(l_batoBranchProductPrefParams);
    
             BatoBranchProductPrefParams l_batoBranchProductPrefParams1 =
                 TestDBUtility.getBatoBranchProductPrefRow();
             l_batoBranchProductPrefParams1.setProductCode("124");
             TestDBUtility.insertWithDel(l_batoBranchProductPrefParams1);
             
             BatoBranchProductPrefParams l_batoBranchProductPrefParams2 =
                 TestDBUtility.getBatoBranchProductPrefRow();
             l_batoBranchProductPrefParams2.setProductCode("125");
             TestDBUtility.insertWithDel(l_batoBranchProductPrefParams2);
    
             TestDBUtility.deleteAll(BatoDoctypeManagementRow.TYPE);
             BatoDoctypeManagementParams l_batoDoctypeManagementParams =
                 TestDBUtility.getBatoDoctypeManagementRow();
             TestDBUtility.insertWithDel(l_batoDoctypeManagementParams);
             
             BatoDoctypeManagementParams l_batoDoctypeManagementParams1 =
                 TestDBUtility.getBatoDoctypeManagementRow();
             l_batoDoctypeManagementParams1.setProductCode("124");
             l_batoDoctypeManagementParams1.setTypeCode("222");
             TestDBUtility.insertWithDel(l_batoDoctypeManagementParams1);
             
             BatoDoctypeManagementParams l_batoDoctypeManagementParams2 =
                 TestDBUtility.getBatoDoctypeManagementRow();
             l_batoDoctypeManagementParams2.setProductCode("125");
             l_batoDoctypeManagementParams2.setTypeCode("333");
             TestDBUtility.insertWithDel(l_batoDoctypeManagementParams2);
    
             WEB3GentradeMultiDocCheckResultUnit[] l_multiDocCheckResultUnit =
                 new WEB3GentradeMultiDocCheckResultUnit[3];
             l_multiDocCheckResultUnit[0] = new WEB3GentradeMultiDocCheckResultUnit();
             l_multiDocCheckResultUnit[0].productCode = "123";
             l_multiDocCheckResultUnit[0].typeCode = "111";
             l_multiDocCheckResultUnit[1] = new WEB3GentradeMultiDocCheckResultUnit();
             l_multiDocCheckResultUnit[1].productCode = "124";
             l_multiDocCheckResultUnit[1].typeCode = "222";
             l_multiDocCheckResultUnit[2] = new WEB3GentradeMultiDocCheckResultUnit();
             l_multiDocCheckResultUnit[2].productCode = "125";
             l_multiDocCheckResultUnit[2].typeCode = "333";

             boolean l_blnIsCheckFlag = true;

             WEB3GentradeBatoClientServiceImplForTest l_impl = new WEB3GentradeBatoClientServiceImplForTest();

             l_impl.validateMultiProspectus(l_multiDocCheckResultUnit, l_blnIsCheckFlag);
             fail();
    
         }
         catch(WEB3BaseException l_ex)
         {
             log.debug(STR_METHOD_NAME, l_ex);
             assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_01988);
             log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
             log.exiting(TEST_END + STR_METHOD_NAME);

         }
         catch (Exception l_ex)
         {
             log.error(ERROR + l_ex.getMessage(), l_ex);
             fail();
         }
     }
     
     /**
      * validate複数銘柄目論見書閲覧
      * カンマ区切りで区切られた値が”履歴あり”、”履歴無し”、”履歴管理無し”以外なら、例外をスローする。
      */
     public void testValidateMultiProspectusCase0006()
     {
         final String STR_METHOD_NAME = "testValidateMultiProspectusCase0006()";
         log.entering(STR_METHOD_NAME);
         
         TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
         try
         {
             TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                     "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                     "getAccountId",
                     new Class[] {},new Long(101001010010L));
    
                 
             LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
             
             TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                 "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                 "getLoginInfo",
                 new Class[] {},
                 l_loginInfoImplForMock);

             TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                     "webbroker3.gentrade.service.delegate.stdimpls.WEB3GentradeBatoClientServiceImpl",
                     "getHashValue",
                     new Class[] {MainAccount.class, String.class, String.class},
                     "3,2,1");

             TestDBUtility.deleteAll(SubAccountRow.TYPE);
             SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
             l_subAccountParams.setAccountId(101001010010L);
             l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
             TestDBUtility.insertWithDel(l_subAccountParams);
    
             TestDBUtility.deleteAll(BatoInstBranchPrefRow.TYPE);
             BatoInstBranchPrefParams l_batoInstBranchPrefParams = 
                 TestDBUtility.getBatoInstBranchPrefRow();
             l_batoInstBranchPrefParams.setUrl("https//www.com.jp/denshibatososo");
             TestDBUtility.insertWithDel(l_batoInstBranchPrefParams);
             
             TestDBUtility.deleteAll(MainAccountRow.TYPE);
             MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
             l_mainAccountParams.setAccountId(101001010010L);
             l_mainAccountParams.setAccountCode("123456");
             TestDBUtility.insertWithDel(l_mainAccountParams);
    
             TestDBUtility.deleteAll(InstitutionRow.TYPE);
             InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
             TestDBUtility.insertWithDel(l_institutionParams);
             
             TestDBUtility.deleteAll(BranchRow.TYPE);
             BranchParams l_branchParams = TestDBUtility.getBranchRow();
             TestDBUtility.insertWithDel(l_branchParams);
             
             TestDBUtility.deleteAll(BatoFunctionPrefRow.TYPE);
             BatoFunctionPrefParams l_batoFunctionPrefParams = TestDBUtility.getBatoFunctionPrefRow();
             l_batoFunctionPrefParams.setServiceName("1");
             TestDBUtility.insertWithDel(l_batoFunctionPrefParams);
             
             TestDBUtility.deleteAll(BatoBranchProductPrefRow.TYPE);
             BatoBranchProductPrefParams l_batoBranchProductPrefParams =
                 TestDBUtility.getBatoBranchProductPrefRow();
             l_batoBranchProductPrefParams.setOperatorInputFlag("0");
             TestDBUtility.insertWithDel(l_batoBranchProductPrefParams);
    
             BatoBranchProductPrefParams l_batoBranchProductPrefParams1 =
                 TestDBUtility.getBatoBranchProductPrefRow();
             l_batoBranchProductPrefParams1.setProductCode("124");
             TestDBUtility.insertWithDel(l_batoBranchProductPrefParams1);
             
             BatoBranchProductPrefParams l_batoBranchProductPrefParams2 =
                 TestDBUtility.getBatoBranchProductPrefRow();
             l_batoBranchProductPrefParams2.setProductCode("125");
             TestDBUtility.insertWithDel(l_batoBranchProductPrefParams2);
    
             TestDBUtility.deleteAll(BatoDoctypeManagementRow.TYPE);
             BatoDoctypeManagementParams l_batoDoctypeManagementParams =
                 TestDBUtility.getBatoDoctypeManagementRow();
             TestDBUtility.insertWithDel(l_batoDoctypeManagementParams);
             
             BatoDoctypeManagementParams l_batoDoctypeManagementParams1 =
                 TestDBUtility.getBatoDoctypeManagementRow();
             l_batoDoctypeManagementParams1.setProductCode("124");
             l_batoDoctypeManagementParams1.setTypeCode("222");
             TestDBUtility.insertWithDel(l_batoDoctypeManagementParams1);
             
             BatoDoctypeManagementParams l_batoDoctypeManagementParams2 =
                 TestDBUtility.getBatoDoctypeManagementRow();
             l_batoDoctypeManagementParams2.setProductCode("125");
             l_batoDoctypeManagementParams2.setTypeCode("333");
             TestDBUtility.insertWithDel(l_batoDoctypeManagementParams2);
    
             WEB3GentradeMultiDocCheckResultUnit[] l_multiDocCheckResultUnit =
                 new WEB3GentradeMultiDocCheckResultUnit[3];
             l_multiDocCheckResultUnit[0] = new WEB3GentradeMultiDocCheckResultUnit();
             l_multiDocCheckResultUnit[0].productCode = "123";
             l_multiDocCheckResultUnit[0].typeCode = "111";
             l_multiDocCheckResultUnit[1] = new WEB3GentradeMultiDocCheckResultUnit();
             l_multiDocCheckResultUnit[1].productCode = "124";
             l_multiDocCheckResultUnit[1].typeCode = "222";
             l_multiDocCheckResultUnit[2] = new WEB3GentradeMultiDocCheckResultUnit();
             l_multiDocCheckResultUnit[2].productCode = "125";
             l_multiDocCheckResultUnit[2].typeCode = "333";

             boolean l_blnIsCheckFlag = true;

             WEB3GentradeBatoClientServiceImplForTest l_impl = new WEB3GentradeBatoClientServiceImplForTest();

             l_impl.validateMultiProspectus(l_multiDocCheckResultUnit, l_blnIsCheckFlag);
             fail();
    
         }
         catch(WEB3BaseException l_ex)
         {
             log.debug(STR_METHOD_NAME, l_ex);
             assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_01959);
             log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
             log.exiting(TEST_END + STR_METHOD_NAME);

         }
         catch (Exception l_ex)
         {
             log.error(ERROR + l_ex.getMessage(), l_ex);
             fail();
         }
     }
    
    public class WEB3GentradeBatoClientServiceImplForTest extends WEB3GentradeBatoClientServiceImpl
    {
        protected void validateConnectionTime() throws WEB3BaseException
        {
            return;
        }
        
        protected String invokeBatoSystem(
            Object[] l_paramList, String l_url, 
            BatoFunctionPrefParams l_BatoFunctionPrefParams) throws WEB3BaseException 
        {
            if (l_BatoFunctionPrefParams.getServiceName().equals("1"))
            {
                return "3,2,0";
            }
            return "1,2,0";
        
        }
    }  
}
@
