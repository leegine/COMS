head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.24.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3DeliveryTargetDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 交付対象  定数定義インタフェイス(WEB3DeliveryTargetDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/01/29 栄イ(中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 交付対象 定数定義インタフェイス
 *
 * @@author 魏キン(中訊)
 * @@version 1.0
 */
public interface WEB3DeliveryTargetDef
{
    /**
     *  0：全顧客
     */
    public static final String  ALL_ACCOUNT = "0";

    /**
     *  1：信用開設済顧客
     */
    public static final String  MARGIN_OPENED_ACCOUNT = "1";

    /**
     *  2：先物・オプション開設済顧客
     */
    public static final String  FUTURE_OPTION_OPENED_ACCOUNT = "2";
}
@
