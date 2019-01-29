head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.46.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiApplyKindDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 申込種別区分(WEB3SrvRegiApplyKindDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/21 李頴淵(sinocom) 新規作成
*/
package webbroker3.srvregi.define;

/**
 * 申込種別区分
 *
 * @@author 李頴淵
 * @@version 1.0
 */
public interface WEB3SrvRegiApplyKindDivDef
{
    /**
     * 0：通常申込  　@
     */
    public final static String USUAL_APPLI = "0";

    /**
     * 1：継続申込　@
     */
    public final static String CONTINUE_APPLI = "1";
    
    /**
     * 2：試用申込　@　@
     */
    public final static String TRIAL_APPLI = "2";
     
    /**
     * 3：無料申込　@　@
     */
    public final static String FREE_APPLI = "3";

}
@
