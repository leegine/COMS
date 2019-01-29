head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.39.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	WEB3QuoteRecordFormat.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3QuoteRecordFormatクラス(WEB3QuoteRecordFormat.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/10 山田　@卓司(FLJ) 新規作成
 */
package webbroker3.quoteadaptor.stdimpls;


/**
 * 時価サーバより受信する時価レコードのフォーマットに関する定数クラス
 *
 * @@author Takuji Yamada (FLJ)
 * @@version 1.0
 */
interface WEB3QuoteRecordFormat
{

    /** 株価日付の長さ */
    static final int QUOTE_DATE_SIZE = 8;

    /** リアル区分の長さ */
    static final int REAL_TYPE_SIZE = 1;

    /** 種別区分の長さ */
    static final int DATA_TYPE_SIZE = 1;

    /** 市場コードの長さ */
    static final int MARKET_CODE_SIZE = 2;

    /** 銘柄コードの長さ */
    static final int PRODUCT_CODE_SIZE = 9;

    /** 限月の長さ */
    static final int MONTH_OF_DELIVERY_SIZE = 6;

    /** プット＆コールの長さ */
    static final int PUT_AND_CALL_SIZE = 1;

    /** 行使価格の長さ */
    static final int STRIKE_PRICE_SIZE = 12;

    /** 始値の長さ */
    static final int OPEN_PRICE_SIZE = 12;

    /** 始値時刻の長さ */
    static final int OPEN_PRICE_TIME_SIZE = 4;

    /** 高値の長さ */
    static final int HIGH_PRICE_SIZE = 12;

    /** 高値時刻の長さ */
    static final int HIGH_PRICE_TIME_SIZE = 4;

    /** 安値の長さ */
    static final int LOW_PRICE_SIZE = 12;

    /** 安値時刻の長さ */
    static final int LOW_PRICE_TIME_SIZE = 4;

    /** 現在値の長さ */
    static final int CURRENT_PRICE_SIZE = 12;

    /** 現在値時刻の長さ */
    static final int CURRENT_PRICE_TIME_SIZE = 4;

    /** 現在値フラグの長さ */
    static final int CURRENT_PRICE_FLAG_SIZE = 1;

    /** 前日比の長さ */
    static final int CHANGE_SIZE = 12;

    /** 出来高の長さ */
    static final int VOLUME_SIZE = 12;

    /** 出来高時刻の長さ */
    static final int VOLUME_TIME_SIZE = 4;

    /** 買気配値タイトルの長さ */
    static final int ASK_PRICE_TITLE_SIZE = 1;

    /** 買気配値の長さ */
    static final int ASK_PRICE_SIZE = 12;

    /** 買気配値時刻の長さ */
    static final int ASK_PRICE_TIME_SIZE = 4;

    /** 売気配値タイトルの長さ */
    static final int BID_PRICE_TITLE_SIZE = 1;

    /** 売気配値の長さ */
    static final int BID_PRICE_SIZE = 12;

    /** 売気配値時刻の長さ */
    static final int BID_PRICE_TIME_SIZE = 4;

    /** 基準値段の長さ */
    static final int BASE_PRICE_SIZE = 12;

}
@
