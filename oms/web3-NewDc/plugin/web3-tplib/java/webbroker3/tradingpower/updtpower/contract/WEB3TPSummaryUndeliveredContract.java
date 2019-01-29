head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.05.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPSummaryUndeliveredContract.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ����n���ʂ̏W�v(WEB3TPSummaryUndeliveredContract.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/02 �V���@@�h�O (FLJ) �V�K�쐬
*/
package webbroker3.tradingpower.updtpower.contract;

import webbroker3.tradingpower.util.ToStringUtils;


/**
 * (����n���ʂ̏W�v)
 */
public class WEB3TPSummaryUndeliveredContract extends WEB3TPContractBase 
{
    
    /**
     * (���ωv)
     */
    private double contractProfit;
    
    /**
     * (���ϑ�)
     */
    private double contractLoss;
    
    /**
     * (���ωv<����>)
     */
    private double todayRepayContractProfit;
    
    /**
     * (���ϑ�<����>)
     */
    private double todayRepayContractLoss;
    
    /**
     * (���ό��ʑO�����i�]��<����>)
     */
    private double todayRepayContractPrevAsset;
    
    /**
     * (���ωv<�w���>)
     */
    private double designateDateContractProfit;
    
    /**
     * (���ϑ�<�w���>)
     */
    private double designateDateContractLoss;
    
    /**
     * @@roseuid 4104AE3F0261
     */
    public WEB3TPSummaryUndeliveredContract() 
    {
     
    }
    
    /**
     * (create����n���ʂ̏W�v)
     * @@return webbroker3.tradingpower.updtpower.contract.WEB3TPSummaryUndeliveredContract
     * @@roseuid 4100F12701F3
     */
    public static WEB3TPSummaryUndeliveredContract create() 
    {
        return new WEB3TPSummaryUndeliveredContract();
    }
    
    /**
     * (get���ωv)<BR>
     * ���ωv���擾����B<BR>
     * @@return double
     * @@roseuid 40BABB6C0334
     */
    public double getContractProfit() 
    {
        return contractProfit;
    }
    
    /**
     * (set���ωv)<BR>
     * �����̌��ωv���Z�b�g����B<BR>
     * @@param l_dblContractProfit - (���ωv)
     * @@roseuid 40BABB6D00F1
     */
    public void setContractProfit(double l_dblContractProfit) 
    {
        contractProfit = l_dblContractProfit;
    }
    
    /**
     * (get���ϑ�)<BR>
     * ���ϑ����擾����B<BR>
     * @@return double
     * @@roseuid 40BABB6C0372
     */
    public double getContractLoss() 
    {
         return contractLoss;
    }
    
    /**
     * (set���ϑ�)<BR>
     * �����̌��ϑ����Z�b�g����B<BR>
     * @@param l_dblContractLoss - (���ϑ�)
     * @@roseuid 40BABB6D0140
     */
    public void setContractLoss(double l_dblContractLoss) 
    {
        contractLoss = l_dblContractLoss;
    }
    
    /**
     * (get���ωv<����>)<BR>
     * ���ωv<����>���擾����B<BR>
     * @@return double
     */
    public double getTodayRepayContractProfit() 
    {
         return todayRepayContractProfit;
    }
    
    /**
     * (set���ωv<����>)<BR>
     * �����̌��ωv<����>���Z�b�g����B<BR>
     * @@param l_dblTodayRepayContractProfit - (���ωv<����>)
     */
    public void setTodayRepayContractProfit(double l_dblTodayRepayContractProfit) 
    {
        todayRepayContractProfit = l_dblTodayRepayContractProfit;
    }
    
    /**
     * (get���ϑ�<����>)<BR>
     * ���ϑ�<����>���擾����B<BR>
     * @@return double
     */
    public double getTodayRepayContractLoss() 
    {
         return todayRepayContractLoss;
    }
    
    /**
     * (set���ϑ�<����>)<BR>
     * �����̌��ϑ�<����>���Z�b�g����B<BR>
     * @@param l_dblTodayRepayContractLoss - (���ϑ�<����>)
     */
    public void setTodayRepayContractLoss(double l_dblTodayRepayContractLoss) 
    {
        todayRepayContractLoss = l_dblTodayRepayContractLoss;
    }
    
    /**
     * (get���ό��ʑO�����i�]��<����>)<BR>
     * ���ό��ʑO�����i�]��<����>���擾����B<BR>
     * @@return double
     */
    public double getTodayRepayContractPrevAsset() 
    {
         return todayRepayContractPrevAsset;
    }
    
    /**
     * (set���ό��ʑO�����i�]��<����>)<BR>
     * �����̌��ό��ʑO�����i�]��<����>���Z�b�g����B<BR>
     * @@param l_dblTodayRepayContractPrevAsset - (���ό��ʑO�����i�]��<����>)
     */
    public void setTodayRepayContractPrevAsset(double l_dblTodayRepayContractPrevAsset) 
    {
        todayRepayContractPrevAsset = l_dblTodayRepayContractPrevAsset;
    }
    
    /**
     * (get���ωv<�w���>)<BR>
     * ���ωv<�w���>���擾����B<BR>
     * @@return double
     */
    public double getDesignateDateContractProfit() 
    {
         return designateDateContractProfit;
    }
    
    /**
     * (set���ωv<�w���>)<BR>
     * �����̌��ωv<�w���>���Z�b�g����B<BR>
     * @@param l_dblDesignateDateContractProfit - (���ωv<�w���>)
     */
    public void setDesignateDateContractProfit(double l_dblDesignateDateContractProfit) 
    {
        designateDateContractProfit = l_dblDesignateDateContractProfit;
    }
    
    /**
     * (get���ϑ�<�w���>)<BR>
     * ���ϑ�<�w���>���擾����B<BR>
     * @@return double
     */
    public double getDesignateDateContractLoss() 
    {
         return designateDateContractLoss;
    }
    
    /**
     * (set���ϑ�<�w���>)<BR>
     * �����̌��ϑ�<�w���>���Z�b�g����B<BR>
     * @@param l_dblDesignateDateContractLoss - (���ϑ�<�w���>)
     */
    public void setDesignateDateContractLoss(double l_dblDesignateDateContractLoss) 
    {
        designateDateContractLoss = l_dblDesignateDateContractLoss;
    }
    
    
    /**
     * ���̃I�u�W�F�N�g�̕�����\����Ԃ��B
     */
    public String toString() 
    {
        return ToStringUtils
            .newToStringBuilder(this)
            .appendSuper(super.toString())
            .append("contractProfit", getContractProfit())
            .append("contractLoss", getContractLoss())
            .append("todayRepayContractProfit", getTodayRepayContractProfit())
            .append("todayRepayContractLoss", getTodayRepayContractLoss())
            .append("todayRepayContractPreviousAsset", getTodayRepayContractPrevAsset())
            .append("designateDateContractProfit", getDesignateDateContractProfit())
            .append("designateDateContractLoss", getDesignateDateContractLoss())
            .toString();
    }
}
@
