head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.07.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoStopInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 停止情報一覧Unit(WEB3AccInfoStopInfoUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/09/19 呉艶飛 (中訊) 新規作成
*/
package webbroker3.accountinfo.message;


/**
 * (停止情報一覧Unit)<BR>
 * 停止情報一覧Unit<BR>
 * <BR>
 * @@author 呉艶飛<BR>
 * @@version 1.0<BR>
 */
public class WEB3AccInfoStopInfoUnit extends WEB3AccInfoStopInfo 
{
    /**
     * (コンストラクタ)
     *コンストラクタ
     */
    public WEB3AccInfoStopInfoUnit()
    {
        
    }
    /**
     * (部店コード)<BR>
     * 部店コード<BR>
     */
    public String branchCode;
    
    /**
     * (顧客コード)<BR>
     * 顧客コード<BR>
     */
    public String accountCode;
    
    /**
     * (顧客名)<BR>
     * 顧客名<BR>
     */
    public String accountName;
    
    /**
     * (管理者ID)<BR>
     * 管理者ID<BR>
     */
    public String managerID;
}
@
