head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.22.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTMMStopStartChgConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・市場別取引停止再開変更完了リクエスト(WEB3AdminTMMStopStartChgConfirmRequest.java)
Author Name      : Daiwa Institute of Research
*/

package webbroker3.trademanagement.message;

import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * （管理者・市場別取引停止再開変更確認リクエスト）<BR>
 * <BR>
 * 管理者・市場別取引停止再開変更確認リクエストクラス<BR>
 * <BR>
 * WEB3AdminTMMStopStartChgConfirmRequest<BR>
 * <BR>
 * WEB3AdminTMMStopStartChgConfirmRequest class<BR>
 * <BR>
 * @@author Prabhu
 * @@version 1.0
 */
public class WEB3AdminTMMStopStartChgConfirmRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_tmm_stop_start_chg_confirm";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200501071345L;

    /**
    * ログ出力ユーティリティ。
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTMMStopStartChgConfirmRequest.class);

    /**
     * (市場別取引状況一覧)
     * <BR>
     * 市場別取引状況一覧<BR>
     */
    public WEB3AdminTMMarketTradingStatusUnit[] marketTradingStatusList;

    /**
     * @@roseuid 41DD3A2A0169
     */
    public WEB3AdminTMMStopStartChgConfirmRequest()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）市場別取引状況一覧のチェック<BR>
     * 　@１－１）this.市場別取引状況一覧 == nullの場合、<BR>
     * 　@　@　@　@　@「市場別取引状況一覧がnull」の例外をスローする。<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01422<BR>
     * <BR>
     * 　@１－２）this.市場別取引状況一覧.length == 0の場合、<BR>
     * 　@　@　@　@　@「市場別取引状況一覧の要素数が0」の例外をスローする。<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01423<BR>
     * <BR>
     * 　@１－３）this.市場別取引状況一覧.lengthの数分、<BR>
     * 　@　@　@　@　@以下の処理を繰り返す。<BR>
     * 　@　@　@　@　@１－３－１）市場別取引状況.validate()をコールする。<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * The correspondence of this class is checked<BR>
     * (However, it is assumed only when the simple check concluded in this class).
     * <BR>
     * <BR>
     * 1)marketTradingStatusList check<BR>
     *   1-1)If marketTradingStatusList == null<BR>
     *            Throw the following error [marketTradingStatusList is null]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01422<BR>
     * <BR>
     *   1-2)If this.marketTradingStatusList.length == 0<BR>
     *            Throw the following error [marketTradingStatusList has 0
     * elements]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01423<BR>
     * <BR>
     *   1-3)Loop for as many marketTradingStatusList elements<BR>
     *     1-3-1)Call WEB3AdminTMMarketTradingStatusUnit.validate<BR>
     * @@roseuid 41775B51013D
     * @@throws WEB3BaseException WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        int l_marketTradingStatusListlength = 0;
        WEB3AdminTMMarketTradingStatusUnit l_marketTradingStatusUnit = null;

        // 1-1marketTradingStatusList = null, throw Exception.
        if (this.marketTradingStatusList == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01422,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        } else
        {
            l_marketTradingStatusListlength = this.marketTradingStatusList.length;

            // 1-2 l_marketTradingStatusListlength = 0, throw Exception.
            if (l_marketTradingStatusListlength == 0)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01423,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            // 1-3 Loop for as many marketTradingStatusList elements.
            for (int i = 0; i < l_marketTradingStatusListlength; i++)
            {
                l_marketTradingStatusUnit = this.marketTradingStatusList[i];

                // 1-3-1 Call WEB3AdminTMMarketTradingStatusUnit.validate.
                l_marketTradingStatusUnit.validate();
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * レスポンスデータを作成する。<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminTMMStopStartChgConfirmResponse(this);
    }
}
@
