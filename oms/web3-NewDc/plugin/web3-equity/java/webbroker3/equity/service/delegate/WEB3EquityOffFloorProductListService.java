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
filename	WEB3EquityOffFloorProductListService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 立会外分売銘柄一覧サービス(WEB3EquityOffFloorProductListService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/22 森川 (SRA) 新規作成
                   2005/01/06 岡村和明(SRA) JavaDoc修正
*/
package webbroker3.equity.service.delegate;

import webbroker3.common.service.delegate.WEB3BusinessService;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.WEB3BaseException;

/**
 * （立会外分売銘柄一覧サービス）。<BR>
 * <BR>
 * 立会外分売銘柄一覧サービスインタフェース。
 * @@version 1.0
 */
public interface WEB3EquityOffFloorProductListService extends WEB3BusinessService 
{
   /**
    * (execute)。<BR>
    * <BR>
    * 立会外分売銘柄一覧サービス処理を実施する。<BR>
    * <BR>
    * @@param l_request - リクエストデータ
    * @@return webbroker3.common.message.WEB3GenResponse
    * @@throws webbroker3.common.WEB3BaseException
    */
   public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
