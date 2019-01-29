head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.26.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d885c296483;
filename	WEB3RlsNotifyTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 「通知経路区分」の定数定義クラス(WEB3RlsNotifyTypeDef.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2005/09/16 劉(FLJ) 新規作成
 */
package webbroker3.rlsgateway.define;

/**
 * 「通知経路区分」の定数定義クラス<br>
 *
 * @@author　@劉(FLJ)
 * @@version 1.0
 */
public interface WEB3RlsNotifyTypeDef
{

    /**
     * ルールエンジン（Default）
     */
    public static final String RLS = "0";

    /**
     * 手動発注(顧客)
     */
    public static final String MANUAL_USER = "1";

    /**
     * 手動発注(オペレータ)
     */
    public static final String MANUAL_OPERATOR = "2";

    /**
     * 手動発注(管理者)
     */
    public static final String MANUAL_ADMIN = "3";

    /**
     * 通知経路：監視(オンライン)
     */
    public static final String OBSERVER_ONLINE = "4";

}
@
