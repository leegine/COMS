head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.56.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3InformCsvUtility.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright                : (株)大和総研 証券ソリューションシステム第二部
File Name                : 連絡管理CSV共通処理クラス(WEB3InformCsvUtility)
Author Name           : Daiwa Institute of Research
Revesion History     : 2005/02/03 王暁傑(中訊) 作成
*/
package webbroker3.inform.util;

/**
 * 
 * 連絡管理CSV共通処理クラス
 * @@author 王暁傑
 * @@version 1.0
 */
public class WEB3InformCsvUtility
{
    private static char csvValueSeperator = '"';
    
    /**
     *コンストラクタ 
     */
    private WEB3InformCsvUtility()
    {
        super();
    }

    /**
     * getValueForCSVLine
     *
     *CSV行用の値を取得.
     *処理ルール:
     *　@　@1)項目値はダブルクオート（”）で囲む。
     * 　@　@　@例:  銘柄コード >> "銘柄コード"
     *  　@　@　@　@　@12345  >> "12345"
     *　@　@2)項目値に ダブルクオート(")を含む場合.
     *       ダブルクオート(")の直前にもう一個ダブルクオート(")を追加します。
     * 　@　@　@例:　@123"455 >> 123""455
     * 　@　@　@　@　@　@銘柄"コード"　@>>　@銘柄""コード""
     */
    private static String getValueForCSVLine(String l_strValue)
    {
        if (l_strValue == null)
        {
            return null;
        }
        
        int          end;
        int          start = 0;
        StringBuffer buf;

        end = l_strValue.indexOf(csvValueSeperator);

        if (end == -1) 
        {
            return new String(csvValueSeperator + l_strValue + csvValueSeperator);
        }

        buf = new StringBuffer();
        
        while (true) 
        {
            if (end == -1) 
            {
                buf.append(l_strValue.substring(start));
                break;
            }
            
            buf.append(l_strValue.substring(start, end));
            buf.append(csvValueSeperator);
            buf.append(csvValueSeperator);
            start = end + 1;
            
            if (start >= l_strValue.length()) break;

            end = l_strValue.indexOf(csvValueSeperator, start);
        }

        return new String(csvValueSeperator + buf.toString() + csvValueSeperator);
        
    }
}
@
