head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.14.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	78c4d885a7b5f07;
filename	WEB3RichPushDataTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : リッチクライアントプッシデータタイプの定数定義クラス(WEB3RichPushDataTypeDef.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2006/02/06 劉(FLJ) 新規作成
 */
package webbroker3.rcp.define;

import java.util.*;

/**
 * リッチクライアントプッシデータタイプの定数定義クラス<br>
 *
 * @@author　@劉(FLJ)
 * @@version 1.0
 */
public class WEB3RichPushDataTypeDef
{

    /**
     * 株式注文受付（信用現引現渡以外）
     */
    public static final String EQUITY_ORDER_ACCEPT = "00";

    /**
     * 株式信用現引現渡受付
     */
    public static final String SWAP_ORDER_ACCEPT = "01";

    /**
     * 株式訂正取消通知
     */
    public static final String EQTYPE_CHANGE_CANCEL = "02";

    /**
     * 株式出来通知
     */
    public static final String EQTYPE_CONT = "03";

    /**
     * 株式失効通知
     */
    public static final String EQTYPE_LAPSE = "04";

    /**
     * 先物OP注文受付通知
     */
    public static final String IFO_ORDER_ACCEPT = "10";

    /**
     * 先物OP訂正取消通知
     */
    public static final String IFO_CHANGE_CANCEL = "12";

    /**
     * 先物OP出来通知
     */
    public static final String IFO_CONT = "13";

    /**
     * 先物OP失効通知
     */
    public static final String IFO_LAPSE = "14";

    /**
     * ネーミングマップ
     */
    public static final Map NAME_TYPE_MAP = new Hashtable(10);

    static
    {
        NAME_TYPE_MAP.put(EQUITY_ORDER_ACCEPT, "eq_order_accept");
        NAME_TYPE_MAP.put(SWAP_ORDER_ACCEPT, "swap_order_accept");
        NAME_TYPE_MAP.put(EQTYPE_CHANGE_CANCEL, "eq_change_cancel");
        NAME_TYPE_MAP.put(EQTYPE_CONT, "eq_cont");
        NAME_TYPE_MAP.put(EQTYPE_LAPSE, "eq_lapse");
        NAME_TYPE_MAP.put(IFO_ORDER_ACCEPT, "ifo_order_accept");
        NAME_TYPE_MAP.put(IFO_CHANGE_CANCEL, "ifo_change_cancel");
        NAME_TYPE_MAP.put(IFO_CONT, "ifo_cont");
        NAME_TYPE_MAP.put(IFO_LAPSE, "ifo_lapse");
    }

}
@
