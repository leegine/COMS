head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.43.32;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3InvestPurposeDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資目的区分(WEB3InvestPurposeDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/09 張宝楠 (中訊) 新規作成
*/

package webbroker3.common.define;

/**
 * 投資目的区分 定数定義インタフェイス
 *
 * @@author 張宝楠
 * @@version 1.0
 */
public interface WEB3InvestPurposeDivDef
{

    /**
     * 1：長期的視野に立ったキャピタルゲイン重視
     */
    public final static String LONG_TERM_PURPOSE  = "1";

    /**
     * 2：中期的視野に立ったキャピタルゲイン重視
     */
    public final static String MEDIUM_TERM_PURPOSE  = "2";

    /**
     * 3：短期的視野に立ったキャピタルゲイン重視
     */
    public final static String SHORT_TERM_PURPOSE  = "3";

}@
