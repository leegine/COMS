head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityHashMapKeyDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : HashMapKey定数定義インタフェイス(WEB3AdminEquityHashMapKeyDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/01/05 劉剣(中訊) 新規作成
Revision History : 2009/01/05 劉剣(中訊) モデル No.237
Revision History : 2009/05/06 李玉玲(中訊) モデル No.243 ＤＢ更新仕様No.032
*/
package webbroker3.eqtypeadmin.define;

/**
 * HashMapKey 定数定義インタフェイス
 *
 * @@author 劉剣
 * @@version 1.0
 */
public interface WEB3AdminEquityHashMapKeyDef
{
    /**
     * 銘柄コード : product_code
     */
    public final static String PRODUCT_CODE = "product_code";

    /**
     * 市場コード : market_code
     */
    public final static String MARKET_CODE = "market_code";

    /**
     * 情報発生日時 : info_generation_timestamp
     */
    public final static String INFO_GENERATION_TIMESTAMP = "info_generation_timestamp";

    /**
     * 売買停止日時／売買再開日時 : trade_stop_restart_timestamp
     */
    public final static String TRADE_STOP_RESTART_TIMESTAMP = "trade_stop_restart_timestamp";

    /**
     * 注文受付再開日時 : ord_receipt_restart_timestamp
     */
    public final static String ORD_RECEIPT_RESTART_TIMESTAMP = "ord_receipt_restart_timestamp";

    /**
     * 基準値（変更後） : new_base_price
     */
    public final static String NEW_BASE_PRICE= "new_base_price";

    /**
     * 制限値幅上限（変更後） : new_high_price_range
     */
    public final static String NEW_HIGH_PRICE_RANGE = "new_high_price_range";

    /**
     * 制限値幅下限（変更後） : new_low_price_range
     */
    public final static String NEW_LOW_PRICE_RANGE = "new_low_price_range";

    /**
     * 基準値（変更前） : old_base_price
     */
    public final static String OLD_BASE_PRICE = "old_base_price";

    /**
     * 制限値幅上限（変更前） : old_high_price_range
     */
    public final static String OLD_HIGH_PRICE_RANGE = "old_high_price_range";

    /**
     * 制限値幅下限（変更前） : old_low_price_range
     */
    public final static String OLD_LOW_PRICE_RANGE = "old_low_price_range";

    /**
     * 表題 : title
     */
    public final static String TITLE = "title";

    /**
     * 評価単価（変更前） : old_estimation_price
     */
    public final static String OLD_ESTIMATION_PRICE = "old_estimation_price";

    /**
     * 評価単価（変更後） : new_estimation_price
     */
    public final static String NEW_ESTIMATION_PRICE = "new_estimation_price";

    /**
     * 基準値（終値）（変更前） : old_last_closing_price
     */
    public final static String OLD_LAST_CLOSING_PRICE = "old_last_closing_price";

    /**
     * 基準値（終値）（変更後） : new_last_closing_price
     */
    public final static String NEW_LAST_CLOSING_PRICE = "new_last_closing_price";

    /**
     * 値幅チェック区分（変更前） : old_price_range_type
     */
    public final static String OLD_PRICE_RANGE_TYPE = "old_price_range_type";

    /**
     * 値幅チェック区分(変更後) : new_price_range_type
     */
    public final static String NEW_PRICE_RANGE_TYPE = "new_price_range_type";

    /**
     * 値幅区分（変更前） : old_price_range_unit_type
     */
    public final static String OLD_PRICE_RANGE_UNIT_TYPE = "old_price_range_unit_type";

    /**
     * 値幅区分（変更後） : new_price_range_unit_type
     */
    public final static String NEW_PRICE_RANGE_UNIT_TYPE = "new_price_range_unit_type";

    /**
     * 基準値（終値）（翌日）（変更前） : old_last_closing_price_updq
     */
    public final static String OLD_LAST_CLOSING_PRICE_UPDQ = "old_last_closing_price_updq";

    /**
     * 基準値（終値）（翌日）（変更後） : new_last_closing_price_updq
     */
    public final static String NEW_LAST_CLOSING_PRICE_UPDQ = "new_last_closing_price_updq";

    /**
     * 基準値（翌日）（変更前） : old_base_price_updq
     */
    public final static String OLD_BASE_PRICE_UPDQ = "old_base_price_updq";

    /**
     * 基準値（翌日）（変更後） : new_base_price_updq
     */
    public final static String NEW_BASE_PRICE_UPDQ = "new_base_price_updq";
}@
