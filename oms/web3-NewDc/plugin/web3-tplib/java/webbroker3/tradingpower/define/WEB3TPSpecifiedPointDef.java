head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.50.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPSpecifiedPointDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 指定日　@定数定義インタフェイス(WEB3TPSpecifiedPointDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/13 nakazato(ACT) 新規作成
                   2006/09/13 徐宏偉　@(中訊) モデル NO.035
Revesion History : 2007/10/16 趙林鵬  (中訊) モデル NO.198
*/
package webbroker3.tradingpower.define;

/**
 *指定日　@定数定義インタフェイス
 *
 * @@version 1.0
 */
public interface WEB3TPSpecifiedPointDef
{
    /**
     * 指定日(T-1):-1
     */
    public final static int T_MINUS1 = -1;

    /**
     * 指定日(T-2):-2
     */
    public final static int T_MINUS2 = -2;

    /**
     * 指定日(T+0):0
     */
    public final static int T_0 = 0;

    /**
     * 指定日(T+1):1
     */
    public final static int T_1 = 1;

    /**
     * 指定日(T+2):2
     */
    public final static int T_2 = 2;

    /**
     * 指定日(T+3):3
     */
    public final static int T_3 = 3;

    /**
     * 指定日(T+4):4
     */
    public final static int T_4 = 4;

    /**
     * 指定日(T+5):5
     */
    public final static int T_5 = 5;
    
    /**
     * 余力計算範囲
     */
    public final static int TP_CALC_RANGE = 5;

}
@
