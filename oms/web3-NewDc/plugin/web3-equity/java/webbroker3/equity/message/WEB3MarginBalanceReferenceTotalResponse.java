head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginBalanceReferenceTotalResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p����c�����v���X�|���X(WEB3MarginBalanceReferenceTotalResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/07 �򑺁@@�m�m(SRA) �V�K�쐬
*/

package webbroker3.equity.message;

import webbroker3.common.message.WEB3GenResponse;


/**
 * �i�M�p����c���Ɖ�c�����v���X�|���X�j�B<BR>
 * <BR>
 * �M�p����c���Ɖ�c�����v���X�|���X�N���X<BR>
 */
public class WEB3MarginBalanceReferenceTotalResponse extends WEB3GenResponse
{
            
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200502071000L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "margin_balance_reference_total";
    
    /**
     * (���������z)<BR>
     *<BR>
     * ���������z<BR>
     */
    public String buyTotalPrice;
    
    /**
     * (���������z)<BR>
     *<BR>
     * ���������z<BR>
     */
    public String sellTotalPrice;
    
    /**
     * (��������������z)<BR>
     *<BR>
     * ��������������z<BR>
     */
    public String capitalGainTotalPrice;
    
    /**
     * (��ʌ����������z)<BR>
     *<BR>
     * ��ʌ����������z<BR>
     */
    public String normalAccountTotalPrice;
    
    /**
     * (�������z���v)<BR>
     *<BR>
     * �������z���v<BR>
     */
    public String totalPrice;
    
    /**
     * (�������]�����v���v)<BR>
     *<BR>
     * �������]�����v���v<BR>
     */
    public String buyTotalAssetProfitLoss;
    
    /**
     * (�������]�����v���v)<BR>
     *<BR>
     * �������]�����v���v<BR>
     */
    public String sellTotalAssetProfitLoss;
    
    /**
     * (��������]�����v���v)<BR>
     *<BR>
     * ��������]�����v���v<BR>
     */
    public String capitalGainTotalAssetProfitLoss;
    
    /**
     * (��ʌ����]�����v���v)<BR>
     *<BR>
     * ��ʌ����]�����v���v<BR>
     */
    public String normalAccountTotalAssetProfitLoss;
    
    /**
     * (�����]�����v���v)<BR>
     *<BR>
     * �����]�����v���v<BR>
     */
    public String totalAssetProfitLoss;
    
    /**
     * (�������]�����v���v�i���o��l���j)<BR>
     *<BR>
     * �������]�����v���v�i���o��l���j<BR>
     */
    public String buyTotalAssetProfitLossCost;
    
    /**
     * (�������]�����v���v�i���o��l���j)<BR>
     *<BR>
     * �������]�����v���v�i���o��l���j<BR>
     */
    public String sellTotalAssetProfitLossCost;
    
    /**
     * (��������]�����v���v�i���o��l���j)<BR>
     *<BR>
     * ��������]�����v���v�i���o��l���j<BR>
     */
    public String capitalGainTotalAssetProfitLossCost;
    
    /**
     * (��ʌ����]�����v���v�i���o��l���j)<BR>
     *<BR>
     * ��ʌ����]�����v���v�i���o��l���j<BR>
     */
    public String normalAccountTotalAssetProfitLossCost;
    
    /**
     * (�����]�����v���v�i���o��l���j)<BR>
     *<BR>
     * �����]�����v���v�i���o��l���j<BR>
     */
    public String totalAssetProfitLossCost;
    
    /**
     * @@roseuid 4206CDBB0182<BR>
     */
    public WEB3MarginBalanceReferenceTotalResponse() 
    {
     
    }
    
    public WEB3MarginBalanceReferenceTotalResponse(WEB3MarginBalanceReferenceTotalRequest l_request)
    {
        super(l_request);
    }
}
@
