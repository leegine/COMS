head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.26.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3VoucherStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 伝票作成ステータス(WEB3VoucherStatusDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/09 張宝楠 (中訊) 新規作成
*/

package webbroker3.common.define;

/**
 * 伝票作成ステータス 定数定義インタフェイス
 *
 * @@author 張宝楠
 * @@version 1.0
 */
public interface WEB3VoucherStatusDef
{

    /**
     * 0：DEFAULT（伝票未作成）
     */
    public final static String DEFAULT  = "0";

    /**
     * 1：作成済
     */
    public final static String CREATE_COMPLETE  = "1";

    /**
     * 2：送信保留中
     */
    public final static String SEND_RESERVING  = "2";

    /**
     * 3：送信済
     */
    public final static String SEND_COMPLETE  = "3";

    /**
     * 4：受信済
     */
    public final static String RECEIVE_COMPLETE  = "4";

    /**
     * 5：送信エラー
     */
    public final static String SEND_ERROR  = "5";

    /**
     * 6：受信エラー
     */
    public final static String RECEIVE_ERROR  = "6";

}@
