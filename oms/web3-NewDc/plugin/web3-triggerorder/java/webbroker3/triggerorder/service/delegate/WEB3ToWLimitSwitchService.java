head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.04.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToWLimitSwitchService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : W指値注文切替処理サービス(WEB3ToWLimitSwitchService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/24 唐性峰(中訊) 新規作成
*/

package webbroker3.triggerorder.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;

import webbroker3.common.WEB3BaseException;


/**
 * (W指値注文切替処理サービス)<BR>
 * W指値注文切替処理サービスインターフェイス<BR>
 * 
 * @@author 唐性峰
 * @@version 1.0
 */
public interface WEB3ToWLimitSwitchService extends Service
{
    
    /**
     * (executeW指値注文切替)<BR>
     * W指値注文切替処理を行う。
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト。<BR>
     * @@param l_lngOrderId - (注文ID)<BR>
     * 注文ID。<BR>
     * （切替対象の注文の注文ID）<BR>
     * @@param l_productType - (銘柄タイプ)<BR>
     * 銘柄タイプ。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 44923CC2024E
     */
    public void executeWLimitSwitch(
        SubAccount l_subAccount, 
        long l_lngOrderId, 
        ProductTypeEnum l_productType) throws WEB3BaseException;
}
@
