head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.06.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoFxAccountInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 為替保証金口座情報(WEB3AccInfoFxAccountInfo)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/2/24 艾興 (中訊) 新規作成
Revesion History : 2008/5/22 車進 (中訊) 仕様変更・モデルNo.234
*/

package webbroker3.accountinfo.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (為替保証金口座情報) <BR>
 * 為替保証金口座情報 <BR>
 * 
 * @@author 艾興(中訊)
 * @@version 1.0
 */
public class WEB3AccInfoFxAccountInfo extends Message
{
    /**
     * (FXログインＩＤ)<BR>
     * FXログインＩＤ
     */
    public String fxLoginId;
    
    /**
     * (FX口座番号（1万通貨コース）)<BR>
     * FX口座番号（1万通貨コース）
     */
    public String fxAccountCode1;
    
    /**
     * (FX口座番号（10万通貨コース）)<BR>
     * FX口座番号（10万通貨コース）
     */
    public String fxAccountCode2;

    /**
     * (FXシステムコード)<BR>
     * FXシステムコード
     */
    public String fxSystemCode;

    /**
     * (FX口座情報) <BR>
     * コンストラクタ。 <BR>
     * 
     * @@return webbroker3.accountinfo.message.WEB3FXAccInformationUnit
     * @@roseuid 41B0393C0146
     */
    public WEB3AccInfoFxAccountInfo()
    {
    }
    
}@
