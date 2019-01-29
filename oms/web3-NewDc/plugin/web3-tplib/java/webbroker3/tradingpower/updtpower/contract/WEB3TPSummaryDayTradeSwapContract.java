head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.04.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPSummaryDayTradeSwapContract.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���v��ԍρE�������n���ʂ̏W�v(WEB3TPSummaryDayTradeSwapContract.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/02 �V���@@�h�O (FLJ) �V�K�쐬
Revesion History : 2008/01/31 �g�E�N�|�@@(���u)�@@�d�l�ύX�@@���f��No.257
*/
package webbroker3.tradingpower.updtpower.contract;

import webbroker3.tradingpower.util.ToStringUtils;


/**
 * (���v��ԍρE�������n���ʂ̏W�v)
 */
public class WEB3TPSummaryDayTradeSwapContract extends WEB3TPContractBase 
{
    
    /**
     * (�������n���ʕ]����)
     */
    private double swapContractAssetLoss;
    
    /**
     * (�������n���ʕ]���v)
     */
    private double swapContractAssetProfit;

    /**
     * (�������n���ʌ��ϑ�)
     */
    private double swapContractSettleLoss;

    /**
     * @@roseuid 4104AE400177
     */
    public WEB3TPSummaryDayTradeSwapContract() 
    {
     
    }
    
    /**
     * (create���v��ԍρE�������n���ʂ̏W�v)
     * @@return webbroker3.tradingpower.updtpower.contract.WEB3TPSummaryDayTradeSwapContract
     * @@roseuid 4100F15A0157
     */
    public static WEB3TPSummaryDayTradeSwapContract create() 
    {
        return new WEB3TPSummaryDayTradeSwapContract();
    }
    
    /**
     * (get�������n���ʕ]����) <BR>
     * <BR>
     * this.�������n���ʕ]������ԋp����B <BR>
     * <BR>
     * @@return double
     */
    public double getSwapContractAssetLoss()
    {
        return swapContractAssetLoss;
    }

    /**
     * (set�������n���ʕ]����) <BR>
     * <BR>
     * ����.�������n���ʕ]�������A <BR>
     * this.�������n���ʕ]�����ɃZ�b�g����B <BR>
     * <BR>
     * @@param l_dblSwapContractAssetLoss - (�������n���ʕ]����)
     */
    public void setSwapContractAssetLoss(double l_dblSwapContractAssetLoss)
    {
        this.swapContractAssetLoss = l_dblSwapContractAssetLoss;
    }

    /**
     * (get�������n���ʕ]���v) <BR>
     * <BR>
     * this.�������n���ʕ]���v��ԋp����B <BR>
     * <BR>
     * @@return double
     */
    public double getSwapContractAssetProfit()
    {
        return swapContractAssetProfit;
    }

    /**
     * (set�������n���ʕ]���v) <BR>
     * <BR>
     * ����.�������n���ʕ]���v���A<BR> 
     * this.�������n���ʕ]���v�ɃZ�b�g����B <BR>
     * <BR>
     * @@param l_dblSwapContractAssetProfit - (�������n���ʕ]���v)
     */
    public void setSwapContractAssetProfit(double l_dblSwapContractAssetProfit)
    {
        this.swapContractAssetProfit = l_dblSwapContractAssetProfit;
    }

    /**
     * (get�������n���ʌ��ϑ�)<BR>
     * this.�������n���ʌ��ϑ���ԋp����B<BR>
     * @@return double
     */
    public double getSwapContractSettleLoss()
    {
        return swapContractSettleLoss;
    }

    /**
     * (set�������n���ʌ��ϑ�)<BR>
     * ����.�������n���ʌ��ϑ����Athis.�������n���ʌ��ϑ��ɃZ�b�g����B<BR>
     * @@param l_dblTradeSwapContractLoss - (�������n���ʌ��ϑ�)
     */
    public void setSwapContractSettleLoss(double l_dblSwapContractSettleLoss)
    {
        this.swapContractSettleLoss = l_dblSwapContractSettleLoss;
    }

    /**
     * ���̃I�u�W�F�N�g�̕�����\����Ԃ��B
     */
    public String toString() 
    {
        return ToStringUtils
            .newToStringBuilder(this)
            .appendSuper(super.toString())
            .append("swapContractAssetLoss", getSwapContractAssetLoss())
            .append("swapContractAssetProfit", getSwapContractAssetProfit())
            .toString();
    }
}
@
