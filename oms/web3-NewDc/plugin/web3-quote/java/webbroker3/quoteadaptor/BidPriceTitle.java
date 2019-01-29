head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.42.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	BidPriceTitle.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 * Copyright        : (株)大和総研 証券ソリューションシステム第二部
 * File Name        : ○○○○○クラス(BidPriceTitle.java)
 * Author Name      : Daiwa Institute of Research
 * Revision History : 2004/01/30 山田　@卓司(FLJ) 新規作成
 *                  : 2006/06/27 山田　@卓司(FTL) 売気配値タイトル.板寄せ中と売気配値タイトル.特別気配を追加
 */
package webbroker3.quoteadaptor;

import com.fitechlabs.xtrade.kernel.enumerated.Enumerated;

/**
 * 売気配値タイトルのEnumクラス<br>
 * 
 * @@author Takuji Yamada
 * @@version 1.0
 */
public final class BidPriceTitle extends Enumerated
{
    /**
     * 売気配値タイトルのEnumクラスで使用する定数<br>
     * 
     * @@author Takuji Yamada
     * @@version 1.0
     */
    public static final class IntValues {
        
        /**
         * 売気配値タイトル：未定義
         */
        public static final int UNDEFINED = 0;
        
        /**
         * 売気配値タイトル：板寄せ中
         */
        public static final int ITAYOSECHU = 3;
        
        /**
         * 売気配値タイトル：売気配
         */
        public static final int BID = 4;
        
        /**
         * 売気配値タイトル：特別気配
         */
        public static final int SPECIAL_QUOTATION = 5;
        
    }
    
    /**
     * 売気配値タイトル：未定義
     */
    public static final BidPriceTitle UNDEFINED =
        new BidPriceTitle(IntValues.UNDEFINED, "UNDEFINED");
    
    /**
     * 売気配値タイトル：板寄せ中
     */
    public static final BidPriceTitle ITAYOSECHU =
        new BidPriceTitle(IntValues.ITAYOSECHU, "ITAYOSECHU");
        
    /**
     * 売気配値タイトル：売気配
     */
    public static final BidPriceTitle BID =
        new BidPriceTitle(IntValues.BID, "BID");
        
    /**
     * 売気配値タイトル：特別気配
     */
    public static final BidPriceTitle SPECIAL_QUOTATION =
        new BidPriceTitle(IntValues.SPECIAL_QUOTATION, "SPECIAL_QUOTATION");
        
    private BidPriceTitle(int i, String s) {
        super(i, s);
    }
}
@
