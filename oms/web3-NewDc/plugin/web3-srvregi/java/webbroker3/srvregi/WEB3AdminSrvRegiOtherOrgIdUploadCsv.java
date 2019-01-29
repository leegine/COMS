head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.39.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiOtherOrgIdUploadCsv.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞCSV(WEB3AdminSrvRegiOtherOrgIdUploadCsv.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/10 武波 (中訊) 新規作成 モデル337,349,351,352
*/

package webbroker3.srvregi;

import java.text.SimpleDateFormat;
import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3OtherOrgStatusDef;
import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.gentrade.WEB3GentradeCsvUploadDataModel;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiOtherOrgService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;


/**
 * (外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞCSV)<BR>
 * 外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞCSV<BR>
 *
 * @@author 武波
 * @@version 1.0
 */
public class WEB3AdminSrvRegiOtherOrgIdUploadCsv extends WEB3GentradeCsvUploadDataModel
{

    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminSrvRegiOtherOrgIdUploadCsv.class);

    /**
     * (通番ラベル)<BR>
     * 定数定義プロパティ　@"通番"<BR>
     */
    public static  String SEQUENCE_NUMBER_LABEL = "通番";

    /**
     * (IDラベル)<BR>
     * 定数定義プロパティ　@"ID"<BR>
     */
    public static  String ID_LABEL = "ID";

    /**
     * (パスワードラベル)<BR>
     * 定数定義プロパティ　@”パスワード”<BR>
     */
    public static  String PASSWORD_LABEL = "パスワード" ;

    /**
     * (ステータスラベル)<BR>
     * 定数定義プロパティ　@"ステータス"<BR>
     */
    public static  String STATUS_LABEL = "ステータス";

    /**
     * (証券会社コードラベル)<BR>
     * 定数定義プロパティ　@”証券会社コード”<BR>
     */
    public static  String INSTITUTION_CODE_LABEL = "証券会社コード";

    /**
     * (部店コードラベル)<BR>
     * 定数定義プロパティ　@”部店コード”<BR>
     */
    public static  String BRANCH_CODE_LABEL = "部店コード";

    /**
     * (口座コードラベル)<BR>
     * 定数定義プロパティ　@”口座コード”<BR>
     */
    public static  String ACCOUNT_CODE_LABEL = "口座コード";

    /**
     * (適用期間Fromラベル)<BR>
     * 定数定義プロパティ　@"適用期間From"<BR>
     */
    public static  String APPLI_START_DATE_LABEL = "適用期間From";

    /**
     * (適用期間Toラベル)<BR>
     * 定数定義プロパティ　@"適用期間To"<BR>
     */
    public static  String APPLI_END_DATE_LABEL = "適用期間To";

    /**
     * (アップロードファ@イルID)<BR>
     * 定数定義プロパティ　@"外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ"<BR>
     */
    public static  String UPLOAD_FILE_ID = "外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ";

    /**
     * (アップロード区分_新規登録)<BR>
     * 定数定義プロパティ　@"0"<BR>
     */
    private static  String UPLOAD_DIV_NEW_REGIST = "0";

    /**
     * (アップロード区分_変更登録)<BR>
     * 定数定義プロパティ　@"1"<BR>
     */
    private static  String UPLOAD_DIV_CHANGE_REGIST = "1";

    /**
     * (アップロード_新規登録_項目数)<BR>
     * 定数定義プロパティ　@4<BR>
     */
    private int uploadNewRegistItemCount = 4;

    /**
     * (アップロード区分)<BR>
     * アップロード区分<BR>
     */
    public String uploadDiv;

    /**
     * @@roseuid 47D1112C01B7
     */
    public WEB3AdminSrvRegiOtherOrgIdUploadCsv()
    {

    }

    /**
     * (外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞCSV)<BR>
     * コンストラクタ<BR>
     * <BR>
     * －引数のアップロードID（Long）をlong型に変換してプロパティにセットする。<BR>
     * 　@（引数のアップロードID（Long）＝nullの場合処理なし）<BR>
     * @@param l_uploadId - (アップロードID)<BR>
     * アップロードID<BR>
     * @@roseuid 47BE50DF030B
     */
    public WEB3AdminSrvRegiOtherOrgIdUploadCsv(Long l_uploadId)
    {
        //－引数のアップロードID（Long）をlong型に変換してプロパティにセットする。
        //（引数のアップロードID（Long）＝nullの場合処理なし）
        if (l_uploadId != null)
        {
            this.administratorUploadId = l_uploadId.longValue();
        }
    }

    /**
     * (getアップロードファ@イルID)<BR>
     * 外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞCSV.アップロードファ@イルＩＤを返却する。<BR>
     * <BR>
     * @@return String
     * @@roseuid 473805A70021
     */
    public String getUploadFileId()
    {
        final String STR_METHOD_NAME = "getUploadFileId()";
        log.entering(STR_METHOD_NAME);

        log.exiting(STR_METHOD_NAME);
        return UPLOAD_FILE_ID;
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
     * (createカラムヘッダ)<BR>
     * アップロード区分によりカラムヘッダをセットする。<BR>
     * <BR>
     * 1) this.isアップロード新規登録()　@＝　@true　@の場合、<BR>
     * 　@　@createカラムヘッダ_新規登録()をコールし、登録用のカラムヘッダをセットする。<BR>
     * <BR>
     * 2) this.isアップロード新規登録()　@＝　@false　@の場合、<BR>
     * 　@　@createカラムヘッダ_変更登録()をコールし、変更用のカラムヘッダをセットする。<BR>
     * <BR>
     * @@roseuid 47BCFD4602EE
     */
    public void createColumnHeader()
    {
        final String STR_METHOD_NAME = "createColumnHeader()";
        log.entering(STR_METHOD_NAME);

        //1) this.isアップロード新規登録()　@＝　@true　@の場合、
        if (this.isUploadNewRegist())
        {
            //createカラムヘッダ_新規登録()をコールし、登録用のカラムヘッダをセットする。
            this.createColumnHeaderNewRegist();
        }
        else
        {
            //2) this.isアップロード新規登録()　@＝　@false　@の場合、
            //createカラムヘッダ_変更登録()をコールし、変更用のカラムヘッダをセットする。
            this.createColumnHeaderChangeRegist();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (createカラムヘッダ_新規登録)<BR>
     * カラムヘッダをセットする。<BR>
     * <BR>
     * 　@以下の通りCSVカラムモデルの配列を生成し、<BR>
     * 　@　@setカラムヘッダ()にてインスタンスにセットする。<BR>
     * <BR>
     * [カラムヘッダ配列]<BR>
     * <BR>
     * －　@index = 0<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞCSV.通番ラベル<BR>
     * 　@カラム番号： 0<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_数値(long)<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 1<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞCSV.IDラベル<BR>
     * 　@カラム番号： 1<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 2<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞCSV.パスワードラベル<BR>
     * 　@カラム番号： 2<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 3<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞCSV.ステータスラベル<BR>
     * 　@カラム番号： 3<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * <BR>
     * @@roseuid 47BE1DA300DE
     */
    private void createColumnHeaderNewRegist()
    {
        final String STR_METHOD_NAME = "createColumnHeaderNewRegist()";
        log.entering(STR_METHOD_NAME);

        final int COLUMN_MAX = 4;

        WEB3GentradeCsvColumnModel[] l_models = new WEB3GentradeCsvColumnModel[COLUMN_MAX];

        int l_intIndex = 0;
        //－　@index = 0
        //[CSVカラムモデル コンストラクタの引数]
        //項目ラベル：　@外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞCSV.通番ラベル
        //カラム番号： 0
        //項目型：　@CSVカラムモデル.項目型_数値(long)
        //日付フォーマット：　@null
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            SEQUENCE_NUMBER_LABEL,
            l_intIndex,
            WEB3GentradeCsvColumnModel.LONGTYPE,
            null);

        //－　@index = 1
        //[CSVカラムモデル コンストラクタの引数]
        //項目ラベル：　@外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞCSV.IDラベル
        //カラム番号： 1
        //項目型：　@CSVカラムモデル.項目型_文字列
        //日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            ID_LABEL,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //－　@index = 2
        //[CSVカラムモデル コンストラクタの引数]
        //項目ラベル：　@外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞCSV.パスワードラベル
        //カラム番号： 2
        //項目型：　@CSVカラムモデル.項目型_文字列
        //日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            PASSWORD_LABEL,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //－　@index = 3
        //[CSVカラムモデル コンストラクタの引数]
        //項目ラベル：　@外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞCSV.ステータスラベル
        //カラム番号： 3
        //項目型：　@CSVカラムモデル.項目型_文字列
        //日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            STATUS_LABEL,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        this.setColumnHeader(l_models);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (createカラムヘッダ_変更登録)<BR>
     * カラムヘッダをセットする。<BR>
     * <BR>
     * 　@以下の通りCSVカラムモデルの配列を生成し、setカラムヘッダ()にてインスタンスにセットする。<BR>
     * <BR>
     * [カラムヘッダ配列]<BR>
     * <BR>
     * －　@index = 0<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞCSV.通番ラベル<BR>
     * 　@カラム番号： 0<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_数値(long)<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 1<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞCSV.IDラベル<BR>
     * 　@カラム番号： 1<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 2<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞCSV.ステータスラベル<BR>
     * 　@カラム番号： 2<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 3<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞCSV.証券会社コードラベル<BR>
     * 　@カラム番号： 3<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 4<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞCSV.部店コードラベル<BR>
     * 　@カラム番号： 4<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 5<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞCSV.口座コードラベル<BR>
     * 　@カラム番号： 5<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 6<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞCSV.適用期間Fromラベル<BR>
     * 　@カラム番号： 6<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_日付<BR>
     * 　@日付フォーマット：　@new SimpleDateFormat("yyyyMMdd")<BR>
     * <BR>
     * －　@index = 7<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞCSV.適用期間Toラベル<BR>
     * 　@カラム番号： 7<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_日付<BR>
     * 　@日付フォーマット：　@new SimpleDateFormat("yyyyMMdd")<BR>
     * <BR>
     * <BR>
     * @@roseuid 47BE1DB4023F
     */
    private void createColumnHeaderChangeRegist()
    {
        final String STR_METHOD_NAME = "createColumnHeaderChangeRegist()";
        log.entering(STR_METHOD_NAME);

        final int COLUMN_MAX = 8;

        WEB3GentradeCsvColumnModel[] l_models = new WEB3GentradeCsvColumnModel[COLUMN_MAX];

        int l_intIndex = 0;
        //－　@index = 0
        //[CSVカラムモデル コンストラクタの引数]
        //項目ラベル：　@外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞCSV.通番ラベル
        //カラム番号： 0
        //項目型：　@CSVカラムモデル.項目型_数値(long)
        //日付フォーマット：　@null
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            SEQUENCE_NUMBER_LABEL,
            l_intIndex,
            WEB3GentradeCsvColumnModel.LONGTYPE,
            null);

        //－　@index = 1
        //[CSVカラムモデル コンストラクタの引数]
        //項目ラベル：　@外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞCSV.IDラベル
        //カラム番号： 1
        //項目型：　@CSVカラムモデル.項目型_文字列
        //日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            ID_LABEL,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //－　@index = 2
        //[CSVカラムモデル コンストラクタの引数]
        //項目ラベル：　@外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞCSV.ステータスラベル
        //カラム番号： 2
        //項目型：　@CSVカラムモデル.項目型_文字列
        //日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            STATUS_LABEL,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //－　@index = 3
        //[CSVカラムモデル コンストラクタの引数]
        //項目ラベル：　@外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞCSV.証券会社コードラベル
        //カラム番号： 3
        //項目型：　@CSVカラムモデル.項目型_文字列
        //日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            INSTITUTION_CODE_LABEL,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //－　@index = 4
        //[CSVカラムモデル コンストラクタの引数]
        //項目ラベル：　@外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞCSV.部店コードラベル
        //カラム番号： 4
        //項目型：　@CSVカラムモデル.項目型_文字列
        //日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            BRANCH_CODE_LABEL,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //－　@index = 5
        //[CSVカラムモデル コンストラクタの引数]
        //項目ラベル：　@外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞCSV.口座コードラベル
        //カラム番号： 5
        //項目型：　@CSVカラムモデル.項目型_文字列
        //日付フォーマット：　@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            ACCOUNT_CODE_LABEL,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //－　@index = 6
        //[CSVカラムモデル コンストラクタの引数]
        //項目ラベル：　@外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞCSV.適用期間Fromラベル
        //カラム番号： 6
        //項目型：　@CSVカラムモデル.項目型_日付
        //日付フォーマット：　@new SimpleDateFormat("yyyyMMdd")
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            APPLI_START_DATE_LABEL,
            l_intIndex,
            WEB3GentradeCsvColumnModel.DATETYPE,
            new SimpleDateFormat(WEB3GentradeTimeDef.DATE_FORMAT_YMD));

        //－　@index = 7
        //[CSVカラムモデル コンストラクタの引数]
        //項目ラベル：　@外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞCSV.適用期間Toラベル
        //カラム番号： 7
        //項目型：　@CSVカラムモデル.項目型_日付
        //日付フォーマット：　@new SimpleDateFormat("yyyyMMdd")
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            APPLI_END_DATE_LABEL,
            l_intIndex,
            WEB3GentradeCsvColumnModel.DATETYPE,
            new SimpleDateFormat(WEB3GentradeTimeDef.DATE_FORMAT_YMD));

        this.setColumnHeader(l_models);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (setアップロード区分)<BR>
     * アップロード区分を設定する。<BR>
     * （※引数のアップロード区分か明細行文字列の必ず一方はnull、他方はnot nullがセットされている）<BR>
     * <BR>
     * 1)　@引数のアップロード区分 != null　@の場合、引数のアップロード区分をプロパティにセットする。<BR>
     * <BR>
     * 2)　@引数の明細行文字列  != null　@の場合、以下の処理を行う。<BR>
     * 　@2-1)　@明細行解析<BR>
     * 　@明細行文字列.split(CSVデータモデル.区切り文字)にて、<BR>
     * 　@　@区切り文字ごとのtoken[]に分割する。<BR>
     * 　@2-2)　@2-1)で取得したtoken[]の要素数を取得し、 その要素数によりアップロード区分をセット<BR>
     * 　@2-2-1)　@token[]の要素数 == アップロード_新規登録_項目数　@の場合　@<BR>
     * 　@　@　@　@　@　@アップロード区分 == アップロード区分_新規登録<BR>
     * 　@2-2-2)　@2-2-1)以外　@の場合　@<BR>
     * 　@　@　@　@　@　@アップロード区分 == アップロード区分_変更登録<BR>
     * @@param l_strUploadDiv - (アップロード区分)<BR>
     * アップロード区分<BR>
     * @@param l_strDetailString - (明細行文字列)<BR>
     * 明細行文字列<BR>
     * <BR>
     * ※ ","を区切り文字にした明細行文字列。<BR>
     * @@roseuid 47C276960104
     */
    public void setUploadDiv(String l_strUploadDiv, String l_strDetailString)
    {
        final String STR_METHOD_NAME = "setUploadDiv(String, String)";
        log.entering(STR_METHOD_NAME);

        //1) 引数のアップロード区分 != null　@の場合、引数のアップロード区分をプロパティにセットする。
        if (l_strUploadDiv != null)
        {
            this.uploadDiv = l_strUploadDiv;
        }

        //2) 引数の明細行文字列  != null　@の場合、以下の処理を行う。
        if (l_strDetailString != null)
        {
            //2-1) 明細行解析
            //明細行文字列.split(CSVデータモデル.区切り文字)にて、区切り文字ごとのtoken[]に分割する。
            String token[] = l_strDetailString.split(regex);

            if (l_strDetailString.lastIndexOf(regex) == (l_strDetailString.length() - 1))
            {
                l_strDetailString = l_strDetailString + 1;
                token = l_strDetailString.split(regex);
                token[token.length - 1] = "";
            }

            //2-2) 2-1)で取得したtoken[]の要素数を取得し、 その要素数によりアップロード区分をセット
            //2-2-1) token[]の要素数 == アップロード_新規登録_項目数　@の場合
            //アップロード区分 == アップロード区分_新規登録
            if (token.length == uploadNewRegistItemCount)
            {
                this.uploadDiv = UPLOAD_DIV_NEW_REGIST;
            }
            else
            {
                //2-2-2) 2-2-1)以外　@の場合
                //アップロード区分 == アップロード区分_変更登録
                this.uploadDiv = UPLOAD_DIV_CHANGE_REGIST;
            }
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (getアップロード区分)<BR>
     * this.アップロード区分を返却する。<BR>
     * @@return String
     * @@roseuid 47C2864D03E0
     */
    public String getUploadDiv()
    {
        final String STR_METHOD_NAME = "getUploadDiv()";
        log.entering(STR_METHOD_NAME);

        log.exiting(STR_METHOD_NAME);
        return this.uploadDiv;
    }

    /**
     * (isアップロード新規登録)<BR>
     * 1) アップロード区分を判定し、対応するboolean値を返却する。<BR>
     * 　@1-1) アップロード区分 == アップロード区分_新規登録　@の場合、trueを返却する。<BR>
     * 　@1-2) 1-1) 以外　@の場合、falseを返却する。<BR>
     * <BR>
     * <BR>
     * @@return boolean
     * @@roseuid 47BEAC030388
     */
    public boolean isUploadNewRegist()
    {
        final String STR_METHOD_NAME = "isUploadNewRegist()";
        log.entering(STR_METHOD_NAME);

        //1-1) アップロード区分 == アップロード区分_新規登録　@の場合、trueを返却する。
        if (UPLOAD_DIV_NEW_REGIST.equals(uploadDiv))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            //1-2) 1-1) 以外　@の場合、falseを返却する。
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }

    /**
     * (get通番)<BR>
     * 外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞCSV.通番を返却する。<BR>
     * 行番号に対応する明細行の通番を取得する。<BR>
     * <BR>
     * this.get項目値()にて通番を取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞCSV.通番ラベル)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号を指定する。<BR>
     * @@return long
     * @@roseuid 47BCFD47001F
     */
    public long getSequenceNumber(int l_intLineNo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getSequenceNumber(int)";
        log.entering(STR_METHOD_NAME);

        //カラム：　@getカラムモデル(外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞCSV.通番ラベル)の戻り値。
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(SEQUENCE_NUMBER_LABEL);

        //this.get項目値()にて通番を取得し返却する。
        Long l_sequenceNumber = (Long)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

		if (l_sequenceNumber == null)
		{
			log.debug("通番が取得できません。");
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_03059,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				"通番が取得できません。");
		}

        log.exiting(STR_METHOD_NAME);
        return l_sequenceNumber.longValue();
    }

    /**
     * (getID)<BR>
     * 外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞCSV.ID を返却する。<BR>
     * 行番号に対応する明細行のID を取得する。<BR>
     * <BR>
     * this.get項目値()にてID を取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞCSV.ID)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号を指定する。<BR>
     * @@return String
     * @@roseuid 47BCFD4700AC
     */
    public String getId(int l_intLineNo)
    {
        final String STR_METHOD_NAME = "getId(int)";
        log.entering(STR_METHOD_NAME);

        //カラム：　@getカラムモデル(外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞCSV.ID)の戻り値。
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(ID_LABEL);

        //this.get項目値()にてID を取得し返却する。
        String l_strId = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

        log.exiting(STR_METHOD_NAME);
        return l_strId;
    }

    /**
     * (getパスワード)<BR>
     * 外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞCSV.パスワード を返却する。<BR>
     * 行番号に対応する明細行のパスワードを取得する。<BR>
     * <BR>
     * this.get項目値()にてパスワードを取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞCSV.パスワード)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号を指定する。<BR>
     * @@return String
     * @@roseuid 47BE1E900118
     */
    public String getPassword(int l_intLineNo)
    {
        final String STR_METHOD_NAME = "getPassword(int)";
        log.entering(STR_METHOD_NAME);

        //カラム：　@getカラムモデル(外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞCSV.パスワード)の戻り値。
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(PASSWORD_LABEL);

        //this.get項目値()にてパスワードを取得し返却する。
        String l_strPassword = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

        log.exiting(STR_METHOD_NAME);
        return l_strPassword;
    }

    /**
     * (getステータス)<BR>
     * 外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞCSV.ステータスを返却する。<BR>
     * 行番号に対応する明細行のステータスを取得する。<BR>
     * <BR>
     * this.get項目値()にてステータスを取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞCSV.ステータス)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号を指定する。<BR>
     * @@return String
     * @@roseuid 47BCFD470148
     */
    public String getStatus(int l_intLineNo)
    {
        final String STR_METHOD_NAME = "getStatus(int)";
        log.entering(STR_METHOD_NAME);

        //カラム：　@getカラムモデル(外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞCSV.ステータス)の戻り値。
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(STATUS_LABEL);

        //this.get項目値()にてステータスを取得し返却する。
        String l_strStatus = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

        log.exiting(STR_METHOD_NAME);
        return l_strStatus;
    }

    /**
     * (get証券会社コード)<BR>
     * 外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞCSV.証券会社コードを返却する。<BR>
     * 行番号に対応する明細行の証券会社コードを取得する。<BR>
     * <BR>
     * this.get項目値()にて証券会社コードを取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞCSV.証券会社コード)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号を指定する。<BR>
     * @@return String
     * @@roseuid 47BCFD4701D4
     */
    public String getInstitutionCode(int l_intLineNo)
    {
        final String STR_METHOD_NAME = "getInstitutionCode(int)";
        log.entering(STR_METHOD_NAME);

        //カラム：　@getカラムモデル(外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞCSV.証券会社コード)の戻り値。
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(INSTITUTION_CODE_LABEL);

        //this.get項目値()にて証券会社コードを取得し返却する。
        String l_strInstitutionCode = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

        log.exiting(STR_METHOD_NAME);
        return l_strInstitutionCode;
    }

    /**
     * (get部店コード)<BR>
     * 外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞCSV.部店コードを返却する。<BR>
     * 行番号に対応する明細行の部店コードを取得する。<BR>
     * <BR>
     * this.get項目値()にて部店コードを取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞCSV.部店コード)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号を指定する。<BR>
     * @@return String
     * @@roseuid 47BCFD470271
     */
    public String getBranchCode(int l_intLineNo)
    {
        final String STR_METHOD_NAME = "getBranchCode(int)";
        log.entering(STR_METHOD_NAME);

        //カラム：　@getカラムモデル(外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞCSV.部店コード)の戻り値。
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(BRANCH_CODE_LABEL);

        //this.get項目値()にて部店コードを取得し返却する。
        String l_strBranchCode = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

        log.exiting(STR_METHOD_NAME);
        return l_strBranchCode;
    }

    /**
     * (get口座コード)<BR>
     * 外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞCSV.口座コードを返却する。<BR>
     * 行番号に対応する明細行の口座コードを取得する。<BR>
     * <BR>
     * this.get項目値()にて口座コードを取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞCSV.口座コード)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号を指定する。<BR>
     * @@return String
     * @@roseuid 47BCFD47030D
     */
    public String getAccountCode(int l_intLineNo)
    {
        final String STR_METHOD_NAME = "getAccountCode(int)";
        log.entering(STR_METHOD_NAME);

        //カラム：　@getカラムモデル(外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞCSV.口座コード)の戻り値。
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(ACCOUNT_CODE_LABEL);

        //this.get項目値()にて口座コードを取得し返却する。
        String l_strAccountCode = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

        log.exiting(STR_METHOD_NAME);
        return l_strAccountCode;
    }

    /**
     * (get適用期間From)<BR>
     * 外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞCSV.適用期間Fromを返却する。<BR>
     * 行番号に対応する明細行の適用期間Fromを取得する。<BR>
     * <BR>
     * this.get項目値()にて適用期間Fromを取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞCSV.適用期間From)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号を指定する。<BR>
     * @@return Date
     * @@roseuid 47BCFD4703A9
     */
    public Date getAppliStartDate(int l_intLineNo)
    {
        final String STR_METHOD_NAME = "getAppliStartDate(int)";
        log.entering(STR_METHOD_NAME);

        //カラム：　@getカラムモデル(外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞCSV.適用期間From)の戻り値。
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(APPLI_START_DATE_LABEL);

        //this.get項目値()にて適用期間Fromを取得し返却する。
        Date l_datAppliStartDate = (Date)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

        log.exiting(STR_METHOD_NAME);
        return l_datAppliStartDate;
    }

    /**
     * (get適用期間To)<BR>
     * 外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞCSV.適用期間Toを返却する。<BR>
     * 行番号に対応する明細行の適用期間Toを取得する。<BR>
     * <BR>
     * this.get項目値()にて適用期間Toを取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号：　@行番号<BR>
     * カラム：　@getカラムモデル(外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞCSV.適用期間To)の戻り値。<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号を指定する。<BR>
     * @@return Date
     * @@roseuid 47BCFD48005D
     */
    public Date getAppliEndDate(int l_intLineNo)
    {
        final String STR_METHOD_NAME = "getAppliEndDate(int)";
        log.entering(STR_METHOD_NAME);

        //カラム：　@getカラムモデル(外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞCSV.適用期間To)の戻り値。
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(APPLI_END_DATE_LABEL);

        //this.get項目値()にて適用期間Toを取得し返却する。
        Date l_datAppliEndDate = (Date)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

        log.exiting(STR_METHOD_NAME);
        return l_datAppliEndDate;
    }

    /**
     * (validate明細行)<BR>
     * アップロード区分により明細行のチェックを行う。<BR>
     * <BR>
     * 1)　@this.isアップロード新規登録()　@＝　@true　@の場合、 以下の処理を実行する。<BR>
     * 　@1-1)　@this.get通番(行番号)にて取得した、指定行番号の通番の入力項目値をチェックする。<BR>
     * 　@　@1-1-1) 通番<0の場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_03059<BR>
     * 　@　@1-1-2) 桁数>18桁の場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_03054<BR>
     * <BR>
     * 　@1-2)　@this.getID(行番号)にて取得した、指定行番号のIDの入力項目値をチェックする。<BR>
     * 　@　@1-2-1)　@ID==nullの場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_02776<BR>
     * 　@　@1-2-2)　@ID!=nullであり、かつ半角英数字以外が格納されている場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_03057<BR>
     * 　@　@1-2-3)　@ID!=nullであり、かつ桁数!=8桁の場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_00954<BR>
     * <BR>
     * 　@1-3)　@this.getパスワード(行番号)にて取得した、<BR>
     * 　@　@　@指定行番号のパスワードの入力項目値をチェックする。<BR>
     * 　@　@1-3-1)　@パスワード==nullの場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_03058<BR>
     * 　@　@1-3-2)　@パスワード!=nullであり、かつ半角英数字以外が格納されている場合、<BR>
     * 　@　@　@　@例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_01916<BR>
     * 　@　@1-3-3)　@パスワード!=nullであり、かつ桁数!=8桁の場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_01915<BR>
     * <BR>
     * 　@1-4)　@this.getステータス(行番号)にて取得した、<BR>
     * 　@　@　@指定行番号のステータスの入力項目値をチェックする。<BR>
     * 　@　@1-4-1)　@ステータス==nullの場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_00889<BR>
     * 　@　@1-4-2)　@ステータス!=nullであり、かつ以下の値以外の場合、例外をスローする。<BR>
     * 　@　@　@・0：使用中<BR>
     * 　@　@　@・1：無効（削除）<BR>
     * 　@　@　@・9：未使用<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_00890<BR>
     * <BR>
     * <BR>
     * 2)　@this.isアップロード新規登録()　@＝　@false　@の場合、以下の処理を実行する。<BR>
     * 　@2-1)　@this.get通番(行番号)にて取得した、指定行番号の通番の入力項目値をチェックする。<BR>
     * 　@　@2-1-1) 通番<0の場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_03059<BR>
     * 　@　@2-1-2) 桁数>18桁の場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_03054<BR>
     * <BR>
     * 　@2-2)　@this.getID(行番号)にて取得した、指定行番号のIDの入力項目値をチェックする。<BR>
     * 　@　@2-2-1)　@ID==nullの場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_02776<BR>
     * 　@　@2-2-2)　@ID!=nullであり、かつ半角英数字以外が格納されている場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_03057<BR>
     * 　@　@2-2-3)　@ID!=nullであり、かつ桁数!=8桁の場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_00954<BR>
     * <BR>
     * 　@2-3)　@this.getステータス(行番号)にて取得した、<BR>
     * 　@　@　@指定行番号のステータスの入力項目値をチェックする。<BR>
     * 　@　@2-3-1)　@ステータス==nullの場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_00889<BR>
     * 　@　@2-3-2)　@ステータス!=nullであり、かつ以下の値以外の場合、例外をスローする。<BR>
     * 　@　@　@・0：使用中<BR>
     * 　@　@　@・1：無効（削除）<BR>
     * 　@　@　@・9：未使用<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_00890<BR>
     * <BR>
     * 　@2-4)　@this.get部店(行番号)にて取得した、<BR>
     * 　@　@　@指定行番号の部店コードの入力項目値をチェックする。<BR>
     * 　@　@2-4-1)　@部店コード!=nullであり、かつ半角数字以外が格納されている場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_01729<BR>
     * 　@　@2-4-2)　@部店コード!=nullであり、かつ桁数!=3桁の場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_00834<BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号を指定する。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47C290AC023E
     */
    public void validateDetailLine(int l_intLineNo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateDetailLine(int)";
        log.entering(STR_METHOD_NAME);

        //1)　@this.isアップロード新規登録()　@＝　@true　@の場合、 以下の処理を実行する。
        if (this.isUploadNewRegist())
        {
            //1-1)　@this.get通番(行番号)にて取得した、指定行番号の通番の入力項目値をチェックする。
            long l_lngSequenceNumber = this.getSequenceNumber(l_intLineNo);

            //1-1-1)通番<0の場合、例外をスローする。
            if (l_lngSequenceNumber < 0)
            {
                log.debug("通番が0より小さい値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03059,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "通番が0より小さい値です。");
            }
            //1-1-2) 桁数>18桁の場合、例外をスローする。
            if ((l_lngSequenceNumber + "").length() > 18)
            {
                log.debug("通番のサイズが不正です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03054,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "通番のサイズが不正です。");
            }

            //1-2) this.getID(行番号)にて取得した、指定行番号のIDの入力項目値をチェックする。
            String l_strId = this.getId(l_intLineNo);

            //1-2-1) ID==nullの場合、例外をスローする。
            if (l_strId == null)
            {
                log.debug("IDがnull。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02776,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "IDがnull。");
            }
            //1-2-2) ID!=nullであり、かつ半角英数字以外が格納されている場合、例外をスローする。
            if (!WEB3StringTypeUtility.isLetterOrDigit(l_strId))
            {
                log.debug("IDが半角英数字以外の値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03057,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "IDが半角英数字以外の値です。");
            }

            //1-2-3) ID!=nullであり、かつ桁数!=8桁の場合、例外をスローする。
            if (l_strId.length() != 8)
            {
                log.debug("IDのサイズが不正です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00954,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "IDのサイズが不正です。");
            }

            //1-3) this.getパスワード(行番号)にて取得した、指定行番号のパスワードの入力項目値をチェックする。
            String l_strPassword = this.getPassword(l_intLineNo);

            //1-3-1) パスワード==nullの場合、例外をスローする。
            if (l_strPassword == null)
            {
                log.debug("パスワードが未入力です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03058,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "パスワードが未入力です。");
            }

            //1-3-2) パスワード!=nullであり、かつ半角英数字以外が格納されている場合、例外をスローする。
            if (!WEB3StringTypeUtility.isLetterOrDigit(l_strPassword))
            {
                log.debug("パスワード（文字列）の文字種が不正です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01916,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "パスワード（文字列）の文字種が不正です。");
            }

            //1-3-3) パスワード!=nullであり、かつ桁数!=8桁の場合、例外をスローする。
            if (l_strPassword.length() != 8)
            {
                log.debug("パスワード（文字列）の長さが不正です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01915,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "パスワード（文字列）の長さが不正です。");
            }

            //1-4) this.getステータス(行番号)にて取得した、指定行番号のステータスの入力項目値をチェックする。
            String l_strStatus = this.getStatus(l_intLineNo);

            //1-4-1) ステータス==nullの場合、例外をスローする。
            if (l_strStatus == null)
            {
                log.debug("ステータスが未指定です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00889,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "ステータスが未指定です。");
            }

            //1-4-2) ステータス!=nullであり、かつ以下の値以外の場合、例外をスローする。
            //・0：使用中
            //・1：無効（削除）
            //・9：未使用
            if (!(WEB3OtherOrgStatusDef.USING.equals(l_strStatus)
                || WEB3OtherOrgStatusDef.INVALIDITY.equals(l_strStatus)
                || WEB3OtherOrgStatusDef.DEFAULT.equals(l_strStatus)))
            {
                log.debug("ステータスが存在しないコード値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00890,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "ステータスが存在しないコード値です。");
            }
        }
        else
        {
            //2) this.isアップロード新規登録()　@＝　@false　@の場合、以下の処理を実行する。
            //2-1)　@this.get通番(行番号)にて取得した、指定行番号の通番の入力項目値をチェックする。
            long l_lngSequenceNumber = this.getSequenceNumber(l_intLineNo);

            //2-1-1)通番<0の場合、例外をスローする。
            if (l_lngSequenceNumber < 0)
            {
                log.debug("通番が0より小さい値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03059,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "通番が0より小さい値です。");
            }

            //2-1-2) 桁数>18桁の場合、例外をスローする。
            if ((l_lngSequenceNumber + "").length() > 18)
            {
                log.debug("通番のサイズが不正です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03054,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "通番のサイズが不正です。");
            }

            //2-2) this.getID(行番号)にて取得した、指定行番号のIDの入力項目値をチェックする。
            String l_strId = this.getId(l_intLineNo);

            //2-2-1) ID==nullの場合、例外をスローする。
            if (l_strId == null)
            {
                log.debug("IDがnull。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02776,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "IDがnull。");
            }

            //2-2-2) ID!=nullであり、かつ半角英数字以外が格納されている場合、例外をスローする。
            if (!WEB3StringTypeUtility.isLetterOrDigit(l_strId))
            {
                log.debug("IDが半角英数字以外の値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03057,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "IDが半角英数字以外の値です。");
            }

            //2-2-3) ID!=nullであり、かつ桁数!=8桁の場合、例外をスローする。
            if (l_strId.length() != 8)
            {
                log.debug("IDのサイズが不正です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00954,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "IDのサイズが不正です。");
            }

            //2-3) this.getステータス(行番号)にて取得した、指定行番号のステータスの入力項目値をチェックする。
            String l_strStatus = this.getStatus(l_intLineNo);

            //2-3-1) ステータス==nullの場合、例外をスローする。
            if (l_strStatus == null)
            {
                log.debug("ステータスが未指定です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00889,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "ステータスが未指定です。");
            }

            //2-3-2) ステータス!=nullであり、かつ以下の値以外の場合、例外をスローする。
            //・0：使用中
            //・1：無効（削除）
            //・9：未使用
            if (!(WEB3OtherOrgStatusDef.USING.equals(l_strStatus)
                || WEB3OtherOrgStatusDef.INVALIDITY.equals(l_strStatus)
                || WEB3OtherOrgStatusDef.DEFAULT.equals(l_strStatus)))
            {
                log.debug("ステータスが存在しないコード値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00890,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "ステータスが存在しないコード値です。");
            }

            //2-4) this.get部店(行番号)にて取得した、指定行番号の部店コードの入力項目値をチェックする。
            String l_strBranchCode = this.getBranchCode(l_intLineNo);

            //2-4-1) 部店コード!=nullであり、かつ半角数字以外が格納されている場合、例外をスローする。
            if (l_strBranchCode != null
                && !WEB3StringTypeUtility.isDigit(l_strBranchCode))
            {
                log.debug("部店コードが数値以外の値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01729,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "部店コードが数値以外の値です。");
            }

            //2-4-2) 部店コード!=nullであり、かつ桁数!=3桁の場合、例外をスローする。
            if (l_strBranchCode != null
                && (l_strBranchCode.length() != 3))
            {
                log.debug("部店コードのサイズが不正です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00834,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "部店コードのサイズが不正です。");
            }
        }
    }

    /**
     * (validateアップロードファ@イル内重複)<BR>
     * 1)　@重複通番が追加されていないかチェックを行う。<BR>
     * <BR>
     * 　@1-1)　@get通番(行番号)にて、指定行番号の通番を取得する。<BR>
     * 　@1-2)　@取得した通番と指定行番号より前の明細行の通番を比較する。<BR>
     * 　@1-3)　@同じ値の行（取得した通番 == 指定行番号より前の明細行の通番）が存在する場合は、<BR>
     * 　@　@通番が重複して登録されていると判定し、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_03051<BR>
     * <BR>
     * <BR>
     * 2)　@重複IDが追加されていないかチェックを行う。<BR>
     * <BR>
     * 　@2-1)　@getID(行番号)にて、指定行番号のIDを取得する。<BR>
     * 　@2-2)　@取得したIDと指定行番号より前の明細行のIDを比較する。<BR>
     * 　@2-3)　@同じ値の行（取得したID == 指定行番号より前の明細行のID）が存在する場合は、<BR>
     * 　@　@IDが重複して登録されていると判定し、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_03052<BR>
     * <BR>
     * <BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号を指定する。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47C26B36031F
     */
    public void validateUploadFileInnerRepeat(int l_intLineNo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateUploadFileInnerRepeat(int)";
        log.entering(STR_METHOD_NAME);

        //1) 重複通番が追加されていないかチェックを行う。
        //1-1) get通番(行番号)にて、指定行番号の通番を取得する。
        long l_lngSequenceNumber = this.getSequenceNumber(l_intLineNo);

        for (int i = 0; i < l_intLineNo; i++)
        {
            //1-2)　@取得した通番と指定行番号より前の明細行の通番を比較する。
            //1-3)　@同じ値の行（取得した通番 == 指定行番号より前の明細行の通番）が存在する場合は、
            //通番が重複して登録されていると判定し、例外をスローする。
            long l_lngSequenceNumberPrevious = this.getSequenceNumber(i);
            if (l_lngSequenceNumberPrevious == l_lngSequenceNumber)
            {
                log.debug("重複通番チェックエラー（同じ値の行が存在する）。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03051,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "重複通番チェックエラー（同じ値の行が存在する）。");
            }
        }

        //2) 重複IDが追加されていないかチェックを行う。
        //2-1) getID(行番号)にて、指定行番号のIDを取得する。
        String l_strId = this.getId(l_intLineNo);

        for (int i = 0; i < l_intLineNo; i++)
        {
            //2-2) 取得したIDと指定行番号より前の明細行のIDを比較する。
            //2-3) 同じ値の行（取得したID == 指定行番号より前の明細行のID）が存在する場合は、
            //IDが重複して登録されていると判定し、例外をスローする。
            String l_strIdPrevious = this.getId(i);
            if (l_strIdPrevious.equals(l_strId))
            {
                log.debug("重複IDチェックエラー（同じ値の行が存在する）。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03052,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "重複IDチェックエラー（同じ値の行が存在する）。");
            }
        }
    }

    /**
     * (validateレコード整合性)<BR>
     * アップロード区分により明細行と該当レコードとの整合性チェックを行う。<BR>
     * <BR>
     * <BR>
     * 　@シーケンス図「（サービス利用）外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ・validateアップロードファ@イル」内の<BR>
     * 　@　@　@validateレコード整合性()コール参照<BR>
     * <BR>
     * ========================================================<BR>
     * シーケンス図 「（サービス利用）外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ・validateアップロードファ@イル」<BR>
     * 　@　@　@　@　@内のvalidateレコード整合性()コール: <BR>
     * 　@　@　@　@　@1.12.8.4.1.1 get外部連携情報()の戻り値 != null　@の場合、例外をスローする<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_03027<BR>
     * ==========================================================<BR>
     * <BR>
     * ========================================================<BR>
     * シーケンス図 「（サービス利用）外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ・validateアップロードファ@イル」<BR>
     * 　@　@　@　@　@内のvalidateレコード整合性()コール: <BR>
     * 　@　@　@　@　@1.12.8.5.1.1 get外部連携情報()の戻り値 == null　@の場合、例外をスローする<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_03019<BR>
     * ==========================================================<BR>
     * <BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号を指定する。<BR>
     * @@param l_strSrvDiv - (サービス区分)<BR>
     * サービス区分<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47BE645601D5
     */
    public void validateRecodeMatch(int l_intLineNo, String l_strSrvDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateRecodeMatch(int, String)";
        log.entering(STR_METHOD_NAME);

        //get通番(int)
        //行番号：　@引数.行番号
        long l_lngSequenceNumber = this.getSequenceNumber(l_intLineNo);

        //get外部連携情報(long, String, boolean)
        //通番：  get通番()の戻り値
        //サービス区分：  引数.サービス区分
        //is行ロック：  false
        WEB3SrvRegiOtherOrgService l_srvRegiOtherOrgService =
            (WEB3SrvRegiOtherOrgService)Services.getService(WEB3SrvRegiOtherOrgService.class);
        WEB3SrvRegiOtherOrgInfoAdmin l_srvRegiOtherOrgInfoAdmin =
            l_srvRegiOtherOrgService.getOtherOrgInfo(l_lngSequenceNumber, l_strSrvDiv, false);

        //isアップロード新規登録( )
        if (this.isUploadNewRegist())
        {
            // get外部連携情報()の戻り値 != null　@の場合、例外をスローする
            if (l_srvRegiOtherOrgInfoAdmin != null)
            {
                log.debug("外部連携情報を取得するはずがない。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03027,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "外部連携情報を取得するはずがない。");
            }
        }
        else
        {
            //get外部連携情報()の戻り値 == null　@の場合、例外をスローする
            if (l_srvRegiOtherOrgInfoAdmin == null)
            {
                log.debug("外部連携情報を取得できません。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03019,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "外部連携情報を取得できません。");
            }

            //getステータス(int)
            //行番号：　@引数.行番号
            String l_strStatus = this.getStatus(l_intLineNo);

            //isステータス変更可能(String)
            //ステータス：  getステータス()の戻り値
            l_srvRegiOtherOrgInfoAdmin.isStatusChangeable(l_strStatus);
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
