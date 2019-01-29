head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.47.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3InformAccSwElecDeliApplyConfResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座切替・電子交付申込確認レスポンス(WEB3InformAccSwElecDeliApplyConfResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/18 孫洪江 (中訊) 新規作成 仕様変更モデル097
*/

package webbroker3.inform.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (口座切替・電子交付申込確認レスポンス)<BR>
 * 口座切替・電子交付申込確認レスポンスクラス<BR>
 *
 * @@author 孫洪江
 * @@version 1.0
 */
public class WEB3InformAccSwElecDeliApplyConfResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "inform_acc_sw_elec_deli_apply_conf";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200707191048L;

    public WEB3InformAccSwElecDeliApplyConfResponse()
    {

    }

    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     * <BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3InformAccSwElecDeliApplyConfResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
