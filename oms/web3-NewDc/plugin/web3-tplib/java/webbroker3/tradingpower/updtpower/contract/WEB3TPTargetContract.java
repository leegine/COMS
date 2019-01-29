head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.05.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPTargetContract.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ώی���(WEB3TPTargetContract.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/02 �V���@@�h�O (FLJ) �V�K�쐬
*/
package webbroker3.tradingpower.updtpower.contract;

import webbroker3.tradingpower.util.ToStringUtils;


/**
 * (�Ώی���)
 */
public class WEB3TPTargetContract 
{
    
    /**
     * (�V�K�����σt���O)
     */
    private boolean contractExecutedFlag;
    
    /**
     * (�Ώی��ʏڍ�)
     */
    private WEB3TPTargetContractDetail targetContractDetail;
    
    /**
     * @@roseuid 4104AB470167
     */
    public WEB3TPTargetContract() 
    {
     
    }
    
    /**
     * (create�Ώی���)<BR>
     * �Ώی��ʂ𐶐�����B<BR>
     * @@return webbroker3.tradingpower.updtpower.contract.WEB3TPTargetContract
     * @@roseuid 40DC0649030D
     */
    public static WEB3TPTargetContract create() 
    {
        return new WEB3TPTargetContract();
    }
    
    /**
     * (is�V�K������)<BR>
     * �V�K�����ς����肷��B<BR>
     * @@return boolean
     * @@roseuid 40C966D00150
     */
    public boolean isContractExecuted() 
    {
        return contractExecutedFlag;
    }
    
    /**
     * (set�V�K�����σt���O)<BR>
     * �����̐V�K�����σt���O���Z�b�g����B<BR>
     * @@param l_isContractExecuted - (�V�K�����σt���O)
     * @@roseuid 40C966DD0056
     */
    public void setContractExecuted(boolean l_isContractExecuted) 
    {
        contractExecutedFlag = l_isContractExecuted;     
    }
    
    /**
     * (get�Ώی��ʏڍ�)<BR>
     * �Ώی��ʏڍׂ��擾����B<BR>
     * @@return webbroker3.tradingpower.updtpower.contract.WEB3TPTargetContractDetail
     * @@roseuid 40C964E001CD
     */
    public WEB3TPTargetContractDetail getTargetContractDetail() 
    {
        return targetContractDetail;
    }
    
    /**
     * (set�Ώی��ʏڍ�)<BR>
     * �����̑Ώی��ʏڍׂ��Z�b�g����B<BR>
     * @@param l_targetContractDetail - (�Ώی��ʏڍ�)
     * @@roseuid 40C965460373
     */
    public void setTargetContractDetail(WEB3TPTargetContractDetail l_targetContractDetail) 
    {
        targetContractDetail = l_targetContractDetail;
    }

    /**
     * ���̃I�u�W�F�N�g�̕�����\����Ԃ��B
     */
    public String toString()
    {
        return ToStringUtils
            .newToStringBuilder(this)
            .append("contractExecutedFlag", isContractExecuted())
            .append("targetContractDetail", getTargetContractDetail())
            .toString();
    }
}
@
