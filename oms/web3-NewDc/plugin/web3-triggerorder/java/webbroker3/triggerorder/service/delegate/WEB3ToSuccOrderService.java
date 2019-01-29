head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.01.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccOrderService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 連続注文発注サービス(WEB3ToSuccOrderService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/13 凌建平(中訊) 新規作成
*/

package webbroker3.triggerorder.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;

import webbroker3.common.WEB3BaseException;


/**
 * (連続注文発注サービス)<BR>
 * 連続注文発注サービスインタフェース。<BR>
 * <BR>
 * @@author 凌建平 <BR>
 * @@version 1.0<BR>
 */
public interface WEB3ToSuccOrderService extends Service 
{
    
    /**
     * (execute連続注文発注)<BR>
     * 連続注文発注処理を行う。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト。<BR>
     * @@param l_lngSubOrderId - (予約注文の注文ID)<BR>
     * 注文ID。<BR>
     * （発注対象の予約注文の注文ID）<BR>
     * @@param l_productType - (銘柄タイプ)<BR>
     * 銘柄タイプ。
     * @@throws WEB3BaseException
     * @@roseuid 4328F6B60346
     */
    public void executeSuccOrder(SubAccount l_subAccount, long l_lngSubOrderId, ProductTypeEnum l_productType) throws WEB3BaseException;
}
@
