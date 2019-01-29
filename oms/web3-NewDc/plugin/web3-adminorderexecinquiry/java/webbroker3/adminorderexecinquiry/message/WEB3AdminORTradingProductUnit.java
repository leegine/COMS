head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.45.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminORTradingProductUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 取扱商品(WEB3AdminORTradingProductUnit.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.adminorderexecinquiry.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (取扱商品)<BR>
 * <BR>
 * 取扱商品クラス<BR>
 * <BR>
 * WEB3AdminORTradingProductUnit<BR>
 * <BR>
 * @@author Arpan
 * @@version 1.0
 */
public class WEB3AdminORTradingProductUnit extends Message
{
    /**
     * (商品区分)<BR>
     * <BR>
     * 商品区分<BR>
     * <BR>
     * 0：　@現物株式<BR>
     * 1：　@信用取引<BR>
     * 2：　@株式ミニ投資<BR>
     * 3：　@オプション<BR>
     * 4：　@先物<BR>
     * 5：　@投信<BR>
     * 6：　@累投<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * productDiv<BR>
     * 0: Def.EQUITY<BR>
     * 1: Def.MARGIN<BR>
     * 2: Def.MINI_STOCK<BR>
     * 3: Def.OPTION<BR>
     * 4: Def.FUTURE<BR>
     * 5: Def.MF<BR>
     * 6: Def.RUITO<BR>
     * <BR>
     */
    public String productDiv;

    /**
     * (取引区分)<BR>
     * <BR>
     * 取引区分<BR>
     * <BR>
     * 1：　@現物買付注文<BR>
     * 2：　@現物売付注文<BR>
     * 3：　@新規買建注文<BR>
     * 4：　@新規売建注文<BR>
     * 5：　@買建返済注文<BR>
     * 6：　@売建返済注文<BR>
     * 7：　@現引注文<BR>
     * 8：　@現渡注文<BR>
     * 99：　@立会外分売<BR>
     * 101：　@ミニ株買付注文<BR>
     * 102：　@ミニ株売付注文<BR>
     * 201：　@投資信託買付注文<BR>
     * 202：　@投資信託売付注文<BR>
     * 203：　@投資信託募集注文<BR>
     * 204：　@投資信託乗換注文<BR>
     * 501：　@累投買付注文<BR>
     * 502：　@累投売付注文<BR>
     * 601：　@指数先物新規買建注文<BR>
     * 602：　@指数先物新規売建注文<BR>
     * 603：　@指数先物売建返済注文<BR>
     * 604：　@指数先物買建返済注文<BR>
     * 605：　@指数オプション新規買建注文<BR>
     * 606：　@指数オプション新規売建注文<BR>
     * 607：　@指数オプション売建返済注文<BR>
     * 608：　@指数オプション買建返済注文<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * tradingType<BR>
     * 1: Def.EQUITY_BUY<BR>
     * 2: Def.EQUITY_SELL<BR>
     * 3: Def.MARGIN_LONG<BR>
     * 4: Def.MARGIN_SHORT<BR>
     * 5: Def.CLOSE_MARGIN_LONG<BR>
     * 6: Def.CLOSE_MARGIN_SHORT<BR>
     * 7: Def.SWAP_MARGIN_LONG<BR>
     * 8: Def.SWAP_MARGIN_SHORT<BR>
     * 99: Def.SALES_OUTSIDE_MARKET <BR>
     * 101: Def.MINI_STOCK_BUY<BR>
     * 102: Def.MINI_STOCK_SELL<BR>
     * 201: Def.MF_BUY<BR>
     * 202: Def.MF_SELL<BR>
     * 501: Def.RUITO_BUY<BR>
     * 502: Def.RUITO_SELL<BR>
     * 601: Def.IDX_FUTURES_BUY_TO_OPEN<BR>
     * 602: Def.IDX_FUTURES_SELL_TO_OPEN<BR>
     * 603: Def.IDX_FUTURES_BUY_TO_CLOSE<BR>
     * 604: Def.IDX_FUTURES_SELL_TO_CLOSE<BR>
     * 605: Def.IDX_OPTIONS_BUY_TO_OPEN<BR>
     * 606: Def.IDX_OPTIONS_SELL_TO_OPEN<BR>
     * 607: Def.IDX_OPTIONS_BUY_TO_CLOSE<BR>
     * 608: Def.IDX_OPTIONS_SELL_TO_CLOSE<BR>
     * <BR>
     */
    public String tradingType;

    /**
     * @@roseuid 4212FD860392
     */
    public WEB3AdminORTradingProductUnit()
    {

    }
}
@
