head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.09.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualTPACancelListUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信管理者余力調整取消一覧行(WEB3AdminMutualTPACancelListUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/12/19 黄建 (中訊) 新規作成
*/

package webbroker3.mf.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 *(投信管理者余力調整取消一覧行)<BR>
 *投資信託管理者余力調整取消一覧行データクラス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0   
 */

public class WEB3AdminMutualTPACancelListUnit extends Message
{
    /**
     * (注文ID)<BR>
     *  注文ID<BR>
     */
    public String orderId;
    
    /**
     * (銘柄コード)<BR>
     *  銘柄コード<BR>
     */
    public String mutualProductCode;
    
    /**
     * (銘柄名)<BR>
     *  銘柄名<BR>
     */
    public String mutualProductName;
    
    /**
     * (精算金額)<BR>
     *  精算金額<BR>
     */
    public String settlePrice;
    
    /**
     * (発注日)<BR>
     *  発注日<BR>
     */
    public Date orderBizDate;
    
    /**
     * (約定日)<BR>
     *  約定日<BR>
     */
    public Date executionTimestamp;
    
    /**
     * (受渡日)<BR>
     *  受渡日<BR>
     */
    public Date deliveryDate;
    
    /**
     * デフォルトコンストラクタ
     * @@roseuid 4073BCCD0389<BR>
     */
    public WEB3AdminMutualTPACancelListUnit()
    {
        
    }
}
@
