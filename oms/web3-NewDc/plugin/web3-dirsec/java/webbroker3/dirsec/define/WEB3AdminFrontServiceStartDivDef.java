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
filename	WEB3AdminFrontServiceStartDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス起動区分 定数定義インタフェイス(WEB3AdminFrontServiceStartDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/01/17  謝旋 (中訊) 仕様変更モデルNo.115
*/
package webbroker3.dirsec.define;

/**
 * サービス起動区分 定数定義インタフェイス
 *
 * @@author SCS 佐藤
 * @@version 1.0
 */
public interface WEB3AdminFrontServiceStartDivDef {
    
    /**
     * 管理者起動
     */
    public final static String ADMINISTRATOR_DIV = "0";
    
    /**
     * 電文処理サーバ起動
     */
    public final static String TELEGRAM_TRANS_DIV = "1";
}
@
