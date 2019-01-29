head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.46.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3InformAccSwElecDeliApplyInpResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座切替・電子交付申込入力レスポンス(WEB3InformAccSwElecDeliApplyInpResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/19 栄イ(中訊) 新規作成 仕様変更モデル097
*/
package webbroker3.inform.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (口座切替・電子交付申込入力レスポンス)<BR>
 * 口座切替・電子交付申込入力レスポンスクラス<BR>
 *
 * @@author 栄イ
 * @@version 1.0
 */
public class WEB3InformAccSwElecDeliApplyInpResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "inform_acc_sw_elec_deli_apply_inp";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200707190952L;

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
     * (顧客名)<BR>
     * 顧客名
     */
    public String accountName;

    /**
     * (変更前情報)<BR>
     * 変更前情報
     */
    public WEB3AdminInformAccSwitchElecDeliApplyInfo beforeInfo;

    /**
     * @@roseuid 4663A9D7006D
     */
    public WEB3InformAccSwElecDeliApplyInpResponse()
    {

    }

    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    public WEB3InformAccSwElecDeliApplyInpResponse(WEB3InformAccSwElecDeliApplyInpRequest l_request)
    {
        super(l_request);
    }
}
@
