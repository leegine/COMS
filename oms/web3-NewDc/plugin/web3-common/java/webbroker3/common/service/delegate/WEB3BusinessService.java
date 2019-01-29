head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3BusinessService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@//ソース ファ@イル: D:\\WEBBROKER3-大和\\詳細設計\\src\\webbroker3\\common\\service\\delegate\\WEB3BusinessService.java

package webbroker3.common.service.delegate;

import webbroker3.common.*;
import webbroker3.common.message.*;
import com.fitechlabs.xtrade.kernel.boot.Service;

/**
 * 業務アプリケーション（上り処理）の業務ロジックのインタフェース。<BR>
 * <BR>
 * @@author 中尾　@寿彦(SRA)
 * @@version 1.0
 * @@see com.fitechlabs.xtrade.kernel.boot.Service
 */
public interface WEB3BusinessService extends Service 
{
   
   /**
    * 引数で与えられたリクエストを基に業務処理を行い、処理結果をレスポンスに設定<BR>
    * して返す。<BR>
    * <BR>
    * @@param l_request リクエスト
    * @@return 処理結果が設定されたレスポンス@@throws 
    * webbroker3.common.WEB3BaseException
    * @@roseuid 403494170141
    */
   public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
