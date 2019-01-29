head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.27.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3LotStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 抽選状態定数定義インタフェイス(WEB3LotStatusDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/03 劉江涛(sinocom) 新規作成
*/
package webbroker3.common.define;

/**
 * 抽選状態 定数定義インタフェイス
 *
 * @@author 劉江涛
 * @@version 1.0
 */
public interface WEB3LotStatusDef
{

    /**
     * 0：DEFAULT（抽選未済）　@ 　@　@
     */
    public final static String DEFAULT = "0";

    /**
     * 1：新規抽選済　@　@　@　@ 　@　@
     */
    public final static String OPEN_LOTTERY_END = "1";
    
     /**
     * 2：繰上抽選済　@　@　@
     */
    public final static String ADVANCED_LOTTERY_END = "2";
}@
