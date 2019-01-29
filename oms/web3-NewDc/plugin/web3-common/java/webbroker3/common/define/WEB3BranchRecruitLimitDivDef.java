head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.32.41;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3BranchRecruitLimitDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 応募枠部店別管理区分定数定義インタフェイス(WEB3BranchRecruitLimitDivDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/26 栄イ (中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 応募枠部店別管理区分 定数定義インタフェイス
 *
 * @@author 栄イ (中訊)
 * @@version 1.0
 */
public interface WEB3BranchRecruitLimitDivDef
{
    /**
     * 0:部店別管理しない
     */
    public final static String BRANCH_LIMIT_NOT  = "0";

    /**
     * 1：部店別管理する
     */
    public final static String BRANCH_LIMIT  = "1";
}
@
