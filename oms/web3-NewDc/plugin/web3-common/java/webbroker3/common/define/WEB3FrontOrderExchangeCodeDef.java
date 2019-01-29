head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.36.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3FrontOrderExchangeCodeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : フロント発注取引所区分コード定数定義インタフェイス(WEB3FrontOrderExchangeCodeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/20 孟東(中訊)　@新規作成
Revesion History : 2009/05/21 趙林鵬(中訊)【共通】仕様変更管理台帳ＤＢレイアウト.687
*/
package webbroker3.common.define;

/**
 * フロント発注取引所区分コード 定数定義インタフェイス
 * 
 * @@author Meng-Dong
 * @@version 1.0
 */
public class WEB3FrontOrderExchangeCodeDef
{
    /**
     * 1:東証
     */
    public static final String TOKYO_STOCK_EXCHANGE = "1";

    /**
     * 2:大証
     */
    public static final String OSAKA_SECURITIES_EXCHANGE = "2";

    /**
     * 3：名証
     */
    public static final String NAGOYA_STOCK_EXCHANGE = "3";
}
@
