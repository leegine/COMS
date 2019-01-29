head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.09.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3IfoReservedDateDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 指定日　@定数定義インタフェイス(WEB3IfoReservedDateDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/14 nakazato(ACT) 新規作成
*/
package webbroker3.ifodeposit.define;

/**
 *指定日　@定数定義インタフェイス
 *
 * @@version 1.0
 */
public interface WEB3IfoReservedDateDef
{
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
}
@
