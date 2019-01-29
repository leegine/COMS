head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.04.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3CarryoverEndTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  注文繰越処理区分　@定数定義インタフェイス(WEB3CarryoverEndTypeDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/24 鄒政 (中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 注文繰越処理区分　@定数定義インタフェイス。
 *
 * @@author 鄒政
 * @@version 1.0
 */
public interface WEB3CarryoverEndTypeDef
{
    /**
     * 0：未処理 
     */
    public static final String NOT_STARTED_PROCESS = "0";

    /**
     * 1：処理済　@ 
     */
    public static final String COMPLETE_PROCESS = "1";

    /**
     * 2：注文繰越AP呼出中
     */
    public static final String CALL_CARRYOVER_AP = "2";

    /**
     * 9：エラー
     */
    public static final String ERROR = "9";

}@
