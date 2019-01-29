head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.23.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoPasswordChangeAccountCsv.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 暗証番号変更顧客CSV(WEB3AdminAccInfoPasswordChangeAccountCsv)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/16 張宝楠 (中訊) 新規作成
*/

package webbroker3.accountinfo;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.gentrade.WEB3GentradeCsvDataModel;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;


/**
 * (暗証番号変更顧客CSV)<BR>
 * 暗証番号変更顧客CSV<BR>
 *
 * @@author 張宝楠(中訊)
 * @@version 1.0
 */
public class WEB3AdminAccInfoPasswordChangeAccountCsv extends WEB3GentradeCsvDataModel 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoPasswordChangeAccountCsv.class); 
    
    /**
     * (証券会社コードラベル)<BR>
     * 定数定義プロパティ　@”証券会社コード” <BR>
     */
    public static  final String INSTITUTION_CODE_LABEL = "証券会社コード";
    
    /**
     * (部店コードラベル)<BR>
     * 定数定義プロパティ　@”部店コード” <BR>
     */
    public static  final String BRANCH_CODE_LABEL = "部店コード";
    
    /**
     * (顧客コードラベル)<BR>
     * 定数定義プロパティ　@”顧客コード” <BR>
     */
    public static  final String ACCOUNT_CODE_LABEL = "顧客コード";
    
    /**
     * (顧客名ラベル)<BR>
     * 定数定義プロパティ　@”顧客名” <BR>
     */
    public static  final String ACCOUNT_NAME_LABEL = "顧客名";
    
    /**
     * (暗証番号更新日ラベル)<BR>
     * 定数定義プロパティ　@”暗証番号更新日” <BR>
     */
    public static  final String PASSWORD_UPDATED_DATE_LABEL = "暗証番号更新日";
    
    /**
     * (暗証番号更新者コードラベル)<BR>
     * 定数定義プロパティ　@”暗証番号更新者コード” <BR>
     */
    public static  final String PASSWORD_UPDATER_CODE_LABEL = "暗証番号更新者コード";
    
    /**
     * (暗証番号変更顧客CSV)<BR>
     * コンストラクタ。 <BR>
     * <BR>
     * インスタンスを生成し、ヘッダ情報をセットする。 <BR>
     * 　@－super()にてインスタンスを生成する。 <BR>
     * 　@－createキーヘッダ()をコールし、キーヘッダ情報を作成する。 <BR>
     * 　@－createカラムヘッダ()をコールし、カラムヘッダ情報を作成する。 <BR>
     * @@return webbroker3.accountinfo.WEB3AdminAccInfoPasswordChangeAccountCsv
     * @@roseuid 416B89D800E6
     */
    public WEB3AdminAccInfoPasswordChangeAccountCsv() 
    {
        createKeyHeader();      //キーヘッダ情報を作成する。
        createColumnHeader();   //カラムヘッダ情報を作成する。
    }
    
    /**
     * (createカラムヘッダ)<BR>
     * 　@以下の通りCSVカラムモデルの配列を生成し、setカラムヘッダ()<BR>
     * にてインスタンスにセットする。 <BR>
     * <BR>
     * [カラムヘッダ配列] <BR>
     * <BR>
     * －　@index = 0 <BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@暗証番号変更顧客CSV.証券会社コードラベル <BR>
     * 　@カラム番号： 0 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 1<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@暗証番号変更顧客CSV.部店コードラベル <BR>
     * 　@カラム番号： 1<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 2<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@暗証番号変更顧客CSV.顧客コードラベル <BR>
     * 　@カラム番号： 2<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 3<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@暗証番号変更顧客CSV.顧客名ラベル <BR>
     * 　@カラム番号： 3<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 4 <BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@暗証番号変更顧客CSV.暗証番号更新日ラベル <BR>
     * 　@カラム番号： 4<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_日付時間<BR>
     * 　@日付フォーマット：　@ <BR>
     * 　@　@new SimpleDateFormat("yyyy/MM/dd HH:mm:ss") <BR>
     * <BR>
     * －　@index = 5<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@暗証番号変更顧客CSV.暗証番号更新者コードラベル <BR>
     * 　@カラム番号： 5<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * @@roseuid 416B89D800C7
     */
    protected void createColumnHeader() 
    {
        final String STR_METHOD_NAME = " createColumnHeader()";
        log.entering(STR_METHOD_NAME);
        
        WEB3GentradeCsvColumnModel[] l_columnHeader = new WEB3GentradeCsvColumnModel[6];

        //証券会社コード        
        l_columnHeader[0] = new WEB3GentradeCsvColumnModel(INSTITUTION_CODE_LABEL, 0, WEB3GentradeCsvColumnModel.STRINGTYPE, null);
        
        //部店コード
        l_columnHeader[1] = new WEB3GentradeCsvColumnModel(BRANCH_CODE_LABEL, 1, WEB3GentradeCsvColumnModel.STRINGTYPE, null);
        
        //顧客コード
        l_columnHeader[2] = new WEB3GentradeCsvColumnModel(ACCOUNT_CODE_LABEL, 2, WEB3GentradeCsvColumnModel.STRINGTYPE, null);
        
        //顧客名
        l_columnHeader[3] = new WEB3GentradeCsvColumnModel(ACCOUNT_NAME_LABEL, 3, WEB3GentradeCsvColumnModel.STRINGTYPE, null);
        
        //パスワード更新日
        l_columnHeader[4] = new WEB3GentradeCsvColumnModel(PASSWORD_UPDATED_DATE_LABEL, 4, WEB3GentradeCsvColumnModel.TIMESTAMPTYPE, new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"));        
        
        //パスワード更新者コード
        l_columnHeader[5] = new WEB3GentradeCsvColumnModel(PASSWORD_UPDATER_CODE_LABEL, 5, WEB3GentradeCsvColumnModel.STRINGTYPE, null);
        
        setColumnHeader(l_columnHeader);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (createキーヘッダ)<BR>
     * キーヘッダをセットする。 <BR>
     * <BR>
     * 　@以下の通り文字列の配列を生成し、setキーヘッダ()にてインスタンス<BR>
     * にセットする。 <BR>
     * <BR>
     * [キーヘッダ配列] <BR>
     * <BR>
     * －　@index = 0 <BR>
     * 　@現在日時を"yyyy/MM/dd HH:mm:ss"の形式にformatした文字列。 <BR>
     * <BR>
     * (*1) 現在日時 <BR>
     * TradingSystem.getSystemTimestamp()<BR>
     * @@roseuid 416B89D800C8
     */
    protected void createKeyHeader() 
    {
        final String STR_METHOD_NAME = " createKeyHeader()";
        log.entering(STR_METHOD_NAME);
        
        String[] l_strKeyHeader = new String[1];
        
        l_strKeyHeader[0] = WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(), "yyyy/MM/dd HH:mm:ss");
        
        setKeyHeader(l_strKeyHeader);
        
        log.exiting(STR_METHOD_NAME);      
    }
    
    /**
     * (set証券会社コード)<BR>
     * 証券会社コードをセットする。 <BR>
     * <BR>
     * １）　@カラムモデル取得 <BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。 <BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数] <BR>
     * 　@項目ラベル：　@暗証番号変更顧客CSV.証券会社コードラベル <BR>
     * <BR>
     * ２）　@値セット <BR>
     * 　@this.set項目値()にて項目値をセットする。 <BR>
     * <BR>
     * 　@[set項目値()に指定する引数] <BR>
     * 　@行番号：　@引数の行番号 <BR>
     * 　@カラム：　@１）で取得したカラムモデル <BR>
     * 　@値：　@証券会社コード<BR>
     * @@param l_intLineNumber - 行番号
     * @@param l_strInstitutionCode - 証券会社コード
     * @@roseuid 416B89D800C9
     */
    public void setInstitutionCode(int l_intLineNumber, String l_strInstitutionCode) 
    {
        final String STR_METHOD_NAME = " setInstitutionCode(int, String)";
        log.entering(STR_METHOD_NAME);

        //カラムモデル取得
        WEB3GentradeCsvColumnModel l_columnModel = getColumnModel(INSTITUTION_CODE_LABEL);
        
        //値セット
        setValue(l_intLineNumber, l_columnModel, l_strInstitutionCode);
      
        log.exiting(STR_METHOD_NAME);
     
    }
    
    /**
     * (set部店コード)<BR>
     * 部店コードをセットする。 <BR>
     * <BR>
     * １）　@カラムモデル取得 <BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。 <BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数] <BR>
     * 　@項目ラベル：　@暗証番号変更顧客CSV.部店コードラベル <BR>
     * <BR>
     * ２）　@値セット <BR>
     * 　@this.set項目値()にて項目値をセットする。 <BR>
     * <BR>
     * 　@[set項目値()に指定する引数] <BR>
     * 　@行番号：　@引数の行番号 <BR>
     * 　@カラム：　@１）で取得したカラムモデル <BR>
     * 　@値：　@部店コード<BR>
     * @@param l_intLineNumber - 行番号
     * @@param l_strBranchCode - 部店コード
     * @@roseuid 416B89D800D6
     */
    public void setBranchCode(int l_intLineNumber, String l_strBranchCode) 
    {
        final String STR_METHOD_NAME = " setBranchCode(int, String)";
        log.entering(STR_METHOD_NAME);

        //カラムモデル取得
        WEB3GentradeCsvColumnModel l_columnModel = getColumnModel(BRANCH_CODE_LABEL);
        
        //値セット
        setValue(l_intLineNumber, l_columnModel, l_strBranchCode);
      
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (set顧客コード)<BR>
     * 顧客コードをセットする。 <BR>
     * <BR>
     * １）　@カラムモデル取得 <BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。 <BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数] <BR>
     * 　@項目ラベル：　@暗証番号変更顧客CSV.顧客コードラベル <BR>
     * <BR>
     * ２）　@値セット <BR>
     * 　@this.set項目値()にて項目値をセットする。 <BR>
     * <BR>
     * 　@[set項目値()に指定する引数] <BR>
     * 　@行番号：　@引数の行番号 <BR>
     * 　@カラム：　@１）で取得したカラムモデル <BR>
     * 　@値：　@顧客コード<BR>
     * @@param l_intLineNumber - 行番号
     * @@param l_strAccountCode - 顧客コード
     * @@roseuid 416B89D800D9
     */
    public void setAccountCode(int l_intLineNumber, String l_strAccountCode) 
    {
        final String STR_METHOD_NAME = " setAccountCode(int, String)";
        log.entering(STR_METHOD_NAME);

        //カラムモデル取得
        WEB3GentradeCsvColumnModel l_columnModel = getColumnModel(ACCOUNT_CODE_LABEL);
        
        //値セット
        setValue(l_intLineNumber, l_columnModel, l_strAccountCode);
      
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (set顧客名)<BR>
     * 顧客名をセットする。 <BR>
     * <BR>
     * １）　@カラムモデル取得 <BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。 <BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数] <BR>
     * 　@項目ラベル：　@暗証番号変更顧客CSV.顧客名ラベル <BR>
     * <BR>
     * ２）　@値セット <BR>
     * 　@this.set項目値()にて項目値をセットする。 <BR>
     * <BR>
     * 　@[set項目値()に指定する引数] <BR>
     * 　@行番号：　@引数の行番号 <BR>
     * 　@カラム：　@１）で取得したカラムモデル <BR>
     * 　@値：　@顧客名<BR>
     * @@param l_intLineNumber - 行番号
     * @@param l_strAccountName - 顧客名
     * @@roseuid 416B89D800DC
     */
    public void setAccountName(int l_intLineNumber, String l_strAccountName) 
    {
        final String STR_METHOD_NAME = " setAccountName(int, String)";
        log.entering(STR_METHOD_NAME);

        //カラムモデル取得
        WEB3GentradeCsvColumnModel l_columnModel = getColumnModel(ACCOUNT_NAME_LABEL);
        
        //値セット
        setValue(l_intLineNumber, l_columnModel, l_strAccountName);
      
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set暗証番号更新日)<BR>
     * 暗証番号更新日をセットする。 <BR>
     * <BR>
     * １）　@カラムモデル取得 <BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。 <BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数] <BR>
     * 　@項目ラベル：　@暗証番号変更顧客CSV.パスワード更新日ラベル <BR>
     * <BR>
     * ２）　@値セット <BR>
     * 　@this.set項目値()にて項目値をセットする。 <BR>
     * <BR>
     * 　@[set項目値()に指定する引数] <BR>
     * 　@行番号：　@引数の行番号 <BR>
     * 　@カラム：　@１）で取得したカラムモデル <BR>
     * 　@値：　@暗証番号更新日<BR>
     * @@param l_intLineNumber - 行番号
     * @@param l_datPasswordUpdatedDate - 暗証番号更新日
     * @@roseuid 416B89D800DF
     */
    public void setPasswordUpdatedDate(int l_intLineNumber, Date l_datPasswordUpdatedDate) 
    {
        final String STR_METHOD_NAME = " setPasswordUpdatedDate(int, String)";
        log.entering(STR_METHOD_NAME);

        //カラムモデル取得
        WEB3GentradeCsvColumnModel l_columnModel = getColumnModel(PASSWORD_UPDATED_DATE_LABEL);
        
        //値セット
        setValue(l_intLineNumber, l_columnModel, l_datPasswordUpdatedDate);
      
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (set暗証番号更新者コード)<BR>
     * 暗証番号更新者コードをセットする。 <BR>
     * <BR>
     * １）　@カラムモデル取得 <BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。 <BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数] <BR>
     * 　@項目ラベル：　@暗証番号変更顧客CSV.パスワード更新者コードラベル <BR>
     * <BR>
     * ２）　@値セット <BR>
     * 　@this.set項目値()にて項目値をセットする。 <BR>
     * <BR>
     * 　@[set項目値()に指定する引数] <BR>
     * 　@行番号：　@引数の行番号 <BR>
     * 　@カラム：　@１）で取得したカラムモデル <BR>
     * 　@値：　@暗証番号更新者コード<BR>
     * @@param l_intLineNumber - 行番号
     * @@param l_strPasswordUpdaterCode - 暗証番号更新者コード
     * @@roseuid 416B89D800E2
     */
    public void setPasswordUpdaterCode(int l_intLineNumber, String l_strPasswordUpdaterCode) 
    {
        final String STR_METHOD_NAME = " setPasswordUpdaterCode(int, String)";
        log.entering(STR_METHOD_NAME);

        //カラムモデル取得
        WEB3GentradeCsvColumnModel l_columnModel = getColumnModel(PASSWORD_UPDATER_CODE_LABEL);
        
        //値セット
        setValue(l_intLineNumber, l_columnModel, l_strPasswordUpdaterCode);
      
        log.exiting(STR_METHOD_NAME);     
    }
}
@
