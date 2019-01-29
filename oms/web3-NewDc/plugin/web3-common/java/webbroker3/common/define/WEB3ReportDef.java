head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.57.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ReportDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 報告書(WEB3ReportDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/09 張宝楠 (中訊) 新規作成
*/

package webbroker3.common.define;

/**
 * 報告書 定数定義インタフェイス
 *
 * @@author 張宝楠
 * @@version 1.0
 */
public interface WEB3ReportDef
{

    /**
     * 0：発送
     */
    public final static String DISPATCH  = "0";

    /**
     * 1：発送止
     */
    public final static String DISPATCH_STOP  = "1";

    /**
     * 2：白封筒
     */
    public final static String WHITE_ENVELOPE  = "2";

    /**
     * 3：送付先
     */
    public final static String DISPATCH_TO  = "3";

}@
