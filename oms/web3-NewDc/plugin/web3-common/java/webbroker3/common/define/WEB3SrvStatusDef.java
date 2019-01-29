head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.37.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3SrvStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ステータス(WEB3SrvStatusDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/11 郭英(sinocom) 新規作成
*/
package webbroker3.common.define;

/**
 * サービスマスターテーブル.ステータス
 *
 * @@author 郭英
 * @@version 1.0
 */
public interface WEB3SrvStatusDef
{

    /**
     * 0：停止中　@
     */
    public final static String STOP = "0";

    /**
     * 1：提供中（申込不可）　@
     */
    public final static String PROVIDING_APPLI_IMPOSSIBLE = "1";
    
    /**
     * 2：提供中
     */
    public final static String PROVIDING = "2";
    
}
 @
