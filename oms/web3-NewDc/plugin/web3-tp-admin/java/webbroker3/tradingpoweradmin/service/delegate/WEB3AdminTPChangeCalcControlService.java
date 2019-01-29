head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.11.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPChangeCalcControlService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 余力制御機@能変更サービスインターフェース(WEB3AdminTPChangeCalcControlService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2005/01/06 堀野 和美(FLJ) 新規作成
*/
package webbroker3.tradingpoweradmin.service.delegate;

import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * 余力制御機@能変更サービスインターフェース
 *
 * 余力制御機@能（信用新規建停止、先物OP新規建停止、出金停止、その他商品買付停止に関
 * わる）変更のインターフェース。
 * Web3BusinessServiceを拡張。
 */
public interface WEB3AdminTPChangeCalcControlService extends WEB3BusinessService
{
}
@
