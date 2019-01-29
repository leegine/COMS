head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFrontHostStatusDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : キューテーブル処理状況 定数定義インタフェイス(WEB3AdminFrontHostStatusDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/01/17  謝旋 (中訊) 仕様変更モデルNo.115
*/
package webbroker3.dirsec.define;

/**
 * キューテーブル処理状況 定数定義インタフェイス
 *
 * @@author SCS 佐藤
 * @@version 1.0
 */
public interface WEB3AdminFrontHostStatusDivDef {
    
    /**
     * 市場受付確認前 
     */
    public final static String MARKET_CONFIRM_PREV = "0";
    
    /**
     * 市場受付確認中
     */
    public final static String MARKET_CONFIRM_IN = "1";
    
    /**
     * 市場受付確認済
     */
    public final static String MARKET_CONFIRM_FUTURE = "2";
}
@
