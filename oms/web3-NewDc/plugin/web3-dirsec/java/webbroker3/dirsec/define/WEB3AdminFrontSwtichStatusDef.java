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
filename	WEB3AdminFrontSwtichStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 画面表示用切替ステータス 定数定義インタフェイス(WEB3AdminFrontSwtichStatusDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/01/17  謝旋 (中訊) 仕様変更モデルNo.115
*/
package webbroker3.dirsec.define;

/**
 * 画面表示用切替ステータス 定数定義インタフェイス
 *
 * @@author SCS 佐藤
 * @@version 1.0
 */
public interface WEB3AdminFrontSwtichStatusDef {

    /**
     * 0：未処理
     */
    public final static String NO_DEAL = "0";
    
    /**
     * 1：切替中
     */
    public final static String DISP_CHANEGE_IN = "1";
    
    /**
     * 2：切替済
     */
    public final static String DISP_CHANEGE_END = "2";
    
    /**
     * 9：切替不可
     */
    public final static String DISP_NO_CHANGE = "9";
}
@
