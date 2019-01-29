head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.09.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3IfoPositionBalanceTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ポジションバランスタイプ　@定数定義インタフェイス(WEB3IfoPositionBalanceTypeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/21 nakazato(ACT) 新規作成
*/
package webbroker3.ifodeposit.define;

/**
 *ポジションバランスタイプ　@定数定義インタフェイス
 */
public interface WEB3IfoPositionBalanceTypeDef
{
    /**
     * 0：　@ニュートラル
     */
    public final static String NEUTRAL = "0";

    /**
     * 1：　@買超過
     */
    public final static String BUY_OVER = "1";

    /**
     * 2：　@売超過
     */
    public final static String SELL_OVER = "2";
}
@
