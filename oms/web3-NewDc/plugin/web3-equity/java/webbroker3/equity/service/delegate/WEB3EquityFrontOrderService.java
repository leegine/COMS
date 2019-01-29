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
filename	WEB3EquityFrontOrderService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式発注サービス(WEB3EquityFrontOrderService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 中尾寿彦 (SRA) 新規作成
Revesion History : 2007/12/17 張騰宇 モデル 1243
*/
package webbroker3.equity.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.equity.data.HostEqtypeOrderAllParams;
import webbroker3.gentrade.WEB3GentradeOrderSwitching;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradedProduct;

/**
 * (株式発注サービス)<BR>
 * <BR>
 * 株式発注サービスのインタフェース。
 * @@version 1.0
 */
public interface WEB3EquityFrontOrderService extends Service
{
    /**
     * (get発注経路区分)<BR>
     * 引数の株式取引銘柄オブジェクト等より、発注可能な発注経路区分を取得し返却する。<BR>
     * @@param l_tradedProduct - (株式取引銘柄)<BR>
     * 株式取引銘柄オブジェクト。
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード。
     * @@param l_strSonarTradedCode - (取引コード（SONAR）)<BR>
     * 取引コード（SONAR）。<BR>
     * <BR>
     * 11：普通株式<BR>
     * 16：立会外分売<BR>
     * 51：信用建<BR>
     * 52：信用返済<BR>
     * 53：現引現渡<BR>
     * 92：場外取引(＝JASDAQ)
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getSubmitOrderRouteDiv(
        EqTypeTradedProduct l_tradedProduct,
        String l_strInstitutionCode,
        String l_strSonarTradedCode)
        throws WEB3BaseException;
    
    /**
     * (get訂正取消時発注経路区分)<BR>
     * 引数の訂正取消対象の注文単位オブジェクトより、発注可能な発注経路区分を取得し返却する。<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト。
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getChangeSubmitOrderRouteDiv(
        EqTypeOrderUnit l_orderUnit)
        throws WEB3BaseException;
    
    /**
     * (get発注先切替)<BR>
     * 指定の注文単位に合致する発注先切替オブジェクトを取得し返す。<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト。
     * @@return 発注先切替
     * @@throws WEB3BaseException
     */
    public WEB3GentradeOrderSwitching getOrderSwitching(
        EqTypeOrderUnit l_orderUnit)
        throws WEB3BaseException;
    
    /**
     * (getフロント発注システム区分)<BR>
     * 引数の市場コード及び店頭公開区分より、フロント発注システム区分を取得し返却する。<BR>
     * @@param l_strMarketCode - (市場コード)<BR>
     * WebⅢの市場コード。
     * @@param l_strOpenOtcDiv - (店頭公開区分)<BR>
     * 店頭公開区分。<BR>
     * （0：DEFAULT　@1：登録　@3：マーケットメイク銘柄） 
     * @@return String
     */
    public String getFrontOrderSystemCode(
        String l_strMarketCode,
        String l_strOpenOtcDiv);
    
    /**
     * (getフロント発注取引所区分コード )<BR>
     * 引数の市場コードより、フロント発注取引所区分コードを取得し返却する。<BR>
     * @@param l_strMarketCode - (市場コード)<BR>
     * WebⅢの市場コード。
     * @@return String
     */
    public String getFrontOrderExchangeCode(String l_strMarketCode);
    
    /**
     * (get社内処理項目)<BR>
     * 引数の注文単位オブジェクトより、発注に使用する「社内処理項目」設定用文字列を<BR>
     * 取得し返却する。<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト。
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getCorpCode(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException;
    
    /**
     * (get（被訂正）社内処理項目)<BR>
     * 引数の注文単位オブジェクトより、発注に使用する「（被訂正）社内処理項目」設定用文字列を<BR>
     * 取得し返却する。<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト。
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getOrgCorpCode(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException;
    
    /**
     * (get注文Rev開始位置IN社内処理項目)<BR>
     * 社内処理項目の文字列中の、注文Rev.開始位置を返す。<BR>
     * @@return int
     */
    public int getIndexOfOrderRevInCorpCode();
    
    /**
     * (get注文Rev桁数)<BR>
     * 注文Rev.の桁数を返す。<BR>
     * @@return int
     */
    public int getFigureOfOrderRev();
    
    /**
     * (get訂正時注文Rev)<BR>
     * 引数の訂正後注文単位オブジェクトより、<BR>
     * 訂正時に注文単位テーブル.注文Revに設定する文字列を取得し返す。<BR>
     * @@param l_orderUnit - (訂正後注文単位)<BR>
     * 訂正後の注文単位オブジェクト。<BR>
     * （xTrade標準項目に、訂正後の値が設定されているオブジェクト）<BR>
     * ※更新インタセプタ.mutate()内部からコールされることを前提としている。<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getChangeOrderRev(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException;
    
    /**
     * (get発注時MQデータコード)<BR>
     * 引数の発注経路区分より、発注時に使用するMQデータコード設定用文字列を<BR>
     * 取得し返却する。<BR>
     * @@param l_strSubmitOrderRouteDiv - (発注経路区分)<BR>
     * 発注経路区分。
     * @@return String
     */
    public String getOrderMQDataCode(String l_strSubmitOrderRouteDiv);
    
    /**
     * (get訂正取消時MQデータコード)<BR>
     * 引数の発注経路区分より、訂正取消時に使用するMQデータコード設定用文字列を<BR>
     * 取得し返却する。<BR>
     * @@param l_strSubmitOrderRouteDiv - (発注経路区分)<BR>
     * 発注経路区分。
     * @@return String
     */
    public String getChangeCancelMQDataCode(String l_strSubmitOrderRouteDiv);
    
    /**
     * (is市場通知中注文IN休憩時間帯)<BR>
     * 取引所が休憩時間中の時間帯において、<BR>
     * 指定の注文に関係するデータ（訂正、取消を含む）が市場に通知中であるかどうかを<BR>
     * 株式注文取引キューテーブルのデータより判定し返す。<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト。
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean isMarketNotifyingOrderInBreakTimeZone(
        EqTypeOrderUnit l_orderUnit)
        throws WEB3BaseException;
    
    /**
     * (lock株式注文取引キュー)<BR>
     * 指定の注文単位の株式注文取引キューデータに共有ロックをかける。<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト。
     * @@throws WEB3BaseException
     */
    public void lockHostEqtypeOrderAll(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException;
    
    /**
     * (get株式注文取引キュー)<BR>
     * 指定の注文単位に該当する株式注文取引キューを取得し返す。<BR>
     * 該当するデータが存在しない場合は、nullを返却する。<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト。
     * @@return HostEqtypeOrderAllParams
     * @@throws WEB3BaseException
     */
    public HostEqtypeOrderAllParams getHostEqtypeOrderAll(
        EqTypeOrderUnit l_orderUnit)
        throws WEB3BaseException;
    
    /**
     * (update株式注文取引キューAT受付時間外)<BR>
     * 前場受付時間外注文を再発注するためのキューデータ更新を行う。
     * @@param l_orderUnitAfter - (注文単位（更新後）)<BR>
     * 更新後の注文単位オブジェクト。
     * @@param l_orderUnitBefore - (注文単位（更新前）)<BR>
     * 更新前の注文単位オブジェクト。
     * @@param l_blnIsCancel - (is取消)<BR>
     * 取消かどうかを判定するフラグ。<BR>
     * （true：取消、false：取消以外）
     * @@throws WEB3BaseException
     */
    public void updateHostEqtypeOrderAllAtAcceptOvertime(
        EqTypeOrderUnit l_orderUnitAfter,
        EqTypeOrderUnit l_orderUnitBefore,
        boolean l_blnIsCancel)
        throws WEB3BaseException;
    
    /**
     * (getNext注文Rev)<BR>
     * 引数の注文Revに1加算した値を返す。<BR>
     * @@param l_strOrderRev - (注文Rev)<BR>
     * 訂正後の注文単位オブジェクト。<BR>
     * @@return String
     * @@throws WEB3BusinessLayerException
     */
    public String getNextOrderRev(String l_strOrderRev) throws WEB3BusinessLayerException;
    
    /**
     * (getPTS訂正時注文Rev )<BR>
     * 引数の訂正後注文単位オブジェクトより、 <BR> 
     * 訂正時に注文単位テーブル.注文Revに設定する文字列を取得し返す。  <BR>
     * （PTS注文の場合にコールされる。）<BR>
     * @@param l_orderUnit - (訂正後注文単位)<BR>
     * 訂正後の注文単位オブジェクト。  <BR>
     * （xTrade標準項目に、訂正後の値が設定されているオブジェクト）  <BR>
     * ※更新インタセプタ.mutate()内部からコールされることを前提としている。<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getPTSChangeOrderRev(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException;
}
@
