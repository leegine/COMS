head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.42.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	CurrentPriceFlag.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 * Copyright        : (株)大和総研 証券ソリューションシステム第二部
 * File Name        : 現在値フラグのEnumクラス(CurrentPriceFlag.java)
 * Author Name      : Daiwa Institute of Research
 * Revision History : 2004/01/30 山田　@卓司(FLJ) 新規作成
 */
package webbroker3.quoteadaptor;

import com.fitechlabs.xtrade.kernel.enumerated.Enumerated;

/**
 * 現在値フラグのEnumクラス<br>
 * 
 * @@author Takuji Yamada
 * @@version 1.0
 */
public final class CurrentPriceFlag extends Enumerated
{
    
    /**
     * 現在値フラグのEnumクラスで使用される定数<br>
     * 
     * @@author Takuji Yamada
     * @@version 1.0
     */
    public static final class IntValues
    {
        
        /**
         * 現在値フラグ：通常
         */
        public static final int NORMAL = 0;
        
        /**
         * 現在値フラグ：終値
         */
        public static final int CLOSING_PRICE = 1;
        
    }

    /**
     * 現在値フラグ：通常
     */
    public static final CurrentPriceFlag NORMAL =
        new CurrentPriceFlag(IntValues.NORMAL, "NORMAL");

    /**
     * 現在値フラグ：終値
     */
    public static final CurrentPriceFlag CLOSING_PRICE =
        new CurrentPriceFlag(IntValues.CLOSING_PRICE, "CLOSE_PRICE");

    /**
     * コンストラクタ
     */
    private CurrentPriceFlag(int i, String s)
    {
        super(i, s);
    }
}
@
