head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.47.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3DealtDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 取扱可能不可能フラグ定数定義クラス(WEB3DealtDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/23 今井　@高史(SRA) 新規作成
Revesion History : 2004/05/17 鄒政 (中訊) 訂正
*/
package webbroker3.common.define;

/**
 * 取扱可能不可能フラグ定数定義クラス
 *
 * @@author 今井　@高史(SRA)
 * @@version 1.0
 */
public interface WEB3DealtDef
{

    /**
     * 0：取扱不可
     */
    public final static String CAN_NOT_DEALT = "0";

    /**
     * 1：取扱可能
     */
    public final static String CAN_DEALT = "1";
}
@
