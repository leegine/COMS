head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.37.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPDepositAutoTransferStopRegistDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 預り金自動振替停止登録区分インターフェース(WEB3AdminTPDepositAutoTransferStopRegistDiv.java)
Author Name      : Daiwa Institute of Research
Revision History : 2005/01/17 堀野 和美(FLJ) 新規作成
*/
package webbroker3.tradingpoweradmin.define;

/**
 * WEB3AdminTPDepositAutoTransferStopRegistDivインターフェース。
 * 預り金自動振替停止登録区分を定義する。
 * @@author 堀野 和美(FLJ)
 * @@version 1.0
 *
 */
public interface WEB3AdminTPDepositAutoTransferStopRegistDivDef {

    /**
     * 1: 登録
     */
    public static final String REGIST = "1";

    /**
     * 1: 解除
     */
    public static final String RELEASE = "2";

}
@
