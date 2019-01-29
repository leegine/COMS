head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.11.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3PvInfoRegistTargetAccountUploadCsv.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 登録対象顧客アップロードCSV(WEB3PvInfoRegistTargetAccountUploadCsv.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 王亞洲(中訊) 新規作成
Revision History : 2007/06/27  謝旋 (中訊) 仕様変更モデルNo.078,No.080
*/
package webbroker3.pvinfo;

import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.gentrade.WEB3GentradeCsvDataModel;
import webbroker3.gentrade.WEB3GentradeCsvUploadDataModel;
import webbroker3.gentrade.data.AdministratorUploadTempParams;
import webbroker3.gentrade.data.AdministratorUploadTempRow;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (登録対象顧客アップロードCSV)<BR>
 * 登録対象顧客アップロードCSV<BR>
 * @@author 王亞洲
 * @@version 1.00
 */
public class WEB3PvInfoRegistTargetAccountUploadCsv extends WEB3GentradeCsvUploadDataModel
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3PvInfoRegistTargetAccountUploadCsv.class);

    /**
     * (部店コードラベル)<BR>
     * 部店コードラベル<BR>
     */
    public static final String BRANCH_CODE_LABEL = "部店コード";

    /**
     * (顧客コードラベル)<BR>
     * 顧客コードラベル<BR>
     */
    public static final String ACCOUNT_CODE_LABEL = "顧客コード";

    /**
     * (アップロードファ@イルID)<BR>
     * 登録対象顧客アップロード<BR>
     */
    public String uploadFileId = "登録対象顧客アップロード";

    /**
     * (登録対象顧客アップロードCSV)<BR>
     * コンストラクタ <BR>
     * <BR>
     * －引数のアップロードIDをプロパティにセットする。 <BR>
     * @@param l_lngUploadId - (アップロードID)<BR>
     * アップロードID<BR>
     * @@return webbroker3.pvinfo.WEB3PvInfoRegistTargetAccountUploadCsv
     * @@roseuid 4160A87100A7
     */
    public WEB3PvInfoRegistTargetAccountUploadCsv(long l_lngUploadId)
    {
        //引数のアップロードIDをプロパティにセットする。
        this.administratorUploadId = l_lngUploadId;
        //this.createカラムヘッダ()をコールする。 
        createColumnHeader();
    }

    /**
     * (登録対象顧客アップロードCSV)<BR>
     * コンストラクタ <BR>
     * <BR>
     * －this.createカラムヘッダ()をコールする。 <BR>
     * @@return webbroker3.pvinfo.WEB3PvInfoRegistTargetAccountUploadCsv
     * @@roseuid 4160A84A0069
     */
    public WEB3PvInfoRegistTargetAccountUploadCsv()
    {
        //this.createカラムヘッダ()をコールする。 
        createColumnHeader();
    }

    /**
     * (getアップロードファ@イルID)<BR>
     * 登録対象顧客アップロードCSV.アップロードファ@イルＩＤを返却する。 <BR>
     * <BR>
     * ※（管理者共通）アップロードテーブル.アップロードファ@イルＩＤに格納する文字列<BR>
     * @@return String
     * @@roseuid 4160A80F03A5
     */
    public String getUploadFileId()
    {
        //登録対象顧客アップロードCSV.アップロードファ@イルＩＤを返却する。
        return this.uploadFileId;
    }

    /**
     * (get銘柄タイプ)<BR>
     * ProductTypeEnum.その他を返却する。<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum
     * @@roseuid 4160B03401D0
     */
    public ProductTypeEnum getProductType()
    {
        //ProductTypeEnum.その他を返却する。
        return ProductTypeEnum.OTHER;
    }

    /**
     * (createカラムヘッダ)<BR>
     * 以下の通りCSVカラムモデルの配列を生成し、setカラムヘッダ()にて<BR>
     * インスタンスにセットする。 <BR>
     * 
     * [カラムヘッダ配列] <BR>
     * <BR>
     * －　@index = 0 <BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@登録対象顧客アップロードCSV.部店コードラベル <BR>
     * 　@カラム番号： 0 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 1 <BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@登録対象顧客アップロードCSV.顧客コードラベル <BR>
     * 　@カラム番号： 1 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * @@roseuid 4160A8C50105
     */
    protected void createColumnHeader()
    {
        final String STR_METHOD_NAME = " createColumnHeader()";
        log.entering(STR_METHOD_NAME);
        
        //[カラムヘッダ配列]
        WEB3GentradeCsvColumnModel[] columnHeader = new WEB3GentradeCsvColumnModel[2];
        columnHeader[0] = new WEB3GentradeCsvColumnModel(BRANCH_CODE_LABEL, 0, WEB3GentradeCsvColumnModel.STRINGTYPE, null);
        columnHeader[1] = new WEB3GentradeCsvColumnModel(ACCOUNT_CODE_LABEL, 1, WEB3GentradeCsvColumnModel.STRINGTYPE, null);
        setColumnHeader(columnHeader);
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get顧客コード)<BR>
     * 行番号に対応する明細行の顧客コードを取得する。 <BR>
     * <BR>
     * this.get項目値()にて顧客コードを取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@行番号 <BR>
     * カラム：　@getカラムモデル(登録対象顧客アップロードCSV.顧客コードラベル)の<BR>
     * 戻り値。 <BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 4160A9EE0114
     */
    public String getAccountCode(int l_intLineNo)
    {
        final String STR_METHOD_NAME = " getAccountCode(int)";
        log.entering(STR_METHOD_NAME);

        //this.get項目値()にて顧客コードを取得し返却する。
        WEB3GentradeCsvColumnModel l_columnModel = this.getColumnModel(ACCOUNT_CODE_LABEL);
        String l_strValue = (String)this.getValue(l_intLineNo, l_columnModel);
        
        log.exiting(STR_METHOD_NAME);
        return l_strValue;
    }

    /**
     * (get部店コード)<BR>
     * 行番号に対応する明細行の部店コードを取得する。 <BR>
     * <BR>
     * this.get項目値()にて部店コードを取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@行番号 <BR>
     * カラム：　@getカラムモデル(登録対象顧客アップロードCSV.部店コードラベル)の<BR>
     * 戻り値。 <BR>
     * @@param l_intLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 4160A9EE0134
     */
    public String getBranchCode(int l_intLineNo)
    {
        final String STR_METHOD_NAME = " getBranchCode(int)";
        log.entering(STR_METHOD_NAME);

        //行番号に対応する明細行の部店コードを取得する
        WEB3GentradeCsvColumnModel l_columnModel = this.getColumnModel(BRANCH_CODE_LABEL);
        String l_strValue = (String)this.getValue(l_intLineNo, l_columnModel);
        
        log.exiting(STR_METHOD_NAME);

        return l_strValue;
    }

    /**
     * (validate明細行)<BR>
     * 明細行のチェックを行う。<BR>
     * <BR>
     * １）　@部店コードのチェック<BR>
     * 　@get部店コード()にて、指定行番号のデータを取得しチェックを行う。<BR>
     * <BR>
     * 　@[get部店コード()に指定する引数]<BR>
     * 　@行番号：　@行番号<BR>
     * <BR>
     * 　@１－１）　@半角数字以外が含まれる場合は、例外をスローする。<BR>         
     * 　@１－２）　@文字数が3byteでない場合は、例外をスローする。<BR>
     *        
     * <BR>
     * ２）　@顧客コードのチェック<BR>
     * 　@get顧客コード()にて、指定行番号のデータを取得しチェックを行う。<BR>
     * <BR>
     * 　@[get顧客コード()に指定する引数]<BR>
     * 　@行番号：　@行番号<BR>
     * <BR>
     * 　@２－１）　@半角数字以外が含まれる場合は、例外をスローする。<BR>         
     * 　@２－２）　@文字数が6byteでない場合は、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01058<BR>
     * <BR>
     * ※スローする例外は、全て「顧客ファ@イル内容エラー」の例外。<BR>
     * @@param l_lngLineNo - (行番号)<BR>
     * 行番号<BR>
     * @@roseuid 4160AA4103B4
     */
    public void validateDetailsLine(long l_lngLineNo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateDetailsLine(long)";
        log.entering(STR_METHOD_NAME);

        log.debug("行番号: " + l_lngLineNo);
        
        //１）　@部店コードのチェック                
        String l_strBranchCode = (String)this.getBranchCode((int) l_lngLineNo);
        boolean l_blnDigitBranchCode = WEB3StringTypeUtility.isDigit(l_strBranchCode);
        int l_intBranchCode = WEB3StringTypeUtility.getByteLength(l_strBranchCode);
        
        //１－１）　@半角数字以外が含まれる場合は、例外をスローする。
        //１－２）　@文字数が3byteでない場合は、例外をスローする。        
        if(!l_blnDigitBranchCode || l_intBranchCode != 3)
        {
            log.error(getClass().getName() + "." + STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_01058.getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01058,
                getClass().getName() + "." + STR_METHOD_NAME,
                WEB3ErrorCatalog.BUSINESS_ERROR_01058.getErrorMessage());                
        }
        
        //２）　@顧客コードのチェック
        String l_strAccountCode = (String)this.getAccountCode((int) l_lngLineNo);
        boolean l_blnDigitAccountCode = WEB3StringTypeUtility.isDigit(l_strAccountCode);
        int l_intAccountCode = WEB3StringTypeUtility.getByteLength(l_strAccountCode);
        
        //２－１）　@半角数字以外が含まれる場合は、例外をスローする。
        //２－２）　@文字数が6byteでない場合は、例外をスローする。        
        if(!l_blnDigitAccountCode || l_intAccountCode != 6)
        {
            log.error(getClass().getName() + "." + STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_01058.getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01058,
                getClass().getName() + "." + STR_METHOD_NAME,
                WEB3ErrorCatalog.BUSINESS_ERROR_01058.getErrorMessage());                
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (setDataFromアップロードTemp)<BR>
     * 「（管理者共通）アップロードテンポラリ」テーブルの<BR>
     * 　@　@指定アップロードＩＤのデータをプロパティにをセットする。<BR>
     * <BR>
     * １）　@テンポラリテーブル読込 <BR>
     * 　@「（管理者共通）アップロードテンポラリ」テーブルを以下で検索し、 <BR>
     * 行番号順に（管理者共通）アップロードテンポラリParams[]を取得する。 <BR>
     * <BR>
     * 　@[検索条件] <BR>
     * 　@アップロードＩＤ = 引数.アップロードＩＤ <BR>
     * <BR>
     * 　@[ソート条件] <BR>
     * 　@CSV行 <BR>
     * <BR>
     * 　@※（管理者共通）アップロードテンポラリParams <BR>
     * 　@DDLにて自動生成する行オブジェクト <BR>
     * <BR>
     * ２）　@キーヘッダ行のセット <BR>
     * 　@this.setキーヘッダ()にてキーヘッダをセットする。 <BR>
     * 　@※１）で読み込んだ行オブジェクト配列のindex = 0の<BR>
     * 　@　@　@　@要素をキーヘッダ行と判定する <BR>
     * <BR>
     * 　@[setキーヘッダ()に指定する引数] <BR>
     * 　@キーヘッダ：<BR>
     * 　@　@　@（管理者共通）アップロードテンポラリParams[0].split(区切り文字) <BR>
     * <BR>
     * ３）　@明細行のセット <BR>
     * 　@（管理者共通）アップロードテンポラリParams[]の2番目（index = 1）<BR>
     * 　@　@　@　@以降の要素について、this.add明細行()にて明細行に追加する。　@ <BR>
     * <BR>
     * 　@[add明細行()に指定する引数] <BR>
     * 　@明細行文字列：<BR>
     * 　@　@　@（管理者共通）アップロードテンポラリParams[index].CSV行 <BR>
     * <BR>
     * ４）　@アップロードＩＤセット <BR>
     * 　@this.アップロードＩＤに引数.アップロードＩＤをセットする。<BR>
     * <BR>
     * @@param l_lngUploadId - アップロードＩＤ
     * @@throws WEB3SystemLayerException
     */
    public void setDataFromUploadTemp(long l_lngUploadId) throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = " setDataFromUploadTemp(long)";
        log.entering(STR_METHOD_NAME);

        List l_lisRecords;
        Long l_upID = new Long(l_lngUploadId);

        try
        {
            //１）　@テンポラリテーブル読込
            //　@「（管理者共通）アップロードテンポラリ」テーブルを以下で検索し、
            //行番号順に（管理者共通）アップロードテンポラリParams[]を取得する。
            //　@[検索条件]
            //　@アップロードＩＤ = 引数.アップロードＩＤ
            //　@[ソート条件]
            //　@CSV行
            String l_strQueryString = " administrator_upload_id = ?";

            Object[] l_objWhereValue = {l_upID};

            String l_strSortCondition = " csv_line_value ";

            QueryProcessor l_qp = Processors.getDefaultProcessor();
            l_lisRecords = l_qp.doFindAllQuery(
                AdministratorUploadTempRow.TYPE,
                l_strQueryString,
                l_strSortCondition,
                null,
                l_objWhereValue);
        }
        catch (DataException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        if (l_lisRecords.isEmpty())
        {
            log.debug("（管理者共通）アップロードテンポラリテーブル検索： 件数 < 1");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                "（管理者共通）アップロードテンポラリテーブル検索： 件数 < 1");
        }

        AdministratorUploadTempParams l_administratorUploadTempParams;

        //２）　@キーヘッダ行のセット
        //　@this.setキーヘッダ()にてキーヘッダをセットする。
        //　@※１）で読み込んだ行オブジェクト配列のindex = 0の要素をキーヘッダ行と判定する
        //　@[setキーヘッダ()に指定する引数]
        //　@キーヘッダ：　@（管理者共通）アップロードテンポラリParams[0].split(区切り文字)
        l_administratorUploadTempParams =
            new AdministratorUploadTempParams((AdministratorUploadTempRow)l_lisRecords.get(0));

        String[] l_strCsvLineValues =
            l_administratorUploadTempParams.getCsvLineValue().split(WEB3GentradeCsvDataModel.regex);
        this.setKeyHeader(l_strCsvLineValues);

        //３）　@明細行のセット
        //　@（管理者共通）アップロードテンポラリParams[]の2番目（index = 1)
        //    以降の要素について、this.add明細行()にて明細行に追加する。
        //　@[add明細行()に指定する引数]
        //　@明細行文字列：　@（管理者共通）アップロードテンポラリParams[index].CSV行
        int l_intSize = l_lisRecords.size();
        for (int i = 1; i < l_intSize; i++)
        {
            l_administratorUploadTempParams =
                (AdministratorUploadTempParams)l_lisRecords.get(i);
            this.addRow(l_administratorUploadTempParams.getCsvLineValue());
        }

        //４）　@アップロードＩＤセット
        //　@this.アップロードＩＤに引数.アップロードＩＤをセットする。
        this.administratorUploadId = l_lngUploadId;

        log.exiting(STR_METHOD_NAME);
    }
}
@
