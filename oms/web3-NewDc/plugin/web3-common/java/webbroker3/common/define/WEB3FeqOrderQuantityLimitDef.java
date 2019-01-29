head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.02.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3FeqOrderQuantityLimitDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 最大注文数量チェック可否定数定義インタフェイス(WEB3FeqOrderQuantityLimitDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/18 栄イ(中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 最大注文数量チェック可否 定数定義インタフェイス
 *
 * @@author 栄イ(中訊)
 * @@version 1.0
 */
public interface WEB3FeqOrderQuantityLimitDef
{
    /**
     * 600：上限単位：香港市場
     */
    public final static String MACIMUM_HONGKONG_MARKET = "600";
}
@
