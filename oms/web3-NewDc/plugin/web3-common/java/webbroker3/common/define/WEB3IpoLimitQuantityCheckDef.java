head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.51.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3IpoLimitQuantityCheckDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : IPO上限申告株数チェック定数定義インタフェイス(WEB3IpoLimitQuantityCheckDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/04 栄イ(中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * IPO上限申告株数チェック 定数定義インタフェイス
 *
 * @@author 栄イ(中訊)
 * @@version 1.0
 */
public interface WEB3IpoLimitQuantityCheckDef
{
    /**
     * 0：チェックなし
     */
    public final static String DEFAULT = "0";

    /**
     * 1：申告株数チェック要
     */
    public final static String CHECK_QUANTITY = "1";
}
@
