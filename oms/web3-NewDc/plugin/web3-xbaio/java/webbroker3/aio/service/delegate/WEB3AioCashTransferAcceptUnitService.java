head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.15.54;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioCashTransferAcceptUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入出金受付UnitServiceインターフェイス(WEB3AioCashTransferAcceptUnitService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/19 周勇 (中訊) 新規作成
                   2004/10/22 黄建 (中訊) レビュー    
*/
package webbroker3.aio.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;

import webbroker3.common.WEB3BaseException;


/**
 * (入出金受付UnitService)<BR>
 * 入出金受付UnitServiceインターフェイス
 * 
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public interface WEB3AioCashTransferAcceptUnitService extends Service 
{
    
    /**
     * (入出金受付DB更新)<BR>
     * 入出金受付DB更新処理を行う。
     * @@param l_orderUnit - (注文単位オブジェクト)
     * @@param l_strErrorCode - (エラーコード)
     * @@param l_strAcceptNoticeDiv - (受付通知区分)
     * @@roseuid 40FF5D7A01B5
     */
    public void execute(AioOrderUnit l_orderUnit, String l_strErrorCode, String l_strAcceptNoticeDiv) 
        throws WEB3BaseException;
}
@
