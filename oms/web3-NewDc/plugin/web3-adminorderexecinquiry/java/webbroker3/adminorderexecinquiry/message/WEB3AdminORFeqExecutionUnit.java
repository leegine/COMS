head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.45.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminORFeqExecutionUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者外国株式出来入力明細(WEB3AdminORFeqExecutionUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/22 鄭海良(中訊) 新規作成
                 : 2005/08/02 郭英(中訊) レビュー
*/

package webbroker3.adminorderexecinquiry.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (管理者外国株式出来入力明細)<BR>
 * 管理者外国株式出来入力明細クラス<BR>
 *
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3AdminORFeqExecutionUnit extends Message
{
    
    /**
     * 注文ＩＤ
     */
    public String id;
    
    /**
     * (運用コード)<BR>
     * 運用コード<BR>
     */
    public String managementCode;
    
    /**
     * (識別コード)<BR>
     * 識別コード<BR>
     */
    public String requestNumber;
    
    /**
     * (部店コード)<BR>
     * 部店コード<BR>
     */
    public String branchCode;
    
    /**
     * (顧客コード)<BR>
     * 顧客コード<BR>
     */
    public String accountCode;
    
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
     * (取引区分)<BR>
     * 取引区分<BR>
     * <BR>
     * 1：　@現物買付注文<BR>
     * 2：　@現物売付注文<BR>
     * 3：　@新規買建注文<BR>
     * 4：　@新規売建注文<BR>
     * 5：　@買建返済注文<BR>
     * 6：　@売建返済注文<BR>
     * 7：　@現引注文<BR>
     * 8：　@現渡注文<BR>
     * 99：　@立会外分売<BR>
     * 101：　@ミニ株買付注文<BR>
     * 102：　@ミニ株売付注文<BR>
     * 201：　@投資信託買付注文<BR>
     * 202：　@投資信託売付注文<BR>
     * 501：　@累投買付注文<BR>
     * 502：　@累投売付注文<BR>
     * 601：　@指数先物新規買建注文<BR>
     * 602：　@指数先物新規売建注文<BR>
     * 603：　@指数先物売建返済注文<BR>
     * 604：　@指数先物買建返済注文<BR>
     * 605：　@指数オプション新規買建注文<BR>
     * 606：　@指数オプション新規売建注文<BR>
     * 607：　@指数オプション売建返済注文<BR>
     * 608：　@指数オプション買建返済注文<BR>
     * 701：  外国株式買付<BR>　@　@　@　@　@　@　@　@　@　@
     * 702：  外国株式売付<BR>
     */
    public String tradingType;
    
    /**
     * (市場コード)<BR>
     * 市場コード<BR>
     */
    public String marketCode;
    
    /**
     * (発注日)<BR>
     * 発注日<BR>
     */
    public Date orderBizDate;
    
    /**
     * (約定番号)<BR>
     * 約定番号<BR>
     */
    public String execNo;
    
    /**
     * (約定単価)<BR>
     * 約定単価<BR>
     */
    public String execPrice;
    
    /**
     * (約定数量)<BR>
     * 約定数量<BR>
     */
    public String execQuantity;
    
    /**
     * (約定為替)<BR>
     * 約定為替<BR>
     */
    public String execExchangeRate;
    
    /**
     * (約定日)<BR>
     * 約定日<BR>
     */
    public Date executionDate;
    
    /**
     * (受渡日)<BR>
     * 受渡日<BR>
     */
    public Date deliveryDate;
    
    /**
     * (現地受渡日)<BR>
     * 現地受渡日<BR>
     */
    public Date localDeliveryDate;
    
    /**
     * (更新者コード)<BR>
     * 更新者コード<BR>
     */
    public String updaterCode;
    
    /**
     * (管理者外国株式出来入力明細)<BR>
     * コンストラクタ<BR>
     * @@roseuid 42AD03B30136
     */
    public WEB3AdminORFeqExecutionUnit() 
    {
     
    }
}
@
