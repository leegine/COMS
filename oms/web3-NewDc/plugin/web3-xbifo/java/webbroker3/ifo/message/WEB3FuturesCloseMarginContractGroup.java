head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.20.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesCloseMarginContractGroup.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数先物返済建玉一覧明細行(WEB3FuturesCloseMarginContractGroup.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/19 鄒鋭 (中訊) 新規作成
Revesion History : 2007/06/08 孫洪江 (中訊) 仕様変更モデルNo.640
*/

package webbroker3.ifo.message;

import java.util.Date;
import com.fitechlabs.xtrade.kernel.message.Message;
/**
 * (株価指数先物返済建玉一覧明細行)<BR>
 * 株価指数先物返済建玉一覧明細行クラス<BR>
 * @@author 鄒鋭
 * @@version 1.0
 */
public class WEB3FuturesCloseMarginContractGroup extends Message
{

    /**
     * (建単価)<BR>
     */
    public String contractPrice;

    /**
     * (建日)<BR>
     */
    public Date openDate;

    /**
     * (返済数量（注文部分数量）)<BR>
     */
    public String contractOrderQuantity;

    /**
     * (注文単価区分)<BR>
     */
    public String orderPriceDiv;
    
    /**
     * (注文単価)<BR>
     */
    public String limitPrice;

    /**
     * (約定数量)<BR>
     */
    public String execQuantity;

    /**
     * (約定単価)<BR>
     */
    public String execPrice;

    /**
     * (建手数料)<BR>
     */
    public String contractCommission;

    /**
     * (建手数料消費税)<BR>
     */
    public String contractCommissionConsumptionTax;

    /**
     * (建代金)<BR>
     */
    public String contractExecPrice;

    /**
     * (決済損益)<BR>
     */
    public String settleProfitLoss;

    /**
     * (建順位)<BR>
     */
    public String closeMarginOrderNumber;
    
    /**
     * (立会区分)<BR>
     * 1：夕場（夕場実施する会社の夕場時間帯のみ設定）　@NULL：その他<BR>
     */
    public String sessionType;

    /**
     * @@roseuid 40F7AE1703A9
     */
    public WEB3FuturesCloseMarginContractGroup()
    {

    }
}
@
