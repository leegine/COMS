head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.08.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityOrderExecNoticeDealedTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式出来通知キューテーブル出来通知区分 定数定義インタフェイス(WEB3EquityOrderExecNoticeDealedTypeDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/19 髙橋　@良和(SRA) 新規作成
*/
package webbroker3.common.define;

/**
 * 株式出来通知キューテーブル出来通知区分　@定数定義インタフェイス
 *
 * @@author 髙橋　@良和(SRA)
 * @@version 1.0
 */
public interface WEB3EquityOrderExecNoticeDealedTypeDef
{
    /**
     * 全部約定
     */
    public static final String ALL_ORDER_EXEC = "1";

    /**
     * 一部約定
     */
    public static final String PART_ORDER_EXEC = "2";

    /**
     * 現引・現渡取消
     */
    public static final String RECEIPT_SPOT_CALCEL = "4";

}
@
