head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.03.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFXAccOpenApplyDownloadRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・FX口座開設申込ダウンロードリクエスト(WEB3AdminFXAccOpenApplyDownloadRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/09 余新敏(中訊) 新規作成
                   2006/03/07 玉岡(SRA) 仕様変更・518
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者・FX口座開設申込ダウンロードリクエスト)<BR>
 * 管理者・FX口座開設申込ダウンロードリクエストクラス<BR>
 * <BR>
 * ※要求ページ番号・ページ内表示行数は、いづれも1を設定。<BR>
 * 　@ ステータス区分は”口座開設中”を設定。<BR>
 * 　@ 約諾書区分は”受領済”を設定。<BR>
 * 
 * @@author 余新敏
 * @@version 1.0
 */
public class WEB3AdminFXAccOpenApplyDownloadRequest extends WEB3AdminFXAccOpenApplyListRequest
{
     /**
      * PTYPE <BR>
      */
     public final static String PTYPE = "admin_fx_acc_open_apply_download";

     /**
      * serialVersionUID <BR>
      */
     public final static long serialVersionUID = 200602091550L;

     /**
      *コンストラクタ<BR>
      */
     public WEB3AdminFXAccOpenApplyDownloadRequest()
     {
         
     }
     
     /**
      * (createResponseの実装)<BR>
      * <BR>
      * 管理者・FX口座開設申込ダウンロードレスポンスオブジェクトを返却する。
      * @@return WEB3GenResponse
      */
     public WEB3GenResponse createResponse()
     {
         return new WEB3AdminFXAccOpenApplyDownloadResponse(this);
     }
}
@
