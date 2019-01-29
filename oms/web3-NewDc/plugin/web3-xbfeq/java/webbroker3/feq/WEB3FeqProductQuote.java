head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqProductQuote.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式時価情報(WEB3FeqProductQuote)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/13  艾興(中訊) 新規作成
                   2005/07/27 魏馨(中訊) レビュー
*/
package webbroker3.feq;

import java.sql.Timestamp;
import java.util.Date;


/**
 * (外国株式時価情報) <BR>
 * 外国株式時価情報
 * 
 * @@author 艾興
 * @@version 1.0
 */
public class WEB3FeqProductQuote 
{
    
    /**
     * (時価) <BR>
     * 時価。
     */
    private double currentPrice = 0;
    
    /**
     * (前日比) <BR>
     * 前日比。
     */
    private double comparedPreviousDay = 0;
    
    /**
     * (時価発表時間) <BR>
     * 時価発表時間。
     */
    private Date currentPricePublicTime = null;
    
    /**
     * (時価取得区分) <BR>
     * 時価取得区分。 <BR>
     *  <BR>
     * 1：時価 <BR>
     * 2：前日終値 <BR>
     * 3：当日終値 <BR>
     */
    private String currentPriceGetDiv;
    
    /**
     * (時価区分) <BR>
     * 時価区分。 <BR>
     *  <BR>
     * 1：現在値 <BR>
     * 2：売気配値 <BR>
     * 3：買気配値 <BR>
     * 4：前日終値 <BR>
     */
    private String currentPriceDiv;
    
    /**
     * (市場コード) <BR>
     * 市場コード。
     */
    private String marketCode;
    
    /**
     * @@roseuid 42CE39E9007D
     */
    public WEB3FeqProductQuote() 
    {
     
    }
    
    /**
     * (set時価) <BR>
     * 時価をセットする。
     * @@param l_dblCurrentPrice - (時価) <BR>
     * 時価。
     * @@roseuid 4282C7840235
     */
    public void setCurrentPrice(double l_dblCurrentPrice) 
    {
        this.currentPrice = l_dblCurrentPrice;
    }
    
    /**
     * (get時価) <BR>
     * 時価を取得する。
     * @@return double
     * @@roseuid 4282C7840254
     */
    public double getCurrentPrice() 
    {
        return this.currentPrice;
    }
    
    /**
     * (set前日比) <BR>
     * 前日比をセットする。
     * @@param l_dblComparedPreviousDay - (前日比) <BR>
     * 前日比。
     * @@roseuid 4282C7840255
     */
    public void setComparedPreviousDay(double l_dblComparedPreviousDay) 
    {
        this.comparedPreviousDay = l_dblComparedPreviousDay;
    }
    
    /**
     * (get前日比)　@<BR>
     * 前日比を取得する。
     * @@return double
     * @@roseuid 4282C7840264
     */
    public double getComparedPreviousDay() 
    {
        return this.comparedPreviousDay;
    }
    
    /**
     * (set時価発表時間) <BR>
     * 時価発表時間をセットする。
     * @@param l_tsCurrentPricePublicTime - (時価発表時間) <BR>
     * 時価発表時間。
     * @@roseuid 4282C7840274
     */
    public void setCurrentPricePublicTime(Timestamp l_tsCurrentPricePublicTime) 
    {
        this.currentPricePublicTime = l_tsCurrentPricePublicTime;
    }
    
    /**
     * (get時価発表時間) <BR>
     * 時価発表時間を取得する。
     * @@return Date
     * @@roseuid 4282C7840283
     */
    public Date getCurrentPricePublicTime() 
    {
        return this.currentPricePublicTime;
    }
    
    /**
     * (set時価取得区分) <BR>
     * 時価取得区分をセットする。
     * @@param l_strCurrentPriceGetDiv - (時価取得区分) <BR>
     * 時価取得区分。
     * @@roseuid 4282C7840293
     */
    public void setCurrentPriceGetDiv(String l_strCurrentPriceGetDiv) 
    {
        this.currentPriceGetDiv = l_strCurrentPriceGetDiv;
    }
    
    /**
     * (get時価取得区分) <BR>
     * 時価取得区分を取得する。
     * @@return String
     * @@roseuid 4282C7840295
     */
    public String getCurrentPriceGetDiv() 
    {
        return this.currentPriceGetDiv;
    }
    
    /**
     * (set時価区分) <BR>
     * 時価区分をセットする。
     * @@param l_strCurrentPriceDiv - (時価区分) <BR>
     * 時価区分。
     * @@roseuid 4292B35C0241
     */
    public void setCurrentPriceDiv(String l_strCurrentPriceDiv) 
    {
        this.currentPriceDiv = l_strCurrentPriceDiv;
     
    }
    
    /**
     * (get時価区分) <BR>
     * 時価区分を取得する。
     * @@return String
     * @@roseuid 4292B35C0243
     */
    public String getCurrentPriceDiv() 
    {
        return this.currentPriceDiv;
    }
    
    /**
     * (set市場コード) <BR>
     * 市場コードをセットする。
     * @@param l_strMarketCode - (市場コード) <BR>
     * 市場コード。
     * @@roseuid 4282C78402A3
     */
    public void setMarketCode(String l_strMarketCode) 
    {
        this.marketCode = l_strMarketCode;
    }
    
    /**
     * (get市場コード) <BR>
     * 市場コードを取得する。
     * @@return String
     * @@roseuid 4282C78402B2
     */
    public String getMarketCode() 
    {
        return this.marketCode;
    }
}
@
