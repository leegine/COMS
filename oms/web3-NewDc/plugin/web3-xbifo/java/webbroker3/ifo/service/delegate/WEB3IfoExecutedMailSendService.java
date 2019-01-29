head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoExecutedMailSendService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP約定メール送信サービス(WEB3IfoExecutedMailSendService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/20 張宝楠 (中訊) 新規作成
*/

package webbroker3.ifo.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;

import webbroker3.common.WEB3BaseException;

/**
 * (先物OP約定メール送信)<BR>
 * 先物OP約定メール送信サービスインタフェイス<BR>
 * @@author 張宝楠(中訊)
 * @@version 1.0
 */
public interface WEB3IfoExecutedMailSendService extends Service 
{
    
    /**
     * 先物OP約定メール送信処理を行う。<BR>
     * <BR>
     * 引数.注文単位を判定し、以下のいずれかのメールデータを作成し<BR>
     * 先物OP約定メール送信テーブルに行を作成する。<BR>
     * <BR>
     * １）　@約定（失効）メールデータ作成<BR>
     * 　@失効理由コードがnullでない場合、create失効メール()にて失効メールを作成する。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@注文単位：　@注文単位オブジェクト<BR>
     * 　@失効理由コード：　@失効理由コード<BR>
     * <BR>
     * 　@（失効理由コード == null）の場合、create約定メール()にて約定メールを作成する。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@注文単位：　@注文単位オブジェクト<BR>
     * @@param l_orderUnit - 注文単位オブジェクト
     * @@param l_strCloseReasonCode - 失効理由コード。<BR>
     * 約定の場合はnullを指定する。<BR>
     * @@roseuid 408CC76800D3
     */
    public void sendMailProcess(OrderUnit l_orderUnit, String l_strCloseReasonCode) throws WEB3BaseException;
    
    /**
     * 引数の注文単位に該当する約定メール（または、失効メール）を無効にする。
     * @@param l_orderUnit - 注文単位オブジェクト
     * @@roseuid 408CC76800D6
     */
    public void undoSendMail(OrderUnit l_orderUnit) throws WEB3BaseException;
}
@
