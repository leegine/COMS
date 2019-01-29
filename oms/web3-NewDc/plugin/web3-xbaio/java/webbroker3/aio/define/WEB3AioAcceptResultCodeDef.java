head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.46.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioAcceptResultCodeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3AioAcceptResultCodeDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/24 屈陽 (中訊) 新規作成
Revesion History : 2008/04/08 武波 (中訊) 仕様変更・モデルNo.839
*/

package webbroker3.aio.define;

/**
 * 結果通知電文の受付結果コード一覧　@定数定義インタフェイス
 *                                                                     
 * @@author 屈陽
 * @@version 1.0
 */

public interface WEB3AioAcceptResultCodeDef
{
    /**
     * 00000000 : 処理完了
     */
    public static final String ACCEPT_RESULT_CODE_00000000 = "00000000";
    
    /**
     * 00000105 : ホスト処理時間外
     */
    public static final String ACCEPT_RESULT_CODE_00000105 = "00000105";
    
    /**
     * 00000199 : 上記以外でホストシステムに起因するエラー
     */
    public static final String ACCEPT_RESULT_CODE_00000199 = "00000199";
    
    /**
     * 00000204 : ユーザーの証拠金口座残高不足によるエラー
     */
    public static final String ACCEPT_RESULT_CODE_00000204 = "00000204";
    
    /**
     * 00000205 : 為替口座の純資産残高不足
     */
    public static final String ACCEPT_RESULT_CODE_00000205 = "00000205";
    
    /**
     * 00000206 : の為替口座の現金残高不足
     */
    public static final String ACCEPT_RESULT_CODE_00000206 = "00000206";
    
    /**
     * 00000207 : 為替口座にマイナス通貨あり
     */
    public static final String ACCEPT_RESULT_CODE_00000207 = "00000207";
    
    /**
     * 00000299 : 上記以外でユーザーに起因するエラー
     */
    public static final String ACCEPT_RESULT_CODE_00000299 = "00000299";
    
    /**
     * 00000501 : 入出金時に該当する証拠金口座が存在しない
     */
    public static final String ACCEPT_RESULT_CODE_00000501 = "00000501";
    
    /**
     * 00000502 : 入出金額が金額制限内でない（下限未満、上限超、単位金額毎でない場合）
     */
    public static final String ACCEPT_RESULT_CODE_00000502 = "00000502";
    
    /**
     * 00000503 : 該当する為替保証金口座が存在しない
     */
    public static final String ACCEPT_RESULT_CODE_00000503 = "00000503";
    
    /**
     * 00000601 : 依頼電文の書式エラー（必須項目未入力）
     */
    public static final String ACCEPT_RESULT_CODE_00000601 = "00000601";
    
    /**
     * 00000602 : 依頼電文の書式エラー（不正な文字の使用）
     */
    public static final String ACCEPT_RESULT_CODE_00000602 = "00000602";
    
    /**
     * 00000603 : 依頼電文の書式エラー（桁数の不備）
     */
    public static final String ACCEPT_RESULT_CODE_00000603 = "00000603";
    
    /**
     * 00000609 : 上記以外で電文書式に起因するエラー
     */
    public static final String ACCEPT_RESULT_CODE_00000609 = "00000609";
   
    /**
     * 00000701 : 処理区分が不明
     */
    public static final String ACCEPT_RESULT_CODE_00000701 = "00000701";
    
    /**
     * 00000801 : 2重処理エラー（口座開設：ログインIDが既に登録済み　@　@
     * 全て：DIR側識別コード（linked_1）が重複する電文を受信した場合）
     */
    public static final String ACCEPT_RESULT_CODE_00000801 = "00000801";
    
    /**
     * 00000901 : 上記、及び下記以外のエラー
     */
    public static final String ACCEPT_RESULT_CODE_00000901 = "00000901";
    
    /**
     * 00000909 : ハッシュ値が不一致の場合
     */
    public static final String ACCEPT_RESULT_CODE_00000909 = "00000909";
    
    /**
     * 00000910 : タイムアウトエラー（送信時より30分以上経過してGFTが受信した場合）
     */
    public static final String ACCEPT_RESULT_CODE_00000910 = "00000910";

    /**
     * 00000990 : GFT接続エラー
     */
    public static final String ACCEPT_RESULT_CODE_00000990 = "00000990";
}
@
