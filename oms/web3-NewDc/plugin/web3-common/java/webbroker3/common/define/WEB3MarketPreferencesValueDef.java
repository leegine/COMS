head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.35.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarketPreferencesValueDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : プリファ@レンスの値定数定義インタフェイス(WEB3MarketPreferencesValueDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/08/30 孟東(sinocom) 新規作成
*/
package webbroker3.common.define;

/**
 * 市場用プリファ@レンステーブルのプリファ@レンスの値定数定義インタフェイス
 *
 * @@author meng-d
 * @@version 1.0
 */
public interface WEB3MarketPreferencesValueDef
{
    /**
     * able：訂正可　@　@　@  　@　@
     */
    public final static String ABLE = "able";

    /**
     * unable：訂正不可　@　@　@  　@　@
     */
    public final static String UNABLE = "unable";
}
@
