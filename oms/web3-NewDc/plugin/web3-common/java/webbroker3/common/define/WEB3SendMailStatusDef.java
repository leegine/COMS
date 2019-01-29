head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.02.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3SendMailStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 電子メイル送信ステイタス定数定義インタフェイス(WEB3SendMailStatusDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/04 劉江涛(sinocom) 新規作成
              001: 2005/03/08 張威  (sinocom) 修正
*/
package webbroker3.common.define;

/**
 * 電子メイル送信ステイタス 定数定義インタフェイス
 *
 * @@author 劉江涛
 * @@version 1.0
 */
public interface WEB3SendMailStatusDef
{

    /**
     * 0：DEFAULT　@　@
     */
    public final static String DEFAULT = "0";

    /**
     * 1：メイル送信要　@ 　@　@　@ 　@　@
     */
    public final static String SEND_MAIL = "1";
    
    /**
     * 2：対象外　@　@　@　@　@
     */
    public final static String EXCEPT_OBJECT = "2";

}@
