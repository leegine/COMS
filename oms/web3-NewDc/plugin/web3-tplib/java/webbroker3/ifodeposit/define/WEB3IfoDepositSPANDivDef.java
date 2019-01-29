head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.09.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3IfoDepositSPANDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : SPAN区分　@定数定義インターフェース(WEB3IfoDepositSPANDivDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/11/05 山田　@卓司(FLJ) 新規作成
*/
package webbroker3.ifodeposit.define;

/**
 * SPAN区分　@定数定義インターフェース
 * 
 * @@author Takuji Yamada
 * @@version 1.0
 */
public interface WEB3IfoDepositSPANDivDef
{
    
    /**
     * SPAN非採用
     */
    public static final String NOT_ADOPTION = "0";
    
    /**
     * SPAN採用
     */
    public static final String ADOPTION = "1";

}
@
