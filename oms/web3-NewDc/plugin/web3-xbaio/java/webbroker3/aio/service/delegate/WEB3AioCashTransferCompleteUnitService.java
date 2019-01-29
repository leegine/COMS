head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.14.23;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioCashTransferCompleteUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入出金完了UnitServiceインターフェイス(WEB3AioCashTransferCompleteUnitService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/19 周勇 (中訊) 新規作成  
                   2004/10/22 黄建 (中訊) レビュー    
*/
package webbroker3.aio.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;
import webbroker3.common.WEB3BaseException;


/**
 * (入出金完了UnitServiceインターフェイス)
 * 
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public interface WEB3AioCashTransferCompleteUnitService extends Service 
{
    
    /**
     * (complete入出金)<BR>
     * 入出金結果での注文データの更新とトランザクションデータの生成を行う。
     * @@param l_orderUnit - (注文単位オブジェクト)
     * @@roseuid 40FE63070016
     */
    public void completeCashTransfer(AioOrderUnit l_orderUnit);
    
    /**
     * (complete入出金取消)<BR>
     * 入出金結果（取消）での注文データとトランザクションデータの更新を行う。
     * @@param l_orderUnit - (注文単位オブジェクト)
     * @@throws WEB3BaseException
     * @@roseuid 4105D55F0177
     */
    public void completeCashTransferCancel(AioOrderUnit l_orderUnit) 
        throws WEB3BaseException;
}@
