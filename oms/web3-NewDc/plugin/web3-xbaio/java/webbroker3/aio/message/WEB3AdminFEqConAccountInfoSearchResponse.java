head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.18.30;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFEqConAccountInfoSearchResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外株口座情報検索レスポンス(WEB3AdminFEqConAccountInfoSearchResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/18 周勇(中訊) 新規作成
                   2006/02/08 黄建(中訊) 仕様変更・モデル481
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (外株口座情報検索レスポンス)<BR>
 * 外株口座情報検索レスポンスクラス
 * 
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3AdminFEqConAccountInfoSearchResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_feq_con_account_info_search";
    
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200503171811L;
    
    /**
     * (部店コード)<BR>
     * 部店コード
     */
    public String branchCode;
    
    /**
     * (顧客ID)<BR>
     * 外国株式顧客ID
     */
    public String accountId;
    
    /**
     * (外株口座番号)<BR>
     * 外国株式口座番号
     */
    public String fstkAccountCode;
    
    /**
     * (名前（姓）)<BR>
     * 名前（姓）
     */
    public String familyName;
    
    /**
     * (名前（名）)<BR>
     * 名前（名）
     */
    public String name;
    
    /**
     * (メールアドレス)<BR>
     * メールアドレス
     */
    public String mailAddress;
    
    /**
     * (顧客コード)<BR>
     * 顧客コード
     */
    public String accountCode;
    
    /**
     * (口座開設状況区分)<BR>
     * 外国株式口座状況区分<BR>
     * <BR>
     * 1： 開設済<BR>
     * 9：抹消 <BR>
     * 99:口座抹消
     */
    public String accountOpenStatusDiv;
    
    /**
     * @@roseuid 423554FE0157
     */
    public WEB3AdminFEqConAccountInfoSearchResponse() 
    {
     
    }
    
    /**
     * (コンストラクタ)<BR>
     * 引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AdminFEqConAccountInfoSearchResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
