head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.18.31;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoCommissionRegistAccountInquiryService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報手数料変更申込顧客問合せｻｰﾋﾞｽ(WEB3AdminAccInfoCommissionRegistAccountInquiryService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10  彭巍(中訊) 新規作成
*/

package webbroker3.accountinfo.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (管理者お客様情報手数料変更申込顧客問合せｻｰﾋﾞｽ)<BR>
 * 管理者お客様情報手数料変更申込顧客問合せｻｰﾋﾞｽインタフェイス<BR>
 * @@author 彭巍
 * @@version 1.0
 */
public interface WEB3AdminAccInfoCommissionRegistAccountInquiryService extends WEB3BusinessService 
{
    
    /**
     * 手数料変更申込顧客問合せ表示処理を行う。
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 415141570230
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
