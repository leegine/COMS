head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.44.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3BondAssetCheckDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券保有資産チェック定数定義インタフェイス(WEB3BondAssetCheckDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 栄イ(中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 債券保有資産チェック定数定義インタフェイス
 *
 * @@author 栄イ(中訊)
 * @@version 1.0
 */
public interface WEB3BondAssetCheckDef
{
    /**
     * 0：保有資産をチェックする。
     */
    public final static String DEFAULT = "0";

    /**
     * 1：保有資産をチェックしない。
     */
    public final static String EXCEPT = "1";
}
@
