head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.02.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AccOpenOrderDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入力区分(WEB3AccOpenOrderDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/09 張宝楠 (中訊) 新規作成
*/

package webbroker3.common.define;

/**
 * 入力区分 定数定義インタフェイス
 *
 * @@author 張宝楠
 * @@version 1.0
 */
public interface WEB3AccOpenOrderDivDef
{

    /**
     * 0:顧客
     */
    public final static String CUSTOMER  = "0";

    /**
     * 1：CCオペレータ　@2：管理者
     */
    public final static String CC_OPERATOR = "1";

    /**
     * 2：管理者
     */
    public final static String ADMINISTRATOR = "2";

}@
