head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.34.32;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiDetailCommon.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用サービス明細情報一覧行共通情報(WEB3AdminSrvRegiDetailCommon.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/21 李頴淵 新規作成
*/

package webbroker3.srvregi.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (サービス利用サービス明細情報一覧行共通情報)<BR>
 * サービス利用サービス明細情報一覧行共通情報データクラス<BR>
 * 
 * @@author 李頴淵
 * @@version 1.0
 */
public class WEB3AdminSrvRegiDetailCommon extends Message 
{
    
    /**
     * (ID)<BR>
     * サービス区分<BR>
     */
    public String serviceDiv;
    
    /**
     * (サービス名称)
     */
    public String serviceName;
    
    /**
     * (ステータス)<BR>
     * 0:停止中　@1:提供中（申込不可）　@2:提供中<BR>
     */
    public String serviceStatus;
    
    /**
     * (申込可能期間設定)<BR>
     * 0：無　@1：有<BR>
     */
    public String applyAbleSet;
    
    /**
     * (サービス利用サービス明細情報一覧行共通情報)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40EE32220025
     */
    public WEB3AdminSrvRegiDetailCommon() 
    {
     
    }
}
@
