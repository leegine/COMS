head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.23.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoExclusiveTransferAccountUploadCsv.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 専用振込先口座アップロードCSV(WEB3AdminAccInfoExclusiveTransferAccountUploadCsv.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/19 カク寛新 (中訊) 新規作成
                   2005/12/26 鄭徳懇 (中訊) 仕様変更No.074
                   2006/02/03 王維（日本中訊）仕様変更No.085
*/

package webbroker3.accountinfo;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.gentrade.WEB3GentradeCsvUploadDataModel;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.FinInstitutionBankDao;
import webbroker3.gentrade.data.FinInstitutionBankRow;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

  
/**
 * (専用振込先口座アップロードCSV)<BR>
 * 専用振込先口座アップロードCSV<BR>
 * @@author カク寛新
 * @@version 1.0 
 */
public class WEB3AdminAccInfoExclusiveTransferAccountUploadCsv extends WEB3GentradeCsvUploadDataModel
{
    /**
     * (ログユーティリティ)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminAccInfoExclusiveTransferAccountUploadCsv.class);
    
    
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
     * (銀行名ラベル)<BR>
     * 定数定義プロパティ　@”銀行名” <BR>
     */
    public static  final String FIN_INSTITUTION_NAME_LABEL = "銀行名";
    
    /**
     * (支店名ラベル)<BR>
     * 定数定義プロパティ　@”支店名” <BR>
     */
    public static  final String FIN_BRANCH_NAME_LABEL = "支店名";
    
    /**
     * (支店コードラベル)<BR>
     * 定数定義プロパティ　@”支店コード” <BR>
     */
    public static  final String FIN_BRANCH_CODE_LABEL = "支店コード";
    
    /**
     * (口座種類名ラベル)<BR>
     * 定数定義プロパティ　@”口座種類名” <BR>
     */
    public static  final String FIN_ACCOUNT_TYPE_NAME_LABEL = "口座種類名";
    
    /**
     * (口座番号ラベル)<BR>
     * 定数定義プロパティ　@”口座番号” <BR>
     */
    public static  final String FIN_ACCOUNT_NO_LABEL = "口座番号";
    
    /**
     * (口座名義人ラベル)<BR>
     * 定数定義プロパティ　@”口座名義人” <BR>
     */
    public static  final String FIN_ACCOUNT_NAME_LABEL = "口座名義人";
    
    /**
     * (アップロードファ@イルＩＤ)<BR>
     * アップロードファ@イルＩＤ_専用振込先口座アップロード <BR>
     * <BR>
     * ※（管理者共通）アップロードテーブル.アップロードファ@イルＩＤに格納する文字列<BR>
     */
    public static  String uploadFileId = "専用振込先口座アップロード";
    
    /**
     * (銀行コードラベル)<BR>
     * 定数定義プロパティ　@”銀行コード” <BR>
     */
    public static  final String FIN_INSTITUTION_CODE_LABEL = "銀行コード";
    
    /**
     * (専用振込先口座アップロードCSV)<BR>
     * コンストラクタ <BR>
     * <BR>
     * －this.createカラムヘッダ()をコールする。 <BR>
     * @@return webbroker3.accountinfo.WEB3AdminAccInfoExclusiveTransferAccountUploadCsv
     * @@roseuid 415B9051037F
     */
    public WEB3AdminAccInfoExclusiveTransferAccountUploadCsv() 
    {
        
        this.createColumnHeader();
    }
    
    /**
     * (専用振込先口座アップロードCSV)<BR>
     * <BR>
     * コンストラクタ <BR>
     * ※　@アップロード中止の場合に使用する。 <BR>
     * <BR>
     * －引数のアップロードIDをプロパティにセットする。 <BR>
     * @@param l_lngUploadId - アップロードID
     * @@return webbroker3.accountinfo.WEB3AdminAccInfoExclusiveTransferAccountUploadCsv
     * @@roseuid 415B90300237
     */
    public WEB3AdminAccInfoExclusiveTransferAccountUploadCsv(long l_lngUploadId) 
    {
        
        this.administratorUploadId = l_lngUploadId;
        this.createColumnHeader();
    }
    
    /**
     * (getアップロードファ@イルＩＤ)<BR>
     * 専用振込先口座アップロードCSV.アップロードファ@イルＩＤを返却する。<BR>
     * <BR>
     * ※（管理者共通）アップロードテーブル.アップロードファ@イルＩＤに格納する文字列<BR>
     * @@return String
     * @@roseuid 415B8E170091
     */
    public String getUploadFileId() 
    {
        
        return uploadFileId;
    }
    
    /**
     * (get銘柄タイプ)<BR>
     * ProductTypeEnum.その他 を返却する。<BR>
     * @@return ProductTypeEnum
     * @@roseuid 415B8F84018B
     */
    public ProductTypeEnum getProductType() 
    {
        
        return ProductTypeEnum.OTHER;
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
     * 　@項目ラベル：　@専用振込先口座CSV.部店コードラベル <BR>
     * 　@カラム番号： 0 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 1 <BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@専用振込先口座CSV.顧客コードラベル <BR>
     * 　@カラム番号： 1 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 2 <BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@専用振込先口座CSV.銀行名ラベル <BR>
     * 　@カラム番号： 2 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 3 <BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@専用振込先口座CSV.支店名ラベル <BR>
     * 　@カラム番号： 3 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 4<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@専用振込先口座CSV.支店コードラベル <BR>
     * 　@カラム番号： 4 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 5<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@専用振込先口座CSV.口座種類名ラベル <BR>
     * 　@カラム番号：5<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 6<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@専用振込先口座CSV.口座番号ラベル <BR>
     * 　@カラム番号： 6 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@ null<BR>
     * <BR>
     * －　@index = 7<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@専用振込先口座CSV.口座名義人ラベル <BR>
     * 　@カラム番号： 7 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@ null<BR>
     * <BR>
     * －　@index = 8<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@専用振込先口座CSV.銀行コードラベル <BR>
     * 　@カラム番号： 8 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null<BR>
     * @@roseuid 415B90700208
     */
    protected void createColumnHeader() 
    {
        
        final String STR_METHOD_NAME = " createColumnHeader()";
        log.entering(STR_METHOD_NAME );

        final int COLUMN_MAX = 9;
        
        WEB3GentradeCsvColumnModel[] l_models = new WEB3GentradeCsvColumnModel[COLUMN_MAX];
        
        //index = 0
        l_models[0] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminAccInfoExclusiveTransferAccountUploadCsv.BRANCH_CODE_LABEL,
                0,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);
        //index = 1
        l_models[1] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminAccInfoExclusiveTransferAccountUploadCsv.ACCOUNT_CODE_LABEL,
                1,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null); 
        //index = 2
        l_models[2] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminAccInfoExclusiveTransferAccountUploadCsv.FIN_INSTITUTION_NAME_LABEL,
                2,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);
        //index = 3
        l_models[3] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminAccInfoExclusiveTransferAccountUploadCsv.FIN_BRANCH_NAME_LABEL,
                3,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);
        //index = 4
        l_models[4] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminAccInfoExclusiveTransferAccountUploadCsv.FIN_BRANCH_CODE_LABEL,
                4,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);
        //index = 5
        l_models[5] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminAccInfoExclusiveTransferAccountUploadCsv.FIN_ACCOUNT_TYPE_NAME_LABEL,
                5,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);
        //index = 6
        l_models[6] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminAccInfoExclusiveTransferAccountUploadCsv.FIN_ACCOUNT_NO_LABEL,
                6,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);
        //index = 7
        l_models[7] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminAccInfoExclusiveTransferAccountUploadCsv.FIN_ACCOUNT_NAME_LABEL,
                7,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);
        //index = 8
        l_models[8] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminAccInfoExclusiveTransferAccountUploadCsv.FIN_INSTITUTION_CODE_LABEL,
                8,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);
                
        this.setColumnHeader(l_models);        
        log.exiting(STR_METHOD_NAME); 
     
    }
    
    /**
     * (get顧客)<BR>
     * 行番号に対応する明細行の顧客オブジェクトを取得する。<BR>
     * <BR>
     * アカウントマネージャ.get顧客()にて顧客オブジェクトを取得し返却する。<BR>
     * <BR>
     * [get顧客()に指定する引数]<BR>
     * 証券会社コード：　@証券会社コード<BR>
     * 部店コード：　@this.get部店コード(行番号)<BR>
     * 口座コード：　@this.get顧客コード(行番号)<BR>
     * @@param l_intLineNumber - 行番号
     * @@param l_strInstitutionCode - 証券会社コード
     * @@return WEB3GentradeMainAccount
     * @@roseuid 415BE81E02A5
     */
    public WEB3GentradeMainAccount getMainAccount(int l_intLineNumber, String l_strInstitutionCode) throws WEB3BaseException
    {
        
        final String STR_METHOD_NAME = " getMainAccount(int l_intLineNumber, String l_strInstitutionCode)";
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
     * (get顧客コード)<BR>
     * 行番号に対応する明細行の顧客コードを取得する。 <BR>
     * <BR>
     * this.get項目値()にて顧客コードを取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@行番号 <BR>
     * カラム：　@getカラムモデル(専用振込先口座アップロードCSV.顧客コードラベル)の戻り値。 <BR>
     * @@param l_intLineNumber - 行番号
     * @@return String
     * @@roseuid 415B91240295
     */
    public String getAccountCode(int l_intLineNumber) 
    {
        
        String l_strAccountCode = (String)this.getValue(
                l_intLineNumber, 
                this.getColumnModel(ACCOUNT_CODE_LABEL)
                );
        return l_strAccountCode;
    }
    
    /**
     * (get部店コード) <BR>
     * 行番号に対応する明細行の部店コードを取得する。 <BR>
     * <BR>
     * this.get項目値()にて部店コードを取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@行番号 <BR>
     * カラム：　@getカラムモデル(専用振込先口座アップロードCSV.部店コードラベル)の戻り値。 <BR>
     * @@param l_intLineNumber - 行番号
     * @@return String
     * @@roseuid 415B915C013D
     */
    public String getBranchCode(int l_intLineNumber) 
    {
        
        String l_strBranchCode = (String)this.getValue(
                l_intLineNumber, 
                this.getColumnModel(BRANCH_CODE_LABEL)); 
        return l_strBranchCode;
    }
    
    /**
     * (get銀行名)<BR>
     * 行番号に対応する明細行の銀行名を取得する。 <BR>
     * <BR>
     * this.get項目値()にて銀行名を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@行番号 <BR>
     * カラム：　@getカラムモデル(専用振込先口座アップロードCSV.銀行名ラベル)の戻り値。 <BR>
     * @@param l_intLineNumber - 行番号
     * @@return String
     * @@roseuid 415B91730276
     */
    public String getFinInstitutionName(int l_intLineNumber) 
    {
        String l_strFinInstitutionName = (String)this.getValue(
                l_intLineNumber, 
                this.getColumnModel(FIN_INSTITUTION_NAME_LABEL)); 
        return l_strFinInstitutionName;
    }
    
    /**
     * (get支店名)<BR>
     * 行番号に対応する明細行の支店名を取得する。 <BR>
     * <BR>
     * this.get項目値()にて支店名を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@行番号 <BR>
     * カラム：　@getカラムモデル(専用振込先口座アップロードCSV.支店名ラベル)の戻り値。 <BR>
     * @@param l_intLineNumber - 行番号
     * @@return String
     * @@roseuid 415B919B0034
     */
    public String getFinBranchName(int l_intLineNumber) 
    {
        
        String l_strFinBranchName = (String)this.getValue(
                l_intLineNumber, 
                this.getColumnModel(FIN_BRANCH_NAME_LABEL)); 
        return l_strFinBranchName;
    }
    
    /**
     * (get支店コード)<BR>
     * 行番号に対応する明細行の支店コードを取得する。 <BR>
     * <BR>
     * this.get項目値()にて支店コードを取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@行番号 <BR>
     * カラム：　@getカラムモデル(専用振込先口座アップロードCSV.支店コードラベル)の戻り値。 <BR>
     * @@param l_intLineNumber - 行番号
     * @@return String
     * @@roseuid 415B91C30208
     */
    public String getFinBranchCode(int l_intLineNumber) 
    {
        
        String l_strFinBranchCode = (String)this.getValue(
                l_intLineNumber, 
                this.getColumnModel(FIN_BRANCH_CODE_LABEL)); 
        return l_strFinBranchCode;
    }
    
    /**
     * (get口座種類名)<BR>
     * 行番号に対応する明細行の口座種類名を取得する。 <BR>
     * <BR>
     * this.get項目値()にて口座種類名を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@行番号 <BR>
     * カラム：　@getカラムモデル(専用振込先口座アップロードCSV.口座種類名ラベル)の戻り値。 <BR>
     * @@param l_intLineNumber - 行番号
     * @@return String
     * @@roseuid 415B91DF018B
     */
    public String getFinAccountTypeName(int l_intLineNumber) 
    {
        
        String l_strFinAccountTypeName = (String)this.getValue(
                l_intLineNumber, 
                this.getColumnModel(FIN_ACCOUNT_TYPE_NAME_LABEL)); 
        return l_strFinAccountTypeName;
    }
    
    /**
     * (get口座番号)<BR>
     * 行番号に対応する明細行の口座番号を取得する。 <BR>
     * <BR>
     * this.get項目値()にて口座番号を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@行番号 <BR>
     * カラム：　@getカラムモデル(専用振込先口座アップロードCSV.口座番号ラベル)の戻り値。 <BR>
     * @@param l_intLineNumber - 行番号
     * @@return String
     * @@roseuid 415B92090295
     */
    public String getFinAccountNo(int l_intLineNumber) 
    {
        
        String l_strFinAccountNo = (String)this.getValue(
                l_intLineNumber, 
                this.getColumnModel(FIN_ACCOUNT_NO_LABEL)); 
        return l_strFinAccountNo;
    }
    
    /**
     * (get口座名義人)<BR>
     * 行番号に対応する明細行の口座名義人を取得する。 <BR>
     * <BR>
     * this.get項目値()にて口座名義人を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@行番号 <BR>
     * カラム：　@getカラムモデル(専用振込先口座アップロードCSV.口座名義人ラベル)の戻り値。 <BR>
     * @@param l_intLineNumber - 行番号
     * @@return String
     * @@roseuid 415B92310266
     */
    public String getFinAccountName(int l_intLineNumber) 
    {
        
        String l_strFinAccountName = (String)this.getValue(
                l_intLineNumber, 
                this.getColumnModel(FIN_ACCOUNT_NAME_LABEL)); 
        return l_strFinAccountName;
    }
    
    /**
     * (get銀行コード)<BR>
     * 行番号に対応する明細行の銀行コードを取得する。 <BR>
     * <BR>
     * this.get項目値()にて銀行コードを取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@行番号 <BR>
     * カラム：　@getカラムモデル <BR>
     * 　@　@　@　@　@(専用振込先口座アップロードCSV.銀行コードラベル)の戻り値。 <BR>
     * @@param l_intLineNumber - 行番号
     * @@return String
     * @@roseuid 415B92310267
     */
    public String getFinInstitutionCode(int l_intLineNumber) 
    {
        
        String l_strFinInstitutionCode = (String) this.getValue(
                l_intLineNumber, 
                this.getColumnModel(FIN_INSTITUTION_CODE_LABEL));
        return l_strFinInstitutionCode;
    }
    
    /**
     * (validate重複顧客)<BR>
     * 重複顧客が追加されていないかチェックを行う。 <BR>
     * <BR>
     * get部店コード（行番号），get顧客コード(行番号)にて、<BR>
     * 指定行番号の部店コード，顧客コードを取得する。 <BR>
     * 取得した部店コード，顧客コードと指定行番号より前の明細行の部店コード，<BR>
     * 顧客コードを比較する。<BR>
     * 同じ値の行（部店コード == 部店コード && 顧客コード == 顧客コード）が存在する場合は、<BR>
     * 顧客が重複して登録されていると判定し、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00517<BR>
     * 
     * @@param l_intLineNumber - 行番号
     * @@throws WEB3BaseException
     * @@roseuid 415B92CF02A5
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
                
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00517,
                    getClass().getName() + STR_METHOD_NAME,
                    " 取得した顧客コードと指定行番号より前の明細行の顧客コードを比較し、同じ値が存在する");                
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
     * 　@行番号：　@行番号<BR>
     * <BR>
     * 　@１－１）　@部店コードが取得できない場合（get部店コード() == null）、<BR>
     * 例外をスローする。<BR>
     * 　@１－２）　@半角数字以外が含まれる場合は、例外をスローする。<BR>
     * 　@１－３）　@文字数が3byteでない場合は、例外をスローする。<BR>
     * <BR>
     * ２）　@顧客コードのチェック<BR>
     * 　@get顧客コード()にて、指定行番号のデータを取得しチェックを行う。<BR>
     * <BR>
     * 　@[get顧客コード()に指定する引数]<BR>
     * 　@行番号：　@行番号<BR>
     * <BR>
     * 　@２－１）　@顧客コードが取得できない場合（get顧客コード() == null）、<BR>
     * 例外をスローする。<BR>
     * 　@２－２）　@半角数字以外が含まれる場合は、例外をスローする。<BR>
     * 　@２－３）　@文字数が6byteでない場合は、例外をスローする。<BR>
     * <BR>
     * ３）　@銀行名のチェック<BR>
     * 　@get銀行名()にて、指定行番号のデータを取得しチェックを行う。<BR>
     * <BR>
     * 　@[get銀行名()に指定する引数]<BR>
     * 　@行番号：　@行番号<BR>
     * <BR>
     * 　@３－１）　@全角文字（double byte charactor）以外が含まれる場合は、<BR>
     * 例外をスローする。<BR>
     * 　@３－２）　@文字数が16（32byte）より大きい場合は、例外をスローする。<BR>
     * <BR>
     * ４）　@支店名のチェック<BR>
     * 　@get支店名()にて、指定行番号のデータを取得しチェックを行う。<BR>
     * <BR>
     * 　@[get支店名()に指定する引数]<BR>
     * 　@行番号：　@行番号<BR>
     * <BR>
     * 　@４－１）　@全角文字（double byte charactor）以外が含まれる場合は、<BR>
     * 例外をスローする。<BR>
     * 　@４－２）　@文字数が16（32byte）より大きい場合は、例外をスローする。<BR>
     * <BR>
     * ５）　@支店コードのチェック<BR>
     * 　@get支店コード()にて、指定行番号のデータを取得しチェックを行う。<BR>
     * <BR>
     * 　@[get支店コード()に指定する引数]<BR>
     * 　@行番号：　@行番号<BR>
     * <BR>
     * 　@５－１）　@半角数字以外が含まれる場合は、例外をスローする。<BR>
     * 　@５－２）　@文字数が3byteでない場合は、例外をスローする。<BR>
     * <BR>
     * ６）　@口座種類名のチェック<BR>
     * 　@get口座種類名()にて、指定行番号のデータを取得しチェックを行う。<BR>
     * <BR>
     * 　@[get口座種類名()に指定する引数]<BR>
     * 　@行番号：　@行番号<BR>
     * <BR>
     * 　@６－１）　@全角文字（double byte charactor）以外が含まれる場合は、<BR>
     * 例外をスローする。<BR>
     * 　@６－２）　@文字数が5（10byte）より大きい場合は、例外をスローする。<BR>
     * <BR>
     * ７）　@口座番号のチェック<BR>
     * 　@get口座番号()にて、指定行番号のデータを取得しチェックを行う。<BR>
     * <BR>
     * 　@[get口座番号()に指定する引数]<BR>
     * 　@行番号：　@行番号<BR>
     * <BR>
     * 　@７－１）　@半角数字以外が含まれる場合は、例外をスローする。<BR>
     * 　@７－２）　@文字数が7byteより大きい場合は、例外をスローする。<BR>
     * <BR>
     * ８）　@口座名義人のチェック<BR>
     * 　@get口座名義人()にて、指定行番号のデータを取得しチェックを行う。<BR>
     * <BR>
     * 　@[get口座名義人()に指定する引数]<BR>
     * 　@行番号：　@行番号<BR>
     * <BR>
     * 　@８－１）　@全角文字（double byte charactor）以外が含まれる場合は、<BR>
     * 例外をスローする。<BR>
     * 　@８－２）　@文字数が20（40byte）より大きい場合は、例外をスローする。<BR>
     * 
     * =================================================================== <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01170<BR>
     * ※スローする例外は、全て「専用振込先口座ファ@イル内容エラー」の例外。
     * =================================================================== <BR>
     * 
     * 　@９－１）　@未入力の場合は、例外をスローする。<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag:   BUSINESS_ERROR_01170<BR>
     * 　@９－２）　@半角数字以外が含まれる場合は、例外をスローする。<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag:   BUSINESS_ERROR_01170<BR>
     * 　@９－３）　@文字数が4byteより大きい場合は、例外をスローする。<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag:   BUSINESS_ERROR_01170<BR>
     * 　@９－４）　@金融機@関テーブルにデータ存在のチェック<BR>
     * 　@　@　@isExist金融機@関（）にて、存在チェックを行う。<BR>
     * <BR>
     * 　@　@　@[isExist金融機@関()に指定する引数]<BR>
     * 　@　@　@銀行コード：９）で取得した銀行コード<BR>
     * 　@　@　@支店コード：５）で取得した支店コード<BR>
     * <BR>
     * 　@　@　@９－４－１）　@存在しない（false）の場合は、例外をスローする。<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag:   BUSINESS_ERROR_01170<BR>
     * <BR>
     * @@param l_intLineNumber - 行番号
     * @@param l_strInstitutionCode - 証券会社コード
     * @@throws WEB3BaseException
     * @@roseuid 415B942C00D0
     */
    public void validateDetailsLine(int l_intLineNumber, String l_strInstitutionCode) throws WEB3BaseException 
    {
        
        final String STR_METHOD_NAME = " validateDetailsLine(int l_intLineNumber, String l_strInstitutionCode)";
        log.entering(STR_METHOD_NAME);
        
        //get部店コード()にて、指定行番号のデータを取得しチェックを行う
        String l_strBranchCode = this.getBranchCode(l_intLineNumber);
        
        //１－１）　@部店コードが取得できない場合（get部店コード() == null）、例外をスローする
        if(l_strBranchCode == null || "".equals(l_strBranchCode))
        {
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01170,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                " 専用振込先口座ファ@イル内容エラー");
        }
        
        //１－２）　@半角数字以外が含まれる場合は、例外をスローする。
        if(!WEB3StringTypeUtility.isDigit(l_strBranchCode))
        {
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01170,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                " 専用振込先口座ファ@イル内容エラー");
        }
        
        //１－３）　@文字数が3byteでない場合は、例外をスローする。
        if(WEB3StringTypeUtility.getByteLength(l_strBranchCode) != 3)
        {
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01170,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                " 専用振込先口座ファ@イル内容エラー");
        }
        
        //get顧客コード()にて、指定行番号のデータを取得しチェックを行う
        String l_strAccountCode = this.getAccountCode(l_intLineNumber);
        
        //２－１）　@顧客コードが取得できない場合（get顧客コード() == null）、例外をスローする
        if(l_strAccountCode == null || "".equals(l_strAccountCode))
        {
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01170,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                " 専用振込先口座ファ@イル内容エラー");
        }
        
        //２－２）　@半角数字以外が含まれる場合は、例外をスローする。
        if(!WEB3StringTypeUtility.isDigit(l_strAccountCode))
        {
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01170,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                " 専用振込先口座ファ@イル内容エラー");
        }
        
        //２－３）　@文字数が6byteでない場合は、例外をスローする。
        if(WEB3StringTypeUtility.getByteLength(l_strAccountCode) != 6)
        {
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01170,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                " 専用振込先口座ファ@イル内容エラー");
        }
        
        //get銀行名()にて、指定行番号のデータを取得しチェックを行う        
        String l_strFinInstitutionName = this.getFinInstitutionName(l_intLineNumber);
        if (l_strFinInstitutionName != null && !l_strFinInstitutionName.equals(""))
        {
            //３－１）　@全角文字（double byte charactor）以外が含まれる場合は、例外をスローする
            if(!WEB3StringTypeUtility.isMulti(l_strFinInstitutionName))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01170,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    " 専用振込先口座ファ@イル内容エラー");
            }
        
            //３－２）　@文字数が16（32byte）より大きい場合は、例外をスローする
            if(WEB3StringTypeUtility.getByteLength(l_strFinInstitutionName) > 32)
            {
            
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01170,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    " 専用振込先口座ファ@イル内容エラー");
            }
        }
        
        //get支店名()にて、指定行番号のデータを取得しチェックを行う
        String l_strFinBranchName = this.getFinBranchName(l_intLineNumber);
        if (l_strFinBranchName != null && !l_strFinBranchName.equals(""))
        {
            //4-1) 全角文字（double byte charactor）以外が含まれる場合は、例外をスローする
            if(!WEB3StringTypeUtility.isMulti(l_strFinBranchName))
            {
                
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01170,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    " 専用振込先口座ファ@イル内容エラー");
            }
            
            //４－２）　@文字数が16（32byte）より大きい場合は、例外をスローする
            if(WEB3StringTypeUtility.getByteLength(l_strFinBranchName) > 32)
            {
                
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01170,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    " 専用振込先口座ファ@イル内容エラー");
            }
        }
        
        //get支店コード()にて、指定行番号のデータを取得しチェックを行う
        String l_strFinBranchCode = this.getFinBranchCode(l_intLineNumber);
        if (l_strFinBranchCode != null && !l_strFinBranchCode.equals(""))
        {
            //5-1) 半角数字以外が含まれる場合は、例外をスローする
            if(!WEB3StringTypeUtility.isDigit(l_strFinBranchCode))
            {
                
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01170,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    " 専用振込先口座ファ@イル内容エラー");
            }
            
            //５－２）　@文字数が3byteでない場合は、例外をスローする
            if(WEB3StringTypeUtility.getByteLength(l_strFinBranchCode) != 3)
            {
                
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01170,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    " 専用振込先口座ファ@イル内容エラー");
            }
        }
        
        //get口座種類名()にて、指定行番号のデータを取得しチェックを行う
        String l_strFinAccountTypeName = this.getFinAccountTypeName(l_intLineNumber);
        if (l_strFinAccountTypeName != null && !l_strFinAccountTypeName.equals(""))
        {
            //6-1) 全角文字（double byte charactor）以外が含まれる場合は、例外をスローする
            if(!WEB3StringTypeUtility.isMulti(l_strFinAccountTypeName))
            {
                
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01170,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    " 専用振込先口座ファ@イル内容エラー");
            }
            
            //６－２）　@文字数が5（10byte）より大きい場合は、例外をスローする
            if(WEB3StringTypeUtility.getByteLength(l_strFinAccountTypeName) > 10)
            {
                
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01170,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    " 専用振込先口座ファ@イル内容エラー");
            }
        }
        
        //get口座番号()にて、指定行番号のデータを取得しチェックを行う
        String l_strFinAccountNo = this.getFinAccountNo(l_intLineNumber);
        if (l_strFinAccountNo != null && !l_strFinAccountNo.equals(""))
        {
            //７－１）　@半角数字以外が含まれる場合は、例外をスローする
            if(!WEB3StringTypeUtility.isDigit(l_strFinAccountNo))
            {
                
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01170,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    " 専用振込先口座ファ@イル内容エラー");
            }
    
            //7-2) 文字数が7byteより大きい場合は、例外をスローする
            if(WEB3StringTypeUtility.getByteLength(l_strFinAccountNo) > 7)
            {
                
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01170,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    " 専用振込先口座ファ@イル内容エラー");
            }
        }
        
        //get口座名義人()にて、指定行番号のデータを取得しチェックを行う]
        String l_strFinAccountName = this.getFinAccountName(l_intLineNumber);
        if (l_strFinAccountName != null && !l_strFinAccountName.equals(""))
        {
            //８－１）　@全角文字（double byte charactor）以外が含まれる場合は、例外をスローする
            if(!WEB3StringTypeUtility.isMulti(l_strFinAccountName))
            {
                
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_01170,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    " 専用振込先口座ファ@イル内容エラー");
            }
            
            //８－２）　@文字数が20（40byte）より大きい場合は、例外をスローする
            if(WEB3StringTypeUtility.getByteLength(l_strFinAccountName) > 40)
            {
                
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01170,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    " 専用振込先口座ファ@イル内容エラー");
            }
        }
        
        //get銀行コード()にて、指定行番号のデータを取得しチェックを行う。
        String l_strFinInstitutionCode = this.getFinInstitutionCode(l_intLineNumber);
        
        // ９－１）　@未入力の場合は、例外をスローする。 
        if (l_strFinInstitutionCode == null || l_strFinInstitutionCode.length() == 0) 
        {
			log.debug(STR_METHOD_NAME);
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_01170, 
				this.getClass().getName() + "." + STR_METHOD_NAME,
				" 専用振込先口座ファ@イル内容エラー。");
        }
        
        // ９－２）　@半角数字以外が含まれる場合は、例外をスローする。
        if(!WEB3StringTypeUtility.isDigit(l_strFinInstitutionCode))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01170,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                " 専用振込先口座ファ@イル内容エラー。");
        }
            
        // ９－３）　@文字数が4byteより大きい場合は、例外をスローする。 
        if(WEB3StringTypeUtility.getByteLength(l_strFinInstitutionCode) > 4)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01170,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                " 専用振込先口座ファ@イル内容エラー。");
        }
            
        // ９－４）　@金融機@関テーブルにデータ存在のチェック 
        if(!isExistFinInstitution(l_strFinInstitutionCode, l_strFinBranchCode))
        {
            //９－４－１）　@存在しない（false）の場合は、例外をスローする。
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01170,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                " 専用振込先口座ファ@イル内容エラー。");
        }
        
        log.exiting(STR_METHOD_NAME);
     
    }
    
    /**
     * (isExist金融機@関)<BR>
     * 金融機@関テーブルに既存行が存在するかを判定する。<BR>
     * <BR>
     * １）　@金融機@関（銀行）マスタより、指定の銀行コードと支店コードに <BR>
     * 　@　@　@該当する行を取得する。 <BR>
     * ２）　@行が取得できればtrue、取得できなければfalseを返却する。<BR>
     * @@param l_strFinInstitutionCode - 銀行コード
     * @@param l_strBranchCode - 支店コード
     * @@return boolean
     * @@throws WEB3BaseException
     */
    protected boolean isExistFinInstitution(String l_strFinInstitutionCode, String l_strBranchCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " isExistFinInstitution(String, String)";
        log.entering(STR_METHOD_NAME);
        
        //１）　@金融機@関（銀行）マスタより、指定の銀行コードと支店コードに該当する行を取得する。
        FinInstitutionBankRow l_finInstitutionBankRow = null;
        try
        {
            l_finInstitutionBankRow = FinInstitutionBankDao.findRowByPk(
                l_strFinInstitutionCode,
                l_strBranchCode);
            
            //２）　@行が取得できればtrue、取得できなければfalseを返却する。
            if (l_finInstitutionBankRow != null) 
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
            else
            {
                log.exiting(STR_METHOD_NAME);
                return false;
            }
        }
        catch (DataFindException l_ex)
        {
            //例外をスロー
            log.debug("テーブルに該当するデータがありません。");
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        catch (DataException l_ex)
        {
            //例外をスロー
            log.debug("DBへのアクセスに失敗しました。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "DBへのアクセスに失敗しました。");
        }
    }
}
@
