head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionOrderExecNotifyUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数オプション出来通知１件サービス実装(WEB3OptionOrderExecNotifyUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/15 鄒鋭 (中訊) 新規作成
*/

package webbroker3.ifo.service.delegate;

import java.sql.Timestamp;

import webbroker3.common.WEB3BaseException;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;

/**
 * (OP出来通知UnitService)<BR>
 * <BR>
 * 株価指数オプション出来通知１件サービスインタフェイス<BR>
 * <BR>
 * １件ごとの出来通知処理を実施する。<BR>
 *                                                                     
 * @@author 鄒鋭
 * @@version 1.0
 */
public interface WEB3OptionOrderExecNotifyUnitService extends Service 
{
    
    /**
     * (notify約定)<BR>
     * <BR>
     * 約定処理を実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（OP出来通知）notify約定」参照。<BR>
     * @@param l_orderUnit - 注文単位オブジェクト
     * @@param l_tsExecDate - 約定日時
     * @@param l_dblExecQuantity - 約定数量
     * @@param l_dblExecPrice - 約定単価
     * @@param l_strExecutedNotifyDivision - 出来通知区分
     * @@roseuid 4087A9AB0335
     */
    public void notifyExecute(OrderUnit l_orderUnit, Timestamp l_tsExecDate, double l_dblExecQuantity, double l_dblExecPrice, String l_strExecutedNotifyDivision) throws WEB3BaseException; 
    
    /**
     * (notify約定取消)<BR>
     * <BR>
     * 出来通知処理を実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（OP出来通知）notify約定取消」参照。<BR>
     * @@param l_orderUnit - 注文単位オブジェクト
     * @@param l_tsExecDate - 約定日時
     * @@param l_dblExecQuantity - 約定数量
     * @@param l_dblExecPrice - 約定単価
     * @@roseuid 4087A9AB033B
     */
    public void notifyExecuteCancel(OrderUnit l_orderUnit, Timestamp l_tsExecDate, double l_dblExecQuantity, double l_dblExecPrice) throws WEB3BaseException;
}
@
