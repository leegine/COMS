head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MstkSellUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式ミニ投資売付明細クラス(WEB3MstkSellUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 李海波 (中訊) 新規作成
*/

package webbroker3.equity.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * （株式ミニ投資売付明細）。<BR>
 * <BR>
 * 株式ミニ投資売付明細クラス
 * @@author 李海波
 * @@version 1.0
 */
public class WEB3MstkSellUnit extends Message 
{
    
    /**
     * （銘柄コード）。
     */
    public String productCode;
    
    /**
     * （銘柄名）。
     */
    public String productName;
    
    /**
     * （市場コード）。<BR>
     * <BR>
     * 1：東京 2：大阪 3：名古屋 6：福岡 8：札幌 9：NNM 10：JASDAQ
     */
    public String marketCode;
    
    /**
     * （残高株数）。
     */
    public String balanceQuantity;
    
    /**
     * （売付可能株数）。
     */
    public String sellPossQuantity;
    
    /**
     * （買付注文中株数）。
     */
    public String buyOrderedQuantity;
    
    /**
     * （売付注文中株数）。
     */
    public String sellOrderedQuantity;
    
    /**
     * （売付可能フラグ）。<BR>
     * <BR>
     * true：売付可能　@　@false：売付不可
     */
    public boolean sellPossFlag;
    
    /**
     * （株式ミニ投資売付明細）。<BR>
     * デフォルトコンストラクタ
     */
    public WEB3MstkSellUnit() 
    {
     
    }
}
@
