head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.48.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccSettingListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 連続設定一覧レスポンス(WEB3SuccSettingListResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/17 呉艶飛(中訊) 新規作成
*/

package webbroker3.triggerorder.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (連続設定一覧レスポンス)<BR>
 * 連続設定一覧レスポンスクラス<BR>
 * @@author 呉艶飛
 * @@version 1.0
 */
public class WEB3SuccSettingListResponse extends WEB3GenResponse 
{
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200510111000L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_settingList";
    
    /**
     * (発注日一覧)<BR>
     * 発注日の一覧<BR>
     */
    public Date[] orderBizDateList;
    
    /**
     * (注文明細一覧)<BR>
     * 注文明細一覧<BR>
     */
    public WEB3SuccOrderUnit[] orderUnitsList = null;
    
    /**
     * (表示ページ番号)<BR>
     * 実際に表示するページ位置を指定<BR>
     * ※先頭ページを"1"とする<BR>
     */
    public String pageIndex = "0";
    
    /**
     * (総ページ数)<BR>
     * 総ページ数<BR>
     */
    public String totalPages = "0";
    
    /**
     * (総レコード数)<BR>
     * 総レコード数<BR>
     */
    public String totalRecords = "0";
    
    /**
     * (先物銘柄情報)<BR>
     * 先物銘柄情報<BR>
     * <BR>
     * リクエストの商品区分一覧に"先物"が含まれる場合セット。<BR>
     */
    public WEB3SuccProductInfo[] futuresProductInfo = null;
    
    /**
     * (オプション銘柄情報)<BR>
     * オプション銘柄情報<BR>
     * <BR>
     * リクエストの商品区分一覧に"オプション"が含まれる場合セット。<BR>
     */
    public WEB3SuccProductInfo[] optionsProductInfo = null;
    
    /**
     * @@roseuid 43489603035B
     */
    public WEB3SuccSettingListResponse() 
    {
     
    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3SuccSettingListResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }     
}
@
