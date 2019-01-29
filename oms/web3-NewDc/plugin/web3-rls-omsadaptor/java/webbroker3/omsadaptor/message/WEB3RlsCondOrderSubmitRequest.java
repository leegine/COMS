head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.27.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	2144d885d4c6839;
filename	WEB3RlsCondOrderSubmitRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : ルールエンジンから一件処理通知リクエスト(WEB3RlsCondOrderSubmitRequest.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/09/15 劉 (FLJ)新規作成
 */

package webbroker3.omsadaptor.message;

import com.fitechlabs.xtrade.kernel.message.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;

/**
 * （ルールエンジンから一件処理通知リクエスト）。<br>
 * <br>
 * ルールエンジンから一件処理通知リクエスト
 * @@author 劉 (FLJ)
 * @@version 1.0
 */
public class WEB3RlsCondOrderSubmitRequest
    extends Request
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "rls_cond_order_submit";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200509150000L;

    /**
     * 口座ID<BR>
     */
    public long account_id;

    /**
     * 補助口座ID<BR>
     */
    public long sub_account_id;

    /**
     * 条件付注文タイプ<BR>
     */
    public int order_type;

    /**
     * 条件付注文ID<BR>
     */
    public long con_order_id;

    /**
     * 条件付注文の商品タイプ<BR>
     */
    public ProductTypeEnum product_type;

}
@
