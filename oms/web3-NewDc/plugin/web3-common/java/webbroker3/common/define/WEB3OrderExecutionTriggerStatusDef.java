head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.55.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3OrderExecutionTriggerStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 約定トリガーテーブル処理状態 定数定義インタフェイス(WEB3OrderExecutionTriggerStatusDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/19 髙橋　@良和(SRA) 新規作成
*/
package webbroker3.common.define;

/**
 * 約定トリガーテーブル処理状態　@定数定義インタフェイス
 *
 * @@author 髙橋　@良和(SRA)
 * @@version 1.0
 */
public interface WEB3OrderExecutionTriggerStatusDef
{
    /**
     * 未稼動
     */
    public static final String THREAD_IDLE = "0";

    /**
     * 稼動
     */
    public static final String THREAD_PROCESSING = "1";

    /**
     * トリガー（AP呼出中）
     */
    public static final String THREAD_API_CALL = "2";
}
@
