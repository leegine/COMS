head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.15.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	78c4d885a7b5f07;
filename	WEB3QtpRichPushUrlPatternDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3QtpRichPushUrlPatternDefクラス(WEB3RichPushDataStatusDef.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/02/06 孫(FLJ) 新規作成
 */
package webbroker3.rcp.define;

/**
 * URLの定数定義クラス<br>
 *
 * @@author　@孫(FLJ)
 * @@version 1.0
 */
public interface WEB3QtpRichPushUrlPatternDef
{

    /**
     * 株式約定通知URL
     */
    public static final String URL_EQUITY = "URL4";
    
    /**
     * 信用約定通知URL
     */
    public static final String URL_MARGIN = "URL10";
    
    /**
     * 先物約定通知URL
     */
    public static final String URL_FUTURES = "URL15";
    
    /**
     * オプション約定通知URL
     */
    public static final String URL_OPTIONS = "URL20";



}
@
