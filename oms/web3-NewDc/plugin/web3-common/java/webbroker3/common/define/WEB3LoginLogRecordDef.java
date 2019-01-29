head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.06.32;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3LoginLogRecordDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ログイン履歴登録要否定数定義インタフェイス(WEB3LoginLogRecordDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/06/13 栄イ(中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * ログイン履歴登録要否 定数定義インタフェイス
 *
 * @@author 栄イ(中訊)
 * @@version 1.0
 */
public interface WEB3LoginLogRecordDef
{
    /**
     * 0：実施しない
     */
    public final static String DEFAULT = "0";

    /**
     * 1：実施する
     */
    public final static String EXECUTE = "1";
}
@
