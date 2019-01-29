head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.01.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToStopOrderService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 逆指値注文発注サービス(WEB3ToStopOrderService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/07 呉艶飛(中訊) 新規作成
*/
package webbroker3.triggerorder.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;

import webbroker3.common.WEB3BaseException;


/**
 * (逆指値注文発注サービス)<BR>
 * 逆指値注文発注サービスインターフェイス<BR>
 * @@author 呉艶飛
 * @@version 1.0
 */
public interface WEB3ToStopOrderService extends Service 
{
    
    /**
     * (execute逆指値注文発注)<BR>
     * 逆指値注文発注処理を行う。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト。<BR>
     * @@param l_lngOrderId - (注文ID)<BR>
     * 注文ID。<BR>
     * （発注対象の注文の注文ID）<BR>
     * @@param l_productType - (銘柄タイプ)<BR>
     * 銘柄タイプ。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 434C78F00343
     */
    public void executeStopOrder(SubAccount l_subAccount, long l_lngOrderId, ProductTypeEnum l_productType) throws WEB3BaseException;
}
@
