head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.16.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioSpsecTransferForceUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 特定口座振替強制UnitService(WEB3AioSpsecTransferForceUnitService)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/06 韋念瓊 (中訊) 新規作成   
*/

package webbroker3.aio.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;

import webbroker3.aio.data.HostSpsecTransNotifyParams;
import webbroker3.common.WEB3BaseException;


/**
 * (特定口座振替強制UnitService)<BR>
 * 特定口座振替強制UnitServiceインターフェイス
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public interface WEB3AioSpsecTransferForceUnitService extends Service 
{
    
    /**
     * (submit注文)<BR>
     * 特定口座振替の注文を登録し、その注文単位を取得する。
     * @@param l_params - 特定口座強制振替キューテーブルの行オブジェクト
     * @@return AioOrderUnit[]
     * @@roseuid 4157961901F5
     */
    public AioOrderUnit[] submitOrder(HostSpsecTransNotifyParams l_params) 
        throws WEB3BaseException;
        
    /**
     * (submit取消)<BR>
     * 特定口座振替の注文の取消を行う。
     * @@param l_params - 特定口座強制振替キューテーブルの行オブジェクト
     * @@return AioOrderUnit[]
     * @@roseuid 4157961901F5
     */
    public void submitCancel(HostSpsecTransNotifyParams l_params) 
        throws WEB3BaseException;
        
}
@
