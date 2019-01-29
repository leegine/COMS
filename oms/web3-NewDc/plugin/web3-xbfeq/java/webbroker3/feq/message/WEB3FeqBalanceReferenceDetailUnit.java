head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.30.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqBalanceReferenceDetailUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式残高照会明細(WEB3FeqBalanceReferenceDetailUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 黄建 (中訊) 新規作成
                 : 2005/08/01 郭英(中訊) レビュー     
*/

package webbroker3.feq.message;

/**
 * (外国株式残高照会明細)<BR>
 * 外国株式残高照会明細クラス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3FeqBalanceReferenceDetailUnit extends WEB3FeqAssetUnit 
{
    
    /**
     * (残高数量)<BR>
     * 残高数量<BR>
     */
    public String balanceQuantity;
    
    /**
     * (売付不能数量)<BR>
     * 売付不能数量<BR>
     */
    public String sellImpossQuantity;
    
    /**
     * (概算簿価単価（円貨））<BR>
     * 概算簿価単価（円貨）<BR>
     */
    public String estimatedBookPrice;
    
    /**
     * (概算簿価単価（外貨）)<BR>
     * 概算簿価単価（外貨）<BR>
     */
    public String foreignEstimatedBookPrice;
    
    /**
     * (簿価単価入力済フラグ)<BR>
     * 簿価単価入力済フラグ<BR>
     * <BR>
     * false：未入力<BR>
     * true：入力済<BR>
     */
    public boolean estimatedBookPriceInputFlag;
    
    /**
     * (時価)<BR>
     * 時価<BR>
     * <BR>
     * ※値がついていないときは未設定<BR>
     */
    public String currentPrice;
    
    /**
     * (前日比)<BR>
     * 前日比<BR>
     * <BR>
     * ※値がついていないときは未設定<BR>
     */
    public String comparedPreviousDay;
    
    /**
     * (時価取得時間)<BR>
     * 時価取得時間<BR>
     * <BR>
     * ※値がついていないときは未設定<BR>
     */
    public String currentPriceTime;
    
    /**
     * (概算評価額(残高数量))<BR>
     * 概算評価額(残高数量)<BR>
     */
    public String estimatedAssetBalanceQuantity;
    
    /**
     * (概算評価額(売付可能数量))<BR>
     * 概算評価額(売付可能数量)<BR>
     */
    public String estimatedAssetSellPossQuantity;
    
    /**
     * (概算評価額(注文中数量))<BR>
     * 概算評価額(注文中数量)<BR>
     */
    public String estimatedAssetOrderedQuantity;
    
    /**
     * (概算評価額(売付不能数量))<BR>
     * 概算評価額(売付不能数量)<BR>
     */
    public String estimatedAssetSellImpossQuantity;
    
    /**
     * (概算評価損益(残高数量))<BR>
     * 概算評価損益(残高数量)<BR>
     */
    public String estimatedAppraisalProfitLossBalanceQuantity;
    
    /**
     * (概算評価損益(売付可能数量))<BR>
     * 概算評価損益(売付可能数量)<BR>
     */
    public String estimatedAppraisalProfitLossSellPossQuantity;
    
    /**
     * (概算評価損益(注文中数量))<BR>
     * 概算評価損益(注文中数量)<BR>
     */
    public String estimatedAppraisalProfitLossOrderedQuantity;
    
    /**
     * (概算評価損益(売付不能数量))<BR>
     * 概算評価損益(売付不能数量)<BR>
     */
    public String estimatedAppraisalProfitLossSellImpossQuantity;
    
    /**
     * (買付可能フラグ)<BR>
     * 買付可能フラグ<BR>
     * <BR>
     * true：買付可能<BR>
     * false：買付不可<BR>
     */
    public boolean buyPossFlag;
    
    /**
     * (外国株式残高照会明細)<BR>
     * コンストラクタ<BR>
     * @@roseuid 42A7FB0D0145
     */
    public WEB3FeqBalanceReferenceDetailUnit() 
    {
     
    }
}
@
