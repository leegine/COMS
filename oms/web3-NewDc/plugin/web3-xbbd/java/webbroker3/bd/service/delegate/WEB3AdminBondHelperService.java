head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondHelperService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���Ǘ��҃w���p�[�T�[�r�X(WEB3AdminBondHelperService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/8/16 ꎉ�(���u) �V�K�쐬         
*/

package webbroker3.bd.service.delegate;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;

import webbroker3.common.WEB3BaseException;
import webbroker3.bd.WEB3BondEstimatedPriceCalcResult;
import webbroker3.bd.WEB3BondExecuteDateInfo;
import webbroker3.bd.WEB3BondOrderTypeJudge;
import webbroker3.bd.WEB3BondOrderUnit;
import webbroker3.bd.WEB3BondProduct;
import webbroker3.bd.data.CustodianRow;
import webbroker3.bd.message.WEB3AdminBondAccountInfo;
import webbroker3.bd.message.WEB3AdminBondCustodianUnit;
import webbroker3.bd.message.WEB3AdminBondOrderExecInfo;
import webbroker3.bd.message.WEB3AdminBondOrderInfo;
import webbroker3.bd.message.WEB3AdminBondProductInfo;

/**
 * (���Ǘ��҃w���p�[�T�[�r�X)<BR>
 * ���Ǘ��҃w���p�[�T�[�r�X�@@�C���^�[�t�F�C�X�N���X
 * 
 * @@author ꎉ�
 * @@version 1.0
 */
public interface WEB3AdminBondHelperService extends Service
{
    
    /**
     * (to�ڋq���)<BR>
     * �������ڋq�����쐬����
     * @@param l_orderUnit - (�������P��)<BR>
     * �������P��
     * @@return webbroker3.bd.message.WEB3AdminBondAccountInfo
     * @@throws WEB3BaseException
     * @@roseuid 44C74D46005C
     */
    public WEB3AdminBondAccountInfo toAccountInfo(WEB3BondOrderUnit l_orderUnit) 
        throws WEB3BaseException;
    
    /**
     * (to�������)<BR>
     * ������蒍�������쐬����
     * @@param l_orderUnit - (�������P��)<BR>
     * �������P��
     * @@return webbroker3.bd.message.WEB3AdminBondOrderInfo
     * @@throws WEB3BaseException
     * @@roseuid 44C74D46005E
     */
    public WEB3AdminBondOrderInfo toOrderInfo(WEB3BondOrderUnit l_orderUnit) 
        throws WEB3BaseException;
    
    /**
     * (to�����)<BR>
     * �������������쐬����
     * @@param l_orderUnit - (�������P��)<BR>
     * �������P��
     * @@return webbroker3.bd.message.WEB3AdminBondOrderExecInfo
     * @@throws WEB3BaseException
     * @@roseuid 44C74D460060
     */
    public WEB3AdminBondOrderExecInfo toOrderExecInfo(WEB3BondOrderUnit l_orderUnit) 
        throws WEB3BaseException;
    
    /**
     * (to�����)<BR>
     * �������������쐬����.
     * @@param l_executeDateInfo - (���������)<BR>
     * ���������
     * @@param l_calcResult - (����n����v�Z����)<BR>
     * ����n����v�Z����
     * @@param l_custodianUnit - (�J�X�g�f�B�A��)<BR>
     * �J�X�g�f�B�A��
     * @@param l_orderUnit - (�g���������P��)<BR>
     * �������P��
     * @@return webbroker3.bd.message.WEB3AdminBondOrderExecInfo
     * @@throws WEB3BaseException
     * @@roseuid 44DAD04E039B
     */
    public WEB3AdminBondOrderExecInfo toOrderExecInfo(
        WEB3BondExecuteDateInfo l_executeDateInfo, 
        WEB3BondEstimatedPriceCalcResult l_calcResult, 
        WEB3AdminBondCustodianUnit l_custodianUnit, 
        WEB3BondOrderUnit l_orderUnit) throws WEB3BaseException;
    
    /**
     * (to�������)<BR>
     * to�������<BR>
     * <BR>
     * �@@���������ɖ�������Ԃ�
     * @@param l_product - (������)<BR>
     * ������
     * @@return webbroker3.bd.message.WEB3AdminBondProductInfo
     * @@throws WEB3BaseException
     * @@roseuid 44D93D6C000F
     */
    public WEB3AdminBondProductInfo toProductInfo(WEB3BondProduct l_product)
        throws WEB3BaseException;
    
    /**
     * (get�⏕����)<BR>
     * get�⏕����
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h
     * @@param l_strAccountCode - (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h
     * @@return SubAccount
     * @@throws WEB3BaseException
     * @@roseuid 44CB1315035E
     */
    public SubAccount getSubAccount(
        String l_strInstitutionCode,
        String l_strBranchCode, 
        String l_strAccountCode) throws WEB3BaseException;
    
    /**
     * (reset��n���)<BR>
     * reset��n���
     * @@param l_calcResult - (����n����v�Z����)<BR>
     * ����n����v�Z����
     * @@param l_orderExecInfo - (�����)<BR>
     * �����
     * @@param l_product - (������)<BR>
     * ������
     * 
     * @@return webbroker3.bd.WEB3BondPriceCalcResult
     * @@throws WEB3BaseException
     * @@roseuid 44CB1DF8020B
     */
    public WEB3BondEstimatedPriceCalcResult resetEstimatedPrice(
        WEB3BondEstimatedPriceCalcResult l_calcResult, 
        WEB3AdminBondOrderExecInfo l_orderExecInfo, 
        WEB3BondProduct l_product) throws WEB3BaseException;
    
    /**
     * (reset�������)<BR>
     * reset�������<BR>
     * <BR>
     * �P�j����.�����.�����I��null�̏ꍇ�A<BR>
     * �@@����.���������.����������.�����.����<BR>
     * <BR>
     * �Q�j����.�����.���n�����I��null�̏ꍇ�A<BR>
     * �@@����.���������.���n����������.�����.���n����<BR>
     * <BR>
     * �R�j����.�����.��n���I��null�̏ꍇ�A<BR>
     * �@@����.���������.��n��������.�����.��n��<BR>
     * <BR>
     * �S�j����.�����.���n��n���I��null�̏ꍇ�A<BR>
     * �@@����.���������.���n��n��������.�����.���n��n��<BR>
     * <BR>
     * �T�j����.�����.�������@@!= null�̏ꍇ�A<BR>
     * �@@�@@����.���������.������������.�����.������<BR>
     * <BR>
     * �U�j����.�����.�������@@== null�̏ꍇ�A<BR>
     * �@@�U�|�P�j�����X�ʏ����𐶐�����B
     * �@@�@@�@@�@@�@@[����]
     * �@@�@@�@@�@@�@@�@@���XID�F����.���X.���XID
     * �@@�U�|�Q�j�����X�ʏ���.get�������ݒ�敪����'�����'
     * �@@�@@�@@�@@�@@����
     * �@@�@@�@@�@@�@@����.��������ʔ���.is���偁��true�@@�̏ꍇ�A
     * �@@�@@�@@�@@�@@�@@����.���������.������������.������.get������
     * �@@�@@�@@�@@�@@�@@�@@�@@[����]
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�����@@�@@�@@�@@�@@�F�@@����.���������.����
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@��������ʔ���F�@@����.��������ʔ���
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@���ϋ敪�@@�@@�@@�@@�F�@@����.���ϋ敪
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@���X�@@�@@�@@�@@�@@�@@�F�@@����.���X
     * �@@�U�|�R�j��L�ȊO�̏ꍇ�A
     * �@@�@@�@@�@@�@@�@@����.���������.������������.�����.��n��
     * �V�j����������Ԃ��B
     * @@param l_orderExecInfo - (�����)<BR>
     * �����
     * @@param l_executeDateInfo - (���������)<BR>
     * ���������
     * @@param l_bondOrderTypeJudge - (��������ʔ���)<BR>
     * ��������ʔ���<BR>
     * @@param l_bondProduct - (������)<BR>
     * �������I�u�W�F�N�g<BR>
     * @@param l_strSettleDiv - (���ϋ敪)<BR>
     * ���ϋ敪
     * @@param l_branch - (���X)<BR>
     * ���X
     * @@return webbroker3.bd.WEB3BondExecuteDateInfo
     * @@throws WEB3BaseException
     * @@roseuid 44D9526903B9
     */
    public WEB3BondExecuteDateInfo resetExecuteDateInfo(
        WEB3AdminBondOrderExecInfo l_orderExecInfo, 
        WEB3BondExecuteDateInfo l_executeDateInfo,
        WEB3BondOrderTypeJudge l_bondOrderTypeJudge, 
        WEB3BondProduct l_bondProduct, String l_strSettleDiv, Branch l_branch) throws WEB3BaseException;
    
    /**
     * (get�������b�N�����{�^���敪)<BR>
     * get�������b�N�����{�^���敪<BR>
     * <BR>
     * �������b�N�����{�^���敪�����肵�A�Y������l��Ԃ��B
     * @@param l_orderUnit - (�����P��)<BR>
     * �g���������P��
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 44D2DADE0339
     */
    public String getOrderLockButtonDiv(WEB3BondOrderUnit l_orderUnit) 
        throws WEB3BaseException;
    
    /**
     * (get���ύX�{�^���敪)<BR>
     * get���ύX�{�^���敪<BR>
     * <BR>
     * ���ύX�{�^���敪�����肵�A�Y������l��Ԃ��B
     * @@param l_orderUnit - (�����P��)<BR>
     * �g���������P��
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 44D3241501E7
     */
    public String getExecuteChangButtonDiv(WEB3BondOrderUnit l_orderUnit) throws WEB3BaseException;
    
    /**
     * (get����{�^���敪)<BR>
     * get����{�^���敪<BR>
     * <BR>
     * ����{�^���敪�����肵�A�Y������l��Ԃ��B
     * @@param l_orderUnit - (�����P��)<BR>
     * �g���������P��
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 44D3251D02F9
     */
    public String getCancelButtonDiv(WEB3BondOrderUnit l_orderUnit) throws WEB3BaseException;
    
    /**
     * (to�J�X�g�f�B�A���ꗗ)<BR>
     * to�J�X�g�f�B�A���ꗗ<BR>
     * <BR>
     * ���������ɃJ�X�g�f�B�A���ꗗ��Ԃ�
     * @@param l_lisCustodian - (�J�X�g�f�B�A�����X�g)<BR>
     * �J�X�g�f�B�A�����X�g<BR>
     * <BR>
     *  CustodianRow��List
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 44D93D5D033C
     */
    public List toCustodianList(List l_lisCustodian) throws WEB3BaseException;
    
    /**
     * (to�J�X�g�f�B�A��)<BR>
     * to�J�X�g�f�B�A��<BR>
     * <BR>
     * ���������ɃJ�X�g�f�B�A����Ԃ�
     * @@param l_row - (�J�X�g�f�B�A��Row)<BR>
     * �J�X�g�f�B�A��Row
     * @@return WEB3AdminBondCustodianUnit
     * @@throws WEB3BaseException
     * @@roseuid 44D94A2D01B5
     */
    public WEB3AdminBondCustodianUnit toCustodian(CustodianRow l_row) throws WEB3BaseException;
    
    /**
     * (to�ڋq���)<BR>
     * �������ڋq�����쐬���� <BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq
     * @@return WEB3AdminBondAccountInfo
     * @@throws WEB3BaseException
     * @@roseuid 44D94A2D01B5
     */
    public WEB3AdminBondAccountInfo toAccountInfo(MainAccount l_mainAccount) throws WEB3BaseException;
}
@
