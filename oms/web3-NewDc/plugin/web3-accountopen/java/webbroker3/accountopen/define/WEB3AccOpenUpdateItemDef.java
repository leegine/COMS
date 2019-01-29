head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.41.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenUpdateItemDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 更新項目 定数定義インタフェイス（WEB3AccOpenUpdateItemDef.java）
Author Name      : Daiwa Institute of Research
Revision History : 2009/08/13 武波 (中訊) 新規作成 モデルNo.164
*/
package webbroker3.accountopen.define;

/**
 * 更新項目 定数定義インタフェイス
 *
 * @@author 武波
 * @@version 1.0
 */
public interface WEB3AccOpenUpdateItemDef
{
    /**
     * 1：印刷切替
     */
    public static final String PRINT_CHANGE = "1";

    /**
     * 2：受領切替
     */
    public static final String RECEIVE_CHANGE = "2";

    /**
     * 3：削除切替
     */
    public static final String DELETE_CHANGE = "3";

}
@
