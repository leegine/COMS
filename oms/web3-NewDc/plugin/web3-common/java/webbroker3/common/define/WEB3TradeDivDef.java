head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.50.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3TradeDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 取引種類(WEB3TradeDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/09 張宝楠 (中訊) 新規作成
*/

package webbroker3.common.define;

/**
 * 取引種類 定数定義インタフェイス
 *
 * @@author 張宝楠
 * @@version 1.0
 */
public interface WEB3TradeDivDef
{

    /**
     * ０：希望しない
     */
    public final static String NOT_HOPE  = "0";

    /**
     * 1：希望する
     */
    public final static String HOPE  = "1";

}@
