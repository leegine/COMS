head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.03.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3PLSProfitLossSpecsCsv.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 損益明細CSV(WEB3PLSProfitLossSpecsCsv.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/10/20 周捷 (中訊) 新規作成
                 : 2006/11/07  周捷 (中訊) モデル067
*/
package webbroker3.tradehistory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ApplicationCodeDef;
import webbroker3.common.define.WEB3CommodityCodeTypeDef;
import webbroker3.common.define.WEB3ProfitLossRecordDivDef;
import webbroker3.common.define.WEB3ProfitLossRemarkDef;
import webbroker3.common.define.WEB3ReturnDivDef;
import webbroker3.common.define.WEB3TermDivDef;
import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.gentrade.WEB3GentradeCsvDataModel;
import webbroker3.gentrade.data.CodeTranslationRow;
import webbroker3.tradehistory.define.WEB3PLSProfitLossSpecsWkCodeDef;
import webbroker3.tradehistory.define.WEB3PlsBvsCarryoverBalanceRecDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (損益明細CSV)<BR>
 * 損益明細CSV
 * 
 * @@author 周捷
 * @@version 1.0
 */
public class WEB3PLSProfitLossSpecsCsv extends WEB3GentradeCsvDataModel
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3PLSProfitLossSpecsCsv.class);
    
    /**
     * (計算日ラベル)<BR>
     * 定数定義プロパティ　@"計算日"<BR>
     */
    private static String CALCDATE_LABEL = "計算日";
    
    /**
     * (受渡日ラベル)<BR>
     * 定数定義プロパティ　@"受渡日"<BR>
     */
    private static String DELIVERYDATE_LABEL = "受渡日";
    
    /**
     * (（商品）適用ラベル)<BR>
     * 定数定義プロパティ　@"（商品）適用"<BR>
     */
    private static String FUNDTYPEAPP_LABEL = "(商品)適用";
    
    /**
     * (銘柄名ラベル)<BR>
     * 定数定義プロパティ　@"銘柄名"<BR>
     */
    private static String PRODUCTNAME_LABEL = "銘柄名";
    
    /**
     * (長短ラベル)<BR>
     * 定数定義プロパティ　@"長短"<BR>
     */
    private static String TERM_LABEL = "長短";
    
    /**
     * (数量ラベル)<BR>
     * 定数定義プロパティ　@"数量"<BR>
     */
    private static String QUANTITY_LABEL = "数量";
    
    /**
     * (譲渡日ラベル)<BR>
     * 定数定義プロパティ　@"譲渡日"<BR>
     */
    private static String PASSDATE_LABEL = "譲渡日";
    
    /**
     * (譲渡金額ラベル)<BR>
     * 定数定義プロパティ　@"譲渡金額"<BR>
     */
    private static String PASSAMOUNT_LABEL = "譲渡金額";
    
    /**
     * (取得日ラベル)<BR>
     * 定数定義プロパティ　@"取得日"<BR>
     */
    private static String GETDATE_LABEL = "取得日";
    
    /**
     * (取得費等ラベル)<BR>
     * 定数定義プロパティ　@"取得費等"<BR>
     */
    private static String GETAMOUNT_LABEL = "取得費等";
    
    /**
     * (損益ラベル)<BR>
     * 定数定義プロパティ　@"損益"<BR>
     */
    private static String PROLOSSAMOUNT_LABEL = "損益";
    
    /**
     * (累計損益ラベル)<BR>
     * 定数定義プロパティ　@"累計損益"<BR>
     */
    private static String TOTALPROLOSSAMOUNT_LABEL = "累計損益";
    
    /**
     * (課税対象額ラベル)<BR>
     * 定数定義プロパティ　@"課税対象額"<BR>
     */
    private static String TAXABLEAMOUNT_LABEL = "課税対象額";
    
    /**
     * (徴収税額ラベル)<BR>
     * 定数定義プロパティ　@"徴収税額"<BR>
     */
    private static String COLLECTTAXAMOUNT_LABEL = "徴収税額";
    
    /**
     * (徴収税額（国税）ラベル)<BR>
     * 定数定義プロパティ　@"徴収税額（国税）"<BR>
     */
    private static String COLLECTTAXNAMOUNT_LABEL = "徴収税額(国税)";
    
    /**
     * (徴収税額（地方税）ラベル)<BR>
     * 定数定義プロパティ　@"徴収税額（地方税）"<BR>
     */
    private static String COLLECTTAXLAMOUNT_LABEL = "徴収税額(地方税)";
    
    /**
     * (損益明細CSV)<BR>
     * コンストラクタ。  <BR>
     * <BR>
     * インスタンスを生成し、ヘッダ情報をセットする。  <BR>
     * 　@－super()にてインスタンスを生成する。  <BR>
     * 　@－createキーヘッダ()をコールし、キーヘッダ情報を作成する。  <BR>
     * 　@－createカラムヘッダ()をコールし、カラムヘッダ情報を作成する。<BR>
     */
    public WEB3PLSProfitLossSpecsCsv()
    {
        //super()にてインスタンスを生成する。
        super();
        
        //createキーヘッダ()をコールし、キーヘッダ情報を作成する。 
        this.createKeyHeader();
        
        //createカラムヘッダ()をコールし、カラムヘッダ情報を作成する。  
        this.createColumnHeader();
    }
    
    /**
     * (createカラムヘッダ)<BR>
     * カラムヘッダをセットする。  <BR>
     * <BR>
     * 　@以下の通りCSVカラムモデルの配列を生成し、setカラムヘッダ()にてインスタンスにセットする。  <BR>
     * <BR>
     * [カラムヘッダ配列]  <BR>
     * <BR>
     * －　@index = 0  <BR>
     * 　@[CSVカラムモデル コンストラクタの引数]  <BR>
     * 　@項目ラベル：　@損益明細照会CSV.計算日ラベル  <BR>
     * 　@カラム番号： 0  <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列  <BR>
     * 　@日付フォーマット：　@null  <BR>
     * <BR>
     * －　@index = 1  <BR>
     * 　@[CSVカラムモデル コンストラクタの引数]  <BR>
     * 　@項目ラベル：　@損益明細照会CSV.受渡日ラベル  <BR>
     * 　@カラム番号： 1  <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_日付型  <BR>
     * 　@日付フォーマット：　@new SimpleDateFormat("yyyy/MM/dd")  <BR>
     * <BR>
     * －　@index = 2  <BR>
     * 　@[CSVカラムモデル コンストラクタの引数]  <BR>
     * 　@項目ラベル：　@損益明細照会CSV.(商品)適用ラベル  <BR>
     * 　@カラム番号： 2  <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列  <BR>
     * 　@日付フォーマット：　@null  <BR>
     * <BR>
     * －　@index = 3  <BR>
     * 　@[CSVカラムモデル コンストラクタの引数]  <BR>
     * 　@項目ラベル：　@損益明細照会CSV.銘柄名ラベル  <BR>
     * 　@カラム番号： 3  <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列  <BR>
     * 　@日付フォーマット：　@null  <BR>
     * <BR>
     * －　@index = 4  <BR>
     * 　@[CSVカラムモデル コンストラクタの引数]  <BR>
     * 　@項目ラベル：　@損益明細照会CSV.長短ラベル  <BR>
     * 　@カラム番号： 4  <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列  <BR>
     * 　@日付フォーマット：　@null  <BR>
     * <BR>
     * －　@index = 5  <BR>
     * 　@[CSVカラムモデル コンストラクタの引数]  <BR>
     * 　@項目ラベル：　@損益明細照会CSV.数量ラベル  <BR>
     * 　@カラム番号： 5  <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列  <BR>
     * 　@日付フォーマット：　@null  <BR>
     * <BR>
     * －　@index = 6  <BR>
     * 　@[CSVカラムモデル コンストラクタの引数]  <BR>
     * 　@項目ラベル：　@損益明細照会CSV.譲渡日ラベル  <BR>
     * 　@カラム番号： 6  <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_日付型  <BR>
     * 　@日付フォーマット：　@new SimpleDateFormat("yyyy/MM/dd")  <BR>
     * <BR>
     * －　@index = 7  <BR>
     * 　@[CSVカラムモデル コンストラクタの引数]  <BR>
     * 　@項目ラベル：　@損益明細照会CSV.譲渡金額ラベル  <BR>
     * 　@カラム番号： 7  <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列  <BR>
     * 　@日付フォーマット：　@null  <BR>
     * <BR>
     * －　@index = 8  <BR>
     * 　@[CSVカラムモデル コンストラクタの引数]  <BR>
     * 　@項目ラベル：　@損益明細照会CSV.取得日ラベル  <BR>
     * 　@カラム番号： 8  <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_日付型  <BR>
     * 　@日付フォーマット：　@new SimpleDateFormat("yyyy/MM/dd")  <BR>
     * <BR>
     * －　@index = 9  <BR>
     * 　@[CSVカラムモデル コンストラクタの引数]  <BR>
     * 　@項目ラベル：　@損益明細照会CSV.取得費等ラベル  <BR>
     * 　@カラム番号： 9  <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列  <BR>
     * 　@日付フォーマット：　@null  <BR>
     * <BR>
     * －　@index = 10  <BR>
     * 　@[CSVカラムモデル コンストラクタの引数]  <BR>
     * 　@項目ラベル：　@損益明細照会CSV.損益ラベル  <BR>
     * 　@カラム番号： 10  <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列  <BR>
     * 　@日付フォーマット：　@null  <BR>
     * <BR>
     * －　@index = 11  <BR>
     * 　@[CSVカラムモデル コンストラクタの引数]  <BR>
     * 　@項目ラベル：　@損益明細照会CSV.累計損益ラベル  <BR>
     * 　@カラム番号： 11  <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列  <BR>
     * 　@日付フォーマット：　@null  <BR>
     * <BR>
     * －　@index = 12  <BR>
     * 　@[CSVカラムモデル コンストラクタの引数]  <BR>
     * 　@項目ラベル：　@損益明細照会CSV.課税対象額ラベル  <BR>
     * 　@カラム番号： 12  <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列  <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 13  <BR>
     * 　@[CSVカラムモデル コンストラクタの引数]  <BR>
     * 　@項目ラベル：　@損益明細照会CSV.徴収税額ラベル  <BR>
     * 　@カラム番号： 13  <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列  <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 14  <BR>
     * 　@[CSVカラムモデル コンストラクタの引数]  <BR>
     * 　@項目ラベル：　@損益明細照会CSV.徴収税額(国税)ラベル  <BR>
     * 　@カラム番号： 14  <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列  <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 15  <BR>
     * 　@[CSVカラムモデル コンストラクタの引数]  <BR>
     * 　@項目ラベル：　@損益明細照会CSV.徴収税額(地方税)ラベル  <BR>
     * 　@カラム番号： 15  <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列  <BR>
     * 　@日付フォーマット：　@null <BR>
     */
    public void createColumnHeader()
    {
        final String STR_METHOD_NAME = " createColumnHeader()";
        log.entering(STR_METHOD_NAME);
        final int COLUMN_MAX = 16;
        
        WEB3GentradeCsvColumnModel[] l_columnModel = new WEB3GentradeCsvColumnModel[COLUMN_MAX];
        
        //項目ラベル：　@損益明細照会CSV.計算日ラベル
        l_columnModel[0] = new WEB3GentradeCsvColumnModel(
            WEB3PLSProfitLossSpecsCsv.CALCDATE_LABEL,
            0,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);
        
        //項目ラベル：　@損益明細照会CSV.受渡日ラベル
        l_columnModel[1] = new WEB3GentradeCsvColumnModel(
            WEB3PLSProfitLossSpecsCsv.DELIVERYDATE_LABEL,
            1,
            WEB3GentradeCsvColumnModel.TIMESTAMPTYPE,
            new SimpleDateFormat("yyyy/MM/dd"));
        
        //項目ラベル：　@損益明細照会CSV..(商品)適用ラベル  
        l_columnModel[2] = new WEB3GentradeCsvColumnModel(
            WEB3PLSProfitLossSpecsCsv.FUNDTYPEAPP_LABEL,
            2,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);
        
        //項目ラベル：　@損益明細照会CSV.銘柄名ラベル  
        l_columnModel[3] = new WEB3GentradeCsvColumnModel(
            WEB3PLSProfitLossSpecsCsv.PRODUCTNAME_LABEL,
            3,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);
        
        //項目ラベル：　@損益明細照会CSV.長短ラベル  
        l_columnModel[4] = new WEB3GentradeCsvColumnModel(
            WEB3PLSProfitLossSpecsCsv.TERM_LABEL,
            4,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);
        
        //項目ラベル：　@損益明細照会CSV.数量ラベル  
        l_columnModel[5] = new WEB3GentradeCsvColumnModel(
            WEB3PLSProfitLossSpecsCsv.QUANTITY_LABEL,
            5,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);
        
        //項目ラベル：　@損益明細照会CSV.譲渡日ラベル
        l_columnModel[6] = new WEB3GentradeCsvColumnModel(
            WEB3PLSProfitLossSpecsCsv.PASSDATE_LABEL,
            6,
            WEB3GentradeCsvColumnModel.TIMESTAMPTYPE,
            new SimpleDateFormat("yyyy/MM/dd"));
        
        //項目ラベル：　@損益明細照会CSV.譲渡金額ラベル  
        l_columnModel[7] = new WEB3GentradeCsvColumnModel(
            WEB3PLSProfitLossSpecsCsv.PASSAMOUNT_LABEL,
            7,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);
        
        //項目ラベル：　@損益明細照会CSV.取得日ラベル  
        l_columnModel[8] = new WEB3GentradeCsvColumnModel(
            WEB3PLSProfitLossSpecsCsv.GETDATE_LABEL,
            8,
            WEB3GentradeCsvColumnModel.TIMESTAMPTYPE,
            new SimpleDateFormat("yyyy/MM/dd"));
        
        //項目ラベル：　@損益明細照会CSV.取得費等ラベル 
        l_columnModel[9] = new WEB3GentradeCsvColumnModel(
            WEB3PLSProfitLossSpecsCsv.GETAMOUNT_LABEL,
            9,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);
        
        //項目ラベル：　@損益明細照会CSV.損益ラベル  
        l_columnModel[10] = new WEB3GentradeCsvColumnModel(
            WEB3PLSProfitLossSpecsCsv.PROLOSSAMOUNT_LABEL,
            10,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);
        
        //項目ラベル：　@損益明細照会CSV.累計損益ラベル  
        l_columnModel[11] = new WEB3GentradeCsvColumnModel(
            WEB3PLSProfitLossSpecsCsv.TOTALPROLOSSAMOUNT_LABEL,
            11,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);
        
        //項目ラベル：　@損益明細照会CSV.課税対象額ラベル  
        l_columnModel[12] = new WEB3GentradeCsvColumnModel(
            WEB3PLSProfitLossSpecsCsv.TAXABLEAMOUNT_LABEL,
            12,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);
        
        //項目ラベル：　@損益明細照会CSV.徴収税額ラベル  
        l_columnModel[13] = new WEB3GentradeCsvColumnModel(
            WEB3PLSProfitLossSpecsCsv.COLLECTTAXAMOUNT_LABEL,
            13,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);
        
        //項目ラベル：　@損益明細照会CSV.徴収税額(国税)ラベル  
        l_columnModel[14] = new WEB3GentradeCsvColumnModel(
            WEB3PLSProfitLossSpecsCsv.COLLECTTAXNAMOUNT_LABEL,
            14,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);
        
        //項目ラベル：　@損益明細照会CSV.徴収税額(地方税)ラベル  
        l_columnModel[15] = new WEB3GentradeCsvColumnModel(
            WEB3PLSProfitLossSpecsCsv.COLLECTTAXLAMOUNT_LABEL,
            15,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);
        
        log.exiting(STR_METHOD_NAME);
        this.setColumnHeader(l_columnModel);
    }
    
    /**
     * (createキーヘッダ)<BR>
     * キーヘッダをセットする。<BR>  
     * <BR>
     * 　@以下の通り文字列の配列を生成し、setキーヘッダ()にてインスタンスにセットする。  <BR>
     * <BR>
     * [キーヘッダ配列]  <BR>
     * <BR>
     * －　@index = 0  <BR>
     * 　@現在日時を"yyyy/MM/dd HH:mm:ss"の形式にformatした文字列。  <BR>
     * <BR>
     * (*1) 現在日時  <BR>
     * TradingSystem.getSystemTimestamp()<BR>
     */
    public void createKeyHeader()
    {
        final String STR_METHOD_NAME = " createKeyHeader()";
        log.entering(STR_METHOD_NAME);
        
        String[] l_strKeyHeaders = new String[1];
        
        //現在日時を"yyyy/MM/dd HH:mm:ss"の形式にformatした文字列。   
        l_strKeyHeaders[0] = WEB3DateUtility.formatDate(
            GtlUtils.getSystemTimestamp(), "yyyy/MM/dd HH:mm:ss");
        
        //setキーヘッダ
        this.setKeyHeader(l_strKeyHeaders);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set計算日)<BR>
     * 「計算日」をセットする。<BR>  
     * <BR>
     * １）カラムモデル取得  <BR>
     * 　@　@this.getカラムモデル()にてCSVカラムモデルを取得する。  <BR>
     * <BR>
     * 　@　@[getカラムモデル()に指定する引数]  <BR>
     * 　@　@項目ラベル：　@損益明細CSV.計算日ラベル  <BR>
     * <BR>
     * ２）値セット  <BR>
     * 　@　@this.set項目値()にて項目値をセットする。  <BR>
     * <BR>
     * 　@　@[set項目値()に指定する引数]  <BR>
     * 　@　@行番号：　@引数の行番号  <BR>
     * 　@　@カラム：　@１）で取得したカラムモデル  <BR>
     * 　@　@値：　@パラメータ.計算日 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号
     * @@param l_strCalcDate - (計算日)<BR>
     * 計算日
     */
    public void setCalcDate(int l_intLineNumber, String l_strCalcDate)
    {
        final String STR_METHOD_NAME = " setCalcDate(int, String)";
        log.entering(STR_METHOD_NAME);
        
        //１）カラムモデル取得
        //this.getカラムモデル()にて、CSVカラムモデルを取得する。
        //[getカラムモデル()に指定する引数]
        //項目ラベル： 損益明細CSV.計算日ラベル
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel = 
            this.getColumnModel(CALCDATE_LABEL);
        
        //２）値セット
        //this.set項目値()にて項目値をセットする。
        //[set項目値()に指定する引数]  
        //行番号：　@引数の行番号  
        //カラム：　@１）で取得したカラムモデル  
        //値：　@パラメータ.計算日 
        this.setValue(l_intLineNumber, l_gentradeCsvColumnModel, l_strCalcDate);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set受渡日)<BR>
     * 「受渡日」をセットする。  <BR>
     * <BR>
     * １）カラムモデル取得  <BR>
     * 　@　@this.getカラムモデル()にてCSVカラムモデルを取得する。  <BR>
     * <BR>
     * 　@　@[getカラムモデル()に指定する引数]  <BR>
     * 　@　@項目ラベル：　@損益明細CSV.受渡日ラベル  <BR>
     * <BR>
     * ２）値セット  <BR>
     * 　@　@this.set項目値()にて項目値をセットする。  <BR>
     * <BR>
     * 　@　@[set項目値()に指定する引数]  <BR>
     * 　@　@行番号：　@引数の行番号  <BR>
     * 　@　@カラム：　@１）で取得したカラムモデル  <BR>
     * 　@　@値：　@パラメータ.受渡日 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号
     * @@param l_datDeliveryDate - (受渡日)<BR>
     * 受渡日
     */
    public void setDeliveryDate(int l_intLineNumber, Date l_datDeliveryDate)
    {
        final String STR_METHOD_NAME = " setDeliveryDate(int, Date)";
        log.entering(STR_METHOD_NAME);
        
        //１）カラムモデル取得
        //this.getカラムモデル()にて、CSVカラムモデルを取得する。
        //[getカラムモデル()に指定する引数]
        //項目ラベル： 損益明細CSV.受渡日ラベル
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel = 
            this.getColumnModel(DELIVERYDATE_LABEL);

        //２）値セット
        //this.set項目値()にて項目値をセットする。
        //[set項目値()に指定する引数]  
        //行番号：　@引数の行番号  
        //カラム：　@１）で取得したカラムモデル  
        //値：　@パラメータ.受渡日 
        this.setValue(l_intLineNumber, l_gentradeCsvColumnModel, l_datDeliveryDate);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set（商品）適用)<BR>
     * 「(商品)適用」をセットする。 <BR> 
     * <BR>
     * １）カラムモデル取得  <BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。  <BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数]  <BR>
     * 　@項目ラベル：　@損益明細CSV.（商品）適用ラベル  <BR>
     * <BR>
     * <BR>
     * ２）検索条件値の設定  <BR>
     * <BR>
     * 　@[wk変数定義] <BR>
     * 　@　@・wkコード <BR>
     * <BR>
     * 　@（１）wkコード設定 <BR>
     * 　@　@　@①@パラメータ.商品コード = 10:日株売買の場合 <BR>
     * 　@　@　@　@- wkコードに"1000"をセットする  <BR>
     * <BR>
     * 　@　@　@②パラメータ.商品コード = 11:ミニ株売買の場合 <BR>
     * 　@　@　@　@- wkコードに"1100"をセットする  <BR>
     * <BR>
     * 　@　@　@③パラメータ.商品コード = 12:ミニ株権利の場合 <BR>
     * 　@　@　@　@A. パラメータ.適用コード = 21:ミニ端株売買の場合 <BR>
     * 　@　@　@　@　@- wkコードに"1221"をセットする   <BR>
     * <BR>
     * 　@　@　@　@B. パラメータ.適用コード = 22:ミニ有償増資の場合 <BR>
     * 　@　@　@　@　@- wkコードに"1222"をセットする   <BR>
     * <BR>
     * 　@　@　@　@C. パラメータ.適用コード = 21・22以外の場合 <BR>
     * 　@　@　@　@　@- wkコードに"1200"をセットする   <BR>
     * <BR>
     * 　@　@　@④パラメータ.商品コード = 15:信用取引の場合 <BR>
     * 　@　@　@　@A. パラメータ.適用コード = 11:確定配当の場合 <BR>
     * 　@　@　@　@　@- wkコードに"1511"をセットする   <BR>
     * <BR>
     * 　@　@　@　@B. パラメータ.適用コード = 12:預り配当の場合 <BR>
     * 　@　@　@　@　@- wkコードに"1512"をセットする   <BR>
     * <BR>
     * 　@　@　@　@C. パラメータ.適用コード = 13:権利受払金の場合 <BR>
     * 　@　@　@　@　@- wkコードに"1513"をセットする   <BR>
     * <BR>
     * 　@　@　@　@D. パラメータ.適用コード = 11・12・13以外の場合 <BR>
     * 　@　@　@　@　@- wkコードに"1500"をセットする   <BR>
     * <BR>
     * 　@　@　@⑤パラメータ.商品コード = 20:投信取引の場合 <BR>
     * 　@　@　@　@- wkコードに"2000"をセットする  <BR>
     * <BR>
     * 　@　@　@⑥パラメータ.商品コード = 21:投信取引の場合 <BR>
     * 　@　@　@　@- wkコードに"2100"をセットする  <BR>
     * <BR>
     * 　@　@　@⑦パラメータ.商品コード = 30:日債売買の場合 <BR>
     * 　@　@　@　@- wkコードに"3000"をセットする  <BR>
     * <BR>
     * 　@　@　@⑧パラメータ.商品コード = 40:外株売買の場合 <BR>
     * 　@　@　@　@- wkコードに"4000"をセットする  <BR>
     * <BR>
     * 　@　@　@⑨パラメータ.商品コード = 42:外株権利の場合 <BR>
     * 　@　@　@　@A. パラメータ.適用コード = 31:外株権利売却の場合 <BR>
     * 　@　@　@　@　@- wkコードに"4231"をセットする   <BR>
     * <BR>
     * 　@　@　@　@B. パラメータ.適用コード = 31以外の場合 <BR>
     * 　@　@　@　@　@- wkコードに"4200"をセットする  <BR>
     * <BR>
     * 　@　@　@⑩パラメータ.商品コードが上記以外の場合（nullを含む） <BR>
     * 　@　@　@　@　@- ４）値セット を行う <BR>
     * 　@　@　@　@　@　@　@値：　@にはnullを設定する <BR>
     * <BR>
     * <BR>
     * ３）表示メッセージの取得  <BR>
     * <BR>
     * 　@QueryProcessor.doFindAllQuery()をコールする。  <BR>
     * <BR>
     * 　@[doFindAllQuery()にセットするパラメータ]  <BR>
     * 　@arg0：　@コード翻訳テーブル(code_translation)  <BR>
     * 　@arg1：　@オプショナル文字列(*1）  <BR>
     * 　@arg2：　@オブジェクト配列（*2）  <BR>
     * <BR>
     * 　@※取得できない場合、nullを返却する。 <BR>
     * <BR>
     * 　@（*1）オプショナル文字列  <BR>
     * <BR>
     * 　@　@　@以下の文字列を作成する。  <BR>
     * 　@　@　@　@"code_type = 'pls_comdv' "  <BR>
     * 　@　@　@＋" and institution_code = ? "  <BR>
     * 　@　@　@＋" and code = ?"  <BR>
     * <BR>
     * 　@（*2）オブジェクト配列  <BR>
     * <BR>
     * 　@　@　@以下の順でArrayListを作成する。  <BR>
     * 　@　@　@・パラメータ.会社コード  <BR>
     * 　@　@　@・wkコード  <BR>
     * <BR>
     * <BR>
     * ４）値セット  <BR>
     * 　@this.set項目値()にて項目値をセットする。 <BR> 
     * <BR>
     * 　@[set項目値()に指定する引数]  <BR>
     * 　@行番号：　@引数の行番号  <BR>
     * 　@カラム：　@１）で取得したカラムモデル  <BR>
     * 　@値：　@３）で取得した、コード翻訳テーブル.表示メッセージ <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号
     * @@param l_strFundType - (商品)<BR>
     * 商品
     * @@param l_strApplicationCode - (適用コード)<BR>
     * 適用コード
     * @@param l_strInstitutionCode - (会社コード)<BR>
     * 会社コード
     * @@throws WEB3BaseException 
     */
    public void setFundTypeApplication(
        int l_intLineNumber, String l_strFundType, String l_strApplicationCode, String l_strInstitutionCode) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " setFundTypeApplication(int, String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        //１）カラムモデル取得
        //this.getカラムモデル()にて、CSVカラムモデルを取得する。
        //[getカラムモデル()に指定する引数]
        //項目ラベル： 損益明細CSV.（商品）適用ラベル
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel = 
            this.getColumnModel(FUNDTYPEAPP_LABEL);
        
        //２）検索条件値の設定
        //　@[wk変数定義] 
        //　@・wkコード
        String l_strWkCode = "";
        
        //（１）wkコード設定 
        //①@パラメータ.商品コード = 10:日株売買の場合 
        //- wkコードに"1000"をセットする  
        //②パラメータ.商品コード = 11:ミニ株売買の場合 
        //- wkコードに"1100"をセットする  
        //③パラメータ.商品コード = 12:ミニ株権利の場合 
        //A. パラメータ.適用コード = 21:ミニ端株売買の場合 
        //- wkコードに"1221"をセットする   
        //B. パラメータ.適用コード = 22:ミニ有償増資の場合 
        //- wkコードに"1222"をセットする   
        //C. パラメータ.適用コード = 21・22以外の場合 
        //- wkコードに"1200"をセットする 
        if (WEB3CommodityCodeTypeDef.JAPANESE_STOCK_TRADE.equals(l_strFundType))
        {
            l_strWkCode = WEB3PLSProfitLossSpecsWkCodeDef.PLS_COMDV_1000;
        }
        else if (WEB3CommodityCodeTypeDef.MINI_STOCK_TRADE.equals(l_strFundType))
        {
            l_strWkCode = WEB3PLSProfitLossSpecsWkCodeDef.PLS_COMDV_1100;;
        }
        else if (WEB3CommodityCodeTypeDef.MINI_STOCK_CLAIM.equals(l_strFundType))
        {
            if (WEB3ApplicationCodeDef.MINI_STOCK_SALE.equals(l_strApplicationCode))
            {
                l_strWkCode = WEB3PLSProfitLossSpecsWkCodeDef.PLS_COMDV_1221;
            }
            else if (WEB3ApplicationCodeDef.MINI_STOCK_ONEROUS_INCREASE.equals(l_strApplicationCode))
            {
                l_strWkCode = WEB3PLSProfitLossSpecsWkCodeDef.PLS_COMDV_1222;
            }
            else
            {
                l_strWkCode = WEB3PLSProfitLossSpecsWkCodeDef.PLS_COMDV_1200;               
            }
        }
        
        //④パラメータ.商品コード = 15:信用取引の場合 
        else if (WEB3CommodityCodeTypeDef.MARGIN.equals(l_strFundType))
        {
            //A. パラメータ.適用コード = 11:確定配当の場合 
            if (WEB3ApplicationCodeDef.CONFIRM_DIVIDEND.equals(l_strApplicationCode))
            {
                //- wkコードに"1511"をセットする   
                l_strWkCode = WEB3PLSProfitLossSpecsWkCodeDef.PLS_COMDV_1511;
            }
            //B. パラメータ.適用コード = 12:預り配当の場合 
            else if (WEB3ApplicationCodeDef.DEPOSIT_DIVIDEND.equals(l_strApplicationCode))
            {
                //- wkコードに"1512"をセットする
                l_strWkCode = WEB3PLSProfitLossSpecsWkCodeDef.PLS_COMDV_1512;
            }
            //C. パラメータ.適用コード = 13:権利受払金の場合 
            else if (WEB3ApplicationCodeDef.CLAIM_PAYMENT.equals(l_strApplicationCode))
            {
                //- wkコードに"1513"をセットする  
                l_strWkCode = WEB3PLSProfitLossSpecsWkCodeDef.PLS_COMDV_1513;
            }
            //D. パラメータ.適用コード = 11・12・13以外の場合 
            else 
            {
                //- wkコードに"1500"をセットする
                l_strWkCode = WEB3PLSProfitLossSpecsWkCodeDef.PLS_COMDV_1500;
            }
        }
        
        //⑤パラメータ.商品コード = 20:投信取引の場合 
        else if (WEB3CommodityCodeTypeDef.MUTUAL_FUND_TRADE.equals(l_strFundType))
        {
            //- wkコードに"2000"をセットする
            l_strWkCode = WEB3PLSProfitLossSpecsWkCodeDef.PLS_COMDV_2000;
        }
        
        //⑥パラメータ.商品コード = 21:投信取引の場合 
        else if (WEB3CommodityCodeTypeDef.MUTUAL_FUND_TRADING.equals(l_strFundType))
        {
            //- wkコードに"2100"をセットする  
            l_strWkCode = WEB3PLSProfitLossSpecsWkCodeDef.PLS_COMDV_2100;
        }

        //⑦パラメータ.商品コード = 30:日債売買の場合 
        else if (WEB3CommodityCodeTypeDef.JAPANESE_BOND_TRADE.equals(l_strFundType))
        {
            //- wkコードに"3000"をセットする  
            l_strWkCode = WEB3PLSProfitLossSpecsWkCodeDef.PLS_COMDV_3000;
        }
        
        //⑧パラメータ.商品コード = 40:外株売買の場合 
        else if (WEB3CommodityCodeTypeDef.FOREIGN_STOCK_TRADE.equals(l_strFundType))
        {
            //- wkコードに"4000"をセットする  
            l_strWkCode = WEB3PLSProfitLossSpecsWkCodeDef.PLS_COMDV_4000;
        }
        
        //⑨パラメータ.商品コード = 42:外株権利の場合 
        else if (WEB3CommodityCodeTypeDef.FOREIGN_STOCK_CLAIM.equals(l_strFundType))
        {
            //A. パラメータ.適用コード = 31:外株権利売却の場合 
            if (WEB3ApplicationCodeDef.FOREIGN_STOCK_CLAIM_SALE.equals(l_strApplicationCode))
            {
                //- wkコードに"4231"をセットする
                l_strWkCode = WEB3PLSProfitLossSpecsWkCodeDef.PLS_COMDV_4231;
            }
            //B. パラメータ.適用コード = 31以外の場合 
            else
            {
                //- wkコードに"4200"をセットする
                l_strWkCode = WEB3PLSProfitLossSpecsWkCodeDef.PLS_COMDV_4200;
            }
        }

        //⑩パラメータ.商品コードが上記以外の場合（nullを含む） 
        //- ４）値セット を行う 
        //値：　@にはnullを設定する
        else 
        {
            l_strWkCode = null;
        }
        
        //３）表示メッセージの取得  
        //QueryProcessor.doFindAllQuery()をコールする。  
        //[doFindAllQuery()にセットするパラメータ]  
        //arg0：　@コード翻訳テーブル(code_translation)  
        //arg1：　@オプショナル文字列(*1）  
        //（*2）オブジェクト配列  
        //以下の順でArrayListを作成する。  
        //・パラメータ.会社コード  
        //・wkコード 
        //arg2：　@オブジェクト配列（*2）  
        //※取得できない場合、nullを返却する。 
        List l_lisRecords = new ArrayList();
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append("code_type = 'pls_comdv' ");
        l_sbWhere.append("and institution_code = ? ");
        l_sbWhere.append("and code = ?");
        Object[] l_objWhere = 
            new Object[]{l_strInstitutionCode, l_strWkCode};
        try
        {
            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_QueryProcessor.doFindAllQuery(
                CodeTranslationRow.TYPE,
                l_sbWhere.toString(),
                null,
                l_objWhere);
        }
        catch (DataFindException l_dfe)
        {
            log.error(STR_METHOD_NAME, l_dfe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dfe.getMessage(),
                l_dfe);
        }
        catch (DataQueryException l_dqe)
        {
            log.error(STR_METHOD_NAME, l_dqe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dqe.getMessage(),
                l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(STR_METHOD_NAME, l_dne);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }
        String l_strDisplayMessage = "";
        if (l_lisRecords == null || l_lisRecords.isEmpty())
        {
            l_strDisplayMessage = null;
        }
        else
        {
            CodeTranslationRow l_translationRow = (CodeTranslationRow) l_lisRecords.get(0);
            l_strDisplayMessage = l_translationRow.getDisplayMessage();
        }
        //４）値セット  
        //this.set項目値()にて項目値をセットする。  
        //[set項目値()に指定する引数]  
        //行番号：　@引数の行番号  
        //カラム：　@１）で取得したカラムモデル  
        //値：　@３）で取得した、コード翻訳テーブル.表示メッセージ 
        this.setValue(l_intLineNumber, l_gentradeCsvColumnModel, l_strDisplayMessage);
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set銘柄名)<BR>
     * 「銘柄名」をセットする。 <BR> 
     * <BR>
     * １）カラムモデル取得<BR>  
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>  
     * <BR>
     * 　@[getカラムモデル()に指定する引数]<BR>  
     * 　@項目ラベル：　@損益明細CSV.銘柄名ラベル<BR>  
     * <BR>
     * <BR>
     * ２）値セット<BR>  
     * ※パラメータ.レコード区分 = 20:明細レコードの場合に実施する<BR> 
     * <BR>
     * 　@　@this.set項目値()にて項目値をセットする。<BR>  
     * <BR>
     * 　@　@[set項目値()に指定する引数]<BR>  
     * 　@　@行番号：　@引数の行番号  <BR>
     * 　@　@カラム：　@１）で取得したカラムモデル<BR>  
     * 　@　@値：　@パラメータ.銘柄名  <BR>
     * <BR>
     * <BR>
     * ３）表示メッセージを取得し値セット<BR> 
     * ※パラメータ.レコード区分 = 20:明細レコード以外の場合に実施する<BR> 
     * <BR>
     * 　@[wk変数定義]<BR> 
     * 　@　@・wkコード <BR>
     * <BR>
     * 　@（１）wkコード設定<BR> 
     * 　@　@　@①@パラメータ.レコード区分 = 00:繰越残高レコードの場合<BR> 
     * 　@　@　@　@- wkコードに"90"をセットする  <BR>
     * <BR>
     * 　@　@　@②パラメータ.レコード区分 = 10:残高レコードの場合<BR> 
     * 　@　@　@　@- wkコードに"91"をセットする  <BR>
     * <BR>
     * 　@　@　@③パラメータ.レコード区分 = 21:入出金レコードの場合<BR> 
     * 　@　@　@　@A. パラメータ.返還金区分 = 1:返還金の場合 <BR>
     * 　@　@　@　@　@A1. パラメータ.備考 = 1:計算済の場合 <BR>
     * 　@　@　@　@　@　@- wkコードに"11"をセットする   <BR>
     * <BR>
     * 　@　@　@　@　@A2. パラメータ.備考 = 1以外の場合<BR> 
     * 　@　@　@　@　@　@- wkコードに"10"をセットする   <BR>
     * <BR>
     * 　@　@　@　@B. パラメータ.返還金区分 = 1以外の場合<BR> 
     * 　@　@　@　@　@B1. パラメータ.備考 = 1:計算済の場合 <BR>
     * 　@　@　@　@　@　@- wkコードに"01"をセットする   <BR>
     * <BR>
     * 　@　@　@　@　@B2. パラメータ.備考 = 1以外の場合<BR> 
     * 　@　@　@　@　@　@- wkコードに"00"をセットする   <BR>
     * <BR>
     * 　@（２）表示メッセージの取得<BR>  
     * <BR>
     * 　@　@QueryProcessor.doFindAllQuery()をコールする。<BR>  
     * <BR>
     * 　@　@[doFindAllQuery()にセットするパラメータ]<BR>  
     * 　@　@arg0：　@コード翻訳テーブル(code_translation) <BR> 
     * 　@　@arg1：　@オプショナル文字列(*1）  <BR>
     * 　@　@arg2：　@オブジェクト配列（*2）  <BR>
     * <BR>
     * 　@　@※取得できない場合、nullを返却する。<BR> 
     * <BR>
     * 　@　@（*1）オプショナル文字列<BR>  
     * <BR>
     * 　@　@　@　@以下の文字列を作成する。<BR>  
     * 　@　@　@　@　@"code_type = 'pls_retdv' "  <BR>
     * 　@　@　@　@＋" and institution_code = ? "  <BR>
     * 　@　@　@　@＋" and code = ?"  <BR>
     * <BR>
     * 　@　@（*2）オブジェクト配列<BR>  
     * <BR>
     * 　@　@　@　@以下の順でArrayListを作成する。<BR>  
     * 　@　@　@　@・パラメータ.会社コード  <BR>
     * 　@　@　@　@・wkコード  <BR>
     * <BR>
     * 　@（３）値セット<BR>  
     * 　@　@this.set項目値()にて項目値をセットする。<BR>  
     * <BR>
     * 　@　@[set項目値()に指定する引数]<BR>  
     * 　@　@行番号：　@引数の行番号  <BR>
     * 　@　@カラム：　@１）で取得したカラムモデル<BR>  
     * 　@　@値：　@（２）で取得した、コード翻訳テーブル.表示メッセージ<BR> 
     * @@param l_strLineNumber - (行番号)<BR>
     * 行番号
     * @@param l_strRecordDiv - (レコード区分)<BR>
     * レコード区分
     * @@param l_strReturnDiv - (返還金区分)<BR>
     * 返還金区分
     * @@param l_strRemark - (備考)<BR>
     * 備考
     * @@param l_strProductName - (銘柄名)<BR>
     * 銘柄名
     * @@param l_strInstitutionCode - (会社コード)<BR>
     * 会社コード
     * @@throws WEB3BaseException 
     */
    public void setProductName(
        String l_strLineNumber, String l_strRecordDiv, 
        String l_strReturnDiv, String l_strRemark, 
        String l_strProductName, String l_strInstitutionCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " setProductName(String, String, String, String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        //１）カラムモデル取得
        //this.getカラムモデル()にて、CSVカラムモデルを取得する。
        //[getカラムモデル()に指定する引数]
        //項目ラベル： 損益明細CSV.銘柄名ラベル
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel = 
            this.getColumnModel(PRODUCTNAME_LABEL);
        
        //２）値セット  
        //※パラメータ.レコード区分 = 20:明細レコードの場合に実施する 
        //　@　@this.set項目値()にて項目値をセットする。  
        //　@　@[set項目値()に指定する引数]  
        //　@　@行番号：　@引数の行番号  
        //　@　@カラム：　@１）で取得したカラムモデル  
        //　@　@値：　@パラメータ.銘柄名          
        if (WEB3ProfitLossRecordDivDef.DETAIL_REC.equals(l_strRecordDiv))
        {
            this.setValue(
                Integer.parseInt(l_strLineNumber), l_gentradeCsvColumnModel, l_strProductName);
            log.exiting(STR_METHOD_NAME);
        }
        
        //３）表示メッセージを取得し値セット 
        //※パラメータ.レコード区分 = 20:明細レコード以外の場合に実施する 
        else
        {
            //　@[wk変数定義] 
            //　@　@・wkコード 
            String l_strWkCode = "";
            //　@（１）wkコード設定 
            //　@　@　@①@パラメータ.レコード区分 = 00:繰越残高レコードの場合 
            if (WEB3PlsBvsCarryoverBalanceRecDef.CARRYOVER_BALANCE_REC.equals(l_strRecordDiv))
            {
                //　@　@　@　@- wkコードに"90"をセットする  
                l_strWkCode = WEB3PLSProfitLossSpecsWkCodeDef.PLS_RETDV_90;
            }
            //　@　@　@②パラメータ.レコード区分 = 10:残高レコードの場合 
            else if (WEB3ProfitLossRecordDivDef.BALANCE_REC.equals(l_strRecordDiv))
            {
                //　@　@　@　@- wkコードに"91"をセットする   
                l_strWkCode = WEB3PLSProfitLossSpecsWkCodeDef.PLS_RETDV_91;
            }
            //　@　@　@③パラメータ.レコード区分 = 21:入出金レコードの場合 
            else if (WEB3ProfitLossRecordDivDef.ORDER_REC.equals(l_strRecordDiv))
            {
                //　@　@　@　@A. パラメータ.返還金区分 = 1:返還金の場合 
                if (WEB3ReturnDivDef.RETURN.equals(l_strReturnDiv))
                {
                    //　@　@　@　@　@A1. パラメータ.備考 = 1:計算済の場合 
                    if (WEB3ProfitLossRemarkDef.CALCULATED.equals(l_strRemark))
                    {
                        //　@　@　@　@　@　@- wkコードに"11"をセットする
                        l_strWkCode = WEB3PLSProfitLossSpecsWkCodeDef.PLS_RETDV_11;
                    }
                    //　@　@　@　@　@A2. パラメータ.備考 = 1以外の場合 
                    else 
                    {
                        //　@　@　@　@　@　@- wkコードに"10"をセットする   
                        l_strWkCode = WEB3PLSProfitLossSpecsWkCodeDef.PLS_RETDV_10;
                    }
                }
                //　@　@　@　@B. パラメータ.返還金区分 = 1以外の場合 
                else
                {
                    //　@　@　@　@　@B1. パラメータ.備考 = 1:計算済の場合 
                    if (WEB3ProfitLossRemarkDef.CALCULATED.equals(l_strRemark))
                    {
                        //　@　@　@　@　@　@- wkコードに"01"をセットする   
                        l_strWkCode = WEB3PLSProfitLossSpecsWkCodeDef.PLS_RETDV_01;
                    }
                    //　@　@　@　@　@B2. パラメータ.備考 = 1以外の場合 
                    else 
                    {
                        //　@　@　@　@　@　@- wkコードに"00"をセットする 
                        l_strWkCode = WEB3PLSProfitLossSpecsWkCodeDef.PLS_RETDV_00;
                    }
                }
            }
            //（２）表示メッセージの取得  
            //QueryProcessor.doFindAllQuery()をコールする。  
            //[doFindAllQuery()にセットするパラメータ]  
            //arg0：　@コード翻訳テーブル(code_translation)  
            //arg1：　@オプショナル文字列(*1）  
            //arg2：　@オブジェクト配列（*2）  
            //※取得できない場合、nullを返却する。 
            //（*1）オプショナル文字列  
            //以下の文字列を作成する。  
            //"code_type = 'pls_retdv' "  
            //＋" and institution_code = ? "  
            //＋" and code = ?"  
            //（*2）オブジェクト配列  
            //以下の順でArrayListを作成する。  
            //・パラメータ.会社コード
            //・wkコード  
            List l_lisRecords = new ArrayList();
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append("code_type = 'pls_retdv' ");
            l_sbWhere.append("and institution_code = ? ");
            l_sbWhere.append("and code = ?");
            
            Object[] l_objWhere = 
                new Object[]{l_strInstitutionCode, l_strWkCode};
            try
            {
                QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
                l_lisRecords = l_QueryProcessor.doFindAllQuery(
                    CodeTranslationRow.TYPE,
                    l_sbWhere.toString(),
                    null,
                    l_objWhere);
            }
            catch (DataFindException l_dfe)
            {
                log.error(STR_METHOD_NAME, l_dfe);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_dfe.getMessage(),
                    l_dfe);
            }
            catch (DataQueryException l_dqe)
            {
                log.error(STR_METHOD_NAME, l_dqe);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_dqe.getMessage(),
                    l_dqe);
            }
            catch (DataNetworkException l_dne)
            {
                log.error(STR_METHOD_NAME, l_dne);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_dne.getMessage(),
                    l_dne);
            }

            String l_strDisplayMessage = "";
            if (l_lisRecords == null || l_lisRecords.isEmpty())
            {
                l_strDisplayMessage = null;
            }
            else
            {
                CodeTranslationRow l_translationRow = (CodeTranslationRow) l_lisRecords.get(0);
                l_strDisplayMessage = l_translationRow.getDisplayMessage();
            }
            //（３）値セット  
            //this.set項目値()にて項目値をセットする。  
            //[set項目値()に指定する引数]  
            //行番号：　@引数の行番号  
            //カラム：　@１）で取得したカラムモデル  
            //値：　@（２）で取得した、コード翻訳テーブル.表示メッセージ 
            this.setValue(Integer.parseInt(l_strLineNumber), l_gentradeCsvColumnModel, l_strDisplayMessage);
            
            log.exiting(STR_METHOD_NAME);
        }
    }
    
    /**
     * (set長短)<BR>
     * 「長短」をセットする。<BR>  
     * <BR>
     * １）カラムモデル取得  <BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。  <BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数]  <BR>
     * 　@項目ラベル：　@損益明細CSV.長短ラベル  <BR>
     * <BR>
     * <BR>
     * ２）検索条件値の設定  <BR>
     * <BR>
     * 　@[wk変数定義] <BR>
     * 　@　@・wkコード <BR>
     * <BR>
     * 　@（１）wkコード設定 <BR>
     * 　@　@　@①@パラメータ.長短等区分 = 1:一般上場の場合 <BR>
     * 　@　@　@　@- wkコードに"1"をセットする <BR>
     * <BR>
     * 　@　@　@②パラメータ.長短等区分 = 2:特定信用の場合 <BR>
     * 　@　@　@　@- wkコードに"2"をセットする <BR>
     * <BR>
     * 　@　@　@③パラメータ.長短等区分 = 3:長期上場の場合 <BR>
     * 　@　@　@　@　@- wkコードに"3"をセットする <BR>
     * <BR>
     * 　@　@　@④パラメータ.長短等区分 = 4:長期特定の場合 <BR>
     * 　@　@　@　@　@- wkコードに"4"をセットする <BR>
     * <BR>
     * 　@　@　@⑤パラメータ.長短等区分が上記以外の場合（nullを含む） <BR>
     * 　@　@　@　@　@- ４）値セット を行う <BR>
     * 　@　@　@　@　@　@　@値：　@にはnullを設定する <BR>
     * <BR>
     * <BR>
     * ３）表示メッセージの取得  <BR>
     * <BR>
     * 　@QueryProcessor.doFindAllQuery()をコールする。  <BR>
     * <BR>
     * 　@[doFindAllQuery()にセットするパラメータ]  <BR>
     * 　@arg0：　@コード翻訳テーブル(code_translation)  <BR>
     * 　@arg1：　@オプショナル文字列(*1）  <BR>
     * 　@arg2：　@オブジェクト配列（*2）  <BR>
     * <BR>
     * 　@※取得できない場合、nullを返却する。 <BR>
     * <BR>
     * 　@（*1）オプショナル文字列  <BR>
     * <BR>
     * 　@　@　@以下の文字列を作成する。  <BR>
     * 　@　@　@　@"code_type = 'pls_terdv' "  <BR>
     * 　@　@　@＋" and institution_code = ? "  <BR>
     * 　@　@　@＋" and code = ?"  <BR>
     * <BR>
     * 　@（*2）オブジェクト配列  <BR>
     * <BR>
     * 　@　@　@以下の順でArrayListを作成する。  <BR>
     * 　@　@　@・パラメータ.会社コード  <BR>
     * 　@　@　@・wkコード  <BR>
     * <BR>
     * ４）値セット  <BR>
     * 　@this.set項目値()にて項目値をセットする。  <BR>
     * <BR>
     * 　@[set項目値()に指定する引数]  <BR>
     * 　@行番号：　@引数の行番号  <BR>
     * 　@カラム：　@１）で取得したカラムモデル  <BR>
     * 　@値：　@３）で取得した、コード翻訳テーブル.表示メッセージ<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号
     * @@param l_strTermDiv - (長短等区分)<BR>
     * 長短等区分
     * @@param l_strInstitutionCode - (会社コード)<BR>
     * 会社コード
     * @@throws WEB3BaseException 
     */
    public void setTerm(
        int l_intLineNumber, String l_strTermDiv,
        String l_strInstitutionCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " setTerm(int, String, String)";
        log.entering(STR_METHOD_NAME);
        
        //１）カラムモデル取得
        //this.getカラムモデル()にて、CSVカラムモデルを取得する。
        //[getカラムモデル()に指定する引数]
        //項目ラベル： 損益明細CSV.長短ラベル
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel = 
            this.getColumnModel(TERM_LABEL);
        
        //２）検索条件値の設定  
        //[wk変数定義] 
        //・wkコード 
        String l_strWkCode = "";
        
        //（１）wkコード設定 
        //①@パラメータ.長短等区分 = 1:一般上場の場合 
        if (WEB3TermDivDef.NORMAL_SECTION.equals(l_strTermDiv))
        {
            //- wkコードに"1"をセットする 
            l_strWkCode = WEB3PLSProfitLossSpecsWkCodeDef.PLS_TERDV_1;
        }

        //②パラメータ.長短等区分 = 2:特定信用の場合 
        //- wkコードに"2"をセットする 
        else if (WEB3TermDivDef.SPECIAL_MARGIN.equals(l_strTermDiv))
        {
            //- wkコードに"2"をセットする 
            l_strWkCode = WEB3PLSProfitLossSpecsWkCodeDef.PLS_TERDV_2;
        }
        
        //③パラメータ.長短等区分 = 3:長期上場の場合 
        else if (WEB3TermDivDef.LONG_SECTION.equals(l_strTermDiv))
        {
            //- wkコードに"3"をセットする 
            l_strWkCode = WEB3PLSProfitLossSpecsWkCodeDef.PLS_TERDV_3;
        }
        
        //④パラメータ.長短等区分 = 4:長期特定の場合 
        else if (WEB3TermDivDef.LONG_SPECIAL.equals(l_strTermDiv))
        {
            //- wkコードに"4"をセットする 
            l_strWkCode = WEB3PLSProfitLossSpecsWkCodeDef.PLS_TERDV_4;
        }
        
        //⑤パラメータ.長短等区分が上記以外の場合（nullを含む） 
        //- ４）値セット を行う 
        //値：　@にはnullを設定する 
        else
        {
            l_strWkCode = null;
        }

        //３）表示メッセージの取得  
        //QueryProcessor.doFindAllQuery()をコールする。  
        //[doFindAllQuery()にセットするパラメータ]  
        //arg0：　@コード翻訳テーブル(code_translation)  
        //arg1：　@オプショナル文字列(*1）  
        //arg2：　@オブジェクト配列（*2）  
        //※取得できない場合、nullを返却する。 
        //（*1）オプショナル文字列  
        //以下の文字列を作成する。  
        //"code_type = 'pls_terdv' "  
        //＋" and institution_code = ? "  
        //＋" and code = ?"  
        //（*2）オブジェクト配列  
        //以下の順でArrayListを作成する。  
        //・パラメータ.会社コード  
        //・wkコード  
        List l_lisRecords = new ArrayList();
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append("code_type = 'pls_terdv' ");
        l_sbWhere.append("and institution_code = ? ");
        l_sbWhere.append("and code = ?");
        
        Object[] l_objWhere = 
            new Object[]{l_strInstitutionCode, l_strWkCode};
        try
        {
            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_QueryProcessor.doFindAllQuery(
                CodeTranslationRow.TYPE,
                l_sbWhere.toString(),
                null,
                l_objWhere);
        }
        catch (DataFindException l_dfe)
        {
            log.error(STR_METHOD_NAME, l_dfe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dfe.getMessage(),
                l_dfe);
        }
        catch (DataQueryException l_dqe)
        {
            log.error(STR_METHOD_NAME, l_dqe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dqe.getMessage(),
                l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(STR_METHOD_NAME, l_dne);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }

        String l_strDisplayMessage = "";
        if (l_lisRecords == null || l_lisRecords.isEmpty())
        {
            l_strDisplayMessage = null;
        }
        else
        {
            CodeTranslationRow l_translationRow = (CodeTranslationRow) l_lisRecords.get(0);
            l_strDisplayMessage = l_translationRow.getDisplayMessage();
        }
        //２）値セット
        //this.set項目値()にて項目値をセットする。
        //[set項目値()に指定する引数]  
        //行番号：　@引数の行番号  
        //カラム：　@１）で取得したカラムモデル  
        //値：　@パラメータ.長短
        this.setValue(l_intLineNumber, l_gentradeCsvColumnModel, l_strDisplayMessage);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set数量)<BR>
     * 「数量」をセットする。 <BR> 
     * <BR>
     * １）カラムモデル取得  <BR>
     * 　@　@this.getカラムモデル()にてCSVカラムモデルを取得する。  <BR>
     * <BR>
     * 　@　@[getカラムモデル()に指定する引数]  <BR>
     * 　@　@項目ラベル：　@損益明細CSV.数量ラベル  <BR>
     * <BR>
     * ２）値セット  <BR>
     * 　@　@this.set項目値()にて項目値をセットする。  <BR>
     * <BR>
     * 　@　@[set項目値()に指定する引数]  <BR>
     * 　@　@行番号：　@引数の行番号  <BR>
     * 　@　@カラム：　@１）で取得したカラムモデル  <BR>
     * 　@　@値：　@パラメータ.数量 <BR>
     * @@param l_strLineNumber - (行番号)<BR>
     * 行番号
     * @@param l_strQuantity - (数量)<BR>
     * 数量
     */
    public void setQuantity(String l_strLineNumber, String l_strQuantity)
    {
        final String STR_METHOD_NAME = " setQuantity(String, String)";
        log.entering(STR_METHOD_NAME);
        
        //１）カラムモデル取得
        //this.getカラムモデル()にて、CSVカラムモデルを取得する。
        //[getカラムモデル()に指定する引数]
        //項目ラベル： 損益明細CSV.数量ラベル
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel = 
            this.getColumnModel(QUANTITY_LABEL);

        //２）値セット
        //this.set項目値()にて項目値をセットする。
        //[set項目値()に指定する引数]  
        //行番号：　@引数の行番号  
        //カラム：　@１）で取得したカラムモデル  
        //値：　@パラメータ.数量
        this.setValue(Integer.parseInt(l_strLineNumber), l_gentradeCsvColumnModel, l_strQuantity);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set譲渡日)<BR>
     * 「譲渡日」をセットする。 <BR> 
     * <BR>
     * １）カラムモデル取得  <BR>
     * 　@　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR> 
     * <BR>
     * 　@　@[getカラムモデル()に指定する引数]  <BR>
     * 　@　@項目ラベル：　@損益明細CSV.譲渡日ラベル  <BR>
     * <BR>
     * ２）値セット  <BR>
     * 　@　@this.set項目値()にて項目値をセットする。  <BR>
     * <BR>
     * 　@　@[set項目値()に指定する引数]  <BR>
     * 　@　@行番号：　@引数の行番号  <BR>
     * 　@　@カラム：　@１）で取得したカラムモデル  <BR>
     * 　@　@値：　@パラメータ.譲渡日 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号
     * @@param l_datPassDate - (譲渡日)<BR>
     * 譲渡日
     */
    public void setPassDate(int l_intLineNumber, Date l_datPassDate)
    {
        final String STR_METHOD_NAME = " setPassDate(int, Date)";
        log.entering(STR_METHOD_NAME);
        
        //１）カラムモデル取得
        //this.getカラムモデル()にて、CSVカラムモデルを取得する。
        //[getカラムモデル()に指定する引数]
        //項目ラベル： 損益明細CSV.譲渡日ラベル
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel = 
            this.getColumnModel(PASSDATE_LABEL);

        //２）値セット
        //this.set項目値()にて項目値をセットする。
        //[set項目値()に指定する引数]  
        //行番号：　@引数の行番号  
        //カラム：　@１）で取得したカラムモデル  
        //値：　@パラメータ.譲渡日
        this.setValue(l_intLineNumber, l_gentradeCsvColumnModel, l_datPassDate);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set譲渡金額)<BR>
     * 「譲渡金額」をセットする。 <BR> 
     * <BR>
     * １）カラムモデル取得  <BR>
     * 　@　@this.getカラムモデル()にてCSVカラムモデルを取得する。  <BR>
     * <BR>
     * 　@　@[getカラムモデル()に指定する引数]  <BR>
     * 　@　@項目ラベル：　@損益明細CSV.譲渡金額ラベル  <BR>
     * <BR>
     * ２）値セット  <BR>
     * 　@　@this.set項目値()にて項目値をセットする。  <BR>
     * <BR>
     * 　@　@[set項目値()に指定する引数]  <BR>
     * 　@　@行番号：　@引数の行番号  <BR>
     * 　@　@カラム：　@１）で取得したカラムモデル  <BR>
     * 　@　@値：　@パラメータ.譲渡金額 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号
     * @@param l_strPassAmount - (譲渡金額)<BR>
     * 譲渡金額
     */
    public void setPassAmount(int l_intLineNumber, String l_strPassAmount)
    {
        final String STR_METHOD_NAME = " setPassAmount(int, String)";
        log.entering(STR_METHOD_NAME);
        
        //１）カラムモデル取得
        //this.getカラムモデル()にて、CSVカラムモデルを取得する。
        //[getカラムモデル()に指定する引数]
        //項目ラベル： 損益明細CSV.譲渡金額ラベル
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel = 
            this.getColumnModel(PASSAMOUNT_LABEL);

        //２）値セット
        //this.set項目値()にて項目値をセットする。
        //[set項目値()に指定する引数]  
        //行番号：　@引数の行番号  
        //カラム：　@１）で取得したカラムモデル  
        //値：　@パラメータ.譲渡金額
        this.setValue(l_intLineNumber, l_gentradeCsvColumnModel, l_strPassAmount);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set取得日)<BR>
     * 「取得日」をセットする。  <BR>
     * <BR>
     * １）カラムモデル取得  <BR>
     * 　@　@this.getカラムモデル()にてCSVカラムモデルを取得する。  <BR>
     * <BR>
     * 　@　@[getカラムモデル()に指定する引数]  <BR>
     * 　@　@項目ラベル：　@損益明細CSV.取得日ラベル  <BR>
     * <BR>
     * ２）値セット  <BR>
     * 　@　@this.set項目値()にて項目値をセットする。  <BR>
     * <BR>
     * 　@　@[set項目値()に指定する引数]  <BR>
     * 　@　@行番号：　@引数の行番号  <BR>
     * 　@　@カラム：　@１）で取得したカラムモデル  <BR>
     * 　@　@値：　@パラメータ.取得日<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号
     * @@param l_datGetDate - (取得日)<BR>
     * 取得日
     */
    public void setGetDate(int l_intLineNumber, Date l_datGetDate)
    {
        final String STR_METHOD_NAME = " setGetDate(int, Date)";
        log.entering(STR_METHOD_NAME);
        
        //１）カラムモデル取得
        //this.getカラムモデル()にて、CSVカラムモデルを取得する。
        //[getカラムモデル()に指定する引数]
        //項目ラベル： 損益明細CSV.取得日ラベル
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel = 
            this.getColumnModel(GETDATE_LABEL);

        //２）値セット
        //this.set項目値()にて項目値をセットする。
        //[set項目値()に指定する引数]  
        //行番号：　@引数の行番号  
        //カラム：　@１）で取得したカラムモデル  
        //値：　@パラメータ.取得日
        this.setValue(l_intLineNumber, l_gentradeCsvColumnModel, l_datGetDate);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set取得費等)<BR>
     * 「取得費等」をセットする。 <BR> 
     * <BR>
     * １）カラムモデル取得  <BR>
     * 　@　@this.getカラムモデル()にてCSVカラムモデルを取得する。  <BR>
     * <BR>
     * 　@　@[getカラムモデル()に指定する引数]  <BR>
     * 　@　@項目ラベル：　@損益明細CSV.取得費等ラベル  <BR>
     * <BR>
     * ２）値セット  <BR>
     * 　@　@this.set項目値()にて項目値をセットする。  <BR>
     * <BR>
     * 　@　@[set項目値()に指定する引数]  <BR>
     * 　@　@行番号：　@引数の行番号  <BR>
     * 　@　@カラム：　@１）で取得したカラムモデル  <BR>
     * 　@　@値：　@パラメータ.取得費等 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号
     * @@param l_strGetAmount - (取得費等)<BR>
     * 取得費等
     */
    public void setGetAmount(int l_intLineNumber, String l_strGetAmount)
    {
        final String STR_METHOD_NAME = " setGetAmount(int, String)";
        log.entering(STR_METHOD_NAME);
        
        //１）カラムモデル取得
        //this.getカラムモデル()にて、CSVカラムモデルを取得する。
        //[getカラムモデル()に指定する引数]
        //項目ラベル： 損益明細CSV.取得費等ラベル
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel = 
            this.getColumnModel(GETAMOUNT_LABEL);

        //２）値セット
        //this.set項目値()にて項目値をセットする。
        //[set項目値()に指定する引数]  
        //行番号：　@引数の行番号  
        //カラム：　@１）で取得したカラムモデル  
        //値：　@パラメータ.取得費等
        this.setValue(l_intLineNumber, l_gentradeCsvColumnModel, l_strGetAmount);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set損益)<BR>
     * 「損益」をセットする。 <BR> 
     * <BR>
     * １）カラムモデル取得  <BR>
     * 　@　@this.getカラムモデル()にてCSVカラムモデルを取得する。  <BR>
     * <BR>
     * 　@　@[getカラムモデル()に指定する引数]  <BR>
     * 　@　@項目ラベル：　@損益明細CSV.損益ラベル  <BR>
     * <BR>
     * ２）値セット  <BR>
     * 　@　@this.set項目値()にて項目値をセットする。  <BR>
     * <BR>
     * 　@　@[set項目値()に指定する引数]  <BR>
     * 　@　@行番号：　@引数の行番号  <BR>
     * 　@　@カラム：　@１）で取得したカラムモデル  <BR>
     * 　@　@値：　@パラメータ.損益 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号
     * @@param l_strProlossAmount - (損益)<BR>
     * 損益
     */
    public void setProlossAmount(int l_intLineNumber, String l_strProlossAmount)
    {
        final String STR_METHOD_NAME = " setProlossAmount(int, String)";
        log.entering(STR_METHOD_NAME);
        
        //１）カラムモデル取得
        //this.getカラムモデル()にて、CSVカラムモデルを取得する。
        //[getカラムモデル()に指定する引数]
        //項目ラベル： 損益明細CSV.損益ラベル
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel = 
            this.getColumnModel(PROLOSSAMOUNT_LABEL);

        //２）値セット
        //this.set項目値()にて項目値をセットする。
        //[set項目値()に指定する引数]  
        //行番号：　@引数の行番号  
        //カラム：　@１）で取得したカラムモデル  
        //値：　@パラメータ.損益
        this.setValue(l_intLineNumber, l_gentradeCsvColumnModel, l_strProlossAmount);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set累計損益)<BR>
     * 「累計損益」をセットする。<BR>  
     * <BR>
     * １）カラムモデル取得  <BR>
     * 　@　@this.getカラムモデル()にてCSVカラムモデルを取得する。  <BR>
     * <BR>
     * 　@　@[getカラムモデル()に指定する引数]  <BR>
     * 　@　@項目ラベル：　@損益明細CSV.累計損益ラベル  <BR>
     * <BR>
     * ２）値セット  <BR>
     * 　@　@this.set項目値()にて項目値をセットする。  <BR>
     * <BR>
     * 　@　@[set項目値()に指定する引数]  <BR>
     * 　@　@行番号：　@引数の行番号  <BR>
     * 　@　@カラム：　@１）で取得したカラムモデル  <BR>
     * 　@　@値：　@パラメータ.累計損益<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号
     * @@param l_strTotalProlossAmount - (累計損益)<BR>
     * 累計損益
     */
    public void setTotalProlossAmount(int l_intLineNumber, String l_strTotalProlossAmount)
    {
        final String STR_METHOD_NAME = " setTotalProlossAmount(int, String)";
        log.entering(STR_METHOD_NAME);
        
        //１）カラムモデル取得
        //this.getカラムモデル()にて、CSVカラムモデルを取得する。
        //[getカラムモデル()に指定する引数]
        //項目ラベル： 損益明細CSV.累計損益ラベル
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel = 
            this.getColumnModel(TOTALPROLOSSAMOUNT_LABEL);

        //２）値セット
        //this.set項目値()にて項目値をセットする。
        //[set項目値()に指定する引数]  
        //行番号：　@引数の行番号  
        //カラム：　@１）で取得したカラムモデル  
        //値：　@パラメータ.累計損益
        this.setValue(l_intLineNumber, l_gentradeCsvColumnModel, l_strTotalProlossAmount);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set課税対象額)<BR>
     * 「課税対象額」をセットする。 <BR> 
     * <BR>
     * １）カラムモデル取得  <BR>
     * 　@　@this.getカラムモデル()にてCSVカラムモデルを取得する。  <BR>
     * <BR>
     * 　@　@[getカラムモデル()に指定する引数]  <BR>
     * 　@　@項目ラベル：　@損益明細CSV.課税対象額ラベル  <BR>
     * <BR>
     * ２）値セット  <BR>
     * 　@　@this.set項目値()にて項目値をセットする。  <BR>
     * <BR>
     * 　@　@[set項目値()に指定する引数]  <BR>
     * 　@　@行番号：　@引数の行番号  <BR>
     * 　@　@カラム：　@１）で取得したカラムモデル  <BR>
     * 　@　@値：　@パラメータ.課税対象額 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号
     * @@param l_strTaxableAmount - (課税対象額)<BR>
     * 課税対象額
     */
    public void setTaxableAmount(int l_intLineNumber, String l_strTaxableAmount)
    {
        final String STR_METHOD_NAME = " setTaxableAmount(int, String)";
        log.entering(STR_METHOD_NAME);
        
        //１）カラムモデル取得
        //this.getカラムモデル()にて、CSVカラムモデルを取得する。
        //[getカラムモデル()に指定する引数]
        //項目ラベル： 損益明細CSV.課税対象額ラベル
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel = 
            this.getColumnModel(TAXABLEAMOUNT_LABEL);

        //２）値セット
        //this.set項目値()にて項目値をセットする。
        //[set項目値()に指定する引数]  
        //行番号：　@引数の行番号  
        //カラム：　@１）で取得したカラムモデル  
        //値：　@パラメータ.課税対象額
        this.setValue(l_intLineNumber, l_gentradeCsvColumnModel, l_strTaxableAmount);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set徴収税額)<BR>
     * 「徴収税額」をセットする。  <BR>
     * <BR>
     * １）カラムモデル取得  <BR>
     * 　@　@this.getカラムモデル()にてCSVカラムモデルを取得する。  <BR>
     * <BR>
     * 　@　@[getカラムモデル()に指定する引数]  <BR>
     * 　@　@項目ラベル：　@損益明細CSV.徴収税額ラベル  <BR>
     * <BR>
     * ２）値セット  <BR>
     * 　@　@this.set項目値()にて項目値をセットする。  <BR>
     * <BR>
     * 　@　@[set項目値()に指定する引数]  <BR>
     * 　@　@行番号：　@引数の行番号  <BR>
     * 　@　@カラム：　@１）で取得したカラムモデル  <BR>
     * 　@　@値：　@パラメータ.徴収税額<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号
     * @@param l_strCollectTaxAmount - (徴収税額)<BR>
     * 徴収税額
     */
    public void setCollectTaxAmount(int l_intLineNumber, String l_strCollectTaxAmount)
    {
        final String STR_METHOD_NAME = " setCollectTaxAmount(int, String)";
        log.entering(STR_METHOD_NAME);
        
        //１）カラムモデル取得
        //this.getカラムモデル()にて、CSVカラムモデルを取得する。
        //[getカラムモデル()に指定する引数]
        //項目ラベル： 損益明細CSV.徴収税額ラベル
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel = 
            this.getColumnModel(COLLECTTAXAMOUNT_LABEL);

        //２）値セット
        //this.set項目値()にて項目値をセットする。
        //[set項目値()に指定する引数]  
        //行番号：　@引数の行番号  
        //カラム：　@１）で取得したカラムモデル  
        //値：　@パラメータ.徴収税額
        this.setValue(l_intLineNumber, l_gentradeCsvColumnModel, l_strCollectTaxAmount);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set徴収税額（国税）)<BR>
     * 「徴収税額（国税）」をセットする。  <BR>
     * <BR>
     * １）カラムモデル取得  <BR>
     * 　@　@this.getカラムモデル()にてCSVカラムモデルを取得する。  <BR>
     * <BR>
     * 　@　@[getカラムモデル()に指定する引数]  <BR>
     * 　@　@項目ラベル：　@損益明細CSV.徴収税額（国税）ラベル  <BR>
     * <BR>
     * ２）値セット  <BR>
     * 　@　@this.set項目値()にて項目値をセットする。  <BR>
     * <BR>
     * 　@　@[set項目値()に指定する引数]  <BR>
     * 　@　@行番号：　@引数の行番号  <BR>
     * 　@　@カラム：　@１）で取得したカラムモデル  <BR>
     * 　@　@値：　@パラメータ.徴収税額（国税）<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号
     * @@param l_strCollectTaxNAmount - (徴収税額（国税）)<BR>
     * 徴収税額（国税）
     */
    public void setCollectTaxNAmount(int l_intLineNumber, String l_strCollectTaxNAmount)
    {
        final String STR_METHOD_NAME = " setCollectTaxNAmount(int, String)";
        log.entering(STR_METHOD_NAME);
        
        //１）カラムモデル取得
        //this.getカラムモデル()にて、CSVカラムモデルを取得する。
        //[getカラムモデル()に指定する引数]
        //項目ラベル： 損益明細CSV.徴収税額（国税）ラベル
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel = 
            this.getColumnModel(COLLECTTAXNAMOUNT_LABEL);

        //２）値セット
        //this.set項目値()にて項目値をセットする。
        //[set項目値()に指定する引数]  
        //行番号：　@引数の行番号  
        //カラム：　@１）で取得したカラムモデル  
        //値：　@パラメータ.徴収税額（国税）
        this.setValue(l_intLineNumber, l_gentradeCsvColumnModel, l_strCollectTaxNAmount);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set徴収税額（地方税）)<BR>
     * 「徴収税額（地方税）」をセットする。<BR>  
     * <BR>
     * １）カラムモデル取得  <BR>
     * 　@　@this.getカラムモデル()にてCSVカラムモデルを取得する。  <BR>
     * <BR>
     * 　@　@[getカラムモデル()に指定する引数]  <BR>
     * 　@　@項目ラベル：　@損益明細CSV.徴収税額（地方税）ラベル  <BR>
     * <BR>
     * ２）値セット  <BR>
     * 　@　@this.set項目値()にて項目値をセットする。  <BR>
     * <BR>
     * 　@　@[set項目値()に指定する引数]  <BR>
     * 　@　@行番号：　@引数の行番号  <BR>
     * 　@　@カラム：　@１）で取得したカラムモデル  <BR>
     * 　@　@値：　@パラメータ.徴収税額（地方税）<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号
     * @@param l_strCollectTaxLAmount - (徴収税額（地方税）)<BR>
     * 徴収税額（地方税）
     */
    public void setCollectTaxLAmount(int l_intLineNumber, String l_strCollectTaxLAmount)
    {
        final String STR_METHOD_NAME = " setCollectTaxLAmount(int, String)";
        log.entering(STR_METHOD_NAME);
        
        
        //１）カラムモデル取得
        //this.getカラムモデル()にて、CSVカラムモデルを取得する。
        //[getカラムモデル()に指定する引数]
        //項目ラベル： 損益明細CSV.徴収税額（地方税）ラベル
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel = 
            this.getColumnModel(COLLECTTAXLAMOUNT_LABEL);

        //２）値セット
        //this.set項目値()にて項目値をセットする。
        //[set項目値()に指定する引数]  
        //行番号：　@引数の行番号  
        //カラム：　@１）で取得したカラムモデル  
        //値：　@パラメータ.徴収税額（地方税）
        this.setValue(l_intLineNumber, l_gentradeCsvColumnModel, l_strCollectTaxLAmount);
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
