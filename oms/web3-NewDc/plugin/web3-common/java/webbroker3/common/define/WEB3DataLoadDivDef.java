head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.25.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3DataLoadDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : データ取込区分定数定義インタフェイス(WEB3DataLoadDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/11/14 栄イ(中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 銀行入金通知テーブルのデータ取込区分定数定義インタフェイス
 *
 * @@author 栄イ(中訊)
 * @@version 1.0
 */

public interface WEB3DataLoadDivDef
{
    /**
     * A:HULFT
     */
    public final static String HULFT = "A";

    /**
     * B:バーチャル口座入金UL
     */
    public final static String BAATYARU_ACCOUNT_CHAHIN_UL = "B";

    /**
     * C:MQ連携
     */
    public final static String MQ_CONNECT = "C";
}
@
