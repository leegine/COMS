head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.23.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMailAddressUploadCsv.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : メールアドレスアップロードCSV(WEB3AdminAccInfoMailAddressUploadCsv.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/14 凌建平 (中訊) 新規作成
                   2006/03/24 呉艶飛 (中訊) モデルNo.102  
                   2006/04/03 黄建 (中訊) モデルNo.103 
*/

package webbroker3.accountinfo;

import java.sql.Timestamp;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.accountinfo.define.WEB3AccInfoDeleteDivDef;
import webbroker3.accountinfo.define.WEB3AccInfoMailFlagDef;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressUploadConfirmRequest;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.gentrade.WEB3GentradeCsvUploadDataModel;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.AdministratorUploadDao;
import webbroker3.gentrade.data.AdministratorUploadParams;
import webbroker3.gentrade.data.AdministratorUploadRow;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (メールアドレスアップロードCSV)<BR>
 * メールアドレスアップロードCSV<BR>
 * <BR>
 * @@author 凌建平
 * @@version 1.0
 */
public class WEB3AdminAccInfoMailAddressUploadCsv extends WEB3GentradeCsvUploadDataModel
{
    /**
     * ログ出力オブジェクト。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoMailAddressUploadCsv.class);
    
    /**
     * (アップロードファ@イルID)<BR>
     * アップロードファ@イルＩＤ_メールアドレスアップロード <BR>
     * <BR>
     * ※（管理者共通）アップロードテーブル.アップロードファ@イルＩＤに<BR>
     * 格納する文字列 <BR>
     */
    public static final String UPLOAD_FILEID = "メールアドレスアップロード";
    
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
     * (メールアドレスラベル)<BR>
     * 定数定義プロパティ　@”メールアドレス” <BR>
     */
    public static  final String MAIL_ADDRESS_LABEL = "メールアドレス";

    /**
     * (案内メール送信フラグラベル)<BR>
     * 定数定義プロパティ　@”案内メール送信フラグ” <BR>
     */
    public static  final String INFORMATION_MAIL_FLAG_LABEL = "案内メール送信フラグ";

    /**
     * (削除区分)<BR>
     * 定数定義プロパティ　@”削除区分”<BR>
     * 0：メールアドレス、案内メール送信フラグ更新<BR>
     * 1：メールアドレス、案内メール送信フラグ削除<BR>
     */
    public static  final String DELETE_DIV = "削除区分";

    /**
     * (限界行数)<BR>
     * 処理限界件数<BR>
     */
    public static  final Integer LIMIT_LINES = new Integer(500);
    
    /**
     * (アップロード中止)<BR>
     * アップロード中止用コメント<BR>
     */
    public static  final String UPLOAD_TERMINATE = "中止";
    
    /**
     * (メールアドレスアップロードCSV)<BR>
     * コンストラクタ<BR>  
     * <BR>
     * －this.createカラムヘッダ()をコールする。<BR>
     * @@roseuid 4147E7B6026C
     */
    public WEB3AdminAccInfoMailAddressUploadCsv() 
    {
        final String STR_METHOD_NAME = "WEB3AdminAccInfoMailAddressUploadCsv()";
        log.entering(STR_METHOD_NAME);
        
        //this.createカラムヘッダ()をコールする。
        this.createColumnHeader();
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (メールアドレスアップロードCSV)<BR>
     * アップロードデータモデルを生成する。<BR>  
     * <BR>
     * ※中止のときのみ使用。<BR> 
     * [コンストラクタの引数]  <BR>
     * アップロードＩＤ：　@リクエストデータ.アップロードＩＤ<BR>
     * @@param l_lngUploadId - アップロードID
     * @@roseuid 4147E7B6026C
     */
    public WEB3AdminAccInfoMailAddressUploadCsv(long l_lngUploadId) 
    {
        super.administratorUploadId = l_lngUploadId;
    }
    
    /**
     * (createカラムヘッダ)<BR>
     * 以下の通りCSVカラムモデルの配列を生成し、<BR>
     * setカラムヘッダ()にてインスタンスにセットする。<BR> 
     * <BR>
     * [カラムヘッダ配列]<BR>
     * <BR>
     * －　@index = 0 <BR> 
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>  
     * 　@項目ラベル：　@メールアドレスCSV.部店コードラベル<BR> 
     * 　@カラム番号： 0 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR> 
     * 　@日付フォーマット：　@null <BR> 
     * <BR>
     * －　@index = 1 <BR> 
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>  
     * 　@項目ラベル：　@メールアドレスCSV.顧客コードラベル<BR>  
     * 　@カラム番号： 1 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>  
     * 　@日付フォーマット：　@null<BR>  
     * <BR>
     * －　@index = 2 <BR> 
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>  
     * 　@項目ラベル：　@メールアドレスCSV.メールアドレスラベル<BR>  
     * 　@カラム番号： 2 <BR> 
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>  
     * 　@日付フォーマット：　@null<BR>  
     * <BR>
     * －　@index = 3 <BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>  
     * 　@項目ラベル：　@メールアドレスCSV.案内メール送信フラグラベル<BR>  
     * 　@カラム番号： 3 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>  
     * 　@日付フォーマット：　@null<BR>  
     * <BR>
     * －　@index = 4 <BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>  
     * 　@項目ラベル：　@メールアドレスCSV.削除区分ラベル<BR>  
     * 　@カラム番号： 4 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>  
     * 　@日付フォーマット：　@null<BR> 
     * <BR>
     * @@roseuid 4147E7B6024D
     */
    protected void createColumnHeader() 
    {
        final String STR_METHOD_NAME = " createColumnHeader()";
        log.entering(STR_METHOD_NAME );

        final int COLUMN_MAX = 5;
        columnHeader = new WEB3GentradeCsvColumnModel[COLUMN_MAX];
                
        //index = 0
        // [CSVカラムモデル コンストラクタの引数]
        // 　@項目ラベル：　@メールアドレスCSV.部店コードラベル
        // 　@カラム番号： 0 
        // 　@項目型：　@CSVカラムモデル.項目型_文字列
        // 　@日付フォーマット：　@null 
        columnHeader[0] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminAccInfoMailAddressUploadCsv.BRANCH_CODE_LABEL,
                0,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);
        
        //index =1
        // [CSVカラムモデル コンストラクタの引数]
        // 　@項目ラベル：　@メールアドレスCSV.顧客コードラベル
        // 　@カラム番号： 1 
        // 　@項目型：　@CSVカラムモデル.項目型_文字列
        // 　@日付フォーマット：　@null
        columnHeader[1] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminAccInfoMailAddressUploadCsv.ACCOUNT_CODE_LABEL,
                1,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);
        
        //index =2
        // [CSVカラムモデル コンストラクタの引数]
        // 　@項目ラベル：　@メールアドレスCSV.メールアドレスラベル
        // 　@カラム番号： 2 
        // 　@項目型：　@CSVカラムモデル.項目型_文字列
        // 　@日付フォーマット：　@null
        columnHeader[2] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminAccInfoMailAddressUploadCsv.MAIL_ADDRESS_LABEL,
                2,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);
        
        //index =3
        // [CSVカラムモデル コンストラクタの引数]
        // 　@項目ラベル：　@メールアドレスCSV.案内メール送信フラグラベル
        // 　@カラム番号： 3 
        // 　@項目型：　@CSVカラムモデル.項目型_文字列
        // 　@日付フォーマット：　@null
        columnHeader[3] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminAccInfoMailAddressUploadCsv.INFORMATION_MAIL_FLAG_LABEL,
                3,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);
        
        //index =4
        // [CSVカラムモデル コンストラクタの引数]
        // 　@項目ラベル：　@メールアドレスCSV.削除区分ラベル
        // 　@カラム番号： 4 
        // 　@項目型：　@CSVカラムモデル.項目型_文字列
        // 　@日付フォーマット：　@null
        columnHeader[4] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminAccInfoMailAddressUploadCsv.DELETE_DIV,
                4,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get顧客コード)<BR>
     * 行番号に対応する明細行の顧客コードを取得する。<BR>  
     * <BR>
     * this.get項目値()にて顧客コードを取得し返却する。<BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>  
     * 行番号：　@行番号 <BR> 
     * カラム：　@getカラムモデル(メールアドレスアップロードCSV.顧客コードラベル)の戻り値。<BR> 
     * <BR>
     * @@param l_intLineNumber - 行番号
     * @@return String
     * @@roseuid 4147E7B6025D
     */
    public String getAccountCode(int l_intLineNumber) 
    {
        final String STR_METHOD_NAME = " getAccountCode(int)";
        log.entering(STR_METHOD_NAME );
        
        String l_strAccountCode = (String)this.getValue(
            l_intLineNumber, 
            this.getColumnModel(WEB3AdminAccInfoMailAddressUploadCsv.ACCOUNT_CODE_LABEL));
        
        log.exiting(STR_METHOD_NAME);
        return l_strAccountCode;
    }

    /**
     * (get部店コード)<BR>
     * 行番号に対応する明細行の部店コードを取得する。<BR>  
     * <BR>
     * this.get項目値()にて部店コードを取得し返却する。<BR>  
     * <BR>
     * [get項目値()に指定する引数]<BR>  
     * 行番号：　@行番号 <BR> 
     * カラム：　@getカラムモデル(メールアドレスアップロードCSV.部店コードラベル)の戻り値。<BR> 
     * <BR>
     * @@param l_intLineNumber - 行番号
     * @@return String
     * @@roseuid 4147E7B6025D
     */
    public String getBranchCode(int l_intLineNumber) 
    {
        final String STR_METHOD_NAME = " getBranchCode(int)";
        log.entering(STR_METHOD_NAME );
        
        String l_strBranchCode = (String)this.getValue(
                l_intLineNumber, 
                this.getColumnModel(WEB3AdminAccInfoMailAddressUploadCsv.BRANCH_CODE_LABEL)); 
        
        log.exiting(STR_METHOD_NAME);
        return l_strBranchCode;
    }
    
    /**
     * (getメールアドレス)<BR>
     * 行番号に対応する明細行のメールアドレスを取得する。<BR>  
     * <BR>
     * this.get項目値()にてメールアドレスを取得し返却する。<BR>  
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@行番号 <BR>
     * カラム：　@getカラムモデル(メールアドレスアップロードCSV.メールアドレスラベル)の戻り値。<BR> 
     * <BR>
     * @@param l_intLineNumber - 行番号
     * @@return String
     * @@roseuid 4147E7B6025D
     */
    public String getMailAddress(int l_intLineNumber) 
    {
        final String STR_METHOD_NAME = " getMailAddress(int)";
        log.entering(STR_METHOD_NAME );
        
        String l_strMailAddress = (String)this.getValue(
                l_intLineNumber, 
                this.getColumnModel(WEB3AdminAccInfoMailAddressUploadCsv.MAIL_ADDRESS_LABEL)); 
        
        log.exiting(STR_METHOD_NAME);
        return l_strMailAddress;
    }
    
    /**
     * (get案内メール送信フラグ)<BR>
     * 行番号に対応する明細行の案内メール送信フラグを取得する。<BR>  
     * <BR>
     * this.get項目値()にて案内メール送信フラグを取得し返却する。<BR>  
     * <BR>
     * [get項目値()に指定する引数] <BR> 
     * 行番号：　@行番号  <BR>
     * カラム：　@getカラムモデル(メールアドレスアップロードCSV.案内メール送信フラグラベル)の戻り値。<BR> 
     * <BR>
     * @@param l_intLineNumber - 行番号
     * @@return String
     * @@roseuid 4147E7B6025D
     */
    public String getInformationMailFlag(int l_intLineNumber) 
    {
        final String STR_METHOD_NAME = " getInformationMailFlag(int)";
        log.entering(STR_METHOD_NAME );
        
        String l_strInnerMailSendFlag = (String)this.getValue(
                l_intLineNumber, 
                this.getColumnModel(WEB3AdminAccInfoMailAddressUploadCsv.INFORMATION_MAIL_FLAG_LABEL)); 
        
        log.exiting(STR_METHOD_NAME);
        return l_strInnerMailSendFlag;
    }
    
    /**
     * (get削除区分)<BR>
     * 行番号に対応する明細行の削除区分を取得する。<BR>  
     * <BR>
     * this.get項目値()にて削除区分を取得し返却する。<BR>  
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@行番号 <BR>
     * カラム：　@getカラムモデル(メールアドレスアップロードCSV.削除区分ラベル)の戻り値。<BR> 
     * <BR>
     * @@param l_intLineNumber - 行番号
     * @@return String
     * @@roseuid 4147E7B6025D
     */
    public String getDeleteDiv(int l_intLineNumber) 
    {
        final String STR_METHOD_NAME = " getDeleteDiv(int)";
        log.entering(STR_METHOD_NAME );
        
        String l_strDeleteDiv = (String)this.getValue(
                l_intLineNumber, 
                this.getColumnModel(WEB3AdminAccInfoMailAddressUploadCsv.DELETE_DIV)); 
        
        log.exiting(STR_METHOD_NAME);
        return l_strDeleteDiv;
    }
    
    /**
     * (getアップロードファ@イルID)<BR>
     * メールアドレスアップロードCSV.アップロードファ@イルＩＤを返却する。<BR> 
     * <BR>
     * ※（管理者共通）アップロードテーブル.アップロードファ@イルＩＤに格納する文字列<BR>
     * <BR>
     * @@return String
     * @@roseuid 4147E7B6025D
     */
    public String getUploadFileId() 
    {

        //メールアドレスアップロードCSV.アップロードファ@イルＩＤを返却する。
    	return WEB3AdminAccInfoMailAddressUploadCsv.UPLOAD_FILEID;
    }
    
    /**
     * (check明細行数)<BR>
     * 明細行数のチェックを行う。<BR>  
     * <BR>
     * リクエストデータ.uploadFile.lengthで取得した明細行数をチェックする。<BR> 
     * <BR>
     * 限界件数よりも明細行数が大きい場合、例外をスローする。<BR>  
     * <BR>
     * 例外をスローした場合、以下のメッセージを表示する。<BR> 
     * 『レコード件数が処理限界値を超えています。』（エラーコード：BUSINESS_ERROR_02418）<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02418<BR>
     * <BR>
     * @@param l_request - リクエストデータ
     * @@throws WEB3BaseException
     * @@roseuid 4147E7B6025D
     */
    public void checkDetailLines(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " checkDetailLines(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME );
        
        if (l_request instanceof WEB3AdminAccInfoMailAddressUploadConfirmRequest)
        {
            String[] uploadFile = 
                ((WEB3AdminAccInfoMailAddressUploadConfirmRequest)l_request).uploadFile;
            if (uploadFile.length > LIMIT_LINES.intValue())
            {
                log.exiting(STR_METHOD_NAME);
                log.debug("レコード件数が処理限界値を超えています。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02418,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "レコード件数が処理限界値を超えています。");
            }            
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate重複顧客)<BR>
     * 重複顧客が追加されていないかチェックを行う。<BR>  
     * <BR>
     * get部店コード（行番号），get顧客コード(行番号)にて、指定行番号の部店コード，顧客コードを取得する。<BR>  
     * 取得した部店コード，顧客コードと指定行番号より前の明細行の部店コード，顧客コードを比較する。<BR> 
     * 同じ値の行（部店コード == 部店コード && 顧客コード == 顧客コード）が存在する場合は、<BR>
     * 顧客が重複して登録されていると判定し、例外をスローする。<BR>  
     * <BR>
     * 例外をスローした場合、以下のメッセージを表示する。<BR> 
     * 『重複顧客チェックエラー（同じ値の行が存在する）』（エラーコード：BUSINESS_ERROR_00517）<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00517<BR>
     * <BR>
     * @@param l_intLineNumber - 行番号
     * @@throws WEB3BaseException
     * @@roseuid 4147E7B6025D
     */
    public void validateDuplicateAccount(int l_intLineNumber) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateDuplicateAccount(int l_intLineNumber)";
        log.entering(STR_METHOD_NAME);
        
        //get顧客コード(番号)にて、指定行番号の顧客コードを取得する
        String l_strAccountCode = this.getAccountCode(l_intLineNumber);
        String l_strBranchCode = this.getBranchCode(l_intLineNumber);
        for(int i = 0; i < l_intLineNumber; i++)
        {        
                
            String l_strAccCode = getAccountCode(i);
            String l_strBraCode = getBranchCode(i);
            if(l_strAccountCode.equals(l_strAccCode)
                && l_strBranchCode.equals(l_strBraCode))
            {
                log.exiting(STR_METHOD_NAME);  
                log.debug("重複顧客チェックエラー（同じ値の行が存在する）。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00517,
                    getClass().getName() + STR_METHOD_NAME,
                    "重複顧客チェックエラー（同じ値の行が存在する）。");                
            }
        }
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (validate明細行)<BR>
     * 明細行のチェックを行う。<BR> 
     * <BR>
     * １）　@部店コードのチェック <BR>
     * 　@get部店コード()にて、指定行番号のデータを取得しチェックを行う。<BR> 
     * <BR>
     * 　@[get部店コード()に指定する引数] <BR>
     * 　@行番号：　@行番号 <BR>
     * <BR>
     * 　@１－１）　@部店コードが取得できない場合（get部店コード() == null）、<BR> 
     * 　@　@　@　@　@　@例外をスローする。 <BR>
     * 　@１－２）　@半角数字以外が含まれる場合は、例外をスローする。 <BR>
     * 　@１－３）　@文字数が3byteでない場合は、例外をスローする。 <BR>
     * 　@１－４）　@例外をスローした場合、以下のメッセージを表示する。 <BR>
     * 　@　@　@　@　@　@『部店コード・顧客コードが不正です。』（エラーコード：BUSINESS_ERROR_02414）<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02414<BR>
     * <BR>
     * ２）　@顧客コードのチェック <BR>
     * 　@get顧客コード()にて、指定行番号のデータを取得しチェックを行う。<BR> 
     * <BR>
     * 　@[get顧客コード()に指定する引数] <BR>
     * 　@行番号：　@行番号 <BR>
     * <BR>
     * 　@２－１）　@顧客コードが取得できない場合（get顧客コード() == null）、<BR> 
     *             例外をスローする。<BR> 
     * 　@２－２）　@半角数字以外が含まれる場合は、例外をスローする。<BR> 
     * 　@２－３）　@文字数が6byteでない場合は、例外をスローする。 <BR>
     * 　@２－４）　@this.get顧客()をコールする。※顧客コードチェック <BR>
     * 　@　@[get顧客()に指定する引数] <BR>
     * 　@　@行番号：　@行番号 <BR>
     * 　@　@証券会社コード：　@証券会社コード <BR>
     * <BR>
     * 　@２－５）　@例外をスローした場合、以下のメッセージを表示する。 <BR>
     * 　@　@　@　@　@　@『部店コード・顧客コードが不正です。』（エラーコード：BUSINESS_ERROR_02414）<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02414<BR>
     * <BR>
     * ３）　@削除区分のチェック <BR>
     * 　@get削除区分()にて、指定行番号のデータを取得しチェックを行う。<BR> 
     * <BR>
     * 　@[get削除区分()に指定する引数] <BR>
     * 　@行番号：　@行番号 <BR>
     * <BR>
     * 　@３－１）　@削除区分が取得できない場合（get削除区分() == null）、<BR> 
     *             例外をスローする。 <BR>
     * 　@３－２）　@半角文字以外が含まれる場合は、例外をスローする。 <BR>
     * 　@３－３）　@文字数が1byteより大きい場合は、例外をスローする。<BR> 
     * 　@３－４）　@取得した文字が『0』、『1』以外の場合は、例外をスローする。<BR> 
     * 　@３－５）　@例外をスローした場合、以下のメッセージを表記する。 <BR>
     * 　@　@　@　@　@　@『削除区分が不正です。』（エラーコード：BUSINESS_ERROR_02417）<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02417<BR> 
     * <BR>
     * ４）　@更新対象チェック <BR>
     * <BR>
     * 　@get削除区分()にて、取得した値が『0』の場合以下のチェックを行う。<BR> 
     * <BR>
     * 　@getメールアドレス()にて、指定行番号のデータを取得する。<BR> 
     * 　@get案内メール送信フラグ()にて、指定行番号のデータを取得する。<BR> 
     * <BR>
     * 　@[getメールアドレス()、get案内メール送信フラグ()に指定する引数] <BR>
     * 　@行番号：　@行番号 <BR>
     * <BR>
     * 　@４－１）　@メールアドレス、案内メール送信フラグ共に取得できない場合 <BR>
     * 　@　@　@　@　@　@（getメールアドレス() == null && get案内メール送信フラグ() == null）、<BR> 
     * 　@　@　@　@　@　@例外をスローする。 <BR>
     * 　@４－２）　@メールアドレスが取得できた場合（getメールアドレス() != null）、<BR> 
     * 　@　@　@　@　@　@以下の処理を行う。 <BR>
     * 　@４－２－１）　@システム実装クラス.isMailAddress <BR>
     * 　@　@　@　@　@　@　@　@（文字列が、メールアドレスに適切な文字(*1)で構成されているかをチェックする。<BR> 
     * 　@　@　@　@　@　@　@　@適切である場合はtrueを、そうでない場合はfalseを返す。 <BR>
     * <BR>
     * 　@　@　@　@　@　@　@　@(*1) メールアドレスに適切な文字 <BR>
     * 　@　@　@　@　@　@　@　@以下の半角文字が使用可能で構成され、’@@’が１つ含まれていること。<BR> 
     * <BR>
     * 　@　@　@　@　@　@　@　@0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!#$&|^_.()-=~[ ]+?/* <BR>
     * <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@また、’@@’は先頭文字，末尾文字ではないこと。） <BR>
     * 　@　@　@　@　@　@　@　@　@を行い、返却値がfalseの場合、例外をスローする。 <BR>
     * <BR>
     * 　@４－２－２）　@文字数が100byteより大きい場合は、例外をスローする。<BR> 
     * 　@４－２－３）　@例外をスローした場合、以下のメッセージを表示する。 <BR>
     * 　@　@　@　@　@　@　@　@　@『メールアドレスが不正です。』（エラーコード：BUSINESS_ERROR_02415）<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02415<BR>  
     * <BR>
     * 　@４－３）　@案内メール送信フラグが取得できた場合 <BR>
     * 　@　@　@　@　@　@（get案内メール送信フラグ() != null）、以下の処理を行う。<BR> 
     * 　@４－３－１）　@半角文字以外が含まれる場合は、例外をスローする。 <BR>
     * 　@４－３－２）　@文字数が1byteより大きい場合は、例外をスローする。 <BR>
     * 　@４－３－３）　@取得した文字が『0』、『1』以外の場合は、例外をスローする。<BR> 
     * 　@４－３－４）　@例外をスローした場合、以下のメッセージを表示する。 <BR>
     * 　@　@　@　@　@　@　@　@　@『案内メール送信フラグが不正です。』（エラーコード：BUSINESS_ERROR_02416）<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02416<BR>
     * <BR>
     * @@param l_intLineNumber - 行番号
     * @@param l_strInstitutionCode - 証券会社コード
     * @@throws WEB3BaseException
     * @@roseuid 4147E7B6025D
     */
    public void validateDetailLine(int l_intLineNumber, String l_strInstitutionCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateDetailsLine(int l_intLineNumber, String l_strInstitutionCode)";
        log.entering(STR_METHOD_NAME);
        
        //get部店コード()にて、指定行番号のデータを取得しチェックを行う
        String l_strBranchCode = this.getBranchCode(l_intLineNumber);
        
        //１－１）　@部店コードが取得できない場合（get部店コード() == null）、例外をスローする
        if(WEB3StringTypeUtility.isEmpty(l_strBranchCode))
        {            
            log.exiting(STR_METHOD_NAME);
            log.debug("部店コード・顧客コードが不正です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02414,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "部店コード・顧客コードが不正です。");
        }
        
        //１－２）　@半角数字以外が含まれる場合は、例外をスローする。
        if(!WEB3StringTypeUtility.isDigit(l_strBranchCode))
        {            
            log.exiting(STR_METHOD_NAME);
            log.debug("部店コード・顧客コードが不正です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02414,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "部店コード・顧客コードが不正です。");
        }
        
        //１－３）　@文字数が3byteでない場合は、例外をスローする。
        if(WEB3StringTypeUtility.getByteLength(l_strBranchCode) != 3)
        {            
            log.exiting(STR_METHOD_NAME);
            log.debug("部店コード・顧客コードが不正です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02414,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "部店コード・顧客コードが不正です。");
        }
        
        //get顧客コード()にて、指定行番号のデータを取得しチェックを行う
        String l_strAccountCode = this.getAccountCode(l_intLineNumber);
        
        //２－１）　@顧客コードが取得できない場合（get顧客コード() == null）、例外をスローする
        if(WEB3StringTypeUtility.isEmpty(l_strAccountCode))
        {            
            log.exiting(STR_METHOD_NAME);
            log.debug("部店コード・顧客コードが不正です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02414,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "部店コード・顧客コードが不正です。");
        }
        
        //２－２）　@半角数字以外が含まれる場合は、例外をスローする。
        if(!WEB3StringTypeUtility.isDigit(l_strAccountCode))
        {            
            log.exiting(STR_METHOD_NAME);
            log.debug("部店コード・顧客コードが不正です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02414,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "部店コード・顧客コードが不正です。");
        }
        
        //２－３）　@文字数が6byteでない場合は、例外をスローする。
        if(WEB3StringTypeUtility.getByteLength(l_strAccountCode) != 6)
        {            
            log.exiting(STR_METHOD_NAME);
            log.debug("部店コード・顧客コードが不正です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02414,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "部店コード・顧客コードが不正です。");
        }

        //２－４）　@this.get顧客()をコールする。※顧客コードチェック
        //　@　@[get顧客()に指定する引数] 
        // 　@　@行番号：　@行番号 
        //　@　@証券会社コード：　@証券会社コード 
        try
        {
            this.getAccount(l_intLineNumber, l_strInstitutionCode);
        }
        catch (WEB3BaseException l_ex)
        {
        // 　@２－５）　@例外をスローした場合、以下のメッセージを表示する。 
        //　@　@　@　@　@　@『部店コード・顧客コードが不正です。』（エラーコード：BUSINESS_ERROR_02414）
        //         class: WEB3BusinessLayerException
        //         tag:   BUSINESS_ERROR_02414            
            log.exiting(STR_METHOD_NAME);
            log.error("部店コード・顧客コードが不正です。",l_ex);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02414,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "部店コード・顧客コードが不正です。");
        }
        
        //get削除区分()にて、指定行番号のデータを取得しチェックを行う。
        String l_strDeleteDiv = this.getDeleteDiv(l_intLineNumber);
        
        //３－１）　@削除区分が取得できない場合（get削除区分() == null）、例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(l_strDeleteDiv))
        {
            log.exiting(STR_METHOD_NAME);
            log.debug("削除区分が不正です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02417,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "削除区分が不正です。");
        }
        
        //３－２）　@半角文字以外が含まれる場合は、例外をスローする。
        if (!WEB3StringTypeUtility.isSingle(l_strDeleteDiv))
        {
            log.exiting(STR_METHOD_NAME);
            log.debug("削除区分が不正です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02417,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "削除区分が不正です。");
        }
        
        //３－３）　@文字数が1byteより大きい場合は、例外をスローする。
        if(WEB3StringTypeUtility.getByteLength(l_strDeleteDiv) > 1)
        {            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02417,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "削除区分が不正です。");
        }
        
        //３－４）　@取得した文字が『0』、『1』以外の場合は、例外をスローする。
        if (!WEB3AccInfoDeleteDivDef.INFORMATION_MAIL_FLAG_UPDATE.equals(l_strDeleteDiv)
            && !WEB3AccInfoDeleteDivDef.INFORMATION_MAIL_FLAG_DELETE.equals(l_strDeleteDiv))
        {
            log.exiting(STR_METHOD_NAME);
            log.debug("削除区分が不正です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02417,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "削除区分が不正です。");
        }
        
        //get削除区分()にて、取得した値が『0』の場合以下のチェックを行う。
        if (WEB3AccInfoDeleteDivDef.INFORMATION_MAIL_FLAG_UPDATE.equals(l_strDeleteDiv))
        {
            //getメールアドレス()にて、指定行番号のデータを取得する。
            String l_strMailAddress = this.getMailAddress(l_intLineNumber);           
            //get案内メール送信フラグ()にて、指定行番号のデータを取得する。
            String l_strInnerMailSendFlag = this.getInformationMailFlag(l_intLineNumber);
            
            //４－１）　@メールアドレス、案内メール送信フラグ共に取得できない場合 
            //（getメールアドレス() == null && get案内メール送信フラグ() == null）、
            //例外をスローする。
            if (WEB3StringTypeUtility.isEmpty(l_strMailAddress) && 
                WEB3StringTypeUtility.isEmpty(l_strInnerMailSendFlag))
            {
                log.exiting(STR_METHOD_NAME);
                log.debug("メールアドレスが不正です。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02415,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "メールアドレスが不正です。");
            }
            
            //４－２）　@メールアドレスが取得できた場合（getメールアドレス() != null）、以下の処理を行う。
            if (!WEB3StringTypeUtility.isEmpty(l_strMailAddress))
            {
                //４－２－１）　@システム実装クラス.isMailAddress 
                //文字列が、メールアドレスに適切な文字(*1)で構成されているかをチェックする。
                //適切である場合はtrueを、そうでない場合はfalseを返す。
                if (!WEB3StringTypeUtility.isMailAddress(l_strMailAddress))
                {
                    log.exiting(STR_METHOD_NAME);
                    log.debug("メールアドレスが不正です。");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02415,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "メールアドレスが不正です。");
                }
                
                //４－２－２）　@文字数が100byteより大きい場合は、例外をスローする。
                if (WEB3StringTypeUtility.getByteLength(l_strMailAddress) > 100)
                {
                    log.exiting(STR_METHOD_NAME);
                    log.debug("メールアドレスが不正です。");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02415,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "メールアドレスが不正です。");
                }
            }
            
            //４－３）　@案内メール送信フラグが取得できた場合（get案内メール送信フラグ() != null）、
            //以下の処理を行う。
            if (!WEB3StringTypeUtility.isEmpty(l_strInnerMailSendFlag))
            {
                //４－３－１）　@半角文字以外が含まれる場合は、例外をスローする。
                if (!WEB3StringTypeUtility.isSingle(l_strInnerMailSendFlag))
                {
                    log.exiting(STR_METHOD_NAME);
                    log.debug("案内メール送信フラグが不正です。");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02416,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "案内メール送信フラグが不正です。");
                }
                
                //４－３－２）　@文字数が1byteより大きい場合は、例外をスローする。
                if (WEB3StringTypeUtility.getByteLength(l_strInnerMailSendFlag) > 1)
                {
                    log.exiting(STR_METHOD_NAME);
                    log.debug("案内メール送信フラグが不正です。");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02416,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "案内メール送信フラグが不正です。");
                }
                
                //４－３－３）　@取得した文字が『0』、『1』以外の場合は、例外をスローする。
                if (!WEB3AccInfoMailFlagDef.FALSE.equals(l_strInnerMailSendFlag) 
                    && !WEB3AccInfoMailFlagDef.TRUE.equals(l_strInnerMailSendFlag))
                {
                    log.exiting(STR_METHOD_NAME);
                    log.debug("案内メール送信フラグが不正です。");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02416,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "案内メール送信フラグが不正です。");
                }
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get顧客)<BR>
     * 行番号に対応する明細行の顧客オブジェクトを取得する。<BR> 
     * <BR>
     * アカウントマネージャ.get顧客()にて顧客オブジェクトを取得し返却する。<BR> 
     * <BR>
     * [get顧客()に指定する引数] <BR>
     * 証券会社コード：　@証券会社コード <BR>
     * 部店コード：　@this.get部店コード(行番号)<BR>
     * 口座コード：　@this.get顧客コード(行番号)<BR>
     * <BR>
     * @@param l_intLineNumber - 行番号
     * @@param l_strInstitutionCode - 証券会社コード
     * @@return MainAccount - 顧客
     * @@throws WEB3BaseException
     * @@roseuid 4147E7B6025D
     */
    public MainAccount getAccount(int l_intLineNumber, String l_strInstitutionCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " getMainAccount(int l_intLineNumber, String l_strInstitutionCode)";
        log.entering(STR_METHOD_NAME );
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accManager = 
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            
        WEB3GentradeMainAccount l_mainAccount = 
            l_accManager.getMainAccount(
                l_strInstitutionCode,
                this.getBranchCode(l_intLineNumber),
                this.getAccountCode(l_intLineNumber));
                
        log.exiting(STR_METHOD_NAME);
        return l_mainAccount;
    }
    
    /**
     * (getアップロード最新履歴)<BR>
     * 当該アップロードファ@イルに関連するアップロード最新履歴を取得する。<BR> 
     * <BR>
     * 以下の条件で「（管理者共通）アップロードテーブル」を検索し、<BR> 
     * 取得した行オブジェクトを返却する。 <BR>
     * レコードがない場合はnullを返却する。 <BR>
     * <BR>
     * [条件] <BR>
     * （管理者共通）アップロードテーブル.証券会社コード = this.get証券会社コード() <BR>
     * （管理者共通）アップロードテーブル.アップロードファ@イルＩＤ = this.getアップロードファ@イルＩＤ()<BR> 
     * （管理者共通）アップロードテーブル.銘柄タイプ = this.get銘柄タイプ() <BR>
     * （管理者共通）アップロードテーブル.データキー = 引数.データキー <BR>
     * （管理者共通）アップロードテーブル.備考１ != "中止" <BR>
     *   ※備考１を比較する時、項目がNULLの場合、文字列で"NULL"と <BR>
     *      置き換えてから比較する。(SQL文：nvl(note1,'NULL') != '中止') <BR>
     * <BR>
     * [取得順] <BR>
     * アップロード開始日時　@降順（：desc）<BR>
     * <BR>
     * @@param l_lngUploadKey - データキー
     * @@return AdministratorUploadRow -（管理者共通）アップロード行
     * @@roseuid 4147E7B6025D
     */
    public AdministratorUploadRow getUploadNewHistory(long l_lngUploadKey) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getUploadNewHistory(long)";
        log.entering(STR_METHOD_NAME );
        List l_lisRecords;
        String l_strInstitutionCode = null;          //証券会社コード
        String l_strUploadFileId = null;             //アップロードファ@イルＩＤ
        ProductTypeEnum l_productTypeEnum = null;    //銘柄タイプ
        
        Long l_lngUpKey = new Long(l_lngUploadKey);  //引数.データキー
              
        //「（管理者共通）アップロードテーブル」を検索
        try
        {
            l_strInstitutionCode = this.getInstitutionCode();
            l_strUploadFileId = this.getUploadFileId();
            l_productTypeEnum = this.getProductType();

            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" institution_code = ? ");            //証券会社コード
            l_sbWhere.append(" and upload_file_id = ? ");          //アップロードファ@イルＩＤ
            l_sbWhere.append(" and product_type = ? ");            //銘柄タイプ
            l_sbWhere.append(" and upload_key = ? ");
            l_sbWhere.append(" and nvl(note1,'NULL') != ? ");
            
            //データキー
            Object[] l_objAdministratorUploadWhere = { 
                l_strInstitutionCode, //this.get証券会社コード()
                l_strUploadFileId,    //this.getアップロードファ@イルＩＤ()
                l_productTypeEnum,    //銘柄タイプ = this.get銘柄タイプ()
                l_lngUpKey,           //データキー
                UPLOAD_TERMINATE      //"中止"  
                }; 

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords =
                l_queryProcessor.doFindAllQuery(
                    AdministratorUploadRow.TYPE,
                    l_sbWhere.toString(),
                    "upload_start_timestamp desc",
                    null,
                    l_objAdministratorUploadWhere);
        }
        catch (DataException de)
        {
            log.exiting(STR_METHOD_NAME);
            log.debug(de.getMessage(), de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        
        //レコードがない場合はnullを返却する。
        if (l_lisRecords == null || l_lisRecords.isEmpty())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        //取得した行オブジェクトを返却する。
        AdministratorUploadRow l_uploadRow = 
            (AdministratorUploadRow)l_lisRecords.get(0); 
        
        log.exiting(STR_METHOD_NAME);
        return l_uploadRow;
    }
    
    /**
     * (get銘柄タイプ)<BR>
     * 銘柄タイプ.その他を返却する。<BR>
     * @@return ProductTypeEnum
     * @@roseuid 42C4E176008F
     */
    public ProductTypeEnum getProductType() 
    {
        return ProductTypeEnum.OTHER;
    }
    
    /**
     * (updateアップロード中止)<BR>
     * 該当アップロード行にアップロード中止を更新する。<BR> 
     * <BR>
     * this.getアップロードＩＤ()に該当する行について <BR>
     * 以下の通り、（管理者共通）アップロードテーブルを更新する。<BR> 
     * <BR>
     * 　@アップロード終了日時 = System.currentTimeMillis()<BR> 
     * 　@アップロード件数 = 0 <BR>
     * 　@備考１ = "中止" <BR>
     * <BR>
     * ※該当データがなくても、例外を上位にスローしない。<BR>
     * @@throws WEB3BaseException
     */
    public void updateUploadEnd() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateUploadEnd()";
        log.entering(STR_METHOD_NAME);
        
        AdministratorUploadRow l_administratorUploadRow = null;
        try
        {
            //this.getアップロードＩＤ()に該当する行について
            //以下の通り、（管理者共通）アップロードテーブルを更新する。
            l_administratorUploadRow = (AdministratorUploadRow)
                AdministratorUploadDao.findRowByAdministratorUploadId(
                	this.getAdministratorUploadId());
            
            if(l_administratorUploadRow == null)
            {
                log.exiting(STR_METHOD_NAME);
                return;
            }    
        }
        catch (DataException l_exp)
        {
            log.debug(l_exp.getMessage(), l_exp);
        	
        	log.exiting(STR_METHOD_NAME);
            return;
        }
            
        AdministratorUploadParams l_administratorUploadParams = 
            new AdministratorUploadParams(l_administratorUploadRow);

        //アップロード終了日時 = System.currentTimeMillis()
        Timestamp l_tsSystemTime = GtlUtils.getSystemTimestamp();
        l_administratorUploadParams.setUploadEndTimestamp(l_tsSystemTime);

        ///アップロード件数 = 0
        l_administratorUploadParams.setUploadCount(0);

        //備考１ = "中止"
        l_administratorUploadParams.setNote1(WEB3AdminAccInfoMailAddressUploadCsv.UPLOAD_TERMINATE);

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateQuery(l_administratorUploadParams);
        }
        catch (DataException l_exp)
        {
            log.error(l_exp.getMessage(), l_exp);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_exp.getMessage(),
                l_exp);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
