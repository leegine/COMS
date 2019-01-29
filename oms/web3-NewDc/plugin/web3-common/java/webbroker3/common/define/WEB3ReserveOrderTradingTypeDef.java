head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.58.32;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ReserveOrderTradingTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 連続注文取引区分定数定義インタフェイス(WEB3ReserveOrderTradingTypeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/12 孟東(中訊)　@新規作成
*/
package webbroker3.common.define;

/**
 * 連続注文取引区分定数定義インタフェイス
 * 
 * @@author Meng-Dong
 * @@version 1.0
 */
public class WEB3ReserveOrderTradingTypeDef
{
    /**
     * 01：買付（前提注文）
     */
    public static final String BUY_ASSUMPTION_ORDER = "01";

    /**
     * 02：買付
     */
    public static final String BUY = "02";

    /**
     * 03：売付（前提注文）
     */
    public static final String SELL_ASSUMPTION_ORDER = "03";

    /**
     * 04：売付（既存残）
     */
    public static final String SELL_EXISTING_REMAINDER = "04";

    /**
     * 05：信用新規建（前提注文）
     */
    public static final String OPEN_CONTRACT_ASSUMPTION_ORDER = "05";

    /**
     * 06：信用新規建
     */
    public static final String OPEN_CONTRACT = "06";

    /**
     * 07：信用返済（前提注文）
     */
    public static final String SETTLE_CONTRACT_ASSUMPTION_ORDER = "07";

    /**
     * 08：信用返済（既存残）
     */
    public static final String SETTLE_CONTRACT_EXISTING_REMAINDER = "08";

    /**
     * 09：現引現渡（前提注文）
     */
    public static final String SWAP_CONTRACT_ASSUMPTION_ORDER = "09";

    /**
     * 10：現引現渡（既存残）
     */
    public static final String SWAP_CONTRACT_EXISTING_REMAINDER = "10";

    /**
     * 11：先物新規建（前提注文）
     */
    public static final String OPEN_FUTURE_ASSUMPTION_ORDER = "11";

    /**
     * 12：先物新規建
     */
    public static final String OPEN_FUTURE = "12";

    /**
     * 13：先物返済（前提注文）
     */
    public static final String SETTLE_FUTURE_ASSUMPTION_ORDER = "13";

    /**
     * 14：先物返済（既存残）
     */
    public static final String SETTLE_FUTURE_EXISTING_REMAINDER = "14";

    /**
     * 15：OP新規建（前提注文）
     */
    public static final String OPEN_OP_ASSUMPTION_ORDER = "15";

    /**
     * 16：OP新規建
     */
    public static final String OPEN_OP = "16";

    /**
     * 17：OP返済（前提注文）
     */
    public static final String SETTLE_OP_ASSUMPTION_ORDER = "17";

    /**
     * 18：OP返済（既存残）
     */
    public static final String SETTLE_OP_EXISTING_REMAINDER = "18";
}
@
