head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.45.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3TermDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3TermDivDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/02 jia-yuanchun(sinocom)　@新規作成
*/
package webbroker3.common.define;

/**
 * 長短等区分　@定数定義インタフェイス
 *                                                                      
 * @@author jia-yuanchun
 * @@version 1.0
 */
public interface WEB3TermDivDef
{
    /**
     * 1:一般上場
     */
    public static final String NORMAL_SECTION = "1";

    /**
     * 2:特定信用
     */
    public static final String SPECIAL_MARGIN = "2";

    /**
     * 3:長期上場
     */
    public static final String LONG_SECTION = "3";

    /**
     * 4:長期特定
     */
    public static final String LONG_SPECIAL = "4";
}
@
