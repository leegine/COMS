head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.42.37;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoBookbuildingPriceCalcResult.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 申告相当額計算結果データクラス(WEB3IpoBookbuildingPriceCalcResult.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 鄭海良(中訊) 新規作成
*/

package webbroker3.ipo;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;

/**
 * 申告相当額計算結果データクラス
 *  
 * @@author 鄭海良
 * @@version 1.0  
 */
public class WEB3IpoBookbuildingPriceCalcResult 
{
    
    /**
     * 計算単価
     */
    private double price = 0;
    
    /**
     * 基準値（時価）
     */
    private double currentPrice = 0;
    
    /**
     * (申告相当額)<BR>
     * 時価
     */
    private double bookbuildingPrice = 0;
    
    /**
     * 計算単価（実価格）
     */
    private double calcUnitPriceTruePrice = 0;
    
    /**
     * (申告相当額計算結果)<BR>
     * デフォルトコンストラクタ
     * @@roseuid 40D7E5230385
     */
    public WEB3IpoBookbuildingPriceCalcResult() 
    {
     
    }
    
    /**
     * (get計算単価)<BR>
     * 計算単価を取得する。<BR>
     * <BR>
     * this.計算単価を返却する。
     * @@return double
     * @@roseuid 40D7D5BF0162
     */
    public double getPrice() 
    {
        return this.price;
    }
    
    /**
     * (get基準値（時価）)<BR>
     * 基準値（時価）を取得する。<BR>
     * <BR>
     * this.基準値（時価）を返却する。
     * @@return double
     * @@roseuid 40D7D5DC03D3
     */
    public double getCurrentPrice() 
    {
        return this.currentPrice;
    }
    
    /**
     * (get申告相当額)<BR>
     * 申告相当額を取得する。<BR>
     * <BR>
     * this.申告相当額を返却する。
     * @@return double
     * @@roseuid 40D7D5DD03D3
     */
    public double getBookbuildingPrice() 
    {
        return this.bookbuildingPrice;
    }
    
    /**
     * (set計算単価)<BR>
     * 計算単価をセットする。<BR>
     * <BR>
     * this.計算単価に引数の計算単価をセットする。
     * @@param l_dblPrice - 計算単価
     * @@roseuid 40D7D5F80327
     */
    public void setPrice(double l_dblPrice) 
    {
        this.price = l_dblPrice;
    }
    
    /**
     * (set基準値（時価）)<BR>
     * 基準値（時価）をセットする。<BR>
     * <BR>
     * this.基準値（時価）に引数の基準値（時価）をセットする。
     * @@param l_dblCurrentPrice - 基準値（時価）
     * @@roseuid 40D7D62A03B4
     */
    public void setCurrentPrice(double l_dblCurrentPrice) 
    {
        this.currentPrice = l_dblCurrentPrice;
    }
    
    /**
     * (set申告相当額)<BR>
     * 申告相当額をセットする。<BR>
     * <BR>
     * this.申告相当額に引数の申告相当額をセットする。
     * @@param l_dblBookbuildingPrice - 申告相当額
     * @@roseuid 40D7D62B03B4
     */
    public void setBookbuildingPrice(double l_dblBookbuildingPrice) 
    {
        this.bookbuildingPrice = l_dblBookbuildingPrice;
    }
    
    /**
     * (get計算単価（実価格）)<BR>
     * 計算単価（実価格）を取得する。<BR>
     * <BR>
     * this.計算単価（実価格）を返却する。
     * @@return double
     * @@roseuid 40EE8BBE004F
     */
    public double getCalcUnitPriceReal() 
    {
        return this.calcUnitPriceTruePrice;
    }
    
    /**
     * (set計算単価（実価格）)<BR>
     * 計算単価（実価格）をセットする。<BR>
     * <BR>
     * this.計算単価（実価格）に引数の計算単価をセットする。
     * @@param l_dblCalcUnitPriceReal - 計算単価（実価格）
     * @@roseuid 40EE8BBE0050
     */
    public void setCalcUnitPriceReal(double l_dblCalcUnitPriceReal) 
    {
        this.calcUnitPriceTruePrice = l_dblCalcUnitPriceReal;
    }

    /**
     * (validate申告相当額)<BR>
     * 申告相当額の最大桁数チェックを行う。<BR>
     * <BR>
     * this.申告相当額の整数部が最大値（１２桁）を超えている場合は、例外をスローする。<BR>
     * @@roseuid 40EE8BBE0050
     */
    public void validateBookbuildingPrice() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validateBookbuildingPrice()";
        
        long l_lngBookbuildingPrice = (long)this.bookbuildingPrice;
        if (l_lngBookbuildingPrice > 999999999999L)
        {
            String l_strErrorMessage = "申告相当額[" + this.bookbuildingPrice + "]の整数部が最大値（１２桁）を超えています。";
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01986,
                this.getClass().getName() + STR_METHOD_NAME, 
                l_strErrorMessage);            
        }
    }
}
@
