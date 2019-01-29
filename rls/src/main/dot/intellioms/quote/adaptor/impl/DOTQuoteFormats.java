/*
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3QuoteFormatsクラス(WEB3QuoteDataFormat.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/08/30 山田　卓司(FLJ) 新規作成
 */
package jp.co.dir.dot.intellioms.quote.adaptor.impl;

/**
 * (時価情報フォーマット定数定義)<BR>
 * <BR>
 * 時価情報のフォーマットを定義したインターフェース。<BR>
 * 
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
interface DOTQuoteFormats
{

    /**
     * シーケンスNO
     * 
     * 時価サーバから受信したシーケンスNO＋枝番（2桁）
     */
    public static final int AP_SEQUENCE_NO_SIZE = DOTQuoteConstants.SEQUENCE_NO_SIZE + 2;

    /** 更新時間 */
    public static final int UPDATED_TIME_SIZE = 23;

    /** 株価日付 */
    public static final int QUOTE_DATE_SIZE = 8;

    /** リアル区分 */
    public static final int REAL_TYPE_SIZE = 1;

    /** 種別区分 */
    public static final int DATA_TYPE_SIZE = 1;

    /** 市場コード */
    public static final int MARKET_CODE_SIZE = 2;

    /** 銘柄コード */
    public static final int PRODUCT_CODE_SIZE = 9;

    /** 限月 */
    public static final int MONTH_OF_DELIVERY_SIZE = 6;

    /** プット＆コール */
    public static final int PUT_AND_CALL_SIZE = 1;

    /** 行使価格 */
    public static final int STRIKE_PRICE_SIZE = 12;

    /** 始値 */
    public static final int OPEN_PRICE_SIZE = 12;

    /** 始値時刻 */
    public static final int OPEN_PRICE_TIME_SIZE = 4;

    /** 高値 */
    public static final int HIGH_PRICE_SIZE = 12;

    /** 高値時刻 */
    public static final int HIGH_PRICE_TIME_SIZE = 4;

    /** 安値 */
    public static final int LOW_PRICE_SIZE = 12;

    /** 安値時刻 */
    public static final int LOW_PRICE_TIME_SIZE = 4;

    /** 現在値 */
    public static final int CURRENT_PRICE_SIZE = 12;

    /** 現在値時刻 */
    public static final int CURRENT_PRICE_TIME_SIZE = 4;

    /** 現在値フラグ */
    public static final int CURRENT_PRICE_FLAG_SIZE = 1;

    /** 前日比 */
    public static final int CHANGE_SIZE = 12;

    /** 出来高 */
    public static final int VOLUME_SIZE = 12;

    /** 出来高時刻 */
    public static final int VOLUME_TIME_SIZE = 4;

    /** 買気配値タイトル */
    public static final int ASK_PRICE_TITLE_SIZE = 1;

    /** 買気配値 */
    public static final int ASK_PRICE_SIZE = 12;

    /** 買気配値時刻 */
    public static final int ASK_PRICE_TIME_SIZE = 4;

    /** 売気配値タイトル */
    public static final int BID_PRICE_TITLE_SIZE = 1;

    /** 売気配値 */
    public static final int BID_PRICE_SIZE = 12;

    /** 売気配値時刻 */
    public static final int BID_PRICE_TIME_SIZE = 4;

    /** 基準値段 */
    public static final int BASE_PRICE_SIZE = 12;

    /** 銘柄コード（株式） */
    public static final int EQUITY_PRODUCT_CODE_SIZE = 5;

    /** 日付フォーマット：yyyyMMdd */
    public static final String DATE_FORMAT = "yyyyMMdd";

    /** 日付フォーマット：HHmm */
    public static final String TIME_FORMAT = "HHmm";

    /** 日付フォーマット：yyyy-MM-dd HH:mm:ss.SSS */
    public static final String TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

    /** 数値フォーマット：#.## */
    public static final String DECIMAL_FORMAT = "#.##";

    /** 
     * 1件のレコードのサイズ
     * 
     * ルールエンジン上の1件のレコードサイズは、受信した時価情報に、
     * シーケンスNOと更新時間が追加されるため、 時価サーバから受信した
     * 時価情報のサイズとは異なる。
     */
    public static final int AP_RECORD_SIZE = AP_SEQUENCE_NO_SIZE + UPDATED_TIME_SIZE
        + DOTQuoteConstants.RECORD_SIZE;

}