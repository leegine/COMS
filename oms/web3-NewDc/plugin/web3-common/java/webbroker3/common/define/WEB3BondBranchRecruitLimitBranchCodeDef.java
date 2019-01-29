head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.33.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3BondBranchRecruitLimitBranchCodeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 部店コード定数定義インタフェイス(WEB3BondBranchRecruitLimitBranchCodeDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/26 栄イ (中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 債券部店別応募枠テーブルの部店コード 定数定義インタフェイス
 *
 * @@author 栄イ (中訊)
 * @@version 1.0
 */
public interface WEB3BondBranchRecruitLimitBranchCodeDef
{
    /**
     * ’---’は全部店とする。
     */
    public final static String ALL_BRANCH  = "---";
}
@
