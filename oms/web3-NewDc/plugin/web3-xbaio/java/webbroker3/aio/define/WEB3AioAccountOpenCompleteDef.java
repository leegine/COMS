head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.48.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioAccountOpenCompleteDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3AioAccountOpenCompleteDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/18 黄建 (中訊) 新規作成
*/

package webbroker3.aio.define;

/**
 * 管理者・FX口座開設ステータス更新共通リクエスト の更新後ステータス区分　@定数定義インタフェイス
 *                                                                     
 * @@author 黄建
 * @@version 1.0
 */

public interface WEB3AioAccountOpenCompleteDef
{
    /**
     * 1：口座開設完了 
     */
    public static final String OPEN_COMPLETE = "1";
    
    /**
     * 9：削除
     */
    public static final String DELETE = "9";
    
	/**
	 * 99：口座抹消
	 */
	public static final String OPEN_DELETE = "99";
}
@
