head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.41.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ListRangeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 表示対象  定数定義インタフェイス(WEB3ListRangeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/05/16　@橋本佳代子 (SRA) 新規作成
*/
package webbroker3.common.define;

/**
 * 表示対象 定数定義インタフェイス<BR>
 * <BR>
 * @@author 橋本佳代子(SRA)
 * @@version 1.0
 */
public interface WEB3ListRangeDef
{
    /**
     *  1：ポートフォリオ登録分のみ<BR>
     */
    public static final String ONLY_PORTFOLIO = "1";

    /**
     *  2：特定分のみ<BR>
     */
    public static final String ONLY_SPECIAL_ACCOUNT = "2";

    /**
     *  3：両方合せる<BR>
     */
    public static final String BOTH = "3";
}
@
