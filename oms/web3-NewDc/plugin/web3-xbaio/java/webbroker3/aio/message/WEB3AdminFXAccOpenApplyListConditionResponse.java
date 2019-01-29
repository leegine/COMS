head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.13.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFXAccOpenApplyListConditionResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 管理者・FX口座開設申込一覧条件入力レスポンス(WEB3AdminFXAccOpenApplyListConditionResponse)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/01/20 黄建(中訊) 新規作成
 */

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者・FX口座開設申込一覧条件入力レスポンス) <BR>
 * 管理者・FX口座開設申込一覧条件入力レスポンスクラス
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3AdminFXAccOpenApplyListConditionResponse extends
    WEB3GenResponse
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "admin_fx_acc_open_apply_list_condition";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200501171456L;

    /**
     * (申込日（自）) <BR>
     * 申込日（自） <BR>
     * (YYYYMMDD)
     */
    public String applyDateFrom;

    /**
     * (申込日（至）) <BR>
     * 申込日（至） <BR>
     * (YYYYMMDD)
     */
    public String applyDateTo;

    /**
     * @@roseuid 41E78FB7036B
     */
    public WEB3AdminFXAccOpenApplyListConditionResponse()
    {
    }
    
    /**
     * (コンストラクタ)<BR>
     * 引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AdminFXAccOpenApplyListConditionResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}@
