head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.30.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioFEqConTransStatusUtility.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外株振替連携の処理状況を取得の処理クラス(WEB3AioFEqConTransStatusUtility)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/08/09 玉岡 和洋 (SRA) 新規作成 
*/

package webbroker3.aio;

import java.util.Hashtable;
import java.util.Map;

import webbroker3.aio.define.WEB3AioJudgeResultDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3OrderStatusDef;
import webbroker3.common.define.WEB3TransferStatusDivDef;


/**
 * 外株振替連携の処理状況を取得の処理クラス
 * 
 * @@author 玉岡 和洋 (SRA)
 * @@version 1.0
 */
public class WEB3AioFEqConTransStatusUtility extends WEB3AioAbstractStatusUtility
{
	/**
	 * 詳しく処理ステータスカラム(Map)
	 */
	private static Map feqConStatusMap = new Hashtable(); 
    
	/**
	 * 詳しく処理ステータスカラム(String[][])
	 */
	private static String[][] itemKey = null;
	//initialize the feqConStatusMap
	static
	{
		//振替状況区分   送受信区分    受付結果コード    注文状態    注文取消区分        
        
		String[][] itemKeytemp = 
		{
			//1> 決済中  －  －  －  －  -----> Q
			{WEB3TransferStatusDivDef.PROCESSING, 
				DefaultStatus.ANY, 
				DefaultStatus.ANY, 
				DefaultStatus.ANY, 
				DefaultStatus.ANY}, {WEB3AioJudgeResultDef.Q},
			//2> 決済エラー  －  －  －  －  -----> J        
			{WEB3TransferStatusDivDef.PROCESS_ERROR, 
				DefaultStatus.ANY, 
				DefaultStatus.ANY, 
				DefaultStatus.ANY, 
				DefaultStatus.ANY}, {WEB3AioJudgeResultDef.J},
			//3> 決済完了  －  －  １：受付済  ０：初期値  -----> D       
			{WEB3TransferStatusDivDef.PROCESS_COMPLETE, 
				DefaultStatus.ANY, 
				DefaultStatus.ANY, 
				WEB3OrderStatusDef.ACCEPTED_OPENORDER, 
				WEB3ModifyCancelTypeDef.INITIAL_VALUE}, {WEB3AioJudgeResultDef.D},
			//4> 決済完了  －  －  ３：発注済  ０：初期値  -----> E        
			{WEB3TransferStatusDivDef.PROCESS_COMPLETE, 
				DefaultStatus.ANY, 
				DefaultStatus.ANY, 
				WEB3OrderStatusDef.MODIFYED, 
				WEB3ModifyCancelTypeDef.INITIAL_VALUE}, {WEB3AioJudgeResultDef.E},
			//5> 決済完了  －  －  ６：発注失敗  ０：初期値  -----> F        
			{WEB3TransferStatusDivDef.PROCESS_COMPLETE, 
				DefaultStatus.ANY, 
				DefaultStatus.ANY, 
				WEB3OrderStatusDef.MODIFY_FAIL_OPENORDER, 
				WEB3ModifyCancelTypeDef.INITIAL_VALUE}, {WEB3AioJudgeResultDef.F},
			//6> 決済完了  －  －  １：受付済  ０：初期値  -----> J
			{WEB3TransferStatusDivDef.PROCESS_COMPLETE, 
				DefaultStatus.ANY, 
				DefaultStatus.ANY, 
				WEB3OrderStatusDef.ACCEPTED_OPENORDER, 
				WEB3ModifyCancelTypeDef.INITIAL_VALUE}, {WEB3AioJudgeResultDef.J},
			//7> 決済完了  －  －  NO RECORD  NO RECORD  -----> J
			{WEB3TransferStatusDivDef.PROCESS_COMPLETE, 
				DefaultStatus.ANY, 
				DefaultStatus.ANY, 
				null, 
				null}, {WEB3AioJudgeResultDef.J},
			//8> 取消  －  －  １４：発注済(取消)  ３：全部取消完了  -----> I
			{WEB3TransferStatusDivDef.CANCEL, 
				DefaultStatus.ANY, 
				DefaultStatus.ANY, 
				WEB3OrderStatusDef.MODIFYED_CANCELORDER, 
				WEB3ModifyCancelTypeDef.CANCELED}, {WEB3AioJudgeResultDef.I},
			//9> その他  －  －  －  －  -----> J     
			{DefaultStatus.OTHER, 
				DefaultStatus.ANY, 
				DefaultStatus.ANY, 
				DefaultStatus.ANY, 
				DefaultStatus.ANY}, {WEB3AioJudgeResultDef.J},
		};
		itemKey = itemKeytemp;
		for (int m = 0; m < itemKey.length; m = m + 2)
		{
			feqConStatusMap.put(new DefaultStatus(itemKey[m]), itemKey[m + 1][0]);
		}
	}
    
	/**
	 * (get処理状況メッセージコード)<BR>
	 * 引数により、外株振替連携の場合、処理状況を取得し、返却する<BR>
	 * 「ＤＢ更新仕様\10.入出金\入出金ステータス構成表.xls」の<BR>
	 *  外国株式連携(3)を参照する<BR>
	 * 
	 * @@param l_transferStatusDiv - UWG振替状況テーブルの振替状況区分
	 * @@param sendRcvDiv - UWG振替状況テーブルの送受信区分
	 * @@param resultCode - UWG振替状況テーブルの受付結果コード
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
		String cancel_type)
	{
		String[] l_strParams = new String[]{
			l_transferStatusDiv, 
			sendRcvDiv, 
			resultCode, 
			orderSatus,
			cancel_type};
        
		WEB3AioAbstractStatusUtility.DefaultStatus l_status = 
			new DefaultStatus(l_strParams);
        
		return this.getStatus(l_status);
	}
    

	/* (non-Javadoc)
	 * @@see webbroker3.aio.WEB3AioAbstractStatusUtility#getStatusMap()
	 */
	public Map getStatusMap()
	{
		return WEB3AioFEqConTransStatusUtility.feqConStatusMap;
	}
}
@
