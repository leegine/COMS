head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.37.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3DefaultMQSendResultForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : //TODO(WEB3DefaultMQSendResultForMock.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/05 徐宏偉 (中訊) 新規作成
*/
package webbroker3.mqgateway.stdimpls;

import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;

import webbroker3.mqgateway.WEB3MQSendResult;

/**
 * XXXXXXクラス//TODO
 *
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public class WEB3DefaultMQSendResultForMock implements WEB3MQSendResult
{

    public static final WEB3MQSendResult SUCCESS_RESULT_INSTANCE =
        WEB3DefaultMQSendResult.newSuccessResultInstance();

    /**
     * 処理結果を表すオブジェクト
     */
    private ProcessingResult result;

    // コンストラクタを使用しないようにprivateで宣言
    private WEB3DefaultMQSendResultForMock()
    {

    }

    // コンストラクタを使用しないようにprivateで宣言
    private WEB3DefaultMQSendResultForMock(ProcessingResult result)
    {
        this.result = result;
    }

    /**
     * 成功を表す新しいインスタンスを生成する<br>
     * <br>
     * @@return 成功を表す新しいインスタンス
     */
    public static WEB3DefaultMQSendResultForMock newSuccessResultInstance()
    {
        return new WEB3DefaultMQSendResultForMock(
            ProcessingResult.newSuccessResultInstance());
    }

    /**
     * 失敗を表す新しいインスタンスを生成する<br>
     * <br>
     * @@return 失敗を表す新しいインスタンス
     */
    public static WEB3DefaultMQSendResultForMock newFailedResultInstance(ErrorInfo errorInfo)
    {
        return new WEB3DefaultMQSendResultForMock(
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
