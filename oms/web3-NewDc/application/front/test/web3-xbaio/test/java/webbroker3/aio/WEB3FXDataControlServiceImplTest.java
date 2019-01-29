head	1.3;
access;
symbols;
locks; strict;
comment	@// @;


1.3
date	2011.04.08.02.18.37;	author zhang-tengyu;	state Exp;
branches;
next	1.2;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6184d9e706b7e68;
filename	WEB3FXDataControlServiceImplTest.java;

1.2
date	2011.04.08.00.53.59;	author che-jin;	state Exp;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	666;
commitid	1404d9e5c8b3d81;
filename	WEB3FXDataControlServiceImplTest.java;

1.1
date	2011.04.07.01.33.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FXDataControlServiceImplTest.java;


desc
@@


1.3
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : FXデータ制御サービスImplのテストクラス(WEB3FXDataControlServiceImplTest.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2008/04/15 周墨洋 (中訊) 新規作成
*/

package webbroker3.aio;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
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
import com.gftforex.soap_api.ResultInfoAddAccount;
import com.gftforex.soap_api.ResultInfoCreateUser;

import test.util.TestDBUtility;

import webbroker3.aio.data.CompFxConditionParams;
import webbroker3.aio.data.CompFxConditionRow;
import webbroker3.aio.data.FxAccountCodeParams;
import webbroker3.aio.data.FxAccountCodeRow;
import webbroker3.aio.data.FxAccountParams;
import webbroker3.aio.data.FxAccountRow;
import webbroker3.aio.data.FxTransferMasterParams;
import webbroker3.aio.data.GftAccountOpenStatusParams;
import webbroker3.aio.data.GftAccountOpenStatusRow;
import webbroker3.aio.data.GftMessageRow;
import webbroker3.aio.data.GftTransferStatusParams;
import webbroker3.aio.data.GftTransferStatusRow;
import webbroker3.aio.define.WEB3AioAcceptResultCodeDef;
import webbroker3.aio.message.WEB3FXAccInformationUnit;
import webbroker3.aio.message.WEB3FXGftAskingTelegramUnit;
import webbroker3.aio.message.WEB3FXGftResultNoticeTelegramUnit;
import webbroker3.aio.message.WEB3FXTradeAgreementUnit;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3GftTransStatusCourseDivDef;
import webbroker3.common.define.WEB3SoapResultCodeDef;
import webbroker3.gentrade.data.InstitutionPreferencesParams;
import webbroker3.gentrade.data.InstitutionPreferencesRow;
import webbroker3.gentrade.data.QuestionAnswerParams;
import webbroker3.gentrade.data.QuestionAnswerRow;
import webbroker3.gentrade.data.QuestionParams;
import webbroker3.gentrade.data.QuestionRow;
import webbroker3.gentrade.data.SoapConnectPrefRpcParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (FXデータ制御サービスImpl)<BR>
 * FXデータ制御サービスImplのテストクラス<BR>
 *
 * @@author 周墨洋
 * @@version 1.0
 */
public class WEB3FXDataControlServiceImplTest extends TestBaseForMock
{

    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3FXDataControlServiceImplTest.class);

    /**
     * FXデータ制御サービスImpl
     */
    WEB3FXDataControlServiceImpl fxDataControlServiceImpl = null;

    /**
     * @@param l_arg0
     */
    public WEB3FXDataControlServiceImplTest(String l_arg0)
    {
        super(l_arg0);
    }

    /**
     *
     */
    protected void setUp() throws Exception
    {
        super.setUp();
    }

    /**
     *
     */
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /**
     * updateGFT口座開設状況
     * testUpdateGFTAccountOpenStatusCase0001
     */
    public void testUpdateGFTAccountOpenStatusCase0001()
    {
        final String STR_METHOD_NAME = " testUpdateGFTAccountOpenStatusCase0001()";
        log.entering(STR_METHOD_NAME);

        this.fxDataControlServiceImpl = new WEB3FXDataControlServiceImpl();

        GftAccountOpenStatusParams l_gftAccountOpenStatusParams =
            new GftAccountOpenStatusParams();
        l_gftAccountOpenStatusParams.setInstitutionCode("0D");
        l_gftAccountOpenStatusParams.setBranchCode("381");
        l_gftAccountOpenStatusParams.setAccountCode("1234567");
        l_gftAccountOpenStatusParams.setOrderRequestNumber("987654321");
        l_gftAccountOpenStatusParams.setAccountOpenStatusDiv("12");
        l_gftAccountOpenStatusParams.setSendRcvDiv("34");
        l_gftAccountOpenStatusParams.setAgreementDiv("5");
        l_gftAccountOpenStatusParams.setCreatedTimestamp(
            WEB3DateUtility.getDate("20060412", "yyyyMMdd"));
        l_gftAccountOpenStatusParams.setLastUpdatedTimestamp(
            WEB3DateUtility.getDate("20060413", "yyyyMMdd"));
        l_gftAccountOpenStatusParams.setResultCodeSoap(null);

        try
        {
            TestDBUtility.deleteAll(GftAccountOpenStatusParams.TYPE);

            TestDBUtility.insertWithDel(l_gftAccountOpenStatusParams);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        try
        {
            String l_strInstitutionCode = "0D";
            String l_strBranchCode = "381";
            String l_strOrderRequestNumber = "987654321";
            String l_strResultCode = "1234";

            this.fxDataControlServiceImpl.updateGFTAccountOpenStatus(
                l_strInstitutionCode,
                l_strBranchCode,
                l_strOrderRequestNumber,
                l_strResultCode);

            QueryProcessor l_processor = Processors.getDefaultProcessor();
            List l_lisResultList =
                l_processor.doFindAllQuery(GftAccountOpenStatusParams.TYPE);
            assertEquals(1, l_lisResultList.size());

            GftAccountOpenStatusParams l_resultGftAccountOpenStatusParams =
                (GftAccountOpenStatusParams)l_lisResultList.get(0);

            int l_intCompareToDay =
                WEB3DateUtility.compareToDay(
                    l_resultGftAccountOpenStatusParams.getLastUpdatedTimestamp(),
                    WEB3DateUtility.getDate("20060413", "yyyyMMdd"));
            if (l_intCompareToDay >= 0)
            {
                assertTrue(true);
            }
            else
            {
            log.exiting(STR_METHOD_NAME);
             fail();
            }

            assertEquals("1234", l_resultGftAccountOpenStatusParams.getResultCodeSoap());

            log.exiting(STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(GftAccountOpenStatusParams.TYPE);
            }
            catch (Exception l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                log.exiting(TEST_END + STR_METHOD_NAME);
                fail();
            }
        }
    }

    /**
     * updateGFT口座開設状況
     * testUpdateGFTAccountOpenStatusCase0002
     */
    public void testUpdateGFTAccountOpenStatusCase0002()
    {
        final String STR_METHOD_NAME = " testUpdateGFTAccountOpenStatusCase0002()";
        log.entering(STR_METHOD_NAME);

        this.fxDataControlServiceImpl = new WEB3FXDataControlServiceImpl();

        try
        {
            TestDBUtility.deleteAll(GftAccountOpenStatusParams.TYPE);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        try
        {
            String l_strInstitutionCode = "0D";
            String l_strBranchCode = "381";
            String l_strOrderRequestNumber = "987654321";
            String l_strResultCode = "1234";

            this.fxDataControlServiceImpl.updateGFTAccountOpenStatus(
                l_strInstitutionCode,
                l_strBranchCode,
                l_strOrderRequestNumber,
                l_strResultCode);

            log.exiting(STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(GftAccountOpenStatusParams.TYPE);
            }
            catch (Exception l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                log.exiting(TEST_END + STR_METHOD_NAME);
                fail();
            }
        }
    }
    
    public void testGetQuestions_Case001()
    {
        final String STR_METHOD_NAME = "testGetQuestions_Case001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_processors = Processors.getDefaultProcessor();
            l_processors.doDeleteAllQuery(QuestionRow.TYPE);
            WEB3FXDataControlService l_service = new WEB3FXDataControlServiceImpl();

            QuestionParams[] l_results =
                l_service.getQuestions("0D", "624", "1001");
            assertNull(l_results);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testGetQuestions_Case002()
    {
        final String STR_METHOD_NAME = "testGetQuestions_Case001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_processors = Processors.getDefaultProcessor();
            l_processors.doDeleteAllQuery(QuestionRow.TYPE);
            QuestionParams l_questionParams = this.getQuestionParams();
            l_questionParams.setQuestionDiv("0001");
            l_questionParams.setQuestionNo("13");
            l_processors.doInsertQuery(l_questionParams);
            
            //222
            l_questionParams.setQuestionNo("11");
            l_processors.doInsertQuery(l_questionParams);
            
            //333
            l_questionParams.setQuestionNo("12");
            l_processors.doInsertQuery(l_questionParams);
            WEB3FXDataControlService l_service = new WEB3FXDataControlServiceImpl();
            QuestionParams[] l_results =
                l_service.getQuestions("0D", "624", null);
            assertEquals(3, l_results.length);
            assertEquals("11", l_results[0].getQuestionNo());
            assertEquals("12", l_results[1].getQuestionNo());
            assertEquals("13", l_results[2].getQuestionNo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetQuestions_Case003()
    {
        final String STR_METHOD_NAME = "testGetQuestions_Case001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_processors = Processors.getDefaultProcessor();
            l_processors.doDeleteAllQuery(QuestionRow.TYPE);
            QuestionParams l_questionParams = this.getQuestionParams();
            l_questionParams.setQuestionDiv("1001");
            l_questionParams.setQuestionNo("13");
            l_processors.doInsertQuery(l_questionParams);
            
            //222
            l_questionParams.setQuestionNo("11");
            l_processors.doInsertQuery(l_questionParams);
            
            //333
            l_questionParams.setQuestionNo("12");
            l_processors.doInsertQuery(l_questionParams);
            
            WEB3FXDataControlService l_service = new WEB3FXDataControlServiceImpl();
            QuestionParams[] l_results =
                l_service.getQuestions("0D", "624", "1001");
            assertEquals(3, l_results.length);
            assertEquals("11", l_results[0].getQuestionNo());
            assertEquals("12", l_results[1].getQuestionNo());
            assertEquals("13", l_results[2].getQuestionNo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testInsertGFTAccountOpenStatus()
    {
        final String STR_METHOD_NAME = "testInsertGFTAccountOpenStatus()";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_processors = Processors.getDefaultProcessor();
            l_processors.doDeleteAllQuery(GftAccountOpenStatusParams.TYPE);
            WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit =
                new WEB3FXGftAskingTelegramUnit();
            l_fxGftAskingTelegramUnit.institutionCode = "0D";
            l_fxGftAskingTelegramUnit.branchCode = "624";
            l_fxGftAskingTelegramUnit.accountCode = "1234567";
            l_fxGftAskingTelegramUnit.requestNumber = "1234";
            WEB3FXDataControlService l_service = new WEB3FXDataControlServiceImpl();
            l_service.insertGFTAccountOpenStatus(l_fxGftAskingTelegramUnit, "1", "11");
            List l_lisResult = l_processors.doFindAllQuery(GftAccountOpenStatusParams.TYPE);
            assertEquals(1, l_lisResult.size());
            assertEquals("11", ((GftAccountOpenStatusRow)l_lisResult.get(0)).getFxSystemCode());
            assertNull(((GftAccountOpenStatusRow)l_lisResult.get(0)).getExtAccountCode());
//            assertNull(((GftAccountOpenStatusRow)l_lisResult.get(0)).getExtAccountCode2());
//            assertNull(((GftAccountOpenStatusRow)l_lisResult.get(0)).getExtAccountCode3());
//            assertNull(((GftAccountOpenStatusRow)l_lisResult.get(0)).getExtAccountCode4());
//            assertNull(((GftAccountOpenStatusRow)l_lisResult.get(0)).getExtAccountCode5());
//            assertNull(((GftAccountOpenStatusRow)l_lisResult.get(0)).getExtAccountCode6());
//            assertNull(((GftAccountOpenStatusRow)l_lisResult.get(0)).getExtAccountCode7());
//            assertNull(((GftAccountOpenStatusRow)l_lisResult.get(0)).getExtAccountCode8());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 【WEB3】【入出金】単体テスト仕様兼報告書NO:1223
     *testInsertGFTAccountOpenStatus_case002
     */
    public void testInsertGFTAccountOpenStatus_case002()
    {
        final String STR_METHOD_NAME = "testInsertGFTAccountOpenStatus_case002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_processors = Processors.getDefaultProcessor();
            l_processors.doDeleteAllQuery(GftAccountOpenStatusParams.TYPE);
            WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit =
                new WEB3FXGftAskingTelegramUnit();
            l_fxGftAskingTelegramUnit.institutionCode = "0D";
            l_fxGftAskingTelegramUnit.branchCode = "624";
            l_fxGftAskingTelegramUnit.accountCode = "1234567";
            l_fxGftAskingTelegramUnit.requestNumber = "1234";
            WEB3FXDataControlService l_service = new WEB3FXDataControlServiceImpl();
            l_service.insertGFTAccountOpenStatus(l_fxGftAskingTelegramUnit, "1", "11");
            List l_lisResult = l_processors.doFindAllQuery(GftAccountOpenStatusParams.TYPE);

            assertNull(((GftAccountOpenStatusRow)l_lisResult.get(0)).getPassword());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testGetFXAccountCode_Case001()
    {
        final String STR_METHOD_NAME = "testGetFXAccountCode_Case001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3FXDataControlService l_service = new WEB3FXDataControlServiceImpl();
            l_service.getFXAccountCode(null, null, null, null, null);
            fail();
        }
        catch(WEB3SystemLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetFXAccountCode_Case002()
    {
        final String STR_METHOD_NAME = "testGetFXAccountCode_Case002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_processors = Processors.getDefaultProcessor();
            l_processors.doDeleteAllQuery(FxAccountCodeRow.TYPE);
            WEB3FXDataControlService l_service = new WEB3FXDataControlServiceImpl();
            l_service.getFXAccountCode("0D", "624", "11", "1234567", "1");
            fail();
        }
        catch(NotFoundException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals("FX口座番号テーブルが取得できませんでした", l_ex.getMessage());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetFXAccountCode_Case003()
    {
        final String STR_METHOD_NAME = "testGetFXAccountCode_Case003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_processors = Processors.getDefaultProcessor();
            l_processors.doDeleteAllQuery(FxAccountCodeRow.TYPE);

            FxAccountCodeParams l_fxAccountCodeParams =
                TestDBUtility.getFxAccountCodeRow();
            l_fxAccountCodeParams.setInstitutionCode("0D");
            l_fxAccountCodeParams.setBranchCode("624");
            l_fxAccountCodeParams.setFxSystemCode("11");
            l_fxAccountCodeParams.setAccountCode("1234567");
            l_fxAccountCodeParams.setFxCourseDiv("1");
            l_processors.doInsertQuery(l_fxAccountCodeParams);

            WEB3FXDataControlService l_service = new WEB3FXDataControlServiceImpl();
            FxAccountCodeParams l_result =
                l_service.getFXAccountCode("0D", "624", "1234567", "1", "11");
            assertEquals("1234567", l_result.getAccountCode());
            assertEquals("11", l_result.getFxSystemCode());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetFXAccountCode_Case004()
    {
        final String STR_METHOD_NAME = "testGetFXAccountCode_Case004()";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_processors = Processors.getDefaultProcessor();
            l_processors.doDeleteAllQuery(FxAccountCodeRow.TYPE);

            FxAccountCodeParams l_fxAccountCodeParams =
                TestDBUtility.getFxAccountCodeRow();
            l_fxAccountCodeParams.setInstitutionCode("0D");
            l_fxAccountCodeParams.setBranchCode("624");
            l_fxAccountCodeParams.setFxSystemCode("11");
            l_fxAccountCodeParams.setAccountCode("1234567");
            l_fxAccountCodeParams.setFxCourseDiv("1");
            l_processors.doInsertQuery(l_fxAccountCodeParams);

            WEB3FXDataControlService l_service = new WEB3FXDataControlServiceImpl();
            FxAccountCodeParams l_result =
                l_service.getFXAccountCode("0D", "624", "123456", "1", "11");
            assertEquals("1234567", l_result.getAccountCode());
            assertEquals("11", l_result.getFxSystemCode());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    
    public void testGetQuestionAnswers()
    {
        final String STR_METHOD_NAME = "testGetQuestionAnswers()";
        log.entering(STR_METHOD_NAME);
        try
        {
        	TestDBUtility.deleteAll(QuestionAnswerRow.TYPE);
        	QuestionAnswerParams l_questionAnswerParams =
        		TestDBUtility.getQuestionAnswerRow();
        	l_questionAnswerParams.setQuestionNo("20");
            l_questionAnswerParams.setQuestionDiv("1");
        	TestDBUtility.insertWithDel(l_questionAnswerParams);
        	l_questionAnswerParams.setQuestionNo("101");
        	TestDBUtility.insertWithDel(l_questionAnswerParams);
        	l_questionAnswerParams.setQuestionNo("31");
        	TestDBUtility.insertWithDel(l_questionAnswerParams);
        	l_questionAnswerParams.setQuestionNo("90");
        	TestDBUtility.insertWithDel(l_questionAnswerParams);
        	l_questionAnswerParams.setQuestionNo("100");
        	TestDBUtility.insertWithDel(l_questionAnswerParams);
        	l_questionAnswerParams.setQuestionNo("200");
        	TestDBUtility.insertWithDel(l_questionAnswerParams);
        	l_questionAnswerParams.setQuestionNo("301");
        	TestDBUtility.insertWithDel(l_questionAnswerParams);

        	WEB3FXDataControlServiceImpl l_fxDataControlServiceImpl =
        		new WEB3FXDataControlServiceImpl();
            QuestionAnswerParams[] l_results =
            	l_fxDataControlServiceImpl.getQuestionAnswers("0D", "381", "1", "000003006");

            assertEquals(l_results[0].getQuestionNo(), "20");
            assertEquals(l_results[1].getQuestionNo(), "31");
            assertEquals(l_results[2].getQuestionNo(), "90");
            assertEquals(l_results[3].getQuestionNo(), "100");
            assertEquals(l_results[4].getQuestionNo(), "101");
            assertEquals(l_results[5].getQuestionNo(), "200");
            assertEquals(l_results[6].getQuestionNo(), "301");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 【ｘTrade】補足資料.DB更新
     * 「FX口座開設_FX口座番号テーブル.xls/
     * (FX口座開設)FX口座番号テーブル_DB更新仕様」
     * 更新正確
     */
    public void testInsertFXAccountCode_C0001()
    {
        final String STR_METHOD_NAME = "testInsertFXAccountCode_C0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_processors = Processors.getDefaultProcessor();
            l_processors.doDeleteAllQuery(FxAccountCodeParams.TYPE);

            WEB3FXAccInformationUnit[] l_fxAccInformationUnits =
            	new WEB3FXAccInformationUnit[1];
            WEB3FXAccInformationUnit l_fxAccInformationUnit0 =
            	new WEB3FXAccInformationUnit();
//            WEB3FXAccInformationUnit l_fxAccInformationUnit1 =
//            	new WEB3FXAccInformationUnit();
//            WEB3FXAccInformationUnit l_fxAccInformationUnit2 =
//            	new WEB3FXAccInformationUnit();
//            WEB3FXAccInformationUnit l_fxAccInformationUnit3 =
//                new WEB3FXAccInformationUnit();
//            WEB3FXAccInformationUnit l_fxAccInformationUnit4 =
//                new WEB3FXAccInformationUnit();
//            WEB3FXAccInformationUnit l_fxAccInformationUnit5 =
//                new WEB3FXAccInformationUnit();
//            WEB3FXAccInformationUnit l_fxAccInformationUnit6 =
//                new WEB3FXAccInformationUnit();
//            WEB3FXAccInformationUnit l_fxAccInformationUnit7 =
//                new WEB3FXAccInformationUnit();
//            WEB3FXAccInformationUnit l_fxAccInformationUnit8 =
//                new WEB3FXAccInformationUnit();
//            WEB3FXAccInformationUnit l_fxAccInformationUnit9 =
//                new WEB3FXAccInformationUnit();
            
            l_fxAccInformationUnits[0] = l_fxAccInformationUnit0;
//            l_fxAccInformationUnits[1] = l_fxAccInformationUnit1;
//            l_fxAccInformationUnits[2] = l_fxAccInformationUnit2;
//            l_fxAccInformationUnits[3] = l_fxAccInformationUnit3;
//            l_fxAccInformationUnits[4] = l_fxAccInformationUnit4;
//            l_fxAccInformationUnits[5] = l_fxAccInformationUnit5;
//            l_fxAccInformationUnits[6] = l_fxAccInformationUnit6;
//            l_fxAccInformationUnits[7] = l_fxAccInformationUnit7;
//            l_fxAccInformationUnits[8] = l_fxAccInformationUnit8;
//            l_fxAccInformationUnits[9] = l_fxAccInformationUnit9;
            
            l_fxAccInformationUnit0.fxCourseDiv = "3";
            l_fxAccInformationUnit0.fxAccountCode = "987654";
//            l_fxAccInformationUnit1.fxCourseDiv = "2";
//            l_fxAccInformationUnit1.fxAccountCode = "654321";
//            l_fxAccInformationUnit2.fxCourseDiv = "1";
//            l_fxAccInformationUnit2.fxAccountCode = "654123";
//            l_fxAccInformationUnit3.fxCourseDiv = "4";
//            l_fxAccInformationUnit3.fxAccountCode = "654123";
//            l_fxAccInformationUnit4.fxCourseDiv = "5";
//            l_fxAccInformationUnit4.fxAccountCode = "654123";
//            l_fxAccInformationUnit5.fxCourseDiv = "6";
//            l_fxAccInformationUnit5.fxAccountCode = "654123";
//            l_fxAccInformationUnit6.fxCourseDiv = "7";
//            l_fxAccInformationUnit6.fxAccountCode = "654123";
//            l_fxAccInformationUnit7.fxCourseDiv = "8";
//            l_fxAccInformationUnit7.fxAccountCode = "654123";
//            l_fxAccInformationUnit8.fxCourseDiv = "9";
//            l_fxAccInformationUnit8.fxAccountCode = "654123";
//            l_fxAccInformationUnit9.fxCourseDiv = "10";
//            l_fxAccInformationUnit9.fxAccountCode = "654123";

            WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit =
                new WEB3FXGftResultNoticeTelegramUnit();
            l_fxGftResultNoticeTelegramUnit.institutionCode = "0D";
            l_fxGftResultNoticeTelegramUnit.branchCode = "624";
            l_fxGftResultNoticeTelegramUnit.accountCode = "1234567";
            l_fxGftResultNoticeTelegramUnit.gftAcc1 = "321";
            l_fxGftResultNoticeTelegramUnit.gftAcc2 = "456";
            l_fxGftResultNoticeTelegramUnit.fxAccInformationList = l_fxAccInformationUnits;
            
            WEB3FXDataControlService l_service = new WEB3FXDataControlServiceImpl();
            l_service.insertFXAccountCode(l_fxGftResultNoticeTelegramUnit, "11","10");
            List l_lisResult = l_processors.doFindAllQuery(FxAccountCodeParams.TYPE);

            assertEquals(1, l_lisResult.size());
            assertEquals("0D", ((FxAccountCodeRow)l_lisResult.get(0)).getInstitutionCode());
            assertEquals("624", ((FxAccountCodeRow)l_lisResult.get(0)).getBranchCode());
            assertEquals("10", ((FxAccountCodeRow)l_lisResult.get(0)).getFxSystemCode());
            assertEquals("3", ((FxAccountCodeRow)l_lisResult.get(0)).getFxCourseDiv());
            assertEquals("1234567", ((FxAccountCodeRow)l_lisResult.get(0)).getAccountCode());
            assertNull(((FxAccountCodeRow)l_lisResult.get(0)).getLastUpdater());
            assertEquals("987654", ((FxAccountCodeRow)l_lisResult.get(0)).getFxAccountCode());
//            
//            assertEquals("0D", ((FxAccountCodeRow)l_lisResult.get(1)).getInstitutionCode());
//            assertEquals("624", ((FxAccountCodeRow)l_lisResult.get(1)).getBranchCode());
//            assertEquals("11", ((FxAccountCodeRow)l_lisResult.get(1)).getFxSystemCode());
//            assertEquals("2", ((FxAccountCodeRow)l_lisResult.get(1)).getFxCourseDiv());
//            assertEquals("1234567", ((FxAccountCodeRow)l_lisResult.get(1)).getAccountCode());
//            assertNull(((FxAccountCodeRow)l_lisResult.get(1)).getLastUpdater());
//            assertEquals("654321", ((FxAccountCodeRow)l_lisResult.get(1)).getFxAccountCode());
//
//
//            assertEquals("0D", ((FxAccountCodeRow)l_lisResult.get(2)).getInstitutionCode());
//            assertEquals("624", ((FxAccountCodeRow)l_lisResult.get(2)).getBranchCode());
//            assertEquals("11", ((FxAccountCodeRow)l_lisResult.get(2)).getFxSystemCode());
//            assertEquals("1", ((FxAccountCodeRow)l_lisResult.get(2)).getFxCourseDiv());
//            assertEquals("1234567", ((FxAccountCodeRow)l_lisResult.get(2)).getAccountCode());
//            assertNull(((FxAccountCodeRow)l_lisResult.get(2)).getLastUpdater());
//            assertEquals("654123", ((FxAccountCodeRow)l_lisResult.get(2)).getFxAccountCode());
//            
//            assertEquals("10", ((FxAccountCodeRow)l_lisResult.get(3)).getFxSystemCode());
//            assertEquals("10", ((FxAccountCodeRow)l_lisResult.get(4)).getFxSystemCode());
//            assertEquals("10", ((FxAccountCodeRow)l_lisResult.get(5)).getFxSystemCode());
//            assertEquals("10", ((FxAccountCodeRow)l_lisResult.get(6)).getFxSystemCode());
//            assertEquals("10", ((FxAccountCodeRow)l_lisResult.get(7)).getFxSystemCode());
//            assertEquals("10", ((FxAccountCodeRow)l_lisResult.get(8)).getFxSystemCode());
//            assertEquals("10", ((FxAccountCodeRow)l_lisResult.get(9)).getFxSystemCode());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testInsertQuestionAnswer_Case001()
    {
        final String STR_METHOD_NAME = "testInsertQuestionAnswer_Case001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_processors = Processors.getDefaultProcessor();
            l_processors.doDeleteAllQuery(QuestionAnswerParams.TYPE);
            WEB3FXTradeAgreementUnit[] l_fxTradeAgreementList =
                new WEB3FXTradeAgreementUnit[2];
            l_fxTradeAgreementList[0] = new WEB3FXTradeAgreementUnit();
            l_fxTradeAgreementList[0].questionNumber = "12";
            l_fxTradeAgreementList[0].questionAnswer = "1";
            
            l_fxTradeAgreementList[1] = new WEB3FXTradeAgreementUnit();
            l_fxTradeAgreementList[1].questionNumber = "13";
            l_fxTradeAgreementList[1].questionAnswer = "0";
            WEB3FXDataControlService l_service = new WEB3FXDataControlServiceImpl();
            l_service.insertQuestionAnswer("0D", "624", "123", l_fxTradeAgreementList, null);
            List l_lisResult = l_processors.doFindAllQuery(QuestionAnswerParams.TYPE);
            assertEquals(2, l_lisResult.size());
            assertEquals("0001", ((QuestionAnswerRow)l_lisResult.get(0)).getQuestionDiv());
            assertEquals("0001", ((QuestionAnswerRow)l_lisResult.get(1)).getQuestionDiv());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testInsertQuestionAnswer_Case002()
    {
        final String STR_METHOD_NAME = "testInsertQuestionAnswer_Case002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_processors = Processors.getDefaultProcessor();
            l_processors.doDeleteAllQuery(QuestionAnswerParams.TYPE);
            WEB3FXTradeAgreementUnit[] l_fxTradeAgreementList =
                new WEB3FXTradeAgreementUnit[2];
            l_fxTradeAgreementList[0] = new WEB3FXTradeAgreementUnit();
            l_fxTradeAgreementList[0].questionNumber = "12";
            l_fxTradeAgreementList[0].questionAnswer = "1";
            
            l_fxTradeAgreementList[1] = new WEB3FXTradeAgreementUnit();
            l_fxTradeAgreementList[1].questionNumber = "13";
            l_fxTradeAgreementList[1].questionAnswer = "0";
            WEB3FXDataControlService l_service = new WEB3FXDataControlServiceImpl();
            l_service.insertQuestionAnswer("0D", "624", "123", l_fxTradeAgreementList, "11");
            List l_lisResult = l_processors.doFindAllQuery(QuestionAnswerParams.TYPE);
            assertEquals(2, l_lisResult.size());
            assertEquals("11", ((QuestionAnswerRow)l_lisResult.get(0)).getQuestionDiv());
            assertEquals("11", ((QuestionAnswerRow)l_lisResult.get(1)).getQuestionDiv());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testInsertGFTTransferStatus0_Case001()
    {
        final String STR_METHOD_NAME = "testInsertGFTTransferStatus0_Case001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_processors = Processors.getDefaultProcessor();
            l_processors.doDeleteAllQuery(GftTransferStatusParams.TYPE);
            CompFxConditionParams l_compFxConditionParams =
                new CompFxConditionParams();
            l_compFxConditionParams.setFxSystemCode("11");
            
            WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit =
                new WEB3FXGftAskingTelegramUnit();
            l_fxGftAskingTelegramUnit.institutionCode = "0D";
            l_fxGftAskingTelegramUnit.branchCode = "624";
            l_fxGftAskingTelegramUnit.accountCode = "1234567";
            l_fxGftAskingTelegramUnit.requestNumber = "123";
            l_fxGftAskingTelegramUnit.gftOperationDiv = "04";
            l_fxGftAskingTelegramUnit.fxAccountCode = "456";
            l_fxGftAskingTelegramUnit.cashinoutAmt = "10001";
            l_fxGftAskingTelegramUnit.dirSendTime = "20080212153212";//YYYYMMDDHHMMSS
            
            WEB3FXDataControlService l_service = new WEB3FXDataControlServiceImpl();
            l_service.insertGFTTransferStatus(
                l_fxGftAskingTelegramUnit,
                "3",
                "20080212",
                "789",
                l_compFxConditionParams,
                "5");
            List l_lisResult = l_processors.doFindAllQuery(GftTransferStatusParams.TYPE);
            assertEquals(1, l_lisResult.size());
            assertEquals("11", ((GftTransferStatusRow)l_lisResult.get(0)).getFxSystemCode());
            assertEquals("5", ((GftTransferStatusRow)l_lisResult.get(0)).getIoListTradeDiv());
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testInsertGFTTransferStatus0_Case002()
    {
        final String STR_METHOD_NAME = "testInsertGFTTransferStatus0_Case002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_processors = Processors.getDefaultProcessor();
            l_processors.doDeleteAllQuery(GftTransferStatusParams.TYPE);
            CompFxConditionParams l_compFxConditionParams =
                new CompFxConditionParams();
            l_compFxConditionParams.setFxSystemCode("11");
            
            WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit =
                new WEB3FXGftAskingTelegramUnit();
            l_fxGftAskingTelegramUnit.institutionCode = "0D";
            l_fxGftAskingTelegramUnit.branchCode = "624";
            l_fxGftAskingTelegramUnit.accountCode = "1234567";
            l_fxGftAskingTelegramUnit.requestNumber = "123";
            l_fxGftAskingTelegramUnit.gftOperationDiv = "04";
            l_fxGftAskingTelegramUnit.fxAccountCode = "456";
            l_fxGftAskingTelegramUnit.cashinoutAmt = "10001";
            l_fxGftAskingTelegramUnit.dirSendTime = "20080212153212";//YYYYMMDDHHMMSS
            
            WEB3FXDataControlService l_service = new WEB3FXDataControlServiceImpl();
            l_service.insertGFTTransferStatus(
                l_fxGftAskingTelegramUnit,
                "3",
                "20080212",
                "789",
                l_compFxConditionParams,
                null);
            List l_lisResult = l_processors.doFindAllQuery(GftTransferStatusParams.TYPE);
            assertEquals(1, l_lisResult.size());
            assertEquals("11", ((GftTransferStatusRow)l_lisResult.get(0)).getFxSystemCode());
            assertNull(((GftTransferStatusRow)l_lisResult.get(0)).getIoListTradeDiv());
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testInsertGFTTransferStatus1_Case001()
    {
        final String STR_METHOD_NAME = "testInsertGFTTransferStatus1_Case001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_processors = Processors.getDefaultProcessor();
            l_processors.doDeleteAllQuery(GftTransferStatusParams.TYPE);
            
            WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit =
                new WEB3FXGftAskingTelegramUnit();
            l_fxGftAskingTelegramUnit.institutionCode = "0D";
            l_fxGftAskingTelegramUnit.branchCode = "624";
            l_fxGftAskingTelegramUnit.accountCode = "1234567";
            l_fxGftAskingTelegramUnit.requestNumber = "123";
            l_fxGftAskingTelegramUnit.gftOperationDiv = "04";
            l_fxGftAskingTelegramUnit.fxAccountCode = "456";
            l_fxGftAskingTelegramUnit.cashinoutAmt = "10001";
            l_fxGftAskingTelegramUnit.dirSendTime = "20080212153212";//YYYYMMDDHHMMSS
            WEB3FXDataControlService l_service = new WEB3FXDataControlServiceImpl();
            l_service.insertGFTTransferStatus(
                l_fxGftAskingTelegramUnit,
                "3",
                "20080212",
                "789",
                "101",
                "5");

            List l_lisResult = l_processors.doFindAllQuery(GftTransferStatusParams.TYPE);
            assertEquals(1, l_lisResult.size());
            assertNull(((GftTransferStatusRow)l_lisResult.get(0)).getFxSystemCode());
            assertEquals("5", ((GftTransferStatusRow)l_lisResult.get(0)).getIoListTradeDiv());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testInsertGFTTransferStatus1_Case002()
    {
        final String STR_METHOD_NAME = "testInsertGFTTransferStatus1_Case002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_processors = Processors.getDefaultProcessor();
            l_processors.doDeleteAllQuery(GftTransferStatusParams.TYPE);
            
            WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit =
                new WEB3FXGftAskingTelegramUnit();
            l_fxGftAskingTelegramUnit.institutionCode = "0D";
            l_fxGftAskingTelegramUnit.branchCode = "624";
            l_fxGftAskingTelegramUnit.accountCode = "1234567";
            l_fxGftAskingTelegramUnit.requestNumber = "123";
            l_fxGftAskingTelegramUnit.gftOperationDiv = "04";
            l_fxGftAskingTelegramUnit.fxAccountCode = "456";
            l_fxGftAskingTelegramUnit.cashinoutAmt = "10001";
            l_fxGftAskingTelegramUnit.dirSendTime = "20080212153212";//YYYYMMDDHHMMSS
            WEB3FXDataControlService l_service = new WEB3FXDataControlServiceImpl();
            l_service.insertGFTTransferStatus(
                l_fxGftAskingTelegramUnit,
                "3",
                "20080212",
                "789",
                "101",
                null);

            List l_lisResult = l_processors.doFindAllQuery(GftTransferStatusParams.TYPE);
            assertEquals(1, l_lisResult.size());
            assertNull(((GftTransferStatusRow)l_lisResult.get(0)).getFxSystemCode());
            assertNull(((GftTransferStatusRow)l_lisResult.get(0)).getIoListTradeDiv());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testInsertFXAccount()
    {
        final String STR_METHOD_NAME = "testInsertFXAccount()";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_processors = Processors.getDefaultProcessor();
            l_processors.doDeleteAllQuery(FxAccountParams.TYPE);

            WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit =
                new WEB3FXGftResultNoticeTelegramUnit();
            l_fxGftResultNoticeTelegramUnit.institutionCode = "0D";
            l_fxGftResultNoticeTelegramUnit.branchCode = "624";
            l_fxGftResultNoticeTelegramUnit.accountCode = "1234567";
            l_fxGftResultNoticeTelegramUnit.gftAcc1 = "321";
            l_fxGftResultNoticeTelegramUnit.gftAcc2 = "456";
            l_fxGftResultNoticeTelegramUnit.fxMailAddress = "jiddk@@126.com";
            l_fxGftResultNoticeTelegramUnit.fxFirstLoginId = "123456789";
            GftAccountOpenStatusParams l_gftAccontOpenStatusParams =
                new GftAccountOpenStatusParams();
            l_gftAccontOpenStatusParams.setFxSystemCode("11");
            l_gftAccontOpenStatusParams.setLastName("jiddk");
            l_gftAccontOpenStatusParams.setFirstName("jidck");
            
            //CompFxConditionRow
            l_processors.doDeleteAllQuery(CompFxConditionRow.TYPE);
            CompFxConditionParams l_compFxConditionParams =
                TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode("0D");
            l_compFxConditionParams.setBranchCode("624");
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
            l_processors.doInsertQuery(l_compFxConditionParams);
            
            //InstitutionParams
            l_processors.doDeleteAllQuery(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_processors.doInsertQuery(l_institutionParams);
            
            WEB3FXDataControlService l_service = new WEB3FXDataControlServiceImpl();
            l_service.insertFXAccount(
                l_fxGftResultNoticeTelegramUnit,
                l_gftAccontOpenStatusParams);
            
            List l_lisResult = l_processors.doFindAllQuery(FxAccountParams.TYPE);
            assertEquals(1, l_lisResult.size());
            assertEquals("11", ((FxAccountRow)l_lisResult.get(0)).getFxSystemCode());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
  
    //insertFXAccount(GftAccountOpenStatusParams l_gftAccontOpenStatusParams,
    //String l_strUpdaterCode)
    public void testInsertFXAccount_case001()
    {
        final String STR_METHOD_NAME = "testInsertFXAccount_case001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_processors = Processors.getDefaultProcessor();
            l_processors.doDeleteAllQuery(FxAccountParams.TYPE);

            
            //TestDBUtility.deleteAll(GftAccountOpenStatusParams.TYPE);
            GftAccountOpenStatusParams l_gftAccountOpenStatusParams =
                new GftAccountOpenStatusParams();
            l_gftAccountOpenStatusParams.setInstitutionCode("0D");
            l_gftAccountOpenStatusParams.setBranchCode("624");
            l_gftAccountOpenStatusParams.setAccountCode("123456");
            l_gftAccountOpenStatusParams.setFxSystemCode("11");
            l_gftAccountOpenStatusParams.setLoginId("1000");
            //TestDBUtility.insertWithDel(l_gftAccontOpenStatusParams);
            
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            WEB3FXDataControlService l_impl = new WEB3FXDataControlServiceImpl();
            l_impl.insertFXAccount(l_gftAccountOpenStatusParams,"123");

            List l_lisResult = l_processors.doFindAllQuery(FxAccountParams.TYPE);
            assertEquals(1, l_lisResult.size());
            assertEquals(3362411123456L, ((FxAccountRow)l_lisResult.get(0)).getFxAccountId());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
  
    
    //insertFXAccount(WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit, 
    //GftAccountOpenStatusParams l_gftAccontOpenStatusParams)
    public void testInsertFXAccount_case0001()
    {
        final String STR_METHOD_NAME = "testInsertFXAccount_case0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_processors = Processors.getDefaultProcessor();
            l_processors.doDeleteAllQuery(FxAccountParams.TYPE);

            
            WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit =
                new WEB3FXGftResultNoticeTelegramUnit();
            l_fxGftResultNoticeTelegramUnit.institutionCode = "0D";
            l_fxGftResultNoticeTelegramUnit.branchCode = "624";
            l_fxGftResultNoticeTelegramUnit.accountCode = "7584652";
            l_fxGftResultNoticeTelegramUnit.fxFirstLoginId = "56789";
            
            GftAccountOpenStatusParams l_gftAccontOpenStatusParams =
                new GftAccountOpenStatusParams();
            l_gftAccontOpenStatusParams.setFxSystemCode("11");
            
            WEB3FXDataControlService l_impl = new WEB3FXDataControlServiceImpl();
            l_impl.insertFXAccount(l_fxGftResultNoticeTelegramUnit,l_gftAccontOpenStatusParams);

            List l_lisResult = l_processors.doFindAllQuery(FxAccountParams.TYPE);
            assertEquals(1, l_lisResult.size());
            assertEquals(3362411758465L, ((FxAccountRow)l_lisResult.get(0)).getFxAccountId());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    public void testInsertFXAccountCode00()
    {
        final String STR_METHOD_NAME = "testInsertFXAccountCode()";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_processors = Processors.getDefaultProcessor();
            l_processors.doDeleteAllQuery(FxAccountCodeParams.TYPE);
            
            GftAccountOpenStatusParams l_gftAccountOpenStatusParams =
                new GftAccountOpenStatusParams();
            WEB3FXAccInformationUnit l_fxAccInformation =
                new WEB3FXAccInformationUnit();

            l_gftAccountOpenStatusParams.setInstitutionCode("0D");
            l_gftAccountOpenStatusParams.setBranchCode("624");
            l_gftAccountOpenStatusParams.setFxSystemCode("11");
            l_gftAccountOpenStatusParams.setAccountCode("123456");
            
            
            l_fxAccInformation.fxCourseDiv = "1";
            l_fxAccInformation.fxAccountCode = "321";
            
            WEB3FXDataControlService l_service = new WEB3FXDataControlServiceImpl();
            l_service.insertFXAccountCode(
                l_gftAccountOpenStatusParams,
                l_fxAccInformation,
                "jiddk");

            List l_lisResult = l_processors.doFindAllQuery(FxAccountCodeParams.TYPE);
            assertEquals(1, l_lisResult.size());
            assertEquals("11", ((FxAccountCodeRow)l_lisResult.get(0)).getFxSystemCode());
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    
    public void testGetNewFXAccountID_case001()
    {
        final String STR_METHOD_NAME = "testGetNewFXAccountID_case001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            String l_strInstitutionCode = "0D";
            String l_strBranchCode = "381";
            String l_strAccountCode = "123456";
            String l_strFxSystemCode = "11";
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);
            
         
            WEB3FXDataControlService l_impl = new WEB3FXDataControlServiceImpl();
            String l_strFxAccountId =
                l_impl.getNewFXAccountID(l_strInstitutionCode,l_strBranchCode,l_strAccountCode,l_strFxSystemCode);
            assertEquals("3338111123456", l_strFxAccountId);
        
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    
    public void testGetNewFXAccountID_case002()
    {
        final String STR_METHOD_NAME = "testGetNewFXAccountID_case002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            String l_strInstitutionCode = "0D";
            String l_strBranchCode = "381";
            String l_strAccountCode = null;
            String l_strFxSystemCode = "11";
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);
            
         
            WEB3FXDataControlService l_impl = new WEB3FXDataControlServiceImpl();
            l_impl.getNewFXAccountID(l_strInstitutionCode,l_strBranchCode,l_strAccountCode,l_strFxSystemCode);
            fail();
        
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetNewFXAccountID_case003()
    {
        final String STR_METHOD_NAME = "testGetNewFXAccountID_case003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            String l_strInstitutionCode = "0D";
            String l_strBranchCode = "381";
            String l_strAccountCode = "1234567";
            String l_strFxSystemCode = "11";
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);
            
         
            WEB3FXDataControlService l_impl = new WEB3FXDataControlServiceImpl();
            String l_strFxAccountId =
                l_impl.getNewFXAccountID(l_strInstitutionCode,l_strBranchCode,l_strAccountCode,l_strFxSystemCode);
            assertEquals("3338111123456", l_strFxAccountId);
        
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetAccountCode_Case001()
    {
        final String STR_METHOD_NAME = "testGetAccountCode_Case001()";
        log.entering(STR_METHOD_NAME);
        WEB3FXDataControlServiceImpl l_impl = 
            new WEB3FXDataControlServiceImpl();
        try
        {
            //FxAccountCodeParams
            TestDBUtility.deleteAll(FxAccountCodeParams.TYPE);
            FxAccountCodeParams l_fxAccountCodeParams =
                TestDBUtility.getFxAccountCodeRow();
            l_fxAccountCodeParams.setInstitutionCode("0D");
            l_fxAccountCodeParams.setBranchCode("624");
            l_fxAccountCodeParams.setAccountCode("789456");
            l_fxAccountCodeParams.setFxAccountCode("123456");
            l_fxAccountCodeParams.setFxSystemCode("12");
            TestDBUtility.insertWithDel(l_fxAccountCodeParams);
            
            l_impl.getAccountCode("0D", "624", "123456", "11");
            fail();
        }
        catch (NotFoundException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals("FX口座番号テーブルが取得できませんでした", l_ex.getMessage());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetAccountCode_Case002()
    {
        final String STR_METHOD_NAME = "testGetAccountCode_Case002()";
        log.entering(STR_METHOD_NAME);
        WEB3FXDataControlServiceImpl l_impl = 
            new WEB3FXDataControlServiceImpl();
        try
        {
            //FxAccountCodeParams
            TestDBUtility.deleteAll(FxAccountCodeParams.TYPE);
            FxAccountCodeParams l_fxAccountCodeParams =
                TestDBUtility.getFxAccountCodeRow();
            l_fxAccountCodeParams.setInstitutionCode("0D");
            l_fxAccountCodeParams.setBranchCode("624");
            l_fxAccountCodeParams.setAccountCode("789456");
            l_fxAccountCodeParams.setFxAccountCode("123456");
            l_fxAccountCodeParams.setFxSystemCode("11");
            TestDBUtility.insertWithDel(l_fxAccountCodeParams);
            
            l_fxAccountCodeParams.setAccountCode("456789");
            TestDBUtility.insertWithDel(l_fxAccountCodeParams);
            
            l_impl.getAccountCode("0D", "624", "123456", "11");
            fail();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80006, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetAccountCode_Case003()
    {
        final String STR_METHOD_NAME = "testGetAccountCode_Case003()";
        log.entering(STR_METHOD_NAME);
        WEB3FXDataControlServiceImpl l_impl = 
            new WEB3FXDataControlServiceImpl();
        try
        {
            //FxAccountCodeParams
            TestDBUtility.deleteAll(FxAccountCodeParams.TYPE);
            FxAccountCodeParams l_fxAccountCodeParams =
                TestDBUtility.getFxAccountCodeRow();
            l_fxAccountCodeParams.setInstitutionCode("0D");
            l_fxAccountCodeParams.setBranchCode("624");
            l_fxAccountCodeParams.setAccountCode("789456");
            l_fxAccountCodeParams.setFxAccountCode("123456");
            l_fxAccountCodeParams.setFxSystemCode("11");
            TestDBUtility.insertWithDel(l_fxAccountCodeParams);

            String l_strReturn =
                l_impl.getAccountCode("0D", "624", "123456", "11");
            assertEquals("789456", l_strReturn);

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetAccountCode_Case004()
    {
        final String STR_METHOD_NAME = "testGetAccountCode_Case004()";
        log.entering(STR_METHOD_NAME);
        WEB3FXDataControlServiceImpl l_impl = 
            new WEB3FXDataControlServiceImpl();
        try
        {
            //FxAccountCodeParams
            TestDBUtility.deleteAll(FxAccountCodeParams.TYPE);
            FxAccountCodeParams l_fxAccountCodeParams =
                TestDBUtility.getFxAccountCodeRow();
            l_fxAccountCodeParams.setInstitutionCode("0D");
            l_fxAccountCodeParams.setBranchCode("624");
            l_fxAccountCodeParams.setAccountCode("789456");
            l_fxAccountCodeParams.setFxAccountCode("123456");
            l_fxAccountCodeParams.setFxSystemCode("11");
            TestDBUtility.insertWithDel(l_fxAccountCodeParams);

            String l_strReturn =
                l_impl.getAccountCode("0D", "624", "123456", null);
            assertEquals("789456", l_strReturn);

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetSoapTFXAcceptResultCode_T01()
    {
        final String STR_METHOD_NAME = "testGetSoapTFXAcceptResultCode_T01()";
        
        log.entering(STR_METHOD_NAME);
        
        WEB3FXDataControlServiceImpl l_impl = new WEB3FXDataControlServiceImpl();
        String l_strAcceptResultCode = WEB3SoapResultCodeDef.NORMAL;
        String l_str = l_impl.getSoapTFXAcceptResultCode(l_strAcceptResultCode);
        
        assertEquals(l_str,WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000000);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetSoapTFXAcceptResultCode_T02()
    {
        final String STR_METHOD_NAME = "testGetSoapTFXAcceptResultCode_T02()";
        
        log.entering(STR_METHOD_NAME);
        
        WEB3FXDataControlServiceImpl l_impl = new WEB3FXDataControlServiceImpl();
        String l_strAcceptResultCode = WEB3SoapResultCodeDef.NORMAL_CANNOT_IN;
        String l_str = l_impl.getSoapTFXAcceptResultCode(l_strAcceptResultCode);

        assertEquals(l_str,WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000000);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetSoapTFXAcceptResultCode_T03()
    {
        final String STR_METHOD_NAME = "testGetSoapTFXAcceptResultCode_T03()";
        log.entering(STR_METHOD_NAME);
 
        WEB3FXDataControlServiceImpl l_impl = new WEB3FXDataControlServiceImpl();
        String l_strAcceptResultCode =  WEB3SoapResultCodeDef.PARAM_ERROR;
        String l_str = l_impl.getSoapTFXAcceptResultCode(l_strAcceptResultCode);

        assertEquals(l_str,WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000609);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetSoapTFXAcceptResultCode_T04()
    {
        final String STR_METHOD_NAME = "testGetSoapTFXAcceptResultCode_T04()";
        
        log.entering(STR_METHOD_NAME);
        
        WEB3FXDataControlServiceImpl l_impl = new WEB3FXDataControlServiceImpl();
        String l_strAcceptResultCode =  WEB3SoapResultCodeDef.USER_CODE_ERROR;
        String l_str = l_impl.getSoapTFXAcceptResultCode(l_strAcceptResultCode);

        assertEquals(l_str,WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000501);
        
        log.exiting(STR_METHOD_NAME);

    }
    
    public void testGetSoapTFXAcceptResultCode_T05()
    {
        final String STR_METHOD_NAME = "testGetSoapTFXAcceptResultCode_T05()";
        
        log.entering(STR_METHOD_NAME);
        
        WEB3FXDataControlServiceImpl l_impl = new WEB3FXDataControlServiceImpl();
        String l_strAcceptResultCode =  WEB3SoapResultCodeDef.REPEAT_ERROR;
        String l_str = l_impl.getSoapTFXAcceptResultCode(l_strAcceptResultCode);

        assertEquals(l_str,WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000801);
        
        
        log.exiting(STR_METHOD_NAME);

    }
    
    public void testGetSoapTFXAcceptResultCode_T06()
    {
        final String STR_METHOD_NAME = "testGetSoapTFXAcceptResultCode_T06()";
        
        log.entering(STR_METHOD_NAME);
        
        WEB3FXDataControlServiceImpl l_impl = new WEB3FXDataControlServiceImpl();
        String l_strAcceptResultCode =  WEB3SoapResultCodeDef.WORK_TIME_OUT_ERROR;
        String l_str = l_impl.getSoapTFXAcceptResultCode(l_strAcceptResultCode);

        assertEquals(l_str,WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000105);
        
        log.exiting(STR_METHOD_NAME);

    }
    /*
    public void testGetSoapTFXAcceptResultCode_T07()
    {
        final String STR_METHOD_NAME = "testGetSoapTFXAcceptResultCode_T07()";
        
        log.entering(STR_METHOD_NAME);
        
        WEB3FXDataControlServiceImpl l_impl = new WEB3FXDataControlServiceImpl();
        String l_strAcceptResultCode = "";
        String l_str = l_impl.getSoapTFXAcceptResultCode(l_strAcceptResultCode);

        assertEquals(l_str,WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000901);
        
        log.exiting(STR_METHOD_NAME);

    }*/
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
    
    protected QuestionAnswerParams getQuestionAnswerParams()
    {
        QuestionAnswerParams l_params = new QuestionAnswerParams();
        //証券会社コード    institution_code    VARCHAR2    3    NotNull
        l_params.setInstitutionCode("0D");
        //部店コード    branch_code    VARCHAR2    3    NotNull
        l_params.setBranchCode("624");
        //質問区分    question_div    VARCHAR2    4    NotNull
        l_params.setQuestionDiv("1001");
        //識別コード    order_request_number    VARCHAR2    9    NotNull
        l_params.setOrderRequestNumber("1234");
        //質問番号    question_no    VARCHAR2    3    NotNull
        l_params.setQuestionNo("101");
        //質問回答    question_answer    VARCHAR2    1    NotNull
        l_params.setQuestionAnswer("1");
        //更新者コード    last_updater    VARCHAR2    20    Null
        //作成日付    created_timestamp    DATE        NotNull
        l_params.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        //更新日付    last_updated_timestamp     DATE        NotNull
        l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        return l_params;
    }
/*
    public void testSendSoapMessage_C0001()
    {
        final String STR_METHOD_NAME = "testSendSoapMessage_C0001()";
        log.entering(STR_METHOD_NAME);

        WEB3FXDataControlServiceImpl l_impl = new WEB3FXDataControlServiceImpl();
        SoapConnectPrefRpcParams l_soapConnectPrefRpcParams =
            new SoapConnectPrefRpcParams();
        l_soapConnectPrefRpcParams.setEndpointName("12;10;11;15;14");

        WEB3FXGftAskingTelegramUnit l_fXGftAskingTelegramUnit =
            new WEB3FXGftAskingTelegramUnit();
        try
        {
            l_impl.sendSoapMessage(l_fXGftAskingTelegramUnit, l_soapConnectPrefRpcParams);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_02398);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testSendSoapMessage_C0002()
    {
        final String STR_METHOD_NAME = "testSendSoapMessage_C0002()";
        log.entering(STR_METHOD_NAME);

        WEB3FXDataControlServiceImpl l_impl = new WEB3FXDataControlServiceImpl();
        SoapConnectPrefRpcParams l_soapConnectPrefRpcParams =
            new SoapConnectPrefRpcParams();
        l_soapConnectPrefRpcParams.setEndpointName("127.0.0.1;");
        l_soapConnectPrefRpcParams.setPortTypeName("124;");

        WEB3FXGftAskingTelegramUnit l_fXGftAskingTelegramUnit =
            new WEB3FXGftAskingTelegramUnit();
        try
        {
            l_impl.sendSoapMessage(l_fXGftAskingTelegramUnit, l_soapConnectPrefRpcParams);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_02398);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testSendSoapMessage_C0003()
    {
        final String STR_METHOD_NAME = "testSendSoapMessage_C0003()";
        log.entering(STR_METHOD_NAME);

        WEB3FXDataControlServiceImpl l_impl = new WEB3FXDataControlServiceImpl();
        SoapConnectPrefRpcParams l_soapConnectPrefRpcParams =
            new SoapConnectPrefRpcParams();
        l_soapConnectPrefRpcParams.setEndpointName("127.0.0.1;");
        l_soapConnectPrefRpcParams.setPortTypeName("d:/testNotFound.txt;test");

        WEB3FXGftAskingTelegramUnit l_fXGftAskingTelegramUnit =
            new WEB3FXGftAskingTelegramUnit();
        try
        {
            l_impl.sendSoapMessage(l_fXGftAskingTelegramUnit, l_soapConnectPrefRpcParams);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_02398);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testSendSoapMessage_C0004()
    {
        final String STR_METHOD_NAME = "testSendSoapMessage_C0004()";
        log.entering(STR_METHOD_NAME);

        WEB3FXDataControlServiceImpl l_impl = new WEB3FXDataControlServiceImpl();
        SoapConnectPrefRpcParams l_soapConnectPrefRpcParams =
            new SoapConnectPrefRpcParams();
        l_soapConnectPrefRpcParams.setEndpointName("127.0.0.1;");
        l_soapConnectPrefRpcParams.setPortTypeName("d:/testFound.txt;test");
        l_soapConnectPrefRpcParams.setOperationName("0");

        WEB3FXGftAskingTelegramUnit l_fXGftAskingTelegramUnit =
            new WEB3FXGftAskingTelegramUnit();
        l_fXGftAskingTelegramUnit.gftOperationDiv = "02";

        try
        {
                l_impl.sendSoapMessage(
                    l_fXGftAskingTelegramUnit,
                    l_soapConnectPrefRpcParams);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_02398);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testSendSoapMessage_C0005()
    {
        final String STR_METHOD_NAME = "testSendSoapMessage_C0005()";
        log.entering(STR_METHOD_NAME);

        WEB3FXDataControlServiceImpl l_impl = new WEB3FXDataControlServiceImpl();
        SoapConnectPrefRpcParams l_soapConnectPrefRpcParams =
            new SoapConnectPrefRpcParams();
        l_soapConnectPrefRpcParams.setEndpointName("127.0.0.1;");
        l_soapConnectPrefRpcParams.setPortTypeName("d:/testFound.txt;" + " ");
        l_soapConnectPrefRpcParams.setOperationName("120");

        WEB3FXGftAskingTelegramUnit l_fXGftAskingTelegramUnit =
            new WEB3FXGftAskingTelegramUnit();
        l_fXGftAskingTelegramUnit.gftOperationDiv = "02";

        try
        {
                l_impl.sendSoapMessage(
                    l_fXGftAskingTelegramUnit,
                    l_soapConnectPrefRpcParams);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_02398);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testSendSoapMessage_C0006()
    {
        final String STR_METHOD_NAME = "testSendSoapMessage_C0006()";
        log.entering(STR_METHOD_NAME);

        WEB3FXDataControlServiceImpl l_impl = new WEB3FXDataControlServiceImpl();
        SoapConnectPrefRpcParams l_soapConnectPrefRpcParams =
            new SoapConnectPrefRpcParams();
        l_soapConnectPrefRpcParams.setEndpointName("127.0.0.1;");
        l_soapConnectPrefRpcParams.setPortTypeName("d:/testFound.txt;test");
        l_soapConnectPrefRpcParams.setOperationName("120");

        WEB3FXGftAskingTelegramUnit l_fXGftAskingTelegramUnit =
            new WEB3FXGftAskingTelegramUnit();
        l_fXGftAskingTelegramUnit.gftOperationDiv = "02";

        try
        {
            String l_strReturnValue =
                l_impl.sendSoapMessage(
                    l_fXGftAskingTelegramUnit,
                    l_soapConnectPrefRpcParams);
            assertNull(l_strReturnValue);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testSendSoapMessage_C0007()
    {
        final String STR_METHOD_NAME = "testSendSoapMessage_C0008()";
        log.entering(STR_METHOD_NAME);

        WEB3FXDataControlServiceImpl l_impl = new WEB3FXDataControlServiceImpl();
        SoapConnectPrefRpcParams l_soapConnectPrefRpcParams =
            new SoapConnectPrefRpcParams();
        l_soapConnectPrefRpcParams.setEndpointName("127.0.0.1;");
        l_soapConnectPrefRpcParams.setPortTypeName("d:/testFound.txt;8080");
        l_soapConnectPrefRpcParams.setOperationName("120");

        WEB3FXGftAskingTelegramUnit l_fXGftAskingTelegramUnit =
            new WEB3FXGftAskingTelegramUnit();
        l_fXGftAskingTelegramUnit.gftOperationDiv = "04";

        try
        {
            String l_strReturnValue =
                l_impl.sendSoapMessage(
                    l_fXGftAskingTelegramUnit,
                    l_soapConnectPrefRpcParams);
            assertNotNull(l_strReturnValue);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testSendSoapMessage_C0008()
    {
        final String STR_METHOD_NAME = "testSendSoapMessage_C0009()";
        log.entering(STR_METHOD_NAME);

        WEB3FXDataControlServiceImpl l_impl = new WEB3FXDataControlServiceImpl();
        SoapConnectPrefRpcParams l_soapConnectPrefRpcParams =
            new SoapConnectPrefRpcParams();
        l_soapConnectPrefRpcParams.setEndpointName("127.0.0.1;8080;12");
        l_soapConnectPrefRpcParams.setPortTypeName("d:/testNotFound.txt;test");

        WEB3FXGftAskingTelegramUnit l_fXGftAskingTelegramUnit =
            new WEB3FXGftAskingTelegramUnit();
        try
        {
            l_impl.sendSoapMessage(l_fXGftAskingTelegramUnit, l_soapConnectPrefRpcParams);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_02398);
            assertEquals(System.getProperty("https.proxyHost"), "127.0.0.1");
            assertEquals(System.getProperty("https.proxyPort"), "8080");
            assertEquals(System.getProperty(
                "weblogic.webservice.transport.https.proxy.host"),
                "127.0.0.1");
            assertEquals(System.getProperty(
                "weblogic.webservice.transport.https.proxy.port"),
                "8080");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
*/

    public void testUpdateGFTAccountOpenStatus_C0001()
    {
    	final String STR_METHOD_NAME = "testUpdateGFTAccountOpenStatus_C0001()";
        log.entering(STR_METHOD_NAME);

        WEB3FXDataControlServiceImpl l_impl = new WEB3FXDataControlServiceImpl();
        try
        {
        	WEB3FXAccInformationUnit[] l_fxAccInformationUnits =
        		new WEB3FXAccInformationUnit[9];
        	WEB3FXAccInformationUnit l_fxAccInformationUnit0 =
        		new WEB3FXAccInformationUnit();
        	l_fxAccInformationUnit0.fxCourseDiv = "1";
        	l_fxAccInformationUnit0.fxAccountCode = "00";
        	l_fxAccInformationUnits[0] = l_fxAccInformationUnit0;
            
        	WEB3FXAccInformationUnit l_fxAccInformationUnit1 =
        		new WEB3FXAccInformationUnit();
        	l_fxAccInformationUnit1.fxCourseDiv = "3";
        	l_fxAccInformationUnit1.fxAccountCode = "02";
        	l_fxAccInformationUnits[1] = l_fxAccInformationUnit1;
            
            WEB3FXAccInformationUnit l_fxAccInformationUnit2 =
                new WEB3FXAccInformationUnit();
            l_fxAccInformationUnit2.fxCourseDiv = "2";
            l_fxAccInformationUnit2.fxAccountCode = "10002";
            l_fxAccInformationUnits[2] = l_fxAccInformationUnit2;
            
            WEB3FXAccInformationUnit l_fxAccInformationUnit3 =
                new WEB3FXAccInformationUnit();
            l_fxAccInformationUnit3.fxCourseDiv = "3";
            l_fxAccInformationUnit3.fxAccountCode = "10003";
            l_fxAccInformationUnits[3] = l_fxAccInformationUnit3;

            WEB3FXAccInformationUnit l_fxAccInformationUnit4 =
                new WEB3FXAccInformationUnit();
            l_fxAccInformationUnit4.fxCourseDiv = "4";
            l_fxAccInformationUnit4.fxAccountCode = "10004";
            l_fxAccInformationUnits[4] = l_fxAccInformationUnit4;

            WEB3FXAccInformationUnit l_fxAccInformationUnit5 =
                new WEB3FXAccInformationUnit();
            l_fxAccInformationUnit5.fxCourseDiv = "5";
            l_fxAccInformationUnit5.fxAccountCode = "10005";
            l_fxAccInformationUnits[5] = l_fxAccInformationUnit5;

            WEB3FXAccInformationUnit l_fxAccInformationUnit6 =
                new WEB3FXAccInformationUnit();
            l_fxAccInformationUnit6.fxCourseDiv = "6";
            l_fxAccInformationUnit6.fxAccountCode = "10006";
            l_fxAccInformationUnits[6] = l_fxAccInformationUnit6;

            WEB3FXAccInformationUnit l_fxAccInformationUnit7 =
                new WEB3FXAccInformationUnit();
            l_fxAccInformationUnit7.fxCourseDiv = "7";
            l_fxAccInformationUnit7.fxAccountCode = "10007";
            l_fxAccInformationUnits[7] = l_fxAccInformationUnit7;

            WEB3FXAccInformationUnit l_fxAccInformationUnit8 =
                new WEB3FXAccInformationUnit();
            l_fxAccInformationUnit8.fxCourseDiv = "8";
            l_fxAccInformationUnit8.fxAccountCode = "10008";
            l_fxAccInformationUnits[8] = l_fxAccInformationUnit8;



        	TestDBUtility.deleteAll(GftAccountOpenStatusRow.TYPE);
        	GftAccountOpenStatusParams l_gftAccountOpenStatusParams =
        		TestDBUtility.getGftAccountOpenStatusRow();
        	TestDBUtility.insertWithDel(l_gftAccountOpenStatusParams);

        	l_impl.updateGFTAccountOpenStatus(
    			l_gftAccountOpenStatusParams,
    			"1",
    			l_fxAccInformationUnits,
    			"1002",
    			"1");

        	QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        	List l_lisResults = l_queryProcessor.doFindAllQuery(GftAccountOpenStatusRow.TYPE);

        	assertEquals(1, l_lisResults.size());
        	assertEquals(((GftAccountOpenStatusRow)l_lisResults.get(0)).getExtAccountCode(), "10003");
//            assertEquals(((GftAccountOpenStatusRow)l_lisResults.get(0)).getExtAccountCode2(), "10002");
//            assertEquals(((GftAccountOpenStatusRow)l_lisResults.get(0)).getExtAccountCode3(), "10003");
//            assertEquals(((GftAccountOpenStatusRow)l_lisResults.get(0)).getExtAccountCode4(), "10004");
//            assertEquals(((GftAccountOpenStatusRow)l_lisResults.get(0)).getExtAccountCode5(), "10005");
//            assertEquals(((GftAccountOpenStatusRow)l_lisResults.get(0)).getExtAccountCode6(), "10006");
//            assertEquals(((GftAccountOpenStatusRow)l_lisResults.get(0)).getExtAccountCode7(), "10007");
//            assertEquals(((GftAccountOpenStatusRow)l_lisResults.get(0)).getExtAccountCode8(), "10008");
        }
        catch (Exception l_ex)
        {
        	log.debug(l_ex.getMessage(), l_ex);
        	log.exiting(STR_METHOD_NAME);
        	fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testUpdateGFTAccountOpenStatus_C0002()
    {
    	final String STR_METHOD_NAME = "testUpdateGFTAccountOpenStatus_C0002()";
        log.entering(STR_METHOD_NAME);

        WEB3FXDataControlServiceImpl l_impl = new WEB3FXDataControlServiceImpl();
        try
        {
        	WEB3FXAccInformationUnit[] l_fxAccInformationUnits =
        		new WEB3FXAccInformationUnit[2];
        	WEB3FXAccInformationUnit l_fxAccInformationUnit0 =
        		new WEB3FXAccInformationUnit();
        	l_fxAccInformationUnit0.fxCourseDiv = "1";
        	l_fxAccInformationUnit0.fxAccountCode = "00";
        	l_fxAccInformationUnits[0] = l_fxAccInformationUnit0;
        	WEB3FXAccInformationUnit l_fxAccInformationUnit1 =
        		new WEB3FXAccInformationUnit();
        	l_fxAccInformationUnit1.fxCourseDiv = "2";
        	l_fxAccInformationUnit1.fxAccountCode = "02";
        	l_fxAccInformationUnits[1] = l_fxAccInformationUnit1;

        	TestDBUtility.deleteAll(GftAccountOpenStatusRow.TYPE);
        	GftAccountOpenStatusParams l_gftAccountOpenStatusParams =
        		TestDBUtility.getGftAccountOpenStatusRow();
        	TestDBUtility.insertWithDel(l_gftAccountOpenStatusParams);

        	l_impl.updateGFTAccountOpenStatus(
    			l_gftAccountOpenStatusParams,
    			"9",
    			l_fxAccInformationUnits,
    			"1002",
    			"10");

        	QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        	List l_lisResults = l_queryProcessor.doFindAllQuery(GftAccountOpenStatusRow.TYPE);

        	assertEquals(1, l_lisResults.size());
        	assertNull(((GftAccountOpenStatusRow)l_lisResults.get(0)).getExtAccountCode());
//            assertNull(((GftAccountOpenStatusRow)l_lisResults.get(0)).getExtAccountCode2());
//            assertNull(((GftAccountOpenStatusRow)l_lisResults.get(0)).getExtAccountCode3());
//            assertNull(((GftAccountOpenStatusRow)l_lisResults.get(0)).getExtAccountCode4());
//            assertNull(((GftAccountOpenStatusRow)l_lisResults.get(0)).getExtAccountCode5());
//            assertNull(((GftAccountOpenStatusRow)l_lisResults.get(0)).getExtAccountCode6());
//            assertNull(((GftAccountOpenStatusRow)l_lisResults.get(0)).getExtAccountCode7());
//            assertNull(((GftAccountOpenStatusRow)l_lisResults.get(0)).getExtAccountCode8());
        }
        catch (Exception l_ex)
        {
        	log.debug(l_ex.getMessage(), l_ex);
        	log.exiting(STR_METHOD_NAME);
        	fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testUpdateGFTAccountOpenStatusThreeParms_C0001()
    {
    	final String STR_METHOD_NAME = "testUpdateGFTAccountOpenStatusThreeParms_C0001()";
        log.entering(STR_METHOD_NAME);

        WEB3FXDataControlServiceImpl l_impl = new WEB3FXDataControlServiceImpl();
        try
        {

            WEB3FXAccInformationUnit[] l_fxAccInformationUnits =
            	new WEB3FXAccInformationUnit[10];
            WEB3FXAccInformationUnit l_fxAccInformationUnit0 =
            	new WEB3FXAccInformationUnit();
            WEB3FXAccInformationUnit l_fxAccInformationUnit1 =
            	new WEB3FXAccInformationUnit();
            WEB3FXAccInformationUnit l_fxAccInformationUnit2 =
            	new WEB3FXAccInformationUnit();
            WEB3FXAccInformationUnit l_fxAccInformationUnit3 =
                new WEB3FXAccInformationUnit();
            WEB3FXAccInformationUnit l_fxAccInformationUnit4 =
                new WEB3FXAccInformationUnit();
            WEB3FXAccInformationUnit l_fxAccInformationUnit5 =
                new WEB3FXAccInformationUnit();
            WEB3FXAccInformationUnit l_fxAccInformationUnit6 =
                new WEB3FXAccInformationUnit();
            WEB3FXAccInformationUnit l_fxAccInformationUnit7 =
                new WEB3FXAccInformationUnit();
            WEB3FXAccInformationUnit l_fxAccInformationUnit8 =
                new WEB3FXAccInformationUnit();
            WEB3FXAccInformationUnit l_fxAccInformationUnit9 =
                new WEB3FXAccInformationUnit();
            
            l_fxAccInformationUnits[0] = l_fxAccInformationUnit0;
            l_fxAccInformationUnits[1] = l_fxAccInformationUnit1;
            l_fxAccInformationUnits[2] = l_fxAccInformationUnit2;
            l_fxAccInformationUnits[3] = l_fxAccInformationUnit3;
            l_fxAccInformationUnits[4] = l_fxAccInformationUnit4;
            l_fxAccInformationUnits[5] = l_fxAccInformationUnit5;
            l_fxAccInformationUnits[6] = l_fxAccInformationUnit6;
            l_fxAccInformationUnits[7] = l_fxAccInformationUnit7;
            l_fxAccInformationUnits[8] = l_fxAccInformationUnit8;
            l_fxAccInformationUnits[9] = l_fxAccInformationUnit9;
            
            l_fxAccInformationUnit0.fxCourseDiv = "3";
            l_fxAccInformationUnit0.fxAccountCode = "987654";
            l_fxAccInformationUnit1.fxCourseDiv = "2";
            l_fxAccInformationUnit1.fxAccountCode = "654321";
            l_fxAccInformationUnit2.fxCourseDiv = "1";
            l_fxAccInformationUnit2.fxAccountCode = "654123";
            l_fxAccInformationUnit3.fxCourseDiv = "2";
            l_fxAccInformationUnit3.fxAccountCode = "10002";
            l_fxAccInformationUnit4.fxCourseDiv = "3";
            l_fxAccInformationUnit4.fxAccountCode = "10003";
            l_fxAccInformationUnit5.fxCourseDiv = "4";
            l_fxAccInformationUnit5.fxAccountCode = "10004";
            l_fxAccInformationUnit6.fxCourseDiv = "5";
            l_fxAccInformationUnit6.fxAccountCode = "10005";
            l_fxAccInformationUnit7.fxCourseDiv = "6";
            l_fxAccInformationUnit7.fxAccountCode = "10006";
            l_fxAccInformationUnit8.fxCourseDiv = "7";
            l_fxAccInformationUnit8.fxAccountCode = "10007";
            l_fxAccInformationUnit9.fxCourseDiv = "8";
            l_fxAccInformationUnit9.fxAccountCode = "10008";

            WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit =
                new WEB3FXGftResultNoticeTelegramUnit();
            l_fxGftResultNoticeTelegramUnit.institutionCode = "0D";
            l_fxGftResultNoticeTelegramUnit.branchCode = "624";
            l_fxGftResultNoticeTelegramUnit.accountCode = "1234567";
            l_fxGftResultNoticeTelegramUnit.gftAcc1 = "321";
            l_fxGftResultNoticeTelegramUnit.gftAcc2 = "456";
            l_fxGftResultNoticeTelegramUnit.fxAccountCode = "001";
            l_fxGftResultNoticeTelegramUnit.fxAccInformationList = l_fxAccInformationUnits;
            
        	TestDBUtility.deleteAll(GftAccountOpenStatusRow.TYPE);
        	GftAccountOpenStatusParams l_gftAccountOpenStatusParams =
        		TestDBUtility.getGftAccountOpenStatusRow();
        	TestDBUtility.insertWithDel(l_gftAccountOpenStatusParams);

        	l_impl.updateGFTAccountOpenStatus(
    			l_gftAccountOpenStatusParams,
    			l_fxGftResultNoticeTelegramUnit,
    			"0000");

        	QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        	List l_lisResults = l_queryProcessor.doFindAllQuery(GftAccountOpenStatusRow.TYPE);

        	assertEquals(1, l_lisResults.size());
        	assertEquals(((GftAccountOpenStatusRow)l_lisResults.get(0)).getExtAccountCode(), "10003");
        	assertEquals(((GftAccountOpenStatusRow)l_lisResults.get(0)).getFxAccountCode10(), "10002");
        	assertEquals(((GftAccountOpenStatusRow)l_lisResults.get(0)).getFxAccountCode01(), "654123");
//            assertEquals(((GftAccountOpenStatusRow)l_lisResults.get(0)).getExtAccountCode2(), "10002");
//            assertEquals(((GftAccountOpenStatusRow)l_lisResults.get(0)).getExtAccountCode3(), "10003");
//            assertEquals(((GftAccountOpenStatusRow)l_lisResults.get(0)).getExtAccountCode4(), "10004");
//            assertEquals(((GftAccountOpenStatusRow)l_lisResults.get(0)).getExtAccountCode5(), "10005");
//            assertEquals(((GftAccountOpenStatusRow)l_lisResults.get(0)).getExtAccountCode6(), "10006");
//            assertEquals(((GftAccountOpenStatusRow)l_lisResults.get(0)).getExtAccountCode7(), "10007");
//            assertEquals(((GftAccountOpenStatusRow)l_lisResults.get(0)).getExtAccountCode8(), "10008");
        }
        catch (Exception l_ex)
        {
        	log.debug(l_ex.getMessage(), l_ex);
        	log.exiting(STR_METHOD_NAME);
        	fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testGetDeliveryDate_C0001()
    {
    	final String STR_METHOD_NAME = "testGetDeliveryDate_C0001()";
        log.entering(STR_METHOD_NAME);

        WEB3FXDataControlService l_impl =
            (WEB3FXDataControlService)Services.getService(
                WEB3FXDataControlService.class);
        try
        {
        	SubAccount l_subAccount = null;
        	String l_strFxSystemDiv = "2";
        	Date l_datOrderBizDate = WEB3DateUtility.getDate("20090318", "yyyyMMdd");
            Date l_datResult =
        	    l_impl.getDeliveryDate(
                    l_datOrderBizDate,
                    l_subAccount,
                    l_strFxSystemDiv);
            Date l_datCheck = WEB3DateUtility.getDate("20090320", "yyyyMMdd");
            assertEquals(l_datCheck, l_datResult);
        }
        catch (Exception l_ex)
        {
        	log.error(l_ex.getMessage(), l_ex);
        	log.exiting(STR_METHOD_NAME);
        	fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testGetDeliveryDate_C0002()
    {
    	final String STR_METHOD_NAME = "testGetDeliveryDate_C0002()";
        log.entering(STR_METHOD_NAME);

        WEB3FXDataControlService l_impl =
            (WEB3FXDataControlService)Services.getService(
                WEB3FXDataControlService.class);
        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setMrfAccOpenDiv("0");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
            TestDBUtility.insertWithDel(l_subAccountParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            SubAccount l_subAccount =
                l_finApp.getAccountManager().getSubAccount(
                    l_subAccountParams.getAccountId(),
                    l_subAccountParams.getSubAccountId());
            
            String l_strFxSystemDiv = "A";
            Date l_datOrderBizDate = WEB3DateUtility.getDate("20090319", "yyyyMMdd");
            Date l_datResult =
                l_impl.getDeliveryDate(
                    l_datOrderBizDate,
                    l_subAccount,
                    l_strFxSystemDiv);
            Date l_datCheck = WEB3DateUtility.getDate("20090319", "yyyyMMdd");
            assertEquals(l_datCheck, l_datResult);
        }
        catch (Exception l_ex)
        {
        	log.error(l_ex.getMessage(), l_ex);
        	log.exiting(STR_METHOD_NAME);
        	fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testGetDeliveryDate_C0003()
    {
        final String STR_METHOD_NAME = "testGetDeliveryDate_C0003()";
        log.entering(STR_METHOD_NAME);

        WEB3FXDataControlService l_impl =
            (WEB3FXDataControlService)Services.getService(
                WEB3FXDataControlService.class);
        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setMrfAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
            TestDBUtility.insertWithDel(l_subAccountParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            SubAccount l_subAccount =
                l_finApp.getAccountManager().getSubAccount(
                    l_subAccountParams.getAccountId(),
                    l_subAccountParams.getSubAccountId());
            
            String l_strFxSystemDiv = "A";
            Date l_datOrderBizDate = WEB3DateUtility.getDate("20090319", "yyyyMMdd");
            Date l_datResult =
                l_impl.getDeliveryDate(
                    l_datOrderBizDate,
                    l_subAccount,
                    l_strFxSystemDiv);
            Date l_datCheck = WEB3DateUtility.getDate("20090320", "yyyyMMdd");
            assertEquals(l_datCheck, l_datResult);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testGetDeliveryDate_C0004()
    {
        final String STR_METHOD_NAME = "testGetDeliveryDate_C0004()";
        log.entering(STR_METHOD_NAME);

        WEB3FXDataControlService l_impl =
            (WEB3FXDataControlService)Services.getService(
                WEB3FXDataControlService.class);
        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setMrfAccOpenDiv("0");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
            TestDBUtility.insertWithDel(l_subAccountParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            SubAccount l_subAccount =
                l_finApp.getAccountManager().getSubAccount(
                    l_subAccountParams.getAccountId(),
                    l_subAccountParams.getSubAccountId());
            
            String l_strFxSystemDiv = "B";
            Date l_datOrderBizDate = WEB3DateUtility.getDate("20090319", "yyyyMMdd");
            Date l_datResult =
                l_impl.getDeliveryDate(
                    l_datOrderBizDate,
                    l_subAccount,
                    l_strFxSystemDiv);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02865, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetSoapTFXAcceptResultCode_C0001()
    {
    	final String STR_METHOD_NAME = "testGetSoapTFXAcceptResultCode_C0001()";
        log.entering(STR_METHOD_NAME);

        String l_strAcceptResultCode = "3007";

        WEB3FXDataControlServiceImpl l_impl = new WEB3FXDataControlServiceImpl();
        String l_strReturn =
        	l_impl.getSoapTFXAcceptResultCode(l_strAcceptResultCode);

        assertEquals(l_strReturn, "00000199");
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateChangePoss_C0001()
    {
    	final String STR_METHOD_NAME = "testValidateChangePoss_C0001()";
        log.entering(STR_METHOD_NAME);

        WEB3FXDataControlService l_impl =
            (WEB3FXDataControlService)Services.getService(
                WEB3FXDataControlService.class);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        AccountManager l_accMgr = l_finApp.getAccountManager();
        try
        {
        	TestDBUtility.deleteAll(MainAccountRow.TYPE);
        	MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo("1");
            l_mainAccountParams.setIfoAccOpenDivOsaka("0");
            l_mainAccountParams.setIfoAccOpenDivNagoya("1");
        	TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
            TestDBUtility.insertWithDel(l_subAccountParams);

        	TestDBUtility.deleteAll(FxAccountRow.TYPE);
            CompFxConditionParams l_compFxConditionParams =
                TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setFxSystemDiv("1");

            SubAccount l_subAccount = l_accMgr.getSubAccount(
                l_subAccountParams.getAccountId(),
                l_subAccountParams.getSubAccountId());

        	l_impl.validateChangePoss(l_subAccount, l_compFxConditionParams);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
        	assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00284);
        }
        catch (Exception l_ex)
        {
        	log.error(l_ex.getMessage(), l_ex);
        	log.exiting(STR_METHOD_NAME);
        	fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateChangePoss_C0002()
    {
        final String STR_METHOD_NAME = "testValidateChangePoss_C0002()";
        log.entering(STR_METHOD_NAME);

        WEB3FXDataControlService l_impl =
            (WEB3FXDataControlService)Services.getService(
                WEB3FXDataControlService.class);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        AccountManager l_accMgr = l_finApp.getAccountManager();
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo("2");
            l_mainAccountParams.setIfoAccOpenDivOsaka("2");
            l_mainAccountParams.setIfoAccOpenDivNagoya("2");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(l_mainAccountParams.getBranchId());
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(FxAccountRow.TYPE);
            CompFxConditionParams l_compFxConditionParams =
                TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setFxSystemDiv("2");
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            l_institutionParams.setInstitutionId(l_mainAccountParams.getInstitutionId());
            TestDBUtility.insertWithDel(l_institutionParams);
            
            
            TestDBUtility.deleteAll(FxAccountParams.TYPE);

            SubAccount l_subAccount = l_accMgr.getSubAccount(
                l_subAccountParams.getAccountId(),
                l_subAccountParams.getSubAccountId());

            l_impl.validateChangePoss(l_subAccount, l_compFxConditionParams);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_01866);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testGetGFTFxMailAddress_C0001()
    {
    	final String STR_METHOD_NAME = "testGetGFTFxMailAddress_C0001()";
    	log.entering(STR_METHOD_NAME);

        WEB3FXDataControlService l_impl =
            (WEB3FXDataControlService)Services.getService(
                WEB3FXDataControlService.class);

    	FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        AccountManager l_accMgr = l_finApp.getAccountManager();
    	try
    	{
    		TestDBUtility.deleteAll(FxAccountRow.TYPE);
    		FxAccountParams l_fxAccountParams =
    			TestDBUtility.getFxAccountRow();
    		l_fxAccountParams.setBranchCode("124");
    		l_fxAccountParams.setAccountCode("102");
    		l_fxAccountParams.setFxSystemCode("02");
    		l_fxAccountParams.setFxMailAddress("1245");
    		TestDBUtility.insertWithDel(l_fxAccountParams);
    		l_fxAccountParams.setBranchCode("124");
    		l_fxAccountParams.setAccountCode("102");
    		l_fxAccountParams.setFxSystemCode("06");
    		l_fxAccountParams.setFxMailAddress("1002");
    		TestDBUtility.insertWithDel(l_fxAccountParams);

    		TestDBUtility.deleteAll(BranchRow.TYPE);
    		BranchParams l_branchParams = TestDBUtility.getBranchRow();
    		l_branchParams.setBranchId(33381L);
    		l_branchParams.setBranchCode("124");
    		TestDBUtility.insertWithDel(l_branchParams);

        	TestDBUtility.deleteAll(MainAccountRow.TYPE);
        	MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        	l_mainAccountParams.setBranchId(1234);
        	l_mainAccountParams.setInstitutionId(123);
        	l_mainAccountParams.setAccountCode("102");
        	l_mainAccountParams.setAccountId(123);
        	l_mainAccountParams.setEmailAddress("jiddk@@126.com");
            l_mainAccountParams.setBranchCode(l_branchParams.getBranchCode());
            l_mainAccountParams.setBranchId(l_branchParams.getBranchId());
        	TestDBUtility.insertWithDel(l_mainAccountParams);

        	TestDBUtility.deleteAll(InstitutionRow.TYPE);
        	InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        	l_institutionParams.setInstitutionId(33);
        	TestDBUtility.insertWithDel(l_institutionParams);

        	TestDBUtility.deleteAll(SubAccountRow.TYPE);
        	SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        	l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        	l_subAccountParams.setAccountId(123);
        	TestDBUtility.insertWithDel(l_subAccountParams);

        	MainAccount l_mainAccount = l_accMgr.getMainAccount(123);
        	SubAccount l_subAccount = l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
    		ArrayList l_lisFxSystemCodes = new ArrayList();
    		l_lisFxSystemCodes.add("01");
    		l_lisFxSystemCodes.add("03");
    		l_lisFxSystemCodes.add("04");

    		String l_strReturn =
    			l_impl.getGFTFxMailAddress(l_subAccount,l_lisFxSystemCodes);

    		assertEquals(l_strReturn, "jiddk@@126.com");
    	}
    	catch (Exception l_ex)
    	{
    		log.error(l_ex.getMessage(), l_ex);
    		log.exiting(STR_METHOD_NAME);
    		fail();
    	}

    	log.exiting(STR_METHOD_NAME);
    }

    public void testGetGFTFxMailAddress_C0002()
    {
    	final String STR_METHOD_NAME = "testGetFxMailAddress_C0002()";
    	log.entering(STR_METHOD_NAME);

        WEB3FXDataControlService l_impl =
            (WEB3FXDataControlService)Services.getService(
                WEB3FXDataControlService.class);

    	FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        AccountManager l_accMgr = l_finApp.getAccountManager();
    	try
    	{
    		TestDBUtility.deleteAll(FxAccountRow.TYPE);
    		FxAccountParams l_fxAccountParams =
    			TestDBUtility.getFxAccountRow();
    		l_fxAccountParams.setFxAccountId(1234);
    		l_fxAccountParams.setBranchCode("124");
    		l_fxAccountParams.setAccountCode("102");
    		l_fxAccountParams.setFxSystemCode("02");
    		l_fxAccountParams.setFxMailAddress("cccc@@wwwwwwwww");
    		TestDBUtility.insertWithDel(l_fxAccountParams);
    		l_fxAccountParams.setFxAccountId(1247);
    		l_fxAccountParams.setBranchCode("124");
    		l_fxAccountParams.setAccountCode("102");
    		l_fxAccountParams.setFxSystemCode("06");
    		l_fxAccountParams.setFxMailAddress("cccc@@wwwwwwwww");
    		TestDBUtility.insertWithDel(l_fxAccountParams);
    		l_fxAccountParams.setFxAccountId(1248);
    		l_fxAccountParams.setBranchCode("124");
    		l_fxAccountParams.setAccountCode("102");
    		l_fxAccountParams.setFxSystemCode("01");
    		l_fxAccountParams.setFxMailAddress("cccc@@wwwwwwwww");
    		TestDBUtility.insertWithDel(l_fxAccountParams);

    		TestDBUtility.deleteAll(BranchRow.TYPE);
    		BranchParams l_branchParams = TestDBUtility.getBranchRow();
    		l_branchParams.setBranchId(33381L);
    		l_branchParams.setBranchCode("124");
    		TestDBUtility.insertWithDel(l_branchParams);

        	TestDBUtility.deleteAll(MainAccountRow.TYPE);
        	MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        	l_mainAccountParams.setBranchId(33381L);
        	l_mainAccountParams.setInstitutionId(123);
        	l_mainAccountParams.setAccountCode("102");
        	l_mainAccountParams.setAccountId(123);
        	l_mainAccountParams.setEmailAddress("0120");
        	TestDBUtility.insertWithDel(l_mainAccountParams);

        	TestDBUtility.deleteAll(InstitutionRow.TYPE);
        	InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        	l_institutionParams.setInstitutionId(33);
        	TestDBUtility.insertWithDel(l_institutionParams);

        	TestDBUtility.deleteAll(SubAccountRow.TYPE);
        	SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        	l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        	l_subAccountParams.setAccountId(123);
        	TestDBUtility.insertWithDel(l_subAccountParams);

        	MainAccount l_mainAccount = l_accMgr.getMainAccount(123);
        	SubAccount l_subAccount = l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
    		ArrayList l_lisFxSystemCodes = new ArrayList();
    		l_lisFxSystemCodes.add("01");
    		l_lisFxSystemCodes.add("02");
    		l_lisFxSystemCodes.add("06");

    		String l_strReturn =
    			l_impl.getGFTFxMailAddress(l_subAccount, l_lisFxSystemCodes);

    		assertEquals(l_strReturn, "cccc@@wwwwwwwww");
    	}
    	catch (Exception l_ex)
    	{
    		log.error(l_ex.getMessage(), l_ex);
    		log.exiting(STR_METHOD_NAME);
    		fail();
    	}

    	log.exiting(STR_METHOD_NAME);
    }

    public void testGetGFTFxMailAddress_C0003()
    {
    	final String STR_METHOD_NAME = "testGetGFTFxMailAddress_C0003()";
    	log.entering(STR_METHOD_NAME);

    	WEB3FXDataControlServiceImpl l_impl = new WEB3FXDataControlServiceImpl();

    	FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        AccountManager l_accMgr = l_finApp.getAccountManager();
    	try
    	{
    		TestDBUtility.deleteAll(FxAccountRow.TYPE);
    		FxAccountParams l_fxAccountParams =
    			TestDBUtility.getFxAccountRow();
    		l_fxAccountParams.setBranchCode("124");
    		l_fxAccountParams.setAccountCode("102");
    		l_fxAccountParams.setFxSystemCode("02");
    		l_fxAccountParams.setFxMailAddress("1245");
    		TestDBUtility.insertWithDel(l_fxAccountParams);
    		l_fxAccountParams.setBranchCode("124");
    		l_fxAccountParams.setAccountCode("102");
    		l_fxAccountParams.setFxSystemCode("06");
    		l_fxAccountParams.setFxMailAddress("1002");
    		TestDBUtility.insertWithDel(l_fxAccountParams);

    		TestDBUtility.deleteAll(BranchRow.TYPE);
    		BranchParams l_branchParams = TestDBUtility.getBranchRow();
    		l_branchParams.setBranchId(33381L);
    		l_branchParams.setBranchCode("140");
    		TestDBUtility.insertWithDel(l_branchParams);

        	TestDBUtility.deleteAll(MainAccountRow.TYPE);
        	MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        	l_mainAccountParams.setBranchId(33381L);
        	l_mainAccountParams.setInstitutionId(123);
        	l_mainAccountParams.setAccountCode("102");
        	l_mainAccountParams.setAccountId(123);
        	l_mainAccountParams.setEmailAddress(null);
        	TestDBUtility.insertWithDel(l_mainAccountParams);

        	TestDBUtility.deleteAll(InstitutionRow.TYPE);
        	InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        	l_institutionParams.setInstitutionId(33);
        	TestDBUtility.insertWithDel(l_institutionParams);

        	TestDBUtility.deleteAll(SubAccountRow.TYPE);
        	SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        	l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        	l_subAccountParams.setAccountId(123);
        	TestDBUtility.insertWithDel(l_subAccountParams);

        	MainAccount l_mainAccount = l_accMgr.getMainAccount(123);
        	SubAccount l_subAccount = l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
    		ArrayList l_lisFxSystemCodes = new ArrayList();
    		l_lisFxSystemCodes.add("01");
    		l_lisFxSystemCodes.add("03");
    		l_lisFxSystemCodes.add("04");

    		String l_strReturn =
    			l_impl.getGFTFxMailAddress(l_subAccount, l_lisFxSystemCodes);

    		assertNull(l_strReturn);
    	}
    	catch (Exception l_ex)
    	{
    		log.error(l_ex.getMessage(), l_ex);
    		log.exiting(STR_METHOD_NAME);
    		fail();
    	}

    	log.exiting(STR_METHOD_NAME);
    }

    public void testGetGFTFxMailAddress_C0004()
    {
    	final String STR_METHOD_NAME = "testGetGFTFxMailAddress_C0004()";
    	log.entering(STR_METHOD_NAME);

    	WEB3FXDataControlServiceImpl l_impl = new WEB3FXDataControlServiceImpl();

    	FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        AccountManager l_accMgr = l_finApp.getAccountManager();
    	try
    	{
    		TestDBUtility.deleteAll(FxAccountRow.TYPE);
    		FxAccountParams l_fxAccountParams =
    			TestDBUtility.getFxAccountRow();
    		l_fxAccountParams.setBranchCode("124");
    		l_fxAccountParams.setAccountCode("102");
    		l_fxAccountParams.setFxSystemCode("02");
    		l_fxAccountParams.setFxMailAddress("1245");
    		TestDBUtility.insertWithDel(l_fxAccountParams);
    		l_fxAccountParams.setBranchCode("124");
    		l_fxAccountParams.setAccountCode("102");
    		l_fxAccountParams.setFxSystemCode("06");
    		l_fxAccountParams.setFxMailAddress("1002");
    		TestDBUtility.insertWithDel(l_fxAccountParams);

    		TestDBUtility.deleteAll(BranchRow.TYPE);
    		BranchParams l_branchParams = TestDBUtility.getBranchRow();
    		l_branchParams.setBranchId(33381L);
    		l_branchParams.setBranchCode("124");
    		TestDBUtility.insertWithDel(l_branchParams);

        	TestDBUtility.deleteAll(MainAccountRow.TYPE);
        	MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        	l_mainAccountParams.setBranchId(33381L);
        	l_mainAccountParams.setInstitutionId(123);
        	l_mainAccountParams.setAccountCode("102");
        	l_mainAccountParams.setAccountId(123);
        	l_mainAccountParams.setEmailAddress("134512");
        	TestDBUtility.insertWithDel(l_mainAccountParams);

        	TestDBUtility.deleteAll(InstitutionRow.TYPE);
        	InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        	l_institutionParams.setInstitutionId(33);
        	TestDBUtility.insertWithDel(l_institutionParams);

        	TestDBUtility.deleteAll(SubAccountRow.TYPE);
        	SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        	l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        	l_subAccountParams.setAccountId(123);
        	TestDBUtility.insertWithDel(l_subAccountParams);

        	MainAccount l_mainAccount = l_accMgr.getMainAccount(123);
        	SubAccount l_subAccount = l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
    		ArrayList l_lisFxSystemCodes = new ArrayList();
    		l_lisFxSystemCodes.add("01");
    		l_lisFxSystemCodes.add("03");
    		l_lisFxSystemCodes.add("04");

    		String l_strReturn =
    			l_impl.getGFTFxMailAddress(l_subAccount, l_lisFxSystemCodes);

    		assertEquals(l_strReturn, "134512");
    	}
    	catch (Exception l_ex)
    	{
    		log.error(l_ex.getMessage(), l_ex);
    		log.exiting(STR_METHOD_NAME);
    		fail();
    	}

    	log.exiting(STR_METHOD_NAME);
    }

    public void testGetGFTFxMailAddress_C0005()
    {
    	final String STR_METHOD_NAME = "testGetGFTFxMailAddress_C0005()";
    	log.entering(STR_METHOD_NAME);

    	WEB3FXDataControlServiceImpl l_impl = new WEB3FXDataControlServiceImpl();

    	FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        AccountManager l_accMgr = l_finApp.getAccountManager();
    	try
    	{
    		TestDBUtility.deleteAll(FxAccountRow.TYPE);
    		FxAccountParams l_fxAccountParams =
    			TestDBUtility.getFxAccountRow();
    		l_fxAccountParams.setBranchCode("124");
    		l_fxAccountParams.setAccountCode("102");
    		l_fxAccountParams.setFxSystemCode("02");
    		l_fxAccountParams.setFxMailAddress("1245");
    		TestDBUtility.insertWithDel(l_fxAccountParams);
    		l_fxAccountParams.setBranchCode("124");
    		l_fxAccountParams.setAccountCode("102");
    		l_fxAccountParams.setFxSystemCode("06");
    		l_fxAccountParams.setFxMailAddress("1002");
    		TestDBUtility.insertWithDel(l_fxAccountParams);

    		TestDBUtility.deleteAll(BranchRow.TYPE);
    		BranchParams l_branchParams = TestDBUtility.getBranchRow();
    		l_branchParams.setBranchId(33381L);
    		l_branchParams.setBranchCode("124");
    		TestDBUtility.insertWithDel(l_branchParams);

        	TestDBUtility.deleteAll(MainAccountRow.TYPE);
        	MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        	l_mainAccountParams.setBranchId(33381L);
        	l_mainAccountParams.setInstitutionId(123);
        	l_mainAccountParams.setAccountCode("102");
        	l_mainAccountParams.setAccountId(123);
        	l_mainAccountParams.setEmailAddress(null);
        	TestDBUtility.insertWithDel(l_mainAccountParams);

        	TestDBUtility.deleteAll(InstitutionRow.TYPE);
        	InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        	l_institutionParams.setInstitutionId(33);
        	TestDBUtility.insertWithDel(l_institutionParams);

        	TestDBUtility.deleteAll(SubAccountRow.TYPE);
        	SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        	l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        	l_subAccountParams.setAccountId(123);
        	TestDBUtility.insertWithDel(l_subAccountParams);

        	MainAccount l_mainAccount = l_accMgr.getMainAccount(123);
        	SubAccount l_subAccount = l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
    		ArrayList l_lisFxSystemCodes = new ArrayList();
    		l_lisFxSystemCodes.add("01");
    		l_lisFxSystemCodes.add("03");
    		l_lisFxSystemCodes.add("04");

    		String l_strReturn =
    			l_impl.getGFTFxMailAddress(l_subAccount, l_lisFxSystemCodes);

    		assertNull(l_strReturn);
    	}
    	catch (Exception l_ex)
    	{
    		log.error(l_ex.getMessage(), l_ex);
    		log.exiting(STR_METHOD_NAME);
    		fail();
    	}

    	log.exiting(STR_METHOD_NAME);
    }

    public void testIsGFTAccOpen_C0001()
    {
    	final String STR_METHOD_NAME = "testIsGFTAccOpen_C0001()";
    	log.entering(STR_METHOD_NAME);

    	WEB3FXDataControlServiceImpl l_impl = new WEB3FXDataControlServiceImpl();
    	try
    	{

        	TestDBUtility.deleteAll(FxAccountRow.TYPE);
        	FxAccountParams l_fxAccountParams = TestDBUtility.getFxAccountRow();
        	l_fxAccountParams.setBranchCode("15");
        	l_fxAccountParams.setAccountCode("102");
        	l_fxAccountParams.setFxSystemCode("10");
        	l_fxAccountParams.setFxAccountDiv("1");
        	TestDBUtility.insertWithDel(l_fxAccountParams);

        	ArrayList l_lisFxSystemCodes = new ArrayList();
        	l_lisFxSystemCodes.add("14");
        	l_lisFxSystemCodes.add("10");
        	l_lisFxSystemCodes.add("05");

        	boolean l_blReturn = l_impl.isGFTAccOpen("0D", "15", "102", l_lisFxSystemCodes);

        	assertTrue(l_blReturn);
    	}
    	catch (Exception l_ex)
    	{
    		log.error(l_ex.getMessage(), l_ex);
    		log.exiting(STR_METHOD_NAME);
    		fail();
    	}
    	log.exiting(STR_METHOD_NAME);
    }


    public void testIsGFTAccOpen_C0002()
    {
    	final String STR_METHOD_NAME = "testIsGFTAccOpen_C0002()";
    	log.entering(STR_METHOD_NAME);

    	WEB3FXDataControlServiceImpl l_impl = new WEB3FXDataControlServiceImpl();
    	try
    	{

        	TestDBUtility.deleteAll(FxAccountRow.TYPE);
        	FxAccountParams l_fxAccountParams = TestDBUtility.getFxAccountRow();
        	l_fxAccountParams.setBranchCode("15");
        	l_fxAccountParams.setAccountCode("102");
        	l_fxAccountParams.setFxSystemCode("18");
        	l_fxAccountParams.setFxAccountDiv("1");
        	TestDBUtility.insertWithDel(l_fxAccountParams);

        	ArrayList l_lisFxSystemCodes = new ArrayList();
        	l_lisFxSystemCodes.add("14");
        	l_lisFxSystemCodes.add("10");
        	l_lisFxSystemCodes.add("05");

        	boolean l_blReturn = l_impl.isGFTAccOpen("0D", "15", "102", l_lisFxSystemCodes);

        	assertFalse(l_blReturn);
    	}
    	catch (Exception l_ex)
    	{
    		log.error(l_ex.getMessage(), l_ex);
    		log.exiting(STR_METHOD_NAME);
    		fail();
    	}
    	log.exiting(STR_METHOD_NAME);
    }

    public void testCreateFXAccInformationUnits_C0001()
    {
    	final String STR_METHOD_NAME = "testCreateFXAccInformationUnits_C0001()";
    	log.entering(STR_METHOD_NAME);

    	WEB3FXDataControlServiceImpl l_impl = new WEB3FXDataControlServiceImpl();

    	WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit =
    		new WEB3FXGftAskingTelegramUnit();
    	l_fxGftAskingTelegramUnit.gftOperationDiv = "01";

    	ResultInfoCreateUser l_resultInfoCreateUser = null;

    	CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
    	l_compFxConditionParams.setFxSystemDiv(null);

        ResultInfoAddAccount l_resultInfoAddAccount = new ResultInfoAddAccount();

    	WEB3FXAccInformationUnit[] l_fxAccInformationUnits =
    		l_impl.createFXAccInformationUnits(
				l_fxGftAskingTelegramUnit,
				l_resultInfoCreateUser,
				null,
				l_compFxConditionParams,
                l_resultInfoAddAccount);

    	assertEquals(l_fxAccInformationUnits.length, 0);
    	log.exiting(STR_METHOD_NAME);
    }

    public void testCreateFXAccInformationUnits_C0002()
    {
    	final String STR_METHOD_NAME = "testCreateFXAccInformationUnits_C0002()";
    	log.entering(STR_METHOD_NAME);

    	WEB3FXDataControlServiceImpl l_impl = new WEB3FXDataControlServiceImpl();

    	WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit =
    		new WEB3FXGftAskingTelegramUnit();
    	l_fxGftAskingTelegramUnit.gftOperationDiv = "01";

    	List<Long> lis = new ArrayList<Long>();
    	lis.add(new Long(120));
    	lis.add(new Long(15));
    	lis.add(new Long(125));
    	long[] l_lngAccountIds = {120,15,125}; 
    	ResultInfoCreateUser l_resultInfoCreateUser =
    		new ResultInfoCreateUser();
    	l_resultInfoCreateUser.setAccountIds(lis); 

    	CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
    	l_compFxConditionParams.setFxSystemDiv(null);

        ResultInfoAddAccount l_resultInfoAddAccount = new ResultInfoAddAccount();
        List<Long> l_lisAccount = new ArrayList<Long>();
        l_lisAccount.add(new Long(1001));
        l_lisAccount.add(new Long(1002));
        l_resultInfoAddAccount.setAccountId(l_lisAccount);
    	WEB3FXAccInformationUnit[] l_fxAccInformationUnits =
    		l_impl.createFXAccInformationUnits(
				l_fxGftAskingTelegramUnit,
				l_resultInfoCreateUser,
				null,
				l_compFxConditionParams,
                l_resultInfoAddAccount);

    	assertEquals(l_fxAccInformationUnits.length,2);
    	log.exiting(STR_METHOD_NAME);
    }

    public void testCreateFXAccInformationUnits_C0003()
    {
    	final String STR_METHOD_NAME = "testCreateFXAccInformationUnits_C0003()";
    	log.entering(STR_METHOD_NAME);

    	WEB3FXDataControlServiceImpl l_impl = new WEB3FXDataControlServiceImpl();

    	WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit =
    		new WEB3FXGftAskingTelegramUnit();
    	l_fxGftAskingTelegramUnit.gftOperationDiv = "01";

    	List<Long> lis = new ArrayList<Long>();
    	lis.add(new Long(120));
    	lis.add(new Long(15));
    	lis.add(new Long(125));
    	long[] l_lngAccountIds = {120,15,125}; 
    	ResultInfoCreateUser l_resultInfoCreateUser =
    		new ResultInfoCreateUser();
    	l_resultInfoCreateUser.setAccountIds(lis); 

    	CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
    	l_compFxConditionParams.setFxSystemDiv(null);

        ResultInfoAddAccount l_resultInfoAddAccount = new ResultInfoAddAccount();

    	WEB3FXAccInformationUnit[] l_fxAccInformationUnits =
    		l_impl.createFXAccInformationUnits(
                l_fxGftAskingTelegramUnit,
                l_resultInfoCreateUser,
                "10",
                l_compFxConditionParams,
                l_resultInfoAddAccount);

    	assertEquals(l_fxAccInformationUnits.length, 3);

    	assertEquals(l_fxAccInformationUnits[0].fxAccountCode, "120");
    	assertEquals(l_fxAccInformationUnits[0].fxCourseDiv, "1");
    	assertEquals(l_fxAccInformationUnits[1].fxAccountCode, "15");
    	assertEquals(l_fxAccInformationUnits[1].fxCourseDiv, "2");
    	assertEquals(l_fxAccInformationUnits[2].fxAccountCode, "125");
    	assertEquals(l_fxAccInformationUnits[2].fxCourseDiv, "3");

    	log.exiting(STR_METHOD_NAME);
    }

    public void testCreateFXAccInformationUnits_C0004()
    {
    	final String STR_METHOD_NAME = "testCreateFXAccInformationUnits_C0004()";
    	log.entering(STR_METHOD_NAME);

    	WEB3FXDataControlServiceImpl l_impl = new WEB3FXDataControlServiceImpl();

    	WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit =
    		new WEB3FXGftAskingTelegramUnit();
    	l_fxGftAskingTelegramUnit.gftOperationDiv = "01";

    	List<Long> lis = new ArrayList<Long>();
    	lis.add(new Long(120));
    	lis.add(new Long(15));
    	lis.add(new Long(125));
    	long[] l_lngAccountIds = {120,15,125}; 
    	ResultInfoCreateUser l_resultInfoCreateUser =
    		new ResultInfoCreateUser();
    	l_resultInfoCreateUser.setAccountIds(lis); 

    	CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
    	l_compFxConditionParams.setFxSystemDiv(null);

        ResultInfoAddAccount l_resultInfoAddAccount = new ResultInfoAddAccount();

    	WEB3FXAccInformationUnit[] l_fxAccInformationUnits =
    		l_impl.createFXAccInformationUnits(
				l_fxGftAskingTelegramUnit,
				l_resultInfoCreateUser, null,
				l_compFxConditionParams,
                l_resultInfoAddAccount);

    	assertEquals(l_fxAccInformationUnits.length, 2);

    	assertEquals(l_fxAccInformationUnits[0].fxAccountCode, "120");
    	assertEquals(l_fxAccInformationUnits[0].fxCourseDiv, "1");
    	assertEquals(l_fxAccInformationUnits[1].fxAccountCode, "15");
    	assertEquals(l_fxAccInformationUnits[1].fxCourseDiv, "2");


    	log.exiting(STR_METHOD_NAME);
    }

    public void testCreateFXAccInformationUnits_C0005()
    {
    	final String STR_METHOD_NAME = "testCreateFXAccInformationUnits_C0005()";
    	log.entering(STR_METHOD_NAME);

    	WEB3FXDataControlServiceImpl l_impl = new WEB3FXDataControlServiceImpl();

    	WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit =
    		new WEB3FXGftAskingTelegramUnit();
    	l_fxGftAskingTelegramUnit.gftOperationDiv = "01";

    	List<Long> lis = new ArrayList<Long>();
    	lis.add(new Long(120));
    	lis.add(new Long(15));
    	lis.add(new Long(125));
    	long[] l_lngAccountIds = {120,15,125}; 
    	ResultInfoCreateUser l_resultInfoCreateUser =
    		new ResultInfoCreateUser();
    	l_resultInfoCreateUser.setAccountIds(lis); 

    	CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
    	l_compFxConditionParams.setFxSystemDiv("2");

        ResultInfoAddAccount l_resultInfoAddAccount = new ResultInfoAddAccount();

    	WEB3FXAccInformationUnit[] l_fxAccInformationUnits =
    		l_impl.createFXAccInformationUnits(
				l_fxGftAskingTelegramUnit,
				l_resultInfoCreateUser, null,
				l_compFxConditionParams,
                l_resultInfoAddAccount);

    	assertEquals(l_fxAccInformationUnits.length, 1);

    	assertEquals(l_fxAccInformationUnits[0].fxAccountCode, "120");
    	assertEquals(l_fxAccInformationUnits[0].fxCourseDiv, "3");

    	log.exiting(STR_METHOD_NAME);
    }

    public void testCreateFXAccInformationUnits_C0006()
    {
    	final String STR_METHOD_NAME = "testCreateFXAccInformationUnits_C0006()";
    	log.entering(STR_METHOD_NAME);

    	WEB3FXDataControlServiceImpl l_impl = new WEB3FXDataControlServiceImpl();

    	WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit =
    		new WEB3FXGftAskingTelegramUnit();
    	l_fxGftAskingTelegramUnit.gftOperationDiv = "04";

    	List<Long> lis = new ArrayList<Long>();
    	lis.add(new Long(120));
    	lis.add(new Long(15));
    	lis.add(new Long(125));
    	long[] l_lngAccountIds = {120,15,125}; 
    	ResultInfoCreateUser l_resultInfoCreateUser =
    		new ResultInfoCreateUser();
    	l_resultInfoCreateUser.setAccountIds(lis); 

    	CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
    	l_compFxConditionParams.setFxSystemDiv("2");

        ResultInfoAddAccount l_resultInfoAddAccount = new ResultInfoAddAccount();

    	WEB3FXAccInformationUnit[] l_fxAccInformationUnits =
    		l_impl.createFXAccInformationUnits(
				l_fxGftAskingTelegramUnit,
				l_resultInfoCreateUser,
				null,
				l_compFxConditionParams,
                l_resultInfoAddAccount);

    	assertEquals(l_fxAccInformationUnits.length, 0);

    	log.exiting(STR_METHOD_NAME);
    }

    public void testCreateFXAccInformationUnits_C0007()
    {
    	final String STR_METHOD_NAME = "testCreateFXAccInformationUnits_C0007()";
    	log.entering(STR_METHOD_NAME);

    	WEB3FXDataControlServiceImpl l_impl = new WEB3FXDataControlServiceImpl();

    	WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit =
    		new WEB3FXGftAskingTelegramUnit();
    	l_fxGftAskingTelegramUnit.gftOperationDiv = "03";

    	List<Long> lis = new ArrayList<Long>();
    	lis.add(new Long(120));
    	lis.add(new Long(15));
    	lis.add(new Long(125));
    	long[] l_lngAccountIds = {120,15,125}; 
    	ResultInfoCreateUser l_resultInfoCreateUser =
    		new ResultInfoCreateUser();
    	l_resultInfoCreateUser.setAccountIds(lis); 

    	CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
    	l_compFxConditionParams.setFxSystemDiv("2");

    	WEB3FXAccInformationUnit[] l_fxAccInformationUnits =
    		l_impl.createFXAccInformationUnits(
				l_fxGftAskingTelegramUnit,
				null,
				null,
				l_compFxConditionParams,
                null);

    	assertEquals(l_fxAccInformationUnits.length, 0);

    	log.exiting(STR_METHOD_NAME);
    }

    public void testCreateFXAccInformationUnits_C0008()
    {
    	final String STR_METHOD_NAME = "testCreateFXAccInformationUnits_C0008()";
    	log.entering(STR_METHOD_NAME);

    	WEB3FXDataControlServiceImpl l_impl = new WEB3FXDataControlServiceImpl();

    	WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit =
    		new WEB3FXGftAskingTelegramUnit();
    	l_fxGftAskingTelegramUnit.gftOperationDiv = "03";

    	ResultInfoCreateUser l_resultInfoCreateUser =
    		new ResultInfoCreateUser();
    	l_resultInfoCreateUser.setAccountIds(null);

    	CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
    	l_compFxConditionParams.setFxSystemDiv("2");

        ResultInfoAddAccount l_resultInfoAddAccount = new ResultInfoAddAccount();
        List<Long> l_lisAccount = new ArrayList<Long>();
        l_lisAccount.add(new Long(1001));
        l_lisAccount.add(new Long(1002));
        l_resultInfoAddAccount.setAccountId(l_lisAccount);
    	WEB3FXAccInformationUnit[] l_fxAccInformationUnits =
    		l_impl.createFXAccInformationUnits(
				l_fxGftAskingTelegramUnit,
				l_resultInfoCreateUser,
				null,
				l_compFxConditionParams,
                l_resultInfoAddAccount);

    	assertEquals(l_fxAccInformationUnits.length, 1);

    	log.exiting(STR_METHOD_NAME);
    }

    public void testCreateFXAccInformationUnits_C0009()
    {
    	final String STR_METHOD_NAME = "testCreateFXAccInformationUnits_C0009()";
    	log.entering(STR_METHOD_NAME);

    	WEB3FXDataControlServiceImpl l_impl = new WEB3FXDataControlServiceImpl();

    	WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit =
    		new WEB3FXGftAskingTelegramUnit();
    	l_fxGftAskingTelegramUnit.gftOperationDiv = "03";

    	List<Long> lis = new ArrayList<Long>();
    	lis.add(new Long(120));
    	lis.add(new Long(15));
    	lis.add(new Long(125));
    	long[] l_lngAccountIds = {120,15,125}; 
    	ResultInfoCreateUser l_resultInfoCreateUser =
    		new ResultInfoCreateUser();
    	l_resultInfoCreateUser.setAccountIds(lis); 

    	CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
    	l_compFxConditionParams.setFxSystemDiv(null);

        ResultInfoAddAccount l_resultInfoAddAccount = new ResultInfoAddAccount();
        List<Long> l_lisAccount = new ArrayList<Long>();
        l_lisAccount.add(new Long(1001));
        l_lisAccount.add(new Long(1002));
        l_resultInfoAddAccount.setAccountId(l_lisAccount);
    	WEB3FXAccInformationUnit[] l_fxAccInformationUnits =
    		l_impl.createFXAccInformationUnits(
				l_fxGftAskingTelegramUnit,
				l_resultInfoCreateUser,
				null,
				l_compFxConditionParams,
                l_resultInfoAddAccount);

    	assertEquals(l_fxAccInformationUnits.length, 2);

    	log.exiting(STR_METHOD_NAME);
    }

    public void testCreateFXAccInformationUnits_C0010()
    {
    	final String STR_METHOD_NAME = "testCreateFXAccInformationUnits_C0010()";
    	log.entering(STR_METHOD_NAME);

    	WEB3FXDataControlServiceImpl l_impl = new WEB3FXDataControlServiceImpl();

    	WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit =
    		new WEB3FXGftAskingTelegramUnit();
    	l_fxGftAskingTelegramUnit.gftOperationDiv = "03";

    	List<Long> lis = new ArrayList<Long>();
    	lis.add(new Long(120));
    	lis.add(new Long(15));
    	lis.add(new Long(125));
    	long[] l_lngAccountIds = {120,15,125}; 
    	ResultInfoCreateUser l_resultInfoCreateUser =
    		new ResultInfoCreateUser();
    	l_resultInfoCreateUser.setAccountIds(lis); 

    	CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
    	l_compFxConditionParams.setFxSystemDiv("2");

        ResultInfoAddAccount l_resultInfoAddAccount = new ResultInfoAddAccount();
        List<Long> l_lisAccount = new ArrayList<Long>();
        l_lisAccount.add(new Long(1001));
        l_lisAccount.add(new Long(1002));
        l_resultInfoAddAccount.setAccountId(l_lisAccount);
    	WEB3FXAccInformationUnit[] l_fxAccInformationUnits =
    		l_impl.createFXAccInformationUnits(
				l_fxGftAskingTelegramUnit,
				l_resultInfoCreateUser,
				null,
				l_compFxConditionParams,
                l_resultInfoAddAccount);

    	assertEquals(l_fxAccInformationUnits.length, 1);

    	log.exiting(STR_METHOD_NAME);
    }

    public void testCreateFXAccInformationUnits_C0011()
    {
    	final String STR_METHOD_NAME = "testCreateFXAccInformationUnits_C0011()";
    	log.entering(STR_METHOD_NAME);

    	WEB3FXDataControlServiceImpl l_impl = new WEB3FXDataControlServiceImpl();

    	WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit =
    		new WEB3FXGftAskingTelegramUnit();
    	l_fxGftAskingTelegramUnit.gftOperationDiv = "03";

    	List<Long> lis = new ArrayList<Long>();
    	lis.add(new Long(120));
    	lis.add(new Long(15));
    	lis.add(new Long(125));
    	long[] l_lngAccountIds = {120,15,125}; 
    	ResultInfoCreateUser l_resultInfoCreateUser =
    		new ResultInfoCreateUser();
    	l_resultInfoCreateUser.setAccountIds(lis); 

    	CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
    	l_compFxConditionParams.setFxSystemDiv("0");

        ResultInfoAddAccount l_resultInfoAddAccount = new ResultInfoAddAccount();

    	WEB3FXAccInformationUnit[] l_fxAccInformationUnits =
    		l_impl.createFXAccInformationUnits(
				l_fxGftAskingTelegramUnit,
				l_resultInfoCreateUser,
				null,
				l_compFxConditionParams,
                l_resultInfoAddAccount);

    	assertEquals(l_fxAccInformationUnits.length, 0);

    	log.exiting(STR_METHOD_NAME);
    }

    public void testGetSameTimeFxSystemCode_C0001()
    {
    	final String STR_METHOD_NAME = "testGetSameTimeFxSystemCode_C0001()";
    	log.entering(STR_METHOD_NAME);

    	WEB3FXDataControlServiceImpl l_impl = new WEB3FXDataControlServiceImpl();
    	try
    	{
	    	TestDBUtility.deleteAll(InstitutionRow.TYPE);
	    	InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
	    	l_institutionParams.setInstitutionCode("123");
	    	TestDBUtility.insertWithDel(l_institutionParams);

	    	l_impl.getSameTimeFxSystemCode("1235");
    	}
    	catch (WEB3BaseException l_ex)
    	{
    		assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.SYSTEM_ERROR_80005);
    	}
    	catch (Exception l_ex)
    	{
    		log.error(l_ex.getMessage(), l_ex);
    		log.exiting(STR_METHOD_NAME);
    		fail();
    	}
    	log.exiting(STR_METHOD_NAME);
    }

    public void testGetSameTimeFxSystemCode_C0002()
    {
    	final String STR_METHOD_NAME = "testGetSameTimeFxSystemCode_C0002()";
    	log.entering(STR_METHOD_NAME);

    	WEB3FXDataControlServiceImpl l_impl = new WEB3FXDataControlServiceImpl();
    	try
    	{
	    	TestDBUtility.deleteAll(InstitutionRow.TYPE);
	    	InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
	    	l_institutionParams.setInstitutionCode("123");
	    	l_institutionParams.setInstitutionId(321);
	    	TestDBUtility.insertWithDel(l_institutionParams);

	    	TestDBUtility.deleteAll(InstitutionPreferencesRow.TYPE);
	    	InstitutionPreferencesParams l_preferencesParams =
	    		TestDBUtility.getInstitutionPreferencesRow();
	    	l_preferencesParams.setInstitutionId(321);
	    	l_preferencesParams.setName("gft.accountopen.fxsystemcode");
	    	l_preferencesParams.setValue("100");
	    	TestDBUtility.insertWithDel(l_preferencesParams);

	    	String l_strReturn = l_impl.getSameTimeFxSystemCode("123");

	    	assertEquals(l_strReturn, "100");
    	}
    	catch (Exception l_ex)
    	{
    		log.error(l_ex.getMessage(), l_ex);
    		log.exiting(STR_METHOD_NAME);
    		fail();
    	}
    	log.exiting(STR_METHOD_NAME);
    }

    public void testGetSameTimeFxSystemCode_C0003()
    {
    	final String STR_METHOD_NAME = "testGetSameTimeFxSystemCode_C0003()";
    	log.entering(STR_METHOD_NAME);

    	WEB3FXDataControlServiceImpl l_impl = new WEB3FXDataControlServiceImpl();
    	try
    	{
	    	TestDBUtility.deleteAll(InstitutionRow.TYPE);
	    	InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
	    	l_institutionParams.setInstitutionCode("123");
	    	l_institutionParams.setInstitutionId(321);
	    	TestDBUtility.insertWithDel(l_institutionParams);

	    	TestDBUtility.deleteAll(InstitutionPreferencesRow.TYPE);
	    	InstitutionPreferencesParams l_preferencesParams =
	    		TestDBUtility.getInstitutionPreferencesRow();
	    	l_preferencesParams.setInstitutionId(322);
	    	l_preferencesParams.setName("gft.accountopen.fxsystemcode");
	    	l_preferencesParams.setValue("100");
	    	TestDBUtility.insertWithDel(l_preferencesParams);

	    	String l_strReturn = l_impl.getSameTimeFxSystemCode("123");

	    	assertNull(l_strReturn);
    	}
    	catch (Exception l_ex)
    	{
    		log.error(l_ex.getMessage(), l_ex);
    		log.exiting(STR_METHOD_NAME);
    		fail();
    	}
    	log.exiting(STR_METHOD_NAME);
    }

    public void testGetGFTFxSystemCodeLists_C0001()
    {
    	final String STR_METHOD_NAME = "testGetGFTFxSystemCodeLists_C0001()";
    	log.entering(STR_METHOD_NAME);

        WEB3FXDataControlService l_impl =
            (WEB3FXDataControlService)Services.getService(
                WEB3FXDataControlService.class);
    	try
    	{
    		TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
    		CompFxConditionParams l_compFxConditionParams =
    			TestDBUtility.getCompFxConditionRow();
    		l_compFxConditionParams.setBranchCode("123");
    		l_compFxConditionParams.setFxSystemCode("04");
    		l_compFxConditionParams.setExtConnectSystemCode("01");
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
    		TestDBUtility.insertWithDel(l_compFxConditionParams);
    		l_compFxConditionParams.setBranchCode("123");
    		l_compFxConditionParams.setFxSystemCode("03");
    		l_compFxConditionParams.setExtConnectSystemCode("01");
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
    		TestDBUtility.insertWithDel(l_compFxConditionParams);
    		l_compFxConditionParams.setBranchCode("123");
    		l_compFxConditionParams.setFxSystemCode("02");
    		l_compFxConditionParams.setExtConnectSystemCode("01");
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
    		TestDBUtility.insertWithDel(l_compFxConditionParams);
    		l_compFxConditionParams.setBranchCode("123");
    		l_compFxConditionParams.setFxSystemCode("01");
    		l_compFxConditionParams.setExtConnectSystemCode("02");
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
    		TestDBUtility.insertWithDel(l_compFxConditionParams);

    		ArrayList l_lisReturn = l_impl.getGFTFxSystemCodeLists("0D", "123");

    		assertEquals(l_lisReturn.size(), 3);
    	}
    	catch (Exception l_ex)
    	{
    		log.error(l_ex.getMessage(), l_ex);
    		log.exiting(STR_METHOD_NAME);
    		fail();
    	}
    	log.exiting(STR_METHOD_NAME);
    }


    public void testGetGFTFxSystemCodeLists_C0002()
    {
    	final String STR_METHOD_NAME = "testGetGFTFxSystemCodeLists_C0002()";
    	log.entering(STR_METHOD_NAME);

        WEB3FXDataControlService l_impl =
            (WEB3FXDataControlService)Services.getService(
                WEB3FXDataControlService.class);
    	try
    	{
    		TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
    		CompFxConditionParams l_compFxConditionParams =
    			TestDBUtility.getCompFxConditionRow();
    		l_compFxConditionParams.setBranchCode("123");
    		l_compFxConditionParams.setFxSystemCode("01");
    		l_compFxConditionParams.setExtConnectSystemCode("02");
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
    		TestDBUtility.insertWithDel(l_compFxConditionParams);
    		l_compFxConditionParams.setBranchCode("123");
    		l_compFxConditionParams.setFxSystemCode("02");
    		l_compFxConditionParams.setExtConnectSystemCode("02");
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
    		TestDBUtility.insertWithDel(l_compFxConditionParams);
    		l_compFxConditionParams.setBranchCode("123");
    		l_compFxConditionParams.setFxSystemCode("03");
    		l_compFxConditionParams.setExtConnectSystemCode("02");
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
    		TestDBUtility.insertWithDel(l_compFxConditionParams);
    		l_compFxConditionParams.setBranchCode("123");
    		l_compFxConditionParams.setFxSystemCode("04");
    		l_compFxConditionParams.setExtConnectSystemCode("01");
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
    		TestDBUtility.insertWithDel(l_compFxConditionParams);

    		ArrayList l_lisReturn = l_impl.getGFTFxSystemCodeLists("0D", "123");

    		assertEquals(l_lisReturn.size(), 1);
    	}
    	catch (Exception l_ex)
    	{
    		log.error(l_ex.getMessage(), l_ex);
    		log.exiting(STR_METHOD_NAME);
    		fail();
    	}
    	log.exiting(STR_METHOD_NAME);
    }

    public void testGetGFTFxSystemCodeLists_C0003()
    {
        final String STR_METHOD_NAME = "testGetGFTFxSystemCodeLists_C0003()";
        log.entering(STR_METHOD_NAME);

        WEB3FXDataControlService l_impl =
            (WEB3FXDataControlService)Services.getService(
                WEB3FXDataControlService.class);
        try
        {
            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            CompFxConditionParams l_compFxConditionParams =
                TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setBranchCode("123");
            l_compFxConditionParams.setFxSystemCode("01");
            l_compFxConditionParams.setExtConnectSystemCode("02");
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
            TestDBUtility.insertWithDel(l_compFxConditionParams);
            l_compFxConditionParams.setBranchCode("123");
            l_compFxConditionParams.setFxSystemCode("02");
            l_compFxConditionParams.setExtConnectSystemCode("02");
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
            TestDBUtility.insertWithDel(l_compFxConditionParams);
            l_compFxConditionParams.setBranchCode("123");
            l_compFxConditionParams.setFxSystemCode("03");
            l_compFxConditionParams.setExtConnectSystemCode("02");
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
            TestDBUtility.insertWithDel(l_compFxConditionParams);
            l_compFxConditionParams.setBranchCode("123");
            l_compFxConditionParams.setFxSystemCode("04");
            l_compFxConditionParams.setExtConnectSystemCode("02");
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
            TestDBUtility.insertWithDel(l_compFxConditionParams);

            ArrayList l_lisReturn = l_impl.getGFTFxSystemCodeLists("0D", "123");

            assertEquals(l_lisReturn.size(), 0);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testＩnsertSimultaneousAccountOpen_C0001()
    {
    	final String STR_METHOD_NAME = "testＩnsertSimultaneousAccountOpen_C0001()";
    	log.entering(STR_METHOD_NAME);

    	WEB3FXDataControlServiceImpl l_impl = new WEB3FXDataControlServiceImpl();

    	WEB3FXGftResultNoticeTelegramUnit l_unit =
    		new WEB3FXGftResultNoticeTelegramUnit();
    	l_unit.institutionCode = "001";
    	l_unit.branchCode = "123";
    	l_unit.accountCode = "021";
    	l_unit.fxLastName = "14512";
    	l_unit.fxFirstName = "0014";
    	l_unit.fxMailAddress = "2350";
    	l_unit.fxFirstLoginId = "1400";

    	GftAccountOpenStatusParams l_gftAccountOpenStatusParams =
    		TestDBUtility.getGftAccountOpenStatusRow();
    	l_gftAccountOpenStatusParams.setFxSystemCode("02");
    	l_gftAccountOpenStatusParams.setLastName("14512");
    	l_gftAccountOpenStatusParams.setFirstName("0014");

    	String l_strSimultaneousFxSystemCode = "01";

    	try
    	{
            TestDBUtility.deleteAll(FxAccountRow.TYPE);

    		TestDBUtility.deleteAll(InstitutionRow.TYPE);
    		InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
    		l_institutionParams.setInstitutionId(1245);
    		TestDBUtility.insertWithDel(l_institutionParams);

    		l_impl.insertSimultaneousAccountOpen(
				l_unit,
				l_gftAccountOpenStatusParams,
				l_strSimultaneousFxSystemCode);

    		String l_strWhere = " fx_account_id = ? ";
    		Object[] l_objValues = {new Long(124562401123456L)};
    		QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
    		List l_lisReturn = l_queryProcessor.doFindAllQuery(
    				FxAccountRow.TYPE,
    				l_strWhere,
    				l_objValues);

    		assertEquals(((FxAccountRow)l_lisReturn.get(0)).getFxAccountId(), 124562401123456L);
    		assertEquals(((FxAccountRow)l_lisReturn.get(0)).getInstitutionCode(), "001");
    		assertEquals(((FxAccountRow)l_lisReturn.get(0)).getBranchCode(), "123");
    		assertEquals(((FxAccountRow)l_lisReturn.get(0)).getFxSystemCode(), "01");
    		assertEquals(((FxAccountRow)l_lisReturn.get(0)).getAccountCode(), "021");
    		assertEquals(((FxAccountRow)l_lisReturn.get(0)).getFxAccountDiv(), "1");
    		assertEquals(((FxAccountRow)l_lisReturn.get(0)).getFxLastName(), "14512");
    		assertEquals(((FxAccountRow)l_lisReturn.get(0)).getFxFirstName(), "0014");
    		assertEquals(((FxAccountRow)l_lisReturn.get(0)).getFxMailAddress(), "2350");
    		assertEquals(((FxAccountRow)l_lisReturn.get(0)).getFxLoginId(), 1400);
    		assertNull(((FxAccountRow)l_lisReturn.get(0)).getLastUpdater());
//    		assertEquals(((FxAccountRow)l_lisReturn.get(0)).getCreatedTimestamp(), GtlUtils.getSystemTimestamp());
//    		assertEquals(((FxAccountRow)l_lisReturn.get(0)).getLastUpdatedTimestamp(), GtlUtils.getSystemTimestamp());
    	}
    	catch (Exception l_ex)
    	{
    		log.error(l_ex.getMessage(), l_ex);
    		log.exiting(STR_METHOD_NAME);
    		fail();
    	}
    	log.exiting(STR_METHOD_NAME);
    }

    public void testＩnsertSimultaneousAccountOpen_C0002()
    {
    	final String STR_METHOD_NAME = "testＩnsertSimultaneousAccountOpen_C0002()";
    	log.entering(STR_METHOD_NAME);

    	WEB3FXDataControlServiceImpl l_impl = new WEB3FXDataControlServiceImpl();

    	WEB3FXGftResultNoticeTelegramUnit l_unit =
    		new WEB3FXGftResultNoticeTelegramUnit();
    	l_unit.institutionCode = "001";
    	l_unit.branchCode = "123";
    	l_unit.accountCode = "021";
    	l_unit.fxLastName = "14512";
    	l_unit.fxFirstName = "0014";
    	l_unit.fxMailAddress = "2350";
    	l_unit.fxFirstLoginId = "1400";

    	GftAccountOpenStatusParams l_gftAccountOpenStatusParams =
    		TestDBUtility.getGftAccountOpenStatusRow();
    	l_gftAccountOpenStatusParams.setFxSystemCode("01");
    	l_gftAccountOpenStatusParams.setLastName("14512");
    	l_gftAccountOpenStatusParams.setFirstName("0014");

    	try
    	{
            TestDBUtility.deleteAll(FxAccountRow.TYPE);

    		TestDBUtility.deleteAll(InstitutionRow.TYPE);
    		InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
    		l_institutionParams.setInstitutionId(1245);
    		TestDBUtility.insertWithDel(l_institutionParams);

    		l_impl.insertSimultaneousAccountOpen(
				l_unit,
				l_gftAccountOpenStatusParams,
				"02");

    		QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
    		String l_strWhere = " fx_account_id = ? ";
    		Object[] l_objValues = {new Long(124562402123456L)};
    		List l_lisReturn = l_queryProcessor.doFindAllQuery(
    				FxAccountRow.TYPE,
    				l_strWhere,
    				l_objValues);

    		assertEquals(l_lisReturn.size(), 1);
    		assertEquals(((FxAccountRow)l_lisReturn.get(0)).getFxAccountId(), 124562402123456L);
    		assertEquals(((FxAccountRow)l_lisReturn.get(0)).getInstitutionCode(), "001");
    		assertEquals(((FxAccountRow)l_lisReturn.get(0)).getBranchCode(), "123");
    		assertEquals(((FxAccountRow)l_lisReturn.get(0)).getFxSystemCode(), "02");
    		assertEquals(((FxAccountRow)l_lisReturn.get(0)).getAccountCode(), "021");
    		assertEquals(((FxAccountRow)l_lisReturn.get(0)).getFxAccountDiv(), "1");
    		assertEquals(((FxAccountRow)l_lisReturn.get(0)).getFxLastName(), "14512");
    		assertEquals(((FxAccountRow)l_lisReturn.get(0)).getFxFirstName(), "0014");
    		assertEquals(((FxAccountRow)l_lisReturn.get(0)).getFxMailAddress(), "2350");
    		assertEquals(((FxAccountRow)l_lisReturn.get(0)).getFxLoginId(), 1400);
    		assertNull(((FxAccountRow)l_lisReturn.get(0)).getLastUpdater());
//    		assertEquals(((FxAccountRow)l_lisReturn.get(0)).getCreatedTimestamp(), GtlUtils.getSystemTimestamp());
//    		assertEquals(((FxAccountRow)l_lisReturn.get(0)).getLastUpdatedTimestamp(), GtlUtils.getSystemTimestamp());
    	}
    	catch (Exception l_ex)
    	{
    		log.error(l_ex.getMessage(), l_ex);
    		log.exiting(STR_METHOD_NAME);
    		fail();
    	}
    	log.exiting(STR_METHOD_NAME);
    }

//
//    public void testGetFXAccountCodes_C0001()
//    {
//    	final String STR_METHOD_NAME = "testGetFXAccountCodes_C0001()";
//    	log.entering(STR_METHOD_NAME);
//
//    	WEB3FXDataControlServiceImpl l_impl = new WEB3FXDataControlServiceImpl();
//    	try
//    	{
//    		TestDBUtility.deleteAll(FxAccountCodeRow.TYPE);
//    		FxAccountCodeParams l_fxAccountCodeParams =
//    			TestDBUtility.getFxAccountCodeRow();
//    		l_fxAccountCodeParams.setBranchCode("123");
//    		l_fxAccountCodeParams.setAccountCode("1245");
//    		l_fxAccountCodeParams.setFxSystemCode("0");
//    		TestDBUtility.insertWithDel(l_fxAccountCodeParams);
//
//    		ArrayList  l_lisFxSystemCodeLists = new ArrayList();
//    		l_lisFxSystemCodeLists.add("0");
//    		l_lisFxSystemCodeLists.add("2");
//
//    		l_impl.getFXAccountCodes("0D", "123", null, l_lisFxSystemCodeLists);
//    		fail();
//    	}
//    	catch (WEB3BaseException l_ex)
//    	{
//    		assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.SYSTEM_ERROR_80017);
//    	}
//    	catch (Exception l_ex)
//    	{
//    		log.debug(l_ex.getMessage(), l_ex);
//    		log.exiting(STR_METHOD_NAME);
//    		fail();
//    	}
//    	log.exiting(STR_METHOD_NAME);
//    }
//
//    public void testGetFXAccountCodes_C0002()
//    {
//    	final String STR_METHOD_NAME = "testGetFXAccountCodes_C0002()";
//    	log.entering(STR_METHOD_NAME);
//
//    	WEB3FXDataControlServiceImpl l_impl = new WEB3FXDataControlServiceImpl();
//    	try
//    	{
//    		TestDBUtility.deleteAll(FxAccountCodeRow.TYPE);
//    		FxAccountCodeParams l_fxAccountCodeParams =
//    			TestDBUtility.getFxAccountCodeRow();
//    		l_fxAccountCodeParams.setBranchCode("123");
//    		l_fxAccountCodeParams.setAccountCode("1245001");
//    		l_fxAccountCodeParams.setFxSystemCode("0");
//    		TestDBUtility.insertWithDel(l_fxAccountCodeParams);
//
//    		ArrayList  l_lisFxSystemCodeLists = new ArrayList();
//    		l_lisFxSystemCodeLists.add("0");
//    		l_lisFxSystemCodeLists.add("2");
//
//    		FxAccountCodeParams[] l_fxAccountCodeParamss =
//    			l_impl.getFXAccountCodes("0D", "123", "124501", l_lisFxSystemCodeLists);
//
//    		assertNull(l_fxAccountCodeParamss);
//    	}
//    	catch (Exception l_ex)
//    	{
//    		log.debug(l_ex.getMessage(), l_ex);
//    		log.exiting(STR_METHOD_NAME);
//    		fail();
//    	}
//    	log.exiting(STR_METHOD_NAME);
//    }
//
//    public void testGetFXAccountCodes_C0003()
//    {
//    	final String STR_METHOD_NAME = "testGetFXAccountCodes_C0003()";
//    	log.entering(STR_METHOD_NAME);
//
//    	WEB3FXDataControlServiceImpl l_impl = new WEB3FXDataControlServiceImpl();
//    	try
//    	{
//    		TestDBUtility.deleteAll(FxAccountCodeRow.TYPE);
//    		FxAccountCodeParams l_fxAccountCodeParams =
//    			TestDBUtility.getFxAccountCodeRow();
//    		l_fxAccountCodeParams.setBranchCode("123");
//    		l_fxAccountCodeParams.setAccountCode("12450");
//    		l_fxAccountCodeParams.setFxSystemCode("04");
//    		l_fxAccountCodeParams.setFxCourseDiv("0");
//    		TestDBUtility.insertWithDel(l_fxAccountCodeParams);
//    		l_fxAccountCodeParams =
//    			TestDBUtility.getFxAccountCodeRow();
//    		l_fxAccountCodeParams.setBranchCode("123");
//    		l_fxAccountCodeParams.setAccountCode("12450");
//    		l_fxAccountCodeParams.setFxSystemCode("01");
//    		l_fxAccountCodeParams.setFxCourseDiv("2");
//    		TestDBUtility.insertWithDel(l_fxAccountCodeParams);
//    		l_fxAccountCodeParams =
//    			TestDBUtility.getFxAccountCodeRow();
//    		l_fxAccountCodeParams.setBranchCode("123");
//    		l_fxAccountCodeParams.setAccountCode("12450");
//    		l_fxAccountCodeParams.setFxSystemCode("01");
//    		l_fxAccountCodeParams.setFxCourseDiv("1");
//    		TestDBUtility.insertWithDel(l_fxAccountCodeParams);
//    		l_fxAccountCodeParams =
//    			TestDBUtility.getFxAccountCodeRow();
//    		l_fxAccountCodeParams.setBranchCode("123");
//    		l_fxAccountCodeParams.setAccountCode("12415");
//    		l_fxAccountCodeParams.setFxSystemCode("04");
//    		l_fxAccountCodeParams.setFxCourseDiv("3");
//    		TestDBUtility.insertWithDel(l_fxAccountCodeParams);
//
//    		ArrayList  l_lisFxSystemCodeLists = new ArrayList();
//    		l_lisFxSystemCodeLists.add("01");
//    		l_lisFxSystemCodeLists.add("04");
//
//    		FxAccountCodeParams[] l_fxAccountCodeParamss =
//    			l_impl.getFXAccountCodes("0D", "123", "12450", l_lisFxSystemCodeLists);
//
//    		assertEquals(l_fxAccountCodeParamss.length, 3);
//
//    		assertEquals(l_fxAccountCodeParamss[0].fx_system_code, "01");
//    		assertEquals(l_fxAccountCodeParamss[0].fx_course_div, "1");
//    		assertEquals(l_fxAccountCodeParamss[1].fx_system_code, "01");
//    		assertEquals(l_fxAccountCodeParamss[1].fx_course_div, "2");
//    		assertEquals(l_fxAccountCodeParamss[2].fx_system_code, "04");
//    		assertEquals(l_fxAccountCodeParamss[2].fx_course_div, "0");
//    	}
//    	catch (Exception l_ex)
//    	{
//    		log.debug(l_ex.getMessage(), l_ex);
//    		log.exiting(STR_METHOD_NAME);
//    		fail();
//    	}
//    	log.exiting(STR_METHOD_NAME);
//    }

    public void testＩnsertFXAccountCode_C0001()
    {
    	final String STR_METHOD_NAME = "testＩnsertFXAccountCode_C0001()";
    	log.entering(STR_METHOD_NAME);

    	WEB3FXDataControlServiceImpl l_impl = new WEB3FXDataControlServiceImpl();
    	try
    	{
    		TestDBUtility.deleteAll(FxAccountCodeRow.TYPE);

    		GftAccountOpenStatusParams l_gftAccountOpenStatusParams =
    			TestDBUtility.getGftAccountOpenStatusRow();
    		l_gftAccountOpenStatusParams.setFxSystemCode("01");
    		l_gftAccountOpenStatusParams.setBranchCode("325");
    		l_gftAccountOpenStatusParams.setAccountCode("6542351");

    		WEB3FXAccInformationUnit l_fXAccInformationUnit =
    			new WEB3FXAccInformationUnit();
    		l_fXAccInformationUnit.fxCourseDiv = "1";
    		l_fXAccInformationUnit.fxAccountCode = "12450";

    		l_impl.insertFXAccountCode(
				l_gftAccountOpenStatusParams,
				l_fXAccInformationUnit,
				"8457",
				"02");

    		QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
    		List l_lisReturn = l_queryProcessor.doFindAllQuery(FxAccountCodeRow.TYPE);

    		assertEquals(l_lisReturn.size(), 1);
    		assertEquals(((FxAccountCodeRow)l_lisReturn.get(0)).getFxSystemCode(), "01");
    		assertEquals(((FxAccountCodeRow)l_lisReturn.get(0)).getInstitutionCode(), "0D");
    		assertEquals(((FxAccountCodeRow)l_lisReturn.get(0)).getBranchCode(), "325");
    		assertEquals(((FxAccountCodeRow)l_lisReturn.get(0)).getAccountCode(), "6542351");
    		assertEquals(((FxAccountCodeRow)l_lisReturn.get(0)).getFxCourseDiv(), "1");
    		assertEquals(((FxAccountCodeRow)l_lisReturn.get(0)).getFxAccountCode(), "12450");
    		assertEquals(((FxAccountCodeRow)l_lisReturn.get(0)).getLastUpdater(), "8457");
    	}
    	catch (Exception l_ex)
    	{
    		log.debug(l_ex.getMessage(), l_ex);
    		log.exiting(STR_METHOD_NAME);
    		fail();
    	}

    	log.exiting(STR_METHOD_NAME);
    }

    public void testＩnsertFXAccountCode_C0002()
    {
    	final String STR_METHOD_NAME = "testＩnsertFXAccountCode_C0002()";
    	log.entering(STR_METHOD_NAME);

    	WEB3FXDataControlServiceImpl l_impl = new WEB3FXDataControlServiceImpl();
    	try
    	{
    		TestDBUtility.deleteAll(FxAccountCodeRow.TYPE);

    		GftAccountOpenStatusParams l_gftAccountOpenStatusParams =
    			TestDBUtility.getGftAccountOpenStatusRow();
    		l_gftAccountOpenStatusParams.setFxSystemCode("01");
    		l_gftAccountOpenStatusParams.setBranchCode("325");
    		l_gftAccountOpenStatusParams.setAccountCode("6542351");

    		WEB3FXAccInformationUnit l_fXAccInformationUnit =
    			new WEB3FXAccInformationUnit();
    		l_fXAccInformationUnit.fxCourseDiv = "2";
    		l_fXAccInformationUnit.fxAccountCode = "12450";

    		l_impl.insertFXAccountCode(
				l_gftAccountOpenStatusParams,
				l_fXAccInformationUnit,
				"8457",
				"02");

    		QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
    		List l_lisReturn = l_queryProcessor.doFindAllQuery(FxAccountCodeRow.TYPE);

    		assertEquals(l_lisReturn.size(), 1);
    		assertEquals(((FxAccountCodeRow)l_lisReturn.get(0)).getFxSystemCode(), "01");
    		assertEquals(((FxAccountCodeRow)l_lisReturn.get(0)).getInstitutionCode(), "0D");
    		assertEquals(((FxAccountCodeRow)l_lisReturn.get(0)).getBranchCode(), "325");
    		assertEquals(((FxAccountCodeRow)l_lisReturn.get(0)).getAccountCode(), "6542351");
    		assertEquals(((FxAccountCodeRow)l_lisReturn.get(0)).getFxCourseDiv(), "2");
    		assertEquals(((FxAccountCodeRow)l_lisReturn.get(0)).getFxAccountCode(), "12450");
    		assertEquals(((FxAccountCodeRow)l_lisReturn.get(0)).getLastUpdater(), "8457");
    	}
    	catch (Exception l_ex)
    	{
    		log.debug(l_ex.getMessage(), l_ex);
    		log.exiting(STR_METHOD_NAME);
    		fail();
    	}

    	log.exiting(STR_METHOD_NAME);
    }

    public void testＩnsertFXAccountCode_C0003()
    {
    	final String STR_METHOD_NAME = "testＩnsertFXAccountCode_C0003()";
    	log.entering(STR_METHOD_NAME);

    	WEB3FXDataControlServiceImpl l_impl = new WEB3FXDataControlServiceImpl();
    	try
    	{
    		TestDBUtility.deleteAll(FxAccountCodeRow.TYPE);

    		GftAccountOpenStatusParams l_gftAccountOpenStatusParams =
    			TestDBUtility.getGftAccountOpenStatusRow();
    		l_gftAccountOpenStatusParams.setFxSystemCode("01");
    		l_gftAccountOpenStatusParams.setBranchCode("325");
    		l_gftAccountOpenStatusParams.setAccountCode("6542351");

    		WEB3FXAccInformationUnit l_fXAccInformationUnit =
    			new WEB3FXAccInformationUnit();
    		l_fXAccInformationUnit.fxCourseDiv = "3";
    		l_fXAccInformationUnit.fxAccountCode = "12450";

    		l_impl.insertFXAccountCode(
				l_gftAccountOpenStatusParams,
				l_fXAccInformationUnit,
				"8457",
				null);

    		QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
    		List l_lisReturn = l_queryProcessor.doFindAllQuery(FxAccountCodeRow.TYPE);

    		assertEquals(l_lisReturn.size(), 1);
    		assertEquals(((FxAccountCodeRow)l_lisReturn.get(0)).getFxSystemCode(), "01");
    		assertEquals(((FxAccountCodeRow)l_lisReturn.get(0)).getInstitutionCode(), "0D");
    		assertEquals(((FxAccountCodeRow)l_lisReturn.get(0)).getBranchCode(), "325");
    		assertEquals(((FxAccountCodeRow)l_lisReturn.get(0)).getAccountCode(), "6542351");
    		assertEquals(((FxAccountCodeRow)l_lisReturn.get(0)).getFxCourseDiv(), "3");
    		assertEquals(((FxAccountCodeRow)l_lisReturn.get(0)).getFxAccountCode(), "12450");
    		assertEquals(((FxAccountCodeRow)l_lisReturn.get(0)).getLastUpdater(), "8457");
    	}
    	catch (Exception l_ex)
    	{
    		log.debug(l_ex.getMessage(), l_ex);
    		log.exiting(STR_METHOD_NAME);
    		fail();
    	}

    	log.exiting(STR_METHOD_NAME);
    }

    public void testＩnsertFXAccountCode_C0004()
    {
    	final String STR_METHOD_NAME = "testＩnsertFXAccountCode_C0004()";
    	log.entering(STR_METHOD_NAME);

    	WEB3FXDataControlServiceImpl l_impl = new WEB3FXDataControlServiceImpl();
    	try
    	{
    		TestDBUtility.deleteAll(FxAccountCodeRow.TYPE);

    		GftAccountOpenStatusParams l_gftAccountOpenStatusParams =
    			TestDBUtility.getGftAccountOpenStatusRow();
    		l_gftAccountOpenStatusParams.setFxSystemCode("01");
    		l_gftAccountOpenStatusParams.setBranchCode("325");
    		l_gftAccountOpenStatusParams.setAccountCode("6542351");

    		WEB3FXAccInformationUnit l_fXAccInformationUnit =
    			new WEB3FXAccInformationUnit();
    		l_fXAccInformationUnit.fxCourseDiv = "3";
    		l_fXAccInformationUnit.fxAccountCode = "12450";

    		l_impl.insertFXAccountCode(
				l_gftAccountOpenStatusParams,
				l_fXAccInformationUnit,
				"8457",
				"02");

    		QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
    		List l_lisReturn = l_queryProcessor.doFindAllQuery(FxAccountCodeRow.TYPE);

    		assertEquals(l_lisReturn.size(), 1);
    		assertEquals(((FxAccountCodeRow)l_lisReturn.get(0)).getFxSystemCode(), "02");
    		assertEquals(((FxAccountCodeRow)l_lisReturn.get(0)).getInstitutionCode(), "0D");
    		assertEquals(((FxAccountCodeRow)l_lisReturn.get(0)).getBranchCode(), "325");
    		assertEquals(((FxAccountCodeRow)l_lisReturn.get(0)).getAccountCode(), "6542351");
    		assertEquals(((FxAccountCodeRow)l_lisReturn.get(0)).getFxCourseDiv(), "3");
    		assertEquals(((FxAccountCodeRow)l_lisReturn.get(0)).getFxAccountCode(), "12450");
    		assertEquals(((FxAccountCodeRow)l_lisReturn.get(0)).getLastUpdater(), "8457");
    	}
    	catch (Exception l_ex)
    	{
    		log.debug(l_ex.getMessage(), l_ex);
    		log.exiting(STR_METHOD_NAME);
    		fail();
    	}

    	log.exiting(STR_METHOD_NAME);
    }

    public void testGetFxTransferMasterParams_Case001()
    {
        final String STR_METHOD_NAME = "testGetFxTransferMasterParams_Case001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(FxTransferMasterParams.TYPE);
            FxTransferMasterParams l_fxTransferMasterParams =
                TestDBUtility.getFxTransferMasterParams();

            WEB3FXDataControlService l_impl =
                (WEB3FXDataControlService)Services.getService(
                    WEB3FXDataControlService.class);
            l_impl.getFxTransferMasterParams(
                l_fxTransferMasterParams.getFxSystemId(),
                l_fxTransferMasterParams.getTransferDiv());
            fail();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }

    public void testGetFxTransferMasterParams_Case002()
    {
        final String STR_METHOD_NAME = "testGetFxTransferMasterParams_Case002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(FxTransferMasterParams.TYPE);
            FxTransferMasterParams l_fxTransferMasterParams =
                TestDBUtility.getFxTransferMasterParams();
            TestDBUtility.insertWithDel(l_fxTransferMasterParams);

            WEB3FXDataControlService l_impl =
                (WEB3FXDataControlService)Services.getService(
                    WEB3FXDataControlService.class);
            FxTransferMasterParams l_result =
                l_impl.getFxTransferMasterParams(
                    l_fxTransferMasterParams.getFxSystemId(),
                    l_fxTransferMasterParams.getTransferDiv());
            assertEquals(l_result.getFxSystemId(),
                l_fxTransferMasterParams.getFxSystemId());
            assertEquals(l_result.getTransferDiv(),
                l_fxTransferMasterParams.getTransferDiv());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    public void testGetFxMailAddress_Case001()
    {
        final String STR_METHOD_NAME = "testGetFxMailAddress_Case001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3FXDataControlService l_impl =
                (WEB3FXDataControlService)Services.getService(
                    WEB3FXDataControlService.class);

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            AccountManager l_accMgr = l_finApp.getAccountManager();
            try
            {
                TestDBUtility.deleteAll(BranchRow.TYPE);
                BranchParams l_branchParams = TestDBUtility.getBranchRow();
                l_branchParams.setBranchId(33381L);
                l_branchParams.setBranchCode("124");
                TestDBUtility.insertWithDel(l_branchParams);

                TestDBUtility.deleteAll(MainAccountRow.TYPE);
                MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
                l_mainAccountParams.setBranchId(1234);
                l_mainAccountParams.setInstitutionId(123);
                l_mainAccountParams.setAccountCode("102");
                l_mainAccountParams.setAccountId(123);
                l_mainAccountParams.setEmailAddress("jiddk@@126.com");
                l_mainAccountParams.setBranchCode(l_branchParams.getBranchCode());
                l_mainAccountParams.setBranchId(l_branchParams.getBranchId());
                TestDBUtility.insertWithDel(l_mainAccountParams);

                TestDBUtility.deleteAll(InstitutionRow.TYPE);
                InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
                l_institutionParams.setInstitutionId(33);
                TestDBUtility.insertWithDel(l_institutionParams);

                TestDBUtility.deleteAll(SubAccountRow.TYPE);
                SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
                l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
                l_subAccountParams.setAccountId(123);
                TestDBUtility.insertWithDel(l_subAccountParams);

                MainAccount l_mainAccount = l_accMgr.getMainAccount(123);
                SubAccount l_subAccount = l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

                String l_strReturn =
                    l_impl.getFxMailAddress(l_subAccount);

                assertEquals(l_strReturn, "jiddk@@126.com");
            }
            catch (Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                fail();
            }
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetFxMailAddress_Case002()
    {
        final String STR_METHOD_NAME = "testGetFxMailAddress_Case002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3FXDataControlService l_impl =
                (WEB3FXDataControlService)Services.getService(
                    WEB3FXDataControlService.class);

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            AccountManager l_accMgr = l_finApp.getAccountManager();
            try
            {
                TestDBUtility.deleteAll(BranchRow.TYPE);
                BranchParams l_branchParams = TestDBUtility.getBranchRow();
                l_branchParams.setBranchId(33381L);
                l_branchParams.setBranchCode("124");
                TestDBUtility.insertWithDel(l_branchParams);

                TestDBUtility.deleteAll(MainAccountRow.TYPE);
                MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
                l_mainAccountParams.setBranchId(1234);
                l_mainAccountParams.setInstitutionId(123);
                l_mainAccountParams.setAccountCode("102");
                l_mainAccountParams.setAccountId(123);
                l_mainAccountParams.setEmailAddress(null);
                l_mainAccountParams.setBranchCode(l_branchParams.getBranchCode());
                l_mainAccountParams.setBranchId(l_branchParams.getBranchId());
                TestDBUtility.insertWithDel(l_mainAccountParams);

                TestDBUtility.deleteAll(InstitutionRow.TYPE);
                InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
                l_institutionParams.setInstitutionId(33);
                TestDBUtility.insertWithDel(l_institutionParams);

                TestDBUtility.deleteAll(SubAccountRow.TYPE);
                SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
                l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
                l_subAccountParams.setAccountId(123);
                TestDBUtility.insertWithDel(l_subAccountParams);

                MainAccount l_mainAccount = l_accMgr.getMainAccount(123);
                SubAccount l_subAccount = l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

                String l_strReturn =
                    l_impl.getFxMailAddress(l_subAccount);

                assertNull(l_strReturn);
            }
            catch (Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                fail();
            }
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetFxMailAddress_Case003()
    {
        final String STR_METHOD_NAME = "testGetFxMailAddress_Case003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3FXDataControlService l_impl =
                (WEB3FXDataControlService)Services.getService(
                    WEB3FXDataControlService.class);

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            AccountManager l_accMgr = l_finApp.getAccountManager();
            try
            {
                TestDBUtility.deleteAll(MainAccountRow.TYPE);

                TestDBUtility.deleteAll(SubAccountRow.TYPE);
                SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
                l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
                l_subAccountParams.setAccountId(123);
                TestDBUtility.insertWithDel(l_subAccountParams);

                SubAccount l_subAccount =
                    l_accMgr.getSubAccount(l_subAccountParams.getAccountId(),
                        l_subAccountParams.getSubAccountId());

                String l_strReturn =
                    l_impl.getFxMailAddress(l_subAccount);
                fail();
            }
            catch (WEB3BaseRuntimeException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.SYSTEM_ERROR_80003);
            }
            catch (Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                fail();
            }
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetFXAccountOpenDiv_Case001()
    {
        final String STR_METHOD_NAME = "testGetFXAccountOpenDiv_Case001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3FXDataControlService l_impl =
                (WEB3FXDataControlService)Services.getService(
                    WEB3FXDataControlService.class);
            String l_strResult = l_impl.getFXAccountOpenDiv("1");
            assertEquals(l_strResult, "1");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetFXAccountOpenDiv_Case002()
    {
        final String STR_METHOD_NAME = "testGetFXAccountOpenDiv_Case002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3FXDataControlService l_impl =
                (WEB3FXDataControlService)Services.getService(
                    WEB3FXDataControlService.class);
            String l_strResult = l_impl.getFXAccountOpenDiv("9");
            assertEquals(l_strResult, "2");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetFXAccountOpenDiv_Case003()
    {
        final String STR_METHOD_NAME = "testGetFXAccountOpenDiv_Case003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3FXDataControlService l_impl =
                (WEB3FXDataControlService)Services.getService(
                    WEB3FXDataControlService.class);
            String l_strResult = l_impl.getFXAccountOpenDiv("2");
            assertEquals(l_strResult, "3");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testUpdateFXAccountMailAddress_Case001()
    {
        final String STR_METHOD_NAME = "testUpdateFXAccountMailAddress_Case001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3FXDataControlService l_impl =
                (WEB3FXDataControlService)Services.getService(
                    WEB3FXDataControlService.class);
            l_impl.updateFXAccountMailAddress(
                null,
                "jiddk@@126.com",
                "123456");
            fail();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.SYSTEM_ERROR_80017);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
        
    }

    public void testUpdateFXAccountMailAddress_Case002()
    {
        final String STR_METHOD_NAME = "testUpdateFXAccountMailAddress_Case002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3FXDataControlService l_impl =
                (WEB3FXDataControlService)Services.getService(
                    WEB3FXDataControlService.class);
            
            TestDBUtility.deleteAll(FxAccountParams.TYPE);
            FxAccountParams l_fxAccountParams =
                TestDBUtility.getFxAccountRow();
            l_fxAccountParams.setFxMailAddress("11111111");
            l_fxAccountParams.setLastUpdater("1111");
            TestDBUtility.insertWithDel(l_fxAccountParams);

            l_impl.updateFXAccountMailAddress(
                l_fxAccountParams,
                "jiddk@@126.com",
                "123456");
            List l_lisResult = Processors.getDefaultProcessor().doFindAllQuery(FxAccountRow.TYPE);
            assertEquals(l_lisResult.size(), 1);
            FxAccountRow l_fxAccountRow =
                (FxAccountRow)l_lisResult.get(0);
            assertEquals(l_fxAccountRow.getFxMailAddress(), "jiddk@@126.com");
            assertEquals(l_fxAccountRow.getLastUpdater(), "123456");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testUpdateFXAccount_Case001()
    {
        final String STR_METHOD_NAME = "testUpdateFXAccount_Case001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3FXDataControlService l_impl =
                (WEB3FXDataControlService)Services.getService(
                    WEB3FXDataControlService.class);
            TestDBUtility.deleteAll(FxAccountParams.TYPE);
            FxAccountParams l_fxAccountParams =
                TestDBUtility.getFxAccountRow();
            l_fxAccountParams.setFxMailAddress("11111111");
            l_fxAccountParams.setFxAccountDiv("2");
            l_fxAccountParams.setLastUpdater("1111");
            TestDBUtility.insertWithDel(l_fxAccountParams);
            l_impl.updateFXAccount(l_fxAccountParams, "1", "123456");
            List l_lisResult = Processors.getDefaultProcessor().doFindAllQuery(FxAccountRow.TYPE);
            assertEquals(l_lisResult.size(), 1);
            FxAccountRow l_fxAccountRow =
                (FxAccountRow)l_lisResult.get(0);
            assertEquals(l_fxAccountRow.getLastUpdater(), "123456");
            assertEquals(l_fxAccountRow.getFxAccountDiv(), "1");
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 【WEB3】【入出金】単体テスト仕様兼報告書 NO:1212
     * 証券会社プリファ@レンス檢索不到數據的情況
     * 証券会社ＩＤ 不滿足檢索條件
     * プリファ@レンス名滿足檢索條件
     * プリファ@レンス名の連番滿足檢索條件
     * TestgetChangedFXLoginID_Case001（）
     */
    public void testgetChangedFXLoginID_Case001()
    {
        final String STR_METHOD_NAME = "testgetChangedFXLoginID_Case001()";
        log.entering(STR_METHOD_NAME);
        
        WEB3FXDataControlService l_impl =
            (WEB3FXDataControlService)Services.getService(
                WEB3FXDataControlService.class);
        try
        {
        	TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
        	InstitutionPreferencesParams l_institutionPreferencesParams =
                TestDBUtility.getInstitutionPreferencesRow();
        	l_institutionPreferencesParams.setName("fx.fxloginid.change.div");
        	 l_institutionPreferencesParams.setNameSerialNo(1);
         	l_institutionPreferencesParams.setValue("53");
        	 TestDBUtility.insertWithDel(l_institutionPreferencesParams);
        	 //証券会社ID：34
        	 //FXシステムコード：53
        	 //FXログインID頭文字：12345678
        	 //FXログインＩＤ：11001
        	long l_lngInstitutionID = 34l;
        	 String l_strFxSystemCode ="53";
        	 String l_strFXLoginFirstChar = "12345678";
        	 long l_lngFXLoginID = 11001l;
        	 
        	 String l_strChangedFXLoginID = 
        		 l_impl.getChangedFXLoginID(l_lngInstitutionID,l_strFxSystemCode,l_strFXLoginFirstChar,l_lngFXLoginID);
        	 
        	 assertEquals("11001",l_strChangedFXLoginID);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * 【WEB3】【入出金】単体テスト仕様兼報告書 NO:1213
     * 証券会社プリファ@レンス檢索不到數據的情況
     * 証券会社ＩＤ 滿足檢索條件
     * プリファ@レンス名不滿足檢索條件
     * プリファ@レンス名の連番滿足檢索條件
     * TestgetChangedFXLoginID_Case002（）
     */
    public void testgetChangedFXLoginID_Case002()
    {
        final String STR_METHOD_NAME = "testgetChangedFXLoginID_Case002()";
        log.entering(STR_METHOD_NAME);
        
        WEB3FXDataControlService l_impl =
            (WEB3FXDataControlService)Services.getService(
                WEB3FXDataControlService.class);
        try
        {
        	TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
        	InstitutionPreferencesParams l_institutionPreferencesParams =
                TestDBUtility.getInstitutionPreferencesRow();
        	l_institutionPreferencesParams.setName("accountinfo.commision.div.check");
        	l_institutionPreferencesParams.setValue("53");
        	 l_institutionPreferencesParams.setNameSerialNo(1);
        	 
        	 //証券会社ID：33
        	 //FXシステムコード：53
        	 //FXログインID頭文字：12345678
        	 //FXログインＩＤ：11001
        	long l_lngInstitutionID = 33l;
        	 String l_strFxSystemCode ="53";
        	 String l_strFXLoginFirstChar = "12345678";
        	 long l_lngFXLoginID = 11001l;
        	 TestDBUtility.insertWithDel(l_institutionPreferencesParams);
        	 String l_strChangedFXLoginID = 
        		 l_impl.getChangedFXLoginID(l_lngInstitutionID,l_strFxSystemCode,l_strFXLoginFirstChar,l_lngFXLoginID);
        	 
        	 assertEquals("11001",l_strChangedFXLoginID);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * 【WEB3】【入出金】単体テスト仕様兼報告書 NO:1214
     * 証券会社プリファ@レンス檢索不到數據的情況
     * 証券会社ＩＤ 滿足檢索條件
     * プリファ@レンス名滿足檢索條件
     * プリファ@レンス名の連番不滿足檢索條件
     * TestgetChangedFXLoginID_Case003（）
     */
    public void testgetChangedFXLoginID_Case003()
    {
        final String STR_METHOD_NAME = "testgetChangedFXLoginID_Case003()";
        log.entering(STR_METHOD_NAME);
        
        WEB3FXDataControlService l_impl =
            (WEB3FXDataControlService)Services.getService(
                WEB3FXDataControlService.class);
        try
        {
        	TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
        	InstitutionPreferencesParams l_institutionPreferencesParams =
                TestDBUtility.getInstitutionPreferencesRow();
        	l_institutionPreferencesParams.setName("fx.fxloginid.change.div");
        	l_institutionPreferencesParams.setValue("53");
        	 l_institutionPreferencesParams.setNameSerialNo(2);
        	 
        	 //証券会社ID：33
        	 //FXシステムコード：53
        	 //FXログインID頭文字：12345678
        	 //FXログインＩＤ：11001
        	long l_lngInstitutionID = 33l;
        	 String l_strFxSystemCode ="53";
        	 String l_strFXLoginFirstChar = "12345678";
        	 long l_lngFXLoginID = 11001l;
        	 TestDBUtility.insertWithDel(l_institutionPreferencesParams);
        	 String l_strChangedFXLoginID = 
        		 l_impl.getChangedFXLoginID(l_lngInstitutionID,l_strFxSystemCode,l_strFXLoginFirstChar,l_lngFXLoginID);
        	 
        	 assertEquals("11001",l_strChangedFXLoginID);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * 【WEB3】【入出金】単体テスト仕様兼報告書 NO:1215
     * 証券会社プリファ@レンス檢索不到數據的情況
     * 表中沒有數據
     * TestgetChangedFXLoginID_Case004（）
     */
    public void testgetChangedFXLoginID_Case004()
    {
        final String STR_METHOD_NAME = "testgetChangedFXLoginID_Case004()";
        log.entering(STR_METHOD_NAME);
        
        WEB3FXDataControlService l_impl =
            (WEB3FXDataControlService)Services.getService(
                WEB3FXDataControlService.class);
        try
        {
        	TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
        	 //証券会社ID：33
        	 //FXシステムコード：53
        	 //FXログインID頭文字：12345678
        	 //FXログインＩＤ：11001
        	long l_lngInstitutionID = 33l;
        	 String l_strFxSystemCode ="53";
        	 String l_strFXLoginFirstChar = "12345678";
        	 long l_lngFXLoginID = 11001l;

        	 String l_strChangedFXLoginID = 
        		 l_impl.getChangedFXLoginID(l_lngInstitutionID,l_strFxSystemCode,l_strFXLoginFirstChar,l_lngFXLoginID);
        	 
        	 assertEquals("11001",l_strChangedFXLoginID);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * 【WEB3】【入出金】単体テスト仕様兼報告書 NO:1216
     * 証券会社プリファ@レンス檢索到數據的情況
     * 証券会社プリファ@レンスParams.プリファ@レンスの値 ！= 引数.FXシステムコードの場合
     */
    public void testgetChangedFXLoginID_Case005()
    {
        final String STR_METHOD_NAME = "testgetChangedFXLoginID_Case005()";
        log.entering(STR_METHOD_NAME);
        
        WEB3FXDataControlService l_impl =
            (WEB3FXDataControlService)Services.getService(
                WEB3FXDataControlService.class);
        try
        {
        	TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
        	InstitutionPreferencesParams l_institutionPreferencesParams =
                TestDBUtility.getInstitutionPreferencesRow();
        	l_institutionPreferencesParams.setName("fx.fxloginid.change.div");
        	l_institutionPreferencesParams.setValue("55");
        	 l_institutionPreferencesParams.setNameSerialNo(1);
        	 
        	 //証券会社ID：33
        	 //FXシステムコード：53
        	 //FXログインID頭文字：12345678
        	 //FXログインＩＤ：11001
        	long l_lngInstitutionID = 33l;
        	 String l_strFxSystemCode ="53";
        	 String l_strFXLoginFirstChar = "12345678";
        	 long l_lngFXLoginID = 11001l;
        	 TestDBUtility.insertWithDel(l_institutionPreferencesParams);
        	 String l_strChangedFXLoginID = 
        		 l_impl.getChangedFXLoginID(l_lngInstitutionID,l_strFxSystemCode,l_strFXLoginFirstChar,l_lngFXLoginID);
        	 
        	 assertEquals("11001",l_strChangedFXLoginID);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * 【WEB3】【入出金】単体テスト仕様兼報告書 NO:1217
     * 証券会社プリファ@レンス檢索到數據的情況
     * 証券会社プリファ@レンスParams.プリファ@レンスの値 ！= 引数.FXシステムコードの場合
     * testgetChangedFXLoginID_Case006()
     */
    public void testgetChangedFXLoginID_Case006()
    {
        final String STR_METHOD_NAME = "testgetChangedFXLoginID_Case006()";
        log.entering(STR_METHOD_NAME);
        
        WEB3FXDataControlService l_impl =
            (WEB3FXDataControlService)Services.getService(
                WEB3FXDataControlService.class);
        try
        {
        	TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
        	InstitutionPreferencesParams l_institutionPreferencesParams =
                TestDBUtility.getInstitutionPreferencesRow();
        	l_institutionPreferencesParams.setName("fx.fxloginid.change.div");
        	l_institutionPreferencesParams.setValue("53");
        	 l_institutionPreferencesParams.setNameSerialNo(1);
        	 
        	 //証券会社ID：33
        	 //FXシステムコード：null
        	 //FXログインID頭文字：12345678
        	 //FXログインＩＤ：11001
        	long l_lngInstitutionID = 33l;
        	 String l_strFxSystemCode =null;
        	 String l_strFXLoginFirstChar = "12345678";
        	 long l_lngFXLoginID = 11001l;
        	 TestDBUtility.insertWithDel(l_institutionPreferencesParams);
        	 String l_strChangedFXLoginID = 
        		 l_impl.getChangedFXLoginID(l_lngInstitutionID,l_strFxSystemCode,l_strFXLoginFirstChar,l_lngFXLoginID);
        	 
        	 assertEquals("11001",l_strChangedFXLoginID);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * 【WEB3】【入出金】単体テスト仕様兼報告書 NO:1218
     * 証券会社プリファ@レンス檢索到數據的情況
     * 証券会社プリファ@レンスParams.プリファ@レンスの値 = 引数.FXシステムコードの場合 
     * testgetChangedFXLoginID_Case007()
     */
    public void testgetChangedFXLoginID_Case007()
    {
        final String STR_METHOD_NAME = "testgetChangedFXLoginID_Case007()";
        log.entering(STR_METHOD_NAME);
        
        WEB3FXDataControlService l_impl =
            (WEB3FXDataControlService)Services.getService(
                WEB3FXDataControlService.class);
        try
        {
        	TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
        	InstitutionPreferencesParams l_institutionPreferencesParams =
                TestDBUtility.getInstitutionPreferencesRow();
        	l_institutionPreferencesParams.setName("fx.fxloginid.change.div");
        	l_institutionPreferencesParams.setValue("53");
        	 l_institutionPreferencesParams.setNameSerialNo(1);
        	 
        	 //証券会社ID：33
        	 //FXシステムコード：53
        	 //FXログインID頭文字：12345678
        	 //FXログインＩＤ：11001
        	long l_lngInstitutionID = 33l;
        	 String l_strFxSystemCode ="53";
        	 String l_strFXLoginFirstChar = "12345678";
        	 long l_lngFXLoginID = 11001l;
        	 TestDBUtility.insertWithDel(l_institutionPreferencesParams);
        	 String l_strChangedFXLoginID = 
        		 l_impl.getChangedFXLoginID(l_lngInstitutionID,l_strFxSystemCode,l_strFXLoginFirstChar,l_lngFXLoginID);
        	 
        	 assertEquals("1234567811001",l_strChangedFXLoginID);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * 【WEB3】【入出金】単体テスト仕様兼報告書 NO:1219
     * 証券会社プリファ@レンス檢索到數據的情況
     * 証券会社プリファ@レンスParams.プリファ@レンスの値 = 引数.FXシステムコードの場合 
     * testgetChangedFXLoginID_Case008()
     */
    public void testgetChangedFXLoginID_Case008()
    {
        final String STR_METHOD_NAME = "testgetChangedFXLoginID_Case008()";
        log.entering(STR_METHOD_NAME);
        
        WEB3FXDataControlService l_impl =
            (WEB3FXDataControlService)Services.getService(
                WEB3FXDataControlService.class);
        try
        {
        	TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
        	InstitutionPreferencesParams l_institutionPreferencesParams =
                TestDBUtility.getInstitutionPreferencesRow();
        	l_institutionPreferencesParams.setName("fx.fxloginid.change.div");
        	l_institutionPreferencesParams.setValue("53");
        	 l_institutionPreferencesParams.setNameSerialNo(1);
        	 
        	 //証券会社ID：33
        	 //FXシステムコード：53
        	 //FXログインID頭文字：12345678
        	 //FXログインＩＤ：110012356
        	long l_lngInstitutionID = 33l;
        	 String l_strFxSystemCode ="53";
        	 String l_strFXLoginFirstChar = "12345678";
        	 long l_lngFXLoginID = 110012356;
        	 TestDBUtility.insertWithDel(l_institutionPreferencesParams);
        	 String l_strChangedFXLoginID = 
        		 l_impl.getChangedFXLoginID(l_lngInstitutionID,l_strFxSystemCode,l_strFXLoginFirstChar,l_lngFXLoginID);
        	 
        	 assertEquals("12345678110012",l_strChangedFXLoginID);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * 【WEB3】【入出金】単体テスト仕様兼報告書 NO:1220
     * 証券会社プリファ@レンス檢索到數據的情況
     * 証券会社プリファ@レンスParams.プリファ@レンスの値 = 引数.FXシステムコードの場合 
     * testgetChangedFXLoginID_Case008()
     */
    public void testgetChangedFXLoginID_Case009()
    {
        final String STR_METHOD_NAME = "testgetChangedFXLoginID_Case009()";
        log.entering(STR_METHOD_NAME);
        
        WEB3FXDataControlService l_impl =
            (WEB3FXDataControlService)Services.getService(
                WEB3FXDataControlService.class);
        try
        {
        	TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
        	InstitutionPreferencesParams l_institutionPreferencesParams =
                TestDBUtility.getInstitutionPreferencesRow();
        	l_institutionPreferencesParams.setName("fx.fxloginid.change.div");
        	l_institutionPreferencesParams.setValue("53");
        	 l_institutionPreferencesParams.setNameSerialNo(1);
        	 
        	 //証券会社ID：33
        	 //FXシステムコード：53
        	 //FXログインID頭文字：12345678
        	 //FXログインＩＤ：0
        	long l_lngInstitutionID = 33l;
        	 String l_strFxSystemCode ="53";
        	 String l_strFXLoginFirstChar = "12345678";
        	 long l_lngFXLoginID = 0;
        	 TestDBUtility.insertWithDel(l_institutionPreferencesParams);
        	 String l_strChangedFXLoginID = 
        		 l_impl.getChangedFXLoginID(l_lngInstitutionID,l_strFxSystemCode,l_strFXLoginFirstChar,l_lngFXLoginID);
        	 
        	 assertEquals("123456780",l_strChangedFXLoginID);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);

    }
    
    public void testInsertGFTMessage_Case001()
    {
        final String STR_METHOD_NAME = "testInsertGFTMessage_Case001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3FXDataControlService l_impl =
                (WEB3FXDataControlService)Services.getService(
                    WEB3FXDataControlService.class);
            WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit =
                new WEB3FXGftAskingTelegramUnit();
            l_fxGftAskingTelegramUnit.address1 = "東京都";
            l_fxGftAskingTelegramUnit.address2 = "江東区";
            l_fxGftAskingTelegramUnit.address3 = "深川５";
            
            TestDBUtility.deleteAll(GftMessageRow.TYPE);
            l_impl.insertGFTMessage(l_fxGftAskingTelegramUnit);
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisResult =
                l_queryProcessor.doFindAllQuery(GftMessageRow.TYPE);
            assertEquals(l_lisResult.size(), 1);
            GftMessageRow l_gftMessageRow = (GftMessageRow)l_lisResult.get(0);
            assertEquals(l_gftMessageRow.getAddress1(), "東京都");
            assertEquals(l_gftMessageRow.getAddress2(), "江東区");
            assertEquals(l_gftMessageRow.getAddress3(), "深川５");
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testInsertGFTMessage_Case002()
    {
        final String STR_METHOD_NAME = "testInsertGFTMessage_Case002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3FXDataControlService l_impl =
                (WEB3FXDataControlService)Services.getService(
                    WEB3FXDataControlService.class);
            WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit =
                new WEB3FXGftAskingTelegramUnit();
            l_fxGftAskingTelegramUnit.address1 = null;
            l_fxGftAskingTelegramUnit.address2 = null;
            l_fxGftAskingTelegramUnit.address3 = null;
            
            TestDBUtility.deleteAll(GftMessageRow.TYPE);
            l_impl.insertGFTMessage(l_fxGftAskingTelegramUnit);
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisResult =
                l_queryProcessor.doFindAllQuery(GftMessageRow.TYPE);
            assertEquals(l_lisResult.size(), 1);
            GftMessageRow l_gftMessageRow = (GftMessageRow)l_lisResult.get(0);
            assertNull(l_gftMessageRow.getAddress1());
            assertNull(l_gftMessageRow.getAddress2());
            assertNull(l_gftMessageRow.getAddress3());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testInsertGFTMessage_Case003()
    {
        final String STR_METHOD_NAME = "testInsertGFTMessage_Case003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3FXDataControlService l_impl =
                (WEB3FXDataControlService)Services.getService(
                    WEB3FXDataControlService.class);
            WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit =
                new WEB3FXGftResultNoticeTelegramUnit();
            
            TestDBUtility.deleteAll(GftMessageRow.TYPE);
            l_impl.insertGFTMessage(l_fxGftResultNoticeTelegramUnit);
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisResult =
                l_queryProcessor.doFindAllQuery(GftMessageRow.TYPE);
            GftMessageRow l_gftMessageRow = (GftMessageRow)l_lisResult.get(0);
            assertEquals(l_lisResult.size(), 1);
            assertNull(l_gftMessageRow.getAddress1());
            assertNull(l_gftMessageRow.getAddress2());
            assertNull(l_gftMessageRow.getAddress3());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testAdminInsertFXAccountCodeSimultaneousCase0001()
    {
        final String STR_METHOD_NAME = "testAdminInsertFXAccountCodeSimultaneousCase0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_processors = Processors.getDefaultProcessor();
            l_processors.doDeleteAllQuery(FxAccountCodeParams.TYPE);
            
            WEB3FXAccInformationUnit[] l_fxAccInformationUnits =
                new WEB3FXAccInformationUnit[1];
            WEB3FXAccInformationUnit l_fxAccInformationUnit0 =
                new WEB3FXAccInformationUnit();
//            WEB3FXAccInformationUnit l_fxAccInformationUnit1 =
//                new WEB3FXAccInformationUnit();
//            WEB3FXAccInformationUnit l_fxAccInformationUnit2 =
//                new WEB3FXAccInformationUnit();
//            WEB3FXAccInformationUnit l_fxAccInformationUnit3 =
//                new WEB3FXAccInformationUnit();
//            WEB3FXAccInformationUnit l_fxAccInformationUnit4 =
//                new WEB3FXAccInformationUnit();
//            WEB3FXAccInformationUnit l_fxAccInformationUnit5 =
//                new WEB3FXAccInformationUnit();
//            WEB3FXAccInformationUnit l_fxAccInformationUnit6 =
//                new WEB3FXAccInformationUnit();
            
            l_fxAccInformationUnits[0] = l_fxAccInformationUnit0;
//            l_fxAccInformationUnits[1] = l_fxAccInformationUnit1;
//            l_fxAccInformationUnits[2] = l_fxAccInformationUnit2;
//            l_fxAccInformationUnits[3] = l_fxAccInformationUnit3;
//            l_fxAccInformationUnits[4] = l_fxAccInformationUnit4;
//            l_fxAccInformationUnits[5] = l_fxAccInformationUnit5;
//            l_fxAccInformationUnits[6] = l_fxAccInformationUnit6;
            
            l_fxAccInformationUnit0.fxCourseDiv = WEB3GftTransStatusCourseDivDef.CFD_COURSE;
            l_fxAccInformationUnit0.fxAccountCode = "10001";
//            l_fxAccInformationUnit1.fxCourseDiv = "4";
//            l_fxAccInformationUnit1.fxAccountCode = "10002";
//            l_fxAccInformationUnit2.fxCourseDiv = "5";
//            l_fxAccInformationUnit2.fxAccountCode = "10003";
//            l_fxAccInformationUnit3.fxCourseDiv = "6";
//            l_fxAccInformationUnit3.fxAccountCode = "10004";
//            l_fxAccInformationUnit4.fxCourseDiv = "7";
//            l_fxAccInformationUnit4.fxAccountCode = "10005";
//            l_fxAccInformationUnit5.fxCourseDiv = "8";
//            l_fxAccInformationUnit5.fxAccountCode = "10006";
//            l_fxAccInformationUnit6.fxCourseDiv = "9";
//            l_fxAccInformationUnit6.fxAccountCode = "10007";
            
            GftAccountOpenStatusParams l_gftAccountOpenStatusParams =
                TestDBUtility.getGftAccountOpenStatusRow();
            l_gftAccountOpenStatusParams.setFxSystemCode("10");
            l_gftAccountOpenStatusParams.setBranchCode("325");
            l_gftAccountOpenStatusParams.setAccountCode("6542351");
            
            WEB3FXDataControlService l_service = new WEB3FXDataControlServiceImpl();
            
            int l_intLength = l_fxAccInformationUnits.length;
            
            for (int i = 0; i < l_intLength; i++)
            {
                l_service.insertFXAccountCode(
                        l_gftAccountOpenStatusParams,
                        l_fxAccInformationUnits[i],
                        "25521",
                        null);
            }
            List l_lisResult = l_processors.doFindAllQuery(FxAccountCodeParams.TYPE);

            assertEquals(1, l_lisResult.size());

            assertEquals("10", ((FxAccountCodeRow)l_lisResult.get(0)).getFxSystemCode());
//            assertEquals("10", ((FxAccountCodeRow)l_lisResult.get(1)).getFxSystemCode());
//            assertEquals("10", ((FxAccountCodeRow)l_lisResult.get(2)).getFxSystemCode());
//            assertEquals("10", ((FxAccountCodeRow)l_lisResult.get(3)).getFxSystemCode());
//            assertEquals("10", ((FxAccountCodeRow)l_lisResult.get(4)).getFxSystemCode());
//            assertEquals("10", ((FxAccountCodeRow)l_lisResult.get(5)).getFxSystemCode());
//            assertEquals("10", ((FxAccountCodeRow)l_lisResult.get(6)).getFxSystemCode());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testAdminInsertFXAccountCodeSimultaneousCase0002()
    {
        final String STR_METHOD_NAME = "testAdminInsertFXAccountCodeSimultaneousCase0002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_processors = Processors.getDefaultProcessor();
            l_processors.doDeleteAllQuery(FxAccountCodeParams.TYPE);
            
            WEB3FXAccInformationUnit[] l_fxAccInformationUnits =
                new WEB3FXAccInformationUnit[1];
            WEB3FXAccInformationUnit l_fxAccInformationUnit0 =
                new WEB3FXAccInformationUnit();
//            WEB3FXAccInformationUnit l_fxAccInformationUnit1 =
//                new WEB3FXAccInformationUnit();
//            WEB3FXAccInformationUnit l_fxAccInformationUnit2 =
//                new WEB3FXAccInformationUnit();
//            WEB3FXAccInformationUnit l_fxAccInformationUnit3 =
//                new WEB3FXAccInformationUnit();
//            WEB3FXAccInformationUnit l_fxAccInformationUnit4 =
//                new WEB3FXAccInformationUnit();
//            WEB3FXAccInformationUnit l_fxAccInformationUnit5 =
//                new WEB3FXAccInformationUnit();
//            WEB3FXAccInformationUnit l_fxAccInformationUnit6 =
//                new WEB3FXAccInformationUnit();
            
            l_fxAccInformationUnits[0] = l_fxAccInformationUnit0;
//            l_fxAccInformationUnits[1] = l_fxAccInformationUnit1;
//            l_fxAccInformationUnits[2] = l_fxAccInformationUnit2;
//            l_fxAccInformationUnits[3] = l_fxAccInformationUnit3;
//            l_fxAccInformationUnits[4] = l_fxAccInformationUnit4;
//            l_fxAccInformationUnits[5] = l_fxAccInformationUnit5;
//            l_fxAccInformationUnits[6] = l_fxAccInformationUnit6;
            
            l_fxAccInformationUnit0.fxCourseDiv = WEB3GftTransStatusCourseDivDef.CFD_COURSE;
            l_fxAccInformationUnit0.fxAccountCode = "10001";
//            l_fxAccInformationUnit1.fxCourseDiv = "4";
//            l_fxAccInformationUnit1.fxAccountCode = "10002";
//            l_fxAccInformationUnit2.fxCourseDiv = "5";
//            l_fxAccInformationUnit2.fxAccountCode = "10003";
//            l_fxAccInformationUnit3.fxCourseDiv = "6";
//            l_fxAccInformationUnit3.fxAccountCode = "10004";
//            l_fxAccInformationUnit4.fxCourseDiv = "7";
//            l_fxAccInformationUnit4.fxAccountCode = "10005";
//            l_fxAccInformationUnit5.fxCourseDiv = "8";
//            l_fxAccInformationUnit5.fxAccountCode = "10006";
//            l_fxAccInformationUnit6.fxCourseDiv = "9";
//            l_fxAccInformationUnit6.fxAccountCode = "10007";
            
            GftAccountOpenStatusParams l_gftAccountOpenStatusParams =
                TestDBUtility.getGftAccountOpenStatusRow();
            l_gftAccountOpenStatusParams.setFxSystemCode("10");
            l_gftAccountOpenStatusParams.setBranchCode("325");
            l_gftAccountOpenStatusParams.setAccountCode("6542351");
            
            WEB3FXDataControlService l_service = new WEB3FXDataControlServiceImpl();
            
            int l_intLength = l_fxAccInformationUnits.length;
            
            for (int i = 0; i < l_intLength; i++)
            {
                l_service.insertFXAccountCode(
                        l_gftAccountOpenStatusParams,
                        l_fxAccInformationUnits[i],
                        "25521",
                        "01");
            }
            List l_lisResult = l_processors.doFindAllQuery(FxAccountCodeParams.TYPE);

            assertEquals(1, l_lisResult.size());

            assertEquals("01", ((FxAccountCodeRow)l_lisResult.get(0)).getFxSystemCode());
//            assertEquals("01", ((FxAccountCodeRow)l_lisResult.get(1)).getFxSystemCode());
//            assertEquals("01", ((FxAccountCodeRow)l_lisResult.get(2)).getFxSystemCode());
//            assertEquals("01", ((FxAccountCodeRow)l_lisResult.get(3)).getFxSystemCode());
//            assertEquals("01", ((FxAccountCodeRow)l_lisResult.get(4)).getFxSystemCode());
//            assertEquals("01", ((FxAccountCodeRow)l_lisResult.get(5)).getFxSystemCode());
//            assertEquals("01", ((FxAccountCodeRow)l_lisResult.get(6)).getFxSystemCode());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

}
@


1.2
log
@*** empty log message ***
@
text
@d34 2
d407 7
a413 7
            assertNull(((GftAccountOpenStatusRow)l_lisResult.get(0)).getExtAccountCode2());
            assertNull(((GftAccountOpenStatusRow)l_lisResult.get(0)).getExtAccountCode3());
            assertNull(((GftAccountOpenStatusRow)l_lisResult.get(0)).getExtAccountCode4());
            assertNull(((GftAccountOpenStatusRow)l_lisResult.get(0)).getExtAccountCode5());
            assertNull(((GftAccountOpenStatusRow)l_lisResult.get(0)).getExtAccountCode6());
            assertNull(((GftAccountOpenStatusRow)l_lisResult.get(0)).getExtAccountCode7());
            assertNull(((GftAccountOpenStatusRow)l_lisResult.get(0)).getExtAccountCode8());
d630 1
a630 1
            	new WEB3FXAccInformationUnit[10];
d633 18
a650 18
            WEB3FXAccInformationUnit l_fxAccInformationUnit1 =
            	new WEB3FXAccInformationUnit();
            WEB3FXAccInformationUnit l_fxAccInformationUnit2 =
            	new WEB3FXAccInformationUnit();
            WEB3FXAccInformationUnit l_fxAccInformationUnit3 =
                new WEB3FXAccInformationUnit();
            WEB3FXAccInformationUnit l_fxAccInformationUnit4 =
                new WEB3FXAccInformationUnit();
            WEB3FXAccInformationUnit l_fxAccInformationUnit5 =
                new WEB3FXAccInformationUnit();
            WEB3FXAccInformationUnit l_fxAccInformationUnit6 =
                new WEB3FXAccInformationUnit();
            WEB3FXAccInformationUnit l_fxAccInformationUnit7 =
                new WEB3FXAccInformationUnit();
            WEB3FXAccInformationUnit l_fxAccInformationUnit8 =
                new WEB3FXAccInformationUnit();
            WEB3FXAccInformationUnit l_fxAccInformationUnit9 =
                new WEB3FXAccInformationUnit();
d653 9
a661 9
            l_fxAccInformationUnits[1] = l_fxAccInformationUnit1;
            l_fxAccInformationUnits[2] = l_fxAccInformationUnit2;
            l_fxAccInformationUnits[3] = l_fxAccInformationUnit3;
            l_fxAccInformationUnits[4] = l_fxAccInformationUnit4;
            l_fxAccInformationUnits[5] = l_fxAccInformationUnit5;
            l_fxAccInformationUnits[6] = l_fxAccInformationUnit6;
            l_fxAccInformationUnits[7] = l_fxAccInformationUnit7;
            l_fxAccInformationUnits[8] = l_fxAccInformationUnit8;
            l_fxAccInformationUnits[9] = l_fxAccInformationUnit9;
d665 18
a682 18
            l_fxAccInformationUnit1.fxCourseDiv = "2";
            l_fxAccInformationUnit1.fxAccountCode = "654321";
            l_fxAccInformationUnit2.fxCourseDiv = "1";
            l_fxAccInformationUnit2.fxAccountCode = "654123";
            l_fxAccInformationUnit3.fxCourseDiv = "4";
            l_fxAccInformationUnit3.fxAccountCode = "654123";
            l_fxAccInformationUnit4.fxCourseDiv = "5";
            l_fxAccInformationUnit4.fxAccountCode = "654123";
            l_fxAccInformationUnit5.fxCourseDiv = "6";
            l_fxAccInformationUnit5.fxAccountCode = "654123";
            l_fxAccInformationUnit6.fxCourseDiv = "7";
            l_fxAccInformationUnit6.fxAccountCode = "654123";
            l_fxAccInformationUnit7.fxCourseDiv = "8";
            l_fxAccInformationUnit7.fxAccountCode = "654123";
            l_fxAccInformationUnit8.fxCourseDiv = "9";
            l_fxAccInformationUnit8.fxAccountCode = "654123";
            l_fxAccInformationUnit9.fxCourseDiv = "10";
            l_fxAccInformationUnit9.fxAccountCode = "654123";
d697 1
a697 1
            assertEquals(10, l_lisResult.size());
d705 25
a729 25
            
            assertEquals("0D", ((FxAccountCodeRow)l_lisResult.get(1)).getInstitutionCode());
            assertEquals("624", ((FxAccountCodeRow)l_lisResult.get(1)).getBranchCode());
            assertEquals("11", ((FxAccountCodeRow)l_lisResult.get(1)).getFxSystemCode());
            assertEquals("2", ((FxAccountCodeRow)l_lisResult.get(1)).getFxCourseDiv());
            assertEquals("1234567", ((FxAccountCodeRow)l_lisResult.get(1)).getAccountCode());
            assertNull(((FxAccountCodeRow)l_lisResult.get(1)).getLastUpdater());
            assertEquals("654321", ((FxAccountCodeRow)l_lisResult.get(1)).getFxAccountCode());


            assertEquals("0D", ((FxAccountCodeRow)l_lisResult.get(2)).getInstitutionCode());
            assertEquals("624", ((FxAccountCodeRow)l_lisResult.get(2)).getBranchCode());
            assertEquals("11", ((FxAccountCodeRow)l_lisResult.get(2)).getFxSystemCode());
            assertEquals("1", ((FxAccountCodeRow)l_lisResult.get(2)).getFxCourseDiv());
            assertEquals("1234567", ((FxAccountCodeRow)l_lisResult.get(2)).getAccountCode());
            assertNull(((FxAccountCodeRow)l_lisResult.get(2)).getLastUpdater());
            assertEquals("654123", ((FxAccountCodeRow)l_lisResult.get(2)).getFxAccountCode());
            
            assertEquals("10", ((FxAccountCodeRow)l_lisResult.get(3)).getFxSystemCode());
            assertEquals("10", ((FxAccountCodeRow)l_lisResult.get(4)).getFxSystemCode());
            assertEquals("10", ((FxAccountCodeRow)l_lisResult.get(5)).getFxSystemCode());
            assertEquals("10", ((FxAccountCodeRow)l_lisResult.get(6)).getFxSystemCode());
            assertEquals("10", ((FxAccountCodeRow)l_lisResult.get(7)).getFxSystemCode());
            assertEquals("10", ((FxAccountCodeRow)l_lisResult.get(8)).getFxSystemCode());
            assertEquals("10", ((FxAccountCodeRow)l_lisResult.get(9)).getFxSystemCode());
d1854 1
a1854 1
            l_fxAccInformationUnit2.fxCourseDiv = WEB3GftTransStatusCourseDivDef.CFD_COURSE_2;
d1860 1
a1860 1
            l_fxAccInformationUnit3.fxCourseDiv = WEB3GftTransStatusCourseDivDef.CFD_COURSE_3;
d1866 1
a1866 1
            l_fxAccInformationUnit4.fxCourseDiv = WEB3GftTransStatusCourseDivDef.CFD_COURSE_4;
d1872 1
a1872 1
            l_fxAccInformationUnit5.fxCourseDiv = WEB3GftTransStatusCourseDivDef.CFD_COURSE_5;
d1878 1
a1878 1
            l_fxAccInformationUnit6.fxCourseDiv = WEB3GftTransStatusCourseDivDef.CFD_COURSE_6;
d1884 1
a1884 1
            l_fxAccInformationUnit7.fxCourseDiv = WEB3GftTransStatusCourseDivDef.CFD_COURSE_7;
d1890 1
a1890 1
            l_fxAccInformationUnit8.fxCourseDiv = WEB3GftTransStatusCourseDivDef.CFD_COURSE_8;
d1912 8
a1919 8
        	assertEquals(((GftAccountOpenStatusRow)l_lisResults.get(0)).getExtAccountCode(), "02");
            assertEquals(((GftAccountOpenStatusRow)l_lisResults.get(0)).getExtAccountCode2(), "10002");
            assertEquals(((GftAccountOpenStatusRow)l_lisResults.get(0)).getExtAccountCode3(), "10003");
            assertEquals(((GftAccountOpenStatusRow)l_lisResults.get(0)).getExtAccountCode4(), "10004");
            assertEquals(((GftAccountOpenStatusRow)l_lisResults.get(0)).getExtAccountCode5(), "10005");
            assertEquals(((GftAccountOpenStatusRow)l_lisResults.get(0)).getExtAccountCode6(), "10006");
            assertEquals(((GftAccountOpenStatusRow)l_lisResults.get(0)).getExtAccountCode7(), "10007");
            assertEquals(((GftAccountOpenStatusRow)l_lisResults.get(0)).getExtAccountCode8(), "10008");
d1968 7
a1974 7
            assertNull(((GftAccountOpenStatusRow)l_lisResults.get(0)).getExtAccountCode2());
            assertNull(((GftAccountOpenStatusRow)l_lisResults.get(0)).getExtAccountCode3());
            assertNull(((GftAccountOpenStatusRow)l_lisResults.get(0)).getExtAccountCode4());
            assertNull(((GftAccountOpenStatusRow)l_lisResults.get(0)).getExtAccountCode5());
            assertNull(((GftAccountOpenStatusRow)l_lisResults.get(0)).getExtAccountCode6());
            assertNull(((GftAccountOpenStatusRow)l_lisResults.get(0)).getExtAccountCode7());
            assertNull(((GftAccountOpenStatusRow)l_lisResults.get(0)).getExtAccountCode8());
d2034 1
a2034 1
            l_fxAccInformationUnit3.fxCourseDiv = WEB3GftTransStatusCourseDivDef.CFD_COURSE_2;
d2036 1
a2036 1
            l_fxAccInformationUnit4.fxCourseDiv = WEB3GftTransStatusCourseDivDef.CFD_COURSE_3;
d2038 1
a2038 1
            l_fxAccInformationUnit5.fxCourseDiv = WEB3GftTransStatusCourseDivDef.CFD_COURSE_4;
d2040 1
a2040 1
            l_fxAccInformationUnit6.fxCourseDiv = WEB3GftTransStatusCourseDivDef.CFD_COURSE_5;
d2042 1
a2042 1
            l_fxAccInformationUnit7.fxCourseDiv = WEB3GftTransStatusCourseDivDef.CFD_COURSE_6;
d2044 1
a2044 1
            l_fxAccInformationUnit8.fxCourseDiv = WEB3GftTransStatusCourseDivDef.CFD_COURSE_7;
d2046 1
a2046 1
            l_fxAccInformationUnit9.fxCourseDiv = WEB3GftTransStatusCourseDivDef.CFD_COURSE_8;
d2073 2
a2074 2
        	assertEquals(((GftAccountOpenStatusRow)l_lisResults.get(0)).getExtAccountCode(), "987654");
        	assertEquals(((GftAccountOpenStatusRow)l_lisResults.get(0)).getFxAccountCode10(), "654321");
d2076 7
a2082 7
            assertEquals(((GftAccountOpenStatusRow)l_lisResults.get(0)).getExtAccountCode2(), "10002");
            assertEquals(((GftAccountOpenStatusRow)l_lisResults.get(0)).getExtAccountCode3(), "10003");
            assertEquals(((GftAccountOpenStatusRow)l_lisResults.get(0)).getExtAccountCode4(), "10004");
            assertEquals(((GftAccountOpenStatusRow)l_lisResults.get(0)).getExtAccountCode5(), "10005");
            assertEquals(((GftAccountOpenStatusRow)l_lisResults.get(0)).getExtAccountCode6(), "10006");
            assertEquals(((GftAccountOpenStatusRow)l_lisResults.get(0)).getExtAccountCode7(), "10007");
            assertEquals(((GftAccountOpenStatusRow)l_lisResults.get(0)).getExtAccountCode8(), "10008");
d2893 5
d2900 1
a2900 1
    	l_resultInfoCreateUser.setAccountIds(null);
d2906 4
a2909 1

d2918 1
a2918 1
    	assertEquals(l_fxAccInformationUnits.length, 0);
d2933 5
a2937 1
    	long[] l_lngAccountIds = {120,15,125};
d2940 1
a2940 1
    	l_resultInfoCreateUser.setAccountIds(l_lngAccountIds);
d2978 5
a2982 1
    	long[] l_lngAccountIds = {120,15,125};
d2985 1
a2985 1
    	l_resultInfoCreateUser.setAccountIds(l_lngAccountIds);
d3021 5
a3025 1
    	long[] l_lngAccountIds = {120,15,125};
d3028 1
a3028 1
    	l_resultInfoCreateUser.setAccountIds(l_lngAccountIds);
d3061 5
a3065 1
    	long[] l_lngAccountIds = {120,15,125};
d3068 1
a3068 1
    	l_resultInfoCreateUser.setAccountIds(l_lngAccountIds);
d3099 5
a3103 1
    	long[] l_lngAccountIds = {120,15,125};
d3106 1
a3106 1
    	l_resultInfoCreateUser.setAccountIds(l_lngAccountIds);
d3143 4
a3146 1

d3155 1
a3155 1
    	assertEquals(l_fxAccInformationUnits.length, 0);
d3171 5
a3175 1
    	long[] l_lngAccountIds = {120,15,125};
d3178 1
a3178 1
    	l_resultInfoCreateUser.setAccountIds(l_lngAccountIds);
d3184 4
a3187 1

d3196 1
a3196 1
    	assertEquals(l_fxAccInformationUnits.length, 0);
d3212 5
a3216 1
    	long[] l_lngAccountIds = {120,15,125};
d3219 1
a3219 1
    	l_resultInfoCreateUser.setAccountIds(l_lngAccountIds);
d3225 4
a3228 1

d3237 1
a3237 1
    	assertEquals(l_fxAccInformationUnits.length, 0);
d3253 5
a3257 1
    	long[] l_lngAccountIds = {120,15,125};
d3260 1
a3260 1
    	l_resultInfoCreateUser.setAccountIds(l_lngAccountIds);
d4911 1
a4911 1
                new WEB3FXAccInformationUnit[8];
d4914 12
a4925 14
            WEB3FXAccInformationUnit l_fxAccInformationUnit1 =
                new WEB3FXAccInformationUnit();
            WEB3FXAccInformationUnit l_fxAccInformationUnit2 =
                new WEB3FXAccInformationUnit();
            WEB3FXAccInformationUnit l_fxAccInformationUnit3 =
                new WEB3FXAccInformationUnit();
            WEB3FXAccInformationUnit l_fxAccInformationUnit4 =
                new WEB3FXAccInformationUnit();
            WEB3FXAccInformationUnit l_fxAccInformationUnit5 =
                new WEB3FXAccInformationUnit();
            WEB3FXAccInformationUnit l_fxAccInformationUnit6 =
                new WEB3FXAccInformationUnit();
            WEB3FXAccInformationUnit l_fxAccInformationUnit7 =
                new WEB3FXAccInformationUnit();
d4928 6
a4933 7
            l_fxAccInformationUnits[1] = l_fxAccInformationUnit1;
            l_fxAccInformationUnits[2] = l_fxAccInformationUnit2;
            l_fxAccInformationUnits[3] = l_fxAccInformationUnit3;
            l_fxAccInformationUnits[4] = l_fxAccInformationUnit4;
            l_fxAccInformationUnits[5] = l_fxAccInformationUnit5;
            l_fxAccInformationUnits[6] = l_fxAccInformationUnit6;
            l_fxAccInformationUnits[7] = l_fxAccInformationUnit7;
d4937 12
a4948 14
            l_fxAccInformationUnit1.fxCourseDiv = WEB3GftTransStatusCourseDivDef.CFD_COURSE_2;
            l_fxAccInformationUnit1.fxAccountCode = "10002";
            l_fxAccInformationUnit2.fxCourseDiv = WEB3GftTransStatusCourseDivDef.CFD_COURSE_3;
            l_fxAccInformationUnit2.fxAccountCode = "10003";
            l_fxAccInformationUnit3.fxCourseDiv = WEB3GftTransStatusCourseDivDef.CFD_COURSE_4;
            l_fxAccInformationUnit3.fxAccountCode = "10004";
            l_fxAccInformationUnit4.fxCourseDiv = WEB3GftTransStatusCourseDivDef.CFD_COURSE_5;
            l_fxAccInformationUnit4.fxAccountCode = "10005";
            l_fxAccInformationUnit5.fxCourseDiv = WEB3GftTransStatusCourseDivDef.CFD_COURSE_6;
            l_fxAccInformationUnit5.fxAccountCode = "10006";
            l_fxAccInformationUnit6.fxCourseDiv = WEB3GftTransStatusCourseDivDef.CFD_COURSE_7;
            l_fxAccInformationUnit6.fxAccountCode = "10007";
            l_fxAccInformationUnit7.fxCourseDiv = WEB3GftTransStatusCourseDivDef.CFD_COURSE_8;
            l_fxAccInformationUnit7.fxAccountCode = "10008";
d4970 1
a4970 1
            assertEquals(8, l_lisResult.size());
d4973 6
a4978 7
            assertEquals("10", ((FxAccountCodeRow)l_lisResult.get(1)).getFxSystemCode());
            assertEquals("10", ((FxAccountCodeRow)l_lisResult.get(2)).getFxSystemCode());
            assertEquals("10", ((FxAccountCodeRow)l_lisResult.get(3)).getFxSystemCode());
            assertEquals("10", ((FxAccountCodeRow)l_lisResult.get(4)).getFxSystemCode());
            assertEquals("10", ((FxAccountCodeRow)l_lisResult.get(5)).getFxSystemCode());
            assertEquals("10", ((FxAccountCodeRow)l_lisResult.get(6)).getFxSystemCode());
            assertEquals("10", ((FxAccountCodeRow)l_lisResult.get(7)).getFxSystemCode());
d4998 1
a4998 1
                new WEB3FXAccInformationUnit[8];
d5001 12
a5012 14
            WEB3FXAccInformationUnit l_fxAccInformationUnit1 =
                new WEB3FXAccInformationUnit();
            WEB3FXAccInformationUnit l_fxAccInformationUnit2 =
                new WEB3FXAccInformationUnit();
            WEB3FXAccInformationUnit l_fxAccInformationUnit3 =
                new WEB3FXAccInformationUnit();
            WEB3FXAccInformationUnit l_fxAccInformationUnit4 =
                new WEB3FXAccInformationUnit();
            WEB3FXAccInformationUnit l_fxAccInformationUnit5 =
                new WEB3FXAccInformationUnit();
            WEB3FXAccInformationUnit l_fxAccInformationUnit6 =
                new WEB3FXAccInformationUnit();
            WEB3FXAccInformationUnit l_fxAccInformationUnit7 =
                new WEB3FXAccInformationUnit();
d5015 6
a5020 7
            l_fxAccInformationUnits[1] = l_fxAccInformationUnit1;
            l_fxAccInformationUnits[2] = l_fxAccInformationUnit2;
            l_fxAccInformationUnits[3] = l_fxAccInformationUnit3;
            l_fxAccInformationUnits[4] = l_fxAccInformationUnit4;
            l_fxAccInformationUnits[5] = l_fxAccInformationUnit5;
            l_fxAccInformationUnits[6] = l_fxAccInformationUnit6;
            l_fxAccInformationUnits[7] = l_fxAccInformationUnit7;
d5024 12
a5035 14
            l_fxAccInformationUnit1.fxCourseDiv = WEB3GftTransStatusCourseDivDef.CFD_COURSE_2;
            l_fxAccInformationUnit1.fxAccountCode = "10002";
            l_fxAccInformationUnit2.fxCourseDiv = WEB3GftTransStatusCourseDivDef.CFD_COURSE_3;
            l_fxAccInformationUnit2.fxAccountCode = "10003";
            l_fxAccInformationUnit3.fxCourseDiv = WEB3GftTransStatusCourseDivDef.CFD_COURSE_4;
            l_fxAccInformationUnit3.fxAccountCode = "10004";
            l_fxAccInformationUnit4.fxCourseDiv = WEB3GftTransStatusCourseDivDef.CFD_COURSE_5;
            l_fxAccInformationUnit4.fxAccountCode = "10005";
            l_fxAccInformationUnit5.fxCourseDiv = WEB3GftTransStatusCourseDivDef.CFD_COURSE_6;
            l_fxAccInformationUnit5.fxAccountCode = "10006";
            l_fxAccInformationUnit6.fxCourseDiv = WEB3GftTransStatusCourseDivDef.CFD_COURSE_7;
            l_fxAccInformationUnit6.fxAccountCode = "10007";
            l_fxAccInformationUnit7.fxCourseDiv = WEB3GftTransStatusCourseDivDef.CFD_COURSE_8;
            l_fxAccInformationUnit7.fxAccountCode = "10008";
d5057 1
a5057 1
            assertEquals(8, l_lisResult.size());
d5060 6
a5065 7
            assertEquals("01", ((FxAccountCodeRow)l_lisResult.get(1)).getFxSystemCode());
            assertEquals("01", ((FxAccountCodeRow)l_lisResult.get(2)).getFxSystemCode());
            assertEquals("01", ((FxAccountCodeRow)l_lisResult.get(3)).getFxSystemCode());
            assertEquals("01", ((FxAccountCodeRow)l_lisResult.get(4)).getFxSystemCode());
            assertEquals("01", ((FxAccountCodeRow)l_lisResult.get(5)).getFxSystemCode());
            assertEquals("01", ((FxAccountCodeRow)l_lisResult.get(6)).getFxSystemCode());
            assertEquals("01", ((FxAccountCodeRow)l_lisResult.get(7)).getFxSystemCode());
@


1.1
log
@*** empty log message ***
@
text
@a33 3
import com.gftforex.soap.api.ResultInfoAddAccount;
import com.gftforex.soap.api.ResultInfoCreateUser;
import com.gftforex.soap.api.SendSyncRequestResponse;
@

