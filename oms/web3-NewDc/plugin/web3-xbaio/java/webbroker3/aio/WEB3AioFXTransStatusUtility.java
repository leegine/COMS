head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.23.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioFXTransStatusUtility.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 為替保証金連携の処理状況を取得の処理クラス(WEB3AioFXTransStatusUtility)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/01 屈陽 (中訊) 新規作成 
                 : 2006/07/13 韋念瓊 (中訊) 仕様変更 モデル595
                 : 2006/11/16 鈴木 (SCS) 仕様変更 モデル686
*/

package webbroker3.aio;

import java.util.Hashtable;
import java.util.Map;

import webbroker3.aio.define.WEB3AioAcceptResultCodeDef;
import webbroker3.aio.define.WEB3AioJudgeResultDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3OrderStatusDef;
import webbroker3.common.define.WEB3SendRcvDivDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.define.WEB3TransferStatusDivDef;


/**
 * 為替保証金連携の処理状況を取得の処理クラス
 * 
 * @@author 屈陽 (中訊)
 * @@version 1.0
 */
public class WEB3AioFXTransStatusUtility extends WEB3AioAbstractStatusUtility
{
    /**
     * 詳しく処理ステータスカラム(Map)
     */
    private static Map fxStatusMap = new Hashtable(); 
    
    /**
     * 詳しく処理ステータスカラム(String[][])
     */
    private static String[][] itemKey = null;
    //initialize the fxStatusMap
    static
    {
        //振替状況区分   送受信区分    受付結果コード    注文状態    注文取消区分   処理区分        
        
        String[][] itemKeytemp = 
        {
            //1> 決済中  送信済   －   －   －  －   -----> Q
            {WEB3TransferStatusDivDef.PROCESSING, 
                WEB3SendRcvDivDef.SEND_COMPLETE, 
                DefaultStatus.ANY, 
                DefaultStatus.ANY, 
                DefaultStatus.ANY, 
                DefaultStatus.ANY}, {WEB3AioJudgeResultDef.Q},
            //2> 決済中  その他  －   －   －  －   -----> J  
            {WEB3TransferStatusDivDef.PROCESSING, 
                DefaultStatus.OTHER, 
                DefaultStatus.ANY, 
                DefaultStatus.ANY, 
                DefaultStatus.ANY, 
                DefaultStatus.ANY}, {WEB3AioJudgeResultDef.J},
            //3> 決済完了   受信済    00000000    NO RECORD     NO RECORD    NO RECORD  -----> J
            {WEB3TransferStatusDivDef.PROCESS_COMPLETE, 
                WEB3SendRcvDivDef.RECEIVE_COMPLETE, 
                WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000000, 
                null, 
                null, 
                null}, {WEB3AioJudgeResultDef.J},
            //4> 決済エラー  受信済   00000105    －   －   －  －   -----> S        
            {WEB3TransferStatusDivDef.PROCESS_ERROR, 
                WEB3SendRcvDivDef.RECEIVE_COMPLETE, 
                WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000105, 
                DefaultStatus.ANY, 
                DefaultStatus.ANY, 
                DefaultStatus.ANY}, {WEB3AioJudgeResultDef.S},
            //5> 決済エラー     受信済         00000199    －   －   －  －   -----> T        
            {WEB3TransferStatusDivDef.PROCESS_ERROR, 
                WEB3SendRcvDivDef.RECEIVE_COMPLETE, 
                WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000199, 
                DefaultStatus.ANY, 
                DefaultStatus.ANY, 
                DefaultStatus.ANY}, {WEB3AioJudgeResultDef.T},
            //6> 決済エラー     受信済         00000204    －   －   －  －   -----> R        
            {WEB3TransferStatusDivDef.PROCESS_ERROR, 
                WEB3SendRcvDivDef.RECEIVE_COMPLETE, 
                WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000204, 
                DefaultStatus.ANY, 
                DefaultStatus.ANY, 
                DefaultStatus.ANY}, {WEB3AioJudgeResultDef.R},
            //7> 決済エラー     受信済         00000205    －   －   －  －   -----> N
            {WEB3TransferStatusDivDef.PROCESS_ERROR,
                WEB3SendRcvDivDef.RECEIVE_COMPLETE,
                WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000205,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
                DefaultStatus.ANY}, {WEB3AioJudgeResultDef.N},
            //8> 決済エラー     受信済         00000206    －   －   －  －   -----> O
            {WEB3TransferStatusDivDef.PROCESS_ERROR,
                WEB3SendRcvDivDef.RECEIVE_COMPLETE,
                WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000206,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
                DefaultStatus.ANY}, {WEB3AioJudgeResultDef.O},
            //9> 決済エラー     受信済         00000207    －   －   －  －   -----> P
            {WEB3TransferStatusDivDef.PROCESS_ERROR,
                WEB3SendRcvDivDef.RECEIVE_COMPLETE,
                WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000207,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
                DefaultStatus.ANY}, {WEB3AioJudgeResultDef.P},
            //10> 決済エラー     受信済         00000801    －   －   －  －   -----> U        
            {WEB3TransferStatusDivDef.PROCESS_ERROR, 
                WEB3SendRcvDivDef.RECEIVE_COMPLETE, 
                WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000801, 
                DefaultStatus.ANY, 
                DefaultStatus.ANY, 
                DefaultStatus.ANY}, {WEB3AioJudgeResultDef.U},    
            //11> 決済エラー     受信済         00000501    －   －   －  －   -----> U        
            {WEB3TransferStatusDivDef.PROCESS_ERROR, 
                WEB3SendRcvDivDef.RECEIVE_COMPLETE, 
                WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000501, 
                DefaultStatus.ANY, 
                DefaultStatus.ANY, 
                DefaultStatus.ANY}, {WEB3AioJudgeResultDef.V},    
            //12> 決済エラー     受信済         00000503    －   －   －  －   -----> U
            {WEB3TransferStatusDivDef.PROCESS_ERROR,
                WEB3SendRcvDivDef.RECEIVE_COMPLETE,
                WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000503,
                DefaultStatus.ANY,
                DefaultStatus.ANY,
                DefaultStatus.ANY}, {WEB3AioJudgeResultDef.V},

            //13> 決済エラー     受信済		その他    －   －   －  －   -----> J        
            {WEB3TransferStatusDivDef.PROCESS_ERROR, 
				WEB3SendRcvDivDef.RECEIVE_COMPLETE, 
                DefaultStatus.OTHER, 
                DefaultStatus.ANY, 
                DefaultStatus.ANY, 
                DefaultStatus.ANY}, {WEB3AioJudgeResultDef.J},
			//14> 決済エラー     その他    －   －   －  －   -----> J        
			{WEB3TransferStatusDivDef.PROCESS_ERROR, 
				DefaultStatus.OTHER, 
				DefaultStatus.ANY, 
				DefaultStatus.ANY, 
				DefaultStatus.ANY, 
				DefaultStatus.ANY}, {WEB3AioJudgeResultDef.J}, 
            
            //15> 決済完了    受信済     00000000   １：受付済      ０：初期値     －   -----> D       
            //16> 決済完了    受信済     00000000   １：受付済      ０：初期値     OTHER   -----> D       
            {WEB3TransferStatusDivDef.PROCESS_COMPLETE, 
                WEB3SendRcvDivDef.RECEIVE_COMPLETE, 
                WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000000, 
                WEB3OrderStatusDef.ACCEPTED_OPENORDER, 
                WEB3ModifyCancelTypeDef.INITIAL_VALUE, 
                DefaultStatus.OTHER}, {WEB3AioJudgeResultDef.D},
            //17> 決済完了  受信済      00000000   ３：発注済      ０：初期値     －   -----> E        
            {WEB3TransferStatusDivDef.PROCESS_COMPLETE, 
                WEB3SendRcvDivDef.RECEIVE_COMPLETE, 
                WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000000, 
                WEB3OrderStatusDef.MODIFYED, 
                WEB3ModifyCancelTypeDef.INITIAL_VALUE, 
                DefaultStatus.ANY}, {WEB3AioJudgeResultDef.E},
            //18> 決済完了  受信済     00000000   ６：発注失敗     ０：初期値     －    -----> F        
            {WEB3TransferStatusDivDef.PROCESS_COMPLETE, 
                WEB3SendRcvDivDef.RECEIVE_COMPLETE, 
                WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000000, 
                WEB3OrderStatusDef.MODIFY_FAIL_OPENORDER, 
                WEB3ModifyCancelTypeDef.INITIAL_VALUE, 
                DefaultStatus.ANY}, {WEB3AioJudgeResultDef.F},
            //19> 決済完了  受信済     00000000   １：受付済      ０：初期値  ９：エラー    -----> J
            {WEB3TransferStatusDivDef.PROCESS_COMPLETE, 
                WEB3SendRcvDivDef.RECEIVE_COMPLETE, 
                WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000000, 
                WEB3OrderStatusDef.ACCEPTED_OPENORDER, 
                WEB3ModifyCancelTypeDef.INITIAL_VALUE, 
                WEB3StatusDef.DATA_ERROR}, {WEB3AioJudgeResultDef.J},
            //20> その他  －   －   －  －   －   -----> J     
            {DefaultStatus.OTHER, 
                DefaultStatus.ANY, 
                DefaultStatus.ANY, 
                DefaultStatus.ANY, 
                DefaultStatus.ANY, 
                DefaultStatus.ANY}, {WEB3AioJudgeResultDef.J},
        };
        itemKey = itemKeytemp;
        for (int m = 0; m < itemKey.length; m = m + 2)
        {
            fxStatusMap.put(new DefaultStatus(itemKey[m]), itemKey[m + 1][0]);
        }
    }
    
    /**
     * (get処理状況メッセージコード)<BR>
     * 引数により、為替保証金連携の場合、処理状況を取得し、返却する<BR>
     * 「ＤＢ更新仕様\10.入出金\入出金ステータス構成表.xls」の<BR>
     *  為替保証金連携(2)を参照する<BR>
     * 
     * @@param l_transferStatusDiv - GFT振替状況テーブルの振替状況区分
     * @@param sendRcvDiv - GFT振替状況テーブルの送受信区分
     * @@param resultCode - GFT振替状況テーブルの受付結果コード
     * @@param orderSatus - 注文単位テーブルの注文状態
     * @@param cancel_type - 注文単位テーブルの注文取消区分
     * @@param status - 受付キューテーブルの処理区分
     * @@return String
     */
    public String getResult(
        String l_transferStatusDiv, 
        String sendRcvDiv, 
        String resultCode,
        String orderSatus,
        String cancel_type,
        String status)
    {
        String[] l_strParams = new String[]{
            l_transferStatusDiv, 
            sendRcvDiv, 
            resultCode, 
            orderSatus,
            cancel_type,
            status};
        
        WEB3AioAbstractStatusUtility.DefaultStatus l_status = 
            new DefaultStatus(l_strParams);
        
        return this.getStatus(l_status);
    }
    

    /* (non-Javadoc)
     * @@see webbroker3.aio.WEB3AioAbstractStatusUtility#getStatusMap()
     */
    public Map getStatusMap()
    {
        return WEB3AioFXTransStatusUtility.fxStatusMap;
    }
}
@
