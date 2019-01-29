head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MstkBalanceReferenceTotalResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����~�j�����c�����v���X�|���X(WEB3MstkBalanceReferenceTotalResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/07 �򑺁@@�m�m(SRA) �V�K�쐬
*/

package webbroker3.equity.message;

import webbroker3.common.message.WEB3GenResponse;


/**
 * �i�����~�j�����c���Ɖ�c�����v���X�|���X�j�B<BR>
 * <BR>
 * �����~�j�����c���Ɖ�c�����v���X�|���X�N���X<BR>
 */
public class WEB3MstkBalanceReferenceTotalResponse extends WEB3GenResponse 
{
        
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200502071000L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "mstk_balance_reference_total";

    /**
     * (��������]���z���v)<BR>
     * <BR>
     * ��������]���z���v<BR>
     */
    public String capitalGainTotalAsset;
    
    /**
     * (��������]�����v���v)<BR>
     * <BR>
     * ��������]�����v���v<BR>
     */
    public String capitalGainTotalAppraisalProfitLoss;
    
    /**
     * (��ʌ����]���z���v)<BR>
     * <BR>
     * ��ʌ����]���z���v<BR>
     */
    public String normalAccountTotalAsset;
    
    /**
     * (��ʌ����]�����v���v)<BR>
     * <BR>
     * ��ʌ����]�����v���v<BR>
     */
    public String normalAccountTotalAppraisalProfitLoss;
    
    /**
     * @@roseuid 4206CC9902CB<BR>
     */
    public WEB3MstkBalanceReferenceTotalResponse() 
    {
     
    }
    
    public WEB3MstkBalanceReferenceTotalResponse(WEB3MstkBalanceReferenceTotalRequest l_request)
    {
        super(l_request);
    }
}
@
