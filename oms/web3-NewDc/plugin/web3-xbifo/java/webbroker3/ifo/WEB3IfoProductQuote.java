head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.44.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoProductQuote.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP時価情報(WEB3IfoCurrentInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/29 呉艶飛 新規作成         
*/
package webbroker3.ifo;

import java.sql.Timestamp;


/**
 * (先物OP時価情報)<BR>
 * 先物OP銘柄時価情報を保持するデータクラス。 <BR>
 * 時価情報取得メソッドの戻り値を保持する。<BR>
 * @@author 呉艶飛
 * @@version 1.0
 */
public class WEB3IfoProductQuote 
{
   
    /**
     * 時価。
     */
    private double currentPrice = 0;
    
    /**
     * 前日比。
     */
    private double comparedPreviousDay = 0;
    
    /**
     * 時価発表時間。
     */
    private Timestamp currentPriceTime;
    
    /**
     * 時価取得区分。<BR>
     * （1：時価　@2：前日終値）<BR>
     */
    private String currentPriceDiv;
    
    /**
     * 市場コード。
     */
    private String marketCode;
    
    /**
     * コンストラクタ
     * @@roseuid 41AC18FC02AF
     */
    public WEB3IfoProductQuote() 
    {
     
    }
    
    /**
     * (set時価)<BR>
     * 時価をセットする。<BR>
     * @@param l_dblCurrentPrice - 時価。
     * @@roseuid 41AC14EE038A
     */
    public void setCurrentPrice(double l_dblCurrentPrice) 
    {
         this.currentPrice = l_dblCurrentPrice;
    }
    
    /**
     * (get時価)<BR>
     * 時価を取得する。<BR>
     * @@return double
     * @@roseuid 41AC14EE038C
     */
    public double getCurrentPrice() 
    {
         return currentPrice;
    }
    
    /**
     * (set前日比)<BR>
     * 前日比をセットする。<BR>
     * @@param l_dblComparedPreviousDay - 前日比。
     * @@roseuid 41B549FC00C8
     */
    public void setComparedPreviousDay(double l_dblComparedPreviousDay) 
    {
         this.comparedPreviousDay = l_dblComparedPreviousDay;
    }
    
    /**
     * (get前日比)<BR>
     * 前日比を取得する。<BR>
     * @@return double
     * @@roseuid 41B549FC00CA
     */
    public double getComparedPreviousDay() 
    {
        return comparedPreviousDay;
    }
    
    /**
     * (set時価発表時間)<BR>
     * 時価発表時間をセットする。<BR>
     * @@param l_tsCurrentPriceTime - 時価発表時間。
     * @@roseuid 41AC14EE0399
     */
    public void setCurrentPriceTime(Timestamp l_tsCurrentPriceTime) 
    {
         this.currentPriceTime = l_tsCurrentPriceTime;
    }
    
    /**
     * (get時価発表時間)<BR>
     * 時価発表時間を取得する。<BR>
     * @@return Timestamp
     * @@roseuid 41AC14EE039B
     */
    public Timestamp getCurrentPriceTime() 
    {
        return currentPriceTime;
    }
    
    /**
     * (set時価取得区分)<BR>
     * 時価取得区分をセットする。<BR>
     * @@param l_strCurrentPriceDiv - 時価取得区分。
     * @@roseuid 41AC14EE039C
     */
    public void setCurrentPriceDiv(String l_strCurrentPriceDiv) 
    {
         this.currentPriceDiv = l_strCurrentPriceDiv;
    }
    
    /**
     * (get時価取得区分)<BR>
     * 時価取得区分を取得する。<BR>
     * @@return String
     * @@roseuid 41AC14EE039E
     */
    public String getCurrentPriceDiv() 
    {
         return currentPriceDiv;
    }
    
    /**
     * (set市場コード)<BR>
     * 市場コードをセットする。<BR>
     * @@param l_strMarketCode - 市場コード。
     * @@roseuid 41AC14EE039F
     */
    public void setMarketCode(String l_strMarketCode) 
    {
         this.marketCode = l_strMarketCode;
    }
    
    /**
     * (get市場コード)<BR>
     * 市場コードを取得する。<BR>
     * @@return String
     * @@roseuid 41AC14EE03A1
     */
    public String getMarketCode() 
    {
        return marketCode;
    }
}
@
