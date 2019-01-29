head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.04.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FEqConAccountOpenAgreementResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外株口座開設取引同意レスポンス(WEB3FEqConAccountOpenAgreementResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/18 周勇(中訊) 新規作成
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (外株口座開設取引同意レスポンス)<BR>
 * 外株口座開設取引同意レスポンスクラス
 * 
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3FEqConAccountOpenAgreementResponse extends WEB3GenResponse 
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "feq_con_account_open_agreement";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200503171454L;
    
    /**
     * (顧客名)<BR>
     * 顧客名
     */
    public String accountName;
    
    /**
     * (質問情報一覧)<BR>
     * 質問情報一覧
     */
    public WEB3FEqConAccountOpenQuestionInfo[] questionInfoList;
    
    /**
     * @@roseuid 423552A901F4
     */
    public WEB3FEqConAccountOpenAgreementResponse() 
    {
     
    }
    
    /**
     * (コンストラクタ)<BR>
     * 引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3FEqConAccountOpenAgreementResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
