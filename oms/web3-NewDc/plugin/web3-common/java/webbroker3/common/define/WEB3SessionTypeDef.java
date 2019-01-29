head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.45.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3SessionTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 立会区分定数定義インタフェイス(WEB3SessionTypeDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/06/12 栄イ(中訊) 新規作成
Revision History : 2008/05/13 趙林鵬(中訊) NULL：その他を追加
*/
package webbroker3.common.define;

/**
 * 立会区分 定数定義インタフェイス
 *
 * @@author 栄イ(中訊)
 * @@version 1.0
 */
public interface WEB3SessionTypeDef
{
    /**
     * 1：夕場（夕場実施する会社の夕場時間帯のみ設定）
     */
    public final static String EVENING_SESSION = "1";

    /**
     * NULL：その他
     */
    public final static String OTHER  = null;
}
@
