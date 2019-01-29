head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3FPTUploadCsv.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 金商法@交付閲覧アップロードCSV(WEB3FPTUploadCsv.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/12/07 武波 (中訊) 新規作成 モデル No.012,No.014,No.015
Revision History : 2007/12/21 謝旋 (中訊) 仕様変更 モデル No.020
Revision History : 2008/01/28 武波 (中訊) 仕様変更 モデル No.027,No.030,No.032
Revision History : 2008/02/13 武波 (中訊) 実装の問題002
*/

package webbroker3.docadmin;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CategCodeDef;
import webbroker3.common.define.WEB3SystemPreferencesNameDef;
import webbroker3.docadmin.define.WEB3FPTStatusDivDef;
import webbroker3.docadmin.message.WEB3AdminFPTUploadConfirmRequest;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.gentrade.WEB3GentradeCsvUploadDataModel;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.AdministratorUploadDao;
import webbroker3.gentrade.data.AdministratorUploadParams;
import webbroker3.gentrade.data.AdministratorUploadRow;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (金商法@交付閲覧アップロードCSV)<BR>
 * 金商法@交付閲覧アップロードCSV<BR>
 *
 * @@author 武波
 * @@version 1.0
 */
public class WEB3FPTUploadCsv extends WEB3GentradeCsvUploadDataModel
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FPTUploadCsv.class);

    /**
     * (部店コードラベル)<BR>
     * 部店コードラベル<BR>
     */
    public String branchCodeLabel = "部店コード";

    /**
     * (顧客コードラベル)<BR>
     * 顧客コードラベル<BR>
     */
    public String accountCodeLabel = "顧客コード";

    /**
     * (書面交付日ラベル)<BR>
     * 書面交付日ラベル<BR>
     */
    public String deliveryDateLabel = "書面交付日";

    /**
     * (電子鳩銘柄コードラベル)<BR>
     * 電子鳩銘柄コードラベル<BR>
     */
    public String batoProductCodeLabel = "電子鳩銘柄コード";

    /**
     * (アップロード中止)<BR>
     * アップロード中止用コメント<BR>
     */
    public String uploadCancel = "中止";

    /**
     * (アップロードファ@イルID)<BR>
     * アップロードファ@イルＩＤ_金商法@交付閲覧アップロード<BR>
     * <BR>
     * ※（管理者共通）アップロードテーブル.アップロードファ@イルＩＤに格納する文字列<BR>
     */
    public String uploadFileId = "金商法@交付閲覧アップロード";

    /**
     * (限界行数)<BR>
     * アップロード可能限界行数。<BR>
     */
    private long limitLines = 500;

    /**
     * (金商法@交付閲覧アップロードCSV)<BR>
     * －this.createカラムヘッダ()をコールする。<BR>
     * @@roseuid 47380C5D004B
     */
    public WEB3FPTUploadCsv()
    {
        final String STR_METHOD_NAME = "WEB3FPTUploadCsv()";
        log.entering(STR_METHOD_NAME);

        //－this.createカラムヘッダ()をコールする。
        this.createColumnHeader();

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (金商法@交付閲覧アップロードCSV)<BR>
     * アップロードデータモデルを生成する。<BR>
     * <BR>
     * ※中止のときのみ使用。<BR>
     * [コンストラクタの引数]<BR>
     * アップロードＩＤ：　@リクエストデータ.アップロードＩＤ<BR>
     * @@param l_lngUploadFileID - (アップロードID)<BR>
     * アップロードID<BR>
     * @@roseuid 47380CD90054
     */
    public WEB3FPTUploadCsv(long l_lngUploadFileID)
    {
        final String STR_METHOD_NAME = "WEB3FPTUploadCsv(long)";
        log.entering(STR_METHOD_NAME);

        //アップロードＩＤ：　@リクエストデータ.アップロードＩＤ
        this.administratorUploadId = l_lngUploadFileID;

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (check明細行数)<BR>
     * 明細行数のチェックを行う。<BR>
     * <BR>
     * １） システムプリファ@レンスより値を取得する。<BR>
     * <BR>
     * [金商法@共通.getシステムプリファ@レンス()に指定する引数]<BR>
     * "UL_DOCADMIN_DEVMANAGE_COUNT"<BR>
     * <BR>
     * ２） 明細行数をチェックする。<BR>
     * 　@２-１） 金商法@共通.getシステムプリファ@レンス() != null の場合<BR>
     * 　@　@　@　@　@取得した（数値変換済の）システムプリファ@レンスの値 < <BR>
     * 　@　@　@　@　@リクエストデータ.アップロードファ@イル.length の場合、例外をスローする。<BR>
     * <BR>
     *   ２-２） 金商法@共通.getシステムプリファ@レンス() == null の場合<BR>
     *   　@　@　@this.限界行数 < リクエストデータ.アップロードファ@イル.lengthの場合、<BR>
     *   　@　@　@例外をスローする。<BR>
     * <BR>
     * ※例外をスローした場合、以下のメッセージを表示する。<BR>
     * 『レコード件数が処理限界値を超えています。』（エラーコード：BUSINESS_ERROR_02418）<BR>
     * @@param l_request - (l_request)<BR>
     * リクエストデータ<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4756315601AE
     */
    public void checkDetailLines(WEB3AdminFPTUploadConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "checkDetailLines(WEB3AdminFPTUploadConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //１） システムプリファ@レンスより値を取得する。
        //[金商法@共通.getシステムプリファ@レンス()に指定する引数]
        //"UL_DOCADMIN_DEVMANAGE_COUNT"
        String l_strValue =
            WEB3AdminFPTCommon.getSystemPreferences(
                WEB3SystemPreferencesNameDef.UL_DOCADMIN_DEVMANAGE_COUNT);

        //２） 明細行数をチェックする。
        //２-１） 金商法@共通.getシステムプリファ@レンス() != null の場合
        //　@取得した（数値変換済の）システムプリファ@レンスの値 < リクエストデータ.アップロードファ@イル.length の場合、例外をスローする。
        //『レコード件数が処理限界値を超えています。』（エラーコード：BUSINESS_ERROR_02418）
        if (l_strValue != null)
        {
            int l_intValue = Integer.parseInt(l_strValue);
            if (l_intValue < l_request.uploadFile.length)
            {
                log.debug("レコード件数が処理限界値を超えています。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02418,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "レコード件数が処理限界値を超えています。");
            }
        }
        else
        {
            //２-２） 金商法@共通.getシステムプリファ@レンス() == null の場合
            //this.限界行数 < リクエストデータ.アップロードファ@イル.lengthの場合、例外をスローする。
            //『レコード件数が処理限界値を超えています。』（エラーコード：BUSINESS_ERROR_02418）
            if (this.limitLines < l_request.uploadFile.length)
            {
                log.debug("レコード件数が処理限界値を超えています。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02418,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "レコード件数が処理限界値を超えています。");
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (createカラムヘッダ)<BR>
     * 以下の通りCSVカラムモデルの配列を生成し、setカラムヘッダ()にてインスタンスにセットする。<BR>
     * <BR>
     * [カラムヘッダ配列]<BR>
     * <BR>
     * －　@index = 0<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@金商法@交付閲覧アップロードCSV.部店コードラベル<BR>
     * 　@カラム番号： 0<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 1<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@金商法@交付閲覧アップロードCSV顧客コードラベル<BR>
     * 　@カラム番号： 1<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 2<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@金商法@交付閲覧アップロードCSV電子鳩銘柄コードラベル<BR>
     * 　@カラム番号： 2<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 3<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@金商法@交付閲覧アップロードCSV.書面交付日ラベル<BR>
     * 　@カラム番号： 3<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_日付<BR>
     * 　@日付フォーマット：　@"YYYYMMDD"<BR>
     * <BR>
     * <BR>
     * @@roseuid 47380418007E
     */
    protected void createColumnHeader()
    {
        final String STR_METHOD_NAME = "createColumnHeader()";
        log.entering(STR_METHOD_NAME);

        final int COLUMN_MAX = 4;

        WEB3GentradeCsvColumnModel[] l_models = new WEB3GentradeCsvColumnModel[COLUMN_MAX];

        int l_intIndex = 0;
        //－　@index = 0
        //[CSVカラムモデル コンストラクタの引数]
        //項目ラベル：　@金商法@交付閲覧アップロードCSV.部店コードラベル
        //カラム番号： 0
        //項目型：　@CSVカラムモデル.項目型_文字列
        //日付フォーマット：　@null
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            branchCodeLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //－　@index = 1
        //[CSVカラムモデル コンストラクタの引数]
        //項目ラベル：　@金商法@交付閲覧アップロードCSV顧客コードラベル
        //カラム番号： 1
        //項目型：　@CSVカラムモデル.項目型_文字列
        //日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            accountCodeLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //－　@index = 2
        //[CSVカラムモデル コンストラクタの引数]
        //項目ラベル：　@金商法@交付閲覧アップロードCSV電子鳩銘柄コードラベル
        //カラム番号： 2
        //項目型：　@CSVカラムモデル.項目型_文字列
        //日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            batoProductCodeLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //－　@index = 3
        //[CSVカラムモデル コンストラクタの引数]
        //項目ラベル：　@金商法@交付閲覧アップロードCSV.書面交付日ラベル
        //カラム番号： 3
        //項目型：　@CSVカラムモデル.項目型_日付
        //日付フォーマット：　@"YYYYMMDD"
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            deliveryDateLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.DATETYPE,
            new SimpleDateFormat(WEB3GentradeTimeDef.DATE_FORMAT_YMD));

        this.setColumnHeader(l_models);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (getアップロードファ@イルID)<BR>
     * 金商法@交付閲覧アップロードCSV.アップロードファ@イルＩＤを返却する。<BR>
     * <BR>
     * ※（管理者共通）アップロードテーブル.アップロードファ@イルＩＤに格納する文字列<BR>
     * @@return String
     * @@roseuid 473805A70021
     */
    public String getUploadFileId()
    {
        final String STR_METHOD_NAME = "getUploadFileId()";
        log.entering(STR_METHOD_NAME);

        log.exiting(STR_METHOD_NAME);
        return this.uploadFileId;
    }

    /**
     * (get顧客)<BR>
     * 行番号に対応する明細行の顧客オブジェクトを取得する。<BR>
     * <BR>
     * アカウントマネージャ.get顧客()にて顧客オブジェクトを取得し返却する。<BR>
     * <BR>
     * [get顧客()に指定する引数]<BR>
     * 証券会社コード：　@引数.証券会社コード<BR>
     * 部店コード：　@this.get部店コード(行番号)<BR>
     * 口座コード：　@this.get顧客コード(行番号)<BR>
     * <BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@return WEB3GentradeMainAccount
     * @@throws WEB3BaseException
     * @@roseuid 473951A9013B
     */
    public WEB3GentradeMainAccount getMainAccount(
        int l_intLineNo, String l_strInstitutionCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getMainAccount(int, String)";
        log.entering(STR_METHOD_NAME);

        //アカウントマネージャ.get顧客()にて顧客オブジェクトを取得し返却する。
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        //[get顧客()に指定する引数]
        //証券会社コード：　@引数.証券会社コード
        //部店コード：　@this.get部店コード(行番号)
        //口座コード：　@this.get顧客コード(行番号)
        WEB3GentradeMainAccount l_mainAccount =
            l_accountManager.getMainAccount(
                l_strInstitutionCode,
                this.getBranchCode(l_intLineNo),
                this.getAccountCode(l_intLineNo));

        log.exiting(STR_METHOD_NAME);
        return l_mainAccount;
    }

    /**
     * (get顧客コード)<BR>
     * 行番号に対応する明細行の顧客コードを取得する。<BR>
     * <BR>
     * this.get項目値()にて顧客コードを取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(金商法@交付閲覧アップロードCSV.顧客コードラベル)の戻り値。<BR>
     * <BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 473804060319
     */
    public String getAccountCode(int l_intLineNo)
    {
        final String STR_METHOD_NAME = "getAccountCode(int)";
        log.entering(STR_METHOD_NAME);

        //カラム：　@getカラムモデル(金商法@交付閲覧アップロードCSV.顧客コードラベル)の戻り値。
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(this.accountCodeLabel);

        //this.get項目値()にて顧客コードを取得し返却する。
        String l_strAccountCode = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

        log.exiting(STR_METHOD_NAME);
        return l_strAccountCode;
    }

    /**
     * (get書面交付日)<BR>
     * 行番号に対応する明細行の書面交付日を取得する。<BR>
     * <BR>
     * this.get項目値()にて書面交付日を取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(金商法@交付閲覧アップロードCSV.書面交付日ラベル)の戻り値。<BR>
     * <BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 473804130207
     */
    public String getDeliveryDate(int l_intLineNo)
    {
        final String STR_METHOD_NAME = "getDeliveryDate(int)";
        log.entering(STR_METHOD_NAME);

        //カラム：　@getカラムモデル(金商法@交付閲覧アップロードCSV.書面交付日ラベル)の戻り値。
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(this.deliveryDateLabel);

        //this.get項目値()にて書面交付日を取得し返却する。
        Date l_datDeliveryDate = (Date)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

        String l_strDeliveryDate = WEB3DateUtility.formatDate(
            l_datDeliveryDate, WEB3GentradeTimeDef.DATE_FORMAT_YMD);
        log.exiting(STR_METHOD_NAME);
        return l_strDeliveryDate;
    }

    /**
     * (get電子鳩銘柄コード)<BR>
     * 行番号に対応する明細行の電子鳩銘柄コードを取得する。<BR>
     * <BR>
     * this.get項目値()にて電子鳩銘柄コードを取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(金商法@交付閲覧アップロードCSV.電子鳩銘柄コードラベル)の戻り値。<BR>
     * <BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     */
    public String getBatoProductCode(int l_intLineNo)
    {
        final String STR_METHOD_NAME = "getBatoProductCode(int)";
        log.entering(STR_METHOD_NAME);

        //カラム：　@getカラムモデル(金商法@交付閲覧アップロードCSV.電子鳩銘柄コードラベル)の戻り値。
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(this.batoProductCodeLabel);

        //this.get項目値()にて電子鳩銘柄コードを取得し返却する。
        String l_strbatoProductCode = (String)this.getValue(
            l_intLineNo, l_gentradeCsvColumnModel);

        log.exiting(STR_METHOD_NAME);
        return l_strbatoProductCode;
    }

    /**
     * (get部店コード)<BR>
     * 行番号に対応する明細行の部店コードを取得する。<BR>
     * <BR>
     * this.get項目値()にて部店コードを取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(金商法@交付閲覧アップロードCSV.部店コードラベル)の戻り値。<BR>
     * <BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 4738040501E2
     */
    public String getBranchCode(int l_intLineNo)
    {
        final String STR_METHOD_NAME = "getBranchCode(int)";
        log.entering(STR_METHOD_NAME);

        //カラム：　@getカラムモデル(金商法@交付閲覧アップロードCSV.部店コードラベル)の戻り値。
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(this.branchCodeLabel);

        //this.get項目値()にて部店コードを取得し返却する。
        String l_strBranchCode = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

        log.exiting(STR_METHOD_NAME);
        return l_strBranchCode;
    }

    /**
     * (get銘柄タイプ)<BR>
     * ProductTypeEnum.その他 を返却する。<BR>
     * @@return ProductTypeEnum
     * @@roseuid 473804140330
     */
    public ProductTypeEnum getProductType()
    {
        final String STR_METHOD_NAME = "getProductType()";
        log.entering(STR_METHOD_NAME);

        //ProductTypeEnum.その他 を返却する
        log.exiting(STR_METHOD_NAME);
        return ProductTypeEnum.OTHER;
    }

    /**
     * (updateアップロード中止)<BR>
     * 該当アップロード行にアップロード中止を更新する。<BR>
     * <BR>
     * 　@this.getアップロードＩＤ()に該当する行について<BR>
     * 以下の通り、（管理者共通）アップロードテーブルを更新する。<BR>
     * <BR>
     * 　@アップロード終了日時 = System.currentTimeMillis()<BR>
     * 　@アップロード件数 = 0<BR>
     * 　@備考１ = this.アップロード中止<BR>
     * <BR>
     * ※該当データがなくても、例外を上位にスローしない。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47380D60036A
     */
    public void updateUploadCancel() throws WEB3BaseException
    {

        final String STR_METHOD_NAME = "updateUploadCancel()";
        log.entering(STR_METHOD_NAME);

        //this.getアップロードＩＤ()に該当する行について
        //以下の通り、（管理者共通）アップロードテーブルを更新する。
        AdministratorUploadRow l_administratorUploadRow;
        try
        {
            l_administratorUploadRow =
                AdministratorUploadDao.findRowByAdministratorUploadId(this.getAdministratorUploadId());

            if (l_administratorUploadRow == null)
            {
                //※該当データがなくても、例外を上位にスローしない。
                log.exiting(STR_METHOD_NAME);
                return;
            }

            AdministratorUploadParams l_administratorUploadParams =
                new AdministratorUploadParams(l_administratorUploadRow);

            //アップロード終了日時 = System.currentTimeMillis()
            l_administratorUploadParams.setUploadEndTimestamp(new Timestamp(System.currentTimeMillis()));

            //アップロード件数 = 0
            l_administratorUploadParams.setUploadCount(0);

            //備考１ = this.アップロード中止
            l_administratorUploadParams.setNote1(this.uploadCancel);

            //以下の通り、（管理者共通）アップロードテーブルを更新する。
            Processors.getDefaultProcessor().doUpdateQuery(l_administratorUploadParams);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate重複顧客)<BR>
     * 処理対象レコードの書面交付管理テーブルへの登録状況をチェックする。<BR>
     * 登録アップロード処理（処理区分：0）の場合、登録済みであれば例外をスローする。<BR>
     * 削除アップロード処理（処理区分：1）の場合、未登録であれば例外をスローする。<BR>
     * <BR>
     * また、処理対象レコードがアップロードファ@イル中で重複している場合も<BR>
     * 例外をスローする。<BR>
     * <BR>
     * <BR>
     * １）　@書面交付管理テーブルのデータ登録状況をチェックする。<BR>
     * <BR>
     * 　@１-１）　@検索文字列を作成する。<BR>
     * <BR>
     * 　@　@１-１-１）　@空の文字列を生成する。<BR>
     * <BR>
     * 　@　@１-１-２）　@証券会社コードを１-１）の文字列に追加する。<BR>
     * <BR>
     * 　@　@　@　@　@"institution_code = ?"<BR>
     * <BR>
     * 　@　@１-１-３）　@部店コードを検索条件文字列に追加する。<BR>
     * <BR>
     * 　@　@　@　@　@検索条件文字列 += "and branch_code = ? "<BR>
     * <BR>
     * 　@　@１-１-４）　@顧客コードを検索条件文字列に追加する。<BR>
     * <BR>
     * 　@　@　@　@　@検索条件文字列 += "and account_code like ? %"<BR>
     * <BR>
     * 　@　@１-１-５）　@書面区分を検索条件文字列に追加する。<BR>
     * <BR>
     * 　@　@　@　@　@検索条件文字列 += "and document_div = ? "<BR>
     * <BR>
     * 　@　@１-１-６）　@銘柄コードを検索条件文字列に追加する。<BR>
     * <BR>
     * 　@　@　@　@　@検索条件文字列 += "and product_code = ? "<BR>
     * <BR>
     * 　@　@１-１-７）　@引数:検索情報.書面交付日を検索条件文字列に追加する<BR>
     * <BR>
     * 　@　@　@　@　@検索条件文字列 += "and delivery_date　@=　@?"<BR>
     * <BR>
     * 　@　@１-１-８）　@書面種類コードを検索条件文字列に追加する<BR>
     * <BR>
     * 　@　@　@　@　@検索条件文字列 += "document_category　@=　@?"<BR>
     * <BR>
     * 　@１-２）　@検索条件コンテナを作成する。<BR>
     * <BR>
     * 　@　@１-２-１）　@空のArrayListを生成する。<BR>
     * <BR>
     * 　@　@１-２-２）　@証券会社コード<BR>
     * <BR>
     * 　@　@　@　@　@引数.証券会社コード を１-２-１）のListに追加する。<BR>
     * <BR>
     * 　@　@１-２-３）　@部店コード <BR>
     * <BR>
     * 　@　@　@　@　@this.get部店コード()の戻り値を １-２-１）のListに追加する。<BR>
     * <BR>
     * 　@　@　@　@　@　@[get部店コード()に指定する引数]<BR>
     * 　@　@　@　@　@　@行番号：　@引数.行番号<BR>
     * <BR>
     * 　@　@１-２-４）　@顧客コード<BR>
     * <BR>
     * 　@　@　@　@　@this.get顧客コード()の戻り値を １-２-１）のListに追加する。<BR>
     * <BR>
     * 　@　@　@　@　@　@[get顧客コード()に指定する引数]<BR>
     * 　@　@　@　@　@　@行番号：　@引数.行番号<BR>
     * <BR>
     * 　@　@１-２-５）　@書面区分<BR>
     * <BR>
     * 　@　@　@　@　@'010' を １-２-１）のListに追加する。<BR>
     * <BR>
     * 　@　@１-２-６）　@銘柄コード<BR>
     * <BR>
     * 　@　@　@　@　@this.get電子鳩銘柄コード()の戻り値を １-２-１）のListに追加する。<BR>
     * <BR>
     * 　@　@　@　@　@　@[get電子鳩銘柄コード()に指定する引数]<BR>
     * 　@　@　@　@　@　@行番号：　@引数.行番号<BR>
     * <BR>
     * 　@　@１-２-７）　@書面交付日<BR>
     * <BR>
     * 　@　@　@　@　@this.get書面交付日()の戻り値を １-２-１）のListに追加する。<BR>
     * <BR>
     * 　@　@　@　@　@　@[get書面交付日()に指定する引数]<BR>
     * 　@　@　@　@　@　@行番号：　@引数.行番号<BR>
     * <BR>
     * 　@　@１-２-８） 書面種類コード<BR>
     * <BR>
     * 　@　@　@　@　@this.get電子鳩銘柄コード()の戻り値左3桁を １-２-１）のListに追加する。<BR>
     * <BR>
     * 　@　@　@　@　@　@[get電子鳩銘柄コード()に指定する引数]<BR>
     * 　@　@　@　@　@　@行番号：　@引数.行番号<BR>
     * <BR>
     * 　@　@１-２-９）　@１-２-１）のListをObject配列に変換する。<BR>
     * <BR>
     * <BR>
     * 　@１-３）　@書面交付管理テーブルからレコードを取得する。<BR>
     * <BR>
     * 　@　@１-３-１）　@書面交付管理オブジェクトを生成する<BR>
     * <BR>
     * 　@　@１-３-２）　@検索を行う。<BR>
     * <BR>
     * 　@　@　@　@　@[get書面交付管理()に指定する引数] <BR>
     * 　@　@　@　@　@　@　@検索文字列： １-１） で作成した検索文字列<BR>
     * 　@　@　@　@　@　@　@検索データコンテナ： １-２-８） で作成したデータコンテナ<BR>
     * <BR>
     * 　@１-４）　@引数.処理区分 == "0" かつ １-３-２） の戻り値長さ > 0 の場合、<BR>
     * 　@　@　@　@　@「指定の顧客は既に登録されています。」<BR>
     * 　@　@　@　@　@（BUSINESS_ERROR_02950）例外をスローする。<BR>
     * <BR>
     * 　@１-５）　@引数.処理区分 == "1" かつ １-３-２） の戻り値長さ == 0 の場合、<BR>
     * 　@　@　@　@　@「書面交付管理行が取得できない。」<BR>
     * 　@　@　@　@　@（BUSINESS_ERROR_02952）例外をスローする。<BR>
     * <BR>
     * <BR>
     * ２）　@アップロードファ@イル中に重複がないかチェックする。<BR>
     * <BR>
     * 　@２-１） 指定行数より前の明細行を取得する。（<BR>
     * 　@　@　@（追加済明細行数分Loop）<BR>
     * <BR>
     * 　@　@　@　@取得済みの明細行全項目と指定行番号より前の明細行の全項目を比較する。<BR>
     * 　@　@　@　@全項目が完全に一致する行が存在する場合は、顧客が重複して登録されていると判定し、例外をスローする。<BR>
     * <BR>
     * 　@　@　@　@※例外をスローした場合、以下のメッセージを表示する。<BR>
     * 　@　@　@　@『重複顧客チェックエラー（同じ値の行が存在する）』<BR>
     * 　@　@　@　@（エラーコード：BUSINESS_ERROR_00517）<BR>
     * <BR>
     * <BR>
     * <BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strProcedureDiv - (処理区分)<BR>
     * 処理区分<BR>
     * <BR>
     * 0：登録アップロード処理<BR>
     * 1：削除アップロード処理<BR>
     * @@throws WEB3BaseException
     * @@roseuid 473AAADA0144
     */
    public void validateDuplicateAccount(
        int l_intLineNo,
        String l_strInstitutionCode,
        String l_strProcedureDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateDuplicateAccount(int, String, String)";
        log.entering(STR_METHOD_NAME);

        //１） 書面交付管理テーブルのデータ登録状況をチェックする。
        //１-１） 検索文字列を作成する。
        //１-１-１） 空の文字列を生成する。
        StringBuffer l_sbWhere = new StringBuffer();

        //１-１-２） 証券会社コードを１-１）の文字列に追加する。
        // "institution_code = ?"
        l_sbWhere.append(" institution_code = ? ");

        //１-１-３） 部店コードを検索条件文字列に追加する。
        //検索条件文字列 += "and branch_code = ? "
        l_sbWhere.append(" and branch_code = ? ");

        //１-１-４） 顧客コードを検索条件文字列に追加する。
        //検索条件文字列 += "and account_code like ? %"
        l_sbWhere.append(" and account_code like ? || '%' ");

        //１-１-５） 書面区分を検索条件文字列に追加する。
        //検索条件文字列 += "and document_div = ? "
        l_sbWhere.append(" and document_div = ? ");

        //１-１-６）　@銘柄コードを検索条件文字列に追加する。
        //検索条件文字列 += "and product_code = ? "
        l_sbWhere.append(" and product_code = ? ");

        //１-１-７）　@引数:検索情報.書面交付日を検索条件文字列に追加する
        // 検索条件文字列 += "and delivery_date = ?"
        l_sbWhere.append(" and delivery_date = ? ");

        //１-１-８）　@書面種類コードを検索条件文字列に追加する
        //検索条件文字列 += "document_category = ?"
        l_sbWhere.append(" and document_category = ? ");

        //１-２） 検索条件コンテナを作成する。
        //１-２-１） 空のArrayListを生成する。
        List l_lisWheres = new ArrayList();

        //１-２-２） 証券会社コード
        //引数.証券会社コード を１-２-１）のListに追加する。
        l_lisWheres.add(l_strInstitutionCode);

        //１-２-３） 部店コード
        //this.get部店コード()の戻り値を １-２-１）のListに追加する。
        //[get部店コード()に指定する引数]
        //行番号：　@引数.行番号
        l_lisWheres.add(this.getBranchCode(l_intLineNo));

        //１-２-４） 顧客コード
        //this.get顧客コード()の戻り値を １-２-１）のListに追加する。
        //[get顧客コード()に指定する引数]
        //行番号：　@引数.行番号
        l_lisWheres.add(this.getAccountCode(l_intLineNo));

        //１-２-５） 書面区分
        //'010' を １-２-１）のListに追加する。
        l_lisWheres.add(WEB3CategCodeDef.DOCUMENT_DELIVERY);

        //１-２-６） 銘柄コード
        //this.get電子鳩銘柄コード()の戻り値を １-２-１）のListに追加する。
        String l_strBatoProductCode = this.getBatoProductCode(l_intLineNo);
        l_lisWheres.add(l_strBatoProductCode);

        //１-２-７） 書面交付日
        //this.get書面交付日()の戻り値を １-２-１）のListに追加する。
        //[get書面交付日()に指定する引数]
        //行番号：　@引数.行番号
        l_lisWheres.add(this.getDeliveryDate(l_intLineNo));

        //１-２-８） 書面種類コード
        //this.get電子鳩銘柄コード()の戻り値左3桁を １-２-１）のListに追加する。
        //[get電子鳩銘柄コード()に指定する引数]
        //行番号：　@引数.行番号
        l_lisWheres.add(l_strBatoProductCode.subSequence(0, 3));

        //１-２-９） １-２-１）のListをObject配列に変換する。
        Object[] l_wheres = new Object[l_lisWheres.size()];
        l_lisWheres.toArray(l_wheres);

        //１-３-２） 検索を行う。
        //[get書面交付管理()に指定する引数]
        //検索文字列： １-１） で作成した検索文字列
        //検索データコンテナ： １-２-８） で作成したデータコンテナ
        WEB3AdminFPTDocDeliveryManagement l_adminFPTDocDeliveryManagement =
            new WEB3AdminFPTDocDeliveryManagement();

        //１-３） 書面交付管理テーブルからレコードを取得する。
        //１-３-１） 書面交付管理オブジェクトを生成する
        List l_lisDocDivManagements =
            l_adminFPTDocDeliveryManagement.getDocDivManagement(
                l_sbWhere.toString(),
                l_wheres);

        //１-４） 引数.処理区分 == "0" かつ １-３-２） の戻り値長さ > 0 の場合、
        //「指定の顧客は既に登録されています。」
        //（BUSINESS_ERROR_02950）例外をスローする。
        if (WEB3FPTStatusDivDef.LOGIN.equals(l_strProcedureDiv)
            && l_lisDocDivManagements.size() > 0)
        {
            log.debug("指定の顧客は既に登録されています。");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02950,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "指定の顧客は既に登録されています。");
        }

        //１-５） 引数.処理区分 == "1" かつ １-３-２） の戻り値長さ == 0 の場合、
        //「書面交付管理行が取得できない。」
        //（BUSINESS_ERROR_02952）例外をスローする。
        if (WEB3FPTStatusDivDef.DELETE.equals(l_strProcedureDiv)
            && l_lisDocDivManagements.size() == 0)
        {
            log.debug("書面交付管理行が取得できない。");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02952,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "書面交付管理行が取得できない。");
        }

        //２） アップロードファ@イル中に重複がないかチェックする。
        // ２-１） 指定行数より前の明細行を取得する。（追加済明細行数分Loop）
        //取得済みの明細行全項目と指定行番号より前の明細行の全項目を比較する。
        //全項目が完全に一致する行が存在する場合は、顧客が重複して登録されていると判定し、例外をスローする。
        String l_strBranchCode = this.getBranchCode(l_intLineNo);
        String l_strAccountCode = this.getAccountCode(l_intLineNo);
        String l_strBatoProductCodeByLineNo = this.getBatoProductCode(l_intLineNo);
        String l_strDeliveryDate = this.getDeliveryDate(l_intLineNo);

        //※例外をスローした場合、以下のメッセージを表示する。
        // 『重複顧客チェックエラー（同じ値の行が存在する）』（エラーコード：BUSINESS_ERROR_00517）
        for (int i = 0; i < l_intLineNo; i++)
        {
            String l_strBranchCodePrevious = this.getBranchCode(i);
            String l_strAccountCodePrevious = this.getAccountCode(i);
            String l_strBatoProductCodePrevious = this.getBatoProductCode(i);
            String l_strDeliveryDatePrevious = this.getDeliveryDate(i);

            if (l_strBranchCode.equals(l_strBranchCodePrevious)
                && l_strAccountCode.equals(l_strAccountCodePrevious)
                && l_strBatoProductCodeByLineNo.equals(l_strBatoProductCodePrevious)
                && l_strDeliveryDate.equals(l_strDeliveryDatePrevious))
            {
                log.debug("重複顧客チェックエラー（同じ値の行が存在する）。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00517,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "重複顧客チェックエラー（同じ値の行が存在する）。");
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate明細行)<BR>
     * 明細行のチェックを行う。<BR>
     * <BR>
     * １）　@部店コードのチェック<BR>
     * 　@get部店コード()にて、指定行番号のデータを取得しチェックを行う。<BR>
     * <BR>
     * 　@[get部店コード()に指定する引数]<BR>
     * 　@　@行番号：　@引数.行番号<BR>
     * <BR>
     * 　@１－１）　@部店コードが取得できない場合（get部店コード() == null）、<BR>
     * 　@　@　@　@　@　@　@「部店コード・顧客コードが不正です。」<BR>
     * 　@　@　@　@　@　@　@（BUSINESS_ERROR_02414）例外をスローする。<BR>
     * 　@１－２）　@半角数字以外が含まれる場合は、「部店コード・顧客コードが不正です。」<BR>
     * 　@　@　@　@　@　@　@（BUSINESS_ERROR_02414）例外をスローする。<BR>
     * 　@１－３）　@文字数が3byteでない場合は、「部店コード・顧客コードが不正です。」<BR>
     * 　@　@　@　@　@　@　@（BUSINESS_ERROR_02414）例外をスローする。<BR>
     * <BR>
     * ２）　@顧客コードのチェック<BR>
     * 　@get顧客コード()にて、指定行番号のデータを取得しチェックを行う。<BR>
     * <BR>
     * 　@[get顧客コード()に指定する引数]<BR>
     * 　@　@行番号：　@引数.行番号<BR>
     * <BR>
     * 　@２－１）　@顧客コードが取得できない場合（get顧客コード() == null）、<BR>
     * 　@　@　@　@　@　@　@「部店コード・顧客コードが不正です。」<BR>
     * 　@　@　@　@　@　@　@（BUSINESS_ERROR_02414）例外をスローする。<BR>
     * 　@２－２）　@半角数字以外が含まれる場合は、「部店コード・顧客コードが不正です。」<BR>
     * 　@　@　@　@　@　@　@（BUSINESS_ERROR_02414）例外をスローする。<BR>
     * 　@２－３）　@文字数が6byteでない場合は、「部店コード・顧客コードが不正です。」<BR>
     * 　@　@　@　@　@　@　@（BUSINESS_ERROR_02414）例外をスローする。<BR>
     * 　@２－４）　@this.get顧客()をコールする。顧客オブジェクトが取得できない場合は、<BR>
     * 　@　@　@　@　@　@　@「部店コード・顧客コードが不正です。」<BR>
     * 　@　@　@　@　@　@　@（BUSINESS_ERROR_02414）例外をスローする。<BR>
     * <BR>
     * 　@　@[get顧客()に指定する引数]<BR>
     * 　@　@　@行番号：　@行番号<BR>
     * 　@　@　@証券会社コード：　@引数.管理者.get証券会社コード()の戻り値<BR>
     * <BR>
     * ３） 書面交付日のチェック<BR>
     * 　@get書面交付日()にて、指定行番号のデータを取得しチェックを行う。<BR>
     * <BR>
     * 　@[get書面交付日()に指定する引数]<BR>
     * 　@　@行番号：　@引数.行番号<BR>
     * <BR>
     * 　@３－１）　@書面交付日が取得できない場合は、「書面交付日が不正です。」<BR>
     * 　@　@（BUSINESS_ERROR）例外をスローする。<BR>
     * 　@　@class　@　@:　@WEB3BusinessLayerException<BR>
     * 　@　@tag　@　@　@:　@BUSINESS_ERROR_02961<BR>
     * <BR>
     * ４） 部店権限チェックを行う。<BR>
     * 　@引数.管理者.validate部店権限()をコールする。<BR>
     * <BR>
     * 　@　@[validate部店権限()に指定する引数]<BR>
     * 　@　@　@部店コード： １） で取得した部店コード<BR>
     * <BR>
     * <BR>
     * ５） 電子鳩銘柄コードのチェック<BR>
     * 　@　@get電子鳩銘柄コード()にて、指定行番号のデータを取得しチェックを行う。<BR>
     * <BR>
     * 　@　@[get電子鳩銘柄コード()に指定する引数]<BR>
     * 　@　@　@行番号：　@引数.行番号<BR>
     * <BR>
     * 　@５－１）　@電子鳩銘柄コードが取得できない場合は、「電子鳩銘柄コードが不正です。」<BR>
     * 　@　@　@　@　@　@（BUSINESS_ERROR）例外をスローする。<BR>
     * 　@　@　@　@　@　@class　@　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag　@　@　@:　@BUSINESS_ERROR_02995<BR>
     * 　@５－２）　@半角数字以外が含まれる場合は、「電子鳩銘柄コードが不正です。」<BR>
     * 　@　@　@　@　@　@（BUSINESS_ERROR）例外をスローする。<BR>
     * 　@　@　@　@　@　@class　@　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag　@　@　@:　@BUSINESS_ERROR_02995<BR>
     * 　@５－３）　@文字数が7byteでない場合は、「電子鳩銘柄コードが不正です。」<BR>
     * 　@　@　@　@　@　@（BUSINESS_ERROR）例外をスローする。<BR>
     * 　@　@　@　@　@　@class　@　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag　@　@　@:　@BUSINESS_ERROR_02995<BR>
     * <BR>
     * <BR>
     * ６）　@電子鳩銘柄コード存在チェックを行う。<BR>
     * 　@６-１） 電子鳩銘柄コード管理オブジェクトを生成する。<BR>
     * <BR>
     * 　@　@[電子鳩銘柄コード管理()に指定する引数]<BR>
     * 　@　@　@証券会社コード： 引数.管理者.get証券会社コード()の戻り値<BR>
     * 　@　@　@部店コード： １） で取得した部店コード<BR>
     * 　@　@　@書面区分： "010"<BR>
     * 　@　@　@電子鳩銘柄コード： ５） で取得した電子鳩銘柄コード<BR>
     * <BR>
     * 　@６-２）　@電子鳩銘柄コード管理#is電子鳩銘柄コード()をコールする。<BR>
     * 　@　@　@　@　@falseが返却された場合、「電子鳩銘柄コードが不正です。」<BR>
     * 　@　@　@　@　@　@（BUSINESS_ERROR）例外をスローする。<BR>
     * 　@　@　@　@　@　@class　@　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag　@　@　@:　@BUSINESS_ERROR_02995<BR>
     * <BR>
     * ７）　@書面種類コード存在チェックを行う。<BR>
     * 　@７-１）　@書面種類管理オブジェクトを生成する。<BR>
     * <BR>
     * 　@　@[書面種類管理()に指定する引数]<BR>
     * 　@　@　@証券会社コード： 引数.管理者.get証券会社コード()の戻り値<BR>
     * 　@　@　@部店コード： １） で取得した部店コードを要素とする長さ１のString配列<BR>
     * 　@　@　@書面種類コード： ５） で取得した電子鳩銘柄コードの左３桁<BR>
     * <BR>
     * 　@７-２）　@書面種類管理#is書面種類コード()をコールする。<BR>
     * 　@　@　@　@　@falseが返却された場合、「指定した電子鳩銘柄コードが正しくありません （上3桁が不正）。」<BR>
     * 　@　@　@　@　@　@（BUSINESS_ERROR）例外をスローする。<BR>
     * 　@　@　@　@　@　@class　@　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag　@　@　@:　@BUSINESS_ERROR_02996<BR>
     * <BR>
     * <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * @@param l_administrator - (管理者)<BR>
     * 管理者オブジェクト<BR>
     * @@throws WEB3BaseException
     * @@roseuid 473810A2000F
     */
    public void validateDetailLine(
        int l_intLineNumber,
        WEB3Administrator l_administrator) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateDetailLine(int, WEB3Administrator)";
        log.entering(STR_METHOD_NAME);

        if (l_administrator == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + "." + STR_METHOD_NAME,
                 "パラメータ値不正。");
        }
        //１）　@部店コードのチェック
        //get部店コード()にて、指定行番号のデータを取得しチェックを行う。
        //[get部店コード()に指定する引数]
        //行番号：　@引数.行番号
        String l_strBranchCode = this.getBranchCode(l_intLineNumber);

        //１－１）　@部店コードが取得できない場合（get部店コード() == null）、
        //「部店コード・顧客コードが不正です。」（BUSINESS_ERROR_02414）例外をスローする。
        if (l_strBranchCode == null)
        {
            log.debug("部店コード・顧客コードが不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02414,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "部店コード・顧客コードが不正です。");
        }

        //１－２）　@半角数字以外が含まれる場合は、「部店コード・顧客コードが不正です。」
        //（BUSINESS_ERROR_02414）例外をスローする。
        if (!WEB3StringTypeUtility.isDigit(l_strBranchCode))
        {
            log.debug("部店コード・顧客コードが不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02414,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "部店コード・顧客コードが不正です。");
        }

        //１－３）　@文字数が3byteでない場合は、「部店コード・顧客コードが不正です。」
        // （BUSINESS_ERROR_02414）例外をスローする。
        if (WEB3StringTypeUtility.getByteLength(l_strBranchCode) != 3)
        {
            log.debug("部店コード・顧客コードが不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02414,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "部店コード・顧客コードが不正です。");
        }

        //２）　@顧客コードのチェック
        //get顧客コード()にて、指定行番号のデータを取得しチェックを行う。
        //[get顧客コード()に指定する引数]
        //行番号：　@引数.行番号
        String l_strAccountCode = this.getAccountCode(l_intLineNumber);

        //２－１）　@顧客コードが取得できない場合（get顧客コード() == null）、
        //「部店コード・顧客コードが不正です。」（BUSINESS_ERROR_02414）例外をスローする。
        if (l_strAccountCode == null)
        {
            log.debug("部店コード・顧客コードが不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02414,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "部店コード・顧客コードが不正です。");
        }

        //２－２）　@半角数字以外が含まれる場合は、「部店コード・顧客コードが不正です。」
        //（BUSINESS_ERROR_02414）例外をスローする。
        if (!WEB3StringTypeUtility.isDigit(l_strAccountCode))
        {
            log.debug("部店コード・顧客コードが不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02414,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "部店コード・顧客コードが不正です。");
        }

        //２－３）　@文字数が6byteでない場合は、「部店コード・顧客コードが不正です。」
        //（BUSINESS_ERROR_02414）例外をスローする。
        if (WEB3StringTypeUtility.getByteLength(l_strAccountCode) != 6)
        {
            log.debug("部店コード・顧客コードが不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02414,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "部店コード・顧客コードが不正です。");
        }

        //２－４）　@this.get顧客()をコールする。顧客オブジェクトが取得できない場合は、
        //「部店コード・顧客コードが不正です。」（BUSINESS_ERROR_02414）例外をスローする。
        //[get顧客()に指定する引数]
        //行番号：　@行番号
        //証券会社コード：　@引数.管理者.get証券会社コード()の戻り値
        try
        {
            this.getMainAccount(l_intLineNumber, l_administrator.getInstitutionCode());
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("部店コード・顧客コードが不正です。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02414,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "部店コード・顧客コードが不正です。");
        }

        //３） 書面交付日のチェック
        //get書面交付日()にて、指定行番号のデータを取得しチェックを行う。
        //[get書面交付日()に指定する引数]
        //行番号：　@引数.行番号
        String l_strDeliveryDate = this.getDeliveryDate(l_intLineNumber);

        //３－１）　@書面交付日が取得できない場合は、「書面交付日が不正です。」（BUSINESS_ERROR_02961）例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(l_strDeliveryDate))
        {
            log.debug("書面交付日が不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02961,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "書面交付日が不正です。");
        }

        //４） 部店権限チェックを行う。
        //引数.管理者.validate部店権限()をコールする。
        //[validate部店権限()に指定する引数]
        //部店コード： １） で取得した部店コード
        l_administrator.validateBranchPermission(l_strBranchCode);

        //５） 電子鳩銘柄コードのチェック
        //get電子鳩銘柄コード()にて、指定行番号のデータを取得しチェックを行う。
        //[get電子鳩銘柄コード()に指定する引数]
        //行番号：　@引数.行番号
        String l_strBatoProductCode = this.getBatoProductCode(l_intLineNumber);

        // ５－１）　@電子鳩銘柄コードが取得できない場合は、「電子鳩銘柄コードが不正です。」
        if (WEB3StringTypeUtility.isEmpty(l_strBatoProductCode))
        {
            log.debug("電子鳩銘柄コードが不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02995,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "電子鳩銘柄コードが不正です。");
        }

        //５－２）　@半角数字以外が含まれる場合は、「電子鳩銘柄コードが不正です。」
        if (!WEB3StringTypeUtility.isDigit(l_strBatoProductCode))
        {
            log.debug("電子鳩銘柄コードが不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02995,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "電子鳩銘柄コードが不正です。");
        }

        //５－３）　@文字数が7byteでない場合は、「電子鳩銘柄コードが不正です。」
        if (WEB3StringTypeUtility.getByteLength(l_strBatoProductCode) != 7)
        {
            log.debug("電子鳩銘柄コードが不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02995,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "電子鳩銘柄コードが不正です。");
        }

        //６） 電子鳩銘柄コード存在チェックを行う。
        //６-１） 電子鳩銘柄コード管理オブジェクトを生成する。
        //[電子鳩銘柄コード管理()に指定する引数]
        //証券会社コード： 引数.管理者.get証券会社コード()の戻り値
        //部店コード： １） で取得した部店コード
        //書面区分： "010"
        //電子鳩銘柄コード： ５） で取得した電子鳩銘柄コード
        WEB3AdminFPTBatoProductCodeManagement l_batoProductCodeManagement =
            new WEB3AdminFPTBatoProductCodeManagement(
                l_administrator.getInstitutionCode(),
                l_strBranchCode,
                WEB3CategCodeDef.DOCUMENT_DELIVERY,
                l_strBatoProductCode);

        //６-２） 電子鳩銘柄コード管理#is電子鳩銘柄コード()をコールする。
        //falseが返却された場合、「電子鳩銘柄コードが不正です。」
        if (!l_batoProductCodeManagement.isBatoProductCode())
        {
            log.debug("電子鳩銘柄コードが不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02995,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "電子鳩銘柄コードが不正です。");
        }

        //７） 書面種類コード存在チェックを行う。
        //７-１） 書面種類管理オブジェクトを生成する。
        //[書面種類管理()に指定する引数]
        //証券会社コード： 引数.管理者.get証券会社コード()の戻り値
        //部店コード： １） で取得した部店コードを要素とする長さ１のString配列
        //書面種類コード： ５） で取得した電子鳩銘柄コードの左３桁
        String[] l_strBranchCodes = new String[1];
        l_strBranchCodes[0] = l_strBranchCode;
        WEB3AdminFPTDocCategoryManagement l_docCategoryManagement =
            new WEB3AdminFPTDocCategoryManagement(
                l_administrator.getInstitutionCode(),
                l_strBranchCodes,
                l_strBatoProductCode.substring(0, 3));

        //７-２） 書面種類管理#is書面種類コード()をコールする。
        //falseが返却された場合、「書面種類コードが不正です。」
        if (!l_docCategoryManagement.isDocumentCategory())
        {
            log.debug("指定した電子鳩銘柄コードが正しくありません （上3桁が不正）。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02996,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "指定した電子鳩銘柄コードが正しくありません （上3桁が不正）。");
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
