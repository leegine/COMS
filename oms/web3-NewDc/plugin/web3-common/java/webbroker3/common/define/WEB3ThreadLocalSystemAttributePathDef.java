head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.41.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ThreadLocalSystemAttributePathDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : スレッドローカルシステム属性設定パス　@定数定義インタフェイス(WEB3ThreadLocalSystemAttributePathDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 太田 正俊(SRA) 新規作成
Revesion History : 2007/07/03 栄イ (中訊)【先物オプション】仕様変更・モデルNo.772
Revesion History : 2008/10/28 栄イ (中訊)【株管理者】仕様変更・モデルNo.176
Revesion History : 2008/04/22 栄イ (中訊)【トリガー注文】仕様変更・モデルNo.336
Revesion History : 2010/01/25 趙林鵬 (中訊)【外国株式】仕様変更・モデルNo.536
Revesion History : 2010/03/05 趙林鵬 (中訊)【外国株式】仕様変更・モデルNo.541
Revesion History : 2010/09/15 趙林鵬 (中訊)【外国株式】仕様変更・モデルNo.553
*/
package webbroker3.common.define;

/**
 * スレッドローカルシステム属性設定パス　@定数定義インタフェイス
 *
 * @@author  太田 正俊(SRA)
 * @@version 1.0
 */
public interface WEB3ThreadLocalSystemAttributePathDef
{
    /**
     * ThreadLocalに保存するスキップ特殊執行取扱停止
     */
    public static final String SKIP_TRIGGER_ORDER_STOP = "web3.skiptriggerorderstop";

    /**
     * RLSへの非同期通知フラグ
     */
    public static final String ORDER_CARRYOVER_ASYNC_RLS_NOTIFY = "web3.ordercarryoverasyncrlsnotify";

    /**
     * 強制決済注文発注審査スキップフラグ
     */
    public static final String FORCED_SETTLE_ORDER_VALIDATION_SKIP = "web3.forcedsettleordervalidationskip";

    /**
     * 予約注文訂正フラグ
     */
    public static final String SUCC_CHANGE_ORDER = "web3.succchangeorder";

    /**
     * 当日為替レートチェックフラグ
     */
    public static final String DAY_EXCHANGE_CHECK_FLAG = "web3.dayexchangecheckflag";

    /**
     * 当日為替登録フラグ
     */
    public static final String DAY_EXCHANGE_REGISTRATION_FLAG = "web3.dayexchangeregistrationflag";
    
    /**
     * 為替ネッティングフラグ
     */
    public static final String NETTING_EXCHANGE_FLAG = "web3.nettingexchangeflag";
}@
