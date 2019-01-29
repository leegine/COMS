head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.24.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMailAddressCsv.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : メールアドレスCSV(WEB3AdminAccInfoMailAddressCsv.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/09 劉江涛 (中訊) 新規作成
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
 * (メールアドレスCSV)<BR>
 * メールアドレス全件ダウンロード，メールアドレス変更顧客ダウンロードで<BR>
 * 作成するCSVファ@イルデータクラス <BR>
 * @@author 劉江涛
 * @@version 1.0
 */
public class WEB3AdminAccInfoMailAddressCsv extends WEB3GentradeCsvDataModel 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoMailAddressCsv.class); 
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
     * (メールアドレスラベル)<BR>
     * 定数定義プロパティ　@”メールアドレス” <BR>
     */
    public static  final String MAIL_ADDRESS_LABEL = "メールアドレス";
    
    /**
     * (メールアドレス更新日ラベル)<BR>
     * 定数定義プロパティ　@”メールアドレス更新日” <BR>
     */
    public static  final String MAIL_ADDRESS_UPDATED_DATE_LABEL = "メールアドレス更新日";
    
    /**
     * (メールアドレス更新者コードラベル)<BR>
     * 定数定義プロパティ　@”メールアドレス更新者コード” <BR>
     */
    public static  final String MAIL_ADDRESS_UPDATER_CODE_LABEL = "メールアドレス更新者コード";
    
    /**
     * (送信フラグラベル)<BR>
     * 定数定義プロパティ　@”送信フラグ” <BR> 
     */
    public static  final String Mail_ADDRESS_SEND_FLAG = "送信フラグ";
    
    /**
     * (メールアドレスCSV)<BR>
     * コンストラクタ。 <BR>
     * <BR>
     * インスタンスを生成し、ヘッダ情報をセットする。 <BR>
     * 　@－super()にてインスタンスを生成する。 <BR>
     * 　@－createキーヘッダ()をコールし、キーヘッダ情報を作成する。 <BR>
     * 　@－createカラムヘッダ()をコールし、カラムヘッダ情報を作成する。 <BR>
     * @@return webbroker3.accountinfo.WEB3AdminAccInfoMailAddressCsv
     * @@roseuid 4147E7B6026C
     */
    public WEB3AdminAccInfoMailAddressCsv() 
    {
        createColumnHeader();
        createKeyHeader();
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
     * 　@項目ラベル：　@メールアドレスCSV.証券会社コードラベル <BR>
     * 　@カラム番号： 0 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 1<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@メールアドレスCSV.部店コードラベル <BR>
     * 　@カラム番号： 1<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 2<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@メールアドレスCSV.顧客コードラベル <BR>
     * 　@カラム番号： 2<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 3<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@メールアドレスCSV.顧客名ラベル <BR>
     * 　@カラム番号： 3<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 4<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@メールアドレスCSV.メールアドレスラベル <BR>
     * 　@カラム番号： 4<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 5 <BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@メールアドレスCSV.送信フラグラベル <BR>
     * 　@カラム番号： 5 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 6<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@メールアドレスCSV.メールアドレス更新日ラベル <BR>
     * 　@カラム番号： 6<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_日付時間<BR>
     * 　@日付フォーマット：　@ <BR>
     * 　@　@new SimpleDateFormat("yyyy/MM/dd HH:mm:ss") <BR>
     * <BR>
     * －　@index = 7<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@メールアドレスCSV.メールアドレス更新者コードラベル <BR>
     * 　@カラム番号： 7<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * @@roseuid 4147E7B6024D
     */
    protected void createColumnHeader() 
    {
        final String STR_METHOD_NAME = " createColumnHeader()";
        log.entering(STR_METHOD_NAME);
        
        WEB3GentradeCsvColumnModel[] l_columnHeader = new WEB3GentradeCsvColumnModel[8];

        //証券会社コード        
        l_columnHeader[0] = new WEB3GentradeCsvColumnModel(INSTITUTION_CODE_LABEL, 0, WEB3GentradeCsvColumnModel.STRINGTYPE, null);
        
        //部店コード
        l_columnHeader[1] = new WEB3GentradeCsvColumnModel(BRANCH_CODE_LABEL, 1, WEB3GentradeCsvColumnModel.STRINGTYPE, null);
        
        //顧客コード
        l_columnHeader[2] = new WEB3GentradeCsvColumnModel(ACCOUNT_CODE_LABEL, 2, WEB3GentradeCsvColumnModel.STRINGTYPE, null);
        
        //顧客名
        l_columnHeader[3] = new WEB3GentradeCsvColumnModel(ACCOUNT_NAME_LABEL, 3, WEB3GentradeCsvColumnModel.STRINGTYPE, null);
        
        //メールアドレスラベル
        l_columnHeader[4] = new WEB3GentradeCsvColumnModel(MAIL_ADDRESS_LABEL, 4, WEB3GentradeCsvColumnModel.STRINGTYPE, null);
        
        //　@[CSVカラムモデル コンストラクタの引数] 
        //項目ラベル：　@メールアドレスCSV.送信フラグラベル 
        //　@カラム番号： 5 
        //　@項目型：　@CSVカラムモデル.項目型_文字列 
        //　@日付フォーマット：　@null 
        l_columnHeader[5] = new WEB3GentradeCsvColumnModel(Mail_ADDRESS_SEND_FLAG, 5, WEB3GentradeCsvColumnModel.STRINGTYPE, null);
        
        //メールアドレス更新日ラベル
        l_columnHeader[6] = new WEB3GentradeCsvColumnModel(MAIL_ADDRESS_UPDATED_DATE_LABEL, 6, WEB3GentradeCsvColumnModel.TIMESTAMPTYPE, new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"));
        
        //メールアドレス更新者コードラベル
        l_columnHeader[7] = new WEB3GentradeCsvColumnModel(MAIL_ADDRESS_UPDATER_CODE_LABEL, 7, WEB3GentradeCsvColumnModel.STRINGTYPE, null);
        
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
     * @@roseuid 4147E7B6024E
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
     * 　@項目ラベル：　@メールアドレスCSV.証券会社コードラベル <BR>
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
     * @@roseuid 4147E7B6024F
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
     * 　@項目ラベル：　@メールアドレスCSV.部店コードラベル <BR>
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
     * @@roseuid 4147E7B6025D
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
     * 　@項目ラベル：　@メールアドレスCSV.顧客コードラベル <BR>
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
     * @@roseuid 4147E7B60260
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
     * @@roseuid 4147E7B60263
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
     * (setメールアドレス)<BR>
     * メールアドレスをセットする。 <BR>
     * <BR>
     * １）　@カラムモデル取得 <BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。 <BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数] <BR>
     * 　@項目ラベル：　@メールアドレスCSV.メールアドレスラベル <BR>
     * <BR>
     * ２）　@値セット <BR>
     * 　@this.set項目値()にて項目値をセットする。 <BR>
     * <BR>
     * 　@[set項目値()に指定する引数] <BR>
     * 　@行番号：　@引数の行番号 <BR>
     * 　@カラム：　@１）で取得したカラムモデル <BR>
     * 　@値：　@メールアドレス<BR>
     * @@param l_intLineNumber - 行番号
     * @@param l_strMailAddress - メールアドレス
     * @@roseuid 4147E8AF029B
     */
    public void setMailAddress(int l_intLineNumber, String l_strMailAddress) 
    {
        final String STR_METHOD_NAME = " setMailAddress(int, String)";
        log.entering(STR_METHOD_NAME);

        //カラムモデル取得
        WEB3GentradeCsvColumnModel l_columnModel = getColumnModel(MAIL_ADDRESS_LABEL);
        
        //値セット
        setValue(l_intLineNumber, l_columnModel, l_strMailAddress);
      
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (setメールアドレス更新日)<BR>
     * メールアドレス更新日をセットする。 <BR>
     * <BR>
     * １）　@カラムモデル取得 <BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。 <BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数] <BR>
     * 　@項目ラベル：　@メールアドレスCSV.メールアドレス更新日ラベル <BR>
     * <BR>
     * ２）　@値セット <BR>
     * 　@this.set項目値()にて項目値をセットする。 <BR>
     * <BR>
     * 　@[set項目値()に指定する引数] <BR>
     * 　@行番号：　@引数の行番号 <BR>
     * 　@カラム：　@１）で取得したカラムモデル <BR>
     * 　@値：　@メールアドレス更新日<BR>
     * @@param l_intLineNumber - 行番号
     * @@param l_datMailAddressUpdatedDate - メールアドレス更新日
     * @@roseuid 4147E7B60266
     */
    public void setMailAddressUpdatedDate(int l_intLineNumber, Date l_datMailAddressUpdatedDate) 
    {
        final String STR_METHOD_NAME = " setMailAddressUpdatedDate(int, Date)";
        log.entering(STR_METHOD_NAME);

        //カラムモデル取得
        WEB3GentradeCsvColumnModel l_columnModel = getColumnModel(MAIL_ADDRESS_UPDATED_DATE_LABEL);
        
        //値セット
        setValue(l_intLineNumber, l_columnModel, l_datMailAddressUpdatedDate);
      
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (setメールアドレス更新者コード)<BR>
     * メールアドレス更新者コードをセットする。 <BR>
     * <BR>
     * １）　@カラムモデル取得 <BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。 <BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数] <BR>
     * 　@項目ラベル：　@メールアドレスCSV.メールアドレスコードラベル <BR>
     * <BR>
     * ２）　@値セット <BR>
     * 　@this.set項目値()にて項目値をセットする。 <BR>
     * <BR>
     * 　@[set項目値()に指定する引数] <BR>
     * 　@行番号：　@引数の行番号 <BR>
     * 　@カラム：　@１）で取得したカラムモデル <BR>
     * 　@値：　@メールアドレス更新者コード<BR>
     * @@param l_intLineNumber - 行番号
     * @@param l_strMailAddressUpdaterCode - パスワード更新者コード
     * @@roseuid 4147E7B60269
     */
    public void setMailAddressUpdaterCode(int l_intLineNumber, String l_strMailAddressUpdaterCode) 
    {
        final String STR_METHOD_NAME = " setMailAddressUpdaterCode(int, String)";
        log.entering(STR_METHOD_NAME);

        //カラムモデル取得
        WEB3GentradeCsvColumnModel l_columnModel = getColumnModel(MAIL_ADDRESS_UPDATER_CODE_LABEL);
        
        //値セット
        setValue(l_intLineNumber, l_columnModel, l_strMailAddressUpdaterCode);
      
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (送信フラグをセットする。)<BR>
     * 送信フラグをセットする。 <BR>
     * <BR>
     * １）　@カラムモデル取得<BR> 
     *　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR> 
     * <BR>
     *　@[getカラムモデル()に指定する引数]<BR> 
     *　@項目ラベル：　@メールアドレスCSV.送信フラグラベル<BR> 
     *<BR>
     * ２）　@値セット<BR> 
     *　@this.set項目値()にて項目値をセットする。<BR> 
     *<BR>
     *　@[set項目値()に指定する引数]<BR> 
     *　@行番号：　@引数の行番号<BR> 
     *　@カラム：　@１）で取得したカラムモデル<BR> 
     *　@値：　@送信フラグ<BR>
     * @@param l_intCode - 行番号
     * @@param l_strSendFlag - 送信フラグ			
     */
    public void setSendFlag(int l_intCode,String l_strSendFlag)
    {
    	//１）　@カラムモデル取得 
		//　@this.getカラムモデル()にてCSVカラムモデルを取得する。

		//　@[getカラムモデル()に指定する引数] 
		//　@項目ラベル：　@メールアドレスCSV.送信フラグラベル 
    	WEB3GentradeCsvColumnModel l_model = 
    		this.getColumnModel(this.Mail_ADDRESS_SEND_FLAG);

		//２）　@値セット 
		//　@this.set項目値()にて項目値をセットする。 

		//　@[set項目値()に指定する引数] 
		//　@行番号：　@引数の行番号 
		//　@カラム：　@１）で取得したカラムモデル 
		//　@値：　@送信フラグ
    	this.setValue(l_intCode,l_model,l_strSendFlag);

    }
}
@
