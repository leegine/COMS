head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.46.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EnforcementDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 実施不実施FLAG定数定義クラス(WEB3EnforcementDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/12 今井　@高史(SRA) 新規作成
*/
package webbroker3.common.define;

/**
 * 実施不実施FLAG定数定義クラス
 *
 * @@author 今井　@高史(SRA)
 * @@version 1.0
 */
public interface WEB3EnforcementDef
{

    /**
     * 0:実施しない
     */
    public final static String NOT_ENFORCEMENT = "0";

    /**
     * 1:実施する
     */
    public final static String ENFORCEMENT = "1";

}
@
