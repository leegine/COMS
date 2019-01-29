head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.43.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	RealType.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 * Copyright        : (株)大和総研 証券ソリューションシステム第二部
 * File Name        : 時価データのリアル区分を表すEnumeratedクラス(RealType.java)
 * Author Name      : Daiwa Institute of Research
 * Revision History : 2004/01/30 山田　@卓司(FLJ) 新規作成
                    : 2008/05/14 許　@　@　@　@競(FLJ) リアル区分の値に追加　@　@4:スナップショット
 */
package webbroker3.quoteadaptor;

import com.fitechlabs.xtrade.kernel.enumerated.Enumerated;

/**
 * リアル区分のEnumクラス
 * 
 * @@author Takuji Yamada
 * @@version 1.0
 */
public final class RealType extends Enumerated
{
    
    /**
     * リアル区分のEnumクラスで使用する定数<br>
     * 
     * @@author Takuji Yamada
     * @@version 1.0
     */
    public static class IntValues {
        
        /**
         * リアル区分：未定義
         */
        public static final int UNDEFINED = 0;
        
        /**
         * リアル区分：リアル
         */
        public static final int REAL = 1;
        
        /**
         * リアル区分：20分ディレイ
         */
        public static final int DELAY = 2;
        
        /**
         * リアル区分：引値
         */
        public static final int CLOSING_PRICE = 3;
        
        /**
         * リアル区分：スナップショット
         */
        public static final int SNAPSHOT = 4;
    }
    
    /**
     * リアル区分：未定義
     */
    public static final RealType UNDEFINED =
        new RealType(IntValues.UNDEFINED, "UNDEFINED");
    
    /**
     * リアル区分：リアル
     */
    public static final RealType REAL =
        new RealType(IntValues.REAL, "REAL");
    
    /**
     * リアル区分：20分ディレイ
     */
    public static final RealType DELAY =
        new RealType(IntValues.DELAY, "DELAY");
        
    /**
     * リアル区分：引値
     */
    public static final RealType CLOSING_PRICE =
        new RealType(IntValues.CLOSING_PRICE, "CLOSING_PRICE");
    
    /**
     * リアル区分：スナップショット
     */
    public static final RealType SNAPSHOT = 
        new RealType(IntValues.SNAPSHOT, "SNAPSHOT");
    
    /**
     * コンストラクタ
     */
    private RealType(int i, String s) {
        super(i, s);
    }
    
}
@
