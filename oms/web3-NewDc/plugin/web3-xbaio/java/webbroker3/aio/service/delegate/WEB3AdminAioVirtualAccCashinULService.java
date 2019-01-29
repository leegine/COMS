head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.20.50;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AdminAioVirtualAccCashinULService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : バーチャル口座入金ULサービス(WEB3AdminAioVirtualAccCashinULService)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/05/09 肖志偉 (中訊) 新規作成                   
*/

package webbroker3.aio.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (バーチャル口座入金ULサービス)<BR>
 * 
 * @@author 肖志偉(中訊)
 * @@version 1.0
 */

public interface WEB3AdminAioVirtualAccCashinULService extends WEB3BusinessService 
{
    
    /**
     * バーチャル口座入金アップロード処理を行う。<BR>  
     * <BR>
     * １）　@リクエストデータの型により、以下の通りメソッドをコールする。<BR>  
     * <BR>
     * ○ 引数のリクエストデータが、バーチャル口座入金ｱｯﾌﾟﾛｰﾄﾞ入力ﾘｸｴｽﾄの場合<BR>  
     * 　@－getアップロード画面()をコールする。  <BR>
     * <BR>
     * ○ 引数のリクエストデータが、バーチャル口座入金ｱｯﾌﾟﾛｰﾄﾞ確認ﾘｸｴｽﾄの場合<BR>  
     * 　@－validateアップロードファ@イル()をコールする。  <BR>
     * <BR>
     * ○ 引数のリクエストデータが、バーチャル口座入金ｱｯﾌﾟﾛｰﾄﾞ完了ﾘｸｴｽﾄの場合<BR>  
     * 　@－submitアップロードファ@イル()をコールする。  <BR>
     * <BR>
     * ○ 引数のリクエストデータが、バーチャル口座入金ｱｯﾌﾟﾛｰﾄﾞ中止ﾘｸｴｽﾄの場合<BR>  
     * 　@－undoアップロード()をコールする。  <BR>
     * <BR>
     * ※サービスメソッドにて例外が発生した場合は、<BR>  
     * 　@例外オブジェクトの追加文字列（WEB3BaseException.errorMessage）<BR>  
     * をレスポンスデータ.errorMessageにセットする。 <BR>
     * <BR>
     * @@param l_request - リクエストデータ
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@throws WEB3BaseException
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException;
}
@
