head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3StringTypeUtility.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 文字列の文字種を判断する機@能を実装するユーティリティ(WEB3StringTypeUtility.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/10 張宝楠 (中訊) 新規作成
Revesion History : 2004/08/12 孟 東(中訊) formatNumber(long, int)を追加
Revesion History : 2004/08/13 孟 東(中訊) formatNumber(double)を追加
Revesion History : 2004/08/13 孟 東(中訊) formatNumber(double)を修正
Revesion History : 2004/08/25 孟 東(中訊) isInteger(String)を追加
Revesion History : 2004/08/25 孟 東(中訊) getNubmerLength(String)を追加
Revesion History : 2004/08/25 孟 東(中訊) getIntegerDigits(String)を追加
Revesion History : 2004/08/25 孟 東(中訊) getFractionDigits(String)を追加
Revesion History : 2004/08/31 孟 東(中訊) formatNumber(double)を修正
Revesion History : 2004/09/07 孟 東(中訊) formatNumber(double)の定義がsynchronizedを追加
Revesion History : 2004/09/23 鄒 政(中訊) isMailAddress(String)を追加
Revesion History : 2004/10/09 鄒 政(中訊) isMailAddress(String)を修正
Revesion History : 2005/07/15 孟 東(中訊) isMinus(String)を追加
Revesion History : 2006/07/21 凌建平(中訊) 仕様変更・モデルNo.201を対応
Revesion History : 2007/01/23 車 進(中訊) toJapDate(Date)を追加
Revesion History : 2007/07/18 佐藤(SCS)   仕様変更・実装の問題No.017を対応
Revesion History : 2008/08/15 趙林鵬(中訊) 仕様変更・モデルNo.331を対応
Revesion History : 2008/09/23 趙林鵬(中訊) 仕様変更・モデルNo.334を対応
Revesion History : 2009/02/04 趙林鵬(中訊) 仕様変更・モデルNo.336、337を対応
*/

package webbroker3.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;


/**
 * 文字列の文字種を判断する機@能を実装するユーティリティ・クラス。<BR>
 * <BR>
 * @@author 菊地(SRA)
 * @@version 1.0
 */
public class WEB3StringTypeUtility 
{

    //private static final char asciiStart           = '\u0020'; //unicodeのASCII文字の開始値
    //private static final char asciiEnd             = '\u007E'; //unicodeのASCII文字の終了値
    private static final char numberStart          = '\u0030'; //unicodeの半角数字の開始値(0)
    private static final char numberEnd            = '\u0039'; //unicodeの半角数字の終了値(9)
    private static final char zenNumberStart          = '\uff10'; //unicodeの全角数字の開始値(０)
    private static final char zenNumberEnd            = '\uff19'; //unicodeの全角数字の終了値(９)
    
    private static final char lowerEnglishStart    = '\u0061'; //unicodeの半角英字小文字の開始値(a)
    private static final char lowerEnglishEnd      = '\u007A'; //unicodeの半角英字小文字の終了値(z)
    private static final char upperEnglishStart    = '\u0041'; //unicodeの半角英字大文字の開始値(A)
    private static final char upperEnglishEnd      = '\u005A'; //unicodeの半角英字大文字の終了値(Z)
    private static final char zenLowerEnglishStart    = '\uff41'; //unicodeの全角英字小文字の開始値(ａ)
    private static final char zenLowerEnglishEnd    = '\uff5A'; //unicodeの全角英字小文字の終了値(ｚ)
    private static final char zenUpperEnglishStart    = '\uff21'; //unicodeの全角英字大文字の開始値(Ａ)
    private static final char zenUpperEnglishEnd    = '\uff3A'; //unicodeの全角英字大文字の終了値(Ｚ)
    
    private static final char zenkakukatakanaStart = '\u30A1'; //unicodeの全角カタカナの開始値
    private static final char zenkakukatakanaEnd   = '\u30FA'; //unicodeの全角カタカナの終了値()
    private static final char hankakuKatakanaStart = '\uFF66'; //unicodeの半角カタカナの開始値()
    private static final char hankakuKatakanaEnd   = '\uFF9F'; //unicodeの半角カタカナの終了値()

    

    private static final String DEFAULT_DOUBLE_FORMAT_PATTERN = "#";
    
    private static DecimalFormat decimalFormat = new DecimalFormat();
    

    private static char[][] single = {
        
        //カナ   ｱ～ﾝ
        {0xff71} ,// ｱ
        {0xff72} ,// ｲ
        {0xff73} ,// ｳ
        {0xff74} ,// ｴ
        {0xff75} ,// ｵ
        {0xff76} ,// ｶ
        {0xff77} ,// ｷ
        {0xff78} ,// ｸ
        {0xff79} ,// ｹ
        {0xff7a} ,// ｺ
        {0xff7b} ,// ｻ
        {0xff7c} ,// ｼ
        {0xff7d} ,// ｽ
        {0xff7e} ,// ｾ
        {0xff7f} ,// ｿ
        {0xff80} ,// ﾀ
        {0xff81} ,// ﾁ
        {0xff82} ,// ﾂ
        {0xff83} ,// ﾃ
        {0xff84} ,// ﾄ
        {0xff85} ,// ﾅ
        {0xff86} ,// ﾆ
        {0xff87} ,// ﾇ
        {0xff88} ,// ﾈ
        {0xff89} ,// ﾉ
        {0xff8a} ,// ﾊ
        {0xff8b} ,// ﾋ
        {0xff8c} ,// ﾌ
        {0xff8d} ,// ﾍ
        {0xff8e} ,// ﾎ
        {0xff8f} ,// ﾏ
        {0xff90} ,// ﾐ
        {0xff91} ,// ﾑ
        {0xff92} ,// ﾒ
        {0xff93} ,// ﾓ
        {0xff94} ,// ﾔ
        {0xff95} ,// ﾕ
        {0xff96} ,// ﾖ
        {0xff97} ,// ﾗ
        {0xff98} ,// ﾘ
        {0xff99} ,// ﾙ
        {0xff9a} ,// ﾚ
        {0xff9b} ,// ﾛ
        {0xff9c} ,// ﾜ
        {0xff66} ,// ｦ
        {0xff9d} ,// ﾝ
        
        //カナ   ｶﾞ～ｺﾞ
        {0xff76,0xff9e} ,// ｶﾞ
        {0xff77,0xff9e} ,// ｷﾞ
        {0xff78,0xff9e} ,// ｸﾞ
        {0xff79,0xff9e} ,// ｹﾞ
        {0xff7a,0xff9e} ,// ｺﾞ
        
        //カナ   ｻﾞ～ｿﾞ
        {0xff7b,0xff9e} ,// ｻﾞ
        {0xff7c,0xff9e} ,// ｼﾞ
        {0xff7d,0xff9e} ,// ｽﾞ
        {0xff7e,0xff9e} ,// ｾﾞ
        {0xff7f,0xff9e} ,// ｿﾞ
        
        //カナ   ﾀﾞ～ﾄﾞ
        {0xff80,0xff9e} ,// ﾀﾞ
        {0xff81,0xff9e} ,// ﾁﾞ
        {0xff82,0xff9e} ,// ﾂﾞ
        {0xff83,0xff9e} ,// ﾃﾞ
        {0xff84,0xff9e} ,// ﾄﾞ
        
        //カナ   ﾊﾞ～ﾎﾞ
        {0xff8a,0xff9e} ,// ﾊﾞ
        {0xff8b,0xff9e} ,// ﾋﾞ
        {0xff8c,0xff9e} ,// ﾌﾞ
        {0xff8d,0xff9e} ,// ﾍﾞ
        {0xff8e,0xff9e} ,// ﾎﾞ
        
        //カナ   ｳﾞ
        {0xff73,0xff9e} ,// ｳﾞ
        
        //カナ   ﾊﾟ～ﾎﾟ
        {0xff8a,0xff9f} ,// ﾊﾟ
        {0xff8b,0xff9f} ,// ﾋﾟ
        {0xff8c,0xff9f} ,// ﾌﾟ
        {0xff8d,0xff9f} ,// ﾍﾟ
        {0xff8e,0xff9f} ,// ﾎﾟ
        
//        //カナ   ｧ～ｫ
//        {0xff67} ,// ｧ
//        {0xff68} ,// ｨ
//        {0xff69} ,// ｩ
//        {0xff6a} ,// ｪ
//        {0xff6b} ,// ｫ

//        {0xff6c} ,// ｬ
//        {0xff6d} ,// ｭ
//        {0xff6e} ,// ｮ
//        {0xff6f} ,// ｯ
        
        //カナ   ｱ～ｵ
        {0xff71} ,// ｱ
        {0xff72} ,// ｲ
        {0xff73} ,// ｳ
        {0xff74} ,// ｴ
        {0xff75} ,// ｵ
        
        //カナ   ｶ、ｹ、ﾂ
        {0xff76} ,// ｶ  
        {0xff79} ,// ｹ  
        {0xff82} ,// ﾂ  
        
        //カナ   ﾔ、ﾕ、ﾖ
        {0xff94} ,// ﾔ
        {0xff95} ,// ﾕ
        {0xff96} ,// ﾖ
        
        //カナ   ﾜ、-
        {0xff9c} ,// ﾜ
        {0x002d} ,// -
        
        //英字  A～Z
        {0x0041} ,// A
        {0x0042} ,// B
        {0x0043} ,// C
        {0x0044} ,// D
        {0x0045} ,// E
        {0x0046} ,// F
        {0x0047} ,// G
        {0x0048} ,// H
        {0x0049} ,// I
        {0x004a} ,// J
        {0x004b} ,// K
        {0x004c} ,// L
        {0x004d} ,// M
        {0x004e} ,// N
        {0x004f} ,// O
        {0x0050} ,// P
        {0x0051} ,// Q
        {0x0052} ,// R
        {0x0053} ,// S
        {0x0054} ,// T
        {0x0055} ,// U
        {0x0056} ,// V
        {0x0057} ,// W
        {0x0058} ,// X
        {0x0059} ,// Y
        {0x005a} ,// Z
        
        //英字  a～z
        {0x0061} ,// a
        {0x0062} ,// b
        {0x0063} ,// c
        {0x0064} ,// d
        {0x0065} ,// e
        {0x0066} ,// f
        {0x0067} ,// g
        {0x0068} ,// h
        {0x0069} ,// i
        {0x006a} ,// j
        {0x006b} ,// k
        {0x006c} ,// l
        {0x006d} ,// m
        {0x006e} ,// n
        {0x006f} ,// o
        {0x0070} ,// p
        {0x0071} ,// q
        {0x0072} ,// r
        {0x0073} ,// s
        {0x0074} ,// t 
        {0x0075} ,// u
        {0x0076} ,// v
        {0x0077} ,// w
        {0x0078} ,// x
        {0x0079} ,// y
        {0x007a} ,// z
        
        //数字  0～9
        {0x0030} ,// 0
        {0x0031} ,// 1
        {0x0032} ,// 2
        {0x0033} ,// 3
        {0x0034} ,// 4
        {0x0035} ,// 5
        {0x0036} ,// 6
        {0x0037} ,// 7
        {0x0038} ,// 8
        {0x0039} ,// 9
        
        //記号  -、(、)  
        {0x002d} ,// -  
        {0x0028} ,// (
        {0x0029} ,// )
        {0x002e} ,// .
        
        //スペース " "
        {0x0020} //" "    

        };

    private static char[] multi={   
        
        //カナ   ア～ン
        0x30a2 ,// ア
        0x30a4 ,// イ
        0x30a6 ,// ウ
        0x30a8 ,// エ
        0x30aa ,// オ
        0x30ab ,// カ
        0x30ad ,// キ
        0x30af ,// ク
        0x30b1 ,// ケ
        0x30b3 ,// コ
        0x30b5 ,// サ
        0x30b7 ,// シ
        0x30b9 ,// ス
        0x30bb ,// セ
        0x30bd ,// ソ
        0x30bf ,// タ
        0x30c1 ,// チ
        0x30c4 ,// ツ
        0x30c6 ,// テ
        0x30c8 ,// ト
        0x30ca ,// ナ
        0x30cb ,// ニ
        0x30cc ,// ヌ
        0x30cd ,// ネ
        0x30ce ,// ノ
        0x30cf ,// ハ
        0x30d2 ,// ヒ
        0x30d5 ,// フ
        0x30d8 ,// ヘ
        0x30db ,// ホ
        0x30de ,// マ
        0x30df ,// ミ
        0x30e0 ,// ム
        0x30e1 ,// メ
        0x30e2 ,// モ
        0x30e4 ,// ヤ
        0x30e6 ,// ユ
        0x30e8 ,// ヨ
        0x30e9 ,// ラ
        0x30ea ,// リ
        0x30eb ,// ル
        0x30ec ,// レ
        0x30ed ,// ロ
        0x30ef ,// ワ
        0x30f2 ,// ヲ
        0x30f3 ,// ン
        
        //カナ   ガ～ゴ
        0x30ac ,// ガ
        0x30ae ,// ギ
        0x30b0 ,// グ
        0x30b2 ,// ゲ
        0x30b4 ,// ゴ
        
        //カナ   ザ～ゾ
        0x30b6 ,// ザ
        0x30b8 ,// ジ
        0x30ba ,// ズ
        0x30bc ,// ゼ
        0x30be ,// ゾ
        
        //カナ   ダ～ド
        0x30c0 ,// ダ
        0x30c2 ,// ヂ
        0x30c5 ,// ヅ
        0x30c7 ,// デ
        0x30c9 ,// ド
        
        //カナ   バ～ボ
        0x30d0 ,// バ
        0x30d3 ,// ビ
        0x30d6 ,// ブ
        0x30d9 ,// ベ
        0x30dc ,// ボ
        
        //カナ   ヴ
        0x30f4 ,//ヴ
        
        //カナ   パ～ポ
        0x30d1 ,// パ
        0x30d4 ,// ピ
        0x30d7 ,// プ
        0x30da ,// ペ
        0x30dd,  // ポ
        
        //カナ   ァ@～ォ
        0x30a1 ,// ァ@
        0x30a3 ,// ィ
        0x30a5 ,// ゥ
        0x30a7 ,// ェ
        0x30a9 ,// ォ
        
        //カナ   ヵ、ヶ、ッ
        0x30f5 ,// ヵ
        0x30f6 ,// ヶ
        0x30c3 ,// ッ
        
        //カナ   ャ、ュ、ョ
        0x30e3 ,// ャ
        0x30e5 ,// ュ
        0x30e7 ,// ョ
        
        //カナ   ヮ、ー
        0x30ee ,// ヮ
        0x30fc ,// ー
        
        //英字  Ａ～Ｚ
        0xff21 ,// Ａ
        0xff22 ,// Ｂ
        0xff23 ,// Ｃ
        0xff24 ,// Ｄ
        0xff25 ,// Ｅ
        0xff26 ,// Ｆ
        0xff27 ,// Ｇ
        0xff28 ,// Ｈ
        0xff29 ,// Ｉ
        0xff2a ,// Ｊ
        0xff2b ,// Ｋ
        0xff2c ,// Ｌ
        0xff2d ,// Ｍ
        0xff2e ,// Ｎ
        0xff2f ,// Ｏ
        0xff30 ,// Ｐ
        0xff31 ,// Ｑ
        0xff32 ,// Ｒ
        0xff33 ,// Ｓ
        0xff34 ,// Ｔ
        0xff35 ,// Ｕ
        0xff36 ,// Ｖ
        0xff37 ,// Ｗ
        0xff38 ,// Ｘ
        0xff39 ,// Ｙ
        0xff3a ,// Ｚ
        
        //英字  ａ～ｚ
        0xff41 ,// ａ
        0xff42 ,// ｂ
        0xff43 ,// ｃ
        0xff44 ,// ｄ
        0xff45 ,// ｅ
        0xff46 ,// ｆ
        0xff47 ,// ｇ
        0xff48 ,// ｈ
        0xff49 ,// ｉ
        0xff4a ,// ｊ
        0xff4b ,// ｋ
        0xff4c ,// ｌ
        0xff4d ,// ｍ
        0xff4e ,// ｎ
        0xff4f ,// ｏ
        0xff50 ,// ｐ
        0xff51 ,// ｑ
        0xff52 ,// ｒ
        0xff53 ,// ｓ
        0xff54 ,// ｔ
        0xff55 ,// ｕ
        0xff56 ,// ｖ
        0xff57 ,// ｗ
        0xff58 ,// ｘ
        0xff59 ,// ｙ
        0xff5a ,// ｚ
        
        //数字  ０～９
        0xff10 ,// ０
        0xff11 ,// １
        0xff12 ,// ２
        0xff13 ,// ３
        0xff14 ,// ４
        0xff15 ,// ５
        0xff16 ,// ６
        0xff17 ,// ７
        0xff18 ,// ８
        0xff19 ,// ９
        
        //記号  －、（、）     
        0xff0d ,//－
        0xff08 ,// （
        0xff09 ,// ）
        0xff0e ,// ．
        
        //スペース "　@"
        0x3000 //"　@"  
    };
    
    private static char[] upperFullKana = { 
        0x30a2, // ア
        0x30a4, // イ
        0x30a6, // ウ
        0x30a8, // エ
        0x30aa, // オ
        0x30e4, // ヤ
        0x30e6, // ユ
        0x30e8, // ヨ
        0x30c4 // ツ
    };

    private static char[] lowerFullKana = { 
        0x30a1, // ァ@
        0x30a3, // ィ
        0x30a5, // ゥ
        0x30a7, // ェ
        0x30a9, // ォ
        0x30e3, // ャ
        0x30e5, // ュ
        0x30e7, // ョ
        0x30c3 // ッ
    };
    
    private static char[] upperHalfKana = { 
        0xff71 ,// ｱ
        0xff72 ,// ｲ
        0xff73 ,// ｳ
        0xff74 ,// ｴ
        0xff75 ,// ｵ
        0xff94 ,// ﾔ
        0xff95 ,// ﾕ
        0xff96 ,// ﾖ
        0xff82  // ﾂ
    };
    private static char[] lowerHalfKana = { 
        0xff67 ,// ｧ
        0xff68 ,// ｨ
        0xff69 ,// ｩ
        0xff6a ,// ｪ
        0xff6b ,// ｫ
        0xff6c ,// ｬ
        0xff6d ,// ｭ
        0xff6e ,// ｮ
        0xff6f  // ｯ
    };
    
    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3StringTypeUtility.class);

    
    /**
     * 文字列が数値であるかをチェックする。<BR>
     * 数値の場合はtrueを、そうでない場合はfalseを返す。<BR>
     * @@param l_str 一つの文字列
     * @@return 数値の場合はtrueを、そうでない場合はfalseを返す。
     * @@roseuid 4088B65B0000
     */
    public static boolean isNumber(String l_str) 
    {   
        if (isEmpty(l_str)) 
        {
            return false;
        }
 
        try
        {
            Double.parseDouble(l_str);
        }
        catch (NumberFormatException e)
        {
            return false;
        }
 
        return true;
    }

    /**
     * 文字列が整数であるかをチェックする。<BR>
     * 整数の場合はtrueを、そうでない場合はfalseを返す。<BR>
     * @@param l_str 一つの文字列
     * @@return 整数sの場合はtrueを、そうでない場合はfalseを返す。
     * @@roseuid 4088B65B0000
     */
    public static boolean isInteger(String l_str) 
    {   
        if (isEmpty(l_str)) 
        {
            return false;
        }
 
        try
        {
            double l_dbl = Double.parseDouble(l_str);
            long l_lng = (long) (l_dbl);
            
            if (l_dbl == l_lng)
            {
                return true;
            }
            else 
            {
                return false;
            }
        }
        catch (NumberFormatException e)
        {
            return false;
        } 
        
    }
    
    /**
     * 数値の長さを取得します。
     *
     * @@param str 一つの文字列
     * @@return 入力されたstrの数値長さを返す。
     */
    public static int getNubmerLength(String l_str) 
    {
        if (!isNumber(l_str)) 
        {
            return -1;
        }
        
        l_str = formatNumber(Double.parseDouble(l_str));
            
        return l_str.length();
    }
    
    /**
     * 整数の長さを取得します。
     *
     * @@param str 一つの文字列
     * @@return 入力されたstrの整数長さを返す。
     */
    public static int getIntegerDigits(String l_str) 
    {
        if (!isNumber(l_str)) 
        {
            return -1;
        }
        
        double l_dbl = Double.parseDouble(l_str);
        long l_lng = (long) (Math.abs(l_dbl));
        
        l_str = formatNumber(l_lng);
            
        return l_str.length();
    }
    
    /**
     * 小数部の長さを取得します。
     *
     * @@param str 一つの文字列
     * @@return 入力されたstrの小数部長さを返す。
     */
    public static int getFractionDigits(String l_str) 
    {
        if (!isNumber(l_str)) 
        {
            return -1;
        }
        
        double l_dbl = Double.parseDouble(l_str);        
        l_str = formatNumber(l_dbl);
        
        int l_intIndex = l_str.lastIndexOf(".");
        
        if (l_intIndex == -1) 
        {
            return 0;
        }
        
        String l_strFraction = l_str.substring(l_intIndex + 1);
            
        return l_strFraction.length();
    }
        
    /**
     * 文字列が数字のみで構成されているかをチェックする。<BR>
     * 数字のみの場合はtrueを、そうでない場合はfalseを返す。<BR>
     * @@param l_str 一つの文字列
     * @@return 数字のみの場合はtrueを、そうでない場合はfalseを返す。
     * @@roseuid 4088B65B0000
     */
    public static  boolean isDigit(String l_str) 
    {
        if (isEmpty(l_str)) 
        {
            return false;
        }
 
        for (int i = 0; i < l_str.length(); i++) 
        {
            char l_ch = l_str.charAt(i);
            if (!isSingleNum(l_ch)) 
            {
                return false;
            }
        }
        return true;
    }
    
    /**
     * 文字列が英字のみで構成されているかをチェックする。<BR>
     * 英字のみの場合はtrueを、そうでない場合はfalseを返す。<BR>
     * @@param l_str 一つの文字列
     * @@return 英字のみの場合はtrueを、そうでない場合はfalseを返す。
     * @@roseuid 4088B6610252
     */
    public static  boolean isLetter(String l_str) 
    {
        if (isEmpty(l_str)) 
        {
            return false;
        }
        
        for (int i = 0; i < l_str.length(); i++) 
        {
            char l_ch = l_str.charAt(i);
            if (!isSingleEng(l_ch)) 
            {
                return false;
            }
        }
        return true;
    
    }
    
    /**
     * 文字列が英時または数字で構成されているかをチェックする。<BR>
     * 英時または数字の場合はtrueを、そうでない場合はfalseを返す。<BR>
     * ※数字のみ、英字のみ、混在でtrue<BR>
     * @@param l_str 一つの文字列
     * @@return boolean
     * @@roseuid 4088B65F01C5
     */
    public static  boolean isLetterOrDigit(String l_str) 
    {
        if (isEmpty(l_str)) 
        {
            return false;
        }
        
        for (int i = 0; i < l_str.length(); i++) 
        {
            char l_ch = l_str.charAt(i);
            if (!isSingleEng(l_ch) && !isSingleNum(l_ch)) 
            {
                return false;
            }
        }
        return true;
    }
    
    /**
     * 文字列が英字と数字で構成されているかをチェックする。<BR>
     * 英字と数字の場合はtrueを、そうでない場合はfalseを返す。<BR>
     * ※英数字混在のみtrue、一方のみはfalse<BR>
     * @@param l_str 一つの文字列
     * @@return boolean
     * @@roseuid 4088B676031D
     */
    public static  boolean isLetterAndDigit(String l_str) 
    {
        if (isEmpty(l_str)) 
        {
            return false;
        }
        
        boolean l_blnHaveLetter = false;
        boolean l_blnHaveDigit = false;
        
        for (int i = 0; i < l_str.length(); i++) 
        {
            char l_ch = l_str.charAt(i);
            if (isSingleEng(l_ch)) 
            {
                l_blnHaveLetter = true;
            } 
            else if (isSingleNum(l_ch)) 
            {
                l_blnHaveDigit = true;
            }
            else
            {
                return false;
            }
        }
        
        return (l_blnHaveLetter && l_blnHaveDigit);
    }
    
    /**
     * 文字列のバイト長さを取得します。
     *
     * @@param str 一つの文字列
     * @@return 入力されたstrのバイト長さを返す。
     */
    public static int getByteLength(String str) 
    {
        try 
        {
            byte[] bytes = str.getBytes();
            return bytes.length;
        } 
        catch (Exception e) 
        {
            return -1;
        }
    }
    
    /**
     * 文字列が日付型されているかをチェックする。
     *
     * @@param l_str 文字列
     * @@param l_strPattern フォーマット
     * @@return boolean
     */
    public static boolean isDateStr(String l_str, String l_strPattern) {

        if (l_str == null || l_strPattern == null) 
        {
            return false;
        }

        SimpleDateFormat l_dateFormat = new SimpleDateFormat(l_strPattern);
        l_dateFormat.setLenient(false);

        try 
        {
            l_dateFormat.parse(l_str);
            return true;
        } 
        catch (ParseException ex) 
        {
            return false;
        }
    }
    
    /**
     * 数値をフォーマットします。
     *
     * @@param l_lngNum    数値
     * @@param l_intLength 桁数
     * @@return フォーマットした文字列を返す。
     */
    public static String formatNumber(long l_lngNum, int l_intLength) 
    {
 
        StringBuffer l_sb = new StringBuffer("" + l_lngNum);
 
        if (l_sb.length() >= l_intLength) 
        {
            return l_sb.toString();
        }
 
        int l_intSubLength = l_intLength - l_sb.length();
 
        for (int i = 0; i < l_intSubLength; i++) 
        {
            l_sb.insert(0, "0");
        }
 
        return l_sb.toString();
    }    

    /**
     * 数値をフォーマットします。
     *
     * @@param l_dbl    数値
     * @@return フォーマットした文字列を返す。
     */
    public static synchronized String formatNumber(double l_dbl)
    {
        if (Double.isNaN(l_dbl))
        {
            return null;
        }
        
        // -0.0 -> 0
        if (l_dbl == 0)
        {
            l_dbl = Math.abs(l_dbl);
        }

        decimalFormat.applyPattern(DEFAULT_DOUBLE_FORMAT_PATTERN);
        decimalFormat.setMaximumFractionDigits(300);
        decimalFormat.setMinimumIntegerDigits(1);
        
        return decimalFormat.format(l_dbl);       
    }

    /**
     * (isMailAddress)
     * <BR>
     * 文字列が、メールアドレスに適切な文字(*1)で構成されているかをチェックする。<BR>
     * 適切である場合はtrueを、そうでない場合はfalseを返す。<BR>
     *  <BR>
     * (*1) メールアドレスに適切な文字以下の半角文字が<BR>
     *   使用可能で構成され、’@@’が１つ含まれていること。<BR>
     *  <BR>
     * 0123456789abcdefghijklmnopqrstuvwxyz<BR>
     * ABCDEFGHIJKLMNOPQRSTUVWXYZ!#$&|^_.()-=~[ ]+/?* <BR>
     *  <BR>
     * また、’@@’は先頭文字，末尾文字ではないこと。<BR>
     *  <BR>
     * @@param l_str 一つの文字列
     * @@return boolean
     */   
    public static boolean isMailAddress(String l_str) 
    {
        final String MAIL_ADDRESS_CHAR = 
            "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!#$&|^_.()-=~[ ]+/?*@@";
        
        final char CHAR_A = '@@';
            
        if((l_str == null) || (l_str.length() < 1))
        {
            return false;
        }
        
        //’@@’は先頭文字，末尾文字ではないこと
        if((l_str.charAt(0) == CHAR_A) || (l_str.charAt(l_str.length() - 1) == CHAR_A))
        {
            return false;
        }
        
        //メールアドレスに適切な文字以下の半角文字が
        //使用可能で構成され、’@@’が１つ含まれていること
        int l_intSize = l_str.length();
        for(int i = 0 ; i < l_intSize ; i ++)
        {
            if(MAIL_ADDRESS_CHAR.indexOf(l_str.charAt(i)) == -1)
            {
                return false;
            }
        }
        
        int l_intFirstIndex = l_str.indexOf(CHAR_A);
        int l_intLastIndex = l_str.lastIndexOf(CHAR_A);
        if((l_intFirstIndex == -1) || (l_intLastIndex == -1) || (l_intFirstIndex != l_intLastIndex))
        {
            return false;
        }
        
        return true;
    }
    

    /**
     * 全角文字かどうか判断します。
     *
     * @@param l_ch 文字
     * @@return 入力されたcは全角文字なら、trueを返す。そうでないとfalseを返す。
     */
    public static boolean isMulti(char l_ch)
    {
        switch (l_ch) {
            case 0xff3c:return true;
            case 0xff5e:return true;
            case 0x2225:return true;
            case 0xff0d:return true;
            case 0xffe0:return true;
            case 0xffe1:return true;
            case 0xffe2:return true;
        }

        char l_chConv = WEB3StringTypeUtility.convert(l_ch);

        String l_str = new Character(l_chConv).toString();
        byte[] l_bytes = l_str.getBytes();
        if (l_bytes.length > 1)
        {
            return true;
        }
        return false;
    }
    
    /**
     * 文字列が全角文字のみで構成されているかをチェックする。<BR>
     * 全角文字のみの場合はtrueを、そうでない場合はfalseを返す。<BR>
     * @@param l_str 一つの文字列
     * @@return 全角文字のみの場合はtrueを、そうでない場合はfalseを返す。 
     */
    public static boolean isMulti(String l_str) 
    {
        if (isEmpty(l_str)) 
        {
            return false;
        }
 
        for (int i = 0; i < l_str.length(); i++) 
        {
            char l_ch = l_str.charAt(i);
            if (!isMulti(l_ch)) 
            {
                return false;
            }
        }
        return true;
    }
    
    /**
     * (isWbyteString)
     * 文字列が全角文字のみで構成されているかをチェックする。<BR>
     * 全角文字のみの場合はtrueを、そうでない場合はfalseを返す。<BR>
     * @@param l_str 一つの文字列
     * @@return 全角文字のみの場合はtrueを、そうでない場合はfalseを返す。 
     */
    public static boolean isWbyteString(String l_str) 
    {
        return isMulti(l_str);
    }
    
    /**
     * 全角カナ（片仮名）文字かどうか判断します。
     *
     * @@param l_ch 文字
     * @@return 入力されたcは全角カナ（片仮名）文字なら、trueを返す。そうでないとfalseを返す。
     */
    public static boolean isWbyteKanaChar(char l_ch) 
    {
        return (l_ch >= zenkakukatakanaStart && l_ch <= zenkakukatakanaEnd);
    }
    
    /**
     * (isWbyteKanaString)
     * 文字列が全角カナ（片仮名）文字のみで構成されているかをチェックする。<BR>
     * 全角カナ文字のみの場合はtrueを、そうでない場合はfalseを返す。<BR>
     * @@param l_str 一つの文字列
     * @@return boolean
     */
    public static boolean isWbyteKanaString(String l_str) 
    {
        if (isEmpty(l_str)) 
        {
            return false;
        }
 
        for (int i = 0; i < l_str.length(); i++) 
        {
            char l_ch = l_str.charAt(i);
            if (!isWbyteKanaChar(l_ch)) 
            {
                return false;
            }
        }
        return true;
        
    }  
    
    /**
     * 半角カナかどうか判断します。
     *
     * @@param l_ch 文字
     * @@return 入力されたcは半角カナなら、trueを返す。そうでないとfalseを返す。
     */
    public static boolean is1byteKanaChar(char l_ch) 
    {
        return (l_ch >= hankakuKatakanaStart && l_ch <= hankakuKatakanaEnd);
    }
    
    /**
     * (is1byteKanaString)
     * 文字列が半角カナ文字のみで構成されているかをチェックする。<BR>
     * 半角カナ文字のみの場合はtrueを、そうでない場合はfalseを返す。<BR>
     * @@param l_str 一つの文字列
     * @@return boolean
     */
    public static boolean is1byteKanaString(String l_str) 
    {
        if (isEmpty(l_str)) 
        {
            return false;
        }
 
        for (int i = 0; i < l_str.length(); i++) 
        {
            char l_ch = l_str.charAt(i);
            if (!is1byteKanaChar(l_ch)) 
            {
                return false;
            }
        }
        return true;
        
    } 
    
    /**
     * (has1byteKana)
     * 文字列に半角カナ文字が存在するかどうかをチェックする。<BR>
     * 半角カナ文字が存在しない場合はfalseを、<BR>
     * そうでない場合はtrueを返す。<BR>
     * @@param l_str 一つの文字列
     * @@return boolean
     */
    public static boolean has1byteKana(String l_str) 
    {
        if (isEmpty(l_str)) 
        {
            return false;
        }
        
        for (int i = 0; i < l_str.length(); i++) 
        {
            char l_ch = l_str.charAt(i);
            if (is1byteKanaChar(l_ch)) 
            {
                return true;
            }
        }
        
        return false;
        
    }
       
    
    /**
     * 半角文字かどうか判断します。
     *
     * @@param l_ch 文字
     * @@return 入力されたcは半角文字なら、trueを返す。そうでないとfalseを返す。
     */
    public static boolean isSingle(char l_ch)
    {
        String l_str = new Character(l_ch).toString();
        byte[] l_bytes = l_str.getBytes();
        if (l_bytes.length == 1)
        {
            return true;
        }
            
        return false;
    }
    
    /**
     * 文字列が半角文字のみで構成されているかをチェックする。<BR>
     * 半角文字のみの場合はtrueを、そうでない場合はfalseを返す。<BR>
     * @@param l_str 一つの文字列
     * @@return 半角文字のみの場合はtrueを、そうでない場合はfalseを返す。 
     */
    public static boolean isSingle(String l_str) 
    {
        if (isEmpty(l_str)) 
        {
            return false;
        }
 
        for (int i = 0; i < l_str.length(); i++) 
        {
            char l_ch = l_str.charAt(i);
            if (!isSingle(l_ch)) 
            {
                return false;
            }
        }
        return true;
    }

    /**
     * 半角数字かどうか判断します。
     *
     * @@param l_ch 文字
     * @@return 入力されたcは半角数字なら、trueを返す。そうでないとfalseを返す。
     */
    public static boolean isSingleNum(char l_ch) 
    {
        return (l_ch >= numberStart && l_ch <= numberEnd);
    }
    
    /**
     * 全角数字かどうか判断します。
     *
     * @@param l_ch 文字
     * @@return 入力されたcは全角数字なら、trueを返す。そうでないとfalseを返す。
     */
    public static boolean isWbyteNum(char l_ch) 
    {
        return (l_ch >= zenNumberStart && l_ch <= zenNumberEnd);
    }
    
    /**
     * 半角英字かどうか判断します。
     *
     * @@param l_ch 文字
     * @@return 入力されたcは半角英字なら、trueを返す。そうでないとfalseを返す。
     */
    public static boolean isSingleEng(char l_ch) 
    {
        if (l_ch >= upperEnglishStart && l_ch <= upperEnglishEnd) 
        {
            return true;
        } 
        else if (l_ch >= lowerEnglishStart && l_ch <= lowerEnglishEnd) 
        {
            return true;
        }
        return false;
    }
    
    /**
     * 全角英字かどうか判断します。
     *
     * @@param l_ch 文字
     * @@return 入力されたcは全角英字なら、trueを返す。そうでないとfalseを返す。
     */
    public static boolean isWbyteEng(char l_ch) 
    {
        if (l_ch >= zenUpperEnglishStart && l_ch <= zenUpperEnglishEnd) 
        {
            return true;
        } 
        else if (l_ch >= zenLowerEnglishStart && l_ch <= zenLowerEnglishEnd) 
        {
            return true;
        }
        return false;
    }
    
    /**
     * 文字列がnullまたは空文字かを判断します。
     *
     * @@param l_str 一つの文字列
     * @@return 入力されたstrはNullなら、trueを返す。そうでないとfalseを返す。
     */
    public static boolean isEmpty(String l_str) 
    {
        return (l_str == null || "".equals(l_str));
    }
    
    /**
     * 文字列がnull、空文字または空白かを判断します。
     *
     * @@param l_str 一つの文字列
     * @@return 入力されたstrはnull、空文字または空白なら、<BR>
     *    trueを返す。そうでないとfalseを返す。<BR>
     */
    public static boolean isEmptyOrBlank(String l_str) 
    {
        return (l_str == null || "".equals(l_str.trim()));
    }

    /**
     * 文字列がnullでなくかつ空文字でないかを判断します。
     *
     * @@param str 一つの文字列
     * @@return 入力されたstrはNullなら、falseを返す。そうでないとtrueを返す。
     */
    public static boolean isNotEmpty(String l_str)
    {
        return (l_str != null && !"".equals(l_str));
    }
    
    /**
     * (isPhoneNumber)<BR>
     *文字列が電話番号（または、携帯番号）として正しい値かを判定する。<BR>
     *以下の条件に当てはまる場合true、どれか一つ<BR>
     *でもあてはまらないはfalseを返却する。<BR>
     * <BR>
     * [trueの条件]<BR>
     * ○ '-'と数字以外の文字が含まれないこと。<BR>
     * ○ '-'が２つ含まれること。<BR>
     * ○ 先頭文字，末尾文字が'-'でないこと。<BR>
     * @@param l_str 一つの文字列<BR>
     * @@return boolean <BR>
     */
    public static boolean isPhoneNumber(String l_str)
    {
        if (isEmpty(l_str))
        {
            return false;
        }
        
        //○ '-'と数字以外の文字が含まれないこと。
        int l_intCnt = 0;
        int l_intLength = l_str.length();
        for (int i = 0; i < l_intLength; i++)
        {
            char l_ch = l_str.charAt(i);
            if ((isSingleNum(l_ch) == false) && ('-' != l_ch))
            {
                return false;
            }
            
            if ('-' == l_ch)
            {
                l_intCnt ++;
            }
            
        }   
        
        //○ '-'が２つ含まれること
        if (l_intCnt != 2)
        {
            return false;
        }
        
        //○ 先頭文字，末尾文字が'-'でないこと
        char l_chBegin = l_str.charAt(0);
        char l_chEnd = l_str.charAt(l_intLength - 1);
        if(l_chBegin == '-' || l_chEnd == '-')
        {
            return false;
        }
        
        return true;
        
    }

    /**
     * (isZipCode)
     *文字列が郵便番号として正しい値かを判定する。<BR>
     *以下の条件に当てはまる場合true、どれか一つでもあては<BR>
     *まらないはfalseを返却する。<BR>
     *<BR>
     *[trueの条件]<BR>
     *○ 半角数字以外の文字が含まれないこと。<BR>
     *○ 文字数が7byteであること。<BR>
     * @@param l_str 一つの文字列<BR>
     * @@return boolean <BR>
     */
    public static boolean isZipCode(String l_str)
    {
        if (isEmpty(l_str))
        {
            return false;
        }
        
        //○ 文字数が7byteであること。
        int l_intBytesLength = l_str.getBytes().length;
        if(l_intBytesLength != 7)
        {
            return false;
        }
        
        //○ 半角数字以外の文字が含まれないこと
        int l_intLength = l_str.length();
        for (int i = 0; i < l_intLength; i++)
        {
            char l_ch = l_str.charAt(i);
            if (isSingleNum(l_ch) == false)
            {
                return false;
            }
        }
        
        return true;
        
    }

    /**
     * (to1byteKana)<BR>
     * 全角カナ，全角英数字文字列を半角カナ，半角英数字文字列に変換する。<BR>
     * （【対象文字変換表】に該当する文字を対象とする）<BR>
     *  <BR>
     * 【対象文字変換表】<BR>
     *  <BR>
     * 種類  （全角）    （半角）<BR>
     * -----------------------------<BR>
     * <BR>
     * カナ   ア～ン       ｱ～ﾝ <BR>
     *         ガ～ゴ       ｶﾞ～ｺﾞ <BR>
     *         ザ～ゾ       ｻﾞ～ｿﾞ <BR>
     *         ダ～ド       ﾀﾞ～ﾄﾞ   <BR>
     *         バ～ボ      ﾊﾞ～ﾎﾞ<BR>
     *         ヴ            ｳﾞ   <BR>
     *         パ～ポ      ﾊﾟ～ﾎﾟ <BR>
     *         ァ@～ォ       ｱ～ｵ <BR>
     *         ヵ、ヶ        ｶ、ｹ <BR>
     *         ッ             ﾂ    <BR>
     *         ャ、ュ、ョ    ﾔ、ﾕ、ﾖ <BR>
     *         ヮ             ﾜ <BR>
     *         ー            -  <BR>
     * 英字  Ａ～Ｚ       A～Z <BR>
     *         ａ～ｚ        a～z <BR>
     * 数字  ０～９       0～9 <BR>
     * 記号  －           -  <BR>
     *          （            ( <BR>
     *          ）            ) <BR>
     * スペース"　@"      " " <BR>
     * <BR>
     * @@param l_strWbyteKana - 全角カナ文字<BR>
     * @@return String <BR>
     */
    public static String to1byteKana(String l_strWbyteKana) 
    {
        if (isEmpty(l_strWbyteKana))
        {
            return l_strWbyteKana;
        }
        
        String l_strReturn = "";
        int l_intLength = l_strWbyteKana.length();
        for(int i = 0; i < l_intLength; i++)
        {
            l_strReturn = l_strReturn + to1byteKana(l_strWbyteKana.charAt(i));
        }
   
        return l_strReturn;
    }
    
    /**
     * 全角カナ，全角英数字文字列を半角カナ，半角英数字文字列に変換する。<BR>
     * （【対象文字変換表】に該当する文字を対象とする）<BR>
     *  <BR>
     * 【対象文字変換表】<BR>
     *  <BR>
     * 種類  （全角）    （半角）<BR>
     * -----------------------------<BR>
     * <BR>
     * カナ   ア～ン       ｱ～ﾝ <BR>
     *         ガ～ゴ       ｶﾞ～ｺﾞ <BR>
     *         ザ～ゾ       ｻﾞ～ｿﾞ <BR>
     *         ダ～ド       ﾀﾞ～ﾄﾞ   <BR>
     *         バ～ボ      ﾊﾞ～ﾎﾞ <BR>
     *         ヴ            ｳﾞ   <BR>
     *         パ～ポ      ﾊﾟ～ﾎﾟ <BR>
     *         ァ@～ォ       ｱ～ｵ <BR>
     *         ヵ、ヶ        ｶ、ｹ <BR>
     *         ッ             ﾂ    <BR>
     *         ャ、ュ、ョ    ﾔ、ﾕ、ﾖ <BR>
     *         ヮ             ﾜ <BR>
     *         ー            -  <BR>
     * 英字  Ａ～Ｚ       A～Z <BR>
     *         ａ～ｚ        a～z <BR>
     * 数字  ０～９       0～9 <BR>
     * 記号  －           -  <BR>
     *          （            ( <BR>
     *          ）            ) <BR>
     * スペース"　@"      " " <BR>
     * <BR>
     * @@param l_chWbyteKana - 全角カナ文字<BR>
     * @@return String <BR>
     */
    private static String to1byteKana(char l_chWbyteKana)
    {
        char l_chWbyteKanaConv = WEB3StringTypeUtility.convert(l_chWbyteKana);

        int l_intLength = multi.length;
        int l_intToken = l_intLength;
        for(int i = 0; i < l_intLength; i++)
        {
            if(multi[i] == l_chWbyteKanaConv)
            {
                l_intToken = i;
                break;
            }
        }
        String l_strReturn;
        if(l_intToken == l_intLength)
        {
            l_strReturn = String.valueOf(l_chWbyteKanaConv);
        }
        else
        {
            char[] l_ch  = single[l_intToken];
            if(l_ch.length == 1)
            {
                l_strReturn = String.valueOf(l_ch[0]);
            }
            else
            {
                l_strReturn = String.valueOf(l_ch[0]) + String.valueOf(l_ch[1]);
            }
        }
        
        return l_strReturn;
    }
    
    /**
     * (toUpperWbyteKana)<BR>
     *  <BR>
     * 全角カナ（小文字⇒大文字）カナ変換を行う。<BR>
     *  <BR>
     * １）　@小文字カナ（ァ@，ィ，ゥ，等）が文字列に含まれれば、<BR>
     * 大文字カナに変換する。（小文字全角カナでない文字は変換しない）<BR>
     *  <BR>
     * 例）　@<BR>
     * ァ@　@→　@ア<BR>
     * ョ　@→　@ヨ<BR>
     * ゥ　@→　@ウ<BR>
     * <BR>
     * @@param l_strWbyteKana - 全角カナ文字<BR>
     * @@return String <BR>
     */
    public static String toUpperWbyteKana(String l_strWbyteKana) 
    {
        if (isEmpty(l_strWbyteKana))
        {
            return l_strWbyteKana;
        }
        
        String l_strReturn = "";
        int l_intLength = l_strWbyteKana.length();
        for(int i = 0; i < l_intLength; i++)
        {
            l_strReturn = l_strReturn + toUpperFullKana(l_strWbyteKana.charAt(i));
        }
   
        return l_strReturn;
        
    }
    
    /**
     * 全角カナ（小文字⇒大文字）カナ変換を行う。<BR>
     *  <BR>
     * １）　@小文字カナ（ァ@，ィ，ゥ，等）が文字列に含まれれば、<BR>
     * 大文字カナに変換する。（小文字全角カナでない文字は変換しない）<BR>
     *  <BR>
     * 例）　@<BR>
     * ァ@　@→　@ア<BR>
     * ョ　@→　@ヨ<BR>
     * ゥ　@→　@ウ<BR>
     * <BR>
     * @@param l_chLowerFullKana - 全角カナ文字<BR>
     * @@return char <BR>
     */
    private static char toUpperFullKana(char l_chLowerFullKana) 
    {
        int l_intLength = lowerFullKana.length;
        int l_intToken = l_intLength;
        for(int i = 0; i < l_intLength; i++)
        {
            if(lowerFullKana[i] == l_chLowerFullKana)
            {
                l_intToken = i;
                break;
            }
        }
        
        char l_chReturn;
        if(l_intToken == l_intLength)
        {
            l_chReturn = l_chLowerFullKana;
        }
        else
        {
            l_chReturn = upperFullKana[l_intToken];
        }
        
        return l_chReturn;
    }
    
    /**
     * 半角カナ（小文字⇒大文字）カナ変換を行う。<BR>
     *  <BR>
     * １）　@小文字カナ（ｧ，ｨ，ｩ，等）が文字列に含まれれば、<BR>
     * 大文字カナに変換する。（小文字半角カナでない文字は変換しない）<BR>
     *  <BR>
     * 例）　@<BR>
     * ｧ　@→　@ｱ<BR>
     * ｮ　@→　@ﾖ<BR>
     * ｩ　@→　@ｳ<BR>
     * <BR>
     * @@param l_chLowerHalfKana - 半角カナ文字<BR>
     * @@return char <BR>
     */
    private static char toUpperHalfKana(char l_chLowerHalfKana) 
    {
        int l_intLength = lowerHalfKana.length;
        int l_intToken = l_intLength;
        for(int i = 0; i < l_intLength; i++)
        {
            if(lowerHalfKana[i] == l_chLowerHalfKana)
            {
                l_intToken = i;
                break;
            }
        }
        
        char l_chReturn;
        if(l_intToken == l_intLength)
        {
            l_chReturn = l_chLowerHalfKana;
        }
        else
        {
            l_chReturn = upperHalfKana[l_intToken];
        }
        
        return l_chReturn;
    }
    
    /**
     * (toUpper1byteKana)
     * <BR>
     * カナ変換を行う。<BR>
     * <BR>
     * １）　@全角カナ文字列を半角カナ文字列に変換する。<BR>
     * （全角カナでない文字は変換しない）<BR>
     * @@see WEB3StringTypeUtility.to1byteKana()<BR>
     * <BR>
     * ２）　@小文字カナ（ｧ，ｨ，ｩ，等）が文字列に含まれれば、<BR>
     * 大文字カナに変換する。（小文字カナでない文字は変換しない）<BR>
     * <BR>
     * 例）　@<BR>
     * ｧ　@→　@ｱ<BR>
     * ｮ　@→　@ﾖ<BR>
     * ｩ　@→　@ｳ<BR>
     * <BR>
     * @@param l_strWbyteKana - 全角カナ文字<BR>
     * @@return String <BR>
     */
    public static String toUpper1byteKana(String l_strWbyteKana) 
    {
        if (isEmpty(l_strWbyteKana))
        {
            return l_strWbyteKana;
        }
        
        //１）　@全角カナ文字列を半角カナ文字列に変換する。
        String l_strChange = to1byteKana(l_strWbyteKana);
        
        // ２）　@小文字カナ（ｧ，ｨ，ｩ，等）が文字列に含まれれば、
        //大文字カナに変換する。
        String l_strReturn = "";
        int l_intLength = l_strChange.length();
        for(int i = 0; i < l_intLength; i++)
        {
            l_strReturn = l_strReturn + toUpperHalfKana(l_strChange.charAt(i));
        }
        
        return l_strReturn;
        
    }
    
    /**
     * (createHashValue)
     * <BR>
     * 渡された引数（Stringの配列）の全てを用いてハッシュ値を取得する。<BR>
     * <BR>
     * １）　@引数.計算対象の中身を順に連結し、それをbyteの配列に変換する。<BR>
     * <BR>
     * ２）　@MessageDigestオブジェクトを取得する。<BR>
     * <BR>
     *   [MessageDigest.getInstanceに渡す引数]<BR>
     *   algorithm＝引数.計算方式<BR>
     * <BR>
     * ３）　@１）で作成した配列を引数に、<BR>
     * MessageDigestオブジェクト.update()をコールする。<BR>
     * <BR>
     * ４）　@MessageDigestオブジェクト.digest()をコールする。<BR>
     * ５）　@digest()の戻り値を１要素ずつ引数にして、<BR>
     *  hexDigit()メソッドをコールする。<BR>
     * ６）　@全てのtoHexDigit()メソッドの戻り値を連結して返却する。<BR>
     * <BR>
     * @@param l_strAlgorithm - (計算方式)<BR>
     * @@param l_algorithmObj - (計算対象)<BR>
     * @@return String <BR>
     */
    public static String createHashValue(String l_strAlgorithm, String[] l_algorithmObj) 
    {
        //１）　@引数.計算対象の中身を順に連結し、それをbyteの配列に変換する
        int l_intLength = l_algorithmObj.length;
        String l_strAlgorithmObj = "";
        for(int i = 0; i < l_intLength; i++)
        {
            if (isNotEmpty(l_algorithmObj[i]))
            {
                l_strAlgorithmObj = l_strAlgorithmObj + l_algorithmObj[i];
            }
        }
        byte[] l_byteAlgorithmObj = l_strAlgorithmObj.getBytes();
        
        //２）　@MessageDigestオブジェクトを取得する。
        MessageDigest l_messageDigest = null;
        try
        {
            l_messageDigest = MessageDigest.getInstance(l_strAlgorithm);
        }
        catch(NoSuchAlgorithmException nsae)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                "WEB3StringTypeUtility.createHashValue(String, String[])",
                nsae.getMessage(),
                nsae);
        }
        
        //３）　@１）で作成した配列を引数に、MessageDigestオブジェクト.update()をコールする。
        l_messageDigest.update(l_byteAlgorithmObj);
        
        //４）　@MessageDigestオブジェクト.digest()をコールする
        StringBuffer l_sbResult = new StringBuffer();
        byte[] l_byteResult = l_messageDigest.digest();

        //５）　@digest()の戻り値を１要素ずつ引数にして、hexDigit()メソッドをコールする。
        //６）　@全てのtoHexDigit()メソッドの戻り値を連結して返却する。
        for (int i = 0; i < l_byteResult.length; i++)
        {
            l_sbResult.append(toHexDigit(l_byteResult[i]));
        }

        return l_sbResult.toString();
    }
    
    private static String toHexDigit(byte x)
    {
        StringBuffer sb = new StringBuffer();
        char c;
        c = (char) ((x >> 4) & 0xf);
        if (c > 9)
        {
            c = (char) ((c - 10) + 'a');
        }
        else
        {
            c = (char) (c + '0');
        }
        sb.append(c);
        c = (char) (x & 0xf);
        if (c > 9)
        {
            c = (char) ((c - 10) + 'a');
        }
        else
        {
            c = (char) (c + '0');
        }
        sb.append(c);
        return sb.toString();
    }
    
    public static boolean isWbyteCrString(String l_str)
    {
        if (isEmpty(l_str))
        {

            return false;
        }

        for (int i = 0; i < l_str.length(); i++)
        {
            char l_ch = l_str.charAt(i);
            if (!isMulti(l_ch))
            {
                if ((l_ch != '\n') && (l_ch != '\r'))
                {
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
     * JIS -> Unicose String 変換処理<br />
     * <br />
     * ~  0x007E       0x007E[TILDE]<br />
     * \  0x005C       0x005C[REVERSE SOLIDUS]<br />
     * ＼ 0x815F       0xFF3C[FULLWIDTH REVERSE SOLIDUS]<br />
     * ～ 0x8160       0x301C[WAVE DASH]<br />
     * ∥ 0x8161       0x2016[DOUBLE VERTICAL LINE]<br />
     * － 0x817C       0x2212[MINUS SIGN]<br />
     * ￠ 0x8191       0x00A2[CENT SIGN]<br />
     * ￡ 0x8192       0x00A3[POUND SIGN]<br />
     * ￢ 0x81CA       0x00AC[NOT SIGN]<br />
     * <br />
     * @@param c 変換前文字
     * @@return 変換後文字
     */
    public static char convert(char c)
    {
        char res = c;
        switch (c) {
        case 0x005c:    // REVERSE SOLIDUS ->
        res = 0xff3c; // FULLWIDTH REVERSE SOLIDUS
        break;
        case 0x301c:    // WAVE DASH ->
        res = 0xff5e; // FULLWIDTH TILDE
        break;
        case 0x2016:    // DOUBLE VERTICAL LINE ->
        res = 0x2225; // PARALLEL TO
        break;
        case 0x2212:    // MINUS SIGN ->
        res = 0xff0d; // FULLWIDTH HYPHEN-MINUS
        break;
        case 0x00a2:    // CENT SIGN ->
        res = 0xffe0; // FULLWIDTH CENT SIGN
        break;
        case 0x00a3:    // POUND SIGN ->
        res = 0xffe1; // FULLWIDTH POUND SIGN
        break;
        case 0x00ac:    // NOT SIGN ->
        res = 0xffe2; // FULLWIDTH NOT SIGN
        }
        return res;
    }

    /**
     * JIS -> Unicose String 変換処理<br />
     * <br />
     * ~  0x007E       0x007E[TILDE]<br />
     * \  0x005C       0x005C[REVERSE SOLIDUS]<br />
     * ＼ 0x815F       0xFF3C[FULLWIDTH REVERSE SOLIDUS]<br />
     * ～ 0x8160       0x301C[WAVE DASH]<br />
     * ∥ 0x8161       0x2016[DOUBLE VERTICAL LINE]<br />
     * － 0x817C       0x2212[MINUS SIGN]<br />
     * ￠ 0x8191       0x00A2[CENT SIGN]<br />
     * ￡ 0x8192       0x00A3[POUND SIGN]<br />
     * ￢ 0x81CA       0x00AC[NOT SIGN]<br />
     * <br />
     * @@param s 変換前文字列
     * @@return 変換後文字列
     */
    public static String convert(String s)
    {
        if (s == null)
        {
            return null;
        }
        
        StringBuffer sb = new StringBuffer();
        char c;
        for (int i = 0; i < s.length(); i++) {
            char res  = WEB3StringTypeUtility.convert(s.charAt(i));
            sb.append(res);
        }
        return new String(sb);
    }

    /**
     * Stringタイプの数値がプラスかマイナスかを判断<BR>
     * <BR>
     * @@param String 数値
     * @@return boolean true マイナス
     */
    public static boolean isMinus(String l_strDoubleValue)
    {
        double l_dblValue = 0.0;
        boolean l_isMinus = false;
        
        Double l_value = new Double(l_strDoubleValue);
        l_dblValue = l_value.doubleValue();
        
        if(l_dblValue < 0)
        {
            l_isMinus = true;
        }
        
        return l_isMinus;  
    }

    /**
     * (toWbyteKana)<BR>
     * 半角カナ，半角英数字文字列を全角カナ，全角英数字文字列に変換する。<BR> 
     * （【対象文字変換表】に該当する文字を対象とする）<BR>
     * <BR>
     * 【対象文字変換表】<BR>
     * <BR>
     * 種類 （全角） （半角）<BR>
     * -----------------------------<BR>
     * カナ ア～ン ｱ～ﾝ <BR>
     * ガ～ゴ ｶﾞ～ｺﾞ <BR>
     * ザ～ゾ ｻﾞ～ｿﾞ <BR>
     * ダ～ド ﾀﾞ～ﾄﾞ <BR>
     * バ～ボ ﾊﾞ～ﾎﾞ <BR>
     * ヴ ｳﾞ <BR>
     * パ～ポ ﾊﾟ～ﾎﾟ <BR>
     * ァ@～ォ ｱ～ｵ <BR>
     * ヵ、ヶ ｶ、ｹ <BR>
     * ッ ﾂ <BR>
     * ャ、ュ、ョ ﾔ、ﾕ、ﾖ <BR>
     * ヮ ﾜ <BR>
     * ー - <BR>
     * 英字 A～Z A～Z <BR>
     * ａ～ｚ a～z <BR>
     * 数字 ０～９ 0～9 <BR>
     * 記号 － - <BR>
     * （ ( <BR>
     * ） ) <BR>
     * スペース "　@"   " " <BR>
     * <BR>
     * @@param l_strSingleByte - 半角カナ文字<BR>
     * @@return String <BR>
     */
    public static String toWbyteKana(String l_strSingleByte) 
    {
        if (isEmpty(l_strSingleByte))
        {
            return l_strSingleByte;
        }

        //戻り値
        String l_strMultiReturn = "";
        int l_intLength = l_strSingleByte.length();
        for(int i = 0; i < l_intLength; i++)
        {
        	boolean l_blnFlag = true;
        	boolean l_blnMaruFlag = true;
        	int l_intIndex = i + 1;

        	if(l_intIndex < l_intLength)
        	{
            	//ｶﾞ～ｺﾞ、ｻﾞ～ｿﾞ、ﾀﾞ～ﾄﾞ、ﾊﾞ～ﾎﾞ、ｳﾞの場合
        		if(l_strSingleByte.charAt(l_intIndex) == 'ﾞ')
        		{
            		l_blnFlag = false;
        		}

        		//ﾊﾟ～ﾎﾟの場合
        		if(l_strSingleByte.charAt(l_intIndex) == 'ﾟ')
        		{
        			l_blnMaruFlag = false;
        		}
        	}

        	//戻り値 = 戻り値 + 半角を全角に変換後の文字列
        	l_strMultiReturn = l_strMultiReturn + toWbyteKana(l_strSingleByte.charAt(i), l_blnFlag, l_blnMaruFlag);

        	//'ﾟ'または'ﾞ'を含み場合、スギップです。
        	if(!l_blnFlag || !l_blnMaruFlag)
        	{
        		i++;
        	}
        }

        return l_strMultiReturn;
    }

    /**
     * 半角カナ，半角英数字文字列を全角カナ，全角英数字文字列に変換する。<BR> 
     * （【対象文字変換表】に該当する文字を対象とする）<BR>
     * <BR>
     * 【対象文字変換表】<BR>
     * <BR>
     * 種類 （全角） （半角）<BR>
     * -----------------------------<BR>
     * カナ ア～ン ｱ～ﾝ <BR>
     * ガ～ゴ ｶﾞ～ｺﾞ <BR>
     * ザ～ゾ ｻﾞ～ｿﾞ <BR>
     * ダ～ド ﾀﾞ～ﾄﾞ <BR>
     * バ～ボ ﾊﾞ～ﾎﾞ <BR>
     * ヴ ｳﾞ <BR>
     * パ～ポ ﾊﾟ～ﾎﾟ <BR>
     * ァ@～ォ ｱ～ｵ <BR>
     * ヵ、ヶ ｶ、ｹ <BR>
     * ッ ﾂ <BR>
     * ャ、ュ、ョ ﾔ、ﾕ、ﾖ <BR>
     * ヮ ﾜ <BR>
     * ー - <BR>
     * 英字 A～Z A～Z <BR>
     * ａ～ｚ a～z <BR>
     * 数字 ０～９ 0～9 <BR>
     * 記号 － - <BR>
     * （ ( <BR>
     * ） ) <BR>
     * スペース "　@"   " " <BR>
     * <BR>
     * @@param l_chSingleByte - 半角カナ文字<BR>
     * @@param l_blnFlag - ﾞフラグ<BR>
     * @@param l_blnMaruFlag - ﾟフラグ<BR>
     * @@return String <BR>
     */
    private static String toWbyteKana(char l_chSingleByte, boolean l_blnFlag, boolean l_blnMaruFlag)
    {
        char l_chSingleByteConv = WEB3StringTypeUtility.convert(l_chSingleByte);

        int l_intLength = single.length;
        int l_intToken = l_intLength;
        for(int i = 0; i < l_intLength; i++)
        {
        	//半角の場合：
            if(l_blnFlag && l_blnMaruFlag && single[i][0] == l_chSingleByteConv)
            {
                l_intToken = i;
                break;
            }

            //半角 + 半角の場合
            //ｶﾞ～ｺﾞ、ｻﾞ～ｿﾞ、ﾀﾞ～ﾄﾞ、ﾊﾞ～ﾎﾞ、ｳﾞの場合
            if (!l_blnFlag && single[i].length == 2
            	&& single[i][0] == l_chSingleByteConv
            	&& single[i][1] == 'ﾞ')
            {
                l_intToken = i;
                break;
            }

            //ﾊﾟ～ﾎﾟの場合
            if (!l_blnMaruFlag && single[i].length == 2
            	&& single[i][0] == l_chSingleByteConv
            	&& single[i][1] == 'ﾟ')
            {
                l_intToken = i;
                break;
            }
        }

        //戻り値
        String l_strMultiReturn;
        //全角の場合、全角を戻る。
        if(l_intToken == l_intLength)
        {
        	l_strMultiReturn = String.valueOf(l_chSingleByteConv);
        }
        //半角の場合、全角に変換する。
        else
        {
        	l_strMultiReturn = String.valueOf(multi[l_intToken]);
        }

        return l_strMultiReturn;
    }

    /**
     * 文字列のバイト数を取得する。<BR>
     * isMultiメソッドを使用して1文字ずつ全角か半角かを判断し、<BR>
     * 算出したバイト数を返却する。<BR>
     * <BR>
     * @@param l_str - (文字列)
     * @@return int
     */
    public static int getFixedByteLength(String l_str)
    {
        if (isEmpty(l_str)) 
        {
            return 0;
        }

        int l_intLenght = 0; 
        for (int i = 0; i < l_str.length(); i++)
        {
            //isMultiメソッドを使用して1文字ずつ全角か半角かを判断し
            char l_ch = l_str.charAt(i);
            if (isMulti(l_ch)) 
            {
                l_intLenght = l_intLenght + 2;
            }
            else
            {
                l_intLenght = l_intLenght + 1;
            }
        }

        //算出したバイト数を返却する。
        return l_intLenght;
    }

    /**
     * 文字列が半角数字と半角ピリオド"."で構成されているかをチェックする。<BR>
     * 該当する場合はtrueを、そうでない場合はfalseを返す。<BR>
     * <BR>
     * @@param l_str - (文字列)
     * @@return boolean
     */
    public static boolean isIpAddress(String l_str)
    {
        if (isEmpty(l_str))
        {
            return false;
        }

        //文字列が半角数字と半角ピリオド"."で構成されているかをチェックする。
        int l_intLength = l_str.length();
        for (int i = 0; i < l_intLength; i++)
        {
            char l_ch = l_str.charAt(i);
            if (!isSingleNum(l_ch) && '.' != l_ch)
            {
                //そうでない場合はfalseを返す。
                return false;
            }
        }

        //該当する場合はtrueを返す。
        return true;
    }
}@
