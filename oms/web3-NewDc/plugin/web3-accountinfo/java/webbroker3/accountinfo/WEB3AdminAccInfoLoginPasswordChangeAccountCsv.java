head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.24.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoLoginPasswordChangeAccountCsv.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : パスワード変更顧客CSV(WEB3AdminAccInfoLoginPasswordChangeAccountCsv)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/15 張宝楠 (中訊) 新規作成
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
 * (パスワード変更顧客CSV)<BR>
 * パスワード変更顧客ダウンロードで作成するCSVファ@イルデータクラス <BR>
 * 
 * @@author 張宝楠(中訊)
 * @@version 1.0
 */
public class WEB3AdminAccInfoLoginPasswordChangeAccountCsv extends WEB3GentradeCsvDataModel 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoLoginPasswordChangeAccountCsv.class); 
    
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
     * (パスワード更新日ラベル)<BR>
     * 定数定義プロパティ　@”パスワード更新日” <BR>
     */
    public static  final String LOGIN_PASSWORD_UPDATED_DATE_LABEL = "パスワード更新日";
    
    /**
     * (パスワード更新者コードラベル)<BR>
     * 定数定義プロパティ　@”パスワード更新者コード” <BR>
     */
    public static  final String LOGIN_PASSWORD_UPDATER_CODE_LABEL = "パスワード更新者コード";
    
    /**
     * (パスワード変更顧客CSV)<BR>
     * コンストラクタ。 <BR>
     * <BR>
     * インスタンスを生成し、ヘッダ情報をセットする。 <BR>
     * 　@－super()にてインスタンスを生成する。 <BR>
     * 　@－createキーヘッダ()をコールし、キーヘッダ情報を作成する。 <BR>
     * 　@－createカラムヘッダ()をコールし、カラムヘッダ情報を作成する。 <BR>
     * @@return webbroker3.accountinfo.WEB3AdminAccInfoLoginPasswordChangeAccountCsv
     * @@roseuid 4147D2C40069
     */
    public WEB3AdminAccInfoLoginPasswordChangeAccountCsv() 
    {
        createKeyHeader();      //キーヘッダ情報を作成する。
        createColumnHeader();   //カラムヘッダ情報を作成する。
    }
    
    /**
     * (createカラムヘッダ)<BR>
     * 　@以下の通りCSVカラムモデルの配列を生成し、setカラムヘッダ()にて<BR>
     * インスタンスにセットする。 <BR>
     * <BR>
     * [カラムヘッダ配列] <BR>
     * <BR>
     * －　@index = 0 <BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@パスワード変更顧客CSV.証券会社コードラベル <BR>
     * 　@カラム番号： 0 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 1<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@パスワード変更顧客CSV.部店コードラベル <BR>
     * 　@カラム番号： 1<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 2<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@パスワード変更顧客CSV.顧客コードラベル <BR>
     * 　@カラム番号： 2<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 3<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@パスワード変更顧客CSV.顧客名ラベル <BR>
     * 　@カラム番号： 3<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 4 <BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@パスワード変更顧客CSV.パスワード更新日ラベル <BR>
     * 　@カラム番号： 4<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_日付時間<BR>
     * 　@日付フォーマット：　@ <BR>
     * 　@　@new SimpleDateFormat("yyyy/MM/dd HH:mm:ss") <BR>
     * <BR>
     * －　@index = 5<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@パスワード変更顧客CSV.パスワード更新者コードラベル <BR>
     * 　@カラム番号： 5<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * @@roseuid 4147D2C303E3
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
        l_columnHeader[4] = new WEB3GentradeCsvColumnModel(LOGIN_PASSWORD_UPDATED_DATE_LABEL, 4, WEB3GentradeCsvColumnModel.TIMESTAMPTYPE, new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"));        
        
        //パスワード更新者コード
        l_columnHeader[5] = new WEB3GentradeCsvColumnModel(LOGIN_PASSWORD_UPDATER_CODE_LABEL, 5, WEB3GentradeCsvColumnModel.STRINGTYPE, null);
        
        setColumnHeader(l_columnHeader);
        
        log.exiting(STR_METHOD_NAME);
     
    }
    
    /**
     * (createキーヘッダ)<BR>
     * キーヘッダをセットする。 <BR>
     * <BR>
     * 　@以下の通り文字列の配列を生成し、setキーヘッダ()にて<BR>
     * インスタンスにセットする。 <BR>
     * <BR>
     * [キーヘッダ配列] <BR>
     * <BR>
     * －　@index = 0 <BR>
     * 　@現在日時を"yyyy/MM/dd HH:mm:ss"の形式にformatした文字列。 <BR>
     * <BR>
     * (*1) 現在日時 <BR>
     * TradingSystem.getSystemTimestamp()<BR>
     * @@roseuid 4147D2C4001A
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
     * 　@項目ラベル：　@パスワード変更顧客CSV.証券会社コードラベル <BR>
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
     * @@roseuid 4147D3A9000B
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
     * 　@項目ラベル：　@パスワード変更顧客CSV.部店コードラベル <BR>
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
     * @@roseuid 4147D2C4003A
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
     * 　@項目ラベル：　@パスワード変更顧客CSV.顧客コードラベル <BR>
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
     * @@roseuid 4147D2C40059
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
     * 　@項目ラベル：　@パスワード変更顧客CSV.顧客名ラベル <BR>
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
     * @@roseuid 4147D3C90191
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
     * (setパスワード更新日)<BR>
     * パスワード更新日をセットする。 <BR>
     * <BR>
     * １）　@カラムモデル取得 <BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。 <BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数] <BR>
     * 　@項目ラベル：　@パスワード変更顧客CSV.パスワード更新日ラベル <BR>
     * <BR>
     * ２）　@値セット <BR>
     * 　@this.set項目値()にて項目値をセットする。 <BR>
     * <BR>
     * 　@[set項目値()に指定する引数] <BR>
     * 　@行番号：　@引数の行番号 <BR>
     * 　@カラム：　@１）で取得したカラムモデル <BR>
     * 　@値：　@パスワード更新日<BR>
     * @@param l_intLineNumber - 行番号
     * @@param l_datLoginPasswordUpdatedDate - パスワード更新日
     * @@roseuid 4147D4320328
     */
    public void setLoginPasswordUpdatedDate(int l_intLineNumber, Date l_datLoginPasswordUpdatedDate) 
    {
        final String STR_METHOD_NAME = " setLoginPasswordUpdatedDate(int, String)";
        log.entering(STR_METHOD_NAME);

        //カラムモデル取得
        WEB3GentradeCsvColumnModel l_columnModel = getColumnModel(LOGIN_PASSWORD_UPDATED_DATE_LABEL);
        
        //値セット
        setValue(l_intLineNumber, l_columnModel, l_datLoginPasswordUpdatedDate);
      
        log.exiting(STR_METHOD_NAME);
    }   
    
    /**
     * (setパスワード更新者コード)<BR>
     * パスワード更新者コードをセットする。 <BR>
     * <BR>
     * １）　@カラムモデル取得 <BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。 <BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数] <BR>
     * 　@項目ラベル：　@パスワード変更顧客CSV.パスワード更新者コードラベル <BR>
     * <BR>
     * ２）　@値セット <BR>
     * 　@this.set項目値()にて項目値をセットする。 <BR>
     * <BR>
     * 　@[set項目値()に指定する引数] <BR>
     * 　@行番号：　@引数の行番号 <BR>
     * 　@カラム：　@１）で取得したカラムモデル <BR>
     * 　@値：　@パスワード更新者コード<BR>
     * @@param l_intLineNumber - 行番号
     * @@param l_strLoginPasswordUpdaterCode - パスワード更新者コード
     * @@roseuid 4147D45A0172
     */
    public void setLoginPasswordUpdaterCode(int l_intLineNumber, String l_strLoginPasswordUpdaterCode) 
    {
        final String STR_METHOD_NAME = " setLoginPasswordUpdaterCode(int, String)";
        log.entering(STR_METHOD_NAME);

        //カラムモデル取得
        WEB3GentradeCsvColumnModel l_columnModel = getColumnModel(LOGIN_PASSWORD_UPDATER_CODE_LABEL);
        
        //値セット
        setValue(l_intLineNumber, l_columnModel, l_strLoginPasswordUpdaterCode);
      
        log.exiting(STR_METHOD_NAME);
    }
}
@
