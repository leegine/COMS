head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.47.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioErrorCommentDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : エラーコメント 定数定義インタフェイス(WEB3AioErrorCommentDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/05 徐宏偉 (中訊) 新規作成
Revesion History : 2007/03/13 何文敏 (中訊) モデルNo.713
Revesion History : 2009/02/20 柴双紅 (中訊) モデルNo.1106,No.1107
*/
package webbroker3.aio.define;

/**
 * エラーコメント 定数定義インタフェイス
 *
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public interface WEB3AioErrorCommentDef
{
    /**
     * 外貨コード不整合エラー 
     */
    public static final String FOREIGN_ERROR = "外貨コード不整合エラー";
    
    /**
     * 円換算額桁数エラー
     */
    public static final String CONVER_AMOUNT_LENGTH_ERROR ="円換算額桁数エラー";

    /**
     * 勘定日エラー
     */
    public static final String SETTLEMENTDATE_ERROR ="勘定日エラー";

    /**
     * 入金起算日エラー
     */
    public static final String DEPOSITDATA_BASEDATE_ERROR ="入金起算日エラー";
}
@
