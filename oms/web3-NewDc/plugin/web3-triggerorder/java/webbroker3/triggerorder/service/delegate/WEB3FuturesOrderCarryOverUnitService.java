head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.01.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3FuturesOrderCarryOverUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物注文繰越１件サービス実装クラス(WEB3FuturesOrderCarryOverUnitService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/7/20 艾興 (中訊) 新規作成
Revesion History : 2008/04/11 趙林鵬 (中訊) モデルNo.277,278
*/
package webbroker3.triggerorder.service.delegate;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;

import webbroker3.common.WEB3BaseException;


/**
 * (先物注文繰越１件サービス)<BR>
 * 先物注文繰越１件サービスインタフェイス<BR>
 * <BR>
 * １件ごとの注文繰越処理を実施する。<BR>
 */
public interface WEB3FuturesOrderCarryOverUnitService extends Service 
{
    
    /**
     * (create新規建翌日注文)<BR>
     * 翌日注文（新規建）を作成する。<BR>
     * <BR>
     * @@param l_orderUnit - 注文単位オブジェクト<BR>
     * @@param l_lisRsvIfoOrderUnits - (予約注文単位一覧)<BR>
     * 予約注文単位一覧
     * @@roseuid 409B517B0253
     */
    public void createOpenContractNextOrder(OrderUnit l_orderUnit, List l_lisRsvIfoOrderUnits)
        throws WEB3BaseException;
    
    /**
     * (create返済翌日注文)<BR>
     * 翌日注文（返済）を作成する。<BR>
     * <BR>
     * @@param l_orderUnit - 注文単位オブジェクト<BR>
     * @@param l_lisRsvIfoOrderUnits - (予約注文単位一覧)<BR>
     * 予約注文単位一覧<BR>
     * @@roseuid 409B5420030F
     */
    public void createSettleContractNextOrder(OrderUnit l_orderUnit, List l_lisRsvIfoOrderUnits)
        throws WEB3BaseException;
    
    /**
     * (update繰越元注文)<BR>
     * 繰越元注文を更新する。<BR>
     * @@param l_orderUnit - （繰越元）注文単位オブジェクト<BR>
     * @@param l_strOrderErrorReasonCode - 注文エラー理由コード<BR>
     * @@roseuid 40A0B87B01F3
     */
    public void updateCarryOverOriginOrder(
        OrderUnit l_orderUnit,
        String l_strOrderErrorReasonCode)
        throws DataQueryException, DataNetworkException, IllegalStateException;
        
    /**
     * (expire繰越元注文)<BR>
     * <BR>
     * @@param l_orderUnit - 注文単位オブジェクト
     * @@return boolean
     * @@roseuid 409B497C0205
     */
    public void expireCarryOverOriginOrder(OrderUnit l_orderUnit) throws WEB3BaseException;
}
@
