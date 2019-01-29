head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.32.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3DepositCheckDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 預りチェック定数定義インタフェイス(WEB3DepositCheckDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/23 李海波(sinocom) 新規作成
*/
package webbroker3.common.define;

/**
 * 預りチェック 定数定義インタフェイス
 *
 * @@author 李海波
 * @@version 1.0
 */
public interface WEB3DepositCheckDivDef
{

    /**
     * 1：預りチェック要  　@　@　@　@　@  　@　@
     */
    public final static String IRRELEVENT = "1";

    /**
     * 9：預りチェック不要　@
     */
    public final static String SELF_ACCESSMENT = "9";  

} @
