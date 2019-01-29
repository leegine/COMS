head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.38.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3DealingStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : IPO需要予測状況表・処理状況 定数定義インタフェイス(WEB3DealingStatusDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/06/16 凌建平(中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 処理状況 定数定義インタフェイス
 *
 * @@author 凌建平
 * @@version 1.0
 */
public interface WEB3DealingStatusDef
{

    /**
     * 0：未処理
     */
    public final static String NOT_DEAL = "0";

    /**
     * 1：処理中
     */
    public final static String DEALING = "1";

    /**
     * 2：処理終了
     */
    public final static String DEALED = "2";

    /**
     * 9：エラー
     */
    public final static String ERROR = "9";
}@
