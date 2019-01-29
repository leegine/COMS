head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.45.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3InputUnitDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入力単位定数定義インタフェイス(WEB3InputUnitDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/28 劉江涛(sinocom) 新規作成
*/
package webbroker3.common.define;

/**
 * 入力単位 定数定義インタフェイス
 *
 * @@author 劉江涛
 * @@version 1.0
 */
public interface WEB3InputUnitDef
{
    /**
     * 1：1　@
     */
    public final static String ONE = "1";
    
    /**
     * 2：1万
     */
    public final static String TEN_THOUSAND = "2";

 }  @
