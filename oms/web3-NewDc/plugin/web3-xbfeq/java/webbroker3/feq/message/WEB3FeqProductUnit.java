head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.36.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqProductUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式銘柄明細(WEB3FeqProductUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 黄建 (中訊) 新規作成
                 : 2005/08/01 郭英(中訊) レビュー   
*/

package webbroker3.feq.message;

import java.util.Date;

/**
 * (外国株式銘柄明細)<BR>
 * 外国株式銘柄明細クラス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3FeqProductUnit extends WEB3FeqProductCodeNameUnit 
{
    
    /**
     * (買付可能)<BR>
     * 買付可能<BR>
     * <BR>
     * true：　@可能<BR>
     * false：　@不可<BR>
     */
    public boolean buyPossFlag;
    
    /**
     * (売付可能)<BR>
     * 売付可能<BR>
     * <BR>
     * true：　@可能<BR>
     * false：　@不可<BR>
     */
    public boolean sellPossFlag;
    
    /**
     * (現地銘柄コード)<BR>
     * 現地銘柄コード<BR>
     */
    public String localProductCode;
    
    /**
     * (買付単位)<BR>
     * 買付単位<BR>
     */
    public String buyUnit;
    
    /**
     * (最低買付単位)<BR>
     * 最低買付単位<BR>
     */
    public String minBuyUnit;
    
    /**
     * (売付単位)<BR>
     * 売付単位<BR>
     */
    public String sellUnit;
    
    /**
     * (最低売付単位)<BR>
     * 最低売付単位<BR>
     */
    public String minSellUnit;
    
    /**
     * (市場コード)<BR>
     * 市場コード<BR>
     */
    public String marketCode;
    
    /**
     * (上場登録日)<BR>
     * 上場登録日<BR>
     */
    public Date listedDate;
    
    /**
     * (上場廃止日)<BR>
     * 上場廃止日<BR>
     */
    public Date unlistedDate;
    
    /**
     * (外国株式銘柄明細)<BR>
     * コンストラクタ<BR>
     * @@roseuid 4200C778027B
     */
    public WEB3FeqProductUnit() 
    {
     
    }
}
@
