head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.03.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3DepositAutoTransferStopRegistDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 保証金自動振替停止登録区分インターフェース(WEB3DepositAutoTransferStopRegistDiv.java)
Author Name      : Daiwa Institute of Research
Revision History : 2010/01/08 武波(中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 保証金自動振替停止登録区分インターフェース
 * 保証金自動振替停止登録区分を定義する。
 * @@author 武波
 * @@version 1.0
 */
public interface WEB3DepositAutoTransferStopRegistDivDef
{

    /**
     * 1: 登録
     */
    public static final String DEFAULT = "1";

    /**
     * 2：解除 DEFAULT "1"
     */
    public static final String RELEASE = "2";

}
@
