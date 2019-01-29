head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.00.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointPackageAdjustUploadCsv.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ポイント一括調整アップロードCSV(WEB3AdminPointPackageAdjustUploadCsv.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 鄭海良(中訊) 新規作成
*/
package webbroker3.point;

import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.gentrade.WEB3GentradeCsvUploadDataModel;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (ポイント一括調整アップロードCSV)<BR>
 * ポイント一括調整アップロードCSVクラス<BR>
 *
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3AdminPointPackageAdjustUploadCsv extends WEB3GentradeCsvUploadDataModel 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3AdminPointPackageAdjustUploadCsv.class);
    
    /**
     * (部店コードラベル)<BR>
     * 部店コードラベル<BR>
     */
    public static  String BRANCH_CODE_LABEL = "部店コード";
    
    /**
     * (顧客コードラベル)<BR>
     * 顧客コードラベル<BR>
     */
    public static  String ACCOUNT_CODE_LABEL = "顧客コード";
    
    /**
     * (調整ポイントラベル)<BR>
     * 調整ポイントラベル<BR>
     */
    public static  String ADJUST_POINT_LABEL = "調整ポイント";
    
    /**
     * (アップロードファ@イルID)<BR>
     * アップロードファ@イルID<BR>
     */
    public String uploadFileId = "ポイント一括調整アップロード";
    
    /**
     * (ポイント一括調整アップロードCSV)<BR>
     * コンストラクタ<BR>
     * <BR>
     * １）引数のアップロードIDをプロパティにセットする。<BR>
     * <BR>
     * ２）this.createカラムヘッダ()をコールする。 <BR>
     * @@param l_uploadId - (アップロードID)<BR>
     * アップロードID<BR>
     * @@roseuid 4199E2C70213
     */
    public WEB3AdminPointPackageAdjustUploadCsv(long l_uploadId) 
    {
        final String STR_METHOD_NAME = " WEB3AdminPointPackageAdjustUploadCsv(long )";
        log.entering(STR_METHOD_NAME);
        
        // １）引数のアップロードIDをプロパティにセットする。
        this.administratorUploadId = l_uploadId;
        
        //２）this.createカラムヘッダ()をコールする。 
        this.createColumnHeader();
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (ポイント一括調整アップロードCSV)<BR>
     * コンストラクタ <BR>
     * <BR>
     * this.createカラムヘッダ()をコールする。 <BR>
     * @@roseuid 4198917B028C
     */
    public WEB3AdminPointPackageAdjustUploadCsv() 
    {
        final String STR_METHOD_NAME = " WEB3AdminPointPackageAdjustUploadCsv()";
        log.entering(STR_METHOD_NAME);
        
        // this.createカラムヘッダ()をコールする。 
        this.createColumnHeader();
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (getアップロードファ@イルＩＤ)<BR>
     * this.アップロードファ@イルＩＤを返却する。<BR>
     * @@return String
     * @@roseuid 41995DD602FA
     */
    public String getUploadFileId() 
    {
        return this.uploadFileId;
    }
    
    /**
     * (get銘柄タイプ)<BR>
     * 銘柄タイプを返却する。<BR>
     * <BR>
     * ProductTypeEnum.その他を返却する。<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum
     * @@roseuid 41995D6E00F6
     */
    public ProductTypeEnum getProductType() 
    {
        return ProductTypeEnum.OTHER;
    }
    
    /**
     * (createカラムヘッダ)<BR>
     * カラムヘッダをセットする。<BR>
     * <BR>
     * 以下のとおりにCSVカラムモデルの配列を生成し、setカラムヘッダ()にて<BR>インスタンスにセットする。<BR>
     * <BR>
     * [カラムヘッダ配列]<BR>
     * <BR>
     * ○index = 0 （部店コード）<BR>
     *    [CSVカラムモデル コンストラクタの引数]<BR>
     *    項目ラベル： ポイント一括調整アップロードCSV.部店コードラベル<BR>
     *    カラム番号： 0<BR>
     *    項目型： CSVカラムモデル.項目型_文字列<BR>
     *    日付フォーマット： null<BR>
     * <BR>
     * ○index = 1 （顧客コード）<BR>
     *    [CSVカラムモデル コンストラクタの引数]<BR>
     *    項目ラベル： ポイント一括調整アップロードCSV.顧客コードラベル<BR>
     *    カラム番号： 1<BR>
     *    項目型： CSVカラムモデル.項目型_文字列<BR>
     *    日付フォーマット： null<BR>
     * <BR>
     * ○index = 2 （調整ポイント）<BR>
     *    [CSVカラムモデル コンストラクタの引数]<BR>
     *    項目ラベル： ポイント一括調整アップロードCSV.調整ポイントラベル<BR>
     *    カラム番号： 2<BR>
     *    項目型： CSVカラムモデル.項目型_数値（long）<BR>
     *    日付フォーマット： null<BR>
     * <BR>
     * @@roseuid 419AB06A0120
     */
    private void createColumnHeader() 
    {
        final String STR_METHOD_NAME = " createColumnHeader()";
        log.entering(STR_METHOD_NAME);
        
        final int COLUMN_MAX = 3;
        
        WEB3GentradeCsvColumnModel[] l_models = new WEB3GentradeCsvColumnModel[COLUMN_MAX];
        //index 0        
        l_models[0] = new WEB3GentradeCsvColumnModel(
            WEB3AdminPointPackageAdjustUploadCsv.BRANCH_CODE_LABEL, 
            0, 
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);
        
        //index 1        
        l_models[1] = new WEB3GentradeCsvColumnModel(
        WEB3AdminPointPackageAdjustUploadCsv.ACCOUNT_CODE_LABEL, 
            1, 
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);
        
        //index 2        
        l_models[2] = new WEB3GentradeCsvColumnModel(
        WEB3AdminPointPackageAdjustUploadCsv.ADJUST_POINT_LABEL, 
            2, 
            WEB3GentradeCsvColumnModel.LONGTYPE,
            null);
            
        this.setColumnHeader(l_models);
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (get部店コード)<BR>
     * 行番号に対応する明細行の部店コードを取得する。 <BR>
     * <BR>
     * this.get項目値()にて部店コードを取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号： 行番号<BR>
     * カラム： getカラムモデル(ポイント一括調整アップロードCSV.部店コードラベル)の戻り値<BR>
     * <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * 
     * @@return String
     * @@roseuid 419AB7F903CF
     */
    public String getBranchCode(int l_intLineNumber) 
    {
        String l_strBranchCode = null;
        Object l_obj = this.getValue(l_intLineNumber, this.getColumnModel(BRANCH_CODE_LABEL));
        if (l_obj != null)
        {
            l_strBranchCode = l_obj.toString();
        }
        return l_strBranchCode;
    }
    
    /**
     * (get顧客コード)<BR>
     * 行番号に対応する明細行の顧客コードを取得する。 <BR>
     * <BR>
     * this.get項目値()にて顧客コードを取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号： 行番号<BR>
     * カラム： getカラムモデル(ポイント一括調整アップロードCSV.顧客コードラベル)の戻り値<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * 
     * @@return String
     * @@roseuid 419AB8A802F4
     */
    public String getAccountCode(int l_intLineNumber) 
    {
        String l_strAccountCode = null;
        Object l_obj = this.getValue(l_intLineNumber, this.getColumnModel(ACCOUNT_CODE_LABEL));
        if (l_obj != null)
        {
            l_strAccountCode = l_obj.toString();
        }  
        return l_strAccountCode;    
    }
    
    /**
     * (get調整ポイント)<BR>
     * 行番号に対応する明細行の調整ポイントを取得する。 <BR>
     * <BR>
     * this.get項目値()にて調整ポイントを取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数]<BR>
     * 行番号： 行番号<BR>
     * カラム： getカラムモデル(ポイント一括調整アップロードCSV.調整ポイントラベル)の戻り値<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * 
     * @@return long
     * @@roseuid 419AB8CC0229
     */
    public long getAdjustPoint(int l_intLineNumber) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getAdjustPoint(int)";
        log.entering(STR_METHOD_NAME);
        Object l_obj = this.getValue(l_intLineNumber, this.getColumnModel(ADJUST_POINT_LABEL));
        if (l_obj == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01719,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        long l_lngAdjustPoint = Long.parseLong(l_obj.toString());
        
        log.exiting(STR_METHOD_NAME);
        
        return l_lngAdjustPoint;
    }
    
    /**
     * (validate重複顧客)<BR>
     * 重複顧客が追加されていないかチェックを行う。 <BR>
     * <BR>
     * get顧客コード(行番号)にて、指定行番号の顧客コードを取得する。 <BR>
     * 取得した顧客コードと指定行番号より前の明細行の顧客コードを比較し、 <BR>
     * 同じ値が存在する場合は、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00517<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * @@roseuid 419ACF5F03B0
     */
    public void validateDuplicateAccount(int l_intLineNumber) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateDuplicateAccount(int)";
        log.entering(STR_METHOD_NAME);
        
        if (l_intLineNumber > 0)
        {
            String l_strAccountCode1 = this.getAccountCode(l_intLineNumber);
            for (int i = 0; i < l_intLineNumber; i++)
            {
                log.debug("loop couont:" + i);
                String l_strAccountCode2 = this.getAccountCode(i);
                if ((l_strAccountCode1 == null && l_strAccountCode2 == null) || 
                    (l_strAccountCode1 != null && l_strAccountCode1.equals(l_strAccountCode2)))
                {
                    String l_strMessage = "重複顧客! line Number1:" + (l_intLineNumber - 1) + "line Number2:" + l_intLineNumber;
                    log.debug(l_strMessage);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00517,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_strMessage);
                } 
            }
        }
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (validate同時アップロード)<BR>
     * 他プロセスのアップロード処理が起動中でないかをチェックする。<BR>
     * <BR>
     * this.validate同時アップロード（アップロードID）をコールする。<BR>
     * <BR>
     * [validate同時アップロード()にセットする引数]<BR>
     * アップロードID： this.アップロードID<BR>
     * @@roseuid 419D4D0601F5
     */
    public void validateSameTimeUpload() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateSameTimeUpload()";
        log.entering(STR_METHOD_NAME);
        
        this.validateSameTimeUpload(new Long(this.administratorUploadId));
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (setDataFromアップロードTemp)<BR>
     * アップロードテンポラリテーブルのデータをプロパティにをセットする。<BR>
     * <BR>
     * this.setDataFromアップロードTemp（アップロードID）をコールする。<BR>
     * <BR>
     * setDataFromアップロードTemp()にセットする引数]<BR>
     * アップロードID： this.アップロードID<BR>
     * @@roseuid 419D4DA20050
     */
    public void setDataFromUploadTemp() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " setDataFromUploadTemp()";
        log.entering(STR_METHOD_NAME);
        
        this.setDataFromUploadTemp(administratorUploadId);//WEB3SystemLayerException
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (validate部店コード)<BR>
     * 部店コードのチェックを行う。<BR>
     * <BR>
     *    引数.部店コード.length() != 3 or<BR>
     *    引数.部店コード.length() != 数字<BR>
     * <BR>
     * の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00834<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01729<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * @@roseuid 41A68940023D
     */
    public void validateBranchCode(String l_strBranchCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateBranchCode(String )";
        log.entering(STR_METHOD_NAME);
        
        if (l_strBranchCode == null || WEB3StringTypeUtility.getByteLength(l_strBranchCode.trim()) != 3)
        {
            String l_strMessage = "部店コード.length() != 3";
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00834,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
            
        if (!WEB3StringTypeUtility.isDigit(l_strBranchCode))
        {
            String l_strMessage = "部店コード != 数字";
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01729,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (validate顧客コード)<BR>
     * 顧客コードのチェックを行う。<BR>
     * <BR>
     *    引数.顧客コード.length() != 6 or<BR>
     *    引数.顧客コード.length() != 数字<BR>
     * <BR>
     * の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00836<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01043<BR>
     * @@param l_strAccountCode - (顧客コード)<BR>
     * 顧客コード<BR>
     * @@roseuid 41A689E002CA
     */
    public void validateAccountCode(String l_strAccountCode) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateAccountCode(String )";
        log.entering(STR_METHOD_NAME);
        
        if (l_strAccountCode == null || WEB3StringTypeUtility.getByteLength(l_strAccountCode.trim()) != 6)
        {
            String l_strMessage = "顧客コード.length() != 6";
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00836,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
            
        if (!WEB3StringTypeUtility.isDigit(l_strAccountCode))
        {
            String l_strMessage = "顧客コード != 数字";
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01043,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        log.exiting(STR_METHOD_NAME);     
    }
}
@
