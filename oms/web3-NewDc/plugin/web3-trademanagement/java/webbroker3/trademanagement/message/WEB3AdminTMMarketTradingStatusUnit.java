head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.21.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTMMarketTradingStatusUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 市場別取引状況(WEB3AdminTMMarketTradingStatusUnit.java)
Author Name      : Daiwa Institute of Research
*/

package webbroker3.trademanagement.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.trademanagement.define.WEB3AdminTMTradeStopDivDef;

/**
 * （市場別取引状況）<BR>
 * <BR>
 * 市場別取引状況クラス<BR>
 * <BR>
 * WEB3AdminTMMarketTradingStatusUnit<BR>
 * <BR>
 * WEB3AdminTMMarketTradingStatusUnit class<BR>
 * <BR>
 * @@author Prabhu
 * @@version 1.0
 */
public class WEB3AdminTMMarketTradingStatusUnit extends Message
{
    /**
    * ログ出力ユーティリティ。
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTMMarketTradingStatusUnit.class);
    /**
     * （市場コード）<BR>
     * <BR>
     * 市場コード<BR>
     * <BR>
     * 0：　@その他<BR>
     * 1：　@東京<BR>
     * 2：　@大阪<BR>
     * 3：　@名古屋<BR>
     * 6：　@福岡<BR>
     * 8：　@札幌<BR>
     * 9：　@NNM<BR>
     * 10：　@JASDAQ<BR>
     * (WEB3MarketCodeDef.javaにて定義)<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * marketCode<BR>
     * <BR>
     * 0：　@Def.DEFAULT<BR>
     * 1：　@Def.TOKYO<BR>
     * 2：　@Def.OSAKA<BR>
     * 3：　@Def.NAGOYA<BR>
     * 6：　@Def.FUKUOKA<BR>
     * 8：　@Def.SAPPORO<BR>
     * 9：　@Def.NNM<BR>
     * 10：　@Def.JASDAQ<BR>
     * (It defines it with WEB3MarketCodeDef.java. )<BR>
     */
    public String marketCode;

    /**
     * （市場名）<BR>
     * <BR>
     * 市場名<BR>
     * <BR>
     * marketName<BR>
     */
    public String marketName;

    /**
     * （取引停止区分）<BR>
     * <BR>
     * 取引停止区分<BR>
     * <BR>
     * 0：　@取引可能<BR>
     * 1：　@取引停止<BR>
     * 2：　@取扱不可<BR>
     * <BR>
     * ※AP層で最新DBデータをセット。<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * tradeStopDiv<BR>
     * <BR>
     * 0 : Def.NORMAL<BR>
     * 1 : Def.SUSPENTION<BR>
     * 2 : "DISABLE"<BR>
     * <BR>
     * ※The latest DB data is set in the AP layer. <BR>
     * <BR>
     */
    public String tradeStopDiv;

    /**
     * （変更後取引停止区分）<BR>
     * 変更後の取引停止区分
     * <BR>
     * 0：　@取引可能<BR>
     * 1：　@取引停止<BR>
     * 2：　@取扱不可<BR>
     * <BR>
     * ※PR層での入力値をセット。<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * afterTradeStopDiv<BR>
     * <BR>
     * 0 : Def.NORMAL<BR>
     * 1 : Def.SUSPENTION<BR>
     * 2 : "DISABLE"<BR>
     * <BR>
     * ※The input value in PR layer is set.<BR>
     */
    public String afterTradeStopDiv = null;

    /**
     * （コンストラクタ）<BR>
     * <BR>
     * コンストラクタ<BR>
     * <BR>
     * @@roseuid 41776D3A010E
     */
    public WEB3AdminTMMarketTradingStatusUnit()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）市場コードチェック<BR>
     * 　@１－１）this.市場コード == nullの場合、<BR>
     * 　@　@　@　@　@「市場コードがnull」の例外をスローする。<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00443<BR>
     * <BR>
     * ２）取引停止区分チェック<BR>
     * 　@this.取引停止区分 != nullの場合、以下のチェックを行う。<BR>
     * 　@２－１）this.取引停止区分が以下の値のいづれにも該当しない場合、<BR>
     * 　@　@　@　@　@「取引停止区分エラー(未定義の値)」の例外をスローする。<BR>
     * 　@　@　@　@　@・"0：取引可能"<BR>
     * 　@　@　@　@　@・"1：取引停止"<BR>
     * 　@　@　@　@　@・"2：取扱不可"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01421<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * The correspondence of this class is checked<BR>
     * (However, it is assumed only when the simple check concluded in this class).
     * <BR>
     * <BR>
     * 1)marketCode check<BR>
     *   1-1)If this.marketCode == null<BR>
     *            Throw the following error [marketCode is null]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00443<BR>
     * <BR>
     * 2)tradeStopDiv check<BR>
     *   2-1)If this.tradeStopDiv != null<BR>
     *     2-1-1)If tradeStopDiv = 0 : Def.NORMAL<BR>
     *                 tradeStopDiv = 1 : Def.SUSPENTION<BR>
     *                 tradeStopDiv = 2 : "DISABLE"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01421<BR>
     * @@roseuid 41775BA50341
     * @@throws WEB3BaseException WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        // 1-1 marketCode = null, throw Exception
        if (this.marketCode == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00443,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // 2-1 if tradeStopDiv != null, throw Exception.
        if (this.tradeStopDiv != null)
        {
            // 2-1-1 if tradeStopDiv != NORMAL, SUSPENTION, DISABLE, throw Exception.
            if ((!WEB3AdminTMTradeStopDivDef.NORMAL.equals(this.tradeStopDiv))
                && (!WEB3AdminTMTradeStopDivDef.SUSPENTION.equals(this.tradeStopDiv))
                && (!WEB3AdminTMTradeStopDivDef.DISABLE.equals(this.tradeStopDiv)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01421,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        log.exiting(STR_METHOD_NAME);
    }
}@
