head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.26.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqAssetUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式保有銘柄情報(WEB3FeqAssetUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 黄建 (中訊) 新規作成
                 : 2005/08/01 郭英(中訊) レビュー  
*/

package webbroker3.feq.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (外国株式保有銘柄情報)<BR>
 * 外国株式保有銘柄情報クラス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3FeqAssetUnit extends Message 
{
    
    /**
     * (保有資産ID)<BR>
     * 保有資産ID<BR>
     */
    public String assetId;
    
    /**
     * (市場コード)<BR>
     * 市場コード<BR>
     */
    public String marketCode;
    
    /**
     * (銘柄コード)<BR>
     * 銘柄コード<BR>
     */
    public String productCode;
    
    /**
     * (現地銘柄コード)<BR>
     * 現地銘柄コード<BR>
     */
    public String localProductCode;
    
    /**
     * (銘柄名)<BR>
     * 銘柄名<BR>
     */
    public String productName;
    
    /**
     * (特定口座区分)<BR>
     * 特定口座区分<BR>
     * <BR>
     * 0：一般<BR>
     * 1：特定<BR>
     */
    public String taxType;
    
    /**
     * (通貨コード)<BR>
     * 通貨コード<BR>
     */
    public String currencyCode;
    
    /**
     * (売付可能数量)<BR>
     * 売付可能数量<BR>
     */
    public String sellPossQuantity;
    
    /**
     * (注文中数量)<BR>
     * 注文中数量<BR>
     */
    public String orderedQuantity;
    
    /**
     * (売付可能フラグ)<BR>
     * 売付可能フラグ<BR>
     * <BR>
     * true：売付可能<BR>
     * false：売付不可<BR>
     */
    public boolean sellPossFlag;
    
    /**
     * (外国株式保有銘柄情報)<BR>
     * コンストラクタ<BR>
     * @@roseuid 42074EE801A3
     */
    public WEB3FeqAssetUnit() 
    {
     
    }
}
@
