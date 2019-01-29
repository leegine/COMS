head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.40.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3FeqAccOpenResultCodeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3FeqAccOpenResultCodeDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/3/22 屈陽 (中訊) 新規作成
*/

package webbroker3.common.define;

/**
 * UWG口座開設状況テーブルの受付結果コード　@定数定義インタフェイス
 *                                                                     
 * @@author 屈陽
 * @@version 1.0s
 */
public interface WEB3FeqAccOpenResultCodeDef
{
    /**
     * 0：口座開設完了
     */
    public static final String OPEN_COMPLETE = "0";
    
    /**
     * 1：口座開設エラー
     */
    public static final String OPEN_ERROR = "1";

    /**
     * 2：口座閉鎖
     */
    public static final String OPEN_CLOSE = "2";
}
@
