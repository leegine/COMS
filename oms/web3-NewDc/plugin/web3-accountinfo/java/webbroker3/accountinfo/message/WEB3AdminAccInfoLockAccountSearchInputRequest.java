head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.11.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoLockAccountSearchInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報ロック顧客問合せ一覧入力リクエスト(WEB3AdminAccInfoLockAccountSearchInputRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/09/19 呉艶飛 (中訊) 新規作成
*/
package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者お客様情報ロック顧客問合せ一覧入力リクエスト)<BR>
 * 管理者お客様情報ロック顧客問合せ一覧入力リクエスト<BR>
 * <BR>
 * @@author 呉艶飛<BR>
 * @@version 1.0<BR>
 */
public class WEB3AdminAccInfoLockAccountSearchInputRequest extends WEB3GenRequest 
{
        
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_lockAccountSearchInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200509191350L;
    
     /**
      * (管理者お客様情報ロック顧客問合せ一覧入力リクエスト)<BR>
      */
     public WEB3AdminAccInfoLockAccountSearchInputRequest()
     {

     }
     
     /**
      * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
      *<BR>
      * @@return レスポンスオブジェクト
      */
     public WEB3GenResponse createResponse()
     {
         return new WEB3AdminAccInfoLockAccountSearchInputResponse(this);
     }
}
@
