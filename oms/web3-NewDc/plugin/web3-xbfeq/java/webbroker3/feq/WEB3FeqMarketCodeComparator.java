head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqMarketCodeComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 市場コードComparator(WEB3FeqMarketCodeComparator.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/21 郭英 (中訊) 新規作成
                   2005/08/03 黄建(中訊) レビュー     
*/

package webbroker3.feq;

import java.util.Comparator;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3LogUtility;


/**
 * (市場コードComparator)<BR>
 * 市場コードComparatorクラス<BR>
 *   
 * @@author 郭英
 * @@version 1.0
 */
public class WEB3FeqMarketCodeComparator implements Comparator 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqMarketCodeComparator.class);
    
    /**
     * 昇順（：asc）、降順（：desc）を指定するフラグ。<BR>
     * <BR>
     *   A：昇順<BR>
     *   D：降順<BR>
     */
    private String orderBy;
    
    /**
     * (カラム番号)<BR>
     * 発注日項目のカラム番号<BR>
     */
    private int columnNumber;
    
    /**
     * @@roseuid 42D0D2EC0290
     */
    public WEB3FeqMarketCodeComparator() 
    {
     
    }
    
    /**
     * (市場コードComparator)<BR>
     * コンストラクタ<BR>
     * <BR>
     * 引数の値を各パラメータにセットする。<BR>
     * @@param l_strOrderBy - 昇順/降順を示すフラグ
     * 
     * @@param l_intColumnNumber - (カラム番号)<BR>
     * カラム番号<BR>
     * @@roseuid 42AFDD59030C
     */
    public WEB3FeqMarketCodeComparator(String l_strOrderBy, int l_intColumnNumber) 
    {
        final String STR_METHOD_NAME =  " WEB3FeqMarketCodeComparator(String, int)";
        log.entering(STR_METHOD_NAME);
        
        if(!WEB3AscDescDef.ASC.equals(l_strOrderBy) && !WEB3AscDescDef.DESC.equals(l_strOrderBy))
        {
            
            throw new IllegalArgumentException("パラメータの値が'A：昇順'、'D：降順'以外です");
            
        }
        this.orderBy = l_strOrderBy;
        this.columnNumber = l_intColumnNumber;
        
        log.exiting(STR_METHOD_NAME); 
    }
    
    /**
     * （compareの実装）<BR>
     * <BR>
     * 市場コードの比較を行う。<BR>
     * <BR>
     * １）引数のcast<BR>
     * 　@引数の明細行１、明細行２をStringにcastする。<BR>
     * <BR>
     * ２）比較<BR>
     * <BR>
     * 　@[昇順指定の場合（this.orderBy == ”昇順”）]<BR>
     * 　@・（明細行１.split(",")[this.カラム番号] < 明細行２.split(",")<BR>
     * [this.カラム番号]）の場合、負の整数（-1）を返却する。<BR>
     * 　@・（明細行１.split(",")[this.カラム番号] = 明細行２.split(",")<BR>
     * [this.カラム番号]）の場合、0を返却する。<BR>
     * 　@・（明細行１.split(",")[this.カラム番号] > 明細行２.split(",")<BR>
     * [this.カラム番号]）の場合、正の整数（1）を返却する。<BR>
     * <BR>
     * 　@[降順指定の場合（this.orderBy == ”降順”）]<BR>
     * 　@・（明細行１.split(",")[this.カラム番号] < 明細行２.split(",")<BR>
     * [this.カラム番号]）の場合、正の整数（1）を返却する。<BR>
     * 　@・（明細行１.split(",")[this.カラム番号] = 明細行２.split(",")<BR>
     * [this.カラム番号]）の場合、0を返却する。<BR>
     * 　@・（明細行１.split(",")[this.カラム番号] > 明細行２.split(",")<BR>
     * [this.カラム番号]）の場合、負の整数（-1）を返却する。<BR>
     * @@param l_objDetailLine1 - (明細行１)<BR>
     * 明細行オブジェクト１<BR>
     * 
     * @@param l_objDetailLine2 - (明細行２)<BR>
     * 明細行オブジェクト２<BR>
     * 
     * @@return int
     * @@roseuid 42AFE46101F3
     */
    public int compare(Object l_objDetailLine1, Object l_objDetailLine2) 
    {
        final String STR_METHOD_NAME =  " compare(Object, Object)";
        log.entering(STR_METHOD_NAME);
        
        //１）引数のcast
        //引数の明細行１、明細行２をStringにcastする。
        if ( ! (l_objDetailLine1 instanceof String) || 
            !(l_objDetailLine2 instanceof String))
        {
            log.debug("パラメータの類型が不正、該当する'String' 類型。");

            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018, 
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータの類型が不正、該当する'String' 類型。");
        }
        
        //２）比較
        String l_strDetailLine1 = (String)l_objDetailLine1;
        String l_strDetailLine2 = (String)l_objDetailLine2;
       
        //[昇順指定の場合（this.orderBy == ”昇順”）]
        if (WEB3AscDescDef.ASC.equals(this.orderBy))
        {        
            //・（明細行１.split(",")[this.カラム番号] < 明細行２.split(",")
            //[this.カラム番号]）の場合、負の整数（-1）を返却する。
            //・（明細行１.split(",")[this.カラム番号] = 明細行２.split(",")
            //[this.カラム番号]）の場合、0を返却する。 
            //・（明細行１.split(",")[this.カラム番号] > 明細行２.split(",")
            //[this.カラム番号]）の場合、正の整数（1）を返却する。             
        
            String[] l_strDetailLine1s = l_strDetailLine1.split(",");
            String[] l_strDetailLine2s = l_strDetailLine2.split(",");
            
            int l_intCnt1 = l_strDetailLine1s.length;
            int l_intCnt2 = l_strDetailLine2s.length;
            
            if (l_intCnt1 - 1 < this.columnNumber || l_intCnt2 - 1 < this.columnNumber)
            {
                log.exiting(STR_METHOD_NAME); 
                return 0;
            }
            
            String l_str1 = l_strDetailLine1s[this.columnNumber];
            String l_str2 = l_strDetailLine2s[this.columnNumber];                      
        
            int l_intFlag = l_str1.compareTo(l_str2);
            if (l_intFlag < 0)
            {
                log.exiting(STR_METHOD_NAME); 
                return -1;
            }
            else if (l_intFlag == 0)
            {
                log.exiting(STR_METHOD_NAME); 
                return 0;
            }
            else
            {
                log.exiting(STR_METHOD_NAME); 
                return 1;
            }
        }
        else if (WEB3AscDescDef.DESC.equals(this.orderBy))
        {        
            //・（明細行１.split(",")[this.カラム番号] < 明細行２.split(",")
            //[this.カラム番号]）の場合、正の整数（1）を返却する。
            //・（明細行１.split(",")[this.カラム番号] = 明細行２.split(",")
            //[this.カラム番号]）の場合、0を返却する。 
            //・（明細行１.split(",")[this.カラム番号] > 明細行２.split(",")
            //[this.カラム番号]）の場合、負の整数（-1）を返却する。             
        
            String[] l_strDetailLine1s = l_strDetailLine1.split(",");
            String[] l_strDetailLine2s = l_strDetailLine2.split(",");
            
            int l_intCnt1 = l_strDetailLine1s.length;
            int l_intCnt2 = l_strDetailLine2s.length;
            
            if (l_intCnt1 - 1 < this.columnNumber || l_intCnt2 - 1 < this.columnNumber)
            {
                log.exiting(STR_METHOD_NAME); 
                return 0;
            }
            
            String l_str1 = l_strDetailLine1s[this.columnNumber];
            String l_str2 = l_strDetailLine2s[this.columnNumber];          
        
            int l_intFlag = l_str1.compareTo(l_str2);
            if (l_intFlag < 0)
            {
                log.exiting(STR_METHOD_NAME); 
                return 1;
            }
            else if (l_intFlag == 0)
            {
                log.exiting(STR_METHOD_NAME); 
                return 0;
            }
            else
            {
                log.exiting(STR_METHOD_NAME); 
                return -1;
            }
        }
        else 
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002, 
                this.getClass().getName() + STR_METHOD_NAME,
                "昇順、降順 undefined.");
        }                
    }
    
    /**
     * （equalsの実装）<BR>
     * <BR>
     * 未使用。<BR>
     * falseを返却する。<BR>
     * @@param arg0
     * @@return boolean
     * @@roseuid 42AFDD59031C
     */
    public boolean equals(java.lang.Object arg0) 
    {
        return false;
    }
}
@
