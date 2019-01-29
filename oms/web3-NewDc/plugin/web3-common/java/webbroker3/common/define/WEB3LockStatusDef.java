head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.51.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3LockStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 注文ロック区分定数定義インタフェイス(WEB3LockStatusDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/28 栄イ (中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 注文ロック区分　@定数定義インタフェイス
 *
 * @@author 栄イ (中訊)
 * @@version 1.0
 */
public interface WEB3LockStatusDef
{
    /**
     * ０：その他
     */
    public static final String OTHER = "0";

    /**
     * 1：解除中
     */
    public static final String RELEASING = "1";

    /**
     * 2：ロック中
     */
    public static final String LOCKING = "2";
}
@
