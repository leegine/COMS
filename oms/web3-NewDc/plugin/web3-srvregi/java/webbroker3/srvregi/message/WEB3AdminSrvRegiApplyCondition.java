head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.36.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiApplyCondition.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@//ソース ファ@イル: D:\\3.0.サービス利用\\Source\\webbroker3\\srvregi\\message\\WEB3AdminSrvRegiApplyCondition.java

package webbroker3.srvregi.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (サービス利用申込条件)<BR>
 * サービス利用条件データクラス<BR>
 */
public class WEB3AdminSrvRegiApplyCondition extends Message 
{

    /**
     * (申込条件有効区分)<BR>
     * 0:無効　@1:有効<BR>
     */
    public String conditionDiv;
    
    /**
     * (申込条件コード)
     */
    public String conditionCode;
    
    /**
     * (申込条件名称)
     */
    public String conditionName;
    
    /**
     * (申込条件値)
     */
    public String conditionValue;
    
    /**
     * (申込条件値有無)<BR>
     * 0：無　@1：有<BR>
     */
    public String conditionExist;
    
    /**
     * (サービス利用申込条件)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40FFA3C40055
     */
    public WEB3AdminSrvRegiApplyCondition() 
    {
     
    }
}
@
