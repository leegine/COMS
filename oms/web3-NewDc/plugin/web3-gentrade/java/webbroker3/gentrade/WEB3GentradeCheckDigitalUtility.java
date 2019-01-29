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
filename	WEB3GentradeCheckDigitalUtility.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : チェックデジットユーティリティ(WEB3GentradeCheckDigitalUtility.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/22 栄イ (中訊) 新規作成
*/
package webbroker3.gentrade;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (チェックデジットユーティリティ)<BR>
 * チェックデジットユーティリティ<BR>
 * @@author 栄イ (中訊)
 * @@version 1.0
 */
public class WEB3GentradeCheckDigitalUtility
{
    /** 
     * ログユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GentradeCheckDigitalUtility.class);
    
    private final static int LENGTH  = 6; //口座コード6桁
    private final static int START  = 7;
    private final static int DIV  = 11;
    private final static int REST  = 10;
    
    /**
     * (getチェックデジット )<BR>
     * <BR>
     * チェックデジットを付加する。 <BR>
     * <BR>
     * １） 引数.口座コード6桁を以下のように定義する。 <BR>
     * <BR>
     * 　@ＡＢＣＤＥＦ <BR>
     * <BR>
     * ２） 以下の演算処理を行い、結果①@を求める。 <BR>
     * 　@ <BR>
     * 　@（Ａ * 7） + （B * 6） + （C * 5） + （D * 4） + （E * 3） + （F * 2） = ①@ <BR>
     * <BR>
     * ３） ２）の処理結果①@に対して、以下の処理を行い、結果②を求める。 <BR>
     * <BR>
     * 　@①@ を 11 で割った余り = ② <BR>
     * <BR>
     * ４） ３）の処理結果②の値により、以下のチェックデジットを設定する。 <BR>
     * 　@ <BR>
     * 　@②の値　@　@→　@　@チェックデジット <BR>
     * 　@　@  <BR>
     *          0        →　@　@　@　@　@　@　@1 <BR>
     *     　@ 1        →　@　@　@　@　@　@　@0 <BR>
     *     　@ 2        →　@　@　@　@　@　@　@9 <BR>
     *    　@  3        →　@　@　@　@　@　@　@8 <BR>
     *   　@   4        →　@　@　@　@　@　@　@7 <BR>
     *   　@   5        →　@　@　@　@　@　@　@6 <BR>
     *   　@   6        →　@　@　@　@　@　@　@5 <BR>
     *   　@   7        →　@　@　@　@　@　@　@4 <BR>
     *   　@   8        →　@　@　@　@　@　@　@3 <BR>
     *   　@   9        →　@　@　@　@　@　@　@2 <BR>
     *   　@   10       →　@　@　@　@　@　@1 <BR>
     *       <BR>
     * ５） ４）で設定した値を、チェックデジットとして戻り値で返す。<BR>
     * <BR>
     * @@param l_strAccountCode  口座コード<BR>
     * @@throws WEB3BaseException<BR>
     * @@return String 
     */
    public static String getCheckDigital(String l_strAccountCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getCheckDigital(String)";
        log.entering(STR_METHOD_NAME);
        
        //１） 引数.口座コード6桁を以下のように定義する。
        //　@ＡＢＣＤＥＦ
        if(l_strAccountCode.length() != LENGTH)
        {
            log.error("（口座コードのサイズが不正です。");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                WEB3GentradeCheckDigitalUtility.class.getName() + "." + STR_METHOD_NAME,
                "口座コードのサイズが不正です。");
        }
        if(!WEB3StringTypeUtility.isDigit(l_strAccountCode))
        {
            log.error("（口座コードが数値以外の値です。");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                WEB3GentradeCheckDigitalUtility.class.getName() + "." + STR_METHOD_NAME,
                "口座コードが数値以外の値です。");
        }
        
        //２） 以下の演算処理を行い、結果①@を求める。
        //　@（Ａ * 7） + （B * 6） + （C * 5） + （D * 4） + （E * 3） + （F * 2） = ①@
        int l_intResult = 0;
        for(int i = 0, j = START; i < LENGTH; i++, j--)
        {
            l_intResult += 
                (Integer.valueOf(l_strAccountCode.substring(i, i + 1))).intValue() * j;
        }
        
        //３） ２）の処理結果①@に対して、以下の処理を行い、結果②を求める。
        //　@①@ を 11 で割った余り = ②
        l_intResult %= DIV;
        
        //４） ３）の処理結果②の値により、以下のチェックデジットを設定する。
        //　@②の値　@　@→　@　@チェックデジット
        l_intResult = (DIV - l_intResult) % REST;
        
        //５） ４）で設定した値を、チェックデジットとして戻り値で返す。
        String l_strResult = String.valueOf(l_intResult);
        log.exiting(STR_METHOD_NAME);
        return l_strResult;
    }
}
@
