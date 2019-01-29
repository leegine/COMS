head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.42.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3TriggerorderSucorderCarryoverDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 連続注文繰越実施区分定数定義インタフェイス(WEB3TriggerorderSucorderCarryoverDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/13/12 栄イ(中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 連続注文繰越実施区分 定数定義インタフェイス
 *
 * @@author 栄イ(中訊)
 * @@version 1.0
 */
public interface WEB3TriggerorderSucorderCarryoverDef
{
    /**
     * 0:実施しない
     */
    public final static String DEFAULT = "0";

    /**
     * 1:実施する
     */
    public final static String EXECUTE = "1";
}
@
