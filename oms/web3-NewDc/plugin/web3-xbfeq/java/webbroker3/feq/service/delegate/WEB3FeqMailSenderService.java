head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqMailSenderService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式メール送信サービス(WEB3FeqMailSenderService)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/13  艾興(中訊) 新規作成
                   2005/07/26 王亞洲(中訊) レビュー
*/
package webbroker3.feq.service.delegate;

import webbroker3.common.WEB3BaseException;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqOrderUnit;


/**
 * (外国株式メール送信サービス) <BR>
 * 外国株式メール送信サービスインタフェイス
 * @@author 艾興
 * @@version 1.0 
 */
public interface WEB3FeqMailSenderService extends Service 
{
    
    /**
     * (create新規注文Mail) <BR>
     * 新規注文の内容を、メール送信テーブル、<BR>
     * メール送信拡張テーブルに登録する。<BR>
     * @@param l_feqOrderUnit - (注文単位)
     * @@roseuid 4295F45603E7
     */
    public void createNewOrderMail(FeqOrderUnit l_feqOrderUnit) throws WEB3BaseException;
    
    /**
     * (create訂正注文Mail) <BR>
     * 訂正注文の内容を、メール送信テーブル、 <BR>
     * メール送信拡張テーブルに登録する。 <BR>
     * @@param l_feqOrderUnit - (注文単位)
     * @@roseuid 4299952E0161
     */
    public void createChangeOrderMail(FeqOrderUnit l_feqOrderUnit) throws WEB3BaseException;
    
    /**
     * (create取消注文Mail) <BR>
     * 取消注文の内容を、メール送信テーブル、 <BR>
     * メール送信拡張テーブルに登録する。 <BR>
     * @@param l_feqOrderUnit - (注文単位)
     * @@roseuid 429995A401A0
     */
    public void createCancelOrderMail(FeqOrderUnit l_feqOrderUnit) throws WEB3BaseException;
}
@
