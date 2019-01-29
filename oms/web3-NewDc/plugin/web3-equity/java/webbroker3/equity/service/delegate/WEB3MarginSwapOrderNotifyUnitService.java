head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginSwapOrderNotifyUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  信用取引注文通知一件サービスインタフェース(WEB3MarginSwapOrderNotifyUnitService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/24 森川 (SRA) 新規作成
*/
package webbroker3.equity.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;


/**
 * （信用取引注文通知一件サービス）。<BR>
 * <BR>
 * 信用取引現引現渡注文通知一件サービスインタフェース。<BR>
 * （トランザクション属性：TransactionalInterceptor.TX_CREATE_NEW）
 * @@version 1.0
 */
public interface WEB3MarginSwapOrderNotifyUnitService extends Service 
{
    
    /**
     * (notify現引現渡注文)。<BR>
     * <BR>
     * 現引現渡注文通知処理を実施する。<BR>
     * <BR>
     * @@param l_params - 現引現渡入力通知キューParamsオブジェクト<BR>
     * @@throws WEB3BaseException
     */
    public void notifySwapOrder(WEB3MarginSwapOrderNotifyDataAdapter l_adapter)
        throws WEB3BaseException;
    
    /**
     * (notify現引現渡注文取消)。<BR>
     * <BR>
     * 現引現渡注文取消処理を実施する。 <BR>
     * <BR>
     * @@param l_params - 現引現渡入力通知キューParamsオブジェクト
     * @@throws WEB3BaseException
     */
    public void notifyCancelSwapOrder(WEB3MarginSwapOrderNotifyDataAdapter l_adapter)
        throws WEB3BaseException;
}
@
