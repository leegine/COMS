head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityExecutedMailSenderService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 約定メール送信サービスインタフェース(WEB3EquityExecutedMailSenderService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/16 艾興 (中訊) 新規作成
*/
package webbroker3.equity.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;

import webbroker3.common.WEB3BaseException;

/**
 * （約定メール送信サービスインタフェース）。
 * @@version 1.0
 */
public interface WEB3EquityExecutedMailSenderService extends Service 
{
    
    /**
     * 株式約定メール送信処理を行う。
     * @@param l_orderUnit - (注文単位)<BR>
     * 株式注文単位オブジェクト
     * @@param l_strReasonCode - 失効理由コード。<BR>
     * 約定の場合はnullを指定する。
     * @@roseuid 4121548E0234
     */
    public void sendMailProcess(OrderUnit l_orderUnit, String l_strReasonCode) throws WEB3BaseException;
    
	/**
	 * 株式約定メール送信処理を行う。
	 * @@param l_orderUnit - (注文単位)<BR>
	 * 株式注文単位オブジェクト
	 * @@param l_strReasonCode - 失効理由コード。<BR>
	 * 約定の場合はnullを指定する。
	 * @@param l_blnConfirmAlreadyIns - (登録済レコード有無確認フラグ)<BR>
	 * 登録済レコード有無確認フラグ。<BR>
	 * @@roseuid 4121548E0234
	 */
	public void sendMailProcess(OrderUnit l_orderUnit, String l_strReasonCode, boolean l_blnConfirmAlreadyIns) throws WEB3BaseException;
    
    /**
     * 引数の注文単位に該当する約定メール（または失効メール）を無効にする。
     * @@param l_orderUnit - 注文単位。
     * @@roseuid 4121548E0253
     */
    public void undoSendMail(OrderUnit l_orderUnit) throws WEB3BaseException;
}
@
