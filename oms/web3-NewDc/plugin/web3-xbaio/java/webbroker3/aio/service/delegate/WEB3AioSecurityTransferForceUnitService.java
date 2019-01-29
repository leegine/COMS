head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.18.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioSecurityTransferForceUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 証券振替強制UnitService(WEB3AioSecurityTransferForceUnitService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/14 屈陽 (中訊) 新規作成   
*/

package webbroker3.aio.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;

import webbroker3.aio.data.HostMrgsecTransNotifyParams;
import webbroker3.common.WEB3BaseException;


/**
 * (証券振替強制UnitService)<BR>
 * 証券振替強制UnitServiceインターフェイス
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public interface WEB3AioSecurityTransferForceUnitService extends Service 
{
    
    /**
     * (submit注文)<BR>
     * 証券振替の注文を登録し、配列を取得する。
     * @@param l_params - 代用振替強制キューテーブルの行オブジェクト
     * @@return AioOrderUnit[]
     * @@roseuid 4157961901F5
     */
    public AioOrderUnit[] submitOrder(HostMrgsecTransNotifyParams l_params) 
        throws WEB3BaseException;
}
@
