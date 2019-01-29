head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.33.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3LotResultDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 抽選結果定数定義インタフェイス(WEB3LotResultDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/04 劉江涛(sinocom) 新規作成
*/
package webbroker3.common.define;

/**
 * 抽選結果 定数定義インタフェイス
 *
 * @@author 劉江涛
 * @@version 1.0
 */
public interface WEB3LotResultDef
{

    /**
     * 0：DEFAULT（未抽選）　@
     */
    public final static String DEFAULT = "0";

    /**
     * 1：当選　@ 　@　@　@ 　@　@
     */
    public final static String ELECTION = "1";
    
    /**
     * 2：補欠　@　@　@　@　@
     */
    public final static String SUPPLEMENT = "2";
    
    /**
     * 3：落選　@　@　@　@　@　@
     */
    public final static String DEFEAT = "3";
}@
