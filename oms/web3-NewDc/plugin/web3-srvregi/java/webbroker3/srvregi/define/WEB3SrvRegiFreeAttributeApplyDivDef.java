head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.47.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiFreeAttributeApplyDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 無料属性申込区分(WEB3SrvRegiFreeAttributeApplyDivDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/06/06 張騰宇 (中訊) 新規作成 モデル242
*/
package webbroker3.srvregi.define;

/**
 * (無料属性申込区分)<BR>
 * null or '0' ：通常申込　@'1'：無料属性申込<BR>
 * @@author 張騰宇
 * @@version 1.0
 */
public interface WEB3SrvRegiFreeAttributeApplyDivDef
{
    /**
     * null or '0' ：通常申込
     */
    public final static String NORMAL_APPLY = "0";

    /**
     * '1'：無料属性申込
     */
    public final static String FREE_ATTRIBUTE_APPLY = "1";
}
@
