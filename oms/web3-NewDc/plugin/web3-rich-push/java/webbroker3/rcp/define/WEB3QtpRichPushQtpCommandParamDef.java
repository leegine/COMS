head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.15.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	78c4d885a7b5f07;
filename	WEB3QtpRichPushQtpCommandParamDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3QtpRichPushQtpCommandParamDefクラス(WEB3QtpRichPushQtpCommandParamDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/05/02 孫(FLJ) 新規作成
*/

package webbroker3.rcp.define;

/**
 * 追加コマンド文字列を定義
 * @@author  : 孫(FLJ)
 * @@version : 1.0
 */
public interface WEB3QtpRichPushQtpCommandParamDef
{
    /**
     * Quoteコマンド文字列
     */
    public static final String QUOTE_PREFIX = ",QUOTE=";
    
    /**
     * 株式通知メッセージのMenuコマンド文字列
     */
    public static final String EQTYPE_MENU = ",MENU=31";
    
    /**
     * IFO通知メッセージのドラッグコマンド文字列
     */
    public static final String IFO_DRAG = ",DRAG=OFF";
    

}
@
