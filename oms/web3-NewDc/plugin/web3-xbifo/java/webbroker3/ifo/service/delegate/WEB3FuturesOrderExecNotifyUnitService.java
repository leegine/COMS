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
filename	WEB3FuturesOrderExecNotifyUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数先物出来通知１件サービスインタフェイス(WEB3FuturesOrderExecNotifyUnitService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/7/23 艾興 (中訊) 新規作成
*/
package webbroker3.ifo.service.delegate;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;

import webbroker3.common.WEB3BaseException;


/**
 * (先物出来通知１件サービス)<BR>
 * 株価指数先物出来通知１件サービスインタフェイス<BR>
 * <BR>
 * １件ごとの出来通知処理を実施する。<BR>
 */
public interface WEB3FuturesOrderExecNotifyUnitService extends Service 
{
    
    /**
     * (notify約定)<BR>
     * 約定処理を実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（先物出来通知）notify約定」参照。<BR>
     * @@param l_orderUnit - 注文単位オブジェクト<BR>
     * @@param l_tsExecDate - 約定日時<BR>
     * @@param l_dblExecQuantity - 約定数量<BR>
     * @@param l_dblExecPrice - 約定単価<BR>
     * @@param l_strExecNotifyDiv - 出来通知区分<BR>
     * @@roseuid 40A842C301C7
     */
    public void notifyExecute(OrderUnit l_orderUnit, Timestamp l_tsExecDate, double l_dblExecQuantity, double l_dblExecPrice, String l_strExecNotifyDiv)
    throws WEB3BaseException;
    
    /**
     * (notify約定取消)<BR>
     * 出来通知処理を実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（先物出来通知）notify約定取消」参照。<BR>
     * @@param l_orderUnit - 注文単位オブジェクト<BR>
     * @@param l_tsExecDate - 約定日時<BR>
     * @@param l_dblExecQuantity - 約定数量<BR>
     * @@param l_dblExecPrice - 約定単価<BR>
     * @@roseuid 40A842C301E6
     */
    public void notifyExecuteCancel(OrderUnit l_orderUnit, Timestamp l_tsExecDate, double l_dblExecQuantity, double l_dblExecPrice) throws WEB3BaseException; 
}
@
