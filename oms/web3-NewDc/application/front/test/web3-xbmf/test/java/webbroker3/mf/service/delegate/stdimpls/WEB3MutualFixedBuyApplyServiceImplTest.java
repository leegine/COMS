head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.09.37;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MutualFixedBuyApplyServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信定時定額買付新規申込サービスImpl(WEB3MutualFixedBuyApplyServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/10/26 孫洪江 (中訊) 新規作成 仕様変更モデルNo.584
*/

package webbroker3.mf.service.delegate.stdimpls;

import java.util.Calendar;

import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3GentradeInstitutionForMock;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.gentrade.message.WEB3GentradeProspectusResult;
import webbroker3.mf.WEB3MutualFundProduct;
import webbroker3.mf.message.WEB3MutualFixedBuyApplyConfirmRequest;
import webbroker3.mf.message.WEB3MutualFixedBuyApplyConfirmResponse;
import webbroker3.mf.message.WEB3MutualFixedBuyCommonUnit;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectParamsValue;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3MutualFixedBuyApplyServiceImplTest extends TestBaseForMock
{
    private WEB3MutualFixedBuyApplyServiceImpl l_service = null;
    
    private WEB3MutualFixedBuyApplyConfirmRequest l_request = null;
    
    private WEB3MutualFixedBuyApplyConfirmResponse l_response = null;

    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3MutualFixedBuyApplyServiceImplTest.class);

    public WEB3MutualFixedBuyApplyServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        this.l_service = new WEB3MutualFixedBuyApplyServiceImpl();
        this.l_request = new WEB3MutualFixedBuyApplyConfirmRequestForTest();
        this.l_response = new WEB3MutualFixedBuyApplyConfirmResponse();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
        this.l_service = null;
        this.l_request = null;
        this.l_response = null;
    }

    /**
     * ＜分岐処理＞リクエスト.投信定時定額買付共通情報一覧.電子鳩チェックフラグ == 
     *                       trueかつwork用の電子鳩障害中フラグがfalseの場合
     * ＜分岐処理＞validate目論見書閲覧()の戻り値が「1:閲覧未済」の場合
     * 
     * 
     * 電子鳩チェック結果 = work用の電子鳩障害中フラグがfalseの場合、1件でも戻り
     *                     値が｢1:閲覧未済｣があれば、｢1:閲覧未済｣をセット。
     * 目論見書閲覧未済銘柄コード一覧 = work用の目論見書閲覧未済銘柄コード一覧をセット。
     */
    public void testValidateFixedBuyApply_0001()
    {
        String STR_METHOD_NAME = "testValidateFixedBuyApply_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            this.initData();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));

            //MutualFundProductRow
            MutualFundProductParams l_MutualFundProductParams = new MutualFundProductParams();
            l_MutualFundProductParams.setFixedBuyPossibleDiv("1");
            l_MutualFundProductParams.setProductId(3304148080000L);
            l_MutualFundProductParams.setInstitutionCode("40");
            l_MutualFundProductParams.setProductCode("0001000");
            l_MutualFundProductParams.setProductIssueCode("0");
            l_MutualFundProductParams.setFundType(MutualFundTypeEnum.DOMESTIC);
            l_MutualFundProductParams.setInitPurchaseMinQty(0L);
            l_MutualFundProductParams.setAddtlPurchaseMinQty(0L);
            l_MutualFundProductParams.setLastUpdater("1001");
            l_MutualFundProductParams.setOnlineUpdatedTimestamp(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_MutualFundProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_MutualFundProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            TestDBUtility.insertWithDel(l_MutualFundProductParams);
            
            WEB3MutualFundProduct l_mfProduct = new WEB3MutualFundProduct(l_MutualFundProductParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.mf.WEB3MutualFundProductManager", "getMutualFundProduct", new Class[]
                    { Institution.class, String.class },
                    l_mfProduct);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                    "validateFixedBuyAmount", 
                    new Class[]{ SubAccount.class, String.class, String.class },
                    null);
            
            WEB3GentradeProspectusResult l_prospectusResult = new WEB3GentradeProspectusResult();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.service.delegate.stdimpls.WEB3GentradeBatoClientServiceImpl",
                    "validateProspectus",
                    new Class[] {String.class, String.class},
                    l_prospectusResult);
            l_prospectusResult.checkResult = "1";//"0" "2"
            
            this.l_request.commonList = new WEB3MutualFixedBuyCommonUnit[1];
            
            this.l_request.commonList[0] = new WEB3MutualFixedBuyCommonUnit();
            
            this.l_request.commonList[0].mutualProductCode = "0";
            
            this.l_request.commonList[0].monthlyBuyAmount = "0";
            
            this.l_request.commonList[0].increaseBuyAmount = "0";
            
            this.l_request.commonList[0].batoCheckFlag = true;
            
            this.l_request.commonList[0].typeCode = "0";
            
            this.l_response = l_service.validateFixedBuyApply(this.l_request);
            
            //ParamsCheck
            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.mf.WEB3MutualFundProductManager",
                    "getMutualFundProduct",
                    new Class[]{ Institution.class, String.class });
            assertEquals(WEB3GentradeInstitutionForMock.class, ((WEB3GentradeInstitutionForMock)l_paramsValue1.getFirstCalled()[0]).getClass());
            assertEquals( "0",(String)l_paramsValue1.getFirstCalled()[1]);
            
            WEB3MockObjectParamsValue l_paramsValue2 =  TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                    "validateFixedBuyAmount", 
                    new Class[]{ SubAccount.class, String.class, String.class });
            assertEquals(WEB3GentradeSubAccount.class, ((WEB3GentradeSubAccount)l_paramsValue2.getFirstCalled()[0]).getClass());
            assertEquals( "0",(String)l_paramsValue2.getFirstCalled()[1]);
            assertEquals( "0",(String)l_paramsValue2.getFirstCalled()[2]);
            
            WEB3MockObjectParamsValue l_paramsValue3 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.gentrade.service.delegate.stdimpls.WEB3GentradeBatoClientServiceImpl",
                    "validateProspectus",
                    new Class[] {String.class, String.class});
            assertEquals( "0",(String)l_paramsValue3.getFirstCalled()[0]);
            assertEquals( "0",(String)l_paramsValue3.getFirstCalled()[1]);
            assertTrue(true);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(""+l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error(""+l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * ＜分岐処理＞リクエスト.投信定時定額買付共通情報一覧.電子鳩チェックフラグ == 
     *                       trueかつwork用の電子鳩障害中フラグがfalseの場合
     * ＜分岐処理＞validate目論見書閲覧()の戻り値が「2:閲覧未済(障害中)」の場合
     * 
     * 電子鳩チェック結果 = work用の電子鳩障害中フラグがtrueの場合、｢2:閲覧未済(障害中)｣をセット。
     * work用の目論見書閲覧未済銘柄コード一覧が1件もない場合、nullをセット。
     */
    public void testValidateFixedBuyApply_0002()
    {
        String STR_METHOD_NAME = "testValidateFixedBuyApply_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            this.initData();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));

            //MutualFundProductRow
            MutualFundProductParams l_MutualFundProductParams = new MutualFundProductParams();
            l_MutualFundProductParams.setFixedBuyPossibleDiv("1");
            l_MutualFundProductParams.setProductId(3304148080000L);
            l_MutualFundProductParams.setInstitutionCode("40");
            l_MutualFundProductParams.setProductCode("0001000");
            l_MutualFundProductParams.setProductIssueCode("0");
            l_MutualFundProductParams.setFundType(MutualFundTypeEnum.DOMESTIC);
            l_MutualFundProductParams.setInitPurchaseMinQty(0L);
            l_MutualFundProductParams.setAddtlPurchaseMinQty(0L);
            l_MutualFundProductParams.setLastUpdater("1001");
            l_MutualFundProductParams.setOnlineUpdatedTimestamp(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_MutualFundProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_MutualFundProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            TestDBUtility.insertWithDel(l_MutualFundProductParams);
            
            WEB3MutualFundProduct l_mfProduct = new WEB3MutualFundProduct(l_MutualFundProductParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.mf.WEB3MutualFundProductManager", "getMutualFundProduct", new Class[]
                    { Institution.class, String.class },
                    l_mfProduct);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                    "validateFixedBuyAmount", 
                    new Class[]{ SubAccount.class, String.class, String.class },
                    null);
            
            WEB3GentradeProspectusResult l_prospectusResult = new WEB3GentradeProspectusResult();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.service.delegate.stdimpls.WEB3GentradeBatoClientServiceImpl",
                    "validateProspectus",
                    new Class[] {String.class, String.class},
                    l_prospectusResult);
            l_prospectusResult.checkResult = "2";//"0" "1"
            
            this.l_request.commonList = new WEB3MutualFixedBuyCommonUnit[5];
            
            this.l_request.commonList[0] = new WEB3MutualFixedBuyCommonUnit();
            this.l_request.commonList[1] = new WEB3MutualFixedBuyCommonUnit();
            this.l_request.commonList[2] = new WEB3MutualFixedBuyCommonUnit();
            this.l_request.commonList[3] = new WEB3MutualFixedBuyCommonUnit();
            this.l_request.commonList[4] = new WEB3MutualFixedBuyCommonUnit();
            
            this.l_request.commonList[0].mutualProductCode = "0";
            this.l_request.commonList[1].mutualProductCode = "1";
            this.l_request.commonList[2].mutualProductCode = "2";
            this.l_request.commonList[3].mutualProductCode = "3";
            this.l_request.commonList[4].mutualProductCode = "4";
            
            this.l_request.commonList[0].monthlyBuyAmount = "0";
            this.l_request.commonList[1].monthlyBuyAmount = "1";
            this.l_request.commonList[2].monthlyBuyAmount = "2";
            this.l_request.commonList[3].monthlyBuyAmount = "3";
            this.l_request.commonList[4].monthlyBuyAmount = "4";
            
            this.l_request.commonList[0].increaseBuyAmount = "0";
            this.l_request.commonList[1].increaseBuyAmount = "1";
            this.l_request.commonList[2].increaseBuyAmount = "2";
            this.l_request.commonList[3].increaseBuyAmount = "3";
            this.l_request.commonList[4].increaseBuyAmount = "4";
            
            this.l_request.commonList[0].batoCheckFlag = true;
            this.l_request.commonList[1].batoCheckFlag = true;
            this.l_request.commonList[2].batoCheckFlag = true;
            this.l_request.commonList[3].batoCheckFlag = true;
            this.l_request.commonList[4].batoCheckFlag = true;
            
            this.l_request.commonList[0].typeCode = "0";
            this.l_request.commonList[1].typeCode = "1";
            this.l_request.commonList[2].typeCode = "2";
            this.l_request.commonList[3].typeCode = "3";
            this.l_request.commonList[4].typeCode = "4";
            
            this.l_response = l_service.validateFixedBuyApply(this.l_request);
            
            //ParamsCheck
            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.mf.WEB3MutualFundProductManager",
                    "getMutualFundProduct",
                    new Class[]{ Institution.class, String.class });
            assertEquals(WEB3GentradeInstitutionForMock.class, ((WEB3GentradeInstitutionForMock)l_paramsValue1.getFirstCalled()[0]).getClass());
            assertEquals( "0",(String)l_paramsValue1.getFirstCalled()[1]);
            
            WEB3MockObjectParamsValue l_paramsValue2 =  TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                    "validateFixedBuyAmount", 
                    new Class[]{ SubAccount.class, String.class, String.class });
            assertEquals(WEB3GentradeSubAccount.class, ((WEB3GentradeSubAccount)l_paramsValue2.getFirstCalled()[0]).getClass());
            assertEquals( "0",(String)l_paramsValue2.getFirstCalled()[1]);
            assertEquals( "0",(String)l_paramsValue2.getFirstCalled()[2]);
            
            WEB3MockObjectParamsValue l_paramsValue3 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.gentrade.service.delegate.stdimpls.WEB3GentradeBatoClientServiceImpl",
                    "validateProspectus",
                    new Class[] {String.class, String.class});
            assertEquals( "0",(String)l_paramsValue3.getFirstCalled()[0]);
            assertEquals( "0",(String)l_paramsValue3.getFirstCalled()[1]);
            assertTrue(true);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(""+l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error(""+l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * ＜分岐処理＞リクエスト.投信定時定額買付共通情報一覧.電子鳩チェックフラグ == 
     *                       trueかつwork用の電子鳩障害中フラグがfalseの場合
     * ＜分岐処理＞validate目論見書閲覧()の戻り値が「2:閲覧未済(障害中)」の場合
     * 
     * 電子鳩チェック結果 = work用の電子鳩障害中フラグがfalseの場合validate目論見書閲覧()
     *                     の戻り値が全て｢0:閲覧済｣の場合、｢0:閲覧済｣をセット。
     */
    public void testValidateFixedBuyApply_0003()
    {
        String STR_METHOD_NAME = "testValidateFixedBuyApply_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            this.initData();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));

            //MutualFundProductRow
            MutualFundProductParams l_MutualFundProductParams = new MutualFundProductParams();
            l_MutualFundProductParams.setFixedBuyPossibleDiv("1");
            l_MutualFundProductParams.setProductId(3304148080000L);
            l_MutualFundProductParams.setInstitutionCode("40");
            l_MutualFundProductParams.setProductCode("0001000");
            l_MutualFundProductParams.setProductIssueCode("0");
            l_MutualFundProductParams.setFundType(MutualFundTypeEnum.DOMESTIC);
            l_MutualFundProductParams.setInitPurchaseMinQty(0L);
            l_MutualFundProductParams.setAddtlPurchaseMinQty(0L);
            l_MutualFundProductParams.setLastUpdater("1001");
            l_MutualFundProductParams.setOnlineUpdatedTimestamp(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_MutualFundProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_MutualFundProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            TestDBUtility.insertWithDel(l_MutualFundProductParams);
            
            WEB3MutualFundProduct l_mfProduct = new WEB3MutualFundProduct(l_MutualFundProductParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.mf.WEB3MutualFundProductManager", "getMutualFundProduct", new Class[]
                    { Institution.class, String.class },
                    l_mfProduct);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                    "validateFixedBuyAmount", 
                    new Class[]{ SubAccount.class, String.class, String.class },
                    null);
            
            WEB3GentradeProspectusResult l_prospectusResult = new WEB3GentradeProspectusResult();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.service.delegate.stdimpls.WEB3GentradeBatoClientServiceImpl",
                    "validateProspectus",
                    new Class[] {String.class, String.class},
                    l_prospectusResult);
            l_prospectusResult.checkResult = "0";//"0" "2"
            
            this.l_request.commonList = new WEB3MutualFixedBuyCommonUnit[5];
            
            this.l_request.commonList[0] = new WEB3MutualFixedBuyCommonUnit();
            this.l_request.commonList[1] = new WEB3MutualFixedBuyCommonUnit();
            this.l_request.commonList[2] = new WEB3MutualFixedBuyCommonUnit();
            this.l_request.commonList[3] = new WEB3MutualFixedBuyCommonUnit();
            this.l_request.commonList[4] = new WEB3MutualFixedBuyCommonUnit();
            
            this.l_request.commonList[0].mutualProductCode = "0";
            this.l_request.commonList[1].mutualProductCode = "1";
            this.l_request.commonList[2].mutualProductCode = "2";
            this.l_request.commonList[3].mutualProductCode = "3";
            this.l_request.commonList[4].mutualProductCode = "4";
            
            this.l_request.commonList[0].monthlyBuyAmount = "0";
            this.l_request.commonList[1].monthlyBuyAmount = "1";
            this.l_request.commonList[2].monthlyBuyAmount = "2";
            this.l_request.commonList[3].monthlyBuyAmount = "3";
            this.l_request.commonList[4].monthlyBuyAmount = "4";
            
            this.l_request.commonList[0].increaseBuyAmount = "0";
            this.l_request.commonList[1].increaseBuyAmount = "1";
            this.l_request.commonList[2].increaseBuyAmount = "2";
            this.l_request.commonList[3].increaseBuyAmount = "3";
            this.l_request.commonList[4].increaseBuyAmount = "4";
            
            this.l_request.commonList[0].batoCheckFlag = true;
            this.l_request.commonList[1].batoCheckFlag = true;
            this.l_request.commonList[2].batoCheckFlag = true;
            this.l_request.commonList[3].batoCheckFlag = true;
            this.l_request.commonList[4].batoCheckFlag = true;
            
            this.l_request.commonList[0].typeCode = "0";
            this.l_request.commonList[1].typeCode = "1";
            this.l_request.commonList[2].typeCode = "2";
            this.l_request.commonList[3].typeCode = "3";
            this.l_request.commonList[4].typeCode = "4";
            
            this.l_response = l_service.validateFixedBuyApply(this.l_request);
            
            //ParamsCheck
            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.mf.WEB3MutualFundProductManager",
                    "getMutualFundProduct",
                    new Class[]{ Institution.class, String.class });
            assertEquals(WEB3GentradeInstitutionForMock.class, ((WEB3GentradeInstitutionForMock)l_paramsValue1.getFirstCalled()[0]).getClass());
            assertEquals( "0",(String)l_paramsValue1.getFirstCalled()[1]);
            
            WEB3MockObjectParamsValue l_paramsValue2 =  TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl",
                    "validateFixedBuyAmount", 
                    new Class[]{ SubAccount.class, String.class, String.class });
            assertEquals(WEB3GentradeSubAccount.class, ((WEB3GentradeSubAccount)l_paramsValue2.getFirstCalled()[0]).getClass());
            assertEquals( "0",(String)l_paramsValue2.getFirstCalled()[1]);
            assertEquals( "0",(String)l_paramsValue2.getFirstCalled()[2]);
            
            WEB3MockObjectParamsValue l_paramsValue3 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.gentrade.service.delegate.stdimpls.WEB3GentradeBatoClientServiceImpl",
                    "validateProspectus",
                    new Class[] {String.class, String.class});
            assertEquals( "0",(String)l_paramsValue3.getFirstCalled()[0]);
            assertEquals( "0",(String)l_paramsValue3.getFirstCalled()[1]);
            assertTrue(true);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(""+l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error(""+l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    private void initData()
    {
        final String STR_METHOD_NAME = "initData()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            //TradingTimeRow
            TradingTimeParams l_tradingTimeParams = new TradingTimeParams();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setStartTime("000000");
            l_tradingTimeParams.setEndTime("235959");
            l_tradingTimeParams.setSubmitMarketTrigger("0");
            l_tradingTimeParams.setEnableOrder("0");
            l_tradingTimeParams.setBizdateCalcParameter("1");
            l_tradingTimeParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_tradingTimeParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            //ProductRow
            ProductParams l_productParams = new ProductParams();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setInstitutionCode("0D");
            l_productParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
            l_productParams.setStandardName("シンセンテルス");
            l_productParams.setLotSize(0.000000D);
            l_productParams.setCalcSize(1.000000D);
            l_productParams.setEstimationPrice(0.000000D);
            l_productParams.setMarginRatio(0.000000D);
            l_productParams.setSecuritiesEstimationRatio(0.000000D);
            l_productParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_productParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.insertWithDel(l_productParams);
            
            //MainAccountRow
            MainAccountParams l_mainAccountParams = new MainAccountParams();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setInstitutionId(33L);
            l_mainAccountParams.setAccountCode("2512246");
            l_mainAccountParams.setBranchId(33381L);
            l_mainAccountParams.setBranchCode("381");
            l_mainAccountParams.setSonarTraderCode("11124");
            l_mainAccountParams.setAccountType(MainAccountTypeEnum.INDIVIDUAL_ACCOUNT);
            l_mainAccountParams.setFamilyName("内藤　@四郎");
            l_mainAccountParams.setFamilyNameAlt1("ﾅｲﾄｳ ｼﾛｳ");
            l_mainAccountParams.setGivenNameAlt1("Siro");
            l_mainAccountParams.setZipCode("1001238");
            l_mainAccountParams.setSubZipCode("1001238");
            l_mainAccountParams.setAddressLine1("東京都");
            l_mainAccountParams.setAddressLine2("江東区");
            l_mainAccountParams.setAddressLine3("深川５");
            l_mainAccountParams.setTelephone("38201115");
            l_mainAccountParams.setMobile("901115");
            l_mainAccountParams.setFax("38202226");
            l_mainAccountParams.setEquityOrderExeMailFlag(BooleanEnum.TRUE);
            l_mainAccountParams.setEquityOrderUnexecMailFlag(BooleanEnum.TRUE);
            l_mainAccountParams.setIfoOrderExecMailFlag(BooleanEnum.TRUE);
            l_mainAccountParams.setIfoOrderUnexecMailFlag(BooleanEnum.TRUE);
            l_mainAccountParams.setInformationMailFlag(BooleanEnum.TRUE);
            l_mainAccountParams.setResident("0");
            l_mainAccountParams.setNewAccountDiv("0");
            l_mainAccountParams.setViaTrustBankDiv("0");
            l_mainAccountParams.setEmailAddress("t4@@dir.co.jp");
            l_mainAccountParams.setTradingPassword("&:,<#!+=!.,#:##&");
            l_mainAccountParams.setAccountOpenDate(WEB3DateUtility.getDate("20040202","yyyyMMdd"));
            l_mainAccountParams.setAccountCloseDate(WEB3DateUtility.getDate("99991231","yyyyMMdd"));
            l_mainAccountParams.setPersonIdentify("1");
            l_mainAccountParams.setEraBorn("3");
            l_mainAccountParams.setBornDate("101013");
            l_mainAccountParams.setSex("1");
            l_mainAccountParams.setYellowCustomer("0");
            l_mainAccountParams.setHtSettlementWay("0");
            l_mainAccountParams.setBankAccountRegi("0");
            l_mainAccountParams.setAccountStatus(MainAccountStatusEnum.ACTIVE);
            l_mainAccountParams.setExaminLockFlag("0");
            l_mainAccountParams.setMngLockFlag("0");
            l_mainAccountParams.setMngLockFlagAdvance(BooleanEnum.FALSE);
            l_mainAccountParams.setMngLockFlagUnpayMargin(BooleanEnum.FALSE);
            l_mainAccountParams.setMngLockFlagShortSecurity(BooleanEnum.FALSE);
            l_mainAccountParams.setMngLockFlagUnsubstitDepo(BooleanEnum.FALSE);
            l_mainAccountParams.setBranchLock("0");
            l_mainAccountParams.setOrderPermission("0");
            l_mainAccountParams.setTaxType(TaxTypeEnum.SPECIAL);
            l_mainAccountParams.setTaxTypeNext(TaxTypeEnum.SPECIAL_WITHHOLD);
            l_mainAccountParams.setMarginTaxType(TaxTypeEnum.UNDEFINED);
            l_mainAccountParams.setMarginTaxTypeNext(TaxTypeEnum.UNDEFINED);
            l_mainAccountParams.setQualifiedInstInvestorDiv("0");
            l_mainAccountParams.setMarginSysAccOpenDiv("0");
            l_mainAccountParams.setMarginGenAccOpenDiv("0");
            l_mainAccountParams.setEquitySpAccOpenDate(WEB3DateUtility.getDate("20040202","yyyyMMdd"));
            l_mainAccountParams.setForeignSecAccOpenDiv("1");
            l_mainAccountParams.setIfoAccOpenDivTokyo("0");
            l_mainAccountParams.setIfoAccOpenDivOsaka("0");
            l_mainAccountParams.setIfoAccOpenDivNagoya("0");
            l_mainAccountParams.setRuitoAccOpenDiv("0");
            l_mainAccountParams.setMrfAccOpenDiv("0");
            l_mainAccountParams.setFxAccOpenDiv("0");
            l_mainAccountParams.setFeqConAccOpenDiv("0");
            l_mainAccountParams.setTopPageId("0");
            l_mainAccountParams.setQuotoType("0");
            l_mainAccountParams.setTradingReportDiv("1");
            l_mainAccountParams.setPositionReportDiv("9");
            l_mainAccountParams.setPositionReportCycleDiv("1");
            l_mainAccountParams.setCertificateDepositFlag(BooleanEnum.TRUE);
            l_mainAccountParams.setAccountStatementFlag(BooleanEnum.TRUE);
            l_mainAccountParams.setEmailLastUpdater("2512246");
            l_mainAccountParams.setEmailLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_mainAccountParams.setTradingPasswordUpdater("2512246");
            l_mainAccountParams.setTrPwdLastUpdateTimestamp(Calendar.getInstance().getTime());
            l_mainAccountParams.setMbOffLastUpdater("2512246");
            l_mainAccountParams.setMbOffLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_mainAccountParams.setEnableOrderLastUpdater("2512246");
            l_mainAccountParams.setEnableOrderUpdatedTimestamp(Calendar.getInstance().getTime());
            l_mainAccountParams.setFxAccOpenDivLastUpdater("2512246");
            l_mainAccountParams.setFxAccOpenUpdatedTimestamp(Calendar.getInstance().getTime());
            l_mainAccountParams.setFeqConAccOpenDivUpdater("2512246");
            l_mainAccountParams.setFeqConAccOpenTimestamp(Calendar.getInstance().getTime());
            l_mainAccountParams.setMrfFundCode("1");
            l_mainAccountParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_mainAccountParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_mainAccountParams.setSpMngAccOpenDiv("0");
            l_mainAccountParams.setOnlyMblOpnDivTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            //SubAccountRow
            SubAccountParams l_subAccountParams = new SubAccountParams();
            //口座ＩＤ]
            l_subAccountParams.setAccountId(333812512246L);
            //補助口座ＩＤ
            l_subAccountParams.setSubAccountId(333812512246L);
            //補助口座タイプ
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            //証券会社コード
            l_subAccountParams.setInstitutionCode("0D");
            //証券会社ID
            l_subAccountParams.setInstitutionId(33);
            //部店ＩＤ
            l_subAccountParams.setBranchId(33381L);
            //補助口座ステータス
            l_subAccountParams.setSubAccountStatus(SubAccountStatusEnum.ACTIVE);
            //口座登録日
            l_subAccountParams.setOpenDate(WEB3DateUtility.getDate("20061211","yyyyMMdd"));
            //口座閉鎖日
            l_subAccountParams.setCloseDate(WEB3DateUtility.getDate("20061211","yyyyMMdd"));
            //残高(当日）
            l_subAccountParams.setCashBalance(13456.0);
            //作成日付
            l_subAccountParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            //更新日付
            l_subAccountParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    private class WEB3MutualFixedBuyApplyConfirmRequestForTest extends WEB3MutualFixedBuyApplyConfirmRequest
    {
        public void validate() throws WEB3BaseException
        {
            
        }
    }
}
@
