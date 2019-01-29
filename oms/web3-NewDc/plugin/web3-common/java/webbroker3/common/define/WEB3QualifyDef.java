head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.39.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3QualifyDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3QualifyDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/04 李海波(sinocom)　@新規作成
*/
package webbroker3.common.define;

/**
 * （優） 代用 適格　@定数定義インタフェイス
 *                                                                      
 * @@author 李海波
 * @@version 1.0
 */
public interface WEB3QualifyDef
{
    /**
     * 0 : 不適格
     */
    public static final String NOT_QUALIFY = "0";

    /**
     * 1 : 適格
     */
    public static final String QUALIFY = "1";

}@
