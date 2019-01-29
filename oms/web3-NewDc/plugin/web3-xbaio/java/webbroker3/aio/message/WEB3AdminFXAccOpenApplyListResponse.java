head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.57.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFXAccOpenApplyListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 管理者・FX口座開設申込一覧レスポンス(WEB3AdminFXAccOpenApplyListResponse)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/01/20 黄建(中訊) 新規作成
                    2006/02/09 余新敏(中訊) 仕様変更・モデル458
                    2006/02/09 鄭徳懇(中訊) 仕様変更・モデル475
                    2006/02/22 情野(SRA) 仕様変更・モデル500
 */

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者・FX口座開設申込一覧レスポンス) <BR>
 * 管理者・FX口座開設申込一覧レスポンスクラス
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3AdminFXAccOpenApplyListResponse extends WEB3GenResponse
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "admin_fx_acc_open_apply_list";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200501171456L;

    /**
     * (部店コード) <BR>
     * 画面にて選択された部店コード <BR>
     * ※null：全部店
     */
    public String branchCode;

    /**
     * (ステータス区分) <BR>
     * 画面にて選択されたステータス <BR>
     * <BR>
     * 0：口座開設中 <BR>
     * 1：口座開設完了 <BR>
     * 2：口座開設エラー <BR>
     * 3：ダウンロード済 <BR>
     * 9：削除
     */
    public String statusDiv;

    /**
     * (MRF口座状況区分) <BR>
     * 画面にて選択されたMRF口座状況 <BR>
     * <BR>
     * 1：開設 <BR>
     * 2：未開設
     */
    public String mrfAccountStatusDiv;

    /**
     * (申込日（自）) <BR>
     * 画面にて入力された申込日（自） <BR>
     * (YYYYMMDDhh) <BR>
     * <BR>
     * ※null：指定なし
     */
    public String applyHourFrom;

    /**
     * (申込日（至）) <BR>
     * 画面にて入力された申込日（至） <BR>
     * (YYYYMMDDhh) <BR>
     * <BR>
     * ※null：指定なし
     */
    public String applyHourTo;

    /**
     * (FX口座開設申込明細一覧) <BR>
     * FX口座開設申込明細の一覧
     */
    public WEB3FXAccOpenApplyUnit[] fxAccOpenApplyList;

    /**
     * (表示ページ番号) <BR>
     * 表示ページ番号
     */
    public String pageIndex;

    /**
     * (総ページ数) <BR>
     * 総ページ数
     */
    public String totalPages;

    /**
     * (総レコード数) <BR>
     * 総レコード数
     */
    public String totalRecords;

    /**
     * (約諾書区分)<BR>
     * 画面にて選択された約諾書区分<BR>
     * <BR>
     * 0：未送信<BR>
     * 1：送信済<BR>
     * 2：受領済<BR>
     * <BR>
     * ※全ての場合は、nullをセット。<BR>
     */
    public String agreementDiv;
    
    /**
     * @@roseuid 41E78FB800EA
     */
    public WEB3AdminFXAccOpenApplyListResponse()
    {
    }
    
    /**
     * (コンストラクタ)<BR>
     * 引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AdminFXAccOpenApplyListResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}@
