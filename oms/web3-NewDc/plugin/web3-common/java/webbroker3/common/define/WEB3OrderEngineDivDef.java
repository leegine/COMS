head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.56.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3OrderEngineDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 発注エンジン区分定数定義インターフェイス(WEB3OrderEngineDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/26 凌建平(sinocom) 新規作成
*/

package webbroker3.common.define;

/**
 * 発注エンジン区分定数定義インターフェイス
 *
 * @@author 凌建平
 * @@version 1.0
 */
public interface WEB3OrderEngineDivDef
{
    /**
     * 1：SONAR
     */
    public static final String SONAR = "1";

    /**
     * 2：DEOS
     */
    public static final String DEOS = "2";
} @
