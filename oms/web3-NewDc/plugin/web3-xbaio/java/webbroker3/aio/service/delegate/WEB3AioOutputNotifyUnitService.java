head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.15.30;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioOutputNotifyUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 出庫通知UnitService(WEB3AioOutputNotifyUnitService)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/05 韋念瓊 (中訊) 新規作成   
*/

package webbroker3.aio.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;

import webbroker3.aio.data.HostSpsecTransNotifyParams;
import webbroker3.common.WEB3BaseException;


/**
 * (出庫通知UnitService)<BR>
 * 出庫通知UnitServiceインターフェイス
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public interface WEB3AioOutputNotifyUnitService extends Service 
{
    
    /**
     * (notify出庫通知)<BR>
     * 出庫通知処理を行う。
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCode - 部店コード
     * @@param l_strAccountCode - 顧客コード
     * @@param l_strProductCode - 銘柄コード
     * @@param l_strCustodyDiv - 保管区分
     * @@param l_strSpecificDiv - 特定口座区分
     * @@param l_lngQuantity - 数量
     * @@param l_strCommodityDiv - 商品区分
     * @@param l_strCancelDiv - 取消区分
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 4157961901F5
     */
    public String notifyOutputNotify(
        String l_strInstitutionCode, 
        String l_strBranchCode, 
        String l_strAccountCode, 
        String l_strProductCode, 
        String l_strCustodyDiv, 
        String l_strSpecificDiv, 
        Long l_lngQuantity,
	    String l_strCommodityDiv,
	    String l_strCancelDiv) 
        throws WEB3BaseException;
}
@
