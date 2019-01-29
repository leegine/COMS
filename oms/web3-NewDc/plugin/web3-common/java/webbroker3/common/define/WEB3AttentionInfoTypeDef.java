head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.08.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AttentionInfoTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 注意情報種別定数定義インタフェイス(WEB3AttentionInfoTypeDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/01/04 趙林鵬(中訊) 新規作成
*/

package webbroker3.common.define;

/**
 * 注意情報種別定数定義インタフェイス<BR>
 * (注意情報履歴テーブルの注意情報種別の參照)<BR>
 * <BR>
 * @@author 趙林鵬
 * @@version 1.0
 */
public interface WEB3AttentionInfoTypeDef
{
    /**
     * 1：売停情報
     */
    public final static String SELL_STOP_INFO = "1";

    /**
     * 2：制限値幅情報
     */
    public final static String LIMIT_RANGE_INFO = "2";

    /**
     * 3：フリーフォーマット
     */
    public final static String FREE_FORMAT = "3";
}@
