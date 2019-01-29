head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.00.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3OrderAcceptStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 注文受付ステータス 定数定義インタフェイス(WEB3OrderAcceptStatusDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/09 今井　@高史(SRA) 新規作成
*/
package webbroker3.common.define;

/**
 * 注文受付ステータス　@定数定義インタフェイス
 *
 * @@author 今井　@高史(SRA)
 * @@version 1.0
 */
public interface WEB3OrderAcceptStatusDef
{

    /**
     * 0 : 通常
     */
    public static final String NORMAL = "0";
    
    /**
     * 1 : バッチ処理中
     */
    public static final String BATCH = "1";

    /**
     * 2 : 緊急停止中
     */
    public static final String SCRAM = "2";
}
@
