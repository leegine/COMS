head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.48.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioAccOpenRemarkDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3AioAccOpenRemarkDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/24 屈陽 (中訊) 新規作成
*/

package webbroker3.aio.define;

/**
 * FX口座開設申込明細 の備考　@定数定義インタフェイス
 *                                                                     
 * @@author 屈陽
 * @@version 1.0
 */

public interface WEB3AioAccOpenRemarkDef
{
    /**
     * 10000000 : 口座開設中 
     */
    public static final String ACCEPT_RESULT_CODE_10000000 = "10000000";
    
    /**
     * 90000000 : 削除
     */
    public static final String ACCEPT_RESULT_CODE_90000000 = "90000000";
    
    /**
     * 99999999 : システムエラー
     */
    public static final String ACCEPT_RESULT_CODE_99999999 = "99999999";
    
    /**
     * 00000000 : 口座開設完了
     */
    public static final String ACCEPT_RESULT_CODE_00000000 = "00000000";
    
    /**
     * 00000105 : GFT受付時間外エラー
     */
    public static final String ACCEPT_RESULT_CODE_00000105 = "00000105";
    
    /**
     * 00000199 : GFTシステム起因エラー
     */
    public static final String ACCEPT_RESULT_CODE_00000199 = "00000199";
    
    /**
     * 00000204 : 残高不足エラー
     */
    public static final String ACCEPT_RESULT_CODE_00000204 = "00000204";
    
    /**
     * 00000299 : ユーザー起因エラー
     */
    public static final String ACCEPT_RESULT_CODE_00000299 = "00000299";
    
    /**
     * 00000501 : 該当保証金口座無しエラー
     */
    public static final String ACCEPT_RESULT_CODE_00000501 = "00000501";
    
    /**
     * 00000502 : 入出金金額エラー
     */
    public static final String ACCEPT_RESULT_CODE_00000502 = "00000502";
    
    /**
     * 00000601 : 電文書式エラー（必須項目未入力）
     */
    public static final String ACCEPT_RESULT_CODE_00000601 = "00000601";
    
    /**
     * 00000602 : 電文書式エラー（不正文字使用）
     */
    public static final String ACCEPT_RESULT_CODE_00000602 = "00000602";
    
    /**
     * 00000603 : 電文書式エラー（桁数不備）
     */
    public static final String ACCEPT_RESULT_CODE_00000603 = "00000603";
    
    /**
     * 00000609 : 電文書式起因エラー
     */
    public static final String ACCEPT_RESULT_CODE_00000609 = "00000609";
   
    /**
     * 00000701 : 処理区分エラー
     */
    public static final String ACCEPT_RESULT_CODE_00000701 = "00000701";
    
    /**
     * 00000801 : 2重処理エラー　@　@
     */
    public static final String ACCEPT_RESULT_CODE_00000801 = "00000801";
    
    /**
     * 00000901 : GFTシステムエラー
     */
    public static final String ACCEPT_RESULT_CODE_00000901 = "00000901";
    
    /**
     * 00000909 : ハッシュ値エラー
     */
    public static final String ACCEPT_RESULT_CODE_00000909 = "00000909";
    
    /**
     * 00000910 : タイムアウトエラー
     */
    public static final String ACCEPT_RESULT_CODE_00000910 = "00000910";
    
	/**
	 * 90000009 : 口座抹消
	 */
	public static final String ACCEPT_RESULT_CODE_90000009 = "90000009";
}
@
