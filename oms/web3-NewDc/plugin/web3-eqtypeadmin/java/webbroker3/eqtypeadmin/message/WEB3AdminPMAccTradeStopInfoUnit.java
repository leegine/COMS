head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminPMAccTradeStopInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 顧客取引停止情報 (WEB3AdminPMAccTradeStopInfoUnit.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.message;

import com.fitechlabs.xtrade.kernel.message.Message;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;

import webbroker3.util.WEB3LogUtility;

import webbroker3.eqtypeadmin.define.WEB3AdminEquityTradeStopDivDef;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;

/**
 * (顧客取引停止情報)<BR>
 * <BR>
 * 顧客取引停止情報クラス<BR>
 * <BR>
 * WEB3AdminPMAccTradeStopInfoUnit<BR>
 * <BR>
 * @@author Sudhindrakinnal
 * @@version 1.0
 */
public class WEB3AdminPMAccTradeStopInfoUnit extends Message
{
    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminPMAccTradeStopInfoUnit.class);

    /**
     * （注文種別）<BR>
     * <BR>
     * 注文種別<BR>
     * <BR>
     * 1：　@株式現物買注文<BR>
     * 2：　@株式現物売注文<BR>
     * 3：　@株式信用新規買建注文<BR>
     * 4：　@株式信用新規売建注文<BR>
     * 5：　@株式信用買建返済注文<BR>
     * 6：　@株式信用売建返済注文<BR>
     * 7：　@株式信用現引注文<BR>
     * 8：　@株式信用現渡注文<BR>
     * 101：　@株式ミニ株買注文<BR>
     * 102：　@株式ミニ株売注文<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * orderType<BR>
     * <BR>
     * 1: Def.EQUITY_BUY<BR>
     * 2: Def.EQUITY_SELL<BR>
     * 3: Def.MARGIN_LONG<BR>
     * 4: Def.MARGIN_SHORT<BR>
     * 5: Def.CLOSE_MARGIN_LONG<BR>
     * 6: Def.CLOSE_MARGIN_SHORT<BR>
     * 7: Def.CLOSE_MARGIN_SHORT<BR>
     * 8: Def.SWAP_MARGIN_SHORT<BR>
     * 101: Def.MINI_STOCK_BUY<BR>
     * 102: Def.MINI_STOCK_SELL<BR>
     * <BR>
     */
    public String orderType;

    /**
     * （取引停止区分）<BR>
     * <BR>
     * 取引停止区分<BR>
     * <BR>
     * 0：　@取引可能<BR>
     * 1：　@取引停止<BR>
     * <BR>
     * --------<English>---------<BR>
     * <BR>
     * tradeStopDiv<BR>
     * <BR>
     * 0: Def.NORMAL<BR>
     * 1: Def.SUSPENTION<BR>
     * <BR>
     */
    public String tradeStopDiv;

    /**
     * （変更後取引停止区分）<BR>
     * <BR>
     * 変更後取引停止区分<BR>
     * <BR>
     * 0：　@取引可能<BR>
     * 1：　@取引停止<BR>
     * <BR>
     * --------<English>---------<BR>
     * <BR>
     * aftTradeStopDiv<BR>
     * <BR>
     * 0: Def.NORMAL<BR>
     * 1: Def.SUSPENTION<BR>
     * <BR>
     */
    public String aftTradeStopDiv = null;

    /**
     * (コンストラクタ)<BR>
     * <BR>
     * コンストラクタ<BR>
     * <BR>
     * Constructor<BR>
     * <BR>
       eqtypeadmin.message.WEB3AdminPMAccTradeStopInfoUnit
     * @@roseuid 41ABE2DA0222
     */
    public WEB3AdminPMAccTradeStopInfoUnit()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）注文種別チェック<BR>
     * 　@１－１）this.注文種別 == nullの場合、<BR>
     * 　@　@　@　@　@「注文種別がnull」の例外をスローする。<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01438<BR>
     * <BR>
     * 　@１－２）this.注文種別が以下の値以外の場合、<BR>
     * 　@　@　@　@　@「注文種別が未定義の値」の例外をスローする。<BR>
     * 　@　@　@　@　@・"1：株式現物買注文"<BR>
     * 　@　@　@　@　@・"2：株式現物売注文"<BR>
     * 　@　@　@　@　@・"3：株式信用新規買建注文"<BR>
     * 　@　@　@　@　@・"4：株式信用新規売建注文"<BR>
     * 　@　@　@　@　@・"5：株式信用買建返済注文"<BR>
     * 　@　@　@　@　@・"6：株式信用売建返済注文"<BR>
     * 　@　@　@　@　@・"7：株式信用現引注文"<BR>
     * 　@　@　@　@　@・"8：株式信用現渡注文"<BR>
     * 　@　@　@　@　@・"101：株式ミニ株買注文"<BR>
     * 　@　@　@　@　@・"102：株式ミニ株売注文"<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01439<BR>
     * <BR>
     * ２）取引停止区分チェック<BR>
     * 　@２－１）this.取引停止区分 == nullの場合、<BR>
     * 　@　@　@　@　@「取引停止区分がnull」の例外をスローする。<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01894<BR>
     * <BR>
     * 　@２－２）this.取引停止区分が以下の値以外の場合、<BR>
     * 　@　@　@　@　@「取引停止区分が未定義の値」の例外をスローする。<BR>
     * 　@　@　@　@　@　@・"0：取引可能"<BR>
     * 　@　@　@　@　@　@・"1：取引停止"<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01421<BR>
     * <BR>
     * ３）変更後取引停止区分チェック<BR>
     * 　@this.変更後取引停止区分 != nullの場合、<BR>
     * 　@以下のチェックを行う。<BR>
     * 　@３－１）this.変更後取引停止区分が以下の値以外の場合、<BR>
     * 　@　@　@　@　@「取引停止区分が未定義の値」の例外をスローする。<BR>
     * 　@　@　@　@　@　@・"0：取引可能"<BR>
     * 　@　@　@　@　@　@・"1：取引停止"<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01421<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * Check l_request<BR>
     * (However, it is assumed only when the simple check concluded in this class).
     * <BR>
     * <BR>
     * 1) orderType check<BR>
     * 　@1-1) If this.orderType == null<BR>
     * 　@　@　@　@　@Throw the exception "orderType is null"<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01438<BR>
     * <BR>
     * 　@1-2)If this.orderType is not either of the following values<BR>
     * 　@　@　@　@　@Throw the exception "orderType is a undefined value"<BR>
     * 　@　@　@　@　@・"1: Def.EQUITY_BUY"<BR>
     * 　@　@　@　@　@・"2: Def.EQUITY_SELL"<BR>
     * 　@　@　@　@　@・"3: Def.MARGIN_LONG"<BR>
     * 　@　@　@　@　@・"4: Def.MARGIN_SHORT"<BR>
     * 　@　@　@　@　@・"5: Def.CLOSE_MARGIN_LONG"<BR>
     * 　@　@　@　@　@・"6: Def.CLOSE_MARGIN_SHORT"<BR>
     * 　@　@　@　@　@・"7: Def.CLOSE_MARGIN_SHORT"<BR>
     * 　@　@　@　@　@・"8: Def.SWAP_MARGIN_SHORT"<BR>
     * 　@　@　@　@　@・"101: Def.MINI_STOCK_BUY"<BR>
     * 　@　@　@　@　@・"102: Def.MINI_STOCK_SELL"<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01439<BR>
     * <BR>
     * 2) tradeStopDiv check<BR>
     * 　@2-1) If this.tradeStopDiv == null<BR>
     * 　@　@　@　@　@Throw the exception "tradeStopDiv is null"<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01894<BR>
     * <BR>
     * 　@2-2) If this.tradeStopDiv is neither of the following values<BR>
     * 　@　@　@　@　@Throw the exception "tradeStopDiv is an undefined value"<BR>
     * 　@　@　@　@　@　@・"0: Def.NORMAL"<BR>
     * 　@　@　@　@　@　@・"1: Def.SUSPENTION"<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01421<BR>
     * <BR>
     * 3)aftTradeStopDiv check<BR>
     * 　@If this.aftTradeStopDiv != null<BR>
     * 　@Check the followings<BR>
     * 　@3-1) If this.aftTradeStopDiv is neither of the following values<BR>
     * 　@　@　@　@　@Throw the exception "tradeStopDiv is an undefined value"<BR>
     * 　@　@　@　@　@　@・"0: Def.NORMAL"<BR>
     * 　@　@　@　@　@　@・"1: Def.SUSPENTION"<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01421<BR>
     * <BR>
     * @@roseuid 4185F4B40021
     * @@throws WEB3BusinessLayerException WEB3BusinessLayerException
     */
    public void validate() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        // 1-1 if orderType is null, throw Exception.
        if (orderType == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01438,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        } else
        {
            //1-2 if orderType not equal to any of WEB3AdminEquityOrderTypeDef, throw Exception.
            if ((!String.valueOf(OrderTypeEnum.EQUITY_BUY.intValue()).equals(orderType))
                && (!String.valueOf(OrderTypeEnum.EQUITY_SELL.intValue()).equals(orderType))
                && (!String.valueOf(OrderTypeEnum.MARGIN_LONG.intValue()).equals(orderType))
                && (!String.valueOf(OrderTypeEnum.MARGIN_SHORT.intValue()).equals(orderType))
                && (!String.valueOf(OrderTypeEnum.CLOSE_MARGIN_LONG.intValue()).equals(orderType))
                && (!String.valueOf(OrderTypeEnum.CLOSE_MARGIN_SHORT.intValue()).equals(orderType))
                && (!String.valueOf(OrderTypeEnum.SWAP_MARGIN_LONG.intValue()).equals(orderType))
                && (!String.valueOf(OrderTypeEnum.SWAP_MARGIN_SHORT.intValue()).equals(orderType))
                && (!String.valueOf(OrderTypeEnum.MINI_STOCK_BUY.intValue()).equals(orderType))
                && (!String.valueOf(OrderTypeEnum.MINI_STOCK_SELL.intValue()).equals(orderType)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01439,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 2-1 if tradeStopDiv is null, throw Exception
        if (tradeStopDiv == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01894,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        } else
        {
            // 2-2 if tradeStopDiv is Not NORMAL & SUSPENTION, throw Exception.
            if ((!WEB3AdminEquityTradeStopDivDef.NORMAL.equals(tradeStopDiv))
                && (!WEB3AdminEquityTradeStopDivDef.SUSPENTION.equals(tradeStopDiv)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01421,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 3-1 if aftTradeStopDiv is Not NORMAL & SUSPENTION, throw Exception.
        if (aftTradeStopDiv != null)
        {
            if ((!WEB3AdminEquityTradeStopDivDef.NORMAL.equals(aftTradeStopDiv))
                && (!WEB3AdminEquityTradeStopDivDef.SUSPENTION.equals(aftTradeStopDiv)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01421,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
