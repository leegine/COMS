head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.47.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d8853e14978;
filename	WEB3DefaultMQSendResult.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : MQMessageSendResultインターフェースの実装クラス(WEB3DefaultMQSendResult.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2003/02/13 山田　@卓司(FLJ) 新規作成
*/
package webbroker3.mqgateway.stdimpls;

import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;

import webbroker3.mqgateway.WEB3MQSendResult;

/**
 * MQMessageSendResultインターフェースの実装クラス<br>
 *<br> 
 * @@author Takuji Yamada
 * @@version 1.0
 */
class WEB3DefaultMQSendResult implements WEB3MQSendResult
{

    public static final WEB3MQSendResult SUCCESS_RESULT_INSTANCE =
        WEB3DefaultMQSendResult.newSuccessResultInstance();

    /**
     * 処理結果を表すオブジェクト
     */
    private ProcessingResult result;

    // コンストラクタを使用しないようにprivateで宣言
    private WEB3DefaultMQSendResult()
    {

    }

    // コンストラクタを使用しないようにprivateで宣言
    private WEB3DefaultMQSendResult(ProcessingResult result)
    {
        this.result = result;
    }

    /**
     * 成功を表す新しいインスタンスを生成する<br>
     * <br>
     * @@return 成功を表す新しいインスタンス
     */
    static WEB3DefaultMQSendResult newSuccessResultInstance()
    {
        return new WEB3DefaultMQSendResult(
            ProcessingResult.newSuccessResultInstance());
    }

    /**
     * 失敗を表す新しいインスタンスを生成する<br>
     * <br>
     * @@return 失敗を表す新しいインスタンス
     */
    static WEB3DefaultMQSendResult newFailedResultInstance(ErrorInfo errorInfo)
    {
        return new WEB3DefaultMQSendResult(
            ProcessingResult.newFailedResultInstance(errorInfo));
    }

    /**
     * @@see webbroker3.mqgateway.WEB3MQSendResult#getErrorInfo()
     */
    public ErrorInfo getErrorInfo()
    {
        return result.getErrorInfo();
    }

    /**
     * @@see webbroker3.mqgateway.WEB3MQSendResult#isFailedResult()
     */
    public boolean isFailedResult()
    {
        return result.isFailedResult();
    }

    /**
     * @@see webbroker3.mqgateway.WEB3MQSendResult#isSuccessResult()
     */
    public boolean isSuccessResult()
    {
        return result.isSuccessfulResult();
    }

}
@
