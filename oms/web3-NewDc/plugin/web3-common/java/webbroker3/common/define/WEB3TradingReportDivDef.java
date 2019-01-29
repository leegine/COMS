head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.43.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3TradingReportDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 取引報告書交付区分定数定義インタフェイス(WEB3TradingReportDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/24 栄イ(中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 取引報告書交付区分 定数定義インタフェイス
 *
 * @@author 栄イ(中訊)
 * @@version 1.0
 */
public interface WEB3TradingReportDivDef
{
    /**
     * 0：郵便配布
     */
    public final static String MAIL_DISTRIBUTION = "0";

    /**
     * 1：電子配布
     */
    public final static String ELECTRON_DISTRIBUTION = "1";
}
@
