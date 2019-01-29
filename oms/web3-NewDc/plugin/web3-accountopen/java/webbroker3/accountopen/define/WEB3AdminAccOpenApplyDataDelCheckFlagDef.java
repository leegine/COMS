head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.41.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenApplyDataDelCheckFlagDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者口座開設資料請求データ削除確認フラグ(WEB3AdminAccOpenApplyDataDelCheckFlagDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/12/15 劉仁和(中訊) 新規作成 モデルNo.160
*/

package webbroker3.accountopen.define;

/**
 * (管理者口座開設資料請求データ削除確認フラグ)<BR>
 * 管理者口座開設資料請求データ削除確認フラグ<BR>
 * <BR>
 * @@author 劉仁和
 * @@version 1.0
 */
public interface WEB3AdminAccOpenApplyDataDelCheckFlagDef
{
    /**
     * 0：未チェック
     */
    public static final String UNCHECK_FLAG = "0";

    /**
     * 1：チェック
     */
    public static final String CHECK_FLAG = "1";
}
@
