head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.49.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3SrvAppliAttributeProcDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 処理区分定数定義インタフェイス(WEB3SrvAppliAttributeProcDivDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/24 栄イ(中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * サービス申込属性テーブルの処理区分　@定数定義インタフェイス
 *                                                                     
 * @@author 栄イ(中訊)
 * @@version 1.0
 */
public interface WEB3SrvAppliAttributeProcDivDef
{
    /**
     * 0 or null :未処理
     */
    public final static String NOT_PROCESSED = "0";

    /**
     * 1:処理済
     */
    public final static String PROCESSED = "1";
}
@
