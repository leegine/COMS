head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.34.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3PrDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 仕法@区分定数定義インタフェイス(WEB3PrDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/22 李海波(sinocom) 新規作成
*/
package webbroker3.common.define;

/**
 * 仕法@区分 定数定義インタフェイス
 *
 * @@author 李海波
 * @@version 1.0
 */
public interface WEB3PrDivDef
{

    /**
     * 1：本券　@　@　@  　@　@
     */
    public final static String ISSUE_TICKET = "1";

    /**
     * 4：（優）
     */
    public final static String PREFERENTIAL_SECURITIES = "4";

} @
