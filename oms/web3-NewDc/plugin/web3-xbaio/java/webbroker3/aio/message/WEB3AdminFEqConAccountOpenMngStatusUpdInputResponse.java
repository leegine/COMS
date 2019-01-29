head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.05.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFEqConAccountOpenMngStatusUpdInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外株口座開設管理ステータス更新入力レスポンス(WEB3AdminFEqConAccountOpenMngStatusUpdInputResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/18 周勇(中訊) 新規作成
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (外株口座開設管理ステータス更新入力レスポンス)<BR>
 * 外株口座開設管理ステータス更新入力レスポンスクラス
 * 
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3AdminFEqConAccountOpenMngStatusUpdInputResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_feq_con_account_open_mng_status_upd_input";
    
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200503171808L;
    
    /**
     * (部店コード)<BR>
     * 部店コード
     */
    public String branchCode;
    
    /**
     * (顧客コード)<BR>
     * 顧客コード
     */
    public String accountCode;
    
    /**
     * (名前（姓）)<BR>
     * 外国株式取引用の名前（姓）
     */
    public String accountFamilyName;
    
    /**
     * (名前（名）)<BR>
     * 外国株式取引用の名前（名）
     */
    public String accountName;
    
    /**
     * (メールアドレス)<BR>
     * メールアドレス
     */
    public String mailAddress;
    
    /**
     * (外株口座番号)<BR>
     * 外国株式取引用の口座番号<BR>
     * <BR>
     * 口座開設状況区分＝0(口座開設中)または2(口座開設エラー）の場合はnull
     */
    public String fstkAccountCode;
    
    /**
     * (ステータス区分)<BR>
     * 0：口座開設中<BR>
     * 1：口座開設完了<BR>
     * 2：口座開設エラー<BR>
     * 9：削除
     */
    public String statusDiv;
    
    /**
     * @@roseuid 423552E9007D
     */
    public WEB3AdminFEqConAccountOpenMngStatusUpdInputResponse() 
    {
     
    }
    
    /**
     * (コンストラクタ)<BR>
     * 引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AdminFEqConAccountOpenMngStatusUpdInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
