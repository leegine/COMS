head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondExecuteNotifyService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券約定通知サービス(WEB3AdminBondExecuteNotifyService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/8/16 徐大方(中訊) 新規作成         
*/

package webbroker3.bd.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondOrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.bd.WEB3AdminBondDefaultInterceptor;

/**
 * (債券約定通知サービス)<BR>
 * 債券約定通知サービスインターフェース
 * 
 * @@author 徐大方
 * @@version 1.0
 */
public interface WEB3AdminBondExecuteNotifyService extends Service
{
    
    /**
     * (notify約定)<BR>
     * notify約定<BR>
     * <BR>
     * 約定処理をする<BR>
     * @@param l_bondOrderUnit - (債券注文単位)<BR>
     * 債券注文単位<BR>
     * @@param l_adminBondDefaultInterceptor - (債券管理者デフォルトインタセプタ)<BR>
     * 債券管理者デフォルトインタセプタ<BR>
     * @@throws WEB3BaseException
     * @@roseuid 44D9961A01FD
     */
    public void notifyExecute(BondOrderUnit l_bondOrderUnit, 
        WEB3AdminBondDefaultInterceptor l_adminBondDefaultInterceptor) throws WEB3BaseException ;
    
    /**
     * (undo約定)<BR>
     * undo約定<BR>
     * <BR>
     * 約定取消処理をする<BR>
     * @@param l_bondOrderUnit - (債券注文単位)<BR>
     * 債券注文単位<BR>
     * @@param l_adminBondDefaultInterceptor - (債券管理者デフォルトインタセプタ)<BR>
     * 債券管理者デフォルトインタセプタ<BR>
     * @@throws WEB3BaseException
     * @@roseuid 44D9961A022C
     */
    public void undoExecute(BondOrderUnit l_bondOrderUnit, 
        WEB3AdminBondDefaultInterceptor l_adminBondDefaultInterceptor) throws WEB3BaseException ;
    
    /**
     * (accept新規注文)<BR>
     * accept新規注文を実行<BR>
     * <BR>
     * 新規注文受付処理をする<BR>
     * @@param l_lngOrderId - (注文ID)<BR>
     * 注文ID<BR>
     * @@param l_adminBondDefaultInterceptor - (債券管理者デフォルトインタセプタ)<BR>
     * 債券管理者デフォルトインタセプタ<BR>
     * @@throws WEB3BaseException
     * @@roseuid 44D9961A026A
     */
    public void acceptNewOrder(long l_lngOrderId, 
        WEB3AdminBondDefaultInterceptor l_adminBondDefaultInterceptor) throws WEB3BaseException ;
    
    /**
     * (accept注文取消)<BR>
     * accept注文取消処理を実行<BR>
     * <BR>
     * 注文取消受付処理をする<BR>
     * @@param l_lngOrderId - (注文ID)<BR>
     * 注文ID<BR>
     * @@param l_adminBondDefaultInterceptor - (債券管理者デフォルトインタセプタ)<BR>
     * 債券管理者デフォルトインタセプタ<BR>
     * @@throws WEB3BaseException
     * @@roseuid 44D9961A0299
     */
    public void acceptOrderCancel(long l_lngOrderId, 
        WEB3AdminBondDefaultInterceptor l_adminBondDefaultInterceptor) throws WEB3BaseException ;
}
@
