head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.01.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ApproveStatusType.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 承認状態区分定数定義インタフェイス(WEB3ApproveStatusType.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/04/25 栄イ(中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 承認状態区分 定数定義インタフェイス
 *
 * @@author 栄イ(中訊)
 * @@version 1.0
 */
public interface WEB3ApproveStatusType
{
    /**
     * 0：未承認
     */
    public final static String UNAPPROVED = "0";

    /**
     * 1：承認済
     */
    public final static String APPROVED = "1";

    /**
     * 2：非承認
     */
    public final static String NON_APPROVED = "2";

    /**
     * 9：エラー
     */
    public final static String ERROR = "9";
}
@
