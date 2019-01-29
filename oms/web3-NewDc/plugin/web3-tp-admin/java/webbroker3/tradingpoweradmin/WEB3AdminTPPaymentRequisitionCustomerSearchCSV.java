head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.12.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPPaymentRequisitionCustomerSearchCSV.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入金請求顧客検索CSV(WEB3AdminTPPaymentRequisitionCustomerSearchCSV.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/06 安陽(中訊) 新規作成 モデルNo.027
Revision History : 2008/10/10 安陽(中訊) 仕様変更 モデルNo.028 029
*/

package webbroker3.tradingpoweradmin;

import webbroker3.common.define.WEB3AccountAttributeDef;
import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.gentrade.WEB3GentradeCsvDataModel;
import webbroker3.tradingpoweradmin.define.WEB3AdminTPAccountAttributeDef;
import webbroker3.util.WEB3LogUtility;


/**
 * (入金請求顧客検索CSV)<BR>
 * <BR>
 * @@author 安陽
 * @@version 1.0
 */
public class WEB3AdminTPPaymentRequisitionCustomerSearchCSV extends WEB3GentradeCsvDataModel
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTPPaymentRequisitionCustomerSearchCSV.class);

    /**
     * (部店コードラベル)<BR>
     * 定数定義プロパティ　@"部店コード"<BR>
     */
    public String BRANCH_CODE_LABEL = "部店コード";

    /**
     * (顧客コードラベル)<BR>
     * 定数定義プロパティ　@"顧客コード"<BR>
     */
    public String ACCOUNT_CODE_LABEL = "顧客コード";

    /**
     * (顧客名ラベル)<BR>
     * 定数定義プロパティ　@"顧客名"<BR>
     */
    public String FAMILY_NAME_LABEL = "顧客名";

    /**
     * (扱者コードラベル)<BR>
     * 定数定義プロパティ　@"扱者コード"<BR>
     */
    public String TRADER_CODE_LABEL = "扱者コード";

    /**
     * (属性ラベル)<BR>
     * 定数定義プロパティ　@"属性"<BR>
     */
    public String ATTRIBUTE_LABEL = "属性";

    /**
     * (立替金ラベル)<BR>
     * 定数定義プロパティ　@"立替金"<BR>
     */
    public String DEBIT_AMOUNT_LABEL = "立替金";

    /**
     * (特別立替金ラベル)<BR>
     * 定数定義プロパティ　@"特別立替金"<BR>
     */
    public String SPECIAL_DEBIT_AMOUNT_LABEL = "特別立替金";

    /**
     * (必要入金額ラベル)<BR>
     * 定数定義プロパティ　@"必要入金額"<BR>
     */
    public String REQUIRED_PAY_AMT_LABEL = "必要入金額";

    /**
     * (第一水準追証金額ラベル)<BR>
     * 定数定義プロパティ　@"30％割れ追証金額"<BR>
     */
    public String FIRST_DEPOSIT_AMOUNT_LABEL = "30％割れ追証金額";

    /**
     * (第一水準追証経過日数ラベル)<BR>
     * 定数定義プロパティ　@"30％割れ追証経過日数"<BR>
     */
    public String FIRST_DEPOSIT_PASS_DAY_LABEL = "30％割れ追証経過日数";

    /**
     * (第二水準追証請求（1）ラベル)<BR>
     * 定数定義プロパティ　@"20％割れ追証請求（1）"<BR>
     */
    public String SECOND_DEPOSIT_1_LABEL = "20％割れ追証請求（1）";

    /**
     * (第二水準追証請求（2）ラベル)<BR>
     * 定数定義プロパティ　@"20％割れ追証請求（2）"<BR>
     */
    public String SECOND_DEPOSIT_2_LABEL = "20％割れ追証請求（2）";

    /**
     * (第二水準追証未入金ラベル)<BR>
     * 定数定義プロパティ　@"20％割れ追証未入金"<BR>
     */
    public String SECOND_DEPOSIT_NON_PAY_LABEL = "20％割れ追証未入金";

    /**
     * (入金請求顧客検索CSV)<BR>
     * コンストラクタ。<BR>
     * <BR>
     * インスタンスを生成し、ヘッダ情報をセットする。<BR>
     * 　@－super()にてインスタンスを生成する。<BR>
     * 　@－createカラムヘッダ()をコールし、カラムヘッダ情報を作成する。<BR>
     * @@roseuid 48D201D603BB
     */
    public WEB3AdminTPPaymentRequisitionCustomerSearchCSV()
    {
        super();
        createColumnHeader();
    }

    /**
     * (createカラムヘッダ)<BR>
     * カラムヘッダをセットする。<BR>
     * <BR>
     * 　@以下の通りCSVカラムモデルの配列を生成し、<BR>
     * 　@　@　@setカラムヘッダ()にてインスタンスにセットする。<BR>
     * <BR>
     * [カラムヘッダ配列]<BR>
     * <BR>
     * －　@index = 0<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@入金請求顧客検索CSV.部店コードラベル<BR>
     * 　@カラム番号： 0<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 1<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@入金請求顧客検索CSV.顧客コードラベル<BR>
     * 　@カラム番号： 1<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 2<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@入金請求顧客検索CSV.顧客名ラベル<BR>
     * 　@カラム番号： 2<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 3<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@入金請求顧客検索CSV.扱者コードラベル<BR>
     * 　@カラム番号： 3<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 4<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@入金請求顧客検索CSV.属性ラベル<BR>
     * 　@カラム番号： 4<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 5<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@入金請求顧客検索CSV.立替金ラベル<BR>
     * 　@カラム番号： 5<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 6<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@入金請求顧客検索CSV.特別立替金ラベル<BR>
     * 　@カラム番号： 6<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 7<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@入金請求顧客検索CSV.必要入金額ラベル<BR>
     * 　@カラム番号： 7<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 8<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@入金請求顧客検索CSV.第一水準追証金額ラベル<BR>
     * 　@カラム番号： 8<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 9<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@入金請求顧客検索CSV.第一水準追証経過日数ラベル<BR>
     * 　@カラム番号： 9<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 10<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@入金請求顧客検索CSV.第二水準追証請求(1)ラベル<BR>
     * 　@カラム番号： 10<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 11<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@入金請求顧客検索CSV.第二水準追証請求(2)ラベル<BR>
     * 　@カラム番号： 11<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 12<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@入金請求顧客検索CSV.第二水準追証未入金ラベル<BR>
     * 　@カラム番号： 12<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * @@roseuid 48D200EF0148
     */
    public void createColumnHeader()
    {
        final String STR_METHOD_NAME = "createColumnHeader()";
        log.entering(STR_METHOD_NAME);

        WEB3GentradeCsvColumnModel[] l_columnHeader = new WEB3GentradeCsvColumnModel[13];

        //部店コード
        l_columnHeader[0] = new WEB3GentradeCsvColumnModel(
            BRANCH_CODE_LABEL, 0, WEB3GentradeCsvColumnModel.STRINGTYPE, null);

        //顧客コード
        l_columnHeader[1] = new WEB3GentradeCsvColumnModel(
            ACCOUNT_CODE_LABEL, 1, WEB3GentradeCsvColumnModel.STRINGTYPE, null);

        //顧客名
        l_columnHeader[2] = new WEB3GentradeCsvColumnModel(
            FAMILY_NAME_LABEL, 2, WEB3GentradeCsvColumnModel.STRINGTYPE, null);

        //扱者コード
        l_columnHeader[3] = new WEB3GentradeCsvColumnModel(
            TRADER_CODE_LABEL, 3, WEB3GentradeCsvColumnModel.STRINGTYPE, null);

        //属性
        l_columnHeader[4] = new WEB3GentradeCsvColumnModel(
            ATTRIBUTE_LABEL, 4, WEB3GentradeCsvColumnModel.STRINGTYPE, null);

        //立替金
        l_columnHeader[5] = new WEB3GentradeCsvColumnModel(
            DEBIT_AMOUNT_LABEL, 5, WEB3GentradeCsvColumnModel.STRINGTYPE, null);

        //特別立替金
        l_columnHeader[6] = new WEB3GentradeCsvColumnModel(
            SPECIAL_DEBIT_AMOUNT_LABEL, 6, WEB3GentradeCsvColumnModel.STRINGTYPE, null);

        //必要入金額
        l_columnHeader[7] = new WEB3GentradeCsvColumnModel(
            REQUIRED_PAY_AMT_LABEL, 7, WEB3GentradeCsvColumnModel.STRINGTYPE, null);

        //第一水準追証金額
        l_columnHeader[8] = new WEB3GentradeCsvColumnModel(
            FIRST_DEPOSIT_AMOUNT_LABEL, 8, WEB3GentradeCsvColumnModel.STRINGTYPE, null);

        //第一水準追証経過日数
        l_columnHeader[9] = new WEB3GentradeCsvColumnModel(
            FIRST_DEPOSIT_PASS_DAY_LABEL, 9, WEB3GentradeCsvColumnModel.STRINGTYPE, null);

        //第二水準追証請求（1）
        l_columnHeader[10] = new WEB3GentradeCsvColumnModel(
            SECOND_DEPOSIT_1_LABEL, 10, WEB3GentradeCsvColumnModel.STRINGTYPE, null);

        //第二水準追証請求（2）
        l_columnHeader[11] = new WEB3GentradeCsvColumnModel(
            SECOND_DEPOSIT_2_LABEL, 11, WEB3GentradeCsvColumnModel.STRINGTYPE, null);

        //第二水準追証未入金
        l_columnHeader[12] = new WEB3GentradeCsvColumnModel(
            SECOND_DEPOSIT_NON_PAY_LABEL, 12, WEB3GentradeCsvColumnModel.STRINGTYPE, null);

        setColumnHeader(l_columnHeader);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set部店コード)<BR>
     * 「部店コード」をセットする。<BR>
     * <BR>
     * １）　@カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数]<BR>
     * 　@項目ラベル：　@入金請求顧客検索CSV.部店コードラベル<BR>
     * <BR>
     * ２）　@値セット<BR>
     * 　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     * 　@[set項目値()に指定する引数]<BR>
     * 　@行番号：　@（引数）行番号<BR>
     * 　@カラム：　@１）で取得したカラムモデル<BR>
     * 　@値：　@（引数）部店コード<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * @@roseuid 48D2187F03AE
     */
    public void setBranchCode(int l_intLineNumber, String l_strBranchCode)
    {
        final String STR_METHOD_NAME = "setBranchCode(int, String)";
        log.entering(STR_METHOD_NAME);

        //カラムモデル取得
        WEB3GentradeCsvColumnModel l_columnModel = getColumnModel(BRANCH_CODE_LABEL);

        //値セット
        setValue(l_intLineNumber, l_columnModel, l_strBranchCode);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set顧客コード)<BR>
     * 「顧客コード」をセットする。<BR>
     * <BR>
     * １）　@カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数]<BR>
     * 　@項目ラベル：　@入金請求顧客検索CSV.顧客コードラベル<BR>
     * <BR>
     * ２）　@値セット<BR>
     * 　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     * 　@[set項目値()に指定する引数]<BR>
     * 　@行番号：　@（引数）行番号<BR>
     * 　@カラム：　@１）で取得したカラムモデル<BR>
     * 　@値：　@（引数）顧客コードの下１桁を除いた６桁の値<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@param l_strAccountCode - (顧客コード)<BR>
     * @@roseuid 48D219E40262
     */
    public void setAccountCode(int l_intLineNumber, String l_strAccountCode)
    {
        final String STR_METHOD_NAME = "setAccountCode(int, String)";
        log.entering(STR_METHOD_NAME);

        //カラムモデル取得
        WEB3GentradeCsvColumnModel l_columnModel = getColumnModel(ACCOUNT_CODE_LABEL);

        //値セット
        setValue(l_intLineNumber, l_columnModel, l_strAccountCode.substring(0, 6));

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set顧客名)<BR>
     * 「顧客名」をセットする。<BR>
     * <BR>
     * １）　@カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数]<BR>
     * 　@項目ラベル：　@入金請求顧客検索CSV.顧客名ラベル<BR>
     * <BR>
     * ２）　@値セット<BR>
     * 　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     * 　@[set項目値()に指定する引数]<BR>
     * 　@行番号：　@（引数）行番号<BR>
     * 　@カラム：　@１）で取得したカラムモデル<BR>
     * 　@値：　@（引数）顧客名<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@param l_strFamilyName - (顧客名)<BR>
     * @@roseuid 48D219EC03AB
     */
    public void setFamilyName(int l_intLineNumber, String l_strFamilyName)
    {
        final String STR_METHOD_NAME = " setAccountName(int, String)";
        log.entering(STR_METHOD_NAME);

        //カラムモデル取得
        WEB3GentradeCsvColumnModel l_columnModel = getColumnModel(FAMILY_NAME_LABEL);

        //値セット
        setValue(l_intLineNumber, l_columnModel, l_strFamilyName);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set扱者コード)<BR>
     * 「扱者コード」をセットする。<BR>
     * <BR>
     * １）　@カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数]<BR>
     * 　@項目ラベル：　@入金請求顧客検索CSV.扱者コードラベル<BR>
     * <BR>
     * ２）　@値セット<BR>
     * 　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     * 　@[set項目値()に指定する引数]<BR>
     * 　@行番号：　@（引数）行番号<BR>
     * 　@カラム：　@１）で取得したカラムモデル<BR>
     * 　@値：　@（引数）扱者コード<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@param l_strTraderCode - (扱者コード)<BR>
     * @@roseuid 48D219ED0011
     */
    public void setTraderCode(int l_intLineNumber, String l_strTraderCode)
    {
        final String STR_METHOD_NAME = "setTraderCode(int, String)";
        log.entering(STR_METHOD_NAME);

        //カラムモデル取得
        WEB3GentradeCsvColumnModel l_columnModel = getColumnModel(TRADER_CODE_LABEL);

        //値セット
        setValue(l_intLineNumber, l_columnModel, l_strTraderCode);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set属性)<BR>
     * 「属性」をセットする。<BR>
     * <BR>
     * １）　@カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数]<BR>
     * 　@項目ラベル：　@入金請求顧客検索CSV.属性ラベル<BR>
     * <BR>
     * ２）　@値セット<BR>
     * 　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     * 　@[set項目値()に指定する引数]<BR>
     * 　@行番号：　@（引数）行番号<BR>
     * 　@カラム：　@１）で取得したカラムモデル<BR>
     * 　@値：引数を元に以下の条件で取得した文字列をセットする。<BR>
     * 　@　@（引数）顧客属性 = 0 の場合　@"現物（前金制）"をセット。<BR>
     * 　@　@（引数）顧客属性 = 1 の場合　@"現物（評価制）"をセット。<BR>
     * 　@　@（引数）顧客属性 = 2 の場合　@"信用"をセット。<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@param l_strAccountAttribute - (顧客属性)<BR>
     * @@roseuid 48D219F5031F
     */
    public void setAttribute(int l_intLineNumber, String l_strAccountAttribute)
    {
        final String STR_METHOD_NAME = "setAttribute(int, String)";
        log.entering(STR_METHOD_NAME);

        //カラムモデル取得
        WEB3GentradeCsvColumnModel l_columnModel = getColumnModel(ATTRIBUTE_LABEL);

        Object l_objValue = null;

        //（引数）顧客属性 = 0 の場合
        if (WEB3AccountAttributeDef.EQUITY_NOT_ASSET_EVAL.equals(l_strAccountAttribute))
        {
            //"現物（前金制）"をセット
            l_objValue = WEB3AdminTPAccountAttributeDef.EQUITY_NOT_ASSET_EVAL;
        }
        //（引数）顧客属性 = 1 の場合
        else if (WEB3AccountAttributeDef.EQUITY_ASSET_EVAL.equals(l_strAccountAttribute))
        {
            //"現物（評価制）"をセット
            l_objValue = WEB3AdminTPAccountAttributeDef.EQUITY_ASSET_EVAL;
        }
        //（引数）顧客属性 = 2 の場合
        else if (WEB3AccountAttributeDef.MARGIN.equals(l_strAccountAttribute))
        {
            //"信用"をセット
            l_objValue = WEB3AdminTPAccountAttributeDef.MARGIN;
        }

        //値セット
        setValue(l_intLineNumber, l_columnModel, l_objValue);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set立替金)<BR>
     * 「立替金」をセットする。<BR>
     * <BR>
     * １）　@カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数]<BR>
     * 　@項目ラベル：　@入金請求顧客検索CSV.立替金ラベル<BR>
     * <BR>
     * ２）　@値セット<BR>
     * 　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     * 　@[set項目値()に指定する引数]<BR>
     * 　@行番号：　@（引数）行番号<BR>
     * 　@カラム：　@１）で取得したカラムモデル<BR>
     * 　@値：　@（引数）立替金<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@param l_strDebitAmount - (立替金)<BR>
     * @@roseuid 48D219F5035E
     */
    public void setDebitAmount(int l_intLineNumber, String l_strDebitAmount)
    {
        final String STR_METHOD_NAME = "setDebitAmount(int, String)";
        log.entering(STR_METHOD_NAME);

        //カラムモデル取得
        WEB3GentradeCsvColumnModel l_columnModel = getColumnModel(DEBIT_AMOUNT_LABEL);

        //値セット
        setValue(l_intLineNumber, l_columnModel, l_strDebitAmount);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set特別立替金)<BR>
     * 「特別立替金」をセットする。<BR>
     * <BR>
     * １）　@カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数]<BR>
     * 　@項目ラベル：　@入金請求顧客検索CSV.特別立替金ラベル<BR>
     * <BR>
     * ２）　@値セット<BR>
     * 　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     * 　@[set項目値()に指定する引数]<BR>
     * 　@行番号：　@（引数）行番号<BR>
     * 　@カラム：　@１）で取得したカラムモデル<BR>
     * 　@値：　@（引数）特別立替金<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@param l_strSpecialDebitAmount - (特別立替金)<BR>
     * @@roseuid 48D21B4F0349
     */
    public void setSpecialDebitAmount(int l_intLineNumber, String l_strSpecialDebitAmount)
    {
        final String STR_METHOD_NAME = "setSpecialDebitAmount(int, String)";
        log.entering(STR_METHOD_NAME);

        //カラムモデル取得
        WEB3GentradeCsvColumnModel l_columnModel = getColumnModel(SPECIAL_DEBIT_AMOUNT_LABEL);

        //値セット
        setValue(l_intLineNumber, l_columnModel, l_strSpecialDebitAmount);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set必要入金額)<BR>
     * 「必要入金額」をセットする。<BR>
     * <BR>
     * １）　@カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数]<BR>
     * 　@項目ラベル：　@入金請求顧客検索CSV.必要入金額ラベル<BR>
     * <BR>
     * ２）　@値セット<BR>
     * 　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     * 　@[set項目値()に指定する引数]<BR>
     * 　@行番号：　@（引数）行番号<BR>
     * 　@カラム：　@１）で取得したカラムモデル<BR>
     * 　@値：　@（引数）預り金不足額(T+0)<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@param l_strLackAccountBalance - (預り金不足額(T+0))<BR>
     * @@roseuid 48D219F5039C
     */
    public void setRequiredPayAmt(int l_intLineNumber, String l_strLackAccountBalance)
    {
        final String STR_METHOD_NAME = "setRequiredPayAmt(int, String)";
        log.entering(STR_METHOD_NAME);

        //カラムモデル取得
        WEB3GentradeCsvColumnModel l_columnModel = getColumnModel(REQUIRED_PAY_AMT_LABEL);

        //値セット
        setValue(l_intLineNumber, l_columnModel, l_strLackAccountBalance);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set第一水準追証金額)<BR>
     * 「第一水準追証金額」をセットする。<BR>
     * <BR>
     * １）　@カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数]<BR>
     * 　@項目ラベル：　@入金請求顧客検索CSV.第一水準追証金額ラベル<BR>
     * <BR>
     * ２）　@値セット<BR>
     * 　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     * 　@[set項目値()に指定する引数]<BR>
     * 　@行番号：　@（引数）行番号<BR>
     * 　@カラム：　@１）で取得したカラムモデル<BR>
     * 　@値：　@（引数）第一水準追証金額<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@param l_strFirstDepositAmount - (第一水準追証金額)<BR>
     * @@roseuid 48D219F503CB
     */
    public void setFirstDepositAmount(int l_intLineNumber, String l_strFirstDepositAmount)
    {
        final String STR_METHOD_NAME = "setFirstDepositAmount(int, String)";
        log.entering(STR_METHOD_NAME);

        //カラムモデル取得
        WEB3GentradeCsvColumnModel l_columnModel = getColumnModel(FIRST_DEPOSIT_AMOUNT_LABEL);

        //値セット
        setValue(l_intLineNumber, l_columnModel, l_strFirstDepositAmount);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set第一水準追証経過日数)<BR>
     * 「第一水準追証経過日数」をセットする。<BR>
     * <BR>
     * １）　@カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数]<BR>
     * 　@項目ラベル：　@入金請求顧客検索CSV.第一水準追証経過日数ラベル<BR>
     * <BR>
     * ２）　@値セット<BR>
     * 　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     * 　@[set項目値()に指定する引数]<BR>
     * 　@行番号：　@（引数）行番号<BR>
     * 　@カラム：　@１）で取得したカラムモデル<BR>
     * 　@値：　@（引数）第一水準追証経過日数<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@param l_strFirstDepositPassDay - (第一水準追証経過日数)<BR>
     * @@roseuid 48D21C57034E
     */
    public void setFirstDepositPassDay(int l_intLineNumber, String l_strFirstDepositPassDay)
    {
        final String STR_METHOD_NAME = "setFirstDepositPassDay(int, String)";
        log.entering(STR_METHOD_NAME);

        //カラムモデル取得
        WEB3GentradeCsvColumnModel l_columnModel = getColumnModel(FIRST_DEPOSIT_PASS_DAY_LABEL);

        //値セット
        setValue(l_intLineNumber, l_columnModel, l_strFirstDepositPassDay);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set第二水準追証請求（1）)<BR>
     * 「第二水準追証請求（1）」をセットする。<BR>
     * <BR>
     * １）　@カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数]<BR>
     * 　@項目ラベル：　@入金請求顧客検索CSV.第二水準追証請求（1）ラベル<BR>
     * <BR>
     * ２）　@値セット<BR>
     * 　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     * 　@[set項目値()に指定する引数]<BR>
     * 　@行番号：　@（引数）行番号<BR>
     * 　@カラム：　@１）で取得したカラムモデル<BR>
     * 　@値：　@（引数）第二水準追証請求（1）<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@param l_strSecondDeposit1 - (第二水準追証請求（1）)<BR>
     * @@roseuid 48D21C59008F
     */
    public void setSecondDeposit1(int l_intLineNumber, String l_strSecondDeposit1)
    {
        final String STR_METHOD_NAME = "setSecondDeposit1(int, String)";
        log.entering(STR_METHOD_NAME);

        //カラムモデル取得
        WEB3GentradeCsvColumnModel l_columnModel = getColumnModel(SECOND_DEPOSIT_1_LABEL);

        //値セット
        setValue(l_intLineNumber, l_columnModel, l_strSecondDeposit1);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set第二水準追証請求（2）)<BR>
     * 「第二水準追証請求（2）」をセットする。<BR>
     * <BR>
     * １）　@カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数]<BR>
     * 　@項目ラベル：　@入金請求顧客検索CSV.第二水準追証請求（2）ラベル<BR>
     * <BR>
     * ２）　@値セット<BR>
     * 　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     * 　@[set項目値()に指定する引数]<BR>
     * 　@行番号：　@（引数）行番号<BR>
     * 　@カラム：　@１）で取得したカラムモデル<BR>
     * 　@値：　@（引数）第二水準追証請求（2）<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@param l_strSecondDeposit2 - (第二水準追証請求（2）)<BR>
     * @@roseuid 48D21C930084
     */
    public void setSecondDeposit2(int l_intLineNumber, String l_strSecondDeposit2)
    {
        final String STR_METHOD_NAME = "setSecondDeposit2(int, String)";
        log.entering(STR_METHOD_NAME);

        //カラムモデル取得
        WEB3GentradeCsvColumnModel l_columnModel = getColumnModel(SECOND_DEPOSIT_2_LABEL);

        //値セット
        setValue(l_intLineNumber, l_columnModel, l_strSecondDeposit2);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set第二水準追証未入金)<BR>
     * 「第二水準追証未入金」をセットする。<BR>
     * <BR>
     * １）　@カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数]<BR>
     * 　@項目ラベル：　@入金請求顧客検索CSV.第二水準追証未入金ラベル<BR>
     * <BR>
     * ２）　@値セット<BR>
     * 　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     * 　@[set項目値()に指定する引数]<BR>
     * 　@行番号：　@（引数）行番号<BR>
     * 　@カラム：　@１）で取得したカラムモデル<BR>
     * 　@値：　@（引数）第二水準追証未入金<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@param l_strSecondDepositNonPay - (第二水準追証未入金)<BR>
     * @@roseuid 48D21C9402C6
     */
    public void setSecondDepositNonPay(int l_intLineNumber, String l_strSecondDepositNonPay)
    {
        final String STR_METHOD_NAME = "setSecondDepositNonPay(int, String)";
        log.entering(STR_METHOD_NAME);

        //カラムモデル取得
        WEB3GentradeCsvColumnModel l_columnModel = getColumnModel(SECOND_DEPOSIT_NON_PAY_LABEL);

        //値セット
        setValue(l_intLineNumber, l_columnModel, l_strSecondDepositNonPay);

        log.exiting(STR_METHOD_NAME);
    }
}
@
