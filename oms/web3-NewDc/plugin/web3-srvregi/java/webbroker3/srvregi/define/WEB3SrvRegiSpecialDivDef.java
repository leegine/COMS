head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.47.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiSpecialDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 特殊申込区分(WEB3SrvRegiSpecialDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/11 (SRA)鈴木美由紀 新規作成
*/
package webbroker3.srvregi.define;

/**
 * 特殊申込区分
 *
 * @@author (SRA)鈴木
 * @@version 1.0
 */
public interface WEB3SrvRegiSpecialDivDef
{
	
    /**
     * 1：適用終了日月末
     */
    public final static String END_OF_MONTH_DIV = "1";
    
}
@
