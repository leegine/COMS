head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.21.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioMutilBankStatusUtility.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 金融機@関決済連携の処理状況を取得の処理クラス(WEB3AioMutilBankStatusUtility)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/01 黄建 (中訊)  新規作成                 
Revesion History : 2007/05/08 何文敏 (中訊)  仕様変更 No.724
*/

package webbroker3.aio;

import java.util.Hashtable;
import java.util.Map;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;

import webbroker3.aio.define.WEB3AioJudgeResultDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3OrderStatusFlagDef;
import webbroker3.common.define.WEB3ResultStatusFlagDef;
import webbroker3.common.define.WEB3StartStatusFlgDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.define.WEB3TransactionStatusDef;

/**
 * 金融機@関決済連携の処理状況を取得の処理クラス。<BR>
 * 
 * @@author 黄建 (中訊)
 * @@version 1.0
 */

public class WEB3AioMutilBankStatusUtility extends WEB3AioAbstractStatusUtility
{

    /**
     * 詳しく処理ステータスカラム(Map)
     */
    private static Map cashOutStatusMap = new Hashtable();

    /**
     * 詳しく処理ステータスカラム(String[][])
     */
    private static String[][] itemKey = null;

    static
    {
        String[][] itemKeyTemp = 
        {
            //金融機@関連携入出金状況テーブル [処理区分]           ０：未処理
            //金融機@関連携入出金状況テーブル [処理FLAG(注文)]     ０：未処理
            //金融機@関連携入出金状況テーブル [処理FLAG(決済開始)] ０：未処理
            //金融機@関連携入出金状況テーブル [処理FLAG(決済結果)] ０：未処理
            //注文単位テーブル              [注文状態]
            //注文単位テーブル              [注文取消区分]
            //入出金伝票受付キューテーブル   [処理区分]
            //0)                           [判定結果]  － ---> A      
            {
                WEB3TransactionStatusDef.NOT_DEAL,
                WEB3OrderStatusFlagDef.NOT_DEAL,
                WEB3StartStatusFlgDef.NOT_DEAL,
                WEB3ResultStatusFlagDef.NOT_DEAL,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
                DefaultStatus.ANY,                
            }, 
            {WEB3AioJudgeResultDef.A},
            
            //金融機@関連携入出金状況テーブル [処理区分]           ０：未処理
            //金融機@関連携入出金状況テーブル [処理FLAG(注文)]     １：要求受信
            //金融機@関連携入出金状況テーブル [処理FLAG(決済開始)] ０：未処理
            //金融機@関連携入出金状況テーブル [処理FLAG(決済結果)] ０：未処理
            //注文単位テーブル              [注文状態]
            //注文単位テーブル              [注文取消区分]
            //入出金伝票受付キューテーブル   [処理区分]
            //2)                           [判定結果]  － ---> A   
            {
                WEB3TransactionStatusDef.NOT_DEAL,
                WEB3OrderStatusFlagDef.REPUEST_RECEIPT,
                WEB3StartStatusFlgDef.NOT_DEAL,
                WEB3ResultStatusFlagDef.NOT_DEAL,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
                DefaultStatus.ANY,      
            }, 
            {WEB3AioJudgeResultDef.A},
            
            //金融機@関連携入出金状況テーブル [処理区分]           ０：未処理
            //金融機@関連携入出金状況テーブル [処理FLAG(注文)]     ２：応答送信
            //金融機@関連携入出金状況テーブル [処理FLAG(決済開始)] ０：未処理
            //金融機@関連携入出金状況テーブル [処理FLAG(決済結果)] ０：未処理
            //注文単位テーブル              [注文状態]
            //注文単位テーブル              [注文取消区分]
            //入出金伝票受付キューテーブル   [処理区分]
            //4)                           [判定結果]  － ---> A   
            {
                WEB3TransactionStatusDef.NOT_DEAL,
                WEB3OrderStatusFlagDef.RESPONSE_SEND,
                WEB3StartStatusFlgDef.NOT_DEAL,
                WEB3ResultStatusFlagDef.NOT_DEAL,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
                DefaultStatus.ANY,      
            }, 
            {WEB3AioJudgeResultDef.A},
            
            //金融機@関連携入出金状況テーブル [処理区分]           ０：未処理
            //金融機@関連携入出金状況テーブル [処理FLAG(注文)]     ２：応答送信
            //金融機@関連携入出金状況テーブル [処理FLAG(決済開始)] １：要求受信
            //金融機@関連携入出金状況テーブル [処理FLAG(決済結果)] ０：未処理
            //注文単位テーブル              [注文状態]
            //注文単位テーブル              [注文取消区分]
            //入出金伝票受付キューテーブル   [処理区分]
            //6)                           [判定結果]  － ---> A   
            {
                WEB3TransactionStatusDef.NOT_DEAL,
                WEB3OrderStatusFlagDef.RESPONSE_SEND,
                WEB3StartStatusFlgDef.REPUEST_RECEIPT,
                WEB3ResultStatusFlagDef.NOT_DEAL,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
                DefaultStatus.ANY,      
            }, 
            {WEB3AioJudgeResultDef.A},
            
            //金融機@関連携入出金状況テーブル [処理区分]           ０：未処理
            //金融機@関連携入出金状況テーブル [処理FLAG(注文)]     ２：応答送信
            //金融機@関連携入出金状況テーブル [処理FLAG(決済開始)] ２：応答送信
            //金融機@関連携入出金状況テーブル [処理FLAG(決済結果)] ０：未処理
            //注文単位テーブル              [注文状態]
            //注文単位テーブル              [注文取消区分]
            //入出金伝票受付キューテーブル   [処理区分]
            //8)                           [判定結果]  － ---> P   
            {
                WEB3TransactionStatusDef.NOT_DEAL,
                WEB3OrderStatusFlagDef.RESPONSE_SEND,
                WEB3StartStatusFlgDef.RESPONSE_SEND,
                WEB3ResultStatusFlagDef.NOT_DEAL,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
                DefaultStatus.ANY,      
            }, 
            {WEB3AioJudgeResultDef.P},
            
            //金融機@関連携入出金状況テーブル [処理区分]           ０：未処理
            //金融機@関連携入出金状況テーブル [処理FLAG(注文)]     ２：応答送信
            //金融機@関連携入出金状況テーブル [処理FLAG(決済開始)] ２：応答送信
            //金融機@関連携入出金状況テーブル [処理FLAG(決済結果)] その他
            //注文単位テーブル              [注文状態]
            //注文単位テーブル              [注文取消区分]
            //入出金伝票受付キューテーブル   [処理区分]
            //10)                           [判定結果]  － ---> B   
            {
                WEB3TransactionStatusDef.NOT_DEAL,
                WEB3OrderStatusFlagDef.RESPONSE_SEND,
                WEB3StartStatusFlgDef.RESPONSE_SEND,
                DefaultStatus.OTHER,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
                DefaultStatus.ANY,      
            }, 
            {WEB3AioJudgeResultDef.B},
            
            //金融機@関連携入出金状況テーブル [処理区分]           ０：未処理
            //金融機@関連携入出金状況テーブル [処理FLAG(注文)]     その他
            //金融機@関連携入出金状況テーブル [処理FLAG(決済開始)] その他
            //金融機@関連携入出金状況テーブル [処理FLAG(決済結果)] その他
            //注文単位テーブル              [注文状態]
            //注文単位テーブル              [注文取消区分]
            //入出金伝票受付キューテーブル   [処理区分]
            //12)                          [判定結果]  － ---> C   
            {
                WEB3TransactionStatusDef.NOT_DEAL,
                DefaultStatus.OTHER,
                DefaultStatus.OTHER,
                DefaultStatus.OTHER,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
                DefaultStatus.ANY,      
            }, 
            {WEB3AioJudgeResultDef.C},
            
            //金融機@関連携入出金状況テーブル [処理区分]           １：決済完了
            //金融機@関連携入出金状況テーブル [処理FLAG(注文)]     ２：応答送信
            //金融機@関連携入出金状況テーブル [処理FLAG(決済開始)] ２：応答送信
            //金融機@関連携入出金状況テーブル [処理FLAG(決済結果)] １：通知受信
            //注文単位テーブル              [注文状態]           １：受付済（新規注文） 
            //注文単位テーブル              [注文取消区分]       ０：初期値
            //入出金伝票受付キューテーブル   [処理区分]           その他 
            //14)                          [判定結果]  － ---> D   
            {
                WEB3TransactionStatusDef.OK,
                WEB3OrderStatusFlagDef.RESPONSE_SEND,
                WEB3StartStatusFlgDef.RESPONSE_SEND,
                WEB3ResultStatusFlagDef.NOTIFY_RECEIPT,
                OrderStatusEnum.ACCEPTED.intValue() + "",
                WEB3ModifyCancelTypeDef.INITIAL_VALUE,
                DefaultStatus.OTHER,      
            }, 
            {WEB3AioJudgeResultDef.D},
            
            //金融機@関連携入出金状況テーブル [処理区分]           １：決済完了
            //金融機@関連携入出金状況テーブル [処理FLAG(注文)]     ２：応答送信
            //金融機@関連携入出金状況テーブル [処理FLAG(決済開始)] ２：応答送信
            //金融機@関連携入出金状況テーブル [処理FLAG(決済結果)] １：通知受信
            //注文単位テーブル              [注文状態]           ３：発注済（新規注文）
            //注文単位テーブル              [注文取消区分]       ０：初期値
            //入出金伝票受付キューテーブル   [処理区分]
            //16)                          [判定結果]  － ---> E   
            {
                WEB3TransactionStatusDef.OK,
                WEB3OrderStatusFlagDef.RESPONSE_SEND,
                WEB3StartStatusFlgDef.RESPONSE_SEND,
                WEB3ResultStatusFlagDef.NOTIFY_RECEIPT,
                OrderStatusEnum.ORDERED.intValue() + "",
                WEB3ModifyCancelTypeDef.INITIAL_VALUE,
                DefaultStatus.ANY,      
            }, 
            {WEB3AioJudgeResultDef.E},
            
            //金融機@関連携入出金状況テーブル [処理区分]           １：決済完了
            //金融機@関連携入出金状況テーブル [処理FLAG(注文)]     ２：応答送信
            //金融機@関連携入出金状況テーブル [処理FLAG(決済開始)] ２：応答送信
            //金融機@関連携入出金状況テーブル [処理FLAG(決済結果)] １：通知受信
            //注文単位テーブル              [注文状態]           ６：発注失敗（新規注文）
            //注文単位テーブル              [注文取消区分]       ０：初期値
            //入出金伝票受付キューテーブル   [処理区分]
            //18)                          [判定結果]  － ---> F   
            {
                WEB3TransactionStatusDef.OK,
                WEB3OrderStatusFlagDef.RESPONSE_SEND,
                WEB3StartStatusFlgDef.RESPONSE_SEND,
                WEB3ResultStatusFlagDef.NOTIFY_RECEIPT,
                OrderStatusEnum.NOT_ORDERED.intValue() + "",
                WEB3ModifyCancelTypeDef.INITIAL_VALUE,
                DefaultStatus.ANY,      
            }, 
            {WEB3AioJudgeResultDef.F},
            
            //金融機@関連携入出金状況テーブル [処理区分]           １：決済完了
            //金融機@関連携入出金状況テーブル [処理FLAG(注文)]     ２：応答送信
            //金融機@関連携入出金状況テーブル [処理FLAG(決済開始)] ２：応答送信
            //金融機@関連携入出金状況テーブル [処理FLAG(決済結果)] １：通知受信
            //注文単位テーブル              [注文状態]           １：受付済（新規注文）
            //注文単位テーブル              [注文取消区分]       ０：初期値
            //入出金伝票受付キューテーブル   [処理区分]          ９：エラー
            //20)                          [判定結果]  － ---> J   
            {
                WEB3TransactionStatusDef.OK,
                WEB3OrderStatusFlagDef.RESPONSE_SEND,
                WEB3StartStatusFlgDef.RESPONSE_SEND,
                WEB3ResultStatusFlagDef.NOTIFY_RECEIPT,
                OrderStatusEnum.ACCEPTED.intValue() + "",
                WEB3ModifyCancelTypeDef.INITIAL_VALUE,
                WEB3StatusDef.DATA_ERROR,      
            }, 
            {WEB3AioJudgeResultDef.J},
            
            //金融機@関連携入出金状況テーブル [処理区分]           １：決済完了
            //金融機@関連携入出金状況テーブル [処理FLAG(注文)]     ２：応答送信
            //金融機@関連携入出金状況テーブル [処理FLAG(決済開始)] ２：応答送信
            //金融機@関連携入出金状況テーブル [処理FLAG(決済結果)] １：通知受信
            //注文単位テーブル              [注文状態]           NO RECORD
            //注文単位テーブル              [注文取消区分]       NO RECORD
            //入出金伝票受付キューテーブル   [処理区分]           NO RECORD
            //22)                          [判定結果]  － ---> E   
            {
                WEB3TransactionStatusDef.OK,
                WEB3OrderStatusFlagDef.RESPONSE_SEND,
                WEB3StartStatusFlgDef.RESPONSE_SEND,
                WEB3ResultStatusFlagDef.NOTIFY_RECEIPT,
                null,
                null,
                null,      
            }, 
            {WEB3AioJudgeResultDef.E},
            
            //金融機@関連携入出金状況テーブル [処理区分]           １：決済完了
            //金融機@関連携入出金状況テーブル [処理FLAG(注文)]     ２：応答送信
            //金融機@関連携入出金状況テーブル [処理FLAG(決済開始)] ２：応答送信
            //金融機@関連携入出金状況テーブル [処理FLAG(決済結果)] ２：応答送信
            //注文単位テーブル              [注文状態]          １：受付済（新規注文）
            //注文単位テーブル              [注文取消区分]       ０：初期値
            //入出金伝票受付キューテーブル   [処理区分]           その他
            //24)                          [判定結果]  － ---> D   
            {
                WEB3TransactionStatusDef.OK,
                WEB3OrderStatusFlagDef.RESPONSE_SEND,
                WEB3StartStatusFlgDef.RESPONSE_SEND,
                WEB3ResultStatusFlagDef.RESPONSE_SEND,
                OrderStatusEnum.ACCEPTED.intValue() + "",
                WEB3ModifyCancelTypeDef.INITIAL_VALUE,
                DefaultStatus.OTHER,      
            }, 
            {WEB3AioJudgeResultDef.D},
            
            //金融機@関連携入出金状況テーブル [処理区分]           １：決済完了
            //金融機@関連携入出金状況テーブル [処理FLAG(注文)]     ２：応答送信
            //金融機@関連携入出金状況テーブル [処理FLAG(決済開始)] ２：応答送信
            //金融機@関連携入出金状況テーブル [処理FLAG(決済結果)] ２：応答送信
            //注文単位テーブル              [注文状態]          ３：発注済（新規注文）
            //注文単位テーブル              [注文取消区分]       ０：初期値
            //入出金伝票受付キューテーブル   [処理区分]           
            //26)                          [判定結果]  － ---> E 
            {
                WEB3TransactionStatusDef.OK,
                WEB3OrderStatusFlagDef.RESPONSE_SEND,
                WEB3StartStatusFlgDef.RESPONSE_SEND,
                WEB3ResultStatusFlagDef.RESPONSE_SEND,
                OrderStatusEnum.ORDERED.intValue() + "",
                WEB3ModifyCancelTypeDef.INITIAL_VALUE,
                DefaultStatus.ANY,      
            }, 
            {WEB3AioJudgeResultDef.E},
            
            //金融機@関連携入出金状況テーブル [処理区分]           １：決済完了
            //金融機@関連携入出金状況テーブル [処理FLAG(注文)]     ２：応答送信
            //金融機@関連携入出金状況テーブル [処理FLAG(決済開始)] ２：応答送信
            //金融機@関連携入出金状況テーブル [処理FLAG(決済結果)] ２：応答送信
            //注文単位テーブル              [注文状態]          ６：発注失敗（新規注文）
            //注文単位テーブル              [注文取消区分]       ０：初期値
            //入出金伝票受付キューテーブル   [処理区分]           
            //28)                          [判定結果]  － ---> F 
            {
                WEB3TransactionStatusDef.OK,
                WEB3OrderStatusFlagDef.RESPONSE_SEND,
                WEB3StartStatusFlgDef.RESPONSE_SEND,
                WEB3ResultStatusFlagDef.RESPONSE_SEND,
                OrderStatusEnum.NOT_ORDERED.intValue() + "",
                WEB3ModifyCancelTypeDef.INITIAL_VALUE,
                DefaultStatus.ANY,      
            }, 
            {WEB3AioJudgeResultDef.F},
            
            //金融機@関連携入出金状況テーブル [処理区分]           １：決済完了
            //金融機@関連携入出金状況テーブル [処理FLAG(注文)]     ２：応答送信
            //金融機@関連携入出金状況テーブル [処理FLAG(決済開始)] ２：応答送信
            //金融機@関連携入出金状況テーブル [処理FLAG(決済結果)] ２：応答送信
            //注文単位テーブル              [注文状態]          １：受付済（新規注文）
            //注文単位テーブル              [注文取消区分]       ０：初期値
            //入出金伝票受付キューテーブル   [処理区分]           ９：エラー
            //30)                          [判定結果]  － ---> J 
            {
                WEB3TransactionStatusDef.OK,
                WEB3OrderStatusFlagDef.RESPONSE_SEND,
                WEB3StartStatusFlgDef.RESPONSE_SEND,
                WEB3ResultStatusFlagDef.RESPONSE_SEND,
                OrderStatusEnum.ACCEPTED.intValue() + "",
                WEB3ModifyCancelTypeDef.INITIAL_VALUE,
                WEB3StatusDef.DATA_ERROR,      
            }, 
            {WEB3AioJudgeResultDef.J},
            
            //金融機@関連携入出金状況テーブル [処理区分]           １：決済完了
            //金融機@関連携入出金状況テーブル [処理FLAG(注文)]     ２：応答送信
            //金融機@関連携入出金状況テーブル [処理FLAG(決済開始)] ２：応答送信
            //金融機@関連携入出金状況テーブル [処理FLAG(決済結果)] ２：応答送信
            //注文単位テーブル              [注文状態]          １４：発注済（取消注文）
            //注文単位テーブル              [注文取消区分]        ３：全部取消完了
            //入出金伝票受付キューテーブル   [処理区分]
            //                          [判定結果]  － ---> I
            {
                WEB3TransactionStatusDef.OK,
                WEB3OrderStatusFlagDef.RESPONSE_SEND,
                WEB3StartStatusFlgDef.RESPONSE_SEND,
                WEB3ResultStatusFlagDef.RESPONSE_SEND,
                OrderStatusEnum.CANCELLED.intValue() + "",
                WEB3ModifyCancelTypeDef.CANCELED,
                DefaultStatus.ANY,
            },
            {WEB3AioJudgeResultDef.I},
            
            //金融機@関連携入出金状況テーブル [処理区分]           １：決済完了
            //金融機@関連携入出金状況テーブル [処理FLAG(注文)]     ２：応答送信
            //金融機@関連携入出金状況テーブル [処理FLAG(決済開始)] ２：応答送信
            //金融機@関連携入出金状況テーブル [処理FLAG(決済結果)] ２：応答送信
            //注文単位テーブル              [注文状態]          NO RECORD
            //注文単位テーブル              [注文取消区分]       NO RECORD
            //入出金伝票受付キューテーブル   [処理区分]           NO RECORD
            //32)                          [判定結果]  － ---> E 
            {
                WEB3TransactionStatusDef.OK,
                WEB3OrderStatusFlagDef.RESPONSE_SEND,
                WEB3StartStatusFlgDef.RESPONSE_SEND,
                WEB3ResultStatusFlagDef.RESPONSE_SEND,
                null,
                null,
                null,      
            }, 
            {WEB3AioJudgeResultDef.E},
       
            //金融機@関連携入出金状況テーブル [処理区分]           １：決済完了
            //金融機@関連携入出金状況テーブル [処理FLAG(注文)]     ２：応答送信
            //金融機@関連携入出金状況テーブル [処理FLAG(決済開始)] ２：応答送信
            //金融機@関連携入出金状況テーブル [処理FLAG(決済結果)] ８：余力計算失敗
            //注文単位テーブル              [注文状態]          １：受付済（新規注文）
            //注文単位テーブル              [注文取消区分]       ０：初期値
            //入出金伝票受付キューテーブル   [処理区分]           その他
            //34)                          [判定結果]  － ---> K   
            {
                WEB3TransactionStatusDef.OK,
                WEB3OrderStatusFlagDef.RESPONSE_SEND,
                WEB3StartStatusFlgDef.RESPONSE_SEND,
                WEB3ResultStatusFlagDef.REMAINING_CALCULATION_FAIL,
                OrderStatusEnum.ACCEPTED.intValue() + "",
                WEB3ModifyCancelTypeDef.INITIAL_VALUE,
                DefaultStatus.OTHER,
            },
            {WEB3AioJudgeResultDef.K},            
            
            //金融機@関連携入出金状況テーブル [処理区分]           １：決済完了
            //金融機@関連携入出金状況テーブル [処理FLAG(注文)]     ２：応答送信
            //金融機@関連携入出金状況テーブル [処理FLAG(決済開始)] ２：応答送信
            //金融機@関連携入出金状況テーブル [処理FLAG(決済結果)] ８：余力計算失敗
            //注文単位テーブル              [注文状態]          ３：発注済（新規注文）
            //注文単位テーブル              [注文取消区分]       ０：初期値
            //入出金伝票受付キューテーブル   [処理区分]           
            //36)                          [判定結果]  － ---> L 
            {
                WEB3TransactionStatusDef.OK,
                WEB3OrderStatusFlagDef.RESPONSE_SEND,
                WEB3StartStatusFlgDef.RESPONSE_SEND,
                WEB3ResultStatusFlagDef.REMAINING_CALCULATION_FAIL,
                OrderStatusEnum.ORDERED.intValue() + "",
                WEB3ModifyCancelTypeDef.INITIAL_VALUE,
                DefaultStatus.ANY,
            },
            {WEB3AioJudgeResultDef.L},            
            
            //金融機@関連携入出金状況テーブル [処理区分]           １：決済完了
            //金融機@関連携入出金状況テーブル [処理FLAG(注文)]     ２：応答送信
            //金融機@関連携入出金状況テーブル [処理FLAG(決済開始)] ２：応答送信
            //金融機@関連携入出金状況テーブル [処理FLAG(決済結果)] ８：余力計算失敗
            //注文単位テーブル              [注文状態]          ６：発注失敗（新規注文）
            //注文単位テーブル              [注文取消区分]       ０：初期値
            //入出金伝票受付キューテーブル   [処理区分]           
            //38)                          [判定結果]  － ---> M 
            {
                WEB3TransactionStatusDef.OK,
                WEB3OrderStatusFlagDef.RESPONSE_SEND,
                WEB3StartStatusFlgDef.RESPONSE_SEND,
                WEB3ResultStatusFlagDef.REMAINING_CALCULATION_FAIL,
                OrderStatusEnum.NOT_ORDERED.intValue() + "",
                WEB3ModifyCancelTypeDef.INITIAL_VALUE,
                DefaultStatus.ANY,
            },
            {WEB3AioJudgeResultDef.M},            
            
            //金融機@関連携入出金状況テーブル [処理区分]           １：決済完了
            //金融機@関連携入出金状況テーブル [処理FLAG(注文)]     ２：応答送信
            //金融機@関連携入出金状況テーブル [処理FLAG(決済開始)] ２：応答送信
            //金融機@関連携入出金状況テーブル [処理FLAG(決済結果)] ８：余力計算失敗
            //注文単位テーブル              [注文状態]          １：受付済（新規注文）
            //注文単位テーブル              [注文取消区分]       ０：初期値
            //入出金伝票受付キューテーブル   [処理区分]           ９：エラー
            //40)                          [判定結果]  － ---> J 
            {
                WEB3TransactionStatusDef.OK,
                WEB3OrderStatusFlagDef.RESPONSE_SEND,
                WEB3StartStatusFlgDef.RESPONSE_SEND,
                WEB3ResultStatusFlagDef.REMAINING_CALCULATION_FAIL,
                OrderStatusEnum.ACCEPTED.intValue() + "",
                WEB3ModifyCancelTypeDef.INITIAL_VALUE,
                WEB3StatusDef.DATA_ERROR,
            },
            {WEB3AioJudgeResultDef.J},            
            
            //金融機@関連携入出金状況テーブル [処理区分]           １：決済完了
            //金融機@関連携入出金状況テーブル [処理FLAG(注文)]     ２：応答送信
            //金融機@関連携入出金状況テーブル [処理FLAG(決済開始)] ２：応答送信
            //金融機@関連携入出金状況テーブル [処理FLAG(決済結果)] ８：余力計算失敗
            //注文単位テーブル              [注文状態]          NO RECORD
            //注文単位テーブル              [注文取消区分]       NO RECORD
            //入出金伝票受付キューテーブル   [処理区分]           NO RECORD
            //42)                          [判定結果]  － ---> L 
            {
                WEB3TransactionStatusDef.OK,
                WEB3OrderStatusFlagDef.RESPONSE_SEND,
                WEB3StartStatusFlgDef.RESPONSE_SEND,
                WEB3ResultStatusFlagDef.REMAINING_CALCULATION_FAIL,
                null,
                null,
                null,
            },
            {WEB3AioJudgeResultDef.L},                
                
            //金融機@関連携入出金状況テーブル [処理区分]           １：決済完了
            //金融機@関連携入出金状況テーブル [処理FLAG(注文)]     ２：応答送信
            //金融機@関連携入出金状況テーブル [処理FLAG(決済開始)] ２：応答送信
            //金融機@関連携入出金状況テーブル [処理FLAG(決済結果)] ９：余力再計算完了
            //注文単位テーブル              [注文状態]          １：受付済（新規注文）
            //注文単位テーブル              [注文取消区分]       ０：初期値
            //入出金伝票受付キューテーブル   [処理区分]           その他
            //44)                          [判定結果]  － ---> D   
            {
                WEB3TransactionStatusDef.OK,
                WEB3OrderStatusFlagDef.RESPONSE_SEND,
                WEB3StartStatusFlgDef.RESPONSE_SEND,
                WEB3ResultStatusFlagDef.REMAINING_CALCULATION_COMPLETE,
                OrderStatusEnum.ACCEPTED.intValue() + "",
                WEB3ModifyCancelTypeDef.INITIAL_VALUE,
                DefaultStatus.OTHER,
            },
                    {WEB3AioJudgeResultDef.D},            
            
            //金融機@関連携入出金状況テーブル [処理区分]           １：決済完了
            //金融機@関連携入出金状況テーブル [処理FLAG(注文)]     ２：応答送信
            //金融機@関連携入出金状況テーブル [処理FLAG(決済開始)] ２：応答送信
            //金融機@関連携入出金状況テーブル [処理FLAG(決済結果)] ９：余力再計算完了
            //注文単位テーブル              [注文状態]          ３：発注済（新規注文）
            //注文単位テーブル              [注文取消区分]       ０：初期値
            //入出金伝票受付キューテーブル   [処理区分]           
            //46)                          [判定結果]  － ---> E 
            {
                WEB3TransactionStatusDef.OK,
                WEB3OrderStatusFlagDef.RESPONSE_SEND,
                WEB3StartStatusFlgDef.RESPONSE_SEND,
                WEB3ResultStatusFlagDef.REMAINING_CALCULATION_COMPLETE,
                OrderStatusEnum.ORDERED.intValue() + "",
                WEB3ModifyCancelTypeDef.INITIAL_VALUE,
                DefaultStatus.ANY,
            },
            {WEB3AioJudgeResultDef.E},            
            
            //金融機@関連携入出金状況テーブル [処理区分]           １：決済完了
            //金融機@関連携入出金状況テーブル [処理FLAG(注文)]     ２：応答送信
            //金融機@関連携入出金状況テーブル [処理FLAG(決済開始)] ２：応答送信
            //金融機@関連携入出金状況テーブル [処理FLAG(決済結果)] ９：余力再計算完了
            //注文単位テーブル              [注文状態]          ６：発注失敗（新規注文）
            //注文単位テーブル              [注文取消区分]       ０：初期値
            //入出金伝票受付キューテーブル   [処理区分]           
            //48)                          [判定結果]  － ---> F 
            {
                WEB3TransactionStatusDef.OK,
                WEB3OrderStatusFlagDef.RESPONSE_SEND,
                WEB3StartStatusFlgDef.RESPONSE_SEND,
                WEB3ResultStatusFlagDef.REMAINING_CALCULATION_COMPLETE,
                OrderStatusEnum.NOT_ORDERED.intValue() + "",
                WEB3ModifyCancelTypeDef.INITIAL_VALUE,
                DefaultStatus.ANY,
            },
            {WEB3AioJudgeResultDef.F},            
            
            //金融機@関連携入出金状況テーブル [処理区分]           １：決済完了
            //金融機@関連携入出金状況テーブル [処理FLAG(注文)]     ２：応答送信
            //金融機@関連携入出金状況テーブル [処理FLAG(決済開始)] ２：応答送信
            //金融機@関連携入出金状況テーブル [処理FLAG(決済結果)] ９：余力再計算完了
            //注文単位テーブル              [注文状態]          １：受付済（新規注文）
            //注文単位テーブル              [注文取消区分]       ０：初期値
            //入出金伝票受付キューテーブル   [処理区分]           ９：エラー
            //50)                          [判定結果]  － ---> J 
            {
                WEB3TransactionStatusDef.OK,
                WEB3OrderStatusFlagDef.RESPONSE_SEND,
                WEB3StartStatusFlgDef.RESPONSE_SEND,
                WEB3ResultStatusFlagDef.REMAINING_CALCULATION_COMPLETE,
                OrderStatusEnum.ACCEPTED.intValue() + "",
                WEB3ModifyCancelTypeDef.INITIAL_VALUE,
                WEB3StatusDef.DATA_ERROR,
            },
            {WEB3AioJudgeResultDef.J},            
            
            //金融機@関連携入出金状況テーブル [処理区分]           １：決済完了
            //金融機@関連携入出金状況テーブル [処理FLAG(注文)]     ２：応答送信
            //金融機@関連携入出金状況テーブル [処理FLAG(決済開始)] ２：応答送信
            //金融機@関連携入出金状況テーブル [処理FLAG(決済結果)] ９：余力再計算完了
            //注文単位テーブル              [注文状態]          NO RECORD
            //注文単位テーブル              [注文取消区分]       NO RECORD
            //入出金伝票受付キューテーブル   [処理区分]           NO RECORD
            //52)                          [判定結果]  － ---> E 
            {
                WEB3TransactionStatusDef.OK,
                WEB3OrderStatusFlagDef.RESPONSE_SEND,
                WEB3StartStatusFlgDef.RESPONSE_SEND,
                WEB3ResultStatusFlagDef.REMAINING_CALCULATION_COMPLETE,
                null,
                null,
                null,
            },
            {WEB3AioJudgeResultDef.E},
          
            //金融機@関連携入出金状況テーブル [処理区分]           １：決済完了
            //金融機@関連携入出金状況テーブル [処理FLAG(注文)]     ２：応答送信
            //金融機@関連携入出金状況テーブル [処理FLAG(決済開始)] ２：応答送信
            //金融機@関連携入出金状況テーブル [処理FLAG(決済結果)] A：決済再処理完了
            //注文単位テーブル              [注文状態]          １：受付済（新規注文）
            //注文単位テーブル              [注文取消区分]       ０：初期値
            //入出金伝票受付キューテーブル   [処理区分]           その他
            //54)                          [判定結果]  － ---> D  
            {
                WEB3TransactionStatusDef.OK,
                WEB3OrderStatusFlagDef.RESPONSE_SEND,
                WEB3StartStatusFlgDef.RESPONSE_SEND,
                WEB3ResultStatusFlagDef.SETTLEMENT_RESEND_PROCESS_COMPLETE,
                OrderStatusEnum.ACCEPTED.intValue() + "",
                WEB3ModifyCancelTypeDef.INITIAL_VALUE,
                DefaultStatus.OTHER,
            },
            {WEB3AioJudgeResultDef.D},            
            
            //金融機@関連携入出金状況テーブル [処理区分]           １：決済完了
            //金融機@関連携入出金状況テーブル [処理FLAG(注文)]     ２：応答送信
            //金融機@関連携入出金状況テーブル [処理FLAG(決済開始)] ２：応答送信
            //金融機@関連携入出金状況テーブル [処理FLAG(決済結果)] A：決済再処理完了
            //注文単位テーブル              [注文状態]          ３：発注済（新規注文）
            //注文単位テーブル              [注文取消区分]       ０：初期値
            //入出金伝票受付キューテーブル   [処理区分]           
            //56)                          [判定結果]  － ---> E 
            {
                WEB3TransactionStatusDef.OK,
                WEB3OrderStatusFlagDef.RESPONSE_SEND,
                WEB3StartStatusFlgDef.RESPONSE_SEND,
                WEB3ResultStatusFlagDef.SETTLEMENT_RESEND_PROCESS_COMPLETE,
                OrderStatusEnum.ORDERED.intValue() + "",
                WEB3ModifyCancelTypeDef.INITIAL_VALUE,
                DefaultStatus.ANY,
            },
            {WEB3AioJudgeResultDef.E},            
            
            //金融機@関連携入出金状況テーブル [処理区分]           １：決済完了
            //金融機@関連携入出金状況テーブル [処理FLAG(注文)]     ２：応答送信
            //金融機@関連携入出金状況テーブル [処理FLAG(決済開始)] ２：応答送信
            //金融機@関連携入出金状況テーブル [処理FLAG(決済結果)] A：決済再処理完了
            //注文単位テーブル              [注文状態]          ６：発注失敗（新規注文）
            //注文単位テーブル              [注文取消区分]       ０：初期値
            //入出金伝票受付キューテーブル   [処理区分]           
            //58)                          [判定結果]  － ---> F 
            {
                WEB3TransactionStatusDef.OK,
                WEB3OrderStatusFlagDef.RESPONSE_SEND,
                WEB3StartStatusFlgDef.RESPONSE_SEND,
                WEB3ResultStatusFlagDef.SETTLEMENT_RESEND_PROCESS_COMPLETE,
                OrderStatusEnum.NOT_ORDERED.intValue() + "",
                WEB3ModifyCancelTypeDef.INITIAL_VALUE,
                DefaultStatus.ANY,
            },
            {WEB3AioJudgeResultDef.F},            
            
            //金融機@関連携入出金状況テーブル [処理区分]           １：決済完了
            //金融機@関連携入出金状況テーブル [処理FLAG(注文)]     ２：応答送信
            //金融機@関連携入出金状況テーブル [処理FLAG(決済開始)] ２：応答送信
            //金融機@関連携入出金状況テーブル [処理FLAG(決済結果)] A：決済再処理完了
            //注文単位テーブル              [注文状態]          １：受付済（新規注文）
            //注文単位テーブル              [注文取消区分]       ０：初期値
            //入出金伝票受付キューテーブル   [処理区分]           ９：エラー
            //60)                          [判定結果]  － ---> J 
            {
                WEB3TransactionStatusDef.OK,
                WEB3OrderStatusFlagDef.RESPONSE_SEND,
                WEB3StartStatusFlgDef.RESPONSE_SEND,
                WEB3ResultStatusFlagDef.SETTLEMENT_RESEND_PROCESS_COMPLETE,
                OrderStatusEnum.ACCEPTED.intValue() + "",
                WEB3ModifyCancelTypeDef.INITIAL_VALUE,
                WEB3StatusDef.DATA_ERROR,
            },
            {WEB3AioJudgeResultDef.J},            
            
            //金融機@関連携入出金状況テーブル [処理区分]           １：決済完了
            //金融機@関連携入出金状況テーブル [処理FLAG(注文)]     ２：応答送信
            //金融機@関連携入出金状況テーブル [処理FLAG(決済開始)] ２：応答送信
            //金融機@関連携入出金状況テーブル [処理FLAG(決済結果)] A：決済再処理完了
            //注文単位テーブル              [注文状態]          NO RECORD
            //注文単位テーブル              [注文取消区分]       NO RECORD
            //入出金伝票受付キューテーブル   [処理区分]           NO RECORD
            //62)                          [判定結果]  － ---> E 
            {
                WEB3TransactionStatusDef.OK,
                WEB3OrderStatusFlagDef.RESPONSE_SEND,
                WEB3StartStatusFlgDef.RESPONSE_SEND,
                WEB3ResultStatusFlagDef.SETTLEMENT_RESEND_PROCESS_COMPLETE,
                null,
                null,
                null,
            },
            {WEB3AioJudgeResultDef.E},
                
            //金融機@関連携入出金状況テーブル [処理区分]           １：決済完了
            //金融機@関連携入出金状況テーブル [処理FLAG(注文)]     その他
            //金融機@関連携入出金状況テーブル [処理FLAG(決済開始)] その他
            //金融機@関連携入出金状況テーブル [処理FLAG(決済結果)] その他
            //注文単位テーブル              [注文状態]          
            //注文単位テーブル              [注文取消区分]       
            //入出金伝票受付キューテーブル   [処理区分]           
            //64)                          [判定結果]  － ---> B 
            {
                WEB3TransactionStatusDef.OK,
                DefaultStatus.OTHER,
                DefaultStatus.OTHER,
                DefaultStatus.OTHER,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
            },
            {WEB3AioJudgeResultDef.B},
                    
            //金融機@関連携入出金状況テーブル [処理区分]           ２：決済中止
            //金融機@関連携入出金状況テーブル [処理FLAG(注文)]     ２：応答送信
            //金融機@関連携入出金状況テーブル [処理FLAG(決済開始)] ２：応答送信
            //金融機@関連携入出金状況テーブル [処理FLAG(決済結果)] ３：通知エラー（FAIL)
            //注文単位テーブル              [注文状態]          
            //注文単位テーブル              [注文取消区分]       
            //入出金伝票受付キューテーブル   [処理区分]           
            //66)                          [判定結果]  － ---> G 
            {
                WEB3TransactionStatusDef.NG,
                WEB3OrderStatusFlagDef.RESPONSE_SEND,
                WEB3StartStatusFlgDef.RESPONSE_SEND,
                WEB3ResultStatusFlagDef.NOTIFY_ERROR_FAIL,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
            },
            {WEB3AioJudgeResultDef.G},
                    
            //金融機@関連携入出金状況テーブル [処理区分]           ２：決済中止
            //金融機@関連携入出金状況テーブル [処理FLAG(注文)]     その他
            //金融機@関連携入出金状況テーブル [処理FLAG(決済開始)] その他
            //金融機@関連携入出金状況テーブル [処理FLAG(決済結果)] その他
            //注文単位テーブル              [注文状態]          
            //注文単位テーブル              [注文取消区分]       
            //入出金伝票受付キューテーブル   [処理区分]           
            //68)                          [判定結果]  － ---> B 
            {
                WEB3TransactionStatusDef.NG,
                DefaultStatus.OTHER,
                DefaultStatus.OTHER,
                DefaultStatus.OTHER,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
            },
            {WEB3AioJudgeResultDef.B},
            
            //金融機@関連携入出金状況テーブル [処理区分]           ３：エラー
            //金融機@関連携入出金状況テーブル [処理FLAG(注文)]     ０：未処理
            //金融機@関連携入出金状況テーブル [処理FLAG(決済開始)] 
            //金融機@関連携入出金状況テーブル [処理FLAG(決済結果)] 
            //注文単位テーブル              [注文状態]          
            //注文単位テーブル              [注文取消区分]       
            //入出金伝票受付キューテーブル   [処理区分]           
            //70)                          [判定結果]  － ---> C 
            {
                WEB3TransactionStatusDef.ERROR,
                WEB3OrderStatusFlagDef.NOT_DEAL,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
            },
            {WEB3AioJudgeResultDef.C},
            
            //金融機@関連携入出金状況テーブル [処理区分]           ３：エラー
            //金融機@関連携入出金状況テーブル [処理FLAG(注文)]     １：要求受信
            //金融機@関連携入出金状況テーブル [処理FLAG(決済開始)] 
            //金融機@関連携入出金状況テーブル [処理FLAG(決済結果)] 
            //注文単位テーブル              [注文状態]          
            //注文単位テーブル              [注文取消区分]       
            //入出金伝票受付キューテーブル   [処理区分]           
            //72)                          [判定結果]  － ---> C 
            {
                WEB3TransactionStatusDef.ERROR,
                WEB3OrderStatusFlagDef.REPUEST_RECEIPT,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
            },
            {WEB3AioJudgeResultDef.C},
            
            //金融機@関連携入出金状況テーブル [処理区分]           ３：エラー
            //金融機@関連携入出金状況テーブル [処理FLAG(注文)]     ２：応答送信
            //金融機@関連携入出金状況テーブル [処理FLAG(決済開始)] ０：未処理
            //金融機@関連携入出金状況テーブル [処理FLAG(決済結果)] 
            //注文単位テーブル              [注文状態]          
            //注文単位テーブル              [注文取消区分]       
            //入出金伝票受付キューテーブル   [処理区分]           
            //74)                          [判定結果]  － ---> C 
            {
                WEB3TransactionStatusDef.ERROR,
                WEB3OrderStatusFlagDef.RESPONSE_SEND,
                WEB3StartStatusFlgDef.NOT_DEAL,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
            },
            {WEB3AioJudgeResultDef.C},
                
            //金融機@関連携入出金状況テーブル [処理区分]           ３：エラー
            //金融機@関連携入出金状況テーブル [処理FLAG(注文)]     ２：応答送信
            //金融機@関連携入出金状況テーブル [処理FLAG(決済開始)] １：要求受信
            //金融機@関連携入出金状況テーブル [処理FLAG(決済結果)] 
            //注文単位テーブル              [注文状態]          
            //注文単位テーブル              [注文取消区分]       
            //入出金伝票受付キューテーブル   [処理区分]           
            //76)                          [判定結果]  － ---> C 
            {
                WEB3TransactionStatusDef.ERROR,
                WEB3OrderStatusFlagDef.RESPONSE_SEND,
                WEB3StartStatusFlgDef.REPUEST_RECEIPT,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
            },
            {WEB3AioJudgeResultDef.C},    
            
            //金融機@関連携入出金状況テーブル [処理区分]           ３：エラー
            //金融機@関連携入出金状況テーブル [処理FLAG(注文)]     ２：応答送信
            //金融機@関連携入出金状況テーブル [処理FLAG(決済開始)] ２：応答送信
            //金融機@関連携入出金状況テーブル [処理FLAG(決済結果)] ４：通知エラー（ERROR)
            //注文単位テーブル              [注文状態]          
            //注文単位テーブル              [注文取消区分]       
            //入出金伝票受付キューテーブル   [処理区分]           
            //78)                          [判定結果]  － ---> H 
            {
                WEB3TransactionStatusDef.ERROR,
                WEB3OrderStatusFlagDef.RESPONSE_SEND,
                WEB3StartStatusFlgDef.RESPONSE_SEND,
                WEB3ResultStatusFlagDef.NOTIFY_ERROR_ERROR,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
            },
            {WEB3AioJudgeResultDef.H},    
            
            //金融機@関連携入出金状況テーブル [処理区分]           ３：エラー
            //金融機@関連携入出金状況テーブル [処理FLAG(注文)]     ２：応答送信
            //金融機@関連携入出金状況テーブル [処理FLAG(決済開始)] ２：応答送信
            //金融機@関連携入出金状況テーブル [処理FLAG(決済結果)] ６：セッションエラー（COMPLETE)
            //注文単位テーブル              [注文状態]          
            //注文単位テーブル              [注文取消区分]       
            //入出金伝票受付キューテーブル   [処理区分]           
            //80)                          [判定結果]  － ---> N 
            {
                WEB3TransactionStatusDef.ERROR,
                WEB3OrderStatusFlagDef.RESPONSE_SEND,
                WEB3StartStatusFlgDef.RESPONSE_SEND,
                WEB3ResultStatusFlagDef.SESSION_ERROR_COMPLETE,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
            },
            {WEB3AioJudgeResultDef.N},   
            
            //金融機@関連携入出金状況テーブル [処理区分]           ３：エラー
            //金融機@関連携入出金状況テーブル [処理FLAG(注文)]     ２：応答送信
            //金融機@関連携入出金状況テーブル [処理FLAG(決済開始)] ２：応答送信
            //金融機@関連携入出金状況テーブル [処理FLAG(決済結果)] ７：セッションエラー（COMPLETE以外)
            //注文単位テーブル              [注文状態]          
            //注文単位テーブル              [注文取消区分]       
            //入出金伝票受付キューテーブル   [処理区分]           
            //82)                          [判定結果]  － ---> O
            {
                WEB3TransactionStatusDef.ERROR,
                WEB3OrderStatusFlagDef.RESPONSE_SEND,
                WEB3StartStatusFlgDef.RESPONSE_SEND,
                WEB3ResultStatusFlagDef.SESSION_ERROR_COMPLETE_EXCEPT,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
            },
            {WEB3AioJudgeResultDef.O},   
            
            //金融機@関連携入出金状況テーブル [処理区分]           ３：エラー
            //金融機@関連携入出金状況テーブル [処理FLAG(注文)]     ２：応答送信
            //金融機@関連携入出金状況テーブル [処理FLAG(決済開始)] ２：応答送信
            //金融機@関連携入出金状況テーブル [処理FLAG(決済結果)] その他
            //注文単位テーブル              [注文状態]          
            //注文単位テーブル              [注文取消区分]       
            //入出金伝票受付キューテーブル   [処理区分]           
            //84)                          [判定結果]  － ---> B
            {
                WEB3TransactionStatusDef.ERROR,
                WEB3OrderStatusFlagDef.RESPONSE_SEND,
                WEB3StartStatusFlgDef.RESPONSE_SEND,
                DefaultStatus.OTHER,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
            },
            {WEB3AioJudgeResultDef.B},  
            
            //金融機@関連携入出金状況テーブル [処理区分]           ３：エラー
            //金融機@関連携入出金状況テーブル [処理FLAG(注文)]     ２：応答送信
            //金融機@関連携入出金状況テーブル [処理FLAG(決済開始)] ５：キャンセル
            //金融機@関連携入出金状況テーブル [処理FLAG(決済結果)] 
            //注文単位テーブル              [注文状態]          
            //注文単位テーブル              [注文取消区分]       
            //入出金伝票受付キューテーブル   [処理区分]           
            //86)                          [判定結果]  － ---> I
            {
                WEB3TransactionStatusDef.ERROR,
                WEB3OrderStatusFlagDef.RESPONSE_SEND,
                WEB3StartStatusFlgDef.CANCEL,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
            },
            {WEB3AioJudgeResultDef.I},  
            
            //金融機@関連携入出金状況テーブル [処理区分]           ３：エラー
            //金融機@関連携入出金状況テーブル [処理FLAG(注文)]     ２：応答送信
            //金融機@関連携入出金状況テーブル [処理FLAG(決済開始)] その他
            //金融機@関連携入出金状況テーブル [処理FLAG(決済結果)] その他
            //注文単位テーブル              [注文状態]          
            //注文単位テーブル              [注文取消区分]       
            //入出金伝票受付キューテーブル   [処理区分]           
            //88)                          [判定結果]  － ---> C
            {
                WEB3TransactionStatusDef.ERROR,
                WEB3OrderStatusFlagDef.RESPONSE_SEND,
                DefaultStatus.OTHER,
                DefaultStatus.OTHER,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
            },
            {WEB3AioJudgeResultDef.C},  
                                    
            //金融機@関連携入出金状況テーブル [処理区分]           ３：エラー
            //金融機@関連携入出金状況テーブル [処理FLAG(注文)]     その他
            //金融機@関連携入出金状況テーブル [処理FLAG(決済開始)] その他
            //金融機@関連携入出金状況テーブル [処理FLAG(決済結果)] その他
            //注文単位テーブル              [注文状態]          
            //注文単位テーブル              [注文取消区分]       
            //入出金伝票受付キューテーブル   [処理区分]           
            //90)                          [判定結果]  － ---> C
            {
                WEB3TransactionStatusDef.ERROR,
                DefaultStatus.OTHER,
                DefaultStatus.OTHER,
                DefaultStatus.OTHER,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
            },
            {WEB3AioJudgeResultDef.C},  
            
            //金融機@関連携入出金状況テーブル [処理区分]           その他：エラー
            //金融機@関連携入出金状況テーブル [処理FLAG(注文)]     
            //金融機@関連携入出金状況テーブル [処理FLAG(決済開始)] 
            //金融機@関連携入出金状況テーブル [処理FLAG(決済結果)] 
            //注文単位テーブル              [注文状態]          
            //注文単位テーブル              [注文取消区分]       
            //入出金伝票受付キューテーブル   [処理区分]           
            //92)                          [判定結果]  － ---> J
            {
                DefaultStatus.OTHER,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
            },
            {WEB3AioJudgeResultDef.J},  
        };
        itemKey = itemKeyTemp;
        
        for (int m = 0; m < itemKey.length; m = m + 2)
        {
            cashOutStatusMap.put(new DefaultStatus(itemKey[m]), itemKey[m + 1][0]);
        }
    }

    /**
     * (get処理状況メッセージコード)<BR>
     * 引数により、オンライン入金の場合、処理状況を取得し、返却する<BR>
     * 「ＤＢ更新仕様\10.入出金\入出金ステータス構成表.xls」の金融機@関決済連携（1）を参照する<BR>
     *
     * @@param l_strTransferStatus - (金融機@関連携入出金状況テーブル.処理区分)
     * @@param l_strOrderStatusFlag - (処理FLAG（注文）)
     * @@param l_strStartStatusFlg - (処理FLAG（決済開始）)
     * @@param l_strResultStatusFlag - (処理FLAG（決済結果）)
     * @@param l_strOrderStatus - (注文状態)
     * @@param l_strCancelType - (注文取消区分)
     * @@param l_strKeyTableStatus - (入出金伝票受付キューテーブル.処理区分)
     * @@return String
     */
    public String getStatus(
        String l_strTransferStatus,
        String l_strOrderStatusFlag,
        String l_strStartStatusFlg,
        String l_strResultStatusFlag,
        String l_StrOrderStatus,
        String l_StrOrderCancelType,
        String l_StrKeyTableStatus)
    {
        String[] l_params = 
        {
            l_strTransferStatus, 
            l_strOrderStatusFlag, 
            l_strStartStatusFlg, 
            l_strResultStatusFlag, 
            l_StrOrderStatus, 
            l_StrOrderCancelType, 
            l_StrKeyTableStatus
        };
        
        Status l_status = new DefaultStatus(l_params);
        return super.getStatus(l_status);
    }
    
    /* (non-Javadoc)
     * @@see webbroker3.aio.WEB3AioAbstractStatusUtility#getStatusMap()
     */
    protected Map getStatusMap()
    {
        return cashOutStatusMap;
    }

    protected String[][] getStringStatus()
    {
        return itemKey;
    }   
}
@
