head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.01.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3CheckDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座開設審査待ちテーブル・審査区分 定数定義インタフェイス(WEB3CheckDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/06/12 凌建平(中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 審査区分 定数定義インタフェイス
 *
 * @@author 凌建平
 * @@version 1.0
 */
public interface WEB3CheckDivDef
{

    /**
     * 0：審査待ち
     */
    public final static String CHECK_WAITING = "0";

    /**
     * 1：認可
     */
    public final static String AGREE = "1";

    /**
     * 2：否認
     */
    public final static String DISAGREE = "2";
}@
