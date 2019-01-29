head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.00.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminDirSecAccRegVoucherStatUpdCompRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : //TODO(WEB3AdminDirSecAccRegVoucherStatUpdCompRequestTest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/06/20 徐宏偉 (中訊) 新規作成
*/
package webbroker3.dirsec.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * XXXXXXクラス//TODO
 *
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public class WEB3AdminDirSecAccRegVoucherStatUpdCompRequestTest extends TestBaseForMock
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3AdminDirSecAccRegVoucherStatUpdCompRequestTest.class);

    public WEB3AdminDirSecAccRegVoucherStatUpdCompRequest l_impl =
        new WEB3AdminDirSecAccRegVoucherStatUpdCompRequest();

    public WEB3AdminDirSecAccRegVoucherStatUpdCompRequestTest(String arg0)
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
     * 顧客情報登録伝票レコード詳細配列 == null の場合
     *  顧客情報登録伝票レコード詳細配列 == null
     * 「更新対象のレコードが不正です。」例外をスローする
     * BUSINESS_ERROR_02838"
     */
    public void testVolidate_case001()
    {
        final String STR_METHOD_NAME = "testGetRequestCode_case001";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_impl.accVoucherRecord = null;
            this.l_impl.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02838, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /*
     * 顧客情報登録伝票レコード詳細配列の長さ == 0 の場合
     * 顧客情報登録伝票レコード詳細配列の長さ == 0
     * 「更新対象のレコードが不正です。」例外をスローする
     * BUSINESS_ERROR_02838"
     */
    public void testVolidate_case002()
    {
        final String STR_METHOD_NAME = "testGetRequestCode_case002";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_impl.accVoucherRecord = new WEB3AdminDirSecAccVoucherRecordDetail[0];
            this.l_impl.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02838, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /*
     * " １-１） 証券会社コードチェック
     *    １-１-１） 顧客情報登録伝票レコード詳細[index].証券会社コード == nullの場合
     *        顧客情報登録伝票レコード詳細[index].証券会社コード == null
     *            「証券会社コードが不正です。」の例外をスローする
     *                 BUSINESS_ERROR_01023"
     */
    public void testVolidate_case003()
    {
        final String STR_METHOD_NAME = "testGetRequestCode_case003";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_impl.accVoucherRecord = new WEB3AdminDirSecAccVoucherRecordDetail[1];
            l_impl.accVoucherRecord[0] = new WEB3AdminDirSecAccVoucherRecordDetail();
            l_impl.accVoucherRecord[0].institutionCode = null;
            this.l_impl.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01023, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /*
     * "  １-２） 部店コードチェック
     *     １-２-１） 顧客情報登録伝票レコード詳細[index].部店コード == nullの場合
     *     顧客情報登録伝票レコード詳細[index].部店コード == null
     *     "「部店コードが不正です。」の例外をスローする。
     *     BUSINESS_ERROR_00779"
     */
    public void testVolidate_case004()
    {
        final String STR_METHOD_NAME = "testGetRequestCode_case004";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_impl.accVoucherRecord = new WEB3AdminDirSecAccVoucherRecordDetail[1];
            l_impl.accVoucherRecord[0] = new WEB3AdminDirSecAccVoucherRecordDetail();
            l_impl.accVoucherRecord[0].institutionCode = "123";
            l_impl.accVoucherRecord[0].branchCode = null;
            this.l_impl.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00779, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /*
     * 顧客情報登録伝票レコード詳細[index].顧客コード == nullの場合
     * 顧客情報登録伝票レコード詳細[index].顧客コード == nullの場合
     * 「顧客コードが不正です。」の例外をスローする
     * BUSINESS_ERROR_00780"
     */
    public void testVolidate_case005()
    {
        final String STR_METHOD_NAME = "testGetRequestCode_case005";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_impl.accVoucherRecord = new WEB3AdminDirSecAccVoucherRecordDetail[1];
            l_impl.accVoucherRecord[0] = new WEB3AdminDirSecAccVoucherRecordDetail();
            l_impl.accVoucherRecord[0].institutionCode = "123";
            l_impl.accVoucherRecord[0].branchCode = "123";
            l_impl.accVoucherRecord[0].accountCode = null;
            this.l_impl.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00780, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /*
     *   １-４） データコードチェック
     *       １-４-１） 顧客情報登録伝票レコード詳細[index].データコード == null の場合
     *       顧客情報登録伝票レコード詳細[index].データコード == null
     *       「データコードが不正です。」の例外をスローする
     *       BUSINESS_ERROR_02828"
     */
    public void testVolidate_case006()
    {
        final String STR_METHOD_NAME = "testGetRequestCode_case006";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_impl.accVoucherRecord = new WEB3AdminDirSecAccVoucherRecordDetail[1];
            l_impl.accVoucherRecord[0] = new WEB3AdminDirSecAccVoucherRecordDetail();
            l_impl.accVoucherRecord[0].institutionCode = "123";
            l_impl.accVoucherRecord[0].branchCode = "123";
            l_impl.accVoucherRecord[0].accountCode = "123";
            l_impl.accVoucherRecord[0].dataCode = null;
            this.l_impl.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02828, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /*
     * "  １-５） 識別コードチェッ
     *     １-５-１） 顧客情報登録伝票レコード詳細[index].識別コード == null の場合"
     *     顧客情報登録伝票レコード詳細[index].識別コード == null
     *     BUSINESS_ERROR_02829
     *
     */
    public void testVolidate_case007()
    {
        final String STR_METHOD_NAME = "testGetRequestCode_case007";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_impl.accVoucherRecord = new WEB3AdminDirSecAccVoucherRecordDetail[1];
            l_impl.accVoucherRecord[0] = new WEB3AdminDirSecAccVoucherRecordDetail();
            l_impl.accVoucherRecord[0].institutionCode = "123";
            l_impl.accVoucherRecord[0].branchCode = "123";
            l_impl.accVoucherRecord[0].accountCode = "123";
            l_impl.accVoucherRecord[0].dataCode = "123";
            l_impl.accVoucherRecord[0].requestNumber = null;
            this.l_impl.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02829, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /*
     * "  １-６） 連絡種別、伝票通番チェック
     * １-６-１） 顧客情報登録伝票レコード詳細[index].口座開設伝票フラグ == FALSE
     *  AND 顧客情報登録伝票レコード詳細[index]連絡種別 == null の場合"
     *  "顧客情報登録伝票レコード詳細[index].口座開設伝票フラグ == FALSE
     *  AND 顧客情報登録伝票レコード詳細[index]連絡種別 == null "
     *   "「連絡種別が不正です。」の例外をスローする
     *   BUSINESS_ERROR_02830"
     */
    public void testGetRequestCode_case008()
    {
        final String STR_METHOD_NAME = "testGetRequestCode_case008";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_impl.accVoucherRecord = new WEB3AdminDirSecAccVoucherRecordDetail[1];
            l_impl.accVoucherRecord[0] = new WEB3AdminDirSecAccVoucherRecordDetail();
            l_impl.accVoucherRecord[0].institutionCode = "123";
            l_impl.accVoucherRecord[0].branchCode = "123";
            l_impl.accVoucherRecord[0].accountCode = "123";
            l_impl.accVoucherRecord[0].dataCode = "123";
            l_impl.accVoucherRecord[0].requestNumber = "123";
            l_impl.accVoucherRecord[0].voucherFlag = false;
            l_impl.accVoucherRecord[0].infoType = null;
            this.l_impl.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02830, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /*
     * "    １-６-２） 顧客情報登録伝票レコード詳細[index].口座開設伝票フラグ == TRUE
     *  AND 顧客情報登録伝票レコード詳細[index].伝票通番 == null の場合"
     *  "顧客情報登録伝票レコード詳細[index].口座開設伝票フラグ == TRUE
     *  AND 顧客情報登録伝票レコード詳細[index].伝票通番 == null
     *  「伝票通番が不正です。」の例外をスローする。
     *  BUSINESS_ERROR_02831"
     */
    public void testGetRequestCode_case009()
    {
        final String STR_METHOD_NAME = "testGetRequestCode_case009";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_impl.accVoucherRecord = new WEB3AdminDirSecAccVoucherRecordDetail[1];
            l_impl.accVoucherRecord[0] = new WEB3AdminDirSecAccVoucherRecordDetail();
            l_impl.accVoucherRecord[0].institutionCode = "123";
            l_impl.accVoucherRecord[0].branchCode = "123";
            l_impl.accVoucherRecord[0].accountCode = "123";
            l_impl.accVoucherRecord[0].dataCode = "123";
            l_impl.accVoucherRecord[0].requestNumber = "123";
            l_impl.accVoucherRecord[0].voucherFlag = true;
            l_impl.accVoucherRecord[0].infoType = "123";
            l_impl.accVoucherRecord[0].voucherNumber = null;
            this.l_impl.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02831, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /*
     * "２） 伝票作成状況チェック
     * ２-１） this.更新_伝票作成状況 == null の場合
     * this.更新_伝票作成状況 == null
     * 「伝票作成状況を入力してください。」の例外をスローする
     * BUSINESS_ERROR_02825"
     */
    public void testGetRequestCode_case010()
    {
        final String STR_METHOD_NAME = "testGetRequestCode_case010";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_impl.accVoucherRecord = new WEB3AdminDirSecAccVoucherRecordDetail[1];
            l_impl.accVoucherRecord[0] = new WEB3AdminDirSecAccVoucherRecordDetail();
            l_impl.accVoucherRecord[0].institutionCode = "123";
            l_impl.accVoucherRecord[0].branchCode = "123";
            l_impl.accVoucherRecord[0].accountCode = "123";
            l_impl.accVoucherRecord[0].dataCode = "123";
            l_impl.accVoucherRecord[0].requestNumber = "123";
            l_impl.accVoucherRecord[0].voucherFlag = true;
            l_impl.accVoucherRecord[0].infoType = "123";
            l_impl.accVoucherRecord[0].voucherNumber = "123";
            l_impl.updateVoucherMakeStatus = null;
            this.l_impl.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02825, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /*
     * "３） エラー理由コードチェック
     * ３-１） this.更新_エラー理由コード == null の場合
     * this.更新_エラー理由コード == null
     * 「エラー理由コードを入力してください。」の例外をスローする
     * BUSINESS_ERROR_02826
     */
    public void testGetRequestCode_case011()
    {
        final String STR_METHOD_NAME = "testGetRequestCode_case011";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_impl.accVoucherRecord = new WEB3AdminDirSecAccVoucherRecordDetail[1];
            l_impl.accVoucherRecord[0] = new WEB3AdminDirSecAccVoucherRecordDetail();
            l_impl.accVoucherRecord[0].institutionCode = "123";
            l_impl.accVoucherRecord[0].branchCode = "123";
            l_impl.accVoucherRecord[0].accountCode = "123";
            l_impl.accVoucherRecord[0].dataCode = "123";
            l_impl.accVoucherRecord[0].requestNumber = "123";
            l_impl.accVoucherRecord[0].voucherFlag = true;
            l_impl.accVoucherRecord[0].infoType = "123";
            l_impl.accVoucherRecord[0].voucherNumber = "123";
            l_impl.accVoucherRecord[0].voucherMakeStatus = "123";
            l_impl.updateVoucherMakeStatus = "1";
            l_impl.updateErrorReasonCode = null;
            this.l_impl.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02826, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /*
     * "３-２） this.更新_エラー理由コードの長さ > 4 OR this.更新_エラー理由コードが半角英数以外の場合
     * this.更新_エラー理由コードの長さ > 4 OR this.更新_エラー理由コードが半角英数以外
     * 1)
     * his.更新_エラー理由コードの長さ = 5
     * this.更新_エラー理由コード = 23abc
     * 2)
     * this.更新_エラー理由コードの長さ = 4
     * this.更新_エラー理由コード = ４４ss
     * 「エラー理由コードが不正です。」の例外をスローする
     * BUSINESS_ERROR_02827"
     */
    public void testGetRequestCode_case012()
    {
        final String STR_METHOD_NAME = "testGetRequestCode_case012";
        log.entering(STR_METHOD_NAME);

        l_impl.accVoucherRecord = new WEB3AdminDirSecAccVoucherRecordDetail[1];
        l_impl.accVoucherRecord[0] = new WEB3AdminDirSecAccVoucherRecordDetail();
        l_impl.accVoucherRecord[0].institutionCode = "123";
        l_impl.accVoucherRecord[0].branchCode = "123";
        l_impl.accVoucherRecord[0].accountCode = "123";
        l_impl.accVoucherRecord[0].dataCode = "123";
        l_impl.accVoucherRecord[0].requestNumber = "123";
        l_impl.accVoucherRecord[0].voucherFlag = true;
        l_impl.accVoucherRecord[0].infoType = "123";
        l_impl.accVoucherRecord[0].voucherNumber = "123";
        l_impl.accVoucherRecord[0].voucherMakeStatus = "123";
        l_impl.updateVoucherMakeStatus = "1";

        for (int i = 0; i < 2; i++)
        {
            try
            {
                if (i == 0)
                {
                    l_impl.updateErrorReasonCode = "23abc";
                }
                else
                {
                    l_impl.updateErrorReasonCode = "４４ss";
                }
                this.l_impl.validate();
                fail();
            }
            catch (WEB3BaseException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02827, l_ex.getErrorInfo());
            }
            catch (Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                fail();
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * "
     * ４）　@暗証番号チェッ
     * ４-１）　@this.暗証番号 == nullの場合
     * this.暗証番号 == null
     * 「暗証番号が不正です。」の例外をスローするBUSINESS_ERROR_02832
     */
    public void testGetRequestCode_case013()
    {
        final String STR_METHOD_NAME = "testGetRequestCode_case013";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_impl.accVoucherRecord = new WEB3AdminDirSecAccVoucherRecordDetail[1];
            l_impl.accVoucherRecord[0] = new WEB3AdminDirSecAccVoucherRecordDetail();
            l_impl.accVoucherRecord[0].institutionCode = "123";
            l_impl.accVoucherRecord[0].branchCode = "123";
            l_impl.accVoucherRecord[0].accountCode = "123";
            l_impl.accVoucherRecord[0].dataCode = "123";
            l_impl.accVoucherRecord[0].requestNumber = "123";
            l_impl.accVoucherRecord[0].voucherFlag = true;
            l_impl.accVoucherRecord[0].infoType = "123";
            l_impl.accVoucherRecord[0].voucherNumber = "123";
            l_impl.accVoucherRecord[0].voucherMakeStatus = "123";
            l_impl.updateVoucherMakeStatus = "1";
            l_impl.updateErrorReasonCode = "123";
            l_impl.password = null;
            this.l_impl.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02832, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /*
     * 正常結束
     */
    public void testGetRequestCode_case014()
    {
        final String STR_METHOD_NAME = "testGetRequestCode_case014";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_impl.accVoucherRecord = new WEB3AdminDirSecAccVoucherRecordDetail[1];
            l_impl.accVoucherRecord[0] = new WEB3AdminDirSecAccVoucherRecordDetail();
            l_impl.accVoucherRecord[0].institutionCode = "123";
            l_impl.accVoucherRecord[0].branchCode = "123";
            l_impl.accVoucherRecord[0].accountCode = "123";
            l_impl.accVoucherRecord[0].dataCode = "123";
            l_impl.accVoucherRecord[0].requestNumber = "123";
            l_impl.accVoucherRecord[0].voucherFlag = true;
            l_impl.accVoucherRecord[0].infoType = "123";
            l_impl.accVoucherRecord[0].voucherNumber = "123";
            l_impl.accVoucherRecord[0].voucherMakeStatus = "123";
            l_impl.updateVoucherMakeStatus = "1";
            l_impl.updateErrorReasonCode = "123";
            l_impl.password = "12";
            this.l_impl.validate();
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
