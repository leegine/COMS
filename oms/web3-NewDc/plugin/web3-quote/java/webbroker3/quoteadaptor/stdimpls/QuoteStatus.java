head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.40.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	QuoteStatus.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3QuoteStatusEnumクラス(WEB3QuoteStatusEnum.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/24 山田　@卓司(FLJ) 新規作成
 */
package webbroker3.quoteadaptor.stdimpls;

import com.fitechlabs.xtrade.kernel.enumerated.Enumerated;


/**
 * 時価サービスの接続状態を表すEnumeratedクラス
 *
 * @@author Takuji Yamada (FLJ)
 * @@version 1.0
 */
public final class QuoteStatus extends Enumerated
{
    
    /**
     * WEB3QuoteStatusEnumの整数値の定義クラス
     *
     * @@author Takuji Yamada (FLJ)
     * @@version 1.0
     */
    public static final class IntValues {

        /**
         * 接続状態：未接続
         */
        public static final int CLOSED = 0;
        
        /**
         * 接続状態：接続中
         */
        public static final int CONNECTING = 1;

        /**
         * 接続状態：接続済
         */
        public static final int CONNECTED = 2;
        
    }
    
    /**
     * 接続状態：未接続
     */
    public static final QuoteStatus CLOSED = 
        new QuoteStatus(IntValues.CLOSED, "CLOSED");
    
    /**
     * 接続状態：接続済
     */
    public static final QuoteStatus CONNECTING =
        new QuoteStatus(IntValues.CONNECTING, "CONNECTING");

    /**
     * 接続状態：接続済
     */
    public static final QuoteStatus CONNECTED =
        new QuoteStatus(IntValues.CONNECTED, "CONNECTED");

    /**
     * コンストラクタ
     * 
     * @@param intValue 整数値
     * @@param stringValue 文字列値
     */
    private QuoteStatus(int intValue, String stringValue)
    {
        super(intValue, stringValue);
    }

}
@
