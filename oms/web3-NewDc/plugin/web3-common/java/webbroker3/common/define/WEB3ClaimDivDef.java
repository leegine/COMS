head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.03.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ClaimDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 請求区分定数定義インタフェイス(WEB3ClaimDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/23 李海波(sinocom) 新規作成
*/
package webbroker3.common.define;

/**
 * 請求区分 定数定義インタフェイス
 *
 * @@author 李海波
 * @@version 1.0
 */
public interface WEB3ClaimDivDef
{

    /**
     * 0：解約　@　@  　@　@　@　@　@  　@　@
     */
    public final static String SELL = "0";

    /**
     * 1：買取　@
     */ 
    public final static String BUY = "1";  

} @
