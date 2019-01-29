head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.44.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminORFeqOrderExecutionRefUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者外国株式注文約定照会Unit(WEB3AdminORFeqOrderExecutionRefUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/22 鄭海良(中訊) 新規作成
                 : 2005/08/02 郭英(中訊) レビュー
*/

package webbroker3.adminorderexecinquiry.message;


/**
 * (管理者外国株式注文約定照会Unit)
 * 管理者外国株式注文約定照会Unitクラス
 *
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3AdminORFeqOrderExecutionRefUnit extends WEB3AdminOROrderExecutionRefCommon 
{
    
    /**
     * (伝票No)<BR>
     * 伝票No<BR>
     */
    public String orderNumber;
    
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
     * (市場コード)<BR>
     * 市場コード<BR>
     */
    public String marketCode;
    
    /**
     * (口座区分)<BR>
     * 0：一般　@1：特定<BR>
     */
    public String taxType = null;
    
    /**
     * (運用コード)<BR>
     * 運用コード<BR>
     */
    public String managementCode;
    
    /**
     * (決済区分)<BR>
     * 決済区分<BR>
     * <BR>
     * 0：円貨<BR>
     * 1：外貨<BR>
     */
    public String settleDiv;
    
    /**
     * (通貨コード)<BR>
     * 通貨コード<BR>
     */
    public String currencyCode;
    
    /**
     * (受渡代金（外貨）)<BR>
     * 受渡代金（外貨）<BR>
     */
    public String foreignDeliveryPrice = null;
    
    /**
     * (更新者コード)<BR>
     * 更新者コード<BR>
     */
    public String updaterCode;
    
    /**
     * (管理者外国株式注文約定照会Unit)<BR>
     * コンストラクタ<BR>
     * @@roseuid 42A69449009D
     */
    public WEB3AdminORFeqOrderExecutionRefUnit() 
    {
     
    }
}
@
