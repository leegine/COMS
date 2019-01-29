head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.12.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashTransUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入出金明細クラス(WEB3AioCashTransUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/11 屈陽 (中訊) 新規作成
Revesion History : 2004/10/22 黄建 (中訊) レビュー
Revesion History : 2005/11/17 李俊 (中訊) フィデリティ対応                           
Revesion History : 2007/05/09 何文敏 (中訊) 仕様変更 No.723
Revesion History : 2008/09/22 武波 (中訊) 仕様変更・モデル1043
*/

package webbroker3.aio.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (入出金明細)<BR>
 * 入出金明細クラス
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public class WEB3AioCashTransUnit extends Message
{
    
    /**
     * (受付日付)<BR>
     * 各注文の受付日時 <BR>
     */
    public Date receptionDate;
    
    /**
     * (取引の種別)<BR>
     * 0： 出金<BR>
     * 1： 会費出金<BR>
     * 2： 情報料出金<BR>
     * 3： 入金<BR>
     * 4： 入金連絡<BR>
     * 5： 先物OP証拠金へ出金 <BR>
     * 6： 先物OP証拠金から入金 <BR>
     * 7： 信用保証金へ出金 <BR>
     * 8： 信用保証金から入金 <BR>
     * 9： FX保証金へ出金 <BR>
     * 10： FX保証金から入金 <BR>
     * 11： 中国株式口座へ出金 <BR>
     * 12： 中国株式口座から入金 <BR>
     * 13： その他出金 <BR>
     * 14： その他入金<BR>
     * 15： 担保ローン返済<BR>
     * 16： CFD口座へ出金<BR>
     * 17： CFD口座から入金<BR>
     * <BR>
     */
    public String tradingType;
    
    /**
     * (金融機@関名)<BR>
     * （オンライン入金のみ）<BR>
     * 決済機@関の名称
     */
    public String paySchemeName;
    
    /**
     * (入出金の金額)
     */
    public String cashTransAmt;
    
    /**
     * (受渡予定日)<BR>
     * 証券口座への入出金日 <BR>
     */
    public Date deliveryScheduledDate;
    
    /**
     * （オンライン入金のみ）<BR>
     * .comデビット決済取引番号 <BR>
     */
    public String comDebitNumber;
    
    /**
     * （備考）<BR>
     * 以下のような内容 <BR>
     * <BR>
     * １）為替保証金振替（取引が"9"もしくは"10"）の場合 <BR>
     * <BR>
     * 対象の口座番号区分 <BR>
     * <BR>
     * 1：1万通貨コース <BR>
     * 2：10万通貨コース <BR>
     * <BR>
     * ２）情報料出金・会費出金（取引が"1"もしくは"2"）の場合 <BR>
     * <BR>
     * 対象商品名 <BR>
     * <BR>
     * 01： 日経テレコン21-624 <BR>
     * 02： 時事通信ニュース・リテラチャート・リアル株価情報 <BR>
     * 06： QUICK IS-Web ＜オリックス証券版＞ <BR>
     * 07： QUICK リサーチネット <BR>
     * 09： 会社四季報 <BR>
     * 10： Market Viewer どこでもトレーダー <BR>
     * 12： 重要事実情報 <BR>
     * 13： 簡単テクニカル分析 <BR>
     * 14： Market ViewerどこでもトレーダーPro. <BR>
     * 33： ハイパーボックス オークション <BR>
     * 34： ハイパートレードPro <BR>
     * 35： リアルタイムトレーダー <BR>
     * 36： イワイトレーダー <BR>
     * 38： リアルタイム株価（QUICK　@Is-Web） <BR>
     * 40： ハイパートレーダー「スモーキーウルフの部屋」 <BR>
     * <BR>
     * ３）その他の出金・その他の入金（取引が"13"もしくは"14"）の場合 <BR>
     * <BR>
     * 02：（発）委託保証金 <BR>
     * 03：端株整理 <BR>
     * 06：名義書換料 <BR>
     * 07：（保）口座管理料 <BR>
     * 08：（外国）口座管理料 <BR>
     * 09：（金）口座管理料 <BR>
     * 10：（債券先物）委託保証金 <BR>
     * 11：（株式先物）委託保証金 <BR>
     * 14：（オプション）委託保証金 <BR>
     * 19：（株券オプション）委託保証金 <BR>
     * 24：銀行振込手数料 <BR>
     * 42：スーパーG（継続）月曜 <BR>
     * 43：スーパーG（継続）火曜 <BR>
     * 44：スーパーG（継続）水曜 <BR>
     * 45：スーパーG（継続）木曜 <BR>
     * 46：スーパーG（継続）金曜 <BR>
     * 47：金貯蓄 1ヶ月 <BR>
     * 48：金貯蓄 3ヶ月 <BR>
     * 49：金貯蓄 6ヶ月 <BR>
     * 50：金貯蓄 1年 <BR>
     * 52：中期国債ファ@ンド <BR>
     * 53：（累投）口座管理料 <BR>
     * 54：中期国債ファ@ンドキャッシング <BR>
     * 55：MMFキャッシング <BR>
     * 71：（先物オプション 東証） <BR>
     * 73：（先物オプション 名証） <BR>
     * 74：（先物オプション 利益払出） <BR>
     * 93：その他預り金 <BR>
     * 99：その他 <BR>
     * <BR>
     * ４）出金　@"0" の場合<BR>
     * <BR>
     * mf：投信解約<BR> 
     * <BR>
     * ５）CFD振替（取引が"16"もしくは"17"）の場合<BR>
     * <BR>
     * 　@　@対象の口座番号区分<BR>
     * <BR>
     * 　@3：CFDコース<BR>
     */
    public String ioRemark;
    
    
    /**
     * (処理状況)<BR>
     * （オンライン入金のみ）<BR>
     * オンライン決済の処理状況<BR>
     * <BR>
     * A： 決済中<BR>
     * B： データ通信エラー（金融機@関決済開始後）<BR>
     * C： データ通信エラー（金融機@関決済開始前）<BR>
     * D： 決済受付<BR>
     * E： 決済完了<BR>
     * F： 決済エラー<BR>
     * G： 金融機@関決済中止<BR>
     * H： 金融機@関決済結果エラー<BR>
     * I： 取消済み<BR>
     * J： 決済失敗（システムエラー）<BR>
     * K： 決済受付（ただし買付余力には加算されず）<BR>
     * L： 決済完了（ただし買付余力には加算されず）<BR>
     * M： 決済エラー<BR>
     * N： セッションアウトエラー（金融機@関決済完了）<BR>
     * O： セッションアウトエラー（金融機@関決済失敗）<BR>
     * P： 決済中<BR>
     * V： 取消中<BR>
     * W： 取消不可<BR>
     * X： 取消失敗<BR>
     * ” ”： NULL<BR>
     * <BR>
     */
    public String payStatus;

    /**
     * （通貨コード）<BR>
     * 通貨コード <BR>
     */
    public String currencyCode;

    /**
     * (コンストラクタ)
     * @@return webbroker3.aio.message.WEB3AioCashTransUnit
     * @@roseuid 40E27A560140
     */
    public WEB3AioCashTransUnit() 
    {
     
    }
}
@
