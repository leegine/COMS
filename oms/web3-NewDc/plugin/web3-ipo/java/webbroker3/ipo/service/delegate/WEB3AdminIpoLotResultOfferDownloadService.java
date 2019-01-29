head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.34.23;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIpoLotResultOfferDownloadService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者IPO抽選結果購入申込状況ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽインタフェイス(WEB3AdminIpoLotResultOfferDownloadService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/16 斉麟 (中訊) 新規作成
*/

package webbroker3.ipo.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * 管理者IPO抽選結果購入申込状況ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽインタフェイス
 */
public interface WEB3AdminIpoLotResultOfferDownloadService extends WEB3BusinessService 
{
    
    /**
     * 管理者IPO抽選結果購入申込状況ダウンロード処理を実施する。
     * @@param l_request - リクエストデータ
     * 
     * @@return WEB3GenResponse
     * @@roseuid 40E143F20066
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
