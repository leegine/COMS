head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.50.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioJudgeResultDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3AioJudgeResultDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/08 王蘭芬 (中訊) 新規作成
*/
package webbroker3.aio.define;

/**
 * 入出金ステータス構成表の判定結果　@定数定義インタフェイス
 *                                                                     
 * @@author 王蘭芬
 * @@version 1.0
 */
public interface WEB3AioJudgeResultDef {
    
    /**
     * A  決済中
     */
    public static final String A = "A";
    
    /**
     * B  データ通信エラー（金融機@関決済開始後）
     */
    public static final String B = "B";
    
    /**
     * C  データ通信エラー（金融機@関決済開始前）
     */
    public static final String C = "C";
    
    /**
     * D  決済受付
     */
    public static final String D = "D";
    
    /**
     * E  決済完了
     */
    public static final String E = "E";
    
    /**
     * F  決済エラー
     */
    public static final String F = "F";
    
    /**
     * G  金融機@関決済中止
     */
    public static final String G = "G";
    
    /**
     * H  金融機@関決済結果エラー
     */
    public static final String H = "H";
    
    /**
     * I  取消済み
     */
    public static final String I = "I";
    
    /**
     * J  決済失敗（システムエラー）
     */
    public static final String J = "J";
    
    /**
     * K  決済受付（ただし買付余力には加算されず）
     */
    public static final String K = "K";
    
    /**
     * L  決済完了（ただし買付余力には加算されず）
     */
    public static final String L = "L";
    
    /**
     * M  決済エラー
     */
    public static final String M = "M";
    
    /**
     * N  セッションアウトエラー（金融機@関決済完了）
     */
    public static final String N = "N";
    
    /**
     * O  セッションアウトエラー（金融機@関決済失敗）
     */
    public static final String O = "O";
    
    /**
     * P  決済中
     */
    public static final String P = "P";
    
    /**
     * V  取消中
     */
    public static final String V = "V";
    
    /**
     * W  取消不可
     */
    public static final String W = "W";
    
    /**
     * X  取消失敗
     */
    public static final String X = "X";
    
    /**
     * Q  GFT振替中
     */
    public static final String Q = "Q";
    
    /**
     * S  GFT受付時間外エラー
     */
    public static final String S = "S";
    
    /**
     * T  GFTシステムエラー
     */
    public static final String T = "T";
    
    /**
     * R  残高不足エラー
     */
    public static final String R = "R";

    /**
     * U  GFT受付済エラー
     */
    public static final String U = "U";

}    
@
