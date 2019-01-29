head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.17.01.33.32;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8804d8162a91dd2;
filename	WEB3QtpRichPushObjectToXMLConverter.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        :　@QTPリッチクライアントプッシュメッセージXMLコンバーター(WEB3QtpRichPushObjectToXMLConverter.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/02/06 孫(FLJ) 新規作成
                  : 2009/06/03 毛(FTL) 岩井対応
 */

package webbroker3.rcp.service.delegate.stdimpls;

import java.lang.reflect.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import webbroker3.util.*;

/**
 * QTPリッチクライアントプッシュメッセージXMLコンバーター
 * @@author  : 孫(FLJ)
 * @@version : 1.0
 */
public final class WEB3QtpRichPushObjectToXMLConverter
{

    /**
     * ログ出力ユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3QtpRichPushObjectToXMLConverter.class);

    /** XML HEAD */
    protected static final String XML_HEAD =
        "<?xml version='1.0' encoding='UTF-8'?>\n<!DOCTYPE tlg SYSTEM \"QnsVer1.dtd\">\n<tlg>\n";

    /** XML DESC HEAD */
    protected static final String XML_DESC_HEAD =
        "<dsc cdsc=\"";

    /** XML DESC TAIL */
    protected static final String XML_DESC_TAIL =
        "\" srvdsc=\"Apl\"/>\n";

    /** XML TAIL */
    protected static final String XML_TAIL =
        "</tlg>\n";

    /**
     * XML TEMPLETE
     */
    public static final String XML_TEMPLETE = "<ntc>\n"+
            "<nhd srlnum=\"_srlnum_\" sid=\"_sid_\" tm=\"_tm_\" knd=\"srv\"/>\n" +
            "<nmsg imp=\"1\">\n" +
            	"<title url=\"qtp:CMD=OPEN,DLG=TradingPanel,CONTENTS=_url__urlParams_\">\n" +
            	"_title_</title>\n" +
           	 	"<quote qcode=\"qtp:CMD=HOTKEY_qcodeParams_\">_qcodeName_</quote>\n" +
           		"_orderTypeName_\n" +
           		"<prc>_prc_</prc>_quantityText_\n" +
           		"<vol>_vol_</vol>\n" +
           		"<lnk url=\"qtp:CMD=OPEN,DLG=TradingPanel,CONTENTS=_url__urlParams_\">_lnkText_</lnk>\n" +
           		"<ftag>_tlgNtcNmsgFtagFatt_</ftag>\n" +
           "</nmsg>\n" +
	"</ntc>\n";

    /**
     * レスポンス解析用のパタン
     */
    public static final Pattern RESPONSE_PATTERN = Pattern.compile("<nhd srlnum=\"([0-9]+)\" sid=\"([A-Za-z0-9]+)\" tm=\"([0-9]+)\" knd=\"srv\"/>[\\x00-\\xff\\u25A0-\\uFFFF]+?<status>([A-Z_]+)</status>");

    /**
     * オブジェクトをXMLフォーマット変換
     * 
     * 
     * 戻り値：	引数nullの場合、戻り値が　@""
     * 			オブジェクト項目取得失敗の場合　@戻り値が　@""　@
     * 			必要な項目にはnullがある場合、戻り値が　@""
     *
     * @@param l_obj Object 
     * @@return String
     */
    public static String toXML(Object l_obj)
    {
        final String STR_METHOD_NAME = "toXML()";
        log.entering(STR_METHOD_NAME);
        String l_result = "";
        //引数はnullでない　@場合、xmlを作成
        if (l_obj != null)
        {
            l_result = XML_TEMPLETE;
            //すべてのフィールドを取得
            Field[] l_fields = l_obj.getClass().getDeclaredFields();
            for(int i = 0; i< l_fields.length ; i++)
            {
                l_fields[i].setAccessible(true);
                try
                {
                    //フィールドの値を取得
                    Object l_fValue = l_fields[i].get(l_obj);
                    String l_fName = "_"+l_fields[i].getName()+"_";
                    if(l_result.indexOf(l_fName) >0)
                    {
                        if (l_fValue != null && l_fValue instanceof String)
                        {
                            //nullでない場合、templateの同じ名前の部分を置換する
                            l_result = l_result.replaceAll("_"+l_fields[i].getName()+"_", (String)l_fValue );
                        }
                        else
                        {
                            // null の場合、「""」でtemplateと同じキー部分を置換する
                            l_result = l_result.replaceAll("_"+l_fields[i].getName()+"_", "" );
                            continue;
                        }
                    }
                }
                catch (Exception ex)
                {
                    //フィールド取得失敗の場合、結果を""に設定して、返す
                    log.error(ex.getMessage(), ex);
                    l_result = "";
                    break;
                }
            }
       }
       else
       {
           log.warn("Null Object. Build xml skip.");
       }

        log.exiting(STR_METHOD_NAME);
        return l_result;

    }

    /**
     * 結果レスポンスを解析して、結果ステータスをMapに保存、キーはリクエストのメッセージオブジェクト
     * 
     * 戻り値：　@引数文字列は　@null の場合、戻り値　@サイズ0　@のMapオブジェクト
     *
     * @@param l_obj Object
     * @@return String
     */
    public static Map parseResult(String l_res)
    {
        final String STR_METHOD_NAME = "parseResult()";
        log.entering(STR_METHOD_NAME);

        Map l_result = new HashMap();
        //引数結果レスポンス文字列がnullである場合、警告出力
        if (l_res == null)
        {
            log.warn("Response message is null. Can't parse result.");
        }
        else
        {
            //文字列パタンをマッチ
            Matcher l_matcher = RESPONSE_PATTERN.matcher(l_res);
            while(l_matcher.find())
            {
                //パタン見つかった場合
                //キーオブジェクトを作成
                WEB3QtpExcutionInformUnit l_unit = new WEB3QtpExcutionInformUnit();

                l_unit.setSrlnum(l_matcher.group(1));
                l_unit.setSid(l_matcher.group(2));
                l_unit.setTm(l_matcher.group(3));

                //ステータスを取得
                String l_status = l_matcher.group(4);

                //マップに保存
                l_result.put(l_unit,l_status);
            }
        }
        if(l_result.size() == 0)
        {
            log.warn("Not Found Unit Result String. All Response Error :" + l_res);
        }

        log.exiting(STR_METHOD_NAME);
        return l_result;

    }
}
@
