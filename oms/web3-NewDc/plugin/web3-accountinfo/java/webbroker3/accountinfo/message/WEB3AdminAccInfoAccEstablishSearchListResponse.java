head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.04.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoAccEstablishSearchListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報新規口座開設・口座移管・ログインロック顧客検索一覧レスポンス(WEB3AdminAccInfoAccEstablishSearchListResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/10/09  何文敏(中訊) 新規作成
*/

package webbroker3.accountinfo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者お客様情報新規口座開設・口座移管・ログインロック顧客検索一覧レスポンス)<BR>
 * 管理者お客様情報新規口座開設・口座移管・ログインロック顧客検索一覧レスポンス<BR>
 * <BR>
 * @@author 何文敏
 * @@version 1.0 
 */

public class WEB3AdminAccInfoAccEstablishSearchListResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_acc_info_acc_establish_search_list";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200610082152L;

    /**
     * @@roseuid 418F39F700AB
     */
    public WEB3AdminAccInfoAccEstablishSearchListResponse()
    {

    }
    
    /**
     * (部店コード)<BR>
     * 部店コード<BR> 
     */
    public String[] branchCode;
    
    /**
     * (扱者コード)<BR>
     * 扱者コード<BR> 
     */
    public String traderCode;
    
    /**
     * (顧客コード)<BR>
     * 顧客コード<BR> 
     */
    public String accountCode;
    
    /**
     * (顧客名（カナ）)<BR>
     * 顧客名（カナ）<BR> 
     */
    public String accountNameKana;
    
    /**
     * (口座種別)<BR>
     * 口座種別<BR>
     * <BR> 
     * 0：　@全て<BR>
     * 1：　@個人客<BR> 
     * 2：　@法@人客<BR>
     */
    public String accountTypeCode;
    
    /**
     * (データ内容番号)<BR>
     * データ内容番号 <BR>
     * <BR>
     * 00：　@データ内容未選択<BR> 
     * 01：　@新規口座開設案内用データ<BR> 
     * 02：　@振込みカード用データ<BR> 
     * 03：　@口座移管案内用データ<BR>
     */
    public String dataContentDiv;
    
    /**
     * (口座開設日（自）)<BR>
     * 口座開設日（自）<BR> 
     */
    public Date accountOpenDateFrom;
    
    /**
     * (口座開設日（至）)<BR>
     * 口座開設日（至）<BR> 
     */
    public Date accountOpenDateTo;
    
    /**
     * (ログインロック区分)<BR>
     * ログインロック区分<BR>
     * <BR>
     * 0：　@全て<BR> 
     * 1：　@ログインロック客<BR> 
     */
    public String loginLockDiv;
    
    /**
     * (総ページ数)<BR>
     * 総ページ数<BR>
     */
    public String totalPages;
    
    
    /**
     * (総レコード数)<BR>
     * 総レコード数<BR> 
     */
    public String totalRecords;
    
    /**
     * (表示ページ番号)<BR>
     * 表示ページ番号<BR> 
     */
    public String pageIndex;
    
    /**
     * (新規口座開設・口座移管・ログインロック顧客情報)<BR>
     * 新規口座開設・口座移管・ログインロック顧客情報<BR>
     */
    public WEB3AccInfoAccEstablishSearchInfo[] accOpenLockSearchList;
    
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminAccInfoAccEstablishSearchListResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
