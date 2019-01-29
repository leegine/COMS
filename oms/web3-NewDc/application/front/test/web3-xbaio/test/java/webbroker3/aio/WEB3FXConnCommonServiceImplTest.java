head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.34.57;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FXConnCommonServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 接続共通Impl(WEB3FXConnCommonServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/06/25 柴双紅 (中訊) 新規作成 モデル1173,1180,1182,1188
Revision History : 2009/08/14 柴双紅 (中訊) モデル1190
Revision History : 2009/09/16 張騰宇 (中訊) モデル1204 1025 1219 1223 1224
*/
package webbroker3.aio;

import java.util.Calendar;

import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;

import test.util.TestDBUtility;

import webbroker3.aio.data.CompFxConditionParams;
import webbroker3.aio.message.WEB3FXAccInformationUnit;
import webbroker3.aio.message.WEB3FXGftAskingTelegramUnit;
import webbroker3.aio.message.WEB3FXGftResultNoticeTelegramUnit;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.data.SoapConnectPrefRpcParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3FXConnCommonServiceImplTest extends TestBaseForMock
{
    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
                WEB3FXConnCommonServiceImplTest.class);

    public WEB3FXConnCommonServiceImplTest(String arg0)
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
    WEB3FXConnCommonServiceImpl l_impl = new WEB3FXConnCommonServiceImpl();
    /*
     * Test method for 'webbroker3.aio.WEB3FXConnCommonServiceImpl.sendExtConnAskingMessage(CompFxConditionParams, WEB3FXGftAskingTelegramUnit)'
     */
//    public void testSendExtConnAskingMessageCase1()
//    {
//        final String STR_METHOD_NAME = "testSendExtConnAskingMessageCase1()";
//        log.entering (STR_METHOD_NAME );   
//        
//        try
//        {
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            l_mainAccountParams.setBranchCode("101");
//            l_mainAccountParams.setBranchId(33381);
//            l_mainAccountParams.setAccountCode("6000070");
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            
//            BranchParams l_branchParams = TestDBUtility.getBranchRow();
//            l_branchParams.setBranchCode("101");
//            l_branchParams.setBranchId(33381);
//            TestDBUtility.insertWithDel(l_branchParams);
//            
//            TestDBUtility.deleteAll(SoapConnectPrefRpcParams.TYPE);
//            SoapConnectPrefRpcParams l_SoapConnectPrefRpcParams = TestDBUtility.getSoapConnectPrefRpcRow();
//            l_SoapConnectPrefRpcParams.setBranchId(33381);
//            l_SoapConnectPrefRpcParams.setConnectDiv("14");
//            TestDBUtility.insertWithDel(l_SoapConnectPrefRpcParams);
//            
//            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
//            l_compFxConditionParams.setFxSystemCode("14");
//            l_compFxConditionParams.setExtConnectSystemCode("05");
//            WEB3FXGftAskingTelegramUnit l_fXGftAskingTelegramUnit = new WEB3FXGftAskingTelegramUnit();
//            l_fXGftAskingTelegramUnit.institutionCode = "0D";
//            l_fXGftAskingTelegramUnit.branchCode = "101";
//            l_fXGftAskingTelegramUnit.accountCode = "6000070";
//            l_fXGftAskingTelegramUnit.gftOperationDiv = "01";
//            l_impl.sendExtConnAskingMessage(l_compFxConditionParams, l_fXGftAskingTelegramUnit);
//        }
//        catch (Exception l_ex)
//        {
//            log.error("" + l_ex);
//            fail();
//        }
//    }

    /*
     * Test method for 'webbroker3.aio.WEB3FXConnCommonServiceImpl.createGftResultNoticeTelegramUnit(WEB3FXGftAskingTelegramUnit, WEB3FXAccInformationUnit[], String)'
     */
    public void testCreateGftResultNoticeTelegramUnitCase1()
    {
        final String STR_METHOD_NAME = "testSendExtConnAskingMessageCase1()";
        log.entering (STR_METHOD_NAME );   
        
        try
        {
            WEB3FXGftAskingTelegramUnit l_fXGftAskingTelegramUnit = new WEB3FXGftAskingTelegramUnit();
            //　@DIR→GFT送信日時 
            l_fXGftAskingTelegramUnit.dirSendTime = "20090922121212";
            //　@処理区分 
            l_fXGftAskingTelegramUnit.gftOperationDiv = "02";
            //　@初期ログインID 
            l_fXGftAskingTelegramUnit.fxFirstLoginId = "111";
            //　@担当区分 
            l_fXGftAskingTelegramUnit.groupName = "2";
            //　@会社コード 
            l_fXGftAskingTelegramUnit.institutionCode = "07";
            //　@WOLFセッションキー 
            l_fXGftAskingTelegramUnit.wolfSession = "aaa";
            //　@アプリケーションID 
            l_fXGftAskingTelegramUnit.wolfAid = "bbb";
            //　@再生成サービスID 
            l_fXGftAskingTelegramUnit.regetServiceId = "ccc";
            //　@SSID 
            l_fXGftAskingTelegramUnit.wolfSsid = "ddd";
            //　@部店コード 
            l_fXGftAskingTelegramUnit.branchCode = "101";
            //　@顧客コード 
            l_fXGftAskingTelegramUnit.accountCode = "1101";
            //　@識別コード 
            l_fXGftAskingTelegramUnit.requestNumber = "122";
            //
            //　@処理区分 = 02：入金の場合： 
            //　@為替保証金口座番号
            l_fXGftAskingTelegramUnit.fxAccountCode = "04";
            //　@入出金額 
            l_fXGftAskingTelegramUnit.cashinoutAmt = "100";
            //　@受渡日 
            l_fXGftAskingTelegramUnit.deliveryDate = "20090930";
            
            WEB3FXGftResultNoticeTelegramUnit l_fXGftResultNoticeTelegramUnit =
                l_impl.createGftResultNoticeTelegramUnit(l_fXGftAskingTelegramUnit, null, "111");

            assertEquals("20090922121212", l_fXGftResultNoticeTelegramUnit.dirSendTime);
            assertEquals("02", l_fXGftResultNoticeTelegramUnit.gftOperationDiv);
            assertEquals("111", l_fXGftResultNoticeTelegramUnit.fxFirstLoginId);
            assertEquals("2", l_fXGftResultNoticeTelegramUnit.groupName);
            assertEquals("07", l_fXGftResultNoticeTelegramUnit.institutionCode);
            assertEquals("aaa", l_fXGftResultNoticeTelegramUnit.wolfSession);
            assertEquals("bbb", l_fXGftResultNoticeTelegramUnit.wolfAid);
            assertEquals("ccc", l_fXGftResultNoticeTelegramUnit.regetServiceId);
            assertEquals("ddd", l_fXGftResultNoticeTelegramUnit.wolfSsid);
            assertEquals("101", l_fXGftResultNoticeTelegramUnit.branchCode);
            assertEquals("1101", l_fXGftResultNoticeTelegramUnit.accountCode);
            assertEquals("122", l_fXGftResultNoticeTelegramUnit.requestNumber);
            assertEquals("04", l_fXGftResultNoticeTelegramUnit.fxAccountCode);
            assertEquals("100", l_fXGftResultNoticeTelegramUnit.cashinoutAmt);
            assertEquals("20090930", l_fXGftResultNoticeTelegramUnit.deliveryDate);
            assertEquals("111", l_fXGftResultNoticeTelegramUnit.resultCode);
            
        }
        catch (Exception l_ex)
        {
            log.error("" + l_ex);
            fail();
        }
    }
    
    public void testCreateGftResultNoticeTelegramUnitCase2()
    {
        final String STR_METHOD_NAME = "testSendExtConnAskingMessageCase2()";
        log.entering (STR_METHOD_NAME );   
        
        try
        {
            WEB3FXGftAskingTelegramUnit l_fXGftAskingTelegramUnit = new WEB3FXGftAskingTelegramUnit();
            //　@DIR→GFT送信日時 
            l_fXGftAskingTelegramUnit.dirSendTime = "20090922121212";
            //　@処理区分 
            l_fXGftAskingTelegramUnit.gftOperationDiv = "04";
            //　@初期ログインID 
            l_fXGftAskingTelegramUnit.fxFirstLoginId = "111";
            //　@担当区分 
            l_fXGftAskingTelegramUnit.groupName = "2";
            //　@会社コード 
            l_fXGftAskingTelegramUnit.institutionCode = "07";
            //　@WOLFセッションキー 
            l_fXGftAskingTelegramUnit.wolfSession = "aaa";
            //　@アプリケーションID 
            l_fXGftAskingTelegramUnit.wolfAid = "bbb";
            //　@再生成サービスID 
            l_fXGftAskingTelegramUnit.regetServiceId = "ccc";
            //　@SSID 
            l_fXGftAskingTelegramUnit.wolfSsid = "ddd";
            //　@部店コード 
            l_fXGftAskingTelegramUnit.branchCode = "101";
            //　@顧客コード 
            l_fXGftAskingTelegramUnit.accountCode = "1101";
            //　@識別コード 
            l_fXGftAskingTelegramUnit.requestNumber = "122";
            //
            //　@　@処理区分 = 04：出金の場合： 
//            //　@メールアドレス 
//            l_fXGftAskingTelegramUnit.fxMailAddress = "";
            //　@為替保証金口座番号
            l_fXGftAskingTelegramUnit.fxAccountCode = "04";
//             　@初期パスワード 
            //　@名前（姓） 
            l_fXGftAskingTelegramUnit.fxLastName = "zhang";
            // 　@名前（名） 
            l_fXGftAskingTelegramUnit.fxFirstName = "san";
            //　@入出金額 
            l_fXGftAskingTelegramUnit.cashinoutAmt = "100";
            
            WEB3FXGftResultNoticeTelegramUnit l_fXGftResultNoticeTelegramUnit =
                l_impl.createGftResultNoticeTelegramUnit(l_fXGftAskingTelegramUnit, null, "111");

            assertEquals("20090922121212", l_fXGftResultNoticeTelegramUnit.dirSendTime);
            assertEquals("04", l_fXGftResultNoticeTelegramUnit.gftOperationDiv);
            assertEquals("111", l_fXGftResultNoticeTelegramUnit.fxFirstLoginId);
            assertEquals("2", l_fXGftResultNoticeTelegramUnit.groupName);
            assertEquals("07", l_fXGftResultNoticeTelegramUnit.institutionCode);
            assertEquals("aaa", l_fXGftResultNoticeTelegramUnit.wolfSession);
            assertEquals("bbb", l_fXGftResultNoticeTelegramUnit.wolfAid);
            assertEquals("ccc", l_fXGftResultNoticeTelegramUnit.regetServiceId);
            assertEquals("ddd", l_fXGftResultNoticeTelegramUnit.wolfSsid);
            assertEquals("101", l_fXGftResultNoticeTelegramUnit.branchCode);
            assertEquals("1101", l_fXGftResultNoticeTelegramUnit.accountCode);
            assertEquals("122", l_fXGftResultNoticeTelegramUnit.requestNumber);
            assertEquals("04", l_fXGftResultNoticeTelegramUnit.fxAccountCode);
            assertEquals("100", l_fXGftResultNoticeTelegramUnit.cashinoutAmt);
            assertNull(l_fXGftResultNoticeTelegramUnit.deliveryDate);
            assertEquals("111", l_fXGftResultNoticeTelegramUnit.resultCode);
//            assertEquals("111", l_fXGftResultNoticeTelegramUnit.fxMailAddress);
            assertEquals("zhang", l_fXGftResultNoticeTelegramUnit.fxLastName);
            assertEquals("san", l_fXGftResultNoticeTelegramUnit.fxFirstName);
            
        }
        catch (Exception l_ex)
        {
            log.error("" + l_ex);
            fail();
        }
    }
    
    public void testCreateGftResultNoticeTelegramUnitCase3()
    {
        final String STR_METHOD_NAME = "testSendExtConnAskingMessageCase3()";
        log.entering (STR_METHOD_NAME );   
        
        try
        {
            WEB3FXGftAskingTelegramUnit l_fXGftAskingTelegramUnit = new WEB3FXGftAskingTelegramUnit();
            //　@DIR→GFT送信日時 
            l_fXGftAskingTelegramUnit.dirSendTime = "20090922121212";
            //　@処理区分 
            l_fXGftAskingTelegramUnit.gftOperationDiv = "01";
            //　@初期ログインID 
            l_fXGftAskingTelegramUnit.fxFirstLoginId = "111";
            //　@担当区分 
            l_fXGftAskingTelegramUnit.groupName = "2";
            //　@会社コード 
            l_fXGftAskingTelegramUnit.institutionCode = "07";
            //　@WOLFセッションキー 
            l_fXGftAskingTelegramUnit.wolfSession = "aaa";
            //　@アプリケーションID 
            l_fXGftAskingTelegramUnit.wolfAid = "bbb";
            //　@再生成サービスID 
            l_fXGftAskingTelegramUnit.regetServiceId = "ccc";
            //　@SSID 
            l_fXGftAskingTelegramUnit.wolfSsid = "ddd";
            //　@部店コード 
            l_fXGftAskingTelegramUnit.branchCode = "101";
            //　@顧客コード 
            l_fXGftAskingTelegramUnit.accountCode = "1101";
            //　@識別コード 
            l_fXGftAskingTelegramUnit.requestNumber = "122";
            //
            //　@　@処理区分 = 01：口座開設
//            //　@メールアドレス 
            l_fXGftAskingTelegramUnit.fxMailAddress = "zhang-tengyu@@sinocom.cn";
 
//             　@初期パスワード 
            l_fXGftAskingTelegramUnit.fxFirstPassword = "111111";
            //　@名前（姓） 
            l_fXGftAskingTelegramUnit.fxLastName = "zhang";

            
            WEB3FXGftResultNoticeTelegramUnit l_fXGftResultNoticeTelegramUnit =
                l_impl.createGftResultNoticeTelegramUnit(l_fXGftAskingTelegramUnit, null, "111");

            assertEquals("20090922121212", l_fXGftResultNoticeTelegramUnit.dirSendTime);
            assertEquals("01", l_fXGftResultNoticeTelegramUnit.gftOperationDiv);
            assertEquals("111", l_fXGftResultNoticeTelegramUnit.fxFirstLoginId);
            assertEquals("2", l_fXGftResultNoticeTelegramUnit.groupName);
            assertEquals("07", l_fXGftResultNoticeTelegramUnit.institutionCode);
            assertEquals("aaa", l_fXGftResultNoticeTelegramUnit.wolfSession);
            assertEquals("bbb", l_fXGftResultNoticeTelegramUnit.wolfAid);
            assertEquals("ccc", l_fXGftResultNoticeTelegramUnit.regetServiceId);
            assertEquals("ddd", l_fXGftResultNoticeTelegramUnit.wolfSsid);
            assertEquals("101", l_fXGftResultNoticeTelegramUnit.branchCode);
            assertEquals("1101", l_fXGftResultNoticeTelegramUnit.accountCode);
            assertEquals("122", l_fXGftResultNoticeTelegramUnit.requestNumber);
//            assertEquals("04", l_fXGftResultNoticeTelegramUnit.fxAccountCode);
//            assertEquals("100", l_fXGftResultNoticeTelegramUnit.cashinoutAmt);
//            assertNull(l_fXGftResultNoticeTelegramUnit.deliveryDate);
            assertEquals("111", l_fXGftResultNoticeTelegramUnit.resultCode);
            assertEquals("zhang-tengyu@@sinocom.cn", l_fXGftResultNoticeTelegramUnit.fxMailAddress);
            assertEquals("111111", l_fXGftResultNoticeTelegramUnit.fxFirstPassword);
            assertEquals("zhang", l_fXGftResultNoticeTelegramUnit.fxLastName);
            
        }
        catch (Exception l_ex)
        {
            log.error("" + l_ex);
            fail();
        }
    }
    
    public void testCreateGftResultNoticeTelegramUnitCase4()
    {
        final String STR_METHOD_NAME = "testSendExtConnAskingMessageCase4()";
        log.entering (STR_METHOD_NAME );   
        
        try
        {
            WEB3FXGftAskingTelegramUnit l_fXGftAskingTelegramUnit = new WEB3FXGftAskingTelegramUnit();
            //　@DIR→GFT送信日時 
            l_fXGftAskingTelegramUnit.dirSendTime = "20090922121212";
            //　@処理区分 
            l_fXGftAskingTelegramUnit.gftOperationDiv = "03";
            //　@初期ログインID 
            l_fXGftAskingTelegramUnit.fxFirstLoginId = "111";
            //　@担当区分 
            l_fXGftAskingTelegramUnit.groupName = "2";
            //　@会社コード 
            l_fXGftAskingTelegramUnit.institutionCode = "07";
            //　@WOLFセッションキー 
            l_fXGftAskingTelegramUnit.wolfSession = "aaa";
            //　@アプリケーションID 
            l_fXGftAskingTelegramUnit.wolfAid = "bbb";
            //　@再生成サービスID 
            l_fXGftAskingTelegramUnit.regetServiceId = "ccc";
            //　@SSID 
            l_fXGftAskingTelegramUnit.wolfSsid = "ddd";
            //　@部店コード 
            l_fXGftAskingTelegramUnit.branchCode = "101";
            //　@顧客コード 
            l_fXGftAskingTelegramUnit.accountCode = "1101";
            //　@識別コード 
            l_fXGftAskingTelegramUnit.requestNumber = "122";
            //
            //　@　@ 03：口座追加
//          //　@メールアドレス 
          l_fXGftAskingTelegramUnit.fxMailAddress = "zhang-tengyu@@sinocom.cn";

//           　@初期パスワード 
          l_fXGftAskingTelegramUnit.fxFirstPassword = "111111";
          //　@名前（姓） 
          l_fXGftAskingTelegramUnit.fxLastName = "zhang";

          
          WEB3FXGftResultNoticeTelegramUnit l_fXGftResultNoticeTelegramUnit =
              l_impl.createGftResultNoticeTelegramUnit(l_fXGftAskingTelegramUnit, null, "111");

          assertEquals("20090922121212", l_fXGftResultNoticeTelegramUnit.dirSendTime);
          assertEquals("03", l_fXGftResultNoticeTelegramUnit.gftOperationDiv);
          assertEquals("111", l_fXGftResultNoticeTelegramUnit.fxFirstLoginId);
          assertEquals("2", l_fXGftResultNoticeTelegramUnit.groupName);
          assertEquals("07", l_fXGftResultNoticeTelegramUnit.institutionCode);
          assertEquals("aaa", l_fXGftResultNoticeTelegramUnit.wolfSession);
          assertEquals("bbb", l_fXGftResultNoticeTelegramUnit.wolfAid);
          assertEquals("ccc", l_fXGftResultNoticeTelegramUnit.regetServiceId);
          assertEquals("ddd", l_fXGftResultNoticeTelegramUnit.wolfSsid);
          assertEquals("101", l_fXGftResultNoticeTelegramUnit.branchCode);
          assertEquals("1101", l_fXGftResultNoticeTelegramUnit.accountCode);
          assertEquals("122", l_fXGftResultNoticeTelegramUnit.requestNumber);
//          assertEquals("04", l_fXGftResultNoticeTelegramUnit.fxAccountCode);
//          assertEquals("100", l_fXGftResultNoticeTelegramUnit.cashinoutAmt);
//          assertNull(l_fXGftResultNoticeTelegramUnit.deliveryDate);
          assertEquals("111", l_fXGftResultNoticeTelegramUnit.resultCode);
          assertEquals("zhang-tengyu@@sinocom.cn", l_fXGftResultNoticeTelegramUnit.fxMailAddress);
          assertEquals("111111", l_fXGftResultNoticeTelegramUnit.fxFirstPassword);
          assertEquals("zhang", l_fXGftResultNoticeTelegramUnit.fxLastName);
            
        }
        catch (Exception l_ex)
        {
            log.error("" + l_ex);
            fail();
        }
    }
    
    public void testCreateGftResultNoticeTelegramUnitCase5()
    {
        final String STR_METHOD_NAME = "testSendExtConnAskingMessageCase5()";
        log.entering (STR_METHOD_NAME );   
        
        try
        {
            WEB3FXGftAskingTelegramUnit l_fXGftAskingTelegramUnit = new WEB3FXGftAskingTelegramUnit();
            //　@DIR→GFT送信日時 
            l_fXGftAskingTelegramUnit.dirSendTime = "20090922121212";
            //　@処理区分 
            l_fXGftAskingTelegramUnit.gftOperationDiv = "04";
            //　@初期ログインID 
            l_fXGftAskingTelegramUnit.fxFirstLoginId = "111";
            //　@担当区分 
            l_fXGftAskingTelegramUnit.groupName = "2";
            //　@会社コード 
            l_fXGftAskingTelegramUnit.institutionCode = "07";
            //　@WOLFセッションキー 
            l_fXGftAskingTelegramUnit.wolfSession = "aaa";
            //　@アプリケーションID 
            l_fXGftAskingTelegramUnit.wolfAid = "bbb";
            //　@再生成サービスID 
            l_fXGftAskingTelegramUnit.regetServiceId = "ccc";
            //　@SSID 
            l_fXGftAskingTelegramUnit.wolfSsid = "ddd";
            //　@部店コード 
            l_fXGftAskingTelegramUnit.branchCode = "101";
            //　@顧客コード 
            l_fXGftAskingTelegramUnit.accountCode = "1101";
            //　@識別コード 
            l_fXGftAskingTelegramUnit.requestNumber = "122";
            //
            //　@　@処理区分 = 04：出金の場合： 
//            //　@メールアドレス 
//            l_fXGftAskingTelegramUnit.fxMailAddress = "";
            //　@為替保証金口座番号
            l_fXGftAskingTelegramUnit.fxAccountCode = "04";
//             　@初期パスワード 
            //　@名前（姓） 
            l_fXGftAskingTelegramUnit.fxLastName = "zhang";
            // 　@名前（名） 
            l_fXGftAskingTelegramUnit.fxFirstName = "san";
            //　@入出金額 
            l_fXGftAskingTelegramUnit.cashinoutAmt = "100";
            
            WEB3FXAccInformationUnit[] l_fXAccInformationUnits = new WEB3FXAccInformationUnit[3];
            WEB3FXAccInformationUnit l_fxAccInformationUnit1 = new WEB3FXAccInformationUnit();
            l_fxAccInformationUnit1.fxCourseDiv = "1";
            l_fxAccInformationUnit1.fxAccountCode ="111";
            WEB3FXAccInformationUnit l_fxAccInformationUnit2 = new WEB3FXAccInformationUnit();
            l_fxAccInformationUnit2.fxCourseDiv = "2";
            l_fxAccInformationUnit2.fxAccountCode ="222";
            WEB3FXAccInformationUnit l_fxAccInformationUnit3 = new WEB3FXAccInformationUnit();
            l_fxAccInformationUnit3.fxCourseDiv = "3";
            l_fxAccInformationUnit3.fxAccountCode ="333";
            l_fXAccInformationUnits[0] = l_fxAccInformationUnit1;
            l_fXAccInformationUnits[1] = l_fxAccInformationUnit2;
            l_fXAccInformationUnits[2] = l_fxAccInformationUnit3;

            WEB3FXGftResultNoticeTelegramUnit l_fXGftResultNoticeTelegramUnit =
                l_impl.createGftResultNoticeTelegramUnit(l_fXGftAskingTelegramUnit, l_fXAccInformationUnits, "111");

            assertEquals("20090922121212", l_fXGftResultNoticeTelegramUnit.dirSendTime);
            assertEquals("04", l_fXGftResultNoticeTelegramUnit.gftOperationDiv);
            assertEquals("111", l_fXGftResultNoticeTelegramUnit.fxFirstLoginId);
            assertEquals("2", l_fXGftResultNoticeTelegramUnit.groupName);
            assertEquals("07", l_fXGftResultNoticeTelegramUnit.institutionCode);
            assertEquals("aaa", l_fXGftResultNoticeTelegramUnit.wolfSession);
            assertEquals("bbb", l_fXGftResultNoticeTelegramUnit.wolfAid);
            assertEquals("ccc", l_fXGftResultNoticeTelegramUnit.regetServiceId);
            assertEquals("ddd", l_fXGftResultNoticeTelegramUnit.wolfSsid);
            assertEquals("101", l_fXGftResultNoticeTelegramUnit.branchCode);
            assertEquals("1101", l_fXGftResultNoticeTelegramUnit.accountCode);
            assertEquals("122", l_fXGftResultNoticeTelegramUnit.requestNumber);
            assertEquals("04", l_fXGftResultNoticeTelegramUnit.fxAccountCode);
            assertEquals("100", l_fXGftResultNoticeTelegramUnit.cashinoutAmt);
            assertNull(l_fXGftResultNoticeTelegramUnit.deliveryDate);
            assertEquals("111", l_fXGftResultNoticeTelegramUnit.resultCode);
//            assertEquals("111", l_fXGftResultNoticeTelegramUnit.fxMailAddress);
            assertEquals("zhang", l_fXGftResultNoticeTelegramUnit.fxLastName);
            assertEquals("san", l_fXGftResultNoticeTelegramUnit.fxFirstName);
            assertEquals("111", l_fXGftResultNoticeTelegramUnit.gftAcc1);
            assertEquals("222", l_fXGftResultNoticeTelegramUnit.gftAcc2);
            assertEquals("3", l_fXGftResultNoticeTelegramUnit.fxAccInformationList.length + "");
            
        }
        catch (Exception l_ex)
        {
            log.error("" + l_ex);
            fail();
        }
    }

    public void testSetSOAPConnectionProxy_Case001()
    {
        final String STR_METHOD_NAME = "testSetSOAPConnectionProxy_Case001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(SoapConnectPrefRpcParams.TYPE);
            SoapConnectPrefRpcParams l_soapConnectPrefRpcParams = new SoapConnectPrefRpcParams();
            l_soapConnectPrefRpcParams.setBranchId(123456789);
            l_soapConnectPrefRpcParams.setConnectDiv("01");
            l_soapConnectPrefRpcParams.setEndpointName("http://10.253.111.117:8080/axis2");
            l_soapConnectPrefRpcParams.setTargetNamespaceName("0");
            l_soapConnectPrefRpcParams.setOperationName("0");
            l_soapConnectPrefRpcParams.setParameterList("1");
            l_soapConnectPrefRpcParams.setConnectDiv("1");
            l_soapConnectPrefRpcParams.setServiceName("1");
            l_soapConnectPrefRpcParams.setPortTypeName("1");
            l_soapConnectPrefRpcParams.setResponseParamType("1");
            l_soapConnectPrefRpcParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_soapConnectPrefRpcParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_soapConnectPrefRpcParams);
            l_impl.setSOAPConnectionProxy(l_soapConnectPrefRpcParams);
            assertNull(System.getProperty("https.proxyHost"));
            assertNull(System.getProperty("https.proxyPort"));
            assertNull(System.getProperty("weblogic.webservice.transport.https.proxy.host"));
            assertNull(System.getProperty("weblogic.webservice.transport.https.proxy.port"));
            assertNull(System.getProperty("http.proxyHost"));
            assertNull(System.getProperty("http.proxyPort"));
            assertNull(System.getProperty("weblogic.webservice.transport.http.proxy.host"));
            assertNull(System.getProperty("weblogic.webservice.transport.http.proxy.port"));
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSetSOAPConnectionProxy_Case002()
    {
        final String STR_METHOD_NAME = "testSetSOAPConnectionProxy_Case002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(SoapConnectPrefRpcParams.TYPE);
            SoapConnectPrefRpcParams l_soapConnectPrefRpcParams = new SoapConnectPrefRpcParams();
            l_soapConnectPrefRpcParams.setBranchId(123456789);
            l_soapConnectPrefRpcParams.setConnectDiv("01");
            l_soapConnectPrefRpcParams.setEndpointName("http://10.253.111.117:8080/axis2;8080");
            l_soapConnectPrefRpcParams.setTargetNamespaceName("0");
            l_soapConnectPrefRpcParams.setOperationName("0");
            l_soapConnectPrefRpcParams.setParameterList("1");
            l_soapConnectPrefRpcParams.setConnectDiv("1");
            l_soapConnectPrefRpcParams.setServiceName("1");
            l_soapConnectPrefRpcParams.setPortTypeName("1");
            l_soapConnectPrefRpcParams.setResponseParamType("1");
            l_soapConnectPrefRpcParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_soapConnectPrefRpcParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_soapConnectPrefRpcParams);
            l_impl.setSOAPConnectionProxy(l_soapConnectPrefRpcParams);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02398, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSetSOAPConnectionProxy_Case003()
    {
        final String STR_METHOD_NAME = "testSetSOAPConnectionProxy_Case003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(SoapConnectPrefRpcParams.TYPE);
            SoapConnectPrefRpcParams l_soapConnectPrefRpcParams = new SoapConnectPrefRpcParams();
            l_soapConnectPrefRpcParams.setBranchId(123456789);
            l_soapConnectPrefRpcParams.setConnectDiv("01");
            l_soapConnectPrefRpcParams.setEndpointName("http://10.253.111.117:8080/axis2;8080;http");
            l_soapConnectPrefRpcParams.setTargetNamespaceName("0");
            l_soapConnectPrefRpcParams.setOperationName("0");
            l_soapConnectPrefRpcParams.setParameterList("1");
            l_soapConnectPrefRpcParams.setConnectDiv("1");
            l_soapConnectPrefRpcParams.setServiceName("1");
            l_soapConnectPrefRpcParams.setPortTypeName("1");
            l_soapConnectPrefRpcParams.setResponseParamType("1");
            l_soapConnectPrefRpcParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_soapConnectPrefRpcParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_soapConnectPrefRpcParams);
            l_impl.setSOAPConnectionProxy(l_soapConnectPrefRpcParams);
            assertEquals(System.getProperty("http.proxyHost"), "http://10.253.111.117:8080/axis2");
            assertEquals(System.getProperty("http.proxyPort"), "8080");
            assertEquals(System.getProperty("weblogic.webservice.transport.http.proxy.host"),
                "http://10.253.111.117:8080/axis2");
            assertEquals(System.getProperty("weblogic.webservice.transport.http.proxy.port"),
                "8080");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSetSOAPConnectionProxy_Case004()
    {
        final String STR_METHOD_NAME = "testSetSOAPConnectionProxy_Case004()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(SoapConnectPrefRpcParams.TYPE);
            SoapConnectPrefRpcParams l_soapConnectPrefRpcParams = new SoapConnectPrefRpcParams();
            l_soapConnectPrefRpcParams.setBranchId(123456789);
            l_soapConnectPrefRpcParams.setConnectDiv("01");
            l_soapConnectPrefRpcParams.setEndpointName("https://10.253.111.117:8080/axis2;8080;https");
            l_soapConnectPrefRpcParams.setTargetNamespaceName("0");
            l_soapConnectPrefRpcParams.setOperationName("0");
            l_soapConnectPrefRpcParams.setParameterList("1");
            l_soapConnectPrefRpcParams.setConnectDiv("1");
            l_soapConnectPrefRpcParams.setServiceName("1");
            l_soapConnectPrefRpcParams.setPortTypeName("1");
            l_soapConnectPrefRpcParams.setResponseParamType("1");
            l_soapConnectPrefRpcParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_soapConnectPrefRpcParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_soapConnectPrefRpcParams);
            l_impl.setSOAPConnectionProxy(l_soapConnectPrefRpcParams);
            assertEquals(System.getProperty("https.proxyHost"), "https://10.253.111.117:8080/axis2");
            assertEquals(System.getProperty("https.proxyPort"), "8080");
            assertEquals(System.getProperty("weblogic.webservice.transport.https.proxy.host"),
                "https://10.253.111.117:8080/axis2");
            assertEquals(System.getProperty("weblogic.webservice.transport.https.proxy.port"),
                "8080");
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
