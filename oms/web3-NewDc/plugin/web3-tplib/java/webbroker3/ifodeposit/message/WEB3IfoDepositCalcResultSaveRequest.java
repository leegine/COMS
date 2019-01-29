head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.53.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3IfoDepositCalcResultSaveRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 証拠金計算結果保存リクエストクラス(WEB3IfoDepositCalcResultSaveRequest.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2007/08/22 孫(FLJ) 新規作成
 */
package webbroker3.ifodeposit.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (証拠金計算結果保存リクエスト)<BR>
 * 証拠金計算結果保存リクエストクラス。<BR>
 * 
 * @@author Sun (FLJ)
 */
public class WEB3IfoDepositCalcResultSaveRequest extends WEB3GenRequest
{
    
    public static final String PTYPE = "ifodeposit_calc_result_save";

    /**
     * コンストラクター
     */
    public WEB3IfoDepositCalcResultSaveRequest()
    {

    }
    
	/**
	 * スレッドNO 
	 */
	public	Long threadNo;
	
	/**
	 * 口座開始ID
	 */
	public long fromAccountId;
	
	/**
	 * 口座終了ID
	 */
	public long toAccountId;

    /**
     * @@return webbroker3.common.message.WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3IfoDepositCalcResultSaveResponse();
    }
}
@
