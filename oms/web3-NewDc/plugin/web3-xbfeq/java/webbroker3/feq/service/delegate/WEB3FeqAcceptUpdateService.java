head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqAcceptUpdateService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式受付更新サービス(WEB3FeqAcceptUpdateService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/19 呉艶飛(中訊) 新規作成
                 : 2005/07/26 王煜(中訊) レビュー
                 : 2006/12/19 徐宏偉(中訊) モデル　@No.314対応
*/
package webbroker3.feq.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.MarketResponseMessage;

import webbroker3.common.WEB3BaseException;

/**
 * (外国株式受付更新サービス) <BR>
 * 外国株式受付更新サービスインタフェイス
 * 
 * @@ author 呉艶飛 
 * @@ version 1.0
 */
public interface WEB3FeqAcceptUpdateService extends Service
{
    
    /**
     * (update受付) <BR>
     * 注文受付更新処理を行う。
     * @@param l_marketResponseMessage - (市場レスポンスメッセージ)
     * @@throws WEB3BaseException
     * @@roseuid 42A579180083
     */
    public void updateAccept(MarketResponseMessage l_marketResponseMessage) throws WEB3BaseException;

    /**
     * (get市場レスポンスメッセージ)<BR>
     * 変更後受付区分に対応する市場レスポンスメッセージを生成し返却する。<BR>
     * <BR>
     * @@param l_lngOrderId - (注文ＩＤ)<BR>
     * 注文ＩＤ<BR>
     * @@param l_strAfterChangeAcceptDiv - (変更後受付区分)<BR>
     * 変更後受付区分<BR>
     * <BR>
     * 01：注文受付済 <BR>
     * 02：注文受付エラー <BR>
     * 03：注文受付済取消<BR>
     * <BR>
     * 11：訂正済 <BR>
     * 12：訂正エラー<BR>
     * <BR>
     * 21：取消済 <BR>
     * 22：取消エラー<BR>
     * <BR>
     * 31：出来ず<BR>
     * @@return MarketResponseMessage
     */
    public MarketResponseMessage getMarketResponseMessage(
        long l_lngOrderId, String l_strAfterChangeAcceptDiv);
}
@
