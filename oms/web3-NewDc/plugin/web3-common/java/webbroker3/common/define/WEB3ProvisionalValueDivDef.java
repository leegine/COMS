head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.50.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ProvisionalValueDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 仮条件区分定数定義インタフェイス(WEB3ProvisionalValueDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/03 劉江涛(sinocom) 新規作成
*/
package webbroker3.common.define;

/**
 * 仮条件区分 定数定義インタフェイス
 *
 * @@author 劉江涛
 * @@version 1.0
 */
public interface WEB3ProvisionalValueDivDef
{

    /**
     * 1：実価格　@　@　@　@　@　@  　@　@
     */
    public final static String TRUE_VALUE = "1";

    /**
     * 2：ディスカウント率　@
     */
    public final static String DISCOUNT_RATIO = "2";
    
}@
