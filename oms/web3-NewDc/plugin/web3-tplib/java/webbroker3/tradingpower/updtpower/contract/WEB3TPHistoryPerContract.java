head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.05.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPHistoryPerContract.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���ʂ��ƕϓ����(WEB3TPHistoryPerContract.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/02 �V���@@�h�O (FLJ) �V�K�쐬
Revesion History : 2007/07/28 �Ј��� (���u)���f��No.116 No.138
Revesion History : 2008/02/01 �����Q(���u) ���f��No.259
Revesion History : 2008/10/21 ������(���u) ���f��No.327
Revesion History : 2008/10/31 ������(���u) ���f��No.351
Revesion History : 2008/11/04 ������(���u) ���f��No.356
Revesion History : 2008/11/07 �И���(���u) ���f��No.364
Revesion History : 2008/11/14 �И���(���u) ���f��No.367
Revesion History : 2009/12/03 �����F(���u) ���f��No.401 No.402 No.403
*/
package webbroker3.tradingpower.updtpower.contract;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.define.WEB3TPContractAmountApplyDateDef;
import webbroker3.tradingpower.define.WEB3TPRestraintDivDef;
import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.tradingpower.util.ToStringBuilder;
import webbroker3.tradingpower.util.ToStringUtils;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionCateg;

/**
 * (���ʂ��ƕϓ����)
 */
public class WEB3TPHistoryPerContract 
{
    
    /**
     * (���O�o�̓��[�e�B���e�B)
     */
     private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3TPHistoryPerContract.class);
     
     /**
      * (�f�o�b�Oison)
      */
     private static boolean DBG = log.ison();

    /**
     * (���ʕϓ�)
     */
    private List histories;
    
    /**
     * (�Ώی���)
     */
    private WEB3TPTargetContract targetContract;
    
    /**
     * (���ʏ��)
     */
    private WEB3TPContractInfo contractInfo;
    
    /**
     * (�ۏ؋����̕���)
     */
    private static final double depositDenominator = 100;
    
    /**
     * (���v�Z���̌덷�␳�l)
     */
    private static final double errorCorrection = 0.000001;
    
    /**
     * @@roseuid 4104AB4702FD
     */
    public WEB3TPHistoryPerContract() 
    {
        histories = new ArrayList();
    }
    
    /**
     * (create���ʂ��ƕϓ����)<BR>
     * ���ʂ��ƕϓ����𐶐�����B<BR>
     * @@param l_contractInfo - (���ʏ��)
     * @@param l_targetContract - (�Ώی���)
     * @@return webbroker3.tradingpower.updtpower.contract.WEB3TPHistoryPerContract
     * @@roseuid 40DC087E02EE
     */
    public static WEB3TPHistoryPerContract create(WEB3TPContractInfo l_contractInfo, WEB3TPTargetContract l_targetContract) 
    {
        WEB3TPHistoryPerContract l_thisInstance = new WEB3TPHistoryPerContract();
        l_thisInstance.setContractInfo(l_contractInfo);
        l_thisInstance.setTargetContract(l_targetContract);
        return l_thisInstance;
    }
    
    /**
     * (get���ʕϓ�)<BR>
     * ���ʕϓ����擾����B<BR>
     * @@return java.util.List
     * @@roseuid 40F3B85B0188
     */
    public List getHistories() 
    {
        return histories;
    }
    
    /**
     * (get�Ώی���)<BR>
     * �Ώی��ʂ��擾����B<BR>
     * @@return webbroker3.tradingpower.updtpower.contract.WEB3TPTargetContract
     * @@roseuid 40DFB452037A
     */
    public WEB3TPTargetContract getTargetContract() 
    {
        return targetContract;
    }
    
    /**
     * (set�Ώی���)<BR>
     * �����̑Ώی��ʂ��Z�b�g����B<BR>
     * @@param l_targetContract - (�Ώی���)
     * @@roseuid 40DFB4520399
     */
    public void setTargetContract(WEB3TPTargetContract l_targetContract) 
    {
        targetContract = l_targetContract;
    }
    
    /**
     * (get���ʏ��)<BR>
     * ���ʏ����擾����B<BR>
     * @@return webbroker3.tradingpower.updtpower.contract.WEB3TPContractInfo
     * @@roseuid 
     */
    public WEB3TPContractInfo getContractInfo() 
    {
        return contractInfo;
    }
    
    /**
     * (set���ʏ��)<BR>
     * �����̌��ʏ����Z�b�g����B<BR>
     * @@param l_contractInfo - (���ʏ��)
     * @@roseuid 
     */
    public void setContractInfo(WEB3TPContractInfo l_contractInfo) 
    {
        contractInfo = l_contractInfo;
    }
    
    /**
     * (get�����ό��ʂ̏W�v)<BR>
     * �����Ŏw�肵�����̖����ό��ʂ̏W�v������B<BR>
     * <BR>
     * �P�j�����������擾����B <BR>
     * �@@�@@�����ό��������Ώی��ʏڍ�.�������� <BR>
     * <BR>
     * �Q�j���ʕϓ��ꗗ���擾����B <BR>
     * <BR>
     * �R�j�����ό��ʂ̌���������e�W�v���ڂ𓱂��o���B <BR>
     * �@@�|�W�v���ځF <BR>
     * �@@�@@�E���ʑ�� <BR>
     * �@@�@@�E�K�v�ۏ؋� <BR>
     * �@@�@@�E�����K�v�ۏ؋� <BR>
     * �@@�@@�E���������ʑ�� <BR>
     * �@@�@@�E�������K�v�ۏ؋� <BR>
     * �@@�@@�E�����������K�v�ۏ؋� <BR>
     * �@@�@@�E�����ό��ʕ]���� <BR>
     * �@@�@@�E�����ό��ʕ]���v <BR>
     * �@@�@@�E���萔�� <BR>
     * �@@�@@�E�����E�t������ <BR>
     * �@@�@@�E�����E�t�����v <BR>
     * �@@�@@�E���̑����ʏ��o�� <BR>
     * <BR>
     * �@@�y�O������z <BR>
     * �@@�E�������ʑ���v��J�n������擾����B <BR>
     * �@@�@@�m�擾���@@�n <BR>
     * �@@�@@�@@�E�]�͌v�Z����().get��Е��X�ʗ]�͌v�Z�����i"contractamount.apply.date"�F�������ʑ���v��J�n���j�̖߂�l���A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@1�FFROM_BIZ_DATE�̏ꍇ<BR> 
     * �@@�@@�@@�@@�E.�������ʑ���v��J�n�����0���Z�b�g����B <BR>
     * <BR>
     * �@@�@@�@@�E�]�͌v�Z����().get��Е��X�ʗ]�͌v�Z�����i"contractamount.apply.date"�F�������ʑ���v��J�n���j�̖߂�l���A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@2�FFROM_T2�̏ꍇ <BR>
     * �@@�@@�@@�@@�E�������ʑ���v��J�n�����2���Z�b�g����B <BR>
     * <BR>
     * �@@�@@�@@�E�ȊO�̏ꍇ <BR>
     * �@@�@@�@@�@@�E�������ʑ���v��J�n�����1���Z�b�g����B <BR>
     * <BR>
     * �@@�E�Ώی��ʏڍ�.���������]�͌v�Z����.�c�Ɠ�(T+0) - �������ʑ���v��J�n����̏ꍇ <BR>
     * �@@�@@�ϓ����f�J�n���Ɍ��� + �������ʑ���v��J�n������Z�b�g����B <BR>
     * �@@�E�Ώی��ʏڍ�.�������]�͌v�Z����.�c�Ɠ�(T+0) - �������ʑ���v��J�n����̏ꍇ <BR>
     * �@@�@@�ϓ����f�J�n�����]�͌v�Z����.�c�Ɠ�(T+0) <BR>
     * �@@�E( �ϓ����f�J�n������n ) ���� ( n�����]�͌v�Z����.�c�Ɠ�[T+5] ) �̏ꍇ<BR> 
     * <BR>
     * �@@�@@�@@�E�Ώی���.is���ρ�true �̏ꍇ�A���A�������� > 0�̏ꍇ�ȉ��̏��������s����B <BR>
     * <BR>
     * �@@�@@�@@�@@�|�����ό��ʂ̌������̋��ߕ� <BR>
     * �@@�@@�@@�@@�@@�|�Ώی���.�V�K�����ρ�true ���� ���ʕϓ�.�g�����U�N�V�����J�e�S�����V�K���@@�̏ꍇ <BR>
     * �@@�@@�@@�@@�@@�@@�����ό������������ό������|���ʕϓ�.������ <BR>
     * �@@�@@�@@�@@�@@�@@�����������̏ꍇ�A����茻�����n�̎��������ʕϓ�.���������}�C�i�X����B <BR>
     * <BR>
     * �@@�@@�@@�@@�|���萔���̋��ߕ� <BR>
     * �@@�@@�@@�@@�@@�E���萔��(n)���Ώی��ʏڍ�.���萔���@@�@@�@@�@@�@@�@@�@@�~�@@�����ό������@@/�@@�Ώی��ʏڍ�.�������� <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�{�@@�Ώی��ʏڍ�.���萔������Ł@@�@@�~�@@�����ό������@@/�@@�Ώی��ʏڍ�.�������� <BR>
     * <BR>
     * �@@�@@�@@�@@�|���̑����ʏ��o��̋��ߕ� <BR>
     * �@@�@@�@@�@@�@@�E���̑����ʏ��o��(n)���Ώی��ʏڍ�.���`�������@@�@@�@@�@@�@@�@@�@@�~�@@�����ό������@@/�@@�Ώی��ʏڍ�.�������� <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�{�@@�Ώی��ʏڍ�.���`����������Ł@@�@@�~�@@�����ό������@@/�@@�Ώی��ʏڍ�.�������� <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�{�@@�Ώی��ʏڍ�.�Ǘ���@@�@@�@@�@@�@@�@@�@@�~�@@�����ό������@@/�@@�Ώی��ʏڍ�.�������� <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�{�@@�Ώی��ʏڍ�.�Ǘ������Ł@@�@@�@@�@@�~�@@�����ό������@@/�@@�Ώی��ʏڍ�.�������� <BR>
     * <BR>
     * �@@�@@�@@�@@�|�������E�t�������v�A�����ό��ʕ]�����v�̋��ߕ� <BR>
     * �@@�@@�@@�@@�@@�E�Ώی��ʏڍ�.���敪�������@@�̏ꍇ <BR>
     * �@@�@@�@@�@@�@@�@@�������E�t������(n)���Ώی��ʏڍ�.�������@@�~�@@�����ό������@@/�@@�Ώی��ʏڍ�.�������� <BR>
     * �@@�@@�@@�@@�@@�@@�������E�t�����v(n)���Ώی��ʏڍ�.�t�����@@�~�@@�����ό������@@/�@@�Ώی��ʏڍ�.�������� <BR>
     * �@@�@@�@@�@@�@@�@@�E�Ώی��ʏڍ�.�]���P����0�@@�̏ꍇ <BR>
     * �@@�@@�@@�@@�@@�@@�@@�����ό��ʕ]�����v(n)���Ώی��ʏڍ�.�]���P���@@�~�@@�����ό������@@-�@@�Ώی��ʏڍ�.���P���@@�~�@@�����ό�����<BR>
     * �@@�@@�@@�@@�@@�E�Ώی��ʏڍ�.���敪�������@@�̏ꍇ <BR>
     * �@@�@@�@@�@@�@@�@@�������E�t������(n)���Ώی��ʏڍ�.�t�����@@�~�@@�����ό������@@/�@@�Ώی��ʏڍ�.�������� <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@ �{�@@�Ώی��ʏڍ�.�݊����@@�@@�@@ �~�@@�����ό������@@/�@@�Ώی��ʏڍ�.�������� <BR>
     * �@@�@@�@@�@@�@@�@@�������E�t�����v(n)���Ώی��ʏڍ�.�������@@�~�@@�����ό������@@/�@@�Ώی��ʏڍ�.�������� <BR>
     * �@@�@@�@@�@@�@@�@@�E�Ώی��ʏڍ�.�]���P����0�@@�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@�@@�����ό��ʕ]�����v(n)���Ώی��ʏڍ�.���P���@@�~�@@�����ό������@@-�@@�Ώی��ʏڍ�.�]���P�� �~�@@�����ό�����<BR>
     * �@@�@@�@@�@@�@@�E�����ό��ʕ]�����v(n)��0�@@�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@�@@�����ό��ʕ]����(n)��ABS(�����ό��ʕ]�����v(n))<BR>
     * �@@�@@�@@�@@�@@�E�����ό��ʕ]�����v(n)����0�@@�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@�@@�����ό��ʕ]���v(n)�������ό��ʕ]�����v(n)<BR>
     * <BR>
     * �@@�@@�|���ʑ���^�K�v�ۏ؋��^�����K�v�ۏ؋��^���������ʑ���^�������K�v�ۏ؋��^�����������K�v�ۏ؋��̋��ߕ�<BR>
     * �@@�@@�@@�E�Ώی���.is���ρ�true �̏ꍇ<BR>
     * �@@�@@�@@�@@���ʑ��(n)�@@�@@�@@�@@�@@�@@�@@���Ώی��ʏڍ�.���P��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�~�@@�����ό�����<BR>
     * �@@�@@�@@�@@�K�v�ۏ؋�(n)�@@�@@�@@�@@�@@�@@�����ʑ��(n)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�~�@@�Ώی��ʏڍ�.�ۏ؋����@@/�@@100<BR>
     * �@@�@@�@@�@@�����K�v�ۏ؋�(n)�@@�@@�@@�@@�����ʑ��(n)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�~�@@�Ώی��ʏڍ�.�����ۏ؋����@@/�@@100<BR>
     * �@@�@@�@@�E�Ώی���.is���ρ�false �̏ꍇ<BR>
     * �@@�@@�@@�@@���������ʑ��(n)�@@�@@�@@�@@���Ώی��ʏڍ�.���P��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�~�@@�����ό�����<BR>
     * �@@�@@�@@�@@�������K�v�ۏ؋�(n)�@@�@@�@@�����ʑ��(n)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�~�@@�Ώی��ʏڍ�.�ۏ؋����@@/�@@100<BR>
     * �@@�@@�@@�@@�����������K�v�ۏ؋�(n)�@@�����ʑ��(n)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�~�@@�Ώی��ʏڍ�.�����ۏ؋����@@/�@@100<BR>
     * <BR>
     * �S�j�����ό��ʂ̏W�v���ʂ�Ԃ��B <BR>
     * <BR>
     * ��n�́A�����̎w����B<BR>
     * ���v�Z���Ɏg�p����e�l�̎擾���@@<BR>
     * �E�Ώی��ʏڍ�.���������@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�E�E�E�Ώی��ʏڍ�.get��������()<BR>
     * �E�Ώی��ʏڍ�.�����ό��ʕ]�����v�@@�@@�@@�@@�@@�E�E�E�Ώی��ʏڍ�.get�����ό��ʕ]�����v()<BR>
     * �E�Ώی��ʏڍ�.���敪�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�E�E�E�Ώی��ʏڍ�.get���敪()<BR>
     * �E�Ώی��ʏڍ�.�������@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�E�E�E�Ώی��ʏڍ�.get������()<BR>
     * �E�Ώی��ʏڍ�.�t�������@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�E�E�E�Ώی��ʏڍ�.get�t����()<BR>
     * �E�Ώی��ʏڍ�.���萔���@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�E�E�E�Ώی��ʏڍ�.get���萔��()<BR>
     * �E�Ώی��ʏڍ�.���萔������Ł@@�@@�@@�@@�@@�@@�@@�E�E�E�Ώی��ʏڍ�.get���萔�������()<BR>
     * �E�Ώی��ʏڍ�.���`�������@@�@@�@@�@@�@@�@@�@@�@@�@@�E�E�E�Ώی��ʏڍ�.get���`������()<BR>
     * �E�Ώی��ʏڍ�.���`����������Ł@@�@@�@@�@@�@@�@@�E�E�E�Ώی��ʏڍ�.get���`�����������()<BR>
     * �E�Ώی��ʏڍ�.�Ǘ���@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�E�E�E�Ώی��ʏڍ�.get�Ǘ���()<BR>
     * �E�Ώی��ʏڍ�.�Ǘ������Ł@@�@@�@@�@@�@@�@@�@@�@@�E�E�E�Ώی��ʏڍ�.get�Ǘ�������()<BR>
     * �E�Ώی��ʏڍ�.�݊����@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�E�E�E�Ώی��ʏڍ�.get�݊���()<BR>
     * �E�Ώی��ʏڍ�.�����@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�E�E�E�Ώی��ʏڍ�.get����()<BR>
     * �E�Ώی��ʏڍ�.���P���@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�E�E�E�Ώی��ʏڍ�.get���P��()<BR>
     * �E�Ώی��ʏڍ�.�ۏ؋����@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�E�E�E�Ώی��ʏڍ�.get�ۏ؋���()<BR>
     * �E�Ώی��ʏڍ�.�����ۏ؋����@@�@@�@@�@@�@@�@@�@@�@@�E�E�E�Ώی��ʏڍ�.get�����ۏ؋���()<BR>
     * �E�Ώی��ʏڍ�.�]���P���@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�E�E�E�Ώی��ʏڍ�.get�]���P��()<BR>
     * �E�]�͌v�Z����.�c�Ɠ�(T+0)�@@�@@�@@�@@�@@�@@�@@�@@ �E�E�E�]�͌v�Z����.get�c�Ɠ�(T+0)<BR>
     * �E���ʕϓ�.�������@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�E�E�E���ʕϓ�.get������()<BR>
     * �E�ۏ؋����@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@ �E�E�E�Ώی��ʏڍ�.get�ۏ؋���()<BR>
     * �E�����ۏ؋����@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@ �E�E�E�Ώی��ʏڍ�.get�����ۏ؋���()<BR>
     * @@param l_datDate - (�w���)
     * @@return webbroker3.tradingpower.updtpower.contract.WEB3TPSummaryOpenContract
     * @@roseuid 40EB66BC014A
     */
    public WEB3TPSummaryOpenContract getSummaryOpenContract(Date l_datDate) 
    {        
        //create�����ό��ʂ̏W�v      
        WEB3TPSummaryOpenContract l_sumOpenContract = WEB3TPSummaryOpenContract.create();
            
        //�Ώی��ʂ��擾
        WEB3TPTargetContract l_targetContract = getTargetContract();
            
        //�Ώی��ʏڍׂ��擾
        WEB3TPTargetContractDetail l_targetContractDetail = l_targetContract.getTargetContractDetail();

        //�]�͌v�Z�������擾
        WEB3TPCalcCondition l_calcCondition = getContractInfo().getCalcCondition();
    
        //�c�Ɠ�(T+0)���擾
        Date l_datBizDate0 = l_calcCondition.getBizDate(WEB3TPSpecifiedPointDef.T_0);
    
        //�������擾
        Date l_datOpenDate = l_targetContractDetail.getOpenDate();
        
        //�������ʌv��J�n���̐ݒ�l���擾
        String l_strContractApplyDate = l_calcCondition.getInstBranCalcCondition(WEB3TPCalcCondition.CONTRACTAMOUNT_APPLY_DATE);        

        //���������t���O
        boolean isTodaysContract = false;
        
        //����=�c�Ɠ�(T+0)�̏ꍇ
        if(WEB3DateUtility.compareToDay(l_datOpenDate, l_datBizDate0) == 0)
        {
            //���ς݂̏ꍇ
            if(l_targetContract.isContractExecuted())
            {
                isTodaysContract = true;
            }
        }

        //�����������擾
        double l_dblOriginalQuantity = 0.0;

        if(isTodaysContract)
        {
            //��ƂȂ�̂͌�����
            l_dblOriginalQuantity = l_targetContractDetail.getQuantity();
        }
        else
        {
            //��ƂȂ�̂͌�������(�t����̐��l)
            l_dblOriginalQuantity = l_targetContractDetail.getOriginalQuantity();
        }
        

        //���������𖢌��ό������̌v�Z���Ƃ���
        double l_dblOpenContractQuantity = l_dblOriginalQuantity;
        
        //���ʕϓ��ꗗ���擾
        List l_histories = getHistories();
        int l_intHistorySize = l_histories.size();

        //�@@�E�]�͌v�Z����().get��Е��X�ʗ]�͌v�Z�����i
        //    "contractamount.apply.date"�F�������ʑ���v��J�n���j�̖߂�l���A1�FFROM_BIZ_DATE�̏ꍇ 
        //�@@�E.�������ʑ���v��J�n�����0���Z�b�g����B 
        //�@@�E�]�͌v�Z����().get��Е��X�ʗ]�͌v�Z�����i
        //    "contractamount.apply.date"�F�������ʑ���v��J�n���j�̖߂�l���A2�FFROM_T2�̏ꍇ 
        // �E�������ʑ���v��J�n�����2���Z�b�g����B 
        // �E�ȊO�̏ꍇ 
        // �E�������ʑ���v��J�n�����1���Z�b�g����B 
        int l_intContractApplyDate = 1;
        if (WEB3TPContractAmountApplyDateDef.DEFAULT.equals(l_strContractApplyDate))
        {
            l_intContractApplyDate = 1;
        }
        else if (WEB3TPContractAmountApplyDateDef.FROM_BIZ_DATE.equals(l_strContractApplyDate))
        {
            l_intContractApplyDate = 0;
        }
        else if (WEB3TPContractAmountApplyDateDef.FROM_T2.equals(l_strContractApplyDate))
        {
            l_intContractApplyDate = 2;
        }

        //�Ώی��ʏڍ�.���������]�͌v�Z����.�c�Ɠ�(T+0) - �������ʑ���v��J�n����̏ꍇ
        //�ϓ����f�J�n���Ɍ��� + �������ʑ���v��J�n������Z�b�g����B
        //�Ώی��ʏڍ�.�������]�͌v�Z����.�c�Ɠ�(T+0) - �������ʑ���v��J�n����̏ꍇ
        //�ϓ����f�J�n�����]�͌v�Z����.�c�Ɠ�(T+0)
        //�ϓ����f�J�n��
        Date l_datReflectStartDay;
        if ((WEB3DateUtility.compareToDay(l_datOpenDate,
            l_calcCondition.rollBizDate(l_datBizDate0, - l_intContractApplyDate)) >= 0))
        {
            l_datReflectStartDay =
                l_calcCondition.rollBizDate(l_datOpenDate, l_intContractApplyDate);
        }
        else
        {
            l_datReflectStartDay = l_datBizDate0;
        }

        //( �ϓ����f�J�n������n ) ���� ( n�����]�͌v�Z����.�c�Ɠ�[T+5] ) �̏ꍇ 
        //�ϓ����f�I�������c�Ɠ�(T+5)
        Date l_datReflectEndDay = l_calcCondition.getBizDate(WEB3TPSpecifiedPointDef.T_5);

        //�w���
        long l_lngDatDate = l_datDate.getTime();

        //���ԓ��̏ꍇ
        if ((l_datReflectStartDay.getTime() <= l_lngDatDate)
            && (l_lngDatDate <= l_datReflectEndDay.getTime()))
        {
            //�Ώی���.is���ρ�true �̏ꍇ�A���A�������� > 0�̏ꍇ�ȉ��̏��������s����B 
            //�V�K���Ƃ��Ė����̎��͈����s���K�v�Ȃ�(�萔��=0,�]�����v=0)
            if(l_targetContract.isContractExecuted())
            {
                //����������0�ȉ��̎�(�����e�[�u���ɓ�������ɖ������)�͈����s���K�v�Ȃ�(�萔��=0,�]�����v=0)
                if(l_dblOriginalQuantity > 0)
                {
                    //���ʕϓ��ꗗ�̃T�C�Y�Ń��[�v
                    for(int i = 0; i < l_intHistorySize; i++)
                    {
                        //���ʕϓ����擾
                        WEB3TPHistory l_history = (WEB3TPHistory)l_histories.get(i);
                
                        //�g�����U�N�V�����J�e�S���擾
                        FinTransactionCateg l_transCateg = l_history.getTransactionCateg();
                
                        //�ԍρE�������n
                        if(FinTransactionCateg.EQTYPE_CLOSE_MARGIN.equals(l_transCateg)
                            || FinTransactionCateg.EQTYPE_SWAP_MARGIN.equals(l_transCateg))
                        {
                            //���������̏ꍇ�A����茻�����n�����}�C�i�X����
                            if(isTodaysContract)
                            {
                                if(! l_history.isExecuted())
                                {
                                    //�����ό����� -= �ϓ�����
                                    l_dblOpenContractQuantity -= l_history.getQuantity();
                                }
                            }
                            else
                            {
                                //�����ό����� -= �ϓ�����
                                l_dblOpenContractQuantity -= l_history.getQuantity();
                            }
                        }
                    }

                    //�������v�Z
                    double l_dblRate = l_dblOpenContractQuantity / l_dblOriginalQuantity;

                    if(DBG)
                    {
                        StringBuffer l_sbLog = new StringBuffer(" net_open_contract_quantity=");
                        l_sbLog.append(l_dblOpenContractQuantity);
                        l_sbLog.append(" proportional_rate=");
                        l_sbLog.append(l_dblRate);
                        log.debug(l_sbLog.toString());
                    }

                    //���萔��
                    double l_dblSetupFee =
                        Math.floor(l_targetContractDetail.getSetupFee()     * l_dblRate + errorCorrection)
                            + Math.floor(l_targetContractDetail.getSetupFeeTax() * l_dblRate + errorCorrection);

                    //���̑����ʏ��o��
                    double l_dblOtherCost =
                        Math.floor(l_targetContractDetail.getNameTransferFee()     * l_dblRate + errorCorrection)
                            + Math.floor(l_targetContractDetail.getNameTransferFeeTax() * l_dblRate + errorCorrection)
                            + Math.floor(l_targetContractDetail.getManagementFee()      * l_dblRate + errorCorrection)
                            + Math.floor(l_targetContractDetail.getManagementFeeTax()   * l_dblRate + errorCorrection);

                    //�������E�t������
                    double l_dblInterestLoss = 0.0;
                    //�������E�t�����v
                    double l_dblInterestProfit = 0.0;
                    
                    //���P�����擾
                    double l_dblContractPrice = l_targetContractDetail.getContractPrice();
                    //�]���P��
                    double l_dblUnitPrice = l_targetContractDetail.getUnitPrice();
                    //���ʑ��
                    double l_dblTmpContractAmount = Math.floor(l_dblContractPrice * l_dblOpenContractQuantity);
                    //�]�����
                    double l_dblTmpEstimatedAmount = Math.floor(l_dblUnitPrice * l_dblOpenContractQuantity);
                    //�����ό��ʕ]�����v
                    double l_dblAssetProfitLoss = 0.0;

                    //���敪���擾
                    ContractTypeEnum l_contractType = l_targetContractDetail.getContractType();
                
                    //�����̏ꍇ
                    if(ContractTypeEnum.LONG.equals(l_contractType))
                    {
                        //�����������ɂȂ�
                        l_dblInterestLoss =
                            Math.floor(l_targetContractDetail.getInterestFee() * l_dblRate + errorCorrection);
                        
                        //�t�������v�ɂȂ�
                        l_dblInterestProfit =
                            Math.floor(l_targetContractDetail.getPayInterestFee() * l_dblRate + errorCorrection);
                        
                        //�]���P�������݂���ꍇ�ɖ����ό��ʕ]�����v��]������
                        if(l_dblUnitPrice > 0)
                        {
                            //�����ό��ʕ]�����v = �]����� - ���ʑ��
                            l_dblAssetProfitLoss = l_dblTmpEstimatedAmount - l_dblTmpContractAmount;
                        }
                    }
                    //�����̏ꍇ
                    else if(ContractTypeEnum.SHORT.equals(l_contractType))
                    {
                        //�t���������ɂȂ�
                        l_dblInterestLoss =
                            Math.floor(l_targetContractDetail.getPayInterestFee() * l_dblRate + errorCorrection);
                        
                        //�݊�����ǉ�
                        l_dblInterestLoss =
                            l_dblInterestLoss
                                + Math.floor(l_targetContractDetail.getLoanEquityFee() * l_dblRate + errorCorrection);
                        
                        //���������v�ɂȂ�
                        l_dblInterestProfit =
                            Math.floor(l_targetContractDetail.getInterestFee() * l_dblRate + errorCorrection);
                        
                        //�]���P�������݂���ꍇ�ɖ����ό��ʕ]�����v��]������
                        if(l_dblUnitPrice > 0)
                        {
                            //�����ό��ʕ]�����v = ���ʑ�� - �]�����
                            l_dblAssetProfitLoss = l_dblTmpContractAmount - l_dblTmpEstimatedAmount;
                        }
                    }
                    
                    //�����ό��ʕ]����
                    double l_dblAssetLoss = 0.0;
                    //�����ό��ʕ]���v
                    double l_dblAssetProfit = 0.0;
                    if(l_dblAssetProfitLoss < 0)
                    {
                        l_dblAssetLoss = Math.abs(Math.ceil(l_dblAssetProfitLoss));
                    }
                    else
                    {
                        l_dblAssetProfit = Math.floor(l_dblAssetProfitLoss);
                    }
                    
                    //���萔�����Z�b�g
                    l_sumOpenContract.setSetupFee(l_dblSetupFee);
                    
                    //���̑����ʏ��o����Z�b�g
                    l_sumOpenContract.setOtherCost(l_dblOtherCost);
                    
                    //�������E�t���������Z�b�g
                    l_sumOpenContract.setInterestLoss(l_dblInterestLoss);
                    
                    //�������E�t�����v���Z�b�g
                    l_sumOpenContract.setInterestProfit(l_dblInterestProfit);
                    
                    //�����ό��ʕ]�������Z�b�g
                    l_sumOpenContract.setAssetLoss(l_dblAssetLoss);
                    
                    //�����ό��ʕ]���v���Z�b�g
                    l_sumOpenContract.setAssetProfit(l_dblAssetProfit);
                }
            }
            
            //���ʑ��
            double l_dblContractAmount = Math.floor(l_targetContractDetail.getContractPrice() * l_dblOpenContractQuantity);

            //�K�v�ۏ؋�
            double l_dblMarginDeposit = Math.floor(l_targetContractDetail.getContractPrice()
                * l_dblOpenContractQuantity
                * l_targetContractDetail.getMarginDepositRate() / depositDenominator);
        
            //�����K�v�ۏ؋�
            double l_dblCashMarginDeposit = Math.floor(l_targetContractDetail.getContractPrice() 
                * l_dblOpenContractQuantity 
                * l_targetContractDetail.getCashMarginDepositRate() / depositDenominator);
            //���ς݂̏ꍇ�A���������σt�B�[���h�ɃZ�b�g
            if(l_targetContract.isContractExecuted())
            {
                l_sumOpenContract.setContractAmount(l_dblContractAmount);
                l_sumOpenContract.setMarginDeposit(l_dblMarginDeposit);
                l_sumOpenContract.setCashMarginDeposit(l_dblCashMarginDeposit);
            }
            //�����V�K���̏ꍇ�A�������t�B�[���h�ɃZ�b�g
            else
            {
                l_sumOpenContract.setUnExecContractAmount(l_dblContractAmount);
                l_sumOpenContract.setUnExecMarginDeposit(l_dblMarginDeposit);
                l_sumOpenContract.setUnExecCashMarginDeposit(l_dblCashMarginDeposit);   
            }
        }

        return l_sumOpenContract;
    }
    
    /**
     * (get����n���ʂ̏W�v)<BR>
     * �����Ŏw�肵�����̖���n���ʂ̏W�v������B<BR>
     * <BR>
     * �P�j���ʕϓ��ꗗ���擾����B<BR>
     * <BR>
     * �Q�j����v��ԍρE�������n���ʂ𖢎�n���ʂƂ��ďW�v����B<BR>
     * �@@�|�W�v���ځF<BR>
     * �@@�@@�E���ωv<BR>
     * �@@�@@�E���ϑ�<BR>
     * �@@�@@�E���ʑ��<BR>
     * �@@�@@�E�K�v�ۏ؋�<BR>
     * �@@�@@�E�����K�v�ۏ؋�<BR>
     * <BR>
     * �@@�|���ʑ���^�K�v�ۏ؋��^�����K�v�ۏ؋��^���ωv�^���ϑ��̋��ߕ�<BR>
     * <BR>
     * �@@�@@�E���ʕϓ��Dis����n����<����v��ԍρE�������n>�i:String =�@@�u1�F�K�v�ۏ؋��v�j)��true<BR>
     * �@@�@@�@@����<BR>
     * �@@�@@�E���ʕϓ�.is�ϓ����ԓ�(n)��true�@@�̏ꍇ<BR>
     * �@@�@@�@@���ʑ��(n)�@@�@@�@@�@@�����ʑ��(n)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�{�@@���ʕϓ�.���ʑ��<BR>
     * �@@�@@�@@�K�v�ۏ؋�(n)�@@�@@�@@���K�v�ۏ؋�(n)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�{�@@���ʕϓ�.�K�v�ۏ؋�<BR>
     * �@@�@@�@@�����K�v�ۏ؋�(n)�@@�������K�v�ۏ؋�(n)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�{�@@���ʕϓ�.�����K�v�ۏ؋�<BR>
     * �@@�@@�E���ʕϓ�.is����n����<����v��ԍρE�������n>�i:String =�@@�u0�FDEFAULT�v�j)��true<BR>
     * �@@����<BR>
     * �@@�@@�E���ʕϓ�.is�ϓ����ԓ�(n)��true�@@�̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@���ωv(n)�@@�@@�@@�@@�@@�����ωv(n)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�{�@@���ʕϓ�.���ωv<BR>
     * �@@�@@�@@���ϑ�(n)�@@�@@�@@�@@�@@�����ϑ�(n)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�{�@@���ʕϓ�.���ϑ�<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�{�@@���ʕϓ�.���ʏ��o��<BR>
     * <BR>
     * �R�j���v��ԍό��ʂ𖢎�n���ʂƂ��ďW�v����B<BR>
     * �@@�|�W�v���ځF<BR>
     * �@@�@@�E���ωv<BR>
     * �@@�@@�E���ϑ�<BR>
     * <BR>
     * �@@�|���ωv�^���ϑ��̋��ߕ�<BR>
     * �@@�@@�E���ʕϓ�.is���v��ԍρE�������n���ʁi:String =�@@�u0�FDEFAULT�v�j)��true<BR>
     * �@@�@@�@@����<BR>
     * �@@�@@�E���ʕϓ�.is�ϓ����ԓ�(n)��true�@@�̏ꍇ<BR>
     * �@@�@@�@@���ωv(n)�@@�@@�@@�@@�@@�����ωv(n)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�{�@@���ʕϓ�.���ωv<BR>
     * �@@�@@�@@���ϑ�(n)�@@�@@�@@�@@�@@�����ϑ�(n)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�{�@@���ʕϓ�.���ϑ�<BR>
     * <BR>
     * �S�j���v��ԍό��ʂ𖢎�n���ʂƂ��ďW�v����B<BR>
     * �@@�|�W�v���ځF<BR>
     * �@@�@@�E���ϑ�<����><BR>
     * �@@�@@�E���ωv<����><BR>
     * �@@�@@�E���ό��ʑO�����i�]��<����><BR>
     * �@@�@@�E���ϑ�<�w���><BR>
     * �@@�@@�E���ωv<�w���><BR>
     * <BR>
     * �@@�|���ωv<����>�^���ϑ�<����>�^���ό��ʑO�����i�]��<����>�̋��ߕ�<BR>
     * �@@�@@�E���ʕϓ�.�g�����U�N�V�����J�e�S�����ԍ�<BR>
     * �@@�@@�@@����<BR>
     * �@@�@@�E�w������]�͌v�Z����.�c�Ɠ�(T+0)<BR>
     * �@@�@@�@@����<BR>
     * �@@�@@�E���ʕϓ�.�g�����U�N�V�������������]�͌v�Z����.�c�Ɠ�(T+0)�@@�̏ꍇ<BR>
     * �@@�@@�@@���ϑ�<����>(n)�@@�@@�����ϑ�<����>(n)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�{�@@���ʕϓ�.���ϑ�<BR>
     * �@@�@@�@@���ωv<����>(n)�@@�@@�����ωv<����>(n)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�{�@@���ʕϓ�.���ωv<BR>
     * �@@�@@�@@�E�Ώی��ʏڍ�.�������]�͌v�Z����.�c�Ɠ�(T+0)<BR>
     * �@@�@@�@@�@@����<BR>
     * �@@�@@�@@�E�Ώی��ʏڍ�.�O���I�l��0�@@�̏ꍇ<BR>
     * �@@�@@�@@�@@�E�Ώی��ʏڍ�.���敪�������@@�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@���ό��ʑO�����i�]��<����>���Ώی��ʏڍ�.�O���I�l�@@�~�@@���ʕϓ�.�����@@-�@@�Ώی��ʏڍ�.���P���@@�~�@@���ʕϓ�.����<BR>
     * �@@�@@�@@�@@�E�Ώی��ʏڍ�.���敪�������@@�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@���ό��ʑO�����i�]��<����>���Ώی��ʏڍ�.���P���@@�~�@@���ʕϓ�.����  -�@@�Ώی��ʏڍ�.�O���I�l�@@�~�@@���ʕϓ�.����<BR>
     * <BR>
     * �@@�|���ωv<�w���>�^���ϑ�<�w���>�̋��ߕ�<BR>
     * �@@�@@�E���ʕϓ�.�g�����U�N�V�����J�e�S�����ԍ�<BR>
     * �@@�@@�@@����<BR>
     * �@@�@@�E�w��������ʕϓ�.��n���@@�̏ꍇ<BR>
     * �@@�@@�@@���ϑ�<�w���>(n)�@@�@@�����ϑ�<�w���>(n)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�{�@@���ʕϓ�.���ϑ�<BR>
     * �@@�@@�@@���ωv<�w���>(n)�@@�@@�����ωv<�w���>(n)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�{�@@���ʕϓ�.���ωv<BR>
     * �T�j����n���ʂ̏W�v���ʂ�Ԃ��B<BR>
     * <BR>
     * ��n�́A�����̎w����B<BR>
     * ���v�Z���Ɏg�p����e�l�̎擾���@@<BR>
     * �E���ʕϓ�.���ʑ���@@�@@�@@�@@�@@�@@�@@�@@�E�E�E���ʕϓ�.calc���ʑ��()<BR>
     * �E���ʕϓ�.�K�v�ۏ؋��@@�@@�@@�@@�@@�@@�@@�E�E�E���ʕϓ�.calc�K�v�ۏ؋�(�ۏ؋���)<BR>
     * �E���ʕϓ�.�����K�v�ۏ؋��@@�@@�@@�@@�@@�E�E�E���ʕϓ�.calc�����K�v�ۏ؋�(�����ۏ؋���)<BR>
     * �E���ʕϓ�.�g�����U�N�V�����������@@�E�E�E���ʕϓ�.get�g�����U�N�V����������()<BR>
     * �E���ʕϓ�.�����@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�E�E�E���ʕϓ�.get����()<BR>
     * �E���ʕϓ�.��n���@@�@@�@@�@@�@@�@@�@@�@@�@@�E�E�E���ʕϓ�.get��n��()<BR>
     * �E���ʕϓ�.���ωv�@@�@@�@@�@@�@@�@@�@@�@@�@@�E�E�E���ʕϓ�.calc���ωv()<BR>
     * �E���ʕϓ�.���ϑ��@@�@@�@@�@@�@@�@@�@@�@@�@@�E�E�E���ʕϓ�.calc���ϑ�()<BR>
     * �E���ʕϓ�.���ʏ��o��@@�@@�@@�@@�@@�@@�@@�E�E�E���ʕϓ�.get���ʏ��o��()<BR>
     * �E���P���@@�@@�@@�@@�@@�@@�@@�@@ �@@�@@�@@�@@�@@�E�E�E�Ώی��ʏڍ�.get���P��()<BR>
     * �E�ۏ؋����@@�@@�@@�@@�@@�@@�@@�@@ �@@�@@�@@�@@�E�E�E�Ώی��ʏڍ�.get�ۏ؋���()<BR>
     * �E�����ۏ؋����@@�@@�@@�@@�@@�@@ �@@�@@�@@�@@�E�E�E�Ώی��ʏڍ�.get�����ۏ؋���()<BR>
     * �E�O���I�l�@@�@@�@@�@@�@@�@@ �@@�@@�@@�@@�@@�@@�E�E�E�Ώی��ʏڍ�.get�O���I�l()<BR>
     * �E�]�͌v�Z����.�c�Ɠ�(T+0)�@@�@@�@@�@@ �E�E�E�]�͌v�Z����.get�c�Ɠ�(T+0)<BR>
     * @@param l_datDate - (�w���)
     * @@return webbroker3.tradingpower.updtpower.contract.WEB3TPSummaryUndeliveredContract
     * @@roseuid 40EB66BC01A7
     */
    public WEB3TPSummaryUndeliveredContract getSummaryUndeliveredContract(Date l_datDate) 
    {
        //create����n���ʂ̏W�v      
        WEB3TPSummaryUndeliveredContract l_sumUndeliveredContract = WEB3TPSummaryUndeliveredContract.create();

        //�Ώی��ʂ��擾
        WEB3TPTargetContract l_targetContract = getTargetContract();

        //�Ώی��ʏڍׂ��擾
        WEB3TPTargetContractDetail l_targetContractDetail = l_targetContract.getTargetContractDetail();

        //�]�͌v�Z�������擾
        WEB3TPCalcCondition l_calcCondition = getContractInfo().getCalcCondition();
    
        //�c�Ɠ�(T+0)���擾
        Date l_datBizDate0 = l_calcCondition.getBizDate(WEB3TPSpecifiedPointDef.T_0);

        //���ʕϓ��ꗗ���擾
        List l_histories = getHistories();
        
        //���ʕϓ��ꗗ�̃T�C�Y�Ń��[�v
        int l_intHistorySize = l_histories.size();
        for(int i = 0; i < l_intHistorySize; i++)
        {
            //���ʕϓ����擾
            WEB3TPHistory l_history = (WEB3TPHistory)l_histories.get(i);

            //����v��ԍρE�������n�̏ꍇ
            if (l_history.isUndeliveredContractNotDayTradeSwap(WEB3TPRestraintDivDef.MARGIN_DEPOSIT))
            {
                //�ϓ����ԓ��̏ꍇ
                if(l_history.isDuringReflectTime(l_datDate))
                {
                    if(DBG)
                    {
                        log.debug("WEB3TPSummaryUndeliveredContract summed up" + "[" + i + "]:" + l_history.toString());
                    }

                    //���ʑ��
                    double l_dblContractAmount = l_sumUndeliveredContract.getContractAmount() + Math.floor(l_history.calcContractAmount());
                    l_sumUndeliveredContract.setContractAmount(l_dblContractAmount);
                                        
                    //�K�v�ۏ؋�
                    double l_dblMarginDeposit = l_sumUndeliveredContract.getMarginDeposit() + Math.floor(l_history.calcMarginDeposit(l_targetContractDetail.getMarginDepositRate()));
                    l_sumUndeliveredContract.setMarginDeposit(l_dblMarginDeposit);
                    
                    //�����K�v�ۏ؋�
                    double l_dblCashMarginDeposit = l_sumUndeliveredContract.getCashMarginDeposit() + Math.floor(l_history.calcCashMarginDeposit(l_targetContractDetail.getCashMarginDepositRate()));
                    l_sumUndeliveredContract.setCashMarginDeposit(l_dblCashMarginDeposit);
                }
            }
            if (l_history.isUndeliveredContractNotDayTradeSwap(WEB3TPRestraintDivDef.DEFAULT))
            {
                if (l_history.isDuringReflectTime(l_datDate))
                {
                    //���ωv
                    double l_dblContractProfit = l_sumUndeliveredContract.getContractProfit() + Math.floor(l_history.calcContractProfit());
                    l_sumUndeliveredContract.setContractProfit(l_dblContractProfit);

                    //���ϑ�
                    double l_dblContractLoss = l_sumUndeliveredContract.getContractLoss()
                        + Math.floor(l_history.calcContractLoss()) + Math.floor(l_history.getContractTotalCost());
                    l_sumUndeliveredContract.setContractLoss(l_dblContractLoss);
                }
            }
            //���v��ԍς̏ꍇ
            else if(l_history.isDayTradeSwap(WEB3TPRestraintDivDef.DEFAULT))
            {
                //�ϓ����ԓ��̏ꍇ
                if(l_history.isDuringReflectTime(l_datDate))
                {
                    if(DBG)
                    {
                        log.debug("WEB3TPSummaryUndeliveredContract Only Profit/Loss summed up" + "[" + i + "]:" + l_history.toString());
                    }

                    //���ωv
                    double l_dblContractProfit = l_sumUndeliveredContract.getContractProfit() + Math.floor(l_history.calcContractProfit());
                    l_sumUndeliveredContract.setContractProfit(l_dblContractProfit);

                    //���ϑ�
                    double l_dblContractLoss = l_sumUndeliveredContract.getContractLoss() + Math.floor(l_history.calcContractLoss());
                    l_sumUndeliveredContract.setContractLoss(l_dblContractLoss);
                }
            }
            
            //���ό��ʂ�<����>�̌��ϑ��v�����߂�
            //�ԍς̏ꍇ
            if(FinTransactionCateg.EQTYPE_CLOSE_MARGIN.equals(l_history.getTransactionCateg()))
            {
                //����.�w���=�ԍϓ�=�c�Ɠ�(T+0)�̏ꍇ�̂݁A���ϑ��v<����>�����߂�
                if(WEB3DateUtility.compareToDay(l_datBizDate0, l_datDate) == 0)
                {
                    if(WEB3DateUtility.compareToDay(l_datBizDate0, l_history.getTransactionDate()) == 0)
                    {
                        //���ϑ�<����>
                        double l_dblTodayRepayContractLoss = 
                            l_sumUndeliveredContract.getTodayRepayContractLoss() + Math.floor(l_history.calcContractLoss());
                        l_sumUndeliveredContract.setTodayRepayContractLoss(l_dblTodayRepayContractLoss);

                        //���ωv<����>
                        double l_dblTodayRepayContractProfit = 
                            l_sumUndeliveredContract.getTodayRepayContractProfit() + Math.floor(l_history.calcContractProfit());
                        l_sumUndeliveredContract.setTodayRepayContractProfit(l_dblTodayRepayContractProfit);
                        
                        //�c�Ɠ�(T+0)�����O�Ɍ��Ă����ʂŁA�O���I�l������ꍇ�ɁA���ό��ʂ̑O�����i�]��<����>�����߂�
                        if(WEB3DateUtility.compareToDay(l_targetContractDetail.getOpenDate(), l_datBizDate0) < 0)
                        {
                            if(l_targetContractDetail.getLastClosingPrice() > 0)
                            {
                                //���敪���擾
                                ContractTypeEnum l_contractType = l_targetContractDetail.getContractType();
                                
                                //���P�����擾
                                double l_dblContractPrice = l_targetContractDetail.getContractPrice();
                     
                                //�O���I�l���擾
                                double l_dblLastClosingPrice = l_targetContractDetail.getLastClosingPrice();
                                
                                //���ϊ������擾
                                double l_dblRepayQuantity = l_history.getQuantity();
                                
                                //���ό��ʑ��
                                double l_dblTmpContractAmount = Math.floor(l_dblContractPrice * l_dblRepayQuantity);
                                
                                //���ό��ʂ̑O�����i�]�����
                                double l_dblTmpEstimatedAmount = Math.floor(l_dblLastClosingPrice * l_dblRepayQuantity);
                                
                                //���ό��ʂ̑O�����i�]��<����>
                                double l_dblTodayRepayContractPrevAsset = 0.0;
                                
                                //�����̏ꍇ
                                if(ContractTypeEnum.LONG.equals(l_contractType))
                                {
                                    //���ό��ʑO�����i�]��<����> = �O���]����� - ���ό��ʑ��
                                    l_dblTodayRepayContractPrevAsset = l_dblTmpEstimatedAmount - l_dblTmpContractAmount;
                                }
                                //�����̏ꍇ
                                else if(ContractTypeEnum.SHORT.equals(l_contractType))
                                {
                                    //���ό��ʑO�����i�]��<����> = ���ό��ʑ�� - �O���]�����
                                    l_dblTodayRepayContractPrevAsset = l_dblTmpContractAmount - l_dblTmpEstimatedAmount;
                                }
                                if(l_dblTodayRepayContractPrevAsset < 0)
                                {
                                    l_dblTodayRepayContractPrevAsset = Math.ceil(l_dblTodayRepayContractPrevAsset);
                                }
                                else
                                {
                                    l_dblTodayRepayContractPrevAsset = Math.floor(l_dblTodayRepayContractPrevAsset);
                                }
                                l_dblTodayRepayContractPrevAsset += l_sumUndeliveredContract.getTodayRepayContractPrevAsset();
                                l_sumUndeliveredContract.setTodayRepayContractPrevAsset(l_dblTodayRepayContractPrevAsset);
                            }
                        }
                    }
                }
                
                //���ό��ʂ�<�w���>�̌��ϑ��v�����߂�
                //�w���=��n���̏ꍇ�̂݁A���ϑ��v<�w���>�����߂�
                if(WEB3DateUtility.compareToDay(l_history.getDeliveryDate(), l_datDate) == 0)
                {
                    //���ϑ�<�w���>
                    double l_dblDesignateDateContractLoss = 
                        l_sumUndeliveredContract.getDesignateDateContractLoss() + Math.floor(l_history.calcContractLoss());
                    l_sumUndeliveredContract.setDesignateDateContractLoss(l_dblDesignateDateContractLoss);

                    //���ωv<�w���>
                    double l_dblDesignateDateContractProfit = 
                        l_sumUndeliveredContract.getDesignateDateContractProfit() + Math.floor(l_history.calcContractProfit());
                    l_sumUndeliveredContract.setDesignateDateContractProfit(l_dblDesignateDateContractProfit);
                }
            }
        }
        
        return l_sumUndeliveredContract;
    }
    
    /**
     * (get���v��ԍρE�������n���ʂ̏W�v) <BR>
     * <BR>
     * ����.�w����̓��v��ԍρE�������n���ʂ��W�v����B <BR>
     * <BR>
     * 1)���v��ԍρE�������n���ʂ̏W�v�I�u�W�F�N�g���쐬����B<BR> 
     * �@@-���v��ԍρE�������n���ʂ̏W�v.create���v��ԍρE�������n���ʂ̏W�v()���R�[�� <BR>
     * <BR>
     * 2)�Ώی��ʃI�u�W�F�N�g���擾����B <BR>
     * �@@-this.get�Ώی���()���R�[�� <BR>
     * <BR>
     * 3)�擾�����Ώی��ʃI�u�W�F�N�g���A�Ώی��ʏڍ׃I�u�W�F�N�g���擾����B <BR>
     * �@@-�Ώی���.get���ʕϓ�()���R�[�� <BR>
     * <BR>
     * 4)���ʕϓ��I�u�W�F�N�g�̃��X�g���擾����B <BR>
     * �@@-this.get���ʕϓ�()���R�[�� <BR>
     * <BR>
     * 5)���v��ԍρE�������n���ʂ̏W�v������B <BR>
     * <BR>
     * �@@���擾�������ʕϓ��I�u�W�F�N�g�̃��X�g�̗v�f����LOOP������<BR>
     * <BR>
     * �@@�@@�@@[����.�w������ϓ����ԓ��̏ꍇ]<BR>
     * �@@�@@�@@(���ʕϓ�.is�ϓ����Ԓ�(n) = true)<BR>
     * <BR>
     * �@@�@@�@@�@@�D[( ���ʕϓ�.is���v��ԍρE�������n���ʁi:String =�@@�u1�F�K�v�ۏ؋��v�j�j= true�̏ꍇ]<BR>
     * �@@�@@�@@�@@���ʑ���^�K�v�ۏ؋��^�����K�v�ۏ؋����W�v����B<BR>
     * �@@�@@�@@�@@�|���ʑ�� = ���ʑ�� + ���ʕϓ�.calc���ʑ��()<BR>
     * �@@�@@�@@�@@�|�K�v�ۏ؋� = �K�v�ۏ؋� + ���ʕϓ�.calc�K�v�ۏ؋�()<BR>
     * �@@�@@�@@�@@�|�����K�v�ۏ؋� = �����K�v�ۏ؋� + ���ʕϓ�.calc�����K�v�ۏ؋�()<BR>
     * <BR>
     * �@@�@@�@@�A�D[�����E���n�ł���ꍇ]<BR>
     * �@@�@@�@@�@@(���ʕϓ�.get�g�����U�N�V�����J�e�S�� = 60�F�����E���n��� )<BR>
     * <BR>
     * �@@�@@�@@�@@[(���ʕϓ�.is���v��ԍρE�������n���ʁi:String =�u2�F�]�����v�v)�@@= true]�̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�|�]�����v���v�Z����B<BR>
     * �@@�@@�@@�@@�@@�@@[�����̏ꍇ]<BR>
     * �@@�@@�@@�@@�@@�@@(�Ώی��ʏڍ�.get���敪() = 1�F����)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�]�����v = (���P�� - �]���P��) �~ ���ʕϓ�.get����()<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@[���n�̏ꍇ]<BR>
     * �@@�@@�@@�@@�@@�@@(�Ώی��ʏڍ�.get���敪() = 2�F����)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�]�����v = (�]���P�� - ���P��) �~ ����<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@������ = ���ʕϓ�.get����()<BR>
     * �@@�@@�@@�@@�@@�@@�����P�� = �Ώی��ʏڍ�.get���P��()<BR>
     * �@@�@@�@@�@@�@@�@@���]���P�� = �Ώی��ʏڍ�.get�]���P��()<BR>
     * �@@�@@�@@�@@�@@�@@���]���P����0�̏ꍇ�A�]�����Ȃ��i�]�����v = 0�Ƃ���j<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�|�������n���ʕ]�����^�������n���ʕ]���v���v�Z����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@[�]�����̏ꍇ]<BR>
     * �@@�@@�@@�@@�@@�@@�v�Z�����]�����v < 0)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�������n���ʕ]���� = ABS(�]�����v)<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@[�]���v�̏ꍇ]<BR>
     * �@@�@@�@@�@@�@@�@@(�ȊO�̏ꍇ)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�������n���ʕ]���v = �]�����v<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�|�������n���ʌ��ϑ����v�Z����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�������n���ʌ��ϑ� = �������n���ʌ��ϑ� + ���ʕϓ�.get���ʏ��o��()<BR> 
     * <BR>
     * 6)���v��ԍρE�������n���ʂ̏W�v�I�u�W�F�N�g�ɒl���Z�b�g���ԋp����B <BR>
     * <BR>
     * �@@�|���v��ԍρE�������n���ʂ̏W�v.���ʑ�� = 5)�ŏW�v���ꂽ���ʑ�� <BR>
     * �@@�|���v��ԍρE�������n���ʂ̏W�v.�K�v�ۏ؋� = 5)�ŏW�v���ꂽ�K�v�ۏ؋� <BR>
     * �@@�|���v��ԍρE�������n���ʂ̏W�v.�����K�v�ۏ؋� = 5)�ŏW�v���ꂽ�����K�v�ۏ؋� <BR>
     * �@@�|���v��ԍρE�������n���ʂ̏W�v.�������n���ʕ]���� = 5)�Ōv�Z���ꂽ�������n���ʕ]���� <BR>
     * �@@�|���v��ԍρE�������n���ʂ̏W�v.�������n���ʕ]���v = 5)�Ōv�Z���ꂽ�������n���ʕ]���v <BR>
     * �@@�|���v��ԍρE�������n���ʂ̏W�v.�������n���ʌ��ϑ� = 5)�Ōv�Z���ꂽ�������n���ʌ��ϑ�<BR>
     * 
     * @@param l_datDate - (�w���)
     * @@return webbroker3.tradingpower.updtpower.contract.WEB3TPSummaryDayTradeSwapContract
     */
    public WEB3TPSummaryDayTradeSwapContract getSummaryDayTradeSwapContract(Date l_datDate) 
    {
        //create���v��ԍρE�������n���ʂ̏W�v      
        WEB3TPSummaryDayTradeSwapContract l_sumDayTradeSwapContract = WEB3TPSummaryDayTradeSwapContract.create();

        //�Ώی��ʂ��擾
        WEB3TPTargetContract l_targetContract = getTargetContract();

        //�Ώی��ʏڍׂ��擾
        WEB3TPTargetContractDetail l_targetContractDetail = l_targetContract.getTargetContractDetail();

        //���ʕϓ��ꗗ���擾
        List l_histories = getHistories();
        
        //���ʕϓ��ꗗ�̃T�C�Y�Ń��[�v
        int l_intHistorySize = l_histories.size();
        for(int i = 0; i < l_intHistorySize; i++)
        {
            //���ʕϓ����擾
            WEB3TPHistory l_history = (WEB3TPHistory)l_histories.get(i);

            //�ϓ����ԓ��̏ꍇ
            if (l_history.isDuringReflectTime(l_datDate))
            {
                //�@@�D[( ���ʕϓ�.is���v��ԍρE�������n���ʁi:String =�@@�u1�F�K�v�ۏ؋��v�j�j= true�̏ꍇ]
                if (l_history.isDayTradeSwap(WEB3TPRestraintDivDef.MARGIN_DEPOSIT))
                {
                    if(DBG)
                    {
                        log.debug("WEB3TPSummaryDayTradeSwapContract summed up" + "[" + i + "]:" + l_history.toString());
                    }

                    //���ʑ��
                    double l_dblContractAmount = l_sumDayTradeSwapContract.getContractAmount() + Math.floor(l_history.calcContractAmount());
                    l_sumDayTradeSwapContract.setContractAmount(l_dblContractAmount);
                                        
                    //�K�v�ۏ؋�
                    double l_dblMarginDeposit = l_sumDayTradeSwapContract.getMarginDeposit() + Math.floor(l_history.calcMarginDeposit(l_targetContractDetail.getMarginDepositRate()));
                    l_sumDayTradeSwapContract.setMarginDeposit(l_dblMarginDeposit);
                    
                    //�����K�v�ۏ؋�
                    double l_dblCashMarginDeposit = l_sumDayTradeSwapContract.getCashMarginDeposit() + Math.floor(l_history.calcCashMarginDeposit(l_targetContractDetail.getCashMarginDepositRate()));
                    l_sumDayTradeSwapContract.setCashMarginDeposit(l_dblCashMarginDeposit);
                }
                //�A�D[�����E���n�ł���ꍇ]
                if (FinTransactionCateg.EQTYPE_SWAP_MARGIN.equals(l_history.getTransactionCateg()))
                {
                    //[(���ʕϓ�.is���v��ԍρE�������n���ʁi:String =�u2�F�]�����v�v)�@@= true]�̏ꍇ
                    if (l_history.isDayTradeSwap(WEB3TPRestraintDivDef.PROFITLOSS))
                    {
                        //���P��
                        double l_dblContractPrice = l_targetContractDetail.getContractPrice();
                        //�]���P��
                        double l_dblUnitPrice = l_targetContractDetail.getUnitPrice();
                        
                        //���ʑ��
                        double l_dblTmpContractAmount = Math.floor(l_dblContractPrice * l_history.getQuantity());
                        //�]�����
                        double l_dblTmpEstimatedAmount = Math.floor(l_dblUnitPrice * l_history.getQuantity());
                        //�������n���ʕ]�����v
                        double l_dblSwapProfitLoss = 0.0;

                        //���敪���擾
                        ContractTypeEnum l_contractType = l_targetContractDetail.getContractType();

                        //�����̏ꍇ
                        if(ContractTypeEnum.LONG.equals(l_contractType))
                        {
                            //�]���P�������݂���ꍇ�Ɍ������n���ʕ]�����v��]������
                            if(l_dblUnitPrice > 0)
                            {
                                //�������n���ʕ]�����v = �]����� - ���ʑ��
                                l_dblSwapProfitLoss = l_dblTmpEstimatedAmount - l_dblTmpContractAmount;
                            }
                        }
                        //�����̏ꍇ
                        else if(ContractTypeEnum.SHORT.equals(l_contractType))
                        {
                            //�]���P�������݂���ꍇ�Ɍ������n���ʕ]�����v��]������
                            if(l_dblUnitPrice > 0)
                            {
                                //�������n���ʕ]�����v = ���ʑ�� - �]�����
                                l_dblSwapProfitLoss = l_dblTmpContractAmount - l_dblTmpEstimatedAmount;
                            }
                        }
                        
                        //�������n���ʕ]����
                        double l_dblSwapLoss = 0.0;
                        //�������n���ʕ]���v
                        double l_dblSwapProfit = 0.0;
                        if(l_dblSwapProfitLoss < 0)
                        {
                            l_dblSwapLoss = Math.abs(Math.ceil(l_dblSwapProfitLoss));
                        }
                        else
                        {
                            l_dblSwapProfit = Math.floor(l_dblSwapProfitLoss);
                        }

                        //�������n���ʕ]�������Z�b�g
                        double l_dblSwapLossTemp = l_sumDayTradeSwapContract.getSwapContractAssetLoss() + l_dblSwapLoss;
                        l_sumDayTradeSwapContract.setSwapContractAssetLoss(l_dblSwapLossTemp);
                        //�������n���ʕ]���v���Z�b�g
                        double l_dblSwapProfitTemp = l_sumDayTradeSwapContract.getSwapContractAssetProfit() + l_dblSwapProfit;
                        l_sumDayTradeSwapContract.setSwapContractAssetProfit(l_dblSwapProfitTemp);     
                    }

                    //[(���ʕϓ�.is���v��ԍρE�������n���ʁi:String =�u0�FDEFAULT�v) = true)]�̏ꍇ
                    if (l_history.isDayTradeSwap(WEB3TPRestraintDivDef.DEFAULT))
                    {
                        //�������n���ʌ��ϑ����Z�b�g
                        BigDecimal l_bdSwapContractSettleLoss = new BigDecimal(Double.toString(
                            l_sumDayTradeSwapContract.getSwapContractSettleLoss()));
                        BigDecimal l_bdContractTotalCost = new BigDecimal(Double.toString(l_history.getContractTotalCost()));

                        double l_dblContractTotalCost =
                            l_bdSwapContractSettleLoss.add(l_bdContractTotalCost).doubleValue();
                        l_sumDayTradeSwapContract.setSwapContractSettleLoss(l_dblContractTotalCost);
                    }
                }
            }
        }
        return l_sumDayTradeSwapContract;
    }
    
    /**
     * (add���ʕϓ�)<BR>
     * �����̌��ʕϓ������ʕϓ��ꗗ�ɒǉ�����B<BR>
     * @@param l_history - (���ʕϓ�)
     * @@roseuid 40BAFC3E008C
     */
    public void addHistory(WEB3TPHistory l_history) 
    {
        List l_histories = getHistories();
        l_histories.add(l_history);
    }

    /**
     * ���̃I�u�W�F�N�g�̕�����\����Ԃ��B
     */
    public String toString()
    {
        ToStringBuilder l_builder = ToStringUtils.newToStringBuilder(this);
        
        //�Ώی���
        l_builder.append("targetContract", getTargetContract());
        
        //���ʕϓ�
        int l_intSize = histories.size();
        for(int i = 0; i < l_intSize; i++)
        {
            l_builder.append("histories" + "[" + i + "]",histories.get(i));
        }
        return l_builder.toString();
    }

    /**
     * (get�����ԍό��ʊ����̏W�v) <BR>
     * <BR>
     * �W�v���������ԍό��ʊ�����ԋp����B<BR>
     * �i�߂�l�̌^�Fdouble�j<BR>
     * <BR>
     * �P�j�@@T+0�̓��t���擾����B<BR>
     * �m�擾���@@�n<BR>
     * �@@this.get���ʏ��().get�]�͌v�Z����().get�c�Ɠ�(T+0)<BR>
     * <BR>
     * �Q�j�@@���ʕϓ��̈ꗗ���擾���A������s���B<BR>
     * �m�擾���@@�n<BR>
     * �@@this.get���ʕϓ�()<BR>
     * �@@�� this.get���ʕϓ�()�@@==�@@null<BR>
     * �@@���� this.get���ʕϓ�().size()�@@==�@@0 �̏ꍇ�A0��ԋp����B<BR>
     * <BR>
     * �R�j�@@�擾�������ʕϓ��ꗗ�̗v�f����LOOP���A�ȉ��̏������s���B<BR>
     * <BR>
     * �i1�j ���ʕϓ��ꗗ���A���ʕϓ��I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �i2�j ���ʕϓ��I�u�W�F�N�g���ȉ��̏����𖞂����ꍇ�A�i3�j�ȍ~�̏������s���B<BR>
     * �m���o�����n<BR>
     *�@@�E���ʕϓ�.get�g�����U�N�V�����J�e�S��()�@@IN<BR>
     *�@@�@@�i"40" (�ԍώ��),�@@"60" (�����E���n���)�jAND<BR>
     *�@@�E���ʕϓ�.get�g�����U�N�V����������()�@@==�@@�P�j�Ŏ擾�������t<BR>
     * <BR>
     * �i3�j �������擾����B<BR>
     * �m�擾���@@�n
     * �@@���ʕϓ�.get����() <BR>
     * <BR>
     * �i4�j �������W�v����B<BR>
     * �m�v�Z���n<BR>
     * �@@�W�v���ʁ@@=�@@�W�v���ʁ@@�{�@@(3)�Ŏ擾��������<BR>
     * <BR>
     * �S�j�@@�����̏W�v���ʂ�ԋp����B<BR>
     * <BR>
     * @@return double
     */
    public double getSummaryTodayRepayContractQuantity()
    {
        final String STR_METHOD_NAME = "getSummaryTodayRepayContractQuantity()";
        log.entering(STR_METHOD_NAME);

        //T+0�̓��t���擾����B
        Date l_datBizDate0 = this.getContractInfo().getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_0);

        //���ʕϓ��̈ꗗ���擾���A������s���B
        List l_lisHistories = this.getHistories();

        //�����̏W�v
        double l_dblSumQuantity = 0;
        BigDecimal l_bdSumQuantity = new BigDecimal(Double.toString(l_dblSumQuantity));

        //�� this.get���ʕϓ�()�@@==�@@null�@@���� this.get���ʕϓ�().size()�@@==�@@0 �̏ꍇ�A0��ԋp����B
        if (l_lisHistories == null || l_lisHistories.size() == 0)
        {
            log.debug(" �W�v���� = " + l_dblSumQuantity);
            log.exiting(STR_METHOD_NAME);
            return l_dblSumQuantity;
        }

        int l_intSize = l_lisHistories.size();

        for (int i = 0; i < l_intSize; i++)
        {
            //���ʕϓ��ꗗ���A���ʕϓ��I�u�W�F�N�g���擾����B
            WEB3TPHistory l_history = (WEB3TPHistory)l_lisHistories.get(i);

            FinTransactionCateg l_transactionCateg = l_history.getTransactionCateg();

            Date l_datTransactionDate = l_history.getTransactionDate();

            //���ʕϓ��I�u�W�F�N�g���ȉ��̏����𖞂����ꍇ�A�ȍ~�̏������s���B
            //���ʕϓ�.get�g�����U�N�V�����J�e�S��()�@@IN�i"40" (�ԍώ��),�@@"60" (�����E���n���)�j
            //AND  ���ʕϓ�.get�g�����U�N�V����������()�@@==�@@�P�j�Ŏ擾�������t
            if ((FinTransactionCateg.EQTYPE_CLOSE_MARGIN.equals(l_transactionCateg)
                || FinTransactionCateg.EQTYPE_SWAP_MARGIN.equals(l_transactionCateg))
                && WEB3DateUtility.compareToDay(l_datTransactionDate, l_datBizDate0) == 0)
            {
                //�������擾����B
                double l_dblQuantity = l_history.getQuantity();

                BigDecimal l_bdQuantity = new BigDecimal(Double.toString(l_dblQuantity));

                //�W�v���ʁ@@=�@@�W�v���� + ���ʕϓ�.get����()
                l_bdSumQuantity = l_bdSumQuantity.add(l_bdQuantity);
            }
        }

        //�����̏W�v���ʂ�ԋp����
        log.debug(" �W�v���� = " + l_bdSumQuantity.doubleValue());
        log.exiting(STR_METHOD_NAME);
        return l_bdSumQuantity.doubleValue();
    }
}
@
