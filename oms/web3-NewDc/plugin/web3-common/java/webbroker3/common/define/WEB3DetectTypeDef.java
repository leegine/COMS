head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.04.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3DetectTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 検知区分定数定義インタフェイス(WEB3DetectTypeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/25 栄イ(中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 検知区分 定数定義インタフェイス
 *
 * @@author 栄イ(中訊)
 * @@version 1.0
 */
public interface WEB3DetectTypeDef
{
    /**
     * 1：オンライン
     */
    public static final String ON_LINE = "1";

    /**
     * 2：前場引バッチ
     */
    public static final String BEFORE_TRADING_BATCH = "2";

    /**
     * 3:後場引バッチ
     */
    public static final String AFTER_TRADING_BATCH = "3";
}
@
