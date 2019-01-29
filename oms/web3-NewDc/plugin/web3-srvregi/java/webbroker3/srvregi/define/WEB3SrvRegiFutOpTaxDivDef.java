head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.47.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiFutOpTaxDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP口座区分（大証）(WEB3SrvRegiFutOpTaxDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/18 李頴淵(sinocom) 新規作成
*/
package webbroker3.srvregi.define;

/**
 * 先物OP口座区分（大証）
 *
 * @@author 李頴淵
 * @@version 1.0
 */
public interface WEB3SrvRegiFutOpTaxDivDef
{
    /**
     * 0：先物OP口座未開設 　@
     */
    public final static String FUTURE_OP_NO_OPEN = "0";
    
    /**
     * 1：オプション開設　@
     */
    public final static String OPTION_OPEN = "1";
    
    /**
     * 2：先物開設　@
     */
    public final static String FUTURE_OPEN = "2";
    
    /**
     * 3：先物オプション開設　@
     */
    public final static String FUTURE_OPTION_OPEN = "3";
}
@
