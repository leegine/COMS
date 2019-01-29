head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.43.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3OperateReportSendMonthDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 運用報告書発送月定数定義インタフェイス(WEB3OperateReportSendMonthDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/04 李海波(sinocom)　@新規作成
*/
package webbroker3.common.define;

/**
 * 運用報告書発送月　@定数定義インタフェイス
 *                                                                      
 * @@author 李海波
 * @@version 1.0
 */
public interface WEB3OperateReportSendMonthDef
{
    /**
     * 00 : 無指定
     */
    public static final String NOT_DESIGNATE = "00";

    /**
     * MM : 指定月
     */
    public static final String DESIGNATE_MONTH = "MM";

}@
