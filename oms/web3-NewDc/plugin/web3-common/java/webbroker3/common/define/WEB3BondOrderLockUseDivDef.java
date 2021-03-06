head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.39.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3BondOrderLockUseDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券注文ロック使用区分定数定義インタフェイス(WEB3BondOrderLockUseDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 栄イ(中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 債券注文ロック使用区分定数定義インタフェイス
 *
 * @@author 栄イ(中訊)
 * @@version 1.0
 */
public interface WEB3BondOrderLockUseDivDef
{
    /**
     * 0：注文ロック区分を使用しない
     */
    public final static String DEFAULT = "0";

    /**
     * 1：注文ロック区分を使用する
     */
    public final static String EXCEPT = "1";
}
@
