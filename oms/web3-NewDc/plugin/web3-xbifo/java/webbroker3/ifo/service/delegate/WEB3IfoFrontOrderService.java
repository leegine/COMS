head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.30;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoFrontOrderService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP発注サービス(WEB3IfoFrontOrderService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/01/25 何文敏 (中訊) 新規作成 仕様変更　@モデルNO.587
*/
package webbroker3.ifo.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.gentrade.WEB3GentradeOrderSwitching;
import webbroker3.ifo.data.HostFotypeOrderAllParams;

/**
 * (先物OP発注サービス)<BR>
 * 先物OP発注サービスのインタフェース。<BR>
 * @@author 何文敏
 * @@version 1.0
 */
public interface WEB3IfoFrontOrderService extends Service
{
    /**
     * (getフロント発注システム区分)<BR>
     * 引数の市場コードにより、フロント発注システム区分を取得し返却する。<BR>
     * <BR>
     * @@param l_strMarketCode - (市場コード)<BR>
     * WEBⅢの市場コード
     * @@return String
     */
    public String getFrontOrderSystemCode(String l_strMarketCode);

    /**
     * (get発注経路区分)<BR>
     * 発注可能な発注経路区分を取得し返却する。<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード
     * @@param l_strMarketCode - (市場コード)<BR>
     * WEBⅢの市場コード
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getSubmitOrderRouteDiv(String l_strInstitutionCode, String l_strMarketCode)
        throws WEB3BaseException;

    /**
     * (get注文Rev開始位置IN社内処理項目)<BR>
     * 社内処理項目の文字列中の、注文Rev.開始位置を返す。<BR>
     * <BR>
     * @@return int
     */
    public int getIndexOfOrderRevInCorpCode();

    /**
     * (get注文Rev桁数)<BR>
     * 注文Rev.の桁数を返す。<BR>
     * <BR>
     * @@return int
     */
    public int getFigureOfOrderRev();

    /**
     * (get社内処理項目)<BR>
     * 引数の注文単位オブジェクトより、発注に使用する「社内処理項目」設定用文字列を<BR>
     * 取得し返却する。<BR>
     * <BR>
     * @@param l_ifoOrderUnit - (注文単位)<BR>
     * 注文単位オブジェクト。
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getCorpCode(IfoOrderUnit l_ifoOrderUnit) throws WEB3BaseException;

    /**
     * (is市場通知中注文IN休憩時間帯)<BR>
     * 取引所が休憩時間中の時間帯において、<BR>
     * 指定の注文に関係するデータ（訂正、取消を含む）が市場に通知中であるかどうかを<BR>
     * 先物OP注文取引キューテーブルのデータより判定し返す。<BR>
     * <BR>
     * @@param l_ifoOrderUnit - (注文単位)<BR>
     * 注文単位オブジェクト。
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean isMarketNotifyingOrderInBreakTimeZone(IfoOrderUnit l_ifoOrderUnit)
        throws WEB3BaseException;

    /**
     * (get先物OP注文取引キュー)<BR>
     * 指定の注文単位に該当する先物OP注文取引キューを取得し返す。<BR>
     * 該当するデータが存在しない場合は、nullを返却する。<BR>
     * <BR>
     * @@param l_ifoOrderUnit - (注文単位)<BR>
     * 注文単位オブジェクト。
     * @@return 先物OP注文取引キューParams
     * @@throws WEB3BaseException
     */
    public HostFotypeOrderAllParams getHostFotypeOrderAll(IfoOrderUnit l_ifoOrderUnit)
        throws WEB3BaseException;

    /**
     * (get訂正時注文Rev)<BR>
     * 引数の訂正後注文単位オブジェクトより、<BR>
     * 訂正時に注文単位テーブル.注文Revに設定する文字列を取得し返す。<BR>
     * <BR>
     * @@param l_ifoOrderUnitAfter - (訂正後注文単位)<BR>
     * 訂正後の注文単位オブジェクト。<BR>
     * （xTrade標準項目に、訂正後の値が設定されているオブジェクト）
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getChangeOrderRev(IfoOrderUnit l_ifoAfterOrderUnit)
        throws WEB3BaseException;

    /**
     * (lock先物OP注文取引キュー)<BR>
     * 指定の注文単位の先物OP注文取引キューデータに共有ロックをかける。<BR>
     * <BR>
     * @@param l_ifoOrderUnit - (注文単位)<BR>
     * 注文単位オブジェクト。
     * @@throws WEB3BaseException
     */
    public void lockHostFotypeOrderAll(IfoOrderUnit l_ifoOrderUnit)
        throws WEB3BaseException;

    /**
     * (get発注時MQデータコード)<BR>
     * 発注経路区分、先物オプション区分により、発注時に使用するMQデータコード設定用文字列を取得し返却する。<BR>
     * <BR>
     * @@param l_ifoOrderUnit - (注文単位)<BR>
     * 注文単位
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getOrderMQDataCode(IfoOrderUnit l_ifoOrderUnit)
        throws WEB3BaseException;

    /**
     * (get訂正取消時MQデータコード)<BR>
     * 発注経路区分、先物オプション区分により、訂正取消時に使用するMQデータコード設定用文字列を取得し返却する。<BR>
     * <BR>
     * @@param l_ifoOrderUnit - (注文単位)<BR>
     * 注文単位
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getChangeCancelMQDataCode(IfoOrderUnit l_ifoOrderUnit)
        throws WEB3BaseException;

    /**
     * (get訂正取消時発注経路区分)<BR>
     * 引数の訂正取消対象の注文単位オブジェクトより、発注可能な発注経路区分を取得し返却する。<BR>
     * <BR>
     * @@param l_ifoOrderUnit - (注文単位)<BR>
     * 注文単位オブジェクト。
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getChangeSubmitOrderRouteDiv(IfoOrderUnit l_ifoOrderUnit)
        throws WEB3BaseException;

    /**
     * (get（被訂正）社内処理項目)<BR>
     * 引数の注文単位オブジェクトより、発注に使用する「（被訂正）社内処理項目」設定用文字列を取得し返却する。<BR>
     * <BR>
     * @@param l_ifoOrderUnit - (注文単位)<BR>
     * 注文単位オブジェクト。
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getOrgCorpCode(IfoOrderUnit l_ifoOrderUnit)
        throws WEB3BaseException;

    /**
     * (getフロント発注取引所区分コード)<BR>
     * 引数の市場コードより、フロント発注取引所区分コードを取得し返却する。<BR>
     * <BR>
     * @@param l_strMarketCode - (市場コード)<BR>
     * WebⅢの市場コード。
     * @@return String
     */
    public String getFrontOrderExchangeCode(String l_strMarketCode);  

    /**
     * (get発注先切替)<BR>
     * 指定の注文単位に合致する発注先切替オブジェクトを取得し返す<BR>
     * <BR>
     * @@param l_ifoOrderUnit - (注文単位)<BR>
     * 注文単位オブジェクト。
     * @@return 発注先切替
     * @@throws WEB3BaseException
     */
    public WEB3GentradeOrderSwitching getOrderSwitching(IfoOrderUnit l_ifoOrderUnit)
        throws WEB3BaseException;

    /**
     * (update先物OP注文取引キューAT受付時間外)<BR>
     * 前場受付時間外注文を再発注するためのキューデータ更新を行う。<BR>
     * <BR>
     * @@param l_orderUnitAfter - (注文単位（更新後）)<BR>
     * 更新後の注文単位オブジェクト。
     * @@param l_orderUnitBefore - (注文単位（更新前）)<BR>
     * 更新前の注文単位オブジェクト。
     * @@param l_blnIsCancel - (is取消)<BR>
     * 取消かどうかを判定するフラグ。
     * （true：取消、false：取消以外）
     * @@throws WEB3BaseException
     */
    public void updateHostFotypeOrderAllAtAcceptOvertime(
        IfoOrderUnit l_orderUnitAfter,
        IfoOrderUnit l_orderUnitBefore,
        boolean l_blnIsCancel) throws WEB3BaseException;

    /**
     * (getNext注文Rev)<BR>
     * 引数の注文Revに1加算した値を返す。<BR>
     * <BR>
     * @@param l_strOrderRev - (注文Rev)
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getNextOrderRev(String l_strOrderRev)
        throws WEB3BusinessLayerException;
}
@
