head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.57.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3DaemonTriggerStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : デーモントリガーテーブル処理状態 定数定義インタフェイス(WEB3DaemonTriggerStatusDef.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/02/07 劉(FLJ) 新規作成
 Revesion History : 2006/03/21 凌建平(中訊) ＤＢレイアウトによって、修正しました
 */

package webbroker3.common.define;

/**
 * デーモントリガーテーブル処理状態　@定数定義インタフェイス
 *
 * @@author 劉(FLJ)
 * @@version 1.0
 */
public interface WEB3DaemonTriggerStatusDef
{
    /**
     * 0:未稼動
     */
    public static final String THREAD_IDLE = "0";

    /**
     * 1:処理中
     */
    public static final String THREAD_PROCESSING = "1";

    /**
     * 2:トリガー（AP呼出中）
     */
    public static final String THREAD_API_CALL = "2";
}
@
