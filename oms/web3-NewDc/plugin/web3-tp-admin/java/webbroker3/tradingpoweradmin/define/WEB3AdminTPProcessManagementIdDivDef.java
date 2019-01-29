head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.36.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPProcessManagementIdDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : プロセス管理ＩＤ定義インターフェース(WEB3AdminTPProcessManagementIdDivDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2005/03/07 asano(SCS) 新規作成
*/
package webbroker3.tradingpoweradmin.define;

/**
 * WEB3AdminTPProcessManagementIdDivDefインターフェース。
 * プロセス管理ＩＤを定義する。
 * @@author asano(SCS)
 * @@version 1.0
 *
 */
public interface WEB3AdminTPProcessManagementIdDivDef 
{

    /**
     * 0002: 大引け値洗い終了
     */
    public static final String NORMAL_END = "0002";

    /**
     * 0003: 前場引け値洗い終了
     */
    public static final String MORNING_CLOSED_END = "0003";

    /**
     * 0004: 大引け速報値洗い終了
     */
    public static final String PROMPT_REPORT_NORMAL_END = "0004";

    /**
     * 0005: 保証金自動振替終了
     */
    public static final String DEPOSIT_AUTOTRANSFER_END = "0005";

}@
