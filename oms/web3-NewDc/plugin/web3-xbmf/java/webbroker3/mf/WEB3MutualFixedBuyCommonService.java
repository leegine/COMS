head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFixedBuyCommonService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �莞��z���t���ʃT�[�r�X(WEB3MutualFixedBuyCommonService)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/06/26 ���G�� (���u) �V�K�쐬
                 : 2006/07/24 �h�C (���u) �d�l�ύX ���f�� No.459
Revesion History : 2008/07/09 ���g (���u) �d�l�ύX ���f�� No.607
Revesion History : 2008/07/31 ���g (���u) �d�l�ύX ���f�� No.621
*/
package webbroker3.mf;

import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;

import webbroker3.common.WEB3BaseException;
import webbroker3.mf.data.MfFixedBuyingCondRow;
import webbroker3.mf.message.WEB3MutualFixedBuyConditionUnit;

/**
 * (�莞��z���t���ʃT�[�r�X)<BR>
 * �莞��z���t���ʃT�[�r�X<BR>
 * 
 * @@author ���G��(���u)
 * @@version 1.0 
 */
public interface WEB3MutualFixedBuyCommonService extends Service 
{
    /**
     * (validate�莞��z���t���z)<BR>
     * �莞��z���t�Œ���z�`�F�b�N�A�P�ʋ��z�`�F�b�N���s�Ȃ��B<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕����<BR>
     * @@param l_strMonthlyBuyAmount - (���t���z�i���X)�j<BR>
     * ���t���z�i���X)<BR>
     * @@param l_strIncreaseBuyAmount - (���t���z�i�ςݑ���))<BR>
     * ���t���z�i�ςݑ����j<BR>
     * @@throws WEB3BaseException
     */
    public void validateFixedBuyAmount(
        SubAccount l_subAccount, 
        String l_strMonthlyBuyAmount,
        String l_strIncreaseBuyAmount) throws WEB3BaseException;
 
    /**
     * (validate�O���،������J��)<BR>
     * �O���،������̊J�݂̕K�v�����邩�ǂ������`�F�b�N����B<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕����<BR>
     * @@param l_mfProduct - (�g�����M����)<BR> 
     * �g�����M����<BR>
     * @@return boolean <BR>
     */
    public boolean validateForeignSecAccOpen(
        SubAccount l_subAccount, 
        WEB3MutualFundProduct l_mfProduct);
       
    /**
     * (get�莞��z���t�������X�g)<BR>
     * �����̏����ɊY������莞��z���t�����̃��X�g���擾����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * @@param l_strAccountCode - (�����R�[�h)<BR>
     * �����R�[�h<BR>
     * @@param l_strQueryString - (��������������)<BR>
     * ��������������<BR>
     * @@param l_objQueryValues - (���������l)<BR>
     * ���������l<BR>
     * @@return List<BR>
     * @@throws WEB3BaseException
     */
    public List getFixedBuyConditionList(
        String l_strInstitutionCode,
        String l_strBranchCode, 
        String l_strAccountCode, 
        String l_strQueryString, 
        Object[] l_objQueryValues) throws WEB3BaseException;
    
    /**
     * (get��s�x�X��)<BR>
     * �����̏����ɊY�������s���E�x�X�����擾����B<BR>
     * @@param l_strFinInstitutionCode - (��s�R�[�h)<BR>
     * ��s�R�[�h<BR>
     * @@param l_strFinBranchCode - (�x�X�R�[�h)<BR>
     * �x�X�R�[�h<BR>
     * @@return String[] <BR>
     * @@throws WEB3BaseException
     */
    public String[] getFinBranchName(
        String l_strFinInstitutionCode,
        String l_strFinBranchCode) throws WEB3BaseException;

    /**
     * (get�莞��z���t�����ύX���X�g)<BR>
     * �����̏����ɊY������莞��z���t�����ύX�̃��X�g���擾����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * @@param l_strAccountCode - (�����R�[�h)<BR>
     * �����R�[�h<BR>
     * @@param l_strQueryString - (��������������)<BR>
     * ��������������<BR>
     * @@param l_objQueryValues - (���������l)<BR>
     * ���������l<BR>
     * @@return List<BR>
     * @@throws WEB3BaseException
     */
    public List getFixedBuyConditionChangeList(
        String l_strInstitutionCode,
        String l_strBranchCode, 
        String l_strAccountCode, 
        String l_strQueryString, 
        Object[] l_objQueryValues) throws WEB3BaseException;

    /**
     * (calc�K�p�J�n�N���i�Ɩ����t�j)<BR>
     * �Ɩ����t�x�[�X�̓K�p�J�n�N�����擾����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * @@return Date<BR>
     * @@throws WEB3BaseException
     */
    public Date calcValidStartDateOrderBizdate(
        String l_strInstitutionCode,
        String l_strBranchCode) throws WEB3BaseException;

    /**
     * (calc�K�p�J�n�N���i���ݓ����j)<BR>
     * ���ݓ����x�[�X�̓K�p�J�n�N�����擾����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * @@return Date<BR>
     * @@throws WEB3BaseException
     */
    public Date calcValidStartDateCurrentDate(
        String l_strInstitutionCode,
        String l_strBranchCode) throws WEB3BaseException;

    /**
     * (validate���������o�^)<BR>
     * �莞��z���t�����������o�^����Ă��邩�`�F�b�N����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * @@param l_strAccountCode - (�����R�[�h)<BR>
     * �����R�[�h<BR>
     */
    public void validateDrawAccountRegist(
        String l_strInstitutionCode,
        String l_strBranchCode, 
        String l_strAccountCode) throws WEB3BaseException;

    /**
     * (calc�ܗ^�m��������z)<BR>
     * �ܗ^�m��������z���擾����B<BR>
     * @@param l_mfFixedBuyingCondRow - (�莞��z���t����Row)<BR>
     * �莞��z���t����Row<BR>
     * @@return String<BR>
     * @@throws WEB3BaseException
     */
    public String calcPrizeAndDecisioDrawAmount(
        MfFixedBuyingCondRow l_mfFixedBuyingCondRow) throws WEB3BaseException;

    /**
     * (is�ܗ^��)<BR>
     * �w�肵���N�����ܗ^�����ǂ������肷��B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_datSelectMY - (�w��N��)<BR>
     * �w��N��<BR>
     * @@return boolean<BR>
     * @@throws WEB3BaseException
     */
    public boolean isPrizeAndMonth(
        String l_strInstitutionCode,
        Date l_datSelectMY) throws WEB3BaseException;

    /**
     * (sort�莞��z���t�����ꗗ)<BR>
     * �w�肳�ꂽ�\�[�g�L�[�A<BR>
     * ���~���Ɋ�Â��ē��M�莞��z���t�����s�̃\�[�g���s���B<BR>
     * @@param l_mutualFixedBuyConditionUnits - (���M�莞��z���t�����s[] )<BR>
     * ���M�莞��z���t�����s[] <BR>
     * @@return WEB3MutualFixedBuyConditionUnit[]<BR>
     * @@throws WEB3BaseException
     */
    public WEB3MutualFixedBuyConditionUnit[] sortFixedBuyConditionList(
        WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnits) throws WEB3BaseException;
}
@
