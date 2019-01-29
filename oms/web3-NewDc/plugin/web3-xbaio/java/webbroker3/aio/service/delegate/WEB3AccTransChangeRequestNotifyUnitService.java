head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.19.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AccTransChangeRequestNotifyUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 振替請求通知UnitService(WEB3AccTransChangeRequestNotifyUnitService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/13 于美麗 (中訊) 新規作成
                   2004/10/26 周勇(中訊) レビュー
*/

package webbroker3.aio.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AssetTransferTypeEnum;

import webbroker3.aio.data.HostTransferReceiptParams;
import webbroker3.common.WEB3BaseException;

/**
 * (振替請求通知UnitService)<BR>
 * 振替請求通知UnitServiceインターフェイス<BR>
 * 
 * @@author 于美麗(中訊)
 * @@version 1.0
 */
public interface WEB3AccTransChangeRequestNotifyUnitService extends Service
{

    /**
     * (submit注文)<BR>
     * SONARからの振替注文の登録を行い、新規注文の注文IDを返却する。<BR>
     * @@param l_hostTransferReceiptParams - (振替入力通知キューParamsオブジェクト)<BR>
     * @@param l_orderType - (注文種別)
     * @@param l_changeType - (振替タイプ)
     * @@return long
     * @@throws WEB3BaseException
     * @@roseuid 413C2CC401E5
     */
    public long submitOrder(
        HostTransferReceiptParams l_hostTransferReceiptParams,
        OrderTypeEnum l_orderType,
        AssetTransferTypeEnum l_changeType)
            throws WEB3BaseException;
}
@
