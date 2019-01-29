head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.54.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformDownLoadCSV.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 連絡情報ダウンロードCSVクラス(WEB3AdminInformDownLoadCSV.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2005/01/24 凌建平(中訊) 作成
Revesion History    : 2005/06/12 凌建平(中訊) モデル・No.031を対応
Revesion History    : 2007/03/09 唐性峰(中訊) モデル・No.032を対応
Revesion History    : 2008/02/21 馮海濤(中訊) モデル・No.126を対応
Revesion History    : 2008/02/29 柴双紅(中訊) 実装No.007
*/

package webbroker3.inform;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BranchCodeDef;
import webbroker3.common.define.WEB3CatDelimitterDef;
import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.gentrade.WEB3GentradeCsvDataModel;
import webbroker3.inform.data.InformDlFormMasterParams;
import webbroker3.inform.data.InformDlFormMasterRow;
import webbroker3.inform.data.VariousInformParams;
import webbroker3.inform.define.WEB3InformCatDelimitterDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (連絡情報ダウンロードCSV)<BR>
 * 連絡情報ダウンロードCSVクラス<BR>
 * @@author 凌建平
 * @@version 1.0
 */
public class WEB3AdminInformDownLoadCSV extends WEB3GentradeCsvDataModel 
{
    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminInformDownLoadCSV.class);
    
    /**
     * (MAX処理件数)<BR>
     * 1回のダウンロードでのMAX処理件数
     */
    public static  int maxDealNumber = 500;
    
    /**
     * (isカラムヘッダ行出力)<BR>
     * カラムヘッダ行を出力するかどうかのフラグ<BR>
     *<BR>
     * trueの場合、カラムヘッダを出力する。
     */
    public boolean isColumnHeadOut;

    /**
     * (isカラムヘッダ行出力)<BR>
     * CSVファ@イルにカラムヘッダ（タイトル文字列）行を出力する場合はtrue、<BR>
     * 要な場合はfalseを返却する。<BR>
     *（overrideメソッド）<BR>
     *<BR>
     * this.isカラムヘッダ行出力 == true の場合はtrue、<BR>
     * this.isカラムヘッダ行出力 == false の場合はfalseを返却する。<BR>
     * @@return boolean
     */
    public boolean isColumnHeadOutput()
    {
        if (this.isColumnHeadOut)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * (createキーヘッダ)<BR>
     * キーヘッダをセットする。<BR>
     * <BR>
     * 以下の通り文字列の配列を生成し、setキーヘッダ()にてインスタンスにセットする。<BR>
     * <BR>
     * [キーヘッダ配列]<BR>
     * <BR>
     * － index = 0<BR>
     *   現在日時を"yyyy/MM/dd HH:mm:ss"の形式にformatした文字列。<BR>
     * <BR>
     * (*) 現在日時<BR>
     * TradingSystem.getSystemTimestamp()<BR>
     */
    protected void createKeyHead()
    {
        String[] l_strKeyHeader = new String[1];
        l_strKeyHeader[0] = WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(),"yyyy/MM/dd HH:mm:ss");
        setKeyHeader(l_strKeyHeader);
    }
    /**
     * @@roseuid 41EE642D035B
     */
    public WEB3AdminInformDownLoadCSV() 
    {
     
    }
    /**
     * (連絡情報ダウンロードCSV)<BR>
     * コンストラクタ<BR>
     * <BR>
     * インスタンスを生成し、ヘッダ情報をセットする。<BR>
     * <BR>
     * １）super()をコールし、インスタンスを生成する。<BR>
     * <BR>
     * ２）引数.isカラムヘッダ行出力==trueの場合、<BR>
     *     createキーヘッダ()をコールし、キーヘッダ情報を生成する。<BR>
     * <BR>
     * ３）createカラムヘッダ()をコールし、カラムヘッダ情報を生成する。<BR>
     * <BR>
     *    [createカラムヘッダ()にセットする引数]<BR>
     *    証券会社コード： 引数.証券会社コード<BR>
     *    部店コード： 引数.部店コード<BR>
     *    連絡種別： 引数.連絡種別<BR>
     * ４）引数.isカラムヘッダ行出力をthis.isカラムヘッダ行出力にセットする。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード
     * @@param l_strInformDiv - (連絡種別)<BR>
     * 連絡種別
     * @@param l_isColumnHeadOutput - (isカラムヘッダ行出力)<BR>
     * カラムヘッダ行出力要否のフラグ
     * @@roseuid 41BEC68A01E1
     */
    public WEB3AdminInformDownLoadCSV(
        String l_strInstitutionCode, 
        String l_strBranchCode, 
        String l_strInformDiv,
        boolean l_isColumnHeadOutput) 
        throws WEB3BaseException
    {
        //１）super()をコールし、インスタンスを生成する。
        super();
        
        //２）引数.isカラムヘッダ行出力==trueの場合、<BR>
        //    createキーヘッダ()をコールし、キーヘッダ情報を生成する。<BR>
		if (l_isColumnHeadOutput)
	        this.createKeyHead();
        
        //3）createカラムヘッダ()をコールし、カラムヘッダ情報を生成する
        this.createColumnHead(
            l_strInstitutionCode,
            l_strBranchCode,
            l_strInformDiv);
            
        //４）引数.isカラムヘッダ行出力をthis.isカラムヘッダ行出力にセットする。
        this.isColumnHeadOut = l_isColumnHeadOutput;
    }
    
    /**
     * (createカラムヘッダ)<BR>
     * カラムヘッダをセットする。<BR>
     * <BR>
     * 以下のとおりにCSVカラムモデルの配列を生成し、<BR>
     * setカラムヘッダ()にてインスタンスにセットする。<BR>
     * <BR>
     * １）ダウンロードフォームマスタ取得<BR>
     *   以下の条件で、連絡情報ＤＬフォームマスタテーブルを検索する。<BR>
     * <BR>
     *   [条件]<BR>
     *   証券会社コード = 引数.証券会社コード<BR>
     *   部店コード = 引数.部店コード<BR>
     *   連絡種別 = 引数.連絡種別<BR>
     * <BR>
     *   [取得順]<BR>
     *   カラム番号 昇順（：asc）<BR>
     * <BR>
     *   該当行が存在しない場合は部店コードの条件を以下のようにする。<BR>
     *   <BR>
     *   [条件]<BR>
     *   部店コード = "000"<BR>
     * <BR>
     * ２）項目ヘッダ生成 <BR>
     * １）で取得した各要素毎に、以下の通り連絡情報カラムモデルの配列を生成し、<BR>
     *     setカラムヘッダ()にてインスタンスにセットする
     * <BR>
     *   [連絡情報カラムモデル コンストラクタの引数]<BR>
     *   項目ラベル： 連絡情報ＤＬフォームマスタ.項目ラベル<BR>
     *   カラム番号： index<BR>
     *   項目型： 連絡情報ＤＬフォームマスタ.項目型<BR>
     *   日付フォーマット： 連絡情報ＤＬフォームマスタ.日付フォーマット<BR>
     *   入力項目物理名： 連絡情報ＤＬフォームマスタ.入力項目物理名<BR>
     *   連結項目デリミッタ： 連絡情報ＤＬフォームマスタ.連結項目デリミッタ<BR>
     *   セクション番号： 連絡情報ＤＬフォームマスタ.セクション番号<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード
     * @@param l_strInformDiv - (連絡種別)<BR>
     * 連絡種別
     * @@roseuid 41BEC7D80230
     */
    protected void createColumnHead(
        String l_strInstitutionCode, 
        String l_strBranchCode, 
        String l_strInformDiv) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createColumnHead()";
        log.entering(STR_METHOD_NAME);
     
        try
        {   
            //１）ダウンロードフォームマスタ取得<BR>
            // 以下の条件で、連絡情報ＤＬフォームマスタテーブルを検索する。<BR>
            // [条件]<BR>
            //    証券会社コード = 引数.証券会社コード<BR>
            //    部店コード = 引数.部店コード<BR>
            //    連絡種別 = 引数.連絡種別<BR>
            // [取得順]<BR>
            //    カラム番号 昇順（：asc）<BR>
            StringBuffer l_strWhere = new StringBuffer();
            l_strWhere.append(" institution_code = ? ");
            l_strWhere.append(" and branch_code = ? ");
            l_strWhere.append(" and inform_div = ? ");
    
            Object[] l_objWhere = {
                l_strInstitutionCode,
                l_strBranchCode,
                l_strInformDiv};
    
            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
            List l_lisRecords = l_QueryProcessor.doFindAllQuery(
                InformDlFormMasterRow.TYPE,
                l_strWhere.toString(),
                "column_number asc",
                null,
                l_objWhere);

            int l_intListRecordCnt = 0;
            if (l_lisRecords != null)
            {
                l_intListRecordCnt = l_lisRecords.size();
            }

            if (l_intListRecordCnt == 0)
            {
                log.debug("該当行が存在しない場合は部店コードの条件を以下のようにする。");
                // 該当行が存在しない場合は部店コードの条件を以下のようにする。<BR>
                // [条件]<BR>
                //    部店コード = "000"<BR>
                Object[] l_objWhereSch = {
                    l_strInstitutionCode,
                    WEB3BranchCodeDef.DEFAULT,
                    l_strInformDiv};

                l_lisRecords = l_QueryProcessor.doFindAllQuery(
                    InformDlFormMasterRow.TYPE,
                    l_strWhere.toString(),
                    "column_number asc",
                    null,
                    l_objWhereSch);

                if (l_lisRecords != null)
                {
                    l_intListRecordCnt = l_lisRecords.size();
                }
            }
            log.debug("検索したレコード数 = " + l_intListRecordCnt);

            List l_lisColumnHeader = new ArrayList();
            for (int i = 0; i < l_intListRecordCnt; i++)
            {
                //２）項目ヘッダ生成 <BR>
                InformDlFormMasterRow l_masterRow = (InformDlFormMasterRow)l_lisRecords.get(i);
                InformDlFormMasterParams l_masterParams = new InformDlFormMasterParams(l_masterRow);
                
                SimpleDateFormat l_dateFormat = null;
                if (l_masterParams.getDateFormat() != null)
                {
                    l_dateFormat = new SimpleDateFormat(l_masterParams.getDateFormat());
                }
                
                WEB3AdminInformColumnModel l_columnModel = new WEB3AdminInformColumnModel(
                    l_masterParams.getColumnLabel(),
                    i,
                    l_masterParams.getColumnType(),
                    l_dateFormat,
                    l_masterParams.getInputItemSymbolName(),
                    l_masterParams.getCatDelimitter(),
                    String.valueOf(l_masterParams.getSectionNumber()));

                l_lisColumnHeader.add(l_columnModel);
            }
            
            WEB3GentradeCsvColumnModel[] l_columnHeader = 
                new WEB3GentradeCsvColumnModel[l_intListRecordCnt];
            l_lisColumnHeader.toArray(l_columnHeader);
            setColumnHeader(l_columnHeader);
        }
        catch (DataQueryException l_dqEx)
        {
            log.error("DBへのアクセスに失敗しました。", l_dqEx);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dqEx.getMessage(),
                l_dqEx);
        }
        catch (DataNetworkException l_dnEx)
        {
            log.error("DBへのアクセスに失敗しました。", l_dnEx);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dnEx.getMessage(),
                l_dnEx);
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set項目値)<BR>
     * データモデルに項目値をセットする。<BR>
     * <BR>
     * this.カラムヘッダ[] の各要素毎に、以下の１）～３）を繰り返す。<BR>
     * <BR>
     * １）項目名取得<BR>
     *   ・this.カラムヘッダ[index] を連絡情報カラムモデル型に変換する（Cast）。<BR>
     *   ・this.カラムヘッダ[index].get入力項目物理名()にて、項目名を取得する。<BR>
     *   ・this.カラムヘッダ[index].get連結項目デリミッタ()にて、連結項目デリミッタを取得する。<BR>
     *   ・this.カラムヘッダ[index].getセクション番号()にて、セクション番号を取得する。<BR>
     * <BR>
     * ２）項目値取得<BR>
     * 　@１）で取得した項目名と一致する各種連絡Paramsの項目から値を取得する。<BR>
     * 　@　@※項目名が顧客コード、且つ、各種連絡Params.顧客コードがnullでない場合、<BR>
     * 　@　@各種連絡Params.顧客コードの頭6桁（substring(0,6)）を取得する。<BR>
     * <BR>
     * ３）値セット<BR>
     * <BR>
     * ３－１）１項目より複数カラムを出力する場合<BR>
     *   （連結項目デリミッタ != null and 連結項目デリミッタ != 0：なし） and (セクション番号 != null）、<BR>
     * 　@２）で取得した項目値をthis.get連結項目デリミッタ値()にて分割した値のセクション番号で指定された要素を<BR>
     * 　@項目値とする。<BR>
     * <BR>
     * 　@[get連結項目デリミッタ値()にセットする引数]<BR>
     * 　@連結項目デリミッタ： 連結項目デリミッタ<BR>
     * <BR>
     * ３－２）項目値セット<BR>
     *   this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     *   [set項目値()にセットする引数]<BR>
     *   行番号： 引数の行番号<BR>
     *   カラム： this.カラムヘッダ[index]
     *   値： ２）または、３－１）で取得した項目値<BR>
     * <BR>
     * @@param l_intPageNumber - (行番号)<BR>
     * 行番号
     * @@param l_variousInformParams - (連絡情報)<BR>
     * 各種連絡行オブジェクト
     * @@roseuid 41BED4E402BC
     */
    public void setItemValue(
        int l_intPageNumber, 
        VariousInformParams l_variousInformParams) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "setItemValue()";
        log.entering(STR_METHOD_NAME);
        
        // this.カラムヘッダ[] の各要素毎に、以下の１）～３）を繰り返す
        int l_intColumnHeaderLth = this.columnHeader.length;
        log.debug("カラムヘッダ長度 = " + l_intColumnHeaderLth);
        for (int i = 0; i < l_intColumnHeaderLth; i++)
        {
            log.debug("index:" + i);
            //１）項目名取得
            // this.カラムヘッダ[index] を連絡情報カラムモデル型に変換する（Cast）。
            WEB3AdminInformColumnModel l_columnModel = (WEB3AdminInformColumnModel)this.columnHeader[i];

            // this.カラムヘッダ[index].get入力項目物理名()にて、項目名を取得する。
            String l_strSymbolName = l_columnModel.getInputItemSymbolName();
            log.debug("入力項目物理名:" + l_strSymbolName);

            // this.カラムヘッダ[index].get連結項目デリミッタ()にて、連結項目デリミッタを取得する。
            String l_strCatDelimitter = l_columnModel.getCatDelimitter();
            log.debug("連結項目デリミッタ:" + l_strCatDelimitter);
            
            // this.カラムヘッダ[index].getセクション番号()にて、セクション番号を取得する。
            String l_strSectionNumber = l_columnModel.getSectionNumber();
            log.debug("セクション番号:" + l_strSectionNumber);

            //２）項目値取得<BR>
            //  １）で取得した項目名と一致する各種連絡Paramsの項目から値を取得する。<BR>
            //※項目名が顧客コード、且つ、各種連絡Params.顧客コードがnullでない場合、
            //各種連絡Params.顧客コードの頭6桁（substring(0,6)）を取得する。
            // 項目値
            Object l_objSymbolValue = null;
            if ("account_code".equals(l_strSymbolName))
            {
                if (l_variousInformParams.getAccountCode() != null)
                {
                    l_objSymbolValue = l_variousInformParams.getAccountCode().substring(0, 6);
                }
                else
                {
                    l_objSymbolValue = null;
                }
            }
            else
            {
                l_objSymbolValue = l_variousInformParams.getColumn(l_strSymbolName);
            }

            String l_strSymbolValue = "";
            if (l_objSymbolValue != null && l_objSymbolValue instanceof String)
            {               
                l_strSymbolValue = l_objSymbolValue.toString();
                log.debug("入力項目値取得:" + l_strSymbolValue);
            }          

            //３）値セット<BR>
            // ３－１）１項目より複数カラムを出力する場合<BR>
            //   （連結項目デリミッタ != null 
            //     and 連結項目デリミッタ != 0：なし） 
            //     and (セクション番号 != null）<BR>
            if (!("".equals(l_strSymbolValue))
                && l_strCatDelimitter != null
                && !WEB3CatDelimitterDef.WITHOUT.equals(l_strCatDelimitter)
                && l_strSectionNumber != null)
            {
                //  ２）で取得した項目値をthis.get連結項目デリミッタ値()にて分割した値のセクション番号で指定された要素を
                //　@項目値とする。
                //　@[get連結項目デリミッタ値()にセットする引数]
                //  連結項目デリミッタ： 連結項目デリミッタ
                String l_strDivisionChar = this.getCatDelimitter(l_strCatDelimitter);
                String[] l_strArraySymbolValues = l_strSymbolValue.split(l_strDivisionChar);

                // 0から指定する
                int l_intSectionNumber = Integer.parseInt(l_strSectionNumber);
     
                if (l_strArraySymbolValues == null 
                    || l_strArraySymbolValues.length == 0 
                    || l_strArraySymbolValues.length <= l_intSectionNumber)
                {
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80002, 
                        this.getClass().getName() + STR_METHOD_NAME);
                }
                
                // セクション番号で指定された要素を項目値とする。
                l_strSymbolValue = l_strArraySymbolValues[l_intSectionNumber];
            }
            
            // ３－２）項目値セット: this.set項目値()にて項目値をセットする。
            // [set項目値()にセットする引数]
            //   行番号： 引数の行番号
            //   カラム： this.カラムヘッダ[index]
            //   値： ２）または、３－１）で取得した項目値
            log.debug("項目値セットの開始 LOOP " + i);
            log.debug("行番号 ：" + l_intPageNumber);
            log.debug("カラム ：" + this.columnHeader[i]);
            if (!("".equals(l_strSymbolValue)))
            {
                log.debug("字符串の値：" + l_strSymbolValue);
                this.setValue(
                    l_intPageNumber,
                    this.columnHeader[i],
                    l_strSymbolValue);
            }
            else
            {
                if (l_objSymbolValue != null && l_objSymbolValue instanceof Date)
                {
                    log.debug("日付の値：" + l_objSymbolValue);
                    DateFormat l_dateFormat = this.columnHeader[i].getDateFormat();
                    String l_strAfterFormat = l_dateFormat.format(l_objSymbolValue);
                    if (l_strAfterFormat == null)
                    {
                        l_strAfterFormat = "";
                    }
                    
                    this.setValue(
                        l_intPageNumber,
                        this.columnHeader[i],
                        l_strAfterFormat);                    
                }
                else
                {
                    log.debug("オブジェクトの値：" + l_objSymbolValue);
                    if (l_objSymbolValue == null)
                    {
                        this.setValue(
                        	l_intPageNumber,
                            this.columnHeader[i],
                            "");           
                    }
                    else
                    {
                        this.setValue(
                            l_intPageNumber,
                            this.columnHeader[i],
                            String.valueOf(l_objSymbolValue));                        
                    }

                }
            }
            
            log.debug("項目値セットの終了 LOOP " + i);
        }

        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (getMAX処理件数)<BR>
     * MAX処理件数を取得する。<BR>
     * <BR>
     * this.MAX処理件数を返却する。
     * @@return int
     * @@roseuid 41C018DB00D8
     */
    public int getMaxDealNumber() 
    {
        return maxDealNumber;
    }

    /**
     * (get連結項目デリミッタ値)<BR>
     * 連結項目デリミッタ値を取得する。<BR>
     * <BR>
     * 引数.連結項目デリミッタに該当する値を返却する。<BR>
     * <BR>
     * １）　@引数.連結項目デリミッタ == 1 の場合、半角SPACEを返却。<BR>
     * <BR>
     * ２）　@引数.連結項目デリミッタ == 2 の場合、全角SPACEを返却。<BR>
     * <BR>
     * ３）　@引数.連結項目デリミッタ == 3 の場合、ハイフン（"-"）を返却。<BR>
     * <BR>
     * ４）　@上記以外の場合、空文字（""）を返却。<BR>
     * <BR>
     * @@param l_strCatDelimitter - (連結項目デリミッタ)<BR>
     * 連結項目デリミッタ<BR>
     * @@return String
     */
    public String getCatDelimitter(String l_strCatDelimitter)
    {
        final String STR_METHOD_NAME = "getCatDelimitter(String)";
        log.entering(STR_METHOD_NAME);

        //引数.連結項目デリミッタに該当する値を返却する。
        //１）　@引数.連結項目デリミッタ == 1 の場合、半角SPACEを返却。
        if (WEB3CatDelimitterDef.HALF_SPACE.equals(l_strCatDelimitter))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3InformCatDelimitterDef.HALF_SPACE;
        }

        //２）　@引数.連結項目デリミッタ == 2 の場合、全角SPACEを返却。
        else if (WEB3CatDelimitterDef.FULL_SPACE.equals(l_strCatDelimitter))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3InformCatDelimitterDef.FULL_SPACE;
        }

        //３）　@引数.連結項目デリミッタ == 3 の場合、ハイフン（"-"）を返却。
        else if (WEB3CatDelimitterDef.HYPHEN.equals(l_strCatDelimitter))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3InformCatDelimitterDef.HYPHEN;
        }

        //４）　@上記以外の場合、空文字（""）を返却。
        log.exiting(STR_METHOD_NAME);
        return WEB3InformCatDelimitterDef.EMPTY_WORD;
    }
    
}
@
