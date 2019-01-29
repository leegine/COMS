head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.28.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AccountOpenStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 開設状況定数定義インタフェイス(WEB3AccountOpenStatusDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/06 孟暁シン(中訊) 新規作成
*/
package webbroker3.common.define;
/**
 * 開設状況 定数定義インタフェイス
 *
 * @@author 孟暁シン(中訊)
 * @@version 1.0
 */
public interface WEB3AccountOpenStatusDef
{
    /**
     * 0：申込
     */
    public final static String REQUEST= "0";

    /**
     * 1：開設
     */
    public final static String OPEN = "1";
    
    /**
     * 2：成約
     */
    public final static String PROMISE = "2";
    
    /**
     * 3：解約
     */
    public final static String RELEASE = "3";
    
    /**
     * 4：閉鎖
     */
    public final static String CLOSEDOWN = "4";
}
@
