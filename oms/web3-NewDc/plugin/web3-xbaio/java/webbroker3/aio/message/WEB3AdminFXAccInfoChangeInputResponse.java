head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.51.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFXAccInfoChangeInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 管理者・FX口座情報変更入力レスポンス(WEB3AdminFXAccInfoChangeInputResponse)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/01/20 黄建(中訊) 新規作成
                    2006/02/08 黄建(中訊) 仕様変更・モデル481
                    2006/02/09 余新敏(中訊) 仕様変更・モデル458
 */

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者・FX口座情報変更入力レスポンス) <BR>
 * 管理者・FX口座情報変更入力レスポンスクラス
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3AdminFXAccInfoChangeInputResponse extends WEB3GenResponse
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "admin_fx_acc_info_change_input";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200501171456L;

    /**
     * (部店コード) <BR>
     * 部店コード
     */
    public String branchCode;

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
     * FX口座情報一覧
     */
    public WEB3FXAccInformationUnit[] fxAccInformationList;

    /**
     * (口座開設状況区分) <BR>
     * 口座開設状況区分 <BR>
     * <BR>
     * 1：開設済 <BR>
     * 2：振替停止<BR>
     * 9：抹消<BR>
     * 99：口座抹消
     */
    public String accountOpenStatusDiv;

    /**
     * @@roseuid 41E78FE4029F
     */
    public WEB3AdminFXAccInfoChangeInputResponse()
    {
    }
    
    /**
     * (コンストラクタ)<BR>
     * 引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AdminFXAccInfoChangeInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}@
