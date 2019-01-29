head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.36.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdoptionDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3AdoptionDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 zhao-lin(sinocom)　@新規作成
*/
package webbroker3.common.define;

/**
 * 採否　@定数定義インタフェイス
 *                                                                      
 * @@author zhao-lin
 * @@version 1.0
 */
public interface WEB3AdoptionDef
{
    /**
     * 0 : 不採用
     */
    public static final String NOT_ADOPT = "0";

    /**
     * 1 : 採用
     */
    public static final String ADOPT = "1";

}
@
