head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MstkBalanceReferenceDetailUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式ミニ投資残高照会明細(WEB3MstkBalanceReferenceDetailUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/07 沢村　@仁士(SRA) 新規作成
*/

package webbroker3.equity.message;


/**
 * （株式ミニ投資残高照会明細）。<BR>
 * <BR>
 * 株式ミニ投資残高照会明細クラス<BR>
 */
public class WEB3MstkBalanceReferenceDetailUnit extends WEB3MstkSellUnit
{
    
    /**
     * (ID)<BR>
     * <BR>
     * 保有資産ID<BR>
     */
    public String id;
    
    /**
     * (口座区分)<BR>
     * <BR>
     * 0：一般　@1：特定<BR>
     */
    public String taxType;
    
    /**
     * (概算簿価単価)<BR>
     * <BR>
     * 概算簿価単価<BR>
     */
    public String estimatedBookPrice = null;
    
    /**
     * (簿価単価入力済フラグ)<BR>
     * <BR>
     * false：　@未入力<BR>
     * true：　@入力済<BR>
     */
    public boolean estimatedBookPriceInputFlag = false;
    
    /**
     * (時価)<BR>
     * <BR>
     * 時価<BR>
     */
    public String currentPrice = null;
    
    /**
     * (前日比)<BR>
     * <BR>
     * 前日比<BR>
     */
    public String comparedPreviousDay = null;
    
    /**
     * (時価取得時間)<BR>
     * <BR>
     * 時価取得時間<BR>
     */
    public String currentPriceTime = null;
    
    /**
     * (概算評価額)<BR>
     * <BR>
     * 概算評価額<BR>
     */
    public String estimatedAsset;
    
    /**
     * (概算評価損益)<BR>
     * <BR>
     * 概算評価損益<BR>
     */
    public String estimatedlAppraisalProfitLoss = null;
    
    /**
     * (買付可能フラグ)<BR>
     * <BR>
     * true：買付可能　@　@false：買付不可<BR>
     */
    public boolean buyPossFlag;
    
    /**
     * デフォルトコンストラクタ<BR>
     * <BR>
     * @@roseuid 41C65B3400C0<BR>
     */
    public WEB3MstkBalanceReferenceDetailUnit() 
    {
     
    }
}
@
