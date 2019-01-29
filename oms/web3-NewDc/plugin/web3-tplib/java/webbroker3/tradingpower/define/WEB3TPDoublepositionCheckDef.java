head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.51.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPDoublepositionCheckDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 「二階建チェック方法@-XXX時」の定数定義インターフェース(WEB3TPDoublepositionCheckDef.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/08/02 nakazato(DIR-ST) 新規作成
 */
package webbroker3.tradingpower.define;


/**
 * (「二階建チェック方法@-XXX時」の定数定義インターフェース)
 */
public interface WEB3TPDoublepositionCheckDef
{
    /**
     * (チェックなし)<BR>
     */
    public final static String DEFAULT = "0";

    /**
     * 発注後に注文銘柄の二階建率が制限二階建率(全体設定)を超えないかチェックする<BR>
     * ※その他商品買付の場合、このプリファ@レンス値を設定しても、全銘柄チェックされる。<BR>
     */
    public final static String ORDER_PRODUCT = "1";

    /**
     * 発注後に注文銘柄含むすべての保有資産内何れかの二階建率が制限二階建率(全体設定)を超えないかチェックする<BR>
     */
    public final static String ALL_PRODUCT = "2";

    /**
     * 発注後に注文銘柄の二階建率が制限二階建率(市場設定)を超えないかチェックする<BR>
     * ※その他商品買付の場合、全銘柄チェックされる。<BR>
     * ※(保護⇒代用)証券振替の場合、注文銘柄について、制限二階建率(全体設定)でチェックされる。<BR>
     * ※原則として、この設定値は、信用新規買建／現物買付／信用現引のみ設定する。<BR>
     */
    public final static String LISTMARKET_ORDER_PRODUCT = "3";

}
@
