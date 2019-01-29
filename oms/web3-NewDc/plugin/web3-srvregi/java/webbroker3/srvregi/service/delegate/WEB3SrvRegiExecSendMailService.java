head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.51.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiExecSendMailService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用確認メール送信サービス(WEB3SrvRegiExecSendMailService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/15 李頴淵 新規作成
*/

package webbroker3.srvregi.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3GentradeSrvRegiApplication;


/**
 * (サービス利用確認メール送信サービス)<BR>
 * @@author 李頴淵
 * @@version 1.0
 *  
 * サービス利用確認メール送信サービスインターフェイス<BR>
 */
public interface WEB3SrvRegiExecSendMailService extends Service 
{
    
    /**
     * サービス利用申込確認メール送信処理を行う。<BR>
     * @@param l_serviceRegist - (サービス申込登録)<BR>
     * サービス申込登録オブジェクト<BR>
     * @@roseuid 414933E6034E
     */
    public void sendMailProcess(WEB3GentradeSrvRegiApplication l_serviceRegist) throws WEB3BaseException; 
}
@
