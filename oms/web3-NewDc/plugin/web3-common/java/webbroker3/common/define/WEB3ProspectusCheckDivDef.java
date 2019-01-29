head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.31.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ProspectusCheckDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 目論見書閲覧チェック区分定数定義インタフェイス(WEB3ProspectusCheckDivDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/11 栄イ(中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 目論見書閲覧チェック区分 定数定義インタフェイス
 *
 * @@author 栄イ(中訊)
 * @@version 1.0
 */
public interface WEB3ProspectusCheckDivDef
{
    /**
     * 0：目論見書をチェックしない
     */
    public final static String PROSPECTUS_NOT_CHECK  = "0";

    /**
     * １：目論見書をチェックする
     */
    public final static String PROSPECTUS_CHECK  = "1";
}
@
