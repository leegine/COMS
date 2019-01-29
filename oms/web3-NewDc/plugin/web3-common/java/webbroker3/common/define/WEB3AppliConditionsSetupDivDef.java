head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.42.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AppliConditionsSetupDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 申込条件設定区分(WEB3AppliConditionsSetupDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/11 郭英(sinocom) 新規作成
*/
package webbroker3.common.define;

/**
 * 申込要サービステーブル.申込条件設定区分
 *
 * @@author 郭英
 * @@version 1.0
 */
public interface WEB3AppliConditionsSetupDivDef
{

    /**
     * 1：AND
     */
    public final static String AND = "1";

    /**
     * 2：OR　@　@　@
     */
    public final static String OR = "2";
    
}@
