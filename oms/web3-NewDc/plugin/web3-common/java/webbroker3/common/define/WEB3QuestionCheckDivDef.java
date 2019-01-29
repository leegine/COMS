head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.05.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3QuestionCheckDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 質問同意チェック実施区分定数定義インタフェイス(WEB3QuestionCheckDivDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/03/19 趙林鵬(中訊) 新規作成
*/

package webbroker3.common.define;

/**
 * 質問同意チェック実施区分定数定義インタフェイス<BR>
 * (会社別FXシステム条件テーブルの質問同意チェック実施区分の參照)<BR>
 * <BR>
 * @@author 趙林鵬
 * @@version 1.0
 */
public interface WEB3QuestionCheckDivDef
{
    /**
     * ０：チェックしない
     */
    public final static String NOT_CHECK = "0";

    /**
     * １：チェックする
     */
    public final static String CHECK = "1";
}
@
