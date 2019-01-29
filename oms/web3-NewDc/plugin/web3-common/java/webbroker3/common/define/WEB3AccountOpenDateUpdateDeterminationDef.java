head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.55.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AccountOpenDateUpdateDeterminationDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座開設日更新判定区分定数定義インタフェイス(WEB3AccountOpenDateUpdateDeterminationDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2010/02/23 趙林鵬(中訊) 新規作成
*/

package webbroker3.common.define;

/**
 * 口座開設日更新判定区分定数定義インタフェイス<BR>
 * (証券会社プリファ@レンステーブルのプリファ@レンスの値の參照)<BR>
 * <BR>
 * @@author 趙林鵬
 * @@version 1.0
 */
public interface WEB3AccountOpenDateUpdateDeterminationDef
{
    /**
     * 0：更新しない
     */
    public final static String DEFAULT = "0";

    /**
     * 1：更新する
     */
    public final static String EXECUTE = "1";
}@
