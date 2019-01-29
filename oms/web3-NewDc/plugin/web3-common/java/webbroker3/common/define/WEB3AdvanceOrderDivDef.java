head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.37.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdvanceOrderDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 売・買 予約注文区分定数定義インタフェイス(WEB3AdvanceOrderDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/04 李海波(sinocom)　@新規作成
*/
package webbroker3.common.define;

/**
 * 売・買 予約注文区分　@定数定義インタフェイス
 *                                                                      
 * @@author 李海波
 * @@version 1.0
 */
public interface WEB3AdvanceOrderDivDef
{
    /**
     * 0 : 不可
     */
    public static final String NOT_POSSIBLE = "0";

    /**
     * 1 : 可能
     */
    public static final String POSSIBLE = "1";

}@
