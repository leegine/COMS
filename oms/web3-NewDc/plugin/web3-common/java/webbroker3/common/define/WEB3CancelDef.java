head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.37.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3CancelDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 取消区分定数定義インタフェイス(WEB3CancelDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/23 李海波(sinocom) 新規作成
*/
package webbroker3.common.define;

/**
 * 取消区分 定数定義インタフェイス
 *
 * @@author 李海波
 * @@version 1.0
 */
public interface WEB3CancelDef
{

    /**
     * '-：取消　@　@  　@　@　@　@　@  　@　@
     */
    public final static String CANCLE = "-";

} @
