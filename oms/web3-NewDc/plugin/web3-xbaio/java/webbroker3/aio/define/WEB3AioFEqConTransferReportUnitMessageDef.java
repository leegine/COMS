head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.47.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioFEqConTransferReportUnitMessageDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3AioFEqConTransferReportUnitMessageDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/05/08 韋念瓊 (中訊) 新規作成
*/

package webbroker3.aio.define;

/**
 * 外株振替レポート明細 のメッセージ  定数定義インタフェイス
 *                                                                     
 * @@author 韋念瓊
 * @@version 1.0
 */

public interface WEB3AioFEqConTransferReportUnitMessageDef
{
    /**
     * 10000000：受付済 
     */
    public static final String ACCEPT_RESULT_CODE_10000000 = "10000000";
    
    /**
     * 20000000：決済中 
     */
    public static final String ACCEPT_RESULT_CODE_20000000 = "20000000";
    
    /**
     * 90000000：取消済
     */
    public static final String ACCEPT_RESULT_CODE_90000000 = "90000000";
    
	/**
	 * 90000009：口座抹消
	 */
	public static final String ACCEPT_RESULT_CODE_90000009 = "90000009";
	
    /**
     * 99999999：決済失敗（システムエラー）
     */
    public static final String ACCEPT_RESULT_CODE_99999999 = "99999999";
    
    /**
     * 00000000：決済完了
     */
    public static final String ACCEPT_RESULT_CODE_00000000 = "00000000";
    
}
@
