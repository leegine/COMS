head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.47.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3DbCurrentPriceCheckDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : DB時価余力チェック区分定数定義インタフェイス(WEB3DbCurrentPriceCheckDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/15 栄イ (中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 証券会社テーブルのDB時価余力チェック区分　@定数定義インタフェイス
 *                                                                     
 * @@author 栄イ
 * @@version 1.0
 */

public interface WEB3DbCurrentPriceCheckDivDef
{
    /**
     * 0：未実施
     */
    public static final String NOT_ENFORCEMENT = "0";

    /**
     * 1：実施([DB時価テーブル]を使用して余力チェックを行う)
     */
    public static final String ENFORCEMENT = "1";
}
@
