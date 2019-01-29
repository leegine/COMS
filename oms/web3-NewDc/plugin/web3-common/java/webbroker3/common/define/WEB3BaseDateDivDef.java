head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.08.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3BaseDateDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 基準日区分定数定義インタフェイス(WEB3BaseDateDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/07 栄イ(中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 基準日区分 定数定義インタフェイス
 *
 * @@author 栄イ(中訊)
 * @@version 1.0
 */
public interface WEB3BaseDateDivDef
{
    /**
     * 1:受渡日
     */
    public final static String DELIVERY_DATE = "1";

    /**
     * 4:約定日
     */
    public final static String EXECUTE_DATE = "4";
}
@
