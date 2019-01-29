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
filename	WEB3IfoDesignateEmbeddedNotifyService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP指定埋通知サービス(WEB3IfoDesignateEmbeddedNotifyService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/16 張宝楠 (中訊) 新規作成
*/

package webbroker3.ifo.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;

import webbroker3.common.WEB3BaseException;

/**
 * (先物OP指定埋通知サービス)<BR>
 * 先物OP指定埋通知サービスインタフェイス<BR>
 * @@author 張宝楠(中訊)
 * @@version 1.0
 */
public interface WEB3IfoDesignateEmbeddedNotifyService extends Service 
{
    
    /**
     * (create指定埋通知)<BR>
     * <BR>
     * 指定埋通知キューテーブルに行を作成する。<BR>
     * @@param l_orderUnit - 注文単位オブジェクト
     * @@roseuid 408CC9D0017F
     */
    public void createDesignateEmbeddedNotify(OrderUnit l_orderUnit) throws WEB3BaseException;
    
    /**
     * (undo指定埋通知)<BR>
     * <BR>
     * 指定埋通知キューテーブルの行を無効にする。<BR>
     * @@param l_orderUnit - 注文単位オブジェクト
     * @@roseuid 408F1A2A0270
     */
    public void undoDesignateEmbeddedNotify(OrderUnit l_orderUnit) throws WEB3BaseException;
}
@
