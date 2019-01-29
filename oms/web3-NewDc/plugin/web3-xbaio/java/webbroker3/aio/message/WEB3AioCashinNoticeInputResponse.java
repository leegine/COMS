head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.04.15;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinNoticeInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入金連絡入力レスポンスクラス(WEB3AioCashinNoticeInputResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/11 屈陽 (中訊) 新規作成
                   2004/10/22 黄建 (中訊) レビュー  
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (入金連絡入力レスポンス)<BR>
 * 入金連絡入力レスポンスクラス
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public class WEB3AioCashinNoticeInputResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "aio_cashin_notice_input";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410111614L;    
    /**
     * (顧客名)
     */
    public String accountName;
    
    /**
     * (顧客の顧客コード)
     */
    public String accountCode;
    
    /**
     * (メールアドレス)<BR>
     * 登録されている顧客のメールアドレス
     */
    public String emailAddress;
    
    /**
     * (振込先金融機@関一覧)<BR>
     * 振込先金融機@関の一覧
     */
    public WEB3AioFinancialInstitutionUnit[] financialInstitutionUnits;
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AioCashinNoticeInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }

}
@
