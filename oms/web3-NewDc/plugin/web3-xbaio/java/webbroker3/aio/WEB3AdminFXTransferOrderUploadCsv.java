head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.35.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AdminFXTransferOrderUploadCsv.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : FX振替注文アップロードCSV(WEB3AdminFXTransferOrderUploadCsv.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/24 鄭徳懇(中訊) 新規作成
                 : 2006/03/02 鄭徳懇 (中訊) 仕様変更・モデル511
                 : 2006/03/07 鄭徳懇 (中訊) 仕様変更・モデル517
                 : 2006/04/07 山下(SRA)    仕様変更・モデル525                                                
                 : 2006/04/20 黄浩澎 (中訊) 障害管理票・U02825                                               
Revesion History : 2008/09/05 王志葵 (中訊) 仕様変更・モデル965,966,967,968,969,977,978
*/
package webbroker3.aio;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.aio.data.CompFxConditionRow;
import webbroker3.aio.data.GftTransferStatusParams;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.gentrade.WEB3GentradeCsvUploadDataModel;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (FX振替注文アップロードCSV)<BR>
 * FX振替注文アップロードCSV<BR>
 * 
 * @@author 鄭徳懇
 * @@version 1.0
 */
public class WEB3AdminFXTransferOrderUploadCsv extends WEB3GentradeCsvUploadDataModel
{
    
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFXTransferOrderUploadCsv.class);
    
    /**
     * (アップロードファ@イルID)<BR>
     * アップロードファ@イルID <BR>
     * <BR>
     * ※（管理者共通）アップロードテーブル.アップロードファ@イルＩＤに<BR>
     * 格納する文字列 <BR>
     */
    public String UPLOAD_FILEID = "FX振替注文アップロード";
    
    /**
     * (参加者コードラベル)<BR>
     * 参加者コードラベル<BR>
     */
    public String PARTICIPANT_CODE_LABEL = "参加者コード";
    
    /**
     * (利用者コードラベル)<BR>
     * 利用者コードラベル<BR>
     */
    public String USER_CODE_LABEL = "利用者コード";
    
    /**
     * (科目コードラベル)<BR>
     * 科目コードラベル <BR>
     */
    public String SUBJECT_CODE_LABEL = "科目コード";
    
    /**
     * (出金額ラベル)<BR>
     * 出金額ラベル <BR>
     */
    public String CASH_OUT_AMOUNT_LABEL = "出金額";
    
    /**
     * (出金日ラベル)<BR>
     * 出金日ラベル <BR>
     */
    public String PAYMENT_DATE_LABEL = "出金日";
    
    /**
     * (入出金番号ラベル)<BR>
     * 入出金番号ラベル <BR>
     */
    public String CASH_IN_OUT_NUMBER_LABEL = "入出金番号";
    
    /**
     * (FX振替注文アップロードCSV)<BR>
     * コンストラクタ <BR>
     * ※　@アップロード中止の場合に使用する。 <BR>
     * <BR>
     * －引数のアップロードIDをプロパティにセットする。 <BR>
     * @@param l_lngUploadId - (アップロードID)<BR>
     * @@roseuid 43C6159801DA
     */
    public WEB3AdminFXTransferOrderUploadCsv(long l_lngUploadId) 
    {
        super.administratorUploadId = l_lngUploadId;
    }
    
    /**
     * (FX振替注文アップロードCSV)<BR>
     * コンストラクタ <BR>
     * <BR>
     * －this.createカラムヘッダ()をコールする。 <BR>
     * @@roseuid 43C6156F039F
     */
    public WEB3AdminFXTransferOrderUploadCsv() 
    {
        this.createColumnHeader();
    }
    
    /**
     * (createカラムヘッダ)<BR>
     * 以下の通りCSVカラムモデルの配列を生成し、setカラムヘッダ()に<BR>
     * てインスタンスにセットする。 <BR>
     * <BR>
     * [カラムヘッダ配列] <BR>
     * <BR>
     * －　@index = 0 <BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@FX振替注文CSV.参加者コードラベル <BR>
     * 　@カラム番号： 0 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 1 <BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@FX振替注文CSV.利用者コードラベル<BR>
     * 　@カラム番号： 1 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 2 <BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@FX振替注文CSV.科目コードラベル<BR>
     * 　@カラム番号： 2 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 3 <BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@FX振替注文CSV.出金額ラベル<BR>
     * 　@カラム番号： 3 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_数値（double）<BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 4 <BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@FX振替注文CSV.出金日ラベル<BR>
     * 　@カラム番号： 4 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_日付<BR>
     * 　@日付フォーマット：　@new SimpleDateFormat("yyyyMMdd") <BR>
     * <BR>
     * －　@index = 5 <BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@FX振替注文CSV.入出金番号ラベル<BR>
     * 　@カラム番号： 5 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * @@roseuid 43C6166D0192
     */
    protected void createColumnHeader() 
    {
        final String STR_METHOD_NAME = " createColumnHeader()";
        log.entering(STR_METHOD_NAME);
        
        //CSVカラムモデルの配列を生成し
        WEB3GentradeCsvColumnModel[] l_columnModel = new WEB3GentradeCsvColumnModel[6];
        
        //－　@index = 0 
        // 　@[CSVカラムモデル コンストラクタの引数] 
        // 　@項目ラベル：　@FX振替注文CSV.参加者コードラベル 
        // 　@カラム番号： 0 
        // 　@項目型：　@CSVカラムモデル.項目型_文字列 
        // 　@日付フォーマット：　@null 
        l_columnModel[0] = 
            new WEB3GentradeCsvColumnModel(
                this.PARTICIPANT_CODE_LABEL,
                0,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);

        // －　@index = 1
        // 　@[CSVカラムモデル コンストラクタの引数] 
        // 　@項目ラベル：　@FX振替注文CSV.利用者コードラベル
        // 　@カラム番号： 1 <BR>
        // 　@項目型：　@CSVカラムモデル.項目型_文字列 
        //　@ 日付フォーマット：　@null
        l_columnModel[1] = 
            new WEB3GentradeCsvColumnModel(
                this.USER_CODE_LABEL,
                1, 
                WEB3GentradeCsvColumnModel.STRINGTYPE, 
                null);

        // －　@index = 2 
        // 　@[CSVカラムモデル コンストラクタの引数] 
        // 　@項目ラベル：　@FX振替注文CSV.科目コードラベル
        // 　@カラム番号： 2 
        // 　@項目型：　@CSVカラムモデル.項目型_文字列 
        // 　@日付フォーマット：　@null 
        l_columnModel[2] = 
            new WEB3GentradeCsvColumnModel(
                this.SUBJECT_CODE_LABEL,
                2,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);

        // －　@index = 3 
        // 　@[CSVカラムモデル コンストラクタの引数] 
        // 　@項目ラベル：　@FX振替注文CSV.出金額ラベル
        // 　@カラム番号： 3 
        // 　@項目型：　@CSVカラムモデル.項目型_数値（double）
        // 　@日付フォーマット：　@null 
        l_columnModel[3] = 
            new WEB3GentradeCsvColumnModel(
                this.CASH_OUT_AMOUNT_LABEL,
                3,
                WEB3GentradeCsvColumnModel.DOUBLETYPE,
                null);

        // －　@index = 4 
        // 　@[CSVカラムモデル コンストラクタの引数] 
        // 　@項目ラベル：　@FX振替注文CSV.出金日ラベル
        //　@ カラム番号： 4 
        // 　@項目型：　@CSVカラムモデル.項目型_日付
        // 　@日付フォーマット：　@new SimpleDateFormat("yyyyMMdd")
        l_columnModel[4] = 
            new WEB3GentradeCsvColumnModel(
                this.PAYMENT_DATE_LABEL,
                4,
                WEB3GentradeCsvColumnModel.DATETYPE,
                new SimpleDateFormat("yyyyMMdd"));

        // －　@index = 5 
        // 　@[CSVカラムモデル コンストラクタの引数] 
        // 　@項目ラベル：　@FX振替注文CSV.入出金番号ラベル
        // 　@カラム番号： 5 <BR>
        // 　@項目型：　@CSVカラムモデル.項目型_文字列 
        // 　@日付フォーマット：　@null 
        l_columnModel[5] = 
            new WEB3GentradeCsvColumnModel(
                this.CASH_IN_OUT_NUMBER_LABEL,
                5,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);
        
        //setカラムヘッダ()にてインスタンスにセットする。
        this.setColumnHeader(l_columnModel);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate明細行)<BR>
     * 明細行のチェックを行う。<BR>
     * <BR>
     * １）　@利用者コードのチェック<BR>
     * 　@this.get利用者コード()にて、指定行番号のデータを取得しチェックを行う。<BR>
     * <BR>
     * 　@[get利用者コード()に指定する引数]<BR>
     * 　@行番号：　@引数.行番号<BR>
     * <BR>
     * 　@１－１）　@半角数字以外が含まれる場合は、例外をスローする。<BR>
     *       　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     *       　@　@　@　@　@tag  : BUSINESS_ERROR_02367<BR>
     * 　@１－２）　@文字数が8byteでない場合は、例外をスローする。<BR>
     *       　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     *       　@　@　@　@　@tag  : BUSINESS_ERROR_02368<BR>
     * ２）　@顧客の存在チェック <BR>
     * 　@this.get顧客()をコールする。<BR>
     * <BR>
     * 　@[get顧客()に指定する引数]<BR>
     * 　@行番号：　@引数.行番号<BR>
     * <BR>
     * ３）　@科目コードのチェック <BR>
     * 　@this.get科目コード()にて、指定行番号のデータを取得しチェックを行う。 <BR>
     * <BR>
     * 　@[get科目コード()に指定する引数] <BR>
     * 　@行番号：　@引数.行番号 <BR>
     * <BR>
     * 　@３－１）　@半角数字以外が含まれる場合は、例外をスローする。 <BR>
     *       　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     *       　@　@　@　@　@tag  : BUSINESS_ERROR_02382<BR>
     * 　@３－２）　@文字数が1byteでない場合は、例外をスローする。 <BR>
     *       　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     *       　@　@　@　@　@tag  : BUSINESS_ERROR_02383<BR>
     * <BR>
     * ４）　@出金額のチェック <BR>
     * 　@this.get出金額()にて、指定行番号のデータを取得しチェックを行う。 <BR>
     * <BR>
     * 　@[get出金額()に指定する引数] <BR>
     * 　@行番号：　@引数.行番号 <BR>
     * <BR>
     * 　@４－１）　@出金額がNullの場合、例外をスローする。        <BR>
     *                class: WEB3SystemLayerException<BR>
     *                tag  : SYSTEM_ERROR_80023
     * 　@４－２）　@桁数が9桁より大きい場合は、例外をスローする。  <BR>
     *       　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     *       　@　@　@　@　@tag  : BUSINESS_ERROR_02413<BR>
     * <BR>
     * ５）　@入出金番号のチェック <BR>
     * 　@this.get入出金番号()にて、指定行番号のデータを取得しチェックを行う。 <BR>
     * <BR>
     * 　@[get入出金番号()に指定する引数] <BR>
     * 　@行番号：　@引数.行番号 <BR>
     * <BR>
     * 　@５－１）　@半角数字以外が含まれる場合は、例外をスローする。 <BR>
     *       　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     *       　@　@　@　@　@tag  : BUSINESS_ERROR_02388<BR>
     * <BR>
     * 　@５－２）　@文字数が9byteより大きい場合は、例外をスローする。 <BR>
     *       　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     *       　@　@　@　@　@tag  : BUSINESS_ERROR_02389<BR>
     * <BR>
     * ６）　@出金日のチェック <BR>
     *                明細行文字列から出金日を取得し、validate出金日(出金日 : String)をコールする。<BR>                
     *                class: WEB3SystemLayerException<BR>
     *                tag  : SYSTEM_ERROR_80023  <BR>
     * <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@param l_rowString     - (明細行文字列)<BR>
     * @@throws WEB3BaseException
     * @@roseuid 43CC891E00B5
     */
    public void validateDetailsLine(int l_intLineNumber, String l_rowString) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateDetailsLine(int)";
        log.entering(STR_METHOD_NAME);
        
        //１）　@利用者コードのチェック
        //this.get利用者コード()にて、指定行番号のデータを取得しチェックを行う。
        String l_strUserCode = this.getUserCode(l_intLineNumber);
        
        //１－１）　@半角数字以外が含まれる場合は、例外をスローする。
        if (!WEB3StringTypeUtility.isDigit(l_strUserCode))
        {
            log.debug("利用者コードの値が半角数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02367,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "利用者コードの値が半角数字以外の値です。");
        }
        
        //１－２）　@文字数が8byteでない場合は、例外をスローする。
        if (l_strUserCode.getBytes().length != 8)
        {
            log.debug("利用者コードの値が8byteが数字ではありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02368,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "利用者コードの値が8byteが数字ではありません。");
        }
        
        //２）　@顧客の存在チェック 
        //this.get顧客()をコールする。
        this.getMainAccount(l_intLineNumber);
        
        //３）　@科目コードのチェック 
        //this.get科目コード()にて、指定行番号のデータを取得しチェックを行う。
        String l_strSubjectCode = this.getSubjectCode(l_intLineNumber);
        
        //３－１）　@半角数字以外が含まれる場合は、例外をスローする。
        if (!WEB3StringTypeUtility.isDigit(l_strSubjectCode))
        {
            log.debug("科目コードの値が半角数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02382,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "科目コードの値が半角数字以外の値です。");
        }
            
        //３－２）　@文字数が1byteでない場合は、例外をスローする。 
        if (l_strSubjectCode.getBytes().length != 1)
        {
            log.debug("科目コードの値が1byteが数字ではありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02383,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "科目コードの値が1byteが数字ではありません。");
        }
        
        //４）　@出金額のチェック 
        //this.get出金額()にて、指定行番号のデータを取得しチェックを行う。                
        //		４－１）　@出金額がNullの場合、例外をスローする。
		String l_strCashOutAmt = null;
        try
        {
			l_strCashOutAmt = WEB3StringTypeUtility.formatNumber(this.getCashOutAmt(l_intLineNumber));
        }
		catch(Exception e)
        {
            log.debug("出金額の値が半角数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02384,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "出金額の値が半角数字以外の値です。");
	    }
        
        //     ４－２）　@桁数が9桁より大きい場合は、例外をスローする。
        if (l_strCashOutAmt.length() > 9)
        {
            log.debug("出金額の桁数が9桁より大きいです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02413,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "出金額の桁数が9桁より大きいです。");
        }
        
        //５）　@入出金番号のチェック 
        //this.get入出金番号()にて、指定行番号のデータを取得しチェックを行う。
        String l_strCashInOutNumber = this.getCashInOutNumber(l_intLineNumber);
        
        //５－１）　@半角数字以外が含まれる場合は、例外をスローする。 
        if (!WEB3StringTypeUtility.isDigit(l_strCashInOutNumber))
        {
            log.debug("入出金番号の値が半角数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02388,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "入出金番号の値が半角数字以外の値です。");
        }
            
        //５－２）　@文字数が9byteより大きい場合は、例外をスローする。 
        if (l_strCashInOutNumber.getBytes().length > 9)
        {
            log.debug("入出金番号の値が9byteより大きいです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02389,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "入出金番号の値が9byteより大きいです。");
        }
        
		//６）　@出金日のチェック <BR>
		// 明細行文字列から出金日を取得し、validate出金日(出金日 : String)をコールする。<BR>
		String[] token = l_rowString.split(regex);		
		String l_strPaymentDate = token[getColumnModel(PAYMENT_DATE_LABEL).getColumnNumber()];
		this.validatePaymentDate(l_strPaymentDate);
		        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get参加者コード)<BR>
     * 引数.行番号に対応する明細行の参加者コードを取得する。 <BR>
     * <BR>
     * this.get項目値()にて参加者コードを取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@引数.行番号 <BR>
     * カラム：　@getカラムモデル(FX振替注文アップロードCSV.参加者コードラベル)<BR>
     * の戻り値。<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@return String
     * @@roseuid 43CC8D7F028A
     */
    public String getParticipantCode(int l_intLineNumber) 
    {
        //this.get項目値()にて参加者コードを取得し返却する。
        return (String) this.getValue(l_intLineNumber, this.getColumnModel(this.PARTICIPANT_CODE_LABEL));
    }
    
    /**
     * (get利用者コード)<BR>
     * 引数.行番号に対応する明細行の利用者コードを取得する。 <BR>
     * <BR>
     * this.get項目値()にて利用者コードを取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@引数.行番号 <BR>
     * カラム：　@getカラムモデル(FX振替注文アップロードCSV.利用者コードラベル)<BR>
     * の戻り値。 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@return String
     * @@roseuid 43CC8E12026B
     */
    public String getUserCode(int l_intLineNumber) 
    {
        //this.get項目値()にて利用者コードを取得し返却する。
        return (String)this.getValue(l_intLineNumber, this.getColumnModel(this.USER_CODE_LABEL));
    }
    
    /**
     * (get科目コード)<BR>
     * 行番号に対応する明細行の科目コードを取得する。 <BR>
     * <BR>
     * this.get項目値()にて科目コードを取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@行番号 <BR>
     * カラム：　@getカラムモデル(FX振替注文アップロードCSV.科目コードラベル) <BR>
     * の戻り値。 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@return String
     * @@roseuid 43CC8E5100F4
     */
    public String getSubjectCode(int l_intLineNumber) 
    {
        //this.get項目値()にて科目コードを取得し返却する。
        return (String) this.getValue(l_intLineNumber, this.getColumnModel(this.SUBJECT_CODE_LABEL));
    }
    
    /**
     * (get出金額)<BR>
     * 行番号に対応する明細行の出金額を取得する。 <BR>
     * <BR>
     * this.get項目値()にて出金額を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@行番号 <BR>
     * カラム：　@getカラムモデル(FX振替注文アップロードCSV.出金額ラベル)の戻り値。<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 43CC8E790067
     */
    public double getCashOutAmt(int l_intLineNumber) throws WEB3BaseException
    {
        return ((Double) this.getValue(l_intLineNumber, this.getColumnModel(this.CASH_OUT_AMOUNT_LABEL))).doubleValue();
    }
    
    /**
     * (get出金日)<BR>
     * 行番号に対応する明細行の出金日を取得する。 <BR>
     * <BR>
     * this.get項目値()にて出金日を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@行番号 <BR>
     * カラム：　@getカラムモデル(FX振替注文アップロードCSV.出金日ラベル)の戻り値。<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@throws WEB3BaseException
     * @@return Date
     * @@roseuid 43CC8EA80299
     */
    public Date getPaymentDate(int l_intLineNumber) throws WEB3BaseException
    {
        return (Date) this.getValue(l_intLineNumber, this.getColumnModel(this.PAYMENT_DATE_LABEL));
    }
    
    /**
     * (get入出金番号)<BR>
     * 行番号に対応する明細行の入出金番号を取得する。 <BR>
     * <BR>
     * this.get項目値()にて入出金番号を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@行番号 <BR>
     * カラム：　@getカラムモデル(FX振替注文アップロードCSV.入出金番号ラベル)の<BR>
     * 　@　@　@　@　@戻り値。 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@return String
     * @@roseuid 43CC8ECC022C
     */
    public String getCashInOutNumber(int l_intLineNumber) 
    {
        //this.get項目値()にて入出金番号を取得し返却する。
        return (String) this.getValue(l_intLineNumber, this.getColumnModel(this.CASH_IN_OUT_NUMBER_LABEL));
    }
    
    /**
     * (get顧客コード)<BR>
     * 引数.行番号に対応する明細行の顧客コード（6桁）を取得する。 <BR>
     * <BR>
     * this.get利用者コード()にて利用者コードを取得し後ろ3桁目以降を返却する。 <BR>
     * <BR>
     * [get利用者コード()に指定する引数] <BR>
     * 行番号：　@引数.行番号 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@return String
     * @@roseuid 43CC8F0403B3
     */
    public String getMainAccountCode(int l_intLineNumber) 
    {
        return this.getUserCode(l_intLineNumber).substring(2);
    }
    
    /**
     * (get顧客)<BR>
     * 引数.行番号に対応する明細行の顧客オブジェクトを取得する。 <BR>
     * <BR>
     * 拡張アカウントマネージャ.get顧客()にて顧客オブジェクトを取得し返却する。 <BR>
     * <BR>
     * [get顧客()に指定する引数] <BR>
     * 証券会社コード：　@this.get証券会社コード()<BR>
     * 部店コード：　@this.get部店コード(引数.行番号)<BR>
     * 口座コード：　@this.get顧客コード(引数.行番号)<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@return WEB3GentradeMainAccount
     * @@throws WEB3BaseException
     * @@roseuid 43CC8FC80057
     */
    public WEB3GentradeMainAccount getMainAccount(int l_intLineNumber) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getMainAccount(int)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        
        //拡張アカウントマネージャ.get顧客()にて顧客オブジェクトを取得し返却する。
        WEB3GentradeMainAccount l_mainAccount = l_accountManager.getMainAccount(
            this.getInstitutionCode(),
            this.getBranchCode(l_intLineNumber),
            this.getMainAccountCode(l_intLineNumber));
        
        log.exiting(STR_METHOD_NAME);
        return l_mainAccount;
    }
    
    /**
     * (get部店コード)<BR>
     * 引数.行番号に対応する明細行の部店コードを取得する。 <BR>
     * <BR>
     * １） this.getFXログインID頭文字()にてFXログインID頭文字を取得する。<BR>
     * 　@[getFXログインID頭文字()に指定する引数] <BR>
     * 　@行番号：　@引数.行番号 <BR>
     * <BR>
     * ２） 以下の条件のもとに会社別FXシステム条件テーブルを取得する。<BR>
     * <BR>
     * 　@・証券会社コード==this.get証券会社コード()<BR>
     * 　@・FXログインID頭文字.substring(1)==１）で取得したFXログインID頭文字<BR>
     * <BR>
     * ３） 会社別FXシステム条件Param.部店コードを返却する。<BR>
     * <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 43CC929D00C5
     */
    public String getBranchCode(int l_intLineNumber) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getBranchCode(int)";
        log.entering(STR_METHOD_NAME);
        
        //１） this.getFXログインID頭文字()にてFXログインID頭文字を取得する。
        String l_strFXHeadOfLoginId = this.getFXHeadOfLoginId(l_intLineNumber);
        
        //２） 以下の条件のもとに会社別FXシステム条件テーブルを取得する。
        //　@・証券会社コード==this.get証券会社コード()
        //  ・FXログインID頭文字.substring(1)==１）で取得したFXログインID頭文字
        String l_strWhere = " institution_code = ? and substr(fx_head_of_login_id, 2) = ? ";
        Object[] l_objValues = {this.getInstitutionCode(), l_strFXHeadOfLoginId};
        List l_lisRows = null;
        try
        {
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    CompFxConditionRow.TYPE,
                    l_strWhere,
                    null,
                    l_objValues);
        }
        catch (DataQueryException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." +STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBアクセスエラー", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        if (l_lisRows == null || l_lisRows.size() == 0)
        {
            log.debug("部店コード・顧客コードが不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02414,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "部店コード・顧客コードが不正です。" + " " +
                    this.getUserCode(l_intLineNumber) + " " + 
                    this.getCashInOutNumber(l_intLineNumber));
        }

        //３） 会社別FXシステム条件Param.部店コードを返却する。
        log.exiting(STR_METHOD_NAME);
        return ((CompFxConditionRow) l_lisRows.get(0)).getBranchCode();
    }
    
    /**
     * (validate重複注文)<BR>
     * 重複注文が追加されていないかチェックを行う。 <BR>
     * <BR>
     * １）　@明細のチェック<BR>
     * <BR>
     * 　@１－１）　@this.get利用者コード(引数.行番号)、<BR>
     * 　@　@　@　@　@　@this.get入出金番号(引数.行番号)にて、<BR>
     * 　@　@　@　@　@　@指定行番号の利用者コード、入出金番号を取得する。 <BR>
     * <BR>
     * 　@１－２）　@取得した利用者コード、入出金番号と、<BR>
     * 　@　@　@　@　@　@指定行番号より前の明細行の利用者コード、入出金番号を比較する。 <BR>
     * 　@　@　@　@　@　@同じ値の行（指定行.利用者コード == それより前の行.利用者コード<BR>
     * 　@　@　@　@　@　@&& 指定行.入出金番号 == それより前の行.入出金番号）<BR>
     * 　@　@　@　@　@　@が存在する場合は、 注文が重複して登録されていると判定し、<BR>
     * 　@　@　@　@　@　@例外をスローする。 <BR>
     *       　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     *       　@　@　@　@　@tag  : BUSINESS_ERROR_02390<BR>
     * <BR>
     * ２）　@GFT振替状況テーブルのチェック<BR>
     * <BR>
     * 　@２－１）　@GFT振替状況テーブルを取得する。<BR>
     * <BR>
     * 　@FXデータ制御サービスImpl.getGFT振替状況()をコールする。<BR>
     * <BR>
     * 　@[getGFT振替状況()に指定する引数]<BR>
     * 　@証券会社コード = this.get証券会社コード()<BR>
     * 　@部店コード = this.get部店コード(引数.行番号)<BR>
     * 　@顧客コード = this.get顧客コード(引数.行番号)<BR>
     * 　@入出金番号 = this.get入出金番号(引数.行番号)<BR>
     * <BR>
     * 　@２－２）　@レコードが存在する場合、注文が重複していると判定し、<BR>
     * 　@　@　@　@　@　@例外をスローする。 <BR>
     *       　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     *       　@　@　@　@　@tag  : BUSINESS_ERROR_02391<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * @@throws WEB3BaseException
     * @@roseuid 43CF1864025A
     */
    public void validateDuplicateOrder(int l_intLineNumber) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateDuplicateOrder(int)";
        log.entering(STR_METHOD_NAME);

        //１）　@明細のチェック
        //１－１）　@this.get利用者コード(引数.行番号)、
        //this.get入出金番号(引数.行番号)にて、
        //指定行番号の利用者コード、入出金番号を取得する。
        String l_strUserCode = this.getUserCode(l_intLineNumber);
        String l_strCashInOutNumber = this.getCashInOutNumber(l_intLineNumber);
        
        //１－２）　@取得した利用者コード、入出金番号と、
        //　@指定行番号より前の明細行の利用者コード、入出金番号を比較する。
        //  同じ値の行（指定行.利用者コード == それより前の行.利用者コード &&
        //  指定行.入出金番号 == それより前の行.入出金番号）
        //  が存在する場合は、 注文が重複して登録されていると判定し、
        //  例外をスローする。 
        if (l_intLineNumber > 0 
            && l_strUserCode.equals(this.getUserCode(l_intLineNumber - 1))
            && l_strCashInOutNumber.equals(this.getCashInOutNumber(l_intLineNumber - 1)))
        {
            log.debug("取得したデータが存在する場合、注文が重複して登録されています。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02390,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "取得したデータが存在する場合、注文が重複して登録されています。");
        }
        
        //２）　@GFT振替状況テーブルのチェック
               //２－１）　@GFT振替状況テーブルを取得する。
               // FXデータ制御サービスImpl.getGFT振替状況()をコールする。
               // [getGFT振替状況()に指定する引数]
               // 証券会社コード = this.get証券会社コード()
               // 部店コード = this.get部店コード(引数.行番号)
               // 顧客コード = this.get顧客コード(引数.行番号)
               // 入出金番号 = this.get入出金番号(引数.行番号)
        WEB3FXDataControlService l_dataControlService = 
            (WEB3FXDataControlService) Services.getService(WEB3FXDataControlService.class);
        GftTransferStatusParams l_params = 
            l_dataControlService.getGFTTransferStatus(
                this.getInstitutionCode(),
                this.getBranchCode(l_intLineNumber),
                this.getMainAccountCode(l_intLineNumber),
                this.getCashInOutNumber(l_intLineNumber));
        
        //２－２）　@レコードが存在する場合、注文が重複していると判定し、
        //        例外をスローする。
        if (l_params != null)
        {
            log.debug("抽選レコードが存在します。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02391,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "抽選レコードが存在します。");
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (getFXログインID頭文字)<BR>
     * 引数.行番号に対応する明細行の部店コードを取得する。 <BR>
     * <BR>
     * １） this.get利用者コード()にて利用者コードを取得し、前2桁を取得する。 <BR>
     * <BR>
     * 　@[get利用者コード()に指定する引数] <BR>
     * 　@行番号：　@引数.行番号<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 43E2F24A03E7
     */
    public String getFXHeadOfLoginId(int l_intLineNumber) 
    {
        //１）this.get利用者コード()にて利用者コードを取得し、前2桁を取得する。
        return this.getUserCode(l_intLineNumber).substring(0, 2);
    }
    
    /**
     * (get銘柄タイプ)<BR>
     * ProductTypeEnum.現金 を返却する。<BR>
     * @@return ProductTypeEnum
     * @@roseuid 43EFF41C0343
     */
    public ProductTypeEnum getProductType() 
    {
        //ProductTypeEnum.現金 を返却する。
        return ProductTypeEnum.CASH;
    }
    
    /**
     * (getアップロードファ@イルＩＤ)<BR>
     * this.アップロードファ@イルＩＤを返却する。<BR>
     * <BR>
     * ※（管理者共通）アップロードテーブル.アップロードファ@イルＩＤに<BR>
     * 格納する文字列<BR>
     * @@return String
     * @@roseuid 43E0675E029D
     */
    public String getUploadFileId() 
    {
        return this.UPLOAD_FILEID;
    }
    
	/**
	 * (validate出金日)<BR>
	 * 出金日のフォーマットチェックを行う。<BR>
	 * <BR>
     * @@param l_strPaymentDate - (出金日)<BR>
     * @@throws WEB3BaseException
	 */
	private void validatePaymentDate(String l_strPaymentDate) throws WEB3BaseException
	{
		final String STR_METHOD_NAME = " validatePaymentDate(String)";
		log.entering(STR_METHOD_NAME);

        //１）出金日のNullチェック
        //２）出金日の８桁チェック		
        //３）出金日の半角数字チェック
        if (!WEB3StringTypeUtility.isDigit(l_strPaymentDate)
            || l_strPaymentDate.length() != 8)
        {
			log.debug("出金日の指定に誤りがあります。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01835,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "出金日の指定に誤りがあります。");
        }		
		
		//４）不正な日付のチェック
		int l_intYear  = Integer.parseInt(l_strPaymentDate.substring(0, 4));
		int l_intMonth = Integer.parseInt(l_strPaymentDate.substring(4, 6)) - 1;
		int l_intDay   = Integer.parseInt(l_strPaymentDate.substring(6, 8));
        
        Calendar cal = new GregorianCalendar();
        cal.set( l_intYear , l_intMonth , l_intDay );
          
		if (cal.get(Calendar.YEAR) != l_intYear 
			|| cal.get(Calendar.MONTH) != l_intMonth
			|| cal.get(Calendar.DATE) != l_intDay) {
		    
			log.debug("出金日の指定に誤りがあります。");
			log.exiting(STR_METHOD_NAME);
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_01835,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				"出金日の指定に誤りがあります。");
		}

		log.exiting(STR_METHOD_NAME);
	}
}
@
