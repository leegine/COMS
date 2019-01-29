head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeCsvDataModel.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : CSVデータモデル(WEB3GentradeCsvDataModel.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/07/28 石 炎(中訊) 新規作成
Revesion History    : 2007/12/21 武 波(中訊) 仕様変更 モデル303
Revesion History    : 2007/12/25 武 波(中訊) 仕様変更 モデル305
Revesion History    : 2008/01/10 栄 イ(中訊) 仕様変更 モデル307
Revesion History    : 2008/01/26 栄 イ(中訊) 仕様変更 モデル314、315
*/

package webbroker3.gentrade;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.define.WEB3GentradeDateAnalysisFlagDivDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (CSVデータモデル) <BR>
 * （WEB3CsvDataModel） <BR>
 * CSVファ@イルデータモデルクラス。 <BR>
 * CSVダウンロード／アップロードにて使用する。<BR>
 * <BR>
 * [CSVファ@イルフォーマットについて] <BR>
 * 1行目（index=0）はキーヘッダと認識する。 <BR>
 * 2行目（index=1）は行ヘッダ（タイトル文字列）と認識する。<BR>
 * ※ 但し、（isカラムヘッダ出力 == false）の場合、<BR>
 * タイトル文字列なしと判定する。<BR>
 * <BR>
 * 以降を明細行と認識する。<BR>
 * <BR>
 * --- CSV file sample---------------<BR>
 * 2004/06/21 16:00:03,86010,(株)大和証券グループ本社<BR>
 * 部店コード,顧客コード,顧客名,数量<BR>
 * 625,2512211,増田　@哲也,5000<BR>
 * 624,2412339,森田　@正樹,5500<BR>
 * 610,2110991,齋藤　@貴弥,2000<BR>
 * 610,2121400,羽賀　@輝明,2500<BR>
 * --- CSV file sample---------------<BR>
 */
public class WEB3GentradeCsvDataModel
{

    /**
     * ログ出力オブジェクト。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GentradeCsvDataModel.class);

    /**
     * （regex）<BR>
     * 区切り文字<BR>
     * CSVファ@イル内の項目区切るデリミッタを定義する。<BR>
     * ※　@カンマ（，）<BR>
     */
    public static String regex = ",";

    /**
     * （keyHeader）<BR>
     * <BR>
     * キーヘッダ。<BR>
     * CVSファ@イルの1行目（index=0）に出力されるデータの配列。<BR>
     */
    protected String[] keyHeader;

    /**
     * （columnHeader）<BR>
     * <BR>
     * カラムヘッダ。<BR>
     * 各カラムの項目ラベルと属性を定義するオブジェクトの配列。<BR>
     * 項目ラベルは、ダウンロードの場合<BR>
     * CVSファ@イルの2行目（index=1）に出力される。<BR>
     */
    protected WEB3GentradeCsvColumnModel[] columnHeader;

    /**
     * (明細行) <BR>
     * （rows）<BR>
     * <BR>
     * 明細行データの集合。<BR>
     * CVSファ@イルの明細行に出力されるデータ行の集合。<BR>
     * <BR>
     * ※　@ArrayListにセットする要素（１明細行のデータが格納される）は、<BR>
     * Objectの配列（：Object[]）。<BR>
     */
    protected ArrayList rows = new ArrayList();

    /**
     * (CSVデータモデル) <BR>
     * （WEB3CsvDataModel）<BR>
     * デフォルトコンストラクタ<BR>
     * @@return webbroker3.gentrade.WEB3CsvDataModel
     * @@roseuid 40E2179203C1
     */
    public WEB3GentradeCsvDataModel()
    {

    }

    /**
     * (isカラムヘッダ行出力) <BR>
     * CSVファ@イルにカラムヘッダ（タイトル文字列）行を出力する場合はtrue、<BR>
     * 不要な場合はfalseを返却する。<BR>
     * <BR>
     * 当該クラスでは、Default値trueを返却する。<BR>
     * （不要な場合は、サブクラスにてoverrideする）<BR>
     * @@return boolean
     * @@roseuid 40F5032500D4
     */
    public boolean isColumnHeadOutput()
    {
        return true;
    }

    /**
     * (get項目値) <BR>
     * （getValue）<BR>
     * 指定の行／列より、値を返却する。<BR>
     * <BR>
     * １）　@行取得<BR>
     * 　@明細行から引数の行番号の要素を取得し、Object[]型に変換する。<BR>
     * <BR>
     * ２）　@値取得<BR>
     * 　@１）で取得したObject配列より、引数のカラムが示すカラム番号の要素<BR>
     *   を返却する。<BR>
     * <BR>
     * // Object l_rowValues[] = (Object[])this.明細行.get(行番号);<BR>
     * // return l_rowValues[カラム.get列番号()];<BR>
     * @@param l_intLineNumber - 行番号 <BR>
     * 行番号を指定する。 <BR>
     * 
     * @@param l_csvColumnModel - カラム <BR>
     * カラム（列）を指定する。<BR>
     * 
     * @@return Object
     * @@roseuid 40E21AAE0299
     */
    public Object getValue(
        int l_intLineNumber,
        WEB3GentradeCsvColumnModel l_csvColumnModel)
    {
        final String STR_METHOD_NAME = "getValue(int," +
            "WEB3GentradeCsvColumnModel)";
        log.entering(STR_METHOD_NAME);
        
        //１）　@行取得
        //明細行から引数の行番号の要素を取得し、Object[]型に変換する。
        Object l_rowValues[] = (Object[])this.rows.get(l_intLineNumber);

        //１）で取得したObject配列より、引数のカラムが示すカラム番号の要素を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_rowValues[l_csvColumnModel.getColumnNumber()];
    }

    /**
     * (get項目値From明細行) <BR>
     * （getValueFromTokenline）<BR>
     * <BR>
     * 引数の明細行の指定列より値を返却する。<BR>
     * <BR>
     * １）　@明細行解析 <BR>
     * 　@明細行文字列.split(CSVデータモデル.区切り文字)にて、<BR>
     *   区切り文字ごとのtoken[]に分割する。<BR>
     * <BR>
     * ２）　@値取得 <BR>
     * 　@１）で取得したtoken[]の、指定列(*1)番目の要素を取得し、<BR>
     * カラム.get項目型()が示すデータ型にて返却する。<BR>
     * <BR>
     * 　@(*1) 指定列indexの取得<BR>
     * 　@カラム.get列番号()<BR>
     * <BR>
     *
     * @@param l_strRows - 明細行文字列 <BR>
     * <BR>
     * ※ ","を区切り文字にした明細行文字列。<BR>
     * 
     * @@param l_csvColumnModel - カラム <BR>
     * カラム（列）を指定する。<BR>
     * 
     * @@return Object
     * @@throws WEB3SystemLayerException
     * @@roseuid 40F48DAD0312
     * @@see String#split(String)
     */
    public Object getValueFromTokenline(
        String l_strRows,
        WEB3GentradeCsvColumnModel l_csvColumnModel)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getValueFromTokenline(String," +
            "WEB3GentradeCsvColumnModel)";
        log.entering(STR_METHOD_NAME);
        
        if ((l_strRows == null) || (l_strRows.equals("")))
        {
            log.error("明細行文字列が不正");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "."
                    + STR_METHOD_NAME,
                "明細行文字列 = null");     
        }
        if ((l_csvColumnModel == null))
        {
            log.error("カラムが不正");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "."
                    + STR_METHOD_NAME,
                "カラムカラム（列） = null");     
        }
              
        //１）　@明細行解析
        String[] token = l_strRows.split(regex);
        
        //１）で取得したtoken[]の、指定列(*1)番目の要素を取得し、
        //    カラム.get項目型()が示すデータ型にて返却する。
        int l_intColumnModel = l_csvColumnModel.getColumnType();
        try
        {
            //項目型_文字列
            if (l_intColumnModel == WEB3GentradeCsvColumnModel.STRINGTYPE)
            {
                log.exiting(STR_METHOD_NAME);
                return token[l_csvColumnModel.getColumnNumber()];
            }
            //項目型_日付
            else if (l_intColumnModel == WEB3GentradeCsvColumnModel.DATETYPE)
            {
                DateFormat l_dateFormat = l_csvColumnModel.getDateFormat();
                Date l_datValue = l_dateFormat.parse(token[l_csvColumnModel.getColumnNumber()]);
                log.exiting(STR_METHOD_NAME);
                return l_datValue;
            }
            //項目型_数値（double）
            else if (l_intColumnModel == WEB3GentradeCsvColumnModel.DOUBLETYPE)
            {
                Double l_dblValue = new Double(token[l_csvColumnModel.getColumnNumber()]);
                log.exiting(STR_METHOD_NAME);
                return l_dblValue;
            }
            //項目型_数値（int）
            else if (l_intColumnModel == WEB3GentradeCsvColumnModel.INTEGERTYPE)
            {
                Integer l_intValue = Integer.valueOf(token[l_csvColumnModel.getColumnNumber()]);
                log.exiting(STR_METHOD_NAME);
                return l_intValue;
            }
            //項目型_数値
            else if (l_intColumnModel == WEB3GentradeCsvColumnModel.LONGTYPE)
            {
                Long l_lngValue = Long.valueOf(token[l_csvColumnModel.getColumnNumber()]);
                log.exiting(STR_METHOD_NAME);
                return l_lngValue;
            }
            //項目型_日付時間
            else if (l_intColumnModel == WEB3GentradeCsvColumnModel.TIMESTAMPTYPE)
            {
                DateFormat l_df = l_csvColumnModel.getDateFormat();
                Timestamp l_tsValue = new Timestamp(l_df.parse(token[l_csvColumnModel.getColumnNumber()]).getTime());
                log.exiting(STR_METHOD_NAME);
                return l_tsValue;
            }
            //token[]の要素数 != this.カラムヘッダ[]の要素数が同じでない場合は、
            //例外をスローする。
            else
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80022,
                    this.getClass().getName() + "." + STR_METHOD_NAME
                    );  
            }
        }
        //token[index]を、this.カラムヘッダ[index].get項目型()に
        //変換できない場合は、例外をスローする。
        catch (ParseException pex)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80023,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                pex.getMessage(),
                pex);
        }
    }

    /**
     * (get項目値文字列) <BR>
     * （getStringValue）<BR>
     * 指定の行／カラムより、値を文字列型にて返却する。<BR>
     * <BR>
     * １）　@値取得 <BR>
     * 　@this.get項目値()にて、該当項目値を取得する。<BR>
     * <BR>
     * 　@[this.get項目値()に指定する引数]<BR>
     * 　@行番号：　@引数の行番号<BR>
     * 　@カラム：　@引数のカラム<BR>
     * <BR>
     * ２）　@フォーマットした文字列返却<BR>
     * 　@引数のカラム.get日付フォーマット()にて、日付フォーマットを取得する。<BR>
     * <BR>
     * 　@－（日付フォーマット == null）の場合は、<BR>
     * 　@　@１）で取得した項目値をtoString()にて文字列に変換し返却する。<BR>
     * 　@　@※　@末尾が".0"の場合は、".0"を削除する。<BR>
     * <BR>
     * 　@－（日付フォーマット != null）の場合は、<BR>
     * 　@　@１）で取得した項目値を<BR>
     *     取得した日付フォーマットにてformatした文字列に変換し返却する。<BR>
     * @@param l_intLineNumber - 行番号 <BR>
     * 行番号を指定する。<BR>
     * @@param l_csvColumnModel - カラム<BR>
     * カラム（列）を指定する。<BR>
     * 
     * @@return String
     * @@roseuid 40E2498B0076
     */
    public String getStringValue(
        int l_intLineNumber,
        WEB3GentradeCsvColumnModel l_csvColumnModel)
    {
        final String STR_METHOD_NAME = "getStringValue(int,WEB3GentradeCsvColumnModel)";
        log.entering(STR_METHOD_NAME);
        
        String l_strReturn = null;
        
        //１）　@値取得 
        Object l_obj = null;
        l_obj = this.getValue(l_intLineNumber, l_csvColumnModel);
        Date l_dat = null;
        String l_strTemp = null;

        if (l_obj == null)
        {
            l_strTemp = "";
            return l_strTemp;
        }
        if (l_obj instanceof Date)
        {
            l_dat = (Date)l_obj;
        }
        else
        {
            l_strTemp = l_obj.toString();
        }
        
        //日付フォーマットを取得する
        DateFormat l_dateFormat = l_csvColumnModel.getDateFormat();
        
        //*（日付フォーマット == null）の場合は、<BR>
        //* １）で取得した項目値をtoString()にて文字列に変換し返却する。<BR>
        //* ※　@末尾が".0"の場合は、".0"を削除する。
        if (l_dateFormat == null)
        {
            
            if (l_strTemp.length() > 2)
            {
                int l_intLength = l_strTemp.length();
                String l_strSubString = l_strTemp.substring(l_intLength - 2, l_intLength);
                if (".0".equals(l_strSubString))
                {
                    l_strReturn = l_strTemp.substring(0, l_intLength - 2);
                }
                else
                {
                    l_strReturn = l_strTemp;
                }
            }
            else
            {
                l_strReturn = l_strTemp;
            }
        }
        //*（日付フォーマット != null）の場合は、<BR>
        //* １）で取得した項目値を<BR>
        //*  取得した日付フォーマットにてformatした文字列に変換し返却する。
        else
        {
            if(l_dat == null)
            {
                return l_strTemp;
            }
            l_strReturn = l_dateFormat.format(l_dat);
            
        }

        log.exiting(STR_METHOD_NAME);
        return l_strReturn;
    }

    /**
     * (getCSVファ@イル行) <BR>
     * （getCsvFileLines）<BR>
     * <BR>
     * CSVファ@イルに出力するデータを、行毎の配列にて返却する。<BR>
     * <BR>
     * １）　@ArrayList生成<BR>
     * 　@new ArrayList() <BR>
     * <BR>
     * ２）　@キーヘッダ行出力<BR>
     *(this.キーヘッダ[] != nullの場合) <BR>
     *  this.キーヘッダ[]の各要素を区切り文字(*1)で区切った文字列に連結し、<BR>
     *  ArrayListに追加する。<BR>
     *(上記以外の場合)<BR>
     *  space文字列(" ")をArrayListに追加する。<BR>
     * <BR>
     * (*1) 区切り文字<BR>
     * CSVデータモデル.区切り文字<BR>
     * <BR>
     * ３）　@ヘッダ行（タイトル文字列）出力<BR>
     * 　@（isカラムヘッダ出力() == true）の場合のみ処理実施。<BR>
     * 　@this.カラムヘッダ[]の各要素より項目ラベルを取得する。<BR>
     * 各項目ラベルを、区切り文字(*1)で区切った文字列に連結し、<BR>
     * ArrayListに追加する。<BR>
     * <BR>
     * ４）　@明細行出力<BR>
     * 　@this.明細行（：ArrayList）の要素番号（rowIndex）毎に、<BR>
     *   以下を繰り返す。<BR>
     * 　@// for (int rowIndex = 0; rowIndex &lt; this.明細行.size(); rowIndex ++) <BR>
     *
     * <BR>
     * 　@４－１）　@各項目のデータ取得<BR>
     * 　@　@this.カラムヘッダ（：CSVカラムモデル[]）の要素数（columnIndex）分、<BR>
     * 項目値取得を繰り返す。<BR>
     * 　@　@// for (int columnIndex = 0; columnIndex &lt; this.カラムヘッダ.length; columnIndex ++)<BR>
     * 　@　@this.get項目値文字列()にて、各カラムのデータ文字列を取得する。<BR>
     * <BR>
     * 　@　@[get項目値文字列()に指定する引数]<BR>
     * 　@　@行番号：　@明細行の要素番号（rowIndex）<BR>
     * 　@　@カラム：　@this.カラムヘッダ[columnIndex]<BR>
     * <BR>
     * 　@４－２）　@データ行作成<BR>
     * 　@　@４－１）で取得した各項目値を、区切り文字(*1)で<BR>
     * 区切った文字列に連結し、ArrayListに追加する。<BR>
     * <BR>
     * ５）　@CSVファ@イル行返却<BR>
     * 　@ArrayListを配列に変換（toArray()）し、返却する。<BR>
     * @@return String[]
     * @@roseuid 40E21CD901BE
     */
    public String[] getCsvFileLines()
    {
        final String STR_METHOD_NAME = "getCsvFileLines()";
        log.entering(STR_METHOD_NAME);
        
        //１）　@ArrayList生成
        ArrayList l_arrayList = new ArrayList();
        
        //２）　@キーヘッダ行出力
        //(this.キーヘッダ[] != nullの場合) 
        //this.キーヘッダ[]の各要素を区切り文字(*1)で区切った文字列に連結し、ArrayListに追加する。
        //(上記以外の場合)
        //space文字列(" ")をArrayListに追加する。
        String l_strkeyHeader = "";
        if (this.keyHeader != null)
        {
            for (int l_intLoop = 0;l_intLoop < this.keyHeader.length;l_intLoop++)
            {
                if ("".equals(l_strkeyHeader))
                {
                    l_strkeyHeader = this.keyHeader[l_intLoop];
                }
                else
                {
                    l_strkeyHeader =
                        l_strkeyHeader + regex + this.keyHeader[l_intLoop];
                }
            }
            l_arrayList.add(l_strkeyHeader);
        }
        else
        {
            l_arrayList.add(" ");
        }
        
        //３）　@ヘッダ行（タイトル文字列）出力
        if (isColumnHeadOutput() == true)
        {
            String l_strColumnLabel = "";
            for (int l_intLoop = 0; l_intLoop < this.columnHeader.length; l_intLoop++)
            {
                if ("".equals(l_strColumnLabel))
                {
                    l_strColumnLabel = this.columnHeader[l_intLoop].getColumnLabel();
                }
                else
                {
                    l_strColumnLabel = l_strColumnLabel + regex + this.columnHeader[l_intLoop].getColumnLabel();
                }
            }
            
            l_arrayList.add(l_strColumnLabel);
        }
        
        //４）　@明細行出力
        int l_intSize = this.rows.size();
        for (int l_intRowIndex = 0; l_intRowIndex < l_intSize; l_intRowIndex++)
        {
            //４－１）　@各項目のデータ取得
            String l_strAddValue = "";
            for (int l_intColumnIndex = 0; l_intColumnIndex < this.columnHeader.length; l_intColumnIndex++)
            {
                String l_strValue = this.getStringValue(l_intRowIndex,columnHeader[l_intColumnIndex]);
            
                //４－２）　@データ行作成
                if ("".equals(l_strAddValue))
                {
                    l_strAddValue = l_strValue;
                }
                else
                {
                    l_strAddValue = l_strAddValue + regex + l_strValue;
                }            
            }
            l_arrayList.add(l_strAddValue);
        }

        //５）　@CSVファ@イル行返却
        Object[] l_objs = l_arrayList.toArray();
        String[] l_strReturns = new String[l_objs.length];
        for (int i = 0; i < l_objs.length; i++)
        {
            l_strReturns[i] = (String)l_objs[i];
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_strReturns;
    }

    /**
     * (getカラムモデル) <BR>
     * 引数の項目ラベルに該当するカラムを、カラムヘッダ配列をより取得する。<BR>
     * <BR>
     * １）　@this.カラムヘッダ[]の各要素より、項目ラベルを取得する。<BR>
     * ２）　@取得した項目ラベルが引数の項目ラベルと一致している要素を<BR>
     * 返却する。<BR>
     * @@param l_strColumnLabel - 項目ラベル <BR>
     * 行ヘッダに出力する項目ラベル<BR>
     * 
     * @@return WEB3GentradeCsvColumnModel
     * @@roseuid 40E260AB02B8
     */
    public WEB3GentradeCsvColumnModel getColumnModel(String l_strColumnLabel)
    {
        final String STR_METHOD_NAME = "getColumnModel(String)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GentradeCsvColumnModel l_returnColumnModel = null;
        
        //１）　@this.カラムヘッダ[]の各要素より、項目ラベルを取得する。
        int l_intLength = this.columnHeader.length;
        for (int l_intLoop = 0; l_intLoop < l_intLength; l_intLoop++)
        {
            //２）　@取得した項目ラベルが引数の項目ラベルと一致している要素を返却する。
            String l_strLabel = this.columnHeader[l_intLoop].getColumnLabel(); 
            if (l_strLabel.equals(l_strColumnLabel))
            {
                l_returnColumnModel = this.columnHeader[l_intLoop];
            }
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_returnColumnModel;
    }

    /**
     * (get明細行数) <BR>
     * 明細行数を取得する。<BR>
     * <BR>
     * this.明細行.size()を返却する。<BR>
     * @@return int
     * @@roseuid 40F536E40180
     */
    public int getRowCount()
    {
        //this.明細行.size()を返却する。
        return this.rows.size();
    }

    /**
     * (set項目値) <BR>
     * （setValue） <BR>
     * <BR>
     * 指定の行／カラムに値をセットする。 <BR>
     * <BR>
     * １）　@行取得 <BR>
     * 　@明細行から引数の行番号の要素を取得し、Object[]型に変換する。 <BR>
     * <BR>
     * ２）　@値セット <BR>
     * 　@１）で取得したObject配列の、引数のカラムが示すカラム番号の要素に<BR>
     * 引数の値をセットする。 <BR>
     * <BR>
     * // Object l_rowValues[] = (Object[])this.明細行.get(行番号); <BR>
     * // l_rowValues[カラム.getカラム番号()] = 値; <BR>
     * @@param l_intLineNumber - 行番号 <BR>
     * 行番号を指定する。<BR>
     * 
     * @@param l_csvColumnModel - カラム <BR>
     * カラム（列）を指定する。<BR>
     * 
     * @@param l_objValue - 値 <BR>
     * セットする値を指定する。<BR>
     * @@roseuid 40F252B6036C
     */
    public void setValue(
        int l_intLineNumber,
        WEB3GentradeCsvColumnModel l_csvColumnModel,
        Object l_objValue)
    {
        final String STR_METHOD_NAME = "setValue(int,WEB3GentradeCsvColumnModel," +
            "Object)";
        log.entering(STR_METHOD_NAME);
        
        //１）　@行取得
        Object l_rowValues[] = (Object[])this.rows.get(l_intLineNumber);
        
        //１）で取得したObject配列の、引数のカラムが示すカラム番号の要素に
        //引数の値をセットする。
        l_rowValues[l_csvColumnModel.getColumnNumber()] = l_objValue;
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (setカラムヘッダ) <BR>
     * （setColumnHeader）<BR>
     * <BR>
     * this.カラムヘッダに引数の値をセットする。<BR>
     * @@param l_columnHeader - カラムヘッダ。<BR>
     * @@roseuid 40E21B9401FC
     */
    public void setColumnHeader(WEB3GentradeCsvColumnModel[] l_columnHeader)
    {
        //this.カラムヘッダに引数の値をセットする。
        this.columnHeader = l_columnHeader;
    }

    /**
     * (setキーヘッダ) <BR>
     * （setKeyHeader）<BR>
     * <BR>
     * this.キーヘッダに引数の値をセットする。<BR>
     * @@param l_strKeyHeader - キーヘッダ <BR>
     * CSVファ@イルの１行目に出力されるキーヘッダ。<BR>
     * キーヘッダがない場合は、nullを指定する。<BR>
     * @@roseuid 40E21C4C02C7
     */
    public void setKeyHeader(String[] l_strKeyHeader)
    {
        final String STR_METHOD_NAME = "setKeyHeader(String[])";
        log.entering(STR_METHOD_NAME);   
        
        if (l_strKeyHeader == null)
        {
            log.error("キーヘッダが不正");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "."
                    + STR_METHOD_NAME,
                 "キーヘッダ = null");     
        }   
        
        //this.キーヘッダに引数の値をセットする。
        this.keyHeader = l_strKeyHeader;
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (add明細行) <BR>
     * （addRow）<BR>
     * 明細行を追加する。<BR>
     * <BR>
     * （要素数 == カラムヘッダ数）のObject型配列を<BR>
     * 生成し（new Object[this.カラムヘッダ.length]）、<BR>
     * 明細リストに追加（add()）する。<BR>
     * <BR>
     * 追加した明細行の要素番号（this.明細行.size() - 1）を返却する。<BR>
     * @@return int
     * @@roseuid 40E23A7A02B8
     */
    public int addRow()
    {
        final String STR_METHOD_NAME = "addRow()";
        log.entering(STR_METHOD_NAME);
        
        if(this.columnHeader != null)
        {
            Object l_obj = new Object[this.columnHeader.length];
            this.rows.add(l_obj);
        }
        else
        {
            log.info("カラムヘッダ(columnHeader) = null、明細行を追加ない。");
        }

        log.exiting(STR_METHOD_NAME);
        return (this.rows.size() -1);
    }

    /**
     * (add明細行) <BR>
     * （addRow）<BR>
     * <BR>
     * 引数の明細行文字列をthis.明細行に追加する。<BR>
     * <BR>
     * １）明細行が空文字列の場合（明細行文字列　@== null Or 明細行文字列 == ""）、<BR>
     * 　@　@-1を返却する。<BR>
     * <BR>
     * 2）明細行解析<BR>
     * 　@明細行文字列.split(CSVデータモデル.区切り文字)にて、<BR>
     * 区切り文字ごとのtoken[]に分割する。<BR>
     * <BR>
     * 3）　@明細行生成<BR>
     * 　@this.add明細行()にて空の明細行を追加する。<BR>
     * <BR>
     * 4）　@項目値セット<BR>
     * 　@１）で取得したtoken[]の各要素を、それぞれthis.カラムヘッダ<BR>
     * [（対応するindex）].get項目型()が示すデータ型に変換する。<BR>
     * <BR>
     * 　@token[]の要素数 != this.カラムヘッダ[]の要素数が同じでない場合は、<BR>
     * 例外をスローする。<BR>
     * class    : WEB3SystemLayerException<BR>
     * tag      : SYSTEM_ERROR_80022<BR>
     * 　@token[index]を、this.カラムヘッダ[index].get項目型()に<BR>
     * 変換できない場合は、例外をスローする。<BR>
     * class    : WEB3SystemLayerException<BR>
     * tag      : SYSTEM_ERROR_80023<BR>
     * <BR>
     * 　@型変換した値を、this.set項目値()にて明細行の指定項目にセットする。<BR>
     * <BR>
     * 　@[set項目値()に指定する引数]<BR>
     * 　@行番号：　@(*1)<BR>
     * 　@カラム：　@this.カラムヘッダ[（対応するindex）]<BR>
     * <BR>
     * 　@行番号(*1)を返却する。<BR>
     * <BR>
     * 　@(*1) 行番号<BR>
     * 　@２）でコールしたthis.add明細行() の戻り値<BR>
     * @@param l_rowString - 明細行文字列<BR>
     * <BR>
     * ※ ","を区切り文字にした明細行文字列。<BR>
     * 
     * @@return int
     * @@throws WEB3SystemLayerException
     * @@roseuid 40F4EFBD0354
     */
    public int addRow(String l_rowString) throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "addRow(String)";
        log.entering(STR_METHOD_NAME);       
        
        //1)明細行が空文字列の場合（明細行文字列　@== null Or 明細行文字列 == ""）、
        //-1を返却する。
        if ((l_rowString == null) || (l_rowString.equals("")))
        {
                return -1;
        }
        
        //2）　@明細行解析
        //明細行文字列.split(CSVデータモデル.区切り文字)にて、
        //区切り文字ごとのtoken[]に分割する。
        String[] token = l_rowString.split(regex);
        if(l_rowString.lastIndexOf(regex) == (l_rowString.length() - 1))
        {
            l_rowString = l_rowString + 'a'; 
            token = l_rowString.split(regex);
            token[token.length - 1] = "";
        }
        
        if(token.length  != this.columnHeader.length)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01993,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "明細行文字列の要素数 = " + token.length 
                    + ",カラムヘッダ[]の要素数 = " + this.columnHeader.length);
        }

        //3）　@明細行生成
        int l_intLineNumber = this.addRow();
        
        //4）　@項目値セット
        //１）で取得したtoken[]の各要素を、それぞれthis.カラムヘッダ
        //[（対応するindex）].get項目型()が示すデータ型に変換する。
        for (int l_intLoop = 0; l_intLoop < token.length; l_intLoop++)
        {
            if( token[l_intLoop] == null || "".equals(token[l_intLoop]) )
            {
                continue;
            }
            
            String l_strColumnLabel = this.columnHeader[l_intLoop].getColumnLabel();
            int l_intColumnNumber = this.columnHeader[l_intLoop].getColumnNumber();
            int l_intColumnModel = this.columnHeader[l_intLoop].getColumnType();
            DateFormat l_dateFormat = this.columnHeader[l_intLoop].getDateFormat();
            
            //CSVカラムモデル
            WEB3GentradeCsvColumnModel l_csvColumnModel = 
                new WEB3GentradeCsvColumnModel(
                    l_strColumnLabel,
                    l_intColumnNumber,
                    l_intColumnModel,
                    l_dateFormat
                    );

            try
            {
                //項目型_文字列
                if (l_intColumnModel == WEB3GentradeCsvColumnModel.STRINGTYPE)
                {
                    //型変換した値を、this.set項目値()にて明細行の指定項目にセットする。
                    String l_strValue = token[l_intLoop].toString();
                    this.setValue(l_intLineNumber,l_csvColumnModel,l_strValue);
                }
                //項目型_日付
                else if (l_intColumnModel == WEB3GentradeCsvColumnModel.DATETYPE)
                {
                    //型変換した値を、this.set項目値()にて明細行の指定項目にセットする。
                    Date l_datValue = l_dateFormat.parse(token[l_intLoop]);
                    this.setValue(l_intLineNumber,l_csvColumnModel,l_datValue);
                }
                //項目型_数値（double）
                else if (l_intColumnModel == WEB3GentradeCsvColumnModel.DOUBLETYPE)
                {
                    //型変換した値を、this.set項目値()にて明細行の指定項目にセットする。
                    Double l_dblValue = Double.valueOf(token[l_intLoop]);
                    this.setValue(l_intLineNumber,l_csvColumnModel,l_dblValue);
                }
                //項目型_数値（int）
                else if (l_intColumnModel == WEB3GentradeCsvColumnModel.INTEGERTYPE)
                {
                    //型変換した値を、this.set項目値()にて明細行の指定項目にセットする。
                    Integer l_intValue = Integer.valueOf(token[l_intLoop]);
                    this.setValue(l_intLineNumber,l_csvColumnModel,l_intValue);
                }
                //項目型_数値（long）
                else if (l_intColumnModel == WEB3GentradeCsvColumnModel.LONGTYPE)
                {
                    //型変換した値を、this.set項目値()にて明細行の指定項目にセットする。
                    Long l_lngValue = Long.valueOf(token[l_intLoop]);
                    this.setValue(l_intLineNumber,l_csvColumnModel,l_lngValue);
                }
                //項目型_日付時間
                else if (l_intColumnModel == WEB3GentradeCsvColumnModel.TIMESTAMPTYPE)
                {
                    //型変換した値を、this.set項目値()にて明細行の指定項目にセットする。
                    DateFormat l_df = l_csvColumnModel.getDateFormat();
                    Timestamp l_tsValue = new Timestamp(l_df.parse(token[l_intLoop]).getTime());
                    this.setValue(l_intLineNumber,l_csvColumnModel,l_tsValue);
                }
                //token[]の要素数 != this.カラムヘッダ[]の要素数が同じでない場合は、
                //例外をスローする。
                else
                {
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80022,
                        this.getClass().getName() + "." + STR_METHOD_NAME
                        );
                }
            }
            //token[index]を、this.カラムヘッダ[index].get項目型()に
            //変換できない場合は、例外をスローする。
            catch(NumberFormatException nfe)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80023,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    nfe.getMessage(),
                    nfe);
            }
            catch (ParseException pex)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80023,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    pex.getMessage(),
                    pex);
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_intLineNumber;
    }

    /**
     * (add明細行)<BR>
     * （addRow）<BR>
     * <BR>
     * 引数の明細行文字列をthis.明細行に追加する。<BR>
     * <BR>
     * CSVデータ中に日付が存在する場合、パラメータにより、<BR>
     * 日付解析を厳密に行うかどうかを判断する。<BR>
     * <BR>
     * １）　@引数.日付解析フラグ == "0" or null の場合、<BR>
     * 　@１-１）　@this.add明細行(String)をコールする。<BR>
     * <BR>
     * 　@　@　@[指定する引数]<BR>
     * 　@　@　@明細行文字列：引数.明細行文字列<BR>
     * <BR>
     * 　@１-２）　@１-１）の戻り値を返却する。<BR>
     * <BR>
     * ２）　@引数.日付解析フラグ == "1" の場合、<BR>
     * <BR>
     * 　@２-１）　@明細行が空文字列の場合（明細行文字列　@== null Or 明細行文字列 == ""）、<BR>
     * 　@　@　@　@　@-1を返却する。<BR>
     * <BR>
     * 　@２-２）　@明細行解析<BR>
     * 　@　@　@　@　@明細行文字列.split(CSVデータモデル.区切り文字)にて、<BR>
     * 　@　@　@　@　@区切り文字ごとのtoken[]に分割する。<BR>
     * <BR>
     * 　@２-３）　@明細行生成<BR>
     * 　@　@　@　@　@　@this.add明細行()にて空の明細行を追加する。<BR>
     * <BR>
     * 　@２-４）　@項目値セット<BR>
     * 　@　@２-４-１）　@２-１）で取得したtoken[]の各要素を、<BR>
     * 　@　@　@それぞれthis.カラムヘッダ[（対応するindex）].get項目型()が示すデータ型に変換する。<BR>
     * <BR>
     * 　@　@　@　@　@　@・token[]の要素数 != this.カラムヘッダ[]の要素数が同じでない場合は、<BR>
     * 　@　@　@　@　@　@例外をスローする。<BR>
     * 　@　@　@　@　@　@class    : WEB3SystemLayerException<BR>
     * 　@　@　@　@　@　@tag      : SYSTEM_ERROR_80022<BR>
     * 　@　@　@　@　@　@・token[index]を、this.カラムヘッダ[index].get項目型()に変換できない場合は、<BR>
     * 　@　@　@　@　@　@例外をスローする。<BR>
     * 　@　@　@　@　@　@※NumberFormatException発生時「数値が不正です」例外をスローする。 <BR>
     * 　@　@　@　@　@　@class    : WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag      : BUSINESS_ERROR_03002<BR>
     * 　@　@　@　@　@　@※ParseException発生時「日付が不正です」例外をスローする。 <BR>
     * 　@　@　@　@　@　@class    : WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag      : BUSINESS_ERROR_02994<BR>
     * <BR>
     * 　@　@　@　@　@　@※日付型、日付時間型を変換する場合は以下を実施後、<BR>
     * 　@　@　@　@　@　@日付/時刻解析を厳密に行う［DateFormat#setLenient(false)実装]。 <BR>
     * 　@　@　@　@　@　@　@・token[index]が数値以外の場合、<BR>
     * 　@　@　@　@　@　@　@「日付が不正です」 （BUSINESS_ERROR_02994）例外をスローする。 <BR>
     * 　@　@　@　@　@　@class    : WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag      : BUSINESS_ERROR_02994<BR>
     * 　@　@　@　@　@　@　@・token[index]の桁数が日付フォーマットパターンの長さ<BR>
     * 　@　@　@　@　@　@　@［((SimpleDateFormat)DateFormat)#toPattern()]と等しくない場合、<BR>
     * 　@　@　@　@　@　@　@「日付が不正です」 （BUSINESS_ERROR_02994）例外をスローする。 <BR>
     * 　@　@　@　@　@　@class    : WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag      : BUSINESS_ERROR_02994<BR>
     * <BR>
     * 　@　@２-４-２） 型変換した値を、this.set項目値()にて明細行の指定項目にセットする。<BR>
     * <BR>
     * 　@　@　@　@　@　@[set項目値()に指定する引数]<BR>
     * 　@　@　@　@　@　@行番号：　@(*1)<BR>
     * 　@　@　@　@　@　@カラム：　@this.カラムヘッダ[（対応するindex）]<BR>
     * 　@<BR>
     * 　@２-５） 行番号(*1)を返却する。<BR>
     * <BR>
     * 　@　@　@　@　@　@(*1) 行番号<BR>
     * 　@　@　@　@　@　@２-３）でコールしたthis.add明細行() の戻り値<BR>
     * <BR>
     * <BR>
     * @@param l_strRows - (明細行文字列)<BR>
     * 明細行文字列<BR>
     * <BR>
     * ※ ","を区切り文字にした明細行文字列。<BR>
     * @@param l_strDateAnalysisFlag - (日付解析フラグ)<BR>
     * 日付解析フラグ<BR>
     * <BR>
     * 日付データフォーマットが存在する場合、<BR>
     * 日付解析を厳密に行うかどうかを設定する。<BR>
     * <BR>
     * 0：厳密ではない解析<BR>
     * 1：厳密な解析<BR>
     * @@return int
     * @@throws WEB3BaseException
     */
    public int addRow(String l_strRows, String l_strDateAnalysisFlag) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "addRow(String, String)";
        log.entering(STR_METHOD_NAME);

        //１） 引数.日付解析フラグ == "0" or null の場合、
        //１-１） this.add明細行(String)をコールする。
        if (WEB3GentradeDateAnalysisFlagDivDef.NOT_STRICTLY_ANALYSIS.equals(l_strDateAnalysisFlag)
            || l_strDateAnalysisFlag == null)
        {
            //[指定する引数]
            //明細行文字列：引数.明細行文字列
            //１-２） １-１）の戻り値を返却する。
            log.exiting(STR_METHOD_NAME);
            return this.addRow(l_strRows);
        }

        //２） 引数.日付解析フラグ == "1" の場合、
        int l_intLineNumber = 0;
        if (WEB3GentradeDateAnalysisFlagDivDef.STRICTLY_ANALYSIS.equals(l_strDateAnalysisFlag))
        {
            //２-１）　@明細行が空文字列の場合（明細行文字列　@== null Or 明細行文字列 == ""）、-1を返却する。
            if (WEB3StringTypeUtility.isEmpty(l_strRows))
            {
                log.exiting(STR_METHOD_NAME);
                return -1;
            }

            //２-２）　@明細行解析
            //明細行文字列.split(CSVデータモデル.区切り文字)にて、区切り文字ごとのtoken[]に分割する。
            String[] token = l_strRows.split(regex);
            if (l_strRows.lastIndexOf(regex) == (l_strRows.length() - 1))
            {
                l_strRows = l_strRows + 'a';
                token = l_strRows.split(regex);
                token[token.length - 1] = "";
            }

            if (token.length != this.columnHeader.length)
            {
                log.debug("CSVファ@イルの要素数が不正。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01993,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "明細行文字列の要素数 = " + token.length
                    + ",カラムヘッダ[]の要素数 = " + this.columnHeader.length);
            }

            //２-３）　@明細行生成
            //this.add明細行()にて空の明細行を追加する。
            l_intLineNumber = this.addRow();

            //２-４）　@項目値セット
            //２-４-１） ２-１）で取得したtoken[]の各要素を、それぞれthis.カラムヘッダ[（対応するindex）].get項目型()が示すデータ型に変換する。
            //・token[]の要素数 != this.カラムヘッダ[]の要素数が同じでない場合は、例外をスローする。
            //・token[index]を、this.カラムヘッダ[index].get項目型()に変換できない場合は、例外をスローする。
            for (int l_intLoop = 0; l_intLoop < token.length; l_intLoop++)
            {
                if (token[l_intLoop] == null || "".equals(token[l_intLoop]))
                {
                    continue;
                }

                String l_strColumnLabel = this.columnHeader[l_intLoop].getColumnLabel();
                int l_intColumnNumber = this.columnHeader[l_intLoop].getColumnNumber();
                int l_intColumnModel = this.columnHeader[l_intLoop].getColumnType();
                DateFormat l_dateFormat = this.columnHeader[l_intLoop].getDateFormat();

                //CSVカラムモデル
                WEB3GentradeCsvColumnModel l_csvColumnModel =
                    new WEB3GentradeCsvColumnModel(
                        l_strColumnLabel,
                        l_intColumnNumber,
                        l_intColumnModel,
                        l_dateFormat
                        );

                try
                {
                    //項目型_文字列
                    if (l_intColumnModel == WEB3GentradeCsvColumnModel.STRINGTYPE)
                    {
                        //型変換した値を、this.set項目値()にて明細行の指定項目にセットする。
                        String l_strValue = token[l_intLoop].toString();
                        this.setValue(l_intLineNumber, l_csvColumnModel, l_strValue);
                    }
                    //項目型_日付
                    else if (l_intColumnModel == WEB3GentradeCsvColumnModel.DATETYPE)
                    {
                        //型変換した値を、this.set項目値()にて明細行の指定項目にセットする。
                        //※日付型を変換する際は、日付/時刻解析を厳密に行う（DateFormat#setLenient(false)実装）
                        if (!WEB3StringTypeUtility.isNumber(token[l_intLoop]))
                        {
                            log.debug("日付が不正です。");
                            log.exiting(STR_METHOD_NAME);
                            throw new WEB3BusinessLayerException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_02994,
                                this.getClass().getName() + "." + STR_METHOD_NAME
                                );
                        }
                        if (token[l_intLoop].length() != ((SimpleDateFormat)l_dateFormat).toPattern().length())
                        {
                            log.debug("日付が不正です。");
                            log.exiting(STR_METHOD_NAME);
                            throw new WEB3BusinessLayerException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_02994,
                                this.getClass().getName() + "." + STR_METHOD_NAME
                                );
                        }
                        l_dateFormat.setLenient(false);
                        Date l_datValue = l_dateFormat.parse(token[l_intLoop]);
                        this.setValue(l_intLineNumber, l_csvColumnModel, l_datValue);
                    }
                    //項目型_数値（double）
                    else if (l_intColumnModel == WEB3GentradeCsvColumnModel.DOUBLETYPE)
                    {
                        //型変換した値を、this.set項目値()にて明細行の指定項目にセットする。
                        Double l_dblValue = Double.valueOf(token[l_intLoop]);
                        this.setValue(l_intLineNumber, l_csvColumnModel, l_dblValue);
                    }
                    //項目型_数値（int）
                    else if (l_intColumnModel == WEB3GentradeCsvColumnModel.INTEGERTYPE)
                    {
                        //型変換した値を、this.set項目値()にて明細行の指定項目にセットする。
                        Integer l_intValue = Integer.valueOf(token[l_intLoop]);
                        this.setValue(l_intLineNumber, l_csvColumnModel, l_intValue);
                    }
                    //項目型_数値（long）
                    else if (l_intColumnModel == WEB3GentradeCsvColumnModel.LONGTYPE)
                    {
                        //型変換した値を、this.set項目値()にて明細行の指定項目にセットする。
                        Long l_lngValue = Long.valueOf(token[l_intLoop]);
                        this.setValue(l_intLineNumber, l_csvColumnModel, l_lngValue);
                    }
                    //項目型_日付時間
                    else if (l_intColumnModel == WEB3GentradeCsvColumnModel.TIMESTAMPTYPE)
                    {
                        //型変換した値を、this.set項目値()にて明細行の指定項目にセットする。
                        DateFormat l_df = l_csvColumnModel.getDateFormat();
                        if (!WEB3StringTypeUtility.isNumber(token[l_intLoop]))
                        {
                            log.debug("日付が不正です。");
                            log.exiting(STR_METHOD_NAME);
                            throw new WEB3BusinessLayerException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_02994,
                                this.getClass().getName() + "." + STR_METHOD_NAME
                                );
                        }
                        if (token[l_intLoop].length() != ((SimpleDateFormat)l_dateFormat).toPattern().length())
                        {
                            log.debug("日付が不正です。");
                            log.exiting(STR_METHOD_NAME);
                            throw new WEB3BusinessLayerException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_02994,
                                this.getClass().getName() + "." + STR_METHOD_NAME
                                );
                        }
                        l_df.setLenient(false);
                        Timestamp l_tsValue = new Timestamp(l_df.parse(token[l_intLoop]).getTime());
                        this.setValue(l_intLineNumber, l_csvColumnModel, l_tsValue);
                    }
                    //token[]の要素数 != this.カラムヘッダ[]の要素数が同じでない場合は、
                    //例外をスローする。
                    else
                    {
                        log.debug("明細行文字列の要素数とカラムヘッダ[]の要素数が同じでない。");
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80022,
                            this.getClass().getName() + "." + STR_METHOD_NAME
                            );
                    }
                }
                //token[index]を、this.カラムヘッダ[index].get項目型()に
                //変換できない場合は、例外をスローする。
                catch (NumberFormatException l_ex)
                {
                    log.error("数値が不正です。", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_03002,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                catch (ParseException l_ex)
                {
                    log.error("日付が不正です。", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02994,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
            }
        }

        //２-５） 行番号(*1)を返却する。
        // (*1) 行番号
        //２-３）でコールしたthis.add明細行() の戻り値
        log.exiting(STR_METHOD_NAME);
        return l_intLineNumber;
    }

    /**
     * (delete明細行) <BR>
     * （deleteRow） <BR>
     * 指定行番号の明細行を削除する。 <BR>
     *  <BR>
     * // this.明細行.remove(行番号) <BR>
     * @@param l_intLineNumber - 行番号 <BR>
     * 行番号を指定する。<BR>
     * @@roseuid 40F5DAF900C5
     */
    public void deleteRow(int l_intLineNumber)
    {
        //指定行番号の明細行を削除する。
        this.rows.remove(l_intLineNumber);
    }
}
@
