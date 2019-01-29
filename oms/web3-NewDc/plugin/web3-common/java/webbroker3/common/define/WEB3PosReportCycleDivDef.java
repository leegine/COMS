head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.00.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3PosReportCycleDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 都度(WEB3PosReportCycleDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/09 張宝楠 (中訊) 新規作成
*/

package webbroker3.common.define;

/**
 * 都度 定数定義インタフェイス
 *
 * @@author 張宝楠
 * @@version 1.0
 */
public interface WEB3PosReportCycleDivDef
{

    /**
     * 0：不作成
     */
    public final static String NOT_CREATE  = "0";

    /**
     * 1：作成
     */
    public final static String CREATE  = "1";

    /**
     * 8：回答書電子交付
     */
    public final static String ANSWER_ELECTRONIC_DELIVER  = "8";

    /**
     * 9：電子交付
     */
    public final static String ELECTRONIC_DELIVER  = "9";

}@
