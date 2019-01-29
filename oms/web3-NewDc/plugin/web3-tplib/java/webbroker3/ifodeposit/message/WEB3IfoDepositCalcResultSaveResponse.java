head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.53.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3IfoDepositCalcResultSaveResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 証拠金情報DB保存レスポンス(WEB3IfoDepositCalcResultSaveResponse.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2007/08/22 孫(FLJ) 新規作成
 */
package webbroker3.ifodeposit.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (証拠金情報DB保存レスポンス)<BR>
 * 証拠金情報DB保存レスポンスクラス。<BR>
 * 
 * @@author Sun (FLJ)
 */
public class WEB3IfoDepositCalcResultSaveResponse extends WEB3GenResponse
{
    
    public static final String PTYPE = "ifodeposit_calc_result_save";

    /**
     * コンストラクター
     */
    public WEB3IfoDepositCalcResultSaveResponse()
    {

    }
    
	/**
	 * スレッドNO 
	 */
	public	Long threadNo;
	
    /**
     * (成功数)
     */
    public Long success;

    /**
     * (失敗数)
     */
    public Long failure;


}
@
