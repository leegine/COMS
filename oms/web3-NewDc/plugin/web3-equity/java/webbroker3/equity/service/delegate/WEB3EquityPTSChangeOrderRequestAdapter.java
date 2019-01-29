head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityPTSChangeOrderRequestAdapter.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (PTS)現物株式注文訂正リクエストアダプタ(WEB3EquityPTSChangeOrderRequestAdapter.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/12/18 張騰宇 (中訊) 新規作成 モデル1241 1250
Revision History : 2008/02/13 トウ鋒鋼 (中訊) 仕様変更モデルNo.1299
*/
package webbroker3.equity.service.delegate;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.equity.WEB3EquityPTSTradingTimeManagement;
import webbroker3.equity.message.WEB3EquityChangeCompleteRequest;
import webbroker3.equity.message.WEB3EquityChangeConfirmRequest;
import webbroker3.util.WEB3LogUtility;


/**
 * ((PTS)現物株式注文訂正リクエストアダプタ)<BR>
 * 画面に依存した処理をラップするアダプタクラス。 <BR>
 * <BR>
 * 当該クラスは、各証券会社の画面用件によって、複数作成されることを前提とする。<BR>
 *
 * @@author 張騰宇
 * @@version 1.0
 */
public class WEB3EquityPTSChangeOrderRequestAdapter extends WEB3EquityChangeOrderRequestAdapter
{
    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityPTSChangeOrderRequestAdapter.class);

    /**
     * @@roseuid 4766071F0092
     */
    protected WEB3EquityPTSChangeOrderRequestAdapter()
    {

    }

    /**
     * (PTS)現物株式注文訂正リクエストアダプタインスタンスを生成する。 <BR>
     * （継承元クラスの同名メソッドのオーバーライド） <BR>
     * <BR>
     * １）　@本インスタンスを生成する。 <BR>
     * ２）　@生成したインスタンスに引数のリクエストデータをセットする。 <BR>
     * ３）　@インスタンスを返却する。 <BR>
     * <BR>
     * （デフォルトコンストラクタはprotectedとし、<BR>
     * 　@本メソッドによってインスタンス化するように制限する）<BR>
     * @@param l_request - (リクエスト)<BR>
     * リクエストデータオブジェクト<BR>
     * @@return WEB3EquityChangeOrderRequestAdapter
     * @@roseuid 474B76140205
     */
    public static WEB3EquityChangeOrderRequestAdapter create(WEB3GenRequest l_request)
    {
        final String STR_METHOD_NAME = "create(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        //１）　@本インスタンスを生成する。
        WEB3EquityPTSChangeOrderRequestAdapter l_adapter =
            new WEB3EquityPTSChangeOrderRequestAdapter();

        //２）　@生成したインスタンスに引数のリクエストデータをセットする。
        if (l_request instanceof WEB3EquityChangeConfirmRequest)
        {
            l_adapter.requestData = (WEB3EquityChangeConfirmRequest)l_request;
        }
        else if (l_request instanceof WEB3EquityChangeCompleteRequest)
        {
            l_adapter.requestData = (WEB3EquityChangeCompleteRequest)l_request;
        }

        //３）　@インスタンスを返却する
        log.exiting(STR_METHOD_NAME);
        return l_adapter;
    }

    /**
     * (get訂正後注文失効日)<BR>
     * リクエストデータ.注文有効期限より、 <BR>
     * AP層で使用する訂正後注文失効日を返却する。 <BR>
     * （継承元クラスの同名メソッドのオーバーライド） <BR>
     * <BR>
     * PTS取引時間管理.get発注日( )の戻り値を返却する。<BR>
     * @@return Date
     * @@throws WEB3BaseException
     * @@roseuid 474D0D8800F3
     */
    public Date getExpirationDate() throws WEB3BaseException
    {
        return WEB3EquityPTSTradingTimeManagement.getOrderBizDate();
    }
}
@
