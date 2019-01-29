head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.53.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FEqConAccountOpenQuestionInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外株口座開設質問情報(WEB3FEqConAccountOpenQuestionInfo)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/18 周勇(中訊) 新規作成
*/

package webbroker3.aio.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (外株口座開設質問情報)<BR>
 * 外株口座開設質問情報クラス
 * 
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3FEqConAccountOpenQuestionInfo extends Message 
{

    /**
     * (質問番号)<BR>
     * 質問番号
     */
    public String questionNumber;
    
    /**
     * (質問内容)<BR>
     * 質問内容
     */
    public String questionContent;
    
    /**
     * (質問回答)<BR>
     * 質問回答
     */
    public String questionAnswer;
    
    /**
     * (外株口座開設質問情報)<BR>
     * コンストラクタ
     * @@roseuid 41CF95DD00C3
     */
    public WEB3FEqConAccountOpenQuestionInfo() 
    {
     
    }
}
@
