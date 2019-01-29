head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.24.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ShortSellingCountMethodDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 空売り数量計上方法@　@定数定義インタフェイス(WEB3ShortSellingCountMethodDivDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/01/30 森川 昌平(SRA) 新規作成
*/
package webbroker3.common.define;

/**
 * 空売り数量計上方法@　@定数定義インタフェイス
 *
 * @@author  森川 昌平(SRA)
 * @@version 1.0
 */
public interface WEB3ShortSellingCountMethodDivDef
{
    /**
     * 0：開局扱いで計上
     */
    public static final String COUNT_AS_OPEN = "0";
    
    /**
     * 1：閉局扱いで計上
     */
    public static final String COUNT_AS_CLOSE = "1";

    /**
     * 2：休憩時間帯扱いで計上
     */
    public static final String COUNT_AS_REST_TIME_ZONE = "2";
}
@
