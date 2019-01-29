head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.38.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3FeqSleBrokerDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外株市場連動対象市場かどうか定数定義インタフェイス(WEB3FeqSleBrokerDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/18 栄イ(中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 外株市場連動対象市場かどうか 定数定義インタフェイス
 *
 * @@author 栄イ(中訊)
 * @@version 1.0
 */
public interface WEB3FeqSleBrokerDef
{
    /**
     * true：市場連動対象
     */
    public final static String MARKET_INTERLOCKING_MOVEMENT_OBJECT = "true";
}
@
