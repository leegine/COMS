head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.03.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3ApplyStateDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 申込状況 定数定義インタフェイス(WEB3ApplyStateDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/06  鄭海良(中訊) 新規作成
*/
package webbroker3.point.define;

/**
 * 申込状況 定数定義インタフェイス
 * 
 * @@author 鄭海良(中訊)
 * @@version 1.0
 */
public interface WEB3ApplyStateDivDef
{

    /**
     * 0：　@受付中
     */
    public static final String ACCEPTING = "0";
    
    /**
     * 1：　@受付済
     */
    public static final String ACCEPTED = "1";
    
    /**
     * 2：　@取消
     */
    public static final String CANCEL = "2";
    
}
@
