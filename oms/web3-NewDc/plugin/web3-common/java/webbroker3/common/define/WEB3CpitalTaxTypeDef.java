head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.34.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3CpitalTaxTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3CpitalTaxTypeDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/26 du-min(sinocom)　@新規作成
*/
package webbroker3.common.define;

/**
 * 譲渡課税区分 　@定数定義インタフェイス
 *
 * @@author du-min
 * @@version 1.0
 */
public interface WEB3CpitalTaxTypeDef
{
    /**
     * 0 : 無関係
     */
    public static final String IRRELEVANT = "0";

    /**
     * 1 : 申告
     */
    public static final String ASSESSMENT = "1";

    /**
     * 2 : 源泉
     */
    public static final String SOURCE = "2";

}
@
