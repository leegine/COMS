head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.50.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondAccountInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 顧客情報(WEB3AdminBondAccountInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 趙林鵬 (中訊) 新規作成
*/

package webbroker3.bd.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (顧客情報)<BR>
 * 顧客情報クラス
 * <BR>
 * @@author 趙林鵬
 * @@version 1.0
 */
public class WEB3AdminBondAccountInfo  extends Message
{
    /**
     *　@ログユーティリティ<BR> 
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondAccountInfo.class);
    
    /**
     * (部店コード)<BR>
     * 部店コード
     */
    public String branchCode;
    
    /**
     * (顧客コード)<BR>
     * 顧客コード
     */
    public String accountCode;
    
    /**
     * (顧客名)<BR>
     * 顧客名
     */
    public String accountName;
    
    /**
     * @@roseuid 44E336320148
     */
    public WEB3AdminBondAccountInfo() 
    {
     
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。 <BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
     * <BR>
     * １)　@部店コードチェック<BR>
     * 　@this.部店コード==nullの場合、例外をスローする。 <BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00833<BR>
     * <BR>
     * 　@this.部店コードが３桁でない場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00834<BR>
     * <BR>
     * 　@this.部店コードが数値でない場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_01729<BR>
     * <BR>
     * ２)　@顧客コードチェック<BR>
     * 　@this.顧客コード==nullの場合、例外をスローする。 <BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00835<BR>
     * <BR>
     * 　@this.顧客コードが６桁でない場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00836<BR>
     * <BR>
     * 　@this.顧客コードが数値でない場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_01043
     * @@throws WEB3BaseException　@
     * @@roseuid 44BDF629036B
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //当リクエストデータの整合性チェックを行う。 
        //（ただし、当クラス内で完結する簡易チェックのみとする。） 
        //１)　@部店コードチェック<BR>
        //this.部店コード==nullの場合、例外をスローする。 
        if (this.branchCode == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "部店コードが未指定です。");
        }
        
        //this.部店コードが３桁でない場合、例外をスローする。
        if (this.branchCode.length() != 3)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00834,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "部店コードのサイズが不正です。");
        }
        
        //this.部店コードが数値でない場合、例外をスローする。
        if (!WEB3StringTypeUtility.isInteger(this.branchCode))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01729,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "部店コードが数値以外の値です。");
        }
        
        //２)　@顧客コードチェック
        //this.顧客コード==nullの場合、例外をスローする。 
        if (this.accountCode == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00835,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "顧客コードが未指定です");
        }
        
        //this.顧客コードが６桁でない場合、例外をスローする。
        if (this.accountCode.length() != 6)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00836,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "顧客コードのサイズが不正です。");
        }
        
        //this.顧客コードが数値でない場合、例外をスローする。
        if (!WEB3StringTypeUtility.isInteger(this.accountCode))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01043,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "顧客コードの値が数字以外の値です。");
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
