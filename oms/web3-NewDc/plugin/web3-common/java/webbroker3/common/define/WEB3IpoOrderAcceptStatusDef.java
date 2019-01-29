head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.00.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3IpoOrderAcceptStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 受付状態定数定義インタフェイス(WEB3IpoOrderAcceptStatusDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/04 劉江涛(sinocom) 新規作成
*/
package webbroker3.common.define;

/**
 * 受付状態 定数定義インタフェイス
 *
 * @@author 劉江涛
 * @@version 1.0
 */
public interface WEB3IpoOrderAcceptStatusDef
{

    /**
     * 0：DEFAULT（初期値）　@
     */
    public final static String DEFAULT = "0";

    /**
     * 1：SONAR送信済　@　@　@　@ 　@　@　@ 　@　@
     */
    public final static String SONAR_MAIL_SENDED = "1";
    
    /**
     * 2：SONAR反映済み　@　@　@　@　@　@　@
     */
    public final static String SONAR_REFLECTED = "2";
        
}

@
