head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3FPTForceLogoutInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.docadmin.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * 管理者 書面未承諾 強制ログアウト結果照会Unit
 */
public class WEB3FPTForceLogoutInfoUnit extends Message
{
    
    /**
     * 処理結果
     * 0:未処理
     * 1:処理済
     * 5:処理中
     * 9:エラー
     */
    public String transactionResult;
    
    /**
     * 処理開始日時
     */
    public Date transactionStartDate;
    
    /**
     * 処理終了日時
     */
    public Date transactionEndDate;
    
    /**
     * 処理件数
     */
    public String endCount;
    
    /**
     * 更新者コード
     */
    public String updaterCode;
    
    /**
     * 書面区分管理情報一覧
     */
    public WEB3FPTDocumentDivAdminInfoUnit[] documentDivList;
    
    /**
     * @@roseuid 47DF467702E6
     */
    public WEB3FPTForceLogoutInfoUnit() 
    {
     
    }
}
@
