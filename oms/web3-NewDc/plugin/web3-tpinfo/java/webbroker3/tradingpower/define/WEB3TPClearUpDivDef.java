head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.12.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPClearUpDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 清算済区分定義クラス(WEB3TPClearUpDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/07 asano(SCS) 新規作成
*/
package webbroker3.tradingpower.define;

/**
 * 清算済区分定数インターフェイス
 */
public interface WEB3TPClearUpDivDef 
{
    /**
     * 受渡済
     */
    public static final String DELIVERD = "0";

    /**
     * 未受渡分含む
     */
    public static final String EXECUTED = "1";

}
@
