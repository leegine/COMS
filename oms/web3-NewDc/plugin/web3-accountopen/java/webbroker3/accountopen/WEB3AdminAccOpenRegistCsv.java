head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.30.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenRegistCsv.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座開設申込CSV(WEB3AdminAccOpenRegistCsv.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/17 鄭海良(中訊) 新規作成
*/

package webbroker3.accountopen;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.accountopen.data.AccOpenDlFormMasterRow;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CatDelimitterDef;
import webbroker3.gentrade.WEB3GentradeCsvDataModel;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;


/**
 * (口座開設申込CSV)<BR>
 * 口座開設申込CSV<BR>
 *   
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3AdminAccOpenRegistCsv extends WEB3GentradeCsvDataModel 
{

    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminAccOpenRegistCsv.class);
    
    /**
     * @@roseuid 41B45E6F01E4
     */
    public WEB3AdminAccOpenRegistCsv() 
    {
     
    }
    
    /**
     * (口座開設申込CSV)<BR>
     * コンストラクタ。 <BR>
     * <BR>
     * インスタンスを生成し、ヘッダ情報をセットする。 <BR>
     * 　@－super()にてインスタンスを生成する。 <BR>
     * 　@－createキーヘッダ()をコールし、キーヘッダ情報を作成する。 <BR>
     * 　@－createカラムヘッダ(証券会社コード，口座区分)をコールし、<BR>
     * カラムヘッダ情報を作成する。 <BR>
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strAccountDiv - 口座区分<BR>
     * <BR>
     * 0：個人アカウント　@1：法@人アカウント<BR>
     * 
     * @@return webbroker3.accountopen.WEB3AdminAccOpenRegistCsv
     * @@roseuid 41A14A2203B4
     */
    public WEB3AdminAccOpenRegistCsv(String l_strInstitutionCode, String l_strAccountDiv)
        throws WEB3BaseException 
    {
        super();
        this.createKeyHeader();
        this.createColumnHeader(l_strInstitutionCode, l_strAccountDiv);
    }
    
    /**
     * (createカラムヘッダ)<BR>
     * CSVカラムモデルの配列を生成し、setカラムヘッダ()にてインスタンスにセットする。 <BR>
     * <BR>
     * １）　@ダウンロードフォームマスタ取得<BR>
     * 　@以下の条件で、口座開設申込ＤＬフォームマスタテーブルを検索する。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@口座開設申込ＤＬフォームマスタ.証券会社コード = 証券会社コード And<BR>
     * 　@口座開設申込ＤＬフォームマスタ.口座区分 = 口座区分<BR>
     * <BR>
     * 　@[取得順]<BR>
     * 　@カラム番号 昇順（：asc）<BR>
     * <BR>
     * 　@該当行が存在しない場合は例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01321<BR>
     * <BR>
     * ２）　@項目ヘッダ生成<BR>
     * 　@１）で取得した各要素毎に、以下の通り口座開設申込カラムモデルの配列を生成し<BR>
     * setカラムヘッダ()にてインスタンスにセットする。 <BR>
     * <BR>
     * 　@[口座開設申込カラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@口座開設申込ＤＬフォームマスタ.項目ラベル<BR>
     * 　@カラム番号： index<BR>
     * 　@項目型：　@口座開設申込ＤＬフォームマスタ.項目型<BR>
     * 　@日付フォーマット：　@口座開設申込ＤＬフォームマスタ.日付フォーマット<BR>
     * 　@入力項目物理名：　@口座開設申込ＤＬフォームマスタ.入力項目物理名<BR>
     * 　@連結項目デリミッタ：　@口座開設申込ＤＬフォームマスタ.連結項目デリミッタ<BR>
     * 　@セクション番号：　@口座開設申込ＤＬフォームマスタ.セクション番号<BR>
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strAccountDiv - 口座区分<BR>
     * <BR>
     * 0：個人アカウント　@1：法@人アカウント<BR>
     * @@roseuid 41A14A220385
     */
    protected void createColumnHeader(String l_strInstitutionCode, String l_strAccountDiv)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " createColumnHeader(String, String)";    
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //１）　@ダウンロードフォームマスタ取得
            List l_lisRecord = Processors.getDefaultProcessor().doFindAllQuery(
                AccOpenDlFormMasterRow.TYPE,
                "institution_code = ? AND account_div = ?",
                "column_number asc",
                null,
                new Object[]{l_strInstitutionCode, l_strAccountDiv});//DataException
            int l_intRecordCount = l_lisRecord.size();    
                
            if (l_intRecordCount == 0)
            {
                String l_strErrorMessage = 
                    "ダウンロードフォームマスタの取得に失敗しました。institutionCode = " 
                    + l_strInstitutionCode 
                    + ",accountDiv = " 
                    + l_strAccountDiv;
                log.debug(l_strErrorMessage);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01321,
                    getClass().getName() + STR_METHOD_NAME,
                    l_strErrorMessage); 
            }
        
            //２）　@項目ヘッダ生成
            log.debug("l_intRecordCount = " + l_intRecordCount);
            WEB3AdminAccOpenRegistCsvColumnModel[] l_models = new WEB3AdminAccOpenRegistCsvColumnModel[l_intRecordCount];
            for (int i = 0; i < l_intRecordCount; i++)
            {
                log.debug("２）　@項目ヘッダ生成");
                AccOpenDlFormMasterRow l_row = (AccOpenDlFormMasterRow)l_lisRecord.get(i);
                SimpleDateFormat l_dateFormat = null;
                if (l_row.getDateFormat() != null)
                {
                    l_dateFormat = new SimpleDateFormat(l_row.getDateFormat());
                }               
                
                WEB3AdminAccOpenRegistCsvColumnModel l_model = 
                    new WEB3AdminAccOpenRegistCsvColumnModel(
                        l_row.getColumnLabel(), 
                        i, 
                        l_row.getColumnType(), 
                        l_dateFormat, 
                        l_row.getInputItemSymbolName(),
                        l_row.getCatDelimitter(),
                        Integer.toString(l_row.getSectionNumber()));
                l_models[i] = l_model;
            }
            
            this.setColumnHeader(l_models);
        }
        catch (DataException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (createキーヘッダ)<BR>
     * キーヘッダをセットする。 <BR>
     * <BR>
     * 　@以下の通り文字列の配列を生成し、setキーヘッダ()にてインスタンスにセットする。 <BR>
     * <BR>
     * [キーヘッダ配列] <BR>
     * <BR>
     * －　@index = 0 <BR>
     * 　@現在日時を"yyyy/MM/dd HH:mm:ss"の形式にformatした文字列。 <BR>
     * <BR>
     * (*1) 現在日時 <BR>
     * TradingSystem.getSystemTimestamp()<BR>
     * @@roseuid 41A14A220394
     */
    protected void createKeyHeader() 
    {
        final String STR_METHOD_NAME = " createKeyHeader()";    
        log.entering(STR_METHOD_NAME);
        
        //index 0
        String[] l_strKeyHeaders = new String[1];
        Timestamp l_tsCurrentTime = GtlUtils.getTradingSystem( ).getSystemTimestamp();
        l_strKeyHeaders[0] = WEB3DateUtility.formatDate(l_tsCurrentTime, "yyyy/MM/dd HH:mm:ss");        
        
        log.debug("l_strKeyHeaders = " + l_strKeyHeaders[0]);
        this.setKeyHeader(l_strKeyHeaders);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set項目値)<BR>
     * データモデルに項目値をセットする。<BR>
     * <BR>
     * this.カラムヘッダ[]　@の各要素毎に、以下の１）～３）を繰り返す（LOOP）。<BR>
     * <BR>
     * １）　@入力項目名取得<BR>
     * 　@this.カラムヘッダ[index]を口座開設申込カラムモデル型に変換する（Cast）。<BR>
     * 　@this.カラムヘッダ[index].get入力項目物理名()にて、入力項目名を取得する。<BR>
     * 　@this.カラムヘッダ[index].get連結項目デリミッタ()にて、連結項目デリミッタを<BR>
     * 取得する。<BR>
     * 　@this.カラムヘッダ[index].getセクション番号()にて、セクション番号を取得する。<BR>
     * <BR>
     * ２）　@入力項目値取得<BR>
     * 　@口座開設見込客.get項目値()にて、口座開設見込客の該当項目値を<BR>
     * 取得する。<BR>
     * <BR>
     * 　@[get項目値()に指定する引数]<BR>
     * 　@列物理名：　@１）で取得した入力項目名<BR>
     * <BR>
     * ３）　@値セット <BR>
     * <BR>
     * 　@３－１）　@１項目より複数カラムを出力する場合<BR>
     * 　@　@１項目より複数カラムを出力する場合<BR>
     * 　@　@（連結項目デリミッタ != null and 連結項目デリミッタ != 0：なし） and <BR>
     * (セクション番号 != null）、<BR>
     * <BR>
     *   ３－１－１）　@連結項目デリミッタが４：郵便番号の場合 <BR>
     *    [セクション番号が’0’の場合] <BR>
     *      ２）で取得した入力項目値の上3桁を取得する。(substring(0,3)) <BR>
     *    [セクション番号が’1’の場合] <BR>
     *      ２）で取得した入力項目値の下4桁を取得する。(substring(3,7)) <BR>
     *   ３－１－２）　@連結項目デリミッタが４：郵便番号以外の場合 <BR>
     * 　@　@２）で取得した入力項目値を連結項目デリミッタにて分割（Split）した値の<BR>
     * セクション番号で指定された要素を入力項目値とする。<BR>
     * <BR>
     * 　@３－２）　@項目値セット<BR>
     * 　@　@this.set項目値()にて項目値をセットする。 <BR>
     * <BR>
     * 　@　@[set項目値()に指定する引数] <BR>
     * 　@　@行番号：　@引数の行番号<BR>
     * 　@　@カラム：　@this.カラムヘッダ[index]<BR>
     * 　@　@値：　@'"' + ２）または、３－１－１）または、３－１－２）で取得した項目値 + '"' <BR>
     * <BR>
     * 　@　@※項目値はダブルクオート（”）で囲む。<BR>
     * @@param l_intLineNumber - 行番号
     * @@param l_accOpenExpAccountOpen - 口座開設見込客
     * @@roseuid 41A151DA01FE
     */
    public void setValue(int l_intLineNumber, WEB3AccOpenExpAccountOpen l_accOpenExpAccountOpen)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " setValue(int, WEB3AccOpenExpAccountOpen)";    
        log.entering(STR_METHOD_NAME);
        
        int l_intColHeaderLen = this.columnHeader.length;
        log.debug("l_intColHeaderLen = " + l_intColHeaderLen);
        for (int i = 0; i < l_intColHeaderLen; i++)
        {
            log.debug("１）　@入力項目名取得");
            //１）　@入力項目名取得
            WEB3AdminAccOpenRegistCsvColumnModel l_model = 
                (WEB3AdminAccOpenRegistCsvColumnModel)this.columnHeader[i];
            log.debug("**************i = " + i);
            String l_strInputItemSymbolName = l_model.getInputItemSymbolName();
            String l_strCatDelimitter = l_model.getCatDelimitter();
            String l_strSectionNo = l_model.getSectionNo();
                
            //２）　@入力項目値取得
            Object l_obj = l_accOpenExpAccountOpen.getItemValue(l_strInputItemSymbolName);
            String l_strValue = null;
            
            //３）　@値セット 
            //　@３－１）　@１項目より複数カラムを出力する場合
            //　@　@１項目より複数カラムを出力する場合
            // （連結項目デリミッタ != null and 連結項目デリミッタ != 0：なし） and (セクション番号 != null） 
            if (l_obj != null
                && l_strCatDelimitter != null
                && !WEB3CatDelimitterDef.WITHOUT.equals(l_strCatDelimitter)
                && l_strSectionNo != null)
            {
                l_strValue = l_obj.toString();
                log.debug("l_strValue = " + l_strValue);

                log.debug("１項目より複数カラムを出力する場合");

                // ３－１－１）  連結項目デリミッタが４：郵便番号の場合 
                //  [セクション番号が’0’の場合] 
                //  ２）で取得した入力項目値の上3桁を取得する。(substring(0,3)) 
                //  [セクション番号が’1’の場合] 
                //  ２）で取得した入力項目値の下4桁を取得する。(substring(3,7))
                if (WEB3CatDelimitterDef.ZIP_CODE.equals(l_strCatDelimitter))
                {
                    final String STR_NO0 = "0";
                    final String STR_NO1 = "1";

                    if (STR_NO0.equals(l_strSectionNo))
                    {                        
                        l_strValue = l_strValue.substring(0, 3);
                    }
                    else if (STR_NO1.equals(l_strSectionNo))
                    {
                        l_strValue = l_strValue.substring(3, 7);
                    }
                }
                // ３－１－２）　@連結項目デリミッタが４：郵便番号以外の場合 
                //  ２）で取得した入力項目値を連結項目デリミッタにて分割（Split）
                //  した値のセクション番号で指定された要素を入力項目値とする
                else
                {
                    String[] l_strSplits = new String[] { " ", "　@", "-" };
                    String[] l_strCatDelimitters =
                        new String[] {
                            WEB3CatDelimitterDef.HALF_SPACE,
                            WEB3CatDelimitterDef.FULL_SPACE,
                            WEB3CatDelimitterDef.HYPHEN };
                    String l_strSplit = null;
                    for (int j = 0; j < l_strCatDelimitters.length; j++)
                    {
                        if (l_strCatDelimitters[j].equals(l_strCatDelimitter))
                        {
                            l_strSplit = l_strSplits[j];
                            break;
                        }
                    }
                    if (l_strSplit == null)
                    {
                        String l_strMessage =
                            "１項目より複数カラムを出力する場合,データエラー! "
                                + "data = "
                                + l_strValue
                                + "; delimitter = "
                                + l_strCatDelimitter
                                + "; section no ="
                                + l_strSectionNo;
                        log.debug(l_strMessage);
                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                            this.getClass().getName() + STR_METHOD_NAME,
                            l_strMessage);
                    }

                    String[] l_strValues = l_strValue.split(l_strSplit);

                    int l_intSectionNo = Integer.parseInt(l_strSectionNo);

                    if (l_strValues == null || l_strValues.length == 0 || l_strValues.length <= l_intSectionNo)
                    {
                        String l_strMessage =
                            "１項目より複数カラムを出力する場合,データエラー! "
                                + "data = "
                                + l_strValue
                                + "; delimitter = "
                                + l_strCatDelimitter
                                + "; section no ="
                                + l_strSectionNo;
                        log.debug(l_strMessage);
                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                            this.getClass().getName() + STR_METHOD_NAME,
                            l_strMessage);
                    }
                    l_strValue = l_strValues[l_intSectionNo];
                }
            }
            
            //　@３－２）　@項目値セット<BR>
            log.debug("l_strValue = " + l_strValue);
            if (l_strValue == null)
            {
                if((l_obj instanceof String)
                    && l_model.getDateFormat() != null)
                {
                    Date l_dat = WEB3DateUtility.getDate((String)l_obj,"yyMMdd");
                    this.setValue(l_intLineNumber, l_model, l_dat); 
                }
                else if(l_obj instanceof BooleanEnum)
                {
                    Integer l_int = new Integer(((BooleanEnum)l_obj).intValue());
                    this.setValue(l_intLineNumber, l_model, l_int);
                }
                else
                {
                    this.setValue(l_intLineNumber, l_model, l_obj);            
                }
            }
            else
            {
                this.setValue(l_intLineNumber, l_model, l_strValue);            
            }
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
