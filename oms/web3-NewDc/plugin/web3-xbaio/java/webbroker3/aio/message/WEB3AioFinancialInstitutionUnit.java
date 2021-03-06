head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.58.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioFinancialInstitutionUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 振込先金融機@関明細(WEB3AioFinancialInstitutionUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 黄建 (中訊) 新規作成
                   2004/10/25 周勇(中訊) レビュー
*/

package webbroker3.aio.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (振込先金融機@関明細)<BR>
 * 振込先金融機@関明細クラス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0 
 */
public class WEB3AioFinancialInstitutionUnit extends Message
{
    
    /**
     * (振込先金融機@関コード)<BR>
     * 振込先金融機@関のコード
     */
    public String finInstitutionCode;
    
    /**
     * (振込先金融機@関名称)<BR>
     * 振込先金融機@関の名称
     */
    public String finInstitutionName;
    
    /**
     * (振込先金融機@関明細)<BR>
     * コンストラクタ
     * @@return webbroker3.aio.message.WEB3AioFinancialInstitutionUnit
     * @@roseuid 40E2575103C1
     */
    public WEB3AioFinancialInstitutionUnit() 
    {
     
    }
    
    public boolean equals(Object obj)
    {
        if(this == obj)
        {
            return true;
        }       
        if(obj instanceof WEB3AioFinancialInstitutionUnit)
        {
            WEB3AioFinancialInstitutionUnit l_aioFinancialInstitutionUnit = (WEB3AioFinancialInstitutionUnit)obj;
            if(finInstitutionCode.equals(l_aioFinancialInstitutionUnit.finInstitutionCode) 
               && finInstitutionName.equals(l_aioFinancialInstitutionUnit.finInstitutionName))
            {
                return true;
            }
        }
        return false;
    }
}
@
