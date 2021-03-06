head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondEstimatedAssetCalcResult.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 債券概算評価額計算結果(WEB3BondEstimatedAssetCalcResult.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/08/17  齊珂 (中訊) 新規作成
 */

package webbroker3.bd;

import java.math.BigDecimal;

/**
 * (債券概算評価額計算結果)<BR>
 * 債券概算評価額計算結果<BR>
 * 
 * @@author 齊珂
 * @@version 1.0
 */
public class WEB3BondEstimatedAssetCalcResult 
{
    /**
     * (評価単価)<BR>
     * 評価単価<BR>
     */
    private BigDecimal estimatedPrice;
    
    /**
     * (概算評価額（円貨）)<BR>
     * 概算評価額（円貨）<BR>
     */
    private BigDecimal estimatedAsset;
    
    /**
     * (概算評価額（外貨）)<BR>
     * 概算評価額（外貨）<BR>
     */
    private BigDecimal foreignEstimatedAsset;
    
    /**
     * (債券概算評価額計算結果)<BR>
     * コンストラクタ<BR>
     * @@roseuid 44C085DA025A
     */
    public WEB3BondEstimatedAssetCalcResult() 
    {
     
    }
    
    /**
     * (get概算評価額（円貨）)<BR>
     * 概算評価額（円貨）を取得する。<BR>
     * @@return BigDecimaｌ
     * @@roseuid 44C088020131
     */
    public BigDecimal getEstimatedAsset() 
    {
         return estimatedAsset;
    }
    
    /**
     * (get概算評価額（外貨）)<BR>
     * 概算評価額（外貨）を取得する。<BR>
     * @@return BigDecimaｌ
     * @@roseuid 44C088230316
     */
    public BigDecimal getForeignEstimatedAsset() 
    {
         return foreignEstimatedAsset;
    }
    
    /**
     * (get評価単価)<BR>
     * 評価単価を取得する。<BR>
     * @@return BigDecimal
     * @@roseuid 44C95EDC03BF
     */
    public BigDecimal getEstimatedPrice() 
    {
         return estimatedPrice;
    }
    
    /**
     * (set概算評価額（円貨）)<BR>
     * 概算評価額（円貨）をセットする。<BR>
     * @@param l_bdEstimatedAsset - (概算評価額（円貨）)<BR>
     * 概算評価額（円貨）<BR>
     * @@roseuid 44C95F0E00C2
     */
    public void setEstimatedAsset(BigDecimal l_bdEstimatedAsset) 
    {
         this.estimatedAsset = l_bdEstimatedAsset;
    }
    
    /**
     * (set概算評価額（外貨）)<BR>
     * 概算評価額（外貨）をセットする。<BR>
     * @@param l_bdForeignEstimatedAsset - (概算評価額（外貨）)<BR>
     * 概算評価額（外貨）<BR>
     * @@roseuid 44C95F490083
     */
    public void setForeignEstimatedAsset(BigDecimal l_bdForeignEstimatedAsset) 
    {
        this.foreignEstimatedAsset = l_bdForeignEstimatedAsset;
    }
    
    /**
     * (set評価単価)<BR>
     * 評価単価をセットする。<BR>
     * @@param l_bdEstimatedPrice - (評価単価)<BR>
     * 評価単価<BR>
     * @@roseuid 44C086F601BE
     */
    public void setEstimatedPrice(BigDecimal l_bdEstimatedPrice) 
    {
        this.estimatedPrice = l_bdEstimatedPrice;
    }
}
@
