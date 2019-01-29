head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.52.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3VoucherCreateStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 伝票作成状況定数定義インタフェイス(WEB3VoucherCreateStatusDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/05/29 栄イ(中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 各種連絡テーブルの伝票作成状況 定数定義インタフェイス
 *
 * @@author 栄イ(中訊)
 * @@version 1.0
 */
public interface WEB3VoucherCreateStatusDef
{
    /**
     * 0：未作成
     */
    public final static String NOT_CREATE  = "0";

    /**
     * 1：作成済
     */
    public final static String CREATE_COMPLETE  = "1";

    /**
     * 2：受付中
     */
    public final static String INT_ACCEPT  = "2";

    /**
     * 3：受付完了
     */
    public final static String ACCEPT_COMPLETE  = "3";

    /**
     * 4：受付エラー
     */
    public final static String ACCEPT_ERROR  = "4";
}
@
