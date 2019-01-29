head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.39.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FXAccOpenInputServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.aio.service.delegate.stdimpls;

import java.util.ArrayList;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;

import test.util.TestDBUtility;

import webbroker3.aio.data.CompFxConditionParams;
import webbroker3.aio.data.CompFxConditionRow;
import webbroker3.aio.data.FxAccountParams;
import webbroker3.aio.data.FxAccountRow;
import webbroker3.aio.message.WEB3FXAccOpenInputRequest;
import webbroker3.aio.message.WEB3FXAccOpenInputResponse;
import webbroker3.aio.message.WEB3FXAccOpenTradeAgreementRequest;
import webbroker3.aio.message.WEB3FXAccOpenTradeAgreementResponse;
import webbroker3.aio.message.WEB3FXTradeAgreementUnit;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.data.BranchPreferencesParams;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.gentrade.data.InstitutionPreferencesParams;
import webbroker3.gentrade.data.QuestionParams;
import webbroker3.gentrade.data.QuestionRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3FXAccOpenInputServiceImplTest extends TestBaseForMock
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FXAccOpenInputServiceImplTest.class);  

    WEB3FXAccOpenInputServiceImpl l_impl;
    public WEB3FXAccOpenInputServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        l_impl = new WEB3FXAccOpenInputServiceImpl();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testGetTradeAgreementScreen_Case001()
    {
        final String STR_METHOD_NAME = "testGetTradeAgreementScreen_Case001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_processors = Processors.getDefaultProcessor();
            WEB3FXAccOpenTradeAgreementRequest l_request =
                new WEB3FXAccOpenTradeAgreementRequest();
            l_request.fxSystemCode = "07";
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getAccountId",
                new Class[] {},
                new Long(123456));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager", 
                "validateOrder",
                new Class[] {SubAccount.class},
                null);
            
            //MainAccountParams
            l_processors.doDeleteAllQuery(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123456);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setInstitutionId(33);
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setBranchId(123);
            l_mainAccountParams.setAccountType(MainAccountTypeEnum.CORPORATE_ACCOUNT);
            l_processors.doInsertQuery(l_mainAccountParams);

            //333812512246
            l_mainAccountParams.setAccountCode("jiddk");
            l_mainAccountParams.setAccountId(333812512246L);
            l_processors.doInsertQuery(l_mainAccountParams);
            
            //SubAccountParams
            l_processors.doDeleteAllQuery(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(123456);
            l_subAccountParams.setInstitutionCode("0D");
            l_subAccountParams.setInstitutionId(33);
            l_subAccountParams.setBranchId(123);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_processors.doInsertQuery(l_subAccountParams);
            
            l_subAccountParams.setAccountId(333812512246L);
            l_processors.doInsertQuery(l_subAccountParams);
            
            //InstitutionParams
            l_processors.doDeleteAllQuery(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33);
            l_processors.doInsertQuery(l_institutionParams);
            
            //BranchParams
            l_processors.doDeleteAllQuery(BranchParams.TYPE);
            BranchParams l_branchParams =
                TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("624");
            l_branchParams.setBranchId(123);
            l_processors.doInsertQuery(l_branchParams);
            
            //CompFxConditionRow
            l_processors.doDeleteAllQuery(CompFxConditionRow.TYPE);
            CompFxConditionParams l_compFxConditionParams =
                TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode("0D");
            l_compFxConditionParams.setBranchCode("624");
            l_compFxConditionParams.setFxSystemCode("07");
            l_processors.doInsertQuery(l_compFxConditionParams);

            //FxAccountRow
            l_processors.doDeleteAllQuery(FxAccountRow.TYPE);
            
            //QuestionParams
            l_processors.doDeleteAllQuery(QuestionRow.TYPE);
            QuestionParams l_questionParams = this.getQuestionParams();
            l_questionParams.setQuestionDiv("07");
            l_questionParams.setQuestionNo("13");
            l_processors.doInsertQuery(l_questionParams);

            //222
            l_questionParams.setQuestionNo("11");
            l_processors.doInsertQuery(l_questionParams);

            WEB3FXAccOpenTradeAgreementResponse l_response =
                l_impl.getTradeAgreementScreen(l_request);
            assertEquals(2, l_response.fxTradeAgreementList.length);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetInputScreen_Case001()
    {
        final String STR_METHOD_NAME = "testGetInputScreen_Case001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_processors = Processors.getDefaultProcessor();
            WEB3FXAccOpenInputRequest l_request =
                new WEB3FXAccOpenInputRequest();
            l_request.fxSystemCode = "07";
            
            WEB3FXTradeAgreementUnit[] fxTradeAgreementList =
                new WEB3FXTradeAgreementUnit[2];
            fxTradeAgreementList[0] = new WEB3FXTradeAgreementUnit();
            fxTradeAgreementList[0].questionAnswer = "1";
            fxTradeAgreementList[0].questionNumber = "101";
            fxTradeAgreementList[1] = new WEB3FXTradeAgreementUnit();
            fxTradeAgreementList[1].questionAnswer = "1";
            fxTradeAgreementList[1].questionNumber = "102";
            
            l_request.fxTradeAgreementList = fxTradeAgreementList;
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getAccountId",
                new Class[] {},
                new Long(123456));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager", 
                "validateOrder",
                new Class[] {SubAccount.class},
                null);
            
            //MainAccountParams
            l_processors.doDeleteAllQuery(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123456);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setInstitutionId(33);
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setBranchId(123);
            l_mainAccountParams.setAccountType(MainAccountTypeEnum.CORPORATE_ACCOUNT);
            l_processors.doInsertQuery(l_mainAccountParams);

            //333812512246
            l_mainAccountParams.setAccountCode("jiddk");
            l_mainAccountParams.setAccountId(333812512246L);
            l_processors.doInsertQuery(l_mainAccountParams);
            
            //SubAccountParams
            l_processors.doDeleteAllQuery(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(123456);
            l_subAccountParams.setInstitutionCode("0D");
            l_subAccountParams.setInstitutionId(33);
            l_subAccountParams.setBranchId(123);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_processors.doInsertQuery(l_subAccountParams);
            
            l_subAccountParams.setAccountId(333812512246L);
            l_processors.doInsertQuery(l_subAccountParams);
            
            //InstitutionPreferencesRow
            l_processors.doDeleteAllQuery(InstitutionPreferencesParams.TYPE);
            InstitutionPreferencesParams l_institutionPreferencesParams =
                getInstitutionPreferencesParams();
            l_institutionPreferencesParams.setInstitutionId(33);
            l_institutionPreferencesParams.setName("fx.agreement.question.check");
            l_institutionPreferencesParams.setNameSerialNo(1);
            l_processors.doInsertQuery(l_institutionPreferencesParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager", 
                "validateOrder",
                new Class[] {SubAccount.class},
                null);
                
            //CompFxConditionRow
            l_processors.doDeleteAllQuery(CompFxConditionRow.TYPE);
            CompFxConditionParams l_compFxConditionParams =
                TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode("0D");
            l_compFxConditionParams.setBranchCode("624");
            l_compFxConditionParams.setFxSystemCode("07");
            l_processors.doInsertQuery(l_compFxConditionParams);
            
            WEB3FXAccOpenInputResponse l_response = l_impl.getInputScreen(l_request);
            assertEquals("t4@@dir.co.jp", l_response.mailAddress);
            assertTrue(!l_response.gftAccOpenFlag);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
        
    }
    
    public void testGetInputScreen_Case002()
    {
        final String STR_METHOD_NAME = "testGetInputScreen_Case002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_processors = Processors.getDefaultProcessor();
            WEB3FXAccOpenInputRequest l_request =
                new WEB3FXAccOpenInputRequest();
            l_request.fxSystemCode = "07";
            
            WEB3FXTradeAgreementUnit[] fxTradeAgreementList =
                new WEB3FXTradeAgreementUnit[2];
            fxTradeAgreementList[0] = new WEB3FXTradeAgreementUnit();
            fxTradeAgreementList[0].questionAnswer = "1";
            fxTradeAgreementList[0].questionNumber = "101";
            fxTradeAgreementList[1] = new WEB3FXTradeAgreementUnit();
            fxTradeAgreementList[1].questionAnswer = "0";
            fxTradeAgreementList[1].questionNumber = "102";
            
            l_request.fxTradeAgreementList = fxTradeAgreementList;
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getAccountId",
                new Class[] {},
                new Long(123456));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager", 
                "validateOrder",
                new Class[] {SubAccount.class},
                null);
            
            //MainAccountParams
            l_processors.doDeleteAllQuery(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123456);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setInstitutionId(33);
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setBranchId(123);
            l_mainAccountParams.setAccountType(MainAccountTypeEnum.CORPORATE_ACCOUNT);
            l_processors.doInsertQuery(l_mainAccountParams);

            //333812512246
            l_mainAccountParams.setAccountCode("jiddk");
            l_mainAccountParams.setAccountId(333812512246L);
            l_processors.doInsertQuery(l_mainAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(123);
            TestDBUtility.insertWithDel(l_branchParams);

            //SubAccountParams
            l_processors.doDeleteAllQuery(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(123456);
            l_subAccountParams.setInstitutionCode("0D");
            l_subAccountParams.setInstitutionId(33);
            l_subAccountParams.setBranchId(123);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_processors.doInsertQuery(l_subAccountParams);
            
            l_subAccountParams.setAccountId(333812512246L);
            l_processors.doInsertQuery(l_subAccountParams);
            
            //InstitutionPreferencesRow
            l_processors.doDeleteAllQuery(InstitutionPreferencesParams.TYPE);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager", 
                "validateOrder",
                new Class[] {SubAccount.class},
                null);
                
            //CompFxConditionRow
            l_processors.doDeleteAllQuery(CompFxConditionRow.TYPE);
            CompFxConditionParams l_compFxConditionParams =
                TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode("0D");
            l_compFxConditionParams.setBranchCode("381");
            l_compFxConditionParams.setFxSystemCode("07");
            l_processors.doInsertQuery(l_compFxConditionParams);

            WEB3FXAccOpenInputResponse l_response = 
                (WEB3FXAccOpenInputResponse)l_request.createResponse();

            l_response = l_impl.getInputScreen(l_request);

            assertEquals("t4@@dir.co.jp", l_response.mailAddress);
            assertFalse(l_response.gftAccOpenFlag);
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01796, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
        
    }

    /**
     * validate( )正常
     * get補助口座(補助口座タイプ : SubAccountTypeEnum)正常
     * get会社別FXシステム条件(String, String, String)
     * throw NotFoundException
     */
    public void testGetInputScreen_C0003()
    {
        final String STR_METHOD_NAME = "testGetInputScreen_C0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3FXTradeAgreementUnit[] l_fXTradeAgreementUnits =
                new WEB3FXTradeAgreementUnit[1];
            WEB3FXTradeAgreementUnit l_fXTradeAgreementUnit =
                new WEB3FXTradeAgreementUnit();
            l_fXTradeAgreementUnit.questionNumber = "10";
            l_fXTradeAgreementUnit.questionAnswer = "1";

            l_fXTradeAgreementUnits[0] = l_fXTradeAgreementUnit;
            WEB3FXAccOpenInputRequest l_request =
                new WEB3FXAccOpenInputRequest();
            l_request.fxTradeAgreementList = l_fXTradeAgreementUnits;
            l_request.batoCheckFlag = false;
            l_request.fxSystemCode= "02";

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(123));

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setInstitutionId(100);
            l_subAccountParams.setBranchId(101);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(100);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(101);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setAccountCode("2");
            l_mainAccountParams.setBranchId(101);
            TestDBUtility.insertWithDel(l_mainAccountParams);


            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            CompFxConditionParams l_compFxConditionParams =
                TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode("0D");
            l_compFxConditionParams.setBranchCode("381");
            l_compFxConditionParams.setFxSystemCode("01");
            l_compFxConditionParams.setFxSystemDiv("2");
            TestDBUtility.insertWithDel(l_compFxConditionParams);

            l_impl.getInputScreen(l_request);

            fail();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * validate( )正常
     * get補助口座(補助口座タイプ : SubAccountTypeEnum)正常
     * get会社別FXシステム条件()の戻り値.質問同意チェック実施区分==1：チェックするの場合
     * FX取引同意質問情報.質問回答≠”1：同意”の場合
     */
    public void testGetInputScreen_C0004()
    {
        final String STR_METHOD_NAME = "testGetInputScreen_C0004()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3FXTradeAgreementUnit[] l_fXTradeAgreementUnits =
                new WEB3FXTradeAgreementUnit[1];
            WEB3FXTradeAgreementUnit l_fXTradeAgreementUnit =
                new WEB3FXTradeAgreementUnit();
            l_fXTradeAgreementUnit.questionNumber = "10";
            l_fXTradeAgreementUnit.questionAnswer = "2";

            l_fXTradeAgreementUnits[0] = l_fXTradeAgreementUnit;
            WEB3FXAccOpenInputRequest l_request =
                new WEB3FXAccOpenInputRequest();
            l_request.fxTradeAgreementList = l_fXTradeAgreementUnits;
            l_request.batoCheckFlag = false;
            l_request.fxSystemCode= "02";

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(123));

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setInstitutionId(100);
            l_subAccountParams.setBranchId(101);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(100);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(101);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setAccountCode("2");
            l_mainAccountParams.setBranchId(101);
            TestDBUtility.insertWithDel(l_mainAccountParams);


            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            CompFxConditionParams l_compFxConditionParams =
                TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode("0D");
            l_compFxConditionParams.setBranchCode("381");
            l_compFxConditionParams.setFxSystemCode("02");
            l_compFxConditionParams.setFxSystemDiv("2");
            l_compFxConditionParams.setQuestionCheckDiv("1");
            TestDBUtility.insertWithDel(l_compFxConditionParams);

            l_impl.getInputScreen(l_request);

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01796, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * validate( )正常
     * get補助口座(補助口座タイプ : SubAccountTypeEnum)正常
     * get会社別FXシステム条件()の戻り値.質問同意チェック実施区分!=1：チェックするの場合
     * FX取引同意質問情報.質問回答≠”1：同意”の場合
     * getFX顧客()でFX顧客Paramsが取得できた場合
     * 引数.会社別FXシステム条件Params.FXシステム区分==2（CFDシステム）の場合
     */
    public void testGetInputScreen_C0005()
    {
        final String STR_METHOD_NAME = "testGetInputScreen_C0005()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3FXTradeAgreementUnit[] l_fXTradeAgreementUnits =
                new WEB3FXTradeAgreementUnit[1];
            WEB3FXTradeAgreementUnit l_fXTradeAgreementUnit =
                new WEB3FXTradeAgreementUnit();
            l_fXTradeAgreementUnit.questionNumber = "10";
            l_fXTradeAgreementUnit.questionAnswer = "2";

            l_fXTradeAgreementUnits[0] = l_fXTradeAgreementUnit;
            WEB3FXAccOpenInputRequest l_request =
                new WEB3FXAccOpenInputRequest();
            l_request.fxTradeAgreementList = l_fXTradeAgreementUnits;
            l_request.batoCheckFlag = false;
            l_request.fxSystemCode= "02";

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(123));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager", 
                "validateOrder",
                new Class[] {SubAccount.class},
                null);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setInstitutionId(100);
            l_subAccountParams.setBranchId(101);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(100);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(101);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setAccountCode("2");
            l_mainAccountParams.setBranchId(101);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(FxAccountRow.TYPE);
            FxAccountParams l_fxAccountParams = TestDBUtility.getFxAccountRow();
            l_fxAccountParams.setInstitutionCode("0D");
            l_fxAccountParams.setBranchCode("381");
            l_fxAccountParams.setFxSystemCode("02");
            l_fxAccountParams.setAccountCode("2");
            TestDBUtility.insertWithDel(l_fxAccountParams);

            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            CompFxConditionParams l_compFxConditionParams =
                TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode("0D");
            l_compFxConditionParams.setBranchCode("381");
            l_compFxConditionParams.setFxSystemCode("02");
            l_compFxConditionParams.setFxSystemDiv("2");
            l_compFxConditionParams.setQuestionCheckDiv("0");
            TestDBUtility.insertWithDel(l_compFxConditionParams);

            l_impl.getInputScreen(l_request);

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03133, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * validate( )正常
     * get補助口座(補助口座タイプ : SubAccountTypeEnum)正常
     * get会社別FXシステム条件()の戻り値.質問同意チェック実施区分!=1：チェックするの場合
     * FX取引同意質問情報.質問回答≠”1：同意”の場合
     * validateFX口座開設可能(SubAccount, String)正常
     * get会社別FXシステム条件()の戻り値.外部接続システムコード==01：GFTの場合
     * メールアドレス：　@getGFTFXメールアドレス()の戻り値
     * GFT口座開設フラグ：　@isGFT口座開設（）の戻り値
     */
    public void testGetInputScreen_C0006()
    {
        final String STR_METHOD_NAME = "testGetInputScreen_C0006()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3FXTradeAgreementUnit[] l_fXTradeAgreementUnits =
                new WEB3FXTradeAgreementUnit[1];
            WEB3FXTradeAgreementUnit l_fXTradeAgreementUnit =
                new WEB3FXTradeAgreementUnit();
            l_fXTradeAgreementUnit.questionNumber = "10";
            l_fXTradeAgreementUnit.questionAnswer = "2";

            l_fXTradeAgreementUnits[0] = l_fXTradeAgreementUnit;
            WEB3FXAccOpenInputRequest l_request =
                new WEB3FXAccOpenInputRequest();
            l_request.fxTradeAgreementList = l_fXTradeAgreementUnits;
            l_request.batoCheckFlag = false;
            l_request.fxSystemCode= "02";

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(123));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXDataControlServiceImpl",
                "getGFTFxSystemCodeLists",
                new Class[]{ String.class, String.class },
                null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXDataControlServiceImpl",
                "isGFTAccOpen",
                new Class[]{ String.class, String.class, String.class, ArrayList.class },
                new Boolean(true));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager", 
                "validateOrder",
                new Class[] {SubAccount.class},
                null);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setInstitutionId(100);
            l_subAccountParams.setBranchId(101);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(100);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(101);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setAccountCode("2");
            l_mainAccountParams.setBranchId(101);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(FxAccountRow.TYPE);
            FxAccountParams l_fxAccountParams = TestDBUtility.getFxAccountRow();
            l_fxAccountParams.setInstitutionCode("0D");
            l_fxAccountParams.setBranchCode("381");
            l_fxAccountParams.setFxSystemCode("01");
            l_fxAccountParams.setAccountCode("2");
            l_fxAccountParams.setFxMailAddress("test");
            TestDBUtility.insertWithDel(l_fxAccountParams);

            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            CompFxConditionParams l_compFxConditionParams =
                TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode("0D");
            l_compFxConditionParams.setBranchCode("381");
            l_compFxConditionParams.setFxSystemCode("02");
            l_compFxConditionParams.setFxSystemDiv("2");
            l_compFxConditionParams.setQuestionCheckDiv("0");
            l_compFxConditionParams.setOnlineAccOpen("1");
            l_compFxConditionParams.setExtConnectSystemCode("01");
            TestDBUtility.insertWithDel(l_compFxConditionParams);

            WEB3FXAccOpenInputResponse l_response = 
                (WEB3FXAccOpenInputResponse)l_request.createResponse();

            l_response = l_impl.getInputScreen(l_request);

            assertEquals("test", l_response.mailAddress);
            assertTrue(l_response.gftAccOpenFlag);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * validate( )正常
     * get補助口座(補助口座タイプ : SubAccountTypeEnum)正常
     * get会社別FXシステム条件()の戻り値.質問同意チェック実施区分!=1：チェックするの場合
     * FX取引同意質問情報.質問回答≠”1：同意”の場合
     * validateFX口座開設可能(SubAccount, String)正常
     * get会社別FXシステム条件()の戻り値.外部接続システムコード!=01：GFTの場合
     * メールアドレス：　@getGFTFXメールアドレス()の戻り値
     * GFT口座開設フラグ：　@isGFT口座開設（）の戻り値
     */
    public void testGetInputScreen_C0007()
    {
        final String STR_METHOD_NAME = "testGetInputScreen_C0007()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3FXTradeAgreementUnit[] l_fXTradeAgreementUnits =
                new WEB3FXTradeAgreementUnit[1];
            WEB3FXTradeAgreementUnit l_fXTradeAgreementUnit =
                new WEB3FXTradeAgreementUnit();
            l_fXTradeAgreementUnit.questionNumber = "10";
            l_fXTradeAgreementUnit.questionAnswer = "2";

            l_fXTradeAgreementUnits[0] = l_fXTradeAgreementUnit;
            WEB3FXAccOpenInputRequest l_request =
                new WEB3FXAccOpenInputRequest();
            l_request.fxTradeAgreementList = l_fXTradeAgreementUnits;
            l_request.batoCheckFlag = false;
            l_request.fxSystemCode= "02";

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(123));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXDataControlServiceImpl",
                "getGFTFxSystemCodeLists",
                new Class[]{ String.class, String.class },
                null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXDataControlServiceImpl",
                "isGFTAccOpen",
                new Class[]{ String.class, String.class, String.class, ArrayList.class },
                new Boolean(true));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager", 
                "validateOrder",
                new Class[] {SubAccount.class},
                null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager", 
                "validateOrder",
                new Class[] {SubAccount.class},
                null);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setInstitutionId(100);
            l_subAccountParams.setBranchId(101);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(100);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(101);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setAccountCode("2");
            l_mainAccountParams.setBranchId(101);
            l_mainAccountParams.setEmailAddress("test1");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(FxAccountRow.TYPE);
            FxAccountParams l_fxAccountParams = TestDBUtility.getFxAccountRow();
            l_fxAccountParams.setInstitutionCode("0D");
            l_fxAccountParams.setBranchCode("381");
            l_fxAccountParams.setFxSystemCode("01");
            l_fxAccountParams.setAccountCode("2");
            l_fxAccountParams.setFxMailAddress("test");
            TestDBUtility.insertWithDel(l_fxAccountParams);

            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            CompFxConditionParams l_compFxConditionParams =
                TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode("0D");
            l_compFxConditionParams.setBranchCode("381");
            l_compFxConditionParams.setFxSystemCode("02");
            l_compFxConditionParams.setFxSystemDiv("2");
            l_compFxConditionParams.setQuestionCheckDiv("0");
            l_compFxConditionParams.setOnlineAccOpen("1");
            l_compFxConditionParams.setExtConnectSystemCode("02");
            TestDBUtility.insertWithDel(l_compFxConditionParams);

            WEB3FXAccOpenInputResponse l_response = 
                (WEB3FXAccOpenInputResponse)l_request.createResponse();

            l_response = l_impl.getInputScreen(l_request);

            assertEquals("test1", l_response.mailAddress);
            assertFalse(l_response.gftAccOpenFlag);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testGetTradeAgreementScreen_C0001()
    {
        final String STR_METHOD_NAME = "testGetTradeAgreementScreen_C0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3FXAccOpenTradeAgreementRequest l_request =
                new WEB3FXAccOpenTradeAgreementRequest();
            l_request.fxSystemCode = "01";

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(123));

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setInstitutionId(100);
            l_subAccountParams.setBranchId(101);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(100);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(101);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setAccountCode("2");
            l_mainAccountParams.setBranchId(101);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "validateOrder",
                new Class[] {SubAccount.class},
                null);

            TestDBUtility.deleteAll(FxAccountRow.TYPE);
            FxAccountParams l_fxAccountParams = TestDBUtility.getFxAccountRow();
            l_fxAccountParams.setInstitutionCode("0D");
            l_fxAccountParams.setBranchCode("381");
            l_fxAccountParams.setFxSystemCode("01");
            l_fxAccountParams.setAccountCode("2");
            TestDBUtility.insertWithDel(l_fxAccountParams);

            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            CompFxConditionParams l_compFxConditionParams =
                TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode("0D");
            l_compFxConditionParams.setBranchCode("381");
            l_compFxConditionParams.setFxSystemCode("01");
            l_compFxConditionParams.setFxSystemDiv("2");
            TestDBUtility.insertWithDel(l_compFxConditionParams);

            l_impl.getTradeAgreementScreen(l_request);

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03133, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }


    public void testGetTradeAgreementScreen_C0002()
    {
        final String STR_METHOD_NAME = "testGetTradeAgreementScreen_C0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3FXAccOpenTradeAgreementRequest l_request =
                new WEB3FXAccOpenTradeAgreementRequest();
            l_request.fxSystemCode = "01";

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(123));

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setInstitutionId(100);
            l_subAccountParams.setBranchId(101);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(100);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(101);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setAccountType(MainAccountTypeEnum.JOINT_OWNERSHIP);
            l_mainAccountParams.setEraBorn(null);
            l_mainAccountParams.setBranchId(101);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "validateOrder",
                new Class[] {SubAccount.class},
                null);

            TestDBUtility.deleteAll(FxAccountRow.TYPE);
            FxAccountParams l_fxAccountParams = TestDBUtility.getFxAccountRow();
            l_fxAccountParams.setInstitutionCode("0D");
            l_fxAccountParams.setBranchCode("381");
            l_fxAccountParams.setFxSystemCode("01");
            l_fxAccountParams.setAccountCode("1");
            TestDBUtility.insertWithDel(l_fxAccountParams);

            
            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            CompFxConditionParams l_compFxConditionParams =
                TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode("0D");
            l_compFxConditionParams.setBranchCode("381");
            l_compFxConditionParams.setFxSystemCode("01");
            l_compFxConditionParams.setFxSystemDiv("2");
            l_compFxConditionParams.setOnlineAccOpen("1");
            TestDBUtility.insertWithDel(l_compFxConditionParams);

            TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
            BranchPreferencesParams l_branchPreferencesParams =
                TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setBranchId(101);
            l_branchPreferencesParams.setName("fxaccountopen.lowlimit.age");
            l_branchPreferencesParams.setNameSerialNo(1);
            l_branchPreferencesParams.setValue("100");
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            l_impl.getTradeAgreementScreen(l_request);

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02198, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    protected QuestionParams getQuestionParams()
    {
        QuestionParams l_params = new QuestionParams();
        //証券会社コード    institution_code    VARCHAR2    3    NotNull
        l_params.setInstitutionCode("0D");
        //部店コード    branch_code    VARCHAR2    3    NotNull
        l_params.setBranchCode("624");
        //質問区分    question_div    VARCHAR2    4    NotNull
        l_params.setQuestionDiv("1001");
        //質問番号    question_no    VARCHAR2    3    NotNull
        l_params.setQuestionNo("11");
        //質問内容    question    VARCHAR2    1000    NotNull
        l_params.setQuestion("this is a test");
        //更新者コード    last_updater    VARCHAR2    20    Null
        //作成日付    created_timestamp    DATE        NotNull
        l_params.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        //更新日付    last_updated_timestamp     DATE        NotNull
        l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        return l_params;
    }
    
    public InstitutionPreferencesParams getInstitutionPreferencesParams()
    {
        InstitutionPreferencesParams l_params = new InstitutionPreferencesParams();
        //証券会社ＩＤ    institution_id     NUMBER    18    NotNull
        l_params.setInstitutionId(33);
        //プリファ@レンス名    name     VARVHAR2    200    NotNull
        l_params.setName("jiddk");
        //プリファ@レンス名の連番    name_serial_no     NUMBER    6    NotNull
        l_params.setNameSerialNo(1);
        //プリファ@レンスの値    value     VARVHAR2    200    NotNull
        l_params.setValue("jidck");
        //作成日付    created_timestamp     DATE        NotNull
        l_params.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        //更新日付    last_updated_timestamp     DATE        NotNull
        l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        return l_params;
    }

}
@
