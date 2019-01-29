head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.50.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3InformAccSwElecDeliApplyCmpResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座切替・電子交付申込完了レスポンス(WEB3InformAccSwElecDeliApplyCmpResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/18 孫洪江 (中訊) 新規作成 仕様変更モデル097
*/

package webbroker3.inform.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (口座切替・電子交付申込完了レスポンス)<BR>
 * 口座切替・電子交付申込完了レスポンスクラス<BR>
 *
 * @@author 孫洪江
 * @@version 1.0
 */
public class WEB3InformAccSwElecDeliApplyCmpResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "inform_acc_sw_elec_deli_apply_cmp";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200707191055L;

    /**
     * (日付情報)<BR>
     * 口座切替・電子交付申込日付情報<BR>
     */
    public WEB3AdminInformAccSwitchElecDeliAppDtInfo dateInfo;

    public WEB3InformAccSwElecDeliApplyCmpResponse()
    {

    }

    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     * <BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3InformAccSwElecDeliApplyCmpResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
