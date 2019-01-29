head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.45.57;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioFxAccountOpenDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3AioFxAccountOpenDivDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/18 黄建 (中訊) 新規作成
*/

package webbroker3.aio.define;

/**
 * 管理者・FX口座開設申込一覧リクエストのMRF口座状況区分　@定数定義インタフェイス
 *                                                                     
 * @@author 黄建
 * @@version 1.0
 */

public interface WEB3AioFxAccountOpenDivDef
{
    /**
     * 1：開設 
     */
    public static final String OPEN = "1";
    
    /**
     * 2：未開設
     */
    public static final String NOT_OPEN = "2";
}

@
