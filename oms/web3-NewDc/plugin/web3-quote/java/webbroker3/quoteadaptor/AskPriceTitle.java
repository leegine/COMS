head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.43.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	AskPriceTitle.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 * Copyright        : (株)大和総研 証券ソリューションシステム第二部
 * File Name        : ○○○○○クラス(AskPriceTitle.java)
 * Author Name      : Daiwa Institute of Research
 * Revision History : 2004/01/30 山田　@卓司(FLJ) 新規作成
 *                    2006/06/27 山田　@卓司(FTL) 買気配値タイトル.特別気配を追加
 */
package webbroker3.quoteadaptor;

import com.fitechlabs.xtrade.kernel.enumerated.Enumerated;

/**
 * 買気配値タイトルのEnumクラス<br>
 * 
 * @@author Takuji Yamada
 * @@version 1.0
 */
public final class AskPriceTitle extends Enumerated
{

    /**
     * 買気配値タイトルのEnumクラスで使用する定数<br>
     * 
     * @@author Takuji Yamada
     * @@version 1.0
     */
    public static final class IntValues
    {

        /**
         * 買気配値タイトル：未定義
         */
        public static final int UNDEFINED = 0;

        /**
         * 買気配値タイトル：買気配
         */
        public static final int ASK = 1;

        /**
         * 買気配値タイトル：売買停止
         */
        public static final int TRADING_SUSPENDED = 2;

        /**
         * 買気配値タイトル：板寄せ中
         */
        public static final int ITAYOSECHU = 3;
        
        /**
         * 買気配値タイトル：特別気配
         */
        public static final int SPECIAL_QUOTATION = 5;
        
    }

    /**
     * 買気配値タイトル：未定義
     */
    public static final AskPriceTitle UNDEFINED =
        new AskPriceTitle(IntValues.UNDEFINED, "UNDEFINED");

    /**
     * 買気配値タイトル：買気配
     */
    public static final AskPriceTitle ASK =
        new AskPriceTitle(IntValues.ASK, "ASK");

    /**
     * 買気配値タイトル：売買停止
     */
    public static final AskPriceTitle TRADING_SUSPENDED =
        new AskPriceTitle(IntValues.TRADING_SUSPENDED, "TRADING_SUSPENDED");

    /**
     * 買気配値タイトル：板寄せ中
     */
    public static final AskPriceTitle ITAYOSECHU =
        new AskPriceTitle(IntValues.ITAYOSECHU, "ITAYOSECHU");

    /**
     * 買気配値タイトル：特別気配
     */
    public static final AskPriceTitle SPECIAL_QUOTATION =
        new AskPriceTitle(IntValues.SPECIAL_QUOTATION, "SPECIAL_QUOTATION");

    /**
     * コンストラクタ
     */
    private AskPriceTitle(int i, String s)
    {
        super(i, s);
    }
}
@
