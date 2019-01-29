head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.03.50;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminDirSecAccRegVoucherStatUpdServiceImplTest_xhw.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : //TODO(WEB3AdminDirSecAccRegVoucherStatUpdServiceImplTest_xhw.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/06/18 徐宏偉 (中訊) 新規作成
*/
package webbroker3.dirsec.service.delegate.stdimpls;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;

import test.util.TestDBUtility;

import webbroker3.accountopen.data.AccOpenVoucherStatusDao;
import webbroker3.accountopen.data.AccOpenVoucherStatusParams;
import webbroker3.accountopen.data.AccOpenVoucherStatusRow;
import webbroker3.accountopen.data.ExpAccountOpenParams;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.dirsec.message.WEB3AdminDirSecAccRegVoucherSearchResRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAccVoucherRecordDetail;
import webbroker3.inform.data.VariousInformDao;
import webbroker3.inform.data.VariousInformParams;
import webbroker3.inform.data.VariousInformRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * XXXXXXクラス//TODO
 *
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public class WEB3AdminDirSecAccRegVoucherStatUpdServiceImplTest_xhw extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3AdminDirSecAccRegVoucherStatUpdServiceImplTest_xhw.class);

    public WEB3AdminDirSecAccRegVoucherStatUpdServiceImpl l_impl =
        new WEB3AdminDirSecAccRegVoucherStatUpdServiceImpl();
    public WEB3AdminDirSecAccRegVoucherStatUpdServiceImplTest_xhw(String arg0)
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

    /*
     * "３-１） の戻り値（口座開設見込客テーブルList）長さ=1 の場合
     * 返回?識別コード的確認"
     * "（引数）証券会社コード=123
     * （引数）l_request.部店コード=624
     * （引数）l_request.顧客コード=123456
     * 首先清空口座開設見込客テーブル
     * 之後向口座開設見込客テーブル中插入一條數據如@下：
     * 1）証券会社コード=123，部店コード=624，顧客コード=123456，識別コード=444"
     * assertEquals("444", 方法@返回?[0]);
     * 正常
     */
    public void testGetRequestCode_case001()
    {
        final String STR_METHOD_NAME = "testGetRequestCode_case001";
        log.entering(STR_METHOD_NAME);
        Method l_method;

        try
        {
            this.ClearData();
            WEB3AdminDirSecAccRegVoucherSearchResRequest l_request =
                new WEB3AdminDirSecAccRegVoucherSearchResRequest();
            l_request.branchCode = "624";
            l_request.accountCode = "123456";
            String l_strInstitutionCode = "123";

            ExpAccountOpenParams l_expAccountOpenParams = this.getExpAccountOpenParams();
            l_expAccountOpenParams.setInstitutionCode("123");
            l_expAccountOpenParams.setBranchCode("624");
            l_expAccountOpenParams.setAccountCode("123456");
            l_expAccountOpenParams.setAccOpenRequestNumber("444");
            TestDBUtility.insertWithDel(l_expAccountOpenParams);

            l_method = WEB3AdminDirSecAccRegVoucherStatUpdServiceImpl.class.getDeclaredMethod(
                "getRequestNumber", new Class[]{
                    WEB3AdminDirSecAccRegVoucherSearchResRequest.class, String.class});
            l_method.setAccessible(true);
            Object l_obj = l_method.invoke(l_impl, new Object[]{l_request, l_strInstitutionCode});
            Object[] l_objA = (Object[])l_obj;
            log.debug("l_objA[0]" + l_objA[0]);
            assertEquals("444", l_objA[0]);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * "３-１） の戻り値（口座開設見込客テーブルList）長さ=0 の場合
     * 返回?識別コード的確認"
     * "（引数）証券会社コード=123
     * （引数）l_request.部店コード=624
     * （引数）l_request.顧客コード=123456
     * 清空口座開設見込客テーブル"  assertEquals(null, 方法@返回?);
     */
    public void testGetRequestCode_case002()
    {
        final String STR_METHOD_NAME = "testGetRequestCode_case002";
        log.entering(STR_METHOD_NAME);
        Method l_method;

        try
        {
            this.ClearData();
            WEB3AdminDirSecAccRegVoucherSearchResRequest l_request =
                new WEB3AdminDirSecAccRegVoucherSearchResRequest();
            l_request.branchCode = "624";
            l_request.accountCode = "123456";
            String l_strInstitutionCode = "123";

            l_method = WEB3AdminDirSecAccRegVoucherStatUpdServiceImpl.class.getDeclaredMethod(
                "getRequestNumber", new Class[]{
                    WEB3AdminDirSecAccRegVoucherSearchResRequest.class, String.class});
            l_method.setAccessible(true);
            Object l_obj = l_method.invoke(l_impl, new Object[]{l_request, l_strInstitutionCode});
            assertEquals(null, l_obj);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * "３-１） の戻り値（口座開設見込客テーブルList）長さ=3 の場合
     * 返回?識別コード的確認"    "（引数）証券会社コード=123
     * （引数）l_request.部店コード=624
     * （引数）l_request.顧客コード=123456
     * 首先清空口座開設見込客テーブル
     * 之後向口座開設見込客テーブル中插入3條數據如@下：
     * 1）証券会社コード=123，部店コード=624，顧客コード=123456，識別コード=444
     * 2）証券会社コード=123，部店コード=624，顧客コード=123456，識別コード=555
     * 3）証券会社コード=123，部店コード=624，顧客コード=123456，識別コード=666"
     * "assertEquals(""444"", 方法@返回?[0]);
     * assertEquals(""555"", 方法@返回?[1]);
     * assertEquals(""666"", 方法@返回?[2]);"
     */
    public void testGetRequestCode_case003()
    {
        final String STR_METHOD_NAME = "testGetRequestCode_case003";
        log.entering(STR_METHOD_NAME);
        Method l_method;

        try
        {
            this.ClearData();
            WEB3AdminDirSecAccRegVoucherSearchResRequest l_request =
                new WEB3AdminDirSecAccRegVoucherSearchResRequest();
            l_request.branchCode = "624";
            l_request.accountCode = "123456";
            String l_strInstitutionCode = "123";

            ExpAccountOpenParams l_expAccountOpenParams = this.getExpAccountOpenParams();
            l_expAccountOpenParams.setInstitutionCode("123");
            l_expAccountOpenParams.setBranchCode("624");
            l_expAccountOpenParams.setAccountCode("123456");
            l_expAccountOpenParams.setAccOpenRequestNumber("444");
            TestDBUtility.insertWithDel(l_expAccountOpenParams);

            l_expAccountOpenParams.setInstitutionCode("123");
            l_expAccountOpenParams.setBranchCode("624");
            l_expAccountOpenParams.setAccountCode("123456");
            l_expAccountOpenParams.setAccOpenRequestNumber("555");
            TestDBUtility.insertWithDel(l_expAccountOpenParams);

            l_expAccountOpenParams.setInstitutionCode("123");
            l_expAccountOpenParams.setBranchCode("624");
            l_expAccountOpenParams.setAccountCode("123456");
            l_expAccountOpenParams.setAccOpenRequestNumber("666");
            TestDBUtility.insertWithDel(l_expAccountOpenParams);

            l_method = WEB3AdminDirSecAccRegVoucherStatUpdServiceImpl.class.getDeclaredMethod(
                "getRequestNumber", new Class[]{
                    WEB3AdminDirSecAccRegVoucherSearchResRequest.class, String.class});
            l_method.setAccessible(true);
            Object l_obj = l_method.invoke(l_impl, new Object[]{l_request, l_strInstitutionCode});
            Object[] l_objA = (Object[])l_obj;
            log.debug("l_objA[0]" + l_objA[0]);
            assertEquals("444", l_objA[0]);
            assertEquals("555", l_objA[1]);
            assertEquals("666", l_objA[2]);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * "（引数）伝票作成状況変換フラグ == TRUE の場合&&（引数）status == 3（受付完了） の時
     * 4 （受信済）を返却する。
     * （引数）伝票作成状況変換フラグ == TRUE
     * （引数）status == 3"
     * assertEquals("4", 方法@返回?);
     */
    public void testChangeStatus_case001()
    {
        final String STR_METHOD_NAME = "testChangeStatus_case001";
        log.entering(STR_METHOD_NAME);
        Method l_method;

        try
        {
            this.ClearData();
            String l_strStatus = "3";
            boolean l_blnIsVoucherStatusChangeFlag = true;

            l_method = WEB3AdminDirSecAccRegVoucherStatUpdServiceImpl.class.getDeclaredMethod(
                "changeStatus", new Class[]{
                    String.class, boolean.class});
            l_method.setAccessible(true);
            Object l_obj = l_method.invoke(l_impl, new Object[]{
                l_strStatus, new Boolean(l_blnIsVoucherStatusChangeFlag)});
            String l_strResult = (String)l_obj;
            assertEquals("4", l_strResult);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * "（引数）伝票作成状況変換フラグ == TRUE の場合&&（引数）status == 4（受付エラー） の時
     * 6（受信エラー）を返却する。
     * （引数）伝票作成状況変換フラグ == TRUE
     * （引数）status == 4"
     *  assertEquals("6", 方法@返回?);
     */
    public void testChangeStatus_case002()
    {
        final String STR_METHOD_NAME = "testChangeStatus_case002";
        log.entering(STR_METHOD_NAME);
        Method l_method;

        try
        {
            this.ClearData();
            String l_strStatus = "4";
            boolean l_blnIsVoucherStatusChangeFlag = true;

            l_method = WEB3AdminDirSecAccRegVoucherStatUpdServiceImpl.class.getDeclaredMethod(
                "changeStatus", new Class[]{
                    String.class, boolean.class});
            l_method.setAccessible(true);
            Object l_obj = l_method.invoke(l_impl, new Object[]{
                l_strStatus, new Boolean(l_blnIsVoucherStatusChangeFlag)});
            String l_strResult = (String)l_obj;
            assertEquals("6", l_strResult);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * "（引数）伝票作成状況変換フラグ == FALSE の場合
     * （引数）statusを返却する。
     * （引数）伝票作成状況変換フラグ == false
     * （引数）status == 4"    assertEquals("4", 方法@返回?);
     */
    public void testChangeStatus_case003()
    {
        final String STR_METHOD_NAME = "testChangeStatus_case003";
        log.entering(STR_METHOD_NAME);
        Method l_method;

        try
        {
            this.ClearData();
            String l_strStatus = "4";
            boolean l_blnIsVoucherStatusChangeFlag = false;

            l_method = WEB3AdminDirSecAccRegVoucherStatUpdServiceImpl.class.getDeclaredMethod(
                "changeStatus", new Class[]{
                    String.class, boolean.class});
            l_method.setAccessible(true);
            Object l_obj = l_method.invoke(l_impl, new Object[]{
                l_strStatus, new Boolean(l_blnIsVoucherStatusChangeFlag)});
            String l_strResult = (String)l_obj;
            assertEquals("4", l_strResult);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * パラメータ値がNULL！
     * （引数）レコードList==null
     * throw 80017
     */
    public void testCreateAccInfoRegVoucher_case001()
    {
        final String STR_METHOD_NAME = "testCreateAccInfoRegVoucher_case001";
        log.entering(STR_METHOD_NAME);
        Method l_method;

        try
        {
            this.ClearData();
            List l_lisRecordList = null;
            boolean l_blnIsAccOpenVoucherFlag = false;
            String l_strBranchCode = null;
            String l_strAccountCode = null;
            ArrayList l_lisRecords = null;

            l_method = WEB3AdminDirSecAccRegVoucherStatUpdServiceImpl.class.getDeclaredMethod(
                "createAccInfoRegVoucher", new Class[]{
                        List.class, boolean.class, String.class, String.class, ArrayList.class});
            l_method.setAccessible(true);
            Object l_obj = l_method.invoke(l_impl, new Object[]{
                l_lisRecordList, new Boolean(l_blnIsAccOpenVoucherFlag),
                l_strBranchCode, l_strAccountCode, l_lisRecords});
            fail();
        }
        catch (InvocationTargetException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                ((WEB3BaseException)l_ex.getTargetException()).getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * "（引数）レコードListの長 == 1
     * （引数）口座開設伝票フラグ == FALSE の場合
     * （引数）レコードList[0].証券会社コード = 123
     * （引数）レコードList[0].部店コード = 624
     * （引数）レコードList[0].顧客コード = 123456
     * （引数）レコードList[0].データコード = 222
     * （引数）レコードList[0].識別コード = 333
     * （引数）レコードList[0].伝票作成状況 = 1
     * （引数）レコードList[0].エラー理由コード =11
     * （引数）レコードList[0].伝票送信日時 = “20070606”
     * （引数）レコードList[0].伝票受信日時 =“20070606”
     * （引数）レコードList[index].連絡種別  = 1
     * （返回?）顧客情報登録伝票レコード詳細.証券会社コード = 123
     * （返回?）顧客情報登録伝票レコード詳細.部店コード = 624
     * （返回?）顧客情報登録伝票レコード詳細.顧客コード = 123456
     * （返回?）顧客情報登録伝票レコード詳細.データコード = 222
     * （返回?）顧客情報登録伝票レコード詳細.識別コード = 333
     * （返回?）顧客情報登録伝票レコード詳細.伝票作成状況 = 1
     * （返回?）顧客情報登録伝票レコード詳細.エラー理由コード = 11
     * （返回?）顧客情報登録伝票レコード詳細.伝票送信日時  = “20070606”
     * （返回?）顧客情報登録伝票レコード詳細.伝票受信日時  = “20070606”
     * （返回?）顧客情報登録伝票レコード詳細.口座開設伝票フラグ = true
     * （返回?）顧客情報登録伝票レコード詳細.連絡種別 = 1
     * （返回?）顧客情報登録伝票レコード詳細.伝票通番 = null"
     */
    public void testCreateAccInfoRegVoucher_case002()
    {
        final String STR_METHOD_NAME = "testCreateAccInfoRegVoucher_case002";
        log.entering(STR_METHOD_NAME);
        Method l_method;

        try
        {
            this.ClearData();
            VariousInformParams l_variousInformParams = new VariousInformParams();
            l_variousInformParams.setInstitutionCode("123");
            l_variousInformParams.setAccountCode("123456");
            l_variousInformParams.setBranchCode("624");
            l_variousInformParams.setRequestCode("222");
            l_variousInformParams.setRequestNumber("333");
            l_variousInformParams.setStatus("1");
            l_variousInformParams.setErrorReasonCode("11");
            l_variousInformParams.setInformDiv("1");
            l_variousInformParams.setSendTimestamp(WEB3DateUtility.getDate("20070606","yyyyMMdd"));
            l_variousInformParams.setReceiptTimestamp(WEB3DateUtility.getDate("20070606","yyyyMMdd"));
            List l_lisRecordList = new ArrayList();
            l_lisRecordList.add(l_variousInformParams);
            boolean l_blnIsAccOpenVoucherFlag = false;
            String l_strBranchCode = null;
            String l_strAccountCode = null;
            ArrayList l_lisRecords = new ArrayList();

            l_method = WEB3AdminDirSecAccRegVoucherStatUpdServiceImpl.class.getDeclaredMethod(
                "createAccInfoRegVoucher", new Class[]{
                        List.class, boolean.class, String.class, String.class, ArrayList.class});
            l_method.setAccessible(true);
            Object l_obj = l_method.invoke(l_impl, new Object[]{
                l_lisRecordList, new Boolean(l_blnIsAccOpenVoucherFlag),
                l_strBranchCode, l_strAccountCode, l_lisRecords});

//            WEB3AdminDirSecAccVoucherRecordDetail l_paramsReturn = (WEB3AdminDirSecAccVoucherRecordDetail)l_obj;
            WEB3AdminDirSecAccVoucherRecordDetail l_paramsReturn =
                (WEB3AdminDirSecAccVoucherRecordDetail)l_lisRecords.get(0);
            //* （返回?）顧客情報登録伝票レコード詳細.証券会社コード = 123
            assertEquals("123", l_paramsReturn.institutionCode);
            //* （返回?）顧客情報登録伝票レコード詳細.部店コード = 624
            assertEquals("624", l_paramsReturn.branchCode);
            //* （返回?）顧客情報登録伝票レコード詳細.顧客コード = 123456
            assertEquals("123456", l_paramsReturn.accountCode);
            //* （返回?）顧客情報登録伝票レコード詳細.データコード = 222
            assertEquals("222", l_paramsReturn.dataCode);
            //* （返回?）顧客情報登録伝票レコード詳細.識別コード = 333
            assertEquals("333", l_paramsReturn.requestNumber);
            //* （返回?）顧客情報登録伝票レコード詳細.伝票作成状況 = 1
            assertEquals("1", l_paramsReturn.voucherMakeStatus);
            //* （返回?）顧客情報登録伝票レコード詳細.エラー理由コード = 11
            assertEquals("11", l_paramsReturn.errorReasonCode);
            //* （返回?）顧客情報登録伝票レコード詳細.伝票送信日時  = “20070606”
            log.debug("顧客情報登録伝票レコード詳細.伝票送信日時  = " + l_paramsReturn.voucherSendTimestamp);
            //* （返回?）顧客情報登録伝票レコード詳細.伝票受信日時  = “20070606”
            log.debug("顧客情報登録伝票レコード詳細.伝票受信日時  = " + l_paramsReturn.voucherRecvTimestamp);
            //* （返回?）顧客情報登録伝票レコード詳細.口座開設伝票フラグ = true
            assertEquals(false, l_paramsReturn.voucherFlag);
            //* （返回?）顧客情報登録伝票レコード詳細.連絡種別 = 1
            assertEquals("1", l_paramsReturn.infoType);
            //* （返回?）顧客情報登録伝票レコード詳細.伝票通番 = null"
            assertEquals(null, l_paramsReturn.voucherNumber);

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * "（引数）レコードListの長 == 1
     * （引数）口座開設伝票フラグ == true の場合
     * （引数）レコードList[0].証券会社コード = 123
     * （引数）レコードList[0].部店コード = 624
     * （引数）レコードList[0].顧客コード = 123456
     * （引数）レコードList[0].データコード = 222
     * （引数）レコードList[0].識別コード = 333
     * （引数）レコードList[0].伝票作成状況 = 1
     * （引数）レコードList[0].エラー理由コード =11
     * （引数）レコードList[0].伝票送信日時 = “20070606”
     * （引数）レコードList[0].伝票受信日時 =“20070606”
     * （引数）レコードList[index].伝票通番 = 1
     * （返回?）顧客情報登録伝票レコード詳細.証券会社コード = 123
     * （返回?）顧客情報登録伝票レコード詳細.部店コード = 624
     * （返回?）顧客情報登録伝票レコード詳細.顧客コード = 123456
     * （返回?）顧客情報登録伝票レコード詳細.データコード = 222
     * （返回?）顧客情報登録伝票レコード詳細.識別コード = 333
     * （返回?）顧客情報登録伝票レコード詳細.伝票作成状況 = 1
     * （返回?）顧客情報登録伝票レコード詳細.エラー理由コード = 11
     * （返回?）顧客情報登録伝票レコード詳細.伝票送信日時  = “20070606”
     * （返回?）顧客情報登録伝票レコード詳細.伝票受信日時  = “20070606”
     * （返回?）顧客情報登録伝票レコード詳細.口座開設伝票フラグ = true
     * （返回?）顧客情報登録伝票レコード詳細.連絡種別 = null
     * （返回?）顧客情報登録伝票レコード詳細.伝票通番 = 1"   正常
     */
    public void testCreateAccInfoRegVoucher_case003()
    {
        final String STR_METHOD_NAME = "testCreateAccInfoRegVoucher_case003";
        log.entering(STR_METHOD_NAME);
        Method l_method;

        try
        {
            this.ClearData();
            AccOpenVoucherStatusParams l_vccOpenVoucherStatusParams = new AccOpenVoucherStatusParams();
            l_vccOpenVoucherStatusParams.setInstitutionCode("123");
            l_vccOpenVoucherStatusParams.setAccOpenRequestNumber("333");
            l_vccOpenVoucherStatusParams.setRequestCode("222");
            l_vccOpenVoucherStatusParams.setVoucherStatus("1");
            l_vccOpenVoucherStatusParams.setErrorCode("11");
            l_vccOpenVoucherStatusParams.setSerialNo("1");
            l_vccOpenVoucherStatusParams.setSendTimestamp(WEB3DateUtility.getDate("20070606","yyyyMMdd"));
            l_vccOpenVoucherStatusParams.setRecvTimestamp(WEB3DateUtility.getDate("20070606","yyyyMMdd"));
            List l_lisRecordList = new ArrayList();
            l_lisRecordList.add(l_vccOpenVoucherStatusParams);
            boolean l_blnIsAccOpenVoucherFlag = true;
            String l_strBranchCode = "624";
            String l_strAccountCode = "123456";
            ArrayList l_lisRecords = new ArrayList();

            l_method = WEB3AdminDirSecAccRegVoucherStatUpdServiceImpl.class.getDeclaredMethod(
                "createAccInfoRegVoucher", new Class[]{
                        List.class, boolean.class, String.class, String.class, ArrayList.class});
            l_method.setAccessible(true);
            Object l_obj = l_method.invoke(l_impl, new Object[]{
                l_lisRecordList, new Boolean(l_blnIsAccOpenVoucherFlag),
                l_strBranchCode, l_strAccountCode, l_lisRecords});

            WEB3AdminDirSecAccVoucherRecordDetail l_paramsReturn =
                (WEB3AdminDirSecAccVoucherRecordDetail)l_lisRecords.get(0);
            //* （返回?）顧客情報登録伝票レコード詳細.証券会社コード = 123
            assertEquals("123", l_paramsReturn.institutionCode);
            //* （返回?）顧客情報登録伝票レコード詳細.部店コード = 624
            assertEquals("624", l_paramsReturn.branchCode);
            //* （返回?）顧客情報登録伝票レコード詳細.顧客コード = 123456
            assertEquals("123456", l_paramsReturn.accountCode);
            //* （返回?）顧客情報登録伝票レコード詳細.データコード = 222
            assertEquals("222", l_paramsReturn.dataCode);
            //* （返回?）顧客情報登録伝票レコード詳細.識別コード = 333
            assertEquals("333", l_paramsReturn.requestNumber);
            //* （返回?）顧客情報登録伝票レコード詳細.伝票作成状況 = 1
            assertEquals("1", l_paramsReturn.voucherMakeStatus);
            //* （返回?）顧客情報登録伝票レコード詳細.エラー理由コード = 11
            assertEquals("11", l_paramsReturn.errorReasonCode);
            //* （返回?）顧客情報登録伝票レコード詳細.伝票送信日時  = “20070606”
            log.debug("顧客情報登録伝票レコード詳細.伝票送信日時  = " + l_paramsReturn.voucherSendTimestamp);
            //* （返回?）顧客情報登録伝票レコード詳細.伝票受信日時  = “20070606”
            log.debug("顧客情報登録伝票レコード詳細.伝票受信日時  = " + l_paramsReturn.voucherRecvTimestamp);
            //* （返回?）顧客情報登録伝票レコード詳細.口座開設伝票フラグ = true
            assertEquals(true, l_paramsReturn.voucherFlag);
            //* （返回?）顧客情報登録伝票レコード詳細.連絡種別 = 1
            assertEquals(null, l_paramsReturn.infoType);
            //* （返回?）顧客情報登録伝票レコード詳細.伝票通番 = 1"
            assertEquals("1", l_paramsReturn.voucherNumber);

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * "（引数）レコードListの長 == 3
     * （引数）口座開設伝票フラグ == FALSE の場合"
     * 察@看返回?
     */
    public void testCreateAccInfoRegVoucher_case004()
    {
        final String STR_METHOD_NAME = "testCreateAccInfoRegVoucher_case004";
        log.entering(STR_METHOD_NAME);
        Method l_method;

        try
        {
            this.ClearData();
            AccOpenVoucherStatusParams l_vccOpenVoucherStatusParams = new AccOpenVoucherStatusParams();
            l_vccOpenVoucherStatusParams.setInstitutionCode("123");
            l_vccOpenVoucherStatusParams.setAccOpenRequestNumber("333");
            l_vccOpenVoucherStatusParams.setRequestCode("222");
            l_vccOpenVoucherStatusParams.setVoucherStatus("1");
            l_vccOpenVoucherStatusParams.setErrorCode("11");
            l_vccOpenVoucherStatusParams.setSerialNo("1");
            l_vccOpenVoucherStatusParams.setSendTimestamp(WEB3DateUtility.getDate("20070606","yyyyMMdd"));
            l_vccOpenVoucherStatusParams.setRecvTimestamp(WEB3DateUtility.getDate("20070606","yyyyMMdd"));
            List l_lisRecordList = new ArrayList();
            l_lisRecordList.add(l_vccOpenVoucherStatusParams);
            l_lisRecordList.add(l_vccOpenVoucherStatusParams);
            l_lisRecordList.add(l_vccOpenVoucherStatusParams);
            boolean l_blnIsAccOpenVoucherFlag = true;
            String l_strBranchCode = "624";
            String l_strAccountCode = "123456";
            ArrayList l_lisRecords = new ArrayList();

            l_method = WEB3AdminDirSecAccRegVoucherStatUpdServiceImpl.class.getDeclaredMethod(
                "createAccInfoRegVoucher", new Class[]{
                        List.class, boolean.class, String.class, String.class, ArrayList.class});
            l_method.setAccessible(true);
            Object l_obj = l_method.invoke(l_impl, new Object[]{
                l_lisRecordList, new Boolean(l_blnIsAccOpenVoucherFlag),
                l_strBranchCode, l_strAccountCode, l_lisRecords});

            WEB3AdminDirSecAccVoucherRecordDetail l_paramsReturn =
                (WEB3AdminDirSecAccVoucherRecordDetail)l_lisRecords.get(0);
            WEB3AdminDirSecAccVoucherRecordDetail l_paramsReturn1 =
                (WEB3AdminDirSecAccVoucherRecordDetail)l_lisRecords.get(1);
            WEB3AdminDirSecAccVoucherRecordDetail l_paramsReturn2 =
                (WEB3AdminDirSecAccVoucherRecordDetail)l_lisRecords.get(2);
            //* （返回?）顧客情報登録伝票レコード詳細.証券会社コード = 123
            assertEquals("123", l_paramsReturn.institutionCode);
            //* （返回?）顧客情報登録伝票レコード詳細.部店コード = 624
            assertEquals("624", l_paramsReturn.branchCode);
            //* （返回?）顧客情報登録伝票レコード詳細.顧客コード = 123456
            assertEquals("123456", l_paramsReturn.accountCode);
            //* （返回?）顧客情報登録伝票レコード詳細.データコード = 222
            assertEquals("222", l_paramsReturn.dataCode);
            //* （返回?）顧客情報登録伝票レコード詳細.識別コード = 333
            assertEquals("333", l_paramsReturn.requestNumber);
            //* （返回?）顧客情報登録伝票レコード詳細.伝票作成状況 = 1
            assertEquals("1", l_paramsReturn.voucherMakeStatus);
            //* （返回?）顧客情報登録伝票レコード詳細.エラー理由コード = 11
            assertEquals("11", l_paramsReturn.errorReasonCode);
            //* （返回?）顧客情報登録伝票レコード詳細.伝票送信日時  = “20070606”
            log.debug("顧客情報登録伝票レコード詳細.伝票送信日時  = " + l_paramsReturn.voucherSendTimestamp);
            //* （返回?）顧客情報登録伝票レコード詳細.伝票受信日時  = “20070606”
            log.debug("顧客情報登録伝票レコード詳細.伝票受信日時  = " + l_paramsReturn.voucherRecvTimestamp);
            //* （返回?）顧客情報登録伝票レコード詳細.口座開設伝票フラグ = true
            assertEquals(true, l_paramsReturn.voucherFlag);
            //* （返回?）顧客情報登録伝票レコード詳細.連絡種別 = 1
            assertEquals(null, l_paramsReturn.infoType);
            //* （返回?）顧客情報登録伝票レコード詳細.伝票通番 = 1"
            assertEquals("1", l_paramsReturn.voucherNumber);

            //* （返回?）顧客情報登録伝票レコード詳細.証券会社コード = 123
            assertEquals("123", l_paramsReturn1.institutionCode);
            //* （返回?）顧客情報登録伝票レコード詳細.部店コード = 624
            assertEquals("624", l_paramsReturn1.branchCode);
            //* （返回?）顧客情報登録伝票レコード詳細.顧客コード = 123456
            assertEquals("123456", l_paramsReturn1.accountCode);
            //* （返回?）顧客情報登録伝票レコード詳細.データコード = 222
            assertEquals("222", l_paramsReturn1.dataCode);
            //* （返回?）顧客情報登録伝票レコード詳細.識別コード = 333
            assertEquals("333", l_paramsReturn1.requestNumber);
            //* （返回?）顧客情報登録伝票レコード詳細.伝票作成状況 = 1
            assertEquals("1", l_paramsReturn1.voucherMakeStatus);
            //* （返回?）顧客情報登録伝票レコード詳細.エラー理由コード = 11
            assertEquals("11", l_paramsReturn1.errorReasonCode);
            //* （返回?）顧客情報登録伝票レコード詳細.伝票送信日時  = “20070606”
            log.debug("顧客情報登録伝票レコード詳細.伝票送信日時  = " + l_paramsReturn1.voucherSendTimestamp);
            //* （返回?）顧客情報登録伝票レコード詳細.伝票受信日時  = “20070606”
            log.debug("顧客情報登録伝票レコード詳細.伝票受信日時  = " + l_paramsReturn1.voucherRecvTimestamp);
            //* （返回?）顧客情報登録伝票レコード詳細.口座開設伝票フラグ = true
            assertEquals(true, l_paramsReturn1.voucherFlag);
            //* （返回?）顧客情報登録伝票レコード詳細.連絡種別 = 1
            assertEquals(null, l_paramsReturn1.infoType);
            //* （返回?）顧客情報登録伝票レコード詳細.伝票通番 = 1"
            assertEquals("1", l_paramsReturn1.voucherNumber);

            //* （返回?）顧客情報登録伝票レコード詳細.証券会社コード = 123
            assertEquals("123", l_paramsReturn2.institutionCode);
            //* （返回?）顧客情報登録伝票レコード詳細.部店コード = 624
            assertEquals("624", l_paramsReturn2.branchCode);
            //* （返回?）顧客情報登録伝票レコード詳細.顧客コード = 123456
            assertEquals("123456", l_paramsReturn2.accountCode);
            //* （返回?）顧客情報登録伝票レコード詳細.データコード = 222
            assertEquals("222", l_paramsReturn2.dataCode);
            //* （返回?）顧客情報登録伝票レコード詳細.識別コード = 333
            assertEquals("333", l_paramsReturn2.requestNumber);
            //* （返回?）顧客情報登録伝票レコード詳細.伝票作成状況 = 1
            assertEquals("1", l_paramsReturn2.voucherMakeStatus);
            //* （返回?）顧客情報登録伝票レコード詳細.エラー理由コード = 11
            assertEquals("11", l_paramsReturn2.errorReasonCode);
            //* （返回?）顧客情報登録伝票レコード詳細.伝票送信日時  = “20070606”
            log.debug("顧客情報登録伝票レコード詳細.伝票送信日時  = " + l_paramsReturn2.voucherSendTimestamp);
            //* （返回?）顧客情報登録伝票レコード詳細.伝票受信日時  = “20070606”
            log.debug("顧客情報登録伝票レコード詳細.伝票受信日時  = " + l_paramsReturn2.voucherRecvTimestamp);
            //* （返回?）顧客情報登録伝票レコード詳細.口座開設伝票フラグ = true
            assertEquals(true, l_paramsReturn2.voucherFlag);
            //* （返回?）顧客情報登録伝票レコード詳細.連絡種別 = 1
            assertEquals(null, l_paramsReturn2.infoType);
            //* （返回?）顧客情報登録伝票レコード詳細.伝票通番 = 1"
            assertEquals("1", l_paramsReturn2.voucherNumber);

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * （引数）顧客情報 == null の場合
     * （引数）顧客情報 == null
     * throw 80017
     */
    public void testUpdateVariousInform_case001()
    {
        final String STR_METHOD_NAME = "testUpdateVariousInform_case001";
        log.entering(STR_METHOD_NAME);
        Method l_method;

        try
        {
            this.ClearData();
            WEB3AdminDirSecAccVoucherRecordDetail l_accountInfo = null;
            String l_strVoucherCreateStatus = null;
            String l_strErrorReasonCode = null;

            l_method = WEB3AdminDirSecAccRegVoucherStatUpdServiceImpl.class.getDeclaredMethod(
                "updateVariousInform", new Class[]{
                    WEB3AdminDirSecAccVoucherRecordDetail.class, String.class, String.class});
            l_method.setAccessible(true);
            Object l_obj = l_method.invoke(l_impl, new Object[]{
                l_accountInfo, l_strVoucherCreateStatus,
                    l_strErrorReasonCode});
            fail();
        }
        catch (InvocationTargetException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                ((WEB3BaseException)l_ex.getTargetException()).getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * " ３-１）各種連絡テーブルへ?索 の戻り値長さ == 0 の場合
     *  「レコードが存在しません。」の例外をスローする"   清空各種連絡テーブル
     *  「レコードが存在しません。」の例外をスローする
     */
    public void testUpdateVariousInform_case002()
    {
        final String STR_METHOD_NAME = "testUpdateVariousInform_case002";
        log.entering(STR_METHOD_NAME);
        Method l_method;

        try
        {
            this.ClearData();
            WEB3AdminDirSecAccVoucherRecordDetail l_accountInfo =
                new WEB3AdminDirSecAccVoucherRecordDetail();
            String l_strVoucherCreateStatus = null;
            String l_strErrorReasonCode = null;

            l_method = WEB3AdminDirSecAccRegVoucherStatUpdServiceImpl.class.getDeclaredMethod(
                "updateVariousInform", new Class[]{
                    WEB3AdminDirSecAccVoucherRecordDetail.class, String.class, String.class});
            l_method.setAccessible(true);
            Object l_obj = l_method.invoke(l_impl, new Object[]{
                l_accountInfo, l_strVoucherCreateStatus,
                    l_strErrorReasonCode});
            fail();
        }
        catch (InvocationTargetException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02837,
                ((WEB3BaseException)l_ex.getTargetException()).getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     *  ３-１）各種連絡テーブルへ?索 の戻り値長さ == 1 の場合各種連絡テーブルの更新
     *  1）（引数）伝票作成状況 = 1
     *  2）（引数）エラー理由コード = 2
     *  3)向数据?中的各種連絡テーブル中插入数据如@下：
     *  各種連絡テーブル.証券会社コード = 123
     *  各種連絡テーブル.連絡種別  = 1
     *  各種連絡テーブル.識別コード  = 24
     *  各種連絡テーブル.部店コード  = 624
     *  4)（引数）顧客情報.証券会社コード = 123
     *  （引数）顧客情報.連絡種別 = 1
     *  （引数）顧客情報.識別コード=24
     *  （引数）顧客情報.部店コード  =624
     *  "   "各種連絡テーブルの更新如@下：
     *  各種連絡テーブル.status = 1
     *  各種連絡テーブル.：""error_reason_code = 2
     *  各種連絡テーブル.receipt_timestamp = ?在日?"
     */
    public void testUpdateVariousInform_case003()
    {
        final String STR_METHOD_NAME = "testUpdateVariousInform_case003";
        log.entering(STR_METHOD_NAME);
        Method l_method;

        try
        {
            this.ClearData();
            WEB3AdminDirSecAccVoucherRecordDetail l_accountInfo =
                new WEB3AdminDirSecAccVoucherRecordDetail();
            l_accountInfo.institutionCode = "123";
            l_accountInfo.infoType = "1";
            l_accountInfo.requestNumber = "24";
            l_accountInfo.branchCode = "624";
            String l_strVoucherCreateStatus = "1";
            String l_strErrorReasonCode = "2";

            VariousInformParams l_variousInforParams = this.getVariousInformRow();
            l_variousInforParams.setInstitutionCode("123");
            l_variousInforParams.setInformDiv("1");
            l_variousInforParams.setRequestNumber("24");
            l_variousInforParams.setBranchCode("624");
            l_variousInforParams.setStatus("2");
            l_variousInforParams.setErrorReasonCode("4");
            TestDBUtility.insertWithDel(l_variousInforParams);
            l_method = WEB3AdminDirSecAccRegVoucherStatUpdServiceImpl.class.getDeclaredMethod(
                "updateVariousInform", new Class[]{
                    WEB3AdminDirSecAccVoucherRecordDetail.class, String.class, String.class});
            l_method.setAccessible(true);
            Object l_obj = l_method.invoke(l_impl, new Object[]{
                l_accountInfo, l_strVoucherCreateStatus,
                    l_strErrorReasonCode});

            String p_institutionCode = "123";
            String p_informDiv = "1";
            String p_requestNumber = "24";
            String p_branchCode = "624"; 
            VariousInformRow l_row = VariousInformDao.findRowByPk(
                p_institutionCode, p_informDiv, p_requestNumber, p_branchCode);
            assertEquals("1", l_row.getStatus());
            assertEquals("2", l_row.getErrorReasonCode());
            log.debug("receipt_timestamp = ?在日?" + l_row.getReceiptTimestamp());
        }
        catch (InvocationTargetException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02308,
                ((WEB3BaseException)l_ex.getTargetException()).getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * （引数）顧客情報 == null の場合
     * （引数）顧客情報 == null
     *   throw 80017 異常
     */
    public void testUpdateAccOpenVoucherStatus_case001()
    {
        final String STR_METHOD_NAME = "testUpdateAccOpenVoucherStatus_case001";
        log.entering(STR_METHOD_NAME);
        Method l_method;

        try
        {
            this.ClearData();
            WEB3AdminDirSecAccVoucherRecordDetail l_accountInfo = null;
            String l_strVoucherCreateStatus = null;
            String l_strErrorReasonCode = null;

            l_method = WEB3AdminDirSecAccRegVoucherStatUpdServiceImpl.class.getDeclaredMethod(
                "updateAccOpenVoucherStatus", new Class[]{
                    WEB3AdminDirSecAccVoucherRecordDetail.class, String.class, String.class});
            l_method.setAccessible(true);
            Object l_obj = l_method.invoke(l_impl, new Object[]{
                l_accountInfo, l_strVoucherCreateStatus,
                    l_strErrorReasonCode});
            fail();
        }
        catch (InvocationTargetException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                ((WEB3BaseException)l_ex.getTargetException()).getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * " ３-１）口座開設作成伝票ステータスへ?索 の戻り値長さ == 0 の場合
     * 「レコードが存在しません。」の例外をスローする"
     * 清空口座開設作成伝票ステータス 「レコードが存在しません。」の例外をスローする
     */
    public void testUpdateAccOpenVoucherStatus_case002()
    {
        final String STR_METHOD_NAME = "testUpdateAccOpenVoucherStatus_case002";
        log.entering(STR_METHOD_NAME);
        Method l_method;

        try
        {
            this.ClearData();
            WEB3AdminDirSecAccVoucherRecordDetail l_accountInfo =
                new WEB3AdminDirSecAccVoucherRecordDetail();
            String l_strVoucherCreateStatus = null;
            String l_strErrorReasonCode = null;

            l_method = WEB3AdminDirSecAccRegVoucherStatUpdServiceImpl.class.getDeclaredMethod(
                "updateAccOpenVoucherStatus", new Class[]{
                    WEB3AdminDirSecAccVoucherRecordDetail.class, String.class, String.class});
            l_method.setAccessible(true);
            Object l_obj = l_method.invoke(l_impl, new Object[]{
                l_accountInfo, l_strVoucherCreateStatus,
                    l_strErrorReasonCode});
            fail();
        }
        catch (InvocationTargetException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02837,
                ((WEB3BaseException)l_ex.getTargetException()).getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     *  ３-１）口座開設作成伝票ステータスへ?索 の戻り値長さ == 1 の場合各種連絡テーブルの更新 "1）
     *  （引数）伝票作成状況 = 1
     *  2）（引数）エラー理由コード = 2
     *  3)向数据?中的口座開設作成伝票ステータス中插入数据如@下
     *  口座開設作成伝票ステータス.証券会社コード = 123
     *  口座開設作成伝票ステータス.識別コード  = 24
     *  口座開設作成伝票ステータス.データコード  = 4
     *  口座開設作成伝票ステータス.伝票通番  = 5
     *  4)（引数）顧客情報.証券会社コード = 123
     *  （引数）顧客情報.連絡種別 = 1
     *  （引数）顧客情報.識別コード=24
     *  （引数）顧客情報.データコード  =4
     *  （引数）顧客情報.伝票通番 = 5
     *  "   "口座開設作成伝票ステータスの更新如@下：
     *  口座開設作成伝票ステータス.voucher_status = 1
     *  口座開設作成伝票ステータス.：""error_code = 2
     *  口座開設作成伝票ステータス.recv_timestamp = ?在日?"
     */
    public void testUpdateAccOpenVoucherStatus_case003()
    {
        final String STR_METHOD_NAME = "testUpdateAccOpenVoucherStatus_case003";
        log.entering(STR_METHOD_NAME);
        Method l_method;

        try
        {
            this.ClearData();
            AccOpenVoucherStatusParams l_params = this.getAccopenVoucherStatus();
            l_params.setInstitutionCode("123");
            l_params.setAccOpenRequestNumber("24");
            l_params.setRequestCode("4");
            l_params.setSerialNo("5");
            l_params.setVoucherStatus("1");
            TestDBUtility.insertWithDel(l_params);

            WEB3AdminDirSecAccVoucherRecordDetail l_accountInfo =
                new WEB3AdminDirSecAccVoucherRecordDetail();
            l_accountInfo.institutionCode = "123";
            l_accountInfo.requestNumber = "24";
            l_accountInfo.dataCode = "4";
            l_accountInfo.voucherNumber = "5";
            l_accountInfo.infoType = "1";
            String l_strVoucherCreateStatus = "1";
            String l_strErrorReasonCode = "2";

            l_method = WEB3AdminDirSecAccRegVoucherStatUpdServiceImpl.class.getDeclaredMethod(
                "updateAccOpenVoucherStatus", new Class[]{
                    WEB3AdminDirSecAccVoucherRecordDetail.class, String.class, String.class});
            l_method.setAccessible(true);
            Object l_obj = l_method.invoke(l_impl, new Object[]{
                l_accountInfo, l_strVoucherCreateStatus,
                    l_strErrorReasonCode});

            String p_institutionCode = "123";
            String p_accOpenRequestNumber = "24";
            String p_requestCode = "4";
            String p_serialNo = "5";
            AccOpenVoucherStatusRow l_row = AccOpenVoucherStatusDao.findRowByPk(
                p_institutionCode, p_accOpenRequestNumber, p_requestCode, p_serialNo );
            assertEquals("1", l_row.getVoucherStatus());
            assertEquals("2", l_row.getErrorCode());
            log.debug("recv_timestamp = ?在日?" + l_row.getRecvTimestamp());
        }
        catch (InvocationTargetException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02308,
                ((WEB3BaseException)l_ex.getTargetException()).getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * "引数）連絡種別 != null の場合
     * 引数）識別コード != null の場合
     * （引数）顧客コードの長さ == 7 の場合
     * （引数）データコード != null の場合
     * （引数）伝票送信日時 != null の場合" "引数）
     * 連絡種別 == 1
     * 引数）識別コード== 12
     * （引数）顧客コード== 1234567
     * （引数）データコード = 111
     * （引数）伝票送信日時 = ""20070808""
     * 清空数据表各種連絡テーブル
     * 向数据表各種連絡テーブル中插入数据如@下：
     * 証券会社コード= 123
     * 連絡種別 = 1
     * 識別コード  = 12
     * 部店コード = 3
     * 顧客コード = 1234567
     * データコード = 111
     * 伝票送信日時条件 = “20070808”
     * 返回?果size == 1
     */
    public void testGetVariousInformRecord_cae001()
    {
        final String STR_METHOD_NAME = "testGetVariousInformRecord_cae001";
        log.entering(STR_METHOD_NAME);
        Method l_method;

        try
        {
            this.ClearData();

            VariousInformParams l_variousInforParams = this.getVariousInformRow();
            l_variousInforParams.setInstitutionCode("123");
            l_variousInforParams.setInformDiv("1");
            l_variousInforParams.setRequestNumber("12");
            l_variousInforParams.setBranchCode("3");
            l_variousInforParams.setAccountCode("1234567");
            l_variousInforParams.setStatus("2");
            l_variousInforParams.setRequestCode("111");
            l_variousInforParams.setErrorReasonCode("4");
            l_variousInforParams.setSendTimestamp(WEB3DateUtility.getDate("20070808","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_variousInforParams);

            String l_strInstitutionCode = "123";
            String l_strInformDiv = "1";
            String l_strRequestNumber = "12";
            String l_strBranchCode = "3";
            String l_strAccountCode = "1234567";
            String l_strDataCode = "111";
            String l_strSendTimestamp = "20070808";

            l_method = WEB3AdminDirSecAccRegVoucherStatUpdServiceImpl.class.getDeclaredMethod(
                "getVariousInformRecord", new Class[]{
                    String.class, String.class, String.class,
                    String.class,String.class, String.class, String.class});
            l_method.setAccessible(true);
            Object l_obj = l_method.invoke(l_impl, new Object[]{
                l_strInstitutionCode, l_strInformDiv,
                l_strRequestNumber, l_strBranchCode, l_strAccountCode, l_strDataCode, l_strSendTimestamp});

            List l_lisTemp = (List)l_obj;
            VariousInformRow l_row = (VariousInformRow)l_lisTemp.get(0);
            assertEquals("2", l_row.getStatus());
            assertEquals("4", l_row.getErrorReasonCode());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * "引数）連絡種別 != null の場合
     * 引数）識別コード != null の場合
     * （引数）顧客コードの長さ < 7 の場合
     * （引数）データコード != null の場合
     * （引数）伝票送信日時 != null の場合" "引数）連絡種別 == 1
     * 引数）識別コード== 12
     * （引数）顧客コード== 123456
     * （引数）データコード = 21
     * （引数）伝票送信日時 = ""20070808""
     * 清空数据表各種連絡テーブル
     * 向数据表各種連絡テーブル中插入数据如@下：
     * 証券会社コード= 123
     * 連絡種別 = 1
     * 識別コード  = 2
     * 部店コード = 3
     * 顧客コード = 123456
     * データコード = 111
     * 伝票送信日時条件 = “20070808”"  返回?果size == 1
     */
    public void testGetVariousInformRecord_cae002()
    {
        final String STR_METHOD_NAME = "testGetVariousInformRecord_cae002";
        log.entering(STR_METHOD_NAME);
        Method l_method;

        try
        {
            this.ClearData();

            VariousInformParams l_variousInforParams = this.getVariousInformRow();
            l_variousInforParams.setInstitutionCode("123");
            l_variousInforParams.setInformDiv("1");
            l_variousInforParams.setRequestNumber("12");
            l_variousInforParams.setBranchCode("3");
            l_variousInforParams.setAccountCode("1234567");
            l_variousInforParams.setStatus("2");
            l_variousInforParams.setRequestCode("111");
            l_variousInforParams.setErrorReasonCode("4");
            l_variousInforParams.setSendTimestamp(WEB3DateUtility.getDate("20070808","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_variousInforParams);

            String l_strInstitutionCode = "123";
            String l_strInformDiv = "1";
            String l_strRequestNumber = "12";
            String l_strBranchCode = "3";
            String l_strAccountCode = "123456";
            String l_strDataCode = "111";
            String l_strSendTimestamp = "20070808";

            l_method = WEB3AdminDirSecAccRegVoucherStatUpdServiceImpl.class.getDeclaredMethod(
                "getVariousInformRecord", new Class[]{
                    String.class, String.class, String.class,
                    String.class,String.class, String.class, String.class});
            l_method.setAccessible(true);
            Object l_obj = l_method.invoke(l_impl, new Object[]{
                l_strInstitutionCode, l_strInformDiv,
                l_strRequestNumber, l_strBranchCode, l_strAccountCode, l_strDataCode, l_strSendTimestamp});

            List l_lisTemp = (List)l_obj;
            VariousInformRow l_row = (VariousInformRow)l_lisTemp.get(0);
            assertEquals("2", l_row.getStatus());
            assertEquals("4", l_row.getErrorReasonCode());
        }
        catch (InvocationTargetException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02308,
                ((WEB3BaseException)l_ex.getTargetException()).getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * "引数）連絡種別 == null の場合
     * 引数）識別コード == null の場合
     * （引数）顧客コードの長さ < 7 の場合
     * （引数）データコード == null の場合
     * （引数）伝票送信日時 == null の場合"
     * "引数）連絡種別 == null
     * 引数）識別コード== null
     * （引数）顧客コード== 123456
     * （引数）データコード = null
     * （引数）伝票送信日時 = null
     * 清空数据表各種連絡テーブル
     * 向数据表各種連絡テーブル中插入数据如@下：
     * 証券会社コード= 123
     * 連絡種別 = 1
     * 識別コード  = 2
     * 部店コード = 3
     * 顧客コード = 123456
     * データコード = 111
     * 伝票送信日時条件 = “20070808”"
     * 返回?果size == 1
     */
    public void testGetVariousInformRecord_cae003()
    {
        final String STR_METHOD_NAME = "testGetVariousInformRecord_cae003";
        log.entering(STR_METHOD_NAME);
        Method l_method;

        try
        {
            this.ClearData();

            VariousInformParams l_variousInforParams = this.getVariousInformRow();
            l_variousInforParams.setInstitutionCode("123");
            l_variousInforParams.setInformDiv("1");
            l_variousInforParams.setRequestNumber("12");
            l_variousInforParams.setBranchCode("3");
            l_variousInforParams.setAccountCode("1234567");
            l_variousInforParams.setStatus("2");
            l_variousInforParams.setRequestCode("111");
            l_variousInforParams.setErrorReasonCode("4");
            l_variousInforParams.setSendTimestamp(WEB3DateUtility.getDate("20070808", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_variousInforParams);

            String l_strInstitutionCode = "123";
            String l_strInformDiv = null;
            String l_strRequestNumber = null;
            String l_strBranchCode = "3";
            String l_strAccountCode = "123456";
            String l_strDataCode = null;
            String l_strSendTimestamp = null;

            l_method = WEB3AdminDirSecAccRegVoucherStatUpdServiceImpl.class.getDeclaredMethod(
                "getVariousInformRecord", new Class[]{
                    String.class, String.class, String.class,
                    String.class,String.class, String.class, String.class});
            l_method.setAccessible(true);
            Object l_obj = l_method.invoke(l_impl, new Object[]{
                l_strInstitutionCode, l_strInformDiv,
                l_strRequestNumber, l_strBranchCode, l_strAccountCode, l_strDataCode, l_strSendTimestamp});

            List l_lisTemp = (List)l_obj;
            VariousInformRow l_row = (VariousInformRow)l_lisTemp.get(0);
            assertEquals("2", l_row.getStatus());
            assertEquals("4", l_row.getErrorReasonCode());
        }
        catch (InvocationTargetException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02308,
                ((WEB3BaseException)l_ex.getTargetException()).getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * "引数）連絡種別 == null
     * 引数）識別コード == null
     * （引数）顧客コードの長さ < 7
     * （引数）データコード == null
     * （引数）伝票送信日時 == null の場合
     * 戻り値の長さ==0の場合はnullを返却する
     * "   "引数）連絡種別 == null
     * 引数）識別コード== null
     * （引数）顧客コード== 123456
     * （引数）データコード = null
     * （引数）伝票送信日時 = null
     * 清空数据表各種連絡テーブル"
     *  "nullを返却する。
     */
    public void testGetVariousInformRecord_cae004()
    {
        final String STR_METHOD_NAME = "testGetVariousInformRecord_cae004";
        log.entering(STR_METHOD_NAME);
        Method l_method;

        try
        {
            this.ClearData();

            String l_strInstitutionCode = "123";
            String l_strInformDiv = null;
            String l_strRequestNumber = null;
            String l_strBranchCode = "3";
            String l_strAccountCode = "123456";
            String l_strDataCode = null;
            String l_strSendTimestamp = null;

            l_method = WEB3AdminDirSecAccRegVoucherStatUpdServiceImpl.class.getDeclaredMethod(
                "getVariousInformRecord", new Class[]{
                    String.class, String.class, String.class,
                    String.class,String.class, String.class, String.class});
            l_method.setAccessible(true);
            Object l_obj = l_method.invoke(l_impl, new Object[]{
                l_strInstitutionCode, l_strInformDiv,
                l_strRequestNumber, l_strBranchCode, l_strAccountCode, l_strDataCode, l_strSendTimestamp});


            List l_lisTemp = (List)l_obj;

            assertEquals(null, l_lisTemp);
        }
        catch (InvocationTargetException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02308,
                ((WEB3BaseException)l_ex.getTargetException()).getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * 識別コード == nullの場合
     * 識別コード == null
     * throw 80017
     */
    public void testGetAccOpenVoucherStatusRecord_case001()
    {
        final String STR_METHOD_NAME = "testGetAccOpenVoucherStatusRecord_case001";
        log.entering(STR_METHOD_NAME);
        Method l_method;

        try
        {
            this.ClearData();
            String l_strInstitutionCode = "123";
            Object[] l_requestNumbers = null;
            String l_strDataCode = null;
            String l_strSerialNo = null;
            String l_strSendTimestamp = null;

            l_method = WEB3AdminDirSecAccRegVoucherStatUpdServiceImpl.class.getDeclaredMethod(
                "getAccOpenVoucherStatusRecord", new Class[]{
                    String.class, Object[].class, String.class, String.class, String.class});
            l_method.setAccessible(true);
            Object l_obj = l_method.invoke(l_impl, new Object[]{
                l_strInstitutionCode, l_requestNumbers,
                l_strDataCode, l_strSerialNo, l_strSendTimestamp});
            fail();
        }
        catch (InvocationTargetException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                ((WEB3BaseException)l_ex.getTargetException()).getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * "識別コード[]の長さ == 1 の場合
     * （引数）データコード != null の場合
     * （引数）伝票通番 != null の場合
     * （引数）伝票送信日時 != null の場合
     * （引数）証券会社コード = 123
     * （引数）識別コード[0] = 1
     *   （引数）データコード =2
     *   （引数）伝票通番 =3
     *    （引数）伝票送信日時 =""20060606""
     *    清空口座開設伝票作成ステータステーブル
     *    口座開設伝票作成ステータステーブル中插入如@下：
     *    口座開設伝票作成ステータステーブル.証券会社コード = 123
     *    口座開設伝票作成ステータステーブル.識別コード[0] = 1
     *   往表座開設伝票作成ステータステーブル.データコード =2
     *   往表座開設伝票作成ステータステーブル.伝票通番 =3
     *   往表座開設伝票作成ステータステーブル.伝票送信日時 =""20060606""
     *   往表座開設伝票作成ステータステーブル.伝票送信日時 =""20060606"" "  返回?果size == 1
     */
    public void testGetAccOpenVoucherStatusRecord_case002()
    {
        final String STR_METHOD_NAME = "testGetAccOpenVoucherStatusRecord_case002";
        log.entering(STR_METHOD_NAME);
        Method l_method;

        try
        {
            this.ClearData();
            String l_strInstitutionCode = "123";
            Object[] l_requestNumbers = {"1"};
            String l_strDataCode = "2";
            String l_strSerialNo = "3";
            String l_strSendTimestamp = "20060606";

            AccOpenVoucherStatusParams l_params = this.getAccopenVoucherStatus();
            l_params.setInstitutionCode("123");
            l_params.setAccOpenRequestNumber("1");
            l_params.setRequestCode("2");
            l_params.setSerialNo("3");
            l_params.setVoucherStatus("1");
            l_params.setSendTimestamp(WEB3DateUtility.getDate("20060606","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_params);

            l_method = WEB3AdminDirSecAccRegVoucherStatUpdServiceImpl.class.getDeclaredMethod(
                "getAccOpenVoucherStatusRecord", new Class[]{
                    String.class, Object[].class, String.class, String.class, String.class});
            l_method.setAccessible(true);
            Object l_obj = l_method.invoke(l_impl, new Object[]{
                l_strInstitutionCode, l_requestNumbers,
                l_strDataCode, l_strSerialNo, l_strSendTimestamp});

            List l_lisTemp = (List)l_obj;
            AccOpenVoucherStatusRow l_row = (AccOpenVoucherStatusRow)l_lisTemp.get(0);
            assertEquals("1", l_row.getAccOpenRequestNumber());
            assertEquals("3", l_row.getSerialNo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * "識別コード[]の長さ == 3 の場合
     * （引数）データコード == null の場合
     * （引数）伝票通番 == null の場合
     * （引数）伝票送信日時 == null の場合
     * （引数）証券会社コード = 123
     * （引数）識別コード[0] = 1
     * （引数）識別コード[1] = 2
     * （引数）識別コード[2] =3
     * （引数）データコード =null
     * （引数）伝票通番 =null
     * （引数）伝票送信日時 =null
     * 清空口座開設伝票作成ステータステーブル
     * 往表口座開設伝票作成ステータステーブル中插入如@下：
     * 往表口座開設伝票作成ステータステーブル.証券会社コード = 123
     * 往表座開設伝票作成ステータステーブル.識別コード = 1
     *  往表座開設伝票作成ステータステーブル.データコード =2
     *   往表座開設伝票作成ステータステーブル.伝票通番 =3
     *   往表座開設伝票作成ステータステーブル.伝票送信日時 =""20060606""
     *   返回?果size == 1
     */
    public void testGetAccOpenVoucherStatusRecord_case003()
    {
        final String STR_METHOD_NAME = "testGetAccOpenVoucherStatusRecord_case003";
        log.entering(STR_METHOD_NAME);
        Method l_method;

        try
        {
            this.ClearData();
            String l_strInstitutionCode = "123";
            Object[] l_requestNumbers = {"1","2","3"};
            String l_strDataCode = null;
            String l_strSerialNo = null;
            String l_strSendTimestamp = null;

            AccOpenVoucherStatusParams l_params = this.getAccopenVoucherStatus();
            l_params.setInstitutionCode("123");
            l_params.setAccOpenRequestNumber("1");
            l_params.setRequestCode("2");
            l_params.setSerialNo("3");
            l_params.setVoucherStatus("1");
            l_params.setSendTimestamp(WEB3DateUtility.getDate("20060606","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_params);

            l_method = WEB3AdminDirSecAccRegVoucherStatUpdServiceImpl.class.getDeclaredMethod(
                "getAccOpenVoucherStatusRecord", new Class[]{
                    String.class, Object[].class, String.class, String.class, String.class});
            l_method.setAccessible(true);
            Object l_obj = l_method.invoke(l_impl, new Object[]{
                l_strInstitutionCode, l_requestNumbers,
                l_strDataCode, l_strSerialNo, l_strSendTimestamp});

            List l_lisTemp = (List)l_obj;
            AccOpenVoucherStatusRow l_row = (AccOpenVoucherStatusRow)l_lisTemp.get(0);
            assertEquals("1", l_row.getAccOpenRequestNumber());
            assertEquals("3", l_row.getSerialNo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * "識別コード[]の長さ == 3 の場合
     * （引数）データコード == null の場合
     * （引数）伝票通番 == null の場合
     * （引数）伝票送信日時 == null の場合
     * （引数）証券会社コード = 123（
     * 引数）識別コード[0] = 1
     * （引数）識別コード[1] = 2
     * 引数）識別コード[2] =3
     * （引数）データコード =null
     * （引数）伝票通番 =null
     * （引数）伝票送信日時 =null
     * 清空口座開設伝票作成ステータステーブル"
     * "nullを返却する。
     */
    public void testGetAccOpenVoucherStatusRecord_case004()
    {
        final String STR_METHOD_NAME = "testGetAccOpenVoucherStatusRecord_case004";
        log.entering(STR_METHOD_NAME);
        Method l_method;

        try
        {
            this.ClearData();
            String l_strInstitutionCode = "123";
            Object[] l_requestNumbers = {"1","2","3"};
            String l_strDataCode = null;
            String l_strSerialNo = null;
            String l_strSendTimestamp = null;

            l_method = WEB3AdminDirSecAccRegVoucherStatUpdServiceImpl.class.getDeclaredMethod(
                "getAccOpenVoucherStatusRecord", new Class[]{
                    String.class, Object[].class, String.class, String.class, String.class});
            l_method.setAccessible(true);
            Object l_obj = l_method.invoke(l_impl, new Object[]{
                l_strInstitutionCode, l_requestNumbers,
                l_strDataCode, l_strSerialNo, l_strSendTimestamp});

            List l_lisTemp = (List)l_obj;
            assertEquals(null, l_lisTemp);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public static ExpAccountOpenParams getExpAccountOpenParams()
    {
        ExpAccountOpenParams l_expAccountOpenParams = new ExpAccountOpenParams();

        //1 証券会社コード  institution_codeVARCHAR23Notnull
        l_expAccountOpenParams.setInstitutionCode("123");
        //2 証券会社ID  institution_idNUMBER18Notnull
        l_expAccountOpenParams.setInstitutionId(123);
        //3 部店ＩＤ  branch_idNUMBER18Notnull
        l_expAccountOpenParams.setBranchId(1232);
        //4 部店コード  branch_codeVARCHAR23Notnull
        l_expAccountOpenParams.setBranchCode("624");
        //5 識別コード  acc_open_request_numberVARCHAR213Notnull
        l_expAccountOpenParams.setAccOpenRequestNumber("624");
        //8 既存口座フラグex_account_flagVARCHAR21Notnull
        l_expAccountOpenParams.setExAccountFlag(BooleanEnum.FALSE);
        //11 口座区分account_div VARCHAR21Notnull
        l_expAccountOpenParams.setAccountDiv("1");
        //12 入力区分order_div VARCHAR21Notnull
        l_expAccountOpenParams.setOrderDiv("1");
        //16 顧客姓（漢字）family_name VARCHAR240Notnull
        l_expAccountOpenParams.setFamilyName("1");
        //17 顧客名（漢字）given_name VARCHAR240Notnull
        l_expAccountOpenParams.setGivenName("1");
        //18 顧客姓（カナ）family_name_alt1 VARCHAR240Notnull
        l_expAccountOpenParams.setFamilyNameAlt1("1");
        //19 顧客名（カナ）given_name_alt1 VARCHAR240Notnull
        l_expAccountOpenParams.setGivenNameAlt1("1");
        //20 性別sex VARCHAR21Notnull
        l_expAccountOpenParams.setSex("1");

        //25 郵便番号zip_codeVARCHAR27Notnull
        l_expAccountOpenParams.setZipCode("1");
        //26 住所１address_line1VARCHAR234Notnull
        l_expAccountOpenParams.setAddressLine1("1");
        //29 住所１（カナ）address_line1_kana VARCHAR2 60 Notnull
        l_expAccountOpenParams.setAddressLine1Kana("1");
        //77 投資経験有無現物株式フラグexperience_flag_equityNUMBER1Notnull
        l_expAccountOpenParams.setExperienceFlagEquity(BooleanEnum.FALSE);
        //78 信用取引フラグexperience_flag_marginNUMBER1Notnull
        l_expAccountOpenParams.setExperienceFlagMargin(BooleanEnum.FALSE);
        //79 債券フラグexperience_flag_bondNUMBER1Notnull
        l_expAccountOpenParams.setExperienceFlagBond(BooleanEnum.FALSE);
        //80 転換社債フラグexperience_flag_wtNUMBER1Notnull
        l_expAccountOpenParams.setExperienceFlagWt(BooleanEnum.FALSE);
        //80 投資信託（株式）フラグexperience_flag_fund_skNUMBER1Notnull
        l_expAccountOpenParams.setExperienceFlagFundSk(BooleanEnum.FALSE);
        //81 投資信託（公社債）フラグexperience_flag_fund_bdNUMBER1Notnull
        l_expAccountOpenParams.setExperienceFlagFundBd(BooleanEnum.FALSE);
        //81 先物・オプションフラグexperience_flag_foNUMBER1Notnull
        l_expAccountOpenParams.setExperienceFlagFo(BooleanEnum.FALSE);
        //82 外国証券フラグexperience_flag_f_equityNUMBER1Notnull
        l_expAccountOpenParams.setExperienceFlagFEquity(BooleanEnum.FALSE);
        //83 その他フラグexperience_flag_etcNUMBER1Notnull
        l_expAccountOpenParams.setExperienceFlagEtc(BooleanEnum.FALSE);
        //84 経験年数（自）experience_fromVARCHAR22NULL
        l_expAccountOpenParams.setExperienceFrom("1");
        //85 経験年数（至）experience_toVARCHAR22NULL
        l_expAccountOpenParams.setExperienceTo("1");
        //86 興味のあるお取引現物株式フラグinterest_flag_equityNUMBER1Notnull
        l_expAccountOpenParams.setInterestFlagEquity(BooleanEnum.FALSE);
        //87 株式ミニ投資フラグinterest_flag_ministockNUMBER1Notnull
        l_expAccountOpenParams.setInterestFlagMinistock(BooleanEnum.FALSE);
        //88 信用取引フラグinterest_flag_marginNUMBER1Notnull
        l_expAccountOpenParams.setInterestFlagMargin(BooleanEnum.FALSE);
        //89 債券フラグinterest_flag_bondNUMBER1Notnull
        l_expAccountOpenParams.setInterestFlagBond(BooleanEnum.FALSE);
        //90 投資信託フラグinterest_flag_fundNUMBER1Notnull
        l_expAccountOpenParams.setInterestFlagFund(BooleanEnum.FALSE);
        //91 先物・オプションフラグinterest_flag_foNUMBER1Notnull
        l_expAccountOpenParams.setInterestFlagFEquity(BooleanEnum.FALSE);
        //92 外国証券フラグinterest_flag_f_equityNUMBER1Notnull
        l_expAccountOpenParams.setInterestFlagFEquity(BooleanEnum.FALSE);
        //93 その他フラグinterest_flag_etcNUMBER1Notnull
        l_expAccountOpenParams.setInterestFlagEtc(BooleanEnum.FALSE);
        //105 検索用区分id_confirm_flagNUMBER1Notnull
        l_expAccountOpenParams.setIdConfirmFlag(BooleanEnum.FALSE);
        //110 内部者登録フラグinsider_flagNUMBER1Notnull

        l_expAccountOpenParams.setInsiderFlag(BooleanEnum.FALSE);
        //147 作成日時created_timestampDATENotnull
        l_expAccountOpenParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
        //148 更新日時last_updated_timestampDATENotnull
        l_expAccountOpenParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040716","yyyyMMdd"));

        l_expAccountOpenParams.setExperienceDivEquity("1");
        l_expAccountOpenParams.setExperienceDivMargin("1");
        l_expAccountOpenParams.setExperienceDivBond("1");
        l_expAccountOpenParams.setExperienceDivWt("1");
        l_expAccountOpenParams.setExperienceDivFundSk("1");
        l_expAccountOpenParams.setExperienceDivFundBd("1");
        l_expAccountOpenParams.setExperienceDivFo("1");
        l_expAccountOpenParams.setExperienceDivFEquity("1");
        l_expAccountOpenParams.setExperienceDivEtc("1");

        return l_expAccountOpenParams;
    }

    public static VariousInformParams getVariousInformRow()
    {
        VariousInformParams variousInformParams = new VariousInformParams();
        variousInformParams.setBranchCode("000");
        variousInformParams.setInstitutionCode("123");
        variousInformParams.setInformDiv("123");
        variousInformParams.setRequestNumber("123");
        variousInformParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
        variousInformParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
        return variousInformParams;
    }

    public static AccOpenVoucherStatusParams getAccopenVoucherStatus()
    {
        AccOpenVoucherStatusParams l_params = new AccOpenVoucherStatusParams();
        //1 証券会社コードinstitution_codeVARCHAR23NotNull（既存値）
        l_params.setInstitutionCode("123");
        //2 識別コードacc_open_request_numberVARCHAR213Notnull（既存値）
        l_params.setAccOpenRequestNumber("123");
        //3 データコードrequest_codeVARCHAR25NotNull（既存値）
        l_params.setRequestCode("123");
        //4 伝票通番serial_noVARCHAR23NotNull（既存値）
        l_params.setSerialNo("1");
        //5 伝票作成ステータスvoucher_statusVARCHAR21NotNullリクエストデータ.更新_伝票作成状況
        l_params.setVoucherStatus("1");
        //6 送信日時send_timestampDATENULL（既存値）
        l_params.setSendTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
        //7 受信日時recv_timestampDATENULL現在日時
        l_params.setRecvTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
        //8 エラーコードerror_codeVARCHAR24NULLリクエストデータ.更新_エラー理由コード
        l_params.setErrorCode("1");
        //9 更新者コードlast_updaterVARCHAR220NULL（既存値）
        l_params.setLastUpdater("1");
        //10 作成日時created_timestampDATENotNull（既存値）
        l_params.setCreatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
        //11 更新日時last_updated_timestampDATENotNull（既存値）
        l_params.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
        return l_params;

    }

    private void ClearData() throws WEB3BaseException
    {
        TestDBUtility.deleteAll(AccOpenVoucherStatusParams.TYPE);
        TestDBUtility.deleteAll(ExpAccountOpenParams.TYPE);
        TestDBUtility.deleteAll(VariousInformRow.TYPE);
    }
}
@
