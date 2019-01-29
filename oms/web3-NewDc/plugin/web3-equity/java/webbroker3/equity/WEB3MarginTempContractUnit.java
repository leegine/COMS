head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginTempContractUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  (信用取引建株明細Temp)<BR>
                 :   信用取引建株明細一時格納クラス(WEB3MarginTempContractUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/04/06 橋本 佳代子 (SRA) 新規作成
*/
package webbroker3.equity;

import webbroker3.equity.message.WEB3MarginContractUnit;
import java.util.Date;


/**
 * （信用取引建株明細Temp）。<BR>
 * <BR>
 * 信用取引建株明細一時格納クラス
 * @@version 1.0
 */
public class WEB3MarginTempContractUnit extends WEB3MarginContractUnit
{
    /**
     * (当初建日)
     */
    public Date firstOpenDate;

    /**
     * (信用取引建株明細Temp)<BR>
     * コンストラクタ
     * @@roseuid 40EB7FD70197
     */
    public WEB3MarginTempContractUnit() 
    {
     
    }
}
@
