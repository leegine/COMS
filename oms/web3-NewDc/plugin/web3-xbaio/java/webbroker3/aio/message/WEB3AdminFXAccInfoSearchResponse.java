head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.51.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFXAccInfoSearchResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 管理者・FX口座情報検索レスポンス(WEB3AdminFXAccInfoSearchResponse)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/01/20 黄建(中訊) 新規作成
                    2006/02/08 黄建(中訊) 仕様変更・モデル481
                    2006/02/09 余新敏(中訊) 仕様変更・モデル458
 Revesion History : 2008/05/19 柴双紅(中訊) 仕様変更 モデルNo.866
 */

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者・FX口座情報検索レスポンス) <BR>
 * 管理者・FX口座情報検索レスポンスクラス
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3AdminFXAccInfoSearchResponse extends WEB3GenResponse
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "admin_fx_acc_info_search";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200501171456L;

    /**
     * (顧客コード) <BR>
     * 顧客コード
     */
    public String accountCode;

    /**
     * (（FX）ログインID) <BR>
     * 為替保証金取引用のログインID
     */
    public String fxLoginId;

    /**
     * (（FX）名前（姓）) <BR>
     * 為替保証金取引用の名前（姓）
     */
    public String fxLastName;

    /**
     * (（FX）名前（名）) <BR>
     * 為替保証金取引用の名前（名）
     */
    public String fxFirstName;

    /**
     * (（FX）メールアドレス) <BR>
     * 為替保証金取引用のメールアドレス
     */
    public String fxMailAddress;

    /**
     * (FX口座情報一覧) <BR>
     * FX口座情報の一覧
     */
    public WEB3FXAccInformationUnit[] fxAccInformationList;

    /**
     * (口座開設状況区分) <BR>
     * 1：開設済 <BR>
     * 2：振替停止<BR>
     * 9：抹消<BR>
     * 99：口座抹消
     */
    public String accountOpenStatusDiv;

    /**
     * (FXシステムコード)<BR>
     * FXシステムコード
     */
    public String fxSystemCode;

    /**
     * @@roseuid 41E78FFF02DE
     */
    public WEB3AdminFXAccInfoSearchResponse()
    {
    }
    
    /**
     * (コンストラクタ)<BR>
     * 引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AdminFXAccInfoSearchResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}@
